package com.jarvis.core.common.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Intent.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0001HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/common/model/Slot;", "", "name", "", "value", "confidence", "", "<init>", "(Ljava/lang/String;Ljava/lang/Object;F)V", "getName", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "getConfidence", "()F", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Slot {
    private final float confidence;
    private final String name;
    private final Object value;

    public static /* synthetic */ Slot copy$default(Slot slot, String str, Object obj, float f, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = slot.name;
        }
        if ((i & 2) != 0) {
            obj = slot.value;
        }
        if ((i & 4) != 0) {
            f = slot.confidence;
        }
        return slot.copy(str, obj, f);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Object getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    public final Slot copy(String name, Object value, float confidence) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new Slot(name, value, confidence);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Slot)) {
            return false;
        }
        Slot slot = (Slot) other;
        return Intrinsics.areEqual(this.name, slot.name) && Intrinsics.areEqual(this.value, slot.value) && Float.compare(this.confidence, slot.confidence) == 0;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.value.hashCode()) * 31) + Float.hashCode(this.confidence);
    }

    public String toString() {
        return "Slot(name=" + this.name + ", value=" + this.value + ", confidence=" + this.confidence + ")";
    }

    public Slot(String name, Object value, float confidence) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = name;
        this.value = value;
        this.confidence = confidence;
    }

    public /* synthetic */ Slot(String str, Object obj, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, obj, (i & 4) != 0 ? 1.0f : f);
    }

    public final String getName() {
        return this.name;
    }

    public final Object getValue() {
        return this.value;
    }

    public final float getConfidence() {
        return this.confidence;
    }
}
