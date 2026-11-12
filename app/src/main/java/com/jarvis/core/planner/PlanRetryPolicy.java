package com.jarvis.core.planner;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ActionPlanModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/planner/PlanRetryPolicy;", "", "maxAttempts", "", "onlyIfRetryable", "", "<init>", "(IZ)V", "getMaxAttempts", "()I", "getOnlyIfRetryable", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PlanRetryPolicy {
    public static final int MAX_ATTEMPTS = 5;
    private final int maxAttempts;
    private final boolean onlyIfRetryable;

    /* JADX WARN: Multi-variable type inference failed */
    public PlanRetryPolicy() {
        this(0, 0 == true ? 1 : 0, 3, null);
    }

    public static /* synthetic */ PlanRetryPolicy copy$default(PlanRetryPolicy planRetryPolicy, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = planRetryPolicy.maxAttempts;
        }
        if ((i2 & 2) != 0) {
            z = planRetryPolicy.onlyIfRetryable;
        }
        return planRetryPolicy.copy(i, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMaxAttempts() {
        return this.maxAttempts;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getOnlyIfRetryable() {
        return this.onlyIfRetryable;
    }

    public final PlanRetryPolicy copy(int maxAttempts, boolean onlyIfRetryable) {
        return new PlanRetryPolicy(maxAttempts, onlyIfRetryable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlanRetryPolicy)) {
            return false;
        }
        PlanRetryPolicy planRetryPolicy = (PlanRetryPolicy) other;
        return this.maxAttempts == planRetryPolicy.maxAttempts && this.onlyIfRetryable == planRetryPolicy.onlyIfRetryable;
    }

    public int hashCode() {
        return (Integer.hashCode(this.maxAttempts) * 31) + Boolean.hashCode(this.onlyIfRetryable);
    }

    public String toString() {
        return "PlanRetryPolicy(maxAttempts=" + this.maxAttempts + ", onlyIfRetryable=" + this.onlyIfRetryable + ")";
    }

    public PlanRetryPolicy(int maxAttempts, boolean onlyIfRetryable) {
        this.maxAttempts = maxAttempts;
        this.onlyIfRetryable = onlyIfRetryable;
        int i = this.maxAttempts;
        boolean z = false;
        if (1 <= i && i < 6) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("maxAttempts out of bounds".toString());
        }
    }

    public /* synthetic */ PlanRetryPolicy(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 1 : i, (i2 & 2) != 0 ? true : z);
    }

    public final int getMaxAttempts() {
        return this.maxAttempts;
    }

    public final boolean getOnlyIfRetryable() {
        return this.onlyIfRetryable;
    }
}
