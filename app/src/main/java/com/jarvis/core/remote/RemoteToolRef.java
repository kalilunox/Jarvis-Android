package com.jarvis.core.remote;

import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.execution.ToolProviderSource;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoteDeviceModels.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/remote/RemoteToolRef;", "", "deviceId", "", "toolId", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "source", "Lcom/jarvis/core/execution/ToolProviderSource;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/ToolDefinition;Lcom/jarvis/core/execution/ToolProviderSource;)V", "getDeviceId", "()Ljava/lang/String;", "getToolId", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "getSource", "()Lcom/jarvis/core/execution/ToolProviderSource;", "registryId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RemoteToolRef {
    private final ToolDefinition definition;
    private final String deviceId;
    private final ToolProviderSource source;
    private final String toolId;

    public static /* synthetic */ RemoteToolRef copy$default(RemoteToolRef remoteToolRef, String str, String str2, ToolDefinition toolDefinition, ToolProviderSource toolProviderSource, int i, Object obj) {
        if ((i & 1) != 0) {
            str = remoteToolRef.deviceId;
        }
        if ((i & 2) != 0) {
            str2 = remoteToolRef.toolId;
        }
        if ((i & 4) != 0) {
            toolDefinition = remoteToolRef.definition;
        }
        if ((i & 8) != 0) {
            toolProviderSource = remoteToolRef.source;
        }
        return remoteToolRef.copy(str, str2, toolDefinition, toolProviderSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ToolProviderSource getSource() {
        return this.source;
    }

    public final RemoteToolRef copy(String deviceId, String toolId, ToolDefinition definition, ToolProviderSource source) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(source, "source");
        return new RemoteToolRef(deviceId, toolId, definition, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RemoteToolRef)) {
            return false;
        }
        RemoteToolRef remoteToolRef = (RemoteToolRef) other;
        return Intrinsics.areEqual(this.deviceId, remoteToolRef.deviceId) && Intrinsics.areEqual(this.toolId, remoteToolRef.toolId) && Intrinsics.areEqual(this.definition, remoteToolRef.definition) && this.source == remoteToolRef.source;
    }

    public int hashCode() {
        return (((((this.deviceId.hashCode() * 31) + this.toolId.hashCode()) * 31) + this.definition.hashCode()) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "RemoteToolRef(deviceId=" + this.deviceId + ", toolId=" + this.toolId + ", definition=" + this.definition + ", source=" + this.source + ")";
    }

    public RemoteToolRef(String deviceId, String toolId, ToolDefinition definition, ToolProviderSource source) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(source, "source");
        this.deviceId = deviceId;
        this.toolId = toolId;
        this.definition = definition;
        this.source = source;
    }

    public /* synthetic */ RemoteToolRef(String str, String str2, ToolDefinition toolDefinition, ToolProviderSource toolProviderSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, toolDefinition, (i & 8) != 0 ? ToolProviderSource.MCP : toolProviderSource);
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final ToolDefinition getDefinition() {
        return this.definition;
    }

    public final ToolProviderSource getSource() {
        return this.source;
    }

    public final String registryId() {
        return "remote." + this.deviceId + "." + this.toolId;
    }
}
