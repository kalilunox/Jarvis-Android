package com.jarvis.core.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LatencyStatsStore.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJT\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u000fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0012\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0013\u0010\u000fR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0014\u0010\u000f¨\u0006#"}, d2 = {"Lcom/jarvis/core/runtime/LatencyPercentiles;", "", "count", "", "p50", "", "p90", "p95", "min", "max", "<init>", "(ILjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "getCount", "()I", "getP50", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getP90", "getP95", "getMin", "getMax", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ILjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)Lcom/jarvis/core/runtime/LatencyPercentiles;", "equals", "", "other", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LatencyPercentiles {
    private final int count;
    private final Long max;
    private final Long min;
    private final Long p50;
    private final Long p90;
    private final Long p95;

    public static /* synthetic */ LatencyPercentiles copy$default(LatencyPercentiles latencyPercentiles, int i, Long l, Long l2, Long l3, Long l4, Long l5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = latencyPercentiles.count;
        }
        if ((i2 & 2) != 0) {
            l = latencyPercentiles.p50;
        }
        Long l6 = l;
        if ((i2 & 4) != 0) {
            l2 = latencyPercentiles.p90;
        }
        Long l7 = l2;
        if ((i2 & 8) != 0) {
            l3 = latencyPercentiles.p95;
        }
        Long l8 = l3;
        if ((i2 & 16) != 0) {
            l4 = latencyPercentiles.min;
        }
        Long l9 = l4;
        if ((i2 & 32) != 0) {
            l5 = latencyPercentiles.max;
        }
        return latencyPercentiles.copy(i, l6, l7, l8, l9, l5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Long getP50() {
        return this.p50;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getP90() {
        return this.p90;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getP95() {
        return this.p95;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Long getMin() {
        return this.min;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getMax() {
        return this.max;
    }

    public final LatencyPercentiles copy(int count, Long p50, Long p90, Long p95, Long min, Long max) {
        return new LatencyPercentiles(count, p50, p90, p95, min, max);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LatencyPercentiles)) {
            return false;
        }
        LatencyPercentiles latencyPercentiles = (LatencyPercentiles) other;
        return this.count == latencyPercentiles.count && Intrinsics.areEqual(this.p50, latencyPercentiles.p50) && Intrinsics.areEqual(this.p90, latencyPercentiles.p90) && Intrinsics.areEqual(this.p95, latencyPercentiles.p95) && Intrinsics.areEqual(this.min, latencyPercentiles.min) && Intrinsics.areEqual(this.max, latencyPercentiles.max);
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.count) * 31) + (this.p50 == null ? 0 : this.p50.hashCode())) * 31) + (this.p90 == null ? 0 : this.p90.hashCode())) * 31) + (this.p95 == null ? 0 : this.p95.hashCode())) * 31) + (this.min == null ? 0 : this.min.hashCode())) * 31) + (this.max != null ? this.max.hashCode() : 0);
    }

    public String toString() {
        return "LatencyPercentiles(count=" + this.count + ", p50=" + this.p50 + ", p90=" + this.p90 + ", p95=" + this.p95 + ", min=" + this.min + ", max=" + this.max + ")";
    }

    public LatencyPercentiles(int count, Long p50, Long p90, Long p95, Long min, Long max) {
        this.count = count;
        this.p50 = p50;
        this.p90 = p90;
        this.p95 = p95;
        this.min = min;
        this.max = max;
    }

    public final int getCount() {
        return this.count;
    }

    public final Long getP50() {
        return this.p50;
    }

    public final Long getP90() {
        return this.p90;
    }

    public final Long getP95() {
        return this.p95;
    }

    public final Long getMin() {
        return this.min;
    }

    public final Long getMax() {
        return this.max;
    }
}
