package com.jarvis.core.execution;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ToolError.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\bHÆ\u0003J=\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/execution/ToolError;", "", "category", "Lcom/jarvis/core/execution/ToolErrorCategory;", "code", "", "message", "details", "", "<init>", "(Lcom/jarvis/core/execution/ToolErrorCategory;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getCategory", "()Lcom/jarvis/core/execution/ToolErrorCategory;", "getCode", "()Ljava/lang/String;", "getMessage", "getDetails", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolError {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int MAX_DETAILS = 8;
    public static final int MAX_KEY = 48;
    public static final int MAX_MESSAGE = 240;
    public static final int MAX_VALUE = 96;
    private final ToolErrorCategory category;
    private final String code;
    private final Map<String, String> details;
    private final String message;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ToolError copy$default(ToolError toolError, ToolErrorCategory toolErrorCategory, String str, String str2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            toolErrorCategory = toolError.category;
        }
        if ((i & 2) != 0) {
            str = toolError.code;
        }
        if ((i & 4) != 0) {
            str2 = toolError.message;
        }
        if ((i & 8) != 0) {
            map = toolError.details;
        }
        return toolError.copy(toolErrorCategory, str, str2, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ToolErrorCategory getCategory() {
        return this.category;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final Map<String, String> component4() {
        return this.details;
    }

    public final ToolError copy(ToolErrorCategory category, String code, String message, Map<String, String> details) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(details, "details");
        return new ToolError(category, code, message, details);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolError)) {
            return false;
        }
        ToolError toolError = (ToolError) other;
        return this.category == toolError.category && Intrinsics.areEqual(this.code, toolError.code) && Intrinsics.areEqual(this.message, toolError.message) && Intrinsics.areEqual(this.details, toolError.details);
    }

    public int hashCode() {
        return (((((this.category.hashCode() * 31) + this.code.hashCode()) * 31) + this.message.hashCode()) * 31) + this.details.hashCode();
    }

    public String toString() {
        return "ToolError(category=" + this.category + ", code=" + this.code + ", message=" + this.message + ", details=" + this.details + ")";
    }

    public ToolError(ToolErrorCategory category, String code, String message, Map<String, String> details) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(details, "details");
        this.category = category;
        this.code = code;
        this.message = message;
        this.details = details;
        if (StringsKt.isBlank(this.code)) {
            throw new IllegalArgumentException("ToolError.code blank".toString());
        }
        if (!(this.message.length() <= 240)) {
            throw new IllegalArgumentException("ToolError.message too long".toString());
        }
        if (!(this.details.size() <= 8)) {
            throw new IllegalArgumentException("too many ToolError details".toString());
        }
        for (Map.Entry<String, String> entry : this.details.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (!(k.length() <= 48)) {
                throw new IllegalArgumentException("ToolError detail key too long".toString());
            }
            if (!(v.length() <= 96)) {
                throw new IllegalArgumentException("ToolError detail value too long".toString());
            }
        }
    }

    public /* synthetic */ ToolError(ToolErrorCategory toolErrorCategory, String str, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(toolErrorCategory, str, str2, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    public final ToolErrorCategory getCategory() {
        return this.category;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Map<String, String> getDetails() {
        return this.details;
    }

    /* JADX INFO: compiled from: ToolError.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/execution/ToolError$Companion;", "", "<init>", "()V", "MAX_MESSAGE", "", "MAX_DETAILS", "MAX_KEY", "MAX_VALUE", "unsupported", "Lcom/jarvis/core/execution/ToolError;", "message", "", "invalidInput", "code", "unavailable", "permissionFailure", "executionFailure", "timeout", "cancelled", "internal", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ ToolError unsupported$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "Executor not implemented";
            }
            return companion.unsupported(str);
        }

        public final ToolError unsupported(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ToolError(ToolErrorCategory.UNSUPPORTED, "EXECUTOR_NOT_IMPLEMENTED", message, null, 8, null);
        }

        public static /* synthetic */ ToolError invalidInput$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "INVALID_INPUT";
            }
            return companion.invalidInput(str, str2);
        }

        public final ToolError invalidInput(String message, String code) {
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(code, "code");
            return new ToolError(ToolErrorCategory.INVALID_INPUT, code, message, null, 8, null);
        }

        public final ToolError unavailable(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ToolError(ToolErrorCategory.UNAVAILABLE, "TOOL_UNAVAILABLE", message, null, 8, null);
        }

        public final ToolError permissionFailure(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ToolError(ToolErrorCategory.PERMISSION_FAILURE, "PERMISSION_FAILURE", message, null, 8, null);
        }

        public final ToolError executionFailure(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ToolError(ToolErrorCategory.EXECUTION_FAILURE, "EXECUTION_FAILURE", message, null, 8, null);
        }

        public static /* synthetic */ ToolError timeout$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "Tool timed out";
            }
            return companion.timeout(str);
        }

        public final ToolError timeout(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ToolError(ToolErrorCategory.TIMEOUT, "TIMEOUT", message, null, 8, null);
        }

        public static /* synthetic */ ToolError cancelled$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "Tool cancelled";
            }
            return companion.cancelled(str);
        }

        public final ToolError cancelled(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ToolError(ToolErrorCategory.CANCELLED, "CANCELLED", message, null, 8, null);
        }

        public final ToolError internal(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ToolError(ToolErrorCategory.INTERNAL_ERROR, "INTERNAL_ERROR", message, null, 8, null);
        }
    }
}
