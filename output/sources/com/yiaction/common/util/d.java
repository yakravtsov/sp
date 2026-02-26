package com.yiaction.common.util;

import android.content.Context;
import android.util.Pair;
import com.yiaction.common.R;
import it.sephiroth.android.library.exif2.ExifInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    public static ArrayList<Pair<String, String>> f4962a;

    private static int a(ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.h);
        if (a2 == null) {
            arrayList.add(new Pair<>("Model", ""));
            return 0;
        }
        String l = a2.l();
        g.a("Get TAG_MODEL: " + l, new Object[0]);
        if (l != null) {
            arrayList.add(new Pair<>("Model", l));
            return 1;
        }
        arrayList.add(new Pair<>("Model", ""));
        return 0;
    }

    public static String a(String str) {
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.a(str, 63);
            it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.ad);
            String l = a2.l();
            String str2 = new String(a2.g());
            g.a(l, new Object[0]);
            g.a(str2, new Object[0]);
            return str2;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(ArrayList<Pair<String, String>> arrayList) {
        if (arrayList == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        Iterator<Pair<String, String>> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Pair<String, String> next = it2.next();
            try {
                jSONObject.put((String) next.first, next.second);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        g.a(jSONObject.toString(), new Object[0]);
        return jSONObject.toString();
    }

    public static ArrayList<Pair<String, String>> a(Context context, String str) {
        ArrayList<Pair<String, String>> arrayList;
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.a(str, 63);
            arrayList = new ArrayList<>();
            try {
                if (a(exifInterface, arrayList) + 0 + b(exifInterface, arrayList) + b(context, exifInterface, arrayList) + c(context, exifInterface, arrayList) + d(context, exifInterface, arrayList) + c(exifInterface, arrayList) + e(context, exifInterface, arrayList) + d(exifInterface, arrayList) >= 5) {
                    f(context, exifInterface, arrayList);
                    g(context, exifInterface, arrayList);
                    h(context, exifInterface, arrayList);
                    a(context, exifInterface, arrayList);
                } else {
                    g.a("less than 5 params, skip exif", new Object[0]);
                    arrayList.clear();
                }
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                f4962a = arrayList;
                return arrayList;
            } catch (NullPointerException e2) {
                e = e2;
                e.printStackTrace();
                f4962a = arrayList;
                return arrayList;
            }
        } catch (IOException e3) {
            e = e3;
            arrayList = null;
        } catch (NullPointerException e4) {
            e = e4;
            arrayList = null;
        }
        f4962a = arrayList;
        return arrayList;
    }

    private static void a(Context context, ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.I);
        if (a2 == null) {
            arrayList.add(new Pair<>("ExposureProgram", "- -"));
            return;
        }
        String l = a2.l();
        g.a("Get TAG_EXPOSURE_PROGRAM: " + l, new Object[0]);
        String str = null;
        try {
            switch (Integer.valueOf(l).intValue()) {
                case 0:
                    str = context.getResources().getString(R.string.Not_defined);
                    break;
                case 1:
                    str = context.getResources().getString(R.string.Manual);
                    break;
                case 2:
                    str = context.getResources().getString(R.string.Normal_program);
                    break;
                case 3:
                    str = context.getResources().getString(R.string.Aperture_priority);
                    break;
                case 4:
                    str = context.getResources().getString(R.string.Shutter_priority);
                    break;
                case 5:
                    str = context.getResources().getString(R.string.Creative_program);
                    break;
                case 6:
                    str = context.getResources().getString(R.string.Action_program);
                    break;
                case 7:
                    str = context.getResources().getString(R.string.Portrait_mode);
                    break;
                case 8:
                    str = context.getResources().getString(R.string.Landscape_mode);
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (str != null) {
            arrayList.add(new Pair<>("ExposureProgram", str));
        } else {
            arrayList.add(new Pair<>("ExposureProgram", "- -"));
        }
    }

    public static void a(String str, String str2) {
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.a(str, 63);
            exifInterface.a(exifInterface.b(ExifInterface.ad, str2));
            exifInterface.a(ExifInterface.ad, str2);
            exifInterface.a(str);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int b(android.content.Context r6, it.sephiroth.android.library.exif2.ExifInterface r7, java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r8) {
        /*
            r0 = 1
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1 = 0
            int r2 = it.sephiroth.android.library.exif2.ExifInterface.aa
            it.sephiroth.android.library.exif2.f r2 = r7.a(r2)
            if (r2 != 0) goto L19
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r2 = "FocalLength"
            java.lang.String r3 = "- -"
            r0.<init>(r2, r3)
            r8.add(r0)
        L18:
            return r1
        L19:
            java.lang.String r2 = r2.l()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Get TAG_FOCAL_LENGTH: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            java.lang.Object[] r5 = new java.lang.Object[r1]
            com.yiaction.common.util.g.a(r4, r5)
            java.lang.String r4 = "/"
            java.lang.String[] r2 = r2.split(r4)
            int r4 = r2.length
            r5 = 2
            if (r4 != r5) goto L86
            r4 = 0
            r4 = r2[r4]     // Catch: java.lang.NumberFormatException -> L82
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch: java.lang.NumberFormatException -> L82
            float r4 = r4.floatValue()     // Catch: java.lang.NumberFormatException -> L82
            r5 = 1
            r2 = r2[r5]     // Catch: java.lang.NumberFormatException -> L82
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch: java.lang.NumberFormatException -> L82
            float r2 = r2.floatValue()     // Catch: java.lang.NumberFormatException -> L82
            float r2 = r4 / r2
        L57:
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L88
            android.util.Pair r1 = new android.util.Pair
            java.lang.String r3 = "FocalLength"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r2 = r4.append(r2)
            android.content.res.Resources r4 = r6.getResources()
            int r5 = com.yiaction.common.R.string.mm
            java.lang.String r4 = r4.getString(r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.<init>(r3, r2)
            r8.add(r1)
        L80:
            r1 = r0
            goto L18
        L82:
            r2 = move-exception
            r2.printStackTrace()
        L86:
            r2 = r3
            goto L57
        L88:
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r2 = "FocalLength"
            java.lang.String r3 = "- -"
            r0.<init>(r2, r3)
            r8.add(r0)
            r0 = r1
            goto L80
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yiaction.common.util.d.b(android.content.Context, it.sephiroth.android.library.exif2.ExifInterface, java.util.ArrayList):int");
    }

    private static int b(ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.aN);
        if (a2 == null) {
            arrayList.add(new Pair<>("LensModel", ""));
            return 0;
        }
        String l = a2.l();
        g.a("Get TAG_LENS_MODEL: " + l, new Object[0]);
        if (l != null) {
            arrayList.add(new Pair<>("LensModel", l));
        } else {
            arrayList.add(new Pair<>("LensModel", ""));
        }
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int c(android.content.Context r7, it.sephiroth.android.library.exif2.ExifInterface r8, java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r9) {
        /*
            r0 = 1
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1 = 0
            int r2 = it.sephiroth.android.library.exif2.ExifInterface.H
            it.sephiroth.android.library.exif2.f r2 = r8.a(r2)
            if (r2 != 0) goto L19
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r2 = "FNumber"
            java.lang.String r3 = "- -"
            r0.<init>(r2, r3)
            r9.add(r0)
        L18:
            return r1
        L19:
            java.lang.String r2 = r2.l()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Get TAG_F_NUMBER: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            java.lang.Object[] r5 = new java.lang.Object[r1]
            com.yiaction.common.util.g.a(r4, r5)
            java.lang.String r4 = "/"
            java.lang.String[] r2 = r2.split(r4)
            int r4 = r2.length
            r5 = 2
            if (r4 != r5) goto L86
            r4 = 0
            r4 = r2[r4]     // Catch: java.lang.NumberFormatException -> L82
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch: java.lang.NumberFormatException -> L82
            float r4 = r4.floatValue()     // Catch: java.lang.NumberFormatException -> L82
            r5 = 1
            r2 = r2[r5]     // Catch: java.lang.NumberFormatException -> L82
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch: java.lang.NumberFormatException -> L82
            float r2 = r2.floatValue()     // Catch: java.lang.NumberFormatException -> L82
            float r2 = r4 / r2
        L57:
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L88
            android.util.Pair r1 = new android.util.Pair
            java.lang.String r3 = "FNumber"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            android.content.res.Resources r5 = r7.getResources()
            int r6 = com.yiaction.common.R.string.f
            java.lang.String r5 = r5.getString(r6)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            r1.<init>(r3, r2)
            r9.add(r1)
        L80:
            r1 = r0
            goto L18
        L82:
            r2 = move-exception
            r2.printStackTrace()
        L86:
            r2 = r3
            goto L57
        L88:
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r2 = "FNumber"
            java.lang.String r3 = "- -"
            r0.<init>(r2, r3)
            r9.add(r0)
            r0 = r1
            goto L80
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yiaction.common.util.d.c(android.content.Context, it.sephiroth.android.library.exif2.ExifInterface, java.util.ArrayList):int");
    }

    private static int c(ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        int i;
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.K);
        if (a2 == null) {
            arrayList.add(new Pair<>("ISO", "- -"));
            return 0;
        }
        String l = a2.l();
        g.a("Get TAG_ISO_SPEED_RATINGS: " + l, new Object[0]);
        try {
            i = Integer.valueOf(l).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            i = -1;
        }
        if (i != -1) {
            arrayList.add(new Pair<>("ISO", i + ""));
            return 1;
        }
        arrayList.add(new Pair<>("ISO", "- -"));
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int d(android.content.Context r6, it.sephiroth.android.library.exif2.ExifInterface r7, java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r8) {
        /*
            r2 = 1
            r1 = -1
            r3 = 0
            int r0 = it.sephiroth.android.library.exif2.ExifInterface.G
            it.sephiroth.android.library.exif2.f r0 = r7.a(r0)
            if (r0 != 0) goto L18
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r1 = "ExposureTime"
            java.lang.String r2 = "- -"
            r0.<init>(r1, r2)
            r8.add(r0)
        L17:
            return r3
        L18:
            java.lang.String r0 = r0.l()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Get TAG_EXPOSURE_TIME: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.Object[] r5 = new java.lang.Object[r3]
            com.yiaction.common.util.g.a(r4, r5)
            java.lang.String r4 = "/"
            java.lang.String[] r0 = r0.split(r4)
            int r4 = r0.length     // Catch: java.lang.NumberFormatException -> L95
            r5 = 2
            if (r4 != r5) goto L99
            r4 = 0
            r4 = r0[r4]     // Catch: java.lang.NumberFormatException -> L95
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.NumberFormatException -> L95
            int r4 = r4.intValue()     // Catch: java.lang.NumberFormatException -> L95
            if (r4 == 0) goto L99
            r4 = 1
            r4 = r0[r4]     // Catch: java.lang.NumberFormatException -> L95
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.NumberFormatException -> L95
            int r4 = r4.intValue()     // Catch: java.lang.NumberFormatException -> L95
            r5 = 0
            r0 = r0[r5]     // Catch: java.lang.NumberFormatException -> L95
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.NumberFormatException -> L95
            int r0 = r0.intValue()     // Catch: java.lang.NumberFormatException -> L95
            int r0 = r4 / r0
        L63:
            if (r0 == r1) goto L9b
            android.util.Pair r1 = new android.util.Pair
            java.lang.String r3 = "ExposureTime"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "1/"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r4 = " "
            java.lang.StringBuilder r0 = r0.append(r4)
            int r4 = com.yiaction.common.R.string.s
            java.lang.String r4 = r6.getString(r4)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            r1.<init>(r3, r0)
            r8.add(r1)
            r0 = r2
        L93:
            r3 = r0
            goto L17
        L95:
            r0 = move-exception
            r0.printStackTrace()
        L99:
            r0 = r1
            goto L63
        L9b:
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r1 = "ExposureTime"
            java.lang.String r2 = "- -"
            r0.<init>(r1, r2)
            r8.add(r0)
            r0 = r3
            goto L93
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yiaction.common.util.d.d(android.content.Context, it.sephiroth.android.library.exif2.ExifInterface, java.util.ArrayList):int");
    }

    private static int d(ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.ai);
        if (a2 == null) {
            arrayList.add(new Pair<>("ColorSpace", "- -"));
            return 0;
        }
        String l = a2.l();
        g.a("Get TAG_COLOR_SPACE: " + l, new Object[0]);
        if (l != null) {
            arrayList.add(new Pair<>("ColorSpace", l));
            return 1;
        }
        arrayList.add(new Pair<>("ColorSpace", "- -"));
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int e(android.content.Context r6, it.sephiroth.android.library.exif2.ExifInterface r7, java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r8) {
        /*
            r0 = 1
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1 = 0
            int r2 = it.sephiroth.android.library.exif2.ExifInterface.U
            it.sephiroth.android.library.exif2.f r2 = r7.a(r2)
            if (r2 != 0) goto L19
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r2 = "ExposureBiasValue"
            java.lang.String r3 = "- -"
            r0.<init>(r2, r3)
            r8.add(r0)
        L18:
            return r1
        L19:
            java.lang.String r2 = r2.l()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Get TAG_EXPOSURE_PROGRAM: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            java.lang.Object[] r5 = new java.lang.Object[r1]
            com.yiaction.common.util.g.a(r4, r5)
            java.lang.String r4 = "/"
            java.lang.String[] r2 = r2.split(r4)
            int r4 = r2.length
            r5 = 2
            if (r4 != r5) goto L86
            r4 = 0
            r4 = r2[r4]     // Catch: java.lang.NumberFormatException -> L82
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch: java.lang.NumberFormatException -> L82
            float r4 = r4.floatValue()     // Catch: java.lang.NumberFormatException -> L82
            r5 = 1
            r2 = r2[r5]     // Catch: java.lang.NumberFormatException -> L82
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch: java.lang.NumberFormatException -> L82
            float r2 = r2.floatValue()     // Catch: java.lang.NumberFormatException -> L82
            float r2 = r4 / r2
        L57:
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L88
            android.util.Pair r1 = new android.util.Pair
            java.lang.String r3 = "ExposureBiasValue"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r2 = r4.append(r2)
            android.content.res.Resources r4 = r6.getResources()
            int r5 = com.yiaction.common.R.string.ev
            java.lang.String r4 = r4.getString(r5)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.<init>(r3, r2)
            r8.add(r1)
        L80:
            r1 = r0
            goto L18
        L82:
            r2 = move-exception
            r2.printStackTrace()
        L86:
            r2 = r3
            goto L57
        L88:
            android.util.Pair r0 = new android.util.Pair
            java.lang.String r2 = "ExposureBiasValue"
            java.lang.String r3 = "- -"
            r0.<init>(r2, r3)
            r8.add(r0)
            r0 = r1
            goto L80
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yiaction.common.util.d.e(android.content.Context, it.sephiroth.android.library.exif2.ExifInterface, java.util.ArrayList):int");
    }

    private static void f(Context context, ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.X);
        if (a2 == null) {
            arrayList.add(new Pair<>("MeteringMode", "- -"));
            return;
        }
        String l = a2.l();
        g.a("Get TAG_METERING_MODE: " + l, new Object[0]);
        String str = null;
        try {
            switch (Integer.valueOf(l).intValue()) {
                case 0:
                    str = context.getResources().getString(R.string.Unknown);
                    break;
                case 1:
                    str = context.getResources().getString(R.string.Average);
                    break;
                case 2:
                    str = context.getResources().getString(R.string.CenterWeightedAverage);
                    break;
                case 3:
                    str = context.getResources().getString(R.string.Spot);
                    break;
                case 4:
                    str = context.getResources().getString(R.string.MultiSpot);
                    break;
                case 5:
                    str = context.getResources().getString(R.string.Pattern);
                    break;
                case 6:
                    str = context.getResources().getString(R.string.Partial);
                    break;
                case 255:
                    str = context.getResources().getString(R.string.other);
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (str != null) {
            arrayList.add(new Pair<>("MeteringMode", str));
        } else {
            arrayList.add(new Pair<>("MeteringMode", "- -"));
        }
    }

    private static void g(Context context, ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.aA);
        if (a2 == null) {
            arrayList.add(new Pair<>("WhiteBalance", "- -"));
            return;
        }
        String l = a2.l();
        g.a("Get TAG_WHITE_BALANCE: " + l, new Object[0]);
        String str = null;
        try {
            switch (Integer.valueOf(l).intValue()) {
                case 0:
                    str = context.getResources().getString(R.string.auto);
                    break;
                case 1:
                    str = context.getResources().getString(R.string.Manual);
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (str != null) {
            arrayList.add(new Pair<>("WhiteBalance", str));
        } else {
            arrayList.add(new Pair<>("WhiteBalance", "- -"));
        }
    }

    private static void h(Context context, ExifInterface exifInterface, ArrayList<Pair<String, String>> arrayList) {
        it.sephiroth.android.library.exif2.f a2 = exifInterface.a(ExifInterface.Z);
        if (a2 == null) {
            arrayList.add(new Pair<>("Flash", "- -"));
            return;
        }
        String l = a2.l();
        g.a("Get TAG_FLASH: " + l, new Object[0]);
        String str = null;
        try {
            switch (Integer.valueOf(Integer.valueOf(l).intValue() & 1).intValue()) {
                case 0:
                    str = context.getResources().getString(R.string.flash_close);
                    break;
                case 1:
                    str = context.getResources().getString(R.string.flash_open);
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (str != null) {
            arrayList.add(new Pair<>("Flash", str));
        } else {
            arrayList.add(new Pair<>("Flash", "- -"));
        }
    }
}
