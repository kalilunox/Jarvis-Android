package com.jarvis.tts;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: TtsPlayer.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ.\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014J\u0006\u0010\u0015\u001a\u00020\u000bJ\u0006\u0010\u0016\u001a\u00020\u000bJ\u0006\u0010\u0017\u001a\u00020\u000bJ\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u000fH\u0002J\u0010\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0005H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001d"}, d2 = {"Lcom/jarvis/tts/TtsPlayer;", "", "<init>", "()V", "playing", "Landroid/media/AudioTrack;", "stopRequested", "", "isPlaying", "()Z", "play", "", "samples", "", "sampleRate", "", "playContinuous", "onFirstWrite", "Lkotlin/Function0;", "blocks", "Lkotlin/sequences/Sequence;", "playChime", "stop", "release", "createStaticTrack", "waitUntilDone", "track", "totalFrames", "finishTrack", "tts-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TtsPlayer {
    private volatile AudioTrack playing;
    private volatile boolean stopRequested;

    public final boolean isPlaying() {
        return this.playing != null;
    }

    public final void play(float[] samples, int sampleRate) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        if (samples.length == 0) {
            return;
        }
        stop();
        AudioTrack track = createStaticTrack(samples, sampleRate);
        if (track == null) {
            return;
        }
        this.stopRequested = false;
        this.playing = track;
        track.play();
        waitUntilDone(track, samples.length);
        finishTrack(track);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playContinuous$default(TtsPlayer ttsPlayer, int i, Function0 function0, Sequence sequence, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function0 = null;
        }
        ttsPlayer.playContinuous(i, function0, sequence);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v8 */
    public final void playContinuous(int sampleRate, Function0<Unit> onFirstWrite, Sequence<float[]> blocks) {
        Intrinsics.checkNotNullParameter(blocks, "blocks");
        stop();
        int it = AudioTrack.getMinBufferSize(sampleRate, 4, 2);
        if (it <= 0) {
            it = sampleRate * 2;
        }
        int bufSize = RangesKt.coerceAtLeast(it * 8, sampleRate * 4);
        boolean z = true;
        AudioAttributes attr = new AudioAttributes.Builder().setUsage(16).setContentType(1).build();
        AudioFormat format = new AudioFormat.Builder().setEncoding(2).setChannelMask(4).setSampleRate(sampleRate).build();
        AudioTrack track = new AudioTrack(attr, format, bufSize, 1, 0);
        if (track.getState() != 1) {
            track.release();
            float[] all = CollectionsKt.toFloatArray(SequencesKt.toList(SequencesKt.flatMapIterable(blocks, new Function1() { // from class: com.jarvis.tts.TtsPlayer$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TtsPlayer.playContinuous$lambda$1((float[]) obj);
                }
            })));
            play(all, sampleRate);
            if (onFirstWrite != null) {
                onFirstWrite.invoke();
                return;
            }
            return;
        }
        float[] all2 = null;
        this.stopRequested = false;
        this.playing = track;
        track.play();
        boolean first = true;
        int frames = 0;
        short[] shortBuf = new short[4096];
        for (float[] block : blocks) {
            if (this.stopRequested || this.playing != track) {
                break;
                break;
            }
            if ((block.length == 0 ? z : all2) == 0) {
                if (first) {
                    first = false;
                    if (onFirstWrite != null) {
                        onFirstWrite.invoke();
                    }
                }
                int offset = 0;
                while (offset < block.length && !this.stopRequested && this.playing == track) {
                    int n = Math.min(shortBuf.length, block.length - offset);
                    int i = 0;
                    while (i < n) {
                        int minBuf = it;
                        float v = RangesKt.coerceIn(block[offset + i], -1.0f, 1.0f);
                        shortBuf[i] = (short) (32767.0f * v);
                        i++;
                        it = minBuf;
                    }
                    int minBuf2 = it;
                    int written = 0;
                    while (written < n && !this.stopRequested && this.playing == track) {
                        int w = track.write(shortBuf, written, n - written);
                        if (w <= 0) {
                            break;
                        }
                        written += w;
                        frames += w;
                    }
                    offset += n;
                    it = minBuf2;
                }
                int minBuf3 = it;
                it = minBuf3;
                all2 = null;
                z = true;
            } else {
                all2 = null;
                z = true;
            }
        }
        long timeoutAt = System.currentTimeMillis() + ((long) 30000);
        while (!this.stopRequested && this.playing == track && track.getPlayState() == 3 && System.currentTimeMillis() < timeoutAt && (frames <= 0 || track.getPlaybackHeadPosition() < frames - 1)) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
            }
        }
        finishTrack(track);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable playContinuous$lambda$1(float[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ArraysKt.asIterable(it);
    }

    public final void playChime() {
        int nA = (int) (((double) 22050) * 0.08d);
        int nB = (int) (((double) 22050) * 0.16d);
        float[] samples = new float[nA + nB];
        int i = 0;
        while (i < nA) {
            samples[i] = ((float) Math.sin((((double) i) * 5529.203070318036d) / ((double) 22050))) * 0.5f;
            i++;
        }
        while (i < samples.length) {
            int j = i - nA;
            samples[i] = ((float) Math.sin((((double) j) * 7376.459550628834d) / ((double) 22050))) * 0.5f;
            i++;
        }
        play(samples, 22050);
    }

    public final void stop() {
        this.stopRequested = true;
        AudioTrack t = this.playing;
        if (t == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            TtsPlayer ttsPlayer = this;
            t.pause();
            t.flush();
            t.stop();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        t.release();
        this.playing = null;
    }

    public final void release() {
        stop();
    }

    private final AudioTrack createStaticTrack(float[] samples, int sampleRate) {
        int bufSize = RangesKt.coerceAtLeast(AudioTrack.getMinBufferSize(sampleRate, 4, 4), samples.length * 4);
        AudioAttributes attr = new AudioAttributes.Builder().setUsage(16).setContentType(1).build();
        AudioFormat format = new AudioFormat.Builder().setEncoding(4).setChannelMask(4).setSampleRate(sampleRate).build();
        AudioTrack $this$createStaticTrack_u24lambda_u240 = new AudioTrack(attr, format, bufSize, 0, 0);
        $this$createStaticTrack_u24lambda_u240.write(samples, 0, samples.length, 0);
        return $this$createStaticTrack_u24lambda_u240;
    }

    private final void waitUntilDone(AudioTrack track, int totalFrames) {
        while (!this.stopRequested && track.getPlayState() == 3 && track.getPlaybackHeadPosition() < totalFrames) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                stop();
                return;
            }
        }
    }

    private final void finishTrack(AudioTrack track) {
        if (this.playing == track) {
            try {
                Result.Companion companion = Result.INSTANCE;
                TtsPlayer ttsPlayer = this;
                track.pause();
                track.flush();
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            track.release();
            this.playing = null;
        }
    }
}
