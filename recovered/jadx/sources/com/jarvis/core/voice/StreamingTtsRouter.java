package com.jarvis.core.voice;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/voice/StreamingTtsRouter;", "", ImagesContract.LOCAL, "Lcom/jarvis/core/voice/StreamingTtsBackend;", "cloud", "<init>", "(Lcom/jarvis/core/voice/StreamingTtsBackend;Lcom/jarvis/core/voice/StreamingTtsBackend;)V", "decide", "Lcom/jarvis/core/voice/TtsRouteDecision;", "ctx", "Lcom/jarvis/core/voice/TtsRouteContext;", "select", "decision", "backend", "reason", "Lcom/jarvis/core/voice/TtsRouteReason;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StreamingTtsRouter {
    private final StreamingTtsBackend cloud;
    private final StreamingTtsBackend local;

    /* JADX INFO: compiled from: TtsContracts.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TtsPreference.values().length];
            try {
                iArr[TtsPreference.LOCAL_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TtsPreference.CLOUD_PREFERRED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TtsPreference.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StreamingTtsRouter(StreamingTtsBackend local, StreamingTtsBackend cloud) {
        Intrinsics.checkNotNullParameter(local, "local");
        this.local = local;
        this.cloud = cloud;
    }

    public /* synthetic */ StreamingTtsRouter(StreamingTtsBackend streamingTtsBackend, StreamingTtsBackend streamingTtsBackend2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(streamingTtsBackend, (i & 2) != 0 ? null : streamingTtsBackend2);
    }

    public final TtsRouteDecision decide(TtsRouteContext ctx) {
        VoiceProviderAvailability cloudAvail;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        VoiceProviderAvailability localAvail = this.local.availability();
        StreamingTtsBackend streamingTtsBackend = this.cloud;
        if (streamingTtsBackend == null || (cloudAvail = streamingTtsBackend.availability()) == null) {
            cloudAvail = VoiceProviderAvailability.UNAVAILABLE;
        }
        if (ctx.getPrivacyRestricted() || ctx.getPreference() == TtsPreference.LOCAL_ONLY) {
            return decision(this.local, ctx.getPrivacyRestricted() ? TtsRouteReason.PRIVACY : TtsRouteReason.LOCAL_ONLY);
        }
        if (!ctx.isOnline()) {
            return decision(this.local, TtsRouteReason.OFFLINE);
        }
        if (this.cloud == null || cloudAvail == VoiceProviderAvailability.UNAVAILABLE || !ctx.getCloudHealthy()) {
            return decision(this.local, TtsRouteReason.CLOUD_UNAVAILABLE);
        }
        switch (WhenMappings.$EnumSwitchMapping$0[ctx.getPreference().ordinal()]) {
            case 1:
                return decision(this.local, TtsRouteReason.LOCAL_ONLY);
            case 2:
                return decision(this.cloud, TtsRouteReason.USER_PREFERENCE);
            case 3:
                if (localAvail != VoiceProviderAvailability.AVAILABLE) {
                    return decision(this.cloud, TtsRouteReason.LOCAL_UNAVAILABLE);
                }
                return ctx.getPreferLowLatency() ? decision(this.local, TtsRouteReason.ONLINE_LATENCY) : decision(this.cloud, TtsRouteReason.ONLINE_QUALITY);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final StreamingTtsBackend select(TtsRouteContext ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        TtsRouteDecision d = decide(ctx);
        return (d.getKind() != VoiceProviderKind.CLOUD || this.cloud == null) ? this.local : this.cloud;
    }

    private final TtsRouteDecision decision(StreamingTtsBackend backend, TtsRouteReason reason) {
        return new TtsRouteDecision(backend.getId(), backend.getKind(), backend.getModel(), backend.getDefaultVoice(), reason, backend.getSupportsStreamingAudio());
    }
}
