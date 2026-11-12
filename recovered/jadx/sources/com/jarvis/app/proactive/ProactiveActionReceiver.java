package com.jarvis.app.proactive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProactiveActionReceiver.kt */
/* JADX INFO: loaded from: classes23.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveActionReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveActionReceiver extends BroadcastReceiver {
    public static final String ACTION_LATER = "com.jarvis.assistant.PROACTIVE_LATER";
    public static final String ACTION_NO = "com.jarvis.assistant.PROACTIVE_NO";
    public static final String ACTION_YES = "com.jarvis.assistant.PROACTIVE_YES";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String EXTRA_DELIVERY_ID = "deliveryId";
    public static final String EXTRA_FINGERPRINT = "fingerprint";
    public static final String EXTRA_PATTERN_KEY = "patternKey";
    private static final String TAG = "Jarvis.ProactiveDelivery";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String deliveryId;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action == null || (deliveryId = intent.getStringExtra(EXTRA_DELIVERY_ID)) == null) {
            return;
        }
        String patternKey = intent.getStringExtra(EXTRA_PATTERN_KEY);
        String fingerprint = intent.getStringExtra(EXTRA_FINGERPRINT);
        Companion companion = INSTANCE;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        companion.perform(applicationContext, action, deliveryId, patternKey, fingerprint);
    }

    /* JADX INFO: compiled from: ProactiveActionReceiver.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveActionReceiver$Companion;", "", "<init>", "()V", "TAG", "", "perform", "", "context", "Landroid/content/Context;", "action", ProactiveActionReceiver.EXTRA_DELIVERY_ID, ProactiveActionReceiver.EXTRA_PATTERN_KEY, ProactiveActionReceiver.EXTRA_FINGERPRINT, "ACTION_YES", "ACTION_NO", "ACTION_LATER", "EXTRA_DELIVERY_ID", "EXTRA_PATTERN_KEY", "EXTRA_FINGERPRINT", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final void perform(Context context, String action, String deliveryId, String patternKey, String fingerprint) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(action, "action");
            Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
            Context app = context.getApplicationContext();
            Log.i(ProactiveActionReceiver.TAG, "receive action=" + action + " id=" + deliveryId + " key=" + (patternKey == null ? "—" : patternKey) + " fp=" + (fingerprint != null ? fingerprint : "—"));
            ProactiveDelivery proactiveDelivery = ProactiveDelivery.INSTANCE;
            Intrinsics.checkNotNull(app);
            proactiveDelivery.handleAction(app, action, deliveryId, patternKey, fingerprint);
            switch (action.hashCode()) {
                case -1807779079:
                    if (!action.equals(ProactiveActionReceiver.ACTION_NO)) {
                        return;
                    }
                    break;
                case -953799116:
                    if (!action.equals(ProactiveActionReceiver.ACTION_LATER)) {
                        return;
                    }
                    break;
                case -206566257:
                    if (!action.equals(ProactiveActionReceiver.ACTION_YES)) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            ProactiveNotification.INSTANCE.cancel(app, deliveryId);
        }
    }
}
