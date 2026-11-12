package com.jarvis.brain.aiprovider;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;", "", "postJson", "Lcom/jarvis/brain/aiprovider/CloudLlmHttpResponse;", ImagesContract.URL, "", "headers", "", "body", "connectTimeoutMs", "", "readTimeoutMs", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CloudLlmHttpTransport {
    CloudLlmHttpResponse postJson(String url, Map<String, String> headers, String body, int connectTimeoutMs, int readTimeoutMs);
}
