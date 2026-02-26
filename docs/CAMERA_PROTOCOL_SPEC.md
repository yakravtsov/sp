# Yi Action Camera (YDXJ01XY) — Protocol Specification

> Reverse-engineered из декомпилированного APK. Протокол основан на Ambarella A12 JSON-RPC over TCP.

---

## 1. Транспорт

| Параметр | Значение |
|---|---|
| **IP камеры** | `192.168.42.1` |
| **Порт команд** | `7878` (TCP) |
| **Порт данных** | `8787` (TCP) — data socket (duration/thumbnail stream/firmware upload), не основной download канал для J22 |
| **RTSP превью** | `rtsp://192.168.42.1/live` |
| **HTTP файлы** | `http://192.168.42.1/DCIM/...` |

### 1.1 Фрейминг

- Одно TCP-соединение, persistent.
- Каждое сообщение — JSON-объект, отправляется как строка через `OutputStreamWriter.write()` + `flush()`.
- Ответы читаются из потока, парсятся regex-ом по `{...}` блокам, содержащим `"msg_id"`.
- Несколько JSON-объектов могут прийти в одном TCP-пакете.

### 1.2 Аутентификация (Token)

1. Клиент отправляет **Start Session** (msg_id=257) с `token: 0`.
2. Камера отвечает `{ "rval": 0, "msg_id": 257, "param": <token> }`.
3. Все последующие команды включают `"token": <token>`.
4. Исключение: msg_id 257 всегда отправляется с `token: 0`.

### 1.3 Request / Response

**Запрос:**
```json
{ "msg_id": <int>, "token": <int>, ...params }
```

**Успешный ответ:**
```json
{ "msg_id": <int>, "rval": 0, ...data }
```

**Ответ с ошибкой:**
```json
{ "msg_id": <int>, "rval": <negative_int> }
```

**Нотификация (асинхронная, от камеры):**
```json
{ "msg_id": 7, "type": "<event_name>", "param": "<value>" }
```

### 1.4 Heartbeat

Два механизма:

1. **Камера → Клиент:** msg_id `1793`. Камера периодически отправляет, клиент автоматически отвечает `{ "msg_id": 1793, "rval": 0 }`.
2. **Клиент → Камера:** msg_id `16777244` (0x100001C). Клиент отправляет каждые **5 секунд** (только J22/Z16/Z18/J11). Таймер перезапускается после каждой отправленной команды (кроме самого 16777244).

---

## 2. Команды (msg_id)

### 2.1 Сессия

| msg_id | Название | Параметры запроса | Ответ |
|--------|----------|-------------------|-------|
| **257** | Start Session | `param: 0, heartbeat: 1` | `param: <token>, model: "<str>", album: 0\|1, fwupdate: 0\|1, mvrecover: 0\|1, sdformat: 0\|1, sdoptimize: 0\|1, usbstorage: 0\|1, voice_control: 0\|1, live: 0\|1` |
| **258** | Stop Session | _(нет)_ | `rval: 0` |

**Флаги в ответе Start Session:**
- `album=1` — камера в режиме альбома, управление заблокировано
- `fwupdate=1` / `sdformat=1` / `mvrecover=1` — камера занята операцией
- `sdoptimize=1` — оптимизация SD
- `usbstorage=1` — USB-режим
- `live=1` — live-стриминг

### 2.2 Настройки

| msg_id | Название | Параметры запроса | Ответ |
|--------|----------|-------------------|-------|
| **1** | Get Setting Value | `type: "<setting_name>"` | `param: "<value>"` |
| **2** | Set Setting | `type: "<setting_name>", param: "<value>"` | `rval: 0, related: <int>` + возможно `type`, `param` в ответе |
| **3** | Get All Settings | _(нет)_ | `param: [ {"<key>": "<value>"}, ... ]` — массив JSON-объектов |
| **5** | Get Specific Setting | `type: "<setting_name>"` | `param: "<value>"` |
| **9** | Get Setting Options | `param: "<setting_name>"` | `options: ["val1", "val2", ...]` |

