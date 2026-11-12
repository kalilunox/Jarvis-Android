package com.jarvis.core.vision;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VisionModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/vision/LocalVisionStub;", "Lcom/jarvis/core/vision/VisionProvider;", "id", "", "ready", "", "<init>", "(Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/vision/VisionProviderKind;", "getKind", "()Lcom/jarvis/core/vision/VisionProviderKind;", "availability", "Lcom/jarvis/core/vision/VisionAvailability;", "analyze", "Lcom/jarvis/core/vision/VisionResult;", "request", "Lcom/jarvis/core/vision/VisionRequest;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LocalVisionStub implements VisionProvider {
    private final String id;
    private final VisionProviderKind kind;
    private final boolean ready;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalVisionStub() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public LocalVisionStub(String id, boolean ready) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.ready = ready;
        this.kind = VisionProviderKind.LOCAL;
    }

    public /* synthetic */ LocalVisionStub(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "vision.local" : str, (i & 2) != 0 ? true : z);
    }

    @Override // com.jarvis.core.vision.VisionProvider
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.core.vision.VisionProvider
    public VisionProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.core.vision.VisionProvider
    public VisionAvailability availability() {
        return this.ready ? VisionAvailability.AVAILABLE : VisionAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.core.vision.VisionProvider
    public VisionResult analyze(VisionRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.ready) {
            if (!(request.getImageJpeg().length == 0)) {
                return new VisionResult(request.getRequestId(), getId(), VisionStatus.SUCCESS, "local-vision:" + StringsKt.take(request.getPrompt(), 40), CollectionsKt.listOf((Object[]) new String[]{"stub", ImagesContract.LOCAL}), null, 32, null);
            }
        }
        return new VisionResult(request.getRequestId(), getId(), VisionStatus.UNAVAILABLE, null, null, "not_ready", 24, null);
    }
}
