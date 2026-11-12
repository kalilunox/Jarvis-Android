package com.jarvis.core.autonomy;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AutonomyReasonCode.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/autonomy/AutonomyReasonCode;", "", "<init>", "(Ljava/lang/String;I)V", "NO_PROACTIVE_DECISION", "PROACTIVE_NO_OP", "NO_PROPOSED_ACTION", "ACTION_NOT_ALLOWED_FOR_SOURCE", "AUTONOMY_LEVEL_OFF", "AUTONOMY_REQUIRES_CONFIRMATION", "USER_BLOCKLISTED", "NOT_IN_ALLOWLIST", "RISK_CLASS_TOO_HIGH", "CONFIDENCE_IRRELEVANT", "CONTEXT_INSUFFICIENT", "MISSING_RISK_ASSESSMENT", "RISK_ASSESSMENT_MISMATCH", "POLICY_ALLOW", "POLICY_DENY", "SAFETY_GATE_DENY", "SAFETY_GATE_REQUIRE_CONFIRMATION", "NOT_IMPLEMENTED", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum AutonomyReasonCode {
    NO_PROACTIVE_DECISION,
    PROACTIVE_NO_OP,
    NO_PROPOSED_ACTION,
    ACTION_NOT_ALLOWED_FOR_SOURCE,
    AUTONOMY_LEVEL_OFF,
    AUTONOMY_REQUIRES_CONFIRMATION,
    USER_BLOCKLISTED,
    NOT_IN_ALLOWLIST,
    RISK_CLASS_TOO_HIGH,
    CONFIDENCE_IRRELEVANT,
    CONTEXT_INSUFFICIENT,
    MISSING_RISK_ASSESSMENT,
    RISK_ASSESSMENT_MISMATCH,
    POLICY_ALLOW,
    POLICY_DENY,
    SAFETY_GATE_DENY,
    SAFETY_GATE_REQUIRE_CONFIRMATION,
    NOT_IMPLEMENTED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<AutonomyReasonCode> getEntries() {
        return $ENTRIES;
    }
}