**Особенность msg_id 2:**
- Поле `related` в ответе: если > 0 — есть связанные настройки, которые изменились (см. раздел 6).
- При `type: "wifi_update"` передаётся `param: "wifi_ssid:<ssid>,wifi_password:<pwd>,reboot:1"` — обновление WiFi камеры.

### 2.3 Viewfinder (превью)

| msg_id | Название | Параметры запроса | Описание |
|--------|----------|-------------------|----------|
| **513** | Start Viewfinder / Start Recording | _(нет)_ | Запускает RTSP-стрим (`rtsp://192.168.42.1/live`) или начинает запись (зависит от режима) |
| **515** | Stop Viewfinder | _(нет)_ | Останавливает RTSP-стрим |
| **259** | Reset Viewfinder | `param: "none_force"` | Мягкий перезапуск VF. **Не возвращает JSON-ответ** — разблокируется нотификацией `vf_start` (msg_id 7). Вызывается при init для J22 и после смены `warp_enable`. |
| **260** | Start Viewfinder (alt) | _(нет)_ | Альтернативный запуск VF |

### 2.4 Запись видео

| msg_id | Название | Параметры запроса | Описание |
|--------|----------|-------------------|----------|
| **513** | Start Recording | _(нет)_ | Та же команда, что Start VF. Действие зависит от текущего режима камеры |
| **514** | Stop Recording | _(нет)_ | Остановка текущей записи |
| **1026** | Record Control | `param: "<value>"` | Управление записью с параметрами |
| **16777241** (0x1000019) | Get Record Status | _(нет)_ | Получение текущего времени записи (для timelapse). Ответ: `param: "<seconds>"` |
| **16777227** (0x100000B) | Photo-in-Video (PIV) | _(нет)_ | Сделать фото во время видеозаписи |

### 2.5 Фотосъёмка

| msg_id | Название | Параметры запроса | Описание |
|--------|----------|-------------------|----------|
| **770** | Take Photo | _(нет)_ | Одиночный снимок / остановка серийной съёмки |
| **16777220** (0x1000004) | Start Capture | `param: "<mode>;<option>"` | Запуск съёмки. Примеры param: `"precise quality;off"`, `"burst quality;3"`, `"precise quality cont.;5"`, `"precise self quality;3"` |
| **16777228** (0x100000C) | Set Capture Mode | `param: "<mode>;<option>"` | Переключение режима съёмки (аналогичный формат) |
| **16777238** (0x1000016) | Cancel Timer Capture | _(нет)_ | Отмена съёмки по таймеру |

**Формат param для capture:**
```
<capture_mode>;<option>
```
- `precise quality;off` — обычное фото
- `precise self quality;3` — таймер 3 сек
- `burst quality;3` — серия 3 кадра
- `precise quality cont.;5` — timelapse интервал 5 сек


> **Уточнение по фото-командам:** в исходниках клиента фото отправляется как `msg_id=770`, а `msg_id=769` встречается в обработчике входящих ответов как совместимость/legacy-case. Для J22 в rewrite рекомендуется использовать `770` + `16777220` для capture mode.

### 2.6 Файловые операции

| msg_id | Название | Параметры запроса | Ответ |
|--------|----------|-------------------|-------|
| **1282** | List Files | `param: "<path>", from: <int>, to: <int>` | `listing: [{"<filename>": "<size>bytes|<date>"}, ...]` (формат, используемый в J22 file manager) |
| **1282** | List Files (all) | _(нет params)_ | Все файлы |
| **1283** | Next Directory / Continue Listing | `param: "<dir_path>"` | В J22 клиенте используется как шаг обхода директорий; после ответа обычно снова вызывается `1282` |
| **1281** | Delete File | `param: "<file_path>"` | `rval: 0` |
| **1286** | Upload File | `md5sum: "<hash>", param: "<path>", size: <long>, offset: 0` | Загрузка файла на камеру (firmware) |

**Путь для файлов:** `/tmp/fuse_d/DCIM/100MEDIA/YDXJ0001.mp4`
**HTTP-доступ:** `http://192.168.42.1/DCIM/100MEDIA/YDXJ0001.mp4`

### 2.7 SD-карта

