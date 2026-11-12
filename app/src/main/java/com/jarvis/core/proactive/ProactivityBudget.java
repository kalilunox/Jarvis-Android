package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: ProactivityBudget.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0013\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/proactive/ProactivityBudget;", "", "config", "Lcom/jarvis/core/proactive/ProactivityConfig;", "<init>", "(Lcom/jarvis/core/proactive/ProactivityConfig;)V", "available", "", "state", "Lcom/jarvis/core/proactive/ProactivityState;", "nowMillis", "", "remaining", "", "withRefill", "record", "type", "Lcom/jarvis/core/proactive/ProactiveDecisionType;", "costOf", "minCost", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactivityBudget {
    private final ProactivityConfig config;

    /* JADX INFO: compiled from: ProactivityBudget.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProactiveDecisionType.values().length];
            try {
                iArr[ProactiveDecisionType.SUGGESTION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ProactiveDecisionType.QUESTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ProactiveDecisionType.CONFIRMATION_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ProactiveDecisionType.NO_OP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProactivityBudget() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ProactivityBudget(ProactivityConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
    }

    public /* synthetic */ ProactivityBudget(ProactivityConfig proactivityConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ProactivityConfig.INSTANCE.getDEFAULT() : proactivityConfig);
    }

    public final boolean available(ProactivityState state, long nowMillis) {
        Intrinsics.checkNotNullParameter(state, "state");
        return remaining(state, nowMillis) >= minCost();
    }

    public final int remaining(ProactivityState state, long nowMillis) {
        Intrinsics.checkNotNullParameter(state, "state");
        ProactivityState refilled = withRefill(state, nowMillis);
        return RangesKt.coerceIn(refilled.getBudgetTokens(), 0, this.config.getBudgetMaxTokens());
    }

    public final ProactivityState withRefill(ProactivityState state, long nowMillis) {
        Intrinsics.checkNotNullParameter(state, "state");
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        int tokens = RangesKt.coerceIn(state.getBudgetTokens(), 0, this.config.getBudgetMaxTokens());
        long last = state.getBudgetLastRefillAtMillis();
        if (last <= 0) {
            return ProactivityState.copy$default(state, 0L, null, 0L, null, null, null, RangesKt.coerceAtMost(RangesKt.coerceAtLeast(tokens, 0), this.config.getBudgetMaxTokens()), now, 0, 0, 0, 0, 3903, null);
        }
        if (now < last) {
            return ProactivityState.copy$default(state, 0L, null, 0L, null, null, null, tokens, 0L, 0, 0, 0, 0, 4031, null);
        }
        long elapsed = now - last;
        int intervals = (int) (elapsed / this.config.getBudgetRefillIntervalMs());
        if (intervals > 0) {
            tokens = RangesKt.coerceAtMost(tokens + intervals, this.config.getBudgetMaxTokens());
            last += ((long) intervals) * this.config.getBudgetRefillIntervalMs();
        }
        return ProactivityState.copy$default(state, 0L, null, 0L, null, null, null, tokens, last, 0, 0, 0, 0, 3903, null);
    }

    public final ProactivityState record(ProactivityState state, long nowMillis, ProactiveDecisionType type) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(type, "type");
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        ProactivityState s = withRefill(state, now);
        if (type == ProactiveDecisionType.NO_OP) {
            return ProactivityState.copy$default(s, 0L, null, 0L, null, null, null, 0, 0L, 0, 0, 0, RangesKt.coerceAtMost(s.getRecordedNoOpCount() + 1, DurationKt.NANOS_IN_MILLIS), 2047, null);
        }
        int cost = costOf(type);
        if (s.getBudgetTokens() < cost) {
            return s;
        }
        ProactivityState s2 = ProactivityState.copy$default(s, 0L, null, 0L, null, null, null, s.getBudgetTokens() - cost, 0L, 0, 0, 0, 0, 4031, null);
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return ProactivityState.copy$default(s2, 0L, null, 0L, null, null, null, 0, 0L, RangesKt.coerceAtMost(s2.getSuggestionCount() + 1, DurationKt.NANOS_IN_MILLIS), 0, 0, 0, 3839, null);
            case 2:
                return ProactivityState.copy$default(s2, 0L, null, 0L, null, null, null, 0, 0L, 0, RangesKt.coerceAtMost(s2.getQuestionCount() + 1, DurationKt.NANOS_IN_MILLIS), 0, 0, 3583, null);
            case 3:
                return ProactivityState.copy$default(s2, 0L, null, 0L, null, null, null, 0, 0L, 0, 0, RangesKt.coerceAtMost(s2.getConfirmationCount() + 1, DurationKt.NANOS_IN_MILLIS), 0, 3071, null);
            case 4:
                return s2;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final int costOf(ProactiveDecisionType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return this.config.getBudgetCostSuggestion();
            case 2:
                return this.config.getBudgetCostQuestion();
            case 3:
                return this.config.getBudgetCostConfirmation();
            case 4:
                return 0;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final int minCost() {
        return Math.min(this.config.getBudgetCostSuggestion(), Math.min(this.config.getBudgetCostQuestion(), this.config.getBudgetCostConfirmation()));
    }
}
