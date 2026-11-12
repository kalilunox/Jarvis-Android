package com.jarvis.core.autonomy;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionSafetyGateModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0003JC\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0001J\u0013\u0010\"\u001a\u00020\u00192\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006'"}, d2 = {"Lcom/jarvis/core/autonomy/ActionSafetyGateResult;", "", "status", "Lcom/jarvis/core/autonomy/ActionSafetyGateStatus;", "failureReason", "Lcom/jarvis/core/autonomy/ActionSafetyFailureReason;", "reason", "", "verdict", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "notes", "", "<init>", "(Lcom/jarvis/core/autonomy/ActionSafetyGateStatus;Lcom/jarvis/core/autonomy/ActionSafetyFailureReason;Ljava/lang/String;Lcom/jarvis/core/autonomy/AutonomyVerdict;Ljava/util/List;)V", "getStatus", "()Lcom/jarvis/core/autonomy/ActionSafetyGateStatus;", "getFailureReason", "()Lcom/jarvis/core/autonomy/ActionSafetyFailureReason;", "getReason", "()Ljava/lang/String;", "getVerdict", "()Lcom/jarvis/core/autonomy/AutonomyVerdict;", "getNotes", "()Ljava/util/List;", "passed", "", "getPassed", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionSafetyGateResult {
    private final ActionSafetyFailureReason failureReason;
    private final List<String> notes;
    private final String reason;
    private final ActionSafetyGateStatus status;
    private final AutonomyVerdict verdict;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionSafetyGateResult copy$default(ActionSafetyGateResult actionSafetyGateResult, ActionSafetyGateStatus actionSafetyGateStatus, ActionSafetyFailureReason actionSafetyFailureReason, String str, AutonomyVerdict autonomyVerdict, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            actionSafetyGateStatus = actionSafetyGateResult.status;
        }
        if ((i & 2) != 0) {
            actionSafetyFailureReason = actionSafetyGateResult.failureReason;
        }
        ActionSafetyFailureReason actionSafetyFailureReason2 = actionSafetyFailureReason;
        if ((i & 4) != 0) {
            str = actionSafetyGateResult.reason;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            autonomyVerdict = actionSafetyGateResult.verdict;
        }
        AutonomyVerdict autonomyVerdict2 = autonomyVerdict;
        if ((i & 16) != 0) {
            list = actionSafetyGateResult.notes;
        }
        return actionSafetyGateResult.copy(actionSafetyGateStatus, actionSafetyFailureReason2, str2, autonomyVerdict2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ActionSafetyGateStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ActionSafetyFailureReason getFailureReason() {
        return this.failureReason;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AutonomyVerdict getVerdict() {
        return this.verdict;
    }

    public final List<String> component5() {
        return this.notes;
    }

    public final ActionSafetyGateResult copy(ActionSafetyGateStatus status, ActionSafetyFailureReason failureReason, String reason, AutonomyVerdict verdict, List<String> notes) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(verdict, "verdict");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new ActionSafetyGateResult(status, failureReason, reason, verdict, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionSafetyGateResult)) {
            return false;
        }
        ActionSafetyGateResult actionSafetyGateResult = (ActionSafetyGateResult) other;
        return this.status == actionSafetyGateResult.status && this.failureReason == actionSafetyGateResult.failureReason && Intrinsics.areEqual(this.reason, actionSafetyGateResult.reason) && Intrinsics.areEqual(this.verdict, actionSafetyGateResult.verdict) && Intrinsics.areEqual(this.notes, actionSafetyGateResult.notes);
    }

    public int hashCode() {
        return (((((((this.status.hashCode() * 31) + (this.failureReason == null ? 0 : this.failureReason.hashCode())) * 31) + this.reason.hashCode()) * 31) + this.verdict.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "ActionSafetyGateResult(status=" + this.status + ", failureReason=" + this.failureReason + ", reason=" + this.reason + ", verdict=" + this.verdict + ", notes=" + this.notes + ")";
    }

    public ActionSafetyGateResult(ActionSafetyGateStatus status, ActionSafetyFailureReason failureReason, String reason, AutonomyVerdict verdict, List<String> notes) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(verdict, "verdict");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.status = status;
        this.failureReason = failureReason;
        this.reason = reason;
        this.verdict = verdict;
        this.notes = notes;
        if (this.status == ActionSafetyGateStatus.PASS) {
            if (!(this.failureReason == null)) {
                throw new IllegalArgumentException("PASS must not carry failureReason".toString());
            }
        }
        if (this.status == ActionSafetyGateStatus.BLOCK) {
            if (!(this.failureReason != null)) {
                throw new IllegalArgumentException("BLOCK requires failureReason".toString());
            }
        }
        if (this.verdict.getType() != AutonomyVerdictType.DENY && this.verdict.getType() != AutonomyVerdictType.NO_ACTION) {
            return;
        }
        if (this.status == ActionSafetyGateStatus.BLOCK) {
        } else {
            throw new IllegalArgumentException("DENY/NO_ACTION verdict cannot PASS the safety gate".toString());
        }
    }

    public /* synthetic */ ActionSafetyGateResult(ActionSafetyGateStatus actionSafetyGateStatus, ActionSafetyFailureReason actionSafetyFailureReason, String str, AutonomyVerdict autonomyVerdict, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(actionSafetyGateStatus, (i & 2) != 0 ? null : actionSafetyFailureReason, (i & 4) != 0 ? "" : str, autonomyVerdict, (i & 16) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final ActionSafetyGateStatus getStatus() {
        return this.status;
    }

    public final ActionSafetyFailureReason getFailureReason() {
        return this.failureReason;
    }

    public final String getReason() {
        return this.reason;
    }

    public final AutonomyVerdict getVerdict() {
        return this.verdict;
    }

    public final List<String> getNotes() {
        return this.notes;
    }

    public final boolean getPassed() {
        return this.status == ActionSafetyGateStatus.PASS;
    }
}
