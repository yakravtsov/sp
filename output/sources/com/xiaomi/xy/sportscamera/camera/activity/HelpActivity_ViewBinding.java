package com.xiaomi.xy.sportscamera.camera.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.activity.HelpActivity;

/* loaded from: classes3.dex */
public class HelpActivity_ViewBinding<T extends HelpActivity> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4439a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;

    public HelpActivity_ViewBinding(final T t, View view) {
        this.f4439a = t;
        t.titleBar = (CustomTitleBar) Utils.findRequiredViewAsType(view, R.id.titleBar, "field 'titleBar'", CustomTitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.category, "field 'category' and method 'onCategoryClick'");
        t.category = (TextView) Utils.castView(findRequiredView, R.id.category, "field 'category'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onCategoryClick();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.deviceName, "field 'deviceName' and method 'onDeviceClick'");
        t.deviceName = (TextView) Utils.castView(findRequiredView2, R.id.deviceName, "field 'deviceName'", TextView.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onDeviceClick();
            }
        });
        t.image = (ImageView) Utils.findRequiredViewAsType(view, R.id.image, "field 'image'", ImageView.class);
        t.listLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.list_layout, "field 'listLayout'", LinearLayout.class);
        t.listView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.list_view, "field 'listView'", RecyclerView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.desc_layout, "field 'descLayout' and method 'onDescClick'");
        t.descLayout = findRequiredView3;
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onDescClick();
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.faq_layout, "field 'faqLayout' and method 'onFaqClick'");
        t.faqLayout = findRequiredView4;
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onFaqClick();
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.connect_layout, "field 'connectLayout' and method 'onConnectClick'");
        t.connectLayout = findRequiredView5;
        this.f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onConnectClick();
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.scenes_layout, "field 'scenes_layout' and method 'onScenesClick'");
        t.scenes_layout = findRequiredView6;
        this.g = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onScenesClick();
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.firmware_layout, "field 'firmware_layout' and method 'onFirmwareClick'");
        t.firmware_layout = findRequiredView7;
        this.h = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onFirmwareClick();
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.ll_buy_parts, "field 'll_buy_parts' and method 'onBuyPartsClick'");
        t.ll_buy_parts = findRequiredView8;
        this.i = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onBuyPartsClick();
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.feedback_layout, "field 'feedback_layout' and method 'onFeedbackClick'");
        t.feedback_layout = findRequiredView9;
        this.j = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onFeedbackClick();
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.emptyView, "method 'onEmptyClick'");
        this.k = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onEmptyClick();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4439a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.titleBar = null;
        t.category = null;
        t.deviceName = null;
        t.image = null;
        t.listLayout = null;
        t.listView = null;
        t.descLayout = null;
        t.faqLayout = null;
        t.connectLayout = null;
        t.scenes_layout = null;
        t.firmware_layout = null;
        t.ll_buy_parts = null;
        t.feedback_layout = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
        this.h.setOnClickListener(null);
        this.h = null;
        this.i.setOnClickListener(null);
        this.i = null;
        this.j.setOnClickListener(null);
        this.j = null;
        this.k.setOnClickListener(null);
        this.k = null;
        this.f4439a = null;
    }
}
