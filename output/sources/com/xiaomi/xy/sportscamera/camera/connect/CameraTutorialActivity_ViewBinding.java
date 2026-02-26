package com.xiaomi.xy.sportscamera.camera.connect;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity;

/* loaded from: classes3.dex */
public class CameraTutorialActivity_ViewBinding<T extends CameraTutorialActivity> implements Unbinder {

    /* renamed from: a, reason: collision with root package name */
    protected T f4594a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;

    public CameraTutorialActivity_ViewBinding(final T t, View view) {
        this.f4594a = t;
        View findRequiredView = Utils.findRequiredView(view, R.id.back, "field 'ivBack' and method 'onBackClick'");
        t.ivBack = (ImageView) Utils.castView(findRequiredView, R.id.back, "field 'ivBack'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onBackClick();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.llRight, "field 'llRight' and method 'onRightClick'");
        t.llRight = (LinearLayout) Utils.castView(findRequiredView2, R.id.llRight, "field 'llRight'", LinearLayout.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onRightClick();
            }
        });
        t.ivRightGreen = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivRightGreen, "field 'ivRightGreen'", ImageView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.llMiddle, "field 'llMiddle' and method 'onMiddleClick'");
        t.llMiddle = (LinearLayout) Utils.castView(findRequiredView3, R.id.llMiddle, "field 'llMiddle'", LinearLayout.class);
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onMiddleClick();
            }
        });
        t.ivMiddleGreen = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivMiddleGreen, "field 'ivMiddleGreen'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.llMiddle2, "field 'llMiddle2' and method 'onMiddle2Click'");
        t.llMiddle2 = (LinearLayout) Utils.castView(findRequiredView4, R.id.llMiddle2, "field 'llMiddle2'", LinearLayout.class);
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onMiddle2Click();
            }
        });
        t.ivMiddleGreen2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivMiddleGreen2, "field 'ivMiddleGreen2'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.llLeft, "field 'llLeft' and method 'onLeftClick'");
        t.llLeft = (LinearLayout) Utils.castView(findRequiredView5, R.id.llLeft, "field 'llLeft'", LinearLayout.class);
        this.f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onLeftClick();
            }
        });
        t.ivLeftGreen = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivLeftGreen, "field 'ivLeftGreen'", ImageView.class);
        t.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewPager, "field 'viewPager'", ViewPager.class);
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tvNext, "field 'tvNext' and method 'onNextClick'");
        t.tvNext = (TextView) Utils.castView(findRequiredView6, R.id.tvNext, "field 'tvNext'", TextView.class);
        this.g = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                t.onNextClick();
            }
        });
        t.tvStep = (TextView) Utils.findRequiredViewAsType(view, R.id.tvStep, "field 'tvStep'", TextView.class);
        t.rlViewPager = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rlViewPager, "field 'rlViewPager'", RelativeLayout.class);
        t.rlWebView = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rlWebView, "field 'rlWebView'", RelativeLayout.class);
        t.webview = (WebView) Utils.findRequiredViewAsType(view, R.id.webview, "field 'webview'", WebView.class);
        t.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        T t = this.f4594a;
        if (t == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        t.ivBack = null;
        t.llRight = null;
        t.ivRightGreen = null;
        t.llMiddle = null;
        t.ivMiddleGreen = null;
        t.llMiddle2 = null;
        t.ivMiddleGreen2 = null;
        t.llLeft = null;
        t.ivLeftGreen = null;
        t.viewPager = null;
        t.tvNext = null;
        t.tvStep = null;
        t.rlViewPager = null;
        t.rlWebView = null;
        t.webview = null;
        t.progressBar = null;
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
        this.f4594a = null;
    }
}
