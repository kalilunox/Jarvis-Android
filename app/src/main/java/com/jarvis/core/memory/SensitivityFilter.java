package com.jarvis.core.memory;

import com.jarvis.core.companion.Sensitivity;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SensitivityFilter.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/memory/SensitivityFilter;", "", "<init>", "()V", "filter", "Lcom/jarvis/core/memory/MemoryPolicyDecision;", "candidate", "Lcom/jarvis/core/memory/MemoryCandidate;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SensitivityFilter {

    /* JADX INFO: compiled from: SensitivityFilter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Sensitivity.values().length];
            try {
                iArr[Sensitivity.SECRET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Sensitivity.PRIVATE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Sensitivity.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Sensitivity.PUBLIC.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final MemoryPolicyDecision filter(MemoryCandidate candidate) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        switch (WhenMappings.$EnumSwitchMapping$0[candidate.getSensitivity().ordinal()]) {
            case 1:
                return new MemoryPolicyDecision(MemoryDisposition.REJECT, "secret_blocked", candidate);
            case 2:
            case 3:
            case 4:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
