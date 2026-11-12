package com.jarvis.core.aioutput;

import com.jarvis.core.execution.CatalogToolRegistry;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StructuredAiOutputPipeline.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005JD\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\tJ`\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2<\b\u0002\u0010\u0012\u001a6\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0013J4\u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\tH\u0002J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\tH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiOutputPipeline;", "", "toolRegistry", "Lcom/jarvis/core/execution/CatalogToolRegistry;", "<init>", "(Lcom/jarvis/core/execution/CatalogToolRegistry;)V", "process", "Lcom/jarvis/core/aioutput/StructuredAiProcessResult;", "raw", "", "task", "Lcom/jarvis/core/aioutput/StructuredAiTask;", "requestId", "repairedRaw", "repairAttempted", "", "repairResult", "processWithRepair", "repairFn", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "failedReason", "original", "processOnce", "stringField", "obj", "Lcom/jarvis/core/aioutput/JsonVal$Obj;", "key", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StructuredAiOutputPipeline {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CatalogToolRegistry toolRegistry;

    /* JADX WARN: Multi-variable type inference failed */
    public StructuredAiOutputPipeline() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public StructuredAiOutputPipeline(CatalogToolRegistry toolRegistry) {
        this.toolRegistry = toolRegistry;
    }

    public /* synthetic */ StructuredAiOutputPipeline(CatalogToolRegistry catalogToolRegistry, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : catalogToolRegistry);
    }

    public final StructuredAiProcessResult process(String raw, StructuredAiTask task, String requestId, String repairedRaw, boolean repairAttempted, String repairResult) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(task, "task");
        StructuredAiProcessResult first = processOnce(raw, task, requestId, repairAttempted, repairResult);
        if (!(first instanceof StructuredAiProcessResult.Valid) && repairedRaw != null && !repairAttempted) {
            return processOnce(repairedRaw, task, requestId, true, repairResult == null ? "supplied" : repairResult);
        }
        return first;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StructuredAiProcessResult processWithRepair$default(StructuredAiOutputPipeline structuredAiOutputPipeline, String str, StructuredAiTask structuredAiTask, String str2, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        if ((i & 8) != 0) {
            function2 = null;
        }
        return structuredAiOutputPipeline.processWithRepair(str, structuredAiTask, str2, function2);
    }

    public final StructuredAiProcessResult processWithRepair(String raw, StructuredAiTask task, String requestId, Function2<? super String, ? super String, String> repairFn) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(task, "task");
        StructuredAiProcessResult first = processOnce(raw, task, requestId, false, null);
        if ((first instanceof StructuredAiProcessResult.Valid) || repairFn == null) {
            return first;
        }
        Intrinsics.checkNotNull(first, "null cannot be cast to non-null type com.jarvis.core.aioutput.StructuredAiProcessResult.Invalid");
        String validationReason = ((StructuredAiProcessResult.Invalid) first).getDiagnostics().getValidationReason();
        if (validationReason == null) {
            validationReason = ((StructuredAiProcessResult.Invalid) first).getDiagnostics().getParse().name();
        }
        String reason = validationReason;
        try {
            Result.Companion companion = Result.INSTANCE;
            StructuredAiOutputPipeline structuredAiOutputPipeline = this;
            objM392constructorimpl = Result.m392constructorimpl(repairFn.invoke(reason, raw));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        String repaired = (String) objM392constructorimpl;
        String str = repaired;
        if (str == null || StringsKt.isBlank(str)) {
            StructuredAiDiagnostics diagnostics = ((StructuredAiProcessResult.Invalid) first).getDiagnostics();
            return new StructuredAiProcessResult.Invalid(diagnostics.copy((415 & 1) != 0 ? diagnostics.structured : false, (415 & 2) != 0 ? diagnostics.schemaVersion : null, (415 & 4) != 0 ? diagnostics.responseType : null, (415 & 8) != 0 ? diagnostics.parse : null, (415 & 16) != 0 ? diagnostics.validation : null, (415 & 32) != 0 ? diagnostics.repairAttempted : true, (415 & 64) != 0 ? diagnostics.repairResult : "empty", (415 & 128) != 0 ? diagnostics.validationReason : null, (415 & 256) != 0 ? diagnostics.parseMs : 0L), ((StructuredAiProcessResult.Invalid) first).getRawPreview());
        }
        StructuredAiProcessResult second = processOnce(repaired, task, requestId, true, "attempted");
        if (second instanceof StructuredAiProcessResult.Valid) {
            StructuredAiDiagnostics diagnostics2 = ((StructuredAiProcessResult.Valid) second).getDiagnostics();
            return StructuredAiProcessResult.Valid.copy$default((StructuredAiProcessResult.Valid) second, null, diagnostics2.copy((415 & 1) != 0 ? diagnostics2.structured : false, (415 & 2) != 0 ? diagnostics2.schemaVersion : null, (415 & 4) != 0 ? diagnostics2.responseType : null, (415 & 8) != 0 ? diagnostics2.parse : null, (415 & 16) != 0 ? diagnostics2.validation : null, (415 & 32) != 0 ? diagnostics2.repairAttempted : true, (415 & 64) != 0 ? diagnostics2.repairResult : "SUCCESS", (415 & 128) != 0 ? diagnostics2.validationReason : null, (415 & 256) != 0 ? diagnostics2.parseMs : 0L), 1, null);
        }
        if (!(second instanceof StructuredAiProcessResult.Invalid)) {
            throw new NoWhenBranchMatchedException();
        }
        StructuredAiDiagnostics diagnostics3 = ((StructuredAiProcessResult.Invalid) second).getDiagnostics();
        return StructuredAiProcessResult.Invalid.copy$default((StructuredAiProcessResult.Invalid) second, diagnostics3.copy((415 & 1) != 0 ? diagnostics3.structured : false, (415 & 2) != 0 ? diagnostics3.schemaVersion : null, (415 & 4) != 0 ? diagnostics3.responseType : null, (415 & 8) != 0 ? diagnostics3.parse : null, (415 & 16) != 0 ? diagnostics3.validation : null, (415 & 32) != 0 ? diagnostics3.repairAttempted : true, (415 & 64) != 0 ? diagnostics3.repairResult : "FAIL", (415 & 128) != 0 ? diagnostics3.validationReason : null, (415 & 256) != 0 ? diagnostics3.parseMs : 0L), null, 2, null);
    }

    private final StructuredAiProcessResult processOnce(String raw, StructuredAiTask task, String requestId, boolean repairAttempted, String repairResult) {
        String preview = StringsKt.take(StringsKt.trim((CharSequence) raw).toString(), 120);
        ParseSyntaxResult syntax = StructuredAiOutputParser.INSTANCE.extractJsonObject(raw);
        if (syntax instanceof ParseSyntaxResult.Fail) {
            return new StructuredAiProcessResult.Invalid(new StructuredAiDiagnostics(task != StructuredAiTask.CHAT, null, null, StructuredParseStatus.FAIL, StructuredValidationStatus.SKIPPED, repairAttempted, repairResult, ((ParseSyntaxResult.Fail) syntax).getReason(), ((ParseSyntaxResult.Fail) syntax).getParseMs()), preview);
        }
        Intrinsics.checkNotNull(syntax, "null cannot be cast to non-null type com.jarvis.core.aioutput.ParseSyntaxResult.Ok");
        SchemaValidationResult schema = StructuredAiSchemaValidator.INSTANCE.validate(((ParseSyntaxResult.Ok) syntax).getJson());
        if (schema instanceof SchemaValidationResult.Fail) {
            return new StructuredAiProcessResult.Invalid(new StructuredAiDiagnostics(true, stringField(((ParseSyntaxResult.Ok) syntax).getJson(), "schemaVersion"), stringField(((ParseSyntaxResult.Ok) syntax).getJson(), "type"), StructuredParseStatus.SUCCESS, StructuredValidationStatus.FAIL, repairAttempted, repairResult, ((SchemaValidationResult.Fail) schema).getReason(), ((ParseSyntaxResult.Ok) syntax).getParseMs()), preview);
        }
        if (!(schema instanceof SchemaValidationResult.Ok)) {
            throw new NoWhenBranchMatchedException();
        }
        StructuredAiEnvelope env = ((SchemaValidationResult.Ok) schema).getEnvelope();
        if (requestId != null && env.getRequestId() == null) {
            env = env.copy((65531 & 1) != 0 ? env.schemaVersion : null, (65531 & 2) != 0 ? env.responseType : null, (65531 & 4) != 0 ? env.requestId : requestId, (65531 & 8) != 0 ? env.confidence : null, (65531 & 16) != 0 ? env.text : null, (65531 & 32) != 0 ? env.question : null, (65531 & 64) != 0 ? env.clarificationReason : null, (65531 & 128) != 0 ? env.toolId : null, (65531 & 256) != 0 ? env.arguments : null, (65531 & 512) != 0 ? env.goal : null, (65531 & 1024) != 0 ? env.intent : null, (65531 & 2048) != 0 ? env.suggestedRoute : null, (65531 & 4096) != 0 ? env.ambiguity : null, (65531 & 8192) != 0 ? env.planSteps : null, (65531 & 16384) != 0 ? env.confirmationSummary : null, (65531 & 32768) != 0 ? env.noActionReason : null);
        }
        SemanticValidationResult sem = StructuredAiSemanticValidator.INSTANCE.validate(env, this.toolRegistry, task);
        if (sem instanceof SemanticValidationResult.Fail) {
            return new StructuredAiProcessResult.Invalid(new StructuredAiDiagnostics(true, env.getSchemaVersion(), env.getResponseType().name(), StructuredParseStatus.SUCCESS, StructuredValidationStatus.FAIL, repairAttempted, repairResult, ((SemanticValidationResult.Fail) sem).getReason(), ((ParseSyntaxResult.Ok) syntax).getParseMs()), preview);
        }
        if (sem instanceof SemanticValidationResult.Ok) {
            return new StructuredAiProcessResult.Valid(((SemanticValidationResult.Ok) sem).getEnvelope(), new StructuredAiDiagnostics(true, env.getSchemaVersion(), env.getResponseType().name(), StructuredParseStatus.SUCCESS, StructuredValidationStatus.SUCCESS, repairAttempted, repairResult, null, ((ParseSyntaxResult.Ok) syntax).getParseMs()));
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String stringField(JsonVal.Obj obj, String key) {
        String strAsString;
        JsonVal jsonValOpt = obj.opt(key);
        String str = null;
        if (jsonValOpt == null || (strAsString = jsonValOpt.asString()) == null) {
            return null;
        }
        String str2 = strAsString;
        if (!StringsKt.isBlank(str2)) {
            str = str2;
        }
        return str;
    }

    /* JADX INFO: compiled from: StructuredAiOutputPipeline.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005¨\u0006\n"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiOutputPipeline$Companion;", "", "<init>", "()V", "jsonInstruction", "", "task", "Lcom/jarvis/core/aioutput/StructuredAiTask;", "repairInstruction", "failedReason", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: StructuredAiOutputPipeline.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[StructuredAiTask.values().length];
                try {
                    iArr[StructuredAiTask.SEMANTIC_INTERPRETATION.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[StructuredAiTask.TOOL_REQUEST.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[StructuredAiTask.PLAN.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[StructuredAiTask.CHAT.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String jsonInstruction(StructuredAiTask task) {
            Intrinsics.checkNotNullParameter(task, "task");
            switch (WhenMappings.$EnumSwitchMapping$0[task.ordinal()]) {
                case 1:
                    return "Ответь ТОЛЬКО JSON: {\"schemaVersion\":\"1\",\"type\":\"ANSWER|CLARIFICATION|PLAN|TOOL_REQUEST|NO_ACTION\",\"suggestedRoute\":\"FAST_SKILL|CHAT|PLAN|CLARIFY|TEMPLATE\",\"goal\":\"...\",\"intent\":\"...\",\"ambiguity\":\"NONE|LOW|MEDIUM|HIGH\",\"text\":\"...\",\"question\":\"...\",\"reason\":\"AMBIGUOUS_TARGET|MISSING_SLOT|MULTIPLE_CANDIDATES|UNSURE_INTENT|OTHER\"} без markdown.";
                case 2:
                    return "Ответь ТОЛЬКО JSON: {\"schemaVersion\":\"1\",\"type\":\"TOOL_REQUEST\",\"toolId\":\"...\",\"arguments\":{},\"goal\":\"...\"} без markdown. Не исполняй действие.";
                case 3:
                    return "Ответь ТОЛЬКО JSON: {\"schemaVersion\":\"1\",\"type\":\"PLAN\",\"goal\":\"...\",\"steps\":[{\"stepId\":\"s1\",\"kind\":\"TOOL\",\"toolId\":\"...\",\"parameters\":{}}]} без markdown. Не исполняй.";
                case 4:
                    return "Если нужен structured ответ: {\"schemaVersion\":\"1\",\"type\":\"ANSWER\",\"text\":\"...\"} иначе обычный текст.";
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        public final String repairInstruction(String failedReason) {
            Intrinsics.checkNotNullParameter(failedReason, "failedReason");
            return "Исправь предыдущий ответ в валидный JSON schemaVersion=1. Ошибка: " + failedReason + ". Только JSON, без пояснений.";
        }
    }
}
