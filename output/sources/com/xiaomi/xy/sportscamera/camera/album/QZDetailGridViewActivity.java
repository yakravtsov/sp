package com.xiaomi.xy.sportscamera.camera.album;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.fragment.XYProgressDialogFragment;
import com.ants360.z13.widget.CustomTitleBarForAlbum;
import com.ants360.z13.widget.DoubleButtonMenu;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.rp.rpfiledatapool.model.RPFile;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.b;
import com.yiaction.common.imageloader.i;
import com.yiaction.common.util.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.g;
import wseemann.media.BuildConfig;

@kotlin.g(a = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n*\u00022$\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0002VWB\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010<\u001a\u00020=J\b\u0010>\u001a\u00020=H\u0002J\u0006\u0010?\u001a\u00020=J\u0006\u0010@\u001a\u00020=J\u0006\u0010A\u001a\u00020=J\b\u0010B\u001a\u00020=H\u0016J\u0012\u0010C\u001a\u00020=2\b\u0010D\u001a\u0004\u0018\u00010EH\u0014J\b\u0010F\u001a\u00020=H\u0014J0\u0010G\u001a\u00020=2\f\u0010H\u001a\b\u0012\u0002\b\u0003\u0018\u00010I2\b\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010L\u001a\u00020\u00162\u0006\u0010M\u001a\u00020NH\u0016J0\u0010O\u001a\u00020!2\f\u0010H\u001a\b\u0012\u0002\b\u0003\u0018\u00010I2\b\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010L\u001a\u00020\u00162\u0006\u0010M\u001a\u00020NH\u0016J\b\u0010P\u001a\u00020=H\u0002J\u000e\u0010Q\u001a\u00020=2\u0006\u0010R\u001a\u00020\u0019J\u0006\u0010S\u001a\u00020=J\u0006\u0010T\u001a\u00020=J\b\u0010U\u001a\u00020=H\u0002R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0004\n\u0002\u0010%R\u001a\u0010&\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0010\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103R \u00104\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001b\"\u0004\b6\u0010\u001dR\u000e\u00107\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity;", "Lcom/ants360/z13/activity/BaseActivity;", "Landroid/widget/AdapterView$OnItemClickListener;", "Landroid/widget/AdapterView$OnItemLongClickListener;", "()V", "adapter", "Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$PhotoGridViewAdapter;", "bottomMenu", "Lcom/ants360/z13/widget/DoubleButtonMenu;", "getBottomMenu", "()Lcom/ants360/z13/widget/DoubleButtonMenu;", "setBottomMenu", "(Lcom/ants360/z13/widget/DoubleButtonMenu;)V", "deleteDialog", "Lcom/ants360/z13/fragment/CustomBottomDialogFragment;", "getDeleteDialog", "()Lcom/ants360/z13/fragment/CustomBottomDialogFragment;", "setDeleteDialog", "(Lcom/ants360/z13/fragment/CustomBottomDialogFragment;)V", "deleteProgressDialog", "Lcom/ants360/z13/fragment/XYProgressDialogFragment;", "failCount", "", "fileItems", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "getFileItems", "()Ljava/util/List;", "setFileItems", "(Ljava/util/List;)V", "gridView", "Landroid/widget/GridView;", "isDeletingPhotoSet", "", "isInMultySelect", "mCameraFileControl", "com/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$mCameraFileControl$1", "Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$mCameraFileControl$1;", "mProgress", "getMProgress", "()I", "setMProgress", "(I)V", "nameType", "", "getNameType", "()Ljava/lang/String;", "setNameType", "(Ljava/lang/String;)V", "receiver", "com/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$receiver$1", "Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$receiver$1;", "selectFileItems", "getSelectFileItems", "setSelectFileItems", "successCount", "titleBar", "Lcom/ants360/z13/widget/CustomTitleBarForAlbum;", "tvNoPhoto", "Landroid/widget/TextView;", "cancelSelectMode", "", "cancelSelectaAllFile", "downloadFile", "enterSelectAllMode", "enterSelectMode", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onItemClick", "parent", "Landroid/widget/AdapterView;", Promotion.ACTION_VIEW, "Landroid/view/View;", "position", "id", "", "onItemLongClick", "selectAllFile", "selectFile", "file", "showDeleteDialog", "showDeleteProgress", "updateDeleteProgress", "ItemViewHodler", "PhotoGridViewAdapter", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class QZDetailGridViewActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private GridView c;
    private b d;
    private TextView e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private XYProgressDialogFragment j;
    private CustomBottomDialogFragment k;
    private List<RPFile> l;
    private CustomTitleBarForAlbum n;
    private DoubleButtonMenu o;
    private String b = "C";
    private List<RPFile> m = new ArrayList();
    private final QZDetailGridViewActivity$receiver$1 p = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.album.QZDetailGridViewActivity$receiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g.b(context, "context");
            g.b(intent, "intent");
            String action = intent.getAction();
            if (b.b) {
                if (g.a((Object) com.xiaoyi.camera.a.a("start_photo_capture"), (Object) action) || g.a((Object) com.xiaoyi.camera.a.a("start_video_record"), (Object) action) || g.a((Object) com.xiaoyi.camera.a.a("enter_album"), (Object) action)) {
                    QZDetailGridViewActivity.this.finish();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sd_card_status"), (Object) action)) {
                    String str = (String) null;
                    try {
                        str = intent.getStringExtra("current_operation_model");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (g.a((Object) ProductAction.ACTION_REMOVE, (Object) str)) {
                        QZDetailGridViewActivity.this.finish();
                    }
                }
            }
        }
    };
    private c q = new c();

    @kotlin.g(a = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$ItemViewHodler;", "", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity;)V", "info", "Lcom/xiaoyi/camera/module/FileItem;", "getInfo", "()Lcom/xiaoyi/camera/module/FileItem;", "setInfo", "(Lcom/xiaoyi/camera/module/FileItem;)V", "ivPhoto", "Landroid/widget/ImageView;", "getIvPhoto", "()Landroid/widget/ImageView;", "setIvPhoto", "(Landroid/widget/ImageView;)V", "llDownloadFlag", "Landroid/view/View;", "getLlDownloadFlag", "()Landroid/view/View;", "setLlDownloadFlag", "(Landroid/view/View;)V", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public final class a {
        private ImageView b;
        private View c;

        public a() {
        }

        public final ImageView a() {
            return this.b;
        }

        public final void a(View view) {
            this.c = view;
        }

        public final void a(ImageView imageView) {
            this.b = imageView;
        }

        public final View b() {
            return this.c;
        }
    }

    @kotlin.g(a = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\bH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\bH\u0016J$\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$PhotoGridViewAdapter;", "Landroid/widget/BaseAdapter;", "context", "Landroid/content/Context;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity;Landroid/content/Context;)V", "inflater", "Landroid/view/LayoutInflater;", "listItemHeight", "", "listItemWidth", "getCount", "getItem", "Lcom/rp/rpfiledatapool/model/RPFile;", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public final class b extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ QZDetailGridViewActivity f4526a;
        private final LayoutInflater b;
        private final int c;
        private final int d;

        public b(QZDetailGridViewActivity qZDetailGridViewActivity, Context context) {
            kotlin.jvm.internal.g.b(context, "context");
            this.f4526a = qZDetailGridViewActivity;
            LayoutInflater from = LayoutInflater.from(context);
            kotlin.jvm.internal.g.a((Object) from, "LayoutInflater.from(context)");
            this.b = from;
            this.c = (com.yiaction.common.util.b.b(context) - com.yiaction.common.util.b.a(context, 12.0f)) / 4;
            this.d = this.c;
        }

        @Override // android.widget.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RPFile getItem(int i) {
            if (this.f4526a.g() == null) {
                return null;
            }
            List<RPFile> g = this.f4526a.g();
            if (g == null) {
                kotlin.jvm.internal.g.a();
            }
            return g.get(i);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.f4526a.g() == null) {
                return 0;
            }
            List<RPFile> g = this.f4526a.g();
            if (g == null) {
                kotlin.jvm.internal.g.a();
            }
            return g.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            kotlin.jvm.internal.g.b(viewGroup, "parent");
            List<RPFile> g = this.f4526a.g();
            if (g == null) {
                kotlin.jvm.internal.g.a();
            }
            RPFile rPFile = g.get(i);
            if (view == null) {
                a aVar2 = new a();
                view = this.b.inflate(R.layout.list_sub_item_camera_photo, viewGroup, false);
                AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(this.c, this.d);
                if (view == null) {
                    kotlin.jvm.internal.g.a();
                }
                view.findViewById(R.id.item).setLayoutParams(layoutParams);
                View findViewById = view.findViewById(R.id.ivPhotoThumbnail);
                if (findViewById == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                }
                aVar2.a((ImageView) findViewById);
                aVar2.a(view.findViewById(R.id.llDownloadFlag));
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                Object tag = view.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.album.QZDetailGridViewActivity.ItemViewHodler");
                }
                aVar = (a) tag;
            }
            if (rPFile.isSelect()) {
                View b = aVar.b();
                if (b == null) {
                    kotlin.jvm.internal.g.a();
                }
                b.setVisibility(0);
            } else {
                View b2 = aVar.b();
                if (b2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                b2.setVisibility(8);
            }
            String a2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a(rPFile);
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------ imageUrl = " + a2, new Object[0]);
            i.b(this.f4526a, a2, aVar.a(), R.drawable.album_list_photo);
            return view;
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$mCameraFileControl$1", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity;)V", "deleteFailed", "", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "deleteSuccess", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class c extends com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a {

        @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                QZDetailGridViewActivity.this.r();
            }
        }

        @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class b implements Runnable {
            final /* synthetic */ RPFile b;

            b(RPFile rPFile) {
                this.b = rPFile;
            }

            @Override // java.lang.Runnable
            public final void run() {
                List<RPFile> g = QZDetailGridViewActivity.this.g();
                if (g != null) {
                    g.remove(this.b);
                }
                QZDetailGridViewActivity.this.h().remove(this.b);
                b bVar = QZDetailGridViewActivity.this.d;
                if (bVar == null) {
                    kotlin.jvm.internal.g.a();
                }
                bVar.notifyDataSetChanged();
                QZDetailGridViewActivity.this.r();
                if (QZDetailGridViewActivity.this.g() != null) {
                    List<RPFile> g2 = QZDetailGridViewActivity.this.g();
                    if (g2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (!g2.isEmpty()) {
                        TextView textView = QZDetailGridViewActivity.this.e;
                        if (textView == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        textView.setVisibility(8);
                        GridView gridView = QZDetailGridViewActivity.this.c;
                        if (gridView == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        gridView.setVisibility(0);
                        return;
                    }
                }
                QZDetailGridViewActivity.this.finish();
            }
        }

        c() {
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void b(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.b(rPFile);
            QZDetailGridViewActivity.this.g++;
            QZDetailGridViewActivity.this.runOnUiThread(new b(rPFile));
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void c(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.c(rPFile);
            QZDetailGridViewActivity.this.h++;
            QZDetailGridViewActivity.this.runOnUiThread(new a());
        }
    }

    @kotlin.g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$onCreate$1", "Lcom/ants360/z13/widget/CustomTitleBarForAlbum$TitleClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity;)V", "onLeftClick", "", "onMiddleClick", "onRightBrowserClick", "onRightClick", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class d implements CustomTitleBarForAlbum.a {
        d() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void a() {
            QZDetailGridViewActivity.this.onBackPressed();
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void b() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void c() {
            if (QZDetailGridViewActivity.this.g() != null) {
                List<RPFile> g = QZDetailGridViewActivity.this.g();
                if (g == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (!g.isEmpty()) {
                    if (QZDetailGridViewActivity.this.f) {
                        QZDetailGridViewActivity.this.k();
                    } else {
                        QZDetailGridViewActivity.this.j();
                    }
                }
            }
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void d() {
        }
    }

    @kotlin.g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$onCreate$2", "Lcom/ants360/z13/widget/DoubleButtonMenu$SingleMenuClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity;)V", "onLeftClick", "", "onRightClick", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class e implements DoubleButtonMenu.a {
        e() {
        }

        @Override // com.ants360.z13.widget.DoubleButtonMenu.a
        public void a() {
            CameraApplication.f1401a.a((Boolean) true);
            QZDetailGridViewActivity.this.m();
        }

        @Override // com.ants360.z13.widget.DoubleButtonMenu.a
        public void b() {
            QZDetailGridViewActivity.this.n();
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity$showDeleteDialog$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZDetailGridViewActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class f implements DimPanelFragment.c {
        f() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            DoubleButtonMenu i = QZDetailGridViewActivity.this.i();
            if (i == null) {
                kotlin.jvm.internal.g.a();
            }
            i.setVisibility(8);
            QZDetailGridViewActivity.this.g = 0;
            QZDetailGridViewActivity.this.h = 0;
            QZDetailGridViewActivity.this.o();
            de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
            List<RPFile> h = QZDetailGridViewActivity.this.h();
            if (h != null) {
                Iterator<T> it2 = h.iterator();
                while (it2.hasNext()) {
                    com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.b((RPFile) it2.next());
                }
            }
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @kotlin.g(a = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, b = {"<anonymous>", "", "progressDialogFragment", "Lcom/ants360/z13/fragment/XYProgressDialogFragment;", "kotlin.jvm.PlatformType", "onKeyBackClicked"})
    /* loaded from: classes.dex */
    public static final class g implements XYProgressDialogFragment.a {
        g() {
        }

        @Override // com.ants360.z13.fragment.XYProgressDialogFragment.a
        public final void a(XYProgressDialogFragment xYProgressDialogFragment) {
            xYProgressDialogFragment.dismissAllowingStateLoss();
            de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
            com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.e();
            QZDetailGridViewActivity.this.a(com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.c(QZDetailGridViewActivity.this.f()));
            b bVar = QZDetailGridViewActivity.this.d;
            if (bVar == null) {
                kotlin.jvm.internal.g.a();
            }
            bVar.notifyDataSetChanged();
            QZDetailGridViewActivity.this.l();
        }
    }

    private final void p() {
        if (this.m == null) {
            this.m = new ArrayList();
        } else {
            this.m.clear();
        }
        if (this.m != null) {
            List<RPFile> list = this.l;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            for (RPFile rPFile : list) {
                rPFile.setSelect(true);
                this.m.add(rPFile);
            }
        }
    }

    private final void q() {
        if (this.m != null) {
            Iterator<RPFile> it2 = this.m.iterator();
            while (it2.hasNext()) {
                it2.next().setSelect(false);
            }
            this.m.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void r() {
        if (this.j != null) {
            XYProgressDialogFragment xYProgressDialogFragment = this.j;
            if (xYProgressDialogFragment == null) {
                kotlin.jvm.internal.g.a();
            }
            this.i++;
            xYProgressDialogFragment.a(this.i);
            XYProgressDialogFragment xYProgressDialogFragment2 = this.j;
            if (xYProgressDialogFragment2 == null) {
                kotlin.jvm.internal.g.a();
            }
            int f2 = xYProgressDialogFragment2.f();
            XYProgressDialogFragment xYProgressDialogFragment3 = this.j;
            if (xYProgressDialogFragment3 == null) {
                kotlin.jvm.internal.g.a();
            }
            if (f2 == xYProgressDialogFragment3.g()) {
                XYProgressDialogFragment xYProgressDialogFragment4 = this.j;
                if (xYProgressDialogFragment4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                xYProgressDialogFragment4.dismissAllowingStateLoss();
                de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
                l();
                a(getString(R.string.camera_album_file_delete, new Object[]{Integer.valueOf(this.g), Integer.valueOf(this.h)}));
            }
        }
    }

    public final void a(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "file");
        if (this.m == null) {
            this.m = new ArrayList();
        }
        List<RPFile> list = this.m;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        if (list.contains(rPFile)) {
            rPFile.setSelect(false);
            List<RPFile> list2 = this.m;
            if (list2 == null) {
                kotlin.jvm.internal.g.a();
            }
            list2.remove(rPFile);
            return;
        }
        rPFile.setSelect(true);
        List<RPFile> list3 = this.m;
        if (list3 == null) {
            kotlin.jvm.internal.g.a();
        }
        list3.add(rPFile);
    }

    public final void a(List<RPFile> list) {
        this.l = list;
    }

    public final String f() {
        return this.b;
    }

    public final List<RPFile> g() {
        return this.l;
    }

    public final List<RPFile> h() {
        return this.m;
    }

    public final DoubleButtonMenu i() {
        return this.o;
    }

    public final void j() {
        this.f = true;
        CustomTitleBarForAlbum customTitleBarForAlbum = this.n;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setLeftTitle(R.string.cancel);
        CustomTitleBarForAlbum customTitleBarForAlbum2 = this.n;
        if (customTitleBarForAlbum2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum2.setRightTitle(R.string.select_all);
        int size = this.m.size();
        CustomTitleBarForAlbum customTitleBarForAlbum3 = this.n;
        if (customTitleBarForAlbum3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum3.setMiddleTitle(k.a(getString(R.string.album_choose_num, new Object[]{Integer.valueOf(size)}), "item", size));
        DoubleButtonMenu doubleButtonMenu = this.o;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setVisibility(0);
        DoubleButtonMenu doubleButtonMenu2 = this.o;
        if (doubleButtonMenu2 == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu2.setButtonEnable(Boolean.valueOf(size > 0));
    }

    public final void k() {
        p();
        b bVar = this.d;
        if (bVar == null) {
            kotlin.jvm.internal.g.a();
        }
        bVar.notifyDataSetChanged();
        int size = this.m.size();
        CustomTitleBarForAlbum customTitleBarForAlbum = this.n;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setMiddleTitle(k.a(getString(R.string.album_choose_num, new Object[]{Integer.valueOf(size)}), "item", size));
        DoubleButtonMenu doubleButtonMenu = this.o;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setVisibility(0);
        DoubleButtonMenu doubleButtonMenu2 = this.o;
        if (doubleButtonMenu2 == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu2.setButtonEnable(Boolean.valueOf(size > 0));
    }

    public final void l() {
        this.f = false;
        q();
        b bVar = this.d;
        if (bVar == null) {
            kotlin.jvm.internal.g.a();
        }
        bVar.notifyDataSetChanged();
        CustomTitleBarForAlbum customTitleBarForAlbum = this.n;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setLeftBackground(R.drawable.btn_back);
        CustomTitleBarForAlbum customTitleBarForAlbum2 = this.n;
        if (customTitleBarForAlbum2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum2.setRightTitle(R.string.album_choose);
        CustomTitleBarForAlbum customTitleBarForAlbum3 = this.n;
        if (customTitleBarForAlbum3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum3.setMiddleTitle(getString(R.string.tv_burst));
        DoubleButtonMenu doubleButtonMenu = this.o;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setVisibility(8);
    }

    public final void m() {
        if (this.m != null) {
            List<RPFile> list = this.m;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!list.isEmpty()) {
                a(R.string.file_add_to_download_quee);
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.a(this.m);
                l();
                return;
            }
        }
        a("请先选择文件");
    }

    public final void n() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.title_delete));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.sure_to_delete));
        this.k = new CustomBottomDialogFragment();
        CustomBottomDialogFragment customBottomDialogFragment = this.k;
        if (customBottomDialogFragment == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment.setArguments(bundle);
        CustomBottomDialogFragment customBottomDialogFragment2 = this.k;
        if (customBottomDialogFragment2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment2.a(new f());
        CustomBottomDialogFragment customBottomDialogFragment3 = this.k;
        if (customBottomDialogFragment3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment3.a(this);
    }

    public final void o() {
        this.i = 0;
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.title_delete));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.process_delete));
        bundle.putString("middle_button", getString(R.string.cancel));
        bundle.putInt("max", this.m.size());
        this.j = new XYProgressDialogFragment();
        XYProgressDialogFragment xYProgressDialogFragment = this.j;
        if (xYProgressDialogFragment == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment.setArguments(bundle);
        XYProgressDialogFragment xYProgressDialogFragment2 = this.j;
        if (xYProgressDialogFragment2 == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment2.a(new g());
        XYProgressDialogFragment xYProgressDialogFragment3 = this.j;
        if (xYProgressDialogFragment3 == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment3.setCancelable(false);
        XYProgressDialogFragment xYProgressDialogFragment4 = this.j;
        if (xYProgressDialogFragment4 == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment4.a(this);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.f) {
            l();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail_grid_view);
        View findViewById = findViewById(R.id.titleBar);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.CustomTitleBarForAlbum");
        }
        this.n = (CustomTitleBarForAlbum) findViewById;
        CustomTitleBarForAlbum customTitleBarForAlbum = this.n;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setMiddleTitle(getResources().getString(R.string.tv_burst));
        CustomTitleBarForAlbum customTitleBarForAlbum2 = this.n;
        if (customTitleBarForAlbum2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum2.setRightBrowserVisible(false);
        CustomTitleBarForAlbum customTitleBarForAlbum3 = this.n;
        if (customTitleBarForAlbum3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum3.setTitleClickListener(new d());
        String stringExtra = getIntent().getStringExtra("type");
        kotlin.jvm.internal.g.a((Object) stringExtra, "intent.getStringExtra(\"type\")");
        this.b = stringExtra;
        this.l = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.c(this.b);
        this.d = new b(this, this);
        View findViewById2 = findViewById(R.id.tvNoPhoto);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.e = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.gridView);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.GridView");
        }
        this.c = (GridView) findViewById3;
        GridView gridView = this.c;
        if (gridView == null) {
            kotlin.jvm.internal.g.a();
        }
        gridView.setAdapter((ListAdapter) this.d);
        GridView gridView2 = this.c;
        if (gridView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        gridView2.setOnItemClickListener(this);
        GridView gridView3 = this.c;
        if (gridView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        gridView3.setOnItemLongClickListener(this);
        View findViewById4 = findViewById(R.id.bottomMenu);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.DoubleButtonMenu");
        }
        this.o = (DoubleButtonMenu) findViewById4;
        DoubleButtonMenu doubleButtonMenu = this.o;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setClickListener(new e());
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.a(this.q);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
        registerReceiver(this.p, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b(this.q);
        unregisterReceiver(this.p);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!this.f) {
            Intent intent = new Intent(this, (Class<?>) QZPhotoSetDetailShowActivity.class);
            intent.putExtra("pos", i);
            intent.putExtra("nameType", this.b);
            startActivity(intent);
            return;
        }
        List<RPFile> list = this.l;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        a(list.get(i));
        b bVar = this.d;
        if (bVar == null) {
            kotlin.jvm.internal.g.a();
        }
        bVar.notifyDataSetChanged();
        j();
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f = true;
        List<RPFile> list = this.l;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        a(list.get(i));
        b bVar = this.d;
        if (bVar == null) {
            kotlin.jvm.internal.g.a();
        }
        bVar.notifyDataSetChanged();
        j();
        return true;
    }
}
