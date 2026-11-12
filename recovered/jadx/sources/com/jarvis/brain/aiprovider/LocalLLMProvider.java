package com.jarvis.brain.aiprovider;

import com.jarvis.brain.ChatLlmEngine;
import com.jarvis.brain.LlmStructuredDecision;
import com.jarvis.core.runtime.TtftKind;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: LocalLLMProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0017\u001a\u00020\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Lcom/jarvis/brain/aiprovider/LocalLLMProvider;", "Lcom/jarvis/brain/aiprovider/AIProvider;", "engine", "Lcom/jarvis/brain/ChatLlmEngine;", "id", "", "displayName", "maxContextTokens", "", "maxOutputTokens", "<init>", "(Lcom/jarvis/brain/ChatLlmEngine;Ljava/lang/String;Ljava/lang/String;II)V", "getId", "()Ljava/lang/String;", "getDisplayName", "kind", "Lcom/jarvis/brain/aiprovider/AIProviderKind;", "getKind", "()Lcom/jarvis/brain/aiprovider/AIProviderKind;", "capabilities", "Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "getCapabilities", "()Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "modelIdHint", "availability", "Lcom/jarvis/brain/aiprovider/AIProviderAvailability;", "warm", "", "complete", "Lcom/jarvis/brain/aiprovider/AICompletionResult;", "request", "Lcom/jarvis/brain/aiprovider/AICompletionRequest;", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LocalLLMProvider implements AIProvider {
    public static final String ID = "local.llm";
    private final AIProviderCapabilities capabilities;
    private final String displayName;
    private final ChatLlmEngine engine;
    private final String id;
    private final AIProviderKind kind;
    private final int maxContextTokens;
    private final int maxOutputTokens;

    public LocalLLMProvider(ChatLlmEngine engine, String id, String displayName, int maxContextTokens, int maxOutputTokens) {
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        this.engine = engine;
        this.id = id;
        this.displayName = displayName;
        this.maxContextTokens = maxContextTokens;
        this.maxOutputTokens = maxOutputTokens;
        this.kind = AIProviderKind.LOCAL;
        this.capabilities = new AIProviderCapabilities(false, false, true, this.maxContextTokens, this.maxOutputTokens, true, false, CollectionsKt.listOf((Object[]) new String[]{"backed_by=ChatLlmEngine", "privacy=local"}), modelIdHint(), QualityRequirement.FAST, QualityRequirement.FAST, PrivacyLevel.LOCAL_REQUIRED);
    }

    public /* synthetic */ LocalLLMProvider(ChatLlmEngine chatLlmEngine, String str, String str2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(chatLlmEngine, (i3 & 2) != 0 ? ID : str, (i3 & 4) != 0 ? "Local LLM" : str2, (i3 & 8) != 0 ? 256 : i, (i3 & 16) != 0 ? 64 : i2);
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public String getDisplayName() {
        return this.displayName;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderCapabilities getCapabilities() {
        return this.capabilities;
    }

    private final String modelIdHint() {
        ChatLlmEngine chatLlmEngine = this.engine;
        ModelNamed modelNamed = chatLlmEngine instanceof ModelNamed ? (ModelNamed) chatLlmEngine : null;
        String named = modelNamed != null ? modelNamed.getModelId() : null;
        String str = named;
        return !(str == null || StringsKt.isBlank(str)) ? named : "local.gguf";
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderAvailability availability() {
        if (this.engine.isReady()) {
            return this.engine.isLoaded() ? AIProviderAvailability.AVAILABLE : AIProviderAvailability.DEGRADED;
        }
        return AIProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public void warm() {
        if (this.engine.isReady()) {
            this.engine.warm();
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x00b1 A[Catch: all -> 0x0167, TryCatch #0 {all -> 0x0167, blocks: (B:11:0x0079, B:13:0x0081, B:15:0x009a, B:17:0x00a1, B:25:0x00e6, B:27:0x00f4, B:33:0x0100, B:34:0x0133, B:23:0x00b1, B:24:0x00cc), top: B:45:0x0079 }] */
    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AICompletionResult complete(AICompletionRequest request) {
        String simpleName;
        String it;
        LlmStructuredDecision llmStructuredDecisionDecide;
        Intrinsics.checkNotNullParameter(request, "request");
        if (request.getCancelRequested()) {
            return new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.CANCELLED, null, 0L, new AIProviderError(AIProviderErrorCategory.CANCELLED, "CANCELLED", "Cancellation requested before generation"), false, null, null, null, null, null, 4056, null);
        }
        if (!this.engine.isReady()) {
            return new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.FAILURE, null, 0L, new AIProviderError(AIProviderErrorCategory.NOT_READY, "MODEL_NOT_READY", "Local model file not available"), false, null, null, null, null, null, 4056, null);
        }
        long started = System.nanoTime();
        try {
            if (!request.getWantStructured() || (llmStructuredDecisionDecide = this.engine.decide(request.getUserText(), request.getHistory(), request.getMemories(), request.getPersona())) == null || (it = llmStructuredDecisionDecide.getUtterance()) == null) {
                it = ChatLlmEngine.generate$default(this.engine, request.getUserText(), request.getHistory(), request.getMemories(), request.getPersona(), null, 16, null);
            } else {
                if (StringsKt.isBlank(it)) {
                    it = null;
                }
                if (it == null) {
                    it = ChatLlmEngine.generate$default(this.engine, request.getUserText(), request.getHistory(), request.getMemories(), request.getPersona(), null, 16, null);
                }
            }
            long latency = (System.nanoTime() - started) / ((long) DurationKt.NANOS_IN_MILLIS);
            String str = it;
            return str == null || StringsKt.isBlank(str) ? new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.FAILURE, null, latency, new AIProviderError(AIProviderErrorCategory.GENERATION_FAILED, "EMPTY_OUTPUT", "Local LLM returned empty output"), false, null, null, null, null, null, 4040, null) : new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.SUCCESS, it, latency, null, false, modelIdHint(), null, TtftKind.NOT_AVAILABLE, null, null, 3104, null);
        } catch (Throwable t) {
            String requestId = request.getRequestId();
            String id = getId();
            AICompletionStatus aICompletionStatus = AICompletionStatus.FAILURE;
            long jNanoTime = (System.nanoTime() - started) / ((long) DurationKt.NANOS_IN_MILLIS);
            AIProviderErrorCategory aIProviderErrorCategory = AIProviderErrorCategory.INTERNAL;
            String message = t.getMessage();
            if (message == null || (simpleName = StringsKt.take(message, 200)) == null) {
                simpleName = t.getClass().getSimpleName();
            }
            Intrinsics.checkNotNull(simpleName);
            return new AICompletionResult(requestId, id, aICompletionStatus, null, jNanoTime, new AIProviderError(aIProviderErrorCategory, "LOCAL_EXCEPTION", simpleName), false, null, null, null, null, null, 4040, null);
        }
    }
}
