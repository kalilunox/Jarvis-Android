package com.jarvis.core.aioutput;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: StructuredAiModels.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiResponseType;", "", "<init>", "(Ljava/lang/String;I)V", "ANSWER", "CLARIFICATION", "TOOL_REQUEST", "PLAN", "NO_ACTION", "CONFIRMATION_REQUIRED", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum StructuredAiResponseType {
    ANSWER,
    CLARIFICATION,
    TOOL_REQUEST,
    PLAN,
    NO_ACTION,
    CONFIRMATION_REQUIRED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<StructuredAiResponseType> getEntries() {
        return $ENTRIES;
    }
}
