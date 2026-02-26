package com.xiaomi.xy.sportscamera.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;
import com.decoder.util.H264Decoder;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.b.b;
import com.xiaoyi.camera.module.FileItem;
import com.yiaction.common.util.g;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class c implements com.xiaoyi.camera.c.a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4557a = c.class.getSimpleName();
    private static c b;
    private com.xiaoyi.camera.b.b c;
    private H264Decoder d;
    private String e;
    private b f;
    private ExecutorService g = Executors.newFixedThreadPool(3);
    private Handler h = new Handler(Looper.getMainLooper());
    private LinkedBlockingQueue<com.xiaoyi.camera.b.b> i;
    private LinkedBlockingQueue<com.xiaoyi.camera.b.b> j;
    private boolean k;
    private boolean l;
    private LruCache<String, Bitmap> m;
    private LinkedHashMap<String, SoftReference<Bitmap>> n;
    private BitmapFactory.Options o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class a extends com.xiaoyi.camera.b.b {
        private boolean e;
        private FileItem f;
        private String g;
        private WeakReference<ImageView> h;
        private Bitmap i;
        private boolean j;
        private Runnable k = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.c.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.b(true);
            }
        };

        public a(FileItem fileItem, ImageView imageView, boolean z) {
            this.f = fileItem;
            this.h = new WeakReference<>(imageView);
            this.j = z;
        }

        private boolean a(byte[] bArr) {
            Bitmap bitmap;
            if (bArr == null) {
                return false;
            }
            try {
                if ("thumb".equals(this.f.getType())) {
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, c.this.o);
                    int width = decodeByteArray.getWidth();
                    int height = decodeByteArray.getHeight();
                    if (width * height > 19200) {
                        g.a("debug_file", "load by http before,  Width: " + decodeByteArray.getWidth() + " Height: " + decodeByteArray.getHeight() + ", HttpPath: " + this.f.getHttpPath(), new Object[0]);
                        bitmap = a(decodeByteArray, width, height, 160, 120);
                    } else {
                        bitmap = decodeByteArray;
                    }
                    g.a("debug_file", "loaded by socket, HttpThumbUrl: " + this.f.getHttpThumbUrl() + " is succeeded. Size: " + (bArr.length / 1024) + "KB, HttpPath: " + this.f.getHttpPath() + ", memCache size: " + c.this.m.size() + ", diskCache size: " + com.jakewharton.a.b.a().c(), new Object[0]);
                } else {
                    if (!"idr".equals(this.f.getType())) {
                        return false;
                    }
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
                    allocateDirect.put(bArr);
                    c.this.d = new H264Decoder(0);
                    c.this.d.consumeNalUnitsFromDirectBuffer(allocateDirect, bArr.length, 1L);
                    int width2 = c.this.d.getWidth();
                    int height2 = c.this.d.getHeight();
                    ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect((((width2 * height2) * 3) / 2) + 1000);
                    c.this.d.decodeFrameToDirectBuffer(allocateDirect2);
                    YuvImage yuvImage = new YuvImage(H264Decoder.yuv420pToyuv420sp(allocateDirect2.array(), width2, height2), 17, width2, height2, null);
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        yuvImage.compressToJpeg(new Rect(0, 0, width2, height2), 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        c.this.d.destroy();
                        c.this.d = null;
                        if (width2 <= 0 || height2 <= 0) {
                            return false;
                        }
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        c.this.o.inPreferredConfig = Bitmap.Config.RGB_565;
                        if (!this.f.getName().contains("_thm") && !this.f.getName().startsWith("QUICK_")) {
                            options.inSampleSize = 6;
                        } else if (this.f.getName().startsWith("QUICK_")) {
                            options.inSampleSize = 2;
                        }
                        options.inPurgeable = true;
                        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                    } catch (IOException e) {
                        e.printStackTrace();
                        bitmap = null;
                    }
                }
                this.f.setThumbnail(bitmap);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        private byte[] a(FileItem fileItem) {
            byte[] bArr;
            Exception e;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long j = 0;
            try {
                byte[] bArr2 = new byte[8192];
                g.a("start download thumbnail size:" + fileItem.getThumbnailSize(), new Object[0]);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(com.xiaoyi.camera.c.a().getInputStream(), 65536);
                while (j != fileItem.getThumbnailSize() && !f()) {
                    if (bufferedInputStream.available() > 0) {
                        int read = bufferedInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        j += read;
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                }
                bArr = ((long) byteArrayOutputStream.size()) == fileItem.getThumbnailSize() ? byteArrayOutputStream.toByteArray() : null;
                try {
                    byteArrayOutputStream.close();
                    g.a("End download thumbnail and close stream", new Object[0]);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    try {
                        byteArrayOutputStream.close();
                        g.a("End download thumbnail and close stream", new Object[0]);
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return bArr;
                }
            } catch (Exception e4) {
                bArr = null;
                e = e4;
            }
            return bArr;
        }

        private byte[] a(InputStream inputStream, long j) {
            int i = 0;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) j);
            try {
                byte[] bArr = new byte[10240];
                while (j > i) {
                    int read = bufferedInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                        i += read;
                    }
                }
                bufferedInputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x01aa A[Catch: MalformedURLException -> 0x01fd, IOException -> 0x0216, OutOfMemoryError -> 0x0225, NumberFormatException -> 0x022a, TryCatch #4 {IOException -> 0x0216, NumberFormatException -> 0x022a, OutOfMemoryError -> 0x0225, MalformedURLException -> 0x01fd, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0019, B:9:0x0066, B:11:0x006c, B:13:0x007e, B:15:0x0090, B:17:0x009a, B:18:0x00e8, B:20:0x00f0, B:22:0x00f4, B:23:0x01c2, B:25:0x01c6, B:26:0x01f2, B:28:0x0193, B:30:0x0199, B:32:0x01b3, B:36:0x019d, B:38:0x01aa, B:39:0x0202, B:41:0x020c, B:42:0x021b), top: B:2:0x0001 }] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0202 A[Catch: MalformedURLException -> 0x01fd, IOException -> 0x0216, OutOfMemoryError -> 0x0225, NumberFormatException -> 0x022a, TRY_ENTER, TryCatch #4 {IOException -> 0x0216, NumberFormatException -> 0x022a, OutOfMemoryError -> 0x0225, MalformedURLException -> 0x01fd, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0019, B:9:0x0066, B:11:0x006c, B:13:0x007e, B:15:0x0090, B:17:0x009a, B:18:0x00e8, B:20:0x00f0, B:22:0x00f4, B:23:0x01c2, B:25:0x01c6, B:26:0x01f2, B:28:0x0193, B:30:0x0199, B:32:0x01b3, B:36:0x019d, B:38:0x01aa, B:39:0x0202, B:41:0x020c, B:42:0x021b), top: B:2:0x0001 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void j() {
            /*
                Method dump skipped, instructions count: 561
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.xy.sportscamera.camera.c.a.j():void");
        }

        private void k() {
            c.this.h.postDelayed(this.k, HlsChunkSource.DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS);
            final boolean a2 = a(a(this.f));
            if (a2) {
                if (c.this.m != null && this.f.getPath() != null && this.f.getThumbnail() != null) {
                    c.this.m.put(this.f.getHttpPath(), this.f.getThumbnail());
                    com.jakewharton.a.b.a().a(this.f.getHttpPath() + this.f.getSize(), 1001, this.f.getThumbnail());
                    g.a("debug_cache", "----------------addBitmapToCache: " + this.f.getHttpPath() + ", memCache size: " + c.this.m.size() + ", diskCache size: " + com.jakewharton.a.b.a().c(), new Object[0]);
                }
                g.a("debug_file", "Thumbnail load complete, " + this.f.getPath(), new Object[0]);
            } else {
                g.a("debug_file", "Thumbnail load failed, " + this.f.getPath(), new Object[0]);
            }
            c.this.h.removeCallbacks(this.k);
            c.this.a(true);
            c.this.h.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.c.a.3
                @Override // java.lang.Runnable
                public void run() {
                    c.this.a(a2, a.this.f, (ImageView) a.this.h.get());
                }
            });
        }

        private c l() {
            return c.this;
        }

        public Bitmap a(Bitmap bitmap, int i, int i2, int i3, int i4) {
            Matrix matrix = new Matrix();
            matrix.postScale(i3 / i, i4 / i2);
            g.a("debug_file", "load by http after,  Width: " + bitmap.getWidth() + " Height: " + bitmap.getHeight() + ", HttpPath: " + this.f.getHttpPath(), new Object[0]);
            return Bitmap.createBitmap(bitmap, 0, 0, i, i2, matrix, true);
        }

        @Override // com.xiaoyi.camera.b.b
        public FileItem a() {
            return this.f;
        }

        @Override // com.xiaoyi.camera.b.b
        public void a(boolean z) {
            if (!z) {
            }
        }

        @Override // com.xiaoyi.camera.b.b
        public FileItem.Action b() {
            return FileItem.Action.THUMBNAIL;
        }

        public void b(boolean z) {
            this.e = z;
        }

        @Override // com.xiaoyi.camera.b.b
        public void b_(String str) {
            this.g = str;
        }

        @Override // com.xiaoyi.camera.b.b
        public ImageView d() {
            return this.h.get();
        }

        @Override // com.xiaoyi.camera.b.b
        public String e() {
            return this.g;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                a aVar = (a) obj;
                if (l().equals(aVar.l())) {
                    return this.f == null ? aVar.f == null : this.f.equals(aVar.f);
                }
                return false;
            }
            return false;
        }

        public boolean f() {
            return this.e;
        }

        public int hashCode() {
            return (this.f == null ? 0 : this.f.hashCode()) + ((l().hashCode() + 31) * 31);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.j && !com.xiaoyi.camera.g.a().a("model").equals("J11") && !com.xiaoyi.camera.g.a().a("model").equals("J22")) {
                g.a("debug_file", "run loadThumbnail", new Object[0]);
                k();
            } else if (this.h.get() == null || this.h.get().getTag(R.integer.camera_imageview_key) == null || !this.h.get().getTag(R.integer.camera_imageview_key).equals(this.f.getPath())) {
                g.a("debug_file", "httpDownloadThumb Tag: " + (this.h.get() != null ? this.h.get().getTag(R.integer.camera_imageview_key) : "") + " Path: " + this.f.getPath(), new Object[0]);
            } else {
                j();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class b extends Thread {
        private boolean b = false;
        private LinkedBlockingQueue<com.xiaoyi.camera.b.b> c;

        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.b = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            if ("thumb".equals(str)) {
                this.c = c.this.i;
            } else if ("idr".equals(str)) {
                this.c = c.this.i;
            } else {
                this.c = c.this.j;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            c.this.a(true);
            while (!this.b) {
                try {
                    if (!c.this.f() || this.c == null || this.c.size() <= 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(50L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        c.this.c = this.c.poll();
                        Bitmap a2 = com.jakewharton.a.b.a().a(c.this.c.a().getHttpPath() + c.this.c.a().getSize(), 1001);
                        if (a2 == null || a2.isRecycled()) {
                            if (((a) c.this.c).j || com.xiaoyi.camera.g.a().a("model").equals("J11") || com.xiaoyi.camera.g.a().a("model").equals("J22")) {
                                if (!c.this.g.isShutdown() && c.this.c.d() != null && c.this.c.d().getTag(R.integer.camera_imageview_key).equals(c.this.c.a().getPath())) {
                                    c.this.g.execute(c.this.c);
                                }
                            } else if (c.this.c.d() == null || !c.this.c.d().getTag(R.integer.camera_imageview_key).equals(c.this.c.a().getPath())) {
                                c.this.a(true);
                            } else {
                                c.this.a(false);
                                com.xiaoyi.camera.g.a().c(c.this.c.a().getType(), c.this.c.a().getPath(), c.this);
                            }
                        }
                    }
                } catch (Exception e2) {
                    c.this.a(true);
                    e2.printStackTrace();
                }
            }
        }
    }

    private c() {
        g.a("debug_file", "-------init---------", new Object[0]);
        e();
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.l = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, FileItem fileItem, ImageView imageView) {
        int i = 0;
        g.a("debug_file", "onLoadThumbnailComplete, result " + z, new Object[0]);
        if (!z) {
            return;
        }
        while (true) {
            int i2 = i;
            if (i2 >= com.xiaoyi.camera.b.a.D().size()) {
                return;
            }
            com.xiaoyi.camera.b.a.D().get(i2).a(fileItem, imageView);
            i = i2 + 1;
        }
    }

    private void e() {
        this.k = com.xiaoyi.camera.d.a.a();
        this.i = new LinkedBlockingQueue<>();
        this.j = new LinkedBlockingQueue<>();
        this.m = new LruCache<String, Bitmap>(((int) Runtime.getRuntime().maxMemory()) / 4) { // from class: com.xiaomi.xy.sportscamera.camera.c.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int sizeOf(String str, Bitmap bitmap) {
                if (bitmap != null) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
                return 0;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void entryRemoved(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
                super.entryRemoved(z, str, bitmap, bitmap2);
                if (bitmap != null) {
                    c.this.n.put(str, new SoftReference(bitmap));
                }
            }
        };
        final int i = 24;
        final float f = 0.75f;
        final boolean z = false;
        this.n = new LinkedHashMap<String, SoftReference<Bitmap>>(i, f, z) { // from class: com.xiaomi.xy.sportscamera.camera.CameraThumbControlUtil$2
            private static final long serialVersionUID = 1;

            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<String, SoftReference<Bitmap>> entry) {
                return size() > 24;
            }
        };
        g();
        this.o = new BitmapFactory.Options();
        this.o.inPreferredConfig = Bitmap.Config.RGB_565;
        this.o.inPurgeable = true;
        this.o.inInputShareable = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        return this.l;
    }

    private void g() {
        if (this.f == null || !this.f.isAlive()) {
            this.f = new b();
            this.f.start();
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        switch (dVar.a()) {
            case InputDeviceCompat.SOURCE_GAMEPAD /* 1025 */:
                this.c.a().setThumbnailSize(jSONObject.optLong("size"));
                if (this.g.isShutdown()) {
                    return;
                }
                this.g.execute(this.c);
                return;
            default:
                return;
        }
    }

    public void a(String str) {
        this.e = str;
        g();
        if (this.f != null) {
            this.f.a(str);
        }
    }

    public void a(String str, FileItem fileItem, ImageView imageView) {
        a(str, fileItem, imageView, (b.a) null);
    }

    public void a(String str, FileItem fileItem, ImageView imageView, b.a aVar) {
        try {
            if (imageView.getTag(R.integer.camera_imageview_key).equals(fileItem.getPath())) {
                if (this.m.get(fileItem.getHttpPath()) != null) {
                    Bitmap bitmap = this.m.get(fileItem.getHttpPath());
                    if (bitmap == null || bitmap.isRecycled()) {
                        this.m.remove(fileItem.getHttpPath());
                    } else if (aVar != null) {
                        aVar.a(bitmap);
                    } else {
                        imageView.setImageBitmap(bitmap);
                    }
                } else if (this.n.get(fileItem.getHttpPath()) != null) {
                    Bitmap bitmap2 = this.n.get(fileItem.getHttpPath()).get();
                    if (bitmap2 == null || bitmap2.isRecycled()) {
                        this.n.remove(fileItem.getHttpPath());
                    } else {
                        if (aVar != null) {
                            aVar.a(bitmap2);
                        } else {
                            imageView.setImageBitmap(bitmap2);
                        }
                        this.m.put(fileItem.getHttpPath(), bitmap2);
                        this.n.remove(fileItem.getHttpPath());
                    }
                } else {
                    Bitmap a2 = com.jakewharton.a.b.a().a(fileItem.getHttpPath() + fileItem.getSize(), 1001);
                    if (a2 != null && !a2.isRecycled()) {
                        g.a("debug_cache", "----------------getCacheBitmapByKey: " + fileItem.getPath() + ", memCache size: " + this.m.size() + ", diskCache size: " + com.jakewharton.a.b.a().c(), new Object[0]);
                        if (aVar != null) {
                            aVar.a(a2);
                        } else {
                            imageView.setImageBitmap(a2);
                        }
                        this.m.put(fileItem.getHttpPath(), a2);
                    } else if ("thumb".equals(str)) {
                        fileItem.setType("thumb");
                        a aVar2 = new a(fileItem, imageView, this.k);
                        aVar2.b_("thumb");
                        if (this.i != null && !this.i.contains(aVar2)) {
                            this.i.add(aVar2);
                        }
                    } else if ("idr".equals(str)) {
                        fileItem.setType("idr");
                        a aVar3 = new a(fileItem, imageView, this.k);
                        aVar3.b_("idr");
                        if (this.i != null && !this.i.contains(aVar3)) {
                            this.i.add(aVar3);
                        }
                    } else {
                        if (TextUtils.isEmpty(fileItem.getType())) {
                            if (fileItem.getPath().endsWith("mp4") || fileItem.getPath().endsWith("MP4")) {
                                fileItem.setType("idr");
                            } else if (fileItem.getPath().endsWith("jpg") || fileItem.getPath().endsWith("JPG")) {
                                fileItem.setType("thumb");
                            }
                        }
                        a aVar4 = new a(fileItem, imageView, this.k);
                        aVar4.b_("both");
                        if (this.j != null && !this.j.contains(aVar4)) {
                            this.j.add(aVar4);
                        }
                    }
                }
            }
            g();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        if (this.i != null && !this.i.isEmpty()) {
            this.i.clear();
        }
        if (this.j == null || this.j.isEmpty()) {
            return;
        }
        this.j.clear();
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        g.a("debug_file", "onReceiveErrorMessage, message: " + dVar.a(), new Object[0]);
        switch (dVar.a()) {
            case InputDeviceCompat.SOURCE_GAMEPAD /* 1025 */:
                a(true);
                this.c.a(false);
                if (jSONObject != null && jSONObject.optInt("rval") == -21 && this.c.g()) {
                    if ("thumb".equals(this.c.e())) {
                        ((a) this.c).j = false;
                        this.i.add(this.c);
                        return;
                    } else if ("idr".equals(this.c.e())) {
                        ((a) this.c).j = false;
                        this.i.add(this.c);
                        return;
                    } else {
                        ((a) this.c).j = false;
                        this.j.add(this.c);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    public void c() {
        b();
        if (this.f == null || !this.f.isAlive()) {
            return;
        }
        this.f.a();
        this.f = null;
    }

    public void d() {
        b();
        if (this.f == null || !this.f.isAlive()) {
            return;
        }
        this.f.a();
        this.f = null;
    }
}
