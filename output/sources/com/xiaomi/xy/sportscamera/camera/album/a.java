package com.xiaomi.xy.sportscamera.camera.album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.sticky.gridview.d;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.rp.rpfiledatapool.model.RPFile;
import com.xiaomi.xy.sportscamera.R;
import com.yiaction.common.imageloader.i;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.g;
import wseemann.media.BuildConfig;

@g(a = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002:\u000212B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u001d\u001a\u00020\u0012J\b\u0010\u001e\u001a\u00020\u0018H\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0018H\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010!\u001a\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010!\u001a\u00020\u0018H\u0016J\u0010\u0010)\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0018H\u0016J\u0006\u0010*\u001a\u00020\u0012J&\u0010+\u001a\u0004\u0018\u00010#2\u0006\u0010!\u001a\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0016\u0010,\u001a\u00020-2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bJ\u000e\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u0012R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u00063"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter;", "Landroid/widget/BaseAdapter;", "Lcom/ants360/z13/sticky/gridview/StickyGridHeadersSimpleAdapter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "mFileList", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "getMFileList", "()Ljava/util/List;", "setMFileList", "(Ljava/util/List;)V", "mIsSelectMode", "", "getMIsSelectMode", "()Z", "setMIsSelectMode", "(Z)V", "mItenWidth", "", "getMItenWidth", "()I", "setMItenWidth", "(I)V", "canSeelct", "getCount", "getHeaderId", "", "position", "getHeaderView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "getItem", "", "getItemId", "getSelectMode", "getView", "setData", "", "list", "setSelectMode", "isSelect", "HeadViewHolder", "ViewHolder", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class a extends BaseAdapter implements d {

    /* renamed from: a, reason: collision with root package name */
    private Context f4541a;
    private List<? extends RPFile> b;
    private int c;
    private boolean d;

    @g(a = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter$HeadViewHolder;", "", Promotion.ACTION_VIEW, "Landroid/view/View;", "(Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter;Landroid/view/View;)V", "head_title", "Landroid/widget/TextView;", "getHead_title", "()Landroid/widget/TextView;", "setHead_title", "(Landroid/widget/TextView;)V", "ants_sports_camera_internationalRelease"})
    /* renamed from: com.xiaomi.xy.sportscamera.camera.album.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class C0190a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f4542a;
        private TextView b;

        public C0190a(a aVar, View view) {
            kotlin.jvm.internal.g.b(view, Promotion.ACTION_VIEW);
            this.f4542a = aVar;
            View findViewById = view.findViewById(R.id.albumTitle);
            if (findViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            this.b = (TextView) findViewById;
        }

        public final TextView a() {
            return this.b;
        }
    }

    @g(a = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016¨\u0006 "}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter$ViewHolder;", "", Promotion.ACTION_VIEW, "Landroid/view/View;", "(Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter;Landroid/view/View;)V", "imageView_check", "Landroid/widget/FrameLayout;", "getImageView_check", "()Landroid/widget/FrameLayout;", "setImageView_check", "(Landroid/widget/FrameLayout;)V", "imageView_count", "Landroid/widget/TextView;", "getImageView_count", "()Landroid/widget/TextView;", "setImageView_count", "(Landroid/widget/TextView;)V", "imageView_image", "Landroid/widget/ImageView;", "getImageView_image", "()Landroid/widget/ImageView;", "setImageView_image", "(Landroid/widget/ImageView;)V", "imageView_item", "Landroid/widget/RelativeLayout;", "getImageView_item", "()Landroid/widget/RelativeLayout;", "setImageView_item", "(Landroid/widget/RelativeLayout;)V", "imageView_type", "getImageView_type", "setImageView_type", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public final class b {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f4543a;
        private RelativeLayout b;
        private ImageView c;
        private FrameLayout d;
        private ImageView e;
        private TextView f;

        public b(a aVar, View view) {
            kotlin.jvm.internal.g.b(view, Promotion.ACTION_VIEW);
            this.f4543a = aVar;
            View findViewById = view.findViewById(R.id.item);
            if (findViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
            }
            this.b = (RelativeLayout) findViewById;
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(aVar.a(), aVar.a());
            RelativeLayout relativeLayout = this.b;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setLayoutParams(layoutParams);
            View findViewById2 = view.findViewById(R.id.iv_image);
            if (findViewById2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            this.c = (ImageView) findViewById2;
            View findViewById3 = view.findViewById(R.id.fl_check);
            if (findViewById3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout");
            }
            this.d = (FrameLayout) findViewById3;
            View findViewById4 = view.findViewById(R.id.iv_type);
            if (findViewById4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            this.e = (ImageView) findViewById4;
            View findViewById5 = view.findViewById(R.id.tvPhotoCount);
            if (findViewById5 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            this.f = (TextView) findViewById5;
        }

        public final ImageView a() {
            return this.c;
        }

        public final FrameLayout b() {
            return this.d;
        }

        public final ImageView c() {
            return this.e;
        }

        public final TextView d() {
            return this.f;
        }
    }

    public a(Context context) {
        kotlin.jvm.internal.g.b(context, "context");
        Context applicationContext = context.getApplicationContext();
        kotlin.jvm.internal.g.a((Object) applicationContext, "context.applicationContext");
        this.f4541a = applicationContext;
        this.c = (com.yiaction.common.util.b.b(context) - (com.yiaction.common.util.b.a(context, 4.0f) * 3)) / 4;
    }

    public final int a() {
        return this.c;
    }

    @Override // com.ants360.z13.sticky.gridview.d
    public long a(int i) {
        List<? extends RPFile> list = this.b;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        return list.get(i).getHeadId();
    }

    @Override // com.ants360.z13.sticky.gridview.d
    public View a(int i, View view, ViewGroup viewGroup) {
        C0190a c0190a;
        String str;
        if (view == null) {
            view = LayoutInflater.from(this.f4541a).inflate(R.layout.album_item_header, viewGroup, false);
            kotlin.jvm.internal.g.a((Object) view, "mConvertView");
            C0190a c0190a2 = new C0190a(this, view);
            view.setTag(c0190a2);
            c0190a = c0190a2;
        } else {
            Object tag = view.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.album.CameraMediaAdapter.HeadViewHolder");
            }
            c0190a = (C0190a) tag;
        }
        if (this.b != null) {
            List<? extends RPFile> list = this.b;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            if (i < list.size()) {
                List<? extends RPFile> list2 = this.b;
                if (list2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                RPFile rPFile = list2.get(i);
                if (rPFile.getHeadId() > 0) {
                    str = rPFile.getDate();
                    kotlin.jvm.internal.g.a((Object) str, "info.date");
                    TextView a2 = c0190a.a();
                    if (a2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    a2.setGravity(16);
                } else {
                    str = "";
                }
                TextView a3 = c0190a.a();
                if (a3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                a3.setText(str);
            }
        }
        return view;
    }

    public final void a(List<? extends RPFile> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean b() {
        return this.d;
    }

    public final boolean c() {
        if (this.b != null) {
            List<? extends RPFile> list = this.b;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!list.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        List<? extends RPFile> list = this.b;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        return list.size();
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
            view = LayoutInflater.from(this.f4541a).inflate(R.layout.layout_qz_album_item, (ViewGroup) null);
            kotlin.jvm.internal.g.a((Object) view, "mConvertView");
            b bVar2 = new b(this, view);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            Object tag = view.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.album.CameraMediaAdapter.ViewHolder");
            }
            bVar = (b) tag;
        }
        List<? extends RPFile> list = this.b;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        RPFile rPFile = list.get(i);
        TextView d = bVar.d();
        if (d == null) {
            kotlin.jvm.internal.g.a();
        }
        d.setVisibility(8);
        if (rPFile.getType() == 2) {
            ImageView c = bVar.c();
            if (c == null) {
                kotlin.jvm.internal.g.a();
            }
            c.setVisibility(0);
            ImageView c2 = bVar.c();
            if (c2 == null) {
                kotlin.jvm.internal.g.a();
            }
            c2.setImageResource(R.drawable.ic_play);
        } else if (rPFile.getType() != 1) {
            ImageView c3 = bVar.c();
            if (c3 == null) {
                kotlin.jvm.internal.g.a();
            }
            c3.setVisibility(8);
        } else if (rPFile.isMultiPhoto()) {
            ImageView c4 = bVar.c();
            if (c4 == null) {
                kotlin.jvm.internal.g.a();
            }
            c4.setVisibility(0);
            ImageView c5 = bVar.c();
            if (c5 == null) {
                kotlin.jvm.internal.g.a();
            }
            c5.setImageResource(R.drawable.camera_multi_photo);
            com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b bVar3 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a;
            String nameType = rPFile.getNameType();
            kotlin.jvm.internal.g.a((Object) nameType, "file.nameType");
            int size = bVar3.c(nameType).size();
            if (size > 0) {
                TextView d2 = bVar.d();
                if (d2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                d2.setVisibility(0);
                TextView d3 = bVar.d();
                if (d3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                d3.setText(String.valueOf(size));
            }
        } else {
            ImageView c6 = bVar.c();
            if (c6 == null) {
                kotlin.jvm.internal.g.a();
            }
            c6.setVisibility(8);
        }
        String a2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a(rPFile);
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------ imageUrl = " + a2, new Object[0]);
        i.b(this.f4541a, a2, bVar.a(), rPFile.getType() == 2 ? R.drawable.album_list_video : R.drawable.album_list_photo);
        if (this.d && rPFile.isSelect()) {
            FrameLayout b2 = bVar.b();
            if (b2 == null) {
                kotlin.jvm.internal.g.a();
            }
            b2.setVisibility(0);
        } else {
            FrameLayout b3 = bVar.b();
            if (b3 == null) {
                kotlin.jvm.internal.g.a();
            }
            b3.setVisibility(8);
        }
        return view;
    }
}
