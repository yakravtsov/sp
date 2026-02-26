# Yi Action Camera — Transport Layer Specification

> Анализ декомпилированного кода: `com.xiaoyi.camera.b` (TCP-сокет) и `com.xiaoyi.camera.controller.a` (WiFi-подключение и сессия).

---

## 1. TCP-сокет (`com.xiaoyi.camera.b`)

### 1.1 Архитектура

Singleton-подобный класс, управляемый из `com.xiaoyi.camera.g`. Содержит:
- **Очередь команд** — `LinkedBlockingQueue<d>` (FIFO)
- **Текущую команду** — `d e` (одна команда в обработке)
- **Флаг готовности** — `boolean g` (true = можно отправлять следующую)
- **Флаг остановки** — `boolean h` (true = сокет закрыт)
- **Сокет-поток** — внутренний `Thread a`
- **Heartbeat Timer** — `Timer` + `TimerTask`

### 1.2 Однопоточная модель (Thread `a`)

Весь I/O в одном потоке. Main loop:

```
1. Установить TCP-соединение (с retry)
2. while (!stopped):
   a. Если очередь непуста И g==true (готов):
      - Извлечь команду из очереди
      - g = false
      - Отправить JSON
      - Перезапустить heartbeat timer (для J22)
   b. Иначе:
      - Читать из сокета (poll)
3. Cleanup: закрыть reader, writer, socket
```

**Ключевое:** одновременно в обработке только одна команда. Следующая отправляется только после получения ответа на текущую (или ошибки).

### 1.3 Установка соединения

```
IP:     192.168.42.1
Port:   7878
Retry:  3 попытки (с паузой 1000ms между ними)
```

**Параметры Socket:**
| Параметр | Значение |
|----------|----------|
| TcpNoDelay | `true` |
| TrafficClass | `20` |
| KeepAlive | `true` |
| PerformancePreferences | `(0, 1, 1)` — приоритет latency и bandwidth |
| SoTimeout | `2000ms` (read timeout) |
| ReuseAddress | `true` |
| Connect timeout | ~`2000ms` |

**I/O:**
- `InputStreamReader` для чтения
- `OutputStreamWriter` для записи
- Буфер чтения: `char[32768]`
- `StringBuilder` для накопления фрагментов

### 1.4 Отправка команды

```
1. Установить token:
   - msg_id 257 → token = 0 (всегда)
   - msg_id 1793 → token = текущий, затем g = true
   - остальные → token = текущий
2. Сериализовать d → JSONObject → String
3. OutputStreamWriter.write(string) + flush()
4. При ошибке:
   - g = true (разблокировать очередь)
   - Вызвать error callback текущей команды
   - Обнулить текущую команду
```

### 1.5 Чтение из сокета

```
1. Проверить reader.ready()
   - Если нет: sleep(50ms), return
2. read(char[32768])
   - Если -1: сокет отключён
3. Append в StringBuilder
4. Быстрая проверка: первый символ '{' И последний '}'
   - Если да: запустить парсинг JSON
   - Если нет: ждём дальше (фрагментированный пакет)
```

### 1.6 Парсинг нескольких JSON в одном пакете

Regex-паттерн для извлечения JSON-объектов:
```regex
\{[^\{\}]*(\{[^\{\}]*\}[^\{\}]*)*(\[\{[^\{\}]*\}\][^\{\}]*)*\}
```

Алгоритм:
```
1. Применить regex matcher к StringBuilder
2. Для каждого match:
   a. Проверить содержит ли "msg_id"
   b. Если да: dispatch (b(String))
   c. Удалить обработанный фрагмент из StringBuilder
3. При JSONException/IOException: удалить фрагмент, установить g=true
```

### 1.7 Dispatch входящих сообщений

Приоритет обработки по msg_id:

| msg_id | Обработка |
|--------|-----------|
| **1793** | **Heartbeat от камеры.** Немедленный ответ: `{msg_id:1793, rval:0}`. Отправляется через приоритетную отправку (минуя очередь). |
| **257** | **Start Session ответ.** Если `rval < 0` или `param < 0` → error callback. Иначе: сохранить token из `param`, `b.b = true` (сессия активна). |
| **7** | **Нотификация.** Передаётся в notification listener (`c.b.a(json)`). Особый случай: если текущая команда = 259 (Reset VF), `g = true` (VF reset не возвращает отдельный ответ, нотификация разблокирует очередь). |
| **258** | **Stop Session.** Если `rval == 0`: `b.b = false`, fire event. |
| **Прочие** | Матчинг по msg_id текущей команды (`e.a() == optInt`). Если совпал: `g = true`, вызвать success/error callback по `rval`. Если `rval == -4` → fire "token expired" event. Обнулить текущую команду. |

