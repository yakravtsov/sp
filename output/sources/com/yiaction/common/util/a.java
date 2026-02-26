package com.yiaction.common.util;

import com.ants360.z13.club.ClubModel;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes3.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static StringBuilder f4961a;
    private static Formatter b;

    public static long a(long j, boolean z) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(a()));
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return (z ? date.getTime() + 86400000 : date.getTime()) - j;
    }

    public static String a(int i) {
        f4961a = new StringBuilder();
        b = new Formatter(f4961a, Locale.getDefault());
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        int i4 = i / 3600;
        f4961a.setLength(0);
        return i4 > 0 ? b.format("%02d:%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)).toString() : b.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2)).toString();
    }

    public static Timestamp a() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 1);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static String b(int i) {
        StringBuilder sb = new StringBuilder();
        int i2 = i / 60;
        int i3 = i - (i2 * 60);
        sb.append(i2 < 10 ? ClubModel.beMember + i2 : String.valueOf(i2)).append(":");
        sb.append(i3 < 10 ? ClubModel.beMember + i3 : String.valueOf(i3));
        return sb.toString();
    }

    public static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static String c(long j) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(j));
    }

    public static String d(long j) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j));
    }

    public static String e(long j) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(j));
    }
}
