package com.xiaomi.xy.sportscamera.camera.upgrade.firmware;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xiaomi.xy.sportscamera.R;
import com.yiaction.common.util.b;

/* loaded from: classes3.dex */
public class ProgressTextView extends TextView {

    /* renamed from: a, reason: collision with root package name */
    private LinearGradient f4786a;
    private Paint b;
    private int c;
    private int d;
    private Rect e;
    private int f;
    private int g;

    public ProgressTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = getPaint();
        this.c = 0;
        this.d = 0;
        this.e = new Rect();
        this.f = 100;
        this.g = 100;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        String charSequence = getText().toString();
        this.b.getTextBounds(charSequence, 0, charSequence.length(), this.e);
        this.f4786a = new LinearGradient(this.f - 1, 0.0f, this.f, 0.0f, new int[]{-1, -16001726}, new float[]{0.5f, 1.0f}, Shader.TileMode.CLAMP);
        this.b.setShader(this.f4786a);
        canvas.drawText(charSequence, (getMeasuredWidth() / 2) - (this.e.width() / 2), (getMeasuredHeight() / 2) + (this.e.height() / 2), this.b);
        setText(getResources().getString(R.string.firmware_download_progress, Integer.valueOf(this.g)) + "%");
    }

    public void setParentViewWidth(int i) {
        this.d = i;
    }

    public void setProgress(int i) {
        this.g = i;
        setText(getResources().getString(R.string.firmware_download_progress, Integer.valueOf(this.g)) + "%");
        this.c = getMeasuredWidth();
        if (this.d == 0) {
            this.d = b.b(getContext()) - (getResources().getDimensionPixelSize(R.dimen.length_40) * 2);
        }
        int i2 = (this.d * this.g) / 100;
        if (i2 < (this.d / 2) - (this.c / 2)) {
            this.f = 0;
        } else if (i2 < (this.d / 2) - (this.c / 2) || i2 > (this.d / 2) + (this.c / 2)) {
            this.f = this.c;
        } else {
            this.f = i2 - ((this.d / 2) - (this.c / 2));
        }
        invalidate();
    }
}
