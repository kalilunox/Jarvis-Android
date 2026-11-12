package com.jarvis.app;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisToggle.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0002J\b\u00104\u001a\u00020\u0012H\u0002J(\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u0007H\u0014J\u0018\u0010:\u001a\u00020\u00122\u0006\u0010;\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u0007H\u0014J\u0010\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020?H\u0014R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001dR\u000e\u0010$\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u001dR\u0014\u00100\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u0010\u001dR\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/jarvis/app/JarvisToggle;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "", "isChecked", "()Z", "setChecked", "(Z)V", "onCheckedChanged", "Lkotlin/Function1;", "", "getOnCheckedChanged", "()Lkotlin/jvm/functions/Function1;", "setOnCheckedChanged", "(Lkotlin/jvm/functions/Function1;)V", "density", "", "dp", "v", "trackWidth", "getTrackWidth", "()F", "trackHeight", "getTrackHeight", "knobSize", "getKnobSize", "knobPadding", "getKnobPadding", "cyan", "knobOffColor", "trackOffColor", "borderColor", "trackPaint", "Landroid/graphics/Paint;", "borderPaint", "onBorderPaint", "knobPaint", "knobX", "knobOff", "getKnobOff", "knobOn", "getKnobOn", "knobAnimator", "Landroid/animation/ValueAnimator;", "updateAnimator", "onSizeChanged", "w", "h", "oldw", "oldh", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisToggle extends View {
    private final int borderColor;
    private final Paint borderPaint;
    private final int cyan;
    private final float density;
    private boolean isChecked;
    private ValueAnimator knobAnimator;
    private final int knobOffColor;
    private final Paint knobPaint;
    private float knobX;
    private final Paint onBorderPaint;
    private Function1<? super Boolean, Unit> onCheckedChanged;
    private final int trackOffColor;
    private final Paint trackPaint;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JarvisToggle(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JarvisToggle(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JarvisToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Intrinsics.checkNotNullParameter(context, "context");
        this.density = getResources().getDisplayMetrics().density;
        this.cyan = Color.parseColor("#62E9F4");
        this.knobOffColor = Color.parseColor("#8F9BAE");
        this.trackOffColor = Color.parseColor("#101820");
        this.borderColor = Color.parseColor("#3347EAFF");
        this.trackPaint = new Paint(1);
        Paint $this$borderPaint_u24lambda_u240 = new Paint(1);
        $this$borderPaint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$borderPaint_u24lambda_u240.setStrokeWidth(dp(1.0f));
        $this$borderPaint_u24lambda_u240.setColor(this.borderColor);
        this.borderPaint = $this$borderPaint_u24lambda_u240;
        Paint $this$onBorderPaint_u24lambda_u240 = new Paint(1);
        $this$onBorderPaint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$onBorderPaint_u24lambda_u240.setStrokeWidth(dp(1.0f));
        $this$onBorderPaint_u24lambda_u240.setColor(this.cyan);
        this.onBorderPaint = $this$onBorderPaint_u24lambda_u240;
        this.knobPaint = new Paint(1);
        setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.JarvisToggle$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JarvisToggle._init_$lambda$0(this.f$0, view);
            }
        });
    }

    public /* synthetic */ JarvisToggle(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: renamed from: isChecked, reason: from getter */
    public final boolean getIsChecked() {
        return this.isChecked;
    }

    public final void setChecked(boolean value) {
        if (this.isChecked == value) {
            return;
        }
        this.isChecked = value;
        updateAnimator();
        invalidate();
        Function1<? super Boolean, Unit> function1 = this.onCheckedChanged;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(value));
        }
    }

    public final Function1<Boolean, Unit> getOnCheckedChanged() {
        return this.onCheckedChanged;
    }

    public final void setOnCheckedChanged(Function1<? super Boolean, Unit> function1) {
        this.onCheckedChanged = function1;
    }

    private final float dp(float v) {
        return this.density * v;
    }

    private final float getTrackWidth() {
        return dp(38.0f);
    }

    private final float getTrackHeight() {
        return dp(22.0f);
    }

    private final float getKnobSize() {
        return dp(16.0f);
    }

    private final float getKnobPadding() {
        return dp(2.0f);
    }

    private final float getKnobOff() {
        return getKnobPadding() + (getKnobSize() / 2.0f);
    }

    private final float getKnobOn() {
        return (getTrackWidth() - getKnobPadding()) - (getKnobSize() / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(JarvisToggle this$0, View it) {
        this$0.setChecked(!this$0.isChecked);
    }

    private final void updateAnimator() {
        ValueAnimator valueAnimator = this.knobAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        float target = this.isChecked ? getKnobOn() : getKnobOff();
        ValueAnimator $this$updateAnimator_u24lambda_u240 = ValueAnimator.ofFloat(this.knobX, target);
        $this$updateAnimator_u24lambda_u240.setDuration(160L);
        $this$updateAnimator_u24lambda_u240.setInterpolator(new AccelerateDecelerateInterpolator());
        $this$updateAnimator_u24lambda_u240.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jarvis.app.JarvisToggle$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                JarvisToggle.updateAnimator$lambda$0$0(this.f$0, valueAnimator2);
            }
        });
        $this$updateAnimator_u24lambda_u240.start();
        this.knobAnimator = $this$updateAnimator_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateAnimator$lambda$0$0(JarvisToggle this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.knobX = ((Float) animatedValue).floatValue();
        this$0.invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.knobX = this.isChecked ? getKnobOn() : getKnobOff();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) getTrackWidth(), (int) getTrackHeight());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float cornerR = dp(2.0f);
        RectF trackRect = new RectF(0.0f, 0.0f, getTrackWidth(), getTrackHeight());
        this.trackPaint.setColor(this.isChecked ? Color.parseColor("#3362E9F4") : this.trackOffColor);
        canvas.drawRoundRect(trackRect, cornerR, cornerR, this.trackPaint);
        canvas.drawRoundRect(trackRect, cornerR, cornerR, this.borderPaint);
        if (this.isChecked) {
            canvas.drawRoundRect(trackRect, cornerR, cornerR, this.onBorderPaint);
        }
        this.knobPaint.setColor(this.isChecked ? this.cyan : this.knobOffColor);
        RectF knobRect = new RectF(this.knobX - (getKnobSize() / 2.0f), (getTrackHeight() / 2.0f) - (getKnobSize() / 2.0f), this.knobX + (getKnobSize() / 2.0f), (getTrackHeight() / 2.0f) + (getKnobSize() / 2.0f));
        canvas.drawRoundRect(knobRect, dp(1.5f), dp(1.5f), this.knobPaint);
    }
}
