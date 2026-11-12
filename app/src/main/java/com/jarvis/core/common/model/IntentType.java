package com.jarvis.core.common.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: Intent.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b/\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/¨\u00060"}, d2 = {"Lcom/jarvis/core/common/model/IntentType;", "", "<init>", "(Ljava/lang/String;I)V", "FLASHLIGHT", "VOLUME", "BRIGHTNESS", "TIMER", "CREATE_EVENT", "SEND_SMS", "SEND_TELEGRAM", "LAUNCH_APP", "SYSTEM_SETTING", "CALL", "CONTACTS", "FILES", "UI_CLICK", "UI_TYPE", "UI_SWIPE", "UI_GLOBAL", "UI_READ", "UI_WAIT", "NOTIFICATIONS", "CLIPBOARD", "SCREENSHOT", "WEATHER", "SYSTEM_STATUS", "REMINDER", "NEWS", "RATES", "LOCATION", "PHONE_CONTROL", "ANNOUNCE_SETTINGS", "NOTES", "PLACES", "CUSTOM_ROUTINE", "APP_ACTION", "WEB_ANSWER", "APP_AGENT", "FORGET", "BRIEFING", "NAVIGATE", "WEB_SEARCH", "CAMERA", "MUSIC", "KNOWLEDGE", "CHAT", "UNKNOWN", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum IntentType {
    FLASHLIGHT,
    VOLUME,
    BRIGHTNESS,
    TIMER,
    CREATE_EVENT,
    SEND_SMS,
    SEND_TELEGRAM,
    LAUNCH_APP,
    SYSTEM_SETTING,
    CALL,
    CONTACTS,
    FILES,
    UI_CLICK,
    UI_TYPE,
    UI_SWIPE,
    UI_GLOBAL,
    UI_READ,
    UI_WAIT,
    NOTIFICATIONS,
    CLIPBOARD,
    SCREENSHOT,
    WEATHER,
    SYSTEM_STATUS,
    REMINDER,
    NEWS,
    RATES,
    LOCATION,
    PHONE_CONTROL,
    ANNOUNCE_SETTINGS,
    NOTES,
    PLACES,
    CUSTOM_ROUTINE,
    APP_ACTION,
    WEB_ANSWER,
    APP_AGENT,
    FORGET,
    BRIEFING,
    NAVIGATE,
    WEB_SEARCH,
    CAMERA,
    MUSIC,
    KNOWLEDGE,
    CHAT,
    UNKNOWN;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<IntentType> getEntries() {
        return $ENTRIES;
    }
}
