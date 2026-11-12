package com.jarvis.core.notes;

import com.jarvis.app.AndroidReminderBook;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: Notes.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H&J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/notes/NoteBook;", "", AndroidReminderBook.KEY_ITEMS, "", "", "list", "add", "", "remove", "", "item", "clear", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NoteBook {
    void add(String list, List<String> items);

    void clear(String list);

    List<String> items(String list);

    boolean remove(String list, String item);
}
