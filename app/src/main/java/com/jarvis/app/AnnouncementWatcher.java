package com.jarvis.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.app.proactive.ProactiveVoice;
import com.jarvis.core.announce.CallAnnouncer;
import com.jarvis.core.announce.IncomingMessage;
import com.jarvis.core.announce.MessageAnnouncer;
import com.jarvis.core.announce.QuietHours;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.proactive.InterruptionKind;
import com.jarvis.core.proactive.PhoneStateSnapshot;
import com.jarvis.core.proactive.RingerKind;
import com.jarvis.screencontrol.JarvisNotificationListenerService;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AnnouncementWatcher.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006*\u0002\u0016\u0019\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u0018\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010#\u001a\u00020$H\u0002J\u0012\u0010%\u001a\u0004\u0018\u00010\u000e2\u0006\u0010&\u001a\u00020\u000eH\u0002J\b\u0010'\u001a\u00020\u001cH\u0002J\b\u0010(\u001a\u00020\u001cH\u0002R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006*"}, d2 = {"Lcom/jarvis/app/AnnouncementWatcher;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "ctx", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "messages", "Lcom/jarvis/core/announce/MessageAnnouncer;", "announcedEvents", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "announcedRing", "", "awaitedAlarmAt", "", "briefedAlarmAt", "callReceiver", "com/jarvis/app/AnnouncementWatcher$callReceiver$1", "Lcom/jarvis/app/AnnouncementWatcher$callReceiver$1;", "tick", "com/jarvis/app/AnnouncementWatcher$tick$1", "Lcom/jarvis/app/AnnouncementWatcher$tick$1;", "start", "", "stop", "onMessage", "key", "message", "Lcom/jarvis/core/announce/IncomingMessage;", "mayTalk", "hourNow", "", "contactName", "number", "checkEvents", "checkAlarm", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AnnouncementWatcher {

    @Deprecated
    public static final long BRIEF_AFTER_MS = 120000;

    @Deprecated
    public static final long BRIEF_UNTIL_MS = 1800000;

    @Deprecated
    public static final long EVENT_LEAD_MS = 900000;

    @Deprecated
    public static final long MINUTE_MS = 60000;

    @Deprecated
    public static final String TAG = "Jarvis.Announce";

    @Deprecated
    public static final long TICK_MS = 60000;
    private final HashSet<String> announcedEvents;
    private boolean announcedRing;
    private long awaitedAlarmAt;
    private long briefedAlarmAt;
    private final AnnouncementWatcher$callReceiver$1 callReceiver;
    private final Context ctx;
    private final Handler handler;
    private final MessageAnnouncer messages;
    private final AnnouncementWatcher$tick$1 tick;
    private static final Companion Companion = new Companion(null);
    private static final IntRange MORNING = new IntRange(4, 11);

    /* JADX WARN: Type inference failed for: r0v5, types: [com.jarvis.app.AnnouncementWatcher$callReceiver$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.jarvis.app.AnnouncementWatcher$tick$1] */
    public AnnouncementWatcher(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.ctx = context.getApplicationContext();
        this.handler = new Handler(Looper.getMainLooper());
        this.messages = new MessageAnnouncer(0L, 1, null);
        this.announcedEvents = new HashSet<>();
        this.callReceiver = new BroadcastReceiver() { // from class: com.jarvis.app.AnnouncementWatcher$callReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Intrinsics.checkNotNullParameter(context2, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                String state = intent.getStringExtra("state");
                if (!Intrinsics.areEqual(state, TelephonyManager.EXTRA_STATE_RINGING)) {
                    this.this$0.announcedRing = false;
                    return;
                }
                String number = intent.getStringExtra("incoming_number");
                if (!this.this$0.announcedRing && number != null) {
                    JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
                    Context context3 = this.this$0.ctx;
                    Intrinsics.checkNotNullExpressionValue(context3, "access$getCtx$p(...)");
                    if (jarvisSettings.announceCalls(context3)) {
                        this.this$0.announcedRing = true;
                        ProactiveVoice proactiveVoice = ProactiveVoice.INSTANCE;
                        Context context4 = this.this$0.ctx;
                        Intrinsics.checkNotNullExpressionValue(context4, "access$getCtx$p(...)");
                        proactiveVoice.speak(context4, CallAnnouncer.INSTANCE.phrase(this.this$0.contactName(number)));
                    }
                }
            }
        };
        this.tick = new Runnable() { // from class: com.jarvis.app.AnnouncementWatcher$tick$1
            @Override // java.lang.Runnable
            public void run() {
                Object objM392constructorimpl;
                Object objM392constructorimpl2;
                AnnouncementWatcher announcementWatcher = this.this$0;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    AnnouncementWatcher$tick$1 announcementWatcher$tick$1 = this;
                    announcementWatcher.checkEvents();
                    objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                if (it != null) {
                    Log.w(AnnouncementWatcher.TAG, "calendar: " + it.getMessage());
                }
                AnnouncementWatcher announcementWatcher2 = this.this$0;
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    AnnouncementWatcher$tick$1 announcementWatcher$tick$2 = this;
                    announcementWatcher2.checkAlarm();
                    objM392constructorimpl2 = Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th2));
                }
                Throwable it2 = Result.m395exceptionOrNullimpl(objM392constructorimpl2);
                if (it2 != null) {
                    Log.w(AnnouncementWatcher.TAG, "alarm: " + it2.getMessage());
                }
                this.this$0.handler.postDelayed(this, 60000L);
            }
        };
    }

    /* JADX INFO: renamed from: com.jarvis.app.AnnouncementWatcher$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: AnnouncementWatcher.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<String, IncomingMessage, Unit> {
        AnonymousClass1(Object obj) {
            super(2, obj, AnnouncementWatcher.class, "onMessage", "onMessage(Ljava/lang/String;Lcom/jarvis/core/announce/IncomingMessage;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, IncomingMessage incomingMessage) {
            invoke2(str, incomingMessage);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String p0, IncomingMessage p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            ((AnnouncementWatcher) this.receiver).onMessage(p0, p1);
        }
    }

    public final void start() {
        this.ctx.registerReceiver(this.callReceiver, new IntentFilter("android.intent.action.PHONE_STATE"));
        JarvisNotificationListenerService.INSTANCE.setMessageListener(new AnonymousClass1(this));
        this.handler.postDelayed(this.tick, 60000L);
    }

    public final void stop() {
        try {
            Result.Companion companion = Result.INSTANCE;
            AnnouncementWatcher $this$stop_u24lambda_u240 = this;
            $this$stop_u24lambda_u240.ctx.unregisterReceiver($this$stop_u24lambda_u240.callReceiver);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        JarvisNotificationListenerService.INSTANCE.setMessageListener(null);
        this.handler.removeCallbacks(this.tick);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onMessage(String key, IncomingMessage message) {
        String phrase;
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        if (!jarvisSettings.announceMessages(ctx) || !mayTalk() || QuietHours.INSTANCE.isQuiet(hourNow()) || (phrase = this.messages.phrase(message, System.currentTimeMillis())) == null) {
            return;
        }
        ProactiveVoice proactiveVoice = ProactiveVoice.INSTANCE;
        Context ctx2 = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx2, "ctx");
        if (proactiveVoice.speak(ctx2, phrase).getRequested()) {
            JarvisNotificationListenerService.INSTANCE.setLastAnnouncedKey(key);
        }
    }

    private final boolean mayTalk() {
        PhoneStateReader phoneStateReader = PhoneStateReader.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        PhoneStateSnapshot phone = phoneStateReader.read(ctx);
        boolean filterOk = phone.getInterruptionFilter() == InterruptionKind.ALL || phone.getInterruptionFilter() == InterruptionKind.UNKNOWN;
        return phone.getRingerMode() == RingerKind.NORMAL && filterOk && !phone.getCallActive();
    }

    private final int hourNow() {
        return Calendar.getInstance().get(11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String contactName(String number) throws IOException {
        if (this.ctx.checkSelfPermission("android.permission.READ_CONTACTS") != 0) {
            return null;
        }
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        Cursor cursorQuery = this.ctx.getContentResolver().query(uri, new String[]{"display_name"}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        Cursor cursor = cursorQuery;
        try {
            Cursor it = cursor;
            String string = it.moveToFirst() ? it.getString(0) : null;
            CloseableKt.closeFinally(cursor, null);
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursor, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkEvents() throws IOException {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        if (jarvisSettings.announceHints(ctx) && mayTalk() && this.ctx.checkSelfPermission("android.permission.READ_CALENDAR") == 0) {
            long now = System.currentTimeMillis();
            Uri.Builder uri = CalendarContract.Instances.CONTENT_URI.buildUpon();
            ContentUris.appendId(uri, now);
            ContentUris.appendId(uri, EVENT_LEAD_MS + now);
            int i = 0;
            int i2 = 1;
            Cursor cursorQuery = this.ctx.getContentResolver().query(uri.build(), new String[]{"title", "begin", "allDay"}, null, null, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor c = cursor;
                    while (c.moveToNext()) {
                        if (c.getInt(2) == 0) {
                            long begin = c.getLong(i2);
                            String string = c.getString(i);
                            if (string == null) {
                                string = "";
                            }
                            String str = string;
                            if (StringsKt.isBlank(str)) {
                                str = "встреча";
                            }
                            String title = str;
                            if (begin >= now && this.announcedEvents.add(begin + "|" + title)) {
                                long minutes = RangesKt.coerceAtLeast((begin - now) / 60000, 1L);
                                ProactiveVoice proactiveVoice = ProactiveVoice.INSTANCE;
                                Context ctx2 = this.ctx;
                                Intrinsics.checkNotNullExpressionValue(ctx2, "ctx");
                                proactiveVoice.speak(ctx2, "Сэр, через " + minutes + " мин: " + title + ".");
                                CloseableKt.closeFinally(cursor, null);
                                return;
                            }
                            i = 0;
                            i2 = 1;
                        } else {
                            i = 0;
                            i2 = 1;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursor, th);
                        throw th2;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:28:0x0068  */
    public final void checkAlarm() {
        boolean due;
        AlarmManager manager = (AlarmManager) this.ctx.getSystemService(AlarmManager.class);
        if (manager == null) {
            return;
        }
        long now = System.currentTimeMillis();
        AlarmManager.AlarmClockInfo next = manager.getNextAlarmClock();
        if (next != null) {
            PendingIntent showIntent = next.getShowIntent();
            if (!Intrinsics.areEqual(showIntent != null ? showIntent.getCreatorPackage() : null, this.ctx.getPackageName()) && next.getTriggerTime() > now) {
                this.awaitedAlarmAt = next.getTriggerTime();
            }
        }
        long passed = now - this.awaitedAlarmAt;
        if (this.awaitedAlarmAt == 0) {
            due = false;
        } else {
            if (!(BRIEF_AFTER_MS <= passed && passed < 1800001) || this.briefedAlarmAt == this.awaitedAlarmAt) {
                due = false;
            } else {
                due = true;
            }
        }
        if (due) {
            IntRange intRange = MORNING;
            int first = intRange.getFirst();
            int last = intRange.getLast();
            int iHourNow = hourNow();
            if (first <= iHourNow && iHourNow <= last) {
                JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
                Context ctx = this.ctx;
                Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
                if (!jarvisSettings.announceHints(ctx) || !mayTalk()) {
                    return;
                }
                this.briefedAlarmAt = this.awaitedAlarmAt;
                new Thread(new Runnable() { // from class: com.jarvis.app.AnnouncementWatcher$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnnouncementWatcher.checkAlarm$lambda$0(this.f$0);
                    }
                }, "jarvis-briefing").start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAlarm$lambda$0(AnnouncementWatcher this$0) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            BrainGraph brainGraph = BrainGraph.INSTANCE;
            Context ctx = this$0.ctx;
            Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
            objM392constructorimpl = Result.m392constructorimpl(brainGraph.build(ctx).process(new UserRequest("сводка на день", null, null, 0L, null, 30, null)).getReplyText());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        String reply = (String) objM392constructorimpl;
        String str = reply;
        if (!(str == null || StringsKt.isBlank(str))) {
            ProactiveVoice proactiveVoice = ProactiveVoice.INSTANCE;
            Context ctx2 = this$0.ctx;
            Intrinsics.checkNotNullExpressionValue(ctx2, "ctx");
            proactiveVoice.speak(ctx2, reply);
        }
    }

    /* JADX INFO: compiled from: AnnouncementWatcher.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/jarvis/app/AnnouncementWatcher$Companion;", "", "<init>", "()V", "TAG", "", "MINUTE_MS", "", "TICK_MS", "EVENT_LEAD_MS", "BRIEF_AFTER_MS", "BRIEF_UNTIL_MS", "MORNING", "Lkotlin/ranges/IntRange;", "getMORNING", "()Lkotlin/ranges/IntRange;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final IntRange getMORNING() {
            return AnnouncementWatcher.MORNING;
        }
    }
}
