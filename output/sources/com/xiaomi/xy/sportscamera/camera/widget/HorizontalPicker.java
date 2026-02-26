package com.xiaomi.xy.sportscamera.camera.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import com.xiaomi.xy.sportscamera.R;
import com.yiaction.common.util.g;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes3.dex */
public class HorizontalPicker extends View {
    private int A;
    private float B;
    private int C;
    private TextDirectionHeuristicCompat D;
    private final c E;
    private float F;
    private int G;
    private boolean H;

    /* renamed from: a, reason: collision with root package name */
    float f4788a;
    private VelocityTracker b;
    private int c;
    private int d;
    private final int e;
    private int f;
    private CharSequence[] g;
    private BoringLayout[] h;
    private TextPaint i;
    private BoringLayout.Metrics j;
    private TextUtils.TruncateAt k;
    private int l;
    private RectF m;
    private RectF n;
    private float o;
    private OverScroller p;
    private OverScroller q;
    private int r;
    private boolean s;
    private int t;
    private ColorStateList u;
    private b v;
    private int w;
    private EdgeEffect x;
    private EdgeEffect y;
    private a z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<HorizontalPicker> f4789a;
        private final WeakReference<Layout> b;
        private byte c = 0;
        private final float d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;
        private int j;
        private float k;
        private boolean l;

        a(HorizontalPicker horizontalPicker, Layout layout, boolean z) {
            float f = (horizontalPicker.getContext().getResources().getDisplayMetrics().density * 30.0f) / 33.0f;
            if (z) {
                this.d = -f;
            } else {
                this.d = f;
            }
            this.f4789a = new WeakReference<>(horizontalPicker);
            this.b = new WeakReference<>(layout);
            this.l = z;
        }

        private void f() {
            this.k = 0.0f;
            HorizontalPicker horizontalPicker = this.f4789a.get();
            if (horizontalPicker != null) {
                horizontalPicker.invalidate();
            }
        }

        void a() {
            if (this.c != 2) {
                return;
            }
            removeMessages(2);
            HorizontalPicker horizontalPicker = this.f4789a.get();
            Layout layout = this.b.get();
            if (horizontalPicker == null || layout == null) {
                return;
            }
            if (horizontalPicker.isFocused() || horizontalPicker.isSelected()) {
                this.k += this.d;
                if (Math.abs(this.k) > this.e) {
                    this.k = this.e;
                    if (this.l) {
                        this.k *= -1.0f;
                    }
                    sendEmptyMessageDelayed(3, 1200L);
                } else {
                    sendEmptyMessageDelayed(2, 33L);
                }
                horizontalPicker.invalidate();
            }
        }

        void a(int i) {
            if (i == 0) {
                b();
                return;
            }
            this.j = i;
            HorizontalPicker horizontalPicker = this.f4789a.get();
            Layout layout = this.b.get();
            if (horizontalPicker == null || layout == null) {
                return;
            }
            this.c = (byte) 1;
            this.k = 0.0f;
            int i2 = horizontalPicker.l;
            float lineWidth = layout.getLineWidth(0);
            float f = i2 / 3.0f;
            this.g = (lineWidth - i2) + f;
            this.e = this.g + i2;
            this.h = f + lineWidth;
            this.i = (i2 / 6.0f) + lineWidth;
            this.f = lineWidth + this.g + lineWidth;
            if (this.l) {
                this.h *= -1.0f;
            }
            horizontalPicker.invalidate();
            sendEmptyMessageDelayed(1, 1200L);
        }

        void b() {
            this.c = (byte) 0;
            removeMessages(1);
            removeMessages(3);
            removeMessages(2);
            f();
        }

        float c() {
            return this.h;
        }

        float d() {
            return this.k;
        }

