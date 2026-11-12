package com.jarvis.core.capability;

import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CapabilityExecutor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ,\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/capability/CapabilityExecutor;", "", "registry", "Lcom/jarvis/core/capability/CapabilityRegistry;", "toolExecutor", "Lcom/jarvis/core/common/ToolExecutor;", "verifier", "Lcom/jarvis/core/capability/CapabilityVerifier;", "<init>", "(Lcom/jarvis/core/capability/CapabilityRegistry;Lcom/jarvis/core/common/ToolExecutor;Lcom/jarvis/core/capability/CapabilityVerifier;)V", "execute", "Lcom/jarvis/core/capability/CapabilityResult;", "call", "Lcom/jarvis/core/capability/CapabilityCall;", "executeViaToolId", "requestId", "", "toolId", "arguments", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CapabilityExecutor {
    private final CapabilityRegistry registry;
    private final ToolExecutor toolExecutor;
    private final CapabilityVerifier verifier;

    public CapabilityExecutor(CapabilityRegistry registry, ToolExecutor toolExecutor, CapabilityVerifier verifier) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        Intrinsics.checkNotNullParameter(toolExecutor, "toolExecutor");
        Intrinsics.checkNotNullParameter(verifier, "verifier");
        this.registry = registry;
        this.toolExecutor = toolExecutor;
        this.verifier = verifier;
    }

    public /* synthetic */ CapabilityExecutor(CapabilityRegistry capabilityRegistry, ToolExecutor toolExecutor, DefaultCapabilityVerifier defaultCapabilityVerifier, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(capabilityRegistry, toolExecutor, (i & 4) != 0 ? new DefaultCapabilityVerifier() : defaultCapabilityVerifier);
    }

    public final CapabilityResult execute(CapabilityCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        CapabilityDescriptor cap = this.registry.find(call.getCapabilityId());
        if (cap == null) {
            return new CapabilityResult(call.getRequestId(), call.getCapabilityId(), ToolResultStatus.FAILED, "Capability не найдена: " + call.getCapabilityId(), null, 0L, false, null, null, 496, null);
        }
        Map $this$execute_u24lambda_u240 = MapsKt.createMapBuilder();
        $this$execute_u24lambda_u240.putAll(call.getArguments());
        String op = call.getOperation();
        if (op != null) {
            if (StringsKt.isBlank(op)) {
                op = null;
            }
            if (op != null && !$this$execute_u24lambda_u240.containsKey("action") && !$this$execute_u24lambda_u240.containsKey("operation")) {
                $this$execute_u24lambda_u240.put("action", op);
            }
        }
        Map args = MapsKt.build($this$execute_u24lambda_u240);
        ToolCall toolCall = new ToolCall(null, call.getRequestId(), cap.getBackingToolId(), args, 1, null);
        ToolResult toolResult = this.toolExecutor.execute(toolCall);
        VerificationOutcome verification = this.verifier.verify(cap, call, toolResult);
        return new CapabilityResult(call.getRequestId(), cap.getId(), toolResult.getStatus(), toolResult.getMessage(), MapsKt.plus(toolResult.getData(), MapsKt.mapOf(TuplesKt.to("capabilityId", cap.getId()), TuplesKt.to("backingToolId", cap.getBackingToolId()))), toolResult.getDurationMs(), verification.getOk(), verification.getNotes(), toolResult);
    }

    public final CapabilityResult executeViaToolId(String requestId, String toolId, Map<String, ? extends Object> arguments) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        CapabilityDescriptor cap = this.registry.findByToolId(toolId);
        if (cap == null) {
            return null;
        }
        return execute(new CapabilityCall(null, requestId, cap.getId(), null, arguments, 9, null));
    }
}
