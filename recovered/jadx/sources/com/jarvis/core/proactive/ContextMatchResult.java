package com.jarvis.core.proactive;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextMatchResult.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0015\u001a\u00020\u0000J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JG\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006!"}, d2 = {"Lcom/jarvis/core/proactive/ContextMatchResult;", "", "matched", "", "score", "", "matchedSignals", "", "", "mismatchSignals", "alreadySatisfied", "<init>", "(ZFLjava/util/List;Ljava/util/List;Z)V", "getMatched", "()Z", "getScore", "()F", "getMatchedSignals", "()Ljava/util/List;", "getMismatchSignals", "getAlreadySatisfied", "withAlreadySatisfied", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ContextMatchResult {
    private final boolean alreadySatisfied;
    private final boolean matched;
    private final List<String> matchedSignals;
    private final List<String> mismatchSignals;
    private final float score;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ContextMatchResult copy$default(ContextMatchResult contextMatchResult, boolean z, float f, List list, List list2, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = contextMatchResult.matched;
        }
        if ((i & 2) != 0) {
            f = contextMatchResult.score;
        }
        float f2 = f;
        if ((i & 4) != 0) {
            list = contextMatchResult.matchedSignals;
        }
        List list3 = list;
        if ((i & 8) != 0) {
            list2 = contextMatchResult.mismatchSignals;
        }
        List list4 = list2;
        if ((i & 16) != 0) {
            z2 = contextMatchResult.alreadySatisfied;
        }
        return contextMatchResult.copy(z, f2, list3, list4, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getMatched() {
        return this.matched;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getScore() {
        return this.score;
    }

    public final List<String> component3() {
        return this.matchedSignals;
    }

    public final List<String> component4() {
        return this.mismatchSignals;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getAlreadySatisfied() {
        return this.alreadySatisfied;
    }

    public final ContextMatchResult copy(boolean matched, float score, List<String> matchedSignals, List<String> mismatchSignals, boolean alreadySatisfied) {
        Intrinsics.checkNotNullParameter(matchedSignals, "matchedSignals");
        Intrinsics.checkNotNullParameter(mismatchSignals, "mismatchSignals");
        return new ContextMatchResult(matched, score, matchedSignals, mismatchSignals, alreadySatisfied);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextMatchResult)) {
            return false;
        }
        ContextMatchResult contextMatchResult = (ContextMatchResult) other;
        return this.matched == contextMatchResult.matched && Float.compare(this.score, contextMatchResult.score) == 0 && Intrinsics.areEqual(this.matchedSignals, contextMatchResult.matchedSignals) && Intrinsics.areEqual(this.mismatchSignals, contextMatchResult.mismatchSignals) && this.alreadySatisfied == contextMatchResult.alreadySatisfied;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.matched) * 31) + Float.hashCode(this.score)) * 31) + this.matchedSignals.hashCode()) * 31) + this.mismatchSignals.hashCode()) * 31) + Boolean.hashCode(this.alreadySatisfied);
    }

    public String toString() {
        return "ContextMatchResult(matched=" + this.matched + ", score=" + this.score + ", matchedSignals=" + this.matchedSignals + ", mismatchSignals=" + this.mismatchSignals + ", alreadySatisfied=" + this.alreadySatisfied + ")";
    }

    public ContextMatchResult(boolean matched, float score, List<String> matchedSignals, List<String> mismatchSignals, boolean alreadySatisfied) {
        Intrinsics.checkNotNullParameter(matchedSignals, "matchedSignals");
        Intrinsics.checkNotNullParameter(mismatchSignals, "mismatchSignals");
        this.matched = matched;
        this.score = score;
        this.matchedSignals = matchedSignals;
        this.mismatchSignals = mismatchSignals;
        this.alreadySatisfied = alreadySatisfied;
    }

    public /* synthetic */ ContextMatchResult(boolean z, float f, List list, List list2, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, f, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? CollectionsKt.emptyList() : list2, (i & 16) != 0 ? false : z2);
    }

    public final boolean getMatched() {
        return this.matched;
    }

    public final float getScore() {
        return this.score;
    }

    public final List<String> getMatchedSignals() {
        return this.matchedSignals;
    }

    public final List<String> getMismatchSignals() {
        return this.mismatchSignals;
    }

    public final boolean getAlreadySatisfied() {
        return this.alreadySatisfied;
    }

    public final ContextMatchResult withAlreadySatisfied() {
        return copy$default(this, false, 0.0f, null, null, true, 14, null);
    }
}
