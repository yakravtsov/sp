package com.xiaomi.xy.sportscamera.camera.album;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.util.StatisticHelper;
import com.ants360.z13.videoClip.VideoEditImpl;
import com.ants360.z13.widget.CustomTitleBar;
import com.ants360.z13.widget.ExtendedViewPager;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.rp.rpfiledatapool.model.RPFile;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.b;
import com.yiaction.videoeditorui.videoClip.ui.VideoClipActivity;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.g;

@g(a = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0007*\u0002G,\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020\u0011H\u0002J\u0010\u0010X\u001a\u00020V2\u0006\u0010W\u001a\u00020\u0011H\u0002J\u0010\u0010Y\u001a\u00020V2\u0006\u0010W\u001a\u00020\u0011H\u0002J\u0006\u0010Z\u001a\u00020VJ\u0006\u0010[\u001a\u00020VJ\b\u0010\\\u001a\u00020VH\u0016J\u0010\u0010]\u001a\u00020V2\u0006\u0010^\u001a\u00020_H\u0016J\u0012\u0010`\u001a\u00020V2\b\u0010a\u001a\u0004\u0018\u00010bH\u0014J\b\u0010c\u001a\u00020VH\u0014J\u0010\u0010d\u001a\u00020V2\u0006\u0010e\u001a\u00020;H\u0016J \u0010f\u001a\u00020V2\u0006\u0010g\u001a\u00020;2\u0006\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020;H\u0016J\u0010\u0010k\u001a\u00020V2\u0006\u0010g\u001a\u00020;H\u0016J\u0010\u0010l\u001a\u00020V2\u0006\u0010W\u001a\u00020\u0011H\u0002J\u001a\u0010m\u001a\u00020V2\u0006\u0010n\u001a\u0002052\b\u0010o\u001a\u0004\u0018\u00010\u0006H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\u001c\u0010%\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!R\u001c\u0010(\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!R\u0010\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0004\n\u0002\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u000205X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0010\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0004\n\u0002\u0010HR\u001c\u0010I\u001a\u0004\u0018\u00010JX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001c\u0010O\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0019\"\u0004\bQ\u0010\u001bR\u001c\u0010R\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u0019\"\u0004\bT\u0010\u001b¨\u0006p"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity;", "Lcom/ants360/z13/activity/BaseActivity;", "Landroid/view/View$OnClickListener;", "Landroid/support/v4/view/ViewPager$OnPageChangeListener;", "()V", "MEDIA_POSIOTION", "", "getMEDIA_POSIOTION", "()Ljava/lang/String;", "adapter", "Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaItemAdapter;", "getAdapter", "()Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaItemAdapter;", "setAdapter", "(Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaItemAdapter;)V", "fileItems", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "getFileItems", "()Ljava/util/List;", "setFileItems", "(Ljava/util/List;)V", "fileName", "Landroid/widget/TextView;", "getFileName", "()Landroid/widget/TextView;", "setFileName", "(Landroid/widget/TextView;)V", "imgDelete", "Landroid/widget/ImageView;", "getImgDelete", "()Landroid/widget/ImageView;", "setImgDelete", "(Landroid/widget/ImageView;)V", "imgDownload", "getImgDownload", "setImgDownload", "imgEdit", "getImgEdit", "setImgEdit", "imgVideoPuzzle", "getImgVideoPuzzle", "setImgVideoPuzzle", "mCameraFileControl", "com/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity$mCameraFileControl$1", "Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity$mCameraFileControl$1;", "mCustomTitleBar", "Lcom/ants360/z13/widget/CustomTitleBar;", "getMCustomTitleBar", "()Lcom/ants360/z13/widget/CustomTitleBar;", "setMCustomTitleBar", "(Lcom/ants360/z13/widget/CustomTitleBar;)V", "mIsDelete", "", "getMIsDelete", "()Z", "setMIsDelete", "(Z)V", "mPosiotion", "", "getMPosiotion", "()I", "setMPosiotion", "(I)V", "mViewPager", "Lcom/ants360/z13/widget/ExtendedViewPager;", "getMViewPager", "()Lcom/ants360/z13/widget/ExtendedViewPager;", "setMViewPager", "(Lcom/ants360/z13/widget/ExtendedViewPager;)V", "receiver", "com/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity$receiver$1", "Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity$receiver$1;", "rlBlock", "Landroid/widget/RelativeLayout;", "getRlBlock", "()Landroid/widget/RelativeLayout;", "setRlBlock", "(Landroid/widget/RelativeLayout;)V", "tvInfoCameraAlbum", "getTvInfoCameraAlbum", "setTvInfoCameraAlbum", "videoDuration", "getVideoDuration", "setVideoDuration", "deleteFile", "", "fileItem", "doEdit", "downloadPhoto", "initData", "initView", "onBackPressed", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPageScrollStateChanged", "state", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "refreshButtonState", "showBlockingCover", "isShow", "text", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class QZCameraMediaShowActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private CustomTitleBar c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private TextView h;
    private TextView i;
    private ExtendedViewPager j;
    private RelativeLayout k;
    private TextView l;
    private int m;
    private CameraMediaItemAdapter n;
    private List<RPFile> o;
    private boolean p;
    private final String b = "MEDIA_POSIOTION";
    private final QZCameraMediaShowActivity$receiver$1 q = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.album.QZCameraMediaShowActivity$receiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            kotlin.jvm.internal.g.b(context, "context");
            kotlin.jvm.internal.g.b(intent, "intent");
            String action = intent.getAction();
            intent.getIntExtra("current_operation_model", -1);
            if (b.b) {
                if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("start_photo_capture"), (Object) action)) {
                    QZCameraMediaShowActivity.this.finish();
                    return;
                }
                if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("photo_taken"), (Object) action) || kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("self_capture_stop"), (Object) action) || kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("burst_complete"), (Object) action) || kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("precise_cont_complete"), (Object) action) || kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("video_record_complete"), (Object) action) || kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("exit_album"), (Object) action)) {
                    QZCameraMediaShowActivity.this.a(false, (String) null);
                    return;
                }
                if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("start_video_record"), (Object) action)) {
                    QZCameraMediaShowActivity.this.finish();
                    return;
                }
                if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("enter_album"), (Object) action)) {
                    QZCameraMediaShowActivity.this.finish();
                    return;
                }
                if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("sd_card_status"), (Object) action)) {
                    String str = (String) null;
                    try {
                        str = intent.getStringExtra("current_operation_model");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (kotlin.jvm.internal.g.a((Object) ProductAction.ACTION_REMOVE, (Object) str)) {
                        QZCameraMediaShowActivity.this.finish();
                    }
                }
            }
        }
    };
    private d r = new d();

    @g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity$deleteFile$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity;Lcom/rp/rpfiledatapool/model/RPFile;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a implements DimPanelFragment.c {
        final /* synthetic */ RPFile b;

        a(RPFile rPFile) {
            this.b = rPFile;
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            QZCameraMediaShowActivity.this.b(true);
            QZCameraMediaShowActivity.this.a(true, "");
            de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
            com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.b(this.b);
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
        }
    }

    @g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity$initView$1", "Lcom/ants360/z13/widget/CustomTitleBar$TitleClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity;)V", "onLeftClick", "", "onMiddleClick", "onRightClick", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class b implements CustomTitleBar.a {
        b() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void a() {
            QZCameraMediaShowActivity.this.onBackPressed();
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void b() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void c() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @g(a = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, b = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch"})
    /* loaded from: classes.dex */
    public static final class c implements View.OnTouchListener {

        /* renamed from: a, reason: collision with root package name */
        public static final c f4520a = new c();

        c() {
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    @g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity$mCameraFileControl$1", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraMediaShowActivity;)V", "deleteFailed", "", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "deleteSuccess", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class d extends com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a {

        @g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class a implements Runnable {
            final /* synthetic */ RPFile b;

            a(RPFile rPFile) {
                this.b = rPFile;
            }

            @Override // java.lang.Runnable
            public final void run() {
                QZCameraMediaShowActivity.this.a(false, (String) null);
                List<RPFile> j = QZCameraMediaShowActivity.this.j();
                if (j == null) {
                    kotlin.jvm.internal.g.a();
                }
                j.remove(this.b);
                if (QZCameraMediaShowActivity.this.j() != null) {
                    List<RPFile> j2 = QZCameraMediaShowActivity.this.j();
                    if (j2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (!j2.isEmpty()) {
                        CameraMediaItemAdapter i = QZCameraMediaShowActivity.this.i();
                        if (i != null) {
                            i.a(QZCameraMediaShowActivity.this.j());
                        }
                        int h = QZCameraMediaShowActivity.this.h();
                        List<RPFile> j3 = QZCameraMediaShowActivity.this.j();
                        if (j3 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        if (h >= j3.size()) {
                            QZCameraMediaShowActivity qZCameraMediaShowActivity = QZCameraMediaShowActivity.this;
                            List<RPFile> j4 = QZCameraMediaShowActivity.this.j();
                            if (j4 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            qZCameraMediaShowActivity.c(j4.size());
                        }
                        List<RPFile> j5 = QZCameraMediaShowActivity.this.j();
                        if (j5 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        QZCameraMediaShowActivity.this.a(j5.get(QZCameraMediaShowActivity.this.h()));
                        de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
                        return;
                    }
                }
                QZCameraMediaShowActivity.this.onBackPressed();
            }
        }

        d() {
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void b(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.b(rPFile);
            QZCameraMediaShowActivity.this.runOnUiThread(new a(rPFile));
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void c(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.c(rPFile);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    public static final class e implements Runnable {
        final /* synthetic */ boolean b;
        final /* synthetic */ String c;

        e(boolean z, String str) {
            this.b = z;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b) {
                RelativeLayout f = QZCameraMediaShowActivity.this.f();
                if (f == null) {
                    kotlin.jvm.internal.g.a();
                }
                f.setVisibility(0);
                TextView g = QZCameraMediaShowActivity.this.g();
                if (g == null) {
                    kotlin.jvm.internal.g.a();
                }
                g.setText(this.c);
            } else {
                RelativeLayout f2 = QZCameraMediaShowActivity.this.f();
                if (f2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                f2.setVisibility(8);
                TextView g2 = QZCameraMediaShowActivity.this.g();
                if (g2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                g2.setText("");
            }
            CameraMediaItemAdapter i = QZCameraMediaShowActivity.this.i();
            if (i != null) {
                i.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(RPFile rPFile) {
        String name = rPFile.getName();
        if (!TextUtils.isEmpty(name)) {
            TextView textView = this.h;
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.h;
            if (textView2 != null) {
                textView2.setText(name);
            }
        }
        ImageView imageView = this.d;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (rPFile.getType() == 1) {
            ImageView imageView2 = this.g;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            TextView textView3 = this.i;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            ImageView imageView3 = this.d;
            if (imageView3 != null) {
                imageView3.setVisibility(0);
                return;
            }
            return;
        }
        ImageView imageView4 = this.g;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        TextView textView4 = this.i;
        if (textView4 != null) {
            textView4.setVisibility(0);
        }
        TextView textView5 = this.i;
        if (textView5 != null) {
            textView5.setText("");
        }
        ImageView imageView5 = this.d;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(boolean z, String str) {
        runOnUiThread(new e(z, str));
    }

    private final void b(RPFile rPFile) {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.title_delete));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.sure_to_delete));
        Fragment instantiate = Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        if (instantiate == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.fragment.CustomBottomDialogFragment");
        }
        CustomBottomDialogFragment customBottomDialogFragment = (CustomBottomDialogFragment) instantiate;
        customBottomDialogFragment.a(new a(rPFile));
        customBottomDialogFragment.a(this);
    }

    private final void c(RPFile rPFile) {
        if (!com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.c((com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d) rPFile)) {
            a(R.string.phone_storage_out);
            return;
        }
        List<RPFile> c2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.c();
        if (c2 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (c2.contains(rPFile)) {
            a(R.string.file_already_downloaded);
            return;
        }
        List<RPFile> d2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.d();
        if (d2 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (d2.contains(rPFile)) {
            a(R.string.file_is_downloading);
        } else {
            com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b(rPFile);
            a(R.string.file_add_to_download_quee);
        }
    }

    private final void d(RPFile rPFile) {
        if (rPFile.getType() == 1) {
            com.ants360.z13.d.a.a(this, com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a(rPFile), rPFile.getName(), kotlin.jvm.internal.g.a((Object) "Z16", (Object) com.xiaoyi.camera.g.a().a("model")), kotlin.jvm.internal.g.a((Object) "Z16", (Object) com.xiaoyi.camera.g.a().a("model")) ? "YDXJ 2" : kotlin.jvm.internal.g.a((Object) "Z18", (Object) com.xiaoyi.camera.g.a().a("model")) ? "YIAC 3" : "");
            StatisticHelper.f();
            return;
        }
        String str = "192.168.100.1";
        if (com.rp.rptool.util.d.a().b() != null) {
            str = com.rp.rptool.util.d.a().b().d();
            kotlin.jvm.internal.g.a((Object) str, "RPToolUtil.getInstance().device.ip");
        }
        String str2 = "http://" + str + rPFile.getPath_dev();
        ImageView imageView = this.d;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setEnabled(false);
        Intent intent = new Intent(this, (Class<?>) VideoClipActivity.class);
        intent.putExtra(com.yiaction.common.a.a.f4927a, VideoEditImpl.a(CameraApplication.f1401a.i(), VideoEditImpl.a(str2), null));
        startActivity(intent);
        StatisticHelper.a();
    }

    public final void b(boolean z) {
        this.p = z;
    }

    public final void c(int i) {
        this.m = i;
    }

    public final RelativeLayout f() {
        return this.k;
    }

    public final TextView g() {
        return this.l;
    }

    public final int h() {
        return this.m;
    }

    public final CameraMediaItemAdapter i() {
        return this.n;
    }

    public final List<RPFile> j() {
        return this.o;
    }

    public final void k() {
        View findViewById = findViewById(R.id.titleBar);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.CustomTitleBar");
        }
        this.c = (CustomTitleBar) findViewById;
        CustomTitleBar customTitleBar = this.c;
        if (customTitleBar == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBar.setTitleClickListener(new b());
        View findViewById2 = findViewById(R.id.imgEdit);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.d = (ImageView) findViewById2;
        ImageView imageView = this.d;
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        View findViewById3 = findViewById(R.id.ivDownload);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.e = (ImageView) findViewById3;
        ImageView imageView2 = this.e;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        ImageView imageView3 = this.e;
        if (imageView3 != null) {
            imageView3.setOnClickListener(this);
        }
        View findViewById4 = findViewById(R.id.fileName);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.h = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.imgDelete);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.f = (ImageView) findViewById5;
        ImageView imageView4 = this.f;
        if (imageView4 != null) {
            imageView4.setOnClickListener(this);
        }
        View findViewById6 = findViewById(R.id.imgVideoPuzzle);
        if (findViewById6 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.g = (ImageView) findViewById6;
        ImageView imageView5 = this.g;
        if (imageView5 != null) {
            imageView5.setOnClickListener(this);
        }
        View findViewById7 = findViewById(R.id.vpCameraMediaShow);
        if (findViewById7 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.ExtendedViewPager");
        }
        this.j = (ExtendedViewPager) findViewById7;
        View findViewById8 = findViewById(R.id.videoDuration);
        if (findViewById8 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.i = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.rlBlock);
        if (findViewById9 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.k = (RelativeLayout) findViewById9;
        RelativeLayout relativeLayout = this.k;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setOnTouchListener(c.f4520a);
        View findViewById10 = findViewById(R.id.tvInfoCameraAlbum);
        if (findViewById10 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.l = (TextView) findViewById10;
    }

    public final void l() {
        List<RPFile> list = this.o;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        a(list.get(this.m));
        this.n = new CameraMediaItemAdapter(this, this.o);
        ExtendedViewPager extendedViewPager = this.j;
        if (extendedViewPager != null) {
            extendedViewPager.setAdapter(this.n);
        }
        ExtendedViewPager extendedViewPager2 = this.j;
        if (extendedViewPager2 != null) {
            extendedViewPager2.setCurrentItem(this.m);
        }
        ExtendedViewPager extendedViewPager3 = this.j;
        if (extendedViewPager3 != null) {
            extendedViewPager3.setOnPageChangeListener(this);
        }
        ExtendedViewPager extendedViewPager4 = this.j;
        if (extendedViewPager4 != null) {
            extendedViewPager4.setPageMargin(getResources().getDimensionPixelSize(R.dimen.length_10));
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.p) {
            setResult(-1);
        }
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        kotlin.jvm.internal.g.b(view, "v");
        switch (view.getId()) {
            case R.id.imgDelete /* 2131755649 */:
                List<RPFile> list = this.o;
                if (list == null) {
                    kotlin.jvm.internal.g.a();
                }
                b(list.get(this.m));
                return;
            case R.id.ivDownload /* 2131755650 */:
                List<RPFile> list2 = this.o;
                if (list2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                c(list2.get(this.m));
                return;
            case R.id.imgEdit /* 2131755651 */:
                List<RPFile> list3 = this.o;
                if (list3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                d(list3.get(this.m));
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_media_show);
        this.m = getIntent().getIntExtra(this.b, 0);
        this.o = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a();
        if (this.m >= 0 && this.o != null) {
            List<RPFile> list = this.o;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            if (list.size() > this.m) {
                k();
                l();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("photo_taken"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("self_capture_stop"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("burst_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("video_record_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("precise_cont_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("exit_album"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
                registerReceiver(this.q, intentFilter);
                com.ants360.z13.b.a.a(this);
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.a(this.r);
                return;
            }
        }
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b(this.r);
        unregisterReceiver(this.q);
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.m = i;
        int i2 = this.m;
        List<RPFile> list = this.o;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        if (i2 >= list.size()) {
            List<RPFile> list2 = this.o;
            if (list2 == null) {
                kotlin.jvm.internal.g.a();
            }
            this.m = list2.size();
        }
        List<RPFile> list3 = this.o;
        if (list3 == null) {
            kotlin.jvm.internal.g.a();
        }
        a(list3.get(this.m));
    }
}
