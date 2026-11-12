package com.jarvis.brain.aiprovider;

import com.jarvis.core.llm.AssembledLlmContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AIProviderModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 22\u00020\u0001:\u00012Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003J\t\u0010(\u001a\u00020\nHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0010HÆ\u0003Jy\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001J\u0013\u0010-\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00063"}, d2 = {"Lcom/jarvis/brain/aiprovider/AICompletionRequest;", "", "requestId", "", "userText", "history", "", "memories", "persona", "wantStructured", "", "cancelRequested", "preferredModelId", "taskType", "Lcom/jarvis/brain/aiprovider/AiTaskType;", "assembledContext", "Lcom/jarvis/core/llm/AssembledLlmContext;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Lcom/jarvis/brain/aiprovider/AiTaskType;Lcom/jarvis/core/llm/AssembledLlmContext;)V", "getRequestId", "()Ljava/lang/String;", "getUserText", "getHistory", "()Ljava/util/List;", "getMemories", "getPersona", "getWantStructured", "()Z", "getCancelRequested", "getPreferredModelId", "getTaskType", "()Lcom/jarvis/brain/aiprovider/AiTaskType;", "getAssembledContext", "()Lcom/jarvis/core/llm/AssembledLlmContext;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AICompletionRequest {
    public static final int MAX_HISTORY = 32;
    public static final int MAX_MEMORIES = 2000;
    public static final int MAX_USER = 4000;
    private final AssembledLlmContext assembledContext;
    private final boolean cancelRequested;
    private final List<String> history;
    private final String memories;
    private final String persona;
    private final String preferredModelId;
    private final String requestId;
    private final AiTaskType taskType;
    private final String userText;
    private final boolean wantStructured;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final AssembledLlmContext getAssembledContext() {
        return this.assembledContext;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUserText() {
        return this.userText;
    }

    public final List<String> component3() {
        return this.history;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMemories() {
        return this.memories;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPersona() {
        return this.persona;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getWantStructured() {
        return this.wantStructured;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getCancelRequested() {
        return this.cancelRequested;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPreferredModelId() {
        return this.preferredModelId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final AiTaskType getTaskType() {
        return this.taskType;
    }

    public final AICompletionRequest copy(String requestId, String userText, List<String> history, String memories, String persona, boolean wantStructured, boolean cancelRequested, String preferredModelId, AiTaskType taskType, AssembledLlmContext assembledContext) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        return new AICompletionRequest(requestId, userText, history, memories, persona, wantStructured, cancelRequested, preferredModelId, taskType, assembledContext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AICompletionRequest)) {
            return false;
        }
        AICompletionRequest aICompletionRequest = (AICompletionRequest) other;
        return Intrinsics.areEqual(this.requestId, aICompletionRequest.requestId) && Intrinsics.areEqual(this.userText, aICompletionRequest.userText) && Intrinsics.areEqual(this.history, aICompletionRequest.history) && Intrinsics.areEqual(this.memories, aICompletionRequest.memories) && Intrinsics.areEqual(this.persona, aICompletionRequest.persona) && this.wantStructured == aICompletionRequest.wantStructured && this.cancelRequested == aICompletionRequest.cancelRequested && Intrinsics.areEqual(this.preferredModelId, aICompletionRequest.preferredModelId) && this.taskType == aICompletionRequest.taskType && Intrinsics.areEqual(this.assembledContext, aICompletionRequest.assembledContext);
    }

    public int hashCode() {
        return (((((((((((((((((this.requestId.hashCode() * 31) + this.userText.hashCode()) * 31) + this.history.hashCode()) * 31) + this.memories.hashCode()) * 31) + this.persona.hashCode()) * 31) + Boolean.hashCode(this.wantStructured)) * 31) + Boolean.hashCode(this.cancelRequested)) * 31) + (this.preferredModelId == null ? 0 : this.preferredModelId.hashCode())) * 31) + (this.taskType == null ? 0 : this.taskType.hashCode())) * 31) + (this.assembledContext != null ? this.assembledContext.hashCode() : 0);
    }

    public String toString() {
        return "AICompletionRequest(requestId=" + this.requestId + ", userText=" + this.userText + ", history=" + this.history + ", memories=" + this.memories + ", persona=" + this.persona + ", wantStructured=" + this.wantStructured + ", cancelRequested=" + this.cancelRequested + ", preferredModelId=" + this.preferredModelId + ", taskType=" + this.taskType + ", assembledContext=" + this.assembledContext + ")";
    }

    public AICompletionRequest(String requestId, String userText, List<String> history, String memories, String persona, boolean wantStructured, boolean cancelRequested, String preferredModelId, AiTaskType taskType, AssembledLlmContext assembledContext) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        this.requestId = requestId;
        this.userText = userText;
        this.history = history;
        this.memories = memories;
        this.persona = persona;
        this.wantStructured = wantStructured;
        this.cancelRequested = cancelRequested;
        this.preferredModelId = preferredModelId;
        this.taskType = taskType;
        this.assembledContext = assembledContext;
        if (StringsKt.isBlank(this.requestId)) {
            throw new IllegalArgumentException("requestId blank".toString());
        }
        if (!(this.userText.length() <= 4000)) {
            throw new IllegalArgumentException("userText too long".toString());
        }
        if (!(this.history.size() <= 32)) {
            throw new IllegalArgumentException("history too long".toString());
        }
        if (!(this.memories.length() <= 2000)) {
            throw new IllegalArgumentException("memories too long".toString());
        }
    }

    public /* synthetic */ AICompletionRequest(String str, String str2, List list, String str3, String str4, boolean z, boolean z2, String str5, AiTaskType aiTaskType, AssembledLlmContext assembledLlmContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? "" : str3, (i & 16) != 0 ? "CLASSIC" : str4, (i & 32) != 0 ? false : z, (i & 64) != 0 ? false : z2, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : aiTaskType, (i & 512) != 0 ? null : assembledLlmContext);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getUserText() {
        return this.userText;
    }

    public final List<String> getHistory() {
        return this.history;
    }

    public final String getMemories() {
        return this.memories;
    }

    public final String getPersona() {
        return this.persona;
    }

    public final boolean getWantStructured() {
        return this.wantStructured;
    }

    public final boolean getCancelRequested() {
        return this.cancelRequested;
    }

    public final String getPreferredModelId() {
        return this.preferredModelId;
    }

    public final AiTaskType getTaskType() {
        return this.taskType;
    }

    public final AssembledLlmContext getAssembledContext() {
        return this.assembledContext;
    }
}
