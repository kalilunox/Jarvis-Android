package com.jarvis.core.browseragent;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.ActionPlanValidation;
import com.jarvis.core.planner.ActionPlanValidator;
import com.jarvis.core.planner.PlanConditionOp;
import com.jarvis.core.planner.PlanRetryPolicy;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.planner.PlanStepCondition;
import com.jarvis.core.planner.PlanStepKind;
import com.jarvis.core.planner.UserGoal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BrowserAgent.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/browseragent/BrowserAgent;", "", "<init>", "()V", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "request", "Lcom/jarvis/core/browseragent/BrowserAgentRequest;", "planFromGoal", "goal", "Lcom/jarvis/core/planner/UserGoal;", "isOnline", "", "prevSuccess", "Lcom/jarvis/core/planner/PlanStepCondition;", "prevId", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BrowserAgent {

    /* JADX INFO: compiled from: BrowserAgent.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BrowserStepKind.values().length];
            try {
                iArr[BrowserStepKind.SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BrowserStepKind.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final ActionPlan plan(BrowserAgentRequest request) {
        List listEmptyList;
        List listEmptyList2;
        Intrinsics.checkNotNullParameter(request, "request");
        String planId = "browser-" + UUID.randomUUID();
        if (!request.isOnline()) {
            return new ActionPlan(planId, request.getGoal().getGoalId(), ActionPlanStatus.EMPTY, CollectionsKt.emptyList(), null, false, null, CollectionsKt.listOf("offline_browser_unavailable"), 112, null);
        }
        List steps = new ArrayList();
        Iterator it = request.getSteps().iterator();
        String prevId = null;
        int i = 0;
        while (it.hasNext()) {
            int index = i;
            int i2 = i + 1;
            BrowserStepKind kind = (BrowserStepKind) it.next();
            boolean needsConfirm = BrowserStepPolicy.INSTANCE.requiresConfirmation(kind);
            if (needsConfirm) {
                String confirmId = "b" + (index + 1) + "-confirm";
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
                PlanStepCondition planStepConditionPrevSuccess = prevSuccess(prevId);
                String lowerCase = kind.name().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                list.add(new PlanStep(confirmId, planStepKind, null, null, null, null, listEmptyList2, planStepConditionPrevSuccess, true, null, 0L, null, null, false, "Confirm browser " + lowerCase, CollectionsKt.listOf("risk=" + BrowserStepPolicy.INSTANCE.riskLevel(kind)), 15932, null));
                prevId = confirmId;
            }
            String lowerCase2 = kind.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            String toolStepId = "b" + (index + 1) + "-" + lowerCase2;
            LinkedHashMap params = new LinkedHashMap();
            switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                case 1:
                    LinkedHashMap linkedHashMap = params;
                    String query = request.getQuery();
                    if (query == null) {
                        query = StringsKt.take(request.getGoal().getText(), 200);
                    }
                    linkedHashMap.put(SearchIntents.EXTRA_QUERY, query);
                    break;
                case 2:
                    String it3 = request.getUrl();
                    if (it3 != null) {
                        params.put(ImagesContract.URL, it3);
                    } else {
                        params.put(ImagesContract.URL, "");
                    }
                    break;
            }
            List list2 = steps;
            PlanStepKind planStepKind2 = PlanStepKind.TOOL;
            String str = BrowserStepPolicy.INSTANCE.toolId(kind);
            LinkedHashMap $this$filterValues$iv = params;
            LinkedHashMap result$iv = new LinkedHashMap();
            for (Map.Entry entry$iv : $this$filterValues$iv.entrySet()) {
                String it4 = (String) entry$iv.getValue();
                if (!StringsKt.isBlank(it4)) {
                    result$iv.put(entry$iv.getKey(), entry$iv.getValue());
                    params = params;
                    it = it;
                }
            }
            Iterator it5 = it;
            LinkedHashMap linkedHashMap2 = result$iv;
            if (prevId != null) {
                String it6 = prevId;
                listEmptyList = CollectionsKt.listOf(it6);
            } else {
                listEmptyList = null;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            list2.add(new PlanStep(toolStepId, planStepKind2, str, null, linkedHashMap2, null, listEmptyList, prevSuccess(prevId), needsConfirm, new PlanRetryPolicy(kind == BrowserStepKind.SEARCH ? 2 : 1, false, 2, null), 0L, null, null, needsConfirm, kind.name(), CollectionsKt.listOf((Object[]) new String[]{"browser_agent=true", "step=" + kind, "risk=" + BrowserStepPolicy.INSTANCE.riskLevel(kind), "online=required"}), 7208, null));
            prevId = toolStepId;
            it = it5;
            i = i2;
        }
        String goalId = request.getGoal().getGoalId();
        ActionPlanStatus actionPlanStatus = ActionPlanStatus.READY;
        PlanStep planStep = (PlanStep) CollectionsKt.firstOrNull(steps);
        String stepId = planStep != null ? planStep.getStepId() : null;
        List $this$filter$iv = steps;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            PlanStep it7 = (PlanStep) element$iv$iv;
            Iterable $this$filter$iv2 = $this$filter$iv;
            if ((it7.getKind() == PlanStepKind.TOOL ? 1 : 0) != 0) {
                destination$iv$iv.add(element$iv$iv);
            }
            $this$filter$iv = $this$filter$iv2;
        }
        Iterable<PlanStep> $this$associate$iv = (List) destination$iv$iv;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv2 = new LinkedHashMap(capacity$iv);
        for (PlanStep it8 : $this$associate$iv) {
            Iterable $this$associate$iv2 = $this$associate$iv;
            Pair pair = TuplesKt.to(it8.getStepId(), "output");
            destination$iv$iv2.put(pair.getFirst(), pair.getSecond());
            $this$associate$iv = $this$associate$iv2;
        }
        ActionPlan plan = new ActionPlan(planId, goalId, actionPlanStatus, steps, stepId, false, destination$iv$iv2, CollectionsKt.listOf((Object[]) new String[]{"planner=browser_agent", "pipeline=" + CollectionsKt.joinToString$default(request.getSteps(), ">", null, null, 0, null, null, 62, null)}), 32, null);
        ActionPlanValidation v = ActionPlanValidator.INSTANCE.validate(plan);
        if (v.getOk()) {
            return plan;
        }
        ActionPlanStatus actionPlanStatus2 = ActionPlanStatus.INVALID;
        List<String> notes = plan.getNotes();
        Iterable $this$map$iv = v.getErrors();
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it9 = (String) item$iv$iv;
            destination$iv$iv3.add("err=" + it9);
            planId = planId;
        }
        return plan.copy((91 & 1) != 0 ? plan.planId : null, (91 & 2) != 0 ? plan.goalId : null, (91 & 4) != 0 ? plan.status : actionPlanStatus2, (91 & 8) != 0 ? plan.steps : null, (91 & 16) != 0 ? plan.entryStepId : null, (91 & 32) != 0 ? plan.cancellationRequested : false, (91 & 64) != 0 ? plan.resultSlots : null, (91 & 128) != 0 ? plan.notes : CollectionsKt.plus((Collection) notes, destination$iv$iv3));
    }

    public static /* synthetic */ ActionPlan planFromGoal$default(BrowserAgent browserAgent, UserGoal userGoal, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return browserAgent.planFromGoal(userGoal, z);
    }

    public final ActionPlan planFromGoal(UserGoal goal, boolean isOnline) {
        Intrinsics.checkNotNullParameter(goal, "goal");
        return plan(new BrowserAgentRequest(goal, null, null, null, isOnline, 14, null));
    }

    private final PlanStepCondition prevSuccess(String prevId) {
        return prevId == null ? new PlanStepCondition(null, null, null, null, 15, null) : new PlanStepCondition(PlanConditionOp.PREV_SUCCESS, prevId, null, null, 12, null);
    }
}
