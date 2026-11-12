package com.jarvis.core.routines;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: EventRoutines.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H&J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/routines/EventRoutines;", "", "rules", "", "Lcom/jarvis/core/routines/EventRule;", "put", "", "rule", "remove", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface EventRoutines {
    void put(EventRule rule);

    void remove(EventRule rule);

    List<EventRule> rules();
}
