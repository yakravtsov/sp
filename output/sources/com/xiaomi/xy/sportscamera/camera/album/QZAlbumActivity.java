package com.xiaomi.xy.sportscamera.camera.album;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.fragment.SDCardRemoveDialogFragment;
import com.ants360.z13.fragment.XYProgressDialogFragment;
import com.ants360.z13.sticky.gridview.StickyGridHeadersGridView;
import com.ants360.z13.widget.AlbumDownloadMenu;
import com.ants360.z13.widget.CustomTitleBarForAlbum;
import com.ants360.z13.widget.DoubleButtonMenu;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.rp.rpfiledatapool.model.RPFile;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaoyi.camera.b;
import com.xiaoyi.camera.controller.CameraMainController;
import com.yiaction.common.util.Constant;
import de.greenrobot.event.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.g;
import org.json.JSONObject;
import wseemann.media.BuildConfig;
import wseemann.media.FFmpegMediaMetadataRetriever;

@kotlin.g(a = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f*\u00029^\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010t\u001a\u00020uJ\u0006\u0010v\u001a\u00020uJ\u0006\u0010w\u001a\u00020uJ\b\u0010x\u001a\u00020uH\u0002J\u0006\u0010y\u001a\u00020uJ\u0006\u0010z\u001a\u00020uJ\u0006\u0010{\u001a\u00020uJ\u0006\u0010|\u001a\u00020uJ\b\u0010}\u001a\u00020uH\u0002J\u0010\u0010~\u001a\u00020u2\u0006\u0010\u007f\u001a\u00020,H\u0002J\t\u0010\u0080\u0001\u001a\u00020uH\u0002J\t\u0010\u0081\u0001\u001a\u00020uH\u0002J\t\u0010\u0082\u0001\u001a\u00020,H\u0002J\t\u0010\u0083\u0001\u001a\u00020uH\u0002J'\u0010\u0084\u0001\u001a\u00020u2\u0007\u0010\u0085\u0001\u001a\u00020\u00062\u0007\u0010\u0086\u0001\u001a\u00020\u00062\n\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u0001H\u0014J\t\u0010\u0089\u0001\u001a\u00020uH\u0016J\u0015\u0010\u008a\u0001\u001a\u00020u2\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001H\u0014J\t\u0010\u008d\u0001\u001a\u00020uH\u0014J\u0011\u0010\u008e\u0001\u001a\u00020u2\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001J\u0011\u0010\u008e\u0001\u001a\u00020u2\b\u0010\u008f\u0001\u001a\u00030\u0091\u0001J8\u0010\u0092\u0001\u001a\u00020u2\u000e\u0010\u0093\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u0094\u00012\n\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u00062\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0016J8\u0010\u009a\u0001\u001a\u00020,2\u000e\u0010\u0093\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u0094\u00012\n\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u00062\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0016J\t\u0010\u009b\u0001\u001a\u00020uH\u0014J\t\u0010\u009c\u0001\u001a\u00020uH\u0002J\t\u0010\u009d\u0001\u001a\u00020uH\u0002J\t\u0010\u009e\u0001\u001a\u00020uH\u0002J\u0007\u0010\u009f\u0001\u001a\u00020uJ\u0010\u0010 \u0001\u001a\u00020u2\u0007\u0010¡\u0001\u001a\u00020&J\u001b\u0010¢\u0001\u001a\u00020u2\u0007\u0010£\u0001\u001a\u00020,2\u0007\u0010\u0098\u0001\u001a\u00020\u0006H\u0002J\u0007\u0010¤\u0001\u001a\u00020uJ\u0007\u0010¥\u0001\u001a\u00020uJ\t\u0010¦\u0001\u001a\u00020uH\u0002J\t\u0010§\u0001\u001a\u00020uH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u000103X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0010\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0004\n\u0002\u0010:R\u001a\u0010;\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010F\u001a\u00020GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001a\u0010L\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010/\"\u0004\bN\u00101R\u001a\u0010O\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010/\"\u0004\bQ\u00101R\u001a\u0010R\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010=\"\u0004\bT\u0010?R\u001a\u0010U\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010=\"\u0004\bW\u0010?R\u001a\u0010X\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010=\"\u0004\bZ\u0010?R\u0010\u0010[\u001a\u0004\u0018\u00010\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010]\u001a\u00020^X\u0082\u0004¢\u0006\u0004\n\u0002\u0010_R\u0010\u0010`\u001a\u0004\u0018\u00010aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010b\u001a\u0004\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010d\u001a\b\u0012\u0004\u0012\u00020&0%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010(\"\u0004\bf\u0010*R\u001c\u0010g\u001a\u0004\u0018\u00010hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u0010\u0010m\u001a\u0004\u0018\u00010nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010o\u001a\u0004\u0018\u00010nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010s¨\u0006¨\u0001"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;", "Lcom/ants360/z13/activity/BaseActivity;", "Landroid/widget/AdapterView$OnItemClickListener;", "Landroid/widget/AdapterView$OnItemLongClickListener;", "()V", "MSG_FILE_DEV_END_UPDATE_DB_LIST", "", "MSG_FILE_DEV_END_UPDATE_FAIL", "REQUEST_CODE_MEDIA", "TAG", "", "kotlin.jvm.PlatformType", "bottomMenu", "Lcom/ants360/z13/widget/DoubleButtonMenu;", "getBottomMenu", "()Lcom/ants360/z13/widget/DoubleButtonMenu;", "setBottomMenu", "(Lcom/ants360/z13/widget/DoubleButtonMenu;)V", "deleteDialog", "Lcom/ants360/z13/fragment/CustomBottomDialogFragment;", "getDeleteDialog", "()Lcom/ants360/z13/fragment/CustomBottomDialogFragment;", "setDeleteDialog", "(Lcom/ants360/z13/fragment/CustomBottomDialogFragment;)V", "deleteProgressDialog", "Lcom/ants360/z13/fragment/XYProgressDialogFragment;", "getDeleteProgressDialog", "()Lcom/ants360/z13/fragment/XYProgressDialogFragment;", "setDeleteProgressDialog", "(Lcom/ants360/z13/fragment/XYProgressDialogFragment;)V", "downloadMenu", "Lcom/ants360/z13/widget/AlbumDownloadMenu;", "getDownloadMenu", "()Lcom/ants360/z13/widget/AlbumDownloadMenu;", "setDownloadMenu", "(Lcom/ants360/z13/widget/AlbumDownloadMenu;)V", "fileItems", "", "Lcom/rp/rpfiledatapool/model/RPFile;", "getFileItems", "()Ljava/util/List;", "setFileItems", "(Ljava/util/List;)V", "isCapturing", "", "isEnterAlbum", "isRecording", "()Z", "setRecording", "(Z)V", "mAdapter", "Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter;", "getMAdapter", "()Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter;", "setMAdapter", "(Lcom/xiaomi/xy/sportscamera/camera/album/CameraMediaAdapter;)V", "mCameraFileControl", "com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$mCameraFileControl$1", "Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$mCameraFileControl$1;", "mFailCount", "getMFailCount", "()I", "setMFailCount", "(I)V", "mFileGV", "Lcom/ants360/z13/sticky/gridview/StickyGridHeadersGridView;", "getMFileGV", "()Lcom/ants360/z13/sticky/gridview/StickyGridHeadersGridView;", "setMFileGV", "(Lcom/ants360/z13/sticky/gridview/StickyGridHeadersGridView;)V", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "setMHandler", "(Landroid/os/Handler;)V", "mIsLoaded", "getMIsLoaded", "setMIsLoaded", "mIsLoading", "getMIsLoading", "setMIsLoading", "mLoadTime", "getMLoadTime", "setMLoadTime", "mProgress", "getMProgress", "setMProgress", "mSuccessCount", "getMSuccessCount", "setMSuccessCount", "pbAlbumLoading", "Landroid/widget/ProgressBar;", "receiver", "com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$receiver$1", "Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$receiver$1;", "rlBlock", "Landroid/widget/RelativeLayout;", "sdRemoveDialog", "Lcom/ants360/z13/fragment/SDCardRemoveDialogFragment;", "selectFileItems", "getSelectFileItems", "setSelectFileItems", "titleBar", "Lcom/ants360/z13/widget/CustomTitleBarForAlbum;", "getTitleBar", "()Lcom/ants360/z13/widget/CustomTitleBarForAlbum;", "setTitleBar", "(Lcom/ants360/z13/widget/CustomTitleBarForAlbum;)V", "tvInfoAlbum", "Landroid/widget/TextView;", "tvNoFile", "getTvNoFile", "()Landroid/widget/TextView;", "setTvNoFile", "(Landroid/widget/TextView;)V", "afterUpdateDBRefreshList", "", "cancelSelectMode", "cancelSelectaAllFile", "clearList", "disconnectDialog_2", "downloadFile", "enterSelectAllMode", "enterSelectMode", "getAPPStatus", "hideOrShowDownloadMenu", "flag", "initReceiver", "initView", "isCameraOperation", "loadMoreData", "onActivityResult", "requestCode", "resultCode", ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA, "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEvent", "event", "Lcom/xforce/yi/event/DownloadFileEvent;", "Lcom/xforce/yi/event/ThumbLoadedEvent;", "onItemClick", "parent", "Landroid/widget/AdapterView;", Promotion.ACTION_VIEW, "Landroid/view/View;", "position", "id", "", "onItemLongClick", "onResume", "redrawDownloadView", "refreshDownloadMenu", "refreshFileData", "selectAllFile", "selectFile", "file", "showBlock", "show", "showDeleteDialog", "showDeleteProgress", "stop", "updateDeleteProgress", "ants_sports_camera_internationalRelease"})
/* loaded from: classes.dex */
public final class QZAlbumActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private int c;
    private CustomTitleBarForAlbum d;
    private StickyGridHeadersGridView e;
    private TextView f;
    private DoubleButtonMenu g;
    private com.xiaomi.xy.sportscamera.camera.album.a h;
    private AlbumDownloadMenu i;
    private RelativeLayout j;
    private TextView k;
    private ProgressBar l;
    private CustomBottomDialogFragment m;
    private XYProgressDialogFragment n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private boolean s;
    private int t;
    private int u;
    private List<RPFile> v;
    private boolean x;
    private boolean y;
    private SDCardRemoveDialogFragment z;
    private String b = QZAlbumActivity.class.getSimpleName();
    private List<RPFile> w = new ArrayList();
    private final int A = 1;
    private final int B = 2;
    private Handler C = new h();
    private final QZAlbumActivity$receiver$1 D = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.album.QZAlbumActivity$receiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment;
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment2;
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment3;
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment4;
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment5;
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment6;
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment7;
            SDCardRemoveDialogFragment sDCardRemoveDialogFragment8;
            g.b(context, "context");
            g.b(intent, "intent");
            String action = intent.getAction();
            if (b.b) {
                if (g.a((Object) com.xiaoyi.camera.a.a("start_photo_capture"), (Object) action)) {
                    QZAlbumActivity.this.x = true;
                    QZAlbumActivity.this.L();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("photo_taken"), (Object) action)) {
                    QZAlbumActivity.this.x = false;
                    if (QZAlbumActivity.this.l()) {
                        return;
                    }
                    QZAlbumActivity.this.a(false, 0);
                    QZAlbumActivity.this.D();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("self_capture_stop"), (Object) action)) {
                    QZAlbumActivity.this.x = false;
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("burst_complete"), (Object) action)) {
                    QZAlbumActivity.this.x = false;
                    QZAlbumActivity.this.a(false, 0);
                    QZAlbumActivity.this.D();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("precise_cont_complete"), (Object) action)) {
                    QZAlbumActivity.this.x = false;
                    QZAlbumActivity.this.a(false, 0);
                    QZAlbumActivity.this.D();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("start_video_record"), (Object) action)) {
                    QZAlbumActivity.this.d(true);
                    QZAlbumActivity.this.a(true, R.string.in_recording);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("video_record_complete"), (Object) action)) {
                    QZAlbumActivity.this.d(false);
                    QZAlbumActivity.this.a(false, 0);
                    QZAlbumActivity.this.D();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("start_fwupdate"), (Object) action)) {
                    QZAlbumActivity.this.a(R.string.camera_start_fw_update);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("STORAGE_RUNOUT"), (Object) action)) {
                    QZAlbumActivity.this.a(R.string.camera_start_usb_storage_mode);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_album"), (Object) action)) {
                    CustomTitleBarForAlbum f2 = QZAlbumActivity.this.f();
                    if (f2 != null) {
                        f2.setRightEnabled(false);
                    }
                    QZAlbumActivity.this.y = true;
                    QZAlbumActivity.this.a(true, R.string.camera_album_operation_ing);
                    QZAlbumActivity.this.F();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_album"), (Object) action)) {
                    QZAlbumActivity.this.y = false;
                    QZAlbumActivity.this.a(false, 0);
                    CustomTitleBarForAlbum f3 = QZAlbumActivity.this.f();
                    if (f3 != null) {
                        f3.setRightEnabled(true);
                    }
                    String stringExtra = intent.getStringExtra("param");
                    if (TextUtils.isEmpty(stringExtra)) {
                        QZAlbumActivity.this.D();
                        return;
                    }
                    try {
                        if (Integer.parseInt(stringExtra) > 0 || !QZAlbumActivity.this.j()) {
                            QZAlbumActivity.this.D();
                            return;
                        }
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_fwupdate"), (Object) action)) {
                    QZAlbumActivity.this.a(true, R.string.firmware_update_info);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_fwupdate"), (Object) action)) {
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_sdformat"), (Object) action)) {
                    QZAlbumActivity.this.a(true, R.string.sd_card_format);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_sdformat"), (Object) action)) {
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_mvrecover"), (Object) action)) {
                    QZAlbumActivity.this.a(true, R.string.video_file_recovery);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_mvrecover"), (Object) action)) {
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_format_done"), (Object) action)) {
                    com.a.a.b.a.f553a.a(QZAlbumActivity.this);
                    QZAlbumActivity.this.D();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_optimize_start"), (Object) action)) {
                    QZAlbumActivity.this.a(true, R.string.camera_sdcard_optimize_start);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_optimize_stop"), (Object) action)) {
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_usb_storage"), (Object) action)) {
                    QZAlbumActivity.this.a(true, R.string.camera_start_usb_storage_mode);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_usb_storage"), (Object) action)) {
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("voice_control_sample_start"), (Object) action)) {
                    QZAlbumActivity.this.a(true, R.string.camera_voice_control);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("voice_control_sample_stop"), (Object) action)) {
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_live"), (Object) action)) {
                    c.a().c(new com.xiaoyi.camera.a.a(false));
                    QZAlbumActivity.this.y();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_live"), (Object) action)) {
                    QZAlbumActivity.this.a(false, 0);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sd_card_status"), (Object) action)) {
                    String str = (String) null;
                    try {
                        str = intent.getStringExtra("current_operation_model");
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    if (g.a((Object) "insert", (Object) str)) {
                        QZAlbumActivity.this.a(QZAlbumActivity.this.getResources().getString(R.string.sd_card_inserd));
                        sDCardRemoveDialogFragment5 = QZAlbumActivity.this.z;
                        if (sDCardRemoveDialogFragment5 != null) {
                            sDCardRemoveDialogFragment6 = QZAlbumActivity.this.z;
                            if (sDCardRemoveDialogFragment6 == null) {
                                g.a();
                            }
                            if (sDCardRemoveDialogFragment6.getFragmentManager() != null) {
                                sDCardRemoveDialogFragment7 = QZAlbumActivity.this.z;
                                if (sDCardRemoveDialogFragment7 == null) {
                                    g.a();
                                }
                                if (sDCardRemoveDialogFragment7.getDialog() != null) {
                                    sDCardRemoveDialogFragment8 = QZAlbumActivity.this.z;
                                    if (sDCardRemoveDialogFragment8 == null) {
                                        g.a();
                                    }
                                    sDCardRemoveDialogFragment8.dismiss();
                                }
                            }
                        }
                        QZAlbumActivity.this.D();
                        return;
                    }
                    if (g.a((Object) ProductAction.ACTION_REMOVE, (Object) str)) {
                        QZAlbumActivity.this.b(false);
                        QZAlbumActivity.this.G();
                        sDCardRemoveDialogFragment = QZAlbumActivity.this.z;
                        if (sDCardRemoveDialogFragment != null) {
                            sDCardRemoveDialogFragment3 = QZAlbumActivity.this.z;
                            if (sDCardRemoveDialogFragment3 == null) {
                                g.a();
                            }
                            if (sDCardRemoveDialogFragment3.getDialog() != null) {
                                sDCardRemoveDialogFragment4 = QZAlbumActivity.this.z;
                                if (sDCardRemoveDialogFragment4 == null) {
                                    g.a();
                                }
                                if (sDCardRemoveDialogFragment4.getDialog().isShowing()) {
                                    return;
                                }
                            }
                        }
                        QZAlbumActivity.this.z = new SDCardRemoveDialogFragment();
                        sDCardRemoveDialogFragment2 = QZAlbumActivity.this.z;
                        if (sDCardRemoveDialogFragment2 == null) {
                            g.a();
                        }
                        sDCardRemoveDialogFragment2.a(QZAlbumActivity.this);
                    }
                }
            }
        }
    };
    private g E = new g();

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$disconnectDialog_2$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a implements DimPanelFragment.c {
        a() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZAlbumActivity.this.finish();
            QZAlbumActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            Intent intent = new Intent(QZAlbumActivity.this, (Class<?>) MainActivity.class);
            intent.setFlags(268435456);
            QZAlbumActivity.this.startActivity(intent);
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZAlbumActivity.this.finish();
            QZAlbumActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            Intent intent = new Intent(QZAlbumActivity.this, (Class<?>) MainActivity.class);
            intent.setFlags(268435456);
            QZAlbumActivity.this.startActivity(intent);
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZAlbumActivity.this.finish();
            QZAlbumActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            Intent intent = new Intent(QZAlbumActivity.this, (Class<?>) MainActivity.class);
            intent.setFlags(268435456);
            QZAlbumActivity.this.startActivity(intent);
        }
    }

    @kotlin.g(a = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$getAPPStatus$1", "Lcom/xiaoyi/camera/listener/CameraCommandListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "onReceiveErrorMessage", "", WBConstants.ACTION_LOG_TYPE_MESSAGE, "Lcom/xiaoyi/camera/CameraMessage;", "json", "Lorg/json/JSONObject;", "onReceiveMessage", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class b implements com.xiaoyi.camera.c.a {

        @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (kotlin.jvm.internal.g.a((Object) "enter_album", (Object) com.xiaoyi.camera.g.a().a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM))) {
                    RelativeLayout relativeLayout = QZAlbumActivity.this.j;
                    if (relativeLayout != null) {
                        relativeLayout.setVisibility(0);
                    }
                    TextView textView = QZAlbumActivity.this.k;
                    if (textView != null) {
                        textView.setText(R.string.camera_album_operation_ing);
                    }
                } else {
                    QZAlbumActivity.this.D();
                }
                QZAlbumActivity.this.I();
            }
        }

        @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* renamed from: com.xiaomi.xy.sportscamera.camera.album.QZAlbumActivity$b$b, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static final class RunnableC0188b implements Runnable {
            RunnableC0188b() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (QZAlbumActivity.this.L() || QZAlbumActivity.this.j()) {
                    com.xiaomi.xy.sportscamera.camera.album.a h = QZAlbumActivity.this.h();
                    if (h != null) {
                        h.notifyDataSetChanged();
                    }
                } else if (kotlin.jvm.internal.g.a((Object) "enter_album", (Object) com.xiaoyi.camera.g.a().a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM))) {
                    RelativeLayout relativeLayout = QZAlbumActivity.this.j;
                    if (relativeLayout != null) {
                        relativeLayout.setVisibility(0);
                    }
                    TextView textView = QZAlbumActivity.this.k;
                    if (textView != null) {
                        textView.setText(R.string.camera_album_operation_ing);
                    }
                } else {
                    QZAlbumActivity.this.D();
                }
                QZAlbumActivity.this.I();
            }
        }

        b() {
        }

        @Override // com.xiaoyi.camera.c.a
        public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
            kotlin.jvm.internal.g.b(dVar, WBConstants.ACTION_LOG_TYPE_MESSAGE);
            kotlin.jvm.internal.g.b(jSONObject, "json");
            String optString = jSONObject.optString("param");
            String a2 = com.xiaoyi.camera.g.a().a("system_mode");
            if (kotlin.jvm.internal.g.a((Object) "record", (Object) optString) && kotlin.jvm.internal.g.a((Object) CameraMainController.CameraMode.RecordMode.toString(), (Object) a2)) {
                com.yiaction.common.util.i.a().a("current_operation_model", 0);
            } else if (kotlin.jvm.internal.g.a((Object) "idle", (Object) optString) && kotlin.jvm.internal.g.a((Object) CameraMainController.CameraMode.CaptureMode.toString(), (Object) a2)) {
                com.yiaction.common.util.i.a().a("current_operation_model", 3);
            } else if (kotlin.jvm.internal.g.a((Object) "capture", (Object) optString) && kotlin.jvm.internal.g.a((Object) CameraMainController.CameraMode.CaptureMode.toString(), (Object) a2)) {
                String a3 = com.xiaoyi.camera.g.a().a("capture_mode");
                if (kotlin.jvm.internal.g.a((Object) Constant.CaptureMode.NORMAL.toString(), (Object) a3)) {
                    com.yiaction.common.util.i.a().a("current_operation_model", 1);
                } else if (kotlin.jvm.internal.g.a((Object) Constant.CaptureMode.BURST.toString(), (Object) a3)) {
                    com.yiaction.common.util.i.a().a("current_operation_model", 2);
                } else if (kotlin.jvm.internal.g.a((Object) Constant.CaptureMode.TIMELAPES.toString(), (Object) a3)) {
                    com.yiaction.common.util.i.a().a("current_operation_model", 3);
                } else if (kotlin.jvm.internal.g.a((Object) Constant.CaptureMode.TIMER.toString(), (Object) a3)) {
                    com.yiaction.common.util.i.a().a("current_operation_model", 4);
                }
            } else {
                com.yiaction.common.util.i.a().a("current_operation_model", -1);
            }
            QZAlbumActivity.this.runOnUiThread(new RunnableC0188b());
        }

        @Override // com.xiaoyi.camera.c.a
        public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
            QZAlbumActivity.this.runOnUiThread(new a());
        }
    }

    @kotlin.g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$initView$1", "Lcom/ants360/z13/widget/CustomTitleBarForAlbum$TitleClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "onLeftClick", "", "onMiddleClick", "onRightBrowserClick", "onRightClick", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class c implements CustomTitleBarForAlbum.a {
        c() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void a() {
            QZAlbumActivity.this.onBackPressed();
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void b() {
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void c() {
            com.xiaomi.xy.sportscamera.camera.album.a h = QZAlbumActivity.this.h();
            if (h == null) {
                kotlin.jvm.internal.g.a();
            }
            if (h.c()) {
                com.xiaomi.xy.sportscamera.camera.album.a h2 = QZAlbumActivity.this.h();
                if (h2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (h2.b()) {
                    QZAlbumActivity.this.s();
                } else {
                    QZAlbumActivity.this.r();
                }
            }
        }

        @Override // com.ants360.z13.widget.CustomTitleBarForAlbum.a
        public void d() {
        }
    }

    @kotlin.g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$initView$2", "Lcom/ants360/z13/widget/DoubleButtonMenu$SingleMenuClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "onLeftClick", "", "onRightClick", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class d implements DoubleButtonMenu.a {
        d() {
        }

        @Override // com.ants360.z13.widget.DoubleButtonMenu.a
        public void a() {
            QZAlbumActivity.this.v();
        }

        @Override // com.ants360.z13.widget.DoubleButtonMenu.a
        public void b() {
            QZAlbumActivity.this.w();
        }
    }

    @kotlin.g(a = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$initView$3", "Lcom/ants360/z13/widget/AlbumDownloadMenu$DownloadMenuListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "onCancelAll", "", "onEnterDownload", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class e implements AlbumDownloadMenu.a {
        e() {
        }

        @Override // com.ants360.z13.widget.AlbumDownloadMenu.a
        public void a() {
            QZAlbumActivity.this.startActivity(new Intent(QZAlbumActivity.this, (Class<?>) QZCameraDownloadActivity.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @kotlin.g(a = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, b = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"})
    /* loaded from: classes.dex */
    public static final class f implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        public static final f f4500a = new f();

        f() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
        }
    }

    @kotlin.g(a = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016¨\u0006\r"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$mCameraFileControl$1", "Lcom/xiaomi/xy/sportscamera/camera/filemanager/j22file/QZCameraFileControl;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "deleteFailed", "", "item", "Lcom/rp/rpfiledatapool/model/RPFile;", "deleteSuccess", "downloadSuccess", "initFileListComplete", "hasFile", "", "initFileListFail", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class g extends com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a {

        @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                QZAlbumActivity.this.H();
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
                List<RPFile> o = QZAlbumActivity.this.o();
                if (o != null) {
                    o.remove(this.b);
                }
                QZAlbumActivity.this.p().remove(this.b);
                com.xiaomi.xy.sportscamera.camera.album.a h = QZAlbumActivity.this.h();
                if (h == null) {
                    kotlin.jvm.internal.g.a();
                }
                h.a(QZAlbumActivity.this.o());
                QZAlbumActivity.this.H();
            }
        }

        g() {
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void a() {
            super.a();
            QZAlbumActivity.this.q().sendEmptyMessage(QZAlbumActivity.this.B);
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void a(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.a(rPFile);
            QZAlbumActivity.this.I();
            CameraApplication.f1401a.a((Boolean) true);
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void a(boolean z) {
            super.a(z);
            if (QZAlbumActivity.this.y || QZAlbumActivity.this.x || QZAlbumActivity.this.l()) {
                return;
            }
            QZAlbumActivity.this.q().sendEmptyMessage(QZAlbumActivity.this.A);
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void b(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.b(rPFile);
            QZAlbumActivity qZAlbumActivity = QZAlbumActivity.this;
            qZAlbumActivity.d(qZAlbumActivity.m() + 1);
            QZAlbumActivity.this.q().post(new b(rPFile));
        }

        @Override // com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a
        public void c(RPFile rPFile) {
            kotlin.jvm.internal.g.b(rPFile, "item");
            super.c(rPFile);
            QZAlbumActivity qZAlbumActivity = QZAlbumActivity.this;
            qZAlbumActivity.e(qZAlbumActivity.n() + 1);
            QZAlbumActivity.this.q().post(new a());
        }
    }

    @kotlin.g(a = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$mHandler$1", "Landroid/os/Handler;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "handleMessage", "", "msg", "Landroid/os/Message;", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class h extends Handler {
        h() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                kotlin.jvm.internal.g.a();
            }
            int i = message.what;
            if (i != QZAlbumActivity.this.A) {
                if (i == QZAlbumActivity.this.B) {
                    QZAlbumActivity.this.a(R.string.system_busy);
                    QZAlbumActivity.this.a(false, 0);
                    QZAlbumActivity.this.u();
                    return;
                }
                return;
            }
            QZAlbumActivity qZAlbumActivity = QZAlbumActivity.this;
            qZAlbumActivity.c(qZAlbumActivity.k() + 1);
            QZAlbumActivity.this.c(false);
            QZAlbumActivity.this.a(false, 0);
            QZAlbumActivity.this.u();
            QZAlbumActivity.this.E();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.xiaomi.xy.sportscamera.camera.album.a h = QZAlbumActivity.this.h();
            if (h != null) {
                h.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    public static final class j implements Runnable {
        j() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (QZAlbumActivity.this.i() != null) {
                AlbumDownloadMenu i = QZAlbumActivity.this.i();
                if (i == null) {
                    kotlin.jvm.internal.g.a();
                }
                i.setVisibility(0);
                AlbumDownloadMenu i2 = QZAlbumActivity.this.i();
                if (i2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                i2.requestLayout();
            }
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity$showDeleteDialog$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/album/QZAlbumActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class k implements DimPanelFragment.c {
        k() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            DoubleButtonMenu g = QZAlbumActivity.this.g();
            if (g == null) {
                kotlin.jvm.internal.g.a();
            }
            g.setVisibility(8);
            QZAlbumActivity.this.d(0);
            QZAlbumActivity.this.e(0);
            QZAlbumActivity.this.x();
            de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
            Iterator<T> it2 = QZAlbumActivity.this.p().iterator();
            while (it2.hasNext()) {
                com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.b((RPFile) it2.next());
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
    public static final class l implements XYProgressDialogFragment.a {
        l() {
        }

        @Override // com.ants360.z13.fragment.XYProgressDialogFragment.a
        public final void a(XYProgressDialogFragment xYProgressDialogFragment) {
            xYProgressDialogFragment.dismissAllowingStateLoss();
            de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
            com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.e();
            com.xiaomi.xy.sportscamera.camera.album.a h = QZAlbumActivity.this.h();
            if (h == null) {
                kotlin.jvm.internal.g.a();
            }
            h.a(QZAlbumActivity.this.o());
            QZAlbumActivity.this.t();
        }
    }

    private final void B() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_photo_capture"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("photo_taken"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("self_capture_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("burst_complete"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_video_record"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("video_record_complete"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("start_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("STORAGE_RUNOUT"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("precise_cont_complete"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_sdformat"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_sdformat"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_mvrecover"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_mvrecover"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_format_done"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_live"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_live"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
        registerReceiver(this.D, intentFilter);
    }

    private final void C() {
        View findViewById = findViewById(R.id.titleBar);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.CustomTitleBarForAlbum");
        }
        this.d = (CustomTitleBarForAlbum) findViewById;
        CustomTitleBarForAlbum customTitleBarForAlbum = this.d;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setRightBrowserVisible(false);
        CustomTitleBarForAlbum customTitleBarForAlbum2 = this.d;
        if (customTitleBarForAlbum2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum2.setTitleClickListener(new c());
        View findViewById2 = findViewById(R.id.gridView);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.sticky.gridview.StickyGridHeadersGridView");
        }
        this.e = (StickyGridHeadersGridView) findViewById2;
        StickyGridHeadersGridView stickyGridHeadersGridView = this.e;
        if (stickyGridHeadersGridView == null) {
            kotlin.jvm.internal.g.a();
        }
        stickyGridHeadersGridView.setAreHeadersSticky(true);
        StickyGridHeadersGridView stickyGridHeadersGridView2 = this.e;
        if (stickyGridHeadersGridView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        stickyGridHeadersGridView2.setOnItemClickListener(this);
        StickyGridHeadersGridView stickyGridHeadersGridView3 = this.e;
        if (stickyGridHeadersGridView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        stickyGridHeadersGridView3.setOnItemLongClickListener(this);
        this.h = new com.xiaomi.xy.sportscamera.camera.album.a(this);
        StickyGridHeadersGridView stickyGridHeadersGridView4 = this.e;
        if (stickyGridHeadersGridView4 == null) {
            kotlin.jvm.internal.g.a();
        }
        stickyGridHeadersGridView4.setAdapter((ListAdapter) this.h);
        View findViewById3 = findViewById(R.id.tvNoFile);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.f = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.bottomMenu);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.DoubleButtonMenu");
        }
        this.g = (DoubleButtonMenu) findViewById4;
        DoubleButtonMenu doubleButtonMenu = this.g;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setClickListener(new d());
        View findViewById5 = findViewById(R.id.downloadMenu);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.AlbumDownloadMenu");
        }
        this.i = (AlbumDownloadMenu) findViewById5;
        AlbumDownloadMenu albumDownloadMenu = this.i;
        if (albumDownloadMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        albumDownloadMenu.setMenuClickListener(new e());
        View findViewById6 = findViewById(R.id.rlBlock);
        if (findViewById6 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.j = (RelativeLayout) findViewById6;
        RelativeLayout relativeLayout = this.j;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setOnClickListener(f.f4500a);
        View findViewById7 = findViewById(R.id.pbCameraAlbumLoading);
        if (findViewById7 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
        }
        this.l = (ProgressBar) findViewById7;
        View findViewById8 = findViewById(R.id.tvInfoCameraAlbum);
        if (findViewById8 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.k = (TextView) findViewById8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void D() {
        if (kotlin.jvm.internal.g.a((Object) com.xiaoyi.camera.g.a().a("sd_card_status"), (Object) ProductAction.ACTION_REMOVE)) {
            a(true, R.string.sd_card_remove);
            return;
        }
        this.p = false;
        this.q = false;
        t();
        F();
        TextView textView = this.f;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setVisibility(8);
        a(true, this.r > 0 ? R.string.pull_refreshing : R.string.initialize_filelist);
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.b();
        E();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void E() {
        if (this.q || L()) {
            return;
        }
        this.q = true;
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void F() {
        A();
        if (this.p) {
            return;
        }
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.c();
        com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
        if (aVar != null) {
            aVar.a((List<? extends RPFile>) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G() {
        F();
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.d();
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void H() {
        if (this.n != null) {
            XYProgressDialogFragment xYProgressDialogFragment = this.n;
            if (xYProgressDialogFragment == null) {
                kotlin.jvm.internal.g.a();
            }
            this.o++;
            xYProgressDialogFragment.a(this.o);
            XYProgressDialogFragment xYProgressDialogFragment2 = this.n;
            if (xYProgressDialogFragment2 == null) {
                kotlin.jvm.internal.g.a();
            }
            int f2 = xYProgressDialogFragment2.f();
            XYProgressDialogFragment xYProgressDialogFragment3 = this.n;
            if (xYProgressDialogFragment3 == null) {
                kotlin.jvm.internal.g.a();
            }
            if (f2 == xYProgressDialogFragment3.g()) {
                XYProgressDialogFragment xYProgressDialogFragment4 = this.n;
                if (xYProgressDialogFragment4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                xYProgressDialogFragment4.dismissAllowingStateLoss();
                de.greenrobot.event.c.a().c(new com.ants360.z13.c.d());
                t();
                if (this.v != null) {
                    List<RPFile> list = this.v;
                    if (list == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (!list.isEmpty()) {
                        TextView textView = this.f;
                        if (textView == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        textView.setVisibility(8);
                        StickyGridHeadersGridView stickyGridHeadersGridView = this.e;
                        if (stickyGridHeadersGridView == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        stickyGridHeadersGridView.setVisibility(0);
                        a(getString(R.string.camera_album_file_delete, new Object[]{Integer.valueOf(this.t), Integer.valueOf(this.u)}));
                    }
                }
                TextView textView2 = this.f;
                if (textView2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView2.setVisibility(0);
                StickyGridHeadersGridView stickyGridHeadersGridView2 = this.e;
                if (stickyGridHeadersGridView2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                stickyGridHeadersGridView2.setVisibility(8);
                a(getString(R.string.camera_album_file_delete, new Object[]{Integer.valueOf(this.t), Integer.valueOf(this.u)}));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void I() {
        RPFile rPFile;
        com.yiaction.common.util.g.a("Call refreshDownloadMenu", new Object[0]);
        AlbumDownloadMenu albumDownloadMenu = this.i;
        if (albumDownloadMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        if (albumDownloadMenu.getVisibility() == 0) {
            if (!com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b().isEmpty()) {
                AlbumDownloadMenu albumDownloadMenu2 = this.i;
                if (albumDownloadMenu2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                List<RPFile> d2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.d();
                if (d2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (!d2.isEmpty()) {
                    List<RPFile> d3 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.d();
                    if (d3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    rPFile = d3.get(0);
                } else {
                    List<RPFile> b2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b();
                    if (b2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b() == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    rPFile = b2.get(r1.size() - 1);
                }
                albumDownloadMenu2.setDownloadImage(rPFile);
                AlbumDownloadMenu albumDownloadMenu3 = this.i;
                if (albumDownloadMenu3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                List<RPFile> c2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.c();
                if (c2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                int size = c2.size();
                List<RPFile> b3 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b();
                if (b3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                albumDownloadMenu3.a(size, b3.size());
                J();
            }
        }
    }

    private final void J() {
        AlbumDownloadMenu albumDownloadMenu = this.i;
        if (albumDownloadMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        albumDownloadMenu.post(new j());
    }

    private final void K() {
        a(true, R.string.initialize_filelist);
        com.xiaoyi.camera.g.a().d("app_status", new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean L() {
        if (this.j == null) {
            return true;
        }
        int b2 = com.yiaction.common.util.i.a().b("current_operation_model", -1);
        switch (b2) {
            case 0:
                a(true, R.string.in_recording);
                break;
            case 1:
                a(true, R.string.in_normal_capturing);
                break;
            case 2:
                a(true, R.string.in_burst);
                break;
            case 3:
                a(true, R.string.in_timeslape);
                break;
            case 4:
                a(true, R.string.in_timer);
                break;
            case 5:
                a(true, R.string.in_quick_recording);
                break;
        }
        return b2 >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(boolean z, int i2) {
        if (z) {
            RelativeLayout relativeLayout = this.j;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(0);
            }
            TextView textView = this.k;
            if (textView != null) {
                textView.setText(i2);
                return;
            }
            return;
        }
        RelativeLayout relativeLayout2 = this.j;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(8);
        }
        TextView textView2 = this.k;
        if (textView2 != null) {
            textView2.setText("");
        }
    }

    private final void e(boolean z) {
        AlbumDownloadMenu albumDownloadMenu = this.i;
        if (albumDownloadMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        albumDownloadMenu.setVisibility(z ? 0 : 8);
        if (z) {
            I();
        }
    }

    public final void A() {
        if (this.w != null) {
            Iterator<RPFile> it2 = this.w.iterator();
            while (it2.hasNext()) {
                it2.next().setSelect(false);
            }
            this.w.clear();
        }
    }

    public final void a(RPFile rPFile) {
        kotlin.jvm.internal.g.b(rPFile, "file");
        if (this.w == null) {
            this.w = new ArrayList();
        }
        if (rPFile.isSelect()) {
            rPFile.setSelect(false);
            if (rPFile.getType() != 1 || !rPFile.isMultiPhoto()) {
                this.w.remove(rPFile);
                return;
            }
            List<RPFile> list = this.w;
            com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b bVar = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a;
            String nameType = rPFile.getNameType();
            kotlin.jvm.internal.g.a((Object) nameType, "file.nameType");
            list.removeAll(bVar.c(nameType));
            return;
        }
        rPFile.setSelect(true);
        if (rPFile.getType() != 1 || !rPFile.isMultiPhoto()) {
            this.w.add(rPFile);
            return;
        }
        List<RPFile> list2 = this.w;
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b bVar2 = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a;
        String nameType2 = rPFile.getNameType();
        kotlin.jvm.internal.g.a((Object) nameType2, "file.nameType");
        list2.addAll(bVar2.c(nameType2));
    }

    public final void b(boolean z) {
        this.p = z;
    }

    public final void c(int i2) {
        this.r = i2;
    }

    public final void c(boolean z) {
        this.q = z;
    }

    public final void d(int i2) {
        this.t = i2;
    }

    public final void d(boolean z) {
        this.s = z;
    }

    public final void e(int i2) {
        this.u = i2;
    }

    public final CustomTitleBarForAlbum f() {
        return this.d;
    }

    public final DoubleButtonMenu g() {
        return this.g;
    }

    public final com.xiaomi.xy.sportscamera.camera.album.a h() {
        return this.h;
    }

    public final AlbumDownloadMenu i() {
        return this.i;
    }

    public final boolean j() {
        return this.p;
    }

    public final int k() {
        return this.r;
    }

    public final boolean l() {
        return this.s;
    }

    public final int m() {
        return this.t;
    }

    public final int n() {
        return this.u;
    }

    public final List<RPFile> o() {
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "----------- requestCode = " + i2, new Object[0]);
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "----------- resultCode = " + i3, new Object[0]);
        if (i2 == this.c && i3 == -1) {
            D();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        if (aVar.b()) {
            t();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_qz_album);
        com.a.a.b.a.f553a.a(this);
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.a(this.E);
        de.greenrobot.event.c.a().a(this);
        C();
        B();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.b(this.E);
        de.greenrobot.event.c.a().b(this);
        unregisterReceiver(this.D);
    }

    public final void onEvent(com.a.a.a.a aVar) {
        kotlin.jvm.internal.g.b(aVar, "event");
        e(true);
    }

    public final void onEvent(com.a.a.a.b bVar) {
        kotlin.jvm.internal.g.b(bVar, "event");
        runOnUiThread(new i());
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        if (aVar.b()) {
            List<RPFile> list = this.v;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            a(list.get(i2));
            com.xiaomi.xy.sportscamera.camera.album.a aVar2 = this.h;
            if (aVar2 == null) {
                kotlin.jvm.internal.g.a();
            }
            aVar2.notifyDataSetChanged();
            r();
            return;
        }
        List<RPFile> list2 = this.v;
        if (list2 == null) {
            kotlin.jvm.internal.g.a();
        }
        RPFile rPFile = list2.get(i2);
        if (rPFile.getType() != 1 || !rPFile.isMultiPhoto()) {
            startActivityForResult(new Intent(this, (Class<?>) QZCameraMediaShowActivity.class).putExtra("MEDIA_POSIOTION", i2), this.c);
            return;
        }
        Intent intent = new Intent(this, (Class<?>) QZDetailGridViewActivity.class);
        intent.putExtra("type", rPFile.getNameType());
        startActivity(intent);
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar.a(true);
        List<RPFile> list = this.v;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        a(list.get(i2));
        com.xiaomi.xy.sportscamera.camera.album.a aVar2 = this.h;
        if (aVar2 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar2.notifyDataSetChanged();
        r();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.p) {
            u();
        } else {
            K();
        }
        e(!com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.b().isEmpty());
    }

    public final List<RPFile> p() {
        return this.w;
    }

    public final Handler q() {
        return this.C;
    }

    public final void r() {
        com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar.a(true);
        CustomTitleBarForAlbum customTitleBarForAlbum = this.d;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setLeftTitle(R.string.cancel);
        CustomTitleBarForAlbum customTitleBarForAlbum2 = this.d;
        if (customTitleBarForAlbum2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum2.setRightTitle(R.string.select_all);
        int size = this.w.size();
        CustomTitleBarForAlbum customTitleBarForAlbum3 = this.d;
        if (customTitleBarForAlbum3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum3.setMiddleTitle(com.yiaction.common.util.k.a(getString(R.string.album_choose_num, new Object[]{Integer.valueOf(size)}), "item", size));
        DoubleButtonMenu doubleButtonMenu = this.g;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setVisibility(0);
        DoubleButtonMenu doubleButtonMenu2 = this.g;
        if (doubleButtonMenu2 == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu2.setButtonEnable(Boolean.valueOf(size > 0));
    }

    public final void s() {
        com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar.a(true);
        z();
        com.xiaomi.xy.sportscamera.camera.album.a aVar2 = this.h;
        if (aVar2 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar2.notifyDataSetChanged();
        int size = this.w.size();
        CustomTitleBarForAlbum customTitleBarForAlbum = this.d;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setMiddleTitle(com.yiaction.common.util.k.a(getString(R.string.album_choose_num, new Object[]{Integer.valueOf(size)}), "item", size));
        DoubleButtonMenu doubleButtonMenu = this.g;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setVisibility(0);
        DoubleButtonMenu doubleButtonMenu2 = this.g;
        if (doubleButtonMenu2 == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu2.setButtonEnable(Boolean.valueOf(size > 0));
    }

    public final void t() {
        com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar.a(false);
        A();
        com.xiaomi.xy.sportscamera.camera.album.a aVar2 = this.h;
        if (aVar2 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar2.notifyDataSetChanged();
        CustomTitleBarForAlbum customTitleBarForAlbum = this.d;
        if (customTitleBarForAlbum == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum.setLeftBackground(R.drawable.btn_back);
        CustomTitleBarForAlbum customTitleBarForAlbum2 = this.d;
        if (customTitleBarForAlbum2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum2.setRightTitle(R.string.album_choose);
        CustomTitleBarForAlbum customTitleBarForAlbum3 = this.d;
        if (customTitleBarForAlbum3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customTitleBarForAlbum3.setMiddleTitle(getString(R.string.title_camera_album));
        DoubleButtonMenu doubleButtonMenu = this.g;
        if (doubleButtonMenu == null) {
            kotlin.jvm.internal.g.a();
        }
        doubleButtonMenu.setVisibility(8);
    }

    public final void u() {
        com.rp.rptool.util.c.b(0, this.b, "refreshFileListDrapDown()");
        this.v = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a(0);
        if (this.v != null) {
            List<RPFile> list = this.v;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!list.isEmpty()) {
                TextView textView = this.f;
                if (textView == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView.setVisibility(8);
                StickyGridHeadersGridView stickyGridHeadersGridView = this.e;
                if (stickyGridHeadersGridView == null) {
                    kotlin.jvm.internal.g.a();
                }
                stickyGridHeadersGridView.setVisibility(0);
                com.xiaomi.xy.sportscamera.camera.album.a aVar = this.h;
                if (aVar == null) {
                    kotlin.jvm.internal.g.a();
                }
                aVar.a(this.v);
                this.p = true;
                return;
            }
        }
        TextView textView2 = this.f;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setVisibility(0);
        StickyGridHeadersGridView stickyGridHeadersGridView2 = this.e;
        if (stickyGridHeadersGridView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        stickyGridHeadersGridView2.setVisibility(8);
    }

    public final void v() {
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.a(this.w);
        t();
        e(true);
    }

    public final void w() {
        if (this.w == null || this.w.isEmpty()) {
            a("请先选择文件");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.title_delete));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.sure_to_delete));
        this.m = new CustomBottomDialogFragment();
        CustomBottomDialogFragment customBottomDialogFragment = this.m;
        if (customBottomDialogFragment == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment.setArguments(bundle);
        CustomBottomDialogFragment customBottomDialogFragment2 = this.m;
        if (customBottomDialogFragment2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment2.a(new k());
        CustomBottomDialogFragment customBottomDialogFragment3 = this.m;
        if (customBottomDialogFragment3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment3.a(this);
    }

    public final void x() {
        this.o = 0;
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.title_delete));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.process_delete));
        bundle.putString("middle_button", getString(R.string.cancel));
        bundle.putInt("max", this.w.size());
        this.n = new XYProgressDialogFragment();
        XYProgressDialogFragment xYProgressDialogFragment = this.n;
        if (xYProgressDialogFragment == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment.setArguments(bundle);
        XYProgressDialogFragment xYProgressDialogFragment2 = this.n;
        if (xYProgressDialogFragment2 == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment2.a(new l());
        XYProgressDialogFragment xYProgressDialogFragment3 = this.n;
        if (xYProgressDialogFragment3 == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment3.setCancelable(false);
        XYProgressDialogFragment xYProgressDialogFragment4 = this.n;
        if (xYProgressDialogFragment4 == null) {
            kotlin.jvm.internal.g.a();
        }
        xYProgressDialogFragment4.a(this);
    }

    public final void y() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.enter_live_and_disconnect));
        bundle.putString("left_button", getString(R.string.button_back_to_home));
        bundle.putString("right_button", getString(R.string.button_reconnect));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new a());
        customBottomDialogFragment.a(this);
    }

    public final void z() {
        if (this.w == null) {
            this.w = new ArrayList();
        } else {
            this.w.clear();
        }
        if (this.w != null) {
            List<RPFile> list = this.v;
            if (list == null) {
                kotlin.jvm.internal.g.a();
            }
            for (RPFile rPFile : list) {
                rPFile.setSelect(true);
                if (rPFile.getType() == 1 && rPFile.isMultiPhoto()) {
                    List<RPFile> list2 = this.w;
                    com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b bVar = com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a;
                    String nameType = rPFile.getNameType();
                    kotlin.jvm.internal.g.a((Object) nameType, "file.nameType");
                    list2.addAll(bVar.c(nameType));
                } else {
                    this.w.add(rPFile);
                }
            }
        }
    }
}
