package com.jarvis.core.routines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoutineModels.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0006J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fJ\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0006R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/routines/RoutineStore;", "", "<init>", "()V", "byId", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/routines/SavedRoutine;", "Lkotlin/collections/LinkedHashMap;", "save", "", "routine", "find", "id", "all", "", "enabled", "size", "", "disable", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RoutineStore {
    private final LinkedHashMap<String, SavedRoutine> byId = new LinkedHashMap<>();

    public final void save(SavedRoutine routine) {
        Intrinsics.checkNotNullParameter(routine, "routine");
        if (!(routine.getStatus() == RoutineStatus.SAVED || routine.getStatus() == RoutineStatus.DISABLED)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this.byId.put(routine.getId(), routine);
    }

    public final SavedRoutine find(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.byId.get(id);
    }

    public final List<SavedRoutine> all() {
        Collection<SavedRoutine> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final List<SavedRoutine> enabled() {
        Iterable iterableValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SavedRoutine it = (SavedRoutine) element$iv$iv;
            if (it.getStatus() == RoutineStatus.SAVED) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final int size() {
        return this.byId.size();
    }

    public final boolean disable(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        SavedRoutine r = this.byId.get(id);
        if (r == null) {
            return false;
        }
        this.byId.put(id, r.copy((495 & 1) != 0 ? r.id : null, (495 & 2) != 0 ? r.title : null, (495 & 4) != 0 ? r.naturalLanguage : null, (495 & 8) != 0 ? r.plan : null, (495 & 16) != 0 ? r.status : RoutineStatus.DISABLED, (495 & 32) != 0 ? r.createdAtMillis : 0L, (495 & 64) != 0 ? r.approvedAtMillis : null, (495 & 128) != 0 ? r.sourceBehaviorKey : null, (495 & 256) != 0 ? r.notes : null));
        return true;
    }
}
