package com.xiaoyi.camera.d;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;
import com.ants360.z13.club.ClubModel;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private WifiManager f4846a;
    private boolean b = false;

    public f(Context context) {
        this.f4846a = (WifiManager) context.getSystemService("wifi");
    }

    private void a(int i) {
        com.yiaction.common.util.g.a("debug_wifi", "disconnect network, result: " + Boolean.valueOf(this.f4846a.disconnect()), new Object[0]);
    }

    public static boolean a(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        return a(scanResult.SSID, scanResult.BSSID);
    }

    public static boolean a(WifiInfo wifiInfo) {
        String ssid;
        if (wifiInfo == null || (ssid = wifiInfo.getSSID()) == null) {
            return false;
        }
        return a(ssid.replace("\"", ""), wifiInfo.getBSSID());
    }

    private static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str.startsWith("Z13_") || str.startsWith("SportsCamera_") || str.startsWith("YDXJ_") || str2.toUpperCase(Locale.getDefault()).startsWith("04:E6:76") || str2.toUpperCase(Locale.getDefault()).startsWith("58:70:C6");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        com.yiaction.common.util.g.a("debug_wifi", "disableAndRemoveWifi: " + str, new Object[0]);
        WifiConfiguration a2 = a(str);
        if (a2 != null) {
            a(a2.networkId);
            b(str);
            c();
        }
    }

    private boolean f(final WifiConfiguration wifiConfiguration) {
        try {
            Class<?> cls = Class.forName("android.net.wifi.WifiManager$ActionListener");
            Method declaredMethod = this.f4846a.getClass().getDeclaredMethod("connect", Integer.TYPE, cls);
            if (declaredMethod != null) {
                declaredMethod.invoke(this.f4846a, Integer.valueOf(wifiConfiguration.networkId), Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.xiaoyi.camera.d.f.2
                    @Override // java.lang.reflect.InvocationHandler
                    public Object invoke(Object obj, Method method, Object[] objArr) {
                        com.yiaction.common.util.g.a("debug_wifi", "connectWifiByReflectMethod  ActionListener : method.getName() = " + method.getName(), new Object[0]);
                        if (objArr != null && objArr.length > 0) {
                            for (Object obj2 : objArr) {
                                com.yiaction.common.util.g.a("debug_wifi", "addAndConnectWifiByReflectMethod : " + obj2, new Object[0]);
                            }
                        }
                        if ("onSuccess".equals(method.getName())) {
                            return ClubModel.beCharge;
                        }
                        f.this.b(wifiConfiguration);
                        return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                    }
                }));
                return true;
            }
        } catch (Exception e) {
            Log.d("debug_wifi", "connectWifiByReflectMethod : Exception = " + e);
        }
        return false;
    }

    private boolean g(final WifiConfiguration wifiConfiguration) {
        try {
            Class<?> cls = Class.forName("android.net.wifi.WifiManager$ActionListener");
            Method declaredMethod = this.f4846a.getClass().getDeclaredMethod("connect", WifiConfiguration.class, cls);
            if (declaredMethod != null) {
                declaredMethod.invoke(this.f4846a, wifiConfiguration, Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.xiaoyi.camera.d.f.3
                    @Override // java.lang.reflect.InvocationHandler
                    public Object invoke(Object obj, Method method, Object[] objArr) {
                        com.yiaction.common.util.g.a("debug_wifi", "addAndConnectWifiByReflectMethod  ActionListener : method.getName() = " + method.getName(), new Object[0]);
                        if (objArr != null && objArr.length > 0) {
                            for (Object obj2 : objArr) {
                                com.yiaction.common.util.g.a("debug_wifi", "addAndConnectWifiByReflectMethod : " + obj2, new Object[0]);
                            }
                        }
                        if ("onSuccess".equals(method.getName())) {
                            return ClubModel.beCharge;
                        }
                        f.this.a(wifiConfiguration);
                        return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                    }
                }));
                return true;
            }
        } catch (Exception e) {
            Log.d("debug_wifi", "addAndConnectWifiByReflectMethod : Exception = " + e);
        }
        return false;
    }

    private boolean i() {
        boolean reconnect = this.f4846a.reconnect();
        com.yiaction.common.util.g.a("debug_wifi", "reconnect: " + reconnect, new Object[0]);
        return reconnect;
    }

    public WifiConfiguration a(String str) {
        List<WifiConfiguration> configuredNetworks = this.f4846a.getConfiguredNetworks();
        if (configuredNetworks == null || configuredNetworks.isEmpty()) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (!TextUtils.isEmpty(wifiConfiguration.SSID) && wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    public WifiConfiguration a(String str, String str2, int i) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        if (i == 1) {
            wifiConfiguration.wepKeys[0] = "";
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        } else if (i == 2) {
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.wepKeys[0] = "\"" + str2 + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        } else if (i == 3) {
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        }
        return wifiConfiguration;
    }

    public void a() {
        com.yiaction.common.util.g.a("debug_wifi", "open wifi", new Object[0]);
        try {
            if (this.f4846a.isWifiEnabled()) {
                return;
            }
            this.f4846a.setWifiEnabled(true);
        } catch (ActivityNotFoundException e) {
        }
    }

    public void a(boolean z) {
        this.b = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    public boolean a(WifiConfiguration wifiConfiguration) {
        int i = 0;
        i = 0;
        com.yiaction.common.util.g.a("debug_wifi", "addAndEnableNetwork " + wifiConfiguration.SSID, new Object[0]);
        try {
            int addNetwork = this.f4846a.addNetwork(wifiConfiguration);
            com.yiaction.common.util.g.a("debug_wifi", "WifiManager add network ： wcgID ＝ " + addNetwork, new Object[0]);
            if (addNetwork >= 0) {
                this.f4846a.enableNetwork(addNetwork, true);
                c();
                i = i();
            } else {
                com.yiaction.common.util.g.a("debug_wifi", "WifiManager add network " + wifiConfiguration.SSID + " failed", new Object[0]);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            com.yiaction.common.util.g.a("debug_wifi", "WifiManager add network " + wifiConfiguration.SSID + " IllegalArgumentException", new Object[i]);
        }
        return i;
    }

    public void b(String str) {
        WifiConfiguration a2 = a(str);
        if (a2 == null) {
            a2 = a("\"" + str + "\"");
        }
        com.yiaction.common.util.g.a("debug_wifi", "will removeWifi ssid " + str, new Object[0]);
        if (a2 != null) {
            com.yiaction.common.util.g.a("debug_wifi", "removeWifi ssid " + str + " result: " + Boolean.valueOf(this.f4846a.removeNetwork(a2.networkId)), new Object[0]);
            c();
        }
    }

    public boolean b() {
        return this.f4846a.isWifiEnabled();
    }

    public boolean b(WifiConfiguration wifiConfiguration) {
        com.yiaction.common.util.g.a("debug_wifi", "enableNetwork " + wifiConfiguration.SSID, new Object[0]);
        this.f4846a.enableNetwork(wifiConfiguration.networkId, true);
        c();
        return i();
    }

    public void c() {
        this.f4846a.saveConfiguration();
    }

    public void c(final String str) {
        com.yiaction.common.util.g.a("debug_wifi", "forget: " + str, new Object[0]);
        WifiConfiguration a2 = a(str);
        if (a2 == null) {
            a2 = a("\"" + str + "\"");
        }
        if (a2 != null) {
            try {
                Class<?> cls = Class.forName("android.net.wifi.WifiManager$ActionListener");
                Method declaredMethod = this.f4846a.getClass().getDeclaredMethod("forget", Integer.TYPE, cls);
                if (declaredMethod != null) {
                    declaredMethod.invoke(this.f4846a, Integer.valueOf(a2.networkId), Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.xiaoyi.camera.d.f.1
                        @Override // java.lang.reflect.InvocationHandler
                        public Object invoke(Object obj, Method method, Object[] objArr) {
                            com.yiaction.common.util.g.a("debug_wifi", "forget psw , ActionListener : method.getName() = " + method.getName(), new Object[0]);
                            if ("onSuccess".equals(method.getName())) {
                                f.this.c();
                                return ClubModel.beCharge;
                            }
                            f.this.d(str);
                            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                        }
                    }));
                }
            } catch (Exception e) {
                Log.d("debug_wifi", "forget psw : Exception = " + e);
                d(str);
            }
        }
    }

    public boolean c(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return false;
        }
        try {
            Field declaredField = wifiConfiguration.getClass().getDeclaredField("disableReason");
            com.yiaction.common.util.g.a("debug_wifi", "isAuthenticateFailed : field = " + declaredField.getName(), new Object[0]);
            declaredField.setAccessible(true);
            return declaredField.getInt(wifiConfiguration) == 3;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public void d() {
        e();
        ScanResult scanResult = null;
        List<WifiConfiguration> configuredNetworks = this.f4846a.getConfiguredNetworks();
        List<ScanResult> scanResults = this.f4846a.getScanResults();
        if (configuredNetworks == null || scanResults == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            String str = wifiConfiguration.SSID;
            if (!TextUtils.isEmpty(str)) {
                com.yiaction.common.util.g.a("debug_wifi", "WifiAdmin conntectToEnableWifi : config.SSID = " + str, new Object[0]);
                String substring = str.substring(1, str.length() - 1);
                com.yiaction.common.util.g.a("debug_wifi", "WifiAdmin conntectToEnableWifi : key = " + substring, new Object[0]);
                hashMap.put(substring, wifiConfiguration);
            }
        }
        for (ScanResult scanResult2 : scanResults) {
            com.yiaction.common.util.g.a("debug_wifi", "WifiAdmin conntectToEnableWifi : wifi.SSID = " + scanResult2.SSID, new Object[0]);
            if (!a(scanResult2)) {
                if (!hashMap.containsKey(scanResult2.SSID) || (scanResult != null && scanResult2.level <= scanResult.level)) {
                    scanResult2 = scanResult;
                }
                scanResult = scanResult2;
            }
        }
        if (scanResult != null) {
            e((WifiConfiguration) hashMap.get(scanResult.SSID));
        }
    }

    public boolean d(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return false;
        }
        com.yiaction.common.util.g.a("debug_wifi", "addAndConnectWifi " + wifiConfiguration.SSID, new Object[0]);
        if (!this.b) {
            return a(wifiConfiguration);
        }
        if (g(wifiConfiguration)) {
            return true;
        }
        com.yiaction.common.util.g.a("debug_wifi", "connect wifi by addAndEnableNetwork method", new Object[0]);
        return a(wifiConfiguration);
    }

    public void e() {
        com.yiaction.common.util.g.a("debug_wifi", "start scan wifi", new Object[0]);
        this.f4846a.startScan();
    }

    public void e(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return;
        }
        com.yiaction.common.util.g.a("debug_wifi", "connectWifi " + wifiConfiguration.SSID, new Object[0]);
        if (!this.b) {
            b(wifiConfiguration);
        } else {
            if (f(wifiConfiguration)) {
                return;
            }
            com.yiaction.common.util.g.a("debug_wifi", "connect wifi by enableNetwork method", new Object[0]);
            b(wifiConfiguration);
        }
    }

    public List<ScanResult> f() {
        return this.f4846a.getScanResults();
    }

    public String g() {
        WifiInfo connectionInfo = this.f4846a.getConnectionInfo();
        return connectionInfo == null ? "NULL" : connectionInfo.getSSID();
    }

    public String h() {
        WifiInfo connectionInfo = this.f4846a.getConnectionInfo();
        return connectionInfo == null ? "NULL" : connectionInfo.getBSSID();
    }
}
