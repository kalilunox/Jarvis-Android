package com.jarvis.core.aioutput;

import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.autonomy.ProposedActionMapper;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.planner.PlanStepKind;
import com.jarvis.core.runtime.RouteDecision;
import com.jarvis.core.runtime.semantic.AmbiguityLevel;
import com.jarvis.core.runtime.semantic.SemanticClarity;
import com.jarvis.core.runtime.semantic.SemanticUnderstanding;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StructuredAiSemanticValidator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J$\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bJ\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiAdapters;", "", "<init>", "()V", "toProposedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "envelope", "Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "toActionPlanDraft", "Lcom/jarvis/core/planner/ActionPlan;", "planId", "", "goalId", "toSemanticUnderstanding", "Lcom/jarvis/core/runtime/semantic/SemanticUnderstanding;", "rawInput", "normalizedInput", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StructuredAiAdapters {
    public static final StructuredAiAdapters INSTANCE = new StructuredAiAdapters();

    /* JADX INFO: compiled from: StructuredAiSemanticValidator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[StructuredAiResponseType.values().length];
            try {
                iArr[StructuredAiResponseType.CLARIFICATION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StructuredAiResponseType.PLAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[StructuredAiResponseType.TOOL_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[StructuredAiResponseType.NO_ACTION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RouteDecision.values().length];
            try {
                iArr2[RouteDecision.CHAT.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[RouteDecision.PLAN.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[RouteDecision.FAST_SKILL.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private StructuredAiAdapters() {
    }

    public final ProposedAction toProposedAction(StructuredAiEnvelope envelope) {
        String toolId;
        Intrinsics.checkNotNullParameter(envelope, "envelope");
        if ((envelope.getResponseType() != StructuredAiResponseType.TOOL_REQUEST && envelope.getResponseType() != StructuredAiResponseType.CONFIRMATION_REQUIRED) || (toolId = envelope.getToolId()) == null) {
            return null;
        }
        ProposedActionMapper proposedActionMapper = ProposedActionMapper.INSTANCE;
        Map<String, String> arguments = envelope.getArguments();
        String text = envelope.getText();
        if (text == null && (text = envelope.getConfirmationSummary()) == null && (text = envelope.getGoal()) == null) {
            text = toolId;
        }
        ProposedAction mapped = proposedActionMapper.fromToolId(toolId, toolId, arguments, text);
        if (mapped != null) {
            return ProposedAction.copy$default(mapped, null, null, null, null, envelope.getArguments(), 15, null);
        }
        ActionKind actionKind = ActionKind.UNKNOWN;
        String goal = envelope.getGoal();
        if (goal == null) {
            goal = toolId;
        }
        return new ProposedAction(toolId, actionKind, StringsKt.take(goal, 160), toolId, envelope.getArguments());
    }

    public static /* synthetic */ ActionPlan toActionPlanDraft$default(StructuredAiAdapters structuredAiAdapters, StructuredAiEnvelope structuredAiEnvelope, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "llm-plan-" + System.nanoTime();
        }
        if ((i & 4) != 0) {
            str2 = "goal-" + System.nanoTime();
        }
        return structuredAiAdapters.toActionPlanDraft(structuredAiEnvelope, str, str2);
    }

    public final ActionPlan toActionPlanDraft(StructuredAiEnvelope envelope, String planId, String goalId) {
        Intrinsics.checkNotNullParameter(envelope, "envelope");
        Intrinsics.checkNotNullParameter(planId, "planId");
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        if (envelope.getResponseType() != StructuredAiResponseType.PLAN) {
            return null;
        }
        Iterable $this$map$iv = envelope.getPlanSteps();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            StructuredPlanStepDraft draft = (StructuredPlanStepDraft) item$iv$iv;
            PlanStepKind kind = PlanStepKind.valueOf(draft.getKind());
            destination$iv$iv.add(new PlanStep(draft.getStepId(), kind, draft.getToolId(), null, draft.getParameters(), null, draft.getDependsOn(), null, draft.getRequiresConfirmation(), null, draft.getWaitMs(), null, null, false, draft.getDisplaySummary(), null, 47784, null));
        }
        List steps = (List) destination$iv$iv;
        return new ActionPlan(planId, goalId, ActionPlanStatus.READY, steps, null, false, null, CollectionsKt.listOf("source=structured_ai_v" + envelope.getSchemaVersion()), 112, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:30:0x007a  */
    /* JADX WARN: Code duplicated, block: B:32:0x0089  */
    /* JADX WARN: Code duplicated, block: B:33:0x008e  */
    /* JADX WARN: Code duplicated, block: B:34:0x0093  */
    /* JADX WARN: Code duplicated, block: B:35:0x0098  */
    /* JADX WARN: Code duplicated, block: B:36:0x009d  */
    /* JADX WARN: Code duplicated, block: B:59:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:61:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00f5  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final SemanticUnderstanding toSemanticUnderstanding(StructuredAiEnvelope envelope, String rawInput, String normalizedInput) {
        String upperCase;
        RouteDecision route;
        AmbiguityLevel ambiguity;
        String toolId;
        String goal;
        String lowerCase;
        List listListOf;
        Intrinsics.checkNotNullParameter(envelope, "envelope");
        Intrinsics.checkNotNullParameter(rawInput, "rawInput");
        Intrinsics.checkNotNullParameter(normalizedInput, "normalizedInput");
        String suggestedRoute = envelope.getSuggestedRoute();
        String upperCase2 = null;
        if (suggestedRoute != null) {
            upperCase = suggestedRoute.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        } else {
            upperCase = null;
        }
        if (upperCase != null) {
            switch (upperCase.hashCode()) {
                case -538919814:
                    if (upperCase.equals("TEMPLATE")) {
                        route = RouteDecision.TEMPLATE;
                        break;
                    } else {
                        switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
                            case 1:
                                route = RouteDecision.CLARIFY;
                                break;
                            case 2:
                                route = RouteDecision.PLAN;
                                break;
                            case 3:
                                route = RouteDecision.FAST_SKILL;
                                break;
                            case 4:
                                route = RouteDecision.CHAT;
                                break;
                            default:
                                route = RouteDecision.CHAT;
                                break;
                        }
                    }
                    break;
                case 2067288:
                    if (upperCase.equals("CHAT")) {
                        route = RouteDecision.CHAT;
                        break;
                    } else {
                        switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
                            case 1:
                                route = RouteDecision.CLARIFY;
                                break;
                            case 2:
                                route = RouteDecision.PLAN;
                                break;
                            case 3:
                                route = RouteDecision.FAST_SKILL;
                                break;
                            case 4:
                                route = RouteDecision.CHAT;
                                break;
                            default:
                                route = RouteDecision.CHAT;
                                break;
                        }
                    }
                    break;
                case 2458409:
                    if (upperCase.equals("PLAN")) {
                        route = RouteDecision.PLAN;
                        break;
                    } else {
                        switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
                            case 1:
                                route = RouteDecision.CLARIFY;
                                break;
                            case 2:
                                route = RouteDecision.PLAN;
                                break;
                            case 3:
                                route = RouteDecision.FAST_SKILL;
                                break;
                            case 4:
                                route = RouteDecision.CHAT;
                                break;
                            default:
                                route = RouteDecision.CHAT;
                                break;
                        }
                    }
                    break;
                case 567496846:
                    if (upperCase.equals("FAST_SKILL")) {
                        route = RouteDecision.FAST_SKILL;
                        break;
                    } else {
                        switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
                            case 1:
                                route = RouteDecision.CLARIFY;
                                break;
                            case 2:
                                route = RouteDecision.PLAN;
                                break;
                            case 3:
                                route = RouteDecision.FAST_SKILL;
                                break;
                            case 4:
                                route = RouteDecision.CHAT;
                                break;
                            default:
                                route = RouteDecision.CHAT;
                                break;
                        }
                    }
                    break;
                case 1571564098:
                    if (upperCase.equals("CLARIFY")) {
                        route = RouteDecision.CLARIFY;
                        break;
                    } else {
                        switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
                            case 1:
                                route = RouteDecision.CLARIFY;
                                break;
                            case 2:
                                route = RouteDecision.PLAN;
                                break;
                            case 3:
                                route = RouteDecision.FAST_SKILL;
                                break;
                            case 4:
                                route = RouteDecision.CHAT;
                                break;
                            default:
                                route = RouteDecision.CHAT;
                                break;
                        }
                    }
                    break;
                default:
                    switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
                        case 1:
                            route = RouteDecision.CLARIFY;
                            break;
                        case 2:
                            route = RouteDecision.PLAN;
                            break;
                        case 3:
                            route = RouteDecision.FAST_SKILL;
                            break;
                        case 4:
                            route = RouteDecision.CHAT;
                            break;
                        default:
                            route = RouteDecision.CHAT;
                            break;
                    }
                    break;
            }
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[envelope.getResponseType().ordinal()]) {
                case 1:
                    route = RouteDecision.CLARIFY;
                    break;
                case 2:
                    route = RouteDecision.PLAN;
                    break;
                case 3:
                    route = RouteDecision.FAST_SKILL;
                    break;
                case 4:
                    route = RouteDecision.CHAT;
                    break;
                default:
                    route = RouteDecision.CHAT;
                    break;
            }
        }
        String ambiguity2 = envelope.getAmbiguity();
        if (ambiguity2 != null) {
            upperCase2 = ambiguity2.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
        }
        if (upperCase2 != null) {
            switch (upperCase2.hashCode()) {
                case -2024701067:
                    if (upperCase2.equals("MEDIUM")) {
                        ambiguity = AmbiguityLevel.MEDIUM;
                    } else if (envelope.getResponseType() != StructuredAiResponseType.CLARIFICATION) {
                        ambiguity = AmbiguityLevel.LOW;
                    } else {
                        ambiguity = AmbiguityLevel.HIGH;
                    }
                    break;
                case 75572:
                    if (upperCase2.equals("LOW")) {
                        ambiguity = AmbiguityLevel.LOW;
                    } else if (envelope.getResponseType() != StructuredAiResponseType.CLARIFICATION) {
                        ambiguity = AmbiguityLevel.LOW;
                    } else {
                        ambiguity = AmbiguityLevel.HIGH;
                    }
                    break;
                case 2217378:
                    if (upperCase2.equals("HIGH")) {
                        ambiguity = AmbiguityLevel.HIGH;
                    } else if (envelope.getResponseType() != StructuredAiResponseType.CLARIFICATION) {
                        ambiguity = AmbiguityLevel.LOW;
                    } else {
                        ambiguity = AmbiguityLevel.HIGH;
                    }
                    break;
                case 2402104:
                    if (upperCase2.equals("NONE")) {
                        ambiguity = AmbiguityLevel.NONE;
                    } else if (envelope.getResponseType() != StructuredAiResponseType.CLARIFICATION) {
                        ambiguity = AmbiguityLevel.LOW;
                    } else {
                        ambiguity = AmbiguityLevel.HIGH;
                    }
                    break;
                default:
                    if (envelope.getResponseType() != StructuredAiResponseType.CLARIFICATION) {
                        ambiguity = AmbiguityLevel.LOW;
                    } else {
                        ambiguity = AmbiguityLevel.HIGH;
                    }
                    break;
            }
        } else if (envelope.getResponseType() != StructuredAiResponseType.CLARIFICATION) {
            ambiguity = AmbiguityLevel.HIGH;
        } else {
            ambiguity = AmbiguityLevel.LOW;
        }
        String goal2 = envelope.getGoal();
        if ((goal2 == null || (toolId = StringsKt.take(goal2, 80)) == null) && (toolId = envelope.getToolId()) == null) {
            String lowerCase2 = envelope.getResponseType().name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            goal = lowerCase2;
        } else {
            goal = toolId;
        }
        String intent = envelope.getIntent();
        if (intent == null || (lowerCase = StringsKt.take(intent, 64)) == null) {
            lowerCase = route.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        String intent2 = lowerCase;
        Float confidence = envelope.getConfidence();
        float fFloatValue = confidence != null ? confidence.floatValue() : 0.65f;
        SemanticClarity semanticClarity = ambiguity.compareTo(AmbiguityLevel.MEDIUM) >= 0 ? SemanticClarity.PARTIAL : SemanticClarity.CLEAR;
        String str = "structured_ai_v" + envelope.getSchemaVersion();
        String question = envelope.getQuestion();
        List listListOf2 = CollectionsKt.listOf((Object[]) new String[]{"structured_type=" + envelope.getResponseType(), "schema=" + envelope.getSchemaVersion()});
        switch (WhenMappings.$EnumSwitchMapping$1[route.ordinal()]) {
            case 1:
                listListOf = CollectionsKt.listOf(JarvisActivity.CHAT);
                break;
            case 2:
                listListOf = CollectionsKt.listOf((Object[]) new String[]{"planner", "discovery"});
                break;
            case 3:
                listListOf = CollectionsKt.listOf("skill");
                break;
            default:
                listListOf = CollectionsKt.emptyList();
                break;
        }
        return new SemanticUnderstanding(rawInput, normalizedInput, goal, intent2, null, null, null, null, null, fFloatValue, ambiguity, semanticClarity, listListOf, route, str, question, null, null, true, listListOf2, 197104, null);
    }
}
