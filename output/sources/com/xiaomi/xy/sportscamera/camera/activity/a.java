package com.xiaomi.xy.sportscamera.camera.activity;

import com.xiaoyi.camera.controller.CameraMainController;
import com.yiaction.common.util.Constant;
import kotlin.g;

@g
/* loaded from: classes.dex */
public final /* synthetic */ class a {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f4491a = new int[Constant.RecordMode.values().length];
    public static final /* synthetic */ int[] b;
    public static final /* synthetic */ int[] c;
    public static final /* synthetic */ int[] d;
    public static final /* synthetic */ int[] e;
    public static final /* synthetic */ int[] f;
    public static final /* synthetic */ int[] g;
    public static final /* synthetic */ int[] h;
    public static final /* synthetic */ int[] i;
    public static final /* synthetic */ int[] j;
    public static final /* synthetic */ int[] k;
    public static final /* synthetic */ int[] l;
    public static final /* synthetic */ int[] m;
    public static final /* synthetic */ int[] n;

    static {
        f4491a[Constant.RecordMode.TIMELAPES.ordinal()] = 1;
        f4491a[Constant.RecordMode.LOOP.ordinal()] = 2;
        f4491a[Constant.RecordMode.SLOW.ordinal()] = 3;
        f4491a[Constant.RecordMode.PHOTO.ordinal()] = 4;
        f4491a[Constant.RecordMode.NORMAL.ordinal()] = 5;
        b = new int[Constant.CaptureMode.values().length];
        b[Constant.CaptureMode.TIMER.ordinal()] = 1;
        b[Constant.CaptureMode.TIMELAPES.ordinal()] = 2;
        b[Constant.CaptureMode.BURST.ordinal()] = 3;
        c = new int[CameraMainController.CameraMode.values().length];
        c[CameraMainController.CameraMode.RecordMode.ordinal()] = 1;
        c[CameraMainController.CameraMode.CaptureMode.ordinal()] = 2;
        d = new int[CameraMainController.CameraMode.values().length];
        d[CameraMainController.CameraMode.CaptureMode.ordinal()] = 1;
        d[CameraMainController.CameraMode.RecordMode.ordinal()] = 2;
        e = new int[CameraMainController.CameraMode.values().length];
        e[CameraMainController.CameraMode.CaptureMode.ordinal()] = 1;
        e[CameraMainController.CameraMode.RecordMode.ordinal()] = 2;
        f = new int[Constant.RecordMode.values().length];
        f[Constant.RecordMode.TIMELAPES.ordinal()] = 1;
        f[Constant.RecordMode.PHOTO.ordinal()] = 2;
        f[Constant.RecordMode.LOOP.ordinal()] = 3;
        f[Constant.RecordMode.SLOW.ordinal()] = 4;
        f[Constant.RecordMode.NORMAL.ordinal()] = 5;
        g = new int[Constant.CaptureMode.values().length];
        g[Constant.CaptureMode.TIMER.ordinal()] = 1;
        g[Constant.CaptureMode.BURST.ordinal()] = 2;
        g[Constant.CaptureMode.TIMELAPES.ordinal()] = 3;
        g[Constant.CaptureMode.NORMAL.ordinal()] = 4;
        h = new int[CameraMainController.CameraMode.values().length];
        h[CameraMainController.CameraMode.RecordMode.ordinal()] = 1;
        h[CameraMainController.CameraMode.CaptureMode.ordinal()] = 2;
        i = new int[CameraMainController.CameraMode.values().length];
        i[CameraMainController.CameraMode.RecordMode.ordinal()] = 1;
        i[CameraMainController.CameraMode.CaptureMode.ordinal()] = 2;
        j = new int[Constant.CaptureMode.values().length];
        j[Constant.CaptureMode.TIMELAPES.ordinal()] = 1;
        j[Constant.CaptureMode.NORMAL.ordinal()] = 2;
        j[Constant.CaptureMode.TIMER.ordinal()] = 3;
        k = new int[CameraMainController.CameraMode.values().length];
        k[CameraMainController.CameraMode.RecordMode.ordinal()] = 1;
        k[CameraMainController.CameraMode.CaptureMode.ordinal()] = 2;
        l = new int[Constant.RecordMode.values().length];
        l[Constant.RecordMode.NORMAL.ordinal()] = 1;
        l[Constant.RecordMode.TIMELAPES.ordinal()] = 2;
        l[Constant.RecordMode.PHOTO.ordinal()] = 3;
        l[Constant.RecordMode.LOOP.ordinal()] = 4;
        l[Constant.RecordMode.SLOW.ordinal()] = 5;
        m = new int[Constant.CaptureMode.values().length];
        m[Constant.CaptureMode.NORMAL.ordinal()] = 1;
        m[Constant.CaptureMode.BURST.ordinal()] = 2;
        m[Constant.CaptureMode.TIMELAPES.ordinal()] = 3;
        m[Constant.CaptureMode.TIMER.ordinal()] = 4;
        n = new int[CameraMainController.CameraMode.values().length];
        n[CameraMainController.CameraMode.RecordMode.ordinal()] = 1;
        n[CameraMainController.CameraMode.CaptureMode.ordinal()] = 2;
    }
}
