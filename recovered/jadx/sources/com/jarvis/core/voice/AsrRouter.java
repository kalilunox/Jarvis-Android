package com.jarvis.core.voice;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HybridVoiceStack.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/jarvis/core/voice/AsrRouter;", "", ImagesContract.LOCAL, "Lcom/jarvis/core/voice/AsrProvider;", "cloud", "<init>", "(Lcom/jarvis/core/voice/AsrProvider;Lcom/jarvis/core/voice/AsrProvider;)V", "select", "ctx", "Lcom/jarvis/core/voice/VoiceRouteContext;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AsrRouter {
    private final AsrProvider cloud;
    private final AsrProvider local;

    public AsrRouter(AsrProvider local, AsrProvider cloud) {
        Intrinsics.checkNotNullParameter(local, "local");
        this.local = local;
        this.cloud = cloud;
    }

    public /* synthetic */ AsrRouter(AsrProvider asrProvider, AsrProvider asrProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(asrProvider, (i & 2) != 0 ? null : asrProvider2);
    }

    public final AsrProvider select(VoiceRouteContext ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        if (ctx.getPrivacyRestricted() || !ctx.isOnline() || !ctx.getPreferCloud()) {
            return this.local;
        }
        AsrProvider c = this.cloud;
        return (c == null || c.availability() != VoiceProviderAvailability.AVAILABLE) ? this.local : c;
    }
}