**Важно:** если msg_id ответа не совпадает с текущей командой и не является 1793/7/258 — ответ игнорируется.

### 1.8 Heartbeat (клиентский)

Помимо ответа на msg_id 1793 от камеры, клиент **сам** периодически отправляет msg_id **16777244** (0x100001C):

```
Интервал:  5000ms (Timer schedule с delay=5000, period=5000)
Действие:  отправка {msg_id: 16777244, token: <token>} в очередь
Модели:    J22, Z16, Z18, J11
```

**Логика перезапуска:** при отправке каждой команды (кроме 16777244):
1. Отменить текущий heartbeat timer
2. Запустить новый timer (5000ms delay/period)

Итого **два heartbeat-механизма:**
- **Камера → Клиент:** msg_id 1793, клиент отвечает `{msg_id:1793, rval:0}`
- **Клиент → Камера:** msg_id 16777244, каждые 5 секунд (для J22)

### 1.9 Очередь команд

- `LinkedBlockingQueue<d>` — потокобезопасная FIFO-очередь
- **Дедупликация:** если команда уже в очереди — не добавлять повторно. Исключения: msg_id 515 (Stop VF) и 16777241 (Get Record Status) — всегда добавляются.
- **Приоритетная отправка** (`a(d)`): устанавливает `g=true`, добавляет в очередь, ставит `h=true`. Используется для Stop Session (msg_id 258).
- **Обычная отправка** (`b(d)`): если очередь существует и не в режиме остановки — добавить. Иначе — вызвать error callback.

### 1.10 Закрытие

```
1. Отменить heartbeat timer
2. Установить флаг stopped (b=true) в потоке
3. Закрыть сокет
4. interrupt() + join(1000ms) на потоке
5. Очистить очередь
6. g = true, текущая команда = null, b.b = false
```

---

## 2. WiFi-подключение и сессия (`com.xiaoyi.camera.controller.a`)

### 2.1 Архитектура

Управляет полным lifecycle подключения: WiFi → TCP → Session → Init.

Компоненты:
- **Handler** (`HandlerC0207a`) — обработка сообщений в UI-потоке
- **BroadcastReceiver** (`c`) — отслеживание WiFi-состояния
- **Callback interface** (`b`) — уведомления вызывающего кода

### 2.2 Полный flow подключения

```
1. a(devicedao.a) — начать подключение
   ├── Зарегистрировать WiFi BroadcastReceiver
   ├── Установить CONNECTION_TIME_OUT = 7000ms
   ├── Если уже подключены к WiFi камеры → d() (шаг 3)
   └── Иначе: настроить WiFi, включить, ждать broadcast

2. BroadcastReceiver → WiFi connected
   └── Отправить CONNECTION_SUCCESS → Handler

3. d() — Start TCP Session
   ├── Guard: если isStartSession == true → return
   ├── isStartSession = true
   ├── Сбросить timeout handler
   └── g.a().a(this) → отправить Start Session (msg_id 257)

4. Handler ← msg_id 257 (Start Session ответ)
   ├── Парсить флаги: model, album, fwupdate, mvrecover, sdformat,
   │   sdoptimize, usbstorage, voice_control, live
   ├── Если album==1 → abort (камера в режиме альбома, код -1102)
   ├── Если fwupdate/mvrecover/sdformat → abort (камера занята, код -21)
   ├── Если sdoptimize → abort (код -1103)
   ├── Если usbstorage → abort (код -1104)
   ├── Если voice_control → abort (код -1105)
   ├── Если live → abort (код -1106)
   └── OK → сохранить model, перейти к шагу 5

5. j() → Get All Settings (msg_id 3)
   └── g.a().i(this)

6. a(JSONObject) — обработка всех настроек
   ├── Распарсить param array, сохранить
   ├── Если idle и не идёт съёмка:
   │   └── Синхронизировать часы (camera_clock → текущее время телефона)
   └── Иначе: перейти к шагу 8

7. Handler ← msg_id 2 (camera_clock ответ)
   └── f() → шаг 7a

7a. f() — после синхронизации часов
   ├── Для J22: отправить Reset VF (msg_id 259)
   └── Перейти к шагу 8

8. i() — Init Complete
   ├── Инициализировать camera module
   ├── Зарегистрировать data socket (msg_id 261)
   ├── Запросить options для всех настроек (h())
   └── Уведомить listener об успешном подключении
```

