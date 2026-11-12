package com.jarvis.core.common.model;

import com.jarvis.app.VoiceService;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserRequest.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006 "}, d2 = {"Lcom/jarvis/core/common/model/UserRequest;", "", VoiceService.EXTRA_TEXT, "", "source", "Lcom/jarvis/core/common/model/RequestSource;", "requestId", "timestampMillis", "", "language", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/common/model/RequestSource;Ljava/lang/String;JLjava/lang/String;)V", "getText", "()Ljava/lang/String;", "getSource", "()Lcom/jarvis/core/common/model/RequestSource;", "getRequestId", "getTimestampMillis", "()J", "getLanguage", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class UserRequest {
    private final String language;
    private final String requestId;
    private final RequestSource source;
    private final String text;
    private final long timestampMillis;

    public static /* synthetic */ UserRequest copy$default(UserRequest userRequest, String str, RequestSource requestSource, String str2, long j, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userRequest.text;
        }
        if ((i & 2) != 0) {
            requestSource = userRequest.source;
        }
        RequestSource requestSource2 = requestSource;
        if ((i & 4) != 0) {
            str2 = userRequest.requestId;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            j = userRequest.timestampMillis;
        }
        long j2 = j;
        if ((i & 16) != 0) {
            str3 = userRequest.language;
        }
        return userRequest.copy(str, requestSource2, str4, j2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final RequestSource getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getTimestampMillis() {
        return this.timestampMillis;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    public final UserRequest copy(String text, RequestSource source, String requestId, long timestampMillis, String language) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(language, "language");
        return new UserRequest(text, source, requestId, timestampMillis, language);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserRequest)) {
            return false;
        }
        UserRequest userRequest = (UserRequest) other;
        return Intrinsics.areEqual(this.text, userRequest.text) && this.source == userRequest.source && Intrinsics.areEqual(this.requestId, userRequest.requestId) && this.timestampMillis == userRequest.timestampMillis && Intrinsics.areEqual(this.language, userRequest.language);
    }

    public int hashCode() {
        return (((((((this.text.hashCode() * 31) + this.source.hashCode()) * 31) + this.requestId.hashCode()) * 31) + Long.hashCode(this.timestampMillis)) * 31) + this.language.hashCode();
    }

    public String toString() {
        return "UserRequest(text=" + this.text + ", source=" + this.source + ", requestId=" + this.requestId + ", timestampMillis=" + this.timestampMillis + ", language=" + this.language + ")";
    }

    public UserRequest(String text, RequestSource source, String requestId, long timestampMillis, String language) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(language, "language");
        this.text = text;
        this.source = source;
        this.requestId = requestId;
        this.timestampMillis = timestampMillis;
        this.language = language;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ UserRequest(String str, RequestSource requestSource, String str2, long j, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str4;
        RequestSource requestSource2 = (i & 2) != 0 ? RequestSource.TEXT : requestSource;
        if ((i & 4) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str4 = string;
        } else {
            str4 = str2;
        }
        this(str, requestSource2, str4, (i & 8) != 0 ? System.currentTimeMillis() : j, (i & 16) != 0 ? "ru" : str3);
    }

    public final String getText() {
        return this.text;
    }

    public final RequestSource getSource() {
        return this.source;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final long getTimestampMillis() {
        return this.timestampMillis;
    }

    public final String getLanguage() {
        return this.language;
    }
}
