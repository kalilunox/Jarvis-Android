package com.jarvis.brain.aiprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AIProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0006J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\u0006\u0010\u0014\u001a\u00020\u0015R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIProviderRegistry;", "", "<init>", "()V", "byId", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/brain/aiprovider/AIProvider;", "Lkotlin/collections/LinkedHashMap;", "register", "", "provider", "unregister", "id", "find", "all", "", "available", "localProviders", "cloudProviders", "size", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AIProviderRegistry {
    private final LinkedHashMap<String, AIProvider> byId = new LinkedHashMap<>();

    public final void register(AIProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        if (StringsKt.isBlank(provider.getId())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this.byId.put(provider.getId(), provider);
    }

    public final void unregister(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.byId.remove(id);
    }

    public final AIProvider find(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.byId.get(id);
    }

    public final List<AIProvider> all() {
        Collection<AIProvider> collectionValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final List<AIProvider> available() {
        Iterable iterableValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AIProvider it = (AIProvider) element$iv$iv;
            if (it.availability() == AIProviderAvailability.AVAILABLE) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<AIProvider> localProviders() {
        Iterable iterableValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AIProvider it = (AIProvider) element$iv$iv;
            if (it.getKind() == AIProviderKind.LOCAL) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<AIProvider> cloudProviders() {
        Iterable iterableValues = this.byId.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AIProvider it = (AIProvider) element$iv$iv;
            if (it.getKind() == AIProviderKind.CLOUD) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final int size() {
        return this.byId.size();
    }
}
