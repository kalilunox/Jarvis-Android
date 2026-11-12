package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SmallViews.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/jarvis/app/ui/WaveView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "skin", "Lcom/jarvis/app/ui/Skin;", "<init>", "(Landroid/content/Context;Lcom/jarvis/app/ui/Skin;)V", "level", "", "getLevel", "()F", "setLevel", "(F)V", "paint", "Landroid/graphics/Paint;", "onDraw", "", "c", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WaveView extends View {
    private float level;
    private final Paint paint;
    private final Skin skin;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WaveView(Context context, Skin skin) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skin, "skin");
        this.skin = skin;
        this.paint = new Paint(1);
    }

    public final float getLevel() {
        return this.level;
    }

    public final void setLevel(float f) {
        this.level = f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas c) {
        Intrinsics.checkNotNullParameter(c, "c");
        float t = SystemClock.uptimeMillis();
        float bw = getWidth() / 34;
        float mid = getHeight() / 2.0f;
        for (int i = 0; i < 34; i++) {
            float e = (float) Math.sin((i * 3.1415927f) / (34 - 1));
            float b = 2 + (getHeight() * e * 0.46f * this.level * ((Math.abs(((float) Math.sin((t / 170.0f) + (i * 0.7f))) * ((float) Math.sin((t / 390.0f) + (i * 0.23f)))) * 0.65f) + 0.35f));
            this.paint.setColor(UiKt.alpha(this.skin.getCyan(), (0.5f * e) + 0.45f));
            c.drawRect(i * bw, mid - b, (i * bw) + RangesKt.coerceAtLeast(0.42f * bw, 1.6f), mid + b, this.paint);
        }
        if (isShown()) {
            postInvalidateDelayed(33L);
        }
    }
}
