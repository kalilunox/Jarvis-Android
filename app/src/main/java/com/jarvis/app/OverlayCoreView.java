package com.jarvis.app;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import com.jarvis.core.model.AssistantState;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayCoreView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0014R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/jarvis/app/OverlayCoreView;", "Landroid/view/View;", "Landroid/view/Choreographer$FrameCallback;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "assistantState", "Lcom/jarvis/core/model/AssistantState;", "getAssistantState", "()Lcom/jarvis/core/model/AssistantState;", "setAssistantState", "(Lcom/jarvis/core/model/AssistantState;)V", "paint", "Landroid/graphics/Paint;", "glow", "Landroid/graphics/BlurMaskFilter;", "running", "", "t0", "", "onAttachedToWindow", "", "onDetachedFromWindow", "doFrame", "frameTimeNanos", "onDraw", "canvas", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OverlayCoreView extends View implements Choreographer.FrameCallback {
    private AssistantState assistantState;
    private final BlurMaskFilter glow;
    private final Paint paint;
    private boolean running;
    private final long t0;

    /* JADX INFO: compiled from: OverlayCoreView.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistantState.values().length];
            try {
                iArr[AssistantState.LISTENING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistantState.THINKING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistantState.EXECUTING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AssistantState.SPEAKING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public OverlayCoreView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlayCoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.assistantState = AssistantState.READY;
        this.paint = new Paint(1);
        this.glow = new BlurMaskFilter(14.0f, BlurMaskFilter.Blur.NORMAL);
        this.t0 = SystemClock.uptimeMillis();
        setLayerType(1, null);
    }

    public /* synthetic */ OverlayCoreView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final AssistantState getAssistantState() {
        return this.assistantState;
    }

    public final void setAssistantState(AssistantState assistantState) {
        Intrinsics.checkNotNullParameter(assistantState, "<set-?>");
        this.assistantState = assistantState;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.running = true;
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.running = false;
        Choreographer.getInstance().removeFrameCallback(this);
        super.onDetachedFromWindow();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long frameTimeNanos) {
        if (this.running) {
            invalidate();
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Triple triple;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float t = (SystemClock.uptimeMillis() - this.t0) / 1000.0f;
        float cx = getWidth() / 2.0f;
        float cy = getHeight() / 2.0f;
        float r = Math.min(getWidth(), getHeight()) * 0.36f;
        int i = WhenMappings.$EnumSwitchMapping$0[this.assistantState.ordinal()];
        Float fValueOf = Float.valueOf(0.04f);
        switch (i) {
            case 1:
                triple = new Triple(Integer.valueOf(Color.parseColor("#7AF6FF")), Float.valueOf(1.4f), Float.valueOf(0.08f));
                break;
            case 2:
            case 3:
                triple = new Triple(Integer.valueOf(Color.parseColor("#F3BB58")), Float.valueOf(3.2f), fValueOf);
                break;
            case 4:
                triple = new Triple(Integer.valueOf(Color.parseColor("#62E9F4")), Float.valueOf(0.8f), Float.valueOf((((float) Math.sin(8.0f * t)) * 0.08f) + 0.16f));
                break;
            default:
                triple = new Triple(Integer.valueOf(Color.parseColor("#62E9F4")), Float.valueOf(0.45f), fValueOf);
                break;
        }
        int ring = ((Number) triple.component1()).intValue();
        float spin = ((Number) triple.component2()).floatValue();
        float pulse = ((Number) triple.component3()).floatValue();
        float beat = (((float) Math.sin((this.assistantState == AssistantState.SPEAKING ? 7.0f : 2.0f) * t)) * pulse) + 1.0f;
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setMaskFilter(this.glow);
        this.paint.setColor(ring);
        this.paint.setAlpha(70);
        canvas.drawCircle(cx, cy, 1.35f * r * beat, this.paint);
        this.paint.setMaskFilter(null);
        this.paint.setAlpha(255);
        this.paint.setShader(new SweepGradient(cx, cy, new int[]{ring, 0, ring}, new float[]{0.0f, 0.55f, 1.0f}));
        canvas.save();
        canvas.rotate(80.0f * t * spin, cx, cy);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(2.4f);
        canvas.drawCircle(cx, cy, r, this.paint);
        this.paint.setShader(null);
        canvas.restore();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(-1);
        canvas.drawCircle(cx, cy, 0.22f * r * beat, this.paint);
        this.paint.setColor(ring);
        this.paint.setAlpha(180);
        canvas.drawCircle((((float) Math.cos(t * spin)) * r * 0.72f) + cx, (((float) Math.sin(t * spin)) * r * 0.72f) + cy, 3.2f, this.paint);
    }
}
