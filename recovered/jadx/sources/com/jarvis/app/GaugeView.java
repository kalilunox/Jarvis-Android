package com.jarvis.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: GaugeView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0014R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/jarvis/app/GaugeView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "v", "", "value", "getValue", "()F", "setValue", "(F)V", "label", "", "getLabel", "()Ljava/lang/String;", "setLabel", "(Ljava/lang/String;)V", "caption", "getCaption", "setCaption", "accent", "", "getAccent", "()I", "setAccent", "(I)V", "paint", "Landroid/graphics/Paint;", "arc", "Landroid/graphics/RectF;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GaugeView extends View {
    private int accent;
    private final RectF arc;
    private String caption;
    private String label;
    private final Paint paint;
    private float value;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public GaugeView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GaugeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.label = "";
        this.caption = "";
        this.accent = Color.parseColor("#62E9F4");
        this.paint = new Paint(1);
        this.arc = new RectF();
    }

    public /* synthetic */ GaugeView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final float getValue() {
        return this.value;
    }

    public final void setValue(float v) {
        this.value = RangesKt.coerceIn(v, 0.0f, 100.0f);
        invalidate();
    }

    public final String getLabel() {
        return this.label;
    }

    public final void setLabel(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.label = str;
    }

    public final String getCaption() {
        return this.caption;
    }

    public final void setCaption(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.caption = str;
    }

    public final int getAccent() {
        return this.accent;
    }

    public final void setAccent(int i) {
        this.accent = i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float cx = getWidth() / 2.0f;
        float cy = getHeight() * 0.46f;
        float r = Math.min(getWidth(), getHeight()) * 0.34f;
        this.arc.set(cx - r, cy - r, cx + r, cy + r);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(r * 0.12f);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setColor(Color.parseColor("#183848"));
        canvas.drawArc(this.arc, 140.0f, 260.0f, false, this.paint);
        this.paint.setColor(this.accent);
        canvas.drawArc(this.arc, 140.0f, (this.value / 100.0f) * 260.0f, false, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.parseColor("#F7F8FB"));
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setTextSize(0.42f * r);
        canvas.drawText(String.valueOf((int) this.value), cx, (0.12f * r) + cy, this.paint);
        this.paint.setColor(this.accent);
        this.paint.setTextSize(0.22f * r);
        canvas.drawText(this.label, cx, (0.95f * r) + cy, this.paint);
        this.paint.setColor(Color.parseColor("#8F9BAE"));
        this.paint.setTextSize(0.18f * r);
        canvas.drawText(this.caption, cx, (1.25f * r) + cy, this.paint);
    }
}
