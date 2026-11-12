package com.jarvis.app;

import com.jarvis.core.nlp.AddressRhythm;
import com.jarvis.core.storage.ConversationJournal;
import com.jarvis.core.storage.JarvisSchema;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ConversationStore.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\fJ\u0014\u0010\u0010\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\bJ\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012J\u0006\u0010\u001a\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\bJ\b\u0010\u001d\u001a\u00020\bH\u0002J\b\u0010\u001e\u001a\u00020\nH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/jarvis/app/ConversationStore;", "", "<init>", "()V", AndroidReminderBook.KEY_ITEMS, "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/jarvis/app/ConversationStore$Bubble;", "seq", "", "pushUser", "", VoiceService.EXTRA_TEXT, "", "pushAi", "badge", "plan", "restore", "entries", "", "Lcom/jarvis/core/storage/ConversationJournal$Entry;", "toggleFavorite", "id", "all", "today", "week", "favorites", "clear", "formatTime", "atMs", "startOfDayMs", "trim", "Bubble", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConversationStore {
    public static final ConversationStore INSTANCE = new ConversationStore();
    private static final CopyOnWriteArrayList<Bubble> items = new CopyOnWriteArrayList<>();
    private static long seq;

    /* JADX INFO: compiled from: ConversationStore.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001*BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\fHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003J_\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010%\u001a\u00020\f2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015¨\u0006+"}, d2 = {"Lcom/jarvis/app/ConversationStore$Bubble;", "", "id", "", "role", "Lcom/jarvis/app/ConversationStore$Bubble$Role;", VoiceService.EXTRA_TEXT, "", "atMs", "badge", "plan", "favorite", "", "dbId", "<init>", "(JLcom/jarvis/app/ConversationStore$Bubble$Role;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getId", "()J", "getRole", "()Lcom/jarvis/app/ConversationStore$Bubble$Role;", "getText", "()Ljava/lang/String;", "getAtMs", "getBadge", "getPlan", "getFavorite", "()Z", "getDbId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "Role", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Bubble {
        private final long atMs;
        private final String badge;
        private final String dbId;
        private final boolean favorite;
        private final long id;
        private final String plan;
        private final Role role;
        private final String text;

        /* JADX INFO: compiled from: ConversationStore.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/jarvis/app/ConversationStore$Bubble$Role;", "", "<init>", "(Ljava/lang/String;I)V", "USER", "AI", "SYSTEM", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public enum Role {
            USER,
            AI,
            SYSTEM;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

            public static EnumEntries<Role> getEntries() {
                return $ENTRIES;
            }
        }

        public static /* synthetic */ Bubble copy$default(Bubble bubble, long j, Role role, String str, long j2, String str2, String str3, boolean z, String str4, int i, Object obj) {
            return bubble.copy((i & 1) != 0 ? bubble.id : j, (i & 2) != 0 ? bubble.role : role, (i & 4) != 0 ? bubble.text : str, (i & 8) != 0 ? bubble.atMs : j2, (i & 16) != 0 ? bubble.badge : str2, (i & 32) != 0 ? bubble.plan : str3, (i & 64) != 0 ? bubble.favorite : z, (i & 128) != 0 ? bubble.dbId : str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Role getRole() {
            return this.role;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getAtMs() {
            return this.atMs;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getBadge() {
            return this.badge;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPlan() {
            return this.plan;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getFavorite() {
            return this.favorite;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getDbId() {
            return this.dbId;
        }

        public final Bubble copy(long id, Role role, String text, long atMs, String badge, String plan, boolean favorite, String dbId) {
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(text, "text");
            return new Bubble(id, role, text, atMs, badge, plan, favorite, dbId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Bubble)) {
                return false;
            }
            Bubble bubble = (Bubble) other;
            return this.id == bubble.id && this.role == bubble.role && Intrinsics.areEqual(this.text, bubble.text) && this.atMs == bubble.atMs && Intrinsics.areEqual(this.badge, bubble.badge) && Intrinsics.areEqual(this.plan, bubble.plan) && this.favorite == bubble.favorite && Intrinsics.areEqual(this.dbId, bubble.dbId);
        }

        public int hashCode() {
            return (((((((((((((Long.hashCode(this.id) * 31) + this.role.hashCode()) * 31) + this.text.hashCode()) * 31) + Long.hashCode(this.atMs)) * 31) + (this.badge == null ? 0 : this.badge.hashCode())) * 31) + (this.plan == null ? 0 : this.plan.hashCode())) * 31) + Boolean.hashCode(this.favorite)) * 31) + (this.dbId != null ? this.dbId.hashCode() : 0);
        }

        public String toString() {
            return "Bubble(id=" + this.id + ", role=" + this.role + ", text=" + this.text + ", atMs=" + this.atMs + ", badge=" + this.badge + ", plan=" + this.plan + ", favorite=" + this.favorite + ", dbId=" + this.dbId + ")";
        }

        public Bubble(long id, Role role, String text, long atMs, String badge, String plan, boolean favorite, String dbId) {
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(text, "text");
            this.id = id;
            this.role = role;
            this.text = text;
            this.atMs = atMs;
            this.badge = badge;
            this.plan = plan;
            this.favorite = favorite;
            this.dbId = dbId;
        }

        public /* synthetic */ Bubble(long j, Role role, String str, long j2, String str2, String str3, boolean z, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, role, str, j2, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? false : z, (i & 128) != 0 ? null : str4);
        }

        public final long getId() {
            return this.id;
        }

        public final Role getRole() {
            return this.role;
        }

        public final String getText() {
            return this.text;
        }

        public final long getAtMs() {
            return this.atMs;
        }

        public final String getBadge() {
            return this.badge;
        }

        public final String getPlan() {
            return this.plan;
        }

        public final boolean getFavorite() {
            return this.favorite;
        }

        public final String getDbId() {
            return this.dbId;
        }
    }

    private ConversationStore() {
    }

    public final void pushUser(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (StringsKt.isBlank(text)) {
            return;
        }
        String clean = StringsKt.trim((CharSequence) text).toString();
        CopyOnWriteArrayList<Bubble> copyOnWriteArrayList = items;
        seq++;
        copyOnWriteArrayList.add(new Bubble(seq, Bubble.Role.USER, clean, System.currentTimeMillis(), null, null, false, ConversationJournal.INSTANCE.appendUser(clean), 112, null));
        trim();
    }

    public static /* synthetic */ void pushAi$default(ConversationStore conversationStore, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        conversationStore.pushAi(str, str2, str3);
    }

    public final void pushAi(String text, String badge, String plan) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (StringsKt.isBlank(text)) {
            return;
        }
        String clean = AddressRhythm.INSTANCE.strip(StringsKt.trim((CharSequence) text).toString());
        CopyOnWriteArrayList<Bubble> copyOnWriteArrayList = items;
        seq++;
        copyOnWriteArrayList.add(new Bubble(seq, Bubble.Role.AI, clean, System.currentTimeMillis(), badge, plan, false, ConversationJournal.INSTANCE.appendAssistant(clean), 64, null));
        trim();
    }

    public final void restore(List<ConversationJournal.Entry> entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        if (entries.isEmpty() || !items.isEmpty()) {
            return;
        }
        List<ConversationJournal.Entry> $this$forEach$iv = entries;
        for (Object element$iv : $this$forEach$iv) {
            ConversationJournal.Entry e = (ConversationJournal.Entry) element$iv;
            Bubble.Role role = Intrinsics.areEqual(e.getRole(), JarvisSchema.ROLE_USER) ? Bubble.Role.USER : Bubble.Role.AI;
            CopyOnWriteArrayList<Bubble> copyOnWriteArrayList = items;
            seq++;
            copyOnWriteArrayList.add(new Bubble(seq, role, role == Bubble.Role.AI ? AddressRhythm.INSTANCE.strip(e.getText()) : e.getText(), e.getCreatedAt(), null, null, e.getKeep(), e.getId(), 48, null));
            $this$forEach$iv = $this$forEach$iv;
        }
        trim();
    }

    public final void toggleFavorite(long id) {
        List $this$indexOfFirst$iv = items;
        int index$iv = 0;
        Iterator<Bubble> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                Bubble it2 = (Bubble) item$iv;
                if (it2.getId() == id) {
                    break;
                } else {
                    index$iv++;
                }
            } else {
                index$iv = -1;
                break;
            }
        }
        int idx = index$iv;
        if (idx >= 0) {
            Bubble old = items.get(idx);
            boolean next = !old.getFavorite();
            CopyOnWriteArrayList<Bubble> copyOnWriteArrayList = items;
            Intrinsics.checkNotNull(old);
            copyOnWriteArrayList.set(idx, Bubble.copy$default(old, 0L, null, null, 0L, null, null, next, null, 191, null));
            ConversationJournal.INSTANCE.setKeep(old.getDbId(), next);
        }
    }

    public final List<Bubble> all() {
        return CollectionsKt.toList(items);
    }

    public final List<Bubble> today() {
        long start = startOfDayMs();
        Iterable $this$filter$iv = items;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Bubble it = (Bubble) element$iv$iv;
            if (it.getAtMs() >= start) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<Bubble> week() {
        long start = System.currentTimeMillis() - 604800000;
        Iterable $this$filter$iv = items;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Bubble it = (Bubble) element$iv$iv;
            if (it.getAtMs() >= start) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<Bubble> favorites() {
        Iterable $this$filter$iv = items;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Bubble it = (Bubble) element$iv$iv;
            if (it.getFavorite()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final void clear() {
        items.clear();
    }

    public final String formatTime(long atMs) {
        String str = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(atMs));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final long startOfDayMs() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTimeInMillis();
    }

    private final void trim() {
        while (items.size() > 120) {
            items.remove(0);
        }
    }
}
