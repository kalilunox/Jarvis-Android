package com.jarvis.core.vision;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisionModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006#"}, d2 = {"Lcom/jarvis/core/vision/VisionResult;", "", "requestId", "", "providerId", "status", "Lcom/jarvis/core/vision/VisionStatus;", "description", "labels", "", "error", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/vision/VisionStatus;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getRequestId", "()Ljava/lang/String;", "getProviderId", "getStatus", "()Lcom/jarvis/core/vision/VisionStatus;", "getDescription", "getLabels", "()Ljava/util/List;", "getError", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VisionResult {
    private final String description;
    private final String error;
    private final List<String> labels;
    private final String providerId;
    private final String requestId;
    private final VisionStatus status;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VisionResult copy$default(VisionResult visionResult, String str, String str2, VisionStatus visionStatus, String str3, List list, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = visionResult.requestId;
        }
        if ((i & 2) != 0) {
            str2 = visionResult.providerId;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            visionStatus = visionResult.status;
        }
        VisionStatus visionStatus2 = visionStatus;
        if ((i & 8) != 0) {
            str3 = visionResult.description;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            list = visionResult.labels;
        }
        List list2 = list;
        if ((i & 32) != 0) {
            str4 = visionResult.error;
        }
        return visionResult.copy(str, str5, visionStatus2, str6, list2, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final VisionStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component5() {
        return this.labels;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final VisionResult copy(String requestId, String providerId, VisionStatus status, String description, List<String> labels, String error) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(labels, "labels");
        return new VisionResult(requestId, providerId, status, description, labels, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VisionResult)) {
            return false;
        }
        VisionResult visionResult = (VisionResult) other;
        return Intrinsics.areEqual(this.requestId, visionResult.requestId) && Intrinsics.areEqual(this.providerId, visionResult.providerId) && this.status == visionResult.status && Intrinsics.areEqual(this.description, visionResult.description) && Intrinsics.areEqual(this.labels, visionResult.labels) && Intrinsics.areEqual(this.error, visionResult.error);
    }

    public int hashCode() {
        return (((((((((this.requestId.hashCode() * 31) + this.providerId.hashCode()) * 31) + this.status.hashCode()) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + this.labels.hashCode()) * 31) + (this.error != null ? this.error.hashCode() : 0);
    }

    public String toString() {
        return "VisionResult(requestId=" + this.requestId + ", providerId=" + this.providerId + ", status=" + this.status + ", description=" + this.description + ", labels=" + this.labels + ", error=" + this.error + ")";
    }

    public VisionResult(String requestId, String providerId, VisionStatus status, String description, List<String> labels, String error) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(labels, "labels");
        this.requestId = requestId;
        this.providerId = providerId;
        this.status = status;
        this.description = description;
        this.labels = labels;
        this.error = error;
    }

    public /* synthetic */ VisionResult(String str, String str2, VisionStatus visionStatus, String str3, List list, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, visionStatus, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? null : str4);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final VisionStatus getStatus() {
        return this.status;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getLabels() {
        return this.labels;
    }

    public final String getError() {
        return this.error;
    }
}
