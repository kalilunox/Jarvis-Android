package com.jarvis.wakeword;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;
import android.util.Log;
import androidx.core.app.NotificationManagerCompat;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OwwAudioFeatures.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\b\b\u0000\u0018\u0000 52\u00060\u0001j\u0002`\u0002:\u00015B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u000eJ!\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00130\u00132\b\b\u0002\u0010\u001b\u001a\u00020\u0011¢\u0006\u0002\u0010\u001cJ'\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00130\u00132\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0002\u0010\u001fJ\b\u0010#\u001a\u00020\u000eH\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u000eH\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0011H\u0002J\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010)\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010*J\u001b\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010)\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010*J\u001b\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010-\u001a\u00020.H\u0002¢\u0006\u0002\u0010/J'\u00100\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0012\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00130\u0013H\u0002¢\u0006\u0002\u00102J\u001b\u00103\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010-\u001a\u00020.H\u0002¢\u0006\u0002\u0010/J\b\u00104\u001a\u00020\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0011\u0010 \u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u00066"}, d2 = {"Lcom/jarvis/wakeword/OwwAudioFeatures;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "env", "Lai/onnxruntime/OrtEnvironment;", "melSession", "Lai/onnxruntime/OrtSession;", "embSession", "<init>", "(Lai/onnxruntime/OrtEnvironment;Lai/onnxruntime/OrtSession;Lai/onnxruntime/OrtSession;)V", "melInput", "", "embInput", "rawBuffer", "", "rawRemainder", "accumulatedSamples", "", "melBuffer", "", "[[F", "featureBuffer", "reset", "", "accept", "pcmInt16Scale", "getFeatures", "nFrames", "(I)[[[F", "getFeaturesAt", "startFromEnd", "(II)[[[F", "featureCount", "getFeatureCount", "()I", "warmNoise", "bufferRaw", "data", "streamingMel", "nSamples", "embeddingsFromPcm", "pcm", "([F)[[F", "computeMel", "squeezeMel", "value", "", "(Ljava/lang/Object;)[[F", "embedBatch", "windows", "([[[F)[[F", "parseEmbeddings", "close", "Companion", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OwwAudioFeatures implements AutoCloseable {
    public static final int CHUNK = 1280;
    public static final int EMB_DIM = 96;
    public static final int FEATURE_FRAMES = 16;
    private static final int FEATURE_MAX = 120;
    public static final int MEL_BINS = 32;
    private static final int MEL_MAX = 970;
    public static final int MEL_WINDOW = 76;
    private static final int RAW_MAX = 16000;
    public static final int SAMPLE_RATE = 16000;
    private static final String TAG = "Jarvis.OWW.Features";
    private int accumulatedSamples;
    private final String embInput;
    private final OrtSession embSession;
    private final OrtEnvironment env;
    private float[][] featureBuffer;
    private float[][] melBuffer;
    private final String melInput;
    private final OrtSession melSession;
    private float[] rawBuffer;
    private float[] rawRemainder;

    public OwwAudioFeatures(OrtEnvironment env, OrtSession melSession, OrtSession embSession) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(melSession, "melSession");
        Intrinsics.checkNotNullParameter(embSession, "embSession");
        this.env = env;
        this.melSession = melSession;
        this.embSession = embSession;
        Set<String> inputNames = this.melSession.getInputNames();
        Intrinsics.checkNotNullExpressionValue(inputNames, "getInputNames(...)");
        Object objFirst = CollectionsKt.first(inputNames);
        Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
        this.melInput = (String) objFirst;
        Set<String> inputNames2 = this.embSession.getInputNames();
        Intrinsics.checkNotNullExpressionValue(inputNames2, "getInputNames(...)");
        Object objFirst2 = CollectionsKt.first(inputNames2);
        Intrinsics.checkNotNullExpressionValue(objFirst2, "first(...)");
        this.embInput = (String) objFirst2;
        this.rawBuffer = new float[0];
        this.rawRemainder = new float[0];
        float[][] fArr = new float[76][];
        for (int i = 0; i < 76; i++) {
            float[] fArr2 = new float[32];
            for (int i2 = 0; i2 < 32; i2++) {
                fArr2[i2] = 1.0f;
            }
            fArr[i] = fArr2;
        }
        this.melBuffer = fArr;
        this.featureBuffer = embeddingsFromPcm(warmNoise());
        Log.i(TAG, "ready feats=" + this.featureBuffer.length);
    }

    public final void reset() {
        this.rawBuffer = new float[0];
        this.rawRemainder = new float[0];
        this.accumulatedSamples = 0;
        float[][] fArr = new float[76][];
        for (int i = 0; i < 76; i++) {
            float[] fArr2 = new float[32];
            for (int i2 = 0; i2 < 32; i2++) {
                fArr2[i2] = 1.0f;
            }
            fArr[i] = fArr2;
        }
        this.melBuffer = fArr;
        this.featureBuffer = embeddingsFromPcm(warmNoise());
    }

    public final int accept(float[] pcmInt16Scale) {
        Intrinsics.checkNotNullParameter(pcmInt16Scale, "pcmInt16Scale");
        float[] x = pcmInt16Scale;
        int processed = 0;
        if (!(this.rawRemainder.length == 0)) {
            x = ArraysKt.plus(this.rawRemainder, x);
            this.rawRemainder = new float[0];
        }
        if (this.accumulatedSamples + x.length >= 1280) {
            int rem = (this.accumulatedSamples + x.length) % CHUNK;
            if (rem != 0) {
                float[] even = ArraysKt.copyOfRange(x, 0, x.length - rem);
                bufferRaw(even);
                this.accumulatedSamples += even.length;
                this.rawRemainder = ArraysKt.copyOfRange(x, x.length - rem, x.length);
            } else {
                bufferRaw(x);
                this.accumulatedSamples += x.length;
                this.rawRemainder = new float[0];
            }
        } else {
            this.accumulatedSamples += x.length;
            bufferRaw(x);
        }
        if (this.accumulatedSamples >= 1280 && this.accumulatedSamples % CHUNK == 0) {
            streamingMel(this.accumulatedSamples);
            int steps = this.accumulatedSamples / CHUNK;
            for (int i = steps - 1; -1 < i; i--) {
                int start = OwwAudioFeaturesKt.melWindowEnd(this.melBuffer.length, i) - 76;
                if (start >= 0) {
                    float[][] window = new float[76][];
                    for (int i2 = 0; i2 < 76; i2++) {
                        window[i2] = this.melBuffer[start + i2];
                    }
                    float[][] emb = embedBatch(new float[][][]{window});
                    if (!(emb.length == 0)) {
                        this.featureBuffer = (float[][]) ArraysKt.plus((Object[]) this.featureBuffer, (Object[]) emb);
                    }
                }
            }
            processed = this.accumulatedSamples;
            this.accumulatedSamples = 0;
        }
        if (this.featureBuffer.length > FEATURE_MAX) {
            Collection $this$toTypedArray$iv = ArraysKt.takeLast(this.featureBuffer, FEATURE_MAX);
            this.featureBuffer = (float[][]) $this$toTypedArray$iv.toArray(new float[0][]);
        }
        return processed != 0 ? processed : this.accumulatedSamples;
    }

    public static /* synthetic */ float[][][] getFeatures$default(OwwAudioFeatures owwAudioFeatures, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 16;
        }
        return owwAudioFeatures.getFeatures(i);
    }

    public final float[][][] getFeatures(int nFrames) {
        int n = RangesKt.coerceAtMost(nFrames, this.featureBuffer.length);
        int start = this.featureBuffer.length - n;
        float[][] fArr = new float[n][];
        for (int i = 0; i < n; i++) {
            fArr[i] = this.featureBuffer[start + i];
        }
        return new float[][][]{fArr};
    }

    public final float[][][] getFeaturesAt(int nFrames, int startFromEnd) {
        int endExclusive = RangesKt.coerceIn(this.featureBuffer.length - startFromEnd, 0, this.featureBuffer.length);
        int start = RangesKt.coerceAtLeast(endExclusive - nFrames, 0);
        int n = RangesKt.coerceAtLeast(endExclusive - start, 0);
        if (n < nFrames) {
            return getFeatures(nFrames);
        }
        float[][] fArr = new float[nFrames][];
        for (int i = 0; i < nFrames; i++) {
            fArr[i] = this.featureBuffer[start + i];
        }
        return new float[][][]{fArr};
    }

    public final int getFeatureCount() {
        return this.featureBuffer.length;
    }

    private final float[] warmNoise() {
        float[] fArr = new float[64000];
        for (int i = 0; i < 64000; i++) {
            fArr[i] = Random.INSTANCE.nextInt(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, 1000);
        }
        return fArr;
    }

    private final void bufferRaw(float[] data) {
        float[] merged = ArraysKt.plus(this.rawBuffer, data);
        this.rawBuffer = merged.length > 16000 ? ArraysKt.copyOfRange(merged, merged.length - 16000, merged.length) : merged;
    }

    private final void streamingMel(int nSamples) {
        if (this.rawBuffer.length < 400) {
            return;
        }
        int need = nSamples + 480;
        float[] slice = ArraysKt.copyOfRange(this.rawBuffer, RangesKt.coerceAtLeast(this.rawBuffer.length - need, 0), this.rawBuffer.length);
        float[][] frames = computeMel(slice);
        if (frames.length == 0) {
            return;
        }
        this.melBuffer = (float[][]) ArraysKt.plus((Object[]) this.melBuffer, (Object[]) frames);
        if (this.melBuffer.length > MEL_MAX) {
            Collection $this$toTypedArray$iv = ArraysKt.takeLast(this.melBuffer, MEL_MAX);
            this.melBuffer = (float[][]) $this$toTypedArray$iv.toArray(new float[0][]);
        }
    }

    private final float[][] embeddingsFromPcm(float[] pcm) {
        float[][] spec = computeMel(pcm);
        if (spec.length == 0) {
            return new float[0][];
        }
        ArrayList windows = new ArrayList();
        for (int i = 0; i + 76 <= spec.length; i += 8) {
            windows.add(ArraysKt.sliceArray(spec, RangesKt.until(i, i + 76)));
        }
        if (windows.isEmpty()) {
            return new float[0][];
        }
        ArrayList $this$toTypedArray$iv = windows;
        return embedBatch((float[][][]) $this$toTypedArray$iv.toArray(new float[0][][]));
    }

    private final float[][] computeMel(float[] pcm) {
        float[][] fArr;
        OnnxTensor tensor = null;
        try {
            try {
                tensor = OnnxTensor.createTensor(this.env, FloatBuffer.wrap(pcm), new long[]{1, pcm.length});
                OrtSession.Result resultRun = this.melSession.run(MapsKt.mapOf(TuplesKt.to(this.melInput, tensor)));
                try {
                    OrtSession.Result outs = resultRun;
                    Object value = outs.get(0).getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                    float[][] fArrSqueezeMel = squeezeMel(value);
                    AutoCloseableKt.closeFinally(resultRun, null);
                    fArr = fArrSqueezeMel;
                    if (tensor != null) {
                        tensor.close();
                    }
                    return fArr;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AutoCloseableKt.closeFinally(resultRun, th);
                        throw th2;
                    }
                }
            } catch (Exception e) {
                Log.w(TAG, "mel: " + e.getMessage());
                fArr = new float[0][];
                if (0 != 0) {
                }
            }
        } catch (Throwable th3) {
            if (0 != 0) {
                tensor.close();
            }
            throw th3;
        }
    }

    private final float[][] squeezeMel(Object value) {
        try {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Array<kotlin.Array<kotlin.FloatArray>>>");
            float[][][][] a = (float[][][][]) value;
            float[][] fr = a[0][0];
            int length = fr.length;
            float[][] fArr = new float[length][];
            for (int i = 0; i < length; i++) {
                int length2 = fr[i].length;
                float[] fArr2 = new float[length2];
                for (int i2 = 0; i2 < length2; i2++) {
                    fArr2[i2] = (fr[i][i2] / 10.0f) + 2.0f;
                }
                fArr[i] = fArr2;
            }
            return fArr;
        } catch (ClassCastException e) {
            try {
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Array<kotlin.FloatArray>>");
                float[][] fr2 = ((float[][][]) value)[0];
                int length3 = fr2.length;
                float[][] fArr3 = new float[length3][];
                for (int i3 = 0; i3 < length3; i3++) {
                    int length4 = fr2[i3].length;
                    float[] fArr4 = new float[length4];
                    for (int i4 = 0; i4 < length4; i4++) {
                        fArr4[i4] = (fr2[i3][i4] / 10.0f) + 2.0f;
                    }
                    fArr3[i3] = fArr4;
                }
                return fArr3;
            } catch (Exception e2) {
                Log.w(TAG, "mel shape " + value.getClass().getName() + ": " + e2.getMessage());
                return new float[0][];
            }
        }
    }

    private final float[][] embedBatch(float[][][] windows) {
        float[][] fArr;
        if (windows.length == 0) {
            return new float[0][];
        }
        OnnxTensor tensor = null;
        try {
            try {
                int length = windows.length;
                float[][][][] shaped = new float[length][][][];
                for (int i = 0; i < length; i++) {
                    float[][][] fArr2 = new float[76][][];
                    for (int i2 = 0; i2 < 76; i2++) {
                        float[][] fArr3 = new float[32][];
                        for (int i3 = 0; i3 < 32; i3++) {
                            fArr3[i3] = new float[]{windows[i][i2][i3]};
                        }
                        fArr2[i2] = fArr3;
                    }
                    shaped[i] = fArr2;
                }
                tensor = OnnxTensor.createTensor(this.env, shaped);
                OrtSession.Result resultRun = this.embSession.run(MapsKt.mapOf(TuplesKt.to(this.embInput, tensor)));
                try {
                    OrtSession.Result outs = resultRun;
                    Object value = outs.get(0).getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                    float[][] embeddings = parseEmbeddings(value);
                    AutoCloseableKt.closeFinally(resultRun, null);
                    fArr = embeddings;
                    if (tensor != null) {
                        tensor.close();
                    }
                    return fArr;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AutoCloseableKt.closeFinally(resultRun, th);
                        throw th2;
                    }
                }
            } catch (Exception e) {
                Log.w(TAG, "emb: " + e.getMessage());
                fArr = new float[0][];
                if (0 != 0) {
                }
            }
        } catch (Throwable th3) {
            if (0 != 0) {
                tensor.close();
            }
            throw th3;
        }
    }

    private final float[][] parseEmbeddings(Object value) {
        try {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Array<kotlin.Array<kotlin.FloatArray>>>");
            float[][][][] raw = (float[][][][]) value;
            int length = raw.length;
            float[][] fArr = new float[length][];
            for (int i = 0; i < length; i++) {
                float[] fArr2 = raw[i][0][0];
                float[] fArrCopyOf = Arrays.copyOf(fArr2, fArr2.length);
                Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
                fArr[i] = fArrCopyOf;
            }
            return fArr;
        } catch (ClassCastException e) {
            try {
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.FloatArray>");
                float[][] raw2 = (float[][]) value;
                int length2 = raw2.length;
                float[][] fArr3 = new float[length2][];
                for (int i2 = 0; i2 < length2; i2++) {
                    float[] fArr4 = raw2[i2];
                    float[] fArrCopyOf2 = Arrays.copyOf(fArr4, fArr4.length);
                    Intrinsics.checkNotNullExpressionValue(fArrCopyOf2, "copyOf(...)");
                    fArr3[i2] = fArrCopyOf2;
                }
                return fArr3;
            } catch (Exception e2) {
                Log.w(TAG, "emb shape " + value.getClass().getName() + ": " + e2.getMessage());
                return new float[0][];
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
    }
}
