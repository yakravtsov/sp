package com.xiaomi.xy.sportscamera.camera.album;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import kotlin.TypeCastException;
import kotlin.g;

@g(a = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraDownloadActivity;", "Lcom/ants360/z13/activity/BaseActivity;", "()V", "fragment", "Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment;", "titleBar", "Lcom/ants360/z13/widget/CustomTitleBar;", "tran", "Landroid/support/v4/app/FragmentTransaction;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class QZCameraDownloadActivity extends BaseActivity {
    private FragmentTransaction b;
    private QZAlbumDownloadFragment c;
    private CustomTitleBar d;

    @g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZCameraDownloadActivity$onCreate$1", "Lcom/ants360/z13/widget/CustomTitleBar$TitleClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZCameraDownloadActivity;)V", "onLeftClick", "", "onMiddleClick", "onRightClick", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a implements CustomTitleBar.a {
        a() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void a() {
            QZCameraDownloadActivity.this.onBackPressed();
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void b() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBar.a
        public void c() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_file_download_activity);
        this.b = getSupportFragmentManager().beginTransaction();
        this.c = new QZAlbumDownloadFragment();
        FragmentTransaction fragmentTransaction = this.b;
        if (fragmentTransaction != null) {
            fragmentTransaction.add(R.id.container, this.c);
        }
        FragmentTransaction fragmentTransaction2 = this.b;
        if (fragmentTransaction2 != null) {
            fragmentTransaction2.commit();
        }
        View findViewById = findViewById(R.id.titleBar);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.CustomTitleBar");
        }
        this.d = (CustomTitleBar) findViewById;
        CustomTitleBar customTitleBar = this.d;
        if (customTitleBar != null) {
            customTitleBar.setTitleClickListener(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        FragmentTransaction fragmentTransaction = this.b;
        if (fragmentTransaction != null) {
            fragmentTransaction.remove(this.c);
        }
    }
}