| msg_id | Название | Параметры запроса | Ответ |
|--------|----------|-------------------|-------|
| **5** | Get SD Total | `type: "total"` | `param: <int>` — общий объём SD в KB |
| **5** | Get SD Free | `type: "free"` | `param: <int>` — свободное место в KB |
| **4** | Format SD | `param: "D:"` | `rval: 0` + нотификации `enter_sdformat` / `exit_sdformat` / `sdcard_format_done` |

> **Примечание (J22):** SD Info получается через два запроса msg_id 5 (`type: "total"` и `type: "free"`), НЕ через msg_id 4. Формат SD — msg_id 4 с `param: "D:"`. msg_id 514 — только Stop Recording, без связи с форматированием.

### 2.8 Устройство

| msg_id | Название | Параметры запроса | Описание |
|--------|----------|-------------------|----------|
| **13** | Get Device Info | _(нет)_ | Информация об устройстве (батарея и т.д.) |
| **261** | Register Data Socket | `type: "TCP", param: "<phone_ip>"` | Регистрация IP телефона для data-transfer на порту 8787 |
| **16777232** (0x1000010) | Unbind Bluetooth | _(нет)_ | Отвязка всех Bluetooth-устройств |
| **16777242** (0x100001A) | Download Firmware Log | _(нет)_ | Скачивание лога прошивки (debug) |
| **16777243** (0x100001B) | SD Capacity Estimation (J22 UI) | _(нет)_ | В коде J22 используется в SD-экране для вывода доступных photo/video (`photo`, `video`) |

### 2.9 Режимы

| msg_id | Название | Параметры запроса | Описание |
|--------|----------|-------------------|----------|
| **2** | Set Record Mode | `type: "rec_mode", param: "<mode>"` | Переключение режима записи |
| **2** | Set Capture Mode | `type: "capture_mode", param: "<mode>"` | Переключение режима съёмки |

**Режимы записи (rec_mode):**
- `record` — обычная запись
- `record_timelapse` — timelapse видео
- `record_slow_motion` — slow motion
- `record_photo` — видео+фото
- `record_loop` — цикличная запись

**Режимы съёмки (capture_mode):**
- `precise quality` — обычное фото
- `precise self quality` — таймер
- `precise quality cont.` — timelapse фото
- `burst quality` — серийная съёмка

---


### 2.10 Верификация backlog-команд (по decompiled-коду)

| msg_id | Статус | Где подтверждено в коде | Верифицированное поведение |
|--------|--------|--------------------------|-----------------------------|
| **1025** (0x401) | ✅ Семантика подтверждена | `camera/c.java` + `g.c(type, path, cb)` | Запрос thumbnail metadata по `type`/`param(path)`, в ответе используется поле `size`; после этого запускается загрузка бинарного превью. |
| **16777219** (0x1000003) | ✅ Семантика подтверждена (firmware flow) | `g.h(String, cb)` + `CameraUpgradeWaittingActivity` | Команда вызывается после `put_file_complete` с `param`=`firmware.bin.gz`/`firmware.upload`; используется как шаг запуска обработки загруженного firmware-файла на камере. |
| **16777224** (0x1000008) | ✅ Контекст подтверждён | `CameraWifiSettingActivity` | Команда отправляется сразу после изменения `wifi_password` и перед завершением Wi‑Fi update flow (post-password apply step). |
| **16777229** (0x100000D) | ✅ Контекст подтверждён | `g.p(cb)` + `CameraUpgradeWaittingActivity.f()` | Команда вызывается в начале firmware upgrade waiting flow (pre-upload init step). |
| **16777230** (0x100000E) | ✅ Контекст подтверждён | `g.q(cb)` + `CameraUpgradeWaittingActivity.onDestroy()` + `camera/b.java#g()` | Команда вызывается при завершении/выходе из upgrade/file-control flow (teardown/finalize step). |
| **16777243** (0x100001B) | ✅ Семантика подтверждена | `CameraSDCardActivity` | Ответ содержит поля `photo` и `video`; используются для отображения оценочного количества доступных фото/минут видео на SD. |

> Важно: для 16777224/16777229/16777230 контекст использования подтверждён статическим анализом, но точные side-effects на устройстве всё ещё желательно проверить на реальной камере (integration runbook).

## 3. Нотификации (msg_id = 7)

