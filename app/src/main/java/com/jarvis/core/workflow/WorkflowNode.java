package com.jarvis.core.workflow;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WorkflowModels.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ja\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006&"}, d2 = {"Lcom/jarvis/core/workflow/WorkflowNode;", "", "id", "", "type", "Lcom/jarvis/core/workflow/WorkflowNodeType;", "label", "toolId", "parameters", "", "nextOnSuccess", "nextOnFailure", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/workflow/WorkflowNodeType;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getType", "()Lcom/jarvis/core/workflow/WorkflowNodeType;", "getLabel", "getToolId", "getParameters", "()Ljava/util/Map;", "getNextOnSuccess", "getNextOnFailure", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class WorkflowNode {
    private final String id;
    private final String label;
    private final String nextOnFailure;
    private final String nextOnSuccess;
    private final Map<String, String> parameters;
    private final String toolId;
    private final WorkflowNodeType type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WorkflowNode copy$default(WorkflowNode workflowNode, String str, WorkflowNodeType workflowNodeType, String str2, String str3, Map map, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = workflowNode.id;
        }
        if ((i & 2) != 0) {
            workflowNodeType = workflowNode.type;
        }
        WorkflowNodeType workflowNodeType2 = workflowNodeType;
        if ((i & 4) != 0) {
            str2 = workflowNode.label;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = workflowNode.toolId;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            map = workflowNode.parameters;
        }
        Map map2 = map;
        if ((i & 32) != 0) {
            str4 = workflowNode.nextOnSuccess;
        }
        String str8 = str4;
        if ((i & 64) != 0) {
            str5 = workflowNode.nextOnFailure;
        }
        return workflowNode.copy(str, workflowNodeType2, str6, str7, map2, str8, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final WorkflowNodeType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> component5() {
        return this.parameters;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getNextOnSuccess() {
        return this.nextOnSuccess;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getNextOnFailure() {
        return this.nextOnFailure;
    }

    public final WorkflowNode copy(String id, WorkflowNodeType type, String label, String toolId, Map<String, String> parameters, String nextOnSuccess, String nextOnFailure) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        return new WorkflowNode(id, type, label, toolId, parameters, nextOnSuccess, nextOnFailure);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowNode)) {
            return false;
        }
        WorkflowNode workflowNode = (WorkflowNode) other;
        return Intrinsics.areEqual(this.id, workflowNode.id) && this.type == workflowNode.type && Intrinsics.areEqual(this.label, workflowNode.label) && Intrinsics.areEqual(this.toolId, workflowNode.toolId) && Intrinsics.areEqual(this.parameters, workflowNode.parameters) && Intrinsics.areEqual(this.nextOnSuccess, workflowNode.nextOnSuccess) && Intrinsics.areEqual(this.nextOnFailure, workflowNode.nextOnFailure);
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.type.hashCode()) * 31) + this.label.hashCode()) * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + this.parameters.hashCode()) * 31) + (this.nextOnSuccess == null ? 0 : this.nextOnSuccess.hashCode())) * 31) + (this.nextOnFailure != null ? this.nextOnFailure.hashCode() : 0);
    }

    public String toString() {
        return "WorkflowNode(id=" + this.id + ", type=" + this.type + ", label=" + this.label + ", toolId=" + this.toolId + ", parameters=" + this.parameters + ", nextOnSuccess=" + this.nextOnSuccess + ", nextOnFailure=" + this.nextOnFailure + ")";
    }

    public WorkflowNode(String id, WorkflowNodeType type, String label, String toolId, Map<String, String> parameters, String nextOnSuccess, String nextOnFailure) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.id = id;
        this.type = type;
        this.label = label;
        this.toolId = toolId;
        this.parameters = parameters;
        this.nextOnSuccess = nextOnSuccess;
        this.nextOnFailure = nextOnFailure;
        if (StringsKt.isBlank(this.id)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.label.length() <= 120) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ WorkflowNode(String str, WorkflowNodeType workflowNodeType, String str2, String str3, Map map, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, workflowNodeType, str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5);
    }

    public final String getId() {
        return this.id;
    }

    public final WorkflowNodeType getType() {
        return this.type;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> getParameters() {
        return this.parameters;
    }

    public final String getNextOnSuccess() {
        return this.nextOnSuccess;
    }

    public final String getNextOnFailure() {
        return this.nextOnFailure;
    }
}
