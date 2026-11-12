package com.jarvis.core.cloud;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChainEntry.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010$\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u001b\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t0\bHÆ\u0003JM\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t0\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR#\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/cloud/ChainEntry;", "", "providerId", "", "modelId", "label", "baseUrl", "paramCandidates", "", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getProviderId", "()Ljava/lang/String;", "getModelId", "getLabel", "getBaseUrl", "getParamCandidates", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ChainEntry {
    private final String baseUrl;
    private final String label;
    private final String modelId;
    private final List<Map<String, Object>> paramCandidates;
    private final String providerId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ChainEntry copy$default(ChainEntry chainEntry, String str, String str2, String str3, String str4, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = chainEntry.providerId;
        }
        if ((i & 2) != 0) {
            str2 = chainEntry.modelId;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = chainEntry.label;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = chainEntry.baseUrl;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            list = chainEntry.paramCandidates;
        }
        return chainEntry.copy(str, str5, str6, str7, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final List<Map<String, Object>> component5() {
        return this.paramCandidates;
    }

    public final ChainEntry copy(String providerId, String modelId, String label, String baseUrl, List<? extends Map<String, ? extends Object>> paramCandidates) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(paramCandidates, "paramCandidates");
        return new ChainEntry(providerId, modelId, label, baseUrl, paramCandidates);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChainEntry)) {
            return false;
        }
        ChainEntry chainEntry = (ChainEntry) other;
        return Intrinsics.areEqual(this.providerId, chainEntry.providerId) && Intrinsics.areEqual(this.modelId, chainEntry.modelId) && Intrinsics.areEqual(this.label, chainEntry.label) && Intrinsics.areEqual(this.baseUrl, chainEntry.baseUrl) && Intrinsics.areEqual(this.paramCandidates, chainEntry.paramCandidates);
    }

    public int hashCode() {
        return (((((((this.providerId.hashCode() * 31) + this.modelId.hashCode()) * 31) + this.label.hashCode()) * 31) + this.baseUrl.hashCode()) * 31) + this.paramCandidates.hashCode();
    }

    public String toString() {
        return "ChainEntry(providerId=" + this.providerId + ", modelId=" + this.modelId + ", label=" + this.label + ", baseUrl=" + this.baseUrl + ", paramCandidates=" + this.paramCandidates + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChainEntry(String providerId, String modelId, String label, String baseUrl, List<? extends Map<String, ? extends Object>> paramCandidates) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(paramCandidates, "paramCandidates");
        this.providerId = providerId;
        this.modelId = modelId;
        this.label = label;
        this.baseUrl = baseUrl;
        this.paramCandidates = paramCandidates;
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final String getModelId() {
        return this.modelId;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final List<Map<String, Object>> getParamCandidates() {
        return this.paramCandidates;
    }
}
