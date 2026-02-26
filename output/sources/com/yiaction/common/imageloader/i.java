package com.yiaction.common.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b.j;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.b.j;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes3.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static String f4954a = "file://";

    /* loaded from: classes3.dex */
    public interface a {
        void a();

        void b();
    }

    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4958a;
        private boolean b;
        private int c;
        private int d;
        private boolean e = false;
        private boolean f = false;

        private b() {
        }

        public static b a() {
            return new b();
        }

        public b a(int i, int i2) {
            this.c = i;
            this.d = i2;
            return this;
        }

        public b a(boolean z) {
            this.f4958a = z;
            return this;
        }

        public b b(boolean z) {
            this.b = z;
            return this;
        }
    }

    public static Bitmap a(Context context, String str) {
        try {
            return com.bumptech.glide.g.b(context).a(str).j().d(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void a(Context context) {
        com.bumptech.glide.g.a(context).a(com.yiaction.common.imageloader.b.class, InputStream.class, new com.yiaction.common.imageloader.a());
        com.bumptech.glide.g.a(context).a(c.class, InputStream.class, new d());
    }

    public static void a(Context context, String str, ImageView imageView, int i) {
        a(context, str, imageView, i, (b) null);
    }

    public static void a(Context context, String str, ImageView imageView, int i, a aVar) {
        a(context, str, imageView, i, aVar, (b) null);
    }

    public static void a(Context context, String str, ImageView imageView, int i, final a aVar, b bVar) {
        com.bumptech.glide.c<String> b2;
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        if (TextUtils.isEmpty(str) || !str.startsWith("http")) {
            b2 = com.bumptech.glide.g.b(context).a(str).d(i).c(i).h().b(new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>() { // from class: com.yiaction.common.imageloader.i.3
                @Override // com.bumptech.glide.request.e
                public boolean a(com.bumptech.glide.load.resource.a.b bVar2, String str2, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    if (a.this == null) {
                        return false;
                    }
                    a.this.a();
                    return false;
                }

                @Override // com.bumptech.glide.request.e
                public boolean a(Exception exc, String str2, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    if (a.this == null) {
                        return false;
                    }
                    a.this.b();
                    return false;
                }
            });
        } else {
            if (str.startsWith("https")) {
                str = "http" + str.substring(5, str.length());
            }
            b2 = com.bumptech.glide.g.b(context).a((com.bumptech.glide.i) new c(str, new j.a().a("Content-Type", "image/jpeg").a())).d(i).c(i).h().b((com.bumptech.glide.request.e) new com.bumptech.glide.request.e<com.bumptech.glide.load.b.d, com.bumptech.glide.load.resource.a.b>() { // from class: com.yiaction.common.imageloader.i.2
                @Override // com.bumptech.glide.request.e
                public boolean a(com.bumptech.glide.load.resource.a.b bVar2, com.bumptech.glide.load.b.d dVar, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    if (a.this == null) {
                        return false;
                    }
                    a.this.a();
                    return false;
                }

                @Override // com.bumptech.glide.request.e
                public boolean a(Exception exc, com.bumptech.glide.load.b.d dVar, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    if (a.this == null) {
                        return false;
                    }
                    a.this.b();
                    return false;
                }
            });
        }
        if (bVar != null) {
            if (bVar.b) {
                b2 = b2.b(true).b(DiskCacheStrategy.NONE);
            }
            if (bVar.c > 0 && bVar.d > 0) {
                b2.b(bVar.c, bVar.d);
            }
            if (!bVar.e) {
                b2.h();
            }
            if (bVar.f4958a) {
                b2.a();
            }
        } else {
            b2.h();
        }
        b2.a(imageView);
    }

    public static void a(Context context, String str, ImageView imageView, int i, b bVar) {
        a(context, str, imageView, i, bVar, (a) null);
    }

    public static void a(Context context, String str, ImageView imageView, int i, b bVar, final a aVar) {
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        com.bumptech.glide.c b2 = com.bumptech.glide.g.b(context).a((com.bumptech.glide.i) new com.yiaction.common.imageloader.b(str)).d(i).c(i).b((com.bumptech.glide.request.e) new com.bumptech.glide.request.e<com.yiaction.common.imageloader.b, com.bumptech.glide.load.resource.a.b>() { // from class: com.yiaction.common.imageloader.i.1
            @Override // com.bumptech.glide.request.e
            public boolean a(com.bumptech.glide.load.resource.a.b bVar2, com.yiaction.common.imageloader.b bVar3, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                if (a.this == null) {
                    return false;
                }
                a.this.a();
                return false;
            }

            @Override // com.bumptech.glide.request.e
            public boolean a(Exception exc, com.yiaction.common.imageloader.b bVar2, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                if (a.this == null) {
                    return false;
                }
                a.this.b();
                return false;
            }
        });
        if (bVar != null) {
            if (bVar.b) {
                b2 = b2.b(true).b(DiskCacheStrategy.NONE);
            }
            if (bVar.c > 0 && bVar.d > 0) {
                b2.b(bVar.c, bVar.d);
            }
            if (!bVar.e) {
                b2.h();
            }
            if (bVar.f4958a) {
                b2.a();
            }
        } else {
            b2.h();
        }
        b2.a(imageView);
    }

    public static Bitmap b(Context context, String str) {
        Bitmap bitmap;
        if (str.startsWith("https")) {
            str = "http" + str.substring(5, str.length());
        }
        if (str != null) {
            try {
                if (str.startsWith("http")) {
                    bitmap = com.bumptech.glide.g.b(context).a((com.bumptech.glide.i) new c(str)).j().d(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                    return bitmap;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            } catch (ExecutionException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        bitmap = com.bumptech.glide.g.b(context).a(str).j().d(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
        return bitmap;
    }

    public static void b(Context context, String str, ImageView imageView, int i) {
        a(context, str, imageView, i, (a) null, (b) null);
    }

    public static void b(Context context, String str, ImageView imageView, int i, b bVar) {
        a(context, str, imageView, i, (a) null, bVar);
    }

    public static File c(Context context, String str) {
        if (str.startsWith("https")) {
            str = "http" + str.substring(5, str.length());
        }
        com.bumptech.glide.d<String> a2 = str.startsWith("http") ? com.bumptech.glide.g.b(context).a((com.bumptech.glide.i) new c(str)) : com.bumptech.glide.g.b(context).a(str);
        a2.b(DiskCacheStrategy.ALL);
        try {
            return a2.c(Integer.MIN_VALUE, Integer.MIN_VALUE).get(20L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return null;
        } catch (TimeoutException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void c(Context context, String str, ImageView imageView, int i) {
        c(context, str, imageView, i, null);
    }

    public static void c(Context context, String str, ImageView imageView, int i, b bVar) {
        b(context, f4954a + str, imageView, i, bVar);
    }

    public static void d(Context context, String str, ImageView imageView, int i, b bVar) {
        com.bumptech.glide.g.b(context).a(str).j().d(i).b(DiskCacheStrategy.ALL).c(i).a(DecodeFormat.PREFER_ARGB_8888).h().a(imageView);
    }
}
