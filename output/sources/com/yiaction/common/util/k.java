package com.yiaction.common.util;

import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes3.dex */
public class k {
    public static String a(String str, String str2) {
        int i = 0;
        if (str != null && str.length() > 0) {
            i = Integer.valueOf(str.trim()).intValue();
        }
        return Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage()) ? i < 2 ? str + " " + str2 : str + " " + str2 + "s" : str + str2;
    }

    public static String a(String str, String str2, int i) {
        return i > 1 ? str.replace(str2, str2 + "s") : str;
    }

    public static String a(String str, String str2, boolean z) {
        int i = 0;
        if (str != null && str.length() > 0) {
            i = Integer.valueOf(str.trim()).intValue();
        }
        if (!Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            return z ? str + str2 : str2;
        }
        if (i < 2) {
            return z ? str + str2 : str2;
        }
        return z ? str + str2 + "s" : str2 + "s";
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.length() == str.getBytes().length;
    }
}
