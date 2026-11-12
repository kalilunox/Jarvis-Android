package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/jarvis/brain/aiprovider/CloudLlmHttpResponse;", "", "code", "", "body", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getBody", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CloudLlmHttpResponse {
    private final String body;
    private final int code;

    public static /* synthetic */ CloudLlmHttpResponse copy$default(CloudLlmHttpResponse cloudLlmHttpResponse, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = cloudLlmHttpResponse.code;
        }
        if ((i2 & 2) != 0) {
            str = cloudLlmHttpResponse.body;
        }
        return cloudLlmHttpResponse.copy(i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    public final CloudLlmHttpResponse copy(int code, String body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return new CloudLlmHttpResponse(code, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CloudLlmHttpResponse)) {
            return false;
        }
        CloudLlmHttpResponse cloudLlmHttpResponse = (CloudLlmHttpResponse) other;
        return this.code == cloudLlmHttpResponse.code && Intrinsics.areEqual(this.body, cloudLlmHttpResponse.body);
    }

    public int hashCode() {
        return (Integer.hashCode(this.code) * 31) + this.body.hashCode();
    }

    public String toString() {
        return "CloudLlmHttpResponse(code=" + this.code + ", body=" + this.body + ")";
    }

    public CloudLlmHttpResponse(int code, String body) {
        Intrinsics.checkNotNullParameter(body, "body");
        this.code = code;
        this.body = body;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getBody() {
        return this.body;
    }
}
