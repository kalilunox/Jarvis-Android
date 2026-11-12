package com.jarvis.core.agent;

import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.proactive.ProactiveDecision;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProactiveAgent.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J=\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006!"}, d2 = {"Lcom/jarvis/core/agent/ProactiveSuggestion;", "", "suggestionId", "", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "allowedToPropose", "", "reason", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/proactive/ProactiveDecision;Lcom/jarvis/core/planner/ActionPlan;ZLjava/lang/String;)V", "getSuggestionId", "()Ljava/lang/String;", "getDecision", "()Lcom/jarvis/core/proactive/ProactiveDecision;", "getPlan", "()Lcom/jarvis/core/planner/ActionPlan;", "getAllowedToPropose", "()Z", "getReason", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProactiveSuggestion {
    private final boolean allowedToPropose;
    private final ProactiveDecision decision;
    private final ActionPlan plan;
    private final String reason;
    private final String suggestionId;

    public static /* synthetic */ ProactiveSuggestion copy$default(ProactiveSuggestion proactiveSuggestion, String str, ProactiveDecision proactiveDecision, ActionPlan actionPlan, boolean z, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = proactiveSuggestion.suggestionId;
        }
        if ((i & 2) != 0) {
            proactiveDecision = proactiveSuggestion.decision;
        }
        ProactiveDecision proactiveDecision2 = proactiveDecision;
        if ((i & 4) != 0) {
            actionPlan = proactiveSuggestion.plan;
        }
        ActionPlan actionPlan2 = actionPlan;
        if ((i & 8) != 0) {
            z = proactiveSuggestion.allowedToPropose;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            str2 = proactiveSuggestion.reason;
        }
        return proactiveSuggestion.copy(str, proactiveDecision2, actionPlan2, z2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSuggestionId() {
        return this.suggestionId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProactiveDecision getDecision() {
        return this.decision;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ActionPlan getPlan() {
        return this.plan;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getAllowedToPropose() {
        return this.allowedToPropose;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    public final ProactiveSuggestion copy(String suggestionId, ProactiveDecision decision, ActionPlan plan, boolean allowedToPropose, String reason) {
        Intrinsics.checkNotNullParameter(suggestionId, "suggestionId");
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new ProactiveSuggestion(suggestionId, decision, plan, allowedToPropose, reason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProactiveSuggestion)) {
            return false;
        }
        ProactiveSuggestion proactiveSuggestion = (ProactiveSuggestion) other;
        return Intrinsics.areEqual(this.suggestionId, proactiveSuggestion.suggestionId) && Intrinsics.areEqual(this.decision, proactiveSuggestion.decision) && Intrinsics.areEqual(this.plan, proactiveSuggestion.plan) && this.allowedToPropose == proactiveSuggestion.allowedToPropose && Intrinsics.areEqual(this.reason, proactiveSuggestion.reason);
    }

    public int hashCode() {
        return (((((((this.suggestionId.hashCode() * 31) + this.decision.hashCode()) * 31) + (this.plan == null ? 0 : this.plan.hashCode())) * 31) + Boolean.hashCode(this.allowedToPropose)) * 31) + this.reason.hashCode();
    }

    public String toString() {
        return "ProactiveSuggestion(suggestionId=" + this.suggestionId + ", decision=" + this.decision + ", plan=" + this.plan + ", allowedToPropose=" + this.allowedToPropose + ", reason=" + this.reason + ")";
    }

    public ProactiveSuggestion(String suggestionId, ProactiveDecision decision, ActionPlan plan, boolean allowedToPropose, String reason) {
        Intrinsics.checkNotNullParameter(suggestionId, "suggestionId");
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.suggestionId = suggestionId;
        this.decision = decision;
        this.plan = plan;
        this.allowedToPropose = allowedToPropose;
        this.reason = reason;
    }

    public final String getSuggestionId() {
        return this.suggestionId;
    }

    public final ProactiveDecision getDecision() {
        return this.decision;
    }

    public final ActionPlan getPlan() {
        return this.plan;
    }

    public final boolean getAllowedToPropose() {
        return this.allowedToPropose;
    }

    public final String getReason() {
        return this.reason;
    }
}
