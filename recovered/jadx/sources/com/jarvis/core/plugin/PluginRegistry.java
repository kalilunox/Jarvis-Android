package com.jarvis.core.plugin;

import com.jarvis.core.common.model.ToolDefinition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PluginContracts.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0006J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u0006\u0010\u0016\u001a\u00020\u0017R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/plugin/PluginRegistry;", "", "<init>", "()V", "byId", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/plugin/Plugin;", "Lkotlin/collections/LinkedHashMap;", "register", "", "plugin", "unregister", "id", "find", "all", "", "started", "toolProviders", "Lcom/jarvis/core/plugin/ToolProvider;", "allDefinitions", "Lcom/jarvis/core/common/model/ToolDefinition;", "size", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PluginRegistry {
    private final LinkedHashMap<String, Plugin> byId = new LinkedHashMap<>();

    public final void register(Plugin plugin) {
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        if (StringsKt.isBlank(plugin.getManifest().getId())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this.byId.put(plugin.getManifest().getId(), plugin);
    }

    public final Plugin unregister(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.byId.remove(id);
    }

    public final Plugin find(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.byId.get(id);
    }

    public final List<Plugin> all() {
        Collection<Plugin> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final List<Plugin> started() {
        Iterable iterableValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Plugin it = (Plugin) element$iv$iv;
            if (it.getState() == PluginLifecycleState.STARTED) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<ToolProvider> toolProviders() {
        Iterable $this$mapNotNull$iv = started();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            Plugin it = (Plugin) element$iv$iv$iv;
            ToolProvider toolProvider = it.toolProvider();
            if (toolProvider != null) {
                destination$iv$iv.add(toolProvider);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<ToolDefinition> allDefinitions() {
        Iterable $this$flatMap$iv = toolProviders();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            ToolProvider it = (ToolProvider) element$iv$iv;
            Iterable list$iv$iv = it.definitions();
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }

    public final int size() {
        return this.byId.size();
    }
}
