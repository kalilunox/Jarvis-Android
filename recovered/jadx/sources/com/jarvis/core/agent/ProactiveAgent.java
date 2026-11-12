package com.jarvis.core.agent;

import com.jarvis.core.autonomy.AutonomyActionPipeline;
import com.jarvis.core.autonomy.AutonomyLevel;
import com.jarvis.core.autonomy.DefaultActionSafetyGate;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.autonomy.ProposedActionMapper;
import com.jarvis.core.discovery.DiscoveryResult;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.DeterministicAIPlanner;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.planner.PlanStepKind;
import com.jarvis.core.planner.PlannerContext;
import com.jarvis.core.planner.UserGoal;
import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProactiveAgent.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ$\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/agent/ProactiveAgent;", "", "planner", "Lcom/jarvis/core/planner/DeterministicAIPlanner;", "loop", "Lcom/jarvis/core/agent/AgentExecutionLoop;", "<init>", "(Lcom/jarvis/core/planner/DeterministicAIPlanner;Lcom/jarvis/core/agent/AgentExecutionLoop;)V", "suggest", "Lcom/jarvis/core/agent/ProactiveSuggestion;", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "discovery", "Lcom/jarvis/core/discovery/DiscoveryResult;", "nowMillis", "", "dryRun", "Lcom/jarvis/core/agent/AgentLoopResult;", "suggestion", "level", "Lcom/jarvis/core/autonomy/AutonomyLevel;", "planFromMappedTool", "Lcom/jarvis/core/planner/ActionPlan;", "toolId", "", "args", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveAgent {
    private final AgentExecutionLoop loop;
    private final DeterministicAIPlanner planner;

    /* JADX WARN: Multi-variable type inference failed */
    public ProactiveAgent() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ProactiveAgent(DeterministicAIPlanner planner, AgentExecutionLoop loop) {
        Intrinsics.checkNotNullParameter(planner, "planner");
        Intrinsics.checkNotNullParameter(loop, "loop");
        this.planner = planner;
        this.loop = loop;
    }

    public /* synthetic */ ProactiveAgent(DeterministicAIPlanner deterministicAIPlanner, AgentExecutionLoop agentExecutionLoop, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DeterministicAIPlanner() : deterministicAIPlanner, (i & 2) != 0 ? new AgentExecutionLoop(new AutonomyActionPipeline(null, new DefaultActionSafetyGate(0L, 1, null), null, 5, null), null, null, 6, null) : agentExecutionLoop);
    }

    public static /* synthetic */ ProactiveSuggestion suggest$default(ProactiveAgent proactiveAgent, ProactiveDecision proactiveDecision, DiscoveryResult discoveryResult, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = System.currentTimeMillis();
        }
        return proactiveAgent.suggest(proactiveDecision, discoveryResult, j);
    }

    public final ProactiveSuggestion suggest(ProactiveDecision decision, DiscoveryResult discovery, long nowMillis) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(discovery, "discovery");
        if (decision.getType() == ProactiveDecisionType.NO_OP) {
            return new ProactiveSuggestion("sug-" + UUID.randomUUID(), decision, null, false, "no_op");
        }
        String text = decision.getBehaviorPatternKey();
        if (text == null) {
            String reason = decision.getReason();
            if (StringsKt.isBlank(reason)) {
                reason = "proactive";
            }
            text = reason;
        }
        UserGoal goal = new UserGoal("proactive-" + UUID.randomUUID(), StringsKt.take(text, 200), nowMillis);
        ActionPlan plan = this.planner.plan(goal, new PlannerContext(discovery, null, 0, true, 0L, 22, null));
        boolean ok = plan.getStatus() == ActionPlanStatus.READY && !plan.getSteps().isEmpty();
        return new ProactiveSuggestion("sug-" + UUID.randomUUID(), decision, plan, ok, ok ? "ready_to_propose" : "no_plan");
    }

    public static /* synthetic */ AgentLoopResult dryRun$default(ProactiveAgent proactiveAgent, ProactiveSuggestion proactiveSuggestion, AutonomyLevel autonomyLevel, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            autonomyLevel = AutonomyLevel.SAFE_ONLY;
        }
        if ((i & 4) != 0) {
            j = System.currentTimeMillis();
        }
        return proactiveAgent.dryRun(proactiveSuggestion, autonomyLevel, j);
    }

    public final AgentLoopResult dryRun(ProactiveSuggestion suggestion, AutonomyLevel level, long nowMillis) {
        Intrinsics.checkNotNullParameter(suggestion, "suggestion");
        Intrinsics.checkNotNullParameter(level, "level");
        ActionPlan plan = suggestion.getPlan();
        if (plan != null && suggestion.getAllowedToPropose()) {
            return this.loop.run(plan, new AgentLoopContext(false, level, null, null, null, 0, nowMillis, null, 188, null));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionPlan planFromMappedTool$default(ProactiveAgent proactiveAgent, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        return proactiveAgent.planFromMappedTool(str, map);
    }

    public final ActionPlan planFromMappedTool(String toolId, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(args, "args");
        ProposedAction proposed = ProposedActionMapper.fromToolId$default(ProposedActionMapper.INSTANCE, toolId, null, args, null, 10, null);
        if (proposed == null) {
            return null;
        }
        return new ActionPlan("proactive-tool-" + UUID.randomUUID(), "g-" + toolId, ActionPlanStatus.READY, CollectionsKt.listOf(new PlanStep("s1", PlanStepKind.TOOL, proposed.getToolId(), null, proposed.getArguments(), null, null, null, false, null, 0L, null, null, false, proposed.getDisplaySummary(), null, 49128, null)), null, false, null, null, 240, null);
    }
}
