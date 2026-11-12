package com.jarvis.core.remote;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RemoteDeviceModels.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/remote/RemoteDevice;", "", "deviceId", "", "kind", "Lcom/jarvis/core/remote/RemoteDeviceKind;", "displayName", "status", "Lcom/jarvis/core/remote/RemoteDeviceStatus;", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/remote/RemoteDeviceKind;Ljava/lang/String;Lcom/jarvis/core/remote/RemoteDeviceStatus;)V", "getDeviceId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/remote/RemoteDeviceKind;", "getDisplayName", "getStatus", "()Lcom/jarvis/core/remote/RemoteDeviceStatus;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RemoteDevice {
    private final String deviceId;
    private final String displayName;
    private final RemoteDeviceKind kind;
    private final RemoteDeviceStatus status;

    public static /* synthetic */ RemoteDevice copy$default(RemoteDevice remoteDevice, String str, RemoteDeviceKind remoteDeviceKind, String str2, RemoteDeviceStatus remoteDeviceStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            str = remoteDevice.deviceId;
        }
        if ((i & 2) != 0) {
            remoteDeviceKind = remoteDevice.kind;
        }
        if ((i & 4) != 0) {
            str2 = remoteDevice.displayName;
        }
        if ((i & 8) != 0) {
            remoteDeviceStatus = remoteDevice.status;
        }
        return remoteDevice.copy(str, remoteDeviceKind, str2, remoteDeviceStatus);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final RemoteDeviceKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final RemoteDeviceStatus getStatus() {
        return this.status;
    }

    public final RemoteDevice copy(String deviceId, RemoteDeviceKind kind, String displayName, RemoteDeviceStatus status) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(status, "status");
        return new RemoteDevice(deviceId, kind, displayName, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RemoteDevice)) {
            return false;
        }
        RemoteDevice remoteDevice = (RemoteDevice) other;
        return Intrinsics.areEqual(this.deviceId, remoteDevice.deviceId) && this.kind == remoteDevice.kind && Intrinsics.areEqual(this.displayName, remoteDevice.displayName) && this.status == remoteDevice.status;
    }

    public int hashCode() {
        return (((((this.deviceId.hashCode() * 31) + this.kind.hashCode()) * 31) + this.displayName.hashCode()) * 31) + this.status.hashCode();
    }

    public String toString() {
        return "RemoteDevice(deviceId=" + this.deviceId + ", kind=" + this.kind + ", displayName=" + this.displayName + ", status=" + this.status + ")";
    }

    public RemoteDevice(String deviceId, RemoteDeviceKind kind, String displayName, RemoteDeviceStatus status) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(status, "status");
        this.deviceId = deviceId;
        this.kind = kind;
        this.displayName = displayName;
        this.status = status;
        if (StringsKt.isBlank(this.deviceId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!StringsKt.isBlank(this.displayName)) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ RemoteDevice(String str, RemoteDeviceKind remoteDeviceKind, String str2, RemoteDeviceStatus remoteDeviceStatus, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, remoteDeviceKind, str2, (i & 8) != 0 ? RemoteDeviceStatus.UNKNOWN : remoteDeviceStatus);
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final RemoteDeviceKind getKind() {
        return this.kind;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final RemoteDeviceStatus getStatus() {
        return this.status;
    }
}
