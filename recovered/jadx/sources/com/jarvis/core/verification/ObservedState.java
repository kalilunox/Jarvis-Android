package com.jarvis.core.verification;

import com.jarvis.core.storage.JarvisSchema;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerificationModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0006J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/verification/ObservedState;", "", "capturedAtMillis", "", JarvisSchema.TABLE_FACTS, "", "", "source", "<init>", "(JLjava/util/Map;Ljava/lang/String;)V", "getCapturedAtMillis", "()J", "getFacts", "()Ljava/util/Map;", "getSource", "()Ljava/lang/String;", "get", "key", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ObservedState {
    public static final int MAX_FACTS = 32;
    public static final int MAX_KEY = 64;
    public static final int MAX_VALUE = 256;
    private final long capturedAtMillis;
    private final Map<String, String> facts;
    private final String source;

    public ObservedState() {
        this(0L, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ObservedState copy$default(ObservedState observedState, long j, Map map, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = observedState.capturedAtMillis;
        }
        if ((i & 2) != 0) {
            map = observedState.facts;
        }
        if ((i & 4) != 0) {
            str = observedState.source;
        }
        return observedState.copy(j, map, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getCapturedAtMillis() {
        return this.capturedAtMillis;
    }

    public final Map<String, String> component2() {
        return this.facts;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final ObservedState copy(long capturedAtMillis, Map<String, String> facts, String source) {
        Intrinsics.checkNotNullParameter(facts, "facts");
        Intrinsics.checkNotNullParameter(source, "source");
        return new ObservedState(capturedAtMillis, facts, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ObservedState)) {
            return false;
        }
        ObservedState observedState = (ObservedState) other;
        return this.capturedAtMillis == observedState.capturedAtMillis && Intrinsics.areEqual(this.facts, observedState.facts) && Intrinsics.areEqual(this.source, observedState.source);
    }

    public int hashCode() {
        return (((Long.hashCode(this.capturedAtMillis) * 31) + this.facts.hashCode()) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "ObservedState(capturedAtMillis=" + this.capturedAtMillis + ", facts=" + this.facts + ", source=" + this.source + ")";
    }

    public ObservedState(long capturedAtMillis, Map<String, String> facts, String source) {
        Intrinsics.checkNotNullParameter(facts, "facts");
        Intrinsics.checkNotNullParameter(source, "source");
        this.capturedAtMillis = capturedAtMillis;
        this.facts = facts;
        this.source = source;
        if (!(this.facts.size() <= 32)) {
            throw new IllegalArgumentException("too many facts".toString());
        }
        for (Map.Entry<String, String> entry : this.facts.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (!(k.length() <= 64)) {
                throw new IllegalArgumentException("fact key too long".toString());
            }
            if (!(v.length() <= 256)) {
                throw new IllegalArgumentException("fact value too long".toString());
            }
        }
        if (!(this.source.length() <= 64)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ ObservedState(long j, Map map, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? MapsKt.emptyMap() : map, (i & 4) != 0 ? "observer" : str);
    }

    public final long getCapturedAtMillis() {
        return this.capturedAtMillis;
    }

    public final Map<String, String> getFacts() {
        return this.facts;
    }

    public final String getSource() {
        return this.source;
    }

    public final String get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.facts.get(key);
    }
}
