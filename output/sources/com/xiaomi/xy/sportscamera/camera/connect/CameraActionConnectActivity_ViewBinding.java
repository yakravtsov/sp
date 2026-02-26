package com.xiaomi.xy.sportscamera.camera.connect;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.connect.CameraActionConnectActivity;

/* loaded from: classes3.dex */
public class CameraActionConnectActivity_ViewBinding<T extends CameraActionConnectActivity> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4584a;

    public CameraActionConnectActivity_ViewBinding(T t, View view) {
        this.f4584a = t;
        t.titleBar = (CustomTitleBar) Utils.findRequiredViewAsType(view, R.id.titleBar, "field 'titleBar'", CustomTitleBar.class);
        t.rl_connect_status = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_connect_status, "field 'rl_connect_status'", RelativeLayout.class);
        t.rlModel = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rlModel, "field 'rlModel'", RelativeLayout.class);
        t.ivModel = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivModel, "field 'ivModel'", ImageView.class);
        t.tvRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tvRemind, "field 'tvRemind'", TextView.class);
        t.tvRemindDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.tvRemindDesc, "field 'tvRemindDesc'", TextView.class);
        t.ivLoad = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivLoad, "field 'ivLoad'", ImageView.class);
        t.tvNext = (TextView) Utils.findRequiredViewAsType(view, R.id.tvNext, "field 'tvNext'", TextView.class);
        t.tvNeedSupport = (TextView) Utils.findRequiredViewAsType(view, R.id.tvNeedSupport, "field 'tvNeedSupport'", TextView.class);
        t.mListView = (ListView) Utils.findRequiredViewAsType(view, R.id.lvCameraList, "field 'mListView'", ListView.class);
        t.deviceVP = (ViewPager) Utils.findRequiredViewAsType(view, R.id.deviceVP, "field 'deviceVP'", ViewPager.class);
        t.rl_connect_fail = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_connect_fail, "field 'rl_connect_fail'", RelativeLayout.class);
        t.iv_connect_fail = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_connect_fail, "field 'iv_connect_fail'", ImageView.class);
        t.tv_connect_fail = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_connect_fail, "field 'tv_connect_fail'", TextView.class);
        t.contact_customer_service = (TextView) Utils.findRequiredViewAsType(view, R.id.contact_customer_service, "field 'contact_customer_service'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4584a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.titleBar = null;
        t.rl_connect_status = null;
        t.rlModel = null;
        t.ivModel = null;
        t.tvRemind = null;
        t.tvRemindDesc = null;
        t.ivLoad = null;
        t.tvNext = null;
        t.tvNeedSupport = null;
        t.mListView = null;
        t.deviceVP = null;
        t.rl_connect_fail = null;
        t.iv_connect_fail = null;
        t.tv_connect_fail = null;
        t.contact_customer_service = null;
        this.f4584a = null;
    }
}
