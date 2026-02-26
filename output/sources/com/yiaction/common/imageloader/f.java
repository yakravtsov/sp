package com.yiaction.common.imageloader;

import com.bumptech.glide.load.b.l;
import java.io.InputStream;
import okhttp3.t;

/* loaded from: classes3.dex */
public class f implements l<b, InputStream> {

    /* renamed from: a, reason: collision with root package name */
    private final t f4951a;

    public f(t tVar) {
        this.f4951a = tVar;
    }

    @Override // com.bumptech.glide.load.b.l
    public com.bumptech.glide.load.a.c<InputStream> a(b bVar, int i, int i2) {
        return new e(this.f4951a, bVar);
    }
}
