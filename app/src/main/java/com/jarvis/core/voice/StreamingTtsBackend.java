package com.jarvis.core.voice;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0012\u001a\u00020\u0013H&J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001aH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001bÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/voice/StreamingTtsBackend;", "", "id", "", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "model", "getModel", "defaultVoice", "getDefaultVoice", "supportsStreamingAudio", "", "getSupportsStreamingAudio", "()Z", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "synthesize", "Lcom/jarvis/core/voice/TtsAudioResult;", VoiceService.EXTRA_TEXT, "voice", "Lcom/jarvis/core/voice/TtsVoiceConfig;", "cancelFlag", "Lkotlin/Function0;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface StreamingTtsBackend {
    VoiceProviderAvailability availability();

    String getDefaultVoice();

    String getId();

    VoiceProviderKind getKind();

    String getModel();

    boolean getSupportsStreamingAudio();

    TtsAudioResult synthesize(String text, TtsVoiceConfig voice, Function0<Boolean> cancelFlag);
}
