package com.yiaction.common.stats;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.xiaomi.c.a.b;
import com.yiaction.common.R;
import com.yiaction.common.util.g;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class UploadStatsManager {

    /* renamed from: a, reason: collision with root package name */
    private static Context f4959a;
    private static HashMap<TrackerName, Tracker> b = new HashMap<>();

    /* loaded from: classes3.dex */
    public enum TrackerName {
        APP_TRACKER,
        GLOBAL_TRACKER,
        ECOMMERCE_TRACKER
    }

    /* loaded from: classes3.dex */
    private static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(Context context, String str, String str2) {
            UploadStatsManager.c(context).send(new HitBuilders.EventBuilder().setCategory(str).setAction(str2).build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(Context context, String str, String str2, long j) {
            UploadStatsManager.c(context).send(new HitBuilders.EventBuilder().setCategory(str).setAction(str2).setValue(j).build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(Context context, String str, String str2, long j, Map<String, String> map) {
            UploadStatsManager.c(context).send(new HitBuilders.EventBuilder().setCategory(str).setAction(str2).setValue(j).setAll(map).build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(Context context, String str, String str2, String str3) {
            UploadStatsManager.c(context).send(new HitBuilders.EventBuilder().setCategory(str).setAction(str2).setLabel(str3).build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(Context context, String str, String str2, Map<String, String> map) {
            UploadStatsManager.c(context).send(new HitBuilders.EventBuilder().setCategory(str).setAction(str2).setAll(map).build());
        }
    }

    private static synchronized Tracker a(Context context, TrackerName trackerName) {
        Tracker tracker;
        synchronized (UploadStatsManager.class) {
            if (!b.containsKey(trackerName)) {
                GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(context);
                b.put(trackerName, trackerName == TrackerName.APP_TRACKER ? googleAnalytics.newTracker("UA-60717157-4") : googleAnalytics.newTracker(R.xml.global_tracker));
            }
            tracker = b.get(trackerName);
        }
        return tracker;
    }

    public static void a(Context context) {
        b.a(context, "2882303761517283873", "5111728330873", "xiaomi");
        b.a(2, 50L);
        b.a(false);
    }

    public static void a(String str, String str2) {
        b.a(str, str2);
        a.b(f4959a, str, str2);
        g.a("Statistic_onCountEvent", str + " : " + str2, new Object[0]);
    }

    public static void a(String str, String str2, long j) {
        b.a(str, str2, j);
        a.b(f4959a, str, str2, j);
        g.a("Statistic_onCalculateEvent", str + " : " + str2 + ":" + j, new Object[0]);
    }

    public static void a(String str, String str2, long j, Map<String, String> map) {
        b.a(str, str2, j, map);
        a.b(f4959a, str, str2, j, map);
        g.a("Statistic_onCalculateEvent", str + " : " + str2 + ":" + j + ":" + map.toString(), new Object[0]);
    }

    public static void a(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("result", str3);
        b.a(str, str2, hashMap);
        a.b(f4959a, str, str2, str3);
        g.a("Statistic_onStringEvent", str + " : " + str2 + ":" + str3, new Object[0]);
    }

    public static void a(String str, String str2, Map<String, String> map) {
        b.a(str, str2, map);
        a.b(f4959a, str, str2, map);
        g.a("Statistic_onCountEvent", str + " : " + str2 + ":" + map.toString(), new Object[0]);
    }

    public static void b(Context context) {
        f4959a = context;
        c(context).enableAdvertisingIdCollection(true);
    }

    public static synchronized Tracker c(Context context) {
        Tracker a2;
        synchronized (UploadStatsManager.class) {
            a2 = a(context, TrackerName.GLOBAL_TRACKER);
        }
        return a2;
    }
}
