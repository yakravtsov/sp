package com.yiaction.common.ffmpeg;

/* loaded from: classes3.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private float f4929a = -1.0f;
    private float b = -1.0f;
    private int c = -1;
    private int d = -1;
    private String e;
    private String f;

    public a a(float f) {
        this.f4929a = f;
        return this;
    }

    public a a(String str) {
        this.f = str;
        return this;
    }

    public String a() {
        StringBuilder sb = new StringBuilder("ffmpeg ");
        if (this.f != null) {
            sb.append("-i ").append(this.f).append(" ");
        }
        if (this.f4929a != -1.0f) {
            sb.append("-ss ").append(this.f4929a).append(" ");
        }
        if (this.b != -1.0f) {
            sb.append("-t ").append(this.b).append(" ");
        }
        sb.append("-c copy ");
        sb.append(this.e);
        return sb.toString();
    }

    public a b(float f) {
        this.b = f;
        return this;
    }

    public a b(String str) {
        this.e = str;
        return this;
    }
}
