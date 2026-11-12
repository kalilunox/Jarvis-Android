package com.jarvis.core.workflow;

import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.PlanConditionOp;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.planner.PlanStepCondition;
import com.jarvis.core.planner.PlanStepKind;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WorkflowModels.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\t¨\u0006\r"}, d2 = {"Lcom/jarvis/core/workflow/WorkflowConverter;", "", "<init>", "()V", "fromActionPlan", "Lcom/jarvis/core/workflow/WorkflowGraph;", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "title", "", "toActionPlan", "graph", "goalId", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WorkflowConverter {
    public static final WorkflowConverter INSTANCE = new WorkflowConverter();

    /* JADX INFO: compiled from: WorkflowModels.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PlanStepKind.values().length];
            try {
                iArr[PlanStepKind.TOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PlanStepKind.CONFIRM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PlanStepKind.BRANCH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PlanStepKind.WAIT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[PlanStepKind.NOOP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[WorkflowNodeType.values().length];
            try {
                iArr2[WorkflowNodeType.ACTION.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[WorkflowNodeType.CONDITION.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[WorkflowNodeType.LOOP.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[WorkflowNodeType.END.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[WorkflowNodeType.TRIGGER.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private WorkflowConverter() {
    }

    public static /* synthetic */ WorkflowGraph fromActionPlan$default(WorkflowConverter workflowConverter, ActionPlan actionPlan, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "Workflow";
        }
        return workflowConverter.fromActionPlan(actionPlan, str);
    }

    /* JADX WARN: Code duplicated, block: B:62:0x0176  */
    /* JADX WARN: Code duplicated, block: B:65:0x018d A[LOOP:2: B:60:0x0170->B:65:0x018d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:79:0x0191 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x0193 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public final WorkflowGraph fromActionPlan(ActionPlan plan, String title) {
        boolean z;
        WorkflowNode lastAction;
        int index$iv;
        Iterator it;
        WorkflowNode it2;
        WorkflowNodeType type;
        Intrinsics.checkNotNullParameter(plan, "plan");
        Intrinsics.checkNotNullParameter(title, "title");
        List nodes = new ArrayList();
        nodes.add(new WorkflowNode("trigger", WorkflowNodeType.TRIGGER, "Start", null, null, plan.getEntryStepId(), null, 88, null));
        Iterator<T> it3 = plan.getSteps().iterator();
        int i = 0;
        while (true) {
            Object stepId = null;
            if (it3.hasNext()) {
                int i2 = i;
                i++;
                PlanStep step = (PlanStep) it3.next();
                PlanStep planStep = (PlanStep) CollectionsKt.getOrNull(plan.getSteps(), i2 + 1);
                stepId = planStep != null ? planStep.getStepId() : null;
                switch (WhenMappings.$EnumSwitchMapping$0[step.getKind().ordinal()]) {
                    case 1:
                        type = WorkflowNodeType.ACTION;
                        break;
                    case 2:
                    case 3:
                        type = WorkflowNodeType.CONDITION;
                        break;
                    case 4:
                        type = WorkflowNodeType.LOOP;
                        break;
                    case 5:
                        type = WorkflowNodeType.END;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                List list = nodes;
                String stepId2 = step.getStepId();
                String displaySummary = step.getDisplaySummary();
                if (StringsKt.isBlank(displaySummary)) {
                    String toolId = step.getToolId();
                    if (toolId == null) {
                        toolId = step.getKind().name();
                    }
                    displaySummary = toolId;
                }
                String str = displaySummary;
                String toolId2 = step.getToolId();
                Map<String, String> parameters = step.getParameters();
                String branchOnSuccess = step.getBranchOnSuccess();
                list.add(new WorkflowNode(stepId2, type, str, toolId2, parameters, branchOnSuccess == null ? stepId : branchOnSuccess, step.getBranchOnFailure()));
            } else {
                List $this$none$iv = nodes;
                if (($this$none$iv instanceof Collection) && $this$none$iv.isEmpty()) {
                    z = true;
                } else {
                    Iterator it4 = $this$none$iv.iterator();
                    while (true) {
                        if (it4.hasNext()) {
                            WorkflowNode it5 = (WorkflowNode) it4.next();
                            if (it5.getType() == WorkflowNodeType.END) {
                                z = false;
                            }
                        } else {
                            z = true;
                        }
                    }
                }
                if (z) {
                    nodes.add(new WorkflowNode("end", WorkflowNodeType.END, "End", null, null, null, null, 120, null));
                    ListIterator iterator$iv = nodes.listIterator(nodes.size());
                    while (iterator$iv.hasPrevious()) {
                        Object element$iv = iterator$iv.previous();
                        WorkflowNode it6 = (WorkflowNode) element$iv;
                        if ((it6.getType() == WorkflowNodeType.END || Intrinsics.areEqual(it6.getId(), "trigger")) ? false : true) {
                            stepId = element$iv;
                            lastAction = (WorkflowNode) stepId;
                            if (lastAction != null && lastAction.getNextOnSuccess() == null) {
                                index$iv = 0;
                                it = nodes.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        Object item$iv = it.next();
                                        it2 = (WorkflowNode) item$iv;
                                        if (Intrinsics.areEqual(it2.getId(), lastAction.getId())) {
                                            index$iv++;
                                        }
                                    } else {
                                        index$iv = -1;
                                    }
                                }
                                int idx = index$iv;
                                nodes.set(idx, WorkflowNode.copy$default(lastAction, null, null, null, null, null, "end", null, 95, null));
                            }
                        }
                    }
                    lastAction = (WorkflowNode) stepId;
                    if (lastAction != null) {
                        index$iv = 0;
                        it = nodes.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Object item$iv2 = it.next();
                                it2 = (WorkflowNode) item$iv2;
                                if (Intrinsics.areEqual(it2.getId(), lastAction.getId())) {
                                    index$iv++;
                                }
                            } else {
                                index$iv = -1;
                            }
                        }
                        int idx2 = index$iv;
                        nodes.set(idx2, WorkflowNode.copy$default(lastAction, null, null, null, null, null, "end", null, 95, null));
                    }
                }
                return new WorkflowGraph("wf-" + plan.getPlanId(), title, "trigger", nodes);
            }
        }
    }

    public static /* synthetic */ ActionPlan toActionPlan$default(WorkflowConverter workflowConverter, WorkflowGraph workflowGraph, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "wf-goal";
        }
        return workflowConverter.toActionPlan(workflowGraph, str);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0209  */
    /* JADX WARN: Code duplicated, block: B:54:0x0211  */
    /* JADX WARN: Code duplicated, block: B:55:0x0217  */
    /* JADX WARN: Code duplicated, block: B:61:0x0245  */
    /* JADX WARN: Code duplicated, block: B:64:0x024f  */
    /* JADX WARN: Code duplicated, block: B:66:0x025f  */
    /* JADX WARN: Code duplicated, block: B:67:0x0261  */
    /* JADX WARN: Code duplicated, block: B:72:0x0268  */
    /* JADX WARN: Code duplicated, block: B:73:0x026c  */
    /* JADX WARN: Code duplicated, block: B:91:0x0265 A[SYNTHETIC] */
    public final ActionPlan toActionPlan(WorkflowGraph graph, String goalId) {
        boolean z;
        Object element$iv;
        WorkflowNode workflowNode;
        PlanStep planStep;
        String entry;
        List $this$any$iv;
        Iterator it;
        PlanStep it2;
        boolean z2;
        ActionPlanStatus actionPlanStatus;
        String nextOnSuccess;
        WorkflowNode it3;
        PlanStepKind kind;
        PlanStep planStep2;
        Intrinsics.checkNotNullParameter(graph, "graph");
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        Iterable $this$filter$iv = graph.getNodes();
        Collection destination$iv$iv = new ArrayList();
        Iterator it4 = $this$filter$iv.iterator();
        while (true) {
            z = true;
            if (!it4.hasNext()) {
                break;
            }
            Object element$iv$iv = it4.next();
            WorkflowNode it5 = (WorkflowNode) element$iv$iv;
            if (it5.getType() != WorkflowNodeType.TRIGGER) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            WorkflowNode node = (WorkflowNode) item$iv$iv;
            switch (WhenMappings.$EnumSwitchMapping$1[node.getType().ordinal()]) {
                case 1:
                    kind = PlanStepKind.TOOL;
                    break;
                case 2:
                    kind = PlanStepKind.BRANCH;
                    break;
                case 3:
                    kind = PlanStepKind.WAIT;
                    break;
                case 4:
                    kind = PlanStepKind.NOOP;
                    break;
                case 5:
                    kind = PlanStepKind.NOOP;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                case 1:
                    String id = node.getId();
                    String toolId = node.getToolId();
                    if (toolId == null) {
                        toolId = "noop.tool";
                    }
                    planStep2 = new PlanStep(id, kind, toolId, null, node.getParameters(), null, null, null, false, null, 0L, node.getNextOnSuccess(), node.getNextOnFailure(), false, node.getLabel(), null, 42984, null);
                    break;
                case 2:
                default:
                    planStep2 = new PlanStep(node.getId(), PlanStepKind.NOOP, null, null, null, null, null, null, false, null, 0L, null, null, false, node.getLabel(), null, 49148, null);
                    break;
                case 3:
                    String id2 = node.getId();
                    String label = node.getLabel();
                    String nextOnSuccess2 = node.getNextOnSuccess();
                    if (nextOnSuccess2 == null) {
                        nextOnSuccess2 = ((WorkflowNode) CollectionsKt.last((List) graph.getNodes())).getId();
                    }
                    String str = nextOnSuccess2;
                    String nextOnFailure = node.getNextOnFailure();
                    if (nextOnFailure == null) {
                        nextOnFailure = ((WorkflowNode) CollectionsKt.last((List) graph.getNodes())).getId();
                    }
                    planStep2 = new PlanStep(id2, kind, null, null, null, null, null, new PlanStepCondition(PlanConditionOp.ALWAYS, null, null, null, 14, null), false, null, 0L, str, nextOnFailure, false, label, null, 42876, null);
                    break;
                case 4:
                    planStep2 = new PlanStep(node.getId(), kind, null, null, null, null, null, null, false, null, 100L, null, null, false, node.getLabel(), null, 48124, null);
                    break;
            }
            destination$iv$iv2.add(planStep2);
        }
        List steps = (List) destination$iv$iv2;
        Iterable $this$firstOrNull$iv = graph.getNodes();
        Iterator it6 = $this$firstOrNull$iv.iterator();
        do {
            if (it6.hasNext()) {
                element$iv = it6.next();
                it3 = (WorkflowNode) element$iv;
            } else {
                element$iv = null;
            }
            workflowNode = (WorkflowNode) element$iv;
            if (workflowNode != null || (nextOnSuccess = workflowNode.getNextOnSuccess()) == null) {
                planStep = (PlanStep) CollectionsKt.firstOrNull(steps);
                if (planStep != null) {
                    entry = planStep.getStepId();
                } else {
                    entry = null;
                }
            } else {
                entry = nextOnSuccess;
            }
            String str2 = "plan-from-" + graph.getId();
            $this$any$iv = steps;
            if (($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                it = $this$any$iv.iterator();
                do {
                    if (it.hasNext()) {
                        Object element$iv2 = it.next();
                        it2 = (PlanStep) element$iv2;
                        if (it2.getKind() == PlanStepKind.TOOL) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    } else {
                        z = false;
                    }
                } while (!z2);
            } else {
                z = false;
            }
            if (z) {
                actionPlanStatus = ActionPlanStatus.READY;
            } else {
                actionPlanStatus = ActionPlanStatus.EMPTY;
            }
            return new ActionPlan(str2, goalId, actionPlanStatus, steps, entry, false, null, CollectionsKt.listOf("from_workflow=" + graph.getId()), 96, null);
        } while (!Intrinsics.areEqual(it3.getId(), graph.getEntryNodeId()));
        workflowNode = (WorkflowNode) element$iv;
        if (workflowNode != null) {
            planStep = (PlanStep) CollectionsKt.firstOrNull(steps);
            if (planStep != null) {
                entry = planStep.getStepId();
            } else {
                entry = null;
            }
        } else {
            planStep = (PlanStep) CollectionsKt.firstOrNull(steps);
            if (planStep != null) {
                entry = planStep.getStepId();
            } else {
                entry = null;
            }
        }
        String str3 = "plan-from-" + graph.getId();
        $this$any$iv = steps;
        if ($this$any$iv instanceof Collection) {
            it = $this$any$iv.iterator();
            do {
                if (it.hasNext()) {
                    Object element$iv3 = it.next();
                    it2 = (PlanStep) element$iv3;
                    if (it2.getKind() == PlanStepKind.TOOL) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                } else {
                    z = false;
                }
            } while (!z2);
        } else {
            it = $this$any$iv.iterator();
            do {
                if (it.hasNext()) {
                    Object element$iv4 = it.next();
                    it2 = (PlanStep) element$iv4;
                    if (it2.getKind() == PlanStepKind.TOOL) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                } else {
                    z = false;
                }
            } while (!z2);
        }
        if (z) {
            actionPlanStatus = ActionPlanStatus.READY;
        } else {
            actionPlanStatus = ActionPlanStatus.EMPTY;
        }
        return new ActionPlan(str3, goalId, actionPlanStatus, steps, entry, false, null, CollectionsKt.listOf("from_workflow=" + graph.getId()), 96, null);
    }
}
