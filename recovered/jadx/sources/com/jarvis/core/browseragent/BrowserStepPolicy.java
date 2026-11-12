package com.jarvis.core.browseragent;

import com.jarvis.core.common.model.RiskLevel;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrowserAgent.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\r"}, d2 = {"Lcom/jarvis/core/browseragent/BrowserStepPolicy;", "", "<init>", "()V", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "kind", "Lcom/jarvis/core/browseragent/BrowserStepKind;", "requiresConfirmation", "", "requiresOnline", "toolId", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BrowserStepPolicy {
    public static final BrowserStepPolicy INSTANCE = new BrowserStepPolicy();

    /* JADX INFO: compiled from: BrowserAgent.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BrowserStepKind.values().length];
            try {
                iArr[BrowserStepKind.SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BrowserStepKind.INSPECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BrowserStepKind.EXTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BrowserStepKind.VERIFY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BrowserStepKind.OPEN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[BrowserStepKind.ACT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private BrowserStepPolicy() {
    }

    public final RiskLevel riskLevel(BrowserStepKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return RiskLevel.LOW;
            case 5:
                return RiskLevel.CONFIRM;
            case 6:
                return RiskLevel.SENSITIVE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean requiresConfirmation(BrowserStepKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        return kind == BrowserStepKind.OPEN || kind == BrowserStepKind.ACT;
    }

    public final boolean requiresOnline(BrowserStepKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        return true;
    }

    public final String toolId(BrowserStepKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        String lowerCase = kind.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return "browser." + lowerCase;
    }
}