Камера асинхронно отправляет события:

```json
{ "msg_id": 7, "type": "<event>", "param": "<data>" }
```

### 3.1 Съёмка

| type | param | Описание |
|------|-------|----------|
| `start_photo_capture` | `<capture_mode>;<timer_status>` | Начало фотосъёмки |
| `photo_taken` | `<file_path>` | Фото сделано |
| `burst_complete` | — | Серия завершена |
| `precise_cont_complete` | — | Timelapse фото завершён |
| `self_capture_stop` | — | Таймер отменён |
| `precise_capture_data_ready` | — | Данные снимка готовы |
| `CANNOT_ISSUE_PIV` | — | Невозможно сделать PIV |

### 3.2 Запись

| type | param | Описание |
|------|-------|----------|
| `start_video_record` | — | Запись началась |
| `video_record_complete` | JSON с info | Запись завершена |
| `rec_time` | `<seconds>` | Текущее время записи |

### 3.3 Режимы и настройки

| type | param | Описание |
|------|-------|----------|
| `switch_to_rec_mode` | `<mode>` | Переключение в режим записи |
| `switch_to_cap_mode` | `<mode>` | Переключение в режим съёмки |
| `setting_changed` | `<setting_name>` | Настройка изменена (+ поле `value` в JSON) |

### 3.4 Состояние устройства

| type | param | Описание |
|------|-------|----------|
| `sd_card_status` | `insert` / `remove` | SD-карта вставлена/извлечена |
| `battery_status` | — | Изменение уровня батареи |
| `adapter_status` | `0` / `1` | Зарядка подключена/отключена |

### 3.5 Блокирующие операции

| type | Описание |
|------|----------|
| `enter_album` / `exit_album` | Вход/выход из режима альбома |
| `enter_sdformat` / `exit_sdformat` | Форматирование SD |
| `enter_fwupdate` / `exit_fwupdate` | Обновление прошивки |
| `enter_mvrecover` / `exit_mvrecover` | Восстановление видео |
| `enter_usb_storage` / `exit_usb_storage` | USB-режим |
| `start_usb_storage` | Камера перешла в USB-режим |
| `start_fwupdate` | Камера начала обновление |
| `sdcard_optimize_start` / `sdcard_optimize_stop` | Оптимизация SD |
| `voice_control_sample_start` / `voice_control_sample_stop` | Голосовое управление |
| `enter_live` / `exit_live` | Live-стриминг |

### 3.6 Bluetooth

| type | param | Описание |
|------|-------|----------|
| `btc_delete_all_binded_dev` | `0`-`4` | Результат отвязки BT (0=success, 1-4=errors) |

---

## 4. Коды ошибок (rval)

| rval | Описание |
|------|----------|
| `0` | Успех |
| `-3` | Сессия уже запущена (можно повторить) |
| `-4` | Невалидный токен / сессия истекла |
| `-21` | Камера занята (fwupdate/sdformat/mvrecover) |
| `-23` | Ошибка переключения режима |

---

## 5. Настройки камеры — подтверждённые данные (YDXJ_v22, fw 1.5.12)

> Данные получены реальными запросами msg_id 9 к камере. Статус:
> - ✅ — подтверждено, есть options
> - ⬚ — пустой ответ (rval:0 без options) — не поддерживается данной прошивкой

### 5.1 Видео — настройки записи

