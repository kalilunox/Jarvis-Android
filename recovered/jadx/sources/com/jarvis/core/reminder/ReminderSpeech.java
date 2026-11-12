package com.jarvis.core.reminder;

import com.jarvis.app.VoiceService;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: ReminderBook.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/reminder/ReminderSpeech;", "", "<init>", "()V", "WEEKDAY_FORMS", "", "", "", "whenPhrase", "at", "Ljava/time/LocalDateTime;", "now", "weekdayPhrase", "date", "Ljava/time/LocalDate;", "firing", VoiceService.EXTRA_TEXT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReminderSpeech {
    public static final ReminderSpeech INSTANCE = new ReminderSpeech();
    private static final Map<Integer, String> WEEKDAY_FORMS = MapsKt.mapOf(TuplesKt.to(1, "в понедельник"), TuplesKt.to(2, "во вторник"), TuplesKt.to(3, "в среду"), TuplesKt.to(4, "в четверг"), TuplesKt.to(5, "в пятницу"), TuplesKt.to(6, "в субботу"), TuplesKt.to(7, "в воскресенье"));

    private ReminderSpeech() {
    }

    public final String whenPhrase(LocalDateTime at, LocalDateTime now) {
        String day;
        Intrinsics.checkNotNullParameter(at, "at");
        Intrinsics.checkNotNullParameter(now, "now");
        long minutes = Duration.between(now, at).plusSeconds(30L).toMinutes();
        if ((0 <= minutes && minutes < 60) && Intrinsics.areEqual(at.toLocalDate(), now.toLocalDate())) {
            return minutes == 0 ? "через минуту" : "через " + minutes + " мин";
        }
        String time = String.format("в %d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(at.getHour()), Integer.valueOf(at.getMinute())}, 2));
        Intrinsics.checkNotNullExpressionValue(time, "format(...)");
        long days = ChronoUnit.DAYS.between(now.toLocalDate(), at.toLocalDate());
        if (days == 0) {
            day = "сегодня";
        } else if (days == 1) {
            day = "завтра";
        } else if (days == 2) {
            day = "послезавтра";
        } else {
            if (3 <= days && days < 7) {
                day = (String) MapsKt.getValue(WEEKDAY_FORMS, Integer.valueOf(at.getDayOfWeek().getValue()));
            } else {
                day = at.getDayOfMonth() + StringUtils.SPACE + at.getMonth().getDisplayName(TextStyle.FULL, new Locale("ru"));
            }
        }
        return day + StringUtils.SPACE + time;
    }

    public final String weekdayPhrase(LocalDate date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return (String) MapsKt.getValue(WEEKDAY_FORMS, Integer.valueOf(date.getDayOfWeek().getValue()));
    }

    public final String firing(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return StringsKt.isBlank(text) ? "Сэр, вы просили напомнить в это время." : "Напоминаю, сэр: " + text + ".";
    }
}
