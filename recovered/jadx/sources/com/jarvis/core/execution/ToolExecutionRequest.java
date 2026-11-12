package com.jarvis.core.execution;

import com.jarvis.core.autonomy.ActionRiskAssessment;
import com.jarvis.core.autonomy.ActionSafetyGateStatus;
import com.jarvis.core.autonomy.AutonomyVerdictType;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ToolExecutionRequest.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010.\u001a\u00020/J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0006HÆ\u0003J\t\u00103\u001a\u00020\bHÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003J\t\u00107\u001a\u00020\u000fHÆ\u0003J\t\u00108\u001a\u00020\u0011HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0014HÆ\u0003J\t\u0010;\u001a\u00020\u0016HÆ\u0003J\u0081\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016HÆ\u0001J\u0013\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020AHÖ\u0001J\t\u0010B\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u0006C"}, d2 = {"Lcom/jarvis/core/execution/ToolExecutionRequest;", "", "id", "", "toolId", "toolDefinition", "Lcom/jarvis/core/common/model/ToolDefinition;", "input", "Lcom/jarvis/core/execution/ToolInput;", "proposedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "integrityFingerprint", "riskAssessment", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "policyVerdictType", "Lcom/jarvis/core/autonomy/AutonomyVerdictType;", "gateStatus", "Lcom/jarvis/core/autonomy/ActionSafetyGateStatus;", "actionRequestId", "metadata", "Lcom/jarvis/core/execution/ToolMetadata;", "createdAtMillis", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/ToolDefinition;Lcom/jarvis/core/execution/ToolInput;Lcom/jarvis/core/autonomy/ProposedAction;Ljava/lang/String;Lcom/jarvis/core/autonomy/ActionRiskAssessment;Lcom/jarvis/core/autonomy/AutonomyVerdictType;Lcom/jarvis/core/autonomy/ActionSafetyGateStatus;Ljava/lang/String;Lcom/jarvis/core/execution/ToolMetadata;J)V", "getId", "()Ljava/lang/String;", "getToolId", "getToolDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "getInput", "()Lcom/jarvis/core/execution/ToolInput;", "getProposedAction", "()Lcom/jarvis/core/autonomy/ProposedAction;", "getIntegrityFingerprint", "getRiskAssessment", "()Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "getPolicyVerdictType", "()Lcom/jarvis/core/autonomy/AutonomyVerdictType;", "getGateStatus", "()Lcom/jarvis/core/autonomy/ActionSafetyGateStatus;", "getActionRequestId", "getMetadata", "()Lcom/jarvis/core/execution/ToolMetadata;", "getCreatedAtMillis", "()J", "toLegacyToolCall", "Lcom/jarvis/core/common/model/ToolCall;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolExecutionRequest {
    private final String actionRequestId;
    private final long createdAtMillis;
    private final ActionSafetyGateStatus gateStatus;
    private final String id;
    private final ToolInput input;
    private final String integrityFingerprint;
    private final ToolMetadata metadata;
    private final AutonomyVerdictType policyVerdictType;
    private final ProposedAction proposedAction;
    private final ActionRiskAssessment riskAssessment;
    private final ToolDefinition toolDefinition;
    private final String toolId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getActionRequestId() {
        return this.actionRequestId;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ToolDefinition getToolDefinition() {
        return this.toolDefinition;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ToolInput getInput() {
        return this.input;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getIntegrityFingerprint() {
        return this.integrityFingerprint;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final ActionRiskAssessment getRiskAssessment() {
        return this.riskAssessment;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final AutonomyVerdictType getPolicyVerdictType() {
        return this.policyVerdictType;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final ActionSafetyGateStatus getGateStatus() {
        return this.gateStatus;
    }

    public final ToolExecutionRequest copy(String id, String toolId, ToolDefinition toolDefinition, ToolInput input, ProposedAction proposedAction, String integrityFingerprint, ActionRiskAssessment riskAssessment, AutonomyVerdictType policyVerdictType, ActionSafetyGateStatus gateStatus, String actionRequestId, ToolMetadata metadata, long createdAtMillis) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(toolDefinition, "toolDefinition");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(proposedAction, "proposedAction");
        Intrinsics.checkNotNullParameter(integrityFingerprint, "integrityFingerprint");
        Intrinsics.checkNotNullParameter(riskAssessment, "riskAssessment");
        Intrinsics.checkNotNullParameter(policyVerdictType, "policyVerdictType");
        Intrinsics.checkNotNullParameter(gateStatus, "gateStatus");
        Intrinsics.checkNotNullParameter(actionRequestId, "actionRequestId");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return new ToolExecutionRequest(id, toolId, toolDefinition, input, proposedAction, integrityFingerprint, riskAssessment, policyVerdictType, gateStatus, actionRequestId, metadata, createdAtMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolExecutionRequest)) {
            return false;
        }
        ToolExecutionRequest toolExecutionRequest = (ToolExecutionRequest) other;
        return Intrinsics.areEqual(this.id, toolExecutionRequest.id) && Intrinsics.areEqual(this.toolId, toolExecutionRequest.toolId) && Intrinsics.areEqual(this.toolDefinition, toolExecutionRequest.toolDefinition) && Intrinsics.areEqual(this.input, toolExecutionRequest.input) && Intrinsics.areEqual(this.proposedAction, toolExecutionRequest.proposedAction) && Intrinsics.areEqual(this.integrityFingerprint, toolExecutionRequest.integrityFingerprint) && Intrinsics.areEqual(this.riskAssessment, toolExecutionRequest.riskAssessment) && this.policyVerdictType == toolExecutionRequest.policyVerdictType && this.gateStatus == toolExecutionRequest.gateStatus && Intrinsics.areEqual(this.actionRequestId, toolExecutionRequest.actionRequestId) && Intrinsics.areEqual(this.metadata, toolExecutionRequest.metadata) && this.createdAtMillis == toolExecutionRequest.createdAtMillis;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.toolId.hashCode()) * 31) + this.toolDefinition.hashCode()) * 31) + this.input.hashCode()) * 31) + this.proposedAction.hashCode()) * 31) + this.integrityFingerprint.hashCode()) * 31) + this.riskAssessment.hashCode()) * 31) + this.policyVerdictType.hashCode()) * 31) + this.gateStatus.hashCode()) * 31) + this.actionRequestId.hashCode()) * 31) + this.metadata.hashCode()) * 31) + Long.hashCode(this.createdAtMillis);
    }

    public String toString() {
        return "ToolExecutionRequest(id=" + this.id + ", toolId=" + this.toolId + ", toolDefinition=" + this.toolDefinition + ", input=" + this.input + ", proposedAction=" + this.proposedAction + ", integrityFingerprint=" + this.integrityFingerprint + ", riskAssessment=" + this.riskAssessment + ", policyVerdictType=" + this.policyVerdictType + ", gateStatus=" + this.gateStatus + ", actionRequestId=" + this.actionRequestId + ", metadata=" + this.metadata + ", createdAtMillis=" + this.createdAtMillis + ")";
    }

    public ToolExecutionRequest(String id, String toolId, ToolDefinition toolDefinition, ToolInput input, ProposedAction proposedAction, String integrityFingerprint, ActionRiskAssessment riskAssessment, AutonomyVerdictType policyVerdictType, ActionSafetyGateStatus gateStatus, String actionRequestId, ToolMetadata metadata, long createdAtMillis) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(toolDefinition, "toolDefinition");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(proposedAction, "proposedAction");
        Intrinsics.checkNotNullParameter(integrityFingerprint, "integrityFingerprint");
        Intrinsics.checkNotNullParameter(riskAssessment, "riskAssessment");
        Intrinsics.checkNotNullParameter(policyVerdictType, "policyVerdictType");
        Intrinsics.checkNotNullParameter(gateStatus, "gateStatus");
        Intrinsics.checkNotNullParameter(actionRequestId, "actionRequestId");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.id = id;
        this.toolId = toolId;
        this.toolDefinition = toolDefinition;
        this.input = input;
        this.proposedAction = proposedAction;
        this.integrityFingerprint = integrityFingerprint;
        this.riskAssessment = riskAssessment;
        this.policyVerdictType = policyVerdictType;
        this.gateStatus = gateStatus;
        this.actionRequestId = actionRequestId;
        this.metadata = metadata;
        this.createdAtMillis = createdAtMillis;
        if (StringsKt.isBlank(this.id)) {
            throw new IllegalArgumentException("request id blank".toString());
        }
        if (StringsKt.isBlank(this.toolId)) {
            throw new IllegalArgumentException("toolId blank".toString());
        }
        if (!Intrinsics.areEqual(this.toolId, this.toolDefinition.getId())) {
            throw new IllegalArgumentException("toolId != toolDefinition.id".toString());
        }
        if (StringsKt.isBlank(this.integrityFingerprint)) {
            throw new IllegalArgumentException("integrityFingerprint blank".toString());
        }
        if (StringsKt.isBlank(this.actionRequestId)) {
            throw new IllegalArgumentException("actionRequestId blank".toString());
        }
        boolean z = true;
        if (!(this.gateStatus == ActionSafetyGateStatus.PASS)) {
            throw new IllegalArgumentException("ToolExecutionRequest requires gate PASS".toString());
        }
        if (!(this.policyVerdictType == AutonomyVerdictType.ALLOW || this.policyVerdictType == AutonomyVerdictType.REQUIRE_CONFIRMATION)) {
            throw new IllegalArgumentException("ToolExecutionRequest forbids DENY/NO_ACTION verdict".toString());
        }
        if (this.proposedAction.getToolId() != null && !Intrinsics.areEqual(this.proposedAction.getToolId(), this.toolId)) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException("proposedAction.toolId mismatch".toString());
        }
        if (!this.input.matches(this.proposedAction)) {
            throw new IllegalArgumentException("ToolInput must match ProposedAction.arguments".toString());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ToolExecutionRequest(String str, String str2, ToolDefinition toolDefinition, ToolInput toolInput, ProposedAction proposedAction, String str3, ActionRiskAssessment actionRiskAssessment, AutonomyVerdictType autonomyVerdictType, ActionSafetyGateStatus actionSafetyGateStatus, String str4, ToolMetadata toolMetadata, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str5;
        if ((i & 1) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str5 = string;
        } else {
            str5 = str;
        }
        this(str5, str2, toolDefinition, toolInput, proposedAction, str3, actionRiskAssessment, autonomyVerdictType, actionSafetyGateStatus, str4, (i & 1024) != 0 ? new ToolMetadata(null, null, null, null, 15, null) : toolMetadata, (i & 2048) != 0 ? 0L : j);
    }

    public final String getId() {
        return this.id;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final ToolDefinition getToolDefinition() {
        return this.toolDefinition;
    }

    public final ToolInput getInput() {
        return this.input;
    }

    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    public final String getIntegrityFingerprint() {
        return this.integrityFingerprint;
    }

    public final ActionRiskAssessment getRiskAssessment() {
        return this.riskAssessment;
    }

    public final AutonomyVerdictType getPolicyVerdictType() {
        return this.policyVerdictType;
    }

    public final ActionSafetyGateStatus getGateStatus() {
        return this.gateStatus;
    }

    public final String getActionRequestId() {
        return this.actionRequestId;
    }

    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final ToolCall toLegacyToolCall() {
        String str = this.id;
        String str2 = this.toolId;
        Map<String, String> parameters = this.input.getParameters();
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(parameters.size()));
        Iterable $this$associateByTo$iv$iv$iv = parameters.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Object key = it$iv$iv.getKey();
            Map.Entry it = (Map.Entry) element$iv$iv$iv;
            Object value = it.getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Any");
            destination$iv$iv.put(key, value);
            parameters = parameters;
        }
        return new ToolCall(null, str, str2, destination$iv$iv, 1, null);
    }
}
