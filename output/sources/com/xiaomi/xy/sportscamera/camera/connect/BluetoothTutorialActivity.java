package com.xiaomi.xy.sportscamera.camera.connect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.v;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.widget.CustomTitleBar;
import com.xiaomi.xy.sportscamera.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class BluetoothTutorialActivity extends BaseActivity {
    private int b;
    private int c;

    @BindView(R.id.category)
    TextView category;
    private c d;
    private a e;

    @BindView(R.id.emptyView)
    View emptyView;
    private List<TutorialType> f;
    private int[] g;
    private int[] h;
    private int[] i;
    private TutorialType j = TutorialType.ACTION_CAMERA_1;

    @BindView(R.id.list_view)
    RecyclerView listView;

    @BindView(R.id.list_layout)
    LinearLayout list_layout;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    @BindView(R.id.tutorial_view)
    RecyclerView tutorial_view;

    /* loaded from: classes3.dex */
    public enum TutorialType {
        ACTION_CAMERA_1("YI Camera 1"),
        ACTION_CAMERA_4K("YI 4K Camera");

        private String mode;

        TutorialType(String str) {
            this.mode = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mode;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class a extends RecyclerView.a<ViewOnClickListenerC0192a> {
        private List<TutorialType> b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class ViewOnClickListenerC0192a extends RecyclerView.t implements View.OnClickListener {
            public TextView n;
            public ImageView o;
            public View p;
            public b q;

            public ViewOnClickListenerC0192a(View view, b bVar) {
                super(view);
                this.q = bVar;
                this.o = (ImageView) view.findViewById(R.id.select);
                this.n = (TextView) view.findViewById(R.id.title);
                this.p = view;
                this.p.setOnClickListener(this);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (this.q != null) {
                    this.q.a(d());
                }
            }
        }

        public a() {
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
        public ViewOnClickListenerC0192a b(ViewGroup viewGroup, int i) {
            return new ViewOnClickListenerC0192a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_select, (ViewGroup) null), new b() { // from class: com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity.a.1
                @Override // com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity.b
                public void a(int i2) {
                    TutorialType tutorialType = (TutorialType) BluetoothTutorialActivity.this.f.get(i2);
                    if (BluetoothTutorialActivity.this.j != tutorialType) {
                        BluetoothTutorialActivity.this.j = tutorialType;
                        BluetoothTutorialActivity.this.category.setText(BluetoothTutorialActivity.this.j.toString());
                        BluetoothTutorialActivity.this.e.f();
                        BluetoothTutorialActivity.this.g();
                    }
                    BluetoothTutorialActivity.this.onEmptyViewClick();
                }
            });
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public void a(ViewOnClickListenerC0192a viewOnClickListenerC0192a, int i) {
            TutorialType tutorialType = this.b.get(i);
            viewOnClickListenerC0192a.n.setText(tutorialType.toString());
            if (BluetoothTutorialActivity.this.j.equals(tutorialType)) {
                viewOnClickListenerC0192a.n.setTextColor(BluetoothTutorialActivity.this.b);
                viewOnClickListenerC0192a.o.setVisibility(0);
            } else {
                viewOnClickListenerC0192a.n.setTextColor(BluetoothTutorialActivity.this.c);
                viewOnClickListenerC0192a.o.setVisibility(8);
            }
        }

        public void a(List<TutorialType> list) {
            this.b = list;
        }
    }

    /* loaded from: classes3.dex */
    public interface b {
        void a(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class c extends RecyclerView.a<a> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public class a extends RecyclerView.t {
            public TextView n;
            public ImageView o;
            public ImageView p;

            public a(View view) {
                super(view);
                this.o = (ImageView) view.findViewById(R.id.iv_num);
                this.p = (ImageView) view.findViewById(R.id.iv_tutorial);
                this.n = (TextView) view.findViewById(R.id.tv_tutorial);
            }
        }

        public c() {
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public int a() {
            if (BluetoothTutorialActivity.this.g == null) {
                return 0;
            }
            return BluetoothTutorialActivity.this.g.length;
        }

        @Override // android.support.v7.widget.RecyclerView.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_bluetooth_tutorial_item, (ViewGroup) null));
        }

        @Override // android.support.v7.widget.RecyclerView.a
        public void a(a aVar, int i) {
            aVar.o.setImageResource(BluetoothTutorialActivity.this.g[i]);
            aVar.n.setText(BluetoothTutorialActivity.this.h[i]);
            aVar.p.setImageResource(BluetoothTutorialActivity.this.i[i]);
        }
    }

    private void f() {
        this.titleBar.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.connect.BluetoothTutorialActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                BluetoothTutorialActivity.this.finish();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        this.b = getResources().getColor(R.color.content_text_color);
        this.c = getResources().getColor(R.color.sign_color);
        this.f = new ArrayList();
        this.f.add(TutorialType.ACTION_CAMERA_1);
        this.f.add(TutorialType.ACTION_CAMERA_4K);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.b(1);
        this.listView.setLayoutManager(linearLayoutManager);
        this.listView.setItemAnimator(new v());
        this.listView.setHasFixedSize(true);
        this.e = new a();
        this.e.a(this.f);
        this.listView.setAdapter(this.e);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.b(1);
        this.tutorial_view.setLayoutManager(linearLayoutManager2);
        this.tutorial_view.setItemAnimator(new v());
        this.tutorial_view.setHasFixedSize(true);
        this.d = new c();
        this.tutorial_view.setAdapter(this.d);
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (TutorialType.ACTION_CAMERA_1.equals(this.j)) {
            this.g = new int[]{R.drawable.ic_camera_bluetooth_1, R.drawable.ic_camera_bluetooth_2};
            this.h = new int[]{R.string.camera_bluetooth_tutorial_z13_1, R.string.camera_bluetooth_tutorial_z13_2};
            this.i = new int[]{R.drawable.ic_camera_bluetooth_tutorial_1, R.drawable.ic_camera_bluetooth_tutorial_2};
        } else if (TutorialType.ACTION_CAMERA_4K.equals(this.j)) {
            this.g = new int[]{R.drawable.ic_camera_bluetooth_1, R.drawable.ic_camera_bluetooth_2, R.drawable.ic_camera_bluetooth_3};
            this.h = new int[]{R.string.camera_bluetooth_tutorial_4k_1, R.string.camera_bluetooth_tutorial_4k_2, R.string.camera_bluetooth_tutorial_4k_3};
            this.i = new int[]{R.drawable.ic_camera_bluetooth_tutorial_3, R.drawable.ic_camera_bluetooth_tutorial_4, R.drawable.ic_camera_bluetooth_tutorial_5};
        }
        this.d.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.category})
    public void onCategoryClick() {
        this.list_layout.setVisibility(0);
        this.e.f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bluetooth_tutorial);
        f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({R.id.emptyView})
    public void onEmptyViewClick() {
        this.list_layout.setVisibility(8);
    }
}
