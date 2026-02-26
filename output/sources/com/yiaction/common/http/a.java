package com.yiaction.common.http;

import android.net.Network;
import android.os.Build;
import com.google.common.net.HttpHeaders;
import com.yiaction.common.http.f;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.i;
import okhttp3.t;
import okhttp3.v;
import org.apache.commons.cli.HelpFormatter;

/* loaded from: classes3.dex */
public class a extends f {
    private static a e;

    /* renamed from: a, reason: collision with root package name */
    public t f4930a;

    private a() {
        a((Network) null);
    }

    public static a a() {
        a aVar;
        synchronized (a.class) {
            if (e == null) {
                e = new a();
            }
            aVar = e;
        }
        return aVar;
    }

    public okhttp3.e a(String str) {
        return this.f4930a.a(new v.a().a(str).a().b());
    }

    public okhttp3.e a(String str, long j) {
        return this.f4930a.a(new v.a().a(str).b(HttpHeaders.RANGE, "bytes=" + j + HelpFormatter.DEFAULT_OPT_PREFIX).a().b());
    }

    public void a(Network network) {
        t.a x = new t().x();
        x.a(10L, TimeUnit.SECONDS).c(30L, TimeUnit.SECONDS).b(30L, TimeUnit.SECONDS).a(new i(0, 5L, TimeUnit.MINUTES));
        if (Build.VERSION.SDK_INT >= 23 && network != null) {
            x.a(network.getSocketFactory());
        }
        this.f4930a = x.a();
    }

    @Override // com.yiaction.common.http.f
    public void a_(String str, final String str2, final g gVar) {
        d = new f.a();
        this.f4930a.a(new v.a().a(str).a().b()).a(new okhttp3.f() { // from class: com.yiaction.common.http.a.1
            @Override // okhttp3.f
            public void a(okhttp3.e eVar, IOException iOException) {
                f.a(gVar, iOException.getMessage());
            }

            /* JADX WARN: Removed duplicated region for block: B:44:0x009c A[Catch: IOException -> 0x00a0, TRY_LEAVE, TryCatch #6 {IOException -> 0x00a0, blocks: (B:50:0x0097, B:44:0x009c), top: B:49:0x0097 }] */
            /* JADX WARN: Removed duplicated region for block: B:49:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // okhttp3.f
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void a(okhttp3.e r11, okhttp3.x r12) {
                /*
                    r10 = this;
                    r2 = 0
                    r7 = 0
                    boolean r0 = r12.c()
                    if (r0 == 0) goto La9
                    java.io.File r0 = new java.io.File
                    java.lang.String r1 = r3
                    r0.<init>(r1)
                    r1 = 2048(0x800, float:2.87E-42)
                    byte[] r6 = new byte[r1]
                    r4 = 0
                    okhttp3.y r1 = r12.g()     // Catch: java.lang.Throwable -> L92 java.io.IOException -> Lbb
                    java.io.InputStream r3 = r1.d()     // Catch: java.lang.Throwable -> L92 java.io.IOException -> Lbb
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> Lb3 java.io.IOException -> Lbe
                    r1.<init>(r0)     // Catch: java.lang.Throwable -> Lb3 java.io.IOException -> Lbe
                L22:
                    int r0 = r3.read(r6)     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    r2 = -1
                    if (r0 == r2) goto L6a
                    long r8 = (long) r0     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    long r4 = r4 + r8
                    r2 = 0
                    r1.write(r6, r2, r0)     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    r0.<init>()     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    java.lang.String r2 = "current------>"
                    java.lang.StringBuilder r0 = r0.append(r2)     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    java.lang.StringBuilder r0 = r0.append(r4)     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    java.lang.String r0 = r0.toString()     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    r2 = 0
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    com.yiaction.common.util.g.a(r0, r2)     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    goto L22
                L49:
                    r0 = move-exception
                    r2 = r3
                L4b:
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> Lb8
                    java.lang.String r0 = "下载失败"
                    r3 = 0
                    java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb8
                    com.yiaction.common.util.g.a(r0, r3)     // Catch: java.lang.Throwable -> Lb8
                    if (r2 == 0) goto L5b
                    r2.close()     // Catch: java.io.IOException -> L89
                L5b:
                    if (r1 == 0) goto L60
                    r1.close()     // Catch: java.io.IOException -> L89
                L60:
                    com.yiaction.common.http.g r0 = r2
                    java.lang.String r1 = r12.d()
                    com.yiaction.common.http.f.a(r0, r1)
                L69:
                    return
                L6a:
                    r1.flush()     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    java.lang.String r0 = r3     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    r2 = 0
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    com.yiaction.common.util.g.a(r0, r2)     // Catch: java.io.IOException -> L49 java.lang.Throwable -> Lb6
                    if (r3 == 0) goto L7a
                    r3.close()     // Catch: java.io.IOException -> L80
                L7a:
                    if (r1 == 0) goto L60
                    r1.close()     // Catch: java.io.IOException -> L80
                    goto L60
                L80:
                    r0 = move-exception
                    java.lang.String r0 = "下载失败"
                    java.lang.Object[] r1 = new java.lang.Object[r7]
                    com.yiaction.common.util.g.a(r0, r1)
                    goto L60
                L89:
                    r0 = move-exception
                    java.lang.String r0 = "下载失败"
                    java.lang.Object[] r1 = new java.lang.Object[r7]
                    com.yiaction.common.util.g.a(r0, r1)
                    goto L60
                L92:
                    r0 = move-exception
                    r1 = r2
                    r3 = r2
                L95:
                    if (r3 == 0) goto L9a
                    r3.close()     // Catch: java.io.IOException -> La0
                L9a:
                    if (r1 == 0) goto L9f
                    r1.close()     // Catch: java.io.IOException -> La0
                L9f:
                    throw r0
                La0:
                    r1 = move-exception
                    java.lang.String r1 = "下载失败"
                    java.lang.Object[] r2 = new java.lang.Object[r7]
                    com.yiaction.common.util.g.a(r1, r2)
                    goto L9f
                La9:
                    com.yiaction.common.http.g r0 = r2
                    java.lang.String r1 = r12.d()
                    com.yiaction.common.http.f.a(r0, r1)
                    goto L69
                Lb3:
                    r0 = move-exception
                    r1 = r2
                    goto L95
                Lb6:
                    r0 = move-exception
                    goto L95
                Lb8:
                    r0 = move-exception
                    r3 = r2
                    goto L95
                Lbb:
                    r0 = move-exception
                    r1 = r2
                    goto L4b
                Lbe:
                    r0 = move-exception
                    r1 = r2
                    r2 = r3
                    goto L4b
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yiaction.common.http.a.AnonymousClass1.a(okhttp3.e, okhttp3.x):void");
            }
        });
    }
}
