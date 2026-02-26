package com.xiaoyi.camera;

import android.util.Log;
import com.google.android.exoplayer.ExoPlayer;
import com.xiaoyi.player.NetworkUtil;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/* loaded from: classes3.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static Socket f4813a;
    private static BufferedInputStream b;

    public static synchronized Socket a() {
        Socket socket;
        synchronized (c.class) {
            if (f4813a == null || f4813a.isClosed()) {
                f4813a = new Socket();
                NetworkUtil.bindSocket(f4813a);
                f4813a.connect(new InetSocketAddress(InetAddress.getByName("192.168.42.1"), 8787), ExoPlayer.Factory.DEFAULT_MIN_REBUFFER_MS);
                f4813a.setSoTimeout(ExoPlayer.Factory.DEFAULT_MIN_REBUFFER_MS);
                f4813a.setReuseAddress(true);
                if (f4813a.getReceiveBufferSize() < 65536) {
                    f4813a.setReceiveBufferSize(65536);
                }
            }
            socket = f4813a;
        }
        return socket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b() {
        if (b != null) {
            try {
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            b = null;
        }
        if (f4813a != null) {
            try {
                f4813a.close();
                Log.d("z13Test", "data socket close");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            f4813a = null;
        }
    }
}
