package com.jarvis.core.companion;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextSnapshot.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010\u001a\u001a\u00020\u0007J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003JK\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lcom/jarvis/core/companion/ContextSnapshot;", "", "context", "Lcom/jarvis/core/companion/PhoneContext;", "takenAtMillis", "", "lastAcceptedObservationId", "", "lastAcceptedAtMillis", "lastAcceptedSource", "Lcom/jarvis/core/companion/ObservationSource;", "lastAcceptedType", "Lcom/jarvis/core/companion/ObservationType;", "<init>", "(Lcom/jarvis/core/companion/PhoneContext;JLjava/lang/String;JLcom/jarvis/core/companion/ObservationSource;Lcom/jarvis/core/companion/ObservationType;)V", "getContext", "()Lcom/jarvis/core/companion/PhoneContext;", "getTakenAtMillis", "()J", "getLastAcceptedObservationId", "()Ljava/lang/String;", "getLastAcceptedAtMillis", "getLastAcceptedSource", "()Lcom/jarvis/core/companion/ObservationSource;", "getLastAcceptedType", "()Lcom/jarvis/core/companion/ObservationType;", "describe", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ContextSnapshot {
    private final PhoneContext context;
    private final long lastAcceptedAtMillis;
    private final String lastAcceptedObservationId;
    private final ObservationSource lastAcceptedSource;
    private final ObservationType lastAcceptedType;
    private final long takenAtMillis;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PhoneContext getContext() {
        return this.context;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTakenAtMillis() {
        return this.takenAtMillis;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLastAcceptedObservationId() {
        return this.lastAcceptedObservationId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getLastAcceptedAtMillis() {
        return this.lastAcceptedAtMillis;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ObservationSource getLastAcceptedSource() {
        return this.lastAcceptedSource;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final ObservationType getLastAcceptedType() {
        return this.lastAcceptedType;
    }

    public final ContextSnapshot copy(PhoneContext context, long takenAtMillis, String lastAcceptedObservationId, long lastAcceptedAtMillis, ObservationSource lastAcceptedSource, ObservationType lastAcceptedType) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ContextSnapshot(context, takenAtMillis, lastAcceptedObservationId, lastAcceptedAtMillis, lastAcceptedSource, lastAcceptedType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextSnapshot)) {
            return false;
        }
        ContextSnapshot contextSnapshot = (ContextSnapshot) other;
        return Intrinsics.areEqual(this.context, contextSnapshot.context) && this.takenAtMillis == contextSnapshot.takenAtMillis && Intrinsics.areEqual(this.lastAcceptedObservationId, contextSnapshot.lastAcceptedObservationId) && this.lastAcceptedAtMillis == contextSnapshot.lastAcceptedAtMillis && this.lastAcceptedSource == contextSnapshot.lastAcceptedSource && this.lastAcceptedType == contextSnapshot.lastAcceptedType;
    }

    public int hashCode() {
        return (((((((((this.context.hashCode() * 31) + Long.hashCode(this.takenAtMillis)) * 31) + (this.lastAcceptedObservationId == null ? 0 : this.lastAcceptedObservationId.hashCode())) * 31) + Long.hashCode(this.lastAcceptedAtMillis)) * 31) + (this.lastAcceptedSource == null ? 0 : this.lastAcceptedSource.hashCode())) * 31) + (this.lastAcceptedType != null ? this.lastAcceptedType.hashCode() : 0);
    }

    public String toString() {
        return "ContextSnapshot(context=" + this.context + ", takenAtMillis=" + this.takenAtMillis + ", lastAcceptedObservationId=" + this.lastAcceptedObservationId + ", lastAcceptedAtMillis=" + this.lastAcceptedAtMillis + ", lastAcceptedSource=" + this.lastAcceptedSource + ", lastAcceptedType=" + this.lastAcceptedType + ")";
    }

    public ContextSnapshot(PhoneContext context, long takenAtMillis, String lastAcceptedObservationId, long lastAcceptedAtMillis, ObservationSource lastAcceptedSource, ObservationType lastAcceptedType) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.takenAtMillis = takenAtMillis;
        this.lastAcceptedObservationId = lastAcceptedObservationId;
        this.lastAcceptedAtMillis = lastAcceptedAtMillis;
        this.lastAcceptedSource = lastAcceptedSource;
        this.lastAcceptedType = lastAcceptedType;
    }

    public /* synthetic */ ContextSnapshot(PhoneContext phoneContext, long j, String str, long j2, ObservationSource observationSource, ObservationType observationType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(phoneContext, j, (i & 4) != 0 ? null : str, (i & 8) != 0 ? 0L : j2, (i & 16) != 0 ? null : observationSource, (i & 32) != 0 ? null : observationType);
    }

    public final PhoneContext getContext() {
        return this.context;
    }

    public final long getTakenAtMillis() {
        return this.takenAtMillis;
    }

    public final String getLastAcceptedObservationId() {
        return this.lastAcceptedObservationId;
    }

    public final long getLastAcceptedAtMillis() {
        return this.lastAcceptedAtMillis;
    }

    public final ObservationSource getLastAcceptedSource() {
        return this.lastAcceptedSource;
    }

    public final ObservationType getLastAcceptedType() {
        return this.lastAcceptedType;
    }

    public final String describe() {
        return this.context.describe();
    }
}
