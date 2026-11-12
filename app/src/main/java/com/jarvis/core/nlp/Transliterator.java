package com.jarvis.core.nlp;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* JADX INFO: compiled from: Transliterator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\f\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/nlp/Transliterator;", "", "<init>", "()V", "toLatin", "", "value", "normalize", "compareKey", "stripEnding", "base", "RU_ENDINGS", "", "BRAND_ALIASES", "", "CYRILLIC_TO_LATIN", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Transliterator {
    private static final Map<Character, String> CYRILLIC_TO_LATIN;
    public static final Transliterator INSTANCE = new Transliterator();
    private static final List<String> RU_ENDINGS = CollectionsKt.listOf((Object[]) new String[]{"ами", "ями", "ого", "его", "ому", "ему", "ыми", "ими", "ей", "ой", "ам", "ям", "ах", "ях", "ом", "ем", "ов", "ев", "ий", "ый", "ая", "ое", "ые", "а", "я", "е", "и", "у", "ю", "ы", "о", "ь"});
    private static final Map<String, String> BRAND_ALIASES = MapsKt.mapOf(TuplesKt.to("yutub", "youtube"), TuplesKt.to("vatsap", "whatsapp"), TuplesKt.to("votsap", "whatsapp"), TuplesKt.to("feysbuk", "facebook"), TuplesKt.to("vkontakte", "vk"), TuplesKt.to("gugl", "google"), TuplesKt.to("tvitter", "twitter"), TuplesKt.to("kamer", "camera"), TuplesKt.to("galere", "gallery"), TuplesKt.to("nastroyk", "settings"), TuplesKt.to("nastroek", "settings"));

    private Transliterator() {
    }

    public final String toLatin(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        StringBuilder sb = new StringBuilder(value.length());
        String lowerCase = value.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        int length = lowerCase.length();
        for (int i = 0; i < length; i++) {
            char ch = lowerCase.charAt(i);
            String strValueOf = CYRILLIC_TO_LATIN.get(Character.valueOf(ch));
            if (strValueOf == null) {
                strValueOf = Character.valueOf(ch);
            }
            sb.append(strValueOf);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final String normalize(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Regex("[^\\p{L}\\p{N}]").replace(toLatin(value), "");
    }

    public final String compareKey(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        String lowerCase = value.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String base = new Regex("[^\\p{L}\\p{N}]").replace(lowerCase, "");
        String normalized = normalize(stripEnding(base));
        String str = BRAND_ALIASES.get(normalized);
        return str == null ? normalized : str;
    }

    private final String stripEnding(String base) {
        for (String e : RU_ENDINGS) {
            if (base.length() > e.length() + 2 && StringsKt.endsWith$default(base, e, false, 2, (Object) null)) {
                return StringsKt.dropLast(base, e.length());
            }
        }
        return base;
    }

    static {
        Map $this$CYRILLIC_TO_LATIN_u24lambda_u240 = MapsKt.createMapBuilder();
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1072, "a");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1073, "b");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1074, "v");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1075, "g");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1076, "d");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1077, "e");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1105, "e");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1078, "zh");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1079, CompressorStreamFactory.Z);
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1080, "i");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1081, "y");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1082, "k");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1083, "l");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1084, "m");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1085, "n");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1086, "o");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1087, "p");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1088, "r");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1089, "s");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1090, "t");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1091, "u");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1092, "f");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1093, "h");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1094, "ts");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1095, "ch");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1096, "sh");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1097, "sch");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1098, "");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1099, "y");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1100, "");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1101, "e");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1102, "yu");
        $this$CYRILLIC_TO_LATIN_u24lambda_u240.put((char) 1103, "ya");
        CYRILLIC_TO_LATIN = MapsKt.build($this$CYRILLIC_TO_LATIN_u24lambda_u240);
    }
}