| Ключ | Статус | Опции | Описание |
|------|--------|-------|----------|
| `video_resolution` | ✅ | `1920x1080 60P 16:9`, `1920x1080 30P 16:9`, `1920x1080 48P 16:9`, `1920x1080 24P 16:9`, `2304x1296 30P 16:9`, `1280x960 60P 4:3`, `1280x960 48P 4:3`, `1280x720 60P 16:9`, `1280x720 48P 16:9`, `1280x720 120P 16:9`, `848x480 240P 16:9` | Разрешение видео |
| `video_quality` | ✅ | `S.Fine`, `Fine`, `Normal` | Качество видео |
| `video_standard` | ✅ | `NTSC`, `PAL` | Стандарт видео |
| `video_stamp` | ✅ | `off`, `date`, `time`, `date/time` | Штамп на видео |
| `video_rotate` | ✅ | `off`, `on` | Поворот на 180° |
| `meter_mode` | ✅ | `center`, `average`, `spot` | Режим экспозамера |
| `auto_low_light` | ✅ | `on`, `off` | Авто низкая освещённость |
| `warp_enable` | ✅ | `on`, `off` | **Коррекция дисторсии (FOV):** on=flat, off=wide/fisheye |
| `video_shutter` | ⬚ | — | Не поддерживается |
| `fov` | ⬚ | — | Не поддерживается (используй `warp_enable`) |
| `iq_video_wb` | ⬚ | — | Баланс белого — не поддерживается |
| `iq_video_iso` | ⬚ | — | ISO — не поддерживается |
| `iq_video_iso_min` | ⬚ | — | ISO min — не поддерживается |
| `iq_video_ev` | ⬚ | — | Экспокоррекция — не поддерживается |
| `video_sharpness` | ⬚ | — | Резкость — не поддерживается |
| `video_flat_color` | ⬚ | — | Цветовой профиль — не поддерживается |
| `iq_eis_enable` | ⬚ | — | EIS — не поддерживается |
| `sound_effect` | ⬚ | — | Не поддерживается |
| `video_file_max_size` | ⬚ | — | Не поддерживается |
| `video_volume_set` | ⬚ | — | Не поддерживается |
| `video_mute_set` | ⬚ | — | Не поддерживается |

### 5.2 Видео — Timelapse Record

| Ключ | Статус | Опции | Описание |
|------|--------|-------|----------|
| `timelapse_video` | ✅ | `off`, `0.5`, `1`, `2`, `5`, `10`, `30`, `60` | Интервал (секунды) |
| `timelapse_video_duration` | ✅ | _(пустой массив — динамический?)_ | Длительность |
| `timelapse_video_resolution` | ✅ | _(те же 11 что и video_resolution)_ | Разрешение timelapse |
| + `video_quality`, `video_stamp`, `meter_mode`, `auto_low_light`, `warp_enable` ||||

### 5.3 Видео — Record + Photo

| Ключ | Статус | Опции | Описание |
|------|--------|-------|----------|
| `record_photo_time` | ✅ | `5`, `10`, `30`, `60` | Интервал фото (секунды) |
| `video_photo_resolution` | ⬚ | — | Не поддерживается (используется `video_resolution`) |

### 5.4 Видео — Slow Motion / Loop

| Ключ | Статус | Описание |
|------|--------|----------|
| `slow_motion_rate` | ⬚ | Не поддерживается данной прошивкой |
| `slow_motion_res` | ⬚ | Не поддерживается |
| `loop_rec_duration` | ⬚ | Не поддерживается |
| `video_loop_resolution` | ⬚ | Не поддерживается |

> **Примечание:** slow motion и loop recording могут потребовать переключения `rec_mode` для активации опций. Требуется дополнительная проверка.

### 5.5 Фото — настройки съёмки

| Ключ | Статус | Опции | Описание |
|------|--------|-------|----------|
| `photo_size` | ✅ | `16M (4608x3456 4:3)`, `13M (4128x3096 4:3)`, `8M (3264x2448 4:3)`, `5M (2560x1920 4:3)`, `12M (4608x2592 16:9)` | Разрешение фото |
| `photo_quality` | ✅ | `S.Fine`, `Fine`, `Normal` | Качество фото |
| `photo_stamp` | ✅ | `off`, `date`, `time`, `date/time` | Штамп на фото |
| `capture_default_mode` | ✅ | `precise quality`, `precise quality cont.`, `burst quality`, `precise self quality` | Режим съёмки по умолчанию |
| `precise_selftime` | ✅ | `3s`, `5s`, `10s`, `15s` | Таймер обратного отсчёта |
| `burst_capture_number` | ✅ | `3 p / s`, `5 p / s`, `7 p / s`, `7 p / 2s` | Серийная съёмка |
| `precise_cont_time` | ✅ | `0.5 sec`, `1.0 sec`, `2.0 sec`, `5.0 sec`, `10.0 sec`, `30.0 sec`, `60.0 sec` | Интервал timelapse фото |
| `iq_photo_shutter` | ⬚ | — | Не поддерживается |
| `iq_photo_wb` | ⬚ | — | Не поддерживается |
| `iq_photo_iso` | ⬚ | — | Не поддерживается |
| `iq_photo_iso_min` | ⬚ | — | Не поддерживается |
| `iq_photo_ev` | ⬚ | — | Не поддерживается |
| `photo_sharpness` | ⬚ | — | Не поддерживается |
| `photo_flat_color` | ⬚ | — | Не поддерживается |
| `quick_view` | ⬚ | — | Не поддерживается |
| `photo_file_type` | ⬚ | — | Не поддерживается |

