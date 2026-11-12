package com.jarvis.core.aioutput;

import com.jarvis.app.VoiceService;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.execution.CatalogToolRegistry;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanValidation;
import com.jarvis.core.planner.ActionPlanValidator;
import com.jarvis.core.planner.PlanStepKind;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StructuredAiSemanticValidator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u001e\u0010\f\u001a\u0004\u0018\u00010\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u000fH\u0002J&\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u000fH\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0002¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiSemanticValidator;", "", "<init>", "()V", "validate", "Lcom/jarvis/core/aioutput/SemanticValidationResult;", "envelope", "Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "toolRegistry", "Lcom/jarvis/core/execution/CatalogToolRegistry;", "task", "Lcom/jarvis/core/aioutput/StructuredAiTask;", "forbiddenArgs", "", "args", "", "validateAgainstDefinition", "def", "Lcom/jarvis/core/common/model/ToolDefinition;", "looksLikeExecutionClaim", "", VoiceService.EXTRA_TEXT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StructuredAiSemanticValidator {
    public static final StructuredAiSemanticValidator INSTANCE = new StructuredAiSemanticValidator();

    /* JADX INFO: compiled from: StructuredAiSemanticValidator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[StructuredAiResponseType.values().length];
            try {
                iArr[StructuredAiResponseType.ANSWER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StructuredAiResponseType.CLARIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[StructuredAiResponseType.TOOL_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[StructuredAiResponseType.PLAN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[StructuredAiResponseType.NO_ACTION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[StructuredAiResponseType.CONFIRMATION_REQUIRED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ParameterType.values().length];
            try {
                iArr2[ParameterType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[ParameterType.INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[ParameterType.ENUM.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[ParameterType.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private StructuredAiSemanticValidator() {
    }

    public static /* synthetic */ SemanticValidationResult validate$default(StructuredAiSemanticValidator structuredAiSemanticValidator, StructuredAiEnvelope structuredAiEnvelope, CatalogToolRegistry catalogToolRegistry, StructuredAiTask structuredAiTask, int i, Object obj) {
        if ((i & 2) != 0) {
            catalogToolRegistry = null;
        }
        if ((i & 4) != 0) {
            structuredAiTask = StructuredAiTask.CHAT;
        }
        return structuredAiSemanticValidator.validate(structuredAiEnvelope, catalogToolRegistry, structuredAiTask);
    }

    public final SemanticValidationResult validate(StructuredAiEnvelope envelope, CatalogToolRegistry toolRegistry, StructuredAiTask task) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(envelope, "envelope");
        Intrinsics.checkNotNullParameter(task, "task");
        switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
            case 1:
                String text = envelope.getText();
                if (text == null || StringsKt.isBlank(text)) {
                    return new SemanticValidationResult.Fail("empty_answer");
                }
                if (looksLikeExecutionClaim(envelope.getText()) && task != StructuredAiTask.CHAT) {
                    return new SemanticValidationResult.Fail("unverified_execution_claim");
                }
                break;
            case 2:
                String question = envelope.getQuestion();
                if (question == null || StringsKt.isBlank(question)) {
                    return new SemanticValidationResult.Fail("empty_question");
                }
                if (envelope.getClarificationReason() == null) {
                    return new SemanticValidationResult.Fail("missing_reason");
                }
                break;
            case 3:
                String toolId = envelope.getToolId();
                String string = toolId != null ? StringsKt.trim((CharSequence) toolId).toString() : null;
                String toolId2 = string != null ? string : "";
                if (StringsKt.isBlank(toolId2)) {
                    return new SemanticValidationResult.Fail("missing_toolId");
                }
                String argFail = forbiddenArgs(envelope.getArguments());
                if (argFail != null) {
                    return new SemanticValidationResult.Fail(argFail);
                }
                if (toolRegistry != null) {
                    ToolDefinition def = toolRegistry.findDefinition(toolId2);
                    if (def != null) {
                        String argsFail = validateAgainstDefinition(def, envelope.getArguments());
                        if (argsFail != null) {
                            return new SemanticValidationResult.Fail(argsFail);
                        }
                    } else {
                        return new SemanticValidationResult.Fail("INVALID_TOOL:" + toolId2);
                    }
                }
                break;
            case 4:
                String goal = envelope.getGoal();
                if (goal == null || StringsKt.isBlank(goal)) {
                    return new SemanticValidationResult.Fail("missing_goal");
                }
                if (envelope.getPlanSteps().isEmpty()) {
                    return new SemanticValidationResult.Fail("empty_plan");
                }
                for (StructuredPlanStepDraft step : envelope.getPlanSteps()) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        StructuredAiSemanticValidator structuredAiSemanticValidator = this;
                        objM392constructorimpl = Result.m392constructorimpl(PlanStepKind.valueOf(step.getKind()));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m398isFailureimpl(objM392constructorimpl)) {
                        objM392constructorimpl = null;
                    }
                    PlanStepKind kind = (PlanStepKind) objM392constructorimpl;
                    if (kind == null) {
                        return new SemanticValidationResult.Fail("invalid_step_kind:" + step.getKind());
                    }
                    if (kind == PlanStepKind.TOOL) {
                        String toolId3 = step.getToolId();
                        String tid = toolId3 != null ? StringsKt.trim((CharSequence) toolId3).toString() : null;
                        if (tid == null) {
                            tid = "";
                        }
                        if (StringsKt.isBlank(tid)) {
                            return new SemanticValidationResult.Fail("plan_tool_missing");
                        }
                        String argFail2 = forbiddenArgs(step.getParameters());
                        if (argFail2 != null) {
                            return new SemanticValidationResult.Fail(argFail2);
                        }
                        if (toolRegistry != null && toolRegistry.findDefinition(tid) == null) {
                            return new SemanticValidationResult.Fail("INVALID_TOOL:" + tid);
                        }
                    }
                    break;
                }
                ActionPlan plan = StructuredAiAdapters.toActionPlanDraft$default(StructuredAiAdapters.INSTANCE, envelope, null, null, 6, null);
                if (plan == null) {
                    return new SemanticValidationResult.Fail("plan_adapt_failed");
                }
                ActionPlanValidation v = ActionPlanValidator.INSTANCE.validate(plan);
                if (!v.getOk()) {
                    return new SemanticValidationResult.Fail("plan_invalid:" + CollectionsKt.firstOrNull((List) v.getErrors()));
                }
                break;
            case 5:
                String toolId4 = envelope.getToolId();
                if (!(toolId4 == null || StringsKt.isBlank(toolId4)) || !envelope.getPlanSteps().isEmpty()) {
                    return new SemanticValidationResult.Fail("no_action_executable_payload");
                }
                break;
            case 6:
                String confirmationSummary = envelope.getConfirmationSummary();
                if (confirmationSummary == null || StringsKt.isBlank(confirmationSummary)) {
                    String text2 = envelope.getText();
                    if (text2 == null || StringsKt.isBlank(text2)) {
                        return new SemanticValidationResult.Fail("empty_confirmation_summary");
                    }
                }
                String argFail3 = forbiddenArgs(envelope.getArguments());
                if (argFail3 != null) {
                    return new SemanticValidationResult.Fail(argFail3);
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return new SemanticValidationResult.Ok(envelope);
    }

    private final String forbiddenArgs(Map<String, String> args) {
        for (String k : args.keySet()) {
            String lk = k.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lk, "toLowerCase(...)");
            Iterable $this$any$iv = StructuredAiLimits.INSTANCE.getFORBIDDEN_ARG_KEYS();
            boolean z = false;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                for (Object element$iv : $this$any$iv) {
                    String it = (String) element$iv;
                    if (Intrinsics.areEqual(lk, it) || StringsKt.contains$default((CharSequence) lk, (CharSequence) it, false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                return "forbidden_arg:" + k;
            }
        }
        return null;
    }

    private final String validateAgainstDefinition(ToolDefinition def, Map<String, String> args) {
        Iterable $this$map$iv = def.getParameters();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ToolParameter it = (ToolParameter) item$iv$iv;
            destination$iv$iv.add(it.getName());
        }
        Set known = CollectionsKt.toSet((List) destination$iv$iv);
        if (!known.isEmpty()) {
            for (String k : args.keySet()) {
                if (!known.contains(k)) {
                    return "unknown_arg:" + k;
                }
            }
            for (ToolParameter p : def.getParameters()) {
                if (p.getRequired()) {
                    String str = args.get(p.getName());
                    if (str == null || StringsKt.isBlank(str)) {
                        return "missing_required_arg:" + p.getName();
                    }
                }
                String v = args.get(p.getName());
                if (v != null) {
                    switch (WhenMappings.$EnumSwitchMapping$1[p.getType().ordinal()]) {
                        case 1:
                            if (!SetsKt.setOf((Object[]) new String[]{"true", "false", "0", StructuredAiLimits.SCHEMA_VERSION, "on", "off"}).contains(v)) {
                                return "invalid_arg_type:" + p.getName();
                            }
                            break;
                            break;
                        case 2:
                            if (StringsKt.toIntOrNull(v) == null) {
                                return "invalid_arg_type:" + p.getName();
                            }
                            break;
                            break;
                        case 3:
                            if (!p.getAllowedValues().isEmpty() && !p.getAllowedValues().contains(v)) {
                                return "invalid_enum:" + p.getName();
                            }
                            break;
                            break;
                        case 4:
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }
            }
            return null;
        }
        return null;
    }

    private final boolean looksLikeExecutionClaim(String text) {
        String t = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "готово", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "выполнил", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "открыл", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "успешно", false, 2, (Object) null)) {
            return StringsKt.contains$default((CharSequence) t, (CharSequence) "tool", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "инструмент", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "выполнил действие", false, 2, (Object) null);
        }
        return false;
    }
}
