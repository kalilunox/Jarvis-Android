package com.jarvis.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.jarvis.app.ui.Fonts;
import com.jarvis.app.ui.UiKt;
import com.jarvis.core.startup.UiStyle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InterfaceChoiceActivity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¨\u0006\u001b"}, d2 = {"Lcom/jarvis/app/InterfaceChoiceActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "card", "Landroid/view/View;", "image", "", "title", "", "about", "style", "Lcom/jarvis/core/startup/UiStyle;", "choose", VoiceService.EXTRA_TEXT, "Landroid/widget/TextView;", "value", "size", "", TypedValues.Custom.S_COLOR, OptionalModuleUtils.FACE, "Landroid/graphics/Typeface;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InterfaceChoiceActivity extends AppCompatActivity {
    private static final Companion Companion = new Companion(null);
    private static final int BG = Color.parseColor("#040811");
    private static final int CARD = Color.parseColor("#0B1628");
    private static final int LINE = Color.parseColor("#1F3654");
    private static final int INK = Color.parseColor("#ECF4FF");
    private static final int MUTED = Color.parseColor("#8FA8C6");
    private static final int ACCENT = Color.parseColor("#35C8FF");

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1);
        root.setPadding(UiKt.dp((Context) this, 20), UiKt.dp((Context) this, 28), UiKt.dp((Context) this, 20), UiKt.dp((Context) this, 24));
        TextView $this$onCreate_u24lambda_u241 = text("ИНТЕРФЕЙС", 12.0f, ACCENT, Fonts.INSTANCE.display(this, TypedValues.TransitionType.TYPE_DURATION));
        $this$onCreate_u24lambda_u241.setLetterSpacing(0.3f);
        root.addView($this$onCreate_u24lambda_u241);
        TextView $this$onCreate_u24lambda_u242 = text("Как будет выглядеть Джарвис?", 26.0f, INK, Fonts.INSTANCE.display(this, TypedValues.TransitionType.TYPE_DURATION));
        $this$onCreate_u24lambda_u242.setPadding(0, UiKt.dp((Context) this, 6), 0, UiKt.dp((Context) this, 6));
        root.addView($this$onCreate_u24lambda_u242);
        root.addView(text("Выберите вид. Поменять его можно в любой момент: в настройках оформления.", 14.0f, MUTED, Fonts.body$default(Fonts.INSTANCE, this, 0, 2, null)));
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        row.setBaselineAligned(false);
        row.setPadding(0, UiKt.dp((Context) this, 22), 0, 0);
        View viewCard = card(R.drawable.ui_choice_modern, "Современный", "Реактор, режим Live и пять тем оформления.", UiStyle.MODERN);
        LinearLayout.LayoutParams $this$onCreate_u24lambda_u244 = new LinearLayout.LayoutParams(0, -1, 1.0f);
        $this$onCreate_u24lambda_u244.setMarginEnd(UiKt.dp((Context) this, 7));
        Unit unit = Unit.INSTANCE;
        row.addView(viewCard, $this$onCreate_u24lambda_u244);
        View viewCard2 = card(R.drawable.ui_choice_classic, "Классический", "Инженерная консоль: ядро, конвейер и журнал.", UiStyle.CLASSIC);
        LinearLayout.LayoutParams $this$onCreate_u24lambda_u245 = new LinearLayout.LayoutParams(0, -1, 1.0f);
        $this$onCreate_u24lambda_u245.setMarginStart(UiKt.dp((Context) this, 7));
        Unit unit2 = Unit.INSTANCE;
        row.addView(viewCard2, $this$onCreate_u24lambda_u245);
        root.addView(row);
        ScrollView $this$onCreate_u24lambda_u246 = new ScrollView(this);
        $this$onCreate_u24lambda_u246.setBackgroundColor(BG);
        $this$onCreate_u24lambda_u246.setFillViewport(true);
        $this$onCreate_u24lambda_u246.addView(root);
        setContentView($this$onCreate_u24lambda_u246);
    }

    private final View card(int image, String title, String about, final UiStyle style) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(1);
        box.setPadding(UiKt.dp((Context) this, 10), UiKt.dp((Context) this, 10), UiKt.dp((Context) this, 10), UiKt.dp((Context) this, 12));
        GradientDrawable $this$card_u24lambda_u240_u240 = new GradientDrawable();
        $this$card_u24lambda_u240_u240.setCornerRadius(UiKt.dp((Context) this, 20));
        $this$card_u24lambda_u240_u240.setColor(CARD);
        $this$card_u24lambda_u240_u240.setStroke(UiKt.dp((Context) this, 1), LINE);
        box.setBackground($this$card_u24lambda_u240_u240);
        box.setClickable(true);
        box.setFocusable(true);
        box.setContentDescription("Интерфейс: " + title);
        box.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.InterfaceChoiceActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.choose(style);
            }
        });
        ImageView $this$card_u24lambda_u241 = new ImageView(this);
        $this$card_u24lambda_u241.setImageResource(image);
        $this$card_u24lambda_u241.setAdjustViewBounds(true);
        $this$card_u24lambda_u241.setScaleType(ImageView.ScaleType.FIT_CENTER);
        $this$card_u24lambda_u241.setOutlineProvider(new ViewOutlineProvider() { // from class: com.jarvis.app.InterfaceChoiceActivity$card$1$1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(outline, "outline");
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), UiKt.dp((Context) this.this$0, 14));
            }
        });
        $this$card_u24lambda_u241.setClipToOutline(true);
        box.addView($this$card_u24lambda_u241, new LinearLayout.LayoutParams(-1, -2));
        TextView $this$card_u24lambda_u242 = text(title, 17.0f, INK, Fonts.INSTANCE.display(this, TypedValues.TransitionType.TYPE_DURATION));
        $this$card_u24lambda_u242.setPadding(UiKt.dp((Context) this, 2), UiKt.dp((Context) this, 12), 0, UiKt.dp((Context) this, 4));
        box.addView($this$card_u24lambda_u242);
        TextView $this$card_u24lambda_u243 = text(about, 12.5f, MUTED, Fonts.body$default(Fonts.INSTANCE, this, 0, 2, null));
        $this$card_u24lambda_u243.setPadding(UiKt.dp((Context) this, 2), 0, 0, UiKt.dp((Context) this, 12));
        box.addView($this$card_u24lambda_u243);
        box.addView(new View(this), new LinearLayout.LayoutParams(-1, 0, 1.0f));
        TextView $this$card_u24lambda_u244 = text("Выбрать", 14.0f, Color.parseColor("#03121F"), Fonts.INSTANCE.body(this, TypedValues.TransitionType.TYPE_DURATION));
        $this$card_u24lambda_u244.setGravity(17);
        $this$card_u24lambda_u244.setPadding(0, UiKt.dp((Context) this, 11), 0, UiKt.dp((Context) this, 11));
        GradientDrawable $this$card_u24lambda_u244_u240 = new GradientDrawable();
        $this$card_u24lambda_u244_u240.setCornerRadius(UiKt.dp((Context) this, 22));
        $this$card_u24lambda_u244_u240.setColor(ACCENT);
        $this$card_u24lambda_u244.setBackground($this$card_u24lambda_u244_u240);
        box.addView($this$card_u24lambda_u244, new LinearLayout.LayoutParams(-1, -2));
        return box;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void choose(UiStyle style) {
        UiMode.INSTANCE.set(this, style);
        UiMode.INSTANCE.continueFrom(this);
    }

    private final TextView text(String value, float size, int color, Typeface face) {
        TextView $this$text_u24lambda_u240 = new TextView(this);
        $this$text_u24lambda_u240.setText(value);
        $this$text_u24lambda_u240.setTextSize(size);
        $this$text_u24lambda_u240.setTextColor(color);
        $this$text_u24lambda_u240.setTypeface(face);
        return $this$text_u24lambda_u240;
    }

    /* JADX INFO: compiled from: InterfaceChoiceActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/app/InterfaceChoiceActivity$Companion;", "", "<init>", "()V", "BG", "", "getBG", "()I", "CARD", "getCARD", "LINE", "getLINE", "INK", "getINK", "MUTED", "getMUTED", "ACCENT", "getACCENT", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getBG() {
            return InterfaceChoiceActivity.BG;
        }

        public final int getCARD() {
            return InterfaceChoiceActivity.CARD;
        }

        public final int getLINE() {
            return InterfaceChoiceActivity.LINE;
        }

        public final int getINK() {
            return InterfaceChoiceActivity.INK;
        }

        public final int getMUTED() {
            return InterfaceChoiceActivity.MUTED;
        }

        public final int getACCENT() {
            return InterfaceChoiceActivity.ACCENT;
        }
    }
}
