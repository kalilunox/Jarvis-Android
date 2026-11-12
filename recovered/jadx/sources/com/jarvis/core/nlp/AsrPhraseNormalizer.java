package com.jarvis.core.nlp;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: AsrPhraseNormalizer.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0016\u0010\u000e\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0002J\u001e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0002R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001c\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001d0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/jarvis/core/nlp/AsrPhraseNormalizer;", "", "<init>", "()V", "normalize", "", VoiceService.EXTRA_TEXT, "isIncompleteCommand", "", "isTruncatedMessengerWrite", "content", "", "isMessengerToken", "w", "isMusicFrameWithoutTitle", "BARE_COMMAND_VERBS", "", "getBARE_COMMAND_VERBS", "()Ljava/util/Set;", "WRITE_VERBS", "DANGLING_PREPS", "SMS_MARKERS", "MESSAGE_STARTERS", "rebuildCasing", "replaced", "originalTokens", "FILLER_WORDS", "MUSIC_FRAME_WORDS", "PHRASE_LATIN", "Lkotlin/Pair;", "LATIN_BY_CYRILLIC", "", "TOKEN_REGEX", "Lkotlin/text/Regex;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AsrPhraseNormalizer {
    private static final Map<String, String> LATIN_BY_CYRILLIC;
    private static final List<Pair<String, String>> PHRASE_LATIN;
    private static final Regex TOKEN_REGEX;
    public static final AsrPhraseNormalizer INSTANCE = new AsrPhraseNormalizer();
    private static final Set<String> BARE_COMMAND_VERBS = SetsKt.setOf((Object[]) new String[]{"включи", "включить", "включите", "выключи", "выключить", "выключите", "открой", "открыть", "откройте", "закрой", "закрыть", "запусти", "запустить", "сделай", "сделайте", "поставь", "поставьте", "найди", "найдите", "покажи", "покажите", "напиши", "написать", "напечатай", "отправь", "передай"});
    private static final Set<String> WRITE_VERBS = SetsKt.setOf((Object[]) new String[]{"напиши", "написать", "напечатай", "отправь", "передай", "отправить"});
    private static final Set<String> DANGLING_PREPS = SetsKt.setOf((Object[]) new String[]{"в", "во", "на", "к", "ко", "с", "со", "для", "про", "и", "или", "а"});
    private static final Set<String> SMS_MARKERS = SetsKt.setOf((Object[]) new String[]{"смс", "sms", "сообщение", "сообщением"});
    private static final Set<String> MESSAGE_STARTERS = SetsKt.setOf((Object[]) new String[]{"привет", "пока", "здравствуй", "здравствуйте", "добрый", "добрая", "спасибо", "пожалуйста", "ок", "хорошо", "ладно", "я", "мы", "ты", "вы", "он", "она", "они", "сейчас", "скоро", "уже", "ещё", "еще", "сегодня", "завтра", "куплю", "буду", "выезжаю", "приеду", "иду", "еду"});
    private static final Set<String> FILLER_WORDS = SetsKt.setOf((Object[]) new String[]{"пожалуйста", "плиз", "please", "ну", "давай", "просто", "мне", "мне-ка"});
    private static final List<String> MUSIC_FRAME_WORDS = CollectionsKt.listOf((Object[]) new String[]{"песн", "песен", "трек", "музык", "альбом", "исполнит", "артист", "композиц"});

    private AsrPhraseNormalizer() {
    }

    public final String normalize(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (StringsKt.isBlank(text)) {
            return text;
        }
        List<String> list = SequencesKt.toList(SequencesKt.map(Regex.findAll$default(TOKEN_REGEX, text, 0, 2, null), new Function1() { // from class: com.jarvis.core.nlp.AsrPhraseNormalizer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AsrPhraseNormalizer.normalize$lambda$0((MatchResult) obj);
            }
        }));
        if (list.isEmpty()) {
            return StringsKt.trim((CharSequence) text).toString();
        }
        String joined = CollectionsKt.joinToString$default(list, StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.core.nlp.AsrPhraseNormalizer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AsrPhraseNormalizer.normalize$lambda$1((String) obj);
            }
        }, 30, null);
        for (Pair<String, String> pair : PHRASE_LATIN) {
            String cyr = pair.component1();
            String latin = pair.component2();
            if (StringsKt.contains$default((CharSequence) joined, (CharSequence) cyr, false, 2, (Object) null)) {
                String replaced = StringsKt.replace$default(joined, cyr, latin, false, 4, (Object) null);
                return rebuildCasing(replaced, list);
            }
        }
        List<String> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String token = (String) item$iv$iv;
            String lower = token.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
            String str = LATIN_BY_CYRILLIC.get(lower);
            if (str == null) {
                String str2 = LATIN_BY_CYRILLIC.get(Transliterator.INSTANCE.normalize(lower));
                if (str2 != null) {
                    token = str2;
                }
            } else {
                token = str;
            }
            destination$iv$iv.add(token);
        }
        List rebuilt = (List) destination$iv$iv;
        return CollectionsKt.joinToString$default(rebuilt, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String normalize$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence normalize$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String lowerCase = it.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String isIncompleteCommand$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getValue();
    }

    public final boolean isIncompleteCommand(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        Regex regex = TOKEN_REGEX;
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        List words = SequencesKt.toList(SequencesKt.map(Regex.findAll$default(regex, lowerCase, 0, 2, null), new Function1() { // from class: com.jarvis.core.nlp.AsrPhraseNormalizer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AsrPhraseNormalizer.isIncompleteCommand$lambda$0((MatchResult) obj);
            }
        }));
        if (words.isEmpty()) {
            return true;
        }
        if (words.size() == 1) {
            return BARE_COMMAND_VERBS.contains(words.get(0)) || WRITE_VERBS.contains(words.get(0));
        }
        List $this$filterNot$iv = words;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it = (String) element$iv$iv;
            if (!FILLER_WORDS.contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List content = (List) destination$iv$iv;
        if (content.isEmpty()) {
            return true;
        }
        return (content.size() == 1 && (BARE_COMMAND_VERBS.contains(content.get(0)) || WRITE_VERBS.contains(content.get(0)))) || DANGLING_PREPS.contains(CollectionsKt.last(content)) || isMusicFrameWithoutTitle(content) || isTruncatedMessengerWrite(content);
    }

    private final boolean isTruncatedMessengerWrite(List<String> content) {
        boolean z;
        boolean z2;
        int size;
        int index$iv = 0;
        Iterator<String> it = content.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                if (WRITE_VERBS.contains((String) item$iv)) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int verbIdx = index$iv;
        if (verbIdx < 0) {
            return false;
        }
        List rest = CollectionsKt.drop(content, verbIdx + 1);
        if (rest.isEmpty()) {
            return true;
        }
        List $this$any$iv = rest;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            z = false;
        } else {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv = it2.next();
                    String it3 = (String) element$iv;
                    if (INSTANCE.isMessengerToken(it3) || SMS_MARKERS.contains(it3)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return false;
        }
        List $this$any$iv2 = rest;
        if (($this$any$iv2 instanceof Collection) && $this$any$iv2.isEmpty()) {
            z2 = false;
        } else {
            Iterator it4 = $this$any$iv2.iterator();
            while (true) {
                if (it4.hasNext()) {
                    Object element$iv2 = it4.next();
                    if (MESSAGE_STARTERS.contains((String) element$iv2)) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
        }
        return !z2 && 2 <= (size = rest.size()) && size < 5;
    }

    private final boolean isMessengerToken(String w) {
        return Intrinsics.areEqual(w, "telegram") || Intrinsics.areEqual(w, "тг") || StringsKt.startsWith$default(w, "телеграм", false, 2, (Object) null) || StringsKt.startsWith$default(w, "whatsapp", false, 2, (Object) null) || StringsKt.startsWith$default(w, "ватсап", false, 2, (Object) null) || StringsKt.startsWith$default(w, "вотсап", false, 2, (Object) null);
    }

    private final boolean isMusicFrameWithoutTitle(List<String> content) {
        boolean z;
        boolean z2;
        int index$iv = 0;
        Iterator<String> it = content.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                String w = (String) item$iv;
                Iterable $this$any$iv = MUSIC_FRAME_WORDS;
                if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                    Iterator it2 = $this$any$iv.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Object element$iv = it2.next();
                            String m = (String) element$iv;
                            if (Intrinsics.areEqual(w, m) || StringsKt.startsWith$default(w, m, false, 2, (Object) null)) {
                                z2 = true;
                                break;
                            }
                        } else {
                            z2 = false;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                }
                if (z2) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int markerIdx = index$iv;
        if (markerIdx < 0 || StringsKt.startsWith$default(content.get(markerIdx), "музык", false, 2, (Object) null)) {
            return false;
        }
        List<String> $this$any$iv2 = content;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (BARE_COMMAND_VERBS.contains(it4)) {
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
        boolean hasVerb = z;
        if (hasVerb) {
            return CollectionsKt.drop(content, markerIdx + 1).isEmpty();
        }
        return false;
    }

    static {
        Iterable $this$sortedByDescending$iv = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("энди ворхол", "andy warhol"), TuplesKt.to("энди вархол", "andy warhol"), TuplesKt.to("энди уорхол", "andy warhol"), TuplesKt.to("богемиан рэпсоди", "bohemian rhapsody"), TuplesKt.to("богемиан рапсоди", "bohemian rhapsody"), TuplesKt.to("богемиан рhapsody", "bohemian rhapsody"), TuplesKt.to("стейт оф грайс", "state of grace"), TuplesKt.to("шейп оф ю", "shape of you"), TuplesKt.to("блейдинг лав", "blinding lights"), TuplesKt.to("блайндинг лайтс", "blinding lights")});
        PHRASE_LATIN = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.nlp.AsrPhraseNormalizer$special$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Pair it = (Pair) t2;
                Pair it2 = (Pair) t;
                return ComparisonsKt.compareValues(Integer.valueOf(((String) it.getFirst()).length()), Integer.valueOf(((String) it2.getFirst()).length()));
            }
        });
        LATIN_BY_CYRILLIC = MapsKt.mapOf(TuplesKt.to("фазер", "father"), TuplesKt.to("фатер", "father"), TuplesKt.to("фазэр", "father"), TuplesKt.to("фадр", "father"), TuplesKt.to("фазе", "father"), TuplesKt.to("ворхол", "warhol"), TuplesKt.to("вархол", "warhol"), TuplesKt.to("уорхол", "warhol"), TuplesKt.to("энди", "andy"), TuplesKt.to("эндй", "andy"), TuplesKt.to("богемиан", "bohemian"), TuplesKt.to("богемьян", "bohemian"), TuplesKt.to("рэпсоди", "rhapsody"), TuplesKt.to("рапсоди", "rhapsody"), TuplesKt.to("инси", "incy"), TuplesKt.to("инци", "incy"), TuplesKt.to("инсиу", "incy"), TuplesKt.to("спотифай", "spotify"), TuplesKt.to("спотифаи", "spotify"), TuplesKt.to("ютуб", "youtube"), TuplesKt.to("ютьюб", "youtube"), TuplesKt.to("нетфликс", "netflix"), TuplesKt.to("дрейк", "drake"), TuplesKt.to("дэйк", "drake"), TuplesKt.to("уикенд", "weeknd"), TuplesKt.to("викнд", "weeknd"), TuplesKt.to("тейлор", "taylor"), TuplesKt.to("свифт", "swift"), TuplesKt.to("бибер", "bieber"), TuplesKt.to("эминем", "eminem"), TuplesKt.to("эминэм", "eminem"), TuplesKt.to("телеграм", "telegram"), TuplesKt.to("телеграмм", "telegram"), TuplesKt.to("телеграме", "telegram"), TuplesKt.to("телеграмме", "telegram"), TuplesKt.to("телеграму", "telegram"), TuplesKt.to("телеграмму", "telegram"), TuplesKt.to("телеграма", "telegram"), TuplesKt.to("телеграмма", "telegram"), TuplesKt.to("вотсап", "whatsapp"), TuplesKt.to("ватсап", "whatsapp"), TuplesKt.to("инстаграм", "instagram"), TuplesKt.to("инстаграмм", "instagram"), TuplesKt.to("хром", "chrome"), TuplesKt.to("гугл", "google"), TuplesKt.to("джимейл", "gmail"), TuplesKt.to("твиттер", "twitter"), TuplesKt.to("твитер", "twitter"), TuplesKt.to("дискорд", "discord"), TuplesKt.to("тиктоке", "tiktok"), TuplesKt.to("тикток", "tiktok"));
        TOKEN_REGEX = new Regex("[\\p{L}\\p{N}]+");
    }

    public final Set<String> getBARE_COMMAND_VERBS() {
        return BARE_COMMAND_VERBS;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0082  */
    private final String rebuildCasing(String replaced, List<String> originalTokens) {
        List<String> list;
        Iterable $this$mapIndexed$iv;
        boolean z;
        String str;
        boolean z2;
        int i = 0;
        List<String> listSplit = new Regex("\\s+").split(replaced, 0);
        if (listSplit.size() != originalTokens.size()) {
            return replaced;
        }
        List<String> $this$mapIndexed$iv2 = listSplit;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv2, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv2) {
            int index$iv$iv2 = index$iv$iv + 1;
            if (index$iv$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String t = (String) item$iv$iv;
            String $this$all$iv = t;
            int i2 = i;
            while (true) {
                if (i2 < $this$all$iv.length()) {
                    char element$iv = $this$all$iv.charAt(i2);
                    list = listSplit;
                    $this$mapIndexed$iv = $this$mapIndexed$iv2;
                    if ('a' <= element$iv && element$iv < '{') {
                        z2 = true;
                    } else if ('0' <= element$iv && element$iv < ':') {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        z = false;
                        break;
                    }
                    i2++;
                    listSplit = list;
                    $this$mapIndexed$iv2 = $this$mapIndexed$iv;
                } else {
                    list = listSplit;
                    $this$mapIndexed$iv = $this$mapIndexed$iv2;
                    z = true;
                    break;
                }
            }
            if (z) {
                str = t;
            } else {
                str = index$iv$iv >= 0 && index$iv$iv < originalTokens.size() ? originalTokens.get(index$iv$iv) : t;
            }
            destination$iv$iv.add(str);
            index$iv$iv = index$iv$iv2;
            listSplit = list;
            $this$mapIndexed$iv2 = $this$mapIndexed$iv;
            i = 0;
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }
}
