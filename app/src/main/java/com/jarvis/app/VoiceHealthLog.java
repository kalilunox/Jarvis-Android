package com.jarvis.app;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: VoiceHealthLog.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/app/VoiceHealthLog;", "", "<init>", "()V", "TAG", "", "FILE_NAME", "MAX_BYTES", "", "time", "Ljava/text/SimpleDateFormat;", "write", "", "context", "Landroid/content/Context;", "line", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceHealthLog {
    private static final String FILE_NAME = "voice_health.log";
    private static final long MAX_BYTES = 524288;
    private static final String TAG = "Jarvis.VoiceHealth";
    public static final VoiceHealthLog INSTANCE = new VoiceHealthLog();
    private static final SimpleDateFormat time = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.US);

    private VoiceHealthLog() {
    }

    public final synchronized void write(Context context, String line) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(line, "line");
        Log.i(TAG, line);
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceHealthLog voiceHealthLog = this;
            File file = new File(context.getFilesDir(), FILE_NAME);
            if (file.length() > 524288) {
                File rotated = new File(context.getFilesDir(), "voice_health.log.1");
                rotated.delete();
                file.renameTo(rotated);
            }
            FilesKt.appendText$default(file, time.format(new Date()) + StringUtils.SPACE + line + StringUtils.LF, null, 2, null);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }
}
