package com.jarvis.core.execution;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatalogToolRegistry.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/execution/ToolRegistryResult;", "", "<init>", "()V", "isOk", "", "()Z", "Ok", "Rejected", "Lcom/jarvis/core/execution/ToolRegistryResult$Ok;", "Lcom/jarvis/core/execution/ToolRegistryResult$Rejected;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ToolRegistryResult {
    public /* synthetic */ ToolRegistryResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: CatalogToolRegistry.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/execution/ToolRegistryResult$Ok;", "Lcom/jarvis/core/execution/ToolRegistryResult;", "toolId", "", "<init>", "(Ljava/lang/String;)V", "getToolId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Ok extends ToolRegistryResult {
        private final String toolId;

        public static /* synthetic */ Ok copy$default(Ok ok, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = ok.toolId;
            }
            return ok.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getToolId() {
            return this.toolId;
        }

        public final Ok copy(String toolId) {
            Intrinsics.checkNotNullParameter(toolId, "toolId");
            return new Ok(toolId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Ok) && Intrinsics.areEqual(this.toolId, ((Ok) other).toolId);
        }

        public int hashCode() {
            return this.toolId.hashCode();
        }

        public String toString() {
            return "Ok(toolId=" + this.toolId + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Ok(String toolId) {
            super(null);
            Intrinsics.checkNotNullParameter(toolId, "toolId");
            this.toolId = toolId;
        }

        public final String getToolId() {
            return this.toolId;
        }
    }

    private ToolRegistryResult() {
    }

    /* JADX INFO: compiled from: CatalogToolRegistry.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/execution/ToolRegistryResult$Rejected;", "Lcom/jarvis/core/execution/ToolRegistryResult;", "reason", "Lcom/jarvis/core/execution/ToolRegistryRejectReason;", "message", "", "<init>", "(Lcom/jarvis/core/execution/ToolRegistryRejectReason;Ljava/lang/String;)V", "getReason", "()Lcom/jarvis/core/execution/ToolRegistryRejectReason;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Rejected extends ToolRegistryResult {
        private final String message;
        private final ToolRegistryRejectReason reason;

        public static /* synthetic */ Rejected copy$default(Rejected rejected, ToolRegistryRejectReason toolRegistryRejectReason, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                toolRegistryRejectReason = rejected.reason;
            }
            if ((i & 2) != 0) {
                str = rejected.message;
            }
            return rejected.copy(toolRegistryRejectReason, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ToolRegistryRejectReason getReason() {
            return this.reason;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Rejected copy(ToolRegistryRejectReason reason, String message) {
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
        public Rejected(ToolRegistryRejectReason reason, String message) {
            super(null);
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(message, "message");
            this.reason = reason;
            this.message = message;
        }

        public final String getMessage() {
            return this.message;
        }

        public final ToolRegistryRejectReason getReason() {
            return this.reason;
        }
    }

    public final boolean isOk() {
        return this instanceof Ok;
    }
}
