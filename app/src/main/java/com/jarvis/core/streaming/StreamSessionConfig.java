package com.jarvis.core.streaming;

import com.jarvis.app.ui.HomeScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StreamSession.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/streaming/StreamSessionConfig;", "", "timeoutMs", "", "allowBargeIn", "", "allowPartialResults", "<init>", "(JZZ)V", "getTimeoutMs", "()J", "getAllowBargeIn", "()Z", "getAllowPartialResults", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StreamSessionConfig {
    private final boolean allowBargeIn;
    private final boolean allowPartialResults;
    private final long timeoutMs;

    public StreamSessionConfig() {
        this(0L, false, false, 7, null);
    }

    public static /* synthetic */ StreamSessionConfig copy$default(StreamSessionConfig streamSessionConfig, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = streamSessionConfig.timeoutMs;
        }
        if ((i & 2) != 0) {
            z = streamSessionConfig.allowBargeIn;
        }
        if ((i & 4) != 0) {
            z2 = streamSessionConfig.allowPartialResults;
        }
        return streamSessionConfig.copy(j, z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getTimeoutMs() {
        return this.timeoutMs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getAllowBargeIn() {
        return this.allowBargeIn;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getAllowPartialResults() {
        return this.allowPartialResults;
    }

    public final StreamSessionConfig copy(long timeoutMs, boolean allowBargeIn, boolean allowPartialResults) {
        return new StreamSessionConfig(timeoutMs, allowBargeIn, allowPartialResults);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamSessionConfig)) {
            return false;
        }
        StreamSessionConfig streamSessionConfig = (StreamSessionConfig) other;
        return this.timeoutMs == streamSessionConfig.timeoutMs && this.allowBargeIn == streamSessionConfig.allowBargeIn && this.allowPartialResults == streamSessionConfig.allowPartialResults;
    }

    public int hashCode() {
        return (((Long.hashCode(this.timeoutMs) * 31) + Boolean.hashCode(this.allowBargeIn)) * 31) + Boolean.hashCode(this.allowPartialResults);
    }

    public String toString() {
        return "StreamSessionConfig(timeoutMs=" + this.timeoutMs + ", allowBargeIn=" + this.allowBargeIn + ", allowPartialResults=" + this.allowPartialResults + ")";
    }

    public StreamSessionConfig(long timeoutMs, boolean allowBargeIn, boolean allowPartialResults) {
        this.timeoutMs = timeoutMs;
        this.allowBargeIn = allowBargeIn;
        this.allowPartialResults = allowPartialResults;
    }

    public /* synthetic */ StreamSessionConfig(long j, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? HomeScreen.CLOCK_TICK_MS : j, (i & 2) != 0 ? true : z, (i & 4) != 0 ? true : z2);
    }

    public final long getTimeoutMs() {
        return this.timeoutMs;
    }

    public final boolean getAllowBargeIn() {
        return this.allowBargeIn;
    }

    public final boolean getAllowPartialResults() {
        return this.allowPartialResults;
    }
}
