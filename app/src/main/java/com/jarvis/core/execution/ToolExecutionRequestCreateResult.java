package com.jarvis.core.execution;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolExecutionRequest.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult;", "", "<init>", "()V", "isCreated", "", "()Z", "Created", "Rejected", "Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult$Created;", "Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult$Rejected;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ToolExecutionRequestCreateResult {
    public /* synthetic */ ToolExecutionRequestCreateResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: ToolExecutionRequest.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult$Created;", "Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult;", "request", "Lcom/jarvis/core/execution/ToolExecutionRequest;", "<init>", "(Lcom/jarvis/core/execution/ToolExecutionRequest;)V", "getRequest", "()Lcom/jarvis/core/execution/ToolExecutionRequest;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Created extends ToolExecutionRequestCreateResult {
        private final ToolExecutionRequest request;

        public static /* synthetic */ Created copy$default(Created created, ToolExecutionRequest toolExecutionRequest, int i, Object obj) {
            if ((i & 1) != 0) {
                toolExecutionRequest = created.request;
            }
            return created.copy(toolExecutionRequest);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ToolExecutionRequest getRequest() {
            return this.request;
        }

        public final Created copy(ToolExecutionRequest request) {
            Intrinsics.checkNotNullParameter(request, "request");
            return new Created(request);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Created) && Intrinsics.areEqual(this.request, ((Created) other).request);
        }

        public int hashCode() {
            return this.request.hashCode();
        }

        public String toString() {
            return "Created(request=" + this.request + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Created(ToolExecutionRequest request) {
            super(null);
            Intrinsics.checkNotNullParameter(request, "request");
            this.request = request;
        }

        public final ToolExecutionRequest getRequest() {
            return this.request;
        }
    }

    private ToolExecutionRequestCreateResult() {
    }

    /* JADX INFO: compiled from: ToolExecutionRequest.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult$Rejected;", "Lcom/jarvis/core/execution/ToolExecutionRequestCreateResult;", "reason", "Lcom/jarvis/core/execution/ToolExecutionRequestRejectReason;", "message", "", "<init>", "(Lcom/jarvis/core/execution/ToolExecutionRequestRejectReason;Ljava/lang/String;)V", "getReason", "()Lcom/jarvis/core/execution/ToolExecutionRequestRejectReason;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Rejected extends ToolExecutionRequestCreateResult {
        private final String message;
        private final ToolExecutionRequestRejectReason reason;

        public static /* synthetic */ Rejected copy$default(Rejected rejected, ToolExecutionRequestRejectReason toolExecutionRequestRejectReason, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                toolExecutionRequestRejectReason = rejected.reason;
            }
            if ((i & 2) != 0) {
                str = rejected.message;
            }
            return rejected.copy(toolExecutionRequestRejectReason, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ToolExecutionRequestRejectReason getReason() {
            return this.reason;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Rejected copy(ToolExecutionRequestRejectReason reason, String message) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(message, "message");
            return new Rejected(reason, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Rejected)) {
                return false;
            }
            Rejected rejected = (Rejected) other;
            return this.reason == rejected.reason && Intrinsics.areEqual(this.message, rejected.message);
        }

        public int hashCode() {
            return (this.reason.hashCode() * 31) + this.message.hashCode();
        }

        public String toString() {
            return "Rejected(reason=" + this.reason + ", message=" + this.message + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Rejected(ToolExecutionRequestRejectReason reason, String message) {
            super(null);
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(message, "message");
            this.reason = reason;
            this.message = message;
        }

        public final ToolExecutionRequestRejectReason getReason() {
            return this.reason;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    public final boolean isCreated() {
        return this instanceof Created;
    }
}
