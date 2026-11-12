package com.jarvis.app.companion;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.app.NotificationCompat;
import com.jarvis.core.behavior.BehaviorLearningService;
import com.jarvis.core.behavior.BehaviorSnapshotCodec;
import com.jarvis.core.behavior.BehaviorStore;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CompanionBehavior.kt */
/* JADX INFO: loaded from: classes18.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0005J\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/app/companion/CompanionBehavior;", "", "<init>", "()V", NotificationCompat.CATEGORY_SERVICE, "Lcom/jarvis/core/behavior/BehaviorLearningService;", "get", "context", "Landroid/content/Context;", "persistFeedback", "", "learning", "persist", "appCtx", "store", "Lcom/jarvis/core/behavior/BehaviorStore;", "PREFS", "", "KEY_DISABLED", "KEY_REJECTED", "KEY_SNAPSHOT", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CompanionBehavior {
    public static final CompanionBehavior INSTANCE = new CompanionBehavior();
    private static final String KEY_DISABLED = "disabled_keys";
    private static final String KEY_REJECTED = "rejected_keys";
    private static final String KEY_SNAPSHOT = "behavior_snapshot_v1";
    private static final String PREFS = "jarvis_behavior_feedback";
    private static volatile BehaviorLearningService service;

    private CompanionBehavior() {
    }

    public final BehaviorLearningService get(Context context) {
        BehaviorLearningService behaviorLearningService;
        Intrinsics.checkNotNullParameter(context, "context");
        BehaviorLearningService it = service;
        if (it != null) {
            return it;
        }
        synchronized (this) {
            behaviorLearningService = service;
            if (behaviorLearningService == null) {
                final CompanionBehavior $this$get_u24lambda_u241_u240 = INSTANCE;
                final Context appCtx = context.getApplicationContext();
                final BehaviorStore store = new BehaviorStore();
                SharedPreferences prefs = appCtx.getSharedPreferences(PREFS, 0);
                String snapRaw = prefs.getString(KEY_SNAPSHOT, null);
                String str = snapRaw;
                if (!(str == null || StringsKt.isBlank(str))) {
                    store.restore(BehaviorSnapshotCodec.INSTANCE.decode(snapRaw));
                } else {
                    Set<String> stringSet = prefs.getStringSet(KEY_DISABLED, SetsKt.emptySet());
                    if (stringSet == null) {
                        stringSet = SetsKt.emptySet();
                    }
                    store.loadDisabled(stringSet);
                    Set<String> stringSet2 = prefs.getStringSet(KEY_REJECTED, SetsKt.emptySet());
                    if (stringSet2 == null) {
                        stringSet2 = SetsKt.emptySet();
                    }
                    store.loadRejected(stringSet2);
                }
                BehaviorLearningService it2 = new BehaviorLearningService(store, null, null, new Function0() { // from class: com.jarvis.app.companion.CompanionBehavior$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CompanionBehavior.get$lambda$1$0$0(this.f$0, appCtx, store);
                    }
                }, 6, null);
                service = it2;
                behaviorLearningService = it2;
            }
        }
        return behaviorLearningService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit get$lambda$1$0$0(CompanionBehavior $this_run, Context $appCtx, BehaviorStore $store) {
        Intrinsics.checkNotNull($appCtx);
        $this_run.persist($appCtx, $store);
        return Unit.INSTANCE;
    }

    public final void persistFeedback(Context context, BehaviorLearningService learning) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(learning, "learning");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        persist(applicationContext, learning.getStore());
    }

    private final void persist(Context appCtx, BehaviorStore store) {
        SharedPreferences prefs = appCtx.getSharedPreferences(PREFS, 0);
        String encoded = BehaviorSnapshotCodec.INSTANCE.encode(store.snapshot());
        prefs.edit().putString(KEY_SNAPSHOT, encoded).putStringSet(KEY_DISABLED, store.disabledKeys()).putStringSet(KEY_REJECTED, store.rejectedKeys()).apply();
    }
}
