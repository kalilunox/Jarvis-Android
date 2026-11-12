package com.jarvis.core.nlp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.AndroidReminderBook;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: MusicQueryMatcher.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001*B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006J \u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0006JT\u0010\f\u001a\n\u0012\u0004\u0012\u0002H\u000e\u0018\u00010\r\"\u0004\b\u0000\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00102\u0006\u0010\u0007\u001a\u00020\u00062\u001e\u0010\u0011\u001a\u001a\u0012\u0004\u0012\u0002H\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00130\u00122\b\b\u0002\u0010\u0014\u001a\u00020\tJ\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0002J\u0018\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H\u0002J\u0018\u0010#\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H\u0002R\u000e\u0010$\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/jarvis/core/nlp/MusicQueryMatcher;", "", "<init>", "()V", "queryVariants", "", "", SearchIntents.EXTRA_QUERY, "score", "", "title", "subtitle", "bestMatch", "Lcom/jarvis/core/nlp/MusicQueryMatcher$Scored;", ExifInterface.GPS_DIRECTION_TRUE, AndroidReminderBook.KEY_ITEMS, "", "labelOf", "Lkotlin/Function1;", "Lkotlin/Pair;", "minScore", "scoreAgainst", "candidate", "tokenCoverage", "haystack", "significantContains", "", "longer", "shorter", "tokens", "value", "similarity", "", "a", "b", "levenshtein", "MIN_ACCEPT", "MIN_SEARCH", "TOKEN_REGEX", "Lkotlin/text/Regex;", "STOPWORDS", "", "Scored", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MusicQueryMatcher {
    public static final int MIN_ACCEPT = 55;
    public static final int MIN_SEARCH = 55;
    public static final MusicQueryMatcher INSTANCE = new MusicQueryMatcher();
    private static final Regex TOKEN_REGEX = new Regex("[\\p{L}\\p{N}]+");
    private static final Set<String> STOPWORDS = SetsKt.setOf((Object[]) new String[]{"the", "a", "an", "and", "or", "of", TypedValues.TransitionType.S_TO, "in", "on", "for", "my", "me", "и", "на", "в", "с", "по", "из", "для", "это", "я", "ты"});

    private MusicQueryMatcher() {
    }

    /* JADX INFO: compiled from: MusicQueryMatcher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0011\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J2\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/nlp/MusicQueryMatcher$Scored;", ExifInterface.GPS_DIRECTION_TRUE, "", "item", "score", "", "label", "", "<init>", "(Ljava/lang/Object;ILjava/lang/String;)V", "getItem", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getScore", "()I", "getLabel", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;ILjava/lang/String;)Lcom/jarvis/core/nlp/MusicQueryMatcher$Scored;", "equals", "", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Scored<T> {
        private final T item;
        private final String label;
        private final int score;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Scored copy$default(Scored scored, Object obj, int i, String str, int i2, Object obj2) {
            if ((i2 & 1) != 0) {
                obj = scored.item;
            }
            if ((i2 & 2) != 0) {
                i = scored.score;
            }
            if ((i2 & 4) != 0) {
                str = scored.label;
            }
            return scored.copy(obj, i, str);
        }

        public final T component1() {
            return this.item;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final Scored<T> copy(T item, int score, String label) {
            Intrinsics.checkNotNullParameter(label, "label");
            return new Scored<>(item, score, label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Scored)) {
                return false;
            }
            Scored scored = (Scored) other;
            return Intrinsics.areEqual(this.item, scored.item) && this.score == scored.score && Intrinsics.areEqual(this.label, scored.label);
        }

        public int hashCode() {
            return ((((this.item == null ? 0 : this.item.hashCode()) * 31) + Integer.hashCode(this.score)) * 31) + this.label.hashCode();
        }

        public String toString() {
            return "Scored(item=" + this.item + ", score=" + this.score + ", label=" + this.label + ")";
        }

        public Scored(T t, int score, String label) {
            Intrinsics.checkNotNullParameter(label, "label");
            this.item = t;
            this.score = score;
            this.label = label;
        }

        public final T getItem() {
            return this.item;
        }

        public final String getLabel() {
            return this.label;
        }

        public final int getScore() {
            return this.score;
        }
    }

    public final List<String> queryVariants(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        String raw = StringsKt.trim((CharSequence) query).toString();
        if (raw.length() == 0) {
            return CollectionsKt.emptyList();
        }
        String normalized = AsrPhraseNormalizer.INSTANCE.normalize(raw);
        String latin = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(new Regex("\\s+").split(raw, 0), StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.core.nlp.MusicQueryMatcher$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MusicQueryMatcher.queryVariants$lambda$0((String) obj);
            }
        }, 30, null)).toString();
        Iterable $this$map$iv = CollectionsKt.listOf((Object[]) new String[]{raw, normalized, latin});
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (!StringsKt.isBlank(it2)) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable $this$distinctBy$iv = (List) destination$iv$iv2;
        HashSet set$iv = new HashSet();
        ArrayList list$iv = new ArrayList();
        for (Object e$iv : $this$distinctBy$iv) {
            String it3 = (String) e$iv;
            String lowerCase = it3.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (set$iv.add(lowerCase)) {
                list$iv.add(e$iv);
            }
        }
        return list$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence queryVariants$lambda$0(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        CharSequence mapped = AsrPhraseNormalizer.INSTANCE.normalize(token);
        String lowerCase = token.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (!Intrinsics.areEqual(mapped, lowerCase)) {
            CharSequence $this$any$iv = mapped;
            boolean z = false;
            for (int i = 0; i < $this$any$iv.length(); i++) {
                char element$iv = $this$any$iv.charAt(i);
                char it = ('a' > element$iv || element$iv >= '{') ? (char) 0 : (char) 1;
                if (it != 0) {
                    z = true;
                    break;
                }
            }
            if (z) {
                return mapped;
            }
        }
        return new Regex("[^\\p{L}\\p{N}]+").replace(Transliterator.INSTANCE.toLatin(token), "");
    }

    public static /* synthetic */ int score$default(MusicQueryMatcher musicQueryMatcher, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        return musicQueryMatcher.score(str, str2, str3);
    }

    public final int score(String query, String title, String subtitle) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        String q = StringsKt.trim((CharSequence) query).toString();
        if (q.length() == 0) {
            return 0;
        }
        int titleScore = scoreAgainst(q, title);
        int subScore = StringsKt.isBlank(subtitle) ? 0 : scoreAgainst(q, subtitle);
        int combined = scoreAgainst(q, StringsKt.trim((CharSequence) (title + StringUtils.SPACE + subtitle)).toString());
        int cover = tokenCoverage(q, title + StringUtils.SPACE + subtitle);
        return ComparisonsKt.maxOf(titleScore, subScore, combined, cover);
    }

    public static /* synthetic */ Scored bestMatch$default(MusicQueryMatcher musicQueryMatcher, Iterable iterable, String str, Function1 function1, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 55;
        }
        return musicQueryMatcher.bestMatch(iterable, str, function1, i);
    }

    public final <T> Scored<T> bestMatch(Iterable<? extends T> items, String query, Function1<? super T, Pair<String, String>> labelOf, int minScore) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(labelOf, "labelOf");
        List<String> listQueryVariants = queryVariants(query);
        if (listQueryVariants.isEmpty()) {
            return null;
        }
        Scored<T> scored = null;
        for (Object obj : items) {
            Pair<String, String> pairInvoke = labelOf.invoke(obj);
            String strComponent1 = pairInvoke.component1();
            String strComponent2 = pairInvoke.component2();
            if (!StringsKt.isBlank(strComponent1) || !StringsKt.isBlank(strComponent2)) {
                Iterator<T> it = listQueryVariants.iterator();
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
                int iScore = INSTANCE.score((String) it.next(), strComponent1, strComponent2);
                while (it.hasNext()) {
                    int iScore2 = INSTANCE.score((String) it.next(), strComponent1, strComponent2);
                    if (iScore < iScore2) {
                        iScore = iScore2;
                    }
                }
                int i = iScore;
                if (i >= minScore) {
                    String str = (StringsKt.isBlank(strComponent1) || StringsKt.isBlank(strComponent2)) ? !StringsKt.isBlank(strComponent1) ? strComponent1 : strComponent2 : strComponent2 + " — " + strComponent1;
                    if (scored == null || i > scored.getScore()) {
                        scored = new Scored<>(obj, i, str);
                    }
                }
            }
        }
        return scored;
    }

    private final int scoreAgainst(String query, String candidate) {
        int count$iv;
        List cTokens;
        String qRaw;
        String cRaw;
        boolean exact;
        boolean close;
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String qRaw2 = StringsKt.trim((CharSequence) lowerCase).toString();
        String lowerCase2 = candidate.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        String cRaw2 = StringsKt.trim((CharSequence) lowerCase2).toString();
        if (qRaw2.length() == 0) {
            return 0;
        }
        if (cRaw2.length() == 0) {
            return 0;
        }
        if (Intrinsics.areEqual(qRaw2, cRaw2)) {
            return 100;
        }
        String q = Transliterator.INSTANCE.compareKey(query);
        String c = Transliterator.INSTANCE.compareKey(candidate);
        if (q.length() == 0) {
            return 0;
        }
        if (c.length() == 0) {
            return 0;
        }
        if (Intrinsics.areEqual(q, c)) {
            return 98;
        }
        if (StringsKt.startsWith$default(c, q, false, 2, (Object) null) && q.length() >= 3) {
            return 90;
        }
        if (significantContains(c, q)) {
            return 82;
        }
        if (significantContains(q, c)) {
            return 72;
        }
        double sim = similarity(q, c);
        if (sim >= 0.82d) {
            return (int) (((double) 88) * sim);
        }
        if (sim >= 0.7d) {
            return (int) (((double) 75) * sim);
        }
        Iterable $this$filterNot$iv = tokens(query);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it = (String) element$iv$iv;
            if (!STOPWORDS.contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List<String> qTokens = (List) destination$iv$iv;
        Iterable $this$filterNot$iv2 = tokens(candidate);
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filterNot$iv2) {
            String it2 = (String) element$iv$iv2;
            if (!STOPWORDS.contains(it2)) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List cTokens2 = (List) destination$iv$iv2;
        if (qTokens.isEmpty()) {
            return 0;
        }
        int hit = 0;
        int fuzzyHit = 0;
        for (String qt : qTokens) {
            String qk = Transliterator.INSTANCE.compareKey(qt);
            if (qk.length() < 2) {
                cTokens = cTokens2;
                qRaw = qRaw2;
                cRaw = cRaw2;
            } else {
                List $this$any$iv = cTokens2;
                if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                    Iterator it3 = $this$any$iv.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Object element$iv = it3.next();
                            qRaw = qRaw2;
                            String ct = (String) element$iv;
                            cRaw = cRaw2;
                            String ck = Transliterator.INSTANCE.compareKey(ct);
                            if (Intrinsics.areEqual(ck, qk) || INSTANCE.significantContains(ck, qk) || INSTANCE.significantContains(qk, ck)) {
                                exact = true;
                                break;
                            }
                            qRaw2 = qRaw;
                            cRaw2 = cRaw;
                        } else {
                            qRaw = qRaw2;
                            cRaw = cRaw2;
                            exact = false;
                            break;
                        }
                    }
                } else {
                    qRaw = qRaw2;
                    cRaw = cRaw2;
                    exact = false;
                }
                if (exact) {
                    hit++;
                    qRaw2 = qRaw;
                    cRaw2 = cRaw;
                } else {
                    List $this$any$iv2 = cTokens2;
                    if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                        Iterator it4 = $this$any$iv2.iterator();
                        while (true) {
                            if (it4.hasNext()) {
                                Object element$iv2 = it4.next();
                                cTokens = cTokens2;
                                String it5 = (String) element$iv2;
                                boolean exact2 = exact;
                                Iterable $this$any$iv3 = $this$any$iv2;
                                if (INSTANCE.similarity(qk, Transliterator.INSTANCE.compareKey(it5)) >= 0.78d) {
                                    close = true;
                                    break;
                                }
                                cTokens2 = cTokens;
                                exact = exact2;
                                $this$any$iv2 = $this$any$iv3;
                            } else {
                                cTokens = cTokens2;
                                close = false;
                                break;
                            }
                        }
                    } else {
                        cTokens = cTokens2;
                        close = false;
                    }
                    if (close) {
                        fuzzyHit++;
                        cTokens2 = cTokens;
                        qRaw2 = qRaw;
                        cRaw2 = cRaw;
                    }
                }
            }
            cTokens2 = cTokens;
            qRaw2 = qRaw;
            cRaw2 = cRaw;
        }
        List $this$count$iv = qTokens;
        if (($this$count$iv instanceof Collection) && $this$count$iv.isEmpty()) {
            count$iv = 0;
        } else {
            count$iv = 0;
            for (Object element$iv3 : $this$count$iv) {
                String it6 = (String) element$iv3;
                if ((Transliterator.INSTANCE.compareKey(it6).length() >= 2) && (count$iv = count$iv + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        int need = count$iv;
        if (need == 0) {
            return 0;
        }
        if (hit == need) {
            return 86;
        }
        if (hit + fuzzyHit == need && hit >= 1) {
            return 70;
        }
        if (hit + fuzzyHit == need) {
            return 58;
        }
        if (hit > 0 && hit + fuzzyHit >= (need + 1) / 2) {
            return (hit * 15) + 40;
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:67:0x018f  */
    private final int tokenCoverage(String query, String haystack) {
        int count$iv;
        boolean z;
        boolean z2;
        Iterable $this$filterNot$iv = tokens(query);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            if (!STOPWORDS.contains((String) element$iv$iv)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv2.add(Transliterator.INSTANCE.compareKey((String) item$iv$iv));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv) {
            if (((String) element$iv$iv2).length() >= 2) {
                destination$iv$iv3.add(element$iv$iv2);
            }
        }
        List qTokens = (List) destination$iv$iv3;
        if (qTokens.isEmpty()) {
            return 0;
        }
        Iterable $this$filterNot$iv2 = tokens(haystack);
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv3 : $this$filterNot$iv2) {
            if (!STOPWORDS.contains((String) element$iv$iv3)) {
                destination$iv$iv4.add(element$iv$iv3);
            }
        }
        Iterable $this$map$iv2 = (List) destination$iv$iv4;
        Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            destination$iv$iv5.add(Transliterator.INSTANCE.compareKey((String) item$iv$iv2));
        }
        Iterable hTokens = (List) destination$iv$iv5;
        String h = Transliterator.INSTANCE.compareKey(haystack);
        List $this$count$iv = qTokens;
        if (($this$count$iv instanceof Collection) && $this$count$iv.isEmpty()) {
            count$iv = 0;
        } else {
            count$iv = 0;
            for (Object element$iv : $this$count$iv) {
                String t = (String) element$iv;
                if (INSTANCE.significantContains(h, t)) {
                    z = true;
                } else {
                    Iterable $this$any$iv = hTokens;
                    if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                        Iterator it = $this$any$iv.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z2 = false;
                                break;
                            }
                            Object element$iv2 = it.next();
                            String it2 = (String) element$iv2;
                            if (INSTANCE.similarity(t, it2) >= 0.78d || Intrinsics.areEqual(it2, t)) {
                                z2 = true;
                                break;
                            }
                        }
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    count$iv++;
                    if (count$iv < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
        }
        int covered = count$iv;
        if (covered == qTokens.size()) {
            return 84;
        }
        if (covered >= (qTokens.size() + 1) / 2) {
            return (covered * 10) + 45;
        }
        return 0;
    }

    private final boolean significantContains(String longer, String shorter) {
        return shorter.length() >= 4 && StringsKt.contains$default((CharSequence) longer, (CharSequence) shorter, false, 2, (Object) null) && (shorter.length() * 100) / longer.length() >= 55;
    }

    private final List<String> tokens(String value) {
        Regex regex = TOKEN_REGEX;
        String lowerCase = value.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return SequencesKt.toList(SequencesKt.filter(SequencesKt.map(Regex.findAll$default(regex, lowerCase, 0, 2, null), new Function1() { // from class: com.jarvis.core.nlp.MusicQueryMatcher$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MusicQueryMatcher.tokens$lambda$0((MatchResult) obj);
            }
        }), new Function1() { // from class: com.jarvis.core.nlp.MusicQueryMatcher$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MusicQueryMatcher.tokens$lambda$1((String) obj));
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String tokens$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean tokens$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.length() > 1;
    }

    private final double similarity(String a, String b) {
        if (Intrinsics.areEqual(a, b)) {
            return 1.0d;
        }
        if (!(a.length() == 0)) {
            if ((b.length() == 0) || Math.abs(a.length() - b.length()) > Math.max(3, Math.max(a.length(), b.length()) / 2)) {
                return 0.0d;
            }
            int dist = levenshtein(a, b);
            return 1.0d - (((double) dist) / ((double) Math.max(a.length(), b.length())));
        }
        return 0.0d;
    }

    private final int levenshtein(String a, String b) {
        int m = b.length();
        int i = m + 1;
        int[] dp = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            dp[i2] = i2;
        }
        int i3 = 1;
        int length = a.length();
        if (1 <= length) {
            while (true) {
                int prev = dp[0];
                dp[0] = i3;
                int j = 1;
                if (1 <= m) {
                    while (true) {
                        int tmp = dp[j];
                        dp[j] = Math.min(dp[j] + 1, Math.min(dp[j - 1] + 1, (a.charAt(i3 + (-1)) == b.charAt(j + (-1)) ? 0 : 1) + prev));
                        prev = tmp;
                        if (j == m) {
                            break;
                        }
                        j++;
                    }
                }
                if (i3 == length) {
                    break;
                }
                i3++;
            }
        }
        int i4 = dp[m];
        return i4;
    }
}
