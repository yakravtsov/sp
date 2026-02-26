package com.yiaction.common.a;

import android.os.Build;
import com.yiaction.common.util.l;
import java.util.Locale;

/* loaded from: classes.dex */
public class d {
    public static final String f = "http://v.xiaoyi.com/?id=###&project=sports&dn=" + l.a(Build.MODEL);
    public static final String g = "http://v.us.xiaoyi.com/?id=###&project=sports&dn=" + l.a(Build.MODEL);

    public static String a() {
        return Locale.SIMPLIFIED_CHINESE.getLanguage().equals(Locale.getDefault().getLanguage()) ? "http://bbs.xiaoyi.com/forum.php?mod=forumdisplay&fid=2" : "http://forum.xiaoyi.com/forum.php?mod=forumdisplay&fid=2";
    }

    public static String d(String str) {
        return "http://" + str + "/firmware/download";
    }
}
