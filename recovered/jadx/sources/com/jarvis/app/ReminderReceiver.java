package com.jarvis.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.proactive.ProactiveVoice;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.reminder.Reminder;
import com.jarvis.core.reminder.ReminderSpeech;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReminderReceiver.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u0010"}, d2 = {"Lcom/jarvis/app/ReminderReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "runCommand", ReminderReceiver.EXTRA_ID, "", "command", "", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReminderReceiver extends BroadcastReceiver {
    private static final String ACTION = "com.jarvis.assistant.REMINDER_FIRE";
    private static final int BASE_NOTIFICATION = 5000;
    private static final String CHANNEL = "jarvis_reminders";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EXTRA_ID = "id";
    private static final String TAG = "Jarvis.Reminders";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        long id = intent.getLongExtra(EXTRA_ID, -1L);
        Reminder reminder = new AndroidReminderBook(context).fired(id);
        if (reminder == null) {
            return;
        }
        if (reminder.getCommand()) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            runCommand(applicationContext, id, reminder.getText());
        } else {
            INSTANCE.deliver(context, id, ReminderSpeech.INSTANCE.firing(reminder.getText()));
            JarvisWidgetProvider.INSTANCE.refresh(context);
        }
    }

    private final void runCommand(final Context context, final long id, final String command) {
        final BroadcastReceiver.PendingResult pending = goAsync();
        new Thread(new Runnable() { // from class: com.jarvis.app.ReminderReceiver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ReminderReceiver.runCommand$lambda$0(context, command, id, pending);
            }
        }, "jarvis-scheduled").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runCommand$lambda$0(Context $context, String $command, long $id, BroadcastReceiver.PendingResult $pending) {
        try {
            String reply = BrainGraph.INSTANCE.build($context).process(new UserRequest($command, null, null, 0L, null, 30, null)).getReplyText();
            INSTANCE.deliver($context, $id, reply);
        } catch (Throwable t) {
            try {
                Log.w("Jarvis.Reminders", "scheduled command failed: " + t.getMessage(), t);
                INSTANCE.deliver($context, $id, "Не удалось выполнить, сэр: " + $command + ".");
            } finally {
                $pending.finish();
            }
        }
    }

    /* JADX INFO: compiled from: ReminderReceiver.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J(\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/jarvis/app/ReminderReceiver$Companion;", "", "<init>", "()V", "TAG", "", "deliver", "", "context", "Landroid/content/Context;", ReminderReceiver.EXTRA_ID, "", VoiceService.EXTRA_TEXT, "notify", "silent", "", "ACTION", "EXTRA_ID", "CHANNEL", "BASE_NOTIFICATION", "", "intent", "Landroid/content/Intent;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void deliver(Context context, long id, String text) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(text, "text");
            boolean spoken = ProactiveVoice.INSTANCE.speak(context, text).getRequested();
            notify(context, id, text, spoken);
            VoiceHealthLog.INSTANCE.write(context, "delivered id=" + id + " spoken=" + spoken);
        }

        private final void notify(Context context, long id, String text, boolean silent) {
            NotificationManager manager = (NotificationManager) context.getSystemService(NotificationManager.class);
            if (manager == null) {
                return;
            }
            manager.createNotificationChannel(new NotificationChannel(ReminderReceiver.CHANNEL, "Напоминания", 4));
            PendingIntent open = PendingIntent.getActivity(context, (int) id, new Intent(context, (Class<?>) JarvisActivity.class).addFlags(268435456), 201326592);
            Notification note = new NotificationCompat.Builder(context, ReminderReceiver.CHANNEL).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Jarvis").setContentText(text).setStyle(new NotificationCompat.BigTextStyle().bigText(text)).setContentIntent(open).setAutoCancel(true).setSilent(silent).setCategory(NotificationCompat.CATEGORY_REMINDER).setPriority(1).build();
            Intrinsics.checkNotNullExpressionValue(note, "build(...)");
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                manager.notify(((int) id) + ReminderReceiver.BASE_NOTIFICATION, note);
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        }

        public final Intent intent(Context context, long id) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intentPutExtra = new Intent(context, (Class<?>) ReminderReceiver.class).setAction(ReminderReceiver.ACTION).putExtra(ReminderReceiver.EXTRA_ID, id);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            return intentPutExtra;
        }
    }
}
