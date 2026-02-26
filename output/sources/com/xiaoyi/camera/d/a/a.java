package com.xiaoyi.camera.d.a;

import com.yiaction.common.util.g;
import java.io.File;
import java.net.URL;

/* loaded from: classes3.dex */
public class a extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private File f4840a;
    private URL b;
    private int c;
    private int d;
    private int e;
    private boolean f = false;
    private b g;
    private String h;

    public a(b bVar, URL url, File file, int i, int i2, int i3, String str) {
        this.d = -1;
        this.b = url;
        this.f4840a = file;
        this.c = i;
        this.g = bVar;
        this.d = i3;
        this.e = i2;
        this.h = str;
    }

    private static void a(String str) {
        g.a("debug_upgrade", str, new Object[0]);
    }

    public boolean a() {
        return this.f;
    }

    public long b() {
        return this.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x01a0 A[Catch: Exception -> 0x01e2, TryCatch #8 {Exception -> 0x01e2, blocks: (B:56:0x019b, B:50:0x01a0, B:51:0x01a3), top: B:55:0x019b }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x019b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 505
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaoyi.camera.d.a.a.run():void");
    }
}
