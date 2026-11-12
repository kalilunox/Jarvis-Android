package com.jarvis.app.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import com.jarvis.app.VoiceController;
import com.jarvis.core.model.AssistantState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HomeLive.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000o\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\"\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010(\u001a\u00020%H\u0002J\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u001fH\u0002J\b\u0010-\u001a\u00020*H\u0002J\b\u0010.\u001a\u00020*H\u0002J\b\u0010/\u001a\u00020*H\u0016J\u0006\u00100\u001a\u00020*J\b\u00101\u001a\u00020*H\u0016J\u0010\u00102\u001a\u00020*2\u0006\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020*H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010#R\u0014\u0010$\u001a\u00020%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u00066"}, d2 = {"Lcom/jarvis/app/ui/LiveScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "skin", "Lcom/jarvis/app/ui/Skin;", "getSkin", "()Lcom/jarvis/app/ui/Skin;", "stageColor", "", "getStageColor", "()I", "mind", "Lcom/jarvis/app/ui/MindView;", "stat", "Landroid/widget/TextView;", "time", "you", "him", "toggle", "muteBtn", "Landroid/widget/FrameLayout;", "muteIcon", "Lcom/jarvis/app/ui/IconView;", "chips", "", "chipBox", "Landroid/widget/LinearLayout;", "talking", "", "pendingStart", "ticker", "com/jarvis/app/ui/LiveScreen$ticker$1", "Lcom/jarvis/app/ui/LiveScreen$ticker$1;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "build", "startTalk", "", "stopTalk", "said", "toggleMute", VoiceController.ENTRY_UI, "onShow", "sync", "onHide", "onState", "state", "Lcom/jarvis/core/model/AssistantState;", "onData", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LiveScreen extends Screen {
    private final LinearLayout chipBox;
    private final List<TextView> chips;
    private final TextView him;
    private final MindView mind;
    private final FrameLayout muteBtn;
    private final IconView muteIcon;
    private boolean pendingStart;
    private final Skin skin;
    private final int stageColor;
    private final TextView stat;
    private boolean talking;
    private final LiveScreen$ticker$1 ticker;
    private final TextView time;
    private final TextView toggle;
    private final View view;
    private final TextView you;

    /* JADX INFO: compiled from: HomeLive.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistantState.values().length];
            try {
                iArr[AssistantState.THINKING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistantState.EXECUTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistantState.SPEAKING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v21, types: [com.jarvis.app.ui.LiveScreen$ticker$1] */
    public LiveScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.skin = HomeLiveKt.darkened(host.getSkin());
        this.stageColor = JarvisActivityKt.mix(ViewCompat.MEASURED_STATE_MASK, getSkin().getViolet(), 0.06f);
        this.mind = new MindView(host, getSkin());
        JarvisActivity jarvisActivity = host;
        this.stat = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        JarvisActivity jarvisActivity2 = host;
        this.time = UiKt.label(jarvisActivity2, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 16.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity2, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, host, 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        JarvisActivity jarvisActivity3 = host;
        this.you = UiKt.label(jarvisActivity3, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity3, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 3);
        JarvisActivity jarvisActivity4 = host;
        this.him = UiKt.label(jarvisActivity4, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 18.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity4, 0, 2, null) : Fonts.INSTANCE.body(host, 500), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 5);
        this.toggle = new TextView(host);
        this.muteBtn = new FrameLayout(host);
        this.muteIcon = new IconView(host, Ic.MIC, getSkin().getText(), 0, 8, null);
        Iterable $this$map$iv = CollectionsKt.listOf((Object[]) new String[]{"Слушаю", "Думаю", "Говорю"});
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            JarvisActivity jarvisActivity5 = host;
            destination$iv$iv.add(UiKt.label(jarvisActivity5, (8 & 1) != 0 ? "" : it, (8 & 2) != 0 ? 14.0f : 11.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity5, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 17, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        }
        this.chips = (List) destination$iv$iv;
        this.chipBox = UiKt.column(host, 6);
        this.ticker = new Runnable() { // from class: com.jarvis.app.ui.LiveScreen$ticker$1
            @Override // java.lang.Runnable
            public void run() {
                if (this.this$0.talking) {
                    long s = (System.currentTimeMillis() - VoiceController.INSTANCE.getLiveStartedAt()) / ((long) 1000);
                    TextView textView = this.this$0.time;
                    long j = 60;
                    String str = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(s / j), Long.valueOf(s % j)}, 2));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    textView.setText(str);
                    this.this$0.getView().postDelayed(this, 1000L);
                }
            }
        };
        this.view = build();
    }

    @Override // com.jarvis.app.ui.Screen
    protected Skin getSkin() {
        return this.skin;
    }

    public final int getStageColor() {
        return this.stageColor;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    private final View build() {
        LinearLayout root = UiKt.column$default(getHost(), 0, 1, null);
        root.setBackgroundColor(this.stageColor);
        root.setClickable(true);
        LinearLayout bar = (LinearLayout) UiKt.pad(UiKt.rowLayout(getHost(), 8), 14, 10);
        FrameLayout $this$build_u24lambda_u241 = new FrameLayout(getHost());
        $this$build_u24lambda_u241.addView(new IconView(getHost(), Ic.BACK, getSkin().getAccent(), 0, 8, null), new FrameLayout.LayoutParams(-2, -2, 17));
        $this$build_u24lambda_u241.setContentDescription("Назад");
        UiKt.tap($this$build_u24lambda_u241, new Function0() { // from class: com.jarvis.app.ui.LiveScreen$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LiveScreen.build$lambda$1$0(this.f$0);
            }
        });
        bar.addView($this$build_u24lambda_u241, UiKt.lp$default(UiKt.dp((Context) getHost(), 42), UiKt.dp((Context) getHost(), 42), 0.0f, 4, null));
        LinearLayout title = UiKt.column$default(getHost(), 0, 1, null);
        JarvisActivity host = getHost();
        TextView $this$build_u24lambda_u242 = UiKt.label(host, (8 & 1) != 0 ? "" : "LIVE", (8 & 2) != 0 ? 14.0f : 15.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, getHost(), 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        $this$build_u24lambda_u242.setLetterSpacing(0.1f);
        title.addView($this$build_u24lambda_u242);
        title.addView(this.stat);
        bar.addView(title, UiKt.lp(0, -2, 1.0f));
        bar.addView(this.time);
        root.addView(bar);
        FrameLayout stage = new FrameLayout(getHost());
        stage.addView(this.mind, new FrameLayout.LayoutParams(-1, -1));
        Iterable $this$forEach$iv = this.chips;
        for (Object element$iv : $this$forEach$iv) {
            TextView c = (TextView) element$iv;
            c.setPadding(UiKt.dp((Context) getHost(), 9), UiKt.dp((Context) getHost(), 6), UiKt.dp((Context) getHost(), 9), UiKt.dp((Context) getHost(), 6));
            c.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 10, 0, 0, 12, null));
            this.chipBox.addView(c, UiKt.lp$default(UiKt.dp((Context) getHost(), 84), -2, 0.0f, 4, null));
        }
        LinearLayout linearLayout = this.chipBox;
        FrameLayout.LayoutParams $this$build_u24lambda_u244 = new FrameLayout.LayoutParams(-2, -2, 8388661);
        $this$build_u24lambda_u244.setMargins(0, UiKt.dp((Context) getHost(), 8), UiKt.dp((Context) getHost(), 10), 0);
        Unit unit = Unit.INSTANCE;
        stage.addView(linearLayout, $this$build_u24lambda_u244);
        root.addView(stage, UiKt.lp$default(0, 0, 1.0f, 1, null));
        View viewPad = UiKt.pad(UiKt.column(getHost(), 4), 20, 0);
        LinearLayout $this$build_u24lambda_u245 = (LinearLayout) viewPad;
        $this$build_u24lambda_u245.setMinimumHeight(UiKt.dp((Context) getHost(), 96));
        LinearLayout text = (LinearLayout) viewPad;
        text.addView(this.you);
        text.addView(this.him);
        root.addView(text);
        View viewPad2 = UiKt.pad(UiKt.rowLayout(getHost(), 20), 16, 10);
        LinearLayout $this$build_u24lambda_u246 = (LinearLayout) viewPad2;
        $this$build_u24lambda_u246.setGravity(17);
        LinearLayout ctl = (LinearLayout) viewPad2;
        FrameLayout $this$build_u24lambda_u247 = this.muteBtn;
        $this$build_u24lambda_u247.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 26, 0, 0, 12, null));
        $this$build_u24lambda_u247.addView(this.muteIcon, new FrameLayout.LayoutParams(-2, -2, 17));
        $this$build_u24lambda_u247.setContentDescription("Выключить микрофон");
        UiKt.tap($this$build_u24lambda_u247, new Function0() { // from class: com.jarvis.app.ui.LiveScreen$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LiveScreen.build$lambda$7$0(this.f$0);
            }
        });
        ctl.addView(this.muteBtn, UiKt.lp$default(UiKt.dp((Context) getHost(), 52), UiKt.dp((Context) getHost(), 52), 0.0f, 4, null));
        TextView $this$build_u24lambda_u248 = this.toggle;
        $this$build_u24lambda_u248.setTextSize(15.0f);
        $this$build_u24lambda_u248.setTypeface(Fonts.INSTANCE.body(getHost(), TypedValues.TransitionType.TYPE_DURATION));
        $this$build_u24lambda_u248.setGravity(17);
        $this$build_u24lambda_u248.setPadding(UiKt.dp((Context) getHost(), 26), 0, UiKt.dp((Context) getHost(), 26), 0);
        UiKt.tap($this$build_u24lambda_u248, new Function0() { // from class: com.jarvis.app.ui.LiveScreen$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LiveScreen.build$lambda$8$0(this.f$0);
            }
        });
        ctl.addView(this.toggle, UiKt.lp$default(-2, UiKt.dp((Context) getHost(), 52), 0.0f, 4, null));
        root.addView(ctl);
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit build$lambda$1$0(LiveScreen this$0) {
        this$0.getHost().closeLive();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit build$lambda$7$0(LiveScreen this$0) {
        this$0.toggleMute();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit build$lambda$8$0(LiveScreen this$0) {
        if (this$0.talking) {
            this$0.stopTalk(true);
        } else {
            this$0.startTalk();
        }
        return Unit.INSTANCE;
    }

    public final void startTalk() {
        if (!getHost().isOn()) {
            getHost().powerOn();
        }
        this.pendingStart = !VoiceController.INSTANCE.isRunning();
        this.talking = true;
        VoiceController.INSTANCE.setLiveStartedAt(System.currentTimeMillis());
        VoiceController.INSTANCE.setLiveTalk(true);
        VoiceController.INSTANCE.setLiveMuted(false);
        this.him.setText("Я здесь, сэр. Говорите, когда будете готовы.");
        this.you.setText("");
        ui();
        getView().post(this.ticker);
    }

    private final void stopTalk(boolean said) {
        this.talking = false;
        VoiceController.INSTANCE.setLiveTalk(false);
        VoiceController.INSTANCE.setLiveMuted(false);
        VoiceController.INSTANCE.stopSpeaking();
        if (said) {
            this.him.setText("Разговор завершён, сэр.");
        }
        this.you.setText("");
        ui();
    }

    private final void toggleMute() {
        VoiceController.INSTANCE.setLiveMuted(!VoiceController.INSTANCE.getLiveMuted());
        this.muteIcon.setTint(VoiceController.INSTANCE.getLiveMuted() ? getSkin().getWarn() : getSkin().getText());
        this.muteIcon.invalidate();
    }

    private final void ui() {
        int onAccent;
        int iAlpha;
        this.toggle.setText(this.talking ? "Завершить" : "Начать разговор");
        TextView textView = this.toggle;
        if (this.talking) {
            onAccent = getSkin().getDanger();
        } else {
            onAccent = getSkin().getLight() ? getSkin().getOnAccent() : getSkin().getText();
        }
        textView.setTextColor(onAccent);
        TextView textView2 = this.toggle;
        JarvisActivity host = getHost();
        Skin skin = getSkin();
        if (this.talking) {
            iAlpha = 0;
        } else {
            iAlpha = UiKt.alpha(getSkin().getAccent(), getSkin().getLight() ? 1.0f : 0.3f);
        }
        textView2.setBackground(UiKt.glassBg(host, skin, 26, iAlpha, this.talking ? getSkin().getDanger() : getSkin().getAccent()));
        this.stat.setText(this.talking ? "Идёт разговор, слово активации не нужно" : "Разговор выключен");
        this.stat.setTextColor(this.talking ? getSkin().getGood() : getSkin().getMuted());
        this.muteBtn.setVisibility(this.talking ? 0 : 8);
        this.chipBox.setVisibility(this.talking ? 0 : 8);
        if (!this.talking) {
            this.time.setText("");
            this.mind.setMood(MindView.Mood.SLEEP);
        } else {
            onState(getHost().getAssistantState());
        }
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        this.talking = VoiceController.INSTANCE.getLiveTalk();
        this.him.setText("Нажмите «Начать разговор», чтобы говорить без слова активации, сэр.");
        ui();
        if (this.talking) {
            getView().removeCallbacks(this.ticker);
            getView().post(this.ticker);
            onData();
        }
    }

    public final void sync() {
        if (this.talking == VoiceController.INSTANCE.getLiveTalk()) {
            return;
        }
        this.talking = VoiceController.INSTANCE.getLiveTalk();
        if (!this.talking) {
            this.him.setText("Разговор завершён, сэр.");
        }
        ui();
        if (this.talking) {
            getView().removeCallbacks(this.ticker);
            getView().post(this.ticker);
        }
    }

    @Override // com.jarvis.app.ui.Screen
    public void onHide() {
        if (this.talking) {
            stopTalk(false);
        }
    }

    @Override // com.jarvis.app.ui.Screen
    public void onState(AssistantState state) {
        int index;
        Intrinsics.checkNotNullParameter(state, "state");
        if (this.talking) {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                case 2:
                    index = 1;
                    break;
                case 3:
                    index = 2;
                    break;
                default:
                    index = 0;
                    break;
            }
            this.mind.setMood((MindView.Mood) CollectionsKt.listOf((Object[]) new MindView.Mood[]{MindView.Mood.LISTEN, MindView.Mood.THINK, MindView.Mood.SPEAK}).get(index));
            Iterable $this$forEachIndexed$iv = this.chips;
            int i = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                TextView c = (TextView) item$iv;
                Skin skin = getSkin();
                c.setTextColor(i == index ? skin.getText() : skin.getMuted());
                JarvisActivity host = getHost();
                Skin skin2 = getSkin();
                int glass = getSkin().getGlass();
                Skin skin3 = getSkin();
                c.setBackground(UiKt.glassBg(host, skin2, 10, glass, i == index ? skin3.getViolet() : skin3.getLine()));
                i = index$iv;
            }
        }
    }

    @Override // com.jarvis.app.ui.Screen
    public void onData() {
        if (this.talking) {
            TextView textView = this.you;
            String it = getHost().getLastPartial();
            if (StringsKt.isBlank(it)) {
                it = getHost().getLastRequest();
            }
            if (StringsKt.isBlank(it)) {
                it = null;
            }
            String it2 = it;
            String it3 = it2 != null ? "«" + it2 + "»" : null;
            if (it3 == null) {
                it3 = "";
            }
            textView.setText(it3);
            if (StringsKt.isBlank(getHost().getLastResponse())) {
                return;
            }
            this.him.setText(getHost().getLastResponse());
        }
    }
}
