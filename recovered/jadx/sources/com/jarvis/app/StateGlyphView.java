package com.jarvis.app;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StateGlyphView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\b\u0010 \u001a\u00020\u001fH\u0014J\u0018\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0007H\u0014J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0014J\u0016\u0010\u0014\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\n \u001d*\u0004\u0018\u00010\u001c0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/jarvis/app/StateGlyphView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "density", "", "dp", "v", "paint", "Landroid/graphics/Paint;", "mode", "Lcom/jarvis/app/CoreTheme$GlyphMode;", "getMode", "()Lcom/jarvis/app/CoreTheme$GlyphMode;", "setMode", "(Lcom/jarvis/app/CoreTheme$GlyphMode;)V", "coreColor", "getCoreColor", "()I", "setCoreColor", "(I)V", "animator", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "onAttachedToWindow", "", "onDetachedFromWindow", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "newMode", TypedValues.Custom.S_COLOR, "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StateGlyphView extends View {
    private final ValueAnimator animator;
    private int coreColor;
    private final float density;
    private CoreTheme.GlyphMode mode;
    private final Paint paint;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StateGlyphView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StateGlyphView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StateGlyphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Intrinsics.checkNotNullParameter(context, "context");
        this.density = getResources().getDisplayMetrics().density;
        this.paint = new Paint(1);
        this.mode = CoreTheme.GlyphMode.OFF;
        this.coreColor = CoreTheme.INSTANCE.getBRASS();
        ValueAnimator $this$animator_u24lambda_u240 = ValueAnimator.ofFloat(0.0f, 1.0f);
        $this$animator_u24lambda_u240.setDuration(1000L);
        $this$animator_u24lambda_u240.setRepeatCount(-1);
        $this$animator_u24lambda_u240.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jarvis.app.StateGlyphView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                StateGlyphView.animator$lambda$0$0(this.f$0, valueAnimator);
            }
        });
        this.animator = $this$animator_u24lambda_u240;
    }

    public /* synthetic */ StateGlyphView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final float dp(float v) {
        return this.density * v;
    }

    public final CoreTheme.GlyphMode getMode() {
        return this.mode;
    }

    public final void setMode(CoreTheme.GlyphMode glyphMode) {
        Intrinsics.checkNotNullParameter(glyphMode, "<set-?>");
        this.mode = glyphMode;
    }

    public final int getCoreColor() {
        return this.coreColor;
    }

    public final void setCoreColor(int i) {
        this.coreColor = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animator$lambda$0$0(StateGlyphView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.invalidate();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.animator.isStarted()) {
            return;
        }
        this.animator.start();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.animator.cancel();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float barH = dp(28.0f);
        setMeasuredDimension((int) dp(62.0f), (int) barH);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        float fSin;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float w = getWidth();
        float h = getHeight();
        if (this.mode == CoreTheme.GlyphMode.OFF) {
            return;
        }
        long t = SystemClock.elapsedRealtime();
        int i = 0;
        if (this.mode == CoreTheme.GlyphMode.THINKING) {
            float dotR = dp(3.5f);
            float cx = w / 2.0f;
            while (i < 3) {
                int i2 = i;
                float a = (((t % 1300) + (((long) i2) * 180)) % 1300) / 1300.0f;
                float bounce = ((float) Math.sin(a * 2.0f * 3.1415927f)) * dp(6.0f);
                float y = ((h / 2.0f) - dotR) + bounce;
                this.paint.setColor(CoreTheme.INSTANCE.getVIOLET());
                this.paint.setStyle(Paint.Style.FILL);
                float fDp = cx - dp(12.0f);
                float cx2 = cx;
                float cx3 = i2;
                canvas.drawCircle(fDp + (cx3 * dp(12.0f)), y, dotR, this.paint);
                i++;
                cx = cx2;
            }
            return;
        }
        int i3 = this.mode == CoreTheme.GlyphMode.WAVE_SOFT ? 1 : 0;
        float gap = dp(3.0f);
        float barW = (w - ((12 - 1) * gap)) / 12;
        float maxBarH = dp(i3 != 0 ? 16.0f : 28.0f);
        float minBarH = dp(4.0f);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.coreColor);
        Path path = new Path();
        int i4 = 0;
        while (i4 < 12) {
            float phase = ((t % 1000) / 1000.0f) + (i4 * 0.09f);
            if (i3 != 0) {
                fSin = ((float) Math.sin(phase * 2.0f * 3.1415927f)) * 0.3f;
                f = 0.4f;
            } else {
                f = 0.5f;
                fSin = ((float) Math.sin((phase * 2.0f * 3.1415927f) + (i4 * 0.3f))) * 0.5f;
            }
            float amp = fSin + f;
            float barH = ((maxBarH - minBarH) * amp) + minBarH;
            float x = i4 * (barW + gap);
            float w2 = w;
            float w3 = h - barH;
            float barH2 = x + barW;
            RectF rectF = new RectF(x, w3, barH2, h);
            float top = barW / 2.0f;
            path.addRoundRect(rectF, top, barW / 2.0f, Path.Direction.CW);
            i4++;
            w = w2;
            t = t;
            h = h;
        }
        canvas.drawPath(path, this.paint);
    }

    public final void setMode(CoreTheme.GlyphMode newMode, int color) {
        Intrinsics.checkNotNullParameter(newMode, "newMode");
        if (this.mode == newMode && this.coreColor == color) {
            return;
        }
        this.mode = newMode;
        this.coreColor = color;
        invalidate();
    }
}
