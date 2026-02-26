package com.xiaomi.xy.sportscamera.camera.a;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.e;
import com.xiaoyi.camera.g;
import com.xiaoyi.camera.module.CameraSettingItem;
import com.xiaoyi.camera.module.b;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class a {
    public static ArrayList<String> a(String str) {
        if ("timelapse_video_resolution".equals(str) && "Z13".equals(g.a().a("model"))) {
            return CameraMainController.y.equals(g.a().a("video_standard")) ? new ArrayList<>(Arrays.asList(e.d)) : new ArrayList<>(Arrays.asList(e.c));
        }
        return b.a(str);
    }

    public static List<CameraSettingItem> a(Context context) {
        String a2 = g.a().a("system_mode");
        String a3 = g.a().a("rec_mode");
        String a4 = g.a().a("capture_mode");
        String a5 = g.a().a("model");
        ArrayList arrayList = new ArrayList();
        if (CameraMainController.CameraMode.RecordMode.toString().equals(a2)) {
            if ("Z16".equals(a5) || "Z18".equals(a5) || "J11".equals(a5) || "J22".equals(a5)) {
                if (Constant.RecordMode.NORMAL.toString().equals(a3)) {
                    a(context, arrayList);
                } else if (Constant.RecordMode.TIMELAPES.toString().equals(a3)) {
                    b(context, arrayList);
                } else if (Constant.RecordMode.SLOW.toString().equals(a3)) {
                    c(context, arrayList);
                } else if (Constant.RecordMode.PHOTO.toString().equals(a3)) {
                    d(context, arrayList);
                } else if (Constant.RecordMode.LOOP.toString().equals(a3)) {
                    e(context, arrayList);
                }
            } else if ("Z13".equals(a5)) {
                if (Constant.RecordMode.NORMAL.toString().equals(a3)) {
                    f(context, arrayList);
                } else if (Constant.RecordMode.TIMELAPES.toString().equals(a3)) {
                    g(context, arrayList);
                }
            }
        } else if (CameraMainController.CameraMode.CaptureMode.toString().equals(a2)) {
            if ("Z16".equals(a5) || "Z18".equals(a5) || "J11".equals(a5) || "J22".equals(a5)) {
                if (Constant.CaptureMode.NORMAL.toString().equals(a4)) {
                    h(context, arrayList);
                } else if (Constant.CaptureMode.TIMELAPES.toString().equals(a4)) {
                    j(context, arrayList);
                } else if (Constant.CaptureMode.TIMER.toString().equals(a4)) {
                    i(context, arrayList);
                } else if (Constant.CaptureMode.BURST.toString().equals(a4)) {
                    k(context, arrayList);
                }
            } else if ("Z13".equals(a5)) {
                if (Constant.CaptureMode.NORMAL.toString().equals(a4)) {
                    l(context, arrayList);
                } else if (Constant.CaptureMode.TIMELAPES.toString().equals(a4)) {
                    n(context, arrayList);
                } else if (Constant.CaptureMode.TIMER.toString().equals(a4)) {
                    m(context, arrayList);
                } else if (Constant.CaptureMode.BURST.toString().equals(a4)) {
                    o(context, arrayList);
                }
            }
        }
        return arrayList;
    }

    private static void a(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("video_resolution"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "video_resolution", 1, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_shutter")) && "on".equals(g.a().a("video_shutter_support"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_shutter), "video_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("fov")) && "on".equals(g.a().a("support_fov"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_fov), "fov", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("auto_low_light"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_auto_low_light), "auto_low_light", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_quality"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_quality), "video_quality", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_wb), "iq_video_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "video_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso_min), "iq_video_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso), "iq_video_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "video_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_ev), "iq_video_ev", 2, true));
        }
        if (!"J22".equals(g.a().a("model")) && !TextUtils.isEmpty(g.a().a("iq_eis_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_setting_eis), "iq_eis_enable", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_stamp")) && "on".equals(g.a().a("stamp_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_time_stamp), "video_stamp", 2, true));
        }
        if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_volume_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_volume), "video_volume_set", 2, true));
        } else if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_mute_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_mute), "video_mute_set", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("sound_effect"))) {
            list.add(new CameraSettingItem(context.getString(R.string.sound_effect), "sound_effect", 2, true));
        }
        if (TextUtils.isEmpty(g.a().a("video_file_max_size"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.video_file_size), "video_file_max_size", 2, true));
    }

    public static ArrayList<CameraSettingItem> b(Context context) {
        ArrayList<CameraSettingItem> arrayList = new ArrayList<>();
        String a2 = g.a().a("model");
        if ("Z16".equals(a2) || "Z18".equals(a2) || "J11".equals(a2) || "J22".equals(a2)) {
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_camera), "", 0));
            if (!TextUtils.isEmpty(g.a().a("long_shutter_define"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.long_shutter_define), "long_shutter_define", 2, true));
            }
            if (!TextUtils.isEmpty(g.a().a("system_default_mode"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_system_default_mode), "system_default_mode", 2, true));
            }
            if (!TextUtils.isEmpty(g.a().a("capture_default_mode"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_capture_default_mode), "capture_default_mode", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("loop_record"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_loop_record), "loop_record", 3, true));
            }
            if (!TextUtils.isEmpty(g.a().a("start_wifi_while_booted"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_start_wifi_while_booted), "start_wifi_while_booted", 3, true));
            }
            if (!TextUtils.isEmpty(g.a().a("video_output_dev_type"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_video_output_status), "video_output_dev_type", 3, true));
            }
            if (!TextUtils.isEmpty(g.a().a("video_rotate"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_video_rotate), "video_rotate", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("emergency_file_backup"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_emergency_file_backup), "emergency_file_backup", 3));
            }
            if (TextUtils.isEmpty(g.a().a("restore_wifi")) || TextUtils.isEmpty(g.a().a("restore_bt"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_unbind_bluetooth_dev), "btc_delete_all_binded_dev", 4));
            }
            if (!TextUtils.isEmpty(g.a().a("buzzer_volume"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_buzzer_volume), "buzzer_volume", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("mic_level"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.video_mic_volume), "mic_level", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("video_standard"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_video_standard), "video_standard", 2, true));
            }
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_wifi), "wifi", 4));
            if (!TextUtils.isEmpty(g.a().a("led_mode"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_led_mode), "led_mode", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("camera_clock"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_camera_clock), "camera_clock", 4));
            }
            if (!TextUtils.isEmpty(g.a().a("screen_auto_lock"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_screen_auto_lock), "screen_auto_lock", 2, true));
            }
            if (!TextUtils.isEmpty(g.a().a("auto_power_off"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_auto_power_off), "auto_power_off", 2, true));
            }
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_device), "", 0));
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_product_name), "product_name", 6));
            if (!TextUtils.isEmpty(g.a().a("serial_number"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_serial_number), "serial_number", 6));
            }
            if (!TextUtils.isEmpty(g.a().a("hw_version"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_hw_version), "hw_version", 6));
            }
            if (!TextUtils.isEmpty(g.a().a("sw_version"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_sw_version), "sw_version", 4));
            }
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_sdcard), "sdcard", 4));
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_restore_factory_settings), "restore_factory_settings", 5, true));
            if (com.xiaoyi.camera.d.a.d() && i.a().b("is_debug_firware", false)) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_firmware_log), "save_log", 3));
            }
        } else if ("Z13".equals(a2)) {
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_camera), "", 0));
            if (!TextUtils.isEmpty(g.a().a("long_shutter_define"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.long_shutter_define), "long_shutter_define", 2, true));
            }
            if (!TextUtils.isEmpty(g.a().a("system_default_mode"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_system_default_mode), "system_default_mode", 2, true));
            }
            if (!TextUtils.isEmpty(g.a().a("capture_default_mode"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_capture_default_mode), "capture_default_mode", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("loop_record"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_loop_record), "loop_record", 3, true));
            }
            if (!TextUtils.isEmpty(g.a().a("start_wifi_while_booted"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_start_wifi_while_booted), "start_wifi_while_booted", 3, true));
            }
            if (!TextUtils.isEmpty(g.a().a("video_output_dev_type"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_video_output_status), "video_output_dev_type", 3, true));
            }
            if (!TextUtils.isEmpty(g.a().a("video_rotate"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_video_rotate), "video_rotate", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("emergency_file_backup"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_emergency_file_backup), "emergency_file_backup", 3));
            }
            if (TextUtils.isEmpty(g.a().a("restore_wifi")) || TextUtils.isEmpty(g.a().a("restore_bt"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_unbind_bluetooth_dev), "btc_delete_all_binded_dev", 4));
            }
            if (!TextUtils.isEmpty(g.a().a("buzzer_volume"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_buzzer_volume), "buzzer_volume", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("video_standard"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_video_standard), "video_standard", 2, true));
            }
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_wifi), "wifi", 4));
            if (!TextUtils.isEmpty(g.a().a("led_mode"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_led_mode), "led_mode", 2));
            }
            if (!TextUtils.isEmpty(g.a().a("camera_clock"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_camera_clock), "camera_clock", 4));
            }
            if (!TextUtils.isEmpty(g.a().a("auto_power_off"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_auto_power_off), "auto_power_off", 2, true));
            }
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_device), "", 0));
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_product_name), "product_name", 6));
            if (!TextUtils.isEmpty(g.a().a("serial_number"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_serial_number), "serial_number", 6));
            }
            if (!TextUtils.isEmpty(g.a().a("hw_version"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_hw_version), "hw_version", 6));
            }
            if (!TextUtils.isEmpty(g.a().a("sw_version"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_sw_version), "sw_version", 4));
            }
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_sdcard), "sdcard", 4));
            if (!TextUtils.isEmpty(g.a().a("buzzer_ring"))) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_buzzer_ring), "buzzer_ring", 5));
            }
            arrayList.add(new CameraSettingItem(context.getString(R.string.setting_restore_factory_settings), "restore_factory_settings", 5, true));
            if (com.xiaoyi.camera.d.a.d() && i.a().b("is_debug_firware", false)) {
                arrayList.add(new CameraSettingItem(context.getString(R.string.setting_firmware_log), "save_log", 3));
            }
        }
        return arrayList;
    }

    private static void b(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("timelapse_video"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_timelaps_internal), "timelapse_video", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("timelapse_video_duration"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_timelaps_video_length), "timelapse_video_duration", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("timelapse_video_resolution"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "timelapse_video_resolution", 1, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_shutter")) && "on".equals(g.a().a("video_shutter_support"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_shutter), "video_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_quality"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_quality), "video_quality", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("fov")) && "on".equals(g.a().a("support_fov"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_fov), "fov", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_wb), "iq_video_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "video_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso_min), "iq_video_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso), "iq_video_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "video_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_ev), "iq_video_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("auto_low_light"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_auto_low_light), "auto_low_light", 3, true));
        }
        if (!"J22".equals(g.a().a("model")) && !TextUtils.isEmpty(g.a().a("iq_eis_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_setting_eis), "iq_eis_enable", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_stamp")) && "on".equals(g.a().a("stamp_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_time_stamp), "video_stamp", 2, true));
        }
        if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_volume_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_volume), "video_volume_set", 2, true));
        } else if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_mute_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_mute), "video_mute_set", 3, true));
        }
        if (TextUtils.isEmpty(g.a().a("video_file_max_size"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.video_file_size), "video_file_max_size", 2, true));
    }

    private static void c(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "slow_motion_res", 2, true));
        } else if (!TextUtils.isEmpty(g.a().a("slow_motion_rate"))) {
            list.add(new CameraSettingItem(context.getString(R.string.slow_motion_rate), "slow_motion_rate", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_shutter")) && "on".equals(g.a().a("video_shutter_support"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_shutter), "video_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("fov")) && "on".equals(g.a().a("support_fov"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_fov), "fov", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("auto_low_light"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_auto_low_light), "auto_low_light", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_quality"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_quality), "video_quality", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_wb), "iq_video_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "video_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso_min), "iq_video_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso), "iq_video_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "video_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_ev), "iq_video_ev", 2, true));
        }
        if (!"J22".equals(g.a().a("model")) && !TextUtils.isEmpty(g.a().a("iq_eis_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_setting_eis), "iq_eis_enable", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_stamp")) && "on".equals(g.a().a("stamp_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_time_stamp), "video_stamp", 2, true));
        }
        if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_volume_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_volume), "video_volume_set", 2, true));
        } else if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_mute_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_mute), "video_mute_set", 3, true));
        }
        if (TextUtils.isEmpty(g.a().a("video_file_max_size"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.video_file_size), "video_file_max_size", 2, true));
    }

    private static void d(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("record_photo_time"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_timelaps_internal), "record_photo_time", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_photo_resolution"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "video_photo_resolution", 1, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_shutter")) && "on".equals(g.a().a("video_shutter_support"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_shutter), "video_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("fov")) && "on".equals(g.a().a("support_fov"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_fov), "fov", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("auto_low_light"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_auto_low_light), "auto_low_light", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_quality"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_quality), "video_quality", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_wb), "iq_video_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "video_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso_min), "iq_video_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso), "iq_video_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "video_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_ev), "iq_video_ev", 2, true));
        }
        if (!"J22".equals(g.a().a("model")) && !TextUtils.isEmpty(g.a().a("iq_eis_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_setting_eis), "iq_eis_enable", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_stamp")) && "on".equals(g.a().a("stamp_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_time_stamp), "video_stamp", 2, true));
        }
        if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_volume_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_volume), "video_volume_set", 2, true));
        } else if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_mute_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_mute), "video_mute_set", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("sound_effect"))) {
            list.add(new CameraSettingItem(context.getString(R.string.sound_effect), "sound_effect", 2, true));
        }
        if (TextUtils.isEmpty(g.a().a("video_file_max_size"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.video_file_size), "video_file_max_size", 2, true));
    }

    private static void e(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("loop_rec_duration"))) {
            list.add(new CameraSettingItem(context.getString(R.string.loop_record_duration), "loop_rec_duration", 2, true));
        }
        if ("J11".equals(g.a().a("model")) && !TextUtils.isEmpty(g.a().a("video_loop_resolution"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "video_loop_resolution", 1, true));
        } else if (!TextUtils.isEmpty(g.a().a("video_resolution"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "video_resolution", 1, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_shutter")) && "on".equals(g.a().a("video_shutter_support"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_shutter), "video_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("fov")) && "on".equals(g.a().a("support_fov"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_fov), "fov", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("auto_low_light"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_auto_low_light), "auto_low_light", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_quality"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_quality), "video_quality", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_wb), "iq_video_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "video_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso_min), "iq_video_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso), "iq_video_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "video_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_ev), "iq_video_ev", 2, true));
        }
        if (!"J22".equals(g.a().a("model")) && !TextUtils.isEmpty(g.a().a("iq_eis_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_setting_eis), "iq_eis_enable", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_stamp")) && "on".equals(g.a().a("stamp_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_time_stamp), "video_stamp", 2, true));
        }
        if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_volume_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_volume), "video_volume_set", 2, true));
        } else if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_mute_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_mute), "video_mute_set", 3, true));
        }
        if (TextUtils.isEmpty(g.a().a("sound_effect"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.sound_effect), "sound_effect", 2, true));
    }

    private static void f(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("video_resolution"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "video_resolution", 1, true));
        }
        if (!TextUtils.isEmpty(g.a().a("fov")) && "on".equals(g.a().a("support_fov"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_fov), "fov", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("auto_low_light"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_auto_low_light), "auto_low_light", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_quality"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_quality), "video_quality", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_wb), "iq_video_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso), "iq_video_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_ev), "iq_video_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "video_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_eis_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_setting_eis), "iq_eis_enable", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_time_stamp), "video_stamp", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_volume_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_volume), "video_volume_set", 2, true));
        } else if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_mute_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_mute), "video_mute_set", 3, true));
        }
        if (TextUtils.isEmpty(g.a().a("video_sharpness")) || !"on".equals(g.a().a("support_sharpness"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "video_sharpness", 2));
    }

    private static void g(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("timelapse_video"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_timelaps_internal), "timelapse_video", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("timelapse_video_duration"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_timelaps_video_length), "timelapse_video_duration", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("timelapse_video_resolution"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_resolution), "timelapse_video_resolution", 1, true));
        }
        if (!TextUtils.isEmpty(g.a().a("fov")) && "on".equals(g.a().a("support_fov"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_fov), "fov", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("auto_low_light"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_auto_low_light), "auto_low_light", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_quality"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_video_quality), "video_quality", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_wb), "iq_video_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_iso), "iq_video_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_video_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_setting_ev), "iq_video_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "video_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_eis_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_setting_eis), "iq_eis_enable", 3, true));
        }
        if (!TextUtils.isEmpty(g.a().a("video_volume_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_volume), "video_volume_set", 2, true));
        } else if ("on".equals(g.a().a("rec_audio_support")) && !TextUtils.isEmpty(g.a().a("video_mute_set"))) {
            list.add(new CameraSettingItem(context.getString(R.string.video_mute), "video_mute_set", 3, true));
        }
        if (TextUtils.isEmpty(g.a().a("video_sharpness")) || !"on".equals(g.a().a("support_sharpness"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "video_sharpness", 2));
    }

    private static void h(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_shutter"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_shutter), "iq_photo_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso_min), "iq_photo_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("quick_view"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_quick_view), "quick_view", 2));
        }
        if (TextUtils.isEmpty(g.a().a("photo_file_type"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.photo_file_type), "photo_file_type", 2));
    }

    private static void i(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("precise_selftime"))) {
            list.add(new CameraSettingItem(context.getString(R.string.countDown), "precise_selftime", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_shutter"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_shutter), "iq_photo_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso_min), "iq_photo_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("quick_view"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_quick_view), "quick_view", 2));
        }
        if (TextUtils.isEmpty(g.a().a("photo_file_type"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.photo_file_type), "photo_file_type", 2));
    }

    private static void j(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("precise_cont_time"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_timelaps_internal), "precise_cont_time", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("timelapse_photo_shutter"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_shutter), "timelapse_photo_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso_min), "iq_photo_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
        }
        if (TextUtils.isEmpty(g.a().a("photo_file_type")) || !"on".equals(g.a().a("photo_file_type_settable"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.photo_file_type), "photo_file_type", 2));
    }

    private static void k(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("burst_capture_number"))) {
            list.add(new CameraSettingItem(context.getString(R.string.frequency), "burst_capture_number", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso_min")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso_min), "iq_photo_iso_min", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
    }

    private static void l(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
        }
        if (TextUtils.isEmpty(g.a().a("quick_view"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.camera_quick_view), "quick_view", 2));
    }

    private static void m(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("precise_selftime"))) {
            list.add(new CameraSettingItem(context.getString(R.string.countDown), "precise_selftime", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_sharpness")) && "on".equals(g.a().a("support_sharpness"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
        }
        if (TextUtils.isEmpty(g.a().a("quick_view"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.camera_quick_view), "quick_view", 2));
    }

    private static void n(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("precise_cont_time"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_timelaps_internal), "precise_cont_time", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("timelapse_photo_shutter"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_shutter), "timelapse_photo_shutter", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
        }
        if (TextUtils.isEmpty(g.a().a("photo_sharpness")) || !"on".equals(g.a().a("support_sharpness"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
    }

    private static void o(Context context, List<CameraSettingItem> list) {
        if (!TextUtils.isEmpty(g.a().a("burst_capture_number"))) {
            list.add(new CameraSettingItem(context.getString(R.string.frequency), "burst_capture_number", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_size"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_size), "photo_size", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("meter_mode"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_meter_mode), "meter_mode", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_wb")) && "on".equals(g.a().a("support_wb"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_wb), "iq_photo_wb", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_iso")) && "on".equals(g.a().a("support_iso"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_iso), "iq_photo_iso", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("iq_photo_ev")) && "on".equals(g.a().a("ev_enable"))) {
            list.add(new CameraSettingItem(context.getString(R.string.photo_setting_ev), "iq_photo_ev", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_flat_color")) && "on".equals(g.a().a("support_flat_color"))) {
            list.add(new CameraSettingItem(context.getString(R.string.camera_color), "photo_flat_color", 2, true));
        }
        if (!TextUtils.isEmpty(g.a().a("photo_stamp"))) {
            list.add(new CameraSettingItem(context.getString(R.string.setting_photo_stamp), "photo_stamp", 2));
        }
        if (TextUtils.isEmpty(g.a().a("photo_sharpness")) || !"on".equals(g.a().a("support_sharpness"))) {
            return;
        }
        list.add(new CameraSettingItem(context.getString(R.string.camera_sharpness), "photo_sharpness", 2));
    }
}
