package com.yiaction.common.http;

import okhttp3.s;
import okhttp3.y;
import okio.l;
import okio.r;

/* loaded from: classes3.dex */
public class e extends y {

    /* renamed from: a, reason: collision with root package name */
    private final y f4938a;
    private final b b;
    private okio.e c;

    public e(y yVar, b bVar) {
        this.f4938a = yVar;
        this.b = bVar;
    }

    private r a(r rVar) {
        return new okio.h(rVar) { // from class: com.yiaction.common.http.e.1

            /* renamed from: a, reason: collision with root package name */
            long f4939a = 0;

            @Override // okio.h, okio.r
            public long a(okio.c cVar, long j) {
                long a2 = super.a(cVar, j);
                this.f4939a = (a2 != -1 ? a2 : 0L) + this.f4939a;
                e.this.b.a(this.f4939a, e.this.f4938a.b(), a2 == -1);
                return a2;
            }
        };
    }

    @Override // okhttp3.y
    public s a() {
        return this.f4938a.a();
    }

    @Override // okhttp3.y
    public long b() {
        return this.f4938a.b();
    }

    @Override // okhttp3.y
    public okio.e c() {
        if (this.c == null) {
            this.c = l.a(a(this.f4938a.c()));
        }
        return this.c;
    }
}
