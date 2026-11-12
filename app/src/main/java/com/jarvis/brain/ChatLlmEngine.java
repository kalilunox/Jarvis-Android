package com.jarvis.brain;

import com.jarvis.core.aioutput.StructuredAiLimits;
import com.jarvis.core.llm.AssembledLlmContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ChatLlmEngine.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J@\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\"\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J4\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bH\u0016¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/ChatLlmEngine;", "", "isReady", "", "isLoaded", "warm", "", "generate", "", "userText", "history", "", "memories", "persona", "assembled", "Lcom/jarvis/core/llm/AssembledLlmContext;", "generateWithContext", "decide", "Lcom/jarvis/brain/LlmStructuredDecision;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ChatLlmEngine {
    String generate(String userText, List<String> history, String memories, String persona, AssembledLlmContext assembled);

    boolean isLoaded();

    boolean isReady();

    void warm();

    /* JADX INFO: compiled from: ChatLlmEngine.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static String generateWithContext(ChatLlmEngine $this, String userText, String persona, AssembledLlmContext assembled) {
            Intrinsics.checkNotNullParameter(userText, "userText");
            Intrinsics.checkNotNullParameter(persona, "persona");
            Intrinsics.checkNotNullParameter(assembled, "assembled");
            return ChatLlmEngine.super.generateWithContext(userText, persona, assembled);
        }

        @Deprecated
        public static LlmStructuredDecision decide(ChatLlmEngine $this, String userText, List<String> history, String memories, String persona) {
            Intrinsics.checkNotNullParameter(userText, "userText");
            Intrinsics.checkNotNullParameter(history, "history");
            Intrinsics.checkNotNullParameter(memories, "memories");
            Intrinsics.checkNotNullParameter(persona, "persona");
            return ChatLlmEngine.super.decide(userText, history, memories, persona);
        }
    }

    static /* synthetic */ String generate$default(ChatLlmEngine chatLlmEngine, String str, List list, String str2, String str3, AssembledLlmContext assembledLlmContext, int i, Object obj) {
        if (obj == null) {
            return chatLlmEngine.generate(str, list, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? "CLASSIC" : str3, (i & 16) != 0 ? null : assembledLlmContext);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: generate");
    }

    default String generateWithContext(String userText, String persona, AssembledLlmContext assembled) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(persona, "persona");
        Intrinsics.checkNotNullParameter(assembled, "assembled");
        return generate(userText, assembled.getHistoryLines(), assembled.getLegacyMemoriesSnippet(), persona, assembled);
    }

    static /* synthetic */ LlmStructuredDecision decide$default(ChatLlmEngine chatLlmEngine, String str, List list, String str2, String str3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decide");
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        if ((i & 8) != 0) {
            str3 = "CLASSIC";
        }
        return chatLlmEngine.decide(str, list, str2, str3);
    }

    default LlmStructuredDecision decide(String userText, List<String> history, String memories, String persona) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        String text = generate$default(this, userText, history, memories, persona, null, 16, null);
        if (text == null) {
            return null;
        }
        LlmStructuredDecision llmStructuredDecision = LlmDecisionParser.INSTANCE.parse(text);
        if (llmStructuredDecision != null) {
            return llmStructuredDecision;
        }
        return new LlmStructuredDecision(LlmDecisionKind.ANSWER, StringsKt.take(text, StructuredAiLimits.MAX_QUESTION_CHARS), 0.0f, null, null, text, 28, null);
    }
}
