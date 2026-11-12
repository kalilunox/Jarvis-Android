package com.jarvis.core.proactive;

import com.jarvis.app.AnnouncementWatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ProactivityConfig.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 62\u00020\u0001:\u00016B\u0089\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\tHÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\u008b\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\tHÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\tHÖ\u0001J\t\u00104\u001a\u000205HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001a¨\u00067"}, d2 = {"Lcom/jarvis/core/proactive/ProactivityConfig;", "", "globalCooldownMs", "", "behaviorCooldownMs", "fingerprintCooldownMs", "ignoreCooldownMs", "rejectCooldownMs", "budgetMaxTokens", "", "budgetRefillIntervalMs", "budgetCostSuggestion", "budgetCostQuestion", "budgetCostConfirmation", "maxFingerprints", "maxBehaviorCooldowns", "maxRejectCooldowns", "<init>", "(JJJJJIJIIIIII)V", "getGlobalCooldownMs", "()J", "getBehaviorCooldownMs", "getFingerprintCooldownMs", "getIgnoreCooldownMs", "getRejectCooldownMs", "getBudgetMaxTokens", "()I", "getBudgetRefillIntervalMs", "getBudgetCostSuggestion", "getBudgetCostQuestion", "getBudgetCostConfirmation", "getMaxFingerprints", "getMaxBehaviorCooldowns", "getMaxRejectCooldowns", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProactivityConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ProactivityConfig DEFAULT = new ProactivityConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8191, null);
    private final long behaviorCooldownMs;
    private final int budgetCostConfirmation;
    private final int budgetCostQuestion;
    private final int budgetCostSuggestion;
    private final int budgetMaxTokens;
    private final long budgetRefillIntervalMs;
    private final long fingerprintCooldownMs;
    private final long globalCooldownMs;
    private final long ignoreCooldownMs;
    private final int maxBehaviorCooldowns;
    private final int maxFingerprints;
    private final int maxRejectCooldowns;
    private final long rejectCooldownMs;

    public ProactivityConfig() {
        this(0L, 0L, 0L, 0L, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 8191, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getGlobalCooldownMs() {
        return this.globalCooldownMs;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getBudgetCostConfirmation() {
        return this.budgetCostConfirmation;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getMaxFingerprints() {
        return this.maxFingerprints;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getMaxBehaviorCooldowns() {
        return this.maxBehaviorCooldowns;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getMaxRejectCooldowns() {
        return this.maxRejectCooldowns;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getBehaviorCooldownMs() {
        return this.behaviorCooldownMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getFingerprintCooldownMs() {
        return this.fingerprintCooldownMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getIgnoreCooldownMs() {
        return this.ignoreCooldownMs;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getRejectCooldownMs() {
        return this.rejectCooldownMs;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getBudgetMaxTokens() {
        return this.budgetMaxTokens;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getBudgetRefillIntervalMs() {
        return this.budgetRefillIntervalMs;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getBudgetCostSuggestion() {
        return this.budgetCostSuggestion;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getBudgetCostQuestion() {
        return this.budgetCostQuestion;
    }

    public final ProactivityConfig copy(long globalCooldownMs, long behaviorCooldownMs, long fingerprintCooldownMs, long ignoreCooldownMs, long rejectCooldownMs, int budgetMaxTokens, long budgetRefillIntervalMs, int budgetCostSuggestion, int budgetCostQuestion, int budgetCostConfirmation, int maxFingerprints, int maxBehaviorCooldowns, int maxRejectCooldowns) {
        return new ProactivityConfig(globalCooldownMs, behaviorCooldownMs, fingerprintCooldownMs, ignoreCooldownMs, rejectCooldownMs, budgetMaxTokens, budgetRefillIntervalMs, budgetCostSuggestion, budgetCostQuestion, budgetCostConfirmation, maxFingerprints, maxBehaviorCooldowns, maxRejectCooldowns);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProactivityConfig)) {
            return false;
        }
        ProactivityConfig proactivityConfig = (ProactivityConfig) other;
        return this.globalCooldownMs == proactivityConfig.globalCooldownMs && this.behaviorCooldownMs == proactivityConfig.behaviorCooldownMs && this.fingerprintCooldownMs == proactivityConfig.fingerprintCooldownMs && this.ignoreCooldownMs == proactivityConfig.ignoreCooldownMs && this.rejectCooldownMs == proactivityConfig.rejectCooldownMs && this.budgetMaxTokens == proactivityConfig.budgetMaxTokens && this.budgetRefillIntervalMs == proactivityConfig.budgetRefillIntervalMs && this.budgetCostSuggestion == proactivityConfig.budgetCostSuggestion && this.budgetCostQuestion == proactivityConfig.budgetCostQuestion && this.budgetCostConfirmation == proactivityConfig.budgetCostConfirmation && this.maxFingerprints == proactivityConfig.maxFingerprints && this.maxBehaviorCooldowns == proactivityConfig.maxBehaviorCooldowns && this.maxRejectCooldowns == proactivityConfig.maxRejectCooldowns;
    }

    public int hashCode() {
        return (((((((((((((((((((((((Long.hashCode(this.globalCooldownMs) * 31) + Long.hashCode(this.behaviorCooldownMs)) * 31) + Long.hashCode(this.fingerprintCooldownMs)) * 31) + Long.hashCode(this.ignoreCooldownMs)) * 31) + Long.hashCode(this.rejectCooldownMs)) * 31) + Integer.hashCode(this.budgetMaxTokens)) * 31) + Long.hashCode(this.budgetRefillIntervalMs)) * 31) + Integer.hashCode(this.budgetCostSuggestion)) * 31) + Integer.hashCode(this.budgetCostQuestion)) * 31) + Integer.hashCode(this.budgetCostConfirmation)) * 31) + Integer.hashCode(this.maxFingerprints)) * 31) + Integer.hashCode(this.maxBehaviorCooldowns)) * 31) + Integer.hashCode(this.maxRejectCooldowns);
    }

    public String toString() {
        return "ProactivityConfig(globalCooldownMs=" + this.globalCooldownMs + ", behaviorCooldownMs=" + this.behaviorCooldownMs + ", fingerprintCooldownMs=" + this.fingerprintCooldownMs + ", ignoreCooldownMs=" + this.ignoreCooldownMs + ", rejectCooldownMs=" + this.rejectCooldownMs + ", budgetMaxTokens=" + this.budgetMaxTokens + ", budgetRefillIntervalMs=" + this.budgetRefillIntervalMs + ", budgetCostSuggestion=" + this.budgetCostSuggestion + ", budgetCostQuestion=" + this.budgetCostQuestion + ", budgetCostConfirmation=" + this.budgetCostConfirmation + ", maxFingerprints=" + this.maxFingerprints + ", maxBehaviorCooldowns=" + this.maxBehaviorCooldowns + ", maxRejectCooldowns=" + this.maxRejectCooldowns + ")";
    }

    public ProactivityConfig(long globalCooldownMs, long behaviorCooldownMs, long fingerprintCooldownMs, long ignoreCooldownMs, long rejectCooldownMs, int budgetMaxTokens, long budgetRefillIntervalMs, int budgetCostSuggestion, int budgetCostQuestion, int budgetCostConfirmation, int maxFingerprints, int maxBehaviorCooldowns, int maxRejectCooldowns) {
        this.globalCooldownMs = globalCooldownMs;
        this.behaviorCooldownMs = behaviorCooldownMs;
        this.fingerprintCooldownMs = fingerprintCooldownMs;
        this.ignoreCooldownMs = ignoreCooldownMs;
        this.rejectCooldownMs = rejectCooldownMs;
        this.budgetMaxTokens = budgetMaxTokens;
        this.budgetRefillIntervalMs = budgetRefillIntervalMs;
        this.budgetCostSuggestion = budgetCostSuggestion;
        this.budgetCostQuestion = budgetCostQuestion;
        this.budgetCostConfirmation = budgetCostConfirmation;
        this.maxFingerprints = maxFingerprints;
        this.maxBehaviorCooldowns = maxBehaviorCooldowns;
        this.maxRejectCooldowns = maxRejectCooldowns;
        if (!(this.globalCooldownMs >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.behaviorCooldownMs >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.fingerprintCooldownMs >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.budgetMaxTokens >= 1)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.budgetRefillIntervalMs > 0) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ ProactivityConfig(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, int i3, int i4, int i5, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? AnnouncementWatcher.EVENT_LEAD_MS : j, (i8 & 2) != 0 ? 7200000L : j2, (i8 & 4) != 0 ? 1800000L : j3, (i8 & 8) != 0 ? 21600000L : j4, (i8 & 16) != 0 ? 43200000L : j5, (i8 & 32) != 0 ? 3 : i, (i8 & 64) != 0 ? 3600000L : j6, (i8 & 128) != 0 ? 1 : i2, (i8 & 256) != 0 ? 1 : i3, (i8 & 512) != 0 ? 1 : i4, (i8 & 1024) != 0 ? 32 : i5, (i8 & 2048) != 0 ? 64 : i6, (i8 & 4096) == 0 ? i7 : 64);
    }

    public final long getGlobalCooldownMs() {
        return this.globalCooldownMs;
    }

    public final long getBehaviorCooldownMs() {
        return this.behaviorCooldownMs;
    }

    public final long getFingerprintCooldownMs() {
        return this.fingerprintCooldownMs;
    }

    public final long getIgnoreCooldownMs() {
        return this.ignoreCooldownMs;
    }

    public final long getRejectCooldownMs() {
        return this.rejectCooldownMs;
    }

    public final int getBudgetMaxTokens() {
        return this.budgetMaxTokens;
    }

    public final long getBudgetRefillIntervalMs() {
        return this.budgetRefillIntervalMs;
    }

    public final int getBudgetCostSuggestion() {
        return this.budgetCostSuggestion;
    }

    public final int getBudgetCostQuestion() {
        return this.budgetCostQuestion;
    }

    public final int getBudgetCostConfirmation() {
        return this.budgetCostConfirmation;
    }

    public final int getMaxFingerprints() {
        return this.maxFingerprints;
    }

    public final int getMaxBehaviorCooldowns() {
        return this.maxBehaviorCooldowns;
    }

    public final int getMaxRejectCooldowns() {
        return this.maxRejectCooldowns;
    }

    /* JADX INFO: compiled from: ProactivityConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/proactive/ProactivityConfig$Companion;", "", "<init>", "()V", "DEFAULT", "Lcom/jarvis/core/proactive/ProactivityConfig;", "getDEFAULT", "()Lcom/jarvis/core/proactive/ProactivityConfig;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ProactivityConfig getDEFAULT() {
            return ProactivityConfig.DEFAULT;
        }
    }
}
