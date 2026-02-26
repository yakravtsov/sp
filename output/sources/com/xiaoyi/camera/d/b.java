package com.xiaoyi.camera.d;

import io.realm.CameraDevice;
import io.realm.n;
import io.realm.q;
import io.realm.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private q f4842a = new q();

    public b() {
        this.f4842a.a();
    }

    public List<CameraDevice> a(String str, String str2) {
        try {
            y a2 = this.f4842a.f6060a.a(CameraDevice.class).a(str, str2).a();
            if (a2 != null && a2.size() > 0) {
                return this.f4842a.f6060a.a(a2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

    public void a() {
        this.f4842a.b();
    }

    public void a(CameraDevice cameraDevice) {
        if (cameraDevice == null) {
            return;
        }
        this.f4842a.a(cameraDevice);
    }

    public void b() {
        this.f4842a.f6060a.a(new n.a() { // from class: com.xiaoyi.camera.d.b.1
            @Override // io.realm.n.a
            public void a(n nVar) {
                y a2 = nVar.a(CameraDevice.class).a();
                if (a2 == null || a2.isEmpty()) {
                    return;
                }
                com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "delAllDeviceToRealm : results.size() = : " + a2.size(), new Object[0]);
                Iterator it2 = a2.iterator();
                while (it2.hasNext()) {
                    CameraDevice cameraDevice = (CameraDevice) it2.next();
                    com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "delAllDeviceToRealm : deviceSn = : " + cameraDevice.realmGet$deviceSn(), new Object[0]);
                    cameraDevice.deleteFromRealm();
                }
            }
        });
    }
}
