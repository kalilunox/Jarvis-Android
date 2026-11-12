package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SmallViews.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0014J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/jarvis/app/ui/SwitchView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "skin", "Lcom/jarvis/app/ui/Skin;", "on", "", "<init>", "(Landroid/content/Context;Lcom/jarvis/app/ui/Skin;Z)V", "v", "checked", "getChecked", "()Z", "setChecked", "(Z)V", "p", "Landroid/graphics/Paint;", "box", "Landroid/graphics/RectF;", "onMeasure", "", "w", "", "h", "onDraw", "c", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SwitchView extends View {
    private final RectF box;
    private boolean checked;
    private final Paint p;
    private final Skin skin;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwitchView(Context context, Skin skin, boolean on) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skin, "skin");
        this.skin = skin;
        this.checked = on;
        this.p = new Paint(1);
        this.box = new RectF();
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final void setChecked(boolean v) {
        this.checked = v;
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int w, int h) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iDp = UiKt.dp(context, 44);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        setMeasuredDimension(iDp, UiKt.dp(context2, 25));
    }

    @Override // android.view.View
    protected void onDraw(Canvas c) {
        int text;
        float f;
        int muted;
        Intrinsics.checkNotNullParameter(c, "c");
        this.box.set(1.0f, 1.0f, getWidth() - 1.0f, getHeight() - 1.0f);
        float r = getHeight() / 2.0f;
        this.p.setStyle(Paint.Style.FILL);
        Paint paint = this.p;
        if (this.checked) {
            text = this.skin.getAccent();
            f = 0.42f;
        } else {
            text = this.skin.getText();
            f = 0.08f;
        }
        paint.setColor(UiKt.alpha(text, f));
        c.drawRoundRect(this.box, r, r, this.p);
        this.p.setStyle(Paint.Style.STROKE);
        this.p.setStrokeWidth(1.5f);
        this.p.setColor(this.checked ? this.skin.getAccent() : this.skin.getLine());
        c.drawRoundRect(this.box, r, r, this.p);
        this.p.setStyle(Paint.Style.FILL);
        Paint paint2 = this.p;
        if (this.checked) {
            muted = this.skin.getLight() ? this.skin.getAccent() : -1;
        } else {
            muted = this.skin.getMuted();
        }
        paint2.setColor(muted);
        c.drawCircle(this.checked ? getWidth() - r : r, r, 0.68f * r, this.p);
    }
}
