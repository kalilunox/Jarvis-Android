package com.jarvis.app;

import android.content.Context;
import com.jarvis.core.notes.NoteBook;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* JADX INFO: compiled from: VoiceStores.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/app/PrefsNoteBook;", "Lcom/jarvis/core/notes/NoteBook;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", AndroidReminderBook.KEY_ITEMS, "", "", "list", "add", "", "remove", "", "item", "clear", "save", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PrefsNoteBook implements NoteBook {
    private final Context context;

    public PrefsNoteBook(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.jarvis.core.notes.NoteBook
    public synchronized List<String> items(String list) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(list, "list");
        try {
            Result.Companion companion = Result.INSTANCE;
            PrefsNoteBook $this$items_u24lambda_u240 = this;
            objM392constructorimpl = Result.m392constructorimpl(VoiceStoresKt.strings(new JSONArray(VoiceStoresKt.voicePrefs($this$items_u24lambda_u240.context).getString("list_" + list, "[]"))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        return (List) objM392constructorimpl;
    }

    @Override // com.jarvis.core.notes.NoteBook
    public synchronized void add(String list, List<String> items) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(items, "items");
        save(list, CollectionsKt.plus((Collection) items(list), (Iterable) items));
    }

    @Override // com.jarvis.core.notes.NoteBook
    public synchronized boolean remove(String list, String item) {
        boolean z;
        item = item;
        synchronized (this) {
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(item, "item");
            List<String> listItems = items(list);
            List<String> $this$filterNot$iv = listItems;
            Collection destination$iv$iv = new ArrayList();
            Iterator it = $this$filterNot$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object element$iv$iv = it.next();
                String it2 = (String) element$iv$iv;
                $this$filterNot$iv = $this$filterNot$iv;
                if (!StringsKt.contains$default((CharSequence) it2, (CharSequence) item, false, 2, (Object) null)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List left = (List) destination$iv$iv;
            save(list, left);
            z = left.size() != listItems.size();
        }
        return z;
    }

    @Override // com.jarvis.core.notes.NoteBook
    public synchronized void clear(String list) {
        Intrinsics.checkNotNullParameter(list, "list");
        save(list, CollectionsKt.emptyList());
    }

    private final void save(String list, List<String> items) {
        VoiceStoresKt.voicePrefs(this.context).edit().putString("list_" + list, new JSONArray((Collection) items).toString()).apply();
    }
}
