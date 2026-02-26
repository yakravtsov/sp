package com.yiaction.common.imageloader;

import android.content.Context;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class a implements m<b, InputStream> {
    @Override // com.bumptech.glide.load.b.m
    public l<b, InputStream> a(Context context, com.bumptech.glide.load.b.c cVar) {
        return new f(com.yiaction.common.http.a.a().f4930a);
    }

    @Override // com.bumptech.glide.load.b.m
    public void a() {
    }
}
