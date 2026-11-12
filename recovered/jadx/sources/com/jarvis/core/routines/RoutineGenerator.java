package com.jarvis.core.routines;

import com.jarvis.core.discovery.DiscoveryResult;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.ActionPlanValidation;
import com.jarvis.core.planner.ActionPlanValidator;
import com.jarvis.core.planner.DeterministicAIPlanner;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.planner.PlanStepKind;
import com.jarvis.core.planner.PlannerContext;
import com.jarvis.core.planner.UserGoal;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RoutineModels.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\rJ\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\rJ\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/routines/RoutineGenerator;", "", "planner", "Lcom/jarvis/core/planner/DeterministicAIPlanner;", "<init>", "(Lcom/jarvis/core/planner/DeterministicAIPlanner;)V", "generate", "Lcom/jarvis/core/routines/RoutineDraft;", "naturalLanguage", "", "discovery", "Lcom/jarvis/core/discovery/DiscoveryResult;", "nowMillis", "", "approve", "Lcom/jarvis/core/routines/SavedRoutine;", "draft", "title", "reject", "buildPreview", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RoutineGenerator {
    private final DeterministicAIPlanner planner;

    /* JADX INFO: compiled from: RoutineModels.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
                iArr[PlanStepKind.WAIT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PlanStepKind.BRANCH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[PlanStepKind.NOOP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RoutineGenerator() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public RoutineGenerator(DeterministicAIPlanner planner) {
        Intrinsics.checkNotNullParameter(planner, "planner");
        this.planner = planner;
    }

    public /* synthetic */ RoutineGenerator(DeterministicAIPlanner deterministicAIPlanner, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DeterministicAIPlanner() : deterministicAIPlanner);
    }

    public static /* synthetic */ RoutineDraft generate$default(RoutineGenerator routineGenerator, String str, DiscoveryResult discoveryResult, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = System.currentTimeMillis();
        }
        return routineGenerator.generate(str, discoveryResult, j);
    }

    public final RoutineDraft generate(String naturalLanguage, DiscoveryResult discovery, long nowMillis) {
        Intrinsics.checkNotNullParameter(naturalLanguage, "naturalLanguage");
        Intrinsics.checkNotNullParameter(discovery, "discovery");
        UserGoal goal = new UserGoal("routine-" + UUID.randomUUID(), StringsKt.take(naturalLanguage, 2000), nowMillis);
        ActionPlan plan = this.planner.plan(goal, new PlannerContext(discovery, null, 6, false, 0L, 18, null));
        ActionPlanValidation validation = ActionPlanValidator.INSTANCE.validate(plan);
        String summary = buildPreview(plan);
        return new RoutineDraft("draft-" + UUID.randomUUID(), naturalLanguage, plan, validation.getOk() && plan.getStatus() == ActionPlanStatus.READY, validation.getErrors(), summary);
    }

    public static /* synthetic */ SavedRoutine approve$default(RoutineGenerator routineGenerator, RoutineDraft routineDraft, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            j = System.currentTimeMillis();
        }
        return routineGenerator.approve(routineDraft, str, j);
    }

    public final SavedRoutine approve(RoutineDraft draft, String title, long nowMillis) {
        Intrinsics.checkNotNullParameter(draft, "draft");
        if (!draft.getValidationOk()) {
            return null;
        }
        String str = "routine-" + UUID.randomUUID();
        String strTake = title == null ? StringsKt.take(draft.getNaturalLanguage(), 60) : title;
        if (StringsKt.isBlank(strTake)) {
            strTake = "Routine";
        }
        return new SavedRoutine(str, strTake, draft.getNaturalLanguage(), draft.getPlan(), RoutineStatus.SAVED, nowMillis, Long.valueOf(nowMillis), null, CollectionsKt.listOf("approved_from=" + draft.getDraftId()), 128, null);
    }

    public static /* synthetic */ SavedRoutine reject$default(RoutineGenerator routineGenerator, RoutineDraft routineDraft, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        return routineGenerator.reject(routineDraft, j);
    }

    public final SavedRoutine reject(RoutineDraft draft, long nowMillis) {
        Intrinsics.checkNotNullParameter(draft, "draft");
        String str = "routine-" + UUID.randomUUID();
        String strTake = StringsKt.take(draft.getNaturalLanguage(), 60);
        if (StringsKt.isBlank(strTake)) {
            strTake = "Rejected";
        }
        return new SavedRoutine(str, strTake, draft.getNaturalLanguage(), draft.getPlan(), RoutineStatus.REJECTED, nowMillis, null, null, CollectionsKt.listOf("rejected_from=" + draft.getDraftId()), 192, null);
    }

    private final String buildPreview(ActionPlan plan) {
        return plan.getSteps().isEmpty() ? "Пустой план" : StringsKt.take(CollectionsKt.joinToString$default(plan.getSteps(), " → ", null, null, 0, null, new Function1() { // from class: com.jarvis.core.routines.RoutineGenerator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RoutineGenerator.buildPreview$lambda$0((PlanStep) obj);
            }
        }, 30, null), 240);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence buildPreview$lambda$0(PlanStep step) {
        Intrinsics.checkNotNullParameter(step, "step");
        switch (WhenMappings.$EnumSwitchMapping$0[step.getKind().ordinal()]) {
            case 1:
                String toolId = step.getToolId();
                if (toolId == null) {
                    toolId = step.getDisplaySummary();
                }
                return toolId;
            case 2:
                return "CONFIRM";
            case 3:
                return "WAIT(" + step.getWaitMs() + "ms)";
            case 4:
                return "BRANCH";
            case 5:
                return "NOOP";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
