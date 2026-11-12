package com.jarvis.core.voice;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.app.VoiceService;
import com.jarvis.app.ui.HomeScreen;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ4\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J \u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/voice/HybridTtsSynthesizer;", "", "router", "Lcom/jarvis/core/voice/StreamingTtsRouter;", ImagesContract.LOCAL, "Lcom/jarvis/core/voice/StreamingTtsBackend;", "cloud", "clockMs", "Lkotlin/Function0;", "", "<init>", "(Lcom/jarvis/core/voice/StreamingTtsRouter;Lcom/jarvis/core/voice/StreamingTtsBackend;Lcom/jarvis/core/voice/StreamingTtsBackend;Lkotlin/jvm/functions/Function0;)V", "lastCloudFailAtMs", "cooldownUntilMs", "synthesizeChunk", "Lcom/jarvis/core/voice/TtsAudioResult;", VoiceService.EXTRA_TEXT, "", "routeCtx", "Lcom/jarvis/core/voice/TtsRouteContext;", "voice", "Lcom/jarvis/core/voice/TtsVoiceConfig;", "cancelFlag", "", "telemetry", "Lcom/jarvis/core/voice/TtsTelemetry;", "cooldownMsFor", NotificationCompat.CATEGORY_ERROR, "Lcom/jarvis/core/voice/TtsErrorKind;", "empty", "backend", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HybridTtsSynthesizer {
    private final Function0<Long> clockMs;
    private final StreamingTtsBackend cloud;
    private volatile long cooldownUntilMs;
    private volatile long lastCloudFailAtMs;
    private final StreamingTtsBackend local;
    private final StreamingTtsRouter router;

    /* JADX INFO: compiled from: TtsContracts.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TtsErrorKind.values().length];
            try {
                iArr[TtsErrorKind.RATE_LIMIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TtsErrorKind.AUTH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public HybridTtsSynthesizer(StreamingTtsRouter router, StreamingTtsBackend local, StreamingTtsBackend cloud, Function0<Long> clockMs) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(local, "local");
        Intrinsics.checkNotNullParameter(clockMs, "clockMs");
        this.router = router;
        this.local = local;
        this.cloud = cloud;
        this.clockMs = clockMs;
    }

    public /* synthetic */ HybridTtsSynthesizer(StreamingTtsRouter streamingTtsRouter, StreamingTtsBackend streamingTtsBackend, StreamingTtsBackend streamingTtsBackend2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(streamingTtsRouter, streamingTtsBackend, (i & 4) != 0 ? null : streamingTtsBackend2, (i & 8) != 0 ? new Function0() { // from class: com.jarvis.core.voice.HybridTtsSynthesizer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0);
    }

    public final TtsAudioResult synthesizeChunk(String text, TtsRouteContext routeCtx, TtsVoiceConfig voice, Function0<Boolean> cancelFlag, TtsTelemetry telemetry) {
        String cleaned;
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(routeCtx, "routeCtx");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(cancelFlag, "cancelFlag");
        Intrinsics.checkNotNullParameter(telemetry, "telemetry");
        String cleaned2 = StringsKt.trim((CharSequence) text).toString();
        if (cleaned2.length() == 0) {
            telemetry.setTtsErrorCategory("EMPTY_TEXT");
            return new TtsAudioResult(new float[0], voice.getSampleRateHz(), this.local.getId(), this.local.getModel(), voice.getVoice(), 0L, 0L, false, false, null, TtsErrorKind.EMPTY_TEXT, 992, null);
        }
        if (cancelFlag.invoke().booleanValue()) {
            telemetry.setTtsCancelled(true);
            telemetry.setTtsErrorCategory("CANCELLED");
            return empty(this.local, voice, TtsErrorKind.CANCELLED);
        }
        long now = this.clockMs.invoke().longValue();
        TtsRouteContext ctx = now < this.cooldownUntilMs ? TtsRouteContext.copy$default(routeCtx, false, null, false, false, false, false, null, 119, null) : routeCtx;
        TtsRouteDecision decision = this.router.decide(ctx);
        telemetry.setTtsProvider(decision.getProviderId());
        telemetry.setTtsModel(decision.getModel());
        telemetry.setTtsVoice(decision.getVoice());
        String lowerCase = decision.getKind().name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        telemetry.setTtsRoute(lowerCase);
        telemetry.setTtsRouteReason(decision.getReason().name());
        telemetry.setTtsStreaming(decision.getStreamingSupported());
        StreamingTtsBackend primary = (decision.getKind() != VoiceProviderKind.CLOUD || this.cloud == null) ? this.local : this.cloud;
        long t0 = this.clockMs.invoke().longValue();
        try {
            Result.Companion companion = Result.INSTANCE;
            HybridTtsSynthesizer hybridTtsSynthesizer = this;
            cleaned = cleaned2;
            try {
                objM392constructorimpl = Result.m392constructorimpl(primary.synthesize(cleaned, voice, cancelFlag));
            } catch (Throwable th) {
                th = th;
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
            th = th2;
            cleaned = cleaned2;
        }
        if (Result.m395exceptionOrNullimpl(objM392constructorimpl) != null) {
            objM392constructorimpl = new TtsAudioResult(new float[0], voice.getSampleRateHz(), primary.getId(), primary.getModel(), voice.getVoice(), 0L, 0L, false, false, null, TtsErrorKind.UNKNOWN, 992, null);
        }
        TtsAudioResult primaryResult = (TtsAudioResult) objM392constructorimpl;
        telemetry.setTtsSetupMs(Long.valueOf(primaryResult.getSetupMs()));
        telemetry.setTtsSynthesisMs(Long.valueOf(primaryResult.getSynthesisMs()));
        if (cancelFlag.invoke().booleanValue()) {
            telemetry.setTtsCancelled(true);
            telemetry.setTtsErrorCategory("CANCELLED");
            return empty(primary, voice, TtsErrorKind.CANCELLED);
        }
        String cleaned3 = cleaned;
        if (!primaryResult.isEmpty()) {
            telemetry.setTtsFirstAudioMs(Long.valueOf(RangesKt.coerceAtLeast(this.clockMs.invoke().longValue() - t0, 0L)));
            return primaryResult;
        }
        if (primary.getKind() == VoiceProviderKind.CLOUD && this.local.availability() == VoiceProviderAvailability.AVAILABLE) {
            TtsErrorKind err = primaryResult.getErrorKind();
            if (err == null) {
                err = TtsErrorKind.PROVIDER_UNAVAILABLE;
            }
            if (err == TtsErrorKind.CANCELLED) {
                telemetry.setTtsCancelled(true);
                telemetry.setTtsErrorCategory(err.name());
                return primaryResult;
            }
            if (err == TtsErrorKind.AUTH || err == TtsErrorKind.RATE_LIMIT) {
                this.lastCloudFailAtMs = this.clockMs.invoke().longValue();
                this.cooldownUntilMs = this.lastCloudFailAtMs + cooldownMsFor(err);
            }
            telemetry.setTtsFallback(true);
            telemetry.setTtsRouteReason("FALLBACK");
            telemetry.setTtsErrorCategory(err.name());
            telemetry.setTtsProvider(this.local.getId());
            telemetry.setTtsModel(this.local.getModel());
            telemetry.setTtsVoice(this.local.getDefaultVoice());
            String lowerCase2 = "LOCAL".toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            telemetry.setTtsRoute(lowerCase2);
            if (cancelFlag.invoke().booleanValue()) {
                telemetry.setTtsCancelled(true);
                return empty(this.local, voice, TtsErrorKind.CANCELLED);
            }
            TtsAudioResult localResult = this.local.synthesize(cleaned3, voice, cancelFlag);
            if (!localResult.isEmpty()) {
                telemetry.setTtsFirstAudioMs(Long.valueOf(RangesKt.coerceAtLeast(this.clockMs.invoke().longValue() - t0, 0L)));
                Long ttsSynthesisMs = telemetry.getTtsSynthesisMs();
                telemetry.setTtsSynthesisMs(Long.valueOf((ttsSynthesisMs != null ? ttsSynthesisMs.longValue() : 0L) + localResult.getSynthesisMs()));
                return localResult.copy((1279 & 1) != 0 ? localResult.samples : null, (1279 & 2) != 0 ? localResult.sampleRateHz : 0, (1279 & 4) != 0 ? localResult.provider : null, (1279 & 8) != 0 ? localResult.model : null, (1279 & 16) != 0 ? localResult.voice : null, (1279 & 32) != 0 ? localResult.setupMs : 0L, (1279 & 64) != 0 ? localResult.synthesisMs : 0L, (1279 & 128) != 0 ? localResult.streamingSupported : false, (1279 & 256) != 0 ? localResult.fallback : true, (1279 & 512) != 0 ? localResult.fallbackReason : TtsRouteReason.FALLBACK, (1279 & 1024) != 0 ? localResult.errorKind : null);
            }
            TtsErrorKind errorKind = localResult.getErrorKind();
            if (errorKind == null) {
                errorKind = TtsErrorKind.PROVIDER_UNAVAILABLE;
            }
            telemetry.setTtsErrorCategory(errorKind.name());
            return localResult.copy((1279 & 1) != 0 ? localResult.samples : null, (1279 & 2) != 0 ? localResult.sampleRateHz : 0, (1279 & 4) != 0 ? localResult.provider : null, (1279 & 8) != 0 ? localResult.model : null, (1279 & 16) != 0 ? localResult.voice : null, (1279 & 32) != 0 ? localResult.setupMs : 0L, (1279 & 64) != 0 ? localResult.synthesisMs : 0L, (1279 & 128) != 0 ? localResult.streamingSupported : false, (1279 & 256) != 0 ? localResult.fallback : true, (1279 & 512) != 0 ? localResult.fallbackReason : TtsRouteReason.FALLBACK, (1279 & 1024) != 0 ? localResult.errorKind : null);
        }
        TtsErrorKind errorKind2 = primaryResult.getErrorKind();
        if (errorKind2 == null) {
            errorKind2 = TtsErrorKind.PROVIDER_UNAVAILABLE;
        }
        telemetry.setTtsErrorCategory(errorKind2.name());
        return primaryResult;
    }

    private final long cooldownMsFor(TtsErrorKind err) {
        switch (WhenMappings.$EnumSwitchMapping$0[err.ordinal()]) {
            case 1:
                return 60000L;
            case 2:
                return 300000L;
            default:
                return HomeScreen.CLOCK_TICK_MS;
        }
    }

    private final TtsAudioResult empty(StreamingTtsBackend backend, TtsVoiceConfig voice, TtsErrorKind err) {
        return new TtsAudioResult(new float[0], voice.getSampleRateHz(), backend.getId(), backend.getModel(), voice.getVoice(), 0L, 0L, false, false, null, err, 992, null);
    }
}
