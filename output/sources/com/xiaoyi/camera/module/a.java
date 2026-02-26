package com.xiaoyi.camera.module;

import android.text.TextUtils;
import org.apache.commons.cli.HelpFormatter;

/* loaded from: classes3.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static String f4876a;
    public static String b;

    public static void a(String str, String str2) {
        f4876a = str;
        b = str2;
    }

    public static boolean b(String str, String str2) {
        boolean d = d(str, str2);
        if (d) {
        }
        return d;
    }

    public static boolean c(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) {
                return true;
            }
            String str3 = str.split("_")[1];
            boolean z = str3.contains(HelpFormatter.DEFAULT_OPT_PREFIX);
            String str4 = str3.split(HelpFormatter.DEFAULT_OPT_PREFIX)[0];
            if (z) {
                return false;
            }
            return str4.equals(str2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean d(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) {
                return false;
            }
            String str3 = str.split("_")[1];
            boolean z = str3.contains(HelpFormatter.DEFAULT_OPT_PREFIX);
            String[] split = str3.split(HelpFormatter.DEFAULT_OPT_PREFIX)[0].split("\\.");
            int intValue = Integer.valueOf(split[0]).intValue();
            int intValue2 = Integer.valueOf(split[1]).intValue();
            int intValue3 = Integer.valueOf(split[2]).intValue();
            String[] split2 = str2.split("\\.");
            int intValue4 = Integer.valueOf(split2[0]).intValue();
            int intValue5 = Integer.valueOf(split2[1]).intValue();
            int intValue6 = Integer.valueOf(split2[2]).intValue();
            if (intValue > intValue4) {
                return false;
            }
            if (intValue != intValue4) {
                return true;
            }
            if (intValue2 > intValue5) {
                return false;
            }
            if (intValue2 != intValue5) {
                return true;
            }
            if (intValue3 <= intValue6) {
                return intValue3 != intValue6 || z;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
