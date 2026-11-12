package com.jarvis.core.cloud;

import com.jarvis.app.VoiceService;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudCall.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0003JG\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016¨\u0006#"}, d2 = {"Lcom/jarvis/core/cloud/ChainResult;", "", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/core/cloud/ChainEntry;", "hedged", "", "timeToFirstTokenMs", "", "failures", "", "Lcom/jarvis/core/cloud/ErrorClass;", "attemptReport", "", "<init>", "(Lcom/jarvis/core/cloud/ChainEntry;ZJLjava/util/List;Ljava/util/List;)V", "getEntry", "()Lcom/jarvis/core/cloud/ChainEntry;", "getHedged", "()Z", "getTimeToFirstTokenMs", "()J", "getFailures", "()Ljava/util/List;", "getAttemptReport", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ChainResult {
    private final List<String> attemptReport;
    private final ChainEntry entry;
    private final List<ErrorClass> failures;
    private final boolean hedged;
    private final long timeToFirstTokenMs;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ChainResult copy$default(ChainResult chainResult, ChainEntry chainEntry, boolean z, long j, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            chainEntry = chainResult.entry;
        }
        if ((i & 2) != 0) {
            z = chainResult.hedged;
        }
        boolean z2 = z;
        if ((i & 4) != 0) {
            j = chainResult.timeToFirstTokenMs;
        }
        long j2 = j;
        if ((i & 8) != 0) {
            list = chainResult.failures;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            list2 = chainResult.attemptReport;
        }
        return chainResult.copy(chainEntry, z2, j2, list3, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ChainEntry getEntry() {
        return this.entry;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHedged() {
        return this.hedged;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getTimeToFirstTokenMs() {
        return this.timeToFirstTokenMs;
    }

    public final List<ErrorClass> component4() {
        return this.failures;
    }

    public final List<String> component5() {
        return this.attemptReport;
    }

    public final ChainResult copy(ChainEntry entry, boolean hedged, long timeToFirstTokenMs, List<? extends ErrorClass> failures, List<String> attemptReport) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(failures, "failures");
        Intrinsics.checkNotNullParameter(attemptReport, "attemptReport");
        return new ChainResult(entry, hedged, timeToFirstTokenMs, failures, attemptReport);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChainResult)) {
            return false;
        }
        ChainResult chainResult = (ChainResult) other;
        return Intrinsics.areEqual(this.entry, chainResult.entry) && this.hedged == chainResult.hedged && this.timeToFirstTokenMs == chainResult.timeToFirstTokenMs && Intrinsics.areEqual(this.failures, chainResult.failures) && Intrinsics.areEqual(this.attemptReport, chainResult.attemptReport);
    }

    public int hashCode() {
        return (((((((this.entry.hashCode() * 31) + Boolean.hashCode(this.hedged)) * 31) + Long.hashCode(this.timeToFirstTokenMs)) * 31) + this.failures.hashCode()) * 31) + this.attemptReport.hashCode();
    }

    public String toString() {
        return "ChainResult(entry=" + this.entry + ", hedged=" + this.hedged + ", timeToFirstTokenMs=" + this.timeToFirstTokenMs + ", failures=" + this.failures + ", attemptReport=" + this.attemptReport + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChainResult(ChainEntry entry, boolean hedged, long timeToFirstTokenMs, List<? extends ErrorClass> failures, List<String> attemptReport) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(failures, "failures");
        Intrinsics.checkNotNullParameter(attemptReport, "attemptReport");
        this.entry = entry;
        this.hedged = hedged;
        this.timeToFirstTokenMs = timeToFirstTokenMs;
        this.failures = failures;
        this.attemptReport = attemptReport;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ChainResult(ChainEntry chainEntry, boolean z, long j, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList;
        if ((i & 16) == 0) {
            listEmptyList = list2;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this(chainEntry, z, j, list, listEmptyList);
    }

    public final ChainEntry getEntry() {
        return this.entry;
    }

    public final boolean getHedged() {
        return this.hedged;
    }

    public final long getTimeToFirstTokenMs() {
        return this.timeToFirstTokenMs;
    }

    public final List<ErrorClass> getFailures() {
        return this.failures;
    }

    public final List<String> getAttemptReport() {
        return this.attemptReport;
    }
}
