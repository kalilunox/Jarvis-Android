package com.jarvis.core.plugin;

import kotlin.Metadata;

/* JADX INFO: compiled from: PluginContracts.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/plugin/Plugin;", "", "manifest", "Lcom/jarvis/core/plugin/PluginManifest;", "getManifest", "()Lcom/jarvis/core/plugin/PluginManifest;", "state", "Lcom/jarvis/core/plugin/PluginLifecycleState;", "start", "", "stop", "toolProvider", "Lcom/jarvis/core/plugin/ToolProvider;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Plugin {
    PluginManifest getManifest();

    boolean start();

    PluginLifecycleState state();

    boolean stop();

    ToolProvider toolProvider();
}
