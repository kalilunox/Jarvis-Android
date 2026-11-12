package com.jarvis.core.nlp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: RuEnTextMerger.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u001e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/nlp/RuEnTextMerger;", "", "<init>", "()V", "merge", "", "ruRaw", "enRaw", "cleanupEn", "raw", "takeThroughMarker", VoiceService.EXTRA_TEXT, "markers", "", "hasLikelyEnPhonetics", "", "ru", "MUSIC_MARKERS", "", "MUSIC_PREFIX_TOKENS", "OPEN_VERBS", "COMMANDISH", "EN_STOP", "PHONETIC_HINTS", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuEnTextMerger {
    public static final RuEnTextMerger INSTANCE = new RuEnTextMerger();
    private static final List<String> MUSIC_MARKERS = CollectionsKt.listOf((Object[]) new String[]{"песн", "трек", "музык", "альбом", "исполнит", "артист", "композиц"});
    private static final Set<String> MUSIC_PREFIX_TOKENS = SetsKt.setOf((Object[]) new String[]{"включи", "включить", "поставь", "поставьте", "запусти", "запустить", "песню", "песня", "песенку", "трек", "музыку", "музыка", "альбом", "альбома", "исполнителя", "исполнитель", "артиста", "артист", "композицию", "мне"});
    private static final Set<String> OPEN_VERBS = SetsKt.setOf((Object[]) new String[]{"открой", "открыть", "откройте", "запусти", "запустить"});
    private static final Set<String> COMMANDISH = SetsKt.plus(SetsKt.plus((Set) MUSIC_PREFIX_TOKENS, (Iterable) OPEN_VERBS), (Iterable) SetsKt.setOf((Object[]) new String[]{"выключи", "закрой", "сделай", "найди", "покажи", "джарвис"}));
    private static final Set<String> EN_STOP = SetsKt.setOf((Object[]) new String[]{"the", "a", "an", "and", "or", TypedValues.TransitionType.S_TO, "of", "in", "on", "for", "my", "me", "please", "play", "song", "music", AbstractCircuitBreaker.PROPERTY_NAME, "start", "turn", "on", "off"});
    private static final List<String> PHONETIC_HINTS = CollectionsKt.listOf((Object[]) new String[]{"фаз", "ворх", "уорх", "энд", "боге", "рэпс", "инс", "споти", "тейл", "дрейк"});

    private RuEnTextMerger() {
    }

    /* JADX WARN: Code duplicated, block: B:65:0x01a5 A[LOOP:2: B:53:0x015c->B:65:0x01a5, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:96:0x01a3 A[SYNTHETIC] */
    public final String merge(String ruRaw, String enRaw) {
        boolean z;
        boolean z2;
        Iterable $this$any$iv;
        int $i$f$any;
        Iterable $this$any$iv2;
        int $i$f$any2;
        boolean z3;
        Intrinsics.checkNotNullParameter(ruRaw, "ruRaw");
        Intrinsics.checkNotNullParameter(enRaw, "enRaw");
        String ru = AsrPhraseNormalizer.INSTANCE.normalize(StringsKt.trim((CharSequence) ruRaw).toString());
        String en = cleanupEn(enRaw);
        if (StringsKt.isBlank(ru)) {
            String string = en;
            if (StringsKt.isBlank(string)) {
                string = StringsKt.trim((CharSequence) ruRaw).toString();
            }
            return string;
        }
        if (StringsKt.isBlank(en)) {
            return ru;
        }
        String ruLower = ru.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(ruLower, "toLowerCase(...)");
        Iterable $this$filter$iv = new Regex("\\s+").split(en, 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (it.length() > 1 && !EN_STOP.contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List enWords = (List) destination$iv$iv;
        if (enWords.isEmpty()) {
            return ru;
        }
        Iterable $this$any$iv3 = MUSIC_MARKERS;
        Iterable iterable = null;
        int i = 2;
        if (($this$any$iv3 instanceof Collection) && ((Collection) $this$any$iv3).isEmpty()) {
            z = false;
        } else {
            Iterator it2 = $this$any$iv3.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it2.next();
                Iterable $this$any$iv4 = $this$any$iv3;
                String en2 = en;
                if (StringsKt.contains$default((CharSequence) ruLower, (CharSequence) element$iv, false, 2, (Object) null)) {
                    z = true;
                    break;
                }
                $this$any$iv3 = $this$any$iv4;
                en = en2;
            }
        }
        if (z) {
            String prefix = takeThroughMarker(ru, MUSIC_PREFIX_TOKENS);
            String enTitle = CollectionsKt.joinToString$default(enWords, StringUtils.SPACE, null, null, 0, null, null, 62, null);
            return !StringsKt.isBlank(prefix) ? StringsKt.trim((CharSequence) (prefix + StringUtils.SPACE + enTitle)).toString() : enTitle;
        }
        Iterable $this$any$iv5 = OPEN_VERBS;
        int $i$f$any3 = 0;
        if (($this$any$iv5 instanceof Collection) && ((Collection) $this$any$iv5).isEmpty()) {
            z2 = false;
        } else {
            Iterator it3 = $this$any$iv5.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z2 = false;
                    break;
                }
                Object element$iv2 = it3.next();
                String it4 = (String) element$iv2;
                if (StringsKt.startsWith$default(ruLower, it4, false, i, (Object) iterable)) {
                    $this$any$iv = $this$any$iv5;
                    $i$f$any = $i$f$any3;
                    $this$any$iv2 = iterable;
                    $i$f$any2 = i;
                } else {
                    $this$any$iv = $this$any$iv5;
                    $i$f$any = $i$f$any3;
                    $this$any$iv2 = null;
                    $i$f$any2 = 2;
                    if (!StringsKt.contains$default((CharSequence) ruLower, (CharSequence) (StringUtils.SPACE + it4 + StringUtils.SPACE), false, 2, (Object) null)) {
                        z3 = false;
                    }
                    if (z3) {
                        z2 = true;
                        break;
                    }
                    iterable = $this$any$iv2;
                    i = $i$f$any2;
                    $i$f$any3 = $i$f$any;
                    $this$any$iv5 = $this$any$iv;
                }
                z3 = true;
                if (z3) {
                    z2 = true;
                    break;
                }
                iterable = $this$any$iv2;
                i = $i$f$any2;
                $i$f$any3 = $i$f$any;
                $this$any$iv5 = $this$any$iv;
            }
        }
        if (z2) {
            String prefix2 = takeThroughMarker(ru, OPEN_VERBS);
            String enApp = CollectionsKt.joinToString$default(enWords, StringUtils.SPACE, null, null, 0, null, null, 62, null);
            return StringsKt.trim((CharSequence) (!StringsKt.isBlank(prefix2) ? new StringBuilder().append(prefix2) : new StringBuilder().append(ru)).append(StringUtils.SPACE).append(enApp).toString()).toString();
        }
        if (enWords.size() >= 1 && hasLikelyEnPhonetics(ruLower)) {
            Iterable $this$takeWhile$iv = new Regex("\\s+").split(ru, 0);
            ArrayList list$iv = new ArrayList();
            for (Object item$iv : $this$takeWhile$iv) {
                Set<String> set = COMMANDISH;
                String lowerCase = ((String) item$iv).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (!set.contains(lowerCase)) {
                    break;
                }
                list$iv.add(item$iv);
            }
            ArrayList verbs = list$iv;
            if (!verbs.isEmpty()) {
                return CollectionsKt.joinToString$default(CollectionsKt.plus((Collection) verbs, (Iterable) enWords), StringUtils.SPACE, null, null, 0, null, null, 62, null);
            }
        }
        return ru;
    }

    private final String cleanupEn(String raw) {
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(new Regex("[^a-z0-9\\s']").replace(lowerCase, StringUtils.SPACE), StringUtils.SPACE)).toString();
    }

    private final String takeThroughMarker(String text, Set<String> markers) {
        int iNextIndex;
        boolean z;
        List<String> listSplit = new Regex("\\s+").split(text, 0);
        ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                String t = listIterator.previous();
                String l = t.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(l, "toLowerCase(...)");
                Set<String> $this$any$iv = markers;
                if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object element$iv = it.next();
                            String m = (String) element$iv;
                            if (Intrinsics.areEqual(l, m) || StringsKt.startsWith$default(l, m, false, 2, (Object) null)) {
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
                if (z) {
                    iNextIndex = listIterator.nextIndex();
                    break;
                }
            } else {
                iNextIndex = -1;
                break;
            }
        }
        int idx = iNextIndex;
        return idx < 0 ? CollectionsKt.joinToString$default(CollectionsKt.take(listSplit, 1), StringUtils.SPACE, null, null, 0, null, null, 62, null) : CollectionsKt.joinToString$default(CollectionsKt.take(listSplit, idx + 1), StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00ec A[LOOP:1: B:20:0x0062->B:56:0x00ec, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:68:0x00ea A[SYNTHETIC] */
    private final boolean hasLikelyEnPhonetics(String ru) {
        boolean z;
        List tail;
        Iterable $this$any$iv;
        boolean z2;
        boolean z3;
        Iterable $this$dropWhile$iv = new Regex("\\s+").split(ru, 0);
        boolean yielding$iv = false;
        ArrayList list$iv = new ArrayList();
        for (Object item$iv : $this$dropWhile$iv) {
            if (yielding$iv) {
                list$iv.add(item$iv);
            } else {
                String it = (String) item$iv;
                if (!COMMANDISH.contains(it)) {
                    list$iv.add(item$iv);
                    yielding$iv = true;
                }
            }
        }
        ArrayList tail2 = list$iv;
        if (tail2.isEmpty()) {
            return false;
        }
        ArrayList $this$any$iv2 = tail2;
        if (($this$any$iv2 instanceof Collection) && $this$any$iv2.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv2) {
            CharSequence t = (String) element$iv;
            CharSequence $this$any$iv3 = t;
            int i = 0;
            while (true) {
                if (i >= $this$any$iv3.length()) {
                    z = false;
                    break;
                }
                char element$iv2 = $this$any$iv3.charAt(i);
                char ch = ('a' > element$iv2 || element$iv2 >= '{') ? (char) 0 : (char) 1;
                if (ch != 0) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                tail = tail2;
                $this$any$iv = $this$any$iv2;
            } else {
                Iterable $this$any$iv4 = PHONETIC_HINTS;
                if (($this$any$iv4 instanceof Collection) && ((Collection) $this$any$iv4).isEmpty()) {
                    tail = tail2;
                    $this$any$iv = $this$any$iv2;
                    z3 = false;
                } else {
                    Iterator it2 = $this$any$iv4.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            tail = tail2;
                            $this$any$iv = $this$any$iv2;
                            z3 = false;
                            break;
                        }
                        Object element$iv3 = it2.next();
                        String h = (String) element$iv3;
                        tail = tail2;
                        $this$any$iv = $this$any$iv2;
                        if (StringsKt.contains$default(t, (CharSequence) h, false, 2, (Object) null)) {
                            z3 = true;
                            break;
                        }
                        tail2 = tail;
                        $this$any$iv2 = $this$any$iv;
                    }
                }
                if (!z3) {
                    z2 = false;
                }
                if (z2) {
                    return true;
                }
                tail2 = tail;
                $this$any$iv2 = $this$any$iv;
            }
            z2 = true;
            if (z2) {
                return true;
            }
            tail2 = tail;
            $this$any$iv2 = $this$any$iv;
        }
        return false;
    }
}
