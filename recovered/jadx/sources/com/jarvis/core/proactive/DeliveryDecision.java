package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PhoneStateSnapshot.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/proactive/DeliveryDecision;", "", "mode", "Lcom/jarvis/core/proactive/DeliveryMode;", "reason", "Lcom/jarvis/core/proactive/DeliveryReason;", "<init>", "(Lcom/jarvis/core/proactive/DeliveryMode;Lcom/jarvis/core/proactive/DeliveryReason;)V", "getMode", "()Lcom/jarvis/core/proactive/DeliveryMode;", "getReason", "()Lcom/jarvis/core/proactive/DeliveryReason;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DeliveryDecision {
    private final DeliveryMode mode;
    private final DeliveryReason reason;

    public static /* synthetic */ DeliveryDecision copy$default(DeliveryDecision deliveryDecision, DeliveryMode deliveryMode, DeliveryReason deliveryReason, int i, Object obj) {
        if ((i & 1) != 0) {
            deliveryMode = deliveryDecision.mode;
        }
        if ((i & 2) != 0) {
            deliveryReason = deliveryDecision.reason;
        }
        return deliveryDecision.copy(deliveryMode, deliveryReason);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DeliveryMode getMode() {
        return this.mode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final DeliveryReason getReason() {
        return this.reason;
    }

    public final DeliveryDecision copy(DeliveryMode mode, DeliveryReason reason) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new DeliveryDecision(mode, reason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeliveryDecision)) {
            return false;
        }
        DeliveryDecision deliveryDecision = (DeliveryDecision) other;
        return this.mode == deliveryDecision.mode && this.reason == deliveryDecision.reason;
    }

    public int hashCode() {
        return (this.mode.hashCode() * 31) + this.reason.hashCode();
    }

    public String toString() {
        return "DeliveryDecision(mode=" + this.mode + ", reason=" + this.reason + ")";
    }

    public DeliveryDecision(DeliveryMode mode, DeliveryReason reason) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.mode = mode;
        this.reason = reason;
    }

    public final DeliveryMode getMode() {
        return this.mode;
    }

    public final DeliveryReason getReason() {
        return this.reason;
    }
}
