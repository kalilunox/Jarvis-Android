package com.jarvis.core.memory;

import com.jarvis.core.companion.Sensitivity;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MemoryWriter.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/jarvis/core/memory/MemoryWriter;", "", "repository", "Lcom/jarvis/core/memory/MemoryRepository;", "clock", "Lkotlin/Function0;", "", "<init>", "(Lcom/jarvis/core/memory/MemoryRepository;Lkotlin/jvm/functions/Function0;)V", "persist", "Lcom/jarvis/core/memory/MemoryEntry;", "decision", "Lcom/jarvis/core/memory/MemoryPolicyDecision;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryWriter {
    private final Function0<Long> clock;
    private final MemoryRepository repository;

    public MemoryWriter(MemoryRepository repository, Function0<Long> clock) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.repository = repository;
        this.clock = clock;
    }

    public /* synthetic */ MemoryWriter(MemoryRepository memoryRepository, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(memoryRepository, (i & 2) != 0 ? new Function0() { // from class: com.jarvis.core.memory.MemoryWriter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0);
    }

    public final MemoryEntry persist(MemoryPolicyDecision decision) {
        Object obj;
        Intrinsics.checkNotNullParameter(decision, "decision");
        if (decision.getDisposition() == MemoryDisposition.REJECT) {
            return null;
        }
        MemoryCandidate c = decision.getCandidate();
        if (c.getSensitivity() == Sensitivity.SECRET) {
            return null;
        }
        long now = this.clock.invoke().longValue();
        Long expires = MemoryRetention.INSTANCE.expiresAt(now, c.getKind(), c.getProvenance(), c.getSensitivity(), c.getConfidence(), decision.getDisposition());
        Iterable $this$firstOrNull$iv = this.repository.query(new MemoryQuery(c.getKind(), null, null, StringsKt.take(c.getStatement(), 40), c.getPackageName(), true, false, 20, 70, null));
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv = it.next();
            MemoryEntry it2 = (MemoryEntry) element$iv;
            boolean z = true;
            if (!StringsKt.equals(it2.getContent(), c.getStatement(), true) || !Intrinsics.areEqual(it2.getPackageName(), c.getPackageName()) || it2.getStatus() == MemoryStatus.DELETED) {
                z = false;
            }
            if (z) {
                obj = element$iv;
                break;
            }
        }
        MemoryEntry existing = (MemoryEntry) obj;
        if (existing == null) {
            String id = c.getId();
            if (StringsKt.isBlank(id)) {
                String string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                id = string;
            }
            MemoryEntry it3 = new MemoryEntry(id, c.getKind(), c.getStatement(), c.getProvenance(), c.getConfidence(), c.getSensitivity(), now, now, c.getFirstSeenMillis(), c.getLastSeenMillis(), expires, c.getEvidenceCount(), CollectionsKt.takeLast(c.getEvidenceObservationIds(), 24), c.getPackageName(), c.getEntities(), MemoryStatus.ACTIVE, decision.getDisposition());
            this.repository.save(it3);
            return it3;
        }
        MemoryEntry entry = existing.copy((8559 & 1) != 0 ? existing.id : null, (8559 & 2) != 0 ? existing.type : null, (8559 & 4) != 0 ? existing.content : null, (8559 & 8) != 0 ? existing.provenance : null, (8559 & 16) != 0 ? existing.confidence : Math.max(existing.getConfidence(), c.getConfidence()), (8559 & 32) != 0 ? existing.sensitivity : null, (8559 & 64) != 0 ? existing.createdAtMillis : 0L, (8559 & 128) != 0 ? existing.updatedAtMillis : now, (8559 & 256) != 0 ? existing.firstObservedAtMillis : 0L, (8559 & 512) != 0 ? existing.lastObservedAtMillis : Math.max(existing.getLastObservedAtMillis(), c.getLastSeenMillis()), (8559 & 1024) != 0 ? existing.expiresAtMillis : expires, (8559 & 2048) != 0 ? existing.evidenceCount : Math.max(existing.getEvidenceCount(), c.getEvidenceCount()), (8559 & 4096) != 0 ? existing.evidenceObservationIds : CollectionsKt.takeLast(CollectionsKt.distinct(CollectionsKt.plus((Collection) existing.getEvidenceObservationIds(), (Iterable) c.getEvidenceObservationIds())), 24), (8559 & 8192) != 0 ? existing.packageName : null, (8559 & 16384) != 0 ? existing.entities : MapsKt.plus(existing.getEntities(), c.getEntities()), (8559 & 32768) != 0 ? existing.status : MemoryStatus.UPDATED, (8559 & 65536) != 0 ? existing.disposition : decision.getDisposition());
        this.repository.update(entry);
        return entry;
    }
}
