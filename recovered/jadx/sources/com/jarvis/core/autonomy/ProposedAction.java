package com.jarvis.core.autonomy;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProposedAction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003JI\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/jarvis/core/autonomy/ProposedAction;", "", "actionId", "", "kind", "Lcom/jarvis/core/autonomy/ActionKind;", "displaySummary", "toolId", "arguments", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/autonomy/ActionKind;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getActionId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/autonomy/ActionKind;", "getDisplaySummary", "getToolId", "getArguments", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProposedAction {
    public static final int MAX_ARGS = 12;
    public static final int MAX_KEY = 48;
    public static final int MAX_SUMMARY = 160;
    public static final int MAX_VALUE = 96;
    private final String actionId;
    private final Map<String, String> arguments;
    private final String displaySummary;
    private final ActionKind kind;
    private final String toolId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ProposedAction copy$default(ProposedAction proposedAction, String str, ActionKind actionKind, String str2, String str3, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = proposedAction.actionId;
        }
        if ((i & 2) != 0) {
            actionKind = proposedAction.kind;
        }
        ActionKind actionKind2 = actionKind;
        if ((i & 4) != 0) {
            str2 = proposedAction.displaySummary;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            str3 = proposedAction.toolId;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            map = proposedAction.arguments;
        }
        return proposedAction.copy(str, actionKind2, str4, str5, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getActionId() {
        return this.actionId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ActionKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDisplaySummary() {
        return this.displaySummary;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> component5() {
        return this.arguments;
    }

    public final ProposedAction copy(String actionId, ActionKind kind, String displaySummary, String toolId, Map<String, String> arguments) {
        Intrinsics.checkNotNullParameter(actionId, "actionId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(displaySummary, "displaySummary");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        return new ProposedAction(actionId, kind, displaySummary, toolId, arguments);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProposedAction)) {
            return false;
        }
        ProposedAction proposedAction = (ProposedAction) other;
        return Intrinsics.areEqual(this.actionId, proposedAction.actionId) && this.kind == proposedAction.kind && Intrinsics.areEqual(this.displaySummary, proposedAction.displaySummary) && Intrinsics.areEqual(this.toolId, proposedAction.toolId) && Intrinsics.areEqual(this.arguments, proposedAction.arguments);
    }

    public int hashCode() {
        return (((((((this.actionId.hashCode() * 31) + this.kind.hashCode()) * 31) + this.displaySummary.hashCode()) * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + this.arguments.hashCode();
    }

    public String toString() {
        return "ProposedAction(actionId=" + this.actionId + ", kind=" + this.kind + ", displaySummary=" + this.displaySummary + ", toolId=" + this.toolId + ", arguments=" + this.arguments + ")";
    }

    public ProposedAction(String actionId, ActionKind kind, String displaySummary, String toolId, Map<String, String> arguments) {
        Intrinsics.checkNotNullParameter(actionId, "actionId");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(displaySummary, "displaySummary");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.actionId = actionId;
        this.kind = kind;
        this.displaySummary = displaySummary;
        this.toolId = toolId;
        this.arguments = arguments;
        if (StringsKt.isBlank(this.actionId)) {
            throw new IllegalArgumentException("actionId blank".toString());
        }
        if (!(this.displaySummary.length() <= 160)) {
            throw new IllegalArgumentException("displaySummary too long".toString());
        }
        if (!(this.arguments.size() <= 12)) {
            throw new IllegalArgumentException("too many arguments".toString());
        }
        for (Map.Entry<String, String> entry : this.arguments.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (!(k.length() <= 48)) {
                throw new IllegalArgumentException("arg key too long".toString());
            }
            if (!(v.length() <= 96)) {
                throw new IllegalArgumentException("arg value too long".toString());
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ProposedAction(String str, ActionKind actionKind, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str4;
        Map mapEmptyMap;
        if ((i & 8) == 0) {
            str4 = str3;
        } else {
            str4 = null;
        }
        if ((i & 16) == 0) {
            mapEmptyMap = map;
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        this(str, actionKind, str2, str4, mapEmptyMap);
    }

    public final String getActionId() {
        return this.actionId;
    }

    public final ActionKind getKind() {
        return this.kind;
    }

    public final String getDisplaySummary() {
        return this.displaySummary;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> getArguments() {
        return this.arguments;
    }
}
