package com.jarvis.core.voice;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsrContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B§\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0006HÆ\u0003J\t\u00105\u001a\u00020\bHÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u000eHÆ\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010<\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010=\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010>\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010(J\t\u0010?\u001a\u00020\u0006HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0018HÆ\u0003Jº\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010CJ\u0013\u0010D\u001a\u00020\u00062\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010F\u001a\u00020GHÖ\u0001J\t\u0010H\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u001eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010)\u001a\u0004\b'\u0010(R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010)\u001a\u0004\b*\u0010(R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010)\u001a\u0004\b+\u0010(R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010)\u001a\u0004\b,\u0010(R\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001eR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b0\u00101¨\u0006I"}, d2 = {"Lcom/jarvis/core/voice/AsrResult;", "", "requestId", "", VoiceService.EXTRA_TEXT, "isFinal", "", "confidence", "Lcom/jarvis/core/voice/AsrConfidence;", "language", "provider", "model", "sessionId", TypedValues.CycleType.S_WAVE_PHASE, "Lcom/jarvis/core/voice/AsrSessionPhase;", "setupMs", "", "firstPartialMs", "finalizeMs", "totalMs", "fallback", "fallbackReason", "Lcom/jarvis/core/voice/AsrRouteReason;", "errorKind", "Lcom/jarvis/core/voice/AsrErrorKind;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLcom/jarvis/core/voice/AsrConfidence;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/voice/AsrSessionPhase;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;ZLcom/jarvis/core/voice/AsrRouteReason;Lcom/jarvis/core/voice/AsrErrorKind;)V", "getRequestId", "()Ljava/lang/String;", "getText", "()Z", "getConfidence", "()Lcom/jarvis/core/voice/AsrConfidence;", "getLanguage", "getProvider", "getModel", "getSessionId", "getPhase", "()Lcom/jarvis/core/voice/AsrSessionPhase;", "getSetupMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFirstPartialMs", "getFinalizeMs", "getTotalMs", "getFallback", "getFallbackReason", "()Lcom/jarvis/core/voice/AsrRouteReason;", "getErrorKind", "()Lcom/jarvis/core/voice/AsrErrorKind;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLcom/jarvis/core/voice/AsrConfidence;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/voice/AsrSessionPhase;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;ZLcom/jarvis/core/voice/AsrRouteReason;Lcom/jarvis/core/voice/AsrErrorKind;)Lcom/jarvis/core/voice/AsrResult;", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AsrResult {
    private final AsrConfidence confidence;
    private final AsrErrorKind errorKind;
    private final boolean fallback;
    private final AsrRouteReason fallbackReason;
    private final Long finalizeMs;
    private final Long firstPartialMs;
    private final boolean isFinal;
    private final String language;
    private final String model;
    private final AsrSessionPhase phase;
    private final String provider;
    private final String requestId;
    private final String sessionId;
    private final Long setupMs;
    private final String text;
    private final Long totalMs;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Long getSetupMs() {
        return this.setupMs;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Long getFirstPartialMs() {
        return this.firstPartialMs;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Long getFinalizeMs() {
        return this.finalizeMs;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Long getTotalMs() {
        return this.totalMs;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final boolean getFallback() {
        return this.fallback;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final AsrRouteReason getFallbackReason() {
        return this.fallbackReason;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final AsrErrorKind getErrorKind() {
        return this.errorKind;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsFinal() {
        return this.isFinal;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AsrConfidence getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final AsrSessionPhase getPhase() {
        return this.phase;
    }

    public final AsrResult copy(String requestId, String text, boolean isFinal, AsrConfidence confidence, String language, String provider, String model, String sessionId, AsrSessionPhase phase, Long setupMs, Long firstPartialMs, Long finalizeMs, Long totalMs, boolean fallback, AsrRouteReason fallbackReason, AsrErrorKind errorKind) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(confidence, "confidence");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(phase, "phase");
        return new AsrResult(requestId, text, isFinal, confidence, language, provider, model, sessionId, phase, setupMs, firstPartialMs, finalizeMs, totalMs, fallback, fallbackReason, errorKind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrResult)) {
            return false;
        }
        AsrResult asrResult = (AsrResult) other;
        return Intrinsics.areEqual(this.requestId, asrResult.requestId) && Intrinsics.areEqual(this.text, asrResult.text) && this.isFinal == asrResult.isFinal && Intrinsics.areEqual(this.confidence, asrResult.confidence) && Intrinsics.areEqual(this.language, asrResult.language) && Intrinsics.areEqual(this.provider, asrResult.provider) && Intrinsics.areEqual(this.model, asrResult.model) && Intrinsics.areEqual(this.sessionId, asrResult.sessionId) && this.phase == asrResult.phase && Intrinsics.areEqual(this.setupMs, asrResult.setupMs) && Intrinsics.areEqual(this.firstPartialMs, asrResult.firstPartialMs) && Intrinsics.areEqual(this.finalizeMs, asrResult.finalizeMs) && Intrinsics.areEqual(this.totalMs, asrResult.totalMs) && this.fallback == asrResult.fallback && this.fallbackReason == asrResult.fallbackReason && this.errorKind == asrResult.errorKind;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.requestId.hashCode() * 31) + this.text.hashCode()) * 31) + Boolean.hashCode(this.isFinal)) * 31) + this.confidence.hashCode()) * 31) + this.language.hashCode()) * 31) + this.provider.hashCode()) * 31) + this.model.hashCode()) * 31) + this.sessionId.hashCode()) * 31) + this.phase.hashCode()) * 31) + (this.setupMs == null ? 0 : this.setupMs.hashCode())) * 31) + (this.firstPartialMs == null ? 0 : this.firstPartialMs.hashCode())) * 31) + (this.finalizeMs == null ? 0 : this.finalizeMs.hashCode())) * 31) + (this.totalMs == null ? 0 : this.totalMs.hashCode())) * 31) + Boolean.hashCode(this.fallback)) * 31) + (this.fallbackReason == null ? 0 : this.fallbackReason.hashCode())) * 31) + (this.errorKind != null ? this.errorKind.hashCode() : 0);
    }

    public String toString() {
        return "AsrResult(requestId=" + this.requestId + ", text=" + this.text + ", isFinal=" + this.isFinal + ", confidence=" + this.confidence + ", language=" + this.language + ", provider=" + this.provider + ", model=" + this.model + ", sessionId=" + this.sessionId + ", phase=" + this.phase + ", setupMs=" + this.setupMs + ", firstPartialMs=" + this.firstPartialMs + ", finalizeMs=" + this.finalizeMs + ", totalMs=" + this.totalMs + ", fallback=" + this.fallback + ", fallbackReason=" + this.fallbackReason + ", errorKind=" + this.errorKind + ")";
    }

    public AsrResult(String requestId, String text, boolean isFinal, AsrConfidence confidence, String language, String provider, String model, String sessionId, AsrSessionPhase phase, Long setupMs, Long firstPartialMs, Long finalizeMs, Long totalMs, boolean fallback, AsrRouteReason fallbackReason, AsrErrorKind errorKind) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(confidence, "confidence");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(phase, "phase");
        this.requestId = requestId;
        this.text = text;
        this.isFinal = isFinal;
        this.confidence = confidence;
        this.language = language;
        this.provider = provider;
        this.model = model;
        this.sessionId = sessionId;
        this.phase = phase;
        this.setupMs = setupMs;
        this.firstPartialMs = firstPartialMs;
        this.finalizeMs = finalizeMs;
        this.totalMs = totalMs;
        this.fallback = fallback;
        this.fallbackReason = fallbackReason;
        this.errorKind = errorKind;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AsrResult(String str, String str2, boolean z, AsrConfidence asrConfidence, String str3, String str4, String str5, String str6, AsrSessionPhase asrSessionPhase, Long l, Long l2, Long l3, Long l4, boolean z2, AsrRouteReason asrRouteReason, AsrErrorKind asrErrorKind, int i, DefaultConstructorMarker defaultConstructorMarker) {
        AsrSessionPhase asrSessionPhase2;
        AsrConfidence asrConfidence2 = (i & 8) != 0 ? AsrConfidence.Unknown.INSTANCE : asrConfidence;
        String str7 = (i & 16) != 0 ? "ru" : str3;
        if ((i & 256) != 0) {
            asrSessionPhase2 = z ? AsrSessionPhase.FINAL : AsrSessionPhase.PARTIAL;
        } else {
            asrSessionPhase2 = asrSessionPhase;
        }
        this(str, str2, z, asrConfidence2, str7, str4, str5, str6, asrSessionPhase2, (i & 512) != 0 ? null : l, (i & 1024) != 0 ? null : l2, (i & 2048) != 0 ? null : l3, (i & 4096) != 0 ? null : l4, (i & 8192) != 0 ? false : z2, (i & 16384) != 0 ? null : asrRouteReason, (i & 32768) != 0 ? null : asrErrorKind);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getText() {
        return this.text;
    }

    public final boolean isFinal() {
        return this.isFinal;
    }

    public final AsrConfidence getConfidence() {
        return this.confidence;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final AsrSessionPhase getPhase() {
        return this.phase;
    }

    public final Long getSetupMs() {
        return this.setupMs;
    }

    public final Long getFirstPartialMs() {
        return this.firstPartialMs;
    }

    public final Long getFinalizeMs() {
        return this.finalizeMs;
    }

    public final Long getTotalMs() {
        return this.totalMs;
    }

    public final boolean getFallback() {
        return this.fallback;
    }

    public final AsrRouteReason getFallbackReason() {
        return this.fallbackReason;
    }

    public final AsrErrorKind getErrorKind() {
        return this.errorKind;
    }
}
