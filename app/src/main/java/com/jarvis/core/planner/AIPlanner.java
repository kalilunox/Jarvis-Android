package com.jarvis.core.planner;

import kotlin.Metadata;

/* JADX INFO: compiled from: AIPlanner.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nH&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/planner/AIPlanner;", "", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "goal", "Lcom/jarvis/core/planner/UserGoal;", "context", "Lcom/jarvis/core/planner/PlannerContext;", "cancel", "reason", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AIPlanner {
    ActionPlan cancel(ActionPlan plan, String reason);

    ActionPlan plan(UserGoal goal, PlannerContext context);

    /* JADX INFO: compiled from: AIPlanner.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ ActionPlan cancel$default(AIPlanner aIPlanner, ActionPlan actionPlan, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }
        if ((i & 2) != 0) {
            str = "user_cancel";
        }
        return aIPlanner.cancel(actionPlan, str);
    }
}
