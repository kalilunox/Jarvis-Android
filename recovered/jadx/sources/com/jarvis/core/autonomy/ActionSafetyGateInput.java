package com.jarvis.core.autonomy;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionSafetyGateModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u0011\u00109\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J§\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010?\u001a\u00020@HÖ\u0001J\t\u0010A\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0019\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b.\u0010!¨\u0006B"}, d2 = {"Lcom/jarvis/core/autonomy/ActionSafetyGateInput;", "", "verdict", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "riskAssessment", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "nowMillis", "", "requiredPermissions", "", "", "grantedPermissions", "contextFreshness", "Lcom/jarvis/core/autonomy/ContextFreshness;", "decisionValidity", "Lcom/jarvis/core/autonomy/DecisionValidity;", "confirmation", "Lcom/jarvis/core/autonomy/ActionConfirmation;", "policyBinding", "Lcom/jarvis/core/autonomy/PolicyDecisionBinding;", "presentedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "allowedArgumentKeys", "forbiddenArgumentKeys", "<init>", "(Lcom/jarvis/core/autonomy/AutonomyVerdict;Lcom/jarvis/core/autonomy/ActionRiskAssessment;JLjava/util/Set;Ljava/util/Set;Lcom/jarvis/core/autonomy/ContextFreshness;Lcom/jarvis/core/autonomy/DecisionValidity;Lcom/jarvis/core/autonomy/ActionConfirmation;Lcom/jarvis/core/autonomy/PolicyDecisionBinding;Lcom/jarvis/core/autonomy/ProposedAction;Ljava/util/Set;Ljava/util/Set;)V", "getVerdict", "()Lcom/jarvis/core/autonomy/AutonomyVerdict;", "getRiskAssessment", "()Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "getNowMillis", "()J", "getRequiredPermissions", "()Ljava/util/Set;", "getGrantedPermissions", "getContextFreshness", "()Lcom/jarvis/core/autonomy/ContextFreshness;", "getDecisionValidity", "()Lcom/jarvis/core/autonomy/DecisionValidity;", "getConfirmation", "()Lcom/jarvis/core/autonomy/ActionConfirmation;", "getPolicyBinding", "()Lcom/jarvis/core/autonomy/PolicyDecisionBinding;", "getPresentedAction", "()Lcom/jarvis/core/autonomy/ProposedAction;", "getAllowedArgumentKeys", "getForbiddenArgumentKeys", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionSafetyGateInput {
    private final Set<String> allowedArgumentKeys;
    private final ActionConfirmation confirmation;
    private final ContextFreshness contextFreshness;
    private final DecisionValidity decisionValidity;
    private final Set<String> forbiddenArgumentKeys;
    private final Set<String> grantedPermissions;
    private final long nowMillis;
    private final PolicyDecisionBinding policyBinding;
    private final ProposedAction presentedAction;
    private final Set<String> requiredPermissions;
    private final ActionRiskAssessment riskAssessment;
    private final AutonomyVerdict verdict;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AutonomyVerdict getVerdict() {
        return this.verdict;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final ProposedAction getPresentedAction() {
        return this.presentedAction;
    }

    public final Set<String> component11() {
        return this.allowedArgumentKeys;
    }

    public final Set<String> component12() {
        return this.forbiddenArgumentKeys;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ActionRiskAssessment getRiskAssessment() {
        return this.riskAssessment;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getNowMillis() {
        return this.nowMillis;
    }

    public final Set<String> component4() {
        return this.requiredPermissions;
    }

    public final Set<String> component5() {
        return this.grantedPermissions;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final ContextFreshness getContextFreshness() {
        return this.contextFreshness;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final DecisionValidity getDecisionValidity() {
        return this.decisionValidity;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final ActionConfirmation getConfirmation() {
        return this.confirmation;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final PolicyDecisionBinding getPolicyBinding() {
        return this.policyBinding;
    }

    public final ActionSafetyGateInput copy(AutonomyVerdict verdict, ActionRiskAssessment riskAssessment, long nowMillis, Set<String> requiredPermissions, Set<String> grantedPermissions, ContextFreshness contextFreshness, DecisionValidity decisionValidity, ActionConfirmation confirmation, PolicyDecisionBinding policyBinding, ProposedAction presentedAction, Set<String> allowedArgumentKeys, Set<String> forbiddenArgumentKeys) {
        Intrinsics.checkNotNullParameter(verdict, "verdict");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(grantedPermissions, "grantedPermissions");
        Intrinsics.checkNotNullParameter(forbiddenArgumentKeys, "forbiddenArgumentKeys");
        return new ActionSafetyGateInput(verdict, riskAssessment, nowMillis, requiredPermissions, grantedPermissions, contextFreshness, decisionValidity, confirmation, policyBinding, presentedAction, allowedArgumentKeys, forbiddenArgumentKeys);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionSafetyGateInput)) {
            return false;
        }
        ActionSafetyGateInput actionSafetyGateInput = (ActionSafetyGateInput) other;
        return Intrinsics.areEqual(this.verdict, actionSafetyGateInput.verdict) && Intrinsics.areEqual(this.riskAssessment, actionSafetyGateInput.riskAssessment) && this.nowMillis == actionSafetyGateInput.nowMillis && Intrinsics.areEqual(this.requiredPermissions, actionSafetyGateInput.requiredPermissions) && Intrinsics.areEqual(this.grantedPermissions, actionSafetyGateInput.grantedPermissions) && Intrinsics.areEqual(this.contextFreshness, actionSafetyGateInput.contextFreshness) && Intrinsics.areEqual(this.decisionValidity, actionSafetyGateInput.decisionValidity) && Intrinsics.areEqual(this.confirmation, actionSafetyGateInput.confirmation) && Intrinsics.areEqual(this.policyBinding, actionSafetyGateInput.policyBinding) && Intrinsics.areEqual(this.presentedAction, actionSafetyGateInput.presentedAction) && Intrinsics.areEqual(this.allowedArgumentKeys, actionSafetyGateInput.allowedArgumentKeys) && Intrinsics.areEqual(this.forbiddenArgumentKeys, actionSafetyGateInput.forbiddenArgumentKeys);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.verdict.hashCode() * 31) + (this.riskAssessment == null ? 0 : this.riskAssessment.hashCode())) * 31) + Long.hashCode(this.nowMillis)) * 31) + this.requiredPermissions.hashCode()) * 31) + this.grantedPermissions.hashCode()) * 31) + (this.contextFreshness == null ? 0 : this.contextFreshness.hashCode())) * 31) + (this.decisionValidity == null ? 0 : this.decisionValidity.hashCode())) * 31) + (this.confirmation == null ? 0 : this.confirmation.hashCode())) * 31) + (this.policyBinding == null ? 0 : this.policyBinding.hashCode())) * 31) + (this.presentedAction == null ? 0 : this.presentedAction.hashCode())) * 31) + (this.allowedArgumentKeys != null ? this.allowedArgumentKeys.hashCode() : 0)) * 31) + this.forbiddenArgumentKeys.hashCode();
    }

    public String toString() {
        return "ActionSafetyGateInput(verdict=" + this.verdict + ", riskAssessment=" + this.riskAssessment + ", nowMillis=" + this.nowMillis + ", requiredPermissions=" + this.requiredPermissions + ", grantedPermissions=" + this.grantedPermissions + ", contextFreshness=" + this.contextFreshness + ", decisionValidity=" + this.decisionValidity + ", confirmation=" + this.confirmation + ", policyBinding=" + this.policyBinding + ", presentedAction=" + this.presentedAction + ", allowedArgumentKeys=" + this.allowedArgumentKeys + ", forbiddenArgumentKeys=" + this.forbiddenArgumentKeys + ")";
    }

    public ActionSafetyGateInput(AutonomyVerdict verdict, ActionRiskAssessment riskAssessment, long nowMillis, Set<String> requiredPermissions, Set<String> grantedPermissions, ContextFreshness contextFreshness, DecisionValidity decisionValidity, ActionConfirmation confirmation, PolicyDecisionBinding policyBinding, ProposedAction presentedAction, Set<String> set, Set<String> forbiddenArgumentKeys) {
        Intrinsics.checkNotNullParameter(verdict, "verdict");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(grantedPermissions, "grantedPermissions");
        Intrinsics.checkNotNullParameter(forbiddenArgumentKeys, "forbiddenArgumentKeys");
        this.verdict = verdict;
        this.riskAssessment = riskAssessment;
        this.nowMillis = nowMillis;
        this.requiredPermissions = requiredPermissions;
        this.grantedPermissions = grantedPermissions;
        this.contextFreshness = contextFreshness;
        this.decisionValidity = decisionValidity;
        this.confirmation = confirmation;
        this.policyBinding = policyBinding;
        this.presentedAction = presentedAction;
        this.allowedArgumentKeys = set;
        this.forbiddenArgumentKeys = forbiddenArgumentKeys;
    }

    public /* synthetic */ ActionSafetyGateInput(AutonomyVerdict autonomyVerdict, ActionRiskAssessment actionRiskAssessment, long j, Set set, Set set2, ContextFreshness contextFreshness, DecisionValidity decisionValidity, ActionConfirmation actionConfirmation, PolicyDecisionBinding policyDecisionBinding, ProposedAction proposedAction, Set set3, Set set4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(autonomyVerdict, actionRiskAssessment, j, (i & 8) != 0 ? SetsKt.emptySet() : set, (i & 16) != 0 ? SetsKt.emptySet() : set2, (i & 32) != 0 ? null : contextFreshness, (i & 64) != 0 ? null : decisionValidity, (i & 128) != 0 ? null : actionConfirmation, (i & 256) != 0 ? null : policyDecisionBinding, (i & 512) != 0 ? null : proposedAction, (i & 1024) != 0 ? null : set3, (i & 2048) != 0 ? ActionArgumentRules.INSTANCE.getGLOBAL_FORBIDDEN_KEYS() : set4);
    }

    public final AutonomyVerdict getVerdict() {
        return this.verdict;
    }

    public final ActionRiskAssessment getRiskAssessment() {
        return this.riskAssessment;
    }

    public final long getNowMillis() {
        return this.nowMillis;
    }

    public final Set<String> getRequiredPermissions() {
        return this.requiredPermissions;
    }

    public final Set<String> getGrantedPermissions() {
        return this.grantedPermissions;
    }

    public final ContextFreshness getContextFreshness() {
        return this.contextFreshness;
    }

    public final DecisionValidity getDecisionValidity() {
        return this.decisionValidity;
    }

    public final ActionConfirmation getConfirmation() {
        return this.confirmation;
    }

    public final PolicyDecisionBinding getPolicyBinding() {
        return this.policyBinding;
    }

    public final ProposedAction getPresentedAction() {
        return this.presentedAction;
    }

    public final Set<String> getAllowedArgumentKeys() {
        return this.allowedArgumentKeys;
    }

    public final Set<String> getForbiddenArgumentKeys() {
        return this.forbiddenArgumentKeys;
    }
}
