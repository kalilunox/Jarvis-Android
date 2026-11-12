package com.jarvis.app;

import android.content.Context;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HudFeedback.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lcom/jarvis/app/HudFeedback;", "", "<init>", "()V", "tick", "", "view", "Landroid/view/View;", "key", "beep", "context", "Landroid/content/Context;", "tone", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HudFeedback {
    public static final HudFeedback INSTANCE = new HudFeedback();

    private HudFeedback() {
    }

    public final void tick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.performHapticFeedback(4);
    }

    public final void key(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.performHapticFeedback(1);
    }

    public static /* synthetic */ void beep$default(HudFeedback hudFeedback, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 25;
        }
        hudFeedback.beep(context, i);
    }

    public final void beep(Context context, int tone) {
        int hour;
        Intrinsics.checkNotNullParameter(context, "context");
        if (JarvisSettings.INSTANCE.hudSfx(context)) {
            if (!JarvisSettings.INSTANCE.prefs(context).getBoolean("companion_quiet", false) || ((hour = Calendar.getInstance().get(11)) < 23 && hour >= 7)) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    HudFeedback hudFeedback = this;
                    final ToneGenerator gen = new ToneGenerator(3, 40);
                    gen.startTone(tone, 70);
                    Result.m392constructorimpl(Boolean.valueOf(new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.jarvis.app.HudFeedback$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            gen.release();
                        }
                    }, 180L)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th));
                }
            }
        }
    }
}
