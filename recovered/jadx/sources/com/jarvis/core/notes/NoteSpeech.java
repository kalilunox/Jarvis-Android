package com.jarvis.core.notes;

import com.jarvis.app.AndroidReminderBook;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Notes.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/jarvis/core/notes/NoteSpeech;", "", "<init>", "()V", "EMPTY", "", "", "TITLE", "read", "list", AndroidReminderBook.KEY_ITEMS, "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NoteSpeech {
    public static final NoteSpeech INSTANCE = new NoteSpeech();
    private static final Map<String, String> EMPTY = MapsKt.mapOf(TuplesKt.to(NoteParser.SHOPPING, "Список покупок пуст, сэр."), TuplesKt.to(NoteParser.TODO, "Список дел пуст, сэр."));
    private static final Map<String, String> TITLE = MapsKt.mapOf(TuplesKt.to(NoteParser.SHOPPING, "В покупках"), TuplesKt.to(NoteParser.TODO, "В делах"), TuplesKt.to(NoteParser.NOTES, "В заметках"));

    private NoteSpeech() {
    }

    public final String read(String list, List<String> items) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(items, "items");
        if (items.isEmpty()) {
            String str = EMPTY.get(list);
            return str == null ? "Заметок нет, сэр." : str;
        }
        return MapsKt.getValue(TITLE, list) + ": " + CollectionsKt.joinToString$default(items, ", ", null, null, 0, null, null, 62, null) + ".";
    }
}
