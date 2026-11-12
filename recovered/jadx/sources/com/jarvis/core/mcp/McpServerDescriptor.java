package com.jarvis.core.mcp;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: McpContracts.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/mcp/McpServerDescriptor;", "", "serverId", "", "displayName", "endpointConfigured", "", "tools", "", "Lcom/jarvis/core/mcp/McpToolDescriptor;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;)V", "getServerId", "()Ljava/lang/String;", "getDisplayName", "getEndpointConfigured", "()Z", "getTools", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class McpServerDescriptor {
    private final String displayName;
    private final boolean endpointConfigured;
    private final String serverId;
    private final List<McpToolDescriptor> tools;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ McpServerDescriptor copy$default(McpServerDescriptor mcpServerDescriptor, String str, String str2, boolean z, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mcpServerDescriptor.serverId;
        }
        if ((i & 2) != 0) {
            str2 = mcpServerDescriptor.displayName;
        }
        if ((i & 4) != 0) {
            z = mcpServerDescriptor.endpointConfigured;
        }
        if ((i & 8) != 0) {
            list = mcpServerDescriptor.tools;
        }
        return mcpServerDescriptor.copy(str, str2, z, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getServerId() {
        return this.serverId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getEndpointConfigured() {
        return this.endpointConfigured;
    }

    public final List<McpToolDescriptor> component4() {
        return this.tools;
    }

    public final McpServerDescriptor copy(String serverId, String displayName, boolean endpointConfigured, List<McpToolDescriptor> tools) {
        Intrinsics.checkNotNullParameter(serverId, "serverId");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(tools, "tools");
        return new McpServerDescriptor(serverId, displayName, endpointConfigured, tools);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof McpServerDescriptor)) {
            return false;
        }
        McpServerDescriptor mcpServerDescriptor = (McpServerDescriptor) other;
        return Intrinsics.areEqual(this.serverId, mcpServerDescriptor.serverId) && Intrinsics.areEqual(this.displayName, mcpServerDescriptor.displayName) && this.endpointConfigured == mcpServerDescriptor.endpointConfigured && Intrinsics.areEqual(this.tools, mcpServerDescriptor.tools);
    }

    public int hashCode() {
        return (((((this.serverId.hashCode() * 31) + this.displayName.hashCode()) * 31) + Boolean.hashCode(this.endpointConfigured)) * 31) + this.tools.hashCode();
    }

    public String toString() {
        return "McpServerDescriptor(serverId=" + this.serverId + ", displayName=" + this.displayName + ", endpointConfigured=" + this.endpointConfigured + ", tools=" + this.tools + ")";
    }

    public McpServerDescriptor(String serverId, String displayName, boolean endpointConfigured, List<McpToolDescriptor> tools) {
        Intrinsics.checkNotNullParameter(serverId, "serverId");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(tools, "tools");
        this.serverId = serverId;
        this.displayName = displayName;
        this.endpointConfigured = endpointConfigured;
        this.tools = tools;
        if (!(!StringsKt.isBlank(this.serverId) && this.serverId.length() <= 64)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(this.displayName)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.tools.size() <= 64) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ McpServerDescriptor(String str, String str2, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getServerId() {
        return this.serverId;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final boolean getEndpointConfigured() {
        return this.endpointConfigured;
    }

    public final List<McpToolDescriptor> getTools() {
        return this.tools;
    }
}
