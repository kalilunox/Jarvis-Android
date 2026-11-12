package com.jarvis.core.reminder;

import com.jarvis.app.VoiceService;
import java.time.LocalDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReminderBook.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014¨\u0006!"}, d2 = {"Lcom/jarvis/core/reminder/Reminder;", "", "id", "", "at", "Ljava/time/LocalDateTime;", VoiceService.EXTRA_TEXT, "", "daily", "", "command", "<init>", "(JLjava/time/LocalDateTime;Ljava/lang/String;ZZ)V", "getId", "()J", "getAt", "()Ljava/time/LocalDateTime;", "getText", "()Ljava/lang/String;", "getDaily", "()Z", "getCommand", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Reminder {
    private final LocalDateTime at;
    private final boolean command;
    private final boolean daily;
    private final long id;
    private final String text;

    public static /* synthetic */ Reminder copy$default(Reminder reminder, long j, LocalDateTime localDateTime, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = reminder.id;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            localDateTime = reminder.at;
        }
        LocalDateTime localDateTime2 = localDateTime;
        if ((i & 4) != 0) {
            str = reminder.text;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            z = reminder.daily;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = reminder.command;
        }
        return reminder.copy(j2, localDateTime2, str2, z3, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LocalDateTime getAt() {
        return this.at;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getDaily() {
        return this.daily;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getCommand() {
        return this.command;
    }

    public final Reminder copy(long id, LocalDateTime at, String text, boolean daily, boolean command) {
        Intrinsics.checkNotNullParameter(at, "at");
        Intrinsics.checkNotNullParameter(text, "text");
        return new Reminder(id, at, text, daily, command);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Reminder)) {
            return false;
        }
        Reminder reminder = (Reminder) other;
        return this.id == reminder.id && Intrinsics.areEqual(this.at, reminder.at) && Intrinsics.areEqual(this.text, reminder.text) && this.daily == reminder.daily && this.command == reminder.command;
    }

    public int hashCode() {
        return (((((((Long.hashCode(this.id) * 31) + this.at.hashCode()) * 31) + this.text.hashCode()) * 31) + Boolean.hashCode(this.daily)) * 31) + Boolean.hashCode(this.command);
    }

    public String toString() {
        return "Reminder(id=" + this.id + ", at=" + this.at + ", text=" + this.text + ", daily=" + this.daily + ", command=" + this.command + ")";
    }

    public Reminder(long id, LocalDateTime at, String text, boolean daily, boolean command) {
        Intrinsics.checkNotNullParameter(at, "at");
        Intrinsics.checkNotNullParameter(text, "text");
        this.id = id;
        this.at = at;
        this.text = text;
        this.daily = daily;
        this.command = command;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Reminder(long j, LocalDateTime localDateTime, String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        boolean z3;
        boolean z4;
        if ((i & 8) == 0) {
            z3 = z;
        } else {
            z3 = false;
        }
        if ((i & 16) == 0) {
            z4 = z2;
        } else {
            z4 = false;
        }
        this(j, localDateTime, str, z3, z4);
    }

    public final long getId() {
        return this.id;
    }

    public final LocalDateTime getAt() {
        return this.at;
    }

    public final String getText() {
        return this.text;
    }

    public final boolean getDaily() {
        return this.daily;
    }

    public final boolean getCommand() {
        return this.command;
    }
}
