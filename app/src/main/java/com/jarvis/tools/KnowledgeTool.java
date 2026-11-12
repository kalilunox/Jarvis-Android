package com.jarvis.tools;

import android.util.Log;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.app.VoiceService;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.tools.net.SimpleHttp;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: KnowledgeTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J*\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J,\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002J\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\u001b2\u0006\u0010!\u001a\u00020\u000eH\u0002J(\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010#2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0002J\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0002J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010'\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0002J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u000eH\u0002J6\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010#2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0019H\u0002J\u0018\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u000eH\u0002J0\u00100\u001a\u0002012\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0019H\u0002J\u001e\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010#2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002J&\u00104\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010#2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u001d\u00105\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0000¢\u0006\u0002\b6J\u0018\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000eH\u0002J\u0010\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000eH\u0002J\u0012\u0010<\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0015\u0010=\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000eH\u0000¢\u0006\u0002\b>R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006@"}, d2 = {"Lcom/jarvis/tools/KnowledgeTool;", "Lcom/jarvis/core/common/Tool;", "<init>", "()V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "answerFollowUp", SearchIntents.EXTRA_QUERY, "", "topic", "excludeTitle", "sentenceFromTopic", "question", "wikipediaLongExtract", "ok", VoiceService.EXTRA_TEXT, "source", "title", "looksLikeQuestion", "", "entityCandidates", "", "canonicalizeTopic", "raw", "aspectSearchQueries", "extractAspect", "aspectSynonyms", "aspect", "wikipediaSummary", "Lkotlin/Pair;", "pluralAnimalTitle", "canon", "isLikelyAnimalQuery", "looksLikePersonBio", "extract", "looksLikeAnimalArticle", "wikipediaSearchAnswer", "search", "preferAspect", "containsWord", "hay", "needle", "scorePage", "", "topicFold", "fetchSummary", "localFact", "formatWikiExtract", "formatWikiExtract$tools_debug", "sameTitle", "a", "b", "fold", "s", "duckDuckGo", "sanitizeForSpeech", "sanitizeForSpeech$tools_debug", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KnowledgeTool implements Tool {
    private static final int MAX_SPEECH_CHARS = 280;
    private static final int MIN_SPEECH_CHARS = 80;
    private static final String TAG = "KnowledgeTool";
    private final ToolDefinition definition = new ToolDefinition("knowledge", "Знания", "Краткий ответ на фактический вопрос (Википедия / DuckDuckGo)", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter(SearchIntents.EXTRA_QUERY, "Вопрос или тема", ParameterType.STRING, true, null, 16, null), new ToolParameter("topic", "Тема диалога (follow-up)", ParameterType.STRING, false, null, 16, null), new ToolParameter("exclude_title", "Пропустить уже озвученную статью", ParameterType.STRING, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.ONLINE_REQUIRED, null, 160, null);
    private static final Map<String, String> LEMMA_TOPICS = MapsKt.mapOf(TuplesKt.to("дельфинов", "Дельфин"), TuplesKt.to("дельфины", "Дельфин"), TuplesKt.to("дельфина", "Дельфин"), TuplesKt.to("дельфин", "Дельфин"), TuplesKt.to("кошек", "Кошка"), TuplesKt.to("кошки", "Кошка"), TuplesKt.to("кошка", "Кошка"), TuplesKt.to("котов", "Кот"), TuplesKt.to("собак", "Собака"), TuplesKt.to("собаки", "Собака"), TuplesKt.to("собака", "Собака"), TuplesKt.to("волков", "Волк"), TuplesKt.to("волки", "Волк"), TuplesKt.to("волк", "Волк"), TuplesKt.to("лисиц", "Лисица"), TuplesKt.to("лисы", "Лисица"), TuplesKt.to("медведей", "Медведь"), TuplesKt.to("медведи", "Медведь"), TuplesKt.to("слонов", "Слон"), TuplesKt.to("слоны", "Слон"), TuplesKt.to("птиц", "Птицы"), TuplesKt.to("китов", "Кит"), TuplesKt.to("киты", "Кит"));
    private static final Map<String, String> LOCAL_FACTS = MapsKt.mapOf(TuplesKt.to("дельфин", "Дельфины — водные млекопитающие из отряда китообразных. Они живут в морях и океанах, дышат воздухом и славятся высоким интеллектом."), TuplesKt.to("кошка", "Кошка — домашнее хищное млекопитающее семейства кошачьих. Один из самых популярных питомцев человека."), TuplesKt.to("кот", "Кот — самец домашней кошки. Кошки — мелкие хищные млекопитающие, живущие рядом с людьми тысячелетиями."), TuplesKt.to("собака", "Собака — домашнее животное семейства псовых, потомок волка. Близкий компаньон человека."), TuplesKt.to("волк", "Волк — крупный хищник семейства псовых. Живёт стаями в лесах и тундре."), TuplesKt.to("слон", "Слон — крупнейшее наземное млекопитающее. Известен хоботом и высоким интеллектом."), TuplesKt.to("кит", "Киты — крупные морские млекопитающие отряда китообразных. Дышат воздухом через дыхало."), TuplesKt.to("медведь", "Медведь — крупный хищник семейства медвежьих. Встречается в лесах Евразии и Америки."), TuplesKt.to("лисица", "Лисица — небольшой хищник семейства псовых с пушистым хвостом. Живёт в лесах и полях."), TuplesKt.to("птицы", "Птицы — теплокровные позвоночные с перьями и крыльями. Большинство умеет летать."));
    private static final Map<String, String> LOCAL_ASPECTS = MapsKt.mapOf(TuplesKt.to("дельфин|интеллект", "Дельфины считают одними из самых умных животных: у них сложный мозг, они учатся, играют и общаются звуковыми сигналами."), TuplesKt.to("дельфин|питание", "Дельфины в основном питаются рыбой и кальмарами, охотясь группами."), TuplesKt.to("дельфин|ареал", "Дельфины обитают почти во всех океанах и морях; есть и пресноводные речные виды."), TuplesKt.to("кошка|интеллект", "Кошки хорошо ориентируются в пространстве и запоминают маршруты, хотя обучаются иначе, чем собаки."), TuplesKt.to("собака|интеллект", "Собаки хорошо понимают команды и эмоции человека, за что их ценят как компаньонов."));

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00c2  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v3 */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        boolean z;
        String strDuckDuckGo;
        ToolResult toolResultAnswerFollowUp;
        String string;
        String string2;
        String string3;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        String string4 = null;
        String string5 = (obj == null || (string3 = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string3).toString();
        if (string5 == null) {
            string5 = "";
        }
        String str = string5;
        Object obj2 = call.getArguments().get("topic");
        String string6 = (obj2 == null || (string2 = obj2.toString()) == null) ? null : StringsKt.trim((CharSequence) string2).toString();
        if (string6 == null) {
            string6 = "";
        }
        String str2 = string6;
        Object obj3 = call.getArguments().get("exclude_title");
        if (obj3 != null && (string = obj3.toString()) != null) {
            string4 = StringsKt.trim((CharSequence) string).toString();
        }
        boolean z2 = string4 != null ? string4 : "";
        if (StringsKt.isBlank(str)) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "О чём рассказать?", null, 0L, 48, null);
        }
        boolean z3 = false;
        z3 = false;
        try {
            if (looksLikeQuestion(str)) {
                z = true;
            } else {
                try {
                    if (StringsKt.isBlank(str2) || Intrinsics.areEqual(str, str2)) {
                        z = false;
                    } else {
                        z = true;
                    }
                } catch (Throwable th) {
                    th = th;
                    z2 = 1;
                }
            }
            Pair<String, String> pairLocalFact = localFact(str, str2);
            if (pairLocalFact != null) {
                String strComponent1 = pairLocalFact.component1();
                String strComponent2 = pairLocalFact.component2();
                Log.i(TAG, "local fact title='" + strComponent1 + "' q='" + StringsKt.take(str, 40) + "'");
                return ok(call, strComponent2, ImagesContract.LOCAL, strComponent1);
            }
            if (z && looksLikeQuestion(str) && (toolResultAnswerFollowUp = answerFollowUp(call, str, str2, z2)) != null) {
                return toolResultAnswerFollowUp;
            }
            Iterator<String> it = entityCandidates(str, str2).iterator();
            while (it.hasNext()) {
                Pair<String, String> pairWikipediaSummary = wikipediaSummary(it.next(), z2);
                if (pairWikipediaSummary != null) {
                    String strComponent3 = pairWikipediaSummary.component1();
                    String strComponent4 = pairWikipediaSummary.component2();
                    Log.i(TAG, "wiki ok title='" + strComponent3 + "' q='" + StringsKt.take(str, 40) + "'");
                    return ok(call, strComponent4, "wikipedia", strComponent3);
                }
            }
            String strDuckDuckGo2 = duckDuckGo(str);
            try {
                if (strDuckDuckGo2 != null) {
                    return ok$default(this, call, sanitizeForSpeech$tools_debug(strDuckDuckGo2), "duckduckgo", null, 8, null);
                }
                if (!StringsKt.isBlank(str2) && (strDuckDuckGo = duckDuckGo(str2)) != null) {
                    return ok$default(this, call, sanitizeForSpeech$tools_debug(strDuckDuckGo), "duckduckgo", null, 8, null);
                }
                Pair<String, String> pairLocalFact2 = localFact(str, str2);
                if (pairLocalFact2 != null) {
                    return ok(call, pairLocalFact2.component2(), ImagesContract.LOCAL, pairLocalFact2.component1());
                }
                Log.w(TAG, "no answer q='" + StringsKt.take(str, 60) + "' topic='" + StringsKt.take(str2, 40) + "'");
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не нашёл краткий ответ по «" + str + "». Уточни формулировку.", null, 0L, 48, null);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            z2 = 1;
        }
        Log.w(TAG, "knowledge failed: " + th.getMessage());
        Pair<String, String> pairLocalFact3 = localFact(str, str2);
        if (pairLocalFact3 != null) {
            String strComponent5 = pairLocalFact3.component1();
            String strComponent6 = pairLocalFact3.component2();
            Log.i(TAG, "local after error title='" + strComponent5 + "'");
            return ok(call, strComponent6, ImagesContract.LOCAL, strComponent5);
        }
        String requestId = call.getRequestId();
        String id = getDefinition().getId();
        String message = th.getMessage();
        if (message != null && StringsKt.contains(message, "Unable to resolve", z2) == z2) {
            z3 = z2;
        }
        ToolResultStatus toolResultStatus = z3 ? ToolResultStatus.OFFLINE : ToolResultStatus.FAILED;
        String message2 = th.getMessage();
        if (message2 == null) {
            message2 = th.getClass().getSimpleName();
        }
        return new ToolResult(requestId, id, toolResultStatus, "Поиск не удался: " + message2, null, 0L, 48, null);
    }

    private final ToolResult answerFollowUp(ToolCall call, String query, String topic, String excludeTitle) throws UnsupportedEncodingException {
        Pair<String, String> pairWikipediaSearchAnswer;
        String text;
        String text2;
        Pair<String, String> pairLocalFact = localFact(query, topic);
        if (pairLocalFact != null) {
            String title = pairLocalFact.component1();
            return ok(call, pairLocalFact.component2(), ImagesContract.LOCAL, title);
        }
        String it = duckDuckGo(query);
        if (it != null) {
            return ok$default(this, call, sanitizeForSpeech$tools_debug(it), "duckduckgo", null, 8, null);
        }
        if (!StringsKt.isBlank(topic) && ((StringsKt.contains$default((CharSequence) fold(query), (CharSequence) "родил", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) fold(query), (CharSequence) "рождени", false, 2, (Object) null) || StringsKt.startsWith$default(fold(query), "где ", false, 2, (Object) null)) && (text2 = sentenceFromTopic(topic, query)) != null)) {
            return ok(call, text2, "wikipedia", topic);
        }
        for (String s : aspectSearchQueries(query, topic)) {
            Pair<String, String> pairWikipediaSearchAnswer2 = wikipediaSearchAnswer(s, excludeTitle, topic, true);
            if (pairWikipediaSearchAnswer2 != null) {
                String title2 = pairWikipediaSearchAnswer2.component1();
                return ok(call, pairWikipediaSearchAnswer2.component2(), "wikipedia", title2);
            }
        }
        if (!StringsKt.isBlank(topic) && (text = sentenceFromTopic(topic, query)) != null) {
            return ok(call, text, "wikipedia", topic);
        }
        if (StringsKt.isBlank(topic) || (pairWikipediaSearchAnswer = wikipediaSearchAnswer(topic, excludeTitle, topic, false)) == null) {
            return null;
        }
        String title3 = pairWikipediaSearchAnswer.component1();
        return ok(call, pairWikipediaSearchAnswer.component2(), "wikipedia", title3);
    }

    /* JADX WARN: Code duplicated, block: B:80:0x01eb A[LOOP:0: B:50:0x014f->B:80:0x01eb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:88:0x0201 A[EDGE_INSN: B:88:0x0201->B:82:0x0201 BREAK  A[LOOP:0: B:50:0x014f->B:80:0x01eb], SYNTHETIC] */
    private final String sentenceFromTopic(String topic, String question) throws JSONException, UnsupportedEncodingException {
        List hints;
        Object element$iv;
        String longText;
        List hints2;
        String q;
        boolean z;
        char c;
        boolean z2;
        boolean z3;
        String longText2 = wikipediaLongExtract(topic);
        if (longText2 == null) {
            Pair<String, String> pairWikipediaSummary = wikipediaSummary(topic, "");
            longText2 = pairWikipediaSummary != null ? pairWikipediaSummary.getSecond() : null;
            if (longText2 == null) {
                return null;
            }
        }
        String q2 = fold(question);
        if (StringsKt.contains$default((CharSequence) q2, (CharSequence) "родил", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q2, (CharSequence) "рождени", false, 2, (Object) null) || StringsKt.startsWith$default(q2, "где ", false, 2, (Object) null)) {
            hints = CollectionsKt.listOf((Object[]) new String[]{"родил", "родился", "родилась", "москв", "1799", "шестого июня", "26 мая"});
        } else if (StringsKt.contains$default((CharSequence) q2, (CharSequence) "умн", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q2, (CharSequence) "интеллект", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q2, (CharSequence) "мозг", false, 2, (Object) null)) {
            hints = CollectionsKt.listOf((Object[]) new String[]{"интеллект", "мозг", "умн", "когнитив"});
        } else if (StringsKt.contains$default((CharSequence) q2, (CharSequence) "едят", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q2, (CharSequence) "пита", false, 2, (Object) null)) {
            hints = CollectionsKt.listOf((Object[]) new String[]{"пита", "едят", "корм"});
        } else {
            Iterable $this$filter$iv = StringsKt.split$default((CharSequence) extractAspect(question), new char[]{' '}, false, 0, 6, (Object) null);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                if (it.length() > 3) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            hints = (List) destination$iv$iv;
        }
        if (hints.isEmpty()) {
            return null;
        }
        List<String> listSplit = new Regex("(?<=[.!?…])\\s+").split(longText2, 0);
        List<String> $this$firstOrNull$iv = listSplit;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            String s = (String) element$iv;
            String f = fold(s);
            Iterable $this$any$iv = hints;
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                longText = longText2;
                hints2 = hints;
                q = q2;
                z = false;
            } else {
                Iterator it3 = $this$any$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        longText = longText2;
                        hints2 = hints;
                        q = q2;
                        z = false;
                        break;
                    }
                    Object element$iv2 = it3.next();
                    String h = (String) element$iv2;
                    longText = longText2;
                    hints2 = hints;
                    q = q2;
                    if (StringsKt.contains$default((CharSequence) f, (CharSequence) h, false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                    longText2 = longText;
                    hints = hints2;
                    q2 = q;
                }
            }
            if (z) {
                if (containsWord(f, fold(topic))) {
                    c = 3;
                    z2 = false;
                } else {
                    z2 = false;
                    if (StringsKt.contains$default((CharSequence) f, (CharSequence) "родил", false, 2, (Object) null)) {
                        c = 3;
                    } else {
                        c = 3;
                        if (listSplit.indexOf(s) < 3) {
                        }
                        if (z3) {
                            break;
                        }
                        longText2 = longText;
                        hints = hints2;
                        q2 = q;
                    }
                }
                z3 = true;
                if (z3) {
                    break;
                    break;
                }
                longText2 = longText;
                hints = hints2;
                q2 = q;
            } else {
                c = 3;
                z2 = false;
            }
            z3 = z2;
            if (z3) {
                break;
                break;
            }
            longText2 = longText;
            hints = hints2;
            q2 = q;
        }
        String hit = (String) element$iv;
        if (hit == null) {
            return null;
        }
        return sanitizeForSpeech$tools_debug(hit);
    }

    private final String wikipediaLongExtract(String topic) throws JSONException, UnsupportedEncodingException {
        JSONArray titles;
        JSONObject jSONObjectOptJSONObject;
        JSONObject pages;
        String q = URLEncoder.encode(topic, Charsets.UTF_8.name());
        String searchUrl = "https://ru.wikipedia.org/w/api.php?action=opensearch&search=" + q + "&limit=1&namespace=0&format=json";
        JSONArray arr = SimpleHttp.INSTANCE.getJsonArrayOrNull(searchUrl, 3600000L);
        if (arr == null || arr.length() < 2 || (titles = arr.optJSONArray(1)) == null || titles.length() == 0) {
            return null;
        }
        String title = titles.getString(0);
        String enc = URLEncoder.encode(title, Charsets.UTF_8.name());
        String url = "https://ru.wikipedia.org/w/api.php?action=query&titles=" + enc + "&prop=extracts&explaintext=1&exsentences=12&format=json";
        JSONObject json = SimpleHttp.INSTANCE.getJsonOrNull(url, 3600000L);
        if (json == null || (jSONObjectOptJSONObject = json.optJSONObject(SearchIntents.EXTRA_QUERY)) == null || (pages = jSONObjectOptJSONObject.optJSONObject("pages")) == null) {
            return null;
        }
        Iterator<String> itKeys = pages.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        String key = (String) SequencesKt.firstOrNull(SequencesKt.asSequence(itKeys));
        if (key == null) {
            return null;
        }
        JSONObject jSONObjectOptJSONObject2 = pages.optJSONObject(key);
        String strOptString = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("extract") : null;
        if (strOptString == null) {
            strOptString = "";
        }
        String extract = StringsKt.trim((CharSequence) strOptString).toString();
        if (extract.length() > 40) {
            return extract;
        }
        return null;
    }

    static /* synthetic */ ToolResult ok$default(KnowledgeTool knowledgeTool, ToolCall toolCall, String str, String str2, String str3, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = null;
        }
        return knowledgeTool.ok(toolCall, str, str2, str3);
    }

    private final ToolResult ok(ToolCall call, String text, String source, String title) {
        String requestId = call.getRequestId();
        String id = getDefinition().getId();
        ToolResultStatus toolResultStatus = ToolResultStatus.SUCCESS;
        Map $this$ok_u24lambda_u240 = MapsKt.createMapBuilder();
        $this$ok_u24lambda_u240.put("source", source);
        $this$ok_u24lambda_u240.put("chars", Integer.valueOf(text.length()));
        String str = title;
        if (!(str == null || StringsKt.isBlank(str))) {
            $this$ok_u24lambda_u240.put("title", title);
            $this$ok_u24lambda_u240.put("search", title);
        }
        Unit unit = Unit.INSTANCE;
        return new ToolResult(requestId, id, toolResultStatus, text, MapsKt.build($this$ok_u24lambda_u240), 0L, 32, null);
    }

    private final boolean looksLikeQuestion(String query) {
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String q = StringsKt.trim((CharSequence) lowerCase).toString();
        return StringsKt.startsWith$default(q, "почему", false, 2, (Object) null) || StringsKt.startsWith$default(q, "зачем", false, 2, (Object) null) || StringsKt.startsWith$default(q, "как ", false, 2, (Object) null) || StringsKt.startsWith$default(q, "где ", false, 2, (Object) null) || StringsKt.startsWith$default(q, "когда ", false, 2, (Object) null) || StringsKt.startsWith$default(q, "какой ", false, 2, (Object) null) || StringsKt.startsWith$default(q, "какая ", false, 2, (Object) null) || StringsKt.startsWith$default(q, "откуда", false, 2, (Object) null) || StringsKt.startsWith$default(q, "сколько ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "?", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) ": почему", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) ": зачем", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) ": как ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) ": где ", false, 2, (Object) null);
    }

    private final List<String> entityCandidates(String query, String topic) {
        Iterable out = new LinkedHashSet();
        if (!StringsKt.isBlank(topic)) {
            ((Collection) out).add(canonicalizeTopic(topic));
            ((Collection) out).add(topic);
        }
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String s = StringsKt.trim((CharSequence) lowerCase).toString();
        for (String p : CollectionsKt.listOf((Object[]) new String[]{"кто такие ", "кто такой ", "кто такая ", "кто это ", "что такое ", "что это такое ", "что за ", "расскажи коротко про ", "расскажи кратко про ", "расскажи про ", "расскажи о ", "загугли ", "коротко про ", "кратко про "})) {
            if (StringsKt.startsWith$default(s, p, false, 2, (Object) null)) {
                s = StringsKt.trim((CharSequence) StringsKt.removePrefix(s, (CharSequence) p)).toString();
                break;
            }
        }
        String lemma = canonicalizeTopic(s);
        if (!StringsKt.isBlank(lemma) && !looksLikeQuestion(s)) {
            ((Collection) out).add(lemma);
            if (!Intrinsics.areEqual(lemma, s)) {
                ((Collection) out).add(s);
            }
        }
        if (!looksLikeQuestion(query)) {
            String qCanon = canonicalizeTopic(StringsKt.trim((CharSequence) query).toString());
            ((Collection) out).add(qCanon);
            ((Collection) out).add(StringsKt.trim((CharSequence) query).toString());
        }
        Iterable $this$filter$iv = out;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.take(CollectionsKt.distinct((List) destination$iv$iv), 6);
    }

    private final String canonicalizeTopic(String raw) {
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String k = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.trim((CharSequence) lowerCase).toString(), (CharSequence) "про "), (CharSequence) "о ")).toString();
        String str = LEMMA_TOPICS.get(k);
        if (str != null) {
            return str;
        }
        String string = StringsKt.trim((CharSequence) raw).toString();
        if (!(string.length() > 0)) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        char it = string.charAt(0);
        StringBuilder sbAppend = sb.append((Object) (Character.isLowerCase(it) ? CharsKt.titlecase(it, new Locale("ru")) : String.valueOf(it)));
        String strSubstring = string.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }

    private final List<String> aspectSearchQueries(String query, String topic) {
        List<String> groupValues;
        String str;
        Iterable out = new LinkedHashSet();
        String aspect = extractAspect(query);
        String string = StringsKt.trim((CharSequence) topic).toString();
        if (StringsKt.isBlank(string)) {
            String string2 = null;
            MatchResult matchResultFind$default = Regex.find$default(new Regex("\\(([^)]+)\\)"), query, 0, 2, null);
            if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = (String) CollectionsKt.getOrNull(groupValues, 1)) != null) {
                string2 = StringsKt.trim((CharSequence) str).toString();
            }
            string = string2 == null ? "" : string2;
        }
        String t = string;
        if (!StringsKt.isBlank(t) && !StringsKt.isBlank(aspect)) {
            ((Collection) out).add(t + StringUtils.SPACE + aspect);
            ((Collection) out).add(aspect + StringUtils.SPACE + t);
            for (String syn : aspectSynonyms(aspect)) {
                ((Collection) out).add(t + StringUtils.SPACE + syn);
            }
        }
        ((Collection) out).add(StringsKt.trim((CharSequence) query).toString());
        Iterable $this$filter$iv = out;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.take((List) destination$iv$iv, 6);
    }

    private final String extractAspect(String query) {
        String q = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "умн", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "интеллект", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "мозг", false, 2, (Object) null)) {
            return "интеллект";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "едят", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "пита", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "еда", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "корм", false, 2, (Object) null)) {
            return "питание";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "живут", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "обита", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "где ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "ареал", false, 2, (Object) null)) {
            return "ареал";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "родил", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "рождение", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "родился", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "родилась", false, 2, (Object) null)) {
            return "рождение";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "размер", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "больш", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "длин", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "вес", false, 2, (Object) null)) {
            return "размер";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "скорост", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "быстр", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "плава", false, 2, (Object) null)) {
            return "скорость";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "размнож", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "детён", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "детеныш", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "рожда", false, 2, (Object) null)) {
            return "размножение";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "истори", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "появил", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "происхожд", false, 2, (Object) null)) {
            return "история";
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "опасн", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "враг", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "хищник", false, 2, (Object) null)) {
            return "опасность";
        }
        Set stop = SetsKt.setOf((Object[]) new String[]{"почему", "зачем", "как", "их", "они", "им", "это", "считают", "считается", "такими", "такой", "самыми", "самых", "одних", "одними", "из", "самых", "очень", "наиболее", "про", "о", "в", "на", "и", "а", "же", "животных", "животные", "называют", "считают"});
        Iterable $this$map$iv = new Regex("\\s+").split(q, 0);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(StringsKt.trim((String) item$iv$iv, '?', '!', '.', ',', Typography.ellipsis));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (it.length() > 3 && !stop.contains(it)) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return CollectionsKt.joinToString$default(CollectionsKt.take((List) destination$iv$iv2, 2), StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final List<String> aspectSynonyms(String aspect) {
        switch (aspect.hashCode()) {
            case 243222739:
                if (aspect.equals("питание")) {
                    return CollectionsKt.listOf((Object[]) new String[]{"питание", "еда"});
                }
                break;
            case 1023496432:
                if (aspect.equals("ареал")) {
                    return CollectionsKt.listOf((Object[]) new String[]{"ареал", "обитание", "распространение"});
                }
                break;
            case 1382747361:
                if (aspect.equals("рождение")) {
                    return CollectionsKt.listOf((Object[]) new String[]{"рождение", "биография", "детство"});
                }
                break;
            case 1841637349:
                if (aspect.equals("интеллект")) {
                    return CollectionsKt.listOf((Object[]) new String[]{"интеллект", "умные", "мозг", "когнитивные"});
                }
                break;
        }
        return CollectionsKt.listOf(aspect);
    }

    static /* synthetic */ Pair wikipediaSummary$default(KnowledgeTool knowledgeTool, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return knowledgeTool.wikipediaSummary(str, str2);
    }

    private final Pair<String, String> wikipediaSummary(String query, String excludeTitle) throws JSONException, UnsupportedEncodingException {
        JSONArray titles;
        String canon;
        Pair<String, String> pairFetchSummary;
        String str = excludeTitle;
        boolean wantAnimal = isLikelyAnimalQuery(query);
        String canon2 = canonicalizeTopic(query);
        if (!StringsKt.isBlank(canon2)) {
            for (String direct : CollectionsKt.distinct(CollectionsKt.listOf((Object[]) new String[]{canon2, pluralAnimalTitle(canon2)}))) {
                if (!sameTitle(direct, str) && (pairFetchSummary = fetchSummary(direct)) != null && (!wantAnimal || !looksLikePersonBio(pairFetchSummary.getFirst(), pairFetchSummary.getSecond()) || looksLikeAnimalArticle(pairFetchSummary.getSecond()))) {
                    if (!wantAnimal || looksLikeAnimalArticle(pairFetchSummary.getSecond()) || !looksLikePersonBio(pairFetchSummary.getFirst(), pairFetchSummary.getSecond())) {
                        return pairFetchSummary;
                    }
                }
            }
        }
        String q = URLEncoder.encode(query, Charsets.UTF_8.name());
        String searchUrl = "https://ru.wikipedia.org/w/api.php?action=opensearch&search=" + q + "&limit=5&namespace=0&format=json";
        JSONArray arr = SimpleHttp.INSTANCE.getJsonArrayOrNull(searchUrl, 3600000L);
        if (arr == null || arr.length() < 2 || (titles = arr.optJSONArray(1)) == null || titles.length() == 0) {
            return null;
        }
        Pair<String, String> pair = null;
        int limit = Math.min(titles.length(), 4);
        int i = 0;
        while (i < limit) {
            String title = titles.getString(i);
            Intrinsics.checkNotNull(title);
            if (sameTitle(title, str)) {
                canon = canon2;
            } else {
                if (wantAnimal) {
                    canon = canon2;
                    if (StringsKt.contains$default((CharSequence) title, ',', false, 2, (Object) null)) {
                        String lowerCase = title.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        if (!StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "вид", false, 2, (Object) null)) {
                            continue;
                        }
                    }
                } else {
                    canon = canon2;
                }
                Pair<String, String> pairFetchSummary2 = fetchSummary(title);
                if (pairFetchSummary2 == null) {
                    continue;
                } else {
                    String extract = pairFetchSummary2.getSecond();
                    if (!wantAnimal || !looksLikePersonBio(title, extract) || looksLikeAnimalArticle(extract)) {
                        if (wantAnimal && looksLikeAnimalArticle(extract)) {
                            return pairFetchSummary2;
                        }
                        if (pair == null && (!wantAnimal || !looksLikePersonBio(title, extract))) {
                            pair = pairFetchSummary2;
                        }
                        if (!wantAnimal) {
                            return pairFetchSummary2;
                        }
                    }
                }
            }
            i++;
            str = excludeTitle;
            canon2 = canon;
        }
        return pair;
    }

    private final String pluralAnimalTitle(String canon) {
        String plural;
        String c = StringsKt.trim((CharSequence) canon).toString();
        if (c.length() == 0) {
            return c;
        }
        if (StringsKt.endsWith$default(c, "а", false, 2, (Object) null) || StringsKt.endsWith$default(c, "я", false, 2, (Object) null)) {
            plural = StringsKt.dropLast(c, 1) + "и";
        } else if (StringsKt.endsWith$default(c, "ь", false, 2, (Object) null)) {
            plural = StringsKt.dropLast(c, 1) + "и";
        } else {
            plural = c + "ы";
        }
        if (!(plural.length() > 0)) {
            return plural;
        }
        StringBuilder sb = new StringBuilder();
        char it = plural.charAt(0);
        StringBuilder sbAppend = sb.append((Object) CharsKt.titlecase(it, new Locale("ru")));
        String strSubstring = plural.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }

    private final boolean isLikelyAnimalQuery(String query) {
        boolean z;
        boolean z2;
        String f = fold(query);
        Iterable $this$any$iv = LEMMA_TOPICS.keySet();
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (Intrinsics.areEqual(f, it2) || StringsKt.contains$default((CharSequence) f, (CharSequence) it2, false, 2, (Object) null)) {
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
        Iterable $this$any$iv2 = LEMMA_TOPICS.values();
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z2 = false;
                    break;
                }
                Object element$iv2 = it3.next();
                String it4 = (String) element$iv2;
                if (Intrinsics.areEqual(fold(it4), f) || StringsKt.contains$default((CharSequence) f, (CharSequence) fold(it4), false, 2, (Object) null)) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (z2) {
            return true;
        }
        Iterable $this$any$iv3 = CollectionsKt.listOf((Object[]) new String[]{"животн", "звер", "птиц", "рыб", "кит", "дельфин", "кошк", "собак", "волк", "лис", "медвед", "слон", "пингвин", "акул"});
        if (($this$any$iv3 instanceof Collection) && ((Collection) $this$any$iv3).isEmpty()) {
            return false;
        }
        for (Object element$iv3 : $this$any$iv3) {
            if (StringsKt.contains$default((CharSequence) f, (CharSequence) element$iv3, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final boolean looksLikePersonBio(String title, String extract) {
        boolean z;
        String e = fold(extract);
        String t = fold(title);
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) ",", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) e, (CharSequence) "млекопитающ", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) e, (CharSequence) "животн", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) e, (CharSequence) "вид ", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) e, (CharSequence) "семейнств", false, 2, (Object) null)) {
            return true;
        }
        Iterable hints = CollectionsKt.listOf((Object[]) new String[]{"поэт", "писатель", "актёр", "актер", "политик", "учёный", "ученый", "музыкант", "певец", "журналист", "активист", "перформер", "родился", "родилась", "советский", "российский писатель", "биографи"});
        Iterable $this$any$iv = hints;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            z = false;
        } else {
            for (Object element$iv : $this$any$iv) {
                String it = (String) element$iv;
                if (StringsKt.contains$default((CharSequence) e, (CharSequence) it, false, 2, (Object) null)) {
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    private final boolean looksLikeAnimalArticle(String extract) {
        String e = fold(extract);
        Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"млекопитающ", "животн", "вид ", "семейнств", "отряд", "китообразн", "птиц", "хищн", "ареал", "питают"});
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) e, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final Pair<String, String> wikipediaSearchAnswer(String search, String excludeTitle, String topic, boolean preferAspect) throws UnsupportedEncodingException {
        JSONObject jSONObjectOptJSONObject;
        JSONObject pages;
        Triple triple;
        String q = URLEncoder.encode(search, Charsets.UTF_8.name());
        String url = "https://ru.wikipedia.org/w/api.php?action=query&generator=search&gsrsearch=" + q + "&gsrlimit=5&prop=extracts&exintro=1&explaintext=1&exsentences=4&format=json";
        JSONObject json = SimpleHttp.INSTANCE.getJsonOrNull(url, 3600000L);
        Triple triple2 = null;
        if (json != null && (jSONObjectOptJSONObject = json.optJSONObject(SearchIntents.EXTRA_QUERY)) != null && (pages = jSONObjectOptJSONObject.optJSONObject("pages")) != null) {
            Iterator<String> itKeys = pages.keys();
            Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
            Iterable keys = SequencesKt.toList(SequencesKt.asSequence(itKeys));
            String topicFold = fold(topic);
            Iterable $this$mapNotNull$iv = keys;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                String key = (String) element$iv$iv$iv;
                JSONObject page = pages.optJSONObject(key);
                if (page == null) {
                    q = q;
                    triple = triple2;
                    destination$iv$iv = destination$iv$iv;
                } else {
                    String strOptString = page.optString("title");
                    Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                    String title = StringsKt.trim((CharSequence) strOptString).toString();
                    String strOptString2 = page.optString("extract");
                    Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                    String extract = StringsKt.trim((CharSequence) strOptString2).toString();
                    if (StringsKt.isBlank(title) || extract.length() < 40) {
                        triple = null;
                    } else if (sameTitle(title, excludeTitle)) {
                        q = q;
                        triple = null;
                        destination$iv$iv = destination$iv$iv;
                    } else {
                        q = q;
                        destination$iv$iv = destination$iv$iv;
                        int score = scorePage(title, extract, search, topicFold, preferAspect);
                        triple = new Triple(Integer.valueOf(score), title, extract);
                    }
                }
                if (triple != null) {
                    destination$iv$iv.add(triple);
                }
                destination$iv$iv = destination$iv$iv;
                q = q;
                triple2 = null;
            }
            Iterable $this$sortedByDescending$iv = (List) destination$iv$iv;
            List scored = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.tools.KnowledgeTool$wikipediaSearchAnswer$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Triple it = (Triple) t2;
                    Integer num = (Integer) it.getFirst();
                    Triple it2 = (Triple) t;
                    return ComparisonsKt.compareValues(num, (Integer) it2.getFirst());
                }
            });
            Triple best = (Triple) CollectionsKt.firstOrNull(scored);
            if (best == null || ((Number) best.getFirst()).intValue() < 1) {
                return null;
            }
            String text = sanitizeForSpeech$tools_debug(formatWikiExtract$tools_debug((String) best.getSecond(), (String) best.getThird()));
            return TuplesKt.to(best.getSecond(), text);
        }
        return null;
    }

    private final boolean containsWord(String hay, String needle) {
        if (StringsKt.isBlank(needle) || StringsKt.isBlank(hay)) {
            return false;
        }
        return new Regex("(?<![а-яёa-z0-9])" + Regex.INSTANCE.escape(needle) + "(?![а-яёa-z0-9])", RegexOption.IGNORE_CASE).containsMatchIn(hay);
    }

    private final int scorePage(String title, String extract, String search, String topicFold, boolean preferAspect) {
        boolean titleHas;
        boolean extractHas;
        String t = fold(title);
        String e = fold(extract);
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) fold(search), new char[]{' '}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (it.length() > 2) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List<String> words = (List) destination$iv$iv;
        int score = 0;
        for (String w : words) {
            if (containsWord(t, w)) {
                score += 3;
            }
            if (containsWord(e, w)) {
                score++;
            }
        }
        if (!StringsKt.isBlank(topicFold)) {
            Iterable $this$filter$iv2 = StringsKt.split$default((CharSequence) topicFold, new char[]{' '}, false, 0, 6, (Object) null);
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                String it2 = (String) element$iv$iv2;
                if (it2.length() > 2) {
                    destination$iv$iv2.add(element$iv$iv2);
                }
            }
            Iterable topicParts = (List) destination$iv$iv2;
            Iterable $this$any$iv = topicParts;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it3 = $this$any$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        titleHas = false;
                        break;
                    }
                    Object element$iv = it3.next();
                    String it4 = (String) element$iv;
                    if (containsWord(t, it4)) {
                        titleHas = true;
                        break;
                    }
                }
            } else {
                titleHas = false;
            }
            Iterable $this$any$iv2 = topicParts;
            if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                Iterator it5 = $this$any$iv2.iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        extractHas = false;
                        break;
                    }
                    Object element$iv2 = it5.next();
                    String it6 = (String) element$iv2;
                    if (containsWord(e, it6)) {
                        extractHas = true;
                        break;
                    }
                }
            } else {
                extractHas = false;
            }
            if (titleHas) {
                score += 6;
            }
            if (extractHas) {
                score += 3;
            }
            if (!titleHas && !extractHas) {
                score -= 12;
            }
        }
        if (preferAspect) {
            for (String hint : CollectionsKt.listOf((Object[]) new String[]{"интеллект", "умн", "мозг", "когнитив", "поведен", "питан", "ареал", "родил", "рождени", "москв", "биограф", "китообразн"})) {
                if (StringsKt.contains$default((CharSequence) t, (CharSequence) hint, false, 2, (Object) null)) {
                    score += 5;
                }
                if (StringsKt.contains$default((CharSequence) e, (CharSequence) hint, false, 2, (Object) null)) {
                    score += 2;
                }
            }
        }
        return score;
    }

    private final Pair<String, String> fetchSummary(String title) throws UnsupportedEncodingException {
        String enc = URLEncoder.encode(StringsKt.replace$default(title, ' ', '_', false, 4, (Object) null), Charsets.UTF_8.name());
        String summaryUrl = "https://ru.wikipedia.org/api/rest_v1/page/summary/" + enc;
        JSONObject json = SimpleHttp.INSTANCE.getJsonOrNull(summaryUrl, 3600000L);
        if (json == null) {
            return null;
        }
        String strOptString = json.optString("extract");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String extract = StringsKt.trim((CharSequence) strOptString).toString();
        if (StringsKt.isBlank(extract)) {
            return null;
        }
        String strOptString2 = json.optString("title");
        if (StringsKt.isBlank(strOptString2)) {
            strOptString2 = title;
        }
        String resolved = strOptString2;
        Intrinsics.checkNotNull(resolved);
        String text = sanitizeForSpeech$tools_debug(formatWikiExtract$tools_debug(resolved, extract));
        return TuplesKt.to(resolved, text);
    }

    private final Pair<String, String> localFact(String query, String topic) {
        List $this$localFact_u24lambda_u240 = CollectionsKt.createListBuilder();
        $this$localFact_u24lambda_u240.add(canonicalizeTopic(query));
        $this$localFact_u24lambda_u240.add(canonicalizeTopic(topic));
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String stripped = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(lowerCase, (CharSequence) "расскажи коротко про "), (CharSequence) "расскажи кратко про "), (CharSequence) "расскажи про "), (CharSequence) "про ")).toString();
        $this$localFact_u24lambda_u240.add(canonicalizeTopic(stripped));
        if (!StringsKt.isBlank(topic)) {
            $this$localFact_u24lambda_u240.add(canonicalizeTopic(topic));
        }
        Iterable $this$map$iv = CollectionsKt.build($this$localFact_u24lambda_u240);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(fold((String) item$iv$iv));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (!StringsKt.isBlank((String) element$iv$iv)) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List<String> keys = CollectionsKt.distinct((List) destination$iv$iv2);
        String aspect = extractAspect(query);
        for (String k : keys) {
            String canonKey = LEMMA_TOPICS.get(k);
            if (canonKey == null) {
                if (k.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    char it = k.charAt(0);
                    StringBuilder sbAppend = sb.append((Object) (Character.isLowerCase(it) ? CharsKt.titlecase(it, new Locale("ru")) : String.valueOf(it)));
                    String strSubstring = k.substring(1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    canonKey = sbAppend.append(strSubstring).toString();
                } else {
                    canonKey = k;
                }
            }
            String title = LEMMA_TOPICS.get(fold(canonKey));
            if (title == null) {
                title = canonKey;
            }
            if (!StringsKt.isBlank(aspect)) {
                String it2 = LOCAL_ASPECTS.get(fold(title) + "|" + aspect);
                if (it2 != null) {
                    return TuplesKt.to(title, it2);
                }
                String it3 = LOCAL_ASPECTS.get(k + "|" + aspect);
                if (it3 != null) {
                    return TuplesKt.to(title, it3);
                }
            }
            String it4 = LOCAL_FACTS.get(fold(title));
            if (it4 != null) {
                return TuplesKt.to(title, it4);
            }
            String it5 = LOCAL_FACTS.get(k);
            if (it5 != null) {
                return TuplesKt.to(title, it5);
            }
        }
        if (StringsKt.isBlank(topic) || StringsKt.isBlank(aspect)) {
            return null;
        }
        String tCanon = canonicalizeTopic(topic);
        String it6 = LOCAL_ASPECTS.get(fold(tCanon) + "|" + aspect);
        if (it6 != null) {
            return TuplesKt.to(tCanon, it6);
        }
        return null;
    }

    public final String formatWikiExtract$tools_debug(String title, String extract) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(extract, "extract");
        String e = StringsKt.trim((CharSequence) StringsKt.replace$default(extract, Typography.nbsp, ' ', false, 4, (Object) null)).toString();
        if (StringsKt.isBlank(e)) {
            return title;
        }
        if (StringsKt.contains$default((CharSequence) e, Typography.mdash, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) e, Typography.ndash, false, 2, (Object) null)) {
            return e;
        }
        String tFold = fold(title);
        String eFold = fold(e);
        if (!StringsKt.isBlank(tFold) && StringsKt.startsWith$default(eFold, tFold, false, 2, (Object) null)) {
            return e;
        }
        String shortTitle = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(title, ',', (String) null, 2, (Object) null)).toString();
        if (!StringsKt.isBlank(shortTitle) && StringsKt.startsWith$default(eFold, fold(shortTitle), false, 2, (Object) null)) {
            return e;
        }
        String afterComma = StringsKt.trim((CharSequence) StringsKt.substringAfter(title, ',', "")).toString();
        return (StringsKt.isBlank(afterComma) || !StringsKt.startsWith$default(eFold, fold(afterComma), false, 2, (Object) null)) ? title + " — " + e : e;
    }

    private final boolean sameTitle(String a, String b) {
        if (StringsKt.isBlank(a) || StringsKt.isBlank(b)) {
            return false;
        }
        return Intrinsics.areEqual(fold(a), fold(b));
    }

    private final String fold(String s) {
        String lowerCase = s.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(lowerCase, Typography.nbsp, ' ', false, 4, (Object) null), "́", "", false, 4, (Object) null), "́", "", false, 4, (Object) null), StringUtils.SPACE)).toString();
    }

    private final String duckDuckGo(String query) throws UnsupportedEncodingException {
        String q = URLEncoder.encode(query, Charsets.UTF_8.name());
        String url = "https://api.duckduckgo.com/?q=" + q + "&format=json&no_html=1&skip_disambig=1";
        JSONObject json = SimpleHttp.INSTANCE.getJsonOrNull(url, 3600000L);
        if (json == null) {
            return null;
        }
        String strOptString = json.optString("AbstractText");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String string = StringsKt.trim((CharSequence) strOptString).toString();
        if (!StringsKt.isBlank(string)) {
            return string;
        }
        String strOptString2 = json.optString("Answer");
        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
        String answer = StringsKt.trim((CharSequence) strOptString2).toString();
        if (StringsKt.isBlank(answer)) {
            return null;
        }
        return answer;
    }

    public final String sanitizeForSpeech$tools_debug(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String t = StringsKt.replace$default(StringsKt.replace$default(StringsKt.trim((CharSequence) new Regex("\\s+").replace(new Regex("[#*_`>|]+").replace(new Regex("\\(\\s*источник[^)]*\\)", RegexOption.IGNORE_CASE).replace(new Regex("\\[\\d+]").replace(new Regex("www\\.\\S+").replace(new Regex("https?://\\S+").replace(new Regex("\\[([^\\]]+)]\\([^)]*\\)").replace(new Regex("!\\[[^\\]]*]\\([^)]*\\)").replace(StringsKt.replace$default(raw, Typography.nbsp, ' ', false, 4, (Object) null), StringUtils.SPACE), "$1"), StringUtils.SPACE), StringUtils.SPACE), StringUtils.SPACE), StringUtils.SPACE), StringUtils.SPACE), StringUtils.SPACE)).toString(), "́", "", false, 4, (Object) null), "́", "", false, 4, (Object) null);
        if (t.length() <= MAX_SPEECH_CHARS) {
            return t;
        }
        String window = StringsKt.take(t, MAX_SPEECH_CHARS);
        int sentenceEnd = StringsKt.lastIndexOfAny$default((CharSequence) window, new char[]{'.', '!', '?', Typography.ellipsis}, 0, false, 6, (Object) null);
        if (sentenceEnd >= 80) {
            return StringsKt.trim((CharSequence) StringsKt.take(window, sentenceEnd + 1)).toString();
        }
        int space = StringsKt.lastIndexOf$default((CharSequence) window, ' ', 0, false, 6, (Object) null);
        if (space >= 80) {
            return StringsKt.trimEnd(StringsKt.take(window, space), ',', ';', ':') + "…";
        }
        return StringsKt.trimEnd((CharSequence) window).toString() + "…";
    }
}
