package com.jarvis.asr;

import com.k2fsa.sherpa.onnx.OnlineStream;
import com.k2fsa.sherpa.onnx.SpeakerEmbeddingExtractor;
import com.k2fsa.sherpa.onnx.SpeakerEmbeddingExtractorConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpeakerEmbedder.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00060\u0001j\u0002`\u0002:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/asr/SpeakerEmbedder;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "modelPath", "", "<init>", "(Ljava/lang/String;)V", "extractor", "Lcom/k2fsa/sherpa/onnx/SpeakerEmbeddingExtractor;", "embed", "", "pcm", "close", "", "Companion", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SpeakerEmbedder implements AutoCloseable {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int SAMPLE_RATE = 16000;
    private final SpeakerEmbeddingExtractor extractor;

    public SpeakerEmbedder(String modelPath) {
        Intrinsics.checkNotNullParameter(modelPath, "modelPath");
        this.extractor = new SpeakerEmbeddingExtractor(null, new SpeakerEmbeddingExtractorConfig(modelPath, 2, false, null, 12, null), 1, null);
    }

    public final float[] embed(float[] pcm) {
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        OnlineStream stream = this.extractor.createStream();
        try {
            stream.acceptWaveform(pcm, 16000);
            stream.inputFinished();
            return this.extractor.isReady(stream) ? this.extractor.compute(stream) : null;
        } finally {
            stream.release();
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.extractor.release();
    }

    /* JADX INFO: compiled from: SpeakerEmbedder.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/asr/SpeakerEmbedder$Companion;", "", "<init>", "()V", "SAMPLE_RATE", "", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
