package com.jarvis.core.voice;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HybridVoiceStack.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/voice/LocalTtsStub;", "Lcom/jarvis/core/voice/TtsProvider;", "id", "", "ready", "", "<init>", "(Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "synthesize", "", VoiceService.EXTRA_TEXT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LocalTtsStub implements TtsProvider {
    private final String id;
    private final VoiceProviderKind kind;
    private final boolean ready;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalTtsStub() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public LocalTtsStub(String id, boolean ready) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.ready = ready;
        this.kind = VoiceProviderKind.LOCAL;
    }

    public /* synthetic */ LocalTtsStub(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "tts.local" : str, (i & 2) != 0 ? true : z);
    }

    @Override // com.jarvis.core.voice.TtsProvider
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.core.voice.TtsProvider
    public VoiceProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.core.voice.TtsProvider
    public VoiceProviderAvailability availability() {
        return this.ready ? VoiceProviderAvailability.AVAILABLE : VoiceProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.core.voice.TtsProvider
    public byte[] synthesize(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (!this.ready || StringsKt.isBlank(text)) {
            return null;
        }
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = 1;
        }
        return bArr;
    }
}
