package com.xiaomi.xy.sportscamera.camera.connect;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity;

/* loaded from: classes3.dex */
public class BluetoothTutorialActivity_ViewBinding<T extends BluetoothTutorialActivity> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4568a;
    private View b;
    private View c;

    public BluetoothTutorialActivity_ViewBinding(final T t, View view) {
        this.f4568a = t;
        t.titleBar = (CustomTitleBar) Utils.findRequiredViewAsType(view, R.id.titleBar, "field 'titleBar'", CustomTitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.category, "field 'category' and method 'onCategoryClick'");
        t.category = (TextView) Utils.castView(findRequiredView, R.id.category, "field 'category'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onCategoryClick();
            }
        });
        t.tutorial_view = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.tutorial_view, "field 'tutorial_view'", RecyclerView.class);
        t.list_layout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.list_layout, "field 'list_layout'", LinearLayout.class);
        t.listView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.list_view, "field 'listView'", RecyclerView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.emptyView, "field 'emptyView' and method 'onEmptyViewClick'");
        t.emptyView = findRequiredView2;
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onEmptyViewClick();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4568a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.titleBar = null;
        t.category = null;
        t.tutorial_view = null;
        t.list_layout = null;
        t.listView = null;
        t.emptyView = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.f4568a = null;
    }
}
