package com.jarvis.core.autonomy;

import com.jarvis.core.proactive.ProactiveDecisionType;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DefaultAutonomyPolicy.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J.\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J.\u0010\u0012\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/autonomy/DefaultAutonomyPolicy;", "Lcom/jarvis/core/autonomy/AutonomyPolicy;", "<init>", "()V", "evaluate", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "input", "Lcom/jarvis/core/autonomy/AutonomyPolicyInput;", "allow", "req", "Lcom/jarvis/core/autonomy/ActionRequest;", "notes", "", "", "confirm", "code", "Lcom/jarvis/core/autonomy/AutonomyReasonCode;", "reason", "deny", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultAutonomyPolicy implements AutonomyPolicy {

    /* JADX INFO: compiled from: DefaultAutonomyPolicy.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ActionRiskClass.values().length];
            try {
                iArr[ActionRiskClass.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ActionRiskClass.LOW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ActionRiskClass.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AutonomyLevel.values().length];
            try {
                iArr2[AutonomyLevel.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[AutonomyLevel.CONFIRM_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[AutonomyLevel.SAFE_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[AutonomyLevel.ASSISTED.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // com.jarvis.core.autonomy.AutonomyPolicy
    public AutonomyVerdict evaluate(AutonomyPolicyInput input) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (input.getDecision().getType() == ProactiveDecisionType.NO_OP) {
            return ActionRequestFactory.noAction$default(ActionRequestFactory.INSTANCE, AutonomyReasonCode.PROACTIVE_NO_OP, "Proactive NO_OP", null, 4, null);
        }
        ProposedAction proposed = input.getProposedAction();
        if (proposed == null || proposed.getKind() == ActionKind.NONE) {
            return ActionRequestFactory.noAction$default(ActionRequestFactory.INSTANCE, AutonomyReasonCode.NO_PROPOSED_ACTION, "No proposed action", null, 4, null);
        }
        ActionRequest actionRequest = ActionRequestFactory.INSTANCE.fromProactive(input.getDecision(), proposed, input.getSource(), input.getNowMillis());
        if (actionRequest == null) {
            return ActionRequestFactory.noAction$default(ActionRequestFactory.INSTANCE, AutonomyReasonCode.NO_PROPOSED_ACTION, "ActionRequestFactory rejected", null, 4, null);
        }
        ActionRiskAssessment assessment = input.getRiskAssessment();
        if (assessment == null) {
            return deny(actionRequest, AutonomyReasonCode.MISSING_RISK_ASSESSMENT, "Fail-closed: ActionRiskAssessment required", CollectionsKt.listOf((Object[]) new String[]{"src=" + input.getSource(), "action=" + proposed.getActionId()}));
        }
        if (!StringsKt.isBlank(assessment.getActionId()) && !Intrinsics.areEqual(assessment.getActionId(), proposed.getActionId())) {
            return deny(actionRequest, AutonomyReasonCode.RISK_ASSESSMENT_MISMATCH, "Fail-closed: assessment.actionId != proposed.actionId", CollectionsKt.listOf((Object[]) new String[]{"proposed=" + proposed.getActionId(), "assessed=" + assessment.getActionId()}));
        }
        if (input.getAutonomyLevel() == AutonomyLevel.OFF) {
            return new AutonomyVerdict(AutonomyVerdictType.DENY, AutonomyReasonCode.AUTONOMY_LEVEL_OFF, "Autonomy is OFF", actionRequest, null, 16, null);
        }
        String actionId = proposed.getActionId();
        if (input.getUserBlocklistActionIds().contains(actionId)) {
            return new AutonomyVerdict(AutonomyVerdictType.DENY, AutonomyReasonCode.USER_BLOCKLISTED, "Action blocklisted by user: " + actionId, actionRequest, null, 16, null);
        }
        ActionRiskClass risk = assessment.getRiskClass();
        boolean allowlisted = input.getUserAllowlistActionIds().contains(actionId);
        List<String> listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf((Object[]) new String[]{"risk=" + risk.name(), "kind=" + assessment.getKind(), "riskReason=" + assessment.getReasonCode(), "level=" + input.getAutonomyLevel(), "conf=" + input.getDecision().getConfidence(), "src=" + input.getSource(), "allowlisted=" + allowlisted}), (Iterable) CollectionsKt.take(assessment.getFactors(), 3));
        if (risk == ActionRiskClass.CRITICAL) {
            return allowlisted ? confirm(actionRequest, AutonomyReasonCode.AUTONOMY_REQUIRES_CONFIRMATION, "CRITICAL allowlisted — confirmation required (never silent ALLOW)", CollectionsKt.plus((Collection<? extends String>) listPlus, "conf_irrelevant=true")) : deny(actionRequest, AutonomyReasonCode.RISK_CLASS_TOO_HIGH, "CRITICAL risk denied", listPlus);
        }
        if (risk == ActionRiskClass.HIGH) {
            return allowlisted ? confirm(actionRequest, AutonomyReasonCode.AUTONOMY_REQUIRES_CONFIRMATION, "HIGH allowlisted — confirmation required", CollectionsKt.plus((Collection<? extends String>) listPlus, "conf_irrelevant=true")) : deny(actionRequest, AutonomyReasonCode.RISK_CLASS_TOO_HIGH, "HIGH risk denied (not allowlisted)", listPlus);
        }
        if (input.getSource() == DecisionSource.SYSTEM && risk != ActionRiskClass.NONE) {
            return confirm(actionRequest, AutonomyReasonCode.AUTONOMY_REQUIRES_CONFIRMATION, "SYSTEM source requires confirmation for risk=" + risk.name(), CollectionsKt.plus((Collection<? extends String>) listPlus, "system_no_silent_allow=true"));
        }
        switch (WhenMappings.$EnumSwitchMapping$1[input.getAutonomyLevel().ordinal()]) {
            case 1:
                throw new IllegalStateException("handled above".toString());
            case 2:
                return risk == ActionRiskClass.NONE ? allow(actionRequest, CollectionsKt.plus((Collection<? extends String>) listPlus, "read_only_ok")) : confirm(actionRequest, AutonomyReasonCode.AUTONOMY_REQUIRES_CONFIRMATION, "CONFIRM_ALL level", listPlus);
            case 3:
                switch (WhenMappings.$EnumSwitchMapping$0[risk.ordinal()]) {
                    case 1:
                    case 2:
                        return allow(actionRequest, listPlus);
                    case 3:
                        return confirm(actionRequest, AutonomyReasonCode.AUTONOMY_REQUIRES_CONFIRMATION, "SAFE_ONLY: MEDIUM needs confirmation", listPlus);
                    default:
                        return deny(actionRequest, AutonomyReasonCode.RISK_CLASS_TOO_HIGH, "SAFE_ONLY rejects " + risk.name(), listPlus);
                }
            case 4:
                if (input.getDecision().getType() == ProactiveDecisionType.CONFIRMATION_REQUEST && risk.ordinal() >= ActionRiskClass.MEDIUM.ordinal()) {
                    return confirm(actionRequest, AutonomyReasonCode.AUTONOMY_REQUIRES_CONFIRMATION, "Decision asked for confirmation", listPlus);
                }
                switch (WhenMappings.$EnumSwitchMapping$0[risk.ordinal()]) {
                    case 1:
                    case 2:
                        return allow(actionRequest, listPlus);
                    case 3:
                        return confirm(actionRequest, AutonomyReasonCode.AUTONOMY_REQUIRES_CONFIRMATION, "ASSISTED: MEDIUM needs confirmation", listPlus);
                    default:
                        return deny(actionRequest, AutonomyReasonCode.RISK_CLASS_TOO_HIGH, "ASSISTED rejects " + risk.name(), listPlus);
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final AutonomyVerdict allow(ActionRequest req, List<String> notes) {
        return new AutonomyVerdict(AutonomyVerdictType.ALLOW, AutonomyReasonCode.POLICY_ALLOW, "Allowed by DefaultAutonomyPolicy", req, notes);
    }

    private final AutonomyVerdict confirm(ActionRequest req, AutonomyReasonCode code, String reason, List<String> notes) {
        return new AutonomyVerdict(AutonomyVerdictType.REQUIRE_CONFIRMATION, code, reason, req, notes);
    }

    private final AutonomyVerdict deny(ActionRequest req, AutonomyReasonCode code, String reason, List<String> notes) {
        return new AutonomyVerdict(AutonomyVerdictType.DENY, code, reason, req, notes);
    }
}
