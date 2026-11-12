package com.jarvis.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.storage.FactPolicies;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TelemetryChartView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nJ\u0016\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nJ\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J6\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\nH\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/jarvis/app/TelemetryChartView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "cpu", "Lkotlin/collections/ArrayDeque;", "", "ram", "paint", "Landroid/graphics/Paint;", "path", "Landroid/graphics/Path;", "fill", "density", FactPolicies.SEED_SOURCE, "", "cpuBase", "ramBase", "push", "cpuLoad", "ramLoad", "onDraw", "canvas", "Landroid/graphics/Canvas;", "drawSeries", "data", TypedValues.Custom.S_COLOR, "", "fillColor", "topPad", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TelemetryChartView extends View {
    private final ArrayDeque<Float> cpu;
    private final float density;
    private final Path fill;
    private final Paint paint;
    private final Path path;
    private final ArrayDeque<Float> ram;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TelemetryChartView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelemetryChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.cpu = new ArrayDeque<>();
        this.ram = new ArrayDeque<>();
        this.paint = new Paint(1);
        this.path = new Path();
        this.fill = new Path();
        this.density = getResources().getDisplayMetrics().density;
        setLayerType(1, null);
    }

    public /* synthetic */ TelemetryChartView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final void seed(float cpuBase, float ramBase) {
        this.cpu.clear();
        this.ram.clear();
        for (int i = 0; i < 30; i++) {
            this.cpu.addLast(Float.valueOf(RangesKt.coerceIn(((Random.INSTANCE.nextFloat() * 6.0f) + cpuBase) - 3.0f, 0.0f, 100.0f)));
            this.ram.addLast(Float.valueOf(RangesKt.coerceIn(((Random.INSTANCE.nextFloat() * 6.0f) + ramBase) - 3.0f, 0.0f, 100.0f)));
        }
        invalidate();
    }

    public final void push(float cpuLoad, float ramLoad) {
        if (this.cpu.size() >= 30) {
            this.cpu.removeFirst();
        }
        if (this.ram.size() >= 30) {
            this.ram.removeFirst();
        }
        this.cpu.addLast(Float.valueOf(RangesKt.coerceIn(cpuLoad, 0.0f, 100.0f)));
        this.ram.addLast(Float.valueOf(RangesKt.coerceIn(ramLoad, 0.0f, 100.0f)));
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int grid = Color.argb(31, 0, 229, 255);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setColor(grid);
        this.paint.setShader(null);
        this.paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.paint.setPathEffect(new DashPathEffect(new float[]{4.0f, 6.0f}, 0.0f));
        for (int i = 1; i < 5; i++) {
            float y = (getHeight() * i) / 5.0f;
            canvas.drawLine(0.0f, y, getWidth(), y, this.paint);
        }
        for (int i2 = 1; i2 < 7; i2++) {
            float x = (getWidth() * i2) / 7.0f;
            canvas.drawLine(x, 0.0f, x, getHeight(), this.paint);
        }
        this.paint.setPathEffect(null);
        float legendTop = this.density * 8.0f;
        drawSeries(canvas, this.cpu, Color.parseColor("#00E5FF"), Color.argb(56, 0, 229, 255), legendTop);
        drawSeries(canvas, this.ram, Color.parseColor("#FFC400"), Color.argb(46, 255, 196, 0), legendTop);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.paint.setTextSize(this.density * 9.0f);
        this.paint.setColor(Color.parseColor("#00E5FF"));
        canvas.drawText("● CPU %", getWidth() - (this.density * 92.0f), this.density * 14.0f, this.paint);
        this.paint.setColor(Color.parseColor("#FFC400"));
        canvas.drawText("● RAM %", getWidth() - (this.density * 46.0f), this.density * 14.0f, this.paint);
    }

    private final void drawSeries(Canvas canvas, ArrayDeque<Float> data, int color, int fillColor, float topPad) {
        if (data.size() < 2) {
            return;
        }
        this.path.reset();
        float right = getWidth() - 8.0f;
        float bottom = getHeight() - 6.0f;
        float span = data.size() - 1;
        ArrayDeque<Float> $this$forEachIndexed$iv = data;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            float v = ((Number) item$iv).floatValue();
            float x = i * (right / span);
            float y = bottom - ((v / 100.0f) * ((bottom - topPad) - 4.0f));
            if (i == 0) {
                this.path.moveTo(x, y);
            } else {
                this.path.lineTo(x, y);
            }
            i = index$iv;
            span = span;
        }
        this.fill.set(this.path);
        this.fill.lineTo(right, bottom);
        this.fill.lineTo(0.0f, bottom);
        this.fill.close();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setShader(new LinearGradient(0.0f, topPad, 0.0f, bottom, fillColor, 0, Shader.TileMode.CLAMP));
        canvas.drawPath(this.fill, this.paint);
        this.paint.setShader(null);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.density * 2.5f);
        this.paint.setColor(color);
        this.paint.setShadowLayer(this.density * 10.0f, 0.0f, 0.0f, color);
        canvas.drawPath(this.path, this.paint);
    }
}
