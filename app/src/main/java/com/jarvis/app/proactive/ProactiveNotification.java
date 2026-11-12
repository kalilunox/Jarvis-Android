package com.jarvis.app.proactive;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.R;
import com.jarvis.app.VoiceService;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.proactive.ProactiveDeliveryContract;
import com.jarvis.core.proactive.ProactiveText;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: ProactiveNotification.kt */
/* JADX INFO: loaded from: classes23.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fJ2\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0007J4\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0002J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveNotification;", "", "<init>", "()V", "BASE_ID", "", "PUBLIC_TITLE", "", "PUBLIC_TEXT", "ensureChannel", "Landroid/app/NotificationManager;", "context", "Landroid/content/Context;", "show", "", ProactiveActionReceiver.EXTRA_DELIVERY_ID, VoiceService.EXTRA_TEXT, "Lcom/jarvis/core/proactive/ProactiveText;", ProactiveActionReceiver.EXTRA_PATTERN_KEY, ProactiveActionReceiver.EXTRA_FINGERPRINT, "cancel", "", "notificationId", "action", "Landroid/app/PendingIntent;", "requestCode", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveNotification {
    private static final int BASE_ID = 4100;
    public static final ProactiveNotification INSTANCE = new ProactiveNotification();
    private static final String PUBLIC_TEXT = "Jarvis: есть предложение";
    private static final String PUBLIC_TITLE = "Jarvis";

    private ProactiveNotification() {
    }

    public final NotificationManager ensureChannel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NotificationManager manager = (NotificationManager) context.getSystemService(NotificationManager.class);
        if (manager == null) {
            return null;
        }
        NotificationChannel $this$ensureChannel_u24lambda_u240 = new NotificationChannel(ProactiveDeliveryContract.CHANNEL_ID, context.getString(R.string.proactive_channel_name), 3);
        $this$ensureChannel_u24lambda_u240.setDescription(context.getString(R.string.proactive_channel_description));
        $this$ensureChannel_u24lambda_u240.setSound(null, null);
        $this$ensureChannel_u24lambda_u240.enableVibration(false);
        $this$ensureChannel_u24lambda_u240.setShowBadge(false);
        $this$ensureChannel_u24lambda_u240.setLockscreenVisibility(0);
        manager.createNotificationChannel($this$ensureChannel_u24lambda_u240);
        return manager;
    }

    public final boolean show(Context context, String deliveryId, ProactiveText text, String patternKey, String fingerprint) {
        NotificationManager manager;
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
        Intrinsics.checkNotNullParameter(text, "text");
        if ((StringsKt.isBlank(text.getTitle()) && StringsKt.isBlank(text.getBody())) || (manager = ensureChannel(context)) == null) {
            return false;
        }
        int iRequestCode = requestCode(deliveryId, AbstractCircuitBreaker.PROPERTY_NAME);
        Intent $this$show_u24lambda_u240 = new Intent(context, (Class<?>) JarvisActivity.class);
        $this$show_u24lambda_u240.addFlags(805306368);
        Unit unit = Unit.INSTANCE;
        PendingIntent open = PendingIntent.getActivity(context, iRequestCode, $this$show_u24lambda_u240, 201326592);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ProactiveDeliveryContract.CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(text.getTitle()).setContentText(text.getBody()).setStyle(new NotificationCompat.BigTextStyle().bigText(text.getBody())).setContentIntent(open).setAutoCancel(true).setOnlyAlertOnce(true).setSilent(true).setCategory(NotificationCompat.CATEGORY_RECOMMENDATION).setVisibility(0).setPriority(0).addAction(0, context.getString(R.string.proactive_action_yes), action(context, deliveryId, patternKey, fingerprint, ProactiveActionReceiver.ACTION_YES)).addAction(0, context.getString(R.string.proactive_action_no), action(context, deliveryId, patternKey, fingerprint, ProactiveActionReceiver.ACTION_NO)).addAction(0, context.getString(R.string.proactive_action_later), action(context, deliveryId, patternKey, fingerprint, ProactiveActionReceiver.ACTION_LATER));
        Intrinsics.checkNotNullExpressionValue(builder, "addAction(...)");
        Notification publicVersion = new NotificationCompat.Builder(context, ProactiveDeliveryContract.CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(PUBLIC_TITLE).setContentText(PUBLIC_TEXT).setSilent(true).setVisibility(1).build();
        Intrinsics.checkNotNullExpressionValue(publicVersion, "build(...)");
        builder.setPublicVersion(publicVersion);
        try {
            Result.Companion companion = Result.INSTANCE;
            ProactiveNotification $this$show_u24lambda_u241 = this;
            manager.notify($this$show_u24lambda_u241.notificationId(deliveryId), builder.build());
            objM392constructorimpl = Result.m392constructorimpl(true);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        return ((Boolean) objM392constructorimpl).booleanValue();
    }

    public final void cancel(Context context, String deliveryId) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
        try {
            Result.Companion companion = Result.INSTANCE;
            ProactiveNotification $this$cancel_u24lambda_u240 = this;
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.cancel($this$cancel_u24lambda_u240.notificationId(deliveryId));
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final int notificationId(String deliveryId) {
        Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
        return (deliveryId.hashCode() & 255) + 4100;
    }

    private final PendingIntent action(Context context, String deliveryId, String patternKey, String fingerprint, String action) {
        Intent intent = new Intent(context, (Class<?>) ProactiveActionReceiver.class);
        intent.setAction(action);
        intent.putExtra(ProactiveActionReceiver.EXTRA_DELIVERY_ID, deliveryId);
        intent.putExtra(ProactiveActionReceiver.EXTRA_PATTERN_KEY, patternKey);
        intent.putExtra(ProactiveActionReceiver.EXTRA_FINGERPRINT, fingerprint);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, requestCode(deliveryId, action), intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    private final int requestCode(String deliveryId, String action) {
        return ((deliveryId.hashCode() * 31) + action.hashCode()) & Integer.MAX_VALUE;
    }
}
