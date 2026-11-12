package com.jarvis.core.agent;

import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AgentLoopModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isRunnable", "", "Lcom/jarvis/core/planner/ActionPlan;", "core_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AgentLoopModelsKt {
    public static final boolean isRunnable(ActionPlan $this$isRunnable) {
        Intrinsics.checkNotNullParameter($this$isRunnable, "<this>");
        return ($this$isRunnable.getStatus() != ActionPlanStatus.READY || $this$isRunnable.getCancellationRequested() || $this$isRunnable.getSteps().isEmpty()) ? false : true;
    }
}
