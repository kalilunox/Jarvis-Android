package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SmallViews.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/jarvis/app/ui/RingView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "skin", "Lcom/jarvis/app/ui/Skin;", "caption", "", "<init>", "(Landroid/content/Context;Lcom/jarvis/app/ui/Skin;Ljava/lang/String;)V", "v", "", "percent", "getPercent", "()I", "setPercent", "(I)V", "ring", "Landroid/graphics/Paint;", "big", "Landroid/text/TextPaint;", "small", "box", "Landroid/graphics/RectF;", "onDraw", "", "c", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RingView extends View {
    private final TextPaint big;
    private final RectF box;
    private final String caption;
    private int percent;
    private final Paint ring;
    private final Skin skin;
    private final TextPaint small;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RingView(Context context, Skin skin, String caption) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skin, "skin");
        Intrinsics.checkNotNullParameter(caption, "caption");
        this.skin = skin;
        this.caption = caption;
        Paint $this$ring_u24lambda_u240 = new Paint(1);
        $this$ring_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$ring_u24lambda_u240.setStrokeCap(Paint.Cap.ROUND);
        $this$ring_u24lambda_u240.setStrokeWidth(UiKt.dp(context, 6));
        this.ring = $this$ring_u24lambda_u240;
        TextPaint $this$big_u24lambda_u240 = new TextPaint(1);
        $this$big_u24lambda_u240.setColor(this.skin.getText());
        $this$big_u24lambda_u240.setTextAlign(Paint.Align.CENTER);
        $this$big_u24lambda_u240.setTypeface(Fonts.display$default(Fonts.INSTANCE, context, 0, 2, null));
        $this$big_u24lambda_u240.setTextSize(UiKt.dp(context, 17));
        this.big = $this$big_u24lambda_u240;
        TextPaint $this$small_u24lambda_u240 = new TextPaint(1);
        $this$small_u24lambda_u240.setColor(this.skin.getMuted());
        $this$small_u24lambda_u240.setTextAlign(Paint.Align.CENTER);
        $this$small_u24lambda_u240.setTypeface(Fonts.body$default(Fonts.INSTANCE, context, 0, 2, null));
        $this$small_u24lambda_u240.setTextSize(UiKt.dp(context, 10.5f));
        this.small = $this$small_u24lambda_u240;
        this.box = new RectF();
    }

    public final int getPercent() {
        return this.percent;
    }

    public final void setPercent(int v) {
        this.percent = v;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas c) {
        Intrinsics.checkNotNullParameter(c, "c");
        float m = this.ring.getStrokeWidth();
        float s = Math.min(getWidth(), getHeight());
        float f = 2;
        float x = (getWidth() - s) / f;
        float y = (getHeight() - s) / f;
        this.box.set(x + m, y + m, (x + s) - m, (y + s) - m);
        this.ring.setColor(UiKt.alpha(this.skin.getText(), 0.1f));
        c.drawArc(this.box, 0.0f, 360.0f, false, this.ring);
        this.ring.setColor(this.skin.getAccent());
        c.drawArc(this.box, -90.0f, this.percent * 3.6f, false, this.ring);
        String str = this.percent + "%";
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        c.drawText(str, width, height + UiKt.dp(context, 2), this.big);
        String str2 = this.caption;
        float width2 = getWidth() / 2.0f;
        float height2 = getHeight() / 2.0f;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        c.drawText(str2, width2, height2 + UiKt.dp(context2, 16), this.small);
    }
}
