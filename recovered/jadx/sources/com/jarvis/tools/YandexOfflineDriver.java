package com.jarvis.tools;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.jarvis.screencontrol.ScreenController;
import com.jarvis.screencontrol.UiNode;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YandexOfflineDriver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/jarvis/tools/YandexOfflineDriver;", "", "context", "Landroid/content/Context;", "started", "Lkotlin/Function0;", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "play", "Lcom/jarvis/tools/PlayerResult;", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class YandexOfflineDriver {
    public static final String PACKAGE = "ru.yandex.music";
    private static final String PLAY_LABEL = "Слушать";
    private static final long PLAY_WAIT_MS = 4000;
    private static final long POLL_MS = 300;
    private static final long SCREEN_WAIT_MS = 8000;
    private final Context context;
    private final Function0<Boolean> started;

    public YandexOfflineDriver(Context context, Function0<Boolean> started) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(started, "started");
        this.context = context;
        this.started = started;
    }

    public final PlayerResult play() throws InterruptedException {
        Object element$iv;
        UiNode it;
        PowerManager power = (PowerManager) this.context.getSystemService(PowerManager.class);
        KeyguardManager keyguard = (KeyguardManager) this.context.getSystemService(KeyguardManager.class);
        if (!power.isInteractive() || keyguard.isKeyguardLocked()) {
            return PlayerResult.LOCKED;
        }
        if (!ScreenController.INSTANCE.isAvailable()) {
            return PlayerResult.NO_ACCESS;
        }
        Intent launch = this.context.getPackageManager().getLaunchIntentForPackage(PACKAGE);
        if (launch == null) {
            return PlayerResult.FAILED;
        }
        this.context.startActivity(launch);
        long deadline = System.currentTimeMillis() + SCREEN_WAIT_MS;
        while (System.currentTimeMillis() < deadline) {
            Iterable $this$firstOrNull$iv = ScreenController.visibleNodes$default(ScreenController.INSTANCE, 0, 1, null);
            Iterator it2 = $this$firstOrNull$iv.iterator();
            do {
                if (!it2.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it2.next();
                it = (UiNode) element$iv;
            } while (!Intrinsics.areEqual(it.getContentDescription(), PLAY_LABEL));
            UiNode button = (UiNode) element$iv;
            if (button != null) {
                ScreenController.tapCenter$default(ScreenController.INSTANCE, button, false, 2, null);
                break;
            }
            Thread.sleep(POLL_MS);
        }
        long until = System.currentTimeMillis() + PLAY_WAIT_MS;
        while (System.currentTimeMillis() < until) {
            if (this.started.invoke().booleanValue()) {
                return PlayerResult.PLAYING;
            }
            Thread.sleep(POLL_MS);
        }
        return PlayerResult.FAILED;
    }
}
