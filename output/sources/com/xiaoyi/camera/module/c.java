package com.xiaoyi.camera.module;

import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    static c f4878a;
    private WeakReference<a> b;

    /* loaded from: classes3.dex */
    public interface a {
    }

    public static c a() {
        if (f4878a != null) {
            return f4878a;
        }
        f4878a = new c();
        return f4878a;
    }

    public void a(a aVar) {
        this.b = new WeakReference<>(aVar);
    }
}
