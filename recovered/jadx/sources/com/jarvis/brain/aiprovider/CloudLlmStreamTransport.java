package com.jarvis.brain.aiprovider;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u00002\u00020\u0001JP\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\fH&Jy\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032'\u0010\u000f\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00110\f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\fH\u0016¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/aiprovider/CloudLlmStreamTransport;", "", "postJsonStream", "", ImagesContract.URL, "", "headers", "", "body", "connectTimeoutMs", "readTimeoutMs", "onLine", "Lkotlin/Function1;", "", "postJsonStreamAbortable", "onAbortable", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", "name", "abort", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CloudLlmStreamTransport {
    int postJsonStream(String url, Map<String, String> headers, String body, int connectTimeoutMs, int readTimeoutMs, Function1<? super String, Boolean> onLine);

    /* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static int postJsonStreamAbortable(CloudLlmStreamTransport $this, String url, Map<String, String> headers, String body, int connectTimeoutMs, int readTimeoutMs, Function1<? super Function0<Unit>, Unit> onAbortable, Function1<? super String, Boolean> onLine) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(body, "body");
            Intrinsics.checkNotNullParameter(onAbortable, "onAbortable");
            Intrinsics.checkNotNullParameter(onLine, "onLine");
            return CloudLlmStreamTransport.super.postJsonStreamAbortable(url, headers, body, connectTimeoutMs, readTimeoutMs, onAbortable, onLine);
        }
    }

    default int postJsonStreamAbortable(String url, Map<String, String> headers, String body, int connectTimeoutMs, int readTimeoutMs, Function1<? super Function0<Unit>, Unit> onAbortable, Function1<? super String, Boolean> onLine) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(onAbortable, "onAbortable");
        Intrinsics.checkNotNullParameter(onLine, "onLine");
        return postJsonStream(url, headers, body, connectTimeoutMs, readTimeoutMs, onLine);
    }
}
