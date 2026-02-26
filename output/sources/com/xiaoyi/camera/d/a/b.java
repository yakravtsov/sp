package com.xiaoyi.camera.d.a;

import android.content.Context;
import android.util.Log;
import com.xiaoyi.camera.d.d;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private Context f4841a;
    private d b;
    private boolean c;
    private int d;
    private int e;
    private a[] f;
    private File g;
    private Map<Integer, Integer> h = new ConcurrentHashMap();
    private int i;
    private String j;
    private String k;

    public b(Context context, String str, File file, String str2, int i, long j, String str3) {
        this.d = 0;
        this.e = 0;
        try {
            this.f4841a = context;
            this.j = str;
            this.k = str3;
            this.b = d.a(this.f4841a);
            new URL(this.j);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.f = new a[i];
            this.e = (int) j;
            this.g = new File(file, str2);
            if (this.g.exists()) {
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------- 文件存在", new Object[0]);
                Map<Integer, Integer> a2 = this.b.a(str);
                if (a2.size() > 0) {
                    for (Map.Entry<Integer, Integer> entry : a2.entrySet()) {
                        this.h.put(entry.getKey(), entry.getValue());
                    }
                }
                if (this.h.size() == this.f.length) {
                    for (int i2 = 0; i2 < this.f.length; i2++) {
                        this.d = this.h.get(Integer.valueOf(i2 + 1)).intValue() + this.d;
                    }
                    a("已经下载的长度" + this.d);
                }
            } else {
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------- 文件不存在", new Object[0]);
                this.d = 0;
            }
            if (this.d != this.g.length()) {
                a("已经下载的长度" + this.d + ",真实文件大小：" + this.g.length());
            }
            this.i = this.e % this.f.length == 0 ? this.e / this.f.length : (this.e / this.f.length) + 1;
        } catch (Exception e) {
            a(e.toString());
            throw new RuntimeException("don't connection this url" + str);
        }
    }

    private static void a(String str) {
        Log.i("FileDownloader", str);
    }

    public int a(Map<String, com.xiaoyi.camera.c.d> map) {
        boolean z;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.g, "rw");
            if (this.e > 0) {
                randomAccessFile.setLength(this.e);
            }
            randomAccessFile.close();
            URL url = new URL(this.j);
            g.a("Download url: " + url.toURI().toString(), new Object[0]);
            if (this.h.size() != this.f.length) {
                this.h.clear();
                for (int i = 0; i < this.f.length; i++) {
                    this.h.put(Integer.valueOf(i + 1), 0);
                }
                this.d = 0;
            }
            for (int i2 = 0; i2 < this.f.length; i2++) {
                if (this.h.get(Integer.valueOf(i2 + 1)).intValue() >= this.i || this.d >= this.e) {
                    this.f[i2] = null;
                } else {
                    this.f[i2] = new a(this, url, this.g, this.i, this.h.get(Integer.valueOf(i2 + 1)).intValue(), i2 + 1, this.k);
                    this.f[i2].setPriority(7);
                    this.f[i2].start();
                }
            }
            this.b.b(this.j);
            this.b.a(this.j, this.h);
            boolean z2 = true;
            while (z2) {
                Thread.sleep(900L);
                int i3 = 0;
                z2 = false;
                while (i3 < this.f.length) {
                    if (this.f[i3] == null || this.f[i3].a()) {
                        z = z2;
                    } else if (this.f[i3].b() == -1) {
                        this.f[i3] = new a(this, url, this.g, this.i, this.h.get(Integer.valueOf(i3 + 1)).intValue(), i3 + 1, this.k);
                        this.f[i3].setPriority(7);
                        this.f[i3].start();
                        z = true;
                    } else {
                        z = this.d != this.e;
                    }
                    i3++;
                    z2 = z;
                }
                com.xiaoyi.camera.c.d dVar = map.get(this.k);
                if (dVar != null) {
                    dVar.a(this.d);
                    g.a("debug_upgrade", "downloadSize = " + this.d, new Object[0]);
                }
                if (b()) {
                    z2 = false;
                }
            }
            if (this.d == this.e) {
                this.b.b(this.j);
                g.a("debug_upgrade", "downloaded downloadSize= " + this.d, new Object[0]);
                com.xiaoyi.camera.c.d dVar2 = map.get(this.k);
                if (dVar2 != null) {
                    i.a().a("FM_IS_DOWNLOAD_FIRMWARE" + this.k, false);
                    dVar2.b();
                }
            }
        } catch (Exception e) {
            a(e.toString());
            g.a("debug_upgrade", "file download error: " + e.getMessage(), new Object[0]);
        }
        return this.d;
    }

    public void a() {
        this.c = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void a(int i) {
        this.d += i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void a(int i, int i2) {
        this.h.put(Integer.valueOf(i), Integer.valueOf(i2));
        this.b.a(this.j, i, i2);
    }

    public boolean b() {
        return this.c;
    }
}
