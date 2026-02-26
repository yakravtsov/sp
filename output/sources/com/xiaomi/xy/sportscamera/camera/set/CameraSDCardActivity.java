package com.xiaomi.xy.sportscamera.camera.set;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ants360.z13.activity.BaseConfigActivity;
import com.ants360.z13.fragment.CustomBottomConfirmDialogFragment;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.widget.CustomTitleBar;
import com.coremedia.iso.boxes.FreeBox;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.c.a;
import com.xiaoyi.camera.d;
import com.xiaoyi.camera.g;
import de.greenrobot.event.c;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class CameraSDCardActivity extends BaseConfigActivity implements View.OnClickListener, a {
    private CustomTitleBar b;
    private TextView c;
    private TextView d;
    private TextView e;
    private double f;
    private double g;
    private boolean h;
    private boolean i;
    private ProgressBar j;
    private View k;
    private View l;
    private RelativeLayout m;
    private TextView n;
    private CustomBottomDialogFragment o;
    private CustomBottomConfirmDialogFragment p;
    private BroadcastReceiver q = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action) || com.xiaoyi.camera.a.a("start_photo_capture").equals(action) || com.xiaoyi.camera.a.a("start_usb_storage").equals(action) || com.xiaoyi.camera.a.a("start_fwupdate").equals(action)) {
                CameraSDCardActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("sd_card_status").equals(action)) {
                String stringExtra = intent.getStringExtra("param");
                if (ProductAction.ACTION_REMOVE.equals(stringExtra)) {
                    CameraSDCardActivity.this.finish();
                    return;
                } else {
                    if ("insert".equals(stringExtra)) {
                        CameraSDCardActivity.this.f();
                        return;
                    }
                    return;
                }
            }
            if (com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                CameraSDCardActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_album").equals(action)) {
                CameraSDCardActivity.this.a(false, "");
                try {
                    if (Integer.parseInt(intent.getStringExtra("param")) > 0) {
                        CameraSDCardActivity.this.f();
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (com.xiaoyi.camera.a.a("sdcard_format_done").equals(action)) {
                new Handler().postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraSDCardActivity.this.f();
                    }
                }, 1000L);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_fwupdate").equals(action)) {
                CameraSDCardActivity.this.a(true, CameraSDCardActivity.this.getString(R.string.firmware_update_info));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_fwupdate").equals(action)) {
                CameraSDCardActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_sdformat").equals(action)) {
                CameraSDCardActivity.this.a(true, CameraSDCardActivity.this.getString(R.string.sd_card_format));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_sdformat").equals(action)) {
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_mvrecover").equals(action)) {
                CameraSDCardActivity.this.a(true, CameraSDCardActivity.this.getString(R.string.video_file_recovery));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_mvrecover").equals(action)) {
                CameraSDCardActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_start").equals(action)) {
                CameraSDCardActivity.this.a(true, CameraSDCardActivity.this.getString(R.string.camera_sdcard_optimize_start));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_stop").equals(action)) {
                CameraSDCardActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_usb_storage").equals(action)) {
                CameraSDCardActivity.this.a(true, CameraSDCardActivity.this.getString(R.string.camera_start_usb_storage_mode));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_usb_storage").equals(action)) {
                CameraSDCardActivity.this.a(false, (String) null);
            } else if (com.xiaoyi.camera.a.a("voice_control_sample_start").equals(action)) {
                CameraSDCardActivity.this.a(true, CameraSDCardActivity.this.getString(R.string.camera_voice_control));
            } else if (com.xiaoyi.camera.a.a("voice_control_sample_stop").equals(action)) {
                CameraSDCardActivity.this.a(false, (String) null);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    CameraSDCardActivity.this.m.setVisibility(0);
                    CameraSDCardActivity.this.n.setText(str);
                } else {
                    CameraSDCardActivity.this.m.setVisibility(8);
                    CameraSDCardActivity.this.n.setText("");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.6
            @Override // java.lang.Runnable
            public void run() {
                CameraSDCardActivity.this.a(true, "");
                CameraSDCardActivity.this.h = false;
                CameraSDCardActivity.this.i = false;
                g.a().d("sd_card_status", CameraSDCardActivity.this);
                if ("Z16".equals(g.a().a("model")) || "Z18".equals(g.a().a("model"))) {
                    g.a().u(CameraSDCardActivity.this);
                }
                if (CameraSDCardActivity.this.p == null || CameraSDCardActivity.this.p.getDialog() == null || !CameraSDCardActivity.this.p.isAdded()) {
                    return;
                }
                CameraSDCardActivity.this.p.dismiss();
            }
        });
    }

    private void g() {
        a(false, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        g();
        if (this.f == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.l.setVisibility(8);
        }
        a(R.string.get_sd_card_info_failed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        g();
        if (this.f == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.l.setVisibility(8);
        }
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.insert_sd_card_first));
        this.p = (CustomBottomConfirmDialogFragment) CustomBottomConfirmDialogFragment.instantiate(this, CustomBottomConfirmDialogFragment.class.getName(), bundle);
        this.p.setCancelable(false);
        this.p.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.7
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                CameraSDCardActivity.this.finish();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        this.p.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        g();
        if (this.f == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.l.setVisibility(8);
            return;
        }
        this.l.setVisibility(0);
        this.k.setVisibility(0);
        this.c.setText(getString(R.string.sdcard_size).replaceAll("XX", (((int) (this.g * 10.0d)) / 10.0d) + "").replaceAll("YY", (((int) (this.f * 10.0d)) / 10.0d) + ""));
        double round = Math.round(((this.f - this.g) / this.f) * 1000.0d);
        this.d.setText((round / 10.0d) + "%");
        this.j.setProgress((int) round);
    }

    private void k() {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("title", getString(R.string.format_sdcard_title));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.format_sdcard_msg));
        this.o = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        this.o.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.8
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                g.a().b(CameraSDCardActivity.this);
                CameraSDCardActivity.this.a(true, CameraSDCardActivity.this.getString(R.string.sd_card_format));
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        this.o.a(this);
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(d dVar, final JSONObject jSONObject) {
        g.a().a("sdcard_need_format", "no-need");
        int a2 = dVar.a();
        if (a2 == 5) {
            String str = (String) dVar.a("type");
            int optInt = jSONObject.optInt("param");
            if ("total".equals(str)) {
                this.f = ((optInt * 1.0d) / 1024.0d) / 1024.0d;
                this.h = true;
            } else if (FreeBox.TYPE.equals(str)) {
                this.g = ((optInt * 1.0d) / 1024.0d) / 1024.0d;
                this.i = true;
            }
            if (this.h && this.i) {
                runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.9
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraSDCardActivity.this.j();
                        CameraSDCardActivity.this.a(false, (String) null);
                    }
                });
                return;
            }
            return;
        }
        if (a2 == 4) {
            if ("Z13".equals(g.a().a("model"))) {
                c.a().c(new com.xiaoyi.camera.a.c());
                f();
                return;
            }
            return;
        }
        if (a2 != 1) {
            if (a2 == 16777243) {
                runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.11
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraSDCardActivity.this.e.setText(CameraSDCardActivity.this.getString(R.string.sd_card_available_photo_video, new Object[]{Integer.valueOf(jSONObject.optInt("photo")), Integer.valueOf(jSONObject.optInt("video") / 60)}));
                        CameraSDCardActivity.this.e.setVisibility(0);
                        CameraSDCardActivity.this.a(false, (String) null);
                    }
                });
                return;
            }
            return;
        }
        String optString = jSONObject.optString("type");
        String optString2 = jSONObject.optString("param");
        if ("sd_card_status".equals(optString)) {
            if (!"insert".equals(optString2)) {
                runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.10
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraSDCardActivity.this.i();
                        CameraSDCardActivity.this.a(false, (String) null);
                    }
                });
            } else {
                g.a().a("total", this);
                g.a().a(FreeBox.TYPE, this);
            }
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(d dVar, JSONObject jSONObject) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.2
            @Override // java.lang.Runnable
            public void run() {
                CameraSDCardActivity.this.h();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.formatSDCard /* 2131755718 */:
                if (!"insert".equals(g.a().a("sd_card_status"))) {
                    if (ProductAction.ACTION_REMOVE.equals(g.a().a("sd_card_status"))) {
                        a(R.string.no_sd_card);
                        return;
                    }
                    return;
                } else if (com.xiaomi.xy.sportscamera.camera.a.a().b()) {
                    Toast.makeText(this, R.string.file_downloading_no_format, 0).show();
                    return;
                } else {
                    k();
                    return;
                }
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.camera_sdcard_activity);
        this.b = (CustomTitleBar) findViewById(R.id.titleBar);
        this.b.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                CameraSDCardActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.c = (TextView) findViewById(R.id.tvSDCardSize);
        this.d = (TextView) findViewById(R.id.tvSDCardPercent);
        this.e = (TextView) findViewById(R.id.tvSDCardDesc);
        this.j = (ProgressBar) findViewById(R.id.progressBar);
        this.k = findViewById(R.id.formatSDCard);
        this.k.setOnClickListener(this);
        this.l = findViewById(R.id.sdcardInfo);
        this.m = (RelativeLayout) findViewById(R.id.rlBlockingCover);
        this.m.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity.4
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.n = (TextView) findViewById(R.id.tvInfo);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_format_done"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
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
        registerReceiver(this.q, intentFilter);
        f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.q);
        a(false, "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.o != null && this.o.getDialog() != null && this.o.getDialog().isShowing()) {
            this.o.dismiss();
        }
        if (this.p == null || this.p.getDialog() == null || !this.p.getDialog().isShowing()) {
            return;
        }
        this.p.dismiss();
    }
}
