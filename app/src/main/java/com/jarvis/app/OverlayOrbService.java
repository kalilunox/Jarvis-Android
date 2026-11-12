package com.jarvis.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.ui.Fonts;
import com.jarvis.app.ui.ReactorView;
import com.jarvis.app.ui.Skin;
import com.jarvis.app.ui.Skins;
import com.jarvis.app.ui.UiKt;
import com.jarvis.core.event.AssistantEvent;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: OverlayOrbService.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 62\u00020\u0001:\u00016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!H\u0002J.\u0010\"\u001a\u00020\r2\u0006\u0010 \u001a\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00172\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001d0'H\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\tH\u0002J\b\u0010.\u001a\u00020\u001dH\u0002J\b\u0010/\u001a\u00020\u001dH\u0002J\b\u00100\u001a\u00020\u001dH\u0002J\b\u00101\u001a\u000202H\u0002J\u0014\u00103\u001a\u0004\u0018\u0001042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u00105\u001a\u00020\u001dH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/jarvis/app/OverlayOrbService;", "Landroid/app/Service;", "<init>", "()V", "window", "Landroid/view/WindowManager;", "root", "Landroid/widget/LinearLayout;", "params", "Landroid/view/WindowManager$LayoutParams;", "core", "Lcom/jarvis/app/ui/ReactorView;", "said", "Landroid/widget/TextView;", "lastState", "Lcom/jarvis/core/model/AssistantState;", "pod", "status", "expanded", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "show", "", "paint", "buildPod", "skin", "Lcom/jarvis/app/ui/Skin;", "chip", VoiceService.EXTRA_TEXT, "", TypedValues.Custom.S_COLOR, "click", "Lkotlin/Function0;", "endTalk", "dragListener", "Landroid/view/View$OnTouchListener;", "view", "Landroid/view/View;", "lp", "togglePod", "openLive", "remove", "notification", "Landroid/app/Notification;", "onBind", "Landroid/os/IBinder;", "onDestroy", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OverlayOrbService extends Service {
    public static final String ACTION_HIDE = "com.jarvis.assistant.OVERLAY_HIDE";
    public static final String ACTION_LIVE = "com.jarvis.assistant.OVERLAY_LIVE";
    public static final String ACTION_SHOW = "com.jarvis.assistant.OVERLAY_SHOW";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int NOTIFICATION_ID = 42;
    private static final int ORB_DP = 76;
    private static final int POD_DP = 236;
    private ReactorView core;
    private boolean expanded;
    private WindowManager.LayoutParams params;
    private LinearLayout pod;
    private LinearLayout root;
    private TextView said;
    private TextView status;
    private WindowManager window;
    private AssistantState lastState = AssistantState.LISTENING;
    private CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()));

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case 57616701:
                    if (action.equals(ACTION_HIDE)) {
                        remove();
                        stopSelf();
                        return 2;
                    }
                    break;
                case 57736423:
                    if (action.equals(ACTION_LIVE)) {
                        openLive();
                    }
                    break;
            }
        }
        if (!VoiceController.INSTANCE.getLiveTalk() || !Settings.canDrawOverlays(this)) {
            stopSelf();
            return 2;
        }
        Notification note = notification();
        if (Build.VERSION.SDK_INT >= 34) {
            startForeground(42, note, BasicMeasure.EXACTLY);
        } else {
            startForeground(42, note);
        }
        show();
        return 1;
    }

    private final void show() {
        Unit unit;
        if (this.root != null) {
            return;
        }
        this.window = (WindowManager) getSystemService(WindowManager.class);
        Skin skin = Skins.INSTANCE.current(this);
        int size = UiKt.dp((Context) this, 76);
        ReactorView reactor = new ReactorView(this, skin);
        this.core = reactor;
        FrameLayout $this$show_u24lambda_u240 = new FrameLayout(this);
        GradientDrawable $this$show_u24lambda_u240_u240 = new GradientDrawable();
        $this$show_u24lambda_u240_u240.setShape(1);
        $this$show_u24lambda_u240_u240.setColor(UiKt.alpha(skin.getBg(), 0.92f));
        $this$show_u24lambda_u240_u240.setStroke(UiKt.dp((Context) this, 1), UiKt.alpha(skin.getAccent(), 0.8f));
        $this$show_u24lambda_u240.setBackground($this$show_u24lambda_u240_u240);
        $this$show_u24lambda_u240.setContentDescription("Орб Live");
        $this$show_u24lambda_u240.addView(reactor, new FrameLayout.LayoutParams(-1, -1));
        LinearLayout podView = buildPod(skin);
        this.pod = podView;
        podView.setVisibility(8);
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(0);
        box.setGravity(16);
        box.addView($this$show_u24lambda_u240, new LinearLayout.LayoutParams(size, size));
        LinearLayout.LayoutParams $this$show_u24lambda_u241_u240 = new LinearLayout.LayoutParams(UiKt.dp((Context) this, POD_DP), -2);
        $this$show_u24lambda_u241_u240.setMarginStart(UiKt.dp((Context) this, 8));
        Unit unit2 = Unit.INSTANCE;
        box.addView(podView, $this$show_u24lambda_u241_u240);
        this.root = box;
        WindowManager.LayoutParams $this$show_u24lambda_u242 = new WindowManager.LayoutParams(-2, -2, 2038, 520, -3);
        $this$show_u24lambda_u242.gravity = 8388659;
        SharedPreferences prefs = getSharedPreferences("jarvis_prefs", 0);
        int maxX = (getResources().getDisplayMetrics().widthPixels - size) - UiKt.dp((Context) this, 12);
        int maxY = (getResources().getDisplayMetrics().heightPixels - size) - UiKt.dp((Context) this, 12);
        $this$show_u24lambda_u242.x = RangesKt.coerceIn(prefs.getInt("overlay_x", maxX), UiKt.dp((Context) this, 12), maxX);
        $this$show_u24lambda_u242.y = RangesKt.coerceIn(prefs.getInt("overlay_y", getResources().getDisplayMetrics().heightPixels / 2), UiKt.dp((Context) this, 12), maxY);
        this.params = $this$show_u24lambda_u242;
        $this$show_u24lambda_u240.setOnTouchListener(dragListener(box, $this$show_u24lambda_u242));
        try {
            Result.Companion companion = Result.INSTANCE;
            OverlayOrbService $this$show_u24lambda_u243 = this;
            WindowManager windowManager = $this$show_u24lambda_u243.window;
            if (windowManager != null) {
                windowManager.addView(box, $this$show_u24lambda_u242);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass2(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass3(null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.OverlayOrbService$show$2, reason: invalid class name */
    /* JADX INFO: compiled from: OverlayOrbService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.OverlayOrbService$show$2", f = "OverlayOrbService.kt", i = {}, l = {WorkQueueKt.MASK}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OverlayOrbService.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<AssistantState> assistantState = JarvisEvents.INSTANCE.getAssistantState();
                    final OverlayOrbService overlayOrbService = OverlayOrbService.this;
                    this.label = 1;
                    if (assistantState.collect(new FlowCollector() { // from class: com.jarvis.app.OverlayOrbService.show.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((AssistantState) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(AssistantState state, Continuation<? super Unit> continuation) {
                            overlayOrbService.lastState = state;
                            overlayOrbService.paint();
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: com.jarvis.app.OverlayOrbService$show$3, reason: invalid class name */
    /* JADX INFO: compiled from: OverlayOrbService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.OverlayOrbService$show$3", f = "OverlayOrbService.kt", i = {}, l = {133}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OverlayOrbService.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    SharedFlow<AssistantEvent> events = JarvisEvents.INSTANCE.getEvents();
                    final OverlayOrbService overlayOrbService = OverlayOrbService.this;
                    this.label = 1;
                    if (events.collect(new FlowCollector() { // from class: com.jarvis.app.OverlayOrbService.show.3.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((AssistantEvent) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(AssistantEvent event, Continuation<? super Unit> continuation) {
                            TextView textView;
                            if (event instanceof AssistantEvent.RequestReceived) {
                                TextView textView2 = overlayOrbService.said;
                                if (textView2 != null) {
                                    textView2.setText("«" + ((AssistantEvent.RequestReceived) event).getText() + "»");
                                }
                            } else if ((event instanceof AssistantEvent.ResponseGenerated) && (textView = overlayOrbService.said) != null) {
                                textView.setText(((AssistantEvent.ResponseGenerated) event).getText());
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void paint() {
        String str;
        ReactorView.Mode mode;
        boolean muted = VoiceController.INSTANCE.getLiveMuted();
        ReactorView reactorView = this.core;
        if (reactorView != null) {
            if (muted) {
                mode = ReactorView.Mode.OFF;
            } else if (this.lastState == AssistantState.THINKING) {
                mode = ReactorView.Mode.THINK;
            } else if (this.lastState == AssistantState.EXECUTING) {
                mode = ReactorView.Mode.ACT;
            } else {
                mode = this.lastState == AssistantState.SPEAKING ? ReactorView.Mode.SPEAK : ReactorView.Mode.LISTEN;
            }
            reactorView.setMode(mode);
        }
        TextView textView = this.status;
        if (textView != null) {
            if (!muted) {
                if (this.lastState != AssistantState.THINKING && this.lastState != AssistantState.EXECUTING) {
                    str = this.lastState == AssistantState.SPEAKING ? "Говорю" : "Слушаю";
                }
            }
            textView.setText(str);
        }
    }

    private final LinearLayout buildPod(Skin skin) {
        View viewPad = UiKt.pad(UiKt.column(this, 8), 12);
        LinearLayout $this$buildPod_u24lambda_u240 = (LinearLayout) viewPad;
        $this$buildPod_u24lambda_u240.setBackground(UiKt.glassBg(this, skin, 18, UiKt.alpha(skin.getBg(), 0.96f), skin.getLine()));
        LinearLayout box = (LinearLayout) viewPad;
        OverlayOrbService overlayOrbService = this;
        TextView st = UiKt.label(overlayOrbService, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 12.0f, skin.getGood(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, overlayOrbService, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        this.status = st;
        box.addView(st);
        OverlayOrbService overlayOrbService2 = this;
        TextView text = UiKt.label(overlayOrbService2, (8 & 1) != 0 ? "" : "Разговор продолжается, сэр.", (8 & 2) != 0 ? 14.0f : 14.0f, skin.getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, overlayOrbService2, 0, 2, null) : Fonts.INSTANCE.body(this, 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 3);
        this.said = text;
        box.addView(text);
        LinearLayout row = UiKt.rowLayout(this, 6);
        final TextView micText = chip(skin, "Микрофон", skin.getText(), new Function0() { // from class: com.jarvis.app.OverlayOrbService$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        });
        micText.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OverlayOrbService$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OverlayOrbService.buildPod$lambda$2(micText, this, view);
            }
        });
        row.addView(micText, new LinearLayout.LayoutParams(0, UiKt.dp((Context) this, 38), 1.0f));
        row.addView(chip(skin, "В Live", skin.getText(), new Function0() { // from class: com.jarvis.app.OverlayOrbService$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OverlayOrbService.buildPod$lambda$3(this.f$0);
            }
        }), new LinearLayout.LayoutParams(0, UiKt.dp((Context) this, 38), 1.0f));
        row.addView(chip(skin, "Завершить", skin.getDanger(), new Function0() { // from class: com.jarvis.app.OverlayOrbService$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OverlayOrbService.buildPod$lambda$4(this.f$0);
            }
        }), new LinearLayout.LayoutParams(0, UiKt.dp((Context) this, 38), 1.2f));
        box.addView(row);
        return box;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPod$lambda$2(TextView $micText, OverlayOrbService this$0, View it) {
        VoiceController.INSTANCE.setLiveMuted(!VoiceController.INSTANCE.getLiveMuted());
        $micText.setText(VoiceController.INSTANCE.getLiveMuted() ? "Включить" : "Микрофон");
        this$0.paint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit buildPod$lambda$3(OverlayOrbService this$0) {
        this$0.openLive();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit buildPod$lambda$4(OverlayOrbService this$0) {
        this$0.endTalk();
        return Unit.INSTANCE;
    }

    private final TextView chip(Skin skin, String text, int color, final Function0<Unit> click) {
        OverlayOrbService overlayOrbService = this;
        final TextView $this$chip_u24lambda_u240 = UiKt.label(overlayOrbService, (8 & 1) != 0 ? "" : text, (8 & 2) != 0 ? 14.0f : 11.5f, color, (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, overlayOrbService, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 17, (8 & 32) != 0 ? Integer.MAX_VALUE : 1);
        $this$chip_u24lambda_u240.setGravity(17);
        $this$chip_u24lambda_u240.setBackground(UiKt.glassBg(this, skin, 12, UiKt.alpha(skin.getText(), 0.05f), color == skin.getDanger() ? skin.getDanger() : skin.getLine()));
        $this$chip_u24lambda_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OverlayOrbService$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OverlayOrbService.chip$lambda$0$0($this$chip_u24lambda_u240, click, view);
            }
        });
        return $this$chip_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chip$lambda$0$0(TextView $this_apply, Function0 $click, View it) {
        HudFeedback.INSTANCE.tick($this_apply);
        $click.invoke();
    }

    private final void endTalk() {
        VoiceController.INSTANCE.setLiveTalk(false);
        VoiceController.INSTANCE.setLiveMuted(false);
        VoiceController.INSTANCE.stopSpeaking();
        remove();
        stopSelf();
    }

    private final View.OnTouchListener dragListener(final View view, final WindowManager.LayoutParams lp) {
        return new View.OnTouchListener() { // from class: com.jarvis.app.OverlayOrbService$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return OverlayOrbService.dragListener$lambda$0(this.f$0, view, lp, view2, motionEvent);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:54:0x0135  */
    public static final boolean dragListener$lambda$0(OverlayOrbService this$0, View $view, WindowManager.LayoutParams $lp, View view, MotionEvent event) {
        int iCoerceIn;
        SharedPreferences prefs = this$0.getSharedPreferences("jarvis_prefs", 0);
        Unit unit = null;
        switch (event.getActionMasked()) {
            case 0:
                $view.setTag(R.id.homeOrb, new float[]{event.getRawX(), event.getRawY(), $lp.x, $lp.y, 0.0f});
                return true;
            case 1:
                Object tag = $view.getTag(R.id.homeOrb);
                float[] tag2 = tag instanceof float[] ? (float[]) tag : null;
                if (tag2 != null) {
                    if (tag2[4] == 0.0f) {
                        this$0.togglePod();
                    } else {
                        int screenW = this$0.getResources().getDisplayMetrics().widthPixels;
                        int margin = (int) (12 * this$0.getResources().getDisplayMetrics().density);
                        int width = RangesKt.coerceAtLeast($view.getWidth(), 1);
                        boolean snap = this$0.getSharedPreferences("jarvis_prefs", 0).getBoolean("companion_orb_snap", true);
                        if (snap) {
                            iCoerceIn = $lp.x + (width / 2) < screenW / 2 ? margin : (screenW - width) - margin;
                        } else {
                            iCoerceIn = RangesKt.coerceIn($lp.x, 0, screenW - width);
                        }
                        $lp.x = iCoerceIn;
                        $lp.y = RangesKt.coerceIn($lp.y, 0, this$0.getResources().getDisplayMetrics().heightPixels - $view.getHeight());
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            WindowManager windowManager = this$0.window;
                            if (windowManager != null) {
                                windowManager.updateViewLayout($view, $lp);
                                unit = Unit.INSTANCE;
                            }
                            Result.m392constructorimpl(unit);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            Result.m392constructorimpl(ResultKt.createFailure(th));
                        }
                        prefs.edit().putInt("overlay_x", $lp.x).putInt("overlay_y", $lp.y).apply();
                    }
                    break;
                } else {
                    this$0.togglePod();
                }
                return true;
            case 2:
                Object tag3 = $view.getTag(R.id.homeOrb);
                float[] fArr = tag3 instanceof float[] ? (float[]) tag3 : null;
                if (fArr == null) {
                    return true;
                }
                float[] tag4 = fArr;
                float dx = event.getRawX() - tag4[0];
                float dy = event.getRawY() - tag4[1];
                if ((dx * dx) + (dy * dy) > 64.0f) {
                    tag4[4] = 1.0f;
                }
                if (tag4[4] == 1.0f) {
                    $lp.x = (int) (tag4[2] + dx);
                    $lp.y = (int) (tag4[3] + dy);
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        WindowManager windowManager2 = this$0.window;
                        if (windowManager2 != null) {
                            windowManager2.updateViewLayout($view, $lp);
                            unit = Unit.INSTANCE;
                        }
                        Result.m392constructorimpl(unit);
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        Result.m392constructorimpl(ResultKt.createFailure(th2));
                    }
                    break;
                }
                return true;
            default:
                return false;
        }
    }

    private final void togglePod() {
        final LinearLayout view;
        this.expanded = !this.expanded;
        LinearLayout linearLayout = this.pod;
        if (linearLayout != null) {
            linearLayout.setVisibility(this.expanded ? 0 : 8);
        }
        final WindowManager.LayoutParams lp = this.params;
        if (lp == null || (view = this.root) == null) {
            return;
        }
        view.post(new Runnable() { // from class: com.jarvis.app.OverlayOrbService$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                OverlayOrbService.togglePod$lambda$0(this.f$0, lp, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void togglePod$lambda$0(OverlayOrbService this$0, WindowManager.LayoutParams $lp, LinearLayout $view) {
        Unit unit;
        int screenW = this$0.getResources().getDisplayMetrics().widthPixels;
        int margin = (int) (12 * this$0.getResources().getDisplayMetrics().density);
        if (($lp.x + $view.getWidth() > screenW - margin || !this$0.expanded) && $lp.x > screenW / 3) {
            $lp.x = (screenW - $view.getWidth()) - margin;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            WindowManager windowManager = this$0.window;
            if (windowManager != null) {
                windowManager.updateViewLayout($view, $lp);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    private final void openLive() {
        Intent launch = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launch != null) {
            launch.addFlags(805306368);
        }
        if (launch != null) {
            launch.putExtra("open_live", true);
        }
        if (launch != null) {
            startActivity(launch);
        }
    }

    private final void remove() {
        Object objM392constructorimpl;
        Unit unit;
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()));
        LinearLayout it = this.root;
        if (it != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                OverlayOrbService $this$remove_u24lambda_u240_u240 = this;
                WindowManager windowManager = $this$remove_u24lambda_u240_u240.window;
                if (windowManager != null) {
                    windowManager.removeView(it);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                objM392constructorimpl = Result.m392constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            Result.m391boximpl(objM392constructorimpl);
        }
        this.root = null;
        this.pod = null;
        this.core = null;
    }

    private final Notification notification() {
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel("jarvis_overlay", "Орб Live", 2));
        Notification notificationBuild = new NotificationCompat.Builder(this, "jarvis_overlay").setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("JARVIS").setContentText("Идёт разговор Live").setOngoing(true).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        return notificationBuild;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        remove();
        super.onDestroy();
    }

    /* JADX INFO: compiled from: OverlayOrbService.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/app/OverlayOrbService$Companion;", "", "<init>", "()V", "ACTION_SHOW", "", "ACTION_HIDE", "ACTION_LIVE", "NOTIFICATION_ID", "", "ORB_DP", "POD_DP", "show", "", "context", "Landroid/content/Context;", "hide", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void show(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (VoiceController.INSTANCE.getLiveTalk()) {
                Intent intent = new Intent(context, (Class<?>) OverlayOrbService.class).setAction(OverlayOrbService.ACTION_SHOW);
                Intrinsics.checkNotNullExpressionValue(intent, "setAction(...)");
                context.startForegroundService(intent);
            }
        }

        public final void hide(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startService(new Intent(context, (Class<?>) OverlayOrbService.class).setAction(OverlayOrbService.ACTION_HIDE));
        }
    }
}
