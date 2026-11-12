package com.jarvis.core.filesagent;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.planner.UserGoal;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilesAgent.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 #2\u00020\u0001:\u0001#BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003JM\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006$"}, d2 = {"Lcom/jarvis/core/filesagent/FileAgentRequest;", "", "goal", "Lcom/jarvis/core/planner/UserGoal;", "operation", "Lcom/jarvis/core/filesagent/FileOperation;", "path", "", SearchIntents.EXTRA_QUERY, "destination", "contentHint", "<init>", "(Lcom/jarvis/core/planner/UserGoal;Lcom/jarvis/core/filesagent/FileOperation;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getGoal", "()Lcom/jarvis/core/planner/UserGoal;", "getOperation", "()Lcom/jarvis/core/filesagent/FileOperation;", "getPath", "()Ljava/lang/String;", "getQuery", "getDestination", "getContentHint", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class FileAgentRequest {
    public static final int MAX_PATH = 512;
    public static final int MAX_QUERY = 200;
    private final String contentHint;
    private final String destination;
    private final UserGoal goal;
    private final FileOperation operation;
    private final String path;
    private final String query;

    public static /* synthetic */ FileAgentRequest copy$default(FileAgentRequest fileAgentRequest, UserGoal userGoal, FileOperation fileOperation, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            userGoal = fileAgentRequest.goal;
        }
        if ((i & 2) != 0) {
            fileOperation = fileAgentRequest.operation;
        }
        FileOperation fileOperation2 = fileOperation;
        if ((i & 4) != 0) {
            str = fileAgentRequest.path;
        }
        String str5 = str;
        if ((i & 8) != 0) {
            str2 = fileAgentRequest.query;
        }
        String str6 = str2;
        if ((i & 16) != 0) {
            str3 = fileAgentRequest.destination;
        }
        String str7 = str3;
        if ((i & 32) != 0) {
            str4 = fileAgentRequest.contentHint;
        }
        return fileAgentRequest.copy(userGoal, fileOperation2, str5, str6, str7, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final UserGoal getGoal() {
        return this.goal;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FileOperation getOperation() {
        return this.operation;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getContentHint() {
        return this.contentHint;
    }

    public final FileAgentRequest copy(UserGoal goal, FileOperation operation, String path, String query, String destination, String contentHint) {
        Intrinsics.checkNotNullParameter(goal, "goal");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return new FileAgentRequest(goal, operation, path, query, destination, contentHint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FileAgentRequest)) {
            return false;
        }
        FileAgentRequest fileAgentRequest = (FileAgentRequest) other;
        return Intrinsics.areEqual(this.goal, fileAgentRequest.goal) && this.operation == fileAgentRequest.operation && Intrinsics.areEqual(this.path, fileAgentRequest.path) && Intrinsics.areEqual(this.query, fileAgentRequest.query) && Intrinsics.areEqual(this.destination, fileAgentRequest.destination) && Intrinsics.areEqual(this.contentHint, fileAgentRequest.contentHint);
    }

    public int hashCode() {
        return (((((((((this.goal.hashCode() * 31) + this.operation.hashCode()) * 31) + (this.path == null ? 0 : this.path.hashCode())) * 31) + (this.query == null ? 0 : this.query.hashCode())) * 31) + (this.destination == null ? 0 : this.destination.hashCode())) * 31) + (this.contentHint != null ? this.contentHint.hashCode() : 0);
    }

    public String toString() {
        return "FileAgentRequest(goal=" + this.goal + ", operation=" + this.operation + ", path=" + this.path + ", query=" + this.query + ", destination=" + this.destination + ", contentHint=" + this.contentHint + ")";
    }

    public FileAgentRequest(UserGoal goal, FileOperation operation, String path, String query, String destination, String contentHint) {
        Intrinsics.checkNotNullParameter(goal, "goal");
        Intrinsics.checkNotNullParameter(operation, "operation");
        this.goal = goal;
        this.operation = operation;
        this.path = path;
        this.query = query;
        this.destination = destination;
        this.contentHint = contentHint;
        String it = this.path;
        if (it != null) {
            if (!(it.length() <= 512)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        String it2 = this.query;
        if (it2 != null) {
            if (!(it2.length() <= 200)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        String it3 = this.destination;
        if (it3 == null) {
            return;
        }
        if (!(it3.length() <= 512)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ FileAgentRequest(UserGoal userGoal, FileOperation fileOperation, String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(userGoal, fileOperation, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4);
    }

    public final UserGoal getGoal() {
        return this.goal;
    }

    public final FileOperation getOperation() {
        return this.operation;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getQuery() {
        return this.query;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final String getContentHint() {
        return this.contentHint;
    }
}
