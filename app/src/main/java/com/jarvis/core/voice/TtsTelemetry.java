package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\bI\b\u0086\b\u0018\u00002\u00020\u0001BÍ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010E\u001a\u00020\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010K\u001a\u00020\tHÆ\u0003J\t\u0010L\u001a\u00020\tHÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010N\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010O\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010P\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010Q\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010R\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010S\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010T\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0002\u0010=J\t\u0010U\u001a\u00020\tHÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÔ\u0001\u0010W\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010XJ\u0013\u0010Y\u001a\u00020\t2\b\u0010Z\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010[\u001a\u00020\u0014HÖ\u0001J\t\u0010\\\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010\u001cR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001a\"\u0004\b,\u0010\u001cR\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u00101\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u00101\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u00101\u001a\u0004\b4\u0010.\"\u0004\b5\u00100R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u00101\u001a\u0004\b6\u0010.\"\u0004\b7\u00100R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u00101\u001a\u0004\b8\u0010.\"\u0004\b9\u00100R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u00101\u001a\u0004\b:\u0010.\"\u0004\b;\u00100R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u0010\n\u0002\u0010@\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010\u0015\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010&\"\u0004\bB\u0010(R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001a\"\u0004\bD\u0010\u001c¨\u0006]"}, d2 = {"Lcom/jarvis/core/voice/TtsTelemetry;", "", "ttsProvider", "", "ttsModel", "ttsVoice", "ttsRoute", "ttsRouteReason", "ttsFallback", "", "ttsStreaming", "ttsSessionId", "ttsSetupMs", "", "ttsFirstAudioMs", "ttsFirstPlaybackMs", "ttsSynthesisMs", "ttsPlaybackMs", "ttsTotalMs", "ttsQueueDepth", "", "ttsCancelled", "ttsErrorCategory", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;ZLjava/lang/String;)V", "getTtsProvider", "()Ljava/lang/String;", "setTtsProvider", "(Ljava/lang/String;)V", "getTtsModel", "setTtsModel", "getTtsVoice", "setTtsVoice", "getTtsRoute", "setTtsRoute", "getTtsRouteReason", "setTtsRouteReason", "getTtsFallback", "()Z", "setTtsFallback", "(Z)V", "getTtsStreaming", "setTtsStreaming", "getTtsSessionId", "setTtsSessionId", "getTtsSetupMs", "()Ljava/lang/Long;", "setTtsSetupMs", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getTtsFirstAudioMs", "setTtsFirstAudioMs", "getTtsFirstPlaybackMs", "setTtsFirstPlaybackMs", "getTtsSynthesisMs", "setTtsSynthesisMs", "getTtsPlaybackMs", "setTtsPlaybackMs", "getTtsTotalMs", "setTtsTotalMs", "getTtsQueueDepth", "()Ljava/lang/Integer;", "setTtsQueueDepth", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTtsCancelled", "setTtsCancelled", "getTtsErrorCategory", "setTtsErrorCategory", "summary", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;ZLjava/lang/String;)Lcom/jarvis/core/voice/TtsTelemetry;", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TtsTelemetry {
    private boolean ttsCancelled;
    private String ttsErrorCategory;
    private boolean ttsFallback;
    private Long ttsFirstAudioMs;
    private Long ttsFirstPlaybackMs;
    private String ttsModel;
    private Long ttsPlaybackMs;
    private String ttsProvider;
    private Integer ttsQueueDepth;
    private String ttsRoute;
    private String ttsRouteReason;
    private String ttsSessionId;
    private Long ttsSetupMs;
    private boolean ttsStreaming;
    private Long ttsSynthesisMs;
    private Long ttsTotalMs;
    private String ttsVoice;

    public TtsTelemetry() {
        this(null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, null, 131071, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTtsProvider() {
        return this.ttsProvider;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Long getTtsFirstAudioMs() {
        return this.ttsFirstAudioMs;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Long getTtsFirstPlaybackMs() {
        return this.ttsFirstPlaybackMs;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Long getTtsSynthesisMs() {
        return this.ttsSynthesisMs;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Long getTtsPlaybackMs() {
        return this.ttsPlaybackMs;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final Long getTtsTotalMs() {
        return this.ttsTotalMs;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final Integer getTtsQueueDepth() {
        return this.ttsQueueDepth;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getTtsCancelled() {
        return this.ttsCancelled;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getTtsErrorCategory() {
        return this.ttsErrorCategory;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTtsModel() {
        return this.ttsModel;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTtsVoice() {
        return this.ttsVoice;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTtsRoute() {
        return this.ttsRoute;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getTtsRouteReason() {
        return this.ttsRouteReason;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getTtsFallback() {
        return this.ttsFallback;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getTtsStreaming() {
        return this.ttsStreaming;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getTtsSessionId() {
        return this.ttsSessionId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Long getTtsSetupMs() {
        return this.ttsSetupMs;
    }

    public final TtsTelemetry copy(String ttsProvider, String ttsModel, String ttsVoice, String ttsRoute, String ttsRouteReason, boolean ttsFallback, boolean ttsStreaming, String ttsSessionId, Long ttsSetupMs, Long ttsFirstAudioMs, Long ttsFirstPlaybackMs, Long ttsSynthesisMs, Long ttsPlaybackMs, Long ttsTotalMs, Integer ttsQueueDepth, boolean ttsCancelled, String ttsErrorCategory) {
        return new TtsTelemetry(ttsProvider, ttsModel, ttsVoice, ttsRoute, ttsRouteReason, ttsFallback, ttsStreaming, ttsSessionId, ttsSetupMs, ttsFirstAudioMs, ttsFirstPlaybackMs, ttsSynthesisMs, ttsPlaybackMs, ttsTotalMs, ttsQueueDepth, ttsCancelled, ttsErrorCategory);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsTelemetry)) {
            return false;
        }
        TtsTelemetry ttsTelemetry = (TtsTelemetry) other;
        return Intrinsics.areEqual(this.ttsProvider, ttsTelemetry.ttsProvider) && Intrinsics.areEqual(this.ttsModel, ttsTelemetry.ttsModel) && Intrinsics.areEqual(this.ttsVoice, ttsTelemetry.ttsVoice) && Intrinsics.areEqual(this.ttsRoute, ttsTelemetry.ttsRoute) && Intrinsics.areEqual(this.ttsRouteReason, ttsTelemetry.ttsRouteReason) && this.ttsFallback == ttsTelemetry.ttsFallback && this.ttsStreaming == ttsTelemetry.ttsStreaming && Intrinsics.areEqual(this.ttsSessionId, ttsTelemetry.ttsSessionId) && Intrinsics.areEqual(this.ttsSetupMs, ttsTelemetry.ttsSetupMs) && Intrinsics.areEqual(this.ttsFirstAudioMs, ttsTelemetry.ttsFirstAudioMs) && Intrinsics.areEqual(this.ttsFirstPlaybackMs, ttsTelemetry.ttsFirstPlaybackMs) && Intrinsics.areEqual(this.ttsSynthesisMs, ttsTelemetry.ttsSynthesisMs) && Intrinsics.areEqual(this.ttsPlaybackMs, ttsTelemetry.ttsPlaybackMs) && Intrinsics.areEqual(this.ttsTotalMs, ttsTelemetry.ttsTotalMs) && Intrinsics.areEqual(this.ttsQueueDepth, ttsTelemetry.ttsQueueDepth) && this.ttsCancelled == ttsTelemetry.ttsCancelled && Intrinsics.areEqual(this.ttsErrorCategory, ttsTelemetry.ttsErrorCategory);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((this.ttsProvider == null ? 0 : this.ttsProvider.hashCode()) * 31) + (this.ttsModel == null ? 0 : this.ttsModel.hashCode())) * 31) + (this.ttsVoice == null ? 0 : this.ttsVoice.hashCode())) * 31) + (this.ttsRoute == null ? 0 : this.ttsRoute.hashCode())) * 31) + (this.ttsRouteReason == null ? 0 : this.ttsRouteReason.hashCode())) * 31) + Boolean.hashCode(this.ttsFallback)) * 31) + Boolean.hashCode(this.ttsStreaming)) * 31) + (this.ttsSessionId == null ? 0 : this.ttsSessionId.hashCode())) * 31) + (this.ttsSetupMs == null ? 0 : this.ttsSetupMs.hashCode())) * 31) + (this.ttsFirstAudioMs == null ? 0 : this.ttsFirstAudioMs.hashCode())) * 31) + (this.ttsFirstPlaybackMs == null ? 0 : this.ttsFirstPlaybackMs.hashCode())) * 31) + (this.ttsSynthesisMs == null ? 0 : this.ttsSynthesisMs.hashCode())) * 31) + (this.ttsPlaybackMs == null ? 0 : this.ttsPlaybackMs.hashCode())) * 31) + (this.ttsTotalMs == null ? 0 : this.ttsTotalMs.hashCode())) * 31) + (this.ttsQueueDepth == null ? 0 : this.ttsQueueDepth.hashCode())) * 31) + Boolean.hashCode(this.ttsCancelled)) * 31) + (this.ttsErrorCategory != null ? this.ttsErrorCategory.hashCode() : 0);
    }

    public String toString() {
        return "TtsTelemetry(ttsProvider=" + this.ttsProvider + ", ttsModel=" + this.ttsModel + ", ttsVoice=" + this.ttsVoice + ", ttsRoute=" + this.ttsRoute + ", ttsRouteReason=" + this.ttsRouteReason + ", ttsFallback=" + this.ttsFallback + ", ttsStreaming=" + this.ttsStreaming + ", ttsSessionId=" + this.ttsSessionId + ", ttsSetupMs=" + this.ttsSetupMs + ", ttsFirstAudioMs=" + this.ttsFirstAudioMs + ", ttsFirstPlaybackMs=" + this.ttsFirstPlaybackMs + ", ttsSynthesisMs=" + this.ttsSynthesisMs + ", ttsPlaybackMs=" + this.ttsPlaybackMs + ", ttsTotalMs=" + this.ttsTotalMs + ", ttsQueueDepth=" + this.ttsQueueDepth + ", ttsCancelled=" + this.ttsCancelled + ", ttsErrorCategory=" + this.ttsErrorCategory + ")";
    }

    public TtsTelemetry(String ttsProvider, String ttsModel, String ttsVoice, String ttsRoute, String ttsRouteReason, boolean ttsFallback, boolean ttsStreaming, String ttsSessionId, Long ttsSetupMs, Long ttsFirstAudioMs, Long ttsFirstPlaybackMs, Long ttsSynthesisMs, Long ttsPlaybackMs, Long ttsTotalMs, Integer ttsQueueDepth, boolean ttsCancelled, String ttsErrorCategory) {
        this.ttsProvider = ttsProvider;
        this.ttsModel = ttsModel;
        this.ttsVoice = ttsVoice;
        this.ttsRoute = ttsRoute;
        this.ttsRouteReason = ttsRouteReason;
        this.ttsFallback = ttsFallback;
        this.ttsStreaming = ttsStreaming;
        this.ttsSessionId = ttsSessionId;
        this.ttsSetupMs = ttsSetupMs;
        this.ttsFirstAudioMs = ttsFirstAudioMs;
        this.ttsFirstPlaybackMs = ttsFirstPlaybackMs;
        this.ttsSynthesisMs = ttsSynthesisMs;
        this.ttsPlaybackMs = ttsPlaybackMs;
        this.ttsTotalMs = ttsTotalMs;
        this.ttsQueueDepth = ttsQueueDepth;
        this.ttsCancelled = ttsCancelled;
        this.ttsErrorCategory = ttsErrorCategory;
    }

    public /* synthetic */ TtsTelemetry(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, String str6, Long l, Long l2, Long l3, Long l4, Long l5, Long l6, Integer num, boolean z3, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? false : z, (i & 64) != 0 ? false : z2, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : l, (i & 512) != 0 ? null : l2, (i & 1024) != 0 ? null : l3, (i & 2048) != 0 ? null : l4, (i & 4096) != 0 ? null : l5, (i & 8192) != 0 ? null : l6, (i & 16384) != 0 ? null : num, (i & 32768) != 0 ? false : z3, (i & 65536) != 0 ? null : str7);
    }

    public final String getTtsProvider() {
        return this.ttsProvider;
    }

    public final void setTtsProvider(String str) {
        this.ttsProvider = str;
    }

    public final String getTtsModel() {
        return this.ttsModel;
    }

    public final void setTtsModel(String str) {
        this.ttsModel = str;
    }

    public final String getTtsVoice() {
        return this.ttsVoice;
    }

    public final void setTtsVoice(String str) {
        this.ttsVoice = str;
    }

    public final String getTtsRoute() {
        return this.ttsRoute;
    }

    public final void setTtsRoute(String str) {
        this.ttsRoute = str;
    }

    public final String getTtsRouteReason() {
        return this.ttsRouteReason;
    }

    public final void setTtsRouteReason(String str) {
        this.ttsRouteReason = str;
    }

    public final boolean getTtsFallback() {
        return this.ttsFallback;
    }

    public final void setTtsFallback(boolean z) {
        this.ttsFallback = z;
    }

    public final boolean getTtsStreaming() {
        return this.ttsStreaming;
    }

    public final void setTtsStreaming(boolean z) {
        this.ttsStreaming = z;
    }

    public final String getTtsSessionId() {
        return this.ttsSessionId;
    }

    public final void setTtsSessionId(String str) {
        this.ttsSessionId = str;
    }

    public final Long getTtsSetupMs() {
        return this.ttsSetupMs;
    }

    public final void setTtsSetupMs(Long l) {
        this.ttsSetupMs = l;
    }

    public final Long getTtsFirstAudioMs() {
        return this.ttsFirstAudioMs;
    }

    public final void setTtsFirstAudioMs(Long l) {
        this.ttsFirstAudioMs = l;
    }

    public final Long getTtsFirstPlaybackMs() {
        return this.ttsFirstPlaybackMs;
    }

    public final void setTtsFirstPlaybackMs(Long l) {
        this.ttsFirstPlaybackMs = l;
    }

    public final Long getTtsSynthesisMs() {
        return this.ttsSynthesisMs;
    }

    public final void setTtsSynthesisMs(Long l) {
        this.ttsSynthesisMs = l;
    }

    public final Long getTtsPlaybackMs() {
        return this.ttsPlaybackMs;
    }

    public final void setTtsPlaybackMs(Long l) {
        this.ttsPlaybackMs = l;
    }

    public final Long getTtsTotalMs() {
        return this.ttsTotalMs;
    }

    public final void setTtsTotalMs(Long l) {
        this.ttsTotalMs = l;
    }

    public final Integer getTtsQueueDepth() {
        return this.ttsQueueDepth;
    }

    public final void setTtsQueueDepth(Integer num) {
        this.ttsQueueDepth = num;
    }

    public final boolean getTtsCancelled() {
        return this.ttsCancelled;
    }

    public final void setTtsCancelled(boolean z) {
        this.ttsCancelled = z;
    }

    public final String getTtsErrorCategory() {
        return this.ttsErrorCategory;
    }

    public final void setTtsErrorCategory(String str) {
        this.ttsErrorCategory = str;
    }

    public final String summary() {
        String str = this.ttsProvider;
        if (str == null) {
            str = "-";
        }
        String str2 = this.ttsModel;
        if (str2 == null) {
            str2 = "-";
        }
        String str3 = this.ttsVoice;
        if (str3 == null) {
            str3 = "-";
        }
        String str4 = this.ttsRoute;
        if (str4 == null) {
            str4 = "-";
        }
        String str5 = this.ttsRouteReason;
        if (str5 == null) {
            str5 = "-";
        }
        boolean z = this.ttsFallback;
        boolean z2 = this.ttsStreaming;
        Object obj = this.ttsSetupMs;
        if (obj == null) {
            obj = "-";
        }
        Object obj2 = this.ttsFirstAudioMs;
        if (obj2 == null) {
            obj2 = "-";
        }
        Object obj3 = this.ttsFirstPlaybackMs;
        if (obj3 == null) {
            obj3 = "-";
        }
        Object obj4 = this.ttsSynthesisMs;
        if (obj4 == null) {
            obj4 = "-";
        }
        Object obj5 = this.ttsTotalMs;
        if (obj5 == null) {
            obj5 = "-";
        }
        Object obj6 = this.ttsQueueDepth;
        if (obj6 == null) {
            obj6 = "-";
        }
        boolean z3 = this.ttsCancelled;
        String str6 = this.ttsErrorCategory;
        if (str6 == null) {
            str6 = "-";
        }
        return "tts provider=" + str + " model=" + str2 + " voice=" + str3 + " route=" + str4 + " reason=" + str5 + " fb=" + z + " stream=" + z2 + " setup=" + obj + " firstAudio=" + obj2 + " playback=" + obj3 + " synth=" + obj4 + " total=" + obj5 + " q=" + obj6 + " cancel=" + z3 + " err=" + str6;
    }
}
