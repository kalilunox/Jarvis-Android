package com.jarvis.core.autonomy;

import com.jarvis.core.proactive.ProactiveDecisionType;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnimplementedAutonomy.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/jarvis/core/autonomy/UnimplementedAutonomyPolicy;", "Lcom/jarvis/core/autonomy/AutonomyPolicy;", "<init>", "()V", "evaluate", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "input", "Lcom/jarvis/core/autonomy/AutonomyPolicyInput;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnimplementedAutonomyPolicy implements AutonomyPolicy {
    public static final UnimplementedAutonomyPolicy INSTANCE = new UnimplementedAutonomyPolicy();

    private UnimplementedAutonomyPolicy() {
    }

    @Override // com.jarvis.core.autonomy.AutonomyPolicy
    public AutonomyVerdict evaluate(AutonomyPolicyInput input) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (input.getDecision().getType() == ProactiveDecisionType.NO_OP) {
            return ActionRequestFactory.noAction$default(ActionRequestFactory.INSTANCE, AutonomyReasonCode.PROACTIVE_NO_OP, "Proactive NO_OP — no action", null, 4, null);
        }
        if (input.getProposedAction() == null) {
            return ActionRequestFactory.noAction$default(ActionRequestFactory.INSTANCE, AutonomyReasonCode.NO_PROPOSED_ACTION, "No proposed action mapped", null, 4, null);
        }
        if (input.getAutonomyLevel() == AutonomyLevel.OFF) {
            return new AutonomyVerdict(AutonomyVerdictType.DENY, AutonomyReasonCode.AUTONOMY_LEVEL_OFF, "Autonomy is OFF", ActionRequestFactory.INSTANCE.fromProactive(input.getDecision(), input.getProposedAction(), input.getSource(), input.getNowMillis()), null, 16, null);
        }
        return new AutonomyVerdict(AutonomyVerdictType.REQUIRE_CONFIRMATION, AutonomyReasonCode.NOT_IMPLEMENTED, "AutonomyPolicy not implemented (Stage 8C). Confidence alone never ALLOWs.", ActionRequestFactory.INSTANCE.fromProactive(input.getDecision(), input.getProposedAction(), input.getSource(), input.getNowMillis()), CollectionsKt.listOf((Object[]) new String[]{"conf=" + input.getDecision().getConfidence(), "level=" + input.getAutonomyLevel(), "risk_kind=" + input.getProposedAction().getKind()}));
    }
}
