package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProactivityState.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J8\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/jarvis/core/proactive/CooldownCheckResult;", "", "allowed", "", "reasonCode", "Lcom/jarvis/core/proactive/ProactiveReasonCode;", "blockedUntilMillis", "", "detail", "", "<init>", "(ZLcom/jarvis/core/proactive/ProactiveReasonCode;Ljava/lang/Long;Ljava/lang/String;)V", "getAllowed", "()Z", "getReasonCode", "()Lcom/jarvis/core/proactive/ProactiveReasonCode;", "getBlockedUntilMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDetail", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(ZLcom/jarvis/core/proactive/ProactiveReasonCode;Ljava/lang/Long;Ljava/lang/String;)Lcom/jarvis/core/proactive/CooldownCheckResult;", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CooldownCheckResult {
    private final boolean allowed;
    private final Long blockedUntilMillis;
    private final String detail;
    private final ProactiveReasonCode reasonCode;

    public static /* synthetic */ CooldownCheckResult copy$default(CooldownCheckResult cooldownCheckResult, boolean z, ProactiveReasonCode proactiveReasonCode, Long l, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = cooldownCheckResult.allowed;
        }
        if ((i & 2) != 0) {
            proactiveReasonCode = cooldownCheckResult.reasonCode;
        }
        if ((i & 4) != 0) {
            l = cooldownCheckResult.blockedUntilMillis;
        }
        if ((i & 8) != 0) {
            str = cooldownCheckResult.detail;
        }
        return cooldownCheckResult.copy(z, proactiveReasonCode, l, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getAllowed() {
        return this.allowed;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getBlockedUntilMillis() {
        return this.blockedUntilMillis;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    public final CooldownCheckResult copy(boolean allowed, ProactiveReasonCode reasonCode, Long blockedUntilMillis, String detail) {
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(detail, "detail");
        return new CooldownCheckResult(allowed, reasonCode, blockedUntilMillis, detail);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CooldownCheckResult)) {
            return false;
        }
        CooldownCheckResult cooldownCheckResult = (CooldownCheckResult) other;
        return this.allowed == cooldownCheckResult.allowed && this.reasonCode == cooldownCheckResult.reasonCode && Intrinsics.areEqual(this.blockedUntilMillis, cooldownCheckResult.blockedUntilMillis) && Intrinsics.areEqual(this.detail, cooldownCheckResult.detail);
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.allowed) * 31) + this.reasonCode.hashCode()) * 31) + (this.blockedUntilMillis == null ? 0 : this.blockedUntilMillis.hashCode())) * 31) + this.detail.hashCode();
    }

    public String toString() {
        return "CooldownCheckResult(allowed=" + this.allowed + ", reasonCode=" + this.reasonCode + ", blockedUntilMillis=" + this.blockedUntilMillis + ", detail=" + this.detail + ")";
    }

    public CooldownCheckResult(boolean allowed, ProactiveReasonCode reasonCode, Long blockedUntilMillis, String detail) {
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(detail, "detail");
        this.allowed = allowed;
        this.reasonCode = reasonCode;
        this.blockedUntilMillis = blockedUntilMillis;
        this.detail = detail;
    }

    public /* synthetic */ CooldownCheckResult(boolean z, ProactiveReasonCode proactiveReasonCode, Long l, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, proactiveReasonCode, (i & 4) != 0 ? null : l, (i & 8) != 0 ? "" : str);
    }

    public final boolean getAllowed() {
        return this.allowed;
    }

    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final Long getBlockedUntilMillis() {
        return this.blockedUntilMillis;
    }

    public final String getDetail() {
        return this.detail;
    }
}
