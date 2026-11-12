package com.jarvis.core.planner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActionPlanModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\t2\u0006\u0010 \u001a\u00020\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\u0015\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003Js\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0013\u0010*\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018¨\u00060"}, d2 = {"Lcom/jarvis/core/planner/ActionPlan;", "", "planId", "", "goalId", "status", "Lcom/jarvis/core/planner/ActionPlanStatus;", "steps", "", "Lcom/jarvis/core/planner/PlanStep;", "entryStepId", "cancellationRequested", "", "resultSlots", "", "notes", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/planner/ActionPlanStatus;Ljava/util/List;Ljava/lang/String;ZLjava/util/Map;Ljava/util/List;)V", "getPlanId", "()Ljava/lang/String;", "getGoalId", "getStatus", "()Lcom/jarvis/core/planner/ActionPlanStatus;", "getSteps", "()Ljava/util/List;", "getEntryStepId", "getCancellationRequested", "()Z", "getResultSlots", "()Ljava/util/Map;", "getNotes", "step", "id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionPlan {
    public static final int MAX_NOTES = 16;
    public static final int MAX_STEPS = 16;
    private final boolean cancellationRequested;
    private final String entryStepId;
    private final String goalId;
    private final List<String> notes;
    private final String planId;
    private final Map<String, String> resultSlots;
    private final ActionPlanStatus status;
    private final List<PlanStep> steps;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPlanId() {
        return this.planId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getGoalId() {
        return this.goalId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ActionPlanStatus getStatus() {
        return this.status;
    }

    public final List<PlanStep> component4() {
        return this.steps;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEntryStepId() {
        return this.entryStepId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getCancellationRequested() {
        return this.cancellationRequested;
    }

    public final Map<String, String> component7() {
        return this.resultSlots;
    }

    public final List<String> component8() {
        return this.notes;
    }

    public final ActionPlan copy(String planId, String goalId, ActionPlanStatus status, List<PlanStep> steps, String entryStepId, boolean cancellationRequested, Map<String, String> resultSlots, List<String> notes) {
        Intrinsics.checkNotNullParameter(planId, "planId");
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(steps, "steps");
        Intrinsics.checkNotNullParameter(resultSlots, "resultSlots");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new ActionPlan(planId, goalId, status, steps, entryStepId, cancellationRequested, resultSlots, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionPlan)) {
            return false;
        }
        ActionPlan actionPlan = (ActionPlan) other;
        return Intrinsics.areEqual(this.planId, actionPlan.planId) && Intrinsics.areEqual(this.goalId, actionPlan.goalId) && this.status == actionPlan.status && Intrinsics.areEqual(this.steps, actionPlan.steps) && Intrinsics.areEqual(this.entryStepId, actionPlan.entryStepId) && this.cancellationRequested == actionPlan.cancellationRequested && Intrinsics.areEqual(this.resultSlots, actionPlan.resultSlots) && Intrinsics.areEqual(this.notes, actionPlan.notes);
    }

    public int hashCode() {
        return (((((((((((((this.planId.hashCode() * 31) + this.goalId.hashCode()) * 31) + this.status.hashCode()) * 31) + this.steps.hashCode()) * 31) + (this.entryStepId == null ? 0 : this.entryStepId.hashCode())) * 31) + Boolean.hashCode(this.cancellationRequested)) * 31) + this.resultSlots.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "ActionPlan(planId=" + this.planId + ", goalId=" + this.goalId + ", status=" + this.status + ", steps=" + this.steps + ", entryStepId=" + this.entryStepId + ", cancellationRequested=" + this.cancellationRequested + ", resultSlots=" + this.resultSlots + ", notes=" + this.notes + ")";
    }

    public ActionPlan(String planId, String goalId, ActionPlanStatus status, List<PlanStep> steps, String entryStepId, boolean cancellationRequested, Map<String, String> resultSlots, List<String> notes) {
        Intrinsics.checkNotNullParameter(planId, "planId");
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(steps, "steps");
        Intrinsics.checkNotNullParameter(resultSlots, "resultSlots");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.planId = planId;
        this.goalId = goalId;
        this.status = status;
        this.steps = steps;
        this.entryStepId = entryStepId;
        this.cancellationRequested = cancellationRequested;
        this.resultSlots = resultSlots;
        this.notes = notes;
        if (StringsKt.isBlank(this.planId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(this.goalId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.steps.size() <= 16)) {
            throw new IllegalArgumentException("too many steps".toString());
        }
        if (!(this.notes.size() <= 16)) {
            throw new IllegalArgumentException("too many notes".toString());
        }
        if (!this.cancellationRequested) {
            return;
        }
        if (this.status == ActionPlanStatus.CANCELLED) {
        } else {
            throw new IllegalArgumentException("cancellationRequested requires CANCELLED status".toString());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ActionPlan(String str, String str2, ActionPlanStatus actionPlanStatus, List list, String str3, boolean z, Map map, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String stepId;
        if ((i & 16) != 0) {
            PlanStep planStep = (PlanStep) CollectionsKt.firstOrNull(list);
            stepId = planStep != null ? planStep.getStepId() : null;
        } else {
            stepId = str3;
        }
        this(str, str2, actionPlanStatus, list, stepId, (i & 32) != 0 ? false : z, (i & 64) != 0 ? MapsKt.emptyMap() : map, (i & 128) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final String getPlanId() {
        return this.planId;
    }

    public final String getGoalId() {
        return this.goalId;
    }

    public final ActionPlanStatus getStatus() {
        return this.status;
    }

    public final List<PlanStep> getSteps() {
        return this.steps;
    }

    public final String getEntryStepId() {
        return this.entryStepId;
    }

    public final boolean getCancellationRequested() {
        return this.cancellationRequested;
    }

    public final Map<String, String> getResultSlots() {
        return this.resultSlots;
    }

    public final List<String> getNotes() {
        return this.notes;
    }

    public final PlanStep step(String id) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(id, "id");
        Iterable $this$firstOrNull$iv = this.steps;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (it.hasNext()) {
            element$iv = it.next();
            PlanStep it2 = (PlanStep) element$iv;
            if (Intrinsics.areEqual(it2.getStepId(), id)) {
                return (PlanStep) element$iv;
            }
        }
        element$iv = null;
        return (PlanStep) element$iv;
    }
}
