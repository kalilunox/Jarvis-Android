package com.jarvis.core.announce;

import kotlin.Metadata;

/* JADX INFO: compiled from: Announcements.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/jarvis/core/announce/QuietHours;", "", "<init>", "()V", "START", "", "END", "isQuiet", "", "hour", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class QuietHours {
    private static final int END = 8;
    public static final QuietHours INSTANCE = new QuietHours();
    private static final int START = 23;

    private QuietHours() {
    }

    public final boolean isQuiet(int hour) {
        return hour >= 23 || hour < 8;
    }
}
