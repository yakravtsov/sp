package com.yiaction.common.imageloader;

import com.bumptech.glide.load.b.l;
import java.io.InputStream;
import okhttp3.t;

/* loaded from: classes3.dex */
public class h implements l<c, InputStream> {

    /* renamed from: a, reason: collision with root package name */
    private final t f4953a;

    public h(t tVar) {
        this.f4953a = tVar;
    }

    @Override // com.bumptech.glide.load.b.l
    public com.bumptech.glide.load.a.c<InputStream> a(c cVar, int i, int i2) {
        return new g(this.f4953a, cVar);
    }
}
