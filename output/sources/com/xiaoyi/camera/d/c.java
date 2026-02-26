package com.xiaoyi.camera.d;

import android.content.Context;
import android.net.wifi.ScanResult;
import com.xiaoyi.camera.devicedao.DaoMaster;
import com.xiaoyi.camera.devicedao.DaoSession;
import com.xiaoyi.camera.devicedao.SportsCameraDao;
import java.util.List;

/* loaded from: classes.dex */
public class c {
    private static c c;

    /* renamed from: a, reason: collision with root package name */
    private DaoSession f4844a;
    private SportsCameraDao b;
    private DaoMaster d;

    private c(Context context) {
        b(context);
        this.b = this.f4844a.a();
    }

    public static c a(Context context) {
        if (c == null) {
            c = new c(context);
        }
        return c;
    }

    public com.xiaoyi.camera.devicedao.a a(String str) {
        return this.b.load(str);
    }

    public List<com.xiaoyi.camera.devicedao.a> a() {
        return this.b.loadAll();
    }

    public void a(com.xiaoyi.camera.devicedao.a aVar) {
        List<com.xiaoyi.camera.devicedao.a> a2 = a();
        if (a2 == null || !a2.contains(aVar)) {
            this.b.insertOrReplace(aVar);
        } else {
            this.b.update(aVar);
        }
    }

    public boolean a(ScanResult scanResult) {
        return a(scanResult.SSID, scanResult.BSSID);
    }

    public boolean a(String str, String str2) {
        return this.b.load(str2) != null;
    }

    public DaoSession b(Context context) {
        if (this.f4844a == null) {
            if (this.d == null) {
                this.d = c(context);
            }
            this.f4844a = this.d.newSession();
        }
        return this.f4844a;
    }

    public void b(com.xiaoyi.camera.devicedao.a aVar) {
        this.b.update(aVar);
    }

    public boolean b(String str) {
        return this.b.load(str) != null;
    }

    public DaoMaster c(Context context) {
        if (this.d == null) {
            this.d = new DaoMaster(new DaoMaster.a(context, "device_db", null).getWritableDatabase());
        }
        return this.d;
    }

    public void c(com.xiaoyi.camera.devicedao.a aVar) {
        this.b.delete(aVar);
    }
}
