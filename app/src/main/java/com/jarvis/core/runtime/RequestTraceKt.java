package com.jarvis.core.runtime;

import com.jarvis.core.aioutput.StructuredAiDiagnostics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RequestTrace.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"applyStructured", "", "Lcom/jarvis/core/runtime/RequestTrace;", "d", "Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "core_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class RequestTraceKt {
    public static final void applyStructured(RequestTrace $this$applyStructured, StructuredAiDiagnostics d) {
        Intrinsics.checkNotNullParameter($this$applyStructured, "<this>");
        Intrinsics.checkNotNullParameter(d, "d");
        $this$applyStructured.setStructured(Boolean.valueOf(d.getStructured()));
        $this$applyStructured.setStructuredSchemaVersion(d.getSchemaVersion());
        $this$applyStructured.setStructuredResponseType(d.getResponseType());
        $this$applyStructured.setStructuredParse(d.getParse().name());
        $this$applyStructured.setStructuredValidation(d.getValidation().name());
        $this$applyStructured.setStructuredRepairAttempted(Boolean.valueOf(d.getRepairAttempted()));
        $this$applyStructured.setStructuredRepairResult(d.getRepairResult());
        $this$applyStructured.setStructuredValidationReason(d.getValidationReason());
    }
}
