package com.jarvis.core.capability;

import android.util.Log;
import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.ToolRegistry;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityAwareToolExecutor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/capability/CapabilityAwareToolExecutor;", "Lcom/jarvis/core/common/ToolExecutor;", "inner", "capabilityRegistry", "Lcom/jarvis/core/capability/CapabilityRegistry;", "verifier", "Lcom/jarvis/core/capability/CapabilityVerifier;", "<init>", "(Lcom/jarvis/core/common/ToolExecutor;Lcom/jarvis/core/capability/CapabilityRegistry;Lcom/jarvis/core/capability/CapabilityVerifier;)V", "registry", "Lcom/jarvis/core/common/ToolRegistry;", "getRegistry", "()Lcom/jarvis/core/common/ToolRegistry;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CapabilityAwareToolExecutor implements ToolExecutor {
    private static final String TAG = "Jarvis.Capability";
    private final CapabilityRegistry capabilityRegistry;
    private final ToolExecutor inner;
    private final CapabilityVerifier verifier;

    public CapabilityAwareToolExecutor(ToolExecutor inner, CapabilityRegistry capabilityRegistry, CapabilityVerifier verifier) {
        Intrinsics.checkNotNullParameter(inner, "inner");
        Intrinsics.checkNotNullParameter(capabilityRegistry, "capabilityRegistry");
        Intrinsics.checkNotNullParameter(verifier, "verifier");
        this.inner = inner;
        this.capabilityRegistry = capabilityRegistry;
        this.verifier = verifier;
    }

    public /* synthetic */ CapabilityAwareToolExecutor(ToolExecutor toolExecutor, CapabilityRegistry capabilityRegistry, DefaultCapabilityVerifier defaultCapabilityVerifier, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(toolExecutor, capabilityRegistry, (i & 4) != 0 ? new DefaultCapabilityVerifier() : defaultCapabilityVerifier);
    }

    @Override // com.jarvis.core.common.ToolExecutor
    public ToolRegistry getRegistry() {
        return this.inner.getRegistry();
    }

    @Override // com.jarvis.core.common.ToolExecutor
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        ToolResult result = this.inner.execute(call);
        CapabilityDescriptor cap = this.capabilityRegistry.findByToolId(call.getToolId());
        if (cap == null) {
            return result;
        }
        VerificationOutcome outcome = this.verifier.verify(cap, new CapabilityCall(null, call.getRequestId(), cap.getId(), null, call.getArguments(), 9, null), result);
        try {
            Result.Companion companion = Result.INSTANCE;
            CapabilityAwareToolExecutor capabilityAwareToolExecutor = this;
            Result.m392constructorimpl(Integer.valueOf(Log.i(TAG, "cap=" + cap.getId() + " tool=" + call.getToolId() + " status=" + result.getStatus() + " verified=" + outcome.getOk() + " notes=" + CollectionsKt.take(outcome.getNotes(), 3))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return ToolResult.copy$default(result, null, null, null, null, MapsKt.plus(result.getData(), MapsKt.mapOf(TuplesKt.to("capabilityId", cap.getId()), TuplesKt.to("verified", Boolean.valueOf(outcome.getOk())), TuplesKt.to("verificationNotes", CollectionsKt.joinToString$default(outcome.getNotes(), "|", null, null, 0, null, null, 62, null)))), 0L, 47, null);
    }
}
