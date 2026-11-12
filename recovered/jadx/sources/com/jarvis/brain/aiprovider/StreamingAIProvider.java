package com.jarvis.brain.aiprovider;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: compiled from: AiStreamModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/aiprovider/StreamingAIProvider;", "Lcom/jarvis/brain/aiprovider/AIProvider;", "stream", "Lcom/jarvis/brain/aiprovider/AIStreamHandle;", "request", "Lcom/jarvis/brain/aiprovider/AICompletionRequest;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/brain/aiprovider/AIStreamListener;", "cancelFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface StreamingAIProvider extends AIProvider {
    AIStreamHandle stream(AICompletionRequest request, AIStreamListener listener, AtomicBoolean cancelFlag);

    /* JADX INFO: compiled from: AiStreamModels.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ AIStreamHandle stream$default(StreamingAIProvider streamingAIProvider, AICompletionRequest aICompletionRequest, AIStreamListener aIStreamListener, AtomicBoolean atomicBoolean, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stream");
        }
        if ((i & 4) != 0) {
            atomicBoolean = new AtomicBoolean(aICompletionRequest.getCancelRequested());
        }
        return streamingAIProvider.stream(aICompletionRequest, aIStreamListener, atomicBoolean);
    }
}
