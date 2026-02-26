package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.ants.video.f.Functions;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.util.m;
import com.ants360.z13.widget.CustomTitleBar;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.b;
import com.xiaoyi.camera.d.e;
import com.yiaction.common.util.g;
import de.greenrobot.event.c;
import io.realm.CameraDevice;
import io.realm.CameraDeviceType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.a.i;
import rx.d;
import rx.j;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class CameraDeviceUpgradeActivity extends BaseActivity {
    private a c;
    private List<CameraDevice> d;

    @BindView(R.id.deviceRV)
    RecyclerView deviceRV;

    @BindView(R.id.rlBlockingCover)
    RelativeLayout rlBlockingCover;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    @BindView(R.id.tvInfo)
    TextView tvInfo;

    @BindView(R.id.tvStatus)
    TextView tvStatus;
    private List<CameraDevice> e = new ArrayList();
    private List<String> f = new ArrayList();
    private Handler g = new Handler(Looper.getMainLooper());
    public BroadcastReceiver b = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) CameraDeviceUpgradeActivity.this.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    if (CameraDeviceUpgradeActivity.this.c != null) {
                        CameraDeviceUpgradeActivity.this.c.b();
                    }
                } else if (e.b(context) && e.c(context) && CameraDeviceUpgradeActivity.this.c != null) {
                    CameraDeviceUpgradeActivity.this.c.b();
                }
            }
        }
    };

    private d<Boolean> a(final Context context) {
        return d.a((d.a) new d.a<Boolean>() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.2
            @Override // rx.a.b
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void call(j<? super Boolean> jVar) {
                g.a(BuildConfig.BUILD_TYPE, "checkNetStatus", new Object[0]);
                if (e.e(context)) {
                    jVar.onNext(true);
                } else {
                    jVar.onError(new Throwable());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.tvStatus.setCompoundDrawables(null, drawable, null, null);
        this.tvStatus.setText(i2);
        this.tvStatus.setVisibility(0);
        this.deviceRV.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    CameraDeviceUpgradeActivity.this.rlBlockingCover.setVisibility(0);
                    CameraDeviceUpgradeActivity.this.tvInfo.setText(str);
                } else {
                    CameraDeviceUpgradeActivity.this.rlBlockingCover.setVisibility(8);
                    CameraDeviceUpgradeActivity.this.tvInfo.setText("");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g() {
        this.g.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.8
            @Override // java.lang.Runnable
            public void run() {
                CameraDeviceUpgradeActivity.this.a(false, (String) null);
                com.xiaomi.xy.sportscamera.camera.d a2 = com.xiaomi.xy.sportscamera.camera.d.a();
                for (CameraDevice cameraDevice : CameraDeviceUpgradeActivity.this.d) {
                    if (!m.b().equals("cn") || !CameraDeviceType.DeviceType.ACTION_J22.toString().equals(cameraDevice.realmGet$deviceType())) {
                        String a3 = a2.a(cameraDevice);
                        String d = a2.d(a3);
                        if (com.xiaoyi.camera.module.a.b(cameraDevice.realmGet$deviceSwVersion(), d) && !CameraDeviceUpgradeActivity.this.f.contains(a3)) {
                            CameraDeviceUpgradeActivity.this.f.add(a3);
                            CameraDeviceUpgradeActivity.this.e.add(cameraDevice);
                        }
                        a2.a(cameraDevice.realmGet$deviceType(), d, false);
                    }
                }
                if (CameraDeviceUpgradeActivity.this.e.isEmpty()) {
                    CameraDeviceUpgradeActivity.this.a(R.drawable.ic_firmware_no_update, R.string.camera_firmware_no_upgrade);
                    return;
                }
                if (CameraDeviceUpgradeActivity.this.c == null) {
                    CameraDeviceUpgradeActivity.this.c = new a(CameraDeviceUpgradeActivity.this);
                    CameraDeviceUpgradeActivity.this.deviceRV.setAdapter(CameraDeviceUpgradeActivity.this.c);
                }
                CameraDeviceUpgradeActivity.this.c.a(CameraDeviceUpgradeActivity.this.e);
                CameraDeviceUpgradeActivity.this.tvStatus.setVisibility(8);
                CameraDeviceUpgradeActivity.this.tvStatus.setOnClickListener(null);
                CameraDeviceUpgradeActivity.this.deviceRV.setVisibility(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (!e.e(this)) {
            a(R.drawable.ic_firmware_no_network, R.string.camera_firmware_no_network);
            if (b.b) {
                f();
            } else {
                i();
            }
            this.tvStatus.setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CameraDeviceUpgradeActivity.this.h();
                }
            });
            return;
        }
        com.xiaoyi.camera.d.b bVar = new com.xiaoyi.camera.d.b();
        this.d = bVar.a("cameraType", CameraDeviceType.CameraType.CAMERA_ACTION.toString());
        bVar.a();
        if (this.d == null || this.d.isEmpty()) {
            a(R.drawable.ic_firmware_no_device, R.string.camera_firmware_no_device);
        } else {
            j();
        }
        this.tvStatus.setOnClickListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        a(true, getString(R.string.prompt_switching_network));
        CameraApplication.f1401a.c(false);
        c.a().c(new com.xiaoyi.camera.a.a(false));
        a((Context) this).b(rx.android.b.a.a()).a(rx.android.b.a.a()).g(new i<d<? extends Throwable>, d<?>>() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.13
            @Override // rx.a.i
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public d<?> call(d<? extends Throwable> dVar) {
                g.a(BuildConfig.BUILD_TYPE, "Retry check", new Object[0]);
                return dVar.d(15).b(1L, TimeUnit.SECONDS);
            }
        }).a(rx.android.b.a.a()).a(new rx.a.b<Boolean>() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.11
            @Override // rx.a.b
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void call(Boolean bool) {
                g.a(BuildConfig.BUILD_TYPE, "switch success", new Object[0]);
                CameraDeviceUpgradeActivity.this.a(false, (String) null);
                CameraDeviceUpgradeActivity.this.h();
            }
        }, Functions.a(), new rx.a.a() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.12
            @Override // rx.a.a
            public void a() {
                g.a(BuildConfig.BUILD_TYPE, "switch failed", new Object[0]);
                CameraDeviceUpgradeActivity.this.a(false, (String) null);
                Toast.makeText(CameraDeviceUpgradeActivity.this, R.string.network_switch_failed, 0).show();
            }
        });
        return false;
    }

    private void j() {
        a(true, getString(R.string.loading_data));
        g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- checkUpgrade", new Object[0]);
        for (int i = 0; i < this.d.size(); i++) {
            final CameraDevice cameraDevice = this.d.get(i);
            if (m.b().equals("cn") && CameraDeviceType.DeviceType.ACTION_J22.toString().equals(cameraDevice.realmGet$deviceType())) {
                a(false, "");
            } else {
                this.g.postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- checkFirmwareVersion", new Object[0]);
                        CameraDeviceUpgradeActivity.this.a(cameraDevice);
                    }
                }, 500 * i);
            }
        }
    }

    public void a(CameraDevice cameraDevice) {
        com.xiaomi.xy.sportscamera.camera.d.a().a(new com.xiaoyi.camera.c.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.4
            @Override // com.xiaoyi.camera.c.c
            public void a() {
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- onNotNeedUpdate", new Object[0]);
                CameraDeviceUpgradeActivity.this.g();
            }

            @Override // com.xiaoyi.camera.c.c
            public void b() {
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- onNeedUpgrade", new Object[0]);
                CameraDeviceUpgradeActivity.this.g();
            }

            @Override // com.xiaoyi.camera.c.c
            public void c() {
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- onNeedDownload", new Object[0]);
                CameraDeviceUpgradeActivity.this.g();
            }

            @Override // com.xiaoyi.camera.c.c
            public void d() {
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- onNeedConnectCammera", new Object[0]);
                CameraDeviceUpgradeActivity.this.g();
            }

            @Override // com.xiaoyi.camera.c.c
            public void e() {
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- onCheckFailed", new Object[0]);
                CameraDeviceUpgradeActivity.this.g();
            }
        }, cameraDevice.realmGet$deviceSn(), cameraDevice, CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(cameraDevice.realmGet$deviceType()) ? "Z18" : CameraDeviceType.DeviceType.ACTION_4K.toString().equals(cameraDevice.realmGet$deviceType()) ? "Z16" : CameraDeviceType.DeviceType.ACTION_J11.toString().equals(cameraDevice.realmGet$deviceType()) ? "J11" : CameraDeviceType.DeviceType.ACTION_J22.toString().equals(cameraDevice.realmGet$deviceType()) ? "J22" : "Z13");
    }

    public void f() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.please_disconnect_camera));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("right_button", getString(R.string.confirm));
        bundle.putString("left_button", getString(R.string.cancel));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.10
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraDeviceUpgradeActivity.this.i();
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
        customBottomDialogFragment.a(this);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_device_upgrade);
        this.titleBar.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                CameraDeviceUpgradeActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.rlBlockingCover.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.b(1);
        this.deviceRV.setLayoutManager(linearLayoutManager);
        h();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.b, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
