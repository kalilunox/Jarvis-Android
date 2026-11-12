package com.jarvis.core.execution;

import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ToolExecutionResult.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u000fHÆ\u0003Jg\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00060"}, d2 = {"Lcom/jarvis/core/execution/ToolExecutionResult;", "", "requestId", "", "toolId", "status", "Lcom/jarvis/core/execution/ToolExecutionStatus;", "output", "", "error", "Lcom/jarvis/core/execution/ToolError;", "metadata", "Lcom/jarvis/core/execution/ToolMetadata;", "executionId", "durationMs", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/execution/ToolExecutionStatus;Ljava/util/Map;Lcom/jarvis/core/execution/ToolError;Lcom/jarvis/core/execution/ToolMetadata;Ljava/lang/String;J)V", "getRequestId", "()Ljava/lang/String;", "getToolId", "getStatus", "()Lcom/jarvis/core/execution/ToolExecutionStatus;", "getOutput", "()Ljava/util/Map;", "getError", "()Lcom/jarvis/core/execution/ToolError;", "getMetadata", "()Lcom/jarvis/core/execution/ToolMetadata;", "getExecutionId", "getDurationMs", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolExecutionResult {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int MAX_KEY = 48;
    public static final int MAX_OUTPUT = 24;
    public static final int MAX_VALUE = 256;
    private final long durationMs;
    private final ToolError error;
    private final String executionId;
    private final ToolMetadata metadata;
    private final Map<String, String> output;
    private final String requestId;
    private final ToolExecutionStatus status;
    private final String toolId;

    /* JADX INFO: compiled from: ToolExecutionResult.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToolExecutionStatus.values().length];
            try {
                iArr[ToolExecutionStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToolExecutionStatus.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ToolExecutionStatus getStatus() {
        return this.status;
    }

    public final Map<String, String> component4() {
        return this.output;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ToolError getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getExecutionId() {
        return this.executionId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    public final ToolExecutionResult copy(String requestId, String toolId, ToolExecutionStatus status, Map<String, String> output, ToolError error, ToolMetadata metadata, String executionId, long durationMs) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(executionId, "executionId");
        return new ToolExecutionResult(requestId, toolId, status, output, error, metadata, executionId, durationMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolExecutionResult)) {
            return false;
        }
        ToolExecutionResult toolExecutionResult = (ToolExecutionResult) other;
        return Intrinsics.areEqual(this.requestId, toolExecutionResult.requestId) && Intrinsics.areEqual(this.toolId, toolExecutionResult.toolId) && this.status == toolExecutionResult.status && Intrinsics.areEqual(this.output, toolExecutionResult.output) && Intrinsics.areEqual(this.error, toolExecutionResult.error) && Intrinsics.areEqual(this.metadata, toolExecutionResult.metadata) && Intrinsics.areEqual(this.executionId, toolExecutionResult.executionId) && this.durationMs == toolExecutionResult.durationMs;
    }

    public int hashCode() {
        return (((((((((((((this.requestId.hashCode() * 31) + this.toolId.hashCode()) * 31) + this.status.hashCode()) * 31) + this.output.hashCode()) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31) + this.metadata.hashCode()) * 31) + this.executionId.hashCode()) * 31) + Long.hashCode(this.durationMs);
    }

    public String toString() {
        return "ToolExecutionResult(requestId=" + this.requestId + ", toolId=" + this.toolId + ", status=" + this.status + ", output=" + this.output + ", error=" + this.error + ", metadata=" + this.metadata + ", executionId=" + this.executionId + ", durationMs=" + this.durationMs + ")";
    }

    public ToolExecutionResult(String requestId, String toolId, ToolExecutionStatus status, Map<String, String> output, ToolError error, ToolMetadata metadata, String executionId, long durationMs) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(executionId, "executionId");
        this.requestId = requestId;
        this.toolId = toolId;
        this.status = status;
        this.output = output;
        this.error = error;
        this.metadata = metadata;
        this.executionId = executionId;
        this.durationMs = durationMs;
        if (StringsKt.isBlank(this.requestId)) {
            throw new IllegalArgumentException("requestId blank".toString());
        }
        if (StringsKt.isBlank(this.toolId)) {
            throw new IllegalArgumentException("toolId blank".toString());
        }
        if (!(this.output.size() <= 24)) {
            throw new IllegalArgumentException("too many output fields".toString());
        }
        for (Map.Entry<String, String> entry : this.output.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (!(k.length() <= 48)) {
                throw new IllegalArgumentException("output key too long".toString());
            }
            if (!(v.length() <= 256)) {
                throw new IllegalArgumentException("output value too long".toString());
            }
        }
        switch (WhenMappings.$EnumSwitchMapping$0[this.status.ordinal()]) {
            case 1:
                if (!(this.error == null)) {
                    throw new IllegalArgumentException("SUCCESS must not carry ToolError".toString());
                }
                return;
            case 2:
                if (!(this.error != null)) {
                    throw new IllegalArgumentException("FAILURE requires ToolError".toString());
                }
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ToolExecutionResult(String str, String str2, ToolExecutionStatus toolExecutionStatus, Map map, ToolError toolError, ToolMetadata toolMetadata, String str3, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str4;
        Map mapEmptyMap = (i & 8) != 0 ? MapsKt.emptyMap() : map;
        ToolError toolError2 = (i & 16) != 0 ? null : toolError;
        ToolMetadata toolMetadata2 = (i & 32) != 0 ? new ToolMetadata(null, null, null, null, 15, null) : toolMetadata;
        if ((i & 64) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str4 = string;
        } else {
            str4 = str3;
        }
        this(str, str2, toolExecutionStatus, mapEmptyMap, toolError2, toolMetadata2, str4, (i & 128) != 0 ? 0L : j);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final ToolExecutionStatus getStatus() {
        return this.status;
    }

    public final Map<String, String> getOutput() {
        return this.output;
    }

    public final ToolError getError() {
        return this.error;
    }

    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    public final String getExecutionId() {
        return this.executionId;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    /* JADX INFO: compiled from: ToolExecutionResult.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012J2\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/execution/ToolExecutionResult$Companion;", "", "<init>", "()V", "MAX_OUTPUT", "", "MAX_KEY", "MAX_VALUE", "success", "Lcom/jarvis/core/execution/ToolExecutionResult;", "requestId", "", "toolId", "output", "", "metadata", "Lcom/jarvis/core/execution/ToolMetadata;", "durationMs", "", "failure", "error", "Lcom/jarvis/core/execution/ToolError;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ToolExecutionResult success$default(Companion companion, String str, String str2, Map map, ToolMetadata toolMetadata, long j, int i, Object obj) {
            ToolMetadata toolMetadata2;
            Map mapEmptyMap = (i & 4) != 0 ? MapsKt.emptyMap() : map;
            if ((i & 8) != 0) {
                toolMetadata2 = new ToolMetadata(null, null, null, null, 15, null);
            } else {
                toolMetadata2 = toolMetadata;
            }
            return companion.success(str, str2, mapEmptyMap, toolMetadata2, (i & 16) != 0 ? 0L : j);
        }

        public final ToolExecutionResult success(String requestId, String toolId, Map<String, String> output, ToolMetadata metadata, long durationMs) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            Intrinsics.checkNotNullParameter(toolId, "toolId");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            return new ToolExecutionResult(requestId, toolId, ToolExecutionStatus.SUCCESS, output, null, metadata, null, durationMs, 64, null);
        }

        public static /* synthetic */ ToolExecutionResult failure$default(Companion companion, String str, String str2, ToolError toolError, ToolMetadata toolMetadata, long j, int i, Object obj) {
            ToolMetadata toolMetadata2;
            long j2;
            if ((i & 8) == 0) {
                toolMetadata2 = toolMetadata;
            } else {
                toolMetadata2 = new ToolMetadata(null, null, null, null, 15, null);
            }
            if ((i & 16) == 0) {
                j2 = j;
            } else {
                j2 = 0;
            }
            return companion.failure(str, str2, toolError, toolMetadata2, j2);
        }

        public final ToolExecutionResult failure(String requestId, String toolId, ToolError error, ToolMetadata metadata, long durationMs) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            Intrinsics.checkNotNullParameter(toolId, "toolId");
            Intrinsics.checkNotNullParameter(error, "error");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            return new ToolExecutionResult(requestId, toolId, ToolExecutionStatus.FAILURE, MapsKt.emptyMap(), error, metadata, null, durationMs, 64, null);
        }
    }
}
