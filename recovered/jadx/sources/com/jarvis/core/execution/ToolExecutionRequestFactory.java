package com.jarvis.core.execution;

import com.jarvis.core.autonomy.ActionArgumentRules;
import com.jarvis.core.autonomy.ActionIntegrity;
import com.jarvis.core.autonomy.ActionRequest;
import com.jarvis.core.autonomy.ActionRiskAssessment;
import com.jarvis.core.autonomy.ActionSafetyFailureReason;
import com.jarvis.core.autonomy.ActionSafetyGateResult;
import com.jarvis.core.autonomy.ActionSafetyGateStatus;
import com.jarvis.core.autonomy.AutonomyVerdict;
import com.jarvis.core.autonomy.AutonomyVerdictType;
import com.jarvis.core.autonomy.PolicyDecisionBinding;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ToolExecutionRequestFactory.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JV\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002¨\u0006\u001c"}, d2 = {"Lcom/jarvis/core/execution/ToolExecutionRequestFactory;", "", "<init>", "()V", "create", "Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult;", "gateResult", "Lcom/jarvis/core/autonomy/ActionSafetyGateResult;", "toolDefinition", "Lcom/jarvis/core/common/model/ToolDefinition;", "policyBinding", "Lcom/jarvis/core/autonomy/PolicyDecisionBinding;", "riskAssessment", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "input", "Lcom/jarvis/core/execution/ToolInput;", "presentedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "metadata", "Lcom/jarvis/core/execution/ToolMetadata;", "nowMillis", "", "reject", "Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult$Rejected;", "reason", "Lcom/jarvis/core/execution/ToolExecutionRequestRejectReason;", "message", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ToolExecutionRequestFactory {
    public static final ToolExecutionRequestFactory INSTANCE = new ToolExecutionRequestFactory();

    /* JADX INFO: compiled from: ToolExecutionRequestFactory.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

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
            int[] iArr2 = new int[ActionSafetyFailureReason.values().length];
            try {
                iArr2[ActionSafetyFailureReason.FORBIDDEN_PARAMETER.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[ActionSafetyFailureReason.UNKNOWN_PARAMETER.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[ActionSafetyFailureReason.INVALID_ARGUMENTS.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private ToolExecutionRequestFactory() {
    }

    public final ToolExecutionRequestCreateResult create(ActionSafetyGateResult gateResult, ToolDefinition toolDefinition, PolicyDecisionBinding policyBinding, ActionRiskAssessment riskAssessment, ToolInput input, ProposedAction presentedAction, ToolMetadata metadata, long nowMillis) {
        Intrinsics.checkNotNullParameter(gateResult, "gateResult");
        Intrinsics.checkNotNullParameter(toolDefinition, "toolDefinition");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (gateResult.getStatus() != ActionSafetyGateStatus.PASS) {
            ToolExecutionRequestRejectReason toolExecutionRequestRejectReason = ToolExecutionRequestRejectReason.GATE_BLOCKED;
            ActionSafetyGateStatus status = gateResult.getStatus();
            Object failureReason = gateResult.getFailureReason();
            if (failureReason == null) {
                failureReason = "?";
            }
            return reject(toolExecutionRequestRejectReason, "SafetyGate " + status + ": " + failureReason);
        }
        AutonomyVerdict verdict = gateResult.getVerdict();
        switch (WhenMappings.$EnumSwitchMapping$0[verdict.getType().ordinal()]) {
            case 1:
                return reject(ToolExecutionRequestRejectReason.DENY_VERDICT, "DENY cannot become ToolExecutionRequest");
            case 2:
                return reject(ToolExecutionRequestRejectReason.NO_ACTION_VERDICT, "NO_ACTION cannot become ToolExecutionRequest");
            case 3:
            case 4:
                ActionRequest actionRequest = verdict.getActionRequest();
                if (actionRequest == null) {
                    return reject(ToolExecutionRequestRejectReason.MISSING_ACTION_REQUEST, "Missing actionRequest on PASS verdict");
                }
                if (policyBinding == null) {
                    return reject(ToolExecutionRequestRejectReason.MISSING_BINDING, "policyBinding required");
                }
                if (riskAssessment == null) {
                    return reject(ToolExecutionRequestRejectReason.MISSING_RISK_ASSESSMENT, "riskAssessment required");
                }
                if (StringsKt.isBlank(toolDefinition.getId()) || StringsKt.isBlank(toolDefinition.getName())) {
                    return reject(ToolExecutionRequestRejectReason.INVALID_TOOL_DEFINITION, "ToolDefinition id/name required");
                }
                ProposedAction action = presentedAction == null ? actionRequest.getProposedAction() : presentedAction;
                ToolInput resolvedInput = input == null ? ToolInput.INSTANCE.fromProposedAction(action) : input;
                if (resolvedInput.matches(action)) {
                    if (Intrinsics.areEqual(action.getArguments(), policyBinding.getPolicyAction().getArguments()) && action.getKind() == policyBinding.getPolicyAction().getKind() && Intrinsics.areEqual(action.getActionId(), policyBinding.getPolicyAction().getActionId()) && Intrinsics.areEqual(action.getToolId(), policyBinding.getPolicyAction().getToolId())) {
                        if (riskAssessment.getRiskClass() == policyBinding.getPolicyRiskAssessment().getRiskClass() && riskAssessment.getKind() == policyBinding.getPolicyRiskAssessment().getKind() && Intrinsics.areEqual(riskAssessment.getActionId(), policyBinding.getPolicyRiskAssessment().getActionId())) {
                            String fingerprint = ActionIntegrity.INSTANCE.fingerprint(action, riskAssessment);
                            if (!Intrinsics.areEqual(fingerprint, policyBinding.getIntegrityFingerprint())) {
                                return reject(ToolExecutionRequestRejectReason.ACTION_TAMPERED, "Integrity fingerprint mismatch after SafetyGate");
                            }
                            String expectedToolId = action.getToolId();
                            if (expectedToolId != null && !Intrinsics.areEqual(expectedToolId, toolDefinition.getId())) {
                                return reject(ToolExecutionRequestRejectReason.TOOL_MISMATCH, "toolDefinition.id=" + toolDefinition + ".id != proposed.toolId=" + expectedToolId);
                            }
                            if (expectedToolId == null) {
                                return reject(ToolExecutionRequestRejectReason.TOOL_MISMATCH, "ProposedAction.toolId required for execution request");
                            }
                            ActionSafetyFailureReason argFail = ActionArgumentRules.INSTANCE.validateArguments(action, ActionArgumentRules.INSTANCE.allowedKeysFor(action), ActionArgumentRules.INSTANCE.getGLOBAL_FORBIDDEN_KEYS());
                            switch (argFail == null ? -1 : WhenMappings.$EnumSwitchMapping$1[argFail.ordinal()]) {
                                case -1:
                                    Iterable $this$filter$iv = toolDefinition.getParameters();
                                    Collection destination$iv$iv = new ArrayList();
                                    for (Object element$iv$iv : $this$filter$iv) {
                                        ToolParameter it = (ToolParameter) element$iv$iv;
                                        if (it.getRequired()) {
                                            destination$iv$iv.add(element$iv$iv);
                                        }
                                    }
                                    for (ToolParameter param : (List) destination$iv$iv) {
                                        if (!resolvedInput.getParameters().containsKey(param.getName())) {
                                            return reject(ToolExecutionRequestRejectReason.INVALID_INPUT, "Missing required parameter: " + param.getName());
                                        }
                                        if (!param.getAllowedValues().isEmpty()) {
                                            String v = resolvedInput.getParameters().get(param.getName());
                                            if (!CollectionsKt.contains(param.getAllowedValues(), v)) {
                                                return reject(ToolExecutionRequestRejectReason.INVALID_INPUT, "Parameter " + param.getName() + "=" + v + " not in allowedValues");
                                            }
                                        }
                                    }
                                    ToolExecutionRequest request = new ToolExecutionRequest(null, toolDefinition.getId(), toolDefinition, resolvedInput, action, fingerprint, riskAssessment, verdict.getType(), ActionSafetyGateStatus.PASS, actionRequest.getId(), metadata, nowMillis, 1, null);
                                    return new ToolExecutionRequestCreateResult.Created(request);
                                case 0:
                                default:
                                    return reject(ToolExecutionRequestRejectReason.INVALID_INPUT, "Argument validation failed: " + argFail);
                                case 1:
                                    return reject(ToolExecutionRequestRejectReason.FORBIDDEN_PARAMETER, "Forbidden parameter in ToolInput");
                                case 2:
                                    return reject(ToolExecutionRequestRejectReason.UNKNOWN_PARAMETER, "Unknown parameter in ToolInput");
                                case 3:
                                    return reject(ToolExecutionRequestRejectReason.INVALID_INPUT, "Invalid ToolInput arguments");
                            }
                        }
                        return reject(ToolExecutionRequestRejectReason.RISK_MISMATCH, "Risk assessment differs from policy binding");
                    }
                    return reject(ToolExecutionRequestRejectReason.ACTION_TAMPERED, "Presented action differs from policy binding snapshot");
                }
                return reject(ToolExecutionRequestRejectReason.INPUT_MISMATCH, "ToolInput does not match ProposedAction.arguments");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final ToolExecutionRequestCreateResult.Rejected reject(ToolExecutionRequestRejectReason reason, String message) {
        return new ToolExecutionRequestCreateResult.Rejected(reason, message);
    }
}
