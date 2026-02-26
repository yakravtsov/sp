package com.xiaomi.xy.sportscamera.camera.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.activity.BaseConfigActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.activity.CameraConnectionFailedActivity;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.album.CameraAlbumActivity;
import com.ants360.z13.album.CameraMediaShowActivity;
import com.ants360.z13.b.a;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.fragment.SDCardRemoveDialogFragment;
import com.ants360.z13.util.StatisticHelper;
import com.ants360.z13.util.q;
import com.ants360.z13.util.w;
import com.ants360.z13.widget.CustomBatteryLoading;
import com.ants360.z13.widget.SudokuView;
import com.ants360.z13.widget.TimelapsSeekBar;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.greenbamboo.prescholleducation.MediaFramework.CameraMediaPlayer;
import com.sina.weibo.sdk.constant.WBConstants;
import com.video.draw.PlayerRenderer;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.d;
import com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity;
import com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity;
import com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker;
import com.xiaomi.xy.sportscamera.camera.widget.SettingItemViewPager;
import com.xiaomi.xy.sportscamera.camera.widget.a;
import com.xiaomi.xy.sportscamera.camera.widget.b;
import com.xiaoyi.camera.b.b;
import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.e;
import com.xiaoyi.camera.module.FileItem;
import com.xiaoyi.camera.module.PhotoFileItem;
import com.xiaoyi.camera.module.VideoFileItem;
import com.xiaoyi.player.NetworkUtil;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import wseemann.media.BuildConfig;
import wseemann.media.FFmpegMediaMetadataRetriever;

