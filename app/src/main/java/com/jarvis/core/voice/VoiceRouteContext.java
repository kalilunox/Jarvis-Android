package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HybridVoiceStack.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/voice/VoiceRouteContext;", "", "isOnline", "", "preferCloud", "privacyRestricted", "<init>", "(ZZZ)V", "()Z", "getPreferCloud", "getPrivacyRestricted", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VoiceRouteContext {
    private final boolean isOnline;
    private final boolean preferCloud;
    private final boolean privacyRestricted;

    public static /* synthetic */ VoiceRouteContext copy$default(VoiceRouteContext voiceRouteContext, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = voiceRouteContext.isOnline;
        }
        if ((i & 2) != 0) {
            z2 = voiceRouteContext.preferCloud;
        }
        if ((i & 4) != 0) {
            z3 = voiceRouteContext.privacyRestricted;
        }
        return voiceRouteContext.copy(z, z2, z3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getPreferCloud() {
        return this.preferCloud;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPrivacyRestricted() {
        return this.privacyRestricted;
    }

    public final VoiceRouteContext copy(boolean isOnline, boolean preferCloud, boolean privacyRestricted) {
        return new VoiceRouteContext(isOnline, preferCloud, privacyRestricted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VoiceRouteContext)) {
            return false;
        }
        VoiceRouteContext voiceRouteContext = (VoiceRouteContext) other;
        return this.isOnline == voiceRouteContext.isOnline && this.preferCloud == voiceRouteContext.preferCloud && this.privacyRestricted == voiceRouteContext.privacyRestricted;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.isOnline) * 31) + Boolean.hashCode(this.preferCloud)) * 31) + Boolean.hashCode(this.privacyRestricted);
    }

    public String toString() {
        return "VoiceRouteContext(isOnline=" + this.isOnline + ", preferCloud=" + this.preferCloud + ", privacyRestricted=" + this.privacyRestricted + ")";
    }

    public VoiceRouteContext(boolean isOnline, boolean preferCloud, boolean privacyRestricted) {
        this.isOnline = isOnline;
        this.preferCloud = preferCloud;
        this.privacyRestricted = privacyRestricted;
    }

    public /* synthetic */ VoiceRouteContext(boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? false : z3);
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final boolean getPreferCloud() {
        return this.preferCloud;
    }

    public final boolean getPrivacyRestricted() {
        return this.privacyRestricted;
    }
}
