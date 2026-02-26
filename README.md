# Yi Camera Prototype (Android)

Прототип Android-приложения для управления Yi Action Camera (J22 / `YDXJ_v22`) по Wi‑Fi/TCP и просмотра RTSP live-потока.

> Статус: рабочий прототип в активной доработке. Детальный список незавершённых задач см. в `docs/REMAINING_WORK.md`.

## 1. Что находится в репозитории

- Документация по протоколу и плану: `docs/`.
- Android-проект прототипа: `android-prototype/`.

## 2. Требования (Ubuntu 24.04)

Установите:
1. `git`
2. `OpenJDK 17`
3. Android Studio (рекомендуется)
4. Android SDK Platform + Build Tools (через Android Studio SDK Manager)
5. ADB (`android-sdk-platform-tools`)

Пример установки базовых пакетов:

```bash
sudo apt update
sudo apt install -y git openjdk-17-jdk android-sdk-platform-tools
```

Проверьте Java:

```bash
java -version
```

Ожидается JDK 17.

## 3. Клонирование проекта

```bash
git clone <URL_ВАШЕГО_РЕПОЗИТОРИЯ>
cd sp
```

## 4. Открытие проекта в Android Studio (рекомендуемый путь)

1. Запустите Android Studio.
2. `Open` → выберите папку `sp/android-prototype`.
3. Дождитесь Gradle Sync.
4. Если Studio попросит установить SDK-компоненты — согласитесь.

## 5. Настройка Android-устройства для запуска

1. На телефоне включите **Developer options**.
2. Включите **USB debugging**.
3. Подключите телефон по USB.
4. Проверьте, что устройство видно в ADB:

```bash
adb devices
```

Статус должен быть `device` (не `unauthorized`).

## 6. Сборка и установка debug-сборки

### Вариант A: из Android Studio

1. Выберите подключённое устройство.
2. Нажмите `Run`.

### Вариант B: через Gradle из терминала

> В текущем репозитории может отсутствовать Gradle Wrapper. Если его нет, используйте Android Studio или локальный `gradle`.

```bash
cd android-prototype
gradle :app:assembleDebug
```

Установка APK (если собирали вручную):

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## 7. Подключение к камере и первый запуск

1. Подключите телефон к Wi‑Fi камеры Yi.
2. Запустите приложение на телефоне.
3. На главном экране нажмите `Connect`.
4. После успешного подключения проверьте:
   - состояние соединения;
   - получение сессии;
   - обновление тех-лога.
5. Нажмите `Start Live` для RTSP (`rtsp://192.168.42.1/live`).
6. Проверьте команды `Start Rec / Stop Rec / Photo`.
7. Проверьте раздел файлов (обновление списка и загрузка).

## 8. Полезные команды отладки

Логи Android-приложения:

```bash
adb logcat
```

Фильтрация по приложению (пример):

```bash
adb logcat | rg yicameraprototype
```

## 9. Типовые проблемы

1. **`adb devices` показывает `unauthorized`**  
   Подтвердите RSA-ключ на телефоне и переподключите USB.

2. **Gradle build падает из-за SDK**  
   Установите нужные Android SDK Platform/Build Tools через SDK Manager в Android Studio.

3. **Нет подключения к камере**  
   Убедитесь, что телефон реально в Wi‑Fi камеры и адрес камеры доступен (`192.168.42.1`).

4. **Live-поток не стартует**  
   Проверьте стабильность Wi‑Fi камеры и статус сессии после `Connect`.

## 10. Что делать дальше

- Список оставшихся работ: `docs/REMAINING_WORK.md`.
- Базовый целевой план реализации: `docs/IMPLEMENTATION_PLAN.md`.
