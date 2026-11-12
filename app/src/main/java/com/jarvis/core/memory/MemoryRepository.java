package com.jarvis.core.memory;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MemoryRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\u000b\u001a\u00020\rH&J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/jarvis/core/memory/MemoryRepository;", "", "save", "Lcom/jarvis/core/memory/MemoryEntry;", VoiceService.EXTRA_ENTRY, "get", "id", "", "update", "delete", "", SearchIntents.EXTRA_QUERY, "", "Lcom/jarvis/core/memory/MemoryQuery;", "expire", "", "nowMillis", "", "clearAll", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface MemoryRepository {
    void clearAll();

    boolean delete(String id);

    int expire(long nowMillis);

    MemoryEntry get(String id);

    List<MemoryEntry> query(MemoryQuery query);

    MemoryEntry save(MemoryEntry entry);

    MemoryEntry update(MemoryEntry entry);

    /* JADX INFO: compiled from: MemoryRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ int expire$default(MemoryRepository memoryRepository, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: expire");
        }
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return memoryRepository.expire(j);
    }
}