        boolean e() {
            return this.c == 2 && Math.abs(this.k) > this.g;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.c = (byte) 2;
                    a();
                    return;
                case 2:
                    a();
                    return;
                case 3:
                    if (this.c == 2) {
                        if (this.j >= 0) {
                            this.j--;
                        }
                        a(this.j);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes3.dex */
    public interface b {
        void a(HorizontalPicker horizontalPicker, int i);

        void d(int i);

        void l();

        void m();
    }

    /* loaded from: classes3.dex */
    private static class c extends ExploreByTouchHelper {

        /* renamed from: a, reason: collision with root package name */
        private HorizontalPicker f4790a;

        public c(HorizontalPicker horizontalPicker) {
            super(horizontalPicker);
            this.f4790a = horizontalPicker;
        }

        @Override // android.support.v4.widget.ExploreByTouchHelper
        protected int getVirtualViewAt(float f, float f2) {
            float f3 = this.f4790a.l + this.f4790a.B;
            float scrollX = ((this.f4790a.getScrollX() + f) - (this.f4790a.C * f3)) / f3;
            if (scrollX < 0.0f || scrollX > this.f4790a.g.length) {
                return Integer.MIN_VALUE;
            }
            return (int) scrollX;
        }

        @Override // android.support.v4.widget.ExploreByTouchHelper
        protected void getVisibleVirtualViews(List<Integer> list) {
            float f = this.f4790a.l + this.f4790a.B;
            float scrollX = this.f4790a.getScrollX() - (this.f4790a.C * f);
            int i = (int) (scrollX / f);
            int i2 = (this.f4790a.C * 2) + 1;
            if (scrollX % f != 0.0f) {
                i2++;
            }
            if (i < 0) {
                i2 += i;
                i = 0;
            } else if (i + i2 > this.f4790a.g.length) {
                i2 = this.f4790a.g.length - i;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                list.add(Integer.valueOf(i + i3));
            }
        }

        @Override // android.support.v4.widget.ExploreByTouchHelper
        protected boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            return false;
        }

        @Override // android.support.v4.widget.ExploreByTouchHelper
        protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setContentDescription(this.f4790a.g[i]);
        }

        @Override // android.support.v4.widget.ExploreByTouchHelper
        protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            float f = this.f4790a.l + this.f4790a.B;
            int scrollX = (int) ((f * i) - (this.f4790a.getScrollX() - (this.f4790a.C * f)));
            int i2 = this.f4790a.l + scrollX;
            accessibilityNodeInfoCompat.setContentDescription(this.f4790a.g[i]);
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(scrollX, 0, i2, this.f4790a.getHeight()));
            accessibilityNodeInfoCompat.addAction(16);
        }
    }

    /* loaded from: classes3.dex */
    public static class d extends View.BaseSavedState {

        /* renamed from: a, reason: collision with root package name */
        private int f4791a;

        public d(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalPicker.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selItem=" + this.f4791a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f4791a);
        }
    }

    public HorizontalPicker(Context context) {
        this(context, null);
    }

    public HorizontalPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.horizontalPickerStyle);
    }

    public HorizontalPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new CharSequence[0];
        this.t = -1;
        this.A = 3;
        this.B = 0.0f;
        this.C = 1;
        this.f4788a = 0.0f;
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        this.i = textPaint;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.HorizontalPicker, i, 0);
        this.F = getContext().getResources().getDisplayMetrics().density;
        int i2 = this.C;
        try {
            this.u = obtainStyledAttributes.getColorStateList(1);
            CharSequence[] textArray = obtainStyledAttributes.getTextArray(4);
            int i3 = obtainStyledAttributes.getInt(2, 3);
            this.A = obtainStyledAttributes.getInt(3, this.A);
            this.B = obtainStyledAttributes.getDimension(5, this.B);
            int i4 = obtainStyledAttributes.getInt(6, i2);
            float dimension = obtainStyledAttributes.getDimension(0, -1.0f);
            if (dimension > -1.0f) {
                setTextSize(dimension);
            }
            switch (i3) {
                case 1:
                    setEllipsize(TextUtils.TruncateAt.START);
                    break;
                case 2:
                    setEllipsize(TextUtils.TruncateAt.MIDDLE);
                    break;
                case 3:
                    setEllipsize(TextUtils.TruncateAt.END);
                    break;
                case 4:
                    setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    break;
            }
            Paint.FontMetricsInt fontMetricsInt = this.i.getFontMetricsInt();
            this.j = new BoringLayout.Metrics();
            this.j.ascent = fontMetricsInt.ascent;
            this.j.bottom = fontMetricsInt.bottom;
            this.j.descent = fontMetricsInt.descent;
            this.j.leading = fontMetricsInt.leading;
            this.j.top = fontMetricsInt.top;
            this.j.width = this.l;
            setWillNotDraw(false);
            this.p = new OverScroller(context);
            this.q = new OverScroller(context, new DecelerateInterpolator(2.5f));
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.f = viewConfiguration.getScaledTouchSlop();
            this.c = viewConfiguration.getScaledMinimumFlingVelocity();
            this.d = viewConfiguration.getScaledMaximumFlingVelocity() / 4;
            this.e = viewConfiguration.getScaledOverscrollDistance();
            this.r = Integer.MIN_VALUE;
            setValues(textArray);
            setSideItems(i4);
            this.E = new c(this);
            ViewCompat.setAccessibilityDelegate(this, this.E);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private int a(float f) {
        return e((int) ((getScrollX() - ((this.l + this.B) * (this.C + 0.5f))) + f));
    }

    private void a() {
        if (this.h == null || this.h.length <= 0 || getWidth() <= 0) {
            return;
        }
        for (int i = 0; i < this.h.length; i++) {
            this.h[i].replaceOrMake(this.g[i], this.i, this.l, Layout.Alignment.ALIGN_CENTER, 1.0f, 1.0f, this.j, false, this.k, this.l);
        }
    }

    private void a(int i, int i2) {
        int i3 = (this.C * 2) + 1;
        this.l = (i - (((int) this.B) * (i3 - 1))) / i3;
        this.m = new RectF(0.0f, 0.0f, this.l, i2);
        this.n = new RectF(this.m);
        f(this.w);
        a();
        f();
    }

    private void a(OverScroller overScroller) {
        if (overScroller == this.p) {
            e();
        }
    }

    private boolean a(CharSequence charSequence) {
        if (this.D == null) {
            this.D = getTextDirectionHeuristic();
        }
        return this.D.isRtl(charSequence, 0, charSequence.length());
    }

    private int b(float f) {
        return (int) (f / (this.l + this.B));
    }

    private int b(int i, int i2) {
        int defaultColor;
        int colorForState;
        int i3 = (int) (this.l + this.B);
        float abs = Math.abs((((1.0f * i) % i3) / 2.0f) / (i3 / 2.0f));
        float f = (((double) abs) > 0.5d ? abs - 0.5f : 0.5f - abs) * 2.0f;
        if (this.t == i2) {
            defaultColor = this.u.getColorForState(new int[]{android.R.attr.state_pressed}, this.u.getDefaultColor());
            colorForState = this.H ? this.G : this.u.getColorForState(new int[]{android.R.attr.state_pressed, android.R.attr.state_selected}, defaultColor);
        } else {
            defaultColor = this.u.getDefaultColor();
            colorForState = this.H ? this.G : this.u.getColorForState(new int[]{android.R.attr.state_selected}, defaultColor);
        }
        return ((Integer) new ArgbEvaluator().evaluate(f, Integer.valueOf(colorForState), Integer.valueOf(defaultColor))).intValue();
    }

    private void b() {
        if (this.v != null) {
            this.v.d(getSelectedItem());
        }
        d();
    }

    private int c(int i) {
        int scrollX = getScrollX();
        int defaultColor = this.u.getDefaultColor();
        int i2 = (int) (this.l + this.B);
        return (scrollX <= (i2 * i) - (i2 / 2) || scrollX >= ((i + 1) * i2) - (i2 / 2)) ? i == this.t ? this.H ? this.G : this.u.getColorForState(new int[]{android.R.attr.state_pressed}, defaultColor) : defaultColor : b(scrollX - (i2 / 2), i);
    }

    private void c() {
        OverScroller overScroller = this.p;
        if (overScroller.isFinished()) {
            overScroller = this.q;
            if (overScroller.isFinished()) {
                return;
            }
        }
        OverScroller overScroller2 = overScroller;
        if (overScroller2.computeScrollOffset()) {
            int currX = overScroller2.getCurrX();
            if (this.r == Integer.MIN_VALUE) {
                this.r = overScroller2.getStartX();
            }
            int scrollRange = getScrollRange();
            if (this.r >= 0 && currX < 0) {
                this.x.onAbsorb((int) overScroller2.getCurrVelocity());
            } else if (this.r <= scrollRange && currX > scrollRange) {
                this.y.onAbsorb((int) overScroller2.getCurrVelocity());
            }
            overScrollBy(currX - this.r, 0, this.r, getScrollY(), getScrollRange(), 0, this.e, 0, false);
            this.r = currX;
            if (overScroller2.isFinished()) {
                a(overScroller2);
            }
            postInvalidate();
        }
    }

    private void d() {
        int scrollX = getScrollX();
        int round = Math.round(scrollX / (this.l + (this.B * 1.0f)));
        if (round < 0) {
            round = 0;
        } else if (round > this.g.length) {
            round = this.g.length;
        }
        this.w = round;
        this.q.startScroll(scrollX, 0, (round * (this.l + ((int) this.B))) - scrollX, 0, 800);
        invalidate();
    }

    private void d(int i) {
        this.r = Integer.MIN_VALUE;
        this.p.fling(getScrollX(), getScrollY(), -i, 0, 0, (this.g.length - 1) * ((int) (this.l + this.B)), 0, 0, getWidth() / 2, 0);
        invalidate();
    }

    private int e(int i) {
        g.a("debug_MainControl", "x:" + i + ",mItemWidth:" + this.l + ",mDividerSize:" + this.B, new Object[0]);
        return Math.round(i / (this.l + this.B));
    }

    private void e() {
        d();
        this.s = false;
        if (this.v != null) {
            this.v.m();
            this.v.a(this, e(getScrollX()));
        }
        f();
    }

    private void f() {
        g();
        int selectedItem = getSelectedItem();
        if (this.h == null || this.h.length == 0) {
            return;
        }
        if (selectedItem >= this.h.length) {
            selectedItem = 0;
        }
        BoringLayout boringLayout = this.h[selectedItem];
        if (this.k != TextUtils.TruncateAt.MARQUEE || this.l >= boringLayout.getLineWidth(0)) {
            return;
        }
        this.z = new a(this, boringLayout, a(this.g[selectedItem]));
        this.z.a(this.A);
    }

    private void f(int i) {
        scrollTo((this.l + ((int) this.B)) * i, 0);
        invalidate();
    }

    private int g(int i) {
        int scrollX = getScrollX();
        return h(scrollX + i) - scrollX;
    }

    private void g() {
        if (this.z != null) {
            this.z.b();
            this.z = null;
        }
    }

    private int getScrollRange() {
        if (this.g == null || this.g.length == 0) {
            return 0;
        }
        return Math.max(0, (this.l + ((int) this.B)) * (this.g.length - 1));
    }

    private TextDirectionHeuristicCompat getTextDirectionHeuristic() {
        return TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
    }

    private int h(int i) {
        if (i < 0) {
            return 0;
        }
        return i > (this.l + ((int) this.B)) * (this.g.length + (-1)) ? (this.l + ((int) this.B)) * (this.g.length - 1) : i;
    }

    private void setTextSize(float f) {
        if (f != this.i.getTextSize()) {
            this.i.setTextSize(f);
            requestLayout();
            invalidate();
        }
    }

    public void a(int i) {
        this.p.startScroll(getScrollX(), 0, g((this.l + ((int) this.B)) * i), 0);
        g();
        invalidate();
    }

    public void b(int i) {
        this.H = true;
        this.G = i;
    }

    @Override // android.view.View
    public void computeScroll() {
        c();
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.E.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.k;
    }

    @Override // android.view.View
    public void getFocusedRect(Rect rect) {
        super.getFocusedRect(rect);
    }

    public int getMarqueeRepeatLimit() {
        return this.A;
    }

    public int getSelectedItem() {
        return e(getScrollX());
    }

    public int getSideItems() {
        return this.C;
    }

    public CharSequence[] getValues() {
        return this.g;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF;
        super.onDraw(canvas);
        int saveCount = canvas.getSaveCount();
        canvas.save();
        int i = this.w;
        float f = this.l + this.B;
        canvas.translate(this.C * f, 0.0f);
        for (int i2 = 0; i2 < this.g.length; i2++) {
            int c2 = c(i2);
            this.i.setColor(c2);
            BoringLayout boringLayout = this.h[i2];
            int saveCount2 = canvas.getSaveCount();
            canvas.save();
            float lineWidth = boringLayout.getLineWidth(0);
            float f2 = lineWidth > ((float) this.l) ? a(this.g[i2]) ? 0.0f + ((lineWidth - this.l) / 2.0f) : 0.0f - ((lineWidth - this.l) / 2.0f) : 0.0f;
            if (this.z != null && i2 == i) {
                f2 += this.z.d();
            }
            canvas.translate(-f2, (canvas.getHeight() - boringLayout.getHeight()) / 2);
            if (f2 == 0.0f) {
                rectF = this.m;
            } else {
                RectF rectF2 = this.n;
                rectF2.set(this.m);
                rectF2.offset(f2, 0.0f);
                rectF = rectF2;
            }
            if (c2 != this.u.getDefaultColor()) {
                this.i.setTextSize((28.0f * this.F) / 2.0f);
            } else {
                this.i.setTextSize((28.0f * this.F) / 2.0f);
            }
            canvas.clipRect(rectF);
            boringLayout.draw(canvas);
            if (this.z != null && i2 == i && this.z.e()) {
                canvas.translate(this.z.c(), 0.0f);
                boringLayout.draw(canvas);
            }
            canvas.restoreToCount(saveCount2);
            canvas.translate(f, 0.0f);
        }
        canvas.restoreToCount(saveCount);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i, keyEvent);
        }
        g.a("debug_pick", "------------------ onKeyDown : keyCode = " + i, new Object[0]);
        switch (i) {
            case 21:
                a(-1);
                return true;
            case 22:
                a(1);
                return true;
            case 23:
            case 66:
                b();
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            Paint.FontMetrics fontMetrics = this.i.getFontMetrics();
            size2 = Math.min(size2, ((int) (Math.abs(fontMetrics.descent) + Math.abs(fontMetrics.ascent))) + getPaddingTop() + getPaddingBottom());
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
        if (this.p.isFinished() || !z) {
            return;
        }
        this.p.springBack(i, i2, 0, getScrollRange(), 0, 0);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof d)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        d dVar = (d) parcelable;
        super.onRestoreInstanceState(dVar.getSuperState());
        setSelectedItem(dVar.f4791a);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        d dVar = new d(super.onSaveInstanceState());
        dVar.f4791a = this.w;
        return dVar;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a(i, i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        if (this.b == null) {
            this.b = VelocityTracker.obtain();
        }
        this.b.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        g.a("debug_pick", "------------------ onTouchEvent : action = " + actionMasked, new Object[0]);
        switch (actionMasked) {
            case 0:
                if (this.v != null) {
                    this.v.l();
                }
                if (!this.q.isFinished()) {
                    this.q.forceFinished(true);
                } else if (this.p.isFinished()) {
                    this.s = false;
                } else {
                    this.p.forceFinished(true);
                }
                this.o = motionEvent.getX();
                if (!this.s) {
                    this.t = a(motionEvent.getX());
                }
                invalidate();
                break;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(false);
                VelocityTracker velocityTracker = this.b;
                velocityTracker.computeCurrentVelocity(1000, this.d);
                int xVelocity = (int) velocityTracker.getXVelocity();
                if (!this.s || Math.abs(xVelocity) <= this.c) {
                    float x = motionEvent.getX();
                    if (!this.s) {
                        int b2 = b(x) - this.C;
                        if (b2 == 0) {
                            b();
                        } else {
                            a(b2);
                        }
                    } else if (this.s) {
                        e();
                    }
                } else {
                    d(xVelocity);
                }
                this.b.recycle();
                this.b = null;
                if (this.x != null) {
                    this.x.onRelease();
                    this.y.onRelease();
                    break;
                }
                break;
            case 2:
                getParent().requestDisallowInterceptTouchEvent(true);
                float x2 = motionEvent.getX();
                int i = (int) (this.o - x2);
                if (this.s || Math.abs(i) > this.f) {
                    if (!this.s) {
                        this.t = -1;
                        this.s = true;
                        g();
                        i = 0;
                    }
                    int scrollRange = getScrollRange();
                    if (overScrollBy(i, 0, getScrollX(), 0, scrollRange, 0, this.e, 0, true)) {
                        this.b.clear();
                    }
                    float scrollX = getScrollX() + i;
                    if (scrollX < 0.0f) {
                        if (this.x != null) {
                            this.x.onPull(i / getWidth());
                        }
                        if (this.y != null && !this.y.isFinished()) {
                            this.y.onRelease();
                        }
                    } else if (scrollX > scrollRange) {
                        this.y.onPull(i / getWidth());
                        if (this.x != null && !this.x.isFinished()) {
                            this.x.onRelease();
                        }
                    }
                    this.o = x2;
                    invalidate();
                    break;
                }
                break;
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                this.t = -1;
                invalidate();
                if (this.x != null) {
                    this.x.onRelease();
                    this.y.onRelease();
                    break;
                }
                break;
        }
        return true;
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        super.scrollBy(i, 0);
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
    }

    public void setDefaultItem(int i) {
        this.w = i;
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.k != truncateAt) {
            this.k = truncateAt;
            a();
            invalidate();
        }
    }

    public void setMarqueeRepeatLimit(int i) {
        this.A = i;
    }

    public void setOnItemSelectedListener(b bVar) {
        this.v = bVar;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i) {
        if (i != 2) {
            Context context = getContext();
            this.x = new EdgeEffect(context);
            this.y = new EdgeEffect(context);
        } else {
            this.x = null;
            this.y = null;
        }
        super.setOverScrollMode(i);
    }

    public void setSelectedItem(int i) {
        this.w = i;
        this.t = this.w;
        f(i);
    }

    public void setSideItems(int i) {
        if (this.C < 0) {
            throw new IllegalArgumentException("Number of items on each side must be grater or equal to 0.");
        }
        if (this.C != i) {
            this.C = i;
            a(getWidth(), getHeight());
        }
    }

    public void setValues(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null || this.g == charSequenceArr) {
            return;
        }
        this.g = charSequenceArr;
        this.h = new BoringLayout[this.g.length];
        for (int i = 0; i < this.h.length; i++) {
            this.h[i] = new BoringLayout(this.g[i], this.i, this.l, Layout.Alignment.ALIGN_CENTER, 1.0f, 1.0f, this.j, false, this.k, this.l);
        }
        if (getWidth() > 0) {
            f();
        }
        requestLayout();
        invalidate();
    }
}
