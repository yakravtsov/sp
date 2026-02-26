package com.xiaomi.xy.sportscamera.camera.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.b.a;
import com.ants360.z13.c.e;
import com.ants360.z13.community.BasePageFragment;
import com.ants360.z13.live.LiveSettingActivity;
import com.ants360.z13.util.StatisticHelper;
import com.ants360.z13.util.m;
import com.daimajia.androidanimations.library.Techniques;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.activity.CameraActivity;
import com.xiaomi.xy.sportscamera.camera.activity.HelpActivity;
import com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity;
import com.xiaomi.xy.sportscamera.camera.c;
import com.xiaomi.xy.sportscamera.camera.connect.CameraActionConnectActivity;
import com.xiaomi.xy.sportscamera.camera.connect.QZCameraActionConnectActivity;
import com.xiaomi.xy.sportscamera.camera.d;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity;
import com.yiaction.common.util.g;
import com.yiaction.common.util.i;
import io.realm.CameraDevice;
import io.realm.CameraDeviceType;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceFragment extends BasePageFragment implements AdapterView.OnItemClickListener {
    private Unbinder b;

    @BindView(R.id.camera_connect_yi_help)
    RelativeLayout camera_connect_yi_help;

    @BindView(R.id.deviceList)
    GridView deviceGridView;

    @BindView(R.id.ivDeviceIcon)
    ImageView ivDeviceIcon;

    @BindView(R.id.iv_yi_help)
    ImageView iv_yi_help;

    @BindView(R.id.llChoiceDevice)
    RelativeLayout llChoiceDevice;

    @BindView(R.id.llConnected)
    RelativeLayout llConnected;

    @BindView(R.id.llFirmware)
    LinearLayout llFirmware;

    @BindView(R.id.camera_connect_yi_logo)
    ImageView mYILogo;

    @BindView(R.id.tvDeviceName)
    TextView tvDeviceName;

    @BindView(R.id.tvDeviceStatus)
    TextView tvDeviceStatus;

    @BindView(R.id.tvDisconnect)
    TextView tvDisconnect;

    @BindView(R.id.tvEnterDevice)
    TextView tvEnterDevice;

    @BindView(R.id.tvFirmware)
    TextView tvFirmware;

    @BindView(R.id.tv_live)
    TextView tv_live;
    private List<CameraDevice> c = new ArrayList();
    private Handler d = new Handler();
    private View.OnClickListener h = new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.fragment.DeviceFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.camera_connect_yi_help /* 2131755938 */:
                    DeviceFragment.this.startActivity(new Intent(DeviceFragment.this.getActivity(), (Class<?>) HelpActivity.class));
                    return;
                case R.id.iv_yi_help /* 2131755939 */:
                case R.id.llChoiceDevice /* 2131755941 */:
                case R.id.device_choose_tips /* 2131755942 */:
                case R.id.device_choose_tips_img /* 2131755943 */:
                case R.id.llFirmware /* 2131755944 */:
                case R.id.llConnected /* 2131755946 */:
                case R.id.ivDeviceIcon /* 2131755947 */:
                case R.id.tvDeviceName /* 2131755948 */:
                default:
                    return;
                case R.id.tv_live /* 2131755940 */:
                    g.a("click publish live", new Object[0]);
                    DeviceFragment.this.startActivity(new Intent(DeviceFragment.this.getActivity(), (Class<?>) LiveSettingActivity.class));
                    StatisticHelper.Y();
                    return;
                case R.id.tvFirmware /* 2131755945 */:
                    if (DeviceFragment.this.getString(R.string.device_new_firmware_available).equals(DeviceFragment.this.tvFirmware.getText())) {
                        DeviceFragment.this.startActivity(new Intent(DeviceFragment.this.getActivity(), (Class<?>) CameraDeviceUpgradeActivity.class));
                        return;
                    } else {
                        if (DeviceFragment.this.getString(R.string.device_status_connected).equals(DeviceFragment.this.tvFirmware.getText())) {
                            DeviceFragment.this.llConnected.setVisibility(0);
                            DeviceFragment.this.llChoiceDevice.setVisibility(8);
                            return;
                        }
                        return;
                    }
                case R.id.tvDeviceStatus /* 2131755949 */:
                    DeviceFragment.this.startActivity(new Intent(DeviceFragment.this.getActivity(), (Class<?>) CameraDeviceUpgradeActivity.class));
                    return;
                case R.id.tvEnterDevice /* 2131755950 */:
                    if ("J22".equals(com.xiaoyi.camera.g.a().a("model"))) {
                        DeviceFragment.this.startActivity(new Intent(DeviceFragment.this.getActivity(), (Class<?>) QZCameraActivity.class));
                        DeviceFragment.this.getActivity().overridePendingTransition(R.anim.camera_open_enter, R.anim.camera_open_exit);
                        return;
                    } else {
                        DeviceFragment.this.startActivity(new Intent(DeviceFragment.this.getActivity(), (Class<?>) CameraActivity.class));
                        DeviceFragment.this.getActivity().overridePendingTransition(R.anim.camera_open_enter, R.anim.camera_open_exit);
                        return;
                    }
                case R.id.tvDisconnect /* 2131755951 */:
                    DeviceFragment.this.llConnected.setVisibility(8);
                    DeviceFragment.this.llChoiceDevice.setVisibility(0);
                    DeviceFragment.this.b();
                    return;
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    final Runnable f4638a = new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.fragment.DeviceFragment.2
        @Override // java.lang.Runnable
        public void run() {
            com.daimajia.androidanimations.library.b.a(Techniques.Bounce).a(DeviceFragment.this.iv_yi_help);
            DeviceFragment.this.d.postDelayed(this, 1500L);
        }
    };

    /* loaded from: classes3.dex */
    public class a {

        /* renamed from: a, reason: collision with root package name */
        public RelativeLayout f4642a;
        public ImageView b;
        public TextView c;

        public a() {
        }
    }

    /* loaded from: classes3.dex */
    public class b extends com.ants360.z13.adapter.a {
        private int c;

        public b(int i) {
            super(i);
            this.c = i;
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public int getCount() {
            if (DeviceFragment.this.c == null) {
                return 0;
            }
            return DeviceFragment.this.c.size();
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public Object getItem(int i) {
            if (DeviceFragment.this.c == null) {
                return null;
            }
            return (CameraDevice) DeviceFragment.this.c.get(i);
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // com.ants360.z13.adapter.a, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(CameraApplication.f1401a.i()).inflate(this.c, (ViewGroup) null);
                aVar = new a();
                aVar.f4642a = (RelativeLayout) view.findViewById(R.id.llItem);
                int b = com.yiaction.common.util.b.b(CameraApplication.f1401a.i());
                if (DeviceFragment.this.c != null && DeviceFragment.this.c.size() > 1) {
                    b /= 2;
                }
                aVar.f4642a.setLayoutParams(new AbsListView.LayoutParams(b, b));
                aVar.b = (ImageView) view.findViewById(R.id.ivItem);
                aVar.c = (TextView) view.findViewById(R.id.tvItem);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.b.setVisibility(0);
            aVar.c.setVisibility(0);
            String realmGet$deviceType = ((CameraDevice) DeviceFragment.this.c.get(i)).realmGet$deviceType();
            if (CameraDeviceType.DeviceType.ACTION_Z13.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_z13);
                aVar.c.setText(R.string.camera_connect_camera_1);
            } else if (CameraDeviceType.DeviceType.ACTION_4K.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_4k);
                aVar.c.setText(R.string.camera_connect_camera_4k);
            } else if (CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_4k_p);
                aVar.c.setText(R.string.camera_connect_camera_4k_plus);
            } else if (CameraDeviceType.DeviceType.ACTION_J11.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_1080p);
                aVar.c.setText(R.string.camera_connect_camera_j11);
            } else if (CameraDeviceType.DeviceType.ACTION_J22.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_discovery);
                aVar.c.setText(R.string.camera_connect_camera_j22);
            } else if (CameraDeviceType.DeviceType.ACTION_YUNTAI.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_yuntai);
                aVar.c.setText(R.string.camera_connect_camera_handheld);
            } else if (CameraDeviceType.DeviceType.PARTS_BLUETOOTH.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_bluetooth);
                aVar.c.setText(R.string.camera_connect_camera_bluetooth);
            } else if (CameraDeviceType.DeviceType.PARTS_STICK.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_stick);
                aVar.c.setText(R.string.camera_connect_camera_stick);
            } else if (CameraDeviceType.DeviceType.PARTS_OTHER.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_parts);
                aVar.c.setText(R.string.camera_connect_camera_parts);
            } else if (CameraDeviceType.DeviceType.DEVICE_MODEL.toString().equals(realmGet$deviceType)) {
                aVar.b.setImageResource(R.drawable.ic_camera_add_parts);
                aVar.c.setText(R.string.camera_connect_camera_parts);
                aVar.b.setVisibility(4);
                aVar.c.setVisibility(4);
            }
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        com.xiaoyi.camera.g.a().e();
        CameraApplication.f1401a.i().sendBroadcast(new Intent().setAction(com.xiaoyi.camera.a.a("exit_album")));
        com.xiaomi.xy.sportscamera.camera.b.a().f();
        c.a().d();
        com.xiaomi.xy.sportscamera.camera.a.a().c();
        com.xiaoyi.camera.b.a.C();
        com.xiaoyi.camera.b.b = false;
        CameraApplication.f1401a.d(false);
        CameraApplication.f1401a.c(false);
        com.xiaoyi.camera.g.a().d();
        this.d.postDelayed(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.fragment.DeviceFragment.3
            @Override // java.lang.Runnable
            public void run() {
                com.xiaoyi.camera.g.a().c();
                com.ants360.z13.b.a.a((a.InterfaceC0051a) null);
            }
        }, 100L);
        a();
    }

    private void d() {
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_4K_P.toString(), null, null, null));
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_4K.toString(), null, null, null));
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_Z13.toString(), null, null, null));
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_J11.toString(), null, null, null));
        if (m.b().equals("en")) {
            this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_ACTION.toString(), CameraDeviceType.DeviceType.ACTION_J22.toString(), null, null, null));
        }
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_PARTS.toString(), CameraDeviceType.DeviceType.PARTS_BLUETOOTH.toString(), null, null, null));
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_YUNTAI.toString(), CameraDeviceType.DeviceType.ACTION_YUNTAI.toString(), null, null, null));
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_PARTS.toString(), CameraDeviceType.DeviceType.PARTS_OTHER.toString(), null, null, null));
        this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.CAMERA_PARTS.toString(), CameraDeviceType.DeviceType.PARTS_STICK.toString(), null, null, null));
        if (this.c.size() % 2 != 0) {
            this.c.add(new CameraDevice(null, null, null, CameraDeviceType.CameraType.WELCOME.toString(), CameraDeviceType.DeviceType.DEVICE_MODEL.toString(), null, null, null));
        }
        if (this.c.isEmpty() || this.c.size() <= 1) {
            this.deviceGridView.setNumColumns(1);
        } else {
            this.deviceGridView.setNumColumns(2);
        }
        this.deviceGridView.setOnItemClickListener(this);
        this.deviceGridView.setAdapter((ListAdapter) new b(R.layout.fragment_camera_add_device_item));
        this.camera_connect_yi_help.setOnClickListener(this.h);
        this.tvEnterDevice.setOnClickListener(this.h);
        this.tvDisconnect.setOnClickListener(this.h);
        this.tvDisconnect.getPaint().setFlags(8);
        this.tvDisconnect.getPaint().setAntiAlias(true);
        this.tvFirmware.setOnClickListener(this.h);
        this.tvFirmware.getPaint().setFlags(8);
        this.tvFirmware.getPaint().setAntiAlias(true);
        this.tv_live.setOnClickListener(this.h);
    }

    private void e() {
        boolean z;
        d a2 = d.a();
        if (com.xiaoyi.camera.b.b) {
            String a3 = com.xiaoyi.camera.g.a().a("model");
            String a4 = com.xiaoyi.camera.g.a().a("serial_number");
            if (!com.xiaoyi.camera.module.a.b(com.xiaoyi.camera.g.a().a("sw_version"), a2.d(a2.h(a3, a4)))) {
                this.tvDeviceStatus.setText(R.string.device_status_connected);
                this.tvDeviceStatus.setCompoundDrawables(null, null, null, null);
                this.tvDeviceStatus.setOnClickListener(null);
                return;
            } else if (a2.p(a3, a4).booleanValue()) {
                this.tvDeviceStatus.setText(R.string.device_new_firmware_downloaded);
                this.tvDeviceStatus.setCompoundDrawables(null, null, null, null);
                this.tvDeviceStatus.setOnClickListener(null);
                return;
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.ic_new_lj_set_right);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.tvDeviceStatus.setCompoundDrawables(null, null, drawable, null);
                this.tvDeviceStatus.setText(R.string.device_new_firmware_available);
                this.tvDeviceStatus.setOnClickListener(this.h);
                return;
            }
        }
        com.xiaoyi.camera.d.b bVar = new com.xiaoyi.camera.d.b();
        List<CameraDevice> a5 = bVar.a("cameraType", CameraDeviceType.CameraType.CAMERA_ACTION.toString());
        bVar.a();
        if (a5 == null || a5.isEmpty()) {
            z = false;
        } else {
            z = false;
            for (CameraDevice cameraDevice : a5) {
                z = com.xiaoyi.camera.module.a.b(cameraDevice.realmGet$deviceSwVersion(), a2.d(a2.a(cameraDevice))) ? true : z;
            }
        }
        if (!z) {
            this.llFirmware.setVisibility(8);
            this.tvFirmware.setVisibility(8);
            return;
        }
        this.tvFirmware.setText(R.string.device_new_firmware_available);
        this.tvFirmware.setVisibility(0);
        this.tvFirmware.getPaint().setFlags(8);
        this.tvFirmware.getPaint().setAntiAlias(true);
        this.llFirmware.setVisibility(0);
    }

    public void a() {
        if (getActivity() == null) {
            return;
        }
        if (com.xiaoyi.camera.b.b) {
            this.llChoiceDevice.setVisibility(8);
            this.llConnected.setVisibility(0);
            String a2 = i.a().a("LAST_CONNECT_DEVICE");
            int i = R.drawable.ic_camera_connect_status_z13;
            String string = getString(R.string.camera_connect_camera_1);
            if (CameraDeviceType.DeviceType.ACTION_4K.toString().equals(a2)) {
                i = R.drawable.ic_camera_connect_status_4k;
                string = getString(R.string.camera_connect_camera_4k);
            } else if (CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(a2)) {
                i = R.drawable.ic_camera_connect_status_4kp;
                string = getString(R.string.camera_connect_camera_4k_plus);
            } else if (CameraDeviceType.DeviceType.ACTION_J11.toString().equals(a2)) {
                i = R.drawable.ic_camera_connect_status_1080;
                string = getString(R.string.camera_connect_camera_j11);
            } else if (CameraDeviceType.DeviceType.ACTION_J22.toString().equals(a2)) {
                i = R.drawable.ic_camera_connect_status_discovery;
                string = getString(R.string.camera_connect_camera_j22);
            }
            this.ivDeviceIcon.setImageResource(i);
            this.tvDeviceName.setText(string.toUpperCase());
        } else {
            this.llConnected.setVisibility(8);
            this.llChoiceDevice.setVisibility(0);
        }
        e();
    }

    @Override // com.ants360.z13.community.BasePageFragment
    public void c() {
    }

    @Override // com.ants360.z13.community.BasePageFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        de.greenrobot.event.c.a().a(this);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_camera_add_device, viewGroup, false);
        this.b = ButterKnife.bind(this, inflate);
        d();
        d.a().e();
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.b.unbind();
        this.d.removeCallbacksAndMessages(null);
        de.greenrobot.event.c.a().b(this);
    }

    public void onEvent(e eVar) {
        e();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CameraDevice cameraDevice = this.c.get(i);
        String realmGet$cameraType = cameraDevice.realmGet$cameraType();
        if (!CameraDeviceType.CameraType.CAMERA_ACTION.toString().equals(realmGet$cameraType)) {
            if (CameraDeviceType.CameraType.CAMERA_YUNTAI.toString().equals(realmGet$cameraType) || CameraDeviceType.CameraType.CAMERA_PARTS.toString().equals(realmGet$cameraType)) {
                Intent intent = new Intent(getActivity(), (Class<?>) HelpActivity.class);
                intent.putExtra(CameraDevice.class.getSimpleName(), cameraDevice);
                startActivity(intent);
                return;
            }
            return;
        }
        StatisticHelper.DeviceType deviceType = StatisticHelper.DeviceType.Z13;
        if (CameraDeviceType.DeviceType.ACTION_4K.toString().equals(cameraDevice.realmGet$deviceType())) {
            deviceType = StatisticHelper.DeviceType.Z16;
        } else if (CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(cameraDevice.realmGet$deviceType())) {
            deviceType = StatisticHelper.DeviceType.Z18;
        } else if (CameraDeviceType.DeviceType.ACTION_J11.toString().equals(cameraDevice.realmGet$deviceType())) {
            deviceType = StatisticHelper.DeviceType.J11;
        } else if (CameraDeviceType.DeviceType.ACTION_J22.toString().equals(cameraDevice.realmGet$deviceType())) {
            deviceType = StatisticHelper.DeviceType.J22;
        }
        StatisticHelper.a(deviceType);
        Intent intent2 = CameraDeviceType.DeviceType.ACTION_J22.toString().equals(cameraDevice.realmGet$deviceType()) ? new Intent(getActivity(), (Class<?>) QZCameraActionConnectActivity.class) : new Intent(getActivity(), (Class<?>) CameraActionConnectActivity.class);
        intent2.putExtra(CameraDevice.class.getSimpleName(), cameraDevice);
        startActivity(intent2);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        a();
    }
}
