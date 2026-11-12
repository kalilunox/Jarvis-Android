package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ProactiveReasonCode.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/proactive/ProactiveReasonCode;", "", "<init>", "(Ljava/lang/String;I)V", "NO_CANDIDATE", "LOW_CONFIDENCE", "LOW_RELEVANCE", "CONTEXT_MISMATCH", "CONTEXT_ALREADY_SATISFIED", "DISABLED", "STALE", "SENSITIVITY_BLOCKED", "BEHAVIOR_COOLDOWN", "FEEDBACK_COOLDOWN", "GLOBAL_COOLDOWN", "BUDGET_EXHAUSTED", "DUPLICATE", "ALREADY_HANDLED", "SELECTED", "EVALUATED", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ProactiveReasonCode {
    NO_CANDIDATE,
    LOW_CONFIDENCE,
    LOW_RELEVANCE,
    CONTEXT_MISMATCH,
    CONTEXT_ALREADY_SATISFIED,
    DISABLED,
    STALE,
    SENSITIVITY_BLOCKED,
    BEHAVIOR_COOLDOWN,
    FEEDBACK_COOLDOWN,
    GLOBAL_COOLDOWN,
    BUDGET_EXHAUSTED,
    DUPLICATE,
    ALREADY_HANDLED,
    SELECTED,
    EVALUATED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<ProactiveReasonCode> getEntries() {
        return $ENTRIES;
    }
}
