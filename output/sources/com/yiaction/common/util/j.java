package com.yiaction.common.util;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class j {
    public static String a(Context context, String str) {
        String str2;
        Exception e;
        try {
            InputStream open = context.getResources().getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            str2 = new String(bArr, "UTF-8");
            try {
                open.close();
            } catch (Exception e2) {
                e = e2;
                System.out.print(e.toString());
                return str2;
            }
        } catch (Exception e3) {
            str2 = "";
            e = e3;
        }
        return str2;
    }

    public static boolean a(Context context, String str, String str2) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        fileOutputStream2 = null;
        InputStream inputStream2 = null;
        try {
            inputStream = context.getAssets().open(str);
            try {
                fileOutputStream = new FileOutputStream(str2);
            } catch (Exception e) {
                fileOutputStream = null;
                inputStream2 = inputStream;
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                    }
                }
                return true;
            } catch (Exception e4) {
                inputStream2 = inputStream;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (Exception e5) {
                    }
                }
                if (fileOutputStream == null) {
                    return false;
                }
                try {
                    fileOutputStream.close();
                    return false;
                } catch (Exception e6) {
                    return false;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e7) {
                    }
                }
                if (fileOutputStream2 == null) {
                    throw th;
                }
                try {
                    fileOutputStream2.close();
                    throw th;
                } catch (Exception e8) {
                    throw th;
                }
            }
        } catch (Exception e9) {
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    public static String[] b(Context context, String str) {
        try {
            return context.getAssets().list(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
