package com.yiaction.common.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.ants360.z13.club.ClubModel;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.common.net.HttpHeaders;
import com.sina.weibo.sdk.constant.WBConstants;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.i;
import okhttp3.o;
import okhttp3.r;
import okhttp3.s;
import okhttp3.t;
import okhttp3.v;
import okhttp3.w;
import okhttp3.x;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static f f4940a;
    protected static Handler d;
    protected t b;
    public String c = "";

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static class a extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 100:
                    ((g) message.obj).a(message.getData().getString("ERROR_DATA"));
                    return;
                case 101:
                    ((g) message.obj).a((g) message.getData().getSerializable("SUCCESS_DATA"));
                    return;
                case 102:
                    h hVar = (h) message.obj;
                    ProgressModel progressModel = (ProgressModel) message.getData().getSerializable("PROGRESS_DATA");
                    if (progressModel != null) {
                        hVar.a(progressModel.curSize, progressModel.allSize, progressModel.done);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes3.dex */
    public class b implements okhttp3.f {
        private g b;

        public b(g gVar) {
            this.b = gVar;
            f.d = new a();
        }

        @Override // okhttp3.f
        public void a(okhttp3.e eVar, IOException iOException) {
            f.a(this.b, iOException.getMessage());
        }

        @Override // okhttp3.f
        public void a(okhttp3.e eVar, x xVar) {
            String f = xVar.g().f();
            if (xVar.c()) {
                f.a(this.b, (Serializable) f);
            } else {
                f.a(this.b, f);
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class c {
        public static String a() {
            return com.yiaction.common.a.b.b() + ", " + new Date(System.currentTimeMillis()).toGMTString();
        }

        private static String a(HashMap<String, String> hashMap, boolean z) {
            hashMap.put("v", com.yiaction.common.a.b.h);
            Object[] array = hashMap.keySet().toArray();
            Arrays.sort(array);
            StringBuilder sb = new StringBuilder();
            for (Object obj : array) {
                String str = (String) obj;
                sb.append(str);
                sb.append("=");
                sb.append(z ? URLEncoder.encode(hashMap.get(str)) : hashMap.get(str));
                sb.append("&");
            }
            if (array.length > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }

        public static o.a a(HashMap<String, String> hashMap) {
            o.a b = b();
            for (String str : hashMap.keySet()) {
                b.a(str, hashMap.get(str));
            }
            return b;
        }

        public static v.a a(String str) {
            v.a aVar = new v.a();
            aVar.b(HttpHeaders.USER_AGENT, com.yiaction.common.a.b);
            aVar.b("X-Xiaoyi-Date", str);
            aVar.b("X-Xiaoyi-v", com.yiaction.common.a.b.h);
            com.yiaction.common.util.g.a("headers", HttpHeaders.USER_AGENT + com.yiaction.common.a.b + "X-Xiaoyi-Date" + str, new Object[0]);
            return aVar;
        }

        public static String b(HashMap<String, String> hashMap) {
            return a(hashMap, true);
        }

        public static o.a b() {
            return new o.a().a("v", com.yiaction.common.a.b.h);
        }
    }

    /* loaded from: classes3.dex */
    public class d implements okhttp3.f {
        private g<String> b;

        public d(g<String> gVar) {
            this.b = gVar;
            f.d = new a();
        }

        @Override // okhttp3.f
        public void a(okhttp3.e eVar, IOException iOException) {
            f.a((g) this.b, iOException.getMessage());
        }

        @Override // okhttp3.f
        public void a(okhttp3.e eVar, x xVar) {
            String f = xVar.g().f();
            if (!xVar.c()) {
                f.a((g) this.b, f);
            } else if (f.this.b(f)) {
                f.a((g) this.b, (Serializable) f);
            } else {
                f.a((g) this.b, f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f() {
        c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(g gVar, Serializable serializable) {
        if (d == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = gVar;
        Bundle bundle = new Bundle();
        obtain.what = 101;
        bundle.putSerializable("SUCCESS_DATA", serializable);
        obtain.setData(bundle);
        d.sendMessage(obtain);
        com.yiaction.common.util.g.a("http success:" + serializable.toString(), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(g gVar, String str) {
        if (d == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = gVar;
        Bundle bundle = new Bundle();
        bundle.putString("ERROR_DATA", str);
        obtain.what = 100;
        obtain.setData(bundle);
        d.sendMessage(obtain);
        com.yiaction.common.util.g.a("http error:" + str, new Object[0]);
    }

    protected static void a(h hVar, ProgressModel progressModel) {
        if (d == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = hVar;
        Bundle bundle = new Bundle();
        obtain.what = 102;
        bundle.putSerializable("PROGRESS_DATA", progressModel);
        obtain.setData(bundle);
        d.sendMessage(obtain);
        com.yiaction.common.util.g.a("http progress:" + progressModel.curSize + "---" + progressModel.allSize + "---" + progressModel.done, new Object[0]);
    }

    public static f b() {
        f fVar;
        synchronized (f.class) {
            if (f4940a == null) {
                f4940a = new f();
            }
            fVar = f4940a;
        }
        return fVar;
    }

    protected t a(final com.yiaction.common.http.b bVar) {
        t.a x = new t().x();
        x.a(10L, TimeUnit.SECONDS).c(30L, TimeUnit.SECONDS).b(30L, TimeUnit.SECONDS).a(new r() { // from class: com.yiaction.common.http.f.1
            @Override // okhttp3.r
            public x a(r.a aVar) {
                x a2 = aVar.a(aVar.a());
                return a2.h().a(new e(a2.g(), bVar)).a();
            }
        });
        return x.a();
    }

    public void a(String str, final h<byte[]> hVar) {
        a(new com.yiaction.common.http.b() { // from class: com.yiaction.common.http.f.2
            @Override // com.yiaction.common.http.b
            public void a(long j, long j2, boolean z) {
                f.a(hVar, new ProgressModel(j, j2, z));
            }
        }).a(new v.a().a(str).b()).a(new okhttp3.f() { // from class: com.yiaction.common.http.f.3
            @Override // okhttp3.f
            public void a(okhttp3.e eVar, IOException iOException) {
                f.a((g) hVar, iOException.getMessage());
            }

            @Override // okhttp3.f
            public void a(okhttp3.e eVar, x xVar) {
                byte[] e = xVar.g().e();
                com.yiaction.common.util.g.a("YiHttpClient", "onResponse length = " + e.length, new Object[0]);
                if (xVar.c()) {
                    hVar.a((h) e);
                } else {
                    hVar.a(xVar.g().f());
                }
            }
        });
    }

    public void a(String str, File file, g<String> gVar) {
        this.b.a(new v.a().a(str).c(w.a((s) null, file)).b()).a(new b(gVar));
    }

    public void a(String str, File file, final h<String> hVar) {
        this.b.a(new v.a().a(str).c(new com.yiaction.common.http.d(w.a((s) null, file), new com.yiaction.common.http.b() { // from class: com.yiaction.common.http.f.4
            @Override // com.yiaction.common.http.b
            public void a(long j, long j2, boolean z) {
                f.a(hVar, new ProgressModel(j, j2, z));
            }
        })).b()).a(new b(hVar));
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, final g<String> gVar) {
        this.b.a(new v.a().a("https://api.weibo.com/2/proxy/live/create").a(new o.a().a("access_token", str).a("title", str2).a("summary", str3).a("width", str5).a("height", str6).a("published", ClubModel.beMember).a(WBConstants.GAME_PARAMS_GAME_IMAGE_URL, str4).a()).b()).a(new okhttp3.f() { // from class: com.yiaction.common.http.f.5
            @Override // okhttp3.f
            public void a(okhttp3.e eVar, IOException iOException) {
                f.a(gVar, iOException.getMessage());
            }

            @Override // okhttp3.f
            public void a(okhttp3.e eVar, x xVar) {
                String f = xVar.g().f();
                if (xVar.c()) {
                    f.a(gVar, (Serializable) f);
                } else {
                    f.a(gVar, f);
                }
            }
        });
    }

    public void a(v vVar) {
        a(vVar, (String) null);
    }

    public void a(v vVar, String str) {
        com.yiaction.common.util.g.a("Request Headers: " + vVar.c().toString(), new Object[0]);
        com.yiaction.common.util.g.a("Request Url: " + vVar.a().toString(), new Object[0]);
        if (str != null) {
            com.yiaction.common.util.g.a("Request query: " + str, new Object[0]);
        }
    }

    public void a_(String str, final String str2, final g gVar) {
        this.b.a(new v.a().a(str).a().b()).a(new okhttp3.f() { // from class: com.yiaction.common.http.f.7
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
                throw new UnsupportedOperationException("Method not decompiled: com.yiaction.common.http.f.AnonymousClass7.a(okhttp3.e, okhttp3.x):void");
            }
        });
    }

    protected boolean b(String str) {
        try {
            return new JSONObject(str).optInt("code", -1) == 1;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public t c() {
        if (this.b == null) {
            t.a x = new t().x();
            x.a(10L, TimeUnit.SECONDS).c(30L, TimeUnit.SECONDS).b(30L, TimeUnit.SECONDS).a(new i());
            this.b = x.a();
        }
        return this.b;
    }

    public void m(String str, String str2, final g<String> gVar) {
        this.b.a(new v.a().a(com.yiaction.common.a.b.r).a(new o.a().a("access_token", str).a("id", str2).a(ProductAction.ACTION_DETAIL, ClubModel.beCharge).a()).b()).a(new okhttp3.f() { // from class: com.yiaction.common.http.f.6
            @Override // okhttp3.f
            public void a(okhttp3.e eVar, IOException iOException) {
                f.a(gVar, iOException.getMessage());
            }

            @Override // okhttp3.f
            public void a(okhttp3.e eVar, x xVar) {
                String f = xVar.g().f();
                if (xVar.c()) {
                    f.a(gVar, (Serializable) f);
                } else {
                    f.a(gVar, f);
                }
            }
        });
    }

    public void p(String str, g gVar) {
        v b2 = new v.a().a(str).a().b();
        this.b.a(b2).a(new b(gVar));
        a(b2);
    }
}
