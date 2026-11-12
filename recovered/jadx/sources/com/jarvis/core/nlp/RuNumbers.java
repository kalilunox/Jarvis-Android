package com.jarvis.core.nlp;

import com.jarvis.core.aioutput.StructuredAiLimits;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RuNumbers.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\n\u001a\u0004\u0018\u00010\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\u0006\u0010\r\u001a\u00020\u0007¢\u0006\u0002\u0010\u000eJ)\u0010\u000f\u001a\u0004\u0018\u00010\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011¢\u0006\u0002\u0010\u0012R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/nlp/RuNumbers;", "", "<init>", "()V", "UNITS", "", "", "", "TENS", "HUNDREDS", "endingAt", "words", "", "end", "(Ljava/util/List;I)Ljava/lang/Integer;", "before", "units", "", "(Ljava/util/List;Ljava/util/Set;)Ljava/lang/Integer;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuNumbers {
    public static final RuNumbers INSTANCE = new RuNumbers();
    private static final Map<String, Integer> UNITS = MapsKt.mapOf(TuplesKt.to("ноль", 0), TuplesKt.to("один", 1), TuplesKt.to("одна", 1), TuplesKt.to("одну", 1), TuplesKt.to("два", 2), TuplesKt.to("две", 2), TuplesKt.to("три", 3), TuplesKt.to("четыре", 4), TuplesKt.to("пять", 5), TuplesKt.to("шесть", 6), TuplesKt.to("семь", 7), TuplesKt.to("восемь", 8), TuplesKt.to("девять", 9), TuplesKt.to("десять", 10), TuplesKt.to("одиннадцать", 11), TuplesKt.to("двенадцать", 12), TuplesKt.to("тринадцать", 13), TuplesKt.to("четырнадцать", 14), TuplesKt.to("пятнадцать", 15), TuplesKt.to("шестнадцать", 16), TuplesKt.to("семнадцать", 17), TuplesKt.to("восемнадцать", 18), TuplesKt.to("девятнадцать", 19));
    private static final Map<String, Integer> TENS = MapsKt.mapOf(TuplesKt.to("двадцать", 20), TuplesKt.to("тридцать", 30), TuplesKt.to("сорок", 40), TuplesKt.to("пятьдесят", 50), TuplesKt.to("шестьдесят", 60), TuplesKt.to("семьдесят", 70), TuplesKt.to("восемьдесят", 80), TuplesKt.to("девяносто", 90));
    private static final Map<String, Integer> HUNDREDS = MapsKt.mapOf(TuplesKt.to("сто", 100), TuplesKt.to("двести", 200), TuplesKt.to("триста", 300), TuplesKt.to("четыреста", Integer.valueOf(StructuredAiLimits.MAX_QUESTION_CHARS)), TuplesKt.to("пятьсот", 500));

    private RuNumbers() {
    }

    public final Integer endingAt(List<String> words, int end) {
        Pair pair;
        Intrinsics.checkNotNullParameter(words, "words");
        String last = (String) CollectionsKt.getOrNull(words, end);
        if (last == null) {
            return null;
        }
        Integer intOrNull = StringsKt.toIntOrNull(last);
        if (intOrNull != null) {
            int it = intOrNull.intValue();
            return Integer.valueOf(it);
        }
        int sum = 0;
        boolean found = false;
        int lastClass = 0;
        for (int i = end; i >= 0; i--) {
            String w = words.get(i);
            if (UNITS.containsKey(w)) {
                pair = TuplesKt.to(MapsKt.getValue(UNITS, w), 1);
            } else if (!TENS.containsKey(w)) {
                if (!HUNDREDS.containsKey(w)) {
                    break;
                }
                pair = TuplesKt.to(MapsKt.getValue(HUNDREDS, w), 3);
            } else {
                pair = TuplesKt.to(MapsKt.getValue(TENS, w), 2);
            }
            int value = ((Number) pair.component1()).intValue();
            int cls = ((Number) pair.component2()).intValue();
            if (cls <= lastClass) {
                break;
            }
            sum += value;
            lastClass = cls;
            found = true;
        }
        if (found) {
            return Integer.valueOf(sum);
        }
        return null;
    }

    public final Integer before(List<String> words, Set<String> units) {
        Intrinsics.checkNotNullParameter(words, "words");
        Intrinsics.checkNotNullParameter(units, "units");
        int index$iv = 0;
        Iterator<String> it = words.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                String it2 = (String) item$iv;
                if (units.contains(it2)) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int idx = index$iv;
        if (idx <= 0) {
            return null;
        }
        return endingAt(words, idx - 1);
    }
}
