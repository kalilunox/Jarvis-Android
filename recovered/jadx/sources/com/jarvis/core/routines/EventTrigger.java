package com.jarvis.core.routines;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: EventRoutines.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/routines/EventTrigger;", "", "title", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "HEADPHONES_ON", "HEADPHONES_OFF", "CHARGER_ON", "CHARGER_OFF", "WIFI_ON", "WIFI_OFF", "UNLOCK", "APP_OPEN", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum EventTrigger {
    HEADPHONES_ON("подключены наушники"),
    HEADPHONES_OFF("отключены наушники"),
    CHARGER_ON("поставлен на зарядку"),
    CHARGER_OFF("снят с зарядки"),
    WIFI_ON("подключён вайфай"),
    WIFI_OFF("отключён вайфай"),
    UNLOCK("телефон разблокирован"),
    APP_OPEN("открыто приложение");

    private final String title;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<EventTrigger> getEntries() {
        return $ENTRIES;
    }

    EventTrigger(String title) {
        this.title = title;
    }

    public final String getTitle() {
        return this.title;
    }
}
