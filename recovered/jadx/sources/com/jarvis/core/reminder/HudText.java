package com.jarvis.core.reminder;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HudText.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/reminder/HudText;", "", "<init>", "()V", "MAX_TEXT", "", "next", "", "reminders", "", "Lcom/jarvis/core/reminder/Reminder;", "now", "Ljava/time/LocalDateTime;", "status", "listening", "", "batteryPercent", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HudText {
    public static final HudText INSTANCE = new HudText();
    private static final int MAX_TEXT = 40;

    private HudText() {
    }

    public final String next(List<Reminder> reminders, LocalDateTime now) {
        Object minElem$iv;
        Intrinsics.checkNotNullParameter(reminders, "reminders");
        Intrinsics.checkNotNullParameter(now, "now");
        List<Reminder> $this$filter$iv = reminders;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Reminder it = (Reminder) element$iv$iv;
            if (it.getAt().compareTo((ChronoLocalDateTime<?>) now) > 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$minByOrNull$iv = (List) destination$iv$iv;
        Iterator iterator$iv = $this$minByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            minElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Reminder it2 = (Reminder) minElem$iv;
                Comparable minValue$iv = it2.getAt();
                do {
                    Object e$iv = iterator$iv.next();
                    Reminder it3 = (Reminder) e$iv;
                    LocalDateTime at = it3.getAt();
                    if (minValue$iv.compareTo(at) > 0) {
                        minElem$iv = e$iv;
                        minValue$iv = at;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            minElem$iv = null;
        }
        Reminder nearest = (Reminder) minElem$iv;
        if (nearest == null) {
            return "Напоминаний нет";
        }
        String moment = ReminderSpeech.INSTANCE.whenPhrase(nearest.getAt(), now);
        String what = StringsKt.take(nearest.getText(), 40);
        return (StringsKt.isBlank(what) ? new StringBuilder().append("Далее: ").append(moment) : new StringBuilder().append("Далее: ").append(moment).append(" · ").append(what)).toString();
    }

    public final String status(boolean listening, int batteryPercent) {
        return (listening ? "Слушаю" : "Отключён") + " · заряд " + batteryPercent + "%";
    }
}
