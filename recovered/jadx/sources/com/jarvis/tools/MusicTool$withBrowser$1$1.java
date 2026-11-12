package com.jarvis.tools;

import android.content.Context;
import android.os.Handler;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: MusicTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016¨\u0006\u0006"}, d2 = {"com/jarvis/tools/MusicTool$withBrowser$1$1", "Landroid/support/v4/media/MediaBrowserCompat$ConnectionCallback;", "onConnected", "", "onConnectionFailed", "onConnectionSuspended", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MusicTool$withBrowser$1$1 extends MediaBrowserCompat.ConnectionCallback {
    final /* synthetic */ Function2<MediaBrowserCompat, MediaControllerCompat, String> $block;
    final /* synthetic */ Ref.ObjectRef<MediaBrowserCompat> $browser;
    final /* synthetic */ CountDownLatch $done;
    final /* synthetic */ AtomicBoolean $finished;
    final /* synthetic */ Handler $main;
    final /* synthetic */ AtomicReference<String> $result;
    final /* synthetic */ MusicTool this$0;

    /* JADX WARN: Multi-variable type inference failed */
    MusicTool$withBrowser$1$1(Ref.ObjectRef<MediaBrowserCompat> objectRef, MusicTool $receiver, AtomicBoolean $finished, AtomicReference<String> atomicReference, CountDownLatch $done, Handler $main, Function2<? super MediaBrowserCompat, ? super MediaControllerCompat, String> function2) {
        this.$browser = objectRef;
        this.this$0 = $receiver;
        this.$finished = $finished;
        this.$result = atomicReference;
        this.$done = $done;
        this.$main = $main;
        this.$block = function2;
    }

    @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
    public void onConnected() {
        MediaBrowserCompat mediaBrowserCompat;
        Object objM392constructorimpl;
        MediaBrowserCompat mediaBrowserCompat2;
        if (this.$browser.element == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browser");
            mediaBrowserCompat = null;
        } else {
            mediaBrowserCompat = this.$browser.element;
        }
        Log.i("Jarvis.Music", "connected root=" + mediaBrowserCompat.getRoot());
        MusicTool musicTool = this.this$0;
        Ref.ObjectRef<MediaBrowserCompat> objectRef = this.$browser;
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool$withBrowser$1$1 musicTool$withBrowser$1$1 = this;
            Context context = musicTool.context;
            if (objectRef.element == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browser");
                mediaBrowserCompat2 = null;
            } else {
                mediaBrowserCompat2 = objectRef.element;
            }
            objM392constructorimpl = Result.m392constructorimpl(new MediaControllerCompat(context, mediaBrowserCompat2.getSessionToken()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        final MediaControllerCompat ctrl = (MediaControllerCompat) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
        if (ctrl == null) {
            MusicTool.withBrowser$finish(this.$finished, this.$result, this.$done, "ERR:Нет MediaSession Киона.");
            return;
        }
        final Handler handler = this.$main;
        final Function2<MediaBrowserCompat, MediaControllerCompat, String> function2 = this.$block;
        final Ref.ObjectRef<MediaBrowserCompat> objectRef2 = this.$browser;
        final AtomicBoolean atomicBoolean = this.$finished;
        final AtomicReference<String> atomicReference = this.$result;
        final CountDownLatch countDownLatch = this.$done;
        new Thread(new Runnable() { // from class: com.jarvis.tools.MusicTool$withBrowser$1$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MusicTool$withBrowser$1$1.onConnected$lambda$1(this.f$0, handler, function2, objectRef2, ctrl, atomicBoolean, atomicReference, countDownLatch);
            }
        }, "jarvis-kion-play").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onConnected$lambda$1(final MusicTool$withBrowser$1$1 this$0, Handler $main, Function2 $block, final Ref.ObjectRef $browser, MediaControllerCompat $ctrl, final AtomicBoolean $finished, final AtomicReference $result, final CountDownLatch $done) {
        Object objM392constructorimpl;
        MediaBrowserCompat mediaBrowserCompat;
        try {
            Result.Companion companion = Result.INSTANCE;
            if ($browser.element == 0) {
                Intrinsics.throwUninitializedPropertyAccessException("browser");
                mediaBrowserCompat = null;
            } else {
                mediaBrowserCompat = (MediaBrowserCompat) $browser.element;
            }
            try {
                objM392constructorimpl = Result.m392constructorimpl((String) $block.invoke(mediaBrowserCompat, $ctrl));
            } catch (Throwable th) {
                th = th;
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
            th = th2;
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            Log.e("Jarvis.Music", "play block failed", it);
            String message = it.getMessage();
            if (message == null) {
                message = "ошибка плеера";
            }
            objM392constructorimpl = "ERR:" + message;
        }
        final String out = (String) objM392constructorimpl;
        $main.post(new Runnable() { // from class: com.jarvis.tools.MusicTool$withBrowser$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MusicTool$withBrowser$1$1.onConnected$lambda$1$2(this.f$0, out, $browser, $finished, $result, $done);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onConnected$lambda$1$2(MusicTool$withBrowser$1$1 this$0, String $out, Ref.ObjectRef $browser, AtomicBoolean $finished, AtomicReference $result, CountDownLatch $done) {
        MediaBrowserCompat mediaBrowserCompat;
        try {
            Result.Companion companion = Result.INSTANCE;
            if ($browser.element == 0) {
                Intrinsics.throwUninitializedPropertyAccessException("browser");
                mediaBrowserCompat = null;
            } else {
                mediaBrowserCompat = (MediaBrowserCompat) $browser.element;
            }
            mediaBrowserCompat.disconnect();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        MusicTool.withBrowser$finish($finished, $result, $done, $out);
    }

    @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
    public void onConnectionFailed() {
        Log.w("Jarvis.Music", "connection failed");
        MusicTool.withBrowser$finish(this.$finished, this.$result, this.$done, "ERR:Кион не отвечает. Открой приложение один раз и повтори.");
    }

    @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
    public void onConnectionSuspended() {
        Log.w("Jarvis.Music", "connection suspended");
    }
}
