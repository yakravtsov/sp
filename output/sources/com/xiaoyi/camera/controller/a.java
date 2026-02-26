package com.xiaoyi.camera.controller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import com.xiaoyi.camera.d;
import com.xiaoyi.camera.d.f;
import com.xiaoyi.player.NetworkUtil;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wseemann.media.BuildConfig;
import wseemann.media.FFmpegMediaMetadataRetriever;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes3.dex */
public class a implements com.xiaoyi.camera.c.a {

    /* renamed from: a, reason: collision with root package name */
    private Context f4831a;
    private b b;
    private com.xiaoyi.camera.d.c c;
    private f d;
    private c e;
    private com.xiaoyi.camera.devicedao.a f;
    private WifiConfiguration g;
    private boolean i;
    private boolean k;
    private boolean j = false;
    private int l = 3;
    private int m = 3;
    private long n = 0;
    private int o = 0;
    private long p = 0;
    private HandlerC0207a h = new HandlerC0207a(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.xiaoyi.camera.controller.a$a, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public class HandlerC0207a extends Handler {
        public HandlerC0207a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            super.handleMessage(message);
            if (a.this.k) {
                return;
            }
            switch (message.what) {
                case 2:
                    g.a("debug_wifi", "AMBA_SET_SETTING", new Object[0]);
                    a.this.f();
                    return;
                case 3:
                    g.a("debug_wifi", "AMBA_GET_ALL_CURRENT_SETTINGS", new Object[0]);
                    a.this.a((JSONObject) message.obj);
                    return;
                case InputDeviceCompat.SOURCE_KEYBOARD /* 257 */:
                    g.a("debug_wifi", "SESSION_START_SUCCESS", new Object[0]);
                    int i = 0;
                    str = "Z13";
                    JSONObject jSONObject = (JSONObject) message.obj;
                    if (jSONObject != null) {
                        try {
                            str = jSONObject.isNull("model") ? "Z13" : jSONObject.getString("model");
                            r9 = jSONObject.isNull(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM) ? 0 : jSONObject.getInt(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
                            r8 = jSONObject.isNull("fwupdate") ? 0 : jSONObject.getInt("fwupdate");
                            r7 = jSONObject.isNull("mvrecover") ? 0 : jSONObject.getInt("mvrecover");
                            r6 = jSONObject.isNull("sdformat") ? 0 : jSONObject.getInt("sdformat");
                            r5 = jSONObject.isNull("sdoptimize") ? 0 : jSONObject.getInt("sdoptimize");
                            r4 = jSONObject.isNull("usbstorage") ? 0 : jSONObject.getInt("usbstorage");
                            r3 = jSONObject.isNull("voice_control") ? 0 : jSONObject.getInt("voice_control");
                            i = !jSONObject.isNull("live") ? jSONObject.getInt("live") : 0;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (r9 == 1) {
                        com.xiaoyi.camera.b.b = false;
                        if (a.this.h != null) {
                            a.this.m = 3;
                            a.this.g();
                            if (a.this.b != null) {
                                a.this.b.a(false, a.this.f, -1102, 0L);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (r8 == 1 || r7 == 1 || r6 == 1) {
                        com.xiaoyi.camera.g.a().c();
                        if (a.this.b != null) {
                            a.this.b.a(true, a.this.f, -21, 0L);
                            return;
                        }
                        return;
                    }
                    if (r5 == 1) {
                        com.xiaoyi.camera.b.b = false;
                        if (a.this.h != null) {
                            a.this.m = 3;
                            a.this.g();
                            if (a.this.b != null) {
                                a.this.b.a(false, a.this.f, -1103, 0L);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (r4 == 1) {
                        com.xiaoyi.camera.b.b = false;
                        if (a.this.h != null) {
                            a.this.m = 3;
                            a.this.g();
                            if (a.this.b != null) {
                                a.this.b.a(false, a.this.f, -1104, 0L);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (r3 == 1) {
                        com.xiaoyi.camera.b.b = false;
                        if (a.this.h != null) {
                            a.this.m = 3;
                            a.this.g();
                            if (a.this.b != null) {
                                a.this.b.a(false, a.this.f, -1105, 0L);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (i != 1) {
                        i.a().a("model", str);
                        a.this.c(str);
                        return;
                    }
                    com.xiaoyi.camera.b.b = false;
                    if (a.this.h != null) {
                        a.this.m = 3;
                        a.this.g();
                        if (a.this.b != null) {
                            a.this.b.a(false, a.this.f, -1106, 0L);
                            return;
                        }
                        return;
                    }
                    return;
                case InputDeviceCompat.SOURCE_TOUCHSCREEN /* 4098 */:
                    g.a("debug_wifi", "CONNECTION_SUCCESS", new Object[0]);
                    g.a("debug_wifi", "registerNotificationHandler", new Object[0]);
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (a.this.b != null) {
                        a.this.b.d(a.this.f, elapsedRealtime - a.this.n);
                    }
                    a.this.d();
                    return;
                case FragmentTransaction.TRANSIT_FRAGMENT_FADE /* 4099 */:
                    g.a("debug_wifi", "CONNECTION_TIME_OUT", new Object[0]);
                    a.this.k();
                    return;
                case 4100:
                    g.a("debug_wifi", "CONNECTION_FAILED", new Object[0]);
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    a.this.g();
                    if (a.this.b != null) {
                        a.this.o = 0;
                        a.this.b.a(a.this.f, -1101, elapsedRealtime2 - a.this.n);
                        return;
                    }
                    return;
                case 4101:
                    g.a("debug_wifi", "CONNECTION_AUTHENTICATE_FAILED", new Object[0]);
                    long elapsedRealtime3 = SystemClock.elapsedRealtime();
                    a.this.b(false);
                    a.this.b(elapsedRealtime3 - a.this.n);
                    return;
                case 8197:
                    long elapsedRealtime4 = SystemClock.elapsedRealtime();
                    com.xiaoyi.camera.g.a().c();
                    g.a("debug_wifi", "unRegisterNotificationHandler", new Object[0]);
                    if (a.this.b != null) {
                        a.this.b.a(true, a.this.f, message.arg1, elapsedRealtime4 - a.this.n);
                        return;
                    }
                    return;
                case 8199:
                    g.a("debug_wifi", "INIT_COMPLETE", new Object[0]);
                    g.a("debug_wifi", "unRegisterNotificationHandler", new Object[0]);
                    a.this.i();
                    return;
                case 16384:
                    g.a("debug_wifi", "WIFI_CONNECTION_EXCEPTION set isStartSession false", new Object[0]);
                    a.this.j = false;
                    a.this.m = 3;
                    g.a("debug_wifi", "------------- WIFI_CONNECTION_EXCEPTION", new Object[0]);
                    a.this.a(a.this.f);
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes3.dex */
    public interface b {
        void a(com.xiaoyi.camera.devicedao.a aVar, int i, long j);

        void a(com.xiaoyi.camera.devicedao.a aVar, long j);

        void a(boolean z, com.xiaoyi.camera.devicedao.a aVar, int i, long j);

        void b(com.xiaoyi.camera.devicedao.a aVar, long j);

        void c(com.xiaoyi.camera.devicedao.a aVar, long j);

        void d(com.xiaoyi.camera.devicedao.a aVar, long j);

        void n_();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            String valueOf;
            if (a.this.k) {
                return;
            }
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                if (networkInfo != null) {
                    if (networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED || Build.VERSION.SDK_INT == 23) {
                        if (a.this.e()) {
                            g.a("debug_wifi", "received NETWORK_STATE_CHANGED_ACTION, EXTRA_NETWORK_INFO  1: ", new Object[0]);
                            return;
                        } else {
                            if (a.this.i) {
                                g.a("debug_wifi", "received NETWORK_STATE_CHANGED_ACTION, EXTRA_NETWORK_INFO  2: ", new Object[0]);
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                g.a("debug_wifi", "wifi stat: " + intent.getIntExtra("wifi_state", 4), new Object[0]);
                return;
            }
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                g.a("debug_wifi", "received CONNECTIVITY_ACTION, is camera wifi: " + a.this.e(), new Object[0]);
                if (((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).getDetailedState() == NetworkInfo.DetailedState.CONNECTED || Build.VERSION.SDK_INT == 23) {
                    g.a("debug_wifi", "received CONNECTIVITY_ACTION, is camera wifi: isConnectStart = " + a.this.i, new Object[0]);
                    if (!a.this.e()) {
                        if (a.this.i) {
                            a.this.a(a.this.f);
                            return;
                        }
                        return;
                    } else {
                        Message.obtain(a.this.h, InputDeviceCompat.SOURCE_TOUCHSCREEN).sendToTarget();
                        a.this.i = false;
                        a.this.h.removeMessages(16384);
                        a.this.h.sendEmptyMessageDelayed(16384, 7000L);
                        return;
                    }
                }
                return;
            }
            if (!intent.getAction().equals("android.net.wifi.supplicant.STATE_CHANGE") || a.this.f4831a == null) {
                return;
            }
            WifiInfo connectionInfo = ((WifiManager) a.this.f4831a.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                SupplicantState supplicantState = connectionInfo.getSupplicantState();
                if (supplicantState == SupplicantState.ASSOCIATED) {
                    valueOf = "关联AP完成";
                    z = false;
                } else if (supplicantState.toString().equals("AUTHENTICATING")) {
                    valueOf = "正在验证";
                    z = false;
                } else if (supplicantState == SupplicantState.ASSOCIATING) {
                    valueOf = "正在关联AP...";
                    z = false;
                } else if (supplicantState == SupplicantState.COMPLETED) {
                    valueOf = "已连接: " + connectionInfo.getSSID();
                    z = false;
                } else if (supplicantState == SupplicantState.DISCONNECTED) {
                    valueOf = "已断开";
                    z = true;
                } else if (supplicantState == SupplicantState.DORMANT) {
                    valueOf = "暂停活动";
                    z = false;
                } else if (supplicantState == SupplicantState.FOUR_WAY_HANDSHAKE) {
                    valueOf = "四路握手中...";
                    z = false;
                } else if (supplicantState == SupplicantState.GROUP_HANDSHAKE) {
                    valueOf = "GROUP_HANDSHAKE";
                    z = false;
                } else if (supplicantState == SupplicantState.INACTIVE) {
                    valueOf = "休眠中...";
                    z = false;
                } else if (supplicantState == SupplicantState.INVALID) {
                    valueOf = "无效";
                    z = false;
                } else if (supplicantState == SupplicantState.SCANNING) {
                    valueOf = "扫描中...";
                    z = false;
                } else if (supplicantState == SupplicantState.UNINITIALIZED) {
                    valueOf = "未初始化";
                    z = false;
                } else {
                    valueOf = String.valueOf(supplicantState);
                    z = false;
                }
                g.a("debug_wifi", "status: " + valueOf, new Object[0]);
            } else {
                z = false;
            }
            int intExtra = intent.getIntExtra("supplicantError", -1);
            g.a("debug_wifi", "status : errorCode ＝ " + intExtra, new Object[0]);
            if (z && intExtra == 1) {
                WifiConfiguration wifiConfiguration = null;
                if (a.this.f != null) {
                    wifiConfiguration = a.this.d.a(a.this.f.b());
                }
                g.a("debug_wifi", "Received ERROR_AUTHENTICATING, isAuthenticateFailed: " + a.this.d.c(wifiConfiguration), new Object[0]);
                Message.obtain(a.this.h, 4101).sendToTarget();
                g.a("debug_wifi", "wifi password error.", new Object[0]);
            }
        }
    }

    public a(Context context) {
        this.f4831a = context.getApplicationContext();
        this.c = com.xiaoyi.camera.d.c.a(this.f4831a);
        this.d = new f(this.f4831a);
        if (!this.d.b()) {
            this.d.a();
        }
        this.e = new c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("param");
        if (jSONObject == null || optJSONArray == null || optJSONArray.length() == 0) {
            j();
            return;
        }
        com.xiaoyi.camera.g.a(optJSONArray);
        if (TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("video_resolution"))) {
            j();
        } else if ("idle".equals(com.xiaoyi.camera.g.a().a("app_status")) && "off".equals(com.xiaoyi.camera.g.a().a("precise_cont_capturing")) && "off".equals(com.xiaoyi.camera.g.a().a("precise_self_running"))) {
            com.xiaoyi.camera.g.a().a("camera_clock", a(new Date().getTime()), this);
        } else {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        if (this.f != null) {
            if (this.c.b(this.f.a())) {
                this.c.c(this.f);
            }
            this.d.c(this.f.b());
        }
        if (this.o >= 3) {
            this.o = 0;
            Message.obtain(this.h, 4100).sendToTarget();
        } else {
            this.o++;
            if (this.b != null) {
                this.b.c(this.f, j);
            }
        }
    }

    @TargetApi(23)
    private void b(String str) {
        if (this.f4831a == null) {
            return;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager) this.f4831a.getSystemService("connectivity");
        NetworkUtil.clearBindProcess(this.f4831a);
        if (Build.VERSION.SDK_INT >= 23) {
            connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addTransportType(1).removeCapability(12).build(), new ConnectivityManager.NetworkCallback() { // from class: com.xiaoyi.camera.controller.a.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    if (a.this.f4831a != null) {
                        NetworkUtil.init(a.this.f4831a, network);
                    }
                    g.a("debug_wifi", "bind network " + network.toString(), new Object[0]);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    if (a.this.f4831a != null) {
                        NetworkUtil.init(a.this.f4831a, null);
                    }
                    try {
                        connectivityManager.unregisterNetworkCallback(this);
                    } catch (SecurityException e) {
                        g.a("debug_wifi", "Failed to unregister network callback", new Object[0]);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        com.xiaoyi.camera.g.a().a("model", str);
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        g.a("debug_wifi", "initComplete()", new Object[0]);
        com.xiaoyi.camera.g.a(this.f4831a);
        com.xiaoyi.camera.module.b.a();
        com.xiaoyi.camera.g.a().m(null);
        h();
        if (this.b != null) {
            this.b.b(this.f, SystemClock.elapsedRealtime() - this.n);
            a();
        }
        g.a("debug_wifi", "CameraActionConnectHelper : mCamera.getMacAdress() = " + this.f.a(), new Object[0]);
        g.a("debug_wifi", "CameraActionConnectHelper : mCamera.getPassword() = " + this.f.c(), new Object[0]);
        if (this.f == null || TextUtils.isEmpty(this.f.a()) || this.c == null) {
            return;
        }
        try {
            this.f.c(com.xiaoyi.camera.g.a().a("wifi_password"));
            this.c.a(this.f);
            g.a(BuildConfig.BUILD_TYPE, a.class.getSimpleName() + " : mDBHelper.addCamera(mCamera)", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void j() {
        g.a("debug_wifi", "sendGetAllCurrentSetting", new Object[0]);
        com.xiaoyi.camera.g.a().i(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.j) {
            return;
        }
        if (this.m <= 0) {
            this.p += SystemClock.elapsedRealtime() - this.n;
            if (this.b != null) {
                this.b.a(this.f, this.p);
            }
            this.p = 0L;
            return;
        }
        this.p += SystemClock.elapsedRealtime() - this.n;
        g.a("debug_wifi", "receive CONNECTION_TIME_OUT, rest retry times: " + this.m + " spend time: " + (SystemClock.elapsedRealtime() - this.n), new Object[0]);
        this.m--;
        a(this.f);
    }

    public String a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public void a() {
        this.k = true;
        this.j = false;
        if (this.e != null && this.f4831a != null) {
            g.a("debug_wifi", "unregisterReceiver  wifi", new Object[0]);
            try {
                b(false);
            } catch (Exception e) {
                g.a("debug_wifi", "unregisterReceiver  wifi  fail", new Object[0]);
            }
        }
        g();
        g.a("debug_wifi", "close, set listener null", new Object[0]);
    }

    public void a(b bVar) {
        g.a("debug_wifi", "set listener: " + bVar, new Object[0]);
        this.b = bVar;
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(d dVar, JSONObject jSONObject) {
        if (this.h != null) {
            g.a("debug_wifi", "onReceiveMessage: json" + jSONObject.toString(), new Object[0]);
            int optInt = jSONObject.optInt("msg_id");
            if (this.h != null) {
                Message.obtain(this.h, optInt, jSONObject).sendToTarget();
            }
        }
    }

    public void a(com.xiaoyi.camera.devicedao.a aVar) {
        this.k = false;
        b(true);
        this.n = SystemClock.elapsedRealtime();
        if (this.h != null) {
            g.a("debug_wifi", "set CONNECTION_TIME_OUT, retry times: " + this.m, new Object[0]);
            this.h.sendEmptyMessageDelayed(FragmentTransaction.TRANSIT_FRAGMENT_FADE, 7000L);
        }
        this.i = true;
        if (this.b != null) {
            this.b.n_();
        }
        this.f = aVar;
        if (this.f4831a == null || this.f == null) {
            return;
        }
        NetworkInfo networkInfo = ((ConnectivityManager) this.f4831a.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED && e()) {
            g.a("debug_wifi", "wifi " + this.f.b() + " alread connect, start session directly 2", new Object[0]);
            d();
            return;
        }
        this.g = null;
        if (this.d != null) {
            this.g = this.d.a(this.f.b());
            if (this.g == null || this.d.c(this.g)) {
                this.d.b(this.f.b());
                g.a("debug_wifi", "will connect: " + this.f.b() + " | " + this.f.c(), new Object[0]);
                this.g = this.d.a(this.f.b(), this.f.c(), 3);
                if (!this.d.d(this.g)) {
                    Message.obtain(this.h, 4100).sendToTarget();
                    try {
                        this.d.b(this.f.b());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                g.a("debug_wifi", "wifi " + this.f.b() + " alread saved in the system config, just enable", new Object[0]);
                this.d.e(this.g);
            }
            b(this.f.b());
        }
    }

    public void a(String str) {
        this.d.c(str);
    }

    public void a(boolean z) {
        this.d.a(z);
    }

    public boolean a(ScanResult scanResult) {
        return this.c != null && this.c.a(scanResult);
    }

    public com.xiaoyi.camera.devicedao.a b(ScanResult scanResult) {
        return this.c.a(scanResult.BSSID);
    }

    public void b() {
        this.b = null;
        this.e = null;
        this.f4831a = null;
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(d dVar, JSONObject jSONObject) {
        if (jSONObject != null) {
            g.a("debug_wifi", "onReceiveErrorMessage: json" + jSONObject.toString(), new Object[0]);
        }
        if (dVar.a() != 257) {
            if (this.h == null || jSONObject == null) {
                return;
            }
            if (3 == jSONObject.optInt("msg_id")) {
                if (jSONObject.optInt("rval") == -21) {
                    j();
                    return;
                }
                return;
            } else {
                if (2 == jSONObject.optInt("msg_id")) {
                    this.h.sendEmptyMessage(8199);
                    return;
                }
                return;
            }
        }
        if (this.h == null || jSONObject == null) {
            if (this.k) {
                return;
            }
            g.a("debug_wifi", "onReceiveErrorMessage set isStartSession false", new Object[0]);
            this.j = false;
            b(false);
            a(this.f);
            return;
        }
        int optInt = jSONObject.optInt("rval");
        if (optInt == -3) {
            Message.obtain(this.h, 8197, optInt, 0).sendToTarget();
        } else if (this.l <= 0) {
            Message.obtain(this.h, 8197, optInt, 0).sendToTarget();
        } else {
            this.l--;
            com.xiaoyi.camera.g.a().a(this);
        }
    }

    public void b(boolean z) {
        try {
            if (this.f4831a != null) {
                if (z) {
                    IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                    intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                    intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
                    intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                    intentFilter.setPriority(Integer.MAX_VALUE);
                    this.f4831a.registerReceiver(this.e, intentFilter);
                } else {
                    this.f4831a.unregisterReceiver(this.e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public com.xiaoyi.camera.devicedao.a c() {
        if (this.f4831a != null) {
            this.n = SystemClock.elapsedRealtime();
            WifiInfo connectionInfo = ((WifiManager) this.f4831a.getSystemService("wifi")).getConnectionInfo();
            NetworkInfo networkInfo = ((ConnectivityManager) this.f4831a.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED && f.a(connectionInfo)) {
                b(true);
                this.f = new com.xiaoyi.camera.devicedao.a();
                this.f.b(connectionInfo.getSSID().replace("\"", ""));
                this.f.a(connectionInfo.getBSSID());
                b(this.f.b());
                g.a("debug_wifi", "wifi " + this.f.b() + " alread connect, start session directly", new Object[0]);
                g.a("debug_wifi", "wifi " + this.f.a() + " alread connect, start session directly", new Object[0]);
                if (this.h != null) {
                    g.a("debug_wifi", "set CONNECTION_TIME_OUT, retry times: " + this.m, new Object[0]);
                    this.h.sendEmptyMessageDelayed(FragmentTransaction.TRANSIT_FRAGMENT_FADE, 7000L);
                }
                this.i = true;
                g.a("debug_wifi", "connectCameraDirectly() set isStartSession false", new Object[0]);
                this.j = false;
                d();
                return this.f;
            }
        }
        return null;
    }

    public void c(boolean z) {
        this.k = z;
    }

    public void d() {
        if (this.j) {
            return;
        }
        g.a("debug_wifi", "start session(), \\'isStartSession\\' is false", new Object[0]);
        this.j = true;
        g();
        com.xiaoyi.camera.g.a().a(this);
    }

    public boolean e() {
        boolean z;
        if (this.f4831a == null) {
            return false;
        }
        WifiInfo connectionInfo = ((WifiManager) this.f4831a.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null && this.f != null) {
            String bssid = connectionInfo.getBSSID();
            String ssid = connectionInfo.getSSID();
            if (!TextUtils.isEmpty(bssid) && !TextUtils.isEmpty(ssid) && !TextUtils.isEmpty(this.f.a()) && !TextUtils.isEmpty(this.f.b()) && bssid.replace("\"", "").equalsIgnoreCase(this.f.a()) && ssid.replace("\"", "").equalsIgnoreCase(this.f.b())) {
                z = true;
                return z;
            }
        }
        z = false;
        return z;
    }

    public void f() {
        if (!"on".equals(com.xiaoyi.camera.g.a().a("preview_status")) && !"Z16".equals(com.xiaoyi.camera.g.a().a("model")) && !"Z18".equals(com.xiaoyi.camera.g.a().a("model")) && !"J11".equals(com.xiaoyi.camera.g.a().a("model")) && !"J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            i();
        } else {
            com.xiaoyi.camera.g.a().l(this);
            i();
        }
    }

    public void g() {
        g.a("debug_wifi", "Stop CONNECTION_TIME_OUT, rest retry times: " + this.m, new Object[0]);
        if (this.h != null) {
            this.m = 3;
            this.h.removeMessages(16384);
            this.h.removeMessages(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
    }

    public void h() {
        com.xiaoyi.camera.g.a().p();
        com.xiaoyi.camera.g.a().k();
        com.xiaoyi.camera.g.a().g();
        com.xiaoyi.camera.g.a().j();
        if ("Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            com.xiaoyi.camera.g.a().l();
            if (!TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("slow_motion_res"))) {
                com.xiaoyi.camera.g.a().m();
            }
            com.xiaoyi.camera.g.a().n();
            com.xiaoyi.camera.g.a().o();
            com.xiaoyi.camera.g.a().h();
            com.xiaoyi.camera.g.a().i();
        }
    }
}
