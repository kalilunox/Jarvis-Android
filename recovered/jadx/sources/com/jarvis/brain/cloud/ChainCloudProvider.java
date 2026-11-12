package com.jarvis.brain.cloud;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.internal.ViewUtils;
import com.jarvis.brain.aiprovider.AICompletionRequest;
import com.jarvis.brain.aiprovider.AICompletionResult;
import com.jarvis.brain.aiprovider.AICompletionStatus;
import com.jarvis.brain.aiprovider.AIProviderAvailability;
import com.jarvis.brain.aiprovider.AIProviderCapabilities;
import com.jarvis.brain.aiprovider.AIProviderError;
import com.jarvis.brain.aiprovider.AIProviderErrorCategory;
import com.jarvis.brain.aiprovider.AIProviderKind;
import com.jarvis.brain.aiprovider.AIStreamEvent;
import com.jarvis.brain.aiprovider.AIStreamEventType;
import com.jarvis.brain.aiprovider.AIStreamHandle;
import com.jarvis.brain.aiprovider.AIStreamListener;
import com.jarvis.brain.aiprovider.AiTaskType;
import com.jarvis.brain.aiprovider.CloudLLMProvider;
import com.jarvis.brain.aiprovider.CloudLlmHttpTransport;
import com.jarvis.brain.aiprovider.StreamingAIProvider;
import com.jarvis.brain.aiprovider.UrlConnectionCloudLlmTransport;
import com.jarvis.core.aioutput.CloudToolAction;
import com.jarvis.core.cloud.AllFailedException;
import com.jarvis.core.cloud.ChainConfig;
import com.jarvis.core.cloud.ChainEntry;
import com.jarvis.core.cloud.ChainHealth;
import com.jarvis.core.cloud.ChainResult;
import com.jarvis.core.cloud.ChainRouter;
import com.jarvis.core.cloud.CloudCall;
import com.jarvis.core.cloud.DefaultCloudChain;
import com.jarvis.core.cloud.ErrorClass;
import com.jarvis.core.cloud.TokenSink;
import com.jarvis.core.runtime.TtftKind;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;

