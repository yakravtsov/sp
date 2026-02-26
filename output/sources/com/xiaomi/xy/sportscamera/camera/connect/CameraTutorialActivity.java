package com.xiaomi.xy.sportscamera.camera.connect;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.fragment.BatteryTipDialog;
import com.ants360.z13.fragment.SNTipDialog;
import com.ants360.z13.util.StatisticHelper;
import com.ants360.z13.util.m;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.xiaomi.xy.sportscamera.R;

/* loaded from: classes3.dex */
public class CameraTutorialActivity extends BaseActivity {
    private a b;
    private TutorialType c = TutorialType.Z13;
    private Handler d = new Handler();

    @BindView(R.id.back)
    ImageView ivBack;

    @BindView(R.id.ivLeftGreen)
    ImageView ivLeftGreen;

    @BindView(R.id.ivMiddleGreen)
    ImageView ivMiddleGreen;

    @BindView(R.id.ivMiddleGreen2)
    ImageView ivMiddleGreen2;

    @BindView(R.id.ivRightGreen)
    ImageView ivRightGreen;

    @BindView(R.id.llLeft)
    LinearLayout llLeft;

    @BindView(R.id.llMiddle)
    LinearLayout llMiddle;

    @BindView(R.id.llMiddle2)
    LinearLayout llMiddle2;

