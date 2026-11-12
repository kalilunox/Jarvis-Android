package com.jarvis.asr;

import android.util.Log;
import com.k2fsa.sherpa.onnx.FeatureConfig;
import com.k2fsa.sherpa.onnx.KeywordSpotter;
import com.k2fsa.sherpa.onnx.KeywordSpotterConfig;
import com.k2fsa.sherpa.onnx.OnlineModelConfig;
import com.k2fsa.sherpa.onnx.OnlineStream;
import com.k2fsa.sherpa.onnx.OnlineTransducerModelConfig;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: SherpaKeywordSpotter.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001c2\u00060\u0001j\u0002`\u0002:\u0001\u001cB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0018\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/jarvis/asr/SherpaKeywordSpotter;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "modelDir", "", "keywordsFile", "Ljava/io/File;", "spec", "Lcom/jarvis/asr/AsrModelSpec;", "<init>", "(Ljava/lang/String;Ljava/io/File;Lcom/jarvis/asr/AsrModelSpec;)V", "spotter", "Lcom/k2fsa/sherpa/onnx/KeywordSpotter;", "stream", "Lcom/k2fsa/sherpa/onnx/OnlineStream;", "busyNs", "", "fedSamples", "process", "", "samples", "", "reset", "", "close", "logLoad", "", "ns", "Companion", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SherpaKeywordSpotter implements AutoCloseable {
    private static final int FEATURE_DIM = 80;
    private static final float GAIN = 10.0f;
    private static final float KEYWORDS_SCORE = 2.0f;
    private static final float KEYWORDS_THRESHOLD = 0.1f;
    private static final int LOAD_LOG_SECONDS = 60;
    private static final int MAX_ACTIVE_PATHS = 4;
    private static final int NUM_TRAILING_BLANKS = 1;
    private static final int SAMPLE_RATE = 16000;
    private static final String TAG = "Jarvis.KWS2";
    private long busyNs;
    private long fedSamples;
    private final KeywordSpotter spotter;
    private final OnlineStream stream;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEYWORDS = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{"▁д жа р ви с @джарвис", "▁д жа р в е с @джарвес", "▁д ж е р ви с @джервис", "▁д жа р ви з @джарвиз", "▁ж а р ви с @жарвис"}), StringUtils.LF, null, StringUtils.LF, 0, null, null, 58, null);

    public SherpaKeywordSpotter(String modelDir, File keywordsFile, AsrModelSpec spec) {
        Intrinsics.checkNotNullParameter(modelDir, "modelDir");
        Intrinsics.checkNotNullParameter(keywordsFile, "keywordsFile");
        Intrinsics.checkNotNullParameter(spec, "spec");
        FilesKt.writeText$default(keywordsFile, KEYWORDS, null, 2, null);
        FeatureConfig featureConfig = new FeatureConfig(16000, 80, 0.0f, 4, null);
        OnlineModelConfig onlineModelConfig = new OnlineModelConfig(new OnlineTransducerModelConfig(modelDir + "/" + spec.getEncoderFile(), modelDir + "/" + spec.getDecoderFile(), modelDir + "/" + spec.getJoinerFile(), null, 8, null), null, null, null, null, modelDir + "/" + spec.getTokensFile(), 1, false, null, spec.getModelType(), null, null, 3486, null);
        String absolutePath = keywordsFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        this.spotter = new KeywordSpotter(null, new KeywordSpotterConfig(featureConfig, onlineModelConfig, 4, absolutePath, KEYWORDS_SCORE, 0.1f, 1), 1, null);
        this.stream = this.spotter.createStream("");
    }

    public /* synthetic */ SherpaKeywordSpotter(String str, File file, AsrModelSpec asrModelSpec, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, file, (i & 4) != 0 ? AsrModelSpec.INSTANCE.getRU_STREAMING() : asrModelSpec);
    }

    public final boolean process(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        long started = System.nanoTime();
        this.stream.acceptWaveform(INSTANCE.amplified$asr_engine_debug(samples), 16000);
        boolean hit = false;
        while (this.spotter.isReady(this.stream)) {
            this.spotter.decode(this.stream);
            if (this.spotter.getResult(this.stream).getKeyword().length() > 0) {
                hit = true;
                this.spotter.reset(this.stream);
            }
        }
        logLoad(samples.length, System.nanoTime() - started);
        return hit;
    }

    public final void reset() {
        this.spotter.reset(this.stream);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.stream.release();
        this.spotter.release();
    }

    private final void logLoad(int samples, long ns) {
        this.busyNs += ns;
        this.fedSamples += (long) samples;
        if (this.fedSamples < 960000) {
            return;
        }
        double rtf = (this.busyNs / 1.0E9d) / (this.fedSamples / ((double) 16000));
        String str = String.format("%.3f", Arrays.copyOf(new Object[]{Double.valueOf(rtf)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        Log.i(TAG, "load rtf=" + str + " seconds=" + (this.fedSamples / ((long) 16000)));
        this.busyNs = 0L;
        this.fedSamples = 0L;
    }

    /* JADX INFO: compiled from: SherpaKeywordSpotter.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/jarvis/asr/SherpaKeywordSpotter$Companion;", "", "<init>", "()V", "TAG", "", "SAMPLE_RATE", "", "FEATURE_DIM", "GAIN", "", "MAX_ACTIVE_PATHS", "KEYWORDS_SCORE", "KEYWORDS_THRESHOLD", "NUM_TRAILING_BLANKS", "LOAD_LOG_SECONDS", "KEYWORDS", "getKEYWORDS$asr_engine_debug", "()Ljava/lang/String;", "amplified", "", "samples", "amplified$asr_engine_debug", "tryCreate", "Lcom/jarvis/asr/SherpaKeywordSpotter;", "modelDir", "keywordsFile", "Ljava/io/File;", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getKEYWORDS$asr_engine_debug() {
            return SherpaKeywordSpotter.KEYWORDS;
        }

        public final float[] amplified$asr_engine_debug(float[] samples) {
            Intrinsics.checkNotNullParameter(samples, "samples");
            int length = samples.length;
            float[] fArr = new float[length];
            for (int i = 0; i < length; i++) {
                fArr[i] = RangesKt.coerceIn(samples[i] * SherpaKeywordSpotter.GAIN, -1.0f, 1.0f);
            }
            return fArr;
        }

        public final SherpaKeywordSpotter tryCreate(String modelDir, File keywordsFile) {
            Intrinsics.checkNotNullParameter(modelDir, "modelDir");
            Intrinsics.checkNotNullParameter(keywordsFile, "keywordsFile");
            try {
                return new SherpaKeywordSpotter(modelDir, keywordsFile, null, 4, null);
            } catch (Throwable t) {
                Log.e(SherpaKeywordSpotter.TAG, "init failed: " + t.getMessage(), t);
                return null;
            }
        }
    }
}
