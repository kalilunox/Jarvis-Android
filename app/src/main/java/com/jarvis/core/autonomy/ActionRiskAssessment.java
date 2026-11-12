package com.jarvis.core.autonomy;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionRiskAssessment.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\fHÆ\u0003JK\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006'"}, d2 = {"Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "", "riskClass", "Lcom/jarvis/core/autonomy/ActionRiskClass;", "reasonCode", "Lcom/jarvis/core/autonomy/ActionRiskReasonCode;", "reason", "", "factors", "", "actionId", "kind", "Lcom/jarvis/core/autonomy/ActionKind;", "<init>", "(Lcom/jarvis/core/autonomy/ActionRiskClass;Lcom/jarvis/core/autonomy/ActionRiskReasonCode;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/jarvis/core/autonomy/ActionKind;)V", "getRiskClass", "()Lcom/jarvis/core/autonomy/ActionRiskClass;", "getReasonCode", "()Lcom/jarvis/core/autonomy/ActionRiskReasonCode;", "getReason", "()Ljava/lang/String;", "getFactors", "()Ljava/util/List;", "getActionId", "getKind", "()Lcom/jarvis/core/autonomy/ActionKind;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionRiskAssessment {
    private final String actionId;
    private final List<String> factors;
    private final ActionKind kind;
    private final String reason;
    private final ActionRiskReasonCode reasonCode;
    private final ActionRiskClass riskClass;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionRiskAssessment copy$default(ActionRiskAssessment actionRiskAssessment, ActionRiskClass actionRiskClass, ActionRiskReasonCode actionRiskReasonCode, String str, List list, String str2, ActionKind actionKind, int i, Object obj) {
        if ((i & 1) != 0) {
            actionRiskClass = actionRiskAssessment.riskClass;
        }
        if ((i & 2) != 0) {
            actionRiskReasonCode = actionRiskAssessment.reasonCode;
        }
        ActionRiskReasonCode actionRiskReasonCode2 = actionRiskReasonCode;
        if ((i & 4) != 0) {
            str = actionRiskAssessment.reason;
        }
        String str3 = str;
        if ((i & 8) != 0) {
            list = actionRiskAssessment.factors;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            str2 = actionRiskAssessment.actionId;
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            actionKind = actionRiskAssessment.kind;
        }
        return actionRiskAssessment.copy(actionRiskClass, actionRiskReasonCode2, str3, list2, str4, actionKind);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ActionRiskClass getRiskClass() {
        return this.riskClass;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ActionRiskReasonCode getReasonCode() {
        return this.reasonCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    public final List<String> component4() {
        return this.factors;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getActionId() {
        return this.actionId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final ActionKind getKind() {
        return this.kind;
    }

    public final ActionRiskAssessment copy(ActionRiskClass riskClass, ActionRiskReasonCode reasonCode, String reason, List<String> factors, String actionId, ActionKind kind) {
        Intrinsics.checkNotNullParameter(riskClass, "riskClass");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(factors, "factors");
        Intrinsics.checkNotNullParameter(actionId, "actionId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        return new ActionRiskAssessment(riskClass, reasonCode, reason, factors, actionId, kind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionRiskAssessment)) {
            return false;
        }
        ActionRiskAssessment actionRiskAssessment = (ActionRiskAssessment) other;
        return this.riskClass == actionRiskAssessment.riskClass && this.reasonCode == actionRiskAssessment.reasonCode && Intrinsics.areEqual(this.reason, actionRiskAssessment.reason) && Intrinsics.areEqual(this.factors, actionRiskAssessment.factors) && Intrinsics.areEqual(this.actionId, actionRiskAssessment.actionId) && this.kind == actionRiskAssessment.kind;
    }

    public int hashCode() {
        return (((((((((this.riskClass.hashCode() * 31) + this.reasonCode.hashCode()) * 31) + this.reason.hashCode()) * 31) + this.factors.hashCode()) * 31) + this.actionId.hashCode()) * 31) + this.kind.hashCode();
    }

    public String toString() {
        return "ActionRiskAssessment(riskClass=" + this.riskClass + ", reasonCode=" + this.reasonCode + ", reason=" + this.reason + ", factors=" + this.factors + ", actionId=" + this.actionId + ", kind=" + this.kind + ")";
    }

    public ActionRiskAssessment(ActionRiskClass riskClass, ActionRiskReasonCode reasonCode, String reason, List<String> factors, String actionId, ActionKind kind) {
        Intrinsics.checkNotNullParameter(riskClass, "riskClass");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(factors, "factors");
        Intrinsics.checkNotNullParameter(actionId, "actionId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        this.riskClass = riskClass;
        this.reasonCode = reasonCode;
        this.reason = reason;
        this.factors = factors;
        this.actionId = actionId;
        this.kind = kind;
    }

    public /* synthetic */ ActionRiskAssessment(ActionRiskClass actionRiskClass, ActionRiskReasonCode actionRiskReasonCode, String str, List list, String str2, ActionKind actionKind, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(actionRiskClass, actionRiskReasonCode, (i & 4) != 0 ? "" : str, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? "" : str2, (i & 32) != 0 ? ActionKind.UNKNOWN : actionKind);
    }

    public final ActionRiskClass getRiskClass() {
        return this.riskClass;
    }

    public final ActionRiskReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final String getReason() {
        return this.reason;
    }

    public final List<String> getFactors() {
        return this.factors;
    }

    public final String getActionId() {
        return this.actionId;
    }

    public final ActionKind getKind() {
        return this.kind;
    }
}
