package com.jarvis.core.plugin;

import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.execution.ToolProviderSource;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PluginContracts.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/plugin/ToolProvider;", "", "providerId", "", "getProviderId", "()Ljava/lang/String;", "source", "Lcom/jarvis/core/execution/ToolProviderSource;", "getSource", "()Lcom/jarvis/core/execution/ToolProviderSource;", "definitions", "", "Lcom/jarvis/core/common/model/ToolDefinition;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ToolProvider {
    List<ToolDefinition> definitions();

    String getProviderId();

    ToolProviderSource getSource();
}
