package com.jarvis.core.llm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LlmContextModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b,\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010*\u001a\u00020\u0005J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050\bHÆ\u0003J\t\u0010/\u001a\u00020\nHÆ\u0003J\t\u00100\u001a\u00020\nHÆ\u0003J\t\u00101\u001a\u00020\nHÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050\bHÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\t\u00105\u001a\u00020\nHÆ\u0003J\t\u00106\u001a\u00020\u0012HÆ\u0003J\t\u00107\u001a\u00020\u0012HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J¡\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0005HÆ\u0001J\u0013\u0010:\u001a\u00020\u00122\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020\nHÖ\u0001J\t\u0010=\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0013\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001a¨\u0006>"}, d2 = {"Lcom/jarvis/core/llm/ContextBuildDiagnostics;", "", "buildMs", "", "systemPromptVersion", "", "promptVersion", "contextSections", "", "contextChars", "", "estimatedInputTokens", "memoryCount", "memorySources", "historyTurns", "capabilityCount", "toolSchemaCount", "deviceContextIncluded", "", "currentAppIncluded", "systemPromptHash", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/util/List;IIILjava/util/List;IIIZZLjava/lang/String;)V", "getBuildMs", "()J", "getSystemPromptVersion", "()Ljava/lang/String;", "getPromptVersion", "getContextSections", "()Ljava/util/List;", "getContextChars", "()I", "getEstimatedInputTokens", "getMemoryCount", "getMemorySources", "getHistoryTurns", "getCapabilityCount", "getToolSchemaCount", "getDeviceContextIncluded", "()Z", "getCurrentAppIncluded", "getSystemPromptHash", "logLine", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ContextBuildDiagnostics {
    private final long buildMs;
    private final int capabilityCount;
    private final int contextChars;
    private final List<String> contextSections;
    private final boolean currentAppIncluded;
    private final boolean deviceContextIncluded;
    private final int estimatedInputTokens;
    private final int historyTurns;
    private final int memoryCount;
    private final List<String> memorySources;
    private final String promptVersion;
    private final String systemPromptHash;
    private final String systemPromptVersion;
    private final int toolSchemaCount;

    public ContextBuildDiagnostics() {
        this(0L, null, null, null, 0, 0, 0, null, 0, 0, 0, false, false, null, 16383, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getBuildMs() {
        return this.buildMs;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getCapabilityCount() {
        return this.capabilityCount;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getToolSchemaCount() {
        return this.toolSchemaCount;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getDeviceContextIncluded() {
        return this.deviceContextIncluded;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getCurrentAppIncluded() {
        return this.currentAppIncluded;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getSystemPromptHash() {
        return this.systemPromptHash;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSystemPromptVersion() {
        return this.systemPromptVersion;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPromptVersion() {
        return this.promptVersion;
    }

    public final List<String> component4() {
        return this.contextSections;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getContextChars() {
        return this.contextChars;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getEstimatedInputTokens() {
        return this.estimatedInputTokens;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getMemoryCount() {
        return this.memoryCount;
    }

    public final List<String> component8() {
        return this.memorySources;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getHistoryTurns() {
        return this.historyTurns;
    }

    public final ContextBuildDiagnostics copy(long buildMs, String systemPromptVersion, String promptVersion, List<String> contextSections, int contextChars, int estimatedInputTokens, int memoryCount, List<String> memorySources, int historyTurns, int capabilityCount, int toolSchemaCount, boolean deviceContextIncluded, boolean currentAppIncluded, String systemPromptHash) {
        Intrinsics.checkNotNullParameter(systemPromptVersion, "systemPromptVersion");
        Intrinsics.checkNotNullParameter(promptVersion, "promptVersion");
        Intrinsics.checkNotNullParameter(contextSections, "contextSections");
        Intrinsics.checkNotNullParameter(memorySources, "memorySources");
        Intrinsics.checkNotNullParameter(systemPromptHash, "systemPromptHash");
        return new ContextBuildDiagnostics(buildMs, systemPromptVersion, promptVersion, contextSections, contextChars, estimatedInputTokens, memoryCount, memorySources, historyTurns, capabilityCount, toolSchemaCount, deviceContextIncluded, currentAppIncluded, systemPromptHash);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextBuildDiagnostics)) {
            return false;
        }
        ContextBuildDiagnostics contextBuildDiagnostics = (ContextBuildDiagnostics) other;
        return this.buildMs == contextBuildDiagnostics.buildMs && Intrinsics.areEqual(this.systemPromptVersion, contextBuildDiagnostics.systemPromptVersion) && Intrinsics.areEqual(this.promptVersion, contextBuildDiagnostics.promptVersion) && Intrinsics.areEqual(this.contextSections, contextBuildDiagnostics.contextSections) && this.contextChars == contextBuildDiagnostics.contextChars && this.estimatedInputTokens == contextBuildDiagnostics.estimatedInputTokens && this.memoryCount == contextBuildDiagnostics.memoryCount && Intrinsics.areEqual(this.memorySources, contextBuildDiagnostics.memorySources) && this.historyTurns == contextBuildDiagnostics.historyTurns && this.capabilityCount == contextBuildDiagnostics.capabilityCount && this.toolSchemaCount == contextBuildDiagnostics.toolSchemaCount && this.deviceContextIncluded == contextBuildDiagnostics.deviceContextIncluded && this.currentAppIncluded == contextBuildDiagnostics.currentAppIncluded && Intrinsics.areEqual(this.systemPromptHash, contextBuildDiagnostics.systemPromptHash);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((Long.hashCode(this.buildMs) * 31) + this.systemPromptVersion.hashCode()) * 31) + this.promptVersion.hashCode()) * 31) + this.contextSections.hashCode()) * 31) + Integer.hashCode(this.contextChars)) * 31) + Integer.hashCode(this.estimatedInputTokens)) * 31) + Integer.hashCode(this.memoryCount)) * 31) + this.memorySources.hashCode()) * 31) + Integer.hashCode(this.historyTurns)) * 31) + Integer.hashCode(this.capabilityCount)) * 31) + Integer.hashCode(this.toolSchemaCount)) * 31) + Boolean.hashCode(this.deviceContextIncluded)) * 31) + Boolean.hashCode(this.currentAppIncluded)) * 31) + this.systemPromptHash.hashCode();
    }

    public String toString() {
        return "ContextBuildDiagnostics(buildMs=" + this.buildMs + ", systemPromptVersion=" + this.systemPromptVersion + ", promptVersion=" + this.promptVersion + ", contextSections=" + this.contextSections + ", contextChars=" + this.contextChars + ", estimatedInputTokens=" + this.estimatedInputTokens + ", memoryCount=" + this.memoryCount + ", memorySources=" + this.memorySources + ", historyTurns=" + this.historyTurns + ", capabilityCount=" + this.capabilityCount + ", toolSchemaCount=" + this.toolSchemaCount + ", deviceContextIncluded=" + this.deviceContextIncluded + ", currentAppIncluded=" + this.currentAppIncluded + ", systemPromptHash=" + this.systemPromptHash + ")";
    }

    public ContextBuildDiagnostics(long buildMs, String systemPromptVersion, String promptVersion, List<String> contextSections, int contextChars, int estimatedInputTokens, int memoryCount, List<String> memorySources, int historyTurns, int capabilityCount, int toolSchemaCount, boolean deviceContextIncluded, boolean currentAppIncluded, String systemPromptHash) {
        Intrinsics.checkNotNullParameter(systemPromptVersion, "systemPromptVersion");
        Intrinsics.checkNotNullParameter(promptVersion, "promptVersion");
        Intrinsics.checkNotNullParameter(contextSections, "contextSections");
        Intrinsics.checkNotNullParameter(memorySources, "memorySources");
        Intrinsics.checkNotNullParameter(systemPromptHash, "systemPromptHash");
        this.buildMs = buildMs;
        this.systemPromptVersion = systemPromptVersion;
        this.promptVersion = promptVersion;
        this.contextSections = contextSections;
        this.contextChars = contextChars;
        this.estimatedInputTokens = estimatedInputTokens;
        this.memoryCount = memoryCount;
        this.memorySources = memorySources;
        this.historyTurns = historyTurns;
        this.capabilityCount = capabilityCount;
        this.toolSchemaCount = toolSchemaCount;
        this.deviceContextIncluded = deviceContextIncluded;
        this.currentAppIncluded = currentAppIncluded;
        this.systemPromptHash = systemPromptHash;
    }

    public /* synthetic */ ContextBuildDiagnostics(long j, String str, String str2, List list, int i, int i2, int i3, List list2, int i4, int i5, int i6, boolean z, boolean z2, String str3, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0L : j, (i7 & 2) != 0 ? "" : str, (i7 & 4) != 0 ? "" : str2, (i7 & 8) != 0 ? CollectionsKt.emptyList() : list, (i7 & 16) != 0 ? 0 : i, (i7 & 32) != 0 ? 0 : i2, (i7 & 64) != 0 ? 0 : i3, (i7 & 128) != 0 ? CollectionsKt.emptyList() : list2, (i7 & 256) != 0 ? 0 : i4, (i7 & 512) != 0 ? 0 : i5, (i7 & 1024) != 0 ? 0 : i6, (i7 & 2048) != 0 ? false : z, (i7 & 4096) != 0 ? false : z2, (i7 & 8192) == 0 ? str3 : "");
    }

    public final long getBuildMs() {
        return this.buildMs;
    }

    public final String getSystemPromptVersion() {
        return this.systemPromptVersion;
    }

    public final String getPromptVersion() {
        return this.promptVersion;
    }

    public final List<String> getContextSections() {
        return this.contextSections;
    }

    public final int getContextChars() {
        return this.contextChars;
    }

    public final int getEstimatedInputTokens() {
        return this.estimatedInputTokens;
    }

    public final int getMemoryCount() {
        return this.memoryCount;
    }

    public final List<String> getMemorySources() {
        return this.memorySources;
    }

    public final int getHistoryTurns() {
        return this.historyTurns;
    }

    public final int getCapabilityCount() {
        return this.capabilityCount;
    }

    public final int getToolSchemaCount() {
        return this.toolSchemaCount;
    }

    public final boolean getDeviceContextIncluded() {
        return this.deviceContextIncluded;
    }

    public final boolean getCurrentAppIncluded() {
        return this.currentAppIncluded;
    }

    public final String getSystemPromptHash() {
        return this.systemPromptHash;
    }

    public final String logLine() {
        return "sections=" + CollectionsKt.joinToString$default(this.contextSections, ",", null, null, 0, null, null, 62, null) + " chars=" + this.contextChars + " tokEst=" + this.estimatedInputTokens + " mem=" + this.memoryCount + " src=" + CollectionsKt.joinToString$default(this.memorySources, "|", null, null, 0, null, null, 62, null) + " hist=" + this.historyTurns + " caps=" + this.capabilityCount + " device=" + this.deviceContextIncluded + " app=" + this.currentAppIncluded + " sys=" + this.systemPromptVersion + " buildMs=" + this.buildMs;
    }
}
