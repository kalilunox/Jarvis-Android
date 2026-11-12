package com.jarvis.wakeword;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WakeSpeechGate.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005J\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/jarvis/wakeword/WakeSpeechGate;", "", "<init>", "()V", "recent", "", "pos", "", "filled", "floor", "", "onChunk", "", "samples", "voicedChunks", "speechPresent", "", "noiseFloor", "getNoiseFloor", "()F", "Companion", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WakeSpeechGate {
    private static final float FLOOR_RISE = 1.005f;
    private static final float INITIAL_FLOOR = 0.001f;
    private static final float MIN_FLOOR = 1.0E-5f;
    private static final int MIN_VOICED_CHUNKS = 3;
    private static final float MIN_VOICED_RMS = 8.0E-4f;
    private static final float VOICED_RATIO = 2.5f;
    private static final int WINDOW_CHUNKS = 15;
    private int filled;
    private int pos;
    private final float[] recent = new float[15];
    private float floor = INITIAL_FLOOR;

    public final void onChunk(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        if (samples.length == 0) {
            return;
        }
        double sum = 0.0d;
        for (float s : samples) {
            sum += (double) (s * s);
        }
        float rms = (float) Math.sqrt(sum / ((double) samples.length));
        this.floor = rms < this.floor ? Math.max(rms, MIN_FLOOR) : this.floor * FLOOR_RISE;
        this.recent[this.pos] = rms;
        this.pos = (this.pos + 1) % 15;
        if (this.filled < 15) {
            this.filled++;
        }
    }

    public final int voicedChunks() {
        float threshold = Math.max(this.floor * VOICED_RATIO, MIN_VOICED_RMS);
        int voiced = 0;
        int i = this.filled;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.recent[i2] >= threshold) {
                voiced++;
            }
        }
        return voiced;
    }

    public final boolean speechPresent() {
        return voicedChunks() >= 3;
    }

    /* JADX INFO: renamed from: getNoiseFloor, reason: from getter */
    public final float getFloor() {
        return this.floor;
    }
}
