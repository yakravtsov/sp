package com.xiaoyi.camera.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.ants360.z13.club.ClubModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xiaoyi.camera.d;
import com.xiaoyi.camera.module.FileItem;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.g;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class CameraMainController extends com.xiaoyi.camera.b.a implements com.xiaoyi.camera.c.a {

    /* renamed from: a, reason: collision with root package name */
    public static String f4814a = "super";
    public static String b = "P";
    public static String c = "FPS";
    public static String d = "Ultra";
    public static String e = "3840x2160";
    public static String f = "4K";
    public static String g = "4000x3008";
    public static String h = "4K HD";
    public static String i = "2704x2032";
    public static String j = "2704x1520";
    public static String k = "2720x2032";
    public static String l = "2720x1520";
    public static String m = "2.7K";
    public static String n = "2560x1920";
    public static String o = "2.5K";
    public static String p = "2304x1296";
    public static String q = "2K";
    public static String r = "M";
    public static String s = "MP";
    public static String[] t = {"1280x720 60P 16:9", "1280x720 120P 16:9", "1280x720 240P 16:9"};
    public static String[] u = {"1280x720 50P 16:9", "1280x720 100P 16:9", "1280x720 200P 16:9"};
    public static String[] v = {"1920x1080 60P 16:9", "1280x720 120P 16:9", "480x360 240P 16:9"};
    public static String[] w = {"1920x1080 50P 16:9", "1280x720 100P 16:9", "480x360 200P 16:9"};
    public static String x = "PAL";
    public static String y = "NTSC";
    private a A;
    private CameraMode B;
    private Constant.RecordMode C;
    private Constant.CaptureMode D;
    private volatile boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private c J;
    private b K;
    private Context z;
    private int I = 0;
    private Handler L = new Handler(Looper.getMainLooper());
    private BroadcastReceiver M = new BroadcastReceiver() { // from class: com.xiaoyi.camera.controller.CameraMainController.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("param");
            g.a("debug_controller", "action: " + action, new Object[0]);
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action)) {
                CameraMainController.this.R();
                return;
            }
            if (com.xiaoyi.camera.a.a("video_record_complete").equals(action)) {
                String stringExtra2 = intent.getStringExtra("VIDEO_RECORD_COMPLETE_JSON");
                CameraMainController cameraMainController = CameraMainController.this;
                if (!TextUtils.isEmpty(stringExtra2)) {
                    stringExtra = stringExtra2;
                }
                cameraMainController.c(stringExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("start_photo_capture").equals(action)) {
                CameraMainController.this.d(stringExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("photo_taken").equals(action)) {
                CameraMainController.this.e(stringExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("piv_complete").equals(action)) {
                CameraMainController.this.f(true);
                return;
            }
            if (com.xiaoyi.camera.a.a("burst_complete").equals(action)) {
                CameraMainController.this.e(stringExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("precise_cont_complete").equals(action)) {
                CameraMainController.this.e(stringExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("vf_start").equals(action)) {
                CameraMainController.this.S();
                return;
            }
            if (com.xiaoyi.camera.a.a("vf_stop").equals(action)) {
                CameraMainController.this.U();
                return;
            }
            if (com.xiaoyi.camera.a.a("battery").equals(action)) {
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.i(true, true, stringExtra);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("adapter").equals(action)) {
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.i(true, false, stringExtra);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("adapter_status").equals(action)) {
                if (!"J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    CameraMainController.this.k();
                    return;
                }
                int parseInt = Integer.parseInt(stringExtra);
                String a2 = com.xiaoyi.camera.g.a().a("battery");
                if (parseInt == 0) {
                    if (CameraMainController.this.A != null) {
                        CameraMainController.this.A.i(true, true, a2);
                        return;
                    }
                    return;
                } else {
                    if (parseInt != 1 || CameraMainController.this.A == null) {
                        return;
                    }
                    CameraMainController.this.A.i(true, false, a2);
                    return;
                }
            }
            if (com.xiaoyi.camera.a.a("battery_status").equals(action)) {
                if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    return;
                }
                CameraMainController.this.k();
                return;
            }
            if (com.xiaoyi.camera.a.a("CARD_NOT_EXIST").equals(action)) {
                CameraMainController.this.W();
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.c(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("CARD_REMOVED").equals(action)) {
                CameraMainController.this.W();
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.c(-1001);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("STORAGE_RUNOUT").equals(action)) {
                CameraMainController.this.W();
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.c(-1002);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("LOW_SPEED_CARD").equals(action)) {
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.c(-1003);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("MUXER_INDEX_LIMIT").equals(action)) {
                CameraMainController.this.W();
                return;
            }
            if (com.xiaoyi.camera.a.a("MUXER_FILE_LIMIT").equals(action)) {
                CameraMainController.this.W();
                return;
            }
            if (com.xiaoyi.camera.a.a("CANNOT_ISSUE_PIV").equals(action)) {
                if (CameraMainController.this.A != null) {
                    if ("on".equals(com.xiaoyi.camera.g.a().a("piv_enable"))) {
                        CameraMainController.this.A.c(false, true);
                        return;
                    } else {
                        CameraMainController.this.A.c(false, false);
                        return;
                    }
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("switch_to_rec_mode").equals(action)) {
                CameraMainController.this.b(CameraMode.RecordMode);
                com.xiaoyi.camera.g.a().a("system_mode", CameraMode.RecordMode.toString());
                if (CameraMainController.this.A == null || !"Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    return;
                }
                CameraMainController.this.A.e(CameraMode.RecordMode.toString());
                return;
            }
            if (com.xiaoyi.camera.a.a("switch_to_cap_mode").equals(action)) {
                CameraMainController.this.b(CameraMode.CaptureMode);
                com.xiaoyi.camera.g.a().a("system_mode", CameraMode.CaptureMode.toString());
                if (CameraMainController.this.A == null || !"Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    return;
                }
                CameraMainController.this.A.e(CameraMode.CaptureMode.toString());
                return;
            }
            if (com.xiaoyi.camera.a.a("setting_changed").equals(action)) {
                CameraMainController.this.H();
                CameraMainController.this.I();
                CameraMainController.this.G();
                if (CameraMainController.this.A != null) {
                    String stringExtra3 = intent.getStringExtra("param");
                    if ("capture_mode".equalsIgnoreCase(stringExtra3)) {
                        com.xiaoyi.camera.g.a().a("system_mode", CameraMode.CaptureMode.toString());
                        CameraMainController.this.A.e(CameraMode.CaptureMode.toString());
                        com.xiaoyi.camera.module.b.b("video_shutter");
                        com.xiaoyi.camera.module.b.b("video_stamp");
                    } else if ("rec_mode".equalsIgnoreCase(stringExtra3)) {
                        com.xiaoyi.camera.g.a().a("system_mode", CameraMode.RecordMode.toString());
                        if (!"live_streaming".equals(intent.getStringExtra(FirebaseAnalytics.Param.VALUE))) {
                            CameraMainController.this.A.e(CameraMode.RecordMode.toString());
                        }
                        com.xiaoyi.camera.module.b.b("video_shutter");
                        com.xiaoyi.camera.module.b.b("video_stamp");
                    } else if ("video_standard".equalsIgnoreCase(stringExtra3)) {
                        com.xiaoyi.camera.module.b.b("video_resolution");
                        com.xiaoyi.camera.module.b.b("video_loop_resolution");
                        com.xiaoyi.camera.module.b.b("timelapse_video_resolution");
                        com.xiaoyi.camera.module.b.b("video_photo_resolution");
                        com.xiaoyi.camera.module.b.b("video_shutter");
                    } else if ("iq_photo_iso".equals(stringExtra3)) {
                        com.xiaoyi.camera.module.b.b("iq_photo_iso_min");
                    } else if ("iq_video_iso".equals(stringExtra3)) {
                        com.xiaoyi.camera.module.b.b("iq_video_iso_min");
                    } else if ("video_resolution".equals(stringExtra3) || "video_photo_resolution".equals(stringExtra3)) {
                        com.xiaoyi.camera.module.b.b("video_shutter");
                    } else if ("slow_motion_rate".equals(stringExtra3)) {
                        com.xiaoyi.camera.module.b.b("video_stamp");
                    }
                    CameraMainController.this.A.d(stringExtra3);
                    CameraMainController.this.A.e("setting_changed");
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("CARD_SIZE_INVALID").equals(action)) {
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.c(-31);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("start_usb_storage").equals(action)) {
                de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.a());
                g.a("debug_event", "CameraMainController START_USB_STORAGE post CameraStopSessionEvent", new Object[0]);
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.c(IMediaPlayer.MEDIA_ERROR_IO);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("start_fwupdate").equals(action)) {
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.c(-1005);
                }
                de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.a());
                g.a("debug_event", "CameraMainController START_FW_UPDATE post CameraStopSessionEvent", new Object[0]);
                return;
            }
            if (com.xiaoyi.camera.a.a("self_capture_stop").equals(action)) {
                CameraMainController.this.e(false);
                CameraMainController.this.c();
                com.xiaoyi.camera.g.a().a("precise_self_remain_time", com.xiaoyi.camera.g.a().a("precise_selftime"));
                com.xiaoyi.camera.g.a().a("precise_self_running", "off");
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.b(false, CameraMainController.this.d(), stringExtra);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("precise_capture_data_ready").equals(action)) {
                CameraMainController.this.w();
                com.xiaoyi.camera.g.a().a("precise_self_running", "off");
                return;
            }
            if (!com.xiaoyi.camera.a.a("rec_time").equals(action)) {
                if (!com.xiaoyi.camera.a.a("wifi_will_shutdown").equals(action) || CameraMainController.this.A == null) {
                    return;
                }
                CameraMainController.this.A.k();
                return;
            }
            if (CameraMainController.this.A == null || stringExtra == null || !CameraMainController.this.r()) {
                return;
            }
            int intValue = Integer.valueOf(stringExtra).intValue();
            CameraMainController.this.A.a(true, intValue, com.yiaction.common.util.a.a(intValue));
        }
    };
    private Runnable N = new Runnable() { // from class: com.xiaoyi.camera.controller.CameraMainController.10
        @Override // java.lang.Runnable
        public void run() {
            final String str = "on".equals(com.xiaoyi.camera.g.a().a("warp_enable")) ? "off" : "on";
            com.xiaoyi.camera.g.a().a("warp_enable", str, new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.controller.CameraMainController.10.1
                @Override // com.xiaoyi.camera.c.a
                public void a(d dVar, JSONObject jSONObject) {
                    com.xiaoyi.camera.g.a().a("warp_enable", str);
                    com.xiaoyi.camera.g.a().l(null);
                    CameraMainController.this.b(true);
                }

                @Override // com.xiaoyi.camera.c.a
                public void b(d dVar, JSONObject jSONObject) {
                    com.xiaoyi.camera.g.a().l(null);
                    CameraMainController.this.b(false);
                }
            });
        }
    };

    /* loaded from: classes3.dex */
    public enum CameraMode {
        RecordMode("record"),
        CaptureMode("capture");

        private String mode;

        CameraMode(String str) {
            this.mode = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mode;
        }
    }

    /* loaded from: classes3.dex */
    public interface a {
        void a(boolean z, int i, String str);

        void a(boolean z, int i, boolean z2, int i2);

        void a(boolean z, FileItem fileItem, ImageView imageView);

        void a(boolean z, String str, int i);

        void a(boolean z, boolean z2);

        void a(boolean z, boolean z2, String str);

        void a(boolean z, boolean z2, boolean z3);

        void a(boolean z, boolean z2, boolean z3, int i);

        void a(boolean z, boolean z2, boolean z3, String str);

        void a(boolean z, boolean z2, boolean z3, boolean z4, int i);

        void a(boolean z, boolean z2, boolean z3, boolean z4, int i, int i2);

        void b(boolean z);

        void b(boolean z, int i, String str);

        void b(boolean z, boolean z2);

        void b(boolean z, boolean z2, String str);

        void b(boolean z, boolean z2, boolean z3);

        void b(boolean z, boolean z2, boolean z3, int i);

        void c(int i);

        void c(boolean z, boolean z2);

        void c(boolean z, boolean z2, String str);

        void c(boolean z, boolean z2, boolean z3);

        void c(boolean z, boolean z2, boolean z3, int i);

        void d(String str);

        void d(boolean z);

        void d(boolean z, boolean z2, String str);

        void e(int i);

        void e(String str);

        void e(boolean z);

        void e(boolean z, boolean z2, String str);

        void f();

        void f(boolean z);

        void f(boolean z, boolean z2, String str);

        void g(boolean z);

        void g(boolean z, boolean z2, String str);

        void h(boolean z);

        void h(boolean z, boolean z2, String str);

        void i(boolean z);

        void i(boolean z, boolean z2, String str);

        void j(boolean z);

        void k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class b {
        private Timer b;
        private TimerTask c;

        private b() {
        }

        public void a() {
            if (this.b != null) {
                this.c.cancel();
                this.b.cancel();
            }
        }

        public void a(long j) {
            a();
            this.b = new Timer();
            this.c = new TimerTask() { // from class: com.xiaoyi.camera.controller.CameraMainController.b.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (CameraMainController.this.C == Constant.RecordMode.TIMELAPES && com.xiaoyi.camera.d.a.c()) {
                        com.xiaoyi.camera.g.a().f(CameraMainController.this);
                    } else {
                        com.xiaoyi.camera.g.a().e(CameraMainController.this);
                    }
                }
            };
            if (this.b != null) {
                this.b.schedule(this.c, 0L, j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class c {
        private int b;
        private Timer c;
        private TimerTask d;

        private c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (this.b < 0) {
                this.d.cancel();
                this.c.cancel();
                this.b = -1;
                return;
            }
            if (CameraMainController.this.A != null) {
                CameraMainController.this.A.e(this.b);
            }
            if (this.b == 0) {
                if (CameraMainController.this.e()) {
                    CameraMainController.this.v();
                } else {
                    CameraMainController.this.L();
                }
            }
            this.b--;
        }

        public void a() {
            if (this.c != null) {
                this.d.cancel();
                this.d = null;
                this.c.cancel();
                this.c = null;
            }
        }

        public void a(int i) {
            this.b = i;
        }

        public void a(long j) {
            a();
            this.c = new Timer();
            this.d = new TimerTask() { // from class: com.xiaoyi.camera.controller.CameraMainController.c.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    c.this.b();
                }
            };
            if (this.c != null) {
                this.c.schedule(this.d, 0L, j);
            }
        }
    }

    public CameraMainController(Context context) {
        this.z = context;
        com.xiaoyi.camera.g.a(context);
    }

    private void E() {
        this.E = false;
        this.F = false;
        this.G = false;
    }

    private void F() {
        if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
            if (y.equals(com.xiaoyi.camera.g.a().a("video_standard"))) {
                t = v;
            } else {
                u = w;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        String a2 = com.xiaoyi.camera.g.a().a("system_mode");
        if (CameraMode.RecordMode.toString().equals(a2)) {
            b(CameraMode.RecordMode);
        } else if (CameraMode.CaptureMode.toString().equals(a2)) {
            b(CameraMode.CaptureMode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        String a2 = com.xiaoyi.camera.g.a().a("capture_mode");
        if (Constant.CaptureMode.NORMAL.toString().equals(a2)) {
            this.D = Constant.CaptureMode.NORMAL;
        } else if (Constant.CaptureMode.BURST.toString().equals(a2)) {
            this.D = Constant.CaptureMode.BURST;
        } else if (Constant.CaptureMode.TIMELAPES.toString().equals(a2)) {
            this.D = Constant.CaptureMode.TIMELAPES;
        } else if (Constant.CaptureMode.TIMER.toString().equals(a2)) {
            this.D = Constant.CaptureMode.TIMER;
        }
        if ("on".equals(com.xiaoyi.camera.g.a().a("precise_self_running"))) {
            this.D = Constant.CaptureMode.TIMER;
            e(true);
        }
        Constant.CaptureMode.NORMAL.setOption("off");
        Constant.CaptureMode.TIMER.setOption(com.xiaoyi.camera.g.a().a("precise_selftime"));
        Constant.CaptureMode.BURST.setOption(com.xiaoyi.camera.g.a().a("burst_capture_number"));
        Constant.CaptureMode.TIMELAPES.setOption(com.xiaoyi.camera.g.a().a("precise_cont_time"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        String a2 = com.xiaoyi.camera.g.a().a("rec_mode");
        if (Constant.RecordMode.PHOTO.toString().equals(a2)) {
            this.C = Constant.RecordMode.PHOTO;
        } else if (Constant.RecordMode.TIMELAPES.toString().equals(a2)) {
            this.C = Constant.RecordMode.TIMELAPES;
        } else if (Constant.RecordMode.LOOP.toString().equals(a2)) {
            this.C = Constant.RecordMode.LOOP;
        } else if (Constant.RecordMode.SLOW.toString().equals(a2)) {
            this.C = Constant.RecordMode.SLOW;
        } else {
            this.C = Constant.RecordMode.NORMAL;
        }
        Constant.RecordMode.NORMAL.setOption("off");
        if (com.xiaoyi.camera.g.a().a("timelapse_video").equalsIgnoreCase("off")) {
            Constant.RecordMode.TIMELAPES.setOption("0.5");
            com.xiaoyi.camera.g.a().b("timelapse_video", "0.5", this);
        } else {
            Constant.RecordMode.TIMELAPES.setOption(com.xiaoyi.camera.g.a().a("timelapse_video"));
        }
        Constant.RecordMode.PHOTO.setOption(com.xiaoyi.camera.g.a().a("record_photo_time"));
        Constant.RecordMode.SLOW.setOption(com.xiaoyi.camera.g.a().a("slow_motion_rate"));
        Constant.RecordMode.LOOP.setOption(com.xiaoyi.camera.g.a().a("loop_rec_duration"));
    }

    private void J() {
        if (this.E) {
            N();
            com.xiaoyi.camera.g.a().d(this);
        } else {
            com.xiaoyi.camera.g.a().c(this);
        }
        v();
    }

    private void K() {
        if (!this.F) {
            com.xiaoyi.camera.g.a().c(this.D.toString() + ";" + this.D.getOption(), this);
            v();
        } else if (Constant.CaptureMode.TIMELAPES.equals(this.D)) {
            com.xiaoyi.camera.g.a().g(this);
            v();
        } else if (Constant.CaptureMode.TIMER.equals(this.D)) {
            com.xiaoyi.camera.g.a().s(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        v();
    }

    private void M() {
        if (this.K == null) {
            this.K = new b();
        }
        this.K.a("J22".equals(com.xiaoyi.camera.g.a().a("model")) ? 500L : 800L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        if (this.K != null) {
            this.K.a();
            this.K = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O() {
        String a2 = com.xiaoyi.camera.g.a().a("precise_cont_capturing");
        if ("on".equals(a2)) {
            this.F = true;
            b(CameraMode.CaptureMode);
            this.D = Constant.CaptureMode.TIMELAPES;
            if (this.A != null) {
                this.A.a(true, false);
                return;
            }
            return;
        }
        if ("off".equals(a2)) {
            if (!"off".equals(com.xiaoyi.camera.g.a().a("precise_self_running"))) {
                if (this.A == null || !this.F) {
                    return;
                }
                this.A.b(true, d(), e());
                b(com.xiaoyi.camera.g.a().a("precise_self_remain_time"));
                return;
            }
            if (d() || this.A == null) {
                return;
            }
            g.a("debug_preview", "handleIdleState()", new Object[0]);
            g.a(BuildConfig.BUILD_TYPE, "handleIdleState onReadyToPlay false preview_off", new Object[0]);
            this.A.e(false);
            w();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        b(CameraMode.CaptureMode);
        String a2 = com.xiaoyi.camera.g.a().a("capture_mode");
        e(true);
        if (Constant.CaptureMode.NORMAL.toString().equals(a2)) {
            this.D = Constant.CaptureMode.NORMAL;
            if (this.A != null) {
                this.A.a(true, d(), e());
                if (e()) {
                    v();
                    return;
                } else {
                    w();
                    L();
                    return;
                }
            }
            return;
        }
        if (Constant.CaptureMode.TIMER.toString().equals(a2)) {
            this.D = Constant.CaptureMode.TIMER;
            if (this.A != null) {
                if (!d()) {
                    this.A.b(true, false, e());
                    return;
                }
                this.A.b(true, true, e());
                if ("off".equals(com.xiaoyi.camera.g.a().a("precise_cont_capturing")) && "on".equals(com.xiaoyi.camera.g.a().a("precise_self_running"))) {
                    com.xiaoyi.camera.g.a().d("precise_self_remain_time", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.controller.CameraMainController.1
                        @Override // com.xiaoyi.camera.c.a
                        public void a(d dVar, JSONObject jSONObject) {
                            try {
                                String optString = jSONObject.optString("param");
                                if (!TextUtils.isEmpty(optString)) {
                                    String replace = optString.replace("s", "");
                                    int parseInt = Integer.parseInt(replace);
                                    if (parseInt > 0) {
                                        com.xiaoyi.camera.g.a().a("precise_self_remain_time", replace);
                                        CameraMainController.this.a(parseInt);
                                    } else {
                                        CameraMainController.this.A.b(false, true, (String) null);
                                    }
                                }
                            } catch (NumberFormatException e2) {
                                e2.printStackTrace();
                            }
                        }

                        @Override // com.xiaoyi.camera.c.a
                        public void b(d dVar, JSONObject jSONObject) {
                            CameraMainController.this.b(com.xiaoyi.camera.g.a().a("precise_self_remain_time"));
                        }
                    });
                    return;
                }
                return;
            }
            return;
        }
        if (Constant.CaptureMode.BURST.toString().equals(a2)) {
            this.D = Constant.CaptureMode.BURST;
            if (this.A != null) {
                this.A.b(true, false);
                return;
            }
            return;
        }
        if (Constant.CaptureMode.TIMELAPES.toString().equals(a2)) {
            this.D = Constant.CaptureMode.TIMELAPES;
            if (this.A != null) {
                this.A.a(true, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        if (this.A != null) {
            g.a("debug_preview", "handleVFState()", new Object[0]);
            g.a(BuildConfig.BUILD_TYPE, "handleVFState onReadyToPlay true null", new Object[0]);
            this.A.e(true);
            w();
        }
        if (Constant.CaptureMode.TIMER.equals(this.D) && this.F) {
            com.xiaoyi.camera.g.a().a("precise_self_running", "off");
            com.xiaoyi.camera.g.a().a("precise_self_remain_time", ClubModel.beMember);
            this.F = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        d(true);
        b(CameraMode.RecordMode);
        com.xiaoyi.camera.g.a().a("system_mode", CameraMode.RecordMode.toString());
        if (this.A != null && this.C != null) {
            boolean d2 = d();
            boolean equals = "on".equals(com.xiaoyi.camera.g.a().a("dual_stream_status"));
            switch (this.C) {
                case NORMAL:
                    this.A.a(true, d2, equals, "on".equals(com.xiaoyi.camera.g.a().a("piv_enable")), 0);
                    break;
                case TIMELAPES:
                    boolean z = com.xiaoyi.camera.d.a.c() || "J22".equals(com.xiaoyi.camera.g.a().a("model"));
                    this.A.a(true, false, z, "off".equalsIgnoreCase(com.xiaoyi.camera.g.a().a("timelapse_video_duration")), q(), 0);
                    if (z) {
                        com.xiaoyi.camera.g.a().e(this);
                        break;
                    }
                    break;
                case PHOTO:
                    this.A.b(true, d2, equals, 0);
                    break;
                case SLOW:
                    this.A.a(true, d2, equals, 0);
                    break;
                case LOOP:
                    this.A.c(true, d2, equals, 0);
                    break;
            }
            M();
        }
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S() {
        if (this.A != null) {
            if (d()) {
                g.a("debug_preview", "handleVfStart()", new Object[0]);
                g.a(BuildConfig.BUILD_TYPE, "handleVfStart onReadyToPlay() true null", new Object[0]);
                this.A.e(true);
            }
            w();
        }
    }

    private boolean T() {
        com.xiaoyi.camera.g.a();
        String a2 = com.xiaoyi.camera.g.a().a("video_resolution");
        if (a2 == null) {
            return true;
        }
        try {
            return Integer.parseInt(a2.split(" ")[1].replace("P", "")) <= 60;
        } catch (Exception e2) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U() {
        if (this.A != null) {
            if (d()) {
                g.a("debug_preview", "handleVfStop()", new Object[0]);
                g.a(BuildConfig.BUILD_TYPE, "handleVfStop() onReadyToPlay false null", new Object[0]);
                this.A.e(false);
            }
            if (g() == CameraMode.RecordMode && p() == Constant.RecordMode.NORMAL && T()) {
                g.a("debug_preview", "normal video mode needs preview", new Object[0]);
                g.a(BuildConfig.BUILD_TYPE, "handleVfStop() onReadyToPlay true null", new Object[0]);
                this.A.e(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        if (!this.E || this.A == null) {
            return;
        }
        this.A.j(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W() {
        if (this.E) {
            d(false);
            if (this.A != null) {
                boolean d2 = d();
                switch (this.C) {
                    case NORMAL:
                        this.A.e(true, d2, null);
                        break;
                    case TIMELAPES:
                        this.A.a(true, d2, com.xiaoyi.camera.d.a.c(), (String) null);
                        break;
                    case PHOTO:
                        this.A.g(true, d2, null);
                        break;
                    case SLOW:
                        this.A.f(true, d2, null);
                        break;
                    case LOOP:
                        this.A.h(true, d2, null);
                        break;
                }
                w();
            }
            N();
            return;
        }
        if (this.F) {
            e(false);
            if (this.D != null) {
                if (this.A != null) {
                    boolean d3 = d();
                    switch (this.D) {
                        case NORMAL:
                            this.A.a(false, d3, (String) null);
                            break;
                        case TIMER:
                            this.A.b(false, d3, (String) null);
                            break;
                        case BURST:
                            this.A.d(false, d3, null);
                            break;
                        case TIMELAPES:
                            this.A.c(false, d3, (String) null);
                            break;
                    }
                    w();
                }
                c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        if (this.A != null) {
            w();
            this.A.i(false);
        }
        this.L.postDelayed(new Runnable() { // from class: com.xiaoyi.camera.controller.CameraMainController.5
            @Override // java.lang.Runnable
            public void run() {
                com.xiaoyi.camera.g.a().b();
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y() {
        b(CameraMode.RecordMode);
        if (this.A != null) {
            w();
            this.A.i(true);
        }
        y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z() {
        if (this.A != null) {
            w();
            this.A.g(true);
        }
        y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        if (this.J == null) {
            this.J = new c();
        }
        this.J.a(i2);
        this.J.a(1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.A == null || TextUtils.isEmpty(str2)) {
            return;
        }
        com.xiaoyi.camera.g.a().a("battery", str2);
        this.A.i(true, str.equals("battery"), str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        if (this.A != null) {
            w();
            this.A.g(false);
        }
        com.xiaoyi.camera.g.a().b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        b(CameraMode.CaptureMode);
        com.xiaoyi.camera.g.a().a("system_mode", this.B.toString());
        com.xiaoyi.camera.g.a().a("capture_mode", this.D.toString());
        if (this.A != null) {
            w();
            this.A.h(true);
        }
        y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        if (this.A != null) {
            w();
            this.A.h(false);
        }
        this.L.postDelayed(new Runnable() { // from class: com.xiaoyi.camera.controller.CameraMainController.6
            @Override // java.lang.Runnable
            public void run() {
                com.xiaoyi.camera.g.a().b();
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        String a2 = com.yiaction.common.util.a.a(i2);
        if (this.A != null) {
            if (p() != null && p().equals(Constant.RecordMode.TIMELAPES) && (com.xiaoyi.camera.d.a.c() || "J22".equals(com.xiaoyi.camera.g.a().a("model")))) {
                this.A.a(true, i2, a2);
            } else {
                this.A.a(true, a2, i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String replace = str.replace("s", "");
        if (TextUtils.isEmpty(replace) || !TextUtils.isDigitsOnly(replace)) {
            return;
        }
        a(Integer.valueOf(replace).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        d(false);
        if (this.A != null && this.C != null) {
            boolean d2 = d();
            switch (this.C) {
                case NORMAL:
                    this.A.e(true, d2, str);
                    break;
                case TIMELAPES:
                    this.A.a(true, d2, com.xiaoyi.camera.d.a.c() || "J22".equals(com.xiaoyi.camera.g.a().a("model")), str);
                    break;
                case PHOTO:
                    this.A.g(true, d2, str);
                    break;
                case SLOW:
                    this.A.f(true, d2, str);
                    break;
                case LOOP:
                    this.A.h(true, d2, str);
                    break;
            }
            w();
        }
        N();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        b(CameraMode.CaptureMode);
        com.xiaoyi.camera.g.a().a("system_mode", CameraMode.CaptureMode.toString());
        e(true);
        f(str);
        if (Constant.CaptureMode.NORMAL.equals(this.D)) {
            if (this.A != null) {
                this.A.a(true, d(), e());
            }
            com.xiaoyi.camera.g.a().a("capture_mode", Constant.CaptureMode.NORMAL.toString());
            if (e()) {
                v();
                return;
            } else {
                w();
                L();
                return;
            }
        }
        if (Constant.CaptureMode.TIMER.equals(this.D)) {
            if (this.A != null) {
                this.A.b(true, d(), e());
                w();
            }
            String replace = this.D.getOption().replace("s", "");
            if (!TextUtils.isEmpty(replace) && TextUtils.isDigitsOnly(replace)) {
                a(Integer.valueOf(replace).intValue());
            }
            com.xiaoyi.camera.g.a().a("precise_self_running", "on");
            return;
        }
        if (Constant.CaptureMode.BURST.equals(this.D)) {
            if (this.A != null) {
                this.A.b(true, false);
                w();
            }
            com.xiaoyi.camera.g.a().a("capture_mode", Constant.CaptureMode.BURST.toString());
            return;
        }
        if (Constant.CaptureMode.TIMELAPES.equals(this.D)) {
            if (this.A != null) {
                this.A.a(true, false);
                w();
            }
            com.xiaoyi.camera.g.a().a("capture_mode", Constant.CaptureMode.TIMELAPES.toString());
        }
    }

    private void d(boolean z) {
        this.E = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        e(false);
        if (this.D == null) {
            return;
        }
        switch (this.D) {
            case NORMAL:
                if (this.A != null) {
                    this.A.a(true, d(), str);
                    break;
                }
                break;
            case TIMER:
                c();
                if (this.A != null) {
                    this.A.b(true, d(), str);
                    break;
                }
                break;
            case BURST:
                if (this.A != null) {
                    this.A.d(true, d(), str);
                    break;
                }
                break;
            case TIMELAPES:
                if (this.A != null) {
                    this.A.c(true, d(), str);
                    break;
                }
                break;
        }
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        this.F = z;
    }

    private void f(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String substring = str.substring(0, str.indexOf(";"));
        String substring2 = str.substring(str.indexOf(";") + 1);
        if (substring.equals(Constant.CaptureMode.NORMAL.toString())) {
            if ("off".equals(substring2)) {
                this.D = Constant.CaptureMode.NORMAL;
            } else {
                this.D = Constant.CaptureMode.TIMER;
                com.xiaoyi.camera.g.a().a("precise_selftime", substring2);
            }
        } else if (substring.equals(Constant.CaptureMode.BURST.toString())) {
            this.D = Constant.CaptureMode.BURST;
            com.xiaoyi.camera.g.a().a("burst_capture_number", substring2);
        } else if (substring.equals(Constant.CaptureMode.TIMELAPES.toString())) {
            this.D = Constant.CaptureMode.TIMELAPES;
            com.xiaoyi.camera.g.a().a("precise_cont_time", substring2);
        }
        this.D.setOption(substring2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(boolean z) {
        this.G = false;
        if (this.A != null) {
            this.A.c(z, true);
            w();
        }
    }

    public boolean A() {
        return (TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("warp_enable")) || "off".equals(com.xiaoyi.camera.g.a().a("dewarp_support_status"))) ? false : true;
    }

    public boolean B() {
        if (g() != null && p() != null && o() != null) {
            return false;
        }
        com.xiaoyi.camera.g.a().i(new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.controller.CameraMainController.2
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.g.a(jSONObject.optJSONArray("param"));
                CameraMainController.this.b();
                if (CameraMainController.this.A != null) {
                    CameraMainController.this.A.f();
                }
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
        return true;
    }

    public void a(CameraMode cameraMode) {
        if (this.E || this.F) {
            return;
        }
        switch (cameraMode) {
            case RecordMode:
                com.xiaoyi.camera.g.a().a("system_mode", CameraMode.RecordMode.toString(), this);
                return;
            case CaptureMode:
                com.xiaoyi.camera.g.a().a("system_mode", CameraMode.CaptureMode.toString(), this);
                return;
            default:
                return;
        }
    }

    public void a(a aVar) {
        g.a("debug_preview", "bindListener()", new Object[0]);
        this.A = aVar;
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(final d dVar, final JSONObject jSONObject) {
        this.L.post(new Runnable() { // from class: com.xiaoyi.camera.controller.CameraMainController.4
            @Override // java.lang.Runnable
            public void run() {
                if (jSONObject == null) {
                    return;
                }
                String optString = jSONObject.optString("type");
                String optString2 = jSONObject.optString("param");
                int optInt = jSONObject.optInt("related");
                switch (dVar.a()) {
                    case 1:
                        if ("app_status".equals(optString)) {
                            g.a("debug_upgrade", "type: " + optString + ",param:" + optString2, new Object[0]);
                            if (CameraMainController.this.A != null) {
                                boolean z = ("record".equals(optString2) || "idle".equals(optString2) || "capture".equals(optString2)) ? false : true;
                                g.a("debug_upgrade", "vfmode: " + z, new Object[0]);
                                CameraMainController.this.A.b(z);
                            }
                            com.xiaoyi.camera.g.a().a("app_status", optString2);
                            if ("record".equals(optString2) && CameraMode.RecordMode.equals(CameraMainController.this.B)) {
                                CameraMainController.this.R();
                                return;
                            }
                            if ("idle".equals(optString2) && CameraMode.CaptureMode.equals(CameraMainController.this.B)) {
                                if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                                    return;
                                }
                                CameraMainController.this.O();
                                return;
                            } else if ("capture".equals(optString2) && CameraMode.CaptureMode.equals(CameraMainController.this.B)) {
                                CameraMainController.this.P();
                                return;
                            } else {
                                if ("vf".equals(optString2)) {
                                    CameraMainController.this.Q();
                                    return;
                                }
                                return;
                            }
                        }
                        return;
                    case 2:
                        CameraMainController.this.w();
                        if ("rec_mode".equals(optString)) {
                            CameraMainController.this.Y();
                            com.xiaoyi.camera.g.a().a("system_mode", CameraMainController.this.B.toString());
                            com.xiaoyi.camera.g.a().a("rec_mode", optString2);
                        }
                        if ("timelapse_video".equals(optString) || "timelapse_video_duration".equals(optString)) {
                            if ("timelapse_video_duration".equals(optString)) {
                                com.xiaoyi.camera.g.a().a("timelapse_video_duration", optString2);
                            } else if ("timelapse_video".equals(optString)) {
                                com.xiaoyi.camera.g.a().a("timelapse_video", optString2);
                            }
                            CameraMainController.this.Z();
                        } else if ("buzzer_ring".equals(optString)) {
                            com.xiaoyi.camera.g.a().a("buzzer_ring", optString2);
                        } else if ("video_photo_resolution".equals(optString)) {
                            CameraMainController.this.Z();
                        } else if ("slow_motion_rate".equals(optString)) {
                            CameraMainController.this.Z();
                        } else if ("record_photo_time".equals(optString)) {
                            CameraMainController.this.Z();
                        } else if ("loop_rec_duration".equals(optString)) {
                            CameraMainController.this.Z();
                        }
                        if (optInt <= 0) {
                            com.xiaoyi.camera.g.a().b();
                            return;
                        }
                        return;
                    case 13:
                        CameraMainController.this.a(optString, optString2);
                        return;
                    case 15:
                        if (jSONObject != null) {
                            CameraMainController.this.H = true;
                            CameraMainController.this.I += jSONObject.optInt("param");
                            CameraMainController.this.w();
                            return;
                        }
                        return;
                    case 515:
                        if (TextUtils.isEmpty(optString2)) {
                            return;
                        }
                        CameraMainController.this.b(Integer.valueOf(optString2).intValue());
                        return;
                    case 769:
                        CameraMainController.this.V();
                        return;
                    case 770:
                        CameraMainController.this.w();
                        return;
                    case 16777228:
                        CameraMainController.this.ab();
                        return;
                    case 16777241:
                        if (CameraMainController.this.A == null || TextUtils.isEmpty(optString2) || !CameraMainController.this.r()) {
                            return;
                        }
                        int intValue = Integer.valueOf(optString2).intValue();
                        CameraMainController.this.A.b(true, intValue, com.yiaction.common.util.a.a(intValue));
                        CameraMainController.this.A.a(true, intValue, "off".equalsIgnoreCase(com.xiaoyi.camera.g.a().a("timelapse_video_duration")), CameraMainController.this.q());
                        return;
                    default:
                        return;
                }
            }
        });
    }

    @Override // com.xiaoyi.camera.b.a
    public void a(FileItem fileItem, ImageView imageView) {
        super.a(fileItem, imageView);
        if (this.A != null) {
            this.A.a(true, fileItem, imageView);
        }
    }

    public void a(Constant.CaptureMode captureMode) {
        String str = "off";
        switch (captureMode) {
            case TIMER:
                str = com.xiaoyi.camera.g.a().a("precise_selftime");
                break;
            case BURST:
                str = com.xiaoyi.camera.g.a().a("burst_capture_number");
                break;
            case TIMELAPES:
                str = com.xiaoyi.camera.g.a().a("precise_cont_time");
                break;
        }
        com.xiaoyi.camera.g.a().e(captureMode.toString() + ";" + str, this);
        this.D = captureMode;
    }

    public void a(Constant.RecordMode recordMode) {
        com.xiaoyi.camera.g.a().b("rec_mode", recordMode.toString(), this);
        this.C = recordMode;
    }

    public void a(String str) {
        v();
        if (this.D == null) {
            return;
        }
        switch (this.D) {
            case TIMER:
                this.D.setOption(str);
                com.xiaoyi.camera.g.a().a("precise_selftime", str);
                break;
            case BURST:
                this.D.setOption(str);
                com.xiaoyi.camera.g.a().a("burst_capture_number", str);
                break;
            case TIMELAPES:
                this.D.setOption(str);
                com.xiaoyi.camera.g.a().a("precise_cont_time", str);
                break;
        }
        com.xiaoyi.camera.g.a().e(this.D.toString() + ";" + this.D.getOption(), this);
    }

    public void a(String str, int i2) {
        v();
        if (this.C == null) {
            return;
        }
        switch (this.C) {
            case TIMELAPES:
                if (i2 == 0) {
                    this.C.setOption(str);
                    com.xiaoyi.camera.g.a().b("timelapse_video", str, this);
                    com.xiaoyi.camera.g.a().a("timelapse_video", str);
                    return;
                } else {
                    if (i2 == 1) {
                        com.xiaoyi.camera.g.a().b("timelapse_video_duration", str, this);
                        com.xiaoyi.camera.g.a().a("timelapse_video_duration", str);
                        return;
                    }
                    return;
                }
            case PHOTO:
                com.xiaoyi.camera.g.a().a("record_photo_time", str);
                com.xiaoyi.camera.g.a().a("record_photo_time", str, this);
                return;
            case SLOW:
                if (TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("slow_motion_res"))) {
                    com.xiaoyi.camera.g.a().a("slow_motion_rate", str);
                    com.xiaoyi.camera.g.a().a("slow_motion_rate", str, this);
                    return;
                } else {
                    com.xiaoyi.camera.g.a().a("slow_motion_res", str);
                    com.xiaoyi.camera.g.a().a("slow_motion_res", str, this);
                    return;
                }
            case LOOP:
                com.xiaoyi.camera.g.a().a("loop_rec_duration", str);
                com.xiaoyi.camera.g.a().a("loop_rec_duration", str, this);
                return;
            default:
                return;
        }
    }

    public void a(boolean z) {
        try {
            if (z) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("video_record_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("photo_taken"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("piv_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("burst_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("precise_cont_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("vf_start"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("vf_stop"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("battery"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("adapter"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("adapter_status"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("battery_status"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("CARD_NOT_EXIST"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("CARD_REMOVED"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("STORAGE_RUNOUT"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("LOW_SPEED_CARD"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("MUXER_INDEX_LIMIT"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("MUXER_FILE_LIMIT"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("CANNOT_ISSUE_PIV"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("switch_to_rec_mode"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("switch_to_cap_mode"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("setting_changed"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("CARD_SIZE_INVALID"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_usb_storage"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("self_capture_stop"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("precise_capture_data_ready"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("rec_time"));
                g.a("debug_preview", "register action: vf start", new Object[0]);
                if (this.z != null) {
                    this.z.registerReceiver(this.M, intentFilter);
                }
            } else {
                g.a("debug_preview", "unregister action: vf start", new Object[0]);
                if (this.z != null) {
                    this.z.unregisterReceiver(this.M);
                }
            }
        } catch (Exception e2) {
        }
    }

    public void b() {
        E();
        a(true);
        G();
        H();
        I();
        F();
    }

    public void b(CameraMode cameraMode) {
        this.B = cameraMode;
        g.a("debug_controller", "Current mode: " + cameraMode.toString(), new Object[0]);
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(final d dVar, final JSONObject jSONObject) {
        this.L.post(new Runnable() { // from class: com.xiaoyi.camera.controller.CameraMainController.7
            @Override // java.lang.Runnable
            public void run() {
                if (jSONObject == null) {
                    return;
                }
                int optInt = jSONObject.optInt("rval");
                switch (dVar.a()) {
                    case 2:
                        CameraMainController.this.w();
                        if (jSONObject != null) {
                            if ("rec_mode".equals(jSONObject.optString("type"))) {
                                CameraMainController.this.X();
                                return;
                            } else {
                                CameraMainController.this.aa();
                                return;
                            }
                        }
                        return;
                    case 15:
                        if (CameraMainController.this.A != null) {
                            CameraMainController.this.w();
                            CameraMainController.this.A.c(optInt);
                            return;
                        }
                        return;
                    case InputDeviceCompat.SOURCE_DPAD /* 513 */:
                        if (CameraMainController.this.A != null && CameraMainController.this.C != null) {
                            boolean d2 = CameraMainController.this.d();
                            switch (CameraMainController.this.C) {
                                case NORMAL:
                                    CameraMainController.this.A.a(false, d2, false, false, optInt);
                                    break;
                                case TIMELAPES:
                                    CameraMainController.this.A.a(false, d2, false, false, CameraMainController.this.q(), optInt);
                                    break;
                                case PHOTO:
                                    CameraMainController.this.A.b(false, d2, false, optInt);
                                    break;
                                case SLOW:
                                    CameraMainController.this.A.a(false, d2, false, optInt);
                                    break;
                                case LOOP:
                                    CameraMainController.this.A.c(false, d2, false, optInt);
                                    break;
                            }
                        }
                        CameraMainController.this.w();
                        return;
                    case 514:
                        if (CameraMainController.this.A != null && CameraMainController.this.C != null) {
                            boolean d3 = CameraMainController.this.d();
                            switch (CameraMainController.this.C) {
                                case NORMAL:
                                    CameraMainController.this.A.e(false, d3, null);
                                    break;
                                case TIMELAPES:
                                    CameraMainController.this.A.a(false, d3, com.xiaoyi.camera.d.a.c(), (String) null);
                                    break;
                                case PHOTO:
                                    CameraMainController.this.A.g(false, d3, null);
                                    break;
                                case SLOW:
                                    CameraMainController.this.A.f(false, d3, null);
                                    break;
                                case LOOP:
                                    CameraMainController.this.A.h(false, d3, null);
                                    break;
                            }
                            CameraMainController.this.w();
                        }
                        CameraMainController.this.N();
                        return;
                    case 515:
                        if (CameraMainController.this.A != null) {
                            CameraMainController.this.A.a(false, (String) null, 0);
                            return;
                        }
                        return;
                    case 770:
                        CameraMainController.this.w();
                        return;
                    case 16777220:
                        if (jSONObject != null) {
                            CameraMainController.this.e(false);
                            if (CameraMainController.this.D == null || CameraMainController.this.A == null) {
                                return;
                            }
                            boolean d4 = CameraMainController.this.d();
                            switch (CameraMainController.this.D) {
                                case NORMAL:
                                    CameraMainController.this.A.a(false, d4, (String) null);
                                    break;
                                case TIMER:
                                    CameraMainController.this.A.b(false, d4, (String) null);
                                    break;
                                case BURST:
                                    CameraMainController.this.A.d(false, d4, null);
                                    break;
                                case TIMELAPES:
                                    CameraMainController.this.A.c(false, d4, (String) null);
                                    break;
                            }
                            CameraMainController.this.A.c(optInt);
                            CameraMainController.this.w();
                            return;
                        }
                        return;
                    case 16777227:
                        CameraMainController.this.f(false);
                        return;
                    case 16777228:
                        if (jSONObject != null) {
                            if (optInt == -23) {
                                CameraMainController.this.ab();
                                return;
                            } else {
                                CameraMainController.this.H();
                                CameraMainController.this.ac();
                                return;
                            }
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void b(boolean z) {
        boolean A = A();
        boolean equals = "on".equals(com.xiaoyi.camera.g.a().a("warp_enable"));
        if (this.A != null) {
            this.A.c(z, A, equals);
        }
    }

    public void c() {
        if (this.J != null) {
            this.J.a();
            this.J = null;
        }
    }

    public void c(boolean z) {
        if (this.A != null) {
            this.A.f(z);
        }
    }

    public boolean d() {
        return "on".equals(com.xiaoyi.camera.g.a().a("preview_status")) || com.xiaoyi.camera.g.a().a("model").equals("Z16") || com.xiaoyi.camera.g.a().a("model").equals("Z18") || com.xiaoyi.camera.g.a().a("model").equals("J11") || com.xiaoyi.camera.g.a().a("model").equals("J22");
    }

    public boolean e() {
        return "auto".equalsIgnoreCase(com.xiaoyi.camera.g.a().a("iq_photo_shutter"));
    }

    public void f() {
        g.a("debug_preview", "unBindListener()", new Object[0]);
        this.A = null;
    }

    public CameraMode g() {
        return this.B;
    }

    public void h() {
        if (this.B != null) {
            switch (this.B) {
                case RecordMode:
                    J();
                    return;
                case CaptureMode:
                    K();
                    return;
                default:
                    return;
            }
        }
    }

    public void i() {
        if (CameraMode.RecordMode.equals(this.B) && this.E) {
            if ("off".equals(com.xiaoyi.camera.g.a().a("piv_enable"))) {
                if (this.A != null) {
                    this.A.c(false, false);
                }
            } else {
                com.xiaoyi.camera.g.a().o(this);
                this.G = true;
                v();
            }
        }
    }

    public void j() {
        com.xiaoyi.camera.g.a().l(this);
    }

    public void k() {
        com.xiaoyi.camera.g.a().j(this);
    }

    public String l() {
        String a2;
        String[] split;
        String str = null;
        if (this.B == null) {
            return null;
        }
        switch (this.B) {
            case RecordMode:
                try {
                    switch (this.C) {
                        case TIMELAPES:
                            a2 = com.xiaoyi.camera.g.a().a("timelapse_video_resolution");
                            break;
                        case PHOTO:
                            a2 = com.xiaoyi.camera.g.a().a("video_photo_resolution");
                            break;
                        case SLOW:
                            String a3 = com.xiaoyi.camera.g.a().a("slow_motion_rate");
                            String a4 = com.xiaoyi.camera.g.a().a("video_standard");
                            int i2 = 0;
                            while (true) {
                                if (i2 < com.xiaoyi.camera.module.b.a("slow_motion_rate").size()) {
                                    if (!com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i2).equals(a3)) {
                                        i2++;
                                    } else if (!y.equals(a4)) {
                                        if (x.equals(a4)) {
                                            a2 = u[i2];
                                            break;
                                        }
                                    } else {
                                        a2 = t[i2];
                                        break;
                                    }
                                }
                            }
                            a2 = null;
                            break;
                        case LOOP:
                            a2 = com.xiaoyi.camera.g.a().a("video_loop_resolution");
                            if (TextUtils.isEmpty(a2)) {
                                a2 = com.xiaoyi.camera.g.a().a("video_resolution");
                                break;
                            }
                            break;
                        default:
                            a2 = com.xiaoyi.camera.g.a().a("video_resolution");
                            break;
                    }
                    if (a2 == null || (split = a2.split(" ")) == null || split.length < 3) {
                        return null;
                    }
                    str = split[2];
                    return str;
                } catch (Exception e2) {
                    return str;
                }
            case CaptureMode:
                String[] split2 = com.xiaoyi.camera.g.a().a("photo_size").split(" ");
                if (split2.length > 2) {
                    return split2[2].substring(0, split2[2].indexOf(")"));
                }
                Log.d("aspect_ratio", "YiActionCamera.getInstance() PHOTO_SIZE=" + split2);
                return null;
            default:
                return null;
        }
    }

    public String m() {
        String str;
        String str2;
        String[] split;
        if (this.B == null) {
            return null;
        }
        switch (this.B) {
            case RecordMode:
                String str3 = null;
                switch (this.C) {
                    case TIMELAPES:
                        if (com.xiaoyi.camera.d.a.c() || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                            str3 = com.xiaoyi.camera.g.a().a("timelapse_video_resolution");
                            break;
                        }
                        break;
                    case PHOTO:
                        str3 = com.xiaoyi.camera.g.a().a("video_photo_resolution");
                        break;
                    case SLOW:
                        if (TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("slow_motion_res"))) {
                            str2 = t[0];
                            String a2 = com.xiaoyi.camera.g.a().a("video_standard");
                            String a3 = com.xiaoyi.camera.g.a().a("slow_motion_rate");
                            int i2 = 0;
                            while (true) {
                                if (i2 < com.xiaoyi.camera.module.b.a("slow_motion_rate").size()) {
                                    if (!com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i2).equals(a3)) {
                                        i2++;
                                    } else if (!y.equals(a2)) {
                                        if (x.equals(a2)) {
                                            str3 = u[i2];
                                            break;
                                        }
                                    } else {
                                        str3 = t[i2];
                                        break;
                                    }
                                }
                            }
                            str3 = str2;
                            break;
                        } else {
                            String a4 = com.xiaoyi.camera.g.a().a("slow_motion_res");
                            try {
                                str3 = a4.substring(0, a4.indexOf("x", a4.indexOf("x") + 1)) + " " + a4.substring(a4.indexOf("x", a4.indexOf("x") + 1) + 1, a4.length()) + "x 16:9";
                                break;
                            } catch (Exception e2) {
                                str2 = t[0];
                                String a5 = com.xiaoyi.camera.g.a().a("video_standard");
                                String a6 = com.xiaoyi.camera.g.a().a("slow_motion_rate");
                                int i3 = 0;
                                while (true) {
                                    if (i3 < com.xiaoyi.camera.module.b.a("slow_motion_rate").size()) {
                                        if (!com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i3).equals(a6)) {
                                            i3++;
                                        } else if (!y.equals(a5)) {
                                            if (x.equals(a5)) {
                                                str3 = u[i3];
                                                break;
                                            }
                                        } else {
                                            str3 = t[i3];
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    case LOOP:
                        str3 = com.xiaoyi.camera.g.a().a("video_loop_resolution");
                        if (TextUtils.isEmpty(str3)) {
                            str3 = com.xiaoyi.camera.g.a().a("video_resolution");
                            break;
                        }
                        break;
                    default:
                        str3 = com.xiaoyi.camera.g.a().a("video_resolution");
                        break;
                }
                if (TextUtils.isEmpty(str3) || (split = str3.split(" ")) == null || split.length <= 0) {
                    return "";
                }
                if (!split[0].contains(e) && !split[0].contains(g) && !split[0].contains(i) && !split[0].contains(j) && !split[0].contains(k) && !split[0].contains(l) && !split[0].contains(n) && !split[0].contains(p)) {
                    String[] split2 = split[0].split("x");
                    if (split2 != null && split2.length == 2) {
                        split[0] = split2[1] + b;
                    }
                } else if (split[0].contains(e)) {
                    split[0] = f;
                } else if (split[0].contains(g)) {
                    split[0] = h;
                } else if (split[0].contains(i) || split[0].contains(j) || split[0].contains(k) || split[0].contains(l)) {
                    split[0] = m;
                } else if (split[0].contains(n)) {
                    split[0] = o;
                } else if (split[0].contains(p)) {
                    split[0] = q;
                }
                int length = split.length;
                return length == 3 ? split[0] + " / " + split[1].replace(b, "") : length == 4 ? split[3].equalsIgnoreCase(f4814a) ? split[0] + " " + d + " / " + split[1].replace(b, "") : split[0] + " / " + split[1].replace(b, "") : length == 2 ? split[0] + " / " + split[1] : split[0];
            case CaptureMode:
                String a7 = com.xiaoyi.camera.g.a().a("photo_size");
                if (a7 == null) {
                    g.a("aspect_ratio", "PHOTO_RESOLUTION error", new Object[0]);
                    return "";
                }
                String[] split3 = a7.split(" ");
                if (split3 == null || split3.length <= 0) {
                    str = "";
                } else {
                    if (!split3[0].contains(s) && split3[0].contains(r)) {
                        split3[0] = split3[0].replace(r, s);
                    }
                    if (split3.length == 4) {
                        String[] split4 = split3[3].split(":");
                        str = (split4 == null || split4.length != 2) ? split3[0] : split3[0] + " " + split4[1];
                    } else {
                        str = split3[0];
                    }
                }
                return str;
            default:
                return "";
        }
    }

    public CameraMode n() {
        return this.B;
    }

    public Constant.CaptureMode o() {
        return this.D;
    }

    public Constant.RecordMode p() {
        return this.C;
    }

    public int q() {
        String[] split;
        try {
            String a2 = com.xiaoyi.camera.g.a().a("timelapse_video_duration");
            if (TextUtils.isEmpty(a2) || a2.equals("off") || !a2.contains("s")) {
                return 0;
            }
            String substring = a2.substring(0, a2.indexOf("s"));
            String option = p().getOption();
            String a3 = com.xiaoyi.camera.g.a().a("timelapse_video_resolution");
            if (TextUtils.isEmpty(a3) || (split = a3.split(" ")) == null || split.length <= 2) {
                return 0;
            }
            return (int) (Float.valueOf(option).floatValue() * Float.valueOf(substring).floatValue() * Integer.parseInt(split[1].replace("P", "")));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public boolean r() {
        return this.E;
    }

    public boolean s() {
        return this.F;
    }

    public void t() {
        com.xiaoyi.camera.g.a().d("app_status", this);
    }

    public void u() {
        f();
        a(false);
        N();
        c();
        this.z = null;
    }

    public void v() {
        if (this.A != null) {
            this.A.d(true);
        }
    }

    public void w() {
        if (this.A != null) {
            this.A.d(false);
        }
    }

    public void x() {
        if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
            com.xiaoyi.camera.g.a().k(new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.controller.CameraMainController.9
                @Override // com.xiaoyi.camera.c.a
                public void a(d dVar, JSONObject jSONObject) {
                    CameraMainController.this.L.postDelayed(CameraMainController.this.N, 1000L);
                }

                @Override // com.xiaoyi.camera.c.a
                public void b(d dVar, JSONObject jSONObject) {
                    CameraMainController.this.b(false);
                }
            });
        } else {
            this.L.post(this.N);
        }
    }

    public void y() {
        if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
            com.xiaoyi.camera.g.a().d("warp_enable", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.controller.CameraMainController.11
                @Override // com.xiaoyi.camera.c.a
                public void a(d dVar, JSONObject jSONObject) {
                    g.a(BuildConfig.BUILD_TYPE, "---------------------------- WARP_ENABLE = " + jSONObject.toString(), new Object[0]);
                    if (jSONObject == null || !jSONObject.isNull("warp_enable")) {
                        return;
                    }
                    com.xiaoyi.camera.g.a().a("warp_enable", jSONObject.optString("param"));
                    CameraMainController.this.b(true);
                }

                @Override // com.xiaoyi.camera.c.a
                public void b(d dVar, JSONObject jSONObject) {
                }
            });
        } else if ("Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            b(true);
        }
    }

    public void z() {
        b(true);
    }
}
