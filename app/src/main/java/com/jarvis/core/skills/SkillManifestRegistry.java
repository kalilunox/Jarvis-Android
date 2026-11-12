package com.jarvis.core.skills;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkillRetriever.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0006J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\u0006\u0010\u0011\u001a\u00020\nR*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/skills/SkillManifestRegistry;", "", "<init>", "()V", "byId", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/skills/SkillManifest;", "Lkotlin/collections/LinkedHashMap;", "register", "", "manifest", "unregister", "id", "find", "all", "", "clear", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SkillManifestRegistry {
    private final LinkedHashMap<String, SkillManifest> byId = new LinkedHashMap<>();

    public final void register(SkillManifest manifest) {
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        this.byId.put(manifest.getId(), manifest);
    }

    public final void unregister(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.byId.remove(id);
    }

    public final SkillManifest find(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.byId.get(id);
    }

    public final List<SkillManifest> all() {
        Collection<SkillManifest> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final void clear() {
        this.byId.clear();
    }
}
