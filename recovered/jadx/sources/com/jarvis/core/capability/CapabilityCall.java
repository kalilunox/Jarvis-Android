package com.jarvis.core.capability;

import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\bHÆ\u0003JI\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/capability/CapabilityCall;", "", "id", "", "requestId", "capabilityId", "operation", "arguments", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getId", "()Ljava/lang/String;", "getRequestId", "getCapabilityId", "getOperation", "getArguments", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CapabilityCall {
    private final Map<String, Object> arguments;
    private final String capabilityId;
    private final String id;
    private final String operation;
    private final String requestId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CapabilityCall copy$default(CapabilityCall capabilityCall, String str, String str2, String str3, String str4, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = capabilityCall.id;
        }
        if ((i & 2) != 0) {
            str2 = capabilityCall.requestId;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = capabilityCall.capabilityId;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = capabilityCall.operation;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            map = capabilityCall.arguments;
        }
        return capabilityCall.copy(str, str5, str6, str7, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCapabilityId() {
        return this.capabilityId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOperation() {
        return this.operation;
    }

    public final Map<String, Object> component5() {
        return this.arguments;
    }

    public final CapabilityCall copy(String id, String requestId, String capabilityId, String operation, Map<String, ? extends Object> arguments) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(capabilityId, "capabilityId");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        return new CapabilityCall(id, requestId, capabilityId, operation, arguments);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CapabilityCall)) {
            return false;
        }
        CapabilityCall capabilityCall = (CapabilityCall) other;
        return Intrinsics.areEqual(this.id, capabilityCall.id) && Intrinsics.areEqual(this.requestId, capabilityCall.requestId) && Intrinsics.areEqual(this.capabilityId, capabilityCall.capabilityId) && Intrinsics.areEqual(this.operation, capabilityCall.operation) && Intrinsics.areEqual(this.arguments, capabilityCall.arguments);
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + this.requestId.hashCode()) * 31) + this.capabilityId.hashCode()) * 31) + (this.operation == null ? 0 : this.operation.hashCode())) * 31) + this.arguments.hashCode();
    }

    public String toString() {
        return "CapabilityCall(id=" + this.id + ", requestId=" + this.requestId + ", capabilityId=" + this.capabilityId + ", operation=" + this.operation + ", arguments=" + this.arguments + ")";
    }

    public CapabilityCall(String id, String requestId, String capabilityId, String operation, Map<String, ? extends Object> arguments) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(capabilityId, "capabilityId");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.id = id;
        this.requestId = requestId;
        this.capabilityId = capabilityId;
        this.operation = operation;
        this.arguments = arguments;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CapabilityCall(String str, String str2, String str3, String str4, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str5;
        if ((i & 1) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str5 = string;
        } else {
            str5 = str;
        }
        this(str5, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? MapsKt.emptyMap() : map);
    }

    public final String getId() {
        return this.id;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getCapabilityId() {
        return this.capabilityId;
    }

    public final String getOperation() {
        return this.operation;
    }

    public final Map<String, Object> getArguments() {
        return this.arguments;
    }
}
