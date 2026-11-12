package com.jarvis.tools;

import android.content.Context;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.screencontrol.ActionCoordinator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UiTools.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/jarvis/tools/UiGlobalTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "coordinator", "Lcom/jarvis/screencontrol/ActionCoordinator;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UiGlobalTool implements Tool {
    private final ActionCoordinator coordinator;
    private final ToolDefinition definition;

    public UiGlobalTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.coordinator = new ActionCoordinator(context);
        this.definition = new ToolDefinition("ui.global", "Системная кнопка", "Системные действия: back, home, recents, notifications, quick_settings, lock", CollectionsKt.listOf(new ToolParameter("action", "Действие", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"back", "home", "recents", "notifications", "quick_settings", "lock", "power"}))), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("action");
        String action = obj != null ? obj.toString() : null;
        if (action == null) {
            action = "";
        }
        ActionCoordinator.ActionResult r = this.coordinator.global(action);
        return new ToolResult(call.getRequestId(), getDefinition().getId(), r.getSuccess() ? ToolResultStatus.SUCCESS : ToolResultStatus.FAILED, r.getMessage(), null, 0L, 48, null);
    }
}
