package com.yiaction.common.imageloader;

import android.content.Context;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class d implements m<c, InputStream> {
    @Override // com.bumptech.glide.load.b.m
    public l<c, InputStream> a(Context context, com.bumptech.glide.load.b.c cVar) {
        return new h(com.yiaction.common.http.c.a().b());
    }

    @Override // com.bumptech.glide.load.b.m
    public void a() {
    }
}
