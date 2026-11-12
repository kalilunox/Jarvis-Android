package com.jarvis.core.voice;

import kotlin.Metadata;

/* JADX INFO: compiled from: AsrContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH&J\u001a\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\f\u001a\u00020\rH&J\b\u0010\u0011\u001a\u00020\tH&J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\u0017À\u0006\u0003"}, d2 = {"Lcom/jarvis/core/voice/StreamingAsrSession;", "", "sessionId", "", "getSessionId", "()Ljava/lang/String;", "providerId", "getProviderId", "feedFloatPcm", "", "samples", "", "sampleRateHz", "", "feedPcm16le", "bytes", "", "finalizeUtterance", "cancel", "reason", "Lcom/jarvis/core/voice/AsrCancelReason;", "isActive", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface StreamingAsrSession {
    void cancel(AsrCancelReason reason);

    void feedFloatPcm(float[] samples, int sampleRateHz);

    void feedPcm16le(byte[] bytes, int sampleRateHz);

    void finalizeUtterance();

    String getProviderId();

    String getSessionId();

    boolean isActive();

    /* JADX INFO: compiled from: AsrContracts.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void feedFloatPcm$default(StreamingAsrSession streamingAsrSession, float[] fArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: feedFloatPcm");
        }
        if ((i2 & 2) != 0) {
            i = 16000;
        }
        streamingAsrSession.feedFloatPcm(fArr, i);
    }

    static /* synthetic */ void feedPcm16le$default(StreamingAsrSession streamingAsrSession, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: feedPcm16le");
        }
        if ((i2 & 2) != 0) {
            i = 16000;
        }
        streamingAsrSession.feedPcm16le(bArr, i);
    }
}
