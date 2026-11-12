package com.jarvis.core.runtime.semantic;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.runtime.RouteDecision;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: FastSemanticClassifier.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 A2\u00020\u0001:\u0001AB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J*\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\"\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\"\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\"\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\"\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\"\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\"\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010#\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0%2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0%2\u0006\u0010\t\u001a\u00020\nH\u0002Jð\u0001\u0010)\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\n2\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\n0%2\u0014\b\u0002\u00106\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n072\n\b\u0002\u00108\u001a\u0004\u0018\u00010\n2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\n0%2\u000e\b\u0002\u0010;\u001a\b\u0012\u0004\u0012\u00020\n0%2\u000e\b\u0002\u0010<\u001a\b\u0012\u0004\u0012\u00020=0%2\u000e\b\u0002\u0010>\u001a\b\u0012\u0004\u0012\u00020\n0%2\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020\n0%2\b\b\u0002\u0010@\u001a\u00020\u0016H\u0002¨\u0006B"}, d2 = {"Lcom/jarvis/core/runtime/semantic/FastSemanticClassifier;", "Lcom/jarvis/core/runtime/semantic/SemanticInterpreter;", "<init>", "()V", "interpret", "Lcom/jarvis/core/runtime/semantic/SemanticUnderstanding;", "input", "Lcom/jarvis/core/runtime/semantic/SemanticInput;", "obviousTemplate", "t", "", "raw", "norm", "pronounAmbiguity", "planGoal", "volumeIntent", "flashlightIntent", "launcherIntent", "brightnessIntent", "weatherIntent", "musicIntent", "looksConversational", "", "hasConversationalWrapper", "looksLikeDeviceCommand", "looksLikeConcreteTarget", "prev", "isShortTimeQuery", "isShortDateQuery", "isShortGreeting", "isShortThanks", "isShortBye", "isShortIdentity", "isShortJoke", "isIncompleteAction", "targetedIncompleteQuestion", "incompleteCandidates", "", "countActionVerbs", "", "extractConstraints", "understanding", "goal", "intent", "confidence", "", "ambiguity", "Lcom/jarvis/core/runtime/semantic/AmbiguityLevel;", "clarity", "Lcom/jarvis/core/runtime/semantic/SemanticClarity;", "route", "Lcom/jarvis/core/runtime/RouteDecision;", "reason", "caps", "args", "", "rewrite", "clarify", "candidates", "constraints", "entities", "Lcom/jarvis/core/runtime/semantic/SemanticEntity;", "refs", "notes", "llm", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FastSemanticClassifier implements SemanticInterpreter {
    private static final Companion Companion = new Companion(null);
    private static final Regex POLITE_WORDS = new Regex("(^|[\\s,])(джарвис|слушай|пожалуйста)(?=$|[\\s,])");
    private static final Regex SPACES = new Regex("[\\s,]{2,}");
    private static final Regex SCHEDULE_WORDS = new Regex("(^|\\s)(через|завтра|послезавтра|каждый|каждое|ежедневно)\\s");
    private static final Regex OWN_ROUTINE = new Regex("^(когда|если|как только)\\s");

    /* JADX INFO: compiled from: FastSemanticClassifier.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/runtime/semantic/FastSemanticClassifier$Companion;", "", "<init>", "()V", "POLITE_WORDS", "Lkotlin/text/Regex;", "getPOLITE_WORDS", "()Lkotlin/text/Regex;", "SPACES", "getSPACES", "SCHEDULE_WORDS", "getSCHEDULE_WORDS", "OWN_ROUTINE", "getOWN_ROUTINE", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Regex getPOLITE_WORDS() {
            return FastSemanticClassifier.POLITE_WORDS;
        }

        public final Regex getSPACES() {
            return FastSemanticClassifier.SPACES;
        }

        public final Regex getSCHEDULE_WORDS() {
            return FastSemanticClassifier.SCHEDULE_WORDS;
        }

        public final Regex getOWN_ROUTINE() {
            return FastSemanticClassifier.OWN_ROUTINE;
        }
    }

    @Override // com.jarvis.core.runtime.semantic.SemanticInterpreter
    public SemanticUnderstanding interpret(SemanticInput input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String raw = StringsKt.trim((CharSequence) input.getRawText()).toString();
        String string = StringsKt.trim((CharSequence) input.getNormalizedText()).toString();
        if (StringsKt.isBlank(string)) {
            string = raw;
        }
        String norm = string;
        String lowerCase = norm.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String spoken = StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '?', '!', '.', Typography.ellipsis);
        String string2 = StringsKt.trim((CharSequence) SPACES.replace(POLITE_WORDS.replace(spoken, StringUtils.SPACE), StringUtils.SPACE)).toString();
        if (StringsKt.isBlank(string2)) {
            string2 = spoken;
        }
        String t = string2;
        if (StringsKt.isBlank(t)) {
            return understanding$default(this, input, raw, norm, "empty", "clarify", 0.2f, AmbiguityLevel.HIGH, SemanticClarity.UNCLEAR, RouteDecision.CLARIFY, "empty_input", null, null, null, "Что сделать?", null, null, null, null, null, false, 1039360, null);
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "напомни", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "напоминан", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "сценарий", false, 2, (Object) null) || OWN_ROUTINE.containsMatchIn(t) || StringsKt.startsWith$default(t, "сделай сам", false, 2, (Object) null) || StringsKt.startsWith$default(t, "сам сделай", false, 2, (Object) null) || SCHEDULE_WORDS.containsMatchIn(t)) {
            return null;
        }
        SemanticUnderstanding it = pronounAmbiguity(t, input);
        if (it != null) {
            return it.copy((1048572 & 1) != 0 ? it.rawInput : raw, (1048572 & 2) != 0 ? it.normalizedInput : norm, (1048572 & 4) != 0 ? it.goal : null, (1048572 & 8) != 0 ? it.intent : null, (1048572 & 16) != 0 ? it.entities : null, (1048572 & 32) != 0 ? it.arguments : null, (1048572 & 64) != 0 ? it.constraints : null, (1048572 & 128) != 0 ? it.contextRefs : null, (1048572 & 256) != 0 ? it.conversationRefs : null, (1048572 & 512) != 0 ? it.confidence : 0.0f, (1048572 & 1024) != 0 ? it.ambiguity : null, (1048572 & 2048) != 0 ? it.clarity : null, (1048572 & 4096) != 0 ? it.requiredCapabilities : null, (1048572 & 8192) != 0 ? it.suggestedRoute : null, (1048572 & 16384) != 0 ? it.routeReason : null, (1048572 & 32768) != 0 ? it.clarifyQuestion : null, (1048572 & 65536) != 0 ? it.candidateInterpretations : null, (1048572 & 131072) != 0 ? it.rewrittenCommand : null, (1048572 & 262144) != 0 ? it.llmInterpretationUsed : false, (1048572 & 524288) != 0 ? it.notes : null);
        }
        SemanticUnderstanding it2 = obviousTemplate(t, raw, norm);
        if (it2 != null) {
            return it2;
        }
        if (looksConversational(t)) {
            return understanding$default(this, input, raw, norm, "conversation", JarvisActivity.CHAT, 0.8f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.CHAT, "conversational_nl", CollectionsKt.listOf(JarvisActivity.CHAT), null, null, null, null, null, null, null, null, false, 1046528, null);
        }
        SemanticUnderstanding it3 = planGoal(t, raw, norm, input);
        if (it3 != null) {
            return it3;
        }
        SemanticUnderstanding it4 = volumeIntent(t, raw, norm);
        if (it4 != null) {
            return it4;
        }
        SemanticUnderstanding it5 = flashlightIntent(t, raw, norm);
        if (it5 != null) {
            return it5;
        }
        SemanticUnderstanding it6 = launcherIntent(t, raw, norm);
        if (it6 != null) {
            return it6;
        }
        SemanticUnderstanding it7 = brightnessIntent(t, raw, norm);
        if (it7 != null) {
            return it7;
        }
        SemanticUnderstanding it8 = weatherIntent(t, raw, norm);
        if (it8 != null) {
            return it8;
        }
        SemanticUnderstanding it9 = musicIntent(t, raw, norm);
        if (it9 != null) {
            return it9;
        }
        if (isIncompleteAction(t)) {
            return understanding$default(this, input, raw, norm, "incomplete_action", "clarify", 0.55f, AmbiguityLevel.MEDIUM, SemanticClarity.PARTIAL, RouteDecision.CLARIFY, "incomplete_action", null, null, null, targetedIncompleteQuestion(t), incompleteCandidates(t), null, null, null, null, false, 1022976, null);
        }
        return null;
    }

    private final SemanticUnderstanding obviousTemplate(String t, String raw, String norm) {
        if (hasConversationalWrapper(t)) {
            return null;
        }
        if (isShortTimeQuery(t)) {
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "tell_time", "template_time", 0.95f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.TEMPLATE, "obvious_time", null, null, null, null, null, null, null, null, null, false, 1047552, null);
        }
        if (isShortDateQuery(t)) {
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "tell_date", "template_date", 0.95f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.TEMPLATE, "obvious_date", null, null, null, null, null, null, null, null, null, false, 1047552, null);
        }
        if (isShortGreeting(t)) {
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "greet", "template_greeting", 0.95f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.TEMPLATE, "obvious_greeting", null, null, null, null, null, null, null, null, null, false, 1047552, null);
        }
        if (isShortThanks(t) || isShortBye(t)) {
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, isShortThanks(t) ? "thanks" : "goodbye", isShortThanks(t) ? "template_thanks" : "template_goodbye", 0.95f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.TEMPLATE, isShortThanks(t) ? "obvious_thanks" : "obvious_goodbye", null, null, null, null, null, null, null, null, null, false, 1047552, null);
        }
        if (isShortIdentity(t)) {
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "identity", "template_identity", 0.9f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.TEMPLATE, "obvious_identity", null, null, null, null, null, null, null, null, null, false, 1047552, null);
        }
        if (isShortJoke(t)) {
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "joke", "template_joke", 0.9f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.TEMPLATE, "obvious_joke", null, null, null, null, null, null, null, null, null, false, 1047552, null);
        }
        return null;
    }

    private final SemanticUnderstanding pronounAmbiguity(String t, SemanticInput input) {
        String str;
        boolean barePronoun = SetsKt.setOf((Object[]) new String[]{"открой его", "открой это", "включи это", "включи его", "выключи это", "закрой это", "сделай нормально", "сделай так", "этот", "тот", "его", "её", "ее", "их", "туда", "сюда"}).contains(t) || new Regex("^(открой|включи|выключи|закрой|запусти)\\s+(его|её|ее|это|тот|тот же)$").matches(t);
        if (!barePronoun) {
            return null;
        }
        String lastToolId = input.getLastToolId();
        boolean hasResolvableCtx = !(lastToolId == null || StringsKt.isBlank(lastToolId)) || looksLikeConcreteTarget(input.getPreviousUserText());
        if (hasResolvableCtx) {
            return null;
        }
        String rawText = input.getRawText();
        String normalizedText = input.getNormalizedText();
        AmbiguityLevel ambiguityLevel = AmbiguityLevel.HIGH;
        SemanticClarity semanticClarity = SemanticClarity.UNCLEAR;
        RouteDecision routeDecision = RouteDecision.CLARIFY;
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "открой", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "запусти", false, 2, (Object) null)) {
            str = "Что именно открыть? Назови приложение или файл.";
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "включи", false, 2, (Object) null)) {
            str = "Что включить — фонарик, музыку или что-то другое?";
        } else {
            str = StringsKt.contains$default((CharSequence) t, (CharSequence) "нормально", false, 2, (Object) null) ? "Уточни: сделать громче, ярче или что-то ещё?" : "Уточни, пожалуйста: о чём именно речь?";
        }
        return understanding$default(this, input, rawText, normalizedText, "resolve_reference", "clarify", 0.7f, ambiguityLevel, semanticClarity, routeDecision, "unresolved_pronoun", null, null, null, str, CollectionsKt.listOf((Object[]) new String[]{"flashlight", "music", "launcher", "volume"}), null, null, CollectionsKt.listOf("pronoun"), null, false, 891904, null);
    }

    private final SemanticUnderstanding planGoal(String t, String raw, String norm, SemanticInput input) {
        boolean goalish;
        int stepsHint;
        boolean multiVerb = countActionVerbs(t) >= 2;
        boolean conjunction = StringsKt.contains$default((CharSequence) t, (CharSequence) " и ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " потом ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " затем ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) ",", false, 2, (Object) null);
        Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"найди", "найди мне", "подготов", "сравни", "помоги", "организ", "запланир", "собери", "купить", "билет", "дешёв", "дешев", "вариант", "поездке", "поездк"});
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    goalish = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (StringsKt.contains$default((CharSequence) t, (CharSequence) it2, false, 2, (Object) null)) {
                    goalish = true;
                    break;
                }
            }
        } else {
            goalish = false;
        }
        boolean browserish = StringsKt.contains$default((CharSequence) t, (CharSequence) "браузер", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "в интернете", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "в гугле", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "погугл", false, 2, (Object) null);
        if (!multiVerb && !conjunction) {
            stepsHint = 0;
        } else if (multiVerb && (conjunction || goalish)) {
            stepsHint = 2;
        } else if (goalish && browserish) {
            stepsHint = 2;
        } else {
            stepsHint = (goalish && conjunction) ? 2 : 0;
        }
        if (stepsHint < 2) {
            return null;
        }
        List $this$planGoal_u24lambda_u241 = CollectionsKt.createListBuilder();
        if (browserish || StringsKt.contains$default((CharSequence) t, (CharSequence) "найди", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "билет", false, 2, (Object) null)) {
            $this$planGoal_u24lambda_u241.add("browser");
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "открой", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "запусти", false, 2, (Object) null)) {
            $this$planGoal_u24lambda_u241.add("launcher");
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "отправ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "напиши", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "telegram", false, 2, (Object) null)) {
            $this$planGoal_u24lambda_u241.add("messaging");
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "сравни", false, 2, (Object) null)) {
            $this$planGoal_u24lambda_u241.add("reasoning");
        }
        if ($this$planGoal_u24lambda_u241.isEmpty()) {
            $this$planGoal_u24lambda_u241.add("planner");
            $this$planGoal_u24lambda_u241.add("discovery");
        }
        List caps = CollectionsKt.distinct(CollectionsKt.build($this$planGoal_u24lambda_u241));
        return understanding$default(this, input, raw, norm, "multi_step_goal", "plan", 0.75f, AmbiguityLevel.LOW, SemanticClarity.PARTIAL, RouteDecision.PLAN, "free_form_multi_step", caps, null, null, null, null, extractConstraints(t), null, null, CollectionsKt.listOf((Object[]) new String[]{"steps=" + stepsHint, "goalish=" + goalish}), false, 751616, null);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0162 A[LOOP:0: B:31:0x010f->B:48:0x0162, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:87:0x0160 A[SYNTHETIC] */
    private final SemanticUnderstanding volumeIntent(String t, String raw, String norm) {
        boolean up;
        List downPhrases;
        List upPhrases;
        boolean z;
        String action;
        String rewrite;
        boolean z2 = false;
        List upPhrases2 = CollectionsKt.listOf((Object[]) new String[]{"громче", "погромче", "прибавь звук", "прибавь громкость", "увеличь громкость", "сделай громче", "давай погромче", "звук побольше", "побольше звук", "volume up"});
        List downPhrases2 = CollectionsKt.listOf((Object[]) new String[]{"тише", "потише", "убавь звук", "убавь громкость", "уменьши громкость", "сделай тише", "сделай потише", "здесь потише", "что-то тихо", "слишком громко", "звук поменьше", "volume down", "сделай здесь потише"});
        List $this$any$iv = upPhrases2;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    up = false;
                    break;
                }
                Object element$iv = it.next();
                String phrase = (String) element$iv;
                if (Intrinsics.areEqual(t, phrase) || StringsKt.startsWith$default(t, new StringBuilder().append(phrase).append(StringUtils.SPACE).toString(), false, 2, (Object) null) || (StringsKt.contains$default((CharSequence) t, (CharSequence) phrase, false, 2, (Object) null) && t.length() < 48)) {
                    up = true;
                    break;
                }
            }
        } else {
            up = false;
        }
        List $this$any$iv2 = downPhrases2;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it2 = $this$any$iv2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                Object element$iv2 = it2.next();
                String phrase2 = (String) element$iv2;
                if (Intrinsics.areEqual(t, phrase2)) {
                    downPhrases = downPhrases2;
                    upPhrases = upPhrases2;
                } else {
                    downPhrases = downPhrases2;
                    if (StringsKt.startsWith$default(t, phrase2 + StringUtils.SPACE, false, 2, (Object) null)) {
                        upPhrases = upPhrases2;
                    } else {
                        upPhrases = upPhrases2;
                        z = StringsKt.contains$default((CharSequence) t, (CharSequence) phrase2, false, 2, (Object) null) && t.length() < 56;
                        if (z) {
                            z2 = true;
                            break;
                        }
                        downPhrases2 = downPhrases;
                        upPhrases2 = upPhrases;
                    }
                }
                if (z) {
                    z2 = true;
                    break;
                }
                downPhrases2 = downPhrases;
                upPhrases2 = upPhrases;
            }
        }
        boolean down = z2;
        boolean z3 = true;
        if (!SetsKt.setOf((Object[]) new String[]{"без звука", "выключи звук", "мьют", "mute"}).contains(t) && !StringsKt.contains$default((CharSequence) t, (CharSequence) "выключи звук", false, 2, (Object) null)) {
            z3 = false;
        }
        boolean mute = z3;
        if (!up && !down && !mute) {
            return null;
        }
        if (hasConversationalWrapper(t) && t.length() > 50) {
            return null;
        }
        if (mute) {
            action = "mute";
        } else {
            action = up ? "up" : "down";
        }
        if (Intrinsics.areEqual(action, "mute")) {
            rewrite = "выключи звук";
        } else {
            rewrite = Intrinsics.areEqual(action, "up") ? "громче" : "тише";
        }
        return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, Intrinsics.areEqual(action, "up") ? "increase_audio_volume" : "reduce_audio_volume", "volume", 0.88f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.FAST_SKILL, "semantic_volume_" + action, CollectionsKt.listOf("volume"), MapsKt.mapOf(TuplesKt.to("action", action)), rewrite, null, null, null, CollectionsKt.listOf(new SemanticEntity("action", action, "volume_action")), null, null, false, 974848, null);
    }

    private final SemanticUnderstanding flashlightIntent(String t, String raw, String norm) {
        boolean on;
        boolean off;
        Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"включи фонарик", "включить фонарик", "фонарик включи", "свет фонарик", "включи свет", "фонарик"});
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    on = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (Intrinsics.areEqual(t, it2) || StringsKt.startsWith$default(t, it2, false, 2, (Object) null)) {
                    on = true;
                    break;
                }
            }
        } else {
            on = false;
        }
        Iterable $this$any$iv2 = CollectionsKt.listOf((Object[]) new String[]{"выключи фонарик", "выключить фонарик", "фонарик выключи", "выключи свет"});
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    off = false;
                    break;
                }
                Object element$iv2 = it3.next();
                String it4 = (String) element$iv2;
                if (Intrinsics.areEqual(t, it4) || StringsKt.startsWith$default(t, it4, false, 2, (Object) null)) {
                    off = true;
                    break;
                }
            }
        } else {
            off = false;
        }
        if (on || off) {
            String action = off ? "off" : "on";
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, Intrinsics.areEqual(action, "on") ? "enable_flashlight" : "disable_flashlight", "flashlight", 0.92f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.FAST_SKILL, "semantic_flashlight_" + action, CollectionsKt.listOf("flashlight"), MapsKt.mapOf(TuplesKt.to("action", action)), Intrinsics.areEqual(action, "on") ? "включи фонарик" : "выключи фонарик", null, null, null, null, null, null, false, 1040384, null);
        }
        if (!StringsKt.contains$default((CharSequence) t, (CharSequence) "фонарик", false, 2, (Object) null) || t.length() >= 40) {
            return null;
        }
        return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "control_flashlight", "flashlight", 0.7f, AmbiguityLevel.MEDIUM, SemanticClarity.PARTIAL, RouteDecision.CLARIFY, "flashlight_ambiguous", CollectionsKt.listOf("flashlight"), null, null, "Включить фонарик или выключить?", CollectionsKt.listOf((Object[]) new String[]{"включи фонарик", "выключи фонарик"}), null, null, null, null, false, 1021952, null);
    }

    private final SemanticUnderstanding launcherIntent(String t, String raw, String norm) {
        boolean open = StringsKt.startsWith$default(t, "открой ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "запусти ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "открой", false, 2, (Object) null) || StringsKt.startsWith$default(t, "запусти", false, 2, (Object) null);
        if (!open) {
            return null;
        }
        if (countActionVerbs(t) >= 2 && (StringsKt.contains$default((CharSequence) t, (CharSequence) " и ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "найди", false, 2, (Object) null))) {
            return null;
        }
        String app = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(t, (CharSequence) "открой "), (CharSequence) "запусти ")).toString();
        if (StringsKt.isBlank(app) || SetsKt.setOf((Object[]) new String[]{"его", "это", "её", "ее"}).contains(app)) {
            return null;
        }
        return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "launch_app", "launcher", 0.85f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.FAST_SKILL, "semantic_launcher", CollectionsKt.listOf("launcher"), MapsKt.mapOf(TuplesKt.to("action", AbstractCircuitBreaker.PROPERTY_NAME), TuplesKt.to("app", app)), "открой " + app, null, null, null, CollectionsKt.listOf(new SemanticEntity("app", app, TypedValues.AttributesType.S_TARGET)), null, null, false, 974848, null);
    }

    private final SemanticUnderstanding brightnessIntent(String t, String raw, String norm) {
        boolean up = StringsKt.contains$default((CharSequence) t, (CharSequence) "ярче", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "прибавь яркость", false, 2, (Object) null);
        boolean down = StringsKt.contains$default((CharSequence) t, (CharSequence) "тусклее", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "убавь яркость", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "темнее экран", false, 2, (Object) null);
        if (!up && !down) {
            return null;
        }
        String action = up ? "up" : "down";
        return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "adjust_brightness", "brightness", 0.85f, AmbiguityLevel.NONE, SemanticClarity.CLEAR, RouteDecision.FAST_SKILL, "semantic_brightness_" + action, CollectionsKt.listOf("brightness"), MapsKt.mapOf(TuplesKt.to("action", action)), up ? "ярче" : "тусклее", null, null, null, null, null, null, false, 1040384, null);
    }

    private final SemanticUnderstanding weatherIntent(String t, String raw, String norm) {
        if (!StringsKt.contains$default((CharSequence) t, (CharSequence) "погод", false, 2, (Object) null)) {
            return null;
        }
        if (!hasConversationalWrapper(t) || t.length() <= 60) {
            return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "weather_query", "weather", 0.8f, AmbiguityLevel.LOW, SemanticClarity.CLEAR, RouteDecision.FAST_SKILL, "semantic_weather", CollectionsKt.listOf("weather"), null, t.length() < 40 ? t : "погода", null, null, null, null, null, null, false, 1042432, null);
        }
        return null;
    }

    private final SemanticUnderstanding musicIntent(String t, String raw, String norm) {
        boolean play = StringsKt.contains$default((CharSequence) t, (CharSequence) "включи музык", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "играй", false, 2, (Object) null) || StringsKt.startsWith$default(t, "послушай", false, 2, (Object) null);
        boolean stop = StringsKt.contains$default((CharSequence) t, (CharSequence) "выключи музык", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "останови музык", false, 2, (Object) null) || Intrinsics.areEqual(t, "пауза");
        if (!play && !stop) {
            return null;
        }
        String action = stop ? "pause" : "play";
        return understanding$default(this, new SemanticInput(raw, norm, null, null, null, null, null, false, 252, null), raw, norm, "music_control", "music", 0.8f, AmbiguityLevel.LOW, SemanticClarity.CLEAR, RouteDecision.FAST_SKILL, "semantic_music_" + action, CollectionsKt.listOf("music"), MapsKt.mapOf(TuplesKt.to("action", action)), stop ? "выключи музыку" : "включи музыку", null, null, null, null, null, null, false, 1040384, null);
    }

    private final boolean looksConversational(String t) {
        boolean z;
        if (t.length() < 8) {
            return false;
        }
        Iterable markers = CollectionsKt.listOf((Object[]) new String[]{"что ты думаешь", "что думаешь", "как думаешь", "расскажи", "объясни", "посоветуй", "почему", "зачем", "мне скучно", "как тебе", "стоит ли", "как ты относишься", "про квантов", "про космос", "про ии", "про ai", "что такое", "помоги понять", "разверни", "подробнее про"});
        Iterable $this$any$iv = markers;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (StringsKt.contains$default((CharSequence) t, (CharSequence) it2, false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return hasConversationalWrapper(t) && !looksLikeDeviceCommand(t);
    }

    private final boolean hasConversationalWrapper(String t) {
        Iterable wrappers = CollectionsKt.listOf((Object[]) new String[]{"как думаешь", "как тебе", "стоит ли", "и стоит", "мне кажется", "а что если", "не знаешь ли", "можешь ли сказать", "интересно"});
        Iterable $this$any$iv = wrappers;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) t, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final boolean looksLikeDeviceCommand(String t) {
        Iterable cmds = CollectionsKt.listOf((Object[]) new String[]{"фонарик", "громк", "тише", "открой", "запусти", "ярче", "погода", "музык", "таймер", "позвони"});
        Iterable $this$any$iv = cmds;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) t, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final boolean looksLikeConcreteTarget(String prev) {
        String str = prev;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        String p = prev.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(p, "toLowerCase(...)");
        if ((StringsKt.contains$default((CharSequence) p, (CharSequence) "открой ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) p, (CharSequence) "запусти ", false, 2, (Object) null)) && new Regex("\\s+").split(p, 0).size() >= 2) {
            String target = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(p, (CharSequence) "открой "), (CharSequence) "запусти ")).toString();
            if (!StringsKt.isBlank(target) && !SetsKt.setOf((Object[]) new String[]{"его", "это", "её", "ее", "тот"}).contains(target)) {
                return true;
            }
        }
        Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"telegram", "браузер", "chrome", "youtube", "настройк", "фонарик"});
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) p, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isShortTimeQuery(String t) {
        if (t.length() > 36) {
            return false;
        }
        return SetsKt.setOf((Object[]) new String[]{"который час", "сколько времени", "сколько время", "текущее время", "время"}).contains(t) || StringsKt.startsWith$default(t, "который час", false, 2, (Object) null) || StringsKt.startsWith$default(t, "сколько времени", false, 2, (Object) null);
    }

    private final boolean isShortDateQuery(String t) {
        if (t.length() > 40) {
            return false;
        }
        return StringsKt.contains$default((CharSequence) t, (CharSequence) "какая дата", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "какое число", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "какой сегодня день", false, 2, (Object) null) || Intrinsics.areEqual(t, "сегодня какая дата");
    }

    private final boolean isShortGreeting(String t) {
        if (t.length() > 28) {
            return false;
        }
        return SetsKt.setOf((Object[]) new String[]{"привет", "здравствуй", "здравствуйте", "добрый день", "добрый вечер", "доброе утро", "хай", "hello", "hi"}).contains(t);
    }

    private final boolean isShortThanks(String t) {
        if (t.length() <= 24) {
            return Intrinsics.areEqual(t, "спасибо") || Intrinsics.areEqual(t, "благодарю") || StringsKt.startsWith$default(t, "спасибо ", false, 2, (Object) null);
        }
        return false;
    }

    private final boolean isShortBye(String t) {
        return t.length() <= 24 && (Intrinsics.areEqual(t, "пока") || Intrinsics.areEqual(t, "до свидания") || Intrinsics.areEqual(t, "увидимся"));
    }

    private final boolean isShortIdentity(String t) {
        if (t.length() > 32) {
            return false;
        }
        return SetsKt.setOf((Object[]) new String[]{"кто ты", "ты кто", "как тебя зовут", "что ты такое"}).contains(t) || StringsKt.startsWith$default(t, "кто ты ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "ты кто ", false, 2, (Object) null);
    }

    private final boolean isShortJoke(String t) {
        if (t.length() > 36) {
            return false;
        }
        return SetsKt.setOf((Object[]) new String[]{"пошути", "шутка", "анекдот", "расскажи шутку", "расскажи анекдот"}).contains(t);
    }

    private final boolean isIncompleteAction(String t) {
        Set incomplete = SetsKt.setOf((Object[]) new String[]{"включи", "выключи", "открой", "запусти", "поставь", "найди", "сделай", "отправь", "напиши"});
        return incomplete.contains(t);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:26:0x0048 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x0060 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:35:0x0063 A[ORIG_RETURN, RETURN] */
    private final String targetedIncompleteQuestion(String t) {
        switch (t) {
            case "отправь":
                return "Кому и что написать?";
            case "найди":
                return "Что найти?";
            case "запусти":
                return "Какое приложение открыть?";
            case "включи":
                return "Что включить — фонарик, музыку или что-то другое?";
            case "напиши":
                return "Кому и что написать?";
            case "открой":
                return "Какое приложение открыть?";
            case "выключи":
                return "Что выключить — фонарик, музыку или звук?";
            case "сделай":
                return "Что именно сделать?";
            default:
                return "Что именно сделать, сэр?";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        if (r6.equals("открой") == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004f, code lost:
    
        if (r6.equals("запусти") == false) goto L20;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<java.lang.String> incompleteCandidates(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 1
            r2 = 0
            r3 = 2
            switch(r0) {
                case 1501894298: goto L49;
                case 1715544524: goto L31;
                case 2066438981: goto L28;
                case 2127486981: goto Lb;
                default: goto La;
            }
        La:
            goto L61
        Lb:
            java.lang.String r0 = "выключи"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L14
            goto La
        L14:
            r0 = 3
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r4 = "выключи фонарик"
            r0[r2] = r4
            java.lang.String r2 = "выключи музыку"
            r0[r1] = r2
            java.lang.String r1 = "тише"
            r0[r3] = r1
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            goto L65
        L28:
            java.lang.String r0 = "открой"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L52
            goto La
        L31:
            java.lang.String r0 = "включи"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L3a
            goto La
        L3a:
            java.lang.String[] r0 = new java.lang.String[r3]
            java.lang.String r3 = "включи фонарик"
            r0[r2] = r3
            java.lang.String r2 = "включи музыку"
            r0[r1] = r2
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            goto L65
        L49:
            java.lang.String r0 = "запусти"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L52
            goto La
        L52:
            java.lang.String[] r0 = new java.lang.String[r3]
            java.lang.String r3 = "открой Telegram"
            r0[r2] = r3
            java.lang.String r2 = "открой настройки"
            r0[r1] = r2
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            goto L65
        L61:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L65:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.core.runtime.semantic.FastSemanticClassifier.incompleteCandidates(java.lang.String):java.util.List");
    }

    private final int countActionVerbs(String t) {
        Iterable verbs = CollectionsKt.listOf((Object[]) new String[]{"открой", "запусти", "найди", "отправь", "напиши", "включи", "выключи", "сравни", "подготов", "скачай", "купить", "забронируй", "покажи"});
        Iterable $this$count$iv = verbs;
        if (($this$count$iv instanceof Collection) && ((Collection) $this$count$iv).isEmpty()) {
            return 0;
        }
        int count$iv = 0;
        for (Object element$iv : $this$count$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) t, (CharSequence) it, false, 2, (Object) null) && (count$iv = count$iv + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return count$iv;
    }

    private final List<String> extractConstraints(String t) {
        List<String> groupValues;
        String it;
        List out = new ArrayList();
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "завтра", false, 2, (Object) null)) {
            out.add("when=tomorrow");
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "сегодня", false, 2, (Object) null)) {
            out.add("when=today");
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "дешёв", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "дешев", false, 2, (Object) null)) {
            out.add("prefer=cheapest");
        }
        MatchResult matchResultFind$default = Regex.find$default(new Regex("в\\s+([а-яёa-z\\-]{3,})", RegexOption.IGNORE_CASE), t, 0, 2, null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (it = (String) CollectionsKt.getOrNull(groupValues, 1)) != null && !SetsKt.setOf((Object[]) new String[]{"интернете", "браузере", "телеграме"}).contains(it)) {
            out.add("place=" + it);
        }
        return out;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ SemanticUnderstanding understanding$default(FastSemanticClassifier fastSemanticClassifier, SemanticInput semanticInput, String str, String str2, String str3, String str4, float f, AmbiguityLevel ambiguityLevel, SemanticClarity semanticClarity, RouteDecision routeDecision, String str5, List list, Map map, String str6, String str7, List list2, List list3, List list4, List list5, List list6, boolean z, int i, Object obj) {
        return fastSemanticClassifier.understanding(semanticInput, str, str2, str3, str4, f, ambiguityLevel, semanticClarity, routeDecision, str5, (i & 1024) != 0 ? CollectionsKt.emptyList() : list, (i & 2048) != 0 ? MapsKt.emptyMap() : map, (i & 4096) != 0 ? null : str6, (i & 8192) != 0 ? null : str7, (i & 16384) != 0 ? CollectionsKt.emptyList() : list2, (32768 & i) != 0 ? CollectionsKt.emptyList() : list3, (65536 & i) != 0 ? CollectionsKt.emptyList() : list4, (131072 & i) != 0 ? CollectionsKt.emptyList() : list5, (262144 & i) != 0 ? CollectionsKt.emptyList() : list6, (i & 524288) != 0 ? false : z);
    }

    private final SemanticUnderstanding understanding(SemanticInput input, String raw, String norm, String goal, String intent, float confidence, AmbiguityLevel ambiguity, SemanticClarity clarity, RouteDecision route, String reason, List<String> caps, Map<String, String> args, String rewrite, String clarify, List<String> candidates, List<String> constraints, List<SemanticEntity> entities, List<String> refs, List<String> notes, boolean llm) {
        return new SemanticUnderstanding(raw, norm, goal, intent, entities, args, constraints, CollectionsKt.listOfNotNull(input.getLastToolId()), refs, confidence, ambiguity, clarity, caps, route, reason, clarify, candidates, rewrite, llm, notes);
    }
}
