package com.jarvis.core.runtime;

import com.jarvis.core.voice.AsrTelemetry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsrTrace.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"applyAsr", "", "Lcom/jarvis/core/runtime/RequestTrace;", "t", "Lcom/jarvis/core/voice/AsrTelemetry;", "core_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AsrTraceKt {
    public static final void applyAsr(RequestTrace $this$applyAsr, AsrTelemetry t) {
        Intrinsics.checkNotNullParameter($this$applyAsr, "<this>");
        Intrinsics.checkNotNullParameter(t, "t");
        $this$applyAsr.setAsrProvider(t.getAsrProvider());
        $this$applyAsr.setAsrModel(t.getAsrModel());
        $this$applyAsr.setAsrRoute(t.getAsrRoute());
        $this$applyAsr.setAsrRouteReason(t.getAsrRouteReason());
        $this$applyAsr.setAsrSetupMs(t.getAsrSetupMs());
        $this$applyAsr.setAsrFirstPartialMs(t.getAsrFirstPartialMs());
        $this$applyAsr.setAsrFinalizeMs(t.getAsrFinalizeMs());
        $this$applyAsr.setAsrTotalMs(t.getAsrTotalMs());
        $this$applyAsr.setAsrFallback(Boolean.valueOf(t.getAsrFallback()));
        $this$applyAsr.setAsrFallbackReason(t.getAsrFallbackReason());
        Long asrTotalMs = t.getAsrTotalMs();
        if (asrTotalMs == null) {
            asrTotalMs = t.getAsrFinalizeMs();
        }
        $this$applyAsr.setAsrMs(asrTotalMs);
        InteractionLatency lat = $this$applyAsr.getInteractionLatency();
        if (lat != null) {
            lat.setAsrStartAt(t.getAsrSessionStartAt());
            lat.setAsrPartialAt(t.getAsrFirstPartialAt());
            lat.setAsrFinalAt(t.getAsrFinalAt());
            if (t.getAsrFallback()) {
                lat.setFallback(true);
            }
        }
    }
}
