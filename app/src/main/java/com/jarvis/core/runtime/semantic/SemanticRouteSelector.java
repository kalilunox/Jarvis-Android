package com.jarvis.core.runtime.semantic;

import com.jarvis.core.runtime.RouteDecision;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SemanticRouteSelector.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/runtime/semantic/SemanticRouteSelector;", "", "<init>", "()V", "select", "Lcom/jarvis/core/runtime/RouteDecision;", "u", "Lcom/jarvis/core/runtime/semantic/SemanticUnderstanding;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SemanticRouteSelector {
    public static final SemanticRouteSelector INSTANCE = new SemanticRouteSelector();

    /* JADX INFO: compiled from: SemanticRouteSelector.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RouteDecision.values().length];
            try {
                iArr[RouteDecision.TEMPLATE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RouteDecision.FAST_SKILL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RouteDecision.PLAN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[RouteDecision.CHAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[RouteDecision.CLARIFY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[RouteDecision.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private SemanticRouteSelector() {
    }

    public final RouteDecision select(SemanticUnderstanding u) {
        Intrinsics.checkNotNullParameter(u, "u");
        if (u.getAmbiguity() == AmbiguityLevel.HIGH || (u.getAmbiguity() == AmbiguityLevel.MEDIUM && u.getClarifyQuestion() != null)) {
            return RouteDecision.CLARIFY;
        }
        if (u.getClarity() == SemanticClarity.UNCLEAR && u.getSuggestedRoute() != RouteDecision.CHAT) {
            return RouteDecision.CLARIFY;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[u.getSuggestedRoute().ordinal()]) {
            case 1:
                if (u.getClarity() == SemanticClarity.CLEAR && u.getConfidence() >= 0.85f) {
                    return RouteDecision.TEMPLATE;
                }
                return RouteDecision.CHAT;
            case 2:
                if (u.getClarity() == SemanticClarity.CLEAR && u.getAmbiguity().compareTo(AmbiguityLevel.LOW) <= 0 && u.getConfidence() >= 0.55f && !u.getRequiredCapabilities().isEmpty()) {
                    return RouteDecision.FAST_SKILL;
                }
                if (u.getConfidence() >= 0.4f && u.getRewrittenCommand() != null) {
                    return RouteDecision.FAST_SKILL;
                }
                return RouteDecision.CLARIFY;
            case 3:
                if (u.getRequiredCapabilities().size() < 2 && u.getConstraints().isEmpty()) {
                    Iterable $this$any$iv = u.getNotes();
                    boolean z = false;
                    if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                        for (Object element$iv : $this$any$iv) {
                            String it = (String) element$iv;
                            if (StringsKt.startsWith$default(it, "steps=", false, 2, (Object) null)) {
                                z = true;
                            }
                        }
                    }
                    if (!z) {
                        return u.getRewrittenCommand() != null ? RouteDecision.FAST_SKILL : RouteDecision.PLAN;
                    }
                }
                return RouteDecision.PLAN;
            case 4:
                return RouteDecision.CHAT;
            case 5:
                return RouteDecision.CLARIFY;
            case 6:
                return u.getConfidence() < 0.35f ? RouteDecision.CLARIFY : RouteDecision.CHAT;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
