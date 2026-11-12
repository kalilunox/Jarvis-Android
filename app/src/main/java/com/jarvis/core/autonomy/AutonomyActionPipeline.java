package com.jarvis.core.autonomy;

import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.proactive.ProactiveDecision;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutonomyActionPipeline.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJª\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00142\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00172\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013J\u001a\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/jarvis/core/autonomy/AutonomyActionPipeline;", "", "policy", "Lcom/jarvis/core/autonomy/AutonomyPolicy;", "gate", "Lcom/jarvis/core/autonomy/ActionSafetyGate;", "riskClassifier", "Lcom/jarvis/core/autonomy/ActionRiskClassifier;", "<init>", "(Lcom/jarvis/core/autonomy/AutonomyPolicy;Lcom/jarvis/core/autonomy/ActionSafetyGate;Lcom/jarvis/core/autonomy/ActionRiskClassifier;)V", "plan", "Lcom/jarvis/core/autonomy/AutonomyActionPlan;", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "proposed", "Lcom/jarvis/core/autonomy/ProposedAction;", "level", "Lcom/jarvis/core/autonomy/AutonomyLevel;", "allowlist", "", "", "blocklist", "nowMillis", "", "requestId", "grantedPermissions", "confirmation", "Lcom/jarvis/core/autonomy/ActionConfirmation;", "contextCapturedAtMillis", "source", "Lcom/jarvis/core/autonomy/DecisionSource;", "allowedArgumentKeys", "requiredPermissionsOverride", "toDraftToolCall", "Lcom/jarvis/core/common/model/ToolCall;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AutonomyActionPipeline {
    private final ActionSafetyGate gate;
    private final AutonomyPolicy policy;
    private final ActionRiskClassifier riskClassifier;

    public AutonomyActionPipeline() {
        this(null, null, null, 7, null);
    }

    public AutonomyActionPipeline(AutonomyPolicy policy, ActionSafetyGate gate, ActionRiskClassifier riskClassifier) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(gate, "gate");
        Intrinsics.checkNotNullParameter(riskClassifier, "riskClassifier");
        this.policy = policy;
        this.gate = gate;
        this.riskClassifier = riskClassifier;
    }

    public /* synthetic */ AutonomyActionPipeline(DefaultAutonomyPolicy defaultAutonomyPolicy, DefaultActionSafetyGate defaultActionSafetyGate, ActionRiskClassifier actionRiskClassifier, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DefaultAutonomyPolicy() : defaultAutonomyPolicy, (i & 2) != 0 ? new DefaultActionSafetyGate(0L, 1, null) : defaultActionSafetyGate, (i & 4) != 0 ? new ActionRiskClassifier() : actionRiskClassifier);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AutonomyActionPlan plan$default(AutonomyActionPipeline autonomyActionPipeline, ProactiveDecision proactiveDecision, ProposedAction proposedAction, AutonomyLevel autonomyLevel, Set set, Set set2, long j, String str, Set set3, ActionConfirmation actionConfirmation, long j2, DecisionSource decisionSource, Set set4, Set set5, int i, Object obj) {
        String str2;
        AutonomyLevel autonomyLevel2 = (i & 4) != 0 ? AutonomyLevel.SAFE_ONLY : autonomyLevel;
        Set setEmptySet = (i & 8) != 0 ? SetsKt.emptySet() : set;
        Set setEmptySet2 = (i & 16) != 0 ? SetsKt.emptySet() : set2;
        long jCurrentTimeMillis = (i & 32) != 0 ? System.currentTimeMillis() : j;
        if ((i & 64) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str2 = string;
        } else {
            str2 = str;
        }
        return autonomyActionPipeline.plan(proactiveDecision, proposedAction, autonomyLevel2, setEmptySet, setEmptySet2, jCurrentTimeMillis, str2, (i & 128) != 0 ? SetsKt.emptySet() : set3, (i & 256) != 0 ? null : actionConfirmation, (i & 512) != 0 ? jCurrentTimeMillis : j2, (i & 1024) != 0 ? DecisionSource.PROACTIVE : decisionSource, (i & 2048) != 0 ? null : set4, (i & 4096) != 0 ? null : set5);
    }

    public final AutonomyActionPlan plan(ProactiveDecision decision, ProposedAction proposed, AutonomyLevel level, Set<String> allowlist, Set<String> blocklist, long nowMillis, String requestId, Set<String> grantedPermissions, ActionConfirmation confirmation, long contextCapturedAtMillis, DecisionSource source, Set<String> allowedArgumentKeys, Set<String> requiredPermissionsOverride) {
        Set<String> set;
        AutonomyPlanStatus status;
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(allowlist, "allowlist");
        Intrinsics.checkNotNullParameter(blocklist, "blocklist");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(grantedPermissions, "grantedPermissions");
        Intrinsics.checkNotNullParameter(source, "source");
        ToolCall draft = null;
        ActionRiskAssessment assessment = proposed != null ? this.riskClassifier.classify(proposed) : null;
        AutonomyVerdict policyVerdict = this.policy.evaluate(new AutonomyPolicyInput(decision, proposed, level, assessment, source, allowlist, blocklist, nowMillis));
        AutonomyAuditLog.INSTANCE.record(policyVerdict, "pipeline.policy");
        PolicyDecisionBinding binding = (proposed == null || assessment == null) ? null : ActionIntegrity.INSTANCE.binding(proposed, assessment);
        if (requiredPermissionsOverride == null) {
            Set<String> setRequiredPermissionsFor = proposed != null ? ActionArgumentRules.INSTANCE.requiredPermissionsFor(proposed) : null;
            if (setRequiredPermissionsFor == null) {
                setRequiredPermissionsFor = SetsKt.emptySet();
            }
            set = setRequiredPermissionsFor;
        } else {
            set = requiredPermissionsOverride;
        }
        ActionSafetyGateResult gateResult = this.gate.evaluate(new ActionSafetyGateInput(policyVerdict, assessment, nowMillis, set, grantedPermissions, new ContextFreshness(contextCapturedAtMillis, 0L, 2, null), new DecisionValidity(nowMillis, 0L, 2, null), confirmation, binding, proposed, allowedArgumentKeys, null, 2048, null));
        AutonomyAuditLog.INSTANCE.record(gateResult.getVerdict(), "pipeline.gate");
        if (gateResult.getPassed() && proposed != null) {
            draft = toDraftToolCall(proposed, requestId);
        }
        if (policyVerdict.getType() == AutonomyVerdictType.NO_ACTION) {
            status = AutonomyPlanStatus.NO_ACTION;
        } else if (gateResult.getPassed() || !CollectionsKt.contains(SetsKt.setOf((Object[]) new ActionSafetyFailureReason[]{ActionSafetyFailureReason.MISSING_CONFIRMATION, ActionSafetyFailureReason.EXPIRED_CONFIRMATION, ActionSafetyFailureReason.INVALID_CONFIRMATION, ActionSafetyFailureReason.CONFIRMATION_ACTION_MISMATCH, ActionSafetyFailureReason.CONFIRMATION_ALREADY_USED}), gateResult.getFailureReason())) {
            status = (gateResult.getPassed() && gateResult.getPassed()) ? AutonomyPlanStatus.READY_TO_EXECUTE : AutonomyPlanStatus.DENIED;
        } else {
            status = AutonomyPlanStatus.AWAITING_CONFIRMATION;
        }
        return new AutonomyActionPlan(status, gateResult.getVerdict(), proposed, gateResult, draft, CollectionsKt.plus((Collection<? extends String>) gateResult.getNotes(), "plan=" + status));
    }

    private final ToolCall toDraftToolCall(ProposedAction proposed, String requestId) {
        String toolId = proposed.getToolId();
        if (toolId == null) {
            return null;
        }
        Map<String, String> arguments = proposed.getArguments();
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(arguments.size()));
        Iterable $this$associateByTo$iv$iv$iv = arguments.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Object key = it$iv$iv.getKey();
            Map.Entry it = (Map.Entry) element$iv$iv$iv;
            Object value = it.getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Any");
            destination$iv$iv.put(key, value);
        }
        return new ToolCall(null, requestId, toolId, destination$iv$iv, 1, null);
    }
}