@SuppressLint({"InflateParams"})
/* loaded from: classes3.dex */
public class CameraActivity extends BaseConfigActivity implements View.OnClickListener, HorizontalPicker.b, a.InterfaceC0204a, b.a, CameraMainController.a {
    private View A;
    private View B;
    private ImageView C;
    private RelativeLayout D;
    private TextView E;
    private TextView F;
    private TextView G;
    private HorizontalPicker H;
    private LinearLayout I;
    private LinearLayout J;
    private LinearLayout K;
    private RelativeLayout L;
    private ImageView M;
    private RelativeLayout N;
    private RelativeLayout O;
    private RelativeLayout P;
    private ImageView Q;
    private SettingItemViewPager R;
    private LinearLayout S;
    private RelativeLayout T;
    private ImageView U;
    private ImageView V;
    private ImageView W;
    private LinearLayout X;
    private RelativeLayout Y;
    private ImageView Z;
    private String aC;
    private String aD;
    private String aE;
    private String aF;
    private View aH;
    private LinearLayout aI;
    private ImageView aJ;
    private CustomBatteryLoading aK;
    private TextView aL;
    private boolean aM;
    private boolean aN;
    private int aO;
    private boolean aQ;
    private int aR;
    private Timer aS;
    private SDCardRemoveDialogFragment aV;
    private boolean aZ;
    private ImageView aa;
    private ImageView ab;
    private RelativeLayout ac;
    private TextView ad;
    private RelativeLayout ae;
    private TextView af;
    private TimelapsSeekBar ag;
    private RelativeLayout ah;
    private LinearLayout ai;
    private LinearLayout aj;
    private LinearLayout ak;
    private CameraMainController al;
    private CameraMediaPlayer am;
    private PlayerRenderer an;
    private com.xiaomi.xy.sportscamera.camera.widget.a ao;
    private com.xiaomi.xy.sportscamera.camera.widget.b ap;
    private CustomBottomDialogFragment aq;
    private CustomBottomDialogFragment ar;
    private Typeface as;
    private Typeface at;
    public String[] b;
    private b ba;
    private String bg;
    public String[] c;
    public String[] d;
    public String[] e;
    private LinearLayout o;
    private ImageView p;
    private ImageView q;
    private ImageView r;
    private ImageView s;
    private RelativeLayout t;
    private TextView u;
    private SudokuView v;
    private RelativeLayout w;
    private GLSurfaceView x;
    private TextView y;
    private ProgressBar z;
    private final int f = 0;
    private final int g = 1;
    private final int h = 2;
    private final int i = 3;
    private final int j = 4;
    private final int k = 0;
    private final int l = 1;
    private final int m = 2;
    private final int n = 3;
    private int au = 0;
    private int av = 0;
    private int aw = 0;
    private int ax = 0;
    private int ay = 0;
    private int az = 0;
    private int aA = 0;
    private boolean aB = false;
    private int aG = 0;
    private int aP = -1;
    private int aT = 0;
    private int aU = 0;
    private String aW = null;
    private String aX = "16:9";
    private String aY = "4:3";
    private final int bb = 10000;
    private final int bc = 20000;
    private c bd = new c(Looper.getMainLooper());
    private boolean be = true;
    private BroadcastReceiver bf = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.50
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = null;
            String action = intent.getAction();
            if (com.xiaoyi.camera.a.a("sd_card_status").equals(action)) {
                try {
                    str = intent.getStringExtra("current_operation_model");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (CameraActivity.this.a()) {
                    if ("insert".equals(str)) {
                        CameraActivity.this.a(CameraActivity.this.getResources().getString(R.string.sd_card_inserd));
                        if (CameraActivity.this.aV == null || CameraActivity.this.aV.getDialog() == null) {
                            return;
                        }
                        CameraActivity.this.aV.dismiss();
                        return;
                    }
                    if (ProductAction.ACTION_REMOVE.equals(str)) {
                        if (CameraActivity.this.aV == null || CameraActivity.this.aV.getDialog() == null || !CameraActivity.this.aV.getDialog().isShowing()) {
                            CameraActivity.this.aV = new SDCardRemoveDialogFragment();
                            CameraActivity.this.aV.a(CameraActivity.this);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                CameraActivity.this.C();
                CameraActivity.this.d(false, false, false);
                CameraActivity.this.a(true, CameraActivity.this.getString(R.string.camera_album_operation_ing));
                if (CameraActivity.this.ah != null) {
                    CameraActivity.this.a(false, false, false, false);
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_album").equals(action)) {
                CameraActivity.this.d(false, true, true);
                CameraActivity.this.a(false, (String) null);
                CameraActivity.this.x.setVisibility(0);
                CameraActivity.this.al.j();
                if (!CameraActivity.this.al.B() && CameraActivity.this.al.d()) {
                    CameraActivity.this.c(true);
                }
                String stringExtra = intent.getStringExtra("param");
                if (stringExtra != null) {
                    try {
                        if (stringExtra.length() <= 0 || Integer.parseInt(stringExtra) <= 0) {
                            return;
                        }
                        CameraActivity.this.q();
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_format_done").equals(action)) {
                CameraActivity.this.a(CameraActivity.this.getResources().getString(R.string.sd_card_format_done));
                CameraActivity.this.q();
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_full").equals(action)) {
                CameraActivity.this.a(CameraActivity.this.getResources().getString(R.string.no_more_space));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_io_error").equals(action)) {
                CameraActivity.this.a(CameraActivity.this.getResources().getString(R.string.sd_card_error));
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_fwupdate").equals(action)) {
                CameraActivity.this.a(true, CameraActivity.this.getString(R.string.firmware_update_info));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_fwupdate").equals(action)) {
                CameraActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_sdformat").equals(action)) {
                CameraActivity.this.a(true, CameraActivity.this.getString(R.string.sd_card_format));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_sdformat").equals(action)) {
                CameraActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_mvrecover").equals(action)) {
                CameraActivity.this.a(true, CameraActivity.this.getString(R.string.video_file_recovery));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_mvrecover").equals(action)) {
                CameraActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_start").equals(action)) {
                CameraActivity.this.a(true, CameraActivity.this.getString(R.string.camera_sdcard_optimize_start));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_stop").equals(action)) {
                CameraActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_usb_storage").equals(action)) {
                CameraActivity.this.a(true, CameraActivity.this.getString(R.string.camera_start_usb_storage_mode));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_usb_storage").equals(action)) {
                CameraActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_start").equals(action)) {
                CameraActivity.this.a(true, CameraActivity.this.getString(R.string.camera_voice_control));
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_stop").equals(action)) {
                CameraActivity.this.a(false, (String) null);
            } else if (com.xiaoyi.camera.a.a("enter_live").equals(action)) {
                CameraActivity.this.s();
            } else if (com.xiaoyi.camera.a.a("exit_live").equals(action)) {
                CameraActivity.this.a(false, (String) null);
            }
        }
    };
    private boolean bh = false;
    private boolean bi = false;
    private boolean bj = false;
    private boolean bk = false;
    private boolean bl = false;
    private boolean bm = true;
    private Runnable bn = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.45
        @Override // java.lang.Runnable
        public void run() {
            CameraActivity.this.L();
        }
    };

    /* loaded from: classes3.dex */
    public class a extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
        private int b = 100;
        private int c = 200;
        private GestureDetector d;

        public a(Context context) {
            this.d = new GestureDetector(context, this);
        }

        public boolean a() {
            return false;
        }

        public boolean b() {
            return false;
        }

        public void c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent == null || motionEvent2 == null) {
                return false;
            }
            if (motionEvent.getX() - motionEvent2.getX() > this.b && Math.abs(f) > this.c) {
                CameraActivity.this.bh = true;
                a();
            }
            if (motionEvent2.getX() - motionEvent.getX() <= this.b || Math.abs(f) <= this.c) {
                return false;
            }
            CameraActivity.this.bh = true;
            b();
            return false;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.d.onTouchEvent(motionEvent);
            switch (motionEvent.getAction()) {
                case 1:
                    c();
                    return false;
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class b extends a {
        public b(Context context) {
            super(context);
        }

        @Override // com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.a
        public boolean a() {
            if (CameraActivity.this.al != null && !CameraActivity.this.al.s() && !CameraActivity.this.al.r()) {
                CameraActivity.this.H.setVisibility(0);
                CameraActivity.this.G.setVisibility(0);
                CameraActivity.this.H.a(1);
                g.a(BuildConfig.BUILD_TYPE, "向左滑", new Object[0]);
                if (CameraActivity.this.aB) {
                    CameraActivity.this.bd.removeCallbacks(CameraActivity.this.bn);
                    CameraActivity.this.bd.postDelayed(CameraActivity.this.bn, 3000L);
                }
            }
            return super.a();
        }

        @Override // com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.a
        public boolean b() {
            if (CameraActivity.this.al != null && !CameraActivity.this.al.s() && !CameraActivity.this.al.r()) {
                CameraActivity.this.H.setVisibility(0);
                CameraActivity.this.G.setVisibility(0);
                CameraActivity.this.H.a(-1);
                g.a(BuildConfig.BUILD_TYPE, "向右滑", new Object[0]);
                if (CameraActivity.this.aB) {
                    CameraActivity.this.bd.removeCallbacks(CameraActivity.this.bn);
                    CameraActivity.this.bd.postDelayed(CameraActivity.this.bn, 3000L);
                }
            }
            return super.b();
        }

        @Override // com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.a
        public void c() {
            super.c();
            if (!CameraActivity.this.bh) {
                CameraActivity.this.L();
                CameraActivity.this.bd.removeCallbacks(CameraActivity.this.bn);
                if (CameraActivity.this.aB && CameraActivity.this.G.getVisibility() == 8 && CameraActivity.this.H.getVisibility() == 8) {
                    CameraActivity.this.bd.postDelayed(CameraActivity.this.bn, 3000L);
                }
            }
            CameraActivity.this.bh = false;
        }
    }

    /* loaded from: classes3.dex */
    public class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 10000:
                    CameraActivity.this.ag.setProgress(CameraActivity.this.aU);
                    if (CameraActivity.this.aU <= CameraActivity.this.aT) {
                        CameraActivity.this.aU++;
                        return;
                    }
                    return;
                case 20000:
                    CameraActivity.this.ag.setVisibility(8);
                    CameraActivity.this.ag.setProgress(0);
                    CameraActivity.this.aT = 0;
                    CameraActivity.this.aU = 0;
                    CameraActivity.this.F.setText("00:00");
                    CameraActivity.this.E.setText("00:00");
                    CameraActivity.this.D.setVisibility(8);
                    if (CameraActivity.this.aS != null) {
                        CameraActivity.this.aS.cancel();
                        CameraActivity.this.aS = null;
                        CameraActivity.this.aT = 0;
                        CameraActivity.this.aU = 0;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.16
            @Override // java.lang.Runnable
            public void run() {
                CameraActivity.this.G.setText(CameraActivity.this.al.m());
                CameraActivity.this.O.setVisibility(0);
                CameraActivity.this.R.setVisibility(0);
                if (CameraActivity.this.al == null || CameraActivity.this.al.g() == null) {
                    return;
                }
                switch (AnonymousClass46.c[CameraActivity.this.al.g().ordinal()]) {
                    case 1:
                        CameraActivity.this.R.a(e.a());
                        switch (AnonymousClass46.f4419a[CameraActivity.this.al.p().ordinal()]) {
                            case 1:
                                CameraActivity.this.aP = 1;
                                CameraActivity.this.R.setSelectedItem(1);
                                break;
                            case 2:
                                CameraActivity.this.aP = 3;
                                CameraActivity.this.R.setSelectedItem(3);
                                break;
                            case 3:
                                CameraActivity.this.aP = 2;
                                CameraActivity.this.R.setSelectedItem(2);
                                break;
                            case 4:
                                CameraActivity.this.aP = 4;
                                CameraActivity.this.R.setSelectedItem(4);
                                break;
                            case 5:
                                CameraActivity.this.aP = 0;
                                CameraActivity.this.R.setSelectedItem(0);
                                break;
                            default:
                                CameraActivity.this.aP = 0;
                                CameraActivity.this.R.setSelectedItem(0);
                                break;
                        }
                    case 2:
                        CameraActivity.this.R.a(e.b());
                        switch (AnonymousClass46.b[CameraActivity.this.al.o().ordinal()]) {
                            case 1:
                                CameraActivity.this.aP = 1;
                                CameraActivity.this.R.setSelectedItem(1);
                                break;
                            case 2:
                                CameraActivity.this.aP = 3;
                                CameraActivity.this.R.setSelectedItem(3);
                                break;
                            case 3:
                                CameraActivity.this.aP = 2;
                                CameraActivity.this.R.setSelectedItem(2);
                                break;
                            case 4:
                                CameraActivity.this.aP = 0;
                                CameraActivity.this.R.setSelectedItem(0);
                                break;
                            default:
                                CameraActivity.this.aP = 0;
                                CameraActivity.this.R.setSelectedItem(0);
                                break;
                        }
                }
                CameraActivity.this.al.z();
                CameraActivity.this.B();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        if (this.al == null || this.al.g() == null) {
            return;
        }
        switch (this.al.g()) {
            case RecordMode:
                String a2 = com.xiaoyi.camera.g.a().a("video_shutter");
                if (!"on".equals(com.xiaoyi.camera.g.a().a("video_shutter_support")) || TextUtils.isEmpty(a2) || "auto".equalsIgnoreCase(a2)) {
                    this.ae.setVisibility(8);
                    return;
                }
                this.ae.setVisibility(0);
                this.af.setText(getString(R.string.camera_shutter_remind, new Object[]{a2}));
                if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    this.af.setTextColor(getResources().getColor(R.color.white));
                    return;
                } else {
                    this.af.setTextColor(getResources().getColor(R.color.primary_grey));
                    return;
                }
            case CaptureMode:
                String a3 = com.xiaoyi.camera.g.a().a("iq_photo_shutter");
                switch (this.al.o()) {
                    case TIMER:
                    case NORMAL:
                        break;
                    case TIMELAPES:
                        a3 = com.xiaoyi.camera.g.a().a("timelapse_photo_shutter");
                        break;
                    case BURST:
                    default:
                        this.ae.setVisibility(8);
                        return;
                }
                if (TextUtils.isEmpty(a3) || "auto".equalsIgnoreCase(a3)) {
                    this.ae.setVisibility(8);
                    return;
                }
                this.ae.setVisibility(0);
                this.af.setText(getString(R.string.camera_shutter_remind, new Object[]{a3}));
                if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    this.af.setTextColor(getResources().getColor(R.color.white));
                    return;
                } else {
                    this.af.setTextColor(getResources().getColor(R.color.primary_grey));
                    return;
                }
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        if (this.ao == null || !this.ao.isShowing()) {
            return;
        }
        this.ao.dismiss();
    }

    private void D() {
        if (this.aq == null || this.aq.getDialog() == null || !this.aq.getDialog().isShowing()) {
            return;
        }
        this.aq.dismiss();
    }

    private void E() {
        if (this.ar == null || this.ar.getDialog() == null || !this.ar.getDialog().isShowing()) {
            return;
        }
        this.ar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        if (this.C.getAnimation() != null) {
            return;
        }
        this.C.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, 1, 0.5f, 1, 0.5f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(200L);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.23
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CameraActivity.this.G();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.C.startAnimation(animationSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(200L);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.24
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CameraActivity.this.C.clearAnimation();
                CameraActivity.this.C.setVisibility(8);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.C.startAnimation(animationSet);
    }

    private void H() {
        switch (this.al.g()) {
            case RecordMode:
                switch (this.al.p()) {
                    case TIMELAPES:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_video_time_elapse");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_video_time_elapse");
                        StatisticHelper.j("Timelapse");
                        return;
                    case LOOP:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_loop_record");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_loop_record");
                        StatisticHelper.j("Loop");
                        return;
                    case SLOW:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_slow_record");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_slow_record");
                        StatisticHelper.j("Slow");
                        return;
                    case PHOTO:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_video_photo");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_video_photo");
                        StatisticHelper.j("Photo");
                        return;
                    case NORMAL:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_video");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_video");
                        StatisticHelper.j("Normal");
                        return;
                    default:
                        return;
                }
            case CaptureMode:
                switch (this.al.o()) {
                    case TIMER:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo_timer");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_photo_timer");
                        StatisticHelper.i("Timer");
                        return;
                    case TIMELAPES:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo_time_elapse");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_photo_time_elapse");
                        StatisticHelper.i("Timelapse");
                        return;
                    case BURST:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo_burst");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_photo_burst");
                        StatisticHelper.i("Burst");
                        return;
                    case NORMAL:
                        UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo");
                        UploadStatsManager.a("camera_operation_start", "camera_operation_photo");
                        StatisticHelper.i("Normal");
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    private void I() {
        if (this.aB) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
            translateAnimation.setDuration(500L);
            translateAnimation.setFillAfter(true);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.27
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CameraActivity.this.bl = true;
                    CameraActivity.this.J();
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.S.startAnimation(translateAnimation);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500L);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.28
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.U.startAnimation(alphaAnimation);
            this.V.startAnimation(alphaAnimation);
            this.Z.startAnimation(alphaAnimation);
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(500L);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.29
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.U.startAnimation(scaleAnimation);
            TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            translateAnimation2.setDuration(500L);
            translateAnimation2.setFillAfter(true);
            translateAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.30
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.X.startAnimation(translateAnimation2);
            this.ab.setVisibility(0);
            this.W.setVisibility(0);
            this.aa.setVisibility(0);
            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation2.setDuration(500L);
            alphaAnimation2.setFillAfter(true);
            alphaAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.31
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.ab.startAnimation(alphaAnimation2);
            this.W.startAnimation(alphaAnimation2);
            this.aa.startAnimation(alphaAnimation2);
            ScaleAnimation scaleAnimation2 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation2.setDuration(500L);
            scaleAnimation2.setFillAfter(true);
            scaleAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.32
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.ab.startAnimation(scaleAnimation2);
            return;
        }
        TranslateAnimation translateAnimation3 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation3.setDuration(500L);
        translateAnimation3.setFillAfter(true);
        translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.34
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CameraActivity.this.bk = true;
                CameraActivity.this.J();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.T.startAnimation(translateAnimation3);
        AlphaAnimation alphaAnimation3 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation3.setDuration(500L);
        alphaAnimation3.setFillAfter(true);
        alphaAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.35
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.U.startAnimation(alphaAnimation3);
        this.V.startAnimation(alphaAnimation3);
        this.Z.startAnimation(alphaAnimation3);
        ScaleAnimation scaleAnimation3 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation3.setDuration(500L);
        scaleAnimation3.setFillAfter(true);
        scaleAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.36
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.U.startAnimation(scaleAnimation3);
        TranslateAnimation translateAnimation4 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
        translateAnimation4.setDuration(500L);
        translateAnimation4.setFillAfter(true);
        translateAnimation4.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.37
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.X.startAnimation(translateAnimation4);
        this.ab.setVisibility(0);
        this.W.setVisibility(0);
        this.aa.setVisibility(0);
        AlphaAnimation alphaAnimation4 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation4.setDuration(500L);
        alphaAnimation4.setFillAfter(true);
        alphaAnimation4.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.38
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.ab.startAnimation(alphaAnimation4);
        this.W.startAnimation(alphaAnimation4);
        this.aa.startAnimation(alphaAnimation4);
        ScaleAnimation scaleAnimation4 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation4.setDuration(500L);
        scaleAnimation4.setFillAfter(true);
        scaleAnimation4.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.39
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.ab.startAnimation(scaleAnimation4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (this.aB) {
            if (this.bi && this.bj && this.bl) {
                this.S.clearAnimation();
                this.U.clearAnimation();
                this.V.clearAnimation();
                this.Z.clearAnimation();
                this.X.clearAnimation();
                this.ab.clearAnimation();
                this.W.clearAnimation();
                this.aa.clearAnimation();
                this.ab.setVisibility(4);
                this.W.setVisibility(4);
                this.aa.setVisibility(4);
                this.bi = false;
                this.bj = false;
                this.bl = false;
                return;
            }
            return;
        }
        if (this.bi && this.bj && this.bk) {
            g.a("debug_command", "-------------------------------------------- setSwitchAnimationFin", new Object[0]);
            this.T.clearAnimation();
            this.U.clearAnimation();
            this.V.clearAnimation();
            this.Z.clearAnimation();
            this.X.clearAnimation();
            this.ab.clearAnimation();
            this.W.clearAnimation();
            this.aa.clearAnimation();
            this.ab.setVisibility(4);
            this.W.setVisibility(4);
            this.aa.setVisibility(4);
            this.bi = false;
            this.bj = false;
            this.bk = false;
        }
    }

    private boolean K() {
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            long j = memoryInfo.availMem;
            g.a(BuildConfig.BUILD_TYPE, "----------------- mi.availMem = " + j, new Object[0]);
            return j > 100000000;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        if (this.aB && this.bm) {
            this.bm = false;
            if (this.G.getVisibility() == 0 && this.H.getVisibility() == 0) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.layout_bottom_out);
                loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.40
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        CameraActivity.this.G.setVisibility(8);
                        CameraActivity.this.bm = true;
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }
                });
                this.G.startAnimation(loadAnimation);
                Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.layout_top_out);
                loadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.41
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        CameraActivity.this.H.setVisibility(8);
                        CameraActivity.this.bm = true;
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }
                });
                this.H.startAnimation(loadAnimation2);
                return;
            }
            Animation loadAnimation3 = AnimationUtils.loadAnimation(this, R.anim.layout_bottom_in);
            loadAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.42
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CameraActivity.this.G.setVisibility(0);
                    CameraActivity.this.bm = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.G.startAnimation(loadAnimation3);
            Animation loadAnimation4 = AnimationUtils.loadAnimation(this, R.anim.layout_top_in);
            loadAnimation4.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.43
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CameraActivity.this.H.setVisibility(0);
                    CameraActivity.this.bm = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            this.H.startAnimation(loadAnimation4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str) {
        if (z) {
            this.t.setVisibility(0);
            this.u.setText(str);
        } else {
            this.t.setVisibility(8);
            this.u.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2, boolean z3, boolean z4) {
        this.ah.setVisibility(z ? 0 : 8);
        this.ai.setVisibility(z2 ? 0 : 4);
        this.aj.setVisibility(z3 ? 0 : 4);
        this.ak.setVisibility(z4 ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, boolean z2, boolean z3) {
        this.Q.setEnabled(z);
        this.T.setEnabled(z2);
        this.O.setEnabled(z3);
        this.M.setEnabled(z3);
        this.Y.setEnabled(z3);
        this.Z.setEnabled(z3);
        this.H.setEnabled(z3);
        this.x.setOnTouchListener(z3 ? this.ba : null);
        this.p.setEnabled(z3);
        this.q.setEnabled(z3);
        this.s.setEnabled(z3);
        ImageView imageView = this.r;
        if (this.al == null || !this.al.A()) {
            z3 = false;
        }
        imageView.setEnabled(z3);
    }

    private String f(int i) {
        switch (i) {
            case -1005:
                return getString(R.string.camera_start_fw_update);
            case IMediaPlayer.MEDIA_ERROR_IO /* -1004 */:
                return getString(R.string.camera_start_usb_storage_mode);
            case -1003:
                return getString(R.string.sd_hc);
            case -1002:
                return getString(R.string.no_more_space);
            case -1001:
                return getString(R.string.sd_card_remove);
            case NotificationManagerCompat.IMPORTANCE_UNSPECIFIED /* -1000 */:
                return getString(R.string.no_sd_card);
            case -31:
                return getString(R.string.low_level_sd_card);
            case -28:
                return "sdCardNeedFormat";
            case -27:
                String string = getString(R.string.no_sd_card);
                j();
                return string;
            case -25:
                return getString(R.string.system_busy);
            case -21:
                return getString(R.string.system_busy);
            case -20:
                return getString(R.string.piv_not_allowed);
            case -17:
                return getString(R.string.no_more_space);
            default:
                return getString(R.string.operation_failed);
        }
    }

    private void f(String str) {
        i.a().a("video_photo_path", str);
        if (this.aB) {
            return;
        }
        this.aW = str;
        PhotoFileItem photoFileItem = new PhotoFileItem();
        photoFileItem.setType("thumb");
        photoFileItem.setPath(str);
        photoFileItem.setHttpThumbUrl(str.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/") + "?type=thumb");
        photoFileItem.setHttpPath(str.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/"));
        this.M.setTag(R.integer.camera_imageview_key, str);
        com.xiaoyi.camera.b.a.a(this.al);
        com.xiaomi.xy.sportscamera.camera.c.a().a("thumb");
        com.yiaction.common.imageloader.i.a(this, photoFileItem.getHttpThumbUrl(), this.M, R.drawable.pic_default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        String str2;
        i.a().a("video_photo_path", str);
        if (this.aB || TextUtils.isEmpty(str)) {
            return;
        }
        VideoFileItem videoFileItem = new VideoFileItem();
        if ("Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            this.aW = str;
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null) {
                    jSONObject.optString("param");
                    String optString = jSONObject.optString("sub");
                    videoFileItem.setName(optString.substring(optString.lastIndexOf("/") + 1, optString.length()));
                    videoFileItem.setPath(optString);
                    videoFileItem.setSize(jSONObject.optLong("ssize"));
                    videoFileItem.setHttpPath(optString.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/"));
                    if (com.xiaoyi.camera.d.a.a()) {
                        str2 = optString.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/");
                        if (str2.endsWith("_thm.mp4") || str2.endsWith("_thm.MP4")) {
                            str2 = str2.replace("_thm.mp4", ".THM").replace("_thm.MP4", ".THM");
                        } else if (str2.endsWith(".sec") || str2.endsWith(".SEC")) {
                            str2 = str2.replace(".sec", ".THM").replace(".SEC", ".THM");
                        }
                    } else {
                        str2 = optString.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/") + "?type=thumb";
                    }
                    videoFileItem.setHttpThumbUrl(str2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
            videoFileItem.setType("idr");
            videoFileItem.setName(str.substring(str.lastIndexOf("/") + 1, str.length()));
            videoFileItem.setPath(str);
            videoFileItem.setHttpPath(str.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/"));
            videoFileItem.setHttpThumbUrl(com.xiaoyi.camera.d.a.a() ? str.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/").replace(".mp4", ".THM").replace(".MP4", ".THM") : str.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/") + "?type=thumb");
            if (this.al.p().equals(Constant.RecordMode.TIMELAPES) && com.xiaoyi.camera.d.a.c()) {
                videoFileItem.setVideoAspectRatio(com.xiaoyi.camera.g.a().a("timelapse_video_resolution"));
            } else {
                videoFileItem.setVideoAspectRatio(com.xiaoyi.camera.g.a().a("video_resolution"));
            }
            videoFileItem.setDuration(this.aG);
        }
        if (videoFileItem != null) {
            this.M.setTag(R.integer.camera_imageview_key, videoFileItem.getPath());
            com.xiaoyi.camera.b.a.a(this.al);
            com.xiaomi.xy.sportscamera.camera.c.a().a("thumb");
            com.xiaomi.xy.sportscamera.camera.c.a().a("idr", videoFileItem, this.M, new b.a() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.18
                @Override // com.xiaoyi.camera.b.b.a
                public void a(Bitmap bitmap) {
                    CameraActivity.this.M.setVisibility(0);
                    if (bitmap != null) {
                        CameraActivity.this.M.setImageBitmap(bitmap);
                    } else {
                        CameraActivity.this.q();
                    }
                }
            });
        }
    }

    private void t() {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("middle_button", getString(R.string.agree));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.smuggle_tip));
        bundle.putBoolean("one_button", true);
        CustomBottomDialogFragment customBottomDialogFragment = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        customBottomDialogFragment.a(new DimPanelFragment.a() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.12
            @Override // com.ants360.z13.fragment.DimPanelFragment.a
            public void a(DialogFragment dialogFragment) {
                CameraActivity.this.u();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.a
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.u();
            }
        });
        customBottomDialogFragment.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.CHINA;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (ProductAction.ACTION_REMOVE.equals(com.xiaoyi.camera.g.a().a("sd_card_status"))) {
            if (this.aV == null || this.aV.getDialog() == null || !this.aV.getDialog().isShowing()) {
                this.aV = new SDCardRemoveDialogFragment();
                this.aV.a(this);
                return;
            }
            return;
        }
        if (i.a().b("already_remind_upgrade", false)) {
            return;
        }
        i.a().a("already_remind_upgrade", true);
        String a2 = com.xiaoyi.camera.g.a().a("model");
        this.aC = com.xiaoyi.camera.g.a().a("serial_number");
        this.aE = com.xiaoyi.camera.g.a().a("sw_version");
        d a3 = d.a();
        this.aF = a3.h(a2, this.aC);
        this.aD = a3.d(this.aF);
        if (!TextUtils.isEmpty(a2) && a2.startsWith("Z13") && a3.o(this.aE)) {
            if ((com.xiaoyi.camera.module.a.c(this.aE, this.aD) || !a3.p(a2, this.aC).booleanValue()) && (com.xiaoyi.camera.module.a.c(this.aE, a3.m(a2)) || !a3.q(a2, this.aC).booleanValue())) {
                Bundle bundle = new Bundle();
                bundle.putString("title", getString(R.string.firmware_download_title));
                bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.upgrade_tips_on_version));
                bundle.putString("left_button", getString(R.string.next_time));
                bundle.putString("right_button", getString(R.string.download_at_once));
                bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
                this.ar = new CustomBottomDialogFragment();
                this.ar.setArguments(bundle);
                this.ar.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.33
                    @Override // com.ants360.z13.fragment.DimPanelFragment.c
                    public void a(DialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                        g.a("debug_event", "CameraMainControlActivity upgradeDialog post CameraStopSessionEvent", new Object[0]);
                        CameraActivity.this.g();
                        CameraActivity.this.startActivity(new Intent(CameraActivity.this, (Class<?>) CameraDeviceUpgradeActivity.class));
                        CameraActivity.this.finish();
                    }

                    @Override // com.ants360.z13.fragment.DimPanelFragment.c
                    public void b(DialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                    }

                    @Override // com.ants360.z13.fragment.DimPanelFragment.c
                    public void c(DialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                    }
                });
                this.ar.a(this);
                return;
            }
            if (this.al == null || this.al.s() || this.al.r()) {
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("title", getString(R.string.firmware_update_info));
            bundle2.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.upgrade_tips_on_ready));
            bundle2.putString("left_button", getString(R.string.next_time));
            bundle2.putString("right_button", getString(R.string.upgrade_at_once));
            bundle2.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
            this.ar = new CustomBottomDialogFragment();
            this.ar.setArguments(bundle2);
            this.ar.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.22
                @Override // com.ants360.z13.fragment.DimPanelFragment.c
                public void a(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                    CameraActivity.this.startActivity(new Intent(CameraActivity.this, (Class<?>) CameraUpgradeActivity.class));
                }

                @Override // com.ants360.z13.fragment.DimPanelFragment.c
                public void b(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                }

                @Override // com.ants360.z13.fragment.DimPanelFragment.c
                public void c(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                }
            });
            this.ar.a(this);
            return;
        }
        if (this.al == null || this.al.s() || this.al.r()) {
            return;
        }
        if ((!com.xiaoyi.camera.module.a.c(this.aE, this.aD) && a3.p(a2, this.aC).booleanValue()) || (!com.xiaoyi.camera.module.a.c(this.aE, a3.m(a2)) && a3.q(a2, this.aC).booleanValue())) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("title", getString(R.string.camera_firmware_upgrade));
            bundle3.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.upgrade_tips_on_ready));
            bundle3.putString("left_button", getString(R.string.next_time));
            bundle3.putString("right_button", getString(R.string.upgrade_at_once));
            bundle3.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
            this.ar = new CustomBottomDialogFragment();
            this.ar.setArguments(bundle3);
            this.ar.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.44
                @Override // com.ants360.z13.fragment.DimPanelFragment.c
                public void a(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                    CameraActivity.this.startActivity(new Intent(CameraActivity.this, (Class<?>) CameraUpgradeActivity.class));
                }

                @Override // com.ants360.z13.fragment.DimPanelFragment.c
                public void b(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                }

                @Override // com.ants360.z13.fragment.DimPanelFragment.c
                public void c(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                }
            });
            this.ar.a(this);
            return;
        }
        if (com.xiaoyi.camera.module.a.c(this.aE, this.aD) || a3.p(a2, this.aC).booleanValue()) {
            return;
        }
        Bundle bundle4 = new Bundle();
        bundle4.putString("title", getString(R.string.firmware_download_title));
        bundle4.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.version_download_upgrade_tips));
        String d = a3.d(this.aF);
        if (!TextUtils.isEmpty(d)) {
            bundle4.putString("version", d);
        }
        String c2 = a3.c(this.aF);
        if (!TextUtils.isEmpty(c2)) {
            bundle4.putString(ProductAction.ACTION_DETAIL, c2);
        }
        bundle4.putString("left_button", getString(R.string.next_time));
        bundle4.putString("right_button", getString(R.string.download_at_once));
        bundle4.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        this.ar = new CustomBottomDialogFragment();
        this.ar.setArguments(bundle4);
        this.ar.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.47
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                g.a("debug_event", "CameraMainControlActivity upgradeDialog post CameraStopSessionEvent", new Object[0]);
                CameraActivity.this.g();
                CameraActivity.this.startActivity(new Intent(CameraActivity.this, (Class<?>) CameraDeviceUpgradeActivity.class));
                CameraActivity.this.finish();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }
        });
        this.ar.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.al != null) {
            this.al.t();
        }
        z();
        A();
        if ("enter_album".equals(com.xiaoyi.camera.g.a().a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM))) {
            d(false, false, false);
            a(true, getString(R.string.camera_album_operation_ing));
        } else {
            d(false, true, true);
        }
        String a2 = com.xiaoyi.camera.g.a().a("battery");
        if (a2 != null && a2.length() > 0 && Integer.parseInt(a2) > 0) {
            i(true, true, a2);
        }
        this.al.k();
        if (this.be) {
            String a3 = i.a().a("video_photo_path");
            if (TextUtils.isEmpty(a3)) {
                q();
            } else if (a3.endsWith(".jpg") || a3.endsWith(".JPG")) {
                f(a3);
            } else {
                g(a3);
            }
            this.be = false;
        }
        this.al.j();
    }

    private void x() {
        this.aP = -1;
        switch (this.al.g()) {
            case RecordMode:
                this.W.setImageResource(R.drawable.icon_video_green);
                this.aa.setImageResource(R.drawable.icon_camera_white);
                p();
                Constant.CaptureMode o = this.al.o();
                if (o != null) {
                    a(o);
                } else {
                    a(Constant.CaptureMode.NORMAL);
                }
                StatisticHelper.h("Picture");
                return;
            case CaptureMode:
                this.W.setImageResource(R.drawable.icon_camera_green);
                this.aa.setImageResource(R.drawable.icon_video_white);
                n();
                Constant.RecordMode p = this.al.p();
                if (p != null) {
                    a(p);
                } else {
                    a(Constant.RecordMode.NORMAL);
                }
                StatisticHelper.h("Video");
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.H != null) {
            this.H.setVisibility(0);
        }
        if (this.G != null) {
            this.G.setVisibility(0);
        }
        if (this.aB) {
            this.bd.removeCallbacks(this.bn);
            this.bd.postDelayed(this.bn, 3000L);
        }
        this.V.setImageResource(R.drawable.icon_camera_white);
        this.Z.setImageResource(R.drawable.icon_video_green);
        if ("Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            this.H.setValues(this.c);
        } else if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
            this.H.setValues(this.b);
        }
        switch (this.al.o()) {
            case TIMER:
                this.H.setSelectedItem(1);
                this.C.setImageResource(R.drawable.show_photo_timer);
                return;
            case TIMELAPES:
                this.H.setSelectedItem(3);
                this.C.setImageResource(R.drawable.show_photo_timelapse);
                return;
            case BURST:
                this.H.setSelectedItem(2);
                this.C.setImageResource(R.drawable.show_photo_burst);
                return;
            default:
                this.H.setSelectedItem(0);
                this.C.setImageResource(R.drawable.show_photo_normal);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.15
            @Override // java.lang.Runnable
            public void run() {
                String a2 = com.xiaoyi.camera.g.a().a("model");
                if (CameraActivity.this.H != null) {
                    CameraActivity.this.H.setVisibility(0);
                }
                if (CameraActivity.this.G != null) {
                    CameraActivity.this.G.setVisibility(0);
                }
                if (CameraActivity.this.aB) {
                    CameraActivity.this.bd.removeCallbacks(CameraActivity.this.bn);
                    CameraActivity.this.bd.postDelayed(CameraActivity.this.bn, 3000L);
                }
                if (CameraActivity.this.al == null || CameraActivity.this.al.g() == null) {
                    return;
                }
                switch (AnonymousClass46.c[CameraActivity.this.al.g().ordinal()]) {
                    case 1:
                        CameraActivity.this.V.setImageResource(R.drawable.icon_video_white);
                        CameraActivity.this.Z.setImageResource(R.drawable.icon_camera_green);
                        if ("Z16".equals(a2) || "Z18".equals(a2) || "J11".equals(a2) || "J22".equals(a2)) {
                            CameraActivity.this.H.setValues(CameraActivity.this.e);
                        } else {
                            CameraActivity.this.H.setValues(CameraActivity.this.d);
                        }
                        g.a(BuildConfig.BUILD_TYPE, "------------------- HorizontalPicker Z16_VIDEO_MODEL setValues = " + CameraActivity.this.e.length, new Object[0]);
                        g.a(BuildConfig.BUILD_TYPE, "------------------- HorizontalPicker Z13_VIDEO_MODEL setValues = " + CameraActivity.this.d.length, new Object[0]);
                        switch (AnonymousClass46.f4419a[CameraActivity.this.al.p().ordinal()]) {
                            case 1:
                                CameraActivity.this.H.setSelectedItem(1);
                                return;
                            case 2:
                                CameraActivity.this.H.setSelectedItem(3);
                                return;
                            case 3:
                                CameraActivity.this.H.setSelectedItem(2);
                                return;
                            case 4:
                                CameraActivity.this.H.setSelectedItem(4);
                                return;
                            case 5:
                                CameraActivity.this.H.setSelectedItem(0);
                                return;
                            default:
                                CameraActivity.this.H.setSelectedItem(0);
                                return;
                        }
                    case 2:
                        CameraActivity.this.V.setImageResource(R.drawable.icon_camera_white);
                        CameraActivity.this.Z.setImageResource(R.drawable.icon_video_green);
                        if ("Z16".equals(a2) || "Z18".equals(a2) || "J11".equals(a2) || "J22".equals(a2)) {
                            CameraActivity.this.H.setValues(CameraActivity.this.c);
                        } else {
                            CameraActivity.this.H.setValues(CameraActivity.this.b);
                        }
                        g.a(BuildConfig.BUILD_TYPE, "------------------- HorizontalPicker Z16_PHOTO_MODEL setValues = " + CameraActivity.this.c.length, new Object[0]);
                        g.a(BuildConfig.BUILD_TYPE, "------------------- HorizontalPicker Z13_PHOTO_MODEL setValues = " + CameraActivity.this.b.length, new Object[0]);
                        switch (AnonymousClass46.b[CameraActivity.this.al.o().ordinal()]) {
                            case 1:
                                CameraActivity.this.H.setSelectedItem(1);
                                return;
                            case 2:
                                CameraActivity.this.H.setSelectedItem(3);
                                return;
                            case 3:
                                CameraActivity.this.H.setSelectedItem(2);
                                return;
                            case 4:
                                CameraActivity.this.H.setSelectedItem(0);
                                return;
                            default:
                                CameraActivity.this.H.setSelectedItem(0);
                                return;
                        }
                    default:
                        return;
                }
            }
        });
    }

    public void a(Menu menu) {
        menu.clear();
        MenuItem add = menu.add(0, 0, 0, "");
        add.setShowAsAction(2);
        if (this.aH == null) {
            this.aH = LayoutInflater.from(this).inflate(R.layout.layout_phone_battery_time, (ViewGroup) null);
            this.aJ = (ImageView) this.aH.findViewById(R.id.iv_camera_battery);
            this.aK = (CustomBatteryLoading) this.aH.findViewById(R.id.cl_camera_battery);
            this.aL = (TextView) this.aH.findViewById(R.id.tv_phone_time);
        }
        if (this.aM) {
            this.aJ.setVisibility(8);
            this.aK.setVisibility(0);
            this.aK.a(this, this.aN);
            this.aK.setProgress(this.aO);
        } else {
            this.aJ.setVisibility(0);
            this.aK.setVisibility(8);
            if (this.aN) {
                this.aJ.setImageResource(R.drawable.information_battery_nothing);
            } else {
                this.aJ.setImageResource(R.drawable.information_battery_none);
            }
        }
        this.aL.setText(this.aO + "%");
        add.setActionView(this.aH);
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void a(HorizontalPicker horizontalPicker, int i) {
        g.a(BuildConfig.BUILD_TYPE, "--------------- index = " + i, new Object[0]);
        if (this.aP == i || this.al == null || this.al.g() == null) {
            return;
        }
        this.aP = i;
        switch (this.al.g()) {
            case RecordMode:
                switch (i) {
                    case 0:
                        a(Constant.RecordMode.NORMAL);
                        this.C.setImageResource(R.drawable.show_video_normal);
                        break;
                    case 1:
                        a(Constant.RecordMode.TIMELAPES);
                        this.C.setImageResource(R.drawable.show_video_timelapse);
                        break;
                    case 2:
                        a(Constant.RecordMode.SLOW);
                        this.C.setImageResource(R.drawable.show_video_slow);
                        break;
                    case 3:
                        a(Constant.RecordMode.LOOP);
                        this.C.setImageResource(R.drawable.show_video_loop);
                        break;
                    case 4:
                        a(Constant.RecordMode.PHOTO);
                        this.C.setImageResource(R.drawable.show_video_photo);
                        break;
                }
                if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    n();
                    break;
                }
                break;
            case CaptureMode:
                switch (i) {
                    case 0:
                        a(Constant.CaptureMode.NORMAL);
                        this.C.setImageResource(R.drawable.show_photo_normal);
                        break;
                    case 1:
                        a(Constant.CaptureMode.TIMER);
                        this.C.setImageResource(R.drawable.show_photo_timer);
                        break;
                    case 2:
                        a(Constant.CaptureMode.BURST);
                        this.C.setImageResource(R.drawable.show_photo_burst);
                        break;
                    case 3:
                        a(Constant.CaptureMode.TIMELAPES);
                        this.C.setImageResource(R.drawable.show_photo_timelapse);
                        break;
                }
                if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    p();
                    break;
                }
                break;
        }
        d(false, false, false);
        if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
            return;
        }
        F();
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.a.InterfaceC0204a, com.xiaomi.xy.sportscamera.camera.widget.b.a
    public void a(CameraMainController.CameraMode cameraMode, Constant.RecordMode recordMode, Constant.CaptureMode captureMode, String str, int i) {
        if (this.al == null || this.al.s() || this.al.r()) {
            return;
        }
        if (i != 0 && str != null) {
            d(true);
        }
        switch (cameraMode) {
            case RecordMode:
                switch (recordMode) {
                    case TIMELAPES:
                        if (i == 0) {
                            this.bg = str;
                            int[] iArr = new int[2];
                            this.O.getLocationInWindow(iArr);
                            if (this.aB) {
                                b(iArr, this.O.getWidth(), Constant.RecordMode.TIMELAPES.toString().equals(this.al.p().toString()) ? 1 : -1);
                                return;
                            } else {
                                a(iArr, this.O.getWidth(), Constant.RecordMode.TIMELAPES.toString().equals(this.al.p().toString()) ? 1 : -1);
                                return;
                            }
                        }
                        if (i == 1) {
                            if (this.bg != null) {
                                this.al.a(this.bg, 0);
                                StatisticHelper.b("Camera_Quick_Setting_Video_Timelapse_Int", this.bg);
                            }
                            if (str != null) {
                                this.al.a(str, 1);
                                StatisticHelper.b("Camera_Quick_Setting_Video_Timelapse_Len", str);
                            }
                            this.bg = null;
                            return;
                        }
                        return;
                    case LOOP:
                        if (str != null) {
                            this.al.a(str, 0);
                            StatisticHelper.b("Camera_Quick_Setting_Video_Loop", str);
                            return;
                        }
                        return;
                    case SLOW:
                        if (str != null) {
                            this.al.a(str, 0);
                            StatisticHelper.b("Camera_Quick_Setting_Video_Slow", str);
                            return;
                        }
                        return;
                    case PHOTO:
                        if (str != null) {
                            this.al.a(str, 0);
                            StatisticHelper.b("Camera_Quick_Setting_Video_Photo", str);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case CaptureMode:
                if (str != null) {
                    switch (captureMode) {
                        case TIMER:
                            StatisticHelper.b("Camera_Quick_Setting_Picture_Timer", str);
                            break;
                        case TIMELAPES:
                            StatisticHelper.b("Camera_Quick_Setting_Picture_Timelapse", str);
                            break;
                        case BURST:
                            StatisticHelper.b("Camera_Quick_Setting_Picture_Burst", str);
                            break;
                    }
                    this.al.a(str);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(Constant.CaptureMode captureMode) {
        if (this.al != null) {
            this.al.a(captureMode);
        }
        this.G.setText(this.al.m());
    }

    public void a(Constant.RecordMode recordMode) {
        if (this.al != null) {
            this.al.a(recordMode);
        }
        this.G.setText(this.al.m());
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, int i, String str) {
        if (this.E.getVisibility() == 0) {
            this.E.setText(str);
            if (i == 0) {
                i = 1;
            }
            this.aG = i;
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, int i, boolean z2, int i2) {
        if (this.F.getVisibility() != 0 || z2 || i2 == 0 || this.aT == i * 100) {
            return;
        }
        this.aT = i * 100;
        this.aU = (i - 1) * 100;
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(final boolean z, final FileItem fileItem, final ImageView imageView) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.19
            @Override // java.lang.Runnable
            public void run() {
                if (!z || imageView == null || fileItem == null || !imageView.getTag(R.integer.camera_imageview_key).equals(fileItem.getPath()) || fileItem.getThumbnail() == null) {
                    CameraActivity.this.q();
                } else {
                    imageView.setImageBitmap(fileItem.getThumbnail());
                }
                if (CameraActivity.this.al != null) {
                    com.xiaoyi.camera.b.a.b(CameraActivity.this.al);
                }
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, String str, int i) {
        if (z) {
            if (!TextUtils.isEmpty(this.ad.getText())) {
                this.ad.setText(str);
            }
            this.aG = i;
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, boolean z2) {
        C();
        E();
        if (z) {
            c(z2);
            d(false, true, false);
            this.y.setTypeface(this.as);
            this.y.setTextSize(0, getResources().getDimension(R.dimen.dialog_title_textsize));
            this.y.setText(R.string.in_timelapes_capture);
            this.y.setVisibility(0);
            this.U.setImageResource(R.drawable.bg_camera_shutter_work);
        } else {
            d(false, true, true);
            e((String) null);
        }
        if (z2) {
            return;
        }
        g.a("debug_preview", "previewEnable : " + getString(R.string.preview_off), new Object[0]);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, boolean z2, String str) {
        this.y.setText("");
        this.y.setTextColor(getResources().getColor(R.color.white));
        this.y.setVisibility(8);
        d(false, true, true);
        if (z) {
            f(str);
        }
        c(true);
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, boolean z2, boolean z3) {
        C();
        E();
        if (z) {
            c(z2);
            d(false, true, false);
            this.U.setImageResource(R.drawable.bg_camera_shutter_work);
            if (!z3) {
                this.y.setTextSize(0, getResources().getDimension(R.dimen.remind_text_size));
                this.y.setTextColor(getResources().getColor(R.color.primary_green));
                this.y.setTypeface(this.at);
                this.y.setVisibility(0);
            }
        } else {
            e((String) null);
            d(false, true, true);
        }
        if (z2) {
            return;
        }
        g.a("debug_preview", "previewEnable : " + getString(R.string.preview_off), new Object[0]);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, boolean z2, boolean z3, int i) {
        C();
        E();
        if (!z) {
            d(false, true, true);
            c(i);
            return;
        }
        d(false, true, false);
        this.D.setVisibility(8);
        this.y.setVisibility(8);
        this.ac.setVisibility(0);
        this.ad.setText("00:00");
        c(z2);
        this.U.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, boolean z2, boolean z3, String str) {
        d(false, true, true);
        c(true);
        if (z) {
            g(str);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
        this.ac.setVisibility(8);
        if (z3) {
            this.bd.sendMessage(this.bd.obtainMessage(20000));
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, boolean z2, boolean z3, boolean z4, int i) {
        C();
        E();
        if (!z) {
            d(false, true, true);
            c(i);
            return;
        }
        d(true, true, false);
        this.O.setVisibility(8);
        if (z4) {
            this.P.setVisibility(0);
            if (!i.a().b("already_tips_start_piv", false)) {
                i.a().a("already_tips_start_piv", true);
                a(true, false, false, true);
            }
        }
        this.D.setVisibility(8);
        this.ac.setVisibility(0);
        this.ad.setText("00:00");
        this.am.onPause();
        this.am.stop();
        c(z2);
        if (!z3) {
            this.y.setTypeface(this.as);
            this.y.setTextSize(0, getResources().getDimension(R.dimen.dialog_title_textsize));
            this.y.setVisibility(0);
            this.y.setText(R.string.no_preview);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
        C();
        E();
        if (!z) {
            d(false, true, true);
            c(i2);
            return;
        }
        d(false, true, false);
        if (!z3) {
            c(z2);
            return;
        }
        c(z2);
        this.D.setVisibility(0);
        this.ac.setVisibility(8);
        this.y.setVisibility(8);
        if (z4) {
            this.ag.setVisibility(8);
        } else {
            this.ag.setVisibility(0);
            this.ag.setMax(i * 100);
            this.aS = new Timer();
            this.aS.schedule(new TimerTask() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.14
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    CameraActivity.this.ag.setProgress(CameraActivity.this.aU);
                    if (CameraActivity.this.aU <= CameraActivity.this.aT) {
                        CameraActivity.this.aU++;
                    }
                }
            }, 0L, 10L);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    public void a(int[] iArr, int i, int i2) {
        int i3;
        this.ao = new com.xiaomi.xy.sportscamera.camera.widget.a(this, R.style.CustomDialog);
        this.ao.a(this);
        int a2 = this.ao.a(this.al.n(), this.al.p(), this.al.o(), i2);
        if (a2 > 0) {
            i3 = ((a2 + 1) * (this.aw / 8)) + (i / 4);
            if (i3 > this.av / 2) {
                i3 = ((int) ((((this.av / 2) / (this.aw / 8)) - 0.5d) * (this.aw / 8))) + (i / 4);
            }
        } else {
            i3 = 0;
        }
        this.ao.a(i, i3, this.aw / 8, this.aB);
        this.ao.setContentView(R.layout.camera_setting_dialog);
        this.ao.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = this.ao.getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i3;
        attributes.gravity = 85;
        attributes.y = this.av - iArr[1];
        attributes.x = ((this.au - i) - ((i - i) / 2)) - iArr[0];
        this.ao.getWindow().setAttributes(attributes);
        this.ao.b();
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z) {
        if (z) {
            this.bd.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.49
                @Override // java.lang.Runnable
                public void run() {
                    CameraActivity.this.v();
                }
            });
            if (this.ac != null) {
                this.ac.setVisibility(8);
            }
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z, int i, String str) {
        if (this.F.getVisibility() == 0) {
            this.F.setText(str);
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z, boolean z2) {
        C();
        E();
        if (!z) {
            e((String) null);
            d(false, true, true);
            return;
        }
        c(z2);
        this.y.setTypeface(this.as);
        this.y.setTextSize(0, getResources().getDimension(R.dimen.dialog_title_textsize));
        this.y.setText(R.string.in_burst_capture);
        this.y.setVisibility(0);
        d(false, false, false);
        this.U.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z, boolean z2, String str) {
        this.y.setText("");
        this.y.setTextColor(getResources().getColor(R.color.white));
        this.y.setVisibility(8);
        d(false, true, true);
        c(true);
        if (z) {
            f(str);
        }
        this.al.w();
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z, boolean z2, boolean z3) {
        C();
        E();
        if (z) {
            d(false, true, false);
            c(z2);
            this.y.setTextSize(0, getResources().getDimension(R.dimen.remind_text_size));
            this.y.setTextColor(getResources().getColor(R.color.primary_green));
            this.y.setTypeface(this.at);
            this.y.setVisibility(0);
            this.U.setImageResource(R.drawable.bg_camera_shutter_work);
        } else {
            d(false, true, true);
            e((String) null);
        }
        if (z2) {
            return;
        }
        g.a("debug_preview", "previewEnable : " + getString(R.string.preview_off), new Object[0]);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z, boolean z2, boolean z3, int i) {
        C();
        E();
        if (!z) {
            d(false, true, true);
            c(i);
            return;
        }
        d(false, true, false);
        this.D.setVisibility(8);
        this.y.setVisibility(8);
        this.ac.setVisibility(0);
        this.ad.setText("00:00");
        this.U.setImageResource(R.drawable.bg_camera_shutter_work);
        c(z2);
    }

    public void b(int[] iArr, int i, int i2) {
        this.ap = new com.xiaomi.xy.sportscamera.camera.widget.b(this, R.style.CustomDialog);
        this.ap.a(this);
        int a2 = this.ap.a(this.al.n(), this.al.p(), this.al.o(), i2);
        int height = this.N.getHeight();
        int i3 = 0;
        if (a2 > 0 && (i3 = ((this.av / 7) * a2) + (height / 4)) > this.au / 2) {
            i3 = ((int) ((((this.au / 2) / (this.av / 7)) + 0.5d) * (this.av / 7))) + (height / 4);
        }
        this.ap.a(i3, height, this.av / 7, this.aB);
        this.ap.setContentView(R.layout.camera_setting_land_dialog);
        this.ap.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = this.ap.getWindow().getAttributes();
        attributes.width = i3;
        attributes.height = height;
        attributes.gravity = 85;
        attributes.y = (((this.av / 4) - height) / 2) + (this.av / 8);
        attributes.x = i;
        this.ap.getWindow().setAttributes(attributes);
        this.ap.b();
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(int i) {
        e(f(i));
        if (-1004 == i) {
            finish();
        }
    }

    public void c(boolean z) {
        g.a("debug_preview", "play preview enable: " + z, new Object[0]);
        if (this.x == null || this.am == null) {
            return;
        }
        if (!this.aZ || !z || !a()) {
            this.x.setVisibility(4);
            this.x.setBackgroundColor(getResources().getColor(R.color.primary_bg));
            this.am.onPause();
            this.am.stop();
            return;
        }
        this.x.setVisibility(0);
        this.x.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        this.x.setKeepScreenOn(true);
        this.am.bindGLRender(this.an);
        this.am.onResume();
        this.am.play();
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(boolean z, boolean z2) {
        this.Q.setEnabled(true);
        if (z) {
            a(R.string.piv_complete);
        } else if (z2) {
            a(R.string.piv_not_allowed);
        } else {
            a(R.string.can_not_piv_in_current_resolution);
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(boolean z, boolean z2, String str) {
        d(false, true, true);
        c(true);
        if (z) {
            this.y.setText((CharSequence) null);
            this.y.setVisibility(8);
            f(str);
        } else {
            a(str);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(final boolean z, final boolean z2, final boolean z3) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.25
            @Override // java.lang.Runnable
            public void run() {
                CameraActivity.this.d(true, true, true);
                CameraActivity.this.a(false, (String) null);
                if (!z) {
                    CameraActivity.this.a(R.string.setting_failed);
                    return;
                }
                if (!z2) {
                    CameraActivity.this.r.setImageResource(R.drawable.camera_wrap_off_enable);
                    CameraActivity.this.r.setEnabled(false);
                    return;
                }
                CameraActivity.this.r.setEnabled(true);
                if (z3) {
                    CameraActivity.this.r.setImageResource(R.drawable.camera_warp_off_select);
                } else {
                    CameraActivity.this.r.setImageResource(R.drawable.camera_warp_on_select);
                }
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(boolean z, boolean z2, boolean z3, int i) {
        C();
        E();
        if (!z) {
            d(false, true, true);
            c(i);
            return;
        }
        d(false, true, false);
        this.D.setVisibility(8);
        this.y.setVisibility(8);
        this.ac.setVisibility(0);
        this.ad.setText("00:00");
        this.U.setImageResource(R.drawable.bg_camera_shutter_work);
        c(z2);
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void d(int i) {
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void d(String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.7
            @Override // java.lang.Runnable
            public void run() {
                CameraActivity.this.C();
                CameraActivity.this.A();
                CameraActivity.this.a(false, false, false, false);
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void d(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (CameraActivity.this.z != null) {
                    if (z) {
                        CameraActivity.this.z.setVisibility(0);
                    } else {
                        CameraActivity.this.z.setVisibility(8);
                    }
                }
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void d(boolean z, boolean z2, String str) {
        d(false, true, true);
        c(true);
        this.y.setText((CharSequence) null);
        this.y.setVisibility(8);
        if (z) {
            f(str);
        } else {
            a(str);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(final int i) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.11
            @Override // java.lang.Runnable
            public void run() {
                if (CameraActivity.this.y != null) {
                    if (i > 0) {
                        CameraActivity.this.y.setText(String.valueOf(i));
                    } else {
                        CameraActivity.this.y.setText("");
                    }
                }
                if (i > 3 || i < 0) {
                    return;
                }
                CameraActivity.this.d(false, false, false);
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.8
            @Override // java.lang.Runnable
            public void run() {
                if (str != null) {
                    g.a(BuildConfig.BUILD_TYPE, "---------------------------------------------------------- message = " + str, new Object[0]);
                    CameraActivity.this.C();
                    if (CameraMainController.CameraMode.RecordMode.toString().equals(str)) {
                        if ("J11".equals(com.xiaoyi.camera.g.a().a("model")) && CameraActivity.this.bi) {
                            CameraActivity.this.bj = true;
                            CameraActivity.this.J();
                        }
                        CameraActivity.this.o();
                        CameraActivity.this.A();
                        CameraActivity.this.F();
                    } else if (CameraMainController.CameraMode.CaptureMode.toString().equals(str)) {
                        if ("J11".equals(com.xiaoyi.camera.g.a().a("model")) && CameraActivity.this.bi) {
                            CameraActivity.this.bj = true;
                            CameraActivity.this.J();
                        }
                        CameraActivity.this.y();
                        CameraActivity.this.A();
                        CameraActivity.this.F();
                    } else if ("sdCardNeedFormat".equals(str)) {
                        CameraActivity.this.i();
                    } else if ("setting_changed".equals(str)) {
                        CameraActivity.this.A();
                    } else {
                        CameraActivity.this.a(str);
                    }
                    CameraActivity.this.c(true);
                }
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(boolean z) {
        c(z);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(final boolean z, boolean z2, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.13
            @Override // java.lang.Runnable
            public void run() {
                CameraActivity.this.d(false, true, true);
                CameraActivity.this.c(true);
                if (z) {
                    CameraActivity.this.g(str);
                }
                CameraActivity.this.ac.setVisibility(8);
                CameraActivity.this.a(false, false, false, false);
                CameraActivity.this.P.setVisibility(4);
                CameraActivity.this.O.setVisibility(0);
                CameraActivity.this.D.setVisibility(8);
                CameraActivity.this.ac.setVisibility(8);
                CameraActivity.this.y.setVisibility(8);
                CameraActivity.this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void f() {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.48
            @Override // java.lang.Runnable
            public void run() {
                CameraActivity.this.w();
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void f(boolean z) {
        if (z) {
            this.A.setVisibility(0);
            this.B.setVisibility(0);
        } else {
            this.A.setVisibility(8);
            this.B.setVisibility(8);
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void f(boolean z, boolean z2, String str) {
        d(false, true, true);
        c(true);
        if (z) {
            g(str);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
        this.ac.setVisibility(8);
    }

    public void g() {
        if (this.y != null) {
            this.y.setVisibility(8);
        }
        if (this.ac != null) {
            this.ac.setVisibility(8);
        }
        if (this.D != null) {
            this.D.setVisibility(8);
        }
        q();
        com.xiaoyi.camera.g.a().e();
        CameraApplication.f1401a.i().sendBroadcast(new Intent().setAction(com.xiaoyi.camera.a.a("exit_album")));
        d(false);
        if (this.al != null) {
            this.al.u();
        }
        com.xiaomi.xy.sportscamera.camera.b.a().f();
        com.xiaomi.xy.sportscamera.camera.c.a().d();
        com.xiaomi.xy.sportscamera.camera.a.a().c();
        com.xiaoyi.camera.b.a.C();
        c(false);
        com.xiaoyi.camera.b.b = false;
        CameraApplication.f1401a.d(false);
        CameraApplication.f1401a.c(false);
        g.a("debug_switch_fragment", "invalidateOptionsMenu when destroyAll", new Object[0]);
        com.xiaoyi.camera.g.a().d();
        this.bd.postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.2
            @Override // java.lang.Runnable
            public void run() {
                com.xiaoyi.camera.g.a().c();
            }
        }, 200L);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void g(boolean z) {
        if (!z) {
            a(R.string.setting_failed);
        }
        A();
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void g(boolean z, boolean z2, String str) {
        d(false, true, true);
        c(true);
        if (z) {
            g(str);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
        this.ac.setVisibility(8);
    }

    public void h() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.disconnect_from_camera));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("right_button", getString(R.string.camera_connect_dis));
        bundle.putString("left_button", getString(R.string.camera_quit));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.3
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.aQ = true;
                CameraActivity.this.g();
                CameraActivity.this.d(false, false, false);
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }
        });
        customBottomDialogFragment.a(this);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void h(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.9
            @Override // java.lang.Runnable
            public void run() {
                g.a(BuildConfig.BUILD_TYPE, "------------- onChangeCaptureMode result = " + z, new Object[0]);
                if (z) {
                    g.a(BuildConfig.BUILD_TYPE, "------------- mController != null = " + (CameraActivity.this.al != null), new Object[0]);
                    if (CameraActivity.this.al != null) {
                        if (!"J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                            CameraActivity.this.bj = true;
                            CameraActivity.this.J();
                        }
                        g.a(BuildConfig.BUILD_TYPE, "------------- AmbaParam.MODEL_Z16", new Object[0]);
                        CameraActivity.this.H.setValues(CameraActivity.this.c);
                        switch (CameraActivity.this.al.o()) {
                            case TIMER:
                                CameraActivity.this.H.setSelectedItem(1);
                                break;
                            case TIMELAPES:
                                CameraActivity.this.H.setSelectedItem(3);
                                break;
                            case BURST:
                                CameraActivity.this.H.setSelectedItem(2);
                                break;
                            case NORMAL:
                                CameraActivity.this.H.setSelectedItem(0);
                                break;
                        }
                    }
                    CameraActivity.this.d(true, true, true);
                } else {
                    CameraActivity.this.a(R.string.set_failed);
                }
                CameraActivity.this.c(true);
                CameraActivity.this.A();
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void h(boolean z, boolean z2, String str) {
        d(false, true, true);
        c(true);
        if (z) {
            g(str);
        }
        this.U.setImageResource(R.drawable.bg_camera_shutter_normal);
        this.ac.setVisibility(8);
    }

    public void i() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.sd_card_format));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.sd_card_format_now));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.4
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                CameraActivity.this.startActivity(new Intent(CameraActivity.this, (Class<?>) CameraSDCardActivity.class));
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        customBottomDialogFragment.a(this);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void i(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.10
            @Override // java.lang.Runnable
            public void run() {
                g.a(BuildConfig.BUILD_TYPE, "------------- onChangeRecordMode result = " + z, new Object[0]);
                if (z) {
                    g.a(BuildConfig.BUILD_TYPE, "------------- mController != null = " + (CameraActivity.this.al != null), new Object[0]);
                    if (CameraActivity.this.al != null) {
                        if (!"J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                            CameraActivity.this.bj = true;
                            CameraActivity.this.J();
                        }
                        if ("Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                            g.a(BuildConfig.BUILD_TYPE, "------------- AmbaParam.MODEL_Z16", new Object[0]);
                            CameraActivity.this.H.setValues(CameraActivity.this.e);
                        } else if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
                            g.a(BuildConfig.BUILD_TYPE, "------------- AmbaParam.MODEL_Z13", new Object[0]);
                            CameraActivity.this.H.setValues(CameraActivity.this.d);
                        }
                        Constant.RecordMode p = CameraActivity.this.al.p();
                        switch (AnonymousClass46.f4419a[p.ordinal()]) {
                            case 1:
                                if (p.getOption() == null) {
                                    p.setOption(com.xiaoyi.camera.g.a().a("timelapse_video"));
                                }
                                CameraActivity.this.H.setSelectedItem(1);
                                break;
                            case 2:
                                CameraActivity.this.H.setSelectedItem(3);
                                break;
                            case 3:
                                CameraActivity.this.H.setSelectedItem(2);
                                break;
                            case 4:
                                CameraActivity.this.H.setSelectedItem(4);
                                break;
                            case 5:
                                CameraActivity.this.H.setSelectedItem(0);
                                break;
                        }
                    }
                    CameraActivity.this.d(true, true, true);
                } else {
                    CameraActivity.this.a(R.string.setting_failed);
                }
                CameraActivity.this.c(true);
                CameraActivity.this.A();
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void i(boolean z, boolean z2, String str) {
        if (z) {
            try {
                this.aO = Integer.parseInt(str);
                if (this.aO < 0) {
                    this.aO = 0;
                }
                if (this.aO > 100) {
                    this.aO = 100;
                }
            } catch (Exception e) {
                this.aO = 0;
            }
            g.a("debug_battery", "isBattery: " + z2 + " level: " + str, new Object[0]);
            if (!z2 || this.aO < 0 || this.aO > 100) {
                if (this.aO < 0 || this.aO > 100) {
                    this.aM = false;
                    this.aN = true;
                } else {
                    this.aM = true;
                    this.aN = true;
                }
            } else if (this.aO < 15 || this.aO > 100) {
                this.aM = false;
                this.aN = false;
            } else {
                this.aM = true;
                this.aN = false;
            }
            if (!this.aB) {
                invalidateOptionsMenu();
                return;
            }
            if (this.aM) {
                this.aJ.setVisibility(8);
                this.aK.setVisibility(0);
                this.aK.a(this, this.aN);
                this.aK.setProgress(this.aO);
            } else {
                this.aJ.setVisibility(0);
                this.aK.setVisibility(8);
                if (this.aN) {
                    this.aJ.setImageResource(R.drawable.information_battery_nothing);
                } else {
                    this.aJ.setImageResource(R.drawable.information_battery_none);
                }
            }
            this.aL.setText(this.aO + "%");
        }
    }

    public void j() {
        Vibrator vibrator = (Vibrator) getSystemService("vibrator");
        if (vibrator == null || !vibrator.hasVibrator()) {
            return;
        }
        vibrator.vibrate(300L);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void j(boolean z) {
        this.Q.setEnabled(true);
        if (z) {
            this.O.setEnabled(false);
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void k() {
        de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.a(false));
        g.a("debug_event", "CameraNotificationService WIFI_WILL_SHUTDOWN post CameraStopSessionEvent", new Object[0]);
        if (!CameraApplication.f1401a.a() || a()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.setFlags(268435456);
        Intent intent2 = new Intent(this, (Class<?>) CameraConnectionFailedActivity.class);
        intent2.setFlags(268435456);
        startActivities(new Intent[]{intent, intent2});
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void l() {
        g.a(BuildConfig.BUILD_TYPE, "------------------------- onItemScrolling ", new Object[0]);
        this.T.setEnabled(false);
        this.Y.setEnabled(false);
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void m() {
        g.a(BuildConfig.BUILD_TYPE, "------------------------- onItemScrollfinish ", new Object[0]);
        this.T.setEnabled(true);
        this.Y.setEnabled(true);
    }

    public void n() {
        this.al.a(CameraMainController.CameraMode.RecordMode);
    }

    public void o() {
        if (this.H != null) {
            this.H.setVisibility(0);
        }
        if (this.G != null) {
            this.G.setVisibility(0);
        }
        if (this.aB) {
            this.bd.removeCallbacks(this.bn);
            this.bd.postDelayed(this.bn, 3000L);
        }
        this.V.setImageResource(R.drawable.icon_video_white);
        this.Z.setImageResource(R.drawable.icon_camera_green);
        if ("Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            this.H.setValues(this.e);
        } else if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
            this.H.setValues(this.d);
        }
        switch (this.al.p()) {
            case TIMELAPES:
                this.H.setSelectedItem(1);
                this.C.setImageResource(R.drawable.show_video_timelapse);
                return;
            case LOOP:
                this.H.setSelectedItem(3);
                this.C.setImageResource(R.drawable.show_video_loop);
                return;
            case SLOW:
                this.H.setSelectedItem(2);
                this.C.setImageResource(R.drawable.show_video_slow);
                return;
            case PHOTO:
                this.H.setSelectedItem(4);
                this.C.setImageResource(R.drawable.show_video_photo);
                return;
            default:
                this.H.setSelectedItem(0);
                this.C.setImageResource(R.drawable.show_video_normal);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 111 && i2 == 1000) {
            com.xiaoyi.camera.b.b = false;
            finish();
            return;
        }
        if (i == 111 && com.xiaoyi.camera.b.b) {
            if (i2 == -1 && w.a().b()) {
                t();
                return;
            }
            return;
        }
        if (i == 115 && i2 == 1002) {
            q();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.aB) {
            setRequestedOrientation(1);
        } else if (!q.g(this)) {
            h();
        } else {
            super.onBackPressed();
            overridePendingTransition(0, R.anim.camera_close_exit);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.al == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.ivFullScreen /* 2131755230 */:
                if (this.aB) {
                    setRequestedOrientation(1);
                    return;
                } else {
                    setRequestedOrientation(0);
                    return;
                }
            case R.id.ivSettingWarp /* 2131755231 */:
                d(false, false, false);
                a(true, "");
                this.al.x();
                return;
            case R.id.ivSudoku /* 2131755232 */:
                if (this.aB) {
                    this.v.a(this.ay, this.az);
                    return;
                } else {
                    this.v.a(this.au, this.aw);
                    return;
                }
            case R.id.ivSetting /* 2131755233 */:
                startActivity(new Intent(this, (Class<?>) VideoPhotoSettingActivity.class));
                StatisticHelper.E();
                return;
            case R.id.ibFileManage /* 2131755254 */:
                if (TextUtils.isEmpty(this.aW) || "Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    startActivity(new Intent(this, (Class<?>) CameraAlbumActivity.class));
                    return;
                }
                Intent intent = new Intent(this, (Class<?>) CameraMediaShowActivity.class);
                intent.putExtra("PREVIEW_FILE_PARAM", this.aW);
                startActivityForResult(intent, 115);
                return;
            case R.id.rlRecordCapture /* 2131755256 */:
                g.a("debug_capture_click", " sessionStart: " + com.xiaoyi.camera.b.b, new Object[0]);
                if (com.xiaoyi.camera.b.b) {
                    d(false, false, false);
                    this.al.h();
                    H();
                    return;
                }
                return;
            case R.id.rlModelSwitch /* 2131755261 */:
                if (com.xiaoyi.camera.b.b) {
                    this.am.onPause();
                    this.am.stop();
                    d(false, false, false);
                    this.bi = true;
                    I();
                    x();
                    return;
                }
                return;
            case R.id.rlFunciton /* 2131755266 */:
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                if (this.aB) {
                    b(iArr, view.getWidth(), Constant.RecordMode.TIMELAPES.toString().equals(this.al.p().toString()) ? 0 : -1);
                    return;
                } else {
                    a(iArr, view.getWidth(), Constant.RecordMode.TIMELAPES.toString().equals(this.al.p().toString()) ? 0 : -1);
                    return;
                }
            case R.id.ivGraph /* 2131755269 */:
                this.Q.setEnabled(false);
                this.al.i();
                StatisticHelper.D();
                return;
            case R.id.rlTipsView /* 2131755735 */:
                if (this.ai.getVisibility() == 0) {
                    i.a().a("already_tips_enter_album", true);
                    if (i.a().b("already_tips_switch_model", false)) {
                        a(false, false, false, false);
                        return;
                    } else {
                        a(true, false, true, false);
                        return;
                    }
                }
                if (this.aj.getVisibility() == 0) {
                    i.a().a("already_tips_switch_model", true);
                    a(false, false, false, false);
                    return;
                } else if (this.ak.getVisibility() != 0) {
                    a(false, false, false, false);
                    return;
                } else {
                    i.a().a("already_tips_start_piv", true);
                    a(false, false, false, false);
                    return;
                }
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        g.a("debug_camera", "--------------onCreate-------------", new Object[0]);
        g.a("debug_switch_fragment", "onCreate CameraFragment", new Object[0]);
        if (getResources().getConfiguration().orientation == 2) {
            this.aB = true;
            requestWindowFeature(1);
            StatisticHelper.F();
        } else {
            this.aB = false;
        }
        super.onCreate(bundle);
        this.aZ = K();
        if (!this.aZ) {
            a(R.string.phone_not_enough_storage);
        }
        this.b = getResources().getStringArray(R.array.photo_model);
        this.c = this.b;
        this.d = getResources().getStringArray(R.array.record_model_z13);
        this.e = getResources().getStringArray(R.array.record_model_z16);
        this.at = Typeface.createFromAsset(getAssets(), "fonts/DINCond-Light.otf");
        this.as = Typeface.createFromAsset(getAssets(), "fonts/DINCond-Regular.otf");
        this.au = com.yiaction.common.util.b.b(this);
        this.av = com.yiaction.common.util.b.c(this);
        if (this.aB) {
            this.ay = (this.av * 4) / 3;
            this.az = this.av;
            this.aA = (this.ay * 9) / 16;
        } else {
            this.aw = (this.au * 3) / 4;
            this.ax = (this.au * 9) / 16;
        }
        this.al = new CameraMainController(CameraApplication.f1401a.i());
        this.am = new CameraMediaPlayer(this, this.al);
        this.ba = new b(this);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_camera);
        this.o = (LinearLayout) findViewById(R.id.llOperationLayout);
        this.p = (ImageView) findViewById(R.id.ivSudoku);
        this.p.setOnClickListener(this);
        this.p.setEnabled(false);
        this.q = (ImageView) findViewById(R.id.ivSetting);
        this.q.setOnClickListener(this);
        this.q.setEnabled(false);
        this.s = (ImageView) findViewById(R.id.ivFullScreen);
        this.s.setOnClickListener(this);
        this.r = (ImageView) findViewById(R.id.ivSettingWarp);
        this.r.setOnClickListener(this);
        this.t = (RelativeLayout) findViewById(R.id.rlBlock);
        this.u = (TextView) findViewById(R.id.tvInfoCameraAlbum);
        this.t.setOnClickListener(this);
        this.v = (SudokuView) findViewById(R.id.svSudokuView);
        this.w = (RelativeLayout) findViewById(R.id.player_surface_frame);
        this.x = (GLSurfaceView) findViewById(R.id.svPreview);
        this.an = (PlayerRenderer) CameraMediaPlayer.getNewRendererForGLSurfaceView(this.x);
        this.am.configGLSurface(this.x, this.an);
        this.x.setLongClickable(true);
        this.x.setOnTouchListener(this.ba);
        this.y = (TextView) findViewById(R.id.tvSelfCaptureTime);
        this.y.setTypeface(this.at);
        this.z = (ProgressBar) findViewById(R.id.pbProcessing);
        this.A = findViewById(R.id.svPreview_top);
        this.B = findViewById(R.id.svPreview_bottom);
        this.C = (ImageView) findViewById(R.id.ivShowModel);
        this.D = (RelativeLayout) findViewById(R.id.timelaps_record_time_layout);
        this.F = (TextView) findViewById(R.id.tvTimelapesRecordCountTime);
        this.E = (TextView) findViewById(R.id.tvTimelapesRecordTime);
        this.G = (TextView) findViewById(R.id.tvResolution);
        this.I = (LinearLayout) findViewById(R.id.llrlControlLayout);
        this.J = (LinearLayout) findViewById(R.id.rlControlPanel);
        this.K = (LinearLayout) findViewById(R.id.llFileManage);
        this.L = (RelativeLayout) findViewById(R.id.rlFileManage);
        this.M = (ImageView) findViewById(R.id.ibFileManage);
        this.M.setOnClickListener(this);
        this.N = (RelativeLayout) findViewById(R.id.llFunciton);
        this.O = (RelativeLayout) findViewById(R.id.rlFunciton);
        this.O.setOnClickListener(this);
        this.R = (SettingItemViewPager) findViewById(R.id.settingItemViewPager);
        this.P = (RelativeLayout) findViewById(R.id.rlGraph);
        this.Q = (ImageView) findViewById(R.id.ivGraph);
        this.Q.setOnClickListener(this);
        this.S = (LinearLayout) findViewById(R.id.llRecordCapture);
        this.T = (RelativeLayout) findViewById(R.id.rlRecordCapture);
        this.T.setOnClickListener(this);
        this.U = (ImageView) findViewById(R.id.cvPhotoView);
        this.V = (ImageView) findViewById(R.id.ibRecordCapture);
        this.W = (ImageView) findViewById(R.id.ivPhotoView);
        this.ac = (RelativeLayout) findViewById(R.id.llNormalRecordTime);
        this.ad = (TextView) findViewById(R.id.tvNormalRecordTime);
        this.ae = (RelativeLayout) findViewById(R.id.rlShutterRemind);
        this.af = (TextView) findViewById(R.id.tvShutterRemind);
        this.X = (LinearLayout) findViewById(R.id.llModelSwitch);
        this.Y = (RelativeLayout) findViewById(R.id.rlModelSwitch);
        this.Y.setOnClickListener(this);
        this.Z = (ImageView) findViewById(R.id.ibRecordCapture_2);
        this.aa = (ImageView) findViewById(R.id.ivSwitchView);
        this.ab = (ImageView) findViewById(R.id.cvSwitchView);
        this.ag = (TimelapsSeekBar) findViewById(R.id.playSeekbar);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.ag.setLayoutParams(layoutParams);
        this.ag.setProgress(0);
        this.H = (HorizontalPicker) findViewById(R.id.hpModelPicker);
        this.H.b(getResources().getColor(R.color.white));
        this.H.setOnItemSelectedListener(this);
        this.ah = (RelativeLayout) findViewById(R.id.rlTipsView);
        this.ah.setVisibility(8);
        this.ah.setOnClickListener(this);
        this.ai = (LinearLayout) findViewById(R.id.llEnterAlbum);
        this.aj = (LinearLayout) findViewById(R.id.llSwitchModel);
        this.ak = (LinearLayout) findViewById(R.id.llStartPiv);
        if (this.aB) {
            this.aI = (LinearLayout) findViewById(R.id.llBatteryLayout);
            this.aJ = (ImageView) findViewById(R.id.iv_camera_battery);
            this.aK = (CustomBatteryLoading) findViewById(R.id.cl_camera_battery);
            this.aL = (TextView) findViewById(R.id.tv_phone_time);
            this.o.setLayoutParams(new LinearLayout.LayoutParams(((this.au - this.ay) * 60) / 160, -1));
            this.w.setLayoutParams(new FrameLayout.LayoutParams(this.ay, this.az));
            this.am.setSurfaceSize(this.ay, this.az);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(this.ay, this.az / 8);
            layoutParams2.gravity = 48;
            this.A.setLayoutParams(layoutParams2);
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(this.ay, this.az / 8);
            layoutParams3.gravity = 80;
            this.B.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(this.ay, this.az / 8);
            layoutParams4.addRule(12);
            this.G.setLayoutParams(layoutParams4);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(this.ay, this.az / 8);
            layoutParams5.addRule(12);
            this.ac.setLayoutParams(layoutParams5);
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(this.ay, this.az / 8);
            layoutParams6.topMargin = this.az / 8;
            this.ae.setLayoutParams(layoutParams6);
            FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(this.ay, this.az / 8);
            layoutParams7.gravity = 48;
            this.H.setLayoutParams(layoutParams7);
            this.bd.removeCallbacks(this.bn);
            this.bd.postDelayed(this.bn, 3000L);
        } else {
            this.v.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.aw));
            this.w.setLayoutParams(new LinearLayout.LayoutParams(this.au, this.aw));
            this.am.setSurfaceSize(this.au, this.aw);
            FrameLayout.LayoutParams layoutParams8 = new FrameLayout.LayoutParams(-1, this.aw / 8);
            layoutParams8.gravity = 48;
            this.A.setLayoutParams(layoutParams8);
            FrameLayout.LayoutParams layoutParams9 = new FrameLayout.LayoutParams(-1, this.aw / 8);
            layoutParams9.gravity = 80;
            this.B.setLayoutParams(layoutParams9);
            this.G.setLayoutParams(new LinearLayout.LayoutParams(-1, this.aw / 8));
            this.ac.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.aw / 8));
            RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(-1, this.aw / 8);
            layoutParams10.addRule(3, R.id.llNormalRecordTime);
            this.ae.setLayoutParams(layoutParams10);
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setLogo(R.drawable.camera_close_select);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_io_error"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_full"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_format_done"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_sdformat"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_sdformat"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_mvrecover"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_mvrecover"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_live"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_live"));
        registerReceiver(this.bf, intentFilter);
        de.greenrobot.event.c.a().a(this);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        g.a("debug_camera", "--------------destroy-------------", new Object[0]);
        g.a("debug_switch_fragment", "destroy CameraFragment", new Object[0]);
        super.onDestroy();
        if (this.y != null) {
            this.y.setVisibility(8);
        }
        if (this.ac != null) {
            this.ac.setVisibility(8);
        }
        if (this.D != null) {
            this.D.setVisibility(8);
        }
        if (this.al != null) {
            this.al.u();
            this.al = null;
        }
        unregisterReceiver(this.bf);
        this.am.destroy();
        de.greenrobot.event.c.a().b(this);
        if (com.xiaoyi.camera.b.b) {
            return;
        }
        NetworkUtil.clearBindProcess(this);
        com.ants360.z13.b.a.a((a.InterfaceC0051a) null);
    }

    public void onEvent(com.ants360.z13.c.d dVar) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.17
            @Override // java.lang.Runnable
            public void run() {
                CameraActivity.this.q();
            }
        });
    }

    public void onEvent(final com.xiaoyi.camera.a.a aVar) {
        g.a("debug_event", "CameraActivity received CameraStopSessionEvent", new Object[0]);
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (!CameraActivity.this.aQ) {
                    CameraActivity.this.g();
                }
                if (aVar.a()) {
                    CameraActivity.this.r();
                }
            }
        });
    }

    public void onEvent(com.xiaoyi.camera.a.c cVar) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.26
            @Override // java.lang.Runnable
            public void run() {
                CameraActivity.this.q();
            }
        });
    }

    public void onEvent(final com.xiaoyi.camera.a.d dVar) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (dVar.a() && CameraActivity.this.al != null) {
                    CameraActivity.this.al.b();
                    CameraActivity.this.z();
                    CameraActivity.this.A();
                }
                CameraActivity.this.d(true, true, true);
            }
        });
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if ((i != 25 && i != 24) || !com.xiaoyi.camera.b.b || this.al == null) {
            return super.onKeyDown(i, keyEvent);
        }
        d(false, false, false);
        this.al.h();
        H();
        return true;
    }

    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        h();
        return false;
    }

    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        g.a("debug_camera", "--------------pause-------------", new Object[0]);
        g.a("debug_switch_fragment", "pause CameraFragment", new Object[0]);
        super.onPause();
        if (this.aQ) {
            return;
        }
        this.am.onPause();
        this.am.stop();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.aB) {
            return false;
        }
        if (com.xiaoyi.camera.b.b) {
            a(menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        g.a("debug_camera", "--------------resume-------------", new Object[0]);
        g.a("debug_preview", "resume()", new Object[0]);
        if (!com.xiaoyi.camera.b.b) {
            finish();
            return;
        }
        if (!i.a().b("already_tips_enter_album", false) && !this.aB) {
            i.a().b("already_tips_enter_album", true);
            a(true, true, false, false);
        }
        if ((i.a().b("already_tips_enter_album", false) || this.aB) && !i.a().b("already_tips_switch_model", false)) {
            i.a().b("already_tips_switch_model", true);
            a(true, false, true, false);
        }
        D();
        this.al.b();
        this.al.a(this);
        if (this.al.B()) {
            return;
        }
        w();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            if (this.aB) {
                int i = ((this.au - this.ay) * 100) / 160;
                this.I.setLayoutParams(new LinearLayout.LayoutParams(i, -1));
                this.aI.setLayoutParams(new LinearLayout.LayoutParams(i, this.az / 8));
                int i2 = (this.av - ((this.az / 8) * 2)) / 3;
                this.aR = i2;
                this.S.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
                int i3 = (i * 60) / 100;
                this.T.setLayoutParams(new LinearLayout.LayoutParams(i3, i3));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((i3 * 100) / 200, (i3 * 100) / 200);
                layoutParams.addRule(13);
                this.V.setLayoutParams(layoutParams);
                this.W.setLayoutParams(layoutParams);
                this.X.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
                this.Y.setLayoutParams(new LinearLayout.LayoutParams(i3, i3));
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((i3 * 100) / 200, (i3 * 100) / 200);
                layoutParams2.addRule(13);
                this.Z.setLayoutParams(layoutParams2);
                this.aa.setLayoutParams(layoutParams2);
                this.N.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
                this.O.setLayoutParams(new RelativeLayout.LayoutParams(i, -1));
                int i4 = (i * 40) / 100;
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i4, i4);
                layoutParams3.addRule(13);
                this.P.setLayoutParams(layoutParams3);
                if (!i.a().b("already_tips_switch_model", false)) {
                    LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, this.av / 4);
                    layoutParams4.rightMargin = i;
                    this.aj.setLayoutParams(layoutParams4);
                }
                if (i.a().b("already_tips_start_piv", false)) {
                    return;
                }
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, this.av / 4);
                layoutParams5.rightMargin = i;
                this.ak.setLayoutParams(layoutParams5);
                return;
            }
            int height = this.I.getHeight();
            int i5 = height / 2;
            int i6 = i5 > (this.au * 210) / 1080 ? (this.au * 210) / 1080 : i5;
            this.aR = i6;
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(i6, i6);
            this.T.setLayoutParams(layoutParams6);
            this.Y.setLayoutParams(layoutParams6);
            new LinearLayout.LayoutParams(-1, i6);
            RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams((i6 * 88) / 210, (i6 * 88) / 210);
            layoutParams7.addRule(13);
            this.V.setLayoutParams(layoutParams7);
            this.Z.setLayoutParams(layoutParams7);
            int i7 = (this.au * 170) / 1080;
            int i8 = (i6 - i7) / 2;
            LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(i7, i7);
            layoutParams8.topMargin = i8;
            this.L.setLayoutParams(layoutParams8);
            RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(((this.au / 3) * 3) / 4, i7);
            layoutParams9.topMargin = i8;
            this.O.setLayoutParams(layoutParams9);
            int i9 = (this.au * 120) / 1080;
            RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(i9, i9);
            layoutParams10.topMargin = (i6 - i9) / 2;
            this.P.setLayoutParams(layoutParams10);
            if (!i.a().b("already_tips_enter_album", false)) {
                RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) this.ai.getLayoutParams();
                layoutParams11.addRule(13);
                layoutParams11.setMargins(this.au / 6, 0, 0, (height - ((height - (i6 * 2)) / 2)) - ((i6 - i9) / 2));
                this.ai.setLayoutParams(layoutParams11);
            }
            if (!i.a().b("already_tips_switch_model", false)) {
                RelativeLayout.LayoutParams layoutParams12 = (RelativeLayout.LayoutParams) this.aj.getLayoutParams();
                layoutParams12.addRule(13);
                layoutParams12.setMargins((this.au / 2) - (i6 / 2), 0, 0, ((height - (i6 * 2)) / 2) + (i6 / 2));
                this.aj.setLayoutParams(layoutParams12);
            }
            if (i.a().b("already_tips_start_piv", false)) {
                return;
            }
            RelativeLayout.LayoutParams layoutParams13 = (RelativeLayout.LayoutParams) this.ak.getLayoutParams();
            layoutParams13.addRule(15);
            layoutParams13.setMargins(0, 0, this.au / 6, (height - ((height - (i6 * 2)) / 2)) - ((i6 - i9) / 2));
            this.ak.setLayoutParams(layoutParams13);
        }
    }

    public void p() {
        this.al.a(CameraMainController.CameraMode.CaptureMode);
    }

    public void q() {
        if (this.M != null) {
            this.M.setImageResource(R.drawable.pic_default);
            this.M.setTag(null);
            this.aW = null;
        }
    }

    public void r() {
        if (this.aQ) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.wifi_connect_failed));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.wifi_connect_failed_desc_default));
        bundle.putString("left_button", getString(R.string.button_back_to_home));
        bundle.putString("right_button", getString(R.string.button_reconnect));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        this.aq = new CustomBottomDialogFragment();
        this.aq.setArguments(bundle);
        this.aq.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.20
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }
        });
        this.aq.a(this);
    }

    public void s() {
        if (this.aQ) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.enter_live_and_disconnect));
        bundle.putString("left_button", getString(R.string.button_back_to_home));
        bundle.putString("right_button", getString(R.string.button_reconnect));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        this.aq = new CustomBottomDialogFragment();
        this.aq.setArguments(bundle);
        this.aq.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.CameraActivity.21
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.g();
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.g();
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraActivity.this.g();
                CameraActivity.this.finish();
                CameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }
        });
        this.aq.a(this);
    }
}
