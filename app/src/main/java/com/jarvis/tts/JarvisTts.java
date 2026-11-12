package com.jarvis.tts;

import com.jarvis.app.VoiceService;
import com.k2fsa.sherpa.onnx.OfflineTts;
import com.k2fsa.sherpa.onnx.OfflineTtsConfig;
import com.k2fsa.sherpa.onnx.OfflineTtsModelConfig;
import com.k2fsa.sherpa.onnx.OfflineTtsVitsModelConfig;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisTts.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b0\u0013J\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/jarvis/tts/JarvisTts;", "", "config", "Lcom/jarvis/tts/JarvisTtsConfig;", "<init>", "(Lcom/jarvis/tts/JarvisTtsConfig;)V", "tts", "Lcom/k2fsa/sherpa/onnx/OfflineTts;", "speed", "", "sampleRate", "", "getSampleRate", "()I", "synthesize", "", VoiceService.EXTRA_TEXT, "", "callback", "Lkotlin/Function1;", "release", "", "tts-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisTts {
    private final float speed;
    private final OfflineTts tts;

    public JarvisTts(JarvisTtsConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.speed = config.getSpeed();
        String absolutePath = new File(config.getModelDir(), config.getModelOnnx()).getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        String absolutePath2 = new File(config.getModelDir(), "tokens.txt").getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
        String absolutePath3 = new File(config.getModelDir(), "espeak-ng-data").getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath3, "getAbsolutePath(...)");
        OfflineTtsVitsModelConfig vits = new OfflineTtsVitsModelConfig(absolutePath, null, absolutePath2, absolutePath3, null, 0.0f, 0.0f, 0.0f, 242, null);
        OfflineTtsModelConfig modelConfig = new OfflineTtsModelConfig(vits, null, null, null, null, null, null, config.getNumThreads(), false, null, 638, null);
        OfflineTtsConfig ttsConfig = new OfflineTtsConfig(modelConfig, null, null, 0, 0.0f, 30, null);
        this.tts = new OfflineTts(null, ttsConfig, 1, null);
    }

    public final int getSampleRate() {
        return this.tts.sampleRate();
    }

    public final float[] synthesize(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return this.tts.generate(text, 0, this.speed).getSamples();
    }

    public final float[] synthesize(String text, Function1<? super float[], Integer> callback) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(callback, "callback");
        return this.tts.generateWithCallback(text, 0, this.speed, callback).getSamples();
    }

    public final void release() {
        this.tts.release();
    }
}
