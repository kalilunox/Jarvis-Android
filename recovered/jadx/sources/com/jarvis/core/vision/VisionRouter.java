package com.jarvis.core.vision;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisionModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/vision/VisionRouter;", "", ImagesContract.LOCAL, "Lcom/jarvis/core/vision/VisionProvider;", "cloud", "<init>", "(Lcom/jarvis/core/vision/VisionProvider;Lcom/jarvis/core/vision/VisionProvider;)V", "select", "isOnline", "", "preferCloud", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VisionRouter {
    private final VisionProvider cloud;
    private final VisionProvider local;

    public VisionRouter(VisionProvider local, VisionProvider cloud) {
        Intrinsics.checkNotNullParameter(local, "local");
        this.local = local;
        this.cloud = cloud;
    }

    public /* synthetic */ VisionRouter(VisionProvider visionProvider, VisionProvider visionProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(visionProvider, (i & 2) != 0 ? null : visionProvider2);
    }

    public static /* synthetic */ VisionProvider select$default(VisionRouter visionRouter, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        return visionRouter.select(z, z2);
    }

    public final VisionProvider select(boolean isOnline, boolean preferCloud) {
        if (!isOnline || !preferCloud) {
            return this.local;
        }
        VisionProvider c = this.cloud;
        return (c == null || c.availability() != VisionAvailability.AVAILABLE) ? this.local : c;
    }
}
