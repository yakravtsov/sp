package com.xiaomi.xy.sportscamera.camera.set;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
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
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.activity.BaseConfigActivity;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.c.v;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.util.ab;
import com.ants360.z13.widget.CustomTitleBar;
import com.ants360.z13.widget.SwitchButton;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.b;
import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.d;
import com.xiaoyi.camera.module.CameraSettingItem;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import de.greenrobot.event.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class VideoPhotoSettingActivity extends BaseConfigActivity implements View.OnClickListener, AdapterView.OnItemClickListener, com.xiaoyi.camera.c.a {
    private CustomTitleBar c;
    private ListView d;
    private a f;
    private RelativeLayout g;
    private RelativeLayout h;
    private TextView i;
    private TextView j;
    private boolean k;
    private String l;
    private String m;
    private String n;
    private final String b = VideoPhotoSettingActivity.class.getName();
    private List<CameraSettingItem> e = new ArrayList();
    private Handler o = new Handler(Looper.getMainLooper());
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            VideoPhotoSettingActivity.this.a(false, (String) null);
            String action = intent.getAction();
            g.a(BuildConfig.BUILD_TYPE, "------------------- BroadcastReceiver action = " + action, new Object[0]);
            int intExtra = intent.getIntExtra("current_operation_model", -1);
            if (com.xiaoyi.camera.a.a("start_photo_capture").equals(action)) {
                VideoPhotoSettingActivity.this.c(intExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("photo_taken").equals(action)) {
                if (VideoPhotoSettingActivity.this.k) {
                    return;
                }
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("burst_complete").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("precise_cont_complete").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action)) {
                VideoPhotoSettingActivity.this.k = true;
                VideoPhotoSettingActivity.this.c(intExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("video_record_complete").equals(action)) {
                VideoPhotoSettingActivity.this.k = false;
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("start_usb_storage").equals(action)) {
                VideoPhotoSettingActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("start_fwupdate").equals(action)) {
                VideoPhotoSettingActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("self_capture_stop").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("setting_changed").equals(action)) {
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                VideoPhotoSettingActivity.this.a(true, VideoPhotoSettingActivity.this.getString(R.string.camera_album_operation_ing));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_album").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_fwupdate").equals(action)) {
                VideoPhotoSettingActivity.this.a(true, VideoPhotoSettingActivity.this.getString(R.string.firmware_update_info));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_fwupdate").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_sdformat").equals(action)) {
                VideoPhotoSettingActivity.this.a(true, VideoPhotoSettingActivity.this.getString(R.string.sd_card_format));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_sdformat").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_mvrecover").equals(action)) {
                VideoPhotoSettingActivity.this.a(true, VideoPhotoSettingActivity.this.getString(R.string.video_file_recovery));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_mvrecover").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_start").equals(action)) {
                VideoPhotoSettingActivity.this.a(true, VideoPhotoSettingActivity.this.getString(R.string.camera_sdcard_optimize_start));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_stop").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_usb_storage").equals(action)) {
                VideoPhotoSettingActivity.this.a(true, VideoPhotoSettingActivity.this.getString(R.string.camera_start_usb_storage_mode));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_usb_storage").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_start").equals(action)) {
                VideoPhotoSettingActivity.this.a(true, VideoPhotoSettingActivity.this.getString(R.string.camera_voice_control));
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_stop").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_live").equals(action)) {
                c.a().c(new com.xiaoyi.camera.a.a(false));
                VideoPhotoSettingActivity.this.f();
            } else if (com.xiaoyi.camera.a.a("exit_live").equals(action)) {
                VideoPhotoSettingActivity.this.a(false, (String) null);
            }
        }
    };
    private Runnable q = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.7
        @Override // java.lang.Runnable
        public void run() {
            VideoPhotoSettingActivity.this.a(false, (String) null);
            VideoPhotoSettingActivity.this.a(R.string.setting_timeout);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class a extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
        private a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return VideoPhotoSettingActivity.this.e.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return VideoPhotoSettingActivity.this.e.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            CameraSettingItem cameraSettingItem = (CameraSettingItem) VideoPhotoSettingActivity.this.e.get(i);
            switch (cameraSettingItem.type) {
                case 1:
                case 2:
                    View inflate = View.inflate(VideoPhotoSettingActivity.this, R.layout.widget_setting_selector, null);
                    ((TextView) inflate.findViewById(R.id.tvName)).setText(cameraSettingItem.displayName);
                    if (cameraSettingItem.type == 5) {
                        return inflate;
                    }
                    TextView textView = (TextView) inflate.findViewById(R.id.tvValue);
                    String a2 = ab.a(VideoPhotoSettingActivity.this, cameraSettingItem.optionName, cameraSettingItem.optionValue);
                    String str = cameraSettingItem.optionName;
                    if (("video_resolution".equals(str) || "video_loop_resolution".equals(str) || "timelapse_video_resolution".equals(str) || "video_photo_resolution".equals(str)) && VideoPhotoSettingActivity.this.l != null) {
                        String replace = a2.contains(CameraMainController.b) ? a2.replace(CameraMainController.b, CameraMainController.c) : a2;
                        if (replace.contains(CameraMainController.f4814a)) {
                            replace = replace.replace(CameraMainController.f4814a, CameraMainController.d);
                        }
                        String[] split = replace.split(" ");
                        if (split == null || split.length <= 0) {
                            textView.setText(replace);
                        } else {
                            String str2 = "";
                            if (!split[0].contains(CameraMainController.e) && !split[0].contains(CameraMainController.g) && !split[0].contains(CameraMainController.i) && !split[0].contains(CameraMainController.j) && !split[0].contains(CameraMainController.n) && !split[0].contains(CameraMainController.p)) {
                                String[] split2 = split[0].split("x");
                                if (split2 != null && split2.length == 2) {
                                    str2 = split2[1] + CameraMainController.b;
                                }
                            } else if (split[0].contains(CameraMainController.e)) {
                                str2 = CameraMainController.f;
                            } else if (split[0].contains(CameraMainController.g)) {
                                str2 = CameraMainController.h;
                            } else if (split[0].contains(CameraMainController.i) || split[0].contains(CameraMainController.j)) {
                                str2 = CameraMainController.m;
                            } else if (split[0].contains(CameraMainController.n)) {
                                str2 = CameraMainController.o;
                            } else if (split[0].contains(CameraMainController.p)) {
                                str2 = CameraMainController.q;
                            }
                            if (split.length == 3) {
                                textView.setText(str2 + " " + split[1] + " " + split[2]);
                            } else if (split.length == 4) {
                                textView.setText(str2 + " " + split[3] + " " + split[1] + " " + split[2]);
                            } else {
                                textView.setText(replace);
                            }
                        }
                    } else if ("photo_size".equals(str)) {
                        if (cameraSettingItem.optionValue.contains("fov")) {
                            textView.setText(cameraSettingItem.optionValue.replace("fov:", ""));
                        } else {
                            textView.setText(cameraSettingItem.optionValue);
                        }
                    } else if ("precise_selftime".equals(str)) {
                        textView.setText(cameraSettingItem.optionValue.replace("s", VideoPhotoSettingActivity.this.getString(R.string.second)));
                    } else if ("precise_cont_time".equals(str)) {
                        if (cameraSettingItem.optionValue.contains("sec")) {
                            textView.setText(cameraSettingItem.optionValue.replace("sec", VideoPhotoSettingActivity.this.getString(R.string.second)).replace(" ", "").replace(".0", ""));
                        } else if (cameraSettingItem.optionValue.contains("min")) {
                            textView.setText(cameraSettingItem.optionValue.replace("min", VideoPhotoSettingActivity.this.getString(R.string.minute)).replace(" ", "").replace(".0", ""));
                        } else if (cameraSettingItem.optionValue.contains("continue")) {
                            textView.setText(cameraSettingItem.optionValue.replace("continue", VideoPhotoSettingActivity.this.getString(R.string.time_interval_continue)).replace(" ", "").replace(".0", ""));
                        } else {
                            textView.setText(cameraSettingItem.optionValue);
                        }
                    } else if ("burst_capture_number".equals(str)) {
                        textView.setText(cameraSettingItem.optionValue.replace(" ", "").replace("s", VideoPhotoSettingActivity.this.getString(R.string.second)).replace(TtmlNode.TAG_P, VideoPhotoSettingActivity.this.getString(R.string.picture_num)));
                    } else if ("timelapse_video".equals(str)) {
                        textView.setText(cameraSettingItem.optionValue + VideoPhotoSettingActivity.this.getString(R.string.second));
                    } else if ("timelapse_video_duration".equals(str)) {
                        if ("off".equals(cameraSettingItem.optionValue)) {
                            textView.setText(R.string.set_off);
                        } else {
                            textView.setText(cameraSettingItem.optionValue.replace("s", VideoPhotoSettingActivity.this.getString(R.string.second)));
                        }
                    } else if ("record_photo_time".equals(str)) {
                        textView.setText(cameraSettingItem.optionValue + VideoPhotoSettingActivity.this.getString(R.string.second));
                    } else if ("loop_rec_duration".equals(str)) {
                        if ("max".equalsIgnoreCase(cameraSettingItem.optionValue)) {
                            textView.setText(R.string.loop_video_duration_max);
                        } else {
                            textView.setText(cameraSettingItem.optionValue.replace(" ", "").replace("minutes", VideoPhotoSettingActivity.this.getString(R.string.minute)));
                        }
                    } else if ("slow_motion_res".equals(str)) {
                        try {
                            textView.setText(cameraSettingItem.optionValue.substring(0, cameraSettingItem.optionValue.indexOf("x", cameraSettingItem.optionValue.indexOf("x") + 1)) + " / " + cameraSettingItem.optionValue.substring(cameraSettingItem.optionValue.indexOf("x", cameraSettingItem.optionValue.indexOf("x") + 1) + 1, cameraSettingItem.optionValue.length()) + "x");
                        } catch (Exception e) {
                            textView.setText(a2);
                        }
                    } else if ("photo_file_type".equals(str)) {
                        textView.setText(a2.toUpperCase());
                    } else {
                        textView.setText(a2);
                    }
                    return inflate;
                case 3:
                    View inflate2 = View.inflate(VideoPhotoSettingActivity.this, R.layout.widget_setting_switcher, null);
                    TextView textView2 = (TextView) inflate2.findViewById(R.id.tvName);
                    textView2.setText(cameraSettingItem.displayName);
                    SwitchButton switchButton = (SwitchButton) inflate2.findViewById(R.id.switcher);
                    if ("on".equals(cameraSettingItem.optionValue)) {
                        switchButton.setChecked(true);
                    } else {
                        switchButton.setChecked(false);
                    }
                    if ("auto_low_light".equals(cameraSettingItem.optionName)) {
                        if ("off".equals(com.xiaoyi.camera.g.a().a("support_auto_low_light"))) {
                            switchButton.setChecked(false);
                            switchButton.setEnabled(false);
                            cameraSettingItem.enable = false;
                            textView2.setTextColor(VideoPhotoSettingActivity.this.getResources().getColor(R.color.setting_title_color));
                        } else {
                            cameraSettingItem.enable = true;
                            switchButton.setEnabled(true);
                            textView2.setTextColor(VideoPhotoSettingActivity.this.getResources().getColor(R.color.setting_header_title_color));
                            switchButton.setEnabled(true);
                        }
                    }
                    if ("iq_eis_enable".equals(cameraSettingItem.optionName)) {
                        if ("off".equals(com.xiaoyi.camera.g.a().a("eis_support_status"))) {
                            switchButton.setChecked(false);
                            switchButton.setEnabled(false);
                            cameraSettingItem.enable = false;
                            textView2.setTextColor(VideoPhotoSettingActivity.this.getResources().getColor(R.color.setting_title_color));
                        } else {
                            cameraSettingItem.enable = true;
                            switchButton.setEnabled(true);
                            textView2.setTextColor(VideoPhotoSettingActivity.this.getResources().getColor(R.color.setting_header_title_color));
                            switchButton.setEnabled(true);
                        }
                    }
                    if ("video_output_dev_type".equals(cameraSettingItem.optionName)) {
                        cameraSettingItem.enable = true;
                        if ("tv".equals(cameraSettingItem.optionValue)) {
                            switchButton.setChecked(true);
                        } else {
                            switchButton.setChecked(false);
                        }
                    }
                    switchButton.setTag(cameraSettingItem);
                    switchButton.setOnCheckedChangeListener(this);
                    return inflate2;
                default:
                    return null;
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            int i2 = ((CameraSettingItem) VideoPhotoSettingActivity.this.e.get(i)).type;
            return (i2 == 0 || i2 == 7 || i2 == 3) ? false : true;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CameraSettingItem cameraSettingItem = (CameraSettingItem) compoundButton.getTag();
            if (cameraSettingItem.enable) {
                if (z) {
                    cameraSettingItem.optionValue = "on";
                } else {
                    cameraSettingItem.optionValue = "off";
                }
                c.a().c(new v(cameraSettingItem, VideoPhotoSettingActivity.this.b));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    VideoPhotoSettingActivity.this.g.setVisibility(0);
                    VideoPhotoSettingActivity.this.i.setText(str);
                } else {
                    VideoPhotoSettingActivity.this.g.setVisibility(8);
                    VideoPhotoSettingActivity.this.i.setText("");
                }
            }
        });
    }

    private void b(boolean z) {
        try {
            if (z) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("video_record_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("photo_taken"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("burst_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("precise_cont_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("self_capture_stop"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_usb_storage"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
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
                intentFilter.addAction(com.xiaoyi.camera.a.a("enter_live"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("exit_live"));
                registerReceiver(this.p, intentFilter);
            } else {
                unregisterReceiver(this.p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        switch (i) {
            case 0:
                a(true, getString(R.string.in_recording));
                return;
            case 1:
                a(true, getString(R.string.in_normal_capturing));
                return;
            case 2:
                a(true, getString(R.string.in_burst));
                return;
            case 3:
                a(true, getString(R.string.in_timeslape));
                return;
            case 4:
                a(true, getString(R.string.in_timer));
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.n = com.xiaoyi.camera.g.a().a("system_mode");
        this.l = com.xiaoyi.camera.g.a().a("rec_mode");
        this.m = com.xiaoyi.camera.g.a().a("capture_mode");
        if (CameraMainController.CameraMode.RecordMode.toString().equals(this.n)) {
            if (Constant.RecordMode.TIMELAPES.toString().equals(this.l)) {
                this.c.setMiddleTitle(R.string.tv_timlapse_record);
            } else if (Constant.RecordMode.SLOW.toString().equals(this.l)) {
                this.c.setMiddleTitle(R.string.slow_motion);
            } else if (Constant.RecordMode.LOOP.toString().equals(this.l)) {
                this.c.setMiddleTitle(R.string.loop_record);
            } else if (Constant.RecordMode.PHOTO.toString().equals(this.l)) {
                this.c.setMiddleTitle(R.string.tv_record_photo);
            } else if (Constant.RecordMode.NORMAL.toString().equals(this.l)) {
                this.c.setMiddleTitle(R.string.tv_normal_record);
            }
        } else if (Constant.CaptureMode.TIMELAPES.toString().equals(this.m)) {
            this.c.setMiddleTitle(R.string.tv_timelapes);
        } else if (Constant.CaptureMode.BURST.toString().equals(this.m)) {
            this.c.setMiddleTitle(R.string.tv_burst);
        } else if (Constant.CaptureMode.TIMER.toString().equals(this.m)) {
            this.c.setMiddleTitle(R.string.tv_timer);
        } else if (Constant.CaptureMode.NORMAL.toString().equals(this.m)) {
            this.c.setMiddleTitle(R.string.tv_normal);
        }
        this.e.clear();
        this.e = com.xiaomi.xy.sportscamera.camera.a.a.a(this);
        if (this.f != null) {
            this.f.notifyDataSetChanged();
        }
    }

    private void h() {
        com.xiaoyi.camera.g.a().k(new com.xiaoyi.camera.c.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.11
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(d dVar, final JSONObject jSONObject) {
        g.a(BuildConfig.BUILD_TYPE, "------------------- onReceiveMessage = " + jSONObject.toString(), new Object[0]);
        if (this.o == null) {
            a(false, (String) null);
            return;
        }
        this.o.removeCallbacks(this.q);
        int optInt = jSONObject.optInt("related");
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.8
            @Override // java.lang.Runnable
            public void run() {
                String optString = jSONObject.optString("type");
                String optString2 = jSONObject.optString("param");
                if ("video_resolution".equals(optString) && "Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    if (optString2.contains("120P") || optString2.contains("240P") || optString2.contains("100P") || optString2.contains("200P")) {
                        VideoPhotoSettingActivity.this.a(R.string.recording_sound_not_allowed);
                    }
                }
            }
        });
        if (dVar == null || dVar.a() != 2 || optInt > 0) {
            runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.9
                @Override // java.lang.Runnable
                public void run() {
                    VideoPhotoSettingActivity.this.a(false, (String) null);
                }
            });
        } else {
            com.xiaoyi.camera.g.a().b();
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(d dVar, JSONObject jSONObject) {
        if (this.o == null) {
            return;
        }
        this.o.removeCallbacks(this.q);
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.10
            @Override // java.lang.Runnable
            public void run() {
                VideoPhotoSettingActivity.this.a(false, (String) null);
                VideoPhotoSettingActivity.this.a(R.string.set_failed);
                com.xiaoyi.camera.g.a().b();
            }
        });
    }

    public void f() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.enter_live_and_disconnect));
        bundle.putString("left_button", getString(R.string.button_back_to_home));
        bundle.putString("right_button", getString(R.string.button_reconnect));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.3
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                VideoPhotoSettingActivity.this.finish();
                VideoPhotoSettingActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
                Intent intent = new Intent(VideoPhotoSettingActivity.this, (Class<?>) MainActivity.class);
                intent.setFlags(268435456);
                VideoPhotoSettingActivity.this.startActivity(intent);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                VideoPhotoSettingActivity.this.finish();
                VideoPhotoSettingActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
                Intent intent = new Intent(VideoPhotoSettingActivity.this, (Class<?>) MainActivity.class);
                intent.setFlags(268435456);
                VideoPhotoSettingActivity.this.startActivity(intent);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                VideoPhotoSettingActivity.this.finish();
                VideoPhotoSettingActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
                Intent intent = new Intent(VideoPhotoSettingActivity.this, (Class<?>) MainActivity.class);
                intent.setFlags(268435456);
                VideoPhotoSettingActivity.this.startActivity(intent);
            }
        });
        customBottomDialogFragment.a(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDeviceSet /* 2131755731 */:
                startActivity(new Intent(this, (Class<?>) DeviceSettingActivity.class));
                return;
            default:
                return;
        }
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
        setContentView(R.layout.camera_setting_video_photo_activity);
        this.l = com.xiaoyi.camera.g.a().a("rec_mode");
        this.n = com.xiaoyi.camera.g.a().a("system_mode");
        this.c = (CustomTitleBar) findViewById(R.id.titleBar);
        this.c.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.4
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                VideoPhotoSettingActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.d = (ListView) findViewById(R.id.listView);
        this.f = new a();
        this.d.setAdapter((ListAdapter) this.f);
        this.d.setOnItemClickListener(this);
        this.g = (RelativeLayout) findViewById(R.id.rlBlockingCover);
        this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.5
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.h = (RelativeLayout) findViewById(R.id.rlYdxj_Pic);
        this.i = (TextView) findViewById(R.id.tvInfo);
        com.yiaction.common.util.b.b(this);
        com.yiaction.common.util.b.c(this);
        this.j = (TextView) findViewById(R.id.tvDeviceSet);
        this.j.setOnClickListener(this);
        c.a().a(this);
        b(true);
        com.xiaoyi.camera.g.a().b();
        h();
        c(i.a().b("current_operation_model", -1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f = null;
        this.o.removeCallbacks(this.q);
        this.o = null;
        c.a().b(this);
        try {
            b(false);
        } catch (IllegalArgumentException e) {
            g.a(e.toString(), new Object[0]);
        }
    }

    public void onEvent(v vVar) {
        g.a("debug_event", getClass() + " recevied SettingItemChangedEvent", new Object[0]);
        CameraSettingItem a2 = vVar.a();
        String str = a2.optionName;
        String str2 = a2.optionValue;
        if (this.b.equals(vVar.b())) {
            if (("video_resolution".equals(str) || "video_loop_resolution".equals(str) || "timelapse_video_resolution".equals(str) || "video_photo_resolution".equals(str)) && this.l != null) {
                if (str2.contains(CameraMainController.d)) {
                    str2 = str2.replace(CameraMainController.d, CameraMainController.f4814a);
                }
            } else if (str.equals("photo_size")) {
                String[] split = str2.split(" ");
                if (split.length >= 4) {
                    str2 = split[0] + " " + split[1] + " " + split[2] + " fov:" + split[3];
                }
            }
            int indexOf = this.e.indexOf(a2);
            String a3 = com.xiaoyi.camera.g.a().a(str);
            if (a3 == null || !a3.equals(str2)) {
                com.xiaoyi.camera.g.a().a(str, str2);
                com.xiaoyi.camera.g.a().a(str, str2, this);
                this.e.remove(indexOf);
                this.e.add(indexOf, a2);
                this.f.notifyDataSetChanged();
                if (a2.stopVf) {
                    a(true, "");
                    this.o.postDelayed(this.q, 15000L);
                }
                UploadStatsManager.a("camera_settings", a2.optionName, a2.optionValue);
            }
        }
    }

    public void onEvent(final com.xiaoyi.camera.a.d dVar) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (VideoPhotoSettingActivity.this.g == null) {
                    return;
                }
                VideoPhotoSettingActivity.this.a(false, (String) null);
                if (dVar.a()) {
                    VideoPhotoSettingActivity.this.g();
                } else {
                    VideoPhotoSettingActivity.this.a(R.string.set_failed);
                }
            }
        });
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CameraSettingItem cameraSettingItem = this.e.get(i);
        if (cameraSettingItem.enable) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CameraSettingItem.SETTING_ITEM, cameraSettingItem);
            bundle.putSerializable(CameraSettingItem.SETTING_OPTIONS, com.xiaomi.xy.sportscamera.camera.a.a.a(cameraSettingItem.optionName));
            Intent intent = new Intent(this, (Class<?>) SettingValueActivity.class);
            intent.putExtra(SettingValueActivity.b, bundle);
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        g();
    }
}
