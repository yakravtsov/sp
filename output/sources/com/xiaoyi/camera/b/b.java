package com.xiaoyi.camera.b;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.xiaoyi.camera.module.FileItem;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class b implements Runnable {
    public int b = 0;
    public int c = 500;
    protected int d = 3;

    /* loaded from: classes3.dex */
    public interface a {
        void a(Bitmap bitmap);
    }

    public FileItem a() {
        return null;
    }

    public void a(JSONObject jSONObject) {
    }

    public void a(boolean z) {
    }

    public FileItem.Action b() {
        return null;
    }

    public void b_(String str) {
    }

    public String c() {
        return null;
    }

    public ImageView d() {
        return null;
    }

    public String e() {
        return null;
    }

    public boolean g() {
        int i = this.d;
        this.d = i - 1;
        return i != 0;
    }

    public int h() {
        return this.b;
    }

    public int i() {
        return this.c;
    }
}
