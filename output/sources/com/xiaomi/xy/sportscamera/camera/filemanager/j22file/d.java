package com.xiaomi.xy.sportscamera.camera.filemanager.j22file;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.ants360.z13.activity.CameraApplication;
import com.rp.rpfiledatapool.model.RPFile;
import com.yiaction.common.util.Constant;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import kotlin.g;
import kotlin.text.i;

@g(a = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u000256B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\tJ\u001f\u0010#\u001a\u00020\u001c\"\b\b\u0000\u0010$*\u00020\t2\b\u0010\"\u001a\u0004\u0018\u0001H$¢\u0006\u0002\u0010%J\u000e\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020\tJ\u0016\u0010&\u001a\u00020!2\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bJ\b\u0010)\u001a\u00020!H\u0002J\u0010\u0010*\u001a\u00020!2\u0006\u0010\"\u001a\u00020\tH\u0002J\u0010\u0010+\u001a\u00020!2\u0006\u0010\"\u001a\u00020\tH\u0002J\u0018\u0010,\u001a\u00020!2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u0004H\u0002J\u0010\u0010.\u001a\u00020!2\u0006\u0010\"\u001a\u00020\tH\u0002J\u0016\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u000202J\b\u00103\u001a\u00020!H\u0002J\u0006\u00104\u001a\u00020!R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00020\u001c8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileDownloadUtil;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "allDownLoadList", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "getAllDownLoadList", "()Ljava/util/List;", "downLoadedList", "getDownLoadedList", "downLoadingList", "getDownLoadingList", "mAllDownLoadList", "", "mCurrentDownLoadFileItem", "mDownLoadQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "mDownLoadThread", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileDownloadUtil$CameraFileDownLoadThread;", "mDownLoadedList", "mDownLoadingList", "mHandler", "Landroid/os/Handler;", "mIsDownLoadCompleted", "", "threadPool", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "cancelDownload", "", "item", "checkDownloadStorage", "T", "(Lcom/rp/rpfiledatapool/model/RPFile;)Z", "downFile", "rpFile", "list", "init", "onDownLoadFailed", "onDownLoadSuccess", "onProgressUpdate", "speed", "onStartDownLoad", "scanFile", "path", "listener", "Landroid/media/MediaScannerConnection$OnScanCompletedListener;", "startDownLoadThread", "stop", "CameraFileDownLoadThread", "FileDownLoadTask", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    public static final d f4629a = null;
    private static final String b = "CameraFileDownload";
    private static a c;
    private static final ExecutorService d = null;
    private static final Handler e = null;
    private static List<RPFile> f;
    private static List<RPFile> g;
    private static List<RPFile> h;
    private static LinkedBlockingQueue<RPFile> i;
    private static volatile boolean j;
    private static RPFile k;

    @g(a = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileDownloadUtil$CameraFileDownLoadThread;", "Ljava/lang/Thread;", "()V", "isStop", "", "()Z", "setStop", "(Z)V", "run", "", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4630a = false;

        public final void a(boolean z) {
            this.f4630a = z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            d dVar = d.f4629a;
            d.j = true;
            d dVar2 = d.f4629a;
            d.k = (RPFile) null;
            while (!this.f4630a) {
                if (d.a(d.f4629a)) {
                    LinkedBlockingQueue c = d.c(d.f4629a);
                    if (c == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (c.size() > 0) {
                        d dVar3 = d.f4629a;
                        d.j = false;
                        d dVar4 = d.f4629a;
                        LinkedBlockingQueue c2 = d.c(d.f4629a);
                        if (c2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        d.k = (RPFile) c2.poll();
                        RPFile b = d.b(d.f4629a);
                        if (b == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        b bVar = new b(b);
                        if (!d.d(d.f4629a).isShutdown()) {
                            d.d(d.f4629a).execute(bVar);
                        }
                    }
                }
                LinkedBlockingQueue c3 = d.c(d.f4629a);
                if (c3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (c3.size() <= 0) {
                    com.yiaction.common.util.g.a(d.f4629a.a(), "QZCameraFileDownloadUtil download stop", new Object[0]);
                    this.f4630a = true;
                } else {
                    try {
                        com.yiaction.common.util.g.a(d.f4629a.a(), "QZCameraFileDownloadUtil download sleep", new Object[0]);
                        TimeUnit.MILLISECONDS.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            com.yiaction.common.util.g.a(d.f4629a.a(), "QZCameraFileDownloadUtil download clear", new Object[0]);
            LinkedBlockingQueue c4 = d.c(d.f4629a);
            if (c4 == null) {
                kotlin.jvm.internal.g.a();
            }
            if (c4.isEmpty()) {
                return;
            }
            LinkedBlockingQueue c5 = d.c(d.f4629a);
            if (c5 == null) {
                kotlin.jvm.internal.g.a();
            }
            c5.clear();
        }
    }

    @g(a = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0002\u001a\u00020\u0003J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileDownloadUtil$FileDownLoadTask;", "Ljava/lang/Runnable;", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "(Lcom/rp/rpfiledatapool/model/RPFile;)V", "oldSize", "", "timer", "Ljava/util/Timer;", "timerTask", "Ljava/util/TimerTask;", "download", "", "getDestFile", "Ljava/io/FileOutputStream;", "getUniqueFileName", "", "fileName", "extension", "run", "", "updateProgress", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private long f4631a;
        private final Timer b;
        private final TimerTask c;
        private final RPFile d;

        @g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                List g = d.g(d.f4629a);
                if (g == null) {
                    kotlin.jvm.internal.g.a();
                }
                g.add(b.this.d);
                List h = d.h(d.f4629a);
                if (h == null) {
                    kotlin.jvm.internal.g.a();
                }
                h.remove(b.this.d);
                com.yiaction.common.util.g.a("debug_scan_file", "begin scan file...", new Object[0]);
                d dVar = d.f4629a;
                String destPath = b.this.d.getDestPath();
                kotlin.jvm.internal.g.a((Object) destPath, "item.destPath");
                dVar.a(destPath, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.b.a.1
                    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                    public final void onScanCompleted(String str, Uri uri) {
                        com.yiaction.common.util.g.a("debug_scan_file", "scan complete, path: " + str + " uri: " + uri, new Object[0]);
                        d.f(d.f4629a).post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.b.a.1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                d.f4629a.e(b.this.d);
                            }
                        });
                    }
                });
            }
        }

        @g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* renamed from: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d$b$b, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static final class RunnableC0196b implements Runnable {
            RunnableC0196b() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                d.f4629a.f(b.this.d);
            }
        }

        public b(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            this.d = rPFile;
            this.b = new Timer();
            this.c = new TimerTask() { // from class: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.b.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    b.this.a();
                }
            };
        }

        public final String a(String str, String str2) {
            kotlin.jvm.internal.g.b(str, "fileName");
            kotlin.jvm.internal.g.b(str2, "extension");
            String str3 = str + str2;
            if (new File(str3).exists()) {
                String str4 = str + "_";
                for (int i = 1; i < Integer.MAX_VALUE; i++) {
                    str3 = str4 + i + str2;
                    if (!new File(str3).exists()) {
                        break;
                    }
                }
            }
            return str3;
        }

        public final void a() {
            if (this.f4631a == 0) {
                this.f4631a = this.d.getCurrentSize();
                return;
            }
            long currentSize = this.d.getCurrentSize();
            int i = (int) (currentSize - this.f4631a);
            this.f4631a = currentSize;
            String str = i > 1024 ? i / 1024 > 1024 ? new BigDecimal((i / 1024) / 1024).setScale(1, 4).toString() + "MB/S" : String.valueOf(i / 1024) + "KB/S" : i > 0 ? String.valueOf(i) + "B/S" : "0B/S";
            this.d.setSpeed(str);
            d.f4629a.a(this.d, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00b8 A[Catch: IOException -> 0x0103, TRY_LEAVE, TryCatch #4 {IOException -> 0x0103, blocks: (B:56:0x00b3, B:49:0x00b8), top: B:55:0x00b3 }] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0118 A[Catch: IOException -> 0x011c, TRY_LEAVE, TryCatch #9 {IOException -> 0x011c, blocks: (B:68:0x0113, B:62:0x0118), top: B:67:0x0113 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0113 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r3v11, types: [java.io.BufferedInputStream] */
        /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r3v5 */
        /* JADX WARN: Type inference failed for: r3v6 */
        /* JADX WARN: Type inference failed for: r3v7, types: [java.io.BufferedInputStream] */
        /* JADX WARN: Type inference failed for: r3v8, types: [java.io.BufferedInputStream] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean a(com.rp.rpfiledatapool.model.RPFile r11) {
            /*
                Method dump skipped, instructions count: 299
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.b.a(com.rp.rpfiledatapool.model.RPFile):boolean");
        }

        public final FileOutputStream b(RPFile rPFile) {
            String str;
            IOException e;
            FileOutputStream fileOutputStream;
            FileNotFoundException e2;
            kotlin.jvm.internal.g.b(rPFile, "item");
            FileOutputStream fileOutputStream2 = (FileOutputStream) null;
            String str2 = (String) null;
            File file = new File(Constant.b + "J22/");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!TextUtils.isEmpty(rPFile.getDestPath())) {
                str = rPFile.getDestPath();
            } else if (i.b(rPFile.getName(), "jpg", false, 2, (Object) null)) {
                str = a(Constant.b + "J22/" + i.a(rPFile.getName(), ".jpg", "", false, 4, (Object) null), ".tmp");
            } else if (i.b(rPFile.getName(), "mp4", false, 2, (Object) null)) {
                str = a(Constant.b + "J22/" + i.a(rPFile.getName(), ".mp4", "", false, 4, (Object) null), ".tmp");
            } else {
                com.yiaction.common.util.g.a(d.f4629a.a(), "unknow file extension", new Object[0]);
                str = str2;
            }
            if (str == null) {
                return null;
            }
            try {
                File file2 = new File(str);
                if (file2.exists()) {
                    fileOutputStream = new FileOutputStream(file2, true);
                } else {
                    file2.delete();
                    if (!file2.createNewFile()) {
                        com.yiaction.common.util.g.a(d.f4629a.a(), "file create failed", new Object[0]);
                        return null;
                    }
                    fileOutputStream = new FileOutputStream(file2, true);
                }
                try {
                    rPFile.setDestPath(str);
                } catch (FileNotFoundException e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    return fileOutputStream;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    return fileOutputStream;
                }
            } catch (FileNotFoundException e5) {
                e2 = e5;
                fileOutputStream = fileOutputStream2;
            } catch (IOException e6) {
                e = e6;
                fileOutputStream = fileOutputStream2;
            }
            return fileOutputStream;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.f4629a.d(this.d);
            this.b.schedule(this.c, 0L, 1000L);
            if (a(this.d)) {
                List e = d.e(d.f4629a);
                if (e == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (e.contains(this.d)) {
                    File file = new File(this.d.getDestPath());
                    String a2 = (i.b(this.d.getName(), "jpg", false, 2, (Object) null) || i.b(this.d.getName(), "JPG", false, 2, (Object) null)) ? a(i.a(this.d.getDestPath(), ".tmp", "", false, 4, (Object) null), ".jpg") : a(i.a(this.d.getDestPath(), ".tmp", "", false, 4, (Object) null), ".mp4");
                    if (a2 != null) {
                        file.renameTo(new File(a2));
                        this.d.setDestPath(a2);
                    }
                    d.f(d.f4629a).post(new a());
                }
            } else {
                d.f(d.f4629a).post(new RunnableC0196b());
            }
            this.c.cancel();
            this.b.cancel();
        }
    }

    static {
        new d();
    }

    private d() {
        f4629a = this;
        b = b;
        d = Executors.newFixedThreadPool(3);
        e = new Handler(Looper.getMainLooper());
        com.yiaction.common.util.g.a("debug_file", "-------init---------", new Object[0]);
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(RPFile rPFile, String str) {
        Iterator<com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a> it2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
        while (it2.hasNext()) {
            it2.next().a(rPFile, str);
        }
    }

    public static final /* synthetic */ boolean a(d dVar) {
        return j;
    }

    public static final /* synthetic */ RPFile b(d dVar) {
        return k;
    }

    public static final /* synthetic */ LinkedBlockingQueue c(d dVar) {
        return i;
    }

    public static final /* synthetic */ ExecutorService d(d dVar) {
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d(RPFile rPFile) {
        rPFile.setDownLoadState(RPFile.DownLoadState.DOWNLOADING);
        Iterator<com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a> it2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
        while (it2.hasNext()) {
            it2.next().d(rPFile);
        }
    }

    public static final /* synthetic */ List e(d dVar) {
        return h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e(RPFile rPFile) {
        rPFile.setDownLoadState(RPFile.DownLoadState.DOWNLOADED);
        Iterator<com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a> it2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
        while (it2.hasNext()) {
            it2.next().a(rPFile);
        }
    }

    public static final /* synthetic */ Handler f(d dVar) {
        return e;
    }

    private final void f() {
        f = Collections.synchronizedList(new ArrayList());
        g = Collections.synchronizedList(new ArrayList());
        h = Collections.synchronizedList(new ArrayList());
        i = new LinkedBlockingQueue<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void f(RPFile rPFile) {
        rPFile.setDownLoadState(RPFile.DownLoadState.DOWNLOADED);
        Iterator<com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a> it2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
        while (it2.hasNext()) {
            it2.next().e(rPFile);
        }
    }

    public static final /* synthetic */ List g(d dVar) {
        return g;
    }

    private final void g() {
        if (c != null) {
            a aVar = c;
            if (aVar == null) {
                kotlin.jvm.internal.g.a();
            }
            if (aVar.isAlive()) {
                return;
            }
        }
        c = new a();
        a aVar2 = c;
        if (aVar2 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar2.start();
    }

    public static final /* synthetic */ List h(d dVar) {
        return f;
    }

    public final String a() {
        return b;
    }

    public final void a(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "item");
        rPFile.setDownLoadState(RPFile.DownLoadState.CANCEL);
        RPFile rPFile2 = k;
        if (rPFile2 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (kotlin.jvm.internal.g.a((Object) rPFile2.getPath_dev(), (Object) rPFile.getPath_dev())) {
            RPFile rPFile3 = k;
            if (rPFile3 == null) {
                kotlin.jvm.internal.g.a();
            }
            rPFile3.setDownLoadState(RPFile.DownLoadState.CANCEL);
        }
        LinkedBlockingQueue<RPFile> linkedBlockingQueue = i;
        if (linkedBlockingQueue == null) {
            kotlin.jvm.internal.g.a();
        }
        linkedBlockingQueue.remove(rPFile);
        List<RPFile> list = f;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        list.remove(rPFile);
        List<RPFile> list2 = h;
        if (list2 == null) {
            kotlin.jvm.internal.g.a();
        }
        list2.remove(rPFile);
    }

    public final void a(String str, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        kotlin.jvm.internal.g.b(str, "path");
        kotlin.jvm.internal.g.b(onScanCompletedListener, "listener");
        String[] strArr = {MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str))};
        MediaScannerConnection.scanFile(com.rp.a.b.a(), new String[]{str}, strArr, onScanCompletedListener);
        CameraApplication.f1401a.a((Boolean) true);
    }

    public final void a(List<? extends RPFile> list) {
        if (list != null) {
            for (RPFile rPFile : list) {
                e eVar = e.f4637a;
                List<RPFile> list2 = h;
                if (list2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (!eVar.a(list2, rPFile)) {
                    e eVar2 = e.f4637a;
                    List<RPFile> list3 = f;
                    if (list3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (!eVar2.a(list3, rPFile)) {
                        List<RPFile> list4 = f;
                        if (list4 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        list4.add(rPFile);
                        List<RPFile> list5 = h;
                        if (list5 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        list5.add(rPFile);
                    }
                    e eVar3 = e.f4637a;
                    LinkedBlockingQueue<RPFile> linkedBlockingQueue = i;
                    if (linkedBlockingQueue == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (!eVar3.a(linkedBlockingQueue, rPFile)) {
                        LinkedBlockingQueue<RPFile> linkedBlockingQueue2 = i;
                        if (linkedBlockingQueue2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        linkedBlockingQueue2.add(rPFile);
                    }
                }
            }
            g();
        }
    }

    public final List<RPFile> b() {
        List<RPFile> list = h;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        return list;
    }

    public final void b(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "rpFile");
        e eVar = e.f4637a;
        List<RPFile> list = h;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        if (eVar.a(list, rPFile)) {
            return;
        }
        e eVar2 = e.f4637a;
        List<RPFile> list2 = f;
        if (list2 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (!eVar2.a(list2, rPFile)) {
            List<RPFile> list3 = f;
            if (list3 == null) {
                kotlin.jvm.internal.g.a();
            }
            list3.add(rPFile);
            List<RPFile> list4 = h;
            if (list4 == null) {
                kotlin.jvm.internal.g.a();
            }
            list4.add(rPFile);
        }
        e eVar3 = e.f4637a;
        LinkedBlockingQueue<RPFile> linkedBlockingQueue = i;
        if (linkedBlockingQueue == null) {
            kotlin.jvm.internal.g.a();
        }
        if (!eVar3.a(linkedBlockingQueue, rPFile)) {
            LinkedBlockingQueue<RPFile> linkedBlockingQueue2 = i;
            if (linkedBlockingQueue2 == null) {
                kotlin.jvm.internal.g.a();
            }
            linkedBlockingQueue2.add(rPFile);
        }
        g();
    }

    public final List<RPFile> c() {
        List<RPFile> list = g;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        return list;
    }

    public final <T extends RPFile> boolean c(T t) {
        long a2 = com.yiaction.common.util.c.a();
        return a2 == 0 || (t != null ? t.getSize() + 0 : 0L) < a2;
    }

    public final List<RPFile> d() {
        List<RPFile> list = f;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        return list;
    }

    public final void e() {
        if (i != null) {
            LinkedBlockingQueue<RPFile> linkedBlockingQueue = i;
            if (linkedBlockingQueue == null) {
                kotlin.jvm.internal.g.a();
            }
            linkedBlockingQueue.clear();
        }
        if (c != null) {
            a aVar = c;
            if (aVar == null) {
                kotlin.jvm.internal.g.a();
            }
            if (aVar.isAlive()) {
                a aVar2 = c;
                if (aVar2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                aVar2.a(true);
                c = (a) null;
            }
        }
        if (f != null) {
            List<RPFile> list = f;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            list.clear();
        }
        if (g != null) {
            List<RPFile> list2 = g;
            if (list2 == null) {
                kotlin.jvm.internal.g.a();
            }
            list2.clear();
        }
        if (h != null) {
            List<RPFile> list3 = h;
            if (list3 == null) {
                kotlin.jvm.internal.g.a();
            }
            list3.clear();
        }
        if (k != null) {
            k = (RPFile) null;
        }
    }
}
