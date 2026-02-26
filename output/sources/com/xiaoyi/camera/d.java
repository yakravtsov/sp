package com.xiaoyi.camera;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class d {
    private static final Object e = new Object();
    private int b;
    private com.xiaoyi.camera.c.a d;

    /* renamed from: a, reason: collision with root package name */
    public String f4839a = "";
    private Map<String, Object> c = new HashMap();

    public d() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(int i, com.xiaoyi.camera.c.a aVar) {
        this.b = i;
        this.d = aVar;
    }

    public int a() {
        return this.b;
    }

    public Object a(String str) {
        return this.c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, Object obj) {
        this.c.put(str, obj);
    }

    public com.xiaoyi.camera.c.a b() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg_id", this.b);
            for (String str : this.c.keySet()) {
                jSONObject.put(str, this.c.get(str));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return "CameraMessage [command=" + this.b + "]";
    }
}