/* JADX INFO: compiled from: ChainCloudProvider.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 S2\u00020\u0001:\u0001SB \u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012#\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00100\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0014\u0012\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\t¢\u0006\u0004\b\u001b\u0010\u001cJ\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\u0006\u0010=\u001a\u00020>J\u000e\u0010?\u001a\u00020>2\u0006\u0010\r\u001a\u00020\nJ\b\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020>H\u0016J\u0010\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u0019H\u0016J\u0016\u0010F\u001a\u00020G2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020403H\u0002J \u0010I\u001a\u00020J2\u0006\u0010E\u001a\u00020\u00192\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u0016J \u0010O\u001a\u00020>2\u0006\u0010E\u001a\u00020\u00192\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u0002J\u0010\u0010Q\u001a\u00020\n2\u0006\u0010E\u001a\u00020\u0019H\u0002J\u0010\u0010R\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR+\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0014\u0010$\u001a\u00020%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\"\u0010-\u001a\u0004\u0018\u00010\n2\b\u0010,\u001a\u0004\u0018\u00010\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010!R\u001e\u00100\u001a\u00020/2\u0006\u0010,\u001a\u00020/@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R*\u00105\u001a\b\u0012\u0004\u0012\u000204032\f\u0010,\u001a\b\u0012\u0004\u0012\u00020403@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u000e\u00108\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00109\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/jarvis/brain/cloud/ChainCloudProvider;", "Lcom/jarvis/brain/aiprovider/StreamingAIProvider;", "legacy", "Lcom/jarvis/brain/aiprovider/CloudLLMProvider;", "router", "Lcom/jarvis/core/cloud/ChainRouter;", "health", "Lcom/jarvis/core/cloud/ChainHealth;", "keyFor", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "providerId", "providersWithKeys", "Lkotlin/Function0;", "", NotificationCompat.CATEGORY_TRANSPORT, "Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;", "clock", "", "config", "Lcom/jarvis/core/cloud/ChainConfig;", "allFailedCooldownMs", "callFactory", "Lcom/jarvis/brain/aiprovider/AICompletionRequest;", "Lcom/jarvis/core/cloud/CloudCall;", "<init>", "(Lcom/jarvis/brain/aiprovider/CloudLLMProvider;Lcom/jarvis/core/cloud/ChainRouter;Lcom/jarvis/core/cloud/ChainHealth;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lcom/jarvis/brain/aiprovider/CloudLlmHttpTransport;Lkotlin/jvm/functions/Function0;Lcom/jarvis/core/cloud/ChainConfig;JLkotlin/jvm/functions/Function1;)V", "getHealth$brain_debug", "()Lcom/jarvis/core/cloud/ChainHealth;", "id", "getId", "()Ljava/lang/String;", "displayName", "getDisplayName", "kind", "Lcom/jarvis/brain/aiprovider/AIProviderKind;", "getKind", "()Lcom/jarvis/brain/aiprovider/AIProviderKind;", "capabilities", "Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "getCapabilities", "()Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "value", "lastWinner", "getLastWinner", "", "lastHedged", "getLastHedged", "()Z", "", "Lcom/jarvis/core/cloud/ErrorClass;", "lastFailures", "getLastFailures", "()Ljava/util/List;", "allFailedUntil", "cachedKeys", "cachedKeysAt", "keysCacheTtlMs", "chainKeys", "invalidate", "", "onKeyChanged", "availability", "Lcom/jarvis/brain/aiprovider/AIProviderAvailability;", "warm", "complete", "Lcom/jarvis/brain/aiprovider/AICompletionResult;", "request", "dominantFailureCategory", "Lcom/jarvis/brain/aiprovider/AIProviderErrorCategory;", "failures", "stream", "Lcom/jarvis/brain/aiprovider/AIStreamHandle;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/brain/aiprovider/AIStreamListener;", "cancelFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "runStream", "realTransport", "systemPrompt", "buildCloudCall", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChainCloudProvider implements StreamingAIProvider {

    @Deprecated
    public static final long CANCEL_POLL_MS = 50;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String TAG = "Jarvis.CloudChain";
    private final long allFailedCooldownMs;
    private volatile long allFailedUntil;
    private Set<String> cachedKeys;
    private long cachedKeysAt;
    private final Function1<AICompletionRequest, CloudCall> callFactory;
    private final AIProviderCapabilities capabilities;
    private final Function0<Long> clock;
    private final ChainConfig config;
    private final String displayName;
    private final ChainHealth health;
    private final String id;
    private final Function1<String, String> keyFor;
    private final long keysCacheTtlMs;
    private final AIProviderKind kind;
    private volatile List<? extends ErrorClass> lastFailures;
    private volatile boolean lastHedged;
    private volatile String lastWinner;
    private final CloudLLMProvider legacy;
    private final Function0<Set<String>> providersWithKeys;
    private final CloudLlmHttpTransport realTransport;
    private final ChainRouter router;

    /* JADX WARN: Multi-variable type inference failed */
    public ChainCloudProvider(CloudLLMProvider legacy, ChainRouter router, ChainHealth health, Function1<? super String, String> keyFor, Function0<? extends Set<String>> providersWithKeys, CloudLlmHttpTransport transport, Function0<Long> clock, ChainConfig config, long allFailedCooldownMs, Function1<? super AICompletionRequest, ? extends CloudCall> function1) {
        Intrinsics.checkNotNullParameter(legacy, "legacy");
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(health, "health");
        Intrinsics.checkNotNullParameter(keyFor, "keyFor");
        Intrinsics.checkNotNullParameter(providersWithKeys, "providersWithKeys");
        Intrinsics.checkNotNullParameter(clock, "clock");
        Intrinsics.checkNotNullParameter(config, "config");
        this.legacy = legacy;
        this.router = router;
        this.health = health;
        this.keyFor = keyFor;
        this.providersWithKeys = providersWithKeys;
        this.clock = clock;
        this.config = config;
        this.allFailedCooldownMs = allFailedCooldownMs;
        this.callFactory = function1;
        this.id = CloudLLMProvider.ID;
        this.displayName = "Cloud LLM (chain)";
        this.kind = AIProviderKind.CLOUD;
        this.capabilities = this.legacy.getCapabilities();
        this.lastFailures = CollectionsKt.emptyList();
        this.keysCacheTtlMs = 2000L;
        this.realTransport = transport == null ? new UrlConnectionCloudLlmTransport() : transport;
    }

    public /* synthetic */ ChainCloudProvider(CloudLLMProvider cloudLLMProvider, ChainRouter chainRouter, ChainHealth chainHealth, Function1 function1, Function0 function0, CloudLlmHttpTransport cloudLlmHttpTransport, Function0 function2, ChainConfig chainConfig, long j, Function1 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(cloudLLMProvider, chainRouter, chainHealth, function1, function0, (i & 32) != 0 ? null : cloudLlmHttpTransport, (i & 64) != 0 ? new Function0() { // from class: com.jarvis.brain.cloud.ChainCloudProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function2, (i & 128) != 0 ? ChainConfig.INSTANCE.getDefault() : chainConfig, (i & 256) != 0 ? 10000L : j, (i & 512) != 0 ? null : function3);
    }

    /* JADX INFO: renamed from: getHealth$brain_debug, reason: from getter */
    public final ChainHealth getHealth() {
        return this.health;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public String getDisplayName() {
        return this.displayName;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderCapabilities getCapabilities() {
        return this.capabilities;
    }

    public final String getLastWinner() {
        return this.lastWinner;
    }

    public final boolean getLastHedged() {
        return this.lastHedged;
    }

    public final List<ErrorClass> getLastFailures() {
        return this.lastFailures;
    }

    private final Set<String> chainKeys() {
        long now = this.clock.invoke().longValue();
        Set<String> set = this.cachedKeys;
        if (set != null && now - this.cachedKeysAt < this.keysCacheTtlMs) {
            return set;
        }
        Iterable $this$filter$iv = this.providersWithKeys.invoke();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (Intrinsics.areEqual(it, "groq") || Intrinsics.areEqual(it, "gemini")) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Set<String> set2 = CollectionsKt.toSet((List) destination$iv$iv);
        this.cachedKeys = set2;
        this.cachedKeysAt = now;
        return set2;
    }

    public final void invalidate() {
        this.cachedKeys = null;
        this.cachedKeysAt = 0L;
    }

    public final void onKeyChanged(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        this.health.reset(providerId);
        invalidate();
        SafeLog.INSTANCE.i(TAG, "keyChanged provider=" + providerId);
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AIProviderAvailability availability() {
        long now = this.clock.invoke().longValue();
        if (now < this.allFailedUntil) {
            return AIProviderAvailability.UNAVAILABLE;
        }
        if (!chainKeys().isEmpty()) {
            return AIProviderAvailability.AVAILABLE;
        }
        if (this.legacy.availability() == AIProviderAvailability.AVAILABLE) {
            return AIProviderAvailability.AVAILABLE;
        }
        return AIProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.brain.aiprovider.AIProvider
    public void warm() {
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [com.jarvis.brain.cloud.ChainCloudProvider$complete$sink$1] */
    @Override // com.jarvis.brain.aiprovider.AIProvider
    public AICompletionResult complete(AICompletionRequest request) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(request, "request");
        final long started = this.clock.invoke().longValue();
        if (request.getCancelRequested()) {
            return new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.CANCELLED, null, 0L, new AIProviderError(AIProviderErrorCategory.CANCELLED, "CANCELLED", "Cancellation requested"), false, null, null, null, null, null, 4056, null);
        }
        Set<String> setChainKeys = chainKeys();
        if (setChainKeys.isEmpty()) {
            return this.legacy.complete(request);
        }
        List<ChainEntry> listAvailableEntries = DefaultCloudChain.INSTANCE.availableEntries(DefaultCloudChain.INSTANCE.entries(), setChainKeys);
        final StringBuilder sinkText = new StringBuilder();
        final Ref.ObjectRef ttft = new Ref.ObjectRef();
        ?? r11 = new TokenSink() { // from class: com.jarvis.brain.cloud.ChainCloudProvider$complete$sink$1
            /* JADX WARN: Type inference failed for: r1v6, types: [T, java.lang.Long] */
            @Override // com.jarvis.core.cloud.TokenSink
            public void onFirstToken(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                if (ttft.element == null) {
                    ttft.element = Long.valueOf(((Number) this.clock.invoke()).longValue() - started);
                }
                sinkText.append(text);
            }

            @Override // com.jarvis.core.cloud.TokenSink
            public void onToken(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                sinkText.append(text);
            }

            @Override // com.jarvis.core.cloud.TokenSink
            public void onComplete(Map<String, ? extends Object> usage) {
            }
        };
        try {
            str = TAG;
            str2 = "complete requestId=";
            try {
                ChainResult result = (ChainResult) BuildersKt__BuildersKt.runBlocking$default(null, new ChainCloudProvider$complete$result$1(this, listAvailableEntries, request, r11, null), 1, null);
                this.lastWinner = result.getEntry().getProviderId() + "/" + result.getEntry().getModelId();
                this.lastHedged = result.getHedged();
                this.lastFailures = result.getFailures();
                try {
                    try {
                        try {
                            try {
                                SafeLog.INSTANCE.i(str, str2 + request.getRequestId() + " winner=" + this.lastWinner + " hedged=" + result.getHedged() + " ttftMs=" + result.getTimeToFirstTokenMs() + " totalMs=" + (this.clock.invoke().longValue() - started) + " attempts=[" + CollectionsKt.joinToString$default(result.getAttemptReport(), null, null, null, 0, null, null, 63, null) + "]");
                                String requestId = request.getRequestId();
                                String id = getId();
                                AICompletionStatus aICompletionStatus = AICompletionStatus.SUCCESS;
                                String string = sinkText.toString();
                                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                return new AICompletionResult(requestId, id, aICompletionStatus, StringsKt.trim((CharSequence) string).toString(), this.clock.invoke().longValue() - started, null, true, result.getEntry().getModelId(), Long.valueOf(result.getTimeToFirstTokenMs()), TtftKind.REAL, null, null, 3104, null);
                            } catch (AllFailedException e) {
                                e = e;
                                if (!e.getPartial()) {
                                    this.allFailedUntil = this.clock.invoke().longValue() + this.allFailedCooldownMs;
                                }
                                this.lastFailures = e.getFailures();
                                SafeLog.INSTANCE.i(str, str2 + request.getRequestId() + " allFailed winner=null hedged=" + e.getHedged() + " ttftMs=null totalMs=" + (this.clock.invoke().longValue() - started) + " failures=" + e.getFailures() + " attempts=[" + CollectionsKt.joinToString$default(e.getAttemptReport(), null, null, null, 0, null, null, 63, null) + "] cooldownMs=" + this.allFailedCooldownMs);
                                return new AICompletionResult(request.getRequestId(), getId(), AICompletionStatus.FAILURE, null, 0L, new AIProviderError(dominantFailureCategory(e.getFailures()), "CHAIN_ALL_FAILED", CollectionsKt.joinToString$default(e.getFailures(), ",", null, null, 0, null, null, 62, null)), false, null, null, null, null, null, 4056, null);
                            }
                        } catch (AllFailedException e2) {
                            e = e2;
                        }
                    } catch (AllFailedException e3) {
                        e = e3;
                    }
                } catch (AllFailedException e4) {
                    e = e4;
                }
            } catch (AllFailedException e5) {
                e = e5;
            }
        } catch (AllFailedException e6) {
            e = e6;
            str = TAG;
            str2 = "complete requestId=";
        }
    }

    private final AIProviderErrorCategory dominantFailureCategory(List<? extends ErrorClass> failures) {
        List<? extends ErrorClass> $this$filter$iv = failures;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            ErrorClass it2 = (ErrorClass) element$iv$iv;
            if (it2 == ErrorClass.RATE_LIMITED || it2 == ErrorClass.RATE_LIMITED_DAILY) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        boolean z = ((List) destination$iv$iv).size() * 2 >= failures.size();
        if (z) {
            return AIProviderErrorCategory.TIMEOUT;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        return AIProviderErrorCategory.NETWORK;
    }

    @Override // com.jarvis.brain.aiprovider.StreamingAIProvider
    public AIStreamHandle stream(AICompletionRequest request, AIStreamListener listener, AtomicBoolean cancelFlag) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(cancelFlag, "cancelFlag");
        if (chainKeys().isEmpty()) {
            return this.legacy.stream(request, listener, cancelFlag);
        }
        if (request.getCancelRequested()) {
            cancelFlag.set(true);
        }
        runStream(request, listener, cancelFlag);
        return new AIStreamHandle(request, cancelFlag) { // from class: com.jarvis.brain.cloud.ChainCloudProvider.stream.1
            final /* synthetic */ AtomicBoolean $cancelFlag;
            private final String requestId;

            {
                this.$cancelFlag = cancelFlag;
                this.requestId = request.getRequestId();
            }

            @Override // com.jarvis.brain.aiprovider.AIStreamHandle
            public String getRequestId() {
                return this.requestId;
            }

            @Override // com.jarvis.brain.aiprovider.AIStreamHandle
            public void cancel() {
                this.$cancelFlag.set(true);
            }

            @Override // com.jarvis.brain.aiprovider.AIStreamHandle
            public boolean isCancelled() {
                return this.$cancelFlag.get();
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0251  */
    /* JADX WARN: Code duplicated, block: B:49:0x026a  */
    /* JADX WARN: Code duplicated, block: B:51:0x0271  */
    /* JADX WARN: Code duplicated, block: B:54:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:55:0x02cd  */
    /* JADX WARN: Type inference failed for: r9v5, types: [com.jarvis.brain.cloud.ChainCloudProvider$runStream$sink$1] */
    private final void runStream(AICompletionRequest request, final AIStreamListener listener, final AtomicBoolean cancelFlag) {
        String str;
        String str2;
        ChainCloudProvider chainCloudProvider;
        String str3;
        ChainCloudProvider chainCloudProvider2;
        ChainCloudProvider chainCloudProvider3;
        long j;
        final long started = this.clock.invoke().longValue();
        final String requestId = request.getRequestId();
        try {
            try {
                try {
                    runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.START, null, null, null, null, 960, null);
                    List<ChainEntry> listAvailableEntries = DefaultCloudChain.INSTANCE.availableEntries(DefaultCloudChain.INSTANCE.entries(), chainKeys());
                    final Ref.IntRef chunks = new Ref.IntRef();
                    ?? r9 = new TokenSink() { // from class: com.jarvis.brain.cloud.ChainCloudProvider$runStream$sink$1
                        @Override // com.jarvis.core.cloud.TokenSink
                        public void onFirstToken(String text) {
                            Intrinsics.checkNotNullParameter(text, "text");
                            chunks.element++;
                            ChainCloudProvider.runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.TEXT, text, null, null, null, 896, null);
                        }

                        @Override // com.jarvis.core.cloud.TokenSink
                        public void onToken(String text) {
                            Intrinsics.checkNotNullParameter(text, "text");
                            chunks.element++;
                            ChainCloudProvider.runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.TEXT, text, null, null, null, 896, null);
                        }

                        @Override // com.jarvis.core.cloud.TokenSink
                        public void onComplete(Map<String, ? extends Object> usage) {
                        }
                    };
                    str3 = " attempts=[";
                    try {
                        CloudCall call = buildCloudCall(request);
                        chainCloudProvider2 = this;
                        try {
                            try {
                                ChainResult result = (ChainResult) BuildersKt__BuildersKt.runBlocking$default(null, new ChainCloudProvider$runStream$result$1(cancelFlag, this, listAvailableEntries, call, r9, null), 1, null);
                                if (cancelFlag.get()) {
                                    try {
                                        runStream$send$default(listener, requestId, this, started, AIStreamEventType.CANCELLED, null, null, null, null, 480, null);
                                        return;
                                    } catch (CancellationException e) {
                                        chainCloudProvider = chainCloudProvider2;
                                        str = TAG;
                                        str2 = "stream requestId=";
                                    }
                                } else {
                                    chainCloudProvider2.lastWinner = result.getEntry().getProviderId() + "/" + result.getEntry().getModelId();
                                    chainCloudProvider2.lastHedged = result.getHedged();
                                    chainCloudProvider2.lastFailures = result.getFailures();
                                    runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.COMPLETE, null, result.getEntry().getModelId(), null, null, 832, null);
                                    runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.METADATA, "ttftMs=" + result.getTimeToFirstTokenMs() + ";chunks=" + chunks.element + ";latencyMs=" + (chainCloudProvider2.clock.invoke().longValue() - started) + ";hedged=" + result.getHedged() + ";entry=" + result.getEntry().getProviderId() + "/" + result.getEntry().getModelId(), result.getEntry().getModelId(), null, null, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
                                    SafeLog safeLog = SafeLog.INSTANCE;
                                    String requestId2 = request.getRequestId();
                                    String str4 = chainCloudProvider2.lastWinner;
                                    boolean hedged = result.getHedged();
                                    long timeToFirstTokenMs = result.getTimeToFirstTokenMs();
                                    long jLongValue = chainCloudProvider2.clock.invoke().longValue() - started;
                                    int i = chunks.element;
                                    String strJoinToString$default = CollectionsKt.joinToString$default(result.getAttemptReport(), null, null, null, 0, null, null, 63, null);
                                    str2 = "stream requestId=";
                                    try {
                                        String str5 = str2 + requestId2 + " winner=" + str4 + " hedged=" + hedged + " ttftMs=" + timeToFirstTokenMs + " totalMs=" + jLongValue + " chunks=" + i + str3 + strJoinToString$default + "]";
                                        str = TAG;
                                        try {
                                            safeLog.i(str, str5);
                                            return;
                                        } catch (AllFailedException e2) {
                                            e = e2;
                                            if (cancelFlag.get()) {
                                                runStream$send$default(listener, requestId, this, started, AIStreamEventType.CANCELLED, null, null, null, null, 480, null);
                                                return;
                                            }
                                            chainCloudProvider3 = chainCloudProvider2;
                                            if (!e.getPartial()) {
                                                chainCloudProvider3.allFailedUntil = chainCloudProvider3.clock.invoke().longValue() + chainCloudProvider3.allFailedCooldownMs;
                                            }
                                            chainCloudProvider3.lastFailures = e.getFailures();
                                            SafeLog safeLog2 = SafeLog.INSTANCE;
                                            String requestId3 = request.getRequestId();
                                            boolean hedged2 = e.getHedged();
                                            boolean partial = e.getPartial();
                                            long jLongValue2 = chainCloudProvider3.clock.invoke().longValue() - started;
                                            List<ErrorClass> failures = e.getFailures();
                                            String strJoinToString$default2 = CollectionsKt.joinToString$default(e.getAttemptReport(), null, null, null, 0, null, null, 63, null);
                                            if (e.getPartial()) {
                                                j = 0;
                                            } else {
                                                j = chainCloudProvider3.allFailedCooldownMs;
                                            }
                                            safeLog2.i(str, str2 + requestId3 + " allFailed winner=null hedged=" + hedged2 + " partial=" + partial + " ttftMs=null totalMs=" + jLongValue2 + " failures=" + failures + str3 + strJoinToString$default2 + "] cooldownMs=" + j);
                                            runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.ERROR, null, null, "CHAIN_ALL_FAILED", CollectionsKt.joinToString$default(e.getFailures(), ",", null, null, 0, null, null, 62, null), 192, null);
                                            return;
                                        } catch (CancellationException e3) {
                                        }
                                    } catch (AllFailedException e4) {
                                        e = e4;
                                        str = TAG;
                                    } catch (CancellationException e5) {
                                        str = TAG;
                                    }
                                }
                            } catch (CancellationException e6) {
                                str = TAG;
                                str2 = "stream requestId=";
                            }
                        } catch (AllFailedException e7) {
                            e = e7;
                            str = TAG;
                            str2 = "stream requestId=";
                            if (cancelFlag.get()) {
                                runStream$send$default(listener, requestId, this, started, AIStreamEventType.CANCELLED, null, null, null, null, 480, null);
                                return;
                            }
                            chainCloudProvider3 = chainCloudProvider2;
                            if (!e.getPartial()) {
                                chainCloudProvider3.allFailedUntil = chainCloudProvider3.clock.invoke().longValue() + chainCloudProvider3.allFailedCooldownMs;
                            }
                            chainCloudProvider3.lastFailures = e.getFailures();
                            SafeLog safeLog3 = SafeLog.INSTANCE;
                            String requestId4 = request.getRequestId();
                            boolean hedged3 = e.getHedged();
                            boolean partial2 = e.getPartial();
                            long jLongValue3 = chainCloudProvider3.clock.invoke().longValue() - started;
                            List<ErrorClass> failures2 = e.getFailures();
                            String strJoinToString$default3 = CollectionsKt.joinToString$default(e.getAttemptReport(), null, null, null, 0, null, null, 63, null);
                            if (e.getPartial()) {
                                j = 0;
                            } else {
                                j = chainCloudProvider3.allFailedCooldownMs;
                            }
                            safeLog3.i(str, str2 + requestId4 + " allFailed winner=null hedged=" + hedged3 + " partial=" + partial2 + " ttftMs=null totalMs=" + jLongValue3 + " failures=" + failures2 + str3 + strJoinToString$default3 + "] cooldownMs=" + j);
                            runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.ERROR, null, null, "CHAIN_ALL_FAILED", CollectionsKt.joinToString$default(e.getFailures(), ",", null, null, 0, null, null, 62, null), 192, null);
                            return;
                        }
                    } catch (AllFailedException e8) {
                        e = e8;
                        chainCloudProvider2 = this;
                        str = TAG;
                        str2 = "stream requestId=";
                        if (cancelFlag.get()) {
                            runStream$send$default(listener, requestId, this, started, AIStreamEventType.CANCELLED, null, null, null, null, 480, null);
                            return;
                        }
                        chainCloudProvider3 = chainCloudProvider2;
                        if (!e.getPartial()) {
                            chainCloudProvider3.allFailedUntil = chainCloudProvider3.clock.invoke().longValue() + chainCloudProvider3.allFailedCooldownMs;
                        }
                        chainCloudProvider3.lastFailures = e.getFailures();
                        SafeLog safeLog4 = SafeLog.INSTANCE;
                        String requestId5 = request.getRequestId();
                        boolean hedged4 = e.getHedged();
                        boolean partial3 = e.getPartial();
                        long jLongValue4 = chainCloudProvider3.clock.invoke().longValue() - started;
                        List<ErrorClass> failures3 = e.getFailures();
                        String strJoinToString$default4 = CollectionsKt.joinToString$default(e.getAttemptReport(), null, null, null, 0, null, null, 63, null);
                        if (e.getPartial()) {
                            j = 0;
                        } else {
                            j = chainCloudProvider3.allFailedCooldownMs;
                        }
                        safeLog4.i(str, str2 + requestId5 + " allFailed winner=null hedged=" + hedged4 + " partial=" + partial3 + " ttftMs=null totalMs=" + jLongValue4 + " failures=" + failures3 + str3 + strJoinToString$default4 + "] cooldownMs=" + j);
                        runStream$emit$default(cancelFlag, listener, requestId, this, started, AIStreamEventType.ERROR, null, null, "CHAIN_ALL_FAILED", CollectionsKt.joinToString$default(e.getFailures(), ",", null, null, 0, null, null, 62, null), 192, null);
                        return;
                    }
                } catch (CancellationException e9) {
                    chainCloudProvider2 = this;
                }
            } catch (AllFailedException e10) {
                e = e10;
                str3 = " attempts=[";
            }
            chainCloudProvider = chainCloudProvider2;
        } catch (AllFailedException e11) {
            e = e11;
            str = TAG;
            str2 = "stream requestId=";
            str3 = " attempts=[";
            chainCloudProvider2 = this;
        } catch (CancellationException e12) {
            str = TAG;
            str2 = "stream requestId=";
            chainCloudProvider = this;
        }
        SafeLog.INSTANCE.i(str, str2 + request.getRequestId() + " cancelled totalMs=" + (chainCloudProvider.clock.invoke().longValue() - started));
        runStream$send$default(listener, requestId, this, started, AIStreamEventType.CANCELLED, null, null, null, null, 480, null);
    }

    static /* synthetic */ void runStream$send$default(AIStreamListener aIStreamListener, String str, ChainCloudProvider chainCloudProvider, long j, AIStreamEventType aIStreamEventType, String str2, String str3, String str4, String str5, int i, Object obj) {
        runStream$send(aIStreamListener, str, chainCloudProvider, j, aIStreamEventType, (i & 32) != 0 ? "" : str2, (i & 64) != 0 ? null : str3, (i & 128) != 0 ? null : str4, (i & 256) != 0 ? null : str5);
    }

    private static final void runStream$send(AIStreamListener $listener, String requestId, ChainCloudProvider this$0, long started, AIStreamEventType type, String text, String modelId, String errorCode, String errorMessage) {
        $listener.onEvent(new AIStreamEvent(type, text, requestId, this$0.getId(), modelId, errorCode, errorMessage, this$0.clock.invoke().longValue() - started));
    }

    static /* synthetic */ void runStream$emit$default(AtomicBoolean atomicBoolean, AIStreamListener aIStreamListener, String str, ChainCloudProvider chainCloudProvider, long j, AIStreamEventType aIStreamEventType, String str2, String str3, String str4, String str5, int i, Object obj) {
        runStream$emit(atomicBoolean, aIStreamListener, str, chainCloudProvider, j, aIStreamEventType, (i & 64) != 0 ? "" : str2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : str4, (i & 512) != 0 ? null : str5);
    }

    private static final void runStream$emit(AtomicBoolean $cancelFlag, AIStreamListener $listener, String requestId, ChainCloudProvider this$0, long started, AIStreamEventType type, String text, String modelId, String errorCode, String errorMessage) {
        if (!$cancelFlag.get()) {
            runStream$send($listener, requestId, this$0, started, type, text, modelId, errorCode, errorMessage);
        }
    }

    private final String systemPrompt(AICompletionRequest request) {
        String base = this.legacy.resolveSystem$brain_debug(request);
        boolean chat = !request.getWantStructured() && (request.getTaskType() == null || request.getTaskType() == AiTaskType.CHAT);
        String today = new SimpleDateFormat("EEEE, d MMMM yyyy, HH:mm", new Locale("ru")).format(new Date());
        if (chat) {
            return base + "\nСейчас: " + today + ".\n" + CloudToolAction.INSTANCE.getPROMPT();
        }
        return base;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CloudCall buildCloudCall(AICompletionRequest request) {
        CloudCall cloudCallInvoke;
        Function1<AICompletionRequest, CloudCall> function1 = this.callFactory;
        if (function1 == null || (cloudCallInvoke = function1.invoke(request)) == null) {
            return new CloudChainCall(systemPrompt(request), request.getHistory(), request.getUserText(), RangesKt.coerceAtLeast(getCapabilities().getMaxOutputTokens(), 16), this.keyFor, this.realTransport, (int) this.config.getTotalDeadlineMs(), (int) this.config.getConnectTimeoutMs(), null, 256, null);
        }
        return cloudCallInvoke;
    }

    /* JADX INFO: compiled from: ChainCloudProvider.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/jarvis/brain/cloud/ChainCloudProvider$Companion;", "", "<init>", "()V", "TAG", "", "CANCEL_POLL_MS", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
