package com.jarvis.core.common.model;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolModels.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JS\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/jarvis/core/common/model/ToolResult;", "", "requestId", "", "toolId", "status", "Lcom/jarvis/core/common/model/ToolResultStatus;", "message", "data", "", "durationMs", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/ToolResultStatus;Ljava/lang/String;Ljava/util/Map;J)V", "getRequestId", "()Ljava/lang/String;", "getToolId", "getStatus", "()Lcom/jarvis/core/common/model/ToolResultStatus;", "getMessage", "getData", "()Ljava/util/Map;", "getDurationMs", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolResult {
    private final Map<String, Object> data;
    private final long durationMs;
    private final String message;
    private final String requestId;
    private final ToolResultStatus status;
    private final String toolId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ToolResult copy$default(ToolResult toolResult, String str, String str2, ToolResultStatus toolResultStatus, String str3, Map map, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = toolResult.requestId;
        }
        if ((i & 2) != 0) {
            str2 = toolResult.toolId;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            toolResultStatus = toolResult.status;
        }
        ToolResultStatus toolResultStatus2 = toolResultStatus;
        if ((i & 8) != 0) {
            str3 = toolResult.message;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            map = toolResult.data;
        }
        Map map2 = map;
        if ((i & 32) != 0) {
            j = toolResult.durationMs;
        }
        return toolResult.copy(str, str4, toolResultStatus2, str5, map2, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ToolResultStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final Map<String, Object> component5() {
        return this.data;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    public final ToolResult copy(String requestId, String toolId, ToolResultStatus status, String message, Map<String, ? extends Object> data, long durationMs) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(data, "data");
        return new ToolResult(requestId, toolId, status, message, data, durationMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolResult)) {
            return false;
        }
        ToolResult toolResult = (ToolResult) other;
        return Intrinsics.areEqual(this.requestId, toolResult.requestId) && Intrinsics.areEqual(this.toolId, toolResult.toolId) && this.status == toolResult.status && Intrinsics.areEqual(this.message, toolResult.message) && Intrinsics.areEqual(this.data, toolResult.data) && this.durationMs == toolResult.durationMs;
    }

    public int hashCode() {
        return (((((((((this.requestId.hashCode() * 31) + this.toolId.hashCode()) * 31) + this.status.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + this.data.hashCode()) * 31) + Long.hashCode(this.durationMs);
    }

    public String toString() {
        return "ToolResult(requestId=" + this.requestId + ", toolId=" + this.toolId + ", status=" + this.status + ", message=" + this.message + ", data=" + this.data + ", durationMs=" + this.durationMs + ")";
    }

    public ToolResult(String requestId, String toolId, ToolResultStatus status, String message, Map<String, ? extends Object> data, long durationMs) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(data, "data");
        this.requestId = requestId;
        this.toolId = toolId;
        this.status = status;
        this.message = message;
        this.data = data;
        this.durationMs = durationMs;
    }

    public /* synthetic */ ToolResult(String str, String str2, ToolResultStatus toolResultStatus, String str3, Map map, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, toolResultStatus, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? 0L : j);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final ToolResultStatus getStatus() {
        return this.status;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Map<String, Object> getData() {
        return this.data;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }
}
