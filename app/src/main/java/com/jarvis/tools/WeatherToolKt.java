package com.jarvis.tools;

import com.jarvis.core.reminder.ReminderSpeech;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: WeatherTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aB\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\t\u001a\u00020\nH\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"weekSummary", "", "place", "lows", "", "", "highs", "codes", "", "today", "Ljava/time/LocalDate;", "FIRST_PRECIPITATION_CODE", "tools_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class WeatherToolKt {
    private static final int FIRST_PRECIPITATION_CODE = 51;

    public static final String weekSummary(String place, List<Double> lows, List<Double> highs, List<Integer> codes, LocalDate today) {
        String strWeekdayPhrase;
        Intrinsics.checkNotNullParameter(place, "place");
        Intrinsics.checkNotNullParameter(lows, "lows");
        Intrinsics.checkNotNullParameter(highs, "highs");
        Intrinsics.checkNotNullParameter(codes, "codes");
        Intrinsics.checkNotNullParameter(today, "today");
        Iterable $this$filter$iv = CollectionsKt.getIndices(codes);
        Collection destination$iv$iv = new ArrayList();
        for (Integer num : $this$filter$iv) {
            if (codes.get(num.intValue()).intValue() >= 51) {
                destination$iv$iv.add(num);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        int $i$f$map = 0;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            int it = ((Number) item$iv$iv).intValue();
            switch (it) {
                case 0:
                    strWeekdayPhrase = "сегодня";
                    break;
                case 1:
                    strWeekdayPhrase = "завтра";
                    break;
                default:
                    ReminderSpeech reminderSpeech = ReminderSpeech.INSTANCE;
                    LocalDate localDatePlusDays = today.plusDays(it);
                    Intrinsics.checkNotNullExpressionValue(localDatePlusDays, "plusDays(...)");
                    strWeekdayPhrase = reminderSpeech.weekdayPhrase(localDatePlusDays);
                    break;
            }
            destination$iv$iv2.add(strWeekdayPhrase);
            $this$map$iv = $this$map$iv;
            $i$f$map = $i$f$map;
        }
        List wet = (List) destination$iv$iv2;
        String rain = wet.isEmpty() ? "Без осадков" : "Осадки " + CollectionsKt.joinToString$default(wet, ", ", null, null, 0, null, null, 62, null);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%s на неделю: от %.0f° до %.0f°. %s, сэр.", Arrays.copyOf(new Object[]{place, Double.valueOf(CollectionsKt.minOrThrow((Iterable<Double>) lows)), Double.valueOf(CollectionsKt.maxOrThrow((Iterable<Double>) highs)), rain}, 4));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
