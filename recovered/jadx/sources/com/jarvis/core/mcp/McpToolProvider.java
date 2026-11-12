package com.jarvis.core.mcp;

import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.execution.CatalogToolRegistry;
import com.jarvis.core.execution.ToolAvailability;
import com.jarvis.core.execution.ToolExecutionLocality;
import com.jarvis.core.execution.ToolMetadata;
import com.jarvis.core.execution.ToolProviderSource;
import com.jarvis.core.execution.ToolRegistration;
import com.jarvis.core.execution.ToolRegistryResult;
import com.jarvis.core.plugin.ToolProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: McpContracts.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/mcp/McpToolProvider;", "Lcom/jarvis/core/plugin/ToolProvider;", "server", "Lcom/jarvis/core/mcp/McpServerDescriptor;", "<init>", "(Lcom/jarvis/core/mcp/McpServerDescriptor;)V", "providerId", "", "getProviderId", "()Ljava/lang/String;", "source", "Lcom/jarvis/core/execution/ToolProviderSource;", "getSource", "()Lcom/jarvis/core/execution/ToolProviderSource;", "definitions", "", "Lcom/jarvis/core/common/model/ToolDefinition;", "isAvailable", "", "importDefinitions", "", "catalog", "Lcom/jarvis/core/execution/CatalogToolRegistry;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class McpToolProvider implements ToolProvider {
    private final String providerId;
    private final McpServerDescriptor server;
    private final ToolProviderSource source;

    public McpToolProvider(McpServerDescriptor server) {
        Intrinsics.checkNotNullParameter(server, "server");
        this.server = server;
        this.providerId = "mcp." + this.server.getServerId();
        this.source = ToolProviderSource.MCP;
    }

    @Override // com.jarvis.core.plugin.ToolProvider
    public String getProviderId() {
        return this.providerId;
    }

    @Override // com.jarvis.core.plugin.ToolProvider
    public ToolProviderSource getSource() {
        return this.source;
    }

    @Override // com.jarvis.core.plugin.ToolProvider
    public List<ToolDefinition> definitions() {
        McpToolProvider mcpToolProvider = this;
        Iterable $this$map$iv = mcpToolProvider.server.getTools();
        int i = 10;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            McpToolDescriptor tool = (McpToolDescriptor) item$iv$iv;
            String str = tool.toolId(mcpToolProvider.server.getServerId());
            String name = tool.getName();
            String description = tool.getDescription();
            Iterable $this$map$iv2 = tool.getInputParameterNames();
            Iterable $this$map$iv3 = $this$map$iv;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, i));
            for (Object item$iv$iv2 : $this$map$iv2) {
                String n = (String) item$iv$iv2;
                destination$iv$iv2.add(new ToolParameter(n, n, null, false, null, 20, null));
            }
            destination$iv$iv.add(new ToolDefinition(str, name, description, (List) destination$iv$iv2, tool.getRiskLevel(), tool.getRequiredPermissions(), OnlineRequirement.ONLINE_REQUIRED, null, 128, null));
            i = 10;
            mcpToolProvider = this;
            $this$map$iv = $this$map$iv3;
        }
        return (List) destination$iv$iv;
    }

    public final boolean isAvailable() {
        return this.server.getEndpointConfigured();
    }

    public final int importDefinitions(CatalogToolRegistry catalog) {
        ToolAvailability toolAvailability;
        Intrinsics.checkNotNullParameter(catalog, "catalog");
        int n = 0;
        for (ToolDefinition def : definitions()) {
            ToolProviderSource toolProviderSource = ToolProviderSource.MCP;
            ToolExecutionLocality toolExecutionLocality = ToolExecutionLocality.REMOTE;
            if (this.server.getEndpointConfigured()) {
                toolAvailability = ToolAvailability.NEEDS_ONLINE;
            } else {
                toolAvailability = ToolAvailability.UNAVAILABLE;
            }
            ToolRegistryResult result = catalog.register(new ToolRegistration(def, null, toolProviderSource, toolExecutionLocality, toolAvailability, new ToolMetadata(SetsKt.setOf((Object[]) new String[]{"mcp", "stub"}), "mcp", null, MapsKt.mapOf(TuplesKt.to("mcp_server", this.server.getServerId())), 4, null), null, 66, null));
            if (result.isOk()) {
                n++;
            }
        }
        return n;
    }
}
