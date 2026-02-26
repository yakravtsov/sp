package com.xiaomi.xy.sportscamera.camera.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ants360.z13.activity.BaseConfigActivity;
import com.ants360.z13.activity.CameraApplication;
import com.ants360.z13.activity.CameraConnectionFailedActivity;
import com.ants360.z13.activity.MainActivity;
import com.ants360.z13.b.a;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.fragment.SDCardRemoveDialogFragment;
import com.ants360.z13.util.StatisticHelper;
import com.ants360.z13.widget.CustomBatteryLoading;
import com.ants360.z13.widget.SudokuView;
import com.ants360.z13.widget.TimelapsSeekBar;
import com.google.android.exoplayer.text.ttml.TtmlNode;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.rp.rptool.util.RPVideoViewHelper;
import com.rp.rptool.util.b;
import com.rp.rptool.util.d;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.album.QZAlbumActivity;
import com.xiaomi.xy.sportscamera.camera.set.CameraSDCardActivity;
import com.xiaomi.xy.sportscamera.camera.set.VideoPhotoSettingActivity;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraDeviceUpgradeActivity;
import com.xiaomi.xy.sportscamera.camera.upgrade.CameraUpgradeActivity;
import com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker;
import com.xiaomi.xy.sportscamera.camera.widget.SettingItemViewPager;
import com.xiaomi.xy.sportscamera.camera.widget.a;
import com.xiaomi.xy.sportscamera.camera.widget.b;
import com.xiaoyi.camera.b.b;
import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.module.FileItem;
import com.xiaoyi.camera.module.PhotoFileItem;
import com.xiaoyi.camera.module.VideoFileItem;
import com.xiaoyi.player.NetworkUtil;
import com.yiaction.common.stats.UploadStatsManager;
import com.yiaction.common.util.Constant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.TypeCastException;
import kotlin.jvm.internal.g;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import wseemann.media.BuildConfig;
import wseemann.media.FFmpegMediaMetadataRetriever;

@kotlin.g(a = {"\u0000Ù\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b$*\u0001x\b\u0007\u0018\u0000 Ï\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\bÏ\u0002Ð\u0002Ñ\u0002Ò\u0002B\u0005¢\u0006\u0002\u0010\u0007J@\u0010\u009e\u0001\u001a\u00030\u009f\u00012\b\u0010 \u0001\u001a\u00030¡\u00012\n\u0010¢\u0001\u001a\u0005\u0018\u00010£\u00012\n\u0010¤\u0001\u001a\u0005\u0018\u00010¥\u00012\t\u0010¦\u0001\u001a\u0004\u0018\u00010\u00132\u0007\u0010§\u0001\u001a\u00020\tH\u0016J\n\u0010¨\u0001\u001a\u00030\u009f\u0001H\u0002J\b\u0010©\u0001\u001a\u00030\u009f\u0001J\u0007\u0010/\u001a\u00030\u009f\u0001J\n\u0010ª\u0001\u001a\u00030\u009f\u0001H\u0002J\n\u0010«\u0001\u001a\u00030\u009f\u0001H\u0002J\n\u0010¬\u0001\u001a\u00030\u009f\u0001H\u0002J\b\u0010\u00ad\u0001\u001a\u00030\u009f\u0001J\u0014\u0010®\u0001\u001a\u0004\u0018\u00010\u00132\u0007\u0010¯\u0001\u001a\u00020\tH\u0002J\n\u0010°\u0001\u001a\u00030\u009f\u0001H\u0002J\n\u0010±\u0001\u001a\u00030\u009f\u0001H\u0002J\n\u0010²\u0001\u001a\u00030\u009f\u0001H\u0002J\b\u0010<\u001a\u000203H\u0002J\n\u0010³\u0001\u001a\u00030\u009f\u0001H\u0002J\n\u0010´\u0001\u001a\u00030\u009f\u0001H\u0002J\n\u0010µ\u0001\u001a\u00030\u009f\u0001H\u0002J&\u0010¶\u0001\u001a\u00030\u009f\u00012\u0007\u0010·\u0001\u001a\u00020\t2\u0007\u0010¸\u0001\u001a\u00020\t2\b\u0010¹\u0001\u001a\u00030º\u0001H\u0014J\n\u0010»\u0001\u001a\u00030\u009f\u0001H\u0016J\n\u0010¼\u0001\u001a\u00030\u009f\u0001H\u0016J\u0014\u0010½\u0001\u001a\u00030\u009f\u00012\n\u0010 \u0001\u001a\u0005\u0018\u00010¥\u0001J\b\u0010¾\u0001\u001a\u00030\u009f\u0001J\u0013\u0010¾\u0001\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u000203H\u0016J\u0013\u0010À\u0001\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u000203H\u0016J\u0013\u0010Á\u0001\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u000203H\u0016J\b\u0010Â\u0001\u001a\u00030\u009f\u0001J\u0013\u0010Ã\u0001\u001a\u00030\u009f\u00012\u0007\u0010Ä\u0001\u001a\u00020WH\u0016J\u0016\u0010Å\u0001\u001a\u00030\u009f\u00012\n\u0010Æ\u0001\u001a\u0005\u0018\u00010Ç\u0001H\u0014J\n\u0010È\u0001\u001a\u00030\u009f\u0001H\u0002J\n\u0010É\u0001\u001a\u00030\u009f\u0001H\u0016J\u0012\u0010Ê\u0001\u001a\u00030\u009f\u00012\b\u0010Ë\u0001\u001a\u00030Ì\u0001J\u0012\u0010Ê\u0001\u001a\u00030\u009f\u00012\b\u0010Ë\u0001\u001a\u00030Í\u0001J\u0012\u0010Ê\u0001\u001a\u00030\u009f\u00012\b\u0010Ë\u0001\u001a\u00030Î\u0001J\u0012\u0010Ê\u0001\u001a\u00030\u009f\u00012\b\u0010Ë\u0001\u001a\u00030Ï\u0001J$\u0010Ð\u0001\u001a\u00030\u009f\u00012\b\u0010Ñ\u0001\u001a\u00030Ò\u00012\u0007\u0010Ó\u0001\u001a\u00020\t2\u0007\u0010Ô\u0001\u001a\u00020\tJ$\u0010Õ\u0001\u001a\u00030\u009f\u00012\b\u0010Ñ\u0001\u001a\u00030Ò\u00012\u0007\u0010Ó\u0001\u001a\u00020\t2\u0007\u0010Ô\u0001\u001a\u00020\tJ%\u0010Ö\u0001\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010×\u0001\u001a\u0002032\u0007\u0010Ø\u0001\u001a\u00020\u0013H\u0016J'\u0010Ù\u0001\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\t\u0010Ú\u0001\u001a\u0004\u0018\u00010\u00132\u0007\u0010\u0093\u0001\u001a\u00020\tH\u0016J\u0013\u0010Û\u0001\u001a\u00030\u009f\u00012\u0007\u0010Ü\u0001\u001a\u000203H\u0016J%\u0010Ý\u0001\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010Þ\u0001\u001a\u00020\t2\u0007\u0010Ú\u0001\u001a\u00020\u0013H\u0016J%\u0010ß\u0001\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010Þ\u0001\u001a\u00020\t2\u0007\u0010Ú\u0001\u001a\u00020\u0013H\u0016J\u0013\u0010à\u0001\u001a\u00030\u009f\u00012\u0007\u0010á\u0001\u001a\u00020\tH\u0016J\n\u0010â\u0001\u001a\u00030\u009f\u0001H\u0016J\n\u0010ã\u0001\u001a\u00030\u009f\u0001H\u0016J\u001c\u0010ä\u0001\u001a\u00030\u009f\u00012\u0007\u0010å\u0001\u001a\u00020`2\u0007\u0010á\u0001\u001a\u00020\tH\u0016J\u001c\u0010æ\u0001\u001a\u0002032\u0007\u0010ç\u0001\u001a\u00020\t2\b\u0010Ë\u0001\u001a\u00030è\u0001H\u0016J\u0013\u0010é\u0001\u001a\u00030\u009f\u00012\u0007\u0010ê\u0001\u001a\u00020\tH\u0016J\u0015\u0010é\u0001\u001a\u00030\u009f\u00012\t\u0010ë\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J\u0013\u0010ì\u0001\u001a\u00030\u009f\u00012\u0007\u0010í\u0001\u001a\u00020\u0013H\u0016J\u0013\u0010î\u0001\u001a\u0002032\b\u0010ï\u0001\u001a\u00030ð\u0001H\u0016J\n\u0010ñ\u0001\u001a\u00030\u009f\u0001H\u0016J\n\u0010ò\u0001\u001a\u00030\u009f\u0001H\u0016J\u0013\u0010ó\u0001\u001a\u00030\u009f\u00012\u0007\u0010ô\u0001\u001a\u000203H\u0016J\u0013\u0010õ\u0001\u001a\u0002032\b\u0010ö\u0001\u001a\u00030÷\u0001H\u0016J\u0013\u0010ø\u0001\u001a\u00030\u009f\u00012\u0007\u0010ù\u0001\u001a\u000203H\u0016J\u0013\u0010ú\u0001\u001a\u00030\u009f\u00012\u0007\u0010û\u0001\u001a\u000203H\u0016J\u0012\u0010ü\u0001\u001a\u00030\u009f\u00012\b\u0010 \u0001\u001a\u00030£\u0001J\n\u0010ý\u0001\u001a\u00030\u009f\u0001H\u0016J\u0014\u0010þ\u0001\u001a\u00030\u009f\u00012\b\u0010ÿ\u0001\u001a\u00030Ç\u0001H\u0016J\u001c\u0010\u0080\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u000203H\u0016J.\u0010\u0082\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010\u0084\u0002\u001a\u0002032\u0007\u0010ê\u0001\u001a\u00020\tH\u0016J%\u0010\u0085\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u0002032\u0007\u0010\u0086\u0002\u001a\u000203H\u0016J7\u0010\u0087\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010\u0084\u0002\u001a\u0002032\u0007\u0010\u0088\u0002\u001a\u0002032\u0007\u0010ê\u0001\u001a\u00020\tH\u0016J.\u0010\u0089\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010\u0084\u0002\u001a\u0002032\u0007\u0010ê\u0001\u001a\u00020\tH\u0016J\u0013\u0010\u008a\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u000203H\u0016J.\u0010\u008b\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010\u0084\u0002\u001a\u0002032\u0007\u0010ê\u0001\u001a\u00020\tH\u0016J\u001c\u0010\u008c\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u000203H\u0016J@\u0010\u008d\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010\u008e\u0002\u001a\u0002032\u0007\u0010\u008f\u0002\u001a\u0002032\u0007\u0010\u0090\u0002\u001a\u00020\t2\u0007\u0010ê\u0001\u001a\u00020\tH\u0016J%\u0010\u0091\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u0002032\u0007\u0010\u0086\u0002\u001a\u000203H\u0016J'\u0010\u0092\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u0002032\t\u0010í\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J%\u0010\u0093\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010í\u0001\u001a\u00020\u0013H\u0016J'\u0010\u0094\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u0002032\t\u0010í\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J%\u0010\u0095\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010í\u0001\u001a\u00020\u0013H\u0016J%\u0010\u0096\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010í\u0001\u001a\u00020\u0013H\u0016J\u001c\u0010\u0097\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0098\u0002\u001a\u000203H\u0016J%\u0010\u0099\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010í\u0001\u001a\u00020\u0013H\u0016J.\u0010\u009a\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0083\u0002\u001a\u0002032\u0007\u0010\u009b\u0002\u001a\u0002032\u0007\u0010í\u0001\u001a\u00020\u0013H\u0016J'\u0010\u009c\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u0002032\t\u0010í\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J'\u0010\u009d\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010\u0081\u0002\u001a\u0002032\t\u0010í\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J*\u0010\u009e\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\n\u0010ï\u0001\u001a\u0005\u0018\u00010\u009f\u00022\t\u0010 \u0002\u001a\u0004\u0018\u00010*H\u0016J\u0013\u0010¡\u0002\u001a\u00030\u009f\u00012\u0007\u0010¢\u0002\u001a\u00020\tH\u0016J\u0013\u0010£\u0002\u001a\u00030\u009f\u00012\u0007\u0010¢\u0002\u001a\u00020\tH\u0016J%\u0010¤\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010¥\u0002\u001a\u0002032\u0007\u0010¦\u0002\u001a\u000203H\u0016J\n\u0010§\u0002\u001a\u00030\u009f\u0001H\u0016J\u0013\u0010¨\u0002\u001a\u00030\u009f\u00012\u0007\u0010©\u0002\u001a\u000203H\u0016J\u0011\u0010ª\u0002\u001a\u00030\u009f\u00012\u0007\u0010û\u0001\u001a\u000203J\n\u0010«\u0002\u001a\u00030\u009f\u0001H\u0002J\u0012\u0010¬\u0002\u001a\u00030\u009f\u00012\b\u0010ö\u0001\u001a\u00030÷\u0001J\u0016\u0010\u00ad\u0002\u001a\u00030\u009f\u00012\n\u0010®\u0002\u001a\u0005\u0018\u00010¯\u0002H\u0002J\u0014\u0010°\u0002\u001a\u00030\u009f\u00012\b\u0010®\u0002\u001a\u00030¯\u0002H\u0002J\u0014\u0010±\u0002\u001a\u00030\u009f\u00012\b\u0010®\u0002\u001a\u00030¯\u0002H\u0002J\u001c\u0010²\u0002\u001a\u00030\u009f\u00012\u0007\u0010³\u0002\u001a\u00020\u00132\u0007\u0010´\u0002\u001a\u00020\u0013H\u0002J%\u0010µ\u0002\u001a\u00030\u009f\u00012\u0007\u0010¶\u0002\u001a\u0002032\u0007\u0010·\u0002\u001a\u0002032\u0007\u0010¸\u0002\u001a\u000203H\u0002J\n\u0010¹\u0002\u001a\u00030\u009f\u0001H\u0002J\n\u0010º\u0002\u001a\u00030\u009f\u0001H\u0002J\n\u0010»\u0002\u001a\u00030\u009f\u0001H\u0002J\b\u0010¼\u0002\u001a\u00030\u009f\u0001J\u001e\u0010½\u0002\u001a\u00030\u009f\u00012\u0007\u0010ô\u0001\u001a\u0002032\t\u0010¾\u0002\u001a\u0004\u0018\u00010\u0013H\u0002J\u0015\u0010¿\u0002\u001a\u00030\u009f\u00012\t\u0010À\u0002\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010Á\u0002\u001a\u00030\u009f\u0001J.\u0010Â\u0002\u001a\u00030\u009f\u00012\u0007\u0010¿\u0001\u001a\u0002032\u0007\u0010Þ\u0001\u001a\u00020\t2\u0007\u0010Ã\u0002\u001a\u0002032\u0007\u0010\u0090\u0002\u001a\u00020\tH\u0016J\n\u0010Ä\u0002\u001a\u00030\u009f\u0001H\u0002J.\u0010Å\u0002\u001a\u00030\u009f\u00012\u0007\u0010Æ\u0002\u001a\u0002032\u0007\u0010Ç\u0002\u001a\u0002032\u0007\u0010È\u0002\u001a\u0002032\u0007\u0010É\u0002\u001a\u000203H\u0002J\u0013\u0010Ê\u0002\u001a\u00030\u009f\u00012\u0007\u0010í\u0001\u001a\u00020\u0013H\u0002J\n\u0010Ë\u0002\u001a\u00030\u009f\u0001H\u0002J\n\u0010Ì\u0002\u001a\u00030\u009f\u0001H\u0002J\n\u0010Í\u0002\u001a\u00030\u009f\u0001H\u0002J\b\u0010Î\u0002\u001a\u00030\u009f\u0001R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u0016\u0010\u001b\u001a\n \u001c*\u0004\u0018\u00010\u00130\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R\"\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0015\"\u0004\b\"\u0010\u0017R\u000e\u0010#\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u000203X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010F\u001a\u00020G¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0010\u0010J\u001a\u0004\u0018\u00010KX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010P\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Q\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010S\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010T\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010U\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u0004\u0018\u00010WX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010X\u001a\u0004\u0018\u00010YX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Z\u001a\u0004\u0018\u00010[X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010]\u001a\u00060^R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u0004\u0018\u00010`X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010b\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010c\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010e\u001a\b\u0018\u00010fR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010g\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010k\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010l\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010m\u001a\u0004\u0018\u00010nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010o\u001a\u0004\u0018\u00010nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010p\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010r\u001a\u0004\u0018\u00010sX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010w\u001a\u00020xX\u0082\u0004¢\u0006\u0004\n\u0002\u0010yR\u0010\u0010z\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010{\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010|\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010}\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010~\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u007f\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0080\u0001\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0081\u0001\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0083\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u008a\u0001\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u008b\u0001\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u008f\u0001\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0093\u0001\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0094\u0001\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0095\u0001\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0096\u0001\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0097\u0001\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0098\u0001\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0099\u0001\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u009a\u0001\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u009b\u0001\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009d\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Ó\u0002"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;", "Lcom/ants360/z13/activity/BaseConfigActivity;", "Landroid/view/View$OnClickListener;", "Lcom/xiaoyi/camera/controller/CameraMainController$CameraMainListener;", "Lcom/xiaomi/xy/sportscamera/camera/widget/HorizontalPicker$OnItemSelectedListener;", "Lcom/xiaomi/xy/sportscamera/camera/widget/CameraSettingDialog2$OnSettingDialogDisListener;", "Lcom/xiaomi/xy/sportscamera/camera/widget/CameraSettingDialog3$OnSettingDialogDisListener;", "()V", "HANDLER_TIMERRECORD_COMPLETE", "", "HANDLER_TIMERRECORD_START", "HANDLER_TIMERRECORD_STOP", "MSG_START_VIDEO", "getMSG_START_VIDEO", "()I", "MSG_STOP_VIDEO", "getMSG_STOP_VIDEO", "PHOTO_MODEL", "", "", "getPHOTO_MODEL", "()Ljava/util/List;", "setPHOTO_MODEL", "(Ljava/util/List;)V", "PHOTO_MODEL_VALUE", "getPHOTO_MODEL_VALUE", "setPHOTO_MODEL_VALUE", "TAG", "kotlin.jvm.PlatformType", "VIDEO_MODEL", "getVIDEO_MODEL", "setVIDEO_MODEL", "VIDEO_MODEL_VALUE", "getVIDEO_MODEL_VALUE", "setVIDEO_MODEL_VALUE", "batteryLevel", "cameraSettingDialog", "Lcom/xiaomi/xy/sportscamera/camera/widget/CameraSettingDialog2;", "cameraSettingDialog3", "Lcom/xiaomi/xy/sportscamera/camera/widget/CameraSettingDialog3;", "cameraVersion", "cvPhotoView", "Landroid/widget/ImageView;", "cvSwitchView", "deinitToolRun", "Ljava/lang/Runnable;", "deinitToolRun_1", "disconnectDialog", "Lcom/ants360/z13/fragment/CustomBottomDialogFragment;", "finishRun", "firstShowThumb", "", "flPlayerFrame", "Landroid/widget/RelativeLayout;", "ibFileManage", "ibRecordCapture", "ibRecordCapture_2", "isAdapterBattery", "isAppClickSet", "isCameraSwitchFin", "isHasAvailMemory", "isLandAnimationFin", "isPortAnimationFin", "isShowBattery", "ivFullScreen", "ivGraph", "ivPhotoView", "ivSettingWarp", "ivShowModel", "ivSwitchView", "listener", "Lcom/rp/rptool/util/RPToolUtil$RPToolCallbackListener;", "getListener", "()Lcom/rp/rptool/util/RPToolUtil$RPToolCallbackListener;", "llBatteryLayout", "Landroid/widget/LinearLayout;", "llEnterAlbum", "llFileManage", "llFunciton", "llModelSwitch", "llNormalRecordTime", "llOperationLayout", "llRecordCapture", "llStartPiv", "llSwitchModel", "llrlControlLayout", "mBatteryView", "Landroid/view/View;", "mClCameraBattery", "Lcom/ants360/z13/widget/CustomBatteryLoading;", "mController", "Lcom/xiaoyi/camera/controller/CameraMainController;", "mCurrentPosition", "mHandler", "Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$MyHandler;", "mHorizontalPicker", "Lcom/xiaomi/xy/sportscamera/camera/widget/HorizontalPicker;", "mInitiativeExit", "mIvCameraBattery", "mModelSwitch", "mMoveHeight", "mMyGestureListener", "Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$MyGestureListener;", "mPhoneTime", "Landroid/widget/TextView;", "mScreenHeight", "mScreenWidth", "mSetting", "mSudoku", "mTextViewLightType", "Landroid/graphics/Typeface;", "mTextViewRegularType", "md5", "newProgress", "pbProcessing", "Landroid/widget/ProgressBar;", "port_height_16_9", "port_height_4_3", "previewParam", "receiver", "com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$receiver$1", "Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$receiver$1;", "rlBlockingCover", "rlControlPanel", "rlFileManage", "rlFunciton", "rlGraph", "rlRecordCapture", "rlShutterRemind", "rlTipsView", "sdRemoveDialog", "Lcom/ants360/z13/fragment/SDCardRemoveDialogFragment;", "seekBartimer", "Ljava/util/Timer;", "serialNumber", "serverVersion", "settingItemViewPager", "Lcom/xiaomi/xy/sportscamera/camera/widget/SettingItemViewPager;", "startVideoRun", "stopSessionRun", "svPreview", "svSudokuView", "Lcom/ants360/z13/widget/SudokuView;", "timelapsRecordTimeLayout", "timelapsSeekBar", "Lcom/ants360/z13/widget/TimelapsSeekBar;", "timerValue", "totalRecordTime", "tvInfoCameraAlbum", "tvNormalRecordTime", "tvResolution", "tvSelfCaptureTime", "tvShutterRemind", "tvTimelapesRecordCountTime", "tvTimelapesRecordTime", "upgradeDialog", "videoHelper", "Lcom/rp/rptool/util/RPVideoViewHelper;", "OnSettingDialogDis", "", "mode", "Lcom/xiaoyi/camera/controller/CameraMainController$CameraMode;", "rmode", "Lcom/yiaction/common/util/Constant$RecordMode;", "cmode", "Lcom/yiaction/common/util/Constant$CaptureMode;", FirebaseAnalytics.Param.VALUE, "type", "checkFirmwareUpgrade", "destroyAll", "dismissDisconnectDialog", "dismissSettingDialog", "dismissUpgradeDialog", "exit", "getErrorMessage", "errorcode", "initCameraMode", "initModelView", "initSettingItemView", "loadEndAnimation", "loadStartAnimation", "loadSwitchAnimation", "onActivityResult", "requestCode", "resultCode", ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA, "Landroid/content/Intent;", "onBackPressed", "onCameraReSetting", "onCaptureModeSelected", "onChangeCaptureMode", "result", "onChangeRecordMode", "onChangeRecordOption", "onChangeVideoMode", "onClick", "v", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDataReport", "onDestroy", "onEvent", "event", "Lcom/ants360/z13/event/CameraMediaDeleteEvent;", "Lcom/xiaoyi/camera/event/CameraStopSessionEvent;", "Lcom/xiaoyi/camera/event/SdcardFormatEvent;", "Lcom/xiaoyi/camera/event/SettingNotifiEvent;", "onFileManageClick_2", "point", "", "temp_width", "timeLapseType", "onFileManageClick_3", "onGetCameraBatteryLevel", "isBattery", FirebaseAnalytics.Param.LEVEL, "onGetRecordTime", "recordTime", "onGetStatus", "vfMode", "onGetTimelapseCountTime", "progress", "onGetTimelapseTime", "onItemClicked", FirebaseAnalytics.Param.INDEX, "onItemScrollfinish", "onItemScrolling", "onItemSelected", "picker", "onKeyDown", "keyCode", "Landroid/view/KeyEvent;", "onNotificationReceive", "errorCode", WBConstants.ACTION_LOG_TYPE_MESSAGE, "onNotificationSettingChange", "param", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onPhoneVibrating", "onPlayView", "isShow", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onProcessing", "isProcessing", "onReadyToPlay", "enable", "onRecordModeSelected", "onResume", "onSaveInstanceState", "outState", "onStartBurstCapture", "previewEnable", "onStartLoopRecord", "previewStatus", "supportPreview", "onStartNormalCapture", "isShutterAuto", "onStartNormalRecord", "supportPiv", "onStartPhotoRecord", "onStartPiv", "onStartSlowRecord", "onStartTimeLapesCapture", "onStartTimelapseRecord", "supportTimelapse", "timelapseOff", "timeMax", "onStartTimerCapture", "onStopBurstCapture", "onStopLoopRecord", "onStopNormalCapture", "onStopNormalRecord", "onStopPhotoRecord", "onStopPiv", "isSupportPiv", "onStopSlowRecord", "onStopTimelapseRecord", "timelapseSupport", "onStopTimerCapture", "onStopTimerLapesCapture", "onThumbnailLoad", "Lcom/xiaoyi/camera/module/FileItem;", "imageView", "onTimerShutter", "time", "onTimerTickTock", "onWarpStatus", "supportWarp", "warpStatus", "onWifiShutdown", "onWindowFocusChanged", "hasFocus", "playPreview", "reSetData", "reSetMenu", "respConnectDevFail", "rtnMsg", "Lcom/rp/rptool/model/IOCtrlReturnMsg;", "respConnectDevSuccess", "respSearchDevice", "sendConnectDevice", "uid", "pwd", "setButtonEnabled", "pivEnabled", "captureEnabled", "otherEnabled", "setPreviewParam", "setShutterRemind", "setSwitchAnimationFin", "setThumbDefault", "showBlockCover", "content", "showPicThumbnail", "path", "showSDCardNeedFormatDialod", "showSeekBarProgress", "timelapsOffMode", "showSmuggleDialog", "showTipsView", "isShowTip", "isShowEnter", "isShowSwitch", "isShowPiv", "showVideoThumbnail", "switchModel", "updateCaptureModeView", "updateLanguage", "updateRecordModeView", "Companion", "GestureListener", "MyGestureListener", "MyHandler", "ants_sports_camera_internationalRelease"})
@SuppressLint({"InflateParams"})
/* loaded from: classes.dex */
public final class QZCameraActivity extends BaseConfigActivity implements View.OnClickListener, HorizontalPicker.b, a.InterfaceC0204a, b.a, CameraMainController.a {
    private LinearLayout A;
    private LinearLayout B;
    private LinearLayout C;
    private RelativeLayout D;
    private ImageView E;
    private RelativeLayout F;
    private RelativeLayout G;
    private RelativeLayout H;
    private ImageView I;
    private SettingItemViewPager J;
    private LinearLayout K;
    private RelativeLayout L;
    private ImageView M;
    private ImageView N;
    private ImageView O;
    private LinearLayout P;
    private RelativeLayout Q;
    private ImageView R;
    private ImageView S;
    private ImageView T;
    private RelativeLayout U;
    private TextView V;
    private RelativeLayout W;
    private TextView X;
    private TimelapsSeekBar Y;
    private RelativeLayout Z;
    private boolean aA;
    private int aB;
    private Timer aC;
    private int aD;
    private SDCardRemoveDialogFragment aE;
    private String aF;
    private boolean aG;
    private c aH;
    private final d aN;
    private boolean aO;
    private final QZCameraActivity$receiver$1 aP;
    private String aQ;
    private boolean aR;
    private boolean aS;
    private boolean aT;
    private final Runnable aU;
    private final Runnable aV;
    private final Runnable aW;
    private final Runnable aX;
    private final Runnable aY;
    private final d.a aZ;
    private LinearLayout aa;
    private LinearLayout ab;
    private LinearLayout ac;
    private CameraMainController ad;
    private com.xiaomi.xy.sportscamera.camera.widget.a ae;
    private CustomBottomDialogFragment af;
    private CustomBottomDialogFragment ag;
    private Typeface ah;
    private Typeface ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private String an;
    private String ao;
    private String ap;
    private String aq;
    private int ar;
    private View as;
    private ImageView at;
    private CustomBatteryLoading au;
    private TextView av;
    private boolean aw;
    private boolean ax;
    private int ay;
    private List<String> d;
    private List<String> e;
    private List<String> f;
    private List<String> g;
    private LinearLayout h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private RelativeLayout m;
    private TextView n;
    private SudokuView o;
    private RelativeLayout p;
    private RelativeLayout q;
    private RPVideoViewHelper r;
    private TextView s;
    private ProgressBar t;
    private ImageView u;
    private RelativeLayout v;
    private TextView w;
    private TextView x;
    private TextView y;
    private HorizontalPicker z;
    public static final a b = new a(null);
    private static final String ba = ba;
    private static final String ba = ba;
    private static final String bb = bb;
    private static final String bb = bb;
    private final String c = QZCameraActivity.class.getSimpleName();
    private int az = -1;
    private final int aI = 10000;
    private final int aJ = 20000;
    private final int aK = 30000;
    private final int aL = 40961;
    private final int aM = 40962;

