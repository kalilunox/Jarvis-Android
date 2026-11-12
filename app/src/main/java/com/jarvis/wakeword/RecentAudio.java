package com.jarvis.wakeword;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VoicePrint.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u0006\u0010\r\u001a\u00020\u0007J\u0006\u0010\u000e\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/wakeword/RecentAudio;", "", "capacity", "", "<init>", "(I)V", "buffer", "", "pos", "filled", "push", "", "samples", "snapshot", "clear", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RecentAudio {
    private final float[] buffer;
    private final int capacity;
    private int filled;
    private int pos;

    public RecentAudio(int capacity) {
        this.capacity = capacity;
        this.buffer = new float[this.capacity];
    }

    public final void push(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        for (float s : samples) {
            this.buffer[this.pos] = s;
            this.pos = (this.pos + 1) % this.capacity;
        }
        this.filled = Math.min(this.capacity, this.filled + samples.length);
    }

    public final float[] snapshot() {
        float[] out = new float[this.filled];
        int start = ((this.pos - this.filled) + this.capacity) % this.capacity;
        int i = this.filled;
        for (int i2 = 0; i2 < i; i2++) {
            out[i2] = this.buffer[(start + i2) % this.capacity];
        }
        return out;
    }

    public final void clear() {
        this.pos = 0;
        this.filled = 0;
    }
}
