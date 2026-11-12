package com.jarvis.core.autonomy;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.core.proactive.ProactiveDecisionType;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutonomyModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010+\u001a\u00020\fHÆ\u0003J\t\u0010,\u001a\u00020\fHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0010HÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012HÆ\u0003Jy\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012HÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u00067"}, d2 = {"Lcom/jarvis/core/autonomy/ActionRequest;", "", "id", "", "source", "Lcom/jarvis/core/autonomy/DecisionSource;", "proposedAction", "Lcom/jarvis/core/autonomy/ProposedAction;", "behaviorPatternKey", "proactiveDecisionType", "Lcom/jarvis/core/proactive/ProactiveDecisionType;", "confidence", "", "relevance", ProactiveActionReceiver.EXTRA_FINGERPRINT, "createdAtMillis", "", "notes", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/autonomy/DecisionSource;Lcom/jarvis/core/autonomy/ProposedAction;Ljava/lang/String;Lcom/jarvis/core/proactive/ProactiveDecisionType;FFLjava/lang/String;JLjava/util/List;)V", "getId", "()Ljava/lang/String;", "getSource", "()Lcom/jarvis/core/autonomy/DecisionSource;", "getProposedAction", "()Lcom/jarvis/core/autonomy/ProposedAction;", "getBehaviorPatternKey", "getProactiveDecisionType", "()Lcom/jarvis/core/proactive/ProactiveDecisionType;", "getConfidence", "()F", "getRelevance", "getFingerprint", "getCreatedAtMillis", "()J", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionRequest {
    private final String behaviorPatternKey;
    private final float confidence;
    private final long createdAtMillis;
    private final String fingerprint;
    private final String id;
    private final List<String> notes;
    private final ProactiveDecisionType proactiveDecisionType;
    private final ProposedAction proposedAction;
    private final float relevance;
    private final DecisionSource source;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<String> component10() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final DecisionSource getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getBehaviorPatternKey() {
        return this.behaviorPatternKey;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ProactiveDecisionType getProactiveDecisionType() {
        return this.proactiveDecisionType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getRelevance() {
        return this.relevance;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getFingerprint() {
        return this.fingerprint;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final ActionRequest copy(String id, DecisionSource source, ProposedAction proposedAction, String behaviorPatternKey, ProactiveDecisionType proactiveDecisionType, float confidence, float relevance, String fingerprint, long createdAtMillis, List<String> notes) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(proposedAction, "proposedAction");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new ActionRequest(id, source, proposedAction, behaviorPatternKey, proactiveDecisionType, confidence, relevance, fingerprint, createdAtMillis, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionRequest)) {
            return false;
        }
        ActionRequest actionRequest = (ActionRequest) other;
        return Intrinsics.areEqual(this.id, actionRequest.id) && this.source == actionRequest.source && Intrinsics.areEqual(this.proposedAction, actionRequest.proposedAction) && Intrinsics.areEqual(this.behaviorPatternKey, actionRequest.behaviorPatternKey) && this.proactiveDecisionType == actionRequest.proactiveDecisionType && Float.compare(this.confidence, actionRequest.confidence) == 0 && Float.compare(this.relevance, actionRequest.relevance) == 0 && Intrinsics.areEqual(this.fingerprint, actionRequest.fingerprint) && this.createdAtMillis == actionRequest.createdAtMillis && Intrinsics.areEqual(this.notes, actionRequest.notes);
    }

    public int hashCode() {
        return (((((((((((((((((this.id.hashCode() * 31) + this.source.hashCode()) * 31) + this.proposedAction.hashCode()) * 31) + (this.behaviorPatternKey == null ? 0 : this.behaviorPatternKey.hashCode())) * 31) + (this.proactiveDecisionType == null ? 0 : this.proactiveDecisionType.hashCode())) * 31) + Float.hashCode(this.confidence)) * 31) + Float.hashCode(this.relevance)) * 31) + (this.fingerprint != null ? this.fingerprint.hashCode() : 0)) * 31) + Long.hashCode(this.createdAtMillis)) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "ActionRequest(id=" + this.id + ", source=" + this.source + ", proposedAction=" + this.proposedAction + ", behaviorPatternKey=" + this.behaviorPatternKey + ", proactiveDecisionType=" + this.proactiveDecisionType + ", confidence=" + this.confidence + ", relevance=" + this.relevance + ", fingerprint=" + this.fingerprint + ", createdAtMillis=" + this.createdAtMillis + ", notes=" + this.notes + ")";
    }

    public ActionRequest(String id, DecisionSource source, ProposedAction proposedAction, String behaviorPatternKey, ProactiveDecisionType proactiveDecisionType, float confidence, float relevance, String fingerprint, long createdAtMillis, List<String> notes) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(proposedAction, "proposedAction");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.id = id;
        this.source = source;
        this.proposedAction = proposedAction;
        this.behaviorPatternKey = behaviorPatternKey;
        this.proactiveDecisionType = proactiveDecisionType;
        this.confidence = confidence;
        this.relevance = relevance;
        this.fingerprint = fingerprint;
        this.createdAtMillis = createdAtMillis;
        this.notes = notes;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ActionRequest(String str, DecisionSource decisionSource, ProposedAction proposedAction, String str2, ProactiveDecisionType proactiveDecisionType, float f, float f2, String str3, long j, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str4;
        if ((i & 1) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str4 = string;
        } else {
            str4 = str;
        }
        this(str4, decisionSource, proposedAction, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : proactiveDecisionType, (i & 32) != 0 ? 0.0f : f, (i & 64) != 0 ? 0.0f : f2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? 0L : j, (i & 512) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getId() {
        return this.id;
    }

    public final DecisionSource getSource() {
        return this.source;
    }

    public final ProposedAction getProposedAction() {
        return this.proposedAction;
    }

    public final String getBehaviorPatternKey() {
        return this.behaviorPatternKey;
    }

    public final ProactiveDecisionType getProactiveDecisionType() {
        return this.proactiveDecisionType;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final float getRelevance() {
        return this.relevance;
    }

    public final String getFingerprint() {
        return this.fingerprint;
    }

    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
