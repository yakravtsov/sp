package com.yiaction.common.util;

import android.util.Log;

/* loaded from: classes.dex */
public class g {
    private static String a() {
        String className = new Exception().getStackTrace()[2].getClassName();
        return className.substring(className.lastIndexOf(".") + 1, className.length());
    }

    public static void a(String str, String str2, Object... objArr) {
        if (com.yiaction.common.a.f4926a) {
            String format = objArr.length > 0 ? String.format(str2, objArr) : str2;
            if (format == null) {
                format = "";
            }
            Log.d(str, format);
        }
    }

    public static void a(String str, Object... objArr) {
        if (com.yiaction.common.a.f4926a) {
            a(a(), str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        if (com.yiaction.common.a.f4926a) {
            if (objArr.length > 0) {
                str = String.format(str, objArr);
            }
            String a2 = a();
            if (str == null) {
                str = "";
            }
            Log.v(a2, str);
        }
    }
}
