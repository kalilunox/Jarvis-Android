package com.jarvis.brain.aiprovider;

import com.google.android.gms.common.Scopes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AIRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b'\b\u0086\b\u0018\u00002\u00020\u0001B\u0093\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u00105\u001a\u00020\u0014HÆ\u0003J\u009b\u0001\u00106\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u00107\u001a\u00020\t2\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\u0014HÖ\u0001J\t\u0010:\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u0006;"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIProviderSelection;", "", "provider", "Lcom/jarvis/brain/aiprovider/AIProvider;", "reasonCode", "Lcom/jarvis/brain/aiprovider/AIRouterReasonCode;", "reason", "", "fallbackUsed", "", "considered", "", "modelId", "selectionReason", "candidateProviders", "candidateModels", "providerHealth", Scopes.PROFILE, "Lcom/jarvis/brain/aiprovider/AiTaskProfile;", "attempt", "", "<init>", "(Lcom/jarvis/brain/aiprovider/AIProvider;Lcom/jarvis/brain/aiprovider/AIRouterReasonCode;Ljava/lang/String;ZLjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/AiTaskProfile;I)V", "getProvider", "()Lcom/jarvis/brain/aiprovider/AIProvider;", "getReasonCode", "()Lcom/jarvis/brain/aiprovider/AIRouterReasonCode;", "getReason", "()Ljava/lang/String;", "getFallbackUsed", "()Z", "getConsidered", "()Ljava/util/List;", "getModelId", "getSelectionReason", "getCandidateProviders", "getCandidateModels", "getProviderHealth", "getProfile", "()Lcom/jarvis/brain/aiprovider/AiTaskProfile;", "getAttempt", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AIProviderSelection {
    private final int attempt;
    private final List<String> candidateModels;
    private final List<String> candidateProviders;
    private final List<String> considered;
    private final boolean fallbackUsed;
    private final String modelId;
    private final AiTaskProfile profile;
    private final AIProvider provider;
    private final String providerHealth;
    private final String reason;
    private final AIRouterReasonCode reasonCode;
    private final String selectionReason;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AIProvider getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getProviderHealth() {
        return this.providerHealth;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final AiTaskProfile getProfile() {
        return this.profile;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getAttempt() {
        return this.attempt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AIRouterReasonCode getReasonCode() {
        return this.reasonCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    public final List<String> component5() {
        return this.considered;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getSelectionReason() {
        return this.selectionReason;
    }

    public final List<String> component8() {
        return this.candidateProviders;
    }

    public final List<String> component9() {
        return this.candidateModels;
    }

    public final AIProviderSelection copy(AIProvider provider, AIRouterReasonCode reasonCode, String reason, boolean fallbackUsed, List<String> considered, String modelId, String selectionReason, List<String> candidateProviders, List<String> candidateModels, String providerHealth, AiTaskProfile profile, int attempt) {
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(considered, "considered");
        Intrinsics.checkNotNullParameter(selectionReason, "selectionReason");
        Intrinsics.checkNotNullParameter(candidateProviders, "candidateProviders");
        Intrinsics.checkNotNullParameter(candidateModels, "candidateModels");
        return new AIProviderSelection(provider, reasonCode, reason, fallbackUsed, considered, modelId, selectionReason, candidateProviders, candidateModels, providerHealth, profile, attempt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIProviderSelection)) {
            return false;
        }
        AIProviderSelection aIProviderSelection = (AIProviderSelection) other;
        return Intrinsics.areEqual(this.provider, aIProviderSelection.provider) && this.reasonCode == aIProviderSelection.reasonCode && Intrinsics.areEqual(this.reason, aIProviderSelection.reason) && this.fallbackUsed == aIProviderSelection.fallbackUsed && Intrinsics.areEqual(this.considered, aIProviderSelection.considered) && Intrinsics.areEqual(this.modelId, aIProviderSelection.modelId) && Intrinsics.areEqual(this.selectionReason, aIProviderSelection.selectionReason) && Intrinsics.areEqual(this.candidateProviders, aIProviderSelection.candidateProviders) && Intrinsics.areEqual(this.candidateModels, aIProviderSelection.candidateModels) && Intrinsics.areEqual(this.providerHealth, aIProviderSelection.providerHealth) && Intrinsics.areEqual(this.profile, aIProviderSelection.profile) && this.attempt == aIProviderSelection.attempt;
    }

    public int hashCode() {
        return ((((((((((((((((((((((this.provider == null ? 0 : this.provider.hashCode()) * 31) + this.reasonCode.hashCode()) * 31) + this.reason.hashCode()) * 31) + Boolean.hashCode(this.fallbackUsed)) * 31) + this.considered.hashCode()) * 31) + (this.modelId == null ? 0 : this.modelId.hashCode())) * 31) + this.selectionReason.hashCode()) * 31) + this.candidateProviders.hashCode()) * 31) + this.candidateModels.hashCode()) * 31) + (this.providerHealth == null ? 0 : this.providerHealth.hashCode())) * 31) + (this.profile != null ? this.profile.hashCode() : 0)) * 31) + Integer.hashCode(this.attempt);
    }

    public String toString() {
        return "AIProviderSelection(provider=" + this.provider + ", reasonCode=" + this.reasonCode + ", reason=" + this.reason + ", fallbackUsed=" + this.fallbackUsed + ", considered=" + this.considered + ", modelId=" + this.modelId + ", selectionReason=" + this.selectionReason + ", candidateProviders=" + this.candidateProviders + ", candidateModels=" + this.candidateModels + ", providerHealth=" + this.providerHealth + ", profile=" + this.profile + ", attempt=" + this.attempt + ")";
    }

    public AIProviderSelection(AIProvider provider, AIRouterReasonCode reasonCode, String reason, boolean fallbackUsed, List<String> considered, String modelId, String selectionReason, List<String> candidateProviders, List<String> candidateModels, String providerHealth, AiTaskProfile profile, int attempt) {
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(considered, "considered");
        Intrinsics.checkNotNullParameter(selectionReason, "selectionReason");
        Intrinsics.checkNotNullParameter(candidateProviders, "candidateProviders");
        Intrinsics.checkNotNullParameter(candidateModels, "candidateModels");
        this.provider = provider;
        this.reasonCode = reasonCode;
        this.reason = reason;
        this.fallbackUsed = fallbackUsed;
        this.considered = considered;
        this.modelId = modelId;
        this.selectionReason = selectionReason;
        this.candidateProviders = candidateProviders;
        this.candidateModels = candidateModels;
        this.providerHealth = providerHealth;
        this.profile = profile;
        this.attempt = attempt;
    }

    public /* synthetic */ AIProviderSelection(AIProvider aIProvider, AIRouterReasonCode aIRouterReasonCode, String str, boolean z, List list, String str2, String str3, List list2, List list3, String str4, AiTaskProfile aiTaskProfile, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(aIProvider, aIRouterReasonCode, str, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? CollectionsKt.emptyList() : list, (i2 & 32) != 0 ? null : str2, (i2 & 64) != 0 ? aIRouterReasonCode.name() : str3, (i2 & 128) != 0 ? CollectionsKt.emptyList() : list2, (i2 & 256) != 0 ? CollectionsKt.emptyList() : list3, (i2 & 512) != 0 ? null : str4, (i2 & 1024) != 0 ? null : aiTaskProfile, (i2 & 2048) != 0 ? 1 : i);
    }

    public final AIProvider getProvider() {
        return this.provider;
    }

    public final AIRouterReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final String getReason() {
        return this.reason;
    }

    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    public final List<String> getConsidered() {
        return this.considered;
    }

    public final String getModelId() {
        return this.modelId;
    }

    public final String getSelectionReason() {
        return this.selectionReason;
    }

    public final List<String> getCandidateProviders() {
        return this.candidateProviders;
    }

    public final List<String> getCandidateModels() {
        return this.candidateModels;
    }

    public final String getProviderHealth() {
        return this.providerHealth;
    }

    public final AiTaskProfile getProfile() {
        return this.profile;
    }

    public final int getAttempt() {
        return this.attempt;
    }
}
