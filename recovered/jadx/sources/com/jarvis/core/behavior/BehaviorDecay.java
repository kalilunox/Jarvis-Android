package com.jarvis.core.behavior;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BehaviorDecay.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/behavior/BehaviorDecay;", "", "<init>", "()V", "DAY_MS", "", "apply", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "candidate", "nowMillis", "", "halfLifeDays", "staleBelow", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BehaviorDecay {
    private static final double DAY_MS = 8.64E7d;
    public static final BehaviorDecay INSTANCE = new BehaviorDecay();

    private BehaviorDecay() {
    }

    public static /* synthetic */ BehaviorCandidate apply$default(BehaviorDecay behaviorDecay, BehaviorCandidate behaviorCandidate, long j, double d, float f, int i, Object obj) {
        double d2;
        float f2;
        if ((i & 4) == 0) {
            d2 = d;
        } else {
            d2 = 14.0d;
        }
        if ((i & 8) == 0) {
            f2 = f;
        } else {
            f2 = 0.35f;
        }
        return behaviorDecay.apply(behaviorCandidate, j, d2, f2);
    }

    public final BehaviorCandidate apply(BehaviorCandidate candidate, long nowMillis, double halfLifeDays, float staleBelow) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        if (candidate.getStatus() == BehaviorCandidateStatus.DISABLED || candidate.getStatus() == BehaviorCandidateStatus.ACCEPTED) {
            return candidate;
        }
        double days = RangesKt.coerceAtLeast(nowMillis - candidate.getLastObservedMillis(), 0L) / DAY_MS;
        if (days < 1.0d) {
            return candidate;
        }
        float factor = (float) Math.exp((-days) / halfLifeDays);
        float next = RangesKt.coerceIn(candidate.getConfidence() * ((0.45f * factor) + 0.55f), 0.0f, 1.0f);
        BehaviorCandidateStatus status = next < staleBelow ? BehaviorCandidateStatus.STALE : candidate.getStatus();
        return candidate.withConfidence(next, status);
    }
}
