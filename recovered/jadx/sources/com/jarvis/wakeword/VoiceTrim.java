package com.jarvis.wakeword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: VoicePrint.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0005J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00122\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/wakeword/VoiceTrim;", "", "<init>", "()V", "FRAME_SAMPLES", "", "PEAK_RATIO", "", "MIN_RMS", "MIN_SPEECH_SAMPLES", "WINDOW_SAMPLES", "HOP_SAMPLES", "MAX_WINDOWS", "speech", "", "pcm", "minSamples", "windows", "", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceTrim {
    private static final int FRAME_SAMPLES = 480;
    private static final int HOP_SAMPLES = 12000;
    public static final VoiceTrim INSTANCE = new VoiceTrim();
    private static final int MAX_WINDOWS = 6;
    private static final float MIN_RMS = 8.0E-4f;
    private static final int MIN_SPEECH_SAMPLES = 3200;
    private static final float PEAK_RATIO = 0.12f;
    private static final int WINDOW_SAMPLES = 24000;

    private VoiceTrim() {
    }

    public static /* synthetic */ float[] speech$default(VoiceTrim voiceTrim, float[] fArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = MIN_SPEECH_SAMPLES;
        }
        return voiceTrim.speech(fArr, i);
    }

    public final float[] speech(float[] pcm, int minSamples) {
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        int frames = pcm.length / FRAME_SAMPLES;
        if (frames == 0) {
            return new float[0];
        }
        float[] rms = new float[frames];
        for (int i = 0; i < frames; i++) {
            double sum = 0.0d;
            for (int i2 = 0; i2 < FRAME_SAMPLES; i2++) {
                float s = pcm[(i * FRAME_SAMPLES) + i2];
                sum += (double) (s * s);
            }
            rms[i] = (float) Math.sqrt(sum / ((double) FRAME_SAMPLES));
        }
        Float fMaxOrNull = ArraysKt.maxOrNull(rms);
        float level = Math.max((fMaxOrNull != null ? fMaxOrNull.floatValue() : 0.0f) * 0.12f, MIN_RMS);
        Iterable $this$filter$iv = RangesKt.until(0, frames);
        Collection destination$iv$iv = new ArrayList();
        for (Integer num : $this$filter$iv) {
            int it = num.intValue();
            if (rms[it] >= level) {
                destination$iv$iv.add(num);
            }
        }
        List voiced = (List) destination$iv$iv;
        if (voiced.size() * FRAME_SAMPLES < minSamples) {
            return new float[0];
        }
        float[] out = new float[voiced.size() * FRAME_SAMPLES];
        List $this$forEachIndexed$iv = voiced;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int f = ((Number) item$iv).intValue();
            ArraysKt.copyInto(pcm, out, index$iv * FRAME_SAMPLES, f * FRAME_SAMPLES, (f + 1) * FRAME_SAMPLES);
            index$iv = index$iv2;
            frames = frames;
            rms = rms;
            level = level;
        }
        return out;
    }

    public final List<float[]> windows(float[] speech) {
        Intrinsics.checkNotNullParameter(speech, "speech");
        if (speech.length <= 24000) {
            return speech.length == 0 ? CollectionsKt.emptyList() : CollectionsKt.listOf(speech);
        }
        int last = speech.length - 24000;
        int hop = Math.max(12000, last / 5);
        Iterable $this$map$iv = RangesKt.step(new IntRange(0, last), hop);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator<Integer> it = $this$map$iv.iterator();
        while (it.hasNext()) {
            int item$iv$iv = ((IntIterator) it).nextInt();
            destination$iv$iv.add(ArraysKt.copyOfRange(speech, item$iv$iv, item$iv$iv + 24000));
        }
        return (List) destination$iv$iv;
    }
}
