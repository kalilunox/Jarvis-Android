package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContextMatchResult.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0000J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\bHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/proactive/ContextMatchWeights;", "", "trigger", "", "followApp", "hour", "packageEntity", "hourTolerance", "", "<init>", "(FFFFI)V", "getTrigger", "()F", "getFollowApp", "getHour", "getPackageEntity", "getHourTolerance", "()I", "normalized", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ContextMatchWeights {
    private final float followApp;
    private final float hour;
    private final int hourTolerance;
    private final float packageEntity;
    private final float trigger;

    public ContextMatchWeights() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 0, 31, null);
    }

    public static /* synthetic */ ContextMatchWeights copy$default(ContextMatchWeights contextMatchWeights, float f, float f2, float f3, float f4, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f = contextMatchWeights.trigger;
        }
        if ((i2 & 2) != 0) {
            f2 = contextMatchWeights.followApp;
        }
        float f5 = f2;
        if ((i2 & 4) != 0) {
            f3 = contextMatchWeights.hour;
        }
        float f6 = f3;
        if ((i2 & 8) != 0) {
            f4 = contextMatchWeights.packageEntity;
        }
        float f7 = f4;
        if ((i2 & 16) != 0) {
            i = contextMatchWeights.hourTolerance;
        }
        return contextMatchWeights.copy(f, f5, f6, f7, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getTrigger() {
        return this.trigger;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getFollowApp() {
        return this.followApp;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getHour() {
        return this.hour;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getPackageEntity() {
        return this.packageEntity;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getHourTolerance() {
        return this.hourTolerance;
    }

    public final ContextMatchWeights copy(float trigger, float followApp, float hour, float packageEntity, int hourTolerance) {
        return new ContextMatchWeights(trigger, followApp, hour, packageEntity, hourTolerance);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextMatchWeights)) {
            return false;
        }
        ContextMatchWeights contextMatchWeights = (ContextMatchWeights) other;
        return Float.compare(this.trigger, contextMatchWeights.trigger) == 0 && Float.compare(this.followApp, contextMatchWeights.followApp) == 0 && Float.compare(this.hour, contextMatchWeights.hour) == 0 && Float.compare(this.packageEntity, contextMatchWeights.packageEntity) == 0 && this.hourTolerance == contextMatchWeights.hourTolerance;
    }

    public int hashCode() {
        return (((((((Float.hashCode(this.trigger) * 31) + Float.hashCode(this.followApp)) * 31) + Float.hashCode(this.hour)) * 31) + Float.hashCode(this.packageEntity)) * 31) + Integer.hashCode(this.hourTolerance);
    }

    public String toString() {
        return "ContextMatchWeights(trigger=" + this.trigger + ", followApp=" + this.followApp + ", hour=" + this.hour + ", packageEntity=" + this.packageEntity + ", hourTolerance=" + this.hourTolerance + ")";
    }

    public ContextMatchWeights(float trigger, float followApp, float hour, float packageEntity, int hourTolerance) {
        this.trigger = trigger;
        this.followApp = followApp;
        this.hour = hour;
        this.packageEntity = packageEntity;
        this.hourTolerance = hourTolerance;
    }

    public /* synthetic */ ContextMatchWeights(float f, float f2, float f3, float f4, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.45f : f, (i2 & 2) != 0 ? 0.25f : f2, (i2 & 4) != 0 ? 0.2f : f3, (i2 & 8) != 0 ? 0.1f : f4, (i2 & 16) != 0 ? 2 : i);
    }

    public final float getTrigger() {
        return this.trigger;
    }

    public final float getFollowApp() {
        return this.followApp;
    }

    public final float getHour() {
        return this.hour;
    }

    public final float getPackageEntity() {
        return this.packageEntity;
    }

    public final int getHourTolerance() {
        return this.hourTolerance;
    }

    public final ContextMatchWeights normalized() {
        float s = this.trigger + this.followApp + this.hour + this.packageEntity;
        return s <= 0.0f ? this : copy$default(this, this.trigger / s, this.followApp / s, this.hour / s, this.packageEntity / s, 0, 16, null);
    }
}
