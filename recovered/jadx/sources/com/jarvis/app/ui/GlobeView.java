package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* JADX INFO: compiled from: SmallViews.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/app/ui/GlobeView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "skin", "Lcom/jarvis/app/ui/Skin;", "<init>", "(Landroid/content/Context;Lcom/jarvis/app/ui/Skin;)V", "land", "", "Lkotlin/Pair;", "", "paint", "Landroid/graphics/Paint;", "grid", "oval", "Landroid/graphics/RectF;", "onDraw", "", "c", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GlobeView extends View {
    private final Paint grid;
    private final List<Pair<Float, Float>> land;
    private final RectF oval;
    private final Paint paint;
    private final Skin skin;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlobeView(Context context, Skin skin) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skin, "skin");
        this.skin = skin;
        List $this$land_u24lambda_u240 = CollectionsKt.createListBuilder();
        for (int i = 0; i < 620; i++) {
            float f = 1;
            float la = (float) Math.asin((2 * Random.INSTANCE.nextFloat()) - f);
            float lo = Random.INSTANCE.nextFloat() * 6.2832f;
            if (((float) Math.sin((2.3f * lo) + f)) + ((float) Math.cos(3.4f * la)) + (((float) Math.sin((5 * lo) + (4 * la))) * 0.5f) > 0.45f) {
                $this$land_u24lambda_u240.add(TuplesKt.to(Float.valueOf(la), Float.valueOf(lo)));
            }
        }
        this.land = CollectionsKt.build($this$land_u24lambda_u240);
        this.paint = new Paint(1);
        Paint $this$grid_u24lambda_u240 = new Paint(1);
        $this$grid_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$grid_u24lambda_u240.setStrokeWidth(1.0f);
        this.grid = $this$grid_u24lambda_u240;
        this.oval = new RectF();
    }

    @Override // android.view.View
    protected void onDraw(Canvas c) {
        Intrinsics.checkNotNullParameter(c, "c");
        float t = SystemClock.uptimeMillis() / 7000.0f;
        float cx = getWidth() / 2.0f;
        float cy = getHeight() / 2.0f;
        float r = Math.min(getWidth(), getHeight()) * 0.44f;
        float f = 0.3f;
        this.grid.setColor(UiKt.alpha(this.skin.getAccent(), 0.3f));
        for (int i = -3; i < 4; i++) {
            float la = (i / 4.0f) * 1.57f;
            float rx = ((float) Math.cos(la)) * r;
            float y = (((float) Math.sin(la)) * r) + cy;
            this.oval.set(cx - rx, y - (rx * 0.12f), cx + rx, (0.12f * rx) + y);
            c.drawOval(this.oval, this.grid);
        }
        for (int i2 = 0; i2 < 6; i2++) {
            float k = Math.abs((float) Math.cos(((i2 / 6.0f) * 3.1416f) + t)) * r;
            this.oval.set(cx - k, cy - r, cx + k, cy + r);
            c.drawOval(this.oval, this.grid);
        }
        Iterable $this$forEach$iv = this.land;
        int $i$f$forEach = 0;
        for (Object element$iv : $this$forEach$iv) {
            Pair pair = (Pair) element$iv;
            float la2 = ((Number) pair.component1()).floatValue();
            float lo = ((Number) pair.component2()).floatValue();
            float a = lo + t;
            float z = ((float) Math.cos(la2)) * ((float) Math.cos(a));
            if (z >= 0.0f) {
                this.paint.setColor(UiKt.alpha(this.skin.getCyan(), (0.6f * z) + f));
                float f2 = 3;
                c.drawRect(cx + (((float) Math.cos(la2)) * ((float) Math.sin(a)) * r), cy + (((float) Math.sin(la2)) * r), (((float) Math.cos(la2)) * ((float) Math.sin(a)) * r) + cx + f2, (((float) Math.sin(la2)) * r) + cy + f2, this.paint);
            }
            $this$forEach$iv = $this$forEach$iv;
            $i$f$forEach = $i$f$forEach;
            f = 0.3f;
        }
        this.grid.setColor(UiKt.alpha(this.skin.getCyan(), 0.8f));
        c.drawCircle(cx, cy, r, this.grid);
        if (isShown()) {
            postInvalidateDelayed(33L);
        }
    }
}
