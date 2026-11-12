package com.jarvis.core.verification;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerificationModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\n¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/verification/ExpectedEffects;", "", "<init>", "()V", "forTool", "", "Lcom/jarvis/core/verification/ExpectedEffect;", "toolId", "", "args", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ExpectedEffects {
    public static final ExpectedEffects INSTANCE = new ExpectedEffects();

    private ExpectedEffects() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List forTool$default(ExpectedEffects expectedEffects, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        return expectedEffects.forTool(str, map);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final List<ExpectedEffect> forTool(String toolId, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(args, "args");
        switch (toolId.hashCode()) {
            case -1183073498:
                if (toolId.equals("flashlight")) {
                    String action = args.get("action");
                    String value = "on";
                    if (action == null) {
                        action = "on";
                    }
                    if (Intrinsics.areEqual(action, "off")) {
                        value = "off";
                    } else if (Intrinsics.areEqual(action, "toggle")) {
                        value = "unknown";
                    }
                    if (Intrinsics.areEqual(value, "unknown")) {
                        return CollectionsKt.emptyList();
                    }
                    return CollectionsKt.listOf(new ExpectedEffect("flashlight", value, false, 4, null));
                }
                break;
            case -810883302:
                if (toolId.equals("volume")) {
                    String action2 = args.get("action");
                    if (action2 == null) {
                        action2 = "up";
                    }
                    return CollectionsKt.listOf(new ExpectedEffect("volume_action_applied", action2, false));
                }
                break;
            case 648162385:
                if (toolId.equals("brightness")) {
                    return CollectionsKt.listOf(new ExpectedEffect("brightness_changed", "true", false));
                }
                break;
        }
        return CollectionsKt.emptyList();
    }
}
