package com.jarvis.core.common;

import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolDefinition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolPolicy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/jarvis/core/common/RiskBasedPolicy;", "Lcom/jarvis/core/common/ToolPolicy;", "<init>", "()V", "decide", "Lcom/jarvis/core/common/PolicyDecision;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RiskBasedPolicy implements ToolPolicy {

    /* JADX INFO: compiled from: ToolPolicy.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RiskLevel.values().length];
            try {
                iArr[RiskLevel.SAFE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RiskLevel.LOW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RiskLevel.CONFIRM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[RiskLevel.SENSITIVE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[RiskLevel.BLOCKED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.jarvis.core.common.ToolPolicy
    public PolicyDecision decide(ToolDefinition definition) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        switch (WhenMappings.$EnumSwitchMapping$0[definition.getRiskLevel().ordinal()]) {
            case 1:
            case 2:
                return PolicyDecision.Allow.INSTANCE;
            case 3:
            case 4:
                return new PolicyDecision.RequiresConfirmation("Действие «" + definition.getName() + "» требует подтверждения");
            case 5:
                return new PolicyDecision.Deny("Политика запрещает действие «" + definition.getName() + "»");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
