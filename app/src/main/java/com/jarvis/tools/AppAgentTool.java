package com.jarvis.tools;

import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppAgentTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/jarvis/tools/AppAgentTool;", "Lcom/jarvis/core/common/Tool;", "start", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppAgentTool implements Tool {
    private final ToolDefinition definition;
    private final Function1<String, String> start;

    /* JADX WARN: Multi-variable type inference failed */
    public AppAgentTool(Function1<? super String, String> start) {
        Intrinsics.checkNotNullParameter(start, "start");
        this.start = start;
        this.definition = new ToolDefinition("agent.run", "Сделай сам", "Выполнить задачу в приложениях шаг за шагом", CollectionsKt.listOf(new ToolParameter("goal", "задача", ParameterType.STRING, true, null, 16, null)), RiskLevel.SAFE, null, OnlineRequirement.ONLINE_ALLOWED, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String string;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("goal");
        String goal = (obj == null || (string = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
        if (goal == null) {
            goal = "";
        }
        return goal.length() == 0 ? new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Что сделать, сэр?", null, 0L, 48, null) : new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, this.start.invoke(goal), null, 0L, 48, null);
    }
}
