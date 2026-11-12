package com.jarvis.core.memory;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: InMemoryMemoryRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00102\u0006\u0010\u000f\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/memory/InMemoryMemoryRepository;", "Lcom/jarvis/core/memory/MemoryRepository;", "<init>", "()V", "map", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/jarvis/core/memory/MemoryEntry;", "save", VoiceService.EXTRA_ENTRY, "get", "id", "update", "delete", "", SearchIntents.EXTRA_QUERY, "", "Lcom/jarvis/core/memory/MemoryQuery;", "expire", "", "nowMillis", "", "clearAll", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InMemoryMemoryRepository implements MemoryRepository {
    private final ConcurrentHashMap<String, MemoryEntry> map = new ConcurrentHashMap<>();

    @Override // com.jarvis.core.memory.MemoryRepository
    public MemoryEntry save(MemoryEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        this.map.put(entry.getId(), entry);
        return entry;
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public MemoryEntry get(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.map.get(id);
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public MemoryEntry update(MemoryEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (!this.map.containsKey(entry.getId())) {
            return null;
        }
        this.map.put(entry.getId(), entry);
        return entry;
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public boolean delete(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        MemoryEntry cur = this.map.get(id);
        if (cur == null) {
            return false;
        }
        this.map.put(id, cur.copy((8559 & 1) != 0 ? cur.id : null, (8559 & 2) != 0 ? cur.type : null, (8559 & 4) != 0 ? cur.content : null, (8559 & 8) != 0 ? cur.provenance : null, (8559 & 16) != 0 ? cur.confidence : 0.0f, (8559 & 32) != 0 ? cur.sensitivity : null, (8559 & 64) != 0 ? cur.createdAtMillis : 0L, (8559 & 128) != 0 ? cur.updatedAtMillis : System.currentTimeMillis(), (8559 & 256) != 0 ? cur.firstObservedAtMillis : 0L, (8559 & 512) != 0 ? cur.lastObservedAtMillis : 0L, (8559 & 1024) != 0 ? cur.expiresAtMillis : null, (8559 & 2048) != 0 ? cur.evidenceCount : 0, (8559 & 4096) != 0 ? cur.evidenceObservationIds : null, (8559 & 8192) != 0 ? cur.packageName : null, (8559 & 16384) != 0 ? cur.entities : null, (8559 & 32768) != 0 ? cur.status : MemoryStatus.DELETED, (8559 & 65536) != 0 ? cur.disposition : null));
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0023  */
    @Override // com.jarvis.core.memory.MemoryRepository
    public List<MemoryEntry> query(final MemoryQuery query) {
        final String needle;
        Intrinsics.checkNotNullParameter(query, "query");
        String textContains = query.getTextContains();
        if (textContains != null) {
            String lowerCase = textContains.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                needle = StringsKt.trim((CharSequence) lowerCase).toString();
            } else {
                needle = null;
            }
        } else {
            needle = null;
        }
        if (needle == null) {
            needle = "";
        }
        Collection<MemoryEntry> collectionValues = this.map.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        Sequence $this$sortedByDescending$iv = SequencesKt.filter(CollectionsKt.asSequence(collectionValues), new Function1() { // from class: com.jarvis.core.memory.InMemoryMemoryRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InMemoryMemoryRepository.query$lambda$0(query, needle, (MemoryEntry) obj));
            }
        });
        Sequence $this$sortedByDescending$iv2 = SequencesKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.memory.InMemoryMemoryRepository$query$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                MemoryEntry it = (MemoryEntry) t2;
                MemoryEntry it2 = (MemoryEntry) t;
                return ComparisonsKt.compareValues(Long.valueOf(it.getUpdatedAtMillis()), Long.valueOf(it2.getUpdatedAtMillis()));
            }
        });
        int $i$f$sortedByDescending = query.getLimit();
        return SequencesKt.toList(SequencesKt.take($this$sortedByDescending$iv2, RangesKt.coerceAtLeast($i$f$sortedByDescending, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean query$lambda$0(MemoryQuery $query, String $needle, MemoryEntry e) {
        Intrinsics.checkNotNullParameter(e, "e");
        if (!$query.getIncludeDeleted() && e.getStatus() == MemoryStatus.DELETED) {
            return false;
        }
        if (!$query.getIncludeExpired() && e.getStatus() == MemoryStatus.EXPIRED) {
            return false;
        }
        if ($query.getType() != null && e.getType() != $query.getType()) {
            return false;
        }
        if ($query.getStatus() != null && e.getStatus() != $query.getStatus()) {
            return false;
        }
        if ($query.getProvenance() != null && e.getProvenance() != $query.getProvenance()) {
            return false;
        }
        if ($query.getPackageName() != null && !Intrinsics.areEqual(e.getPackageName(), $query.getPackageName())) {
            return false;
        }
        if ($needle.length() > 0) {
            String lowerCase = e.getContent().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (!StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) $needle, false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public int expire(long nowMillis) {
        this = this;
        int n = 0;
        for (Map.Entry<String, MemoryEntry> entry : this.map.entrySet()) {
            String id = entry.getKey();
            MemoryEntry e = entry.getValue();
            if (e.getStatus() != MemoryStatus.DELETED && e.getStatus() != MemoryStatus.EXPIRED) {
                Long exp = e.getExpiresAtMillis();
                if (exp != null && nowMillis >= exp.longValue()) {
                    this.map.put(id, e.copy((8559 & 1) != 0 ? e.id : null, (8559 & 2) != 0 ? e.type : null, (8559 & 4) != 0 ? e.content : null, (8559 & 8) != 0 ? e.provenance : null, (8559 & 16) != 0 ? e.confidence : 0.0f, (8559 & 32) != 0 ? e.sensitivity : null, (8559 & 64) != 0 ? e.createdAtMillis : 0L, (8559 & 128) != 0 ? e.updatedAtMillis : nowMillis, (8559 & 256) != 0 ? e.firstObservedAtMillis : 0L, (8559 & 512) != 0 ? e.lastObservedAtMillis : 0L, (8559 & 1024) != 0 ? e.expiresAtMillis : null, (8559 & 2048) != 0 ? e.evidenceCount : 0, (8559 & 4096) != 0 ? e.evidenceObservationIds : null, (8559 & 8192) != 0 ? e.packageName : null, (8559 & 16384) != 0 ? e.entities : null, (8559 & 32768) != 0 ? e.status : MemoryStatus.EXPIRED, (8559 & 65536) != 0 ? e.disposition : null));
                    n++;
                } else {
                    long staleAfter = MemoryRetention.INSTANCE.staleAfterMillis(e.getType(), e.getProvenance());
                    if (e.getStatus() == MemoryStatus.ACTIVE && nowMillis - e.getLastObservedAtMillis() >= staleAfter) {
                        this.map.put(id, e.copy((8559 & 1) != 0 ? e.id : null, (8559 & 2) != 0 ? e.type : null, (8559 & 4) != 0 ? e.content : null, (8559 & 8) != 0 ? e.provenance : null, (8559 & 16) != 0 ? e.confidence : 0.0f, (8559 & 32) != 0 ? e.sensitivity : null, (8559 & 64) != 0 ? e.createdAtMillis : 0L, (8559 & 128) != 0 ? e.updatedAtMillis : nowMillis, (8559 & 256) != 0 ? e.firstObservedAtMillis : 0L, (8559 & 512) != 0 ? e.lastObservedAtMillis : 0L, (8559 & 1024) != 0 ? e.expiresAtMillis : null, (8559 & 2048) != 0 ? e.evidenceCount : 0, (8559 & 4096) != 0 ? e.evidenceObservationIds : null, (8559 & 8192) != 0 ? e.packageName : null, (8559 & 16384) != 0 ? e.entities : null, (8559 & 32768) != 0 ? e.status : MemoryStatus.STALE, (8559 & 65536) != 0 ? e.disposition : null));
                        n++;
                    }
                }
            }
        }
        return n;
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public void clearAll() {
        this.map.clear();
    }
}
