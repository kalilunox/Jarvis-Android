package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AiStreamModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002¨\u0006\u0005"}, d2 = {"asStreamingOrNull", "Lcom/jarvis/brain/aiprovider/StreamingAIProvider;", "Lcom/jarvis/brain/aiprovider/AIProvider;", "supportsRealStreaming", "", "brain_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AiStreamModelsKt {
    public static final StreamingAIProvider asStreamingOrNull(AIProvider $this$asStreamingOrNull) {
        Intrinsics.checkNotNullParameter($this$asStreamingOrNull, "<this>");
        if ($this$asStreamingOrNull instanceof StreamingAIProvider) {
            return (StreamingAIProvider) $this$asStreamingOrNull;
        }
        return null;
    }

    public static final boolean supportsRealStreaming(AIProvider $this$supportsRealStreaming) {
        Intrinsics.checkNotNullParameter($this$supportsRealStreaming, "<this>");
        return $this$supportsRealStreaming.getCapabilities().getSupportsStreaming() && ($this$supportsRealStreaming instanceof StreamingAIProvider);
    }
}
