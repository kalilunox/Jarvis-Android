package com.jarvis.core.nlp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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

/* JADX INFO: compiled from: CommandTextSelector.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/nlp/CommandTextSelector;", "", "<init>", "()V", "select", "", "streamingRu", "offlineMulti", "englishPass", "extractLatinPhrase", "enRaw", "preferWithRuVerb", "ru", "other", "isObjectCommand", "", VoiceService.EXTRA_TEXT, "looksLikeCommand", "COMMAND_VERBS", "", "EN_NOISE", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CommandTextSelector {
    public static final CommandTextSelector INSTANCE = new CommandTextSelector();
    private static final List<String> COMMAND_VERBS = CollectionsKt.listOf((Object[]) new String[]{"включи", "выключи", "открой", "закрой", "поставь", "запусти", "найди", "покажи", "расскажи", "play", AbstractCircuitBreaker.PROPERTY_NAME, "turn"});
    private static final Set<String> EN_NOISE = SetsKt.setOf((Object[]) new String[]{"the", "a", "an", "and", "or", TypedValues.TransitionType.S_TO, "of", "in", "on", "for", "my", "me", "i", "you", "we", "it", "is", "are", "was", "be", "this", "that", "please", "yeah", "yes", "no", "oh", "uh", "um", "ah", "he", "she", "his", "her", "they", "them", "their", "thank", "thanks", "okay", "ok", "so", "just", "like"});

    private CommandTextSelector() {
    }

    public static /* synthetic */ String select$default(CommandTextSelector commandTextSelector, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        return commandTextSelector.select(str, str2, str3);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00c6  */
    public final String select(String streamingRu, String offlineMulti, String englishPass) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        char element$iv;
        String it;
        Intrinsics.checkNotNullParameter(streamingRu, "streamingRu");
        String ru = AsrPhraseNormalizer.INSTANCE.normalize(StringsKt.trim((CharSequence) streamingRu).toString());
        String it2 = (offlineMulti == null || (it = StringsKt.trim((CharSequence) offlineMulti).toString()) == null) ? null : AsrPhraseNormalizer.INSTANCE.normalize(it);
        String base = "";
        if (it2 == null) {
            it2 = "";
        }
        String enRaw = englishPass != null ? StringsKt.trim((CharSequence) englishPass).toString() : null;
        if (enRaw == null) {
            enRaw = "";
        }
        String enLatin = extractLatinPhrase(enRaw);
        if (!StringsKt.isBlank(enLatin) && isObjectCommand(ru)) {
            String skeleton = (!looksLikeCommand(ru) && looksLikeCommand(it2)) ? it2 : ru;
            String merged = RuEnTextMerger.INSTANCE.merge(skeleton, enLatin);
            String $this$any$iv = merged;
            int i = 0;
            while (true) {
                if (i < $this$any$iv.length()) {
                    char element$iv2 = $this$any$iv.charAt(i);
                    if ((('a' > element$iv2 || element$iv2 >= '{') ? (char) 0 : (char) 1) != 0) {
                        element$iv = 1;
                        break;
                    }
                    i++;
                } else {
                    element$iv = 0;
                    break;
                }
            }
            if (element$iv != 0) {
                return merged;
            }
        }
        if (!StringsKt.isBlank(it2) && looksLikeCommand(it2)) {
            base = it2;
        } else if (!StringsKt.isBlank(ru)) {
            base = ru;
        } else if (!StringsKt.isBlank(it2)) {
            base = it2;
        }
        if (!StringsKt.isBlank(enLatin) && isObjectCommand(base)) {
            String merged2 = RuEnTextMerger.INSTANCE.merge(base, enLatin);
            String $this$any$iv2 = merged2;
            int i2 = 0;
            while (true) {
                if (i2 < $this$any$iv2.length()) {
                    char element$iv3 = $this$any$iv2.charAt(i2);
                    if ((('a' > element$iv3 || element$iv3 >= '{') ? (char) 0 : (char) 1) != 0) {
                        z4 = true;
                        break;
                    }
                    i2++;
                } else {
                    z4 = false;
                    break;
                }
            }
            if (z4) {
                return merged2;
            }
        }
        if (!StringsKt.isBlank(enLatin) && StringsKt.isBlank(base)) {
            return enLatin;
        }
        CharSequence $this$any$iv3 = it2;
        int i3 = 0;
        while (true) {
            if (i3 < $this$any$iv3.length()) {
                char element$iv4 = $this$any$iv3.charAt(i3);
                if ((('a' > element$iv4 || element$iv4 >= '{') ? (char) 0 : (char) 1) != 0) {
                    z = true;
                    break;
                }
                i3++;
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            return preferWithRuVerb(ru, it2);
        }
        if (!StringsKt.isBlank(enLatin) && !StringsKt.isBlank(base)) {
            CharSequence $this$any$iv4 = base;
            int i4 = 0;
            while (true) {
                if (i4 < $this$any$iv4.length()) {
                    char element$iv5 = $this$any$iv4.charAt(i4);
                    if ((('a' > element$iv5 || element$iv5 >= '{') ? (char) 0 : (char) 1) != 0) {
                        z2 = true;
                        break;
                    }
                    i4++;
                } else {
                    z2 = false;
                    break;
                }
            }
            if (!z2) {
                String merged3 = RuEnTextMerger.INSTANCE.merge(base, enLatin);
                String $this$any$iv5 = merged3;
                int i5 = 0;
                while (true) {
                    if (i5 < $this$any$iv5.length()) {
                        char element$iv6 = $this$any$iv5.charAt(i5);
                        if ((('a' > element$iv6 || element$iv6 >= '{') ? (char) 0 : (char) 1) != 0) {
                            z3 = true;
                            break;
                        }
                        i5++;
                    } else {
                        z3 = false;
                        break;
                    }
                }
                if (z3) {
                    return merged3;
                }
            }
        }
        String str = base;
        if (StringsKt.isBlank(str)) {
            str = ru;
        }
        String str2 = str;
        if (StringsKt.isBlank(str2)) {
            str2 = enLatin;
        }
        return str2;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:37:0x00bf A[SYNTHETIC] */
    public final String extractLatinPhrase(String enRaw) {
        String cleaned;
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(enRaw, "enRaw");
        String lowerCase = enRaw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String cleaned2 = StringsKt.trim((CharSequence) new Regex("\\s+").replace(new Regex("[^a-z0-9\\s']").replace(lowerCase, StringUtils.SPACE), StringUtils.SPACE)).toString();
        if (StringsKt.isBlank(cleaned2)) {
            return "";
        }
        int i = 1;
        int i2 = 0;
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) cleaned2, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String w = (String) element$iv$iv;
            if (w.length() <= i) {
                cleaned = cleaned2;
            } else {
                String $this$any$iv = w;
                int i3 = i2;
                while (true) {
                    if (i3 < $this$any$iv.length()) {
                        char element$iv = $this$any$iv.charAt(i3);
                        cleaned = cleaned2;
                        if ('a' <= element$iv && element$iv < '{') {
                            z2 = true;
                            break;
                        }
                        i3++;
                        cleaned2 = cleaned;
                    } else {
                        cleaned = cleaned2;
                        z2 = false;
                        break;
                    }
                }
                if (z2 && !EN_NOISE.contains(w)) {
                    z = true;
                }
                if (z) {
                    destination$iv$iv.add(element$iv$iv);
                }
                cleaned2 = cleaned;
                i = 1;
                i2 = 0;
            }
            z = false;
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
            cleaned2 = cleaned;
            i = 1;
            i2 = 0;
        }
        List words = (List) destination$iv$iv;
        return CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    private final String preferWithRuVerb(String ru, String other) {
        boolean hasRuVerb;
        boolean z;
        String ruLower = ru.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(ruLower, "toLowerCase(...)");
        String otherLower = other.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(otherLower, "toLowerCase(...)");
        Iterable $this$any$iv = COMMAND_VERBS;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) ruLower, (CharSequence) it2, false, 2, (Object) null)) {
                        hasRuVerb = true;
                        break;
                    }
                } else {
                    hasRuVerb = false;
                    break;
                }
            }
        } else {
            hasRuVerb = false;
        }
        Iterable $this$any$iv2 = COMMAND_VERBS;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (StringsKt.contains$default((CharSequence) otherLower, (CharSequence) it4, false, 2, (Object) null)) {
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
        boolean hasOtherVerb = z;
        return (!hasRuVerb || hasOtherVerb) ? other : RuEnTextMerger.INSTANCE.merge(ru, other);
    }

    private final boolean isObjectCommand(String text) {
        boolean z;
        String t = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        Iterable $this$any$iv = COMMAND_VERBS;
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
        return z || StringsKt.contains$default((CharSequence) t, (CharSequence) "песн", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "трек", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "альбом", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "музык", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "открой", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "запусти", false, 2, (Object) null);
    }

    private final boolean looksLikeCommand(String text) {
        boolean z;
        String t = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        Iterable $this$any$iv = COMMAND_VERBS;
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
        return z || StringsKt.contains$default((CharSequence) t, (CharSequence) "песн", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "трек", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "погод", false, 2, (Object) null);
    }
}
