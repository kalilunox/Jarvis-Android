package com.jarvis.core.runtime;

import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.agent.AgentExecutionLoop;
import com.jarvis.core.agent.AgentLoopContext;
import com.jarvis.core.agent.AgentLoopResult;
import com.jarvis.core.discovery.DiscoveryResult;
import com.jarvis.core.planner.AIPlanner;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.DeterministicAIPlanner;
import com.jarvis.core.planner.PlannerContext;
import com.jarvis.core.planner.UserGoal;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlanPathCoordinator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J<\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/runtime/PlanPathCoordinator;", "", "planner", "Lcom/jarvis/core/planner/AIPlanner;", "agentLoop", "Lcom/jarvis/core/agent/AgentExecutionLoop;", "<init>", "(Lcom/jarvis/core/planner/AIPlanner;Lcom/jarvis/core/agent/AgentExecutionLoop;)V", "planAndRun", "Lcom/jarvis/core/runtime/PlanPathCoordinator$PlanRun;", "goalText", "", "discovery", "Lcom/jarvis/core/discovery/DiscoveryResult;", "requestId", "nowMillis", "", JarvisActivity.LIVE, "", "maxSteps", "", "PlanRun", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlanPathCoordinator {
    private final AgentExecutionLoop agentLoop;
    private final AIPlanner planner;

    /* JADX WARN: Multi-variable type inference failed */
    public PlanPathCoordinator() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public PlanPathCoordinator(AIPlanner planner, AgentExecutionLoop agentLoop) {
        Intrinsics.checkNotNullParameter(planner, "planner");
        Intrinsics.checkNotNullParameter(agentLoop, "agentLoop");
        this.planner = planner;
        this.agentLoop = agentLoop;
    }

    public /* synthetic */ PlanPathCoordinator(DeterministicAIPlanner deterministicAIPlanner, AgentExecutionLoop agentExecutionLoop, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DeterministicAIPlanner() : deterministicAIPlanner, (i & 2) != 0 ? new AgentExecutionLoop(null, null, null, 7, null) : agentExecutionLoop);
    }

    /* JADX INFO: compiled from: PlanPathCoordinator.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J3\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/runtime/PlanPathCoordinator$PlanRun;", "", "goal", "Lcom/jarvis/core/planner/UserGoal;", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "loopResult", "Lcom/jarvis/core/agent/AgentLoopResult;", "dryRun", "", "<init>", "(Lcom/jarvis/core/planner/UserGoal;Lcom/jarvis/core/planner/ActionPlan;Lcom/jarvis/core/agent/AgentLoopResult;Z)V", "getGoal", "()Lcom/jarvis/core/planner/UserGoal;", "getPlan", "()Lcom/jarvis/core/planner/ActionPlan;", "getLoopResult", "()Lcom/jarvis/core/agent/AgentLoopResult;", "getDryRun", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class PlanRun {
        private final boolean dryRun;
        private final UserGoal goal;
        private final AgentLoopResult loopResult;
        private final ActionPlan plan;

        public static /* synthetic */ PlanRun copy$default(PlanRun planRun, UserGoal userGoal, ActionPlan actionPlan, AgentLoopResult agentLoopResult, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                userGoal = planRun.goal;
            }
            if ((i & 2) != 0) {
                actionPlan = planRun.plan;
            }
            if ((i & 4) != 0) {
                agentLoopResult = planRun.loopResult;
            }
            if ((i & 8) != 0) {
                z = planRun.dryRun;
            }
            return planRun.copy(userGoal, actionPlan, agentLoopResult, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UserGoal getGoal() {
            return this.goal;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ActionPlan getPlan() {
            return this.plan;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final AgentLoopResult getLoopResult() {
            return this.loopResult;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getDryRun() {
            return this.dryRun;
        }

        public final PlanRun copy(UserGoal goal, ActionPlan plan, AgentLoopResult loopResult, boolean dryRun) {
            Intrinsics.checkNotNullParameter(goal, "goal");
            Intrinsics.checkNotNullParameter(plan, "plan");
            return new PlanRun(goal, plan, loopResult, dryRun);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PlanRun)) {
                return false;
            }
            PlanRun planRun = (PlanRun) other;
            return Intrinsics.areEqual(this.goal, planRun.goal) && Intrinsics.areEqual(this.plan, planRun.plan) && Intrinsics.areEqual(this.loopResult, planRun.loopResult) && this.dryRun == planRun.dryRun;
        }

        public int hashCode() {
            return (((((this.goal.hashCode() * 31) + this.plan.hashCode()) * 31) + (this.loopResult == null ? 0 : this.loopResult.hashCode())) * 31) + Boolean.hashCode(this.dryRun);
        }

        public String toString() {
            return "PlanRun(goal=" + this.goal + ", plan=" + this.plan + ", loopResult=" + this.loopResult + ", dryRun=" + this.dryRun + ")";
        }

        public PlanRun(UserGoal goal, ActionPlan plan, AgentLoopResult loopResult, boolean dryRun) {
            Intrinsics.checkNotNullParameter(goal, "goal");
            Intrinsics.checkNotNullParameter(plan, "plan");
            this.goal = goal;
            this.plan = plan;
            this.loopResult = loopResult;
            this.dryRun = dryRun;
        }

        public final UserGoal getGoal() {
            return this.goal;
        }

        public final ActionPlan getPlan() {
            return this.plan;
        }

        public final AgentLoopResult getLoopResult() {
            return this.loopResult;
        }

        public final boolean getDryRun() {
            return this.dryRun;
        }
    }

    public final PlanRun planAndRun(String goalText, DiscoveryResult discovery, String requestId, long nowMillis, boolean live, int maxSteps) {
        Intrinsics.checkNotNullParameter(goalText, "goalText");
        Intrinsics.checkNotNullParameter(discovery, "discovery");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        UserGoal goal = new UserGoal(requestId + "/goal", StringsKt.take(goalText, 2000), nowMillis);
        ActionPlan plan = this.planner.plan(goal, new PlannerContext(discovery, null, maxSteps, false, 0L, 18, null));
        AgentLoopResult loopResult = this.agentLoop.run(plan, new AgentLoopContext(live, null, null, null, null, 0, nowMillis, null, 190, null));
        return new PlanRun(goal, plan, loopResult, !live);
    }
}
