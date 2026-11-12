package com.jarvis.brain.aiprovider;

import com.jarvis.app.VoiceService;
import com.jarvis.core.runtime.TtftKind;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AIProviderModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b,\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00101\u001a\u00020\tHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u00103\u001a\u00020\rHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010%J\t\u00106\u001a\u00020\u0011HÆ\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010*J\u0010\u00108\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010*J\u0092\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÆ\u0001¢\u0006\u0002\u0010:J\u0013\u0010;\u001a\u00020\r2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0013HÖ\u0001J\t\u0010>\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\n\n\u0002\u0010+\u001a\u0004\b,\u0010*¨\u0006?"}, d2 = {"Lcom/jarvis/brain/aiprovider/AICompletionResult;", "", "requestId", "", "providerId", "status", "Lcom/jarvis/brain/aiprovider/AICompletionStatus;", VoiceService.EXTRA_TEXT, "latencyMs", "", "error", "Lcom/jarvis/brain/aiprovider/AIProviderError;", "streamed", "", "modelId", "ttftMs", "ttftKind", "Lcom/jarvis/core/runtime/TtftKind;", "inputTokens", "", "outputTokens", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/AICompletionStatus;Ljava/lang/String;JLcom/jarvis/brain/aiprovider/AIProviderError;ZLjava/lang/String;Ljava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getRequestId", "()Ljava/lang/String;", "getProviderId", "getStatus", "()Lcom/jarvis/brain/aiprovider/AICompletionStatus;", "getText", "getLatencyMs", "()J", "getError", "()Lcom/jarvis/brain/aiprovider/AIProviderError;", "getStreamed", "()Z", "getModelId", "getTtftMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTtftKind", "()Lcom/jarvis/core/runtime/TtftKind;", "getInputTokens", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOutputTokens", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/brain/aiprovider/AICompletionStatus;Ljava/lang/String;JLcom/jarvis/brain/aiprovider/AIProviderError;ZLjava/lang/String;Ljava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/jarvis/brain/aiprovider/AICompletionResult;", "equals", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AICompletionResult {
    private final AIProviderError error;
    private final Integer inputTokens;
    private final long latencyMs;
    private final String modelId;
    private final Integer outputTokens;
    private final String providerId;
    private final String requestId;
    private final AICompletionStatus status;
    private final boolean streamed;
    private final String text;
    private final TtftKind ttftKind;
    private final Long ttftMs;

    /* JADX INFO: compiled from: AIProviderModels.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AICompletionStatus.values().length];
            try {
                iArr[AICompletionStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AICompletionStatus.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AICompletionStatus.CANCELLED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Integer getInputTokens() {
        return this.inputTokens;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Integer getOutputTokens() {
        return this.outputTokens;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final AICompletionStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLatencyMs() {
        return this.latencyMs;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final AIProviderError getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getStreamed() {
        return this.streamed;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Long getTtftMs() {
        return this.ttftMs;
    }

    public final AICompletionResult copy(String requestId, String providerId, AICompletionStatus status, String text, long latencyMs, AIProviderError error, boolean streamed, String modelId, Long ttftMs, TtftKind ttftKind, Integer inputTokens, Integer outputTokens) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        return new AICompletionResult(requestId, providerId, status, text, latencyMs, error, streamed, modelId, ttftMs, ttftKind, inputTokens, outputTokens);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AICompletionResult)) {
            return false;
        }
        AICompletionResult aICompletionResult = (AICompletionResult) other;
        return Intrinsics.areEqual(this.requestId, aICompletionResult.requestId) && Intrinsics.areEqual(this.providerId, aICompletionResult.providerId) && this.status == aICompletionResult.status && Intrinsics.areEqual(this.text, aICompletionResult.text) && this.latencyMs == aICompletionResult.latencyMs && Intrinsics.areEqual(this.error, aICompletionResult.error) && this.streamed == aICompletionResult.streamed && Intrinsics.areEqual(this.modelId, aICompletionResult.modelId) && Intrinsics.areEqual(this.ttftMs, aICompletionResult.ttftMs) && this.ttftKind == aICompletionResult.ttftKind && Intrinsics.areEqual(this.inputTokens, aICompletionResult.inputTokens) && Intrinsics.areEqual(this.outputTokens, aICompletionResult.outputTokens);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.requestId.hashCode() * 31) + this.providerId.hashCode()) * 31) + this.status.hashCode()) * 31) + (this.text == null ? 0 : this.text.hashCode())) * 31) + Long.hashCode(this.latencyMs)) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31) + Boolean.hashCode(this.streamed)) * 31) + (this.modelId == null ? 0 : this.modelId.hashCode())) * 31) + (this.ttftMs == null ? 0 : this.ttftMs.hashCode())) * 31) + this.ttftKind.hashCode()) * 31) + (this.inputTokens == null ? 0 : this.inputTokens.hashCode())) * 31) + (this.outputTokens != null ? this.outputTokens.hashCode() : 0);
    }

    public String toString() {
        return "AICompletionResult(requestId=" + this.requestId + ", providerId=" + this.providerId + ", status=" + this.status + ", text=" + this.text + ", latencyMs=" + this.latencyMs + ", error=" + this.error + ", streamed=" + this.streamed + ", modelId=" + this.modelId + ", ttftMs=" + this.ttftMs + ", ttftKind=" + this.ttftKind + ", inputTokens=" + this.inputTokens + ", outputTokens=" + this.outputTokens + ")";
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a4  */
    public AICompletionResult(String requestId, String providerId, AICompletionStatus status, String text, long latencyMs, AIProviderError error, boolean streamed, String modelId, Long ttftMs, TtftKind ttftKind, Integer inputTokens, Integer outputTokens) {
        boolean z;
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        this.requestId = requestId;
        this.providerId = providerId;
        this.status = status;
        this.text = text;
        this.latencyMs = latencyMs;
        this.error = error;
        this.streamed = streamed;
        this.modelId = modelId;
        this.ttftMs = ttftMs;
        this.ttftKind = ttftKind;
        this.inputTokens = inputTokens;
        this.outputTokens = outputTokens;
        if (StringsKt.isBlank(this.requestId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(this.providerId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        switch (WhenMappings.$EnumSwitchMapping$0[this.status.ordinal()]) {
            case 1:
                if (this.error == null) {
                    String str = this.text;
                    z = str == null || StringsKt.isBlank(str) ? false : true;
                }
                if (z) {
                    return;
                } else {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            case 2:
            case 3:
                if (!(this.error != null)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AICompletionResult(String str, String str2, AICompletionStatus aICompletionStatus, String str3, long j, AIProviderError aIProviderError, boolean z, String str4, Long l, TtftKind ttftKind, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        TtftKind ttftKind2;
        String str5 = (i & 8) != 0 ? null : str3;
        long j2 = (i & 16) != 0 ? 0L : j;
        AIProviderError aIProviderError2 = (i & 32) != 0 ? null : aIProviderError;
        boolean z2 = (i & 64) != 0 ? false : z;
        String str6 = (i & 128) != 0 ? null : str4;
        Long l2 = (i & 256) != 0 ? null : l;
        if ((i & 512) != 0) {
            ttftKind2 = l2 != null ? TtftKind.REAL : TtftKind.NOT_AVAILABLE;
        } else {
            ttftKind2 = ttftKind;
        }
        this(str, str2, aICompletionStatus, str5, j2, aIProviderError2, z2, str6, l2, ttftKind2, (i & 1024) != 0 ? null : num, (i & 2048) != 0 ? null : num2);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final AICompletionStatus getStatus() {
        return this.status;
    }

    public final String getText() {
        return this.text;
    }

    public final long getLatencyMs() {
        return this.latencyMs;
    }

    public final AIProviderError getError() {
        return this.error;
    }

    public final boolean getStreamed() {
        return this.streamed;
    }

    public final String getModelId() {
        return this.modelId;
    }

    public final Long getTtftMs() {
        return this.ttftMs;
    }

    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    public final Integer getInputTokens() {
        return this.inputTokens;
    }

    public final Integer getOutputTokens() {
        return this.outputTokens;
    }
}
