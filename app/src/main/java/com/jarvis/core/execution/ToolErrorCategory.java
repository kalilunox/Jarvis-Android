package com.jarvis.core.execution;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ToolError.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/jarvis/core/execution/ToolErrorCategory;", "", "<init>", "(Ljava/lang/String;I)V", "INVALID_INPUT", "UNAVAILABLE", "PERMISSION_FAILURE", "EXECUTION_FAILURE", "TIMEOUT", "CANCELLED", "UNSUPPORTED", "INTERNAL_ERROR", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ToolErrorCategory {
    INVALID_INPUT,
    UNAVAILABLE,
    PERMISSION_FAILURE,
    EXECUTION_FAILURE,
    TIMEOUT,
    CANCELLED,
    UNSUPPORTED,
    INTERNAL_ERROR;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<ToolErrorCategory> getEntries() {
        return $ENTRIES;
    }
}
