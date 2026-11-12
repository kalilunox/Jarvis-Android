package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: PhoneStateSnapshot.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/proactive/DeliveryReason;", "", "<init>", "(Ljava/lang/String;I)V", "NO_OP", "NOTIFICATIONS_DISABLED", "JARVIS_SESSION_ACTIVE", "JARVIS_SPEAKING", "CALL_ACTIVE", "RINGER_SILENT", "RINGER_VIBRATE", "DND_PRIORITY", "DND_ALARMS", "DND_NONE", "DND_UNKNOWN", "VOICE_UNAVAILABLE", "READY", "DUPLICATE", "SNOOZED", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum DeliveryReason {
    NO_OP,
    NOTIFICATIONS_DISABLED,
    JARVIS_SESSION_ACTIVE,
    JARVIS_SPEAKING,
    CALL_ACTIVE,
    RINGER_SILENT,
    RINGER_VIBRATE,
    DND_PRIORITY,
    DND_ALARMS,
    DND_NONE,
    DND_UNKNOWN,
    VOICE_UNAVAILABLE,
    READY,
    DUPLICATE,
    SNOOZED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<DeliveryReason> getEntries() {
        return $ENTRIES;
    }
}
