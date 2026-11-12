package com.jarvis.core.common.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolModels.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010-\u001a\u00020\fHÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0012HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u008f\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u00105\u001a\u00020\u00122\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u000208HÖ\u0001J\t\u00109\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0017¨\u0006:"}, d2 = {"Lcom/jarvis/core/common/model/AssistantResponse;", "", "requestId", "", "replyText", "status", "Lcom/jarvis/core/common/model/AssistantResponseStatus;", "intent", "Lcom/jarvis/core/common/model/Intent;", "toolResult", "Lcom/jarvis/core/common/model/ToolResult;", "durationMs", "", "route", "routeReason", "provider", "model", "fallbackUsed", "", "verification", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/AssistantResponseStatus;Lcom/jarvis/core/common/model/Intent;Lcom/jarvis/core/common/model/ToolResult;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getRequestId", "()Ljava/lang/String;", "getReplyText", "getStatus", "()Lcom/jarvis/core/common/model/AssistantResponseStatus;", "getIntent", "()Lcom/jarvis/core/common/model/Intent;", "getToolResult", "()Lcom/jarvis/core/common/model/ToolResult;", "getDurationMs", "()J", "getRoute", "getRouteReason", "getProvider", "getModel", "getFallbackUsed", "()Z", "getVerification", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AssistantResponse {
    private final long durationMs;
    private final boolean fallbackUsed;
    private final Intent intent;
    private final String model;
    private final String provider;
    private final String replyText;
    private final String requestId;
    private final String route;
    private final String routeReason;
    private final AssistantResponseStatus status;
    private final ToolResult toolResult;
    private final String verification;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getVerification() {
        return this.verification;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getReplyText() {
        return this.replyText;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final AssistantResponseStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Intent getIntent() {
        return this.intent;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ToolResult getToolResult() {
        return this.toolResult;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getRoute() {
        return this.route;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getRouteReason() {
        return this.routeReason;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    public final AssistantResponse copy(String requestId, String replyText, AssistantResponseStatus status, Intent intent, ToolResult toolResult, long durationMs, String route, String routeReason, String provider, String model, boolean fallbackUsed, String verification) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(replyText, "replyText");
        Intrinsics.checkNotNullParameter(status, "status");
        return new AssistantResponse(requestId, replyText, status, intent, toolResult, durationMs, route, routeReason, provider, model, fallbackUsed, verification);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssistantResponse)) {
            return false;
        }
        AssistantResponse assistantResponse = (AssistantResponse) other;
        return Intrinsics.areEqual(this.requestId, assistantResponse.requestId) && Intrinsics.areEqual(this.replyText, assistantResponse.replyText) && this.status == assistantResponse.status && Intrinsics.areEqual(this.intent, assistantResponse.intent) && Intrinsics.areEqual(this.toolResult, assistantResponse.toolResult) && this.durationMs == assistantResponse.durationMs && Intrinsics.areEqual(this.route, assistantResponse.route) && Intrinsics.areEqual(this.routeReason, assistantResponse.routeReason) && Intrinsics.areEqual(this.provider, assistantResponse.provider) && Intrinsics.areEqual(this.model, assistantResponse.model) && this.fallbackUsed == assistantResponse.fallbackUsed && Intrinsics.areEqual(this.verification, assistantResponse.verification);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.requestId.hashCode() * 31) + this.replyText.hashCode()) * 31) + this.status.hashCode()) * 31) + (this.intent == null ? 0 : this.intent.hashCode())) * 31) + (this.toolResult == null ? 0 : this.toolResult.hashCode())) * 31) + Long.hashCode(this.durationMs)) * 31) + (this.route == null ? 0 : this.route.hashCode())) * 31) + (this.routeReason == null ? 0 : this.routeReason.hashCode())) * 31) + (this.provider == null ? 0 : this.provider.hashCode())) * 31) + (this.model == null ? 0 : this.model.hashCode())) * 31) + Boolean.hashCode(this.fallbackUsed)) * 31) + (this.verification != null ? this.verification.hashCode() : 0);
    }

    public String toString() {
        return "AssistantResponse(requestId=" + this.requestId + ", replyText=" + this.replyText + ", status=" + this.status + ", intent=" + this.intent + ", toolResult=" + this.toolResult + ", durationMs=" + this.durationMs + ", route=" + this.route + ", routeReason=" + this.routeReason + ", provider=" + this.provider + ", model=" + this.model + ", fallbackUsed=" + this.fallbackUsed + ", verification=" + this.verification + ")";
    }

    public AssistantResponse(String requestId, String replyText, AssistantResponseStatus status, Intent intent, ToolResult toolResult, long durationMs, String route, String routeReason, String provider, String model, boolean fallbackUsed, String verification) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(replyText, "replyText");
        Intrinsics.checkNotNullParameter(status, "status");
        this.requestId = requestId;
        this.replyText = replyText;
        this.status = status;
        this.intent = intent;
        this.toolResult = toolResult;
        this.durationMs = durationMs;
        this.route = route;
        this.routeReason = routeReason;
        this.provider = provider;
        this.model = model;
        this.fallbackUsed = fallbackUsed;
        this.verification = verification;
    }

    public /* synthetic */ AssistantResponse(String str, String str2, AssistantResponseStatus assistantResponseStatus, Intent intent, ToolResult toolResult, long j, String str3, String str4, String str5, String str6, boolean z, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, assistantResponseStatus, (i & 8) != 0 ? null : intent, (i & 16) != 0 ? null : toolResult, (i & 32) != 0 ? 0L : j, (i & 64) != 0 ? null : str3, (i & 128) != 0 ? null : str4, (i & 256) != 0 ? null : str5, (i & 512) != 0 ? null : str6, (i & 1024) != 0 ? false : z, (i & 2048) != 0 ? null : str7);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getReplyText() {
        return this.replyText;
    }

    public final AssistantResponseStatus getStatus() {
        return this.status;
    }

    public final Intent getIntent() {
        return this.intent;
    }

    public final ToolResult getToolResult() {
        return this.toolResult;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final String getRoute() {
        return this.route;
    }

    public final String getRouteReason() {
        return this.routeReason;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getModel() {
        return this.model;
    }

    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    public final String getVerification() {
        return this.verification;
    }
}
