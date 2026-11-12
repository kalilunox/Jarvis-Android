package com.jarvis.core.behavior;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BehaviorStore.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u0015\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JO\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0007HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/jarvis/core/behavior/BehaviorStoreSnapshot;", "", "candidates", "", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "disabledKeys", "", "", "rejectedKeys", "ignoreUntil", "", "", "<init>", "(Ljava/util/List;Ljava/util/Set;Ljava/util/Set;Ljava/util/Map;)V", "getCandidates", "()Ljava/util/List;", "getDisabledKeys", "()Ljava/util/Set;", "getRejectedKeys", "getIgnoreUntil", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class BehaviorStoreSnapshot {
    private final List<BehaviorCandidate> candidates;
    private final Set<String> disabledKeys;
    private final Map<String, Long> ignoreUntil;
    private final Set<String> rejectedKeys;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BehaviorStoreSnapshot copy$default(BehaviorStoreSnapshot behaviorStoreSnapshot, List list, Set set, Set set2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            list = behaviorStoreSnapshot.candidates;
        }
        if ((i & 2) != 0) {
            set = behaviorStoreSnapshot.disabledKeys;
        }
        if ((i & 4) != 0) {
            set2 = behaviorStoreSnapshot.rejectedKeys;
        }
        if ((i & 8) != 0) {
            map = behaviorStoreSnapshot.ignoreUntil;
        }
        return behaviorStoreSnapshot.copy(list, set, set2, map);
    }

    public final List<BehaviorCandidate> component1() {
        return this.candidates;
    }

    public final Set<String> component2() {
        return this.disabledKeys;
    }

    public final Set<String> component3() {
        return this.rejectedKeys;
    }

    public final Map<String, Long> component4() {
        return this.ignoreUntil;
    }

    public final BehaviorStoreSnapshot copy(List<BehaviorCandidate> candidates, Set<String> disabledKeys, Set<String> rejectedKeys, Map<String, Long> ignoreUntil) {
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(disabledKeys, "disabledKeys");
        Intrinsics.checkNotNullParameter(rejectedKeys, "rejectedKeys");
        Intrinsics.checkNotNullParameter(ignoreUntil, "ignoreUntil");
        return new BehaviorStoreSnapshot(candidates, disabledKeys, rejectedKeys, ignoreUntil);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BehaviorStoreSnapshot)) {
            return false;
        }
        BehaviorStoreSnapshot behaviorStoreSnapshot = (BehaviorStoreSnapshot) other;
        return Intrinsics.areEqual(this.candidates, behaviorStoreSnapshot.candidates) && Intrinsics.areEqual(this.disabledKeys, behaviorStoreSnapshot.disabledKeys) && Intrinsics.areEqual(this.rejectedKeys, behaviorStoreSnapshot.rejectedKeys) && Intrinsics.areEqual(this.ignoreUntil, behaviorStoreSnapshot.ignoreUntil);
    }

    public int hashCode() {
        return (((((this.candidates.hashCode() * 31) + this.disabledKeys.hashCode()) * 31) + this.rejectedKeys.hashCode()) * 31) + this.ignoreUntil.hashCode();
    }

    public String toString() {
        return "BehaviorStoreSnapshot(candidates=" + this.candidates + ", disabledKeys=" + this.disabledKeys + ", rejectedKeys=" + this.rejectedKeys + ", ignoreUntil=" + this.ignoreUntil + ")";
    }

    public BehaviorStoreSnapshot(List<BehaviorCandidate> candidates, Set<String> disabledKeys, Set<String> rejectedKeys, Map<String, Long> ignoreUntil) {
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(disabledKeys, "disabledKeys");
        Intrinsics.checkNotNullParameter(rejectedKeys, "rejectedKeys");
        Intrinsics.checkNotNullParameter(ignoreUntil, "ignoreUntil");
        this.candidates = candidates;
        this.disabledKeys = disabledKeys;
        this.rejectedKeys = rejectedKeys;
        this.ignoreUntil = ignoreUntil;
    }

    public final List<BehaviorCandidate> getCandidates() {
        return this.candidates;
    }

    public final Set<String> getDisabledKeys() {
        return this.disabledKeys;
    }

    public final Set<String> getRejectedKeys() {
        return this.rejectedKeys;
    }

    public final Map<String, Long> getIgnoreUntil() {
        return this.ignoreUntil;
    }
}
