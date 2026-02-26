package com.xiaomi.xy.sportscamera.camera.upgrade.firmware;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.fragment.CustomBottomDialogFragment;
import com.ants360.z13.fragment.DimPanelFragment;
import com.ants360.z13.util.q;
import com.ants360.z13.widget.CustomTitleBar;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.xy.sportscamera.R;
import com.xiaomi.xy.sportscamera.camera.d;
import com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareVersionDialog;
import com.yiaction.common.util.g;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class FirmwareListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btnUpgrade)
    TextView btnUpgrade;
    private d c;

    @BindView(R.id.download_progress)
    DownloadProgressView download_progress;
    private ArrayList<String> e;
    private HashMap<String, List<FirmwareModel>> f;
    private int g;
    private int h;
    private String i;

    @BindView(R.id.ivCameraModel)
    ImageView ivCameraModel;
    private FirmwareModel j;

    @BindView(R.id.rlBlock)
    View rlBlock;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    @BindView(R.id.tv_fm_version)
    TextView tv_fm_version;

    @BindView(R.id.tv_hw_version)
    TextView tv_hw_version;
    private String d = "Z13";
    com.xiaoyi.camera.c.d b = new com.xiaoyi.camera.c.d() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.4
        @Override // com.xiaoyi.camera.c.d
        public void a() {
            FirmwareListActivity.this.k.sendMessage(FirmwareListActivity.this.k.obtainMessage(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_DURATION));
        }

        @Override // com.xiaoyi.camera.c.d
        public void a(int i) {
            Message obtainMessage = FirmwareListActivity.this.k.obtainMessage(IjkMediaPlayer.FFP_PROP_INT64_AUDIO_CACHED_DURATION);
            obtainMessage.arg1 = i;
            FirmwareListActivity.this.k.sendMessage(obtainMessage);
        }

        @Override // com.xiaoyi.camera.c.d
        public void b() {
            FirmwareListActivity.this.k.sendEmptyMessage(IjkMediaPlayer.FFP_PROP_INT64_AUDIO_DECODER);
        }
    };
    private a k = new a(Looper.getMainLooper());

    /* loaded from: classes3.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case IjkMediaPlayer.FFP_PROP_INT64_SELECTED_VIDEO_STREAM /* 20001 */:
                    FirmwareListActivity.this.d((String) message.obj);
                    return;
                case IjkMediaPlayer.FFP_PROP_INT64_SELECTED_AUDIO_STREAM /* 20002 */:
                    FirmwareListActivity.this.rlBlock.setVisibility(8);
                    FirmwareListActivity.this.a(R.string.check_failed);
                    return;
                case IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER /* 20003 */:
                    FirmwareListActivity.this.tv_hw_version.setEnabled(false);
                    FirmwareListActivity.this.tv_fm_version.setEnabled(false);
                    FirmwareListActivity.this.download_progress.setVisibility(0);
                    FirmwareListActivity.this.download_progress.setProgress(0);
                    FirmwareListActivity.this.btnUpgrade.setVisibility(4);
                    FirmwareListActivity.this.f();
                    return;
                case IjkMediaPlayer.FFP_PROP_INT64_AUDIO_DECODER /* 20004 */:
                    FirmwareListActivity.this.a(R.string.prompt_download_finished);
                    FirmwareListActivity.this.tv_hw_version.setEnabled(true);
                    FirmwareListActivity.this.tv_fm_version.setEnabled(true);
                    FirmwareListActivity.this.r();
                    FirmwareListActivity.this.download_progress.setVisibility(4);
                    FirmwareListActivity.this.download_progress.setProgress(0);
                    FirmwareListActivity.this.btnUpgrade.setVisibility(0);
                    FirmwareListActivity.this.btnUpgrade.setText(R.string.firmware_only_downloaded);
                    return;
                case IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_DURATION /* 20005 */:
                    FirmwareListActivity.this.tv_hw_version.setEnabled(true);
                    FirmwareListActivity.this.tv_fm_version.setEnabled(true);
                    FirmwareListActivity.this.download_progress.setVisibility(4);
                    FirmwareListActivity.this.download_progress.setProgress(0);
                    FirmwareListActivity.this.btnUpgrade.setVisibility(0);
                    return;
                case IjkMediaPlayer.FFP_PROP_INT64_AUDIO_CACHED_DURATION /* 20006 */:
                    FirmwareListActivity.this.download_progress.setProgress((int) ((message.arg1 / Float.valueOf((float) FirmwareListActivity.this.j.firmwareSize).floatValue()) * 100.0f));
                    return;
                case IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_BYTES /* 20007 */:
                    FirmwareListActivity.this.a(R.string.files_already_downloaded);
                    return;
                case IjkMediaPlayer.FFP_PROP_INT64_AUDIO_CACHED_BYTES /* 20008 */:
                    FirmwareListActivity.this.n();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        this.rlBlock.setVisibility(8);
        List<FirmwareModel> parse = FirmwareModel.parse(this.d, str);
        if (parse == null || parse.isEmpty()) {
            this.k.sendMessage(this.k.obtainMessage(IjkMediaPlayer.FFP_PROP_INT64_SELECTED_AUDIO_STREAM));
        } else {
            if (this.f == null || this.f.isEmpty()) {
                this.f = new HashMap<>();
            }
            if (this.e == null || this.e.isEmpty()) {
                this.e = new ArrayList<>();
            }
            for (FirmwareModel firmwareModel : parse) {
                String str2 = firmwareModel.hardwareCode;
                if (!this.e.contains(str2)) {
                    this.e.add(str2);
                }
                List<FirmwareModel> list = this.f.get(str2);
                if (list == null || list.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(firmwareModel);
                    this.f.put(str2, arrayList);
                } else {
                    list.add(firmwareModel);
                }
            }
            i();
        }
        g.a(BuildConfig.BUILD_TYPE, "getFirmwareList : onSuccess : routeModelList.size() = " + parse.size(), new Object[0]);
    }

    private void h() {
        this.titleBar.setTitleClickListener(new CustomTitleBar.a() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.1
            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void a() {
                FirmwareListActivity.this.onBackPressed();
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void b() {
            }

            @Override // com.ants360.z13.widget.CustomTitleBar.a
            public void c() {
            }
        });
        if ("Z13".equals(this.d)) {
            this.ivCameraModel.setImageResource(R.drawable.ic_camera_connect_status_z13);
        } else if ("Z16".equals(this.d)) {
            this.ivCameraModel.setImageResource(R.drawable.ic_camera_connect_status_4k);
        } else if ("Z18".equals(this.d)) {
            this.ivCameraModel.setImageResource(R.drawable.ic_camera_connect_status_4kp);
        } else if ("J11".equals(this.d)) {
            this.ivCameraModel.setImageResource(R.drawable.ic_camera_connect_status_1080);
        } else if ("J22".equals(this.d)) {
            this.ivCameraModel.setImageResource(R.drawable.ic_camera_connect_status_discovery);
        }
        this.tv_hw_version.setOnClickListener(this);
        this.tv_fm_version.setOnClickListener(this);
        this.btnUpgrade.setOnClickListener(this);
        this.download_progress.setOnClickListener(this);
        this.rlBlock.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.i = this.e.get(this.g);
        this.j = this.f.get(this.i).get(this.h);
        this.tv_hw_version.setText(this.i);
        this.tv_fm_version.setText(this.j.firmwareCode);
        if (this.c.r(this.j.md5Code)) {
            this.btnUpgrade.setText(R.string.firmware_only_downloaded);
        } else {
            this.btnUpgrade.setText(R.string.download);
        }
    }

    private void j() {
        Bundle bundle = new Bundle();
        bundle.putString("VERSION_TITLE", getString(R.string.hw_version_code));
        bundle.putInt("VERSION_POSITION", this.g);
        bundle.putStringArrayList("VERSION_LIST", this.e);
        FirmwareVersionDialog firmwareVersionDialog = (FirmwareVersionDialog) FirmwareVersionDialog.instantiate(this, FirmwareVersionDialog.class.getName(), bundle);
        firmwareVersionDialog.a(new FirmwareVersionDialog.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.2
            @Override // com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareVersionDialog.c
            public void a(int i) {
                FirmwareListActivity.this.g = i;
                FirmwareListActivity.this.h = 0;
                FirmwareListActivity.this.i();
            }
        });
        firmwareVersionDialog.a(this);
    }

    private void k() {
        Bundle bundle = new Bundle();
        bundle.putString("VERSION_TITLE", getString(R.string.fm_version_code));
        bundle.putInt("VERSION_POSITION", this.h);
        bundle.putStringArrayList("VERSION_LIST", l());
        FirmwareVersionDialog firmwareVersionDialog = (FirmwareVersionDialog) FirmwareVersionDialog.instantiate(this, FirmwareVersionDialog.class.getName(), bundle);
        firmwareVersionDialog.a(new FirmwareVersionDialog.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.3
            @Override // com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareVersionDialog.c
            public void a(int i) {
                FirmwareListActivity.this.h = i;
                FirmwareListActivity.this.i();
            }
        });
        firmwareVersionDialog.a(this);
    }

    private ArrayList<String> l() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<FirmwareModel> it2 = this.f.get(this.e.get(this.g)).iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().firmwareCode);
        }
        return arrayList;
    }

    private void m() {
        new Thread(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (FirmwareListActivity.this.c.p(FirmwareListActivity.this.j.md5Code)) {
                    FirmwareListActivity.this.k.sendEmptyMessage(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_BYTES);
                    return;
                }
                d dVar = FirmwareListActivity.this.c;
                d unused = FirmwareListActivity.this.c;
                if (dVar.r(d.l(FirmwareListActivity.this.d))) {
                    FirmwareListActivity.this.k.sendEmptyMessage(IjkMediaPlayer.FFP_PROP_INT64_AUDIO_CACHED_BYTES);
                } else {
                    FirmwareListActivity.this.k.sendEmptyMessage(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.fm_download_file_delete_tip, new Object[]{this.c.m(this.d), this.j.firmwareCode, this.c.m(this.d)}));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.6
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                d unused = FirmwareListActivity.this.c;
                String l = d.l(FirmwareListActivity.this.d);
                StringBuilder sb = new StringBuilder();
                d unused2 = FirmwareListActivity.this.c;
                StringBuilder append = sb.append(d.f());
                d unused3 = FirmwareListActivity.this.c;
                File file = new File(append.append(d.s(l)).toString());
                if (file.exists()) {
                    file.delete();
                }
                FirmwareListActivity.this.k.sendEmptyMessage(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER);
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }
        });
        customBottomDialogFragment.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        String str = this.j.md5Code;
        String str2 = this.j.firmwareUrl;
        Long valueOf = Long.valueOf(this.j.firmwareSize);
        this.c.a(str, this.b);
        this.c.a(str, (Boolean) true);
        if (this.c.a(this, str2, str, valueOf.longValue())) {
            return;
        }
        this.k.sendMessage(this.k.obtainMessage(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_DURATION));
    }

    private void p() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.ACTION_LOG_TYPE_MESSAGE, getString(R.string.firmware_download_tip));
        CustomBottomDialogFragment customBottomDialogFragment = new CustomBottomDialogFragment();
        customBottomDialogFragment.setArguments(bundle);
        customBottomDialogFragment.a(new DimPanelFragment.c() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.7
            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void a(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                FirmwareListActivity.this.o();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void b(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override // com.ants360.z13.fragment.DimPanelFragment.c
            public void c(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }
        });
        customBottomDialogFragment.a(this);
    }

    private boolean q() {
        if (this.e != null && this.f != null && this.j != null) {
            return false;
        }
        this.rlBlock.setVisibility(0);
        this.c.a(this.d, new com.yiaction.common.http.g<String>() { // from class: com.xiaomi.xy.sportscamera.camera.upgrade.firmware.FirmwareListActivity.8
            @Override // com.yiaction.common.http.g
            /* renamed from: a, reason: avoid collision after fix types in other method */
            public void a2(String str) {
                FirmwareListActivity.this.k.sendMessage(FirmwareListActivity.this.k.obtainMessage(IjkMediaPlayer.FFP_PROP_INT64_SELECTED_AUDIO_STREAM));
            }

            @Override // com.yiaction.common.http.g
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(String str) {
                Message obtainMessage = FirmwareListActivity.this.k.obtainMessage(IjkMediaPlayer.FFP_PROP_INT64_SELECTED_VIDEO_STREAM);
                obtainMessage.obj = str;
                FirmwareListActivity.this.k.sendMessage(obtainMessage);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        String str;
        ArrayList arrayList = new ArrayList();
        Iterator<String> it2 = this.e.iterator();
        while (it2.hasNext()) {
            for (FirmwareModel firmwareModel : this.f.get(it2.next())) {
                if (firmwareModel.md5Code.equals(this.j.md5Code) && !arrayList.contains(firmwareModel.hardwareCode)) {
                    arrayList.add(firmwareModel.hardwareCode);
                }
            }
        }
        String str2 = "";
        Iterator it3 = arrayList.iterator();
        while (true) {
            str = str2;
            if (!it3.hasNext()) {
                break;
            }
            str2 = str + ((String) it3.next()) + HelpFormatter.DEFAULT_OPT_PREFIX;
        }
        if (!TextUtils.isEmpty(str)) {
            str = str.substring(0, str.length() - 1);
        }
        this.c.k(this.j.snPrefix, str);
        this.c.l(this.j.snPrefix, this.j.md5Code);
        this.c.m(this.j.snPrefix, this.j.originMd5Code);
        this.c.n(this.j.snPrefix, this.j.firmwareCode);
        this.c.o(this.j.snPrefix, this.j.firmwareMemo);
    }

    public void f() {
        if (q.b(this) && !q.c(this)) {
            o();
        } else if (q.a(this)) {
            p();
        } else {
            a(R.string.prompt_no_network_connection);
        }
    }

    public void g() {
        this.c.d();
        this.c.a(this.j.md5Code, (Boolean) false);
        StringBuilder sb = new StringBuilder();
        d dVar = this.c;
        StringBuilder append = sb.append(d.f());
        d dVar2 = this.c;
        File file = new File(append.append(d.s(this.j.md5Code)).toString());
        if (file.exists()) {
            file.delete();
        }
        this.k.sendEmptyMessage(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_DURATION);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpgrade /* 2131755296 */:
                if (q()) {
                    return;
                }
                m();
                return;
            case R.id.tv_hw_version /* 2131755298 */:
                if (q()) {
                    return;
                }
                j();
                return;
            case R.id.tv_fm_version /* 2131755701 */:
                if (q()) {
                    return;
                }
                k();
                return;
            case R.id.download_progress /* 2131755703 */:
                g();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_download_list_activity);
        this.d = getIntent().getStringExtra("DOWNLOAD_MODEL");
        h();
        this.c = d.a();
        q();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
