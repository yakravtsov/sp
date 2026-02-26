package com.xiaoyi.camera.module;

/* loaded from: classes3.dex */
public class CameraFileItem implements Cloneable, Comparable<CameraFileItem> {

    /* renamed from: a, reason: collision with root package name */
    private int f4875a;
    private String b;
    private String c;

    /* loaded from: classes3.dex */
    public enum Action {
        THUMBNAIL,
        DOWNLOAD,
        DELETE
    }

    public int a() {
        return this.f4875a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(CameraFileItem cameraFileItem) {
        if (cameraFileItem != null && cameraFileItem.a() <= this.f4875a) {
            return cameraFileItem.a() < this.f4875a ? -1 : 0;
        }
        return 1;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            CameraFileItem cameraFileItem = (CameraFileItem) obj;
            if (this.b == null) {
                if (cameraFileItem.b != null) {
                    return false;
                }
            } else if (!this.b.equals(cameraFileItem.b)) {
                return false;
            }
            return this.c == null ? cameraFileItem.c == null : this.c.equals(cameraFileItem.c);
        }
        return false;
    }

    public int hashCode() {
        return (((this.b == null ? 0 : this.b.hashCode()) + 31) * 31) + (this.c != null ? this.c.hashCode() : 0);
    }
}
