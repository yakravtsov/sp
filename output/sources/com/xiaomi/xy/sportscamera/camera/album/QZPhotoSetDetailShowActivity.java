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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.album.AlbumDownLoadActivity;
import com.ants360.z13.c.d;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.widget.CustomTitleBar;
import com.ants360.z13.widget.ExtendedViewPager;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.rp.rpfiledatapool.model.RPFile;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a;
import com.xiaoyi.camera.b;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.g;

@g(a = {"\u0000s\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005*\u0001\u0018\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u00010B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0016J\u0012\u0010$\u001a\u00020\u001d2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010'\u001a\u00020\u001dH\u0014J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u0012H\u0016J \u0010*\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0012H\u0016J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u0012H\u0016J\b\u0010/\u001a\u00020\u001dH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0018\u00010\u0010R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity;", "Lcom/ants360/z13/activity/BaseActivity;", "Landroid/support/v4/view/ViewPager$OnPageChangeListener;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaItemAdapter;", "fileName", "Landroid/widget/TextView;", "imgDownload", "Landroid/widget/ImageView;", "imgEdit", "imgMediaDelete", "mFileItem", "Lcom/rp/rpfiledatapool/model/RPFile;", "mPhotoController", "Lcom/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity$PhotoController;", "mPos", "", "mViewPager", "Lcom/ants360/z13/widget/ExtendedViewPager;", "photoList", "", "receiver", "com/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity$receiver$1", "Lcom/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity$receiver$1;", "titleBar", "Lcom/ants360/z13/widget/CustomTitleBar;", "deletePhoto", "", "downloadPhoto", "handleDeleteSuccess", "initView", "onClick", Promotion.ACTION_VIEW, "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPageScrollStateChanged", "arg0", "onPageScrolled", "arg1", "", "arg2", "onPageSelected", "updateDownloadStatus", "PhotoController", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class QZPhotoSetDetailShowActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private int b;
    private CameraMediaItemAdapter c;
    private RPFile d;
    private a e;
    private List<RPFile> f;
    private ExtendedViewPager g;
    private CustomTitleBar h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private TextView l;
    private final QZPhotoSetDetailShowActivity$receiver$1 m = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.album.QZPhotoSetDetailShowActivity$receiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            kotlin.jvm.internal.g.b(context, "context");
            kotlin.jvm.internal.g.b(intent, "intent");
            String action = intent.getAction();
            if (b.b) {
                if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("start_photo_capture"), (Object) action) || kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("start_video_record"), (Object) action) || kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("enter_album"), (Object) action)) {
                    QZPhotoSetDetailShowActivity.this.finish();
                    return;
                }
                if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.a.a("sd_card_status"), (Object) action)) {
                    String str = (String) null;
                    try {
                        str = intent.getStringExtra("current_operation_model");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (kotlin.jvm.internal.g.a((Object) ProductAction.ACTION_REMOVE, (Object) str)) {
                        QZPhotoSetDetailShowActivity.this.finish();
                    }
                }
            }
        }
    };

    @g(a = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity$PhotoController;", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity;)V", "deleteFailed", "", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "deleteSuccess", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public final class a extends com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a {

        @g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* renamed from: com.xiaomi.xy.sportscamera.camera.album.QZPhotoSetDetailShowActivity$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static final class RunnableC0189a implements Runnable {
            RunnableC0189a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                ExtendedViewPager extendedViewPager = QZPhotoSetDetailShowActivity.this.g;
                if (extendedViewPager == null) {
                    kotlin.jvm.internal.g.a();
                }
                extendedViewPager.setScrollEnable(true);
                CameraMediaItemAdapter cameraMediaItemAdapter = QZPhotoSetDetailShowActivity.this.c;
                if (cameraMediaItemAdapter == null) {
                    kotlin.jvm.internal.g.a();
                }
                cameraMediaItemAdapter.notifyDataSetChanged();
                QZPhotoSetDetailShowActivity.this.a(R.string.delete_failed);
            }
        }

        @g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                QZPhotoSetDetailShowActivity.this.i();
            }
        }

        public a() {
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void b(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.b(rPFile);
            QZPhotoSetDetailShowActivity.this.runOnUiThread(new b());
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void c(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.c(rPFile);
            QZPhotoSetDetailShowActivity.this.runOnUiThread(new RunnableC0189a());
        }
    }

    @g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity$deletePhoto$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class b implements DimPanelFragment.c {
        b() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b bVar = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a;
            RPFile rPFile = QZPhotoSetDetailShowActivity.this.d;
            if (rPFile == null) {
                kotlin.jvm.internal.g.a();
            }
            bVar.b(rPFile);
            de.greenrobot.event.c.a().c(new d());
            ExtendedViewPager extendedViewPager = QZPhotoSetDetailShowActivity.this.g;
            if (extendedViewPager == null) {
                kotlin.jvm.internal.g.a();
            }
            extendedViewPager.setScrollEnable(false);
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

    @g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity$initView$1", "Lcom/ants360/z13/widget/CustomTitleBar$TitleClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZPhotoSetDetailShowActivity;)V", "onLeftClick", "", "onMiddleClick", "onRightClick", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class c implements CustomTitleBar.a {
        c() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void a() {
            QZPhotoSetDetailShowActivity.this.finish();
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void b() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void c() {
        }
    }

    private final void f() {
        View findViewById = findViewById(R.id.titleBar);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.CustomTitleBar");
        }
        this.h = (CustomTitleBar) findViewById;
        View findViewById2 = findViewById(R.id.imgDelete);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.i = (ImageView) findViewById2;
        View findViewById3 = findViewById(R.id.imgEdit);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.j = (ImageView) findViewById3;
        View findViewById4 = findViewById(R.id.ivDownload);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.k = (ImageView) findViewById4;
        ImageView imageView = this.k;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setVisibility(0);
        View findViewById5 = findViewById(R.id.fileName);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.l = (TextView) findViewById5;
        CustomTitleBar customTitleBar = this.h;
        if (customTitleBar == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBar.setTitleClickListener(new c());
        ImageView imageView2 = this.i;
        if (imageView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView2.setOnClickListener(this);
        ImageView imageView3 = this.j;
        if (imageView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView3.setOnClickListener(this);
        ImageView imageView4 = this.k;
        if (imageView4 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView4.setOnClickListener(this);
        View findViewById6 = findViewById(R.id.vpCameraMediaShow);
        if (findViewById6 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.ExtendedViewPager");
        }
        this.g = (ExtendedViewPager) findViewById6;
        this.c = new CameraMediaItemAdapter(this, this.f);
        ExtendedViewPager extendedViewPager = this.g;
        if (extendedViewPager == null) {
            kotlin.jvm.internal.g.a();
        }
        extendedViewPager.setAdapter(this.c);
        ExtendedViewPager extendedViewPager2 = this.g;
        if (extendedViewPager2 == null) {
            kotlin.jvm.internal.g.a();
        }
        extendedViewPager2.setCurrentItem(this.b);
        ExtendedViewPager extendedViewPager3 = this.g;
        if (extendedViewPager3 == null) {
            kotlin.jvm.internal.g.a();
        }
        extendedViewPager3.setOnPageChangeListener(this);
        ExtendedViewPager extendedViewPager4 = this.g;
        if (extendedViewPager4 == null) {
            kotlin.jvm.internal.g.a();
        }
        extendedViewPager4.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
    }

    private final void g() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.title_delete));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.sure_to_delete));
        Fragment instantiate = Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        if (instantiate == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.fragment.CustomBottomDialogFragment");
        }
        CustomBottomDialogFragment customBottomDialogFragment = (CustomBottomDialogFragment) instantiate;
        customBottomDialogFragment.a(new b());
        customBottomDialogFragment.a(this);
    }

    private final void h() {
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d dVar = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a;
        RPFile rPFile = this.d;
        if (rPFile == null) {
            kotlin.jvm.internal.g.a();
        }
        dVar.b(rPFile);
        j();
        a(R.string.file_add_to_download_quee);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i() {
        ExtendedViewPager extendedViewPager = this.g;
        if (extendedViewPager == null) {
            kotlin.jvm.internal.g.a();
        }
        extendedViewPager.setScrollEnable(true);
        de.greenrobot.event.c.a().c(new d());
        List<RPFile> list = this.f;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        list.remove(this.b);
        List<RPFile> list2 = this.f;
        if (list2 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (list2.isEmpty()) {
            finish();
            return;
        }
        CameraMediaItemAdapter cameraMediaItemAdapter = this.c;
        if (cameraMediaItemAdapter != null) {
            cameraMediaItemAdapter.a(this.f);
        }
        int i = this.b;
        List<RPFile> list3 = this.f;
        if (list3 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (i >= list3.size()) {
            List<RPFile> list4 = this.f;
            if (list4 == null) {
                kotlin.jvm.internal.g.a();
            }
            this.b = list4.size();
        }
        List<RPFile> list5 = this.f;
        if (list5 == null) {
            kotlin.jvm.internal.g.a();
        }
        this.d = list5.get(this.b);
        j();
    }

    private final void j() {
        TextView textView = this.l;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        RPFile rPFile = this.d;
        if (rPFile == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText(rPFile.getName());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        kotlin.jvm.internal.g.b(view, Promotion.ACTION_VIEW);
        switch (view.getId()) {
            case R.id.tvMediaDownloadStats /* 2131755183 */:
                startActivity(new Intent(this, (Class<?>) AlbumDownLoadActivity.class));
                return;
            case R.id.imgDelete /* 2131755649 */:
                g();
                return;
            case R.id.ivDownload /* 2131755650 */:
                h();
                return;
            case R.id.imgEdit /* 2131755651 */:
                String str = kotlin.jvm.internal.g.a((Object) "Z16", (Object) com.xiaoyi.camera.g.a().a("model")) ? "YDXJ 2" : kotlin.jvm.internal.g.a((Object) "Z18", (Object) com.xiaoyi.camera.g.a().a("model")) ? "YIAC 3" : "";
                QZPhotoSetDetailShowActivity qZPhotoSetDetailShowActivity = this;
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b bVar = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a;
                RPFile rPFile = this.d;
                if (rPFile == null) {
                    kotlin.jvm.internal.g.a();
                }
                String a2 = bVar.a(rPFile);
                RPFile rPFile2 = this.d;
                if (rPFile2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                com.ants360.z13.d.a.a(qZPhotoSetDetailShowActivity, a2, rPFile2.getName(), "Z16".equals(com.xiaoyi.camera.g.a().a("model")), str);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_photo_show_activity);
        String stringExtra = getIntent().getStringExtra("nameType");
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        this.b = getIntent().getIntExtra("pos", 0);
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b bVar = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a;
        kotlin.jvm.internal.g.a((Object) stringExtra, "nameType");
        this.f = bVar.c(stringExtra);
        if (this.f != null) {
            List<RPFile> list = this.f;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!list.isEmpty()) {
                if (this.b >= 0) {
                    int i = this.b;
                    List<RPFile> list2 = this.f;
                    if (list2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (i < list2.size()) {
                        List<RPFile> list3 = this.f;
                        if (list3 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        this.d = list3.get(this.b);
                        this.e = new a();
                        a.C0193a c0193a = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b;
                        a aVar = this.e;
                        if (aVar == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        c0193a.a(aVar);
                        f();
                        j();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
                        intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
                        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
                        intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
                        registerReceiver(this.m, intentFilter);
                        return;
                    }
                }
                finish();
                return;
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.m);
        a.C0193a c0193a = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b;
        a aVar = this.e;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        c0193a.b(aVar);
        if (this.g != null) {
            ExtendedViewPager extendedViewPager = this.g;
            if (extendedViewPager == null) {
                kotlin.jvm.internal.g.a();
            }
            extendedViewPager.removeAllViews();
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.b = i;
        int i2 = this.b;
        List<RPFile> list = this.f;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        if (i2 >= list.size()) {
            List<RPFile> list2 = this.f;
            if (list2 == null) {
                kotlin.jvm.internal.g.a();
            }
            this.b = list2.size();
        }
        List<RPFile> list3 = this.f;
        if (list3 == null) {
            kotlin.jvm.internal.g.a();
        }
        this.d = list3.get(this.b);
        j();
    }
}
