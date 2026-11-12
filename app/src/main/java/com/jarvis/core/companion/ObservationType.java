package com.jarvis.core.companion;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ObservationEnums.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0016\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/companion/ObservationType;", "", "<init>", "(Ljava/lang/String;I)V", "SCREEN_ON", "SCREEN_OFF", "DEVICE_UNLOCKED", "DEVICE_LOCKED", "BATTERY_CHANGED", "CHARGER_CONNECTED", "CHARGER_DISCONNECTED", "HEADPHONES_CONNECTED", "HEADPHONES_DISCONNECTED", "NETWORK_CHANGED", "APP_FOREGROUND", "APP_BACKGROUND", "WINDOW_CHANGED", "NOTIFICATION_POSTED", "NOTIFICATION_REMOVED", "USER_INTERACTION", "VOICE_REQUEST", "TOOL_EXECUTED", "UNKNOWN", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ObservationType {
    SCREEN_ON,
    SCREEN_OFF,
    DEVICE_UNLOCKED,
    DEVICE_LOCKED,
    BATTERY_CHANGED,
    CHARGER_CONNECTED,
    CHARGER_DISCONNECTED,
    HEADPHONES_CONNECTED,
    HEADPHONES_DISCONNECTED,
    NETWORK_CHANGED,
    APP_FOREGROUND,
    APP_BACKGROUND,
    WINDOW_CHANGED,
    NOTIFICATION_POSTED,
    NOTIFICATION_REMOVED,
    USER_INTERACTION,
    VOICE_REQUEST,
    TOOL_EXECUTED,
    UNKNOWN;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<ObservationType> getEntries() {
        return $ENTRIES;
    }
}
