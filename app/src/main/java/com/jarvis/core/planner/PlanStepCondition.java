package com.jarvis.core.planner;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActionPlanModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/planner/PlanStepCondition;", "", "op", "Lcom/jarvis/core/planner/PlanConditionOp;", "dependsOnStepId", "", "resultKey", "expectedValue", "<init>", "(Lcom/jarvis/core/planner/PlanConditionOp;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getOp", "()Lcom/jarvis/core/planner/PlanConditionOp;", "getDependsOnStepId", "()Ljava/lang/String;", "getResultKey", "getExpectedValue", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PlanStepCondition {
    private final String dependsOnStepId;
    private final String expectedValue;
    private final PlanConditionOp op;
    private final String resultKey;

    public PlanStepCondition() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ PlanStepCondition copy$default(PlanStepCondition planStepCondition, PlanConditionOp planConditionOp, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            planConditionOp = planStepCondition.op;
        }
        if ((i & 2) != 0) {
            str = planStepCondition.dependsOnStepId;
        }
        if ((i & 4) != 0) {
            str2 = planStepCondition.resultKey;
        }
        if ((i & 8) != 0) {
            str3 = planStepCondition.expectedValue;
        }
        return planStepCondition.copy(planConditionOp, str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PlanConditionOp getOp() {
        return this.op;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDependsOnStepId() {
        return this.dependsOnStepId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getResultKey() {
        return this.resultKey;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getExpectedValue() {
        return this.expectedValue;
    }

    public final PlanStepCondition copy(PlanConditionOp op, String dependsOnStepId, String resultKey, String expectedValue) {
        Intrinsics.checkNotNullParameter(op, "op");
        return new PlanStepCondition(op, dependsOnStepId, resultKey, expectedValue);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlanStepCondition)) {
            return false;
        }
        PlanStepCondition planStepCondition = (PlanStepCondition) other;
        return this.op == planStepCondition.op && Intrinsics.areEqual(this.dependsOnStepId, planStepCondition.dependsOnStepId) && Intrinsics.areEqual(this.resultKey, planStepCondition.resultKey) && Intrinsics.areEqual(this.expectedValue, planStepCondition.expectedValue);
    }

    public int hashCode() {
        return (((((this.op.hashCode() * 31) + (this.dependsOnStepId == null ? 0 : this.dependsOnStepId.hashCode())) * 31) + (this.resultKey == null ? 0 : this.resultKey.hashCode())) * 31) + (this.expectedValue != null ? this.expectedValue.hashCode() : 0);
    }

    public String toString() {
        return "PlanStepCondition(op=" + this.op + ", dependsOnStepId=" + this.dependsOnStepId + ", resultKey=" + this.resultKey + ", expectedValue=" + this.expectedValue + ")";
    }

    public PlanStepCondition(PlanConditionOp op, String dependsOnStepId, String resultKey, String expectedValue) {
        Intrinsics.checkNotNullParameter(op, "op");
        this.op = op;
        this.dependsOnStepId = dependsOnStepId;
        this.resultKey = resultKey;
        this.expectedValue = expectedValue;
        if (this.op != PlanConditionOp.RESULT_EQUALS && this.op != PlanConditionOp.RESULT_CONTAINS) {
            return;
        }
        String str = this.dependsOnStepId;
        if (str == null || StringsKt.isBlank(str)) {
            throw new IllegalArgumentException("result condition needs dependsOnStepId".toString());
        }
        String str2 = this.resultKey;
        if (str2 == null || StringsKt.isBlank(str2)) {
            throw new IllegalArgumentException("result condition needs resultKey".toString());
        }
    }

    public /* synthetic */ PlanStepCondition(PlanConditionOp planConditionOp, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? PlanConditionOp.ALWAYS : planConditionOp, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
    }

    public final PlanConditionOp getOp() {
        return this.op;
    }

    public final String getDependsOnStepId() {
        return this.dependsOnStepId;
    }

    public final String getResultKey() {
        return this.resultKey;
    }

    public final String getExpectedValue() {
        return this.expectedValue;
    }
}
