package com.jarvis.core.storage;

import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RetentionPolicy.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0005J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u000e\u001a\u00020\u0005J \u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0005J\u001d\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/storage/RetentionPolicy;", "", "<init>", "()V", "HOUR_MS", "", "DAY_MS", "CONVERSATION_TTL_MS", "RECENT_WINDOW_MS", "CONTEXT_RECENT_LIMIT", "", "STORE_RECENT_LIMIT", "cutoff", "nowMillis", "ttlMs", "isExpired", "", "createdAtMillis", "keep", "isRecent", "windowMs", "isFactExpired", "expiresAtMillis", "(Ljava/lang/Long;J)Z", "ageMs", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RetentionPolicy {
    public static final int CONTEXT_RECENT_LIMIT = 20;
    public static final long CONVERSATION_TTL_MS = 7776000000L;
    private static final long DAY_MS = 86400000;
    private static final long HOUR_MS = 3600000;
    public static final RetentionPolicy INSTANCE = new RetentionPolicy();
    public static final long RECENT_WINDOW_MS = 172800000;
    public static final int STORE_RECENT_LIMIT = 60;

    private RetentionPolicy() {
    }

    public static /* synthetic */ long cutoff$default(RetentionPolicy retentionPolicy, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = CONVERSATION_TTL_MS;
        }
        return retentionPolicy.cutoff(j, j2);
    }

    public final long cutoff(long nowMillis, long ttlMs) {
        return nowMillis - ttlMs;
    }

    public final boolean isExpired(long createdAtMillis, long nowMillis, boolean keep, long ttlMs) {
        return !keep && createdAtMillis < cutoff(nowMillis, ttlMs);
    }

    public static /* synthetic */ boolean isRecent$default(RetentionPolicy retentionPolicy, long j, long j2, long j3, int i, Object obj) {
        if ((i & 4) != 0) {
            j3 = RECENT_WINDOW_MS;
        }
        return retentionPolicy.isRecent(j, j2, j3);
    }

    public final boolean isRecent(long createdAtMillis, long nowMillis, long windowMs) {
        return createdAtMillis > nowMillis || nowMillis - createdAtMillis <= windowMs;
    }

    public final boolean isFactExpired(Long expiresAtMillis, long nowMillis) {
        return expiresAtMillis != null && expiresAtMillis.longValue() <= nowMillis;
    }

    public final long ageMs(long createdAtMillis, long nowMillis) {
        if (createdAtMillis <= 0) {
            return -1L;
        }
        return RangesKt.coerceAtLeast(nowMillis - createdAtMillis, 0L);
    }
}
