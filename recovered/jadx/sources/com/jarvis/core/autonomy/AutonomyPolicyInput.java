package com.jarvis.core.autonomy;

import com.jarvis.core.proactive.ProactiveDecision;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutonomyModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\t\u0010*\u001a\u00020\u0011HÆ\u0003Ji\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00062"}, d2 = {"Lcom/jarvis/core/autonomy/AutonomyPolicyInput;", "", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "proposedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "autonomyLevel", "Lcom/jarvis/core/autonomy/AutonomyLevel;", "riskAssessment", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "source", "Lcom/jarvis/core/autonomy/DecisionSource;", "userAllowlistActionIds", "", "", "userBlocklistActionIds", "nowMillis", "", "<init>", "(Lcom/jarvis/core/proactive/ProactiveDecision;Lcom/jarvis/core/autonomy/ProposedAction;Lcom/jarvis/core/autonomy/AutonomyLevel;Lcom/jarvis/core/autonomy/ActionRiskAssessment;Lcom/jarvis/core/autonomy/DecisionSource;Ljava/util/Set;Ljava/util/Set;J)V", "getDecision", "()Lcom/jarvis/core/proactive/ProactiveDecision;", "getProposedAction", "()Lcom/jarvis/core/autonomy/ProposedAction;", "getAutonomyLevel", "()Lcom/jarvis/core/autonomy/AutonomyLevel;", "getRiskAssessment", "()Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "getSource", "()Lcom/jarvis/core/autonomy/DecisionSource;", "getUserAllowlistActionIds", "()Ljava/util/Set;", "getUserBlocklistActionIds", "getNowMillis", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AutonomyPolicyInput {
    private final AutonomyLevel autonomyLevel;
    private final ProactiveDecision decision;
    private final long nowMillis;
    private final ProposedAction proposedAction;
    private final ActionRiskAssessment riskAssessment;
    private final DecisionSource source;
    private final Set<String> userAllowlistActionIds;
    private final Set<String> userBlocklistActionIds;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ProactiveDecision getDecision() {
        return this.decision;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final AutonomyLevel getAutonomyLevel() {
        return this.autonomyLevel;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ActionRiskAssessment getRiskAssessment() {
        return this.riskAssessment;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final DecisionSource getSource() {
        return this.source;
    }

    public final Set<String> component6() {
        return this.userAllowlistActionIds;
    }

    public final Set<String> component7() {
        return this.userBlocklistActionIds;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getNowMillis() {
        return this.nowMillis;
    }

    public final AutonomyPolicyInput copy(ProactiveDecision decision, ProposedAction proposedAction, AutonomyLevel autonomyLevel, ActionRiskAssessment riskAssessment, DecisionSource source, Set<String> userAllowlistActionIds, Set<String> userBlocklistActionIds, long nowMillis) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(autonomyLevel, "autonomyLevel");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(userAllowlistActionIds, "userAllowlistActionIds");
        Intrinsics.checkNotNullParameter(userBlocklistActionIds, "userBlocklistActionIds");
        return new AutonomyPolicyInput(decision, proposedAction, autonomyLevel, riskAssessment, source, userAllowlistActionIds, userBlocklistActionIds, nowMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutonomyPolicyInput)) {
            return false;
        }
        AutonomyPolicyInput autonomyPolicyInput = (AutonomyPolicyInput) other;
        return Intrinsics.areEqual(this.decision, autonomyPolicyInput.decision) && Intrinsics.areEqual(this.proposedAction, autonomyPolicyInput.proposedAction) && this.autonomyLevel == autonomyPolicyInput.autonomyLevel && Intrinsics.areEqual(this.riskAssessment, autonomyPolicyInput.riskAssessment) && this.source == autonomyPolicyInput.source && Intrinsics.areEqual(this.userAllowlistActionIds, autonomyPolicyInput.userAllowlistActionIds) && Intrinsics.areEqual(this.userBlocklistActionIds, autonomyPolicyInput.userBlocklistActionIds) && this.nowMillis == autonomyPolicyInput.nowMillis;
    }

    public int hashCode() {
        return (((((((((((((this.decision.hashCode() * 31) + (this.proposedAction == null ? 0 : this.proposedAction.hashCode())) * 31) + this.autonomyLevel.hashCode()) * 31) + (this.riskAssessment != null ? this.riskAssessment.hashCode() : 0)) * 31) + this.source.hashCode()) * 31) + this.userAllowlistActionIds.hashCode()) * 31) + this.userBlocklistActionIds.hashCode()) * 31) + Long.hashCode(this.nowMillis);
    }

    public String toString() {
        return "AutonomyPolicyInput(decision=" + this.decision + ", proposedAction=" + this.proposedAction + ", autonomyLevel=" + this.autonomyLevel + ", riskAssessment=" + this.riskAssessment + ", source=" + this.source + ", userAllowlistActionIds=" + this.userAllowlistActionIds + ", userBlocklistActionIds=" + this.userBlocklistActionIds + ", nowMillis=" + this.nowMillis + ")";
    }

    public AutonomyPolicyInput(ProactiveDecision decision, ProposedAction proposedAction, AutonomyLevel autonomyLevel, ActionRiskAssessment riskAssessment, DecisionSource source, Set<String> userAllowlistActionIds, Set<String> userBlocklistActionIds, long nowMillis) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(autonomyLevel, "autonomyLevel");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(userAllowlistActionIds, "userAllowlistActionIds");
        Intrinsics.checkNotNullParameter(userBlocklistActionIds, "userBlocklistActionIds");
        this.decision = decision;
        this.proposedAction = proposedAction;
        this.autonomyLevel = autonomyLevel;
        this.riskAssessment = riskAssessment;
        this.source = source;
        this.userAllowlistActionIds = userAllowlistActionIds;
        this.userBlocklistActionIds = userBlocklistActionIds;
        this.nowMillis = nowMillis;
    }

    public /* synthetic */ AutonomyPolicyInput(ProactiveDecision proactiveDecision, ProposedAction proposedAction, AutonomyLevel autonomyLevel, ActionRiskAssessment actionRiskAssessment, DecisionSource decisionSource, Set set, Set set2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(proactiveDecision, proposedAction, autonomyLevel, (i & 8) != 0 ? null : actionRiskAssessment, (i & 16) != 0 ? DecisionSource.PROACTIVE : decisionSource, (i & 32) != 0 ? SetsKt.emptySet() : set, (i & 64) != 0 ? SetsKt.emptySet() : set2, (i & 128) != 0 ? 0L : j);
    }

    public final ProactiveDecision getDecision() {
        return this.decision;
    }

    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    public final AutonomyLevel getAutonomyLevel() {
        return this.autonomyLevel;
    }

    public final ActionRiskAssessment getRiskAssessment() {
        return this.riskAssessment;
    }

    public final DecisionSource getSource() {
        return this.source;
    }

    public final Set<String> getUserAllowlistActionIds() {
        return this.userAllowlistActionIds;
    }

    public final Set<String> getUserBlocklistActionIds() {
        return this.userBlocklistActionIds;
    }

    public final long getNowMillis() {
        return this.nowMillis;
    }
}
