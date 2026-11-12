package com.jarvis.app;

import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.location.LocationRequestCompat;
import com.jarvis.brain.AssistantOrchestrator;
import com.jarvis.brain.aiprovider.ChatStreamResult;
import com.jarvis.core.common.model.AssistantResponse;
import com.jarvis.core.common.model.AssistantResponseStatus;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.nlp.ConversationWindow;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.runtime.ImmediateAckPolicy;
import com.jarvis.core.runtime.InteractionLatency;
import com.jarvis.core.runtime.InteractionLatencyKt;
import com.jarvis.core.runtime.LatencyStatsStore;
import com.jarvis.core.runtime.RequestTrace;
import com.jarvis.core.runtime.RouteDecision;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: VoiceService.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.VoiceService$coreListener$1$onRequest$1", f = "VoiceService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class VoiceService$coreListener$1$onRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $gen;
    final /* synthetic */ UserRequest $request;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VoiceService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VoiceService$coreListener$1$onRequest$1(VoiceService voiceService, UserRequest userRequest, long j, Continuation<? super VoiceService$coreListener$1$onRequest$1> continuation) {
        super(2, continuation);
        this.this$0 = voiceService;
        this.$request = userRequest;
        this.$gen = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VoiceService$coreListener$1$onRequest$1 voiceService$coreListener$1$onRequest$1 = new VoiceService$coreListener$1$onRequest$1(this.this$0, this.$request, this.$gen, continuation);
        voiceService$coreListener$1$onRequest$1.L$0 = obj;
        return voiceService$coreListener$1$onRequest$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceService$coreListener$1$onRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) throws Throwable {
        CoroutineScope $this$launch = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Process.setThreadPriority(-1);
                VoiceController.INSTANCE.setProcessingBusy(true);
                final long t0 = SystemClock.elapsedRealtime();
                final AssistantOrchestrator orch = BrainGraph.INSTANCE.build(this.this$0);
                String maybeAck = ImmediateAckPolicy.acknowledgementFor$default(ImmediateAckPolicy.INSTANCE, RouteDecision.CHAT, this.$request.getText(), null, 4, null);
                if (maybeAck != null) {
                    this.this$0.ackJob = BuildersKt__Builders_commonKt.launch$default($this$launch, null, null, new AnonymousClass1(this.this$0, this.$gen, orch, maybeAck, null), 3, null);
                }
                final AtomicBoolean firstAudioOnce = new AtomicBoolean(false);
                final Ref.IntRef streamGen = new Ref.IntRef();
                final Ref.BooleanRef usedStream = new Ref.BooleanRef();
                UserRequest userRequest = this.$request;
                long j = this.$gen;
                final VoiceService voiceService = this.this$0;
                final long j2 = this.$gen;
                Function0<Boolean> function0 = new Function0() { // from class: com.jarvis.app.VoiceService$coreListener$1$onRequest$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(VoiceService$coreListener$1$onRequest$1.invokeSuspend$lambda$0(voiceService, j2));
                    }
                };
                final VoiceService voiceService2 = this.this$0;
                final long j3 = this.$gen;
                AssistantResponse streamed = orch.processStreamingChat(userRequest, j, function0, new Function2() { // from class: com.jarvis.app.VoiceService$coreListener$1$onRequest$1$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return Boolean.valueOf(VoiceService$coreListener$1$onRequest$1.invokeSuspend$lambda$1(voiceService2, j3, streamGen, usedStream, orch, t0, firstAudioOnce, (String) obj, ((Boolean) obj2).booleanValue()));
                    }
                });
                if (this.this$0.generationGate.isStale(this.$gen)) {
                    Log.i("Jarvis.VoiceService", "stale request " + this.$request.getRequestId() + " — skip");
                    VoiceController.INSTANCE.setProcessingBusy(false);
                    return Unit.INSTANCE;
                }
                if (streamed != null) {
                    Job job = this.this$0.ackJob;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    long brainMs = SystemClock.elapsedRealtime() - t0;
                    final RequestTrace trace = orch.getLastTrace();
                    InteractionLatency lat = trace != null ? trace.getInteractionLatency() : null;
                    if (lat != null) {
                        lat.setFirstUsefulAt(Boxing.boxLong(SystemClock.elapsedRealtime()));
                    }
                    AssistantResponseStatus status = streamed.getStatus();
                    ChatStreamResult lastChatStreamResult = orch.getLastChatStreamResult();
                    Boolean boolBoxBoolean = lastChatStreamResult != null ? Boxing.boxBoolean(lastChatStreamResult.getStreamed()) : null;
                    ChatStreamResult lastChatStreamResult2 = orch.getLastChatStreamResult();
                    Long ttftMs = lastChatStreamResult2 != null ? lastChatStreamResult2.getTtftMs() : null;
                    ChatStreamResult lastChatStreamResult3 = orch.getLastChatStreamResult();
                    Log.d("Jarvis.VoiceService", "stream-chat " + brainMs + "ms status=" + status + " streamUsed=" + boolBoxBoolean + " ttft=" + ttftMs + " sentences=" + (lastChatStreamResult3 != null ? Boxing.boxInt(lastChatStreamResult3.getSentenceCount()) : null) + " reply=\"" + StringsKt.take(SensitivityClassifier.INSTANCE.mask(streamed.getReplyText()), 120) + "\"");
                    if (usedStream.element) {
                        if (streamed.getToolResult() != null) {
                            VoiceController.enqueueStreamSpeak$default(VoiceController.INSTANCE, streamed.getReplyText(), streamGen.element, null, 4, null);
                        }
                        VoiceController voiceController = VoiceController.INSTANCE;
                        final VoiceService voiceService3 = this.this$0;
                        final long j4 = this.$gen;
                        final InteractionLatency interactionLatency = lat;
                        voiceController.endStreamSpeak(new Function0() { // from class: com.jarvis.app.VoiceService$coreListener$1$onRequest$1$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return VoiceService$coreListener$1$onRequest$1.invokeSuspend$lambda$2(voiceService3, j4, interactionLatency, trace);
                            }
                        });
                    } else {
                        VoiceService.speakFull$default(this.this$0, streamed.getReplyText(), this.$gen, t0, brainMs, orch, null, 32, null);
                    }
                    return Unit.INSTANCE;
                }
                try {
                    try {
                        AssistantResponse response = orch.process(this.$request);
                        if (!this.this$0.generationGate.isStale(this.$gen)) {
                            Job job2 = this.this$0.ackJob;
                            if (job2 != null) {
                                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                            }
                            VoiceController.INSTANCE.stopSpeaking();
                            this.this$0.speakFull(response.getReplyText(), this.$gen, t0, SystemClock.elapsedRealtime() - t0, orch, response);
                            return Unit.INSTANCE;
                        }
                        VoiceController.INSTANCE.setProcessingBusy(false);
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        t = th;
                        VoiceController.INSTANCE.setProcessingBusy(false);
                        throw t;
                    }
                } catch (Throwable th2) {
                    t = th2;
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.jarvis.app.VoiceService$coreListener$1$onRequest$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: VoiceService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.VoiceService$coreListener$1$onRequest$1$1", f = "VoiceService.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $gen;
        final /* synthetic */ String $maybeAck;
        final /* synthetic */ AssistantOrchestrator $orch;
        int label;
        final /* synthetic */ VoiceService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(VoiceService voiceService, long j, AssistantOrchestrator assistantOrchestrator, String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = voiceService;
            this.$gen = j;
            this.$orch = assistantOrchestrator;
            this.$maybeAck = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$gen, this.$orch, this.$maybeAck, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            InteractionLatency interactionLatency;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(700L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (!this.this$0.generationGate.isStale(this.$gen)) {
                RequestTrace lastTrace = this.$orch.getLastTrace();
                if (lastTrace != null && (interactionLatency = lastTrace.getInteractionLatency()) != null) {
                    interactionLatency.setFirstAckAt(Boxing.boxLong(SystemClock.elapsedRealtime()));
                }
                VoiceController.speak$default(VoiceController.INSTANCE, this.$maybeAck, null, null, 6, null);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$0(VoiceService this$0, long $gen) {
        return this$0.generationGate.isStale($gen);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$1(VoiceService this$0, long $gen, Ref.IntRef $streamGen, Ref.BooleanRef $usedStream, final AssistantOrchestrator $orch, final long $t0, final AtomicBoolean $firstAudioOnce, String sentence, boolean first) {
        if (this$0.generationGate.isStale($gen)) {
            return false;
        }
        Job job = this$0.ackJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (first) {
            VoiceController.INSTANCE.stopSpeaking();
            $streamGen.element = VoiceController.INSTANCE.beginStreamSpeak();
            $usedStream.element = true;
            RequestTrace lastTrace = $orch.getLastTrace();
            if (lastTrace != null) {
                lastTrace.setFirstSentenceMs(Long.valueOf(SystemClock.elapsedRealtime() - $t0));
            }
            RequestTrace lastTrace2 = $orch.getLastTrace();
            if (lastTrace2 != null) {
                lastTrace2.setFirstTextChunkMs(Long.valueOf(SystemClock.elapsedRealtime() - $t0));
            }
        }
        VoiceController.INSTANCE.enqueueStreamSpeak(sentence, $streamGen.element, new Function1() { // from class: com.jarvis.app.VoiceService$coreListener$1$onRequest$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VoiceService$coreListener$1$onRequest$1.invokeSuspend$lambda$1$0($firstAudioOnce, $t0, $orch, ((Long) obj).longValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1$0(AtomicBoolean $firstAudioOnce, long $t0, AssistantOrchestrator $orch, long ttsMs) {
        InteractionLatency interactionLatency;
        if ($firstAudioOnce.compareAndSet(false, true)) {
            VoiceController.INSTANCE.setProcessingBusy(false);
            long e2e = SystemClock.elapsedRealtime() - $t0;
            RequestTrace lastTrace = $orch.getLastTrace();
            if (lastTrace != null) {
                lastTrace.setFirstAudioMs(Long.valueOf(e2e));
            }
            RequestTrace lastTrace2 = $orch.getLastTrace();
            if (lastTrace2 != null) {
                lastTrace2.setTtsStartupMs(Long.valueOf(ttsMs));
            }
            RequestTrace lastTrace3 = $orch.getLastTrace();
            if (lastTrace3 != null) {
                lastTrace3.setTtsFirstAudioMs(Long.valueOf(e2e));
            }
            RequestTrace lastTrace4 = $orch.getLastTrace();
            if (lastTrace4 != null) {
                lastTrace4.setE2eMs(Long.valueOf(e2e));
            }
            RequestTrace lastTrace5 = $orch.getLastTrace();
            if (lastTrace5 != null && (interactionLatency = lastTrace5.getInteractionLatency()) != null) {
                interactionLatency.setTtsFirstAudioAt(Long.valueOf(SystemClock.elapsedRealtime()));
            }
            Log.d("Jarvis.VoiceService", "stream first-audio e2e=" + e2e + "ms tts=" + ttsMs + " slo1s=" + (e2e <= 1000));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2(VoiceService this$0, long $gen, InteractionLatency $lat, RequestTrace $trace) {
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
        if (JarvisSettings.INSTANCE.conversationMode(this$0)) {
            VoiceController.INSTANCE.listenForFollowUp(ConversationWindow.CONVERSATION_MS);
        }
        return Unit.INSTANCE;
    }
}
