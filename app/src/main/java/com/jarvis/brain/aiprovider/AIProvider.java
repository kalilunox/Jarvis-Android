package com.jarvis.brain.aiprovider;

import kotlin.Metadata;

/* JADX INFO: compiled from: AIProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018À\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIProvider;", "", "id", "", "getId", "()Ljava/lang/String;", "displayName", "getDisplayName", "kind", "Lcom/jarvis/brain/aiprovider/AIProviderKind;", "getKind", "()Lcom/jarvis/brain/aiprovider/AIProviderKind;", "capabilities", "Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "getCapabilities", "()Lcom/jarvis/brain/aiprovider/AIProviderCapabilities;", "availability", "Lcom/jarvis/brain/aiprovider/AIProviderAvailability;", "warm", "", "complete", "Lcom/jarvis/brain/aiprovider/AICompletionResult;", "request", "Lcom/jarvis/brain/aiprovider/AICompletionRequest;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AIProvider {
    AIProviderAvailability availability();

    AICompletionResult complete(AICompletionRequest request);

    AIProviderCapabilities getCapabilities();

    String getDisplayName();

    String getId();

    AIProviderKind getKind();

    void warm();
}
