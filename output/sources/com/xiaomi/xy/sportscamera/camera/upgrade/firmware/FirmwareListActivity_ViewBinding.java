package com.xiaomi.xy.sportscamera.camera.upgrade.firmware;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity;

/* loaded from: classes3.dex */
public class FirmwareListActivity_ViewBinding<T extends FirmwareListActivity> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4782a;

    public FirmwareListActivity_ViewBinding(T t, View view) {
        this.f4782a = t;
        t.titleBar = (CustomTitleBar) Utils.findRequiredViewAsType(view, R.id.titleBar, "field 'titleBar'", CustomTitleBar.class);
        t.ivCameraModel = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivCameraModel, "field 'ivCameraModel'", ImageView.class);
        t.tv_hw_version = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hw_version, "field 'tv_hw_version'", TextView.class);
        t.tv_fm_version = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_fm_version, "field 'tv_fm_version'", TextView.class);
        t.btnUpgrade = (TextView) Utils.findRequiredViewAsType(view, R.id.btnUpgrade, "field 'btnUpgrade'", TextView.class);
        t.download_progress = (DownloadProgressView) Utils.findRequiredViewAsType(view, R.id.download_progress, "field 'download_progress'", DownloadProgressView.class);
        t.rlBlock = Utils.findRequiredView(view, R.id.rlBlock, "field 'rlBlock'");
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4782a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.titleBar = null;
        t.ivCameraModel = null;
        t.tv_hw_version = null;
        t.tv_fm_version = null;
        t.btnUpgrade = null;
        t.download_progress = null;
        t.rlBlock = null;
        this.f4782a = null;
    }
}