    @kotlin.g(a = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$Companion;", "", "()V", "LIGHT_FONTS_DIR", "", "getLIGHT_FONTS_DIR", "()Ljava/lang/String;", "REGULAR_FONTS_DIR", "getREGULAR_FONTS_DIR", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }

        public final String a() {
            return QZCameraActivity.ba;
        }

        public final String b() {
            return QZCameraActivity.bb;
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class aa implements Runnable {
        final /* synthetic */ boolean b;

        aa(boolean z) {
            this.b = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------- onChangeRecordMode result = " + this.b, new Object[0]);
            if (this.b) {
                com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------- mController != null = " + (QZCameraActivity.this.ad != null), new Object[0]);
                if (QZCameraActivity.this.ad != null) {
                    if (!kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model"))) {
                        QZCameraActivity.this.aS = true;
                        QZCameraActivity.this.S();
                    }
                    HorizontalPicker horizontalPicker = QZCameraActivity.this.z;
                    if (horizontalPicker == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    List<String> j = QZCameraActivity.this.j();
                    if (j == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    List<String> list = j;
                    if (list == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                    }
                    Object[] array = list.toArray(new String[list.size()]);
                    if (array == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    horizontalPicker.setValues((CharSequence[]) array);
                    CameraMainController cameraMainController = QZCameraActivity.this.ad;
                    if (cameraMainController == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    Constant.RecordMode p = cameraMainController.p();
                    List<String> h = QZCameraActivity.this.h();
                    if (h == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    Iterator<Integer> it2 = kotlin.collections.k.a((Collection<?>) h).iterator();
                    while (it2.hasNext()) {
                        int b = ((kotlin.collections.v) it2).b();
                        List<String> h2 = QZCameraActivity.this.h();
                        if (h2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        String str = h2.get(b);
                        CameraMainController cameraMainController2 = QZCameraActivity.this.ad;
                        if (cameraMainController2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        if (kotlin.jvm.internal.g.a((Object) str, (Object) cameraMainController2.p().toString())) {
                            HorizontalPicker horizontalPicker2 = QZCameraActivity.this.z;
                            if (horizontalPicker2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            horizontalPicker2.setSelectedItem(b);
                        }
                    }
                    Constant.RecordMode recordMode = Constant.RecordMode.TIMELAPES;
                    CameraMainController cameraMainController3 = QZCameraActivity.this.ad;
                    if (cameraMainController3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (kotlin.jvm.internal.g.a(recordMode, cameraMainController3.p()) && p.getOption() == null) {
                        p.setOption(com.xiaoyi.camera.g.a().a("timelapse_video"));
                    }
                }
                QZCameraActivity.this.d(true, true, true);
            } else {
                QZCameraActivity.this.a(R.string.setting_failed);
            }
            QZCameraActivity.this.c(true);
            QZCameraActivity.this.J();
            QZCameraActivity.this.U();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class ab implements Runnable {
        final /* synthetic */ com.xiaoyi.camera.a.a b;

        ab(com.xiaoyi.camera.a.a aVar) {
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (!QZCameraActivity.this.aA) {
                QZCameraActivity.this.p();
            }
            if (this.b.a()) {
                QZCameraActivity.this.x();
            }
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class ac implements Runnable {
        final /* synthetic */ com.xiaoyi.camera.a.d b;

        ac(com.xiaoyi.camera.a.d dVar) {
            this.b = dVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b.a() && QZCameraActivity.this.ad != null) {
                CameraMainController cameraMainController = QZCameraActivity.this.ad;
                if (cameraMainController == null) {
                    kotlin.jvm.internal.g.a();
                }
                cameraMainController.b();
                QZCameraActivity.this.I();
                QZCameraActivity.this.J();
            }
            QZCameraActivity.this.d(true, true, true);
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class ad implements Runnable {
        ad() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            QZCameraActivity.this.w();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class ae implements Runnable {
        ae() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            QZCameraActivity.this.w();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class af implements Runnable {
        af() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            QZCameraActivity.this.E();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    public static final class ag implements Runnable {
        final /* synthetic */ String b;

        ag(String str) {
            this.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b != null) {
                com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "---------------------------------------------------------- message = " + this.b, new Object[0]);
                QZCameraActivity.this.L();
                if (kotlin.jvm.internal.g.a((Object) CameraMainController.CameraMode.RecordMode.toString(), (Object) this.b)) {
                    if (kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model")) && QZCameraActivity.this.aR) {
                        QZCameraActivity.this.aS = true;
                        QZCameraActivity.this.S();
                    }
                    QZCameraActivity.this.u();
                    QZCameraActivity.this.J();
                    QZCameraActivity.this.O();
                    QZCameraActivity.this.U();
                } else if (kotlin.jvm.internal.g.a((Object) CameraMainController.CameraMode.CaptureMode.toString(), (Object) this.b)) {
                    if (kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model")) && QZCameraActivity.this.aR) {
                        QZCameraActivity.this.aS = true;
                        QZCameraActivity.this.S();
                    }
                    QZCameraActivity.this.H();
                    QZCameraActivity.this.J();
                    QZCameraActivity.this.O();
                    QZCameraActivity.this.U();
                } else if (kotlin.jvm.internal.g.a((Object) "sdCardNeedFormat", (Object) this.b)) {
                    QZCameraActivity.this.r();
                } else if (kotlin.jvm.internal.g.a((Object) "setting_changed", (Object) this.b)) {
                    QZCameraActivity.this.J();
                } else {
                    QZCameraActivity.this.a(this.b);
                }
                QZCameraActivity.this.c(true);
            }
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class ah implements Runnable {
        ah() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            QZCameraActivity.this.L();
            QZCameraActivity.this.J();
            QZCameraActivity.this.a(false, false, false, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    public static final class ai implements Runnable {
        final /* synthetic */ boolean b;

        ai(boolean z) {
            this.b = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (QZCameraActivity.this.t != null) {
                if (this.b) {
                    ProgressBar progressBar = QZCameraActivity.this.t;
                    if (progressBar == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    progressBar.setVisibility(0);
                    return;
                }
                ProgressBar progressBar2 = QZCameraActivity.this.t;
                if (progressBar2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                progressBar2.setVisibility(8);
            }
        }
    }

    @kotlin.g(a = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$onStartTimelapseRecord$1", "Ljava/util/TimerTask;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "run", "", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class aj extends TimerTask {
        aj() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            TimelapsSeekBar timelapsSeekBar = QZCameraActivity.this.Y;
            if (timelapsSeekBar == null) {
                kotlin.jvm.internal.g.a();
            }
            timelapsSeekBar.setProgress(QZCameraActivity.this.aD);
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class ak implements Runnable {
        final /* synthetic */ boolean b;
        final /* synthetic */ String c;

        ak(boolean z, String str) {
            this.b = z;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            QZCameraActivity.this.d(false, true, true);
            QZCameraActivity.this.c(true);
            if (this.b) {
                QZCameraActivity.this.g(this.c);
            }
            RelativeLayout relativeLayout = QZCameraActivity.this.U;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setVisibility(8);
            QZCameraActivity.this.a(false, false, false, false);
            RelativeLayout relativeLayout2 = QZCameraActivity.this.H;
            if (relativeLayout2 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout2.setVisibility(4);
            RelativeLayout relativeLayout3 = QZCameraActivity.this.G;
            if (relativeLayout3 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout3.setVisibility(0);
            RelativeLayout relativeLayout4 = QZCameraActivity.this.v;
            if (relativeLayout4 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout4.setVisibility(8);
            RelativeLayout relativeLayout5 = QZCameraActivity.this.U;
            if (relativeLayout5 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout5.setVisibility(8);
            TextView textView = QZCameraActivity.this.s;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setVisibility(8);
            ImageView imageView = QZCameraActivity.this.M;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class al implements Runnable {
        final /* synthetic */ boolean b;
        final /* synthetic */ ImageView c;
        final /* synthetic */ FileItem d;

        al(boolean z, ImageView imageView, FileItem fileItem) {
            this.b = z;
            this.c = imageView;
            this.d = fileItem;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (!this.b || this.c == null || this.d == null || !kotlin.jvm.internal.g.a(this.c.getTag(R.integer.camera_imageview_key), (Object) this.d.getPath()) || this.d.getThumbnail() == null) {
                QZCameraActivity.this.w();
            } else {
                this.c.setImageBitmap(this.d.getThumbnail());
            }
            if (QZCameraActivity.this.ad != null) {
                com.xiaoyi.camera.b.a.b(QZCameraActivity.this.ad);
            }
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class am implements Runnable {
        final /* synthetic */ int b;

        am(int i) {
            this.b = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (QZCameraActivity.this.s != null) {
                if (this.b > 0) {
                    TextView textView = QZCameraActivity.this.s;
                    if (textView == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    textView.setText(String.valueOf(this.b));
                } else {
                    TextView textView2 = QZCameraActivity.this.s;
                    if (textView2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    textView2.setText("");
                }
            }
            if (this.b <= 0) {
                QZCameraActivity.this.d(false, false, false);
            }
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class an implements Runnable {
        final /* synthetic */ boolean b;
        final /* synthetic */ boolean c;
        final /* synthetic */ boolean d;

        an(boolean z, boolean z2, boolean z3) {
            this.b = z;
            this.c = z2;
            this.d = z3;
        }

        @Override // java.lang.Runnable
        public final void run() {
            QZCameraActivity.this.d(true, true, true);
            QZCameraActivity.this.a(false, (String) null);
            if (!this.b) {
                QZCameraActivity.this.a(R.string.setting_failed);
                return;
            }
            if (!this.c) {
                ImageView imageView = QZCameraActivity.this.k;
                if (imageView == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView.setImageResource(R.drawable.camera_wrap_off_enable);
                ImageView imageView2 = QZCameraActivity.this.k;
                if (imageView2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView2.setEnabled(false);
                return;
            }
            ImageView imageView3 = QZCameraActivity.this.k;
            if (imageView3 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView3.setEnabled(true);
            if (this.d) {
                ImageView imageView4 = QZCameraActivity.this.k;
                if (imageView4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView4.setImageResource(R.drawable.camera_warp_off_select);
                return;
            }
            ImageView imageView5 = QZCameraActivity.this.k;
            if (imageView5 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView5.setImageResource(R.drawable.camera_warp_on_select);
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$showSDCardNeedFormatDialod$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class ao implements DimPanelFragment.c {
        ao() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            QZCameraActivity.this.startActivity(new Intent(QZCameraActivity.this, (Class<?>) CameraSDCardActivity.class));
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

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$showSmuggleDialog$1", "Lcom/ants360/z13/fragment/DimPanelFragment$MiddleClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onMiddleClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class ap implements DimPanelFragment.a {
        ap() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.a
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            QZCameraActivity.this.D();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.a
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.D();
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$showVideoThumbnail$1", "Lcom/xiaoyi/camera/filemanager/CameraFileManageTask$ThumbnailDownLoadListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onDownLoadCanceled", "", "onDownLoadFailed", "onDownLoadStart", "onDownLoadSuccess", "bitmap", "Landroid/graphics/Bitmap;", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class aq implements b.a {
        aq() {
        }

        @Override // com.xiaoyi.camera.b.b.a
        public void a(Bitmap bitmap) {
            ImageView imageView = QZCameraActivity.this.E;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setVisibility(0);
            if (bitmap == null) {
                QZCameraActivity.this.w();
                return;
            }
            ImageView imageView2 = QZCameraActivity.this.E;
            if (imageView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView2.setImageBitmap(bitmap);
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class ar implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public static final ar f4467a = new ar();

        ar() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.rp.rptool.util.d.a().c();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class as implements Runnable {
        as() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------ YiActionCamera.getInstance().stopSession()", new Object[0]);
            com.xiaoyi.camera.g.a().d();
            QZCameraActivity.this.aN.postDelayed(QZCameraActivity.this.aY, 200L);
        }
    }

    @kotlin.g(a = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0096\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0016J,\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0019"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$GestureListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;Landroid/content/Context;)V", "distance", "", "gestureDetector", "Landroid/view/GestureDetector;", "velocity", TtmlNode.LEFT, "", "onFling", "e1", "Landroid/view/MotionEvent;", "e2", "velocityX", "", "velocityY", "onTouch", "v", "Landroid/view/View;", "event", TtmlNode.RIGHT, "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public class b extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ QZCameraActivity f4469a;
        private final int b;
        private final int c;
        private final GestureDetector d;

        public b(QZCameraActivity qZCameraActivity, Context context) {
            kotlin.jvm.internal.g.b(context, "context");
            this.f4469a = qZCameraActivity;
            this.b = 100;
            this.c = 200;
            this.d = new GestureDetector(context, this);
        }

        public boolean a() {
            return false;
        }

        public boolean b() {
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent == null || motionEvent2 == null) {
                return false;
            }
            if (motionEvent.getX() - motionEvent2.getX() > this.b && Math.abs(f) > this.c) {
                a();
            }
            if (motionEvent2.getX() - motionEvent.getX() <= this.b || Math.abs(f) <= this.c) {
                return false;
            }
            b();
            return false;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            kotlin.jvm.internal.g.b(view, "v");
            kotlin.jvm.internal.g.b(motionEvent, "event");
            this.d.onTouchEvent(motionEvent);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @kotlin.g(a = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$MyGestureListener;", "Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$GestureListener;", "Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;", "context", "Landroid/content/Context;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;Landroid/content/Context;)V", TtmlNode.LEFT, "", TtmlNode.RIGHT, "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public final class c extends b {
        final /* synthetic */ QZCameraActivity b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(QZCameraActivity qZCameraActivity, Context context) {
            super(qZCameraActivity, context);
            kotlin.jvm.internal.g.b(context, "context");
            this.b = qZCameraActivity;
        }

        @Override // com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity.b
        public boolean a() {
            if (this.b.ad != null) {
                CameraMainController cameraMainController = this.b.ad;
                if (cameraMainController == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (!cameraMainController.s()) {
                    CameraMainController cameraMainController2 = this.b.ad;
                    if (cameraMainController2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (!cameraMainController2.r()) {
                        HorizontalPicker horizontalPicker = this.b.z;
                        if (horizontalPicker == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        horizontalPicker.a(1);
                        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "向左滑", new Object[0]);
                    }
                }
            }
            return super.a();
        }

        @Override // com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity.b
        public boolean b() {
            if (this.b.ad != null) {
                CameraMainController cameraMainController = this.b.ad;
                if (cameraMainController == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (!cameraMainController.s()) {
                    CameraMainController cameraMainController2 = this.b.ad;
                    if (cameraMainController2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    if (!cameraMainController2.r()) {
                        HorizontalPicker horizontalPicker = this.b.z;
                        if (horizontalPicker == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        horizontalPicker.a(-1);
                        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "向右滑", new Object[0]);
                    }
                }
            }
            return super.b();
        }
    }

    @kotlin.g(a = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, b = {"Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$MyHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;Landroid/os/Looper;)V", "handleMessage", "", "msg", "Landroid/os/Message;", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public final class d extends Handler {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ QZCameraActivity f4470a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(QZCameraActivity qZCameraActivity, Looper looper) {
            super(looper);
            kotlin.jvm.internal.g.b(looper, "looper");
            this.f4470a = qZCameraActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            kotlin.jvm.internal.g.b(message, "msg");
            super.handleMessage(message);
            int i = message.what;
            if (i != this.f4470a.aJ) {
                if (i != this.f4470a.aK) {
                    if (i == this.f4470a.n()) {
                        com.rp.rptool.util.d.a().a(this.f4470a.r);
                        return;
                    } else {
                        if (i == this.f4470a.o()) {
                            com.rp.rptool.util.d.a().e();
                            return;
                        }
                        return;
                    }
                }
                com.yiaction.common.util.g.a("debug_progress", "------------ HANDLER_TIMERRECORD_COMPLETE", new Object[0]);
                String a2 = com.xiaoyi.camera.g.a().a("timelapse_video_duration");
                if (!TextUtils.isEmpty(a2)) {
                    try {
                        String a3 = kotlin.text.i.a(a2, "s", "", true);
                        TextView textView = this.f4470a.w;
                        if (textView == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        Integer valueOf = Integer.valueOf(a3);
                        kotlin.jvm.internal.g.a((Object) valueOf, "Integer.valueOf(time)");
                        textView.setText(com.yiaction.common.util.a.a(valueOf.intValue()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                QZCameraActivity qZCameraActivity = this.f4470a;
                TimelapsSeekBar timelapsSeekBar = this.f4470a.Y;
                if (timelapsSeekBar == null) {
                    kotlin.jvm.internal.g.a();
                }
                qZCameraActivity.aD = timelapsSeekBar.getMax();
                TimelapsSeekBar timelapsSeekBar2 = this.f4470a.Y;
                if (timelapsSeekBar2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                timelapsSeekBar2.setProgress(this.f4470a.aD);
                return;
            }
            com.yiaction.common.util.g.a("debug_progress", "------------ HANDLER_TIMERRECORD_STOP", new Object[0]);
            TimelapsSeekBar timelapsSeekBar3 = this.f4470a.Y;
            if (timelapsSeekBar3 == null) {
                kotlin.jvm.internal.g.a();
            }
            timelapsSeekBar3.setVisibility(8);
            TimelapsSeekBar timelapsSeekBar4 = this.f4470a.Y;
            if (timelapsSeekBar4 == null) {
                kotlin.jvm.internal.g.a();
            }
            timelapsSeekBar4.setProgress(0);
            this.f4470a.aD = 0;
            TextView textView2 = this.f4470a.x;
            if (textView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView2.setText("00:00");
            TextView textView3 = this.f4470a.w;
            if (textView3 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView3.setText("00:00");
            RelativeLayout relativeLayout = this.f4470a.v;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setVisibility(8);
            if (this.f4470a.aC != null) {
                Timer timer = this.f4470a.aC;
                if (timer == null) {
                    kotlin.jvm.internal.g.a();
                }
                timer.cancel();
                this.f4470a.aC = (Timer) null;
                this.f4470a.aD = 0;
            }
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$checkFirmwareUpgrade$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class e implements DimPanelFragment.c {
        e() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.startActivity(new Intent(QZCameraActivity.this, (Class<?>) CameraUpgradeActivity.class));
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$checkFirmwareUpgrade$2", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class f implements DimPanelFragment.c {
        f() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            com.yiaction.common.util.g.a("debug_event", "CameraMainControlActivity upgradeDialog post CameraStopSessionEvent", new Object[0]);
            QZCameraActivity.this.p();
            QZCameraActivity.this.startActivity(new Intent(QZCameraActivity.this, (Class<?>) CameraDeviceUpgradeActivity.class));
            QZCameraActivity.this.finish();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$checkFirmwareUpgrade$3", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class g implements DimPanelFragment.c {
        g() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.startActivity(new Intent(QZCameraActivity.this, (Class<?>) CameraUpgradeActivity.class));
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$checkFirmwareUpgrade$4", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class h implements DimPanelFragment.c {
        h() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            com.yiaction.common.util.g.a("debug_event", "CameraMainControlActivity upgradeDialog post CameraStopSessionEvent", new Object[0]);
            QZCameraActivity.this.p();
            QZCameraActivity.this.startActivity(new Intent(QZCameraActivity.this, (Class<?>) CameraDeviceUpgradeActivity.class));
            QZCameraActivity.this.finish();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------ RPToolUtil.getInstance().deinitTool()", new Object[0]);
            com.rp.rptool.util.d.a().d();
            QZCameraActivity.this.aN.postDelayed(QZCameraActivity.this.aX, 200L);
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class j implements Runnable {
        j() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------ RPToolUtil.getInstance().deinitTool()", new Object[0]);
            com.rp.rptool.util.d.a().d();
            QZCameraActivity.this.aN.postDelayed(QZCameraActivity.this.aV, 100L);
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$disconnectDialog$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class k implements DimPanelFragment.c {
        k() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.finish();
            QZCameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.finish();
            QZCameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.finish();
            QZCameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$exit$1", "Lcom/ants360/z13/fragment/DimPanelFragment$OpClickListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onEmptyAreaClicked", "", "paramDialogFragment", "Landroid/app/DialogFragment;", "onLeftClicked", "onRightClicked", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class l implements DimPanelFragment.c {

        @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class a implements Runnable {

            /* renamed from: a, reason: collision with root package name */
            public static final a f4479a = new a();

            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                com.rp.rptool.util.d.a().d();
            }
        }

        @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
        /* loaded from: classes.dex */
        static final class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                QZCameraActivity.this.a(false, (String) null);
                QZCameraActivity.this.finish();
                QZCameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }
        }

        l() {
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void a(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.d(false, false, false);
            QZCameraActivity.this.aA = true;
            QZCameraActivity.this.p();
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void b(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
            QZCameraActivity.this.a(true, "");
            com.rp.rptool.util.d.a().b(QZCameraActivity.this.y());
            com.rp.rptool.util.d.a().e();
            QZCameraActivity.this.aN.postDelayed(a.f4479a, 200L);
            QZCameraActivity.this.aN.postDelayed(new b(), 500L);
        }

        @Override // com.ants360.z13.fragment.DimPanelFragment.c
        public void c(DialogFragment dialogFragment) {
            kotlin.jvm.internal.g.b(dialogFragment, "paramDialogFragment");
            dialogFragment.dismiss();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class m implements Runnable {
        m() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------ YiActionCamera.getInstance().stop()", new Object[0]);
            com.xiaoyi.camera.g.a().c();
            if (QZCameraActivity.this.aA) {
                QZCameraActivity.this.a(false, (String) null);
                QZCameraActivity.this.finish();
                QZCameraActivity.this.overridePendingTransition(0, R.anim.camera_close_exit);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    public static final class n implements Runnable {
        n() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.xiaoyi.camera.g.a().a("model");
            if (QZCameraActivity.this.z != null) {
                HorizontalPicker horizontalPicker = QZCameraActivity.this.z;
                if (horizontalPicker == null) {
                    kotlin.jvm.internal.g.a();
                }
                horizontalPicker.setVisibility(0);
            }
            if (QZCameraActivity.this.y != null) {
                TextView textView = QZCameraActivity.this.y;
                if (textView == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView.setVisibility(0);
            }
            if (QZCameraActivity.this.ad == null) {
                return;
            }
            CameraMainController cameraMainController = QZCameraActivity.this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            if (cameraMainController.g() != null) {
                CameraMainController cameraMainController2 = QZCameraActivity.this.ad;
                if (cameraMainController2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                CameraMainController.CameraMode g = cameraMainController2.g();
                if (g != null) {
                    switch (com.xiaomi.xy.sportscamera.camera.activity.a.h[g.ordinal()]) {
                        case 1:
                            ImageView imageView = QZCameraActivity.this.N;
                            if (imageView == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            imageView.setImageResource(R.drawable.icon_video_white);
                            ImageView imageView2 = QZCameraActivity.this.R;
                            if (imageView2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            imageView2.setImageResource(R.drawable.icon_camera_green);
                            HorizontalPicker horizontalPicker2 = QZCameraActivity.this.z;
                            if (horizontalPicker2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            List<String> j = QZCameraActivity.this.j();
                            if (j == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            List<String> list = j;
                            if (list == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                            }
                            Object[] array = list.toArray(new String[list.size()]);
                            if (array == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            horizontalPicker2.setValues((CharSequence[]) array);
                            StringBuilder append = new StringBuilder().append("------------------- HorizontalPicker Z16_VIDEO_MODEL setValues = ");
                            List<String> h = QZCameraActivity.this.h();
                            if (h == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, append.append(h.size()).toString(), new Object[0]);
                            List<String> h2 = QZCameraActivity.this.h();
                            if (h2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            Iterator<Integer> it2 = kotlin.collections.k.a((Collection<?>) h2).iterator();
                            while (it2.hasNext()) {
                                int b = ((kotlin.collections.v) it2).b();
                                List<String> h3 = QZCameraActivity.this.h();
                                if (h3 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                String str = h3.get(b);
                                CameraMainController cameraMainController3 = QZCameraActivity.this.ad;
                                if (cameraMainController3 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                if (kotlin.jvm.internal.g.a((Object) str, (Object) cameraMainController3.p().toString())) {
                                    QZCameraActivity.this.az = b;
                                    HorizontalPicker horizontalPicker3 = QZCameraActivity.this.z;
                                    if (horizontalPicker3 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    horizontalPicker3.setSelectedItem(QZCameraActivity.this.az);
                                }
                            }
                            return;
                        case 2:
                            ImageView imageView3 = QZCameraActivity.this.N;
                            if (imageView3 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            imageView3.setImageResource(R.drawable.icon_camera_white);
                            ImageView imageView4 = QZCameraActivity.this.R;
                            if (imageView4 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            imageView4.setImageResource(R.drawable.icon_video_green);
                            HorizontalPicker horizontalPicker4 = QZCameraActivity.this.z;
                            if (horizontalPicker4 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            List<String> i = QZCameraActivity.this.i();
                            if (i == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            List<String> list2 = i;
                            if (list2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                            }
                            Object[] array2 = list2.toArray(new String[list2.size()]);
                            if (array2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            horizontalPicker4.setValues((CharSequence[]) array2);
                            StringBuilder append2 = new StringBuilder().append("------------------- HorizontalPicker Z16_PHOTO_MODEL setValues = ");
                            List<String> g2 = QZCameraActivity.this.g();
                            if (g2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, append2.append(g2.size()).toString(), new Object[0]);
                            List<String> g3 = QZCameraActivity.this.g();
                            if (g3 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            Iterator<Integer> it3 = kotlin.collections.k.a((Collection<?>) g3).iterator();
                            while (it3.hasNext()) {
                                int b2 = ((kotlin.collections.v) it3).b();
                                List<String> g4 = QZCameraActivity.this.g();
                                if (g4 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                String str2 = g4.get(b2);
                                CameraMainController cameraMainController4 = QZCameraActivity.this.ad;
                                if (cameraMainController4 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                if (kotlin.jvm.internal.g.a((Object) str2, (Object) cameraMainController4.o().toString())) {
                                    QZCameraActivity.this.az = b2;
                                    HorizontalPicker horizontalPicker5 = QZCameraActivity.this.z;
                                    if (horizontalPicker5 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    horizontalPicker5.setSelectedItem(QZCameraActivity.this.az);
                                }
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    public static final class o implements Runnable {
        o() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            TextView textView = QZCameraActivity.this.y;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            CameraMainController cameraMainController = QZCameraActivity.this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setText(cameraMainController.m());
            RelativeLayout relativeLayout = QZCameraActivity.this.G;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setVisibility(0);
            SettingItemViewPager settingItemViewPager = QZCameraActivity.this.J;
            if (settingItemViewPager == null) {
                kotlin.jvm.internal.g.a();
            }
            settingItemViewPager.setVisibility(0);
            if (QZCameraActivity.this.ad == null) {
                return;
            }
            CameraMainController cameraMainController2 = QZCameraActivity.this.ad;
            if (cameraMainController2 == null) {
                kotlin.jvm.internal.g.a();
            }
            if (cameraMainController2.g() != null) {
                CameraMainController cameraMainController3 = QZCameraActivity.this.ad;
                if (cameraMainController3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                CameraMainController.CameraMode g = cameraMainController3.g();
                if (g != null) {
                    switch (com.xiaomi.xy.sportscamera.camera.activity.a.i[g.ordinal()]) {
                        case 1:
                            SettingItemViewPager settingItemViewPager2 = QZCameraActivity.this.J;
                            if (settingItemViewPager2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            settingItemViewPager2.a(com.xiaoyi.camera.e.a());
                            List<String> h = QZCameraActivity.this.h();
                            if (h == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            Iterator<Integer> it2 = kotlin.collections.k.a((Collection<?>) h).iterator();
                            while (it2.hasNext()) {
                                int b = ((kotlin.collections.v) it2).b();
                                List<String> h2 = QZCameraActivity.this.h();
                                if (h2 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                String str = h2.get(b);
                                CameraMainController cameraMainController4 = QZCameraActivity.this.ad;
                                if (cameraMainController4 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                if (kotlin.jvm.internal.g.a((Object) str, (Object) cameraMainController4.p().toString())) {
                                    QZCameraActivity.this.az = b;
                                    SettingItemViewPager settingItemViewPager3 = QZCameraActivity.this.J;
                                    if (settingItemViewPager3 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    settingItemViewPager3.setSelectedItem(QZCameraActivity.this.az);
                                }
                            }
                            break;
                        case 2:
                            SettingItemViewPager settingItemViewPager4 = QZCameraActivity.this.J;
                            if (settingItemViewPager4 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            settingItemViewPager4.a(com.xiaoyi.camera.e.b());
                            List<String> g2 = QZCameraActivity.this.g();
                            if (g2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            Iterator<Integer> it3 = kotlin.collections.k.a((Collection<?>) g2).iterator();
                            while (it3.hasNext()) {
                                int b2 = ((kotlin.collections.v) it3).b();
                                List<String> g3 = QZCameraActivity.this.g();
                                if (g3 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                String str2 = g3.get(b2);
                                CameraMainController cameraMainController5 = QZCameraActivity.this.ad;
                                if (cameraMainController5 == null) {
                                    kotlin.jvm.internal.g.a();
                                }
                                if (kotlin.jvm.internal.g.a((Object) str2, (Object) cameraMainController5.o().toString())) {
                                    QZCameraActivity.this.az = b2;
                                    SettingItemViewPager settingItemViewPager5 = QZCameraActivity.this.J;
                                    if (settingItemViewPager5 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    settingItemViewPager5.setSelectedItem(QZCameraActivity.this.az);
                                }
                            }
                            break;
                    }
                }
                CameraMainController cameraMainController6 = QZCameraActivity.this.ad;
                if (cameraMainController6 == null) {
                    kotlin.jvm.internal.g.a();
                }
                cameraMainController6.z();
                QZCameraActivity.this.K();
            }
        }
    }

    @kotlin.g(a = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$listener$1", "Lcom/rp/rptool/util/RPToolUtil$RPToolCallbackListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "handleRPToolCallback", "", "rtnMsg", "Lcom/rp/rptool/model/IOCtrlReturnMsg;", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class p implements d.a {
        p() {
        }

        @Override // com.rp.rptool.util.d.a
        public void a(com.rp.rptool.a.b bVar) {
            kotlin.jvm.internal.g.b(bVar, "rtnMsg");
            int b = bVar.b();
            if (40961 <= b && 57374 >= b) {
                StringBuilder append = new StringBuilder().append("respIOCtrlMsg : 0x");
                String hexString = Integer.toHexString(b);
                if (hexString == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String upperCase = hexString.toUpperCase();
                kotlin.jvm.internal.g.a((Object) upperCase, "(this as java.lang.String).toUpperCase()");
                Log.d("respIOCtrlMsg", append.append(upperCase).toString());
            }
            switch (b) {
                case 12289:
                    QZCameraActivity.this.a(R.string.camera_connect_connect_success);
                    QZCameraActivity.this.b(bVar);
                    return;
                case 12290:
                    QZCameraActivity.this.a(R.string.camera_connect_connect_fail);
                    QZCameraActivity.this.c(bVar);
                    return;
                case 12322:
                    QZCameraActivity.this.a(bVar);
                    return;
                case 41023:
                    Toast.makeText(com.rp.a.b.a(), "MSG_START_VIDEO", 0).show();
                    com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------ iOCTRLType = " + b, new Object[0]);
                    QZCameraActivity.this.aN.sendEmptyMessage(QZCameraActivity.this.n());
                    return;
                default:
                    return;
            }
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadEndAnimation$1", "Landroid/view/animation/Animation$AnimationListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class q implements Animation.AnimationListener {
        q() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
            ImageView imageView = QZCameraActivity.this.u;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.clearAnimation();
            ImageView imageView2 = QZCameraActivity.this.u;
            if (imageView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView2.setVisibility(8);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadStartAnimation$1", "Landroid/view/animation/Animation$AnimationListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class r implements Animation.AnimationListener {
        r() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
            QZCameraActivity.this.P();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadSwitchAnimation$1", "Landroid/view/animation/Animation$AnimationListener;", "(Lcom/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity;)V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class s implements Animation.AnimationListener {
        s() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
            QZCameraActivity.this.aT = true;
            QZCameraActivity.this.S();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadSwitchAnimation$2", "Landroid/view/animation/Animation$AnimationListener;", "()V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class t implements Animation.AnimationListener {
        t() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadSwitchAnimation$3", "Landroid/view/animation/Animation$AnimationListener;", "()V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class u implements Animation.AnimationListener {
        u() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadSwitchAnimation$4", "Landroid/view/animation/Animation$AnimationListener;", "()V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class v implements Animation.AnimationListener {
        v() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadSwitchAnimation$5", "Landroid/view/animation/Animation$AnimationListener;", "()V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class w implements Animation.AnimationListener {
        w() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, b = {"com/xiaomi/xy/sportscamera/camera/activity/QZCameraActivity$loadSwitchAnimation$6", "Landroid/view/animation/Animation$AnimationListener;", "()V", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "ants_sports_camera_internationalRelease"})
    /* loaded from: classes.dex */
    public static final class x implements Animation.AnimationListener {
        x() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            kotlin.jvm.internal.g.b(animation, "animation");
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class y implements Runnable {
        y() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            QZCameraActivity.this.F();
        }
    }

    @kotlin.g(a = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, b = {"<anonymous>", "", "run"})
    /* loaded from: classes.dex */
    static final class z implements Runnable {
        final /* synthetic */ boolean b;

        z(boolean z) {
            this.b = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------- onChangeCaptureMode result = " + this.b, new Object[0]);
            if (this.b) {
                com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------- mController != null = " + (QZCameraActivity.this.ad != null), new Object[0]);
                if (QZCameraActivity.this.ad != null) {
                    if (!kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model"))) {
                        QZCameraActivity.this.aS = true;
                        QZCameraActivity.this.S();
                    }
                    com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------- AmbaParam.MODEL_Z16", new Object[0]);
                    HorizontalPicker horizontalPicker = QZCameraActivity.this.z;
                    if (horizontalPicker == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    List<String> i = QZCameraActivity.this.i();
                    if (i == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    List<String> list = i;
                    if (list == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                    }
                    Object[] array = list.toArray(new String[list.size()]);
                    if (array == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    horizontalPicker.setValues((CharSequence[]) array);
                    List<String> g = QZCameraActivity.this.g();
                    if (g == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    Iterator<Integer> it2 = kotlin.collections.k.a((Collection<?>) g).iterator();
                    while (it2.hasNext()) {
                        int b = ((kotlin.collections.v) it2).b();
                        List<String> g2 = QZCameraActivity.this.g();
                        if (g2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        String str = g2.get(b);
                        CameraMainController cameraMainController = QZCameraActivity.this.ad;
                        if (cameraMainController == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        if (kotlin.jvm.internal.g.a((Object) str, (Object) cameraMainController.o().toString())) {
                            HorizontalPicker horizontalPicker2 = QZCameraActivity.this.z;
                            if (horizontalPicker2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            horizontalPicker2.setSelectedItem(b);
                        }
                    }
                }
                QZCameraActivity.this.d(true, true, true);
            } else {
                QZCameraActivity.this.a(R.string.set_failed);
            }
            QZCameraActivity.this.c(true);
            QZCameraActivity.this.J();
            QZCameraActivity.this.U();
        }
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity$receiver$1] */
    public QZCameraActivity() {
        Looper mainLooper = Looper.getMainLooper();
        kotlin.jvm.internal.g.a((Object) mainLooper, "Looper.getMainLooper()");
        this.aN = new d(this, mainLooper);
        this.aO = true;
        this.aP = new BroadcastReceiver() { // from class: com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity$receiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                RelativeLayout relativeLayout;
                RelativeLayout relativeLayout2;
                SDCardRemoveDialogFragment sDCardRemoveDialogFragment;
                SDCardRemoveDialogFragment sDCardRemoveDialogFragment2;
                SDCardRemoveDialogFragment sDCardRemoveDialogFragment3;
                SDCardRemoveDialogFragment sDCardRemoveDialogFragment4;
                SDCardRemoveDialogFragment sDCardRemoveDialogFragment5;
                SDCardRemoveDialogFragment sDCardRemoveDialogFragment6;
                SDCardRemoveDialogFragment sDCardRemoveDialogFragment7;
                g.b(context, "context");
                g.b(intent, "intent");
                String action = intent.getAction();
                if (g.a((Object) com.xiaoyi.camera.a.a("sd_card_status"), (Object) action)) {
                    String str = (String) null;
                    try {
                        str = intent.getStringExtra("current_operation_model");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (QZCameraActivity.this.a()) {
                        if (g.a((Object) "insert", (Object) str)) {
                            QZCameraActivity.this.a(QZCameraActivity.this.getResources().getString(R.string.sd_card_inserd));
                            sDCardRemoveDialogFragment5 = QZCameraActivity.this.aE;
                            if (sDCardRemoveDialogFragment5 != null) {
                                sDCardRemoveDialogFragment6 = QZCameraActivity.this.aE;
                                if (sDCardRemoveDialogFragment6 == null) {
                                    g.a();
                                }
                                if (sDCardRemoveDialogFragment6.getDialog() != null) {
                                    sDCardRemoveDialogFragment7 = QZCameraActivity.this.aE;
                                    if (sDCardRemoveDialogFragment7 == null) {
                                        g.a();
                                    }
                                    sDCardRemoveDialogFragment7.dismiss();
                                }
                            }
                            QZCameraActivity.this.a(false, (String) null);
                            QZCameraActivity.this.d(false, true, true);
                            return;
                        }
                        if (g.a((Object) ProductAction.ACTION_REMOVE, (Object) str)) {
                            sDCardRemoveDialogFragment = QZCameraActivity.this.aE;
                            if (sDCardRemoveDialogFragment != null) {
                                sDCardRemoveDialogFragment3 = QZCameraActivity.this.aE;
                                if (sDCardRemoveDialogFragment3 == null) {
                                    g.a();
                                }
                                if (sDCardRemoveDialogFragment3.getDialog() != null) {
                                    sDCardRemoveDialogFragment4 = QZCameraActivity.this.aE;
                                    if (sDCardRemoveDialogFragment4 == null) {
                                        g.a();
                                    }
                                    if (sDCardRemoveDialogFragment4.getDialog().isShowing()) {
                                        return;
                                    }
                                }
                            }
                            QZCameraActivity.this.aE = new SDCardRemoveDialogFragment();
                            sDCardRemoveDialogFragment2 = QZCameraActivity.this.aE;
                            if (sDCardRemoveDialogFragment2 == null) {
                                g.a();
                            }
                            sDCardRemoveDialogFragment2.a(QZCameraActivity.this);
                            QZCameraActivity.this.a(false, (String) null);
                            QZCameraActivity.this.d(false, true, true);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_album"), (Object) action)) {
                    QZCameraActivity.this.L();
                    QZCameraActivity.this.d(false, false, false);
                    QZCameraActivity.this.a(true, QZCameraActivity.this.getString(R.string.camera_album_operation_ing));
                    relativeLayout2 = QZCameraActivity.this.Z;
                    if (relativeLayout2 != null) {
                        QZCameraActivity.this.a(false, false, false, false);
                        return;
                    }
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_album"), (Object) action)) {
                    QZCameraActivity.this.d(false, true, true);
                    if (!QZCameraActivity.this.aA) {
                        QZCameraActivity.this.a(false, (String) null);
                    }
                    relativeLayout = QZCameraActivity.this.q;
                    if (relativeLayout == null) {
                        g.a();
                    }
                    relativeLayout.setVisibility(0);
                    CameraMainController cameraMainController = QZCameraActivity.this.ad;
                    if (cameraMainController == null) {
                        g.a();
                    }
                    cameraMainController.j();
                    CameraMainController cameraMainController2 = QZCameraActivity.this.ad;
                    if (cameraMainController2 == null) {
                        g.a();
                    }
                    if (!cameraMainController2.B()) {
                        CameraMainController cameraMainController3 = QZCameraActivity.this.ad;
                        if (cameraMainController3 == null) {
                            g.a();
                        }
                        if (cameraMainController3.d()) {
                            QZCameraActivity.this.c(true);
                        }
                    }
                    String stringExtra = intent.getStringExtra("param");
                    if (stringExtra != null) {
                        try {
                            if (stringExtra.length() <= 0 || Integer.parseInt(stringExtra) <= 0) {
                                return;
                            }
                            QZCameraActivity.this.w();
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_format_done"), (Object) action)) {
                    QZCameraActivity.this.a(QZCameraActivity.this.getResources().getString(R.string.sd_card_format_done));
                    QZCameraActivity.this.w();
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_full"), (Object) action)) {
                    QZCameraActivity.this.a(QZCameraActivity.this.getResources().getString(R.string.no_more_space));
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_io_error"), (Object) action)) {
                    QZCameraActivity.this.a(QZCameraActivity.this.getResources().getString(R.string.sd_card_error));
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_fwupdate"), (Object) action)) {
                    QZCameraActivity.this.a(true, QZCameraActivity.this.getString(R.string.firmware_update_info));
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_fwupdate"), (Object) action)) {
                    QZCameraActivity.this.a(false, (String) null);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_sdformat"), (Object) action)) {
                    QZCameraActivity.this.a(true, QZCameraActivity.this.getString(R.string.sd_card_format));
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_sdformat"), (Object) action)) {
                    QZCameraActivity.this.a(false, (String) null);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_mvrecover"), (Object) action)) {
                    QZCameraActivity.this.a(true, QZCameraActivity.this.getString(R.string.video_file_recovery));
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_mvrecover"), (Object) action)) {
                    QZCameraActivity.this.a(false, (String) null);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_optimize_start"), (Object) action)) {
                    QZCameraActivity.this.a(true, QZCameraActivity.this.getString(R.string.camera_sdcard_optimize_start));
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("sdcard_optimize_stop"), (Object) action)) {
                    QZCameraActivity.this.a(false, (String) null);
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("enter_usb_storage"), (Object) action)) {
                    QZCameraActivity.this.a(true, QZCameraActivity.this.getString(R.string.camera_start_usb_storage_mode));
                    return;
                }
                if (g.a((Object) com.xiaoyi.camera.a.a("exit_usb_storage"), (Object) action)) {
                    QZCameraActivity.this.a(false, (String) null);
                } else if (g.a((Object) com.xiaoyi.camera.a.a("voice_control_sample_start"), (Object) action)) {
                    QZCameraActivity.this.a(true, QZCameraActivity.this.getString(R.string.camera_voice_control));
                } else if (g.a((Object) com.xiaoyi.camera.a.a("voice_control_sample_stop"), (Object) action)) {
                    QZCameraActivity.this.a(false, (String) null);
                }
            }
        };
        this.aU = new j();
        this.aV = ar.f4467a;
        this.aW = new i();
        this.aX = new as();
        this.aY = new m();
        this.aZ = new p();
    }

    private final void B() {
        this.d = com.xiaoyi.camera.module.b.a("system_photo_mode");
        List<String> list = this.d;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        if (list.size() == 0) {
            String captureMode = Constant.CaptureMode.NORMAL.toString();
            kotlin.jvm.internal.g.a((Object) captureMode, "CaptureMode.NORMAL.toString()");
            String captureMode2 = Constant.CaptureMode.TIMER.toString();
            kotlin.jvm.internal.g.a((Object) captureMode2, "CaptureMode.TIMER.toString()");
            String captureMode3 = Constant.CaptureMode.BURST.toString();
            kotlin.jvm.internal.g.a((Object) captureMode3, "CaptureMode.BURST.toString()");
            this.d = kotlin.collections.k.c(captureMode, captureMode2, captureMode3);
        }
        this.e = com.xiaoyi.camera.module.b.a("system_video_mode");
        List<String> list2 = this.e;
        if (list2 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (list2.size() == 0) {
            String recordMode = Constant.RecordMode.NORMAL.toString();
            kotlin.jvm.internal.g.a((Object) recordMode, "RecordMode.NORMAL.toString()");
            String recordMode2 = Constant.RecordMode.LOOP.toString();
            kotlin.jvm.internal.g.a((Object) recordMode2, "RecordMode.LOOP.toString()");
            String recordMode3 = Constant.RecordMode.TIMELAPES.toString();
            kotlin.jvm.internal.g.a((Object) recordMode3, "RecordMode.TIMELAPES.toString()");
            this.e = kotlin.collections.k.c(recordMode, recordMode2, recordMode3);
        }
        this.f = new ArrayList();
        this.g = new ArrayList();
        List<String> list3 = this.d;
        if (list3 == null) {
            kotlin.jvm.internal.g.a();
        }
        for (String str : list3) {
            if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.NORMAL.toString())) {
                List<String> list4 = this.f;
                if (list4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string = getResources().getString(R.string.tv_normal);
                kotlin.jvm.internal.g.a((Object) string, "resources.getString(R.string.tv_normal)");
                list4.add(string);
            } else if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.TIMER.toString())) {
                List<String> list5 = this.f;
                if (list5 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string2 = getResources().getString(R.string.tv_timer);
                kotlin.jvm.internal.g.a((Object) string2, "resources.getString(R.string.tv_timer)");
                list5.add(string2);
            } else if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.TIMELAPES.toString())) {
                List<String> list6 = this.f;
                if (list6 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string3 = getResources().getString(R.string.time_lapse_pic);
                kotlin.jvm.internal.g.a((Object) string3, "resources.getString(R.string.time_lapse_pic)");
                list6.add(string3);
            } else if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.BURST.toString())) {
                List<String> list7 = this.f;
                if (list7 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string4 = getResources().getString(R.string.tv_burst);
                kotlin.jvm.internal.g.a((Object) string4, "resources.getString(R.string.tv_burst)");
                list7.add(string4);
            }
        }
        List<String> list8 = this.e;
        if (list8 == null) {
            kotlin.jvm.internal.g.a();
        }
        for (String str2 : list8) {
            if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.NORMAL.toString())) {
                List<String> list9 = this.g;
                if (list9 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string5 = getResources().getString(R.string.tv_normal_record);
                kotlin.jvm.internal.g.a((Object) string5, "resources.getString(R.string.tv_normal_record)");
                list9.add(string5);
            } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.TIMELAPES.toString())) {
                List<String> list10 = this.g;
                if (list10 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string6 = getResources().getString(R.string.tv_timlapse_record);
                kotlin.jvm.internal.g.a((Object) string6, "resources.getString(R.string.tv_timlapse_record)");
                list10.add(string6);
            } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.SLOW.toString())) {
                List<String> list11 = this.g;
                if (list11 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string7 = getResources().getString(R.string.slow_motion);
                kotlin.jvm.internal.g.a((Object) string7, "resources.getString(R.string.slow_motion)");
                list11.add(string7);
            } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.LOOP.toString())) {
                List<String> list12 = this.g;
                if (list12 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string8 = getResources().getString(R.string.loop_record);
                kotlin.jvm.internal.g.a((Object) string8, "resources.getString(R.string.loop_record)");
                list12.add(string8);
            } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.PHOTO.toString())) {
                List<String> list13 = this.g;
                if (list13 == null) {
                    kotlin.jvm.internal.g.a();
                }
                String string9 = getResources().getString(R.string.tv_record_photo);
                kotlin.jvm.internal.g.a((Object) string9, "resources.getString(R.string.tv_record_photo)");
                list13.add(string9);
            }
        }
    }

    private final void C() {
        Bundle bundle = new Bundle();
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("middle_button", getString(R.string.agree));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.smuggle_tip));
        bundle.putBoolean("one_button", true);
        Fragment instantiate = Fragment.instantiate(this, CustomBottomDialogFragment.class.getName(), bundle);
        if (instantiate == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.fragment.CustomBottomDialogFragment");
        }
        CustomBottomDialogFragment customBottomDialogFragment = (CustomBottomDialogFragment) instantiate;
        customBottomDialogFragment.a(new ap());
        customBottomDialogFragment.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void D() {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.CHINA;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0231, code lost:
    
        r0 = new android.os.Bundle();
        r0.putString("title", getString(com.xiaomi.xy.sportscamera.R.string.camera_firmware_upgrade));
        r0.putString(com.sina.weibo.sdk.constant.WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(com.xiaomi.xy.sportscamera.R.string.upgrade_tips_on_ready));
        r0.putString("left_button", getString(com.xiaomi.xy.sportscamera.R.string.next_time));
        r0.putString("right_button", getString(com.xiaomi.xy.sportscamera.R.string.upgrade_at_once));
        r0.putInt(com.google.android.exoplayer.text.ttml.TtmlNode.TAG_STYLE, com.xiaomi.xy.sportscamera.R.style.DimPanel_No_StatusBar);
        r10.ag = new com.ants360.z13.fragment.CustomBottomDialogFragment();
        r1 = r10.ag;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0271, code lost:
    
        if (r1 != null) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0273, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0276, code lost:
    
        r1.setArguments(r0);
        r1 = r10.ag;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x027b, code lost:
    
        if (r1 != null) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x027d, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0280, code lost:
    
        r1.a(new com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity.g(r10));
        r0 = r10.ag;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x028c, code lost:
    
        if (r0 != null) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x028e, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0291, code lost:
    
        r0.a(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x022f, code lost:
    
        if (r0.booleanValue() != false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00d6, code lost:
    
        if (r0.booleanValue() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00f7, code lost:
    
        if (r10.ad == null) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00f9, code lost:
    
        r0 = r10.ad;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00fb, code lost:
    
        if (r0 != null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00fd, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0104, code lost:
    
        if (r0.s() != false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0106, code lost:
    
        r0 = r10.ad;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0108, code lost:
    
        if (r0 != null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x010a, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0111, code lost:
    
        if (r0.r() != false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0113, code lost:
    
        r0 = new android.os.Bundle();
        r0.putString("title", getString(com.xiaomi.xy.sportscamera.R.string.firmware_update_info));
        r0.putString(com.sina.weibo.sdk.constant.WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(com.xiaomi.xy.sportscamera.R.string.upgrade_tips_on_ready));
        r0.putString("left_button", getString(com.xiaomi.xy.sportscamera.R.string.next_time));
        r0.putString("right_button", getString(com.xiaomi.xy.sportscamera.R.string.upgrade_at_once));
        r0.putInt(com.google.android.exoplayer.text.ttml.TtmlNode.TAG_STYLE, com.xiaomi.xy.sportscamera.R.style.DimPanel_No_StatusBar);
        r10.ag = new com.ants360.z13.fragment.CustomBottomDialogFragment();
        r1 = r10.ag;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0153, code lost:
    
        if (r1 != null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0155, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0158, code lost:
    
        r1.setArguments(r0);
        r1 = r10.ag;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x015d, code lost:
    
        if (r1 != null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x015f, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0162, code lost:
    
        r1.a(new com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity.e(r10));
        r0 = r10.ag;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x016e, code lost:
    
        if (r0 != null) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0170, code lost:
    
        kotlin.jvm.internal.g.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0173, code lost:
    
        r0.a(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00f3, code lost:
    
        if (r0.booleanValue() != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0212, code lost:
    
        if (r0.booleanValue() == false) goto L94;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void E() {
        /*
            Method dump skipped, instructions count: 823
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity.E():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void F() {
        if (this.ad != null) {
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            cameraMainController.t();
        }
        I();
        J();
        if (kotlin.jvm.internal.g.a((Object) "enter_album", (Object) com.xiaoyi.camera.g.a().a(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM))) {
            d(false, false, false);
            a(true, getString(R.string.camera_album_operation_ing));
        } else {
            d(false, true, true);
        }
        String a2 = com.xiaoyi.camera.g.a().a("battery");
        if (a2 != null && a2.length() > 0 && Integer.parseInt(a2) > 0) {
            i(true, true, a2);
        }
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController2.k();
        if (this.aO) {
            String a3 = com.yiaction.common.util.i.a().a("video_photo_path");
            if (TextUtils.isEmpty(a3)) {
                w();
            } else if (kotlin.text.i.b(a3, ".jpg", false, 2, (Object) null) || kotlin.text.i.b(a3, ".JPG", false, 2, (Object) null)) {
                f(a3);
            } else {
                kotlin.jvm.internal.g.a((Object) a3, "path");
                g(a3);
            }
            this.aO = false;
        }
        CameraMainController cameraMainController3 = this.ad;
        if (cameraMainController3 == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController3.j();
    }

    private final void G() {
        this.az = -1;
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        CameraMainController.CameraMode g2 = cameraMainController.g();
        if (g2 == null) {
            return;
        }
        switch (com.xiaomi.xy.sportscamera.camera.activity.a.e[g2.ordinal()]) {
            case 1:
                ImageView imageView = this.O;
                if (imageView == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView.setImageResource(R.drawable.icon_camera_green);
                ImageView imageView2 = this.S;
                if (imageView2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView2.setImageResource(R.drawable.icon_video_white);
                t();
                CameraMainController cameraMainController2 = this.ad;
                if (cameraMainController2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                Constant.RecordMode p2 = cameraMainController2.p();
                if (p2 != null) {
                    a(p2);
                } else {
                    a(Constant.RecordMode.NORMAL);
                }
                StatisticHelper.h("Video");
                return;
            case 2:
                ImageView imageView3 = this.O;
                if (imageView3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView3.setImageResource(R.drawable.icon_video_green);
                ImageView imageView4 = this.S;
                if (imageView4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView4.setImageResource(R.drawable.icon_camera_white);
                v();
                CameraMainController cameraMainController3 = this.ad;
                if (cameraMainController3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                Constant.CaptureMode o2 = cameraMainController3.o();
                if (o2 != null) {
                    a(o2);
                } else {
                    a(Constant.CaptureMode.NORMAL);
                }
                StatisticHelper.h("Picture");
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void H() {
        if (this.z != null) {
            HorizontalPicker horizontalPicker = this.z;
            if (horizontalPicker == null) {
                kotlin.jvm.internal.g.a();
            }
            horizontalPicker.setVisibility(0);
        }
        if (this.y != null) {
            TextView textView = this.y;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setVisibility(0);
        }
        ImageView imageView = this.N;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.icon_camera_white);
        ImageView imageView2 = this.R;
        if (imageView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView2.setImageResource(R.drawable.icon_video_green);
        HorizontalPicker horizontalPicker2 = this.z;
        if (horizontalPicker2 == null) {
            kotlin.jvm.internal.g.a();
        }
        List<String> list = this.f;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        List<String> list2 = list;
        if (list2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Object[] array = list2.toArray(new String[list2.size()]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        horizontalPicker2.setValues((CharSequence[]) array);
        List<String> list3 = this.d;
        if (list3 == null) {
            kotlin.jvm.internal.g.a();
        }
        Iterator<Integer> it2 = kotlin.collections.k.a((Collection<?>) list3).iterator();
        while (it2.hasNext()) {
            int b2 = ((kotlin.collections.v) it2).b();
            List<String> list4 = this.d;
            if (list4 == null) {
                kotlin.jvm.internal.g.a();
            }
            String str = list4.get(b2);
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            if (kotlin.jvm.internal.g.a((Object) str, (Object) cameraMainController.o().toString())) {
                this.az = b2;
                HorizontalPicker horizontalPicker3 = this.z;
                if (horizontalPicker3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                horizontalPicker3.setSelectedItem(this.az);
            }
        }
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        Constant.CaptureMode o2 = cameraMainController2.o();
        if (o2 == null) {
            return;
        }
        switch (com.xiaomi.xy.sportscamera.camera.activity.a.g[o2.ordinal()]) {
            case 1:
                ImageView imageView3 = this.u;
                if (imageView3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView3.setImageResource(R.drawable.show_photo_timer);
                return;
            case 2:
                ImageView imageView4 = this.u;
                if (imageView4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView4.setImageResource(R.drawable.show_photo_burst);
                return;
            case 3:
                ImageView imageView5 = this.u;
                if (imageView5 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView5.setImageResource(R.drawable.show_photo_timelapse);
                return;
            case 4:
                ImageView imageView6 = this.u;
                if (imageView6 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView6.setImageResource(R.drawable.show_photo_normal);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void I() {
        runOnUiThread(new n());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void J() {
        runOnUiThread(new o());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void K() {
        if (this.ad == null) {
            return;
        }
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        if (cameraMainController.g() != null) {
            CameraMainController cameraMainController2 = this.ad;
            if (cameraMainController2 == null) {
                kotlin.jvm.internal.g.a();
            }
            CameraMainController.CameraMode g2 = cameraMainController2.g();
            if (g2 != null) {
                switch (com.xiaomi.xy.sportscamera.camera.activity.a.k[g2.ordinal()]) {
                    case 1:
                        String a2 = com.xiaoyi.camera.g.a().a("video_shutter");
                        if (!kotlin.jvm.internal.g.a((Object) "on", (Object) com.xiaoyi.camera.g.a().a("video_shutter_support")) || TextUtils.isEmpty(a2) || kotlin.text.i.a("auto", a2, true)) {
                            RelativeLayout relativeLayout = this.W;
                            if (relativeLayout == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            relativeLayout.setVisibility(8);
                            return;
                        }
                        RelativeLayout relativeLayout2 = this.W;
                        if (relativeLayout2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        relativeLayout2.setVisibility(0);
                        TextView textView = this.X;
                        if (textView == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        textView.setText(getString(R.string.camera_shutter_remind, new Object[]{a2}));
                        if (kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model"))) {
                            TextView textView2 = this.X;
                            if (textView2 == null) {
                                kotlin.jvm.internal.g.a();
                            }
                            textView2.setTextColor(getResources().getColor(R.color.white));
                            return;
                        }
                        TextView textView3 = this.X;
                        if (textView3 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        textView3.setTextColor(getResources().getColor(R.color.primary_grey));
                        return;
                    case 2:
                        String a3 = com.xiaoyi.camera.g.a().a("iq_photo_shutter");
                        CameraMainController cameraMainController3 = this.ad;
                        if (cameraMainController3 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        Constant.CaptureMode o2 = cameraMainController3.o();
                        if (o2 != null) {
                            switch (com.xiaomi.xy.sportscamera.camera.activity.a.j[o2.ordinal()]) {
                                case 1:
                                    String a4 = com.xiaoyi.camera.g.a().a("timelapse_photo_shutter");
                                    if (TextUtils.isEmpty(a4) || kotlin.text.i.a("auto", a4, true)) {
                                        RelativeLayout relativeLayout3 = this.W;
                                        if (relativeLayout3 == null) {
                                            kotlin.jvm.internal.g.a();
                                        }
                                        relativeLayout3.setVisibility(8);
                                        return;
                                    }
                                    RelativeLayout relativeLayout4 = this.W;
                                    if (relativeLayout4 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    relativeLayout4.setVisibility(0);
                                    TextView textView4 = this.X;
                                    if (textView4 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    textView4.setText(getString(R.string.camera_shutter_remind, new Object[]{a4}));
                                    if (kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model"))) {
                                        TextView textView5 = this.X;
                                        if (textView5 == null) {
                                            kotlin.jvm.internal.g.a();
                                        }
                                        textView5.setTextColor(getResources().getColor(R.color.white));
                                        return;
                                    }
                                    TextView textView6 = this.X;
                                    if (textView6 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    textView6.setTextColor(getResources().getColor(R.color.primary_grey));
                                    return;
                                case 2:
                                case 3:
                                    if (TextUtils.isEmpty(a3) || kotlin.text.i.a("auto", a3, true)) {
                                        RelativeLayout relativeLayout5 = this.W;
                                        if (relativeLayout5 == null) {
                                            kotlin.jvm.internal.g.a();
                                        }
                                        relativeLayout5.setVisibility(8);
                                        return;
                                    }
                                    RelativeLayout relativeLayout6 = this.W;
                                    if (relativeLayout6 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    relativeLayout6.setVisibility(0);
                                    TextView textView7 = this.X;
                                    if (textView7 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    textView7.setText(getString(R.string.camera_shutter_remind, new Object[]{a3}));
                                    if (kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model"))) {
                                        TextView textView8 = this.X;
                                        if (textView8 == null) {
                                            kotlin.jvm.internal.g.a();
                                        }
                                        textView8.setTextColor(getResources().getColor(R.color.white));
                                        return;
                                    }
                                    TextView textView9 = this.X;
                                    if (textView9 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    textView9.setTextColor(getResources().getColor(R.color.primary_grey));
                                    return;
                            }
                        }
                        RelativeLayout relativeLayout7 = this.W;
                        if (relativeLayout7 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        relativeLayout7.setVisibility(8);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void L() {
        if (this.ae != null) {
            com.xiaomi.xy.sportscamera.camera.widget.a aVar = this.ae;
            if (aVar == null) {
                kotlin.jvm.internal.g.a();
            }
            if (aVar.isShowing()) {
                com.xiaomi.xy.sportscamera.camera.widget.a aVar2 = this.ae;
                if (aVar2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                aVar2.dismiss();
            }
        }
    }

    private final void M() {
        if (this.af != null) {
            CustomBottomDialogFragment customBottomDialogFragment = this.af;
            if (customBottomDialogFragment == null) {
                kotlin.jvm.internal.g.a();
            }
            if (customBottomDialogFragment.getDialog() != null) {
                CustomBottomDialogFragment customBottomDialogFragment2 = this.af;
                if (customBottomDialogFragment2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (customBottomDialogFragment2.getDialog().isShowing()) {
                    CustomBottomDialogFragment customBottomDialogFragment3 = this.af;
                    if (customBottomDialogFragment3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    customBottomDialogFragment3.dismiss();
                }
            }
        }
    }

    private final void N() {
        if (this.ag != null) {
            CustomBottomDialogFragment customBottomDialogFragment = this.ag;
            if (customBottomDialogFragment == null) {
                kotlin.jvm.internal.g.a();
            }
            if (customBottomDialogFragment.getDialog() != null) {
                CustomBottomDialogFragment customBottomDialogFragment2 = this.ag;
                if (customBottomDialogFragment2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (customBottomDialogFragment2.getDialog().isShowing()) {
                    CustomBottomDialogFragment customBottomDialogFragment3 = this.ag;
                    if (customBottomDialogFragment3 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    customBottomDialogFragment3.dismiss();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void O() {
        ImageView imageView = this.u;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        if (imageView.getAnimation() != null) {
            return;
        }
        ImageView imageView2 = this.u;
        if (imageView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView2.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, 1, 0.5f, 1, 0.5f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(200L);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new r());
        ImageView imageView3 = this.u;
        if (imageView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView3.startAnimation(animationSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void P() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(200L);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new q());
        ImageView imageView = this.u;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.startAnimation(animationSet);
    }

    private final void Q() {
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        CameraMainController.CameraMode g2 = cameraMainController.g();
        if (g2 == null) {
            return;
        }
        switch (com.xiaomi.xy.sportscamera.camera.activity.a.n[g2.ordinal()]) {
            case 1:
                CameraMainController cameraMainController2 = this.ad;
                if (cameraMainController2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                Constant.RecordMode p2 = cameraMainController2.p();
                if (p2 != null) {
                    switch (com.xiaomi.xy.sportscamera.camera.activity.a.l[p2.ordinal()]) {
                        case 1:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_video");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_video");
                            StatisticHelper.j("Normal");
                            return;
                        case 2:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_video_time_elapse");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_video_time_elapse");
                            StatisticHelper.j("Timelapse");
                            return;
                        case 3:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_video_photo");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_video_photo");
                            StatisticHelper.j("Photo");
                            return;
                        case 4:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_loop_record");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_loop_record");
                            StatisticHelper.j("Loop");
                            return;
                        case 5:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_slow_record");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_slow_record");
                            StatisticHelper.j("Slow");
                            return;
                        default:
                            return;
                    }
                }
                return;
            case 2:
                CameraMainController cameraMainController3 = this.ad;
                if (cameraMainController3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                Constant.CaptureMode o2 = cameraMainController3.o();
                if (o2 != null) {
                    switch (com.xiaomi.xy.sportscamera.camera.activity.a.m[o2.ordinal()]) {
                        case 1:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_photo");
                            StatisticHelper.i("Normal");
                            return;
                        case 2:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo_burst");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_photo_burst");
                            StatisticHelper.i("Burst");
                            return;
                        case 3:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo_time_elapse");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_photo_time_elapse");
                            StatisticHelper.i("Timelapse");
                            return;
                        case 4:
                            UploadStatsManager.a("camera_operation", "camera_operation_start", "camera_operation_photo_timer");
                            UploadStatsManager.a("camera_operation_start", "camera_operation_photo_timer");
                            StatisticHelper.i("Timer");
                            return;
                        default:
                            return;
                    }
                }
                return;
            default:
                return;
        }
    }

    private final void R() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(500L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new s());
        RelativeLayout relativeLayout = this.L;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.startAnimation(translateAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500L);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new t());
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.startAnimation(alphaAnimation);
        ImageView imageView2 = this.N;
        if (imageView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView2.startAnimation(alphaAnimation);
        ImageView imageView3 = this.R;
        if (imageView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView3.startAnimation(alphaAnimation);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(500L);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new u());
        ImageView imageView4 = this.M;
        if (imageView4 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView4.startAnimation(scaleAnimation);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
        translateAnimation2.setDuration(500L);
        translateAnimation2.setFillAfter(true);
        translateAnimation2.setAnimationListener(new v());
        LinearLayout linearLayout = this.P;
        if (linearLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        linearLayout.startAnimation(translateAnimation2);
        ImageView imageView5 = this.T;
        if (imageView5 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView5.setVisibility(0);
        ImageView imageView6 = this.O;
        if (imageView6 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView6.setVisibility(0);
        ImageView imageView7 = this.S;
        if (imageView7 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView7.setVisibility(0);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration(500L);
        alphaAnimation2.setFillAfter(true);
        alphaAnimation2.setAnimationListener(new w());
        ImageView imageView8 = this.T;
        if (imageView8 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView8.startAnimation(alphaAnimation2);
        ImageView imageView9 = this.O;
        if (imageView9 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView9.startAnimation(alphaAnimation2);
        ImageView imageView10 = this.S;
        if (imageView10 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView10.startAnimation(alphaAnimation2);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration(500L);
        scaleAnimation2.setFillAfter(true);
        scaleAnimation2.setAnimationListener(new x());
        ImageView imageView11 = this.T;
        if (imageView11 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView11.startAnimation(scaleAnimation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void S() {
        if (this.aR && this.aS && this.aT) {
            com.yiaction.common.util.g.a("debug_command", "-------------------------------------------- setSwitchAnimationFin", new Object[0]);
            RelativeLayout relativeLayout = this.L;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.clearAnimation();
            ImageView imageView = this.M;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.clearAnimation();
            ImageView imageView2 = this.N;
            if (imageView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView2.clearAnimation();
            ImageView imageView3 = this.R;
            if (imageView3 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView3.clearAnimation();
            LinearLayout linearLayout = this.P;
            if (linearLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            linearLayout.clearAnimation();
            ImageView imageView4 = this.T;
            if (imageView4 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView4.clearAnimation();
            ImageView imageView5 = this.O;
            if (imageView5 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView5.clearAnimation();
            ImageView imageView6 = this.S;
            if (imageView6 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView6.clearAnimation();
            ImageView imageView7 = this.T;
            if (imageView7 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView7.setVisibility(4);
            ImageView imageView8 = this.O;
            if (imageView8 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView8.setVisibility(4);
            ImageView imageView9 = this.S;
            if (imageView9 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView9.setVisibility(4);
            this.aR = false;
            this.aS = false;
            this.aT = false;
        }
    }

    private final boolean T() {
        try {
            Object systemService = getSystemService("activity");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
            long j2 = memoryInfo.availMem;
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "----------------- mi.availMem = " + j2, new Object[0]);
            return j2 > ((long) 100000000);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void U() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.aj, this.am);
        layoutParams.gravity = 17;
        RelativeLayout relativeLayout = this.q;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(com.rp.rptool.a.b bVar) {
        String str;
        String str2;
        com.rp.rptool.util.c.b(0, this.c, "respSearchDevice()");
        if (bVar.a() <= -1) {
            com.rp.rptool.util.c.b(0, this.c, "respSearchDevice() overtime!");
            return;
        }
        if (bVar.d() / b.c.a() == 0) {
            com.rp.rptool.util.c.a(3, this.c, "respSearchDevice() returnMsg to num == 0!");
            return;
        }
        b.c cVar = new b.c(bVar.c(), 0);
        com.rp.rptool.util.c.a(0, this.c, "respSearchDevice() info = " + cVar);
        String str3 = (String) null;
        try {
            str = new String(cVar.f3704a, kotlin.text.d.f7120a).trim();
        } catch (Exception e2) {
            e2.printStackTrace();
            com.rp.rptool.util.c.a(3, this.c, "respSearchDevice() uid 2 String error!");
            str = str3;
        }
        if (TextUtils.isEmpty(str)) {
            com.rp.rptool.util.c.a(3, this.c, "respSearchDevice() uid null error!");
            return;
        }
        String str4 = (String) null;
        try {
            str2 = new String(cVar.b, kotlin.text.d.f7120a).trim();
        } catch (Exception e3) {
            e3.printStackTrace();
            com.rp.rptool.util.c.a(0, this.c, "respSearchDevice() ip 2 String error!");
            str2 = str4;
        }
        if (TextUtils.isEmpty(str2)) {
            com.rp.rptool.util.c.a(3, this.c, "respSearchDevice() ip null error!");
            return;
        }
        com.rp.rptool.a.c cVar2 = new com.rp.rptool.a.c(str, "12345", -1, str2, -1);
        com.rp.rptool.util.d.a().a(cVar2);
        String a2 = cVar2.a();
        kotlin.jvm.internal.g.a((Object) a2, "device.uid");
        String b2 = cVar2.b();
        kotlin.jvm.internal.g.a((Object) b2, "device.password");
        a(a2, b2);
    }

    private final void a(String str, String str2) {
        com.rp.rptool.util.c.b(0, this.c, "sendConnectDevice()");
        com.rp.rptool.util.d.a().a(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(boolean z2, String str) {
        if (z2) {
            RelativeLayout relativeLayout = this.m;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setVisibility(0);
            TextView textView = this.n;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setText(str);
            return;
        }
        RelativeLayout relativeLayout2 = this.m;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setVisibility(8);
        TextView textView2 = this.n;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(boolean z2, boolean z3, boolean z4, boolean z5) {
        RelativeLayout relativeLayout = this.Z;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout = this.aa;
        if (linearLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        linearLayout.setVisibility(z3 ? 0 : 4);
        LinearLayout linearLayout2 = this.ab;
        if (linearLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        linearLayout2.setVisibility(z4 ? 0 : 4);
        LinearLayout linearLayout3 = this.ac;
        if (linearLayout3 == null) {
            kotlin.jvm.internal.g.a();
        }
        linearLayout3.setVisibility(z5 ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b(com.rp.rptool.a.b bVar) {
        com.rp.rptool.util.c.a(0, this.c, "respConnectDevSuccess() rtnMsg = " + bVar);
        com.rp.rptool.util.d.a().b().a(bVar.a());
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------ rtnMsg.sid = " + bVar.a(), new Object[0]);
        com.rp.rptool.util.e.a("YI", com.rp.rptool.util.d.a().b().a());
        com.rp.rptool.util.d.a().a(41022, 1);
        this.aN.sendEmptyMessage(this.aL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(com.rp.rptool.a.b bVar) {
        if (bVar != null) {
            com.rp.rptool.util.c.a(0, this.c, "respConnectDevFail() rtnMsg = " + bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008a, code lost:
    
        if (r1.A() != false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(boolean r3, boolean r4, boolean r5) {
        /*
            r2 = this;
            android.widget.ImageView r0 = r2.I
            if (r0 != 0) goto L7
            kotlin.jvm.internal.g.a()
        L7:
            r0.setEnabled(r3)
            android.widget.RelativeLayout r0 = r2.L
            if (r0 != 0) goto L11
            kotlin.jvm.internal.g.a()
        L11:
            r0.setEnabled(r4)
            android.widget.RelativeLayout r0 = r2.G
            if (r0 != 0) goto L1b
            kotlin.jvm.internal.g.a()
        L1b:
            r0.setEnabled(r5)
            android.widget.ImageView r0 = r2.E
            if (r0 != 0) goto L25
            kotlin.jvm.internal.g.a()
        L25:
            r0.setEnabled(r5)
            android.widget.RelativeLayout r0 = r2.Q
            if (r0 != 0) goto L2f
            kotlin.jvm.internal.g.a()
        L2f:
            r0.setEnabled(r5)
            android.widget.ImageView r0 = r2.R
            if (r0 != 0) goto L39
            kotlin.jvm.internal.g.a()
        L39:
            r0.setEnabled(r5)
            com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker r0 = r2.z
            if (r0 != 0) goto L43
            kotlin.jvm.internal.g.a()
        L43:
            r0.setEnabled(r5)
            android.widget.RelativeLayout r1 = r2.q
            if (r1 != 0) goto L4d
            kotlin.jvm.internal.g.a()
        L4d:
            if (r5 == 0) goto L90
            com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity$c r0 = r2.aH
        L51:
            android.view.View$OnTouchListener r0 = (android.view.View.OnTouchListener) r0
            r1.setOnTouchListener(r0)
            android.widget.ImageView r0 = r2.i
            if (r0 != 0) goto L5d
            kotlin.jvm.internal.g.a()
        L5d:
            r0.setEnabled(r5)
            android.widget.ImageView r0 = r2.j
            if (r0 != 0) goto L67
            kotlin.jvm.internal.g.a()
        L67:
            r0.setEnabled(r5)
            android.widget.ImageView r0 = r2.l
            if (r0 != 0) goto L71
            kotlin.jvm.internal.g.a()
        L71:
            r0.setEnabled(r5)
            android.widget.ImageView r0 = r2.k
            if (r0 != 0) goto L7b
            kotlin.jvm.internal.g.a()
        L7b:
            com.xiaoyi.camera.controller.CameraMainController r1 = r2.ad
            if (r1 == 0) goto L92
            com.xiaoyi.camera.controller.CameraMainController r1 = r2.ad
            if (r1 != 0) goto L86
            kotlin.jvm.internal.g.a()
        L86:
            boolean r1 = r1.A()
            if (r1 == 0) goto L92
        L8c:
            r0.setEnabled(r5)
            return
        L90:
            r0 = 0
            goto L51
        L92:
            r5 = 0
            goto L8c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.xy.sportscamera.camera.activity.QZCameraActivity.d(boolean, boolean, boolean):void");
    }

    private final String f(int i2) {
        switch (i2) {
            case -1005:
                return getString(R.string.camera_start_fw_update);
            case IMediaPlayer.MEDIA_ERROR_IO /* -1004 */:
                return getString(R.string.camera_start_usb_storage_mode);
            case -1003:
                return getString(R.string.sd_hc);
            case -1002:
                return getString(R.string.no_more_space);
            case -1001:
                return getString(R.string.sd_card_remove);
            case NotificationManagerCompat.IMPORTANCE_UNSPECIFIED /* -1000 */:
                return getString(R.string.no_sd_card);
            case -31:
                return getString(R.string.low_level_sd_card);
            case -28:
                return "sdCardNeedFormat";
            case -27:
                String string = getString(R.string.no_sd_card);
                s();
                return string;
            case -25:
                return getString(R.string.system_busy);
            case -21:
                return getString(R.string.system_busy);
            case -20:
                return getString(R.string.piv_not_allowed);
            case -17:
                return getString(R.string.no_more_space);
            default:
                return getString(R.string.operation_failed);
        }
    }

    private final void f(String str) {
        if (!(!kotlin.jvm.internal.g.a((Object) "J22", (Object) com.xiaoyi.camera.g.a().a("model")))) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            com.yiaction.common.imageloader.i.a(this, com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a(str), this.E, R.drawable.pic_default);
            return;
        }
        com.yiaction.common.util.i.a().a("video_photo_path", str);
        this.aF = str;
        PhotoFileItem photoFileItem = new PhotoFileItem();
        photoFileItem.setType("thumb");
        photoFileItem.setPath(str);
        photoFileItem.setHttpThumbUrl(kotlin.jvm.internal.g.a(str != null ? kotlin.text.i.a(str, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null) : null, (Object) "?type=thumb"));
        photoFileItem.setHttpPath(str != null ? kotlin.text.i.a(str, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null) : null);
        ImageView imageView = this.E;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setTag(R.integer.camera_imageview_key, str);
        com.xiaoyi.camera.b.a.a(this.ad);
        com.xiaomi.xy.sportscamera.camera.c.a().a("thumb");
        com.yiaction.common.imageloader.i.a(this, photoFileItem.getHttpThumbUrl(), this.E, R.drawable.pic_default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void g(String str) {
        String str2;
        if (!(!kotlin.jvm.internal.g.a((Object) "J22", (Object) com.xiaoyi.camera.g.a().a("model")))) {
            if (TextUtils.isEmpty(str)) {
                w();
                return;
            }
            String optString = new JSONObject(str).optString("param");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            com.yiaction.common.imageloader.i.a(this, com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.a(optString), this.E, R.drawable.pic_default);
            return;
        }
        com.yiaction.common.util.i.a().a("video_photo_path", str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        VideoFileItem videoFileItem = new VideoFileItem();
        if (kotlin.jvm.internal.g.a((Object) "Z16", (Object) com.xiaoyi.camera.g.a().a("model")) || kotlin.jvm.internal.g.a((Object) "Z18", (Object) com.xiaoyi.camera.g.a().a("model")) || kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model")) || kotlin.jvm.internal.g.a((Object) "J22", (Object) com.xiaoyi.camera.g.a().a("model"))) {
            this.aF = str;
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.optString("param");
                String optString2 = jSONObject.optString("sub");
                int b2 = kotlin.text.i.b((CharSequence) optString2, "/", 0, false, 6, (Object) null) + 1;
                int length = optString2.length();
                if (optString2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = optString2.substring(b2, length);
                kotlin.jvm.internal.g.a((Object) substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                videoFileItem.setName(substring);
                videoFileItem.setPath(optString2);
                videoFileItem.setSize(jSONObject.optLong("ssize"));
                videoFileItem.setHttpPath(kotlin.text.i.a(optString2, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null));
                if (com.xiaoyi.camera.d.a.a()) {
                    str2 = kotlin.text.i.a(optString2, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null);
                    if (kotlin.text.i.b(str2, "_thm.mp4", false, 2, (Object) null) || kotlin.text.i.b(str2, "_thm.MP4", false, 2, (Object) null)) {
                        str2 = kotlin.text.i.a(kotlin.text.i.a(str2, "_thm.mp4", ".THM", false, 4, (Object) null), "_thm.MP4", ".THM", false, 4, (Object) null);
                    } else if (kotlin.text.i.b(str2, ".sec", false, 2, (Object) null) || kotlin.text.i.b(str2, ".SEC", false, 2, (Object) null)) {
                        str2 = kotlin.text.i.a(kotlin.text.i.a(str2, ".sec", ".THM", false, 4, (Object) null), ".SEC", ".THM", false, 4, (Object) null);
                    }
                } else {
                    str2 = kotlin.text.i.a(optString2, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null) + "?type=thumb";
                }
                videoFileItem.setHttpThumbUrl(str2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else if (kotlin.jvm.internal.g.a((Object) "Z13", (Object) com.xiaoyi.camera.g.a().a("model"))) {
            videoFileItem.setType("idr");
            int b3 = kotlin.text.i.b((CharSequence) str, "/", 0, false, 6, (Object) null) + 1;
            int length2 = str.length();
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring2 = str.substring(b3, length2);
            kotlin.jvm.internal.g.a((Object) substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            videoFileItem.setName(substring2);
            videoFileItem.setPath(str);
            videoFileItem.setHttpPath(kotlin.text.i.a(str, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null));
            videoFileItem.setHttpThumbUrl(com.xiaoyi.camera.d.a.a() ? kotlin.text.i.a(kotlin.text.i.a(kotlin.text.i.a(str, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null), ".mp4", ".THM", false, 4, (Object) null), ".MP4", ".THM", false, 4, (Object) null) : kotlin.text.i.a(str, "/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/", false, 4, (Object) null) + "?type=thumb");
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            if (kotlin.jvm.internal.g.a(cameraMainController.p(), Constant.RecordMode.TIMELAPES) && com.xiaoyi.camera.d.a.c()) {
                videoFileItem.setVideoAspectRatio(com.xiaoyi.camera.g.a().a("timelapse_video_resolution"));
            } else {
                videoFileItem.setVideoAspectRatio(com.xiaoyi.camera.g.a().a("video_resolution"));
            }
            videoFileItem.setDuration(this.ar);
        }
        ImageView imageView = this.E;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setTag(R.integer.camera_imageview_key, videoFileItem.getPath());
        com.xiaoyi.camera.b.a.a(this.ad);
        com.xiaomi.xy.sportscamera.camera.c.a().a("thumb");
        com.xiaomi.xy.sportscamera.camera.c.a().a("idr", videoFileItem, this.E, new aq());
    }

    public final void a(Menu menu) {
        kotlin.jvm.internal.g.b(menu, "menu");
        menu.clear();
        MenuItem add = menu.add(0, 0, 0, "");
        add.setShowAsAction(2);
        if (this.as == null) {
            this.as = LayoutInflater.from(this).inflate(R.layout.layout_phone_battery_time, (ViewGroup) null);
            View view = this.as;
            if (view == null) {
                kotlin.jvm.internal.g.a();
            }
            View findViewById = view.findViewById(R.id.iv_camera_battery);
            if (findViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            this.at = (ImageView) findViewById;
            View view2 = this.as;
            if (view2 == null) {
                kotlin.jvm.internal.g.a();
            }
            View findViewById2 = view2.findViewById(R.id.cl_camera_battery);
            if (findViewById2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.CustomBatteryLoading");
            }
            this.au = (CustomBatteryLoading) findViewById2;
            View view3 = this.as;
            if (view3 == null) {
                kotlin.jvm.internal.g.a();
            }
            View findViewById3 = view3.findViewById(R.id.tv_phone_time);
            if (findViewById3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            this.av = (TextView) findViewById3;
        }
        if (this.aw) {
            ImageView imageView = this.at;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setVisibility(8);
            CustomBatteryLoading customBatteryLoading = this.au;
            if (customBatteryLoading == null) {
                kotlin.jvm.internal.g.a();
            }
            customBatteryLoading.setVisibility(0);
            CustomBatteryLoading customBatteryLoading2 = this.au;
            if (customBatteryLoading2 == null) {
                kotlin.jvm.internal.g.a();
            }
            customBatteryLoading2.a(this, this.ax);
            CustomBatteryLoading customBatteryLoading3 = this.au;
            if (customBatteryLoading3 == null) {
                kotlin.jvm.internal.g.a();
            }
            customBatteryLoading3.setProgress(this.ay);
        } else {
            ImageView imageView2 = this.at;
            if (imageView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView2.setVisibility(0);
            CustomBatteryLoading customBatteryLoading4 = this.au;
            if (customBatteryLoading4 == null) {
                kotlin.jvm.internal.g.a();
            }
            customBatteryLoading4.setVisibility(8);
            if (this.ax) {
                ImageView imageView3 = this.at;
                if (imageView3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView3.setImageResource(R.drawable.information_battery_nothing);
            } else {
                ImageView imageView4 = this.at;
                if (imageView4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView4.setImageResource(R.drawable.information_battery_none);
            }
        }
        TextView textView = this.av;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText(String.valueOf(this.ay) + "%");
        add.setActionView(this.as);
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void a(HorizontalPicker horizontalPicker, int i2) {
        kotlin.jvm.internal.g.b(horizontalPicker, "picker");
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "--------------- index = " + i2, new Object[0]);
        if (this.az == i2 || this.ad == null) {
            return;
        }
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        if (cameraMainController.g() == null) {
            return;
        }
        this.az = i2;
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        CameraMainController.CameraMode g2 = cameraMainController2.g();
        if (g2 != null) {
            switch (com.xiaomi.xy.sportscamera.camera.activity.a.d[g2.ordinal()]) {
                case 1:
                    List<String> list = this.d;
                    if (list == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    String str = list.get(i2);
                    if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.NORMAL.toString())) {
                        a(Constant.CaptureMode.NORMAL);
                        ImageView imageView = this.u;
                        if (imageView == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView.setImageResource(R.drawable.show_photo_normal);
                    } else if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.TIMER.toString())) {
                        a(Constant.CaptureMode.TIMER);
                        ImageView imageView2 = this.u;
                        if (imageView2 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView2.setImageResource(R.drawable.show_photo_timer);
                    } else if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.BURST.toString())) {
                        a(Constant.CaptureMode.BURST);
                        ImageView imageView3 = this.u;
                        if (imageView3 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView3.setImageResource(R.drawable.show_photo_burst);
                    } else if (kotlin.jvm.internal.g.a((Object) str, (Object) Constant.CaptureMode.TIMELAPES.toString())) {
                        a(Constant.CaptureMode.TIMELAPES);
                        ImageView imageView4 = this.u;
                        if (imageView4 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView4.setImageResource(R.drawable.show_photo_timelapse);
                    }
                    if (kotlin.jvm.internal.g.a((Object) "Z13", (Object) com.xiaoyi.camera.g.a().a("model"))) {
                        v();
                        break;
                    }
                    break;
                case 2:
                    List<String> list2 = this.e;
                    if (list2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    String str2 = list2.get(i2);
                    if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.NORMAL.toString())) {
                        a(Constant.RecordMode.NORMAL);
                        ImageView imageView5 = this.u;
                        if (imageView5 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView5.setImageResource(R.drawable.show_video_normal);
                    } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.TIMELAPES.toString())) {
                        a(Constant.RecordMode.TIMELAPES);
                        ImageView imageView6 = this.u;
                        if (imageView6 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView6.setImageResource(R.drawable.show_video_timelapse);
                    } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.SLOW.toString())) {
                        a(Constant.RecordMode.SLOW);
                        ImageView imageView7 = this.u;
                        if (imageView7 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView7.setImageResource(R.drawable.show_video_slow);
                    } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.LOOP.toString())) {
                        a(Constant.RecordMode.LOOP);
                        ImageView imageView8 = this.u;
                        if (imageView8 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView8.setImageResource(R.drawable.show_video_loop);
                    } else if (kotlin.jvm.internal.g.a((Object) str2, (Object) Constant.RecordMode.PHOTO.toString())) {
                        a(Constant.RecordMode.PHOTO);
                        ImageView imageView9 = this.u;
                        if (imageView9 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        imageView9.setImageResource(R.drawable.show_video_photo);
                    }
                    if (kotlin.jvm.internal.g.a((Object) "Z13", (Object) com.xiaoyi.camera.g.a().a("model"))) {
                        t();
                        break;
                    }
                    break;
            }
        }
        d(false, false, false);
        if (!kotlin.jvm.internal.g.a((Object) "J11", (Object) com.xiaoyi.camera.g.a().a("model"))) {
            O();
        }
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.a.InterfaceC0204a, com.xiaomi.xy.sportscamera.camera.widget.b.a
    public void a(CameraMainController.CameraMode cameraMode, Constant.RecordMode recordMode, Constant.CaptureMode captureMode, String str, int i2) {
        kotlin.jvm.internal.g.b(cameraMode, "mode");
        if (this.ad != null) {
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            if (cameraMainController.s()) {
                return;
            }
            CameraMainController cameraMainController2 = this.ad;
            if (cameraMainController2 == null) {
                kotlin.jvm.internal.g.a();
            }
            if (cameraMainController2.r()) {
                return;
            }
            if (i2 != 0 && str != null) {
                d(true);
            }
            switch (com.xiaomi.xy.sportscamera.camera.activity.a.c[cameraMode.ordinal()]) {
                case 1:
                    if (recordMode != null) {
                        switch (com.xiaomi.xy.sportscamera.camera.activity.a.f4491a[recordMode.ordinal()]) {
                            case 1:
                                if (i2 == 0) {
                                    this.aQ = str;
                                    int[] iArr = new int[2];
                                    RelativeLayout relativeLayout = this.G;
                                    if (relativeLayout == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    relativeLayout.getLocationInWindow(iArr);
                                    RelativeLayout relativeLayout2 = this.G;
                                    if (relativeLayout2 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    int width = relativeLayout2.getWidth();
                                    String recordMode2 = Constant.RecordMode.TIMELAPES.toString();
                                    CameraMainController cameraMainController3 = this.ad;
                                    if (cameraMainController3 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    a(iArr, width, kotlin.jvm.internal.g.a((Object) recordMode2, (Object) cameraMainController3.p().toString()) ? 1 : -1);
                                    return;
                                }
                                if (i2 == 1) {
                                    if (this.aQ != null) {
                                        CameraMainController cameraMainController4 = this.ad;
                                        if (cameraMainController4 == null) {
                                            kotlin.jvm.internal.g.a();
                                        }
                                        cameraMainController4.a(this.aQ, 0);
                                        StatisticHelper.b("Camera_Quick_Setting_Video_Timelapse_Int", this.aQ);
                                    }
                                    if (str != null) {
                                        CameraMainController cameraMainController5 = this.ad;
                                        if (cameraMainController5 == null) {
                                            kotlin.jvm.internal.g.a();
                                        }
                                        cameraMainController5.a(str, 1);
                                        StatisticHelper.b("Camera_Quick_Setting_Video_Timelapse_Len", str);
                                    }
                                    this.aQ = (String) null;
                                    return;
                                }
                                return;
                            case 2:
                                if (str != null) {
                                    CameraMainController cameraMainController6 = this.ad;
                                    if (cameraMainController6 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    cameraMainController6.a(str, 0);
                                    StatisticHelper.b("Camera_Quick_Setting_Video_Loop", str);
                                    return;
                                }
                                return;
                            case 3:
                                if (str != null) {
                                    CameraMainController cameraMainController7 = this.ad;
                                    if (cameraMainController7 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    cameraMainController7.a(str, 0);
                                    StatisticHelper.b("Camera_Quick_Setting_Video_Slow", str);
                                    return;
                                }
                                return;
                            case 4:
                                if (str != null) {
                                    CameraMainController cameraMainController8 = this.ad;
                                    if (cameraMainController8 == null) {
                                        kotlin.jvm.internal.g.a();
                                    }
                                    cameraMainController8.a(str, 0);
                                    StatisticHelper.b("Camera_Quick_Setting_Video_Photo", str);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                    return;
                case 2:
                    if (str != null) {
                        if (captureMode != null) {
                            switch (com.xiaomi.xy.sportscamera.camera.activity.a.b[captureMode.ordinal()]) {
                                case 1:
                                    StatisticHelper.b("Camera_Quick_Setting_Picture_Timer", str);
                                    break;
                                case 2:
                                    StatisticHelper.b("Camera_Quick_Setting_Picture_Timelapse", str);
                                    break;
                                case 3:
                                    StatisticHelper.b("Camera_Quick_Setting_Picture_Burst", str);
                                    break;
                            }
                        }
                        CameraMainController cameraMainController9 = this.ad;
                        if (cameraMainController9 == null) {
                            kotlin.jvm.internal.g.a();
                        }
                        cameraMainController9.a(str);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public final void a(Constant.CaptureMode captureMode) {
        if (this.ad != null) {
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            cameraMainController.a(captureMode);
        }
        TextView textView = this.y;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText(cameraMainController2.m());
    }

    public final void a(Constant.RecordMode recordMode) {
        kotlin.jvm.internal.g.b(recordMode, "mode");
        if (this.ad != null) {
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            cameraMainController.a(recordMode);
        }
        TextView textView = this.y;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText(cameraMainController2.m());
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, int i2, String str) {
        kotlin.jvm.internal.g.b(str, "recordTime");
        TextView textView = this.w;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        if (textView.getVisibility() == 0) {
            TextView textView2 = this.w;
            if (textView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView2.setText(str);
            if (i2 == 0) {
                i2 = 1;
            }
            this.ar = i2;
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, int i2, boolean z3, int i3) {
        TextView textView = this.x;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        if (textView.getVisibility() != 0 || z3 || i3 == 0) {
            return;
        }
        this.aD = i2 * 100;
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, FileItem fileItem, ImageView imageView) {
        runOnUiThread(new al(z2, imageView, fileItem));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, String str, int i2) {
        if (z2) {
            TextView textView = this.V;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            if (!TextUtils.isEmpty(textView.getText())) {
                TextView textView2 = this.V;
                if (textView2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView2.setText(str);
            }
            this.ar = i2;
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, boolean z3) {
        L();
        N();
        if (z2) {
            c(z3);
            d(false, true, false);
            TextView textView = this.s;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setTypeface(this.ah);
            TextView textView2 = this.s;
            if (textView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView2.setTextSize(0, getResources().getDimension(R.dimen.dialog_title_textsize));
            TextView textView3 = this.s;
            if (textView3 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView3.setText(R.string.in_timelapes_capture);
            TextView textView4 = this.s;
            if (textView4 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView4.setVisibility(0);
            ImageView imageView = this.M;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setImageResource(R.drawable.bg_camera_shutter_work);
        } else {
            d(false, true, true);
            e((String) null);
        }
        if (z3) {
            return;
        }
        com.yiaction.common.util.g.a("debug_preview", "previewEnable : " + getString(R.string.preview_off), new Object[0]);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, boolean z3, String str) {
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText("");
        TextView textView2 = this.s;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setTextColor(getResources().getColor(R.color.white));
        TextView textView3 = this.s;
        if (textView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView3.setVisibility(8);
        d(false, true, true);
        if (z2) {
            f(str);
        }
        c(true);
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, boolean z3, boolean z4) {
        L();
        N();
        if (z2) {
            c(z3);
            d(false, true, false);
            ImageView imageView = this.M;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setImageResource(R.drawable.bg_camera_shutter_work);
            if (!z4) {
                TextView textView = this.s;
                if (textView == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView.setTextSize(0, getResources().getDimension(R.dimen.remind_text_size));
                TextView textView2 = this.s;
                if (textView2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView2.setTextColor(getResources().getColor(R.color.primary_green));
                TextView textView3 = this.s;
                if (textView3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView3.setTypeface(this.ai);
                TextView textView4 = this.s;
                if (textView4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                textView4.setVisibility(0);
            }
        } else {
            e((String) null);
            d(false, true, true);
        }
        if (z3) {
            return;
        }
        com.yiaction.common.util.g.a("debug_preview", "previewEnable : " + getString(R.string.preview_off), new Object[0]);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, boolean z3, boolean z4, int i2) {
        L();
        N();
        if (!z2) {
            d(false, true, true);
            c(i2);
            return;
        }
        d(false, true, false);
        RelativeLayout relativeLayout = this.v;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setVisibility(8);
        RelativeLayout relativeLayout2 = this.U;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setVisibility(0);
        TextView textView2 = this.V;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setText("00:00");
        c(z3);
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, boolean z3, boolean z4, String str) {
        kotlin.jvm.internal.g.b(str, "param");
        d(false, true, true);
        c(true);
        if (z2) {
            g(str);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
        RelativeLayout relativeLayout = this.U;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
        if (z4) {
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------ newProgress = " + this.aD, new Object[0]);
            StringBuilder append = new StringBuilder().append("------------------ MAX = ");
            TimelapsSeekBar timelapsSeekBar = this.Y;
            if (timelapsSeekBar == null) {
                kotlin.jvm.internal.g.a();
            }
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, append.append(timelapsSeekBar.getMax()).toString(), new Object[0]);
            int i2 = this.aD;
            if (this.Y == null) {
                kotlin.jvm.internal.g.a();
            }
            if (i2 < r1.getMax() - 100) {
                this.aN.sendEmptyMessage(this.aJ);
            } else {
                this.aN.sendEmptyMessage(this.aK);
                this.aN.sendEmptyMessageDelayed(this.aJ, 100L);
            }
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, boolean z3, boolean z4, boolean z5, int i2) {
        L();
        N();
        if (!z2) {
            d(false, true, true);
            c(i2);
            return;
        }
        d(true, true, false);
        RelativeLayout relativeLayout = this.G;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
        if (z5) {
            RelativeLayout relativeLayout2 = this.H;
            if (relativeLayout2 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout2.setVisibility(0);
            if (!com.yiaction.common.util.i.a().b("already_tips_start_piv", false)) {
                com.yiaction.common.util.i.a().a("already_tips_start_piv", true);
                a(true, false, false, true);
            }
        }
        RelativeLayout relativeLayout3 = this.v;
        if (relativeLayout3 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout3.setVisibility(8);
        RelativeLayout relativeLayout4 = this.U;
        if (relativeLayout4 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout4.setVisibility(0);
        TextView textView = this.V;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText("00:00");
        c(z3);
        if (!z4) {
            TextView textView2 = this.s;
            if (textView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView2.setTypeface(this.ah);
            TextView textView3 = this.s;
            if (textView3 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView3.setTextSize(0, getResources().getDimension(R.dimen.dialog_title_textsize));
            TextView textView4 = this.s;
            if (textView4 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView4.setVisibility(0);
            TextView textView5 = this.s;
            if (textView5 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView5.setText(R.string.no_preview);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void a(boolean z2, boolean z3, boolean z4, boolean z5, int i2, int i3) {
        L();
        N();
        if (!z2) {
            d(false, true, true);
            c(i3);
            return;
        }
        d(false, true, false);
        if (!z4) {
            c(z3);
            return;
        }
        c(z3);
        RelativeLayout relativeLayout = this.v;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(0);
        RelativeLayout relativeLayout2 = this.U;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setVisibility(8);
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setVisibility(8);
        if (z5) {
            TimelapsSeekBar timelapsSeekBar = this.Y;
            if (timelapsSeekBar == null) {
                kotlin.jvm.internal.g.a();
            }
            timelapsSeekBar.setVisibility(8);
        } else {
            TimelapsSeekBar timelapsSeekBar2 = this.Y;
            if (timelapsSeekBar2 == null) {
                kotlin.jvm.internal.g.a();
            }
            timelapsSeekBar2.setVisibility(0);
            TimelapsSeekBar timelapsSeekBar3 = this.Y;
            if (timelapsSeekBar3 == null) {
                kotlin.jvm.internal.g.a();
            }
            timelapsSeekBar3.setMax(i2 * 100);
            this.aC = new Timer();
            Timer timer = this.aC;
            if (timer == null) {
                kotlin.jvm.internal.g.a();
            }
            timer.schedule(new aj(), 0L, 100L);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    public final void a(int[] iArr, int i2, int i3) {
        int i4;
        kotlin.jvm.internal.g.b(iArr, "point");
        this.ae = new com.xiaomi.xy.sportscamera.camera.widget.a(this, R.style.CustomDialog);
        com.xiaomi.xy.sportscamera.camera.widget.a aVar = this.ae;
        if (aVar == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar.a(this);
        com.xiaomi.xy.sportscamera.camera.widget.a aVar2 = this.ae;
        if (aVar2 == null) {
            kotlin.jvm.internal.g.a();
        }
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        CameraMainController.CameraMode n2 = cameraMainController.n();
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        Constant.RecordMode p2 = cameraMainController2.p();
        CameraMainController cameraMainController3 = this.ad;
        if (cameraMainController3 == null) {
            kotlin.jvm.internal.g.a();
        }
        int a2 = aVar2.a(n2, p2, cameraMainController3.o(), i3);
        if (a2 > 0) {
            i4 = ((a2 + 1) * (this.al / 8)) + (i2 / 4);
            if (i4 > this.ak / 2) {
                i4 = ((int) ((((this.ak / 2) / (this.al / 8)) - 0.5d) * (this.al / 8))) + (i2 / 4);
            }
        } else {
            i4 = 0;
        }
        com.xiaomi.xy.sportscamera.camera.widget.a aVar3 = this.ae;
        if (aVar3 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar3.a(i2, i4, this.al / 8, false);
        com.xiaomi.xy.sportscamera.camera.widget.a aVar4 = this.ae;
        if (aVar4 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar4.setContentView(R.layout.camera_setting_dialog);
        com.xiaomi.xy.sportscamera.camera.widget.a aVar5 = this.ae;
        if (aVar5 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar5.setCanceledOnTouchOutside(true);
        com.xiaomi.xy.sportscamera.camera.widget.a aVar6 = this.ae;
        if (aVar6 == null) {
            kotlin.jvm.internal.g.a();
        }
        Window window = aVar6.getWindow();
        if (window == null) {
            kotlin.jvm.internal.g.a();
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = i2;
        attributes.height = i4;
        attributes.gravity = 85;
        attributes.y = this.ak - iArr[1];
        attributes.x = ((this.aj - i2) - ((i2 - i2) / 2)) - iArr[0];
        com.xiaomi.xy.sportscamera.camera.widget.a aVar7 = this.ae;
        if (aVar7 == null) {
            kotlin.jvm.internal.g.a();
        }
        Window window2 = aVar7.getWindow();
        if (window2 == null) {
            kotlin.jvm.internal.g.a();
        }
        window2.setAttributes(attributes);
        com.xiaomi.xy.sportscamera.camera.widget.a aVar8 = this.ae;
        if (aVar8 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar8.b();
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z2) {
        if (z2) {
            this.aN.post(new af());
            if (this.U != null) {
                RelativeLayout relativeLayout = this.U;
                if (relativeLayout == null) {
                    kotlin.jvm.internal.g.a();
                }
                relativeLayout.setVisibility(8);
            }
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z2, int i2, String str) {
        kotlin.jvm.internal.g.b(str, "recordTime");
        TextView textView = this.x;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        if (textView.getVisibility() == 0) {
            TextView textView2 = this.x;
            if (textView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView2.setText(str);
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z2, boolean z3) {
        L();
        N();
        if (!z2) {
            e((String) null);
            d(false, true, true);
            return;
        }
        c(z3);
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setTypeface(this.ah);
        TextView textView2 = this.s;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setTextSize(0, getResources().getDimension(R.dimen.dialog_title_textsize));
        TextView textView3 = this.s;
        if (textView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView3.setText(R.string.in_burst_capture);
        TextView textView4 = this.s;
        if (textView4 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView4.setVisibility(0);
        d(false, false, false);
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_work);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z2, boolean z3, String str) {
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText("");
        TextView textView2 = this.s;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setTextColor(getResources().getColor(R.color.white));
        TextView textView3 = this.s;
        if (textView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView3.setVisibility(8);
        d(false, true, true);
        c(true);
        if (z2) {
            f(str);
        }
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController.w();
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z2, boolean z3, boolean z4) {
        L();
        N();
        if (z2) {
            d(false, true, false);
            c(z3);
            TextView textView = this.s;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setTextSize(0, getResources().getDimension(R.dimen.remind_text_size));
            TextView textView2 = this.s;
            if (textView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView2.setTextColor(getResources().getColor(R.color.primary_green));
            TextView textView3 = this.s;
            if (textView3 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView3.setTypeface(this.ai);
            TextView textView4 = this.s;
            if (textView4 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView4.setVisibility(0);
            ImageView imageView = this.M;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setImageResource(R.drawable.bg_camera_shutter_work);
        } else {
            d(false, true, true);
            e((String) null);
        }
        if (z3) {
            return;
        }
        com.yiaction.common.util.g.a("debug_preview", "previewEnable : " + getString(R.string.preview_off), new Object[0]);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void b(boolean z2, boolean z3, boolean z4, int i2) {
        L();
        N();
        if (!z2) {
            d(false, true, true);
            c(i2);
            return;
        }
        d(false, true, false);
        RelativeLayout relativeLayout = this.v;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setVisibility(8);
        RelativeLayout relativeLayout2 = this.U;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setVisibility(0);
        TextView textView2 = this.V;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setText("00:00");
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_work);
        c(z3);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(int i2) {
        e(f(i2));
        if (-1004 == i2) {
            finish();
        }
    }

    public final void c(boolean z2) {
        com.yiaction.common.util.g.a("debug_preview", "play preview enable: " + z2, new Object[0]);
        if (!this.aG || !z2 || a()) {
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(boolean z2, boolean z3) {
        ImageView imageView = this.I;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setEnabled(true);
        if (z2) {
            a(R.string.piv_complete);
        } else if (z3) {
            a(R.string.piv_not_allowed);
        } else {
            a(R.string.can_not_piv_in_current_resolution);
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(boolean z2, boolean z3, String str) {
        d(false, true, true);
        c(true);
        if (z2) {
            TextView textView = this.s;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setText((CharSequence) null);
            TextView textView2 = this.s;
            if (textView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            textView2.setVisibility(8);
            f(str);
        } else {
            a(str);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(boolean z2, boolean z3, boolean z4) {
        runOnUiThread(new an(z2, z3, z4));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void c(boolean z2, boolean z3, boolean z4, int i2) {
        L();
        N();
        if (!z2) {
            d(false, true, true);
            c(i2);
            return;
        }
        d(false, true, false);
        RelativeLayout relativeLayout = this.v;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setVisibility(8);
        RelativeLayout relativeLayout2 = this.U;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setVisibility(0);
        TextView textView2 = this.V;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setText("00:00");
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_work);
        c(z3);
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void d(int i2) {
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void d(String str) {
        kotlin.jvm.internal.g.b(str, "param");
        runOnUiThread(new ah());
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void d(boolean z2) {
        runOnUiThread(new ai(z2));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void d(boolean z2, boolean z3, String str) {
        d(false, true, true);
        c(true);
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setText((CharSequence) null);
        TextView textView2 = this.s;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setVisibility(8);
        if (z2) {
            f(str);
        } else {
            a(str);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(int i2) {
        runOnUiThread(new am(i2));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(String str) {
        runOnUiThread(new ag(str));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(boolean z2) {
        c(z2);
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void e(boolean z2, boolean z3, String str) {
        kotlin.jvm.internal.g.b(str, "param");
        runOnUiThread(new ak(z2, str));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void f() {
        runOnUiThread(new y());
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void f(boolean z2) {
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void f(boolean z2, boolean z3, String str) {
        kotlin.jvm.internal.g.b(str, "param");
        d(false, true, true);
        c(true);
        if (z2) {
            g(str);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
        RelativeLayout relativeLayout = this.U;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
    }

    public final List<String> g() {
        return this.d;
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void g(boolean z2) {
        if (!z2) {
            a(R.string.setting_failed);
        }
        J();
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void g(boolean z2, boolean z3, String str) {
        kotlin.jvm.internal.g.b(str, "param");
        d(false, true, true);
        c(true);
        if (z2) {
            g(str);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
        RelativeLayout relativeLayout = this.U;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
    }

    public final List<String> h() {
        return this.e;
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void h(boolean z2) {
        runOnUiThread(new z(z2));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void h(boolean z2, boolean z3, String str) {
        kotlin.jvm.internal.g.b(str, "param");
        d(false, true, true);
        c(true);
        if (z2) {
            g(str);
        }
        ImageView imageView = this.M;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.bg_camera_shutter_normal);
        RelativeLayout relativeLayout = this.U;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setVisibility(8);
    }

    public final List<String> i() {
        return this.f;
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void i(boolean z2) {
        runOnUiThread(new aa(z2));
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void i(boolean z2, boolean z3, String str) {
        kotlin.jvm.internal.g.b(str, FirebaseAnalytics.Param.LEVEL);
        if (z2) {
            try {
                this.ay = Integer.parseInt(str);
                if (this.ay < 0) {
                    this.ay = 0;
                }
                if (this.ay > 100) {
                    this.ay = 100;
                }
            } catch (Exception e2) {
                this.ay = 0;
            }
            com.yiaction.common.util.g.a("debug_battery", "isBattery: " + z3 + " level: " + str, new Object[0]);
            if (!z3 || this.ay < 0 || this.ay > 100) {
                int i2 = this.ay;
                if (i2 >= 0 && 100 >= i2) {
                    this.aw = true;
                    this.ax = true;
                } else {
                    this.aw = false;
                    this.ax = true;
                }
            } else {
                int i3 = this.ay;
                if (15 <= i3 && 100 >= i3) {
                    this.aw = true;
                    this.ax = false;
                } else {
                    this.aw = false;
                    this.ax = false;
                }
            }
            invalidateOptionsMenu();
        }
    }

    public final List<String> j() {
        return this.g;
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void j(boolean z2) {
        ImageView imageView = this.I;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setEnabled(true);
        if (z2) {
            RelativeLayout relativeLayout = this.G;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setEnabled(false);
        }
    }

    @Override // com.xiaoyi.camera.controller.CameraMainController.a
    public void k() {
        de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.a(false));
        com.yiaction.common.util.g.a("debug_event", "CameraNotificationService WIFI_WILL_SHUTDOWN post CameraStopSessionEvent", new Object[0]);
        if (!CameraApplication.f1401a.a() || a()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.setFlags(268435456);
        Intent intent2 = new Intent(this, (Class<?>) CameraConnectionFailedActivity.class);
        intent2.setFlags(268435456);
        startActivities(new Intent[]{intent, intent2});
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void l() {
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------------- onItemScrolling ", new Object[0]);
        RelativeLayout relativeLayout = this.L;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setEnabled(false);
        RelativeLayout relativeLayout2 = this.Q;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setEnabled(false);
    }

    @Override // com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker.b
    public void m() {
        com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "------------------------- onItemScrollfinish ", new Object[0]);
        RelativeLayout relativeLayout = this.L;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setEnabled(true);
        RelativeLayout relativeLayout2 = this.Q;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setEnabled(true);
    }

    public final int n() {
        return this.aL;
    }

    public final int o() {
        return this.aM;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        kotlin.jvm.internal.g.b(intent, ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA);
        if (i2 == 111 && i3 == 1000) {
            com.xiaoyi.camera.b.b = false;
            finish();
            return;
        }
        if (i2 == 111 && com.xiaoyi.camera.b.b) {
            if (i3 == -1 && com.ants360.z13.util.w.a().b()) {
                C();
                return;
            }
            return;
        }
        if (i2 == 115 && i3 == 1002) {
            w();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (!com.ants360.z13.util.q.g(this)) {
            q();
        } else {
            super.onBackPressed();
            overridePendingTransition(0, R.anim.camera_close_exit);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        kotlin.jvm.internal.g.b(view, "v");
        if (this.ad == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.ivFullScreen /* 2131755230 */:
                setRequestedOrientation(0);
                return;
            case R.id.ivSettingWarp /* 2131755231 */:
                d(false, false, false);
                a(true, "");
                CameraMainController cameraMainController = this.ad;
                if (cameraMainController == null) {
                    kotlin.jvm.internal.g.a();
                }
                cameraMainController.x();
                return;
            case R.id.ivSudoku /* 2131755232 */:
                SudokuView sudokuView = this.o;
                if (sudokuView == null) {
                    kotlin.jvm.internal.g.a();
                }
                sudokuView.a(this.aj, this.am);
                return;
            case R.id.ivSetting /* 2131755233 */:
                startActivity(new Intent(this, (Class<?>) VideoPhotoSettingActivity.class));
                StatisticHelper.E();
                return;
            case R.id.ibFileManage /* 2131755254 */:
                startActivity(new Intent(this, (Class<?>) QZAlbumActivity.class));
                return;
            case R.id.rlRecordCapture /* 2131755256 */:
                com.yiaction.common.util.g.a("debug_capture_click", " sessionStart: " + com.xiaoyi.camera.b.b, new Object[0]);
                if (com.xiaoyi.camera.b.b) {
                    d(false, false, false);
                    CameraMainController cameraMainController2 = this.ad;
                    if (cameraMainController2 == null) {
                        kotlin.jvm.internal.g.a();
                    }
                    cameraMainController2.h();
                    Q();
                    return;
                }
                return;
            case R.id.rlModelSwitch /* 2131755261 */:
                if (com.xiaoyi.camera.b.b) {
                    d(false, false, false);
                    this.aR = true;
                    R();
                    G();
                    return;
                }
                return;
            case R.id.rlFunciton /* 2131755266 */:
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                int width = view.getWidth();
                String recordMode = Constant.RecordMode.TIMELAPES.toString();
                CameraMainController cameraMainController3 = this.ad;
                if (cameraMainController3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                a(iArr, width, kotlin.jvm.internal.g.a((Object) recordMode, (Object) cameraMainController3.p().toString()) ? 0 : -1);
                return;
            case R.id.ivGraph /* 2131755269 */:
                ImageView imageView = this.I;
                if (imageView == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView.setEnabled(false);
                CameraMainController cameraMainController4 = this.ad;
                if (cameraMainController4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                cameraMainController4.i();
                StatisticHelper.D();
                return;
            case R.id.rlTipsView /* 2131755735 */:
                LinearLayout linearLayout = this.aa;
                if (linearLayout == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (linearLayout.getVisibility() == 0) {
                    com.yiaction.common.util.i.a().a("already_tips_enter_album", true);
                    if (com.yiaction.common.util.i.a().b("already_tips_switch_model", false)) {
                        a(false, false, false, false);
                        return;
                    } else {
                        a(true, false, true, false);
                        return;
                    }
                }
                LinearLayout linearLayout2 = this.ab;
                if (linearLayout2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (linearLayout2.getVisibility() == 0) {
                    com.yiaction.common.util.i.a().a("already_tips_switch_model", true);
                    a(false, false, false, false);
                    return;
                }
                LinearLayout linearLayout3 = this.ac;
                if (linearLayout3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                if (linearLayout3.getVisibility() != 0) {
                    a(false, false, false, false);
                    return;
                } else {
                    com.yiaction.common.util.i.a().a("already_tips_start_piv", true);
                    a(false, false, false, false);
                    return;
                }
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        com.yiaction.common.util.g.a("debug_camera", "--------------onCreate-------------", new Object[0]);
        super.onCreate(bundle);
        this.aG = T();
        if (!this.aG) {
            a(R.string.phone_not_enough_storage);
        }
        B();
        this.ai = Typeface.createFromAsset(getAssets(), b.a());
        this.ah = Typeface.createFromAsset(getAssets(), b.b());
        this.aj = com.yiaction.common.util.b.b(this);
        this.ak = com.yiaction.common.util.b.c(this);
        this.al = (this.aj * 3) / 4;
        this.am = (this.aj * 9) / 16;
        this.ad = new CameraMainController(CameraApplication.f1401a.i());
        this.aH = new c(this, this);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_camera_qz);
        View findViewById = findViewById(R.id.llOperationLayout);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.h = (LinearLayout) findViewById;
        View findViewById2 = findViewById(R.id.ivSudoku);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.i = (ImageView) findViewById2;
        ImageView imageView = this.i;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setOnClickListener(this);
        ImageView imageView2 = this.i;
        if (imageView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView2.setEnabled(false);
        View findViewById3 = findViewById(R.id.ivSetting);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.j = (ImageView) findViewById3;
        ImageView imageView3 = this.j;
        if (imageView3 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView3.setOnClickListener(this);
        ImageView imageView4 = this.j;
        if (imageView4 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView4.setEnabled(false);
        View findViewById4 = findViewById(R.id.ivFullScreen);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.l = (ImageView) findViewById4;
        View findViewById5 = findViewById(R.id.ivSettingWarp);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.k = (ImageView) findViewById5;
        ImageView imageView5 = this.k;
        if (imageView5 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView5.setOnClickListener(this);
        View findViewById6 = findViewById(R.id.rlBlock);
        if (findViewById6 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.m = (RelativeLayout) findViewById6;
        View findViewById7 = findViewById(R.id.tvInfoCameraAlbum);
        if (findViewById7 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.n = (TextView) findViewById7;
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout.setOnClickListener(this);
        View findViewById8 = findViewById(R.id.svSudokuView);
        if (findViewById8 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.SudokuView");
        }
        this.o = (SudokuView) findViewById8;
        View findViewById9 = findViewById(R.id.player_surface_frame);
        if (findViewById9 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.p = (RelativeLayout) findViewById9;
        View findViewById10 = findViewById(R.id.svPreview);
        if (findViewById10 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.q = (RelativeLayout) findViewById10;
        RelativeLayout relativeLayout2 = this.q;
        if (relativeLayout2 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout2.setOnTouchListener(this.aH);
        this.r = new RPVideoViewHelper(this, this.q);
        View findViewById11 = findViewById(R.id.tvSelfCaptureTime);
        if (findViewById11 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.s = (TextView) findViewById11;
        TextView textView = this.s;
        if (textView == null) {
            kotlin.jvm.internal.g.a();
        }
        textView.setTypeface(this.ai);
        View findViewById12 = findViewById(R.id.pbProcessing);
        if (findViewById12 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
        }
        this.t = (ProgressBar) findViewById12;
        View findViewById13 = findViewById(R.id.ivShowModel);
        if (findViewById13 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.u = (ImageView) findViewById13;
        View findViewById14 = findViewById(R.id.timelaps_record_time_layout);
        if (findViewById14 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.v = (RelativeLayout) findViewById14;
        View findViewById15 = findViewById(R.id.tvTimelapesRecordCountTime);
        if (findViewById15 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.x = (TextView) findViewById15;
        View findViewById16 = findViewById(R.id.tvTimelapesRecordTime);
        if (findViewById16 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.w = (TextView) findViewById16;
        View findViewById17 = findViewById(R.id.tvResolution);
        if (findViewById17 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.y = (TextView) findViewById17;
        View findViewById18 = findViewById(R.id.llrlControlLayout);
        if (findViewById18 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.A = (LinearLayout) findViewById18;
        View findViewById19 = findViewById(R.id.rlControlPanel);
        if (findViewById19 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.B = (LinearLayout) findViewById19;
        View findViewById20 = findViewById(R.id.llFileManage);
        if (findViewById20 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.C = (LinearLayout) findViewById20;
        View findViewById21 = findViewById(R.id.rlFileManage);
        if (findViewById21 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.D = (RelativeLayout) findViewById21;
        View findViewById22 = findViewById(R.id.ibFileManage);
        if (findViewById22 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.E = (ImageView) findViewById22;
        ImageView imageView6 = this.E;
        if (imageView6 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView6.setOnClickListener(this);
        View findViewById23 = findViewById(R.id.llFunciton);
        if (findViewById23 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.F = (RelativeLayout) findViewById23;
        View findViewById24 = findViewById(R.id.rlFunciton);
        if (findViewById24 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.G = (RelativeLayout) findViewById24;
        RelativeLayout relativeLayout3 = this.G;
        if (relativeLayout3 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout3.setOnClickListener(this);
        View findViewById25 = findViewById(R.id.settingItemViewPager);
        if (findViewById25 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.widget.SettingItemViewPager");
        }
        this.J = (SettingItemViewPager) findViewById25;
        View findViewById26 = findViewById(R.id.rlGraph);
        if (findViewById26 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.H = (RelativeLayout) findViewById26;
        View findViewById27 = findViewById(R.id.ivGraph);
        if (findViewById27 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.I = (ImageView) findViewById27;
        ImageView imageView7 = this.I;
        if (imageView7 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView7.setOnClickListener(this);
        View findViewById28 = findViewById(R.id.llRecordCapture);
        if (findViewById28 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.K = (LinearLayout) findViewById28;
        View findViewById29 = findViewById(R.id.rlRecordCapture);
        if (findViewById29 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.L = (RelativeLayout) findViewById29;
        RelativeLayout relativeLayout4 = this.L;
        if (relativeLayout4 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout4.setOnClickListener(this);
        View findViewById30 = findViewById(R.id.cvPhotoView);
        if (findViewById30 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.M = (ImageView) findViewById30;
        View findViewById31 = findViewById(R.id.ibRecordCapture);
        if (findViewById31 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.N = (ImageView) findViewById31;
        View findViewById32 = findViewById(R.id.ivPhotoView);
        if (findViewById32 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.O = (ImageView) findViewById32;
        View findViewById33 = findViewById(R.id.llNormalRecordTime);
        if (findViewById33 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.U = (RelativeLayout) findViewById33;
        View findViewById34 = findViewById(R.id.tvNormalRecordTime);
        if (findViewById34 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.V = (TextView) findViewById34;
        View findViewById35 = findViewById(R.id.rlShutterRemind);
        if (findViewById35 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.W = (RelativeLayout) findViewById35;
        View findViewById36 = findViewById(R.id.tvShutterRemind);
        if (findViewById36 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.X = (TextView) findViewById36;
        View findViewById37 = findViewById(R.id.llModelSwitch);
        if (findViewById37 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.P = (LinearLayout) findViewById37;
        View findViewById38 = findViewById(R.id.rlModelSwitch);
        if (findViewById38 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.Q = (RelativeLayout) findViewById38;
        RelativeLayout relativeLayout5 = this.Q;
        if (relativeLayout5 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout5.setOnClickListener(this);
        View findViewById39 = findViewById(R.id.ibRecordCapture_2);
        if (findViewById39 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.R = (ImageView) findViewById39;
        View findViewById40 = findViewById(R.id.ivSwitchView);
        if (findViewById40 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.S = (ImageView) findViewById40;
        View findViewById41 = findViewById(R.id.cvSwitchView);
        if (findViewById41 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.T = (ImageView) findViewById41;
        View findViewById42 = findViewById(R.id.playSeekbar);
        if (findViewById42 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ants360.z13.widget.TimelapsSeekBar");
        }
        this.Y = (TimelapsSeekBar) findViewById42;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        TimelapsSeekBar timelapsSeekBar = this.Y;
        if (timelapsSeekBar == null) {
            kotlin.jvm.internal.g.a();
        }
        timelapsSeekBar.setLayoutParams(layoutParams);
        TimelapsSeekBar timelapsSeekBar2 = this.Y;
        if (timelapsSeekBar2 == null) {
            kotlin.jvm.internal.g.a();
        }
        timelapsSeekBar2.setProgress(0);
        View findViewById43 = findViewById(R.id.hpModelPicker);
        if (findViewById43 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.xy.sportscamera.camera.widget.HorizontalPicker");
        }
        this.z = (HorizontalPicker) findViewById43;
        HorizontalPicker horizontalPicker = this.z;
        if (horizontalPicker == null) {
            kotlin.jvm.internal.g.a();
        }
        horizontalPicker.b(getResources().getColor(R.color.white));
        HorizontalPicker horizontalPicker2 = this.z;
        if (horizontalPicker2 == null) {
            kotlin.jvm.internal.g.a();
        }
        horizontalPicker2.setOnItemSelectedListener(this);
        View findViewById44 = findViewById(R.id.rlTipsView);
        if (findViewById44 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.Z = (RelativeLayout) findViewById44;
        RelativeLayout relativeLayout6 = this.Z;
        if (relativeLayout6 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout6.setVisibility(8);
        RelativeLayout relativeLayout7 = this.Z;
        if (relativeLayout7 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout7.setOnClickListener(this);
        View findViewById45 = findViewById(R.id.llEnterAlbum);
        if (findViewById45 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.aa = (LinearLayout) findViewById45;
        View findViewById46 = findViewById(R.id.llSwitchModel);
        if (findViewById46 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.ab = (LinearLayout) findViewById46;
        View findViewById47 = findViewById(R.id.llStartPiv);
        if (findViewById47 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.ac = (LinearLayout) findViewById47;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, this.am);
        SudokuView sudokuView = this.o;
        if (sudokuView == null) {
            kotlin.jvm.internal.g.a();
        }
        sudokuView.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(this.aj, this.am);
        RelativeLayout relativeLayout8 = this.p;
        if (relativeLayout8 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout8.setLayoutParams(layoutParams3);
        U();
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, this.al / 8);
        TextView textView2 = this.y;
        if (textView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        textView2.setLayoutParams(layoutParams4);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, this.al / 8);
        RelativeLayout relativeLayout9 = this.U;
        if (relativeLayout9 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout9.setLayoutParams(layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, this.al / 8);
        layoutParams6.addRule(3, R.id.llNormalRecordTime);
        RelativeLayout relativeLayout10 = this.W;
        if (relativeLayout10 == null) {
            kotlin.jvm.internal.g.a();
        }
        relativeLayout10.setLayoutParams(layoutParams6);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setLogo(R.drawable.camera_close_select);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.xiaoyi.camera.a.a("sd_card_status"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_io_error"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_full"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_format_done"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_album"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_fwupdate"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_sdformat"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_sdformat"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_mvrecover"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_mvrecover"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("sdcard_optimize_stop"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("enter_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("exit_usb_storage"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_start"));
        intentFilter.addAction(com.xiaoyi.camera.a.a("voice_control_sample_stop"));
        registerReceiver(this.aP, intentFilter);
        de.greenrobot.event.c.a().a(this);
        com.rp.a aVar = com.rp.a.b;
        Context i2 = CameraApplication.f1401a.i();
        if (i2 == null) {
            kotlin.jvm.internal.g.a();
        }
        aVar.a(i2);
        com.rp.rptool.util.d.a().a(new com.rp.rptool.a.c("F3J9A9NE4E6RUH6PSFZ1", "12345", 0, "192.168.42.1", 6669));
        com.rp.rptool.util.d.a().a(this.aZ);
        com.rp.rptool.util.d.a().e();
        this.aN.postDelayed(this.aU, 100L);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        com.yiaction.common.util.g.a("debug_camera", "--------------destroy-------------", new Object[0]);
        com.yiaction.common.util.g.a("debug_switch_fragment", "destroy CameraFragment", new Object[0]);
        super.onDestroy();
        if (this.s != null) {
            TextView textView = this.s;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setVisibility(8);
        }
        if (this.U != null) {
            RelativeLayout relativeLayout = this.U;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setVisibility(8);
        }
        if (this.v != null) {
            RelativeLayout relativeLayout2 = this.v;
            if (relativeLayout2 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout2.setVisibility(8);
        }
        if (this.ad != null) {
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            cameraMainController.u();
            this.ad = (CameraMainController) null;
        }
        unregisterReceiver(this.aP);
        de.greenrobot.event.c.a().b(this);
        if (com.xiaoyi.camera.b.b) {
            return;
        }
        NetworkUtil.clearBindProcess(CameraApplication.f1401a.i());
        com.ants360.z13.b.a.a((a.InterfaceC0051a) null);
    }

    public final void onEvent(com.ants360.z13.c.d dVar) {
        kotlin.jvm.internal.g.b(dVar, "event");
        runOnUiThread(new ad());
    }

    public final void onEvent(com.xiaoyi.camera.a.a aVar) {
        kotlin.jvm.internal.g.b(aVar, "event");
        com.yiaction.common.util.g.a("debug_event", "CameraActivity received CameraStopSessionEvent", new Object[0]);
        runOnUiThread(new ab(aVar));
    }

    public final void onEvent(com.xiaoyi.camera.a.c cVar) {
        kotlin.jvm.internal.g.b(cVar, "event");
        runOnUiThread(new ae());
    }

    public final void onEvent(com.xiaoyi.camera.a.d dVar) {
        kotlin.jvm.internal.g.b(dVar, "event");
        runOnUiThread(new ac(dVar));
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        kotlin.jvm.internal.g.b(keyEvent, "event");
        if ((i2 != 25 && i2 != 24) || !com.xiaoyi.camera.b.b || this.ad == null) {
            return super.onKeyDown(i2, keyEvent);
        }
        d(false, false, false);
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController.h();
        Q();
        return true;
    }

    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        kotlin.jvm.internal.g.b(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        q();
        return false;
    }

    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        com.yiaction.common.util.g.a("debug_camera", "--------------pause-------------", new Object[0]);
        com.yiaction.common.util.g.a("debug_switch_fragment", "pause CameraFragment", new Object[0]);
        super.onPause();
        if (!this.aA) {
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        kotlin.jvm.internal.g.b(menu, "menu");
        if (com.xiaoyi.camera.b.b) {
            a(menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // com.ants360.z13.activity.BaseConfigActivity, com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        com.yiaction.common.util.g.a("debug_camera", "--------------resume-------------", new Object[0]);
        com.yiaction.common.util.g.a("debug_preview", "resume()", new Object[0]);
        if (!com.xiaoyi.camera.b.b) {
            finish();
            return;
        }
        if (!com.yiaction.common.util.i.a().b("already_tips_enter_album", false)) {
            com.yiaction.common.util.i.a().b("already_tips_enter_album", true);
            a(true, true, false, false);
        }
        if (com.yiaction.common.util.i.a().b("already_tips_enter_album", false) && !com.yiaction.common.util.i.a().b("already_tips_switch_model", false)) {
            com.yiaction.common.util.i.a().b("already_tips_switch_model", true);
            a(true, false, true, false);
        }
        M();
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController.b();
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController2.a(this);
        CameraMainController cameraMainController3 = this.ad;
        if (cameraMainController3 == null) {
            kotlin.jvm.internal.g.a();
        }
        if (cameraMainController3.B()) {
            return;
        }
        F();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        kotlin.jvm.internal.g.b(bundle, "outState");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (z2) {
            LinearLayout linearLayout = this.A;
            if (linearLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            int height = linearLayout.getHeight();
            int i2 = height / 2;
            int i3 = i2 > (this.aj * 210) / 1080 ? (this.aj * 210) / 1080 : i2;
            this.aB = i3;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i3, i3);
            RelativeLayout relativeLayout = this.L;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setLayoutParams(layoutParams);
            RelativeLayout relativeLayout2 = this.Q;
            if (relativeLayout2 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout2.setLayoutParams(layoutParams);
            new LinearLayout.LayoutParams(-1, i3);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((i3 * 88) / 210, (i3 * 88) / 210);
            layoutParams2.addRule(13);
            ImageView imageView = this.N;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setLayoutParams(layoutParams2);
            ImageView imageView2 = this.R;
            if (imageView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView2.setLayoutParams(layoutParams2);
            int i4 = (this.aj * 170) / 1080;
            int i5 = (i3 - i4) / 2;
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(i4, i4);
            layoutParams3.topMargin = i5;
            RelativeLayout relativeLayout3 = this.D;
            if (relativeLayout3 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout3.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(((this.aj / 3) * 3) / 4, i4);
            layoutParams4.topMargin = i5;
            RelativeLayout relativeLayout4 = this.G;
            if (relativeLayout4 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout4.setLayoutParams(layoutParams4);
            int i6 = (this.aj * 120) / 1080;
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i6, i6);
            layoutParams5.topMargin = (i3 - i6) / 2;
            RelativeLayout relativeLayout5 = this.H;
            if (relativeLayout5 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout5.setLayoutParams(layoutParams5);
            if (!com.yiaction.common.util.i.a().b("already_tips_enter_album", false)) {
                LinearLayout linearLayout2 = this.aa;
                if (linearLayout2 == null) {
                    kotlin.jvm.internal.g.a();
                }
                ViewGroup.LayoutParams layoutParams6 = linearLayout2.getLayoutParams();
                if (layoutParams6 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                }
                RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) layoutParams6;
                layoutParams7.addRule(13);
                layoutParams7.setMargins(this.aj / 6, 0, 0, (height - ((height - (i3 * 2)) / 2)) - ((i3 - i6) / 2));
                LinearLayout linearLayout3 = this.aa;
                if (linearLayout3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                linearLayout3.setLayoutParams(layoutParams7);
            }
            if (!com.yiaction.common.util.i.a().b("already_tips_switch_model", false)) {
                LinearLayout linearLayout4 = this.ab;
                if (linearLayout4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                ViewGroup.LayoutParams layoutParams8 = linearLayout4.getLayoutParams();
                if (layoutParams8 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                }
                RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) layoutParams8;
                layoutParams9.addRule(13);
                layoutParams9.setMargins((this.aj / 2) - (i3 / 2), 0, 0, ((height - (i3 * 2)) / 2) + (i3 / 2));
                LinearLayout linearLayout5 = this.ab;
                if (linearLayout5 == null) {
                    kotlin.jvm.internal.g.a();
                }
                linearLayout5.setLayoutParams(layoutParams9);
            }
            if (com.yiaction.common.util.i.a().b("already_tips_start_piv", false)) {
                return;
            }
            LinearLayout linearLayout6 = this.ac;
            if (linearLayout6 == null) {
                kotlin.jvm.internal.g.a();
            }
            ViewGroup.LayoutParams layoutParams10 = linearLayout6.getLayoutParams();
            if (layoutParams10 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) layoutParams10;
            layoutParams11.addRule(15);
            layoutParams11.setMargins(0, 0, this.aj / 6, (height - ((height - (i3 * 2)) / 2)) - ((i3 - i6) / 2));
            LinearLayout linearLayout7 = this.ac;
            if (linearLayout7 == null) {
                kotlin.jvm.internal.g.a();
            }
            linearLayout7.setLayoutParams(layoutParams11);
        }
    }

    public final void p() {
        a(true, (String) null);
        if (this.s != null) {
            TextView textView = this.s;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setVisibility(8);
        }
        if (this.U != null) {
            RelativeLayout relativeLayout = this.U;
            if (relativeLayout == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout.setVisibility(8);
        }
        if (this.v != null) {
            RelativeLayout relativeLayout2 = this.v;
            if (relativeLayout2 == null) {
                kotlin.jvm.internal.g.a();
            }
            relativeLayout2.setVisibility(8);
        }
        w();
        com.xiaoyi.camera.g.a().e();
        Context i2 = CameraApplication.f1401a.i();
        if (i2 != null) {
            i2.sendBroadcast(new Intent().setAction(com.xiaoyi.camera.a.a("exit_album")));
        }
        d(false);
        if (this.ad != null) {
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            cameraMainController.u();
        }
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.b.f4624a.d();
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.d.f4629a.e();
        com.xiaomi.xy.sportscamera.camera.filemanager.j22file.a.b.a();
        c(false);
        com.xiaoyi.camera.b.b = false;
        CameraApplication.f1401a.d(false);
        CameraApplication.f1401a.c(false);
        com.yiaction.common.util.g.a("debug_switch_fragment", "invalidateOptionsMenu when destroyAll", new Object[0]);
        com.rp.rptool.util.d.a().b(this.aZ);
        com.rp.rptool.util.d.a().e();
        this.aN.postDelayed(this.aW, 200L);
    }

    public final void q() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.disconnect_from_camera));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        bundle.putString("right_button", getString(R.string.camera_connect_dis));
        bundle.putString("left_button", getString(R.string.camera_quit));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new l());
        customBottomDialogFragment.a(this);
    }

    public final void r() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.sd_card_format));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.sd_card_format_now));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new ao());
        customBottomDialogFragment.a(this);
    }

    public void s() {
        Object systemService = getSystemService("vibrator");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.os.Vibrator");
        }
        Vibrator vibrator = (Vibrator) systemService;
        if (vibrator == null || !vibrator.hasVibrator()) {
            return;
        }
        vibrator.vibrate(300L);
    }

    public final void t() {
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController.a(CameraMainController.CameraMode.RecordMode);
    }

    public final void u() {
        if (this.z != null) {
            HorizontalPicker horizontalPicker = this.z;
            if (horizontalPicker == null) {
                kotlin.jvm.internal.g.a();
            }
            horizontalPicker.setVisibility(0);
        }
        if (this.y != null) {
            TextView textView = this.y;
            if (textView == null) {
                kotlin.jvm.internal.g.a();
            }
            textView.setVisibility(0);
        }
        ImageView imageView = this.N;
        if (imageView == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView.setImageResource(R.drawable.icon_video_white);
        ImageView imageView2 = this.R;
        if (imageView2 == null) {
            kotlin.jvm.internal.g.a();
        }
        imageView2.setImageResource(R.drawable.icon_camera_green);
        HorizontalPicker horizontalPicker2 = this.z;
        if (horizontalPicker2 == null) {
            kotlin.jvm.internal.g.a();
        }
        List<String> list = this.g;
        if (list == null) {
            kotlin.jvm.internal.g.a();
        }
        List<String> list2 = list;
        if (list2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Object[] array = list2.toArray(new String[list2.size()]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        horizontalPicker2.setValues((CharSequence[]) array);
        List<String> list3 = this.e;
        if (list3 == null) {
            kotlin.jvm.internal.g.a();
        }
        Iterator<Integer> it2 = kotlin.collections.k.a((Collection<?>) list3).iterator();
        while (it2.hasNext()) {
            int b2 = ((kotlin.collections.v) it2).b();
            List<String> list4 = this.e;
            if (list4 == null) {
                kotlin.jvm.internal.g.a();
            }
            String str = list4.get(b2);
            CameraMainController cameraMainController = this.ad;
            if (cameraMainController == null) {
                kotlin.jvm.internal.g.a();
            }
            if (kotlin.jvm.internal.g.a((Object) str, (Object) cameraMainController.p().toString())) {
                this.az = b2;
                HorizontalPicker horizontalPicker3 = this.z;
                if (horizontalPicker3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                horizontalPicker3.setSelectedItem(this.az);
            }
        }
        CameraMainController cameraMainController2 = this.ad;
        if (cameraMainController2 == null) {
            kotlin.jvm.internal.g.a();
        }
        Constant.RecordMode p2 = cameraMainController2.p();
        if (p2 == null) {
            return;
        }
        switch (com.xiaomi.xy.sportscamera.camera.activity.a.f[p2.ordinal()]) {
            case 1:
                ImageView imageView3 = this.u;
                if (imageView3 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView3.setImageResource(R.drawable.show_video_timelapse);
                return;
            case 2:
                ImageView imageView4 = this.u;
                if (imageView4 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView4.setImageResource(R.drawable.show_video_photo);
                return;
            case 3:
                ImageView imageView5 = this.u;
                if (imageView5 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView5.setImageResource(R.drawable.show_video_loop);
                return;
            case 4:
                ImageView imageView6 = this.u;
                if (imageView6 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView6.setImageResource(R.drawable.show_video_slow);
                return;
            case 5:
                ImageView imageView7 = this.u;
                if (imageView7 == null) {
                    kotlin.jvm.internal.g.a();
                }
                imageView7.setImageResource(R.drawable.show_video_normal);
                return;
            default:
                return;
        }
    }

    public final void v() {
        CameraMainController cameraMainController = this.ad;
        if (cameraMainController == null) {
            kotlin.jvm.internal.g.a();
        }
        cameraMainController.a(CameraMainController.CameraMode.CaptureMode);
    }

    public final void w() {
        if (this.E != null) {
            ImageView imageView = this.E;
            if (imageView == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView.setImageResource(R.drawable.pic_default);
            ImageView imageView2 = this.E;
            if (imageView2 == null) {
                kotlin.jvm.internal.g.a();
            }
            imageView2.setTag(null);
            this.aF = (String) null;
        }
    }

    public final void x() {
        if (this.aA) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.wifi_connect_failed));
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.wifi_connect_failed_desc_default));
        bundle.putString("left_button", getString(R.string.button_back_to_home));
        bundle.putString("right_button", getString(R.string.button_reconnect));
        bundle.putInt(TtmlNode.TAG_STYLE, R.style.DimPanel_No_StatusBar);
        this.af = new CustomBottomDialogFragment();
        CustomBottomDialogFragment customBottomDialogFragment = this.af;
        if (customBottomDialogFragment == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment.setArguments(bundle);
        CustomBottomDialogFragment customBottomDialogFragment2 = this.af;
        if (customBottomDialogFragment2 == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment2.a(new k());
        CustomBottomDialogFragment customBottomDialogFragment3 = this.af;
        if (customBottomDialogFragment3 == null) {
            kotlin.jvm.internal.g.a();
        }
        customBottomDialogFragment3.a(this);
    }

    public final d.a y() {
        return this.aZ;
    }
}
