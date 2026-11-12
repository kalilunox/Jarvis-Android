package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AIRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003JI\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006\""}, d2 = {"Lcom/jarvis/brain/aiprovider/AIRouteAttempt;", "", "attempt", "", "providerId", "", "modelId", "reason", "latencyMs", "", "error", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getAttempt", "()I", "getProviderId", "()Ljava/lang/String;", "getModelId", "getReason", "getLatencyMs", "()J", "getError", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AIRouteAttempt {
    private final int attempt;
    private final String error;
    private final long latencyMs;
    private final String modelId;
    private final String providerId;
    private final String reason;

    public static /* synthetic */ AIRouteAttempt copy$default(AIRouteAttempt aIRouteAttempt, int i, String str, String str2, String str3, long j, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = aIRouteAttempt.attempt;
        }
        if ((i2 & 2) != 0) {
            str = aIRouteAttempt.providerId;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = aIRouteAttempt.modelId;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = aIRouteAttempt.reason;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            j = aIRouteAttempt.latencyMs;
        }
        long j2 = j;
        if ((i2 & 32) != 0) {
            str4 = aIRouteAttempt.error;
        }
        return aIRouteAttempt.copy(i, str5, str6, str7, j2, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAttempt() {
        return this.attempt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLatencyMs() {
        return this.latencyMs;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final AIRouteAttempt copy(int attempt, String providerId, String modelId, String reason, long latencyMs, String error) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new AIRouteAttempt(attempt, providerId, modelId, reason, latencyMs, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIRouteAttempt)) {
            return false;
        }
        AIRouteAttempt aIRouteAttempt = (AIRouteAttempt) other;
        return this.attempt == aIRouteAttempt.attempt && Intrinsics.areEqual(this.providerId, aIRouteAttempt.providerId) && Intrinsics.areEqual(this.modelId, aIRouteAttempt.modelId) && Intrinsics.areEqual(this.reason, aIRouteAttempt.reason) && this.latencyMs == aIRouteAttempt.latencyMs && Intrinsics.areEqual(this.error, aIRouteAttempt.error);
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.attempt) * 31) + this.providerId.hashCode()) * 31) + (this.modelId == null ? 0 : this.modelId.hashCode())) * 31) + this.reason.hashCode()) * 31) + Long.hashCode(this.latencyMs)) * 31) + (this.error != null ? this.error.hashCode() : 0);
    }

    public String toString() {
        return "AIRouteAttempt(attempt=" + this.attempt + ", providerId=" + this.providerId + ", modelId=" + this.modelId + ", reason=" + this.reason + ", latencyMs=" + this.latencyMs + ", error=" + this.error + ")";
    }

    public AIRouteAttempt(int attempt, String providerId, String modelId, String reason, long latencyMs, String error) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.attempt = attempt;
        this.providerId = providerId;
        this.modelId = modelId;
        this.reason = reason;
        this.latencyMs = latencyMs;
        this.error = error;
    }

    public final int getAttempt() {
        return this.attempt;
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final String getModelId() {
        return this.modelId;
    }

    public final String getReason() {
        return this.reason;
    }

    public final long getLatencyMs() {
        return this.latencyMs;
    }

    public final String getError() {
        return this.error;
    }
}
