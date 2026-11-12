package com.jarvis.core.reminder;

import com.jarvis.app.VoiceService;
import java.time.LocalDateTime;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ReminderBook.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH&J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH&J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/jarvis/core/reminder/ReminderBook;", "", "add", "Lcom/jarvis/core/reminder/Reminder;", "at", "Ljava/time/LocalDateTime;", VoiceService.EXTRA_TEXT, "", "daily", "", "command", "all", "", "remove", "id", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ReminderBook {
    Reminder add(LocalDateTime at, String text, boolean daily, boolean command);

    List<Reminder> all();

    boolean remove(long id);

    /* JADX INFO: compiled from: ReminderBook.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Reminder add$default(ReminderBook reminderBook, LocalDateTime localDateTime, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: add");
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return reminderBook.add(localDateTime, str, z, z2);
    }
}
