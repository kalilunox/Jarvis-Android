package com.jarvis.tools;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.reminder.ReminderSpeech;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimerTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/jarvis/tools/TimerTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "handler", "Landroid/os/Handler;", "vibrator", "Landroid/os/Vibrator;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "endsAtMs", "", "stopwatchStartedAt", "endRunnable", "Ljava/lang/Runnable;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "cancel", "status", "setAlarm", "nextAlarm", "cancelAlarm", NotificationCompat.CATEGORY_STOPWATCH, "done", "message", "", "ring", "", "vibrate", "durationMs", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TimerTool implements Tool {
    private static final long ALARM_SETTLE_MS = 1200;
    private static final long RING_MS = 6000;
    private static final String TAG = "Jarvis.Tools";
    private final Context context;
    private final ToolDefinition definition;
    private final Runnable endRunnable;
    private volatile long endsAtMs;
    private final Handler handler;
    private volatile long stopwatchStartedAt;
    private final Vibrator vibrator;

    public TimerTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.handler = new Handler(Looper.getMainLooper());
        Object systemService = this.context.getSystemService("vibrator");
        this.vibrator = systemService instanceof Vibrator ? (Vibrator) systemService : null;
        this.definition = new ToolDefinition("timer", "Таймер", "Запускает таймер обратного отсчёта на заданное количество минут/секунд", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("minutes", "минуты", ParameterType.INTEGER, false, null, 16, null), new ToolParameter("seconds", "секунды", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
        this.endRunnable = new Runnable() { // from class: com.jarvis.tools.TimerTool$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TimerTool.endRunnable$lambda$0(this.f$0);
            }
        };
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void endRunnable$lambda$0(TimerTool this$0) {
        this$0.endsAtMs = 0L;
        this$0.vibrate(3000L);
        this$0.ring();
        Log.d(TAG, "timer finished");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String label;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("action");
        String string = obj != null ? obj.toString() : null;
        if (string != null) {
            switch (string.hashCode()) {
                case -1367724422:
                    if (string.equals("cancel")) {
                        return cancel(call);
                    }
                    break;
                case -892481550:
                    if (string.equals("status")) {
                        return status(call);
                    }
                    break;
                case -605903832:
                    if (string.equals("alarm_cancel")) {
                        return cancelAlarm(call);
                    }
                    break;
                case -130660960:
                    if (string.equals("alarm_status")) {
                        return nextAlarm(call);
                    }
                    break;
                case 92895825:
                    if (string.equals(NotificationCompat.CATEGORY_ALARM)) {
                        return setAlarm(call);
                    }
                    break;
                case 1651731981:
                    if (string.equals(NotificationCompat.CATEGORY_STOPWATCH)) {
                        return stopwatch(call);
                    }
                    break;
            }
        }
        Object obj2 = call.getArguments().get("minutes");
        Number number = obj2 instanceof Number ? (Number) obj2 : null;
        int minutes = number != null ? number.intValue() : 0;
        Object obj3 = call.getArguments().get("seconds");
        Number number2 = obj3 instanceof Number ? (Number) obj3 : null;
        int seconds = number2 != null ? number2.intValue() : 0;
        long totalMs = ((((long) minutes) * 60) + ((long) seconds)) * 1000;
        if (totalMs <= 0) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указано время таймера.", null, 0L, 48, null);
        }
        this.handler.removeCallbacks(this.endRunnable);
        this.handler.postDelayed(this.endRunnable, totalMs);
        this.endsAtMs = SystemClock.elapsedRealtime() + totalMs;
        if (minutes > 0) {
            label = (seconds > 0 ? new StringBuilder().append(minutes).append(" мин ").append(seconds).append(" сек") : new StringBuilder().append(minutes).append(" мин")).toString();
        } else {
            label = seconds + " сек";
        }
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Таймер: " + label + ".", null, 0L, 48, null);
    }

    private final ToolResult cancel(ToolCall call) {
        boolean had = this.endsAtMs != 0;
        this.handler.removeCallbacks(this.endRunnable);
        this.endsAtMs = 0L;
        return done(call, had ? "Таймер отменён." : "Таймер не запущен.");
    }

    private final ToolResult status(ToolCall call) {
        StringBuilder sbAppend;
        long left = this.endsAtMs - SystemClock.elapsedRealtime();
        if (this.endsAtMs == 0 || left <= 0) {
            return done(call, "Таймер не запущен.");
        }
        long seconds = (((long) 999) + left) / ((long) 1000);
        if (seconds >= 60) {
            long j = 60;
            sbAppend = new StringBuilder().append(seconds / j).append(" мин ").append(seconds % j);
        } else {
            sbAppend = new StringBuilder().append(seconds);
        }
        String text = sbAppend.append(" сек").toString();
        return done(call, "Осталось " + text + ".");
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00e3  */
    private final ToolResult setAlarm(ToolCall call) {
        Object obj = call.getArguments().get("hour");
        Number number = obj instanceof Number ? (Number) obj : null;
        Integer hour = number != null ? Integer.valueOf(number.intValue()) : null;
        Object obj2 = call.getArguments().get("minute");
        Number number2 = obj2 instanceof Number ? (Number) obj2 : null;
        boolean z = false;
        int minute = number2 != null ? number2.intValue() : 0;
        if (hour != null) {
            int iIntValue = hour.intValue();
            if (iIntValue >= 0 && iIntValue < 24) {
                if (minute >= 0 && minute < 60) {
                    z = true;
                }
                if (z) {
                    try {
                        this.context.startActivity(new Intent("android.intent.action.SET_ALARM").putExtra("android.intent.extra.alarm.HOUR", hour.intValue()).putExtra("android.intent.extra.alarm.MINUTES", minute).putExtra("android.intent.extra.alarm.SKIP_UI", true).addFlags(268435456));
                        String str = String.format("Будильник на %d:%02d.", Arrays.copyOf(new Object[]{hour, Integer.valueOf(minute)}, 2));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                        try {
                            return done(call, str);
                        } catch (Throwable th) {
                            t = th;
                            Log.w(TAG, "alarm failed: " + t.getMessage(), t);
                            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не удалось поставить будильник.", null, 0L, 48, null);
                        }
                    } catch (Throwable th2) {
                        t = th2;
                    }
                }
            }
        }
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "На какое время?", null, 0L, 48, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object, java.time.LocalDateTime] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final ToolResult nextAlarm(ToolCall call) {
        AlarmManager.AlarmClockInfo next;
        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(AlarmManager.class);
        if (alarmManager == null || (next = alarmManager.getNextAlarmClock()) == null) {
            return done(call, "Будильников нет, сэр.");
        }
        ?? localDateTime = Instant.ofEpochMilli(next.getTriggerTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        ReminderSpeech reminderSpeech = ReminderSpeech.INSTANCE;
        Intrinsics.checkNotNull(localDateTime);
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        Intrinsics.checkNotNullExpressionValue(localDateTimeNow, "now(...)");
        return done(call, "Ближайший будильник " + reminderSpeech.whenPhrase(localDateTime, localDateTimeNow) + ", сэр.");
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0062  */
    private final ToolResult cancelAlarm(ToolCall call) {
        String str;
        AlarmManager.AlarmClockInfo nextAlarmClock;
        AlarmManager.AlarmClockInfo nextAlarmClock2;
        try {
            AlarmManager alarms = (AlarmManager) this.context.getSystemService(AlarmManager.class);
            Long after = null;
            Long before = (alarms == null || (nextAlarmClock2 = alarms.getNextAlarmClock()) == null) ? null : Long.valueOf(nextAlarmClock2.getTriggerTime());
            this.context.startActivity(new Intent("android.intent.action.DISMISS_ALARM").putExtra("android.intent.extra.alarm.SEARCH_MODE", "android.next").addFlags(268435456));
            Thread.sleep(ALARM_SETTLE_MS);
            if (alarms != null && (nextAlarmClock = alarms.getNextAlarmClock()) != null) {
                after = Long.valueOf(nextAlarmClock.getTriggerTime());
            }
            if (before != null) {
                long jLongValue = before.longValue();
                if (after != null && jLongValue == after.longValue()) {
                    str = "Открыл будильники, сэр. Снимите нужный вручную.";
                } else {
                    str = "Будильник снят, сэр.";
                }
            } else {
                str = "Открыл будильники, сэр. Снимите нужный вручную.";
            }
            return done(call, str);
        } catch (Throwable t) {
            Log.w(TAG, "alarm cancel failed: " + t.getMessage(), t);
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не удалось снять будильник.", null, 0L, 48, null);
        }
    }

    private final ToolResult stopwatch(ToolCall call) {
        StringBuilder sbAppend;
        StringBuilder sb;
        String str;
        Object obj = call.getArguments().get("mode");
        String mode = obj != null ? obj.toString() : null;
        if (Intrinsics.areEqual(mode, "start")) {
            this.stopwatchStartedAt = SystemClock.elapsedRealtime();
            return done(call, "Секундомер запущен, сэр.");
        }
        long started = this.stopwatchStartedAt;
        if (started == 0) {
            return done(call, "Секундомер не запущен, сэр.");
        }
        long seconds = (SystemClock.elapsedRealtime() - started) / ((long) 1000);
        if (Intrinsics.areEqual(mode, "stop")) {
            this.stopwatchStartedAt = 0L;
        }
        if (seconds >= 60) {
            long j = 60;
            sbAppend = new StringBuilder().append(seconds / j).append(" мин ").append(seconds % j);
        } else {
            sbAppend = new StringBuilder().append(seconds);
        }
        String time = sbAppend.append(" сек").toString();
        if (Intrinsics.areEqual(mode, "stop")) {
            sb = new StringBuilder();
            str = "Прошло ";
        } else {
            sb = new StringBuilder();
            str = "Идёт ";
        }
        return done(call, sb.append(str).append(time).append(", сэр.").toString());
    }

    private final ToolResult done(ToolCall call, String message) {
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, message, null, 0L, 48, null);
    }

    private final void ring() {
        Object objM392constructorimpl;
        Uri uri = RingtoneManager.getDefaultUri(4);
        if (uri == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            TimerTool $this$ring_u24lambda_u240 = this;
            objM392constructorimpl = Result.m392constructorimpl(RingtoneManager.getRingtone($this$ring_u24lambda_u240.context, uri));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        final Ringtone ringtone = (Ringtone) objM392constructorimpl;
        if (ringtone == null) {
            return;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            TimerTool timerTool = this;
            ringtone.play();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        this.handler.postDelayed(new Runnable() { // from class: com.jarvis.tools.TimerTool$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TimerTool.ring$lambda$2(this.f$0, ringtone);
            }
        }, RING_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ring$lambda$2(TimerTool this$0, Ringtone $ringtone) {
        try {
            Result.Companion companion = Result.INSTANCE;
            $ringtone.stop();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    private final void vibrate(long durationMs) {
        Object objM392constructorimpl;
        Vibrator v = this.vibrator;
        if (v == null) {
            return;
        }
        VibrationEffect effect = VibrationEffect.createOneShot(durationMs, -1);
        try {
            Result.Companion companion = Result.INSTANCE;
            TimerTool timerTool = this;
            v.vibrate(effect);
            objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            Log.w(TAG, "vibrate failed: " + it.getMessage(), it);
        }
    }
}
