package com.jarvis.core.runtime;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FeatureStatus.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000e\u001a\u00020\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/runtime/FeatureStatus;", "", "id", "", "lifecycle", "Lcom/jarvis/core/runtime/FeatureLifecycle;", "note", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/runtime/FeatureLifecycle;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLifecycle", "()Lcom/jarvis/core/runtime/FeatureLifecycle;", "getNote", "short", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class FeatureStatus {
    private final String id;
    private final FeatureLifecycle lifecycle;
    private final String note;

    public static /* synthetic */ FeatureStatus copy$default(FeatureStatus featureStatus, String str, FeatureLifecycle featureLifecycle, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = featureStatus.id;
        }
        if ((i & 2) != 0) {
            featureLifecycle = featureStatus.lifecycle;
        }
        if ((i & 4) != 0) {
            str2 = featureStatus.note;
        }
        return featureStatus.copy(str, featureLifecycle, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FeatureLifecycle getLifecycle() {
        return this.lifecycle;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    public final FeatureStatus copy(String id, FeatureLifecycle lifecycle, String note) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(note, "note");
        return new FeatureStatus(id, lifecycle, note);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatureStatus)) {
            return false;
        }
        FeatureStatus featureStatus = (FeatureStatus) other;
        return Intrinsics.areEqual(this.id, featureStatus.id) && this.lifecycle == featureStatus.lifecycle && Intrinsics.areEqual(this.note, featureStatus.note);
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.lifecycle.hashCode()) * 31) + this.note.hashCode();
    }

    public String toString() {
        return "FeatureStatus(id=" + this.id + ", lifecycle=" + this.lifecycle + ", note=" + this.note + ")";
    }

    public FeatureStatus(String id, FeatureLifecycle lifecycle, String note) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(note, "note");
        this.id = id;
        this.lifecycle = lifecycle;
        this.note = note;
    }

    public /* synthetic */ FeatureStatus(String str, FeatureLifecycle featureLifecycle, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, featureLifecycle, (i & 4) != 0 ? "" : str2);
    }

    public final String getId() {
        return this.id;
    }

    public final FeatureLifecycle getLifecycle() {
        return this.lifecycle;
    }

    public final String getNote() {
        return this.note;
    }

    /* JADX INFO: renamed from: short, reason: not valid java name */
    public final String m369short() {
        String str;
        String str2 = this.id;
        String lowerCase = this.lifecycle.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (StringsKt.isBlank(this.note)) {
            str = "";
        } else {
            str = "(" + this.note + ")";
        }
        return str2 + "=" + lowerCase + str;
    }
}
