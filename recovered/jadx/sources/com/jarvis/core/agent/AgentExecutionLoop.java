package com.jarvis.core.agent;

import com.jarvis.core.autonomy.AutonomyActionPipeline;
import com.jarvis.core.autonomy.AutonomyActionPlan;
import com.jarvis.core.autonomy.AutonomyPlanStatus;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.autonomy.ProposedActionMapper;
import com.jarvis.core.autonomy.SafeAutonomousExecutor;
import com.jarvis.core.autonomy.SafeAutonomousResult;
import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.nativeandroid.NativeAndroidToolCatalog;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.PlanConditionOp;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.planner.PlanStepCondition;
import com.jarvis.core.planner.PlanStepKind;
import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import com.jarvis.core.proactive.ProactiveReasonCode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AgentExecutionLoop.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u00012B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJJ\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00172\u0006\u0010\u0018\u001a\u00020\u00152\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aH\u0002J(\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J8\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0015H\u0002JL\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010$\u001a\u0004\u0018\u00010\u001b2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J$\u0010+\u001a\u00020&2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aH\u0002J8\u0010-\u001a\u00020&2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u0010\u0010/\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J0\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010$\u001a\u00020\u001b2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/jarvis/core/agent/AgentExecutionLoop;", "", "pipeline", "Lcom/jarvis/core/autonomy/AutonomyActionPipeline;", "toolExecutor", "Lcom/jarvis/core/common/ToolExecutor;", "safeExecutor", "Lcom/jarvis/core/autonomy/SafeAutonomousExecutor;", "<init>", "(Lcom/jarvis/core/autonomy/AutonomyActionPipeline;Lcom/jarvis/core/common/ToolExecutor;Lcom/jarvis/core/autonomy/SafeAutonomousExecutor;)V", "run", "Lcom/jarvis/core/agent/AgentLoopResult;", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "context", "Lcom/jarvis/core/agent/AgentLoopContext;", "executeStep", "Lcom/jarvis/core/agent/AgentExecutionLoop$StepOutcome;", "step", "Lcom/jarvis/core/planner/PlanStep;", "attempt", "", "steps", "", "index", "stepStatus", "", "", "Lcom/jarvis/core/agent/StepRunStatus;", "runToolStep", "runLiveSafe", "proposed", "Lcom/jarvis/core/autonomy/ProposedAction;", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "toolOutcome", "toolId", "ok", "", "message", "executed", "autonomyStatus", "Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "depsSatisfied", "status", "conditionHolds", "slots", "syntheticDecision", "mergeDefaultArgs", "params", "StepOutcome", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AgentExecutionLoop {
    private final AutonomyActionPipeline pipeline;
    private final SafeAutonomousExecutor safeExecutor;
    private final ToolExecutor toolExecutor;

    /* JADX INFO: compiled from: AgentExecutionLoop.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[LoopDecision.values().length];
            try {
                iArr[LoopDecision.CONTINUE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LoopDecision.RETRY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LoopDecision.MODIFY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LoopDecision.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PlanStepKind.values().length];
            try {
                iArr2[PlanStepKind.NOOP.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[PlanStepKind.WAIT.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[PlanStepKind.CONFIRM.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[PlanStepKind.BRANCH.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[PlanStepKind.TOOL.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[AutonomyPlanStatus.values().length];
            try {
                iArr3[AutonomyPlanStatus.READY_TO_EXECUTE.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr3[AutonomyPlanStatus.AWAITING_CONFIRMATION.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr3[AutonomyPlanStatus.NEEDS_CONFIRMATION.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr3[AutonomyPlanStatus.DENIED.ordinal()] = 4;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr3[AutonomyPlanStatus.NO_ACTION.ordinal()] = 5;
            } catch (NoSuchFieldError e14) {
            }
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[PlanConditionOp.values().length];
            try {
                iArr4[PlanConditionOp.ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr4[PlanConditionOp.PREV_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr4[PlanConditionOp.PREV_FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr4[PlanConditionOp.RESULT_EQUALS.ordinal()] = 4;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr4[PlanConditionOp.RESULT_CONTAINS.ordinal()] = 5;
            } catch (NoSuchFieldError e19) {
            }
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    public AgentExecutionLoop() {
        this(null, null, null, 7, null);
    }

    public AgentExecutionLoop(AutonomyActionPipeline pipeline, ToolExecutor toolExecutor, SafeAutonomousExecutor safeExecutor) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        this.pipeline = pipeline;
        this.toolExecutor = toolExecutor;
        this.safeExecutor = safeExecutor;
    }

    public /* synthetic */ AgentExecutionLoop(AutonomyActionPipeline autonomyActionPipeline, ToolExecutor toolExecutor, SafeAutonomousExecutor safeAutonomousExecutor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new AutonomyActionPipeline(null, null, null, 7, null) : autonomyActionPipeline, (i & 2) != 0 ? null : toolExecutor, (i & 4) != 0 ? null : safeAutonomousExecutor);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x03b4  */
    public final AgentLoopResult run(ActionPlan plan, AgentLoopContext context) {
        int index;
        LoopDecision finalDecision;
        LoopDecision finalDecision2;
        String stoppedReason;
        boolean z;
        boolean z2;
        boolean allOk;
        AgentExecutionLoop agentExecutionLoop = this;
        Intrinsics.checkNotNullParameter(plan, "plan");
        Intrinsics.checkNotNullParameter(context, "context");
        if (plan.getStatus() == ActionPlanStatus.CANCELLED || plan.getCancellationRequested()) {
            return new AgentLoopResult(plan.getPlanId(), plan.getGoalId(), false, LoopDecision.STOP, "plan_cancelled", CollectionsKt.emptyList(), MapsKt.emptyMap(), CollectionsKt.listOf("cancelled_before_run"));
        }
        int i = 0;
        if (plan.getStatus() != ActionPlanStatus.READY || plan.getSteps().isEmpty()) {
            return new AgentLoopResult(plan.getPlanId(), plan.getGoalId(), plan.getStatus() == ActionPlanStatus.EMPTY, LoopDecision.STOP, "plan_not_ready:" + plan.getStatus(), CollectionsKt.emptyList(), MapsKt.emptyMap(), CollectionsKt.take(plan.getNotes(), 4));
        }
        List records = new ArrayList();
        LinkedHashMap stepStatus = new LinkedHashMap();
        LinkedHashMap resultSlots = new LinkedHashMap();
        Map attempts = new LinkedHashMap();
        List notes = CollectionsKt.mutableListOf("loop=stage12", "live=" + context.getLive());
        List<PlanStep> steps = plan.getSteps();
        LoopDecision finalDecision3 = LoopDecision.CONTINUE;
        String stoppedReason2 = "completed";
        int index2 = 0;
        while (true) {
            int index3 = steps.size();
            if (index2 >= index3) {
                index = index2;
                finalDecision = finalDecision3;
            } else {
                PlanStep step = steps.get(index2);
                StepRunStatus prior = (StepRunStatus) stepStatus.get(step.getStepId());
                if (prior == StepRunStatus.SUCCESS || prior == StepRunStatus.SKIPPED) {
                    index2++;
                    i = 0;
                    agentExecutionLoop = this;
                } else {
                    Integer num = (Integer) attempts.get(step.getStepId());
                    int attempt = (num != null ? num.intValue() : i) + 1;
                    attempts.put(step.getStepId(), Integer.valueOf(attempt));
                    if (!agentExecutionLoop.depsSatisfied(step, stepStatus)) {
                        records.add(new StepRunRecord(step.getStepId(), StepRunStatus.SKIPPED, attempt, null, null, false, "dependencies not satisfied", LoopDecision.CONTINUE, null, 312, null));
                        stepStatus.put(step.getStepId(), StepRunStatus.SKIPPED);
                        index2++;
                    } else if (!agentExecutionLoop.conditionHolds(step, stepStatus, resultSlots)) {
                        records.add(new StepRunRecord(step.getStepId(), StepRunStatus.SKIPPED, attempt, null, null, false, "condition not met: " + step.getCondition().getOp(), LoopDecision.CONTINUE, null, 312, null));
                        stepStatus.put(step.getStepId(), StepRunStatus.SKIPPED);
                        index2++;
                        i = 0;
                    } else {
                        index = index2;
                        StepOutcome outcome = executeStep(step, attempt, context, steps, index2, stepStatus);
                        records.add(outcome.getRecord());
                        stepStatus.put(step.getStepId(), outcome.getRecord().getStatus());
                        if (outcome.getRecord().getStatus() == StepRunStatus.SUCCESS || outcome.getRecord().getStatus() == StepRunStatus.FAILURE) {
                            LinkedHashMap linkedHashMap = resultSlots;
                            String stepId = step.getStepId();
                            String message = outcome.getRecord().getMessage();
                            if (StringsKt.isBlank(message)) {
                                message = outcome.getRecord().getStatus().name();
                            }
                            linkedHashMap.put(stepId, message);
                        }
                        switch (WhenMappings.$EnumSwitchMapping$0[outcome.getRecord().getDecisionAfter().ordinal()]) {
                            case 1:
                                index2 = outcome.getNextIndex();
                                i = 0;
                                agentExecutionLoop = this;
                                continue;
                            case 2:
                                if (attempt < RangesKt.coerceAtMost(step.getRetry().getMaxAttempts(), context.getMaxRetriesPerStep())) {
                                    index2 = index;
                                    i = 0;
                                    agentExecutionLoop = this;
                                } else {
                                    LoopDecision finalDecision4 = LoopDecision.STOP;
                                    stoppedReason2 = "retry_exhausted:" + step.getStepId();
                                    notes.add("retry_exhausted=" + step.getStepId());
                                    int lastIndex = CollectionsKt.getLastIndex(records);
                                    StepRunRecord record = outcome.getRecord();
                                    records.set(lastIndex, record.copy((383 & 1) != 0 ? record.stepId : null, (383 & 2) != 0 ? record.status : null, (383 & 4) != 0 ? record.attempt : 0, (383 & 8) != 0 ? record.autonomyStatus : null, (383 & 16) != 0 ? record.toolId : null, (383 & 32) != 0 ? record.executed : false, (383 & 64) != 0 ? record.message : null, (383 & 128) != 0 ? record.decisionAfter : LoopDecision.STOP, (383 & 256) != 0 ? record.notes : null));
                                    finalDecision = finalDecision4;
                                }
                                break;
                            case 3:
                                LoopDecision finalDecision5 = LoopDecision.MODIFY;
                                stoppedReason2 = "modify_needed:" + step.getStepId() + ":" + outcome.getRecord().getMessage();
                                notes.add("modify=" + step.getStepId());
                                finalDecision = finalDecision5;
                                break;
                            case 4:
                                LoopDecision finalDecision6 = LoopDecision.STOP;
                                String message2 = outcome.getRecord().getMessage();
                                if (StringsKt.isBlank(message2)) {
                                    message2 = "stop:" + step.getStepId();
                                }
                                String stoppedReason3 = message2;
                                stoppedReason2 = stoppedReason3;
                                finalDecision = finalDecision6;
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    }
                }
            }
        }
        if (finalDecision == LoopDecision.CONTINUE && index >= steps.size()) {
            Iterable iterableValues = stepStatus.values();
            Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
            Iterable $this$all$iv = iterableValues;
            if (((Collection) $this$all$iv).isEmpty()) {
                allOk = true;
            } else {
                Iterator it = $this$all$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        StepRunStatus it2 = (StepRunStatus) element$iv;
                        LoopDecision finalDecision7 = finalDecision;
                        if (it2 == StepRunStatus.SUCCESS || it2 == StepRunStatus.SKIPPED) {
                            finalDecision = finalDecision7;
                        } else {
                            allOk = false;
                        }
                    } else {
                        allOk = true;
                    }
                }
            }
            LoopDecision finalDecision8 = LoopDecision.STOP;
            String stoppedReason4 = allOk ? "completed" : "finished_with_issues";
            finalDecision2 = finalDecision8;
            stoppedReason = stoppedReason4;
        } else {
            finalDecision2 = finalDecision;
            stoppedReason = stoppedReason2;
        }
        if (!Intrinsics.areEqual(stoppedReason, "completed") || stepStatus.isEmpty()) {
            z = false;
        } else {
            Iterable iterableValues2 = stepStatus.values();
            Intrinsics.checkNotNullExpressionValue(iterableValues2, "<get-values>(...)");
            Iterable $this$all$iv2 = iterableValues2;
            if (((Collection) $this$all$iv2).isEmpty()) {
                z2 = true;
            } else {
                Iterator it3 = $this$all$iv2.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv2 = it3.next();
                        StepRunStatus it4 = (StepRunStatus) element$iv2;
                        if (!(it4 == StepRunStatus.SUCCESS || it4 == StepRunStatus.SKIPPED)) {
                            z2 = false;
                        }
                    } else {
                        z2 = true;
                    }
                }
            }
            if (z2) {
                z = true;
            } else {
                z = false;
            }
        }
        boolean reallyCompleted = z;
        return new AgentLoopResult(plan.getPlanId(), plan.getGoalId(), reallyCompleted, finalDecision2, stoppedReason, records, resultSlots, notes);
    }

    /* JADX INFO: compiled from: AgentExecutionLoop.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/agent/AgentExecutionLoop$StepOutcome;", "", "record", "Lcom/jarvis/core/agent/StepRunRecord;", "nextIndex", "", "<init>", "(Lcom/jarvis/core/agent/StepRunRecord;I)V", "getRecord", "()Lcom/jarvis/core/agent/StepRunRecord;", "getNextIndex", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class StepOutcome {
        private final int nextIndex;
        private final StepRunRecord record;

        public static /* synthetic */ StepOutcome copy$default(StepOutcome stepOutcome, StepRunRecord stepRunRecord, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                stepRunRecord = stepOutcome.record;
            }
            if ((i2 & 2) != 0) {
                i = stepOutcome.nextIndex;
            }
            return stepOutcome.copy(stepRunRecord, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StepRunRecord getRecord() {
            return this.record;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getNextIndex() {
            return this.nextIndex;
        }

        public final StepOutcome copy(StepRunRecord record, int nextIndex) {
            Intrinsics.checkNotNullParameter(record, "record");
            return new StepOutcome(record, nextIndex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StepOutcome)) {
                return false;
            }
            StepOutcome stepOutcome = (StepOutcome) other;
            return Intrinsics.areEqual(this.record, stepOutcome.record) && this.nextIndex == stepOutcome.nextIndex;
        }

        public int hashCode() {
            return (this.record.hashCode() * 31) + Integer.hashCode(this.nextIndex);
        }

        public String toString() {
            return "StepOutcome(record=" + this.record + ", nextIndex=" + this.nextIndex + ")";
        }

        public StepOutcome(StepRunRecord record, int nextIndex) {
            Intrinsics.checkNotNullParameter(record, "record");
            this.record = record;
            this.nextIndex = nextIndex;
        }

        public final int getNextIndex() {
            return this.nextIndex;
        }

        public final StepRunRecord getRecord() {
            return this.record;
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00aa  */
    private final StepOutcome executeStep(PlanStep step, int attempt, AgentLoopContext context, List<PlanStep> steps, int index, Map<String, ? extends StepRunStatus> stepStatus) {
        int iIntValue;
        switch (WhenMappings.$EnumSwitchMapping$1[step.getKind().ordinal()]) {
            case 1:
                return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.SUCCESS, attempt, null, null, false, "noop", LoopDecision.CONTINUE, null, 312, null), index + 1);
            case 2:
                return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.SUCCESS, attempt, null, null, false, "waited_logical:" + step.getWaitMs(), LoopDecision.CONTINUE, CollectionsKt.listOf("no_thread_sleep"), 56, null), index + 1);
            case 3:
                return context.getConfirmedStepIds().contains(step.getStepId()) ? new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.SUCCESS, attempt, null, null, false, "confirmed", LoopDecision.CONTINUE, null, 312, null), index + 1) : new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.AWAITING_CONFIRMATION, attempt, null, null, false, "awaiting_confirmation", LoopDecision.STOP, null, 312, null), index);
            case 4:
                String prev = (String) CollectionsKt.firstOrNull((List) step.getDependsOn());
                boolean prevOk = prev != null && stepStatus.get(prev) == StepRunStatus.SUCCESS;
                String targetId = prevOk ? step.getBranchOnSuccess() : step.getBranchOnFailure();
                if (targetId != null) {
                    int index$iv = 0;
                    Iterator<PlanStep> it = steps.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object item$iv = it.next();
                            PlanStep it2 = (PlanStep) item$iv;
                            if (!Intrinsics.areEqual(it2.getStepId(), targetId)) {
                                index$iv++;
                            }
                        } else {
                            index$iv = -1;
                        }
                    }
                    Integer numValueOf = Integer.valueOf(index$iv);
                    int it3 = numValueOf.intValue();
                    if (!(it3 >= 0)) {
                        numValueOf = null;
                    }
                    if (numValueOf != null) {
                        iIntValue = numValueOf.intValue();
                    } else {
                        iIntValue = index + 1;
                    }
                } else {
                    iIntValue = index + 1;
                }
                int targetIndex = iIntValue;
                return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.SUCCESS, attempt, null, null, false, "branch→" + (targetId == null ? "end" : targetId), LoopDecision.CONTINUE, CollectionsKt.listOf("prevOk=" + prevOk), 56, null), targetIndex);
            case 5:
                return runToolStep(step, attempt, context, index);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final StepOutcome runToolStep(PlanStep step, int attempt, AgentLoopContext context, int index) {
        String toolId = step.getToolId();
        String str = toolId;
        if (str == null || StringsKt.isBlank(str)) {
            return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.FAILURE, attempt, null, null, false, "missing_toolId", LoopDecision.MODIFY, null, 312, null), index);
        }
        ProposedActionMapper proposedActionMapper = ProposedActionMapper.INSTANCE;
        Map<String, String> mapMergeDefaultArgs = mergeDefaultArgs(toolId, step.getParameters());
        String displaySummary = step.getDisplaySummary();
        if (StringsKt.isBlank(displaySummary)) {
            displaySummary = null;
        }
        ProposedAction proposed = proposedActionMapper.fromToolId(toolId, toolId, mapMergeDefaultArgs, displaySummary);
        if (proposed == null) {
            return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.FAILURE, attempt, null, toolId, false, "unknown_tool_mapping", LoopDecision.MODIFY, null, 296, null), index);
        }
        ProactiveDecision decision = syntheticDecision(step);
        if (context.getLive() && this.safeExecutor != null && ProposedActionMapper.INSTANCE.getFIRST_SAFE_TOOL_IDS().contains(toolId)) {
            return runLiveSafe(step, proposed, decision, attempt, context, index);
        }
        AutonomyActionPlan autonomyPlan = AutonomyActionPipeline.plan$default(this.pipeline, decision, proposed, context.getAutonomyLevel(), context.getAllowlist(), context.getBlocklist(), context.getNowMillis(), null, context.getGrantedPermissions(), null, 0L, null, null, null, 8000, null);
        switch (WhenMappings.$EnumSwitchMapping$2[autonomyPlan.getStatus().ordinal()]) {
            case 1:
                if (!context.getLive() || this.toolExecutor == null) {
                    return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.SUCCESS, attempt, autonomyPlan.getStatus(), toolId, false, "dry_run_ready", LoopDecision.CONTINUE, CollectionsKt.take(autonomyPlan.getNotes(), 3)), index + 1);
                }
                boolean liveOk = NativeAndroidToolCatalog.INSTANCE.liveAllowed(toolId);
                if (!liveOk) {
                    return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.BLOCKED, attempt, autonomyPlan.getStatus(), toolId, false, "live_blocked_stage14_mode", LoopDecision.STOP, null, 288, null), index);
                }
                ToolCall draftToolCall = autonomyPlan.getDraftToolCall();
                if (draftToolCall == null) {
                    String stepId = step.getStepId();
                    Map<String, String> arguments = proposed.getArguments();
                    Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(arguments.size()));
                    Iterable $this$associateByTo$iv$iv$iv = arguments.entrySet();
                    for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                        Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                        Object key = it$iv$iv.getKey();
                        Map.Entry it = (Map.Entry) element$iv$iv$iv;
                        Object value = it.getValue();
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Any");
                        destination$iv$iv.put(key, value);
                        arguments = arguments;
                    }
                    draftToolCall = new ToolCall(null, stepId, toolId, destination$iv$iv, 1, null);
                }
                ToolCall call = draftToolCall;
                ToolResult result = this.toolExecutor.execute(call);
                boolean ok = result.getStatus() == ToolResultStatus.SUCCESS;
                String message = result.getMessage();
                if (message == null) {
                    message = result.getStatus().name();
                }
                return toolOutcome(step, attempt, toolId, ok, message, true, autonomyPlan.getStatus(), index);
            case 2:
            case 3:
                return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.AWAITING_CONFIRMATION, attempt, autonomyPlan.getStatus(), toolId, false, "gate_needs_confirmation", LoopDecision.STOP, CollectionsKt.take(autonomyPlan.getNotes(), 3), 32, null), index);
            case 4:
                boolean canRetry = attempt < step.getRetry().getMaxAttempts() && !step.getCancelOnFailure();
                return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.BLOCKED, attempt, autonomyPlan.getStatus(), toolId, false, "denied:" + autonomyPlan.getVerdict().getReasonCode(), canRetry ? LoopDecision.RETRY : LoopDecision.STOP, CollectionsKt.take(autonomyPlan.getNotes(), 3), 32, null), index);
            case 5:
                return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.SKIPPED, attempt, autonomyPlan.getStatus(), toolId, false, "no_action", LoopDecision.CONTINUE, null, 288, null), index + 1);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final StepOutcome runLiveSafe(PlanStep step, ProposedAction proposed, ProactiveDecision decision, int attempt, AgentLoopContext context, int index) {
        SafeAutonomousExecutor safeAutonomousExecutor = this.safeExecutor;
        Intrinsics.checkNotNull(safeAutonomousExecutor);
        SafeAutonomousResult result = safeAutonomousExecutor.tryExecute(decision, proposed, context.getAutonomyLevel(), context.getAllowlist(), context.getBlocklist(), context.getNowMillis());
        String toolId = proposed.getToolId();
        boolean executed = result.getExecuted();
        String reason = result.getReason();
        boolean executed2 = result.getExecuted();
        AutonomyActionPlan plan = result.getPlan();
        return toolOutcome(step, attempt, toolId, executed, reason, executed2, plan != null ? plan.getStatus() : null, index);
    }

    private final StepOutcome toolOutcome(PlanStep step, int attempt, String toolId, boolean ok, String message, boolean executed, AutonomyPlanStatus autonomyStatus, int index) {
        if (ok) {
            return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.SUCCESS, attempt, autonomyStatus, toolId, executed, message, LoopDecision.CONTINUE, null, 256, null), index + 1);
        }
        LoopDecision decisionAfter = (!step.getCancelOnFailure() && attempt < step.getRetry().getMaxAttempts()) ? LoopDecision.RETRY : LoopDecision.STOP;
        return new StepOutcome(new StepRunRecord(step.getStepId(), StepRunStatus.FAILURE, attempt, autonomyStatus, toolId, executed, message, decisionAfter, null, 256, null), index);
    }

    private final boolean depsSatisfied(PlanStep step, Map<String, ? extends StepRunStatus> status) {
        if (step.getDependsOn().isEmpty()) {
            return true;
        }
        Iterable $this$all$iv = step.getDependsOn();
        if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
            return true;
        }
        for (Object element$iv : $this$all$iv) {
            String dep = (String) element$iv;
            if (!(status.get(dep) == StepRunStatus.SUCCESS)) {
                return false;
            }
        }
        return true;
    }

    private final boolean conditionHolds(PlanStep step, Map<String, ? extends StepRunStatus> status, Map<String, String> slots) {
        String str;
        PlanStepCondition c = step.getCondition();
        switch (WhenMappings.$EnumSwitchMapping$3[c.getOp().ordinal()]) {
            case 1:
                return true;
            case 2:
                String id = c.getDependsOnStepId();
                return id != null && status.get(id) == StepRunStatus.SUCCESS;
            case 3:
                String id2 = c.getDependsOnStepId();
                return id2 != null && status.get(id2) == StepRunStatus.FAILURE;
            case 4:
                String id3 = c.getDependsOnStepId();
                if (id3 == null) {
                    return false;
                }
                return Intrinsics.areEqual(slots.get(id3), c.getExpectedValue());
            case 5:
                String id4 = c.getDependsOnStepId();
                if (id4 != null && (str = slots.get(id4)) != null) {
                    String str2 = str;
                    String expectedValue = c.getExpectedValue();
                    if (expectedValue == null) {
                        expectedValue = "";
                    }
                    return StringsKt.contains$default((CharSequence) str2, (CharSequence) expectedValue, false, 2, (Object) null);
                }
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final ProactiveDecision syntheticDecision(PlanStep step) {
        return new ProactiveDecision(ProactiveDecisionType.SUGGESTION, "agent.loop:" + step.getStepId(), null, null, ProactiveReasonCode.SELECTED, "Stage 12 agent loop step " + step.getStepId(), 0.85f, 0.85f, 0L, null, null, null, 3852, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final Map<String, String> mergeDefaultArgs(String toolId, Map<String, String> params) {
        LinkedHashMap result$iv = new LinkedHashMap();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String it = entry.getValue();
            if (!StringsKt.isBlank(it)) {
                result$iv.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap $this$filterValues$iv = result$iv;
        Map<String, String> mutableMap = MapsKt.toMutableMap($this$filterValues$iv);
        switch (toolId) {
            case "flashlight":
                mutableMap.putIfAbsent("action", "on");
            case "volume":
                mutableMap.putIfAbsent("action", "up");
            case "music":
                mutableMap.putIfAbsent("action", "play");
            case "brightness":
                mutableMap.putIfAbsent("action", "up");
            default:
                if (toolId.equals("flashlight")) {
                    mutableMap.putIfAbsent("action", "on");
                }
                return mutableMap;
        }
    }
}
