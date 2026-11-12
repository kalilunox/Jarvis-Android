package com.jarvis.core.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InteractionLatency.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"applyLatency", "", "Lcom/jarvis/core/runtime/RequestTrace;", "lat", "Lcom/jarvis/core/runtime/InteractionLatency;", "core_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class InteractionLatencyKt {
    public static final void applyLatency(RequestTrace $this$applyLatency, InteractionLatency lat) {
        Intrinsics.checkNotNullParameter($this$applyLatency, "<this>");
        Intrinsics.checkNotNullParameter(lat, "lat");
        $this$applyLatency.setE2eMs(lat.endToEndMs());
        $this$applyLatency.setFirstAckMs(lat.timeToFirstAcknowledgementMs());
        $this$applyLatency.setFirstUsefulMs(lat.timeToFirstUsefulResultMs());
        $this$applyLatency.setFirstAudioMs(lat.timeToFirstAudioMs());
        $this$applyLatency.setCompleteMs(lat.timeToCompletionMs());
        $this$applyLatency.setAsrMs(lat.asrMs());
        $this$applyLatency.setSemanticMs(lat.semanticMs());
        $this$applyLatency.setContextMs(lat.contextMs());
        $this$applyLatency.setRouteMs(lat.routeMs());
        $this$applyLatency.setAiWaitMs(lat.aiWaitMs());
        $this$applyLatency.setAiFirstByteMs(lat.aiFirstByteMs());
        $this$applyLatency.setAiTtftMs(lat.realTtftMs());
        $this$applyLatency.setAiTtftKind(lat.getTtftKind().name());
        $this$applyLatency.setAiCompletionMs(lat.aiCompletionMs());
        $this$applyLatency.setParseMs(lat.parseMs());
        $this$applyLatency.setPlannerMs(lat.plannerMs());
        $this$applyLatency.setToolMs(lat.toolMs());
        $this$applyLatency.setVerifyMs(lat.verifyMs());
        $this$applyLatency.setTtsStartupMs(lat.ttsStartupMs());
        $this$applyLatency.setTtsFirstAudioMs(lat.ttsFirstAudioFromAnchorMs());
        $this$applyLatency.setColdStart(Boolean.valueOf(lat.getColdStart()));
        $this$applyLatency.setWarmState(lat.getWarmState().name());
        $this$applyLatency.setCancelled(Boolean.valueOf(lat.getCancelled()));
        if (lat.getFallback()) {
            $this$applyLatency.setFallback(true);
        }
        $this$applyLatency.setStreamingUsed(Boolean.valueOf(lat.getStreamingUsed()));
        if ($this$applyLatency.getTotalLatencyMs() == 0) {
            Long lTimeToCompletionMs = lat.timeToCompletionMs();
            $this$applyLatency.setTotalLatencyMs((lTimeToCompletionMs == null && (lTimeToCompletionMs = lat.timeToFirstUsefulResultMs()) == null) ? 0L : lTimeToCompletionMs.longValue());
        }
    }
}
