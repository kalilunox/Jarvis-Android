package com.jarvis.core.proactive;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProactiveDecision.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00100\u001a\u00020\tHÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\fHÆ\u0003J\t\u00103\u001a\u00020\fHÆ\u0003J\t\u00104\u001a\u00020\u000fHÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010&J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012HÆ\u0003J\u0015\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014HÆ\u0003J \u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014HÆ\u0001¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020>HÖ\u0001J\t\u0010?\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006@"}, d2 = {"Lcom/jarvis/core/proactive/ProactiveDecision;", "", "type", "Lcom/jarvis/core/proactive/ProactiveDecisionType;", "behaviorPatternKey", "", "behaviorId", ProactiveActionReceiver.EXTRA_FINGERPRINT, "reasonCode", "Lcom/jarvis/core/proactive/ProactiveReasonCode;", "reason", "confidence", "", "relevance", "createdAtMillis", "", "cooldownUntilMillis", "notes", "", "metadata", "", "<init>", "(Lcom/jarvis/core/proactive/ProactiveDecisionType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/proactive/ProactiveReasonCode;Ljava/lang/String;FFJLjava/lang/Long;Ljava/util/List;Ljava/util/Map;)V", "getType", "()Lcom/jarvis/core/proactive/ProactiveDecisionType;", "getBehaviorPatternKey", "()Ljava/lang/String;", "getBehaviorId", "getFingerprint", "getReasonCode", "()Lcom/jarvis/core/proactive/ProactiveReasonCode;", "getReason", "getConfidence", "()F", "getRelevance", "getCreatedAtMillis", "()J", "getCooldownUntilMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getNotes", "()Ljava/util/List;", "getMetadata", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Lcom/jarvis/core/proactive/ProactiveDecisionType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/proactive/ProactiveReasonCode;Ljava/lang/String;FFJLjava/lang/Long;Ljava/util/List;Ljava/util/Map;)Lcom/jarvis/core/proactive/ProactiveDecision;", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProactiveDecision {
    private final String behaviorId;
    private final String behaviorPatternKey;
    private final float confidence;
    private final Long cooldownUntilMillis;
    private final long createdAtMillis;
    private final String fingerprint;
    private final Map<String, String> metadata;
    private final List<String> notes;
    private final String reason;
    private final ProactiveReasonCode reasonCode;
    private final float relevance;
    private final ProactiveDecisionType type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ProactiveDecisionType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Long getCooldownUntilMillis() {
        return this.cooldownUntilMillis;
    }

    public final List<String> component11() {
        return this.notes;
    }

    public final Map<String, String> component12() {
        return this.metadata;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getBehaviorPatternKey() {
        return this.behaviorPatternKey;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBehaviorId() {
        return this.behaviorId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getFingerprint() {
        return this.fingerprint;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final float getRelevance() {
        return this.relevance;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final ProactiveDecision copy(ProactiveDecisionType type, String behaviorPatternKey, String behaviorId, String fingerprint, ProactiveReasonCode reasonCode, String reason, float confidence, float relevance, long createdAtMillis, Long cooldownUntilMillis, List<String> notes, Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return new ProactiveDecision(type, behaviorPatternKey, behaviorId, fingerprint, reasonCode, reason, confidence, relevance, createdAtMillis, cooldownUntilMillis, notes, metadata);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProactiveDecision)) {
            return false;
        }
        ProactiveDecision proactiveDecision = (ProactiveDecision) other;
        return this.type == proactiveDecision.type && Intrinsics.areEqual(this.behaviorPatternKey, proactiveDecision.behaviorPatternKey) && Intrinsics.areEqual(this.behaviorId, proactiveDecision.behaviorId) && Intrinsics.areEqual(this.fingerprint, proactiveDecision.fingerprint) && this.reasonCode == proactiveDecision.reasonCode && Intrinsics.areEqual(this.reason, proactiveDecision.reason) && Float.compare(this.confidence, proactiveDecision.confidence) == 0 && Float.compare(this.relevance, proactiveDecision.relevance) == 0 && this.createdAtMillis == proactiveDecision.createdAtMillis && Intrinsics.areEqual(this.cooldownUntilMillis, proactiveDecision.cooldownUntilMillis) && Intrinsics.areEqual(this.notes, proactiveDecision.notes) && Intrinsics.areEqual(this.metadata, proactiveDecision.metadata);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.type.hashCode() * 31) + (this.behaviorPatternKey == null ? 0 : this.behaviorPatternKey.hashCode())) * 31) + (this.behaviorId == null ? 0 : this.behaviorId.hashCode())) * 31) + (this.fingerprint == null ? 0 : this.fingerprint.hashCode())) * 31) + this.reasonCode.hashCode()) * 31) + this.reason.hashCode()) * 31) + Float.hashCode(this.confidence)) * 31) + Float.hashCode(this.relevance)) * 31) + Long.hashCode(this.createdAtMillis)) * 31) + (this.cooldownUntilMillis != null ? this.cooldownUntilMillis.hashCode() : 0)) * 31) + this.notes.hashCode()) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "ProactiveDecision(type=" + this.type + ", behaviorPatternKey=" + this.behaviorPatternKey + ", behaviorId=" + this.behaviorId + ", fingerprint=" + this.fingerprint + ", reasonCode=" + this.reasonCode + ", reason=" + this.reason + ", confidence=" + this.confidence + ", relevance=" + this.relevance + ", createdAtMillis=" + this.createdAtMillis + ", cooldownUntilMillis=" + this.cooldownUntilMillis + ", notes=" + this.notes + ", metadata=" + this.metadata + ")";
    }

    public ProactiveDecision(ProactiveDecisionType type, String behaviorPatternKey, String behaviorId, String fingerprint, ProactiveReasonCode reasonCode, String reason, float confidence, float relevance, long createdAtMillis, Long cooldownUntilMillis, List<String> notes, Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.type = type;
        this.behaviorPatternKey = behaviorPatternKey;
        this.behaviorId = behaviorId;
        this.fingerprint = fingerprint;
        this.reasonCode = reasonCode;
        this.reason = reason;
        this.confidence = confidence;
        this.relevance = relevance;
        this.createdAtMillis = createdAtMillis;
        this.cooldownUntilMillis = cooldownUntilMillis;
        this.notes = notes;
        this.metadata = metadata;
    }

    public /* synthetic */ ProactiveDecision(ProactiveDecisionType proactiveDecisionType, String str, String str2, String str3, ProactiveReasonCode proactiveReasonCode, String str4, float f, float f2, long j, Long l, List list, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(proactiveDecisionType, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, proactiveReasonCode, (i & 32) != 0 ? "" : str4, (i & 64) != 0 ? 0.0f : f, (i & 128) != 0 ? 0.0f : f2, (i & 256) != 0 ? 0L : j, (i & 512) != 0 ? null : l, (i & 1024) != 0 ? CollectionsKt.emptyList() : list, (i & 2048) != 0 ? MapsKt.emptyMap() : map);
    }

    public final ProactiveDecisionType getType() {
        return this.type;
    }

    public final String getBehaviorPatternKey() {
        return this.behaviorPatternKey;
    }

    public final String getBehaviorId() {
        return this.behaviorId;
    }

    public final String getFingerprint() {
        return this.fingerprint;
    }

    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final String getReason() {
        return this.reason;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final float getRelevance() {
        return this.relevance;
    }

    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final Long getCooldownUntilMillis() {
        return this.cooldownUntilMillis;
    }

    public final List<String> getNotes() {
        return this.notes;
    }

    public final Map<String, String> getMetadata() {
        return this.metadata;
    }
}
