package com.jarvis.core.runtime.semantic;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SemanticModels.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\fHÆ\u0003Jm\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010#\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lcom/jarvis/core/runtime/semantic/SemanticInput;", "", "rawText", "", "normalizedText", "previousUserText", "previousAssistantText", "lastToolId", "lastToolArgs", "", "pendingPhase", "hasPendingAction", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Z)V", "getRawText", "()Ljava/lang/String;", "getNormalizedText", "getPreviousUserText", "getPreviousAssistantText", "getLastToolId", "getLastToolArgs", "()Ljava/util/Map;", "getPendingPhase", "getHasPendingAction", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SemanticInput {
    private final boolean hasPendingAction;
    private final Map<String, String> lastToolArgs;
    private final String lastToolId;
    private final String normalizedText;
    private final String pendingPhase;
    private final String previousAssistantText;
    private final String previousUserText;
    private final String rawText;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRawText() {
        return this.rawText;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNormalizedText() {
        return this.normalizedText;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPreviousUserText() {
        return this.previousUserText;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPreviousAssistantText() {
        return this.previousAssistantText;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLastToolId() {
        return this.lastToolId;
    }

    public final Map<String, String> component6() {
        return this.lastToolArgs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPendingPhase() {
        return this.pendingPhase;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getHasPendingAction() {
        return this.hasPendingAction;
    }

    public final SemanticInput copy(String rawText, String normalizedText, String previousUserText, String previousAssistantText, String lastToolId, Map<String, String> lastToolArgs, String pendingPhase, boolean hasPendingAction) {
        Intrinsics.checkNotNullParameter(rawText, "rawText");
        Intrinsics.checkNotNullParameter(normalizedText, "normalizedText");
        Intrinsics.checkNotNullParameter(lastToolArgs, "lastToolArgs");
        return new SemanticInput(rawText, normalizedText, previousUserText, previousAssistantText, lastToolId, lastToolArgs, pendingPhase, hasPendingAction);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SemanticInput)) {
            return false;
        }
        SemanticInput semanticInput = (SemanticInput) other;
        return Intrinsics.areEqual(this.rawText, semanticInput.rawText) && Intrinsics.areEqual(this.normalizedText, semanticInput.normalizedText) && Intrinsics.areEqual(this.previousUserText, semanticInput.previousUserText) && Intrinsics.areEqual(this.previousAssistantText, semanticInput.previousAssistantText) && Intrinsics.areEqual(this.lastToolId, semanticInput.lastToolId) && Intrinsics.areEqual(this.lastToolArgs, semanticInput.lastToolArgs) && Intrinsics.areEqual(this.pendingPhase, semanticInput.pendingPhase) && this.hasPendingAction == semanticInput.hasPendingAction;
    }

    public int hashCode() {
        return (((((((((((((this.rawText.hashCode() * 31) + this.normalizedText.hashCode()) * 31) + (this.previousUserText == null ? 0 : this.previousUserText.hashCode())) * 31) + (this.previousAssistantText == null ? 0 : this.previousAssistantText.hashCode())) * 31) + (this.lastToolId == null ? 0 : this.lastToolId.hashCode())) * 31) + this.lastToolArgs.hashCode()) * 31) + (this.pendingPhase != null ? this.pendingPhase.hashCode() : 0)) * 31) + Boolean.hashCode(this.hasPendingAction);
    }

    public String toString() {
        return "SemanticInput(rawText=" + this.rawText + ", normalizedText=" + this.normalizedText + ", previousUserText=" + this.previousUserText + ", previousAssistantText=" + this.previousAssistantText + ", lastToolId=" + this.lastToolId + ", lastToolArgs=" + this.lastToolArgs + ", pendingPhase=" + this.pendingPhase + ", hasPendingAction=" + this.hasPendingAction + ")";
    }

    public SemanticInput(String rawText, String normalizedText, String previousUserText, String previousAssistantText, String lastToolId, Map<String, String> lastToolArgs, String pendingPhase, boolean hasPendingAction) {
        Intrinsics.checkNotNullParameter(rawText, "rawText");
        Intrinsics.checkNotNullParameter(normalizedText, "normalizedText");
        Intrinsics.checkNotNullParameter(lastToolArgs, "lastToolArgs");
        this.rawText = rawText;
        this.normalizedText = normalizedText;
        this.previousUserText = previousUserText;
        this.previousAssistantText = previousAssistantText;
        this.lastToolId = lastToolId;
        this.lastToolArgs = lastToolArgs;
        this.pendingPhase = pendingPhase;
        this.hasPendingAction = hasPendingAction;
    }

    public /* synthetic */ SemanticInput(String str, String str2, String str3, String str4, String str5, Map map, String str6, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? MapsKt.emptyMap() : map, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? false : z);
    }

    public final String getRawText() {
        return this.rawText;
    }

    public final String getNormalizedText() {
        return this.normalizedText;
    }

    public final String getPreviousUserText() {
        return this.previousUserText;
    }

    public final String getPreviousAssistantText() {
        return this.previousAssistantText;
    }

    public final String getLastToolId() {
        return this.lastToolId;
    }

    public final Map<String, String> getLastToolArgs() {
        return this.lastToolArgs;
    }

    public final String getPendingPhase() {
        return this.pendingPhase;
    }

    public final boolean getHasPendingAction() {
        return this.hasPendingAction;
    }
}
