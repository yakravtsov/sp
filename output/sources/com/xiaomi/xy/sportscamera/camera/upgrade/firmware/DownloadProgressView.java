package com.xiaomi.xy.sportscamera.camera.upgrade.firmware;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.xiaomi.xy.sportscamera.R;

/* loaded from: classes3.dex */
public class DownloadProgressView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private ProgressBar f4772a;
    private ProgressTextView b;

    public DownloadProgressView(Context context) {
        this(context, null);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_of_download_progress, this);
        this.f4772a = (ProgressBar) inflate.findViewById(R.id.pb_download_progress);
        this.b = (ProgressTextView) inflate.findViewById(R.id.tv_download_progress);
    }

    public void setProgress(int i) {
        this.f4772a.setProgress(i);
        this.b.setProgress(i);
    }

    public void setProgressTextWidth(int i) {
        this.b.setParentViewWidth(i);
    }
}
