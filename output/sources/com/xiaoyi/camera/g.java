package com.xiaoyi.camera;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.InputDeviceCompat;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xiaoyi.camera.controller.CameraMainController;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.i;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import wseemann.media.BuildConfig;
import wseemann.media.FFmpegMediaMetadataRetriever;

/* loaded from: classes3.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static Context f4861a;
    private static g b;
    private ConcurrentHashMap<String, String> d = new ConcurrentHashMap<>();
    private com.xiaoyi.camera.c.b f = new com.xiaoyi.camera.c.b() { // from class: com.xiaoyi.camera.g.1
        @Override // com.xiaoyi.camera.c.b
        public void a(JSONObject jSONObject) {
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("param");
            Intent intent = new Intent();
            intent.setAction(a.a(optString));
            intent.putExtra("param", optString2);
            com.yiaction.common.util.g.a("debug_cmd", "Notification:json=" + jSONObject.toString(), new Object[0]);
            try {
                if ("start_photo_capture".equals(optString)) {
                    String str = optString2.split(";")[0];
                    String str2 = optString2.split(";")[1];
                    if (Constant.CaptureMode.NORMAL.toString().equals(str)) {
                        if ("off".equals(str2)) {
                            intent.putExtra("current_operation_model", 1);
                            g.this.c.a("current_operation_model", 1);
                        } else {
                            intent.putExtra("current_operation_model", 4);
                            g.this.c.a("current_operation_model", 4);
                        }
                    } else if (Constant.CaptureMode.BURST.toString().equals(str)) {
                        intent.putExtra("current_operation_model", 2);
                        g.this.c.a("current_operation_model", 2);
                    } else if (Constant.CaptureMode.TIMELAPES.toString().equals(str)) {
                        intent.putExtra("current_operation_model", 3);
                        g.this.a("precise_cont_capturing", "on");
                        g.this.c.a("current_operation_model", 3);
                    } else if (Constant.CaptureMode.TIMER.toString().equals(str)) {
                        intent.putExtra("current_operation_model", 4);
                        g.this.a("precise_self_running", "on");
                        g.this.c.a("current_operation_model", 4);
                    } else {
                        intent.putExtra("current_operation_model", -1);
                        g.this.c.a("current_operation_model", -1);
                    }
                } else if ("photo_taken".equals(optString)) {
                    if (Constant.CaptureMode.TIMER.toString().equals(g.this.a("capture_mode"))) {
                        g.this.a("precise_self_running", "off");
                    }
                    g.this.c.a("current_operation_model", -1);
                } else if ("self_capture_stop".equals(optString)) {
                    intent.putExtra("current_operation_model", -1);
                    g.this.c.a("current_operation_model", -1);
                } else if ("precise_cont_complete".equals(optString)) {
                    g.this.a("precise_cont_capturing", "off");
                    intent.putExtra("current_operation_model", optString2);
                    g.this.c.a("current_operation_model", -1);
                } else if ("burst_complete".equals(optString)) {
                    g.this.c.a("current_operation_model", -1);
                } else if ("video_record_complete".equals(optString)) {
                    if ("Z16".equals(g.this.a("model")) || "Z18".equals(g.this.a("model")) || "J11".equals(g.this.a("model")) || "J22".equals(g.this.a("model"))) {
                        intent.putExtra("VIDEO_RECORD_COMPLETE_JSON", String.valueOf(jSONObject));
                    }
                    g.this.c.a("current_operation_model", -1);
                } else if ("start_video_record".equals(optString)) {
                    intent.putExtra("current_operation_model", 0);
                    g.this.c.a("current_operation_model", 0);
                } else if ("sd_card_status".equals(optString)) {
                    intent.putExtra("current_operation_model", optString2);
                    if (ProductAction.ACTION_REMOVE.equals(optString2)) {
                        g.this.a("sd_card_status", ProductAction.ACTION_REMOVE);
                    } else if ("insert".equals(optString2)) {
                        g.this.a("sd_card_status", "insert");
                    }
                } else if ("switch_to_cap_mode".equals(optString)) {
                    g.this.a("system_mode", optString2);
                } else if ("switch_to_rec_mode".equals(optString)) {
                    g.this.a("system_mode", optString2);
                } else if ("setting_changed".equals(optString)) {
                    String optString3 = jSONObject.optString(FirebaseAnalytics.Param.VALUE);
                    intent.putExtra(FirebaseAnalytics.Param.VALUE, optString3);
                    g.this.a(optString2, optString3, jSONObject.optJSONArray("related"));
                } else if ("enter_album".equals(optString) || "exit_album".equals(optString)) {
                    g.this.a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM, optString);
                }
                g.f4861a.sendBroadcast(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private i c = i.a();
    private b e = new b(this.f);

    private g() {
    }

    public static g a() {
        if (b == null) {
            b = new g();
        }
        return b;
    }

    public static g a(Context context) {
        f4861a = context;
        return a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, JSONArray jSONArray) {
        a(str, str2);
        if (str.equals("rec_mode")) {
            a("system_mode", CameraMainController.CameraMode.RecordMode.toString());
        } else if (str.equals("capture_mode")) {
            a("system_mode", CameraMainController.CameraMode.CaptureMode.toString());
        }
        if ((!"Z16".equals(a().a("model")) && !"Z18".equals(a().a("model")) && !"J11".equals(a().a("model")) && !"J22".equals(a().a("model"))) || jSONArray == null || jSONArray.length() <= 0) {
            b();
            return;
        }
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "-------------- 有联动项", new Object[0]);
        a(jSONArray);
        de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.d(true));
    }

    public static synchronized void a(JSONArray jSONArray) {
        synchronized (g.class) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < jSONArray.length()) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    Iterator<String> keys = optJSONObject.keys();
                    if (keys.hasNext()) {
                        String next = keys.next();
                        b.b(next, optJSONObject.optString(next));
                    }
                    i = i2 + 1;
                }
            }
        }
    }

    private void b(String str, String str2) {
        if (!"enter_album".equals(a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM)) || "exit_album".equals(str2)) {
            this.d.put(str, str2);
            if (this.c != null) {
                this.c.a(str, str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        ArrayList<String> a2 = com.xiaoyi.camera.module.b.a("system_default_mode");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it2 = a2.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (Constant.CaptureMode.NORMAL.toString().equals(next)) {
                arrayList.add(next);
            } else if (Constant.CaptureMode.TIMER.toString().equals(next)) {
                arrayList.add(next);
            } else if (Constant.CaptureMode.TIMELAPES.toString().equals(next)) {
                arrayList.add(next);
            } else if (Constant.CaptureMode.BURST.toString().equals(next)) {
                arrayList.add(next);
            } else if (Constant.RecordMode.NORMAL.toString().equals(next)) {
                arrayList2.add(next);
            } else if (Constant.RecordMode.TIMELAPES.toString().equals(next)) {
                arrayList2.add(next);
            } else if (Constant.RecordMode.SLOW.toString().equals(next)) {
                arrayList2.add(next);
            } else if (Constant.RecordMode.PHOTO.toString().equals(next)) {
                arrayList2.add(next);
            } else if (Constant.RecordMode.LOOP.toString().equals(next)) {
                arrayList2.add(next);
            }
        }
        com.xiaoyi.camera.module.b.a("system_photo_mode", arrayList);
        com.xiaoyi.camera.module.b.a("system_video_mode", arrayList2);
    }

    public String a(String str) {
        String str2 = this.d.get(str);
        return str2 == null ? "" : str2;
    }

    public void a(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(InputDeviceCompat.SOURCE_KEYBOARD, aVar);
        dVar.a("param", 0);
        dVar.a("heartbeat", 1);
        this.e.b();
        this.e.b(dVar);
        com.yiaction.common.util.g.a("debug_wifi", "sent AmbaCommand.AMBA_START_SESSION", new Object[0]);
    }

    public void a(File file, String str, String str2, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(1286, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.a("md5sum", str2);
        dVar.a("param", str);
        dVar.a("size", Long.valueOf(file.length()));
        dVar.a("offset", 0);
        this.e.b(dVar);
    }

    public void a(String str, int i, int i2, com.xiaoyi.camera.c.a aVar) {
        com.yiaction.common.util.g.a("debug_file", "send list command: 1282 with option: " + str, new Object[0]);
        d dVar = new d(1282, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.a("param", str);
        dVar.a("from", Integer.valueOf(i));
        dVar.a("to", Integer.valueOf(i2));
        this.e.b(dVar);
    }

    public void a(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(5, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("type", str);
            this.e.b(dVar);
        }
    }

    public void a(String str, String str2) {
        if (!"enter_album".equals(a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM)) || "exit_album".equals(str2)) {
            this.d.put(str, str2);
            if (this.c != null) {
                this.c.a(str, str2);
            }
        }
    }

    public void a(String str, String str2, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(2, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.a("type", str);
        dVar.a("param", str2);
        this.e.b(dVar);
    }

    public void b() {
        com.xiaoyi.camera.module.d.a(new Runnable() { // from class: com.xiaoyi.camera.g.5
            @Override // java.lang.Runnable
            public void run() {
                g.this.i(new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.5.1
                    @Override // com.xiaoyi.camera.c.a
                    public void a(d dVar, JSONObject jSONObject) {
                        com.yiaction.common.util.g.a("debug_pal", "re init all current setting", new Object[0]);
                        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------------------getAllCurrentSetting", new Object[0]);
                        g.a(jSONObject.optJSONArray("param"));
                        de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.d(true));
                    }

                    @Override // com.xiaoyi.camera.c.a
                    public void b(d dVar, JSONObject jSONObject) {
                        de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.d(false));
                    }
                });
            }
        });
    }

    public void b(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(4, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", "D:");
            this.e.b(dVar);
        }
    }

    public void b(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(9, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", str);
            this.e.b(dVar);
        }
    }

    public void b(String str, String str2, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(2, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.a("param", str2);
        dVar.a("type", str);
        this.e.b(dVar);
    }

    public void c() {
        if (this.e != null) {
            this.e.c();
            this.e = null;
        }
        f4861a = null;
        b = null;
        this.d.clear();
        com.xiaoyi.camera.module.b.a();
    }

    public void c(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(InputDeviceCompat.SOURCE_DPAD, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void c(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777220, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", str);
            this.e.b(dVar);
        }
    }

    public void c(String str, String str2, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(InputDeviceCompat.SOURCE_GAMEPAD, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.a("type", str);
        dVar.a("param", str2);
        this.e.b(dVar);
    }

    public void d() {
        a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM, "exit_album");
        d dVar = new d(258, null);
        this.e.a();
        this.e.a(dVar);
    }

    public void d(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(514, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void d(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(1, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("type", str);
            this.e.b(dVar);
        }
    }

    public void d(String str, String str2, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(1026, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.f4839a = str;
        dVar.a("param", str2);
        this.e.b(dVar);
    }

    public void e() {
        if (this.e != null) {
            this.e.d();
        }
    }

    public void e(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(515, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void e(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777228, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", str);
            this.e.b(dVar);
        }
    }

    public void e(String str, String str2, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(2, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.a("type", "wifi_update");
        dVar.a("param", "wifi_ssid:" + str + ",wifi_password:" + str2 + ",reboot:1");
        this.e.b(dVar);
    }

    public Map<String, String> f() {
        return this.d;
    }

    public void f(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777241, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void f(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(1281, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", str);
            this.e.b(dVar);
        }
    }

    public void g() {
        b("burst_capture_number", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.6
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("burst_capture_number", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void g(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(770, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void g(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(1283, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", str);
            this.e.b(dVar);
        }
    }

    public void h() {
        b("record_photo_time", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.7
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("record_photo_time", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void h(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(1282, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void h(String str, com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777219, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", str);
            this.e.b(dVar);
        }
    }

    public void i() {
        b("loop_rec_duration", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.8
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("loop_rec_duration", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void i(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(3, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void j() {
        b("precise_cont_time", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.9
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("precise_cont_time", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void j(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(13, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void k() {
        b("precise_selftime", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.10
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("precise_selftime", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void k(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(260, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void l() {
        b("slow_motion_rate", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.11
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("slow_motion_rate", jSONObject.optJSONArray("options"));
                de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.d(true));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void l(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(259, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
        } else {
            dVar.a("param", "none_force");
            this.e.b(dVar);
        }
    }

    public void m() {
        b("slow_motion_res", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.12
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("slow_motion_res", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void m(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(261, aVar);
        if (this.e == null && aVar != null) {
            aVar.b(dVar, null);
            return;
        }
        dVar.a("type", "TCP");
        dVar.a("param", com.xiaoyi.camera.d.e.g(f4861a));
        this.e.b(dVar);
    }

    public void n() {
        b("timelapse_video", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.2
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("timelapse_video", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void n(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777224, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void o() {
        b("timelapse_video_duration", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.3
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("timelapse_video_duration", jSONObject.optJSONArray("options"));
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void o(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777227, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void p() {
        b("system_default_mode", new com.xiaoyi.camera.c.a() { // from class: com.xiaoyi.camera.g.4
            @Override // com.xiaoyi.camera.c.a
            public void a(d dVar, JSONObject jSONObject) {
                com.xiaoyi.camera.module.b.a("system_default_mode", jSONObject.optJSONArray("options"));
                g.this.r();
            }

            @Override // com.xiaoyi.camera.c.a
            public void b(d dVar, JSONObject jSONObject) {
            }
        });
    }

    public void p(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777229, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void q(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777230, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void r(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(InputDeviceCompat.SOURCE_JOYSTICK, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void s(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777238, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void t(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777242, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }

    public void u(com.xiaoyi.camera.c.a aVar) {
        d dVar = new d(16777243, aVar);
        if (this.e != null || aVar == null) {
            this.e.b(dVar);
        } else {
            aVar.b(dVar, null);
        }
    }
}
