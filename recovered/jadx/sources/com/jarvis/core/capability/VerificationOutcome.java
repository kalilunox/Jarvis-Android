package com.jarvis.core.capability;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityExecutor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/capability/VerificationOutcome;", "", "ok", "", "notes", "", "", "<init>", "(ZLjava/util/List;)V", "getOk", "()Z", "getNotes", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VerificationOutcome {
    private final List<String> notes;
    private final boolean ok;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VerificationOutcome copy$default(VerificationOutcome verificationOutcome, boolean z, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = verificationOutcome.ok;
        }
        if ((i & 2) != 0) {
            list = verificationOutcome.notes;
        }
        return verificationOutcome.copy(z, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getOk() {
        return this.ok;
    }

    public final List<String> component2() {
        return this.notes;
    }

    public final VerificationOutcome copy(boolean ok, List<String> notes) {
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new VerificationOutcome(ok, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VerificationOutcome)) {
            return false;
        }
        VerificationOutcome verificationOutcome = (VerificationOutcome) other;
        return this.ok == verificationOutcome.ok && Intrinsics.areEqual(this.notes, verificationOutcome.notes);
    }

    public int hashCode() {
        return (Boolean.hashCode(this.ok) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "VerificationOutcome(ok=" + this.ok + ", notes=" + this.notes + ")";
    }

    public VerificationOutcome(boolean ok, List<String> notes) {
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.ok = ok;
        this.notes = notes;
    }

    public /* synthetic */ VerificationOutcome(boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final boolean getOk() {
        return this.ok;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
