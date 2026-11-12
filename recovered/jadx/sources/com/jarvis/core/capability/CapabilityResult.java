package com.jarvis.core.capability;

import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\tHÆ\u0003J\t\u0010)\u001a\u00020\u000bHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0011HÆ\u0003Jy\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0013\u0010.\u001a\u00020\r2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00063"}, d2 = {"Lcom/jarvis/core/capability/CapabilityResult;", "", "requestId", "", "capabilityId", "status", "Lcom/jarvis/core/common/model/ToolResultStatus;", "message", "data", "", "durationMs", "", "verified", "", "verificationNotes", "", "toolResult", "Lcom/jarvis/core/common/model/ToolResult;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/ToolResultStatus;Ljava/lang/String;Ljava/util/Map;JZLjava/util/List;Lcom/jarvis/core/common/model/ToolResult;)V", "getRequestId", "()Ljava/lang/String;", "getCapabilityId", "getStatus", "()Lcom/jarvis/core/common/model/ToolResultStatus;", "getMessage", "getData", "()Ljava/util/Map;", "getDurationMs", "()J", "getVerified", "()Z", "getVerificationNotes", "()Ljava/util/List;", "getToolResult", "()Lcom/jarvis/core/common/model/ToolResult;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CapabilityResult {
    private final String capabilityId;
    private final Map<String, Object> data;
    private final long durationMs;
    private final String message;
    private final String requestId;
    private final ToolResultStatus status;
    private final ToolResult toolResult;
    private final List<String> verificationNotes;
    private final boolean verified;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCapabilityId() {
        return this.capabilityId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ToolResultStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final Map<String, Object> component5() {
        return this.data;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getVerified() {
        return this.verified;
    }

    public final List<String> component8() {
        return this.verificationNotes;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final ToolResult getToolResult() {
        return this.toolResult;
    }

    public final CapabilityResult copy(String requestId, String capabilityId, ToolResultStatus status, String message, Map<String, ? extends Object> data, long durationMs, boolean verified, List<String> verificationNotes, ToolResult toolResult) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(capabilityId, "capabilityId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(verificationNotes, "verificationNotes");
        return new CapabilityResult(requestId, capabilityId, status, message, data, durationMs, verified, verificationNotes, toolResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CapabilityResult)) {
            return false;
        }
        CapabilityResult capabilityResult = (CapabilityResult) other;
        return Intrinsics.areEqual(this.requestId, capabilityResult.requestId) && Intrinsics.areEqual(this.capabilityId, capabilityResult.capabilityId) && this.status == capabilityResult.status && Intrinsics.areEqual(this.message, capabilityResult.message) && Intrinsics.areEqual(this.data, capabilityResult.data) && this.durationMs == capabilityResult.durationMs && this.verified == capabilityResult.verified && Intrinsics.areEqual(this.verificationNotes, capabilityResult.verificationNotes) && Intrinsics.areEqual(this.toolResult, capabilityResult.toolResult);
    }

    public int hashCode() {
        return (((((((((((((((this.requestId.hashCode() * 31) + this.capabilityId.hashCode()) * 31) + this.status.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + this.data.hashCode()) * 31) + Long.hashCode(this.durationMs)) * 31) + Boolean.hashCode(this.verified)) * 31) + this.verificationNotes.hashCode()) * 31) + (this.toolResult != null ? this.toolResult.hashCode() : 0);
    }

    public String toString() {
        return "CapabilityResult(requestId=" + this.requestId + ", capabilityId=" + this.capabilityId + ", status=" + this.status + ", message=" + this.message + ", data=" + this.data + ", durationMs=" + this.durationMs + ", verified=" + this.verified + ", verificationNotes=" + this.verificationNotes + ", toolResult=" + this.toolResult + ")";
    }

    public CapabilityResult(String requestId, String capabilityId, ToolResultStatus status, String message, Map<String, ? extends Object> data, long durationMs, boolean verified, List<String> verificationNotes, ToolResult toolResult) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(capabilityId, "capabilityId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(verificationNotes, "verificationNotes");
        this.requestId = requestId;
        this.capabilityId = capabilityId;
        this.status = status;
        this.message = message;
        this.data = data;
        this.durationMs = durationMs;
        this.verified = verified;
        this.verificationNotes = verificationNotes;
        this.toolResult = toolResult;
    }

    public /* synthetic */ CapabilityResult(String str, String str2, ToolResultStatus toolResultStatus, String str3, Map map, long j, boolean z, List list, ToolResult toolResult, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, toolResultStatus, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? 0L : j, (i & 64) != 0 ? false : z, (i & 128) != 0 ? CollectionsKt.emptyList() : list, (i & 256) != 0 ? null : toolResult);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getCapabilityId() {
        return this.capabilityId;
    }

    public final ToolResultStatus getStatus() {
        return this.status;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Map<String, Object> getData() {
        return this.data;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final boolean getVerified() {
        return this.verified;
    }

    public final List<String> getVerificationNotes() {
        return this.verificationNotes;
    }

    public final ToolResult getToolResult() {
        return this.toolResult;
    }
}
