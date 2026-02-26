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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import com.xiaoyi.camera.b;
import com.xiaoyi.camera.d.f;
import com.xiaoyi.camera.module.CameraSettingItem;
import com.yiaction.common.util.g;
import de.greenrobot.event.c;
import java.util.ArrayList;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class CameraResetActivity extends BaseConfigActivity implements AdapterView.OnItemClickListener, com.xiaoyi.camera.c.a {
    private CustomTitleBar b;
    private ListView c;
    private a d;
    private RelativeLayout e;
    private TextView f;
    private boolean g;
    private String h;
    private String i;
    private f j;
    private ArrayList<CameraSettingItem> k;
    private CustomBottomDialogFragment l;
    private CustomBottomDialogFragment m;
    private CustomBottomDialogFragment n;
    private CustomBottomConfirmDialogFragment o;
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            g.a(BuildConfig.BUILD_TYPE, "------------------- BroadcastReceiver action = " + action, new Object[0]);
            if (com.xiaoyi.camera.a.a("start_photo_capture").equals(action)) {
                switch (intent.getIntExtra("current_operation_model", -1)) {
                    case 1:
                        CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.in_normal_capturing));
                        return;
                    case 2:
                        CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.in_burst));
                        return;
                    case 3:
                        CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.in_timeslape));
                        return;
                    case 4:
                        CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.in_timer));
                        return;
                    default:
                        return;
                }
            }
            if (com.xiaoyi.camera.a.a("photo_taken").equals(action)) {
                if (CameraResetActivity.this.g) {
                    return;
                }
                CameraResetActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("burst_complete").equals(action)) {
                CameraResetActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("precise_cont_complete").equals(action)) {
                CameraResetActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action)) {
                CameraResetActivity.this.g = true;
                CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.in_recording));
                return;
            }
            if (com.xiaoyi.camera.a.a("video_record_complete").equals(action)) {
                CameraResetActivity.this.g = false;
                CameraResetActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("start_usb_storage").equals(action)) {
                CameraResetActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("start_fwupdate").equals(action)) {
                CameraResetActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("self_capture_stop").equals(action)) {
                CameraResetActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("setting_changed").equals(action)) {
                CameraResetActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.camera_album_operation_ing));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_album").equals(action)) {
                CameraResetActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_start").equals(action)) {
                CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.camera_sdcard_optimize_start));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_stop").equals(action)) {
                CameraResetActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_usb_storage").equals(action)) {
                CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.camera_start_usb_storage_mode));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_usb_storage").equals(action)) {
                CameraResetActivity.this.a(false, (String) null);
            } else if (com.xiaoyi.camera.a.a("voice_control_sample_start").equals(action)) {
                CameraResetActivity.this.a(true, CameraResetActivity.this.getString(R.string.camera_voice_control));
            } else if (com.xiaoyi.camera.a.a("voice_control_sample_stop").equals(action)) {
                CameraResetActivity.this.a(false, (String) null);
            }
        }
    };
    private Handler q = new Handler(Looper.getMainLooper());
    private Runnable r = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.8
        @Override // java.lang.Runnable
        public void run() {
            CameraResetActivity.this.d(CameraResetActivity.this.getString(R.string.setting_restore_camera_settings_success));
        }
    };
    private Runnable s = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.4
        @Override // java.lang.Runnable
        public void run() {
            CameraResetActivity.this.j();
        }
    };

    /* loaded from: classes3.dex */
    private class a extends BaseAdapter {

        /* renamed from: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class C0197a {

            /* renamed from: a, reason: collision with root package name */
            public TextView f4659a;
            public View b;

            public C0197a() {
            }
        }

        private a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (CameraResetActivity.this.k == null) {
                return 0;
            }
            return CameraResetActivity.this.k.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return CameraResetActivity.this.k.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            C0197a c0197a;
            if (view == null) {
                c0197a = new C0197a();
                view = View.inflate(CameraResetActivity.this, R.layout.setting_value_item, null);
                c0197a.f4659a = (TextView) view.findViewById(R.id.tvValue);
                c0197a.b = view.findViewById(R.id.leftBand);
                c0197a.b.setVisibility(8);
                view.setTag(c0197a);
            } else {
                c0197a = (C0197a) view.getTag();
            }
            c0197a.f4659a.setText(((CameraSettingItem) CameraResetActivity.this.k.get(i)).displayName);
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.9
            @Override // java.lang.Runnable
            public void run() {
                CameraResetActivity.this.f();
                if (z) {
                    CameraResetActivity.this.e.setVisibility(0);
                    CameraResetActivity.this.f.setText(str);
                } else {
                    CameraResetActivity.this.e.setVisibility(8);
                    CameraResetActivity.this.f.setText("");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, str);
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        this.o = (CustomBottomConfirmDialogFragment) CustomBottomConfirmDialogFragment.instantiate(this, CustomBottomConfirmDialogFragment.class.getName(), bundle);
        this.o.setCancelable(false);
        this.o.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.3
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                c.a().c(new com.xiaoyi.camera.a.a());
                com.xiaoyi.camera.g.a().c();
                CameraResetActivity.this.startActivity(new Intent(CameraResetActivity.this, (Class<?>) MainActivity.class));
                CameraResetActivity.this.finish();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        this.o.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.l != null && this.l.getDialog() != null && this.l.getDialog().isShowing()) {
            this.l.dismissAllowingStateLoss();
        }
        if (this.m != null && this.m.getDialog() != null && this.m.getDialog().isShowing()) {
            this.m.dismissAllowingStateLoss();
        }
        if (this.n == null || this.n.getDialog() == null || !this.n.getDialog().isShowing()) {
            return;
        }
        this.n.dismissAllowingStateLoss();
    }

    private void g() {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("title", getString(R.string.setting_restore_factory_settings));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.factory_reset_message));
        this.l = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        this.l.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.12
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                if ("J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    d.a().e();
                    d.a().d();
                }
                com.xiaoyi.camera.g.a().a("restore_factory_settings", "on", CameraResetActivity.this);
                CameraResetActivity.this.a(true, "");
                dialogFragment.dismiss();
                CameraResetActivity.this.q.postDelayed(CameraResetActivity.this.r, 3000L);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        this.l.a(this);
    }

    private void h() {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("title", getString(R.string.setting_restore_wifi_settings));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.setting_restore_wifi_settings_message));
        this.m = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        this.m.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.13
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                if ("J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    d.a().e();
                    d.a().d();
                }
                try {
                    CameraResetActivity.this.j = new f(CameraResetActivity.this);
                    if (CameraResetActivity.this.j != null) {
                        CameraResetActivity.this.h = CameraResetActivity.this.j.g().replace("\"", "");
                        CameraResetActivity.this.i = CameraResetActivity.this.j.h().replace("\"", "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CameraApplication.f1401a.c(false);
                com.xiaoyi.camera.g.a().a("restore_wifi", "on", CameraResetActivity.this);
                CameraResetActivity.this.a(true, "");
                dialogFragment.dismiss();
                CameraResetActivity.this.q.postDelayed(CameraResetActivity.this.s, 3000L);
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

    private void i() {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("title", getString(R.string.setting_restore_bluetooth_settings));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.setting_restore_bluetooth_settings_message));
        this.n = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        this.n.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.2
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                com.xiaoyi.camera.g.a().a("restore_bt", "on", CameraResetActivity.this);
                CameraResetActivity.this.a(true, "");
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
        this.n.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        CameraApplication.f1401a.c(false);
        com.xiaoyi.camera.d.c a2 = com.xiaoyi.camera.d.c.a(this);
        com.xiaoyi.camera.devicedao.a a3 = a2.a(this.i);
        if (a3 != null) {
            a3.b(this.h);
            a3.c("1234567890");
            a2.b(a3);
        }
        this.j.c(this.h);
        this.q.postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.5
            @Override // java.lang.Runnable
            public void run() {
                CameraResetActivity.this.a(false, (String) null);
                com.xiaoyi.camera.g.a().c();
                CameraResetActivity.this.d(CameraResetActivity.this.getString(R.string.setting_restore_wifi_settings_success));
            }
        }, 1000L);
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(final com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.10
            @Override // java.lang.Runnable
            public void run() {
                if (dVar == null) {
                    CameraResetActivity.this.finish();
                    return;
                }
                if ("restore_wifi".equals(dVar.a("type"))) {
                    CameraResetActivity.this.q.removeCallbacks(CameraResetActivity.this.s);
                    CameraResetActivity.this.j();
                } else if ("restore_factory_settings".equals(dVar.a("type"))) {
                    CameraResetActivity.this.q.removeCallbacks(CameraResetActivity.this.r);
                    com.xiaoyi.camera.g.a().c();
                    CameraResetActivity.this.d(CameraResetActivity.this.getString(R.string.setting_restore_camera_settings_success));
                } else if ("restore_bt".equals(dVar.a("type"))) {
                    CameraResetActivity.this.a(R.string.setting_restore_bluetooth_settings_success);
                    CameraResetActivity.this.a(false, (String) null);
                }
            }
        });
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.11
            @Override // java.lang.Runnable
            public void run() {
                CameraResetActivity.this.a(false, (String) null);
                CameraResetActivity.this.a(R.string.set_failed);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!b.b) {
            Intent intent = new Intent(this, (Class<?>) MainActivity.class);
            intent.setFlags(268468224);
            startActivity(intent);
            finish();
            return;
        }
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.camera_setting_device_activity);
        this.k = new ArrayList<>();
        this.k.add(new CameraSettingItem(getString(R.string.setting_restore_default_settings), "restore_factory_settings", 6));
        this.k.add(new CameraSettingItem(getString(R.string.setting_restore_wifi_settings), "restore_wifi", 6));
        if (!"J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            this.k.add(new CameraSettingItem(getString(R.string.setting_restore_bluetooth_settings), "restore_bt", 6));
        }
        this.b = (CustomTitleBar) findViewById(R.id.titleBar);
        this.b.setMiddleTitle(R.string.setting_restore_factory_settings);
        this.b.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.6
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                CameraResetActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.e = (RelativeLayout) findViewById(R.id.rlBlockingCover);
        this.f = (TextView) findViewById(R.id.tvInfo);
        this.e.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.CameraResetActivity.7
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.c = (ListView) findViewById(R.id.listView);
        this.d = new a();
        this.c.setAdapter((ListAdapter) this.d);
        this.c.setOnItemClickListener(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("photo_taken"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("burst_complete"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("precise_cont_complete"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("video_record_complete"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("self_capture_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("setting_changed"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_stop"));
        registerReceiver(this.p, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.q.removeCallbacks(this.r);
        this.q.removeCallbacks(this.s);
        unregisterReceiver(this.p);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = this.k.get(i).optionName;
        if ("restore_factory_settings".equals(str)) {
            g();
        } else if ("restore_wifi".equals(str)) {
            h();
        } else if ("restore_bt".equals(str)) {
            i();
        }
    }
}
