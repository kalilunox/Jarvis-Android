package com.jarvis.core.nlp;

import com.jarvis.app.VoiceService;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: JarvisStyle.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/nlp/JarvisStyle;", "", "<init>", "()V", "MAX_CONFIRMATION", "", "PHRASES", "", "", "LISTENING", "", "confirmation", VoiceService.EXTRA_TEXT, "listening", "turn", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisStyle {
    private static final int MAX_CONFIRMATION = 48;
    public static final JarvisStyle INSTANCE = new JarvisStyle();
    private static final Map<String, String> PHRASES = MapsKt.mapOf(TuplesKt.to("Включён.", "Фонарик включён, сэр."), TuplesKt.to("Выключен.", "Фонарик выключен, сэр."), TuplesKt.to("Открыл.", "Открываю, сэр."), TuplesKt.to("Играю.", "Воспроизвожу, сэр."), TuplesKt.to("Дальше.", "Следующий трек, сэр."), TuplesKt.to("Назад.", "Предыдущий трек, сэр."), TuplesKt.to("Максимум.", "Громкость на максимуме, сэр."), TuplesKt.to("Без звука.", "Звук отключён, сэр."));
    private static final List<String> LISTENING = CollectionsKt.listOf((Object[]) new String[]{"Слушаю, сэр.", "Да, сэр?", "К вашим услугам.", "Слушаю вас, сэр."});

    private JarvisStyle() {
    }

    public final String confirmation(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String t = StringsKt.trim((CharSequence) text).toString();
        String it = PHRASES.get(t);
        if (it != null) {
            return it;
        }
        if (t.length() > 48 || !StringsKt.endsWith$default(t, ".", false, 2, (Object) null) || StringsKt.contains((CharSequence) t, (CharSequence) "сэр", true)) {
            return t;
        }
        return StringsKt.dropLast(t, 1) + ", сэр.";
    }

    public final String listening(int turn) {
        return LISTENING.get(Math.floorMod(turn, LISTENING.size()));
    }
}
