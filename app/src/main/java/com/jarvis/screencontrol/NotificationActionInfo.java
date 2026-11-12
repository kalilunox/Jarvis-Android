package com.jarvis.screencontrol;

import android.app.PendingIntent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisNotificationListenerService.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J3\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/jarvis/screencontrol/NotificationActionInfo;", "", "index", "", "title", "", "actionIntent", "Landroid/app/PendingIntent;", "canReply", "", "<init>", "(ILjava/lang/String;Landroid/app/PendingIntent;Z)V", "getIndex", "()I", "getTitle", "()Ljava/lang/String;", "getActionIntent", "()Landroid/app/PendingIntent;", "getCanReply", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class NotificationActionInfo {
    private final PendingIntent actionIntent;
    private final boolean canReply;
    private final int index;
    private final String title;

    public static /* synthetic */ NotificationActionInfo copy$default(NotificationActionInfo notificationActionInfo, int i, String str, PendingIntent pendingIntent, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = notificationActionInfo.index;
        }
        if ((i2 & 2) != 0) {
            str = notificationActionInfo.title;
        }
        if ((i2 & 4) != 0) {
            pendingIntent = notificationActionInfo.actionIntent;
        }
        if ((i2 & 8) != 0) {
            z = notificationActionInfo.canReply;
        }
        return notificationActionInfo.copy(i, str, pendingIntent, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final PendingIntent getActionIntent() {
        return this.actionIntent;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getCanReply() {
        return this.canReply;
    }

    public final NotificationActionInfo copy(int index, String title, PendingIntent actionIntent, boolean canReply) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new NotificationActionInfo(index, title, actionIntent, canReply);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationActionInfo)) {
            return false;
        }
        NotificationActionInfo notificationActionInfo = (NotificationActionInfo) other;
        return this.index == notificationActionInfo.index && Intrinsics.areEqual(this.title, notificationActionInfo.title) && Intrinsics.areEqual(this.actionIntent, notificationActionInfo.actionIntent) && this.canReply == notificationActionInfo.canReply;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.index) * 31) + this.title.hashCode()) * 31) + (this.actionIntent == null ? 0 : this.actionIntent.hashCode())) * 31) + Boolean.hashCode(this.canReply);
    }

    public String toString() {
        return "NotificationActionInfo(index=" + this.index + ", title=" + this.title + ", actionIntent=" + this.actionIntent + ", canReply=" + this.canReply + ")";
    }

    public NotificationActionInfo(int index, String title, PendingIntent actionIntent, boolean canReply) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.index = index;
        this.title = title;
        this.actionIntent = actionIntent;
        this.canReply = canReply;
    }

    public /* synthetic */ NotificationActionInfo(int i, String str, PendingIntent pendingIntent, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, pendingIntent, (i2 & 8) != 0 ? false : z);
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getTitle() {
        return this.title;
    }

    public final PendingIntent getActionIntent() {
        return this.actionIntent;
    }

    public final boolean getCanReply() {
        return this.canReply;
    }
}
