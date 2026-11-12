package com.jarvis.screencontrol;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.VoiceService;
import com.jarvis.core.announce.IncomingMessage;
import com.jarvis.core.companion.ObservationSource;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.privacy.SensitivityClassifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: JarvisNotificationListenerService.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\u0012\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u000e\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u0016\u001a\u00020\tJ\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0005J\u0006\u0010\u001b\u001a\u00020\u0014J\u0016\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/jarvis/screencontrol/JarvisNotificationListenerService;", "Landroid/service/notification/NotificationListenerService;", "<init>", "()V", "lastPostedKey", "", "lastPostedAtElapsed", "", "onListenerConnected", "", "onListenerDisconnected", "onNotificationPosted", "sbn", "Landroid/service/notification/StatusBarNotification;", "onNotificationRemoved", "notifyMessage", "info", "Lcom/jarvis/screencontrol/NotificationInfo;", "publishToBus", "posted", "", "shouldIgnore", "refresh", "list", "", "dismiss", "key", "clearAll", "sendAction", "actionIndex", "", "replyText", VoiceService.EXTRA_TEXT, "keyOf", "toInfo", "Companion", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisNotificationListenerService extends NotificationListenerService {
    private static final String TAG = "Jarvis.NLS";
    private static volatile JarvisNotificationListenerService instance;
    private static volatile String lastAnnouncedKey;
    private static volatile Function2<? super String, ? super IncomingMessage, Unit> messageListener;
    private volatile long lastPostedAtElapsed;
    private volatile String lastPostedKey;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final JarvisNotificationListenerService$Companion$cache$1 cache = new JarvisNotificationListenerService$Companion$cache$1();

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        instance = this;
        Log.i(TAG, "Notification listener connected");
        refresh();
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerDisconnected() {
        if (instance == this) {
            instance = null;
        }
        Log.i(TAG, "Notification listener disconnected");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null) {
            return;
        }
        NotificationInfo info = toInfo(sbn);
        synchronized (cache) {
            cache.put(keyOf(sbn), info);
            Unit unit = Unit.INSTANCE;
        }
        publishToBus(sbn, true);
        notifyMessage(sbn, info);
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(StatusBarNotification sbn) {
        if (sbn == null) {
            return;
        }
        synchronized (cache) {
        }
        publishToBus(sbn, false);
    }

    private final void notifyMessage(StatusBarNotification sbn, NotificationInfo info) {
        Function2<? super String, ? super IncomingMessage, Unit> function2 = messageListener;
        if (function2 == null || shouldIgnore(sbn) || sbn.isOngoing() || (sbn.getNotification().flags & 512) != 0) {
            return;
        }
        Bundle bundle = sbn.getNotification().extras;
        boolean z = false;
        if (bundle != null && bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
            z = true;
        }
        boolean group = z;
        String strKeyOf = keyOf(sbn);
        String packageName = sbn.getPackageName();
        if (packageName == null) {
            packageName = "";
        }
        function2.invoke(strKeyOf, new IncomingMessage(packageName, info.getTitle(), info.getText(), group));
    }

    private final void publishToBus(StatusBarNotification sbn, boolean posted) {
        String summary;
        if (shouldIgnore(sbn)) {
            return;
        }
        String key = keyOf(sbn);
        if (posted) {
            long now = SystemClock.elapsedRealtime();
            if (Intrinsics.areEqual(key, this.lastPostedKey) && now - this.lastPostedAtElapsed < 1200) {
                return;
            }
            this.lastPostedKey = key;
            this.lastPostedAtElapsed = now;
        }
        String packageName = sbn.getPackageName();
        if (packageName == null) {
            packageName = "";
        }
        String str = packageName;
        if (StringsKt.isBlank(str)) {
            return;
        }
        String pkg = str;
        boolean sensitive = SensitivityClassifier.INSTANCE.isSensitivePackage(pkg);
        NotificationPrivacy notificationPrivacy = NotificationPrivacy.INSTANCE;
        Bundle bundle = sbn.getNotification().extras;
        String shortTitle = notificationPrivacy.summaryTitle(bundle != null ? bundle.getCharSequence(NotificationCompat.EXTRA_TITLE) : null, pkg);
        if (!posted) {
            summary = "Уведомление снято: " + pkg;
        } else if (sensitive) {
            summary = "Уведомление: " + pkg + " · скрыто";
        } else {
            summary = !StringsKt.isBlank(shortTitle) ? "Уведомление: " + pkg + " · " + shortTitle : "Уведомление: " + pkg;
        }
        PhoneContextBus.INSTANCE.publish(new PhoneObservation(null, 0L, ObservationSource.NOTIFICATION_LISTENER, posted ? ObservationType.NOTIFICATION_POSTED : ObservationType.NOTIFICATION_REMOVED, pkg, null, summary, MapsKt.mapOf(TuplesKt.to("package", pkg), TuplesKt.to("ongoing", String.valueOf(sbn.isOngoing())), TuplesKt.to("has_title", String.valueOf(!StringsKt.isBlank(shortTitle)))), sensitive ? Sensitivity.SECRET : Sensitivity.PRIVATE, 0.85f, 35, null));
        Log.d(TAG, (posted ? "POSTED" : "REMOVED") + StringUtils.SPACE + pkg);
    }

    private final boolean shouldIgnore(StatusBarNotification sbn) {
        if (Intrinsics.areEqual(sbn.getPackageName(), getPackageName())) {
            return true;
        }
        Notification n = sbn.getNotification();
        int flags = n.flags;
        if ((flags & 512) != 0 || (flags & 64) != 0) {
            return true;
        }
        if (sbn.isOngoing() && Intrinsics.areEqual(n.category, NotificationCompat.CATEGORY_SERVICE)) {
            return true;
        }
        String pkg = sbn.getPackageName();
        if (pkg == null) {
            pkg = "";
        }
        return Intrinsics.areEqual(pkg, "android") || StringsKt.startsWith$default(pkg, "com.android.systemui", false, 2, (Object) null);
    }

    public final void refresh() {
        Collection destination$iv$iv;
        StatusBarNotification[] activeNotifications = getActiveNotifications();
        if (activeNotifications == null) {
            destination$iv$iv = null;
        } else {
            Collection destination$iv$iv2 = new ArrayList(activeNotifications.length);
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                Intrinsics.checkNotNull(statusBarNotification);
                destination$iv$iv2.add(TuplesKt.to(keyOf(statusBarNotification), toInfo(statusBarNotification)));
            }
            destination$iv$iv = (List) destination$iv$iv2;
        }
        if (destination$iv$iv == null) {
            destination$iv$iv = CollectionsKt.emptyList();
        }
        Collection fresh = destination$iv$iv;
        synchronized (cache) {
            cache.clear();
            Collection $this$forEach$iv = fresh;
            for (Object element$iv : $this$forEach$iv) {
                Pair pair = (Pair) element$iv;
                String k = (String) pair.component1();
                NotificationInfo v = (NotificationInfo) pair.component2();
                cache.put(k, v);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final List<NotificationInfo> list() {
        List<NotificationInfo> listSortedWith;
        synchronized (cache) {
            Iterable iterableValues = cache.values();
            Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
            Iterable $this$sortedByDescending$iv = iterableValues;
            listSortedWith = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.screencontrol.JarvisNotificationListenerService$list$lambda$0$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    NotificationInfo it = (NotificationInfo) t2;
                    NotificationInfo it2 = (NotificationInfo) t;
                    return ComparisonsKt.compareValues(Long.valueOf(it.getPostTime()), Long.valueOf(it2.getPostTime()));
                }
            });
        }
        return listSortedWith;
    }

    public final boolean dismiss(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            cancelNotification(key);
            synchronized (cache) {
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public final boolean clearAll() {
        try {
            cancelAllNotifications();
            synchronized (cache) {
                cache.clear();
                Unit unit = Unit.INSTANCE;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public final boolean sendAction(String key, int actionIndex) {
        NotificationInfo info;
        NotificationActionInfo action;
        PendingIntent pi;
        Intrinsics.checkNotNullParameter(key, "key");
        synchronized (cache) {
            info = (NotificationInfo) cache.get((Object) key);
        }
        if (info == null || (action = (NotificationActionInfo) CollectionsKt.getOrNull(info.getActions(), actionIndex)) == null || (pi = action.getActionIntent()) == null) {
            return false;
        }
        try {
            pi.send();
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0069  */
    public final boolean replyText(String key, String text) {
        StatusBarNotification statusBarNotification;
        Notification.Action action;
        Notification.Action action2;
        RemoteInput[] remoteInputs;
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(text, "text");
        if (StringsKt.isBlank(text)) {
            return false;
        }
        StatusBarNotification[] activeNotifications = getActiveNotifications();
        if (activeNotifications != null) {
            int length = activeNotifications.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    statusBarNotification = null;
                    break;
                }
                statusBarNotification = activeNotifications[i];
                if (Intrinsics.areEqual(statusBarNotification.getKey(), key)) {
                    break;
                }
                i++;
            }
            if (statusBarNotification != null) {
                StatusBarNotification sbn = statusBarNotification;
                Notification.Action[] actionArr = sbn.getNotification().actions;
                if (actionArr == null) {
                    return false;
                }
                int length2 = actionArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        action = null;
                        break;
                    }
                    Notification.Action action3 = actionArr[i2];
                    Object[] $this$any$iv = action3.getRemoteInputs();
                    if ($this$any$iv == null) {
                        z = false;
                    } else {
                        if ($this$any$iv.length > 0) {
                            Object obj = $this$any$iv[0];
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                    if (z) {
                        action = action3;
                        break;
                    }
                    i2++;
                }
                if (action == null || (remoteInputs = (action2 = action).getRemoteInputs()) == null) {
                    return false;
                }
                Intent fill = new Intent();
                Bundle results = new Bundle();
                for (RemoteInput ri : remoteInputs) {
                    results.putCharSequence(ri.getResultKey(), text);
                }
                RemoteInput.addResultsToIntent(remoteInputs, fill, results);
                try {
                    PendingIntent pendingIntent = action2.actionIntent;
                    if (pendingIntent != null) {
                        pendingIntent.send(this, 0, fill);
                    }
                    return true;
                } catch (Throwable t) {
                    Log.w(TAG, "replyText failed: " + t.getMessage());
                    return false;
                }
            }
        }
        JarvisNotificationListenerService $this$replyText_u24lambda_u241 = this;
        $this$replyText_u24lambda_u241.refresh();
        return false;
    }

    private final String keyOf(StatusBarNotification sbn) {
        String key = sbn.getKey();
        Intrinsics.checkNotNullExpressionValue(key, "getKey(...)");
        return key;
    }

    private final NotificationInfo toInfo(StatusBarNotification sbn) {
        int $i$f$mapIndexed;
        boolean z;
        Notification n = sbn.getNotification();
        Bundle extras = n.extras;
        ArrayList arrayList = null;
        String title = NotificationPrivacy.INSTANCE.sanitize(extras != null ? extras.getCharSequence(NotificationCompat.EXTRA_TITLE) : null, sbn.getPackageName());
        String text = NotificationPrivacy.INSTANCE.sanitize(extras != null ? extras.getCharSequence(NotificationCompat.EXTRA_TEXT) : null, sbn.getPackageName());
        Notification.Action[] actionArr = n.actions;
        if (actionArr != null) {
            Notification.Action[] actionArr2 = actionArr;
            int $i$f$mapIndexed2 = 0;
            Collection destination$iv$iv = new ArrayList(actionArr2.length);
            int index$iv$iv = 0;
            int length = actionArr2.length;
            int i = 0;
            while (i < length) {
                Notification.Action action = actionArr2[i];
                int index$iv$iv2 = index$iv$iv + 1;
                Notification n2 = n;
                Bundle extras2 = extras;
                Notification.Action[] actionArr3 = actionArr2;
                String strSanitize = NotificationPrivacy.INSTANCE.sanitize(action.title, sbn.getPackageName());
                PendingIntent pendingIntent = action.actionIntent;
                RemoteInput[] remoteInputs = action.getRemoteInputs();
                if (remoteInputs != null) {
                    $i$f$mapIndexed = $i$f$mapIndexed2;
                    z = true;
                    if (!(remoteInputs.length == 0)) {
                    }
                    destination$iv$iv.add(new NotificationActionInfo(index$iv$iv, strSanitize, pendingIntent, z));
                    i++;
                    index$iv$iv = index$iv$iv2;
                    n = n2;
                    extras = extras2;
                    actionArr2 = actionArr3;
                    $i$f$mapIndexed2 = $i$f$mapIndexed;
                } else {
                    $i$f$mapIndexed = $i$f$mapIndexed2;
                }
                z = false;
                destination$iv$iv.add(new NotificationActionInfo(index$iv$iv, strSanitize, pendingIntent, z));
                i++;
                index$iv$iv = index$iv$iv2;
                n = n2;
                extras = extras2;
                actionArr2 = actionArr3;
                $i$f$mapIndexed2 = $i$f$mapIndexed;
            }
            arrayList = (List) destination$iv$iv;
        }
        List actions = arrayList == null ? CollectionsKt.emptyList() : arrayList;
        String key = sbn.getKey();
        Intrinsics.checkNotNullExpressionValue(key, "getKey(...)");
        String packageName = sbn.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        return new NotificationInfo(key, packageName, title, text, sbn.getPostTime(), actions);
    }

    /* JADX INFO: compiled from: JarvisNotificationListenerService.kt */
    @Metadata(d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000*\u0001\u0019\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001b\u001a\u00020\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR.\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006 "}, d2 = {"Lcom/jarvis/screencontrol/JarvisNotificationListenerService$Companion;", "", "<init>", "()V", "TAG", "", "value", "Lcom/jarvis/screencontrol/JarvisNotificationListenerService;", "instance", "getInstance", "()Lcom/jarvis/screencontrol/JarvisNotificationListenerService;", "messageListener", "Lkotlin/Function2;", "Lcom/jarvis/core/announce/IncomingMessage;", "", "getMessageListener", "()Lkotlin/jvm/functions/Function2;", "setMessageListener", "(Lkotlin/jvm/functions/Function2;)V", "lastAnnouncedKey", "getLastAnnouncedKey", "()Ljava/lang/String;", "setLastAnnouncedKey", "(Ljava/lang/String;)V", "cache", "com/jarvis/screencontrol/JarvisNotificationListenerService$Companion$cache$1", "Lcom/jarvis/screencontrol/JarvisNotificationListenerService$Companion$cache$1;", "isConnected", "", "snapshot", "", "Lcom/jarvis/screencontrol/NotificationInfo;", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JarvisNotificationListenerService getInstance() {
            return JarvisNotificationListenerService.instance;
        }

        public final Function2<String, IncomingMessage, Unit> getMessageListener() {
            return JarvisNotificationListenerService.messageListener;
        }

        public final void setMessageListener(Function2<? super String, ? super IncomingMessage, Unit> function2) {
            JarvisNotificationListenerService.messageListener = function2;
        }

        public final String getLastAnnouncedKey() {
            return JarvisNotificationListenerService.lastAnnouncedKey;
        }

        public final void setLastAnnouncedKey(String str) {
            JarvisNotificationListenerService.lastAnnouncedKey = str;
        }

        public final boolean isConnected() {
            return getInstance() != null;
        }

        public final List<NotificationInfo> snapshot() {
            JarvisNotificationListenerService companion = getInstance();
            if (companion != null) {
                companion.refresh();
            }
            JarvisNotificationListenerService companion2 = getInstance();
            List<NotificationInfo> list = companion2 != null ? companion2.list() : null;
            return list == null ? CollectionsKt.emptyList() : list;
        }
    }
}
