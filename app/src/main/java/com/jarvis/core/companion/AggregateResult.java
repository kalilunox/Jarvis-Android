package com.jarvis.core.companion;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextAggregator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/companion/AggregateResult;", "", "accepted", "", "context", "Lcom/jarvis/core/companion/PhoneContext;", "dropReason", "", "<init>", "(ZLcom/jarvis/core/companion/PhoneContext;Ljava/lang/String;)V", "getAccepted", "()Z", "getContext", "()Lcom/jarvis/core/companion/PhoneContext;", "getDropReason", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AggregateResult {
    private final boolean accepted;
    private final PhoneContext context;
    private final String dropReason;

    public static /* synthetic */ AggregateResult copy$default(AggregateResult aggregateResult, boolean z, PhoneContext phoneContext, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = aggregateResult.accepted;
        }
        if ((i & 2) != 0) {
            phoneContext = aggregateResult.context;
        }
        if ((i & 4) != 0) {
            str = aggregateResult.dropReason;
        }
        return aggregateResult.copy(z, phoneContext, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getAccepted() {
        return this.accepted;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PhoneContext getContext() {
        return this.context;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDropReason() {
        return this.dropReason;
    }

    public final AggregateResult copy(boolean accepted, PhoneContext context, String dropReason) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new AggregateResult(accepted, context, dropReason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AggregateResult)) {
            return false;
        }
        AggregateResult aggregateResult = (AggregateResult) other;
        return this.accepted == aggregateResult.accepted && Intrinsics.areEqual(this.context, aggregateResult.context) && Intrinsics.areEqual(this.dropReason, aggregateResult.dropReason);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.accepted) * 31) + this.context.hashCode()) * 31) + (this.dropReason == null ? 0 : this.dropReason.hashCode());
    }

    public String toString() {
        return "AggregateResult(accepted=" + this.accepted + ", context=" + this.context + ", dropReason=" + this.dropReason + ")";
    }

    public AggregateResult(boolean accepted, PhoneContext context, String dropReason) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.accepted = accepted;
        this.context = context;
        this.dropReason = dropReason;
    }

    public /* synthetic */ AggregateResult(boolean z, PhoneContext phoneContext, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, phoneContext, (i & 4) != 0 ? null : str);
    }

    public final boolean getAccepted() {
        return this.accepted;
    }

    public final PhoneContext getContext() {
        return this.context;
    }

    public final String getDropReason() {
        return this.dropReason;
    }
}
