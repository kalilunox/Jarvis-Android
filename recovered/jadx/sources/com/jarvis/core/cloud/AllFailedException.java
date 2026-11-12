package com.jarvis.core.cloud;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChainRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00060\u0001j\u0002`\u0002B;\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/cloud/AllFailedException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "failures", "", "Lcom/jarvis/core/cloud/ErrorClass;", "hedged", "", "attemptReport", "", "partial", "<init>", "(Ljava/util/List;ZLjava/util/List;Z)V", "getFailures", "()Ljava/util/List;", "getHedged", "()Z", "getAttemptReport", "getPartial", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AllFailedException extends RuntimeException {
    private final List<String> attemptReport;
    private final List<ErrorClass> failures;
    private final boolean hedged;
    private final boolean partial;

    public AllFailedException() {
        this(null, false, null, false, 15, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AllFailedException(List<? extends ErrorClass> failures, boolean hedged, List<String> attemptReport, boolean partial) {
        super("AllFailed");
        Intrinsics.checkNotNullParameter(failures, "failures");
        Intrinsics.checkNotNullParameter(attemptReport, "attemptReport");
        this.failures = failures;
        this.hedged = hedged;
        this.attemptReport = attemptReport;
        this.partial = partial;
    }

    public /* synthetic */ AllFailedException(List list, boolean z, List list2, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? false : z, (i & 4) != 0 ? CollectionsKt.emptyList() : list2, (i & 8) != 0 ? false : z2);
    }

    public final List<ErrorClass> getFailures() {
        return this.failures;
    }

    public final boolean getHedged() {
        return this.hedged;
    }

    public final List<String> getAttemptReport() {
        return this.attemptReport;
    }

    public final boolean getPartial() {
        return this.partial;
    }
}
