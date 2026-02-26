package com.yiaction.common.http;

import okhttp3.s;
import okhttp3.w;
import okio.l;
import okio.q;

/* loaded from: classes3.dex */
public class d extends w {

    /* renamed from: a, reason: collision with root package name */
    private final w f4936a;
    private final b b;
    private okio.d c;

    public d(w wVar, b bVar) {
        this.f4936a = wVar;
        this.b = bVar;
    }

    private q a(q qVar) {
        return new okio.g(qVar) { // from class: com.yiaction.common.http.d.1

            /* renamed from: a, reason: collision with root package name */
            long f4937a = 0;
            long b = 0;

            @Override // okio.g, okio.q
            public void a_(okio.c cVar, long j) {
                super.a_(cVar, j);
                if (this.b == 0) {
                    this.b = d.this.b();
                }
                this.f4937a += j;
                d.this.b.a(this.f4937a, this.b, this.f4937a == this.b);
            }
        };
    }

    @Override // okhttp3.w
    public s a() {
        return this.f4936a.a();
    }

    @Override // okhttp3.w
    public void a(okio.d dVar) {
        try {
            if (this.c == null) {
                this.c = l.a(a((q) dVar));
            }
            this.f4936a.a(this.c);
            this.c.flush();
        } catch (Exception e) {
            com.yiaction.common.util.g.a(e.toString(), new Object[0]);
        }
    }

    @Override // okhttp3.w
    public long b() {
        return this.f4936a.b();
    }
}
