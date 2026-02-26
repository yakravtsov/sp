package com.xiaomi.xy.sportscamera.camera.set;

import android.annotation.SuppressLint;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.activity.BaseConfigActivity;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.util.ab;
import com.ants360.z13.widget.CustomTitleBar;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.d;
import com.xiaoyi.camera.module.CameraSettingItem;
import com.xiaoyi.camera.module.b;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.g;
import de.greenrobot.event.c;
import java.util.ArrayList;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

@SuppressLint({"ViewHolder"})
/* loaded from: classes3.dex */
public class SettingValueActivity extends BaseConfigActivity implements AdapterView.OnItemClickListener, com.xiaoyi.camera.c.a {
    public static String b = "SETTING_VALUE";
    private static int h = -1;
    private CustomTitleBar c;
    private ListView d;
    private a e;
    private RelativeLayout f;
    private TextView g;
    private boolean i;
    private int j;
    private CameraSettingItem k;
    private ArrayList<String> l;
    private Handler m = new Handler(Looper.getMainLooper());
    private BroadcastReceiver n = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            String action = intent.getAction();
            g.a(BuildConfig.BUILD_TYPE, "------------------- BroadcastReceiver action = " + action, new Object[0]);
            if (com.xiaoyi.camera.a.a("start_photo_capture").equals(action)) {
                int unused = SettingValueActivity.h = intent.getIntExtra("current_operation_model", -1);
                switch (SettingValueActivity.h) {
                    case 1:
                        SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.in_normal_capturing));
                        return;
                    case 2:
                        SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.in_burst));
                        return;
                    case 3:
                        SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.in_timeslape));
                        return;
                    case 4:
                        SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.in_timer));
                        return;
                    default:
                        return;
                }
            }
            if (com.xiaoyi.camera.a.a("photo_taken").equals(action)) {
                if (SettingValueActivity.this.i) {
                    return;
                }
                SettingValueActivity.this.a(false, (String) null);
                int unused2 = SettingValueActivity.h = -1;
                return;
            }
            if (com.xiaoyi.camera.a.a("burst_complete").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                int unused3 = SettingValueActivity.h = -1;
                return;
            }
            if (com.xiaoyi.camera.a.a("precise_cont_complete").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                int unused4 = SettingValueActivity.h = -1;
                return;
            }
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action)) {
                int unused5 = SettingValueActivity.h = intent.getIntExtra("current_operation_model", -1);
                SettingValueActivity.this.i = true;
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.in_recording));
                return;
            }
            if (com.xiaoyi.camera.a.a("video_record_complete").equals(action)) {
                SettingValueActivity.this.i = false;
                SettingValueActivity.this.a(false, (String) null);
                int unused6 = SettingValueActivity.h = -1;
                return;
            }
            if (com.xiaoyi.camera.a.a("start_usb_storage").equals(action)) {
                SettingValueActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("start_fwupdate").equals(action)) {
                SettingValueActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("self_capture_stop").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                int unused7 = SettingValueActivity.h = -1;
                return;
            }
            if (com.xiaoyi.camera.a.a("setting_changed").equals(action)) {
                SettingValueActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String stringExtra = intent.getStringExtra("param");
                        if ("rec_mode".equals(stringExtra) || "capture_mode".equals(stringExtra)) {
                            SettingValueActivity.this.finish();
                            return;
                        }
                        if ("video_shutter".equals(SettingValueActivity.this.k.optionName) && ("video_standard".equals(stringExtra) || "video_resolution".equals(stringExtra) || "video_photo_resolution".equals(stringExtra))) {
                            SettingValueActivity.this.finish();
                            return;
                        }
                        if ("video_stamp".equals(SettingValueActivity.this.k.optionName) && "slow_motion_rate".equals(stringExtra)) {
                            SettingValueActivity.this.finish();
                        } else {
                            if ("video_standard".equals(stringExtra)) {
                                SettingValueActivity.this.f();
                                return;
                            }
                            SettingValueActivity.this.h();
                            SettingValueActivity.this.e.notifyDataSetChanged();
                            SettingValueActivity.this.d.setSelection(SettingValueActivity.this.j);
                        }
                    }
                });
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.camera_album_operation_ing));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_album").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_fwupdate").equals(action)) {
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.firmware_update_info));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_fwupdate").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_sdformat").equals(action)) {
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.sd_card_format));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_sdformat").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_mvrecover").equals(action)) {
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.video_file_recovery));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_mvrecover").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_start").equals(action)) {
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.camera_sdcard_optimize_start));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_stop").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_usb_storage").equals(action)) {
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.camera_start_usb_storage_mode));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_usb_storage").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
            } else if (com.xiaoyi.camera.a.a("voice_control_sample_start").equals(action)) {
                SettingValueActivity.this.a(true, SettingValueActivity.this.getString(R.string.camera_voice_control));
            } else if (com.xiaoyi.camera.a.a("voice_control_sample_stop").equals(action)) {
                SettingValueActivity.this.a(false, (String) null);
            }
        }
    };
    private Runnable o = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.7
        @Override // java.lang.Runnable
        public void run() {
            SettingValueActivity.this.a(false, (String) null);
            SettingValueActivity.this.a(R.string.setting_timeout);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class a extends BaseAdapter {

        /* renamed from: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class C0200a {

            /* renamed from: a, reason: collision with root package name */
            public TextView f4720a;
            public View b;

            public C0200a() {
            }
        }

        private a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (SettingValueActivity.this.l == null) {
                return 0;
            }
            return SettingValueActivity.this.l.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return SettingValueActivity.this.l.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            C0200a c0200a;
            if (view == null) {
                c0200a = new C0200a();
                view = View.inflate(SettingValueActivity.this, R.layout.setting_value_item, null);
                c0200a.f4720a = (TextView) view.findViewById(R.id.tvValue);
                c0200a.b = view.findViewById(R.id.leftBand);
                view.setTag(c0200a);
            } else {
                c0200a = (C0200a) view.getTag();
            }
            String str = (String) SettingValueActivity.this.l.get(i);
            if ("auto_power_off".equals(SettingValueActivity.this.k.optionName) && "off".equals(str)) {
                c0200a.f4720a.setText(R.string.auto_power_off_never);
            } else if ("precise_selftime".equals(SettingValueActivity.this.k.optionName)) {
                c0200a.f4720a.setText(str.replace("s", SettingValueActivity.this.getString(R.string.second)));
            } else if ("precise_cont_time".equals(SettingValueActivity.this.k.optionName)) {
                if (str.contains("sec")) {
                    c0200a.f4720a.setText(str.replace("sec", SettingValueActivity.this.getString(R.string.second)).replace(" ", "").replace(".0", ""));
                } else if (str.contains("min")) {
                    c0200a.f4720a.setText(str.replace("min", SettingValueActivity.this.getString(R.string.minute)).replace(" ", "").replace(".0", ""));
                } else if (str.contains("continue")) {
                    c0200a.f4720a.setText(str.replace("continue", SettingValueActivity.this.getString(R.string.time_interval_continue)).replace(" ", "").replace(".0", ""));
                } else {
                    c0200a.f4720a.setText(str);
                }
            } else if ("burst_capture_number".equals(SettingValueActivity.this.k.optionName)) {
                c0200a.f4720a.setText(str.replace(" ", "").replace("s", SettingValueActivity.this.getString(R.string.second)).replace(TtmlNode.TAG_P, SettingValueActivity.this.getString(R.string.picture_num)));
            } else if ("timelapse_video".equals(SettingValueActivity.this.k.optionName)) {
                c0200a.f4720a.setText(str + SettingValueActivity.this.getString(R.string.second));
            } else if ("timelapse_video_duration".equals(SettingValueActivity.this.k.optionName)) {
                if ("off".equals(str)) {
                    c0200a.f4720a.setText(R.string.set_off);
                } else {
                    c0200a.f4720a.setText(str.replace("s", SettingValueActivity.this.getString(R.string.second)));
                }
            } else if ("record_photo_time".equals(SettingValueActivity.this.k.optionName)) {
                c0200a.f4720a.setText(str + SettingValueActivity.this.getString(R.string.second));
            } else if ("loop_rec_duration".equals(SettingValueActivity.this.k.optionName)) {
                if ("max".equalsIgnoreCase(str)) {
                    c0200a.f4720a.setText(R.string.loop_video_duration_max);
                } else {
                    c0200a.f4720a.setText(str.replace(" ", "").replace("minutes", SettingValueActivity.this.getString(R.string.minute)));
                }
            } else if ("slow_motion_res".equals(SettingValueActivity.this.k.optionName)) {
                try {
                    c0200a.f4720a.setText(str.substring(0, str.indexOf("x", str.indexOf("x") + 1)) + " / " + str.substring(str.indexOf("x", str.indexOf("x") + 1) + 1, str.length()) + "x");
                } catch (Exception e) {
                    c0200a.f4720a.setText(str);
                }
            } else if ("photo_file_type".equals(SettingValueActivity.this.k.optionName)) {
                c0200a.f4720a.setText(str.toUpperCase());
            } else {
                c0200a.f4720a.setText(ab.a(SettingValueActivity.this, SettingValueActivity.this.k.optionName, str));
            }
            if (i == SettingValueActivity.this.j) {
                c0200a.b.setVisibility(0);
            } else {
                c0200a.b.setVisibility(8);
            }
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    SettingValueActivity.this.f.setVisibility(0);
                    SettingValueActivity.this.g.setText(str);
                } else {
                    SettingValueActivity.this.f.setVisibility(8);
                    SettingValueActivity.this.g.setText("");
                }
            }
        });
    }

    private void b(boolean z) {
        if (!z) {
            try {
                unregisterReceiver(this.n);
                return;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return;
            }
        }
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
        registerReceiver(this.n, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.k.setOptionValue(com.xiaoyi.camera.g.a().a(this.k.optionName));
        int size = this.l == null ? 0 : this.l.size();
        for (int i = 0; i < size; i++) {
            if (this.l.get(i).equals(this.k.optionValue)) {
                this.j = i;
                return;
            }
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(d dVar, JSONObject jSONObject) {
        g.a(BuildConfig.BUILD_TYPE, "------------------- onReceiveMessage = " + jSONObject.toString(), new Object[0]);
        if (this.m == null) {
            a(false, (String) null);
            return;
        }
        this.m.removeCallbacks(this.o);
        int optInt = jSONObject.optInt("related");
        String optString = jSONObject.optString("type");
        String optString2 = jSONObject.optString("param");
        if ("video_resolution".equals(optString) && "Z13".equals(com.xiaoyi.camera.g.a().a("model")) && (optString2.contains("120P") || optString2.contains("240P") || optString2.contains("100P") || optString2.contains("200P"))) {
            runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    SettingValueActivity.this.a(R.string.recording_sound_not_allowed);
                }
            });
        }
        if ("video_standard".equals(optString)) {
            b.b("video_resolution");
            b.b("video_loop_resolution");
            b.b("timelapse_video_resolution");
            b.b("video_photo_resolution");
            b.b("video_shutter");
        } else if ("iq_photo_iso".equals(optString)) {
            b.b("iq_photo_iso_min");
        } else if ("iq_video_iso".equals(optString)) {
            b.b("iq_video_iso_min");
        } else if ("video_resolution".equals(optString) || "video_photo_resolution".equals(optString)) {
            b.b("video_shutter");
        } else if ("slow_motion_rate".equals(optString)) {
            b.b("video_stamp");
        }
        if (dVar == null || dVar.a() != 2 || optInt > 0) {
            runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.9
                @Override // java.lang.Runnable
                public void run() {
                    SettingValueActivity.this.a(false, (String) null);
                }
            });
        } else {
            com.xiaoyi.camera.g.a().b();
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(d dVar, JSONObject jSONObject) {
        if (this.m == null) {
            return;
        }
        this.m.removeCallbacks(this.o);
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.10
            @Override // java.lang.Runnable
            public void run() {
                SettingValueActivity.this.a(false, (String) null);
                SettingValueActivity.this.a(R.string.set_failed);
                com.xiaoyi.camera.g.a().b();
            }
        });
    }

    public void d(String str) {
        g.a("debug_event", getClass() + " recevied SettingItemChangedEvent", new Object[0]);
        String str2 = this.k.optionName;
        if ("video_resolution".equals(str2) || "video_loop_resolution".equals(str2) || "timelapse_video_resolution".equals(str2) || "video_photo_resolution".equals(str2)) {
            if (str.contains(CameraMainController.d)) {
                str = str.replace(CameraMainController.d, CameraMainController.f4814a);
            }
        } else if (str2.equals("photo_size")) {
            String[] split = str.split(" ");
            if (split.length >= 4) {
                str = split[0] + " " + split[1] + " " + split[2] + " fov:" + split[3];
            }
        }
        String a2 = com.xiaoyi.camera.g.a().a(str2);
        if (TextUtils.isEmpty(str) || a2.equals(str)) {
            return;
        }
        com.xiaoyi.camera.g.a().a(str2, str);
        h();
        this.e.notifyDataSetChanged();
        com.xiaoyi.camera.g.a().a(str2, str, this);
        if (this.k.stopVf) {
            a(true, "");
            this.m.postDelayed(this.o, 15000L);
        }
        UploadStatsManager.a("camera_settings", this.k.optionName, this.k.optionValue);
    }

    public void f() {
        a(true, "");
        com.xiaoyi.camera.g.a().b(this.k.optionName, new com.xiaoyi.camera.c.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.5
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                b.a(SettingValueActivity.this.k.optionName, jSONObject.optJSONArray("options"));
                SettingValueActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SettingValueActivity.this.a(false, "");
                        SettingValueActivity.this.l = b.a(SettingValueActivity.this.k.optionName);
                        SettingValueActivity.this.h();
                        SettingValueActivity.this.e.notifyDataSetChanged();
                        SettingValueActivity.this.d.setSelection(SettingValueActivity.this.j);
                    }
                });
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
                SettingValueActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!com.xiaoyi.camera.b.b) {
            Intent intent = new Intent(this, (Class<?>) MainActivity.class);
            intent.setFlags(268468224);
            startActivity(intent);
            finish();
            return;
        }
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.camera_setting_device_activity);
        Bundle bundleExtra = getIntent().getBundleExtra(b);
        this.k = (CameraSettingItem) bundleExtra.getSerializable(CameraSettingItem.SETTING_ITEM);
        this.l = (ArrayList) bundleExtra.getSerializable(CameraSettingItem.SETTING_OPTIONS);
        if (this.k != null && ("fov".equals(this.k.optionName) || "timelapse_photo_shutter".equals(this.k.optionName))) {
            this.l = null;
        }
        h();
        this.c = (CustomTitleBar) findViewById(R.id.titleBar);
        this.c.setMiddleTitle(this.k.displayName);
        this.c.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.3
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                SettingValueActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.f = (RelativeLayout) findViewById(R.id.rlBlockingCover);
        this.g = (TextView) findViewById(R.id.tvInfo);
        this.f.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.4
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.d = (ListView) findViewById(R.id.listView);
        this.e = new a();
        this.d.setAdapter((ListAdapter) this.e);
        this.d.setSelection(this.j);
        this.d.setOnItemClickListener(this);
        b(true);
        if (this.l == null || this.l.size() <= 0) {
            f();
        }
        c.a().a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b(false);
        c.a().b(this);
    }

    public void onEvent(final com.xiaoyi.camera.a.d dVar) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.SettingValueActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (SettingValueActivity.this.f == null) {
                    return;
                }
                SettingValueActivity.this.a(false, (String) null);
                if (!dVar.a()) {
                    SettingValueActivity.this.a(R.string.set_failed);
                } else {
                    SettingValueActivity.this.h();
                    SettingValueActivity.this.e.notifyDataSetChanged();
                }
            }
        });
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!"insert".equals(com.xiaoyi.camera.g.a().a("sd_card_status")) && "video_file_max_size".equals(this.k.optionName)) {
            a(R.string.please_insert_sdcard);
        } else {
            this.k.optionValue = this.l.get(i);
            d(this.k.optionValue);
        }
    }
}
