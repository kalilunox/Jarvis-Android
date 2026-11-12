package com.jarvis.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: JarvisSlider.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0014J\b\u0010'\u001a\u00020\u000bH\u0002J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u000bH\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020+H\u0016J\u0010\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u000201H\u0014R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R(\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/jarvis/app/JarvisSlider;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "v", "", "value", "getValue", "()F", "setValue", "(F)V", "onValueChanged", "Lkotlin/Function1;", "", "getOnValueChanged", "()Lkotlin/jvm/functions/Function1;", "setOnValueChanged", "(Lkotlin/jvm/functions/Function1;)V", "density", "dp", "trackHeight", "knobRadius", "goldColor", "trackOffColor", "borderColor", "trackPaint", "Landroid/graphics/Paint;", "donePaint", "knobPaint", "borderPaint", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "knobX", "setFromTouch", "x", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "performClick", "onDraw", "canvas", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisSlider extends View {
    private final int borderColor;
    private final Paint borderPaint;
    private final float density;
    private final Paint donePaint;
    private final int goldColor;
    private final Paint knobPaint;
    private final float knobRadius;
    private Function1<? super Float, Unit> onValueChanged;
    private final float trackHeight;
    private final int trackOffColor;
    private final Paint trackPaint;
    private float value;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JarvisSlider(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JarvisSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JarvisSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Intrinsics.checkNotNullParameter(context, "context");
        this.value = 0.6f;
        this.density = getResources().getDisplayMetrics().density;
        this.trackHeight = dp(3.0f);
        this.knobRadius = dp(9.0f);
        this.goldColor = Color.parseColor("#E0AD55");
        this.trackOffColor = Color.parseColor("#202832");
        this.borderColor = Color.parseColor("#2B333D");
        this.trackPaint = new Paint(1);
        this.donePaint = new Paint(1);
        Paint $this$knobPaint_u24lambda_u240 = new Paint(1);
        $this$knobPaint_u24lambda_u240.setColor(this.goldColor);
        this.knobPaint = $this$knobPaint_u24lambda_u240;
        Paint $this$borderPaint_u24lambda_u240 = new Paint(1);
        $this$borderPaint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$borderPaint_u24lambda_u240.setStrokeWidth(dp(1.0f));
        $this$borderPaint_u24lambda_u240.setColor(this.borderColor);
        this.borderPaint = $this$borderPaint_u24lambda_u240;
        setClickable(true);
        setFocusable(true);
    }

    public /* synthetic */ JarvisSlider(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final float getValue() {
        return this.value;
    }

    public final void setValue(float v) {
        float clamped = RangesKt.coerceIn(v, 0.0f, 1.0f);
        if (!(this.value == clamped)) {
            this.value = clamped;
            invalidate();
            Function1<? super Float, Unit> function1 = this.onValueChanged;
            if (function1 != null) {
                function1.invoke(Float.valueOf(clamped));
            }
        }
    }

    public final Function1<Float, Unit> getOnValueChanged() {
        return this.onValueChanged;
    }

    public final void setOnValueChanged(Function1<? super Float, Unit> function1) {
        this.onValueChanged = function1;
    }

    private final float dp(float v) {
        return this.density * v;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = View.resolveSize((int) dp(200.0f), widthMeasureSpec);
        int h = View.resolveSize((int) ((this.knobRadius * 2) + dp(8.0f)), heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    private final float knobX() {
        float usable = getWidth() - (this.knobRadius * 2);
        return this.knobRadius + (this.value * usable);
    }

    private final void setFromTouch(float x) {
        float usable = getWidth() - (this.knobRadius * 2);
        float raw = (x - this.knobRadius) / usable;
        setValue(RangesKt.coerceIn(raw, 0.0f, 1.0f));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        switch (event.getActionMasked()) {
            case 0:
                performClick();
                setFromTouch(event.getX());
                return true;
            case 1:
            default:
                return super.onTouchEvent(event);
            case 2:
                setFromTouch(event.getX());
                return true;
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float cy = getHeight() / 2.0f;
        float left = this.knobRadius;
        float right = getWidth() - this.knobRadius;
        this.trackPaint.setColor(this.trackOffColor);
        float f = 2;
        canvas.drawRoundRect(new RectF(left, cy - (this.trackHeight / f), right, (this.trackHeight / f) + cy), this.trackHeight, this.trackHeight, this.trackPaint);
        canvas.drawRoundRect(new RectF(left, cy - (this.trackHeight / f), right, (this.trackHeight / f) + cy), this.trackHeight, this.trackHeight, this.borderPaint);
        float kx = knobX();
        if (kx > left) {
            this.donePaint.setColor(this.goldColor);
            canvas.drawRoundRect(new RectF(left, cy - (this.trackHeight / f), kx, (this.trackHeight / f) + cy), this.trackHeight, this.trackHeight, this.donePaint);
        }
        canvas.drawCircle(kx, cy, this.knobRadius, this.knobPaint);
        float f2 = this.knobRadius * 0.35f;
        Paint $this$onDraw_u24lambda_u240 = new Paint(1);
        $this$onDraw_u24lambda_u240.setColor(Color.parseColor("#1A1822"));
        Unit unit = Unit.INSTANCE;
        canvas.drawCircle(kx, cy, f2, $this$onDraw_u24lambda_u240);
    }
}
