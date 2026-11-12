package com.jarvis.core.proactive;

import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.companion.ContextSnapshot;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProactiveDecisionEngine.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ0\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J4\u0010\u0018\u001a\u00020\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J(\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u001fH\u0002J(\u0010\"\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u001fH\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J \u0010'\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/jarvis/core/proactive/ProactiveDecisionEngine;", "", "filter", "Lcom/jarvis/core/proactive/ProactiveCandidateFilter;", "policy", "Lcom/jarvis/core/proactive/DecisionPolicy;", "cooldownPolicy", "Lcom/jarvis/core/proactive/CooldownPolicy;", "budget", "Lcom/jarvis/core/proactive/ProactivityBudget;", "<init>", "(Lcom/jarvis/core/proactive/ProactiveCandidateFilter;Lcom/jarvis/core/proactive/DecisionPolicy;Lcom/jarvis/core/proactive/CooldownPolicy;Lcom/jarvis/core/proactive/ProactivityBudget;)V", "evaluate", "Lcom/jarvis/core/proactive/DecisionEvaluationResult;", "candidate", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "state", "Lcom/jarvis/core/proactive/ProactivityState;", "feedback", "Lcom/jarvis/core/proactive/BehaviorFeedbackView;", "nowMillis", "", "evaluateBest", "candidates", "", "isBetterEligible", "", "a", "fa", "Lcom/jarvis/core/proactive/CandidateFilterResult;", "b", "fb", "isBetterFallback", "fallbackReasonPriority", "", "code", "Lcom/jarvis/core/proactive/ProactiveReasonCode;", "applyDecision", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveDecisionEngine {
    private final ProactivityBudget budget;
    private final CooldownPolicy cooldownPolicy;
    private final ProactiveCandidateFilter filter;
    private final DecisionPolicy policy;

    /* JADX INFO: compiled from: ProactiveDecisionEngine.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProactiveReasonCode.values().length];
            try {
                iArr[ProactiveReasonCode.CONTEXT_ALREADY_SATISFIED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ProactiveReasonCode.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ProactiveReasonCode.DUPLICATE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ProactiveReasonCode.BUDGET_EXHAUSTED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ProactiveReasonCode.GLOBAL_COOLDOWN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ProactiveReasonCode.BEHAVIOR_COOLDOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ProactiveReasonCode.FEEDBACK_COOLDOWN.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ProactiveReasonCode.CONTEXT_MISMATCH.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ProactiveReasonCode.LOW_RELEVANCE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ProactiveReasonCode.LOW_CONFIDENCE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ProactiveDecisionEngine() {
        this(null, null, null, null, 15, null);
    }

    public ProactiveDecisionEngine(ProactiveCandidateFilter filter, DecisionPolicy policy, CooldownPolicy cooldownPolicy, ProactivityBudget budget) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(cooldownPolicy, "cooldownPolicy");
        Intrinsics.checkNotNullParameter(budget, "budget");
        this.filter = filter;
        this.policy = policy;
        this.cooldownPolicy = cooldownPolicy;
        this.budget = budget;
    }

    public /* synthetic */ ProactiveDecisionEngine(ProactiveCandidateFilter proactiveCandidateFilter, DecisionPolicy decisionPolicy, CooldownPolicy cooldownPolicy, ProactivityBudget proactivityBudget, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            proactiveCandidateFilter = new ProactiveCandidateFilter(null, null, null, null, null, 31, null);
        }
        this(proactiveCandidateFilter, (i & 2) != 0 ? new DecisionPolicy(null, 1, null) : decisionPolicy, (i & 4) != 0 ? new CooldownPolicy(null, 1, null) : cooldownPolicy, (i & 8) != 0 ? new ProactivityBudget(null, 1, null) : proactivityBudget);
    }

    public final DecisionEvaluationResult evaluate(BehaviorCandidate candidate, ContextSnapshot snapshot, ProactivityState state, BehaviorFeedbackView feedback, long nowMillis) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        ProactivityState refilled = this.budget.withRefill(state, now);
        if (candidate == null || StringsKt.isBlank(candidate.getPatternKey())) {
            return new DecisionEvaluationResult(new ProactiveDecision(ProactiveDecisionType.NO_OP, null, null, null, ProactiveReasonCode.NO_CANDIDATE, "No candidate", 0.0f, 0.0f, now, null, null, null, 3790, null), this.budget.record(refilled, now, ProactiveDecisionType.NO_OP));
        }
        CandidateFilterResult filtered = this.filter.filter(candidate, snapshot, refilled, feedback, now);
        ProactiveDecision decision = this.policy.decide(candidate, filtered, now);
        ProactivityState nextState = applyDecision(refilled, decision, now);
        return new DecisionEvaluationResult(decision, nextState);
    }

    public final DecisionEvaluationResult evaluateBest(List<BehaviorCandidate> candidates, ContextSnapshot snapshot, ProactivityState state, BehaviorFeedbackView feedback, long nowMillis) {
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        ProactivityState refilled = this.budget.withRefill(state, now);
        if (candidates.isEmpty()) {
            return evaluate(null, snapshot, refilled, feedback, now);
        }
        Pair bestEligible = null;
        Pair fallback = null;
        for (BehaviorCandidate c : candidates) {
            now = now;
            CandidateFilterResult f = this.filter.filter(c, snapshot, refilled, feedback, now);
            if (f.getEligible()) {
                if (bestEligible == null || isBetterEligible(c, f, (BehaviorCandidate) bestEligible.getFirst(), (CandidateFilterResult) bestEligible.getSecond())) {
                    bestEligible = TuplesKt.to(c, f);
                }
            } else if (fallback == null || isBetterFallback(c, f, (BehaviorCandidate) fallback.getFirst(), (CandidateFilterResult) fallback.getSecond())) {
                fallback = TuplesKt.to(c, f);
            }
        }
        long now2 = now;
        Pair chosen = bestEligible == null ? fallback : bestEligible;
        if (chosen == null) {
            return evaluate(null, snapshot, refilled, feedback, now2);
        }
        ProactiveDecision decision = this.policy.decide((BehaviorCandidate) chosen.getFirst(), (CandidateFilterResult) chosen.getSecond(), now2);
        return new DecisionEvaluationResult(decision, applyDecision(refilled, decision, now2));
    }

    private final boolean isBetterEligible(BehaviorCandidate a, CandidateFilterResult fa, BehaviorCandidate b, CandidateFilterResult fb) {
        List<String> matchedSignals;
        List<String> matchedSignals2;
        RelevanceResult relevance = fa.getRelevance();
        float relA = relevance != null ? relevance.getRelevance() : 0.0f;
        RelevanceResult relevance2 = fb.getRelevance();
        float relB = relevance2 != null ? relevance2.getRelevance() : 0.0f;
        int relCmp = Float.compare(relA, relB);
        if (relCmp != 0) {
            return relCmp > 0;
        }
        int confCmp = Float.compare(a.getConfidence(), b.getConfidence());
        if (confCmp != 0) {
            return confCmp > 0;
        }
        ContextMatchResult match = fa.getMatch();
        float matchA = match != null ? match.getScore() : 0.0f;
        ContextMatchResult match2 = fb.getMatch();
        float matchB = match2 != null ? match2.getScore() : 0.0f;
        int matchCmp = Float.compare(matchA, matchB);
        if (matchCmp != 0) {
            return matchCmp > 0;
        }
        ContextMatchResult match3 = fa.getMatch();
        int sigA = (match3 == null || (matchedSignals2 = match3.getMatchedSignals()) == null) ? 0 : matchedSignals2.size();
        ContextMatchResult match4 = fb.getMatch();
        int sigB = (match4 == null || (matchedSignals = match4.getMatchedSignals()) == null) ? 0 : matchedSignals.size();
        int sigCmp = Intrinsics.compare(sigA, sigB);
        if (sigCmp != 0) {
            return sigCmp > 0;
        }
        return a.getPatternKey().compareTo(b.getPatternKey()) < 0;
    }

    private final boolean isBetterFallback(BehaviorCandidate a, CandidateFilterResult fa, BehaviorCandidate b, CandidateFilterResult fb) {
        int prioA = fallbackReasonPriority(fa.getReasonCode());
        int prioB = fallbackReasonPriority(fb.getReasonCode());
        if (prioA != prioB) {
            return prioA < prioB;
        }
        return a.getPatternKey().compareTo(b.getPatternKey()) < 0;
    }

    private final int fallbackReasonPriority(ProactiveReasonCode code) {
        switch (WhenMappings.$EnumSwitchMapping$0[code.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
            case 6:
            case 7:
                return 4;
            case 8:
                return 5;
            case 9:
                return 6;
            case 10:
                return 7;
            default:
                return 8;
        }
    }

    private final ProactivityState applyDecision(ProactivityState state, ProactiveDecision decision, long nowMillis) {
        ProactivityState next = this.budget.record(state, nowMillis, decision.getType());
        if (decision.getType() != ProactiveDecisionType.NO_OP) {
            String behaviorPatternKey = decision.getBehaviorPatternKey();
            if (!(behaviorPatternKey == null || StringsKt.isBlank(behaviorPatternKey))) {
                return this.cooldownPolicy.afterProactiveDecision(next, nowMillis, decision.getBehaviorPatternKey(), decision.getType(), decision.getFingerprint());
            }
            return next;
        }
        return next;
    }
}
