package com.jarvis.core.voice;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.app.VoiceService;
import com.jarvis.app.proactive.PhoneStateReader;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: HybridAsrController.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001$B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0018J\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/jarvis/core/voice/HybridAsrController;", "", "router", "Lcom/jarvis/core/voice/StreamingAsrRouter;", ImagesContract.LOCAL, "Lcom/jarvis/core/voice/StreamingAsrBackend;", "cloud", "clockMs", "Lkotlin/Function0;", "", "<init>", "(Lcom/jarvis/core/voice/StreamingAsrRouter;Lcom/jarvis/core/voice/StreamingAsrBackend;Lcom/jarvis/core/voice/StreamingAsrBackend;Lkotlin/jvm/functions/Function0;)V", "lock", "active", "Lcom/jarvis/core/voice/HybridAsrController$ActiveSession;", "start", "Lcom/jarvis/core/voice/AsrTelemetry;", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", "routeCtx", "Lcom/jarvis/core/voice/AsrRouteContext;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "feedFloatPcm", "", "samples", "", "sampleRateHz", "", "finalizeUtterance", "cancel", "reason", "Lcom/jarvis/core/voice/AsrCancelReason;", "isActive", "", "currentTelemetry", "ActiveSession", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HybridAsrController {
    private ActiveSession active;
    private final Function0<Long> clockMs;
    private final StreamingAsrBackend cloud;
    private final StreamingAsrBackend local;
    private final Object lock;
    private final StreamingAsrRouter router;

    public HybridAsrController(StreamingAsrRouter router, StreamingAsrBackend local, StreamingAsrBackend cloud, Function0<Long> clockMs) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(local, "local");
        Intrinsics.checkNotNullParameter(clockMs, "clockMs");
        this.router = router;
        this.local = local;
        this.cloud = cloud;
        this.clockMs = clockMs;
        this.lock = new Object();
    }

    public /* synthetic */ HybridAsrController(StreamingAsrRouter streamingAsrRouter, StreamingAsrBackend streamingAsrBackend, StreamingAsrBackend streamingAsrBackend2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(streamingAsrRouter, streamingAsrBackend, (i & 4) != 0 ? null : streamingAsrBackend2, (i & 8) != 0 ? new Function0() { // from class: com.jarvis.core.voice.HybridAsrController$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0);
    }

    public final AsrTelemetry start(AsrSessionConfig config, AsrRouteContext routeCtx, AsrSessionListener listener) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(routeCtx, "routeCtx");
        Intrinsics.checkNotNullParameter(listener, "listener");
        cancel(AsrCancelReason.NEW_INTERACTION);
        AsrRouteDecision decision = this.router.decide(AsrRouteContext.copy$default(routeCtx, false, null, routeCtx.getPrivacyRestricted() || config.getPrivacyLocalRequired(), false, false, false, null, 123, null));
        String providerId = decision.getProviderId();
        String model = decision.getModel();
        String lowerCase = decision.getKind().name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        AsrTelemetry telemetry = new AsrTelemetry(providerId, model, lowerCase, decision.getReason().name(), this.clockMs.invoke(), null, null, null, null, null, null, null, false, null, 16352, null);
        StreamingAsrBackend primary = (decision.getKind() != VoiceProviderKind.CLOUD || this.cloud == null) ? this.local : this.cloud;
        ActiveSession session = new ActiveSession(config, decision, telemetry, primary, this.local, listener, this.clockMs, primary.getKind() == VoiceProviderKind.CLOUD && this.local.availability() == VoiceProviderAvailability.AVAILABLE);
        synchronized (this.lock) {
            this.active = session;
            Unit unit = Unit.INSTANCE;
        }
        session.open();
        return telemetry;
    }

    public static /* synthetic */ void feedFloatPcm$default(HybridAsrController hybridAsrController, float[] fArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 16000;
        }
        hybridAsrController.feedFloatPcm(fArr, i);
    }

    public final void feedFloatPcm(float[] samples, int sampleRateHz) {
        ActiveSession activeSession;
        Intrinsics.checkNotNullParameter(samples, "samples");
        synchronized (this.lock) {
            activeSession = this.active;
        }
        if (activeSession != null) {
            activeSession.feedFloatPcm(samples, sampleRateHz);
        }
    }

    public final void finalizeUtterance() {
        ActiveSession activeSession;
        synchronized (this.lock) {
            activeSession = this.active;
        }
        if (activeSession != null) {
            activeSession.finalizeUtterance();
        }
    }

    public final void cancel(AsrCancelReason reason) {
        ActiveSession s;
        Intrinsics.checkNotNullParameter(reason, "reason");
        synchronized (this.lock) {
            s = this.active;
            this.active = null;
        }
        if (s != null) {
            s.cancel(reason);
        }
    }

    public final boolean isActive() {
        boolean z;
        synchronized (this.lock) {
            ActiveSession activeSession = this.active;
            z = false;
            if (activeSession != null && activeSession.isActive()) {
                z = true;
            }
        }
        return z;
    }

    public final AsrTelemetry currentTelemetry() {
        AsrTelemetry telemetry;
        synchronized (this.lock) {
            ActiveSession activeSession = this.active;
            telemetry = activeSession != null ? activeSession.getTelemetry() : null;
        }
        return telemetry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: HybridAsrController.kt */
    @Metadata(d1 = {"\u0000\u009b\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002*\u00015\b\u0002\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0006\u0010(\u001a\u00020)J\u0016\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020)J\u000e\u00100\u001a\u00020)2\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u00020\u0011J\u0015\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0011H\u0002¢\u0006\u0002\u00107J\u0018\u00108\u001a\u00020)2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0011H\u0002J\u0010\u0010<\u001a\u00020)2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u00020)2\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u00020 H\u0002J\u0018\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020D2\u0006\u0010A\u001a\u00020 H\u0002J\u0010\u0010E\u001a\u00020)2\u0006\u00109\u001a\u00020:H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0010\u0012\f\u0012\n !*\u0004\u0018\u00010 0 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/jarvis/core/voice/HybridAsrController$ActiveSession;", "", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", "decision", "Lcom/jarvis/core/voice/AsrRouteDecision;", "telemetry", "Lcom/jarvis/core/voice/AsrTelemetry;", "primaryBackend", "Lcom/jarvis/core/voice/StreamingAsrBackend;", "localBackend", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "clockMs", "Lkotlin/Function0;", "", "allowCloudFallback", "", "<init>", "(Lcom/jarvis/core/voice/AsrSessionConfig;Lcom/jarvis/core/voice/AsrRouteDecision;Lcom/jarvis/core/voice/AsrTelemetry;Lcom/jarvis/core/voice/StreamingAsrBackend;Lcom/jarvis/core/voice/StreamingAsrBackend;Lcom/jarvis/core/voice/AsrSessionListener;Lkotlin/jvm/functions/Function0;Z)V", "getConfig", "()Lcom/jarvis/core/voice/AsrSessionConfig;", "getDecision", "()Lcom/jarvis/core/voice/AsrRouteDecision;", "getTelemetry", "()Lcom/jarvis/core/voice/AsrTelemetry;", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "finalEmitted", "fallbackStarted", "lastPartial", "Ljava/util/concurrent/atomic/AtomicReference;", "", "kotlin.jvm.PlatformType", "pcmRing", "Lcom/jarvis/core/voice/BoundedPcmRing;", PhoneStateReader.FAKE_SESSION, "Lcom/jarvis/core/voice/StreamingAsrSession;", "usingFallback", "startedAt", AbstractCircuitBreaker.PROPERTY_NAME, "", "feedFloatPcm", "samples", "", "sampleRateHz", "", "finalizeUtterance", "cancel", "reason", "Lcom/jarvis/core/voice/AsrCancelReason;", "isActive", "forwardingListener", "com/jarvis/core/voice/HybridAsrController$ActiveSession$forwardingListener$1", "primary", "(Z)Lcom/jarvis/core/voice/HybridAsrController$ActiveSession$forwardingListener$1;", "handleEvent", "result", "Lcom/jarvis/core/voice/AsrResult;", "fromPrimary", "startLocalFallback", "errorKind", "Lcom/jarvis/core/voice/AsrErrorKind;", "emitFinalOnce", "markPartial", VoiceService.EXTRA_TEXT, "emitPhase", TypedValues.CycleType.S_WAVE_PHASE, "Lcom/jarvis/core/voice/AsrSessionPhase;", "emit", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class ActiveSession {
        private final boolean allowCloudFallback;
        private final Function0<Long> clockMs;
        private final AtomicBoolean closed;
        private final AsrSessionConfig config;
        private final AsrRouteDecision decision;
        private final AtomicBoolean fallbackStarted;
        private final AtomicBoolean finalEmitted;
        private final AtomicReference<String> lastPartial;
        private final AsrSessionListener listener;
        private final StreamingAsrBackend localBackend;
        private final BoundedPcmRing pcmRing;
        private final StreamingAsrBackend primaryBackend;
        private StreamingAsrSession session;
        private final long startedAt;
        private final AsrTelemetry telemetry;
        private boolean usingFallback;

        /* JADX INFO: compiled from: HybridAsrController.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AsrSessionPhase.values().length];
                try {
                    iArr[AsrSessionPhase.PARTIAL.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[AsrSessionPhase.FINAL.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[AsrSessionPhase.COMPLETE.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[AsrSessionPhase.ERROR.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[AsrSessionPhase.CANCEL.ordinal()] = 5;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[AsrSessionPhase.START.ordinal()] = 6;
                } catch (NoSuchFieldError e6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public ActiveSession(AsrSessionConfig config, AsrRouteDecision decision, AsrTelemetry telemetry, StreamingAsrBackend primaryBackend, StreamingAsrBackend localBackend, AsrSessionListener listener, Function0<Long> clockMs, boolean allowCloudFallback) {
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(decision, "decision");
            Intrinsics.checkNotNullParameter(telemetry, "telemetry");
            Intrinsics.checkNotNullParameter(primaryBackend, "primaryBackend");
            Intrinsics.checkNotNullParameter(localBackend, "localBackend");
            Intrinsics.checkNotNullParameter(listener, "listener");
            Intrinsics.checkNotNullParameter(clockMs, "clockMs");
            this.config = config;
            this.decision = decision;
            this.telemetry = telemetry;
            this.primaryBackend = primaryBackend;
            this.localBackend = localBackend;
            this.listener = listener;
            this.clockMs = clockMs;
            this.allowCloudFallback = allowCloudFallback;
            this.closed = new AtomicBoolean(false);
            this.finalEmitted = new AtomicBoolean(false);
            this.fallbackStarted = new AtomicBoolean(false);
            this.lastPartial = new AtomicReference<>("");
            this.pcmRing = new BoundedPcmRing(this.config.getMaxBufferedMs(), this.config.getSampleRateHz());
            this.startedAt = this.clockMs.invoke().longValue();
        }

        public final AsrSessionConfig getConfig() {
            return this.config;
        }

        public final AsrRouteDecision getDecision() {
            return this.decision;
        }

        public final AsrTelemetry getTelemetry() {
            return this.telemetry;
        }

        public final void open() {
            long setupStart = this.clockMs.invoke().longValue();
            this.session = this.primaryBackend.openSession(this.config, forwardingListener(true));
            this.telemetry.setAsrSetupMs(Long.valueOf(RangesKt.coerceAtLeast(this.clockMs.invoke().longValue() - setupStart, 0L)));
            emitPhase(AsrSessionPhase.START, "");
        }

        public final void feedFloatPcm(float[] samples, int sampleRateHz) {
            Intrinsics.checkNotNullParameter(samples, "samples");
            if (this.closed.get()) {
                return;
            }
            this.pcmRing.appendFloat(samples, sampleRateHz);
            StreamingAsrSession streamingAsrSession = this.session;
            if (streamingAsrSession != null) {
                streamingAsrSession.feedFloatPcm(samples, sampleRateHz);
            }
        }

        public final void finalizeUtterance() {
            StreamingAsrSession streamingAsrSession;
            if (!this.closed.get() && (streamingAsrSession = this.session) != null) {
                streamingAsrSession.finalizeUtterance();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void cancel(AsrCancelReason reason) {
            Object objM392constructorimpl;
            Intrinsics.checkNotNullParameter(reason, "reason");
            if (this.closed.compareAndSet(false, true)) {
                StreamingAsrSession streamingAsrSession = this.session;
                if (streamingAsrSession != null) {
                    streamingAsrSession.cancel(reason);
                }
                if (this.finalEmitted.get()) {
                    return;
                }
                String requestId = this.config.getRequestId();
                String str = this.lastPartial.get();
                Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                String str2 = str;
                String asrProvider = this.telemetry.getAsrProvider();
                if (asrProvider == null) {
                    asrProvider = this.primaryBackend.getId();
                }
                String str3 = asrProvider;
                String asrModel = this.telemetry.getAsrModel();
                if (asrModel == null) {
                    asrModel = this.primaryBackend.getModel();
                }
                String str4 = asrModel;
                String sessionId = this.config.getSessionId();
                AsrSessionPhase asrSessionPhase = AsrSessionPhase.CANCEL;
                AsrErrorKind asrErrorKind = AsrErrorKind.CANCELLED;
                boolean z = this.usingFallback;
                String it = this.telemetry.getAsrFallbackReason();
                Object obj = null;
                if (it != null) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        ActiveSession activeSession = this;
                        objM392constructorimpl = Result.m392constructorimpl(AsrRouteReason.valueOf(it));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                    obj = (AsrRouteReason) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
                }
                emit(new AsrResult(requestId, str2, false, null, null, str3, str4, sessionId, asrSessionPhase, null, null, null, null, z, obj, asrErrorKind, 7704, null));
            }
        }

        public final boolean isActive() {
            return !this.closed.get();
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.jarvis.core.voice.HybridAsrController$ActiveSession$forwardingListener$1] */
        private final HybridAsrController$ActiveSession$forwardingListener$1 forwardingListener(final boolean primary) {
            return new AsrSessionListener() { // from class: com.jarvis.core.voice.HybridAsrController$ActiveSession$forwardingListener$1
                @Override // com.jarvis.core.voice.AsrSessionListener
                public void onEvent(AsrResult result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    this.this$0.handleEvent(result, primary);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleEvent(AsrResult result, boolean fromPrimary) {
            if (this.closed.get()) {
                return;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[result.getPhase().ordinal()]) {
                case 1:
                    if (StringsKt.isBlank(result.getText())) {
                        return;
                    }
                    this.lastPartial.set(result.getText());
                    markPartial(result.getText());
                    emit(result.copy((32792 & 1) != 0 ? result.requestId : this.config.getRequestId(), (32792 & 2) != 0 ? result.text : null, (32792 & 4) != 0 ? result.isFinal : false, (32792 & 8) != 0 ? result.confidence : null, (32792 & 16) != 0 ? result.language : null, (32792 & 32) != 0 ? result.provider : null, (32792 & 64) != 0 ? result.model : null, (32792 & 128) != 0 ? result.sessionId : this.config.getSessionId(), (32792 & 256) != 0 ? result.phase : null, (32792 & 512) != 0 ? result.setupMs : null, (32792 & 1024) != 0 ? result.firstPartialMs : null, (32792 & 2048) != 0 ? result.finalizeMs : null, (32792 & 4096) != 0 ? result.totalMs : null, (32792 & 8192) != 0 ? result.fallback : this.usingFallback, (32792 & 16384) != 0 ? result.fallbackReason : null, (32792 & 32768) != 0 ? result.errorKind : null));
                    return;
                case 2:
                case 3:
                    emitFinalOnce(result);
                    return;
                case 4:
                    if (fromPrimary && this.allowCloudFallback && !this.fallbackStarted.get()) {
                        AsrErrorKind errorKind = result.getErrorKind();
                        if (errorKind == null) {
                            errorKind = AsrErrorKind.INTERNAL;
                        }
                        startLocalFallback(errorKind);
                        return;
                    }
                    if (this.finalEmitted.get()) {
                        return;
                    }
                    String str = this.lastPartial.get();
                    Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                    emitFinalOnce(result.copy((32792 & 1) != 0 ? result.requestId : null, (32792 & 2) != 0 ? result.text : str, (32792 & 4) != 0 ? result.isFinal : true, (32792 & 8) != 0 ? result.confidence : null, (32792 & 16) != 0 ? result.language : null, (32792 & 32) != 0 ? result.provider : null, (32792 & 64) != 0 ? result.model : null, (32792 & 128) != 0 ? result.sessionId : null, (32792 & 256) != 0 ? result.phase : AsrSessionPhase.FINAL, (32792 & 512) != 0 ? result.setupMs : null, (32792 & 1024) != 0 ? result.firstPartialMs : null, (32792 & 2048) != 0 ? result.finalizeMs : null, (32792 & 4096) != 0 ? result.totalMs : null, (32792 & 8192) != 0 ? result.fallback : false, (32792 & 16384) != 0 ? result.fallbackReason : null, (32792 & 32768) != 0 ? result.errorKind : null));
                    return;
                case 5:
                    cancel(AsrCancelReason.PROVIDER_FAILURE);
                    return;
                case 6:
                    return;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        private final void startLocalFallback(AsrErrorKind errorKind) {
            StreamingAsrSession streamingAsrSession;
            Unit unit;
            if (this.fallbackStarted.compareAndSet(false, true)) {
                this.usingFallback = true;
                this.telemetry.setAsrFallback(true);
                this.telemetry.setAsrFallbackReason("FALLBACK");
                this.telemetry.setAsrProvider(this.localBackend.getId());
                this.telemetry.setAsrModel(this.localBackend.getModel());
                AsrTelemetry asrTelemetry = this.telemetry;
                String lowerCase = "LOCAL".toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                asrTelemetry.setAsrRoute(lowerCase);
                this.telemetry.setAsrRouteReason("FALLBACK");
                try {
                    Result.Companion companion = Result.INSTANCE;
                    ActiveSession $this$startLocalFallback_u24lambda_u240 = this;
                    StreamingAsrSession streamingAsrSession2 = $this$startLocalFallback_u24lambda_u240.session;
                    if (streamingAsrSession2 != null) {
                        streamingAsrSession2.cancel(AsrCancelReason.PROVIDER_FAILURE);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    Result.m392constructorimpl(unit);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                float[] buffered = this.pcmRing.snapshotFloat();
                this.lastPartial.set("");
                this.session = this.localBackend.openSession(this.config, forwardingListener(false));
                if (!(buffered.length == 0) && (streamingAsrSession = this.session) != null) {
                    streamingAsrSession.feedFloatPcm(buffered, this.config.getSampleRateHz());
                }
                StreamingAsrSession streamingAsrSession3 = this.session;
                if (streamingAsrSession3 != null) {
                    streamingAsrSession3.finalizeUtterance();
                }
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    ActiveSession $this$startLocalFallback_u24lambda_u241 = this;
                    Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.Asr", "fallback local after cloud error=" + errorKind + " session=" + $this$startLocalFallback_u24lambda_u241.config.getSessionId())));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th2));
                }
            }
        }

        private final void emitFinalOnce(AsrResult result) {
            Long lValueOf;
            if (this.finalEmitted.compareAndSet(false, true)) {
                this.closed.set(true);
                long now = this.clockMs.invoke().longValue();
                this.telemetry.setAsrFinalAt(Long.valueOf(now));
                AsrTelemetry asrTelemetry = this.telemetry;
                Long asrSessionStartAt = this.telemetry.getAsrSessionStartAt();
                if (asrSessionStartAt != null) {
                    long it = asrSessionStartAt.longValue();
                    lValueOf = Long.valueOf(RangesKt.coerceAtLeast(now - it, 0L));
                } else {
                    lValueOf = null;
                }
                asrTelemetry.setAsrFinalizeMs(lValueOf);
                this.telemetry.setAsrTotalMs(Long.valueOf(RangesKt.coerceAtLeast(now - this.startedAt, 0L)));
                String text = result.getText();
                if (StringsKt.isBlank(text)) {
                    text = this.lastPartial.get();
                }
                String text2 = text;
                String requestId = this.config.getRequestId();
                String sessionId = this.config.getSessionId();
                Intrinsics.checkNotNull(text2);
                AsrSessionPhase asrSessionPhase = AsrSessionPhase.FINAL;
                String asrProvider = this.telemetry.getAsrProvider();
                if (asrProvider == null) {
                    asrProvider = result.getProvider();
                }
                String str = asrProvider;
                String asrModel = this.telemetry.getAsrModel();
                if (asrModel == null) {
                    asrModel = result.getModel();
                }
                emit(result.copy((32792 & 1) != 0 ? result.requestId : requestId, (32792 & 2) != 0 ? result.text : text2, (32792 & 4) != 0 ? result.isFinal : true, (32792 & 8) != 0 ? result.confidence : null, (32792 & 16) != 0 ? result.language : null, (32792 & 32) != 0 ? result.provider : str, (32792 & 64) != 0 ? result.model : asrModel, (32792 & 128) != 0 ? result.sessionId : sessionId, (32792 & 256) != 0 ? result.phase : asrSessionPhase, (32792 & 512) != 0 ? result.setupMs : this.telemetry.getAsrSetupMs(), (32792 & 1024) != 0 ? result.firstPartialMs : this.telemetry.getAsrFirstPartialMs(), (32792 & 2048) != 0 ? result.finalizeMs : this.telemetry.getAsrFinalizeMs(), (32792 & 4096) != 0 ? result.totalMs : this.telemetry.getAsrTotalMs(), (32792 & 8192) != 0 ? result.fallback : this.usingFallback || result.getFallback(), (32792 & 16384) != 0 ? result.fallbackReason : this.usingFallback ? AsrRouteReason.FALLBACK : result.getFallbackReason(), (32792 & 32768) != 0 ? result.errorKind : null));
                String requestId2 = this.config.getRequestId();
                String sessionId2 = this.config.getSessionId();
                AsrSessionPhase asrSessionPhase2 = AsrSessionPhase.COMPLETE;
                String asrProvider2 = this.telemetry.getAsrProvider();
                if (asrProvider2 == null) {
                    asrProvider2 = result.getProvider();
                }
                String str2 = asrProvider2;
                String asrModel2 = this.telemetry.getAsrModel();
                if (asrModel2 == null) {
                    asrModel2 = result.getModel();
                }
                emit(result.copy((32792 & 1) != 0 ? result.requestId : requestId2, (32792 & 2) != 0 ? result.text : text2, (32792 & 4) != 0 ? result.isFinal : true, (32792 & 8) != 0 ? result.confidence : null, (32792 & 16) != 0 ? result.language : null, (32792 & 32) != 0 ? result.provider : str2, (32792 & 64) != 0 ? result.model : asrModel2, (32792 & 128) != 0 ? result.sessionId : sessionId2, (32792 & 256) != 0 ? result.phase : asrSessionPhase2, (32792 & 512) != 0 ? result.setupMs : null, (32792 & 1024) != 0 ? result.firstPartialMs : null, (32792 & 2048) != 0 ? result.finalizeMs : null, (32792 & 4096) != 0 ? result.totalMs : null, (32792 & 8192) != 0 ? result.fallback : this.usingFallback, (32792 & 16384) != 0 ? result.fallbackReason : null, (32792 & 32768) != 0 ? result.errorKind : null));
            }
        }

        private final void markPartial(String text) {
            Long lValueOf;
            long now = this.clockMs.invoke().longValue();
            if (this.telemetry.getAsrFirstPartialAt() == null) {
                this.telemetry.setAsrFirstPartialAt(Long.valueOf(now));
                AsrTelemetry asrTelemetry = this.telemetry;
                Long asrSessionStartAt = this.telemetry.getAsrSessionStartAt();
                if (asrSessionStartAt != null) {
                    long it = asrSessionStartAt.longValue();
                    lValueOf = Long.valueOf(RangesKt.coerceAtLeast(now - it, 0L));
                } else {
                    lValueOf = null;
                }
                asrTelemetry.setAsrFirstPartialMs(lValueOf);
            }
            if (text.length() >= 4 && this.telemetry.getAsrFirstStablePartialAt() == null) {
                this.telemetry.setAsrFirstStablePartialAt(Long.valueOf(now));
            }
        }

        private final void emitPhase(AsrSessionPhase phase, String text) {
            String requestId = this.config.getRequestId();
            String asrProvider = this.telemetry.getAsrProvider();
            if (asrProvider == null) {
                asrProvider = this.primaryBackend.getId();
            }
            String str = asrProvider;
            String asrModel = this.telemetry.getAsrModel();
            if (asrModel == null) {
                asrModel = this.primaryBackend.getModel();
            }
            emit(new AsrResult(requestId, text, false, null, null, str, asrModel, this.config.getSessionId(), phase, null, null, null, null, false, null, null, 65048, null));
        }

        private final void emit(AsrResult result) {
            this.listener.onEvent(result);
        }
    }
}
