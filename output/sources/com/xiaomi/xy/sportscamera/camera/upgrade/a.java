package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.c.d;
import com.xiaoyi.camera.d.e;
import com.yiaction.common.util.g;
import io.realm.CameraDevice;
import io.realm.CameraDeviceType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class a extends RecyclerView.a<b> {
    private Context b;
    private List<CameraDevice> c;
    private b e;
    private CameraDevice f;
    private Map<CameraDevice, b> g = new HashMap();
    private final int h = 101;
    private final int i = 1001;
    private final int j = 102;
    private final int k = 1002;
    private Handler l = new Handler(Looper.getMainLooper()) { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    int floatValue = (int) ((message.arg1 / Float.valueOf(a.this.d.g(a.this.d.a(a.this.f))).floatValue()) * 100.0f);
                    a.this.e.r.setProgress(floatValue);
                    a.this.e.r.setVisibility(0);
                    a.this.e.q.setText(floatValue + "%");
                    return;
                case 102:
                    a.this.e.r.setVisibility(8);
                    a.this.e.v.setVisibility(8);
                    a.this.e.w.setVisibility(0);
                    a.this.g.remove(a.this.f);
                    a.this.d.a(a.this.d.a(a.this.f), (Boolean) false);
                    a.this.d.b(a.this.d.a(a.this.f));
                    Iterator it2 = a.this.g.keySet().iterator();
                    if (it2.hasNext()) {
                        a.this.f = (CameraDevice) it2.next();
                        a.this.e = (b) a.this.g.get(a.this.f);
                        a.this.c();
                        return;
                    }
                    return;
                case 1001:
                    int i = message.arg1;
                    a.this.e.r.setProgress(i);
                    a.this.e.r.setVisibility(0);
                    a.this.e.q.setText(i + "%");
                    return;
                case 1002:
                    a.this.e.r.setVisibility(8);
                    a.this.e.v.setVisibility(8);
                    a.this.e.w.setVisibility(0);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    d f4767a = new d() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.a.3
        @Override // com.xiaoyi.camera.c.d
        public void a() {
        }

        @Override // com.xiaoyi.camera.c.d
        public void a(int i) {
            Message obtainMessage = a.this.l.obtainMessage(101);
            obtainMessage.arg1 = i;
            a.this.l.sendMessage(obtainMessage);
        }

        @Override // com.xiaoyi.camera.c.d
        public void b() {
            a.this.l.sendEmptyMessage(102);
        }
    };
    private com.xiaomi.xy.sportscamera.camera.d d = com.xiaomi.xy.sportscamera.camera.d.a();

    /* renamed from: com.xiaomi.xy.sportscamera.camera.upgrade.a$a, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public interface InterfaceC0202a {
        void a(b bVar, int i);
    }

    /* loaded from: classes3.dex */
    public class b extends RecyclerView.t implements View.OnClickListener {
        public ImageView n;
        public TextView o;
        public TextView p;
        public Button q;
        public ProgressBar r;
        public TextView s;
        public TextView t;
        public TextView u;
        public FrameLayout v;
        public Button w;
        public InterfaceC0202a x;

        public b(View view, InterfaceC0202a interfaceC0202a) {
            super(view);
            this.x = interfaceC0202a;
            this.n = (ImageView) view.findViewById(R.id.ivModel);
            this.o = (TextView) view.findViewById(R.id.tvName);
            this.p = (TextView) view.findViewById(R.id.tv_hw_version);
            this.q = (Button) view.findViewById(R.id.btnUpgrade);
            this.r = (ProgressBar) view.findViewById(R.id.downloadProgress);
            this.s = (TextView) view.findViewById(R.id.tvCameraVersion);
            this.t = (TextView) view.findViewById(R.id.tvServerVersion);
            this.u = (TextView) view.findViewById(R.id.tvUpgradeMsg);
            this.v = (FrameLayout) view.findViewById(R.id.flUpgrade);
            this.w = (Button) view.findViewById(R.id.tvUpdateRemind);
            this.q.setOnClickListener(this);
            this.w.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnUpgrade /* 2131755296 */:
                    if (this.x != null) {
                        this.x.a((b) view.getTag(), d());
                        return;
                    }
                    return;
                case R.id.tvUpdateRemind /* 2131755297 */:
                    a.this.b.startActivity(new Intent(a.this.b, (Class<?>) MainActivity.class));
                    ((CameraDeviceUpgradeActivity) a.this.b).finish();
                    return;
                default:
                    return;
            }
        }
    }

    public a(Context context) {
        this.b = context;
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public int a() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }

    @Override // android.support.v7.widget.RecyclerView.a
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b b(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.b).inflate(R.layout.activity_camera_device_upgrade_item, viewGroup, false), new InterfaceC0202a() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.a.2
            @Override // com.xiaomi.xy.sportscamera.camera.upgrade.a.InterfaceC0202a
            public void a(b bVar, int i2) {
                boolean z;
                CameraDevice cameraDevice = (CameraDevice) a.this.c.get(i2);
                String a2 = a.this.d.a(cameraDevice);
                if (a.this.d.j(a2).booleanValue() && a.this.d.a(a2) != null) {
                    a.this.f = cameraDevice;
                    a.this.e = bVar;
                    return;
                }
                Iterator it2 = a.this.c.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = false;
                        break;
                    }
                    String a3 = a.this.d.a((CameraDevice) it2.next());
                    if (a.this.d.j(a3).booleanValue() && a.this.d.a(a3) != null) {
                        z = true;
                        break;
                    }
                }
                g.a(BuildConfig.BUILD_TYPE, "------------------------- isHaveDownload = " + z, new Object[0]);
                if (z) {
                    b bVar2 = (b) a.this.g.get(cameraDevice);
                    if (bVar2 != null) {
                        bVar2.q.setText(R.string.download);
                        a.this.g.remove(cameraDevice);
                        return;
                    } else {
                        bVar.q.setText(R.string.camera_action_firmware_wait);
                        a.this.g.put(cameraDevice, bVar);
                        return;
                    }
                }
                if (e.a(a.this.b)) {
                    a.this.f = cameraDevice;
                    a.this.e = bVar;
                    Bundle bundle = new Bundle();
                    bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, a.this.b.getString(R.string.firmware_download_tip));
                    CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
                    customBottomDialogFragment.setArguments(bundle);
                    customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.a.2.1
                        @Override // com.ants360.z13.fragment.DimPanelFragment.c
                        public void a(DialogFragment dialogFragment) {
                            dialogFragment.dismiss();
                            a.this.g.put(a.this.f, a.this.e);
                            a.this.c();
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
                    customBottomDialogFragment.a((CameraDeviceUpgradeActivity) a.this.b);
                    return;
                }
                if (!e.b(a.this.b) || e.c(a.this.b)) {
                    ((CameraDeviceUpgradeActivity) a.this.b).a(R.string.prompt_no_network_connection);
                    return;
                }
                a.this.f = cameraDevice;
                a.this.e = bVar;
                a.this.g.put(a.this.f, a.this.e);
                a.this.c();
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public void a(b bVar, int i) {
        CameraDevice cameraDevice = this.c.get(i);
        bVar.q.setTag(bVar);
        String str = "Z13";
        String realmGet$deviceType = cameraDevice.realmGet$deviceType();
        if (CameraDeviceType.DeviceType.ACTION_Z13.toString().equals(realmGet$deviceType)) {
            bVar.n.setImageResource(R.drawable.ic_camera_add_z13);
            bVar.o.setText(R.string.camera_connect_camera_1);
            str = "Z13";
        } else if (CameraDeviceType.DeviceType.ACTION_4K.toString().equals(realmGet$deviceType)) {
            bVar.n.setImageResource(R.drawable.ic_camera_add_4k);
            bVar.o.setText(R.string.camera_connect_camera_4k);
            str = "Z16";
        } else if (CameraDeviceType.DeviceType.ACTION_4K_P.toString().equals(realmGet$deviceType)) {
            bVar.n.setImageResource(R.drawable.ic_camera_add_4k_p);
            bVar.o.setText(R.string.camera_connect_camera_4k_plus);
            str = "Z18";
        } else if (CameraDeviceType.DeviceType.ACTION_J11.toString().equals(realmGet$deviceType)) {
            bVar.n.setImageResource(R.drawable.ic_camera_add_1080p);
            bVar.o.setText(R.string.camera_connect_camera_j11);
            str = "J11";
        } else if (CameraDeviceType.DeviceType.ACTION_J22.toString().equals(realmGet$deviceType)) {
            bVar.n.setImageResource(R.drawable.ic_camera_add_discovery);
            bVar.o.setText(R.string.camera_connect_camera_j22);
            str = "J22";
        }
        String a2 = this.d.a(cameraDevice);
        String realmGet$deviceSn = cameraDevice.realmGet$deviceSn();
        String e = this.d.e(realmGet$deviceSn);
        bVar.s.setText(this.b.getString(R.string.camera_firmware_current_version) + e.split("_")[1]);
        String d = this.d.d(a2);
        bVar.t.setText(this.b.getString(R.string.camera_firmware_server_version) + d);
        bVar.u.setText(this.d.c(a2));
        bVar.p.setText(this.b.getString(R.string.hw_version_code) + "：" + this.d.i(a2));
        bVar.r.setVisibility(8);
        if (!TextUtils.isEmpty(a2) && this.d.j(a2).booleanValue() && this.d.a(a2) != null) {
            this.f = cameraDevice;
            this.e = bVar;
            this.d.a(a2, this.f4767a);
        }
        if ((!com.xiaoyi.camera.module.a.c(e, d) && this.d.p(str, realmGet$deviceSn).booleanValue()) || (!com.xiaoyi.camera.module.a.c(e, this.d.m(str)) && this.d.q(str, realmGet$deviceSn).booleanValue())) {
            bVar.v.setVisibility(8);
            bVar.w.setVisibility(0);
        } else {
            bVar.v.setVisibility(0);
            bVar.w.setVisibility(8);
            bVar.q.setText(R.string.download);
        }
    }

    public void a(List<CameraDevice> list) {
        this.c = list;
        f();
    }

    public void b() {
        this.d.d();
        if (this.f != null) {
            this.d.a(this.d.a(this.f), (Boolean) false);
        }
        this.d.b();
        f();
    }

    public void c() {
        String realmGet$deviceSn = this.f.realmGet$deviceSn();
        String a2 = this.d.a(this.f);
        this.d.a(a2, this.f4767a);
        this.d.a(this.b, a2, realmGet$deviceSn);
    }
}
