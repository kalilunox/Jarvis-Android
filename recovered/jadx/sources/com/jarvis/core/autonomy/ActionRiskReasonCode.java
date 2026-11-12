package com.jarvis.core.autonomy;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ActionRiskReasonCode.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/autonomy/ActionRiskReasonCode;", "", "<init>", "(Ljava/lang/String;I)V", "NO_SIDE_EFFECT", "READ_ONLY", "LOCAL_REVERSIBLE", "USER_VISIBLE_EFFECT", "DIRECT_STATE_CHANGE", "EXTERNAL_SIDE_EFFECT", "DATA_MUTATION", "DATA_DELETION", "FINANCIAL_EFFECT", "IRREVERSIBLE_ACTION", "UNKNOWN_ACTION", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ActionRiskReasonCode {
    NO_SIDE_EFFECT,
    READ_ONLY,
    LOCAL_REVERSIBLE,
    USER_VISIBLE_EFFECT,
    DIRECT_STATE_CHANGE,
    EXTERNAL_SIDE_EFFECT,
    DATA_MUTATION,
    DATA_DELETION,
    FINANCIAL_EFFECT,
    IRREVERSIBLE_ACTION,
    UNKNOWN_ACTION;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<ActionRiskReasonCode> getEntries() {
        return $ENTRIES;
    }
}
