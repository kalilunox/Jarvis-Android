package com.jarvis.core.execution;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FutureToolExecutor.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/jarvis/core/execution/UnimplementedFutureToolExecutor;", "Lcom/jarvis/core/execution/FutureToolExecutor;", "<init>", "()V", "execute", "Lcom/jarvis/core/execution/ToolExecutionResult;", "request", "Lcom/jarvis/core/execution/ToolExecutionRequest;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnimplementedFutureToolExecutor implements FutureToolExecutor {
    public static final UnimplementedFutureToolExecutor INSTANCE = new UnimplementedFutureToolExecutor();

    private UnimplementedFutureToolExecutor() {
    }

    @Override // com.jarvis.core.execution.FutureToolExecutor
    public ToolExecutionResult execute(ToolExecutionRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return ToolExecutionResult.Companion.failure$default(ToolExecutionResult.INSTANCE, request.getId(), request.getToolId(), ToolError.INSTANCE.unsupported("Stage 8E: FutureToolExecutor stub — no real execution"), request.getMetadata(), 0L, 16, null);
    }
}
