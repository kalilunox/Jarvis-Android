package com.jarvis.brain.aiprovider;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AiStreamModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010!\u001a\u00020\fHÆ\u0003J_\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006)"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIStreamEvent;", "", "type", "Lcom/jarvis/brain/aiprovider/AIStreamEventType;", VoiceService.EXTRA_TEXT, "", "requestId", "providerId", "modelId", "errorCode", "errorMessage", "atElapsedMs", "", "<init>", "(Lcom/jarvis/brain/aiprovider/AIStreamEventType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getType", "()Lcom/jarvis/brain/aiprovider/AIStreamEventType;", "getText", "()Ljava/lang/String;", "getRequestId", "getProviderId", "getModelId", "getErrorCode", "getErrorMessage", "getAtElapsedMs", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AIStreamEvent {
    private final long atElapsedMs;
    private final String errorCode;
    private final String errorMessage;
    private final String modelId;
    private final String providerId;
    private final String requestId;
    private final String text;
    private final AIStreamEventType type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AIStreamEventType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getModelId() {
        return this.modelId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getErrorCode() {
        return this.errorCode;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getAtElapsedMs() {
        return this.atElapsedMs;
    }

    public final AIStreamEvent copy(AIStreamEventType type, String text, String requestId, String providerId, String modelId, String errorCode, String errorMessage, long atElapsedMs) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        return new AIStreamEvent(type, text, requestId, providerId, modelId, errorCode, errorMessage, atElapsedMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIStreamEvent)) {
            return false;
        }
        AIStreamEvent aIStreamEvent = (AIStreamEvent) other;
        return this.type == aIStreamEvent.type && Intrinsics.areEqual(this.text, aIStreamEvent.text) && Intrinsics.areEqual(this.requestId, aIStreamEvent.requestId) && Intrinsics.areEqual(this.providerId, aIStreamEvent.providerId) && Intrinsics.areEqual(this.modelId, aIStreamEvent.modelId) && Intrinsics.areEqual(this.errorCode, aIStreamEvent.errorCode) && Intrinsics.areEqual(this.errorMessage, aIStreamEvent.errorMessage) && this.atElapsedMs == aIStreamEvent.atElapsedMs;
    }

    public int hashCode() {
        return (((((((((((((this.type.hashCode() * 31) + this.text.hashCode()) * 31) + this.requestId.hashCode()) * 31) + this.providerId.hashCode()) * 31) + (this.modelId == null ? 0 : this.modelId.hashCode())) * 31) + (this.errorCode == null ? 0 : this.errorCode.hashCode())) * 31) + (this.errorMessage != null ? this.errorMessage.hashCode() : 0)) * 31) + Long.hashCode(this.atElapsedMs);
    }

    public String toString() {
        return "AIStreamEvent(type=" + this.type + ", text=" + this.text + ", requestId=" + this.requestId + ", providerId=" + this.providerId + ", modelId=" + this.modelId + ", errorCode=" + this.errorCode + ", errorMessage=" + this.errorMessage + ", atElapsedMs=" + this.atElapsedMs + ")";
    }

    public AIStreamEvent(AIStreamEventType type, String text, String requestId, String providerId, String modelId, String errorCode, String errorMessage, long atElapsedMs) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        this.type = type;
        this.text = text;
        this.requestId = requestId;
        this.providerId = providerId;
        this.modelId = modelId;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.atElapsedMs = atElapsedMs;
    }

    public /* synthetic */ AIStreamEvent(AIStreamEventType aIStreamEventType, String str, String str2, String str3, String str4, String str5, String str6, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(aIStreamEventType, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? "" : str2, (i & 8) == 0 ? str3 : "", (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) == 0 ? str6 : null, (i & 128) != 0 ? 0L : j);
    }

    public final AIStreamEventType getType() {
        return this.type;
    }

    public final String getText() {
        return this.text;
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

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final long getAtElapsedMs() {
        return this.atElapsedMs;
    }
}
