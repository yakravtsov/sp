package com.xiaomi.xy.sportscamera.camera.album;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ants360.z13.community.BasePageFragment;
import com.rp.rpfiledatapool.model.RPFile;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a;
import com.yiaction.common.imageloader.i;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.g;

@g(a = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 +2\u00020\u0001:\u0005+,-./B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0012\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J(\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"J\u001e\u0010#\u001a\u00020\u00142\f\u0010$\u001a\b\u0018\u00010%R\u00020\u00002\u0006\u0010&\u001a\u00020'H\u0002J\u0012\u0010(\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0012\u0010)\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u001e\u0010*\u001a\u00020\u00142\f\u0010$\u001a\b\u0018\u00010%R\u00020\u00002\u0006\u0010&\u001a\u00020'H\u0002R\u0014\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment;", "Lcom/ants360/z13/community/BasePageFragment;", "()V", "downloadingAdapter", "Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadingAdapter;", "lvDownloading", "Landroid/widget/ListView;", "mDownLoadController", "Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadController;", "mFilePositionMap", "Ljava/util/HashMap;", "", "", "mHandler", "Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadHandler;", "pbDownload", "Landroid/widget/ProgressBar;", "tvStorage", "Landroid/widget/TextView;", "fetchData", "", "initStorageView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onEvent", "event", "Lcom/xforce/yi/event/ThumbLoadedEvent;", "refreshProgress", "viewHolder", "Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadViewHolder;", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "showProgress", "showSpeed", "updateSpeed", "Companion", "DownLoadController", "DownLoadHandler", "DownLoadViewHolder", "DownLoadingAdapter", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class QZAlbumDownloadFragment extends BasePageFragment {

    /* renamed from: a, reason: collision with root package name */
    public static final a f4510a = new a(null);
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;
    private static final int o = 4;
    private static final int p = 5;
    private ListView b;
    private TextView c;
    private ProgressBar d;
    private e h;
    private c i;
    private b j;
    private HashMap<String, Integer> k = new HashMap<>();

    @g(a = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0082D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$Companion;", "", "()V", "DOWNALOADING_ADAPTER", "", "getDOWNALOADING_ADAPTER", "()I", "UPDATE_DOWNLOADED", "getUPDATE_DOWNLOADED", "UPDATE_DOWNLOADING", "getUPDATE_DOWNLOADING", "UPDATE_PROGRESS", "getUPDATE_PROGRESS", "UPDATE_SPEED", "getUPDATE_SPEED", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int a() {
            return QZAlbumDownloadFragment.l;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int b() {
            return QZAlbumDownloadFragment.m;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int c() {
            return QZAlbumDownloadFragment.n;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int d() {
            return QZAlbumDownloadFragment.o;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int e() {
            return QZAlbumDownloadFragment.p;
        }
    }

    @g(a = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0004H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadController;", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment;)V", "oldSpeed", "", "downloadCancled", "", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "downloadFailed", "downloadSuccess", "startDownLoad", "updateProgress", "speed", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    private final class b extends com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a {
        private String c;

        public b() {
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void a(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.a(rPFile);
            if (QZAlbumDownloadFragment.this.getActivity() == null) {
                return;
            }
            QZAlbumDownloadFragment.this.k.remove(rPFile.getPath_dev());
            c cVar = QZAlbumDownloadFragment.this.i;
            if (cVar == null) {
                kotlin.jvm.internal.g.a();
            }
            cVar.sendEmptyMessage(QZAlbumDownloadFragment.f4510a.a());
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void a(RPFile rPFile, String str) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            kotlin.jvm.internal.g.b(str, "speed");
            super.a(rPFile, str);
            if (!kotlin.jvm.internal.g.a((Object) str, (Object) this.c)) {
                this.c = str;
                Message.obtain(QZAlbumDownloadFragment.this.i, QZAlbumDownloadFragment.f4510a.c(), rPFile).sendToTarget();
            }
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void d(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.d(rPFile);
            c cVar = QZAlbumDownloadFragment.this.i;
            if (cVar == null) {
                kotlin.jvm.internal.g.a();
            }
            cVar.sendEmptyMessage(QZAlbumDownloadFragment.f4510a.a());
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void e(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.e(rPFile);
            c cVar = QZAlbumDownloadFragment.this.i;
            if (cVar == null) {
                kotlin.jvm.internal.g.a();
            }
            cVar.sendEmptyMessage(QZAlbumDownloadFragment.f4510a.a());
        }
    }

    @g(a = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadHandler;", "Landroid/os/Handler;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment;)V", "handleMessage", "", "msg", "Landroid/os/Message;", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    private final class c extends Handler {
        public c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            kotlin.jvm.internal.g.b(message, "msg");
            super.handleMessage(message);
            int i = message.what;
            if (i == QZAlbumDownloadFragment.f4510a.d()) {
                QZAlbumDownloadFragment.this.h = new e(QZAlbumDownloadFragment.this, com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b());
                ListView listView = QZAlbumDownloadFragment.this.b;
                if (listView == null) {
                    kotlin.jvm.internal.g.a();
                }
                listView.setAdapter((ListAdapter) QZAlbumDownloadFragment.this.h);
                return;
            }
            if (i == QZAlbumDownloadFragment.f4510a.a()) {
                if (QZAlbumDownloadFragment.this.h != null) {
                    e eVar = QZAlbumDownloadFragment.this.h;
                    if (eVar == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    eVar.notifyDataSetChanged();
                    return;
                }
                return;
            }
            if (i == QZAlbumDownloadFragment.f4510a.b()) {
                List<RPFile> d = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.d();
                if (d == null) {
                    kotlin.jvm.internal.g.a();
                }
                d.isEmpty();
                return;
            }
            if (i == QZAlbumDownloadFragment.f4510a.c()) {
                Object obj = message.obj;
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.rp.rpfiledatapool.model.RPFile");
                }
                QZAlbumDownloadFragment.this.a((RPFile) obj);
                return;
            }
            if (i == QZAlbumDownloadFragment.f4510a.e()) {
                Object obj2 = message.obj;
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.rp.rpfiledatapool.model.RPFile");
                }
                QZAlbumDownloadFragment.this.b((RPFile) obj2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @g(a = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001c\u0010!\u001a\u0004\u0018\u00010\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001d¨\u0006$"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadViewHolder;", "", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment;)V", "ibDownloadController", "Landroid/widget/ImageButton;", "getIbDownloadController$ants_sports_camera_internationalRelease", "()Landroid/widget/ImageButton;", "setIbDownloadController$ants_sports_camera_internationalRelease", "(Landroid/widget/ImageButton;)V", "ivThumb", "Landroid/widget/ImageView;", "getIvThumb$ants_sports_camera_internationalRelease", "()Landroid/widget/ImageView;", "setIvThumb$ants_sports_camera_internationalRelease", "(Landroid/widget/ImageView;)V", "ivVideo", "getIvVideo$ants_sports_camera_internationalRelease", "setIvVideo$ants_sports_camera_internationalRelease", "pbDownLoad", "Landroid/widget/ProgressBar;", "getPbDownLoad$ants_sports_camera_internationalRelease", "()Landroid/widget/ProgressBar;", "setPbDownLoad$ants_sports_camera_internationalRelease", "(Landroid/widget/ProgressBar;)V", "tvDownloadSize", "Landroid/widget/TextView;", "getTvDownloadSize$ants_sports_camera_internationalRelease", "()Landroid/widget/TextView;", "setTvDownloadSize$ants_sports_camera_internationalRelease", "(Landroid/widget/TextView;)V", "tvDownloadSpeed", "getTvDownloadSpeed$ants_sports_camera_internationalRelease", "setTvDownloadSpeed$ants_sports_camera_internationalRelease", "tvFileName", "getTvFileName$ants_sports_camera_internationalRelease", "setTvFileName$ants_sports_camera_internationalRelease", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public final class d {
        private ImageView b;
        private ImageView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private ProgressBar g;
        private ImageButton h;

        public d() {
        }

        public final ImageView a() {
            return this.b;
        }

        public final void a(ImageButton imageButton) {
            this.h = imageButton;
        }

        public final void a(ImageView imageView) {
            this.b = imageView;
        }

        public final void a(ProgressBar progressBar) {
            this.g = progressBar;
        }

        public final void a(TextView textView) {
            this.d = textView;
        }

        public final ImageView b() {
            return this.c;
        }

        public final void b(ImageView imageView) {
            this.c = imageView;
        }

        public final void b(TextView textView) {
            this.e = textView;
        }

        public final TextView c() {
            return this.d;
        }

        public final void c(TextView textView) {
            this.f = textView;
        }

        public final TextView d() {
            return this.e;
        }

        public final TextView e() {
            return this.f;
        }

        public final ProgressBar f() {
            return this.g;
        }

        public final ImageButton g() {
            return this.h;
        }
    }

    @g(a = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0014\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment$DownLoadingAdapter;", "Landroid/widget/BaseAdapter;", "list", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumDownloadFragment;Ljava/util/List;)V", "getCount", "", "getItem", "", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "setData", "", "mlist", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    private final class e extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ QZAlbumDownloadFragment f4514a;
        private List<? extends RPFile> b;

        @g(a = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, b = {"<anonymous>", "", "v", "Landroid/view/View;", "onClick"})
        /* loaded from: classes.dex */
        static final class a implements View.OnClickListener {
            final /* synthetic */ RPFile b;

            a(RPFile rPFile) {
                this.b = rPFile;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                kotlin.jvm.internal.g.b(view, "v");
                e.this.f4514a.k.remove(this.b.getPath_dev());
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d dVar = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a;
                Object tag = view.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.rp.rpfiledatapool.model.RPFile");
                }
                dVar.a((RPFile) tag);
                e.this.a(com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b());
            }
        }

        public e(QZAlbumDownloadFragment qZAlbumDownloadFragment, List<? extends RPFile> list) {
            kotlin.jvm.internal.g.b(list, "list");
            this.f4514a = qZAlbumDownloadFragment;
            this.b = list;
        }

        public final void a(List<? extends RPFile> list) {
            kotlin.jvm.internal.g.b(list, "mlist");
            this.b = list;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.b.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.b.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            d dVar;
            kotlin.jvm.internal.g.b(viewGroup, "parent");
            if (view == null) {
                d dVar2 = new d();
                view = LayoutInflater.from(this.f4514a.getActivity()).inflate(R.layout.list_item_file_downloading, viewGroup, false);
                if (view == null) {
                    kotlin.jvm.internal.g.a();
                }
                View findViewById = view.findViewById(R.id.tvFileName);
                if (findViewById == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                dVar2.a((TextView) findViewById);
                View findViewById2 = view.findViewById(R.id.ivDownLoadFileThumbnail);
                if (findViewById2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                }
                dVar2.a((ImageView) findViewById2);
                View findViewById3 = view.findViewById(R.id.ivVideo);
                if (findViewById3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                }
                dVar2.b((ImageView) findViewById3);
                View findViewById4 = view.findViewById(R.id.tvDownLoadSize);
                if (findViewById4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                dVar2.b((TextView) findViewById4);
                View findViewById5 = view.findViewById(R.id.tvDownLoadSpeed);
                if (findViewById5 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                dVar2.c((TextView) findViewById5);
                View findViewById6 = view.findViewById(R.id.ibDownLoadController);
                if (findViewById6 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageButton");
                }
                dVar2.a((ImageButton) findViewById6);
                View findViewById7 = view.findViewById(R.id.pbDownload);
                if (findViewById7 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
                }
                dVar2.a((ProgressBar) findViewById7);
                view.setTag(dVar2);
                dVar = dVar2;
            } else {
                Object tag = view.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.album.QZAlbumDownloadFragment.DownLoadViewHolder");
                }
                dVar = (d) tag;
            }
            if (i < this.b.size()) {
                RPFile rPFile = this.b.get(i);
                TextView c = dVar.c();
                if (c == null) {
                    kotlin.jvm.internal.g.a();
                }
                c.setText(rPFile.getName());
                if (rPFile.getType() == 2) {
                    ImageView b = dVar.b();
                    if (b == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    b.setVisibility(0);
                } else {
                    ImageView b2 = dVar.b();
                    if (b2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    b2.setVisibility(8);
                }
                i.a(this.f4514a.getActivity(), com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a(rPFile), dVar.a(), R.drawable.pic_default);
                ProgressBar f = dVar.f();
                if (f == null) {
                    kotlin.jvm.internal.g.a();
                }
                f.setTag(rPFile.getPath_dev());
                com.yiaction.common.util.g.a("max:" + ((int) (rPFile.getSize() / 1024)) + "progress:" + (((int) rPFile.getCurrentSize()) / 1024), new Object[0]);
                TextView d = dVar.d();
                if (d == null) {
                    kotlin.jvm.internal.g.a();
                }
                d.setTag(rPFile.getPath_dev());
                this.f4514a.a(dVar, rPFile);
                if (kotlin.jvm.internal.g.a(rPFile.getDownLoadState(), RPFile.DownLoadState.DOWNLOADING)) {
                    if (TextUtils.isEmpty(rPFile.getSpeed())) {
                        TextView e = dVar.e();
                        if (e == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        e.setText("0 B/S");
                    } else {
                        TextView e2 = dVar.e();
                        if (e2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        e2.setText(rPFile.getSpeed());
                    }
                    ImageButton g = dVar.g();
                    if (g != null) {
                        g.setVisibility(0);
                    }
                    ImageButton g2 = dVar.g();
                    if (g2 != null) {
                        g2.setBackgroundResource(R.drawable.close_button);
                    }
                } else if (kotlin.jvm.internal.g.a(rPFile.getDownLoadState(), RPFile.DownLoadState.DOWNLOADED)) {
                    TextView e3 = dVar.e();
                    if (e3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    e3.setText("");
                    ImageButton g3 = dVar.g();
                    if (g3 != null) {
                        g3.setVisibility(8);
                    }
                } else if (kotlin.jvm.internal.g.a(rPFile.getDownLoadState(), RPFile.DownLoadState.WAIT)) {
                    TextView e4 = dVar.e();
                    if (e4 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    e4.setText("");
                    ImageButton g4 = dVar.g();
                    if (g4 != null) {
                        g4.setVisibility(0);
                    }
                    ImageButton g5 = dVar.g();
                    if (g5 != null) {
                        g5.setBackgroundResource(R.drawable.close_button);
                    }
                }
                ImageButton g6 = dVar.g();
                if (g6 == null) {
                    kotlin.jvm.internal.g.a();
                }
                g6.setTag(rPFile);
                ImageButton g7 = dVar.g();
                if (g7 == null) {
                    kotlin.jvm.internal.g.a();
                }
                g7.setOnClickListener(new a(rPFile));
                TextView e5 = dVar.e();
                if (e5 == null) {
                    kotlin.jvm.internal.g.a();
                }
                e5.setTag(rPFile.getPath_dev());
                this.f4514a.k.put(rPFile.getPath_dev(), Integer.valueOf(i));
            }
            return view;
        }
    }

    @g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            e eVar = QZAlbumDownloadFragment.this.h;
            if (eVar != null) {
                eVar.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(RPFile rPFile) {
        int i;
        if (rPFile == null || this.b == null || this.k == null || this.k.get(rPFile.getPath_dev()) == null) {
            return;
        }
        ListView listView = this.b;
        if (listView == null) {
            kotlin.jvm.internal.g.a();
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        Integer num = this.k.get(rPFile.getPath_dev());
        if (num == null) {
            kotlin.jvm.internal.g.a();
        }
        if (firstVisiblePosition <= num.intValue()) {
            Integer num2 = this.k.get(rPFile.getPath_dev());
            if (num2 == null) {
                kotlin.jvm.internal.g.a();
            }
            int intValue = num2.intValue();
            ListView listView2 = this.b;
            if (listView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            i = intValue - listView2.getFirstVisiblePosition();
        } else {
            i = -1;
        }
        ListView listView3 = this.b;
        if (listView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        View childAt = listView3.getChildAt(i);
        if (i == -1 || childAt == null) {
            return;
        }
        Object tag = childAt.getTag();
        if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.album.QZAlbumDownloadFragment.DownLoadViewHolder");
        }
        d dVar = (d) tag;
        a(dVar, rPFile);
        b(dVar, rPFile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(d dVar, RPFile rPFile) {
        String str;
        String str2;
        if (dVar == null) {
            kotlin.jvm.internal.g.a();
        }
        ProgressBar f2 = dVar.f();
        if (f2 == null) {
            kotlin.jvm.internal.g.a();
        }
        f2.setMax((int) (rPFile.getSize() / 1024));
        ProgressBar f3 = dVar.f();
        if (f3 == null) {
            kotlin.jvm.internal.g.a();
        }
        f3.setProgress((int) (rPFile.getCurrentSize() / 1024));
        com.yiaction.common.util.g.a("max:" + ((int) (rPFile.getSize() / 1024)) + "progress:" + (((int) rPFile.getCurrentSize()) / 1024), new Object[0]);
        if (rPFile.getSize() > 1073741824) {
            str = new BigDecimal(((rPFile.getSize() / 1024) / 1024.0d) / 1024.0d).setScale(1, 4).toString() + "GB";
        } else {
            str = new BigDecimal((rPFile.getSize() / 1024) / 1024).setScale(1, 4).toString() + "MB";
        }
        if (rPFile.getCurrentSize() > 1073741824) {
            str2 = new BigDecimal(((rPFile.getCurrentSize() / 1024) / 1024.0d) / 1024.0d).setScale(1, 4).toString() + "GB";
        } else {
            str2 = new BigDecimal((rPFile.getCurrentSize() / 1024) / 1024).setScale(1, 4).toString() + "MB";
        }
        TextView d2 = dVar.d();
        if (d2 == null) {
            kotlin.jvm.internal.g.a();
        }
        d2.setText(str2 + "/" + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b(RPFile rPFile) {
        int i;
        if (rPFile == null || this.b == null || this.k == null || this.k.get(rPFile.getPath_dev()) == null) {
            return;
        }
        ListView listView = this.b;
        if (listView == null) {
            kotlin.jvm.internal.g.a();
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        Integer num = this.k.get(rPFile.getPath_dev());
        if (num == null) {
            kotlin.jvm.internal.g.a();
        }
        kotlin.jvm.internal.g.a((Object) num, "mFilePositionMap[item.path_dev]!!");
        if (kotlin.jvm.internal.g.a(firstVisiblePosition, num.intValue()) <= 0) {
            Integer num2 = this.k.get(rPFile.getPath_dev());
            if (num2 == null) {
                kotlin.jvm.internal.g.a();
            }
            int intValue = num2.intValue();
            ListView listView2 = this.b;
            if (listView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            i = intValue - listView2.getFirstVisiblePosition();
        } else {
            i = -1;
        }
        ListView listView3 = this.b;
        if (listView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        View childAt = listView3.getChildAt(i);
        if (i == -1 || childAt == null) {
            return;
        }
        Object tag = childAt.getTag();
        if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.album.QZAlbumDownloadFragment.DownLoadViewHolder");
        }
        b((d) tag, rPFile);
    }

    private final void b(d dVar, RPFile rPFile) {
        if (TextUtils.isEmpty(rPFile.getSpeed())) {
            if (dVar == null) {
                kotlin.jvm.internal.g.a();
            }
            TextView e2 = dVar.e();
            if (e2 == null) {
                kotlin.jvm.internal.g.a();
            }
            e2.setText("0 B/S");
            return;
        }
        if (dVar == null) {
            kotlin.jvm.internal.g.a();
        }
        TextView e3 = dVar.e();
        if (e3 == null) {
            kotlin.jvm.internal.g.a();
        }
        e3.setText(rPFile.getSpeed());
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ad A[Catch: FormatFlagsConversionMismatchException -> 0x0237, TryCatch #0 {FormatFlagsConversionMismatchException -> 0x0237, blocks: (B:3:0x00a4, B:5:0x00aa, B:6:0x00ad, B:10:0x00c9, B:19:0x00e8, B:21:0x012b, B:24:0x00ed, B:26:0x00ff, B:30:0x0110, B:39:0x0137, B:41:0x0183, B:44:0x013c, B:47:0x0156, B:51:0x016a, B:60:0x0196, B:62:0x01d7, B:65:0x019b, B:67:0x01ad, B:71:0x01be, B:79:0x01e3, B:85:0x01e8, B:88:0x0201, B:90:0x0212, B:91:0x0215, B:93:0x021f, B:94:0x0222), top: B:2:0x00a4 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0212 A[Catch: FormatFlagsConversionMismatchException -> 0x0237, TryCatch #0 {FormatFlagsConversionMismatchException -> 0x0237, blocks: (B:3:0x00a4, B:5:0x00aa, B:6:0x00ad, B:10:0x00c9, B:19:0x00e8, B:21:0x012b, B:24:0x00ed, B:26:0x00ff, B:30:0x0110, B:39:0x0137, B:41:0x0183, B:44:0x013c, B:47:0x0156, B:51:0x016a, B:60:0x0196, B:62:0x01d7, B:65:0x019b, B:67:0x01ad, B:71:0x01be, B:79:0x01e3, B:85:0x01e8, B:88:0x0201, B:90:0x0212, B:91:0x0215, B:93:0x021f, B:94:0x0222), top: B:2:0x00a4 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x021f A[Catch: FormatFlagsConversionMismatchException -> 0x0237, TryCatch #0 {FormatFlagsConversionMismatchException -> 0x0237, blocks: (B:3:0x00a4, B:5:0x00aa, B:6:0x00ad, B:10:0x00c9, B:19:0x00e8, B:21:0x012b, B:24:0x00ed, B:26:0x00ff, B:30:0x0110, B:39:0x0137, B:41:0x0183, B:44:0x013c, B:47:0x0156, B:51:0x016a, B:60:0x0196, B:62:0x01d7, B:65:0x019b, B:67:0x01ad, B:71:0x01be, B:79:0x01e3, B:85:0x01e8, B:88:0x0201, B:90:0x0212, B:91:0x0215, B:93:0x021f, B:94:0x0222), top: B:2:0x00a4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void g() {
        /*
            Method dump skipped, instructions count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.xy.sportscamera.camera.album.QZAlbumDownloadFragment.g():void");
    }

    @Override // com.ants360.z13.community.BasePageFragment
    public void c() {
    }

    @Override // com.ants360.z13.community.BasePageFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = new b();
        this.i = new c();
        a.C0193a c0193a = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b;
        b bVar = this.j;
        if (bVar == null) {
            kotlin.jvm.internal.g.a();
        }
        c0193a.a(bVar);
        de.greenrobot.event.c.a().a(this);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            kotlin.jvm.internal.g.a();
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_download_manage, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.lvDownload);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ListView");
        }
        this.b = (ListView) findViewById;
        this.h = new e(this, com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b());
        ListView listView = this.b;
        if (listView == null) {
            kotlin.jvm.internal.g.a();
        }
        listView.setAdapter((ListAdapter) this.h);
        View findViewById2 = inflate.findViewById(R.id.tvStorage);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.c = (TextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.pbDownload);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
        }
        this.d = (ProgressBar) findViewById3;
        g();
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a.C0193a c0193a = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b;
        b bVar = this.j;
        if (bVar == null) {
            kotlin.jvm.internal.g.a();
        }
        c0193a.b(bVar);
        de.greenrobot.event.c.a().b(this);
    }

    public final void onEvent(com.a.a.a.b bVar) {
        kotlin.jvm.internal.g.b(bVar, "event");
        getActivity().runOnUiThread(new f());
    }
}
