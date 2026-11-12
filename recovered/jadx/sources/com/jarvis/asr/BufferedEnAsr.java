package com.jarvis.asr;

import android.util.Log;
import com.jarvis.audio.AudioCapture;
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
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BufferedEnAsr.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00192\u00060\u0001j\u0002`\u0002:\u0001\u0019B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/asr/BufferedEnAsr;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "modelDir", "", "spec", "Lcom/jarvis/asr/AsrModelSpec;", "<init>", "(Ljava/lang/String;Lcom/jarvis/asr/AsrModelSpec;)V", "lock", "", "recognizer", "Lcom/k2fsa/sherpa/onnx/OnlineRecognizer;", "released", "", "decode", "samples", "", "feed", "", "stream", "Lcom/k2fsa/sherpa/onnx/OnlineStream;", "chunk", "maybeBoost", "close", "Companion", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BufferedEnAsr implements AutoCloseable {
    private static final int EARLY_EXIT_AFTER_SAMPLES = 3200;
    public static final int FEATURE_DIM = 80;
    private static final int LEAD_PAD_SAMPLES = 4000;
    public static final int SAMPLE_RATE = 16000;
    private static final String TAG = "Jarvis.Asr";
    private static final int TRAIL_PAD_SAMPLES = 16000;
    private final Object lock;
    private final OnlineRecognizer recognizer;
    private boolean released;
    private final AsrModelSpec spec;

    public BufferedEnAsr(String modelDir, AsrModelSpec spec) {
        Intrinsics.checkNotNullParameter(modelDir, "modelDir");
        Intrinsics.checkNotNullParameter(spec, "spec");
        this.spec = spec;
        this.lock = new Object();
        if (!new File(modelDir).isDirectory()) {
            throw new IllegalArgumentException(("EN model dir missing: " + modelDir).toString());
        }
        for (String name : this.spec.requiredFiles()) {
            if (!new File(modelDir, name).isFile()) {
                throw new IllegalArgumentException(("EN model file missing: " + name).toString());
            }
        }
        OnlineRecognizerConfig config = new OnlineRecognizerConfig(new FeatureConfig(16000, 80, 0.0f, 4, null), new OnlineModelConfig(new OnlineTransducerModelConfig(modelDir + "/" + this.spec.getEncoderFile(), modelDir + "/" + this.spec.getDecoderFile(), modelDir + "/" + this.spec.getJoinerFile(), null, 8, null), null, null, null, null, modelDir + "/" + this.spec.getTokensFile(), 2, false, null, this.spec.getModelType(), null, null, 3486, null), null, null, null, new EndpointConfig(new EndpointRule(false, 2.4f, 0.0f), new EndpointRule(false, 1.2f, 0.0f), new EndpointRule(false, 0.0f, 20.0f)), false, "greedy_search", 4, null, 0.0f, null, null, 0.0f, 15900, null);
        this.recognizer = new OnlineRecognizer(null, config, 1, null);
        Log.i(TAG, "buffered EN ASR ready (" + this.spec.getDirName() + ")");
    }

    public /* synthetic */ BufferedEnAsr(String str, AsrModelSpec asrModelSpec, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? AsrModelSpec.INSTANCE.getEN_STREAMING() : asrModelSpec);
    }

    public final String decode(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        synchronized (this.lock) {
            if (!this.released) {
                if (!(samples.length == 0)) {
                    OnlineStream stream = OnlineRecognizer.createStream$default(this.recognizer, null, 1, null);
                    try {
                        float[] pcm = maybeBoost(samples);
                        feed(stream, new float[4000]);
                        int off = 0;
                        while (off < pcm.length) {
                            int end = Math.min(off + AudioCapture.CHUNK_SAMPLES, pcm.length);
                            feed(stream, ArraysKt.copyOfRange(pcm, off, end));
                            off = end;
                        }
                        String best = "";
                        int trailed = 0;
                        while (trailed < 16000) {
                            int n = Math.min(AudioCapture.CHUNK_SAMPLES, 16000 - trailed);
                            feed(stream, new float[n]);
                            trailed += n;
                            String t = StringsKt.trim((CharSequence) this.recognizer.getResult(stream).getText()).toString();
                            if (!StringsKt.isBlank(t)) {
                                best = t;
                                if (trailed >= EARLY_EXIT_AFTER_SAMPLES) {
                                    break;
                                }
                            }
                        }
                        if (StringsKt.isBlank(best)) {
                            Log.w(TAG, "EN decode empty (pcm=" + samples.length + ")");
                        } else {
                            Log.i(TAG, "EN decode=\"" + best + "\" (pcm=" + samples.length + ")");
                        }
                        stream.release();
                        return best;
                    } catch (Throwable th) {
                        stream.release();
                        throw th;
                    }
                }
            }
            return "";
        }
    }

    private final void feed(OnlineStream stream, float[] chunk) {
        if (chunk.length == 0) {
            return;
        }
        stream.acceptWaveform(chunk, 16000);
        while (this.recognizer.isReady(stream)) {
            this.recognizer.decode(stream);
        }
    }

    private final float[] maybeBoost(float[] samples) {
        double sum = 0.0d;
        for (float s : samples) {
            sum += (double) (s * s);
        }
        float rms = (float) Math.sqrt(sum / ((double) samples.length));
        if (rms < 0.01f || rms > 0.08f) {
            return samples;
        }
        float gain = RangesKt.coerceIn(0.12f / rms, 1.5f, 8.0f);
        int length = samples.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = RangesKt.coerceIn(samples[i] * gain, -0.99f, 0.99f);
        }
        return fArr;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.lock) {
            if (this.released) {
                return;
            }
            this.released = true;
            this.recognizer.release();
            Unit unit = Unit.INSTANCE;
        }
    }
}
