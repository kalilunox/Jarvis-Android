package com.jarvis.core.aioutput;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StructuredAiSchemaValidator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tH\u0002J\"\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\"\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u001a\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiSchemaValidator;", "", "<init>", "()V", "validate", "Lcom/jarvis/core/aioutput/SchemaValidationResult;", "json", "Lcom/jarvis/core/aioutput/JsonVal$Obj;", "stringOf", "", "obj", "key", "requiredString", "max", "", "boundOpt", "parseStringMap", "", "raw", "Lcom/jarvis/core/aioutput/JsonVal;", "parseStringList", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StructuredAiSchemaValidator {
    public static final StructuredAiSchemaValidator INSTANCE = new StructuredAiSchemaValidator();

    /* JADX INFO: compiled from: StructuredAiSchemaValidator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
        }
    }

    private StructuredAiSchemaValidator() {
    }

    /* JADX WARN: Code duplicated, block: B:212:0x0678  */
    public final SchemaValidationResult validate(JsonVal.Obj json) {
        String upperCase;
        Object objM392constructorimpl;
        Float confidence;
        String suggestedRoute;
        String ambiguity;
        String str;
        Object objM392constructorimpl2;
        String string;
        String planGoal;
        JsonVal.Arr stepsArr;
        String string2;
        Intrinsics.checkNotNullParameter(json, "json");
        List<String> listUnknownTopLevelKeys = StructuredAiOutputParser.INSTANCE.unknownTopLevelKeys(json);
        if (!listUnknownTopLevelKeys.isEmpty()) {
            return new SchemaValidationResult.Fail("unknown_fields:" + CollectionsKt.joinToString$default(listUnknownTopLevelKeys, ",", null, null, 0, null, null, 62, null));
        }
        String strStringOf = stringOf(json, "schemaVersion");
        String string3 = strStringOf != null ? StringsKt.trim((CharSequence) strStringOf).toString() : null;
        if (string3 == null) {
            string3 = "";
        }
        String version = string3;
        if (StringsKt.isBlank(version)) {
            return new SchemaValidationResult.Fail("missing_schemaVersion");
        }
        if (!Intrinsics.areEqual(version, StructuredAiLimits.SCHEMA_VERSION)) {
            return new SchemaValidationResult.Fail("unsupported_schema:" + version);
        }
        String strStringOf2 = stringOf(json, "type");
        if (strStringOf2 == null || (string2 = StringsKt.trim((CharSequence) strStringOf2).toString()) == null) {
            upperCase = null;
        } else {
            upperCase = string2.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        }
        if (upperCase == null) {
            upperCase = "";
        }
        String typeRaw = upperCase;
        if (StringsKt.isBlank(typeRaw)) {
            return new SchemaValidationResult.Fail("missing_type");
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            StructuredAiSchemaValidator structuredAiSchemaValidator = this;
            objM392constructorimpl = Result.m392constructorimpl(StructuredAiResponseType.valueOf(typeRaw));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        StructuredAiResponseType type = (StructuredAiResponseType) objM392constructorimpl;
        if (type == null) {
            return new SchemaValidationResult.Fail("invalid_type:" + typeRaw);
        }
        JsonVal c = json.opt("confidence");
        if (c == null || Intrinsics.areEqual(c, JsonVal.Null.INSTANCE)) {
            confidence = null;
        } else {
            if (!(c instanceof JsonVal.Num)) {
                return new SchemaValidationResult.Fail("invalid_confidence");
            }
            if (((JsonVal.Num) c).getValue() < 0.0d || ((JsonVal.Num) c).getValue() > 1.0d) {
                return new SchemaValidationResult.Fail("invalid_confidence");
            }
            confidence = Float.valueOf((float) ((JsonVal.Num) c).getValue());
        }
        String it = stringOf(json, "requestId");
        String requestId = (it == null || StringsKt.isBlank(it)) ? null : it;
        String goal = boundOpt(json, "goal", 160);
        String intent = boundOpt(json, "intent", 64);
        String strBoundOpt = boundOpt(json, "suggestedRoute", 32);
        if (strBoundOpt != null) {
            suggestedRoute = strBoundOpt.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(suggestedRoute, "toUpperCase(...)");
        } else {
            suggestedRoute = null;
        }
        String strBoundOpt2 = boundOpt(json, "ambiguity", 16);
        if (strBoundOpt2 != null) {
            String upperCase2 = strBoundOpt2.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
            ambiguity = upperCase2;
        } else {
            ambiguity = null;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                String text = requiredString(json, VoiceService.EXTRA_TEXT, StructuredAiLimits.MAX_TEXT_CHARS);
                return text == null ? new SchemaValidationResult.Fail("missing_or_empty_text") : new SchemaValidationResult.Ok(new StructuredAiEnvelope(version, type, requestId, confidence, text, null, null, null, null, goal, intent, suggestedRoute, ambiguity, null, null, null, 57824, null));
            case 2:
                String question = requiredString(json, "question", StructuredAiLimits.MAX_QUESTION_CHARS);
                if (question == null) {
                    return new SchemaValidationResult.Fail("missing_or_empty_question");
                }
                String strStringOf3 = stringOf(json, "reason");
                if (strStringOf3 == null || (string = StringsKt.trim((CharSequence) strStringOf3).toString()) == null) {
                    str = "OTHER";
                } else {
                    String upperCase3 = string.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase3, "toUpperCase(...)");
                    if (upperCase3 != null) {
                        String str2 = upperCase3;
                        if (StringsKt.isBlank(str2)) {
                            str2 = null;
                        }
                        str = str2;
                        if (str == null) {
                            str = "OTHER";
                        }
                    } else {
                        str = "OTHER";
                    }
                }
                String reasonRaw = str;
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    StructuredAiSchemaValidator structuredAiSchemaValidator2 = this;
                    objM392constructorimpl2 = Result.m392constructorimpl(ClarificationReason.valueOf(reasonRaw));
                    break;
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl2)) {
                    objM392constructorimpl2 = null;
                }
                ClarificationReason reason = (ClarificationReason) objM392constructorimpl2;
                if (reason == null) {
                    return new SchemaValidationResult.Fail("invalid_reason:" + reasonRaw);
                }
                return new SchemaValidationResult.Ok(new StructuredAiEnvelope(version, type, requestId, confidence, question, question, reason, null, null, goal, intent, suggestedRoute == null ? "CLARIFY" : suggestedRoute, ambiguity, null, null, null, 57728, null));
            case 3:
                String toolId = requiredString(json, "toolId", 64);
                if (toolId == null) {
                    return new SchemaValidationResult.Fail("missing_or_empty_toolId");
                }
                Map<String, String> stringMap = parseStringMap(json.opt("arguments"));
                if (stringMap == null) {
                    return new SchemaValidationResult.Fail("invalid_arguments");
                }
                return stringMap.size() > 12 ? new SchemaValidationResult.Fail("too_many_arguments") : new SchemaValidationResult.Ok(new StructuredAiEnvelope(version, type, requestId, confidence, boundOpt(json, VoiceService.EXTRA_TEXT, StructuredAiLimits.MAX_TEXT_CHARS), null, null, toolId, stringMap, goal, intent, suggestedRoute, ambiguity, null, null, null, 57440, null));
            case 4:
                if (goal == null) {
                    String strRequiredString = requiredString(json, "goal", 160);
                    if (strRequiredString == null) {
                        return new SchemaValidationResult.Fail("missing_or_empty_goal");
                    }
                    planGoal = strRequiredString;
                } else {
                    planGoal = goal;
                }
                JsonVal jsonValOpt = json.opt("steps");
                if (jsonValOpt == null || (stepsArr = jsonValOpt.asArr()) == null) {
                    return new SchemaValidationResult.Fail("missing_steps");
                }
                if (stepsArr.getItems().isEmpty()) {
                    return new SchemaValidationResult.Fail("empty_steps");
                }
                if (stepsArr.getItems().size() > 16) {
                    return new SchemaValidationResult.Fail("too_many_steps");
                }
                List steps = new ArrayList();
                Iterable $this$forEachIndexed$iv = stepsArr.getItems();
                int $i$f$forEachIndexed = 0;
                int index$iv = 0;
                Iterator it2 = $this$forEachIndexed$iv.iterator();
                while (it2.hasNext()) {
                    Object item$iv = it2.next();
                    int index$iv2 = index$iv + 1;
                    if (index$iv < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    JsonVal item = (JsonVal) item$iv;
                    JsonVal.Obj s = item.asObj();
                    if (s == null) {
                        return new SchemaValidationResult.Fail("invalid_step_" + index$iv);
                    }
                    Iterable $this$filter$iv = s.keys();
                    JsonVal.Arr stepsArr2 = stepsArr;
                    Collection destination$iv$iv = new ArrayList();
                    Iterator it3 = $this$filter$iv.iterator();
                    while (true) {
                        Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                        int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                        Iterator it4 = it2;
                        Float confidence2 = confidence;
                        String requestId2 = requestId;
                        if (it3.hasNext()) {
                            StructuredAiResponseType type2 = type;
                            Object element$iv$iv = it3.next();
                            if (!SetsKt.setOf((Object[]) new String[]{"stepId", "kind", "toolId", "parameters", "dependsOn", "requiresConfirmation", "displaySummary", "waitMs"}).contains((String) element$iv$iv)) {
                                destination$iv$iv.add(element$iv$iv);
                            }
                            $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                            $i$f$forEachIndexed = $i$f$forEachIndexed2;
                            it2 = it4;
                            confidence = confidence2;
                            requestId = requestId2;
                            type = type2;
                        } else {
                            StructuredAiResponseType type3 = type;
                            List stepUnknown = (List) destination$iv$iv;
                            if (!stepUnknown.isEmpty()) {
                                return new SchemaValidationResult.Fail("unknown_step_fields:" + CollectionsKt.joinToString$default(stepUnknown, ",", null, null, 0, null, null, 62, null));
                            }
                            String stepId = INSTANCE.requiredString(s, "stepId", 64);
                            if (stepId == null) {
                                return new SchemaValidationResult.Fail("missing_stepId_" + index$iv);
                            }
                            String kind = INSTANCE.requiredString(s, "kind", 16);
                            if (kind == null) {
                                return new SchemaValidationResult.Fail("missing_kind_" + index$iv);
                            }
                            Map<String, String> stringMap2 = INSTANCE.parseStringMap(s.opt("parameters"));
                            if (stringMap2 == null) {
                                stringMap2 = MapsKt.emptyMap();
                            }
                            Map<String, String> map = stringMap2;
                            if (map.size() > 12) {
                                return new SchemaValidationResult.Fail("too_many_step_params_" + index$iv);
                            }
                            List<String> stringList = INSTANCE.parseStringList(s.opt("dependsOn"));
                            if (stringList == null) {
                                return new SchemaValidationResult.Fail("invalid_dependsOn_" + index$iv);
                            }
                            JsonVal b = s.opt("requiresConfirmation");
                            boolean requiresConfirmation = b instanceof JsonVal.Bool ? ((JsonVal.Bool) b).getValue() : false;
                            JsonVal w = s.opt("waitMs");
                            long waitMs = w instanceof JsonVal.Num ? RangesKt.coerceIn((long) ((JsonVal.Num) w).getValue(), 0L, 3600000L) : 0L;
                            List list = steps;
                            String upperCase4 = kind.toUpperCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(upperCase4, "toUpperCase(...)");
                            String strBoundOpt3 = INSTANCE.boundOpt(s, "toolId", 64);
                            String strBoundOpt4 = INSTANCE.boundOpt(s, "displaySummary", 160);
                            list.add(new StructuredPlanStepDraft(stepId, upperCase4, strBoundOpt3, map, stringList, requiresConfirmation, strBoundOpt4 == null ? "" : strBoundOpt4, waitMs));
                            index$iv = index$iv2;
                            $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                            $i$f$forEachIndexed = $i$f$forEachIndexed2;
                            it2 = it4;
                            stepsArr = stepsArr2;
                            confidence = confidence2;
                            requestId = requestId2;
                            type = type3;
                        }
                    }
                }
                return new SchemaValidationResult.Ok(new StructuredAiEnvelope(version, type, requestId, confidence, boundOpt(json, VoiceService.EXTRA_TEXT, StructuredAiLimits.MAX_TEXT_CHARS), null, null, null, null, planGoal, intent, suggestedRoute == null ? "PLAN" : suggestedRoute, ambiguity, steps, null, null, 49632, null));
            case 5:
                String strStringOf4 = stringOf(json, "toolId");
                if (strStringOf4 == null || StringsKt.isBlank(strStringOf4)) {
                    return json.has("steps") ? new SchemaValidationResult.Fail("no_action_with_steps") : new SchemaValidationResult.Ok(new StructuredAiEnvelope(version, type, requestId, confidence, null, null, null, null, null, goal, intent, suggestedRoute, ambiguity, null, null, boundOpt(json, "noActionReason", 160), 25072, null));
                }
                return new SchemaValidationResult.Fail("no_action_with_tool");
            case 6:
                String strRequiredString2 = requiredString(json, "summary", 160);
                if (strRequiredString2 == null && (strRequiredString2 = boundOpt(json, VoiceService.EXTRA_TEXT, 160)) == null) {
                    return new SchemaValidationResult.Fail("missing_summary");
                }
                String summary = strRequiredString2;
                String toolId2 = boundOpt(json, "toolId", 64);
                Map<String, String> stringMap3 = parseStringMap(json.opt("arguments"));
                if (stringMap3 == null) {
                    stringMap3 = MapsKt.emptyMap();
                }
                return new SchemaValidationResult.Ok(new StructuredAiEnvelope(version, type, requestId, confidence, summary, null, null, toolId2, stringMap3, goal, intent, suggestedRoute, ambiguity, null, summary, null, 41056, null));
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final String stringOf(JsonVal.Obj obj, String key) {
        JsonVal jsonValOpt = obj.opt(key);
        if (jsonValOpt != null) {
            return jsonValOpt.asString();
        }
        return null;
    }

    private final String requiredString(JsonVal.Obj obj, String key, int max) {
        String strStringOf = stringOf(obj, key);
        String v = strStringOf != null ? StringsKt.trim((CharSequence) strStringOf).toString() : null;
        if (v == null) {
            v = "";
        }
        if (StringsKt.isBlank(v)) {
            return null;
        }
        return StringsKt.take(v, max);
    }

    private final String boundOpt(JsonVal.Obj obj, String key, int max) {
        String strStringOf = stringOf(obj, key);
        String v = strStringOf != null ? StringsKt.trim((CharSequence) strStringOf).toString() : null;
        if (v == null) {
            v = "";
        }
        String it = v;
        String str = !StringsKt.isBlank(it) ? v : null;
        if (str != null) {
            return StringsKt.take(str, max);
        }
        return null;
    }

    private final Map<String, String> parseStringMap(JsonVal raw) {
        String s;
        if (raw == null || (raw instanceof JsonVal.Null)) {
            return MapsKt.emptyMap();
        }
        JsonVal.Obj obj = raw.asObj();
        if (obj == null) {
            return null;
        }
        LinkedHashMap out = new LinkedHashMap();
        for (Map.Entry<String, JsonVal> entry : obj.getMap().entrySet()) {
            String k = entry.getKey();
            JsonVal v = entry.getValue();
            if (k.length() > 48 || (s = v.asString()) == null || s.length() > 96) {
                return null;
            }
            out.put(k, s);
        }
        return out;
    }

    private final List<String> parseStringList(JsonVal raw) {
        String s;
        if (raw == null || (raw instanceof JsonVal.Null)) {
            return CollectionsKt.emptyList();
        }
        JsonVal.Arr arr = raw.asArr();
        if (arr == null) {
            return null;
        }
        List out = new ArrayList();
        for (JsonVal item : arr.getItems()) {
            JsonVal.Str str = item instanceof JsonVal.Str ? (JsonVal.Str) item : null;
            if (str == null || (s = str.getValue()) == null) {
                return null;
            }
            out.add(StringsKt.take(s, 64));
        }
        return out;
    }
}
