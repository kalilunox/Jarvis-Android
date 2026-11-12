package com.jarvis.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.reminder.Reminder;
import com.jarvis.core.reminder.ReminderBook;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AndroidReminderBook.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 *2\u00020\u0001:\u0001*B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\rH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010!\u001a\u00020 H\u0002J'\u0010\"\u001a\u00020\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0002\u0010%J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\u000fH\u0002J\u0018\u0010(\u001a\n \u0007*\u0004\u0018\u00010\u000f0\u000f2\u0006\u0010)\u001a\u00020\u0019H\u0002R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/jarvis/app/AndroidReminderBook;", "Lcom/jarvis/core/reminder/ReminderBook;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "ctx", "kotlin.jvm.PlatformType", "prefs", "Landroid/content/SharedPreferences;", "alarms", "Landroid/app/AlarmManager;", "add", "Lcom/jarvis/core/reminder/Reminder;", "at", "Ljava/time/LocalDateTime;", VoiceService.EXTRA_TEXT, "", "daily", "", "command", "all", "", "remove", "id", "", "fired", "rearm", "", "arm", NotificationCompat.CATEGORY_REMINDER, "fireIntent", "Landroid/app/PendingIntent;", "openApp", "store", AndroidReminderBook.KEY_ITEMS, "nextId", "(Ljava/util/List;Ljava/lang/Long;)V", "timeToMillis", "t", "millisToTime", "ms", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AndroidReminderBook implements ReminderBook {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String KEY_ITEMS = "items";

    @Deprecated
    public static final String KEY_NEXT = "next_id";

    @Deprecated
    public static final String TAG = "Jarvis.Reminders";
    private final AlarmManager alarms;
    private final Context ctx;
    private final SharedPreferences prefs;

    public AndroidReminderBook(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.ctx = context.getApplicationContext();
        this.prefs = this.ctx.getSharedPreferences("jarvis_reminders", 0);
        this.alarms = (AlarmManager) this.ctx.getSystemService(AlarmManager.class);
    }

    @Override // com.jarvis.core.reminder.ReminderBook
    public synchronized Reminder add(LocalDateTime at, String text, boolean daily, boolean command) {
        Reminder reminder;
        Intrinsics.checkNotNullParameter(at, "at");
        Intrinsics.checkNotNullParameter(text, "text");
        long id = this.prefs.getLong(KEY_NEXT, 1L);
        reminder = new Reminder(id, at, text, daily, command);
        store(CollectionsKt.plus((Collection<? extends Reminder>) all(), reminder), Long.valueOf(1 + id));
        arm(reminder);
        return reminder;
    }

    @Override // com.jarvis.core.reminder.ReminderBook
    public synchronized List<Reminder> all() {
        Object objM392constructorimpl;
        ArrayList arrayList;
        try {
            Result.Companion companion = Result.INSTANCE;
            AndroidReminderBook $this$all_u24lambda_u240 = this;
            objM392constructorimpl = Result.m392constructorimpl(new JSONArray($this$all_u24lambda_u240.prefs.getString(KEY_ITEMS, "[]")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m395exceptionOrNullimpl(objM392constructorimpl) != null) {
            objM392constructorimpl = new JSONArray();
        }
        JSONArray array = (JSONArray) objM392constructorimpl;
        int length = array.length();
        arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            int i2 = i;
            JSONObject o = array.getJSONObject(i2);
            long j = o.getLong("id");
            LocalDateTime localDateTimeMillisToTime = millisToTime(o.getLong("at"));
            Intrinsics.checkNotNullExpressionValue(localDateTimeMillisToTime, "millisToTime(...)");
            String strOptString = o.optString(VoiceService.EXTRA_TEXT);
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            arrayList.add(new Reminder(j, localDateTimeMillisToTime, strOptString, o.optBoolean("daily"), o.optBoolean("command")));
        }
        return arrayList;
    }

    @Override // com.jarvis.core.reminder.ReminderBook
    public synchronized boolean remove(long id) {
        boolean z;
        Iterable iterableAll = all();
        Iterable $this$none$iv = iterableAll;
        if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
            Iterator it = $this$none$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    Reminder it2 = (Reminder) element$iv;
                    if (it2.getId() == id) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return false;
        }
        AlarmManager alarmManager = this.alarms;
        if (alarmManager != null) {
            alarmManager.cancel(fireIntent(id));
        }
        Iterable $this$filter$iv = iterableAll;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Reminder it3 = (Reminder) element$iv$iv;
            if (it3.getId() != id) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        store$default(this, (List) destination$iv$iv, null, 2, null);
        return true;
    }

    public final synchronized Reminder fired(long id) {
        Object element$iv;
        Iterable $this$firstOrNull$iv = all();
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
        } while (!(((Reminder) element$iv).getId() == id));
        Reminder reminder = (Reminder) element$iv;
        if (reminder == null) {
            return null;
        }
        if (!reminder.getDaily()) {
            remove(id);
            return reminder;
        }
        LocalDateTime next = reminder.getAt();
        LocalDateTime now = LocalDateTime.now();
        while (next.compareTo((ChronoLocalDateTime<?>) now) <= 0) {
            LocalDateTime localDateTimePlusDays = next.plusDays(1L);
            Intrinsics.checkNotNullExpressionValue(localDateTimePlusDays, "plusDays(...)");
            next = localDateTimePlusDays;
        }
        Reminder moved = Reminder.copy$default(reminder, 0L, next, null, false, false, 29, null);
        Iterable $this$map$iv = all();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Reminder it2 = (Reminder) item$iv$iv;
            if (it2.getId() == id) {
                it2 = moved;
            }
            destination$iv$iv.add(it2);
        }
        store$default(this, (List) destination$iv$iv, null, 2, null);
        arm(moved);
        return reminder;
    }

    public final synchronized void rearm() {
        LocalDateTime now = LocalDateTime.now();
        for (Reminder reminder : all()) {
            if (reminder.getAt().compareTo((ChronoLocalDateTime<?>) now) > 0) {
                arm(reminder);
            } else {
                Context context = this.ctx;
                ReminderReceiver.Companion companion = ReminderReceiver.INSTANCE;
                Context ctx = this.ctx;
                Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
                context.sendBroadcast(companion.intent(ctx, reminder.getId()));
            }
        }
    }

    private final void arm(Reminder reminder) {
        AlarmManager manager = this.alarms;
        if (manager == null) {
            return;
        }
        long at = timeToMillis(reminder.getAt());
        PendingIntent fire = fireIntent(reminder.getId());
        try {
            boolean exact = Build.VERSION.SDK_INT < 31 || manager.canScheduleExactAlarms();
            if (exact) {
                manager.setAlarmClock(new AlarmManager.AlarmClockInfo(at, openApp()), fire);
            } else {
                manager.setAndAllowWhileIdle(0, at, fire);
            }
        } catch (SecurityException e) {
            Log.w(TAG, "exact alarm denied: " + e.getMessage());
            manager.setAndAllowWhileIdle(0, at, fire);
        }
    }

    private final PendingIntent fireIntent(long id) {
        ReminderReceiver.Companion companion = ReminderReceiver.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        PendingIntent broadcast = PendingIntent.getBroadcast(this.ctx, (int) id, companion.intent(ctx, id), 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    private final PendingIntent openApp() {
        PendingIntent activity = PendingIntent.getActivity(this.ctx, 0, new Intent(this.ctx, (Class<?>) JarvisActivity.class), 201326592);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        return activity;
    }

    static /* synthetic */ void store$default(AndroidReminderBook androidReminderBook, List list, Long l, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        androidReminderBook.store(list, l);
    }

    private final void store(List<Reminder> items, Long nextId) {
        JSONArray array = new JSONArray();
        List<Reminder> $this$forEach$iv = items;
        for (Object element$iv : $this$forEach$iv) {
            Reminder r = (Reminder) element$iv;
            array.put(new JSONObject().put("id", r.getId()).put("at", timeToMillis(r.getAt())).put(VoiceService.EXTRA_TEXT, r.getText()).put("daily", r.getDaily()).put("command", r.getCommand()));
        }
        SharedPreferences.Editor edit = this.prefs.edit().putString(KEY_ITEMS, array.toString());
        if (nextId != null) {
            long it = nextId.longValue();
            edit.putLong(KEY_NEXT, it);
        }
        edit.apply();
        JarvisWidgetProvider.Companion companion = JarvisWidgetProvider.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        companion.refresh(ctx);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.time.ZonedDateTime] */
    private final long timeToMillis(LocalDateTime t) {
        return t.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.time.LocalDateTime] */
    private final LocalDateTime millisToTime(long ms) {
        return Instant.ofEpochMilli(ms).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /* JADX INFO: compiled from: AndroidReminderBook.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/jarvis/app/AndroidReminderBook$Companion;", "", "<init>", "()V", "TAG", "", "KEY_ITEMS", "KEY_NEXT", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
