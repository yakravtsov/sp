package com.xiaomi.xy.sportscamera.camera.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.widget.CameraSettingView;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.e;
import com.xiaoyi.camera.g;
import com.yiaction.common.util.Constant;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class b extends Dialog implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f4800a;
    private int b;
    private boolean c;
    private int d;
    private CameraMainController.CameraMode e;
    private Constant.RecordMode f;
    private Constant.CaptureMode g;
    private Context h;
    private RelativeLayout i;
    private CameraSettingView j;
    private TextView k;
    private RecyclerView l;
    private c m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private String r;
    private int s;
    private a t;

    /* loaded from: classes3.dex */
    public interface a {
        void a(CameraMainController.CameraMode cameraMode, Constant.RecordMode recordMode, Constant.CaptureMode captureMode, String str, int i);
    }

    /* renamed from: com.xiaomi.xy.sportscamera.camera.widget.b$b, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public class C0206b extends RecyclerView.t {
        public LinearLayout n;
        public TextView o;
        public TextView p;

        public C0206b(View view) {
            super(view);
            this.n = (LinearLayout) view.findViewById(R.id.llContent);
            this.n.setLayoutParams(new LinearLayout.LayoutParams(b.this.p, -1));
            this.o = (TextView) view.findViewById(R.id.tvSettingNum);
            this.p = (TextView) view.findViewById(R.id.tvSettingUnit);
            this.p.setVisibility(8);
        }
    }

    /* loaded from: classes3.dex */
    public class c extends RecyclerView.a<C0206b> {
        public c() {
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public int a() {
            return b.this.d;
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public long a(int i) {
            return 0L;
        }

        @Override // android.support.v7.widget.RecyclerView.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0206b b(ViewGroup viewGroup, int i) {
            return new C0206b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.camera_setting_option_item_3, (ViewGroup) null));
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public void a(C0206b c0206b, final int i) {
            c0206b.n.setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.xy.sportscamera.camera.widget.b.c.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    b.this.c = true;
                    b.this.b = i;
                    b.this.dismiss();
                }
            });
            if (b.this.f4800a == i) {
                c0206b.o.setTextColor(b.this.h.getResources().getColor(R.color.white));
                c0206b.p.setTextColor(b.this.h.getResources().getColor(R.color.white));
            } else {
                c0206b.o.setTextColor(b.this.h.getResources().getColor(R.color.setting_dialog_content_color));
                c0206b.p.setTextColor(b.this.h.getResources().getColor(R.color.setting_dialog_content_color));
            }
            switch (b.this.e) {
                case CaptureMode:
                    switch (b.this.g) {
                        case BURST:
                            c0206b.o.setText(e.f4859a[i].split(" ")[0]);
                            c0206b.p.setText(e.f4859a[i].split(" ")[1]);
                            c0206b.p.setVisibility(0);
                            return;
                        case TIMELAPES:
                            String str = com.xiaoyi.camera.module.b.a("precise_cont_time").get(i);
                            if (str.contains("continue")) {
                                c0206b.o.setText(b.this.h.getString(R.string.time_interval_continue_abridge));
                                c0206b.p.setVisibility(8);
                                return;
                            } else {
                                c0206b.o.setText(str.split(" ")[0].replace(".0", ""));
                                c0206b.p.setText(str.split(" ")[1]);
                                c0206b.p.setVisibility(0);
                                return;
                            }
                        case TIMER:
                            c0206b.o.setText(com.xiaoyi.camera.module.b.a("precise_selftime").get(i).replace("s", ""));
                            return;
                        default:
                            return;
                    }
                case RecordMode:
                    switch (b.this.f) {
                        case PHOTO:
                            c0206b.o.setText(com.xiaoyi.camera.module.b.a("record_photo_time").get(i));
                            return;
                        case LOOP:
                            String str2 = com.xiaoyi.camera.module.b.a("loop_rec_duration").get(i).split(" ")[0];
                            if ("max".equalsIgnoreCase(str2)) {
                                str2 = b.this.h.getString(R.string.loop_video_duration_max);
                            }
                            c0206b.o.setText(str2);
                            return;
                        case TIMELAPES:
                            if (b.this.s == 0) {
                                c0206b.o.setText(com.xiaoyi.camera.module.b.a("timelapse_video").get(i));
                                return;
                            } else {
                                if (b.this.s == 1) {
                                    String replace = com.xiaoyi.camera.module.b.a("timelapse_video_duration").get(i).replace("s", "");
                                    if ("off".equals(replace)) {
                                        replace = b.this.h.getString(R.string.set_off);
                                    }
                                    c0206b.o.setText(replace);
                                    return;
                                }
                                return;
                            }
                        case SLOW:
                            if (TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
                                c0206b.o.setText(com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i).split(" ")[0]);
                                return;
                            }
                            try {
                                String[] split = com.xiaoyi.camera.module.b.a("slow_motion_res").get(i).split("x");
                                c0206b.o.setText(split[1] + "P");
                                c0206b.p.setText(split[2] + "x");
                                c0206b.p.setVisibility(0);
                                return;
                            } catch (Exception e) {
                                c0206b.o.setText(com.xiaoyi.camera.module.b.a("slow_motion_rate").get(i).split(" ")[0]);
                                c0206b.p.setVisibility(8);
                                return;
                            }
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    public b(Context context, int i) {
        super(context, i);
        this.d = 0;
        this.s = -1;
        this.m = new c();
        this.h = context;
    }

    public int a(CameraMainController.CameraMode cameraMode, Constant.RecordMode recordMode, Constant.CaptureMode captureMode, int i) {
        this.s = i;
        this.e = cameraMode;
        switch (cameraMode) {
            case CaptureMode:
                this.g = captureMode;
                switch (this.g) {
                    case BURST:
                        this.r = this.h.getString(R.string.frequency);
                        g(g.a().a("burst_capture_number"));
                        break;
                    case TIMELAPES:
                        this.r = this.h.getString(R.string.camera_timelaps_internal);
                        h(g.a().a("precise_cont_time"));
                        break;
                    case TIMER:
                        this.r = this.h.getString(R.string.countDown);
                        f(g.a().a("precise_selftime"));
                        break;
                }
            case RecordMode:
                this.f = recordMode;
                switch (this.f) {
                    case PHOTO:
                        this.r = this.h.getString(R.string.camera_timelaps_internal);
                        b(g.a().a("record_photo_time"));
                        break;
                    case LOOP:
                        this.r = this.h.getString(R.string.loop_record_duration);
                        a(g.a().a("loop_rec_duration"));
                        break;
                    case TIMELAPES:
                        this.r = this.h.getString(R.string.camera_timelaps_internal);
                        if (this.s != 0) {
                            if (this.s == 1) {
                                this.r = this.h.getString(R.string.camera_timelaps_video_length);
                                c(g.a().a("timelapse_video_duration"));
                                break;
                            }
                        } else {
                            this.r = this.h.getString(R.string.camera_timelaps_internal);
                            c(g.a().a("timelapse_video"));
                            break;
                        }
                        break;
                    case SLOW:
                        this.r = this.h.getString(R.string.slow_rate);
                        if (!TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
                            this.r = this.h.getString(R.string.slow_motion_resolution_choose);
                            e(g.a().a("slow_motion_res"));
                            break;
                        } else {
                            this.r = this.h.getString(R.string.slow_rate);
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
                if (this.g.getOption().equals(str) || this.t == null) {
                    return;
                }
                this.t.a(CameraMainController.CameraMode.CaptureMode, null, this.g, str, -1);
                return;
            case RecordMode:
                switch (this.f) {
                    case PHOTO:
                        String str2 = com.xiaoyi.camera.module.b.a("record_photo_time").get(this.b);
                        if (g.a().a("record_photo_time").equals(str2) || this.t == null) {
                            return;
                        }
                        this.t.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.PHOTO, null, str2, -1);
                        return;
                    case LOOP:
                        String str3 = com.xiaoyi.camera.module.b.a("loop_rec_duration").get(this.b);
                        if (g.a().a("loop_rec_duration").equals(str3) || this.t == null) {
                            return;
                        }
                        this.t.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.LOOP, null, str3, -1);
                        return;
                    case TIMELAPES:
                        if (this.s == 0) {
                            if (com.xiaoyi.camera.d.a.c()) {
                                String str4 = com.xiaoyi.camera.module.b.a("timelapse_video").get(this.b);
                                if (g.a().a("timelapse_video").equals(str4) || this.t == null) {
                                    return;
                                }
                                this.t.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.TIMELAPES, null, str4, 0);
                                return;
                            }
                            return;
                        }
                        if (this.s == 1) {
                            String str5 = com.xiaoyi.camera.module.b.a("timelapse_video_duration").get(this.b);
                            if (g.a().a("timelapse_video_duration").equals(str5) || this.t == null) {
                                return;
                            }
                            this.t.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.TIMELAPES, null, str5, 1);
                            return;
                        }
                        return;
                    case SLOW:
                        if (TextUtils.isEmpty(g.a().a("slow_motion_res"))) {
                            String str6 = com.xiaoyi.camera.module.b.a("slow_motion_rate").get(this.b);
                            if (g.a().a("slow_motion_rate").equals(str6) || this.t == null) {
                                return;
                            }
                            this.t.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.SLOW, null, str6, -1);
                            return;
                        }
                        String str7 = com.xiaoyi.camera.module.b.a("slow_motion_res").get(this.b);
                        if (g.a().a("slow_motion_res").equals(str7) || this.t == null) {
                            return;
                        }
                        this.t.a(CameraMainController.CameraMode.RecordMode, Constant.RecordMode.SLOW, null, str7, -1);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void a(int i, int i2, int i3, boolean z) {
        this.n = i;
        this.o = i2;
        this.p = i3;
        this.q = z;
    }

    public void a(a aVar) {
        this.t = aVar;
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
                this.f4800a = i2;
            }
            i = i2 + 1;
        }
    }

    public void b() {
        super.show();
        new Handler().post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.widget.b.1
            @Override // java.lang.Runnable
            public void run() {
                TranslateAnimation translateAnimation = new TranslateAnimation(b.this.i.getWidth(), 0.0f, 0.0f, 0.0f);
                translateAnimation.setFillEnabled(true);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaomi.xy.sportscamera.camera.widget.b.1.1
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
                b.this.i.startAnimation(translateAnimation);
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
                this.f4800a = i2;
            }
            i = i2 + 1;
        }
    }

    public void c(String str) {
        int i = 0;
        if (this.s == 0) {
            this.d = com.xiaoyi.camera.module.b.a("timelapse_video").size();
            while (i < this.d) {
                if (str.equals(com.xiaoyi.camera.module.b.a("timelapse_video").get(i))) {
                    this.f4800a = i;
                    return;
                }
                i++;
            }
            return;
        }
        if (this.s == 1) {
            this.d = com.xiaoyi.camera.module.b.a("timelapse_video_duration").size();
            while (i < this.d) {
                if (str.equals(com.xiaoyi.camera.module.b.a("timelapse_video_duration").get(i))) {
                    this.f4800a = i;
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
                this.f4800a = i2;
                return;
            }
            i = i2 + 1;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.c && this.b != this.f4800a) {
            a();
        } else if (this.t != null) {
            this.t.a(this.e, this.f, this.g, null, this.s);
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
                this.f4800a = i2;
                return;
            }
            i = i2 + 1;
        }
    }

    public void f(String str) {
        this.d = com.xiaoyi.camera.module.b.a("precise_selftime").size();
        for (int i = 0; i < this.d; i++) {
            if (str.equals(com.xiaoyi.camera.module.b.a("precise_selftime").get(i))) {
                this.f4800a = i;
                return;
            }
        }
    }

    public void g(String str) {
        e.d();
        this.d = e.f4859a.length;
        for (int i = 0; i < this.d; i++) {
            if (str.equals(e.b[i])) {
                this.f4800a = i;
                return;
            }
        }
    }

    public void h(String str) {
        this.d = com.xiaoyi.camera.module.b.a("precise_cont_time").size();
        for (int i = 0; i < this.d; i++) {
            if (str.equals(com.xiaoyi.camera.module.b.a("precise_cont_time").get(i))) {
                this.f4800a = i;
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
        this.i = (RelativeLayout) findViewById(R.id.parentView);
        this.j = (CameraSettingView) findViewById(R.id.settingView);
        this.j.a(this.n, this.o, this.q);
        this.k = (TextView) findViewById(R.id.tvSettingTitle);
        this.k.setText(this.r);
        this.k.setLayoutParams(new LinearLayout.LayoutParams(this.n - (this.o / 4), -2));
        this.l = (RecyclerView) findViewById(R.id.lvSettingOptions);
        this.l.setLayoutParams(new LinearLayout.LayoutParams(this.n - (this.o / 4), -2));
        this.l.setLayoutManager(new StaggeredGridLayoutManager(1, 0));
        this.l.setAdapter(this.m);
        this.l.a(this.f4800a);
    }
}
