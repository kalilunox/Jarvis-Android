package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsrContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/jarvis/core/voice/AsrRouteDecision;", "", "providerId", "", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "model", "reason", "Lcom/jarvis/core/voice/AsrRouteReason;", "streaming", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/voice/VoiceProviderKind;Ljava/lang/String;Lcom/jarvis/core/voice/AsrRouteReason;Z)V", "getProviderId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "getModel", "getReason", "()Lcom/jarvis/core/voice/AsrRouteReason;", "getStreaming", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AsrRouteDecision {
    private final VoiceProviderKind kind;
    private final String model;
    private final String providerId;
    private final AsrRouteReason reason;
    private final boolean streaming;

    public static /* synthetic */ AsrRouteDecision copy$default(AsrRouteDecision asrRouteDecision, String str, VoiceProviderKind voiceProviderKind, String str2, AsrRouteReason asrRouteReason, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = asrRouteDecision.providerId;
        }
        if ((i & 2) != 0) {
            voiceProviderKind = asrRouteDecision.kind;
        }
        VoiceProviderKind voiceProviderKind2 = voiceProviderKind;
        if ((i & 4) != 0) {
            str2 = asrRouteDecision.model;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            asrRouteReason = asrRouteDecision.reason;
        }
        AsrRouteReason asrRouteReason2 = asrRouteReason;
        if ((i & 16) != 0) {
            z = asrRouteDecision.streaming;
        }
        return asrRouteDecision.copy(str, voiceProviderKind2, str3, asrRouteReason2, z);
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
    public final AsrRouteReason getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getStreaming() {
        return this.streaming;
    }

    public final AsrRouteDecision copy(String providerId, VoiceProviderKind kind, String model, AsrRouteReason reason, boolean streaming) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new AsrRouteDecision(providerId, kind, model, reason, streaming);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrRouteDecision)) {
            return false;
        }
        AsrRouteDecision asrRouteDecision = (AsrRouteDecision) other;
        return Intrinsics.areEqual(this.providerId, asrRouteDecision.providerId) && this.kind == asrRouteDecision.kind && Intrinsics.areEqual(this.model, asrRouteDecision.model) && this.reason == asrRouteDecision.reason && this.streaming == asrRouteDecision.streaming;
    }

    public int hashCode() {
        return (((((((this.providerId.hashCode() * 31) + this.kind.hashCode()) * 31) + this.model.hashCode()) * 31) + this.reason.hashCode()) * 31) + Boolean.hashCode(this.streaming);
    }

    public String toString() {
        return "AsrRouteDecision(providerId=" + this.providerId + ", kind=" + this.kind + ", model=" + this.model + ", reason=" + this.reason + ", streaming=" + this.streaming + ")";
    }

    public AsrRouteDecision(String providerId, VoiceProviderKind kind, String model, AsrRouteReason reason, boolean streaming) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.providerId = providerId;
        this.kind = kind;
        this.model = model;
        this.reason = reason;
        this.streaming = streaming;
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

    public final AsrRouteReason getReason() {
        return this.reason;
    }

    public final boolean getStreaming() {
        return this.streaming;
    }
}
