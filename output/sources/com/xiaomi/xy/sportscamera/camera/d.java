package com.xiaomi.xy.sportscamera.camera;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.c.e;
import com.ants360.z13.util.l;
import com.ants360.z13.util.m;
import com.ants360.z13.util.q;
import com.google.android.gms.common.ConnectionResult;
import com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareModel;
import com.yiaction.common.util.Constant;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import io.realm.CameraDevice;
import io.realm.CameraDeviceType;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static d f4616a;
    private Map<String, com.xiaoyi.camera.c.d> b = new HashMap();
    private a c;

    /* loaded from: classes3.dex */
    public final class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        Context f4621a;
        String b;
        long c;
        private String e;
        private File f;
        private com.xiaoyi.camera.d.a.b g;
        private String h;

        public a(Context context, String str, File file, String str2, String str3, long j) {
            this.e = str;
            this.f = file;
            this.h = str2;
            this.f4621a = context;
            this.b = str3;
            this.c = j;
        }

        public void a() {
            if (this.g != null) {
                this.g.a();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.g = new com.xiaoyi.camera.d.a.b(this.f4621a, this.e, this.f, this.h, 1, this.c, this.b);
                this.g.a(d.this.b);
            } catch (Exception e) {
                d.this.a(this.b, (Boolean) false);
                com.xiaoyi.camera.c.d dVar = (com.xiaoyi.camera.c.d) d.this.b.get(this.b);
                if (dVar != null) {
                    dVar.a();
                }
            }
        }
    }

    private d() {
    }

    public static d a() {
        if (f4616a == null) {
            f4616a = new d();
        }
        return f4616a;
    }

    private void a(Context context, String str, File file, String str2, String str3, long j) {
        this.c = new a(context, str, file, str2, str3, j);
        com.xiaoyi.camera.module.d.a().post(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CameraDevice cameraDevice) {
        String str = CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(cameraDevice.realmGet$deviceType()) ? "Z18" : CameraDeviceType.DeviceType.ACTION_4K.toString().equals(cameraDevice.realmGet$deviceType()) ? "Z16" : CameraDeviceType.DeviceType.ACTION_J11.toString().equals(cameraDevice.realmGet$deviceType()) ? "J11" : CameraDeviceType.DeviceType.ACTION_J22.toString().equals(cameraDevice.realmGet$deviceType()) ? "J22" : "Z13";
        g.a("debug_upgrade", "auto checkout, serialNumber: " + cameraDevice.realmGet$deviceSn(), new Object[0]);
        if (q.e(CameraApplication.f1401a.i())) {
            g.a("debug_upgrade", "checkFirmwareVersion", new Object[0]);
            a(new com.xiaoyi.camera.c.c() { // from class: com.xiaomi.xy.sportscamera.camera.d.4
                @Override // com.xiaoyi.camera.c.c
                public void a() {
                    g.a("debug_upgrade", "BroadcastReceiver onNotNeedUpdate! ", new Object[0]);
                }

                @Override // com.xiaoyi.camera.c.c
                public void b() {
                    g.a("debug_upgrade", "BroadcastReceiver onNeedUpgrade! ", new Object[0]);
                    de.greenrobot.event.c.a().c(new e(true));
                }

                @Override // com.xiaoyi.camera.c.c
                public void c() {
                    g.a("debug_upgrade", "BroadcastReceiver onNeedDownload! ", new Object[0]);
                    de.greenrobot.event.c.a().c(new e(true));
                }

                @Override // com.xiaoyi.camera.c.c
                public void d() {
                    g.a("debug_upgrade", "BroadcastReceiver onNeedConnectCammera! ", new Object[0]);
                }

                @Override // com.xiaoyi.camera.c.c
                public void e() {
                }
            }, cameraDevice.realmGet$deviceSn(), cameraDevice, str);
        }
    }

    public static String f() {
        return Constant.f + "camera2" + File.separator;
    }

    public static String k(String str) {
        return i.a().a("FM_MANUAL_HW_" + str);
    }

    public static String l(String str) {
        return i.a().a("FM_MANUAL_MD5_" + str);
    }

    public static String n(String str) {
        return i.a().a("FM_MANUAL_NOTE_" + str);
    }

    public static String s(String str) {
        return "firmware_" + str + ".gzip";
    }

    public static String t(String str) {
        return Constant.f + str + File.separator;
    }

    public com.xiaoyi.camera.c.d a(String str) {
        return this.b.get(str);
    }

    public String a(CameraDevice cameraDevice) {
        String str = "Z13";
        String realmGet$deviceType = cameraDevice.realmGet$deviceType();
        if (CameraDeviceType.DeviceType.ACTION_Z13.toString().equals(realmGet$deviceType)) {
            str = "Z13";
        } else if (CameraDeviceType.DeviceType.ACTION_4K.toString().equals(realmGet$deviceType)) {
            str = "Z16";
        } else if (CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(realmGet$deviceType)) {
            str = "Z18";
        } else if (CameraDeviceType.DeviceType.ACTION_J11.toString().equals(realmGet$deviceType)) {
            str = "J11";
        } else if (CameraDeviceType.DeviceType.ACTION_J22.toString().equals(realmGet$deviceType)) {
            str = "J22";
        }
        return i.a().a("FM_DOWNLOAD_FILE_MD5" + str + r(str, cameraDevice.realmGet$deviceSn()));
    }

    public void a(final com.xiaoyi.camera.c.c cVar, final String str, CameraDevice cameraDevice, final String str2) {
        if (TextUtils.isEmpty(str)) {
            cVar.d();
        } else {
            new l().a(str2, cameraDevice.realmGet$deviceSwVersion(), cameraDevice.realmGet$deviceHwVersion(), str, new com.yiaction.common.http.g<String>() { // from class: com.xiaomi.xy.sportscamera.camera.d.2
                @Override // com.yiaction.common.http.g
                /* renamed from: a, reason: avoid collision after fix types in other method */
                public void a2(String str3) {
                    cVar.e();
                }

                @Override // com.yiaction.common.http.g
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void a(String str3) {
                    g.a("debug_upgrade", "update json: " + str3, new Object[0]);
                    try {
                        JSONObject jSONObject = new JSONObject(str3);
                        String optString = jSONObject.optString("firmwareMemo");
                        String optString2 = jSONObject.optString("firmwareCode");
                        String optString3 = jSONObject.optString("firmwareUrl");
                        String optString4 = jSONObject.optString("md5Code");
                        String optString5 = jSONObject.optString("firmwareSize");
                        String optString6 = jSONObject.optString("uploadTime");
                        String optString7 = jSONObject.optString("originMd5Code");
                        String optString8 = jSONObject.optString("hardwareCode");
                        if (TextUtils.isEmpty(optString3) || TextUtils.isEmpty(optString4)) {
                            cVar.a();
                            return;
                        }
                        if (!TextUtils.isEmpty(optString8)) {
                            for (String str4 : optString8.split(",")) {
                                d.this.a(str2, str4, optString4);
                            }
                        }
                        d.this.b(optString4, optString2);
                        d.this.a(optString4, optString);
                        d.this.d(optString4, optString3);
                        d.this.e(optString4, optString5);
                        d.this.f(optString4, optString6);
                        d.this.i(optString4, optString7);
                        d.this.j(optString4, optString8);
                        d.this.g(optString4, d.s(optString4));
                        String e = d.this.e(str);
                        g.a("debug_upgrade", "-------------------------- cameraVersion = " + e, new Object[0]);
                        if (!com.xiaoyi.camera.module.a.b(e, optString2)) {
                            cVar.a();
                        } else if (d.this.p(optString4)) {
                            cVar.b();
                        } else {
                            cVar.c();
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(String str, com.xiaoyi.camera.c.d dVar) {
        this.b.put(str, dVar);
    }

    public void a(String str, com.yiaction.common.http.g<String> gVar) {
        new l().a(str, gVar);
    }

    public void a(String str, Boolean bool) {
        i.a().a("FM_IS_DOWNLOAD_FIRMWARE" + str, bool.booleanValue());
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_UPDATE_MESSAGE" + str, str2);
    }

    public void a(String str, String str2, String str3) {
        i.a().a("FM_DOWNLOAD_FILE_MD5" + str + str2, str3);
    }

    public void a(String str, String str2, boolean z) {
        i.a().a("FM_UPGRADE_REMIND" + str + str2, z);
    }

    public boolean a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(Constant.f + str + "/");
            if (!file.exists()) {
                file.mkdirs();
            }
            String f = f(str);
            String h = h(str);
            d();
            a(str, (Boolean) true);
            g.a("debug_upgrade", "start downloadFirmware ", new Object[0]);
            a(context, f, file, h, str, Long.parseLong(g(str)));
            return true;
        } catch (Exception e) {
            g.a("debug_upgrade", "e: " + e.getMessage(), new Object[0]);
            if (!p(str)) {
                return false;
            }
            a(str, (Boolean) false);
            return true;
        }
    }

    public boolean a(Context context, String str, String str2, long j) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            File file = new File(f());
            if (!file.exists()) {
                file.mkdirs();
            }
            d();
            a(str2, (Boolean) true);
            g.a("debug_upgrade", "start downloadFirmware ", new Object[0]);
            a(context, str, file, s(str2), str2, j);
            return true;
        } catch (Exception e) {
            g.a("debug_upgrade", "e: " + e.getMessage(), new Object[0]);
            return false;
        }
    }

    public void b() {
        this.b.clear();
    }

    public void b(String str) {
        this.b.remove(str);
    }

    public void b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_UPDATE_VERSION" + str, str2);
    }

    public String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return i.a().a("FM_UPDATE_MESSAGE" + str);
    }

    public void c() {
        new l().a(new com.yiaction.common.http.g<String>() { // from class: com.xiaomi.xy.sportscamera.camera.d.1
            @Override // com.yiaction.common.http.g
            /* renamed from: a, reason: avoid collision after fix types in other method */
            public void a2(String str) {
            }

            @Override // com.yiaction.common.http.g
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(String str) {
                try {
                    List<FirmwareModel> parse = FirmwareModel.parse(str);
                    if (parse == null || parse.isEmpty()) {
                        return;
                    }
                    for (FirmwareModel firmwareModel : parse) {
                        String str2 = firmwareModel.md5Code;
                        String str3 = firmwareModel.snPrefix;
                        String str4 = firmwareModel.hardwareCode;
                        String[] split = str4.split(",");
                        if (split != null && split.length > 0) {
                            for (String str5 : split) {
                                d.this.a(str3, str5, str2);
                                d.this.b(str2, firmwareModel.firmwareCode);
                                d.this.a(str2, firmwareModel.firmwareMemo);
                                d.this.d(str2, firmwareModel.firmwareUrl);
                                d.this.e(str2, String.valueOf(firmwareModel.firmwareSize));
                                d.this.i(str2, firmwareModel.originMd5Code);
                                d.this.j(str2, str4);
                                d.this.g(str2, d.s(str2));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void c(String str, String str2) {
        i.a().a(str + "_SN_FW_VERSION", str2);
    }

    public String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return i.a().a("FM_UPDATE_VERSION" + str);
    }

    public void d() {
        if (this.c != null) {
            this.c.a();
            com.xiaoyi.camera.module.d.a().removeCallbacks(this.c);
            this.c = null;
        }
    }

    public void d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_FIRMWARE_DOWNLOAD_URL" + str, str2);
    }

    public String e(String str) {
        return i.a().a(str + "_SN_FW_VERSION");
    }

    public void e() {
        try {
            com.xiaoyi.camera.d.b bVar = new com.xiaoyi.camera.d.b();
            List<CameraDevice> a2 = bVar.a("cameraType", CameraDeviceType.CameraType.CAMERA_ACTION.toString());
            if (a2 == null || a2.isEmpty()) {
                return;
            }
            if (i.a().b("CLEAR_DEVICE", true)) {
                bVar.b();
                i.a().a("CLEAR_DEVICE", false);
                g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- delAllDeviceToRealm", new Object[0]);
                return;
            }
            g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- checkUpgrade", new Object[0]);
            bVar.a();
            Handler handler = new Handler();
            if (a2 == null || a2.isEmpty()) {
                return;
            }
            for (int i = 0; i < a2.size(); i++) {
                final CameraDevice cameraDevice = a2.get(i);
                if (!m.b().equals("cn") || !CameraDeviceType.DeviceType.ACTION_J22.toString().equals(cameraDevice.realmGet$deviceType())) {
                    handler.postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.d.3
                        @Override // java.lang.Runnable
                        public void run() {
                            d.this.b(cameraDevice);
                            g.a(BuildConfig.BUILD_TYPE, "-------------------------------------- checkFirmwareVersion", new Object[0]);
                        }
                    }, (i + 1) * ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void e(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_TOTAL_SIZE" + str, str2);
    }

    public String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return i.a().a("FM_FIRMWARE_DOWNLOAD_URL" + str);
    }

    public void f(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_UPLOAD_TIME" + str, str2);
    }

    public String g(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return i.a().a("FM_TOTAL_SIZE" + str);
    }

    public void g(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_DOWNLOAD_FILENAME" + str, str2);
    }

    public String h(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return i.a().a("FM_DOWNLOAD_FILENAME" + str);
    }

    public String h(String str, String str2) {
        return i.a().a("FM_DOWNLOAD_FILE_MD5" + str + r(str, str2));
    }

    public String i(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return i.a().a("FM_HARDWARE_CODE" + str);
    }

    public void i(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_DOWNLOAD_FILE_ORIGIN_MD5" + str, str2);
    }

    public Boolean j(String str) {
        return Boolean.valueOf(i.a().b("FM_IS_DOWNLOAD_FIRMWARE" + str, false));
    }

    public void j(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a().a("FM_HARDWARE_CODE" + str, str2);
    }

    public void k(String str, String str2) {
        i.a().a("FM_MANUAL_HW_" + str, str2);
    }

    public void l(String str, String str2) {
        i.a().a("FM_MANUAL_MD5_" + str, str2);
    }

    public String m(String str) {
        return i.a().a("FM_MANUAL_VERSION_" + str);
    }

    public void m(String str, String str2) {
        i.a().a("FM_MANUAL_ORGMD5_" + str, str2);
    }

    public void n(String str, String str2) {
        i.a().a("FM_MANUAL_VERSION_" + str, str2);
    }

    public void o(String str, String str2) {
        i.a().a("FM_MANUAL_NOTE_" + str, str2);
    }

    public boolean o(String str) {
        try {
            String str2 = str.split("_")[1];
            if (str2.contains(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            }
            String[] split = str2.split(HelpFormatter.DEFAULT_OPT_PREFIX)[0].split("\\.");
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            int parseInt3 = Integer.parseInt(split[2]);
            if (parseInt > 1) {
                return false;
            }
            if (parseInt != 1) {
                return true;
            }
            if (parseInt2 <= 2) {
                return parseInt2 != 2 || parseInt3 <= 0;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean p(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && q(h(str, str2))) {
            return true;
        }
        return false;
    }

    public boolean p(String str) {
        return q(str) || r(str);
    }

    public Boolean q(String str, String str2) {
        boolean z;
        String k = k(str);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(k) && !TextUtils.isEmpty(str2)) {
            String substring = ("Z18".equals(com.xiaoyi.camera.g.a().a("model")) || "Z16".equals(com.xiaoyi.camera.g.a().a("model")) || "J11".equals(com.xiaoyi.camera.g.a().a("model")) || "J22".equals(com.xiaoyi.camera.g.a().a("model"))) ? str2.substring(4, 7) : str2.substring(1, 4);
            String[] split = k.split(HelpFormatter.DEFAULT_OPT_PREFIX);
            for (String str3 : split) {
                if (substring.equals(str3) && r(l(str))) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }

    public boolean q(String str) {
        File file;
        if (TextUtils.isEmpty(str) || (file = new File(t(str), s(str))) == null || !file.exists()) {
            return false;
        }
        String a2 = com.yiaction.common.util.e.a(file);
        if (str.equals(a2)) {
            return true;
        }
        g.a("debug_upgrade", "checkMD5_1 check failur! originMD5: " + a2 + ",DownloadFileMd5: " + str, new Object[0]);
        return false;
    }

    public String r(String str, String str2) {
        String str3 = null;
        try {
            if ("Z18".equals(str) || "Z16".equals(str) || "J11".equals(str) || "J22".equals(str)) {
                if (!TextUtils.isEmpty(str2) && str2.length() >= 7) {
                    str3 = str2.substring(4, 7);
                }
            } else if (!TextUtils.isEmpty(str2) && str2.length() >= 4) {
                str3 = str2.substring(1, 4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    public boolean r(String str) {
        File file;
        if (TextUtils.isEmpty(str) || (file = new File(f(), s(str))) == null || !file.exists()) {
            return false;
        }
        String a2 = com.yiaction.common.util.e.a(file);
        if (str.equals(a2)) {
            return true;
        }
        g.a("debug_upgrade", "checkMD5_2 check failur! originMD5: " + a2 + ",DownloadFileMd5: " + str, new Object[0]);
        return false;
    }
}
