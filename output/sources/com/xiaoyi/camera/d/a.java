package com.xiaoyi.camera.d;

/* loaded from: classes3.dex */
public class a {
    public static boolean a() {
        if (com.xiaoyi.camera.g.a() != null) {
            if (com.xiaoyi.camera.g.a().a("model").equals("J11")) {
                return true;
            }
            String a2 = com.xiaoyi.camera.g.a().a("dev_functions");
            if (!a2.equals("")) {
                try {
                    if ((Integer.valueOf(a2).intValue() & 1) == 1) {
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean b() {
        if (com.xiaoyi.camera.g.a() == null) {
            return false;
        }
        String a2 = com.xiaoyi.camera.g.a().a("dev_functions");
        if (a2.equals("")) {
            return false;
        }
        try {
            return (Integer.valueOf(a2).intValue() & 4) == 4;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean c() {
        if (com.xiaoyi.camera.g.a() == null) {
            return false;
        }
        String a2 = com.xiaoyi.camera.g.a().a("dev_functions");
        if (a2.equals("")) {
            return false;
        }
        try {
            return (Integer.valueOf(a2).intValue() & 512) == 512;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean d() {
        if (com.xiaoyi.camera.g.a() == null) {
            return false;
        }
        String a2 = com.xiaoyi.camera.g.a().a("dev_functions");
        if (a2.equals("")) {
            return false;
        }
        try {
            return (Integer.valueOf(a2).intValue() & 4096) == 4096;
        } catch (Exception e) {
            return false;
        }
    }
}
