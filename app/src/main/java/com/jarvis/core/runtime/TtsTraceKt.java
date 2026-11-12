package com.jarvis.core.runtime;

import com.jarvis.core.voice.TtsTelemetry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TtsTrace.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"applyTts", "", "Lcom/jarvis/core/runtime/RequestTrace;", "t", "Lcom/jarvis/core/voice/TtsTelemetry;", "core_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class TtsTraceKt {
    public static final void applyTts(RequestTrace $this$applyTts, TtsTelemetry t) {
        Intrinsics.checkNotNullParameter($this$applyTts, "<this>");
        Intrinsics.checkNotNullParameter(t, "t");
        $this$applyTts.setTtsProvider(t.getTtsProvider());
        $this$applyTts.setTtsModel(t.getTtsModel());
        $this$applyTts.setTtsVoice(t.getTtsVoice());
        $this$applyTts.setTtsRoute(t.getTtsRoute());
        $this$applyTts.setTtsRouteReason(t.getTtsRouteReason());
        $this$applyTts.setTtsFallback(Boolean.valueOf(t.getTtsFallback()));
        $this$applyTts.setTtsStreaming(Boolean.valueOf(t.getTtsStreaming()));
        $this$applyTts.setTtsSessionId(t.getTtsSessionId());
        $this$applyTts.setTtsSetupMs(t.getTtsSetupMs());
        $this$applyTts.setTtsStartupMs(t.getTtsSetupMs());
        $this$applyTts.setTtsFirstAudioMs(t.getTtsFirstAudioMs());
        $this$applyTts.setTtsFirstPlaybackMs(t.getTtsFirstPlaybackMs());
        $this$applyTts.setTtsSynthesisMs(t.getTtsSynthesisMs());
        $this$applyTts.setTtsPlaybackMs(t.getTtsPlaybackMs());
        $this$applyTts.setTtsTotalMs(t.getTtsTotalMs());
        $this$applyTts.setTtsQueueDepth(t.getTtsQueueDepth());
        $this$applyTts.setTtsCancelled(Boolean.valueOf(t.getTtsCancelled()));
        $this$applyTts.setTtsErrorCategory(t.getTtsErrorCategory());
        if (t.getTtsFallback()) {
            $this$applyTts.setFallback(true);
        }
    }
}
