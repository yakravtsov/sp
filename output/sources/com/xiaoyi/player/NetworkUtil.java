package com.xiaoyi.player;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.util.Log;
import com.yiaction.common.http.a;
import com.yiaction.common.imageloader.i;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;

/* loaded from: classes3.dex */
public class NetworkUtil {
    private static int netId;
    private static Network network;

    @TargetApi(23)
    public static void bindProcess(Context context) {
        if (context == null || network == null || Build.VERSION.SDK_INT < 23) {
            return;
        }
        Log.d("NetworkUtil", " start bindprocess");
        ((ConnectivityManager) context.getSystemService("connectivity")).bindProcessToNetwork(network);
    }

    @TargetApi(23)
    public static void bindSocket(Socket socket) {
        Log.d("NetworkUtil", " start bindSocket");
        if (network == null || Build.VERSION.SDK_INT < 23) {
            return;
        }
        try {
            network.bindSocket(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("NetworkUtil", " bindSocket success: network" + network + "+socket" + socket);
    }

    @TargetApi(23)
    public static void bindSocketToNetwork(int i) {
        Log.d("NetworkUtil", " start bindSocketToNetwork");
        if (network == null || Build.VERSION.SDK_INT < 23) {
            return;
        }
        FileDescriptor fileDescriptor = new FileDescriptor();
        try {
            Field declaredField = FileDescriptor.class.getDeclaredField("descriptor");
            declaredField.setAccessible(true);
            declaredField.setInt(fileDescriptor, i);
            network.bindSocket(fileDescriptor);
            Log.d("NetworkUtil", " bindSocketToNetwork success: network" + network + "+socketfd" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(23)
    public static void clearBindProcess(Context context) {
        if (context == null || Build.VERSION.SDK_INT < 23) {
            return;
        }
        Log.d("NetworkUtil", " start clearprocess");
        ((ConnectivityManager) context.getSystemService("connectivity")).bindProcessToNetwork(null);
    }

    public static Network getNetwork() {
        return network;
    }

    public static void init(int i) {
        netId = i;
    }

    public static void init(Context context, Network network2) {
        Log.d("NetworkUtil", "init network" + network2);
        network = network2;
        a.a().a(network2);
        i.a(context);
    }
}
