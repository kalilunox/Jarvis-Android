package com.jarvis.core.autonomy;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionSafetyGateModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/jarvis/core/autonomy/ActionConfirmation;", "", "confirmationId", "", "boundActionRequestId", "boundIntegrityFingerprint", "confirmedAtMillis", "", "expiresAtMillis", "consumed", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZ)V", "getConfirmationId", "()Ljava/lang/String;", "getBoundActionRequestId", "getBoundIntegrityFingerprint", "getConfirmedAtMillis", "()J", "getExpiresAtMillis", "getConsumed", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionConfirmation {
    private final String boundActionRequestId;
    private final String boundIntegrityFingerprint;
    private final String confirmationId;
    private final long confirmedAtMillis;
    private final boolean consumed;
    private final long expiresAtMillis;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getConfirmationId() {
        return this.confirmationId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getBoundActionRequestId() {
        return this.boundActionRequestId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBoundIntegrityFingerprint() {
        return this.boundIntegrityFingerprint;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getConfirmedAtMillis() {
        return this.confirmedAtMillis;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getExpiresAtMillis() {
        return this.expiresAtMillis;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getConsumed() {
        return this.consumed;
    }

    public final ActionConfirmation copy(String confirmationId, String boundActionRequestId, String boundIntegrityFingerprint, long confirmedAtMillis, long expiresAtMillis, boolean consumed) {
        Intrinsics.checkNotNullParameter(confirmationId, "confirmationId");
        Intrinsics.checkNotNullParameter(boundActionRequestId, "boundActionRequestId");
        Intrinsics.checkNotNullParameter(boundIntegrityFingerprint, "boundIntegrityFingerprint");
        return new ActionConfirmation(confirmationId, boundActionRequestId, boundIntegrityFingerprint, confirmedAtMillis, expiresAtMillis, consumed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionConfirmation)) {
            return false;
        }
        ActionConfirmation actionConfirmation = (ActionConfirmation) other;
        return Intrinsics.areEqual(this.confirmationId, actionConfirmation.confirmationId) && Intrinsics.areEqual(this.boundActionRequestId, actionConfirmation.boundActionRequestId) && Intrinsics.areEqual(this.boundIntegrityFingerprint, actionConfirmation.boundIntegrityFingerprint) && this.confirmedAtMillis == actionConfirmation.confirmedAtMillis && this.expiresAtMillis == actionConfirmation.expiresAtMillis && this.consumed == actionConfirmation.consumed;
    }

    public int hashCode() {
        return (((((((((this.confirmationId.hashCode() * 31) + this.boundActionRequestId.hashCode()) * 31) + this.boundIntegrityFingerprint.hashCode()) * 31) + Long.hashCode(this.confirmedAtMillis)) * 31) + Long.hashCode(this.expiresAtMillis)) * 31) + Boolean.hashCode(this.consumed);
    }

    public String toString() {
        return "ActionConfirmation(confirmationId=" + this.confirmationId + ", boundActionRequestId=" + this.boundActionRequestId + ", boundIntegrityFingerprint=" + this.boundIntegrityFingerprint + ", confirmedAtMillis=" + this.confirmedAtMillis + ", expiresAtMillis=" + this.expiresAtMillis + ", consumed=" + this.consumed + ")";
    }

    public ActionConfirmation(String confirmationId, String boundActionRequestId, String boundIntegrityFingerprint, long confirmedAtMillis, long expiresAtMillis, boolean consumed) {
        Intrinsics.checkNotNullParameter(confirmationId, "confirmationId");
        Intrinsics.checkNotNullParameter(boundActionRequestId, "boundActionRequestId");
        Intrinsics.checkNotNullParameter(boundIntegrityFingerprint, "boundIntegrityFingerprint");
        this.confirmationId = confirmationId;
        this.boundActionRequestId = boundActionRequestId;
        this.boundIntegrityFingerprint = boundIntegrityFingerprint;
        this.confirmedAtMillis = confirmedAtMillis;
        this.expiresAtMillis = expiresAtMillis;
        this.consumed = consumed;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ActionConfirmation(String str, String str2, String str3, long j, long j2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        boolean z2;
        if ((i & 32) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        this(str, str2, str3, j, j2, z2);
    }

    public final String getConfirmationId() {
        return this.confirmationId;
    }

    public final String getBoundActionRequestId() {
        return this.boundActionRequestId;
    }

    public final String getBoundIntegrityFingerprint() {
        return this.boundIntegrityFingerprint;
    }

    public final long getConfirmedAtMillis() {
        return this.confirmedAtMillis;
    }

    public final long getExpiresAtMillis() {
        return this.expiresAtMillis;
    }

    public final boolean getConsumed() {
        return this.consumed;
    }
}
