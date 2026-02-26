package com.xiaoyi.camera;

import android.text.TextUtils;
import com.yiaction.common.util.Constant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public static String[] f4859a = {"30 p/6s", "30 p/3s", "30 p/2s", "30 p/s", "10 p/3s", "10 p/2s", "10 p/s", "5 p/s", "3 p/s"};
    public static String[] b = {"30 p / 6s", "30 p / 3s", "30 p / 2s", "30 p / s", "10 p / 3s", "10 p / 2s", "10 p / s", "5 p / s", "3 p / s"};
    public static final String[] c = {"1920x1080 48P 16:9", "1280x960 48P 4:3"};
    public static final String[] d = {"1920x1080 60P 16:9", "1280x960 60P 4:3"};

    public static List<String[]> a() {
        g a2 = g.a();
        ArrayList arrayList = new ArrayList();
        if ("J22".equals(a2.a("model"))) {
            for (String str : com.xiaoyi.camera.module.b.a("system_video_mode")) {
                if (Constant.RecordMode.NORMAL.toString().equals(str)) {
                    arrayList.add(new String[0]);
                }
                if (Constant.RecordMode.TIMELAPES.toString().equals(str)) {
                    arrayList.add(a(a2.a("timelapse_video"), a2.a("timelapse_video_duration")));
                }
                if (Constant.RecordMode.SLOW.toString().equals(str)) {
                    arrayList.add(e(a2.a("slow_motion_rate")));
                }
                if (Constant.RecordMode.PHOTO.toString().equals(str)) {
                    arrayList.add(g(a2.a("record_photo_time")));
                }
                if (Constant.RecordMode.LOOP.toString().equals(str)) {
                    arrayList.add(f(a2.a("loop_rec_duration")));
                }
            }
        } else {
            arrayList.add(new String[0]);
            arrayList.add(a(a2.a("timelapse_video"), a2.a("timelapse_video_duration")));
            if ("Z16".equals(a2.a("model")) || "Z18".equals(a2.a("model")) || "J11".equals(a2.a("model")) || "J22".equals(a2.a("model"))) {
                arrayList.add(e(a2.a("slow_motion_rate")));
                arrayList.add(f(a2.a("loop_rec_duration")));
                arrayList.add(g(a2.a("record_photo_time")));
            }
        }
        return arrayList;
    }

    public static String[] a(String str) {
        return str.replace("s", " sec").split(" ");
    }

    public static String[] a(String str, String str2) {
        return new String[]{(str + " sec").split(" ")[0] + "/" + str2.replace("s", ""), "INT/LEN"};
    }

    public static List<String[]> b() {
        g a2 = g.a();
        ArrayList arrayList = new ArrayList();
        if ("J22".equals(g.a().a("model"))) {
            for (String str : com.xiaoyi.camera.module.b.a("system_photo_mode")) {
                if (Constant.CaptureMode.NORMAL.toString().equals(str)) {
                    arrayList.add(new String[0]);
                }
                if (Constant.CaptureMode.TIMER.toString().equals(str)) {
                    arrayList.add(a(a2.a("precise_selftime")));
                }
                if (Constant.CaptureMode.TIMELAPES.toString().equals(str)) {
                    arrayList.add(b(a2.a("precise_cont_time")));
                }
                if (Constant.CaptureMode.BURST.toString().equals(str)) {
                    arrayList.add(c(a2.a("burst_capture_number")));
                }
            }
        } else {
            arrayList.add(new String[0]);
            arrayList.add(a(a2.a("precise_selftime")));
            arrayList.add(c(a2.a("burst_capture_number")));
            arrayList.add(b(a2.a("precise_cont_time")));
        }
        return arrayList;
    }

    public static String[] b(String str) {
        return str.replace(".0", "").split(" ");
    }

    public static void c() {
        ArrayList<String> a2 = com.xiaoyi.camera.module.b.a("burst_capture_number");
        if (a2 == null || a2.size() <= 0) {
            g.a().g();
        }
        b = new String[a2.size()];
        a2.toArray(b);
        int size = a2.size();
        f4859a = new String[size];
        for (int i = 0; i < size; i++) {
            f4859a[i] = b[i].replace(" ", "");
        }
    }

    public static String[] c(String str) {
        String[] split = str.split(" ");
        return (split == null || split.length != 4) ? new String[0] : new String[]{split[0], split[1] + split[2] + split[3]};
    }

    public static String d(String str) {
        String[] split = str.split(" ");
        return (split == null || split.length != 4) ? str : split[0] + " " + split[1] + split[2] + split[3];
    }

    public static void d() {
        ArrayList<String> a2 = com.xiaoyi.camera.module.b.a("burst_capture_number");
        if (a2 == null || a2.size() <= 0) {
            g.a().g();
        }
        b = new String[a2.size()];
        a2.toArray(b);
        int size = a2.size();
        f4859a = new String[size];
        for (int i = 0; i < size; i++) {
            f4859a[i] = d(b[i]);
        }
    }

    public static String[] e(String str) {
        String str2;
        if (TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
            str2 = str + " rate";
        } else {
            try {
                String[] split = g.a().a("slow_motion_res").split("x");
                str2 = split[1] + "P " + split[2] + "x";
            } catch (Exception e) {
                str2 = str + " rate";
            }
        }
        return str2.split(" ");
    }

    public static String[] f(String str) {
        return str.replace("minutes", "min").split(" ");
    }

    public static String[] g(String str) {
        return (str + " sec").split(" ");
    }
}
