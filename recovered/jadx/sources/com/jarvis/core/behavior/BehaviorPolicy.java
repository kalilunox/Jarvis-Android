package com.jarvis.core.behavior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BehaviorPolicy.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J6\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/behavior/BehaviorPolicy;", "", "minSuggestConfidence", "", "minRoutineConfidence", "rejectRecoveryConfidence", "<init>", "(FFF)V", "filter", "", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "candidates", "disabledKeys", "", "", "rejectedKeys", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BehaviorPolicy {
    private final float minRoutineConfidence;
    private final float minSuggestConfidence;
    private final float rejectRecoveryConfidence;

    public BehaviorPolicy() {
        this(0.0f, 0.0f, 0.0f, 7, null);
    }

    public BehaviorPolicy(float minSuggestConfidence, float minRoutineConfidence, float rejectRecoveryConfidence) {
        this.minSuggestConfidence = minSuggestConfidence;
        this.minRoutineConfidence = minRoutineConfidence;
        this.rejectRecoveryConfidence = rejectRecoveryConfidence;
    }

    public /* synthetic */ BehaviorPolicy(float f, float f2, float f3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.45f : f, (i & 2) != 0 ? 0.55f : f2, (i & 4) != 0 ? 0.7f : f3);
    }

    public final List<BehaviorCandidate> filter(List<BehaviorCandidate> candidates, Set<String> disabledKeys, Set<String> rejectedKeys) {
        BehaviorCandidate c;
        Set<String> disabledKeys2 = disabledKeys;
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(disabledKeys2, "disabledKeys");
        Intrinsics.checkNotNullParameter(rejectedKeys, "rejectedKeys");
        List<BehaviorCandidate> $this$mapNotNull$iv = candidates;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            BehaviorCandidate raw = (BehaviorCandidate) element$iv$iv$iv;
            BehaviorCandidate behaviorCandidateCopy$default = null;
            if (!disabledKeys2.contains(raw.getPatternKey())) {
                if (!rejectedKeys.contains(raw.getPatternKey())) {
                    c = raw;
                } else if (raw.getConfidence() >= this.rejectRecoveryConfidence) {
                    c = BehaviorCandidate.copy$default(raw, null, null, null, null, RangesKt.coerceAtLeast(raw.getConfidence() - 0.12f, 0.0f), 0, 0L, 0L, null, null, null, null, 0, raw.getNegativeEvidenceCount() + 1, null, 24559, null);
                }
                if (c.getStatus() != BehaviorCandidateStatus.STALE) {
                    float need = c.getKind() == BehaviorKind.ROUTINE ? this.minRoutineConfidence : this.minSuggestConfidence;
                    if (c.getConfidence() >= need) {
                        behaviorCandidateCopy$default = BehaviorCandidate.copy$default(c, null, null, null, null, 0.0f, 0, 0L, 0L, null, BehaviorCandidateStatus.SUGGEST_ONLY, null, null, 0, 0, null, 32255, null);
                    }
                }
            }
            if (behaviorCandidateCopy$default != null) {
                destination$iv$iv.add(behaviorCandidateCopy$default);
            }
            disabledKeys2 = disabledKeys;
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv;
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.behavior.BehaviorPolicy$filter$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                BehaviorCandidate it = (BehaviorCandidate) t2;
                BehaviorCandidate it2 = (BehaviorCandidate) t;
                return ComparisonsKt.compareValues(Float.valueOf(it.getConfidence()), Float.valueOf(it2.getConfidence()));
            }
        });
    }
}
