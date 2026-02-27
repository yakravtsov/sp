# Crash analysis: `android-prototype/app/crash.log`

## Root cause

Application crashes on main thread with:

- `FATAL EXCEPTION: main`
- `java.lang.SecurityException: ... was not granted either of these permissions: android.permission.CHANGE_NETWORK_STATE, android.permission.WRITE_SETTINGS`

The stack trace points to a direct call to `ConnectivityManager.requestNetwork(...)` in `CameraNetworkBinder.requestWifiNetwork`.

## Why it happens

1. `connect()` in `MainViewModel` launches `repository.connect()`.
2. `CameraRepository.connect()` calls `binder.bindCameraNetwork()`.
3. `bindCameraNetwork()` calls `requestWifiNetwork()`.
4. `requestWifiNetwork()` invokes `connectivityManager.requestNetwork(...)`.
5. Without `CHANGE_NETWORK_STATE` (or `WRITE_SETTINGS`), Android throws `SecurityException` immediately.
6. Exception is not handled before it bubbles to main coroutine context, causing process crash.

## Immediate fixes

### 1) Add missing manifest permission

Add to `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
```

This is the expected permission for `requestNetwork` in this code path.

### 2) Defensive exception handling around network request

Wrap `requestNetwork(...)` in `runCatching`/`try` and return `Result.failure(...)` instead of crashing.

### 3) Handle connect failures in UI layer

Wrap `repository.connect()` in a guarded launch (`runCatching`) and map failures to UI state/error message.

## Recommended hardening

- Add log event for permission-denied (`SecurityException`) with actionable message.
- Add instrumentation/unit test for binder behavior when permission is missing (expect failure result, no crash).
- Consider graceful fallback: continue app usage with limited functionality if camera network binding fails.

## Non-root log entries

- `ashmem ... Pinning is deprecated since Android Q` is a warning, not the crash trigger.
- `SurfaceFlinger ... Application Error` appears after the crash dialog is shown.
