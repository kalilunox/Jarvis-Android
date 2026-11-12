package com.jarvis.core.autonomy;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutonomyAuditLog.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003JG\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011¨\u0006%"}, d2 = {"Lcom/jarvis/core/autonomy/AutonomyAuditEntry;", "", "atMillis", "", "stage", "", "verdictType", "Lcom/jarvis/core/autonomy/AutonomyVerdictType;", "reasonCode", "Lcom/jarvis/core/autonomy/AutonomyReasonCode;", "actionId", "noteSummary", "<init>", "(JLjava/lang/String;Lcom/jarvis/core/autonomy/AutonomyVerdictType;Lcom/jarvis/core/autonomy/AutonomyReasonCode;Ljava/lang/String;Ljava/lang/String;)V", "getAtMillis", "()J", "getStage", "()Ljava/lang/String;", "getVerdictType", "()Lcom/jarvis/core/autonomy/AutonomyVerdictType;", "getReasonCode", "()Lcom/jarvis/core/autonomy/AutonomyReasonCode;", "getActionId", "getNoteSummary", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AutonomyAuditEntry {
    private final String actionId;
    private final long atMillis;
    private final String noteSummary;
    private final AutonomyReasonCode reasonCode;
    private final String stage;
    private final AutonomyVerdictType verdictType;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getAtMillis() {
        return this.atMillis;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStage() {
        return this.stage;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final AutonomyVerdictType getVerdictType() {
        return this.verdictType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AutonomyReasonCode getReasonCode() {
        return this.reasonCode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getActionId() {
        return this.actionId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getNoteSummary() {
        return this.noteSummary;
    }

    public final AutonomyAuditEntry copy(long atMillis, String stage, AutonomyVerdictType verdictType, AutonomyReasonCode reasonCode, String actionId, String noteSummary) {
        Intrinsics.checkNotNullParameter(stage, "stage");
        Intrinsics.checkNotNullParameter(verdictType, "verdictType");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(noteSummary, "noteSummary");
        return new AutonomyAuditEntry(atMillis, stage, verdictType, reasonCode, actionId, noteSummary);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutonomyAuditEntry)) {
            return false;
        }
        AutonomyAuditEntry autonomyAuditEntry = (AutonomyAuditEntry) other;
        return this.atMillis == autonomyAuditEntry.atMillis && Intrinsics.areEqual(this.stage, autonomyAuditEntry.stage) && this.verdictType == autonomyAuditEntry.verdictType && this.reasonCode == autonomyAuditEntry.reasonCode && Intrinsics.areEqual(this.actionId, autonomyAuditEntry.actionId) && Intrinsics.areEqual(this.noteSummary, autonomyAuditEntry.noteSummary);
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.atMillis) * 31) + this.stage.hashCode()) * 31) + this.verdictType.hashCode()) * 31) + this.reasonCode.hashCode()) * 31) + (this.actionId == null ? 0 : this.actionId.hashCode())) * 31) + this.noteSummary.hashCode();
    }

    public String toString() {
        return "AutonomyAuditEntry(atMillis=" + this.atMillis + ", stage=" + this.stage + ", verdictType=" + this.verdictType + ", reasonCode=" + this.reasonCode + ", actionId=" + this.actionId + ", noteSummary=" + this.noteSummary + ")";
    }

    public AutonomyAuditEntry(long atMillis, String stage, AutonomyVerdictType verdictType, AutonomyReasonCode reasonCode, String actionId, String noteSummary) {
        Intrinsics.checkNotNullParameter(stage, "stage");
        Intrinsics.checkNotNullParameter(verdictType, "verdictType");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(noteSummary, "noteSummary");
        this.atMillis = atMillis;
        this.stage = stage;
        this.verdictType = verdictType;
        this.reasonCode = reasonCode;
        this.actionId = actionId;
        this.noteSummary = noteSummary;
    }

    public final long getAtMillis() {
        return this.atMillis;
    }

    public final String getStage() {
        return this.stage;
    }

    public final AutonomyVerdictType getVerdictType() {
        return this.verdictType;
    }

    public final AutonomyReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final String getActionId() {
        return this.actionId;
    }

    public final String getNoteSummary() {
        return this.noteSummary;
    }
}
