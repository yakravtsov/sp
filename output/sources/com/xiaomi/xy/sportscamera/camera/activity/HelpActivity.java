package com.xiaomi.xy.sportscamera.camera.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.v;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.ants.video.f.Functions;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.activity.ShopWebviewActivity;
import com.ants360.z13.activity.SnsWebViewActivity;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.util.StatisticHelper;
import com.ants360.z13.util.m;
import com.ants360.z13.widget.CustomTitleBar;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity;
import com.xiaomi.xy.sportscamera.camera.connect.CameraTutorialActivity;
import com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity;
import com.xiaoyi.camera.d.e;
import com.yiaction.common.util.g;
import de.greenrobot.event.c;
import io.realm.CameraDevice;
import io.realm.CameraDeviceType;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import rx.a.i;
import rx.d;
import rx.j;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class HelpActivity extends BaseActivity {
    private int b;
    private int c;

    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.connect_layout)
    View connectLayout;
    private ArrayList<ArrayList<String>> d;

    @BindView(R.id.desc_layout)
    View descLayout;

    @BindView(R.id.deviceName)
    TextView deviceName;
    private ArrayList<String> e;
    private boolean f;

    @BindView(R.id.faq_layout)
    View faqLayout;

    @BindView(R.id.feedback_layout)
    View feedback_layout;

    @BindView(R.id.firmware_layout)
    View firmware_layout;
    private a g;
    private int h;
    private int i;

    @BindView(R.id.image)
    ImageView image;
    private int j;
    private CameraDevice k;

    @BindView(R.id.list_layout)
    LinearLayout listLayout;

    @BindView(R.id.list_view)
    RecyclerView listView;

    @BindView(R.id.ll_buy_parts)
    View ll_buy_parts;

    @BindView(R.id.scenes_layout)
    View scenes_layout;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    /* loaded from: classes3.dex */
    class a extends RecyclerView.a<b> {
        private ArrayList<String> b;

        a() {
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public int a() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        @Override // android.support.v7.widget.RecyclerView.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b b(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_select, (ViewGroup) null));
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public void a(b bVar, final int i) {
            bVar.n.setText(this.b.get(i));
            if (HelpActivity.this.f) {
                if (HelpActivity.this.b == i) {
                    bVar.n.setTextColor(HelpActivity.this.h);
                    bVar.o.setVisibility(0);
                } else {
                    bVar.n.setTextColor(HelpActivity.this.i);
                    bVar.o.setVisibility(8);
                }
            } else if (HelpActivity.this.c == i) {
                bVar.n.setTextColor(HelpActivity.this.h);
                bVar.o.setVisibility(0);
            } else {
                bVar.n.setTextColor(HelpActivity.this.i);
                bVar.o.setVisibility(8);
            }
            bVar.p.setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (HelpActivity.this.f) {
                        if (HelpActivity.this.b != i) {
                            HelpActivity.this.b = i;
                            HelpActivity.this.c = 0;
                        }
                        HelpActivity.this.f = false;
                        a.this.f();
                        HelpActivity.this.listLayout.setVisibility(8);
                    } else {
                        HelpActivity.this.c = i;
                        a.this.f();
                        HelpActivity.this.listLayout.setVisibility(8);
                    }
                    HelpActivity.this.g();
                }
            });
        }

        public void a(ArrayList<String> arrayList) {
            this.b = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class b extends RecyclerView.t {
        public TextView n;
        public ImageView o;
        public View p;

        public b(View view) {
            super(view);
            this.o = (ImageView) view.findViewById(R.id.select);
            this.n = (TextView) view.findViewById(R.id.title);
            this.p = view;
        }
    }

    private d<Boolean> a(final Context context) {
        return d.a((d.a) new d.a<Boolean>() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity.6
            @Override // rx.a.b
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void call(j<? super Boolean> jVar) {
                g.a(BuildConfig.BUILD_TYPE, "checkNetStatus", new Object[0]);
                if (e.e(context)) {
                    jVar.onNext(true);
                } else {
                    jVar.onError(new Throwable());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.category.setText(this.e.get(this.b));
        this.deviceName.setText(this.d.get(this.b).get(this.c));
        switch (this.b) {
            case 0:
                if (m.b().equals("en")) {
                    if (this.c == 2) {
                        this.image.setImageResource(R.drawable.ic_camera_add_z13);
                        this.j = 10;
                    } else if (this.c == 1) {
                        this.image.setImageResource(R.drawable.ic_camera_add_4k);
                        this.j = 11;
                    } else if (this.c == 0) {
                        this.image.setImageResource(R.drawable.ic_camera_add_4k_p);
                        this.j = 13;
                    } else if (this.c == 5) {
                        this.image.setImageResource(R.drawable.ic_camera_add_yuntai);
                        this.j = 15;
                    } else if (this.c == 3) {
                        this.image.setImageResource(R.drawable.ic_camera_add_1080p);
                        this.j = 16;
                    } else if (this.c == 4) {
                        this.image.setImageResource(R.drawable.ic_camera_add_discovery);
                        this.j = 17;
                    }
                } else if (this.c == 2) {
                    this.image.setImageResource(R.drawable.ic_camera_add_z13);
                    this.j = 10;
                } else if (this.c == 1) {
                    this.image.setImageResource(R.drawable.ic_camera_add_4k);
                    this.j = 11;
                } else if (this.c == 0) {
                    this.image.setImageResource(R.drawable.ic_camera_add_4k_p);
                    this.j = 13;
                } else if (this.c == 4) {
                    this.image.setImageResource(R.drawable.ic_camera_add_yuntai);
                    this.j = 15;
                } else if (this.c == 3) {
                    this.image.setImageResource(R.drawable.ic_camera_add_1080p);
                    this.j = 16;
                }
                if (this.j == 15) {
                    this.descLayout.setVisibility(0);
                    this.faqLayout.setVisibility(0);
                    this.connectLayout.setVisibility(8);
                } else {
                    this.connectLayout.setVisibility(0);
                    this.descLayout.setVisibility(0);
                    this.faqLayout.setVisibility(0);
                }
                this.firmware_layout.setVisibility(8);
                this.scenes_layout.setVisibility(8);
                this.ll_buy_parts.setVisibility(8);
                return;
            case 1:
                this.j = 0;
                if (this.c == 0) {
                    this.image.setImageResource(R.drawable.ic_camera_help_water);
                } else if (this.c == 1) {
                    this.image.setImageResource(R.drawable.ic_camera_help_strap);
                } else if (this.c == 2) {
                    this.image.setImageResource(R.drawable.ic_camera_help_helmet);
                } else if (this.c == 3) {
                    this.image.setImageResource(R.drawable.ic_camera_help_blue);
                    this.j = 12;
                } else if (this.c == 4) {
                    this.image.setImageResource(R.drawable.ic_camera_help_stick);
                }
                if (this.j == 12) {
                    this.connectLayout.setVisibility(0);
                    this.scenes_layout.setVisibility(8);
                    this.ll_buy_parts.setVisibility(0);
                } else {
                    this.connectLayout.setVisibility(8);
                    this.scenes_layout.setVisibility(0);
                    this.ll_buy_parts.setVisibility(0);
                }
                this.descLayout.setVisibility(8);
                this.faqLayout.setVisibility(8);
                this.firmware_layout.setVisibility(8);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        c(getString(R.string.prompt_switching_network));
        CameraApplication.f1401a.c(false);
        c.a().c(new com.xiaoyi.camera.a.a(false));
        a((Context) this).b(rx.android.b.a.a()).a(rx.android.b.a.a()).g(new i<d<? extends Throwable>, d<?>>() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity.5
            @Override // rx.a.i
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public d<?> call(d<? extends Throwable> dVar) {
                g.a(BuildConfig.BUILD_TYPE, "Retry check", new Object[0]);
                return dVar.d(15).b(1L, TimeUnit.SECONDS);
            }
        }).a(rx.android.b.a.a()).a(new rx.a.b<Boolean>() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity.3
            @Override // rx.a.b
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void call(Boolean bool) {
                g.a(BuildConfig.BUILD_TYPE, "switch success", new Object[0]);
                HelpActivity.this.e();
            }
        }, Functions.a(), new rx.a.a() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity.4
            @Override // rx.a.a
            public void a() {
                g.a(BuildConfig.BUILD_TYPE, "switch failed", new Object[0]);
                HelpActivity.this.e();
                Toast.makeText(HelpActivity.this, R.string.network_switch_failed, 0).show();
            }
        });
        return false;
    }

    private void i() {
        String str;
        Object obj;
        str = "N/A";
        String str2 = "N/A";
        Object obj2 = "N/A";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            str = packageInfo != null ? packageInfo.versionName : "N/A";
            str2 = com.yiaction.common.util.i.a().a("LAST_CONNECT_DEVICE_SN");
            if (!TextUtils.isEmpty(str2)) {
                String e = com.xiaomi.xy.sportscamera.camera.d.a().e(str2);
                if (!TextUtils.isEmpty(e) && e.split("_").length > 1) {
                    obj2 = e.split("_")[1];
                }
            }
            obj = Build.BRAND + "  " + Build.MODEL;
        } catch (PackageManager.NameNotFoundException | NumberFormatException e2) {
            e2.printStackTrace();
            obj = "N/A";
        }
        try {
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setData(Uri.parse("mailto:support@yitechnology.com"));
            intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.feedback_email_title));
            intent.putExtra("android.intent.extra.TEXT", getString(R.string.feedback_email_content, new Object[]{obj, str, str2, obj2}));
            Intent.createChooser(intent, getString(R.string.please_select_mail_client));
            startActivity(intent);
        } catch (Exception e3) {
            try {
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.putExtra("android.intent.extra.EMAIL", new String[]{"support@yitechnology.com"});
                intent2.putExtra("android.intent.extra.TEXT", getString(R.string.feedback_email_content, new Object[]{obj, str, str2, obj2}));
                intent2.putExtra("android.intent.extra.SUBJECT", getString(R.string.feedback_email_title));
                intent2.setType("message/rfc882");
                Intent.createChooser(intent2, getString(R.string.please_select_mail_client));
                startActivity(intent2);
            } catch (Exception e4) {
                a(R.string.no_install_email_client);
            }
        }
    }

    public void f() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.please_disconnect_camera));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("right_button", getString(R.string.confirm));
        bundle.putString("left_button", getString(R.string.cancel));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity.2
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                HelpActivity.this.h();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }
        });
        customBottomDialogFragment.a(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.ll_buy_parts})
    public void onBuyPartsClick() {
        Intent intent = new Intent(this, (Class<?>) ShopWebviewActivity.class);
        intent.putExtra("url", "");
        intent.putExtra("title", getString(R.string.buy));
        startActivity(intent);
        StatisticHelper.N();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.category})
    public void onCategoryClick() {
        this.listLayout.setVisibility(0);
        this.f = true;
        this.g.a(this.e);
        this.g.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.connect_layout})
    public void onConnectClick() {
        Intent intent = new Intent(this, (Class<?>) CameraTutorialActivity.class);
        if (this.b == 0) {
            if (m.b().equals("en")) {
                if (this.c == 0 || this.c == 1 || this.c == 3 || this.c == 4) {
                    intent.putExtra("SWITCH_TUTORIAL_INDEX", 1);
                } else if (this.c == 2) {
                    intent.putExtra("SWITCH_TUTORIAL_INDEX", 0);
                } else {
                    intent.putExtra("SWITCH_TUTORIAL_INDEX", 2);
                }
            } else if (this.c == 0 || this.c == 1 || this.c == 3) {
                intent.putExtra("SWITCH_TUTORIAL_INDEX", 1);
            } else if (this.c == 2) {
                intent.putExtra("SWITCH_TUTORIAL_INDEX", 0);
            } else {
                intent.putExtra("SWITCH_TUTORIAL_INDEX", 2);
            }
        } else if (this.b == 1) {
            intent = new Intent(this, (Class<?>) BluetoothTutorialActivity.class);
        }
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_help);
        this.k = (CameraDevice) getIntent().getSerializableExtra(CameraDevice.class.getSimpleName());
        if (this.k != null) {
            if (CameraDeviceType.DeviceType.ACTION_YUNTAI.toString().equals(this.k.realmGet$deviceType())) {
                this.b = 0;
                this.j = 15;
                if (m.b().equals("en")) {
                    this.c = 5;
                } else {
                    this.c = 4;
                }
            } else if (CameraDeviceType.DeviceType.PARTS_BLUETOOTH.toString().equals(this.k.realmGet$deviceType())) {
                this.b = 1;
                this.c = 3;
                this.j = 12;
            } else if (CameraDeviceType.DeviceType.PARTS_STICK.toString().equals(this.k.realmGet$deviceType())) {
                this.b = 1;
                this.c = 4;
            } else if (CameraDeviceType.DeviceType.PARTS_OTHER.toString().equals(this.k.realmGet$deviceType())) {
                this.b = 1;
                this.c = 0;
            }
        }
        this.h = getResources().getColor(R.color.content_text_color);
        this.i = getResources().getColor(R.color.sign_color);
        ((RelativeLayout.LayoutParams) this.titleBar.getRightText().getLayoutParams()).rightMargin = com.yiaction.common.util.b.a(this, 30.0f);
        this.titleBar.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.activity.HelpActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                HelpActivity.this.onBackPressed();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        if (!(CameraApplication.f1401a.c() && com.ants360.z13.module.a.v) && (CameraApplication.f1401a.c() || !com.ants360.z13.module.a.w)) {
            this.feedback_layout.setVisibility(8);
        } else {
            this.feedback_layout.setVisibility(0);
        }
        this.e = new ArrayList<>();
        this.e.add(getString(R.string.camera_connect_camera));
        this.e.add(getString(R.string.camera_connect_camera_parts));
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getString(R.string.camera_connect_camera_4k_plus));
        arrayList.add(getString(R.string.camera_connect_camera_4k));
        arrayList.add(getString(R.string.camera_connect_camera_1));
        arrayList.add(getString(R.string.camera_connect_camera_j11));
        if (m.b().equals("en")) {
            arrayList.add(getString(R.string.camera_connect_camera_j22));
        }
        arrayList.add(getString(R.string.camera_connect_camera_handheld));
        ArrayList<String> arrayList2 = new ArrayList<>(1);
        arrayList2.add(getString(R.string.camera_connect_camera_waterproof));
        arrayList2.add(getString(R.string.camera_connect_camera_strap));
        arrayList2.add(getString(R.string.camera_connect_camera_helmet));
        arrayList2.add(getString(R.string.camera_connect_camera_bluetooth));
        arrayList2.add(getString(R.string.camera_connect_camera_stick));
        this.d = new ArrayList<>(2);
        this.d.add(arrayList);
        this.d.add(arrayList2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.b(1);
        this.listView.setLayoutManager(linearLayoutManager);
        this.listView.setItemAnimator(new v());
        this.listView.setHasFixedSize(true);
        this.g = new a();
        this.g.a(this.e);
        this.listView.setAdapter(this.g);
        g();
        if (e.e(this)) {
            return;
        }
        if (com.xiaoyi.camera.b.b) {
            f();
        } else {
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.desc_layout})
    public void onDescClick() {
        Intent intent = new Intent(this, (Class<?>) SnsWebViewActivity.class);
        intent.putExtra("title", getString(R.string.setting_user_guide));
        intent.putExtra("url", this.j == 10 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/illustrate/1/user/0" : this.j == 14 ? "cn".endsWith(m.a()) ? "http://www.xiaoyi.com/yicam" : "http://www.yitechnology.com/Yimirrorless/instructions/id/8.html" : this.j == 13 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/illustrate/4kplus/user/0" : this.j == 15 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/yuntai/use" : this.j == 16 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/illustrate/11/user/0" : this.j == 17 ? "http://onlinesup.xiaoyi.com/#/" + m.b() + "/illustrate/22/user/1" : "http://onlinesup.xiaoyi.com/#/" + m.a() + "/illustrate/2/user/0");
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.deviceName})
    public void onDeviceClick() {
        this.listLayout.setVisibility(0);
        this.f = false;
        this.g.a(this.d.get(this.b));
        this.g.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.emptyView})
    public void onEmptyClick() {
        this.listLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.faq_layout})
    public void onFaqClick() {
        Intent intent = new Intent(this, (Class<?>) SnsWebViewActivity.class);
        intent.putExtra("title", getString(R.string.setting_common_problem));
        intent.putExtra("url", this.j == 10 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/carema/1" : this.j == 11 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/carema/2" : this.j == 13 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/carema/4kplus" : this.j == 15 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/yuntai" : this.j == 16 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/carema/11" : this.j == 17 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/carema/22" : "http://onlinesup.xiaoyi.com/#/" + m.a() + "/weidan/31");
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.feedback_layout})
    public void onFeedbackClick() {
        i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.firmware_layout})
    public void onFirmwareClick() {
        Intent intent = new Intent(this, (Class<?>) FirmwareListActivity.class);
        if (this.j == 10) {
            intent.putExtra("DOWNLOAD_MODEL", "Z13");
        } else if (this.j == 11) {
            intent.putExtra("DOWNLOAD_MODEL", "Z16");
        } else if (this.j == 13) {
            intent.putExtra("DOWNLOAD_MODEL", "Z18");
        } else if (this.j == 16) {
            intent.putExtra("DOWNLOAD_MODEL", "J11");
        } else if (this.j == 17) {
            intent.putExtra("DOWNLOAD_MODEL", "J22");
        }
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.scenes_layout})
    public void onScenesClick() {
        Intent intent = new Intent(this, (Class<?>) SnsWebViewActivity.class);
        intent.putExtra("title", getString(R.string.parts_user_scenes));
        intent.putExtra("url", this.c == 0 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/scene/1" : this.c == 1 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/scene/2" : this.c == 2 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/scene/3" : this.c == 4 ? "http://onlinesup.xiaoyi.com/#/" + m.a() + "/scene/4" : "http://onlinesup.xiaoyi.com/#/" + m.a() + "/scene/1");
        startActivity(intent);
    }
}
