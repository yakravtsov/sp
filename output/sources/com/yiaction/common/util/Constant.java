package com.yiaction.common.util;

import android.os.Environment;

/* loaded from: classes.dex */
public class Constant {
    private static String w = Environment.getExternalStorageDirectory().getAbsolutePath();

    /* renamed from: a, reason: collision with root package name */
    public static String f4960a = "/DCIM/SportsCamera/";
    public static String b = w + f4960a;
    public static String c = w + "/SportsCamera/";
    public static String d = c + "Cache/";
    public static String e = "/SportsCamera/firmware/";
    public static final String f = w + e;
    public static String g = c + "veengine/";
    public static String h = g + "theme/";
    public static String i = g + "spk/";
    public static String j = g + "thumbnail/";
    public static String k = g + "temp/";
    public static String l = g + "movies/";
    public static String m = g + "music/";
    public static String n = "/data/data/com.ants360.quickphoto/lib/";
    public static String o = "/data/data/com.ants360.quickphoto/libcopy/";
    public static String p = c + "/log/";
    public static String q = p + "firmware.log";
    public static String r = p + "app.log";
    public static String s = "/SportsCamera/download";
    public static String t = "Z221503A3951513";
    public static String u = "YiGlideCache";
    public static int v = 6;

    /* loaded from: classes3.dex */
    public enum CaptureMode {
        NORMAL("precise quality"),
        BURST("burst quality"),
        TIMELAPES("precise quality cont."),
        TIMER("precise self quality");

        private String option;
        private String value;

        CaptureMode(String str) {
            this.value = str;
        }

        public String getOption() {
            return this.option;
        }

        public void setOption(String str) {
            this.option = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    /* loaded from: classes3.dex */
    public enum RecordMode {
        NORMAL("record"),
        TIMELAPES("record_timelapse"),
        PHOTO("record_photo"),
        LOOP("record_loop"),
        SLOW("record_slow_motion");

        private String option;
        private String value;

        RecordMode(String str) {
            this.value = str;
        }

        public String getOption() {
            return this.option;
        }

        public void setOption(String str) {
            this.option = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }
}