### 2.3 Запрос options при инициализации

Метод `h()` запрашивает options (msg_id 9) для ключевых настроек:

**Все модели:**
- `system_default_mode` → заполняет списки photo/video modes
- `precise_selftime`
- `burst_capture_number`
- `precise_cont_time`

**Только J22/Z16/Z18/J11:**
- `slow_motion_rate`
- `slow_motion_res` (если настройка непуста)
- `timelapse_video`
- `timelapse_video_duration`
- `record_photo_time`
- `loop_rec_duration`

### 2.4 Network Binding (API 23+)

Для гарантии маршрутизации трафика через WiFi камеры (а не через мобильные данные):

```java
ConnectivityManager.registerNetworkCallback(
    new NetworkRequest.Builder()
        .addTransportType(TRANSPORT_WIFI)
        .removeCapability(NET_CAPABILITY_VALIDATED)  // WiFi камеры не имеет интернета
        .build(),
    new NetworkCallback() {
        onAvailable(network) → NetworkUtil.init(context, network)  // bind process
        onLost(network) → NetworkUtil.init(context, null)  // unbind
    }
);
```

**Ключевое:** `removeCapability(NET_CAPABILITY_VALIDATED)` — без этого Android не будет использовать WiFi камеры, потому что у неё нет интернета.

### 2.5 Retry-логика

| Сценарий | Retries | Timeout | Действие |
|----------|---------|---------|----------|
| WiFi подключение | 3 | 7000ms | Повтор `a(devicedao.a)` |
| TCP Socket | 3 | ~2000ms connect + 2000ms read | Повтор в цикле (b.java) |
| Start Session (msg_id 257) | 3 (`l` field) | — | Повтор `g.a().a(this)` |
| Auth failure (WiFi password) | 3 (`o` field) | — | Удалить WiFi config, повтор |
| Get All Settings error -21 | ∞ | — | Повтор `j()` (камера занята) |

### 2.6 Error codes (внутренние)

| Код | Значение |
|-----|----------|
| -3 | Сессия уже запущена (rval от камеры) |
| -4 | Невалидный token (rval от камеры) |
| -21 | Камера занята (rval от камеры) |
| -1101 | WiFi connection failed |
| -1102 | Камера в режиме альбома |
| -1103 | SD card optimization |
| -1104 | USB storage mode |
| -1105 | Voice control mode |
| -1106 | Live streaming mode |

### 2.7 BroadcastReceiver — отслеживаемые события

| Action | Обработка |
|--------|-----------|
| `CONNECTIVITY_CHANGE` | Проверка подключения к WiFi камеры → CONNECTION_SUCCESS |
| `WIFI_STATE_CHANGED` | Логирование состояния WiFi |
| `NETWORK_STATE_CHANGED` | Проверка CONNECTED state |
| `SUPPLICANT_STATE_CHANGE` | Обнаружение ошибки пароля (errorCode==1 + DISCONNECTED) |

---

## 3. Ключевые выводы для реализации

### 3.1 Однокомандная модель
Камера обрабатывает одну команду за раз. Необходима очередь с последовательной отправкой. Следующая команда отправляется только после ответа/ошибки/таймаута предыдущей.

### 3.2 Два heartbeat-механизма
- **Пассивный:** ответ на msg_id 1793 от камеры
- **Активный:** периодическая отправка msg_id 16777244 (каждые 5с, J22)

### 3.3 Нотификации не блокируют очередь
msg_id 7 обрабатывается параллельно. Исключение: если текущая команда = 259 (Reset VF), нотификация разблокирует очередь (VF reset не имеет отдельного ответа).

### 3.4 Network binding обязателен
Без привязки процесса к WiFi-сети камеры Android будет маршрутизировать TCP через мобильные данные. Критично для Android 6+ (API 23+).

### 3.5 Таймауты
- Socket connect: ~2000ms
- Socket read: 2000ms (SoTimeout)
- WiFi connection: 7000ms
- Между poll'ами при отсутствии данных: 50ms sleep

### 3.6 Фрагментация
Данные из сокета могут приходить фрагментировано. StringBuilder накапливает, парсинг запускается только когда буфер начинается с `{` и заканчивается `}`. Regex извлекает все полные JSON-объекты.
