package com.jarvis.core.agent;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppAgentRules.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/agent/CompactItem;", "", "item", "Lcom/jarvis/core/agent/ScreenItem;", "source", "", "<init>", "(Lcom/jarvis/core/agent/ScreenItem;I)V", "getItem", "()Lcom/jarvis/core/agent/ScreenItem;", "getSource", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CompactItem {
    private final ScreenItem item;
    private final int source;

    public static /* synthetic */ CompactItem copy$default(CompactItem compactItem, ScreenItem screenItem, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            screenItem = compactItem.item;
        }
        if ((i2 & 2) != 0) {
            i = compactItem.source;
        }
        return compactItem.copy(screenItem, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ScreenItem getItem() {
        return this.item;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSource() {
        return this.source;
    }

    public final CompactItem copy(ScreenItem item, int source) {
        Intrinsics.checkNotNullParameter(item, "item");
        return new CompactItem(item, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CompactItem)) {
            return false;
        }
        CompactItem compactItem = (CompactItem) other;
        return Intrinsics.areEqual(this.item, compactItem.item) && this.source == compactItem.source;
    }

    public int hashCode() {
        return (this.item.hashCode() * 31) + Integer.hashCode(this.source);
    }

    public String toString() {
        return "CompactItem(item=" + this.item + ", source=" + this.source + ")";
    }

    public CompactItem(ScreenItem item, int source) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.item = item;
        this.source = source;
    }

    public final ScreenItem getItem() {
        return this.item;
    }

    public final int getSource() {
        return this.source;
    }
}
