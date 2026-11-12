package com.jarvis.core.storage;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.AndroidReminderBook;
import com.jarvis.app.VoiceService;
import com.jarvis.core.skills.SkillRetriever;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FactPolicies.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006J\"\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005JD\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0005\"\u0004\b\u0000\u0010\u00142\u0006\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00060\u001aR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/storage/FactScoring;", "", "<init>", "()V", "tokenize", "", "", VoiceService.EXTRA_TEXT, "wordsMatch", "", "a", "b", "SHORT_CASE_TAILS", "", "WORD_ENDINGS", "score", "", "queryWords", "factWords", "topN", ExifInterface.GPS_DIRECTION_TRUE, SearchIntents.EXTRA_QUERY, AndroidReminderBook.KEY_ITEMS, "limit", "", "textOf", "Lkotlin/Function1;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FactScoring {
    public static final FactScoring INSTANCE = new FactScoring();
    private static final Set<String> SHORT_CASE_TAILS = SetsKt.setOf((Object[]) new String[]{"й", "я", "ю"});
    private static final Set<String> WORD_ENDINGS = SetsKt.setOf((Object[]) new String[]{"а", "я", "о", "е", "ё", "ы", "и", "у", "ю", "ой", "ей", "ом", "ем", "ём", "ам", "ям", "ах", "ях", "ов", "ев", "ий", "ия", "ию", "ье", "ья", "ого", "его", "ому", "ему", "ым", "им", "ых", "их", "ую", "юю", "ая", "яя", "ое", "ее", "ешь", "ёшь", "ишь", "шь", "ет", "ёт", "ит", "ем", "им", "ете", "ёте", "ите", "ут", "ют", "ат", "ят"});

    private FactScoring() {
    }

    public final List<String> tokenize(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return SkillRetriever.INSTANCE.defaultNormalize(text);
    }

    public final boolean wordsMatch(String a, String b) {
        int minLen;
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (Intrinsics.areEqual(a, b)) {
            return true;
        }
        if (!(a.length() == 0)) {
            if ((b.length() == 0) || (minLen = Math.min(a.length(), b.length())) < 3) {
                return false;
            }
            int prefix = 0;
            while (prefix < minLen && a.charAt(prefix) == b.charAt(prefix)) {
                prefix++;
            }
            if (prefix == minLen) {
                return true;
            }
            if (minLen == 3) {
                if (prefix != 2) {
                    return false;
                }
                if (a.length() == b.length()) {
                    String ta = a.substring(2);
                    Intrinsics.checkNotNullExpressionValue(ta, "substring(...)");
                    String tb = b.substring(2);
                    Intrinsics.checkNotNullExpressionValue(tb, "substring(...)");
                    return SHORT_CASE_TAILS.contains(ta) && SHORT_CASE_TAILS.contains(tb);
                }
                String longer = a.length() > b.length() ? a : b;
                String tail = longer.substring(2);
                Intrinsics.checkNotNullExpressionValue(tail, "substring(...)");
                return tail.length() >= 2 && WORD_ENDINGS.contains(tail);
            }
            if (prefix < 3) {
                return false;
            }
            if (minLen > 5) {
                return ((float) prefix) / ((float) minLen) >= 0.6f;
            }
            Set<String> set = WORD_ENDINGS;
            String strSubstring = a.substring(prefix);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            if (set.contains(strSubstring)) {
                return true;
            }
            Set<String> set2 = WORD_ENDINGS;
            String strSubstring2 = b.substring(prefix);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            return set2.contains(strSubstring2);
        }
        return false;
    }

    public final float score(List<String> queryWords, List<String> factWords) {
        int i;
        boolean z;
        Intrinsics.checkNotNullParameter(queryWords, "queryWords");
        Intrinsics.checkNotNullParameter(factWords, "factWords");
        if (queryWords.isEmpty() || factWords.isEmpty()) {
            return 0.0f;
        }
        List<String> $this$count$iv = queryWords;
        if (($this$count$iv instanceof Collection) && $this$count$iv.isEmpty()) {
            i = 0;
        } else {
            int count$iv = 0;
            for (Object element$iv : $this$count$iv) {
                String q = (String) element$iv;
                List<String> $this$any$iv = factWords;
                if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        Object element$iv2 = it.next();
                        String f = (String) element$iv2;
                        if (INSTANCE.wordsMatch(q, f)) {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
                if (z && (count$iv = count$iv + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            i = count$iv;
        }
        int hits = i;
        return hits / queryWords.size();
    }

    public final <T> List<T> topN(String query, List<? extends T> items, int limit, Function1<? super T, String> textOf) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(textOf, "textOf");
        List<String> list = tokenize(query);
        if (list.isEmpty() || limit <= 0) {
            return CollectionsKt.emptyList();
        }
        List<? extends T> $this$map$iv = items;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(TuplesKt.to(item$iv$iv, Float.valueOf(INSTANCE.score(list, INSTANCE.tokenize(textOf.invoke(item$iv$iv))))));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Pair it = (Pair) element$iv$iv;
            if (((Number) it.getSecond()).floatValue() > 0.0f) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv2;
        Iterable<Pair> $this$map$iv2 = CollectionsKt.take(CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.storage.FactScoring$topN$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Pair it2 = (Pair) t2;
                Float f = (Float) it2.getSecond();
                Pair it3 = (Pair) t;
                return ComparisonsKt.compareValues(f, (Float) it3.getSecond());
            }
        }), limit);
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Pair it2 : $this$map$iv2) {
            destination$iv$iv3.add(it2.getFirst());
        }
        return (List) destination$iv$iv3;
    }
}
