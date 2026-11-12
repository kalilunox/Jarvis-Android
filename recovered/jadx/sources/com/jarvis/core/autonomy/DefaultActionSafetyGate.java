package com.jarvis.core.autonomy;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DefaultActionSafetyGate.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J.\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J6\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0003H\u0002J&\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0002J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\rH\u0002J.\u0010 \u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\r2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/jarvis/core/autonomy/DefaultActionSafetyGate;", "Lcom/jarvis/core/autonomy/ActionSafetyGate;", "clockSkewToleranceMillis", "", "<init>", "(J)V", "evaluate", "Lcom/jarvis/core/autonomy/ActionSafetyGateResult;", "input", "Lcom/jarvis/core/autonomy/ActionSafetyGateInput;", "checkIntegrity", "Lkotlin/Pair;", "Lcom/jarvis/core/autonomy/ActionSafetyFailureReason;", "", "presented", "Lcom/jarvis/core/autonomy/ProposedAction;", "assessment", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "binding", "Lcom/jarvis/core/autonomy/PolicyDecisionBinding;", "checkConfirmation", "conf", "Lcom/jarvis/core/autonomy/ActionConfirmation;", "req", "Lcom/jarvis/core/autonomy/ActionRequest;", "nowMillis", "checkTimestamp", "eventMillis", "harden", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "verdict", "reason", "block", "failure", "notes", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultActionSafetyGate implements ActionSafetyGate {
    public static final long CLOCK_SKEW_TOLERANCE_MS = 2000;
    private static final Set<ActionKind> KINDS_REQUIRING_EXPLICIT_PERMS = SetsKt.setOf((Object[]) new ActionKind[]{ActionKind.COMMUNICATION, ActionKind.UI_AUTOMATION, ActionKind.DATA_DELETION, ActionKind.FINANCIAL, ActionKind.SYSTEM_SETTINGS, ActionKind.UNKNOWN});
    private final long clockSkewToleranceMillis;

    /* JADX INFO: compiled from: DefaultActionSafetyGate.kt */
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

    public DefaultActionSafetyGate() {
        this(0L, 1, null);
    }

    public DefaultActionSafetyGate(long clockSkewToleranceMillis) {
        this.clockSkewToleranceMillis = clockSkewToleranceMillis;
    }

    public /* synthetic */ DefaultActionSafetyGate(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 2000L : j);
    }

    @Override // com.jarvis.core.autonomy.ActionSafetyGate
    public ActionSafetyGateResult evaluate(ActionSafetyGateInput input) {
        Set<String> setRequiredPermissionsFor;
        Pair<ActionSafetyFailureReason, String> pairCheckConfirmation;
        Intrinsics.checkNotNullParameter(input, "input");
        AutonomyVerdict verdict = input.getVerdict();
        List<String> listPlus = CollectionsKt.plus((Collection<? extends String>) verdict.getNotes(), "gate=DefaultActionSafetyGate");
        switch (WhenMappings.$EnumSwitchMapping$0[verdict.getType().ordinal()]) {
            case 1:
                return block(verdict, ActionSafetyFailureReason.DENY_VERDICT, "Policy DENY cannot pass SafetyGate", listPlus);
            case 2:
                return block(verdict, ActionSafetyFailureReason.NO_ACTION_VERDICT, "NO_ACTION is not executable", listPlus);
            case 3:
            case 4:
                ActionRequest req = verdict.getActionRequest();
                if (req == null) {
                    return block(AutonomyVerdict.copy$default(verdict, AutonomyVerdictType.DENY, AutonomyReasonCode.SAFETY_GATE_DENY, "Missing actionRequest", null, null, 24, null), ActionSafetyFailureReason.MISSING_ACTION_REQUEST, "ALLOW/CONFIRM without actionRequest", listPlus);
                }
                ProposedAction presentedAction = input.getPresentedAction();
                if (presentedAction == null) {
                    presentedAction = req.getProposedAction();
                }
                ProposedAction presented = presentedAction;
                ActionRiskAssessment assessment = input.getRiskAssessment();
                if (assessment == null) {
                    return block(harden(verdict, "Missing risk assessment"), ActionSafetyFailureReason.MISSING_RISK_ASSESSMENT, "Fail-closed: riskAssessment required", listPlus);
                }
                if (!StringsKt.isBlank(assessment.getActionId()) && !Intrinsics.areEqual(assessment.getActionId(), presented.getActionId())) {
                    return block(harden(verdict, "Risk assessment mismatch"), ActionSafetyFailureReason.RISK_ASSESSMENT_MISMATCH, "assessment.actionId != presented.actionId", listPlus);
                }
                PolicyDecisionBinding binding = input.getPolicyBinding();
                if (binding == null) {
                    return block(harden(verdict, "Missing policy binding"), ActionSafetyFailureReason.MISSING_POLICY_BINDING, "Fail-closed: policyBinding required", listPlus);
                }
                Pair<ActionSafetyFailureReason, String> pairCheckIntegrity = checkIntegrity(presented, assessment, binding);
                if (pairCheckIntegrity != null) {
                    return block(harden(verdict, pairCheckIntegrity.getSecond()), pairCheckIntegrity.getFirst(), pairCheckIntegrity.getSecond(), listPlus);
                }
                Set<String> allowedArgumentKeys = input.getAllowedArgumentKeys();
                if (allowedArgumentKeys == null) {
                    allowedArgumentKeys = ActionArgumentRules.INSTANCE.allowedKeysFor(presented);
                }
                ActionSafetyFailureReason argFail = ActionArgumentRules.INSTANCE.validateArguments(presented, allowedArgumentKeys, input.getForbiddenArgumentKeys());
                if (argFail != null) {
                    return block(harden(verdict, "Argument validation failed: " + argFail), argFail, "Argument validation failed: " + argFail, listPlus);
                }
                if (!input.getRequiredPermissions().isEmpty()) {
                    setRequiredPermissionsFor = input.getRequiredPermissions();
                } else {
                    setRequiredPermissionsFor = ActionArgumentRules.INSTANCE.requiredPermissionsFor(presented);
                }
                Set<String> set = setRequiredPermissionsFor;
                if (KINDS_REQUIRING_EXPLICIT_PERMS.contains(presented.getKind()) && set.isEmpty()) {
                    return block(harden(verdict, "Permissions unspecified for kind"), ActionSafetyFailureReason.PERMISSIONS_UNSPECIFIED, "Fail-closed: " + presented.getKind() + " requires declared permissions", listPlus);
                }
                if (!input.getGrantedPermissions().containsAll(set)) {
                    return block(harden(verdict, "Missing permissions"), ActionSafetyFailureReason.MISSING_PERMISSION, "Missing permissions: " + CollectionsKt.joinToString$default(SetsKt.minus((Set) set, (Iterable) input.getGrantedPermissions()), null, null, null, 0, null, null, 63, null), listPlus);
                }
                ContextFreshness freshness = input.getContextFreshness();
                if (freshness == null) {
                    return block(harden(verdict, "Missing context freshness"), ActionSafetyFailureReason.MISSING_CONTEXT_FRESHNESS, "Fail-closed: contextFreshness required", listPlus);
                }
                Pair<ActionSafetyFailureReason, String> pairCheckTimestamp = checkTimestamp(freshness.getContextCapturedAtMillis(), input.getNowMillis());
                if (pairCheckTimestamp == null) {
                    long age = input.getNowMillis() - freshness.getContextCapturedAtMillis();
                    if (age > freshness.getMaxAgeMillis()) {
                        return block(harden(verdict, "Stale context"), ActionSafetyFailureReason.STALE_CONTEXT, "Context age=" + age + "ms > max=" + freshness.getMaxAgeMillis() + "ms", listPlus);
                    }
                    DecisionValidity validity = input.getDecisionValidity();
                    if (validity == null) {
                        return block(harden(verdict, "Missing decision validity"), ActionSafetyFailureReason.MISSING_DECISION_VALIDITY, "Fail-closed: decisionValidity required", listPlus);
                    }
                    Pair<ActionSafetyFailureReason, String> pairCheckTimestamp2 = checkTimestamp(validity.getDecidedAtMillis(), input.getNowMillis());
                    if (pairCheckTimestamp2 != null) {
                        return block(harden(verdict, pairCheckTimestamp2.getSecond()), pairCheckTimestamp2.getFirst(), pairCheckTimestamp2.getSecond(), listPlus);
                    }
                    if (validity.getTtlMillis() >= 0) {
                        long expiresAt = validity.getDecidedAtMillis() + validity.getTtlMillis();
                        if (input.getNowMillis() > expiresAt) {
                            return block(harden(verdict, "Decision expired"), ActionSafetyFailureReason.EXPIRED_DECISION, "Decision expired at=" + expiresAt + " now=" + input.getNowMillis(), listPlus);
                        }
                        switch (WhenMappings.$EnumSwitchMapping$0[verdict.getType().ordinal()]) {
                            case 3:
                                ActionConfirmation conf = input.getConfirmation();
                                if (conf != null && (pairCheckConfirmation = checkConfirmation(conf, req, binding, input.getNowMillis())) != null) {
                                    return block(verdict, pairCheckConfirmation.getFirst(), pairCheckConfirmation.getSecond(), listPlus);
                                }
                                break;
                            case 4:
                                ActionConfirmation conf2 = input.getConfirmation();
                                if (conf2 != null) {
                                    Pair<ActionSafetyFailureReason, String> pairCheckConfirmation2 = checkConfirmation(conf2, req, binding, input.getNowMillis());
                                    if (pairCheckConfirmation2 != null) {
                                        return block(verdict, pairCheckConfirmation2.getFirst(), pairCheckConfirmation2.getSecond(), listPlus);
                                    }
                                } else {
                                    return block(verdict, ActionSafetyFailureReason.MISSING_CONFIRMATION, "REQUIRE_CONFIRMATION without confirmation", listPlus);
                                }
                                break;
                        }
                        return new ActionSafetyGateResult(ActionSafetyGateStatus.PASS, null, "SafetyGate PASS", AutonomyVerdict.copy$default(verdict, null, null, null, null, CollectionsKt.plus((Collection<? extends String>) listPlus, "gate=pass"), 15, null), CollectionsKt.plus((Collection<? extends String>) listPlus, "gate=pass"));
                    }
                    return block(harden(verdict, "Malformed decision TTL"), ActionSafetyFailureReason.MALFORMED_TIMESTAMP, "ttlMillis must be >= 0", listPlus);
                }
                return block(harden(verdict, pairCheckTimestamp.getSecond()), pairCheckTimestamp.getFirst(), pairCheckTimestamp.getSecond(), listPlus);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final Pair<ActionSafetyFailureReason, String> checkIntegrity(ProposedAction presented, ActionRiskAssessment assessment, PolicyDecisionBinding binding) {
        String expected = binding.getIntegrityFingerprint();
        String actual = ActionIntegrity.INSTANCE.fingerprint(presented, assessment);
        if (!Intrinsics.areEqual(actual, expected)) {
            if (presented.getKind() != binding.getPolicyAction().getKind()) {
                return TuplesKt.to(ActionSafetyFailureReason.ACTION_KIND_MODIFIED, "ActionKind changed after Policy");
            }
            if (!Intrinsics.areEqual(presented.getToolId(), binding.getPolicyAction().getToolId()) || !Intrinsics.areEqual(presented.getActionId(), binding.getPolicyAction().getActionId())) {
                return TuplesKt.to(ActionSafetyFailureReason.TARGET_MODIFIED, "Target/actionId/toolId changed after Policy");
            }
            if (!Intrinsics.areEqual(presented.getArguments(), binding.getPolicyAction().getArguments())) {
                return TuplesKt.to(ActionSafetyFailureReason.ARGUMENTS_MODIFIED, "Arguments changed after Policy");
            }
            if (assessment.getRiskClass() != binding.getPolicyRiskAssessment().getRiskClass() || assessment.getKind() != binding.getPolicyRiskAssessment().getKind() || !Intrinsics.areEqual(assessment.getActionId(), binding.getPolicyRiskAssessment().getActionId())) {
                return TuplesKt.to(ActionSafetyFailureReason.RISK_ASSESSMENT_MISMATCH, "Risk assessment changed after Policy");
            }
            return TuplesKt.to(ActionSafetyFailureReason.PLAN_INTEGRITY_FAILURE, "Integrity fingerprint mismatch");
        }
        if (assessment.getRiskClass() != binding.getPolicyRiskAssessment().getRiskClass()) {
            return TuplesKt.to(ActionSafetyFailureReason.RISK_ASSESSMENT_MISMATCH, "riskClass differs from policy binding");
        }
        return null;
    }

    private final Pair<ActionSafetyFailureReason, String> checkConfirmation(ActionConfirmation conf, ActionRequest req, PolicyDecisionBinding binding, long nowMillis) {
        if (StringsKt.isBlank(conf.getConfirmationId())) {
            return TuplesKt.to(ActionSafetyFailureReason.INVALID_CONFIRMATION, "Blank confirmationId");
        }
        if (conf.getConsumed()) {
            return TuplesKt.to(ActionSafetyFailureReason.CONFIRMATION_ALREADY_USED, "Confirmation already used");
        }
        if (!Intrinsics.areEqual(conf.getBoundActionRequestId(), req.getId())) {
            return TuplesKt.to(ActionSafetyFailureReason.CONFIRMATION_ACTION_MISMATCH, "Confirmation bound to different ActionRequest");
        }
        if (!Intrinsics.areEqual(conf.getBoundIntegrityFingerprint(), binding.getIntegrityFingerprint())) {
            return TuplesKt.to(ActionSafetyFailureReason.CONFIRMATION_ACTION_MISMATCH, "Confirmation fingerprint mismatch");
        }
        if (conf.getConfirmedAtMillis() <= 0 || conf.getExpiresAtMillis() <= 0) {
            return TuplesKt.to(ActionSafetyFailureReason.MALFORMED_TIMESTAMP, "Malformed confirmation timestamps");
        }
        if (conf.getExpiresAtMillis() < conf.getConfirmedAtMillis()) {
            return TuplesKt.to(ActionSafetyFailureReason.MALFORMED_TIMESTAMP, "expiresAt < confirmedAt");
        }
        if (this.clockSkewToleranceMillis + nowMillis < conf.getConfirmedAtMillis()) {
            return TuplesKt.to(ActionSafetyFailureReason.CLOCK_ANOMALY, "Confirmation timestamp in the future");
        }
        if (nowMillis > conf.getExpiresAtMillis()) {
            return TuplesKt.to(ActionSafetyFailureReason.EXPIRED_CONFIRMATION, "Confirmation expired");
        }
        return null;
    }

    private final Pair<ActionSafetyFailureReason, String> checkTimestamp(long eventMillis, long nowMillis) {
        if (eventMillis <= 0 || nowMillis <= 0) {
            return TuplesKt.to(ActionSafetyFailureReason.MALFORMED_TIMESTAMP, "Timestamp must be > 0");
        }
        if (this.clockSkewToleranceMillis + nowMillis < eventMillis) {
            return TuplesKt.to(ActionSafetyFailureReason.CLOCK_ANOMALY, "Event timestamp in the future (clock anomaly)");
        }
        return null;
    }

    private final AutonomyVerdict harden(AutonomyVerdict verdict, String reason) {
        if (verdict.getType() == AutonomyVerdictType.DENY || verdict.getType() == AutonomyVerdictType.NO_ACTION) {
            return verdict;
        }
        return AutonomyVerdict.copy$default(verdict, AutonomyVerdictType.DENY, AutonomyReasonCode.SAFETY_GATE_DENY, reason, null, CollectionsKt.plus((Collection<? extends String>) verdict.getNotes(), "gate_hardened=true"), 8, null);
    }

    private final ActionSafetyGateResult block(AutonomyVerdict verdict, ActionSafetyFailureReason failure, String reason, List<String> notes) {
        return new ActionSafetyGateResult(ActionSafetyGateStatus.BLOCK, failure, reason, verdict, CollectionsKt.plus((Collection<? extends String>) notes, "gate=block:" + failure));
    }
}
