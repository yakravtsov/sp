package com.xiaoyi.camera.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.sina.weibo.sdk.statistic.StatisticConfig;
import com.xiaoyi.camera.d.c;
import com.xiaoyi.camera.d.f;
import com.yiaction.common.util.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes3.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private Context f4835a;
    private InterfaceC0208b b;
    private c c;
    private f d;
    private boolean f;
    private ArrayList<ScanResult> g;
    private boolean h = false;
    private long i = 0;
    private final Runnable j = new Runnable() { // from class: com.xiaoyi.camera.controller.b.2
        @Override // java.lang.Runnable
        public void run() {
            if (b.this.f) {
                return;
            }
            List<ScanResult> f = b.this.d.f();
            g.a("debug_scan", "get scan result: " + (f == null ? 0 : f.size()), new Object[0]);
            if (f != null && !f.isEmpty()) {
                b.this.a(f);
                if (b.this.g != null && !b.this.g.isEmpty() && b.this.b != null) {
                    if (b.this.e != null) {
                        b.this.e.removeMessages(1);
                    }
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (b.this.b != null) {
                        b.this.b.a(elapsedRealtime - b.this.i, b.this.g.size());
                    }
                    if (b.this.g.size() > 0) {
                        b.this.h = true;
                        b.this.b.a(b.this.g);
                    }
                }
            }
            b.this.e.sendEmptyMessage(12290);
        }
    };
    private a e = new a(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    b.this.a(SystemClock.elapsedRealtime() - b.this.i);
                    return;
                case 12290:
                    if (b.this.d != null) {
                        b.this.d.e();
                    }
                    b.this.c();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.xiaoyi.camera.controller.b$b, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public interface InterfaceC0208b {
        void a(long j);

        void a(long j, int i);

        void a(List<ScanResult> list);
    }

    public b(Context context) {
        this.f4835a = context.getApplicationContext();
        this.c = c.a(this.f4835a);
        this.d = new f(this.f4835a);
        if (!this.d.b()) {
            this.d.a();
        }
        this.g = new ArrayList<>(15);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        this.f = true;
        d();
        g.a("debug_wifi", "listener = " + this.b, new Object[0]);
        if (this.b != null) {
            this.b.a(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<ScanResult> list) {
        if (this.g != null) {
            this.g.clear();
        }
        Collections.sort(list, new Comparator<ScanResult>() { // from class: com.xiaoyi.camera.controller.b.1
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(ScanResult scanResult, ScanResult scanResult2) {
                return Math.abs(scanResult.level) - Math.abs(scanResult2.level);
            }
        });
        g.a("debug_scan", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", new Object[0]);
        for (ScanResult scanResult : list) {
            if (f.a(scanResult)) {
                if (this.g != null && !this.g.contains(scanResult)) {
                    if (this.c == null || !this.c.a(scanResult)) {
                        this.g.add(scanResult);
                    } else {
                        this.g.add(0, scanResult);
                    }
                }
                g.a("debug_scan", "ssid: " + scanResult.SSID + "\t\t***", new Object[0]);
            } else {
                g.a("debug_scan", "ssid: " + scanResult.SSID, new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.e != null) {
            this.e.postDelayed(this.j, this.h ? 3000L : 1000L);
        }
    }

    private void d() {
        g.a("debug_wifi", "Stop scan camera.", new Object[0]);
        if (this.e != null) {
            this.e.removeMessages(1);
            this.e.removeCallbacks(this.j);
            this.e.removeMessages(12290);
            this.f = true;
        }
    }

    public void a() {
        d();
        this.e.removeCallbacksAndMessages(null);
    }

    public void a(InterfaceC0208b interfaceC0208b) {
        g.a("debug_wifi", "set listener: " + interfaceC0208b, new Object[0]);
        this.b = interfaceC0208b;
    }

    public void b() {
        g.a("debug_wifi", "Start scan camera.", new Object[0]);
        this.h = false;
        this.f = false;
        if (this.e != null) {
            this.e.sendEmptyMessageDelayed(1, StatisticConfig.MIN_UPLOAD_INTERVAL);
        }
        this.i = SystemClock.elapsedRealtime();
        this.d.e();
        c();
    }
}
