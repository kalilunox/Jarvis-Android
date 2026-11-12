package com.jarvis.core.agent;

import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.autonomy.AutonomyLevel;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AgentLoopModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J\t\u0010%\u001a\u00020\u000eHÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003Jq\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010(\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\fHÖ\u0001J\t\u0010+\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017¨\u0006,"}, d2 = {"Lcom/jarvis/core/agent/AgentLoopContext;", "", JarvisActivity.LIVE, "", "autonomyLevel", "Lcom/jarvis/core/autonomy/AutonomyLevel;", "confirmedStepIds", "", "", "allowlist", "blocklist", "maxRetriesPerStep", "", "nowMillis", "", "grantedPermissions", "<init>", "(ZLcom/jarvis/core/autonomy/AutonomyLevel;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;IJLjava/util/Set;)V", "getLive", "()Z", "getAutonomyLevel", "()Lcom/jarvis/core/autonomy/AutonomyLevel;", "getConfirmedStepIds", "()Ljava/util/Set;", "getAllowlist", "getBlocklist", "getMaxRetriesPerStep", "()I", "getNowMillis", "()J", "getGrantedPermissions", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AgentLoopContext {
    private final Set<String> allowlist;
    private final AutonomyLevel autonomyLevel;
    private final Set<String> blocklist;
    private final Set<String> confirmedStepIds;
    private final Set<String> grantedPermissions;
    private final boolean live;
    private final int maxRetriesPerStep;
    private final long nowMillis;

    public AgentLoopContext() {
        this(false, null, null, null, null, 0, 0L, null, 255, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getLive() {
        return this.live;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AutonomyLevel getAutonomyLevel() {
        return this.autonomyLevel;
    }

    public final Set<String> component3() {
        return this.confirmedStepIds;
    }

    public final Set<String> component4() {
        return this.allowlist;
    }

    public final Set<String> component5() {
        return this.blocklist;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getMaxRetriesPerStep() {
        return this.maxRetriesPerStep;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getNowMillis() {
        return this.nowMillis;
    }

    public final Set<String> component8() {
        return this.grantedPermissions;
    }

    public final AgentLoopContext copy(boolean live, AutonomyLevel autonomyLevel, Set<String> confirmedStepIds, Set<String> allowlist, Set<String> blocklist, int maxRetriesPerStep, long nowMillis, Set<String> grantedPermissions) {
        Intrinsics.checkNotNullParameter(autonomyLevel, "autonomyLevel");
        Intrinsics.checkNotNullParameter(confirmedStepIds, "confirmedStepIds");
        Intrinsics.checkNotNullParameter(allowlist, "allowlist");
        Intrinsics.checkNotNullParameter(blocklist, "blocklist");
        Intrinsics.checkNotNullParameter(grantedPermissions, "grantedPermissions");
        return new AgentLoopContext(live, autonomyLevel, confirmedStepIds, allowlist, blocklist, maxRetriesPerStep, nowMillis, grantedPermissions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AgentLoopContext)) {
            return false;
        }
        AgentLoopContext agentLoopContext = (AgentLoopContext) other;
        return this.live == agentLoopContext.live && this.autonomyLevel == agentLoopContext.autonomyLevel && Intrinsics.areEqual(this.confirmedStepIds, agentLoopContext.confirmedStepIds) && Intrinsics.areEqual(this.allowlist, agentLoopContext.allowlist) && Intrinsics.areEqual(this.blocklist, agentLoopContext.blocklist) && this.maxRetriesPerStep == agentLoopContext.maxRetriesPerStep && this.nowMillis == agentLoopContext.nowMillis && Intrinsics.areEqual(this.grantedPermissions, agentLoopContext.grantedPermissions);
    }

    public int hashCode() {
        return (((((((((((((Boolean.hashCode(this.live) * 31) + this.autonomyLevel.hashCode()) * 31) + this.confirmedStepIds.hashCode()) * 31) + this.allowlist.hashCode()) * 31) + this.blocklist.hashCode()) * 31) + Integer.hashCode(this.maxRetriesPerStep)) * 31) + Long.hashCode(this.nowMillis)) * 31) + this.grantedPermissions.hashCode();
    }

    public String toString() {
        return "AgentLoopContext(live=" + this.live + ", autonomyLevel=" + this.autonomyLevel + ", confirmedStepIds=" + this.confirmedStepIds + ", allowlist=" + this.allowlist + ", blocklist=" + this.blocklist + ", maxRetriesPerStep=" + this.maxRetriesPerStep + ", nowMillis=" + this.nowMillis + ", grantedPermissions=" + this.grantedPermissions + ")";
    }

    public AgentLoopContext(boolean live, AutonomyLevel autonomyLevel, Set<String> confirmedStepIds, Set<String> allowlist, Set<String> blocklist, int maxRetriesPerStep, long nowMillis, Set<String> grantedPermissions) {
        Intrinsics.checkNotNullParameter(autonomyLevel, "autonomyLevel");
        Intrinsics.checkNotNullParameter(confirmedStepIds, "confirmedStepIds");
        Intrinsics.checkNotNullParameter(allowlist, "allowlist");
        Intrinsics.checkNotNullParameter(blocklist, "blocklist");
        Intrinsics.checkNotNullParameter(grantedPermissions, "grantedPermissions");
        this.live = live;
        this.autonomyLevel = autonomyLevel;
        this.confirmedStepIds = confirmedStepIds;
        this.allowlist = allowlist;
        this.blocklist = blocklist;
        this.maxRetriesPerStep = maxRetriesPerStep;
        this.nowMillis = nowMillis;
        this.grantedPermissions = grantedPermissions;
        int i = this.maxRetriesPerStep;
        boolean z = false;
        if (1 <= i && i < 6) {
            z = true;
        }
        if (z) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ AgentLoopContext(boolean z, AutonomyLevel autonomyLevel, Set set, Set set2, Set set3, int i, long j, Set set4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? AutonomyLevel.SAFE_ONLY : autonomyLevel, (i2 & 4) != 0 ? SetsKt.emptySet() : set, (i2 & 8) != 0 ? SetsKt.emptySet() : set2, (i2 & 16) != 0 ? SetsKt.emptySet() : set3, (i2 & 32) != 0 ? 3 : i, (i2 & 64) != 0 ? System.currentTimeMillis() : j, (i2 & 128) != 0 ? SetsKt.emptySet() : set4);
    }

    public final boolean getLive() {
        return this.live;
    }

    public final AutonomyLevel getAutonomyLevel() {
        return this.autonomyLevel;
    }

    public final Set<String> getConfirmedStepIds() {
        return this.confirmedStepIds;
    }

    public final Set<String> getAllowlist() {
        return this.allowlist;
    }

    public final Set<String> getBlocklist() {
        return this.blocklist;
    }

    public final int getMaxRetriesPerStep() {
        return this.maxRetriesPerStep;
    }

    public final long getNowMillis() {
        return this.nowMillis;
    }

    public final Set<String> getGrantedPermissions() {
        return this.grantedPermissions;
    }
}
