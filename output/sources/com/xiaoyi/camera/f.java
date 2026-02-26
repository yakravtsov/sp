package com.xiaoyi.camera;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

/* loaded from: classes3.dex */
public class f extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private File f4860a;
    private com.xiaoyi.camera.c.e b;
    private boolean c = false;

    public f(File file, com.xiaoyi.camera.c.e eVar) {
        this.f4860a = file;
        this.b = eVar;
    }

    public void a() {
        this.c = false;
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        interrupt();
        try {
            join(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int read;
        super.run();
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(c.a().getOutputStream());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.f4860a));
            long length = this.f4860a.length();
            byte[] bArr = new byte[512];
            this.c = true;
            int i = 0;
            int i2 = 0;
            while (this.c && (read = bufferedInputStream.read(bArr)) != -1 && i2 <= length) {
                bufferedOutputStream.write(bArr, 0, read);
                bufferedOutputStream.flush();
                int i3 = i2 + read;
                com.yiaction.common.util.g.a("debug_upgrade", "written: " + i3, new Object[0]);
                this.b.a(i3);
                i2 = i3;
                i = read;
            }
            com.yiaction.common.util.g.a("debug_upgrade", "written = " + i2 + " last packege length: " + i, new Object[0]);
            bufferedOutputStream.flush();
            bufferedInputStream.close();
        } catch (Exception e) {
            com.yiaction.common.util.g.a("debug_upgrade", "Exception: " + e.getMessage(), new Object[0]);
            this.c = false;
            this.b.a();
            e.printStackTrace();
        }
    }
}
