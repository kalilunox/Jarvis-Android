package com.jarvis.brain.aiprovider;

import com.jarvis.brain.ChatLlmEngine;
import com.jarvis.brain.LlmDecisionKind;
import com.jarvis.brain.LlmDecisionParser;
import com.jarvis.brain.LlmStructuredDecision;
import com.jarvis.core.aioutput.StructuredAiLimits;
import com.jarvis.core.llm.AssembledLlmContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ChatLlmEngineFromProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J:\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J0\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/brain/aiprovider/ChatLlmEngineFromProvider;", "Lcom/jarvis/brain/ChatLlmEngine;", "provider", "Lcom/jarvis/brain/aiprovider/AIProvider;", "<init>", "(Lcom/jarvis/brain/aiprovider/AIProvider;)V", "isReady", "", "isLoaded", "warm", "", "generate", "", "userText", "history", "", "memories", "persona", "assembled", "Lcom/jarvis/core/llm/AssembledLlmContext;", "decide", "Lcom/jarvis/brain/LlmStructuredDecision;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChatLlmEngineFromProvider implements ChatLlmEngine {
    private final AIProvider provider;

    public ChatLlmEngineFromProvider(AIProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.provider = provider;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public /* bridge */ String generateWithContext(String userText, String persona, AssembledLlmContext assembled) {
        return super.generateWithContext(userText, persona, assembled);
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public boolean isReady() {
        return this.provider.availability() != AIProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public boolean isLoaded() {
        return this.provider.availability() == AIProviderAvailability.AVAILABLE;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public void warm() {
        this.provider.warm();
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public String generate(String userText, List<String> history, String memories, String persona, AssembledLlmContext assembled) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        AICompletionResult result = this.provider.complete(new AICompletionRequest("chat-" + System.nanoTime(), userText, history, memories, persona, false, false, null, null, assembled, 448, null));
        if (result.getStatus() == AICompletionStatus.SUCCESS) {
            return result.getText();
        }
        return null;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public LlmStructuredDecision decide(String userText, List<String> history, String memories, String persona) {
        String text;
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        if (!this.provider.getCapabilities().getSupportsStructuredDecide()) {
            return super.decide(userText, history, memories, persona);
        }
        AICompletionResult result = this.provider.complete(new AICompletionRequest("decide-" + System.nanoTime(), userText, history, memories, persona, true, false, null, null, null, 960, null));
        if (result.getStatus() != AICompletionStatus.SUCCESS || (text = result.getText()) == null) {
            return null;
        }
        LlmStructuredDecision llmStructuredDecision = LlmDecisionParser.INSTANCE.parse(text);
        if (llmStructuredDecision != null) {
            return llmStructuredDecision;
        }
        return new LlmStructuredDecision(LlmDecisionKind.ANSWER, StringsKt.take(text, StructuredAiLimits.MAX_QUESTION_CHARS), 0.0f, null, null, text, 28, null);
    }
}
