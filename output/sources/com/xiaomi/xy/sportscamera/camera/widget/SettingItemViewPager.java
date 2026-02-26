package com.xiaomi.xy.sportscamera.camera.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.widget.NonScrollingViewPager;
import com.xiaomi.xy.sportscamera.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SettingItemViewPager extends NonScrollingViewPager {

    /* renamed from: a, reason: collision with root package name */
    public PagerAdapter f4792a;
    private LayoutInflater b;
    private List<String[]> c;

    public SettingItemViewPager(Context context) {
        this(context, null);
    }

    public SettingItemViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new ArrayList();
        this.f4792a = new PagerAdapter() { // from class: com.xiaomi.xy.sportscamera.camera.widget.SettingItemViewPager.1
            @Override // android.support.v4.view.PagerAdapter
            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            }

            @Override // android.support.v4.view.PagerAdapter
            public int getCount() {
                return SettingItemViewPager.this.c.size();
            }

            @Override // android.support.v4.view.PagerAdapter
            public Object instantiateItem(ViewGroup viewGroup, int i) {
                View inflate = SettingItemViewPager.this.b.inflate(R.layout.setting_value_item_layout, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(R.id.tvValueNum);
                TextView textView2 = (TextView) inflate.findViewById(R.id.tvValueUnit);
                String[] strArr = (String[]) SettingItemViewPager.this.c.get(i);
                if (strArr != null) {
                    int length = strArr.length;
                    if (length == 2) {
                        textView.setText(strArr[0]);
                        textView2.setText(strArr[1].toUpperCase());
                    } else if (length == 1) {
                        if (strArr[0].contains("continue")) {
                            textView.setText(CameraApplication.f1401a.i().getString(R.string.time_interval_continue_abridge));
                        } else {
                            textView.setText(strArr[0]);
                        }
                        textView2.setVisibility(8);
                    } else {
                        textView.setText("");
                        textView2.setText("");
                    }
                } else {
                    textView.setText("");
                    textView2.setText("");
                }
                viewGroup.addView(inflate);
                return inflate;
            }

            @Override // android.support.v4.view.PagerAdapter
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }
        };
        this.b = LayoutInflater.from(context);
        setAdapter(this.f4792a);
    }

    public void a(int i, boolean z) {
        setCurrentItem(i, z);
    }

    public void a(List<String[]> list) {
        if (list != null) {
            this.c = list;
            removeAllViews();
            setAdapter(this.f4792a);
            this.f4792a.notifyDataSetChanged();
        }
    }

    public void setSelectedItem(int i) {
        a(i, true);
    }
}
