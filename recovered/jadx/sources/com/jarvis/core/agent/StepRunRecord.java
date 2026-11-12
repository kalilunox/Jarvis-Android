package com.jarvis.core.agent;

import com.jarvis.core.autonomy.AutonomyPlanStatus;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AgentLoopModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b!\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010)\u001a\u00020\fHÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u000fHÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003Jm\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0001J\u0013\u0010.\u001a\u00020\f2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u0007HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00062"}, d2 = {"Lcom/jarvis/core/agent/StepRunRecord;", "", "stepId", "", "status", "Lcom/jarvis/core/agent/StepRunStatus;", "attempt", "", "autonomyStatus", "Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "toolId", "executed", "", "message", "decisionAfter", "Lcom/jarvis/core/agent/LoopDecision;", "notes", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/agent/StepRunStatus;ILcom/jarvis/core/autonomy/AutonomyPlanStatus;Ljava/lang/String;ZLjava/lang/String;Lcom/jarvis/core/agent/LoopDecision;Ljava/util/List;)V", "getStepId", "()Ljava/lang/String;", "getStatus", "()Lcom/jarvis/core/agent/StepRunStatus;", "getAttempt", "()I", "getAutonomyStatus", "()Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "getToolId", "getExecuted", "()Z", "getMessage", "getDecisionAfter", "()Lcom/jarvis/core/agent/LoopDecision;", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StepRunRecord {
    private final int attempt;
    private final AutonomyPlanStatus autonomyStatus;
    private final LoopDecision decisionAfter;
    private final boolean executed;
    private final String message;
    private final List<String> notes;
    private final StepRunStatus status;
    private final String stepId;
    private final String toolId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getStepId() {
        return this.stepId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final StepRunStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getAttempt() {
        return this.attempt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AutonomyPlanStatus getAutonomyStatus() {
        return this.autonomyStatus;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getExecuted() {
        return this.executed;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final LoopDecision getDecisionAfter() {
        return this.decisionAfter;
    }

    public final List<String> component9() {
        return this.notes;
    }

    public final StepRunRecord copy(String stepId, StepRunStatus status, int attempt, AutonomyPlanStatus autonomyStatus, String toolId, boolean executed, String message, LoopDecision decisionAfter, List<String> notes) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(decisionAfter, "decisionAfter");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new StepRunRecord(stepId, status, attempt, autonomyStatus, toolId, executed, message, decisionAfter, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StepRunRecord)) {
            return false;
        }
        StepRunRecord stepRunRecord = (StepRunRecord) other;
        return Intrinsics.areEqual(this.stepId, stepRunRecord.stepId) && this.status == stepRunRecord.status && this.attempt == stepRunRecord.attempt && this.autonomyStatus == stepRunRecord.autonomyStatus && Intrinsics.areEqual(this.toolId, stepRunRecord.toolId) && this.executed == stepRunRecord.executed && Intrinsics.areEqual(this.message, stepRunRecord.message) && this.decisionAfter == stepRunRecord.decisionAfter && Intrinsics.areEqual(this.notes, stepRunRecord.notes);
    }

    public int hashCode() {
        return (((((((((((((((this.stepId.hashCode() * 31) + this.status.hashCode()) * 31) + Integer.hashCode(this.attempt)) * 31) + (this.autonomyStatus == null ? 0 : this.autonomyStatus.hashCode())) * 31) + (this.toolId != null ? this.toolId.hashCode() : 0)) * 31) + Boolean.hashCode(this.executed)) * 31) + this.message.hashCode()) * 31) + this.decisionAfter.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "StepRunRecord(stepId=" + this.stepId + ", status=" + this.status + ", attempt=" + this.attempt + ", autonomyStatus=" + this.autonomyStatus + ", toolId=" + this.toolId + ", executed=" + this.executed + ", message=" + this.message + ", decisionAfter=" + this.decisionAfter + ", notes=" + this.notes + ")";
    }

    public StepRunRecord(String stepId, StepRunStatus status, int attempt, AutonomyPlanStatus autonomyStatus, String toolId, boolean executed, String message, LoopDecision decisionAfter, List<String> notes) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(decisionAfter, "decisionAfter");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.stepId = stepId;
        this.status = status;
        this.attempt = attempt;
        this.autonomyStatus = autonomyStatus;
        this.toolId = toolId;
        this.executed = executed;
        this.message = message;
        this.decisionAfter = decisionAfter;
        this.notes = notes;
    }

    public /* synthetic */ StepRunRecord(String str, StepRunStatus stepRunStatus, int i, AutonomyPlanStatus autonomyPlanStatus, String str2, boolean z, String str3, LoopDecision loopDecision, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, stepRunStatus, (i2 & 4) != 0 ? 1 : i, (i2 & 8) != 0 ? null : autonomyPlanStatus, (i2 & 16) != 0 ? null : str2, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? "" : str3, loopDecision, (i2 & 256) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getStepId() {
        return this.stepId;
    }

    public final StepRunStatus getStatus() {
        return this.status;
    }

    public final int getAttempt() {
        return this.attempt;
    }

    public final AutonomyPlanStatus getAutonomyStatus() {
        return this.autonomyStatus;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final boolean getExecuted() {
        return this.executed;
    }

    public final String getMessage() {
        return this.message;
    }

    public final LoopDecision getDecisionAfter() {
        return this.decisionAfter;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
