package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.fragment.ProgressDialogFragment;
import com.ants360.z13.widget.CustomTitleBar;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.d;
import com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity;
import com.xiaoyi.camera.g;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class CameraUpgradeActivity extends BaseActivity implements View.OnClickListener, com.xiaoyi.camera.c.a {
    String b;

    @BindView(R.id.btnUpgrade)
    Button btnUpgrade;
    String c;
    private ProgressDialogFragment d;
    private d e;
    private boolean f = false;
    private boolean g = false;
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action) || com.xiaoyi.camera.a.a("start_photo_capture").equals(action) || com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                CameraUpgradeActivity.this.finish();
            }
        }
    };

    @BindView(R.id.llVersion)
    LinearLayout llVersion;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    @BindView(R.id.tvDeleteVersion)
    TextView tvDeleteVersion;

    @BindView(R.id.tvUpgradeContent)
    TextView tvUpgradeContent;

    @BindView(R.id.tvVersion)
    TextView tvVersion;

    @BindView(R.id.tvVersion1)
    TextView tvVersion1;

    @BindView(R.id.tvVersion2)
    TextView tvVersion2;

    private void d(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, str);
        bundle.putString("right_button", getString(R.string.tvDelete));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity.6
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraUpgradeActivity.this.h();
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

    private void f() {
        this.c = g.a().a("serial_number");
        this.b = g.a().a("model");
        String h = this.e.h(this.b, this.c);
        this.f = this.e.p(this.b, this.c).booleanValue();
        this.g = this.e.q(this.b, this.c).booleanValue();
        if (this.f && this.g) {
            this.llVersion.setVisibility(0);
            this.tvVersion.setVisibility(8);
            this.tvVersion1.setText(this.e.d(h));
            this.tvVersion1.setSelected(true);
            this.tvVersion2.setText(this.e.m(this.b));
            this.tvVersion2.setSelected(false);
            this.tvUpgradeContent.setText(this.e.c(h));
            return;
        }
        this.llVersion.setVisibility(8);
        this.tvVersion.setVisibility(0);
        if (this.f) {
            this.tvVersion1.setSelected(true);
            this.tvVersion2.setSelected(false);
            this.tvVersion.setText(this.e.d(h));
            this.tvUpgradeContent.setText(this.e.c(h));
            return;
        }
        if (!this.g) {
            finish();
            return;
        }
        this.tvVersion1.setSelected(false);
        this.tvVersion2.setSelected(true);
        this.tvVersion.setText(this.e.m(this.b));
        TextView textView = this.tvUpgradeContent;
        d dVar = this.e;
        textView.setText(d.n(this.b));
    }

    private void g() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.sd_card_format));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.sd_card_format_now));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity.2
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                CameraUpgradeActivity.this.startActivity(new Intent(CameraUpgradeActivity.this, (Class<?>) CameraSDCardActivity.class));
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

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        String str = null;
        if (this.tvVersion1.isSelected()) {
            String h = this.e.h(this.b, this.c);
            StringBuilder sb = new StringBuilder();
            d dVar = this.e;
            StringBuilder append = sb.append(d.t(h));
            d dVar2 = this.e;
            str = append.append(d.s(h)).toString();
        } else if (this.tvVersion2.isSelected()) {
            d dVar3 = this.e;
            String l = d.l(this.b);
            StringBuilder sb2 = new StringBuilder();
            d dVar4 = this.e;
            StringBuilder append2 = sb2.append(d.f());
            d dVar5 = this.e;
            str = append2.append(d.s(l)).toString();
        }
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
        f();
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        switch (jSONObject.optInt("msg_id")) {
            case 13:
                final int optInt = jSONObject.optInt("param");
                final String optString = jSONObject.optString("type");
                runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraUpgradeActivity.this.d.dismissAllowingStateLoss();
                        if ("battery".equals(optString) && optInt < 50) {
                            CameraUpgradeActivity.this.a(R.string.battery_lower_than_50_percent);
                            return;
                        }
                        Intent intent = new Intent(CameraUpgradeActivity.this, (Class<?>) CameraUpgradeWaittingActivity.class);
                        if (CameraUpgradeActivity.this.tvVersion1.isSelected()) {
                            String h = CameraUpgradeActivity.this.e.h(CameraUpgradeActivity.this.b, CameraUpgradeActivity.this.c);
                            intent.putExtra("FIRMWARE_SV", CameraUpgradeActivity.this.e.d(h));
                            intent.putExtra("FIRMWARE_MD5", h);
                            StringBuilder sb = new StringBuilder();
                            d unused = CameraUpgradeActivity.this.e;
                            StringBuilder append = sb.append(d.t(h));
                            d unused2 = CameraUpgradeActivity.this.e;
                            intent.putExtra("FIRMWARE_FILE_NAME", append.append(d.s(h)).toString());
                        } else if (CameraUpgradeActivity.this.tvVersion2.isSelected()) {
                            d unused3 = CameraUpgradeActivity.this.e;
                            String l = d.l(CameraUpgradeActivity.this.b);
                            intent.putExtra("FIRMWARE_SV", CameraUpgradeActivity.this.e.m(CameraUpgradeActivity.this.b));
                            intent.putExtra("FIRMWARE_MD5", l);
                            StringBuilder sb2 = new StringBuilder();
                            d unused4 = CameraUpgradeActivity.this.e;
                            StringBuilder append2 = sb2.append(d.f());
                            d unused5 = CameraUpgradeActivity.this.e;
                            intent.putExtra("FIRMWARE_FILE_NAME", append2.append(d.s(l)).toString());
                        }
                        CameraUpgradeActivity.this.startActivity(intent);
                        CameraUpgradeActivity.this.finish();
                    }
                });
                return;
            default:
                return;
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity.4
            @Override // java.lang.Runnable
            public void run() {
                CameraUpgradeActivity.this.d.dismissAllowingStateLoss();
                CameraUpgradeActivity.this.a(R.string.upgrade_one_more);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpgrade /* 2131755296 */:
                if (!"insert".equals(g.a().a("sd_card_status"))) {
                    a(R.string.please_insert_sdcard);
                    return;
                } else if (!"no-need".equals(g.a().a("sdcard_need_format"))) {
                    g();
                    return;
                } else {
                    this.d.a((BaseActivity) this);
                    g.a().j(this);
                    return;
                }
            case R.id.tvVersion1 /* 2131755746 */:
                this.tvVersion1.setSelected(true);
                this.tvVersion2.setSelected(false);
                this.tvUpgradeContent.setText(this.e.c(this.e.h(this.b, this.c)));
                return;
            case R.id.tvVersion2 /* 2131755747 */:
                this.tvVersion1.setSelected(false);
                this.tvVersion2.setSelected(true);
                TextView textView = this.tvUpgradeContent;
                d dVar = this.e;
                textView.setText(d.n(this.b));
                return;
            case R.id.tvDeleteVersion /* 2131755748 */:
                String str = null;
                if (this.tvVersion1.isSelected()) {
                    str = getString(R.string.ignore_firmware_version_tip, new Object[]{this.e.d(this.e.h(this.b, this.c))});
                } else if (this.tvVersion2.isSelected()) {
                    str = getString(R.string.ignore_firmware_version_tip, new Object[]{this.e.m(this.b)});
                }
                d(str);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_upgrade_activity);
        this.e = d.a();
        this.titleBar.setMiddleTitle(R.string.camera_firmware_upgrade);
        this.titleBar.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                CameraUpgradeActivity.this.onBackPressed();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.btnUpgrade.setOnClickListener(this);
        this.tvVersion1.setOnClickListener(this);
        this.tvVersion2.setOnClickListener(this);
        this.tvDeleteVersion.setOnClickListener(this);
        this.tvDeleteVersion.getPaint().setFlags(8);
        f();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        registerReceiver(this.h, intentFilter);
        this.d = new ProgressDialogFragment();
        this.d.a(getString(R.string.check_camera_status));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.h);
    }
}
