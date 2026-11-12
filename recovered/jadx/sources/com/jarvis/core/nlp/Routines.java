package com.jarvis.core.nlp;

import com.jarvis.app.VoiceService;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: Routines.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/nlp/Routines;", "", "<init>", "()V", "SLEEP", "Lcom/jarvis/core/nlp/Routine;", "MOVIE", "FOCUS", "LEAVING", "MORNING", "PHRASES", "", "", "find", VoiceService.EXTRA_TEXT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Routines {
    public static final Routines INSTANCE = new Routines();
    private static final Routine SLEEP = new Routine(CollectionsKt.listOf((Object[]) new String[]{"останови музыку", "выключи фонарик", "яркость на минимум", "включи режим не беспокоить"}), "Спокойной ночи, сэр.", null, 4, null);
    private static final Routine MOVIE = new Routine(CollectionsKt.listOf((Object[]) new String[]{"останови музыку", "яркость на тридцать", "включи режим не беспокоить"}), "Приятного просмотра, сэр.", null, 4, null);
    private static final Routine FOCUS = new Routine(CollectionsKt.listOf((Object[]) new String[]{"останови музыку", "включи режим не беспокоить"}), "Вас не потревожат, сэр.", null, 4, null);
    private static final Routine LEAVING = new Routine(CollectionsKt.listOf((Object[]) new String[]{"останови музыку", "выключи фонарик"}), "Хорошего пути, сэр.", "погода");
    private static final Routine MORNING = new Routine(CollectionsKt.listOf((Object[]) new String[]{"выключи режим не беспокоить", "яркость на шестьдесят", "сводка на день"}), "", null, 4, null);
    private static final Map<String, Routine> PHRASES = MapsKt.mapOf(TuplesKt.to("режим сна", SLEEP), TuplesKt.to("я ложусь спать", SLEEP), TuplesKt.to("иду спать", SLEEP), TuplesKt.to("пора спать", SLEEP), TuplesKt.to("я спать", SLEEP), TuplesKt.to("режим кино", MOVIE), TuplesKt.to("киносеанс", MOVIE), TuplesKt.to("смотрю фильм", MOVIE), TuplesKt.to("режим работы", FOCUS), TuplesKt.to("режим концентрации", FOCUS), TuplesKt.to("мне нужно сосредоточиться", FOCUS), TuplesKt.to("я ухожу", LEAVING), TuplesKt.to("я ушёл", LEAVING), TuplesKt.to("я ушел", LEAVING), TuplesKt.to("режим ухода", LEAVING), TuplesKt.to("я проснулся", MORNING), TuplesKt.to("я встал", MORNING), TuplesKt.to("режим дня", MORNING));

    private Routines() {
    }

    public final Routine find(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String spoken = StringsKt.trim(StringsKt.removePrefix(StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '.', '!', '?', Typography.ellipsis), (CharSequence) "джарвис"), ' ', ',');
        return PHRASES.get(StringsKt.trim(StringsKt.removeSuffix(StringsKt.removeSuffix(spoken, (CharSequence) ", сэр"), (CharSequence) " пожалуйста"), ' ', ','));
    }
}
