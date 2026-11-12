package com.jarvis.wakeword;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OwwWakeSpotter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 ;2\u00060\u0001j\u0002`\u0002:\u0001;B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\bJ\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020(J\u0010\u0010.\u001a\u00020(2\b\b\u0002\u0010/\u001a\u00020\u0018J\u0006\u00100\u001a\u00020(J\u0010\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\bH\u0002J!\u00103\u001a\u00020\u00062\u0012\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0505H\u0002¢\u0006\u0002\u00106J\u0010\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020(H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020\u0012X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006<"}, d2 = {"Lcom/jarvis/wakeword/OwwWakeSpotter;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "context", "Landroid/content/Context;", "threshold", "", "patience", "", "<init>", "(Landroid/content/Context;FI)V", "env", "Lai/onnxruntime/OrtEnvironment;", "melSession", "Lai/onnxruntime/OrtSession;", "embSession", "clfSession", "clfInput", "", "features", "Lcom/jarvis/wakeword/OwwAudioFeatures;", "framesSinceStart", "aboveCount", "lastHitAtMs", "", "lastScoreLogAtMs", "peakScore", "lastScore", "armed", "", "armAfterMs", "engine", "getEngine", "()Ljava/lang/String;", "lastScoreValue", "getLastScoreValue", "()F", "isArmed", "()Z", "applySensitivity", "", "level", "process", "samples", "", "disarm", "armForNextWake", "cooldownMs", "reset", "predictScore", "prepared", "runClassifier", "feats", "", "([[[F)F", "readScore", "value", "", "close", "Companion", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OwwWakeSpotter implements AutoCloseable {
    private static final String ASSET_DIR = "openwakeword";
    private static final String CLF_MODEL = "djarvis.onnx";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long DEDUP_MS = 900;
    private static final int DEFAULT_PATIENCE = 1;
    public static final float DEFAULT_THRESHOLD = 0.05f;
    private static final String EMB_MODEL = "embedding_model.onnx";
    private static final String MEL_MODEL = "melspectrogram.onnx";
    public static final long POST_SESSION_COOLDOWN_MS = 700;
    private static final long SCORE_LOG_MS = 600;
    private static final String TAG = "Jarvis.KWS";
    private static final int WARMUP_FRAMES = 2;
    private int aboveCount;
    private volatile long armAfterMs;
    private volatile boolean armed;
    private final String clfInput;
    private final OrtSession clfSession;
    private final OrtSession embSession;
    private final String engine;
    private final OrtEnvironment env;
    private final OwwAudioFeatures features;
    private int framesSinceStart;
    private long lastHitAtMs;
    private float lastScore;
    private long lastScoreLogAtMs;
    private final OrtSession melSession;
    private volatile int patience;
    private float peakScore;
    private volatile float threshold;

    public OwwWakeSpotter(Context context, float threshold, int patience) throws OrtException {
        Intrinsics.checkNotNullParameter(context, "context");
        this.threshold = threshold;
        this.patience = patience;
        this.armed = true;
        this.engine = "openwakeword-djarvis";
        try {
            Result.Companion companion = Result.INSTANCE;
            OwwWakeSpotter owwWakeSpotter = this;
            System.loadLibrary("onnxruntime_oww");
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            OwwWakeSpotter owwWakeSpotter2 = this;
            System.loadLibrary("onnxruntime4j_jni");
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        OrtEnvironment environment = OrtEnvironment.getEnvironment();
        Intrinsics.checkNotNullExpressionValue(environment, "getEnvironment(...)");
        this.env = environment;
        AssetManager assets = context.getApplicationContext().getAssets();
        OrtSession.SessionOptions $this$_init__u24lambda_u242 = new OrtSession.SessionOptions();
        $this$_init__u24lambda_u242.setIntraOpNumThreads(1);
        $this$_init__u24lambda_u242.setInterOpNumThreads(1);
        $this$_init__u24lambda_u242.addConfigEntry("session.intra_op.allow_spinning", "0");
        this.melSession = _init_$load(assets, this, $this$_init__u24lambda_u242, MEL_MODEL);
        this.embSession = _init_$load(assets, this, $this$_init__u24lambda_u242, EMB_MODEL);
        this.clfSession = _init_$load(assets, this, $this$_init__u24lambda_u242, CLF_MODEL);
        Set<String> inputNames = this.clfSession.getInputNames();
        Intrinsics.checkNotNullExpressionValue(inputNames, "getInputNames(...)");
        Object objFirst = CollectionsKt.first(inputNames);
        Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
        this.clfInput = (String) objFirst;
        this.features = new OwwAudioFeatures(this.env, this.melSession, this.embSession);
        Log.i(TAG, "ready thr=" + this.threshold + " patience=" + this.patience + " in=" + this.clfSession.getInputInfo().keySet() + " feats=" + this.features.getFeatureCount());
    }

    public /* synthetic */ OwwWakeSpotter(Context context, float f, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? 0.05f : f, (i2 & 4) != 0 ? 1 : i);
    }

    public final String getEngine() {
        return this.engine;
    }

    /* JADX INFO: renamed from: getLastScoreValue, reason: from getter */
    public final float getLastScore() {
        return this.lastScore;
    }

    /* JADX INFO: renamed from: isArmed, reason: from getter */
    public final boolean getArmed() {
        return this.armed;
    }

    private static final OrtSession _init_$load(AssetManager assets, OwwWakeSpotter this$0, OrtSession.SessionOptions options, String name) throws IOException {
        InputStream inputStreamOpen = assets.open("openwakeword/" + name);
        try {
            InputStream it = inputStreamOpen;
            OrtEnvironment ortEnvironment = this$0.env;
            Intrinsics.checkNotNull(it);
            OrtSession ortSessionCreateSession = ortEnvironment.createSession(ByteStreamsKt.readBytes(it), options);
            CloseableKt.closeFinally(inputStreamOpen, null);
            Intrinsics.checkNotNullExpressionValue(ortSessionCreateSession, "use(...)");
            return ortSessionCreateSession;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStreamOpen, th);
                throw th2;
            }
        }
    }

    public final void applySensitivity(int level) {
        this.threshold = INSTANCE.thresholdFromLevel(level);
        this.patience = INSTANCE.patienceFromLevel(level);
        this.aboveCount = 0;
        Log.i(TAG, "sensitivity=" + level + " thr=" + this.threshold + " patience=" + this.patience);
    }

    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean, int] */
    public final boolean process(float[] samples) {
        ?? r1;
        Intrinsics.checkNotNullParameter(samples, "samples");
        if (samples.length == 0) {
            return false;
        }
        int length = samples.length;
        float[] pcm = new float[length];
        for (int i = 0; i < length; i++) {
            pcm[i] = samples[i] * 32768.0f;
        }
        int prepared = this.features.accept(pcm);
        if (prepared < 1280 || this.features.getFeatureCount() < 16) {
            return false;
        }
        this.framesSinceStart++;
        long now = System.currentTimeMillis();
        if (!this.armed || now < this.armAfterMs || this.framesSinceStart <= 2) {
            return false;
        }
        float score = predictScore(prepared);
        this.lastScore = score;
        this.peakScore = Math.max(this.peakScore, score);
        if (now - this.lastScoreLogAtMs >= SCORE_LOG_MS) {
            this.lastScoreLogAtMs = now;
            String str = String.format("%.3f", Arrays.copyOf(new Object[]{Float.valueOf(score)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            String str2 = String.format("%.3f", Arrays.copyOf(new Object[]{Float.valueOf(this.peakScore)}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            Log.i(TAG, "score=" + str + " peak=" + str2 + " thr=" + this.threshold + " armed=" + this.armed);
            this.peakScore = 0.0f;
        }
        if (score >= this.threshold) {
            this.aboveCount++;
            r1 = 0;
        } else {
            r1 = 0;
            this.aboveCount = 0;
        }
        if (this.aboveCount < this.patience || now - this.lastHitAtMs < DEDUP_MS) {
            return r1;
        }
        this.lastHitAtMs = now;
        this.aboveCount = r1;
        this.armed = r1;
        String str3 = String.format("%.3f", Arrays.copyOf(new Object[]{Float.valueOf(score)}, 1));
        Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
        Log.i(TAG, "HIT «джарвис» score=" + str3 + " thr=" + this.threshold + " → disarm");
        return true;
    }

    public final void disarm() {
        this.armed = false;
        this.aboveCount = 0;
    }

    public static /* synthetic */ void armForNextWake$default(OwwWakeSpotter owwWakeSpotter, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 700;
        }
        owwWakeSpotter.armForNextWake(j);
    }

    public final void armForNextWake(long cooldownMs) {
        this.features.reset();
        this.framesSinceStart = 0;
        this.aboveCount = 0;
        this.lastScore = 0.0f;
        this.peakScore = 0.0f;
        this.armAfterMs = System.currentTimeMillis() + RangesKt.coerceAtLeast(cooldownMs, 0L);
        this.armed = true;
        Log.i(TAG, "armed for next wake cooldown=" + cooldownMs + "ms thr=" + this.threshold);
    }

    public final void reset() {
        this.features.reset();
        this.framesSinceStart = 0;
        this.aboveCount = 0;
        this.lastScore = 0.0f;
        this.peakScore = 0.0f;
    }

    private final float predictScore(int prepared) {
        try {
            if (prepared <= 1280) {
                return runClassifier(this.features.getFeatures(16));
            }
            int steps = prepared / OwwAudioFeatures.CHUNK;
            float best = 0.0f;
            for (int i = steps - 1; -1 < i; i--) {
                float[][][] feats = this.features.getFeaturesAt(16, i);
                best = Math.max(best, runClassifier(feats));
            }
            return best;
        } catch (Exception e) {
            Log.w(TAG, "predict: " + e.getMessage());
            return 0.0f;
        }
    }

    private final float runClassifier(float[][][] feats) {
        OnnxTensor tensor = null;
        try {
            tensor = OnnxTensor.createTensor(this.env, feats);
            OrtSession.Result resultRun = this.clfSession.run(MapsKt.mapOf(TuplesKt.to(this.clfInput, tensor)));
            try {
                OrtSession.Result outs = resultRun;
                Object value = outs.get(0).getValue();
                Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                float score = readScore(value);
                AutoCloseableKt.closeFinally(resultRun, null);
                if (tensor != null) {
                    tensor.close();
                }
                return score;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(resultRun, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            if (tensor != null) {
                tensor.close();
            }
            throw th3;
        }
    }

    private final float readScore(Object value) {
        try {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.FloatArray>");
            return ((float[][]) value)[0][0];
        } catch (ClassCastException e) {
            try {
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Array<kotlin.FloatArray>>");
                return ((float[][][]) value)[0][0][0];
            } catch (ClassCastException e2) {
                if (value instanceof float[]) {
                    return ((float[]) value)[0];
                }
                if (value instanceof Float) {
                    return ((Number) value).floatValue();
                }
                Log.w(TAG, "clf shape " + value.getClass().getName());
                return 0.0f;
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        try {
            Result.Companion companion = Result.INSTANCE;
            OwwWakeSpotter $this$close_u24lambda_u240 = this;
            $this$close_u24lambda_u240.features.close();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            OwwWakeSpotter $this$close_u24lambda_u241 = this;
            $this$close_u24lambda_u241.clfSession.close();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        try {
            Result.Companion companion5 = Result.INSTANCE;
            OwwWakeSpotter $this$close_u24lambda_u242 = this;
            $this$close_u24lambda_u242.embSession.close();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        try {
            Result.Companion companion7 = Result.INSTANCE;
            OwwWakeSpotter $this$close_u24lambda_u243 = this;
            $this$close_u24lambda_u243.melSession.close();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th4) {
            Result.Companion companion8 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th4));
        }
    }

    /* JADX INFO: compiled from: OwwWakeSpotter.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/jarvis/wakeword/OwwWakeSpotter$Companion;", "", "<init>", "()V", "TAG", "", "ASSET_DIR", "MEL_MODEL", "EMB_MODEL", "CLF_MODEL", "DEFAULT_THRESHOLD", "", "DEFAULT_PATIENCE", "", "WARMUP_FRAMES", "DEDUP_MS", "", "SCORE_LOG_MS", "POST_SESSION_COOLDOWN_MS", "thresholdFromLevel", "level", "patienceFromLevel", "tryCreate", "Lcom/jarvis/wakeword/OwwWakeSpotter;", "context", "Landroid/content/Context;", "sensitivityLevel", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final float thresholdFromLevel(int level) {
            switch (level) {
                case 0:
                    return 0.1f;
                case 1:
                default:
                    return 0.05f;
                case 2:
                    return 0.03f;
            }
        }

        public final int patienceFromLevel(int level) {
            return 1;
        }

        public final OwwWakeSpotter tryCreate(Context context, int sensitivityLevel) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                return new OwwWakeSpotter(context, thresholdFromLevel(sensitivityLevel), patienceFromLevel(sensitivityLevel));
            } catch (Throwable t) {
                Log.e(OwwWakeSpotter.TAG, "init failed: " + t.getMessage(), t);
                return null;
            }
        }
    }
}
