package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProactiveTextBuilder.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/proactive/ProactiveText;", "", "voice", "", "title", "body", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getVoice", "()Ljava/lang/String;", "getTitle", "getBody", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProactiveText {
    private final String body;
    private final String title;
    private final String voice;

    public static /* synthetic */ ProactiveText copy$default(ProactiveText proactiveText, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = proactiveText.voice;
        }
        if ((i & 2) != 0) {
            str2 = proactiveText.title;
        }
        if ((i & 4) != 0) {
            str3 = proactiveText.body;
        }
        return proactiveText.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getVoice() {
        return this.voice;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    public final ProactiveText copy(String voice, String title, String body) {
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        return new ProactiveText(voice, title, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProactiveText)) {
            return false;
        }
        ProactiveText proactiveText = (ProactiveText) other;
        return Intrinsics.areEqual(this.voice, proactiveText.voice) && Intrinsics.areEqual(this.title, proactiveText.title) && Intrinsics.areEqual(this.body, proactiveText.body);
    }

    public int hashCode() {
        return (((this.voice.hashCode() * 31) + this.title.hashCode()) * 31) + this.body.hashCode();
    }

    public String toString() {
        return "ProactiveText(voice=" + this.voice + ", title=" + this.title + ", body=" + this.body + ")";
    }

    public ProactiveText(String voice, String title, String body) {
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        this.voice = voice;
        this.title = title;
        this.body = body;
    }

    public final String getVoice() {
        return this.voice;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getBody() {
        return this.body;
    }
}