### 5.6 Настройки устройства

| Ключ | Статус | Опции | Описание |
|------|--------|-------|----------|
| `system_default_mode` | ✅ | `capture`, `record` | Режим по умолчанию при включении |
| `rec_default_mode` | ✅ | `record`, `record_timelapse` | Подрежим записи по умолчанию |
| `auto_power_off` | ✅ | `off`, `3 minutes`, `5 minutes`, `10 minutes` | Автовыключение |
| `led_mode` | ✅ | `all enable`, `all disable`, `status enable` | Режим LED |
| `buzzer_volume` | ✅ | `high`, `low`, `mute` | Громкость зуммера |
| `video_output_dev_type` | ✅ | `hdmi`, `tv`, `off` | Видеовыход |
| `rc_button_mode` | ✅ | `mode_shutter`, `record_capture` | Режим кнопки пульта |
| `osd_enable` | ✅ | `on`, `off` | Экранное меню |
| `long_shutter_define` | ⬚ | — | Не поддерживается |
| `screen_auto_lock` | ⬚ | — | Не поддерживается |
| `mic_level` | ⬚ | — | Не поддерживается |

### 5.7 Настройки из Get All Settings (read-only / internal)

| Ключ | Значение (пример) | Описание |
|------|-------------------|----------|
| `hw_version` | `YDXJ_v22` | Версия железа |
| `sw_version` | `YDXJv22_1.5.12_build-...` | Версия прошивки |
| `serial_number` | `Z221506A4261890` | Серийный номер |
| `wifi_ssid` | `YDXJ_4261890__2` | SSID WiFi |
| `wifi_password` | `9078563412` | Пароль WiFi |
| `system_mode` | `record` / `capture` | Текущий глобальный режим |
| `rec_mode` | `record` | Текущий подрежим записи |
| `capture_mode` | `precise quality` | Текущий режим съёмки |
| `app_status` | `idle` | Статус камеры |
| `sd_card_status` | `insert` | Статус SD-карты |
| `preview_status` | `on` | Статус превью (RTSP) |
| `piv_enable` | `on` | Photo-in-Video доступна |
| `streaming_status` | `on` | Статус стриминга |
| `dual_stream_status` | `on` | Двойной стрим |
| `precise_cont_capturing` | `off` | Идёт ли timelapse-съёмка |
| `precise_self_running` | `off` | Идёт ли съёмка по таймеру |
| `precise_self_remain_time` | `0` | Оставшееся время таймера |
| `quick_record_time` | `0` | Время быстрой записи |
| `sdcard_need_format` | `no-need` | Нужно ли форматировать SD |
| `dev_functions` | `7743` | Битовая маска функций (readonly) |
| `loop_record` | `off` | Включена ли loop-запись |
| `start_wifi_while_booted` | `off` | WiFi при включении |
| `emergency_file_backup` | `on` | Резервное копирование |
| `buzzer_ring` | `off` | Звонок зуммера |
| `save_log` | `off` | Сохранение лога прошивки |
| `camera_clock` | `2026-02-25 17:40:05` | Часы камеры (settable через msg_id 2) |
| `support_auto_low_light` | `off` | Флаг поддержки |
| `warp_enable` | `off` | Коррекция дисторсии |

### 5.8 msg_id 13 — Device Info

```json
{"rval":0,"msg_id":13,"type":"adapter","param":"100"}
```
- `type: "adapter"` — тип питания (adapter = зарядка подключена)
- `param: "100"` — уровень заряда (%)

### 5.9 msg_id 4 — Format SD Card

