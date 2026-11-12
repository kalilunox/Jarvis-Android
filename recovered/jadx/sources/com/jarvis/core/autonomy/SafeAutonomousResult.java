package com.jarvis.core.autonomy;

import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeAutonomousExecutor.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u000bHÆ\u0003JA\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/jarvis/core/autonomy/SafeAutonomousResult;", "", "executed", "", "plan", "Lcom/jarvis/core/autonomy/AutonomyActionPlan;", "result", "Lcom/jarvis/core/common/model/ToolResult;", "reason", "", "toolCall", "Lcom/jarvis/core/common/model/ToolCall;", "<init>", "(ZLcom/jarvis/core/autonomy/AutonomyActionPlan;Lcom/jarvis/core/common/model/ToolResult;Ljava/lang/String;Lcom/jarvis/core/common/model/ToolCall;)V", "getExecuted", "()Z", "getPlan", "()Lcom/jarvis/core/autonomy/AutonomyActionPlan;", "getResult", "()Lcom/jarvis/core/common/model/ToolResult;", "getReason", "()Ljava/lang/String;", "getToolCall", "()Lcom/jarvis/core/common/model/ToolCall;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SafeAutonomousResult {
    private final boolean executed;
    private final AutonomyActionPlan plan;
    private final String reason;
    private final ToolResult result;
    private final ToolCall toolCall;

    public static /* synthetic */ SafeAutonomousResult copy$default(SafeAutonomousResult safeAutonomousResult, boolean z, AutonomyActionPlan autonomyActionPlan, ToolResult toolResult, String str, ToolCall toolCall, int i, Object obj) {
        if ((i & 1) != 0) {
            z = safeAutonomousResult.executed;
        }
        if ((i & 2) != 0) {
            autonomyActionPlan = safeAutonomousResult.plan;
        }
        AutonomyActionPlan autonomyActionPlan2 = autonomyActionPlan;
        if ((i & 4) != 0) {
            toolResult = safeAutonomousResult.result;
        }
        ToolResult toolResult2 = toolResult;
        if ((i & 8) != 0) {
            str = safeAutonomousResult.reason;
        }
        String str2 = str;
        if ((i & 16) != 0) {
            toolCall = safeAutonomousResult.toolCall;
        }
        return safeAutonomousResult.copy(z, autonomyActionPlan2, toolResult2, str2, toolCall);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getExecuted() {
        return this.executed;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AutonomyActionPlan getPlan() {
        return this.plan;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ToolResult getResult() {
        return this.result;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ToolCall getToolCall() {
        return this.toolCall;
    }

    public final SafeAutonomousResult copy(boolean executed, AutonomyActionPlan plan, ToolResult result, String reason, ToolCall toolCall) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new SafeAutonomousResult(executed, plan, result, reason, toolCall);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SafeAutonomousResult)) {
            return false;
        }
        SafeAutonomousResult safeAutonomousResult = (SafeAutonomousResult) other;
        return this.executed == safeAutonomousResult.executed && Intrinsics.areEqual(this.plan, safeAutonomousResult.plan) && Intrinsics.areEqual(this.result, safeAutonomousResult.result) && Intrinsics.areEqual(this.reason, safeAutonomousResult.reason) && Intrinsics.areEqual(this.toolCall, safeAutonomousResult.toolCall);
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.executed) * 31) + (this.plan == null ? 0 : this.plan.hashCode())) * 31) + (this.result == null ? 0 : this.result.hashCode())) * 31) + this.reason.hashCode()) * 31) + (this.toolCall != null ? this.toolCall.hashCode() : 0);
    }

    public String toString() {
        return "SafeAutonomousResult(executed=" + this.executed + ", plan=" + this.plan + ", result=" + this.result + ", reason=" + this.reason + ", toolCall=" + this.toolCall + ")";
    }

    public SafeAutonomousResult(boolean executed, AutonomyActionPlan plan, ToolResult result, String reason, ToolCall toolCall) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.executed = executed;
        this.plan = plan;
        this.result = result;
        this.reason = reason;
        this.toolCall = toolCall;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SafeAutonomousResult(boolean z, AutonomyActionPlan autonomyActionPlan, ToolResult toolResult, String str, ToolCall toolCall, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ToolCall toolCall2;
        if ((i & 16) == 0) {
            toolCall2 = toolCall;
        } else {
            toolCall2 = null;
        }
        this(z, autonomyActionPlan, toolResult, str, toolCall2);
    }

    public final boolean getExecuted() {
        return this.executed;
    }

    public final AutonomyActionPlan getPlan() {
        return this.plan;
    }

    public final ToolResult getResult() {
        return this.result;
    }

    public final String getReason() {
        return this.reason;
    }

    public final ToolCall getToolCall() {
        return this.toolCall;
    }
}
