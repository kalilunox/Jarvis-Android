package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: HybridAsrController.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/voice/AsrAudioFormat;", "", "<init>", "()V", "SAMPLE_RATE_HZ", "", "CHANNELS", "PREFERRED_CHUNK_SAMPLES", "floatToPcm16le", "", "samples", "", "pcm16leToWav", "pcm16le", "sampleRateHz", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AsrAudioFormat {
    public static final int CHANNELS = 1;
    public static final AsrAudioFormat INSTANCE = new AsrAudioFormat();
    public static final int PREFERRED_CHUNK_SAMPLES = 640;
    public static final int SAMPLE_RATE_HZ = 16000;

    private AsrAudioFormat() {
    }

    public final byte[] floatToPcm16le(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        byte[] out = new byte[samples.length * 2];
        int o = 0;
        for (float s : samples) {
            short v = (short) (RangesKt.coerceIn(s, -1.0f, 1.0f) * 32767.0f);
            int o2 = o + 1;
            out[o] = (byte) (v & 255);
            o = o2 + 1;
            out[o2] = (byte) ((v >> 8) & 255);
        }
        return out;
    }

    public static /* synthetic */ byte[] pcm16leToWav$default(AsrAudioFormat asrAudioFormat, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 16000;
        }
        return asrAudioFormat.pcm16leToWav(bArr, i);
    }

    public final byte[] pcm16leToWav(byte[] pcm16le, int sampleRateHz) {
        Intrinsics.checkNotNullParameter(pcm16le, "pcm16le");
        int dataSize = pcm16le.length;
        byte[] out = new byte[dataSize + 44];
        pcm16leToWav$w(out, "RIFF", 0);
        pcm16leToWav$i32(out, dataSize + 36, 4);
        pcm16leToWav$w(out, "WAVE", 8);
        pcm16leToWav$w(out, "fmt ", 12);
        pcm16leToWav$i32(out, 16, 16);
        pcm16leToWav$i16(out, 1, 20);
        pcm16leToWav$i16(out, 1, 22);
        pcm16leToWav$i32(out, sampleRateHz, 24);
        pcm16leToWav$i32(out, sampleRateHz * 2, 28);
        pcm16leToWav$i16(out, 2, 32);
        pcm16leToWav$i16(out, 16, 34);
        pcm16leToWav$w(out, "data", 36);
        pcm16leToWav$i32(out, dataSize, 40);
        System.arraycopy(pcm16le, 0, out, 44, dataSize);
        return out;
    }

    private static final void pcm16leToWav$w(byte[] out, String s, int at) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            out[at + i] = (byte) s.charAt(i);
        }
    }

    private static final void pcm16leToWav$i32(byte[] out, int v, int at) {
        out[at] = (byte) (v & 255);
        out[at + 1] = (byte) ((v >> 8) & 255);
        out[at + 2] = (byte) ((v >> 16) & 255);
        out[at + 3] = (byte) ((v >> 24) & 255);
    }

    private static final void pcm16leToWav$i16(byte[] out, int v, int at) {
        out[at] = (byte) (v & 255);
        out[at + 1] = (byte) ((v >> 8) & 255);
    }
}
