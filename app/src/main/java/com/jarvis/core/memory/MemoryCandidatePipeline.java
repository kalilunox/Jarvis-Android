package com.jarvis.core.memory;

import com.jarvis.core.companion.ContextSnapshot;
import com.jarvis.core.companion.PhoneObservation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryCandidatePipeline.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bJ\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/memory/MemoryCandidatePipeline;", "", "detector", "Lcom/jarvis/core/memory/MemoryCandidateDetector;", "sensitivityFilter", "Lcom/jarvis/core/memory/SensitivityFilter;", "policy", "Lcom/jarvis/core/memory/MemoryPolicy;", "<init>", "(Lcom/jarvis/core/memory/MemoryCandidateDetector;Lcom/jarvis/core/memory/SensitivityFilter;Lcom/jarvis/core/memory/MemoryPolicy;)V", "evaluate", "", "Lcom/jarvis/core/memory/MemoryPolicyDecision;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "recent", "Lcom/jarvis/core/companion/PhoneObservation;", "resetForTests", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryCandidatePipeline {
    private final MemoryCandidateDetector detector;
    private final MemoryPolicy policy;
    private final SensitivityFilter sensitivityFilter;

    public MemoryCandidatePipeline() {
        this(null, null, null, 7, null);
    }

    public MemoryCandidatePipeline(MemoryCandidateDetector detector, SensitivityFilter sensitivityFilter, MemoryPolicy policy) {
        Intrinsics.checkNotNullParameter(detector, "detector");
        Intrinsics.checkNotNullParameter(sensitivityFilter, "sensitivityFilter");
        Intrinsics.checkNotNullParameter(policy, "policy");
        this.detector = detector;
        this.sensitivityFilter = sensitivityFilter;
        this.policy = policy;
    }

    public /* synthetic */ MemoryCandidatePipeline(MemoryCandidateDetector memoryCandidateDetector, SensitivityFilter sensitivityFilter, MemoryPolicy memoryPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new MemoryCandidateDetector(0, 0L, 3, null) : memoryCandidateDetector, (i & 2) != 0 ? new SensitivityFilter() : sensitivityFilter, (i & 4) != 0 ? new MemoryPolicy(0.0f, 0.0f, 3, null) : memoryPolicy);
    }

    public final List<MemoryPolicyDecision> evaluate(ContextSnapshot snapshot, List<PhoneObservation> recent) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(recent, "recent");
        Iterable $this$map$iv = MemoryCandidateDetector.detect$default(this.detector, snapshot, recent, 0L, 4, null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            MemoryCandidate c = (MemoryCandidate) item$iv$iv;
            MemoryPolicyDecision memoryPolicyDecisionFilter = this.sensitivityFilter.filter(c);
            if (memoryPolicyDecisionFilter == null) {
                memoryPolicyDecisionFilter = this.policy.decide(c);
            }
            destination$iv$iv.add(memoryPolicyDecisionFilter);
        }
        return (List) destination$iv$iv;
    }

    public final void resetForTests() {
    }
}
