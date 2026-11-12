package com.jarvis.brain.cloud;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.app.VoiceService;
import com.jarvis.brain.aiprovider.CloudLlmHttpTransport;
import com.jarvis.brain.aiprovider.OpenAiCompatibleCloudClient;
import com.jarvis.brain.aiprovider.StreamChatResult;
import com.jarvis.brain.aiprovider.UrlConnectionCloudLlmTransport;
import com.jarvis.core.cloud.CallListener;
import com.jarvis.core.cloud.CancelHandle;
import com.jarvis.core.cloud.ChainConfig;
import com.jarvis.core.cloud.ChainEntry;
import com.jarvis.core.cloud.CloudCall;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CloudChainCall.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&Bz\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012#\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J;\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u00032\b\u0010\"\u001a\u0004\u0018\u00010\b2\b\u0010#\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0002\u0010$J\u001e\u0010%\u001a\u0004\u0018\u00010\u00032\b\u0010#\u001a\u0004\u0018\u00010\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/jarvis/brain/cloud/CloudChainCall;", "Lcom/jarvis/core/cloud/CloudCall;", "systemPrompt", "", "history", "", "userText", "maxTokens", "", "keyFor", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "providerId", NotificationCompat.CATEGORY_TRANSPORT, "Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;", "readTimeoutMs", "connectTimeoutMs", "executor", "Ljava/util/concurrent/ExecutorService;", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;IILjava/util/concurrent/ExecutorService;)V", "start", "Lcom/jarvis/core/cloud/CancelHandle;", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/core/cloud/ChainEntry;", "paramCandidateIndex", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/cloud/CallListener;", "safeError", "", "cancelled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "key", "code", "body", "(Lcom/jarvis/core/cloud/CallListener;Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "redactKey", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudChainCall implements CloudCall {
    private static final ExecutorService DEFAULT_EXECUTOR;
    private static final String TAG = "Jarvis.CloudChainCall";
    private final int connectTimeoutMs;
    private final ExecutorService executor;
    private final List<String> history;
    private final Function1<String, String> keyFor;
    private final int maxTokens;
    private final int readTimeoutMs;
    private final String systemPrompt;
    private final CloudLlmHttpTransport transport;
    private final String userText;

    /* JADX WARN: Multi-variable type inference failed */
    public CloudChainCall(String systemPrompt, List<String> history, String userText, int maxTokens, Function1<? super String, String> keyFor, CloudLlmHttpTransport transport, int readTimeoutMs, int connectTimeoutMs, ExecutorService executor) {
        Intrinsics.checkNotNullParameter(systemPrompt, "systemPrompt");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(keyFor, "keyFor");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.systemPrompt = systemPrompt;
        this.history = history;
        this.userText = userText;
        this.maxTokens = maxTokens;
        this.keyFor = keyFor;
        this.transport = transport;
        this.readTimeoutMs = readTimeoutMs;
        this.connectTimeoutMs = connectTimeoutMs;
        this.executor = executor;
    }

    public /* synthetic */ CloudChainCall(String str, List list, String str2, int i, Function1 function1, CloudLlmHttpTransport cloudLlmHttpTransport, int i2, int i3, ExecutorService executorService, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, str2, i, function1, (i4 & 32) != 0 ? new UrlConnectionCloudLlmTransport() : cloudLlmHttpTransport, (i4 & 64) != 0 ? (int) ChainConfig.INSTANCE.getDefault().getTotalDeadlineMs() : i2, (i4 & 128) != 0 ? (int) ChainConfig.INSTANCE.getDefault().getConnectTimeoutMs() : i3, (i4 & 256) != 0 ? DEFAULT_EXECUTOR : executorService);
    }

    @Override // com.jarvis.core.cloud.CloudCall
    public CancelHandle start(final ChainEntry entry, int paramCandidateIndex, final CallListener listener) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(listener, "listener");
        final AtomicBoolean cancelled = new AtomicBoolean(false);
        final AtomicReference abortConnection = new AtomicReference(null);
        Map mapEmptyMap = (Map) CollectionsKt.getOrNull(entry.getParamCandidates(), paramCandidateIndex);
        if (mapEmptyMap == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        final Map params = mapEmptyMap;
        this.executor.execute(new Runnable() { // from class: com.jarvis.brain.cloud.CloudChainCall$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CloudChainCall.start$lambda$0(cancelled, this, entry, listener, params, abortConnection);
            }
        });
        return new CancelHandle() { // from class: com.jarvis.brain.cloud.CloudChainCall.start.2
            @Override // com.jarvis.core.cloud.CancelHandle
            public void cancel() {
                cancelled.set(true);
                Function0<Unit> function0 = abortConnection.get();
                if (function0 != null) {
                    function0.invoke();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(final AtomicBoolean $cancelled, CloudChainCall this$0, final ChainEntry $entry, final CallListener $listener, Map $params, final AtomicReference $abortConnection) {
        if ($cancelled.get()) {
            return;
        }
        final String key = this$0.keyFor.invoke($entry.getProviderId());
        String str = key;
        if (str == null || StringsKt.isBlank(str)) {
            this$0.safeError($listener, $cancelled, null, Integer.valueOf(TypedValues.CycleType.TYPE_CURVE_FIT), "api key missing");
            return;
        }
        final Ref.BooleanRef firstTokenSent = new Ref.BooleanRef();
        OpenAiCompatibleCloudClient client = new OpenAiCompatibleCloudClient(new Function0() { // from class: com.jarvis.brain.cloud.CloudChainCall$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return $entry.getBaseUrl();
            }
        }, new Function0() { // from class: com.jarvis.brain.cloud.CloudChainCall$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CloudChainCall.start$lambda$0$1(key);
            }
        }, this$0.transport, null, this$0.connectTimeoutMs, this$0.readTimeoutMs, 8, null);
        StreamChatResult streamChatResultStreamChat = client.streamChat($entry.getModelId(), this$0.systemPrompt, this$0.userText, this$0.history, this$0.maxTokens, $params, new Function0() { // from class: com.jarvis.brain.cloud.CloudChainCall$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf($cancelled.get());
            }
        }, new Function1() { // from class: com.jarvis.brain.cloud.CloudChainCall$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CloudChainCall.start$lambda$0$3($abortConnection, $cancelled, (Function0) obj);
            }
        }, new Function1() { // from class: com.jarvis.brain.cloud.CloudChainCall$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CloudChainCall.start$lambda$0$4($cancelled, firstTokenSent, $listener, (String) obj);
            }
        });
        if ($cancelled.get()) {
            return;
        }
        if (streamChatResultStreamChat.getCode() != null) {
            int iIntValue = streamChatResultStreamChat.getCode().intValue();
            if (200 <= iIntValue && iIntValue < 300) {
                if ($cancelled.get()) {
                    return;
                }
                $listener.onComplete(streamChatResultStreamChat.getUsage());
                return;
            }
            this$0.safeError($listener, $cancelled, key, streamChatResultStreamChat.getCode(), streamChatResultStreamChat.getErrorBody());
            return;
        }
        this$0.safeError($listener, $cancelled, key, null, streamChatResultStreamChat.getErrorBody());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String start$lambda$0$1(String $key) {
        return $key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit start$lambda$0$3(AtomicReference $abortConnection, AtomicBoolean $cancelled, Function0 abort) {
        Intrinsics.checkNotNullParameter(abort, "abort");
        $abortConnection.set(abort);
        if ($cancelled.get()) {
            abort.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit start$lambda$0$4(AtomicBoolean $cancelled, Ref.BooleanRef $firstTokenSent, CallListener $listener, String delta) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        if ($cancelled.get()) {
            return Unit.INSTANCE;
        }
        if (!$firstTokenSent.element) {
            $firstTokenSent.element = true;
            $listener.onFirstToken(delta);
        } else {
            $listener.onToken(delta);
        }
        return Unit.INSTANCE;
    }

    private final void safeError(CallListener listener, AtomicBoolean cancelled, String key, Integer code, String body) {
        if (cancelled.get()) {
            return;
        }
        String redacted = redactKey(body, key);
        listener.onError(code, redacted);
        SafeLog.INSTANCE.w(TAG, "chainCall error code=" + code + " body=" + (redacted != null ? StringsKt.take(redacted, 120) : null));
    }

    private final String redactKey(String body, String key) {
        if (body == null) {
            return null;
        }
        String str = key;
        return !(str == null || StringsKt.isBlank(str)) ? StringsKt.replace$default(body, key, "[REDACTED]", false, 4, (Object) null) : body;
    }

    static {
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.jarvis.brain.cloud.CloudChainCall$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return CloudChainCall.DEFAULT_EXECUTOR$lambda$0(runnable);
            }
        });
        Intrinsics.checkNotNullExpressionValue(executorServiceNewCachedThreadPool, "newCachedThreadPool(...)");
        DEFAULT_EXECUTOR = executorServiceNewCachedThreadPool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread DEFAULT_EXECUTOR$lambda$0(Runnable r) {
        Thread $this$DEFAULT_EXECUTOR_u24lambda_u240_u240 = new Thread(r, "cloud-chain");
        $this$DEFAULT_EXECUTOR_u24lambda_u240_u240.setDaemon(true);
        return $this$DEFAULT_EXECUTOR_u24lambda_u240_u240;
    }
}
