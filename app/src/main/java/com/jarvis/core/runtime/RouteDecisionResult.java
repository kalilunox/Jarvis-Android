package com.jarvis.core.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RouteDecision.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001J\u0013\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016¨\u0006%"}, d2 = {"Lcom/jarvis/core/runtime/RouteDecisionResult;", "", "route", "Lcom/jarvis/core/runtime/RouteDecision;", "reason", "", "skillId", "toolId", "templateReason", "plannerUsed", "", "agentUsed", "<init>", "(Lcom/jarvis/core/runtime/RouteDecision;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getRoute", "()Lcom/jarvis/core/runtime/RouteDecision;", "getReason", "()Ljava/lang/String;", "getSkillId", "getToolId", "getTemplateReason", "getPlannerUsed", "()Z", "getAgentUsed", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RouteDecisionResult {
    private final boolean agentUsed;
    private final boolean plannerUsed;
    private final String reason;
    private final RouteDecision route;
    private final String skillId;
    private final String templateReason;
    private final String toolId;

    public static /* synthetic */ RouteDecisionResult copy$default(RouteDecisionResult routeDecisionResult, RouteDecision routeDecision, String str, String str2, String str3, String str4, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            routeDecision = routeDecisionResult.route;
        }
        if ((i & 2) != 0) {
            str = routeDecisionResult.reason;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = routeDecisionResult.skillId;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = routeDecisionResult.toolId;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = routeDecisionResult.templateReason;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            z = routeDecisionResult.plannerUsed;
        }
        boolean z3 = z;
        if ((i & 64) != 0) {
            z2 = routeDecisionResult.agentUsed;
        }
        return routeDecisionResult.copy(routeDecision, str5, str6, str7, str8, z3, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final RouteDecision getRoute() {
        return this.route;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSkillId() {
        return this.skillId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getTemplateReason() {
        return this.templateReason;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getPlannerUsed() {
        return this.plannerUsed;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getAgentUsed() {
        return this.agentUsed;
    }

    public final RouteDecisionResult copy(RouteDecision route, String reason, String skillId, String toolId, String templateReason, boolean plannerUsed, boolean agentUsed) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new RouteDecisionResult(route, reason, skillId, toolId, templateReason, plannerUsed, agentUsed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RouteDecisionResult)) {
            return false;
        }
        RouteDecisionResult routeDecisionResult = (RouteDecisionResult) other;
        return this.route == routeDecisionResult.route && Intrinsics.areEqual(this.reason, routeDecisionResult.reason) && Intrinsics.areEqual(this.skillId, routeDecisionResult.skillId) && Intrinsics.areEqual(this.toolId, routeDecisionResult.toolId) && Intrinsics.areEqual(this.templateReason, routeDecisionResult.templateReason) && this.plannerUsed == routeDecisionResult.plannerUsed && this.agentUsed == routeDecisionResult.agentUsed;
    }

    public int hashCode() {
        return (((((((((((this.route.hashCode() * 31) + this.reason.hashCode()) * 31) + (this.skillId == null ? 0 : this.skillId.hashCode())) * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + (this.templateReason != null ? this.templateReason.hashCode() : 0)) * 31) + Boolean.hashCode(this.plannerUsed)) * 31) + Boolean.hashCode(this.agentUsed);
    }

    public String toString() {
        return "RouteDecisionResult(route=" + this.route + ", reason=" + this.reason + ", skillId=" + this.skillId + ", toolId=" + this.toolId + ", templateReason=" + this.templateReason + ", plannerUsed=" + this.plannerUsed + ", agentUsed=" + this.agentUsed + ")";
    }

    public RouteDecisionResult(RouteDecision route, String reason, String skillId, String toolId, String templateReason, boolean plannerUsed, boolean agentUsed) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.route = route;
        this.reason = reason;
        this.skillId = skillId;
        this.toolId = toolId;
        this.templateReason = templateReason;
        this.plannerUsed = plannerUsed;
        this.agentUsed = agentUsed;
    }

    public /* synthetic */ RouteDecisionResult(RouteDecision routeDecision, String str, String str2, String str3, String str4, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(routeDecision, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? false : z, (i & 64) != 0 ? false : z2);
    }

    public final RouteDecision getRoute() {
        return this.route;
    }

    public final String getReason() {
        return this.reason;
    }

    public final String getSkillId() {
        return this.skillId;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final String getTemplateReason() {
        return this.templateReason;
    }

    public final boolean getPlannerUsed() {
        return this.plannerUsed;
    }

    public final boolean getAgentUsed() {
        return this.agentUsed;
    }
}
