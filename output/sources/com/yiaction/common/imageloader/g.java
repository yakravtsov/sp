package com.yiaction.common.imageloader;

import com.bumptech.glide.Priority;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import okhttp3.t;
import okhttp3.v;
import okhttp3.x;
import okhttp3.y;

/* loaded from: classes3.dex */
public class g implements com.bumptech.glide.load.a.c<InputStream> {

    /* renamed from: a, reason: collision with root package name */
    private final t f4952a;
    private final c b;
    private InputStream c;
    private y d;
    private int e = 0;

    public g(t tVar, c cVar) {
        this.f4952a = tVar;
        this.b = cVar;
    }

    private y c(Priority priority) {
        v.a a2 = new v.a().a(this.b.b());
        for (Map.Entry<String, String> entry : this.b.c().entrySet()) {
            a2.b(entry.getKey(), entry.getValue());
        }
        x a3 = this.f4952a.a(a2.b()).a();
        y g = a3.g();
        if (a3.c()) {
            return g;
        }
        throw new IOException("Request failed with code: " + a3.b());
    }

    @Override // com.bumptech.glide.load.a.c
    public void a() {
        if (this.c != null) {
            try {
                this.c.close();
            } catch (IOException e) {
            }
        }
        if (this.d != null) {
            try {
                this.d.close();
            } catch (Exception e2) {
            }
        }
    }

    @Override // com.bumptech.glide.load.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public InputStream a(Priority priority) {
        y yVar = null;
        do {
            try {
                yVar = c(priority);
            } catch (Exception e) {
                this.e++;
                if (this.e >= 3) {
                    throw new Exception("load image exceed max retry times");
                }
            }
        } while (yVar == null);
        this.c = com.bumptech.glide.g.b.a(yVar.d(), yVar.b());
        return this.c;
    }

    @Override // com.bumptech.glide.load.a.c
    public String b() {
        return this.b.d();
    }

    @Override // com.bumptech.glide.load.a.c
    public void c() {
    }
}
