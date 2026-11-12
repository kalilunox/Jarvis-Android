package com.jarvis.core.proactive;

import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.behavior.BehaviorCandidateStatus;
import com.jarvis.core.behavior.BehaviorKind;
import com.jarvis.core.companion.ContextSnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RelevanceEvaluator.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u000eH\u0002J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/proactive/RelevanceEvaluator;", "", "matcher", "Lcom/jarvis/core/proactive/ContextMatcher;", "weights", "Lcom/jarvis/core/proactive/RelevanceWeights;", "clock", "Lkotlin/Function0;", "", "<init>", "(Lcom/jarvis/core/proactive/ContextMatcher;Lcom/jarvis/core/proactive/RelevanceWeights;Lkotlin/jvm/functions/Function0;)V", "evaluate", "Lcom/jarvis/core/proactive/RelevanceResult;", "candidate", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "recencyFactor", "", "lastObservedMillis", "nowMillis", "statusFactor", "c", "kindFactor", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelevanceEvaluator {
    private final Function0<Long> clock;
    private final ContextMatcher matcher;
    private final RelevanceWeights weights;

    /* JADX INFO: compiled from: RelevanceEvaluator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BehaviorCandidateStatus.values().length];
            try {
                iArr[BehaviorCandidateStatus.SUGGEST_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BehaviorCandidateStatus.ACCEPTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BehaviorCandidateStatus.REJECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BehaviorCandidateStatus.IGNORED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BehaviorCandidateStatus.DISABLED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[BehaviorCandidateStatus.STALE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public RelevanceEvaluator() {
        this(null, null, null, 7, null);
    }

    public RelevanceEvaluator(ContextMatcher matcher, RelevanceWeights weights, Function0<Long> clock) {
        Intrinsics.checkNotNullParameter(matcher, "matcher");
        Intrinsics.checkNotNullParameter(weights, "weights");
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.matcher = matcher;
        this.weights = weights;
        this.clock = clock;
    }

    public /* synthetic */ RelevanceEvaluator(ContextMatcher contextMatcher, RelevanceWeights relevanceWeights, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ContextMatcher(null, null, 3, null) : contextMatcher, (i & 2) != 0 ? new RelevanceWeights(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : relevanceWeights, (i & 4) != 0 ? new Function0() { // from class: com.jarvis.core.proactive.RelevanceEvaluator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0);
    }

    public final RelevanceResult evaluate(BehaviorCandidate candidate, ContextSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        ContextMatchResult match = this.matcher.match(candidate, snapshot);
        List notes = new ArrayList();
        if (match.getAlreadySatisfied()) {
            notes.add("already_satisfied");
            return new RelevanceResult(0.0f, match, ProactiveReasonCode.CONTEXT_ALREADY_SATISFIED, notes);
        }
        if (candidate.getStatus() == BehaviorCandidateStatus.DISABLED || candidate.getStatus() == BehaviorCandidateStatus.STALE) {
            notes.add("status=" + candidate.getStatus());
            return new RelevanceResult(0.0f, ContextMatchResult.copy$default(match, false, 0.0f, null, null, false, 30, null), candidate.getStatus() == BehaviorCandidateStatus.DISABLED ? ProactiveReasonCode.DISABLED : ProactiveReasonCode.STALE, notes);
        }
        RelevanceWeights w = this.weights.normalized();
        float contextPart = match.getScore();
        float recencyPart = recencyFactor(candidate.getLastObservedMillis(), snapshot.getTakenAtMillis());
        float statusPart = statusFactor(candidate);
        float kindPart = kindFactor(candidate);
        float relevance = RangesKt.coerceIn((w.getContextMatch() * contextPart) + (w.getRecency() * recencyPart) + (w.getStatusBoost() * statusPart) + (w.getKindBoost() * kindPart), 0.0f, 1.0f);
        if (!match.getMatched()) {
            relevance = RangesKt.coerceIn(relevance * 0.35f, 0.0f, 0.45f);
            notes.add("context_not_matched");
        }
        String str = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(contextPart)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        notes.add("ctx=" + str);
        String str2 = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(recencyPart)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        notes.add("rec=" + str2);
        List list = notes;
        Iterable $this$map$iv = match.getMatchedSignals();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add("m:" + it);
        }
        CollectionsKt.addAll(list, (List) destination$iv$iv);
        List list2 = notes;
        Iterable $this$map$iv2 = match.getMismatchSignals();
        int $i$f$map = 0;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            Iterable $this$map$iv3 = $this$map$iv2;
            String it2 = (String) item$iv$iv2;
            destination$iv$iv2.add("x:" + it2);
            $this$map$iv2 = $this$map$iv3;
            $i$f$map = $i$f$map;
            w = w;
        }
        CollectionsKt.addAll(list2, (List) destination$iv$iv2);
        ProactiveReasonCode reason = (match.getMatched() || match.getScore() >= 0.25f) ? relevance < 0.35f ? ProactiveReasonCode.LOW_RELEVANCE : ProactiveReasonCode.EVALUATED : ProactiveReasonCode.CONTEXT_MISMATCH;
        return new RelevanceResult(relevance, match, reason, notes);
    }

    private final float recencyFactor(long lastObservedMillis, long nowMillis) {
        double days = RangesKt.coerceAtLeast(nowMillis - lastObservedMillis, 0L) / 8.64E7d;
        return RangesKt.coerceIn((float) Math.exp((-days) / 14.0d), 0.0f, 1.0f);
    }

    private final float statusFactor(BehaviorCandidate c) {
        switch (WhenMappings.$EnumSwitchMapping$0[c.getStatus().ordinal()]) {
            case 1:
                return 1.0f;
            case 2:
                return 0.7f;
            case 3:
                return 0.35f;
            case 4:
                return 0.5f;
            case 5:
            case 6:
                return 0.0f;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final float kindFactor(BehaviorCandidate c) {
        return c.getKind() == BehaviorKind.ROUTINE ? 1.0f : 0.75f;
    }
}
