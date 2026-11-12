package com.jarvis.tools;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.screencontrol.ScreenController;
import com.jarvis.screencontrol.UiNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SoundCloudDriver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0002J\u001e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/jarvis/tools/SoundCloudDriver;", "", "context", "Landroid/content/Context;", "started", "Lkotlin/Function0;", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "playSearch", "Lcom/jarvis/tools/PlayerResult;", SearchIntents.EXTRA_QUERY, "", "waitForTrack", "Lcom/jarvis/tools/SoundCloudScreen$Row;", "leave", "", "before", "waitUntil", "timeoutMs", "", "done", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SoundCloudDriver {
    private static final long BACK_PAUSE_MS = 350;
    private static final int MAX_BACKS = 3;
    public static final String PACKAGE = "com.soundcloud.android";
    private static final long PLAY_WAIT_MS = 7000;
    private static final long POLL_MS = 250;
    private static final long SCREEN_WAIT_MS = 8000;
    public static final String SERVICE = "com.soundcloud.android.playback.players.MediaService";
    private static final String TAG = "Jarvis.Music";
    private final Context context;
    private final Function0<Boolean> started;

    public SoundCloudDriver(Context context, Function0<Boolean> started) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(started, "started");
        this.context = context;
        this.started = started;
    }

    public final PlayerResult playSearch(String query) throws InterruptedException {
        Intrinsics.checkNotNullParameter(query, "query");
        PowerManager power = (PowerManager) this.context.getSystemService(PowerManager.class);
        KeyguardManager keyguard = (KeyguardManager) this.context.getSystemService(KeyguardManager.class);
        if (!power.isInteractive() || keyguard.isKeyguardLocked()) {
            return PlayerResult.LOCKED;
        }
        if (!ScreenController.INSTANCE.isAvailable()) {
            return PlayerResult.NO_ACCESS;
        }
        String before = ScreenController.INSTANCE.activePackageName();
        Context context = this.context;
        Intent $this$playSearch_u24lambda_u240 = new Intent("android.media.action.MEDIA_PLAY_FROM_SEARCH");
        $this$playSearch_u24lambda_u240.putExtra(SearchIntents.EXTRA_QUERY, query);
        $this$playSearch_u24lambda_u240.setPackage(PACKAGE);
        $this$playSearch_u24lambda_u240.addFlags(268435456);
        context.startActivity($this$playSearch_u24lambda_u240);
        SoundCloudScreen.Row row = waitForTrack(query);
        if (row == null) {
            Log.w(TAG, "SoundCloud: нет трека в результатах");
            return PlayerResult.FAILED;
        }
        int width = this.context.getResources().getDisplayMetrics().widthPixels;
        ScreenController.tap$default(ScreenController.INSTANCE, width / 2.0f, (row.getTop() + row.getBottom()) / 2.0f, false, 4, null);
        if (!waitUntil(7000L, this.started)) {
            return PlayerResult.FAILED;
        }
        leave(before);
        return PlayerResult.PLAYING;
    }

    private final SoundCloudScreen.Row waitForTrack(String query) throws InterruptedException {
        long deadline = System.currentTimeMillis() + SCREEN_WAIT_MS;
        while (System.currentTimeMillis() < deadline) {
            if (Intrinsics.areEqual(ScreenController.INSTANCE.activePackageName(), PACKAGE)) {
                Iterable $this$map$iv = ScreenController.visibleNodes$default(ScreenController.INSTANCE, 0, 1, null);
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    UiNode it = (UiNode) item$iv$iv;
                    destination$iv$iv.add(new SoundCloudScreen.Row(it.getText(), it.getBounds().top, it.getBounds().bottom));
                }
                List rows = (List) destination$iv$iv;
                SoundCloudScreen.Row it2 = SoundCloudScreen.INSTANCE.bestTrack(rows, query);
                if (it2 != null) {
                    return it2;
                }
            }
            Thread.sleep(POLL_MS);
        }
        return null;
    }

    private final void leave(String before) throws InterruptedException {
        if (before == null || Intrinsics.areEqual(before, PACKAGE)) {
            return;
        }
        for (int i = 0; i < 3 && Intrinsics.areEqual(ScreenController.INSTANCE.activePackageName(), PACKAGE); i++) {
            ScreenController.INSTANCE.globalAction("back");
            Thread.sleep(BACK_PAUSE_MS);
        }
    }

    private final boolean waitUntil(long timeoutMs, Function0<Boolean> done) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (System.currentTimeMillis() < deadline) {
            if (done.invoke().booleanValue()) {
                return true;
            }
            Thread.sleep(POLL_MS);
        }
        return false;
    }
}
