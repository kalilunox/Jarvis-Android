package com.jarvis.core.notes;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: UserData.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/notes/CustomRoutines;", "", "steps", "", "", "name", "put", "", "remove", "", "names", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CustomRoutines {
    List<String> names();

    void put(String name, List<String> steps);

    boolean remove(String name);

    List<String> steps(String name);
}
