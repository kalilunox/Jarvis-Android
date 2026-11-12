package com.jarvis.core.planner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ActionPlanValidator.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¨\u0006\r"}, d2 = {"Lcom/jarvis/core/planner/ActionPlanValidator;", "", "<init>", "()V", "validate", "Lcom/jarvis/core/planner/ActionPlanValidation;", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "hasCycle", "", "steps", "", "Lcom/jarvis/core/planner/PlanStep;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActionPlanValidator {
    public static final ActionPlanValidator INSTANCE = new ActionPlanValidator();

    /* JADX INFO: compiled from: ActionPlanValidator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActionPlanStatus.values().length];
            try {
                iArr[ActionPlanStatus.EMPTY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ActionPlanStatus.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ActionPlanValidator() {
    }

    public final ActionPlanValidation validate(ActionPlan plan) {
        Intrinsics.checkNotNullParameter(plan, "plan");
        List errors = new ArrayList();
        if (plan.getSteps().isEmpty()) {
            switch (WhenMappings.$EnumSwitchMapping$0[plan.getStatus().ordinal()]) {
                case 1:
                case 2:
                    return new ActionPlanValidation(true, CollectionsKt.emptyList());
                default:
                    return new ActionPlanValidation(false, CollectionsKt.listOf("non-empty status with no steps"));
            }
        }
        Iterable $this$map$iv = plan.getSteps();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(((PlanStep) item$iv$iv).getStepId());
        }
        List ids = (List) destination$iv$iv;
        if (ids.size() != CollectionsKt.toSet(ids).size()) {
            errors.add("duplicate stepId");
        }
        Set idSet = CollectionsKt.toSet(ids);
        String entry = plan.getEntryStepId();
        if (entry != null && !idSet.contains(entry)) {
            errors.add("entryStepId missing: " + entry);
        }
        for (PlanStep step : plan.getSteps()) {
            for (String dep : step.getDependsOn()) {
                if (!idSet.contains(dep)) {
                    errors.add("step " + step.getStepId() + " depends on missing " + dep);
                }
                if (Intrinsics.areEqual(dep, step.getStepId())) {
                    errors.add("step " + step.getStepId() + " self-dependency");
                }
            }
            String d = step.getCondition().getDependsOnStepId();
            if (d != null && !idSet.contains(d)) {
                errors.add("step " + step.getStepId() + " condition depends on missing " + d);
            }
            for (PlanParameterBinding b : step.getParameterBindings()) {
                if (!idSet.contains(b.getFromStepId())) {
                    errors.add("step " + step.getStepId() + " binding from missing " + b.getFromStepId());
                }
                if (Intrinsics.areEqual(b.getFromStepId(), step.getStepId())) {
                    errors.add("step " + step.getStepId() + " binding self-ref");
                }
            }
            String it = step.getBranchOnSuccess();
            if (it != null && !idSet.contains(it)) {
                errors.add("step " + step.getStepId() + " branchOnSuccess missing " + it);
            }
            String it2 = step.getBranchOnFailure();
            if (it2 != null && !idSet.contains(it2)) {
                errors.add("step " + step.getStepId() + " branchOnFailure missing " + it2);
            }
        }
        if (hasCycle(plan.getSteps())) {
            errors.add("dependency cycle detected");
        }
        return new ActionPlanValidation(errors.isEmpty(), errors);
    }

    private final boolean hasCycle(List<PlanStep> steps) {
        List<PlanStep> $this$associate$iv = steps;
        int $i$f$associate = 0;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associate$iv) {
            PlanStep step = (PlanStep) element$iv$iv;
            LinkedHashSet deps = new LinkedHashSet();
            deps.addAll(step.getDependsOn());
            String it = step.getCondition().getDependsOnStepId();
            if (it != null) {
                deps.add(it);
            }
            Iterable $this$forEach$iv = step.getParameterBindings();
            for (Object element$iv : $this$forEach$iv) {
                deps.add(((PlanParameterBinding) element$iv).getFromStepId());
                $this$associate$iv = $this$associate$iv;
                $i$f$associate = $i$f$associate;
            }
            Iterable $this$associate$iv2 = $this$associate$iv;
            Pair pair = TuplesKt.to(step.getStepId(), deps);
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
            $this$associate$iv = $this$associate$iv2;
            $i$f$associate = $i$f$associate;
        }
        Set visiting = new LinkedHashSet();
        Set visited = new LinkedHashSet();
        Iterable $this$any$iv = destination$iv$iv.keySet();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv2 : $this$any$iv) {
            if (hasCycle$dfs(visiting, visited, destination$iv$iv, (String) element$iv2)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean hasCycle$dfs(Set<String> set, Set<String> set2, Map<String, ? extends LinkedHashSet<String>> map, String id) {
        if (set.contains(id)) {
            return true;
        }
        if (set2.contains(id)) {
            return false;
        }
        set.add(id);
        Set<String> setEmptySet = map.get(id);
        if (setEmptySet == null) {
            setEmptySet = SetsKt.emptySet();
        }
        for (String next : setEmptySet) {
            if (hasCycle$dfs(set, set2, map, next)) {
                return true;
            }
        }
        set.remove(id);
        set2.add(id);
        return false;
    }
}
