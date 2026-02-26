package com.xiaomi.xy.sportscamera.camera;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.c.x;
import com.ants360.z13.util.StatisticHelper;
import com.xiaoyi.camera.module.FileItem;
import com.xiaoyi.camera.module.PhotoFileItem;
import com.xiaoyi.camera.module.VideoFileItem;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.g;
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

/* loaded from: classes3.dex */
public class a {
    private static final String d = a.class.getSimpleName();
    private static a e;

    /* renamed from: a, reason: collision with root package name */
    public List<FileItem> f4370a;
    public List<FileItem> b;
    public List<FileItem> c;
    private C0186a f;
    private ExecutorService g = Executors.newFixedThreadPool(3);
    private Handler h = new Handler(Looper.getMainLooper());
    private LinkedBlockingQueue<FileItem> i;
    private volatile boolean j;
    private FileItem k;
    private com.xiaoyi.camera.d.d l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.xy.sportscamera.camera.a$a, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public class C0186a extends Thread {
        private boolean b = false;

        public C0186a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            a.this.j = true;
            a.this.k = null;
            while (!this.b) {
                if (!a.this.j || a.this.i.size() <= 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    a.this.j = false;
                    a.this.k = (FileItem) a.this.i.poll();
                    b bVar = new b(a.this.k);
                    if (!a.this.g.isShutdown()) {
                        a.this.g.execute(bVar);
                    }
                }
            }
            if (a.this.i.isEmpty()) {
                return;
            }
            a.this.i.clear();
        }
    }

    /* loaded from: classes3.dex */
    class b implements Runnable {
        private FileItem b;
        private long c;
        private Timer d = new Timer();
        private TimerTask e;

        /* renamed from: com.xiaomi.xy.sportscamera.camera.a$b$2, reason: invalid class name */
        /* loaded from: classes3.dex */
        class AnonymousClass2 implements Runnable {
            AnonymousClass2() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.add(b.this.b);
                a.this.j(b.this.b);
                g.a("debug_scan_file", "begin scan file...", new Object[0]);
                a.a(b.this.b.getDestPath(), new MediaScannerConnection.OnScanCompletedListener() { // from class: com.xiaomi.xy.sportscamera.camera.a.b.2.1
                    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                    public void onScanCompleted(String str, Uri uri) {
                        g.a("debug_scan_file", "scan complete, path: " + str + " uri: " + uri, new Object[0]);
                        a.this.h.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.a.b.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                a.this.l(b.this.b);
                            }
                        });
                    }
                });
            }
        }

        public b(FileItem fileItem) {
            this.b = fileItem;
            this.e = new TimerTask() { // from class: com.xiaomi.xy.sportscamera.camera.a.b.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    b.this.a();
                }
            };
        }

        public String a(String str, String str2) {
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

        public void a() {
            if (this.c == 0) {
                this.c = this.b.getCurrentSize();
                return;
            }
            long currentSize = this.b.getCurrentSize();
            int i = (int) (currentSize - this.c);
            this.c = currentSize;
            String str = i > 1024 ? i / 1024 > 1024 ? new BigDecimal((i / 1024.0d) / 1024.0d).setScale(1, 4).toString() + "MB/S" : (i / 1024) + "KB/S" : i > 0 ? i + "B/S" : "0B/S";
            this.b.setSpeed(str);
            a.this.a(this.b, str);
        }

        /* JADX WARN: Removed duplicated region for block: B:61:0x01c1  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x01ce  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x01dc A[Catch: IOException -> 0x01e0, TRY_LEAVE, TryCatch #7 {IOException -> 0x01e0, blocks: (B:75:0x01d7, B:67:0x01dc), top: B:74:0x01d7 }] */
        /* JADX WARN: Removed duplicated region for block: B:73:? A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x01d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(com.xiaoyi.camera.module.FileItem r18) {
            /*
                Method dump skipped, instructions count: 513
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.xy.sportscamera.camera.a.b.a(com.xiaoyi.camera.module.FileItem):boolean");
        }

        public FileOutputStream b(FileItem fileItem) {
            String str;
            FileOutputStream fileOutputStream = null;
            File file = new File(Constant.b);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (fileItem.getDestPath() != null) {
                str = fileItem.getDestPath();
            } else if (fileItem.getPath().endsWith("jpg")) {
                str = a(Constant.b + fileItem.getName().replace(".jpg", ""), ".tmp");
            } else if (fileItem.getPath().endsWith("JPG")) {
                str = a(Constant.b + fileItem.getName().replace(".JPG", ""), ".tmp");
            } else if (fileItem.getPath().endsWith("mp4")) {
                str = a(Constant.b + fileItem.getName().replace(".mp4", ""), ".tmp");
            } else if (fileItem.getPath().endsWith("MP4")) {
                str = a(Constant.b + fileItem.getName().replace(".MP4", ""), ".tmp");
            } else if (fileItem.getPath().endsWith("SEC")) {
                str = a(Constant.b + fileItem.getName(), ".tmp");
            } else if (fileItem.getPath().endsWith("sec")) {
                str = a(Constant.b + fileItem.getName(), ".tmp");
            } else {
                Log.d(a.d, "unknow file extension");
                str = null;
            }
            if (str != null) {
                try {
                    File file2 = new File(str);
                    if (!file2.exists() || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                        file2.delete();
                        if (file2.createNewFile()) {
                            fileOutputStream = new FileOutputStream(file2, true);
                        } else {
                            Log.d(a.d, "file create failed");
                        }
                    } else {
                        fileOutputStream = new FileOutputStream(file2, true);
                    }
                    fileItem.setDestPath(str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return fileOutputStream;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.k(this.b);
            this.d.schedule(this.e, 0L, 1000L);
            if (!a(this.b)) {
                a.this.h.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.a.b.3
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.n(b.this.b);
                    }
                });
            } else if (this.b.isDownLoadingCancled()) {
                a.this.o(this.b);
            } else if (this.b.isDownLoadCompleted()) {
                File file = new File(this.b.getDestPath());
                String a2 = (this.b.getName().endsWith("jpg") || this.b.getName().endsWith("JPG")) ? a(this.b.getDestPath().replace(".tmp", ""), ".jpg") : a(this.b.getDestPath().replace(".tmp", ""), ".mp4");
                if (a2 != null) {
                    file.renameTo(new File(a2));
                    this.b.setDestPath(a2);
                }
                a.this.h.post(new AnonymousClass2());
            }
            this.e.cancel();
            this.d.cancel();
        }
    }

    private a() {
        g.a("debug_file", "-------init---------", new Object[0]);
        e();
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (e == null) {
                e = new a();
            }
            aVar = e;
        }
        return aVar;
    }

    public static void a(Context context, File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
        CameraApplication.f1401a.a((Boolean) true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(FileItem fileItem, String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= com.xiaoyi.camera.b.a.D().size()) {
                return;
            }
            com.xiaoyi.camera.b.a.D().get(i2).a(fileItem, str);
            i = i2 + 1;
        }
    }

    public static void a(String str, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        MediaScannerConnection.scanFile(CameraApplication.f1401a.i(), new String[]{str}, new String[]{MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str))}, onScanCompletedListener);
        CameraApplication.f1401a.a((Boolean) true);
    }

    public static <T extends FileItem> boolean a(ArrayList<T> arrayList) {
        return a(false, (ArrayList) arrayList);
    }

    public static <T extends FileItem> boolean a(boolean z, ArrayList<T> arrayList) {
        long j;
        long a2 = com.yiaction.common.util.c.a();
        Iterator<T> it2 = arrayList.iterator();
        long j2 = 0;
        while (it2.hasNext()) {
            T next = it2.next();
            if (next != null) {
                j = (next.getHDVideoFileItem() != null) & z ? next.getHDVideoFileItem().getSize() + j2 : next.getSize() + j2;
            } else {
                j = j2;
            }
            j2 = j;
        }
        return a2 == 0 || j2 < a2;
    }

    public static <T extends FileItem> boolean d(T t) {
        long a2 = com.yiaction.common.util.c.a();
        return a2 == 0 || (t != null ? t.getSize() + 0 : 0L) < a2;
    }

    private void e() {
        this.l = com.xiaoyi.camera.d.d.a(CameraApplication.f1401a.i());
        this.f4370a = Collections.synchronizedList(new ArrayList());
        this.b = Collections.synchronizedList(new ArrayList());
        this.c = Collections.synchronizedList(new ArrayList());
        this.i = new LinkedBlockingQueue<>();
    }

    private void f() {
        File file = new File(Constant.b);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (file2.isFile() && file2.getAbsolutePath().endsWith(".tmp")) {
                    file2.delete();
                }
            }
        }
    }

    private void g() {
        if (this.f == null || !this.f.isAlive()) {
            this.f = new C0186a();
            this.f.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(FileItem fileItem) {
        UploadStatsManager.a("Camera_File_Download", "Download_Count", "Start");
        fileItem.setDownLoading(true);
        fileItem.setDownloadState(FileItem.DownLoadState.DOWNLOADING);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= com.xiaoyi.camera.b.a.D().size()) {
                return;
            }
            com.xiaoyi.camera.b.a.D().get(i2).a(fileItem);
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(FileItem fileItem) {
        int i = 0;
        UploadStatsManager.a("Camera_File_Download", "All_Download_Result", "Download_Success");
        if (fileItem instanceof VideoFileItem) {
            if (fileItem.isHighDefinition()) {
                UploadStatsManager.a("Camera_File_Download", "HD_Download_Result", "HD_Download_Success");
            } else {
                UploadStatsManager.a("Camera_File_Download", "BD_Download_Result", "BD_Download_Success");
            }
        }
        fileItem.setDownLoading(false);
        fileItem.setDownLoadCompleted(true);
        m(fileItem);
        while (true) {
            int i2 = i;
            if (i2 >= com.xiaoyi.camera.b.a.D().size()) {
                de.greenrobot.event.c.a().c(new x());
                return;
            } else {
                com.xiaoyi.camera.b.a.D().get(i2).b(fileItem);
                i = i2 + 1;
            }
        }
    }

    private void m(FileItem fileItem) {
        if (this.l != null) {
            com.xiaoyi.camera.downloadfiledao.a aVar = new com.xiaoyi.camera.downloadfiledao.a();
            aVar.a(false);
            aVar.a(fileItem.getDestPath().replace(Constant.b, ""));
            this.l.a(aVar);
            Log.d("download", "add download file");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(FileItem fileItem) {
        int i = 0;
        UploadStatsManager.a("Camera_File_Download", "Download_Count", "Download_Fail");
        if (fileItem instanceof VideoFileItem) {
            if (fileItem.isHighDefinition()) {
                UploadStatsManager.a("Camera_File_Download", "HD_Download_Result", "HD_Download_Fail");
            } else {
                UploadStatsManager.a("Camera_File_Download", "BD_Download_Result", "BD_Download_Fail");
            }
        }
        fileItem.setDownLoading(false);
        fileItem.setDownloadState(FileItem.DownLoadState.STOP);
        while (true) {
            int i2 = i;
            if (i2 >= com.xiaoyi.camera.b.a.D().size()) {
                return;
            }
            com.xiaoyi.camera.b.a.D().get(i2).d(fileItem);
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(FileItem fileItem) {
        int i = 0;
        UploadStatsManager.a("Camera_File_Download", "Download_Count", "Download_Cancel");
        fileItem.setDownLoading(false);
        fileItem.setDownloadState(FileItem.DownLoadState.STOP);
        while (true) {
            int i2 = i;
            if (i2 >= com.xiaoyi.camera.b.a.D().size()) {
                de.greenrobot.event.c.a().c(new x());
                return;
            } else {
                com.xiaoyi.camera.b.a.D().get(i2).c(fileItem);
                i = i2 + 1;
            }
        }
    }

    public void a(List<PhotoFileItem> list) {
        long j;
        final ArrayList arrayList = new ArrayList();
        long j2 = 0;
        try {
            for (PhotoFileItem photoFileItem : list) {
                if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.c, photoFileItem)) {
                    if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.f4370a, photoFileItem)) {
                        arrayList.add(photoFileItem);
                    }
                    if (com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.i, photoFileItem)) {
                        j = j2;
                    } else {
                        photoFileItem.setDownloadState(FileItem.DownLoadState.WAIT);
                        photoFileItem.setDownLoadingCancled(false);
                        this.i.add(photoFileItem);
                        j = photoFileItem.getSize() + j2;
                    }
                    j2 = j;
                }
            }
            if (this.h != null) {
                this.h.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.f4370a.addAll(arrayList);
                        a.this.c.addAll(arrayList);
                    }
                });
            }
            g();
            StatisticHelper.h(list.size());
            StatisticHelper.k(j2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(List<FileItem> list, boolean z) {
        long j;
        ArrayList arrayList = new ArrayList();
        long j2 = 0;
        try {
            for (FileItem fileItem : list) {
                fileItem.setDownloadState(FileItem.DownLoadState.WAIT);
                fileItem.setDownLoadingCancled(false);
                if (fileItem instanceof VideoFileItem) {
                    FileItem hDVideoFileItem = z ? fileItem.getHDVideoFileItem() : fileItem;
                    if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.c, hDVideoFileItem)) {
                        if (com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.f4370a, hDVideoFileItem)) {
                            j = j2;
                        } else {
                            arrayList.add(hDVideoFileItem);
                            j = hDVideoFileItem.getSize() + j2;
                        }
                        if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.i, hDVideoFileItem)) {
                            hDVideoFileItem.setDownloadState(FileItem.DownLoadState.WAIT);
                            hDVideoFileItem.setDownLoadingCancled(false);
                            this.i.add(hDVideoFileItem);
                        }
                        j2 = j;
                    }
                } else if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.c, fileItem)) {
                    if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.f4370a, fileItem)) {
                        arrayList.add(fileItem);
                        j2 += fileItem.getSize();
                    }
                    if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.i, fileItem)) {
                        fileItem.setDownloadState(FileItem.DownLoadState.WAIT);
                        fileItem.setDownLoadingCancled(false);
                        this.i.add(fileItem);
                    }
                    j = j2;
                    j2 = j;
                }
            }
            this.f4370a.addAll(arrayList);
            this.c.addAll(arrayList);
            g();
            StatisticHelper.h(arrayList.size());
            StatisticHelper.k(j2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean a(FileItem fileItem) {
        if (fileItem == null || !fileItem.isDownLoadingCancled()) {
            return (this.k == null || !fileItem.equals(this.k)) ? (fileItem instanceof VideoFileItem) && ((VideoFileItem) fileItem).getHDVideoFileItem() != null && ((VideoFileItem) fileItem).getHDVideoFileItem().isDownLoading() : this.k.isDownLoading();
        }
        return false;
    }

    public boolean b() {
        return !this.f4370a.isEmpty();
    }

    public boolean b(FileItem fileItem) {
        return this.i.contains(fileItem);
    }

    public void c() {
        if (this.i != null) {
            this.i.clear();
        }
        if (this.f != null && this.f.isAlive()) {
            this.f.b = true;
            this.f = null;
        }
        if (this.f4370a != null && !this.f4370a.isEmpty()) {
            this.f4370a.clear();
            f();
        }
        if (this.b != null) {
            this.b.clear();
        }
        if (this.c != null) {
            this.c.clear();
        }
        if (this.k != null) {
            this.k.setDownLoadingCancled(true);
            this.k = null;
        }
        de.greenrobot.event.c.a().c(new x());
    }

    public void c(FileItem fileItem) {
        if (com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.c, fileItem)) {
            return;
        }
        if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.f4370a, fileItem)) {
            this.f4370a.add(fileItem);
            this.c.add(fileItem);
        }
        if (!com.xiaomi.xy.sportscamera.camera.filemanager.a.a(this.i, fileItem)) {
            this.i.add(fileItem);
            fileItem.setDownloadState(FileItem.DownLoadState.WAIT);
            fileItem.setDownLoadingCancled(false);
        }
        g();
        StatisticHelper.h(1);
        StatisticHelper.k(fileItem.getSize());
    }

    public void e(FileItem fileItem) {
        fileItem.setDownLoadingCancled(true);
        fileItem.setDownloadState(FileItem.DownLoadState.STOP);
        this.i.remove(fileItem);
        j(fileItem);
        this.c.remove(fileItem);
        o(fileItem);
    }

    public boolean f(FileItem fileItem) {
        return this.i != null && this.i.contains(fileItem);
    }

    public void g(FileItem fileItem) {
        this.i.remove(fileItem);
        this.c.remove(fileItem);
    }

    public boolean h(FileItem fileItem) {
        return this.f4370a != null && this.f4370a.contains(fileItem);
    }

    public void i(FileItem fileItem) {
        this.c.remove(fileItem);
    }

    public void j(FileItem fileItem) {
        synchronized (this.f4370a) {
            this.f4370a.remove(fileItem);
        }
    }
}
