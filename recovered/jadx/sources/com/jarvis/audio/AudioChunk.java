package com.jarvis.audio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioCapture.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/audio/AudioChunk;", "", "samples", "", "<init>", "([F)V", "getSamples", "()[F", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "audio-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AudioChunk {
    private final float[] samples;

    public static /* synthetic */ AudioChunk copy$default(AudioChunk audioChunk, float[] fArr, int i, Object obj) {
        if ((i & 1) != 0) {
            fArr = audioChunk.samples;
        }
        return audioChunk.copy(fArr);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float[] getSamples() {
        return this.samples;
    }

    public final AudioChunk copy(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        return new AudioChunk(samples);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AudioChunk) && Intrinsics.areEqual(this.samples, ((AudioChunk) other).samples);
    }

    public int hashCode() {
        return Arrays.hashCode(this.samples);
    }

    public String toString() {
        return "AudioChunk(samples=" + Arrays.toString(this.samples) + ")";
    }

    public AudioChunk(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        this.samples = samples;
    }

    public final float[] getSamples() {
        return this.samples;
    }
}
