package com.jarvis.core.vision;

import kotlin.Metadata;

/* JADX INFO: compiled from: VisionModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/jarvis/core/vision/VisionProvider;", "", "id", "", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/vision/VisionProviderKind;", "getKind", "()Lcom/jarvis/core/vision/VisionProviderKind;", "availability", "Lcom/jarvis/core/vision/VisionAvailability;", "analyze", "Lcom/jarvis/core/vision/VisionResult;", "request", "Lcom/jarvis/core/vision/VisionRequest;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface VisionProvider {
    VisionResult analyze(VisionRequest request);

    VisionAvailability availability();

    String getId();

    VisionProviderKind getKind();
}
