package com.jarvis.brain.cloud;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeLog.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\n"}, d2 = {"Lcom/jarvis/brain/cloud/SafeLog;", "", "<init>", "()V", "i", "", "tag", "", NotificationCompat.CATEGORY_MESSAGE, "w", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SafeLog {
    public static final SafeLog INSTANCE = new SafeLog();

    private SafeLog() {
    }

    public final void i(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        try {
            Log.i(tag, msg);
        } catch (Throwable th) {
        }
    }

    public final void w(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        try {
            Log.w(tag, msg);
        } catch (Throwable th) {
        }
    }
}
