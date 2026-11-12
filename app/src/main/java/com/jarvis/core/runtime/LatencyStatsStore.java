package com.jarvis.core.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LatencyStatsStore.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J(\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001aJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dJ\u0006\u0010\u001e\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/runtime/LatencyStatsStore;", "", "<init>", "()V", "MAX", "", "samples", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/jarvis/core/runtime/LatencySample;", "byRoute", "Ljava/util/concurrent/ConcurrentHashMap;", "", "value", "last", "getLast", "()Lcom/jarvis/core/runtime/LatencySample;", "record", "", "sample", "recordFrom", "trace", "Lcom/jarvis/core/runtime/RequestTrace;", "percentiles", "Lcom/jarvis/core/runtime/LatencyPercentiles;", "route", "selector", "Lkotlin/Function1;", "", "summaryLines", "", "clear", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LatencyStatsStore {
    private static final int MAX = 64;
    private static volatile LatencySample last;
    public static final LatencyStatsStore INSTANCE = new LatencyStatsStore();
    private static final CopyOnWriteArrayList<LatencySample> samples = new CopyOnWriteArrayList<>();
    private static final ConcurrentHashMap<String, CopyOnWriteArrayList<LatencySample>> byRoute = new ConcurrentHashMap<>();

    private LatencyStatsStore() {
    }

    public final LatencySample getLast() {
        return last;
    }

    public final void record(LatencySample sample) {
        CopyOnWriteArrayList<LatencySample> copyOnWriteArrayListPutIfAbsent;
        Intrinsics.checkNotNullParameter(sample, "sample");
        last = sample;
        samples.add(sample);
        while (samples.size() > 64) {
            samples.remove(0);
        }
        ConcurrentMap $this$getOrPut$iv = byRoute;
        String route = sample.getRoute();
        CopyOnWriteArrayList<LatencySample> copyOnWriteArrayList = $this$getOrPut$iv.get(route);
        if (copyOnWriteArrayList == null && (copyOnWriteArrayListPutIfAbsent = $this$getOrPut$iv.putIfAbsent(route, (copyOnWriteArrayList = new CopyOnWriteArrayList<>()))) != null) {
            copyOnWriteArrayList = copyOnWriteArrayListPutIfAbsent;
        }
        CopyOnWriteArrayList<LatencySample> copyOnWriteArrayList2 = copyOnWriteArrayList;
        copyOnWriteArrayList2.add(sample);
        while (copyOnWriteArrayList2.size() > 64) {
            copyOnWriteArrayList2.remove(0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003f A[PHI: r0
      0x003f: PHI (r0v3 java.lang.Long) = (r0v2 java.lang.Long), (r0v6 java.lang.Long) binds: [B:3:0x001f, B:8:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    public final void recordFrom(RequestTrace trace) {
        Long l;
        Intrinsics.checkNotNullParameter(trace, "trace");
        String requestId = trace.getRequestId();
        String strName = trace.getRoute().name();
        Long e2eMs = trace.getE2eMs();
        if (e2eMs == null) {
            e2eMs = Long.valueOf(trace.getTotalLatencyMs());
            long it = e2eMs.longValue();
            if (it > 0) {
                l = e2eMs;
            } else {
                l = null;
            }
        } else {
            l = e2eMs;
        }
        Long firstAudioMs = trace.getFirstAudioMs();
        Long firstUsefulMs = trace.getFirstUsefulMs();
        Long aiCompletionMs = trace.getAiCompletionMs();
        if (aiCompletionMs == null) {
            aiCompletionMs = trace.getAiLatencyMs();
        }
        Long aiTtftMs = Intrinsics.areEqual(trace.getAiTtftKind(), "REAL") ? trace.getAiTtftMs() : null;
        String aiTtftKind = trace.getAiTtftKind();
        if (aiTtftKind == null) {
            aiTtftKind = "NOT_AVAILABLE";
        }
        String str = aiTtftKind;
        String warmState = trace.getWarmState();
        if (warmState == null) {
            warmState = "UNKNOWN";
        }
        record(new LatencySample(requestId, strName, l, firstAudioMs, firstUsefulMs, aiCompletionMs, aiTtftMs, str, warmState, Intrinsics.areEqual((Object) trace.getColdStart(), (Object) true), Intrinsics.areEqual((Object) trace.getCancelled(), (Object) true), trace.getProvider(), trace.getModel(), 0L, 8192, null));
    }

    public static /* synthetic */ LatencyPercentiles percentiles$default(LatencyStatsStore latencyStatsStore, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return latencyStatsStore.percentiles(str, function1);
    }

    public final LatencyPercentiles percentiles(String route, Function1<? super LatencySample, Long> selector) {
        List src;
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (route == null) {
            src = samples;
        } else {
            src = byRoute.get(route);
            if (src == null) {
                src = CollectionsKt.emptyList();
            }
        }
        List $this$mapNotNull$iv = src;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            Long lInvoke = selector.invoke(element$iv$iv$iv);
            if (lInvoke != null) {
                destination$iv$iv.add(lInvoke);
            }
        }
        List values = CollectionsKt.sorted((List) destination$iv$iv);
        if (values.isEmpty()) {
            return new LatencyPercentiles(0, null, null, null, null, null);
        }
        return new LatencyPercentiles(values.size(), Long.valueOf(percentiles$pct(values, 0.5d)), Long.valueOf(percentiles$pct(values, 0.9d)), Long.valueOf(percentiles$pct(values, 0.95d)), (Long) CollectionsKt.first(values), (Long) CollectionsKt.last(values));
    }

    private static final long percentiles$pct(List<Long> list, double p) {
        int idx = RangesKt.coerceIn((int) (((double) (list.size() - 1)) * p), 0, CollectionsKt.getLastIndex(list));
        return list.get(idx).longValue();
    }

    public final List<String> summaryLines() {
        String route;
        Object e2eMs;
        Object firstAudioMs;
        Object firstUsefulMs;
        Object aiCompletionMs;
        Object realTtftMs;
        String ttftKind;
        String warmState;
        LatencySample lastSample = last;
        LatencyPercentiles audio = percentiles$default(this, null, new Function1() { // from class: com.jarvis.core.runtime.LatencyStatsStore$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LatencyStatsStore.summaryLines$lambda$0((LatencySample) obj);
            }
        }, 1, null);
        LatencyPercentiles e2e = percentiles$default(this, null, new Function1() { // from class: com.jarvis.core.runtime.LatencyStatsStore$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LatencyStatsStore.summaryLines$lambda$1((LatencySample) obj);
            }
        }, 1, null);
        LatencyPercentiles ai2 = percentiles$default(this, null, new Function1() { // from class: com.jarvis.core.runtime.LatencyStatsStore$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LatencyStatsStore.summaryLines$lambda$2((LatencySample) obj);
            }
        }, 1, null);
        List lines = new ArrayList();
        List list = lines;
        if (lastSample == null || (route = lastSample.getRoute()) == null) {
            route = "-";
        }
        if (lastSample == null || (e2eMs = lastSample.getE2eMs()) == null) {
            e2eMs = "-";
        }
        if (lastSample == null || (firstAudioMs = lastSample.getFirstAudioMs()) == null) {
            firstAudioMs = "-";
        }
        if (lastSample == null || (firstUsefulMs = lastSample.getFirstUsefulMs()) == null) {
            firstUsefulMs = "-";
        }
        if (lastSample == null || (aiCompletionMs = lastSample.getAiCompletionMs()) == null) {
            aiCompletionMs = "-";
        }
        if (lastSample == null || (realTtftMs = lastSample.getRealTtftMs()) == null) {
            realTtftMs = "n/a";
        }
        if (lastSample == null || (ttftKind = lastSample.getTtftKind()) == null) {
            ttftKind = "-";
        }
        list.add("latency last route=" + route + " e2e=" + e2eMs + " audio=" + firstAudioMs + " useful=" + firstUsefulMs + " ai=" + aiCompletionMs + " ttft=" + realTtftMs + " ttftKind=" + ttftKind + " warm=" + ((lastSample == null || (warmState = lastSample.getWarmState()) == null) ? "-" : warmState));
        List list2 = lines;
        int count = audio.getCount();
        Object p50 = audio.getP50();
        if (p50 == null) {
            p50 = "-";
        }
        Object p90 = audio.getP90();
        if (p90 == null) {
            p90 = "-";
        }
        Object p95 = audio.getP95();
        if (p95 == null) {
            p95 = "-";
        }
        list2.add("latency audio n=" + count + " p50=" + p50 + " p90=" + p90 + " p95=" + p95);
        List list3 = lines;
        int count2 = e2e.getCount();
        Object p51 = e2e.getP50();
        if (p51 == null) {
            p51 = "-";
        }
        Object p91 = e2e.getP90();
        if (p91 == null) {
            p91 = "-";
        }
        list3.add("latency e2e n=" + count2 + " p50=" + p51 + " p90=" + p91);
        List list4 = lines;
        int count3 = ai2.getCount();
        Object p52 = ai2.getP50();
        if (p52 == null) {
            p52 = "-";
        }
        Object p92 = ai2.getP90();
        if (p92 == null) {
            p92 = "-";
        }
        list4.add("latency aiCompletion n=" + count3 + " p50=" + p52 + " p90=" + p92);
        for (String routeName : CollectionsKt.listOf((Object[]) new String[]{"FAST_SKILL", "TEMPLATE", "CHAT", "PLAN", "CLARIFY"})) {
            LatencyPercentiles r = percentiles(routeName, new Function1() { // from class: com.jarvis.core.runtime.LatencyStatsStore$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LatencyStatsStore.summaryLines$lambda$3((LatencySample) obj);
                }
            });
            if (r.getCount() > 0) {
                lines.add("latency " + routeName + " audio n=" + r.getCount() + " p50=" + r.getP50() + " p90=" + r.getP90());
            }
        }
        return lines;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long summaryLines$lambda$0(LatencySample it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getFirstAudioMs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long summaryLines$lambda$1(LatencySample it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getE2eMs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long summaryLines$lambda$2(LatencySample it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getAiCompletionMs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long summaryLines$lambda$3(LatencySample it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getFirstAudioMs();
    }

    public final void clear() {
        samples.clear();
        byRoute.clear();
        last = null;
    }
}
