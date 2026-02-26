package com.xiaomi.xy.sportscamera.camera.album;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.rp.rpfiledatapool.model.RPFile;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b;
import com.yiaction.common.imageloader.i;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.g;

@g(a = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0013H\u0016J\u0016\u0010\u001c\u001a\u00020\r2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005J\u000e\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0006R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaItemAdapter;", "Landroid/support/v4/view/PagerAdapter;", "context", "Landroid/app/Activity;", "list", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "(Landroid/app/Activity;Ljava/util/List;)V", "inflater", "Landroid/view/LayoutInflater;", "mContext", "mList", "destroyItem", "", "container", "Landroid/view/ViewGroup;", "position", "", "object", "", "getCount", "getItemPosition", "instantiateItem", "Landroid/view/View;", "isViewFromObject", "", "arg0", "arg1", "setData", "toYIPlay", "file", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class CameraMediaItemAdapter extends PagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Activity f4492a;
    private LayoutInflater b;
    private List<? extends RPFile> c;

    public CameraMediaItemAdapter(Activity activity, List<? extends RPFile> list) {
        kotlin.jvm.internal.g.b(activity, "context");
        this.f4492a = activity;
        LayoutInflater from = LayoutInflater.from(this.f4492a);
        kotlin.jvm.internal.g.a((Object) from, "LayoutInflater.from(mContext)");
        this.b = from;
        this.c = list;
    }

    @Override // android.support.v4.view.PagerAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public View instantiateItem(ViewGroup viewGroup, int i) {
        kotlin.jvm.internal.g.b(viewGroup, "container");
        View inflate = this.b.inflate(R.layout.fragment_qz_photo_gallery, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.ivVideoThumb);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        ImageView imageView = (ImageView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.photoType);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        ImageView imageView2 = (ImageView) findViewById2;
        List<? extends RPFile> list = this.c;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        RPFile rPFile = list.get(i);
        imageView2.setTag(rPFile);
        if (rPFile.getType() == 2) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        i.a(this.f4492a, b.f4624a.a(rPFile), imageView, rPFile.getType() == 2 ? R.drawable.album_details_video : R.drawable.album_details_photo);
        viewGroup.addView(inflate);
        kotlin.jvm.internal.g.a((Object) inflate, Promotion.ACTION_VIEW);
        return inflate;
    }

    public final void a(List<? extends RPFile> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        kotlin.jvm.internal.g.b(viewGroup, "container");
        kotlin.jvm.internal.g.b(obj, "object");
        viewGroup.removeView((View) obj);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        List<? extends RPFile> list = this.c;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        return list.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        kotlin.jvm.internal.g.b(view, "arg0");
        kotlin.jvm.internal.g.b(obj, "arg1");
        return kotlin.jvm.internal.g.a(view, (View) obj);
    }
}
