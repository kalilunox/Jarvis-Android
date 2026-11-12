package com.jarvis.core.reminder;

import com.jarvis.app.VoiceService;
import java.time.LocalDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReminderParser.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/reminder/ReminderRequest;", "", "at", "Ljava/time/LocalDateTime;", VoiceService.EXTRA_TEXT, "", "daily", "", "<init>", "(Ljava/time/LocalDateTime;Ljava/lang/String;Z)V", "getAt", "()Ljava/time/LocalDateTime;", "getText", "()Ljava/lang/String;", "getDaily", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ReminderRequest {
    private final LocalDateTime at;
    private final boolean daily;
    private final String text;

    public static /* synthetic */ ReminderRequest copy$default(ReminderRequest reminderRequest, LocalDateTime localDateTime, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            localDateTime = reminderRequest.at;
        }
        if ((i & 2) != 0) {
            str = reminderRequest.text;
        }
        if ((i & 4) != 0) {
            z = reminderRequest.daily;
        }
        return reminderRequest.copy(localDateTime, str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LocalDateTime getAt() {
        return this.at;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getDaily() {
        return this.daily;
    }

    public final ReminderRequest copy(LocalDateTime at, String text, boolean daily) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new ReminderRequest(at, text, daily);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReminderRequest)) {
            return false;
        }
        ReminderRequest reminderRequest = (ReminderRequest) other;
        return Intrinsics.areEqual(this.at, reminderRequest.at) && Intrinsics.areEqual(this.text, reminderRequest.text) && this.daily == reminderRequest.daily;
    }

    public int hashCode() {
        return ((((this.at == null ? 0 : this.at.hashCode()) * 31) + this.text.hashCode()) * 31) + Boolean.hashCode(this.daily);
    }

    public String toString() {
        return "ReminderRequest(at=" + this.at + ", text=" + this.text + ", daily=" + this.daily + ")";
    }

    public ReminderRequest(LocalDateTime at, String text, boolean daily) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.at = at;
        this.text = text;
        this.daily = daily;
    }

    public /* synthetic */ ReminderRequest(LocalDateTime localDateTime, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(localDateTime, str, (i & 4) != 0 ? false : z);
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
}
