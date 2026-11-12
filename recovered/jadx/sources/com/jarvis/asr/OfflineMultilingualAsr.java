package com.jarvis.asr;

import android.util.Log;
import androidx.core.view.PointerIconCompat;
import com.k2fsa.sherpa.onnx.FeatureConfig;
import com.k2fsa.sherpa.onnx.OfflineModelConfig;
import com.k2fsa.sherpa.onnx.OfflineNemoEncDecCtcModelConfig;
import com.k2fsa.sherpa.onnx.OfflineRecognizer;
import com.k2fsa.sherpa.onnx.OfflineRecognizerConfig;
import com.k2fsa.sherpa.onnx.OfflineStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OfflineMultilingualAsr.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00060\u0001j\u0002`\u0002:\u0001\u0012B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/asr/OfflineMultilingualAsr;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "modelDir", "", "<init>", "(Ljava/lang/String;)V", "lock", "", "recognizer", "Lcom/k2fsa/sherpa/onnx/OfflineRecognizer;", "released", "", "decode", "samples", "", "close", "", "Companion", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OfflineMultilingualAsr implements AutoCloseable {
    public static final int FEATURE_DIM = 80;
    public static final String MODEL_DIR = "sherpa-onnx-nemo-fast-conformer-ctc-be-de-en-es-fr-hr-it-pl-ru-uk-20k-int8";
    public static final int SAMPLE_RATE = 16000;
    private static final String TAG = "Jarvis.Asr";
    private final Object lock;
    private final OfflineRecognizer recognizer;
    private boolean released;

    public OfflineMultilingualAsr(String modelDir) {
        Object element$iv;
        File it;
        Intrinsics.checkNotNullParameter(modelDir, "modelDir");
        this.lock = new Object();
        if (!new File(modelDir).isDirectory()) {
            throw new IllegalArgumentException(("Model dir not found: " + modelDir).toString());
        }
        Iterable $this$map$iv = CollectionsKt.listOf((Object[]) new String[]{"model.int8.onnx", "model.onnx"});
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it2 = (String) item$iv$iv;
            destination$iv$iv.add(new File(modelDir, it2));
        }
        Iterable $this$firstOrNull$iv = (List) destination$iv$iv;
        Iterator it3 = $this$firstOrNull$iv.iterator();
        do {
            if (!it3.hasNext()) {
                element$iv = null;
                break;
            } else {
                element$iv = it3.next();
                it = (File) element$iv;
            }
        } while (!it.isFile());
        File modelFile = (File) element$iv;
        if (modelFile == null) {
            throw new IllegalStateException(("No model.int8.onnx / model.onnx in " + modelDir).toString());
        }
        File tokens = new File(modelDir, "tokens.txt");
        if (!tokens.isFile()) {
            throw new IllegalArgumentException(("tokens.txt missing in " + modelDir).toString());
        }
        FeatureConfig featureConfig = new FeatureConfig(16000, 80, 0.0f, 4, null);
        String absolutePath = modelFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        OfflineNemoEncDecCtcModelConfig offlineNemoEncDecCtcModelConfig = new OfflineNemoEncDecCtcModelConfig(absolutePath);
        String absolutePath2 = tokens.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
        OfflineRecognizerConfig config = new OfflineRecognizerConfig(featureConfig, new OfflineModelConfig(null, null, null, null, null, offlineNemoEncDecCtcModelConfig, null, null, null, null, null, null, null, null, null, null, null, null, 0, false, null, null, absolutePath2, null, null, 29360095, null), null, "greedy_search", 0, null, 0.0f, null, null, 0.0f, PointerIconCompat.TYPE_NO_DROP, null);
        this.recognizer = new OfflineRecognizer(null, config);
        Log.i(TAG, "offline multilingual ready from " + modelFile.getName());
    }

    public final String decode(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        synchronized (this.lock) {
            if (!this.released) {
                if (!(samples.length == 0)) {
                    OfflineStream stream = this.recognizer.createStream();
                    try {
                        stream.acceptWaveform(samples, 16000);
                        this.recognizer.decode(stream);
                        String string = StringsKt.trim((CharSequence) this.recognizer.getResult(stream).getText()).toString();
                        stream.release();
                        return string;
                    } catch (Throwable th) {
                        stream.release();
                        throw th;
                    }
                }
            }
            return "";
        }
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
