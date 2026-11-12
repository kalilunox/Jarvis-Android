package com.jarvis.brain.aiprovider;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: ProviderHealthTracker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\rJ\"\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0003J\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fj\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/jarvis/brain/aiprovider/ProviderHealthTracker;", "", "cooldownMs", "", "degradedAfterFailures", "", "unavailableAfterFailures", "clock", "Lkotlin/Function0;", "<init>", "(JIILkotlin/jvm/functions/Function0;)V", "byId", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/brain/aiprovider/ProviderHealthTracker$Entry;", "Lkotlin/collections/LinkedHashMap;", "status", "Lcom/jarvis/brain/aiprovider/ProviderHealthStatus;", "providerId", "quota", "Lcom/jarvis/brain/aiprovider/QuotaState;", "recordSuccess", "", "recordFailure", "error", "Lcom/jarvis/brain/aiprovider/AIProviderError;", "latencyMs", "snapshot", "reset", "Entry", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProviderHealthTracker {
    public static final long DEFAULT_COOLDOWN_MS = 30000;
    private final LinkedHashMap<String, Entry> byId;
    private final Function0<Long> clock;
    private final long cooldownMs;
    private final int degradedAfterFailures;
    private final int unavailableAfterFailures;

    public ProviderHealthTracker() {
        this(0L, 0, 0, null, 15, null);
    }

    public ProviderHealthTracker(long cooldownMs, int degradedAfterFailures, int unavailableAfterFailures, Function0<Long> clock) {
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.cooldownMs = cooldownMs;
        this.degradedAfterFailures = degradedAfterFailures;
        this.unavailableAfterFailures = unavailableAfterFailures;
        this.clock = clock;
        this.byId = new LinkedHashMap<>();
    }

    public /* synthetic */ ProviderHealthTracker(long j, int i, int i2, Function0 function0, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 30000L : j, (i3 & 2) != 0 ? 2 : i, (i3 & 4) != 0 ? 4 : i2, (i3 & 8) != 0 ? new Function0() { // from class: com.jarvis.brain.aiprovider.ProviderHealthTracker$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0);
    }

    /* JADX INFO: compiled from: ProviderHealthTracker.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0007HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003JQ\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\u0005HÖ\u0001J\t\u00104\u001a\u00020\tHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0015\"\u0004\b%\u0010\u0017R\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0015\"\u0004\b'\u0010\u0017¨\u00065"}, d2 = {"Lcom/jarvis/brain/aiprovider/ProviderHealthTracker$Entry;", "", "status", "Lcom/jarvis/brain/aiprovider/ProviderHealthStatus;", "consecutiveFailures", "", "cooldownUntilMs", "", "lastErrorCode", "", "quota", "Lcom/jarvis/brain/aiprovider/QuotaState;", "successCount", "failureCount", "<init>", "(Lcom/jarvis/brain/aiprovider/ProviderHealthStatus;IJLjava/lang/String;Lcom/jarvis/brain/aiprovider/QuotaState;II)V", "getStatus", "()Lcom/jarvis/brain/aiprovider/ProviderHealthStatus;", "setStatus", "(Lcom/jarvis/brain/aiprovider/ProviderHealthStatus;)V", "getConsecutiveFailures", "()I", "setConsecutiveFailures", "(I)V", "getCooldownUntilMs", "()J", "setCooldownUntilMs", "(J)V", "getLastErrorCode", "()Ljava/lang/String;", "setLastErrorCode", "(Ljava/lang/String;)V", "getQuota", "()Lcom/jarvis/brain/aiprovider/QuotaState;", "setQuota", "(Lcom/jarvis/brain/aiprovider/QuotaState;)V", "getSuccessCount", "setSuccessCount", "getFailureCount", "setFailureCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Entry {
        private int consecutiveFailures;
        private long cooldownUntilMs;
        private int failureCount;
        private String lastErrorCode;
        private QuotaState quota;
        private ProviderHealthStatus status;
        private int successCount;

        public Entry() {
            this(null, 0, 0L, null, null, 0, 0, WorkQueueKt.MASK, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ProviderHealthStatus getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getConsecutiveFailures() {
            return this.consecutiveFailures;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getCooldownUntilMs() {
            return this.cooldownUntilMs;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLastErrorCode() {
            return this.lastErrorCode;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final QuotaState getQuota() {
            return this.quota;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getSuccessCount() {
            return this.successCount;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getFailureCount() {
            return this.failureCount;
        }

        public final Entry copy(ProviderHealthStatus status, int consecutiveFailures, long cooldownUntilMs, String lastErrorCode, QuotaState quota, int successCount, int failureCount) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(quota, "quota");
            return new Entry(status, consecutiveFailures, cooldownUntilMs, lastErrorCode, quota, successCount, failureCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return this.status == entry.status && this.consecutiveFailures == entry.consecutiveFailures && this.cooldownUntilMs == entry.cooldownUntilMs && Intrinsics.areEqual(this.lastErrorCode, entry.lastErrorCode) && this.quota == entry.quota && this.successCount == entry.successCount && this.failureCount == entry.failureCount;
        }

        public int hashCode() {
            return (((((((((((this.status.hashCode() * 31) + Integer.hashCode(this.consecutiveFailures)) * 31) + Long.hashCode(this.cooldownUntilMs)) * 31) + (this.lastErrorCode == null ? 0 : this.lastErrorCode.hashCode())) * 31) + this.quota.hashCode()) * 31) + Integer.hashCode(this.successCount)) * 31) + Integer.hashCode(this.failureCount);
        }

        public String toString() {
            return "Entry(status=" + this.status + ", consecutiveFailures=" + this.consecutiveFailures + ", cooldownUntilMs=" + this.cooldownUntilMs + ", lastErrorCode=" + this.lastErrorCode + ", quota=" + this.quota + ", successCount=" + this.successCount + ", failureCount=" + this.failureCount + ")";
        }

        public Entry(ProviderHealthStatus status, int consecutiveFailures, long cooldownUntilMs, String lastErrorCode, QuotaState quota, int successCount, int failureCount) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(quota, "quota");
            this.status = status;
            this.consecutiveFailures = consecutiveFailures;
            this.cooldownUntilMs = cooldownUntilMs;
            this.lastErrorCode = lastErrorCode;
            this.quota = quota;
            this.successCount = successCount;
            this.failureCount = failureCount;
        }

        public /* synthetic */ Entry(ProviderHealthStatus providerHealthStatus, int i, long j, String str, QuotaState quotaState, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? ProviderHealthStatus.UNKNOWN : providerHealthStatus, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0L : j, (i4 & 8) != 0 ? null : str, (i4 & 16) != 0 ? QuotaState.UNKNOWN : quotaState, (i4 & 32) != 0 ? 0 : i2, (i4 & 64) == 0 ? i3 : 0);
        }

        public final ProviderHealthStatus getStatus() {
            return this.status;
        }

        public final void setStatus(ProviderHealthStatus providerHealthStatus) {
            Intrinsics.checkNotNullParameter(providerHealthStatus, "<set-?>");
            this.status = providerHealthStatus;
        }

        public final int getConsecutiveFailures() {
            return this.consecutiveFailures;
        }

        public final void setConsecutiveFailures(int i) {
            this.consecutiveFailures = i;
        }

        public final long getCooldownUntilMs() {
            return this.cooldownUntilMs;
        }

        public final void setCooldownUntilMs(long j) {
            this.cooldownUntilMs = j;
        }

        public final String getLastErrorCode() {
            return this.lastErrorCode;
        }

        public final void setLastErrorCode(String str) {
            this.lastErrorCode = str;
        }

        public final QuotaState getQuota() {
            return this.quota;
        }

        public final void setQuota(QuotaState quotaState) {
            Intrinsics.checkNotNullParameter(quotaState, "<set-?>");
            this.quota = quotaState;
        }

        public final int getSuccessCount() {
            return this.successCount;
        }

        public final void setSuccessCount(int i) {
            this.successCount = i;
        }

        public final int getFailureCount() {
            return this.failureCount;
        }

        public final void setFailureCount(int i) {
            this.failureCount = i;
        }
    }

    public final ProviderHealthStatus status(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Entry e = this.byId.get(providerId);
        if (e == null) {
            return ProviderHealthStatus.UNKNOWN;
        }
        long now = this.clock.invoke().longValue();
        if (e.getCooldownUntilMs() > 0 && now >= e.getCooldownUntilMs()) {
            e.setCooldownUntilMs(0L);
            if (e.getStatus() == ProviderHealthStatus.UNAVAILABLE) {
                e.setStatus(ProviderHealthStatus.DEGRADED);
                e.setConsecutiveFailures(this.degradedAfterFailures);
            }
        }
        return e.getCooldownUntilMs() > now ? ProviderHealthStatus.UNAVAILABLE : e.getStatus();
    }

    public final QuotaState quota(String providerId) {
        QuotaState quota;
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Entry entry = this.byId.get(providerId);
        return (entry == null || (quota = entry.getQuota()) == null) ? QuotaState.UNKNOWN : quota;
    }

    public final void recordSuccess(String providerId) {
        Entry entry;
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Map $this$getOrPut$iv = this.byId;
        Entry entry2 = $this$getOrPut$iv.get(providerId);
        if (entry2 == null) {
            entry = new Entry(null, 0, 0L, null, null, 0, 0, WorkQueueKt.MASK, null);
            $this$getOrPut$iv.put(providerId, entry);
        } else {
            entry = entry2;
        }
        Entry e = entry;
        e.setConsecutiveFailures(0);
        e.setCooldownUntilMs(0L);
        e.setStatus(ProviderHealthStatus.AVAILABLE);
        e.setSuccessCount(e.getSuccessCount() + 1);
        e.setLastErrorCode(null);
        if (e.getQuota() == QuotaState.RATE_LIMITED) {
            e.setQuota(QuotaState.AVAILABLE);
        }
    }

    public static /* synthetic */ void recordFailure$default(ProviderHealthTracker providerHealthTracker, String str, AIProviderError aIProviderError, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 0;
        }
        providerHealthTracker.recordFailure(str, aIProviderError, j);
    }

    public final void recordFailure(String providerId, AIProviderError error, long latencyMs) {
        Entry entry;
        ProviderHealthStatus providerHealthStatus;
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Map $this$getOrPut$iv = this.byId;
        Entry entry2 = $this$getOrPut$iv.get(providerId);
        if (entry2 == null) {
            entry = new Entry(null, 0, 0L, null, null, 0, 0, WorkQueueKt.MASK, null);
            $this$getOrPut$iv.put(providerId, entry);
        } else {
            entry = entry2;
        }
        Entry e = entry;
        e.setFailureCount(e.getFailureCount() + 1);
        e.setConsecutiveFailures(e.getConsecutiveFailures() + 1);
        e.setLastErrorCode(error != null ? error.getCode() : null);
        if ((error != null ? error.getCategory() : null) != AIProviderErrorCategory.UNAVAILABLE || (!StringsKt.contains$default((CharSequence) error.getCode(), (CharSequence) "401", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) error.getCode(), (CharSequence) "403", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) error.getMessage(), (CharSequence) "401", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) error.getMessage(), (CharSequence) "403", false, 2, (Object) null) && !Intrinsics.areEqual(error.getCode(), "CLOUD_NO_KEY"))) {
            if (Intrinsics.areEqual(error != null ? error.getCode() : null, "CLOUD_ERROR") && StringsKt.contains$default((CharSequence) error.getMessage(), (CharSequence) "HTTP 429", false, 2, (Object) null)) {
                e.setQuota(QuotaState.RATE_LIMITED);
                e.setStatus(ProviderHealthStatus.DEGRADED);
                e.setCooldownUntilMs(this.clock.invoke().longValue() + this.cooldownMs);
                return;
            }
            if ((error != null ? error.getCategory() : null) != AIProviderErrorCategory.TIMEOUT) {
                if ((error != null ? error.getCategory() : null) != AIProviderErrorCategory.NETWORK) {
                    if (e.getConsecutiveFailures() >= this.unavailableAfterFailures) {
                        e.setStatus(ProviderHealthStatus.UNAVAILABLE);
                        e.setCooldownUntilMs(this.clock.invoke().longValue() + this.cooldownMs);
                        return;
                    } else if (e.getConsecutiveFailures() >= this.degradedAfterFailures) {
                        e.setStatus(ProviderHealthStatus.DEGRADED);
                        e.setCooldownUntilMs(this.clock.invoke().longValue() + (this.cooldownMs / ((long) 2)));
                        return;
                    } else {
                        e.setStatus(ProviderHealthStatus.DEGRADED);
                        return;
                    }
                }
            }
            if (e.getConsecutiveFailures() >= this.unavailableAfterFailures) {
                providerHealthStatus = ProviderHealthStatus.UNAVAILABLE;
            } else {
                providerHealthStatus = ProviderHealthStatus.DEGRADED;
            }
            e.setStatus(providerHealthStatus);
            e.setCooldownUntilMs(this.clock.invoke().longValue() + this.cooldownMs);
            return;
        }
        e.setStatus(ProviderHealthStatus.UNAVAILABLE);
        e.setCooldownUntilMs(this.clock.invoke().longValue() + (this.cooldownMs * ((long) 2)));
    }

    public final String snapshot(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Entry e = this.byId.get(providerId);
        ProviderHealthStatus st = status(providerId);
        if (e == null) {
            return providerId + ":" + st;
        }
        return providerId + ":" + st + "/q=" + e.getQuota() + "/fail=" + e.getConsecutiveFailures();
    }

    public final void reset(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        this.byId.remove(providerId);
    }
}
