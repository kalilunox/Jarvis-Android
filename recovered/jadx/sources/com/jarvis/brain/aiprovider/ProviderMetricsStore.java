package com.jarvis.brain.aiprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ProviderMetricsStore.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001#B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J?\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\bJ\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0002\u0010\u001cJ\u000e\u0010 \u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\bJ\u0006\u0010!\u001a\u00020\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/jarvis/brain/aiprovider/ProviderMetricsStore;", "", "windowSize", "", "<init>", "(I)V", "byProvider", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/brain/aiprovider/ProviderMetricsStore$Bucket;", "Lkotlin/collections/LinkedHashMap;", "cloudOk", "localOk", "fallbackOk", "totalOk", "record", "", "providerId", "kind", "Lcom/jarvis/brain/aiprovider/AIProviderKind;", "latencyMs", "", "success", "", "fallbackUsed", "ttftMs", "(Ljava/lang/String;Lcom/jarvis/brain/aiprovider/AIProviderKind;JZZLjava/lang/Long;)V", "averageLatencyMs", "(Ljava/lang/String;)Ljava/lang/Long;", "failureRate", "", "averageTtftMs", "speedScore", "aggregate", "Lcom/jarvis/brain/aiprovider/RouteAggregateMetrics;", "Bucket", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProviderMetricsStore {
    private final LinkedHashMap<String, Bucket> byProvider;
    private int cloudOk;
    private int fallbackOk;
    private int localOk;
    private int totalOk;
    private final int windowSize;

    public ProviderMetricsStore() {
        this(0, 1, null);
    }

    public ProviderMetricsStore(int windowSize) {
        this.windowSize = windowSize;
        this.byProvider = new LinkedHashMap<>();
    }

    public /* synthetic */ ProviderMetricsStore(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 16 : i);
    }

    /* JADX INFO: compiled from: ProviderMetricsStore.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003JA\u0010\u001f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0006HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011¨\u0006&"}, d2 = {"Lcom/jarvis/brain/aiprovider/ProviderMetricsStore$Bucket;", "", "latencies", "Lkotlin/collections/ArrayDeque;", "", "success", "", "failure", "ttftSum", "ttftCount", "<init>", "(Lkotlin/collections/ArrayDeque;IIJI)V", "getLatencies", "()Lkotlin/collections/ArrayDeque;", "getSuccess", "()I", "setSuccess", "(I)V", "getFailure", "setFailure", "getTtftSum", "()J", "setTtftSum", "(J)V", "getTtftCount", "setTtftCount", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Bucket {
        private int failure;
        private final ArrayDeque<Long> latencies;
        private int success;
        private int ttftCount;
        private long ttftSum;

        public Bucket() {
            this(null, 0, 0, 0L, 0, 31, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Bucket copy$default(Bucket bucket, ArrayDeque arrayDeque, int i, int i2, long j, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                arrayDeque = bucket.latencies;
            }
            if ((i4 & 2) != 0) {
                i = bucket.success;
            }
            int i5 = i;
            if ((i4 & 4) != 0) {
                i2 = bucket.failure;
            }
            int i6 = i2;
            if ((i4 & 8) != 0) {
                j = bucket.ttftSum;
            }
            long j2 = j;
            if ((i4 & 16) != 0) {
                i3 = bucket.ttftCount;
            }
            return bucket.copy(arrayDeque, i5, i6, j2, i3);
        }

        public final ArrayDeque<Long> component1() {
            return this.latencies;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getSuccess() {
            return this.success;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getFailure() {
            return this.failure;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getTtftSum() {
            return this.ttftSum;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getTtftCount() {
            return this.ttftCount;
        }

        public final Bucket copy(ArrayDeque<Long> latencies, int success, int failure, long ttftSum, int ttftCount) {
            Intrinsics.checkNotNullParameter(latencies, "latencies");
            return new Bucket(latencies, success, failure, ttftSum, ttftCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Bucket)) {
                return false;
            }
            Bucket bucket = (Bucket) other;
            return Intrinsics.areEqual(this.latencies, bucket.latencies) && this.success == bucket.success && this.failure == bucket.failure && this.ttftSum == bucket.ttftSum && this.ttftCount == bucket.ttftCount;
        }

        public int hashCode() {
            return (((((((this.latencies.hashCode() * 31) + Integer.hashCode(this.success)) * 31) + Integer.hashCode(this.failure)) * 31) + Long.hashCode(this.ttftSum)) * 31) + Integer.hashCode(this.ttftCount);
        }

        public String toString() {
            return "Bucket(latencies=" + this.latencies + ", success=" + this.success + ", failure=" + this.failure + ", ttftSum=" + this.ttftSum + ", ttftCount=" + this.ttftCount + ")";
        }

        public Bucket(ArrayDeque<Long> latencies, int success, int failure, long ttftSum, int ttftCount) {
            Intrinsics.checkNotNullParameter(latencies, "latencies");
            this.latencies = latencies;
            this.success = success;
            this.failure = failure;
            this.ttftSum = ttftSum;
            this.ttftCount = ttftCount;
        }

        public /* synthetic */ Bucket(ArrayDeque arrayDeque, int i, int i2, long j, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? new ArrayDeque() : arrayDeque, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0L : j, (i4 & 16) == 0 ? i3 : 0);
        }

        public final ArrayDeque<Long> getLatencies() {
            return this.latencies;
        }

        public final int getSuccess() {
            return this.success;
        }

        public final void setSuccess(int i) {
            this.success = i;
        }

        public final int getFailure() {
            return this.failure;
        }

        public final void setFailure(int i) {
            this.failure = i;
        }

        public final long getTtftSum() {
            return this.ttftSum;
        }

        public final void setTtftSum(long j) {
            this.ttftSum = j;
        }

        public final int getTtftCount() {
            return this.ttftCount;
        }

        public final void setTtftCount(int i) {
            this.ttftCount = i;
        }
    }

    public static /* synthetic */ void record$default(ProviderMetricsStore providerMetricsStore, String str, AIProviderKind aIProviderKind, long j, boolean z, boolean z2, Long l, int i, Object obj) {
        Long l2;
        if ((i & 32) == 0) {
            l2 = l;
        } else {
            l2 = null;
        }
        providerMetricsStore.record(str, aIProviderKind, j, z, z2, l2);
    }

    public final synchronized void record(String providerId, AIProviderKind kind, long latencyMs, boolean success, boolean fallbackUsed, Long ttftMs) {
        Bucket bucket;
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Map $this$getOrPut$iv = this.byProvider;
        Bucket bucket2 = $this$getOrPut$iv.get(providerId);
        if (bucket2 == null) {
            bucket = new Bucket(null, 0, 0, 0L, 0, 31, null);
            $this$getOrPut$iv.put(providerId, bucket);
        } else {
            bucket = bucket2;
        }
        Bucket b = bucket;
        if (success) {
            b.setSuccess(b.getSuccess() + 1);
            this.totalOk++;
            if (fallbackUsed) {
                this.fallbackOk++;
            } else if (kind == AIProviderKind.CLOUD) {
                this.cloudOk++;
            } else {
                this.localOk++;
            }
            if (latencyMs >= 0) {
                b.getLatencies().addLast(Long.valueOf(latencyMs));
                while (b.getLatencies().size() > this.windowSize) {
                    b.getLatencies().removeFirst();
                }
            }
            if (ttftMs != null && ttftMs.longValue() >= 0) {
                b.setTtftSum(b.getTtftSum() + ttftMs.longValue());
                b.setTtftCount(b.getTtftCount() + 1);
            }
        } else {
            b.setFailure(b.getFailure() + 1);
        }
    }

    public final synchronized Long averageLatencyMs(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Bucket b = this.byProvider.get(providerId);
        if (b == null) {
            return null;
        }
        if (b.getLatencies().isEmpty()) {
            return null;
        }
        return Long.valueOf(CollectionsKt.sumOfLong(b.getLatencies()) / ((long) b.getLatencies().size()));
    }

    public final synchronized float failureRate(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Bucket b = this.byProvider.get(providerId);
        if (b == null) {
            return 0.0f;
        }
        int t = b.getSuccess() + b.getFailure();
        if (t == 0) {
            return 0.0f;
        }
        return b.getFailure() / t;
    }

    public final synchronized Long averageTtftMs(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Bucket b = this.byProvider.get(providerId);
        if (b == null) {
            return null;
        }
        if (b.getTtftCount() == 0) {
            return null;
        }
        return Long.valueOf(b.getTtftSum() / ((long) b.getTtftCount()));
    }

    public final synchronized float speedScore(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Long lAverageLatencyMs = averageLatencyMs(providerId);
        if (lAverageLatencyMs == null) {
            return 0.5f;
        }
        long avg = lAverageLatencyMs.longValue();
        float fail = failureRate(providerId);
        float latScore = RangesKt.coerceIn(1.0f - RangesKt.coerceIn((avg - 200.0f) / 4800.0f, 0.0f, 1.0f), 0.1f, 1.0f);
        return RangesKt.coerceIn((1.0f - (0.7f * fail)) * latScore, 0.0f, 1.0f);
    }

    public final synchronized RouteAggregateMetrics aggregate() {
        int total;
        Long avgCompletion;
        Long avgTtft;
        total = RangesKt.coerceAtLeast(this.cloudOk + this.localOk + this.fallbackOk, 1);
        Iterable iterableValues = this.byProvider.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable<Bucket> $this$flatMap$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Bucket it : $this$flatMap$iv) {
            Iterable list$iv$iv = it.getLatencies();
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        List allLat = (List) destination$iv$iv;
        avgCompletion = allLat.isEmpty() ? null : Long.valueOf(CollectionsKt.sumOfLong(allLat) / ((long) allLat.size()));
        Iterable iterableValues2 = this.byProvider.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues2, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues2;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Bucket it2 = (Bucket) element$iv$iv;
            if (it2.getTtftCount() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List<Bucket> ttftPairs = (List) destination$iv$iv2;
        if (ttftPairs.isEmpty()) {
            avgTtft = null;
        } else {
            long ttftCount = 0;
            long ttftSum = 0;
            for (Bucket it3 : ttftPairs) {
                ttftSum += it3.getTtftSum();
            }
            for (Bucket it4 : ttftPairs) {
                ttftCount += (long) it4.getTtftCount();
            }
            avgTtft = Long.valueOf(ttftSum / ttftCount);
        }
        return new RouteAggregateMetrics((this.cloudOk * 100.0f) / total, (this.localOk * 100.0f) / total, (this.fallbackOk * 100.0f) / total, avgCompletion, avgTtft, this.totalOk);
    }
}
