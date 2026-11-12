package com.jarvis.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActivityFeed.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0011J\u000e\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\bJ\u0006\u0010\u001b\u001a\u00020\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/jarvis/app/ActivityFeed;", "", "<init>", "()V", AndroidReminderBook.KEY_ITEMS, "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/jarvis/app/ActivityFeed$Item;", "seq", "", "push", "", "title", "", "detail", "kind", "Lcom/jarvis/app/ActivityFeed$Item$Kind;", "pendingConfirm", "", "failed", "all", "", "filter", "markResolved", "id", "approved", "formatTime", "atMs", "clear", "Item", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActivityFeed {
    public static final ActivityFeed INSTANCE = new ActivityFeed();
    private static final CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();
    private static long seq;

    /* JADX INFO: compiled from: ActivityFeed.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001*BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003JO\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\u0013\u0010%\u001a\u00020\u000b2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001a¨\u0006+"}, d2 = {"Lcom/jarvis/app/ActivityFeed$Item;", "", "id", "", "title", "", "detail", "kind", "Lcom/jarvis/app/ActivityFeed$Item$Kind;", "atMs", "pendingConfirm", "", "failed", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/jarvis/app/ActivityFeed$Item$Kind;JZZ)V", "getId", "()J", "getTitle", "()Ljava/lang/String;", "getDetail", "getKind", "()Lcom/jarvis/app/ActivityFeed$Item$Kind;", "getAtMs", "getPendingConfirm", "()Z", "setPendingConfirm", "(Z)V", "getFailed", "setFailed", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "Kind", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Item {
        private final long atMs;
        private final String detail;
        private boolean failed;
        private final long id;
        private final Kind kind;
        private boolean pendingConfirm;
        private final String title;

        /* JADX INFO: compiled from: ActivityFeed.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/jarvis/app/ActivityFeed$Item$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "VOICE", "TOOL", "SYSTEM", "CONFIRM", "MEMORY", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public enum Kind {
            VOICE,
            TOOL,
            SYSTEM,
            CONFIRM,
            MEMORY;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

            public static EnumEntries<Kind> getEntries() {
                return $ENTRIES;
            }
        }

        public static /* synthetic */ Item copy$default(Item item, long j, String str, String str2, Kind kind, long j2, boolean z, boolean z2, int i, Object obj) {
            return item.copy((i & 1) != 0 ? item.id : j, (i & 2) != 0 ? item.title : str, (i & 4) != 0 ? item.detail : str2, (i & 8) != 0 ? item.kind : kind, (i & 16) != 0 ? item.atMs : j2, (i & 32) != 0 ? item.pendingConfirm : z, (i & 64) != 0 ? item.failed : z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDetail() {
            return this.detail;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Kind getKind() {
            return this.kind;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getAtMs() {
            return this.atMs;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getPendingConfirm() {
            return this.pendingConfirm;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getFailed() {
            return this.failed;
        }

        public final Item copy(long id, String title, String detail, Kind kind, long atMs, boolean pendingConfirm, boolean failed) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(detail, "detail");
            Intrinsics.checkNotNullParameter(kind, "kind");
            return new Item(id, title, detail, kind, atMs, pendingConfirm, failed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Item)) {
                return false;
            }
            Item item = (Item) other;
            return this.id == item.id && Intrinsics.areEqual(this.title, item.title) && Intrinsics.areEqual(this.detail, item.detail) && this.kind == item.kind && this.atMs == item.atMs && this.pendingConfirm == item.pendingConfirm && this.failed == item.failed;
        }

        public int hashCode() {
            return (((((((((((Long.hashCode(this.id) * 31) + this.title.hashCode()) * 31) + this.detail.hashCode()) * 31) + this.kind.hashCode()) * 31) + Long.hashCode(this.atMs)) * 31) + Boolean.hashCode(this.pendingConfirm)) * 31) + Boolean.hashCode(this.failed);
        }

        public String toString() {
            return "Item(id=" + this.id + ", title=" + this.title + ", detail=" + this.detail + ", kind=" + this.kind + ", atMs=" + this.atMs + ", pendingConfirm=" + this.pendingConfirm + ", failed=" + this.failed + ")";
        }

        public Item(long id, String title, String detail, Kind kind, long atMs, boolean pendingConfirm, boolean failed) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(detail, "detail");
            Intrinsics.checkNotNullParameter(kind, "kind");
            this.id = id;
            this.title = title;
            this.detail = detail;
            this.kind = kind;
            this.atMs = atMs;
            this.pendingConfirm = pendingConfirm;
            this.failed = failed;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ Item(long j, String str, String str2, Kind kind, long j2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            boolean z3;
            boolean z4;
            if ((i & 32) == 0) {
                z3 = z;
            } else {
                z3 = false;
            }
            if ((i & 64) == 0) {
                z4 = z2;
            } else {
                z4 = false;
            }
            this(j, str, str2, kind, j2, z3, z4);
        }

        public final long getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getDetail() {
            return this.detail;
        }

        public final Kind getKind() {
            return this.kind;
        }

        public final long getAtMs() {
            return this.atMs;
        }

        public final boolean getPendingConfirm() {
            return this.pendingConfirm;
        }

        public final void setPendingConfirm(boolean z) {
            this.pendingConfirm = z;
        }

        public final boolean getFailed() {
            return this.failed;
        }

        public final void setFailed(boolean z) {
            this.failed = z;
        }
    }

    private ActivityFeed() {
    }

    public static /* synthetic */ void push$default(ActivityFeed activityFeed, String str, String str2, Item.Kind kind, boolean z, boolean z2, int i, Object obj) {
        boolean z3;
        boolean z4;
        if ((i & 8) == 0) {
            z3 = z;
        } else {
            z3 = false;
        }
        if ((i & 16) == 0) {
            z4 = z2;
        } else {
            z4 = false;
        }
        activityFeed.push(str, str2, kind, z3, z4);
    }

    public final void push(String title, String detail, Item.Kind kind, boolean pendingConfirm, boolean failed) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(detail, "detail");
        Intrinsics.checkNotNullParameter(kind, "kind");
        seq++;
        long id = seq;
        items.add(0, new Item(id, title, detail, kind, System.currentTimeMillis(), pendingConfirm, failed));
        while (items.size() > 80) {
            items.remove(CollectionsKt.getLastIndex(items));
        }
    }

    public final List<Item> all() {
        return CollectionsKt.toList(items);
    }

    public final List<Item> filter(Item.Kind kind) {
        if (kind == null) {
            return all();
        }
        Iterable $this$filter$iv = items;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Item it = (Item) element$iv$iv;
            if (it.getKind() == kind) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final void markResolved(long id, boolean approved) {
        Object next;
        StringBuilder sb;
        String str;
        Iterator<T> it = items.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((Item) next).getId() == id));
        Item it2 = (Item) next;
        if (it2 != null) {
            it2.setPendingConfirm(false);
            it2.setFailed(!approved);
        }
        List $this$indexOfFirst$iv = items;
        int index$iv = 0;
        Iterator<Item> it3 = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (!it3.hasNext()) {
                index$iv = -1;
                break;
            }
            Object item$iv = it3.next();
            if (((Item) item$iv).getId() == id) {
                break;
            } else {
                index$iv++;
            }
        }
        int idx = index$iv;
        if (idx >= 0) {
            Item old = items.get(idx);
            CopyOnWriteArrayList<Item> copyOnWriteArrayList = items;
            Intrinsics.checkNotNull(old);
            boolean z = !approved;
            String title = old.getTitle();
            if (approved) {
                sb = new StringBuilder();
                str = "✓ ";
            } else {
                sb = new StringBuilder();
                str = "✕ ";
            }
            copyOnWriteArrayList.set(idx, Item.copy$default(old, 0L, sb.append(str).append(title).toString(), null, null, 0L, false, z, 29, null));
        }
    }

    public final String formatTime(long atMs) {
        String str = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(atMs));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final void clear() {
        items.clear();
    }
}
