package com.jarvis.core.workflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkflowModels.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0002\u001a\u00020\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/workflow/WorkflowGraph;", "", "id", "", "title", "entryNodeId", "nodes", "", "Lcom/jarvis/core/workflow/WorkflowNode;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getEntryNodeId", "getNodes", "()Ljava/util/List;", "node", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class WorkflowGraph {
    private final String entryNodeId;
    private final String id;
    private final List<WorkflowNode> nodes;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WorkflowGraph copy$default(WorkflowGraph workflowGraph, String str, String str2, String str3, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = workflowGraph.id;
        }
        if ((i & 2) != 0) {
            str2 = workflowGraph.title;
        }
        if ((i & 4) != 0) {
            str3 = workflowGraph.entryNodeId;
        }
        if ((i & 8) != 0) {
            list = workflowGraph.nodes;
        }
        return workflowGraph.copy(str, str2, str3, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEntryNodeId() {
        return this.entryNodeId;
    }

    public final List<WorkflowNode> component4() {
        return this.nodes;
    }

    public final WorkflowGraph copy(String id, String title, String entryNodeId, List<WorkflowNode> nodes) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(entryNodeId, "entryNodeId");
        Intrinsics.checkNotNullParameter(nodes, "nodes");
        return new WorkflowGraph(id, title, entryNodeId, nodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowGraph)) {
            return false;
        }
        WorkflowGraph workflowGraph = (WorkflowGraph) other;
        return Intrinsics.areEqual(this.id, workflowGraph.id) && Intrinsics.areEqual(this.title, workflowGraph.title) && Intrinsics.areEqual(this.entryNodeId, workflowGraph.entryNodeId) && Intrinsics.areEqual(this.nodes, workflowGraph.nodes);
    }

    public int hashCode() {
        return (((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.entryNodeId.hashCode()) * 31) + this.nodes.hashCode();
    }

    public String toString() {
        return "WorkflowGraph(id=" + this.id + ", title=" + this.title + ", entryNodeId=" + this.entryNodeId + ", nodes=" + this.nodes + ")";
    }

    public WorkflowGraph(String id, String title, String entryNodeId, List<WorkflowNode> nodes) {
        boolean z;
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(entryNodeId, "entryNodeId");
        Intrinsics.checkNotNullParameter(nodes, "nodes");
        this.id = id;
        this.title = title;
        this.entryNodeId = entryNodeId;
        this.nodes = nodes;
        if (this.nodes.isEmpty()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Iterable $this$any$iv = this.nodes;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                WorkflowNode it2 = (WorkflowNode) element$iv;
                if (Intrinsics.areEqual(it2.getId(), this.entryNodeId)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Iterable $this$map$iv = this.nodes;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            WorkflowNode it3 = (WorkflowNode) item$iv$iv;
            destination$iv$iv.add(it3.getId());
        }
        if (!(CollectionsKt.toSet((List) destination$iv$iv).size() == this.nodes.size())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getEntryNodeId() {
        return this.entryNodeId;
    }

    public final List<WorkflowNode> getNodes() {
        return this.nodes;
    }

    public final WorkflowNode node(String id) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(id, "id");
        Iterable $this$firstOrNull$iv = this.nodes;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (it.hasNext()) {
            element$iv = it.next();
            WorkflowNode it2 = (WorkflowNode) element$iv;
            if (Intrinsics.areEqual(it2.getId(), id)) {
                return (WorkflowNode) element$iv;
            }
        }
        element$iv = null;
        return (WorkflowNode) element$iv;
    }
}
