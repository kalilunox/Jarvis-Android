package com.jarvis.tools.net;

import com.google.android.gms.common.internal.ImagesContract;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SimpleHttp.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\rJ \u0010\u0010\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/tools/net/SimpleHttp;", "", "<init>", "()V", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/jarvis/tools/net/SimpleHttp$Entry;", "get", ImagesContract.URL, "ttlMs", "", "timeoutMs", "", "accept", "maxAttempts", "getOnce", "getJson", "Lorg/json/JSONObject;", "getJsonArray", "Lorg/json/JSONArray;", "getJsonOrNull", "getJsonArrayOrNull", "Entry", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SimpleHttp {
    public static final SimpleHttp INSTANCE = new SimpleHttp();
    private static final ConcurrentHashMap<String, Entry> cache = new ConcurrentHashMap<>();

    private SimpleHttp() {
    }

    /* JADX INFO: compiled from: SimpleHttp.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/tools/net/SimpleHttp$Entry;", "", "body", "", "expiresAt", "", "<init>", "(Ljava/lang/String;J)V", "getBody", "()Ljava/lang/String;", "getExpiresAt", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Entry {
        private final String body;
        private final long expiresAt;

        public static /* synthetic */ Entry copy$default(Entry entry, String str, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = entry.body;
            }
            if ((i & 2) != 0) {
                j = entry.expiresAt;
            }
            return entry.copy(str, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBody() {
            return this.body;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getExpiresAt() {
            return this.expiresAt;
        }

        public final Entry copy(String body, long expiresAt) {
            Intrinsics.checkNotNullParameter(body, "body");
            return new Entry(body, expiresAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return Intrinsics.areEqual(this.body, entry.body) && this.expiresAt == entry.expiresAt;
        }

        public int hashCode() {
            return (this.body.hashCode() * 31) + Long.hashCode(this.expiresAt);
        }

        public String toString() {
            return "Entry(body=" + this.body + ", expiresAt=" + this.expiresAt + ")";
        }

        public Entry(String body, long expiresAt) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.body = body;
            this.expiresAt = expiresAt;
        }

        public final String getBody() {
            return this.body;
        }

        public final long getExpiresAt() {
            return this.expiresAt;
        }
    }

    public static /* synthetic */ String get$default(SimpleHttp simpleHttp, String str, long j, int i, String str2, int i2, int i3, Object obj) {
        return simpleHttp.get(str, (i3 & 2) != 0 ? 0L : j, (i3 & 4) != 0 ? 8000 : i, (i3 & 8) != 0 ? "application/json, text/plain, */*" : str2, (i3 & 16) != 0 ? 3 : i2);
    }

    public final String get(String url, long ttlMs, int timeoutMs, String accept, int maxAttempts) throws Exception {
        Entry hit;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(accept, "accept");
        long j = 0;
        if (ttlMs > 0 && (hit = cache.get(url)) != null && hit.getExpiresAt() > System.currentTimeMillis()) {
            return hit.getBody();
        }
        boolean z = false;
        Object lastError = null;
        int i = 0;
        while (i < maxAttempts) {
            int attempt = i;
            try {
                try {
                    String body = INSTANCE.getOnce(url, timeoutMs, accept);
                    if (ttlMs > j) {
                        cache.put(url, new Entry(body, System.currentTimeMillis() + ttlMs));
                    }
                    return body;
                } catch (Exception e) {
                    e = e;
                    Object lastError2 = e;
                    String msg = e.getMessage();
                    if (msg == null) {
                        msg = "";
                    }
                    boolean z2 = true;
                    if (!StringsKt.contains$default(msg, "HTTP 429", z, 2, (Object) null) && !StringsKt.contains$default(msg, "HTTP 503", z, 2, (Object) null) && !StringsKt.contains$default(msg, "HTTP 502", z, 2, (Object) null) && !StringsKt.contains((CharSequence) msg, (CharSequence) "timed out", true) && !StringsKt.contains((CharSequence) msg, (CharSequence) "Timeout", true)) {
                        z2 = z;
                    }
                    boolean retryable = z2;
                    if (!retryable || attempt == maxAttempts - 1) {
                        throw e;
                    }
                    long backoff = (((long) (attempt + 1)) * 350) + (((long) attempt) * 50);
                    Thread.sleep(RangesKt.coerceAtMost(backoff, 2000L));
                    i++;
                    lastError = lastError2;
                    j = 0;
                    z = false;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        if (lastError != null) {
            throw ((Throwable) lastError);
        }
        throw new IllegalStateException("HTTP failed");
    }

    private final String getOnce(String url, int timeoutMs, String accept) throws IOException {
        URLConnection uRLConnectionOpenConnection = new URL(url).openConnection();
        Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection conn = (HttpURLConnection) uRLConnectionOpenConnection;
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(timeoutMs);
        conn.setReadTimeout(timeoutMs);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Accept", accept);
        conn.setRequestProperty("User-Agent", "JarvisAndroid/0.2 (local voice assistant; contact: jarvis-android@local)");
        conn.setRequestProperty("Accept-Language", "ru,en;q=0.8");
        try {
            int code = conn.getResponseCode();
            InputStream stream = 200 <= code && code < 300 ? conn.getInputStream() : conn.getErrorStream();
            if (stream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, Charsets.UTF_8));
                try {
                    BufferedReader it = bufferedReader;
                    String body = TextStreamsKt.readText(it);
                    CloseableKt.closeFinally(bufferedReader, null);
                    if (!(200 <= code && code < 300)) {
                        throw new IllegalStateException(("HTTP " + code + ": " + StringsKt.take(body, 200)).toString());
                    }
                    conn.disconnect();
                    return body;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(bufferedReader, th);
                        throw th2;
                    }
                }
            }
            conn.disconnect();
            return "";
        } catch (Throwable th3) {
            conn.disconnect();
            throw th3;
        }
    }

    public static /* synthetic */ JSONObject getJson$default(SimpleHttp simpleHttp, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return simpleHttp.getJson(str, j);
    }

    public final JSONObject getJson(String url, long ttlMs) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new JSONObject(get$default(this, url, ttlMs, 0, null, 0, 28, null));
    }

    public static /* synthetic */ JSONArray getJsonArray$default(SimpleHttp simpleHttp, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return simpleHttp.getJsonArray(str, j);
    }

    public final JSONArray getJsonArray(String url, long ttlMs) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new JSONArray(get$default(this, url, ttlMs, 0, null, 0, 28, null));
    }

    public static /* synthetic */ JSONObject getJsonOrNull$default(SimpleHttp simpleHttp, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return simpleHttp.getJsonOrNull(str, j);
    }

    public final JSONObject getJsonOrNull(String url, long ttlMs) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            Result.Companion companion = Result.INSTANCE;
            SimpleHttp $this$getJsonOrNull_u24lambda_u240 = this;
            objM392constructorimpl = Result.m392constructorimpl($this$getJsonOrNull_u24lambda_u240.getJson(url, ttlMs));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        return (JSONObject) objM392constructorimpl;
    }

    public static /* synthetic */ JSONArray getJsonArrayOrNull$default(SimpleHttp simpleHttp, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return simpleHttp.getJsonArrayOrNull(str, j);
    }

    public final JSONArray getJsonArrayOrNull(String url, long ttlMs) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            Result.Companion companion = Result.INSTANCE;
            SimpleHttp $this$getJsonArrayOrNull_u24lambda_u240 = this;
            objM392constructorimpl = Result.m392constructorimpl($this$getJsonArrayOrNull_u24lambda_u240.getJsonArray(url, ttlMs));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        return (JSONArray) objM392constructorimpl;
    }
}
