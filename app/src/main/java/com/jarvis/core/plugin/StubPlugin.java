package com.jarvis.core.plugin;

import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.execution.ToolProviderSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PluginContracts.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/plugin/StubPlugin;", "Lcom/jarvis/core/plugin/Plugin;", "manifest", "Lcom/jarvis/core/plugin/PluginManifest;", "definitions", "", "Lcom/jarvis/core/common/model/ToolDefinition;", "failOnStart", "", "<init>", "(Lcom/jarvis/core/plugin/PluginManifest;Ljava/util/List;Z)V", "getManifest", "()Lcom/jarvis/core/plugin/PluginManifest;", "state", "Lcom/jarvis/core/plugin/PluginLifecycleState;", "start", "stop", "toolProvider", "Lcom/jarvis/core/plugin/ToolProvider;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StubPlugin implements Plugin {
    private final List<ToolDefinition> definitions;
    private final boolean failOnStart;
    private final PluginManifest manifest;
    private PluginLifecycleState state;

    public StubPlugin(PluginManifest manifest, List<ToolDefinition> definitions, boolean failOnStart) {
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        Intrinsics.checkNotNullParameter(definitions, "definitions");
        this.manifest = manifest;
        this.definitions = definitions;
        this.failOnStart = failOnStart;
        this.state = PluginLifecycleState.REGISTERED;
    }

    public /* synthetic */ StubPlugin(PluginManifest pluginManifest, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(pluginManifest, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? false : z);
    }

    @Override // com.jarvis.core.plugin.Plugin
    public PluginManifest getManifest() {
        return this.manifest;
    }

    @Override // com.jarvis.core.plugin.Plugin
    /* JADX INFO: renamed from: state, reason: from getter */
    public PluginLifecycleState getState() {
        return this.state;
    }

    @Override // com.jarvis.core.plugin.Plugin
    public boolean start() {
        if (this.failOnStart) {
            this.state = PluginLifecycleState.FAILED;
            return false;
        }
        this.state = PluginLifecycleState.STARTED;
        return true;
    }

    @Override // com.jarvis.core.plugin.Plugin
    public boolean stop() {
        this.state = PluginLifecycleState.STOPPED;
        return true;
    }

    @Override // com.jarvis.core.plugin.Plugin
    public ToolProvider toolProvider() {
        if (this.state == PluginLifecycleState.STARTED && !this.definitions.isEmpty()) {
            return new ToolProvider() { // from class: com.jarvis.core.plugin.StubPlugin.toolProvider.1
                private final String providerId;
                private final ToolProviderSource source = ToolProviderSource.PLUGIN;

                {
                    this.providerId = StubPlugin.this.getManifest().getId();
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
                    return StubPlugin.this.definitions;
                }
            };
        }
        return null;
    }
}
