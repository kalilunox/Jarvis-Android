package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CandidateFilterResult.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003Jc\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006)"}, d2 = {"Lcom/jarvis/core/proactive/DecisionPolicyThresholds;", "", "minConfidenceEligible", "", "minRelevanceEligible", "minConfidenceSuggestion", "minRelevanceSuggestion", "minConfidenceQuestion", "minRelevanceQuestion", "maxConfidenceQuestion", "minConfidenceConfirmation", "minRelevanceConfirmation", "<init>", "(FFFFFFFFF)V", "getMinConfidenceEligible", "()F", "getMinRelevanceEligible", "getMinConfidenceSuggestion", "getMinRelevanceSuggestion", "getMinConfidenceQuestion", "getMinRelevanceQuestion", "getMaxConfidenceQuestion", "getMinConfidenceConfirmation", "getMinRelevanceConfirmation", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DecisionPolicyThresholds {
    private final float maxConfidenceQuestion;
    private final float minConfidenceConfirmation;
    private final float minConfidenceEligible;
    private final float minConfidenceQuestion;
    private final float minConfidenceSuggestion;
    private final float minRelevanceConfirmation;
    private final float minRelevanceEligible;
    private final float minRelevanceQuestion;
    private final float minRelevanceSuggestion;

    public DecisionPolicyThresholds() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 511, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getMinConfidenceEligible() {
        return this.minConfidenceEligible;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getMinRelevanceEligible() {
        return this.minRelevanceEligible;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getMinConfidenceSuggestion() {
        return this.minConfidenceSuggestion;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getMinRelevanceSuggestion() {
        return this.minRelevanceSuggestion;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getMinConfidenceQuestion() {
        return this.minConfidenceQuestion;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getMinRelevanceQuestion() {
        return this.minRelevanceQuestion;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getMaxConfidenceQuestion() {
        return this.maxConfidenceQuestion;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final float getMinConfidenceConfirmation() {
        return this.minConfidenceConfirmation;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final float getMinRelevanceConfirmation() {
        return this.minRelevanceConfirmation;
    }

    public final DecisionPolicyThresholds copy(float minConfidenceEligible, float minRelevanceEligible, float minConfidenceSuggestion, float minRelevanceSuggestion, float minConfidenceQuestion, float minRelevanceQuestion, float maxConfidenceQuestion, float minConfidenceConfirmation, float minRelevanceConfirmation) {
        return new DecisionPolicyThresholds(minConfidenceEligible, minRelevanceEligible, minConfidenceSuggestion, minRelevanceSuggestion, minConfidenceQuestion, minRelevanceQuestion, maxConfidenceQuestion, minConfidenceConfirmation, minRelevanceConfirmation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DecisionPolicyThresholds)) {
            return false;
        }
        DecisionPolicyThresholds decisionPolicyThresholds = (DecisionPolicyThresholds) other;
        return Float.compare(this.minConfidenceEligible, decisionPolicyThresholds.minConfidenceEligible) == 0 && Float.compare(this.minRelevanceEligible, decisionPolicyThresholds.minRelevanceEligible) == 0 && Float.compare(this.minConfidenceSuggestion, decisionPolicyThresholds.minConfidenceSuggestion) == 0 && Float.compare(this.minRelevanceSuggestion, decisionPolicyThresholds.minRelevanceSuggestion) == 0 && Float.compare(this.minConfidenceQuestion, decisionPolicyThresholds.minConfidenceQuestion) == 0 && Float.compare(this.minRelevanceQuestion, decisionPolicyThresholds.minRelevanceQuestion) == 0 && Float.compare(this.maxConfidenceQuestion, decisionPolicyThresholds.maxConfidenceQuestion) == 0 && Float.compare(this.minConfidenceConfirmation, decisionPolicyThresholds.minConfidenceConfirmation) == 0 && Float.compare(this.minRelevanceConfirmation, decisionPolicyThresholds.minRelevanceConfirmation) == 0;
    }

    public int hashCode() {
        return (((((((((((((((Float.hashCode(this.minConfidenceEligible) * 31) + Float.hashCode(this.minRelevanceEligible)) * 31) + Float.hashCode(this.minConfidenceSuggestion)) * 31) + Float.hashCode(this.minRelevanceSuggestion)) * 31) + Float.hashCode(this.minConfidenceQuestion)) * 31) + Float.hashCode(this.minRelevanceQuestion)) * 31) + Float.hashCode(this.maxConfidenceQuestion)) * 31) + Float.hashCode(this.minConfidenceConfirmation)) * 31) + Float.hashCode(this.minRelevanceConfirmation);
    }

    public String toString() {
        return "DecisionPolicyThresholds(minConfidenceEligible=" + this.minConfidenceEligible + ", minRelevanceEligible=" + this.minRelevanceEligible + ", minConfidenceSuggestion=" + this.minConfidenceSuggestion + ", minRelevanceSuggestion=" + this.minRelevanceSuggestion + ", minConfidenceQuestion=" + this.minConfidenceQuestion + ", minRelevanceQuestion=" + this.minRelevanceQuestion + ", maxConfidenceQuestion=" + this.maxConfidenceQuestion + ", minConfidenceConfirmation=" + this.minConfidenceConfirmation + ", minRelevanceConfirmation=" + this.minRelevanceConfirmation + ")";
    }

    public DecisionPolicyThresholds(float minConfidenceEligible, float minRelevanceEligible, float minConfidenceSuggestion, float minRelevanceSuggestion, float minConfidenceQuestion, float minRelevanceQuestion, float maxConfidenceQuestion, float minConfidenceConfirmation, float minRelevanceConfirmation) {
        this.minConfidenceEligible = minConfidenceEligible;
        this.minRelevanceEligible = minRelevanceEligible;
        this.minConfidenceSuggestion = minConfidenceSuggestion;
        this.minRelevanceSuggestion = minRelevanceSuggestion;
        this.minConfidenceQuestion = minConfidenceQuestion;
        this.minRelevanceQuestion = minRelevanceQuestion;
        this.maxConfidenceQuestion = maxConfidenceQuestion;
        this.minConfidenceConfirmation = minConfidenceConfirmation;
        this.minRelevanceConfirmation = minRelevanceConfirmation;
    }

    public /* synthetic */ DecisionPolicyThresholds(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.45f : f, (i & 2) != 0 ? 0.4f : f2, (i & 4) != 0 ? 0.55f : f3, (i & 8) == 0 ? f4 : 0.55f, (i & 16) != 0 ? 0.48f : f5, (i & 32) != 0 ? 0.62f : f6, (i & 64) != 0 ? 0.72f : f7, (i & 128) != 0 ? 0.7f : f8, (i & 256) != 0 ? 0.68f : f9);
    }

    public final float getMinConfidenceEligible() {
        return this.minConfidenceEligible;
    }

    public final float getMinRelevanceEligible() {
        return this.minRelevanceEligible;
    }

    public final float getMinConfidenceSuggestion() {
        return this.minConfidenceSuggestion;
    }

    public final float getMinRelevanceSuggestion() {
        return this.minRelevanceSuggestion;
    }

    public final float getMinConfidenceQuestion() {
        return this.minConfidenceQuestion;
    }

    public final float getMinRelevanceQuestion() {
        return this.minRelevanceQuestion;
    }

    public final float getMaxConfidenceQuestion() {
        return this.maxConfidenceQuestion;
    }

    public final float getMinConfidenceConfirmation() {
        return this.minConfidenceConfirmation;
    }

    public final float getMinRelevanceConfirmation() {
        return this.minRelevanceConfirmation;
    }
}
