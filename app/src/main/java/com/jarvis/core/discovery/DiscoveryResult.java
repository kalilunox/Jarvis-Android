package com.jarvis.core.discovery;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.AndroidReminderBook;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DiscoveryModels.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003JG\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\nHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011¨\u0006!"}, d2 = {"Lcom/jarvis/core/discovery/DiscoveryResult;", "", SearchIntents.EXTRA_QUERY, "", AndroidReminderBook.KEY_ITEMS, "", "Lcom/jarvis/core/discovery/DiscoveredItem;", "truncated", "", "considered", "", "notes", "<init>", "(Ljava/lang/String;Ljava/util/List;ZILjava/util/List;)V", "getQuery", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "getTruncated", "()Z", "getConsidered", "()I", "getNotes", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DiscoveryResult {
    private final int considered;
    private final List<DiscoveredItem> items;
    private final List<String> notes;
    private final String query;
    private final boolean truncated;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DiscoveryResult copy$default(DiscoveryResult discoveryResult, String str, List list, boolean z, int i, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = discoveryResult.query;
        }
        if ((i2 & 2) != 0) {
            list = discoveryResult.items;
        }
        List list3 = list;
        if ((i2 & 4) != 0) {
            z = discoveryResult.truncated;
        }
        boolean z2 = z;
        if ((i2 & 8) != 0) {
            i = discoveryResult.considered;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            list2 = discoveryResult.notes;
        }
        return discoveryResult.copy(str, list3, z2, i3, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    public final List<DiscoveredItem> component2() {
        return this.items;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getTruncated() {
        return this.truncated;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getConsidered() {
        return this.considered;
    }

    public final List<String> component5() {
        return this.notes;
    }

    public final DiscoveryResult copy(String query, List<DiscoveredItem> items, boolean truncated, int considered, List<String> notes) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new DiscoveryResult(query, items, truncated, considered, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DiscoveryResult)) {
            return false;
        }
        DiscoveryResult discoveryResult = (DiscoveryResult) other;
        return Intrinsics.areEqual(this.query, discoveryResult.query) && Intrinsics.areEqual(this.items, discoveryResult.items) && this.truncated == discoveryResult.truncated && this.considered == discoveryResult.considered && Intrinsics.areEqual(this.notes, discoveryResult.notes);
    }

    public int hashCode() {
        return (((((((this.query.hashCode() * 31) + this.items.hashCode()) * 31) + Boolean.hashCode(this.truncated)) * 31) + Integer.hashCode(this.considered)) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "DiscoveryResult(query=" + this.query + ", items=" + this.items + ", truncated=" + this.truncated + ", considered=" + this.considered + ", notes=" + this.notes + ")";
    }

    public DiscoveryResult(String query, List<DiscoveredItem> items, boolean truncated, int considered, List<String> notes) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.query = query;
        this.items = items;
        this.truncated = truncated;
        this.considered = considered;
        this.notes = notes;
        if (this.items.size() <= 12) {
        } else {
            throw new IllegalArgumentException("DiscoveryResult must not exceed hard cap".toString());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DiscoveryResult(String str, List list, boolean z, int i, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList;
        if ((i2 & 16) == 0) {
            listEmptyList = list2;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this(str, list, z, i, listEmptyList);
    }

    public final String getQuery() {
        return this.query;
    }

    public final List<DiscoveredItem> getItems() {
        return this.items;
    }

    public final boolean getTruncated() {
        return this.truncated;
    }

    public final int getConsidered() {
        return this.considered;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
