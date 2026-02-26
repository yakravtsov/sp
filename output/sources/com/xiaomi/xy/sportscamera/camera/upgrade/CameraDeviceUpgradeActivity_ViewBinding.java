package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity;

/* loaded from: classes3.dex */
public class CameraDeviceUpgradeActivity_ViewBinding<T extends CameraDeviceUpgradeActivity> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4746a;

    public CameraDeviceUpgradeActivity_ViewBinding(T t, View view) {
        this.f4746a = t;
        t.titleBar = (CustomTitleBar) Utils.findRequiredViewAsType(view, R.id.titleBar, "field 'titleBar'", CustomTitleBar.class);
        t.deviceRV = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.deviceRV, "field 'deviceRV'", RecyclerView.class);
        t.tvStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tvStatus, "field 'tvStatus'", TextView.class);
        t.rlBlockingCover = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rlBlockingCover, "field 'rlBlockingCover'", RelativeLayout.class);
        t.tvInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.tvInfo, "field 'tvInfo'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4746a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.titleBar = null;
        t.deviceRV = null;
        t.tvStatus = null;
        t.rlBlockingCover = null;
        t.tvInfo = null;
        this.f4746a = null;
    }
}
