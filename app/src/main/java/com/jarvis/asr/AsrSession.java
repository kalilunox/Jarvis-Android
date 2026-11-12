package com.jarvis.asr;

import kotlin.Metadata;

/* JADX INFO: compiled from: AsrSession.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/asr/AsrSession;", "", "processChunk", "Lcom/jarvis/asr/AsrChunkResult;", "samples", "", "reset", "", "release", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AsrSession {
    AsrChunkResult processChunk(float[] samples);

    void release();

    void reset();
}
