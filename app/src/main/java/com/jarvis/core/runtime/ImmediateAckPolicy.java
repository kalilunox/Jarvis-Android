package com.jarvis.core.runtime;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LatencyUx.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/runtime/ImmediateAckPolicy;", "", "<init>", "()V", "slowChatHints", "", "", "acknowledgementFor", "route", "Lcom/jarvis/core/runtime/RouteDecision;", "userText", "estimatedAiMs", "", "(Lcom/jarvis/core/runtime/RouteDecision;Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/String;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ImmediateAckPolicy {
    public static final ImmediateAckPolicy INSTANCE = new ImmediateAckPolicy();
    private static final List<String> slowChatHints = CollectionsKt.listOf((Object[]) new String[]{"объясни", "расскажи", "подумай", "сравни", "почему", "как работает", "подробно", "разверн", "анализ"});

    /* JADX INFO: compiled from: LatencyUx.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RouteDecision.values().length];
            try {
                iArr[RouteDecision.FAST_SKILL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RouteDecision.TEMPLATE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RouteDecision.CLARIFY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[RouteDecision.CHAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[RouteDecision.PLAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[RouteDecision.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ImmediateAckPolicy() {
    }

    public static /* synthetic */ String acknowledgementFor$default(ImmediateAckPolicy immediateAckPolicy, RouteDecision routeDecision, String str, Long l, int i, Object obj) {
        if ((i & 4) != 0) {
            l = null;
        }
        return immediateAckPolicy.acknowledgementFor(routeDecision, str, l);
    }

    public final String acknowledgementFor(RouteDecision route, String userText, Long estimatedAiMs) {
        boolean z;
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(userText, "userText");
        switch (WhenMappings.$EnumSwitchMapping$0[route.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return null;
            default:
                String lowerCase = userText.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String t = StringsKt.trim((CharSequence) lowerCase).toString();
                if (t.length() < 24 && estimatedAiMs == null) {
                    return null;
                }
                Iterable $this$any$iv = slowChatHints;
                boolean z2 = true;
                if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                    z = false;
                } else {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object element$iv = it.next();
                            String it2 = (String) element$iv;
                            if (StringsKt.contains$default((CharSequence) t, (CharSequence) it2, false, 2, (Object) null)) {
                                z = true;
                            }
                        } else {
                            z = false;
                        }
                    }
                }
                if (!z && t.length() < 80 && (estimatedAiMs == null || estimatedAiMs.longValue() < 2500)) {
                    z2 = false;
                }
                boolean looksHeavy = z2;
                if (!looksHeavy) {
                    return null;
                }
                switch (WhenMappings.$EnumSwitchMapping$0[route.ordinal()]) {
                    case 4:
                    case 5:
                    case 6:
                        return "Секунду…";
                    default:
                        return null;
                }
        }
    }
}
