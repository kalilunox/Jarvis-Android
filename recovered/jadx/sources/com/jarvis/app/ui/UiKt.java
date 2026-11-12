package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.jarvis.app.HudFeedback;
import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Ui.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001\u001a0\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\u0001\u001a\u0016\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0004\u001aD\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0017\u001a\u00020\u0001\u001a$\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00012\b\b\u0002\u0010\u001b\u001a\u00020\u00012\b\b\u0002\u0010\u001c\u001a\u00020\u0004\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020\u0001\u001a\u0014\u0010 \u001a\u00020\u001e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020\u0001\u001a!\u0010!\u001a\u0002H\"\"\b\b\u0000\u0010\"*\u00020#*\u0002H\"2\u0006\u0010$\u001a\u00020\u0001¢\u0006\u0002\u0010%\u001a)\u0010!\u001a\u0002H\"\"\b\b\u0000\u0010\"*\u00020#*\u0002H\"2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010&\u001a'\u0010'\u001a\u0002H\"\"\b\b\u0000\u0010\"*\u00020#*\u0002H\"2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)¢\u0006\u0002\u0010+¨\u0006,"}, d2 = {"dp", "", "Landroid/content/Context;", "v", "", "glassBg", "Landroid/graphics/drawable/GradientDrawable;", "skin", "Lcom/jarvis/app/ui/Skin;", "radius", "fill", "line", "alpha", TypedValues.Custom.S_COLOR, "a", "label", "Landroid/widget/TextView;", VoiceService.EXTRA_TEXT, "", "size", OptionalModuleUtils.FACE, "Landroid/graphics/Typeface;", "gravity", "lines", "lp", "Landroid/widget/LinearLayout$LayoutParams;", "w", "h", "weight", "column", "Landroid/widget/LinearLayout;", "gapDp", "rowLayout", "pad", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "all", "(Landroid/view/View;I)Landroid/view/View;", "(Landroid/view/View;II)Landroid/view/View;", "tap", "action", "Lkotlin/Function0;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function0;)Landroid/view/View;", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UiKt {
    public static final int dp(Context $this$dp, float v) {
        Intrinsics.checkNotNullParameter($this$dp, "<this>");
        return (int) TypedValue.applyDimension(1, v, $this$dp.getResources().getDisplayMetrics());
    }

    public static final int dp(Context $this$dp, int v) {
        Intrinsics.checkNotNullParameter($this$dp, "<this>");
        return dp($this$dp, v);
    }

    public static /* synthetic */ GradientDrawable glassBg$default(Context context, Skin skin, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 16;
        }
        if ((i4 & 4) != 0) {
            i2 = skin.getGlass();
        }
        if ((i4 & 8) != 0) {
            i3 = skin.getLine();
        }
        return glassBg(context, skin, i, i2, i3);
    }

    public static final GradientDrawable glassBg(Context $this$glassBg, Skin skin, int radius, int fill, int line) {
        Intrinsics.checkNotNullParameter($this$glassBg, "<this>");
        Intrinsics.checkNotNullParameter(skin, "skin");
        GradientDrawable $this$glassBg_u24lambda_u240 = new GradientDrawable();
        $this$glassBg_u24lambda_u240.setColor(fill);
        $this$glassBg_u24lambda_u240.setCornerRadius(dp($this$glassBg, radius));
        $this$glassBg_u24lambda_u240.setStroke(RangesKt.coerceAtLeast(dp($this$glassBg, 1), 1), line);
        return $this$glassBg_u24lambda_u240;
    }

    public static final int alpha(int color, float a) {
        return Color.argb(RangesKt.coerceIn((int) (255 * a), 0, 255), Color.red(color), Color.green(color), Color.blue(color));
    }

    public static final TextView label(Context $this$label, CharSequence text, float size, int color, Typeface face, int gravity, int lines) {
        Intrinsics.checkNotNullParameter($this$label, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(face, "face");
        TextView $this$label_u24lambda_u240 = new TextView($this$label);
        $this$label_u24lambda_u240.setText(text);
        $this$label_u24lambda_u240.setTextSize(size);
        $this$label_u24lambda_u240.setTextColor(color);
        $this$label_u24lambda_u240.setTypeface(face);
        $this$label_u24lambda_u240.setGravity(gravity);
        $this$label_u24lambda_u240.setMaxLines(lines);
        if (lines != Integer.MAX_VALUE) {
            $this$label_u24lambda_u240.setEllipsize(TextUtils.TruncateAt.END);
        }
        $this$label_u24lambda_u240.setIncludeFontPadding(false);
        $this$label_u24lambda_u240.setLineSpacing(0.0f, 1.2f);
        return $this$label_u24lambda_u240;
    }

    public static /* synthetic */ LinearLayout.LayoutParams lp$default(int i, int i2, float f, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = -1;
        }
        if ((i3 & 2) != 0) {
            i2 = -2;
        }
        if ((i3 & 4) != 0) {
            f = 0.0f;
        }
        return lp(i, i2, f);
    }

    public static final LinearLayout.LayoutParams lp(int w, int h, float weight) {
        return new LinearLayout.LayoutParams(w, h, weight);
    }

    public static final LinearLayout column(Context $this$column, int gapDp) {
        Intrinsics.checkNotNullParameter($this$column, "<this>");
        LinearLayout $this$column_u24lambda_u240 = new LinearLayout($this$column);
        $this$column_u24lambda_u240.setOrientation(1);
        if (gapDp > 0) {
            GradientDrawable $this$column_u24lambda_u240_u240 = new GradientDrawable();
            $this$column_u24lambda_u240_u240.setSize(0, dp($this$column, gapDp));
            $this$column_u24lambda_u240_u240.setColor(0);
            $this$column_u24lambda_u240.setDividerDrawable($this$column_u24lambda_u240_u240);
            $this$column_u24lambda_u240.setShowDividers(2);
        }
        return $this$column_u24lambda_u240;
    }

    public static /* synthetic */ LinearLayout column$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return column(context, i);
    }

    public static final LinearLayout rowLayout(Context $this$rowLayout, int gapDp) {
        Intrinsics.checkNotNullParameter($this$rowLayout, "<this>");
        LinearLayout $this$rowLayout_u24lambda_u240 = new LinearLayout($this$rowLayout);
        $this$rowLayout_u24lambda_u240.setOrientation(0);
        $this$rowLayout_u24lambda_u240.setGravity(16);
        if (gapDp > 0) {
            GradientDrawable $this$rowLayout_u24lambda_u240_u240 = new GradientDrawable();
            $this$rowLayout_u24lambda_u240_u240.setSize(dp($this$rowLayout, gapDp), 0);
            $this$rowLayout_u24lambda_u240_u240.setColor(0);
            $this$rowLayout_u24lambda_u240.setDividerDrawable($this$rowLayout_u24lambda_u240_u240);
            $this$rowLayout_u24lambda_u240.setShowDividers(2);
        }
        return $this$rowLayout_u24lambda_u240;
    }

    public static /* synthetic */ LinearLayout rowLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return rowLayout(context, i);
    }

    public static final <T extends View> T pad(T t, int all) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Context context = t.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int p = dp(context, all);
        t.setPadding(p, p, p, p);
        return t;
    }

    public static final <T extends View> T pad(T t, int h, int v) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Context context = t.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iDp = dp(context, h);
        Context context2 = t.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        int iDp2 = dp(context2, v);
        Context context3 = t.getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        int iDp3 = dp(context3, h);
        Context context4 = t.getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
        t.setPadding(iDp, iDp2, iDp3, dp(context4, v));
        return t;
    }

    public static final <T extends View> T tap(T t, final Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        t.setClickable(true);
        t.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.ui.UiKt$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UiKt.tap$lambda$0$0(action, view);
            }
        });
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tap$lambda$0$0(Function0 $action, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        $action.invoke();
    }
}
