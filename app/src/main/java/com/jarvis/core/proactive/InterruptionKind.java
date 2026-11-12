package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.apache.commons.codec.language.bm.Rule;

/* JADX INFO: compiled from: PhoneStateSnapshot.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/jarvis/core/proactive/InterruptionKind;", "", "<init>", "(Ljava/lang/String;I)V", Rule.ALL, "PRIORITY", "ALARMS", "NONE", "UNKNOWN", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum InterruptionKind {
    ALL,
    PRIORITY,
    ALARMS,
    NONE,
    UNKNOWN;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<InterruptionKind> getEntries() {
        return $ENTRIES;
    }
}
