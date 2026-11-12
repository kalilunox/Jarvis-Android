package com.jarvis.core.proactive;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.commons.compress.archivers.zip.UnixStat;

/* JADX INFO: compiled from: ProactivityState.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 ;2\u00020\u0001:\u0001;B¥\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r\u0012\b\b\u0002\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00032\b\b\u0002\u0010'\u001a\u00020(J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u0015\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010/\u001a\u00020\rHÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\rHÆ\u0003J\t\u00102\u001a\u00020\rHÆ\u0003J\t\u00103\u001a\u00020\rHÆ\u0003J\t\u00104\u001a\u00020\rHÆ\u0003J§\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rHÆ\u0001J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\rHÖ\u0001J\t\u0010:\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\u0012\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001f¨\u0006<"}, d2 = {"Lcom/jarvis/core/proactive/ProactivityState;", "", "lastDecisionAtMillis", "", "lastDecisionType", "Lcom/jarvis/core/proactive/ProactiveDecisionType;", "globalCooldownUntilMillis", "behaviorCooldownUntil", "", "", "fingerprintCooldownUntil", "rejectCooldownUntil", "budgetTokens", "", "budgetLastRefillAtMillis", "suggestionCount", "questionCount", "confirmationCount", "recordedNoOpCount", "<init>", "(JLcom/jarvis/core/proactive/ProactiveDecisionType;JLjava/util/Map;Ljava/util/Map;Ljava/util/Map;IJIIII)V", "getLastDecisionAtMillis", "()J", "getLastDecisionType", "()Lcom/jarvis/core/proactive/ProactiveDecisionType;", "getGlobalCooldownUntilMillis", "getBehaviorCooldownUntil", "()Ljava/util/Map;", "getFingerprintCooldownUntil", "getRejectCooldownUntil", "getBudgetTokens", "()I", "getBudgetLastRefillAtMillis", "getSuggestionCount", "getQuestionCount", "getConfirmationCount", "getRecordedNoOpCount", "pruneExpired", "nowMillis", "config", "Lcom/jarvis/core/proactive/ProactivityConfig;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProactivityState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, Long> behaviorCooldownUntil;
    private final long budgetLastRefillAtMillis;
    private final int budgetTokens;
    private final int confirmationCount;
    private final Map<String, Long> fingerprintCooldownUntil;
    private final long globalCooldownUntilMillis;
    private final long lastDecisionAtMillis;
    private final ProactiveDecisionType lastDecisionType;
    private final int questionCount;
    private final int recordedNoOpCount;
    private final Map<String, Long> rejectCooldownUntil;
    private final int suggestionCount;

    public ProactivityState() {
        this(0L, null, 0L, null, null, null, 0, 0L, 0, 0, 0, 0, UnixStat.PERM_MASK, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ProactivityState copy$default(ProactivityState proactivityState, long j, ProactiveDecisionType proactiveDecisionType, long j2, Map map, Map map2, Map map3, int i, long j3, int i2, int i3, int i4, int i5, int i6, Object obj) {
        return proactivityState.copy((i6 & 1) != 0 ? proactivityState.lastDecisionAtMillis : j, (i6 & 2) != 0 ? proactivityState.lastDecisionType : proactiveDecisionType, (i6 & 4) != 0 ? proactivityState.globalCooldownUntilMillis : j2, (i6 & 8) != 0 ? proactivityState.behaviorCooldownUntil : map, (i6 & 16) != 0 ? proactivityState.fingerprintCooldownUntil : map2, (i6 & 32) != 0 ? proactivityState.rejectCooldownUntil : map3, (i6 & 64) != 0 ? proactivityState.budgetTokens : i, (i6 & 128) != 0 ? proactivityState.budgetLastRefillAtMillis : j3, (i6 & 256) != 0 ? proactivityState.suggestionCount : i2, (i6 & 512) != 0 ? proactivityState.questionCount : i3, (i6 & 1024) != 0 ? proactivityState.confirmationCount : i4, (i6 & 2048) != 0 ? proactivityState.recordedNoOpCount : i5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getLastDecisionAtMillis() {
        return this.lastDecisionAtMillis;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getQuestionCount() {
        return this.questionCount;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getConfirmationCount() {
        return this.confirmationCount;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getRecordedNoOpCount() {
        return this.recordedNoOpCount;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProactiveDecisionType getLastDecisionType() {
        return this.lastDecisionType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getGlobalCooldownUntilMillis() {
        return this.globalCooldownUntilMillis;
    }

    public final Map<String, Long> component4() {
        return this.behaviorCooldownUntil;
    }

    public final Map<String, Long> component5() {
        return this.fingerprintCooldownUntil;
    }

    public final Map<String, Long> component6() {
        return this.rejectCooldownUntil;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getBudgetTokens() {
        return this.budgetTokens;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getBudgetLastRefillAtMillis() {
        return this.budgetLastRefillAtMillis;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getSuggestionCount() {
        return this.suggestionCount;
    }

    public final ProactivityState copy(long lastDecisionAtMillis, ProactiveDecisionType lastDecisionType, long globalCooldownUntilMillis, Map<String, Long> behaviorCooldownUntil, Map<String, Long> fingerprintCooldownUntil, Map<String, Long> rejectCooldownUntil, int budgetTokens, long budgetLastRefillAtMillis, int suggestionCount, int questionCount, int confirmationCount, int recordedNoOpCount) {
        Intrinsics.checkNotNullParameter(behaviorCooldownUntil, "behaviorCooldownUntil");
        Intrinsics.checkNotNullParameter(fingerprintCooldownUntil, "fingerprintCooldownUntil");
        Intrinsics.checkNotNullParameter(rejectCooldownUntil, "rejectCooldownUntil");
        return new ProactivityState(lastDecisionAtMillis, lastDecisionType, globalCooldownUntilMillis, behaviorCooldownUntil, fingerprintCooldownUntil, rejectCooldownUntil, budgetTokens, budgetLastRefillAtMillis, suggestionCount, questionCount, confirmationCount, recordedNoOpCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProactivityState)) {
            return false;
        }
        ProactivityState proactivityState = (ProactivityState) other;
        return this.lastDecisionAtMillis == proactivityState.lastDecisionAtMillis && this.lastDecisionType == proactivityState.lastDecisionType && this.globalCooldownUntilMillis == proactivityState.globalCooldownUntilMillis && Intrinsics.areEqual(this.behaviorCooldownUntil, proactivityState.behaviorCooldownUntil) && Intrinsics.areEqual(this.fingerprintCooldownUntil, proactivityState.fingerprintCooldownUntil) && Intrinsics.areEqual(this.rejectCooldownUntil, proactivityState.rejectCooldownUntil) && this.budgetTokens == proactivityState.budgetTokens && this.budgetLastRefillAtMillis == proactivityState.budgetLastRefillAtMillis && this.suggestionCount == proactivityState.suggestionCount && this.questionCount == proactivityState.questionCount && this.confirmationCount == proactivityState.confirmationCount && this.recordedNoOpCount == proactivityState.recordedNoOpCount;
    }

    public int hashCode() {
        return (((((((((((((((((((((Long.hashCode(this.lastDecisionAtMillis) * 31) + (this.lastDecisionType == null ? 0 : this.lastDecisionType.hashCode())) * 31) + Long.hashCode(this.globalCooldownUntilMillis)) * 31) + this.behaviorCooldownUntil.hashCode()) * 31) + this.fingerprintCooldownUntil.hashCode()) * 31) + this.rejectCooldownUntil.hashCode()) * 31) + Integer.hashCode(this.budgetTokens)) * 31) + Long.hashCode(this.budgetLastRefillAtMillis)) * 31) + Integer.hashCode(this.suggestionCount)) * 31) + Integer.hashCode(this.questionCount)) * 31) + Integer.hashCode(this.confirmationCount)) * 31) + Integer.hashCode(this.recordedNoOpCount);
    }

    public String toString() {
        return "ProactivityState(lastDecisionAtMillis=" + this.lastDecisionAtMillis + ", lastDecisionType=" + this.lastDecisionType + ", globalCooldownUntilMillis=" + this.globalCooldownUntilMillis + ", behaviorCooldownUntil=" + this.behaviorCooldownUntil + ", fingerprintCooldownUntil=" + this.fingerprintCooldownUntil + ", rejectCooldownUntil=" + this.rejectCooldownUntil + ", budgetTokens=" + this.budgetTokens + ", budgetLastRefillAtMillis=" + this.budgetLastRefillAtMillis + ", suggestionCount=" + this.suggestionCount + ", questionCount=" + this.questionCount + ", confirmationCount=" + this.confirmationCount + ", recordedNoOpCount=" + this.recordedNoOpCount + ")";
    }

    public ProactivityState(long lastDecisionAtMillis, ProactiveDecisionType lastDecisionType, long globalCooldownUntilMillis, Map<String, Long> behaviorCooldownUntil, Map<String, Long> fingerprintCooldownUntil, Map<String, Long> rejectCooldownUntil, int budgetTokens, long budgetLastRefillAtMillis, int suggestionCount, int questionCount, int confirmationCount, int recordedNoOpCount) {
        Intrinsics.checkNotNullParameter(behaviorCooldownUntil, "behaviorCooldownUntil");
        Intrinsics.checkNotNullParameter(fingerprintCooldownUntil, "fingerprintCooldownUntil");
        Intrinsics.checkNotNullParameter(rejectCooldownUntil, "rejectCooldownUntil");
        this.lastDecisionAtMillis = lastDecisionAtMillis;
        this.lastDecisionType = lastDecisionType;
        this.globalCooldownUntilMillis = globalCooldownUntilMillis;
        this.behaviorCooldownUntil = behaviorCooldownUntil;
        this.fingerprintCooldownUntil = fingerprintCooldownUntil;
        this.rejectCooldownUntil = rejectCooldownUntil;
        this.budgetTokens = budgetTokens;
        this.budgetLastRefillAtMillis = budgetLastRefillAtMillis;
        this.suggestionCount = suggestionCount;
        this.questionCount = questionCount;
        this.confirmationCount = confirmationCount;
        this.recordedNoOpCount = recordedNoOpCount;
    }

    public /* synthetic */ ProactivityState(long j, ProactiveDecisionType proactiveDecisionType, long j2, Map map, Map map2, Map map3, int i, long j3, int i2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0L : j, (i6 & 2) != 0 ? null : proactiveDecisionType, (i6 & 4) != 0 ? 0L : j2, (i6 & 8) != 0 ? MapsKt.emptyMap() : map, (i6 & 16) != 0 ? MapsKt.emptyMap() : map2, (i6 & 32) != 0 ? MapsKt.emptyMap() : map3, (i6 & 64) != 0 ? ProactivityConfig.INSTANCE.getDEFAULT().getBudgetMaxTokens() : i, (i6 & 128) == 0 ? j3 : 0L, (i6 & 256) != 0 ? 0 : i2, (i6 & 512) != 0 ? 0 : i3, (i6 & 1024) != 0 ? 0 : i4, (i6 & 2048) == 0 ? i5 : 0);
    }

    public final long getLastDecisionAtMillis() {
        return this.lastDecisionAtMillis;
    }

    public final ProactiveDecisionType getLastDecisionType() {
        return this.lastDecisionType;
    }

    public final long getGlobalCooldownUntilMillis() {
        return this.globalCooldownUntilMillis;
    }

    public final Map<String, Long> getBehaviorCooldownUntil() {
        return this.behaviorCooldownUntil;
    }

    public final Map<String, Long> getFingerprintCooldownUntil() {
        return this.fingerprintCooldownUntil;
    }

    public final Map<String, Long> getRejectCooldownUntil() {
        return this.rejectCooldownUntil;
    }

    public final int getBudgetTokens() {
        return this.budgetTokens;
    }

    public final long getBudgetLastRefillAtMillis() {
        return this.budgetLastRefillAtMillis;
    }

    public final int getSuggestionCount() {
        return this.suggestionCount;
    }

    public final int getQuestionCount() {
        return this.questionCount;
    }

    public final int getConfirmationCount() {
        return this.confirmationCount;
    }

    public final int getRecordedNoOpCount() {
        return this.recordedNoOpCount;
    }

    public static /* synthetic */ ProactivityState pruneExpired$default(ProactivityState proactivityState, long j, ProactivityConfig proactivityConfig, int i, Object obj) {
        if ((i & 2) != 0) {
            proactivityConfig = ProactivityConfig.INSTANCE.getDEFAULT();
        }
        return proactivityState.pruneExpired(j, proactivityConfig);
    }

    public final ProactivityState pruneExpired(long nowMillis, ProactivityConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        long now = INSTANCE.sanitizeNow(nowMillis);
        Companion companion = INSTANCE;
        Map<String, Long> map = this.behaviorCooldownUntil;
        LinkedHashMap result$iv = new LinkedHashMap();
        Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, Long> next = it.next();
            long it2 = next.getValue().longValue();
            if (it2 > now) {
                result$iv.put(next.getKey(), next.getValue());
            }
        }
        LinkedHashMap $this$filterValues$iv = result$iv;
        int $i$f$filterValues = config.getMaxBehaviorCooldowns();
        Map<String, Long> mapBoundMap = companion.boundMap($this$filterValues$iv, $i$f$filterValues);
        Companion companion2 = INSTANCE;
        Map<String, Long> map2 = this.fingerprintCooldownUntil;
        LinkedHashMap result$iv2 = new LinkedHashMap();
        for (Map.Entry<String, Long> entry : map2.entrySet()) {
            long it3 = entry.getValue().longValue();
            if (it3 > now) {
                result$iv2.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap $this$filterValues$iv2 = result$iv2;
        int $i$f$filterValues2 = config.getMaxFingerprints();
        Map<String, Long> mapBoundMap2 = companion2.boundMap($this$filterValues$iv2, $i$f$filterValues2);
        Companion companion3 = INSTANCE;
        Map<String, Long> map3 = this.rejectCooldownUntil;
        LinkedHashMap result$iv3 = new LinkedHashMap();
        for (Map.Entry<String, Long> entry2 : map3.entrySet()) {
            long it4 = entry2.getValue().longValue();
            if (it4 > now) {
                result$iv3.put(entry2.getKey(), entry2.getValue());
            }
        }
        LinkedHashMap $this$filterValues$iv3 = result$iv3;
        int $i$f$filterValues3 = config.getMaxRejectCooldowns();
        return copy$default(this, 0L, null, 0L, mapBoundMap, mapBoundMap2, companion3.boundMap($this$filterValues$iv3, $i$f$filterValues3), 0, 0L, 0, 0, 0, 0, 4039, null);
    }

    /* JADX INFO: compiled from: ProactivityState.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ.\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/proactive/ProactivityState$Companion;", "", "<init>", "()V", "initial", "Lcom/jarvis/core/proactive/ProactivityState;", "config", "Lcom/jarvis/core/proactive/ProactivityConfig;", "sanitizeNow", "", "nowMillis", "boundMap", "", "", "map", "max", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ ProactivityState initial$default(Companion companion, ProactivityConfig proactivityConfig, int i, Object obj) {
            if ((i & 1) != 0) {
                proactivityConfig = ProactivityConfig.INSTANCE.getDEFAULT();
            }
            return companion.initial(proactivityConfig);
        }

        public final ProactivityState initial(ProactivityConfig config) {
            Intrinsics.checkNotNullParameter(config, "config");
            return new ProactivityState(0L, null, 0L, null, null, null, config.getBudgetMaxTokens(), 0L, 0, 0, 0, 0, 4031, null);
        }

        public final long sanitizeNow(long nowMillis) {
            return RangesKt.coerceAtLeast(nowMillis, 0L);
        }

        public final Map<String, Long> boundMap(Map<String, Long> map, int max) {
            Intrinsics.checkNotNullParameter(map, "map");
            if (map.size() <= max) {
                return map;
            }
            Iterable $this$sortedBy$iv = map.entrySet();
            Iterable $this$associate$iv = CollectionsKt.takeLast(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.core.proactive.ProactivityState$Companion$boundMap$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Map.Entry it = (Map.Entry) t;
                    Long l = (Long) it.getValue();
                    Map.Entry it2 = (Map.Entry) t2;
                    return ComparisonsKt.compareValues(l, (Long) it2.getValue());
                }
            }), max);
            int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
            Map destination$iv$iv = new LinkedHashMap(capacity$iv);
            for (Object element$iv$iv : $this$associate$iv) {
                Map.Entry it = (Map.Entry) element$iv$iv;
                Pair pair = TuplesKt.to(it.getKey(), it.getValue());
                destination$iv$iv.put(pair.getFirst(), pair.getSecond());
            }
            return destination$iv$iv;
        }
    }
}
