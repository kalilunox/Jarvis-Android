package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jarvis.core.model.AssistantState;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: JarvisActivity.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0004J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001eH\u0004J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019H\u0004JZ\u0010!\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020&2\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010)H\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006*"}, d2 = {"Lcom/jarvis/app/ui/Screen;", "", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "getHost", "()Lcom/jarvis/app/ui/JarvisActivity;", "skin", "Lcom/jarvis/app/ui/Skin;", "getSkin", "()Lcom/jarvis/app/ui/Skin;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onShow", "", "onHide", "onState", "state", "Lcom/jarvis/core/model/AssistantState;", "onData", "header", "title", "", "withBack", "", "scroll", "body", "Landroid/widget/LinearLayout;", JarvisActivity.SECTION, "Landroid/widget/TextView;", "card", "sub", "icon", "trailing", "tone", "", "titleColor", "onClick", "Lkotlin/Function0;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Screen {
    private final JarvisActivity host;

    public abstract View getView();

    public Screen(JarvisActivity host) {
        Intrinsics.checkNotNullParameter(host, "host");
        this.host = host;
    }

    protected final JarvisActivity getHost() {
        return this.host;
    }

    protected Skin getSkin() {
        return this.host.getSkin();
    }

    public void onShow() {
    }

    public void onHide() {
    }

    public void onState(AssistantState state) {
        Intrinsics.checkNotNullParameter(state, "state");
    }

    public void onData() {
    }

    protected final View header(String title, boolean withBack) {
        Intrinsics.checkNotNullParameter(title, "title");
        LinearLayout row = (LinearLayout) UiKt.pad(UiKt.rowLayout(this.host, 8), 14, 10);
        if (withBack) {
            FrameLayout $this$header_u24lambda_u240 = new FrameLayout(this.host);
            $this$header_u24lambda_u240.addView(new IconView(this.host, Ic.BACK, getSkin().getAccent(), 0, 8, null), new FrameLayout.LayoutParams(-2, -2, 17));
            $this$header_u24lambda_u240.setContentDescription("Назад");
            UiKt.tap($this$header_u24lambda_u240, new Function0() { // from class: com.jarvis.app.ui.Screen$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Screen.header$lambda$0$0(this.f$0);
                }
            });
            row.addView($this$header_u24lambda_u240, UiKt.lp$default(UiKt.dp((Context) this.host, 42), UiKt.dp((Context) this.host, 42), 0.0f, 4, null));
        }
        JarvisActivity jarvisActivity = this.host;
        TextView t = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : title, (8 & 2) != 0 ? 14.0f : 22.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, this.host, 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        t.setPadding(UiKt.dp((Context) this.host, 10), 0, 0, 0);
        GradientDrawable $this$header_u24lambda_u241 = new GradientDrawable();
        $this$header_u24lambda_u241.setColor(0);
        t.setBackground($this$header_u24lambda_u241);
        View $this$header_u24lambda_u242 = new View(this.host);
        $this$header_u24lambda_u242.setBackgroundColor(getSkin().getAccent());
        row.addView($this$header_u24lambda_u242, UiKt.lp$default(UiKt.dp((Context) this.host, 3), UiKt.dp((Context) this.host, 24), 0.0f, 4, null));
        row.addView(t, UiKt.lp(0, -2, 1.0f));
        return row;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit header$lambda$0$0(Screen this$0) {
        this$0.host.back();
        return Unit.INSTANCE;
    }

    protected final View scroll(LinearLayout body) {
        Intrinsics.checkNotNullParameter(body, "body");
        ScrollView $this$scroll_u24lambda_u240 = new ScrollView(this.host);
        $this$scroll_u24lambda_u240.setFillViewport(true);
        $this$scroll_u24lambda_u240.setVerticalScrollBarEnabled(false);
        $this$scroll_u24lambda_u240.setOverScrollMode(2);
        $this$scroll_u24lambda_u240.setClipToPadding(false);
        $this$scroll_u24lambda_u240.setPadding(UiKt.dp((Context) this.host, 14), 0, UiKt.dp((Context) this.host, 14), UiKt.dp((Context) this.host, 40));
        $this$scroll_u24lambda_u240.addView(body, new ViewGroup.LayoutParams(-1, -2));
        return $this$scroll_u24lambda_u240;
    }

    protected final TextView section(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        JarvisActivity jarvisActivity = this.host;
        String upperCase = title.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        TextView $this$section_u24lambda_u240 = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : upperCase, (8 & 2) != 0 ? 14.0f : 13.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, this.host, 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        $this$section_u24lambda_u240.setLetterSpacing(0.08f);
        $this$section_u24lambda_u240.setPadding(UiKt.dp((Context) this.host, 2), UiKt.dp((Context) this.host, 8), 0, 0);
        return $this$section_u24lambda_u240;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LinearLayout card$default(Screen screen, String str, String str2, String str3, View view, int i, int i2, Function0 function0, int i3, Object obj) {
        if (obj == null) {
            return screen.card(str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? null : view, (i3 & 16) != 0 ? screen.getSkin().getAccent() : i, (i3 & 32) != 0 ? screen.getSkin().getText() : i2, (i3 & 64) == 0 ? function0 : null);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: card");
    }

    protected final LinearLayout card(String title, String sub, String icon, View trailing, int tone, int titleColor, Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(title, "title");
        LinearLayout row = (LinearLayout) UiKt.pad(UiKt.rowLayout(this.host, 12), 12);
        row.setBackground(UiKt.glassBg$default(this.host, getSkin(), 0, 0, 0, 14, null));
        if (icon != null) {
            FrameLayout $this$card_u24lambda_u240 = new FrameLayout(this.host);
            $this$card_u24lambda_u240.setBackground(UiKt.glassBg(this.host, getSkin(), 12, UiKt.alpha(tone, 0.13f), UiKt.alpha(tone, 0.35f)));
            $this$card_u24lambda_u240.addView(new IconView(this.host, icon, tone, 0, 8, null), new FrameLayout.LayoutParams(-2, -2, 17));
            row.addView($this$card_u24lambda_u240, UiKt.lp$default(UiKt.dp((Context) this.host, 44), UiKt.dp((Context) this.host, 44), 0.0f, 4, null));
        }
        boolean z = true;
        LinearLayout text = UiKt.column$default(this.host, 0, 1, null);
        JarvisActivity jarvisActivity = this.host;
        text.addView(UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : title, (8 & 2) != 0 ? 14.0f : 15.0f, titleColor, (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : Fonts.INSTANCE.body(this.host, 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        String str = sub;
        if (str != null && !StringsKt.isBlank(str)) {
            z = false;
        }
        if (!z) {
            JarvisActivity jarvisActivity2 = this.host;
            TextView $this$card_u24lambda_u241 = UiKt.label(jarvisActivity2, (8 & 1) != 0 ? "" : sub, (8 & 2) != 0 ? 14.0f : 12.5f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
            $this$card_u24lambda_u241.setPadding(0, UiKt.dp((Context) this.host, 2), 0, 0);
            text.addView($this$card_u24lambda_u241);
        }
        row.addView(text, UiKt.lp(0, -2, 1.0f));
        if (trailing != null) {
            row.addView(trailing, UiKt.lp$default(-2, -2, 0.0f, 4, null));
        } else if (onClick != null) {
            JarvisActivity jarvisActivity3 = this.host;
            row.addView(UiKt.label(jarvisActivity3, (8 & 1) != 0 ? "" : "›", (8 & 2) != 0 ? 14.0f : 18.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity3, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        }
        if (onClick != null) {
        }
        return row;
    }
}
