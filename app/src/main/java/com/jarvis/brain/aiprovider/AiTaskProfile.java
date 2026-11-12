package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AiTaskProfile.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b6\b\u0086\b\u0018\u0000 G2\u00020\u0001:\u0001GB\u009f\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\b\b\u0002\u0010\u0016\u001a\u00020\t\u0012\b\b\u0002\u0010\u0017\u001a\u00020\t\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\tHÆ\u0003J\t\u00107\u001a\u00020\u000bHÆ\u0003J\t\u00108\u001a\u00020\rHÆ\u0003J\t\u00109\u001a\u00020\tHÆ\u0003J\t\u0010:\u001a\u00020\u0010HÆ\u0003J\t\u0010;\u001a\u00020\u0012HÆ\u0003J\t\u0010<\u001a\u00020\tHÆ\u0003J\t\u0010=\u001a\u00020\tHÆ\u0003J\t\u0010>\u001a\u00020\tHÆ\u0003J\t\u0010?\u001a\u00020\tHÆ\u0003J\t\u0010@\u001a\u00020\tHÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J¡\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\u0005HÆ\u0001J\u0013\u0010C\u001a\u00020\t2\b\u0010D\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010E\u001a\u00020\rHÖ\u0001J\t\u0010F\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0013\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0011\u0010\u0015\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\"R\u0011\u0010\u0017\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\"R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001eR\u0011\u00101\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b2\u0010\"¨\u0006H"}, d2 = {"Lcom/jarvis/brain/aiprovider/AiTaskProfile;", "", "taskType", "Lcom/jarvis/brain/aiprovider/AiTaskType;", "routeHint", "", "complexity", "Lcom/jarvis/brain/aiprovider/AIRequestComplexity;", "reasoningRequired", "", "privacyLevel", "Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "contextSizeTokens", "", "streamingRequired", "latencySensitivity", "Lcom/jarvis/brain/aiprovider/LatencySensitivity;", "qualityRequirement", "Lcom/jarvis/brain/aiprovider/QualityRequirement;", "offlineAllowed", "fallbackAllowed", "structuredOutputRequired", "preferCloud", "isOnline", "requestId", "<init>", "(Lcom/jarvis/brain/aiprovider/AiTaskType;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/AIRequestComplexity;ZLcom/jarvis/brain/aiprovider/PrivacyLevel;IZLcom/jarvis/brain/aiprovider/LatencySensitivity;Lcom/jarvis/brain/aiprovider/QualityRequirement;ZZZZZLjava/lang/String;)V", "getTaskType", "()Lcom/jarvis/brain/aiprovider/AiTaskType;", "getRouteHint", "()Ljava/lang/String;", "getComplexity", "()Lcom/jarvis/brain/aiprovider/AIRequestComplexity;", "getReasoningRequired", "()Z", "getPrivacyLevel", "()Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "getContextSizeTokens", "()I", "getStreamingRequired", "getLatencySensitivity", "()Lcom/jarvis/brain/aiprovider/LatencySensitivity;", "getQualityRequirement", "()Lcom/jarvis/brain/aiprovider/QualityRequirement;", "getOfflineAllowed", "getFallbackAllowed", "getStructuredOutputRequired", "getPreferCloud", "getRequestId", "privacyRestricted", "getPrivacyRestricted", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "other", "hashCode", "toString", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AiTaskProfile {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AIRequestComplexity complexity;
    private final int contextSizeTokens;
    private final boolean fallbackAllowed;
    private final boolean isOnline;
    private final LatencySensitivity latencySensitivity;
    private final boolean offlineAllowed;
    private final boolean preferCloud;
    private final PrivacyLevel privacyLevel;
    private final QualityRequirement qualityRequirement;
    private final boolean reasoningRequired;
    private final String requestId;
    private final String routeHint;
    private final boolean streamingRequired;
    private final boolean structuredOutputRequired;
    private final AiTaskType taskType;

    public AiTaskProfile() {
        this(null, null, null, false, null, 0, false, null, null, false, false, false, false, false, null, 32767, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AiTaskType getTaskType() {
        return this.taskType;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getOfflineAllowed() {
        return this.offlineAllowed;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getFallbackAllowed() {
        return this.fallbackAllowed;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getStructuredOutputRequired() {
        return this.structuredOutputRequired;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getPreferCloud() {
        return this.preferCloud;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRouteHint() {
        return this.routeHint;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final AIRequestComplexity getComplexity() {
        return this.complexity;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getReasoningRequired() {
        return this.reasoningRequired;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final PrivacyLevel getPrivacyLevel() {
        return this.privacyLevel;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getContextSizeTokens() {
        return this.contextSizeTokens;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getStreamingRequired() {
        return this.streamingRequired;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final LatencySensitivity getLatencySensitivity() {
        return this.latencySensitivity;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final QualityRequirement getQualityRequirement() {
        return this.qualityRequirement;
    }

    public final AiTaskProfile copy(AiTaskType taskType, String routeHint, AIRequestComplexity complexity, boolean reasoningRequired, PrivacyLevel privacyLevel, int contextSizeTokens, boolean streamingRequired, LatencySensitivity latencySensitivity, QualityRequirement qualityRequirement, boolean offlineAllowed, boolean fallbackAllowed, boolean structuredOutputRequired, boolean preferCloud, boolean isOnline, String requestId) {
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        Intrinsics.checkNotNullParameter(complexity, "complexity");
        Intrinsics.checkNotNullParameter(privacyLevel, "privacyLevel");
        Intrinsics.checkNotNullParameter(latencySensitivity, "latencySensitivity");
        Intrinsics.checkNotNullParameter(qualityRequirement, "qualityRequirement");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        return new AiTaskProfile(taskType, routeHint, complexity, reasoningRequired, privacyLevel, contextSizeTokens, streamingRequired, latencySensitivity, qualityRequirement, offlineAllowed, fallbackAllowed, structuredOutputRequired, preferCloud, isOnline, requestId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiTaskProfile)) {
            return false;
        }
        AiTaskProfile aiTaskProfile = (AiTaskProfile) other;
        return this.taskType == aiTaskProfile.taskType && Intrinsics.areEqual(this.routeHint, aiTaskProfile.routeHint) && this.complexity == aiTaskProfile.complexity && this.reasoningRequired == aiTaskProfile.reasoningRequired && this.privacyLevel == aiTaskProfile.privacyLevel && this.contextSizeTokens == aiTaskProfile.contextSizeTokens && this.streamingRequired == aiTaskProfile.streamingRequired && this.latencySensitivity == aiTaskProfile.latencySensitivity && this.qualityRequirement == aiTaskProfile.qualityRequirement && this.offlineAllowed == aiTaskProfile.offlineAllowed && this.fallbackAllowed == aiTaskProfile.fallbackAllowed && this.structuredOutputRequired == aiTaskProfile.structuredOutputRequired && this.preferCloud == aiTaskProfile.preferCloud && this.isOnline == aiTaskProfile.isOnline && Intrinsics.areEqual(this.requestId, aiTaskProfile.requestId);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((this.taskType.hashCode() * 31) + (this.routeHint == null ? 0 : this.routeHint.hashCode())) * 31) + this.complexity.hashCode()) * 31) + Boolean.hashCode(this.reasoningRequired)) * 31) + this.privacyLevel.hashCode()) * 31) + Integer.hashCode(this.contextSizeTokens)) * 31) + Boolean.hashCode(this.streamingRequired)) * 31) + this.latencySensitivity.hashCode()) * 31) + this.qualityRequirement.hashCode()) * 31) + Boolean.hashCode(this.offlineAllowed)) * 31) + Boolean.hashCode(this.fallbackAllowed)) * 31) + Boolean.hashCode(this.structuredOutputRequired)) * 31) + Boolean.hashCode(this.preferCloud)) * 31) + Boolean.hashCode(this.isOnline)) * 31) + this.requestId.hashCode();
    }

    public String toString() {
        return "AiTaskProfile(taskType=" + this.taskType + ", routeHint=" + this.routeHint + ", complexity=" + this.complexity + ", reasoningRequired=" + this.reasoningRequired + ", privacyLevel=" + this.privacyLevel + ", contextSizeTokens=" + this.contextSizeTokens + ", streamingRequired=" + this.streamingRequired + ", latencySensitivity=" + this.latencySensitivity + ", qualityRequirement=" + this.qualityRequirement + ", offlineAllowed=" + this.offlineAllowed + ", fallbackAllowed=" + this.fallbackAllowed + ", structuredOutputRequired=" + this.structuredOutputRequired + ", preferCloud=" + this.preferCloud + ", isOnline=" + this.isOnline + ", requestId=" + this.requestId + ")";
    }

    public AiTaskProfile(AiTaskType taskType, String routeHint, AIRequestComplexity complexity, boolean reasoningRequired, PrivacyLevel privacyLevel, int contextSizeTokens, boolean streamingRequired, LatencySensitivity latencySensitivity, QualityRequirement qualityRequirement, boolean offlineAllowed, boolean fallbackAllowed, boolean structuredOutputRequired, boolean preferCloud, boolean isOnline, String requestId) {
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        Intrinsics.checkNotNullParameter(complexity, "complexity");
        Intrinsics.checkNotNullParameter(privacyLevel, "privacyLevel");
        Intrinsics.checkNotNullParameter(latencySensitivity, "latencySensitivity");
        Intrinsics.checkNotNullParameter(qualityRequirement, "qualityRequirement");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.taskType = taskType;
        this.routeHint = routeHint;
        this.complexity = complexity;
        this.reasoningRequired = reasoningRequired;
        this.privacyLevel = privacyLevel;
        this.contextSizeTokens = contextSizeTokens;
        this.streamingRequired = streamingRequired;
        this.latencySensitivity = latencySensitivity;
        this.qualityRequirement = qualityRequirement;
        this.offlineAllowed = offlineAllowed;
        this.fallbackAllowed = fallbackAllowed;
        this.structuredOutputRequired = structuredOutputRequired;
        this.preferCloud = preferCloud;
        this.isOnline = isOnline;
        this.requestId = requestId;
    }

    public /* synthetic */ AiTaskProfile(AiTaskType aiTaskType, String str, AIRequestComplexity aIRequestComplexity, boolean z, PrivacyLevel privacyLevel, int i, boolean z2, LatencySensitivity latencySensitivity, QualityRequirement qualityRequirement, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? AiTaskType.CHAT : aiTaskType, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? AIRequestComplexity.SIMPLE : aIRequestComplexity, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? PrivacyLevel.NORMAL : privacyLevel, (i2 & 32) != 0 ? 0 : i, (i2 & 64) != 0 ? false : z2, (i2 & 128) != 0 ? LatencySensitivity.NORMAL : latencySensitivity, (i2 & 256) != 0 ? QualityRequirement.BALANCED : qualityRequirement, (i2 & 512) != 0 ? true : z3, (i2 & 1024) != 0 ? true : z4, (i2 & 2048) != 0 ? false : z5, (i2 & 4096) == 0 ? z6 : false, (i2 & 8192) == 0 ? z7 : true, (i2 & 16384) != 0 ? "" : str2);
    }

    public final AiTaskType getTaskType() {
        return this.taskType;
    }

    public final String getRouteHint() {
        return this.routeHint;
    }

    public final AIRequestComplexity getComplexity() {
        return this.complexity;
    }

    public final boolean getReasoningRequired() {
        return this.reasoningRequired;
    }

    public final PrivacyLevel getPrivacyLevel() {
        return this.privacyLevel;
    }

    public final int getContextSizeTokens() {
        return this.contextSizeTokens;
    }

    public final boolean getStreamingRequired() {
        return this.streamingRequired;
    }

    public final LatencySensitivity getLatencySensitivity() {
        return this.latencySensitivity;
    }

    public final QualityRequirement getQualityRequirement() {
        return this.qualityRequirement;
    }

    public final boolean getOfflineAllowed() {
        return this.offlineAllowed;
    }

    public final boolean getFallbackAllowed() {
        return this.fallbackAllowed;
    }

    public final boolean getStructuredOutputRequired() {
        return this.structuredOutputRequired;
    }

    public final boolean getPreferCloud() {
        return this.preferCloud;
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final boolean getPrivacyRestricted() {
        return this.privacyLevel == PrivacyLevel.SENSITIVE || this.privacyLevel == PrivacyLevel.LOCAL_REQUIRED;
    }

    /* JADX INFO: compiled from: AiTaskProfile.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tJ<\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u0013J\"\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\b\u001a\u00020\tJ \u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\b\u001a\u00020\t¨\u0006\u0016"}, d2 = {"Lcom/jarvis/brain/aiprovider/AiTaskProfile$Companion;", "", "<init>", "()V", "fromLegacy", "Lcom/jarvis/brain/aiprovider/AiTaskProfile;", "context", "Lcom/jarvis/brain/aiprovider/AIRouterContext;", "requestId", "", "forChat", "isOnline", "", "preferCloud", "complexity", "Lcom/jarvis/brain/aiprovider/AIRequestComplexity;", "privacy", "Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "userTextLen", "", "forSemanticInterpretation", "forPlanning", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: AiTaskProfile.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AIRequestComplexity.values().length];
                try {
                    iArr[AIRequestComplexity.COMPLEX.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[AIRequestComplexity.SIMPLE.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ AiTaskProfile fromLegacy$default(Companion companion, AIRouterContext aIRouterContext, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = "";
            }
            return companion.fromLegacy(aIRouterContext, str);
        }

        public final AiTaskProfile fromLegacy(AIRouterContext context, String requestId) {
            AiTaskType aiTaskType;
            QualityRequirement qualityRequirement;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            switch (WhenMappings.$EnumSwitchMapping$0[context.getComplexity().ordinal()]) {
                case 1:
                    aiTaskType = AiTaskType.CHAT;
                    break;
                case 2:
                    aiTaskType = AiTaskType.CHAT;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            AIRequestComplexity complexity = context.getComplexity();
            boolean z = context.getComplexity() == AIRequestComplexity.COMPLEX;
            PrivacyLevel privacyLevel = context.getPrivacyRestricted() ? PrivacyLevel.LOCAL_REQUIRED : PrivacyLevel.NORMAL;
            switch (WhenMappings.$EnumSwitchMapping$0[context.getComplexity().ordinal()]) {
                case 1:
                    qualityRequirement = QualityRequirement.QUALITY;
                    break;
                case 2:
                    qualityRequirement = QualityRequirement.BALANCED;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return new AiTaskProfile(aiTaskType, null, complexity, z, privacyLevel, 0, false, null, qualityRequirement, false, false, false, context.getPreferCloudWhenOnline(), context.isOnline(), requestId, 3810, null);
        }

        /* JADX WARN: Code duplicated, block: B:35:0x006e  */
        public final AiTaskProfile forChat(boolean isOnline, boolean preferCloud, AIRequestComplexity complexity, PrivacyLevel privacy, String requestId, int userTextLen) {
            LatencySensitivity latencySensitivity;
            QualityRequirement qualityRequirement;
            Intrinsics.checkNotNullParameter(complexity, "complexity");
            Intrinsics.checkNotNullParameter(privacy, "privacy");
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            boolean reasoning = complexity == AIRequestComplexity.COMPLEX || userTextLen > 180;
            AiTaskType aiTaskType = AiTaskType.CHAT;
            AIRequestComplexity aIRequestComplexity = reasoning ? AIRequestComplexity.COMPLEX : complexity;
            int iCoerceAtLeast = RangesKt.coerceAtLeast(userTextLen / 3, 32);
            if (preferCloud) {
                latencySensitivity = LatencySensitivity.NORMAL;
            } else {
                latencySensitivity = 1 <= userTextLen && userTextLen < 40 ? LatencySensitivity.HIGH : LatencySensitivity.NORMAL;
            }
            if (reasoning) {
                qualityRequirement = QualityRequirement.QUALITY;
            } else if (!preferCloud && complexity == AIRequestComplexity.SIMPLE) {
                if (1 <= userTextLen && userTextLen < 40) {
                    qualityRequirement = QualityRequirement.FAST;
                } else {
                    qualityRequirement = QualityRequirement.BALANCED;
                }
            } else {
                qualityRequirement = QualityRequirement.BALANCED;
            }
            return new AiTaskProfile(aiTaskType, "CHAT", aIRequestComplexity, reasoning, privacy, iCoerceAtLeast, preferCloud && isOnline && privacy != PrivacyLevel.LOCAL_REQUIRED && privacy != PrivacyLevel.SENSITIVE, latencySensitivity, qualityRequirement, false, false, false, preferCloud, isOnline, requestId, 3584, null);
        }

        public static /* synthetic */ AiTaskProfile forSemanticInterpretation$default(Companion companion, boolean z, boolean z2, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                z2 = false;
            }
            if ((i & 4) != 0) {
                str = "";
            }
            return companion.forSemanticInterpretation(z, z2, str);
        }

        public final AiTaskProfile forSemanticInterpretation(boolean isOnline, boolean preferCloud, String requestId) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            return new AiTaskProfile(AiTaskType.SEMANTIC_INTERPRETATION, null, AIRequestComplexity.SIMPLE, false, PrivacyLevel.NORMAL, 0, false, LatencySensitivity.HIGH, QualityRequirement.FAST, false, true, true, preferCloud, isOnline, requestId, 544, null);
        }

        public static /* synthetic */ AiTaskProfile forPlanning$default(Companion companion, boolean z, boolean z2, String str, int i, Object obj) {
            if ((i & 4) != 0) {
                str = "";
            }
            return companion.forPlanning(z, z2, str);
        }

        public final AiTaskProfile forPlanning(boolean isOnline, boolean preferCloud, String requestId) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            return new AiTaskProfile(AiTaskType.PLANNING, "PLAN", AIRequestComplexity.COMPLEX, true, null, 0, false, LatencySensitivity.LOW, QualityRequirement.REASONING, false, false, false, preferCloud, isOnline, requestId, 3696, null);
        }
    }
}
