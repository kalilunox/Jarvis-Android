package com.jarvis.core.runtime;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.autonomy.ActionArgumentRules;
import com.jarvis.core.autonomy.ActionIntegrity;
import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.autonomy.ActionRiskAssessment;
import com.jarvis.core.autonomy.ActionRiskClass;
import com.jarvis.core.autonomy.ActionRiskClassifier;
import com.jarvis.core.autonomy.AutonomyActionPipeline;
import com.jarvis.core.autonomy.AutonomyActionPlan;
import com.jarvis.core.autonomy.AutonomyLevel;
import com.jarvis.core.autonomy.AutonomyPlanStatus;
import com.jarvis.core.autonomy.DecisionSource;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.common.PolicyDecision;
import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import com.jarvis.core.proactive.ProactiveReasonCode;
import com.jarvis.core.verification.DefaultResultVerifier;
import com.jarvis.core.verification.ExpectedEffect;
import com.jarvis.core.verification.ExpectedEffects;
import com.jarvis.core.verification.ObservedState;
import com.jarvis.core.verification.ResultVerifier;
import com.jarvis.core.verification.VerificationRequest;
import com.jarvis.core.verification.VerificationResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ExecutionOrchestrator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001+BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u000f\u0010\u0010JJ\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u000e2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eJ \u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\"\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00180(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180*H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/jarvis/core/runtime/ExecutionOrchestrator;", "", "toolExecutor", "Lcom/jarvis/core/common/ToolExecutor;", "pipeline", "Lcom/jarvis/core/autonomy/AutonomyActionPipeline;", "bridge", "Lcom/jarvis/core/runtime/LiveSkillRiskBridge;", "verifier", "Lcom/jarvis/core/verification/ResultVerifier;", "riskClassifier", "Lcom/jarvis/core/autonomy/ActionRiskClassifier;", "confirmLevelRequired", "Lkotlin/Function0;", "", "<init>", "(Lcom/jarvis/core/common/ToolExecutor;Lcom/jarvis/core/autonomy/AutonomyActionPipeline;Lcom/jarvis/core/runtime/LiveSkillRiskBridge;Lcom/jarvis/core/verification/ResultVerifier;Lcom/jarvis/core/autonomy/ActionRiskClassifier;Lkotlin/jvm/functions/Function0;)V", "executeAction", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome;", "toolCall", "Lcom/jarvis/core/common/model/ToolCall;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "requestId", "", "userConfirmed", "priorFingerprint", "nowMillis", "", "autonomyLevel", "Lcom/jarvis/core/autonomy/AutonomyLevel;", "softVerify", "Lcom/jarvis/core/verification/VerificationResult;", "call", "result", "Lcom/jarvis/core/common/model/ToolResult;", "userUtteranceDecision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "toolId", "allowedKeys", "", "args", "", "Outcome", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ExecutionOrchestrator {
    private final LiveSkillRiskBridge bridge;
    private final Function0<Boolean> confirmLevelRequired;
    private final AutonomyActionPipeline pipeline;
    private final ActionRiskClassifier riskClassifier;
    private final ToolExecutor toolExecutor;
    private final ResultVerifier verifier;

    /* JADX INFO: compiled from: ExecutionOrchestrator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ActionKind.values().length];
            try {
                iArr[ActionKind.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ActionKind.READ_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ActionKind.LOCAL_DEVICE_SAFE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AutonomyPlanStatus.values().length];
            try {
                iArr2[AutonomyPlanStatus.READY_TO_EXECUTE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[AutonomyPlanStatus.AWAITING_CONFIRMATION.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[AutonomyPlanStatus.NEEDS_CONFIRMATION.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[AutonomyPlanStatus.DENIED.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[AutonomyPlanStatus.NO_ACTION.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public ExecutionOrchestrator(ToolExecutor toolExecutor, AutonomyActionPipeline pipeline, LiveSkillRiskBridge bridge, ResultVerifier verifier, ActionRiskClassifier riskClassifier, Function0<Boolean> confirmLevelRequired) {
        Intrinsics.checkNotNullParameter(toolExecutor, "toolExecutor");
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        Intrinsics.checkNotNullParameter(bridge, "bridge");
        Intrinsics.checkNotNullParameter(verifier, "verifier");
        Intrinsics.checkNotNullParameter(riskClassifier, "riskClassifier");
        Intrinsics.checkNotNullParameter(confirmLevelRequired, "confirmLevelRequired");
        this.toolExecutor = toolExecutor;
        this.pipeline = pipeline;
        this.bridge = bridge;
        this.verifier = verifier;
        this.riskClassifier = riskClassifier;
        this.confirmLevelRequired = confirmLevelRequired;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ ExecutionOrchestrator(ToolExecutor toolExecutor, AutonomyActionPipeline autonomyActionPipeline, LiveSkillRiskBridge liveSkillRiskBridge, ResultVerifier resultVerifier, ActionRiskClassifier actionRiskClassifier, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(toolExecutor, (i & 2) != 0 ? new AutonomyActionPipeline(null, null, null, 7, null) : autonomyActionPipeline, (i & 4) != 0 ? new LiveSkillRiskBridge(null, 1, 0 == true ? 1 : 0) : liveSkillRiskBridge, (i & 8) != 0 ? new DefaultResultVerifier() : resultVerifier, (i & 16) != 0 ? new ActionRiskClassifier() : actionRiskClassifier, (i & 32) != 0 ? new Function0() { // from class: com.jarvis.core.runtime.ExecutionOrchestrator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(ExecutionOrchestrator._init_$lambda$0());
            }
        } : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return true;
    }

    /* JADX INFO: compiled from: ExecutionOrchestrator.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome;", "", "<init>", "()V", "Executed", "NeedsConfirmation", "Denied", "Failed", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$Denied;", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$Executed;", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$Failed;", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$NeedsConfirmation;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Outcome {
        public /* synthetic */ Outcome(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: ExecutionOrchestrator.kt */
        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$Executed;", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome;", "toolResult", "Lcom/jarvis/core/common/model/ToolResult;", "verification", "Lcom/jarvis/core/verification/VerificationResult;", "autonomyStatus", "Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "<init>", "(Lcom/jarvis/core/common/model/ToolResult;Lcom/jarvis/core/verification/VerificationResult;Lcom/jarvis/core/autonomy/AutonomyPlanStatus;)V", "getToolResult", "()Lcom/jarvis/core/common/model/ToolResult;", "getVerification", "()Lcom/jarvis/core/verification/VerificationResult;", "getAutonomyStatus", "()Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Executed extends Outcome {
            private final AutonomyPlanStatus autonomyStatus;
            private final ToolResult toolResult;
            private final VerificationResult verification;

            public static /* synthetic */ Executed copy$default(Executed executed, ToolResult toolResult, VerificationResult verificationResult, AutonomyPlanStatus autonomyPlanStatus, int i, Object obj) {
                if ((i & 1) != 0) {
                    toolResult = executed.toolResult;
                }
                if ((i & 2) != 0) {
                    verificationResult = executed.verification;
                }
                if ((i & 4) != 0) {
                    autonomyPlanStatus = executed.autonomyStatus;
                }
                return executed.copy(toolResult, verificationResult, autonomyPlanStatus);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final ToolResult getToolResult() {
                return this.toolResult;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final VerificationResult getVerification() {
                return this.verification;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }

            public final Executed copy(ToolResult toolResult, VerificationResult verification, AutonomyPlanStatus autonomyStatus) {
                Intrinsics.checkNotNullParameter(toolResult, "toolResult");
                Intrinsics.checkNotNullParameter(verification, "verification");
                Intrinsics.checkNotNullParameter(autonomyStatus, "autonomyStatus");
                return new Executed(toolResult, verification, autonomyStatus);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Executed)) {
                    return false;
                }
                Executed executed = (Executed) other;
                return Intrinsics.areEqual(this.toolResult, executed.toolResult) && Intrinsics.areEqual(this.verification, executed.verification) && this.autonomyStatus == executed.autonomyStatus;
            }

            public int hashCode() {
                return (((this.toolResult.hashCode() * 31) + this.verification.hashCode()) * 31) + this.autonomyStatus.hashCode();
            }

            public String toString() {
                return "Executed(toolResult=" + this.toolResult + ", verification=" + this.verification + ", autonomyStatus=" + this.autonomyStatus + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Executed(ToolResult toolResult, VerificationResult verification, AutonomyPlanStatus autonomyStatus) {
                super(null);
                Intrinsics.checkNotNullParameter(toolResult, "toolResult");
                Intrinsics.checkNotNullParameter(verification, "verification");
                Intrinsics.checkNotNullParameter(autonomyStatus, "autonomyStatus");
                this.toolResult = toolResult;
                this.verification = verification;
                this.autonomyStatus = autonomyStatus;
            }

            public final ToolResult getToolResult() {
                return this.toolResult;
            }

            public final VerificationResult getVerification() {
                return this.verification;
            }

            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }
        }

        private Outcome() {
        }

        /* JADX INFO: compiled from: ExecutionOrchestrator.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$NeedsConfirmation;", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome;", "reason", "", "integrityFingerprint", "autonomyStatus", "Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/autonomy/AutonomyPlanStatus;)V", "getReason", "()Ljava/lang/String;", "getIntegrityFingerprint", "getAutonomyStatus", "()Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedsConfirmation extends Outcome {
            private final AutonomyPlanStatus autonomyStatus;
            private final String integrityFingerprint;
            private final String reason;

            public static /* synthetic */ NeedsConfirmation copy$default(NeedsConfirmation needsConfirmation, String str, String str2, AutonomyPlanStatus autonomyPlanStatus, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = needsConfirmation.reason;
                }
                if ((i & 2) != 0) {
                    str2 = needsConfirmation.integrityFingerprint;
                }
                if ((i & 4) != 0) {
                    autonomyPlanStatus = needsConfirmation.autonomyStatus;
                }
                return needsConfirmation.copy(str, str2, autonomyPlanStatus);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getReason() {
                return this.reason;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getIntegrityFingerprint() {
                return this.integrityFingerprint;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }

            public final NeedsConfirmation copy(String reason, String integrityFingerprint, AutonomyPlanStatus autonomyStatus) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                Intrinsics.checkNotNullParameter(autonomyStatus, "autonomyStatus");
                return new NeedsConfirmation(reason, integrityFingerprint, autonomyStatus);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof NeedsConfirmation)) {
                    return false;
                }
                NeedsConfirmation needsConfirmation = (NeedsConfirmation) other;
                return Intrinsics.areEqual(this.reason, needsConfirmation.reason) && Intrinsics.areEqual(this.integrityFingerprint, needsConfirmation.integrityFingerprint) && this.autonomyStatus == needsConfirmation.autonomyStatus;
            }

            public int hashCode() {
                return (((this.reason.hashCode() * 31) + (this.integrityFingerprint == null ? 0 : this.integrityFingerprint.hashCode())) * 31) + this.autonomyStatus.hashCode();
            }

            public String toString() {
                return "NeedsConfirmation(reason=" + this.reason + ", integrityFingerprint=" + this.integrityFingerprint + ", autonomyStatus=" + this.autonomyStatus + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NeedsConfirmation(String reason, String integrityFingerprint, AutonomyPlanStatus autonomyStatus) {
                super(null);
                Intrinsics.checkNotNullParameter(reason, "reason");
                Intrinsics.checkNotNullParameter(autonomyStatus, "autonomyStatus");
                this.reason = reason;
                this.integrityFingerprint = integrityFingerprint;
                this.autonomyStatus = autonomyStatus;
            }

            public final String getReason() {
                return this.reason;
            }

            public final String getIntegrityFingerprint() {
                return this.integrityFingerprint;
            }

            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }
        }

        /* JADX INFO: compiled from: ExecutionOrchestrator.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$Denied;", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome;", "reason", "", "autonomyStatus", "Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/autonomy/AutonomyPlanStatus;)V", "getReason", "()Ljava/lang/String;", "getAutonomyStatus", "()Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Denied extends Outcome {
            private final AutonomyPlanStatus autonomyStatus;
            private final String reason;

            public static /* synthetic */ Denied copy$default(Denied denied, String str, AutonomyPlanStatus autonomyPlanStatus, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = denied.reason;
                }
                if ((i & 2) != 0) {
                    autonomyPlanStatus = denied.autonomyStatus;
                }
                return denied.copy(str, autonomyPlanStatus);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getReason() {
                return this.reason;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }

            public final Denied copy(String reason, AutonomyPlanStatus autonomyStatus) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new Denied(reason, autonomyStatus);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Denied)) {
                    return false;
                }
                Denied denied = (Denied) other;
                return Intrinsics.areEqual(this.reason, denied.reason) && this.autonomyStatus == denied.autonomyStatus;
            }

            public int hashCode() {
                return (this.reason.hashCode() * 31) + (this.autonomyStatus == null ? 0 : this.autonomyStatus.hashCode());
            }

            public String toString() {
                return "Denied(reason=" + this.reason + ", autonomyStatus=" + this.autonomyStatus + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Denied(String reason, AutonomyPlanStatus autonomyStatus) {
                super(null);
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
                this.autonomyStatus = autonomyStatus;
            }

            public final String getReason() {
                return this.reason;
            }

            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }
        }

        /* JADX INFO: compiled from: ExecutionOrchestrator.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome$Failed;", "Lcom/jarvis/core/runtime/ExecutionOrchestrator$Outcome;", "reason", "", "autonomyStatus", "Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/autonomy/AutonomyPlanStatus;)V", "getReason", "()Ljava/lang/String;", "getAutonomyStatus", "()Lcom/jarvis/core/autonomy/AutonomyPlanStatus;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Failed extends Outcome {
            private final AutonomyPlanStatus autonomyStatus;
            private final String reason;

            public static /* synthetic */ Failed copy$default(Failed failed, String str, AutonomyPlanStatus autonomyPlanStatus, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = failed.reason;
                }
                if ((i & 2) != 0) {
                    autonomyPlanStatus = failed.autonomyStatus;
                }
                return failed.copy(str, autonomyPlanStatus);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getReason() {
                return this.reason;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }

            public final Failed copy(String reason, AutonomyPlanStatus autonomyStatus) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new Failed(reason, autonomyStatus);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Failed)) {
                    return false;
                }
                Failed failed = (Failed) other;
                return Intrinsics.areEqual(this.reason, failed.reason) && this.autonomyStatus == failed.autonomyStatus;
            }

            public int hashCode() {
                return (this.reason.hashCode() * 31) + (this.autonomyStatus == null ? 0 : this.autonomyStatus.hashCode());
            }

            public String toString() {
                return "Failed(reason=" + this.reason + ", autonomyStatus=" + this.autonomyStatus + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Failed(String reason, AutonomyPlanStatus autonomyStatus) {
                super(null);
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
                this.autonomyStatus = autonomyStatus;
            }

            public /* synthetic */ Failed(String str, AutonomyPlanStatus autonomyPlanStatus, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? null : autonomyPlanStatus);
            }

            public final String getReason() {
                return this.reason;
            }

            public final AutonomyPlanStatus getAutonomyStatus() {
                return this.autonomyStatus;
            }
        }
    }

    public final Outcome executeAction(ToolCall toolCall, ToolDefinition definition, String requestId, boolean userConfirmed, String priorFingerprint, long nowMillis, AutonomyLevel autonomyLevel) {
        ProposedAction proposedActionCopy$default;
        Set<String> setEmptySet;
        Intrinsics.checkNotNullParameter(toolCall, "toolCall");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(autonomyLevel, "autonomyLevel");
        LiveSkillRiskBridge.BridgedSkill bridged = this.bridge.bridge(toolCall, definition);
        boolean confirmBypass = definition.getRiskLevel() == RiskLevel.CONFIRM && !this.confirmLevelRequired.invoke().booleanValue() && this.riskClassifier.classify(bridged.getProposed()).getRiskClass().ordinal() <= ActionRiskClass.MEDIUM.ordinal();
        PolicyDecision.Allow legacy = (confirmBypass && (bridged.getLegacy() instanceof PolicyDecision.RequiresConfirmation)) ? PolicyDecision.Allow.INSTANCE : bridged.getLegacy();
        PolicyDecision legacyDecision = legacy;
        if (legacy instanceof PolicyDecision.Deny) {
            this.riskClassifier.classify(bridged.getProposed());
            return new Outcome.Denied(((PolicyDecision.Deny) legacy).getReason(), AutonomyPlanStatus.DENIED);
        }
        if (legacy instanceof PolicyDecision.RequiresConfirmation) {
            if (!userConfirmed) {
                ActionRiskAssessment assessment = this.riskClassifier.classify(bridged.getProposed());
                String fingerprint = ActionIntegrity.INSTANCE.fingerprint(bridged.getProposed(), assessment);
                return new Outcome.NeedsConfirmation(((PolicyDecision.RequiresConfirmation) legacy).getReason(), fingerprint, AutonomyActionPipeline.plan$default(this.pipeline, userUtteranceDecision(toolCall.getToolId(), nowMillis), bridged.getProposed(), autonomyLevel, bridged.getAllowlist(), null, nowMillis, requestId, SetsKt.emptySet(), null, 0L, DecisionSource.USER_UTTERANCE, allowedKeys(bridged.getProposed().getArguments()), SetsKt.emptySet(), 784, null).getStatus());
            }
        } else if (!(legacy instanceof PolicyDecision.Allow)) {
            throw new NoWhenBranchMatchedException();
        }
        ProposedAction proposedBase = bridged.getProposed();
        if (userConfirmed || confirmBypass) {
            proposedActionCopy$default = ProposedAction.copy$default(proposedBase, null, (CollectionsKt.contains(LiveSkillRiskBridge.INSTANCE.getREAD_TOOL_IDS(), proposedBase.getToolId()) || proposedBase.getKind() == ActionKind.READ_ONLY) ? ActionKind.READ_ONLY : ActionKind.LOCAL_DEVICE_SAFE, null, null, null, 29, null);
        } else {
            proposedActionCopy$default = proposedBase;
        }
        ProposedAction proposed = proposedActionCopy$default;
        ActionRiskAssessment assessment2 = this.riskClassifier.classify(proposed);
        String fingerprint2 = priorFingerprint == null ? ActionIntegrity.INSTANCE.fingerprint(proposed, assessment2) : priorFingerprint;
        Set<String> setRequiredPermissionsFor = ActionArgumentRules.INSTANCE.requiredPermissionsFor(proposed);
        switch (WhenMappings.$EnumSwitchMapping$0[proposed.getKind().ordinal()]) {
            case 1:
            case 2:
            case 3:
                setEmptySet = SetsKt.emptySet();
                break;
            default:
                Set<String> of = setRequiredPermissionsFor;
                if (of.isEmpty()) {
                    of = SetsKt.setOf("capability:LIVE_USER");
                }
                setEmptySet = of;
                break;
        }
        AutonomyActionPlan plan = AutonomyActionPipeline.plan$default(this.pipeline, userUtteranceDecision(toolCall.getToolId(), nowMillis), proposed, autonomyLevel, (userConfirmed || confirmBypass) ? SetsKt.emptySet() : bridged.getAllowlist(), null, nowMillis, requestId, (userConfirmed || (legacyDecision instanceof PolicyDecision.Allow)) ? setEmptySet : SetsKt.emptySet(), null, 0L, DecisionSource.USER_UTTERANCE, allowedKeys(proposed.getArguments()), setEmptySet, 528, null);
        switch (WhenMappings.$EnumSwitchMapping$1[plan.getStatus().ordinal()]) {
            case 1:
                ToolCall draftToolCall = plan.getDraftToolCall();
                if (draftToolCall == null) {
                    draftToolCall = toolCall;
                }
                ToolCall call = ToolCall.copy$default(draftToolCall, null, requestId, null, null, 13, null);
                ToolCall execCall = Intrinsics.areEqual(call.getToolId(), toolCall.getToolId()) ? ToolCall.copy$default(toolCall, null, requestId, null, null, 13, null) : call;
                ToolResult result = this.toolExecutor.execute(execCall);
                VerificationResult verification = softVerify(execCall, result, requestId);
                return new Outcome.Executed(result, verification, plan.getStatus());
            case 2:
            case 3:
                String reason = plan.getVerdict().getReason();
                if (StringsKt.isBlank(reason)) {
                    reason = "Confirmation required";
                }
                return new Outcome.NeedsConfirmation(reason, fingerprint2, plan.getStatus());
            case 4:
            case 5:
                String reason2 = plan.getVerdict().getReason();
                if (StringsKt.isBlank(reason2)) {
                    reason2 = "Denied by autonomy pipeline";
                }
                return new Outcome.Denied(reason2, plan.getStatus());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final VerificationResult softVerify(ToolCall call, ToolResult result, String requestId) {
        Map<String, String> facts;
        String on;
        Map<String, String> mapStringifyArgs = LiveSkillRiskBridge.INSTANCE.stringifyArgs(call.getArguments());
        Iterable<Map.Entry> $this$associate$iv = CollectionsKt.take(result.getData().entrySet(), 16);
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Map.Entry entry : $this$associate$iv) {
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            Pair pair = TuplesKt.to(StringsKt.take(k, 64), StringsKt.take(v.toString(), 256));
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
        }
        Map facts2 = MapsKt.toMutableMap(destination$iv$iv);
        if (Intrinsics.areEqual(call.getToolId(), "flashlight")) {
            String str = (String) facts2.get("on");
            if (str != null) {
                on = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(on, "toLowerCase(...)");
            } else {
                on = null;
            }
            if (Intrinsics.areEqual(on, "true") || Intrinsics.areEqual(on, "on")) {
                facts2.putIfAbsent("flashlight", "on");
            }
            if (Intrinsics.areEqual(on, "false") || Intrinsics.areEqual(on, "off")) {
                facts2.putIfAbsent("flashlight", "off");
            }
            String a = (String) facts2.get("action");
            if (a != null && (Intrinsics.areEqual(a, "on") || Intrinsics.areEqual(a, "off"))) {
                facts2.putIfAbsent("flashlight", a);
            }
        }
        ObservedState observed = facts2.isEmpty() ? null : new ObservedState(System.currentTimeMillis(), facts2, null, 4, null);
        Iterable $this$filter$iv = ExpectedEffects.INSTANCE.forTool(call.getToolId(), mapStringifyArgs);
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ExpectedEffect e = (ExpectedEffect) element$iv$iv;
            boolean z = false;
            if (observed != null && (facts = observed.getFacts()) != null && facts.containsKey(e.getKey())) {
                z = true;
            }
            if (z) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List expected = (List) destination$iv$iv2;
        return this.verifier.verify(new VerificationRequest(requestId + "/verify", call.getToolId(), result.getStatus().name(), result.getMessage(), facts2, expected, observed, false, 128, null));
    }

    private final ProactiveDecision userUtteranceDecision(String toolId, long nowMillis) {
        return new ProactiveDecision(ProactiveDecisionType.SUGGESTION, "live.skill:" + toolId, null, null, ProactiveReasonCode.SELECTED, "V2-01 live skill execution", 0.95f, 0.95f, nowMillis, null, null, null, 3596, null);
    }

    private final Set<String> allowedKeys(Map<String, String> args) {
        return SetsKt.plus((Set) args.keySet(), (Iterable) SetsKt.setOf((Object[]) new String[]{"action", SearchIntents.EXTRA_QUERY, "city", VoiceService.EXTRA_TEXT, "app", "package", "name"}));
    }
}
