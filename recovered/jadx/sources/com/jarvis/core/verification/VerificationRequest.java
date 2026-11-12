package com.jarvis.core.verification;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VerificationModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010&\u001a\u00020\u000fHÆ\u0003Js\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010(\u001a\u00020\u000f2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006-"}, d2 = {"Lcom/jarvis/core/verification/VerificationRequest;", "", "stepId", "", "toolId", "toolResultStatus", "toolResultMessage", "toolResultData", "", "expectedEffects", "", "Lcom/jarvis/core/verification/ExpectedEffect;", "observed", "Lcom/jarvis/core/verification/ObservedState;", "irreversibleAction", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;Lcom/jarvis/core/verification/ObservedState;Z)V", "getStepId", "()Ljava/lang/String;", "getToolId", "getToolResultStatus", "getToolResultMessage", "getToolResultData", "()Ljava/util/Map;", "getExpectedEffects", "()Ljava/util/List;", "getObserved", "()Lcom/jarvis/core/verification/ObservedState;", "getIrreversibleAction", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VerificationRequest {
    private final List<ExpectedEffect> expectedEffects;
    private final boolean irreversibleAction;
    private final ObservedState observed;
    private final String stepId;
    private final String toolId;
    private final Map<String, String> toolResultData;
    private final String toolResultMessage;
    private final String toolResultStatus;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getStepId() {
        return this.stepId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getToolResultStatus() {
        return this.toolResultStatus;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getToolResultMessage() {
        return this.toolResultMessage;
    }

    public final Map<String, String> component5() {
        return this.toolResultData;
    }

    public final List<ExpectedEffect> component6() {
        return this.expectedEffects;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final ObservedState getObserved() {
        return this.observed;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getIrreversibleAction() {
        return this.irreversibleAction;
    }

    public final VerificationRequest copy(String stepId, String toolId, String toolResultStatus, String toolResultMessage, Map<String, String> toolResultData, List<ExpectedEffect> expectedEffects, ObservedState observed, boolean irreversibleAction) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(toolResultData, "toolResultData");
        Intrinsics.checkNotNullParameter(expectedEffects, "expectedEffects");
        return new VerificationRequest(stepId, toolId, toolResultStatus, toolResultMessage, toolResultData, expectedEffects, observed, irreversibleAction);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VerificationRequest)) {
            return false;
        }
        VerificationRequest verificationRequest = (VerificationRequest) other;
        return Intrinsics.areEqual(this.stepId, verificationRequest.stepId) && Intrinsics.areEqual(this.toolId, verificationRequest.toolId) && Intrinsics.areEqual(this.toolResultStatus, verificationRequest.toolResultStatus) && Intrinsics.areEqual(this.toolResultMessage, verificationRequest.toolResultMessage) && Intrinsics.areEqual(this.toolResultData, verificationRequest.toolResultData) && Intrinsics.areEqual(this.expectedEffects, verificationRequest.expectedEffects) && Intrinsics.areEqual(this.observed, verificationRequest.observed) && this.irreversibleAction == verificationRequest.irreversibleAction;
    }

    public int hashCode() {
        return (((((((((((((this.stepId.hashCode() * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + (this.toolResultStatus == null ? 0 : this.toolResultStatus.hashCode())) * 31) + (this.toolResultMessage == null ? 0 : this.toolResultMessage.hashCode())) * 31) + this.toolResultData.hashCode()) * 31) + this.expectedEffects.hashCode()) * 31) + (this.observed != null ? this.observed.hashCode() : 0)) * 31) + Boolean.hashCode(this.irreversibleAction);
    }

    public String toString() {
        return "VerificationRequest(stepId=" + this.stepId + ", toolId=" + this.toolId + ", toolResultStatus=" + this.toolResultStatus + ", toolResultMessage=" + this.toolResultMessage + ", toolResultData=" + this.toolResultData + ", expectedEffects=" + this.expectedEffects + ", observed=" + this.observed + ", irreversibleAction=" + this.irreversibleAction + ")";
    }

    public VerificationRequest(String stepId, String toolId, String toolResultStatus, String toolResultMessage, Map<String, String> toolResultData, List<ExpectedEffect> expectedEffects, ObservedState observed, boolean irreversibleAction) {
        Intrinsics.checkNotNullParameter(stepId, "stepId");
        Intrinsics.checkNotNullParameter(toolResultData, "toolResultData");
        Intrinsics.checkNotNullParameter(expectedEffects, "expectedEffects");
        this.stepId = stepId;
        this.toolId = toolId;
        this.toolResultStatus = toolResultStatus;
        this.toolResultMessage = toolResultMessage;
        this.toolResultData = toolResultData;
        this.expectedEffects = expectedEffects;
        this.observed = observed;
        this.irreversibleAction = irreversibleAction;
        if (StringsKt.isBlank(this.stepId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.expectedEffects.size() <= 16)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.toolResultData.size() <= 16) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ VerificationRequest(String str, String str2, String str3, String str4, Map map, List list, ObservedState observedState, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? CollectionsKt.emptyList() : list, (i & 64) == 0 ? observedState : null, (i & 128) != 0 ? false : z);
    }

    public final String getStepId() {
        return this.stepId;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final String getToolResultStatus() {
        return this.toolResultStatus;
    }

    public final String getToolResultMessage() {
        return this.toolResultMessage;
    }

    public final Map<String, String> getToolResultData() {
        return this.toolResultData;
    }

    public final List<ExpectedEffect> getExpectedEffects() {
        return this.expectedEffects;
    }

    public final ObservedState getObserved() {
        return this.observed;
    }

    public final boolean getIrreversibleAction() {
        return this.irreversibleAction;
    }
}
