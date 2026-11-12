package com.jarvis.brain.aiprovider;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.app.proactive.PhoneStateReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AIRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 I2\u00020\u0001:\u0001IBS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\nJ\"\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&J.\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\nJ\u0006\u0010'\u001a\u00020(J$\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00162\u0006\u0010\u001f\u001a\u00020 2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u0016H\u0002J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010/\u001a\u0002002\u0006\u0010.\u001a\u00020*H\u0002J \u00101\u001a\u0004\u0018\u00010*2\f\u00102\u001a\b\u0012\u0004\u0012\u00020*0\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002J \u00103\u001a\u0004\u0018\u00010*2\f\u00104\u001a\b\u0012\u0004\u0012\u00020*0\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002J$\u00105\u001a\b\u0012\u0004\u0012\u00020*0\u00162\f\u00106\u001a\b\u0012\u0004\u0012\u00020*0\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u00107\u001a\u00020\n2\u0006\u00108\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020 H\u0002J \u00109\u001a\u0004\u0018\u00010*2\u0006\u0010\u001f\u001a\u00020 2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\n0;H\u0002J\\\u0010<\u001a\u00020\u001c2\u0006\u00108\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\n2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\n0\u00162\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\n0\u00162\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\n0\u00162\b\b\u0002\u0010C\u001a\u00020-H\u0002J4\u0010D\u001a\u00020\u001c2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\n0\u00162\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\n0\u00162\u0006\u0010?\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 H\u0002J \u0010E\u001a\u00020F2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010G\u001a\u00020\u001c2\u0006\u0010H\u001a\u00020$H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R*\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006J"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIRouter;", "", "registry", "Lcom/jarvis/brain/aiprovider/AIProviderRegistry;", "health", "Lcom/jarvis/brain/aiprovider/ProviderHealthTracker;", "metrics", "Lcom/jarvis/brain/aiprovider/ProviderMetricsStore;", "cloudFastModel", "Lkotlin/Function0;", "", "cloudQualityModel", "localModelId", "<init>", "(Lcom/jarvis/brain/aiprovider/AIProviderRegistry;Lcom/jarvis/brain/aiprovider/ProviderHealthTracker;Lcom/jarvis/brain/aiprovider/ProviderMetricsStore;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "healthTracker", "getHealthTracker", "()Lcom/jarvis/brain/aiprovider/ProviderHealthTracker;", "metricsStore", "getMetricsStore", "()Lcom/jarvis/brain/aiprovider/ProviderMetricsStore;", "value", "", "Lcom/jarvis/brain/aiprovider/AIRouteAttempt;", "lastAttempts", "getLastAttempts", "()Ljava/util/List;", "select", "Lcom/jarvis/brain/aiprovider/AIProviderSelection;", "context", "Lcom/jarvis/brain/aiprovider/AIRouterContext;", Scopes.PROFILE, "Lcom/jarvis/brain/aiprovider/AiTaskProfile;", "preferredProviderId", "complete", "Lkotlin/Pair;", "Lcom/jarvis/brain/aiprovider/AICompletionResult;", "request", "Lcom/jarvis/brain/aiprovider/AICompletionRequest;", "aggregateMetrics", "Lcom/jarvis/brain/aiprovider/RouteAggregateMetrics;", "filterCandidates", "Lcom/jarvis/brain/aiprovider/AIProvider;", "all", "isUsable", "", "p", "effectiveAvailability", "Lcom/jarvis/brain/aiprovider/AIProviderAvailability;", "pickLocal", "locals", "firstHealthyCloud", "clouds", "rank", "list", "resolveModelId", "provider", "nextFallback", "tried", "", "selection", "code", "Lcom/jarvis/brain/aiprovider/AIRouterReasonCode;", "reason", "considered", "candidates", "models", "fallback", PhoneStateReader.FAKE_NONE, "logTrace", "", "sel", "result", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AIRouter {
    public static final int MAX_ATTEMPTS = 3;
    private final Function0<String> cloudFastModel;
    private final Function0<String> cloudQualityModel;
    private final ProviderHealthTracker health;
    private volatile List<AIRouteAttempt> lastAttempts;
    private final Function0<String> localModelId;
    private final ProviderMetricsStore metrics;
    private final AIProviderRegistry registry;

    /* JADX INFO: compiled from: AIRouter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ProviderHealthStatus.values().length];
            try {
                iArr[ProviderHealthStatus.AVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ProviderHealthStatus.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ProviderHealthStatus.DEGRADED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ProviderHealthStatus.UNAVAILABLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[QualityRequirement.values().length];
            try {
                iArr2[QualityRequirement.REASONING.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[QualityRequirement.QUALITY.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[QualityRequirement.BALANCED.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[QualityRequirement.FAST.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public AIRouter(AIProviderRegistry registry, ProviderHealthTracker health, ProviderMetricsStore metrics, Function0<String> cloudFastModel, Function0<String> cloudQualityModel, Function0<String> localModelId) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        Intrinsics.checkNotNullParameter(health, "health");
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        Intrinsics.checkNotNullParameter(cloudFastModel, "cloudFastModel");
        Intrinsics.checkNotNullParameter(cloudQualityModel, "cloudQualityModel");
        Intrinsics.checkNotNullParameter(localModelId, "localModelId");
        this.registry = registry;
        this.health = health;
        this.metrics = metrics;
        this.cloudFastModel = cloudFastModel;
        this.cloudQualityModel = cloudQualityModel;
        this.localModelId = localModelId;
        this.lastAttempts = CollectionsKt.emptyList();
    }

    public /* synthetic */ AIRouter(AIProviderRegistry aIProviderRegistry, ProviderHealthTracker providerHealthTracker, ProviderMetricsStore providerMetricsStore, Function0 function0, Function0 function1, Function0 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(aIProviderRegistry, (i & 2) != 0 ? new ProviderHealthTracker(0L, 0, 0, null, 15, null) : providerHealthTracker, (i & 4) != 0 ? new ProviderMetricsStore(0, 1, null) : providerMetricsStore, (i & 8) != 0 ? new Function0() { // from class: com.jarvis.brain.aiprovider.AIRouter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NaraRouterDefaults.MODEL_FAST;
            }
        } : function0, (i & 16) != 0 ? new Function0() { // from class: com.jarvis.brain.aiprovider.AIRouter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NaraRouterDefaults.MODEL_QUALITY;
            }
        } : function1, (i & 32) != 0 ? new Function0() { // from class: com.jarvis.brain.aiprovider.AIRouter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ImagesContract.LOCAL;
            }
        } : function2);
    }

    /* JADX INFO: renamed from: getHealthTracker, reason: from getter */
    public final ProviderHealthTracker getHealth() {
        return this.health;
    }

    /* JADX INFO: renamed from: getMetricsStore, reason: from getter */
    public final ProviderMetricsStore getMetrics() {
        return this.metrics;
    }

    public final List<AIRouteAttempt> getLastAttempts() {
        return this.lastAttempts;
    }

    public final AIProviderSelection select(AIRouterContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return select(AiTaskProfile.Companion.fromLegacy$default(AiTaskProfile.INSTANCE, context, null, 2, null), context.getPreferredProviderId());
    }

    public static /* synthetic */ AIProviderSelection select$default(AIRouter aIRouter, AiTaskProfile aiTaskProfile, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return aIRouter.select(aiTaskProfile, str);
    }

    public final AIProviderSelection select(AiTaskProfile profile, String preferredProviderId) {
        List<? extends AIProvider> list;
        Object element$iv;
        Object obj;
        AIProvider fastest;
        AIProvider best;
        AIProvider pref;
        Intrinsics.checkNotNullParameter(profile, "profile");
        List considered = new ArrayList();
        List<AIProvider> listLocalProviders = this.registry.localProviders();
        List<AIProvider> listCloudProviders = this.registry.cloudProviders();
        List<AIProvider> $this$forEach$iv = listLocalProviders;
        for (Object element$iv2 : $this$forEach$iv) {
            AIProvider it = (AIProvider) element$iv2;
            considered.add(it.getId() + ":" + effectiveAvailability(it));
        }
        List<AIProvider> $this$forEach$iv2 = listCloudProviders;
        for (Object element$iv3 : $this$forEach$iv2) {
            AIProvider it2 = (AIProvider) element$iv3;
            considered.add(it2.getId() + ":" + effectiveAvailability(it2));
        }
        Iterable iterableFilterCandidates = filterCandidates(profile, CollectionsKt.plus((Collection) listLocalProviders, (Iterable) listCloudProviders));
        Iterable $this$map$iv = iterableFilterCandidates;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(((AIProvider) item$iv$iv).getId());
        }
        List candidateIds = (List) destination$iv$iv;
        Iterable $this$map$iv2 = iterableFilterCandidates;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            destination$iv$iv2.add(resolveModelId((AIProvider) item$iv$iv2, profile));
        }
        List candidateModels = (List) destination$iv$iv2;
        if (preferredProviderId != null && (pref = this.registry.find(preferredProviderId)) != null && isUsable(pref, profile)) {
            if (profile.getPrivacyLevel() != PrivacyLevel.LOCAL_REQUIRED || pref.getKind() != AIProviderKind.CLOUD) {
                return selection$default(this, pref, profile, AIRouterReasonCode.PREFERRED, "Preferred provider " + preferredProviderId, considered, candidateIds, candidateModels, false, 128, null);
            }
        }
        if (profile.getPrivacyLevel() == PrivacyLevel.LOCAL_REQUIRED || (profile.getPrivacyLevel() == PrivacyLevel.SENSITIVE && !profile.getPreferCloud())) {
            AIProvider local = pickLocal(listLocalProviders, r14);
            return local != null ? selection$default(this, local, profile, AIRouterReasonCode.PRIVACY_LOCAL_ONLY, "Privacy → local only", considered, r9, candidateModels, false, 128, null) : none(considered, r9, "Privacy-restricted and no local provider", profile);
        }
        if (!profile.isOnline()) {
            AIProvider local2 = pickLocal(listLocalProviders, profile);
            return local2 != null ? selection$default(this, local2, profile, AIRouterReasonCode.OFFLINE_LOCAL, "Offline → local", considered, r9, candidateModels, false, 128, null) : none(considered, r9, "Offline and no local provider", profile);
        }
        if (profile.getStreamingRequired()) {
            list = iterableFilterCandidates;
            List<? extends AIProvider> $this$filter$iv = list;
            Collection destination$iv$iv3 = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                if (((AIProvider) element$iv$iv).getCapabilities().getSupportsStreaming()) {
                    destination$iv$iv3.add(element$iv$iv);
                }
            }
            List streamOk = (List) destination$iv$iv3;
            if (!streamOk.isEmpty() && (best = (AIProvider) CollectionsKt.firstOrNull((List) rank(streamOk, profile))) != null) {
                return selection$default(this, best, profile, AIRouterReasonCode.STREAMING_CAPABLE, "Streaming required → " + best.getId(), considered, r9, candidateModels, false, 128, null);
            }
        } else {
            list = iterableFilterCandidates;
        }
        if (profile.getTaskType() == AiTaskType.SEMANTIC_INTERPRETATION || profile.getStructuredOutputRequired()) {
            Iterable $this$filter$iv2 = list;
            Collection destination$iv$iv4 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                AIProvider it3 = (AIProvider) element$iv$iv2;
                if (it3.getCapabilities().getSupportsStructuredDecide() && (it3.getKind() == AIProviderKind.LOCAL || profile.getPreferCloud())) {
                    destination$iv$iv4.add(element$iv$iv2);
                }
            }
            Iterable structured = (List) destination$iv$iv4;
            Iterable $this$firstOrNull$iv = structured;
            Iterator it4 = $this$firstOrNull$iv.iterator();
            do {
                if (!it4.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it4.next();
            } while (!(((AIProvider) element$iv).getKind() == AIProviderKind.LOCAL));
            AIProvider it5 = (AIProvider) element$iv;
            if (it5 == null && ((it5 = pickLocal(listLocalProviders, profile)) == null || !it5.getCapabilities().getSupportsStructuredDecide())) {
                it5 = null;
            }
            AIProvider localStruct = it5;
            if (localStruct != null && !profile.getPreferCloud()) {
                return selection$default(this, localStruct, profile, AIRouterReasonCode.STRUCTURED_LOCAL, "Structured/semantic → local fast", considered, r15, candidateModels, false, 128, null);
            }
            Iterable $this$firstOrNull$iv2 = structured;
            Iterator it6 = $this$firstOrNull$iv2.iterator();
            while (true) {
                if (!it6.hasNext()) {
                    obj = null;
                    break;
                }
                Object element$iv4 = it6.next();
                if (((AIProvider) element$iv4).getKind() == AIProviderKind.CLOUD) {
                    obj = element$iv4;
                    break;
                }
            }
            AIProvider cloudStruct = (AIProvider) obj;
            if (cloudStruct != null && profile.getPreferCloud()) {
                return selection$default(this, cloudStruct, profile, AIRouterReasonCode.ONLINE_SIMPLE_CLOUD_PREF, "Structured + preferCloud → cloud", considered, r15, candidateModels, false, 128, null);
            }
            if (localStruct != null) {
                return selection$default(this, localStruct, profile, AIRouterReasonCode.STRUCTURED_LOCAL, "Structured → local", considered, r15, candidateModels, false, 128, null);
            }
        }
        if (!profile.getPreferCloud() && profile.getLatencySensitivity() == LatencySensitivity.HIGH && profile.getQualityRequirement() == QualityRequirement.FAST && (fastest = (AIProvider) CollectionsKt.firstOrNull((List) rank(list, profile))) != null) {
            return selection$default(this, fastest, profile, AIRouterReasonCode.LATENCY_PRIORITY, "Latency priority → " + fastest.getId(), considered, r9, candidateModels, false, 128, null);
        }
        if (profile.getReasoningRequired() || profile.getQualityRequirement() == QualityRequirement.REASONING || profile.getQualityRequirement() == QualityRequirement.QUALITY || profile.getTaskType() == AiTaskType.PLANNING || profile.getComplexity() == AIRequestComplexity.COMPLEX) {
            AIProvider cloud = firstHealthyCloud(listCloudProviders, profile);
            if (cloud != null && (profile.getPreferCloud() || profile.getReasoningRequired() || profile.getTaskType() == AiTaskType.PLANNING)) {
                return selection$default(this, cloud, profile, AIRouterReasonCode.ONLINE_QUALITY, "Online quality/reasoning → cloud", considered, r15, candidateModels, false, 128, null);
            }
            if (cloud != null && profile.getPreferCloud()) {
                return selection$default(this, cloud, profile, AIRouterReasonCode.ONLINE_COMPLEX_CLOUD, "Online + complex → cloud", considered, r15, candidateModels, false, 128, null);
            }
            AIProvider local3 = pickLocal(listLocalProviders, profile);
            if (local3 != null) {
                return selection(local3, profile, AIRouterReasonCode.FALLBACK_LOCAL, "Cloud unavailable for quality task → local", considered, r15, candidateModels, cloud == null && profile.getPreferCloud());
            }
        }
        if (profile.getPreferCloud()) {
            AIProvider cloud2 = firstHealthyCloud(listCloudProviders, profile);
            if (cloud2 != null) {
                return selection$default(this, cloud2, profile, AIRouterReasonCode.ONLINE_SIMPLE_CLOUD_PREF, "Online + preferCloud → cloud", considered, r15, candidateModels, false, 128, null);
            }
            AIProvider local4 = pickLocal(listLocalProviders, profile);
            if (local4 != null) {
                return selection(local4, profile, AIRouterReasonCode.CLOUD_UNAVAILABLE, "Cloud unavailable → local fallback", considered, r15, candidateModels, true);
            }
        }
        AIProvider local5 = pickLocal(listLocalProviders, profile);
        if (local5 != null) {
            return selection$default(this, local5, profile, AIRouterReasonCode.ONLINE_SIMPLE_LOCAL, "Online → local (default)", considered, r15, candidateModels, false, 128, null);
        }
        AIProvider aIProvider = (AIProvider) CollectionsKt.firstOrNull((List) list);
        if (aIProvider == null) {
            aIProvider = (AIProvider) CollectionsKt.firstOrNull((List) this.registry.available());
        }
        AIProvider any = aIProvider;
        return any != null ? selection(any, profile, AIRouterReasonCode.FALLBACK_ANY_AVAILABLE, "Fallback any available " + any.getId(), considered, r15, candidateModels, true) : none(considered, r15, "No AI providers available", profile);
    }

    public final Pair<AIProviderSelection, AICompletionResult> complete(AIRouterContext context, AICompletionRequest request) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(request, "request");
        return complete$default(this, AiTaskProfile.INSTANCE.fromLegacy(context, request.getRequestId()), request, null, 4, null);
    }

    public static /* synthetic */ Pair complete$default(AIRouter aIRouter, AiTaskProfile aiTaskProfile, AICompletionRequest aICompletionRequest, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        return aIRouter.complete(aiTaskProfile, aICompletionRequest, str);
    }

    public final Pair<AIProviderSelection, AICompletionResult> complete(AiTaskProfile profile, AICompletionRequest request, String preferredProviderId) {
        AIProviderSelection selection;
        AIProvider provider;
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(request, "request");
        List attempts = new ArrayList();
        AIProviderSelection selection2 = select(profile, preferredProviderId);
        AIProvider provider2 = selection2.getProvider();
        if (provider2 == null) {
            this.lastAttempts = attempts;
            return TuplesKt.to(selection2, new AICompletionResult(request.getRequestId(), PhoneStateReader.FAKE_NONE, AICompletionStatus.FAILURE, null, 0L, new AIProviderError(AIProviderErrorCategory.UNAVAILABLE, "NO_PROVIDER", selection2.getReason()), false, null, null, null, null, null, 4056, null));
        }
        Set tried = new LinkedHashSet();
        AIProviderSelection selection3 = selection2;
        AIProvider provider3 = provider2;
        int attempt = 1;
        while (true) {
            if (attempt > 3) {
                selection = selection3;
                provider = provider3;
                break;
            }
            tried.add(provider3.getId());
            String modelId = resolveModelId(provider3, profile);
            int attempt2 = attempt;
            AIProvider provider4 = provider3;
            selection = selection3;
            Set tried2 = tried;
            AICompletionRequest enriched = request.copy((TypedValues.MotionType.TYPE_PATHMOTION_ARC & 1) != 0 ? request.requestId : null, (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 2) != 0 ? request.userText : null, (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 4) != 0 ? request.history : null, (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 8) != 0 ? request.memories : null, (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 16) != 0 ? request.persona : null, (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 32) != 0 ? request.wantStructured : request.getWantStructured() || profile.getStructuredOutputRequired(), (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 64) != 0 ? request.cancelRequested : false, (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 128) != 0 ? request.preferredModelId : modelId, (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 256) != 0 ? request.taskType : profile.getTaskType(), (TypedValues.MotionType.TYPE_PATHMOTION_ARC & 512) != 0 ? request.assembledContext : null);
            provider = provider4;
            AICompletionResult result = provider.complete(enriched);
            List list = attempts;
            String id = provider.getId();
            String modelId2 = result.getModelId();
            String str = modelId2 == null ? modelId : modelId2;
            String selectionReason = selection.getSelectionReason();
            long latencyMs = result.getLatencyMs();
            AIProviderError error = result.getError();
            list.add(new AIRouteAttempt(attempt2, id, str, selectionReason, latencyMs, error != null ? error.getCode() : null));
            if (result.getStatus() != AICompletionStatus.SUCCESS) {
                this.health.recordFailure(provider.getId(), result.getError(), result.getLatencyMs());
                ProviderMetricsStore.record$default(this.metrics, provider.getId(), provider.getKind(), result.getLatencyMs(), false, selection.getFallbackUsed(), null, 32, null);
                if (!profile.getFallbackAllowed() || profile.getPrivacyLevel() == PrivacyLevel.LOCAL_REQUIRED) {
                    break;
                    break;
                }
                AIProvider next = nextFallback(profile, tried2);
                if (next == null) {
                    this.lastAttempts = attempts;
                    AIProviderSelection failSel = selection.copy((1424 & 1) != 0 ? selection.provider : null, (1424 & 2) != 0 ? selection.reasonCode : null, (1424 & 4) != 0 ? selection.reason : null, (1424 & 8) != 0 ? selection.fallbackUsed : selection.getFallbackUsed(), (1424 & 16) != 0 ? selection.considered : null, (1424 & 32) != 0 ? selection.modelId : modelId, (1424 & 64) != 0 ? selection.selectionReason : null, (1424 & 128) != 0 ? selection.candidateProviders : null, (1424 & 256) != 0 ? selection.candidateModels : null, (1424 & 512) != 0 ? selection.providerHealth : this.health.snapshot(provider.getId()), (1424 & 1024) != 0 ? selection.profile : null, (1424 & 2048) != 0 ? selection.attempt : attempt2);
                    logTrace(profile, failSel, result);
                    return TuplesKt.to(failSel, result);
                }
                attempt = attempt2 + 1;
                tried = tried2;
                selection3 = selection.copy((1424 & 1) != 0 ? selection.provider : next, (1424 & 2) != 0 ? selection.reasonCode : AIRouterReasonCode.FALLBACK_LOCAL, (1424 & 4) != 0 ? selection.reason : "Fallback after " + provider.getId() + " error → " + next.getId(), (1424 & 8) != 0 ? selection.fallbackUsed : true, (1424 & 16) != 0 ? selection.considered : null, (1424 & 32) != 0 ? selection.modelId : resolveModelId(next, profile), (1424 & 64) != 0 ? selection.selectionReason : "FALLBACK_AFTER_ERROR", (1424 & 128) != 0 ? selection.candidateProviders : null, (1424 & 256) != 0 ? selection.candidateModels : null, (1424 & 512) != 0 ? selection.providerHealth : this.health.snapshot(next.getId()), (1424 & 1024) != 0 ? selection.profile : null, (1424 & 2048) != 0 ? selection.attempt : attempt2 + 1);
                provider3 = next;
            } else {
                this.health.recordSuccess(provider.getId());
                this.metrics.record(provider.getId(), provider.getKind(), result.getLatencyMs(), true, selection.getFallbackUsed(), result.getTtftMs());
                this.lastAttempts = attempts;
                String modelId3 = result.getModelId();
                AIProviderSelection finalSel = selection.copy((1424 & 1) != 0 ? selection.provider : null, (1424 & 2) != 0 ? selection.reasonCode : null, (1424 & 4) != 0 ? selection.reason : null, (1424 & 8) != 0 ? selection.fallbackUsed : false, (1424 & 16) != 0 ? selection.considered : null, (1424 & 32) != 0 ? selection.modelId : modelId3 == null ? modelId : modelId3, (1424 & 64) != 0 ? selection.selectionReason : null, (1424 & 128) != 0 ? selection.candidateProviders : null, (1424 & 256) != 0 ? selection.candidateModels : null, (1424 & 512) != 0 ? selection.providerHealth : this.health.snapshot(provider.getId()), (1424 & 1024) != 0 ? selection.profile : null, (1424 & 2048) != 0 ? selection.attempt : attempt2);
                logTrace(profile, finalSel, result);
                String modelId4 = result.getModelId();
                return TuplesKt.to(finalSel, result.copy((3967 & 1) != 0 ? result.requestId : null, (3967 & 2) != 0 ? result.providerId : null, (3967 & 4) != 0 ? result.status : null, (3967 & 8) != 0 ? result.text : null, (3967 & 16) != 0 ? result.latencyMs : 0L, (3967 & 32) != 0 ? result.error : null, (3967 & 64) != 0 ? result.streamed : false, (3967 & 128) != 0 ? result.modelId : modelId4 == null ? modelId : modelId4, (3967 & 256) != 0 ? result.ttftMs : null, (3967 & 512) != 0 ? result.ttftKind : null, (3967 & 1024) != 0 ? result.inputTokens : null, (3967 & 2048) != 0 ? result.outputTokens : null));
            }
        }
        this.lastAttempts = attempts;
        String requestId = request.getRequestId();
        String id2 = provider.getId();
        if (id2 == null) {
            id2 = PhoneStateReader.FAKE_NONE;
        }
        return TuplesKt.to(selection, new AICompletionResult(requestId, id2, AICompletionStatus.FAILURE, null, 0L, new AIProviderError(AIProviderErrorCategory.UNAVAILABLE, "ALL_ATTEMPTS_FAILED", "All provider attempts failed"), false, null, null, null, null, null, 4056, null));
    }

    public final RouteAggregateMetrics aggregateMetrics() {
        return this.metrics.aggregate();
    }

    private final List<AIProvider> filterCandidates(AiTaskProfile profile, List<? extends AIProvider> all) {
        List<? extends AIProvider> $this$filter$iv = all;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AIProvider p = (AIProvider) element$iv$iv;
            boolean z = false;
            if (isUsable(p, profile) && ((profile.getPrivacyLevel() != PrivacyLevel.LOCAL_REQUIRED || p.getKind() != AIProviderKind.CLOUD) && ((profile.isOnline() || !p.getCapabilities().getRequiresNetwork()) && (!profile.getStructuredOutputRequired() || p.getCapabilities().getSupportsStructuredDecide())))) {
                if (profile.getContextSizeTokens() > 0 && p.getCapabilities().getMaxContextTokens() > 0) {
                    profile.getContextSizeTokens();
                    p.getCapabilities().getMaxContextTokens();
                }
                QuotaState quota = this.health.quota(p.getId());
                if (quota != QuotaState.EXHAUSTED) {
                    z = true;
                }
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    private final boolean isUsable(AIProvider p, AiTaskProfile profile) {
        AIProviderAvailability av = effectiveAvailability(p);
        if (av == AIProviderAvailability.UNAVAILABLE) {
            return false;
        }
        if (p.getKind() == AIProviderKind.LOCAL) {
            return av == AIProviderAvailability.AVAILABLE || av == AIProviderAvailability.DEGRADED;
        }
        ProviderHealthStatus hs = this.health.status(p.getId());
        if (hs == ProviderHealthStatus.UNAVAILABLE) {
            return false;
        }
        return av == AIProviderAvailability.AVAILABLE || hs == ProviderHealthStatus.DEGRADED;
    }

    private final AIProviderAvailability effectiveAvailability(AIProvider p) {
        AIProviderAvailability base = p.availability();
        ProviderHealthStatus hs = this.health.status(p.getId());
        if (hs == ProviderHealthStatus.UNAVAILABLE) {
            return AIProviderAvailability.UNAVAILABLE;
        }
        return (hs == ProviderHealthStatus.DEGRADED && base == AIProviderAvailability.AVAILABLE) ? AIProviderAvailability.DEGRADED : base;
    }

    private final AIProvider pickLocal(List<? extends AIProvider> locals, AiTaskProfile profile) {
        Object element$iv;
        List<? extends AIProvider> $this$filter$iv = locals;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AIProvider it = (AIProvider) element$iv$iv;
            if (isUsable(it, profile)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        AIProvider aIProvider = (AIProvider) CollectionsKt.firstOrNull((List) rank((List) destination$iv$iv, profile));
        if (aIProvider == null) {
            List<? extends AIProvider> $this$firstOrNull$iv = locals;
            Iterator it2 = $this$firstOrNull$iv.iterator();
            while (it2.hasNext()) {
                element$iv = it2.next();
                AIProvider it3 = (AIProvider) element$iv;
                AIProviderAvailability a = it3.availability();
                if (a == AIProviderAvailability.AVAILABLE || a == AIProviderAvailability.DEGRADED) {
                    return (AIProvider) element$iv;
                }
            }
            element$iv = null;
            return (AIProvider) element$iv;
        }
        return aIProvider;
    }

    private final AIProvider firstHealthyCloud(List<? extends AIProvider> clouds, AiTaskProfile profile) {
        List<? extends AIProvider> $this$filter$iv = clouds;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AIProvider it = (AIProvider) element$iv$iv;
            if (isUsable(it, profile)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (AIProvider) CollectionsKt.firstOrNull((List) rank((List) destination$iv$iv, profile));
    }

    private final List<AIProvider> rank(List<? extends AIProvider> list, final AiTaskProfile profile) {
        List<? extends AIProvider> $this$sortedByDescending$iv = list;
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.brain.aiprovider.AIRouter$rank$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                float f;
                float f2;
                float score;
                float f3;
                float f4;
                float score2;
                AIProvider p = (AIProvider) t2;
                switch (AIRouter.WhenMappings.$EnumSwitchMapping$0[this.this$0.health.status(p.getId()).ordinal()]) {
                    case 1:
                    case 2:
                        f = 1.0f;
                        break;
                    case 3:
                        f = 0.4f;
                        break;
                    case 4:
                        f = -10.0f;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                float score3 = 0.0f + f;
                if (profile.getLatencySensitivity() == LatencySensitivity.HIGH) {
                    score3 += this.this$0.metrics.speedScore(p.getId()) * 2.0f;
                    if (p.getKind() == AIProviderKind.LOCAL) {
                        score3 += 0.3f;
                    }
                }
                switch (AIRouter.WhenMappings.$EnumSwitchMapping$1[profile.getQualityRequirement().ordinal()]) {
                    case 1:
                    case 2:
                        if (p.getKind() == AIProviderKind.CLOUD) {
                            score3 += 1.5f;
                        }
                        switch (AIRouter.WhenMappings.$EnumSwitchMapping$1[p.getCapabilities().getQualityTier().ordinal()]) {
                            case 1:
                                f2 = 1.2f;
                                break;
                            case 2:
                                f2 = 1.0f;
                                break;
                            case 3:
                                f2 = 0.4f;
                                break;
                            case 4:
                                f2 = 0.1f;
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        score = score3 + f2;
                        break;
                    case 3:
                        score = score3 + 0.3f;
                        if (profile.getPreferCloud() && p.getKind() == AIProviderKind.CLOUD) {
                            score += 0.6f;
                        }
                        if (!profile.getPreferCloud() && p.getKind() == AIProviderKind.LOCAL) {
                            score += 0.6f;
                        }
                        break;
                    case 4:
                        score = score3 + this.this$0.metrics.speedScore(p.getId());
                        if (p.getCapabilities().getSpeedTier() == QualityRequirement.FAST) {
                            score += 0.8f;
                        }
                        if (p.getKind() == AIProviderKind.LOCAL) {
                            score += 0.5f;
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                if (profile.getStreamingRequired() && p.getCapabilities().getSupportsStreaming()) {
                    score += 1.0f;
                }
                if (profile.getStructuredOutputRequired() && p.getCapabilities().getSupportsStructuredDecide()) {
                    score += 0.5f;
                }
                Float fValueOf = Float.valueOf(score);
                AIProvider p2 = (AIProvider) t;
                switch (AIRouter.WhenMappings.$EnumSwitchMapping$0[this.this$0.health.status(p2.getId()).ordinal()]) {
                    case 1:
                    case 2:
                        f3 = 1.0f;
                        break;
                    case 3:
                        f3 = 0.4f;
                        break;
                    case 4:
                        f3 = -10.0f;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                float score4 = 0.0f + f3;
                if (profile.getLatencySensitivity() == LatencySensitivity.HIGH) {
                    score4 += this.this$0.metrics.speedScore(p2.getId()) * 2.0f;
                    if (p2.getKind() == AIProviderKind.LOCAL) {
                        score4 += 0.3f;
                    }
                }
                switch (AIRouter.WhenMappings.$EnumSwitchMapping$1[profile.getQualityRequirement().ordinal()]) {
                    case 1:
                    case 2:
                        if (p2.getKind() == AIProviderKind.CLOUD) {
                            score4 += 1.5f;
                        }
                        switch (AIRouter.WhenMappings.$EnumSwitchMapping$1[p2.getCapabilities().getQualityTier().ordinal()]) {
                            case 1:
                                f4 = 1.2f;
                                break;
                            case 2:
                                f4 = 1.0f;
                                break;
                            case 3:
                                f4 = 0.4f;
                                break;
                            case 4:
                                f4 = 0.1f;
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        score2 = score4 + f4;
                        break;
                    case 3:
                        score2 = score4 + 0.3f;
                        if (profile.getPreferCloud() && p2.getKind() == AIProviderKind.CLOUD) {
                            score2 += 0.6f;
                        }
                        if (!profile.getPreferCloud() && p2.getKind() == AIProviderKind.LOCAL) {
                            score2 += 0.6f;
                        }
                        break;
                    case 4:
                        score2 = score4 + this.this$0.metrics.speedScore(p2.getId());
                        if (p2.getCapabilities().getSpeedTier() == QualityRequirement.FAST) {
                            score2 += 0.8f;
                        }
                        if (p2.getKind() == AIProviderKind.LOCAL) {
                            score2 += 0.5f;
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                if (profile.getStreamingRequired() && p2.getCapabilities().getSupportsStreaming()) {
                    score2 += 1.0f;
                }
                if (profile.getStructuredOutputRequired() && p2.getCapabilities().getSupportsStructuredDecide()) {
                    score2 += 0.5f;
                }
                return ComparisonsKt.compareValues(fValueOf, Float.valueOf(score2));
            }
        });
    }

    private final String resolveModelId(AIProvider provider, AiTaskProfile profile) {
        if (provider.getKind() == AIProviderKind.LOCAL) {
            String defaultModelId = provider.getCapabilities().getDefaultModelId();
            if (StringsKt.isBlank(defaultModelId)) {
                defaultModelId = this.localModelId.invoke();
            }
            return defaultModelId;
        }
        switch (WhenMappings.$EnumSwitchMapping$1[profile.getQualityRequirement().ordinal()]) {
            case 1:
            case 2:
                String strInvoke = this.cloudQualityModel.invoke();
                if (StringsKt.isBlank(strInvoke)) {
                    String defaultModelId2 = provider.getCapabilities().getDefaultModelId();
                    if (StringsKt.isBlank(defaultModelId2)) {
                        defaultModelId2 = NaraRouterDefaults.MODEL_QUALITY;
                    }
                    strInvoke = defaultModelId2;
                }
                return strInvoke;
            case 3:
            case 4:
                String strInvoke2 = this.cloudFastModel.invoke();
                if (StringsKt.isBlank(strInvoke2)) {
                    String defaultModelId3 = provider.getCapabilities().getDefaultModelId();
                    if (StringsKt.isBlank(defaultModelId3)) {
                        defaultModelId3 = NaraRouterDefaults.MODEL_FAST;
                    }
                    strInvoke2 = defaultModelId3;
                }
                return strInvoke2;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final AIProvider nextFallback(AiTaskProfile profile, Set<String> tried) {
        Iterable $this$filter$iv = filterCandidates(profile, this.registry.all());
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AIProvider it = (AIProvider) element$iv$iv;
            if (!tried.contains(it.getId())) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List pool = (List) destination$iv$iv;
        return (AIProvider) CollectionsKt.firstOrNull((List) rank(pool, profile));
    }

    static /* synthetic */ AIProviderSelection selection$default(AIRouter aIRouter, AIProvider aIProvider, AiTaskProfile aiTaskProfile, AIRouterReasonCode aIRouterReasonCode, String str, List list, List list2, List list3, boolean z, int i, Object obj) {
        boolean z2;
        if ((i & 128) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        return aIRouter.selection(aIProvider, aiTaskProfile, aIRouterReasonCode, str, list, list2, list3, z2);
    }

    private final AIProviderSelection selection(AIProvider provider, AiTaskProfile profile, AIRouterReasonCode code, String reason, List<String> considered, List<String> candidates, List<String> models, boolean fallback) {
        return new AIProviderSelection(provider, code, reason, fallback, considered, resolveModelId(provider, profile), code.name(), candidates, models, this.health.snapshot(provider.getId()), profile, 0, 2048, null);
    }

    private final AIProviderSelection none(List<String> considered, List<String> candidates, String reason, AiTaskProfile profile) {
        return new AIProviderSelection(null, AIRouterReasonCode.NONE_AVAILABLE, reason, false, considered, null, "NONE_AVAILABLE", candidates, null, null, profile, 0, 2856, null);
    }

    private final void logTrace(AiTaskProfile profile, AIProviderSelection sel, AICompletionResult result) {
        String id;
        try {
            Result.Companion companion = Result.INSTANCE;
            AIRouter aIRouter = this;
            String requestId = profile.getRequestId();
            if (StringsKt.isBlank(requestId)) {
                requestId = result.getRequestId();
            }
            AiTaskType taskType = profile.getTaskType();
            String routeHint = profile.getRouteHint();
            Object obj = "-";
            if (routeHint == null) {
                routeHint = "-";
            }
            AIProvider provider = sel.getProvider();
            if (provider == null || (id = provider.getId()) == null) {
                id = "-";
            }
            String modelId = sel.getModelId();
            if (modelId == null) {
                modelId = "-";
            }
            String selectionReason = sel.getSelectionReason();
            boolean fallbackUsed = sel.getFallbackUsed();
            String providerHealth = sel.getProviderHealth();
            if (providerHealth == null) {
                providerHealth = "-";
            }
            String strJoinToString$default = CollectionsKt.joinToString$default(sel.getCandidateProviders(), ",", null, null, 0, null, null, 62, null);
            long latencyMs = result.getLatencyMs();
            Long ttftMs = result.getTtftMs();
            if (ttftMs != null) {
                obj = ttftMs;
            }
            Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.AIRouter", "req=" + ((Object) requestId) + " task=" + taskType + " route=" + routeHint + " provider=" + id + " model=" + modelId + " reason=" + selectionReason + " fallback=" + fallbackUsed + " health=" + providerHealth + " candidates=" + strJoinToString$default + " latency=" + latencyMs + "ms ttft=" + obj + " streamReq=" + profile.getStreamingRequired() + " streamUsed=" + result.getStreamed() + " status=" + result.getStatus() + " attempt=" + sel.getAttempt())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }
}
