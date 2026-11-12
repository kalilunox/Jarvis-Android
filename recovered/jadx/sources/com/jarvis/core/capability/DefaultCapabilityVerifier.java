package com.jarvis.core.capability;

import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CapabilityExecutor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/jarvis/core/capability/DefaultCapabilityVerifier;", "Lcom/jarvis/core/capability/CapabilityVerifier;", "<init>", "()V", "verify", "Lcom/jarvis/core/capability/VerificationOutcome;", "capability", "Lcom/jarvis/core/capability/CapabilityDescriptor;", "call", "Lcom/jarvis/core/capability/CapabilityCall;", "toolResult", "Lcom/jarvis/core/common/model/ToolResult;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultCapabilityVerifier implements CapabilityVerifier {

    /* JADX INFO: compiled from: CapabilityExecutor.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToolResultStatus.values().length];
            try {
                iArr[ToolResultStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToolResultStatus.NEEDS_CONFIRMATION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToolResultStatus.DENIED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ToolResultStatus.OFFLINE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ToolResultStatus.UNAVAILABLE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ToolResultStatus.AMBIGUOUS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ToolResultStatus.FAILED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.jarvis.core.capability.CapabilityVerifier
    public VerificationOutcome verify(CapabilityDescriptor capability, CapabilityCall call, ToolResult toolResult) {
        Intrinsics.checkNotNullParameter(capability, "capability");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(toolResult, "toolResult");
        switch (WhenMappings.$EnumSwitchMapping$0[toolResult.getStatus().ordinal()]) {
            case 1:
                return new VerificationOutcome(true, CollectionsKt.listOf((Object[]) new String[]{"tool_success", "capability=" + capability.getId()}));
            case 2:
                return new VerificationOutcome(false, CollectionsKt.listOf("needs_confirmation"));
            case 3:
                return new VerificationOutcome(false, CollectionsKt.listOf("denied"));
            case 4:
                return new VerificationOutcome(false, CollectionsKt.listOf((Object[]) new String[]{"offline", "onlineRequirement=" + capability.getOnlineRequirement()}));
            case 5:
                return new VerificationOutcome(false, CollectionsKt.listOf((Object[]) new String[]{"unavailable", "availability=" + capability.getAvailability()}));
            case 6:
                return new VerificationOutcome(false, CollectionsKt.listOf("ambiguous"));
            case 7:
                String[] strArr = new String[2];
                strArr[0] = "failed";
                String message = toolResult.getMessage();
                if (message == null) {
                    message = "";
                }
                strArr[1] = StringsKt.take(message, 80);
                return new VerificationOutcome(false, CollectionsKt.listOf((Object[]) strArr));
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
