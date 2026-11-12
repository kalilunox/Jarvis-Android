package com.jarvis.core.voice;

import kotlin.Metadata;

/* JADX INFO: compiled from: HybridVoiceStack.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\n\u001a\u00020\u000bH&J\u001c\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/jarvis/core/voice/AsrProvider;", "", "id", "", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "transcribe", "pcm16le", "", "sampleRateHz", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AsrProvider {
    VoiceProviderAvailability availability();

    String getId();

    VoiceProviderKind getKind();

    String transcribe(byte[] pcm16le, int sampleRateHz);

    /* JADX INFO: compiled from: HybridVoiceStack.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ String transcribe$default(AsrProvider asrProvider, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transcribe");
        }
        if ((i2 & 2) != 0) {
            i = 16000;
        }
        return asrProvider.transcribe(bArr, i);
    }
}
