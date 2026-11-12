package com.jarvis.core.autonomy;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AutonomyAuditLog.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/autonomy/AutonomyAuditLog;", "", "<init>", "()V", "CAPACITY", "", "entries", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/jarvis/core/autonomy/AutonomyAuditEntry;", "record", "", "verdict", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "stage", "", "recent", "", "limit", "clearForTests", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AutonomyAuditLog {
    private static final int CAPACITY = 64;
    public static final AutonomyAuditLog INSTANCE = new AutonomyAuditLog();
    private static final CopyOnWriteArrayList<AutonomyAuditEntry> entries = new CopyOnWriteArrayList<>();

    private AutonomyAuditLog() {
    }

    public static /* synthetic */ void record$default(AutonomyAuditLog autonomyAuditLog, AutonomyVerdict autonomyVerdict, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "gate";
        }
        autonomyAuditLog.record(autonomyVerdict, str);
    }

    public final void record(AutonomyVerdict verdict, String stage) {
        ProposedAction proposedAction;
        Intrinsics.checkNotNullParameter(verdict, "verdict");
        Intrinsics.checkNotNullParameter(stage, "stage");
        ActionRequest actionRequest = verdict.getActionRequest();
        String actionId = (actionRequest == null || (proposedAction = actionRequest.getProposedAction()) == null) ? null : proposedAction.getActionId();
        AutonomyAuditEntry entry = new AutonomyAuditEntry(System.currentTimeMillis(), stage, verdict.getType(), verdict.getReasonCode(), actionId, CollectionsKt.joinToString$default(CollectionsKt.take(verdict.getNotes(), 4), ";", null, null, 0, null, null, 62, null));
        entries.add(entry);
        while (entries.size() > 64) {
            entries.remove(0);
        }
    }

    public static /* synthetic */ List recent$default(AutonomyAuditLog autonomyAuditLog, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 16;
        }
        return autonomyAuditLog.recent(i);
    }

    public final List<AutonomyAuditEntry> recent(int limit) {
        return CollectionsKt.takeLast(entries, RangesKt.coerceIn(limit, 1, 64));
    }

    public final void clearForTests() {
        entries.clear();
    }
}
