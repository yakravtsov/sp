package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.fragment.CustomBottomConfirmDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.util.j;
import com.ants360.z13.widget.CircleBar;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.d;
import com.xiaoyi.camera.c.e;
import com.xiaoyi.camera.d.b;
import com.xiaoyi.camera.f;
import com.xiaoyi.camera.g;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.i;
import de.greenrobot.event.c;
import io.realm.CameraDevice;
import io.realm.CameraDeviceType;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class CameraUpgradeWaittingActivity extends BaseActivity implements View.OnClickListener, com.xiaoyi.camera.c.a {
    CircleBar b;
    ImageView c;
    Button d;
    private TextView k;
    private TextView l;
    private TextView m;
    private File n;
    private File o;
    private d p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private boolean w;
    private g x;
    private f y;
    private long z;
    boolean e = false;
    private List<String> v = new ArrayList();
    String f = "/tmp/fuse_d/";
    String g = "firmware.upload";
    String h = "firmware.bin.gz";
    private BroadcastReceiver A = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("param");
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (com.xiaoyi.camera.a.a("put_file_complete").equals(action)) {
                UploadStatsManager.a("firmware", "firmware_upload_success", elapsedRealtime - CameraUpgradeWaittingActivity.this.z);
                UploadStatsManager.a("firmware", "firmware_upload_result", "firmware_upload_success");
                CameraUpgradeWaittingActivity.this.y.a();
                CameraUpgradeWaittingActivity.this.e = false;
                if (com.xiaoyi.camera.d.a.b()) {
                    CameraUpgradeWaittingActivity.this.x.h(CameraUpgradeWaittingActivity.this.h, CameraUpgradeWaittingActivity.this);
                    CameraUpgradeWaittingActivity.this.j.start();
                } else {
                    CameraApplication.f1401a.c(false);
                    CameraUpgradeWaittingActivity.this.k.setVisibility(4);
                    CameraUpgradeWaittingActivity.this.c.setBackgroundResource(R.drawable.firmware_install);
                    CameraUpgradeWaittingActivity.this.l.setText(R.string.firware_upgrade_patient);
                    if ("Z13".equals(g.a().a("model"))) {
                        CameraUpgradeWaittingActivity.this.m.setVisibility(0);
                    } else {
                        CameraUpgradeWaittingActivity.this.m.setVisibility(4);
                    }
                    CameraUpgradeWaittingActivity.this.d.setVisibility(0);
                    CameraUpgradeWaittingActivity.this.x.h(CameraUpgradeWaittingActivity.this.g, CameraUpgradeWaittingActivity.this);
                }
                i.a().a("force_update" + CameraUpgradeWaittingActivity.this.q + CameraUpgradeWaittingActivity.this.r, true);
                CameraUpgradeWaittingActivity.this.p.a(CameraUpgradeWaittingActivity.this.t, (Boolean) false);
                try {
                    if (CameraUpgradeWaittingActivity.this.n.exists()) {
                        CameraUpgradeWaittingActivity.this.n.delete();
                    }
                    String path = CameraUpgradeWaittingActivity.this.n.getPath();
                    File file = new File(path);
                    if (file.isDirectory()) {
                        d unused = CameraUpgradeWaittingActivity.this.p;
                        if (path.contains(d.t(CameraUpgradeWaittingActivity.this.t))) {
                            file.delete();
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (com.xiaoyi.camera.a.a("timed_out").equals(action)) {
                UploadStatsManager.a("firmware", "firmware_upload_fail", elapsedRealtime - CameraUpgradeWaittingActivity.this.z);
                UploadStatsManager.a("firmware", "firmware_upload_result", "firmware_upload_fail");
                UploadStatsManager.a("firmware", "firmware_upload_fail_reason", "firmware_upload_fail_timeout");
                CameraUpgradeWaittingActivity.this.y.a();
                CameraUpgradeWaittingActivity.this.c(R.string.firmware_upgrade_failed_time_out);
                i.a().a("force_update" + CameraUpgradeWaittingActivity.this.q + CameraUpgradeWaittingActivity.this.r, false);
                return;
            }
            if (com.xiaoyi.camera.a.a("no_space").equals(action)) {
                UploadStatsManager.a("firmware", "firmware_upload_fail", elapsedRealtime - CameraUpgradeWaittingActivity.this.z);
                UploadStatsManager.a("firmware", "firmware_upload_result", "firmware_upload_fail");
                UploadStatsManager.a("firmware", "firmware_upload_fail_reason", "firmware_upload_fail_sdcard_nospace");
                CameraUpgradeWaittingActivity.this.y.a();
                CameraUpgradeWaittingActivity.this.c(R.string.firmware_upgrade_failed_no_space);
                i.a().a("force_update" + CameraUpgradeWaittingActivity.this.q + CameraUpgradeWaittingActivity.this.r, false);
                return;
            }
            if (com.xiaoyi.camera.a.a("put_file_fail").equals(action)) {
                UploadStatsManager.a("firmware", "firmware_upload_fail", elapsedRealtime - CameraUpgradeWaittingActivity.this.z);
                UploadStatsManager.a("firmware", "firmware_upload_result", "firmware_upload_fail");
                UploadStatsManager.a("firmware", "firmware_upload_fail_reason", "firmware_upload_fail_put_file_failed");
                CameraUpgradeWaittingActivity.this.y.a();
                CameraUpgradeWaittingActivity.this.c(R.string.firmware_upgrade_failed_put_file_failed);
                return;
            }
            if (com.xiaoyi.camera.a.a("sd_card_status").equals(action)) {
                if (!ProductAction.ACTION_REMOVE.equals(stringExtra)) {
                    if ("insert".equals(stringExtra)) {
                        CameraUpgradeWaittingActivity.this.c(R.string.sd_card_inserd);
                        return;
                    }
                    return;
                } else {
                    UploadStatsManager.a("firmware", "firmware_upload_fail", elapsedRealtime - CameraUpgradeWaittingActivity.this.z);
                    UploadStatsManager.a("firmware", "firmware_upload_result", "firmware_upload_fail");
                    UploadStatsManager.a("firmware", "firmware_upload_fail_reason", "firmware_upload_fail_sdcard_removed");
                    CameraUpgradeWaittingActivity.this.y.a();
                    CameraUpgradeWaittingActivity.this.c(R.string.firmware_upgrade_failed_sd_card_remove);
                    i.a().a("force_update" + CameraUpgradeWaittingActivity.this.q + CameraUpgradeWaittingActivity.this.r, false);
                    return;
                }
            }
            if (com.xiaoyi.camera.a.a("firmware_unzip_success").equals(action)) {
                if (com.xiaoyi.camera.d.a.b()) {
                    CameraUpgradeWaittingActivity.this.j.cancel();
                    CameraApplication.f1401a.c(false);
                    CameraUpgradeWaittingActivity.this.k.setVisibility(4);
                    CameraUpgradeWaittingActivity.this.c.setBackgroundResource(R.drawable.firmware_install);
                    CameraUpgradeWaittingActivity.this.l.setText(R.string.firware_upgrade_patient);
                    if ("Z13".equals(g.a().a("model"))) {
                        CameraUpgradeWaittingActivity.this.m.setVisibility(0);
                    } else {
                        CameraUpgradeWaittingActivity.this.m.setVisibility(4);
                    }
                    CameraUpgradeWaittingActivity.this.d.setVisibility(0);
                    CameraUpgradeWaittingActivity.this.k.setText(CameraUpgradeWaittingActivity.this.getString(R.string.firmware_upgrade_progress, new Object[]{100}));
                    CameraUpgradeWaittingActivity.this.e = false;
                    CameraUpgradeWaittingActivity.this.b.setSweepAngle((float) (100 * 3.6d));
                    CameraUpgradeWaittingActivity.this.b.a();
                    CameraUpgradeWaittingActivity.this.p.c(CameraUpgradeWaittingActivity.this.q, i.a().a("model") + "_" + CameraUpgradeWaittingActivity.this.s);
                    new Thread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            b bVar = new b();
                            List<CameraDevice> a2 = bVar.a("cameraType", CameraDeviceType.CameraType.CAMERA_ACTION.toString());
                            if (a2 != null && !a2.isEmpty()) {
                                Iterator<CameraDevice> it2 = a2.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    CameraDevice next = it2.next();
                                    if (CameraUpgradeWaittingActivity.this.q.equals(next.realmGet$deviceSn())) {
                                        next.realmSet$deviceSwVersion(CameraDeviceType.CameraType.CAMERA_ACTION.toString() + "_" + CameraUpgradeWaittingActivity.this.s);
                                        bVar.a(next);
                                        break;
                                    }
                                }
                            }
                            bVar.a();
                        }
                    }).start();
                    return;
                }
                return;
            }
            if (com.xiaoyi.camera.a.a("start_fwupdate").equals(action) && com.xiaoyi.camera.d.a.b()) {
                CameraUpgradeWaittingActivity.this.j.cancel();
                CameraApplication.f1401a.c(false);
                CameraUpgradeWaittingActivity.this.k.setVisibility(4);
                CameraUpgradeWaittingActivity.this.c.setBackgroundResource(R.drawable.firmware_install);
                CameraUpgradeWaittingActivity.this.l.setText(R.string.firware_upgrade_patient);
                if ("Z13".equals(g.a().a("model"))) {
                    CameraUpgradeWaittingActivity.this.m.setVisibility(0);
                } else {
                    CameraUpgradeWaittingActivity.this.m.setVisibility(4);
                }
                CameraUpgradeWaittingActivity.this.d.setVisibility(0);
                CameraUpgradeWaittingActivity.this.k.setText(CameraUpgradeWaittingActivity.this.getString(R.string.firmware_upgrade_progress, new Object[]{100}));
                CameraUpgradeWaittingActivity.this.e = false;
                CameraUpgradeWaittingActivity.this.b.setSweepAngle((float) (100 * 3.6d));
                CameraUpgradeWaittingActivity.this.b.a();
                CameraUpgradeWaittingActivity.this.p.a(CameraUpgradeWaittingActivity.this.t, (Boolean) false);
            }
        }
    };
    double i = 90.0d;
    CountDownTimer j = new CountDownTimer(21000, 1000) { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.7
        @Override // android.os.CountDownTimer
        public void onFinish() {
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            CameraUpgradeWaittingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.7.1
                @Override // java.lang.Runnable
                public void run() {
                    CameraUpgradeWaittingActivity.this.i += 0.5d;
                    CameraUpgradeWaittingActivity.this.k.setText(CameraUpgradeWaittingActivity.this.getString(R.string.firmware_upgrade_progress, new Object[]{Integer.valueOf((int) CameraUpgradeWaittingActivity.this.i)}));
                    CameraUpgradeWaittingActivity.this.e = true;
                    CameraUpgradeWaittingActivity.this.b.setSweepAngle((float) (CameraUpgradeWaittingActivity.this.i * 3.6d));
                    CameraUpgradeWaittingActivity.this.b.a();
                }
            });
        }
    };

    private void g() {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.6
            @Override // java.lang.Runnable
            public void run() {
                c.a().c(new com.xiaoyi.camera.a.a());
                com.yiaction.common.util.g.a("debug_event", "CameraUpgradeWaittingActivity post CameraStopSessionEvent", new Object[0]);
                CameraUpgradeWaittingActivity.this.startActivity(new Intent(CameraUpgradeWaittingActivity.this, (Class<?>) MainActivity.class));
                CameraUpgradeWaittingActivity.this.finish();
            }
        });
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        if (dVar.a() != 1286) {
            if (dVar.a() == 16777219) {
            }
            return;
        }
        if (com.xiaoyi.camera.d.a.b()) {
            final long length = this.n.length();
            com.yiaction.common.util.g.a("debug_upgrade", "start upload Gzipfile, file size: " + length, new Object[0]);
            this.y = new f(this.n, new e() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.2
                @Override // com.xiaoyi.camera.c.e
                public void a() {
                    CameraUpgradeWaittingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            CameraUpgradeWaittingActivity.this.a(R.string.firmware_upgrade_failure);
                            CameraUpgradeWaittingActivity.this.finish();
                        }
                    });
                }

                @Override // com.xiaoyi.camera.c.e
                public void a(final int i) {
                    CameraUpgradeWaittingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int i2 = (int) (((int) (((1.0d * i) / length) * 100.0d)) * 0.9d);
                            if (CameraUpgradeWaittingActivity.this.v.contains(String.valueOf(i2))) {
                                return;
                            }
                            CameraUpgradeWaittingActivity.this.v.add(String.valueOf(i2));
                            com.yiaction.common.util.g.a("debug_upgrade", "Gzipfile progress: " + i2, new Object[0]);
                            CameraUpgradeWaittingActivity.this.k.setText(CameraUpgradeWaittingActivity.this.getString(R.string.firmware_upgrade_progress, new Object[]{Integer.valueOf(i2)}));
                            CameraUpgradeWaittingActivity.this.e = true;
                            CameraUpgradeWaittingActivity.this.b.setSweepAngle((float) (i2 * 3.6d));
                            CameraUpgradeWaittingActivity.this.b.a();
                        }
                    });
                }
            });
            this.y.start();
            return;
        }
        final long length2 = this.o.length();
        com.yiaction.common.util.g.a("debug_upgrade", "start upload file, file size: " + length2, new Object[0]);
        this.y = new f(this.o, new e() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.3
            @Override // com.xiaoyi.camera.c.e
            public void a() {
                CameraUpgradeWaittingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraUpgradeWaittingActivity.this.a(R.string.firmware_upgrade_failure);
                        CameraUpgradeWaittingActivity.this.finish();
                    }
                });
            }

            @Override // com.xiaoyi.camera.c.e
            public void a(final int i) {
                CameraUpgradeWaittingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int i2 = (int) (((1.0d * i) / length2) * 100.0d);
                        com.yiaction.common.util.g.a("debug_upgrade", "file progress: " + i2, new Object[0]);
                        CameraUpgradeWaittingActivity.this.k.setText(CameraUpgradeWaittingActivity.this.getString(R.string.firmware_upgrade_progress, new Object[]{Integer.valueOf(i2)}));
                        CameraUpgradeWaittingActivity.this.e = true;
                        CameraUpgradeWaittingActivity.this.b.setSweepAngle((float) (i2 * 3.6d));
                        CameraUpgradeWaittingActivity.this.b.a();
                    }
                });
            }
        });
        this.y.start();
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        if (dVar.a() == 1286) {
            startActivity(new Intent(this, (Class<?>) CameraUpgradeFailActivity.class));
            finish();
        }
    }

    public void c(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(i));
        CustomBottomConfirmDialogFragment customBottomConfirmDialogFragment = (CustomBottomConfirmDialogFragment) CustomBottomConfirmDialogFragment.instantiate(this, CustomBottomConfirmDialogFragment.class.getName(), bundle);
        customBottomConfirmDialogFragment.setCancelable(false);
        customBottomConfirmDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.5
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                CameraUpgradeWaittingActivity.this.finish();
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

    /* JADX WARN: Type inference failed for: r0v18, types: [com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity$1] */
    protected void f() {
        if (this.w) {
            return;
        }
        this.x.p(this);
        this.p = d.a();
        this.q = g.a().a("serial_number");
        this.r = g.a().a("sw_version");
        this.s = getIntent().getStringExtra("FIRMWARE_SV");
        this.t = getIntent().getStringExtra("FIRMWARE_MD5");
        this.u = getIntent().getStringExtra("FIRMWARE_FILE_NAME");
        this.n = new File(this.u);
        this.w = true;
        this.z = SystemClock.elapsedRealtime();
        UploadStatsManager.a("firmware", "firmware_upload_start");
        this.x.g(this.f, this);
        new Thread() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeWaittingActivity.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                d.a();
                if (CameraUpgradeWaittingActivity.this.n.exists()) {
                    if (com.xiaoyi.camera.d.a.b()) {
                        try {
                            CameraUpgradeWaittingActivity.this.x.a(CameraUpgradeWaittingActivity.this.n, CameraUpgradeWaittingActivity.this.h, CameraUpgradeWaittingActivity.this.t, CameraUpgradeWaittingActivity.this);
                            return;
                        } catch (Exception e) {
                            com.yiaction.common.util.g.a("debug_upgrade", "decompress exception", new Object[0]);
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            String replace = CameraUpgradeWaittingActivity.this.u.replace(".gzip", "");
                            CameraUpgradeWaittingActivity.this.o = new File(replace);
                            if (CameraUpgradeWaittingActivity.this.o.exists()) {
                                CameraUpgradeWaittingActivity.this.o.delete();
                            }
                            j.a(CameraUpgradeWaittingActivity.this.n, false);
                            CameraUpgradeWaittingActivity.this.o = new File(replace);
                            if (CameraUpgradeWaittingActivity.this.o.exists()) {
                                com.yiaction.common.util.g.a("debug_upgrade", "decompress file from: " + CameraUpgradeWaittingActivity.this.n.getPath() + " | " + CameraUpgradeWaittingActivity.this.n.length() + " to " + CameraUpgradeWaittingActivity.this.o.getPath() + " | " + CameraUpgradeWaittingActivity.this.o.length(), new Object[0]);
                                if (CameraUpgradeWaittingActivity.this.n.length() * 2 < CameraUpgradeWaittingActivity.this.o.length()) {
                                    UploadStatsManager.a("firmware", "firmware_upload_result", "firmware_upload_fail");
                                    UploadStatsManager.a("firmware", "firmware_upload_fail_reason", "firmware_upload_fail_uzip_failed");
                                    UploadStatsManager.a("firmware", "firmware_upload_fail_uzip_failed", CameraUpgradeWaittingActivity.this.o.length());
                                }
                                CameraUpgradeWaittingActivity.this.x.a(CameraUpgradeWaittingActivity.this.o, CameraUpgradeWaittingActivity.this.g, com.yiaction.common.util.e.a(CameraUpgradeWaittingActivity.this.o), CameraUpgradeWaittingActivity.this);
                                return;
                            }
                            return;
                        } catch (Exception e2) {
                            com.yiaction.common.util.g.a("debug_upgrade", "decompress exception", new Object[0]);
                            e2.printStackTrace();
                        }
                    }
                }
                CameraUpgradeWaittingActivity.this.startActivity(new Intent(CameraUpgradeWaittingActivity.this, (Class<?>) CameraUpgradeFailActivity.class));
                CameraUpgradeWaittingActivity.this.finish();
            }
        }.start();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.e) {
            return;
        }
        g();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancelUpgrade /* 2131755759 */:
                g();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.f1394a = false;
        super.onCreate(bundle);
        getWindow().setFlags(128, 128);
        setContentView(R.layout.camera_upgrade_waiting_activity);
        this.b = (CircleBar) findViewById(R.id.settings_figure);
        this.k = (TextView) findViewById(R.id.tvProgress);
        this.k.setText(getString(R.string.firmware_upgrade_progress, new Object[]{0}));
        this.l = (TextView) findViewById(R.id.tvTips);
        this.m = (TextView) findViewById(R.id.tvcautions);
        this.c = (ImageView) findViewById(R.id.ivfirmware);
        this.d = (Button) findViewById(R.id.btnCancelUpgrade);
        this.d.setOnClickListener(this);
        this.x = g.a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("put_file_complete"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("timed_out"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("no_space"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("put_file_fail"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("firmware_unzip_success"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
        registerReceiver(this.A, intentFilter);
        f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.x.q(this);
        unregisterReceiver(this.A);
    }
}
