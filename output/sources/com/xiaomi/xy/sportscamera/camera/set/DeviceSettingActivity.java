package com.xiaomi.xy.sportscamera.camera.set;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ants360.z13.activity.BaseConfigActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.c.v;
import com.ants360.z13.fragment.CustomBottomConfirmDialogFragment;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.fragment.LocateCameraFragment;
import com.ants360.z13.util.ab;
import com.ants360.z13.util.e;
import com.ants360.z13.widget.CustomTitleBar;
import com.ants360.z13.widget.SwitchButton;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity;
import com.xiaoyi.camera.d;
import com.xiaoyi.camera.d.f;
import com.xiaoyi.camera.module.CameraSettingItem;
import com.xiaoyi.camera.module.b;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import de.greenrobot.event.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class DeviceSettingActivity extends BaseConfigActivity implements AdapterView.OnItemClickListener, com.xiaoyi.camera.c.a {
    private CustomTitleBar c;
    private ListView d;
    private a f;
    private RelativeLayout g;
    private TextView h;
    private boolean i;
    private long j;
    private int k;
    private CustomBottomDialogFragment m;
    private LocateCameraFragment n;
    private CustomBottomDialogFragment o;
    private String p;
    private String q;
    private f r;
    private final String b = DeviceSettingActivity.class.getName();
    private List<CameraSettingItem> e = new ArrayList();
    private boolean l = false;
    private BroadcastReceiver s = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceSettingActivity.this.a(false, (String) null);
            String action = intent.getAction();
            g.a(BuildConfig.BUILD_TYPE, "------------------- BroadcastReceiver action = " + action, new Object[0]);
            int intExtra = intent.getIntExtra("current_operation_model", -1);
            if (com.xiaoyi.camera.a.a("start_photo_capture").equals(action)) {
                DeviceSettingActivity.this.c(intExtra);
                return;
            }
            if (com.xiaoyi.camera.a.a("photo_taken").equals(action)) {
                if (DeviceSettingActivity.this.i) {
                    return;
                }
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("burst_complete").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("precise_cont_complete").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("start_video_record").equals(action)) {
                DeviceSettingActivity.this.i = true;
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.in_recording));
                return;
            }
            if (com.xiaoyi.camera.a.a("video_record_complete").equals(action)) {
                DeviceSettingActivity.this.i = false;
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("start_usb_storage").equals(action)) {
                Toast.makeText(DeviceSettingActivity.this.getApplicationContext(), DeviceSettingActivity.this.getResources().getString(R.string.camera_start_usb_storage_mode), 0).show();
                DeviceSettingActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("start_fwupdate").equals(action)) {
                Toast.makeText(DeviceSettingActivity.this.getApplicationContext(), DeviceSettingActivity.this.getResources().getString(R.string.camera_start_fw_update), 0).show();
                DeviceSettingActivity.this.finish();
                return;
            }
            if (com.xiaoyi.camera.a.a("btc_delete_all_binded_dev").equals(action)) {
                switch (Integer.parseInt(intent.getStringExtra("param"))) {
                    case 0:
                        DeviceSettingActivity.this.u.sendEmptyMessage(6);
                        return;
                    case 1:
                        DeviceSettingActivity.this.u.sendEmptyMessage(4);
                        return;
                    case 2:
                        DeviceSettingActivity.this.u.sendEmptyMessage(3);
                        return;
                    case 3:
                        DeviceSettingActivity.this.u.sendEmptyMessage(5);
                        return;
                    case 4:
                        DeviceSettingActivity.this.u.sendEmptyMessage(7);
                        return;
                    default:
                        return;
                }
            }
            if (com.xiaoyi.camera.a.a("self_capture_stop").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("setting_changed").equals(action)) {
                DeviceSettingActivity.this.k();
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_album").equals(action)) {
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.camera_album_operation_ing));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_album").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_fwupdate").equals(action)) {
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.firmware_update_info));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_fwupdate").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_sdformat").equals(action)) {
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.sd_card_format));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_sdformat").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_mvrecover").equals(action)) {
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.video_file_recovery));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_mvrecover").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_start").equals(action)) {
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.camera_sdcard_optimize_start));
                return;
            }
            if (com.xiaoyi.camera.a.a("sdcard_optimize_stop").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_usb_storage").equals(action)) {
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.camera_start_usb_storage_mode));
                return;
            }
            if (com.xiaoyi.camera.a.a("exit_usb_storage").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_start").equals(action)) {
                DeviceSettingActivity.this.a(true, DeviceSettingActivity.this.getString(R.string.camera_voice_control));
                return;
            }
            if (com.xiaoyi.camera.a.a("voice_control_sample_stop").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
                return;
            }
            if (com.xiaoyi.camera.a.a("enter_live").equals(action)) {
                c.a().c(new com.xiaoyi.camera.a.a(false));
                DeviceSettingActivity.this.f();
            } else if (com.xiaoyi.camera.a.a("exit_live").equals(action)) {
                DeviceSettingActivity.this.a(false, (String) null);
            }
        }
    };
    private Runnable t = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.4
        @Override // java.lang.Runnable
        public void run() {
            DeviceSettingActivity.this.a(false, (String) null);
            DeviceSettingActivity.this.a(R.string.setting_timeout);
        }
    };

    @SuppressLint({"HandlerLeak"})
    private Handler u = new Handler() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 3:
                    Toast.makeText(DeviceSettingActivity.this, R.string.unbind_bluetooth_failed, 0).show();
                    return;
                case 4:
                    Toast.makeText(DeviceSettingActivity.this, R.string.unbind_bluetooth_succeeded, 0).show();
                    return;
                case 5:
                    Toast.makeText(DeviceSettingActivity.this, R.string.unbind_bluetooth_unbinded, 0).show();
                    return;
                case 6:
                    Toast.makeText(DeviceSettingActivity.this, R.string.setting_timeout, 0).show();
                    return;
                case 7:
                    Toast.makeText(DeviceSettingActivity.this, R.string.bluetooth_unbinding_timeout, 0).show();
                    return;
                default:
                    return;
            }
        }
    };
    private Runnable v = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.7
        @Override // java.lang.Runnable
        public void run() {
            DeviceSettingActivity.this.i();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity$5, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass5 implements com.xiaoyi.camera.c.a {
        AnonymousClass5() {
        }

        @Override // com.xiaoyi.camera.c.a
        public void a(d dVar, JSONObject jSONObject) {
            if (dVar.a() == 16777242) {
                String optString = jSONObject.optString("param");
                if (optString.equals("")) {
                    DeviceSettingActivity.this.a(false, (String) null);
                    DeviceSettingActivity.this.a(R.string.dump_firmware_log_failed);
                    return;
                }
                String str = "http://192.168.42.1/" + optString;
                try {
                    File file = new File(Constant.p);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else if (file.isFile()) {
                        file.delete();
                        file.mkdirs();
                    }
                    File file2 = new File(Constant.q);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    com.yiaction.common.http.a.a().a_(str, file2.getAbsolutePath(), new com.yiaction.common.http.g<String>() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.5.1
                        @Override // com.yiaction.common.http.g
                        /* renamed from: a, reason: avoid collision after fix types in other method */
                        public void a2(String str2) {
                            DeviceSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.5.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    DeviceSettingActivity.this.a(false, (String) null);
                                    DeviceSettingActivity.this.a(R.string.download_firmware_log_failed);
                                }
                            });
                        }

                        @Override // com.yiaction.common.http.g
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public void a(String str2) {
                            DeviceSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.5.1.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    DeviceSettingActivity.this.a(false, (String) null);
                                    DeviceSettingActivity.this.a(R.string.download_firmware_log_successed);
                                }
                            });
                        }
                    });
                } catch (SecurityException e) {
                    DeviceSettingActivity.this.a(false, (String) null);
                    DeviceSettingActivity.this.a(R.string.download_firmware_log_failed);
                    e.printStackTrace();
                } catch (Exception e2) {
                    DeviceSettingActivity.this.a(false, (String) null);
                    DeviceSettingActivity.this.a(R.string.download_firmware_log_failed);
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.xiaoyi.camera.c.a
        public void b(d dVar, JSONObject jSONObject) {
            DeviceSettingActivity.this.a(false, (String) null);
            DeviceSettingActivity.this.a(R.string.dump_firmware_log_failed);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class a extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
        private a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return DeviceSettingActivity.this.e.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return DeviceSettingActivity.this.e.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            CameraSettingItem cameraSettingItem = (CameraSettingItem) DeviceSettingActivity.this.e.get(i);
            switch (cameraSettingItem.type) {
                case 0:
                    View inflate = View.inflate(DeviceSettingActivity.this, R.layout.widget_setting_header, null);
                    ((TextView) inflate.findViewById(R.id.tvName)).setText(cameraSettingItem.displayName);
                    return inflate;
                case 1:
                default:
                    return null;
                case 2:
                case 4:
                case 5:
                case 6:
                    View inflate2 = View.inflate(DeviceSettingActivity.this, R.layout.widget_setting_selector, null);
                    TextView textView = (TextView) inflate2.findViewById(R.id.tvName);
                    textView.setText(cameraSettingItem.displayName);
                    TextView textView2 = (TextView) inflate2.findViewById(R.id.tvValue);
                    if (cameraSettingItem.type != 5) {
                        if ("sw_version".equals(cameraSettingItem.optionName) && cameraSettingItem.optionValue != null) {
                            textView2.setText(cameraSettingItem.optionValue.split("_")[1]);
                            ImageView imageView = (ImageView) inflate2.findViewById(R.id.ivNew);
                            if (DeviceSettingActivity.this.l) {
                                imageView.setVisibility(0);
                            }
                        } else if ("hw_version".equals(cameraSettingItem.optionName)) {
                            String a2 = com.xiaoyi.camera.g.a().a("serial_number");
                            if ("Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                                if (!TextUtils.isEmpty(a2) && a2.length() >= 7) {
                                    textView2.setText(a2.substring(4, 7));
                                }
                            } else if (!TextUtils.isEmpty(a2) && a2.length() >= 7) {
                                textView2.setText(a2.substring(1, 4));
                            }
                        } else if ("product_name".equals(cameraSettingItem.optionName)) {
                            String a3 = com.xiaoyi.camera.g.a().a("product_name");
                            if ("Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "Z16".equals(com.xiaoyi.camera.g.a().a("model"))) {
                                textView2.setText(a3);
                            } else if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
                                if (TextUtils.isEmpty(a3) || !a3.contains("4K")) {
                                    textView2.setText(a3);
                                } else {
                                    textView2.setText("YI Lite Action Camera");
                                }
                            } else if ("J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                                textView2.setText("YI Discovery Action Camera");
                            } else if (TextUtils.isEmpty(a3)) {
                                textView2.setText("YI Action Camera");
                            } else {
                                textView2.setText(cameraSettingItem.optionValue);
                            }
                        } else {
                            String a4 = ab.a(DeviceSettingActivity.this, cameraSettingItem.optionName, cameraSettingItem.optionValue);
                            if ("auto_power_off".equals(cameraSettingItem.optionName) && "off".equals(cameraSettingItem.optionValue)) {
                                textView2.setText(R.string.auto_power_off_never);
                            } else {
                                textView2.setText(a4);
                            }
                        }
                    }
                    if ("mic_level".equals(cameraSettingItem.optionName)) {
                        if ("on".equals(com.xiaoyi.camera.g.a().a("rec_audio_support"))) {
                            cameraSettingItem.enable = true;
                            textView.setTextColor(DeviceSettingActivity.this.getResources().getColor(R.color.setting_header_title_color));
                            textView2.setTextColor(DeviceSettingActivity.this.getResources().getColor(R.color.setting_header_title_color));
                        } else {
                            cameraSettingItem.enable = false;
                            textView.setTextColor(DeviceSettingActivity.this.getResources().getColor(R.color.setting_title_color));
                            textView2.setTextColor(DeviceSettingActivity.this.getResources().getColor(R.color.setting_title_color));
                            textView2.setText(R.string.set_auto);
                        }
                    }
                    if (6 != cameraSettingItem.type) {
                        return inflate2;
                    }
                    inflate2.findViewById(R.id.ivRight).setVisibility(8);
                    return inflate2;
                case 3:
                    View inflate3 = View.inflate(DeviceSettingActivity.this, R.layout.widget_setting_switcher, null);
                    ((TextView) inflate3.findViewById(R.id.tvName)).setText(cameraSettingItem.displayName);
                    SwitchButton switchButton = (SwitchButton) inflate3.findViewById(R.id.switcher);
                    if ("on".equals(cameraSettingItem.optionValue)) {
                        switchButton.setChecked(true);
                    } else {
                        switchButton.setChecked(false);
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
                    return inflate3;
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            int i2 = ((CameraSettingItem) DeviceSettingActivity.this.e.get(i)).type;
            return (i2 == 0 || i2 == 7 || i2 == 3) ? false : true;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CameraSettingItem cameraSettingItem = (CameraSettingItem) compoundButton.getTag();
            if (!"video_output_dev_type".equals(cameraSettingItem.optionName)) {
                if (cameraSettingItem.enable) {
                    if (z) {
                        cameraSettingItem.optionValue = "on";
                    } else {
                        cameraSettingItem.optionValue = "off";
                    }
                    c.a().c(new v(cameraSettingItem, DeviceSettingActivity.this.b));
                    return;
                }
                return;
            }
            if (cameraSettingItem.enable) {
                if (z) {
                    cameraSettingItem.optionValue = "tv";
                } else {
                    cameraSettingItem.optionValue = "off";
                }
                c.a().c(new v(cameraSettingItem, DeviceSettingActivity.this.b));
                g.a("debug_event", getClass() + " post SettingItemChangedEvent", new Object[0]);
            }
        }
    }

    private void a(final CameraSettingItem cameraSettingItem) {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        this.n = (LocateCameraFragment) Fragment.instantiate(this, LocateCameraFragment.class.getName(), bundle);
        this.n.a(new LocateCameraFragment.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.18
            @Override // com.ants360.z13.fragment.LocateCameraFragment.a
            public void a(DialogFragment dialogFragment, boolean z) {
                cameraSettingItem.optionValue = z ? "on" : "off";
                c.a().c(new v(cameraSettingItem, DeviceSettingActivity.this.b));
                g.a("debug_event", getClass() + " post SettingItemChangedEvent", new Object[0]);
            }
        });
        this.n.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.19
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
            }
        });
        this.n.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.11
            @Override // java.lang.Runnable
            public void run() {
                DeviceSettingActivity.this.k();
                if (!z) {
                    DeviceSettingActivity.this.g.setVisibility(8);
                    DeviceSettingActivity.this.h.setText("");
                    return;
                }
                if (DeviceSettingActivity.this.m != null && DeviceSettingActivity.this.m.getDialog() != null && DeviceSettingActivity.this.m.getDialog().isShowing()) {
                    DeviceSettingActivity.this.m.dismiss();
                }
                if (DeviceSettingActivity.this.n != null && DeviceSettingActivity.this.n.getDialog() != null && DeviceSettingActivity.this.n.getDialog().isShowing()) {
                    DeviceSettingActivity.this.n.dismiss();
                }
                if (DeviceSettingActivity.this.o != null && DeviceSettingActivity.this.o.getDialog() != null && DeviceSettingActivity.this.o.getDialog().isShowing()) {
                    DeviceSettingActivity.this.o.dismiss();
                }
                DeviceSettingActivity.this.g.setVisibility(0);
                DeviceSettingActivity.this.h.setText(str);
            }
        });
    }

    private void b(final CameraSettingItem cameraSettingItem) {
        if (cameraSettingItem == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("title", getString(R.string.set_camera_clock_title));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.set_camera_clock_message));
        this.m = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        this.m.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.20
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                cameraSettingItem.optionValue = e.b(new Date().getTime());
                c.a().c(new v(cameraSettingItem, DeviceSettingActivity.this.b));
                g.a("debug_event", getClass() + " post SettingItemChangedEvent", new Object[0]);
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
        this.m.a(this);
    }

    private void b(boolean z) {
        try {
            if (z) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("photo_taken"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("burst_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("precise_cont_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("video_record_complete"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_usb_storage"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("btc_delete_all_binded_dev"));
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
                intentFilter.addAction(com.xiaoyi.camera.a.a("enter_live"));
                intentFilter.addAction(com.xiaoyi.camera.a.a("exit_live"));
                registerReceiver(this.s, intentFilter);
            } else {
                unregisterReceiver(this.s);
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

    private void c(CameraSettingItem cameraSettingItem) {
        com.xiaoyi.camera.g.a().r(new com.xiaoyi.camera.c.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.21
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
                DeviceSettingActivity.this.u.sendEmptyMessage(3);
            }
        });
    }

    private void d(final CameraSettingItem cameraSettingItem) {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("title", getString(R.string.setting_restore_factory_settings));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.factory_reset_message));
        this.o = (CustomBottomDialogFragment) Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        this.o.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.2
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                try {
                    DeviceSettingActivity.this.r = new f(DeviceSettingActivity.this);
                    if (DeviceSettingActivity.this.r != null) {
                        DeviceSettingActivity.this.p = DeviceSettingActivity.this.r.g().replace("\"", "");
                        DeviceSettingActivity.this.q = DeviceSettingActivity.this.r.h().replace("\"", "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cameraSettingItem.optionValue = "on";
                c.a().c(new v(cameraSettingItem, DeviceSettingActivity.this.b));
                g.a("debug_event", getClass() + " post SettingItemChangedEvent", new Object[0]);
                CameraApplication.f1401a.c(false);
                DeviceSettingActivity.this.u.postDelayed(DeviceSettingActivity.this.v, 2000L);
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
        this.o.a(this);
    }

    private void d(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, str);
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        CustomBottomConfirmDialogFragment customBottomConfirmDialogFragment = (CustomBottomConfirmDialogFragment) CustomBottomConfirmDialogFragment.instantiate(this, CustomBottomConfirmDialogFragment.class.getName(), bundle);
        customBottomConfirmDialogFragment.setCancelable(false);
        customBottomConfirmDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.8
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                c.a().c(new com.xiaoyi.camera.a.a());
                com.xiaoyi.camera.g.a().c();
                DeviceSettingActivity.this.startActivity(new Intent(DeviceSettingActivity.this, (Class<?>) MainActivity.class));
                DeviceSettingActivity.this.finish();
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
        this.e.clear();
        this.e = com.xiaomi.xy.sportscamera.camera.a.a.b(this);
        if (this.f != null) {
            this.f.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        com.xiaoyi.camera.g.a().t(new AnonymousClass5());
        a(true, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        CameraApplication.f1401a.c(false);
        com.xiaoyi.camera.d.c a2 = com.xiaoyi.camera.d.c.a(this);
        com.xiaoyi.camera.devicedao.a a3 = a2.a(this.q);
        if (a3 != null) {
            a3.b(this.p);
            a3.c("1234567890");
            a2.b(a3);
        }
        this.r.c(this.p);
        a(false, (String) null);
        d(getString(R.string.restore_factory_success));
        com.xiaoyi.camera.g.a().c();
    }

    private void j() {
        CameraSettingItem cameraSettingItem = new CameraSettingItem(getResources().getString(R.string.setting_camera_clock), "camera_clock", 4, false);
        cameraSettingItem.optionValue = e.b(new Date().getTime());
        c.a().c(new v(cameraSettingItem, this.b));
        g.a("debug_event", getClass() + " post SettingItemChangedEvent", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.m != null && this.m.getDialog() != null && this.m.getDialog().isShowing()) {
            this.m.dismissAllowingStateLoss();
        }
        if (this.n != null && this.n.getDialog() != null && this.n.getDialog().isShowing()) {
            this.n.dismissAllowingStateLoss();
        }
        if (this.o == null || this.o.getDialog() == null || !this.o.getDialog().isShowing()) {
            return;
        }
        this.o.dismissAllowingStateLoss();
    }

    private void l() {
        new Thread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.13
            @Override // java.lang.Runnable
            public void run() {
                com.xiaomi.xy.sportscamera.camera.d a2 = com.xiaomi.xy.sportscamera.camera.d.a();
                String a3 = com.xiaoyi.camera.g.a().a("serial_number");
                String a4 = com.xiaoyi.camera.g.a().a("model");
                String d = a2.d(a2.h(a4, a3));
                String a5 = com.xiaoyi.camera.g.a().a("sw_version");
                if ((com.xiaoyi.camera.module.a.c(a5, d) || !a2.p(a4, a3).booleanValue()) && (com.xiaoyi.camera.module.a.c(a5, a2.m(a4)) || !a2.q(a4, a3).booleanValue())) {
                    return;
                }
                DeviceSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.13.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DeviceSettingActivity.this.l = true;
                        if (DeviceSettingActivity.this.f != null) {
                            DeviceSettingActivity.this.f.notifyDataSetChanged();
                        }
                    }
                });
            }
        }).start();
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(d dVar, JSONObject jSONObject) {
        g.a(BuildConfig.BUILD_TYPE, "------------------- onReceiveMessage = " + jSONObject.toString(), new Object[0]);
        if (this.u == null) {
            a(false, (String) null);
            return;
        }
        this.u.removeCallbacks(this.t);
        String str = (String) dVar.a("type");
        int optInt = jSONObject.optInt("related");
        if ("video_standard".equals(str)) {
            b.b("video_resolution");
            b.b("video_loop_resolution");
            b.b("timelapse_video_resolution");
            b.b("video_photo_resolution");
        }
        if (dVar == null || dVar.a() != 2 || optInt > 0) {
            runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.9
                @Override // java.lang.Runnable
                public void run() {
                    DeviceSettingActivity.this.a(false, (String) null);
                }
            });
        } else {
            com.xiaoyi.camera.g.a().b();
        }
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(d dVar, JSONObject jSONObject) {
        if (this.u == null) {
            return;
        }
        this.u.removeCallbacks(this.t);
        this.u.removeCallbacks(this.v);
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.10
            @Override // java.lang.Runnable
            public void run() {
                DeviceSettingActivity.this.a(false, (String) null);
                DeviceSettingActivity.this.a(R.string.set_failed);
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
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.14
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                DeviceSettingActivity.this.finish();
                DeviceSettingActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
                Intent intent = new Intent(DeviceSettingActivity.this, (Class<?>) MainActivity.class);
                intent.setFlags(268435456);
                DeviceSettingActivity.this.startActivity(intent);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                DeviceSettingActivity.this.finish();
                DeviceSettingActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
                Intent intent = new Intent(DeviceSettingActivity.this, (Class<?>) MainActivity.class);
                intent.setFlags(268435456);
                DeviceSettingActivity.this.startActivity(intent);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                DeviceSettingActivity.this.finish();
                DeviceSettingActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
                Intent intent = new Intent(DeviceSettingActivity.this, (Class<?>) MainActivity.class);
                intent.setFlags(268435456);
                DeviceSettingActivity.this.startActivity(intent);
            }
        });
        customBottomDialogFragment.a(this);
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
        this.c = (CustomTitleBar) findViewById(R.id.titleBar);
        this.c.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.12
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                DeviceSettingActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.g = (RelativeLayout) findViewById(R.id.rlBlockingCover);
        this.h = (TextView) findViewById(R.id.tvInfo);
        this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.15
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.d = (ListView) findViewById(R.id.listView);
        this.f = new a();
        this.d.setAdapter((ListAdapter) this.f);
        this.d.setOnItemClickListener(this);
        c.a().a(this);
        b(true);
        this.k = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f = null;
        if (this.u != null) {
            this.u.removeCallbacks(this.t);
            this.u = null;
        }
        c.a().b(this);
        try {
            b(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEvent(v vVar) {
        g.a("debug_event", getClass() + " recevied SettingItemChangedEvent", new Object[0]);
        CameraSettingItem a2 = vVar.a();
        String str = a2.optionName;
        String str2 = a2.optionValue;
        if (this.b.equals(vVar.b())) {
            int indexOf = this.e.indexOf(a2);
            String a3 = com.xiaoyi.camera.g.a().a(str);
            if (a3 == null || !a3.equals(str2) || "restore_factory_settings".equals(a2.optionName)) {
                com.xiaoyi.camera.g.a().a(str, str2);
                com.xiaoyi.camera.g.a().a(str, str2, this);
                this.e.remove(indexOf);
                this.e.add(indexOf, a2);
                if (this.f != null) {
                    this.f.notifyDataSetChanged();
                }
                if (a2.stopVf) {
                    a(true, "");
                    this.u.postDelayed(this.t, 15000L);
                }
                UploadStatsManager.a("camera_settings", a2.optionName, a2.optionValue);
            }
        }
    }

    public void onEvent(final com.xiaoyi.camera.a.d dVar) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (DeviceSettingActivity.this.g == null) {
                    return;
                }
                DeviceSettingActivity.this.a(false, (String) null);
                if (dVar.a()) {
                    DeviceSettingActivity.this.g();
                } else {
                    DeviceSettingActivity.this.a(R.string.set_failed);
                }
            }
        });
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Bundle bundle = new Bundle();
        CameraSettingItem cameraSettingItem = this.e.get(i);
        ArrayList<String> a2 = b.a(cameraSettingItem.optionName);
        bundle.putSerializable(CameraSettingItem.SETTING_ITEM, cameraSettingItem);
        bundle.putSerializable(CameraSettingItem.SETTING_OPTIONS, a2);
        if (cameraSettingItem.enable) {
            switch (cameraSettingItem.type) {
                case 1:
                case 2:
                    Intent intent = new Intent(this, (Class<?>) SettingValueActivity.class);
                    intent.putExtra(SettingValueActivity.b, bundle);
                    startActivity(intent);
                    return;
                case 3:
                default:
                    return;
                case 4:
                    if ("camera_clock".equals(cameraSettingItem.optionName)) {
                        b(cameraSettingItem);
                        return;
                    }
                    if ("sdcard".equals(cameraSettingItem.optionName)) {
                        if ("insert".equals(com.xiaoyi.camera.g.a().a("sd_card_status"))) {
                            startActivity(new Intent(this, (Class<?>) CameraSDCardActivity.class));
                            return;
                        } else {
                            a(R.string.sd_card_remove);
                            return;
                        }
                    }
                    if ("wifi".equals(cameraSettingItem.optionName)) {
                        startActivity(new Intent(this, (Class<?>) CameraWifiSettingActivity.class));
                        return;
                    }
                    if ("sw_version".equals(cameraSettingItem.optionName)) {
                        if (com.xiaoyi.camera.b.b && this.l) {
                            startActivity(new Intent(this, (Class<?>) CameraUpgradeActivity.class));
                            return;
                        }
                        return;
                    }
                    if ("btc_delete_all_binded_dev".equals(cameraSettingItem.optionName)) {
                        c(cameraSettingItem);
                        return;
                    }
                    if ("hw_version".equals(cameraSettingItem.optionName)) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis <= this.j || currentTimeMillis - this.j >= 500) {
                            this.k = 0;
                        } else {
                            this.k++;
                            if (4 == this.k) {
                                this.k = 0;
                                if (com.xiaoyi.camera.d.a.d() && !i.a().b("is_debug_firware", false)) {
                                    i.a().a("is_debug_firware", true);
                                    this.e.add(new CameraSettingItem(getString(R.string.setting_firmware_log), "save_log", 3));
                                    if (this.f != null) {
                                        this.f.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                        this.j = currentTimeMillis;
                        return;
                    }
                    return;
                case 5:
                    if (!"restore_factory_settings".equals(cameraSettingItem.optionName)) {
                        if ("buzzer_ring".equals(cameraSettingItem.optionName)) {
                            a(cameraSettingItem);
                            return;
                        }
                        return;
                    } else if (TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("restore_wifi")) || TextUtils.isEmpty(com.xiaoyi.camera.g.a().a("restore_bt"))) {
                        d(cameraSettingItem);
                        return;
                    } else {
                        startActivity(new Intent(this, (Class<?>) CameraResetActivity.class));
                        return;
                    }
                case 6:
                    if ("hw_version".equals(cameraSettingItem.optionName)) {
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 <= this.j || currentTimeMillis2 - this.j >= 500) {
                            this.k = 0;
                        } else {
                            this.k++;
                            if (4 == this.k) {
                                this.k = 0;
                                if (com.xiaoyi.camera.g.a().a("save_log").equals("on")) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                    builder.setTitle(R.string.title_firmware_log);
                                    builder.setMessage(R.string.download_firmware_log);
                                    builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.16
                                        @Override // android.content.DialogInterface.OnClickListener
                                        public void onClick(DialogInterface dialogInterface, int i2) {
                                            DeviceSettingActivity.this.h();
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.set.DeviceSettingActivity.17
                                        @Override // android.content.DialogInterface.OnClickListener
                                        public void onClick(DialogInterface dialogInterface, int i2) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    builder.create().show();
                                }
                            }
                        }
                        this.j = currentTimeMillis2;
                        return;
                    }
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        g();
        l();
        int b = i.a().b("current_operation_model", -1);
        if (b >= 0) {
            c(b);
        } else {
            if (i.a().b("already_init_time", false)) {
                return;
            }
            i.a().a("already_init_time", true);
            j();
        }
    }
}
