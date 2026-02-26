package com.xiaomi.xy.sportscamera.camera.filemanager.j22file;

import com.google.api.client.http.HttpMethods;
import com.rp.rpfiledatapool.model.RPFile;
import java.io.Serializable;
import kotlin.g;
import kotlin.jvm.internal.f;

@g(a = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u0000 \u001a2\u00020\u0001:\u0002\u0019\u001aB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u001b"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask;", "Ljava/lang/Runnable;", "()V", "fromIndex", "", "getFromIndex", "()I", "setFromIndex", "(I)V", "toIndex", "getToIndex", "setToIndex", "getAction", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask$Action;", "getDirectory", "", "getFileItem", "Lcom/rp/rpfiledatapool/model/RPFile;", "setJSONObject", "", "jo", "Lorg/json/JSONObject;", "setSuccess", "success", "", "Action", "Companion", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public abstract class QZCameraFileTask implements Runnable {
    private static final int d = 0;
    private int b = f4622a.a();
    private int c = f4622a.b();

    /* renamed from: a, reason: collision with root package name */
    public static final a f4622a = new a(null);
    private static final int e = 50;

    @g(a = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask$Action;", "", "Ljava/io/Serializable;", "(Ljava/lang/String;I)V", "DOWNLOAD", HttpMethods.DELETE, "LISTING", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public enum Action implements Serializable {
        DOWNLOAD,
        DELETE,
        LISTING
    }

    @g(a = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileTask$Companion;", "", "()V", "FROM_DEFAULT", "", "getFROM_DEFAULT", "()I", "TO_DEFAULT", "getTO_DEFAULT", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }

        public final int a() {
            return QZCameraFileTask.d;
        }

        public final int b() {
            return QZCameraFileTask.e;
        }
    }

    public RPFile a() {
        return null;
    }

    public Action b() {
        return null;
    }
}
