package com.jarvis.core.aioutput;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StructuredAiChatBridge.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¨\u0006\t"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiChatBridge;", "", "<init>", "()V", "wrapChatText", "Lcom/jarvis/core/aioutput/StructuredAiProcessResult;", "raw", "", "requestId", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StructuredAiChatBridge {
    public static final StructuredAiChatBridge INSTANCE = new StructuredAiChatBridge();

    private StructuredAiChatBridge() {
    }

    public static /* synthetic */ StructuredAiProcessResult wrapChatText$default(StructuredAiChatBridge structuredAiChatBridge, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return structuredAiChatBridge.wrapChatText(str, str2);
    }

    public final StructuredAiProcessResult wrapChatText(String raw, String requestId) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        StructuredAiOutputPipeline pipeline = new StructuredAiOutputPipeline(null, 1, null);
        StructuredAiProcessResult asJson = pipeline.process(raw, StructuredAiTask.CHAT, (56 & 4) != 0 ? null : requestId, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (56 & 32) != 0 ? null : null);
        if (asJson instanceof StructuredAiProcessResult.Valid) {
            return asJson;
        }
        String text = StringsKt.trim((CharSequence) raw).toString();
        if (StringsKt.isBlank(text)) {
            return new StructuredAiProcessResult.Invalid(new StructuredAiDiagnostics(false, null, null, StructuredParseStatus.FAIL, StructuredValidationStatus.FAIL, false, null, "empty_chat", 0L, 352, null), "");
        }
        return new StructuredAiProcessResult.Valid(new StructuredAiEnvelope(StructuredAiLimits.SCHEMA_VERSION, StructuredAiResponseType.ANSWER, requestId, null, StringsKt.take(text, StructuredAiLimits.MAX_TEXT_CHARS), null, null, null, null, null, null, null, null, null, null, null, 65512, null), new StructuredAiDiagnostics(false, StructuredAiLimits.SCHEMA_VERSION, "ANSWER", StructuredParseStatus.SUCCESS, StructuredValidationStatus.SUCCESS, false, null, "chat_free_text", 0L, 352, null));
    }
}
