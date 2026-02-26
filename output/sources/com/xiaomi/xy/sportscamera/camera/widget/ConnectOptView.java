package com.xiaomi.xy.sportscamera.camera.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.xiaomi.xy.sportscamera.R;

/* loaded from: classes3.dex */
public class ConnectOptView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f4787a;
    private int b;
    private int c;
    private int d;
    private Paint e;

    public ConnectOptView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ConnectOptView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
        this.d = getResources().getColor(R.color.camera_connect_bg);
        this.e = new Paint();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b > 0) {
            this.e.setStyle(Paint.Style.FILL);
            this.e.setColor(this.d);
            Path path = new Path();
            path.moveTo(0.0f, 30.0f);
            path.lineTo(this.c - 30, 30.0f);
            path.lineTo(this.c, 0.0f);
            path.lineTo(this.c + 30, 30.0f);
            path.lineTo(this.f4787a, 30.0f);
            path.lineTo(this.f4787a, this.b);
            path.lineTo(0.0f, this.b);
            path.close();
            canvas.drawPath(path, this.e);
        }
    }
}
