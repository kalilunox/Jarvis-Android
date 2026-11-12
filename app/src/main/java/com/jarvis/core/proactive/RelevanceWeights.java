package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContextMatchResult.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000e\u001a\u00020\u0000J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/proactive/RelevanceWeights;", "", "contextMatch", "", "recency", "statusBoost", "kindBoost", "<init>", "(FFFF)V", "getContextMatch", "()F", "getRecency", "getStatusBoost", "getKindBoost", "normalized", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RelevanceWeights {
    private final float contextMatch;
    private final float kindBoost;
    private final float recency;
    private final float statusBoost;

    public RelevanceWeights() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 15, null);
    }

    public static /* synthetic */ RelevanceWeights copy$default(RelevanceWeights relevanceWeights, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = relevanceWeights.contextMatch;
        }
        if ((i & 2) != 0) {
            f2 = relevanceWeights.recency;
        }
        if ((i & 4) != 0) {
            f3 = relevanceWeights.statusBoost;
        }
        if ((i & 8) != 0) {
            f4 = relevanceWeights.kindBoost;
        }
        return relevanceWeights.copy(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getContextMatch() {
        return this.contextMatch;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getRecency() {
        return this.recency;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getStatusBoost() {
        return this.statusBoost;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getKindBoost() {
        return this.kindBoost;
    }

    public final RelevanceWeights copy(float contextMatch, float recency, float statusBoost, float kindBoost) {
        return new RelevanceWeights(contextMatch, recency, statusBoost, kindBoost);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RelevanceWeights)) {
            return false;
        }
        RelevanceWeights relevanceWeights = (RelevanceWeights) other;
        return Float.compare(this.contextMatch, relevanceWeights.contextMatch) == 0 && Float.compare(this.recency, relevanceWeights.recency) == 0 && Float.compare(this.statusBoost, relevanceWeights.statusBoost) == 0 && Float.compare(this.kindBoost, relevanceWeights.kindBoost) == 0;
    }

    public int hashCode() {
        return (((((Float.hashCode(this.contextMatch) * 31) + Float.hashCode(this.recency)) * 31) + Float.hashCode(this.statusBoost)) * 31) + Float.hashCode(this.kindBoost);
    }

    public String toString() {
        return "RelevanceWeights(contextMatch=" + this.contextMatch + ", recency=" + this.recency + ", statusBoost=" + this.statusBoost + ", kindBoost=" + this.kindBoost + ")";
    }

    public RelevanceWeights(float contextMatch, float recency, float statusBoost, float kindBoost) {
        this.contextMatch = contextMatch;
        this.recency = recency;
        this.statusBoost = statusBoost;
        this.kindBoost = kindBoost;
        if (this.contextMatch >= 0.0f && this.recency >= 0.0f && this.statusBoost >= 0.0f && this.kindBoost >= 0.0f) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ RelevanceWeights(float f, float f2, float f3, float f4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.55f : f, (i & 2) != 0 ? 0.2f : f2, (i & 4) != 0 ? 0.15f : f3, (i & 8) != 0 ? 0.1f : f4);
    }

    public final float getContextMatch() {
        return this.contextMatch;
    }

    public final float getRecency() {
        return this.recency;
    }

    public final float getStatusBoost() {
        return this.statusBoost;
    }

    public final float getKindBoost() {
        return this.kindBoost;
    }

    public final RelevanceWeights normalized() {
        float s = this.contextMatch + this.recency + this.statusBoost + this.kindBoost;
        return s <= 0.0f ? this : new RelevanceWeights(this.contextMatch / s, this.recency / s, this.statusBoost / s, this.kindBoost / s);
    }
}
