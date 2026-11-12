package com.jarvis.brain;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LlmStructuredDecision.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003JI\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006#"}, d2 = {"Lcom/jarvis/brain/LlmStructuredDecision;", "", "kind", "Lcom/jarvis/brain/LlmDecisionKind;", "utterance", "", "confidence", "", "goal", "capabilityHint", "raw", "<init>", "(Lcom/jarvis/brain/LlmDecisionKind;Ljava/lang/String;FLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getKind", "()Lcom/jarvis/brain/LlmDecisionKind;", "getUtterance", "()Ljava/lang/String;", "getConfidence", "()F", "getGoal", "getCapabilityHint", "getRaw", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LlmStructuredDecision {
    private final String capabilityHint;
    private final float confidence;
    private final String goal;
    private final LlmDecisionKind kind;
    private final String raw;
    private final String utterance;

    public static /* synthetic */ LlmStructuredDecision copy$default(LlmStructuredDecision llmStructuredDecision, LlmDecisionKind llmDecisionKind, String str, float f, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            llmDecisionKind = llmStructuredDecision.kind;
        }
        if ((i & 2) != 0) {
            str = llmStructuredDecision.utterance;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            f = llmStructuredDecision.confidence;
        }
        float f2 = f;
        if ((i & 8) != 0) {
            str2 = llmStructuredDecision.goal;
        }
        String str6 = str2;
        if ((i & 16) != 0) {
            str3 = llmStructuredDecision.capabilityHint;
        }
        String str7 = str3;
        if ((i & 32) != 0) {
            str4 = llmStructuredDecision.raw;
        }
        return llmStructuredDecision.copy(llmDecisionKind, str5, f2, str6, str7, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LlmDecisionKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUtterance() {
        return this.utterance;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGoal() {
        return this.goal;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCapabilityHint() {
        return this.capabilityHint;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getRaw() {
        return this.raw;
    }

    public final LlmStructuredDecision copy(LlmDecisionKind kind, String utterance, float confidence, String goal, String capabilityHint, String raw) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(utterance, "utterance");
        Intrinsics.checkNotNullParameter(raw, "raw");
        return new LlmStructuredDecision(kind, utterance, confidence, goal, capabilityHint, raw);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LlmStructuredDecision)) {
            return false;
        }
        LlmStructuredDecision llmStructuredDecision = (LlmStructuredDecision) other;
        return this.kind == llmStructuredDecision.kind && Intrinsics.areEqual(this.utterance, llmStructuredDecision.utterance) && Float.compare(this.confidence, llmStructuredDecision.confidence) == 0 && Intrinsics.areEqual(this.goal, llmStructuredDecision.goal) && Intrinsics.areEqual(this.capabilityHint, llmStructuredDecision.capabilityHint) && Intrinsics.areEqual(this.raw, llmStructuredDecision.raw);
    }

    public int hashCode() {
        return (((((((((this.kind.hashCode() * 31) + this.utterance.hashCode()) * 31) + Float.hashCode(this.confidence)) * 31) + (this.goal == null ? 0 : this.goal.hashCode())) * 31) + (this.capabilityHint != null ? this.capabilityHint.hashCode() : 0)) * 31) + this.raw.hashCode();
    }

    public String toString() {
        return "LlmStructuredDecision(kind=" + this.kind + ", utterance=" + this.utterance + ", confidence=" + this.confidence + ", goal=" + this.goal + ", capabilityHint=" + this.capabilityHint + ", raw=" + this.raw + ")";
    }

    public LlmStructuredDecision(LlmDecisionKind kind, String utterance, float confidence, String goal, String capabilityHint, String raw) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(utterance, "utterance");
        Intrinsics.checkNotNullParameter(raw, "raw");
        this.kind = kind;
        this.utterance = utterance;
        this.confidence = confidence;
        this.goal = goal;
        this.capabilityHint = capabilityHint;
        this.raw = raw;
    }

    public /* synthetic */ LlmStructuredDecision(LlmDecisionKind llmDecisionKind, String str, float f, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(llmDecisionKind, str, (i & 4) != 0 ? 0.6f : f, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? "" : str4);
    }

    public final LlmDecisionKind getKind() {
        return this.kind;
    }

    public final String getUtterance() {
        return this.utterance;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final String getGoal() {
        return this.goal;
    }

    public final String getCapabilityHint() {
        return this.capabilityHint;
    }

    public final String getRaw() {
        return this.raw;
    }
}
