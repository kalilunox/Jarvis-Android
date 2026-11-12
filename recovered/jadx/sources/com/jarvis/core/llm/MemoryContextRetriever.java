package com.jarvis.core.llm;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.skills.SkillRetriever;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: MemoryContextRetriever.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J2\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\t\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bJ\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J\u001c\u0010\u0015\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\u0016\u001a\u00020\u0011R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/llm/MemoryContextRetriever;", "", "<init>", "()V", "memoryQuestionMarkers", "", "", "needsMemory", "", SearchIntents.EXTRA_QUERY, "isAboutUser", "ABOUT_USER_WORDS", "", "rank", "Lcom/jarvis/core/llm/MemorySnippet;", "snippets", "limit", "", "cloudAllowed", "isCloudBlocked", "s", "formatBlock", "maxChars", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryContextRetriever {
    public static final MemoryContextRetriever INSTANCE = new MemoryContextRetriever();
    private static final List<String> memoryQuestionMarkers = CollectionsKt.listOf((Object[]) new String[]{"помнишь", "помню", "память", "обычно", "предпочита", "зовут", "имя", "vpn", "настрой", "люблю", "ненавиж", "всегда", "никогда"});
    private static final Set<String> ABOUT_USER_WORDS = SetsKt.setOf((Object[]) new String[]{"меня", "мне", "мой", "моя", "моё", "мое", "мои", "моих", "мою", "обо"});

    private MemoryContextRetriever() {
    }

    public final boolean needsMemory(String query) {
        boolean z;
        Intrinsics.checkNotNullParameter(query, "query");
        String q = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        Iterable $this$any$iv = memoryQuestionMarkers;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) q, (CharSequence) it2, false, 2, (Object) null)) {
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
        return z || isAboutUser(q);
    }

    public final boolean isAboutUser(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable words = CollectionsKt.toSet(new Regex("[^\\p{L}]+").split(lowerCase, 0));
        Iterable $this$any$iv = words;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (ABOUT_USER_WORDS.contains(it)) {
                return true;
            }
        }
        return false;
    }

    public final List<MemorySnippet> rank(String query, List<MemorySnippet> snippets, int limit, boolean cloudAllowed) {
        int count$iv;
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(snippets, "snippets");
        if (snippets.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<String> listDefaultNormalize = SkillRetriever.INSTANCE.defaultNormalize(query);
        boolean needs = needsMemory(query);
        List<MemorySnippet> $this$filter$iv = snippets;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            MemorySnippet it = (MemorySnippet) element$iv$iv;
            if (cloudAllowed || !INSTANCE.isCloudBlocked(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            MemorySnippet s = (MemorySnippet) item$iv$iv;
            List<String> listDefaultNormalize2 = SkillRetriever.INSTANCE.defaultNormalize(s.getText());
            float score = s.getScore();
            if (!listDefaultNormalize.isEmpty() && !listDefaultNormalize2.isEmpty()) {
                List<String> $this$count$iv = listDefaultNormalize;
                if (($this$count$iv instanceof Collection) && $this$count$iv.isEmpty()) {
                    count$iv = 0;
                } else {
                    count$iv = 0;
                    for (Object element$iv : $this$count$iv) {
                        String it2 = (String) element$iv;
                        if (CollectionsKt.toSet(listDefaultNormalize2).contains(it2)) {
                            count$iv++;
                            if (count$iv < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                }
                int hits = count$iv;
                score += hits / listDefaultNormalize.size();
            }
            if (needs && StringsKt.contains((CharSequence) s.getSource(), (CharSequence) "companion", true)) {
                score += 0.2f;
            }
            destination$iv$iv2.add(MemorySnippet.copy$default(s, null, null, null, score, 7, null));
        }
        Iterable $this$filter$iv2 = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            MemorySnippet it3 = (MemorySnippet) element$iv$iv2;
            if (needs || it3.getScore() > 0.15f) {
                destination$iv$iv3.add(element$iv$iv2);
            }
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv3;
        return CollectionsKt.take(CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.llm.MemoryContextRetriever$rank$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                MemorySnippet it4 = (MemorySnippet) t2;
                MemorySnippet it5 = (MemorySnippet) t;
                return ComparisonsKt.compareValues(Float.valueOf(it4.getScore()), Float.valueOf(it5.getScore()));
            }
        }), RangesKt.coerceIn(limit, 0, 8));
    }

    private final boolean isCloudBlocked(MemorySnippet s) {
        String sens = s.getSensitivity().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(sens, "toUpperCase(...)");
        return Intrinsics.areEqual(sens, "PRIVATE") || Intrinsics.areEqual(sens, "SECRET") || Intrinsics.areEqual(sens, "LOCAL_ONLY");
    }

    public final String formatBlock(List<MemorySnippet> snippets, int maxChars) {
        Intrinsics.checkNotNullParameter(snippets, "snippets");
        return snippets.isEmpty() ? "" : StringsKt.take(CollectionsKt.joinToString$default(snippets, StringUtils.LF, null, null, 0, null, new Function1() { // from class: com.jarvis.core.llm.MemoryContextRetriever$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MemoryContextRetriever.formatBlock$lambda$0((MemorySnippet) obj);
            }
        }, 30, null), maxChars);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence formatBlock$lambda$0(MemorySnippet it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "- " + StringsKt.take(it.getText(), 160) + " [" + it.getSource() + "]";
    }
}
