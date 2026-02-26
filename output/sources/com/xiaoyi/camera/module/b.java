package com.xiaoyi.camera.module;

import com.ants360.z13.club.ClubModel;
import com.xiaoyi.camera.controller.CameraMainController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, List<String>> f4877a = new HashMap();

    public static ArrayList<String> a(String str) {
        List<String> list = f4877a.get(str);
        return list == null ? new ArrayList<>() : new ArrayList<>(list);
    }

    public static void a() {
        f4877a.clear();
    }

    public static void a(String str, List<String> list) {
        f4877a.put(str, list);
    }

    public static void a(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        if ("photo_size".equals(str)) {
            a(jSONArray);
            return;
        }
        if ("video_resolution".equals(str) || "video_loop_resolution".equals(str) || "timelapse_video_resolution".equals(str) || "video_photo_resolution".equals(str)) {
            b(str, jSONArray);
            return;
        }
        if ("video_rotate".equals(str)) {
            b(jSONArray);
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optString(i));
            }
            if (arrayList.isEmpty()) {
                return;
            }
            a(str, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(List<String> list) {
        int size = list.size();
        for (int i = 1; i < size; i++) {
            String str = list.get(i);
            int intValue = Integer.valueOf(str.substring(0, str.indexOf("x"))).intValue();
            int i2 = i;
            while (i2 > 0 && intValue >= Integer.valueOf(list.get(i2 - 1).substring(0, list.get(i2 - 1).indexOf("x"))).intValue()) {
                list.set(i2, list.get(i2 - 1));
                i2--;
            }
            list.set(i2, str);
        }
    }

    public static void a(List<String> list, String str) {
        int size = list.size();
        for (int i = 1; i < size; i++) {
            String str2 = list.get(i);
            int intValue = Integer.valueOf(str2.substring(0, str2.indexOf(str))).intValue();
            int i2 = i;
            while (i2 > 0 && intValue >= Integer.valueOf(list.get(i2 - 1).substring(0, list.get(i2 - 1).indexOf(str))).intValue()) {
                list.set(i2, list.get(i2 - 1));
                i2--;
            }
            list.set(i2, str2);
        }
    }

    public static void a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                String optString = jSONArray.optString(i);
                if (optString.contains("fov")) {
                    optString = optString.replace("fov:", "");
                }
                arrayList.add(optString);
            }
            if (arrayList.isEmpty()) {
                return;
            }
            a(arrayList, "M");
            a("photo_size", arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b() {
        a("video_quality", (List<String>) Arrays.asList("S.Fine", "Fine", "Normal"));
        a("video_standard", (List<String>) Arrays.asList("NTSC", "PAL"));
        a("capture_default_mode", (List<String>) Arrays.asList("precise quality", "precise self quality", "precise quality cont.", "burst quality"));
        a("timelapse_video", (List<String>) Arrays.asList("0.5", ClubModel.beCharge, ClubModel.beAdmin, "5", "10", "30", "60"));
        a("timelapse_video_duration", (List<String>) Arrays.asList("off", "6s", "8s", "10s", "20s", "30s", "60s", "120s"));
        a("long_shutter_define", (List<String>) Arrays.asList("power_off", "switch_mode"));
        a("slow_motion_rate", (List<String>) Arrays.asList(ClubModel.beAdmin, "4", "8"));
    }

    public static void b(String str) {
        f4877a.remove(str);
    }

    public static void b(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                String optString = jSONArray.optString(i);
                if (optString.contains(CameraMainController.f4814a)) {
                    optString = optString.replace(CameraMainController.f4814a, CameraMainController.d);
                }
                arrayList.add(optString);
            }
            if (arrayList.isEmpty()) {
                return;
            }
            a(arrayList);
            a(str, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 2) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.optString(i));
                    }
                    if (arrayList.isEmpty()) {
                        a("video_rotate", (List<String>) Arrays.asList("on", "off"));
                        return;
                    } else {
                        a("video_rotate", arrayList);
                        return;
                    }
                }
            } catch (Exception e) {
                a("video_rotate", (List<String>) Arrays.asList("on", "off"));
                return;
            }
        }
        a("video_rotate", (List<String>) Arrays.asList("on", "off"));
    }
}
