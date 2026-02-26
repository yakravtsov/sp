package com.xiaomi.xy.sportscamera.camera.connect;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.adapter.a;
import com.ants360.z13.album.CameraAlbumActivity;
import com.ants360.z13.fragment.InputWifiPasswordFragment;
import com.ants360.z13.util.StatisticHelper;
import com.ants360.z13.util.m;
import com.ants360.z13.util.y;
import com.ants360.z13.widget.CustomTitleBar;
import com.rp.rptool.util.RPJni;
import com.sina.weibo.sdk.statistic.StatisticConfig;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.activity.CameraActivity;
import com.xiaomi.xy.sportscamera.camera.activity.HelpActivity;
import com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity;
import com.xiaomi.xy.sportscamera.camera.d;
import com.xiaoyi.camera.controller.a;
import com.xiaoyi.camera.controller.b;
import com.xiaoyi.camera.d.f;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import io.realm.CameraDevice;
import io.realm.CameraDeviceType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class QZCameraActionConnectActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, a.b, b.InterfaceC0208b {
    private String b;
    private CameraDevice c;

    @BindView(R.id.contact_customer_service)
    TextView contact_customer_service;
    private com.xiaoyi.camera.controller.a d;

    @BindView(R.id.deviceVP)
    ViewPager deviceVP;
    private com.xiaoyi.camera.controller.b e;
    private b f;
    private InputWifiPasswordFragment g;
    private com.xiaoyi.camera.devicedao.a h;

    @BindView(R.id.ivLoad)
    ImageView ivLoad;

    @BindView(R.id.ivModel)
    ImageView ivModel;

    @BindView(R.id.iv_connect_fail)
    ImageView iv_connect_fail;
    private List<Integer> k;
    private List<Integer> l;

    @BindView(R.id.lvCameraList)
    ListView mListView;

    @BindView(R.id.rlModel)
    RelativeLayout rlModel;

    @BindView(R.id.rl_connect_fail)
    RelativeLayout rl_connect_fail;

    @BindView(R.id.rl_connect_status)
    RelativeLayout rl_connect_status;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    @BindView(R.id.tvNeedSupport)
    TextView tvNeedSupport;

    @BindView(R.id.tvNext)
    TextView tvNext;

    @BindView(R.id.tvRemind)
    TextView tvRemind;

    @BindView(R.id.tvRemindDesc)
    TextView tvRemindDesc;

    @BindView(R.id.tv_connect_fail)
    TextView tv_connect_fail;
    private Handler i = new Handler(Looper.getMainLooper());
    private ConnectStatus j = ConnectStatus.STATUS_OPEN_DEVICE;
    private boolean m = true;
    private boolean n = false;
    private ViewPager.OnPageChangeListener o = new ViewPager.OnPageChangeListener() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.6
        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            QZCameraActionConnectActivity.this.tvRemind.setText(((Integer) QZCameraActionConnectActivity.this.l.get(i)).intValue());
            if (i == 0) {
                QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_OPEN_DEVICE;
            } else {
                QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_OPEN_WIFI;
            }
        }
    };
    private Runnable p = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.12
        @Override // java.lang.Runnable
        public void run() {
            QZCameraActionConnectActivity.this.a(QZCameraActionConnectActivity.this.h, StatisticConfig.MIN_UPLOAD_INTERVAL);
        }
    };
    private BroadcastReceiver q = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.13
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            g.a(BuildConfig.BUILD_TYPE, "------------------- action = " + action, new Object[0]);
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("wifi_state", 0);
                g.a(BuildConfig.BUILD_TYPE, "------------------- state = " + intExtra, new Object[0]);
                if (intExtra == 11) {
                    QZCameraActionConnectActivity.this.unregisterReceiver(this);
                    QZCameraActionConnectActivity.this.f();
                }
            }
        }
    };

    /* loaded from: classes3.dex */
    public enum ConnectStatus {
        STATUS_OPEN_DEVICE,
        STATUS_OPEN_WIFI,
        STATUS_SEARCH,
        STATUS_SEARCH_FAIL,
        STATUS_LIST,
        STATUS_CONNECT,
        STATUS_CONNECT_RETRY,
        STATUS_CONNECT_FAIL,
        STATUS_SUCCESS
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class a extends PagerAdapter {
        private a() {
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return QZCameraActionConnectActivity.this.k.size();
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(QZCameraActionConnectActivity.this).inflate(R.layout.activity_connect_remind_item, (ViewGroup) null);
            ((RelativeLayout) inflate.findViewById(R.id.rlModel)).setVisibility(0);
            ((ImageView) inflate.findViewById(R.id.ivModel)).setImageResource(((Integer) QZCameraActionConnectActivity.this.k.get(i)).intValue());
            ((TextView) inflate.findViewById(R.id.tvRemind)).setText(((Integer) QZCameraActionConnectActivity.this.l.get(i)).intValue());
            if (inflate != null) {
                viewGroup.removeView(inflate);
                viewGroup.addView(inflate);
            }
            return inflate;
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    /* loaded from: classes3.dex */
    public class b extends com.ants360.z13.adapter.a {
        private List<ScanResult> c;

        public b() {
            super(R.layout.activity_camera_action_wifi_item);
            this.c = new ArrayList();
        }

        public void a(List<ScanResult> list) {
            this.c.clear();
            this.c.addAll(list);
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public int getCount() {
            return this.c.size();
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public Object getItem(int i) {
            return this.c.get(i);
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            a.C0047a c0047a;
            ScanResult scanResult = this.c.get(i);
            if (view == null) {
                View inflate = View.inflate(QZCameraActionConnectActivity.this, this.f1493a, null);
                c0047a = new a.C0047a(inflate);
                inflate.setTag(c0047a);
            } else {
                c0047a = (a.C0047a) view.getTag();
            }
            c0047a.b(R.id.tvWifiName).setText(scanResult.SSID);
            ImageView c = c0047a.c(R.id.ivMatch);
            if (QZCameraActionConnectActivity.this.d == null || !QZCameraActionConnectActivity.this.d.a(scanResult)) {
                c.setVisibility(8);
            } else {
                c.setVisibility(0);
            }
            return c0047a.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ScanResult scanResult) {
        com.xiaoyi.camera.b.b = false;
        CameraApplication.f1401a.d(false);
        CameraApplication.f1401a.c(false);
        String str = scanResult.SSID;
        String str2 = scanResult.BSSID;
        g.a("debug_wifi", "CameraActionConnectActivity connectWifi : ssid = " + str, new Object[0]);
        g.a("debug_wifi", "CameraActionConnectActivity connectWifi : macAdress = " + str2, new Object[0]);
        if (this.d.a(scanResult)) {
            this.h = this.d.b(scanResult);
            g.a("debug_wifi", "CameraActionConnectActivity connectWifi : UserCamera = " + this.h.b(), new Object[0]);
            if (!this.h.b().equals(str)) {
                this.d.a(str);
            }
            this.h.b(str);
            this.h.a(str2);
        } else {
            this.h = new com.xiaoyi.camera.devicedao.a(str2, str, "1234567890");
        }
        if (this.e != null) {
            this.e.a();
        }
        g.a("debug_wifi", "------------- currentWifiInfo : Password = " + this.h.c(), new Object[0]);
        a(this.h);
        this.j = ConnectStatus.STATUS_CONNECT;
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TextView textView) {
        textView.setText(Html.fromHtml(getString(R.string.connect_fail_try_method_1) + "<br／>" + getString(R.string.connect_fail_try_method_2) + "<br／>" + getString(R.string.connect_fail_try_method_3_1) + "<a href='lianjie'>" + getString(R.string.connect_fail_try_method_3_2) + "</a>" + getString(R.string.connect_fail_try_method_3_3) + "<br／>" + getString(R.string.connect_fail_try_method_4) + "<br／>" + getString(R.string.connect_fail_try_method_5)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            int length = text.length();
            Spannable spannable = (Spannable) textView.getText();
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            spannableStringBuilder.clearSpans();
            for (URLSpan uRLSpan : uRLSpanArr) {
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.4
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        QZCameraActionConnectActivity.this.n = true;
                        QZCameraActionConnectActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                    }
                }, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 34);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.primary_green)), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 34);
            }
            textView.setText(spannableStringBuilder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        try {
            Drawable drawable = this.ivLoad.getDrawable();
            if (drawable != null && (drawable instanceof AnimationDrawable)) {
                if (z) {
                    this.ivLoad.setVisibility(0);
                    ((AnimationDrawable) drawable).start();
                } else {
                    this.ivLoad.setVisibility(8);
                    ((AnimationDrawable) drawable).stop();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.2
            @Override // java.lang.Runnable
            public void run() {
                QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(0);
                String string = QZCameraActionConnectActivity.this.getString(R.string.cant_start_session_failed_solution);
                if (i == -21) {
                    string = QZCameraActionConnectActivity.this.getString(R.string.start_session_failed_in_systemBusy);
                } else if (i == -1102) {
                    string = QZCameraActionConnectActivity.this.getString(R.string.camera_album_operation_ing);
                    QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                } else if (i == -1100 || i == -1101) {
                    string = QZCameraActionConnectActivity.this.getString(R.string.start_session_failed);
                } else if (i == -3) {
                    QZCameraActionConnectActivity.this.tvNeedSupport.setVisibility(8);
                } else if (i == -1103) {
                    string = QZCameraActionConnectActivity.this.getString(R.string.camera_sdcard_optimize_start);
                    QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                } else if (i == -1104) {
                    string = QZCameraActionConnectActivity.this.getString(R.string.camera_start_usb_storage_mode);
                    QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                } else if (i == -1105) {
                    string = QZCameraActionConnectActivity.this.getString(R.string.camera_voice_control);
                    QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                } else if (i == -1106) {
                    string = QZCameraActionConnectActivity.this.getString(R.string.camera_live_mode);
                    QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                }
                QZCameraActionConnectActivity.this.tvRemind.setText(string);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        this.tvRemindDesc.setText(Html.fromHtml(str + "</br><br/>" + getString(R.string.camera_connect_or) + "</br><br/><a href='lianjie'>" + getString(R.string.camera_connect_from_system_and_back) + "</a>"));
        this.tvRemindDesc.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = this.tvRemindDesc.getText();
        if (text instanceof Spannable) {
            int length = text.length();
            Spannable spannable = (Spannable) this.tvRemindDesc.getText();
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            spannableStringBuilder.clearSpans();
            for (URLSpan uRLSpan : uRLSpanArr) {
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.3
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        QZCameraActionConnectActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                    }
                }, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 34);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.primary_grey)), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 34);
            }
            this.tvRemindDesc.setText(spannableStringBuilder);
        }
    }

    private void i() {
        if (CameraDeviceType.CameraType.CAMERA_ACTION.toString().equals(this.c.realmGet$cameraType())) {
            if (CameraDeviceType.DeviceType.ACTION_Z13.toString().equals(this.c.realmGet$deviceType())) {
                this.k = Arrays.asList(Integer.valueOf(R.drawable.ic_camera_connect_open_z13), Integer.valueOf(R.drawable.ic_camera_connect_open_z13_wifi));
                this.l = Arrays.asList(Integer.valueOf(R.string.camera_action_open_camera), Integer.valueOf(R.string.camera_action_open_wifi));
                this.ivModel.setImageResource(R.drawable.ic_camera_connect_status_z13);
            } else if (CameraDeviceType.DeviceType.ACTION_4K.toString().equals(this.c.realmGet$deviceType())) {
                Integer[] numArr = new Integer[2];
                numArr[0] = Integer.valueOf(R.drawable.ic_camera_connect_open_4k);
                numArr[1] = Integer.valueOf("cn".equals(m.a()) ? R.drawable.ic_camera_connect_open_4k_wifi_2_c : R.drawable.ic_camera_connect_open_4k_wifi_2_e);
                this.k = Arrays.asList(numArr);
                this.l = Arrays.asList(Integer.valueOf(R.string.camera_action_open_camera), Integer.valueOf(R.string.camera_action_open_wifi));
                this.ivModel.setImageResource(R.drawable.ic_camera_connect_status_4k);
            } else if (CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(this.c.realmGet$deviceType())) {
                Integer[] numArr2 = new Integer[2];
                numArr2[0] = Integer.valueOf(R.drawable.ic_camera_connect_open_4kp);
                numArr2[1] = Integer.valueOf("cn".equals(m.a()) ? R.drawable.ic_camera_connect_open_4k_wifi_2_c : R.drawable.ic_camera_connect_open_4k_wifi_2_e);
                this.k = Arrays.asList(numArr2);
                this.l = Arrays.asList(Integer.valueOf(R.string.camera_action_open_camera), Integer.valueOf(R.string.camera_action_open_wifi));
                this.ivModel.setImageResource(R.drawable.ic_camera_connect_status_4kp);
            } else if (CameraDeviceType.DeviceType.ACTION_J11.toString().equals(this.c.realmGet$deviceType())) {
                Integer[] numArr3 = new Integer[2];
                numArr3[0] = Integer.valueOf(R.drawable.ic_camera_connect_open_4k);
                numArr3[1] = Integer.valueOf("cn".equals(m.a()) ? R.drawable.ic_camera_connect_open_lite_wifi_c : R.drawable.ic_camera_connect_open_lite_wifi_e);
                this.k = Arrays.asList(numArr3);
                this.l = Arrays.asList(Integer.valueOf(R.string.camera_action_open_camera), Integer.valueOf(R.string.camera_action_open_wifi));
                this.ivModel.setImageResource(R.drawable.ic_camera_connect_status_1080);
            } else if (CameraDeviceType.DeviceType.ACTION_J22.toString().equals(this.c.realmGet$deviceType())) {
                Integer[] numArr4 = new Integer[2];
                numArr4[0] = Integer.valueOf(R.drawable.ic_camera_connect_open_4k);
                numArr4[1] = Integer.valueOf("cn".equals(m.a()) ? R.drawable.ic_camera_connect_open_discovery_wifi_c : R.drawable.ic_camera_connect_open_discovery_wifi_e);
                this.k = Arrays.asList(numArr4);
                this.l = Arrays.asList(Integer.valueOf(R.string.camera_action_open_camera), Integer.valueOf(R.string.camera_action_open_wifi));
                this.ivModel.setImageResource(R.drawable.ic_camera_connect_status_discovery);
            }
        }
        this.deviceVP.setAdapter(new a());
        this.deviceVP.addOnPageChangeListener(this.o);
        this.deviceVP.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        runOnUiThread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.7
            @Override // java.lang.Runnable
            public void run() {
                switch (QZCameraActionConnectActivity.this.j) {
                    case STATUS_OPEN_DEVICE:
                        QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_OPEN_WIFI;
                        QZCameraActionConnectActivity.this.deviceVP.setCurrentItem(QZCameraActionConnectActivity.this.deviceVP.getCurrentItem() + 1);
                        return;
                    case STATUS_OPEN_WIFI:
                        if (CameraDeviceType.CameraType.CAMERA_ACTION.toString().equals(QZCameraActionConnectActivity.this.c.realmGet$cameraType())) {
                            if (CameraDeviceType.DeviceType.ACTION_Z13.toString().equals(QZCameraActionConnectActivity.this.c.realmGet$deviceType())) {
                                QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_SEARCH;
                                QZCameraActionConnectActivity.this.j();
                                return;
                            } else {
                                if (QZCameraActionConnectActivity.this.deviceVP.getCurrentItem() != QZCameraActionConnectActivity.this.k.size() - 1) {
                                    QZCameraActionConnectActivity.this.deviceVP.setCurrentItem(QZCameraActionConnectActivity.this.deviceVP.getCurrentItem() + 1);
                                    return;
                                }
                                QZCameraActionConnectActivity.this.n = true;
                                QZCameraActionConnectActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                                return;
                            }
                        }
                        return;
                    case STATUS_SEARCH:
                        QZCameraActionConnectActivity.this.rl_connect_status.setVisibility(0);
                        QZCameraActionConnectActivity.this.rl_connect_fail.setVisibility(8);
                        QZCameraActionConnectActivity.this.titleBar.setMiddleTitle("");
                        QZCameraActionConnectActivity.this.deviceVP.setVisibility(8);
                        QZCameraActionConnectActivity.this.rlModel.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvRemind.setText(R.string.camera_action_search_wifi);
                        QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                        QZCameraActionConnectActivity.this.tvNeedSupport.setVisibility(8);
                        QZCameraActionConnectActivity.this.tvNext.setVisibility(8);
                        QZCameraActionConnectActivity.this.ivLoad.setImageResource(R.drawable.yi_camera_loading);
                        QZCameraActionConnectActivity.this.b(true);
                        if (QZCameraActionConnectActivity.this.h()) {
                            return;
                        }
                        QZCameraActionConnectActivity.this.f();
                        return;
                    case STATUS_SEARCH_FAIL:
                        QZCameraActionConnectActivity.this.tvRemind.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvRemind.setText(R.string.camera_action_search_wifi_no);
                        QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(0);
                        QZCameraActionConnectActivity.this.d(QZCameraActionConnectActivity.this.getString(R.string.camera_connect_search_remind_desc));
                        QZCameraActionConnectActivity.this.tvNeedSupport.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvNext.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvNext.setText(R.string.camera_action_search_wifi_retry);
                        QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_SEARCH;
                        QZCameraActionConnectActivity.this.b(false);
                        QZCameraActionConnectActivity.this.titleBar.setMiddleTitle(R.string.camera_connect_search_timeout);
                        QZCameraActionConnectActivity.this.rl_connect_status.setVisibility(8);
                        QZCameraActionConnectActivity.this.rl_connect_fail.setVisibility(0);
                        QZCameraActionConnectActivity.this.iv_connect_fail.setImageResource(R.drawable.ic_search_fail);
                        QZCameraActionConnectActivity.this.tv_connect_fail.setText(R.string.search_fail_try_method);
                        return;
                    case STATUS_LIST:
                        QZCameraActionConnectActivity.this.tvRemind.setVisibility(4);
                        QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                        QZCameraActionConnectActivity.this.mListView.setVisibility(0);
                        QZCameraActionConnectActivity.this.b(false);
                        QZCameraActionConnectActivity.this.tvNext.setVisibility(8);
                        QZCameraActionConnectActivity.this.tvNeedSupport.setVisibility(8);
                        return;
                    case STATUS_CONNECT:
                        QZCameraActionConnectActivity.this.rl_connect_status.setVisibility(0);
                        QZCameraActionConnectActivity.this.rl_connect_fail.setVisibility(8);
                        QZCameraActionConnectActivity.this.contact_customer_service.setVisibility(8);
                        QZCameraActionConnectActivity.this.titleBar.setMiddleTitle("");
                        QZCameraActionConnectActivity.this.tvRemind.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvRemind.setText(R.string.camera_action_connect_device);
                        QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(8);
                        QZCameraActionConnectActivity.this.mListView.setVisibility(8);
                        QZCameraActionConnectActivity.this.b(true);
                        QZCameraActionConnectActivity.this.tvNext.setVisibility(8);
                        QZCameraActionConnectActivity.this.tvNeedSupport.setVisibility(8);
                        if (QZCameraActionConnectActivity.this.e != null) {
                            QZCameraActionConnectActivity.this.e.a();
                        }
                        QZCameraActionConnectActivity.this.i.postDelayed(QZCameraActionConnectActivity.this.p, StatisticConfig.MIN_UPLOAD_INTERVAL);
                        return;
                    case STATUS_CONNECT_RETRY:
                        QZCameraActionConnectActivity.this.a(QZCameraActionConnectActivity.this.h);
                        QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_CONNECT;
                        QZCameraActionConnectActivity.this.j();
                        g.a("debug_wifi", "------------- mStatus = STATUS_CONNECT_RETRY", new Object[0]);
                        return;
                    case STATUS_CONNECT_FAIL:
                        QZCameraActionConnectActivity.this.mListView.setVisibility(8);
                        QZCameraActionConnectActivity.this.tvRemind.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvRemind.setText(R.string.camera_action_connect_device_fail);
                        QZCameraActionConnectActivity.this.tvRemindDesc.setVisibility(0);
                        QZCameraActionConnectActivity.this.d(QZCameraActionConnectActivity.this.getString(R.string.camera_connect_connect_fail_remind_desc));
                        QZCameraActionConnectActivity.this.tvNeedSupport.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvNext.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvNext.setText(R.string.camera_action_connect_device_retry);
                        QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_CONNECT_RETRY;
                        QZCameraActionConnectActivity.this.b(false);
                        com.xiaoyi.camera.b.b = false;
                        if (QZCameraActionConnectActivity.this.d != null) {
                            QZCameraActionConnectActivity.this.d.a(true);
                            QZCameraActionConnectActivity.this.d.c(true);
                            QZCameraActionConnectActivity.this.d.a();
                        }
                        QZCameraActionConnectActivity.this.i.removeCallbacks(QZCameraActionConnectActivity.this.p);
                        QZCameraActionConnectActivity.this.titleBar.setMiddleTitle(R.string.camera_connect_connect_fail);
                        QZCameraActionConnectActivity.this.rl_connect_status.setVisibility(8);
                        QZCameraActionConnectActivity.this.rl_connect_fail.setVisibility(0);
                        QZCameraActionConnectActivity.this.contact_customer_service.setVisibility(0);
                        QZCameraActionConnectActivity.this.iv_connect_fail.setImageResource(R.drawable.ic_connect_fail);
                        QZCameraActionConnectActivity.this.a(QZCameraActionConnectActivity.this.tv_connect_fail);
                        return;
                    case STATUS_SUCCESS:
                        QZCameraActionConnectActivity.this.tvRemind.setVisibility(0);
                        QZCameraActionConnectActivity.this.tvRemind.setText(R.string.camera_action_connect_device_success);
                        QZCameraActionConnectActivity.this.mListView.setVisibility(8);
                        QZCameraActionConnectActivity.this.b(false);
                        QZCameraActionConnectActivity.this.ivLoad.setVisibility(0);
                        QZCameraActionConnectActivity.this.ivLoad.setImageResource(R.drawable.ic_camera_connect_success);
                        QZCameraActionConnectActivity.this.i.removeCallbacks(QZCameraActionConnectActivity.this.p);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void k() {
        Bundle bundle = new Bundle();
        bundle.putString("title", this.h.b());
        if (this.g == null || this.g.getDialog() == null || !this.g.getDialog().isShowing()) {
            g.a("debug_wifi", "------------- showInputPasswordDialog", new Object[0]);
            this.g = (InputWifiPasswordFragment) InputWifiPasswordFragment.instantiate(this, InputWifiPasswordFragment.class.getName(), bundle);
            this.g.a(new InputWifiPasswordFragment.a() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.11
                @Override // com.ants360.z13.fragment.InputWifiPasswordFragment.a
                public void a(DialogFragment dialogFragment) {
                    QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_CONNECT_FAIL;
                    QZCameraActionConnectActivity.this.j();
                    QZCameraActionConnectActivity.this.c(-1100);
                }

                @Override // com.ants360.z13.fragment.InputWifiPasswordFragment.a
                public void a(DialogFragment dialogFragment, String str) {
                    QZCameraActionConnectActivity.this.h.c(str);
                    if (QZCameraActionConnectActivity.this.d != null) {
                        g.a("debug_wifi", "------------- showInputPasswordDialog", new Object[0]);
                        QZCameraActionConnectActivity.this.a(QZCameraActionConnectActivity.this.h);
                    }
                    QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_CONNECT;
                    QZCameraActionConnectActivity.this.j();
                }
            });
            this.g.a(this);
        }
    }

    @Override // com.xiaoyi.camera.controller.b.InterfaceC0208b
    public void a(long j) {
        this.j = ConnectStatus.STATUS_SEARCH_FAIL;
        j();
        UploadStatsManager.a("connection", "scan_camera_time_out", j);
        UploadStatsManager.a("connection", "scan_result", "scan_camera_time_out");
    }

    @Override // com.xiaoyi.camera.controller.b.InterfaceC0208b
    public void a(long j, int i) {
        UploadStatsManager.a("connection", "scan_camera_used_time", j);
        UploadStatsManager.a("connection", "scan_camera_found_account", i);
        UploadStatsManager.a("connection", "scan_result", "scan_found");
    }

    public void a(com.xiaoyi.camera.devicedao.a aVar) {
        com.ants360.z13.b.a.a();
        this.d.c(false);
        this.d.a(aVar);
    }

    @Override // com.xiaoyi.camera.controller.a.b
    public void a(com.xiaoyi.camera.devicedao.a aVar, int i, long j) {
        this.h = aVar;
        this.j = ConnectStatus.STATUS_CONNECT_FAIL;
        j();
        c(i);
        if (TextUtils.isEmpty(aVar.a())) {
            return;
        }
        StatisticHelper.c(aVar.a(), j);
    }

    @Override // com.xiaoyi.camera.controller.a.b
    public void a(com.xiaoyi.camera.devicedao.a aVar, long j) {
        this.j = ConnectStatus.STATUS_CONNECT_FAIL;
        j();
        j();
        this.tvRemind.setText(R.string.start_session_timeout);
        if (aVar != null) {
            this.h = aVar;
            if (TextUtils.isEmpty(aVar.a())) {
                return;
            }
            StatisticHelper.e(aVar.a(), j);
        }
    }

    @Override // com.xiaoyi.camera.controller.b.InterfaceC0208b
    public void a(final List<ScanResult> list) {
        this.i.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.8
            @Override // java.lang.Runnable
            public void run() {
                String realmGet$cameraType = QZCameraActionConnectActivity.this.c.realmGet$cameraType();
                String realmGet$deviceMac = QZCameraActionConnectActivity.this.c.realmGet$deviceMac();
                if (CameraDeviceType.CameraType.CAMERA_ACTION.toString().equals(realmGet$cameraType)) {
                    g.a("debug_wifi", "--------------------------------- list.size() = " + list.size(), new Object[0]);
                    if (!TextUtils.isEmpty(realmGet$deviceMac)) {
                        for (ScanResult scanResult : list) {
                            if (realmGet$deviceMac.equals(scanResult.BSSID)) {
                                QZCameraActionConnectActivity.this.a(scanResult);
                                return;
                            }
                        }
                        return;
                    }
                    QZCameraActionConnectActivity.this.j = ConnectStatus.STATUS_LIST;
                    QZCameraActionConnectActivity.this.j();
                    if (QZCameraActionConnectActivity.this.f != null) {
                        QZCameraActionConnectActivity.this.f.a(list);
                        QZCameraActionConnectActivity.this.f.notifyDataSetChanged();
                    } else {
                        QZCameraActionConnectActivity.this.f = new b();
                        QZCameraActionConnectActivity.this.f.a(list);
                        QZCameraActionConnectActivity.this.mListView.setAdapter((ListAdapter) QZCameraActionConnectActivity.this.f);
                    }
                }
            }
        });
    }

    @Override // com.xiaoyi.camera.controller.a.b
    public void a(boolean z, com.xiaoyi.camera.devicedao.a aVar, int i, long j) {
        this.j = ConnectStatus.STATUS_CONNECT_FAIL;
        j();
        c(i);
        StatisticHelper.a(z, aVar.a(), j);
    }

    @Override // com.xiaoyi.camera.controller.a.b
    public void b(com.xiaoyi.camera.devicedao.a aVar, long j) {
        this.j = ConnectStatus.STATUS_SUCCESS;
        j();
        if (this.e != null) {
            this.e.a();
        }
        if (this.d != null) {
            this.d.a();
        }
        i.a().a("already_remind_upgrade", false);
        i.a().a("current_operation_model", -1);
        i.a().a("video_photo_path", "");
        i.a().a("already_init_time", false);
        CameraApplication.f1401a.c(true);
        CameraApplication.f1401a.g();
        CameraApplication.f1401a.d(true);
        com.xiaoyi.camera.module.a.a(com.xiaoyi.camera.g.a().a("sw_version"), com.xiaoyi.camera.g.a().a("hw_version"));
        d a2 = d.a();
        String a3 = com.xiaoyi.camera.g.a().a("serial_number");
        String a4 = com.xiaoyi.camera.g.a().a("sw_version");
        String a5 = com.xiaoyi.camera.g.a().a("hw_version");
        a2.c(a3, a4);
        com.xiaoyi.camera.d.b bVar = new com.xiaoyi.camera.d.b();
        CameraDevice cameraDevice = null;
        if ("Z13".equals(com.xiaoyi.camera.g.a().a("model"))) {
            cameraDevice = new CameraDevice(a3, aVar.a(), getString(R.string.camera_connect_camera_1), CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_Z13.toString(), a4, a5, com.xiaoyi.camera.g.a().a("wifi_password"));
        } else if ("Z16".equals(com.xiaoyi.camera.g.a().a("model"))) {
            cameraDevice = new CameraDevice(a3, aVar.a(), getString(R.string.camera_connect_camera_4k), CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_4K.toString(), a4, a5, com.xiaoyi.camera.g.a().a("wifi_password"));
        } else if ("Z18".equals(com.xiaoyi.camera.g.a().a("model"))) {
            cameraDevice = new CameraDevice(a3, aVar.a(), getString(R.string.camera_connect_camera_4k_plus), CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_4K_P.toString(), a4, a5, com.xiaoyi.camera.g.a().a("wifi_password"));
        } else if ("J11".equals(com.xiaoyi.camera.g.a().a("model"))) {
            cameraDevice = new CameraDevice(a3, aVar.a(), getString(R.string.camera_connect_camera_j11), CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_J11.toString(), a4, a5, com.xiaoyi.camera.g.a().a("wifi_password"));
        } else if ("J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
            cameraDevice = new CameraDevice(a3, aVar.a(), getString(R.string.camera_connect_camera_j22), CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_J22.toString(), a4, a5, com.xiaoyi.camera.g.a().a("wifi_password"));
        }
        bVar.a(cameraDevice);
        bVar.a();
        g.a("debug_switch_fragment", "invalidateOptionsMenu when connect succeeded", new Object[0]);
        if (cameraDevice != null) {
            i.a().a("LAST_CONNECT_CAMERA", cameraDevice.realmGet$cameraType());
            i.a().a("LAST_CONNECT_DEVICE", cameraDevice.realmGet$deviceType());
        } else {
            i.a().a("LAST_CONNECT_CAMERA", CameraDeviceType.CameraType.CAMERA_ACTION.toString());
            i.a().a("LAST_CONNECT_DEVICE", CameraDeviceType.DeviceType.ACTION_Z13.toString());
        }
        i.a().a("LAST_CONNECT_DEVICE_SN", a3);
        if (aVar != null) {
            com.ants360.z13.b.a.a(aVar);
            com.xiaoyi.camera.d.g.a().a(aVar.a(), 1);
            i.a().a(aVar.a(), com.xiaoyi.camera.g.a().a("model"));
        }
        new Thread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.9
            @Override // java.lang.Runnable
            public void run() {
                com.xiaoyi.camera.module.b.b();
                Map<String, String> f = com.xiaoyi.camera.g.a().f();
                if (f.isEmpty()) {
                    return;
                }
                for (Map.Entry<String, String> entry : f.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    g.a(BuildConfig.BUILD_TYPE, "----------------------------- key " + key + "    value = " + value, new Object[0]);
                    if (!TextUtils.isEmpty(value) && !"wifi_ssid".equals(key) && !"wifi_password".equals(key) && !"app_status".equals(key) && !"camera_clock".equals(key) && !"sta_connect_password".equals(key) && !"sta_ssid".equals(key) && !"sta_password".equals(key) && !"sta_ip".equals(key) && !"buzzer_ring".equals(key)) {
                        if ("serial_number".equals(key) && value.length() > 7) {
                            value = value.substring(0, value.length() - 7);
                        }
                        StatisticHelper.c(key, value);
                    }
                }
            }
        }).start();
        g.a("debug_preview", "connectionSucess()", new Object[0]);
        this.i.postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.10
            @Override // java.lang.Runnable
            public void run() {
                QZCameraActionConnectActivity.this.setResult(-1);
                QZCameraActionConnectActivity.this.finish();
                if ("ENTER_CONNECT_FROM_ALBUM".equals(QZCameraActionConnectActivity.this.b)) {
                    QZCameraActionConnectActivity.this.startActivity(new Intent(QZCameraActionConnectActivity.this, (Class<?>) CameraAlbumActivity.class));
                    return;
                }
                if ("J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                    QZCameraActionConnectActivity.this.startActivity(new Intent(QZCameraActionConnectActivity.this, (Class<?>) QZCameraActivity.class));
                } else {
                    QZCameraActionConnectActivity.this.startActivity(new Intent(QZCameraActionConnectActivity.this, (Class<?>) CameraActivity.class));
                }
                QZCameraActionConnectActivity.this.overridePendingTransition(R.anim.camera_open_enter, R.anim.camera_open_exit);
            }
        }, 1000L);
        if (!TextUtils.isEmpty(aVar.a())) {
            StatisticHelper.b(aVar.a(), j);
        }
        UploadStatsManager.a("firmware", "firmware_version", com.xiaoyi.camera.g.a().a("sw_version"));
        StatisticHelper.m(com.xiaoyi.camera.g.a().a("serial_number"));
        StatisticHelper.g(com.xiaoyi.camera.g.a().a("model"));
    }

    @Override // com.xiaoyi.camera.controller.a.b
    public void c(com.xiaoyi.camera.devicedao.a aVar, long j) {
        com.xiaoyi.camera.b.b = false;
        if (this.d != null) {
            this.d.a();
        }
        if (aVar != null) {
            this.h = aVar;
            this.i.removeCallbacks(this.p);
            k();
        } else {
            this.j = ConnectStatus.STATUS_CONNECT_FAIL;
            j();
        }
        if (TextUtils.isEmpty(aVar.a())) {
            return;
        }
        StatisticHelper.d(aVar.a(), j);
    }

    @Override // com.xiaoyi.camera.controller.a.b
    public void d(com.xiaoyi.camera.devicedao.a aVar, long j) {
        if (TextUtils.isEmpty(aVar.a())) {
            return;
        }
        StatisticHelper.a(aVar.a(), j);
    }

    public void f() {
        if (this.d != null) {
            com.xiaoyi.camera.devicedao.a c = this.d.c();
            this.h = c;
            if (c != null) {
                this.d.c(false);
                this.j = ConnectStatus.STATUS_CONNECT;
                j();
                return;
            }
        }
        this.e.b();
    }

    public boolean g() {
        try {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
            Method declaredMethod = wifiManager.getClass().getDeclaredMethod("isWifiApEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(wifiManager, new Object[0])).booleanValue();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean h() {
        if (!g()) {
            return false;
        }
        registerReceiver(this.q, new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED"));
        try {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
            Method declaredMethod = wifiManager.getClass().getDeclaredMethod("getWifiApConfiguration", new Class[0]);
            declaredMethod.setAccessible(true);
            wifiManager.getClass().getDeclaredMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE).invoke(wifiManager, (WifiConfiguration) declaredMethod.invoke(wifiManager, new Object[0]), false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
        return true;
    }

    @Override // com.xiaoyi.camera.controller.a.b
    public void n_() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (!com.xiaoyi.camera.b.b) {
            if (this.e != null) {
                this.e.a();
            }
            if (this.d != null) {
                this.d.a();
            }
            setResult(1000);
        }
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvNeedSupport /* 2131755275 */:
                startActivity(new Intent(this, (Class<?>) HelpActivity.class));
                return;
            case R.id.tvNext /* 2131755283 */:
                j();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_action_connect);
        y.b(this);
        RPJni.init();
        com.xiaoyi.camera.g.a(CameraApplication.f1401a.i());
        this.b = getIntent().getStringExtra("ENTER_CONNECT");
        this.c = (CameraDevice) getIntent().getSerializableExtra(CameraDevice.class.getSimpleName());
        if (this.c == null) {
            this.c = new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_Z13.toString(), null, null, null);
        }
        this.e = new com.xiaoyi.camera.controller.b(this);
        this.e.a(this);
        this.d = new com.xiaoyi.camera.controller.a(this);
        this.d.a(this);
        this.d.a(false);
        this.mListView.setOnItemClickListener(this);
        this.tvNext.setOnClickListener(this);
        this.tvNeedSupport.setOnClickListener(this);
        this.tvNeedSupport.getPaint().setFlags(8);
        this.tvNeedSupport.getPaint().setAntiAlias(true);
        this.titleBar.setLeftBackground(R.drawable.back);
        this.titleBar.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                if (!com.xiaoyi.camera.b.b) {
                    if (QZCameraActionConnectActivity.this.e != null) {
                        QZCameraActionConnectActivity.this.e.a();
                    }
                    if (QZCameraActionConnectActivity.this.d != null) {
                        QZCameraActionConnectActivity.this.d.a();
                    }
                    QZCameraActionConnectActivity.this.setResult(1000);
                }
                QZCameraActionConnectActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        i();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.a();
        }
        if (this.d != null) {
            this.d.a();
            this.d.b();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a((ScanResult) this.f.getItem(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            WifiInfo connectionInfo = ((WifiManager) getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (this.n && f.a(connectionInfo)) {
                this.j = ConnectStatus.STATUS_SEARCH;
                j();
            } else if (this.m) {
                this.m = false;
                this.n = false;
                String str = this.c.realmGet$deviceType() + "REMIND_OPEN_CAMERA_WIFI";
                if (!i.a().b(str, true)) {
                    this.n = true;
                    startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                }
                i.a().a(str, false);
            }
        }
    }
}
