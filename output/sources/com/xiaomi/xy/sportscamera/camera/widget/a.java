package com.xiaomi.xy.sportscamera.camera.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ants360.z13.widget.CameraSettingView;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.e;
import com.xiaoyi.camera.g;
import com.yiaction.common.util.Constant;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class a extends Dialog implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f4794a;
    private int b;
    private boolean c;
    private int d;
    private CameraMainController.CameraMode e;
    private Constant.RecordMode f;
    private Constant.CaptureMode g;
    private Context h;
    private FrameLayout i;
    private CameraSettingView j;
    private TextView k;
    private View l;
    private ListView m;
    private c n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private String s;
    private int t;
    private InterfaceC0204a u;

    /* renamed from: com.xiaomi.xy.sportscamera.camera.widget.a$a, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public interface InterfaceC0204a {
        void a(CameraMainController.CameraMode cameraMode, Constant.RecordMode recordMode, Constant.CaptureMode captureMode, String str, int i);
    }

    /* loaded from: classes3.dex */
    public class b {

        /* renamed from: a, reason: collision with root package name */
        public TextView f4798a;

        public b() {
        }
    }

    /* loaded from: classes3.dex */
    public class c extends BaseAdapter {
        public c() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return a.this.d;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                bVar = new b();
                view = LayoutInflater.from(a.this.h).inflate(R.layout.camera_setting_option_item_2, (ViewGroup) null);
                bVar.f4798a = (TextView) view.findViewById(R.id.tvSettingOptions);
                bVar.f4798a.setLayoutParams(new LinearLayout.LayoutParams(-1, a.this.q));
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            if (a.this.f4794a == i) {
                bVar.f4798a.setTextColor(a.this.h.getResources().getColor(R.color.white));
            } else {
                bVar.f4798a.setTextColor(a.this.h.getResources().getColor(R.color.setting_dialog_content_color));
            }
            switch (a.this.e) {
                case CaptureMode:
                    switch (a.this.g) {
                        case BURST:
                            bVar.f4798a.setText(e.f4859a[i].replace(" ", ""));
                            break;
                        case TIMELAPES:
                            String str = com.xiaoyi.camera.module.b.a("precise_cont_time").get(i);
                            if (!str.contains("continue")) {
                                bVar.f4798a.setText(str.replace(".0", "").replace(" ", ""));
                                break;
                            } else {
                                bVar.f4798a.setText(a.this.h.getString(R.string.time_interval_continue_abridge));
                                break;
                            }
                        case TIMER:
                            bVar.f4798a.setText(com.xiaoyi.camera.module.b.a("precise_selftime").get(i).replace("s", ""));
                            break;
                    }
                case RecordMode:
                    switch (a.this.f) {
                        case PHOTO:
                            bVar.f4798a.setText(com.xiaoyi.camera.module.b.a("record_photo_time").get(i));
                            break;
                        case LOOP:
                            String str2 = com.xiaoyi.camera.module.b.a("loop_rec_duration").get(i).split(" ")[0];
                            if ("max".equalsIgnoreCase(str2)) {
                                str2 = a.this.h.getString(R.string.loop_video_duration_max);
                            }
                            bVar.f4798a.setText(str2);
                            break;
                        case TIMELAPES:
                            if (a.this.t != 0) {
                                if (a.this.t == 1) {
                                    String replace = com.xiaoyi.camera.module.b.a("timelapse_video_duration").get(i).replace("s", "");
                                    if ("off".equals(replace)) {
                                        replace = a.this.h.getString(R.string.set_off);
                                    }
                                    bVar.f4798a.setText(replace);
                                    break;
                                }
                            } else {
                                bVar.f4798a.setText(com.xiaoyi.camera.module.b.a("timelapse_video").get(i));
                                break;
                            }
                            break;
                        case SLOW:
                            if (!TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
                                try {
                                    String[] split = com.xiaoyi.camera.module.b.a("slow_motion_res").get(i).split("x");
                                    bVar.f4798a.setText(split[1] + "P/" + split[2] + "x");
                                    break;
                                } catch (Exception e) {
                                    bVar.f4798a.setText(com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i).split(" ")[0]);
                                    break;
                                }
                            } else {
                                bVar.f4798a.setText(com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i).split(" ")[0]);
                                break;
                            }
                    }
            }
            return view;
        }
    }

    public a(Context context, int i) {
        super(context, i);
        this.d = 0;
        this.t = -1;
        this.n = new c();
        this.h = context;
    }

    public int a(CameraMainController.CameraMode cameraMode, Constant.RecordMode recordMode, Constant.CaptureMode captureMode, int i) {
        this.t = i;
        this.e = cameraMode;
        switch (cameraMode) {
            case CaptureMode:
                this.g = captureMode;
                switch (this.g) {
                    case BURST:
                        this.s = this.h.getString(R.string.frequency);
                        g(g.a().a("burst_capture_number"));
                        break;
                    case TIMELAPES:
                        this.s = this.h.getString(R.string.camera_timelaps_internal);
                        h(g.a().a("precise_cont_time"));
                        break;
                    case TIMER:
                        this.s = this.h.getString(R.string.countDown);
                        f(g.a().a("precise_selftime"));
                        break;
                }
            case RecordMode:
                this.f = recordMode;
                switch (this.f) {
                    case PHOTO:
                        this.s = this.h.getString(R.string.camera_timelaps_internal);
                        b(g.a().a("record_photo_time"));
                        break;
                    case LOOP:
                        this.s = this.h.getString(R.string.loop_record_duration);
                        a(g.a().a("loop_rec_duration"));
                        break;
                    case TIMELAPES:
                        this.s = this.h.getString(R.string.camera_timelaps_internal);
                        if (this.t != 0) {
                            if (this.t == 1) {
                                this.s = this.h.getString(R.string.camera_timelaps_video_length);
                                c(g.a().a("timelapse_video_duration"));
                                break;
                            }
                        } else {
                            this.s = this.h.getString(R.string.camera_timelaps_internal);
                            c(g.a().a("timelapse_video"));
                            break;
                        }
                        break;
                    case SLOW:
                        this.s = this.h.getString(R.string.slow_rate);
                        if (!TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
                            this.s = this.h.getString(R.string.slow_motion_resolution_choose);
                            e(g.a().a("slow_motion_res"));
                            break;
                        } else {
                            this.s = this.h.getString(R.string.slow_rate);
                            d(g.a().a("slow_motion_rate"));
                            break;
                        }
                }
        }
        return this.d;
    }

    public void a() {
        String str;
        switch (this.e) {
            case CaptureMode:
                switch (this.g) {
                    case BURST:
                        str = e.b[this.b];
                        break;
                    case TIMELAPES:
                        str = com.xiaoyi.camera.module.b.a("precise_cont_time").get(this.b);
                        break;
                    case TIMER:
                        str = com.xiaoyi.camera.module.b.a("precise_selftime").get(this.b);
                        break;
                    default:
                        str = "";
                        break;
                }
                if (this.g.getOption().equals(str) || this.u == null) {
                    return;
                }
                this.u.a(CameraMainController.CameraMode.CaptureMode, null, this.g, str, -1);
                return;
            case RecordMode:
                switch (this.f) {
                    case PHOTO:
                        String str2 = com.xiaoyi.camera.module.b.a("record_photo_time").get(this.b);
                        if (g.a().a("record_photo_time").equals(str2) || this.u == null) {
                            return;
                        }
                        this.u.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.PHOTO, null, str2, -1);
                        return;
                    case LOOP:
                        String str3 = com.xiaoyi.camera.module.b.a("loop_rec_duration").get(this.b);
                        if (g.a().a("loop_rec_duration").equals(str3) || this.u == null) {
                            return;
                        }
                        this.u.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.LOOP, null, str3, -1);
                        return;
                    case TIMELAPES:
                        if (this.t == 0) {
                            if (com.xiaoyi.camera.d.a.c()) {
                                String str4 = com.xiaoyi.camera.module.b.a("timelapse_video").get(this.b);
                                if (g.a().a("timelapse_video").equals(str4) || this.u == null) {
                                    return;
                                }
                                this.u.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.TIMELAPES, null, str4, 0);
                                return;
                            }
                            return;
                        }
                        if (this.t == 1) {
                            String str5 = com.xiaoyi.camera.module.b.a("timelapse_video_duration").get(this.b);
                            if (g.a().a("timelapse_video_duration").equals(str5) || this.u == null) {
                                return;
                            }
                            this.u.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.TIMELAPES, null, str5, 1);
                            return;
                        }
                        return;
                    case SLOW:
                        if (TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
                            String str6 = com.xiaoyi.camera.module.b.a("slow_motion_rate").get(this.b);
                            if (g.a().a("slow_motion_rate").equals(str6) || this.u == null) {
                                return;
                            }
                            this.u.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.SLOW, null, str6, -1);
                            return;
                        }
                        String str7 = com.xiaoyi.camera.module.b.a("slow_motion_res").get(this.b);
                        if (g.a().a("slow_motion_res").equals(str7) || this.u == null) {
                            return;
                        }
                        this.u.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.SLOW, null, str7, -1);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void a(int i, int i2, int i3, boolean z) {
        this.o = i;
        this.p = i2;
        this.q = i3;
        this.r = z;
    }

    public void a(InterfaceC0204a interfaceC0204a) {
        this.u = interfaceC0204a;
    }

    public void a(String str) {
        ArrayList<String> a2 = com.xiaoyi.camera.module.b.a("loop_rec_duration");
        this.d = a2.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= a2.size()) {
                return;
            }
            if (a2.get(i2).equalsIgnoreCase(str)) {
                this.f4794a = i2;
            }
            i = i2 + 1;
        }
    }

    public void b() {
        super.show();
        new Handler().post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.widget.a.1
            @Override // java.lang.Runnable
            public void run() {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, a.this.i.getHeight(), 0.0f);
                translateAnimation.setFillEnabled(true);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.widget.a.1.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }
                });
                translateAnimation.setDuration(150L);
                a.this.i.startAnimation(translateAnimation);
            }
        });
    }

    public void b(String str) {
        ArrayList<String> a2 = com.xiaoyi.camera.module.b.a("record_photo_time");
        this.d = a2.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.d) {
                return;
            }
            if (a2.get(i2).equalsIgnoreCase(str)) {
                this.f4794a = i2;
            }
            i = i2 + 1;
        }
    }

    public void c(String str) {
        int i = 0;
        if (this.t == 0) {
            this.d = com.xiaoyi.camera.module.b.a("timelapse_video").size();
            while (i < this.d) {
                if (str.equals(com.xiaoyi.camera.module.b.a("timelapse_video").get(i))) {
                    this.f4794a = i;
                    return;
                }
                i++;
            }
            return;
        }
        if (this.t == 1) {
            this.d = com.xiaoyi.camera.module.b.a("timelapse_video_duration").size();
            while (i < this.d) {
                if (str.equals(com.xiaoyi.camera.module.b.a("timelapse_video_duration").get(i))) {
                    this.f4794a = i;
                    return;
                }
                i++;
            }
        }
    }

    public void d(String str) {
        this.d = com.xiaoyi.camera.module.b.a("slow_motion_rate").size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.d) {
                return;
            }
            if (com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i2).equals(str)) {
                this.f4794a = i2;
                return;
            }
            i = i2 + 1;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.c && this.b != this.f4794a) {
            a();
        } else if (this.u != null) {
            this.u.a(this.e, this.f, this.g, null, this.t);
        }
        this.h = null;
        super.dismiss();
    }

    public void e(String str) {
        this.d = com.xiaoyi.camera.module.b.a("slow_motion_res").size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.d) {
                return;
            }
            if (com.xiaoyi.camera.module.b.a("slow_motion_res").get(i2).equals(str)) {
                this.f4794a = i2;
                return;
            }
            i = i2 + 1;
        }
    }

    public void f(String str) {
        this.d = com.xiaoyi.camera.module.b.a("precise_selftime").size();
        for (int i = 0; i < this.d; i++) {
            if (str.equals(com.xiaoyi.camera.module.b.a("precise_selftime").get(i))) {
                this.f4794a = i;
                return;
            }
        }
    }

    public void g(String str) {
        e.c();
        this.d = e.f4859a.length;
        for (int i = 0; i < this.d; i++) {
            if (str.equals(e.b[i])) {
                this.f4794a = i;
                return;
            }
        }
    }

    public void h(String str) {
        this.d = com.xiaoyi.camera.module.b.a("precise_cont_time").size();
        for (int i = 0; i < this.d; i++) {
            if (str.equals(com.xiaoyi.camera.module.b.a("precise_cont_time").get(i))) {
                this.f4794a = i;
                return;
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        com.yiaction.common.util.g.a("dialog", "AdapterView onItemClick position: " + i + ", arg3: " + j, new Object[0]);
        this.c = true;
        this.b = i;
        dismiss();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        super.setContentView(i);
        this.i = (FrameLayout) findViewById(R.id.parentView);
        this.j = (CameraSettingView) findViewById(R.id.settingView);
        this.j.a(this.o, this.p, this.r);
        this.k = (TextView) findViewById(R.id.paramsTitle);
        this.k.setText(this.s);
        this.k.setLayoutParams(new LinearLayout.LayoutParams(-1, this.q));
        this.m = (ListView) findViewById(R.id.lvSettingOptions);
        this.m.setAdapter((ListAdapter) this.n);
        this.m.setSelector(new ColorDrawable(0));
        this.m.setOnItemClickListener(this);
        this.m.setSelection(this.f4794a);
        this.l = findViewById(R.id.emptyView);
        this.l.setLayoutParams(new LinearLayout.LayoutParams(-1, this.o / 4));
    }
}
