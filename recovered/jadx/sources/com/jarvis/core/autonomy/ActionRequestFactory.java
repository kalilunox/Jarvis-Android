package com.jarvis.core.autonomy;

import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutonomyContracts.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/autonomy/ActionRequestFactory;", "", "<init>", "()V", "fromProactive", "Lcom/jarvis/core/autonomy/ActionRequest;", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "proposedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "source", "Lcom/jarvis/core/autonomy/DecisionSource;", "nowMillis", "", "noAction", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "reasonCode", "Lcom/jarvis/core/autonomy/AutonomyReasonCode;", "reason", "", "notes", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActionRequestFactory {
    public static final ActionRequestFactory INSTANCE = new ActionRequestFactory();

    private ActionRequestFactory() {
    }

    public static /* synthetic */ ActionRequest fromProactive$default(ActionRequestFactory actionRequestFactory, ProactiveDecision proactiveDecision, ProposedAction proposedAction, DecisionSource decisionSource, long j, int i, Object obj) {
        DecisionSource decisionSource2;
        long createdAtMillis;
        if ((i & 4) == 0) {
            decisionSource2 = decisionSource;
        } else {
            decisionSource2 = DecisionSource.PROACTIVE;
        }
        if ((i & 8) == 0) {
            createdAtMillis = j;
        } else {
            createdAtMillis = proactiveDecision.getCreatedAtMillis();
        }
        return actionRequestFactory.fromProactive(proactiveDecision, proposedAction, decisionSource2, createdAtMillis);
    }

    public final ActionRequest fromProactive(ProactiveDecision decision, ProposedAction proposedAction, DecisionSource source, long nowMillis) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(source, "source");
        if (decision.getType() == ProactiveDecisionType.NO_OP || proposedAction == null || proposedAction.getKind() == ActionKind.NONE) {
            return null;
        }
        return new ActionRequest(null, source, proposedAction, decision.getBehaviorPatternKey(), decision.getType(), decision.getConfidence(), decision.getRelevance(), decision.getFingerprint(), nowMillis, CollectionsKt.listOf((Object[]) new String[]{"conf=" + decision.getConfidence(), "rel=" + decision.getRelevance(), "dtype=" + decision.getType()}), 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AutonomyVerdict noAction$default(ActionRequestFactory actionRequestFactory, AutonomyReasonCode autonomyReasonCode, String str, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        return actionRequestFactory.noAction(autonomyReasonCode, str, list);
    }

    public final AutonomyVerdict noAction(AutonomyReasonCode reasonCode, String reason, List<String> notes) {
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new AutonomyVerdict(AutonomyVerdictType.NO_ACTION, reasonCode, reason, null, notes);
    }
}
