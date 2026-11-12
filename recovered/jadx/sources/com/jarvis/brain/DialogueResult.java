package com.jarvis.brain;

import com.jarvis.app.VoiceService;
import com.jarvis.core.llm.ContextBuildDiagnostics;
import com.jarvis.core.runtime.TtftKind;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DialogueResult.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u00019B\u007f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u000bHÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010/\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u00100\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u008a\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u00103J\u0013\u00104\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u000207HÖ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\"\u0010 R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006:"}, d2 = {"Lcom/jarvis/brain/DialogueResult;", "", VoiceService.EXTRA_TEXT, "", "kind", "Lcom/jarvis/brain/DialogueResult$Kind;", "reason", "providerHint", "modelHint", "selectionReason", "fallbackUsed", "", "latencyMs", "", "ttftMs", "ttftKind", "Lcom/jarvis/core/runtime/TtftKind;", "contextDiagnostics", "Lcom/jarvis/core/llm/ContextBuildDiagnostics;", "<init>", "(Ljava/lang/String;Lcom/jarvis/brain/DialogueResult$Kind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Long;Ljava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;Lcom/jarvis/core/llm/ContextBuildDiagnostics;)V", "getText", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/brain/DialogueResult$Kind;", "getReason", "getProviderHint", "getModelHint", "getSelectionReason", "getFallbackUsed", "()Z", "getLatencyMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTtftMs", "getTtftKind", "()Lcom/jarvis/core/runtime/TtftKind;", "getContextDiagnostics", "()Lcom/jarvis/core/llm/ContextBuildDiagnostics;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Lcom/jarvis/brain/DialogueResult$Kind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Long;Ljava/lang/Long;Lcom/jarvis/core/runtime/TtftKind;Lcom/jarvis/core/llm/ContextBuildDiagnostics;)Lcom/jarvis/brain/DialogueResult;", "equals", "other", "hashCode", "", "toString", "Kind", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DialogueResult {
    private final ContextBuildDiagnostics contextDiagnostics;
    private final boolean fallbackUsed;
    private final Kind kind;
    private final Long latencyMs;
    private final String modelHint;
    private final String providerHint;
    private final String reason;
    private final String selectionReason;
    private final String text;
    private final TtftKind ttftKind;
    private final Long ttftMs;

    /* JADX INFO: compiled from: DialogueResult.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/jarvis/brain/DialogueResult$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "TEMPLATE", "LLM", "FALLBACK_TEMPLATE", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Kind {
        TEMPLATE,
        LLM,
        FALLBACK_TEMPLATE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final ContextBuildDiagnostics getContextDiagnostics() {
        return this.contextDiagnostics;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Kind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getProviderHint() {
        return this.providerHint;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getModelHint() {
        return this.modelHint;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSelectionReason() {
        return this.selectionReason;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Long getLatencyMs() {
        return this.latencyMs;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Long getTtftMs() {
        return this.ttftMs;
    }

    public final DialogueResult copy(String text, Kind kind, String reason, String providerHint, String modelHint, String selectionReason, boolean fallbackUsed, Long latencyMs, Long ttftMs, TtftKind ttftKind, ContextBuildDiagnostics contextDiagnostics) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new DialogueResult(text, kind, reason, providerHint, modelHint, selectionReason, fallbackUsed, latencyMs, ttftMs, ttftKind, contextDiagnostics);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DialogueResult)) {
            return false;
        }
        DialogueResult dialogueResult = (DialogueResult) other;
        return Intrinsics.areEqual(this.text, dialogueResult.text) && this.kind == dialogueResult.kind && Intrinsics.areEqual(this.reason, dialogueResult.reason) && Intrinsics.areEqual(this.providerHint, dialogueResult.providerHint) && Intrinsics.areEqual(this.modelHint, dialogueResult.modelHint) && Intrinsics.areEqual(this.selectionReason, dialogueResult.selectionReason) && this.fallbackUsed == dialogueResult.fallbackUsed && Intrinsics.areEqual(this.latencyMs, dialogueResult.latencyMs) && Intrinsics.areEqual(this.ttftMs, dialogueResult.ttftMs) && this.ttftKind == dialogueResult.ttftKind && Intrinsics.areEqual(this.contextDiagnostics, dialogueResult.contextDiagnostics);
    }

    public int hashCode() {
        return (((((((((((((((((((this.text.hashCode() * 31) + this.kind.hashCode()) * 31) + this.reason.hashCode()) * 31) + (this.providerHint == null ? 0 : this.providerHint.hashCode())) * 31) + (this.modelHint == null ? 0 : this.modelHint.hashCode())) * 31) + (this.selectionReason == null ? 0 : this.selectionReason.hashCode())) * 31) + Boolean.hashCode(this.fallbackUsed)) * 31) + (this.latencyMs == null ? 0 : this.latencyMs.hashCode())) * 31) + (this.ttftMs == null ? 0 : this.ttftMs.hashCode())) * 31) + (this.ttftKind == null ? 0 : this.ttftKind.hashCode())) * 31) + (this.contextDiagnostics != null ? this.contextDiagnostics.hashCode() : 0);
    }

    public String toString() {
        return "DialogueResult(text=" + this.text + ", kind=" + this.kind + ", reason=" + this.reason + ", providerHint=" + this.providerHint + ", modelHint=" + this.modelHint + ", selectionReason=" + this.selectionReason + ", fallbackUsed=" + this.fallbackUsed + ", latencyMs=" + this.latencyMs + ", ttftMs=" + this.ttftMs + ", ttftKind=" + this.ttftKind + ", contextDiagnostics=" + this.contextDiagnostics + ")";
    }

    public DialogueResult(String text, Kind kind, String reason, String providerHint, String modelHint, String selectionReason, boolean fallbackUsed, Long latencyMs, Long ttftMs, TtftKind ttftKind, ContextBuildDiagnostics contextDiagnostics) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.text = text;
        this.kind = kind;
        this.reason = reason;
        this.providerHint = providerHint;
        this.modelHint = modelHint;
        this.selectionReason = selectionReason;
        this.fallbackUsed = fallbackUsed;
        this.latencyMs = latencyMs;
        this.ttftMs = ttftMs;
        this.ttftKind = ttftKind;
        this.contextDiagnostics = contextDiagnostics;
    }

    public /* synthetic */ DialogueResult(String str, Kind kind, String str2, String str3, String str4, String str5, boolean z, Long l, Long l2, TtftKind ttftKind, ContextBuildDiagnostics contextBuildDiagnostics, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, kind, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? false : z, (i & 128) != 0 ? null : l, (i & 256) != 0 ? null : l2, (i & 512) != 0 ? null : ttftKind, (i & 1024) != 0 ? null : contextBuildDiagnostics);
    }

    public final String getText() {
        return this.text;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final String getReason() {
        return this.reason;
    }

    public final String getProviderHint() {
        return this.providerHint;
    }

    public final String getModelHint() {
        return this.modelHint;
    }

    public final String getSelectionReason() {
        return this.selectionReason;
    }

    public final boolean getFallbackUsed() {
        return this.fallbackUsed;
    }

    public final Long getLatencyMs() {
        return this.latencyMs;
    }

    public final Long getTtftMs() {
        return this.ttftMs;
    }

    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    public final ContextBuildDiagnostics getContextDiagnostics() {
        return this.contextDiagnostics;
    }
}
