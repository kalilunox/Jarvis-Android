package com.jarvis.core.memory;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.skills.SkillRetriever;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Memory2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u0006J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0013R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/memory/Memory2Store;", "", "<init>", "()V", "records", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/memory/Memory2Record;", "Lkotlin/collections/LinkedHashMap;", "put", "record", "find", "id", "byKind", "", "kind", "Lcom/jarvis/core/memory/Memory2Kind;", "durable", "size", "", "clearEphemeral", "", "searchRelevant", SearchIntents.EXTRA_QUERY, "limit", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Memory2Store {
    private final LinkedHashMap<String, Memory2Record> records = new LinkedHashMap<>();

    public final Memory2Record put(Memory2Record record) {
        Intrinsics.checkNotNullParameter(record, "record");
        Memory2Durability durability = Memory2Policy.INSTANCE.clampDurability(record.getKind(), record.getDurability());
        Memory2Record stored = durability == record.getDurability() ? record : record.copy((119 & 1) != 0 ? record.id : null, (119 & 2) != 0 ? record.kind : null, (119 & 4) != 0 ? record.text : null, (119 & 8) != 0 ? record.durability : durability, (119 & 16) != 0 ? record.createdAtMillis : 0L, (119 & 32) != 0 ? record.score : 0.0f, (119 & 64) != 0 ? record.metadata : null);
        this.records.put(stored.getId(), stored);
        return stored;
    }

    public final Memory2Record find(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.records.get(id);
    }

    public final List<Memory2Record> byKind(Memory2Kind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Iterable iterableValues = this.records.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Memory2Record it = (Memory2Record) element$iv$iv;
            if (it.getKind() == kind) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<Memory2Record> durable() {
        Iterable iterableValues = this.records.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Memory2Record it = (Memory2Record) element$iv$iv;
            if (it.getDurability() == Memory2Durability.DURABLE) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final int size() {
        return this.records.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean clearEphemeral$lambda$0(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((Memory2Record) it.getValue()).getDurability() == Memory2Durability.EPHEMERAL;
    }

    public final void clearEphemeral() {
        Set<Map.Entry<String, Memory2Record>> setEntrySet = this.records.entrySet();
        Intrinsics.checkNotNullExpressionValue(setEntrySet, "<get-entries>(...)");
        CollectionsKt.removeAll(setEntrySet, new Function1() { // from class: com.jarvis.core.memory.Memory2Store$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Memory2Store.clearEphemeral$lambda$0((Map.Entry) obj));
            }
        });
    }

    public static /* synthetic */ List searchRelevant$default(Memory2Store memory2Store, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 5;
        }
        return memory2Store.searchRelevant(str, i);
    }

    public final List<Memory2Record> searchRelevant(String query, int limit) {
        Iterable $this$mapNotNull$iv;
        int count$iv;
        Intrinsics.checkNotNullParameter(query, "query");
        List<String> listDefaultNormalize = SkillRetriever.INSTANCE.defaultNormalize(query);
        if (listDefaultNormalize.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        Iterable iterableValues = this.records.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$mapNotNull$iv2 = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv2) {
            Memory2Record r = (Memory2Record) element$iv$iv$iv;
            List<String> listDefaultNormalize2 = SkillRetriever.INSTANCE.defaultNormalize(r.getText());
            Pair pair = null;
            if (listDefaultNormalize2.isEmpty()) {
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
            } else {
                List<String> $this$count$iv = listDefaultNormalize;
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                if (($this$count$iv instanceof Collection) && $this$count$iv.isEmpty()) {
                    count$iv = 0;
                } else {
                    count$iv = 0;
                    for (Object element$iv : $this$count$iv) {
                        $this$count$iv = $this$count$iv;
                        String it = (String) element$iv;
                        listDefaultNormalize2 = listDefaultNormalize2;
                        if (CollectionsKt.toSet(listDefaultNormalize2).contains(it)) {
                            count$iv++;
                            if (count$iv < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                }
                int hits = count$iv;
                if (hits != 0) {
                    pair = TuplesKt.to(r, Float.valueOf(hits / listDefaultNormalize.size()));
                }
            }
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
            $this$mapNotNull$iv2 = $this$mapNotNull$iv;
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv;
        Iterable $this$map$iv = CollectionsKt.take(CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.memory.Memory2Store$searchRelevant$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Pair it2 = (Pair) t2;
                Float f = (Float) it2.getSecond();
                Pair it3 = (Pair) t;
                return ComparisonsKt.compareValues(f, (Float) it3.getSecond());
            }
        }), RangesKt.coerceIn(limit, 1, 12));
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Pair it2 = (Pair) item$iv$iv;
            destination$iv$iv2.add((Memory2Record) it2.getFirst());
        }
        return (List) destination$iv$iv2;
    }
}
