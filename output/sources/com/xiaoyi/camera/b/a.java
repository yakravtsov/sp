package com.xiaoyi.camera.b;

import android.widget.ImageView;
import com.xiaoyi.camera.module.FileItem;
import com.xiaoyi.camera.module.VideoFileItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private static List<a> f4812a = null;

    public static void C() {
        if (f4812a != null) {
            f4812a.clear();
        }
        f4812a = null;
    }

    public static List<a> D() {
        if (f4812a == null) {
            f4812a = Collections.synchronizedList(new ArrayList());
        }
        return f4812a;
    }

    public static void a(a aVar) {
        if (f4812a == null) {
            f4812a = Collections.synchronizedList(new ArrayList());
        }
        if (f4812a.contains(aVar)) {
            return;
        }
        f4812a.add(aVar);
    }

    public static void b(a aVar) {
        if (f4812a != null) {
            f4812a.remove(aVar);
        }
    }

    public void a() {
    }

    public void a(FileItem fileItem) {
    }

    public void a(FileItem fileItem, ImageView imageView) {
    }

    public void a(FileItem fileItem, String str) {
    }

    public void a(VideoFileItem videoFileItem) {
    }

    public void b(FileItem fileItem) {
    }

    public void c(FileItem fileItem) {
    }

    public void d(FileItem fileItem) {
    }

    public void e(FileItem fileItem) {
    }

    public void f(FileItem fileItem) {
    }
}
