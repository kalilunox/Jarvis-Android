package com.jarvis.brain.semantic;

import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.brain.ChatLlmEngine;
import com.jarvis.brain.aiprovider.AICompletionRequest;
import com.jarvis.brain.aiprovider.AICompletionResult;
import com.jarvis.brain.aiprovider.AICompletionStatus;
import com.jarvis.brain.aiprovider.AIProvider;
import com.jarvis.brain.aiprovider.AIProviderSelection;
import com.jarvis.brain.aiprovider.AIRouter;
import com.jarvis.brain.aiprovider.AiTaskProfile;
import com.jarvis.brain.aiprovider.AiTaskType;
import com.jarvis.core.aioutput.StructuredAiAdapters;
import com.jarvis.core.aioutput.StructuredAiDiagnostics;
import com.jarvis.core.aioutput.StructuredAiOutputPipeline;
import com.jarvis.core.aioutput.StructuredAiProcessResult;
import com.jarvis.core.aioutput.StructuredAiTask;
import com.jarvis.core.llm.AssembledLlmContext;
import com.jarvis.core.llm.ContextBuildInput;
import com.jarvis.core.llm.LlmContextBudget;
import com.jarvis.core.llm.LlmContextKind;
import com.jarvis.core.llm.UnifiedContextBuilder;
import com.jarvis.core.runtime.RouteDecision;
import com.jarvis.core.runtime.semantic.AmbiguityLevel;
import com.jarvis.core.runtime.semantic.SemanticClarity;
import com.jarvis.core.runtime.semantic.SemanticInput;
import com.jarvis.core.runtime.semantic.SemanticInterpreter;
import com.jarvis.core.runtime.semantic.SemanticUnderstanding;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LlmStructuredSemanticInterpreter.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B]\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010!\u001a\u0004\u0018\u00010\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001f\u0010#\u001a\u0004\u0018\u00010\u001e2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b%J\u001f\u0010&\u001a\u0004\u0018\u00010\u001e2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b'R\u0016\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\"\u0010\u0017\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\"\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0010\u001a\u0004\u0018\u00010\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006("}, d2 = {"Lcom/jarvis/brain/semantic/LlmStructuredSemanticInterpreter;", "Lcom/jarvis/core/runtime/semantic/SemanticInterpreter;", "chatLlm", "Lkotlin/Function0;", "Lcom/jarvis/brain/ChatLlmEngine;", "routerProvider", "Lcom/jarvis/brain/aiprovider/AIRouter;", "isOnline", "", "preferCloud", "contextBuilder", "Lcom/jarvis/core/llm/UnifiedContextBuilder;", "structuredPipeline", "Lcom/jarvis/core/aioutput/StructuredAiOutputPipeline;", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lcom/jarvis/core/llm/UnifiedContextBuilder;Lcom/jarvis/core/aioutput/StructuredAiOutputPipeline;)V", "value", "", "lastProviderId", "getLastProviderId", "()Ljava/lang/String;", "lastModelId", "getLastModelId", "lastSelectionReason", "getLastSelectionReason", "Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "lastStructuredDiagnostics", "getLastStructuredDiagnostics", "()Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "interpret", "Lcom/jarvis/core/runtime/semantic/SemanticUnderstanding;", "input", "Lcom/jarvis/core/runtime/semantic/SemanticInput;", "generateStructured", "prompt", "parse", "raw", "parse$brain_debug", "parseLegacyLine", "parseLegacyLine$brain_debug", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LlmStructuredSemanticInterpreter implements SemanticInterpreter {
    private final Function0<ChatLlmEngine> chatLlm;
    private final UnifiedContextBuilder contextBuilder;
    private final Function0<Boolean> isOnline;
    private volatile String lastModelId;
    private volatile String lastProviderId;
    private volatile String lastSelectionReason;
    private volatile StructuredAiDiagnostics lastStructuredDiagnostics;
    private final Function0<Boolean> preferCloud;
    private final Function0<AIRouter> routerProvider;
    private final StructuredAiOutputPipeline structuredPipeline;

    /* JADX INFO: compiled from: LlmStructuredSemanticInterpreter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RouteDecision.values().length];
            try {
                iArr[RouteDecision.CHAT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RouteDecision.PLAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RouteDecision.FAST_SKILL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LlmStructuredSemanticInterpreter(Function0<? extends ChatLlmEngine> chatLlm, Function0<AIRouter> routerProvider, Function0<Boolean> isOnline, Function0<Boolean> preferCloud, UnifiedContextBuilder contextBuilder, StructuredAiOutputPipeline structuredPipeline) {
        Intrinsics.checkNotNullParameter(chatLlm, "chatLlm");
        Intrinsics.checkNotNullParameter(routerProvider, "routerProvider");
        Intrinsics.checkNotNullParameter(isOnline, "isOnline");
        Intrinsics.checkNotNullParameter(preferCloud, "preferCloud");
        Intrinsics.checkNotNullParameter(contextBuilder, "contextBuilder");
        Intrinsics.checkNotNullParameter(structuredPipeline, "structuredPipeline");
        this.chatLlm = chatLlm;
        this.routerProvider = routerProvider;
        this.isOnline = isOnline;
        this.preferCloud = preferCloud;
        this.contextBuilder = contextBuilder;
        this.structuredPipeline = structuredPipeline;
    }

    public /* synthetic */ LlmStructuredSemanticInterpreter(Function0 function0, Function0 function1, Function0 function2, Function0 function3, UnifiedContextBuilder unifiedContextBuilder, StructuredAiOutputPipeline structuredAiOutputPipeline, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i & 2) != 0 ? new Function0() { // from class: com.jarvis.brain.semantic.LlmStructuredSemanticInterpreter.1
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                return null;
            }
        } : function1, (i & 4) != 0 ? new Function0() { // from class: com.jarvis.brain.semantic.LlmStructuredSemanticInterpreter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(LlmStructuredSemanticInterpreter._init_$lambda$0());
            }
        } : function2, (i & 8) != 0 ? new Function0() { // from class: com.jarvis.brain.semantic.LlmStructuredSemanticInterpreter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(LlmStructuredSemanticInterpreter._init_$lambda$1());
            }
        } : function3, (i & 16) != 0 ? new UnifiedContextBuilder() : unifiedContextBuilder, (i & 32) != 0 ? new StructuredAiOutputPipeline(null, 1, null) : structuredAiOutputPipeline);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$1() {
        return false;
    }

    public final String getLastProviderId() {
        return this.lastProviderId;
    }

    public final String getLastModelId() {
        return this.lastModelId;
    }

    public final String getLastSelectionReason() {
        return this.lastSelectionReason;
    }

    public final StructuredAiDiagnostics getLastStructuredDiagnostics() {
        return this.lastStructuredDiagnostics;
    }

    @Override // com.jarvis.core.runtime.semantic.SemanticInterpreter
    public SemanticUnderstanding interpret(SemanticInput input) {
        Intrinsics.checkNotNullParameter(input, "input");
        StringBuilder $this$interpret_u24lambda_u240 = new StringBuilder();
        $this$interpret_u24lambda_u240.append(StructuredAiOutputPipeline.INSTANCE.jsonInstruction(StructuredAiTask.SEMANTIC_INTERPRETATION)).append('\n');
        $this$interpret_u24lambda_u240.append("Не выполняй действие. Только классификация.").append('\n');
        $this$interpret_u24lambda_u240.append("Запрос: ").append(StringsKt.take(input.getNormalizedText(), 200));
        String it = input.getPreviousUserText();
        if (it != null) {
            if (StringsKt.isBlank(it)) {
                it = null;
            }
            if (it != null) {
                $this$interpret_u24lambda_u240.append(" | prev_user=").append(StringsKt.take(it, 80));
            }
        }
        String it2 = input.getLastToolId();
        if (it2 != null) {
            $this$interpret_u24lambda_u240.append(" | last_tool=").append(it2);
        }
        String prompt = $this$interpret_u24lambda_u240.toString();
        String out = generateStructured(prompt, input);
        if (out == null || StringsKt.isBlank(out)) {
            return null;
        }
        return parse$brain_debug(out, input);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x005f  */
    private final String generateStructured(String prompt, SemanticInput input) {
        String it;
        Object objM392constructorimpl;
        AIRouter router = this.routerProvider.invoke();
        String requestId = "sem-" + System.nanoTime();
        UnifiedContextBuilder unifiedContextBuilder = this.contextBuilder;
        LlmContextKind llmContextKind = LlmContextKind.SEMANTIC_INTERPRETATION;
        LlmContextBudget llmContextBudget = LlmContextBudget.FAST;
        String it2 = input.getPreviousUserText();
        if (it2 == null) {
            it = null;
        } else {
            if (StringsKt.isBlank(it2)) {
                it2 = null;
            }
            if (it2 != null) {
                it = "user: " + StringsKt.take(it2, 80);
            } else {
                it = null;
            }
        }
        AssembledLlmContext assembled = unifiedContextBuilder.build(new ContextBuildInput(prompt, llmContextKind, llmContextBudget, "DEVELOPER", CollectionsKt.listOfNotNull(it), null, null, null, null, null, null, null, null, null, false, null, 49120, null));
        if (router != null) {
            AiTaskProfile profile = AiTaskProfile.INSTANCE.forSemanticInterpretation(this.isOnline.invoke().booleanValue(), this.preferCloud.invoke().booleanValue(), requestId);
            Pair pairComplete$default = AIRouter.complete$default(router, profile, new AICompletionRequest(requestId, prompt, assembled.getHistoryLines(), "", "DEVELOPER", true, false, null, AiTaskType.SEMANTIC_INTERPRETATION, assembled, 192, null), null, 4, null);
            AIProviderSelection sel = (AIProviderSelection) pairComplete$default.component1();
            AICompletionResult result = (AICompletionResult) pairComplete$default.component2();
            AIProvider provider = sel.getProvider();
            this.lastProviderId = provider != null ? provider.getId() : null;
            String modelId = result.getModelId();
            if (modelId == null) {
                modelId = sel.getModelId();
            }
            this.lastModelId = modelId;
            this.lastSelectionReason = sel.getSelectionReason();
            if (result.getStatus() == AICompletionStatus.SUCCESS) {
                String text = result.getText();
                if (text != null) {
                    return StringsKt.trim((CharSequence) text).toString();
                }
                return null;
            }
        }
        ChatLlmEngine engine = this.chatLlm.invoke();
        if (engine == null || !engine.isReady()) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            LlmStructuredSemanticInterpreter llmStructuredSemanticInterpreter = this;
            objM392constructorimpl = Result.m392constructorimpl(ChatLlmEngine.generate$default(engine, prompt, CollectionsKt.emptyList(), "", "DEVELOPER", null, 16, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        String str = (String) objM392constructorimpl;
        if (str != null) {
            return StringsKt.trim((CharSequence) str).toString();
        }
        return null;
    }

    public final SemanticUnderstanding parse$brain_debug(String raw, SemanticInput input) {
        StructuredAiDiagnostics diagnostics;
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(input, "input");
        StructuredAiProcessResult processed = this.structuredPipeline.processWithRepair(raw, StructuredAiTask.SEMANTIC_INTERPRETATION, null, null);
        if (processed instanceof StructuredAiProcessResult.Valid) {
            diagnostics = ((StructuredAiProcessResult.Valid) processed).getDiagnostics();
        } else {
            if (!(processed instanceof StructuredAiProcessResult.Invalid)) {
                throw new NoWhenBranchMatchedException();
            }
            diagnostics = ((StructuredAiProcessResult.Invalid) processed).getDiagnostics();
        }
        this.lastStructuredDiagnostics = diagnostics;
        if (processed instanceof StructuredAiProcessResult.Valid) {
            SemanticUnderstanding u = StructuredAiAdapters.INSTANCE.toSemanticUnderstanding(((StructuredAiProcessResult.Valid) processed).getEnvelope(), input.getRawText(), input.getNormalizedText());
            List<String> notes = u.getNotes();
            String[] strArr = new String[3];
            String str = this.lastProviderId;
            if (str == null) {
                str = "-";
            }
            strArr[0] = "ai_provider=" + str;
            String str2 = this.lastModelId;
            if (str2 == null) {
                str2 = "-";
            }
            strArr[1] = "ai_model=" + str2;
            String str3 = this.lastSelectionReason;
            strArr[2] = "ai_reason=" + (str3 != null ? str3 : "-");
            return u.copy((1048572 & 1) != 0 ? u.rawInput : null, (1048572 & 2) != 0 ? u.normalizedInput : null, (1048572 & 4) != 0 ? u.goal : null, (1048572 & 8) != 0 ? u.intent : null, (1048572 & 16) != 0 ? u.entities : null, (1048572 & 32) != 0 ? u.arguments : null, (1048572 & 64) != 0 ? u.constraints : null, (1048572 & 128) != 0 ? u.contextRefs : null, (1048572 & 256) != 0 ? u.conversationRefs : null, (1048572 & 512) != 0 ? u.confidence : 0.0f, (1048572 & 1024) != 0 ? u.ambiguity : null, (1048572 & 2048) != 0 ? u.clarity : null, (1048572 & 4096) != 0 ? u.requiredCapabilities : null, (1048572 & 8192) != 0 ? u.suggestedRoute : null, (1048572 & 16384) != 0 ? u.routeReason : null, (1048572 & 32768) != 0 ? u.clarifyQuestion : null, (1048572 & 65536) != 0 ? u.candidateInterpretations : null, (1048572 & 131072) != 0 ? u.rewrittenCommand : null, (1048572 & 262144) != 0 ? u.llmInterpretationUsed : false, (1048572 & 524288) != 0 ? u.notes : CollectionsKt.plus((Collection) notes, (Iterable) CollectionsKt.listOf((Object[]) strArr)));
        }
        if (!(processed instanceof StructuredAiProcessResult.Invalid)) {
            throw new NoWhenBranchMatchedException();
        }
        SemanticUnderstanding legacyLine$brain_debug = parseLegacyLine$brain_debug(raw, input);
        if (legacyLine$brain_debug == null) {
            return null;
        }
        StructuredAiDiagnostics diagnostics2 = ((StructuredAiProcessResult.Invalid) processed).getDiagnostics();
        String validationReason = ((StructuredAiProcessResult.Invalid) processed).getDiagnostics().getValidationReason();
        if (validationReason == null) {
            validationReason = "";
        }
        this.lastStructuredDiagnostics = diagnostics2.copy((415 & 1) != 0 ? diagnostics2.structured : false, (415 & 2) != 0 ? diagnostics2.schemaVersion : null, (415 & 4) != 0 ? diagnostics2.responseType : null, (415 & 8) != 0 ? diagnostics2.parse : null, (415 & 16) != 0 ? diagnostics2.validation : null, (415 & 32) != 0 ? diagnostics2.repairAttempted : false, (415 & 64) != 0 ? diagnostics2.repairResult : null, (415 & 128) != 0 ? diagnostics2.validationReason : validationReason + "+legacy_fallback", (415 & 256) != 0 ? diagnostics2.parseMs : 0L);
        return legacyLine$brain_debug;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:59:0x0163  */
    /* JADX WARN: Code duplicated, block: B:83:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:91:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:99:0x01ed  */
    public final SemanticUnderstanding parseLegacyLine$brain_debug(String raw, SemanticInput input) {
        Pair pair;
        Object element$iv;
        String upperCase;
        RouteDecision route;
        String upperCase2;
        AmbiguityLevel ambiguity;
        List listListOf;
        Pair pair2;
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(input, "input");
        Iterator<String> it = StringsKt.lineSequence(raw).iterator();
        do {
            pair = null;
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
        } while (!StringsKt.contains$default((CharSequence) element$iv, (CharSequence) "route=", false, 2, (Object) null));
        String str = (String) element$iv;
        if (str == null && (str = (String) CollectionsKt.firstOrNull((List) StringsKt.lines(raw))) == null) {
            str = "";
        }
        String line = str;
        if (StringsKt.isBlank(line)) {
            return null;
        }
        Iterable $this$mapNotNull$iv = StringsKt.split$default((CharSequence) line, new char[]{';'}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            String part = (String) element$iv$iv$iv;
            int i = StringsKt.indexOf$default((CharSequence) part, '=', 0, false, 6, (Object) null);
            if (i <= 0) {
                pair2 = pair;
            } else {
                String lowerCase = StringsKt.trim((CharSequence) StringsKt.take(part, i)).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                pair2 = TuplesKt.to(lowerCase, StringsKt.trim((CharSequence) StringsKt.drop(part, i + 1)).toString());
            }
            if (pair2 != null) {
                destination$iv$iv.add(pair2);
            }
            pair = null;
        }
        Map map = MapsKt.toMap((List) destination$iv$iv);
        if (!map.containsKey("route")) {
            return null;
        }
        String str2 = (String) map.get("route");
        if (str2 != null) {
            upperCase = str2.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        } else {
            upperCase = null;
        }
        if (upperCase != null) {
            switch (upperCase) {
                case "TEMPLATE":
                    route = RouteDecision.TEMPLATE;
                    break;
                case "CHAT":
                    route = RouteDecision.CHAT;
                    break;
                case "PLAN":
                    route = RouteDecision.PLAN;
                    break;
                case "FAST_SKILL":
                    route = RouteDecision.FAST_SKILL;
                    break;
                case "CLARIFY":
                    route = RouteDecision.CLARIFY;
                    break;
                default:
                    route = RouteDecision.CHAT;
                    break;
            }
        } else {
            route = RouteDecision.CHAT;
        }
        String str3 = (String) map.get("ambiguity");
        if (str3 != null) {
            upperCase2 = str3.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
        } else {
            upperCase2 = null;
        }
        if (upperCase2 != null) {
            switch (upperCase2) {
                case "MEDIUM":
                    ambiguity = AmbiguityLevel.MEDIUM;
                    break;
                case "LOW":
                    ambiguity = AmbiguityLevel.LOW;
                    break;
                case "HIGH":
                    ambiguity = AmbiguityLevel.HIGH;
                    break;
                case "NONE":
                    ambiguity = AmbiguityLevel.NONE;
                    break;
                default:
                    ambiguity = AmbiguityLevel.LOW;
                    break;
            }
        } else {
            ambiguity = AmbiguityLevel.LOW;
        }
        String goal = (String) map.get("goal");
        if (goal == null) {
            goal = "llm_interpreted";
        } else {
            if (StringsKt.isBlank(goal)) {
                goal = null;
            }
            if (goal == null) {
                goal = "llm_interpreted";
            }
        }
        String it2 = (String) map.get("intent");
        if (it2 == null) {
            it2 = route.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(it2, "toLowerCase(...)");
        } else {
            if (StringsKt.isBlank(it2)) {
                it2 = null;
            }
            if (it2 == null) {
                it2 = route.name().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(it2, "toLowerCase(...)");
            }
        }
        String intent = it2;
        String it3 = (String) map.get("clarify");
        String clarify = (it3 == null || StringsKt.isBlank(it3)) ? null : it3;
        String rawText = input.getRawText();
        String normalizedText = input.getNormalizedText();
        String strTake = StringsKt.take(goal, 80);
        String strTake2 = StringsKt.take(intent, 64);
        SemanticClarity semanticClarity = ambiguity.compareTo(AmbiguityLevel.MEDIUM) >= 0 ? SemanticClarity.PARTIAL : SemanticClarity.CLEAR;
        String[] strArr = new String[5];
        strArr[0] = "llm_raw=" + StringsKt.take(line, 120);
        String str4 = this.lastProviderId;
        if (str4 == null) {
            str4 = "-";
        }
        strArr[1] = "ai_provider=" + str4;
        String str5 = this.lastModelId;
        if (str5 == null) {
            str5 = "-";
        }
        strArr[2] = "ai_model=" + str5;
        String str6 = this.lastSelectionReason;
        strArr[3] = "ai_reason=" + (str6 != null ? str6 : "-");
        strArr[4] = "structured_legacy=true";
        List listListOf2 = CollectionsKt.listOf((Object[]) strArr);
        switch (WhenMappings.$EnumSwitchMapping$0[route.ordinal()]) {
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
        return new SemanticUnderstanding(rawText, normalizedText, strTake, strTake2, null, null, null, null, null, 0.65f, ambiguity, semanticClarity, listListOf, route, "llm_semantic_legacy", clarify, null, null, true, listListOf2, 197104, null);
    }
}
