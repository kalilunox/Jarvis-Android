package com.jarvis.brain.aiprovider;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.compress.archivers.zip.UnixStat;

/* JADX INFO: compiled from: AIProviderModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\r2\b\b\u0002\u0010+\u001a\u00020\rJ\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0007HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\t\u00104\u001a\u00020\rHÆ\u0003J\t\u00105\u001a\u00020\u0010HÆ\u0003J\t\u00106\u001a\u00020\u0010HÆ\u0003J\t\u00107\u001a\u00020\u0013HÆ\u0003J\u0087\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013HÆ\u0001J\u0013\u00109\u001a\u00020\u00032\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\u0007HÖ\u0001J\t\u0010<\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006="}, d2 = {"Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "", "supportsStreaming", "", "supportsCancellation", "supportsStructuredDecide", "maxContextTokens", "", "maxOutputTokens", "offlineCapable", "requiresNetwork", "notes", "", "", "defaultModelId", "qualityTier", "Lcom/jarvis/brain/aiprovider/QualityRequirement;", "speedTier", "privacyClass", "Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "<init>", "(ZZZIIZZLjava/util/List;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/QualityRequirement;Lcom/jarvis/brain/aiprovider/QualityRequirement;Lcom/jarvis/brain/aiprovider/PrivacyLevel;)V", "getSupportsStreaming", "()Z", "getSupportsCancellation", "getSupportsStructuredDecide", "getMaxContextTokens", "()I", "getMaxOutputTokens", "getOfflineCapable", "getRequiresNetwork", "getNotes", "()Ljava/util/List;", "getDefaultModelId", "()Ljava/lang/String;", "getQualityTier", "()Lcom/jarvis/brain/aiprovider/QualityRequirement;", "getSpeedTier", "getPrivacyClass", "()Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "toModelDescriptor", "Lcom/jarvis/brain/aiprovider/ModelDescriptor;", "providerId", "modelId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AIProviderCapabilities {
    private final String defaultModelId;
    private final int maxContextTokens;
    private final int maxOutputTokens;
    private final List<String> notes;
    private final boolean offlineCapable;
    private final PrivacyLevel privacyClass;
    private final QualityRequirement qualityTier;
    private final boolean requiresNetwork;
    private final QualityRequirement speedTier;
    private final boolean supportsCancellation;
    private final boolean supportsStreaming;
    private final boolean supportsStructuredDecide;

    public AIProviderCapabilities() {
        this(false, false, false, 0, 0, false, false, null, null, null, null, null, UnixStat.PERM_MASK, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getSupportsStreaming() {
        return this.supportsStreaming;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final QualityRequirement getQualityTier() {
        return this.qualityTier;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final QualityRequirement getSpeedTier() {
        return this.speedTier;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final PrivacyLevel getPrivacyClass() {
        return this.privacyClass;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getSupportsCancellation() {
        return this.supportsCancellation;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getSupportsStructuredDecide() {
        return this.supportsStructuredDecide;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getMaxContextTokens() {
        return this.maxContextTokens;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getMaxOutputTokens() {
        return this.maxOutputTokens;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getOfflineCapable() {
        return this.offlineCapable;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getRequiresNetwork() {
        return this.requiresNetwork;
    }

    public final List<String> component8() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getDefaultModelId() {
        return this.defaultModelId;
    }

    public final AIProviderCapabilities copy(boolean supportsStreaming, boolean supportsCancellation, boolean supportsStructuredDecide, int maxContextTokens, int maxOutputTokens, boolean offlineCapable, boolean requiresNetwork, List<String> notes, String defaultModelId, QualityRequirement qualityTier, QualityRequirement speedTier, PrivacyLevel privacyClass) {
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(defaultModelId, "defaultModelId");
        Intrinsics.checkNotNullParameter(qualityTier, "qualityTier");
        Intrinsics.checkNotNullParameter(speedTier, "speedTier");
        Intrinsics.checkNotNullParameter(privacyClass, "privacyClass");
        return new AIProviderCapabilities(supportsStreaming, supportsCancellation, supportsStructuredDecide, maxContextTokens, maxOutputTokens, offlineCapable, requiresNetwork, notes, defaultModelId, qualityTier, speedTier, privacyClass);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIProviderCapabilities)) {
            return false;
        }
        AIProviderCapabilities aIProviderCapabilities = (AIProviderCapabilities) other;
        return this.supportsStreaming == aIProviderCapabilities.supportsStreaming && this.supportsCancellation == aIProviderCapabilities.supportsCancellation && this.supportsStructuredDecide == aIProviderCapabilities.supportsStructuredDecide && this.maxContextTokens == aIProviderCapabilities.maxContextTokens && this.maxOutputTokens == aIProviderCapabilities.maxOutputTokens && this.offlineCapable == aIProviderCapabilities.offlineCapable && this.requiresNetwork == aIProviderCapabilities.requiresNetwork && Intrinsics.areEqual(this.notes, aIProviderCapabilities.notes) && Intrinsics.areEqual(this.defaultModelId, aIProviderCapabilities.defaultModelId) && this.qualityTier == aIProviderCapabilities.qualityTier && this.speedTier == aIProviderCapabilities.speedTier && this.privacyClass == aIProviderCapabilities.privacyClass;
    }

    public int hashCode() {
        return (((((((((((((((((((((Boolean.hashCode(this.supportsStreaming) * 31) + Boolean.hashCode(this.supportsCancellation)) * 31) + Boolean.hashCode(this.supportsStructuredDecide)) * 31) + Integer.hashCode(this.maxContextTokens)) * 31) + Integer.hashCode(this.maxOutputTokens)) * 31) + Boolean.hashCode(this.offlineCapable)) * 31) + Boolean.hashCode(this.requiresNetwork)) * 31) + this.notes.hashCode()) * 31) + this.defaultModelId.hashCode()) * 31) + this.qualityTier.hashCode()) * 31) + this.speedTier.hashCode()) * 31) + this.privacyClass.hashCode();
    }

    public String toString() {
        return "AIProviderCapabilities(supportsStreaming=" + this.supportsStreaming + ", supportsCancellation=" + this.supportsCancellation + ", supportsStructuredDecide=" + this.supportsStructuredDecide + ", maxContextTokens=" + this.maxContextTokens + ", maxOutputTokens=" + this.maxOutputTokens + ", offlineCapable=" + this.offlineCapable + ", requiresNetwork=" + this.requiresNetwork + ", notes=" + this.notes + ", defaultModelId=" + this.defaultModelId + ", qualityTier=" + this.qualityTier + ", speedTier=" + this.speedTier + ", privacyClass=" + this.privacyClass + ")";
    }

    public AIProviderCapabilities(boolean supportsStreaming, boolean supportsCancellation, boolean supportsStructuredDecide, int maxContextTokens, int maxOutputTokens, boolean offlineCapable, boolean requiresNetwork, List<String> notes, String defaultModelId, QualityRequirement qualityTier, QualityRequirement speedTier, PrivacyLevel privacyClass) {
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(defaultModelId, "defaultModelId");
        Intrinsics.checkNotNullParameter(qualityTier, "qualityTier");
        Intrinsics.checkNotNullParameter(speedTier, "speedTier");
        Intrinsics.checkNotNullParameter(privacyClass, "privacyClass");
        this.supportsStreaming = supportsStreaming;
        this.supportsCancellation = supportsCancellation;
        this.supportsStructuredDecide = supportsStructuredDecide;
        this.maxContextTokens = maxContextTokens;
        this.maxOutputTokens = maxOutputTokens;
        this.offlineCapable = offlineCapable;
        this.requiresNetwork = requiresNetwork;
        this.notes = notes;
        this.defaultModelId = defaultModelId;
        this.qualityTier = qualityTier;
        this.speedTier = speedTier;
        this.privacyClass = privacyClass;
    }

    public /* synthetic */ AIProviderCapabilities(boolean z, boolean z2, boolean z3, int i, int i2, boolean z4, boolean z5, List list, String str, QualityRequirement qualityRequirement, QualityRequirement qualityRequirement2, PrivacyLevel privacyLevel, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? false : z, (i3 & 2) != 0 ? false : z2, (i3 & 4) != 0 ? true : z3, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2, (i3 & 32) != 0 ? false : z4, (i3 & 64) != 0 ? false : z5, (i3 & 128) != 0 ? CollectionsKt.emptyList() : list, (i3 & 256) != 0 ? "" : str, (i3 & 512) != 0 ? QualityRequirement.BALANCED : qualityRequirement, (i3 & 1024) != 0 ? QualityRequirement.BALANCED : qualityRequirement2, (i3 & 2048) != 0 ? PrivacyLevel.NORMAL : privacyLevel);
    }

    public final boolean getSupportsStreaming() {
        return this.supportsStreaming;
    }

    public final boolean getSupportsCancellation() {
        return this.supportsCancellation;
    }

    public final boolean getSupportsStructuredDecide() {
        return this.supportsStructuredDecide;
    }

    public final int getMaxContextTokens() {
        return this.maxContextTokens;
    }

    public final int getMaxOutputTokens() {
        return this.maxOutputTokens;
    }

    public final boolean getOfflineCapable() {
        return this.offlineCapable;
    }

    public final boolean getRequiresNetwork() {
        return this.requiresNetwork;
    }

    public final List<String> getNotes() {
        return this.notes;
    }

    public final String getDefaultModelId() {
        return this.defaultModelId;
    }

    public final QualityRequirement getQualityTier() {
        return this.qualityTier;
    }

    public final QualityRequirement getSpeedTier() {
        return this.speedTier;
    }

    public final PrivacyLevel getPrivacyClass() {
        return this.privacyClass;
    }

    public static /* synthetic */ ModelDescriptor toModelDescriptor$default(AIProviderCapabilities aIProviderCapabilities, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = aIProviderCapabilities.defaultModelId;
        }
        return aIProviderCapabilities.toModelDescriptor(str, str2);
    }

    public final ModelDescriptor toModelDescriptor(String providerId, String modelId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        String str = modelId;
        if (StringsKt.isBlank(str)) {
            String str2 = this.defaultModelId;
            if (StringsKt.isBlank(str2)) {
                str2 = providerId;
            }
            str = str2;
        }
        return new ModelDescriptor(providerId, str, this.qualityTier, this.speedTier, this.maxContextTokens, this.supportsStreaming, this.supportsStructuredDecide, this.requiresNetwork, this.privacyClass);
    }
}
