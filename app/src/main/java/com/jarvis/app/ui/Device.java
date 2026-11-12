package com.jarvis.app.ui;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.StatFs;
import com.jarvis.app.EventRoutineRunner;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt;

/* JADX INFO: compiled from: Device.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/jarvis/app/ui/Device;", "", "<init>", "()V", "time", "", "greeting", "battery", "", "context", "Landroid/content/Context;", "temperature", "ramPercent", EventRoutineRunner.NETWORK, "freeStorageGb", "", "cpuPercent", "vitals", "nextAlarm", "(Landroid/content/Context;)Ljava/lang/Long;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Device {
    public static final Device INSTANCE = new Device();

    private Device() {
    }

    public final String time() {
        String str = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String greeting() {
        int i = Calendar.getInstance().get(11);
        if (5 <= i && i < 12) {
            return "Доброе утро";
        }
        if (12 <= i && i < 18) {
            return "Добрый день";
        }
        return 18 <= i && i < 23 ? "Добрый вечер" : "Доброй ночи";
    }

    public final int battery(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BatteryManager batteryManager = (BatteryManager) context.getSystemService(BatteryManager.class);
        if (batteryManager != null) {
            return batteryManager.getIntProperty(4);
        }
        return 0;
    }

    public final int temperature(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return (intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("temperature", 0) : 0) / 10;
    }

    public final int ramPercent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
        if (activityManager != null) {
            activityManager.getMemoryInfo(info);
        }
        if (info.totalMem <= 0) {
            return 0;
        }
        long j = 100;
        return (int) (j - ((info.availMem * j) / info.totalMem));
    }

    public final String network(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        if (cm == null) {
            return "Нет";
        }
        NetworkCapabilities caps = cm.getNetworkCapabilities(cm.getActiveNetwork());
        if (caps == null) {
            return "Нет сети";
        }
        if (caps.hasTransport(1)) {
            return "Wi-Fi";
        }
        return caps.hasTransport(0) ? "Моб. сеть" : "Подключено";
    }

    public final long freeStorageGb() {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Device device = this;
            objM392constructorimpl = Result.m392constructorimpl(Long.valueOf(new StatFs(Environment.getDataDirectory().getPath()).getAvailableBytes() / ((long) InstantKt.NANOS_PER_SECOND)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = 0L;
        }
        return ((Number) objM392constructorimpl).longValue();
    }

    public final int cpuPercent() {
        int cores = RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors(), 1);
        double sum = 0.0d;
        int seen = 0;
        for (int i = 0; i < cores; i++) {
            Long lCpuPercent$read = cpuPercent$read(i, "scaling_cur_freq");
            if (lCpuPercent$read != null) {
                long cur = lCpuPercent$read.longValue();
                Long lCpuPercent$read2 = cpuPercent$read(i, "cpuinfo_max_freq");
                if (lCpuPercent$read2 != null) {
                    long max = lCpuPercent$read2.longValue();
                    if (max > 0) {
                        sum += cur / max;
                        seen++;
                    }
                }
            }
        }
        if (seen == 0) {
            return 0;
        }
        return (int) ((sum / ((double) seen)) * ((double) 100));
    }

    private static final Long cpuPercent$read(int i, String name) {
        Object objM392constructorimpl;
        Device device = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(Long.valueOf(Long.parseLong(StringsKt.trim((CharSequence) FilesKt.readText$default(new File("/sys/devices/system/cpu/cpu" + i + "/cpufreq/" + name), null, 1, null)).toString())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (Long) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }

    public final String vitals(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return "Заряд " + battery(context) + "%\nПамять " + ramPercent(context) + "%\nСеть " + network(context);
    }

    public final Long nextAlarm(Context context) {
        AlarmManager.AlarmClockInfo nextAlarmClock;
        Intrinsics.checkNotNullParameter(context, "context");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(AlarmManager.class);
        if (alarmManager == null || (nextAlarmClock = alarmManager.getNextAlarmClock()) == null) {
            return null;
        }
        return Long.valueOf(nextAlarmClock.getTriggerTime());
    }
}
