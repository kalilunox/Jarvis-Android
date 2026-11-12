package com.jarvis.core.runtime;

import android.os.SystemClock;
import kotlin.Metadata;
import org.apache.commons.compress.harmony.pack200.PackingOptions;

/* JADX INFO: compiled from: LatencyClock.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/jarvis/core/runtime/LatencyClock;", "", "<init>", "()V", "elapsedMs", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LatencyClock {
    public static final LatencyClock INSTANCE = new LatencyClock();

    private LatencyClock() {
    }

    public final long elapsedMs() {
        try {
            return SystemClock.elapsedRealtime();
        } catch (Throwable th) {
            return System.nanoTime() / PackingOptions.SEGMENT_LIMIT;
        }
    }
}
