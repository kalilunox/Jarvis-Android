package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AiTaskProfile.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/jarvis/brain/aiprovider/QuotaState;", "", "<init>", "(Ljava/lang/String;I)V", "AVAILABLE", "NEAR_LIMIT", "RATE_LIMITED", "EXHAUSTED", "UNKNOWN", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum QuotaState {
    AVAILABLE,
    NEAR_LIMIT,
    RATE_LIMITED,
    EXHAUSTED,
    UNKNOWN;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<QuotaState> getEntries() {
        return $ENTRIES;
    }
}
