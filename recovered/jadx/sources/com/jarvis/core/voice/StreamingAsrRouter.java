package com.jarvis.core.voice;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HybridVoiceStack.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/voice/StreamingAsrRouter;", "", ImagesContract.LOCAL, "Lcom/jarvis/core/voice/StreamingAsrBackend;", "cloud", "<init>", "(Lcom/jarvis/core/voice/StreamingAsrBackend;Lcom/jarvis/core/voice/StreamingAsrBackend;)V", "decide", "Lcom/jarvis/core/voice/AsrRouteDecision;", "ctx", "Lcom/jarvis/core/voice/AsrRouteContext;", "select", "decision", "backend", "reason", "Lcom/jarvis/core/voice/AsrRouteReason;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StreamingAsrRouter {
    private final StreamingAsrBackend cloud;
    private final StreamingAsrBackend local;

    /* JADX INFO: compiled from: HybridVoiceStack.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AsrPreference.values().length];
            try {
                iArr[AsrPreference.LOCAL_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AsrPreference.CLOUD_PREFERRED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AsrPreference.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StreamingAsrRouter(StreamingAsrBackend local, StreamingAsrBackend cloud) {
        Intrinsics.checkNotNullParameter(local, "local");
        this.local = local;
        this.cloud = cloud;
    }

    public /* synthetic */ StreamingAsrRouter(StreamingAsrBackend streamingAsrBackend, StreamingAsrBackend streamingAsrBackend2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(streamingAsrBackend, (i & 2) != 0 ? null : streamingAsrBackend2);
    }

    public final AsrRouteDecision decide(AsrRouteContext ctx) {
        VoiceProviderAvailability cloudAvail;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        VoiceProviderAvailability localAvail = this.local.availability();
        StreamingAsrBackend streamingAsrBackend = this.cloud;
        if (streamingAsrBackend == null || (cloudAvail = streamingAsrBackend.availability()) == null) {
            cloudAvail = VoiceProviderAvailability.UNAVAILABLE;
        }
        if (ctx.getPrivacyRestricted() || ctx.getPreference() == AsrPreference.LOCAL_ONLY) {
            StreamingAsrBackend streamingAsrBackend2 = this.local;
            AsrRouteReason asrRouteReason = AsrRouteReason.PRIVACY;
            if (!ctx.getPrivacyRestricted()) {
                asrRouteReason = null;
            }
            if (asrRouteReason == null) {
                asrRouteReason = AsrRouteReason.LOCAL_ONLY;
            }
            return decision(streamingAsrBackend2, asrRouteReason);
        }
        if (!ctx.isOnline()) {
            return decision(this.local, AsrRouteReason.OFFLINE);
        }
        if (this.cloud == null || cloudAvail == VoiceProviderAvailability.UNAVAILABLE || !ctx.getCloudHealthy()) {
            return decision(this.local, AsrRouteReason.CLOUD_UNAVAILABLE);
        }
        if (localAvail == VoiceProviderAvailability.UNAVAILABLE && !ctx.getLocalHealthy()) {
            return decision(this.cloud, AsrRouteReason.LOCAL_UNAVAILABLE);
        }
        switch (WhenMappings.$EnumSwitchMapping$0[ctx.getPreference().ordinal()]) {
            case 1:
                return decision(this.local, AsrRouteReason.LOCAL_ONLY);
            case 2:
                if (cloudAvail == VoiceProviderAvailability.AVAILABLE && ctx.getCloudHealthy()) {
                    StreamingAsrBackend streamingAsrBackend3 = this.cloud;
                    Intrinsics.checkNotNull(streamingAsrBackend3);
                    return decision(streamingAsrBackend3, AsrRouteReason.USER_PREFERENCE);
                }
                return decision(this.local, AsrRouteReason.CLOUD_UNAVAILABLE);
            case 3:
                if (cloudAvail != VoiceProviderAvailability.AVAILABLE || !ctx.getCloudHealthy()) {
                    return decision(this.local, AsrRouteReason.CLOUD_UNAVAILABLE);
                }
                if (localAvail != VoiceProviderAvailability.AVAILABLE) {
                    StreamingAsrBackend streamingAsrBackend4 = this.cloud;
                    Intrinsics.checkNotNull(streamingAsrBackend4);
                    return decision(streamingAsrBackend4, AsrRouteReason.LOCAL_UNAVAILABLE);
                }
                if (ctx.getPreferLowLatency()) {
                    return decision(this.local, AsrRouteReason.ONLINE_LATENCY);
                }
                StreamingAsrBackend streamingAsrBackend5 = this.cloud;
                Intrinsics.checkNotNull(streamingAsrBackend5);
                return decision(streamingAsrBackend5, AsrRouteReason.ONLINE_QUALITY);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final StreamingAsrBackend select(AsrRouteContext ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        AsrRouteDecision d = decide(ctx);
        return (d.getKind() != VoiceProviderKind.CLOUD || this.cloud == null) ? this.local : this.cloud;
    }

    private final AsrRouteDecision decision(StreamingAsrBackend backend, AsrRouteReason reason) {
        return new AsrRouteDecision(backend.getId(), backend.getKind(), backend.getModel(), reason, backend.getSupportsStreamingPartials());
    }
}
