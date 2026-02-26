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
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.activity.BaseConfigActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.fragment.CustomBottomConfirmDialogFragment;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.widget.CustomTitleBar;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.rp.rptool.util.d;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.c.a;
import com.xiaoyi.camera.d.f;
import com.xiaoyi.camera.d.g;
import de.greenrobot.event.c;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class CameraWifiSettingActivity extends BaseConfigActivity implements CompoundButton.OnCheckedChangeListener, a {
    private f b;
    private EditText c;
    private EditText d;
    private CheckBox e;
    private String f;
    private String g;
    private String h;
    private String i;
    private CustomTitleBar j;
    private RelativeLayout k;
    private TextView l;
    private CustomBottomDialogFragment m;
    private Handler n = new Handler(Looper.getMainLooper());
    private boolean o = false;
    private boolean p = false;
    private Runnable q = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.5
        @Override // java.lang.Runnable
        public void run() {
            CameraWifiSettingActivity.this.h();
        }
    };
    private BroadcastReceiver r = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action) || com.xiaoyi.camera.a.a("start_photo_capture").equals(action) || com.xiaoyi.camera.a.a("start_usb_storage").equals(action) || com.xiaoyi.camera.a.a("start_fwupdate").equals(action)) {
                CameraWifiSettingActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_sdformat").equals(action)) {
                CameraWifiSettingActivity.this.a(true, CameraWifiSettingActivity.this.getString(R.string.sd_card_format));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_sdformat").equals(action)) {
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_mvrecover").equals(action)) {
                CameraWifiSettingActivity.this.a(true, CameraWifiSettingActivity.this.getString(R.string.video_file_recovery));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_mvrecover").equals(action)) {
                CameraWifiSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_start").equals(action)) {
                CameraWifiSettingActivity.this.a(true, CameraWifiSettingActivity.this.getString(R.string.camera_sdcard_optimize_start));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_stop").equals(action)) {
                CameraWifiSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_usb_storage").equals(action)) {
                CameraWifiSettingActivity.this.a(true, CameraWifiSettingActivity.this.getString(R.string.camera_start_usb_storage_mode));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_usb_storage").equals(action)) {
                CameraWifiSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_start").equals(action)) {
                CameraWifiSettingActivity.this.a(true, CameraWifiSettingActivity.this.getString(R.string.camera_voice_control));
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_stop").equals(action)) {
                CameraWifiSettingActivity.this.a(false, (String) null);
            } else if (com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                CameraWifiSettingActivity.this.finish();
            } else if (com.xiaoyi.camera.a.a("exit_album").equals(action)) {
                CameraWifiSettingActivity.this.a(false, "");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.9
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    CameraWifiSettingActivity.this.k.setVisibility(0);
                    CameraWifiSettingActivity.this.l.setText(str);
                } else {
                    CameraWifiSettingActivity.this.k.setVisibility(8);
                    CameraWifiSettingActivity.this.l.setText("");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            a(R.string.ssid_is_empty);
            return false;
        }
        Matcher matcher = Pattern.compile("^[A-Z0-9a-z_]{1,20}").matcher(str);
        int end = matcher.matches() ? matcher.end() : 0;
        if (end > 0 && end == str.length()) {
            return true;
        }
        a(R.string.ssid_format_error);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            a(R.string.password_is_empty);
            return false;
        }
        Matcher matcher = Pattern.compile("^[A-Z0-9a-z]{8,64}").matcher(str);
        int end = matcher.matches() ? matcher.end() : 0;
        if (end >= 8 && end == str.length()) {
            return true;
        }
        a(R.string.password_format_error);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        g.a().a(this.g, this.h);
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.wifi_set_success));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        CustomBottomConfirmDialogFragment customBottomConfirmDialogFragment = (CustomBottomConfirmDialogFragment) CustomBottomConfirmDialogFragment.instantiate(this, CustomBottomConfirmDialogFragment.class.getName(), bundle);
        customBottomConfirmDialogFragment.setCancelable(false);
        customBottomConfirmDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.3
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                c.a().c(new com.xiaoyi.camera.a.a());
                com.xiaoyi.camera.g.a().c();
                CameraWifiSettingActivity.this.startActivity(new Intent(CameraWifiSettingActivity.this, (Class<?>) MainActivity.class));
                CameraWifiSettingActivity.this.finish();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        customBottomConfirmDialogFragment.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a(false, (String) null);
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.wifi_set_title));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.restart_device_immediately));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        this.m = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        this.m.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.4
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                CameraWifiSettingActivity.this.a(true, "");
                dialogFragment.dismiss();
                CameraWifiSettingActivity.this.o = false;
                CameraWifiSettingActivity.this.p = false;
                if ("J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    d.a().e();
                    d.a().d();
                }
                if (TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("wifi_update"))) {
                    com.xiaoyi.camera.g.a().a("wifi_ssid", CameraWifiSettingActivity.this.g, CameraWifiSettingActivity.this);
                } else {
                    com.xiaoyi.camera.g.a().e(CameraWifiSettingActivity.this.g, CameraWifiSettingActivity.this.h, CameraWifiSettingActivity.this);
                    CameraWifiSettingActivity.this.n.postDelayed(CameraWifiSettingActivity.this.q, 2000L);
                }
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        this.m.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        com.xiaoyi.camera.devicedao.a aVar;
        CameraApplication.f1401a.c(false);
        com.xiaoyi.camera.d.c a2 = com.xiaoyi.camera.d.c.a(this);
        com.xiaoyi.camera.devicedao.a a3 = a2.a(this.i);
        String str = this.f.endsWith("_5G") ? this.g + "_5G" : this.g;
        com.yiaction.common.util.g.a("debug_wifi", "CameraWifiSettingActivity : ssid_temp ＝ " + str, new Object[0]);
        if (a3 != null) {
            a3.b(str);
            a3.c(this.h);
            aVar = a3;
        } else {
            com.xiaoyi.camera.devicedao.a aVar2 = new com.xiaoyi.camera.devicedao.a(this.i, str, this.h);
            com.yiaction.common.util.g.a("debug_wifi", "CameraWifiSettingActivity : sportsCamera == null", new Object[0]);
            aVar = aVar2;
        }
        a2.b(aVar);
        this.b.c(this.f);
        this.n.postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.6
            @Override // java.lang.Runnable
            public void run() {
                CameraWifiSettingActivity.this.a(false, (String) null);
                CameraWifiSettingActivity.this.f();
                com.xiaoyi.camera.g.a().c();
            }
        }, 1000L);
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        String str = (String) dVar.a("type");
        if ("wifi_update".equals(str)) {
            this.o = true;
            this.p = true;
        } else {
            if ("wifi_ssid".equals(str)) {
                com.xiaoyi.camera.g.a().a("wifi_password", this.h, this);
                this.o = true;
            }
            if ("wifi_password".equals(str)) {
                this.p = true;
                com.xiaoyi.camera.g.a().n(this);
            }
        }
        if (this.o && this.p) {
            this.n.removeCallbacksAndMessages(null);
            this.n.postDelayed(this.q, 500L);
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(com.xiaoyi.camera.d dVar, final JSONObject jSONObject) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.7
            @Override // java.lang.Runnable
            public void run() {
                CameraWifiSettingActivity.this.n.removeCallbacks(CameraWifiSettingActivity.this.q);
                CameraWifiSettingActivity.this.a(false, (String) null);
                com.yiaction.common.util.g.a("wifi err" + jSONObject, new Object[0]);
                if (jSONObject != null) {
                    CameraWifiSettingActivity.this.a(R.string.setting_failed);
                }
            }
        });
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.d.setInputType(1);
        } else {
            this.d.setInputType(129);
        }
        if (this.d.getText() != null) {
            this.d.setSelection(this.d.getText().toString().length());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.camera_wifisetting_activity);
        this.j = (CustomTitleBar) findViewById(R.id.titleBar);
        this.j.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                CameraWifiSettingActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
                CameraWifiSettingActivity.this.g = CameraWifiSettingActivity.this.c.getText().toString().trim();
                CameraWifiSettingActivity.this.h = CameraWifiSettingActivity.this.d.getText().toString().trim();
                if (CameraWifiSettingActivity.this.d(CameraWifiSettingActivity.this.g).booleanValue() && CameraWifiSettingActivity.this.e(CameraWifiSettingActivity.this.h).booleanValue()) {
                    if (CameraWifiSettingActivity.this.g.endsWith("_5G")) {
                        CameraWifiSettingActivity.this.g = CameraWifiSettingActivity.this.g.substring(0, CameraWifiSettingActivity.this.g.length() - "_5G".length());
                    }
                    CameraWifiSettingActivity.this.g();
                }
            }
        });
        this.c = (EditText) findViewById(R.id.etSSID);
        this.d = (EditText) findViewById(R.id.etPassWord);
        this.e = (CheckBox) findViewById(R.id.ckbShowPassword);
        this.e.setOnCheckedChangeListener(this);
        this.b = new f(this);
        this.i = this.b.h();
        this.f = com.xiaoyi.camera.g.a().a("wifi_ssid");
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------- mSsid" + this.f, new Object[0]);
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------- mSsid" + this.f, new Object[0]);
        if (!TextUtils.isEmpty(this.f)) {
            this.c.setText(this.f.endsWith("_5G") ? this.f.substring(0, this.f.length() - "_5G".length()) : this.f);
        }
        this.k = (RelativeLayout) findViewById(R.id.rlBlockingCover);
        this.k.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraWifiSettingActivity.2
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.l = (TextView) findViewById(R.id.tvInfo);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
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
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        registerReceiver(this.r, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.r);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.m == null || this.m.getDialog() == null || !this.m.getDialog().isShowing()) {
            return;
        }
        this.m.dismissAllowingStateLoss();
    }
}
