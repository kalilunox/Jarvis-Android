package com.jarvis.core.planner;

import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.discovery.DiscoveredItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DeterministicAIPlanner.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0002J\u001c\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/planner/DeterministicAIPlanner;", "Lcom/jarvis/core/planner/AIPlanner;", "<init>", "()V", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "goal", "Lcom/jarvis/core/planner/UserGoal;", "context", "Lcom/jarvis/core/planner/PlannerContext;", "cancel", "reason", "", "prevSuccess", "Lcom/jarvis/core/planner/PlanStepCondition;", "prevId", "boundedParams", "", "item", "Lcom/jarvis/core/discovery/DiscoveredItem;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DeterministicAIPlanner implements AIPlanner {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jarvis.core.planner.AIPlanner
    public ActionPlan plan(UserGoal goal, PlannerContext context) {
        List listEmptyList;
        List listEmptyList2;
        Intrinsics.checkNotNullParameter(goal, "goal");
        Intrinsics.checkNotNullParameter(context, "context");
        String planId = "plan-" + UUID.randomUUID();
        Iterable $this$filter$iv = context.getDiscovery().getItems();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            DiscoveredItem it = (DiscoveredItem) element$iv$iv;
            if (!StringsKt.isBlank(it.getToolId())) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv;
        List items = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.planner.DeterministicAIPlanner$plan$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                DiscoveredItem it2 = (DiscoveredItem) t2;
                DiscoveredItem it3 = (DiscoveredItem) t;
                return ComparisonsKt.compareValues(Float.valueOf(it2.getScore()), Float.valueOf(it3.getScore()));
            }
        });
        int i = 0;
        int i2 = 1;
        if (items.isEmpty()) {
            return new ActionPlan(planId, goal.getGoalId(), ActionPlanStatus.EMPTY, CollectionsKt.emptyList(), null, false, null, CollectionsKt.listOf((Object[]) new String[]{"no_discovered_tools", "discovery_considered=" + context.getDiscovery().getConsidered()}), 112, null);
        }
        List<DiscoveredItem> selected = context.getPreferSingleStep() ? CollectionsKt.listOf(CollectionsKt.first(items)) : CollectionsKt.take(items, RangesKt.coerceAtMost(context.getMaxSteps(), 16));
        List steps = new ArrayList();
        String prevId = null;
        int i3 = 0;
        for (DiscoveredItem item : selected) {
            int index = i3;
            int i4 = i3 + i2;
            int i5 = item.getRiskLevel().ordinal() >= RiskLevel.CONFIRM.ordinal() ? i2 : i;
            if (i5 != 0) {
                String confirmId = "s" + (index + 1) + "-confirm";
                List list = steps;
                PlanStepKind planStepKind = PlanStepKind.CONFIRM;
                if (prevId != null) {
                    String it2 = prevId;
                    listEmptyList2 = CollectionsKt.listOf(it2);
                } else {
                    listEmptyList2 = null;
                }
                if (listEmptyList2 == null) {
                    listEmptyList2 = CollectionsKt.emptyList();
                }
                list.add(new PlanStep(confirmId, planStepKind, null, null, null, null, listEmptyList2, prevSuccess(prevId), true, null, 0L, null, null, false, "Confirm: " + item.getName(), CollectionsKt.listOf((Object[]) new String[]{"risk=" + item.getRiskLevel(), "tool=" + item.getToolId()}), 15932, null));
                prevId = confirmId;
            }
            if (context.getDefaultWaitBetweenMs() > 0 && prevId != null && index > 0) {
                String waitId = "s" + (index + 1) + "-wait";
                steps.add(new PlanStep(waitId, PlanStepKind.WAIT, null, null, null, null, CollectionsKt.listOf(prevId), prevSuccess(prevId), false, null, context.getDefaultWaitBetweenMs(), null, null, false, "Wait " + context.getDefaultWaitBetweenMs() + "ms", null, 47932, null));
                prevId = waitId;
            }
            String toolStepId = "s" + (index + 1) + "-tool";
            List list2 = steps;
            PlanStepKind planStepKind2 = PlanStepKind.TOOL;
            String toolId = item.getToolId();
            String capabilityId = item.getCapabilityId();
            Map<String, String> mapBoundedParams = boundedParams(item);
            if (prevId != null) {
                String it3 = prevId;
                listEmptyList = CollectionsKt.listOf(it3);
            } else {
                listEmptyList = null;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List list3 = listEmptyList;
            PlanStepCondition planStepConditionPrevSuccess = prevSuccess(prevId);
            PlanRetryPolicy planRetryPolicy = new PlanRetryPolicy(item.getRiskLevel() == RiskLevel.LOW ? 2 : 1, false, 2, null);
            String name = item.getName();
            if (StringsKt.isBlank(name)) {
                name = item.getToolId();
            }
            list2.add(new PlanStep(toolStepId, planStepKind2, toolId, capabilityId, mapBoundedParams, null, list3, planStepConditionPrevSuccess, i5, planRetryPolicy, 0L, null, null, i5, name, CollectionsKt.listOf((Object[]) new String[]{"score=" + item.getScore(), "risk=" + item.getRiskLevel(), "source=" + item.getSource()}), 7200, null));
            prevId = toolStepId;
            i3 = i4;
            i = 0;
            i2 = 1;
        }
        if (!context.getPreferSingleStep() && selected.size() > 1 && prevId != null) {
            steps.add(new PlanStep("s-branch", PlanStepKind.BRANCH, null, null, null, null, CollectionsKt.listOf(prevId), new PlanStepCondition(PlanConditionOp.ALWAYS, null, null, null, 14, null), false, null, 0L, "end-noop", "end-noop", true, "End branch", CollectionsKt.listOf("terminal_branch"), 1852, null));
            steps.add(new PlanStep("end-noop", PlanStepKind.NOOP, null, null, null, null, null, null, false, null, 0L, null, null, false, "End", CollectionsKt.listOf("terminal"), 16380, null));
        }
        String goalId = goal.getGoalId();
        ActionPlanStatus actionPlanStatus = ActionPlanStatus.READY;
        List list4 = CollectionsKt.toList(steps);
        PlanStep planStep = (PlanStep) CollectionsKt.firstOrNull(steps);
        String stepId = planStep != null ? planStep.getStepId() : null;
        List $this$filter$iv2 = steps;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            PlanStep it4 = (PlanStep) element$iv$iv2;
            Iterable $this$filter$iv3 = $this$filter$iv2;
            if (it4.getKind() == PlanStepKind.TOOL) {
                destination$iv$iv2.add(element$iv$iv2);
            }
            $this$filter$iv2 = $this$filter$iv3;
        }
        Iterable<PlanStep> $this$associate$iv = (List) destination$iv$iv2;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv3 = new LinkedHashMap(capacity$iv);
        for (PlanStep it5 : $this$associate$iv) {
            Iterable $this$associate$iv2 = $this$associate$iv;
            Pair pair = TuplesKt.to(it5.getStepId(), "output");
            destination$iv$iv3.put(pair.getFirst(), pair.getSecond());
            $this$associate$iv = $this$associate$iv2;
        }
        ActionPlan plan = new ActionPlan(planId, goalId, actionPlanStatus, list4, stepId, false, destination$iv$iv3, CollectionsKt.listOf((Object[]) new String[]{"planner=deterministic", "selected=" + selected.size(), "preferSingle=" + context.getPreferSingleStep()}), 32, null);
        ActionPlanValidation validation = ActionPlanValidator.INSTANCE.validate(plan);
        if (validation.getOk()) {
            return plan;
        }
        ActionPlanStatus actionPlanStatus2 = ActionPlanStatus.INVALID;
        List<String> notes = plan.getNotes();
        Iterable $this$map$iv = validation.getErrors();
        Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it6 = (String) item$iv$iv;
            destination$iv$iv4.add("err=" + it6);
        }
        return plan.copy((91 & 1) != 0 ? plan.planId : null, (91 & 2) != 0 ? plan.goalId : null, (91 & 4) != 0 ? plan.status : actionPlanStatus2, (91 & 8) != 0 ? plan.steps : null, (91 & 16) != 0 ? plan.entryStepId : null, (91 & 32) != 0 ? plan.cancellationRequested : false, (91 & 64) != 0 ? plan.resultSlots : null, (91 & 128) != 0 ? plan.notes : CollectionsKt.plus((Collection) notes, destination$iv$iv4));
    }

    @Override // com.jarvis.core.planner.AIPlanner
    public ActionPlan cancel(ActionPlan plan, String reason) {
        Intrinsics.checkNotNullParameter(plan, "plan");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return plan.copy((91 & 1) != 0 ? plan.planId : null, (91 & 2) != 0 ? plan.goalId : null, (91 & 4) != 0 ? plan.status : ActionPlanStatus.CANCELLED, (91 & 8) != 0 ? plan.steps : null, (91 & 16) != 0 ? plan.entryStepId : null, (91 & 32) != 0 ? plan.cancellationRequested : true, (91 & 64) != 0 ? plan.resultSlots : null, (91 & 128) != 0 ? plan.notes : CollectionsKt.plus((Collection<? extends String>) plan.getNotes(), StringsKt.take("cancel=" + reason, 120)));
    }

    private final PlanStepCondition prevSuccess(String prevId) {
        if (prevId == null) {
            return new PlanStepCondition(null, null, null, null, 15, null);
        }
        return new PlanStepCondition(PlanConditionOp.PREV_SUCCESS, prevId, null, null, 12, null);
    }

    private final Map<String, String> boundedParams(DiscoveredItem item) {
        Iterable $this$associateWith$iv = CollectionsKt.take(item.getParameterNames(), 16);
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        Map destination$iv$iv = result$iv;
        for (Object element$iv$iv : $this$associateWith$iv) {
            destination$iv$iv.put(element$iv$iv, "");
        }
        Map destination$iv$iv2 = new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size()));
        Iterable $this$associateByTo$iv$iv$iv = destination$iv$iv.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            String k = (String) ((Map.Entry) element$iv$iv$iv).getKey();
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            destination$iv$iv2.put(StringsKt.take(k, 64), it$iv$iv.getValue());
        }
        Map destination$iv$iv3 = new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv2.size()));
        Iterable $this$associateByTo$iv$iv$iv2 = destination$iv$iv2.entrySet();
        for (Object element$iv$iv$iv2 : $this$associateByTo$iv$iv$iv2) {
            Map.Entry it$iv$iv2 = (Map.Entry) element$iv$iv$iv2;
            Object key = it$iv$iv2.getKey();
            String v = (String) ((Map.Entry) element$iv$iv$iv2).getValue();
            destination$iv$iv3.put(key, StringsKt.take(v, 512));
        }
        return destination$iv$iv3;
    }
}
