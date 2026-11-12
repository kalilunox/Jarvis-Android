package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AiTaskProfile.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\u000bHÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\t\u0010(\u001a\u00020\u000fHÆ\u0003Jc\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010*\u001a\u00020\u000b2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\tHÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006."}, d2 = {"Lcom/jarvis/brain/aiprovider/ModelDescriptor;", "", "providerId", "", "modelId", "qualityTier", "Lcom/jarvis/brain/aiprovider/QualityRequirement;", "speedTier", "contextLimit", "", "streaming", "", "structuredOutput", "onlineOnly", "privacyClass", "Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/QualityRequirement;Lcom/jarvis/brain/aiprovider/QualityRequirement;IZZZLcom/jarvis/brain/aiprovider/PrivacyLevel;)V", "getProviderId", "()Ljava/lang/String;", "getModelId", "getQualityTier", "()Lcom/jarvis/brain/aiprovider/QualityRequirement;", "getSpeedTier", "getContextLimit", "()I", "getStreaming", "()Z", "getStructuredOutput", "getOnlineOnly", "getPrivacyClass", "()Lcom/jarvis/brain/aiprovider/PrivacyLevel;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ModelDescriptor {
    private final int contextLimit;
    private final String modelId;
    private final boolean onlineOnly;
    private final PrivacyLevel privacyClass;
    private final String providerId;
    private final QualityRequirement qualityTier;
    private final QualityRequirement speedTier;
    private final boolean streaming;
    private final boolean structuredOutput;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final QualityRequirement getQualityTier() {
        return this.qualityTier;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final QualityRequirement getSpeedTier() {
        return this.speedTier;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getContextLimit() {
        return this.contextLimit;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getStreaming() {
        return this.streaming;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getStructuredOutput() {
        return this.structuredOutput;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getOnlineOnly() {
        return this.onlineOnly;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final PrivacyLevel getPrivacyClass() {
        return this.privacyClass;
    }

    public final ModelDescriptor copy(String providerId, String modelId, QualityRequirement qualityTier, QualityRequirement speedTier, int contextLimit, boolean streaming, boolean structuredOutput, boolean onlineOnly, PrivacyLevel privacyClass) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        Intrinsics.checkNotNullParameter(qualityTier, "qualityTier");
        Intrinsics.checkNotNullParameter(speedTier, "speedTier");
        Intrinsics.checkNotNullParameter(privacyClass, "privacyClass");
        return new ModelDescriptor(providerId, modelId, qualityTier, speedTier, contextLimit, streaming, structuredOutput, onlineOnly, privacyClass);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModelDescriptor)) {
            return false;
        }
        ModelDescriptor modelDescriptor = (ModelDescriptor) other;
        return Intrinsics.areEqual(this.providerId, modelDescriptor.providerId) && Intrinsics.areEqual(this.modelId, modelDescriptor.modelId) && this.qualityTier == modelDescriptor.qualityTier && this.speedTier == modelDescriptor.speedTier && this.contextLimit == modelDescriptor.contextLimit && this.streaming == modelDescriptor.streaming && this.structuredOutput == modelDescriptor.structuredOutput && this.onlineOnly == modelDescriptor.onlineOnly && this.privacyClass == modelDescriptor.privacyClass;
    }

    public int hashCode() {
        return (((((((((((((((this.providerId.hashCode() * 31) + this.modelId.hashCode()) * 31) + this.qualityTier.hashCode()) * 31) + this.speedTier.hashCode()) * 31) + Integer.hashCode(this.contextLimit)) * 31) + Boolean.hashCode(this.streaming)) * 31) + Boolean.hashCode(this.structuredOutput)) * 31) + Boolean.hashCode(this.onlineOnly)) * 31) + this.privacyClass.hashCode();
    }

    public String toString() {
        return "ModelDescriptor(providerId=" + this.providerId + ", modelId=" + this.modelId + ", qualityTier=" + this.qualityTier + ", speedTier=" + this.speedTier + ", contextLimit=" + this.contextLimit + ", streaming=" + this.streaming + ", structuredOutput=" + this.structuredOutput + ", onlineOnly=" + this.onlineOnly + ", privacyClass=" + this.privacyClass + ")";
    }

    public ModelDescriptor(String providerId, String modelId, QualityRequirement qualityTier, QualityRequirement speedTier, int contextLimit, boolean streaming, boolean structuredOutput, boolean onlineOnly, PrivacyLevel privacyClass) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        Intrinsics.checkNotNullParameter(qualityTier, "qualityTier");
        Intrinsics.checkNotNullParameter(speedTier, "speedTier");
        Intrinsics.checkNotNullParameter(privacyClass, "privacyClass");
        this.providerId = providerId;
        this.modelId = modelId;
        this.qualityTier = qualityTier;
        this.speedTier = speedTier;
        this.contextLimit = contextLimit;
        this.streaming = streaming;
        this.structuredOutput = structuredOutput;
        this.onlineOnly = onlineOnly;
        this.privacyClass = privacyClass;
    }

    public /* synthetic */ ModelDescriptor(String str, String str2, QualityRequirement qualityRequirement, QualityRequirement qualityRequirement2, int i, boolean z, boolean z2, boolean z3, PrivacyLevel privacyLevel, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? QualityRequirement.BALANCED : qualityRequirement, (i2 & 8) != 0 ? QualityRequirement.BALANCED : qualityRequirement2, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? true : z2, (i2 & 128) != 0 ? false : z3, (i2 & 256) != 0 ? PrivacyLevel.NORMAL : privacyLevel);
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final String getModelId() {
        return this.modelId;
    }

    public final QualityRequirement getQualityTier() {
        return this.qualityTier;
    }

    public final QualityRequirement getSpeedTier() {
        return this.speedTier;
    }

    public final int getContextLimit() {
        return this.contextLimit;
    }

    public final boolean getStreaming() {
        return this.streaming;
    }

    public final boolean getStructuredOutput() {
        return this.structuredOutput;
    }

    public final boolean getOnlineOnly() {
        return this.onlineOnly;
    }

    public final PrivacyLevel getPrivacyClass() {
        return this.privacyClass;
    }
}
