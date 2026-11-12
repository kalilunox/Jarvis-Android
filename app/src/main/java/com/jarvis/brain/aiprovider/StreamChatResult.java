package com.jarvis.brain.aiprovider;

import com.jarvis.app.VoiceService;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\bHÆ\u0003JF\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/jarvis/brain/aiprovider/StreamChatResult;", "", "code", "", "errorBody", "", VoiceService.EXTRA_TEXT, "usage", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getErrorBody", "()Ljava/lang/String;", "getText", "getUsage", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Lcom/jarvis/brain/aiprovider/StreamChatResult;", "equals", "", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StreamChatResult {
    private final Integer code;
    private final String errorBody;
    private final String text;
    private final Map<String, Object> usage;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StreamChatResult copy$default(StreamChatResult streamChatResult, Integer num, String str, String str2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            num = streamChatResult.code;
        }
        if ((i & 2) != 0) {
            str = streamChatResult.errorBody;
        }
        if ((i & 4) != 0) {
            str2 = streamChatResult.text;
        }
        if ((i & 8) != 0) {
            map = streamChatResult.usage;
        }
        return streamChatResult.copy(num, str, str2, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getErrorBody() {
        return this.errorBody;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getText() {
        return this.text;
    }

    public final Map<String, Object> component4() {
        return this.usage;
    }

    public final StreamChatResult copy(Integer code, String errorBody, String text, Map<String, ? extends Object> usage) {
        Intrinsics.checkNotNullParameter(errorBody, "errorBody");
        Intrinsics.checkNotNullParameter(text, "text");
        return new StreamChatResult(code, errorBody, text, usage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamChatResult)) {
            return false;
        }
        StreamChatResult streamChatResult = (StreamChatResult) other;
        return Intrinsics.areEqual(this.code, streamChatResult.code) && Intrinsics.areEqual(this.errorBody, streamChatResult.errorBody) && Intrinsics.areEqual(this.text, streamChatResult.text) && Intrinsics.areEqual(this.usage, streamChatResult.usage);
    }

    public int hashCode() {
        return ((((((this.code == null ? 0 : this.code.hashCode()) * 31) + this.errorBody.hashCode()) * 31) + this.text.hashCode()) * 31) + (this.usage != null ? this.usage.hashCode() : 0);
    }

    public String toString() {
        return "StreamChatResult(code=" + this.code + ", errorBody=" + this.errorBody + ", text=" + this.text + ", usage=" + this.usage + ")";
    }

    public StreamChatResult(Integer code, String errorBody, String text, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(errorBody, "errorBody");
        Intrinsics.checkNotNullParameter(text, "text");
        this.code = code;
        this.errorBody = errorBody;
        this.text = text;
        this.usage = map;
    }

    public final Integer getCode() {
        return this.code;
    }

    public final String getErrorBody() {
        return this.errorBody;
    }

    public final String getText() {
        return this.text;
    }

    public final Map<String, Object> getUsage() {
        return this.usage;
    }
}
