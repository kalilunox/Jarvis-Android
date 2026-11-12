package com.jarvis.core.common;

import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: Tool.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/jarvis/core/common/DefaultToolExecutor;", "Lcom/jarvis/core/common/ToolExecutor;", "registry", "Lcom/jarvis/core/common/ToolRegistry;", "<init>", "(Lcom/jarvis/core/common/ToolRegistry;)V", "getRegistry", "()Lcom/jarvis/core/common/ToolRegistry;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultToolExecutor implements ToolExecutor {
    private final ToolRegistry registry;

    public DefaultToolExecutor(ToolRegistry registry) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        this.registry = registry;
    }

    @Override // com.jarvis.core.common.ToolExecutor
    public ToolRegistry getRegistry() {
        return this.registry;
    }

    @Override // com.jarvis.core.common.ToolExecutor
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Tool tool = getRegistry().find(call.getToolId());
        if (tool == null) {
            return new ToolResult(call.getRequestId(), call.getToolId(), ToolResultStatus.FAILED, "Инструмент не найден: " + call.getToolId(), null, 0L, 48, null);
        }
        long started = System.nanoTime();
        try {
            return ToolResult.copy$default(tool.execute(call), null, null, null, null, null, (System.nanoTime() - started) / ((long) DurationKt.NANOS_IN_MILLIS), 31, null);
        } catch (Throwable t) {
            String requestId = call.getRequestId();
            String toolId = call.getToolId();
            ToolResultStatus toolResultStatus = ToolResultStatus.FAILED;
            String name = tool.getDefinition().getName();
            String message = t.getMessage();
            if (message == null) {
                message = t.getClass().getSimpleName();
            }
            return new ToolResult(requestId, toolId, toolResultStatus, "Ошибка при выполнении «" + name + "»: " + message, null, (System.nanoTime() - started) / ((long) DurationKt.NANOS_IN_MILLIS), 16, null);
        }
    }
}