```json
{"rval":0,"msg_id":4}
```
> При тестировании вернулся `rval: 0` без данных. Анализ декомпилированного кода показал: msg_id 4 с `param: "D:"` — это **команда форматирования SD-карты**, а не получения информации. Информация о SD получается через msg_id 5 с `type: "total"` и `type: "free"` (два отдельных запроса, ответ в KB). Формат подтверждается нотификациями `enter_sdformat` / `exit_sdformat` / `sdcard_format_done`.

---

## 6. Взаимозависимости настроек

При изменении одной настройки, другие настройки могут стать невалидными. Клиент должен заново запросить их опции (msg_id 9).

| Изменённая настройка | Инвалидируемые настройки |
|----------------------|--------------------------|
| `video_standard` | `video_resolution`, `timelapse_video_resolution` |
| `rec_mode` | Переключение `system_mode` → обновление UI |
| `capture_mode` | Переключение `system_mode` → обновление UI |

> Остальные зависимости из декомпиляции (`iq_*`, `video_shutter`, `slow_motion_rate` → `video_stamp`) не актуальны для данной прошивки, т.к. эти настройки не поддерживаются.

---

## 7. Типичные flow

### 7.1 Подключение

```
1. WiFi connect → 192.168.42.1 (SSID: YDXJ_XXXXXX)
2. TCP connect → 192.168.42.1:7878
3. Recv (возможна нотификация): { "msg_id": 7, "type": "vf_stop" }
4. Send: { "msg_id": 257, "token": 0, "param": 0, "heartbeat": 1 }
5. Recv: { "rval": 0, "msg_id": 257, "param": <token> }
   Token инкрементируется при каждом новом подключении (1, 2, 3, ...)
6. Send: { "msg_id": 3, "token": <token> }  ← Get All Settings
7. Recv: { "rval": 0, "msg_id": 3, "param": [...] }
   Модель определяется из hw_version в ответе (напр. "YDXJ_v22" → J22)
8. Send: { "msg_id": 2, "token": <token>, "type": "camera_clock", "param": "2026-02-25 15:00:00" }
9. Fetch setting options (msg_id 9) для settable-настроек
```

### 7.2 RTSP превью

```
1. Send: { "msg_id": 259, "token": <token>, "param": "none_force" }  ← Reset VF
2. Open RTSP stream: rtsp://192.168.42.1/live
```

### 7.3 Запись видео (start/stop)

```
Start: { "msg_id": 513, "token": <token> }
Stop:  { "msg_id": 514, "token": <token> }
```
Камера отправит нотификации `start_video_record` и `video_record_complete`.

### 7.4 Фотосъёмка

```
Обычное фото:
{ "msg_id": 16777220, "token": <token>, "param": "precise quality;off" }

Серия:
{ "msg_id": 16777220, "token": <token>, "param": "burst quality;3" }

Таймер:
{ "msg_id": 16777220, "token": <token>, "param": "precise self quality;3" }

Timelapse:
{ "msg_id": 16777220, "token": <token>, "param": "precise quality cont.;5" }

Отмена таймера:
{ "msg_id": 16777238, "token": <token> }

Стоп timelapse:
{ "msg_id": 770, "token": <token> }
```

### 7.5 Список файлов и скачивание

```
1. Send: { "msg_id": 1282, "token": <token>, "param": "/tmp/fuse_d/DCIM", "from": 0, "to": 99 }
2. Recv: listing array
3. Download via HTTP: http://192.168.42.1/DCIM/100MEDIA/<filename>
```

### 7.6 Изменение настройки

```
1. Send: { "msg_id": 9, "token": <token>, "param": "video_resolution" }  ← Get Options
2. Recv: { "rval": 0, "msg_id": 9, "options": ["1920x1080 60P 16:9", ...] }
3. Send: { "msg_id": 2, "token": <token>, "type": "video_resolution", "param": "1920x1080 60P 16:9" }
4. Recv: { "rval": 0, "msg_id": 2, "related": 0 }
5. If related > 0: Send msg_id 3 to refresh all settings
```

---

## 8. Модель камеры YDXJ01XY

**Подтверждено тестированием:** `hw_version: "YDXJ_v22"` → модель **J22** в коде.

