package com.jarvis.core.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LatencyStatsStore.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b,\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010+\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010,\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010-\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010.\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0019J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u000eHÆ\u0003J\t\u00102\u001a\u00020\u000eHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0006HÆ\u0003J¨\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u00107J\u0013\u00108\u001a\u00020\u000e2\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020;HÖ\u0001J\t\u0010<\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001c\u0010\u0019R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001d\u0010\u0019R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006="}, d2 = {"Lcom/jarvis/core/runtime/LatencySample;", "", "requestId", "", "route", "e2eMs", "", "firstAudioMs", "firstUsefulMs", "aiCompletionMs", "realTtftMs", "ttftKind", "warmState", "coldStart", "", "cancelled", "provider", "model", "atMillis", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;J)V", "getRequestId", "()Ljava/lang/String;", "getRoute", "getE2eMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFirstAudioMs", "getFirstUsefulMs", "getAiCompletionMs", "getRealTtftMs", "getTtftKind", "getWarmState", "getColdStart", "()Z", "getCancelled", "getProvider", "getModel", "getAtMillis", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;J)Lcom/jarvis/core/runtime/LatencySample;", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LatencySample {
    private final Long aiCompletionMs;
    private final long atMillis;
    private final boolean cancelled;
    private final boolean coldStart;
    private final Long e2eMs;
    private final Long firstAudioMs;
    private final Long firstUsefulMs;
    private final String model;
    private final String provider;
    private final Long realTtftMs;
    private final String requestId;
    private final String route;
    private final String ttftKind;
    private final String warmState;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getColdStart() {
        return this.coldStart;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getCancelled() {
        return this.cancelled;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getAtMillis() {
        return this.atMillis;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRoute() {
        return this.route;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getE2eMs() {
        return this.e2eMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getFirstAudioMs() {
        return this.firstAudioMs;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Long getFirstUsefulMs() {
        return this.firstUsefulMs;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getAiCompletionMs() {
        return this.aiCompletionMs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getRealTtftMs() {
        return this.realTtftMs;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getTtftKind() {
        return this.ttftKind;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getWarmState() {
        return this.warmState;
    }

    public final LatencySample copy(String requestId, String route, Long e2eMs, Long firstAudioMs, Long firstUsefulMs, Long aiCompletionMs, Long realTtftMs, String ttftKind, String warmState, boolean coldStart, boolean cancelled, String provider, String model, long atMillis) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        Intrinsics.checkNotNullParameter(warmState, "warmState");
        return new LatencySample(requestId, route, e2eMs, firstAudioMs, firstUsefulMs, aiCompletionMs, realTtftMs, ttftKind, warmState, coldStart, cancelled, provider, model, atMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LatencySample)) {
            return false;
        }
        LatencySample latencySample = (LatencySample) other;
        return Intrinsics.areEqual(this.requestId, latencySample.requestId) && Intrinsics.areEqual(this.route, latencySample.route) && Intrinsics.areEqual(this.e2eMs, latencySample.e2eMs) && Intrinsics.areEqual(this.firstAudioMs, latencySample.firstAudioMs) && Intrinsics.areEqual(this.firstUsefulMs, latencySample.firstUsefulMs) && Intrinsics.areEqual(this.aiCompletionMs, latencySample.aiCompletionMs) && Intrinsics.areEqual(this.realTtftMs, latencySample.realTtftMs) && Intrinsics.areEqual(this.ttftKind, latencySample.ttftKind) && Intrinsics.areEqual(this.warmState, latencySample.warmState) && this.coldStart == latencySample.coldStart && this.cancelled == latencySample.cancelled && Intrinsics.areEqual(this.provider, latencySample.provider) && Intrinsics.areEqual(this.model, latencySample.model) && this.atMillis == latencySample.atMillis;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.requestId.hashCode() * 31) + this.route.hashCode()) * 31) + (this.e2eMs == null ? 0 : this.e2eMs.hashCode())) * 31) + (this.firstAudioMs == null ? 0 : this.firstAudioMs.hashCode())) * 31) + (this.firstUsefulMs == null ? 0 : this.firstUsefulMs.hashCode())) * 31) + (this.aiCompletionMs == null ? 0 : this.aiCompletionMs.hashCode())) * 31) + (this.realTtftMs == null ? 0 : this.realTtftMs.hashCode())) * 31) + this.ttftKind.hashCode()) * 31) + this.warmState.hashCode()) * 31) + Boolean.hashCode(this.coldStart)) * 31) + Boolean.hashCode(this.cancelled)) * 31) + (this.provider == null ? 0 : this.provider.hashCode())) * 31) + (this.model != null ? this.model.hashCode() : 0)) * 31) + Long.hashCode(this.atMillis);
    }

    public String toString() {
        return "LatencySample(requestId=" + this.requestId + ", route=" + this.route + ", e2eMs=" + this.e2eMs + ", firstAudioMs=" + this.firstAudioMs + ", firstUsefulMs=" + this.firstUsefulMs + ", aiCompletionMs=" + this.aiCompletionMs + ", realTtftMs=" + this.realTtftMs + ", ttftKind=" + this.ttftKind + ", warmState=" + this.warmState + ", coldStart=" + this.coldStart + ", cancelled=" + this.cancelled + ", provider=" + this.provider + ", model=" + this.model + ", atMillis=" + this.atMillis + ")";
    }

    public LatencySample(String requestId, String route, Long e2eMs, Long firstAudioMs, Long firstUsefulMs, Long aiCompletionMs, Long realTtftMs, String ttftKind, String warmState, boolean coldStart, boolean cancelled, String provider, String model, long atMillis) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        Intrinsics.checkNotNullParameter(warmState, "warmState");
        this.requestId = requestId;
        this.route = route;
        this.e2eMs = e2eMs;
        this.firstAudioMs = firstAudioMs;
        this.firstUsefulMs = firstUsefulMs;
        this.aiCompletionMs = aiCompletionMs;
        this.realTtftMs = realTtftMs;
        this.ttftKind = ttftKind;
        this.warmState = warmState;
        this.coldStart = coldStart;
        this.cancelled = cancelled;
        this.provider = provider;
        this.model = model;
        this.atMillis = atMillis;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ LatencySample(String str, String str2, Long l, Long l2, Long l3, Long l4, Long l5, String str3, String str4, boolean z, boolean z2, String str5, String str6, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        long jCurrentTimeMillis;
        if ((i & 8192) == 0) {
            jCurrentTimeMillis = j;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        this(str, str2, l, l2, l3, l4, l5, str3, str4, z, z2, str5, str6, jCurrentTimeMillis);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getRoute() {
        return this.route;
    }

    public final Long getE2eMs() {
        return this.e2eMs;
    }

    public final Long getFirstAudioMs() {
        return this.firstAudioMs;
    }

    public final Long getFirstUsefulMs() {
        return this.firstUsefulMs;
    }

    public final Long getAiCompletionMs() {
        return this.aiCompletionMs;
    }

    public final Long getRealTtftMs() {
        return this.realTtftMs;
    }

    public final String getTtftKind() {
        return this.ttftKind;
    }

    public final String getWarmState() {
        return this.warmState;
    }

    public final boolean getColdStart() {
        return this.coldStart;
    }

    public final boolean getCancelled() {
        return this.cancelled;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getModel() {
        return this.model;
    }

    public final long getAtMillis() {
        return this.atMillis;
    }
}
