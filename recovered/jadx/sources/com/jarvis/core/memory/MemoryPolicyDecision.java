package com.jarvis.core.memory;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryCandidate.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/memory/MemoryPolicyDecision;", "", "disposition", "Lcom/jarvis/core/memory/MemoryDisposition;", "reason", "", "candidate", "Lcom/jarvis/core/memory/MemoryCandidate;", "<init>", "(Lcom/jarvis/core/memory/MemoryDisposition;Ljava/lang/String;Lcom/jarvis/core/memory/MemoryCandidate;)V", "getDisposition", "()Lcom/jarvis/core/memory/MemoryDisposition;", "getReason", "()Ljava/lang/String;", "getCandidate", "()Lcom/jarvis/core/memory/MemoryCandidate;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MemoryPolicyDecision {
    private final MemoryCandidate candidate;
    private final MemoryDisposition disposition;
    private final String reason;

    public static /* synthetic */ MemoryPolicyDecision copy$default(MemoryPolicyDecision memoryPolicyDecision, MemoryDisposition memoryDisposition, String str, MemoryCandidate memoryCandidate, int i, Object obj) {
        if ((i & 1) != 0) {
            memoryDisposition = memoryPolicyDecision.disposition;
        }
        if ((i & 2) != 0) {
            str = memoryPolicyDecision.reason;
        }
        if ((i & 4) != 0) {
            memoryCandidate = memoryPolicyDecision.candidate;
        }
        return memoryPolicyDecision.copy(memoryDisposition, str, memoryCandidate);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MemoryDisposition getDisposition() {
        return this.disposition;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MemoryCandidate getCandidate() {
        return this.candidate;
    }

    public final MemoryPolicyDecision copy(MemoryDisposition disposition, String reason, MemoryCandidate candidate) {
        Intrinsics.checkNotNullParameter(disposition, "disposition");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        return new MemoryPolicyDecision(disposition, reason, candidate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemoryPolicyDecision)) {
            return false;
        }
        MemoryPolicyDecision memoryPolicyDecision = (MemoryPolicyDecision) other;
        return this.disposition == memoryPolicyDecision.disposition && Intrinsics.areEqual(this.reason, memoryPolicyDecision.reason) && Intrinsics.areEqual(this.candidate, memoryPolicyDecision.candidate);
    }

    public int hashCode() {
        return (((this.disposition.hashCode() * 31) + this.reason.hashCode()) * 31) + this.candidate.hashCode();
    }

    public String toString() {
        return "MemoryPolicyDecision(disposition=" + this.disposition + ", reason=" + this.reason + ", candidate=" + this.candidate + ")";
    }

    public MemoryPolicyDecision(MemoryDisposition disposition, String reason, MemoryCandidate candidate) {
        Intrinsics.checkNotNullParameter(disposition, "disposition");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        this.disposition = disposition;
        this.reason = reason;
        this.candidate = candidate;
    }

    public final MemoryDisposition getDisposition() {
        return this.disposition;
    }

    public final String getReason() {
        return this.reason;
    }

    public final MemoryCandidate getCandidate() {
        return this.candidate;
    }
}
