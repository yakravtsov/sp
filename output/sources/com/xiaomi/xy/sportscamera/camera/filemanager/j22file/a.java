package com.xiaomi.xy.sportscamera.camera.filemanager.j22file;

import com.rp.rpfiledatapool.model.RPFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.g;
import kotlin.jvm.internal.f;

@g(a = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0004H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u0017"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl;", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControlListener;", "()V", "deleteCancled", "", "deleteFailed", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "deleteSuccess", "downloadCancled", "downloadFailed", "downloadSuccess", "initFileListComplete", "hasFile", "", "initFileListFail", "sdCardEmpty", "startDownLoad", "updateProgress", "speed", "", "updateSpeed", "Companion", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private static List<a> f4623a;
    public static final C0193a b = new C0193a(null);

    @g(a = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0005R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0010"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl$Companion;", "", "()V", "mControllerList", "", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl;", "getMControllerList", "()Ljava/util/List;", "setMControllerList", "(Ljava/util/List;)V", "clear", "", "getControllerList", "registerController", "controller", "unregisterController", "ants_sports_camera_internationalRelease"})
    /* renamed from: com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0193a {
        private C0193a() {
        }

        public /* synthetic */ C0193a(f fVar) {
            this();
        }

        private final void a(List<a> list) {
            a.f4623a = list;
        }

        private final List<a> c() {
            return a.f4623a;
        }

        public final void a() {
            if (c() != null) {
                List<a> c = c();
                if (c == null) {
                    kotlin.jvm.internal.g.a();
                }
                c.clear();
            }
            a((List<a>) null);
        }

        public final void a(a aVar) {
            kotlin.jvm.internal.g.b(aVar, "controller");
            if (c() == null) {
                a(Collections.synchronizedList(new ArrayList()));
            }
            List<a> c = c();
            if (c == null) {
                kotlin.jvm.internal.g.a();
            }
            if (c.contains(aVar)) {
                return;
            }
            List<a> c2 = c();
            if (c2 == null) {
                kotlin.jvm.internal.g.a();
            }
            c2.add(aVar);
        }

        public final List<a> b() {
            if (c() == null) {
                a(Collections.synchronizedList(new ArrayList()));
            }
            List<a> c = c();
            if (c == null) {
                kotlin.jvm.internal.g.a();
            }
            return c;
        }

        public final void b(a aVar) {
            kotlin.jvm.internal.g.b(aVar, "controller");
            if (c() != null) {
                List<a> c = c();
                if (c == null) {
                    kotlin.jvm.internal.g.a();
                }
                c.remove(aVar);
            }
        }
    }

    public void a() {
    }

    public void a(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "item");
    }

    public void a(RPFile rPFile, String str) {
        kotlin.jvm.internal.g.b(rPFile, "item");
        kotlin.jvm.internal.g.b(str, "speed");
    }

    public void a(boolean z) {
    }

    public void b(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "item");
    }

    public void c(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "item");
    }

    public void d(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "item");
    }

    public void e(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "item");
    }
}
