package com.jarvis.app;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.ViewCompat;
import com.google.android.material.internal.ViewUtils;
import com.jarvis.app.ui.HomeScreen;
import com.jarvis.core.model.AssistantState;
import com.jarvis.wakeword.WakeWordEngine;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: CoreView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0002J\b\u0010#\u001a\u00020$H\u0002J\u001e\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0019J\b\u0010)\u001a\u00020$H\u0014J\b\u0010*\u001a\u00020$H\u0014J\u0018\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u0007H\u0014J\u0018\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0002J\u0018\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0019H\u0002J\u0010\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u001bH\u0002J\u0010\u00105\u001a\u00020$2\u0006\u00106\u001a\u000207H\u0014J\\\u00108\u001a\u00020$2\u0006\u00106\u001a\u0002072\u0006\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u001b2\u0006\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u001b2\u0006\u0010?\u001a\u00020\u001b2\b\b\u0002\u0010@\u001a\u00020\u001b2\b\b\u0002\u0010A\u001a\u00020\u0007H\u0002JH\u0010B\u001a\u00020$2\u0006\u00106\u001a\u0002072\u0006\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020\u00072\u0006\u0010E\u001a\u00020\u001b2\u0006\u0010A\u001a\u00020\u0007H\u0002J@\u0010F\u001a\u00020$2\u0006\u00106\u001a\u0002072\u0006\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u001b2\u0006\u0010;\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0019H\u0002J \u0010G\u001a\u00020\u00072\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u00072\u0006\u0010J\u001a\u00020\u001bH\u0002J\u0010\u0010K\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0007H\u0002R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n \"*\u0004\u0018\u00010!0!X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/jarvis/app/CoreView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "", "isCompact", "()Z", "setCompact", "(Z)V", "Lcom/jarvis/core/model/AssistantState;", "state", "getState", "()Lcom/jarvis/core/model/AssistantState;", "setState", "(Lcom/jarvis/core/model/AssistantState;)V", "coreColor", "deepColor", "breatheMs", "", "density", "", "dp", "v", "paint", "Landroid/graphics/Paint;", "animator", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "applyStateColors", "", "setStateColors", "core", "deep", "periodMs", "onAttachedToWindow", "onDetachedFromWindow", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "resolveSizeDefault", "defaultSize", "measureSpec", "single", "time", "ease", "t", "onDraw", "canvas", "Landroid/graphics/Canvas;", "drawArc", "cx", "cy", "r", "rotationDeg", TypedValues.Custom.S_COLOR, "width", "sweepDeg", "offsetDeg", "alpha", "drawOrbitParticle", "radius", "angleDeg", "size", "drawOrb", "mixColor", "base", "blend", "blendFraction", "deepEnd", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreView extends View {
    private final ValueAnimator animator;
    private long breatheMs;
    private int coreColor;
    private int deepColor;
    private final float density;
    private boolean isCompact;
    private final Paint paint;
    private AssistantState state;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CoreView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CoreView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Intrinsics.checkNotNullParameter(context, "context");
        this.state = AssistantState.READY;
        this.coreColor = CoreTheme.INSTANCE.getBRASS();
        this.deepColor = CoreTheme.INSTANCE.getBRASS_DEEP();
        this.breatheMs = 3600L;
        this.density = getResources().getDisplayMetrics().density;
        this.paint = new Paint(1);
        ValueAnimator $this$animator_u24lambda_u240 = ValueAnimator.ofFloat(0.0f, 1.0f);
        $this$animator_u24lambda_u240.setDuration(1000L);
        $this$animator_u24lambda_u240.setRepeatCount(-1);
        $this$animator_u24lambda_u240.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jarvis.app.CoreView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                CoreView.animator$lambda$0$0(this.f$0, valueAnimator);
            }
        });
        this.animator = $this$animator_u24lambda_u240;
        applyStateColors();
    }

    public /* synthetic */ CoreView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: renamed from: isCompact, reason: from getter */
    public final boolean getIsCompact() {
        return this.isCompact;
    }

    public final void setCompact(boolean value) {
        this.isCompact = value;
        requestLayout();
    }

    public final AssistantState getState() {
        return this.state;
    }

    public final void setState(AssistantState value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.state == value) {
            return;
        }
        this.state = value;
        applyStateColors();
        invalidate();
    }

    private final float dp(float v) {
        return this.density * v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animator$lambda$0$0(CoreView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.invalidate();
    }

    private final void applyStateColors() {
        CoreTheme.CoreColors c = CoreTheme.forState$default(CoreTheme.INSTANCE, this.state, 0, 2, null);
        this.coreColor = c.getCore();
        this.deepColor = c.getDeep();
        this.breatheMs = c.getBreatheMs();
    }

    public final void setStateColors(int core, int deep, long periodMs) {
        this.coreColor = core;
        this.deepColor = deep;
        this.breatheMs = periodMs;
        invalidate();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.animator.isStarted()) {
            this.animator.start();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.animator.cancel();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultSize = (int) dp(this.isCompact ? 88.0f : 266.0f);
        int w = resolveSizeDefault(defaultSize, widthMeasureSpec);
        int h = resolveSizeDefault(defaultSize, heightMeasureSpec);
        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    private final int resolveSizeDefault(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case Integer.MIN_VALUE:
                return Math.min(defaultSize, size);
            case BasicMeasure.EXACTLY /* 1073741824 */:
                return size;
            default:
                return defaultSize;
        }
    }

    private final float single(long time, long periodMs) {
        return (time % periodMs) / periodMs;
    }

    private final float ease(float t) {
        return 1.0f - ((1.0f - t) * (1.0f - t));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float width;
        float f;
        long t;
        long t2;
        int core;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        long t3 = SystemClock.elapsedRealtime();
        float cx = getWidth() / 2.0f;
        float cy = getHeight() / 2.0f;
        if (this.isCompact) {
            width = getWidth();
            f = 88.0f;
        } else {
            width = getWidth();
            f = 266.0f;
        }
        float s = width / f;
        int core2 = this.coreColor;
        int deep = this.deepColor;
        float haloR = (this.isCompact ? 44.0f : 105.0f) * s * (((0.5f - (((float) Math.cos(single(t3, 4200L) * 6.2831855f)) * 0.5f)) * 0.13f) + 1.0f);
        int[] haloColors = {CoreTheme.INSTANCE.withAlpha(core2, 48), 0};
        this.paint.setShader(new RadialGradient(cx, cy, haloR, haloColors, new float[]{0.0f, 0.66f}, Shader.TileMode.CLAMP));
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx, cy, haloR, this.paint);
        this.paint.setShader(null);
        float pulseBase = this.isCompact ? 31.0f : 75.0f;
        long pulsePeriod = this.isCompact ? 3000L : 3400L;
        int i = 0;
        while (i < 3) {
            int i2 = i;
            int deep2 = deep;
            long pulsePeriod2 = pulsePeriod;
            float p = single((((long) i2) * (this.isCompact ? 1000L : 1130L)) + t3, pulsePeriod2);
            float r = ((ease(p) * 0.97f) + 0.88f) * pulseBase * s;
            this.paint.setShader(null);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(s * 1.0f);
            this.paint.setColor(CoreTheme.INSTANCE.withAlpha(core2, (int) (85 * (1.0f - p))));
            canvas.drawCircle(cx, cy, r, this.paint);
            i++;
            deep = deep2;
            pulsePeriod = pulsePeriod2;
        }
        int deep3 = deep;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(CoreTheme.INSTANCE.getBORDER());
        this.paint.setShader(null);
        float outerR = (this.isCompact ? 39.0f : 64.0f) * s;
        float innerR = (this.isCompact ? 33.0f : 52.0f) * s;
        this.paint.setStrokeWidth(s * 1.0f);
        canvas.drawCircle(cx, cy, outerR, this.paint);
        this.paint.setPathEffect(new DashPathEffect(new float[]{s * 2.0f, 8.0f * s}, 0.0f));
        int i3 = 140;
        this.paint.setAlpha(140);
        canvas.drawCircle(cx, cy, innerR, this.paint);
        this.paint.setAlpha(255);
        this.paint.setPathEffect(null);
        if (!this.isCompact) {
            float ticksAngle = single(t3, 60000L) * 360.0f;
            dp(RangesKt.coerceAtLeast(s, 1.0f) * 68.0f);
            int i4 = 0;
            while (i4 < 72) {
                float a = ((3.1415927f * ticksAngle) / 180.0f) + (((i4 * 5) * 3.1415927f) / 180.0f);
                boolean z = i4 % 6 == 0;
                float r1 = (z ? 68.0f : 70.0f) * s;
                float r2 = s * 73.0f;
                float haloR2 = haloR;
                float x1 = cx + (((float) Math.cos(a)) * r1);
                float y1 = cy + (((float) Math.sin(a)) * r1);
                float x2 = cx + (((float) Math.cos(a)) * r2);
                float y2 = cy + (((float) Math.sin(a)) * r2);
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setStrokeWidth((z ? 1.4f : 1.0f) * s);
                Paint paint = this.paint;
                CoreTheme coreTheme = CoreTheme.INSTANCE;
                int border = z ? core2 : CoreTheme.INSTANCE.getBORDER();
                if (z) {
                    i3 = 200;
                }
                paint.setColor(coreTheme.withAlpha(border, i3));
                canvas.drawLine(x1, y1, x2, y2, this.paint);
                i4++;
                haloR = haloR2;
                i3 = 140;
            }
        }
        drawArc$default(this, canvas, cx, cy, outerR, single(t3, 26000L) * 360.0f, core2, (this.isCompact ? 2.2f : 2.5f) * s, 67.0f, 0.0f, 0, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
        if (this.isCompact) {
            t = t3;
        } else {
            t = t3;
            drawArc(canvas, cx, cy, outerR, single(t, 26000L) * 360.0f, core2, s * 1.5f, 27.0f, -170.0f, 150);
        }
        long t4 = t;
        drawArc$default(this, canvas, cx, cy, innerR, (-single(t, 14000L)) * 360.0f, CoreTheme.INSTANCE.getTEAL(), (this.isCompact ? 1.6f : 2.0f) * s, 73.0f, 0.0f, 0, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
        if (this.isCompact) {
            t2 = t4;
            drawOrbitParticle(canvas, cx, cy, s * 30.0f, single(t2, WakeWordEngine.DEFAULT_AWAIT_COMMAND_TIMEOUT_MS) * 360.0f, core2, s * 3.0f, 255);
            drawOrbitParticle(canvas, cx, cy, s * 22.0f, (-single(t2, HomeScreen.CLOCK_TICK_MS)) * 360.0f, CoreTheme.INSTANCE.getTEAL(), s * 2.0f, 230);
            core = core2;
        } else {
            t2 = t4;
            drawOrbitParticle(canvas, cx, cy, s * 62.0f, single(t2, WakeWordEngine.DEFAULT_AWAIT_COMMAND_TIMEOUT_MS) * 360.0f, core2, s * 3.0f, 255);
            drawOrbitParticle(canvas, cx, cy, s * 46.0f, (-single(t2, HomeScreen.CLOCK_TICK_MS)) * 360.0f, CoreTheme.INSTANCE.getTEAL(), s * 2.0f, 230);
            core = core2;
            drawOrbitParticle(canvas, cx, cy, s * 84.0f, single(t2, 21000L) * 360.0f, CoreTheme.INSTANCE.withAlpha(core, 230), s * 2.5f, 200);
        }
        drawOrb(canvas, cx, cy, (this.isCompact ? 27.0f : 66.0f) * s, core, deep3, t2);
    }

    static /* synthetic */ void drawArc$default(CoreView coreView, Canvas canvas, float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, int i2, int i3, Object obj) {
        coreView.drawArc(canvas, f, f2, f3, f4, i, f5, f6, (i3 & 256) != 0 ? 0.0f : f7, (i3 & 512) != 0 ? 255 : i2);
    }

    private final void drawArc(Canvas canvas, float cx, float cy, float r, float rotationDeg, int color, float width, float sweepDeg, float offsetDeg, int alpha) {
        RectF rect = new RectF(cx - r, cy - r, cx + r, cy + r);
        canvas.save();
        canvas.rotate(rotationDeg, cx, cy);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(width);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setShader(new SweepGradient(cx, cy, new int[]{CoreTheme.INSTANCE.withAlpha(color, 0), CoreTheme.INSTANCE.withAlpha(color, alpha), CoreTheme.INSTANCE.withAlpha(color, 0)}, new float[]{0.0f, 0.28f, 1.0f}));
        canvas.drawArc(rect, offsetDeg, sweepDeg, false, this.paint);
        this.paint.setShader(null);
        this.paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.restore();
    }

    private final void drawOrbitParticle(Canvas canvas, float cx, float cy, float radius, float angleDeg, int color, float size, int alpha) {
        float a = (3.1415927f * angleDeg) / 180.0f;
        float x = (((float) Math.cos(a)) * radius) + cx;
        float y = (((float) Math.sin(a)) * radius) + cy;
        this.paint.setShader(null);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(CoreTheme.INSTANCE.withAlpha(color, alpha / 2));
        canvas.drawCircle(x, y, 1.9f * size, this.paint);
        this.paint.setColor(CoreTheme.INSTANCE.withAlpha(color, alpha));
        canvas.drawCircle(x, y, size, this.paint);
    }

    private final void drawOrb(Canvas canvas, float cx, float cy, float r, int core, int deep, long t) {
        float breathe = (((float) Math.sin(single(t, RangesKt.coerceAtLeast(this.breatheMs, 1L)) * 6.2831855f)) * 0.045f) + 1.0f;
        float orbR = r * breathe;
        this.paint.setShader(new RadialGradient(cx, cy, orbR * 1.5f, new int[]{CoreTheme.INSTANCE.withAlpha(core, 90), CoreTheme.INSTANCE.withAlpha(core, 24), 0}, new float[]{0.0f, 0.55f, 1.0f}, Shader.TileMode.CLAMP));
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx, cy, orbR * 1.5f, this.paint);
        float lx = cx - (orbR * 0.16f);
        float ly = cy - (orbR * 0.22f);
        RadialGradient body = new RadialGradient(lx, ly, orbR * 1.15f, new int[]{mixColor(core, CoreTheme.INSTANCE.getTEXT(), 0.08f), core, deep, deepEnd(deep)}, new float[]{0.0f, 0.32f, 0.72f, 1.0f}, Shader.TileMode.CLAMP);
        this.paint.setShader(body);
        canvas.drawCircle(cx, cy, orbR, this.paint);
        RadialGradient shade = new RadialGradient(cx, cy, orbR, new int[]{ViewCompat.MEASURED_SIZE_MASK, CoreTheme.INSTANCE.withAlpha(CoreTheme.INSTANCE.getBG_DEEP(), 45), CoreTheme.INSTANCE.withAlpha(CoreTheme.INSTANCE.getBG_DEEP(), 140)}, new float[]{0.55f, 0.85f, 1.0f}, Shader.TileMode.CLAMP);
        this.paint.setShader(shade);
        canvas.drawCircle(cx, cy, orbR, this.paint);
        this.paint.setShader(null);
        this.paint.setColor(CoreTheme.INSTANCE.withAlpha(CoreTheme.INSTANCE.getTEXT(), 46));
        canvas.drawCircle(cx - (orbR * 0.34f), cy - (orbR * 0.4f), orbR * 0.22f, this.paint);
        this.paint.setColor(CoreTheme.INSTANCE.withAlpha(CoreTheme.INSTANCE.getTEXT(), 20));
        canvas.drawCircle(cx - (orbR * 0.24f), cy - (orbR * 0.28f), orbR * 0.42f, this.paint);
        canvas.save();
        canvas.rotate(single(t, 4400L) * 360.0f, cx, cy);
        this.paint.setShader(new SweepGradient(cx, cy, new int[]{0, CoreTheme.INSTANCE.withAlpha(CoreTheme.INSTANCE.getTEXT(), 64), 0}, new float[]{0.0f, 0.1f, 0.3f}));
        canvas.drawCircle(cx, cy, orbR, this.paint);
        this.paint.setShader(null);
        canvas.restore();
    }

    private final int mixColor(int base, int blend, float blendFraction) {
        int r = (int) ((Color.red(base) * (1.0f - blendFraction)) + (Color.red(blend) * blendFraction));
        int g = (int) ((Color.green(base) * (1.0f - blendFraction)) + (Color.green(blend) * blendFraction));
        int b = (int) ((Color.blue(base) * (1.0f - blendFraction)) + (Color.blue(blend) * blendFraction));
        return Color.rgb(r, g, b);
    }

    private final int deepEnd(int deep) {
        return deep;
    }
}
