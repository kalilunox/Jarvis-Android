package com.jarvis.core.nlp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: ContactPhraseNormalizer.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\"\n\u0000\n\u0002\u0010$\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/nlp/ContactPhraseNormalizer;", "", "<init>", "()V", "canonical", "", "spoken", "searchQueries", "", "toNominativeNoun", "word", "toNominativeAdjective", "matchCase", "sample", "value", "HONORIFICS", "", "KNOWN_OBLIQUE", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ContactPhraseNormalizer {
    public static final ContactPhraseNormalizer INSTANCE = new ContactPhraseNormalizer();
    private static final Set<String> HONORIFICS = SetsKt.setOf((Object[]) new String[]{"любимой", "любимый", "любимая", "любимого", "любимую", "мой", "моя", "моей", "моему", "моего", "мою", "дорогой", "дорогая", "дорогому", "дорогую", "милый", "милая", "милому", "милую", "родной", "родная", "родному"});
    private static final Map<String, String> KNOWN_OBLIQUE = MapsKt.mapOf(TuplesKt.to("жене", "жена"), TuplesKt.to("женой", "жена"), TuplesKt.to("жены", "жена"), TuplesKt.to("жену", "жена"), TuplesKt.to("маме", "мама"), TuplesKt.to("мамой", "мама"), TuplesKt.to("мамы", "мама"), TuplesKt.to("маму", "мама"), TuplesKt.to("мами", "мама"), TuplesKt.to("папе", "папа"), TuplesKt.to("папой", "папа"), TuplesKt.to("папы", "папа"), TuplesKt.to("папу", "папа"), TuplesKt.to("брату", "брат"), TuplesKt.to("брата", "брат"), TuplesKt.to("братом", "брат"), TuplesKt.to("сестре", "сестра"), TuplesKt.to("сестру", "сестра"), TuplesKt.to("сестрой", "сестра"), TuplesKt.to("другу", "друг"), TuplesKt.to("друга", "друг"), TuplesKt.to("другом", "друг"), TuplesKt.to("подруге", "подруга"), TuplesKt.to("подругу", "подруга"), TuplesKt.to("подругой", "подруга"), TuplesKt.to("мужу", "муж"), TuplesKt.to("мужа", "муж"), TuplesKt.to("мужем", "муж"), TuplesKt.to("сыну", "сын"), TuplesKt.to("сына", "сын"), TuplesKt.to("сыном", "сын"), TuplesKt.to("дочери", "дочь"), TuplesKt.to("дочку", "дочь"), TuplesKt.to("дочерью", "дочь"), TuplesKt.to("бабушке", "бабушка"), TuplesKt.to("бабушку", "бабушка"), TuplesKt.to("дедушке", "дедушка"), TuplesKt.to("дедушку", "дедушка"), TuplesKt.to("коллеге", "коллега"), TuplesKt.to("коллегу", "коллега"), TuplesKt.to("начальнику", "начальник"), TuplesKt.to("начальника", "начальник"), TuplesKt.to("избранному", "Избранное"), TuplesKt.to("избранной", "Избранное"));

    private ContactPhraseNormalizer() {
    }

    public final String canonical(String spoken) {
        Intrinsics.checkNotNullParameter(spoken, "spoken");
        String str = (String) CollectionsKt.firstOrNull((List) searchQueries(spoken));
        if (str == null) {
            str = "";
        }
        String string = str;
        if (StringsKt.isBlank(string)) {
            string = StringsKt.trim((CharSequence) spoken).toString();
        }
        return string;
    }

    public final List<String> searchQueries(String spoken) {
        Intrinsics.checkNotNullParameter(spoken, "spoken");
        String raw = StringsKt.trim((CharSequence) spoken).toString();
        if (StringsKt.isBlank(raw)) {
            return CollectionsKt.emptyList();
        }
        Iterable $this$filter$iv = new Regex("\\s+").split(raw, 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List<String> parts = (List) destination$iv$iv;
        if (parts.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List honorifics = new ArrayList();
        List core = new ArrayList();
        for (String p : parts) {
            Set<String> set = HONORIFICS;
            String lowerCase = p.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (set.contains(lowerCase)) {
                honorifics.add(toNominativeAdjective(p));
            } else {
                core.add(toNominativeNoun(p));
            }
        }
        if (core.isEmpty() && !honorifics.isEmpty()) {
            return CollectionsKt.listOf(raw);
        }
        String corePhrase = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(core, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        String withHonorific = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.plus((Collection) honorifics, (Iterable) core), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        String last = (String) CollectionsKt.lastOrNull(core);
        if (last == null) {
            last = "";
        }
        List $this$searchQueries_u24lambda_u241 = CollectionsKt.createListBuilder();
        if (!StringsKt.isBlank(corePhrase)) {
            $this$searchQueries_u24lambda_u241.add(corePhrase);
        }
        if (!StringsKt.isBlank(last) && !Intrinsics.areEqual(last, corePhrase)) {
            $this$searchQueries_u24lambda_u241.add(last);
        }
        if (!StringsKt.isBlank(withHonorific) && !Intrinsics.areEqual(withHonorific, corePhrase)) {
            $this$searchQueries_u24lambda_u241.add(withHonorific);
        }
        if (!Intrinsics.areEqual(raw, corePhrase) && !Intrinsics.areEqual(raw, withHonorific)) {
            $this$searchQueries_u24lambda_u241.add(raw);
        }
        return CollectionsKt.distinct(CollectionsKt.build($this$searchQueries_u24lambda_u241));
    }

    public final String toNominativeNoun(String word) {
        Intrinsics.checkNotNullParameter(word, "word");
        String lower = word.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        String it = KNOWN_OBLIQUE.get(lower);
        if (it != null) {
            return INSTANCE.matchCase(word, it);
        }
        if (StringsKt.endsWith$default(lower, "у", false, 2, (Object) null) && lower.length() >= 4) {
            return matchCase(word, StringsKt.dropLast(lower, 1));
        }
        if (StringsKt.endsWith$default(lower, "ю", false, 2, (Object) null) && lower.length() >= 4) {
            return matchCase(word, StringsKt.dropLast(lower, 1) + "я");
        }
        if (StringsKt.endsWith$default(lower, "е", false, 2, (Object) null)) {
            int length = lower.length();
            if ((4 <= length && length < 11) && !StringsKt.endsWith$default(lower, "ое", false, 2, (Object) null) && !StringsKt.endsWith$default(lower, "ее", false, 2, (Object) null) && !StringsKt.endsWith$default(lower, "ие", false, 2, (Object) null) && !StringsKt.endsWith$default(lower, "ье", false, 2, (Object) null)) {
                String stem = StringsKt.dropLast(lower, 1);
                if (StringsKt.endsWith$default(stem, "ь", false, 2, (Object) null)) {
                    return matchCase(word, StringsKt.dropLast(stem, 1) + "я");
                }
                return matchCase(word, stem + "а");
            }
        }
        if (StringsKt.endsWith$default(lower, "ой", false, 2, (Object) null) || StringsKt.endsWith$default(lower, "ей", false, 2, (Object) null)) {
            return toNominativeAdjective(word);
        }
        return word;
    }

    public final String toNominativeAdjective(String word) {
        Intrinsics.checkNotNullParameter(word, "word");
        String lower = word.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        if (StringsKt.endsWith$default(lower, "ой", false, 2, (Object) null) && lower.length() > 3) {
            return matchCase(word, StringsKt.dropLast(lower, 2) + "ая");
        }
        if (StringsKt.endsWith$default(lower, "ей", false, 2, (Object) null) && lower.length() > 3) {
            return matchCase(word, StringsKt.dropLast(lower, 2) + "ая");
        }
        if (StringsKt.endsWith$default(lower, "ому", false, 2, (Object) null) && lower.length() > 4) {
            return matchCase(word, StringsKt.dropLast(lower, 3) + "ый");
        }
        if (StringsKt.endsWith$default(lower, "ему", false, 2, (Object) null) && lower.length() > 4) {
            return matchCase(word, StringsKt.dropLast(lower, 3) + "ий");
        }
        return word;
    }

    private final String matchCase(String sample, String value) {
        if (!(sample.length() == 0)) {
            if (!(value.length() == 0)) {
                if (Character.isUpperCase(sample.charAt(0))) {
                    if (value.length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        char it = value.charAt(0);
                        String strValueOf = String.valueOf(it);
                        Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
                        String upperCase = strValueOf.toUpperCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                        StringBuilder sbAppend = sb.append((Object) upperCase);
                        String strSubstring = value.substring(1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        return sbAppend.append(strSubstring).toString();
                    }
                }
                return value;
            }
        }
        return value;
    }
}
