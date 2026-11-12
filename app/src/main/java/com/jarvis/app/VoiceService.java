package com.jarvis.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.proactive.ProactiveVoice;
import com.jarvis.brain.AssistantOrchestrator;
import com.jarvis.core.common.model.AssistantResponse;
import com.jarvis.core.common.model.AssistantResponseStatus;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.nlp.ConversationWindow;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.runtime.InteractionGenerationGate;
import com.jarvis.core.runtime.InteractionLatency;
import com.jarvis.core.runtime.InteractionLatencyKt;
import com.jarvis.core.runtime.LatencyStatsStore;
import com.jarvis.core.runtime.RequestTrace;
import com.jarvis.core.runtime.RouteDecision;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: VoiceService.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002\u001e!\u0018\u0000 H2\u00020\u0001:\u0001HB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0002J\b\u0010/\u001a\u00020$H\u0016J\"\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u0002012\u0006\u00105\u001a\u000201H\u0016J\u001a\u00106\u001a\u00020$2\u0006\u00107\u001a\u00020\n2\b\b\u0002\u00108\u001a\u00020&H\u0002J\u0010\u00109\u001a\u00020\n2\u0006\u00102\u001a\u000203H\u0002J\b\u0010:\u001a\u00020\u0018H\u0002J\u0010\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020&H\u0002J\b\u0010=\u001a\u00020$H\u0002J\u0012\u0010>\u001a\u00020?2\b\b\u0002\u00108\u001a\u00020&H\u0002J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020&H\u0002J\b\u0010C\u001a\u00020$H\u0002J\u0014\u0010D\u001a\u0004\u0018\u00010E2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u0010G\u001a\u00020$H\u0016R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0010\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010F\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/jarvis/app/VoiceService;", "Landroid/app/Service;", "<init>", "()V", "brainExecutor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "coreScope", "Lkotlinx/coroutines/CoroutineScope;", "coreRegistered", "", "engine", "Lcom/jarvis/app/VoiceEngine;", "generationGate", "Lcom/jarvis/core/runtime/InteractionGenerationGate;", "ackJob", "Lkotlinx/coroutines/Job;", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "()Landroid/media/AudioManager;", "sayLock", "", "sayFocusRequest", "Landroid/media/AudioFocusRequest;", "sayFocusHeld", "sayInFlight", "announcements", "Lcom/jarvis/app/AnnouncementWatcher;", "batteryLowReceiver", "com/jarvis/app/VoiceService$batteryLowReceiver$1", "Lcom/jarvis/app/VoiceService$batteryLowReceiver$1;", "coreListener", "com/jarvis/app/VoiceService$coreListener$1", "Lcom/jarvis/app/VoiceService$coreListener$1;", "speakFull", "", VoiceService.EXTRA_TEXT, "", "gen", "", "t0", "brainMs", "orch", "Lcom/jarvis/brain/AssistantOrchestrator;", "response", "Lcom/jarvis/core/common/model/AssistantResponse;", "onCreate", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "startVoice", "liveMode", VoiceService.EXTRA_ENTRY, "handleProactiveSay", "buildSayFocusRequest", "finishProactiveSay", "reason", "stopVoice", "buildNotification", "Landroid/app/Notification;", "serviceAction", "Landroid/app/PendingIntent;", "action", "openLiveScreen", "onBind", "Landroid/os/IBinder;", "widgetJob", "onDestroy", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceService extends Service {
    private static final long ACK_DELAY_MS = 700;
    public static final String ACTION_LIVE_START = "com.jarvis.assistant.VOICE_LIVE_START";
    public static final String ACTION_SAY = "com.jarvis.assistant.VOICE_SAY";
    public static final String ACTION_START = "com.jarvis.assistant.VOICE_START";
    public static final String ACTION_STOP = "com.jarvis.assistant.VOICE_STOP";
    public static final String ACTION_TOGGLE_WAKE = "com.jarvis.assistant.VOICE_TOGGLE_WAKE";
    private static final String CHANNEL_ID = "jarvis_voice";
    public static final String EXTRA_ENTRY = "entry";
    public static final String EXTRA_TEXT = "text";
    private static final int NOTIFICATION_ID = 1;
    private static final String SAY_TAG = "Jarvis.ProactiveDelivery";
    private static final String TAG = "Jarvis.VoiceService";
    private Job ackJob;
    private AnnouncementWatcher announcements;
    private final VoiceService$batteryLowReceiver$1 batteryLowReceiver;
    private final ExecutorService brainExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda6
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return VoiceService.brainExecutor$lambda$0(runnable);
        }
    });
    private final VoiceService$coreListener$1 coreListener;
    private boolean coreRegistered;
    private final CoroutineScope coreScope;
    private VoiceEngine engine;
    private final InteractionGenerationGate generationGate;
    private boolean sayFocusHeld;
    private AudioFocusRequest sayFocusRequest;
    private boolean sayInFlight;
    private final Object sayLock;
    private Job widgetJob;

    /* JADX WARN: Type inference failed for: r0v8, types: [com.jarvis.app.VoiceService$batteryLowReceiver$1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.jarvis.app.VoiceService$coreListener$1] */
    public VoiceService() {
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        ExecutorService brainExecutor = this.brainExecutor;
        Intrinsics.checkNotNullExpressionValue(brainExecutor, "brainExecutor");
        this.coreScope = CoroutineScopeKt.CoroutineScope(completableJobSupervisorJob$default.plus(ExecutorsKt.from(brainExecutor)));
        this.generationGate = new InteractionGenerationGate();
        this.sayLock = new Object();
        this.batteryLowReceiver = new BroadcastReceiver() { // from class: com.jarvis.app.VoiceService$batteryLowReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                BatteryManager batteryManager = (BatteryManager) this.this$0.getSystemService(BatteryManager.class);
                Integer percent = batteryManager != null ? Integer.valueOf(batteryManager.getIntProperty(4)) : null;
                ProactiveVoice.INSTANCE.speak(context, "Сэр, заряд на исходе: " + percent + "%.");
            }
        };
        this.coreListener = new VoiceController.Listener() { // from class: com.jarvis.app.VoiceService$coreListener$1
            @Override // com.jarvis.app.VoiceController.Listener
            public /* bridge */ void onPartial(String text) {
                super.onPartial(text);
            }

            @Override // com.jarvis.app.VoiceController.Listener
            public void onWake() {
                this.this$0.generationGate.cancelCurrent();
                BrainGraph.INSTANCE.build(this.this$0).cancelActiveChatStream();
                VoiceController.INSTANCE.stopSpeaking();
                Job job = this.this$0.ackJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
            }

            @Override // com.jarvis.app.VoiceController.Listener
            public void onRequest(UserRequest request) {
                Intrinsics.checkNotNullParameter(request, "request");
                if (StringsKt.isBlank(request.getText()) || AppAgentRunner.INSTANCE.consume(request.getText())) {
                    return;
                }
                long gen = this.this$0.generationGate.begin();
                BrainGraph.INSTANCE.build(this.this$0).cancelActiveChatStream();
                VoiceController.INSTANCE.stopSpeaking();
                Job job = this.this$0.ackJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                BuildersKt__Builders_commonKt.launch$default(this.this$0.coreScope, null, null, new VoiceService$coreListener$1$onRequest$1(this.this$0, request, gen, null), 3, null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread brainExecutor$lambda$0(Runnable r) {
        Thread $this$brainExecutor_u24lambda_u240_u240 = new Thread(r, "jarvis-brain");
        $this$brainExecutor_u24lambda_u240_u240.setPriority(7);
        return $this$brainExecutor_u24lambda_u240_u240;
    }

    private final AudioManager getAudioManager() {
        return (AudioManager) getSystemService(AudioManager.class);
    }

    static /* synthetic */ void speakFull$default(VoiceService voiceService, String str, long j, long j2, long j3, AssistantOrchestrator assistantOrchestrator, AssistantResponse assistantResponse, int i, Object obj) {
        AssistantResponse assistantResponse2;
        if ((i & 32) == 0) {
            assistantResponse2 = assistantResponse;
        } else {
            assistantResponse2 = null;
        }
        voiceService.speakFull(str, j, j2, j3, assistantOrchestrator, assistantResponse2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:34:0x00ac  */
    public final void speakFull(final String text, final long gen, final long t0, final long brainMs, AssistantOrchestrator orch, final AssistantResponse response) {
        Object route;
        boolean z;
        final RequestTrace trace = orch.getLastTrace();
        final InteractionLatency lat = trace != null ? trace.getInteractionLatency() : null;
        if (lat != null) {
            lat.setFirstUsefulAt(Long.valueOf(SystemClock.elapsedRealtime()));
        }
        AssistantResponseStatus status = response != null ? response.getStatus() : null;
        if (response == null || (route = response.getRoute()) == null) {
            route = trace != null ? trace.getRoute() : null;
        }
        Log.d(TAG, "brain " + brainMs + "ms status=" + status + " route=" + route + " orch=" + (response != null ? response.getDurationMs() : brainMs) + "ms reply=\"" + StringsKt.take(SensitivityClassifier.INSTANCE.mask(text), 120) + "\"");
        if ((response != null ? response.getStatus() : null) == AssistantResponseStatus.NEEDS_CONFIRMATION) {
            z = true;
        } else {
            if ((response != null ? response.getStatus() : null) == AssistantResponseStatus.NEEDS_CLARIFICATION) {
                z = true;
            } else {
                z = false;
            }
        }
        final boolean needsFollowUp = z;
        if (lat != null) {
            lat.setTtsStartAt(Long.valueOf(SystemClock.elapsedRealtime()));
        }
        VoiceController.INSTANCE.speak(text, new Function0() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VoiceService.speakFull$lambda$0(this.f$0, gen, lat, trace, response, needsFollowUp, text);
            }
        }, new Function1() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VoiceService.speakFull$lambda$1(this.f$0, gen, lat, t0, brainMs, trace, ((Long) obj).longValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit speakFull$lambda$0(VoiceService this$0, long $gen, InteractionLatency $lat, RequestTrace $trace, AssistantResponse $response, boolean $needsFollowUp, String $text) {
        String strName;
        RouteDecision route;
        if (this$0.generationGate.isStale($gen)) {
            return Unit.INSTANCE;
        }
        if ($lat != null) {
            $lat.setTtsCompleteAt(Long.valueOf(SystemClock.elapsedRealtime()));
        }
        if ($lat != null) {
            $lat.setInteractionCompleteAt(Long.valueOf(SystemClock.elapsedRealtime()));
        }
        if ($lat != null && $trace != null) {
            InteractionLatencyKt.applyLatency($trace, $lat);
            Log.i("Jarvis.Latency", $lat.summaryLine());
            LatencyStatsStore.INSTANCE.recordFrom($trace);
        }
        VoiceController.INSTANCE.setProcessingBusy(false);
        if ($response == null || (strName = $response.getRoute()) == null) {
            strName = ($trace == null || (route = $trace.getRoute()) == null) ? null : route.name();
        }
        boolean isChat = Intrinsics.areEqual(strName, "CHAT");
        if ($needsFollowUp) {
            VoiceController.listenForFollowUp$default(VoiceController.INSTANCE, 0L, 1, null);
        } else if (ConversationWindow.INSTANCE.keepListening($text, isChat, JarvisSettings.INSTANCE.conversationMode(this$0))) {
            VoiceController.INSTANCE.listenForFollowUp(ConversationWindow.CONVERSATION_MS);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit speakFull$lambda$1(VoiceService this$0, long $gen, InteractionLatency $lat, long $t0, long $brainMs, RequestTrace $trace, long ttsFirstMs) {
        if (this$0.generationGate.isStale($gen)) {
            return Unit.INSTANCE;
        }
        VoiceController.INSTANCE.setProcessingBusy(false);
        long firstAudioAt = SystemClock.elapsedRealtime();
        if ($lat != null) {
            $lat.setTtsFirstAudioAt(Long.valueOf(firstAudioAt));
        }
        long firstAudioE2e = firstAudioAt - $t0;
        Log.d(TAG, "e2e first-audio " + firstAudioE2e + "ms (brain=" + $brainMs + "ms tts=" + ttsFirstMs + ") slo1s=" + (firstAudioE2e <= 1000));
        if ($trace != null) {
            $trace.setFirstAudioMs(Long.valueOf(firstAudioE2e));
            $trace.setTtsStartupMs(Long.valueOf(ttsFirstMs));
            $trace.setTtsFirstAudioMs(Long.valueOf(firstAudioE2e));
            $trace.setE2eMs(Long.valueOf(firstAudioE2e));
        }
        return Unit.INSTANCE;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        registerReceiver(this.batteryLowReceiver, new IntentFilter("android.intent.action.BATTERY_LOW"));
        AnnouncementWatcher it = new AnnouncementWatcher(this);
        it.start();
        this.announcements = it;
        VoiceController.INSTANCE.addListener(this.coreListener);
        this.coreRegistered = true;
        this.widgetJob = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new AnonymousClass2(null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.VoiceService$onCreate$2, reason: invalid class name */
    /* JADX INFO: compiled from: VoiceService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.VoiceService$onCreate$2", f = "VoiceService.kt", i = {}, l = {291}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return VoiceService.this.new AnonymousClass2(continuation);
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
                    final VoiceService voiceService = VoiceService.this;
                    this.label = 1;
                    if (assistantState.collect(new FlowCollector() { // from class: com.jarvis.app.VoiceService.onCreate.2.1
                        public final Object emit(AssistantState it, Continuation<? super Unit> continuation) {
                            JarvisWidgetProvider.INSTANCE.refresh(voiceService);
                            return Unit.INSTANCE;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((AssistantState) value, (Continuation<? super Unit>) $completion);
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:37:0x00a1  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) throws Throwable {
        String entry;
        if (intent == null || (entry = intent.getStringExtra(EXTRA_ENTRY)) == null) {
            entry = VoiceController.INSTANCE.getLastEntry();
        }
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case -628300234:
                    if (!action.equals(ACTION_TOGGLE_WAKE)) {
                        Log.i(TAG, "onStartCommand START entry=" + entry);
                        startVoice(false, entry);
                    } else {
                        JarvisSettings.INSTANCE.setWakeEnabled(this, !JarvisSettings.INSTANCE.wakeEnabled(this));
                        if (this.engine == null) {
                            stopSelf();
                        } else {
                            startForeground(1, buildNotification(entry), 128);
                        }
                    }
                    break;
                case -486632110:
                    if (!action.equals(ACTION_SAY)) {
                        Log.i(TAG, "onStartCommand START entry=" + entry);
                        startVoice(false, entry);
                    } else if (!handleProactiveSay(intent)) {
                        stopSelf(startId);
                    }
                    break;
                case 1575037896:
                    if (!action.equals(ACTION_LIVE_START)) {
                        Log.i(TAG, "onStartCommand START entry=" + entry);
                        startVoice(false, entry);
                    } else {
                        Log.i(TAG, "onStartCommand LIVE entry=" + entry);
                        if (this.engine == null) {
                            startVoice(true, entry);
                        } else {
                            openLiveScreen();
                        }
                    }
                    break;
                case 2094291803:
                    if (!action.equals(ACTION_STOP)) {
                        Log.i(TAG, "onStartCommand START entry=" + entry);
                        startVoice(false, entry);
                    } else {
                        stopVoice();
                    }
                    break;
                default:
                    Log.i(TAG, "onStartCommand START entry=" + entry);
                    startVoice(false, entry);
                    break;
            }
        } else {
            Log.i(TAG, "onStartCommand START entry=" + entry);
            startVoice(false, entry);
        }
        return 1;
    }

    static /* synthetic */ void startVoice$default(VoiceService voiceService, boolean z, String str, int i, Object obj) throws Throwable {
        if ((i & 2) != 0) {
            str = "unknown";
        }
        voiceService.startVoice(z, str);
    }

    private final void startVoice(boolean liveMode, String entry) throws Throwable {
        if (this.engine != null) {
            Log.d(TAG, "startVoice ignored: already running entry=" + entry);
            return;
        }
        Log.i(TAG, "VoicePipeline owning mic entry=" + entry + " live=" + liveMode);
        startForeground(1, buildNotification(entry), 128);
        VoiceEngine voiceEngine = new VoiceEngine(this, new Function0() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VoiceService.startVoice$lambda$0();
            }
        }, new Function1() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VoiceService.startVoice$lambda$1((UserRequest) obj);
            }
        }, new Function1() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VoiceService.startVoice$lambda$2((String) obj);
            }
        }, liveMode, JarvisSettings.INSTANCE.wakeEnabled(this));
        if (!voiceEngine.start()) {
            voiceEngine.stop();
            stopVoice();
            return;
        }
        this.engine = voiceEngine;
        VoiceController.INSTANCE.attachEngine$app_debug(voiceEngine);
        VoiceController.INSTANCE.setRunning$app_debug(true);
        if (!JarvisSettings.INSTANCE.lowPowerMode(this)) {
            BrainGraph.INSTANCE.warmLlmBrain(this);
        } else {
            BrainGraph.INSTANCE.warmChatLlm(this);
            Log.d(TAG, "low-power: chat warm only");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startVoice$lambda$0() {
        VoiceController.INSTANCE.notifyWake$app_debug();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startVoice$lambda$1(UserRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        VoiceController.INSTANCE.notifyRequest$app_debug(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startVoice$lambda$2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        VoiceController.INSTANCE.notifyPartial$app_debug(it);
        return Unit.INSTANCE;
    }

    private final boolean handleProactiveSay(Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_TEXT);
        String text = stringExtra != null ? StringsKt.trim((CharSequence) stringExtra).toString() : null;
        if (text == null) {
            text = "";
        }
        if (text.length() == 0) {
            Log.i(SAY_TAG, "say skip: empty text");
            return true;
        }
        if (this.engine == null || !VoiceController.INSTANCE.getHasEngine()) {
            Log.i(SAY_TAG, "say skip: service not running");
            return false;
        }
        AssistantState state = JarvisEvents.INSTANCE.getAssistantState().getValue();
        boolean session = VoiceController.INSTANCE.isLiveSession() || state == AssistantState.LISTENING || state == AssistantState.THINKING || state == AssistantState.SPEAKING;
        if (session) {
            Log.i(SAY_TAG, "say skip: session active state=" + state);
            return true;
        }
        if (VoiceController.INSTANCE.isSpeaking()) {
            Log.i(SAY_TAG, "say skip: already speaking");
            return true;
        }
        synchronized (this.sayLock) {
            if (this.sayInFlight) {
                Log.i(SAY_TAG, "say skip: previous proactive phrase in flight");
            } else {
                AudioManager am = getAudioManager();
                if (am == null) {
                    VoiceService voiceService = this;
                    Log.i(SAY_TAG, "say skip: no AudioManager");
                } else {
                    AudioFocusRequest request = buildSayFocusRequest();
                    int result = am.requestAudioFocus(request);
                    Log.i(SAY_TAG, "say focus request result=" + result + " gain=TRANSIENT_MAY_DUCK usage=ASSISTANT content=SPEECH");
                    if (result == 1) {
                        this.sayFocusRequest = request;
                        this.sayFocusHeld = true;
                        this.sayInFlight = true;
                        Unit unit = Unit.INSTANCE;
                        Log.i(SAY_TAG, "say accepted chars=" + text.length() + " state=" + state);
                        VoiceController.INSTANCE.speak(text, new Function0() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return VoiceService.handleProactiveSay$lambda$1(this.f$0);
                            }
                        }, new Function1() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return VoiceService.handleProactiveSay$lambda$2(((Long) obj).longValue());
                            }
                        });
                        return true;
                    }
                    Log.i(SAY_TAG, "say skip: audio focus denied");
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleProactiveSay$lambda$1(VoiceService this$0) {
        this$0.finishProactiveSay("complete");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleProactiveSay$lambda$2(long ms) {
        Log.i(SAY_TAG, "say first-audio " + ms + "ms");
        return Unit.INSTANCE;
    }

    private final AudioFocusRequest buildSayFocusRequest() {
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(3).setAudioAttributes(new AudioAttributes.Builder().setUsage(16).setContentType(1).build()).setWillPauseWhenDucked(false).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() { // from class: com.jarvis.app.VoiceService$$ExternalSyntheticLambda2
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                VoiceService.buildSayFocusRequest$lambda$0(this.f$0, i);
            }
        }).build();
        Intrinsics.checkNotNullExpressionValue(audioFocusRequestBuild, "build(...)");
        return audioFocusRequestBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildSayFocusRequest$lambda$0(VoiceService this$0, int change) {
        Log.i(SAY_TAG, "say focus change=" + change);
        switch (change) {
            case -2:
            case -1:
                VoiceController.INSTANCE.stopSpeaking();
                this$0.finishProactiveSay("focus_lost");
                break;
        }
    }

    private final void finishProactiveSay(String reason) {
        synchronized (this.sayLock) {
            if (this.sayInFlight) {
                this.sayInFlight = false;
                AudioFocusRequest request = this.sayFocusRequest;
                boolean held = this.sayFocusHeld;
                this.sayFocusRequest = null;
                this.sayFocusHeld = false;
                if (!held || request == null) {
                    Log.i(SAY_TAG, "say finished reason=" + reason + " (no focus)");
                } else {
                    AudioManager audioManager = getAudioManager();
                    if (audioManager != null) {
                        audioManager.abandonAudioFocusRequest(request);
                    }
                    Log.i(SAY_TAG, "say focus abandon reason=" + reason);
                }
            }
        }
    }

    private final void stopVoice() {
        this.generationGate.cancelCurrent();
        VoiceController.INSTANCE.setRunning$app_debug(false);
        VoiceEngine voiceEngine = this.engine;
        if (voiceEngine != null) {
            voiceEngine.stop();
        }
        this.engine = null;
        stopForeground(2);
        stopSelf();
    }

    static /* synthetic */ Notification buildNotification$default(VoiceService voiceService, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "unknown";
        }
        return voiceService.buildNotification(str);
    }

    private final Notification buildNotification(String entry) {
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager manager = (NotificationManager) systemService;
        manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, getString(R.string.voice_channel_name), 2));
        String suffix = Intrinsics.areEqual(entry, VoiceController.ENTRY_SYSTEM_ASSIST) ? " · системный помощник" : "";
        boolean wakeOn = JarvisSettings.INSTANCE.wakeEnabled(this);
        boolean silent = !JarvisSettings.INSTANCE.prefs(this).getBoolean("companion_bg_notify", true);
        Notification notificationBuild = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(getString(R.string.voice_notification_title)).setContentText(getString(R.string.voice_notification_text) + suffix).setOngoing(true).setSilent(silent).setCategory(NotificationCompat.CATEGORY_SERVICE).addAction(R.drawable.ic_fui_mic, wakeOn ? "MUTE" : "WAKE", serviceAction(ACTION_TOGGLE_WAKE)).addAction(R.drawable.ic_fui_live, "LIVE MODE", serviceAction(ACTION_LIVE_START)).addAction(R.drawable.ic_fui_stop, "STOP SERVICE", serviceAction(ACTION_STOP)).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        return notificationBuild;
    }

    private final PendingIntent serviceAction(String action) {
        Intent intent = new Intent(this, (Class<?>) VoiceService.class).setAction(action);
        Intrinsics.checkNotNullExpressionValue(intent, "setAction(...)");
        PendingIntent service = PendingIntent.getService(this, action.hashCode(), intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(service, "getService(...)");
        return service;
    }

    private final void openLiveScreen() {
        Intent launch = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launch == null) {
            return;
        }
        launch.addFlags(805306368);
        launch.putExtra("open_live", true);
        startActivity(launch);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Job job = this.widgetJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceService $this$onDestroy_u24lambda_u240 = this;
            $this$onDestroy_u24lambda_u240.unregisterReceiver($this$onDestroy_u24lambda_u240.batteryLowReceiver);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        AnnouncementWatcher announcementWatcher = this.announcements;
        if (announcementWatcher != null) {
            announcementWatcher.stop();
        }
        finishProactiveSay("destroy");
        if (this.coreRegistered) {
            VoiceController.INSTANCE.removeListener(this.coreListener);
        }
        VoiceEngine voiceEngine = this.engine;
        if (voiceEngine != null) {
            voiceEngine.stop();
        }
        this.engine = null;
        CoroutineScopeKt.cancel$default(this.coreScope, null, 1, null);
        this.brainExecutor.shutdownNow();
        super.onDestroy();
    }
}
