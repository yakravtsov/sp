package com.yiaction.common.util;

import android.os.Environment;
import android.os.StatFs;

/* loaded from: classes3.dex */
public class c {
    public static long a() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1024;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
