package com.jarvis.app;

import android.content.Context;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: WakeAudioDump.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0014J\u0006\u0010\u001a\u001a\u00020\u0011J\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/jarvis/app/WakeAudioDump;", "", "<init>", "()V", "TAG", "", "FILE_NAME", "MAX_BYTES", "", "out", "Ljava/io/BufferedOutputStream;", "written", "replayRequested", "", "isActive", "()Z", "requestReplay", "", "takeReplayRequest", "read", "", "context", "Landroid/content/Context;", "start", "write", "samples", "stop", "delete", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WakeAudioDump {
    private static final String FILE_NAME = "wake_dump.pcm";
    public static final WakeAudioDump INSTANCE = new WakeAudioDump();
    private static final long MAX_BYTES = 28800000;
    private static final String TAG = "Jarvis.WakeDump";
    private static volatile BufferedOutputStream out;
    private static volatile boolean replayRequested;
    private static long written;

    private WakeAudioDump() {
    }

    public final boolean isActive() {
        return out != null;
    }

    public final void requestReplay() {
        replayRequested = true;
    }

    public final boolean takeReplayRequest() {
        if (!replayRequested) {
            return false;
        }
        replayRequested = false;
        return true;
    }

    public final float[] read(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        byte[] bytes = FilesKt.readBytes(new File(context.getFilesDir(), FILE_NAME));
        int length = bytes.length / 2;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int low = bytes[i2] & UByte.MAX_VALUE;
            fArr[i] = ((bytes[i2 + 1] << 8) | low) / 32768.0f;
        }
        return fArr;
    }

    public final synchronized void start(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (out != null) {
            return;
        }
        written = 0L;
        out = new BufferedOutputStream(new FileOutputStream(new File(context.getFilesDir(), FILE_NAME)), 65536);
        Log.i(TAG, "start");
    }

    public final synchronized void write(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        BufferedOutputStream o = out;
        if (o == null) {
            return;
        }
        byte[] bytes = new byte[samples.length * 2];
        int length = samples.length;
        for (int i = 0; i < length; i++) {
            int v = RangesKt.coerceIn((int) (samples[i] * 32768.0f), -32768, 32767);
            bytes[i * 2] = (byte) v;
            bytes[(i * 2) + 1] = (byte) (v >> 8);
        }
        o.write(bytes);
        written += (long) bytes.length;
        if (written >= MAX_BYTES) {
            stop();
        }
    }

    public final synchronized void stop() {
        BufferedOutputStream o = out;
        if (o == null) {
            return;
        }
        out = null;
        try {
            Result.Companion companion = Result.INSTANCE;
            WakeAudioDump wakeAudioDump = this;
            o.close();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Log.i(TAG, "stop bytes=" + written);
    }

    public final void delete(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        stop();
        new File(context.getFilesDir(), FILE_NAME).delete();
        Log.i(TAG, "deleted");
    }
}
