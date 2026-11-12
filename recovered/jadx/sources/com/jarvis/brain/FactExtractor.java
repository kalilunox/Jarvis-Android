package com.jarvis.brain;

import com.jarvis.app.VoiceService;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.privacy.SensitivityClassifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;

/* JADX INFO: compiled from: FactExtractor.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u0018\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/jarvis/brain/FactExtractor;", "", "<init>", "()V", "REFUSAL", "", "factMarkers", "", "possessiveRegex", "Lkotlin/text/Regex;", "leadInRegex", "questionWords", "", "secretWordRegex", "templates", "Lcom/jarvis/brain/FactExtractor$Template;", "decide", "Lcom/jarvis/brain/FactExtractor$Decision;", "requestText", "extract", "isSecretPhrase", "", VoiceService.EXTRA_TEXT, "isQuestionForm", "raw", "body", "applyTemplate", "capitalize", "Decision", "Template", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FactExtractor {
    public static final String REFUSAL = "Такое я не запоминаю.";
    public static final FactExtractor INSTANCE = new FactExtractor();
    private static final List<String> factMarkers = CollectionsKt.listOf((Object[]) new String[]{"люблю", "предпочитаю", "ненавижу", "не люблю", "мне нравится", "у меня", "я обычно", "обычно я", "мне не нравится", "я встаю в", "я ложусь в", "день рождения", "родился", "живу в", "работаю", "учусь в"});
    private static final Regex possessiveRegex = new Regex("(?<![а-яё])(?:мой|моя|моё|мое|мои)(?![а-яё])", RegexOption.IGNORE_CASE);
    private static final Regex leadInRegex = new Regex("^(?:запомни|запиши|не\\s+забудь|имей\\s+в\\s+виду|remember)(?:\\s*,?\\s*(?:что|о\\s+том,?\\s*что|that))?[\\s,:;.!—–-]*", RegexOption.IGNORE_CASE);
    private static final Set<String> questionWords = SetsKt.setOf((Object[]) new String[]{"что", "кто", "где", "когда", "почему", "зачем", "как", "какой", "какая", "какое", "какие", "какого", "какому", "каким", "каком", "какую", "каких", "чей", "чья", "чьё", "чье", "чьи", "сколько", "куда", "откуда", "ли", "ль", "what", "who", "whom", "whose", "where", "when", "why", "how", "which", "is", "are", "am", "was", "were", "do", "does", "did", "can", "could", "will", "would", "should", "shall", "may", "might", "have", "has", "had"});
    private static final Regex secretWordRegex = new Regex("(?<![а-яёa-z0-9])(?:парол[а-яё]*|код[а-яё]*|пин[а-яё]*|cvv|cvc|ключ[а-яё]*|токен[а-яё]*|secret|password|passcode|otp|pin|token|api[- ]?key)(?![а-яёa-z0-9])", RegexOption.IGNORE_CASE);
    private static final List<Template> templates = CollectionsKt.listOf((Object[]) new Template[]{new Template(new Regex("^(?:я\\s+)?не\\s+люблю\\s+(.+)$", RegexOption.IGNORE_CASE), new Function1() { // from class: com.jarvis.brain.FactExtractor$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return FactExtractor.templates$lambda$0((String) obj);
        }
    }), new Template(new Regex("^(?:я\\s+)?люблю\\s+(.+)$", RegexOption.IGNORE_CASE), new Function1() { // from class: com.jarvis.brain.FactExtractor$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return FactExtractor.templates$lambda$1((String) obj);
        }
    }), new Template(new Regex("^(?:я\\s+)?предпочитаю\\s+(.+)$", RegexOption.IGNORE_CASE), new Function1() { // from class: com.jarvis.brain.FactExtractor$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return FactExtractor.templates$lambda$2((String) obj);
        }
    }), new Template(new Regex("^(?:я\\s+)?живу\\s+в\\s+(.+)$", RegexOption.IGNORE_CASE), new Function1() { // from class: com.jarvis.brain.FactExtractor$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return FactExtractor.templates$lambda$3((String) obj);
        }
    }), new Template(new Regex("^(?:я\\s+)?работаю\\s+в\\s+(.+)$", RegexOption.IGNORE_CASE), new Function1() { // from class: com.jarvis.brain.FactExtractor$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return FactExtractor.templates$lambda$4((String) obj);
        }
    }), new Template(new Regex("^(?:я\\s+)?работаю\\s+(.+)$", RegexOption.IGNORE_CASE), new Function1() { // from class: com.jarvis.brain.FactExtractor$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return FactExtractor.templates$lambda$5((String) obj);
        }
    }), new Template(new Regex("^меня\\s+зовут\\s+(.+)$", RegexOption.IGNORE_CASE), new Function1() { // from class: com.jarvis.brain.FactExtractor$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return FactExtractor.templates$lambda$6((String) obj);
        }
    })});

    private FactExtractor() {
    }

    /* JADX INFO: compiled from: FactExtractor.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/FactExtractor$Decision;", "", "Save", "Refused", "Lcom/jarvis/brain/FactExtractor$Decision$Refused;", "Lcom/jarvis/brain/FactExtractor$Decision$Save;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Decision {

        /* JADX INFO: compiled from: FactExtractor.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/brain/FactExtractor$Decision$Save;", "Lcom/jarvis/brain/FactExtractor$Decision;", VoiceService.EXTRA_TEXT, "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Save implements Decision {
            private final String text;

            public static /* synthetic */ Save copy$default(Save save, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = save.text;
                }
                return save.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getText() {
                return this.text;
            }

            public final Save copy(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                return new Save(text);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Save) && Intrinsics.areEqual(this.text, ((Save) other).text);
            }

            public int hashCode() {
                return this.text.hashCode();
            }

            public String toString() {
                return "Save(text=" + this.text + ")";
            }

            public Save(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                this.text = text;
            }

            public final String getText() {
                return this.text;
            }
        }

        /* JADX INFO: compiled from: FactExtractor.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/jarvis/brain/FactExtractor$Decision$Refused;", "Lcom/jarvis/brain/FactExtractor$Decision;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Refused implements Decision {
            public static final Refused INSTANCE = new Refused();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Refused)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return -424768984;
            }

            public String toString() {
                return "Refused";
            }

            private Refused() {
            }
        }
    }

    /* JADX INFO: compiled from: FactExtractor.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/jarvis/brain/FactExtractor$Template;", "", "regex", "Lkotlin/text/Regex;", "build", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/text/Regex;Lkotlin/jvm/functions/Function1;)V", "getRegex", "()Lkotlin/text/Regex;", "getBuild", "()Lkotlin/jvm/functions/Function1;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Template {
        private final Function1<String, String> build;
        private final Regex regex;

        /* JADX WARN: Multi-variable type inference failed */
        public Template(Regex regex, Function1<? super String, String> build) {
            Intrinsics.checkNotNullParameter(regex, "regex");
            Intrinsics.checkNotNullParameter(build, "build");
            this.regex = regex;
            this.build = build;
        }

        public final Function1<String, String> getBuild() {
            return this.build;
        }

        public final Regex getRegex() {
            return this.regex;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String templates$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "Пользователь не любит " + it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String templates$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "Пользователь любит " + it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String templates$lambda$2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "Пользователь предпочитает " + it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String templates$lambda$3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "Пользователь живёт в " + it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String templates$lambda$4(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "Пользователь работает в " + it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String templates$lambda$5(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "Пользователь работает " + it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String templates$lambda$6(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "Пользователя зовут " + it;
    }

    public final Decision decide(String requestText) {
        Object objSubstring;
        boolean z;
        Intrinsics.checkNotNullParameter(requestText, "requestText");
        String raw = StringsKt.trim((CharSequence) requestText).toString();
        boolean z2 = false;
        if (raw.length() == 0) {
            return null;
        }
        MatchResult lead = Regex.find$default(leadInRegex, raw, 0, 2, null);
        boolean hadLeadIn = lead != null;
        if (lead != null) {
            objSubstring = raw.substring(lead.getRange().getLast() + 1);
            Intrinsics.checkNotNullExpressionValue(objSubstring, "substring(...)");
        } else {
            objSubstring = raw;
        }
        Object body = objSubstring;
        String strTrim = StringsKt.trim(StringsKt.trim((CharSequence) body).toString(), ',', ':', ';', '.', '!', '?', Typography.mdash, Typography.ndash, Soundex.SILENT_MARKER, ' ');
        String lower = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        Iterable $this$any$iv = factMarkers;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) lower, (CharSequence) it2, false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        boolean hasMarker = z || possessiveRegex.containsMatchIn(lower);
        Iterable $this$any$iv2 = templates;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    Template it4 = (Template) element$iv2;
                    if (it4.getRegex().matches(strTrim)) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
        }
        boolean hasTemplate = z2;
        if (isQuestionForm(raw, strTrim)) {
            return null;
        }
        if ((!hadLeadIn && !hasMarker && !hasTemplate) || strTrim.length() < 3) {
            return null;
        }
        if (isSecretPhrase(strTrim) || isSecretPhrase(raw)) {
            return Decision.Refused.INSTANCE;
        }
        String text = capitalize(applyTemplate(strTrim));
        int length = text.length();
        if (3 <= length && length < 241) {
            return new Decision.Save(text);
        }
        return null;
    }

    public final String extract(String requestText) {
        Intrinsics.checkNotNullParameter(requestText, "requestText");
        Decision decisionDecide = decide(requestText);
        Decision.Save save = decisionDecide instanceof Decision.Save ? (Decision.Save) decisionDecide : null;
        if (save != null) {
            return save.getText();
        }
        return null;
    }

    public final boolean isSecretPhrase(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return secretWordRegex.containsMatchIn(text) || SensitivityClassifier.classify$default(SensitivityClassifier.INSTANCE, text, null, 2, null) == Sensitivity.SECRET;
    }

    public static /* synthetic */ boolean isQuestionForm$default(FactExtractor factExtractor, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return factExtractor.isQuestionForm(str, str2);
    }

    public final boolean isQuestionForm(String raw, String body) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(body, "body");
        boolean z = false;
        if (StringsKt.endsWith$default(StringsKt.trimEnd((CharSequence) raw).toString(), "?", false, 2, (Object) null)) {
            return true;
        }
        Iterable $this$map$iv = new Regex("\\s+").split(StringsKt.trim((CharSequence) body).toString(), 0);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String lowerCase = ((String) item$iv$iv).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            destination$iv$iv.add(StringsKt.trim(lowerCase, ',', ':', ';', '.', '!', '?', Typography.mdash, Typography.ndash, Soundex.SILENT_MARKER, 171, 187, Typography.quote, '\''));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (((String) element$iv$iv).length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List tokens = (List) destination$iv$iv2;
        String first = (String) CollectionsKt.firstOrNull(tokens);
        if (first == null) {
            return false;
        }
        List $this$any$iv = tokens;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            for (Object element$iv : $this$any$iv) {
                String it = (String) element$iv;
                if (Intrinsics.areEqual(it, "ли") || Intrinsics.areEqual(it, "ль")) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            return true;
        }
        return questionWords.contains(first);
    }

    private final String applyTemplate(String body) {
        for (Template t : templates) {
            MatchResult m = Regex.find$default(t.getRegex(), body, 0, 2, null);
            if (m != null) {
                String tail = StringsKt.trim((CharSequence) m.getGroupValues().get(1)).toString();
                if (!(tail.length() == 0)) {
                    return t.getBuild().invoke(tail);
                }
            }
        }
        return body;
    }

    private final String capitalize(String text) {
        if (text.length() == 0) {
            return text;
        }
        char upperCase = Character.toUpperCase(text.charAt(0));
        String strSubstring = text.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return upperCase + strSubstring;
    }
}
