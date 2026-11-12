package com.jarvis.core.memory;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryPolicy.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/memory/MemoryPolicy;", "", "minStoreConfidence", "", "minTemporaryConfidence", "<init>", "(FF)V", "decide", "Lcom/jarvis/core/memory/MemoryPolicyDecision;", "candidate", "Lcom/jarvis/core/memory/MemoryCandidate;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryPolicy {
    private final float minStoreConfidence;
    private final float minTemporaryConfidence;

    /* JADX INFO: compiled from: MemoryPolicy.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MemoryProvenance.values().length];
            try {
                iArr[MemoryProvenance.USER_STATED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MemoryProvenance.INFERRED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MemoryProvenance.OBSERVED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[MemoryKind.values().length];
            try {
                iArr2[MemoryKind.ROUTINE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[MemoryKind.EPISODIC.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[MemoryKind.PREFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[MemoryKind.SEMANTIC.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[MemoryKind.PROCEDURAL.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[MemoryKind.WORKING.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MemoryPolicy() {
        float f = 0.0f;
        this(f, f, 3, null);
    }

    public MemoryPolicy(float minStoreConfidence, float minTemporaryConfidence) {
        this.minStoreConfidence = minStoreConfidence;
        this.minTemporaryConfidence = minTemporaryConfidence;
    }

    public /* synthetic */ MemoryPolicy(float f, float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.9f : f, (i & 2) != 0 ? 0.55f : f2);
    }

    public final MemoryPolicyDecision decide(MemoryCandidate candidate) {
        MemoryDisposition disp;
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        if (candidate.getConfidence() < this.minTemporaryConfidence) {
            return new MemoryPolicyDecision(MemoryDisposition.REJECT, "low_confidence", candidate);
        }
        switch (WhenMappings.$EnumSwitchMapping$0[candidate.getProvenance().ordinal()]) {
            case 1:
                if (candidate.getConfidence() >= this.minStoreConfidence) {
                    disp = MemoryDisposition.STORE;
                } else {
                    disp = MemoryDisposition.TEMPORARY;
                }
                return new MemoryPolicyDecision(disp, "user_stated", candidate);
            case 2:
                return new MemoryPolicyDecision(MemoryDisposition.REJECT, "inferred_disabled_stage5", candidate);
            case 3:
                switch (WhenMappings.$EnumSwitchMapping$1[candidate.getKind().ordinal()]) {
                    case 1:
                        return new MemoryPolicyDecision(MemoryDisposition.TEMPORARY, "routine_hint_observed", candidate);
                    case 2:
                        return new MemoryPolicyDecision(MemoryDisposition.TEMPORARY, "episodic_observed", candidate);
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return new MemoryPolicyDecision(MemoryDisposition.TEMPORARY, "observed_temporary_only", candidate);
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
