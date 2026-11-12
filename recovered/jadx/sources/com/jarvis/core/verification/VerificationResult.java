package com.jarvis.core.verification;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerificationModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003JS\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/jarvis/core/verification/VerificationResult;", "", "status", "Lcom/jarvis/core/verification/VerificationStatus;", "matched", "", "", "mismatched", "missing", "notes", "<init>", "(Lcom/jarvis/core/verification/VerificationStatus;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getStatus", "()Lcom/jarvis/core/verification/VerificationStatus;", "getMatched", "()Ljava/util/List;", "getMismatched", "getMissing", "getNotes", "ok", "", "getOk", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VerificationResult {
    private final List<String> matched;
    private final List<String> mismatched;
    private final List<String> missing;
    private final List<String> notes;
    private final VerificationStatus status;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VerificationResult copy$default(VerificationResult verificationResult, VerificationStatus verificationStatus, List list, List list2, List list3, List list4, int i, Object obj) {
        if ((i & 1) != 0) {
            verificationStatus = verificationResult.status;
        }
        if ((i & 2) != 0) {
            list = verificationResult.matched;
        }
        List list5 = list;
        if ((i & 4) != 0) {
            list2 = verificationResult.mismatched;
        }
        List list6 = list2;
        if ((i & 8) != 0) {
            list3 = verificationResult.missing;
        }
        List list7 = list3;
        if ((i & 16) != 0) {
            list4 = verificationResult.notes;
        }
        return verificationResult.copy(verificationStatus, list5, list6, list7, list4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final VerificationStatus getStatus() {
        return this.status;
    }

    public final List<String> component2() {
        return this.matched;
    }

    public final List<String> component3() {
        return this.mismatched;
    }

    public final List<String> component4() {
        return this.missing;
    }

    public final List<String> component5() {
        return this.notes;
    }

    public final VerificationResult copy(VerificationStatus status, List<String> matched, List<String> mismatched, List<String> missing, List<String> notes) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(matched, "matched");
        Intrinsics.checkNotNullParameter(mismatched, "mismatched");
        Intrinsics.checkNotNullParameter(missing, "missing");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new VerificationResult(status, matched, mismatched, missing, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VerificationResult)) {
            return false;
        }
        VerificationResult verificationResult = (VerificationResult) other;
        return this.status == verificationResult.status && Intrinsics.areEqual(this.matched, verificationResult.matched) && Intrinsics.areEqual(this.mismatched, verificationResult.mismatched) && Intrinsics.areEqual(this.missing, verificationResult.missing) && Intrinsics.areEqual(this.notes, verificationResult.notes);
    }

    public int hashCode() {
        return (((((((this.status.hashCode() * 31) + this.matched.hashCode()) * 31) + this.mismatched.hashCode()) * 31) + this.missing.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "VerificationResult(status=" + this.status + ", matched=" + this.matched + ", mismatched=" + this.mismatched + ", missing=" + this.missing + ", notes=" + this.notes + ")";
    }

    public VerificationResult(VerificationStatus status, List<String> matched, List<String> mismatched, List<String> missing, List<String> notes) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(matched, "matched");
        Intrinsics.checkNotNullParameter(mismatched, "mismatched");
        Intrinsics.checkNotNullParameter(missing, "missing");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.status = status;
        this.matched = matched;
        this.mismatched = mismatched;
        this.missing = missing;
        this.notes = notes;
    }

    public /* synthetic */ VerificationResult(VerificationStatus verificationStatus, List list, List list2, List list3, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(verificationStatus, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? CollectionsKt.emptyList() : list2, (i & 8) != 0 ? CollectionsKt.emptyList() : list3, (i & 16) != 0 ? CollectionsKt.emptyList() : list4);
    }

    public final VerificationStatus getStatus() {
        return this.status;
    }

    public final List<String> getMatched() {
        return this.matched;
    }

    public final List<String> getMismatched() {
        return this.mismatched;
    }

    public final List<String> getMissing() {
        return this.missing;
    }

    public final List<String> getNotes() {
        return this.notes;
    }

    public final boolean getOk() {
        return this.status == VerificationStatus.SUCCESS || this.status == VerificationStatus.PARTIAL_SUCCESS;
    }
}
