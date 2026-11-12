package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ReactorArt.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/jarvis/app/ui/ReactorArt;", "", "<init>", "()V", "TAU", "", "render", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "skin", "Lcom/jarvis/app/ui/Skin;", "sizeDp", "", "on", "", "loud", "MAX_PX", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReactorArt {
    public static final ReactorArt INSTANCE = new ReactorArt();
    private static final int MAX_PX = 300;
    private static final float TAU = 6.2831855f;

    private ReactorArt() {
    }

    public final Bitmap render(Context context, Skin skin, int sizeDp, boolean on, boolean loud) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skin, "skin");
        int size = RangesKt.coerceAtMost(UiKt.dp(context, sizeDp) * 1, 300);
        Bitmap bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
        Canvas c = new Canvas(bmp);
        float cx = size / 2.0f;
        float r = size * 0.44f;
        Paint $this$render_u24lambda_u240 = new Paint(1);
        $this$render_u24lambda_u240.setStyle(Paint.Style.STROKE);
        RectF box = new RectF();
        float power = on ? 1.0f : 0.12f;
        PorterDuffXfermode add = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        Paint $this$render_u24lambda_u241 = new Paint(1);
        $this$render_u24lambda_u241.setXfermode(add);
        $this$render_u24lambda_u241.setShader(new RadialGradient(cx, cx, r * 1.13f, new int[]{UiKt.alpha(skin.getAccent(), power * 0.5f), UiKt.alpha(skin.getAccent(), 0.0f)}, (float[]) null, Shader.TileMode.CLAMP));
        c.drawCircle(cx, cx, r * 1.13f, $this$render_u24lambda_u241);
        int i = 0;
        while (i < 10) {
            float f = r * 0.88f;
            float f2 = ((i * 6.2831855f) / 10) + 0.15f;
            int metal = skin.getMetal();
            if (!on) {
                metal = UiKt.alpha(metal, 0.7f);
            }
            float cx2 = cx;
            render$arc($this$render_u24lambda_u240, box, cx2, c, f, f2, 0.42f, metal, r * 0.1f);
            i++;
            cx = cx2;
        }
        float cx3 = cx;
        Paint p = $this$render_u24lambda_u240;
        p.setXfermode(add);
        int i2 = 0;
        while (i2 < 5) {
            render$arc(p, box, cx3, c, r * 0.72f, ((i2 * 6.2831855f) / 5) + 0.5f, 0.5f, UiKt.alpha(skin.getHot(), 0.75f * power), RangesKt.coerceAtLeast(0.03f * r, 1.0f));
            i2++;
            p = p;
        }
        Paint p2 = p;
        int i3 = 0;
        while (true) {
            if (i3 >= 18) {
                break;
            }
            float extra = (loud && i3 % 3 == 0) ? 0.3f : 0.0f;
            float f3 = r * 0.6f;
            float f4 = (i3 * 6.2831855f) / 18;
            int accent = skin.getAccent();
            float f5 = 0.35f;
            float f6 = (power * 0.5f) + 0.35f + extra;
            if (on) {
                f5 = 1.0f;
            }
            render$arc(p2, box, cx3, c, f3, f4, 0.24f, UiKt.alpha(accent, RangesKt.coerceAtMost(f6 * f5, 1.0f)), r * 0.07f);
            i3++;
        }
        int i4 = 0;
        while (true) {
            if (i4 >= 10) {
                break;
            }
            render$arc(p2, box, cx3, c, r * 0.4f, ((i4 * 6.2831855f) / 10) + 0.1f, 0.42f, UiKt.alpha(skin.getCyan(), (power * 0.6f) + 0.08f), r * 0.11f);
            i4++;
        }
        p2.setXfermode(null);
        float core = ((0.1f * power) + 0.2f) * r;
        $this$render_u24lambda_u241.setShader(new RadialGradient(cx3, cx3, core * 1.5f, new int[]{UiKt.alpha(-1, on ? 1.0f : 0.4f), UiKt.alpha(skin.getCyan(), on ? 0.95f : 0.3f), UiKt.alpha(skin.getAccent(), 0.0f)}, new float[]{0.0f, 0.3f, 1.0f}, Shader.TileMode.CLAMP));
        c.drawCircle(cx3, cx3, core * 1.5f, $this$render_u24lambda_u241);
        return bmp;
    }

    private static final void render$arc(Paint p, RectF box, float cx, Canvas c, float radius, float a, float sweep, int color, float width) {
        p.setColor(color);
        p.setStrokeWidth(width);
        box.set(cx - radius, cx - radius, cx + radius, cx + radius);
        c.drawArc(box, (float) Math.toDegrees(a), (float) Math.toDegrees(sweep), false, p);
    }
}
