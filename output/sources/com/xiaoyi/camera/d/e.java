package com.xiaoyi.camera.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class e {
    private static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static boolean a(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0)) == null || networkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED) {
            return false;
        }
        return networkInfo.isConnected();
    }

    public static boolean b(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1)) == null || !networkInfo.getDetailedState().equals(NetworkInfo.DetailedState.CONNECTED)) {
            return false;
        }
        return networkInfo.isConnected();
    }

    public static boolean c(Context context) {
        return f.a(((WifiManager) context.getSystemService("wifi")).getConnectionInfo());
    }

    public static boolean d(Context context) {
        return b(context) && !c(context);
    }

    public static boolean e(Context context) {
        return d(context) || a(context);
    }

    public static boolean f(Context context) {
        return e(context);
    }

    public static String g(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        return a(wifiManager.getConnectionInfo().getIpAddress());
    }

    public static boolean h(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        try {
            Method declaredMethod = wifiManager.getClass().getDeclaredMethod("isWifiApEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(wifiManager, new Object[0])).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static String i(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            return ((WifiConfiguration) wifiManager.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(wifiManager, new Object[0])).SSID;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
