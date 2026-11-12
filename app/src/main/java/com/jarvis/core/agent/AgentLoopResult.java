package com.jarvis.core.agent;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AgentLoopModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u0015\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003Jq\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0001J\u0013\u0010(\u001a\u00020\u00062\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001b¨\u0006-"}, d2 = {"Lcom/jarvis/core/agent/AgentLoopResult;", "", "planId", "", "goalId", "completed", "", "finalDecision", "Lcom/jarvis/core/agent/LoopDecision;", "stoppedReason", "records", "", "Lcom/jarvis/core/agent/StepRunRecord;", "resultSlots", "", "notes", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLcom/jarvis/core/agent/LoopDecision;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/List;)V", "getPlanId", "()Ljava/lang/String;", "getGoalId", "getCompleted", "()Z", "getFinalDecision", "()Lcom/jarvis/core/agent/LoopDecision;", "getStoppedReason", "getRecords", "()Ljava/util/List;", "getResultSlots", "()Ljava/util/Map;", "getNotes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AgentLoopResult {
    private final boolean completed;
    private final LoopDecision finalDecision;
    private final String goalId;
    private final List<String> notes;
    private final String planId;
    private final List<StepRunRecord> records;
    private final Map<String, String> resultSlots;
    private final String stoppedReason;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPlanId() {
        return this.planId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getGoalId() {
        return this.goalId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getCompleted() {
        return this.completed;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final LoopDecision getFinalDecision() {
        return this.finalDecision;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStoppedReason() {
        return this.stoppedReason;
    }

    public final List<StepRunRecord> component6() {
        return this.records;
    }

    public final Map<String, String> component7() {
        return this.resultSlots;
    }

    public final List<String> component8() {
        return this.notes;
    }

    public final AgentLoopResult copy(String planId, String goalId, boolean completed, LoopDecision finalDecision, String stoppedReason, List<StepRunRecord> records, Map<String, String> resultSlots, List<String> notes) {
        Intrinsics.checkNotNullParameter(planId, "planId");
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        Intrinsics.checkNotNullParameter(finalDecision, "finalDecision");
        Intrinsics.checkNotNullParameter(stoppedReason, "stoppedReason");
        Intrinsics.checkNotNullParameter(records, "records");
        Intrinsics.checkNotNullParameter(resultSlots, "resultSlots");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new AgentLoopResult(planId, goalId, completed, finalDecision, stoppedReason, records, resultSlots, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AgentLoopResult)) {
            return false;
        }
        AgentLoopResult agentLoopResult = (AgentLoopResult) other;
        return Intrinsics.areEqual(this.planId, agentLoopResult.planId) && Intrinsics.areEqual(this.goalId, agentLoopResult.goalId) && this.completed == agentLoopResult.completed && this.finalDecision == agentLoopResult.finalDecision && Intrinsics.areEqual(this.stoppedReason, agentLoopResult.stoppedReason) && Intrinsics.areEqual(this.records, agentLoopResult.records) && Intrinsics.areEqual(this.resultSlots, agentLoopResult.resultSlots) && Intrinsics.areEqual(this.notes, agentLoopResult.notes);
    }

    public int hashCode() {
        return (((((((((((((this.planId.hashCode() * 31) + this.goalId.hashCode()) * 31) + Boolean.hashCode(this.completed)) * 31) + this.finalDecision.hashCode()) * 31) + this.stoppedReason.hashCode()) * 31) + this.records.hashCode()) * 31) + this.resultSlots.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "AgentLoopResult(planId=" + this.planId + ", goalId=" + this.goalId + ", completed=" + this.completed + ", finalDecision=" + this.finalDecision + ", stoppedReason=" + this.stoppedReason + ", records=" + this.records + ", resultSlots=" + this.resultSlots + ", notes=" + this.notes + ")";
    }

    public AgentLoopResult(String planId, String goalId, boolean completed, LoopDecision finalDecision, String stoppedReason, List<StepRunRecord> records, Map<String, String> resultSlots, List<String> notes) {
        Intrinsics.checkNotNullParameter(planId, "planId");
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        Intrinsics.checkNotNullParameter(finalDecision, "finalDecision");
        Intrinsics.checkNotNullParameter(stoppedReason, "stoppedReason");
        Intrinsics.checkNotNullParameter(records, "records");
        Intrinsics.checkNotNullParameter(resultSlots, "resultSlots");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.planId = planId;
        this.goalId = goalId;
        this.completed = completed;
        this.finalDecision = finalDecision;
        this.stoppedReason = stoppedReason;
        this.records = records;
        this.resultSlots = resultSlots;
        this.notes = notes;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AgentLoopResult(String str, String str2, boolean z, LoopDecision loopDecision, String str3, List list, Map map, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList;
        if ((i & 128) == 0) {
            listEmptyList = list2;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this(str, str2, z, loopDecision, str3, list, map, listEmptyList);
    }

    public final String getPlanId() {
        return this.planId;
    }

    public final String getGoalId() {
        return this.goalId;
    }

    public final boolean getCompleted() {
        return this.completed;
    }

    public final LoopDecision getFinalDecision() {
        return this.finalDecision;
    }

    public final String getStoppedReason() {
        return this.stoppedReason;
    }

    public final List<StepRunRecord> getRecords() {
        return this.records;
    }

    public final Map<String, String> getResultSlots() {
        return this.resultSlots;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
