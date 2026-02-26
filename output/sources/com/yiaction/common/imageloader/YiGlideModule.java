package com.yiaction.common.imageloader;

import android.content.Context;
import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;
import com.yiaction.common.util.Constant;

/* loaded from: classes3.dex */
public class YiGlideModule implements com.bumptech.glide.d.a {
    @Override // com.bumptech.glide.d.a
    public void a(Context context, com.bumptech.glide.g gVar) {
    }

    @Override // com.bumptech.glide.d.a
    public void a(Context context, com.bumptech.glide.h hVar) {
        hVar.a(new com.bumptech.glide.load.engine.b.f(context, Constant.u, 262144000));
        com.yiaction.common.util.g.a("yi glide module set disk cache", new Object[0]);
        hVar.a(new FifoPriorityThreadPoolExecutor(5));
        com.yiaction.common.util.g.a("yi glide module set concurrency number", new Object[0]);
    }
}
