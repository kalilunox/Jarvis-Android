package com.jarvis.core.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeatureStatus.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u000eJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/runtime/FeatureStatusSnapshot;", "", "capturedAtMillis", "", "features", "", "Lcom/jarvis/core/runtime/FeatureStatus;", "<init>", "(JLjava/util/List;)V", "getCapturedAtMillis", "()J", "getFeatures", "()Ljava/util/List;", "lines", "", "find", "id", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class FeatureStatusSnapshot {
    private final long capturedAtMillis;
    private final List<FeatureStatus> features;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FeatureStatusSnapshot copy$default(FeatureStatusSnapshot featureStatusSnapshot, long j, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            j = featureStatusSnapshot.capturedAtMillis;
        }
        if ((i & 2) != 0) {
            list = featureStatusSnapshot.features;
        }
        return featureStatusSnapshot.copy(j, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getCapturedAtMillis() {
        return this.capturedAtMillis;
    }

    public final List<FeatureStatus> component2() {
        return this.features;
    }

    public final FeatureStatusSnapshot copy(long capturedAtMillis, List<FeatureStatus> features) {
        Intrinsics.checkNotNullParameter(features, "features");
        return new FeatureStatusSnapshot(capturedAtMillis, features);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatureStatusSnapshot)) {
            return false;
        }
        FeatureStatusSnapshot featureStatusSnapshot = (FeatureStatusSnapshot) other;
        return this.capturedAtMillis == featureStatusSnapshot.capturedAtMillis && Intrinsics.areEqual(this.features, featureStatusSnapshot.features);
    }

    public int hashCode() {
        return (Long.hashCode(this.capturedAtMillis) * 31) + this.features.hashCode();
    }

    public String toString() {
        return "FeatureStatusSnapshot(capturedAtMillis=" + this.capturedAtMillis + ", features=" + this.features + ")";
    }

    public FeatureStatusSnapshot(long capturedAtMillis, List<FeatureStatus> features) {
        Intrinsics.checkNotNullParameter(features, "features");
        this.capturedAtMillis = capturedAtMillis;
        this.features = features;
    }

    public final long getCapturedAtMillis() {
        return this.capturedAtMillis;
    }

    public final List<FeatureStatus> getFeatures() {
        return this.features;
    }

    public final List<String> lines() {
        Iterable $this$map$iv = this.features;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            FeatureStatus it = (FeatureStatus) item$iv$iv;
            destination$iv$iv.add(it.m369short());
        }
        return (List) destination$iv$iv;
    }

    public final FeatureStatus find(String id) {
        Object next;
        Intrinsics.checkNotNullParameter(id, "id");
        Iterator<T> it = this.features.iterator();
        while (it.hasNext()) {
            next = it.next();
            FeatureStatus it2 = (FeatureStatus) next;
            if (Intrinsics.areEqual(it2.getId(), id)) {
                return (FeatureStatus) next;
            }
        }
        next = null;
        return (FeatureStatus) next;
    }
}
