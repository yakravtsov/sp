package com.yiaction.common.ffmpeg;

import android.util.Log;

/* loaded from: classes3.dex */
public class CommandParse {
    static {
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("swscale");
        System.loadLibrary("avfilter");
        System.loadLibrary("avdevice");
        System.loadLibrary("avcommand");
    }

    public int a(String str) {
        if (str.isEmpty()) {
            return 1;
        }
        String[] split = str.split("(?<!\\\\)\\s");
        for (int i = 0; i < split.length; i++) {
            Log.d("ffmpeg-jni", split[i]);
            split[i] = split[i].replaceAll("\\\\\\s", " ");
            Log.d("ffmpeg-jni--", split[i]);
        }
        return run(split.length, split);
    }

    public native int run(int i, String[] strArr);
}
