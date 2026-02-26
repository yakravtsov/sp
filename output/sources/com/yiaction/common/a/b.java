package com.yiaction.common.a;

import java.util.Calendar;

/* loaded from: classes3.dex */
public class b {
    public static String h = "v3.2";
    public static String i = "snsapi.xiaoyi.com";
    public static String j = "snsapitest.xiaoyi.com";
    public static String k = "139.196.235.42:8032";
    public static String l = "120.55.161.71:8321";
    public static String m = "http://" + j;
    public static String n = "snsapiustest.xiaoyi.com";
    public static String o = "snsapius321.mi-ae.com";
    public static String p = "snsapius.xiaoyi.com";
    public static String q = "http://" + n;
    public static String r = "https://api.weibo.com/2/proxy/live/show";
    public static String s = "/stickies/video/list";
    public static String t = "/stickies/video/category";
    public static String u = "/music/category";
    public static String v = "/music/list";
    public static String w = "/media/url";
    public static String x = "/live/users";
    public static String y = "/live/comment/list";
    public static String z = "/live/media/info";
    public static String A = "/live/thumbnail/url";
    public static String B = "/live/media/release";
    public static String C = "/live/online/info";
    public static String D = "/live/media/list";
    public static String E = "/live/user/list";
    public static String F = "http://livevideo.xiaoyi.com/fetchhls";
    public static String G = "http://live.us.xiaoyi.com/fetchhls";
    public static String H = "/live/comment";
    public static String I = "/live/like";
    public static String J = "https://api.weibo.com/2/proxy/live/update";
    public static String K = "https://api.weibo.com/2/proxy/live/show";
    public static String L = "https://api.weibo.com/2/proxy/live/delete";
    public static String M = "/live/status";
    public static String N = "/copywriter/writer";
    public static String O = "http://upload.iqiyi.com/uploadfinish";
    public static String P = "http://openapi.iqiyi.com/api/file/info";
    public static String Q = "/user/%s/follow";
    public static int R = 50104;
    public static int S = 50105;

    public static String a(String str, String str2) {
        return "http://openapi.iqiyi.com/api/file/fullStatus?access_token=" + str + "&file_id=" + str2;
    }

    public static boolean a(int i2) {
        return i2 == R || i2 == S;
    }

    public static String b() {
        switch (Calendar.getInstance().get(7)) {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
            default:
                return "";
        }
    }

    public static String b(String str) {
        return com.yiaction.common.a.c ? m + str : q + str;
    }

    public static String b(String str, String str2) {
        return com.yiaction.common.a.c ? m + str + "?" + str2 : q + str + "?" + str2;
    }
}
