package com.jarvis.core.aioutput;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StructuredAiModels.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u000eHÆ\u0003Jm\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010'\u001a\u00020\u000b2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredPlanStepDraft;", "", "stepId", "", "kind", "toolId", "parameters", "", "dependsOn", "", "requiresConfirmation", "", "displaySummary", "waitMs", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;ZLjava/lang/String;J)V", "getStepId", "()Ljava/lang/String;", "getKind", "getToolId", "getParameters", "()Ljava/util/Map;", "getDependsOn", "()Ljava/util/List;", "getRequiresConfirmation", "()Z", "getDisplaySummary", "getWaitMs", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StructuredPlanStepDraft {
    private final List<String> dependsOn;
    private final String displaySummary;
    private final String kind;
    private final Map<String, String> parameters;
    private final boolean requiresConfirmation;
    private final String stepId;
    private final String toolId;
    private final long waitMs;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getStepId() {
        return this.stepId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> component4() {
        return this.parameters;
    }

    public final List<String> component5() {
        return this.dependsOn;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getRequiresConfirmation() {
        return this.requiresConfirmation;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDisplaySummary() {
        return this.displaySummary;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getWaitMs() {
        return this.waitMs;
    }

    public final StructuredPlanStepDraft copy(String stepId, String kind, String toolId, Map<String, String> parameters, List<String> dependsOn, boolean requiresConfirmation, String displaySummary, long waitMs) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(dependsOn, "dependsOn");
        Intrinsics.checkNotNullParameter(displaySummary, "displaySummary");
        return new StructuredPlanStepDraft(stepId, kind, toolId, parameters, dependsOn, requiresConfirmation, displaySummary, waitMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StructuredPlanStepDraft)) {
            return false;
        }
        StructuredPlanStepDraft structuredPlanStepDraft = (StructuredPlanStepDraft) other;
        return Intrinsics.areEqual(this.stepId, structuredPlanStepDraft.stepId) && Intrinsics.areEqual(this.kind, structuredPlanStepDraft.kind) && Intrinsics.areEqual(this.toolId, structuredPlanStepDraft.toolId) && Intrinsics.areEqual(this.parameters, structuredPlanStepDraft.parameters) && Intrinsics.areEqual(this.dependsOn, structuredPlanStepDraft.dependsOn) && this.requiresConfirmation == structuredPlanStepDraft.requiresConfirmation && Intrinsics.areEqual(this.displaySummary, structuredPlanStepDraft.displaySummary) && this.waitMs == structuredPlanStepDraft.waitMs;
    }

    public int hashCode() {
        return (((((((((((((this.stepId.hashCode() * 31) + this.kind.hashCode()) * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + this.parameters.hashCode()) * 31) + this.dependsOn.hashCode()) * 31) + Boolean.hashCode(this.requiresConfirmation)) * 31) + this.displaySummary.hashCode()) * 31) + Long.hashCode(this.waitMs);
    }

    public String toString() {
        return "StructuredPlanStepDraft(stepId=" + this.stepId + ", kind=" + this.kind + ", toolId=" + this.toolId + ", parameters=" + this.parameters + ", dependsOn=" + this.dependsOn + ", requiresConfirmation=" + this.requiresConfirmation + ", displaySummary=" + this.displaySummary + ", waitMs=" + this.waitMs + ")";
    }

    public StructuredPlanStepDraft(String stepId, String kind, String toolId, Map<String, String> parameters, List<String> dependsOn, boolean requiresConfirmation, String displaySummary, long waitMs) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(dependsOn, "dependsOn");
        Intrinsics.checkNotNullParameter(displaySummary, "displaySummary");
        this.stepId = stepId;
        this.kind = kind;
        this.toolId = toolId;
        this.parameters = parameters;
        this.dependsOn = dependsOn;
        this.requiresConfirmation = requiresConfirmation;
        this.displaySummary = displaySummary;
        this.waitMs = waitMs;
    }

    public /* synthetic */ StructuredPlanStepDraft(String str, String str2, String str3, Map map, List list, boolean z, String str4, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? MapsKt.emptyMap() : map, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? false : z, (i & 64) != 0 ? "" : str4, (i & 128) != 0 ? 0L : j);
    }

    public final String getStepId() {
        return this.stepId;
    }

    public final String getKind() {
        return this.kind;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> getParameters() {
        return this.parameters;
    }

    public final List<String> getDependsOn() {
        return this.dependsOn;
    }

    public final boolean getRequiresConfirmation() {
        return this.requiresConfirmation;
    }

    public final String getDisplaySummary() {
        return this.displaySummary;
    }

    public final long getWaitMs() {
        return this.waitMs;
    }
}
