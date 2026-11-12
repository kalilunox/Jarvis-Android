package com.jarvis.core.nativeandroid;

import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.common.model.RiskLevel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NativeAndroidToolCatalog.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\fHÆ\u0003JK\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006'"}, d2 = {"Lcom/jarvis/core/nativeandroid/NativeToolSpec;", "", "toolId", "", "category", "actionKind", "Lcom/jarvis/core/autonomy/ActionKind;", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "execMode", "Lcom/jarvis/core/nativeandroid/NativeToolExecMode;", "notes", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/autonomy/ActionKind;Lcom/jarvis/core/common/model/RiskLevel;Lcom/jarvis/core/nativeandroid/NativeToolExecMode;Ljava/util/List;)V", "getToolId", "()Ljava/lang/String;", "getCategory", "getActionKind", "()Lcom/jarvis/core/autonomy/ActionKind;", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getExecMode", "()Lcom/jarvis/core/nativeandroid/NativeToolExecMode;", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class NativeToolSpec {
    private final ActionKind actionKind;
    private final String category;
    private final NativeToolExecMode execMode;
    private final List<String> notes;
    private final RiskLevel riskLevel;
    private final String toolId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NativeToolSpec copy$default(NativeToolSpec nativeToolSpec, String str, String str2, ActionKind actionKind, RiskLevel riskLevel, NativeToolExecMode nativeToolExecMode, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = nativeToolSpec.toolId;
        }
        if ((i & 2) != 0) {
            str2 = nativeToolSpec.category;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            actionKind = nativeToolSpec.actionKind;
        }
        ActionKind actionKind2 = actionKind;
        if ((i & 8) != 0) {
            riskLevel = nativeToolSpec.riskLevel;
        }
        RiskLevel riskLevel2 = riskLevel;
        if ((i & 16) != 0) {
            nativeToolExecMode = nativeToolSpec.execMode;
        }
        NativeToolExecMode nativeToolExecMode2 = nativeToolExecMode;
        if ((i & 32) != 0) {
            list = nativeToolSpec.notes;
        }
        return nativeToolSpec.copy(str, str3, actionKind2, riskLevel2, nativeToolExecMode2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ActionKind getActionKind() {
        return this.actionKind;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final NativeToolExecMode getExecMode() {
        return this.execMode;
    }

    public final List<String> component6() {
        return this.notes;
    }

    public final NativeToolSpec copy(String toolId, String category, ActionKind actionKind, RiskLevel riskLevel, NativeToolExecMode execMode, List<String> notes) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(actionKind, "actionKind");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(execMode, "execMode");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new NativeToolSpec(toolId, category, actionKind, riskLevel, execMode, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NativeToolSpec)) {
            return false;
        }
        NativeToolSpec nativeToolSpec = (NativeToolSpec) other;
        return Intrinsics.areEqual(this.toolId, nativeToolSpec.toolId) && Intrinsics.areEqual(this.category, nativeToolSpec.category) && this.actionKind == nativeToolSpec.actionKind && this.riskLevel == nativeToolSpec.riskLevel && this.execMode == nativeToolSpec.execMode && Intrinsics.areEqual(this.notes, nativeToolSpec.notes);
    }

    public int hashCode() {
        return (((((((((this.toolId.hashCode() * 31) + this.category.hashCode()) * 31) + this.actionKind.hashCode()) * 31) + this.riskLevel.hashCode()) * 31) + this.execMode.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "NativeToolSpec(toolId=" + this.toolId + ", category=" + this.category + ", actionKind=" + this.actionKind + ", riskLevel=" + this.riskLevel + ", execMode=" + this.execMode + ", notes=" + this.notes + ")";
    }

    public NativeToolSpec(String toolId, String category, ActionKind actionKind, RiskLevel riskLevel, NativeToolExecMode execMode, List<String> notes) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(actionKind, "actionKind");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(execMode, "execMode");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.toolId = toolId;
        this.category = category;
        this.actionKind = actionKind;
        this.riskLevel = riskLevel;
        this.execMode = execMode;
        this.notes = notes;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ NativeToolSpec(String str, String str2, ActionKind actionKind, RiskLevel riskLevel, NativeToolExecMode nativeToolExecMode, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList;
        if ((i & 32) == 0) {
            listEmptyList = list;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this(str, str2, actionKind, riskLevel, nativeToolExecMode, listEmptyList);
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final String getCategory() {
        return this.category;
    }

    public final ActionKind getActionKind() {
        return this.actionKind;
    }

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final NativeToolExecMode getExecMode() {
        return this.execMode;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
