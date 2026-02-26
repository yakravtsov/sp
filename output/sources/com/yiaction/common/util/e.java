package com.yiaction.common.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/* loaded from: classes3.dex */
public class e {
    public static File a(Context context, String str) {
        String path;
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File externalCacheDir = context.getExternalCacheDir();
            path = externalCacheDir != null ? externalCacheDir.getPath() : context.getCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + str);
    }

    public static String a() {
        g.a("first sd card path", Environment.getExternalStorageDirectory().getAbsolutePath(), new Object[0]);
        String str = System.getenv("SECONDARY_STORAGE");
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(":")) {
                if (new File(str2).isDirectory()) {
                    g.a("second sd card path", str2, new Object[0]);
                    return str2;
                }
            }
        }
        return null;
    }

    public static String a(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme != null && !"file".equals(scheme)) {
            if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
                return null;
            }
            if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
                str = query.getString(columnIndex);
            }
            query.close();
            return str;
        }
        return uri.getPath();
    }

    public static String a(Context context, String str, String str2) {
        return h(context).getAbsolutePath() + "/ve_" + h.a(str) + "_" + str2;
    }

    public static String a(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            fileInputStream.close();
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest.digest()) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(String str) {
        try {
            b(str);
            new File(str.toString()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long b(File file) {
        long j;
        Exception e;
        try {
            File[] listFiles = file.listFiles();
            j = 0;
            for (int i = 0; i < listFiles.length; i++) {
                try {
                    j += listFiles[i].isDirectory() ? b(listFiles[i]) : listFiles[i].length();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return j;
                }
            }
        } catch (Exception e3) {
            j = 0;
            e = e3;
        }
        return j;
    }

    public static File b() {
        File file = new File(Constant.b);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static boolean b(String str) {
        boolean z = false;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        String[] list = file.list();
        int i = 0;
        while (true) {
            boolean z2 = z;
            if (i >= list.length) {
                return z2;
            }
            File file2 = str.endsWith(File.separator) ? new File(str + list[i]) : new File(str + File.separator + list[i]);
            if (file2.isFile()) {
                file2.delete();
            }
            if (file2.isDirectory()) {
                b(str + "/" + list[i]);
                a(str + "/" + list[i]);
                z = true;
            } else {
                z = z2;
            }
            i++;
        }
    }

    public static File c(Context context) {
        File a2 = a(context, "share_thumb");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }

    public static File d(Context context) {
        File a2 = a(context, "edit_pic_thumb");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }

    public static File e(Context context) {
        File a2 = a(context, "edit_audio");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }

    public static File f(Context context) {
        File a2 = a(context, "edit_music_thumb");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }

    public static File g(Context context) {
        File a2 = a(context, "edit_sticker_preview");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }

    public static File h(Context context) {
        File a2 = a(context, "edit_sticker_preview");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }

    public static File i(Context context) {
        File a2 = a(context, "head_show");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }

    public static File j(Context context) {
        File a2 = a(context, "compound");
        if (!a2.exists()) {
            a2.mkdir();
        }
        return a2;
    }
}
