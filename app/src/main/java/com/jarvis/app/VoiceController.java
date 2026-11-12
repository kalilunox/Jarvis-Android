package com.jarvis.app;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.event.AssistantEvent;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.nlp.ConversationWindow;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VoiceEngine.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001_B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0006J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0006J\u0006\u0010(\u001a\u00020\u0018J\"\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00180.J\u000e\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u000202J\u000e\u00103\u001a\u00020\u00182\u0006\u00101\u001a\u000202J\u000e\u00104\u001a\u00020\u00182\u0006\u00101\u001a\u000202J\u000e\u00105\u001a\u00020\u00182\u0006\u00101\u001a\u000202J \u00107\u001a\u00020\u00182\u0006\u00101\u001a\u0002022\u0006\u00108\u001a\u00020\n2\u0006\u00109\u001a\u00020\u0011H\u0002J\u000e\u0010:\u001a\u00020\u00182\u0006\u00101\u001a\u000202J8\u0010;\u001a\u00020\u00182\u0006\u0010<\u001a\u00020\u00112\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010>2\u0016\b\u0002\u0010?\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0018\u0018\u00010.J\u0006\u0010@\u001a\u00020\u0018J\u0006\u0010A\u001a\u00020,J.\u0010B\u001a\u00020\u00182\u0006\u0010<\u001a\u00020\u00112\u0006\u0010C\u001a\u00020,2\u0016\b\u0002\u0010?\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0018\u0018\u00010.J\u0016\u0010D\u001a\u00020\u00182\u000e\b\u0002\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00180>J\u0010\u0010E\u001a\u00020\u00182\b\b\u0002\u0010F\u001a\u00020#J\u000e\u0010G\u001a\u00020\u00182\u0006\u0010H\u001a\u00020\nJ\u0006\u0010I\u001a\u00020\u0018J\u0006\u0010J\u001a\u00020\u0018J\r\u0010K\u001a\u00020\u0018H\u0000¢\u0006\u0002\bLJ\u0015\u0010M\u001a\u00020\u00182\u0006\u0010<\u001a\u00020\u0011H\u0000¢\u0006\u0002\bNJ\u0015\u0010O\u001a\u00020\u00182\u0006\u0010P\u001a\u00020QH\u0000¢\u0006\u0002\bRJ\u0015\u0010S\u001a\u00020\u00182\u0006\u0010T\u001a\u00020\nH\u0000¢\u0006\u0002\bUJ\u0015\u0010V\u001a\u00020\u00182\u0006\u0010W\u001a\u00020\u0016H\u0000¢\u0006\u0002\bXR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u000e\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0010\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0011\u0010)\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b)\u0010\fR\u0010\u00106\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Lcom/jarvis/app/VoiceController;", "", "<init>", "()V", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/jarvis/app/VoiceController$Listener;", "mainHandler", "Landroid/os/Handler;", "value", "", "isRunning", "()Z", "isLiveSession", "hasEngine", "getHasEngine", "isSpeaking", "", "lastEntry", "getLastEntry", "()Ljava/lang/String;", "activeEngine", "Lcom/jarvis/app/VoiceEngine;", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeListener", "liveTalk", "getLiveTalk", "setLiveTalk", "(Z)V", "liveMuted", "getLiveMuted", "setLiveMuted", "liveStartedAt", "", "getLiveStartedAt", "()J", "setLiveStartedAt", "(J)V", "manualWake", "isIdle", "recordVoiceSample", "durationMs", "", "done", "Lkotlin/Function1;", "", "cancelTurn", "context", "Landroid/content/Context;", "start", "startLive", "startFromSystemAssist", "appContextForWidget", "startInternal", "liveMode", VoiceService.EXTRA_ENTRY, "stop", "speak", VoiceService.EXTRA_TEXT, "onComplete", "Lkotlin/Function0;", "onFirstAudio", "stopSpeaking", "beginStreamSpeak", "enqueueStreamSpeak", "streamGeneration", "endStreamSpeak", "listenForFollowUp", "timeoutMs", "setProcessingBusy", "busy", "showOverlay", "hideOverlay", "notifyWake", "notifyWake$app_debug", "notifyPartial", "notifyPartial$app_debug", "notifyRequest", "request", "Lcom/jarvis/core/common/model/UserRequest;", "notifyRequest$app_debug", "setRunning", "running", "setRunning$app_debug", "attachEngine", "engine", "attachEngine$app_debug", "ENTRY_UNKNOWN", "ENTRY_UI", "ENTRY_UI_LIVE", "ENTRY_SYSTEM_ASSIST", "ENTRY_DEBUG", "TAG", "Listener", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceController {
    public static final String ENTRY_DEBUG = "debug";
    public static final String ENTRY_SYSTEM_ASSIST = "system_assist";
    public static final String ENTRY_UI = "ui";
    public static final String ENTRY_UI_LIVE = "ui_live";
    public static final String ENTRY_UNKNOWN = "unknown";
    private static final String TAG = "Jarvis.VoicePipeline";
    private static volatile VoiceEngine activeEngine;
    private static volatile Context appContextForWidget;
    private static volatile boolean isLiveSession;
    private static volatile boolean isRunning;
    private static volatile boolean liveMuted;
    private static volatile long liveStartedAt;
    private static volatile boolean liveTalk;
    public static final VoiceController INSTANCE = new VoiceController();
    private static final CopyOnWriteArrayList<Listener> listeners = new CopyOnWriteArrayList<>();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static volatile String lastEntry = "unknown";

    private VoiceController() {
    }

    public final boolean isRunning() {
        return isRunning;
    }

    public final boolean isLiveSession() {
        return isLiveSession;
    }

    public final boolean getHasEngine() {
        return activeEngine != null;
    }

    public final boolean isSpeaking() {
        VoiceEngine voiceEngine = activeEngine;
        return voiceEngine != null && voiceEngine.isSpeaking();
    }

    public final String getLastEntry() {
        return lastEntry;
    }

    /* JADX INFO: compiled from: VoiceEngine.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/app/VoiceController$Listener;", "", "onWake", "", "onRequest", "request", "Lcom/jarvis/core/common/model/UserRequest;", "onPartial", VoiceService.EXTRA_TEXT, "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Listener {
        void onRequest(UserRequest request);

        void onWake();

        /* JADX INFO: compiled from: VoiceEngine.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void onPartial(Listener $this, String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                Listener.super.onPartial(text);
            }
        }

        default void onPartial(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
        }
    }

    public final void addListener(Listener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public final void removeListener(Listener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listeners.remove(listener);
    }

    public final boolean getLiveTalk() {
        return liveTalk;
    }

    public final void setLiveTalk(boolean z) {
        liveTalk = z;
    }

    public final boolean getLiveMuted() {
        return liveMuted;
    }

    public final void setLiveMuted(boolean z) {
        liveMuted = z;
    }

    public final long getLiveStartedAt() {
        return liveStartedAt;
    }

    public final void setLiveStartedAt(long j) {
        liveStartedAt = j;
    }

    public final void manualWake() {
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.manualWake();
        }
    }

    public final boolean isIdle() {
        VoiceEngine voiceEngine = activeEngine;
        return voiceEngine != null && voiceEngine.isIdle();
    }

    public final boolean recordVoiceSample(int durationMs, Function1<? super float[], Unit> done) {
        Intrinsics.checkNotNullParameter(done, "done");
        VoiceEngine engine = activeEngine;
        if (engine == null) {
            return false;
        }
        engine.recordSample(durationMs, done);
        return true;
    }

    public final void cancelTurn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.cancelTurn();
        }
        BrainGraph.INSTANCE.build(context).cancelActiveChatStream();
    }

    public final void start(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        startInternal(context, false, ENTRY_UI);
    }

    public final void startLive(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        startInternal(context, true, ENTRY_UI_LIVE);
    }

    public final void startFromSystemAssist(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        startInternal(context, true, ENTRY_SYSTEM_ASSIST);
    }

    private final void startInternal(Context context, boolean liveMode, String entry) {
        appContextForWidget = context.getApplicationContext();
        lastEntry = entry;
        JarvisSettings.INSTANCE.setVoiceWanted(context, true);
        if (liveMode) {
            isLiveSession = true;
        }
        String action = liveMode ? VoiceService.ACTION_LIVE_START : VoiceService.ACTION_START;
        Log.i(TAG, "pipeline start entry=" + entry + " live=" + liveMode + " running=" + isRunning);
        Intent $this$startInternal_u24lambda_u240 = new Intent(context, (Class<?>) VoiceService.class);
        $this$startInternal_u24lambda_u240.setAction(action);
        $this$startInternal_u24lambda_u240.putExtra(VoiceService.EXTRA_ENTRY, entry);
        context.startForegroundService($this$startInternal_u24lambda_u240);
    }

    public final void stop(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        JarvisSettings.INSTANCE.setVoiceWanted(context, false);
        Intent $this$stop_u24lambda_u240 = new Intent(context, (Class<?>) VoiceService.class);
        $this$stop_u24lambda_u240.setAction(VoiceService.ACTION_STOP);
        context.startService($this$stop_u24lambda_u240);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void speak$default(VoiceController voiceController, String str, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        voiceController.speak(str, function0, function1);
    }

    public final void speak(String text, Function0<Unit> onComplete, Function1<? super Long, Unit> onFirstAudio) {
        Intrinsics.checkNotNullParameter(text, "text");
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.speak(text, onComplete, onFirstAudio);
        } else {
            if (onFirstAudio != null) {
                onFirstAudio.invoke(0L);
            }
            if (onComplete != null) {
                onComplete.invoke();
            }
        }
        JarvisEvents.INSTANCE.publish(new AssistantEvent.ResponseGenerated(text));
        JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.SPEAKING));
    }

    public final void stopSpeaking() {
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.stopSpeaking();
        }
    }

    public final int beginStreamSpeak() {
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            return voiceEngine.beginStreamSpeak();
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void enqueueStreamSpeak$default(VoiceController voiceController, String str, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        voiceController.enqueueStreamSpeak(str, i, function1);
    }

    public final void enqueueStreamSpeak(String text, int streamGeneration, Function1<? super Long, Unit> onFirstAudio) {
        Intrinsics.checkNotNullParameter(text, "text");
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.enqueueStreamSpeak(text, streamGeneration, onFirstAudio);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void endStreamSpeak$default(VoiceController voiceController, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0() { // from class: com.jarvis.app.VoiceController$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        voiceController.endStreamSpeak(function0);
    }

    public final void endStreamSpeak(Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.endStreamSpeak(onComplete);
        } else {
            onComplete.invoke();
        }
    }

    public static /* synthetic */ void listenForFollowUp$default(VoiceController voiceController, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = ConversationWindow.CONFIRMATION_MS;
        }
        voiceController.listenForFollowUp(j);
    }

    public final void listenForFollowUp(long timeoutMs) {
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.listenForFollowUp(timeoutMs);
        }
    }

    public final void setProcessingBusy(boolean busy) {
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.setProcessingBusy(busy);
        }
    }

    public final void showOverlay() {
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.showOverlay();
        }
    }

    public final void hideOverlay() {
        VoiceEngine voiceEngine = activeEngine;
        if (voiceEngine != null) {
            voiceEngine.hideOverlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyWake$lambda$0() {
        Iterable $this$forEach$iv = listeners;
        for (Object element$iv : $this$forEach$iv) {
            Listener it = (Listener) element$iv;
            it.onWake();
        }
    }

    public final void notifyWake$app_debug() {
        mainHandler.post(new Runnable() { // from class: com.jarvis.app.VoiceController$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                VoiceController.notifyWake$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyPartial$lambda$0(String $text) {
        Iterable $this$forEach$iv = listeners;
        for (Object element$iv : $this$forEach$iv) {
            Listener it = (Listener) element$iv;
            it.onPartial($text);
        }
    }

    public final void notifyPartial$app_debug(final String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        mainHandler.post(new Runnable() { // from class: com.jarvis.app.VoiceController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                VoiceController.notifyPartial$lambda$0(text);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyRequest$lambda$0(UserRequest $request) {
        Iterable $this$forEach$iv = listeners;
        for (Object element$iv : $this$forEach$iv) {
            Listener it = (Listener) element$iv;
            it.onRequest($request);
        }
    }

    public final void notifyRequest$app_debug(final UserRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        mainHandler.post(new Runnable() { // from class: com.jarvis.app.VoiceController$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                VoiceController.notifyRequest$lambda$0(request);
            }
        });
    }

    public final void setRunning$app_debug(boolean running) {
        isRunning = running;
        Context it = appContextForWidget;
        if (it != null) {
            JarvisWidgetProvider.INSTANCE.refresh(it);
        }
        if (!running) {
            activeEngine = null;
            isLiveSession = false;
        }
    }

    public final void attachEngine$app_debug(VoiceEngine engine) {
        Intrinsics.checkNotNullParameter(engine, "engine");
        activeEngine = engine;
    }
}
