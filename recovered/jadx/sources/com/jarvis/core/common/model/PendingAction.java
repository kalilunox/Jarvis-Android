package com.jarvis.core.common.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConversationContext.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002#$B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003JC\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011¨\u0006%"}, d2 = {"Lcom/jarvis/core/common/model/PendingAction;", "", "toolCall", "Lcom/jarvis/core/common/model/ToolCall;", "prompt", "", TypedValues.CycleType.S_WAVE_PHASE, "Lcom/jarvis/core/common/model/PendingAction$Phase;", "choices", "", "Lcom/jarvis/core/common/model/PendingAction$Choice;", "integrityFingerprint", "<init>", "(Lcom/jarvis/core/common/model/ToolCall;Ljava/lang/String;Lcom/jarvis/core/common/model/PendingAction$Phase;Ljava/util/List;Ljava/lang/String;)V", "getToolCall", "()Lcom/jarvis/core/common/model/ToolCall;", "getPrompt", "()Ljava/lang/String;", "getPhase", "()Lcom/jarvis/core/common/model/PendingAction$Phase;", "getChoices", "()Ljava/util/List;", "getIntegrityFingerprint", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Phase", "Choice", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PendingAction {
    private final List<Choice> choices;
    private final String integrityFingerprint;
    private final Phase phase;
    private final String prompt;
    private final ToolCall toolCall;

    /* JADX INFO: compiled from: ConversationContext.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/common/model/PendingAction$Phase;", "", "<init>", "(Ljava/lang/String;I)V", "CONFIRM", "DISAMBIGUATE", "COLLECT_CHAT", "COLLECT_TEXT", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Phase {
        CONFIRM,
        DISAMBIGUATE,
        COLLECT_CHAT,
        COLLECT_TEXT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Phase> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PendingAction copy$default(PendingAction pendingAction, ToolCall toolCall, String str, Phase phase, List list, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            toolCall = pendingAction.toolCall;
        }
        if ((i & 2) != 0) {
            str = pendingAction.prompt;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            phase = pendingAction.phase;
        }
        Phase phase2 = phase;
        if ((i & 8) != 0) {
            list = pendingAction.choices;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            str2 = pendingAction.integrityFingerprint;
        }
        return pendingAction.copy(toolCall, str3, phase2, list2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ToolCall getToolCall() {
        return this.toolCall;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPrompt() {
        return this.prompt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Phase getPhase() {
        return this.phase;
    }

    public final List<Choice> component4() {
        return this.choices;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIntegrityFingerprint() {
        return this.integrityFingerprint;
    }

    public final PendingAction copy(ToolCall toolCall, String prompt, Phase phase, List<Choice> choices, String integrityFingerprint) {
        Intrinsics.checkNotNullParameter(toolCall, "toolCall");
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(choices, "choices");
        return new PendingAction(toolCall, prompt, phase, choices, integrityFingerprint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PendingAction)) {
            return false;
        }
        PendingAction pendingAction = (PendingAction) other;
        return Intrinsics.areEqual(this.toolCall, pendingAction.toolCall) && Intrinsics.areEqual(this.prompt, pendingAction.prompt) && this.phase == pendingAction.phase && Intrinsics.areEqual(this.choices, pendingAction.choices) && Intrinsics.areEqual(this.integrityFingerprint, pendingAction.integrityFingerprint);
    }

    public int hashCode() {
        return (((((((this.toolCall.hashCode() * 31) + this.prompt.hashCode()) * 31) + this.phase.hashCode()) * 31) + this.choices.hashCode()) * 31) + (this.integrityFingerprint == null ? 0 : this.integrityFingerprint.hashCode());
    }

    public String toString() {
        return "PendingAction(toolCall=" + this.toolCall + ", prompt=" + this.prompt + ", phase=" + this.phase + ", choices=" + this.choices + ", integrityFingerprint=" + this.integrityFingerprint + ")";
    }

    public PendingAction(ToolCall toolCall, String prompt, Phase phase, List<Choice> choices, String integrityFingerprint) {
        Intrinsics.checkNotNullParameter(toolCall, "toolCall");
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(choices, "choices");
        this.toolCall = toolCall;
        this.prompt = prompt;
        this.phase = phase;
        this.choices = choices;
        this.integrityFingerprint = integrityFingerprint;
    }

    public /* synthetic */ PendingAction(ToolCall toolCall, String str, Phase phase, List list, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(toolCall, str, (i & 4) != 0 ? Phase.CONFIRM : phase, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? null : str2);
    }

    public final ToolCall getToolCall() {
        return this.toolCall;
    }

    public final String getPrompt() {
        return this.prompt;
    }

    public final Phase getPhase() {
        return this.phase;
    }

    public final List<Choice> getChoices() {
        return this.choices;
    }

    public final String getIntegrityFingerprint() {
        return this.integrityFingerprint;
    }

    /* JADX INFO: compiled from: ConversationContext.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/common/model/PendingAction$Choice;", "", "label", "", "value", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "getValue", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Choice {
        private final String label;
        private final String value;

        public static /* synthetic */ Choice copy$default(Choice choice, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = choice.label;
            }
            if ((i & 2) != 0) {
                str2 = choice.value;
            }
            return choice.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        public final Choice copy(String label, String value) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(value, "value");
            return new Choice(label, value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Choice)) {
                return false;
            }
            Choice choice = (Choice) other;
            return Intrinsics.areEqual(this.label, choice.label) && Intrinsics.areEqual(this.value, choice.value);
        }

        public int hashCode() {
            return (this.label.hashCode() * 31) + this.value.hashCode();
        }

        public String toString() {
            return "Choice(label=" + this.label + ", value=" + this.value + ")";
        }

        public Choice(String label, String value) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(value, "value");
            this.label = label;
            this.value = value;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
