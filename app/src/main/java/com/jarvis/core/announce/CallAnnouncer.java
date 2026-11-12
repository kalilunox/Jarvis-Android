package com.jarvis.core.announce;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Announcements.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¨\u0006\u0007"}, d2 = {"Lcom/jarvis/core/announce/CallAnnouncer;", "", "<init>", "()V", "phrase", "", "contactName", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallAnnouncer {
    public static final CallAnnouncer INSTANCE = new CallAnnouncer();

    private CallAnnouncer() {
    }

    public final String phrase(String contactName) {
        String str = contactName;
        return str == null || StringsKt.isBlank(str) ? "Сэр, звонок с неизвестного номера." : "Сэр, звонит " + contactName + ".";
    }
}
