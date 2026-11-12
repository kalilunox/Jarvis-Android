package com.jarvis.core.voice;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsrContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JU\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0007HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lcom/jarvis/core/voice/AsrSessionConfig;", "", "requestId", "", "sessionId", "language", "sampleRateHz", "", "maxBufferedMs", "vocabularyHints", "", "privacyLocalRequired", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/util/List;Z)V", "getRequestId", "()Ljava/lang/String;", "getSessionId", "getLanguage", "getSampleRateHz", "()I", "getMaxBufferedMs", "getVocabularyHints", "()Ljava/util/List;", "getPrivacyLocalRequired", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AsrSessionConfig {
    private final String language;
    private final int maxBufferedMs;
    private final boolean privacyLocalRequired;
    private final String requestId;
    private final int sampleRateHz;
    private final String sessionId;
    private final List<String> vocabularyHints;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AsrSessionConfig copy$default(AsrSessionConfig asrSessionConfig, String str, String str2, String str3, int i, int i2, List list, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = asrSessionConfig.requestId;
        }
        if ((i3 & 2) != 0) {
            str2 = asrSessionConfig.sessionId;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            str3 = asrSessionConfig.language;
        }
        String str5 = str3;
        if ((i3 & 8) != 0) {
            i = asrSessionConfig.sampleRateHz;
        }
        int i4 = i;
        if ((i3 & 16) != 0) {
            i2 = asrSessionConfig.maxBufferedMs;
        }
        int i5 = i2;
        if ((i3 & 32) != 0) {
            list = asrSessionConfig.vocabularyHints;
        }
        List list2 = list;
        if ((i3 & 64) != 0) {
            z = asrSessionConfig.privacyLocalRequired;
        }
        return asrSessionConfig.copy(str, str4, str5, i4, i5, list2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getSampleRateHz() {
        return this.sampleRateHz;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getMaxBufferedMs() {
        return this.maxBufferedMs;
    }

    public final List<String> component6() {
        return this.vocabularyHints;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getPrivacyLocalRequired() {
        return this.privacyLocalRequired;
    }

    public final AsrSessionConfig copy(String requestId, String sessionId, String language, int sampleRateHz, int maxBufferedMs, List<String> vocabularyHints, boolean privacyLocalRequired) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(vocabularyHints, "vocabularyHints");
        return new AsrSessionConfig(requestId, sessionId, language, sampleRateHz, maxBufferedMs, vocabularyHints, privacyLocalRequired);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrSessionConfig)) {
            return false;
        }
        AsrSessionConfig asrSessionConfig = (AsrSessionConfig) other;
        return Intrinsics.areEqual(this.requestId, asrSessionConfig.requestId) && Intrinsics.areEqual(this.sessionId, asrSessionConfig.sessionId) && Intrinsics.areEqual(this.language, asrSessionConfig.language) && this.sampleRateHz == asrSessionConfig.sampleRateHz && this.maxBufferedMs == asrSessionConfig.maxBufferedMs && Intrinsics.areEqual(this.vocabularyHints, asrSessionConfig.vocabularyHints) && this.privacyLocalRequired == asrSessionConfig.privacyLocalRequired;
    }

    public int hashCode() {
        return (((((((((((this.requestId.hashCode() * 31) + this.sessionId.hashCode()) * 31) + this.language.hashCode()) * 31) + Integer.hashCode(this.sampleRateHz)) * 31) + Integer.hashCode(this.maxBufferedMs)) * 31) + this.vocabularyHints.hashCode()) * 31) + Boolean.hashCode(this.privacyLocalRequired);
    }

    public String toString() {
        return "AsrSessionConfig(requestId=" + this.requestId + ", sessionId=" + this.sessionId + ", language=" + this.language + ", sampleRateHz=" + this.sampleRateHz + ", maxBufferedMs=" + this.maxBufferedMs + ", vocabularyHints=" + this.vocabularyHints + ", privacyLocalRequired=" + this.privacyLocalRequired + ")";
    }

    public AsrSessionConfig(String requestId, String sessionId, String language, int sampleRateHz, int maxBufferedMs, List<String> vocabularyHints, boolean privacyLocalRequired) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(vocabularyHints, "vocabularyHints");
        this.requestId = requestId;
        this.sessionId = sessionId;
        this.language = language;
        this.sampleRateHz = sampleRateHz;
        this.maxBufferedMs = maxBufferedMs;
        this.vocabularyHints = vocabularyHints;
        this.privacyLocalRequired = privacyLocalRequired;
    }

    public /* synthetic */ AsrSessionConfig(String str, String str2, String str3, int i, int i2, List list, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i3 & 4) != 0 ? "ru-RU" : str3, (i3 & 8) != 0 ? 16000 : i, (i3 & 16) != 0 ? 30000 : i2, (i3 & 32) != 0 ? CollectionsKt.emptyList() : list, (i3 & 64) != 0 ? false : z);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public final int getMaxBufferedMs() {
        return this.maxBufferedMs;
    }

    public final List<String> getVocabularyHints() {
        return this.vocabularyHints;
    }

    public final boolean getPrivacyLocalRequired() {
        return this.privacyLocalRequired;
    }
}