    @BindView(R.id.llRight)
    LinearLayout llRight;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.rlViewPager)
    RelativeLayout rlViewPager;

    @BindView(R.id.rlWebView)
    RelativeLayout rlWebView;

    @BindView(R.id.tvNext)
    TextView tvNext;

    @BindView(R.id.tvStep)
    TextView tvStep;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.webview)
    WebView webview;

    /* loaded from: classes3.dex */
    public enum TutorialType {
        Z13,
        Z16,
        BLUE,
        SCREEN
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class a extends PagerAdapter {
        private View b;
        private View c;
        private View d;
        private View e;
        private View f;
        private View g;
        private View h;
        private View i;

        private a() {
        }

        private View a(int i) {
            switch (i) {
                case 0:
                    if (this.b == null) {
                        this.b = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_z13_1, (ViewGroup) null);
                    }
                    View view = this.b;
                    ImageView imageView = (ImageView) view.findViewById(R.id.ivCamera);
                    imageView.setImageResource(R.drawable.tutorial_z13_step1);
                    ((AnimationDrawable) imageView.getDrawable()).start();
                    a(view, CameraTutorialActivity.this.getString(R.string.tutorial_turn_on_camera));
                    return view;
                case 1:
                    if (this.c == null) {
                        this.c = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_z13_2, (ViewGroup) null);
                    }
                    View view2 = this.c;
                    ImageView imageView2 = (ImageView) view2.findViewById(R.id.ivCamera);
                    imageView2.setImageResource(R.drawable.tutorial_z13_step2);
                    ((AnimationDrawable) imageView2.getDrawable()).start();
                    a(view2, CameraTutorialActivity.this.getString(R.string.tutorial_turn_on_wifi));
                    return view2;
                case 2:
                    if (this.d == null) {
                        this.d = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_z13_3, (ViewGroup) null);
                    }
                    View view3 = this.d;
                    ImageView imageView3 = (ImageView) view3.findViewById(R.id.ivFinger);
                    imageView3.setImageResource(R.drawable.tutorial_z13_step3);
                    ((AnimationDrawable) imageView3.getDrawable()).start();
                    view3.findViewById(R.id.tvSN).setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity.a.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view4) {
                            SNTipDialog sNTipDialog = new SNTipDialog();
                            Bundle bundle = new Bundle();
                            bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
                            bundle.putInt("Image", R.drawable.sn_1);
                            bundle.putString("SN_1", CameraTutorialActivity.this.getString(R.string.tutorial_SN_tip));
                            sNTipDialog.setArguments(bundle);
                            sNTipDialog.a(CameraTutorialActivity.this);
                            StatisticHelper.C();
                        }
                    });
                    view3.findViewById(R.id.tvPsw).setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity.a.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view4) {
                            SNTipDialog sNTipDialog = new SNTipDialog();
                            Bundle bundle = new Bundle();
                            bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
                            bundle.putInt("Image", R.drawable.pic_forget_psw_z13);
                            bundle.putString("SN_1", CameraTutorialActivity.this.getString(R.string.camera_tutorial_default_psw));
                            bundle.putString("SN_2", CameraTutorialActivity.this.getString(R.string.camera_tutorial_reset_psw_z13));
                            sNTipDialog.setArguments(bundle);
                            sNTipDialog.a(CameraTutorialActivity.this);
                            StatisticHelper.C();
                        }
                    });
                    return view3;
                default:
                    return null;
            }
        }

        private void a(View view, int i) {
            ((TextView) view.findViewById(R.id.tvCamera)).setText(i);
        }

        private void a(View view, CharSequence charSequence) {
            ((TextView) view.findViewById(R.id.tvCamera)).setText(charSequence);
        }

        private View b(int i) {
            switch (i) {
                case 0:
                    if (this.e == null) {
                        this.e = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_z16_1, (ViewGroup) null);
                    }
                    View view = this.e;
                    a(view, CameraTutorialActivity.this.getString(R.string.tutorial_turn_on_camera));
                    ImageView imageView = (ImageView) view.findViewById(R.id.ivCamera);
                    imageView.setImageResource(R.drawable.tutorial_z16_step1);
                    ((AnimationDrawable) imageView.getDrawable()).start();
                    view.findViewById(R.id.tvBat).setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity.a.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            BatteryTipDialog batteryTipDialog = new BatteryTipDialog();
                            Bundle bundle = new Bundle();
                            bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
                            batteryTipDialog.setArguments(bundle);
                            batteryTipDialog.a(CameraTutorialActivity.this);
                            StatisticHelper.B();
                        }
                    });
                    return view;
                case 1:
                    if (this.f == null) {
                        this.f = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_z16_2, (ViewGroup) null);
                    }
                    View view2 = this.f;
                    a(view2, R.string.tutorial_z16_step2);
                    ImageView imageView2 = (ImageView) view2.findViewById(R.id.ivCamera);
                    imageView2.setImageResource(R.drawable.tutorial_z16_step2);
                    ((AnimationDrawable) imageView2.getDrawable()).start();
                    return view2;
                case 2:
                    if (this.g == null) {
                        this.g = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_z16_3, (ViewGroup) null);
                    }
                    View view3 = this.g;
                    a(view3, R.string.tutorial_z13_step3);
                    ImageView imageView3 = (ImageView) view3.findViewById(R.id.ivFinger);
                    imageView3.setImageResource(R.drawable.tutorial_z13_step3);
                    ((AnimationDrawable) imageView3.getDrawable()).start();
                    view3.findViewById(R.id.tvSN).setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity.a.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view4) {
                            SNTipDialog sNTipDialog = new SNTipDialog();
                            Bundle bundle = new Bundle();
                            bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
                            bundle.putInt("Image", R.drawable.sn_2);
                            bundle.putString("SN_1", CameraTutorialActivity.this.getString(R.string.tutorial_SN_tip));
                            sNTipDialog.setArguments(bundle);
                            sNTipDialog.a(CameraTutorialActivity.this);
                            StatisticHelper.C();
                        }
                    });
                    view3.findViewById(R.id.tvPsw).setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity.a.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view4) {
                            SNTipDialog sNTipDialog = new SNTipDialog();
                            Bundle bundle = new Bundle();
                            bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
                            if ("cn".equals(m.a())) {
                                bundle.putInt("Image", R.drawable.pic_forget_psw_z16_c);
                            } else {
                                bundle.putInt("Image", R.drawable.pic_forget_psw_z16_e);
                            }
                            bundle.putString("SN_1", CameraTutorialActivity.this.getString(R.string.camera_tutorial_default_psw));
                            bundle.putString("SN_2", CameraTutorialActivity.this.getString(R.string.camera_tutorial_reset_psw_z16));
                            sNTipDialog.setArguments(bundle);
                            sNTipDialog.a(CameraTutorialActivity.this);
                            StatisticHelper.C();
                        }
                    });
                    return view3;
                default:
                    return null;
            }
        }

        private View c(int i) {
            switch (i) {
                case 0:
                    if (this.h == null) {
                        this.h = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_blue_1, (ViewGroup) null);
                    }
                    View view = this.h;
                    ImageView imageView = (ImageView) view.findViewById(R.id.ivCamera);
                    imageView.setImageResource(R.drawable.tutorial_z13_step2);
                    ((AnimationDrawable) imageView.getDrawable()).start();
                    a(view, R.string.tutorial_blue_step1);
                    return view;
                case 1:
                    if (this.i == null) {
                        this.i = LayoutInflater.from(CameraTutorialActivity.this).inflate(R.layout.tutorial_view_blue_2, (ViewGroup) null);
                    }
                    View view2 = this.i;
                    a(view2, R.string.tutorial_blue_step2);
                    return view2;
                default:
                    return null;
            }
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            if (CameraTutorialActivity.this.c == TutorialType.BLUE) {
                return 2;
            }
            return CameraTutorialActivity.this.c == TutorialType.SCREEN ? 8 : 3;
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = null;
            switch (CameraTutorialActivity.this.c) {
                case Z13:
                    view = a(i);
                    break;
                case Z16:
                    view = b(i);
                    break;
                case BLUE:
                    view = c(i);
                    break;
            }
            if (view != null) {
                viewGroup.removeView(view);
                viewGroup.addView(view);
            }
            return view;
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public static String f() {
        return CameraApplication.f1401a.d() ? "http://onlinesup.xiaoyi.com/slidedescription/app/index.html#/android/ch/carousel" : "http://onlinesup.xiaoyi.com/slidedescription/app/index.html#/android/en/carousel";
    }

    private void g() {
        this.b = new a();
        this.viewPager.setAdapter(this.b);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        if (CameraTutorialActivity.this.c == TutorialType.BLUE) {
                            CameraTutorialActivity.this.tvStep.setText("1 / 2");
                        } else if (CameraTutorialActivity.this.c == TutorialType.SCREEN) {
                            CameraTutorialActivity.this.tvStep.setText("1 / 8");
                        } else {
                            CameraTutorialActivity.this.tvStep.setText("1 / 3");
                        }
                        CameraTutorialActivity.this.tvNext.setText(R.string.next);
                        return;
                    case 1:
                        if (CameraTutorialActivity.this.c == TutorialType.BLUE) {
                            CameraTutorialActivity.this.tvStep.setText("2 / 2");
                            CameraTutorialActivity.this.tvNext.setText(R.string.tutorial_know);
                            return;
                        } else if (CameraTutorialActivity.this.c == TutorialType.SCREEN) {
                            CameraTutorialActivity.this.tvStep.setText("2 / 8");
                            CameraTutorialActivity.this.tvNext.setText(R.string.next);
                            return;
                        } else {
                            CameraTutorialActivity.this.tvStep.setText("2 / 3");
                            CameraTutorialActivity.this.tvNext.setText(R.string.next);
                            return;
                        }
                    case 2:
                        if (CameraTutorialActivity.this.c == TutorialType.SCREEN) {
                            CameraTutorialActivity.this.tvStep.setText("3 / 8");
                            CameraTutorialActivity.this.tvNext.setText(R.string.next);
                            return;
                        } else {
                            CameraTutorialActivity.this.tvStep.setText("3 / 3");
                            CameraTutorialActivity.this.tvNext.setText(R.string.tutorial_know);
                            return;
                        }
                    case 3:
                        CameraTutorialActivity.this.tvStep.setText("4 / 8");
                        CameraTutorialActivity.this.tvNext.setText(R.string.next);
                        return;
                    case 4:
                        CameraTutorialActivity.this.tvStep.setText("5 / 8");
                        CameraTutorialActivity.this.tvNext.setText(R.string.next);
                        return;
                    case 5:
                        CameraTutorialActivity.this.tvStep.setText("6 / 8");
                        CameraTutorialActivity.this.tvNext.setText(R.string.next);
                        return;
                    case 6:
                        CameraTutorialActivity.this.tvStep.setText("7 / 8");
                        CameraTutorialActivity.this.tvNext.setText(R.string.next);
                        return;
                    case 7:
                        CameraTutorialActivity.this.tvStep.setText("8 / 8");
                        CameraTutorialActivity.this.tvNext.setText(R.string.tutorial_know);
                        return;
                    default:
                        return;
                }
            }
        });
        h();
        this.llMiddle.setVisibility(0);
    }

    private void h() {
        this.c = TutorialType.Z13;
        l();
        this.rlViewPager.setVisibility(0);
        this.rlWebView.setVisibility(4);
        this.ivLeftGreen.setVisibility(0);
        this.ivMiddleGreen.setVisibility(4);
        this.ivMiddleGreen2.setVisibility(4);
        this.ivRightGreen.setVisibility(4);
    }

    private void i() {
        this.c = TutorialType.Z16;
        l();
        this.rlViewPager.setVisibility(0);
        this.rlWebView.setVisibility(4);
        this.ivLeftGreen.setVisibility(4);
        this.ivMiddleGreen.setVisibility(0);
        this.ivMiddleGreen2.setVisibility(4);
        this.ivRightGreen.setVisibility(4);
    }

    private void j() {
        this.c = TutorialType.BLUE;
        l();
        this.rlViewPager.setVisibility(0);
        this.rlWebView.setVisibility(4);
        this.ivLeftGreen.setVisibility(4);
        this.ivMiddleGreen.setVisibility(4);
        this.ivMiddleGreen2.setVisibility(4);
        this.ivRightGreen.setVisibility(0);
    }

    private void k() {
        this.c = TutorialType.SCREEN;
        this.ivLeftGreen.setVisibility(4);
        this.ivMiddleGreen.setVisibility(4);
        this.ivMiddleGreen2.setVisibility(0);
        this.ivRightGreen.setVisibility(4);
        this.rlViewPager.setVisibility(4);
        this.rlWebView.setVisibility(0);
        this.webview.loadUrl(f());
        WebSettings settings = this.webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setUserAgentString("User-Agent:Android");
        this.webview.setWebViewClient(new WebViewClient() { // from class: com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity.2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                CameraTutorialActivity.this.progressBar.setVisibility(8);
            }
        });
        this.progressBar.setVisibility(0);
    }

    private void l() {
        if (this.c == TutorialType.BLUE) {
            this.tvStep.setText("1 / 2");
        } else if (this.c == TutorialType.SCREEN) {
            this.tvStep.setText("1 / 8");
        } else {
            this.tvStep.setText("1 / 3");
        }
        this.tvNext.setText(R.string.next);
        this.b.notifyDataSetChanged();
        this.viewPager.setAdapter(this.b);
        this.viewPager.setCurrentItem(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.back})
    public void onBackClick() {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_tutorial);
        g();
        int intExtra = getIntent().getIntExtra("SWITCH_TUTORIAL_INDEX", 0);
        if (intExtra == 1) {
            i();
        } else if (intExtra == 2) {
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.llLeft})
    public void onLeftClick() {
        h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.llMiddle2})
    public void onMiddle2Click() {
        k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.llMiddle})
    public void onMiddleClick() {
        i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.tvNext})
    public void onNextClick() {
        int currentItem = this.viewPager.getCurrentItem();
        if ((this.c != TutorialType.BLUE || currentItem >= 1) && ((this.c == TutorialType.BLUE || currentItem >= 2) && (this.c != TutorialType.SCREEN || currentItem >= 7))) {
            finish();
        } else {
            this.viewPager.setCurrentItem(currentItem + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.llRight})
    public void onRightClick() {
        j();
    }
}
