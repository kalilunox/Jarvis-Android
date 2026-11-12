package com.jarvis.core.notes;

import com.jarvis.app.AndroidReminderBook;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Notes.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\b\t\n\u000bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0004\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/notes/NoteCommand;", "", "<init>", "()V", "list", "", "getList", "()Ljava/lang/String;", "Add", "Read", "Remove", "Clear", "Lcom/jarvis/core/notes/NoteCommand$Add;", "Lcom/jarvis/core/notes/NoteCommand$Clear;", "Lcom/jarvis/core/notes/NoteCommand$Read;", "Lcom/jarvis/core/notes/NoteCommand$Remove;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class NoteCommand {
    public /* synthetic */ NoteCommand(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getList();

    private NoteCommand() {
    }

    /* JADX INFO: compiled from: Notes.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/notes/NoteCommand$Add;", "Lcom/jarvis/core/notes/NoteCommand;", "list", "", AndroidReminderBook.KEY_ITEMS, "", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getList", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Add extends NoteCommand {
        private final List<String> items;
        private final String list;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Add copy$default(Add add, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = add.list;
            }
            if ((i & 2) != 0) {
                list = add.items;
            }
            return add.copy(str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getList() {
            return this.list;
        }

        public final List<String> component2() {
            return this.items;
        }

        public final Add copy(String list, List<String> items) {
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(items, "items");
            return new Add(list, items);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Add)) {
                return false;
            }
            Add add = (Add) other;
            return Intrinsics.areEqual(this.list, add.list) && Intrinsics.areEqual(this.items, add.items);
        }

        public int hashCode() {
            return (this.list.hashCode() * 31) + this.items.hashCode();
        }

        public String toString() {
            return "Add(list=" + this.list + ", items=" + this.items + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Add(String list, List<String> items) {
            super(null);
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(items, "items");
            this.list = list;
            this.items = items;
        }

        public final List<String> getItems() {
            return this.items;
        }

        @Override // com.jarvis.core.notes.NoteCommand
        public String getList() {
            return this.list;
        }
    }

    /* JADX INFO: compiled from: Notes.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/notes/NoteCommand$Read;", "Lcom/jarvis/core/notes/NoteCommand;", "list", "", "<init>", "(Ljava/lang/String;)V", "getList", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Read extends NoteCommand {
        private final String list;

        public static /* synthetic */ Read copy$default(Read read, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = read.list;
            }
            return read.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getList() {
            return this.list;
        }

        public final Read copy(String list) {
            Intrinsics.checkNotNullParameter(list, "list");
            return new Read(list);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Read) && Intrinsics.areEqual(this.list, ((Read) other).list);
        }

        public int hashCode() {
            return this.list.hashCode();
        }

        public String toString() {
            return "Read(list=" + this.list + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Read(String list) {
            super(null);
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
        }

        @Override // com.jarvis.core.notes.NoteCommand
        public String getList() {
            return this.list;
        }
    }

    /* JADX INFO: compiled from: Notes.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/notes/NoteCommand$Remove;", "Lcom/jarvis/core/notes/NoteCommand;", "list", "", "item", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getList", "()Ljava/lang/String;", "getItem", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Remove extends NoteCommand {
        private final String item;
        private final String list;

        public static /* synthetic */ Remove copy$default(Remove remove, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = remove.list;
            }
            if ((i & 2) != 0) {
                str2 = remove.item;
            }
            return remove.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getList() {
            return this.list;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getItem() {
            return this.item;
        }

        public final Remove copy(String list, String item) {
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(item, "item");
            return new Remove(list, item);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Remove)) {
                return false;
            }
            Remove remove = (Remove) other;
            return Intrinsics.areEqual(this.list, remove.list) && Intrinsics.areEqual(this.item, remove.item);
        }

        public int hashCode() {
            return (this.list.hashCode() * 31) + this.item.hashCode();
        }

        public String toString() {
            return "Remove(list=" + this.list + ", item=" + this.item + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Remove(String list, String item) {
            super(null);
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(item, "item");
            this.list = list;
            this.item = item;
        }

        public final String getItem() {
            return this.item;
        }

        @Override // com.jarvis.core.notes.NoteCommand
        public String getList() {
            return this.list;
        }
    }

    /* JADX INFO: compiled from: Notes.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/notes/NoteCommand$Clear;", "Lcom/jarvis/core/notes/NoteCommand;", "list", "", "<init>", "(Ljava/lang/String;)V", "getList", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Clear extends NoteCommand {
        private final String list;

        public static /* synthetic */ Clear copy$default(Clear clear, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = clear.list;
            }
            return clear.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getList() {
            return this.list;
        }

        public final Clear copy(String list) {
            Intrinsics.checkNotNullParameter(list, "list");
            return new Clear(list);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Clear) && Intrinsics.areEqual(this.list, ((Clear) other).list);
        }

        public int hashCode() {
            return this.list.hashCode();
        }

        public String toString() {
            return "Clear(list=" + this.list + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Clear(String list) {
            super(null);
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
        }

        @Override // com.jarvis.core.notes.NoteCommand
        public String getList() {
            return this.list;
        }
    }
}
