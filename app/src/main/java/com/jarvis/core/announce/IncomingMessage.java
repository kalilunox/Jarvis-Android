package com.jarvis.core.announce;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Announcements.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/announce/IncomingMessage;", "", "appPackage", "", "sender", VoiceService.EXTRA_TEXT, "group", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAppPackage", "()Ljava/lang/String;", "getSender", "getText", "getGroup", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class IncomingMessage {
    private final String appPackage;
    private final boolean group;
    private final String sender;
    private final String text;

    public static /* synthetic */ IncomingMessage copy$default(IncomingMessage incomingMessage, String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = incomingMessage.appPackage;
        }
        if ((i & 2) != 0) {
            str2 = incomingMessage.sender;
        }
        if ((i & 4) != 0) {
            str3 = incomingMessage.text;
        }
        if ((i & 8) != 0) {
            z = incomingMessage.group;
        }
        return incomingMessage.copy(str, str2, str3, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAppPackage() {
        return this.appPackage;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSender() {
        return this.sender;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getGroup() {
        return this.group;
    }

    public final IncomingMessage copy(String appPackage, String sender, String text, boolean group) {
        Intrinsics.checkNotNullParameter(appPackage, "appPackage");
        Intrinsics.checkNotNullParameter(sender, "sender");
        Intrinsics.checkNotNullParameter(text, "text");
        return new IncomingMessage(appPackage, sender, text, group);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IncomingMessage)) {
            return false;
        }
        IncomingMessage incomingMessage = (IncomingMessage) other;
        return Intrinsics.areEqual(this.appPackage, incomingMessage.appPackage) && Intrinsics.areEqual(this.sender, incomingMessage.sender) && Intrinsics.areEqual(this.text, incomingMessage.text) && this.group == incomingMessage.group;
    }

    public int hashCode() {
        return (((((this.appPackage.hashCode() * 31) + this.sender.hashCode()) * 31) + this.text.hashCode()) * 31) + Boolean.hashCode(this.group);
    }

    public String toString() {
        return "IncomingMessage(appPackage=" + this.appPackage + ", sender=" + this.sender + ", text=" + this.text + ", group=" + this.group + ")";
    }

    public IncomingMessage(String appPackage, String sender, String text, boolean group) {
        Intrinsics.checkNotNullParameter(appPackage, "appPackage");
        Intrinsics.checkNotNullParameter(sender, "sender");
        Intrinsics.checkNotNullParameter(text, "text");
        this.appPackage = appPackage;
        this.sender = sender;
        this.text = text;
        this.group = group;
    }

    public /* synthetic */ IncomingMessage(String str, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? false : z);
    }

    public final String getAppPackage() {
        return this.appPackage;
    }

    public final String getSender() {
        return this.sender;
    }

    public final String getText() {
        return this.text;
    }

    public final boolean getGroup() {
        return this.group;
    }
}
