package com.jarvis.core.planner;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActionPlanModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b3\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 K2\u00020\u0001:\u0001KBÉ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003J\t\u0010<\u001a\u00020\u000fHÆ\u0003J\t\u0010=\u001a\u00020\u0011HÆ\u0003J\t\u0010>\u001a\u00020\u0013HÆ\u0003J\t\u0010?\u001a\u00020\u0015HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0011HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\u000f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003JÏ\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00032\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0001J\u0013\u0010F\u001a\u00020\u00112\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020IHÖ\u0001J\t\u0010J\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001eR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001eR\u0011\u0010\u0018\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b2\u0010+R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001eR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b4\u0010&¨\u0006L"}, d2 = {"Lcom/jarvis/core/planner/PlanStep;", "", "stepId", "", "kind", "Lcom/jarvis/core/planner/PlanStepKind;", "toolId", "capabilityId", "parameters", "", "parameterBindings", "", "Lcom/jarvis/core/planner/PlanParameterBinding;", "dependsOn", "condition", "Lcom/jarvis/core/planner/PlanStepCondition;", "requiresConfirmation", "", "retry", "Lcom/jarvis/core/planner/PlanRetryPolicy;", "waitMs", "", "branchOnSuccess", "branchOnFailure", "cancelOnFailure", "displaySummary", "notes", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/planner/PlanStepKind;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Lcom/jarvis/core/planner/PlanStepCondition;ZLcom/jarvis/core/planner/PlanRetryPolicy;JLjava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/List;)V", "getStepId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/planner/PlanStepKind;", "getToolId", "getCapabilityId", "getParameters", "()Ljava/util/Map;", "getParameterBindings", "()Ljava/util/List;", "getDependsOn", "getCondition", "()Lcom/jarvis/core/planner/PlanStepCondition;", "getRequiresConfirmation", "()Z", "getRetry", "()Lcom/jarvis/core/planner/PlanRetryPolicy;", "getWaitMs", "()J", "getBranchOnSuccess", "getBranchOnFailure", "getCancelOnFailure", "getDisplaySummary", "getNotes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PlanStep {
    public static final int MAX_DEPS = 8;
    public static final int MAX_NOTES = 8;
    public static final int MAX_PARAMS = 16;
    public static final int MAX_PARAM_KEY = 64;
    public static final int MAX_PARAM_VALUE = 512;
    public static final long MAX_WAIT_MS = 3600000;
    private final String branchOnFailure;
    private final String branchOnSuccess;
    private final boolean cancelOnFailure;
    private final String capabilityId;
    private final PlanStepCondition condition;
    private final List<String> dependsOn;
    private final String displaySummary;
    private final PlanStepKind kind;
    private final List<String> notes;
    private final List<PlanParameterBinding> parameterBindings;
    private final Map<String, String> parameters;
    private final boolean requiresConfirmation;
    private final PlanRetryPolicy retry;
    private final String stepId;
    private final String toolId;
    private final long waitMs;

    /* JADX INFO: compiled from: ActionPlanModels.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlanStepKind.values().length];
            try {
                iArr[PlanStepKind.TOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PlanStepKind.WAIT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PlanStepKind.CONFIRM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PlanStepKind.BRANCH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[PlanStepKind.NOOP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getStepId() {
        return this.stepId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final PlanRetryPolicy getRetry() {
        return this.retry;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getWaitMs() {
        return this.waitMs;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getBranchOnSuccess() {
        return this.branchOnSuccess;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getBranchOnFailure() {
        return this.branchOnFailure;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final boolean getCancelOnFailure() {
        return this.cancelOnFailure;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getDisplaySummary() {
        return this.displaySummary;
    }

    public final List<String> component16() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PlanStepKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCapabilityId() {
        return this.capabilityId;
    }

    public final Map<String, String> component5() {
        return this.parameters;
    }

    public final List<PlanParameterBinding> component6() {
        return this.parameterBindings;
    }

    public final List<String> component7() {
        return this.dependsOn;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final PlanStepCondition getCondition() {
        return this.condition;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getRequiresConfirmation() {
        return this.requiresConfirmation;
    }

    public final PlanStep copy(String stepId, PlanStepKind kind, String toolId, String capabilityId, Map<String, String> parameters, List<PlanParameterBinding> parameterBindings, List<String> dependsOn, PlanStepCondition condition, boolean requiresConfirmation, PlanRetryPolicy retry, long waitMs, String branchOnSuccess, String branchOnFailure, boolean cancelOnFailure, String displaySummary, List<String> notes) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(parameterBindings, "parameterBindings");
        Intrinsics.checkNotNullParameter(dependsOn, "dependsOn");
        Intrinsics.checkNotNullParameter(condition, "condition");
        Intrinsics.checkNotNullParameter(retry, "retry");
        Intrinsics.checkNotNullParameter(displaySummary, "displaySummary");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new PlanStep(stepId, kind, toolId, capabilityId, parameters, parameterBindings, dependsOn, condition, requiresConfirmation, retry, waitMs, branchOnSuccess, branchOnFailure, cancelOnFailure, displaySummary, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlanStep)) {
            return false;
        }
        PlanStep planStep = (PlanStep) other;
        return Intrinsics.areEqual(this.stepId, planStep.stepId) && this.kind == planStep.kind && Intrinsics.areEqual(this.toolId, planStep.toolId) && Intrinsics.areEqual(this.capabilityId, planStep.capabilityId) && Intrinsics.areEqual(this.parameters, planStep.parameters) && Intrinsics.areEqual(this.parameterBindings, planStep.parameterBindings) && Intrinsics.areEqual(this.dependsOn, planStep.dependsOn) && Intrinsics.areEqual(this.condition, planStep.condition) && this.requiresConfirmation == planStep.requiresConfirmation && Intrinsics.areEqual(this.retry, planStep.retry) && this.waitMs == planStep.waitMs && Intrinsics.areEqual(this.branchOnSuccess, planStep.branchOnSuccess) && Intrinsics.areEqual(this.branchOnFailure, planStep.branchOnFailure) && this.cancelOnFailure == planStep.cancelOnFailure && Intrinsics.areEqual(this.displaySummary, planStep.displaySummary) && Intrinsics.areEqual(this.notes, planStep.notes);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.stepId.hashCode() * 31) + this.kind.hashCode()) * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + (this.capabilityId == null ? 0 : this.capabilityId.hashCode())) * 31) + this.parameters.hashCode()) * 31) + this.parameterBindings.hashCode()) * 31) + this.dependsOn.hashCode()) * 31) + this.condition.hashCode()) * 31) + Boolean.hashCode(this.requiresConfirmation)) * 31) + this.retry.hashCode()) * 31) + Long.hashCode(this.waitMs)) * 31) + (this.branchOnSuccess == null ? 0 : this.branchOnSuccess.hashCode())) * 31) + (this.branchOnFailure != null ? this.branchOnFailure.hashCode() : 0)) * 31) + Boolean.hashCode(this.cancelOnFailure)) * 31) + this.displaySummary.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "PlanStep(stepId=" + this.stepId + ", kind=" + this.kind + ", toolId=" + this.toolId + ", capabilityId=" + this.capabilityId + ", parameters=" + this.parameters + ", parameterBindings=" + this.parameterBindings + ", dependsOn=" + this.dependsOn + ", condition=" + this.condition + ", requiresConfirmation=" + this.requiresConfirmation + ", retry=" + this.retry + ", waitMs=" + this.waitMs + ", branchOnSuccess=" + this.branchOnSuccess + ", branchOnFailure=" + this.branchOnFailure + ", cancelOnFailure=" + this.cancelOnFailure + ", displaySummary=" + this.displaySummary + ", notes=" + this.notes + ")";
    }

    public PlanStep(String stepId, PlanStepKind kind, String toolId, String capabilityId, Map<String, String> parameters, List<PlanParameterBinding> parameterBindings, List<String> dependsOn, PlanStepCondition condition, boolean requiresConfirmation, PlanRetryPolicy retry, long waitMs, String branchOnSuccess, String branchOnFailure, boolean cancelOnFailure, String displaySummary, List<String> notes) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(parameterBindings, "parameterBindings");
        Intrinsics.checkNotNullParameter(dependsOn, "dependsOn");
        Intrinsics.checkNotNullParameter(condition, "condition");
        Intrinsics.checkNotNullParameter(retry, "retry");
        Intrinsics.checkNotNullParameter(displaySummary, "displaySummary");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.stepId = stepId;
        this.kind = kind;
        this.toolId = toolId;
        this.capabilityId = capabilityId;
        this.parameters = parameters;
        this.parameterBindings = parameterBindings;
        this.dependsOn = dependsOn;
        this.condition = condition;
        this.requiresConfirmation = requiresConfirmation;
        this.retry = retry;
        this.waitMs = waitMs;
        this.branchOnSuccess = branchOnSuccess;
        this.branchOnFailure = branchOnFailure;
        this.cancelOnFailure = cancelOnFailure;
        this.displaySummary = displaySummary;
        this.notes = notes;
        if (StringsKt.isBlank(this.stepId)) {
            throw new IllegalArgumentException("stepId blank".toString());
        }
        boolean z = true;
        if (!(this.parameters.size() <= 16)) {
            throw new IllegalArgumentException("too many parameters".toString());
        }
        Map<String, String> map = this.parameters;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            Map<String, String> map2 = map;
            if (!(k.length() <= 64)) {
                throw new IllegalArgumentException("param key too long".toString());
            }
            if (!(v.length() <= 512)) {
                throw new IllegalArgumentException("param value too long".toString());
            }
            map = map2;
        }
        if (!(this.parameterBindings.size() <= 16)) {
            throw new IllegalArgumentException("too many bindings".toString());
        }
        if (!(this.dependsOn.size() <= 8)) {
            throw new IllegalArgumentException("too many deps".toString());
        }
        long j = this.waitMs;
        if (!(0 <= j && j < 3600001)) {
            throw new IllegalArgumentException("waitMs out of bounds".toString());
        }
        if (!(this.notes.size() <= 8)) {
            throw new IllegalArgumentException("too many notes".toString());
        }
        switch (WhenMappings.$EnumSwitchMapping$0[this.kind.ordinal()]) {
            case 1:
                String str = this.toolId;
                if (str != null && !StringsKt.isBlank(str)) {
                    z = false;
                }
                if (z) {
                    throw new IllegalArgumentException("TOOL step needs toolId".toString());
                }
                return;
            case 2:
                if (!(this.waitMs > 0)) {
                    throw new IllegalArgumentException("WAIT step needs waitMs > 0".toString());
                }
                return;
            case 3:
                if (!this.requiresConfirmation && StringsKt.isBlank(this.displaySummary)) {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException("CONFIRM step needs confirmation intent".toString());
                }
                return;
            case 4:
                String str2 = this.branchOnSuccess;
                if (str2 == null || StringsKt.isBlank(str2)) {
                    String str3 = this.branchOnFailure;
                    if (str3 == null || StringsKt.isBlank(str3)) {
                        z = false;
                    }
                }
                if (!z) {
                    throw new IllegalArgumentException("BRANCH needs at least one branch target".toString());
                }
                return;
            case 5:
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public /* synthetic */ PlanStep(String str, PlanStepKind planStepKind, String str2, String str3, Map map, List list, List list2, PlanStepCondition planStepCondition, boolean z, PlanRetryPolicy planRetryPolicy, long j, String str4, String str5, boolean z2, String str6, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, planStepKind, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? CollectionsKt.emptyList() : list, (i & 64) != 0 ? CollectionsKt.emptyList() : list2, (i & 128) != 0 ? new PlanStepCondition(null, null, null, null, 15, null) : planStepCondition, (i & 256) != 0 ? false : z, (i & 512) != 0 ? new PlanRetryPolicy(0, false, 3, null) : planRetryPolicy, (i & 1024) != 0 ? 0L : j, (i & 2048) != 0 ? null : str4, (i & 4096) != 0 ? null : str5, (i & 8192) != 0 ? false : z2, (i & 16384) != 0 ? "" : str6, (i & 32768) != 0 ? CollectionsKt.emptyList() : list3);
    }

    public final String getStepId() {
        return this.stepId;
    }

    public final PlanStepKind getKind() {
        return this.kind;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final String getCapabilityId() {
        return this.capabilityId;
    }

    public final Map<String, String> getParameters() {
        return this.parameters;
    }

    public final List<PlanParameterBinding> getParameterBindings() {
        return this.parameterBindings;
    }

    public final List<String> getDependsOn() {
        return this.dependsOn;
    }

    public final PlanStepCondition getCondition() {
        return this.condition;
    }

    public final boolean getRequiresConfirmation() {
        return this.requiresConfirmation;
    }

    public final PlanRetryPolicy getRetry() {
        return this.retry;
    }

    public final long getWaitMs() {
        return this.waitMs;
    }

    public final String getBranchOnSuccess() {
        return this.branchOnSuccess;
    }

    public final String getBranchOnFailure() {
        return this.branchOnFailure;
    }

    public final boolean getCancelOnFailure() {
        return this.cancelOnFailure;
    }

    public final String getDisplaySummary() {
        return this.displaySummary;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
