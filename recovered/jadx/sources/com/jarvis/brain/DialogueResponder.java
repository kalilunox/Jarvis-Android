package com.jarvis.brain;

import android.content.Context;
import com.jarvis.core.llm.AssembledLlmContext;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: DialogueResponder.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ(\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0005H\u0016J4\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0002J(\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J(\u0010\u0019\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/jarvis/brain/DialogueResponder;", "", "context", "Landroid/content/Context;", "persona", "", "chatLlm", "Lkotlin/Function0;", "Lcom/jarvis/brain/ChatLlmEngine;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "respond", "userText", "history", "", "memories", "respondDetailed", "Lcom/jarvis/brain/DialogueResult;", "assembled", "Lcom/jarvis/core/llm/AssembledLlmContext;", "pickJoke", "templateFallback", "t", "prevUser", "lastAssistant", "resolveWithContext", "shortFollowUp", "", "extractTopic", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class DialogueResponder {
    private static final String CAPABILITIES = "Управляю телефоном и приложениями, сэр. Музыка, звонки, сообщения, погода, напоминания, маршруты, новости, курсы валют, сводка на день. На вопросы тоже отвечаю.";
    private static final String TAG = "Jarvis.Dialogue";
    private final Function0<ChatLlmEngine> chatLlm;
    private final Context context;
    private final String persona;

    /* JADX WARN: Multi-variable type inference failed */
    public DialogueResponder(Context context, String persona, Function0<? extends ChatLlmEngine> function0) {
        Intrinsics.checkNotNullParameter(persona, "persona");
        this.context = context;
        this.persona = persona;
        this.chatLlm = function0;
    }

    public /* synthetic */ DialogueResponder(Context context, String str, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "CLASSIC" : str, (i & 4) != 0 ? null : function0);
    }

    public static /* synthetic */ String respond$default(DialogueResponder dialogueResponder, String str, List list, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respond");
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        return dialogueResponder.respond(str, list, str2);
    }

    public String respond(String userText, List<String> history, String memories) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        return respondDetailed$default(this, userText, history, memories, null, 8, null).getText();
    }

    public static /* synthetic */ DialogueResult respondDetailed$default(DialogueResponder dialogueResponder, String str, List list, String str2, AssembledLlmContext assembledLlmContext, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respondDetailed");
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        if ((i & 8) != 0) {
            assembledLlmContext = null;
        }
        return dialogueResponder.respondDetailed(str, list, str2, assembledLlmContext);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:161:0x058f  */
    /* JADX WARN: Code duplicated, block: B:195:0x0643  */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0613, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r14, "как тебя зовут") == false) goto L199;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v232 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46, types: [com.jarvis.brain.aiprovider.RoutedChatLlmEngine] */
    /* JADX WARN: Type inference failed for: r25v1 */
    /* JADX WARN: Type inference failed for: r25v10 */
    /* JADX WARN: Type inference failed for: r25v11 */
    /* JADX WARN: Type inference failed for: r25v2 */
    /* JADX WARN: Type inference failed for: r25v3, types: [com.jarvis.brain.ChatLlmEngine] */
    /* JADX WARN: Type inference failed for: r25v4 */
    /* JADX WARN: Type inference failed for: r25v5 */
    /* JADX WARN: Type inference failed for: r25v6 */
    /* JADX WARN: Type inference failed for: r25v7 */
    /* JADX WARN: Type inference failed for: r25v8 */
    /* JADX WARN: Type inference failed for: r25v9 */
    /* JADX WARN: Type inference failed for: r32v0 */
    /* JADX WARN: Type inference failed for: r32v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r32v2 */
    /* JADX WARN: Type inference failed for: r33v0 */
    /* JADX WARN: Type inference failed for: r33v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r33v2 */
    /* JADX WARN: Type inference failed for: r36v0 */
    /* JADX WARN: Type inference failed for: r36v1, types: [java.lang.Long] */
    /* JADX WARN: Type inference failed for: r36v2 */
    /* JADX WARN: Type inference failed for: r37v0 */
    /* JADX WARN: Type inference failed for: r37v1, types: [com.jarvis.core.runtime.TtftKind] */
    /* JADX WARN: Type inference failed for: r37v2 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.jarvis.brain.ChatLlmEngine] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.jarvis.brain.DialogueResult respondDetailed(java.lang.String r55, java.util.List<java.lang.String> r56, java.lang.String r57, com.jarvis.core.llm.AssembledLlmContext r58) {
        /*
            Method dump skipped, instruction units count: 2324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.brain.DialogueResponder.respondDetailed(java.lang.String, java.util.List, java.lang.String, com.jarvis.core.llm.AssembledLlmContext):com.jarvis.brain.DialogueResult");
    }

    private static final boolean respondDetailed$hasConversationalWrapper(String t) {
        Iterable wrappers = CollectionsKt.listOf((Object[]) new String[]{"как думаешь", "как тебе", "стоит ли", "и стоит", "мне кажется", "а что если", "интересно", "можешь ли"});
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

    private static final boolean respondDetailed$shortEnough(String t, int max) {
        return t.length() <= max && !respondDetailed$hasConversationalWrapper(t);
    }

    private static final boolean respondDetailed$has(String t, String... words) {
        for (String str : words) {
            if (StringsKt.contains$default((CharSequence) t, (CharSequence) str, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean respondDetailed$isShortFollowUp(String t) {
        if (t.length() > 56) {
            return false;
        }
        return SetsKt.setOf((Object[]) new String[]{"подробнее", "продолжи", "дальше", "ещё", "еще", "и что", "ну и", "а это", "про это", "об этом", "а там", "почему", "зачем", "как так"}).contains(t) || StringsKt.startsWith$default(t, "а ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "ну ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а что", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а как", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а почему", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а зачем", false, 2, (Object) null) || StringsKt.startsWith$default(t, "расскажи ещё", false, 2, (Object) null) || StringsKt.startsWith$default(t, "расскажи еще", false, 2, (Object) null);
    }

    private final String pickJoke() {
        List jokes = CollectionsKt.listOf((Object[]) new String[]{"Программист заходит в лифт, а там написано «этаж 1…16». Он выбирает 17 — «а вдруг там баг».", "Говорит Wi‑Fi роутеру: «Ты меня слышишь?» Тот: «Пакет потерян».", "Почему дельфин не пишет код? Потому что у него нет «берега» ошибок — только океан.", "Спросил у будильника: «Который час?» Он: «Пора вставать». Классика.", "Оптимист изобретает самолёт, пессимист — парашют, инженер — инструкцию на 400 страниц."});
        return (String) jokes.get((int) (System.nanoTime() % ((long) jokes.size())));
    }

    private static final boolean templateFallback$has(String $t, String... words) {
        for (String str : words) {
            if (StringsKt.contains$default((CharSequence) $t, (CharSequence) str, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x0090 A[ORIG_RETURN, RETURN] */
    private final String templateFallback(String t, String prevUser, String memories, String lastAssistant) {
        List<String> groupValues;
        String str;
        if (SetsKt.setOf((Object[]) new String[]{"это", "туда", "туда же", "сюда", "так же", "также"}).contains(t) && !StringsKt.isBlank(prevUser)) {
            return "Вы имеете в виду «" + prevUser + "», сэр? Уточните командой целиком.";
        }
        if (templateFallback$has(t, "ты кто", "кто ты", "что ты")) {
            switch (this.persona) {
                case "DEVELOPER":
                    return "Jarvis · локальный ASR/TTS · Brain.";
                case "FRIENDLY":
                    return "Привет! Я Джарвис, твой голосовой помощник.";
                case "MINIMAL":
                    return "Джарвис. Офлайн-ассистент.";
                default:
                    return "Джарвис, сэр. Ваш личный ассистент.";
            }
        }
        if (templateFallback$has(t, "что ты помнишь", "что помнишь", "моя память") && !StringsKt.isBlank(memories)) {
            return "Из памяти: " + StringsKt.take(memories, 280);
        }
        String string = null;
        if (!templateFallback$has(t, "как меня зовут", "мое имя", "моё имя") || StringsKt.isBlank(memories)) {
            if (templateFallback$has(t, "что ты умеешь", "что умеешь")) {
                return "Управляю устройством и могу поболтать, если скачана модель диалога (Настройки → Модели).";
            }
            return (SetsKt.setOf((Object[]) new String[]{"да", "нет", "ага", "угу", "давай"}).contains(t) && StringsKt.contains$default((CharSequence) lastAssistant, (CharSequence) "?", false, 2, (Object) null)) ? "Хорошо, сэр. Что сделать дальше?" : "Для свободного разговора скачай «Диалог · Qwen 2.5 1.5B» в Настройки → Модели. Команды вроде «включи фонарик» работают и без неё.";
        }
        MatchResult matchResultFind$default = Regex.find$default(new Regex("(?i)(?:зовут|имя)[:\\s]+([^\\n,;.]+)"), memories, 0, 2, null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = groupValues.get(1)) != null) {
            string = StringsKt.trim((CharSequence) str).toString();
        }
        String nameHint = string;
        String str2 = nameHint;
        return !(str2 == null || StringsKt.isBlank(str2)) ? "Вас зовут " + nameHint + ", сэр." : "В памяти есть: " + StringsKt.take(memories, 200);
    }

    private final String resolveWithContext(String userText, String prevUser, String lastAssistant, boolean shortFollowUp) {
        if (StringsKt.isBlank(prevUser) && !shortFollowUp) {
            return userText;
        }
        String strExtractTopic = extractTopic(prevUser);
        if (StringsKt.isBlank(strExtractTopic)) {
            strExtractTopic = extractTopic(lastAssistant);
        }
        String topic = strExtractTopic;
        String s = StringsKt.trim((CharSequence) userText).toString();
        if (!StringsKt.isBlank(topic)) {
            s = new Regex("\\bнее\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bнеё\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bнего\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bэто\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bим\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bих\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bони\\b", RegexOption.IGNORE_CASE).replace(s, topic), topic), topic), topic), topic), topic), topic);
        }
        String s2 = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(s, (CharSequence) "а "), (CharSequence) "А ")).toString();
        if (shortFollowUp && !StringsKt.isBlank(topic) && !StringsKt.contains((CharSequence) s2, (CharSequence) topic, true)) {
            return "Вопрос про «" + topic + "»: " + s2 + ". Ответь по этой теме, коротко.";
        }
        if (shortFollowUp && !StringsKt.isBlank(prevUser)) {
            return "Продолжение темы «" + StringsKt.take(prevUser, 60) + "»: " + s2;
        }
        String str = s2;
        if (StringsKt.isBlank(str)) {
            str = userText;
        }
        return str;
    }

    private final String extractTopic(String prevUser) {
        if (StringsKt.isBlank(prevUser)) {
            return "";
        }
        String lowerCase = prevUser.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String s = StringsKt.trim((CharSequence) lowerCase).toString();
        List<String> prefixes = CollectionsKt.listOf((Object[]) new String[]{"расскажи коротко про ", "расскажи про ", "расскажи о ", "что такое ", "кто такие ", "кто такой ", "кто такая ", "кто это ", "почему ", "зачем ", "что думаешь про ", "что думаешь о ", "загугли "});
        for (String p : prefixes) {
            if (StringsKt.startsWith$default(s, p, false, 2, (Object) null)) {
                s = StringsKt.trim((CharSequence) StringsKt.removePrefix(s, (CharSequence) p)).toString();
                break;
            }
        }
        return StringsKt.take(StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.take(new Regex("\\s+").split(s, 0), 3), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString(), 40);
    }
}
