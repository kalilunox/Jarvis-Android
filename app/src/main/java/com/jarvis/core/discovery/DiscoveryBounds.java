package com.jarvis.core.discovery;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatalogToolDiscovery.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/jarvis/core/discovery/DiscoveryBounds;", "", "<init>", "()V", "ensureNotFullCatalog", "", "result", "Lcom/jarvis/core/discovery/DiscoveryResult;", "catalogSize", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DiscoveryBounds {
    public static final DiscoveryBounds INSTANCE = new DiscoveryBounds();

    private DiscoveryBounds() {
    }

    public final void ensureNotFullCatalog(DiscoveryResult result, int catalogSize) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (!(result.getItems().size() <= 12)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (catalogSize > 12) {
            if (!(result.getItems().size() < catalogSize)) {
                throw new IllegalArgumentException("Discovery leaked full catalog".toString());
            }
        }
    }
}
