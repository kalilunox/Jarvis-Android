package com.jarvis.core.llm;

import com.jarvis.core.common.model.PendingAction;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.companion.PhoneContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LlmContextModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BÓ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\n\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0\nHÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\f0\nHÆ\u0003J\u000f\u0010<\u001a\b\u0012\u0004\u0012\u00020\f0\nHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\t\u0010C\u001a\u00020\u0019HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J×\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010F\u001a\u00020\u00192\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020IHÖ\u0001J\t\u0010J\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010%R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b+\u0010%R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b-\u0010%R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001e¨\u0006K"}, d2 = {"Lcom/jarvis/core/llm/ContextBuildInput;", "", "userText", "", "kind", "Lcom/jarvis/core/llm/LlmContextKind;", "budget", "Lcom/jarvis/core/llm/LlmContextBudget;", "persona", "conversationLines", "", "longTermMemorySnippets", "Lcom/jarvis/core/llm/MemorySnippet;", "companionMemorySnippets", "memory2Snippets", "phoneContext", "Lcom/jarvis/core/companion/PhoneContext;", "capabilityLines", "taskGoal", "taskConstraints", "pendingAction", "Lcom/jarvis/core/common/model/PendingAction;", "lastToolCall", "Lcom/jarvis/core/common/model/ToolCall;", "cloudMemoryAllowed", "", "referenceHint", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/llm/LlmContextKind;Lcom/jarvis/core/llm/LlmContextBudget;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/jarvis/core/companion/PhoneContext;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Lcom/jarvis/core/common/model/PendingAction;Lcom/jarvis/core/common/model/ToolCall;ZLjava/lang/String;)V", "getUserText", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/llm/LlmContextKind;", "getBudget", "()Lcom/jarvis/core/llm/LlmContextBudget;", "getPersona", "getConversationLines", "()Ljava/util/List;", "getLongTermMemorySnippets", "getCompanionMemorySnippets", "getMemory2Snippets", "getPhoneContext", "()Lcom/jarvis/core/companion/PhoneContext;", "getCapabilityLines", "getTaskGoal", "getTaskConstraints", "getPendingAction", "()Lcom/jarvis/core/common/model/PendingAction;", "getLastToolCall", "()Lcom/jarvis/core/common/model/ToolCall;", "getCloudMemoryAllowed", "()Z", "getReferenceHint", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ContextBuildInput {
    private final LlmContextBudget budget;
    private final List<String> capabilityLines;
    private final boolean cloudMemoryAllowed;
    private final List<MemorySnippet> companionMemorySnippets;
    private final List<String> conversationLines;
    private final LlmContextKind kind;
    private final ToolCall lastToolCall;
    private final List<MemorySnippet> longTermMemorySnippets;
    private final List<MemorySnippet> memory2Snippets;
    private final PendingAction pendingAction;
    private final String persona;
    private final PhoneContext phoneContext;
    private final String referenceHint;
    private final List<String> taskConstraints;
    private final String taskGoal;
    private final String userText;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUserText() {
        return this.userText;
    }

    public final List<String> component10() {
        return this.capabilityLines;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTaskGoal() {
        return this.taskGoal;
    }

    public final List<String> component12() {
        return this.taskConstraints;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final PendingAction getPendingAction() {
        return this.pendingAction;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final ToolCall getLastToolCall() {
        return this.lastToolCall;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getCloudMemoryAllowed() {
        return this.cloudMemoryAllowed;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getReferenceHint() {
        return this.referenceHint;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LlmContextKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final LlmContextBudget getBudget() {
        return this.budget;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPersona() {
        return this.persona;
    }

    public final List<String> component5() {
        return this.conversationLines;
    }

    public final List<MemorySnippet> component6() {
        return this.longTermMemorySnippets;
    }

    public final List<MemorySnippet> component7() {
        return this.companionMemorySnippets;
    }

    public final List<MemorySnippet> component8() {
        return this.memory2Snippets;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final PhoneContext getPhoneContext() {
        return this.phoneContext;
    }

    public final ContextBuildInput copy(String userText, LlmContextKind kind, LlmContextBudget budget, String persona, List<String> conversationLines, List<MemorySnippet> longTermMemorySnippets, List<MemorySnippet> companionMemorySnippets, List<MemorySnippet> memory2Snippets, PhoneContext phoneContext, List<String> capabilityLines, String taskGoal, List<String> taskConstraints, PendingAction pendingAction, ToolCall lastToolCall, boolean cloudMemoryAllowed, String referenceHint) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(budget, "budget");
        Intrinsics.checkNotNullParameter(persona, "persona");
        Intrinsics.checkNotNullParameter(conversationLines, "conversationLines");
        Intrinsics.checkNotNullParameter(longTermMemorySnippets, "longTermMemorySnippets");
        Intrinsics.checkNotNullParameter(companionMemorySnippets, "companionMemorySnippets");
        Intrinsics.checkNotNullParameter(memory2Snippets, "memory2Snippets");
        Intrinsics.checkNotNullParameter(capabilityLines, "capabilityLines");
        Intrinsics.checkNotNullParameter(taskConstraints, "taskConstraints");
        return new ContextBuildInput(userText, kind, budget, persona, conversationLines, longTermMemorySnippets, companionMemorySnippets, memory2Snippets, phoneContext, capabilityLines, taskGoal, taskConstraints, pendingAction, lastToolCall, cloudMemoryAllowed, referenceHint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextBuildInput)) {
            return false;
        }
        ContextBuildInput contextBuildInput = (ContextBuildInput) other;
        return Intrinsics.areEqual(this.userText, contextBuildInput.userText) && this.kind == contextBuildInput.kind && this.budget == contextBuildInput.budget && Intrinsics.areEqual(this.persona, contextBuildInput.persona) && Intrinsics.areEqual(this.conversationLines, contextBuildInput.conversationLines) && Intrinsics.areEqual(this.longTermMemorySnippets, contextBuildInput.longTermMemorySnippets) && Intrinsics.areEqual(this.companionMemorySnippets, contextBuildInput.companionMemorySnippets) && Intrinsics.areEqual(this.memory2Snippets, contextBuildInput.memory2Snippets) && Intrinsics.areEqual(this.phoneContext, contextBuildInput.phoneContext) && Intrinsics.areEqual(this.capabilityLines, contextBuildInput.capabilityLines) && Intrinsics.areEqual(this.taskGoal, contextBuildInput.taskGoal) && Intrinsics.areEqual(this.taskConstraints, contextBuildInput.taskConstraints) && Intrinsics.areEqual(this.pendingAction, contextBuildInput.pendingAction) && Intrinsics.areEqual(this.lastToolCall, contextBuildInput.lastToolCall) && this.cloudMemoryAllowed == contextBuildInput.cloudMemoryAllowed && Intrinsics.areEqual(this.referenceHint, contextBuildInput.referenceHint);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.userText.hashCode() * 31) + this.kind.hashCode()) * 31) + this.budget.hashCode()) * 31) + this.persona.hashCode()) * 31) + this.conversationLines.hashCode()) * 31) + this.longTermMemorySnippets.hashCode()) * 31) + this.companionMemorySnippets.hashCode()) * 31) + this.memory2Snippets.hashCode()) * 31) + (this.phoneContext == null ? 0 : this.phoneContext.hashCode())) * 31) + this.capabilityLines.hashCode()) * 31) + (this.taskGoal == null ? 0 : this.taskGoal.hashCode())) * 31) + this.taskConstraints.hashCode()) * 31) + (this.pendingAction == null ? 0 : this.pendingAction.hashCode())) * 31) + (this.lastToolCall == null ? 0 : this.lastToolCall.hashCode())) * 31) + Boolean.hashCode(this.cloudMemoryAllowed)) * 31) + (this.referenceHint != null ? this.referenceHint.hashCode() : 0);
    }

    public String toString() {
        return "ContextBuildInput(userText=" + this.userText + ", kind=" + this.kind + ", budget=" + this.budget + ", persona=" + this.persona + ", conversationLines=" + this.conversationLines + ", longTermMemorySnippets=" + this.longTermMemorySnippets + ", companionMemorySnippets=" + this.companionMemorySnippets + ", memory2Snippets=" + this.memory2Snippets + ", phoneContext=" + this.phoneContext + ", capabilityLines=" + this.capabilityLines + ", taskGoal=" + this.taskGoal + ", taskConstraints=" + this.taskConstraints + ", pendingAction=" + this.pendingAction + ", lastToolCall=" + this.lastToolCall + ", cloudMemoryAllowed=" + this.cloudMemoryAllowed + ", referenceHint=" + this.referenceHint + ")";
    }

    public ContextBuildInput(String userText, LlmContextKind kind, LlmContextBudget budget, String persona, List<String> conversationLines, List<MemorySnippet> longTermMemorySnippets, List<MemorySnippet> companionMemorySnippets, List<MemorySnippet> memory2Snippets, PhoneContext phoneContext, List<String> capabilityLines, String taskGoal, List<String> taskConstraints, PendingAction pendingAction, ToolCall lastToolCall, boolean cloudMemoryAllowed, String referenceHint) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(budget, "budget");
        Intrinsics.checkNotNullParameter(persona, "persona");
        Intrinsics.checkNotNullParameter(conversationLines, "conversationLines");
        Intrinsics.checkNotNullParameter(longTermMemorySnippets, "longTermMemorySnippets");
        Intrinsics.checkNotNullParameter(companionMemorySnippets, "companionMemorySnippets");
        Intrinsics.checkNotNullParameter(memory2Snippets, "memory2Snippets");
        Intrinsics.checkNotNullParameter(capabilityLines, "capabilityLines");
        Intrinsics.checkNotNullParameter(taskConstraints, "taskConstraints");
        this.userText = userText;
        this.kind = kind;
        this.budget = budget;
        this.persona = persona;
        this.conversationLines = conversationLines;
        this.longTermMemorySnippets = longTermMemorySnippets;
        this.companionMemorySnippets = companionMemorySnippets;
        this.memory2Snippets = memory2Snippets;
        this.phoneContext = phoneContext;
        this.capabilityLines = capabilityLines;
        this.taskGoal = taskGoal;
        this.taskConstraints = taskConstraints;
        this.pendingAction = pendingAction;
        this.lastToolCall = lastToolCall;
        this.cloudMemoryAllowed = cloudMemoryAllowed;
        this.referenceHint = referenceHint;
    }

    public /* synthetic */ ContextBuildInput(String str, LlmContextKind llmContextKind, LlmContextBudget llmContextBudget, String str2, List list, List list2, List list3, List list4, PhoneContext phoneContext, List list5, String str3, List list6, PendingAction pendingAction, ToolCall toolCall, boolean z, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? LlmContextKind.CHAT : llmContextKind, (i & 4) != 0 ? LlmContextBudget.BALANCED : llmContextBudget, (i & 8) != 0 ? "CLASSIC" : str2, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, (i & 64) != 0 ? CollectionsKt.emptyList() : list3, (i & 128) != 0 ? CollectionsKt.emptyList() : list4, (i & 256) != 0 ? null : phoneContext, (i & 512) != 0 ? CollectionsKt.emptyList() : list5, (i & 1024) != 0 ? null : str3, (i & 2048) != 0 ? CollectionsKt.emptyList() : list6, (i & 4096) != 0 ? null : pendingAction, (i & 8192) != 0 ? null : toolCall, (i & 16384) != 0 ? true : z, (i & 32768) == 0 ? str4 : null);
    }

    public final String getUserText() {
        return this.userText;
    }

    public final LlmContextKind getKind() {
        return this.kind;
    }

    public final LlmContextBudget getBudget() {
        return this.budget;
    }

    public final String getPersona() {
        return this.persona;
    }

    public final List<String> getConversationLines() {
        return this.conversationLines;
    }

    public final List<MemorySnippet> getLongTermMemorySnippets() {
        return this.longTermMemorySnippets;
    }

    public final List<MemorySnippet> getCompanionMemorySnippets() {
        return this.companionMemorySnippets;
    }

    public final List<MemorySnippet> getMemory2Snippets() {
        return this.memory2Snippets;
    }

    public final PhoneContext getPhoneContext() {
        return this.phoneContext;
    }

    public final List<String> getCapabilityLines() {
        return this.capabilityLines;
    }

    public final String getTaskGoal() {
        return this.taskGoal;
    }

    public final List<String> getTaskConstraints() {
        return this.taskConstraints;
    }

    public final PendingAction getPendingAction() {
        return this.pendingAction;
    }

    public final ToolCall getLastToolCall() {
        return this.lastToolCall;
    }

    public final boolean getCloudMemoryAllowed() {
        return this.cloudMemoryAllowed;
    }

    public final String getReferenceHint() {
        return this.referenceHint;
    }
}
