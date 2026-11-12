package com.jarvis.brain.aiprovider;

import com.google.android.gms.common.internal.ImagesContract;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J<\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016JP\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016Jy\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2'\u0010\u0014\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00160\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016¨\u0006\u001a"}, d2 = {"Lcom/jarvis/brain/aiprovider/UrlConnectionCloudLlmTransport;", "Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;", "Lcom/jarvis/brain/aiprovider/CloudLlmStreamTransport;", "<init>", "()V", "postJson", "Lcom/jarvis/brain/aiprovider/CloudLlmHttpResponse;", ImagesContract.URL, "", "headers", "", "body", "connectTimeoutMs", "", "readTimeoutMs", "postJsonStream", "onLine", "Lkotlin/Function1;", "", "postJsonStreamAbortable", "onAbortable", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", "name", "abort", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UrlConnectionCloudLlmTransport implements CloudLlmHttpTransport, CloudLlmStreamTransport {
    @Override // com.jarvis.brain.aiprovider.CloudLlmHttpTransport
    public CloudLlmHttpResponse postJson(String url, Map<String, String> headers, String body, int connectTimeoutMs, int readTimeoutMs) throws IOException {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(body, "body");
        URLConnection uRLConnectionOpenConnection = new URI(url).toURL().openConnection();
        Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection $this$postJson_u24lambda_u240 = (HttpURLConnection) uRLConnectionOpenConnection;
        $this$postJson_u24lambda_u240.setRequestMethod("POST");
        boolean z = true;
        $this$postJson_u24lambda_u240.setDoInput(true);
        $this$postJson_u24lambda_u240.setDoOutput(true);
        $this$postJson_u24lambda_u240.setConnectTimeout(connectTimeoutMs);
        $this$postJson_u24lambda_u240.setReadTimeout(readTimeoutMs);
        $this$postJson_u24lambda_u240.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        $this$postJson_u24lambda_u240.setRequestProperty("User-Agent", NaraRouterDefaults.USER_AGENT);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            $this$postJson_u24lambda_u240.setRequestProperty(k, v);
        }
        try {
            OutputStream outputStream = $this$postJson_u24lambda_u240.getOutputStream();
            try {
                OutputStream os = outputStream;
                byte[] bytes = body.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                os.write(bytes);
                os.flush();
                Unit unit = Unit.INSTANCE;
                String text = null;
                CloseableKt.closeFinally(outputStream, null);
                int code = $this$postJson_u24lambda_u240.getResponseCode();
                if (200 > code || code >= 300) {
                    z = false;
                }
                InputStream stream = z ? $this$postJson_u24lambda_u240.getInputStream() : $this$postJson_u24lambda_u240.getErrorStream();
                if (stream != null) {
                    Reader inputStreamReader = new InputStreamReader(stream, Charsets.UTF_8);
                    BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                    try {
                        BufferedReader it = bufferedReader;
                        String text2 = TextStreamsKt.readText(it);
                        CloseableKt.closeFinally(bufferedReader, null);
                        text = text2;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(bufferedReader, th);
                            throw th2;
                        }
                    }
                }
                if (text == null) {
                    text = "";
                }
                CloudLlmHttpResponse cloudLlmHttpResponse = new CloudLlmHttpResponse(code, text);
                $this$postJson_u24lambda_u240.disconnect();
                return cloudLlmHttpResponse;
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(outputStream, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            $this$postJson_u24lambda_u240.disconnect();
            throw th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit postJsonStream$lambda$0(Function0 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    @Override // com.jarvis.brain.aiprovider.CloudLlmStreamTransport
    public int postJsonStream(String url, Map<String, String> headers, String body, int connectTimeoutMs, int readTimeoutMs, Function1<? super String, Boolean> onLine) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(onLine, "onLine");
        return postJsonStreamAbortable(url, headers, body, connectTimeoutMs, readTimeoutMs, new Function1() { // from class: com.jarvis.brain.aiprovider.UrlConnectionCloudLlmTransport$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UrlConnectionCloudLlmTransport.postJsonStream$lambda$0((Function0) obj);
            }
        }, onLine);
    }

    @Override // com.jarvis.brain.aiprovider.CloudLlmStreamTransport
    public int postJsonStreamAbortable(String url, Map<String, String> headers, String body, int connectTimeoutMs, int readTimeoutMs, Function1<? super Function0<Unit>, Unit> onAbortable, Function1<? super String, Boolean> onLine) throws IOException {
        String line;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(onAbortable, "onAbortable");
        Intrinsics.checkNotNullParameter(onLine, "onLine");
        URLConnection uRLConnectionOpenConnection = new URI(url).toURL().openConnection();
        Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        final HttpURLConnection $this$postJsonStreamAbortable_u24lambda_u240 = (HttpURLConnection) uRLConnectionOpenConnection;
        $this$postJsonStreamAbortable_u24lambda_u240.setRequestMethod("POST");
        $this$postJsonStreamAbortable_u24lambda_u240.setDoInput(true);
        $this$postJsonStreamAbortable_u24lambda_u240.setDoOutput(true);
        $this$postJsonStreamAbortable_u24lambda_u240.setConnectTimeout(connectTimeoutMs);
        $this$postJsonStreamAbortable_u24lambda_u240.setReadTimeout(readTimeoutMs);
        $this$postJsonStreamAbortable_u24lambda_u240.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        $this$postJsonStreamAbortable_u24lambda_u240.setRequestProperty("Accept", "text/event-stream");
        $this$postJsonStreamAbortable_u24lambda_u240.setRequestProperty("User-Agent", NaraRouterDefaults.USER_AGENT);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            $this$postJsonStreamAbortable_u24lambda_u240.setRequestProperty(k, v);
        }
        onAbortable.invoke(new Function0() { // from class: com.jarvis.brain.aiprovider.UrlConnectionCloudLlmTransport$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UrlConnectionCloudLlmTransport.postJsonStreamAbortable$lambda$1($this$postJsonStreamAbortable_u24lambda_u240);
            }
        });
        try {
            OutputStream outputStream = $this$postJsonStreamAbortable_u24lambda_u240.getOutputStream();
            try {
                OutputStream os = outputStream;
                byte[] bytes = body.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                os.write(bytes);
                os.flush();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, null);
                int code = $this$postJsonStreamAbortable_u24lambda_u240.getResponseCode();
                boolean z = false;
                if (200 <= code && code < 300) {
                    z = true;
                }
                InputStream stream = z ? $this$postJsonStreamAbortable_u24lambda_u240.getInputStream() : $this$postJsonStreamAbortable_u24lambda_u240.getErrorStream();
                if (stream != null) {
                    Reader inputStreamReader = new InputStreamReader(stream, Charsets.UTF_8);
                    BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                    try {
                        BufferedReader reader = bufferedReader;
                        do {
                            line = reader.readLine();
                            if (line == null) {
                                break;
                            }
                        } while (onLine.invoke(line).booleanValue());
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(bufferedReader, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(bufferedReader, th);
                            throw th2;
                        }
                    }
                }
                $this$postJsonStreamAbortable_u24lambda_u240.disconnect();
                return code;
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(outputStream, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            $this$postJsonStreamAbortable_u24lambda_u240.disconnect();
            throw th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit postJsonStreamAbortable$lambda$1(HttpURLConnection $conn) {
        $conn.disconnect();
        return Unit.INSTANCE;
    }
}
