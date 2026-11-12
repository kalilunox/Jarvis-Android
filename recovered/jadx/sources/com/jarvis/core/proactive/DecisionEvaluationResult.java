package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProactiveDecision.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/proactive/DecisionEvaluationResult;", "", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "nextState", "Lcom/jarvis/core/proactive/ProactivityState;", "<init>", "(Lcom/jarvis/core/proactive/ProactiveDecision;Lcom/jarvis/core/proactive/ProactivityState;)V", "getDecision", "()Lcom/jarvis/core/proactive/ProactiveDecision;", "getNextState", "()Lcom/jarvis/core/proactive/ProactivityState;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DecisionEvaluationResult {
    private final ProactiveDecision decision;
    private final ProactivityState nextState;

    public static /* synthetic */ DecisionEvaluationResult copy$default(DecisionEvaluationResult decisionEvaluationResult, ProactiveDecision proactiveDecision, ProactivityState proactivityState, int i, Object obj) {
        if ((i & 1) != 0) {
            proactiveDecision = decisionEvaluationResult.decision;
        }
        if ((i & 2) != 0) {
            proactivityState = decisionEvaluationResult.nextState;
        }
        return decisionEvaluationResult.copy(proactiveDecision, proactivityState);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ProactiveDecision getDecision() {
        return this.decision;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProactivityState getNextState() {
        return this.nextState;
    }

    public final DecisionEvaluationResult copy(ProactiveDecision decision, ProactivityState nextState) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(nextState, "nextState");
        return new DecisionEvaluationResult(decision, nextState);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DecisionEvaluationResult)) {
            return false;
        }
        DecisionEvaluationResult decisionEvaluationResult = (DecisionEvaluationResult) other;
        return Intrinsics.areEqual(this.decision, decisionEvaluationResult.decision) && Intrinsics.areEqual(this.nextState, decisionEvaluationResult.nextState);
    }

    public int hashCode() {
        return (this.decision.hashCode() * 31) + this.nextState.hashCode();
    }

    public String toString() {
        return "DecisionEvaluationResult(decision=" + this.decision + ", nextState=" + this.nextState + ")";
    }

    public DecisionEvaluationResult(ProactiveDecision decision, ProactivityState nextState) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(nextState, "nextState");
        this.decision = decision;
        this.nextState = nextState;
    }

    public final ProactiveDecision getDecision() {
        return this.decision;
    }

    public final ProactivityState getNextState() {
        return this.nextState;
    }
}
