package com.jarvis.core.capability;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityRegistry.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toSummary", "Lcom/jarvis/core/capability/CapabilitySummary;", "Lcom/jarvis/core/capability/CapabilityDescriptor;", "core_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class CapabilityRegistryKt {
    public static final CapabilitySummary toSummary(CapabilityDescriptor $this$toSummary) {
        Intrinsics.checkNotNullParameter($this$toSummary, "<this>");
        return new CapabilitySummary($this$toSummary.getId(), $this$toSummary.getTitle(), $this$toSummary.getDescription(), $this$toSummary.getOperations(), $this$toSummary.getRiskLevel(), $this$toSummary.getExamples(), $this$toSummary.getBackingToolId());
    }
}
