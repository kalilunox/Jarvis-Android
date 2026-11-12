package com.jarvis.core.autonomy;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ActionSafetyGateModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/autonomy/ContextFreshness;", "", "contextCapturedAtMillis", "", "maxAgeMillis", "<init>", "(JJ)V", "getContextCapturedAtMillis", "()J", "getMaxAgeMillis", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ContextFreshness {
    public static final long DEFAULT_MAX_AGE_MS = 30000;
    private final long contextCapturedAtMillis;
    private final long maxAgeMillis;

    public static /* synthetic */ ContextFreshness copy$default(ContextFreshness contextFreshness, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = contextFreshness.contextCapturedAtMillis;
        }
        if ((i & 2) != 0) {
            j2 = contextFreshness.maxAgeMillis;
        }
        return contextFreshness.copy(j, j2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getContextCapturedAtMillis() {
        return this.contextCapturedAtMillis;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getMaxAgeMillis() {
        return this.maxAgeMillis;
    }

    public final ContextFreshness copy(long contextCapturedAtMillis, long maxAgeMillis) {
        return new ContextFreshness(contextCapturedAtMillis, maxAgeMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextFreshness)) {
            return false;
        }
        ContextFreshness contextFreshness = (ContextFreshness) other;
        return this.contextCapturedAtMillis == contextFreshness.contextCapturedAtMillis && this.maxAgeMillis == contextFreshness.maxAgeMillis;
    }

    public int hashCode() {
        return (Long.hashCode(this.contextCapturedAtMillis) * 31) + Long.hashCode(this.maxAgeMillis);
    }

    public String toString() {
        return "ContextFreshness(contextCapturedAtMillis=" + this.contextCapturedAtMillis + ", maxAgeMillis=" + this.maxAgeMillis + ")";
    }

    public ContextFreshness(long contextCapturedAtMillis, long maxAgeMillis) {
        this.contextCapturedAtMillis = contextCapturedAtMillis;
        this.maxAgeMillis = maxAgeMillis;
    }

    public /* synthetic */ ContextFreshness(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? 30000L : j2);
    }

    public final long getContextCapturedAtMillis() {
        return this.contextCapturedAtMillis;
    }

    public final long getMaxAgeMillis() {
        return this.maxAgeMillis;
    }
}
