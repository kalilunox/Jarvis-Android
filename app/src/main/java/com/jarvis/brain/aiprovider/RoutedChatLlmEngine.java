package com.jarvis.brain.aiprovider;

import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.brain.ChatLlmEngine;
import com.jarvis.brain.LlmStructuredDecision;
import com.jarvis.core.llm.AssembledLlmContext;
import com.jarvis.core.runtime.TtftKind;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RoutedChatLlmEngine.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bc\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010)\u001a\u00020\u0007H\u0016J\b\u0010*\u001a\u00020\u0007H\u0016J\b\u0010+\u001a\u00020,H\u0016J:\u0010-\u001a\u0004\u0018\u00010\u00102\u0006\u0010.\u001a\u00020\u00102\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0010002\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00102\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0018\u00105\u001a\u00020,2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0002R\u0016\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\"\u0010\u0016\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u001e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u0004\u0018\u00010\u001b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u001b@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u001e\u0010$\u001a\u00020#2\u0006\u0010\u000f\u001a\u00020#@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001e\u0010'\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001a¨\u0006:"}, d2 = {"Lcom/jarvis/brain/aiprovider/RoutedChatLlmEngine;", "Lcom/jarvis/brain/ChatLlmEngine;", "localProvider", "Lkotlin/Function0;", "routerProvider", "Lcom/jarvis/brain/aiprovider/AIRouter;", "isOnline", "", "preferCloud", "complexity", "Lcom/jarvis/brain/aiprovider/AIRequestComplexity;", "privacyLevel", "Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "value", "", "lastProviderId", "getLastProviderId", "()Ljava/lang/String;", "lastModelId", "getLastModelId", "lastSelectionReason", "getLastSelectionReason", "lastFallback", "getLastFallback", "()Z", "", "lastLatencyMs", "getLastLatencyMs", "()J", "lastTtftMs", "getLastTtftMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "Lcom/jarvis/core/runtime/TtftKind;", "lastTtftKind", "getLastTtftKind", "()Lcom/jarvis/core/runtime/TtftKind;", "lastStreamed", "getLastStreamed", "isReady", "isLoaded", "warm", "", "generate", "userText", "history", "", "memories", "persona", "assembled", "Lcom/jarvis/core/llm/AssembledLlmContext;", "remember", "sel", "Lcom/jarvis/brain/aiprovider/AIProviderSelection;", "result", "Lcom/jarvis/brain/aiprovider/AICompletionResult;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RoutedChatLlmEngine implements ChatLlmEngine {
    private final Function0<AIRequestComplexity> complexity;
    private final Function0<Boolean> isOnline;
    private volatile boolean lastFallback;
    private volatile long lastLatencyMs;
    private volatile String lastModelId;
    private volatile String lastProviderId;
    private volatile String lastSelectionReason;
    private volatile boolean lastStreamed;
    private volatile TtftKind lastTtftKind;
    private volatile Long lastTtftMs;
    private final Function0<ChatLlmEngine> localProvider;
    private final Function0<Boolean> preferCloud;
    private final Function0<PrivacyLevel> privacyLevel;
    private final Function0<AIRouter> routerProvider;

    /* JADX WARN: Multi-variable type inference failed */
    public RoutedChatLlmEngine(Function0<? extends ChatLlmEngine> localProvider, Function0<AIRouter> routerProvider, Function0<Boolean> isOnline, Function0<Boolean> preferCloud, Function0<? extends AIRequestComplexity> complexity, Function0<? extends PrivacyLevel> privacyLevel) {
        Intrinsics.checkNotNullParameter(localProvider, "localProvider");
        Intrinsics.checkNotNullParameter(routerProvider, "routerProvider");
        Intrinsics.checkNotNullParameter(isOnline, "isOnline");
        Intrinsics.checkNotNullParameter(preferCloud, "preferCloud");
        Intrinsics.checkNotNullParameter(complexity, "complexity");
        Intrinsics.checkNotNullParameter(privacyLevel, "privacyLevel");
        this.localProvider = localProvider;
        this.routerProvider = routerProvider;
        this.isOnline = isOnline;
        this.preferCloud = preferCloud;
        this.complexity = complexity;
        this.privacyLevel = privacyLevel;
        this.lastTtftKind = TtftKind.NOT_AVAILABLE;
    }

    public /* synthetic */ RoutedChatLlmEngine(Function0 function0, Function0 function1, Function0 function2, Function0 function3, Function0 function4, Function0 function5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Function0 function6;
        Function0 function7;
        if ((i & 16) == 0) {
            function6 = function4;
        } else {
            function6 = new Function0() { // from class: com.jarvis.brain.aiprovider.RoutedChatLlmEngine$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AIRequestComplexity.SIMPLE;
                }
            };
        }
        if ((i & 32) == 0) {
            function7 = function5;
        } else {
            function7 = new Function0() { // from class: com.jarvis.brain.aiprovider.RoutedChatLlmEngine$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PrivacyLevel.NORMAL;
                }
            };
        }
        this(function0, function1, function2, function3, function6, function7);
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public /* bridge */ LlmStructuredDecision decide(String userText, List<String> list, String memories, String persona) {
        return super.decide(userText, list, memories, persona);
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public /* bridge */ String generateWithContext(String userText, String persona, AssembledLlmContext assembled) {
        return super.generateWithContext(userText, persona, assembled);
    }

    public final String getLastProviderId() {
        return this.lastProviderId;
    }

    public final String getLastModelId() {
        return this.lastModelId;
    }

    public final String getLastSelectionReason() {
        return this.lastSelectionReason;
    }

    public final boolean getLastFallback() {
        return this.lastFallback;
    }

    public final long getLastLatencyMs() {
        return this.lastLatencyMs;
    }

    public final Long getLastTtftMs() {
        return this.lastTtftMs;
    }

    public final TtftKind getLastTtftKind() {
        return this.lastTtftKind;
    }

    public final boolean getLastStreamed() {
        return this.lastStreamed;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public boolean isReady() {
        ChatLlmEngine local = this.localProvider.invoke();
        if (local != null && local.isReady()) {
            return true;
        }
        AIRouter router = this.routerProvider.invoke();
        if (router == null) {
            return false;
        }
        AIProviderSelection sel = AIRouter.select$default(router, AiTaskProfile.INSTANCE.forChat(this.isOnline.invoke().booleanValue(), this.preferCloud.invoke().booleanValue(), this.complexity.invoke(), (8 & 8) != 0 ? PrivacyLevel.NORMAL : this.privacyLevel.invoke(), (8 & 16) != 0 ? "" : null, (8 & 32) != 0 ? 0 : 0), null, 2, null);
        return (sel.getProvider() == null || sel.getProvider().availability() == AIProviderAvailability.UNAVAILABLE) ? false : true;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public boolean isLoaded() {
        ChatLlmEngine chatLlmEngineInvoke = this.localProvider.invoke();
        return (chatLlmEngineInvoke != null && chatLlmEngineInvoke.isLoaded()) || isReady();
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public void warm() {
        ChatLlmEngine chatLlmEngineInvoke = this.localProvider.invoke();
        if (chatLlmEngineInvoke != null) {
            chatLlmEngineInvoke.warm();
        }
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public String generate(String userText, List<String> history, String memories, String persona, AssembledLlmContext assembled) {
        String modelId;
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        AIRouter aIRouterInvoke = this.routerProvider.invoke();
        String str = "chat-" + System.nanoTime();
        AiTaskProfile aiTaskProfileForChat = AiTaskProfile.INSTANCE.forChat(this.isOnline.invoke().booleanValue(), this.preferCloud.invoke().booleanValue(), this.complexity.invoke(), this.privacyLevel.invoke(), str, userText.length());
        if (aIRouterInvoke != null) {
            Pair pairComplete$default = AIRouter.complete$default(aIRouterInvoke, aiTaskProfileForChat, new AICompletionRequest(str, userText, history, memories, persona, false, false, null, null, assembled, 480, null), null, 4, null);
            AIProviderSelection aIProviderSelection = (AIProviderSelection) pairComplete$default.component1();
            AICompletionResult aICompletionResult = (AICompletionResult) pairComplete$default.component2();
            remember(aIProviderSelection, aICompletionResult);
            if (aICompletionResult.getStatus() == AICompletionStatus.SUCCESS) {
                String text = aICompletionResult.getText();
                if (!(text == null || StringsKt.isBlank(text))) {
                    AIProvider provider = aIProviderSelection.getProvider();
                    Log.i("Jarvis.CloudLLM", "ok provider=" + (provider != null ? provider.getId() : null) + " model=" + aIProviderSelection.getModelId() + " reason=" + aIProviderSelection.getSelectionReason() + " fallback=" + aIProviderSelection.getFallbackUsed() + " latency=" + aICompletionResult.getLatencyMs() + "ms");
                    return aICompletionResult.getText();
                }
            }
            AICompletionStatus status = aICompletionResult.getStatus();
            AIProviderError error = aICompletionResult.getError();
            String code = error != null ? error.getCode() : null;
            AIProvider provider2 = aIProviderSelection.getProvider();
            Log.w("Jarvis.CloudLLM", "router miss status=" + status + " err=" + code + " provider=" + (provider2 != null ? provider2.getId() : null) + " — try direct local");
        }
        ChatLlmEngine chatLlmEngineInvoke = this.localProvider.invoke();
        String strGenerate = chatLlmEngineInvoke != null ? chatLlmEngineInvoke.generate(userText, history, memories, persona, assembled) : null;
        String str2 = strGenerate;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            this.lastProviderId = LocalLLMProvider.ID;
            ChatLlmEngine chatLlmEngineInvoke2 = this.localProvider.invoke();
            ModelNamed modelNamed = chatLlmEngineInvoke2 instanceof ModelNamed ? (ModelNamed) chatLlmEngineInvoke2 : null;
            if (modelNamed == null || (modelId = modelNamed.getModelId()) == null) {
                modelId = ImagesContract.LOCAL;
            }
            this.lastModelId = modelId;
            this.lastSelectionReason = "DIRECT_LOCAL";
            this.lastFallback = true;
        }
        return strGenerate;
    }

    private final void remember(AIProviderSelection sel, AICompletionResult result) {
        String providerId;
        AIProvider provider = sel.getProvider();
        if (provider == null || (providerId = provider.getId()) == null) {
            providerId = result.getProviderId();
        }
        this.lastProviderId = providerId;
        String modelId = result.getModelId();
        if (modelId == null) {
            modelId = sel.getModelId();
        }
        this.lastModelId = modelId;
        this.lastSelectionReason = sel.getSelectionReason();
        this.lastFallback = sel.getFallbackUsed();
        this.lastLatencyMs = result.getLatencyMs();
        this.lastTtftMs = result.getTtftMs();
        this.lastTtftKind = result.getTtftKind();
        this.lastStreamed = result.getStreamed();
    }
}
