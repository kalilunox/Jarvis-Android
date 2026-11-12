package com.jarvis.core.nlp;

import com.jarvis.app.EventRoutineRunner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemStatusReport.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0014JD\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/jarvis/core/nlp/SystemSnapshot;", "", "batteryPercent", "", "charging", "", EventRoutineRunner.NETWORK, "", "freeStorageGb", "batteryTempC", "<init>", "(IZLjava/lang/String;ILjava/lang/Integer;)V", "getBatteryPercent", "()I", "getCharging", "()Z", "getNetwork", "()Ljava/lang/String;", "getFreeStorageGb", "getBatteryTempC", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "copy", "(IZLjava/lang/String;ILjava/lang/Integer;)Lcom/jarvis/core/nlp/SystemSnapshot;", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SystemSnapshot {
    private final int batteryPercent;
    private final Integer batteryTempC;
    private final boolean charging;
    private final int freeStorageGb;
    private final String network;

    public static /* synthetic */ SystemSnapshot copy$default(SystemSnapshot systemSnapshot, int i, boolean z, String str, int i2, Integer num, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = systemSnapshot.batteryPercent;
        }
        if ((i3 & 2) != 0) {
            z = systemSnapshot.charging;
        }
        boolean z2 = z;
        if ((i3 & 4) != 0) {
            str = systemSnapshot.network;
        }
        String str2 = str;
        if ((i3 & 8) != 0) {
            i2 = systemSnapshot.freeStorageGb;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            num = systemSnapshot.batteryTempC;
        }
        return systemSnapshot.copy(i, z2, str2, i4, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getBatteryPercent() {
        return this.batteryPercent;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getCharging() {
        return this.charging;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getNetwork() {
        return this.network;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getFreeStorageGb() {
        return this.freeStorageGb;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getBatteryTempC() {
        return this.batteryTempC;
    }

    public final SystemSnapshot copy(int batteryPercent, boolean charging, String network, int freeStorageGb, Integer batteryTempC) {
        return new SystemSnapshot(batteryPercent, charging, network, freeStorageGb, batteryTempC);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SystemSnapshot)) {
            return false;
        }
        SystemSnapshot systemSnapshot = (SystemSnapshot) other;
        return this.batteryPercent == systemSnapshot.batteryPercent && this.charging == systemSnapshot.charging && Intrinsics.areEqual(this.network, systemSnapshot.network) && this.freeStorageGb == systemSnapshot.freeStorageGb && Intrinsics.areEqual(this.batteryTempC, systemSnapshot.batteryTempC);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.batteryPercent) * 31) + Boolean.hashCode(this.charging)) * 31) + (this.network == null ? 0 : this.network.hashCode())) * 31) + Integer.hashCode(this.freeStorageGb)) * 31) + (this.batteryTempC != null ? this.batteryTempC.hashCode() : 0);
    }

    public String toString() {
        return "SystemSnapshot(batteryPercent=" + this.batteryPercent + ", charging=" + this.charging + ", network=" + this.network + ", freeStorageGb=" + this.freeStorageGb + ", batteryTempC=" + this.batteryTempC + ")";
    }

    public SystemSnapshot(int batteryPercent, boolean charging, String network, int freeStorageGb, Integer batteryTempC) {
        this.batteryPercent = batteryPercent;
        this.charging = charging;
        this.network = network;
        this.freeStorageGb = freeStorageGb;
        this.batteryTempC = batteryTempC;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SystemSnapshot(int i, boolean z, String str, int i2, Integer num, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        Integer num2;
        if ((i3 & 16) == 0) {
            num2 = num;
        } else {
            num2 = null;
        }
        this(i, z, str, i2, num2);
    }

    public final int getBatteryPercent() {
        return this.batteryPercent;
    }

    public final boolean getCharging() {
        return this.charging;
    }

    public final String getNetwork() {
        return this.network;
    }

    public final int getFreeStorageGb() {
        return this.freeStorageGb;
    }

    public final Integer getBatteryTempC() {
        return this.batteryTempC;
    }
}
