package com.jarvis.core.mcp;

import com.jarvis.core.execution.CatalogToolRegistry;
import com.jarvis.core.plugin.ToolProvider;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: McpContracts.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0006J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0010J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0014R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/mcp/McpRegistry;", "", "<init>", "()V", "byId", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/mcp/McpToolProvider;", "Lkotlin/collections/LinkedHashMap;", "register", "server", "Lcom/jarvis/core/mcp/McpServerDescriptor;", "unregister", "serverId", "find", "all", "", "providers", "Lcom/jarvis/core/plugin/ToolProvider;", "importAllDefinitions", "", "catalog", "Lcom/jarvis/core/execution/CatalogToolRegistry;", "size", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class McpRegistry {
    private final LinkedHashMap<String, McpToolProvider> byId = new LinkedHashMap<>();

    public final McpToolProvider register(McpServerDescriptor server) {
        Intrinsics.checkNotNullParameter(server, "server");
        McpToolProvider provider = new McpToolProvider(server);
        this.byId.put(server.getServerId(), provider);
        return provider;
    }

    public final McpToolProvider unregister(String serverId) {
        Intrinsics.checkNotNullParameter(serverId, "serverId");
        return this.byId.remove(serverId);
    }

    public final McpToolProvider find(String serverId) {
        Intrinsics.checkNotNullParameter(serverId, "serverId");
        return this.byId.get(serverId);
    }

    public final List<McpToolProvider> all() {
        Collection<McpToolProvider> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final List<ToolProvider> providers() {
        Collection<McpToolProvider> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final int importAllDefinitions(CatalogToolRegistry catalog) {
        Intrinsics.checkNotNullParameter(catalog, "catalog");
        Collection<McpToolProvider> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        int iImportDefinitions = 0;
        for (McpToolProvider it : collectionValues) {
            iImportDefinitions += it.importDefinitions(catalog);
        }
        return iImportDefinitions;
    }

    public final int size() {
        return this.byId.size();
    }
}
