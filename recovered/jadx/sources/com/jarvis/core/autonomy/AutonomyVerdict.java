package com.jarvis.core.autonomy;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutonomyModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0003JC\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/jarvis/core/autonomy/AutonomyVerdict;", "", "type", "Lcom/jarvis/core/autonomy/AutonomyVerdictType;", "reasonCode", "Lcom/jarvis/core/autonomy/AutonomyReasonCode;", "reason", "", "actionRequest", "Lcom/jarvis/core/autonomy/ActionRequest;", "notes", "", "<init>", "(Lcom/jarvis/core/autonomy/AutonomyVerdictType;Lcom/jarvis/core/autonomy/AutonomyReasonCode;Ljava/lang/String;Lcom/jarvis/core/autonomy/ActionRequest;Ljava/util/List;)V", "getType", "()Lcom/jarvis/core/autonomy/AutonomyVerdictType;", "getReasonCode", "()Lcom/jarvis/core/autonomy/AutonomyReasonCode;", "getReason", "()Ljava/lang/String;", "getActionRequest", "()Lcom/jarvis/core/autonomy/ActionRequest;", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AutonomyVerdict {
    private final ActionRequest actionRequest;
    private final List<String> notes;
    private final String reason;
    private final AutonomyReasonCode reasonCode;
    private final AutonomyVerdictType type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AutonomyVerdict copy$default(AutonomyVerdict autonomyVerdict, AutonomyVerdictType autonomyVerdictType, AutonomyReasonCode autonomyReasonCode, String str, ActionRequest actionRequest, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            autonomyVerdictType = autonomyVerdict.type;
        }
        if ((i & 2) != 0) {
            autonomyReasonCode = autonomyVerdict.reasonCode;
        }
        AutonomyReasonCode autonomyReasonCode2 = autonomyReasonCode;
        if ((i & 4) != 0) {
            str = autonomyVerdict.reason;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            actionRequest = autonomyVerdict.actionRequest;
        }
        ActionRequest actionRequest2 = actionRequest;
        if ((i & 16) != 0) {
            list = autonomyVerdict.notes;
        }
        return autonomyVerdict.copy(autonomyVerdictType, autonomyReasonCode2, str2, actionRequest2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AutonomyVerdictType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AutonomyReasonCode getReasonCode() {
        return this.reasonCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ActionRequest getActionRequest() {
        return this.actionRequest;
    }

    public final List<String> component5() {
        return this.notes;
    }

    public final AutonomyVerdict copy(AutonomyVerdictType type, AutonomyReasonCode reasonCode, String reason, ActionRequest actionRequest, List<String> notes) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new AutonomyVerdict(type, reasonCode, reason, actionRequest, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutonomyVerdict)) {
            return false;
        }
        AutonomyVerdict autonomyVerdict = (AutonomyVerdict) other;
        return this.type == autonomyVerdict.type && this.reasonCode == autonomyVerdict.reasonCode && Intrinsics.areEqual(this.reason, autonomyVerdict.reason) && Intrinsics.areEqual(this.actionRequest, autonomyVerdict.actionRequest) && Intrinsics.areEqual(this.notes, autonomyVerdict.notes);
    }

    public int hashCode() {
        return (((((((this.type.hashCode() * 31) + this.reasonCode.hashCode()) * 31) + this.reason.hashCode()) * 31) + (this.actionRequest == null ? 0 : this.actionRequest.hashCode())) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "AutonomyVerdict(type=" + this.type + ", reasonCode=" + this.reasonCode + ", reason=" + this.reason + ", actionRequest=" + this.actionRequest + ", notes=" + this.notes + ")";
    }

    public AutonomyVerdict(AutonomyVerdictType type, AutonomyReasonCode reasonCode, String reason, ActionRequest actionRequest, List<String> notes) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.type = type;
        this.reasonCode = reasonCode;
        this.reason = reason;
        this.actionRequest = actionRequest;
        this.notes = notes;
        if (this.type == AutonomyVerdictType.ALLOW) {
            if (!(this.actionRequest != null)) {
                throw new IllegalArgumentException("ALLOW requires actionRequest".toString());
            }
        }
        if (this.type != AutonomyVerdictType.NO_ACTION) {
            return;
        }
        if (!(this.actionRequest == null)) {
            throw new IllegalArgumentException("NO_ACTION must not carry actionRequest".toString());
        }
    }

    public /* synthetic */ AutonomyVerdict(AutonomyVerdictType autonomyVerdictType, AutonomyReasonCode autonomyReasonCode, String str, ActionRequest actionRequest, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(autonomyVerdictType, autonomyReasonCode, (i & 4) != 0 ? "" : str, (i & 8) != 0 ? null : actionRequest, (i & 16) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final AutonomyVerdictType getType() {
        return this.type;
    }

    public final AutonomyReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final String getReason() {
        return this.reason;
    }

    public final ActionRequest getActionRequest() {
        return this.actionRequest;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
