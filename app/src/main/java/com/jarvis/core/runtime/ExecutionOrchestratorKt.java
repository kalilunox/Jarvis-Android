package com.jarvis.core.runtime;

import com.jarvis.core.verification.VerificationResult;
import com.jarvis.core.verification.VerificationStatus;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExecutionOrchestrator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"traceLabel", "", "Lcom/jarvis/core/verification/VerificationResult;", "core_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ExecutionOrchestratorKt {

    /* JADX INFO: compiled from: ExecutionOrchestrator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VerificationStatus.values().length];
            try {
                iArr[VerificationStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VerificationStatus.PARTIAL_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VerificationStatus.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[VerificationStatus.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[VerificationStatus.RETRYABLE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[VerificationStatus.IRREVERSIBLE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String traceLabel(VerificationResult $this$traceLabel) {
        Intrinsics.checkNotNullParameter($this$traceLabel, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$traceLabel.getStatus().ordinal()]) {
            case 1:
                return "SUCCESS";
            case 2:
                return "PARTIAL";
            case 3:
                return "FAILURE";
            case 4:
                return "UNKNOWN";
            case 5:
                return "RETRYABLE";
            case 6:
                return "IRREVERSIBLE";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
