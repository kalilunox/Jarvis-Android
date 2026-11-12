package com.jarvis.brain.aiprovider;

import com.jarvis.core.runtime.TtftKind;
import com.jarvis.core.streaming.VoiceStreamState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChatStreamPipeline.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001dJ\t\u00101\u001a\u00020\tHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0005HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0012HÆ\u0003J\t\u0010:\u001a\u00020\u0007HÆ\u0003J\t\u0010;\u001a\u00020\u0015HÆ\u0003J¤\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0015HÆ\u0001¢\u0006\u0002\u0010=J\u0013\u0010>\u001a\u00020\u00052\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020\u0012HÖ\u0001J\t\u0010A\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u0006B"}, d2 = {"Lcom/jarvis/brain/aiprovider/ChatStreamResult;", "", "fullText", "", "streamed", "", "ttftMs", "", "ttftKind", "Lcom/jarvis/core/runtime/TtftKind;", "providerId", "modelId", "selectionReason", "fallbackUsed", "cancelled", "failed", "fallbackReason", "sentenceCount", "", "latencyMs", "voiceState", "Lcom/jarvis/core/streaming/VoiceStreamState;", "<init>", "(Ljava/lang/String;ZLjava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;IJLcom/jarvis/core/streaming/VoiceStreamState;)V", "getFullText", "()Ljava/lang/String;", "getStreamed", "()Z", "getTtftMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTtftKind", "()Lcom/jarvis/core/runtime/TtftKind;", "getProviderId", "getModelId", "getSelectionReason", "getFallbackUsed", "getCancelled", "getFailed", "getFallbackReason", "getSentenceCount", "()I", "getLatencyMs", "()J", "getVoiceState", "()Lcom/jarvis/core/streaming/VoiceStreamState;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;ZLjava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;IJLcom/jarvis/core/streaming/VoiceStreamState;)Lcom/jarvis/brain/aiprovider/ChatStreamResult;", "equals", "other", "hashCode", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ChatStreamResult {
    private final boolean cancelled;
    private final boolean failed;
    private final String fallbackReason;
    private final boolean fallbackUsed;
    private final String fullText;
    private final long latencyMs;
    private final String modelId;
    private final String providerId;
    private final String selectionReason;
    private final int sentenceCount;
    private final boolean streamed;
    private final TtftKind ttftKind;
    private final Long ttftMs;
    private final VoiceStreamState voiceState;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFullText() {
        return this.fullText;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getFailed() {
        return this.failed;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getFallbackReason() {
        return this.fallbackReason;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getSentenceCount() {
        return this.sentenceCount;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getLatencyMs() {
        return this.latencyMs;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final VoiceStreamState getVoiceState() {
        return this.voiceState;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getStreamed() {
        return this.streamed;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getTtftMs() {
        return this.ttftMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getSelectionReason() {
        return this.selectionReason;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getCancelled() {
        return this.cancelled;
    }

    public final ChatStreamResult copy(String fullText, boolean streamed, Long ttftMs, TtftKind ttftKind, String providerId, String modelId, String selectionReason, boolean fallbackUsed, boolean cancelled, boolean failed, String fallbackReason, int sentenceCount, long latencyMs, VoiceStreamState voiceState) {
        Intrinsics.checkNotNullParameter(fullText, "fullText");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        Intrinsics.checkNotNullParameter(voiceState, "voiceState");
        return new ChatStreamResult(fullText, streamed, ttftMs, ttftKind, providerId, modelId, selectionReason, fallbackUsed, cancelled, failed, fallbackReason, sentenceCount, latencyMs, voiceState);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChatStreamResult)) {
            return false;
        }
        ChatStreamResult chatStreamResult = (ChatStreamResult) other;
        return Intrinsics.areEqual(this.fullText, chatStreamResult.fullText) && this.streamed == chatStreamResult.streamed && Intrinsics.areEqual(this.ttftMs, chatStreamResult.ttftMs) && this.ttftKind == chatStreamResult.ttftKind && Intrinsics.areEqual(this.providerId, chatStreamResult.providerId) && Intrinsics.areEqual(this.modelId, chatStreamResult.modelId) && Intrinsics.areEqual(this.selectionReason, chatStreamResult.selectionReason) && this.fallbackUsed == chatStreamResult.fallbackUsed && this.cancelled == chatStreamResult.cancelled && this.failed == chatStreamResult.failed && Intrinsics.areEqual(this.fallbackReason, chatStreamResult.fallbackReason) && this.sentenceCount == chatStreamResult.sentenceCount && this.latencyMs == chatStreamResult.latencyMs && this.voiceState == chatStreamResult.voiceState;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.fullText.hashCode() * 31) + Boolean.hashCode(this.streamed)) * 31) + (this.ttftMs == null ? 0 : this.ttftMs.hashCode())) * 31) + this.ttftKind.hashCode()) * 31) + (this.providerId == null ? 0 : this.providerId.hashCode())) * 31) + (this.modelId == null ? 0 : this.modelId.hashCode())) * 31) + (this.selectionReason == null ? 0 : this.selectionReason.hashCode())) * 31) + Boolean.hashCode(this.fallbackUsed)) * 31) + Boolean.hashCode(this.cancelled)) * 31) + Boolean.hashCode(this.failed)) * 31) + (this.fallbackReason != null ? this.fallbackReason.hashCode() : 0)) * 31) + Integer.hashCode(this.sentenceCount)) * 31) + Long.hashCode(this.latencyMs)) * 31) + this.voiceState.hashCode();
    }

    public String toString() {
        return "ChatStreamResult(fullText=" + this.fullText + ", streamed=" + this.streamed + ", ttftMs=" + this.ttftMs + ", ttftKind=" + this.ttftKind + ", providerId=" + this.providerId + ", modelId=" + this.modelId + ", selectionReason=" + this.selectionReason + ", fallbackUsed=" + this.fallbackUsed + ", cancelled=" + this.cancelled + ", failed=" + this.failed + ", fallbackReason=" + this.fallbackReason + ", sentenceCount=" + this.sentenceCount + ", latencyMs=" + this.latencyMs + ", voiceState=" + this.voiceState + ")";
    }

    public ChatStreamResult(String fullText, boolean streamed, Long ttftMs, TtftKind ttftKind, String providerId, String modelId, String selectionReason, boolean fallbackUsed, boolean cancelled, boolean failed, String fallbackReason, int sentenceCount, long latencyMs, VoiceStreamState voiceState) {
        Intrinsics.checkNotNullParameter(fullText, "fullText");
        Intrinsics.checkNotNullParameter(ttftKind, "ttftKind");
        Intrinsics.checkNotNullParameter(voiceState, "voiceState");
        this.fullText = fullText;
        this.streamed = streamed;
        this.ttftMs = ttftMs;
        this.ttftKind = ttftKind;
        this.providerId = providerId;
        this.modelId = modelId;
        this.selectionReason = selectionReason;
        this.fallbackUsed = fallbackUsed;
        this.cancelled = cancelled;
        this.failed = failed;
        this.fallbackReason = fallbackReason;
        this.sentenceCount = sentenceCount;
        this.latencyMs = latencyMs;
        this.voiceState = voiceState;
    }

    public /* synthetic */ ChatStreamResult(String str, boolean z, Long l, TtftKind ttftKind, String str2, String str3, String str4, boolean z2, boolean z3, boolean z4, String str5, int i, long j, VoiceStreamState voiceStreamState, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, l, ttftKind, str2, str3, str4, z2, z3, z4, (i2 & 1024) != 0 ? null : str5, (i2 & 2048) != 0 ? 0 : i, (i2 & 4096) != 0 ? 0L : j, (i2 & 8192) != 0 ? VoiceStreamState.COMPLETED : voiceStreamState);
    }

    public final String getFullText() {
        return this.fullText;
    }

    public final boolean getStreamed() {
        return this.streamed;
    }

    public final Long getTtftMs() {
        return this.ttftMs;
    }

    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    public final String getProviderId() {
        return this.providerId;
    }

    public final String getModelId() {
        return this.modelId;
    }

    public final String getSelectionReason() {
        return this.selectionReason;
    }

    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    public final boolean getCancelled() {
        return this.cancelled;
    }

    public final boolean getFailed() {
        return this.failed;
    }

    public final String getFallbackReason() {
        return this.fallbackReason;
    }

    public final int getSentenceCount() {
        return this.sentenceCount;
    }

    public final long getLatencyMs() {
        return this.latencyMs;
    }

    public final VoiceStreamState getVoiceState() {
        return this.voiceState;
    }
}
