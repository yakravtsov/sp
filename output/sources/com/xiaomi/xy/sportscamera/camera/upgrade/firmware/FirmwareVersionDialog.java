package com.xiaomi.xy.sportscamera.camera.upgrade.firmware;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ants360.z13.fragment.DimPanelFragment;
import com.xiaomi.xy.sportscamera.R;
import java.util.List;

/* loaded from: classes3.dex */
public class FirmwareVersionDialog extends DimPanelFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public c f4783a;
    private TextView b;
    private ListView c;

    /* loaded from: classes3.dex */
    private class a extends BaseAdapter {
        private int b;
        private List<String> c;
        private LayoutInflater d;

        public a(int i, List<String> list) {
            this.b = i;
            this.c = list;
            this.d = LayoutInflater.from(FirmwareVersionDialog.this.getActivity());
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.c == null) {
                return 0;
            }
            return this.c.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                bVar = new b();
                view = this.d.inflate(R.layout.camera_download_list_version_dialog_item, (ViewGroup) null);
                bVar.f4785a = (TextView) view.findViewById(R.id.tvVersion);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            bVar.f4785a.setText(this.c.get(i));
            if (this.b == i) {
                bVar.f4785a.setTextColor(FirmwareVersionDialog.this.getResources().getColor(R.color.primary_green));
            } else {
                bVar.f4785a.setTextColor(FirmwareVersionDialog.this.getResources().getColor(R.color.tag_title_color));
            }
            return view;
        }
    }

    /* loaded from: classes3.dex */
    private class b {

        /* renamed from: a, reason: collision with root package name */
        public TextView f4785a;

        private b() {
        }
    }

    /* loaded from: classes3.dex */
    public interface c {
        void a(int i);
    }

    @Override // com.ants360.z13.fragment.DimPanelFragment
    protected int a() {
        return R.layout.camera_download_list_version_dialog;
    }

    public void a(c cVar) {
        this.f4783a = cVar;
    }

    @Override // com.ants360.z13.fragment.DimPanelFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.b = (TextView) onCreateView.findViewById(R.id.tvTitle);
        this.c = (ListView) onCreateView.findViewById(R.id.lvVersion);
        this.b.setText(getArguments().getString("VERSION_TITLE"));
        this.c.setAdapter((ListAdapter) new a(getArguments().getInt("VERSION_POSITION"), getArguments().getStringArrayList("VERSION_LIST")));
        this.c.setOnItemClickListener(this);
        return onCreateView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.f4783a != null) {
            this.f4783a.a(i);
        }
        dismissAllowingStateLoss();
    }
}
