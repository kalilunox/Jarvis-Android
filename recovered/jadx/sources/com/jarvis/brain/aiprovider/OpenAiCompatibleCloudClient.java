package com.jarvis.brain.aiprovider;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.storage.JarvisSchema;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0007\u0018\u00002\u00020\u0001BM\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010JY\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00132\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ}\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00132\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u001b2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0!¢\u0006\u0004\b#\u0010$J\u009b\u0001\u0010%\u001a\u00020&2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u001b2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032)\b\u0002\u0010'\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\"0\u0003¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\"0!2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0!J#\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b2\u0006\u0010,\u001a\u00020\u0004H\u0000¢\u0006\u0002\b-J\u0017\u0010.\u001a\u0004\u0018\u00010\u00042\u0006\u0010/\u001a\u00020\u0004H\u0000¢\u0006\u0002\b0J\u0017\u00101\u001a\u0004\u0018\u00010\u00042\u0006\u0010,\u001a\u00020\u0004H\u0000¢\u0006\u0002\b2J=\u00103\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004040\u00182\u0006\u0010\u0015\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\u0006\u0010\u0016\u001a\u00020\u0004H\u0000¢\u0006\u0002\b5JP\u00106\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0018\u00107\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004040\u00182\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u00102\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u001bH\u0002J\u0010\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u0004H\u0002J\u0017\u0010?\u001a\u0004\u0018\u00010\u00042\u0006\u0010,\u001a\u00020\u0004H\u0000¢\u0006\u0002\b@R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020\u00040:X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<¨\u0006A"}, d2 = {"Lcom/jarvis/brain/aiprovider/OpenAiCompatibleCloudClient;", "", "baseUrlProvider", "Lkotlin/Function0;", "", "apiKeyProvider", NotificationCompat.CATEGORY_TRANSPORT, "Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;", "streamTransport", "Lcom/jarvis/brain/aiprovider/CloudLlmStreamTransport;", "connectTimeoutMs", "", "readTimeoutMs", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;Lcom/jarvis/brain/aiprovider/CloudLlmStreamTransport;II)V", "hasApiKey", "", "isConfigured", "chatCompletion", "Lkotlin/Result;", "model", "systemPrompt", "userText", "history", "", "maxTokens", "extraBody", "", "chatCompletion-hUnOzRk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/util/Map;)Ljava/lang/Object;", "chatCompletionStream", "isCancelled", "onDelta", "Lkotlin/Function1;", "", "chatCompletionStream-eH_QyT8", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/util/Map;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "streamChat", "Lcom/jarvis/brain/aiprovider/StreamChatResult;", "onAbortable", "Lkotlin/ParameterName;", "name", "abort", "extractStreamUsage", "json", "extractStreamUsage$brain_debug", "parseSseDataLine", "line", "parseSseDataLine$brain_debug", "extractStreamDelta", "extractStreamDelta$brain_debug", "buildMessages", "Lkotlin/Pair;", "buildMessages$brain_debug", "buildJsonBody", "messages", "stream", "PROTECTED_BODY_KEYS", "", "getPROTECTED_BODY_KEYS$brain_debug", "()Ljava/util/Set;", "jsonString", "s", "extractAssistantContent", "extractAssistantContent$brain_debug", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OpenAiCompatibleCloudClient {
    private final Set<String> PROTECTED_BODY_KEYS;
    private final Function0<String> apiKeyProvider;
    private final Function0<String> baseUrlProvider;
    private final int connectTimeoutMs;
    private final int readTimeoutMs;
    private final CloudLlmStreamTransport streamTransport;
    private final CloudLlmHttpTransport transport;

    public OpenAiCompatibleCloudClient(Function0<String> baseUrlProvider, Function0<String> apiKeyProvider, CloudLlmHttpTransport transport, CloudLlmStreamTransport streamTransport, int connectTimeoutMs, int readTimeoutMs) {
        Intrinsics.checkNotNullParameter(baseUrlProvider, "baseUrlProvider");
        Intrinsics.checkNotNullParameter(apiKeyProvider, "apiKeyProvider");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Intrinsics.checkNotNullParameter(streamTransport, "streamTransport");
        this.baseUrlProvider = baseUrlProvider;
        this.apiKeyProvider = apiKeyProvider;
        this.transport = transport;
        this.streamTransport = streamTransport;
        this.connectTimeoutMs = connectTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
        this.PROTECTED_BODY_KEYS = SetsKt.setOf((Object[]) new String[]{"model", "messages", "stream", "max_tokens", "temperature"});
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ OpenAiCompatibleCloudClient(Function0 function0, Function0 function1, CloudLlmHttpTransport cloudLlmHttpTransport, CloudLlmStreamTransport cloudLlmStreamTransport, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        CloudLlmStreamTransport urlConnectionCloudLlmTransport;
        CloudLlmHttpTransport urlConnectionCloudLlmTransport2 = (i3 & 4) != 0 ? new UrlConnectionCloudLlmTransport() : cloudLlmHttpTransport;
        if ((i3 & 8) != 0) {
            UrlConnectionCloudLlmTransport urlConnectionCloudLlmTransport3 = urlConnectionCloudLlmTransport2 instanceof CloudLlmStreamTransport ? (CloudLlmStreamTransport) urlConnectionCloudLlmTransport2 : null;
            urlConnectionCloudLlmTransport = urlConnectionCloudLlmTransport3 == null ? new UrlConnectionCloudLlmTransport() : urlConnectionCloudLlmTransport3;
        } else {
            urlConnectionCloudLlmTransport = cloudLlmStreamTransport;
        }
        this(function0, function1, urlConnectionCloudLlmTransport2, urlConnectionCloudLlmTransport, (i3 & 16) != 0 ? 12000 : i, (i3 & 32) != 0 ? 45000 : i2);
    }

    public final boolean hasApiKey() {
        String strInvoke = this.apiKeyProvider.invoke();
        String string = strInvoke != null ? StringsKt.trim((CharSequence) strInvoke).toString() : null;
        return !(string == null || StringsKt.isBlank(string));
    }

    public final boolean isConfigured() {
        return hasApiKey() && !StringsKt.isBlank(StringsKt.trim((CharSequence) this.baseUrlProvider.invoke()).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: chatCompletion-hUnOzRk$default, reason: not valid java name */
    public static /* synthetic */ Object m336chatCompletionhUnOzRk$default(OpenAiCompatibleCloudClient openAiCompatibleCloudClient, String str, String str2, String str3, List list, int i, Map map, int i2, Object obj) {
        int i3;
        Map mapEmptyMap;
        if ((i2 & 16) == 0) {
            i3 = i;
        } else {
            i3 = 256;
        }
        if ((i2 & 32) == 0) {
            mapEmptyMap = map;
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        return openAiCompatibleCloudClient.m338chatCompletionhUnOzRk(str, str2, str3, list, i3, mapEmptyMap);
    }

    /* JADX INFO: renamed from: chatCompletion-hUnOzRk, reason: not valid java name */
    public final Object m338chatCompletionhUnOzRk(String model, String systemPrompt, String userText, List<String> history, int maxTokens, Map<String, ? extends Object> extraBody) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(systemPrompt, "systemPrompt");
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(extraBody, "extraBody");
        String strInvoke = this.apiKeyProvider.invoke();
        String string = strInvoke != null ? StringsKt.trim((CharSequence) strInvoke).toString() : null;
        if (string == null) {
            string = "";
        }
        String key = string;
        if (StringsKt.isBlank(key)) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m392constructorimpl(ResultKt.createFailure(new IllegalStateException("API key missing")));
        }
        boolean z = true;
        String base = StringsKt.trimEnd(StringsKt.trim((CharSequence) this.baseUrlProvider.invoke()).toString(), IOUtils.DIR_SEPARATOR_UNIX);
        String url = base + "/chat/completions";
        String body = buildJsonBody(model, buildMessages$brain_debug(systemPrompt, history, userText), maxTokens, false, extraBody);
        try {
            CloudLlmHttpResponse response = this.transport.postJson(url, MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + key), TuplesKt.to("User-Agent", NaraRouterDefaults.USER_AGENT)), body, this.connectTimeoutMs, this.readTimeoutMs);
            int code = response.getCode();
            if (!(200 <= code && code < 300)) {
                Result.Companion companion2 = Result.INSTANCE;
                return Result.m392constructorimpl(ResultKt.createFailure(new IllegalStateException("HTTP " + response.getCode() + ": " + StringsKt.take(response.getBody(), 200))));
            }
            String text = extractAssistantContent$brain_debug(response.getBody());
            String str = text;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            if (z) {
                Result.Companion companion3 = Result.INSTANCE;
                return Result.m392constructorimpl(ResultKt.createFailure(new IllegalStateException("empty content")));
            }
            Result.Companion companion4 = Result.INSTANCE;
            return Result.m392constructorimpl(StringsKt.trim((CharSequence) text).toString());
        } catch (Throwable t) {
            Result.Companion companion5 = Result.INSTANCE;
            return Result.m392constructorimpl(ResultKt.createFailure(t));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean chatCompletionStream_eH_QyT8$lambda$0() {
        return false;
    }

    /* JADX INFO: renamed from: chatCompletionStream-eH_QyT8, reason: not valid java name */
    public final Object m339chatCompletionStreameH_QyT8(String model, String systemPrompt, String userText, List<String> history, int maxTokens, Map<String, ? extends Object> extraBody, final Function0<Boolean> isCancelled, final Function1<? super String, Unit> onDelta) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(systemPrompt, "systemPrompt");
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(extraBody, "extraBody");
        Intrinsics.checkNotNullParameter(isCancelled, "isCancelled");
        Intrinsics.checkNotNullParameter(onDelta, "onDelta");
        String strInvoke = this.apiKeyProvider.invoke();
        String string = strInvoke != null ? StringsKt.trim((CharSequence) strInvoke).toString() : null;
        if (string == null) {
            string = "";
        }
        String key = string;
        if (StringsKt.isBlank(key)) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m392constructorimpl(ResultKt.createFailure(new IllegalStateException("API key missing")));
        }
        String base = StringsKt.trimEnd(StringsKt.trim((CharSequence) this.baseUrlProvider.invoke()).toString(), IOUtils.DIR_SEPARATOR_UNIX);
        String url = base + "/chat/completions";
        String body = buildJsonBody(model, buildMessages$brain_debug(systemPrompt, history, userText), maxTokens, true, extraBody);
        final StringBuilder acc = new StringBuilder();
        try {
            int code = this.streamTransport.postJsonStream(url, MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + key), TuplesKt.to("Accept", "text/event-stream"), TuplesKt.to("User-Agent", NaraRouterDefaults.USER_AGENT)), body, this.connectTimeoutMs, this.readTimeoutMs, new Function1() { // from class: com.jarvis.brain.aiprovider.OpenAiCompatibleCloudClient$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(OpenAiCompatibleCloudClient.chatCompletionStream_eH_QyT8$lambda$1(isCancelled, this, acc, onDelta, (String) obj));
                }
            });
            if (isCancelled.invoke().booleanValue()) {
                Result.Companion companion2 = Result.INSTANCE;
                return Result.m392constructorimpl(ResultKt.createFailure(new IllegalStateException("CANCELLED")));
            }
            if (!(200 <= code && code < 300)) {
                Result.Companion companion3 = Result.INSTANCE;
                return Result.m392constructorimpl(ResultKt.createFailure(new IllegalStateException("HTTP " + code + " stream")));
            }
            String string2 = acc.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            String text = StringsKt.trim((CharSequence) string2).toString();
            if (StringsKt.isBlank(text)) {
                Result.Companion companion4 = Result.INSTANCE;
                return Result.m392constructorimpl(ResultKt.createFailure(new IllegalStateException("empty stream content")));
            }
            Result.Companion companion5 = Result.INSTANCE;
            return Result.m392constructorimpl(text);
        } catch (Throwable t) {
            Result.Companion companion6 = Result.INSTANCE;
            return Result.m392constructorimpl(ResultKt.createFailure(t));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean chatCompletionStream_eH_QyT8$lambda$1(Function0 $isCancelled, OpenAiCompatibleCloudClient this$0, StringBuilder $acc, Function1 $onDelta, String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        if (((Boolean) $isCancelled.invoke()).booleanValue()) {
            return false;
        }
        String payload = this$0.parseSseDataLine$brain_debug(line);
        if (payload == null) {
            return true;
        }
        if (Intrinsics.areEqual(payload, "[DONE]")) {
            return false;
        }
        String delta = this$0.extractStreamDelta$brain_debug(payload);
        String str = delta;
        if (!(str == null || str.length() == 0)) {
            $acc.append(delta);
            $onDelta.invoke(delta);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean streamChat$lambda$0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit streamChat$lambda$1(Function0 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x01a0  */
    public final StreamChatResult streamChat(String model, String systemPrompt, String userText, List<String> history, int maxTokens, Map<String, ? extends Object> extraBody, final Function0<Boolean> isCancelled, Function1<? super Function0<Unit>, Unit> onAbortable, final Function1<? super String, Unit> onDelta) {
        int i;
        StreamChatResult streamChatResult;
        String message;
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(systemPrompt, "systemPrompt");
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(extraBody, "extraBody");
        Intrinsics.checkNotNullParameter(isCancelled, "isCancelled");
        Intrinsics.checkNotNullParameter(onAbortable, "onAbortable");
        Intrinsics.checkNotNullParameter(onDelta, "onDelta");
        String strInvoke = this.apiKeyProvider.invoke();
        String string = strInvoke != null ? StringsKt.trim((CharSequence) strInvoke).toString() : null;
        if (string == null) {
            string = "";
        }
        String key = string;
        if (StringsKt.isBlank(key)) {
            return new StreamChatResult(Integer.valueOf(TypedValues.CycleType.TYPE_CURVE_FIT), "api key missing", "", null);
        }
        String base = StringsKt.trimEnd(StringsKt.trim((CharSequence) this.baseUrlProvider.invoke()).toString(), IOUtils.DIR_SEPARATOR_UNIX);
        String url = base + "/chat/completions";
        String body = buildJsonBody(model, buildMessages$brain_debug(systemPrompt, history, userText), maxTokens, true, extraBody);
        final StringBuilder acc = new StringBuilder();
        final StringBuilder errBody = new StringBuilder();
        final Ref.ObjectRef usage = new Ref.ObjectRef();
        try {
            i = 2048;
            try {
                int code = this.streamTransport.postJsonStreamAbortable(url, MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + key), TuplesKt.to("Accept", "text/event-stream"), TuplesKt.to("User-Agent", NaraRouterDefaults.USER_AGENT)), body, this.connectTimeoutMs, this.readTimeoutMs, onAbortable, new Function1() { // from class: com.jarvis.brain.aiprovider.OpenAiCompatibleCloudClient$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(OpenAiCompatibleCloudClient.streamChat$lambda$2(isCancelled, this, errBody, usage, acc, onDelta, (String) obj));
                    }
                });
                try {
                    if (isCancelled.invoke().booleanValue()) {
                        String string2 = acc.toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                        return new StreamChatResult(0, "", string2, null);
                    }
                    if (!(200 <= code && code < 300)) {
                        Integer numValueOf = Integer.valueOf(code);
                        String string3 = errBody.toString();
                        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                        return new StreamChatResult(numValueOf, StringsKt.take(string3, 2048), "", null);
                    }
                    Integer numValueOf2 = Integer.valueOf(code);
                    String string4 = acc.toString();
                    Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                    try {
                        streamChatResult = new StreamChatResult(numValueOf2, "", string4, (Map) usage.element);
                    } catch (Throwable th) {
                        t = th;
                        message = t.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        streamChatResult = new StreamChatResult(null, StringsKt.take(message, i), "", null);
                    }
                    return streamChatResult;
                } catch (Throwable th2) {
                    t = th2;
                }
            } catch (Throwable th3) {
                t = th3;
            }
        } catch (Throwable th4) {
            t = th4;
            i = 2048;
        }
        message = t.getMessage();
        if (message == null) {
            message = "";
        }
        streamChatResult = new StreamChatResult(null, StringsKt.take(message, i), "", null);
        return streamChatResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.util.Map] */
    public static final boolean streamChat$lambda$2(Function0 $isCancelled, OpenAiCompatibleCloudClient this$0, StringBuilder $errBody, Ref.ObjectRef $usage, StringBuilder $acc, Function1 $onDelta, String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        if (((Boolean) $isCancelled.invoke()).booleanValue()) {
            return false;
        }
        String payload = this$0.parseSseDataLine$brain_debug(line);
        if (payload == null) {
            if ($errBody.length() < 2048) {
                $errBody.append(line).append('\n');
            }
            return true;
        }
        if (Intrinsics.areEqual(payload, "[DONE]")) {
            return false;
        }
        ?? ExtractStreamUsage$brain_debug = this$0.extractStreamUsage$brain_debug(payload);
        if (ExtractStreamUsage$brain_debug != 0) {
            $usage.element = ExtractStreamUsage$brain_debug;
        }
        String delta = this$0.extractStreamDelta$brain_debug(payload);
        String str = delta;
        if (!(str == null || str.length() == 0)) {
            $acc.append(delta);
            $onDelta.invoke(delta);
        }
        return true;
    }

    public final Map<String, Object> extractStreamUsage$brain_debug(String json) {
        MatchResult promptM;
        Intrinsics.checkNotNullParameter(json, "json");
        if (StringsKt.isBlank(json) || Intrinsics.areEqual(json, "[DONE]") || (promptM = Regex.find$default(new Regex("\"prompt_tokens\"\\s*:\\s*(\\d+)"), json, 0, 2, null)) == null) {
            return null;
        }
        MatchResult completionM = Regex.find$default(new Regex("\"completion_tokens\"\\s*:\\s*(\\d+)"), json, 0, 2, null);
        Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("prompt_tokens", Integer.valueOf(Integer.parseInt(promptM.getGroupValues().get(1)))));
        if (completionM != null) {
            mapMutableMapOf.put("completion_tokens", Integer.valueOf(Integer.parseInt(completionM.getGroupValues().get(1))));
        }
        MatchResult reasoningM = Regex.find$default(new Regex("\"reasoning_tokens\"\\s*:\\s*(\\d+)"), json, 0, 2, null);
        if (reasoningM != null) {
            mapMutableMapOf.put("reasoning_tokens", Integer.valueOf(Integer.parseInt(reasoningM.getGroupValues().get(1))));
        }
        return mapMutableMapOf;
    }

    public final String parseSseDataLine$brain_debug(String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        String t = StringsKt.trim((CharSequence) line).toString();
        if ((t.length() == 0) || StringsKt.startsWith$default(t, ":", false, 2, (Object) null) || !StringsKt.startsWith$default(t, "data:", false, 2, (Object) null)) {
            return null;
        }
        return StringsKt.trim((CharSequence) StringsKt.removePrefix(t, (CharSequence) "data:")).toString();
    }

    public final String extractStreamDelta$brain_debug(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        if (StringsKt.isBlank(json) || Intrinsics.areEqual(json, "[DONE]")) {
            return null;
        }
        try {
            JSONObject root = new JSONObject(json);
            JSONArray choices = root.optJSONArray("choices");
            if (choices == null || choices.length() == 0) {
                return null;
            }
            JSONObject choice = choices.getJSONObject(0);
            JSONObject delta = choice.optJSONObject("delta");
            if (delta == null) {
                return null;
            }
            String it = delta.optString("content");
            Intrinsics.checkNotNull(it);
            if (it.length() > 0) {
                return it;
            }
            return null;
        } catch (Throwable th) {
            MatchResult m = Regex.find$default(new Regex("\"content\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\""), json, 0, 2, null);
            if (m == null) {
                return null;
            }
            String it2 = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(m.getGroupValues().get(1), "\\n", StringUtils.LF, false, 4, (Object) null), "\\\"", "\"", false, 4, (Object) null), "\\\\", "\\", false, 4, (Object) null);
            if (it2.length() > 0) {
                return it2;
            }
            return null;
        }
    }

    public final List<Pair<String, String>> buildMessages$brain_debug(String systemPrompt, List<String> history, String userText) {
        Intrinsics.checkNotNullParameter(systemPrompt, "systemPrompt");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(userText, "userText");
        ArrayList out = new ArrayList(8);
        if (!StringsKt.isBlank(systemPrompt)) {
            out.add(TuplesKt.to("system", StringsKt.take(SensitivityClassifier.INSTANCE.mask(systemPrompt), AICompletionRequest.MAX_USER)));
        }
        Iterable $this$forEach$iv = CollectionsKt.takeLast(history, 8);
        for (Object element$iv : $this$forEach$iv) {
            String line = (String) element$iv;
            String lower = line.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
            if (StringsKt.startsWith$default(lower, "user:", false, 2, (Object) null)) {
                out.add(TuplesKt.to(JarvisSchema.ROLE_USER, StringsKt.take(SensitivityClassifier.INSTANCE.mask(StringsKt.trim((CharSequence) StringsKt.substringAfter$default(line, ':', (String) null, 2, (Object) null)).toString()), 800)));
            } else if (StringsKt.startsWith$default(lower, "assistant:", false, 2, (Object) null)) {
                out.add(TuplesKt.to(JarvisSchema.ROLE_ASSISTANT, StringsKt.take(SensitivityClassifier.INSTANCE.mask(StringsKt.trim((CharSequence) StringsKt.substringAfter$default(line, ':', (String) null, 2, (Object) null)).toString()), 800)));
            }
        }
        out.add(TuplesKt.to(JarvisSchema.ROLE_USER, StringsKt.take(SensitivityClassifier.INSTANCE.mask(userText), AICompletionRequest.MAX_USER)));
        return out;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ String buildJsonBody$default(OpenAiCompatibleCloudClient openAiCompatibleCloudClient, String str, List list, int i, boolean z, Map map, int i2, Object obj) {
        Map mapEmptyMap;
        if ((i2 & 16) == 0) {
            mapEmptyMap = map;
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        return openAiCompatibleCloudClient.buildJsonBody(str, list, i, z, mapEmptyMap);
    }

    private final String buildJsonBody(String model, List<Pair<String, String>> messages, int maxTokens, boolean stream, Map<String, ? extends Object> extraBody) {
        StringBuilder sb = new StringBuilder(512);
        char c = '{';
        sb.append('{');
        sb.append("\"model\":").append(jsonString(model)).append(',');
        sb.append("\"max_tokens\":").append(RangesKt.coerceIn(maxTokens, 16, 1024)).append(',');
        sb.append("\"temperature\":0.7,");
        if (stream) {
            sb.append("\"stream\":true,");
        }
        sb.append("\"messages\":[");
        List<Pair<String, String>> $this$forEachIndexed$iv = messages;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Pair pair = (Pair) item$iv;
            String role = (String) pair.component1();
            String content = (String) pair.component2();
            if (i > 0) {
                sb.append(',');
            }
            sb.append(c);
            sb.append("\"role\":").append(jsonString(role)).append(',');
            sb.append("\"content\":").append(jsonString(content));
            sb.append('}');
            i = index$iv;
            c = '{';
        }
        sb.append(']');
        LinkedHashMap result$iv = new LinkedHashMap();
        for (Map.Entry<String, ? extends Object> entry : extraBody.entrySet()) {
            if (!this.PROTECTED_BODY_KEYS.contains(entry.getKey())) {
                result$iv.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap $this$filterValues$iv = result$iv;
        LinkedHashMap result$iv2 = new LinkedHashMap();
        for (Map.Entry entry$iv : $this$filterValues$iv.entrySet()) {
            Object it = entry$iv.getValue();
            if ((it instanceof String) || (it instanceof Boolean) || (it instanceof Number)) {
                result$iv2.put(entry$iv.getKey(), entry$iv.getValue());
            }
        }
        LinkedHashMap $this$forEach$iv = result$iv2;
        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            String k = (String) element$iv.getKey();
            Object v = element$iv.getValue();
            sb.append(',');
            sb.append(jsonString(k)).append(':');
            if (v instanceof String) {
                sb.append(jsonString((String) v));
            } else if (v instanceof Boolean) {
                sb.append(((Boolean) v).booleanValue());
            } else if (v instanceof Number) {
                sb.append(v);
            } else {
                sb.append("null");
            }
        }
        sb.append('}');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final Set<String> getPROTECTED_BODY_KEYS$brain_debug() {
        return this.PROTECTED_BODY_KEYS;
    }

    private final String jsonString(String s) {
        String escaped = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s, "\\", "\\\\", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null), StringUtils.LF, "\\n", false, 4, (Object) null), StringUtils.CR, "\\r", false, 4, (Object) null), "\t", "\\t", false, 4, (Object) null);
        return "\"" + escaped + "\"";
    }

    public final String extractAssistantContent$brain_debug(String json) {
        JSONObject msg;
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            JSONObject root = new JSONObject(json);
            JSONArray choices = root.optJSONArray("choices");
            if (choices == null || choices.length() == 0 || (msg = choices.getJSONObject(0).optJSONObject("message")) == null) {
                return null;
            }
            String it = msg.optString("content");
            Intrinsics.checkNotNull(it);
            if (StringsKt.isBlank(it)) {
                return null;
            }
            return it;
        } catch (Throwable th) {
            MatchResult m = Regex.find$default(new Regex("\"content\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\""), json, 0, 2, null);
            if (m == null) {
                return null;
            }
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(m.getGroupValues().get(1), "\\n", StringUtils.LF, false, 4, (Object) null), "\\\"", "\"", false, 4, (Object) null), "\\\\", "\\", false, 4, (Object) null);
        }
    }
}
