package com.jarvis.core.capability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityRegistry.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u0006J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u0006J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0013R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/capability/CapabilityRegistry;", "", "<init>", "()V", "byId", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/capability/CapabilityDescriptor;", "Lkotlin/collections/LinkedHashMap;", "byToolId", "register", "", "descriptor", "unregister", "id", "find", "findByToolId", "toolId", "all", "", "size", "", "summaries", "Lcom/jarvis/core/capability/CapabilitySummary;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CapabilityRegistry {
    private final LinkedHashMap<String, CapabilityDescriptor> byId = new LinkedHashMap<>();
    private final LinkedHashMap<String, String> byToolId = new LinkedHashMap<>();

    public final void register(CapabilityDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.byId.put(descriptor.getId(), descriptor);
        this.byToolId.put(descriptor.getBackingToolId(), descriptor.getId());
    }

    public final void unregister(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        CapabilityDescriptor d = this.byId.remove(id);
        if (d == null) {
            return;
        }
        this.byToolId.remove(d.getBackingToolId());
    }

    public final CapabilityDescriptor find(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.byId.get(id);
    }

    public final CapabilityDescriptor findByToolId(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        String it = this.byToolId.get(toolId);
        if (it != null) {
            return this.byId.get(it);
        }
        return null;
    }

    public final List<CapabilityDescriptor> all() {
        Collection<CapabilityDescriptor> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final int size() {
        return this.byId.size();
    }

    public final List<CapabilitySummary> summaries() {
        Iterable $this$map$iv = all();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CapabilityDescriptor it = (CapabilityDescriptor) item$iv$iv;
            destination$iv$iv.add(CapabilityRegistryKt.toSummary(it));
        }
        return (List) destination$iv$iv;
    }
}
