package com.jarvis.core.proactive;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.behavior.BehaviorKind;
import com.jarvis.core.behavior.PatternFamily;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DecisionPolicy.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002J@\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/proactive/DecisionPolicy;", "", "thresholds", "Lcom/jarvis/core/proactive/DecisionPolicyThresholds;", "<init>", "(Lcom/jarvis/core/proactive/DecisionPolicyThresholds;)V", "decide", "Lcom/jarvis/core/proactive/ProactiveDecision;", "candidate", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "filter", "Lcom/jarvis/core/proactive/CandidateFilterResult;", "nowMillis", "", "isStrongActionCandidate", "", "c", "noOp", "reasonCode", "Lcom/jarvis/core/proactive/ProactiveReasonCode;", "reason", "", "notes", "", "humanReason", "code", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DecisionPolicy {
    private final DecisionPolicyThresholds thresholds;

    /* JADX INFO: compiled from: DecisionPolicy.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProactiveReasonCode.values().length];
            try {
                iArr[ProactiveReasonCode.DISABLED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ProactiveReasonCode.STALE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ProactiveReasonCode.LOW_CONFIDENCE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ProactiveReasonCode.LOW_RELEVANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ProactiveReasonCode.CONTEXT_MISMATCH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ProactiveReasonCode.CONTEXT_ALREADY_SATISFIED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ProactiveReasonCode.BUDGET_EXHAUSTED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ProactiveReasonCode.GLOBAL_COOLDOWN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ProactiveReasonCode.BEHAVIOR_COOLDOWN.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ProactiveReasonCode.FEEDBACK_COOLDOWN.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[ProactiveReasonCode.DUPLICATE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[ProactiveReasonCode.NO_CANDIDATE.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DecisionPolicy() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DecisionPolicy(DecisionPolicyThresholds thresholds) {
        Intrinsics.checkNotNullParameter(thresholds, "thresholds");
        this.thresholds = thresholds;
    }

    public /* synthetic */ DecisionPolicy(DecisionPolicyThresholds decisionPolicyThresholds, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DecisionPolicyThresholds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 511, null) : decisionPolicyThresholds);
    }

    public final ProactiveDecision decide(BehaviorCandidate candidate, CandidateFilterResult filter, long nowMillis) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(filter, "filter");
        if (!filter.getEligible()) {
            return noOp$default(this, candidate, filter, nowMillis, filter.getReasonCode(), humanReason(filter.getReasonCode()), null, 32, null);
        }
        RelevanceResult relevance = filter.getRelevance();
        float relevance2 = relevance != null ? relevance.getRelevance() : 0.0f;
        float confidence = candidate.getConfidence();
        List<String> mutableList = CollectionsKt.toMutableList((Collection) filter.getNotes());
        mutableList.add("conf=" + confidence);
        mutableList.add("rel=" + relevance2);
        if (relevance2 < this.thresholds.getMinRelevanceEligible()) {
            return noOp(candidate, filter, nowMillis, ProactiveReasonCode.LOW_RELEVANCE, "low_relevance", mutableList);
        }
        if (confidence < this.thresholds.getMinConfidenceEligible()) {
            return noOp(candidate, filter, nowMillis, ProactiveReasonCode.LOW_CONFIDENCE, "low_confidence", mutableList);
        }
        boolean confirmationOk = confidence >= this.thresholds.getMinConfidenceConfirmation() && relevance2 >= this.thresholds.getMinRelevanceConfirmation() && isStrongActionCandidate(candidate);
        if (confirmationOk) {
            return new ProactiveDecision(ProactiveDecisionType.CONFIRMATION_REQUEST, candidate.getPatternKey(), candidate.getId(), filter.getFingerprint(), ProactiveReasonCode.SELECTED, "Сильный контекстный match; нужно явное подтверждение (без выполнения).", confidence, relevance2, nowMillis, null, mutableList, MapsKt.mapOf(TuplesKt.to("tier", "confirmation")), 512, null);
        }
        boolean questionOk = relevance2 >= this.thresholds.getMinRelevanceQuestion() && confidence >= this.thresholds.getMinConfidenceQuestion() && confidence < this.thresholds.getMaxConfidenceQuestion() && !isStrongActionCandidate(candidate);
        if (questionOk) {
            return new ProactiveDecision(ProactiveDecisionType.QUESTION, candidate.getPatternKey(), candidate.getId(), filter.getFingerprint(), ProactiveReasonCode.SELECTED, "Контекст похож, но уверенность умеренная — уточняющий вопрос.", confidence, relevance2, nowMillis, null, mutableList, MapsKt.mapOf(TuplesKt.to("tier", "question")), 512, null);
        }
        boolean suggestionOk = confidence >= this.thresholds.getMinConfidenceSuggestion() && relevance2 >= this.thresholds.getMinRelevanceSuggestion();
        if (suggestionOk) {
            return new ProactiveDecision(ProactiveDecisionType.SUGGESTION, candidate.getPatternKey(), candidate.getId(), filter.getFingerprint(), ProactiveReasonCode.SELECTED, "Уместное мягкое предложение по наблюдаемому поведению.", confidence, relevance2, nowMillis, null, mutableList, MapsKt.mapOf(TuplesKt.to("tier", "suggestion")), 512, null);
        }
        return noOp(candidate, filter, nowMillis, ProactiveReasonCode.LOW_RELEVANCE, "thresholds_not_met", mutableList);
    }

    private final boolean isStrongActionCandidate(BehaviorCandidate c) {
        boolean hasTriggerFollow = c.getEntities().containsKey("trigger") && (c.getEntities().containsKey("follow") || c.getEntities().containsKey(TypedValues.TransitionType.S_TO));
        return hasTriggerFollow && (c.getKind() == BehaviorKind.ROUTINE || c.getFamily() == PatternFamily.SEQUENCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ ProactiveDecision noOp$default(DecisionPolicy decisionPolicy, BehaviorCandidate behaviorCandidate, CandidateFilterResult candidateFilterResult, long j, ProactiveReasonCode proactiveReasonCode, String str, List list, int i, Object obj) {
        List notes;
        if ((i & 32) == 0) {
            notes = list;
        } else {
            notes = candidateFilterResult.getNotes();
        }
        return decisionPolicy.noOp(behaviorCandidate, candidateFilterResult, j, proactiveReasonCode, str, notes);
    }

    private final ProactiveDecision noOp(BehaviorCandidate candidate, CandidateFilterResult filter, long nowMillis, ProactiveReasonCode reasonCode, String reason, List<String> notes) {
        ProactiveDecisionType proactiveDecisionType = ProactiveDecisionType.NO_OP;
        String patternKey = candidate.getPatternKey();
        String id = candidate.getId();
        String fingerprint = filter.getFingerprint();
        float confidence = candidate.getConfidence();
        RelevanceResult relevance = filter.getRelevance();
        float relevance2 = relevance != null ? relevance.getRelevance() : 0.0f;
        CooldownCheckResult cooldown = filter.getCooldown();
        return new ProactiveDecision(proactiveDecisionType, patternKey, id, fingerprint, reasonCode, reason, confidence, relevance2, nowMillis, cooldown != null ? cooldown.getBlockedUntilMillis() : null, notes, null, 2048, null);
    }

    private final String humanReason(ProactiveReasonCode code) {
        switch (WhenMappings.$EnumSwitchMapping$0[code.ordinal()]) {
            case 1:
                return "Behavior disabled";
            case 2:
                return "Behavior stale";
            case 3:
                return "Confidence too low";
            case 4:
                return "Relevance too low";
            case 5:
                return "Context mismatch";
            case 6:
                return "Already satisfied";
            case 7:
                return "Proactivity budget exhausted";
            case 8:
                return "Global cooldown";
            case 9:
                return "Behavior cooldown";
            case 10:
                return "Feedback cooldown";
            case 11:
                return "Duplicate fingerprint";
            case 12:
                return "No candidate";
            default:
                return code.name();
        }
    }
}
