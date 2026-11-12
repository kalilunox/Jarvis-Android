package com.jarvis.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EqualizerView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001aH\u0014J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!H\u0014R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/jarvis/app/EqualizerView;", "Landroid/view/View;", "Landroid/view/Choreographer$FrameCallback;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "active", "", "getActive", "()Z", "setActive", "(Z)V", "accent", "", "getAccent", "()I", "setAccent", "(I)V", "paint", "Landroid/graphics/Paint;", "running", "bars", "onAttachedToWindow", "", "onDetachedFromWindow", "doFrame", "frameTimeNanos", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EqualizerView extends View implements Choreographer.FrameCallback {
    private int accent;
    private boolean active;
    private final int bars;
    private final Paint paint;
    private boolean running;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EqualizerView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EqualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.accent = Color.parseColor("#62E9F4");
        this.paint = new Paint(1);
        this.bars = 24;
    }

    public /* synthetic */ EqualizerView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final boolean getActive() {
        return this.active;
    }

    public final void setActive(boolean z) {
        this.active = z;
    }

    public final int getAccent() {
        return this.accent;
    }

    public final void setAccent(int i) {
        this.accent = i;
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
        double dSin;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        long t = SystemClock.uptimeMillis();
        float gap = getWidth() / (this.bars * 3.0f);
        float barW = (getWidth() - ((this.bars + 1) * gap)) / this.bars;
        this.paint.setColor(this.accent);
        int i = this.bars;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.active) {
                dSin = (Math.sin((t / 90.0d) + (((double) i2) * 0.55d)) + 1.0d) / 2.0d;
            } else {
                dSin = (((Math.sin((t / 700.0d) + (((double) i2) * 0.4d)) + 1.0d) / 2.0d) * 0.1d) + 0.08d;
            }
            double wave = dSin;
            float h = (float) (((double) getHeight()) * (((double) 0.12f) + (((double) 0.84f) * wave)));
            float x = gap + (i2 * (barW + gap));
            this.paint.setAlpha(this.active ? 230 : 120);
            canvas.drawRect(x, getHeight() - h, x + barW, getHeight(), this.paint);
        }
    }
}
