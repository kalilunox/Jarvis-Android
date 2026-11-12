package com.jarvis.asr;

import com.k2fsa.sherpa.onnx.EndpointConfig;
import com.k2fsa.sherpa.onnx.EndpointRule;
import com.k2fsa.sherpa.onnx.FeatureConfig;
import com.k2fsa.sherpa.onnx.OnlineModelConfig;
import com.k2fsa.sherpa.onnx.OnlineRecognizer;
import com.k2fsa.sherpa.onnx.OnlineRecognizerConfig;
import com.k2fsa.sherpa.onnx.OnlineStream;
import com.k2fsa.sherpa.onnx.OnlineTransducerModelConfig;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SherpaAsrSession.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/asr/SherpaAsrSession;", "Lcom/jarvis/asr/AsrSession;", "modelDir", "", "spec", "Lcom/jarvis/asr/AsrModelSpec;", "<init>", "(Ljava/lang/String;Lcom/jarvis/asr/AsrModelSpec;)V", "lock", "", "recognizer", "Lcom/k2fsa/sherpa/onnx/OnlineRecognizer;", "stream", "Lcom/k2fsa/sherpa/onnx/OnlineStream;", "released", "", "processChunk", "Lcom/jarvis/asr/AsrChunkResult;", "samples", "", "reset", "", "release", "Companion", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SherpaAsrSession implements AsrSession {
    public static final int FEATURE_DIM = 80;
    public static final String MODEL_DIR = "sherpa-onnx-streaming-zipformer-small-ru-vosk-int8-2025-08-16";
    public static final int SAMPLE_RATE = 16000;
    private final Object lock;
    private final OnlineRecognizer recognizer;
    private boolean released;
    private final AsrModelSpec spec;
    private OnlineStream stream;

    public SherpaAsrSession(String modelDir, AsrModelSpec spec) {
        Intrinsics.checkNotNullParameter(modelDir, "modelDir");
        Intrinsics.checkNotNullParameter(spec, "spec");
        this.spec = spec;
        this.lock = new Object();
        if (!new File(modelDir).isDirectory()) {
            throw new IllegalArgumentException(("Model dir not found: " + modelDir).toString());
        }
        OnlineRecognizerConfig config = new OnlineRecognizerConfig(new FeatureConfig(16000, 80, 0.0f, 4, null), new OnlineModelConfig(new OnlineTransducerModelConfig(modelDir + "/" + this.spec.getEncoderFile(), modelDir + "/" + this.spec.getDecoderFile(), modelDir + "/" + this.spec.getJoinerFile(), null, 8, null), null, null, null, null, modelDir + "/" + this.spec.getTokensFile(), 0, false, null, this.spec.getModelType(), null, null, 3550, null), null, null, null, new EndpointConfig(new EndpointRule(false, 2.0f, 0.0f), new EndpointRule(true, 1.6f, 0.0f), new EndpointRule(false, 0.0f, 25.0f)), true, "greedy_search", 4, null, 0.0f, null, null, 0.0f, 15900, null);
        this.recognizer = new OnlineRecognizer(null, config, 1, null);
        this.stream = OnlineRecognizer.createStream$default(this.recognizer, null, 1, null);
    }

    public /* synthetic */ SherpaAsrSession(String str, AsrModelSpec asrModelSpec, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? AsrModelSpec.INSTANCE.getRU_STREAMING() : asrModelSpec);
    }

    @Override // com.jarvis.asr.AsrSession
    public AsrChunkResult processChunk(float[] samples) {
        AsrChunkResult asrChunkResult;
        Intrinsics.checkNotNullParameter(samples, "samples");
        synchronized (this.lock) {
            if (this.released) {
                asrChunkResult = new AsrChunkResult("", false);
            } else {
                SherpaAsrSession $this$processChunk_u24lambda_u240_u240 = this;
                $this$processChunk_u24lambda_u240_u240.stream.acceptWaveform(samples, 16000);
                while ($this$processChunk_u24lambda_u240_u240.recognizer.isReady($this$processChunk_u24lambda_u240_u240.stream)) {
                    $this$processChunk_u24lambda_u240_u240.recognizer.decode($this$processChunk_u24lambda_u240_u240.stream);
                }
                boolean isEndpoint = $this$processChunk_u24lambda_u240_u240.recognizer.isEndpoint($this$processChunk_u24lambda_u240_u240.stream);
                String text = $this$processChunk_u24lambda_u240_u240.recognizer.getResult($this$processChunk_u24lambda_u240_u240.stream).getText();
                asrChunkResult = new AsrChunkResult(text, isEndpoint);
            }
        }
        return asrChunkResult;
    }

    @Override // com.jarvis.asr.AsrSession
    public void reset() {
        synchronized (this.lock) {
            if (!this.released) {
                this.recognizer.reset(this.stream);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.jarvis.asr.AsrSession
    public void release() {
        synchronized (this.lock) {
            if (this.released) {
                return;
            }
            this.released = true;
            this.stream.release();
            this.recognizer.release();
            Unit unit = Unit.INSTANCE;
        }
    }
}
