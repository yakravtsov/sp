package com.xiaoyi.camera.d;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static g f4850a;
    private SharedPreferences b;

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (f4850a == null) {
                f4850a = new g();
            }
            gVar = f4850a;
        }
        return gVar;
    }

    public String a(String str) {
        return this.b.getString(str, null);
    }

    public void a(Context context) {
        if (this.b == null) {
            this.b = context.getSharedPreferences("wifi", 0);
        }
    }

    public void a(String str, int i) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putInt("home_init_wifi" + str, i);
        edit.commit();
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(str, str2);
        edit.commit();
    }
}
