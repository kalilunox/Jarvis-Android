package com.jarvis.app.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.jarvis.app.JarvisSettings;
import com.jarvis.app.JarvisWidgetProvider;
import com.jarvis.app.VoiceService;
import com.jarvis.core.model.AssistantState;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: HomeLive.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000W\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0013\u0018\u0000 12\u00020\u0001:\u00011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\"\u001a\u00020\u000bH\u0002J\b\u0010#\u001a\u00020\u000bH\u0002J\b\u0010$\u001a\u00020\u000bH\u0002J\b\u0010%\u001a\u00020\u000bH\u0002J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001bJ\b\u0010)\u001a\u00020'H\u0016J\b\u0010*\u001a\u00020'H\u0016J\b\u0010+\u001a\u00020'H\u0002J\u0010\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020'H\u0016J\b\u00100\u001a\u00020'H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00062"}, d2 = {"Lcom/jarvis/app/ui/HomeScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "reactor", "Lcom/jarvis/app/ui/ReactorView;", "wave", "Lcom/jarvis/app/ui/WaveView;", "statusDot", "Landroid/view/View;", "status", "Landroid/widget/TextView;", "clock", "bubble", "word", "hintText", "clockTick", "com/jarvis/app/ui/HomeScreen$clockTick$1", "Lcom/jarvis/app/ui/HomeScreen$clockTick$1;", "vitals", "weather", "weatherSub", "wasOn", "", "hintOverride", "", "weatherAt", "", "greeted", "view", "getView", "()Landroid/view/View;", "topBar", "stage", "voicePanel", "tiles", "hint", "", VoiceService.EXTRA_TEXT, "onShow", "onHide", "loadWeather", "onState", "state", "Lcom/jarvis/core/model/AssistantState;", "onData", "render", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HomeScreen extends Screen {

    @Deprecated
    public static final long CLOCK_TICK_MS = 15000;
    private static final Companion Companion = new Companion(null);
    private static final List<String> STEPS = CollectionsKt.listOf((Object[]) new String[]{"Распознаю речь", "Смотрю контекст", "Ищу сведения", "Формирую ответ", "Готовлю голос"});

    @Deprecated
    public static final long WEATHER_TTL_MS = 1800000;
    private final TextView bubble;
    private final TextView clock;
    private final HomeScreen$clockTick$1 clockTick;
    private boolean greeted;
    private String hintOverride;
    private final TextView hintText;
    private final ReactorView reactor;
    private final TextView status;
    private final View statusDot;
    private final View view;
    private final TextView vitals;
    private boolean wasOn;
    private final WaveView wave;
    private final TextView weather;
    private long weatherAt;
    private final TextView weatherSub;
    private final TextView word;

    /* JADX INFO: compiled from: HomeLive.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistantState.values().length];
            try {
                iArr[AssistantState.OFFLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistantState.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistantState.LISTENING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AssistantState.THINKING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AssistantState.EXECUTING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AssistantState.SPEAKING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[AssistantState.STARTING.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v19, types: [com.jarvis.app.ui.HomeScreen$clockTick$1] */
    public HomeScreen(final JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.reactor = new ReactorView(host, host.getSkin());
        this.wave = new WaveView(host, host.getSkin());
        this.statusDot = new View(host);
        JarvisActivity jarvisActivity = host;
        this.status = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getGood(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 1);
        JarvisActivity jarvisActivity2 = host;
        this.clock = UiKt.label(jarvisActivity2, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 22.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity2, 0, 2, null) : Fonts.INSTANCE.display(host, 500), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        JarvisActivity jarvisActivity3 = host;
        this.bubble = UiKt.label(jarvisActivity3, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity3, 0, 2, null) : Fonts.INSTANCE.body(host, 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 4);
        JarvisActivity jarvisActivity4 = host;
        this.word = UiKt.label(jarvisActivity4, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 16.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity4, 0, 2, null) : Fonts.INSTANCE.body(host, 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 1);
        JarvisActivity jarvisActivity5 = host;
        TextView $this$hintText_u24lambda_u240 = UiKt.label(jarvisActivity5, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity5, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 2);
        $this$hintText_u24lambda_u240.setMinLines(2);
        this.hintText = $this$hintText_u24lambda_u240;
        this.clockTick = new Runnable() { // from class: com.jarvis.app.ui.HomeScreen$clockTick$1
            @Override // java.lang.Runnable
            public void run() {
                this.this$0.clock.setText(Device.INSTANCE.time());
                this.this$0.vitals.setText(Device.INSTANCE.vitals(host));
                this.this$0.getView().postDelayed(this, HomeScreen.CLOCK_TICK_MS);
            }
        };
        JarvisActivity jarvisActivity6 = host;
        this.vitals = UiKt.label(jarvisActivity6, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 11.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity6, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 3);
        JarvisActivity jarvisActivity7 = host;
        this.weather = UiKt.label(jarvisActivity7, (8 & 1) != 0 ? "" : "—", (8 & 2) != 0 ? 14.0f : 20.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity7, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, host, 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        JarvisActivity jarvisActivity8 = host;
        this.weatherSub = UiKt.label(jarvisActivity8, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 11.5f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity8, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 1);
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(topBar());
        $this$view_u24lambda_u240.addView(stage(), UiKt.lp$default(0, 0, 1.0f, 1, null));
        View viewVoicePanel = voicePanel();
        LinearLayout.LayoutParams $this$view_u24lambda_u240_u240 = UiKt.lp$default(0, 0, 0.0f, 7, null);
        $this$view_u24lambda_u240_u240.setMargins(UiKt.dp((Context) host, 14), UiKt.dp((Context) host, 6), UiKt.dp((Context) host, 14), 0);
        Unit unit = Unit.INSTANCE;
        $this$view_u24lambda_u240.addView(viewVoicePanel, $this$view_u24lambda_u240_u240);
        View viewTiles = tiles();
        LinearLayout.LayoutParams $this$view_u24lambda_u240_u241 = UiKt.lp$default(0, 0, 0.0f, 7, null);
        $this$view_u24lambda_u240_u241.setMargins(UiKt.dp((Context) host, 14), UiKt.dp((Context) host, 8), UiKt.dp((Context) host, 14), UiKt.dp((Context) host, 30));
        Unit unit2 = Unit.INSTANCE;
        $this$view_u24lambda_u240.addView(viewTiles, $this$view_u24lambda_u240_u241);
        this.view = $this$view_u24lambda_u240;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    private final View topBar() {
        LinearLayout row = (LinearLayout) UiKt.pad(UiKt.rowLayout(getHost(), 10), 14, 10);
        LinearLayout left = UiKt.column$default(getHost(), 0, 1, null);
        JarvisActivity host = getHost();
        TextView $this$topBar_u24lambda_u240 = UiKt.label(host, (8 & 1) != 0 ? "" : "J.A.R.V.I.S.", (8 & 2) != 0 ? 14.0f : 20.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : Fonts.INSTANCE.brand(getHost()), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        $this$topBar_u24lambda_u240.setLetterSpacing(0.14f);
        $this$topBar_u24lambda_u240.setShadowLayer(16.0f, 0.0f, 0.0f, getSkin().getAccent());
        left.addView($this$topBar_u24lambda_u240);
        LinearLayout st = UiKt.rowLayout(getHost(), 6);
        st.setPadding(0, UiKt.dp((Context) getHost(), 3), 0, 0);
        st.addView(this.statusDot, UiKt.lp$default(UiKt.dp((Context) getHost(), 7), UiKt.dp((Context) getHost(), 7), 0.0f, 4, null));
        st.addView(this.status);
        left.addView(st);
        row.addView(left, UiKt.lp(0, -2, 1.0f));
        row.addView(this.clock);
        return row;
    }

    private final View stage() {
        FrameLayout stage = new FrameLayout(getHost());
        stage.addView(this.reactor, new FrameLayout.LayoutParams(-1, -1));
        View $this$stage_u24lambda_u240 = new View(getHost());
        $this$stage_u24lambda_u240.setContentDescription("Включить или выключить ассистента");
        UiKt.tap($this$stage_u24lambda_u240, new Function0() { // from class: com.jarvis.app.ui.HomeScreen$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeScreen.stage$lambda$0$0(this.f$0);
            }
        });
        stage.addView($this$stage_u24lambda_u240, new FrameLayout.LayoutParams(UiKt.dp((Context) getHost(), 220), UiKt.dp((Context) getHost(), 220), 17));
        int m = UiKt.dp((Context) getHost(), 14);
        TextView $this$stage_u24lambda_u241 = this.bubble;
        $this$stage_u24lambda_u241.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 12, 0, 0, 12, null));
        $this$stage_u24lambda_u241.setPadding(UiKt.dp((Context) getHost(), 10), UiKt.dp((Context) getHost(), 7), UiKt.dp((Context) getHost(), 10), UiKt.dp((Context) getHost(), 7));
        $this$stage_u24lambda_u241.setMaxWidth(UiKt.dp((Context) getHost(), 190));
        FrameLayout.LayoutParams $this$stage_u24lambda_u242 = new FrameLayout.LayoutParams(-2, -2, 8388659);
        $this$stage_u24lambda_u242.setMargins(m, UiKt.dp((Context) getHost(), 4), 0, 0);
        Unit unit = Unit.INSTANCE;
        stage.addView($this$stage_u24lambda_u241, $this$stage_u24lambda_u242);
        TextView $this$stage_u24lambda_u243 = this.vitals;
        $this$stage_u24lambda_u243.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 12, 0, 0, 12, null));
        $this$stage_u24lambda_u243.setPadding(UiKt.dp((Context) getHost(), 10), UiKt.dp((Context) getHost(), 7), UiKt.dp((Context) getHost(), 10), UiKt.dp((Context) getHost(), 7));
        UiKt.tap($this$stage_u24lambda_u243, new Function0() { // from class: com.jarvis.app.ui.HomeScreen$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeScreen.stage$lambda$3$0(this.f$0);
            }
        });
        FrameLayout.LayoutParams $this$stage_u24lambda_u244 = new FrameLayout.LayoutParams(-2, -2, 8388661);
        $this$stage_u24lambda_u244.setMargins(0, UiKt.dp((Context) getHost(), 4), m, 0);
        Unit unit2 = Unit.INSTANCE;
        stage.addView($this$stage_u24lambda_u243, $this$stage_u24lambda_u244);
        View viewPad = UiKt.pad(UiKt.rowLayout(getHost(), 8), 10, 7);
        LinearLayout $this$stage_u24lambda_u245 = (LinearLayout) viewPad;
        $this$stage_u24lambda_u245.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 12, 0, 0, 12, null));
        $this$stage_u24lambda_u245.addView(this.weather);
        $this$stage_u24lambda_u245.addView(this.weatherSub);
        UiKt.tap($this$stage_u24lambda_u245, new Function0() { // from class: com.jarvis.app.ui.HomeScreen$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeScreen.stage$lambda$5$0(this.f$0);
            }
        });
        LinearLayout wx = (LinearLayout) viewPad;
        FrameLayout.LayoutParams $this$stage_u24lambda_u246 = new FrameLayout.LayoutParams(-2, -2, 8388693);
        $this$stage_u24lambda_u246.setMargins(0, 0, m, UiKt.dp((Context) getHost(), 4));
        Unit unit3 = Unit.INSTANCE;
        stage.addView(wx, $this$stage_u24lambda_u246);
        View viewPad2 = UiKt.pad(UiKt.rowLayout(getHost(), 8), 10, 7);
        LinearLayout $this$stage_u24lambda_u247 = (LinearLayout) viewPad2;
        $this$stage_u24lambda_u247.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 12, 0, 0, 12, null));
        $this$stage_u24lambda_u247.addView(new IconView(getHost(), Ic.LIVE, getSkin().getAccent(), 20));
        LinearLayout $this$stage_u24lambda_u247_u240 = UiKt.column$default(getHost(), 0, 1, null);
        JarvisActivity host = getHost();
        $this$stage_u24lambda_u247_u240.addView(UiKt.label(host, (8 & 1) != 0 ? "" : "Режим Live", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        JarvisActivity host2 = getHost();
        $this$stage_u24lambda_u247_u240.addView(UiKt.label(host2, (8 & 1) != 0 ? "" : "Без слова", (8 & 2) != 0 ? 14.0f : 11.0f, getSkin().getGood(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        $this$stage_u24lambda_u247.addView($this$stage_u24lambda_u247_u240);
        UiKt.tap($this$stage_u24lambda_u247, new Function0() { // from class: com.jarvis.app.ui.HomeScreen$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeScreen.stage$lambda$7$1(this.f$0);
            }
        });
        LinearLayout liveChip = (LinearLayout) viewPad2;
        FrameLayout.LayoutParams $this$stage_u24lambda_u248 = new FrameLayout.LayoutParams(-2, -2, 8388691);
        $this$stage_u24lambda_u248.setMargins(m, 0, 0, UiKt.dp((Context) getHost(), 4));
        Unit unit4 = Unit.INSTANCE;
        stage.addView(liveChip, $this$stage_u24lambda_u248);
        return stage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit stage$lambda$0$0(HomeScreen this$0) {
        if (this$0.getHost().isOn()) {
            this$0.getHost().powerOff();
        } else {
            this$0.getHost().powerOn();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit stage$lambda$3$0(HomeScreen this$0) {
        this$0.getHost().show("status");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit stage$lambda$5$0(HomeScreen this$0) {
        this$0.getHost().show(JarvisActivity.SCAN);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit stage$lambda$7$1(HomeScreen this$0) {
        this$0.getHost().openLive();
        return Unit.INSTANCE;
    }

    private final View voicePanel() {
        LinearLayout row = (LinearLayout) UiKt.pad(UiKt.rowLayout(getHost(), 12), 12, 10);
        row.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 0, 0, 0, 14, null));
        row.setMinimumHeight(UiKt.dp((Context) getHost(), 66));
        LinearLayout t = UiKt.column$default(getHost(), 0, 1, null);
        t.addView(this.word);
        t.addView(this.hintText);
        row.addView(t, UiKt.lp(0, -2, 1.3f));
        row.addView(this.wave, UiKt.lp(0, UiKt.dp((Context) getHost(), 40), 1.0f));
        UiKt.tap(row, new Function0() { // from class: com.jarvis.app.ui.HomeScreen$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeScreen.voicePanel$lambda$0(this.f$0);
            }
        });
        return row;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit voicePanel$lambda$0(HomeScreen this$0) {
        this$0.getHost().show(JarvisActivity.CHAT);
        return Unit.INSTANCE;
    }

    private final View tiles() {
        LinearLayout row = UiKt.rowLayout(getHost(), 8);
        tiles$tile(this, row, Ic.BOLT, "Быстрые\nдействия", JarvisActivity.SKILLS);
        tiles$tile(this, row, Ic.CLOCK, "Рутины", JarvisActivity.ROUTINES);
        tiles$tile(this, row, Ic.CHART, "Статус\nсистемы", "status");
        tiles$tile(this, row, Ic.GLOBE, "Анализ\nситуации", JarvisActivity.SCAN);
        return row;
    }

    private static final void tiles$tile(final HomeScreen this$0, LinearLayout row, String icon, String title, final String id) {
        View viewPad = UiKt.pad(UiKt.column$default(this$0.getHost(), 0, 1, null), 2, 9);
        LinearLayout $this$tiles_u24tile_u24lambda_u240 = (LinearLayout) viewPad;
        $this$tiles_u24tile_u24lambda_u240.setGravity(17);
        $this$tiles_u24tile_u24lambda_u240.setBackground(UiKt.glassBg$default(this$0.getHost(), this$0.getSkin(), 0, 0, 0, 14, null));
        $this$tiles_u24tile_u24lambda_u240.addView(new IconView(this$0.getHost(), icon, this$0.getSkin().getAccent(), 0, 8, null));
        JarvisActivity host = this$0.getHost();
        TextView $this$tiles_u24tile_u24lambda_u240_u240 = UiKt.label(host, (8 & 1) != 0 ? "" : title, (8 & 2) != 0 ? 14.0f : 11.0f, this$0.getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 17, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        $this$tiles_u24tile_u24lambda_u240_u240.setPadding(0, UiKt.dp((Context) this$0.getHost(), 5), 0, 0);
        $this$tiles_u24tile_u24lambda_u240.addView($this$tiles_u24tile_u24lambda_u240_u240);
        UiKt.tap($this$tiles_u24tile_u24lambda_u240, new Function0() { // from class: com.jarvis.app.ui.HomeScreen$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeScreen.tiles$tile$lambda$0$1(this.f$0, id);
            }
        });
        LinearLayout box = (LinearLayout) viewPad;
        row.addView(box, UiKt.lp(0, -1, 1.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit tiles$tile$lambda$0$1(HomeScreen this$0, String $id) {
        this$0.getHost().show($id);
        return Unit.INSTANCE;
    }

    public final void hint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.hintOverride = text;
        render();
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        getView().removeCallbacks(this.clockTick);
        getView().post(this.clockTick);
        render();
        loadWeather();
    }

    @Override // com.jarvis.app.ui.Screen
    public void onHide() {
        getView().removeCallbacks(this.clockTick);
    }

    private final void loadWeather() {
        long now = System.currentTimeMillis();
        if (now - this.weatherAt < 1800000) {
            return;
        }
        this.weatherAt = now;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getHost()), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.HomeScreen$loadWeather$1, reason: invalid class name */
    /* JADX INFO: compiled from: HomeLive.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.HomeScreen$loadWeather$1", f = "HomeLive.kt", i = {}, l = {153}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeScreen.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objWithContext;
            List<String> groupValues;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new HomeScreen$loadWeather$1$message$1(HomeScreen.this, null), this);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objWithContext = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String message = (String) objWithContext;
            if (message == null) {
                message = "";
            }
            MatchResult matchResultFind$default = Regex.find$default(new Regex("сейчас (-?\\d+)°"), message, 0, 2, null);
            String degrees = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : groupValues.get(1);
            if (degrees == null) {
                HomeScreen.this.weatherAt = 0L;
                return Unit.INSTANCE;
            }
            HomeScreen.this.weather.setText((StringsKt.startsWith$default(degrees, "-", false, 2, (Object) null) ? "" : "+") + degrees + "°");
            TextView textView = HomeScreen.this.weatherSub;
            String str = (String) CollectionsKt.getOrNull(StringsKt.split$default((CharSequence) message, new String[]{". "}, false, 0, 6, (Object) null), 1);
            textView.setText(str != null ? str : "");
            SharedPreferences.Editor editorEdit = JarvisSettings.INSTANCE.prefs(HomeScreen.this.getHost()).edit();
            CharSequence text = HomeScreen.this.weather.getText();
            String lowerCase = HomeScreen.this.weatherSub.getText().toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            editorEdit.putString(JarvisWidgetProvider.PREF_WEATHER, ((Object) text) + ", " + lowerCase).apply();
            JarvisWidgetProvider.INSTANCE.refresh(HomeScreen.this.getHost());
            return Unit.INSTANCE;
        }
    }

    @Override // com.jarvis.app.ui.Screen
    public void onState(AssistantState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.hintOverride = null;
        render();
    }

    @Override // com.jarvis.app.ui.Screen
    public void onData() {
        render();
    }

    private final void render() {
        ReactorView.Mode mode;
        float f;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        AssistantState state = getHost().getAssistantState();
        boolean on = getHost().isOn();
        if (on && !this.wasOn) {
            this.reactor.kick();
        }
        this.wasOn = on;
        ReactorView reactorView = this.reactor;
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
            case 2:
                mode = ReactorView.Mode.OFF;
                break;
            case 3:
                mode = ReactorView.Mode.LISTEN;
                break;
            case 4:
                mode = ReactorView.Mode.THINK;
                break;
            case 5:
                mode = ReactorView.Mode.ACT;
                break;
            case 6:
                mode = ReactorView.Mode.SPEAK;
                break;
            default:
                mode = ReactorView.Mode.IDLE;
                break;
        }
        reactorView.setMode(mode);
        this.reactor.setStep(getHost().getAnswerStep());
        WaveView waveView = this.wave;
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
            case 2:
                f = 0.0f;
                break;
            case 3:
                f = 0.6f;
                break;
            case 4:
            case 5:
                f = 0.4f;
                break;
            case 6:
                f = 0.85f;
                break;
            default:
                f = 0.08f;
                break;
        }
        waveView.setLevel(f);
        TextView textView = this.word;
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
                str = "Выключен";
                break;
            case 2:
                str = "Сбой";
                break;
            case 3:
                str = "Слушаю…";
                break;
            case 4:
                str = "Думаю…";
                break;
            case 5:
                str = "Выполняю";
                break;
            case 6:
                str = "Говорю";
                break;
            case 7:
                str = "Запускаюсь";
                break;
            default:
                str = "Ожидаю";
                break;
        }
        textView.setText(str);
        TextView textView2 = this.hintText;
        String str6 = this.hintOverride;
        if (str6 != null) {
            str3 = str6;
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                    str2 = "Нажмите на ядро, чтобы включить";
                    break;
                case 2:
                    str2 = "Нажмите на ядро, чтобы перезапустить";
                    break;
                case 3:
                    String lastPartial = getHost().getLastPartial();
                    if (StringsKt.isBlank(lastPartial)) {
                        lastPartial = "Скажите, что нужно сделать";
                    }
                    str2 = lastPartial;
                    break;
                case 4:
                    List<String> list = STEPS;
                    int answerStep = getHost().getAnswerStep();
                    boolean z = false;
                    if (answerStep >= 0 && answerStep < list.size()) {
                        z = true;
                    }
                    str2 = z ? list.get(answerStep) : STEPS.get(1);
                    break;
                case 5:
                    str2 = "Выполняю действие";
                    break;
                case 6:
                    str2 = "Скажите «Джарвис», чтобы перебить";
                    break;
                case 7:
                    str2 = "Загружаю голос и модели";
                    break;
                default:
                    str2 = "Скажите «Джарвис» или нажмите на микрофон";
                    break;
            }
            str3 = str2;
        }
        textView2.setText(str3);
        TextView textView3 = this.status;
        if (on) {
            str4 = state == AssistantState.STARTING ? "Запускаюсь" : "Онлайн · слышу «Джарвис»";
        }
        textView3.setText(str4);
        TextView textView4 = this.status;
        Skin skin = getSkin();
        textView4.setTextColor(on ? skin.getGood() : skin.getMuted());
        View view = this.statusDot;
        GradientDrawable $this$render_u24lambda_u242 = new GradientDrawable();
        $this$render_u24lambda_u242.setShape(1);
        Skin skin2 = getSkin();
        $this$render_u24lambda_u242.setColor(on ? skin2.getGood() : skin2.getMuted());
        view.setBackground($this$render_u24lambda_u242);
        TextView textView5 = this.bubble;
        String lastResponse = getHost().getLastResponse();
        if (StringsKt.isBlank(lastResponse)) {
            if (StringsKt.isBlank(getHost().getLastRequest())) {
                if (on) {
                    str5 = Device.INSTANCE.greeting() + ", сэр. Чем займёмся?";
                } else {
                    str5 = "Ассистент выключен, сэр.";
                }
                lastResponse = str5;
            } else {
                lastResponse = "«" + getHost().getLastRequest() + "»";
            }
        }
        textView5.setText(lastResponse);
    }

    /* JADX INFO: compiled from: HomeLive.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/jarvis/app/ui/HomeScreen$Companion;", "", "<init>", "()V", "STEPS", "", "", "getSTEPS", "()Ljava/util/List;", "WEATHER_TTL_MS", "", "CLOCK_TICK_MS", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getSTEPS() {
            return HomeScreen.STEPS;
        }
    }
}
