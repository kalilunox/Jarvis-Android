package com.jarvis.core.routines;

import com.jarvis.app.EventRoutineRunner;
import com.jarvis.core.companion.ObservationType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventRoutines.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/jarvis/core/routines/PhoneEvent;", "", "type", "Lcom/jarvis/core/companion/ObservationType;", EventRoutineRunner.NETWORK, "", "packageName", EventRoutineRunner.STARTUP, "", "<init>", "(Lcom/jarvis/core/companion/ObservationType;Ljava/lang/String;Ljava/lang/String;Z)V", "getType", "()Lcom/jarvis/core/companion/ObservationType;", "getNetwork", "()Ljava/lang/String;", "getPackageName", "getStartup", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PhoneEvent {
    private final String network;
    private final String packageName;
    private final boolean startup;
    private final ObservationType type;

    public static /* synthetic */ PhoneEvent copy$default(PhoneEvent phoneEvent, ObservationType observationType, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            observationType = phoneEvent.type;
        }
        if ((i & 2) != 0) {
            str = phoneEvent.network;
        }
        if ((i & 4) != 0) {
            str2 = phoneEvent.packageName;
        }
        if ((i & 8) != 0) {
            z = phoneEvent.startup;
        }
        return phoneEvent.copy(observationType, str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ObservationType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNetwork() {
        return this.network;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getStartup() {
        return this.startup;
    }

    public final PhoneEvent copy(ObservationType type, String network, String packageName, boolean startup) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new PhoneEvent(type, network, packageName, startup);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhoneEvent)) {
            return false;
        }
        PhoneEvent phoneEvent = (PhoneEvent) other;
        return this.type == phoneEvent.type && Intrinsics.areEqual(this.network, phoneEvent.network) && Intrinsics.areEqual(this.packageName, phoneEvent.packageName) && this.startup == phoneEvent.startup;
    }

    public int hashCode() {
        return (((((this.type.hashCode() * 31) + (this.network == null ? 0 : this.network.hashCode())) * 31) + (this.packageName != null ? this.packageName.hashCode() : 0)) * 31) + Boolean.hashCode(this.startup);
    }

    public String toString() {
        return "PhoneEvent(type=" + this.type + ", network=" + this.network + ", packageName=" + this.packageName + ", startup=" + this.startup + ")";
    }

    public PhoneEvent(ObservationType type, String network, String packageName, boolean startup) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.network = network;
        this.packageName = packageName;
        this.startup = startup;
    }

    public /* synthetic */ PhoneEvent(ObservationType observationType, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(observationType, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? false : z);
    }

    public final ObservationType getType() {
        return this.type;
    }

    public final String getNetwork() {
        return this.network;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean getStartup() {
        return this.startup;
    }
}
