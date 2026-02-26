package com.xiaoyi.camera.devicedao;

import com.xiaoyi.camera.g;

/* loaded from: classes3.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private String f4853a;
    private String b;
    private String c;

    public a() {
    }

    public a(String str, String str2, String str3) {
        this.f4853a = str;
        this.b = str2;
        this.c = str3;
    }

    public String a() {
        return this.f4853a;
    }

    public void a(String str) {
        this.f4853a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
        g.a().a("wifi_ssid", str);
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            a aVar = (a) obj;
            return this.f4853a == null ? aVar.f4853a == null : this.f4853a.equals(aVar.f4853a);
        }
        return false;
    }

    public int hashCode() {
        return (this.f4853a == null ? 0 : this.f4853a.hashCode()) + 31;
    }
}
