package com.jarvis.core.browseragent;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.core.planner.UserGoal;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrowserAgent.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\u000bHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0015¨\u0006\""}, d2 = {"Lcom/jarvis/core/browseragent/BrowserAgentRequest;", "", "goal", "Lcom/jarvis/core/planner/UserGoal;", "steps", "", "Lcom/jarvis/core/browseragent/BrowserStepKind;", SearchIntents.EXTRA_QUERY, "", ImagesContract.URL, "isOnline", "", "<init>", "(Lcom/jarvis/core/planner/UserGoal;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Z)V", "getGoal", "()Lcom/jarvis/core/planner/UserGoal;", "getSteps", "()Ljava/util/List;", "getQuery", "()Ljava/lang/String;", "getUrl", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class BrowserAgentRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<BrowserStepKind> DEFAULT_PIPELINE = CollectionsKt.listOf((Object[]) new BrowserStepKind[]{BrowserStepKind.SEARCH, BrowserStepKind.OPEN, BrowserStepKind.INSPECT, BrowserStepKind.EXTRACT, BrowserStepKind.ACT, BrowserStepKind.VERIFY});
    private final UserGoal goal;
    private final boolean isOnline;
    private final String query;
    private final List<BrowserStepKind> steps;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BrowserAgentRequest copy$default(BrowserAgentRequest browserAgentRequest, UserGoal userGoal, List list, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            userGoal = browserAgentRequest.goal;
        }
        if ((i & 2) != 0) {
            list = browserAgentRequest.steps;
        }
        List list2 = list;
        if ((i & 4) != 0) {
            str = browserAgentRequest.query;
        }
        String str3 = str;
        if ((i & 8) != 0) {
            str2 = browserAgentRequest.url;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            z = browserAgentRequest.isOnline;
        }
        return browserAgentRequest.copy(userGoal, list2, str3, str4, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final UserGoal getGoal() {
        return this.goal;
    }

    public final List<BrowserStepKind> component2() {
        return this.steps;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    public final BrowserAgentRequest copy(UserGoal goal, List<? extends BrowserStepKind> steps, String query, String url, boolean isOnline) {
        Intrinsics.checkNotNullParameter(goal, "goal");
        Intrinsics.checkNotNullParameter(steps, "steps");
        return new BrowserAgentRequest(goal, steps, query, url, isOnline);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BrowserAgentRequest)) {
            return false;
        }
        BrowserAgentRequest browserAgentRequest = (BrowserAgentRequest) other;
        return Intrinsics.areEqual(this.goal, browserAgentRequest.goal) && Intrinsics.areEqual(this.steps, browserAgentRequest.steps) && Intrinsics.areEqual(this.query, browserAgentRequest.query) && Intrinsics.areEqual(this.url, browserAgentRequest.url) && this.isOnline == browserAgentRequest.isOnline;
    }

    public int hashCode() {
        return (((((((this.goal.hashCode() * 31) + this.steps.hashCode()) * 31) + (this.query == null ? 0 : this.query.hashCode())) * 31) + (this.url != null ? this.url.hashCode() : 0)) * 31) + Boolean.hashCode(this.isOnline);
    }

    public String toString() {
        return "BrowserAgentRequest(goal=" + this.goal + ", steps=" + this.steps + ", query=" + this.query + ", url=" + this.url + ", isOnline=" + this.isOnline + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BrowserAgentRequest(UserGoal goal, List<? extends BrowserStepKind> steps, String query, String url, boolean isOnline) {
        Intrinsics.checkNotNullParameter(goal, "goal");
        Intrinsics.checkNotNullParameter(steps, "steps");
        this.goal = goal;
        this.steps = steps;
        this.query = query;
        this.url = url;
        this.isOnline = isOnline;
        if (!(!this.steps.isEmpty() && this.steps.size() <= 16)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String it = this.query;
        if (it != null) {
            if (!(it.length() <= 400)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        String it2 = this.url;
        if (it2 == null) {
            return;
        }
        if (!(it2.length() <= 1000)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ BrowserAgentRequest(UserGoal userGoal, List list, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(userGoal, (i & 2) != 0 ? DEFAULT_PIPELINE : list, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? true : z);
    }

    public final UserGoal getGoal() {
        return this.goal;
    }

    public final List<BrowserStepKind> getSteps() {
        return this.steps;
    }

    public final String getQuery() {
        return this.query;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    /* JADX INFO: compiled from: BrowserAgent.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/jarvis/core/browseragent/BrowserAgentRequest$Companion;", "", "<init>", "()V", "DEFAULT_PIPELINE", "", "Lcom/jarvis/core/browseragent/BrowserStepKind;", "getDEFAULT_PIPELINE", "()Ljava/util/List;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<BrowserStepKind> getDEFAULT_PIPELINE() {
            return BrowserAgentRequest.DEFAULT_PIPELINE;
        }
    }
}
