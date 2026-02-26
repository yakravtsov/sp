package com.xiaomi.xy.sportscamera.camera.fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.fragment.DeviceFragment;

/* loaded from: classes3.dex */
public class DeviceFragment_ViewBinding<T extends DeviceFragment> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4643a;

    public DeviceFragment_ViewBinding(T t, View view) {
        this.f4643a = t;
        t.mYILogo = (ImageView) Utils.findRequiredViewAsType(view, R.id.camera_connect_yi_logo, "field 'mYILogo'", ImageView.class);
        t.camera_connect_yi_help = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.camera_connect_yi_help, "field 'camera_connect_yi_help'", RelativeLayout.class);
        t.iv_yi_help = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_yi_help, "field 'iv_yi_help'", ImageView.class);
        t.llChoiceDevice = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.llChoiceDevice, "field 'llChoiceDevice'", RelativeLayout.class);
        t.llConnected = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.llConnected, "field 'llConnected'", RelativeLayout.class);
        t.ivDeviceIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivDeviceIcon, "field 'ivDeviceIcon'", ImageView.class);
        t.tvDeviceName = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDeviceName, "field 'tvDeviceName'", TextView.class);
        t.tvDeviceStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDeviceStatus, "field 'tvDeviceStatus'", TextView.class);
        t.tvEnterDevice = (TextView) Utils.findRequiredViewAsType(view, R.id.tvEnterDevice, "field 'tvEnterDevice'", TextView.class);
        t.tvDisconnect = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDisconnect, "field 'tvDisconnect'", TextView.class);
        t.tvFirmware = (TextView) Utils.findRequiredViewAsType(view, R.id.tvFirmware, "field 'tvFirmware'", TextView.class);
        t.llFirmware = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llFirmware, "field 'llFirmware'", LinearLayout.class);
        t.deviceGridView = (GridView) Utils.findRequiredViewAsType(view, R.id.deviceList, "field 'deviceGridView'", GridView.class);
        t.tv_live = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_live, "field 'tv_live'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4643a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.mYILogo = null;
        t.camera_connect_yi_help = null;
        t.iv_yi_help = null;
        t.llChoiceDevice = null;
        t.llConnected = null;
        t.ivDeviceIcon = null;
        t.tvDeviceName = null;
        t.tvDeviceStatus = null;
        t.tvEnterDevice = null;
        t.tvDisconnect = null;
        t.tvFirmware = null;
        t.llFirmware = null;
        t.deviceGridView = null;
        t.tv_live = null;
        this.f4643a = null;
    }
}
