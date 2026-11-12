package com.jarvis.core.runtime.semantic;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: SemanticModels.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/runtime/semantic/AmbiguityLevel;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "LOW", "MEDIUM", "HIGH", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum AmbiguityLevel {
    NONE,
    LOW,
    MEDIUM,
    HIGH;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<AmbiguityLevel> getEntries() {
        return $ENTRIES;
    }
}
