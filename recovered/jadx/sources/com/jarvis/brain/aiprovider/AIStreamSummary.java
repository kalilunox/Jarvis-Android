package com.jarvis.brain.aiprovider;

import com.jarvis.core.runtime.TtftKind;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AiStreamModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b*\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0007HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\nHÆ\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010!J\t\u00103\u001a\u00020\rHÆ\u0003J\t\u00104\u001a\u00020\u000fHÆ\u0003J\t\u00105\u001a\u00020\u000fHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u00107\u001a\u00020\u0014HÆ\u0003J\u008c\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020\u000f2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020\u0014HÖ\u0001J\t\u0010=\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006>"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIStreamSummary;", "", "requestId", "", "providerId", "modelId", "status", "Lcom/jarvis/brain/aiprovider/AICompletionStatus;", "fullText", "latencyMs", "", "ttftMs", "ttftKind", "Lcom/jarvis/core/runtime/TtftKind;", "streamed", "", "cancelled", "error", "Lcom/jarvis/brain/aiprovider/AIProviderError;", "chunkCount", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/AICompletionStatus;Ljava/lang/String;JLjava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;ZZLcom/jarvis/brain/aiprovider/AIProviderError;I)V", "getRequestId", "()Ljava/lang/String;", "getProviderId", "getModelId", "getStatus", "()Lcom/jarvis/brain/aiprovider/AICompletionStatus;", "getFullText", "getLatencyMs", "()J", "getTtftMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTtftKind", "()Lcom/jarvis/core/runtime/TtftKind;", "getStreamed", "()Z", "getCancelled", "getError", "()Lcom/jarvis/brain/aiprovider/AIProviderError;", "getChunkCount", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/AICompletionStatus;Ljava/lang/String;JLjava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;ZZLcom/jarvis/brain/aiprovider/AIProviderError;I)Lcom/jarvis/brain/aiprovider/AIStreamSummary;", "equals", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AIStreamSummary {
    private final boolean cancelled;
    private final int chunkCount;
    private final AIProviderError error;
    private final String fullText;
    private final long latencyMs;
    private final String modelId;
    private final String providerId;
    private final String requestId;
    private final AICompletionStatus status;
    private final boolean streamed;
    private final TtftKind ttftKind;
    private final Long ttftMs;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getCancelled() {
        return this.cancelled;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final AIProviderError getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getChunkCount() {
        return this.chunkCount;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AICompletionStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFullText() {
        return this.fullText;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getLatencyMs() {
        return this.latencyMs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getTtftMs() {
        return this.ttftMs;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getStreamed() {
        return this.streamed;
    }

    public final AIStreamSummary copy(String requestId, String providerId, String modelId, AICompletionStatus status, String fullText, long latencyMs, Long ttftMs, TtftKind ttftKind, boolean streamed, boolean cancelled, AIProviderError error, int chunkCount) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(fullText, "fullText");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        return new AIStreamSummary(requestId, providerId, modelId, status, fullText, latencyMs, ttftMs, ttftKind, streamed, cancelled, error, chunkCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIStreamSummary)) {
            return false;
        }
        AIStreamSummary aIStreamSummary = (AIStreamSummary) other;
        return Intrinsics.areEqual(this.requestId, aIStreamSummary.requestId) && Intrinsics.areEqual(this.providerId, aIStreamSummary.providerId) && Intrinsics.areEqual(this.modelId, aIStreamSummary.modelId) && this.status == aIStreamSummary.status && Intrinsics.areEqual(this.fullText, aIStreamSummary.fullText) && this.latencyMs == aIStreamSummary.latencyMs && Intrinsics.areEqual(this.ttftMs, aIStreamSummary.ttftMs) && this.ttftKind == aIStreamSummary.ttftKind && this.streamed == aIStreamSummary.streamed && this.cancelled == aIStreamSummary.cancelled && Intrinsics.areEqual(this.error, aIStreamSummary.error) && this.chunkCount == aIStreamSummary.chunkCount;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.requestId.hashCode() * 31) + this.providerId.hashCode()) * 31) + (this.modelId == null ? 0 : this.modelId.hashCode())) * 31) + this.status.hashCode()) * 31) + this.fullText.hashCode()) * 31) + Long.hashCode(this.latencyMs)) * 31) + (this.ttftMs == null ? 0 : this.ttftMs.hashCode())) * 31) + this.ttftKind.hashCode()) * 31) + Boolean.hashCode(this.streamed)) * 31) + Boolean.hashCode(this.cancelled)) * 31) + (this.error != null ? this.error.hashCode() : 0)) * 31) + Integer.hashCode(this.chunkCount);
    }

    public String toString() {
        return "AIStreamSummary(requestId=" + this.requestId + ", providerId=" + this.providerId + ", modelId=" + this.modelId + ", status=" + this.status + ", fullText=" + this.fullText + ", latencyMs=" + this.latencyMs + ", ttftMs=" + this.ttftMs + ", ttftKind=" + this.ttftKind + ", streamed=" + this.streamed + ", cancelled=" + this.cancelled + ", error=" + this.error + ", chunkCount=" + this.chunkCount + ")";
    }

    public AIStreamSummary(String requestId, String providerId, String modelId, AICompletionStatus status, String fullText, long latencyMs, Long ttftMs, TtftKind ttftKind, boolean streamed, boolean cancelled, AIProviderError error, int chunkCount) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(fullText, "fullText");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        this.requestId = requestId;
        this.providerId = providerId;
        this.modelId = modelId;
        this.status = status;
        this.fullText = fullText;
        this.latencyMs = latencyMs;
        this.ttftMs = ttftMs;
        this.ttftKind = ttftKind;
        this.streamed = streamed;
        this.cancelled = cancelled;
        this.error = error;
        this.chunkCount = chunkCount;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AIStreamSummary(String str, String str2, String str3, AICompletionStatus aICompletionStatus, String str4, long j, Long l, TtftKind ttftKind, boolean z, boolean z2, AIProviderError aIProviderError, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        AIProviderError aIProviderError2;
        int i3;
        if ((i2 & 1024) == 0) {
            aIProviderError2 = aIProviderError;
        } else {
            aIProviderError2 = null;
        }
        if ((i2 & 2048) == 0) {
            i3 = i;
        } else {
            i3 = 0;
        }
        this(str, str2, str3, aICompletionStatus, str4, j, l, ttftKind, z, z2, aIProviderError2, i3);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final String getModelId() {
        return this.modelId;
    }

    public final AICompletionStatus getStatus() {
        return this.status;
    }

    public final String getFullText() {
        return this.fullText;
    }

    public final long getLatencyMs() {
        return this.latencyMs;
    }

    public final Long getTtftMs() {
        return this.ttftMs;
    }

    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    public final boolean getStreamed() {
        return this.streamed;
    }

    public final boolean getCancelled() {
        return this.cancelled;
    }

    public final AIProviderError getError() {
        return this.error;
    }

    public final int getChunkCount() {
        return this.chunkCount;
    }
}
