package com.jarvis.core.common.model;

import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolModels.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0003J=\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/common/model/ToolCall;", "", "id", "", "requestId", "toolId", "arguments", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getId", "()Ljava/lang/String;", "getRequestId", "getToolId", "getArguments", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolCall {
    private final Map<String, Object> arguments;
    private final String id;
    private final String requestId;
    private final String toolId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ToolCall copy$default(ToolCall toolCall, String str, String str2, String str3, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = toolCall.id;
        }
        if ((i & 2) != 0) {
            str2 = toolCall.requestId;
        }
        if ((i & 4) != 0) {
            str3 = toolCall.toolId;
        }
        if ((i & 8) != 0) {
            map = toolCall.arguments;
        }
        return toolCall.copy(str, str2, str3, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, Object> component4() {
        return this.arguments;
    }

    public final ToolCall copy(String id, String requestId, String toolId, Map<String, ? extends Object> arguments) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        return new ToolCall(id, requestId, toolId, arguments);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolCall)) {
            return false;
        }
        ToolCall toolCall = (ToolCall) other;
        return Intrinsics.areEqual(this.id, toolCall.id) && Intrinsics.areEqual(this.requestId, toolCall.requestId) && Intrinsics.areEqual(this.toolId, toolCall.toolId) && Intrinsics.areEqual(this.arguments, toolCall.arguments);
    }

    public int hashCode() {
        return (((((this.id.hashCode() * 31) + this.requestId.hashCode()) * 31) + this.toolId.hashCode()) * 31) + this.arguments.hashCode();
    }

    public String toString() {
        return "ToolCall(id=" + this.id + ", requestId=" + this.requestId + ", toolId=" + this.toolId + ", arguments=" + this.arguments + ")";
    }

    public ToolCall(String id, String requestId, String toolId, Map<String, ? extends Object> arguments) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.id = id;
        this.requestId = requestId;
        this.toolId = toolId;
        this.arguments = arguments;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ToolCall(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(str, "toString(...)");
        }
        this(str, str2, str3, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    public final String getId() {
        return this.id;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, Object> getArguments() {
        return this.arguments;
    }
}
