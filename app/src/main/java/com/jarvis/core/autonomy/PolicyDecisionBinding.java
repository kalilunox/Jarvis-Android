package com.jarvis.core.autonomy;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionSafetyGateModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/autonomy/PolicyDecisionBinding;", "", "integrityFingerprint", "", "policyAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "policyRiskAssessment", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/autonomy/ProposedAction;Lcom/jarvis/core/autonomy/ActionRiskAssessment;)V", "getIntegrityFingerprint", "()Ljava/lang/String;", "getPolicyAction", "()Lcom/jarvis/core/autonomy/ProposedAction;", "getPolicyRiskAssessment", "()Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PolicyDecisionBinding {
    private final String integrityFingerprint;
    private final ProposedAction policyAction;
    private final ActionRiskAssessment policyRiskAssessment;

    public static /* synthetic */ PolicyDecisionBinding copy$default(PolicyDecisionBinding policyDecisionBinding, String str, ProposedAction proposedAction, ActionRiskAssessment actionRiskAssessment, int i, Object obj) {
        if ((i & 1) != 0) {
            str = policyDecisionBinding.integrityFingerprint;
        }
        if ((i & 2) != 0) {
            proposedAction = policyDecisionBinding.policyAction;
        }
        if ((i & 4) != 0) {
            actionRiskAssessment = policyDecisionBinding.policyRiskAssessment;
        }
        return policyDecisionBinding.copy(str, proposedAction, actionRiskAssessment);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getIntegrityFingerprint() {
        return this.integrityFingerprint;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProposedAction getPolicyAction() {
        return this.policyAction;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ActionRiskAssessment getPolicyRiskAssessment() {
        return this.policyRiskAssessment;
    }

    public final PolicyDecisionBinding copy(String integrityFingerprint, ProposedAction policyAction, ActionRiskAssessment policyRiskAssessment) {
        Intrinsics.checkNotNullParameter(integrityFingerprint, "integrityFingerprint");
        Intrinsics.checkNotNullParameter(policyAction, "policyAction");
        Intrinsics.checkNotNullParameter(policyRiskAssessment, "policyRiskAssessment");
        return new PolicyDecisionBinding(integrityFingerprint, policyAction, policyRiskAssessment);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PolicyDecisionBinding)) {
            return false;
        }
        PolicyDecisionBinding policyDecisionBinding = (PolicyDecisionBinding) other;
        return Intrinsics.areEqual(this.integrityFingerprint, policyDecisionBinding.integrityFingerprint) && Intrinsics.areEqual(this.policyAction, policyDecisionBinding.policyAction) && Intrinsics.areEqual(this.policyRiskAssessment, policyDecisionBinding.policyRiskAssessment);
    }

    public int hashCode() {
        return (((this.integrityFingerprint.hashCode() * 31) + this.policyAction.hashCode()) * 31) + this.policyRiskAssessment.hashCode();
    }

    public String toString() {
        return "PolicyDecisionBinding(integrityFingerprint=" + this.integrityFingerprint + ", policyAction=" + this.policyAction + ", policyRiskAssessment=" + this.policyRiskAssessment + ")";
    }

    public PolicyDecisionBinding(String integrityFingerprint, ProposedAction policyAction, ActionRiskAssessment policyRiskAssessment) {
        Intrinsics.checkNotNullParameter(integrityFingerprint, "integrityFingerprint");
        Intrinsics.checkNotNullParameter(policyAction, "policyAction");
        Intrinsics.checkNotNullParameter(policyRiskAssessment, "policyRiskAssessment");
        this.integrityFingerprint = integrityFingerprint;
        this.policyAction = policyAction;
        this.policyRiskAssessment = policyRiskAssessment;
    }

    public final String getIntegrityFingerprint() {
        return this.integrityFingerprint;
    }

    public final ProposedAction getPolicyAction() {
        return this.policyAction;
    }

    public final ActionRiskAssessment getPolicyRiskAssessment() {
        return this.policyRiskAssessment;
    }
}
