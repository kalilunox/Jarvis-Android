package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/jarvis/core/voice/TtsRouteDecision;", "", "providerId", "", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "model", "voice", "reason", "Lcom/jarvis/core/voice/TtsRouteReason;", "streamingSupported", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/voice/VoiceProviderKind;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/voice/TtsRouteReason;Z)V", "getProviderId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "getModel", "getVoice", "getReason", "()Lcom/jarvis/core/voice/TtsRouteReason;", "getStreamingSupported", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TtsRouteDecision {
    private final VoiceProviderKind kind;
    private final String model;
    private final String providerId;
    private final TtsRouteReason reason;
    private final boolean streamingSupported;
    private final String voice;

    public static /* synthetic */ TtsRouteDecision copy$default(TtsRouteDecision ttsRouteDecision, String str, VoiceProviderKind voiceProviderKind, String str2, String str3, TtsRouteReason ttsRouteReason, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ttsRouteDecision.providerId;
        }
        if ((i & 2) != 0) {
            voiceProviderKind = ttsRouteDecision.kind;
        }
        VoiceProviderKind voiceProviderKind2 = voiceProviderKind;
        if ((i & 4) != 0) {
            str2 = ttsRouteDecision.model;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            str3 = ttsRouteDecision.voice;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            ttsRouteReason = ttsRouteDecision.reason;
        }
        TtsRouteReason ttsRouteReason2 = ttsRouteReason;
        if ((i & 32) != 0) {
            z = ttsRouteDecision.streamingSupported;
        }
        return ttsRouteDecision.copy(str, voiceProviderKind2, str4, str5, ttsRouteReason2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final VoiceProviderKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVoice() {
        return this.voice;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TtsRouteReason getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getStreamingSupported() {
        return this.streamingSupported;
    }

    public final TtsRouteDecision copy(String providerId, VoiceProviderKind kind, String model, String voice, TtsRouteReason reason, boolean streamingSupported) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new TtsRouteDecision(providerId, kind, model, voice, reason, streamingSupported);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsRouteDecision)) {
            return false;
        }
        TtsRouteDecision ttsRouteDecision = (TtsRouteDecision) other;
        return Intrinsics.areEqual(this.providerId, ttsRouteDecision.providerId) && this.kind == ttsRouteDecision.kind && Intrinsics.areEqual(this.model, ttsRouteDecision.model) && Intrinsics.areEqual(this.voice, ttsRouteDecision.voice) && this.reason == ttsRouteDecision.reason && this.streamingSupported == ttsRouteDecision.streamingSupported;
    }

    public int hashCode() {
        return (((((((((this.providerId.hashCode() * 31) + this.kind.hashCode()) * 31) + this.model.hashCode()) * 31) + this.voice.hashCode()) * 31) + this.reason.hashCode()) * 31) + Boolean.hashCode(this.streamingSupported);
    }

    public String toString() {
        return "TtsRouteDecision(providerId=" + this.providerId + ", kind=" + this.kind + ", model=" + this.model + ", voice=" + this.voice + ", reason=" + this.reason + ", streamingSupported=" + this.streamingSupported + ")";
    }

    public TtsRouteDecision(String providerId, VoiceProviderKind kind, String model, String voice, TtsRouteReason reason, boolean streamingSupported) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.providerId = providerId;
        this.kind = kind;
        this.model = model;
        this.voice = voice;
        this.reason = reason;
        this.streamingSupported = streamingSupported;
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final VoiceProviderKind getKind() {
        return this.kind;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getVoice() {
        return this.voice;
    }

    public final TtsRouteReason getReason() {
        return this.reason;
    }

    public final boolean getStreamingSupported() {
        return this.streamingSupported;
    }
}
