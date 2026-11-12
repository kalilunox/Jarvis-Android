package com.jarvis.core.memory;

import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: MemoryRecall.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fJ\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/memory/MemoryRecall;", "", "repository", "Lcom/jarvis/core/memory/MemoryRepository;", "<init>", "(Lcom/jarvis/core/memory/MemoryRepository;)V", "byType", "", "Lcom/jarvis/core/memory/MemoryEntry;", "type", "Lcom/jarvis/core/memory/MemoryKind;", "limit", "", "byStatus", "status", "Lcom/jarvis/core/memory/MemoryStatus;", "active", "temporary", "staleOrExpired", "textSearch", SearchIntents.EXTRA_QUERY, "", "recent", "preferences", "explain", "id", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryRecall {
    private final MemoryRepository repository;

    public MemoryRecall(MemoryRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public static /* synthetic */ List byType$default(MemoryRecall memoryRecall, MemoryKind memoryKind, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 30;
        }
        return memoryRecall.byType(memoryKind, i);
    }

    public final List<MemoryEntry> byType(MemoryKind type, int limit) {
        Intrinsics.checkNotNullParameter(type, "type");
        return this.repository.query(new MemoryQuery(type, null, null, null, null, false, false, limit, 126, null));
    }

    public static /* synthetic */ List byStatus$default(MemoryRecall memoryRecall, MemoryStatus memoryStatus, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 30;
        }
        return memoryRecall.byStatus(memoryStatus, i);
    }

    public final List<MemoryEntry> byStatus(MemoryStatus status, int limit) {
        Intrinsics.checkNotNullParameter(status, "status");
        return this.repository.query(new MemoryQuery(null, status, null, null, null, true, false, limit, 93, null));
    }

    public static /* synthetic */ List active$default(MemoryRecall memoryRecall, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 40;
        }
        return memoryRecall.active(i);
    }

    public final List<MemoryEntry> active(int limit) {
        return CollectionsKt.plus((Collection) this.repository.query(new MemoryQuery(null, MemoryStatus.ACTIVE, null, null, null, false, false, limit, 125, null)), (Iterable) this.repository.query(new MemoryQuery(null, MemoryStatus.UPDATED, null, null, null, false, false, limit, 125, null)));
    }

    public static /* synthetic */ List temporary$default(MemoryRecall memoryRecall, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 30;
        }
        return memoryRecall.temporary(i);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x004a  */
    public final List<MemoryEntry> temporary(int limit) {
        boolean z;
        Iterable $this$filter$iv = this.repository.query(new MemoryQuery(null, null, null, null, null, false, false, 200, WorkQueueKt.MASK, null));
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            MemoryEntry it = (MemoryEntry) element$iv$iv;
            if (it.getDisposition() == MemoryDisposition.TEMPORARY) {
                z = MemoryEntry.isAlive$default(it, 0L, 1, null);
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.take((List) destination$iv$iv, limit);
    }

    public static /* synthetic */ List staleOrExpired$default(MemoryRecall memoryRecall, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 30;
        }
        return memoryRecall.staleOrExpired(i);
    }

    public final List<MemoryEntry> staleOrExpired(int limit) {
        return CollectionsKt.plus((Collection) this.repository.query(new MemoryQuery(null, MemoryStatus.STALE, null, null, null, true, false, limit, 93, null)), (Iterable) this.repository.query(new MemoryQuery(null, MemoryStatus.EXPIRED, null, null, null, true, false, limit, 93, null)));
    }

    public static /* synthetic */ List textSearch$default(MemoryRecall memoryRecall, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 20;
        }
        return memoryRecall.textSearch(str, i);
    }

    public final List<MemoryEntry> textSearch(String query, int limit) {
        Intrinsics.checkNotNullParameter(query, "query");
        return this.repository.query(new MemoryQuery(null, null, null, query, null, false, false, limit, 119, null));
    }

    public static /* synthetic */ List recent$default(MemoryRecall memoryRecall, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 20;
        }
        return memoryRecall.recent(i);
    }

    public final List<MemoryEntry> recent(int limit) {
        return this.repository.query(new MemoryQuery(null, null, null, null, null, false, false, limit, WorkQueueKt.MASK, null));
    }

    public final List<MemoryEntry> preferences() {
        return byType$default(this, MemoryKind.PREFERENCE, 0, 2, null);
    }

    public final String explain(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        MemoryEntry e = this.repository.get(id);
        if (e == null) {
            return null;
        }
        StringBuilder $this$explain_u24lambda_u240 = new StringBuilder();
        $this$explain_u24lambda_u240.append(e.getContent()).append('\n');
        $this$explain_u24lambda_u240.append("provenance=" + e.getProvenance() + " type=" + e.getType() + " status=" + e.getStatus()).append('\n');
        $this$explain_u24lambda_u240.append("confidence=" + e.getConfidence() + " sensitivity=" + e.getSensitivity()).append('\n');
        $this$explain_u24lambda_u240.append("evidence=" + e.getEvidenceCount() + " ids=" + CollectionsKt.take(e.getEvidenceObservationIds(), 8)).append('\n');
        $this$explain_u24lambda_u240.append("first=" + e.getFirstObservedAtMillis() + " last=" + e.getLastObservedAtMillis()).append('\n');
        Object expiresAtMillis = e.getExpiresAtMillis();
        if (expiresAtMillis == null) {
            expiresAtMillis = "never";
        }
        $this$explain_u24lambda_u240.append("expires=" + expiresAtMillis).append('\n');
        if (!e.getEntities().isEmpty()) {
            $this$explain_u24lambda_u240.append("entities=" + e.getEntities()).append('\n');
        }
        return StringsKt.trim((CharSequence) $this$explain_u24lambda_u240.toString()).toString();
    }
}
