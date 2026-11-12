package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: HybridAsrController.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\rR\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/voice/BoundedPcmRing;", "", "maxBufferedMs", "", "sampleRateHz", "<init>", "(II)V", "capacity", "buf", "", "size", "write", "appendFloat", "", "samples", "snapshotFloat", "clear", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BoundedPcmRing {
    private final float[] buf;
    private final int capacity;
    private int size;
    private int write;

    public BoundedPcmRing(int maxBufferedMs, int sampleRateHz) {
        this.capacity = RangesKt.coerceAtLeast((int) ((((double) maxBufferedMs) / 1000.0d) * ((double) sampleRateHz)), sampleRateHz);
        this.buf = new float[this.capacity];
    }

    public final synchronized void appendFloat(float[] samples, int sampleRateHz) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        for (float s : samples) {
            this.buf[this.write] = s;
            this.write = (this.write + 1) % this.capacity;
            if (this.size < this.capacity) {
                this.size++;
            }
        }
    }

    public final synchronized float[] snapshotFloat() {
        if (this.size == 0) {
            return new float[0];
        }
        float[] out = new float[this.size];
        int start = this.size == this.capacity ? this.write : 0;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            out[i2] = this.buf[(start + i2) % this.capacity];
        }
        return out;
    }

    public final synchronized void clear() {
        this.size = 0;
        this.write = 0;
    }
}