| Поле | Значение |
|------|----------|
| hw_version | `YDXJ_v22` |
| sw_version | `YDXJv22_1.5.12_build-20170809022523_git-b74efd19_r20` |
| serial_number | `Z221506A4261890` |
| wifi_ssid | `YDXJ_4261890__2` |

Модель J22 в коде означает:
- Полный набор режимов записи и съёмки
- Поддержка `piv_enable`, `warp_enable`, `emergency_file_backup`
- **Нет** `iq_eis_enable` (EIS отключена для J22 в коде)
- `video_loop_resolution` — не отдельная настройка (используется `video_resolution`)

---

## 9. Реальный ответ камеры (Get All Settings, msg_id 3)

> Получено 2025-02-25, прошивка v22_1.5.12, подключение через nc.

```json
[
  { "camera_clock": "2026-02-25 17:40:05" },
  { "video_standard": "NTSC" },
  { "app_status": "idle" },
  { "video_resolution": "1280x960 48P 4:3" },
  { "video_stamp": "off" },
  { "video_quality": "S.Fine" },
  { "timelapse_video": "1" },
  { "capture_mode": "precise quality" },
  { "photo_size": "16M (4608x3456 4:3)" },
  { "photo_stamp": "off" },
  { "photo_quality": "S.Fine" },
  { "timelapse_photo": "off" },
  { "preview_status": "on" },
  { "buzzer_volume": "low" },
  { "buzzer_ring": "off" },
  { "capture_default_mode": "precise quality" },
  { "precise_cont_time": "0.5 sec" },
  { "burst_capture_number": "7 p / s" },
  { "wifi_ssid": "YDXJ_4261890__2" },
  { "wifi_password": "9078563412" },
  { "led_mode": "status enable" },
  { "meter_mode": "center" },
  { "sd_card_status": "insert" },
  { "video_output_dev_type": "hdmi" },
  { "sw_version": "YDXJv22_1.5.12_build-20170809022523_git-b74efd19_r20" },
  { "hw_version": "YDXJ_v22" },
  { "dual_stream_status": "on" },
  { "streaming_status": "on" },
  { "precise_cont_capturing": "off" },
  { "piv_enable": "on" },
  { "auto_low_light": "on" },
  { "loop_record": "off" },
  { "warp_enable": "off" },
  { "support_auto_low_light": "off" },
  { "precise_selftime": "5s" },
  { "precise_self_running": "off" },
  { "auto_power_off": "off" },
  { "serial_number": "Z221506A4261890" },
  { "system_mode": "record" },
  { "system_default_mode": "record" },
  { "start_wifi_while_booted": "off" },
  { "quick_record_time": "0" },
  { "precise_self_remain_time": "0" },
  { "sdcard_need_format": "no-need" },
  { "video_rotate": "off" },
  { "emergency_file_backup": "on" },
  { "osd_enable": "off" },
  { "rec_default_mode": "record" },
  { "rec_mode": "record" },
  { "record_photo_time": "5" },
  { "dev_functions": "7743" },
  { "rc_button_mode": "mode_shutter" },
  { "timelapse_video_duration": "off" },
  { "timelapse_video_resolution": "1920x1080 60P 16:9" },
  { "save_log": "off" }
]
```

### Настройки, обнаруженные только в реальном ответе (не из декомпиляции)

| Ключ | Значение | Описание |
|------|----------|----------|
| `dual_stream_status` | `on` | Двойной стрим (основной + превью) |
| `streaming_status` | `on` | Статус стриминга |
| `support_auto_low_light` | `off` | Флаг поддержки auto low light |
| `quick_record_time` | `0` | Время быстрой записи |
| `sdcard_need_format` | `no-need` | Нужно ли форматировать SD |
| `osd_enable` | `off` | Экранное меню (OSD) |
| `rec_default_mode` | `record` | Режим записи по умолчанию |
| `dev_functions` | `7743` | Битовая маска доступных функций |
| `rc_button_mode` | `mode_shutter` | Режим кнопки пульта |
| `photo_quality` | `S.Fine` | Качество фото (отдельно от video) |
| `timelapse_photo` | `off` | Статус timelapse фото |
