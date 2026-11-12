package com.jarvis.core.proactive;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextMatchResult.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J7\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/jarvis/core/proactive/RelevanceResult;", "", "relevance", "", "match", "Lcom/jarvis/core/proactive/ContextMatchResult;", "reasonCode", "Lcom/jarvis/core/proactive/ProactiveReasonCode;", "notes", "", "", "<init>", "(FLcom/jarvis/core/proactive/ContextMatchResult;Lcom/jarvis/core/proactive/ProactiveReasonCode;Ljava/util/List;)V", "getRelevance", "()F", "getMatch", "()Lcom/jarvis/core/proactive/ContextMatchResult;", "getReasonCode", "()Lcom/jarvis/core/proactive/ProactiveReasonCode;", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RelevanceResult {
    private final ContextMatchResult match;
    private final List<String> notes;
    private final ProactiveReasonCode reasonCode;
    private final float relevance;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RelevanceResult copy$default(RelevanceResult relevanceResult, float f, ContextMatchResult contextMatchResult, ProactiveReasonCode proactiveReasonCode, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            f = relevanceResult.relevance;
        }
        if ((i & 2) != 0) {
            contextMatchResult = relevanceResult.match;
        }
        if ((i & 4) != 0) {
            proactiveReasonCode = relevanceResult.reasonCode;
        }
        if ((i & 8) != 0) {
            list = relevanceResult.notes;
        }
        return relevanceResult.copy(f, contextMatchResult, proactiveReasonCode, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getRelevance() {
        return this.relevance;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ContextMatchResult getMatch() {
        return this.match;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final List<String> component4() {
        return this.notes;
    }

    public final RelevanceResult copy(float relevance, ContextMatchResult match, ProactiveReasonCode reasonCode, List<String> notes) {
        Intrinsics.checkNotNullParameter(match, "match");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new RelevanceResult(relevance, match, reasonCode, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RelevanceResult)) {
            return false;
        }
        RelevanceResult relevanceResult = (RelevanceResult) other;
        return Float.compare(this.relevance, relevanceResult.relevance) == 0 && Intrinsics.areEqual(this.match, relevanceResult.match) && this.reasonCode == relevanceResult.reasonCode && Intrinsics.areEqual(this.notes, relevanceResult.notes);
    }

    public int hashCode() {
        return (((((Float.hashCode(this.relevance) * 31) + this.match.hashCode()) * 31) + this.reasonCode.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "RelevanceResult(relevance=" + this.relevance + ", match=" + this.match + ", reasonCode=" + this.reasonCode + ", notes=" + this.notes + ")";
    }

    public RelevanceResult(float relevance, ContextMatchResult match, ProactiveReasonCode reasonCode, List<String> notes) {
        Intrinsics.checkNotNullParameter(match, "match");
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.relevance = relevance;
        this.match = match;
        this.reasonCode = reasonCode;
        this.notes = notes;
    }

    public /* synthetic */ RelevanceResult(float f, ContextMatchResult contextMatchResult, ProactiveReasonCode proactiveReasonCode, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, contextMatchResult, (i & 4) != 0 ? ProactiveReasonCode.EVALUATED : proactiveReasonCode, (i & 8) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final float getRelevance() {
        return this.relevance;
    }

    public final ContextMatchResult getMatch() {
        return this.match;
    }

    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
