package com.jarvis.core.autonomy;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnimplementedAutonomy.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/jarvis/core/autonomy/UnimplementedActionSafetyGate;", "Lcom/jarvis/core/autonomy/ActionSafetyGate;", "<init>", "()V", "evaluate", "Lcom/jarvis/core/autonomy/ActionSafetyGateResult;", "input", "Lcom/jarvis/core/autonomy/ActionSafetyGateInput;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnimplementedActionSafetyGate implements ActionSafetyGate {
    public static final UnimplementedActionSafetyGate INSTANCE = new UnimplementedActionSafetyGate();

    /* JADX INFO: compiled from: UnimplementedAutonomy.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AutonomyVerdictType.values().length];
            try {
                iArr[AutonomyVerdictType.DENY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AutonomyVerdictType.NO_ACTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AutonomyVerdictType.ALLOW.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AutonomyVerdictType.REQUIRE_CONFIRMATION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private UnimplementedActionSafetyGate() {
    }

    @Override // com.jarvis.core.autonomy.ActionSafetyGate
    public ActionSafetyGateResult evaluate(ActionSafetyGateInput input) {
        Intrinsics.checkNotNullParameter(input, "input");
        AutonomyVerdict v = input.getVerdict();
        switch (WhenMappings.$EnumSwitchMapping$0[v.getType().ordinal()]) {
            case 1:
                return new ActionSafetyGateResult(ActionSafetyGateStatus.BLOCK, ActionSafetyFailureReason.DENY_VERDICT, "DENY cannot pass", v, CollectionsKt.plus((Collection<? extends String>) v.getNotes(), "gate=unimplemented"));
            case 2:
                return new ActionSafetyGateResult(ActionSafetyGateStatus.BLOCK, ActionSafetyFailureReason.NO_ACTION_VERDICT, "NO_ACTION cannot pass", v, CollectionsKt.plus((Collection<? extends String>) v.getNotes(), "gate=unimplemented"));
            case 3:
            case 4:
                AutonomyVerdict hardened = AutonomyVerdict.copy$default(v, AutonomyVerdictType.DENY, AutonomyReasonCode.SAFETY_GATE_DENY, "ActionSafetyGate stub blocks executable path", null, CollectionsKt.plus((Collection<? extends String>) v.getNotes(), "gate=unimplemented"), 8, null);
                return new ActionSafetyGateResult(ActionSafetyGateStatus.BLOCK, ActionSafetyFailureReason.UNKNOWN_SECURITY_STATE, "UnimplementedActionSafetyGate blocks all executable verdicts", hardened, hardened.getNotes());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
