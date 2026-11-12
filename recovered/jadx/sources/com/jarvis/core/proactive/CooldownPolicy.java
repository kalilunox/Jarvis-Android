package com.jarvis.core.proactive;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.core.behavior.BehaviorFeedback;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CooldownPolicy.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J2\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\tJ2\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\tJ&\u0010\u0014\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/proactive/CooldownPolicy;", "", "config", "Lcom/jarvis/core/proactive/ProactivityConfig;", "<init>", "(Lcom/jarvis/core/proactive/ProactivityConfig;)V", "canConsider", "Lcom/jarvis/core/proactive/CooldownCheckResult;", ProactiveActionReceiver.EXTRA_PATTERN_KEY, "", "nowMillis", "", "state", "Lcom/jarvis/core/proactive/ProactivityState;", "feedback", "Lcom/jarvis/core/proactive/BehaviorFeedbackView;", ProactiveActionReceiver.EXTRA_FINGERPRINT, "afterProactiveDecision", "type", "Lcom/jarvis/core/proactive/ProactiveDecisionType;", "afterFeedback", "Lcom/jarvis/core/behavior/BehaviorFeedback;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CooldownPolicy {
    private final ProactivityConfig config;

    /* JADX INFO: compiled from: CooldownPolicy.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BehaviorFeedback.values().length];
            try {
                iArr[BehaviorFeedback.DISABLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BehaviorFeedback.IGNORE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BehaviorFeedback.ACCEPT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BehaviorFeedback.REJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CooldownPolicy() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public CooldownPolicy(ProactivityConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
    }

    public /* synthetic */ CooldownPolicy(ProactivityConfig proactivityConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ProactivityConfig.INSTANCE.getDEFAULT() : proactivityConfig);
    }

    public static /* synthetic */ CooldownCheckResult canConsider$default(CooldownPolicy cooldownPolicy, String str, long j, ProactivityState proactivityState, BehaviorFeedbackView behaviorFeedbackView, String str2, int i, Object obj) {
        String str3;
        if ((i & 16) == 0) {
            str3 = str2;
        } else {
            str3 = null;
        }
        return cooldownPolicy.canConsider(str, j, proactivityState, behaviorFeedbackView, str3);
    }

    public final CooldownCheckResult canConsider(String patternKey, long nowMillis, ProactivityState state, BehaviorFeedbackView feedback, String fingerprint) {
        Long fpUntil;
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        if (feedback.getDisabledKeys().contains(patternKey)) {
            return new CooldownCheckResult(false, ProactiveReasonCode.DISABLED, null, "disabled_in_behavior_store", 4, null);
        }
        Long ignoreUntil = feedback.getIgnoreUntil().get(patternKey);
        if (ignoreUntil != null && now < ignoreUntil.longValue()) {
            return new CooldownCheckResult(false, ProactiveReasonCode.FEEDBACK_COOLDOWN, ignoreUntil, "ignore_until");
        }
        if (feedback.getRejectedKeys().contains(patternKey)) {
            Long rejectUntil = state.getRejectCooldownUntil().get(patternKey);
            if (rejectUntil != null && now < rejectUntil.longValue()) {
                return new CooldownCheckResult(false, ProactiveReasonCode.FEEDBACK_COOLDOWN, rejectUntil, "reject_cooldown");
            }
            return new CooldownCheckResult(false, ProactiveReasonCode.FEEDBACK_COOLDOWN, null, "rejected_key", 4, null);
        }
        if (now < state.getGlobalCooldownUntilMillis()) {
            return new CooldownCheckResult(false, ProactiveReasonCode.GLOBAL_COOLDOWN, Long.valueOf(state.getGlobalCooldownUntilMillis()), "global");
        }
        Long behaviorUntil = state.getBehaviorCooldownUntil().get(patternKey);
        if (behaviorUntil != null && now < behaviorUntil.longValue()) {
            return new CooldownCheckResult(false, ProactiveReasonCode.BEHAVIOR_COOLDOWN, behaviorUntil, "behavior");
        }
        if (fingerprint != null && (fpUntil = state.getFingerprintCooldownUntil().get(fingerprint)) != null && now < fpUntil.longValue()) {
            return new CooldownCheckResult(false, ProactiveReasonCode.DUPLICATE, fpUntil, ProactiveActionReceiver.EXTRA_FINGERPRINT);
        }
        return new CooldownCheckResult(true, ProactiveReasonCode.EVALUATED, null, "clear", 4, null);
    }

    public static /* synthetic */ ProactivityState afterProactiveDecision$default(CooldownPolicy cooldownPolicy, ProactivityState proactivityState, long j, String str, ProactiveDecisionType proactiveDecisionType, String str2, int i, Object obj) {
        String str3;
        if ((i & 16) == 0) {
            str3 = str2;
        } else {
            str3 = null;
        }
        return cooldownPolicy.afterProactiveDecision(proactivityState, j, str, proactiveDecisionType, str3);
    }

    public final ProactivityState afterProactiveDecision(ProactivityState state, long nowMillis, String patternKey, ProactiveDecisionType type, String fingerprint) {
        Map<String, Long> mapBoundMap;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(type, "type");
        if (type == ProactiveDecisionType.NO_OP) {
            return state;
        }
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        ProactivityState pruned = state.pruneExpired(now, this.config);
        Map<String, Long> mapBoundMap2 = ProactivityState.INSTANCE.boundMap(MapsKt.plus(pruned.getBehaviorCooldownUntil(), TuplesKt.to(patternKey, Long.valueOf(this.config.getBehaviorCooldownMs() + now))), this.config.getMaxBehaviorCooldowns());
        Map<String, Long> fingerprintCooldownUntil = pruned.getFingerprintCooldownUntil();
        if (fingerprint == null) {
            mapBoundMap = fingerprintCooldownUntil;
        } else {
            mapBoundMap = ProactivityState.INSTANCE.boundMap(MapsKt.plus(fingerprintCooldownUntil, TuplesKt.to(fingerprint, Long.valueOf(this.config.getFingerprintCooldownMs() + now))), this.config.getMaxFingerprints());
        }
        return ProactivityState.copy$default(pruned, now, type, now + this.config.getGlobalCooldownMs(), mapBoundMap2, mapBoundMap, null, 0, 0L, 0, 0, 0, 0, 4064, null);
    }

    public final ProactivityState afterFeedback(ProactivityState state, long nowMillis, String patternKey, BehaviorFeedback feedback) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        ProactivityState pruned = state.pruneExpired(now, this.config);
        switch (WhenMappings.$EnumSwitchMapping$0[feedback.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return pruned;
            case 4:
                return ProactivityState.copy$default(pruned, 0L, null, 0L, null, null, ProactivityState.INSTANCE.boundMap(MapsKt.plus(pruned.getRejectCooldownUntil(), TuplesKt.to(patternKey, Long.valueOf(this.config.getRejectCooldownMs() + now))), this.config.getMaxRejectCooldowns()), 0, 0L, 0, 0, 0, 0, 4063, null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
