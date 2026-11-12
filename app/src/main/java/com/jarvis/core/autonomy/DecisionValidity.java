package com.jarvis.core.autonomy;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ActionSafetyGateModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/autonomy/DecisionValidity;", "", "decidedAtMillis", "", "ttlMillis", "<init>", "(JJ)V", "getDecidedAtMillis", "()J", "getTtlMillis", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DecisionValidity {
    public static final long DEFAULT_TTL_MS = 60000;
    private final long decidedAtMillis;
    private final long ttlMillis;

    public static /* synthetic */ DecisionValidity copy$default(DecisionValidity decisionValidity, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = decisionValidity.decidedAtMillis;
        }
        if ((i & 2) != 0) {
            j2 = decisionValidity.ttlMillis;
        }
        return decisionValidity.copy(j, j2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getDecidedAtMillis() {
        return this.decidedAtMillis;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTtlMillis() {
        return this.ttlMillis;
    }

    public final DecisionValidity copy(long decidedAtMillis, long ttlMillis) {
        return new DecisionValidity(decidedAtMillis, ttlMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DecisionValidity)) {
            return false;
        }
        DecisionValidity decisionValidity = (DecisionValidity) other;
        return this.decidedAtMillis == decisionValidity.decidedAtMillis && this.ttlMillis == decisionValidity.ttlMillis;
    }

    public int hashCode() {
        return (Long.hashCode(this.decidedAtMillis) * 31) + Long.hashCode(this.ttlMillis);
    }

    public String toString() {
        return "DecisionValidity(decidedAtMillis=" + this.decidedAtMillis + ", ttlMillis=" + this.ttlMillis + ")";
    }

    public DecisionValidity(long decidedAtMillis, long ttlMillis) {
        this.decidedAtMillis = decidedAtMillis;
        this.ttlMillis = ttlMillis;
    }

    public /* synthetic */ DecisionValidity(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? 60000L : j2);
    }

    public final long getDecidedAtMillis() {
        return this.decidedAtMillis;
    }

    public final long getTtlMillis() {
        return this.ttlMillis;
    }
}
