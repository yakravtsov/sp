package com.yiaction.common.util;

import android.os.Handler;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public abstract class m<T> extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<T> f4964a;

    public m(T t) {
        this.f4964a = new WeakReference<>(t);
    }

    public T a() {
        return this.f4964a.get();
    }
}
