package com.xiaoyi.camera.module;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes3.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static Handler f4879a;
    private static HandlerThread b;

    public static Handler a() {
        if (f4879a == null) {
            synchronized (d.class) {
                b = new HandlerThread("XY_FILE_RW");
                b.start();
                f4879a = new Handler(b.getLooper());
            }
        }
        return f4879a;
    }

    public static void a(Runnable runnable) {
        a().post(runnable);
    }
}
