package com.xiaomi.xy.sportscamera.camera.filemanager.j22file;

import com.rp.rpfiledatapool.model.RPFile;
import com.sina.weibo.sdk.component.ShareRequestParam;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.g;

@g(a = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007J\u001c\u0010\u0003\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\b\u001a\u00020\u0007¨\u0006\u000b"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCollectionUtil;", "", "()V", "contains", "", "queue", "Ljava/util/concurrent/LinkedBlockingQueue;", "Lcom/rp/rpfiledatapool/model/RPFile;", ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA, "list", "", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public static final e f4637a = null;

    static {
        new e();
    }

    private e() {
        f4637a = this;
    }

    public final boolean a(List<? extends RPFile> list, RPFile rPFile) {
        kotlin.jvm.internal.g.b(list, "list");
        kotlin.jvm.internal.g.b(rPFile, ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA);
        List<? extends RPFile> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (kotlin.jvm.internal.g.a((Object) ((RPFile) it2.next()).getPath_dev(), (Object) rPFile.getPath_dev())) {
                return true;
            }
        }
        return false;
    }

    public final boolean a(LinkedBlockingQueue<RPFile> linkedBlockingQueue, RPFile rPFile) {
        kotlin.jvm.internal.g.b(linkedBlockingQueue, "queue");
        kotlin.jvm.internal.g.b(rPFile, ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA);
        LinkedBlockingQueue<RPFile> linkedBlockingQueue2 = linkedBlockingQueue;
        if ((linkedBlockingQueue2 instanceof Collection) && linkedBlockingQueue2.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = linkedBlockingQueue2.iterator();
        while (it2.hasNext()) {
            if (kotlin.jvm.internal.g.a((Object) ((RPFile) it2.next()).getPath_dev(), (Object) rPFile.getPath_dev())) {
                return true;
            }
        }
        return false;
    }
}
