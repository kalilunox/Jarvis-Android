package com.jarvis.app.companion;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.jarvis.core.companion.ObservationSource;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneContext;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.onboarding.PermissionsHelper;
import com.jarvis.wakeword.WakeWordEngine;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UsageStatsObserver.kt */
/* JADX INFO: loaded from: classes18.dex */
@Metadata(d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0010\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\b\u0010\u0015\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u0017"}, d2 = {"Lcom/jarvis/app/companion/UsageStatsObserver;", "", "appContext", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "handler", "Landroid/os/Handler;", "pollIntervalMs", "", "started", "", "lastFgPackage", "", "lastEmitAtElapsed", "pollRunnable", "com/jarvis/app/companion/UsageStatsObserver$pollRunnable$1", "Lcom/jarvis/app/companion/UsageStatsObserver$pollRunnable$1;", "start", "", "stop", "pollOnce", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UsageStatsObserver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "Jarvis.UsageStats";
    private static volatile UsageStatsObserver instance;
    private final Context appContext;
    private final Handler handler;
    private volatile long lastEmitAtElapsed;
    private volatile String lastFgPackage;
    private final long pollIntervalMs;
    private final UsageStatsObserver$pollRunnable$1 pollRunnable;
    private volatile boolean started;

    /* JADX WARN: Type inference failed for: r0v3, types: [com.jarvis.app.companion.UsageStatsObserver$pollRunnable$1] */
    public UsageStatsObserver(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.handler = new Handler(Looper.getMainLooper());
        this.pollIntervalMs = WakeWordEngine.DEFAULT_MAX_COMMAND_DURATION_MS;
        this.pollRunnable = new Runnable() { // from class: com.jarvis.app.companion.UsageStatsObserver$pollRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                if (this.this$0.started) {
                    this.this$0.pollOnce();
                    this.this$0.handler.postDelayed(this, this.this$0.pollIntervalMs);
                }
            }
        };
    }

    public final void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        this.handler.post(this.pollRunnable);
        Log.i(TAG, "UsageStatsObserver started");
    }

    public final void stop() {
        if (this.started) {
            this.started = false;
            this.handler.removeCallbacks(this.pollRunnable);
            Log.i(TAG, "UsageStatsObserver stopped");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pollOnce() {
        Object objM392constructorimpl;
        Object maxElem$iv;
        String it;
        if (PermissionsHelper.INSTANCE.isUsageAccessGranted(this.appContext)) {
            Object systemService = this.appContext.getSystemService("usagestats");
            UsageStatsManager usm = systemService instanceof UsageStatsManager ? (UsageStatsManager) systemService : null;
            if (usm == null) {
                return;
            }
            long end = System.currentTimeMillis();
            long begin = end - 60000;
            try {
                Result.Companion companion = Result.INSTANCE;
                UsageStatsObserver usageStatsObserver = this;
                objM392constructorimpl = Result.m392constructorimpl(usm.queryUsageStats(4, begin, end));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = null;
            }
            List stats = (List) objM392constructorimpl;
            if (stats == null) {
                stats = CollectionsKt.emptyList();
            }
            if (stats.isEmpty()) {
                return;
            }
            Iterable $this$maxByOrNull$iv = stats;
            Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
            if (iterator$iv.hasNext()) {
                maxElem$iv = iterator$iv.next();
                if (iterator$iv.hasNext()) {
                    UsageStats it2 = (UsageStats) maxElem$iv;
                    long maxValue$iv = it2.getLastTimeUsed();
                    do {
                        Object e$iv = iterator$iv.next();
                        UsageStats it3 = (UsageStats) e$iv;
                        long v$iv = it3.getLastTimeUsed();
                        if (maxValue$iv < v$iv) {
                            maxElem$iv = e$iv;
                            maxValue$iv = v$iv;
                        }
                    } while (iterator$iv.hasNext());
                }
            } else {
                maxElem$iv = null;
            }
            UsageStats top = (UsageStats) maxElem$iv;
            if (top == null || (it = top.getPackageName()) == null) {
                return;
            }
            String pkg = StringsKt.isBlank(it) ? null : it;
            if (pkg == null || Intrinsics.areEqual(pkg, this.lastFgPackage)) {
                return;
            }
            this.lastFgPackage = pkg;
            this.lastEmitAtElapsed = SystemClock.elapsedRealtime();
            PhoneContext snap = PhoneContextBus.INSTANCE.snapshot().getContext();
            if (Intrinsics.areEqual(pkg, snap.getCurrentApp())) {
                return;
            }
            if (snap.getCurrentAppSource() == ObservationSource.ACCESSIBILITY) {
                long age = System.currentTimeMillis() - snap.getCurrentAppAtMillis();
                if (0 <= age && age < 45000) {
                    return;
                }
            }
            boolean accepted = PhoneContextBus.INSTANCE.publish(new PhoneObservation(null, 0L, ObservationSource.USAGE_STATS, ObservationType.APP_FOREGROUND, pkg, null, "UsageStats FG: " + pkg, MapsKt.mapOf(TuplesKt.to("package", pkg), TuplesKt.to("last_used", String.valueOf(top.getLastTimeUsed()))), Sensitivity.NORMAL, 0.7f, 35, null));
            if (accepted) {
                Log.d(TAG, "APP_FOREGROUND " + pkg);
            } else {
                Log.d(TAG, "dropped " + pkg + " (aggregator)");
            }
        }
    }

    /* JADX INFO: compiled from: UsageStatsObserver.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/jarvis/app/companion/UsageStatsObserver$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lcom/jarvis/app/companion/UsageStatsObserver;", "ensureStarted", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UsageStatsObserver ensureStarted(Context context) {
            UsageStatsObserver it;
            Intrinsics.checkNotNullParameter(context, "context");
            UsageStatsObserver existing = UsageStatsObserver.instance;
            if (existing != null) {
                existing.start();
                return existing;
            }
            synchronized (this) {
                it = UsageStatsObserver.instance;
                if (it == null) {
                    Context applicationContext = context.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                    it = new UsageStatsObserver(applicationContext);
                    Companion companion = UsageStatsObserver.INSTANCE;
                    UsageStatsObserver.instance = it;
                    it.start();
                }
            }
            return it;
        }
    }
}
