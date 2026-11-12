package com.jarvis.core.cloud;

import com.jarvis.app.ui.HomeScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ChainConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 02\u00020\u0001:\u00010Bu\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003Jw\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0007HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012¨\u00061"}, d2 = {"Lcom/jarvis/core/cloud/ChainConfig;", "", "hedgeAfterMs", "", "totalDeadlineMs", "connectTimeoutMs", "maxParallel", "", "dailyExhaustedCooldownMs", "rateLimitedDefaultSeconds", "rateLimitedMaxSeconds", "circuitOpenMs", "circuitFailureThreshold", "streamIdleTimeoutMs", "blockedCooldownMs", "<init>", "(JJJIJIIJIJJ)V", "getHedgeAfterMs", "()J", "getTotalDeadlineMs", "getConnectTimeoutMs", "getMaxParallel", "()I", "getDailyExhaustedCooldownMs", "getRateLimitedDefaultSeconds", "getRateLimitedMaxSeconds", "getCircuitOpenMs", "getCircuitFailureThreshold", "getStreamIdleTimeoutMs", "getBlockedCooldownMs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ChainConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ChainConfig Default = new ChainConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2047, null);
    private final long blockedCooldownMs;
    private final int circuitFailureThreshold;
    private final long circuitOpenMs;
    private final long connectTimeoutMs;
    private final long dailyExhaustedCooldownMs;
    private final long hedgeAfterMs;
    private final int maxParallel;
    private final int rateLimitedDefaultSeconds;
    private final int rateLimitedMaxSeconds;
    private final long streamIdleTimeoutMs;
    private final long totalDeadlineMs;

    public ChainConfig() {
        this(0L, 0L, 0L, 0, 0L, 0, 0, 0L, 0, 0L, 0L, 2047, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getHedgeAfterMs() {
        return this.hedgeAfterMs;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getStreamIdleTimeoutMs() {
        return this.streamIdleTimeoutMs;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getBlockedCooldownMs() {
        return this.blockedCooldownMs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTotalDeadlineMs() {
        return this.totalDeadlineMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getConnectTimeoutMs() {
        return this.connectTimeoutMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getMaxParallel() {
        return this.maxParallel;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getDailyExhaustedCooldownMs() {
        return this.dailyExhaustedCooldownMs;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getRateLimitedDefaultSeconds() {
        return this.rateLimitedDefaultSeconds;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getRateLimitedMaxSeconds() {
        return this.rateLimitedMaxSeconds;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getCircuitOpenMs() {
        return this.circuitOpenMs;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getCircuitFailureThreshold() {
        return this.circuitFailureThreshold;
    }

    public final ChainConfig copy(long hedgeAfterMs, long totalDeadlineMs, long connectTimeoutMs, int maxParallel, long dailyExhaustedCooldownMs, int rateLimitedDefaultSeconds, int rateLimitedMaxSeconds, long circuitOpenMs, int circuitFailureThreshold, long streamIdleTimeoutMs, long blockedCooldownMs) {
        return new ChainConfig(hedgeAfterMs, totalDeadlineMs, connectTimeoutMs, maxParallel, dailyExhaustedCooldownMs, rateLimitedDefaultSeconds, rateLimitedMaxSeconds, circuitOpenMs, circuitFailureThreshold, streamIdleTimeoutMs, blockedCooldownMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChainConfig)) {
            return false;
        }
        ChainConfig chainConfig = (ChainConfig) other;
        return this.hedgeAfterMs == chainConfig.hedgeAfterMs && this.totalDeadlineMs == chainConfig.totalDeadlineMs && this.connectTimeoutMs == chainConfig.connectTimeoutMs && this.maxParallel == chainConfig.maxParallel && this.dailyExhaustedCooldownMs == chainConfig.dailyExhaustedCooldownMs && this.rateLimitedDefaultSeconds == chainConfig.rateLimitedDefaultSeconds && this.rateLimitedMaxSeconds == chainConfig.rateLimitedMaxSeconds && this.circuitOpenMs == chainConfig.circuitOpenMs && this.circuitFailureThreshold == chainConfig.circuitFailureThreshold && this.streamIdleTimeoutMs == chainConfig.streamIdleTimeoutMs && this.blockedCooldownMs == chainConfig.blockedCooldownMs;
    }

    public int hashCode() {
        return (((((((((((((((((((Long.hashCode(this.hedgeAfterMs) * 31) + Long.hashCode(this.totalDeadlineMs)) * 31) + Long.hashCode(this.connectTimeoutMs)) * 31) + Integer.hashCode(this.maxParallel)) * 31) + Long.hashCode(this.dailyExhaustedCooldownMs)) * 31) + Integer.hashCode(this.rateLimitedDefaultSeconds)) * 31) + Integer.hashCode(this.rateLimitedMaxSeconds)) * 31) + Long.hashCode(this.circuitOpenMs)) * 31) + Integer.hashCode(this.circuitFailureThreshold)) * 31) + Long.hashCode(this.streamIdleTimeoutMs)) * 31) + Long.hashCode(this.blockedCooldownMs);
    }

    public String toString() {
        return "ChainConfig(hedgeAfterMs=" + this.hedgeAfterMs + ", totalDeadlineMs=" + this.totalDeadlineMs + ", connectTimeoutMs=" + this.connectTimeoutMs + ", maxParallel=" + this.maxParallel + ", dailyExhaustedCooldownMs=" + this.dailyExhaustedCooldownMs + ", rateLimitedDefaultSeconds=" + this.rateLimitedDefaultSeconds + ", rateLimitedMaxSeconds=" + this.rateLimitedMaxSeconds + ", circuitOpenMs=" + this.circuitOpenMs + ", circuitFailureThreshold=" + this.circuitFailureThreshold + ", streamIdleTimeoutMs=" + this.streamIdleTimeoutMs + ", blockedCooldownMs=" + this.blockedCooldownMs + ")";
    }

    public ChainConfig(long hedgeAfterMs, long totalDeadlineMs, long connectTimeoutMs, int maxParallel, long dailyExhaustedCooldownMs, int rateLimitedDefaultSeconds, int rateLimitedMaxSeconds, long circuitOpenMs, int circuitFailureThreshold, long streamIdleTimeoutMs, long blockedCooldownMs) {
        this.hedgeAfterMs = hedgeAfterMs;
        this.totalDeadlineMs = totalDeadlineMs;
        this.connectTimeoutMs = connectTimeoutMs;
        this.maxParallel = maxParallel;
        this.dailyExhaustedCooldownMs = dailyExhaustedCooldownMs;
        this.rateLimitedDefaultSeconds = rateLimitedDefaultSeconds;
        this.rateLimitedMaxSeconds = rateLimitedMaxSeconds;
        this.circuitOpenMs = circuitOpenMs;
        this.circuitFailureThreshold = circuitFailureThreshold;
        this.streamIdleTimeoutMs = streamIdleTimeoutMs;
        this.blockedCooldownMs = blockedCooldownMs;
    }

    public /* synthetic */ ChainConfig(long j, long j2, long j3, int i, long j4, int i2, int i3, long j5, int i4, long j6, long j7, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 2500L : j, (i5 & 2) != 0 ? HomeScreen.CLOCK_TICK_MS : j2, (i5 & 4) != 0 ? 4000L : j3, (i5 & 8) != 0 ? 2 : i, (i5 & 16) != 0 ? 21600000L : j4, (i5 & 32) != 0 ? 20 : i2, (i5 & 64) != 0 ? 60 : i3, (i5 & 128) != 0 ? 300000L : j5, (i5 & 256) != 0 ? 3 : i4, (i5 & 512) != 0 ? 8000L : j6, (i5 & 1024) != 0 ? 300000L : j7);
    }

    public final long getHedgeAfterMs() {
        return this.hedgeAfterMs;
    }

    public final long getTotalDeadlineMs() {
        return this.totalDeadlineMs;
    }

    public final long getConnectTimeoutMs() {
        return this.connectTimeoutMs;
    }

    public final int getMaxParallel() {
        return this.maxParallel;
    }

    public final long getDailyExhaustedCooldownMs() {
        return this.dailyExhaustedCooldownMs;
    }

    public final int getRateLimitedDefaultSeconds() {
        return this.rateLimitedDefaultSeconds;
    }

    public final int getRateLimitedMaxSeconds() {
        return this.rateLimitedMaxSeconds;
    }

    public final long getCircuitOpenMs() {
        return this.circuitOpenMs;
    }

    public final int getCircuitFailureThreshold() {
        return this.circuitFailureThreshold;
    }

    public final long getStreamIdleTimeoutMs() {
        return this.streamIdleTimeoutMs;
    }

    public final long getBlockedCooldownMs() {
        return this.blockedCooldownMs;
    }

    /* JADX INFO: compiled from: ChainConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/cloud/ChainConfig$Companion;", "", "<init>", "()V", "Default", "Lcom/jarvis/core/cloud/ChainConfig;", "getDefault", "()Lcom/jarvis/core/cloud/ChainConfig;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChainConfig getDefault() {
            return ChainConfig.Default;
        }
    }
}
