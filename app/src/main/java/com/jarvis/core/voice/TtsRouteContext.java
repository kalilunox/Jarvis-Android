package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/jarvis/core/voice/TtsRouteContext;", "", "isOnline", "", "preference", "Lcom/jarvis/core/voice/TtsPreference;", "privacyRestricted", "cloudHealthy", "localHealthy", "preferLowLatency", "language", "", "<init>", "(ZLcom/jarvis/core/voice/TtsPreference;ZZZZLjava/lang/String;)V", "()Z", "getPreference", "()Lcom/jarvis/core/voice/TtsPreference;", "getPrivacyRestricted", "getCloudHealthy", "getLocalHealthy", "getPreferLowLatency", "getLanguage", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TtsRouteContext {
    private final boolean cloudHealthy;
    private final boolean isOnline;
    private final String language;
    private final boolean localHealthy;
    private final boolean preferLowLatency;
    private final TtsPreference preference;
    private final boolean privacyRestricted;

    public static /* synthetic */ TtsRouteContext copy$default(TtsRouteContext ttsRouteContext, boolean z, TtsPreference ttsPreference, boolean z2, boolean z3, boolean z4, boolean z5, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = ttsRouteContext.isOnline;
        }
        if ((i & 2) != 0) {
            ttsPreference = ttsRouteContext.preference;
        }
        TtsPreference ttsPreference2 = ttsPreference;
        if ((i & 4) != 0) {
            z2 = ttsRouteContext.privacyRestricted;
        }
        boolean z6 = z2;
        if ((i & 8) != 0) {
            z3 = ttsRouteContext.cloudHealthy;
        }
        boolean z7 = z3;
        if ((i & 16) != 0) {
            z4 = ttsRouteContext.localHealthy;
        }
        boolean z8 = z4;
        if ((i & 32) != 0) {
            z5 = ttsRouteContext.preferLowLatency;
        }
        boolean z9 = z5;
        if ((i & 64) != 0) {
            str = ttsRouteContext.language;
        }
        return ttsRouteContext.copy(z, ttsPreference2, z6, z7, z8, z9, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TtsPreference getPreference() {
        return this.preference;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPrivacyRestricted() {
        return this.privacyRestricted;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getCloudHealthy() {
        return this.cloudHealthy;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getLocalHealthy() {
        return this.localHealthy;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getPreferLowLatency() {
        return this.preferLowLatency;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    public final TtsRouteContext copy(boolean isOnline, TtsPreference preference, boolean privacyRestricted, boolean cloudHealthy, boolean localHealthy, boolean preferLowLatency, String language) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(language, "language");
        return new TtsRouteContext(isOnline, preference, privacyRestricted, cloudHealthy, localHealthy, preferLowLatency, language);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsRouteContext)) {
            return false;
        }
        TtsRouteContext ttsRouteContext = (TtsRouteContext) other;
        return this.isOnline == ttsRouteContext.isOnline && this.preference == ttsRouteContext.preference && this.privacyRestricted == ttsRouteContext.privacyRestricted && this.cloudHealthy == ttsRouteContext.cloudHealthy && this.localHealthy == ttsRouteContext.localHealthy && this.preferLowLatency == ttsRouteContext.preferLowLatency && Intrinsics.areEqual(this.language, ttsRouteContext.language);
    }

    public int hashCode() {
        return (((((((((((Boolean.hashCode(this.isOnline) * 31) + this.preference.hashCode()) * 31) + Boolean.hashCode(this.privacyRestricted)) * 31) + Boolean.hashCode(this.cloudHealthy)) * 31) + Boolean.hashCode(this.localHealthy)) * 31) + Boolean.hashCode(this.preferLowLatency)) * 31) + this.language.hashCode();
    }

    public String toString() {
        return "TtsRouteContext(isOnline=" + this.isOnline + ", preference=" + this.preference + ", privacyRestricted=" + this.privacyRestricted + ", cloudHealthy=" + this.cloudHealthy + ", localHealthy=" + this.localHealthy + ", preferLowLatency=" + this.preferLowLatency + ", language=" + this.language + ")";
    }

    public TtsRouteContext(boolean isOnline, TtsPreference preference, boolean privacyRestricted, boolean cloudHealthy, boolean localHealthy, boolean preferLowLatency, String language) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(language, "language");
        this.isOnline = isOnline;
        this.preference = preference;
        this.privacyRestricted = privacyRestricted;
        this.cloudHealthy = cloudHealthy;
        this.localHealthy = localHealthy;
        this.preferLowLatency = preferLowLatency;
        this.language = language;
    }

    public /* synthetic */ TtsRouteContext(boolean z, TtsPreference ttsPreference, boolean z2, boolean z3, boolean z4, boolean z5, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? TtsPreference.AUTO : ttsPreference, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? true : z3, (i & 16) != 0 ? true : z4, (i & 32) == 0 ? z5 : true, (i & 64) != 0 ? "ru" : str);
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final TtsPreference getPreference() {
        return this.preference;
    }

    public final boolean getPrivacyRestricted() {
        return this.privacyRestricted;
    }

    public final boolean getCloudHealthy() {
        return this.cloudHealthy;
    }

    public final boolean getLocalHealthy() {
        return this.localHealthy;
    }

    public final boolean getPreferLowLatency() {
        return this.preferLowLatency;
    }

    public final String getLanguage() {
        return this.language;
    }
}
