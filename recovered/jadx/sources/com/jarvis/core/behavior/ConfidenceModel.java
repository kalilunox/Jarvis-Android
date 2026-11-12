package com.jarvis.core.behavior;

import com.jarvis.core.companion.ObservationSource;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ConfidenceModel.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u0005J%\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/behavior/ConfidenceModel;", "", "<init>", "()V", "HARD_CAP", "", "SINGLE_EVENT_MAX", "score", "evidenceCount", "", "uniqueDays", "lastObservedMillis", "", "nowMillis", "sourceReliability", "negativeEvidenceCount", "regularityScore", "merge", "previous", "fresh", "newEvidenceAdded", "", "(Ljava/lang/Float;FZ)F", "sourceWeight", "source", "Lcom/jarvis/core/companion/ObservationSource;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConfidenceModel {
    public static final float HARD_CAP = 0.92f;
    public static final ConfidenceModel INSTANCE = new ConfidenceModel();
    public static final float SINGLE_EVENT_MAX = 0.22f;

    /* JADX INFO: compiled from: ConfidenceModel.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ObservationSource.values().length];
            try {
                iArr[ObservationSource.ACCESSIBILITY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ObservationSource.SYSTEM_BROADCAST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ObservationSource.USAGE_STATS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ObservationSource.NOTIFICATION_LISTENER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ObservationSource.VOICE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ObservationSource.TOOL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ObservationSource.INTERNAL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ConfidenceModel() {
    }

    public final float score(int evidenceCount, int uniqueDays, long lastObservedMillis, long nowMillis, float sourceReliability, int negativeEvidenceCount, float regularityScore) {
        if (evidenceCount <= 0) {
            return 0.0f;
        }
        if (evidenceCount == 1) {
            return Math.min(0.22f, (0.05f * sourceReliability) + 0.12f);
        }
        float freq = RangesKt.coerceIn((float) (Math.log(((double) evidenceCount) + 1.0d) / Math.log(21.0d)), 0.0f, 1.0f);
        double daysSince = RangesKt.coerceAtLeast(nowMillis - lastObservedMillis, 0L) / 8.64E7d;
        float recency = RangesKt.coerceIn((float) Math.exp((-daysSince) / 10.0d), 0.0f, 1.0f);
        float daySpread = Math.min(1.0f, uniqueDays / Math.max(1.0f, Math.min(evidenceCount, 7)));
        float negativePenalty = RangesKt.coerceAtMost(negativeEvidenceCount * 0.1f, 0.55f);
        float raw = (0.34f * freq) + (0.22f * recency) + (0.18f * daySpread) + (RangesKt.coerceIn(regularityScore, 0.0f, 1.0f) * 0.16f) + (RangesKt.coerceIn(sourceReliability, 0.0f, 1.0f) * 0.1f);
        return RangesKt.coerceIn(raw - negativePenalty, 0.0f, 0.92f);
    }

    public final float merge(Float previous, float fresh, boolean newEvidenceAdded) {
        float f = RangesKt.coerceIn(fresh, 0.0f, 0.92f);
        if (previous == null) {
            return f;
        }
        float p = RangesKt.coerceIn(previous.floatValue(), 0.0f, 0.92f);
        if (!newEvidenceAdded) {
            return RangesKt.coerceIn(Math.min(p, f), 0.0f, 0.92f);
        }
        float grown = Math.max(p, (0.65f * p) + (0.35f * f));
        return RangesKt.coerceIn(Math.max(grown, 0.85f * f), 0.0f, 0.92f);
    }

    public final float sourceWeight(ObservationSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        switch (WhenMappings.$EnumSwitchMapping$0[source.ordinal()]) {
            case 1:
                return 0.9f;
            case 2:
                return 0.85f;
            case 3:
                return 0.65f;
            case 4:
                return 0.55f;
            case 5:
            case 6:
            case 7:
                return 0.7f;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
