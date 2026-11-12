package com.jarvis.core.capability;

import com.jarvis.core.common.model.ToolResult;
import kotlin.Metadata;

/* JADX INFO: compiled from: CapabilityExecutor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/capability/CapabilityVerifier;", "", "verify", "Lcom/jarvis/core/capability/VerificationOutcome;", "capability", "Lcom/jarvis/core/capability/CapabilityDescriptor;", "call", "Lcom/jarvis/core/capability/CapabilityCall;", "toolResult", "Lcom/jarvis/core/common/model/ToolResult;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CapabilityVerifier {
    VerificationOutcome verify(CapabilityDescriptor capability, CapabilityCall call, ToolResult toolResult);
}
