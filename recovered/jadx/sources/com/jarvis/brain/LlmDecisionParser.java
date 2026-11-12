package com.jarvis.brain;

import com.jarvis.core.aioutput.StructuredAiLimits;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: LlmStructuredDecision.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jarvis/brain/LlmDecisionParser;", "", "<init>", "()V", "KIND_LINE", "Lkotlin/text/Regex;", "KIND_TAG", "parse", "Lcom/jarvis/brain/LlmStructuredDecision;", "raw", "", "kindOf", "Lcom/jarvis/brain/LlmDecisionKind;", "name", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LlmDecisionParser {
    public static final LlmDecisionParser INSTANCE = new LlmDecisionParser();
    private static final Regex KIND_LINE = new Regex("^\\s*(ANSWER|QUESTION|CLARIFICATION|ACTION|PLAN|CONFIRMATION_REQUIRED|REFUSAL|ERROR|FOLLOW_UP)\\s*[:|\\-–]\\s*(.+)$", (Set<? extends RegexOption>) SetsKt.setOf((Object[]) new RegexOption[]{RegexOption.IGNORE_CASE, RegexOption.DOT_MATCHES_ALL}));
    private static final Regex KIND_TAG = new Regex("(?i)\\b(ANSWER|QUESTION|CLARIFICATION|ACTION|PLAN|CONFIRMATION_REQUIRED|REFUSAL|ERROR|FOLLOW_UP)\\b");

    private LlmDecisionParser() {
    }

    public final LlmStructuredDecision parse(String raw) {
        List<String> groupValues;
        List<String> groupValues2;
        String str;
        String strReplace$default;
        List<String> groupValues3;
        Intrinsics.checkNotNullParameter(raw, "raw");
        String t = StringsKt.trim((CharSequence) raw).toString();
        String str2 = null;
        if (StringsKt.isBlank(t)) {
            return null;
        }
        MatchResult lineMatch = Regex.find$default(KIND_LINE, t, 0, 2, null);
        if (lineMatch != null) {
            LlmDecisionKind kind = kindOf(lineMatch.getGroupValues().get(1));
            String utter = StringsKt.take(StringsKt.trim((CharSequence) lineMatch.getGroupValues().get(2)).toString(), StructuredAiLimits.MAX_QUESTION_CHARS);
            if (kind != null && !StringsKt.isBlank(utter)) {
                return new LlmStructuredDecision(kind, utter, 0.7f, null, null, t, 24, null);
            }
        }
        MatchResult matchResultFind$default = Regex.find$default(new Regex("\"kind\"\\s*:\\s*\"([A-Za-z_]+)\"", RegexOption.IGNORE_CASE), t, 0, 2, null);
        String kindJson = (matchResultFind$default == null || (groupValues3 = matchResultFind$default.getGroupValues()) == null) ? null : groupValues3.get(1);
        MatchResult matchResultFind$default2 = Regex.find$default(new Regex("\"utterance\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"", RegexOption.IGNORE_CASE), t, 0, 2, null);
        String utterJson = (matchResultFind$default2 == null || (groupValues2 = matchResultFind$default2.getGroupValues()) == null || (str = groupValues2.get(1)) == null || (strReplace$default = StringsKt.replace$default(str, "\\\"", "\"", false, 4, (Object) null)) == null) ? null : StringsKt.replace$default(strReplace$default, "\\n", StringUtils.SPACE, false, 4, (Object) null);
        if (kindJson != null) {
            String str3 = utterJson;
            if (!(str3 == null || StringsKt.isBlank(str3))) {
                LlmDecisionKind kind2 = kindOf(kindJson);
                if (kind2 == null) {
                    kind2 = LlmDecisionKind.ANSWER;
                }
                return new LlmStructuredDecision(kind2, StringsKt.take(utterJson, StructuredAiLimits.MAX_QUESTION_CHARS), 0.75f, null, null, t, 24, null);
            }
        }
        String cleaned = StringsKt.trim((CharSequence) SequencesKt.joinToString$default(SequencesKt.filter(SequencesKt.map(StringsKt.lineSequence(t), new Function1() { // from class: com.jarvis.brain.LlmDecisionParser$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LlmDecisionParser.parse$lambda$0((String) obj);
            }
        }), new Function1() { // from class: com.jarvis.brain.LlmDecisionParser$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LlmDecisionParser.parse$lambda$1((String) obj));
            }
        }), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        if (StringsKt.isBlank(cleaned)) {
            return null;
        }
        MatchResult matchResultFind$default3 = Regex.find$default(KIND_TAG, cleaned, 0, 2, null);
        if (matchResultFind$default3 != null && (groupValues = matchResultFind$default3.getGroupValues()) != null) {
            str2 = groupValues.get(1);
        }
        String tagged = str2;
        LlmDecisionKind kind3 = kindOf(tagged);
        if (kind3 == null) {
            kind3 = LlmDecisionKind.ANSWER;
        }
        String utter2 = StringsKt.trim((CharSequence) new Regex("^(ANSWER|QUESTION|CLARIFICATION|ACTION|PLAN|CONFIRMATION_REQUIRED|REFUSAL|ERROR|FOLLOW_UP)\\s*[:|\\-–]\\s*", RegexOption.IGNORE_CASE).replace(cleaned, "")).toString();
        if (StringsKt.isBlank(utter2)) {
            utter2 = cleaned;
        }
        return new LlmStructuredDecision(kind3, StringsKt.take(utter2, StructuredAiLimits.MAX_QUESTION_CHARS), 0.55f, null, null, t, 24, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parse$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.trim((CharSequence) it).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean parse$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (it.length() > 0) && !StringsKt.startsWith$default(it, "{", false, 2, (Object) null);
    }

    private final LlmDecisionKind kindOf(String name) {
        Object objM392constructorimpl;
        String str = name;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            LlmDecisionParser llmDecisionParser = this;
            String upperCase = name.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            objM392constructorimpl = Result.m392constructorimpl(LlmDecisionKind.valueOf(upperCase));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (LlmDecisionKind) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }
}
