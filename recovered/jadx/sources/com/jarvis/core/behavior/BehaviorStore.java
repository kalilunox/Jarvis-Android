package com.jarvis.core.behavior;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BehaviorStore.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 .2\u00020\u0001:\u0001.B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u000eJ\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u000eJ\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u000eJ\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0006J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aJ\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u001dJ,\u0010\u001e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0013\u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u000eJ\u0014\u0010\"\u001a\u00020\u00102\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060$J\u0014\u0010%\u001a\u00020\u00102\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060$J\u001a\u0010&\u001a\u00020\u00102\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u001dJ\u0014\u0010(\u001a\u00020\u00102\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012J\u0006\u0010*\u001a\u00020+J\u000e\u0010,\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+J\u0006\u0010-\u001a\u00020\u0010R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000RN\u0010\b\u001aB\u0012\f\u0012\n \n*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \n*\u0004\u0018\u00010\u000b0\u000b \n* \u0012\f\u0012\n \n*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \n*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000RN\u0010\f\u001aB\u0012\f\u0012\n \n*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \n*\u0004\u0018\u00010\u000b0\u000b \n* \u0012\f\u0012\n \n*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \n*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/jarvis/core/behavior/BehaviorStore;", "", "<init>", "()V", "candidates", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "disabled", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", "", "rejected", "ignoreUntil", "", "upsertSuggestions", "", "list", "", "nowMillis", "suggestions", "activeCandidates", "all", "get", ProactiveActionReceiver.EXTRA_PATTERN_KEY, "disabledKeys", "", "rejectedKeys", "ignoreUntilMap", "", "applyFeedback", "feedback", "Lcom/jarvis/core/behavior/BehaviorFeedback;", "ignoreCooldownMs", "loadDisabled", "keys", "", "loadRejected", "loadIgnoreUntil", "map", "replaceAll", "candidatesIn", "snapshot", "Lcom/jarvis/core/behavior/BehaviorStoreSnapshot;", "restore", "clearForTests", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BehaviorStore {
    public static final int ACTIVE_CANDIDATES_LIMIT = 24;
    private final ConcurrentHashMap<String, BehaviorCandidate> candidates = new ConcurrentHashMap<>();
    private final ConcurrentHashMap.KeySetView<String, Boolean> disabled = ConcurrentHashMap.newKeySet();
    private final ConcurrentHashMap.KeySetView<String, Boolean> rejected = ConcurrentHashMap.newKeySet();
    private final ConcurrentHashMap<String, Long> ignoreUntil = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: BehaviorStore.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BehaviorFeedback.values().length];
            try {
                iArr[BehaviorFeedback.ACCEPT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BehaviorFeedback.IGNORE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BehaviorFeedback.REJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BehaviorFeedback.DISABLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void upsertSuggestions$default(BehaviorStore behaviorStore, List list, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        behaviorStore.upsertSuggestions(list, j);
    }

    public final void upsertSuggestions(List<BehaviorCandidate> list, long nowMillis) {
        List<String> listEmptyList;
        List<String> supportingEvidenceIds;
        Intrinsics.checkNotNullParameter(list, "list");
        for (BehaviorCandidate c : list) {
            if (!this.disabled.contains(c.getPatternKey())) {
                BehaviorCandidate prev = this.candidates.get(c.getPatternKey());
                if ((prev != null ? prev.getStatus() : null) != BehaviorCandidateStatus.ACCEPTED) {
                    if ((prev != null ? prev.getStatus() : null) != BehaviorCandidateStatus.DISABLED) {
                        if (prev == null || (listEmptyList = prev.getSupportingEvidenceIds()) == null) {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        List mergedIds = CollectionsKt.takeLast(CollectionsKt.distinct(CollectionsKt.plus((Collection) listEmptyList, (Iterable) c.getSupportingEvidenceIds())), 24);
                        boolean newEvidence = mergedIds.size() > ((prev == null || (supportingEvidenceIds = prev.getSupportingEvidenceIds()) == null) ? 0 : supportingEvidenceIds.size());
                        float mergedConf = ConfidenceModel.INSTANCE.merge(prev != null ? Float.valueOf(prev.getConfidence()) : null, c.getConfidence(), newEvidence);
                        int neg = Math.max(prev != null ? prev.getNegativeEvidenceCount() : 0, c.getNegativeEvidenceCount());
                        BehaviorCandidateStatus status = (prev != null ? prev.getStatus() : null) == BehaviorCandidateStatus.REJECTED ? BehaviorCandidateStatus.REJECTED : BehaviorCandidateStatus.SUGGEST_ONLY;
                        this.candidates.put(c.getPatternKey(), BehaviorCandidate.copy$default(c, null, null, null, null, mergedConf, Math.max(prev != null ? prev.getEvidenceCount() : 0, Math.max(c.getEvidenceCount(), mergedIds.size())), Math.min(prev != null ? prev.getFirstObservedMillis() : c.getFirstObservedMillis(), c.getFirstObservedMillis()), Math.max(prev != null ? prev.getLastObservedMillis() : 0L, c.getLastObservedMillis()), mergedIds, c.getStatus() == BehaviorCandidateStatus.STALE ? BehaviorCandidateStatus.STALE : status, null, null, Math.max(prev != null ? prev.getUniqueDays() : 0, c.getUniqueDays()), neg, null, 19471, null));
                        Long until = this.ignoreUntil.get(c.getPatternKey());
                        if (until != null && nowMillis >= until.longValue()) {
                            this.ignoreUntil.remove(c.getPatternKey());
                        }
                    }
                }
            }
        }
    }

    public static /* synthetic */ List suggestions$default(BehaviorStore behaviorStore, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return behaviorStore.suggestions(j);
    }

    public final List<BehaviorCandidate> suggestions(long nowMillis) {
        boolean z;
        Iterable iterableValues = this.candidates.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            BehaviorCandidate it2 = (BehaviorCandidate) element$iv$iv;
            if (it2.getStatus() == BehaviorCandidateStatus.SUGGEST_ONLY) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$filter$iv2 = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            BehaviorCandidate it3 = (BehaviorCandidate) element$iv$iv2;
            if (it3.getConfidence() >= 0.45f) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        Iterable $this$filter$iv3 = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv3 : $this$filter$iv3) {
            BehaviorCandidate c = (BehaviorCandidate) element$iv$iv3;
            Long l = this.ignoreUntil.get(c.getPatternKey());
            if (l != null) {
                long until = l.longValue();
                z = nowMillis >= until;
            } else {
                z = true;
            }
            if (z) {
                destination$iv$iv3.add(element$iv$iv3);
            }
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv3;
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.behavior.BehaviorStore$suggestions$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                BehaviorCandidate it4 = (BehaviorCandidate) t2;
                BehaviorCandidate it5 = (BehaviorCandidate) t;
                return ComparisonsKt.compareValues(Float.valueOf(it4.getConfidence()), Float.valueOf(it5.getConfidence()));
            }
        });
    }

    public static /* synthetic */ List activeCandidates$default(BehaviorStore behaviorStore, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return behaviorStore.activeCandidates(j);
    }

    public final List<BehaviorCandidate> activeCandidates(long nowMillis) {
        Iterable iterableValues = this.candidates.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            BehaviorCandidate it2 = (BehaviorCandidate) element$iv$iv;
            if (it2.getStatus() == BehaviorCandidateStatus.SUGGEST_ONLY || it2.getStatus() == BehaviorCandidateStatus.ACCEPTED) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$filter$iv2 = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            BehaviorCandidate it3 = (BehaviorCandidate) element$iv$iv2;
            if (it3.getStatus() != BehaviorCandidateStatus.STALE) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        Iterable $this$filter$iv3 = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv3 : $this$filter$iv3) {
            BehaviorCandidate it4 = (BehaviorCandidate) element$iv$iv3;
            if (!this.disabled.contains(it4.getPatternKey())) {
                destination$iv$iv3.add(element$iv$iv3);
            }
        }
        Iterable $this$filter$iv4 = (List) destination$iv$iv3;
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv4 : $this$filter$iv4) {
            BehaviorCandidate it5 = (BehaviorCandidate) element$iv$iv4;
            if (it5.getConfidence() >= 0.4f) {
                destination$iv$iv4.add(element$iv$iv4);
            }
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv4;
        return CollectionsKt.take(CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.behavior.BehaviorStore$activeCandidates$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                BehaviorCandidate it6 = (BehaviorCandidate) t2;
                BehaviorCandidate it7 = (BehaviorCandidate) t;
                return ComparisonsKt.compareValues(Float.valueOf(it6.getConfidence()), Float.valueOf(it7.getConfidence()));
            }
        }), 24);
    }

    public final List<BehaviorCandidate> all() {
        Iterable iterableValues = this.candidates.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$sortedByDescending$iv = iterableValues;
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.behavior.BehaviorStore$all$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                BehaviorCandidate it = (BehaviorCandidate) t2;
                BehaviorCandidate it2 = (BehaviorCandidate) t;
                return ComparisonsKt.compareValues(Float.valueOf(it.getConfidence()), Float.valueOf(it2.getConfidence()));
            }
        });
    }

    public final BehaviorCandidate get(String patternKey) {
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        return this.candidates.get(patternKey);
    }

    public final Set<String> disabledKeys() {
        ConcurrentHashMap.KeySetView<String, Boolean> disabled = this.disabled;
        Intrinsics.checkNotNullExpressionValue(disabled, "disabled");
        return CollectionsKt.toSet(disabled);
    }

    public final Set<String> rejectedKeys() {
        ConcurrentHashMap.KeySetView<String, Boolean> rejected = this.rejected;
        Intrinsics.checkNotNullExpressionValue(rejected, "rejected");
        return CollectionsKt.toSet(rejected);
    }

    public final Map<String, Long> ignoreUntilMap() {
        return MapsKt.toMap(this.ignoreUntil);
    }

    public static /* synthetic */ BehaviorCandidate applyFeedback$default(BehaviorStore behaviorStore, String str, BehaviorFeedback behaviorFeedback, long j, long j2, int i, Object obj) {
        long jCurrentTimeMillis;
        long j3;
        if ((i & 4) == 0) {
            jCurrentTimeMillis = j;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        if ((i & 8) == 0) {
            j3 = j2;
        } else {
            j3 = 21600000;
        }
        return behaviorStore.applyFeedback(str, behaviorFeedback, jCurrentTimeMillis, j3);
    }

    public final BehaviorCandidate applyFeedback(String patternKey, BehaviorFeedback feedback, long nowMillis, long ignoreCooldownMs) {
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        BehaviorCandidate cur = this.candidates.get(patternKey);
        if (cur == null) {
            return null;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[feedback.ordinal()]) {
            case 1:
                this.ignoreUntil.remove(patternKey);
                BehaviorCandidate next = BehaviorCandidate.copy$default(cur, null, null, null, null, 0.0f, 0, 0L, 0L, null, BehaviorCandidateStatus.ACCEPTED, null, null, 0, 0, null, 32255, null);
                this.candidates.put(patternKey, next);
                return next;
            case 2:
                this.ignoreUntil.put(patternKey, Long.valueOf(nowMillis + ignoreCooldownMs));
                BehaviorCandidate next2 = BehaviorCandidate.copy$default(cur, null, null, null, null, 0.0f, 0, 0L, 0L, null, BehaviorCandidateStatus.SUGGEST_ONLY, null, null, 0, 0, null, 32255, null);
                this.candidates.put(patternKey, next2);
                return next2;
            case 3:
                this.rejected.add(patternKey);
                this.ignoreUntil.remove(patternKey);
                BehaviorCandidate next3 = BehaviorCandidate.copy$default(cur, null, null, null, null, RangesKt.coerceAtLeast(cur.getConfidence() - 0.2f, 0.0f), 0, 0L, 0L, null, BehaviorCandidateStatus.REJECTED, null, null, 0, cur.getNegativeEvidenceCount() + 1, null, 24047, null);
                this.candidates.put(patternKey, next3);
                return next3;
            case 4:
                this.disabled.add(patternKey);
                this.rejected.remove(patternKey);
                this.ignoreUntil.remove(patternKey);
                BehaviorCandidate next4 = BehaviorCandidate.copy$default(cur, null, null, null, null, 0.0f, 0, 0L, 0L, null, BehaviorCandidateStatus.DISABLED, null, null, 0, 0, null, 32255, null);
                this.candidates.put(patternKey, next4);
                return next4;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void loadDisabled(Collection<String> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        this.disabled.addAll(keys);
    }

    public final void loadRejected(Collection<String> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        this.rejected.addAll(keys);
    }

    public final void loadIgnoreUntil(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.ignoreUntil.putAll(map);
    }

    public final void replaceAll(List<BehaviorCandidate> candidatesIn) {
        Intrinsics.checkNotNullParameter(candidatesIn, "candidatesIn");
        this.candidates.clear();
        for (BehaviorCandidate c : candidatesIn) {
            this.candidates.put(c.getPatternKey(), c);
        }
    }

    public final BehaviorStoreSnapshot snapshot() {
        return new BehaviorStoreSnapshot(all(), disabledKeys(), rejectedKeys(), ignoreUntilMap());
    }

    public final void restore(BehaviorStoreSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        clearForTests();
        replaceAll(snapshot.getCandidates());
        loadDisabled(snapshot.getDisabledKeys());
        loadRejected(snapshot.getRejectedKeys());
        loadIgnoreUntil(snapshot.getIgnoreUntil());
    }

    public final void clearForTests() {
        this.candidates.clear();
        this.disabled.clear();
        this.rejected.clear();
        this.ignoreUntil.clear();
    }
}
