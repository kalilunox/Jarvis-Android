package com.jarvis.screencontrol;

import com.jarvis.app.VoiceService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisNotificationListenerService.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JK\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/jarvis/screencontrol/NotificationInfo;", "", "key", "", "packageName", "title", VoiceService.EXTRA_TEXT, "postTime", "", "actions", "", "Lcom/jarvis/screencontrol/NotificationActionInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V", "getKey", "()Ljava/lang/String;", "getPackageName", "getTitle", "getText", "getPostTime", "()J", "getActions", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class NotificationInfo {
    private final List<NotificationActionInfo> actions;
    private final String key;
    private final String packageName;
    private final long postTime;
    private final String text;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NotificationInfo copy$default(NotificationInfo notificationInfo, String str, String str2, String str3, String str4, long j, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = notificationInfo.key;
        }
        if ((i & 2) != 0) {
            str2 = notificationInfo.packageName;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = notificationInfo.title;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = notificationInfo.text;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            j = notificationInfo.postTime;
        }
        long j2 = j;
        if ((i & 32) != 0) {
            list = notificationInfo.actions;
        }
        return notificationInfo.copy(str, str5, str6, str7, j2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getPostTime() {
        return this.postTime;
    }

    public final List<NotificationActionInfo> component6() {
        return this.actions;
    }

    public final NotificationInfo copy(String key, String packageName, String title, String text, long postTime, List<NotificationActionInfo> actions) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(actions, "actions");
        return new NotificationInfo(key, packageName, title, text, postTime, actions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationInfo)) {
            return false;
        }
        NotificationInfo notificationInfo = (NotificationInfo) other;
        return Intrinsics.areEqual(this.key, notificationInfo.key) && Intrinsics.areEqual(this.packageName, notificationInfo.packageName) && Intrinsics.areEqual(this.title, notificationInfo.title) && Intrinsics.areEqual(this.text, notificationInfo.text) && this.postTime == notificationInfo.postTime && Intrinsics.areEqual(this.actions, notificationInfo.actions);
    }

    public int hashCode() {
        return (((((((((this.key.hashCode() * 31) + this.packageName.hashCode()) * 31) + this.title.hashCode()) * 31) + this.text.hashCode()) * 31) + Long.hashCode(this.postTime)) * 31) + this.actions.hashCode();
    }

    public String toString() {
        return "NotificationInfo(key=" + this.key + ", packageName=" + this.packageName + ", title=" + this.title + ", text=" + this.text + ", postTime=" + this.postTime + ", actions=" + this.actions + ")";
    }

    public NotificationInfo(String key, String packageName, String title, String text, long postTime, List<NotificationActionInfo> actions) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(actions, "actions");
        this.key = key;
        this.packageName = packageName;
        this.title = title;
        this.text = text;
        this.postTime = postTime;
        this.actions = actions;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getText() {
        return this.text;
    }

    public final long getPostTime() {
        return this.postTime;
    }

    public final List<NotificationActionInfo> getActions() {
        return this.actions;
    }
}
