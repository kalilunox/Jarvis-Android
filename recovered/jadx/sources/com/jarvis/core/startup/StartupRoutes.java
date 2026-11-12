package com.jarvis.core.startup;

import kotlin.Metadata;

/* JADX INFO: compiled from: StartupRoute.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/startup/StartupRoutes;", "", "<init>", "()V", "decide", "Lcom/jarvis/core/startup/StartupRoute;", "onboardingDone", "", "style", "Lcom/jarvis/core/startup/UiStyle;", "guideDone", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StartupRoutes {
    public static final StartupRoutes INSTANCE = new StartupRoutes();

    private StartupRoutes() {
    }

    public final StartupRoute decide(boolean onboardingDone, UiStyle style, boolean guideDone) {
        if (!onboardingDone) {
            return StartupRoute.ONBOARDING;
        }
        if (style == null && !guideDone) {
            return StartupRoute.CHOOSE_UI;
        }
        if (guideDone) {
            return style == UiStyle.CLASSIC ? StartupRoute.CLASSIC : StartupRoute.MODERN;
        }
        return StartupRoute.GUIDE;
    }
}
