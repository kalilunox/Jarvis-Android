package com.jarvis.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import com.google.android.material.timepicker.TimeModel;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.jarvis.app.ui.Fonts;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.app.ui.Skin;
import com.jarvis.app.ui.Skins;
import com.jarvis.app.ui.UiKt;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UsageGuideActivity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0003*+,B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\u0005H\u0002J\b\u0010\r\u001a\u00020\u0005H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0012H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J&\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00072\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\u001fH\u0002J(\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u001cH\u0002J\b\u0010)\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/jarvis/app/UsageGuideActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "look", "Lcom/jarvis/app/UsageGuideActivity$Look;", "classic", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "modernLook", "classicLook", "build", "Landroid/view/View;", "sections", "", "Lcom/jarvis/app/UsageGuideActivity$Part;", JarvisActivity.SECTION, "index", "", "part", "link", "Landroid/widget/TextView;", "view", "button", "label", "", "primary", "onClick", "Lkotlin/Function0;", VoiceService.EXTRA_TEXT, "value", "size", "", TypedValues.Custom.S_COLOR, OptionalModuleUtils.FACE, "Landroid/graphics/Typeface;", "openSection", "title", "done", "Look", "Part", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UsageGuideActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String PREFS = "jarvis_prefs";
    public static final String PREF_USAGE_GUIDE_DONE = "usage_guide_done_v2";
    private static final String SECTION_AI = "ИИ и модели";
    private static final String SECTION_VOICE = "Голос и слух";
    private boolean classic;
    private Look look;

    /* JADX INFO: compiled from: UsageGuideActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0002\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0010\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001d¨\u0006\""}, d2 = {"Lcom/jarvis/app/UsageGuideActivity$Look;", "", "bg", "", "card", "line", "ink", "muted", "accent", "warm", "onAccent", "light", "", "head", "Landroid/graphics/Typeface;", "body", "classic", "<init>", "(IIIIIIIIZLandroid/graphics/Typeface;Landroid/graphics/Typeface;Z)V", "getBg", "()I", "getCard", "getLine", "getInk", "getMuted", "getAccent", "getWarm", "getOnAccent", "getLight", "()Z", "getHead", "()Landroid/graphics/Typeface;", "getBody", "getClassic", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Look {
        private final int accent;
        private final int bg;
        private final Typeface body;
        private final int card;
        private final boolean classic;
        private final Typeface head;
        private final int ink;
        private final boolean light;
        private final int line;
        private final int muted;
        private final int onAccent;
        private final int warm;

        public Look(int bg, int card, int line, int ink, int muted, int accent, int warm, int onAccent, boolean light, Typeface head, Typeface body, boolean classic) {
            Intrinsics.checkNotNullParameter(head, "head");
            Intrinsics.checkNotNullParameter(body, "body");
            this.bg = bg;
            this.card = card;
            this.line = line;
            this.ink = ink;
            this.muted = muted;
            this.accent = accent;
            this.warm = warm;
            this.onAccent = onAccent;
            this.light = light;
            this.head = head;
            this.body = body;
            this.classic = classic;
        }

        public final int getBg() {
            return this.bg;
        }

        public final int getCard() {
            return this.card;
        }

        public final int getInk() {
            return this.ink;
        }

        public final int getLine() {
            return this.line;
        }

        public final int getMuted() {
            return this.muted;
        }

        public final int getAccent() {
            return this.accent;
        }

        public final boolean getLight() {
            return this.light;
        }

        public final int getOnAccent() {
            return this.onAccent;
        }

        public final int getWarm() {
            return this.warm;
        }

        public final Typeface getBody() {
            return this.body;
        }

        public final boolean getClassic() {
            return this.classic;
        }

        public final Typeface getHead() {
            return this.head;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        this.classic = UiMode.INSTANCE.isClassic(this);
        this.look = this.classic ? classicLook() : modernLook();
        Window window = getWindow();
        Look look = this.look;
        Look look2 = null;
        if (look == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look = null;
        }
        window.setStatusBarColor(look.getBg());
        Window window2 = getWindow();
        Look look3 = this.look;
        if (look3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look3 = null;
        }
        window2.setNavigationBarColor(look3.getBg());
        View decorView = getWindow().getDecorView();
        Look look4 = this.look;
        if (look4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
        } else {
            look2 = look4;
        }
        if (look2.getLight()) {
            i = 8208;
        } else {
            i = 0;
        }
        decorView.setSystemUiVisibility(i);
        setContentView(build());
    }

    private final Look modernLook() {
        Skin s = Skins.INSTANCE.current(this);
        return new Look(s.getBg(), s.getGlass(), s.getLine(), s.getText(), s.getMuted(), s.getAccent(), s.getHot(), s.getOnAccent(), s.getLight(), Fonts.INSTANCE.display(this, TypedValues.TransitionType.TYPE_DURATION), Fonts.body$default(Fonts.INSTANCE, this, 0, 2, null), false);
    }

    private static final int classicLook$c(UsageGuideActivity this$0, int id) {
        return ContextCompat.getColor(this$0, id);
    }

    private final Look classicLook() {
        int iClassicLook$c = classicLook$c(this, R.color.jarvis_bg);
        int iClassicLook$c2 = classicLook$c(this, R.color.jarvis_panel);
        int iClassicLook$c3 = classicLook$c(this, R.color.jarvis_border);
        int iClassicLook$c4 = classicLook$c(this, R.color.jarvis_text);
        int iClassicLook$c5 = classicLook$c(this, R.color.jarvis_muted);
        int iClassicLook$c6 = classicLook$c(this, R.color.jarvis_cyan);
        int iClassicLook$c7 = classicLook$c(this, R.color.jarvis_primary);
        int iClassicLook$c8 = classicLook$c(this, R.color.jarvis_bg);
        Typeface MONOSPACE = Typeface.MONOSPACE;
        Intrinsics.checkNotNullExpressionValue(MONOSPACE, "MONOSPACE");
        Typeface DEFAULT = Typeface.DEFAULT;
        Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
        return new Look(iClassicLook$c, iClassicLook$c2, iClassicLook$c3, iClassicLook$c4, iClassicLook$c5, iClassicLook$c6, iClassicLook$c7, iClassicLook$c8, false, MONOSPACE, DEFAULT, true);
    }

    private final View build() {
        Look look;
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1);
        root.setPadding(UiKt.dp((Context) this, 18), UiKt.dp((Context) this, 26), UiKt.dp((Context) this, 18), UiKt.dp((Context) this, 28));
        Look look2 = this.look;
        if (look2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look2 = null;
        }
        if (look2.getClassic()) {
            Look look3 = this.look;
            if (look3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look3 = null;
            }
            int warm = look3.getWarm();
            Look look4 = this.look;
            if (look4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look4 = null;
            }
            TextView $this$build_u24lambda_u241 = text("// JARVIS · ИНСТРУКЦИЯ", 12.0f, warm, look4.getHead());
            $this$build_u24lambda_u241.setLetterSpacing(0.12f);
            root.addView($this$build_u24lambda_u241);
            Look look5 = this.look;
            if (look5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look5 = null;
            }
            int ink = look5.getInk();
            Typeface typefaceCreate = Typeface.create(Typeface.MONOSPACE, 1);
            Intrinsics.checkNotNullExpressionValue(typefaceCreate, "create(...)");
            TextView $this$build_u24lambda_u242 = text("КАК ЭТИМ ПОЛЬЗОВАТЬСЯ", 22.0f, ink, typefaceCreate);
            $this$build_u24lambda_u242.setPadding(0, UiKt.dp((Context) this, 6), 0, UiKt.dp((Context) this, 6));
            root.addView($this$build_u24lambda_u242);
        } else {
            Look look6 = this.look;
            if (look6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look6 = null;
            }
            int accent = look6.getAccent();
            Look look7 = this.look;
            if (look7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look7 = null;
            }
            TextView $this$build_u24lambda_u243 = text("ГИД", 12.0f, accent, look7.getHead());
            $this$build_u24lambda_u243.setLetterSpacing(0.3f);
            root.addView($this$build_u24lambda_u243);
            Look look8 = this.look;
            if (look8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look8 = null;
            }
            int ink2 = look8.getInk();
            Look look9 = this.look;
            if (look9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look9 = null;
            }
            TextView $this$build_u24lambda_u244 = text("Как пользоваться Джарвисом", 26.0f, ink2, look9.getHead());
            $this$build_u24lambda_u244.setPadding(0, UiKt.dp((Context) this, 6), 0, UiKt.dp((Context) this, 6));
            root.addView($this$build_u24lambda_u244);
        }
        Look look10 = this.look;
        if (look10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look10 = null;
        }
        int muted = look10.getMuted();
        Look look11 = this.look;
        if (look11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look11 = null;
        }
        root.addView(text("Шесть коротких пунктов. Гид можно открыть снова в настройках.", 14.0f, muted, look11.getBody()));
        Iterable $this$forEachIndexed$iv = sections();
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Part s = (Part) item$iv;
            View viewSection = section(i + 1, s);
            LinearLayout.LayoutParams $this$build_u24lambda_u245_u240 = new LinearLayout.LayoutParams(-1, -2);
            Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
            $this$build_u24lambda_u245_u240.topMargin = UiKt.dp((Context) this, 14);
            Unit unit = Unit.INSTANCE;
            root.addView(viewSection, $this$build_u24lambda_u245_u240);
            i = index$iv;
            $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
        }
        Look look12 = this.look;
        if (look12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look12 = null;
        }
        TextView textViewButton = button(look12.getClassic() ? "[ НАЧАТЬ РАБОТУ ]" : "Начать", true, new Function0() { // from class: com.jarvis.app.UsageGuideActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UsageGuideActivity.build$lambda$6(this.f$0);
            }
        });
        LinearLayout.LayoutParams $this$build_u24lambda_u247 = new LinearLayout.LayoutParams(-1, UiKt.dp((Context) this, 52));
        $this$build_u24lambda_u247.topMargin = UiKt.dp((Context) this, 22);
        Unit unit2 = Unit.INSTANCE;
        root.addView(textViewButton, $this$build_u24lambda_u247);
        ScrollView $this$build_u24lambda_u248 = new ScrollView(this);
        Look look13 = this.look;
        if (look13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look = null;
        } else {
            look = look13;
        }
        $this$build_u24lambda_u248.setBackgroundColor(look.getBg());
        $this$build_u24lambda_u248.addView(root);
        return $this$build_u24lambda_u248;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit build$lambda$6(UsageGuideActivity this$0) {
        this$0.done();
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: UsageGuideActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001e\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u00060\u0005\u0012 \b\u0002\u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00060\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR)\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R)\u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/jarvis/app/UsageGuideActivity$Part;", "", "title", "", "blocks", "", "Lkotlin/Pair;", "actions", "Lkotlin/Function0;", "", "tip", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getBlocks", "()Ljava/util/List;", "getActions", "getTip", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Part {
        private final List<Pair<String, Function0<Unit>>> actions;
        private final List<Pair<String, List<String>>> blocks;
        private final String tip;
        private final String title;

        /* JADX WARN: Multi-variable type inference failed */
        public Part(String title, List<? extends Pair<String, ? extends List<String>>> blocks, List<? extends Pair<String, ? extends Function0<Unit>>> actions, String tip) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(blocks, "blocks");
            Intrinsics.checkNotNullParameter(actions, "actions");
            this.title = title;
            this.blocks = blocks;
            this.actions = actions;
            this.tip = tip;
        }

        public /* synthetic */ Part(String str, List list, List list2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, list, (i & 4) != 0 ? CollectionsKt.emptyList() : list2, (i & 8) != 0 ? null : str2);
        }

        public final String getTitle() {
            return this.title;
        }

        public final List<Pair<String, List<String>>> getBlocks() {
            return this.blocks;
        }

        public final List<Pair<String, Function0<Unit>>> getActions() {
            return this.actions;
        }

        public final String getTip() {
            return this.tip;
        }
    }

    private final List<Part> sections() {
        String keysPath = this.classic ? "«Профиль» → «Общее» → «Облачный LLM». Вставьте ключ в поле Groq или Google Gemini и нажмите «Сохранить»." : "«Ещё» → «ИИ и модели» → «Облачные ключи». Нажмите на строку Groq или Gemini, вставьте ключ и нажмите «Сохранить». Метка сменится на «Сохранён». Там же оставьте маршрут «Гибрид» и включите «Использовать ИИ-модель».";
        String voicePlace = this.classic ? "Запись голоса открывается отдельным экраном кнопкой ниже. После записи нажмите «Назад», и вы вернётесь сюда." : "«Ещё» → «Голос и слух».";
        DefaultConstructorMarker defaultConstructorMarker = null;
        String str = null;
        int i = 12;
        List list = null;
        return CollectionsKt.listOf((Object[]) new Part[]{new Part("Как говорить", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Скажите «Джарвис», дождитесь сигнала и говорите команду. Можно одной фразой: «Джарвис, какая погода».", CollectionsKt.listOf((Object[]) new String[]{"«Позвони маме», «Напиши Саше в Телеграм, что опоздаю»", "«Разбуди в семь утра», «Поставь таймер на 10 минут»", "«Включи фонарик», «Громкость на максимум», «Включи Bluetooth»", "«Какая погода завтра», «Курс доллара», «Что нового»"})), TuplesKt.to("Можно несколько дел сразу: «выключи Wi-Fi и поставь будильник на 8». Звонки, отправку сообщений и удаление Джарвис переспросит.", CollectionsKt.emptyList())}), null, null, 12, null), new Part("Облачный ИИ: Groq и Gemini", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Без ключей работают команды телефона и локальная модель. С ключами Джарвис отвечает умнее и быстрее. Оба ключа бесплатные, нужен только аккаунт.", CollectionsKt.emptyList()), TuplesKt.to("Groq: основная быстрая модель", CollectionsKt.listOf((Object[]) new String[]{"Откройте https://console.groq.com/keys и войдите через Google или почту.", "Нажмите Create API Key и дайте любое имя, например «jarvis».", "Сразу скопируйте ключ: он начинается с gsk_ и больше не покажется."})), TuplesKt.to("Gemini: запасная цепочка", CollectionsKt.listOf((Object[]) new String[]{"Откройте https://aistudio.google.com/apikey и войдите аккаунтом Google.", "Нажмите Create API key, выберите или создайте проект.", "Скопируйте ключ: он начинается с AIza."})), TuplesKt.to("Куда вставить: " + keysPath, CollectionsKt.emptyList())}), CollectionsKt.listOf(TuplesKt.to("Открыть настройки ключей", new Function0() { // from class: com.jarvis.app.UsageGuideActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UsageGuideActivity.sections$lambda$0(this.f$0);
            }
        })), "Ключи хранятся на телефоне в зашифрованном виде и уходят только в Groq и Google. Никому их не пересылайте. В некоторых странах эти сайты открываются только через VPN."), new Part("Ваш голос", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Джарвис может слушаться только вас: чужие голоса и звук из видео не будут его будить. " + voicePlace, CollectionsKt.listOf((Object[]) new String[]{"Нажмите «Скачать модель узнавания»: она нужна один раз, 28 МБ.", "Несколько раз скажите «Джарвис», затем прочитайте вслух предложенные фразы. Говорите обычным голосом в тихом месте.", "Включите «Только мой голос»."})), TuplesKt.to("Если слово срабатывает слишком часто или редко, поменяйте «Чувствительность» там же. Если узнавать стал хуже, нажмите «Записать голос заново».", CollectionsKt.emptyList())}), CollectionsKt.listOf(TuplesKt.to("Записать голос", new Function0() { // from class: com.jarvis.app.UsageGuideActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UsageGuideActivity.sections$lambda$1(this.f$0);
            }
        })), str, 8, defaultConstructorMarker), new Part("Live и «сделай сам»", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Вкладка Live включает живой разговор без слова «Джарвис». Перебивайте, когда хотите.", CollectionsKt.emptyList()), TuplesKt.to("Начните команду со слов «сделай сам», и Джарвис выполнит её в приложении: найдёт видео на YouTube, откроет раздел настроек или место на карте. «Стоп» прерывает. Пароли и карты он не вводит никогда.", CollectionsKt.emptyList())}), list, str, i, defaultConstructorMarker), new Part("Музыка и сценарии", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Установите SoundCloud и войдите в аккаунт: «включи Queen», «включи мою музыку», «пауза», «дальше». Без интернета Джарвис включит скачанное в Яндекс Музыке.", CollectionsKt.emptyList()), TuplesKt.to("Правила говорите вслух: «когда подключу наушники, включи музыку», «когда открою YouTube, громкость на максимум». Список правил — в разделе сценариев.", CollectionsKt.emptyList())}), null, null, 12, null), new Part("Если не слышит", CollectionsKt.listOf(TuplesKt.to("", CollectionsKt.listOf((Object[]) new String[]{"Проверьте, что ассистент включён.", "Батарея для Джарвиса: «без ограничений», автозапуск разрешён.", "Поднимите чувствительность слова или перезапишите голос.", "Отвечает «не понял» на вопросы: проверьте ключи и интернет."}))), list, str, i, defaultConstructorMarker)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sections$lambda$0(UsageGuideActivity this$0) {
        this$0.openSection(SECTION_AI);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sections$lambda$1(UsageGuideActivity this$0) {
        this$0.openSection(SECTION_VOICE);
        return Unit.INSTANCE;
    }

    private final View section(int index, Part part) {
        GradientDrawable drawable;
        String title;
        int ink;
        Typeface head;
        String str;
        int muted;
        Typeface body;
        LinearLayout box = new LinearLayout(this);
        int i = 1;
        box.setOrientation(1);
        box.setPadding(UiKt.dp((Context) this, 16), UiKt.dp((Context) this, 14), UiKt.dp((Context) this, 16), UiKt.dp((Context) this, 16));
        Look look = this.look;
        if (look == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look = null;
        }
        if (look.getClassic()) {
            drawable = ContextCompat.getDrawable(this, R.drawable.bg_hud_panel);
        } else {
            GradientDrawable $this$section_u24lambda_u240_u240 = new GradientDrawable();
            $this$section_u24lambda_u240_u240.setCornerRadius(UiKt.dp((Context) this, 18));
            Look look2 = this.look;
            if (look2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look2 = null;
            }
            $this$section_u24lambda_u240_u240.setColor(look2.getCard());
            int iDp = UiKt.dp((Context) this, 1);
            Look look3 = this.look;
            if (look3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look3 = null;
            }
            $this$section_u24lambda_u240_u240.setStroke(iDp, look3.getLine());
            drawable = $this$section_u24lambda_u240_u240;
        }
        box.setBackground(drawable);
        Look look4 = this.look;
        if (look4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look4 = null;
        }
        if (look4.getClassic()) {
            Integer numValueOf = Integer.valueOf(index);
            String upperCase = part.getTitle().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            title = String.format("%02d // %s", Arrays.copyOf(new Object[]{numValueOf, upperCase}, 2));
            Intrinsics.checkNotNullExpressionValue(title, "format(...)");
        } else {
            title = part.getTitle();
        }
        LinearLayout $this$section_u24lambda_u241 = new LinearLayout(this);
        int i2 = 0;
        $this$section_u24lambda_u241.setOrientation(0);
        $this$section_u24lambda_u241.setGravity(16);
        LinearLayout head2 = $this$section_u24lambda_u241;
        Look look5 = this.look;
        if (look5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look5 = null;
        }
        if (!look5.getClassic()) {
            String str2 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(index)}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            Look look6 = this.look;
            if (look6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look6 = null;
            }
            int accent = look6.getAccent();
            Look look7 = this.look;
            if (look7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look7 = null;
            }
            TextView $this$section_u24lambda_u242 = text(str2, 12.0f, accent, look7.getHead());
            $this$section_u24lambda_u242.setPadding(0, 0, UiKt.dp((Context) this, 10), 0);
            head2.addView($this$section_u24lambda_u242);
        }
        Look look8 = this.look;
        if (look8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look8 = null;
        }
        float f = look8.getClassic() ? 14.0f : 17.0f;
        Look look9 = this.look;
        if (look9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look9 = null;
        }
        if (look9.getClassic()) {
            Look look10 = this.look;
            if (look10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look10 = null;
            }
            ink = look10.getAccent();
        } else {
            Look look11 = this.look;
            if (look11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look11 = null;
            }
            ink = look11.getInk();
        }
        Look look12 = this.look;
        if (look12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look12 = null;
        }
        if (look12.getClassic()) {
            head = Typeface.create(Typeface.MONOSPACE, 1);
        } else {
            Look look13 = this.look;
            if (look13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look13 = null;
            }
            head = look13.getHead();
        }
        Intrinsics.checkNotNull(head);
        head2.addView(text(title, f, ink, head));
        box.addView(head2);
        Iterable $this$forEach$iv = part.getBlocks();
        for (Object element$iv : $this$forEach$iv) {
            Pair pair = (Pair) element$iv;
            String para = (String) pair.component1();
            Iterable steps = (List) pair.component2();
            if (!StringsKt.isBlank(para)) {
                int i3 = (((Collection) steps).isEmpty() || para.length() >= 40) ? i2 : i;
                float f2 = i3 != 0 ? 14.5f : 14.0f;
                Look look14 = this.look;
                if (i3 != 0) {
                    if (look14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("look");
                        look14 = null;
                    }
                    muted = look14.getInk();
                } else {
                    if (look14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("look");
                        look14 = null;
                    }
                    muted = look14.getMuted();
                }
                Look look15 = this.look;
                if (i3 != 0) {
                    if (look15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("look");
                        look15 = null;
                    }
                    body = Typeface.create(look15.getBody(), i);
                } else {
                    if (look15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("look");
                        look15 = null;
                    }
                    body = look15.getBody();
                }
                Intrinsics.checkNotNull(body);
                TextView $this$section_u24lambda_u243_u240 = text(para, f2, muted, body);
                $this$section_u24lambda_u243_u240.setPadding(0, UiKt.dp((Context) this, 10), 0, 0);
                $this$section_u24lambda_u243_u240.setLineSpacing(0.0f, 1.15f);
                Unit unit = Unit.INSTANCE;
                box.addView(link($this$section_u24lambda_u243_u240));
            }
            Iterable $this$forEachIndexed$iv = steps;
            int $i$f$forEachIndexed = 0;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv2 = index$iv + 1;
                if (index$iv < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                String step = (String) item$iv;
                Iterable $this$forEach$iv2 = $this$forEach$iv;
                int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                LinearLayout row = new LinearLayout(this);
                LinearLayout head3 = head2;
                row.setOrientation(0);
                String para2 = para;
                row.setPadding(0, UiKt.dp((Context) this, 6), 0, 0);
                Look look16 = this.look;
                if (look16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("look");
                    look16 = null;
                }
                String mark = look16.getClassic() ? "›" : (index$iv + 1) + ".";
                Look look17 = this.look;
                if (look17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("look");
                    look17 = null;
                }
                int accent2 = look17.getAccent();
                Look look18 = this.look;
                if (look18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("look");
                    look18 = null;
                }
                TextView $this$section_u24lambda_u243_u241_u241 = text(mark, 14.0f, accent2, look18.getHead());
                $this$section_u24lambda_u243_u241_u241.setMinWidth(UiKt.dp((Context) this, 20));
                row.addView($this$section_u24lambda_u243_u241_u241);
                Look look19 = this.look;
                if (look19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("look");
                    look19 = null;
                }
                int ink2 = look19.getInk();
                Look look20 = this.look;
                if (look20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("look");
                    look20 = null;
                }
                TextView $this$section_u24lambda_u243_u241_u242 = text(step, 14.0f, ink2, look20.getBody());
                $this$section_u24lambda_u243_u241_u242.setLineSpacing(0.0f, 1.15f);
                Unit unit2 = Unit.INSTANCE;
                row.addView(link($this$section_u24lambda_u243_u241_u242), new LinearLayout.LayoutParams(0, -2, 1.0f));
                box.addView(row);
                index$iv = index$iv2;
                $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEachIndexed = $i$f$forEachIndexed2;
                head2 = head3;
                para = para2;
            }
            title = title;
            i = 1;
            i2 = 0;
        }
        String it = part.getTip();
        if (it != null) {
            Look look21 = this.look;
            if (look21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look21 = null;
            }
            int muted2 = look21.getMuted();
            Look look22 = this.look;
            if (look22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look22 = null;
            }
            TextView $this$section_u24lambda_u244_u240 = text(it, 12.5f, muted2, look22.getBody());
            $this$section_u24lambda_u244_u240.setPadding(UiKt.dp((Context) this, 12), UiKt.dp((Context) this, 10), UiKt.dp((Context) this, 12), UiKt.dp((Context) this, 10));
            GradientDrawable $this$section_u24lambda_u244_u240_u240 = new GradientDrawable();
            UsageGuideActivity usageGuideActivity = this;
            Look look23 = this.look;
            if (look23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look23 = null;
            }
            $this$section_u24lambda_u244_u240_u240.setCornerRadius(UiKt.dp((Context) usageGuideActivity, look23.getClassic() ? 4 : 12));
            Look look24 = this.look;
            if (look24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look24 = null;
            }
            int i4 = look24.getLight() ? 30 : 22;
            Look look25 = this.look;
            if (look25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look25 = null;
            }
            int iRed = Color.red(look25.getWarm());
            Look look26 = this.look;
            if (look26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look26 = null;
            }
            int iGreen = Color.green(look26.getWarm());
            Look look27 = this.look;
            if (look27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look27 = null;
            }
            $this$section_u24lambda_u244_u240_u240.setColor(Color.argb(i4, iRed, iGreen, Color.blue(look27.getWarm())));
            $this$section_u24lambda_u244_u240.setBackground($this$section_u24lambda_u244_u240_u240);
            LinearLayout.LayoutParams $this$section_u24lambda_u244_u241 = new LinearLayout.LayoutParams(-1, -2);
            $this$section_u24lambda_u244_u241.topMargin = UiKt.dp((Context) this, 12);
            Unit unit3 = Unit.INSTANCE;
            box.addView($this$section_u24lambda_u244_u240, $this$section_u24lambda_u244_u241);
            Unit unit4 = Unit.INSTANCE;
            Unit unit5 = Unit.INSTANCE;
        }
        for (Object element$iv2 : part.getActions()) {
            Pair pair2 = (Pair) element$iv2;
            String label = (String) pair2.component1();
            Function0<Unit> function0 = (Function0) pair2.component2();
            Look look28 = this.look;
            if (look28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look28 = null;
            }
            if (look28.getClassic()) {
                String upperCase2 = label.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                str = "> " + upperCase2;
            } else {
                str = label;
            }
            TextView textViewButton = button(str, false, function0);
            LinearLayout.LayoutParams $this$section_u24lambda_u245_u240 = new LinearLayout.LayoutParams(-1, UiKt.dp((Context) this, 46));
            $this$section_u24lambda_u245_u240.topMargin = UiKt.dp((Context) this, 12);
            Unit unit6 = Unit.INSTANCE;
            box.addView(textViewButton, $this$section_u24lambda_u245_u240);
        }
        return box;
    }

    private final TextView link(TextView view) {
        Linkify.addLinks(view, 1);
        Look look = this.look;
        if (look == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look = null;
        }
        view.setLinkTextColor(look.getAccent());
        view.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }

    private final TextView button(String label, boolean primary, final Function0<Unit> onClick) {
        int accent;
        TextView $this$button_u24lambda_u240 = new TextView(this);
        $this$button_u24lambda_u240.setText(label);
        $this$button_u24lambda_u240.setGravity(17);
        Look look = this.look;
        Look look2 = null;
        if (look == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look = null;
        }
        $this$button_u24lambda_u240.setTextSize(look.getClassic() ? 13.0f : 15.0f);
        Look look3 = this.look;
        if (look3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look3 = null;
        }
        $this$button_u24lambda_u240.setTypeface(look3.getClassic() ? Typeface.create(Typeface.MONOSPACE, 1) : Fonts.INSTANCE.body(this, TypedValues.TransitionType.TYPE_DURATION));
        Look look4 = this.look;
        if (primary) {
            if (look4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look4 = null;
            }
            accent = look4.getOnAccent();
        } else {
            if (look4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
                look4 = null;
            }
            accent = look4.getAccent();
        }
        $this$button_u24lambda_u240.setTextColor(accent);
        GradientDrawable $this$button_u24lambda_u240_u240 = new GradientDrawable();
        UsageGuideActivity usageGuideActivity = this;
        Look look5 = this.look;
        if (look5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("look");
            look5 = null;
        }
        $this$button_u24lambda_u240_u240.setCornerRadius(UiKt.dp((Context) usageGuideActivity, look5.getClassic() ? 4 : 24));
        if (primary) {
            Look look6 = this.look;
            if (look6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
            } else {
                look2 = look6;
            }
            $this$button_u24lambda_u240_u240.setColor(look2.getAccent());
        } else {
            $this$button_u24lambda_u240_u240.setColor(0);
            int iDp = UiKt.dp((Context) this, 1);
            Look look7 = this.look;
            if (look7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("look");
            } else {
                look2 = look7;
            }
            $this$button_u24lambda_u240_u240.setStroke(iDp, look2.getAccent());
        }
        $this$button_u24lambda_u240.setBackground($this$button_u24lambda_u240_u240);
        $this$button_u24lambda_u240.setClickable(true);
        $this$button_u24lambda_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.UsageGuideActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                onClick.invoke();
            }
        });
        return $this$button_u24lambda_u240;
    }

    private final TextView text(String value, float size, int color, Typeface face) {
        TextView $this$text_u24lambda_u240 = new TextView(this);
        $this$text_u24lambda_u240.setText(value);
        $this$text_u24lambda_u240.setTextSize(size);
        $this$text_u24lambda_u240.setTextColor(color);
        $this$text_u24lambda_u240.setTypeface(face);
        return $this$text_u24lambda_u240;
    }

    private final void openSection(String title) {
        startActivity(new Intent(this, (Class<?>) JarvisActivity.class).putExtra(UiMode.EXTRA_FORCE_MODERN, true).putExtra(UiMode.EXTRA_OPEN_SECTION, title));
    }

    private final void done() {
        INSTANCE.markDone(this);
        if (isTaskRoot()) {
            UiMode.INSTANCE.continueFrom(this);
        } else {
            finish();
        }
    }

    /* JADX INFO: compiled from: UsageGuideActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/app/UsageGuideActivity$Companion;", "", "<init>", "()V", "PREFS", "", "PREF_USAGE_GUIDE_DONE", "SECTION_AI", "SECTION_VOICE", "markDone", "", "context", "Landroid/content/Context;", "needsGuide", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void markDone(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences("jarvis_prefs", 0).edit().putBoolean(UsageGuideActivity.PREF_USAGE_GUIDE_DONE, true).apply();
        }

        public final boolean needsGuide(AppCompatActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            SharedPreferences prefs = activity.getSharedPreferences("jarvis_prefs", 0);
            return !prefs.getBoolean(UsageGuideActivity.PREF_USAGE_GUIDE_DONE, false);
        }
    }
}
