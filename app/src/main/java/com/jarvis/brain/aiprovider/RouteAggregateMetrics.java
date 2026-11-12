package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProviderMetricsStore.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010\u001c\u001a\u00020\nHÆ\u0003JN\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\nHÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/jarvis/brain/aiprovider/RouteAggregateMetrics;", "", "cloudPercent", "", "localPercent", "fallbackPercent", "averageCompletionMs", "", "averageTtftMs", "totalSuccess", "", "<init>", "(FFFLjava/lang/Long;Ljava/lang/Long;I)V", "getCloudPercent", "()F", "getLocalPercent", "getFallbackPercent", "getAverageCompletionMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAverageTtftMs", "getTotalSuccess", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(FFFLjava/lang/Long;Ljava/lang/Long;I)Lcom/jarvis/brain/aiprovider/RouteAggregateMetrics;", "equals", "", "other", "hashCode", "toString", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RouteAggregateMetrics {
    private final Long averageCompletionMs;
    private final Long averageTtftMs;
    private final float cloudPercent;
    private final float fallbackPercent;
    private final float localPercent;
    private final int totalSuccess;

    public static /* synthetic */ RouteAggregateMetrics copy$default(RouteAggregateMetrics routeAggregateMetrics, float f, float f2, float f3, Long l, Long l2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f = routeAggregateMetrics.cloudPercent;
        }
        if ((i2 & 2) != 0) {
            f2 = routeAggregateMetrics.localPercent;
        }
        float f4 = f2;
        if ((i2 & 4) != 0) {
            f3 = routeAggregateMetrics.fallbackPercent;
        }
        float f5 = f3;
        if ((i2 & 8) != 0) {
            l = routeAggregateMetrics.averageCompletionMs;
        }
        Long l3 = l;
        if ((i2 & 16) != 0) {
            l2 = routeAggregateMetrics.averageTtftMs;
        }
        Long l4 = l2;
        if ((i2 & 32) != 0) {
            i = routeAggregateMetrics.totalSuccess;
        }
        return routeAggregateMetrics.copy(f, f4, f5, l3, l4, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getCloudPercent() {
        return this.cloudPercent;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getLocalPercent() {
        return this.localPercent;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getFallbackPercent() {
        return this.fallbackPercent;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getAverageCompletionMs() {
        return this.averageCompletionMs;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Long getAverageTtftMs() {
        return this.averageTtftMs;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getTotalSuccess() {
        return this.totalSuccess;
    }

    public final RouteAggregateMetrics copy(float cloudPercent, float localPercent, float fallbackPercent, Long averageCompletionMs, Long averageTtftMs, int totalSuccess) {
        return new RouteAggregateMetrics(cloudPercent, localPercent, fallbackPercent, averageCompletionMs, averageTtftMs, totalSuccess);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RouteAggregateMetrics)) {
            return false;
        }
        RouteAggregateMetrics routeAggregateMetrics = (RouteAggregateMetrics) other;
        return Float.compare(this.cloudPercent, routeAggregateMetrics.cloudPercent) == 0 && Float.compare(this.localPercent, routeAggregateMetrics.localPercent) == 0 && Float.compare(this.fallbackPercent, routeAggregateMetrics.fallbackPercent) == 0 && Intrinsics.areEqual(this.averageCompletionMs, routeAggregateMetrics.averageCompletionMs) && Intrinsics.areEqual(this.averageTtftMs, routeAggregateMetrics.averageTtftMs) && this.totalSuccess == routeAggregateMetrics.totalSuccess;
    }

    public int hashCode() {
        return (((((((((Float.hashCode(this.cloudPercent) * 31) + Float.hashCode(this.localPercent)) * 31) + Float.hashCode(this.fallbackPercent)) * 31) + (this.averageCompletionMs == null ? 0 : this.averageCompletionMs.hashCode())) * 31) + (this.averageTtftMs != null ? this.averageTtftMs.hashCode() : 0)) * 31) + Integer.hashCode(this.totalSuccess);
    }

    public String toString() {
        return "RouteAggregateMetrics(cloudPercent=" + this.cloudPercent + ", localPercent=" + this.localPercent + ", fallbackPercent=" + this.fallbackPercent + ", averageCompletionMs=" + this.averageCompletionMs + ", averageTtftMs=" + this.averageTtftMs + ", totalSuccess=" + this.totalSuccess + ")";
    }

    public RouteAggregateMetrics(float cloudPercent, float localPercent, float fallbackPercent, Long averageCompletionMs, Long averageTtftMs, int totalSuccess) {
        this.cloudPercent = cloudPercent;
        this.localPercent = localPercent;
        this.fallbackPercent = fallbackPercent;
        this.averageCompletionMs = averageCompletionMs;
        this.averageTtftMs = averageTtftMs;
        this.totalSuccess = totalSuccess;
    }

    public final float getCloudPercent() {
        return this.cloudPercent;
    }

    public final float getLocalPercent() {
        return this.localPercent;
    }

    public final float getFallbackPercent() {
        return this.fallbackPercent;
    }

    public final Long getAverageCompletionMs() {
        return this.averageCompletionMs;
    }

    public final Long getAverageTtftMs() {
        return this.averageTtftMs;
    }

    public final int getTotalSuccess() {
        return this.totalSuccess;
    }
}
