package com.jarvis.core.proactive;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.core.aioutput.StructuredAiLimits;
import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.behavior.BehaviorCandidateStatus;
import com.jarvis.core.companion.ContextSnapshot;
import com.jarvis.core.companion.PhoneContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProactiveCandidateFilter.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ.\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0002J&\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001b0#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/jarvis/core/proactive/ProactiveCandidateFilter;", "", "matcher", "Lcom/jarvis/core/proactive/ContextMatcher;", "relevanceEvaluator", "Lcom/jarvis/core/proactive/RelevanceEvaluator;", "cooldownPolicy", "Lcom/jarvis/core/proactive/CooldownPolicy;", "budget", "Lcom/jarvis/core/proactive/ProactivityBudget;", "thresholds", "Lcom/jarvis/core/proactive/DecisionPolicyThresholds;", "<init>", "(Lcom/jarvis/core/proactive/ContextMatcher;Lcom/jarvis/core/proactive/RelevanceEvaluator;Lcom/jarvis/core/proactive/CooldownPolicy;Lcom/jarvis/core/proactive/ProactivityBudget;Lcom/jarvis/core/proactive/DecisionPolicyThresholds;)V", "filter", "Lcom/jarvis/core/proactive/CandidateFilterResult;", "candidate", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "state", "Lcom/jarvis/core/proactive/ProactivityState;", "feedback", "Lcom/jarvis/core/proactive/BehaviorFeedbackView;", "nowMillis", "", "buildFingerprint", "", "shortPkg", "pkg", "ineligible", "code", "Lcom/jarvis/core/proactive/ProactiveReasonCode;", ProactiveActionReceiver.EXTRA_FINGERPRINT, "notes", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveCandidateFilter {
    private final ProactivityBudget budget;
    private final CooldownPolicy cooldownPolicy;
    private final ContextMatcher matcher;
    private final RelevanceEvaluator relevanceEvaluator;
    private final DecisionPolicyThresholds thresholds;

    /* JADX INFO: compiled from: ProactiveCandidateFilter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BehaviorCandidateStatus.values().length];
            try {
                iArr[BehaviorCandidateStatus.DISABLED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BehaviorCandidateStatus.STALE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BehaviorCandidateStatus.REJECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ProactiveCandidateFilter() {
        this(null, null, null, null, null, 31, null);
    }

    public ProactiveCandidateFilter(ContextMatcher matcher, RelevanceEvaluator relevanceEvaluator, CooldownPolicy cooldownPolicy, ProactivityBudget budget, DecisionPolicyThresholds thresholds) {
        Intrinsics.checkNotNullParameter(matcher, "matcher");
        Intrinsics.checkNotNullParameter(relevanceEvaluator, "relevanceEvaluator");
        Intrinsics.checkNotNullParameter(cooldownPolicy, "cooldownPolicy");
        Intrinsics.checkNotNullParameter(budget, "budget");
        Intrinsics.checkNotNullParameter(thresholds, "thresholds");
        this.matcher = matcher;
        this.relevanceEvaluator = relevanceEvaluator;
        this.cooldownPolicy = cooldownPolicy;
        this.budget = budget;
        this.thresholds = thresholds;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ProactiveCandidateFilter(ContextMatcher contextMatcher, RelevanceEvaluator relevanceEvaluator, CooldownPolicy cooldownPolicy, ProactivityBudget proactivityBudget, DecisionPolicyThresholds decisionPolicyThresholds, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ContextMatcher contextMatcher2 = (i & 1) != 0 ? new ContextMatcher(null, null, 3, null) : contextMatcher;
        this(contextMatcher2, (i & 2) != 0 ? new RelevanceEvaluator(contextMatcher2, null, null, 6, null) : relevanceEvaluator, (i & 4) != 0 ? new CooldownPolicy(null, 1, null) : cooldownPolicy, (i & 8) != 0 ? new ProactivityBudget(null, 1, null) : proactivityBudget, (i & 16) != 0 ? new DecisionPolicyThresholds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 511, null) : decisionPolicyThresholds);
    }

    public final CandidateFilterResult filter(BehaviorCandidate candidate, ContextSnapshot snapshot, ProactivityState state, BehaviorFeedbackView feedback, long nowMillis) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        List notes = new ArrayList();
        String fingerprint = buildFingerprint(candidate, snapshot);
        notes.add("fp=" + fingerprint);
        if (StringsKt.isBlank(candidate.getPatternKey())) {
            return ineligible(ProactiveReasonCode.NO_CANDIDATE, fingerprint, CollectionsKt.plus((Collection<? extends String>) notes, "blank_key"));
        }
        switch (WhenMappings.$EnumSwitchMapping$0[candidate.getStatus().ordinal()]) {
            case 1:
                return ineligible(ProactiveReasonCode.DISABLED, fingerprint, CollectionsKt.plus((Collection<? extends String>) notes, "status_disabled"));
            case 2:
                return ineligible(ProactiveReasonCode.STALE, fingerprint, CollectionsKt.plus((Collection<? extends String>) notes, "status_stale"));
            case 3:
                return ineligible(ProactiveReasonCode.FEEDBACK_COOLDOWN, fingerprint, CollectionsKt.plus((Collection<? extends String>) notes, "status_rejected"));
            default:
                if (candidate.getConfidence() < this.thresholds.getMinConfidenceEligible()) {
                    return ineligible(ProactiveReasonCode.LOW_CONFIDENCE, fingerprint, CollectionsKt.plus((Collection<? extends String>) notes, "conf=" + candidate.getConfidence()));
                }
                CooldownCheckResult cooldown = this.cooldownPolicy.canConsider(candidate.getPatternKey(), nowMillis, state, feedback, fingerprint);
                if (!cooldown.getAllowed()) {
                    return new CandidateFilterResult(false, cooldown.getReasonCode(), fingerprint, null, null, cooldown, CollectionsKt.plus((Collection<? extends String>) notes, cooldown.getDetail()), 24, null);
                }
                RelevanceResult relevance = this.relevanceEvaluator.evaluate(candidate, snapshot);
                ContextMatchResult match = relevance.getMatch();
                if (match.getAlreadySatisfied() || relevance.getReasonCode() == ProactiveReasonCode.CONTEXT_ALREADY_SATISFIED) {
                    return new CandidateFilterResult(false, ProactiveReasonCode.CONTEXT_ALREADY_SATISFIED, fingerprint, match, relevance, cooldown, CollectionsKt.plus((Collection) notes, (Iterable) relevance.getNotes()));
                }
                if (!match.getMatched()) {
                    return new CandidateFilterResult(false, ProactiveReasonCode.CONTEXT_MISMATCH, fingerprint, match, relevance, cooldown, CollectionsKt.plus((Collection) notes, (Iterable) relevance.getNotes()));
                }
                if (relevance.getRelevance() < this.thresholds.getMinRelevanceEligible()) {
                    return new CandidateFilterResult(false, ProactiveReasonCode.LOW_RELEVANCE, fingerprint, match, relevance, cooldown, CollectionsKt.plus((Collection<? extends String>) notes, "rel=" + relevance.getRelevance()));
                }
                return !this.budget.available(state, nowMillis) ? new CandidateFilterResult(false, ProactiveReasonCode.BUDGET_EXHAUSTED, fingerprint, match, relevance, cooldown, CollectionsKt.plus((Collection<? extends String>) notes, "budget=0")) : new CandidateFilterResult(true, ProactiveReasonCode.EVALUATED, fingerprint, match, relevance, cooldown, CollectionsKt.plus((Collection) notes, (Iterable) relevance.getNotes()));
        }
    }

    public final String buildFingerprint(BehaviorCandidate candidate, ContextSnapshot snapshot) {
        String it;
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        PhoneContext ctx = snapshot.getContext();
        long hourBucket = snapshot.getTakenAtMillis() / 3600000;
        String it2 = ctx.getCurrentApp();
        if (it2 == null || (it = shortPkg(it2)) == null) {
            it = "-";
        }
        String hp = ctx.getHeadphonesConnected() ? StructuredAiLimits.SCHEMA_VERSION : "0";
        return CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{StringsKt.take(candidate.getPatternKey(), 96), "hp=" + hp, "app=" + it, "h=" + hourBucket}), "|", null, null, 0, null, null, 62, null);
    }

    private final String shortPkg(String pkg) {
        List parts = StringsKt.split$default((CharSequence) pkg, new char[]{'.'}, false, 0, 6, (Object) null);
        return StringsKt.take(CollectionsKt.joinToString$default(CollectionsKt.takeLast(parts, 2), ".", null, null, 0, null, null, 62, null), 40);
    }

    private final CandidateFilterResult ineligible(ProactiveReasonCode code, String fingerprint, List<String> notes) {
        return new CandidateFilterResult(false, code, fingerprint, null, null, null, notes, 56, null);
    }
}
