package com.jarvis.core.notes;

import com.jarvis.app.VoiceService;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UserData.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/notes/Places;", "", "<init>", "()V", "HOME", "", "WORK", "SAVE_HOME", "Lkotlin/text/Regex;", "SAVE_WORK", "DESTINATIONS", "", "saveRequest", "Lkotlin/Pair;", VoiceService.EXTRA_TEXT, "keyOf", "destination", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Places {
    public static final String HOME = "home";
    public static final Places INSTANCE = new Places();
    private static final Regex SAVE_HOME = new Regex("^(?:запомни |сохрани |запиши )?(?:мой )?(?:домашний адрес|адрес дома)\\s+(.+)$");
    private static final Regex SAVE_WORK = new Regex("^(?:запомни |сохрани |запиши )?(?:мой )?(?:рабочий адрес|адрес работы)\\s+(.+)$");
    public static final String WORK = "work";
    private static final Map<String, String> DESTINATIONS = MapsKt.mapOf(TuplesKt.to("домой", "home"), TuplesKt.to("дом", "home"), TuplesKt.to("дому", "home"), TuplesKt.to("дома", "home"), TuplesKt.to("работу", WORK), TuplesKt.to("работы", WORK), TuplesKt.to("работа", WORK), TuplesKt.to("работе", WORK));

    private Places() {
    }

    public final Pair<String, String> saveRequest(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '.', '!');
        MatchResult it = Regex.find$default(SAVE_HOME, t, 0, 2, null);
        if (it != null) {
            return TuplesKt.to("home", it.getGroupValues().get(1));
        }
        MatchResult it2 = Regex.find$default(SAVE_WORK, t, 0, 2, null);
        if (it2 != null) {
            return TuplesKt.to(WORK, it2.getGroupValues().get(1));
        }
        return null;
    }

    public final String keyOf(String destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        Map<String, String> map = DESTINATIONS;
        String lowerCase = destination.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return map.get(StringsKt.trim((CharSequence) lowerCase).toString());
    }
}
