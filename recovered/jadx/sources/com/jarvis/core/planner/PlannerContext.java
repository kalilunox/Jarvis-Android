package com.jarvis.core.planner;

import com.jarvis.core.discovery.DiscoveryResult;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionPlanModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 #2\u00020\u0001:\u0001#B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JB\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0007HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0004\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/jarvis/core/planner/PlannerContext;", "", "discovery", "Lcom/jarvis/core/discovery/DiscoveryResult;", "isOnline", "", "maxSteps", "", "preferSingleStep", "defaultWaitBetweenMs", "", "<init>", "(Lcom/jarvis/core/discovery/DiscoveryResult;Ljava/lang/Boolean;IZJ)V", "getDiscovery", "()Lcom/jarvis/core/discovery/DiscoveryResult;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getMaxSteps", "()I", "getPreferSingleStep", "()Z", "getDefaultWaitBetweenMs", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcom/jarvis/core/discovery/DiscoveryResult;Ljava/lang/Boolean;IZJ)Lcom/jarvis/core/planner/PlannerContext;", "equals", "other", "hashCode", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PlannerContext {
    public static final int DEFAULT_MAX_STEPS = 8;
    private final long defaultWaitBetweenMs;
    private final DiscoveryResult discovery;
    private final Boolean isOnline;
    private final int maxSteps;
    private final boolean preferSingleStep;

    public static /* synthetic */ PlannerContext copy$default(PlannerContext plannerContext, DiscoveryResult discoveryResult, Boolean bool, int i, boolean z, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            discoveryResult = plannerContext.discovery;
        }
        if ((i2 & 2) != 0) {
            bool = plannerContext.isOnline;
        }
        Boolean bool2 = bool;
        if ((i2 & 4) != 0) {
            i = plannerContext.maxSteps;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            z = plannerContext.preferSingleStep;
        }
        boolean z2 = z;
        if ((i2 & 16) != 0) {
            j = plannerContext.defaultWaitBetweenMs;
        }
        return plannerContext.copy(discoveryResult, bool2, i3, z2, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DiscoveryResult getDiscovery() {
        return this.discovery;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMaxSteps() {
        return this.maxSteps;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getPreferSingleStep() {
        return this.preferSingleStep;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getDefaultWaitBetweenMs() {
        return this.defaultWaitBetweenMs;
    }

    public final PlannerContext copy(DiscoveryResult discovery, Boolean isOnline, int maxSteps, boolean preferSingleStep, long defaultWaitBetweenMs) {
        Intrinsics.checkNotNullParameter(discovery, "discovery");
        return new PlannerContext(discovery, isOnline, maxSteps, preferSingleStep, defaultWaitBetweenMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlannerContext)) {
            return false;
        }
        PlannerContext plannerContext = (PlannerContext) other;
        return Intrinsics.areEqual(this.discovery, plannerContext.discovery) && Intrinsics.areEqual(this.isOnline, plannerContext.isOnline) && this.maxSteps == plannerContext.maxSteps && this.preferSingleStep == plannerContext.preferSingleStep && this.defaultWaitBetweenMs == plannerContext.defaultWaitBetweenMs;
    }

    public int hashCode() {
        return (((((((this.discovery.hashCode() * 31) + (this.isOnline == null ? 0 : this.isOnline.hashCode())) * 31) + Integer.hashCode(this.maxSteps)) * 31) + Boolean.hashCode(this.preferSingleStep)) * 31) + Long.hashCode(this.defaultWaitBetweenMs);
    }

    public String toString() {
        return "PlannerContext(discovery=" + this.discovery + ", isOnline=" + this.isOnline + ", maxSteps=" + this.maxSteps + ", preferSingleStep=" + this.preferSingleStep + ", defaultWaitBetweenMs=" + this.defaultWaitBetweenMs + ")";
    }

    public PlannerContext(DiscoveryResult discovery, Boolean isOnline, int maxSteps, boolean preferSingleStep, long defaultWaitBetweenMs) {
        Intrinsics.checkNotNullParameter(discovery, "discovery");
        this.discovery = discovery;
        this.isOnline = isOnline;
        this.maxSteps = maxSteps;
        this.preferSingleStep = preferSingleStep;
        this.defaultWaitBetweenMs = defaultWaitBetweenMs;
        int i = this.maxSteps;
        boolean z = false;
        if (!(1 <= i && i < 17)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        long j = this.defaultWaitBetweenMs;
        if (0 <= j && j < 3600001) {
            z = true;
        }
        if (z) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ PlannerContext(DiscoveryResult discoveryResult, Boolean bool, int i, boolean z, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(discoveryResult, (i2 & 2) != 0 ? null : bool, (i2 & 4) != 0 ? 8 : i, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? 0L : j);
    }

    public final DiscoveryResult getDiscovery() {
        return this.discovery;
    }

    public final Boolean isOnline() {
        return this.isOnline;
    }

    public final int getMaxSteps() {
        return this.maxSteps;
    }

    public final boolean getPreferSingleStep() {
        return this.preferSingleStep;
    }

    public final long getDefaultWaitBetweenMs() {
        return this.defaultWaitBetweenMs;
    }
}
