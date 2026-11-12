package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PhoneStateSnapshot.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006&"}, d2 = {"Lcom/jarvis/core/proactive/PhoneStateSnapshot;", "", "ringerMode", "Lcom/jarvis/core/proactive/RingerKind;", "interruptionFilter", "Lcom/jarvis/core/proactive/InterruptionKind;", "callActive", "", "jarvisSessionActive", "jarvisSpeaking", "notificationsAllowed", "voiceAvailable", "<init>", "(Lcom/jarvis/core/proactive/RingerKind;Lcom/jarvis/core/proactive/InterruptionKind;ZZZZZ)V", "getRingerMode", "()Lcom/jarvis/core/proactive/RingerKind;", "getInterruptionFilter", "()Lcom/jarvis/core/proactive/InterruptionKind;", "getCallActive", "()Z", "getJarvisSessionActive", "getJarvisSpeaking", "getNotificationsAllowed", "getVoiceAvailable", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PhoneStateSnapshot {
    private final boolean callActive;
    private final InterruptionKind interruptionFilter;
    private final boolean jarvisSessionActive;
    private final boolean jarvisSpeaking;
    private final boolean notificationsAllowed;
    private final RingerKind ringerMode;
    private final boolean voiceAvailable;

    public static /* synthetic */ PhoneStateSnapshot copy$default(PhoneStateSnapshot phoneStateSnapshot, RingerKind ringerKind, InterruptionKind interruptionKind, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, Object obj) {
        if ((i & 1) != 0) {
            ringerKind = phoneStateSnapshot.ringerMode;
        }
        if ((i & 2) != 0) {
            interruptionKind = phoneStateSnapshot.interruptionFilter;
        }
        InterruptionKind interruptionKind2 = interruptionKind;
        if ((i & 4) != 0) {
            z = phoneStateSnapshot.callActive;
        }
        boolean z6 = z;
        if ((i & 8) != 0) {
            z2 = phoneStateSnapshot.jarvisSessionActive;
        }
        boolean z7 = z2;
        if ((i & 16) != 0) {
            z3 = phoneStateSnapshot.jarvisSpeaking;
        }
        boolean z8 = z3;
        if ((i & 32) != 0) {
            z4 = phoneStateSnapshot.notificationsAllowed;
        }
        boolean z9 = z4;
        if ((i & 64) != 0) {
            z5 = phoneStateSnapshot.voiceAvailable;
        }
        return phoneStateSnapshot.copy(ringerKind, interruptionKind2, z6, z7, z8, z9, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final RingerKind getRingerMode() {
        return this.ringerMode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final InterruptionKind getInterruptionFilter() {
        return this.interruptionFilter;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getCallActive() {
        return this.callActive;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getJarvisSessionActive() {
        return this.jarvisSessionActive;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getJarvisSpeaking() {
        return this.jarvisSpeaking;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getNotificationsAllowed() {
        return this.notificationsAllowed;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getVoiceAvailable() {
        return this.voiceAvailable;
    }

    public final PhoneStateSnapshot copy(RingerKind ringerMode, InterruptionKind interruptionFilter, boolean callActive, boolean jarvisSessionActive, boolean jarvisSpeaking, boolean notificationsAllowed, boolean voiceAvailable) {
        Intrinsics.checkNotNullParameter(ringerMode, "ringerMode");
        Intrinsics.checkNotNullParameter(interruptionFilter, "interruptionFilter");
        return new PhoneStateSnapshot(ringerMode, interruptionFilter, callActive, jarvisSessionActive, jarvisSpeaking, notificationsAllowed, voiceAvailable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhoneStateSnapshot)) {
            return false;
        }
        PhoneStateSnapshot phoneStateSnapshot = (PhoneStateSnapshot) other;
        return this.ringerMode == phoneStateSnapshot.ringerMode && this.interruptionFilter == phoneStateSnapshot.interruptionFilter && this.callActive == phoneStateSnapshot.callActive && this.jarvisSessionActive == phoneStateSnapshot.jarvisSessionActive && this.jarvisSpeaking == phoneStateSnapshot.jarvisSpeaking && this.notificationsAllowed == phoneStateSnapshot.notificationsAllowed && this.voiceAvailable == phoneStateSnapshot.voiceAvailable;
    }

    public int hashCode() {
        return (((((((((((this.ringerMode.hashCode() * 31) + this.interruptionFilter.hashCode()) * 31) + Boolean.hashCode(this.callActive)) * 31) + Boolean.hashCode(this.jarvisSessionActive)) * 31) + Boolean.hashCode(this.jarvisSpeaking)) * 31) + Boolean.hashCode(this.notificationsAllowed)) * 31) + Boolean.hashCode(this.voiceAvailable);
    }

    public String toString() {
        return "PhoneStateSnapshot(ringerMode=" + this.ringerMode + ", interruptionFilter=" + this.interruptionFilter + ", callActive=" + this.callActive + ", jarvisSessionActive=" + this.jarvisSessionActive + ", jarvisSpeaking=" + this.jarvisSpeaking + ", notificationsAllowed=" + this.notificationsAllowed + ", voiceAvailable=" + this.voiceAvailable + ")";
    }

    public PhoneStateSnapshot(RingerKind ringerMode, InterruptionKind interruptionFilter, boolean callActive, boolean jarvisSessionActive, boolean jarvisSpeaking, boolean notificationsAllowed, boolean voiceAvailable) {
        Intrinsics.checkNotNullParameter(ringerMode, "ringerMode");
        Intrinsics.checkNotNullParameter(interruptionFilter, "interruptionFilter");
        this.ringerMode = ringerMode;
        this.interruptionFilter = interruptionFilter;
        this.callActive = callActive;
        this.jarvisSessionActive = jarvisSessionActive;
        this.jarvisSpeaking = jarvisSpeaking;
        this.notificationsAllowed = notificationsAllowed;
        this.voiceAvailable = voiceAvailable;
    }

    public final RingerKind getRingerMode() {
        return this.ringerMode;
    }

    public final InterruptionKind getInterruptionFilter() {
        return this.interruptionFilter;
    }

    public final boolean getCallActive() {
        return this.callActive;
    }

    public final boolean getJarvisSessionActive() {
        return this.jarvisSessionActive;
    }

    public final boolean getJarvisSpeaking() {
        return this.jarvisSpeaking;
    }

    public final boolean getNotificationsAllowed() {
        return this.notificationsAllowed;
    }

    public final boolean getVoiceAvailable() {
        return this.voiceAvailable;
    }
}
