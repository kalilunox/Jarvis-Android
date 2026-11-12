package com.jarvis.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.startup.StartupRoute;
import com.jarvis.core.startup.StartupRoutes;
import com.jarvis.core.startup.UiStyle;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UiMode.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/app/UiMode;", "", "<init>", "()V", "KEY", "", "EXTRA_FORCE_MODERN", "EXTRA_OPEN_SECTION", "style", "Lcom/jarvis/core/startup/UiStyle;", "context", "Landroid/content/Context;", "isClassic", "", "set", "", "route", "Lcom/jarvis/core/startup/StartupRoute;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "stepIntent", "Landroid/content/Intent;", "homeIntent", "continueFrom", "switchTo", "Landroid/app/Activity;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UiMode {
    public static final String EXTRA_FORCE_MODERN = "force_modern";
    public static final String EXTRA_OPEN_SECTION = "open_section";
    public static final UiMode INSTANCE = new UiMode();
    private static final String KEY = "ui_mode";

    /* JADX INFO: compiled from: UiMode.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StartupRoute.values().length];
            try {
                iArr[StartupRoute.ONBOARDING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StartupRoute.CHOOSE_UI.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[StartupRoute.GUIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[StartupRoute.MODERN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[StartupRoute.CLASSIC.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private UiMode() {
    }

    public final UiStyle style(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return UiStyle.INSTANCE.of(JarvisSettings.INSTANCE.prefs(context).getString(KEY, null));
    }

    public final boolean isClassic(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return style(context) == UiStyle.CLASSIC;
    }

    public final void set(Context context, UiStyle style) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(style, "style");
        JarvisSettings.INSTANCE.prefs(context).edit().putString(KEY, style.getId()).apply();
    }

    public final StartupRoute route(AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return StartupRoutes.INSTANCE.decide(!OnboardingActivity.INSTANCE.needsOnboarding(activity), style(activity), !UsageGuideActivity.INSTANCE.needsGuide(activity));
    }

    public final Intent stepIntent(Context context, StartupRoute route) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(route, "route");
        switch (WhenMappings.$EnumSwitchMapping$0[route.ordinal()]) {
            case 1:
                return new Intent(context, (Class<?>) OnboardingActivity.class);
            case 2:
                return new Intent(context, (Class<?>) InterfaceChoiceActivity.class);
            case 3:
                return new Intent(context, (Class<?>) UsageGuideActivity.class);
            case 4:
            case 5:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final Intent homeIntent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Intent(context, (Class<?>) (isClassic(context) ? DashboardActivity.class : JarvisActivity.class));
    }

    public final void continueFrom(AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intent next = stepIntent(activity, route(activity));
        if (next == null) {
            next = homeIntent(activity).addFlags(268468224);
            Intrinsics.checkNotNullExpressionValue(next, "addFlags(...)");
        }
        activity.startActivity(next);
        activity.finish();
    }

    public final void switchTo(Activity activity, UiStyle style) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(style, "style");
        set(activity, style);
        activity.startActivity(homeIntent(activity).addFlags(268468224));
        activity.finish();
    }
}
