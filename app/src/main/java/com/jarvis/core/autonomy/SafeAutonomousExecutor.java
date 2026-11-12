package com.jarvis.core.autonomy;

import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.proactive.ProactiveDecision;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeAutonomousExecutor.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JJ\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/autonomy/SafeAutonomousExecutor;", "", "toolExecutor", "Lcom/jarvis/core/common/ToolExecutor;", "pipeline", "Lcom/jarvis/core/autonomy/AutonomyActionPipeline;", "<init>", "(Lcom/jarvis/core/common/ToolExecutor;Lcom/jarvis/core/autonomy/AutonomyActionPipeline;)V", "tryExecute", "Lcom/jarvis/core/autonomy/SafeAutonomousResult;", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "proposed", "Lcom/jarvis/core/autonomy/ProposedAction;", "level", "Lcom/jarvis/core/autonomy/AutonomyLevel;", "allowlist", "", "", "blocklist", "nowMillis", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SafeAutonomousExecutor {
    private final AutonomyActionPipeline pipeline;
    private final ToolExecutor toolExecutor;

    public SafeAutonomousExecutor(ToolExecutor toolExecutor, AutonomyActionPipeline pipeline) {
        Intrinsics.checkNotNullParameter(toolExecutor, "toolExecutor");
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        this.toolExecutor = toolExecutor;
        this.pipeline = pipeline;
    }

    public final SafeAutonomousResult tryExecute(ProactiveDecision decision, ProposedAction proposed, AutonomyLevel level, Set<String> allowlist, Set<String> blocklist, long nowMillis) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(proposed, "proposed");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(allowlist, "allowlist");
        Intrinsics.checkNotNullParameter(blocklist, "blocklist");
        String toolId = proposed.getToolId();
        if (toolId == null || !ProposedActionMapper.INSTANCE.getFIRST_SAFE_TOOL_IDS().contains(toolId)) {
            return new SafeAutonomousResult(false, null, null, "tool not in FIRST_SAFE_TOOL_IDS: " + (toolId == null ? "null" : toolId), null, 16, null);
        }
        AutonomyActionPlan plan = AutonomyActionPipeline.plan$default(this.pipeline, decision, proposed, level, allowlist, blocklist, nowMillis, null, null, null, 0L, null, null, null, 8128, null);
        if (plan.getStatus() != AutonomyPlanStatus.READY_TO_EXECUTE) {
            return new SafeAutonomousResult(false, plan, null, "plan status=" + plan.getStatus(), null, 16, null);
        }
        ToolCall call = plan.getDraftToolCall();
        if (call == null) {
            return new SafeAutonomousResult(false, plan, null, "no draft ToolCall", null, 16, null);
        }
        ToolResult result = this.toolExecutor.execute(call);
        AutonomyAuditLog.INSTANCE.record(AutonomyVerdict.copy$default(plan.getVerdict(), null, null, null, null, CollectionsKt.plus((Collection<? extends String>) CollectionsKt.plus((Collection<? extends String>) plan.getVerdict().getNotes(), "exec=" + result.getStatus()), "tool=" + result.getToolId()), 15, null), "safe.exec");
        boolean z = result.getStatus() == ToolResultStatus.SUCCESS;
        String message = result.getMessage();
        if (message == null) {
            message = result.getStatus().name();
        }
        return new SafeAutonomousResult(z, plan, result, message, call);
    }
}
