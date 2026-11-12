package com.jarvis.core.autonomy;

import com.jarvis.core.common.model.ToolCall;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutonomyActionPipeline.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003JQ\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006*"}, d2 = {"Lcom/jarvis/core/autonomy/AutonomyActionPlan;", "", "status", "Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "verdict", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "proposedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "gateResult", "Lcom/jarvis/core/autonomy/ActionSafetyGateResult;", "draftToolCall", "Lcom/jarvis/core/common/model/ToolCall;", "notes", "", "", "<init>", "(Lcom/jarvis/core/autonomy/AutonomyPlanStatus;Lcom/jarvis/core/autonomy/AutonomyVerdict;Lcom/jarvis/core/autonomy/ProposedAction;Lcom/jarvis/core/autonomy/ActionSafetyGateResult;Lcom/jarvis/core/common/model/ToolCall;Ljava/util/List;)V", "getStatus", "()Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "getVerdict", "()Lcom/jarvis/core/autonomy/AutonomyVerdict;", "getProposedAction", "()Lcom/jarvis/core/autonomy/ProposedAction;", "getGateResult", "()Lcom/jarvis/core/autonomy/ActionSafetyGateResult;", "getDraftToolCall", "()Lcom/jarvis/core/common/model/ToolCall;", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AutonomyActionPlan {
    private final ToolCall draftToolCall;
    private final ActionSafetyGateResult gateResult;
    private final List<String> notes;
    private final ProposedAction proposedAction;
    private final AutonomyPlanStatus status;
    private final AutonomyVerdict verdict;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AutonomyActionPlan copy$default(AutonomyActionPlan autonomyActionPlan, AutonomyPlanStatus autonomyPlanStatus, AutonomyVerdict autonomyVerdict, ProposedAction proposedAction, ActionSafetyGateResult actionSafetyGateResult, ToolCall toolCall, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            autonomyPlanStatus = autonomyActionPlan.status;
        }
        if ((i & 2) != 0) {
            autonomyVerdict = autonomyActionPlan.verdict;
        }
        AutonomyVerdict autonomyVerdict2 = autonomyVerdict;
        if ((i & 4) != 0) {
            proposedAction = autonomyActionPlan.proposedAction;
        }
        ProposedAction proposedAction2 = proposedAction;
        if ((i & 8) != 0) {
            actionSafetyGateResult = autonomyActionPlan.gateResult;
        }
        ActionSafetyGateResult actionSafetyGateResult2 = actionSafetyGateResult;
        if ((i & 16) != 0) {
            toolCall = autonomyActionPlan.draftToolCall;
        }
        ToolCall toolCall2 = toolCall;
        if ((i & 32) != 0) {
            list = autonomyActionPlan.notes;
        }
        return autonomyActionPlan.copy(autonomyPlanStatus, autonomyVerdict2, proposedAction2, actionSafetyGateResult2, toolCall2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AutonomyPlanStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AutonomyVerdict getVerdict() {
        return this.verdict;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ActionSafetyGateResult getGateResult() {
        return this.gateResult;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ToolCall getDraftToolCall() {
        return this.draftToolCall;
    }

    public final List<String> component6() {
        return this.notes;
    }

    public final AutonomyActionPlan copy(AutonomyPlanStatus status, AutonomyVerdict verdict, ProposedAction proposedAction, ActionSafetyGateResult gateResult, ToolCall draftToolCall, List<String> notes) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(verdict, "verdict");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new AutonomyActionPlan(status, verdict, proposedAction, gateResult, draftToolCall, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutonomyActionPlan)) {
            return false;
        }
        AutonomyActionPlan autonomyActionPlan = (AutonomyActionPlan) other;
        return this.status == autonomyActionPlan.status && Intrinsics.areEqual(this.verdict, autonomyActionPlan.verdict) && Intrinsics.areEqual(this.proposedAction, autonomyActionPlan.proposedAction) && Intrinsics.areEqual(this.gateResult, autonomyActionPlan.gateResult) && Intrinsics.areEqual(this.draftToolCall, autonomyActionPlan.draftToolCall) && Intrinsics.areEqual(this.notes, autonomyActionPlan.notes);
    }

    public int hashCode() {
        return (((((((((this.status.hashCode() * 31) + this.verdict.hashCode()) * 31) + (this.proposedAction == null ? 0 : this.proposedAction.hashCode())) * 31) + (this.gateResult == null ? 0 : this.gateResult.hashCode())) * 31) + (this.draftToolCall != null ? this.draftToolCall.hashCode() : 0)) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "AutonomyActionPlan(status=" + this.status + ", verdict=" + this.verdict + ", proposedAction=" + this.proposedAction + ", gateResult=" + this.gateResult + ", draftToolCall=" + this.draftToolCall + ", notes=" + this.notes + ")";
    }

    public AutonomyActionPlan(AutonomyPlanStatus status, AutonomyVerdict verdict, ProposedAction proposedAction, ActionSafetyGateResult gateResult, ToolCall draftToolCall, List<String> notes) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(verdict, "verdict");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.status = status;
        this.verdict = verdict;
        this.proposedAction = proposedAction;
        this.gateResult = gateResult;
        this.draftToolCall = draftToolCall;
        this.notes = notes;
    }

    public /* synthetic */ AutonomyActionPlan(AutonomyPlanStatus autonomyPlanStatus, AutonomyVerdict autonomyVerdict, ProposedAction proposedAction, ActionSafetyGateResult actionSafetyGateResult, ToolCall toolCall, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(autonomyPlanStatus, autonomyVerdict, proposedAction, (i & 8) != 0 ? null : actionSafetyGateResult, (i & 16) != 0 ? null : toolCall, (i & 32) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final AutonomyPlanStatus getStatus() {
        return this.status;
    }

    public final AutonomyVerdict getVerdict() {
        return this.verdict;
    }

    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    public final ActionSafetyGateResult getGateResult() {
        return this.gateResult;
    }

    public final ToolCall getDraftToolCall() {
        return this.draftToolCall;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
