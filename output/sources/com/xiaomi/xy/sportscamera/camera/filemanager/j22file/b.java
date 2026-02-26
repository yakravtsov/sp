package com.xiaomi.xy.sportscamera.camera.filemanager.j22file;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.rp.rpfiledatapool.model.RPFile;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.camera.filemanager.j22file.QZCameraFileTask;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.TypeCastException;
import kotlin.collections.k;
import kotlin.g;
import kotlin.text.i;
import org.json.JSONArray;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

@g(a = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002MNB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020/J\u000e\u00101\u001a\u00020/2\u0006\u00102\u001a\u00020\tJ\u0016\u00103\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u00104\u001a\u00020\u0004J\u000e\u00105\u001a\u00020!2\u0006\u00106\u001a\u00020!J\u000e\u00107\u001a\u00020!2\u0006\u00102\u001a\u00020\tJ\u0010\u00107\u001a\u00020!2\b\u00108\u001a\u0004\u0018\u00010!J\u0014\u00109\u001a\b\u0012\u0004\u0012\u00020\t0:2\u0006\u0010;\u001a\u00020!J\u0010\u0010<\u001a\u00020/2\u0006\u0010=\u001a\u00020>H\u0002J\u0006\u0010?\u001a\u00020/J\u0006\u0010@\u001a\u00020/J\b\u0010A\u001a\u00020\rH\u0003J\u001a\u0010B\u001a\u00020/2\u0006\u0010C\u001a\u00020\r2\b\u0010D\u001a\u0004\u0018\u00010\tH\u0002J\u001c\u0010E\u001a\u00020/2\b\u0010F\u001a\u0004\u0018\u00010G2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u001c\u0010H\u001a\u00020/2\b\u0010F\u001a\u0004\u0018\u00010G2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0010\u0010I\u001a\u00020/2\u0006\u0010J\u001a\u00020\rH\u0003J\b\u0010K\u001a\u00020/H\u0002J\u0006\u0010L\u001a\u00020/R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00020\r8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\u0011R\"\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000b\"\u0004\b#\u0010\u0011R\"\u0010$\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000b\"\u0004\b&\u0010\u0011R\u0016\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010+\u001a\n -*\u0004\u0018\u00010,0,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControlUtil;", "Lcom/xiaoyi/camera/listener/CameraCommandListener;", "()V", "LOAD_NUM", "", "getLOAD_NUM", "()I", "fileItems", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "getFileItems", "()Ljava/util/List;", "isStop", "", "mAllFileListItems", "getMAllFileListItems", "setMAllFileListItems", "(Ljava/util/List;)V", "mCurrentTask", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask;", "mFileControlThread", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControlUtil$QZFileControlThread;", "mFileTotal", "getMFileTotal", "setMFileTotal", "(I)V", "mHandler", "Landroid/os/Handler;", "mIsActionCompleted", "mPhotoFileListItems", "getMPhotoFileListItems", "setMPhotoFileListItems", "mPhotoTypeItems", "", "getMPhotoTypeItems", "setMPhotoTypeItems", "mVideoFileListItems", "getMVideoFileListItems", "setMVideoFileListItems", "mWorkingQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "sleep_timeout", "", "threadPool", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "cancelDelete", "", "clearAllFile", "deleteFile", "file", "getFileList", "position", "getFileNameNoEx", "fileName", "getImageUrl", "filePath", "getPhotoFileByType", "Ljava/util/ArrayList;", "type", "initDirectory", "json", "Lorg/json/JSONObject;", "initFile", "initFileList", "isActionComplete", "onDeleteFileComplete", "result", "item", "onReceiveErrorMessage", WBConstants.ACTION_LOG_TYPE_MESSAGE, "Lcom/xiaoyi/camera/CameraMessage;", "onReceiveMessage", "setActionComplete", "state", "startFileControlThread", "stop", "DeleteFileTask", "QZFileControlThread", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class b implements com.xiaoyi.camera.c.a {

    /* renamed from: a, reason: collision with root package name */
    public static final b f4624a = null;
    private static final int b = 200;
    private static int c = 0;
    private static List<RPFile> d = null;
    private static List<RPFile> e = null;
    private static List<RPFile> f = null;
    private static List<String> g = null;
    private static QZCameraFileTask h = null;
    private static C0194b i = null;
    private static final ExecutorService j = null;
    private static final Handler k = null;
    private static LinkedBlockingQueue<QZCameraFileTask> l = null;
    private static volatile boolean m = false;
    private static boolean n = false;
    private static final long o = 150;

    @g(a = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControlUtil$DeleteFileTask;", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask;", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "(Lcom/rp/rpfiledatapool/model/RPFile;)V", "getAction", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask$Action;", "getFileItem", "run", "", "setSuccess", "success", "", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a extends QZCameraFileTask {
        private final RPFile b;

        public a(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            this.b = rPFile;
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.QZCameraFileTask
        public RPFile a() {
            return this.b;
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.QZCameraFileTask
        public QZCameraFileTask.Action b() {
            return QZCameraFileTask.Action.DELETE;
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    @g(a = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010\f\u001a\u00020\tH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\r"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControlUtil$QZFileControlThread;", "Ljava/lang/Thread;", "()V", "isStop", "", "()Z", "setStop", "(Z)V", "dispatchByAction", "", "task", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask;", "run", "ants_sports_camera_internationalRelease"})
    /* renamed from: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0194b extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4625a;

        @g(a = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, b = {"com/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControlUtil$QZFileControlThread$dispatchByAction$1", "Lcom/xiaoyi/camera/listener/CameraCommandListener;", "(Lcom/rp/rpfiledatapool/model/RPFile;)V", "onReceiveErrorMessage", "", WBConstants.ACTION_LOG_TYPE_MESSAGE, "Lcom/xiaoyi/camera/CameraMessage;", "json", "Lorg/json/JSONObject;", "onReceiveMessage", "ants_sports_camera_internationalRelease"})
        /* renamed from: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b$b$a */
        /* loaded from: classes.dex */
        public static final class a implements com.xiaoyi.camera.c.a {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ RPFile f4626a;

            a(RPFile rPFile) {
                this.f4626a = rPFile;
            }

            @Override // com.xiaoyi.camera.c.a
            public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
                b bVar = b.f4624a;
                QZCameraFileTask c = b.c(b.f4624a);
                if (c == null) {
                    kotlin.jvm.internal.g.a();
                }
                bVar.a(true, c.a());
                com.yiaction.common.util.g.a("Receive del command:" + (this.f4626a != null ? this.f4626a.getName() : ""), new Object[0]);
                b.f4624a.a(true);
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
                b bVar = b.f4624a;
                QZCameraFileTask c = b.c(b.f4624a);
                if (c == null) {
                    kotlin.jvm.internal.g.a();
                }
                bVar.a(false, c.a());
                com.yiaction.common.util.g.a("Receive del command:" + (this.f4626a != null ? this.f4626a.getName() : ""), new Object[0]);
                b.f4624a.a(true);
            }
        }

        private final void a(QZCameraFileTask qZCameraFileTask) {
            if (qZCameraFileTask == null) {
                com.yiaction.common.util.g.a("debug_action", "dispatchByAction:: task is null", new Object[0]);
                return;
            }
            QZCameraFileTask.Action b = qZCameraFileTask.b();
            if (b != null) {
                switch (b) {
                    case DELETE:
                        b.f4624a.a(false);
                        RPFile a2 = qZCameraFileTask.a();
                        StringBuilder append = new StringBuilder().append("Strat delete file :");
                        if (a2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        com.yiaction.common.util.g.a(append.append(a2.getName()).toString(), new Object[0]);
                        String str = "192.168.100.1";
                        if (com.rp.rptool.util.d.a().b() != null) {
                            str = com.rp.rptool.util.d.a().b().d();
                            kotlin.jvm.internal.g.a((Object) str, "RPToolUtil.getInstance().device.ip");
                        }
                        String str2 = "http://" + str + a2.getPath_dev();
                        com.xiaoyi.camera.g.a().f(a2.getPath_dev(), new a(a2));
                        return;
                    default:
                        return;
                }
            }
        }

        public final void a(boolean z) {
            this.f4625a = z;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(7:4|(4:6|(1:8)|9|(6:11|12|(1:14)|15|(5:25|26|(1:28)|29|30)(3:17|18|20)|21))|31|32|34|21|2) */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00b4, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00b5, code lost:
        
            r0.printStackTrace();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r5 = this;
                r4 = 0
                super.run()
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                r1 = 1
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.a(r0, r1)
                java.lang.String r0 = "debug_file"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "-------FileControlThread---------isStop:"
                java.lang.StringBuilder r1 = r1.append(r2)
                boolean r2 = r5.f4625a
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                java.lang.Object[] r2 = new java.lang.Object[r4]
                com.yiaction.common.util.g.a(r0, r1, r2)
            L26:
                boolean r0 = r5.f4625a
                if (r0 != 0) goto Lba
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                java.util.concurrent.LinkedBlockingQueue r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.a(r0)
                if (r0 == 0) goto La6
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                java.util.concurrent.LinkedBlockingQueue r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.a(r0)
                if (r0 != 0) goto L3d
                kotlin.jvm.internal.g.a()
            L3d:
                int r0 = r0.size()
                if (r0 <= 0) goto La6
                java.lang.String r0 = "debug_file"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "-------FileControlThread---------Queue size:"
                java.lang.StringBuilder r1 = r1.append(r2)
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                java.util.concurrent.LinkedBlockingQueue r2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.a(r2)
                if (r2 != 0) goto L5b
                kotlin.jvm.internal.g.a()
            L5b:
                int r2 = r2.size()
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                java.lang.Object[] r2 = new java.lang.Object[r4]
                com.yiaction.common.util.g.a(r0, r1, r2)
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                boolean r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.b(r0)
                if (r0 == 0) goto L94
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r1 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                java.util.concurrent.LinkedBlockingQueue r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.a(r0)
                if (r0 != 0) goto L81
                kotlin.jvm.internal.g.a()
            L81:
                java.lang.Object r0 = r0.poll()
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.QZCameraFileTask r0 = (com.xiaomi.xy.sportscamera.camera.filemanager.j22file.QZCameraFileTask) r0
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.a(r1, r0)
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.QZCameraFileTask r0 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.c(r0)
                r5.a(r0)
                goto L26
            L94:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> La1
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r1 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a     // Catch: java.lang.InterruptedException -> La1
                long r2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.d(r1)     // Catch: java.lang.InterruptedException -> La1
                r0.sleep(r2)     // Catch: java.lang.InterruptedException -> La1
                goto L26
            La1:
                r0 = move-exception
                r0.printStackTrace()
                goto L26
            La6:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> Lb4
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b r1 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a     // Catch: java.lang.InterruptedException -> Lb4
                long r2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.d(r1)     // Catch: java.lang.InterruptedException -> Lb4
                r0.sleep(r2)     // Catch: java.lang.InterruptedException -> Lb4
                goto L26
            Lb4:
                r0 = move-exception
                r0.printStackTrace()
                goto L26
            Lba:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.C0194b.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @g(a = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, b = {"<anonymous>", "", "lhs", "Lcom/rp/rpfiledatapool/model/RPFile;", "kotlin.jvm.PlatformType", "rhs", "compare"})
    /* loaded from: classes.dex */
    public static final class c<T> implements Comparator<RPFile> {

        /* renamed from: a, reason: collision with root package name */
        public static final c f4627a = new c();

        c() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final int compare(RPFile rPFile, RPFile rPFile2) {
            if (rPFile == null) {
                return 1;
            }
            if (rPFile2 == null) {
                return -1;
            }
            if (rPFile.getTime() == null) {
                return 1;
            }
            if (rPFile2.getTime() == null) {
                return -1;
            }
            String time = rPFile2.getTime();
            String time2 = rPFile.getTime();
            kotlin.jvm.internal.g.a((Object) time2, "lhs.time");
            int compareTo = time.compareTo(time2);
            if (compareTo != 0) {
                return compareTo;
            }
            String name = rPFile2.getName();
            String name2 = rPFile.getName();
            kotlin.jvm.internal.g.a((Object) name2, "lhs.name");
            return name.compareTo(name2);
        }
    }

    static {
        new b();
    }

    private b() {
        f4624a = this;
        b = 200;
        j = Executors.newFixedThreadPool(3);
        k = new Handler(Looper.getMainLooper());
        o = o;
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "----------------------------------- QZCameraFileControlUtil : init", new Object[0]);
        l = new LinkedBlockingQueue<>();
        m = true;
    }

    public static final /* synthetic */ LinkedBlockingQueue a(b bVar) {
        return l;
    }

    private final void a(JSONObject jSONObject) {
        List a2;
        List a3;
        List<RPFile> list;
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        if (jSONObject.isNull("total")) {
            c = jSONObject.getInt("total_count");
        } else {
            c = jSONObject.getInt("total");
        }
        JSONArray jSONArray = jSONObject.getJSONArray("listing");
        if (jSONArray == null || jSONArray.length() <= 0) {
            Iterator<T> it2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
            while (it2.hasNext()) {
                ((com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a) it2.next()).a(false);
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length() - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length;
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                kotlin.jvm.internal.g.a((Object) jSONObject2, "array.getJSONObject(i)");
                Iterator<String> keys = jSONObject2.keys();
                if (keys.hasNext()) {
                    String next = keys.next();
                    if (next == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    String str = next;
                    String optString = jSONObject2.optString(str);
                    if (optString == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    if (!TextUtils.isEmpty(optString)) {
                        List<String> a4 = new kotlin.text.g("byte\\|").a(optString, 0);
                        if (!a4.isEmpty()) {
                            ListIterator<String> listIterator = a4.listIterator(a4.size());
                            while (listIterator.hasPrevious()) {
                                if (!(listIterator.previous().length() == 0)) {
                                    a2 = k.c((Iterable) a4, listIterator.nextIndex() + 1);
                                    break;
                                }
                            }
                        }
                        a2 = k.a();
                        List list2 = a2;
                        if (list2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                        }
                        Object[] array = list2.toArray(new String[list2.size()]);
                        if (array == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        String[] strArr = (String[]) array;
                        if (strArr != null && strArr.length == 1) {
                            List<String> a5 = new kotlin.text.g("bytes\\|").a(optString, 0);
                            if (!a5.isEmpty()) {
                                ListIterator<String> listIterator2 = a5.listIterator(a5.size());
                                while (listIterator2.hasPrevious()) {
                                    if (!(listIterator2.previous().length() == 0)) {
                                        a3 = k.c((Iterable) a5, listIterator2.nextIndex() + 1);
                                        break;
                                    }
                                }
                            }
                            a3 = k.a();
                            List list3 = a3;
                            if (list3 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                            }
                            Object[] array2 = list3.toArray(new String[list3.size()]);
                            if (array2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            strArr = (String[]) array2;
                        }
                        String str2 = strArr[1];
                        String str3 = strArr[0];
                        if (str3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                        RPFile rPFile = new RPFile(str, str2, Long.parseLong(i.b((CharSequence) str3).toString()));
                        if (rPFile.getType() != 1) {
                            arrayList.add(rPFile);
                            List<RPFile> list4 = f;
                            if (list4 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            list4.add(rPFile);
                        } else if (e != null) {
                            List<RPFile> list5 = e;
                            if (list5 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            list5.add(rPFile);
                            if (rPFile.isMultiPhoto()) {
                                List<String> list6 = g;
                                if (list6 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                if (!list6.contains(rPFile.getNameType())) {
                                    List<String> list7 = g;
                                    if (list7 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    String nameType = rPFile.getNameType();
                                    kotlin.jvm.internal.g.a((Object) nameType, "item.nameType");
                                    list7.add(nameType);
                                    arrayList.add(rPFile);
                                }
                            } else {
                                arrayList.add(rPFile);
                            }
                        }
                    }
                }
                if (i2 == 0) {
                    break;
                } else {
                    length = i2 - 1;
                }
            }
        }
        if ((!arrayList.isEmpty()) && (list = d) != null) {
            list.addAll(arrayList);
        }
        if (d != null) {
            List<RPFile> list8 = d;
            if (list8 == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!list8.isEmpty()) {
                Collections.sort(d, c.f4627a);
            }
        }
        Iterator<T> it3 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
        while (it3.hasNext()) {
            ((com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a) it3.next()).a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void a(boolean z) {
        com.yiaction.common.util.g.a("debug_action", "setActionComplete: state=" + z, new Object[0]);
        m = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(boolean z, RPFile rPFile) {
        if (!z) {
            for (com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a aVar : com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b()) {
                if (rPFile == null) {
                    kotlin.jvm.internal.g.a();
                }
                aVar.c(rPFile);
            }
            return;
        }
        if (rPFile == null) {
            kotlin.jvm.internal.g.a();
        }
        if (rPFile.getType() == 1) {
            List<RPFile> list = e;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            list.remove(rPFile);
            if (rPFile.isMultiPhoto()) {
                List<RPFile> list2 = d;
                if (list2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                int size = list2.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    List<RPFile> list3 = d;
                    if (list3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (kotlin.jvm.internal.g.a((Object) list3.get(i2).getName(), (Object) rPFile.getName())) {
                        List<RPFile> list4 = d;
                        if (list4 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        list4.remove(rPFile);
                        String nameType = rPFile.getNameType();
                        kotlin.jvm.internal.g.a((Object) nameType, "item.nameType");
                        ArrayList<RPFile> c2 = c(nameType);
                        if (c2.size() > 0) {
                            List<RPFile> list5 = d;
                            if (list5 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            RPFile rPFile2 = c2.get(0);
                            kotlin.jvm.internal.g.a((Object) rPFile2, "files[0]");
                            list5.add(i2, rPFile2);
                        }
                    } else {
                        i2++;
                    }
                }
            } else {
                List<RPFile> list6 = d;
                if (list6 == null) {
                    kotlin.jvm.internal.g.a();
                }
                list6.remove(rPFile);
                List<RPFile> list7 = e;
                if (list7 == null) {
                    kotlin.jvm.internal.g.a();
                }
                list7.remove(rPFile);
            }
        } else {
            List<RPFile> list8 = d;
            if (list8 == null) {
                kotlin.jvm.internal.g.a();
            }
            list8.remove(rPFile);
            List<RPFile> list9 = f;
            if (list9 == null) {
                kotlin.jvm.internal.g.a();
            }
            list9.remove(rPFile);
        }
        Iterator<T> it2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
        while (it2.hasNext()) {
            ((com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a) it2.next()).b(rPFile);
        }
    }

    public static final /* synthetic */ QZCameraFileTask c(b bVar) {
        return h;
    }

    public static final /* synthetic */ long d(b bVar) {
        return o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized boolean g() {
        return m;
    }

    private final void h() {
        if (i != null) {
            C0194b c0194b = i;
            if (c0194b == null) {
                kotlin.jvm.internal.g.a();
            }
            if (c0194b.isAlive()) {
                return;
            }
        }
        i = new C0194b();
        C0194b c0194b2 = i;
        if (c0194b2 == null) {
            kotlin.jvm.internal.g.a();
        }
        c0194b2.start();
    }

    public final String a(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "file");
        String str = "192.168.42.1";
        if (com.rp.rptool.util.d.a().b() != null) {
            str = com.rp.rptool.util.d.a().b().d();
            kotlin.jvm.internal.g.a((Object) str, "RPToolUtil.getInstance().device.ip");
        }
        if (rPFile.getType() == 2) {
            StringBuilder append = new StringBuilder().append("http://").append(str).append(com.rp.rpfiledatapool.a.a.b).append("video/.thumb/");
            String name = rPFile.getName();
            kotlin.jvm.internal.g.a((Object) name, "file.name");
            return append.append(b(name)).append(".THM").toString();
        }
        StringBuilder append2 = new StringBuilder().append("http://").append(str).append(com.rp.rpfiledatapool.a.a.b).append("photo/.thumb/");
        String name2 = rPFile.getName();
        kotlin.jvm.internal.g.a((Object) name2, "file.name");
        return append2.append(b(name2)).append(".THM").toString();
    }

    public final String a(String str) {
        String str2;
        if (com.rp.rptool.util.d.a().b() != null) {
            String d2 = com.rp.rptool.util.d.a().b().d();
            kotlin.jvm.internal.g.a((Object) d2, "RPToolUtil.getInstance().device.ip");
            str2 = d2;
        } else {
            str2 = "192.168.42.1";
        }
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (str == null) {
            kotlin.jvm.internal.g.a();
        }
        String str3 = (String) i.b((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null).get(r0.size() - 1);
        return i.c(str, "jpg", true) ? "http://" + str2 + com.rp.rpfiledatapool.a.a.b + "photo/.thumb/" + b(str3) + ".THM" : i.c(str, "mp4", true) ? "http://" + str2 + com.rp.rpfiledatapool.a.a.b + "video/.thumb/" + b(str3) + ".THM" : str2;
    }

    public final List<RPFile> a() {
        return d;
    }

    public final List<RPFile> a(int i2) {
        switch (i2) {
            case 1:
                ArrayList arrayList = new ArrayList();
                if (a() == null) {
                    return arrayList;
                }
                List<RPFile> a2 = a();
                if (a2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (a2.isEmpty()) {
                    return arrayList;
                }
                List<RPFile> a3 = a();
                if (a3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                for (Object obj : a3) {
                    if (((RPFile) obj).getType() == 1) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            case 2:
                return f;
            default:
                return a();
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        if (jSONObject != null) {
            a(jSONObject);
        }
    }

    public final String b(String str) {
        kotlin.jvm.internal.g.b(str, "fileName");
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int b2 = i.b((CharSequence) str, '.', 0, false, 6, (Object) null);
        int length = str.length();
        if (-1 > b2 || length < b2) {
            return str;
        }
        String substring = str.substring(0, b2);
        kotlin.jvm.internal.g.a((Object) substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public final void b() {
        n = false;
        if (d == null) {
            d = Collections.synchronizedList(new ArrayList());
        } else {
            List<RPFile> list = d;
            if (list != null) {
                list.clear();
            }
        }
        if (f == null) {
            f = Collections.synchronizedList(new ArrayList());
        } else {
            List<RPFile> list2 = f;
            if (list2 != null) {
                list2.clear();
            }
        }
        if (e == null) {
            e = Collections.synchronizedList(new ArrayList());
        } else {
            List<RPFile> list3 = e;
            if (list3 != null) {
                list3.clear();
            }
        }
        if (g == null) {
            g = Collections.synchronizedList(new ArrayList());
            return;
        }
        List<String> list4 = g;
        if (list4 != null) {
            list4.clear();
        }
    }

    public final void b(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "file");
        a aVar = new a(rPFile);
        if (l != null) {
            LinkedBlockingQueue<QZCameraFileTask> linkedBlockingQueue = l;
            if (linkedBlockingQueue == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!linkedBlockingQueue.contains(aVar)) {
                LinkedBlockingQueue<QZCameraFileTask> linkedBlockingQueue2 = l;
                if (linkedBlockingQueue2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                linkedBlockingQueue2.add(aVar);
            }
        }
        h();
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        Iterator<T> it2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b().iterator();
        while (it2.hasNext()) {
            ((com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a) it2.next()).a();
        }
    }

    public final ArrayList<RPFile> c(String str) {
        kotlin.jvm.internal.g.b(str, "type");
        ArrayList<RPFile> arrayList = new ArrayList<>();
        if (e != null) {
            List<RPFile> list = e;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!list.isEmpty()) {
                List<RPFile> list2 = e;
                if (list2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                int size = list2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    List<RPFile> list3 = e;
                    if (list3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (kotlin.jvm.internal.g.a((Object) list3.get(i2).getNameType(), (Object) str)) {
                        List<RPFile> list4 = e;
                        if (list4 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        arrayList.add(list4.get(i2));
                    }
                }
            }
        }
        return arrayList;
    }

    public final void c() {
        List<RPFile> list = d;
        if (list != null) {
            list.clear();
        }
        c = 0;
    }

    public final void d() {
        n = true;
        List<RPFile> list = d;
        if (list != null) {
            list.clear();
        }
        d = (List) null;
        c = 0;
        LinkedBlockingQueue<QZCameraFileTask> linkedBlockingQueue = l;
        if (linkedBlockingQueue != null) {
            linkedBlockingQueue.clear();
        }
        if (i != null) {
            C0194b c0194b = i;
            if (c0194b == null) {
                kotlin.jvm.internal.g.a();
            }
            if (c0194b.isAlive()) {
                C0194b c0194b2 = i;
                if (c0194b2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                c0194b2.a(true);
                i = (C0194b) null;
            }
        }
    }

    public final void e() {
        LinkedBlockingQueue<QZCameraFileTask> linkedBlockingQueue = l;
        if (linkedBlockingQueue != null) {
            linkedBlockingQueue.clear();
        }
    }

    public final void f() {
        List<RPFile> list = e;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        int size = list.size();
        List<RPFile> list2 = f;
        if (list2 == null) {
            kotlin.jvm.internal.g.a();
        }
        int size2 = list2.size() + size;
        if (d == null || size2 == 0 || size2 != c) {
            com.xiaoyi.camera.g.a().a("/tmp/fuse_d/DCIM/ -D -S", d != null ? size2 : 0, d != null ? (size2 + b) - 1 : b - 1, this);
        }
    }
}
