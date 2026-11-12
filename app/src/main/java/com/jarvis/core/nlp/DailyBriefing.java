package com.jarvis.core.nlp;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: DailyBriefing.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005J>\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/nlp/DailyBriefing;", "", "<init>", "()V", "NIGHT_END", "", "MORNING_END", "DAY_END", "greeting", "", "hour", "compose", "minute", "weather", "events", "reminders", "status", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DailyBriefing {
    private static final int DAY_END = 18;
    public static final DailyBriefing INSTANCE = new DailyBriefing();
    private static final int MORNING_END = 12;
    private static final int NIGHT_END = 5;

    private DailyBriefing() {
    }

    public final String greeting(int hour) {
        if (hour < 5) {
            return "Доброй ночи, сэр.";
        }
        if (hour < 12) {
            return "Доброе утро, сэр.";
        }
        return hour < 18 ? "Добрый день, сэр." : "Добрый вечер, сэр.";
    }

    public final String compose(int hour, int minute, String weather, String events, String reminders, String status) {
        List $this$compose_u24lambda_u240 = CollectionsKt.createListBuilder();
        $this$compose_u24lambda_u240.add(INSTANCE.greeting(hour));
        String str = String.format("Сейчас %d:%02d.", Arrays.copyOf(new Object[]{Integer.valueOf(hour), Integer.valueOf(minute)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        $this$compose_u24lambda_u240.add(str);
        $this$compose_u24lambda_u240.add(weather == null ? "Погоду получить не удалось." : weather);
        if (events != null) {
            $this$compose_u24lambda_u240.add(events);
        }
        if (reminders != null) {
            String it = !StringsKt.startsWith$default(reminders, "Напоминаний нет", false, 2, (Object) null) ? reminders : null;
            if (it != null) {
                $this$compose_u24lambda_u240.add(it);
            }
        }
        if (status != null) {
            String it2 = StringsKt.startsWith$default(status, "Внимание", false, 2, (Object) null) ? status : null;
            if (it2 != null) {
                $this$compose_u24lambda_u240.add(StringsKt.substringBefore$default(it2, ". ", (String) null, 2, (Object) null) + ".");
            }
        }
        List parts = CollectionsKt.build($this$compose_u24lambda_u240);
        return CollectionsKt.joinToString$default(parts, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }
}
