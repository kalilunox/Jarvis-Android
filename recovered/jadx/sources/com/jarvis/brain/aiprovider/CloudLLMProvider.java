package com.jarvis.brain.aiprovider;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.core.aioutput.StructuredAiLimits;
import com.jarvis.core.llm.AssembledLlmContext;
import com.jarvis.core.runtime.LatencyClock;
import com.jarvis.core.runtime.TtftKind;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: CloudLLMProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 72\u00020\u0001:\u00017By\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003J\u0015\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&H\u0000¢\u0006\u0002\b'J\u0010\u0010(\u001a\u00020)2\u0006\u0010%\u001a\u00020&H\u0016J \u0010*\u001a\u00020+2\u0006\u0010%\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J(\u00100\u001a\u00020)2\u0006\u00101\u001a\u00020\u00032\u0006\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u00068"}, d2 = {"Lcom/jarvis/brain/aiprovider/CloudLLMProvider;", "Lcom/jarvis/brain/aiprovider/StreamingAIProvider;", "id", "", "displayName", "client", "Lcom/jarvis/brain/aiprovider/OpenAiCompatibleCloudClient;", "modelProvider", "Lkotlin/Function0;", "systemPromptProvider", "maxContextTokens", "", "maxOutputTokens", "streamingEnabled", "", "endpointConfigured", "apiKeyPresent", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/OpenAiCompatibleCloudClient;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;IIZZZ)V", "getId", "()Ljava/lang/String;", "getDisplayName", "kind", "Lcom/jarvis/brain/aiprovider/AIProviderKind;", "getKind", "()Lcom/jarvis/brain/aiprovider/AIProviderKind;", "capabilities", "Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "getCapabilities", "()Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "availability", "Lcom/jarvis/brain/aiprovider/AIProviderAvailability;", "warm", "", "activeModelId", "preferred", "resolveSystem", "request", "Lcom/jarvis/brain/aiprovider/AICompletionRequest;", "resolveSystem$brain_debug", "complete", "Lcom/jarvis/brain/aiprovider/AICompletionResult;", "stream", "Lcom/jarvis/brain/aiprovider/AIStreamHandle;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/brain/aiprovider/AIStreamListener;", "cancelFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mapFailure", "requestId", "model", "latency", "", NotificationCompat.CATEGORY_ERROR, "", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudLLMProvider implements StreamingAIProvider {
    public static final String ID = "cloud.llm";
    private final boolean apiKeyPresent;
    private final AIProviderCapabilities capabilities;
    private final OpenAiCompatibleCloudClient client;
    private final String displayName;
    private final boolean endpointConfigured;
    private final String id;
    private final AIProviderKind kind;
    private final int maxContextTokens;
    private final int maxOutputTokens;
    private final Function0<String> modelProvider;
    private final boolean streamingEnabled;
    private final Function0<String> systemPromptProvider;

    public CloudLLMProvider() {
        this(null, null, null, null, null, 0, 0, false, false, false, 1023, null);
    }

    public CloudLLMProvider(String id, String displayName, OpenAiCompatibleCloudClient client, Function0<String> modelProvider, Function0<String> systemPromptProvider, int maxContextTokens, int maxOutputTokens, boolean streamingEnabled, boolean endpointConfigured, boolean apiKeyPresent) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(modelProvider, "modelProvider");
        Intrinsics.checkNotNullParameter(systemPromptProvider, "systemPromptProvider");
        this.id = id;
        this.displayName = displayName;
        this.client = client;
        this.modelProvider = modelProvider;
        this.systemPromptProvider = systemPromptProvider;
        this.maxContextTokens = maxContextTokens;
        this.maxOutputTokens = maxOutputTokens;
        this.streamingEnabled = streamingEnabled;
        this.endpointConfigured = endpointConfigured;
        this.apiKeyPresent = apiKeyPresent;
        this.kind = AIProviderKind.CLOUD;
        boolean z = this.streamingEnabled && this.client != null;
        int i = this.maxContextTokens;
        int i2 = this.maxOutputTokens;
        QualityRequirement qualityRequirement = QualityRequirement.QUALITY;
        QualityRequirement qualityRequirement2 = QualityRequirement.FAST;
        PrivacyLevel privacyLevel = PrivacyLevel.NORMAL;
        String[] strArr = new String[4];
        strArr[0] = "provider=openai_compatible";
        strArr[1] = "openai_compatible=true";
        strArr[2] = "endpoint=user_configured";
        strArr[3] = "streaming=" + (this.streamingEnabled && this.client != null);
        this.capabilities = new AIProviderCapabilities(z, true, true, i, i2, false, true, CollectionsKt.listOf((Object[]) strArr), NaraRouterDefaults.MODEL_FAST, qualityRequirement, qualityRequirement2, privacyLevel);
    }

    public /* synthetic */ CloudLLMProvider(String str, String str2, OpenAiCompatibleCloudClient openAiCompatibleCloudClient, Function0 function0, Function0 function1, int i, int i2, boolean z, boolean z2, boolean z3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? ID : str, (i3 & 2) != 0 ? "Cloud LLM (OpenAI-compatible)" : str2, (i3 & 4) != 0 ? null : openAiCompatibleCloudClient, (i3 & 8) != 0 ? new Function0() { // from class: com.jarvis.brain.aiprovider.CloudLLMProvider$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NaraRouterDefaults.MODEL_FAST;
            }
        } : function0, (i3 & 16) != 0 ? new Function0() { // from class: com.jarvis.brain.aiprovider.CloudLLMProvider$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CloudLLMProvider._init_$lambda$1();
            }
        } : function1, (i3 & 32) != 0 ? 8192 : i, (i3 & 64) != 0 ? 256 : i2, (i3 & 128) != 0 ? true : z, (i3 & 256) != 0 ? false : z2, (i3 & 512) == 0 ? z3 : false);
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public String getDisplayName() {
        return this.displayName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$1() {
        return "Ты Джарвис — голосовой ассистент. Отвечай кратко по-русски, без markdown и списков, 1–3 предложения.";
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderCapabilities getCapabilities() {
        return this.capabilities;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderAvailability availability() {
        if (this.client != null && this.client.isConfigured()) {
            return AIProviderAvailability.AVAILABLE;
        }
        return AIProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public void warm() {
    }

    public static /* synthetic */ String activeModelId$default(CloudLLMProvider cloudLLMProvider, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return cloudLLMProvider.activeModelId(str);
    }

    public final String activeModelId(String preferred) {
        if (preferred != null) {
            String str = !StringsKt.isBlank(preferred) ? preferred : null;
            if (str != null) {
                return str;
            }
        }
        String strInvoke = this.modelProvider.invoke();
        if (StringsKt.isBlank(strInvoke)) {
            strInvoke = NaraRouterDefaults.MODEL_FAST;
        }
        return strInvoke;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:28:0x007f  */
    public final String resolveSystem$brain_debug(AICompletionRequest request) {
        String str;
        String it;
        Intrinsics.checkNotNullParameter(request, "request");
        AssembledLlmContext assembled = request.getAssembledContext();
        if (assembled != null && (it = assembled.getSystemPrompt()) != null) {
            if (StringsKt.isBlank(it)) {
                it = null;
            }
            if (it != null) {
                return it;
            }
        }
        StringBuilder $this$resolveSystem_u24lambda_u241 = new StringBuilder();
        $this$resolveSystem_u24lambda_u241.append(this.systemPromptProvider.invoke());
        if (!StringsKt.isBlank(request.getPersona())) {
            $this$resolveSystem_u24lambda_u241.append(' ');
            String upperCase = request.getPersona().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            switch (upperCase) {
                case "DEVELOPER":
                    str = "Режим DEVELOPER: технично, точно, термины допустимы, без сюсюканья.";
                    break;
                case "FRIENDLY":
                    str = "Режим FRIENDLY: тепло, на «ты», живые короткие фразы, лёгкий юмор, без канцелярита.";
                    break;
                case "MINIMAL":
                    str = "Режим MINIMAL: максимум одно короткое предложение, только суть, без воды.";
                    break;
                default:
                    str = "Режим CLASSIC: спокойный вежливый дворецкий, чёткие сдержанные ответы.";
                    break;
            }
            $this$resolveSystem_u24lambda_u241.append(str);
        }
        if (!StringsKt.isBlank(request.getMemories())) {
            $this$resolveSystem_u24lambda_u241.append(" Память: ").append(StringsKt.take(request.getMemories(), StructuredAiLimits.MAX_QUESTION_CHARS));
        }
        if (request.getWantStructured() || request.getTaskType() == AiTaskType.SEMANTIC_INTERPRETATION) {
            $this$resolveSystem_u24lambda_u241.append(" Отвечай строго в запрошенном структурированном формате, без лишнего текста.");
        }
        return $this$resolveSystem_u24lambda_u241.toString();
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AICompletionResult complete(AICompletionRequest request) {
        List<String> history;
        Intrinsics.checkNotNullParameter(request, "request");
        if (request.getCancelRequested()) {
            return new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.CANCELLED, null, 0L, new AIProviderError(AIProviderErrorCategory.CANCELLED, "CANCELLED", "Cancellation requested"), false, null, null, null, null, null, 4056, null);
        }
        OpenAiCompatibleCloudClient c = this.client;
        if (c == null || !c.isConfigured()) {
            return new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.FAILURE, null, 0L, new AIProviderError(AIProviderErrorCategory.UNAVAILABLE, "CLOUD_NO_KEY", "Cloud API key not configured"), false, null, null, null, null, null, 4056, null);
        }
        long started = System.nanoTime();
        String model = activeModelId(request.getPreferredModelId());
        String system = resolveSystem$brain_debug(request);
        AssembledLlmContext assembledContext = request.getAssembledContext();
        if (assembledContext == null || (history = assembledContext.getHistoryLines()) == null) {
            history = request.getHistory();
        }
        Object result = OpenAiCompatibleCloudClient.m336chatCompletionhUnOzRk$default(c, model, system, request.getUserText(), history, this.maxOutputTokens, null, 32, null);
        long latency = (System.nanoTime() - started) / ((long) DurationKt.NANOS_IN_MILLIS);
        Throwable err = Result.m395exceptionOrNullimpl(result);
        if (err == null) {
            String text = (String) result;
            return new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.SUCCESS, StringsKt.take(text, 800), latency, null, false, model, null, TtftKind.NOT_AVAILABLE, null, null, 3104, null);
        }
        return mapFailure(request.getRequestId(), model, latency, err);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jarvis.brain.aiprovider.StreamingAIProvider
    public AIStreamHandle stream(final AICompletionRequest request, final AIStreamListener listener, final AtomicBoolean cancelFlag) {
        List<String> history;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(cancelFlag, "cancelFlag");
        if (request.getCancelRequested()) {
            cancelFlag.set(true);
        }
        AIStreamHandle aIStreamHandle = new AIStreamHandle(request, cancelFlag) { // from class: com.jarvis.brain.aiprovider.CloudLLMProvider$stream$handle$1
            final /* synthetic */ AtomicBoolean $cancelled;
            private final String requestId;

            {
                this.$cancelled = cancelFlag;
                this.requestId = request.getRequestId();
            }

            @Override // com.jarvis.brain.aiprovider.AIStreamHandle
            public String getRequestId() {
                return this.requestId;
            }

            @Override // com.jarvis.brain.aiprovider.AIStreamHandle
            public void cancel() {
                this.$cancelled.set(true);
            }

            @Override // com.jarvis.brain.aiprovider.AIStreamHandle
            public boolean isCancelled() {
                return this.$cancelled.get();
            }
        };
        if (!getCapabilities().getSupportsStreaming() || this.client == null || !this.client.isConfigured()) {
            listener.onEvent(new AIStreamEvent(AIStreamEventType.ERROR, null, request.getRequestId(), getId(), null, "STREAM_UNSUPPORTED", "Streaming not available", LatencyClock.INSTANCE.elapsedMs(), 18, null));
            return aIStreamHandle;
        }
        if (cancelFlag.get()) {
            listener.onEvent(new AIStreamEvent(AIStreamEventType.CANCELLED, null, request.getRequestId(), getId(), null, null, null, LatencyClock.INSTANCE.elapsedMs(), 114, null));
            return aIStreamHandle;
        }
        final String model = activeModelId(request.getPreferredModelId());
        String system = resolveSystem$brain_debug(request);
        AssembledLlmContext assembledContext = request.getAssembledContext();
        if (assembledContext == null || (history = assembledContext.getHistoryLines()) == null) {
            history = request.getHistory();
        }
        List<String> list = history;
        long started = System.nanoTime();
        final Ref.ObjectRef firstTokenAt = new Ref.ObjectRef();
        final Ref.IntRef chunkCount = new Ref.IntRef();
        listener.onEvent(new AIStreamEvent(AIStreamEventType.START, null, request.getRequestId(), getId(), model, null, null, LatencyClock.INSTANCE.elapsedMs(), 98, null));
        Object result = this.client.m339chatCompletionStreameH_QyT8(model, system, request.getUserText(), list, (32 & 16) != 0 ? 256 : this.maxOutputTokens, (32 & 32) != 0 ? MapsKt.emptyMap() : null, (32 & 64) != 0 ? new Function0() { // from class: com.jarvis.brain.aiprovider.OpenAiCompatibleCloudClient$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(OpenAiCompatibleCloudClient.chatCompletionStream_eH_QyT8$lambda$0());
            }
        } : new Function0() { // from class: com.jarvis.brain.aiprovider.CloudLLMProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CloudLLMProvider.stream$lambda$0(cancelFlag, request));
            }
        }, new Function1() { // from class: com.jarvis.brain.aiprovider.CloudLLMProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CloudLLMProvider.stream$lambda$1(cancelFlag, firstTokenAt, chunkCount, listener, request, this, model, (String) obj);
            }
        });
        long jNanoTime = System.nanoTime() - started;
        long j = DurationKt.NANOS_IN_MILLIS;
        long latency = jNanoTime / j;
        if (cancelFlag.get()) {
            listener.onEvent(new AIStreamEvent(AIStreamEventType.CANCELLED, null, request.getRequestId(), getId(), model, null, null, LatencyClock.INSTANCE.elapsedMs(), 98, null));
            return aIStreamHandle;
        }
        Throwable err = Result.m395exceptionOrNullimpl(result);
        Long lValueOf = null;
        if (err == null) {
            String text = (String) result;
            Long l = (Long) firstTokenAt.element;
            if (l != null) {
                long it = l.longValue();
                lValueOf = Long.valueOf((it - started) / j);
            }
            Long ttft = lValueOf;
            listener.onEvent(new AIStreamEvent(AIStreamEventType.COMPLETE, StringsKt.take(text, 800), request.getRequestId(), getId(), model, null, null, LatencyClock.INSTANCE.elapsedMs(), 96, null));
            listener.onEvent(new AIStreamEvent(AIStreamEventType.METADATA, "ttftMs=" + (ttft != null ? ttft.longValue() : -1L) + ";chunks=" + chunkCount.element + ";latencyMs=" + latency, request.getRequestId(), getId(), model, null, null, LatencyClock.INSTANCE.elapsedMs(), 96, null));
        } else {
            String msg = err.getMessage();
            if (msg == null) {
                msg = "";
            }
            if (StringsKt.contains$default((CharSequence) msg, (CharSequence) "CANCELLED", false, 2, (Object) null)) {
                listener.onEvent(new AIStreamEvent(AIStreamEventType.CANCELLED, null, request.getRequestId(), getId(), model, null, null, LatencyClock.INSTANCE.elapsedMs(), 98, null));
            } else {
                listener.onEvent(new AIStreamEvent(AIStreamEventType.ERROR, null, request.getRequestId(), getId(), model, "CLOUD_STREAM_ERROR", StringsKt.take(msg, 200), LatencyClock.INSTANCE.elapsedMs(), 2, null));
            }
        }
        return aIStreamHandle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean stream$lambda$0(AtomicBoolean $cancelled, AICompletionRequest $request) {
        return $cancelled.get() || $request.getCancelRequested();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Long] */
    public static final Unit stream$lambda$1(AtomicBoolean $cancelled, Ref.ObjectRef $firstTokenAt, Ref.IntRef $chunkCount, AIStreamListener $listener, AICompletionRequest $request, CloudLLMProvider this$0, String $model, String delta) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        if ($cancelled.get()) {
            return Unit.INSTANCE;
        }
        if ($firstTokenAt.element == 0) {
            $firstTokenAt.element = Long.valueOf(System.nanoTime());
        }
        $chunkCount.element++;
        $listener.onEvent(new AIStreamEvent(AIStreamEventType.TEXT, delta, $request.getRequestId(), this$0.getId(), $model, null, null, LatencyClock.INSTANCE.elapsedMs(), 96, null));
        return Unit.INSTANCE;
    }

    private final AICompletionResult mapFailure(String requestId, String model, long latency, Throwable err) {
        String msg;
        AIProviderErrorCategory category;
        String message = err.getMessage();
        if (message == null || (msg = StringsKt.take(message, 200)) == null) {
            msg = err.getClass().getSimpleName();
        }
        Intrinsics.checkNotNull(msg);
        if (StringsKt.contains$default((CharSequence) msg, (CharSequence) "HTTP 401", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) msg, (CharSequence) "HTTP 403", false, 2, (Object) null)) {
            category = AIProviderErrorCategory.UNAVAILABLE;
        } else if (StringsKt.contains$default((CharSequence) msg, (CharSequence) "HTTP 429", false, 2, (Object) null)) {
            category = AIProviderErrorCategory.TIMEOUT;
        } else {
            category = (StringsKt.contains$default((CharSequence) msg, (CharSequence) "HTTP", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) msg, (CharSequence) "Unable to resolve", false, 2, (Object) null) || StringsKt.contains((CharSequence) msg, (CharSequence) "failed to connect", true)) ? AIProviderErrorCategory.NETWORK : AIProviderErrorCategory.GENERATION_FAILED;
        }
        return new AICompletionResult(requestId, getId(), AICompletionStatus.FAILURE, null, latency, new AIProviderError(category, "CLOUD_ERROR", msg), false, model, null, null, null, null, 3912, null);
    }
}
