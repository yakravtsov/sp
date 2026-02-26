package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity;

/* loaded from: classes3.dex */
public class CameraUpgradeActivity_ViewBinding<T extends CameraUpgradeActivity> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4753a;

    public CameraUpgradeActivity_ViewBinding(T t, View view) {
        this.f4753a = t;
        t.titleBar = (CustomTitleBar) Utils.findRequiredViewAsType(view, R.id.titleBar, "field 'titleBar'", CustomTitleBar.class);
        t.tvVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tvVersion, "field 'tvVersion'", TextView.class);
        t.llVersion = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llVersion, "field 'llVersion'", LinearLayout.class);
        t.tvVersion1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tvVersion1, "field 'tvVersion1'", TextView.class);
        t.tvVersion2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tvVersion2, "field 'tvVersion2'", TextView.class);
        t.tvUpgradeContent = (TextView) Utils.findRequiredViewAsType(view, R.id.tvUpgradeContent, "field 'tvUpgradeContent'", TextView.class);
        t.tvDeleteVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDeleteVersion, "field 'tvDeleteVersion'", TextView.class);
        t.btnUpgrade = (Button) Utils.findRequiredViewAsType(view, R.id.btnUpgrade, "field 'btnUpgrade'", Button.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4753a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.titleBar = null;
        t.tvVersion = null;
        t.llVersion = null;
        t.tvVersion1 = null;
        t.tvVersion2 = null;
        t.tvUpgradeContent = null;
        t.tvDeleteVersion = null;
        t.btnUpgrade = null;
        this.f4753a = null;
    }
}
