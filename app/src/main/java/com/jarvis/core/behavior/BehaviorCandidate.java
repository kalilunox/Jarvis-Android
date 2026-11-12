package com.jarvis.core.behavior;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BehaviorCandidate.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\f\u0012\b\b\u0002\u0010\u0018\u001a\u00020\f\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u0013J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0007HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\nHÆ\u0003J\t\u0010;\u001a\u00020\fHÆ\u0003J\t\u0010<\u001a\u00020\u000eHÆ\u0003J\t\u0010=\u001a\u00020\u000eHÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\t\u0010?\u001a\u00020\u0013HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\u0015\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016HÆ\u0003J\t\u0010B\u001a\u00020\fHÆ\u0003J\t\u0010C\u001a\u00020\fHÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J±\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00032\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00162\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\u0003HÆ\u0001J\u0013\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010I\u001a\u00020\fHÖ\u0001J\t\u0010J\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0017\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b1\u0010&R\u0011\u0010\u0018\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b2\u0010&R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001d¨\u0006K"}, d2 = {"Lcom/jarvis/core/behavior/BehaviorCandidate;", "", "id", "", "kind", "Lcom/jarvis/core/behavior/BehaviorKind;", "family", "Lcom/jarvis/core/behavior/PatternFamily;", "description", "confidence", "", "evidenceCount", "", "firstObservedMillis", "", "lastObservedMillis", "supportingEvidenceIds", "", "status", "Lcom/jarvis/core/behavior/BehaviorCandidateStatus;", ProactiveActionReceiver.EXTRA_PATTERN_KEY, "entities", "", "uniqueDays", "negativeEvidenceCount", "why", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/behavior/BehaviorKind;Lcom/jarvis/core/behavior/PatternFamily;Ljava/lang/String;FIJJLjava/util/List;Lcom/jarvis/core/behavior/BehaviorCandidateStatus;Ljava/lang/String;Ljava/util/Map;IILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/behavior/BehaviorKind;", "getFamily", "()Lcom/jarvis/core/behavior/PatternFamily;", "getDescription", "getConfidence", "()F", "getEvidenceCount", "()I", "getFirstObservedMillis", "()J", "getLastObservedMillis", "getSupportingEvidenceIds", "()Ljava/util/List;", "getStatus", "()Lcom/jarvis/core/behavior/BehaviorCandidateStatus;", "getPatternKey", "getEntities", "()Ljava/util/Map;", "getUniqueDays", "getNegativeEvidenceCount", "getWhy", "withConfidence", "c", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class BehaviorCandidate {
    private final float confidence;
    private final String description;
    private final Map<String, String> entities;
    private final int evidenceCount;
    private final PatternFamily family;
    private final long firstObservedMillis;
    private final String id;
    private final BehaviorKind kind;
    private final long lastObservedMillis;
    private final int negativeEvidenceCount;
    private final String patternKey;
    private final BehaviorCandidateStatus status;
    private final List<String> supportingEvidenceIds;
    private final int uniqueDays;
    private final String why;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BehaviorCandidate copy$default(BehaviorCandidate behaviorCandidate, String str, BehaviorKind behaviorKind, PatternFamily patternFamily, String str2, float f, int i, long j, long j2, List list, BehaviorCandidateStatus behaviorCandidateStatus, String str3, Map map, int i2, int i3, String str4, int i4, Object obj) {
        return behaviorCandidate.copy((i4 & 1) != 0 ? behaviorCandidate.id : str, (i4 & 2) != 0 ? behaviorCandidate.kind : behaviorKind, (i4 & 4) != 0 ? behaviorCandidate.family : patternFamily, (i4 & 8) != 0 ? behaviorCandidate.description : str2, (i4 & 16) != 0 ? behaviorCandidate.confidence : f, (i4 & 32) != 0 ? behaviorCandidate.evidenceCount : i, (i4 & 64) != 0 ? behaviorCandidate.firstObservedMillis : j, (i4 & 128) != 0 ? behaviorCandidate.lastObservedMillis : j2, (i4 & 256) != 0 ? behaviorCandidate.supportingEvidenceIds : list, (i4 & 512) != 0 ? behaviorCandidate.status : behaviorCandidateStatus, (i4 & 1024) != 0 ? behaviorCandidate.patternKey : str3, (i4 & 2048) != 0 ? behaviorCandidate.entities : map, (i4 & 4096) != 0 ? behaviorCandidate.uniqueDays : i2, (i4 & 8192) != 0 ? behaviorCandidate.negativeEvidenceCount : i3, (i4 & 16384) != 0 ? behaviorCandidate.why : str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final BehaviorCandidateStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPatternKey() {
        return this.patternKey;
    }

    public final Map<String, String> component12() {
        return this.entities;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getUniqueDays() {
        return this.uniqueDays;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final int getNegativeEvidenceCount() {
        return this.negativeEvidenceCount;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getWhy() {
        return this.why;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final BehaviorKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final PatternFamily getFamily() {
        return this.family;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getEvidenceCount() {
        return this.evidenceCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getFirstObservedMillis() {
        return this.firstObservedMillis;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getLastObservedMillis() {
        return this.lastObservedMillis;
    }

    public final List<String> component9() {
        return this.supportingEvidenceIds;
    }

    public final BehaviorCandidate copy(String id, BehaviorKind kind, PatternFamily family, String description, float confidence, int evidenceCount, long firstObservedMillis, long lastObservedMillis, List<String> supportingEvidenceIds, BehaviorCandidateStatus status, String patternKey, Map<String, String> entities, int uniqueDays, int negativeEvidenceCount, String why) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(family, "family");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(supportingEvidenceIds, "supportingEvidenceIds");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(why, "why");
        return new BehaviorCandidate(id, kind, family, description, confidence, evidenceCount, firstObservedMillis, lastObservedMillis, supportingEvidenceIds, status, patternKey, entities, uniqueDays, negativeEvidenceCount, why);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BehaviorCandidate)) {
            return false;
        }
        BehaviorCandidate behaviorCandidate = (BehaviorCandidate) other;
        return Intrinsics.areEqual(this.id, behaviorCandidate.id) && this.kind == behaviorCandidate.kind && this.family == behaviorCandidate.family && Intrinsics.areEqual(this.description, behaviorCandidate.description) && Float.compare(this.confidence, behaviorCandidate.confidence) == 0 && this.evidenceCount == behaviorCandidate.evidenceCount && this.firstObservedMillis == behaviorCandidate.firstObservedMillis && this.lastObservedMillis == behaviorCandidate.lastObservedMillis && Intrinsics.areEqual(this.supportingEvidenceIds, behaviorCandidate.supportingEvidenceIds) && this.status == behaviorCandidate.status && Intrinsics.areEqual(this.patternKey, behaviorCandidate.patternKey) && Intrinsics.areEqual(this.entities, behaviorCandidate.entities) && this.uniqueDays == behaviorCandidate.uniqueDays && this.negativeEvidenceCount == behaviorCandidate.negativeEvidenceCount && Intrinsics.areEqual(this.why, behaviorCandidate.why);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((this.id.hashCode() * 31) + this.kind.hashCode()) * 31) + this.family.hashCode()) * 31) + this.description.hashCode()) * 31) + Float.hashCode(this.confidence)) * 31) + Integer.hashCode(this.evidenceCount)) * 31) + Long.hashCode(this.firstObservedMillis)) * 31) + Long.hashCode(this.lastObservedMillis)) * 31) + this.supportingEvidenceIds.hashCode()) * 31) + this.status.hashCode()) * 31) + this.patternKey.hashCode()) * 31) + this.entities.hashCode()) * 31) + Integer.hashCode(this.uniqueDays)) * 31) + Integer.hashCode(this.negativeEvidenceCount)) * 31) + this.why.hashCode();
    }

    public String toString() {
        return "BehaviorCandidate(id=" + this.id + ", kind=" + this.kind + ", family=" + this.family + ", description=" + this.description + ", confidence=" + this.confidence + ", evidenceCount=" + this.evidenceCount + ", firstObservedMillis=" + this.firstObservedMillis + ", lastObservedMillis=" + this.lastObservedMillis + ", supportingEvidenceIds=" + this.supportingEvidenceIds + ", status=" + this.status + ", patternKey=" + this.patternKey + ", entities=" + this.entities + ", uniqueDays=" + this.uniqueDays + ", negativeEvidenceCount=" + this.negativeEvidenceCount + ", why=" + this.why + ")";
    }

    public BehaviorCandidate(String id, BehaviorKind kind, PatternFamily family, String description, float confidence, int evidenceCount, long firstObservedMillis, long lastObservedMillis, List<String> supportingEvidenceIds, BehaviorCandidateStatus status, String patternKey, Map<String, String> entities, int uniqueDays, int negativeEvidenceCount, String why) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(family, "family");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(supportingEvidenceIds, "supportingEvidenceIds");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(why, "why");
        this.id = id;
        this.kind = kind;
        this.family = family;
        this.description = description;
        this.confidence = confidence;
        this.evidenceCount = evidenceCount;
        this.firstObservedMillis = firstObservedMillis;
        this.lastObservedMillis = lastObservedMillis;
        this.supportingEvidenceIds = supportingEvidenceIds;
        this.status = status;
        this.patternKey = patternKey;
        this.entities = entities;
        this.uniqueDays = uniqueDays;
        this.negativeEvidenceCount = negativeEvidenceCount;
        this.why = why;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ BehaviorCandidate(String str, BehaviorKind behaviorKind, PatternFamily patternFamily, String str2, float f, int i, long j, long j2, List list, BehaviorCandidateStatus behaviorCandidateStatus, String str3, Map map, int i2, int i3, String str4, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        String str5;
        if ((i4 & 1) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str5 = string;
        } else {
            str5 = str;
        }
        this(str5, behaviorKind, patternFamily, str2, f, i, j, j2, (i4 & 256) != 0 ? CollectionsKt.emptyList() : list, (i4 & 512) != 0 ? BehaviorCandidateStatus.SUGGEST_ONLY : behaviorCandidateStatus, str3, (i4 & 2048) != 0 ? MapsKt.emptyMap() : map, (i4 & 4096) != 0 ? 1 : i2, (i4 & 8192) != 0 ? 0 : i3, (i4 & 16384) != 0 ? "" : str4);
    }

    public final String getId() {
        return this.id;
    }

    public final BehaviorKind getKind() {
        return this.kind;
    }

    public final PatternFamily getFamily() {
        return this.family;
    }

    public final String getDescription() {
        return this.description;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final int getEvidenceCount() {
        return this.evidenceCount;
    }

    public final long getFirstObservedMillis() {
        return this.firstObservedMillis;
    }

    public final long getLastObservedMillis() {
        return this.lastObservedMillis;
    }

    public final List<String> getSupportingEvidenceIds() {
        return this.supportingEvidenceIds;
    }

    public final BehaviorCandidateStatus getStatus() {
        return this.status;
    }

    public final String getPatternKey() {
        return this.patternKey;
    }

    public final Map<String, String> getEntities() {
        return this.entities;
    }

    public final int getUniqueDays() {
        return this.uniqueDays;
    }

    public final int getNegativeEvidenceCount() {
        return this.negativeEvidenceCount;
    }

    public final String getWhy() {
        return this.why;
    }

    public static /* synthetic */ BehaviorCandidate withConfidence$default(BehaviorCandidate behaviorCandidate, float f, BehaviorCandidateStatus behaviorCandidateStatus, int i, Object obj) {
        if ((i & 2) != 0) {
            behaviorCandidateStatus = behaviorCandidate.status;
        }
        return behaviorCandidate.withConfidence(f, behaviorCandidateStatus);
    }

    public final BehaviorCandidate withConfidence(float c, BehaviorCandidateStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return copy$default(this, null, null, null, null, RangesKt.coerceIn(c, 0.0f, 1.0f), 0, 0L, 0L, null, status, null, null, 0, 0, null, 32239, null);
    }
}
