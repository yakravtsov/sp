package com.yiaction.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.ants360.z13.club.ClubModel;
import com.google.android.gms.common.Scopes;

/* loaded from: classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static i f4963a;
    private SharedPreferences b;

    private i() {
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (f4963a == null) {
                f4963a = new i();
            }
            iVar = f4963a;
        }
        return iVar;
    }

    public String a(String str) {
        return this.b.getString(str, "");
    }

    public void a(Context context) {
        if (this.b == null) {
            this.b = context.getSharedPreferences(Scopes.PROFILE, 0);
        }
    }

    public void a(String str, int i) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void a(String str, long j) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void a(String str, boolean z) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public void a(boolean z) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putBoolean("isChinaUser", z);
        edit.commit();
    }

    public int b(String str, int i) {
        return this.b.getInt(str, i);
    }

    public long b(String str) {
        return this.b.getLong(str, -1L);
    }

    public Boolean b() {
        return Boolean.valueOf(this.b.getBoolean("isChinaUser", false));
    }

    public void b(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("video_sticker_items_" + str, str2);
        edit.commit();
    }

    public boolean b(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public int c(String str) {
        return this.b.getInt(str, 0);
    }

    public String c() {
        return this.b.getString("recommend.json", "");
    }

    public void c(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("pic_sticker_items_" + str, str2);
        edit.commit();
    }

    public String d() {
        return this.b.getString("discoverhead.hson", "");
    }

    public void d(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("video_music_items_" + str, str2);
        edit.commit();
    }

    public boolean d(String str) {
        return this.b.getBoolean(str, false);
    }

    public String e() {
        return this.b.getString("discoverbody.json", "");
    }

    public boolean e(String str) {
        return this.b.contains(str);
    }

    public String f() {
        return this.b.getString("current_version", ClubModel.beMember);
    }

    public void f(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.commit();
    }

    public void g(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("video_sticker_classify", str);
        edit.commit();
    }

    public void h(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("pic_sticker_classify", str);
        edit.commit();
    }

    public String i(String str) {
        return this.b.getString(str, "");
    }

    public String j(String str) {
        return this.b.getString(str, "");
    }

    public String k(String str) {
        return this.b.getString(str, "");
    }

    public String l(String str) {
        return this.b.getString(str, "");
    }

    public void m(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("video_music_classify", str);
        edit.commit();
    }

    public String n(String str) {
        return this.b.getString(str, "");
    }

    public String o(String str) {
        return this.b.getString(str, "");
    }

    public void p(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("recommend.json", str);
        edit.commit();
    }

    public void q(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("discoverhead.hson", str);
        edit.commit();
    }

    public void r(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("discoverbody.json", str);
        edit.commit();
    }

    public void s(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("current_version", str);
        edit.commit();
    }
}
