package com.jarvis.app.asr;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.jarvis.app.JarvisSettings;
import com.jarvis.app.VoiceService;
import com.jarvis.asr.AsrSession;
import com.jarvis.core.voice.AsrCancelReason;
import com.jarvis.core.voice.AsrPreference;
import com.jarvis.core.voice.AsrResult;
import com.jarvis.core.voice.AsrRouteContext;
import com.jarvis.core.voice.AsrRouteDecision;
import com.jarvis.core.voice.AsrRouteReason;
import com.jarvis.core.voice.AsrSessionConfig;
import com.jarvis.core.voice.AsrSessionListener;
import com.jarvis.core.voice.AsrSessionPhase;
import com.jarvis.core.voice.AsrTelemetry;
import com.jarvis.core.voice.HybridAsrController;
import com.jarvis.core.voice.StreamingAsrBackend;
import com.jarvis.core.voice.StreamingAsrRouter;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.core.voice.VoiceProviderKind;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HybridAsrBridge.kt */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0005\u0018\u0000 42\u00020\u0001:\u00014B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\bJ\u0006\u0010#\u001a\u00020$J\\\u0010%\u001a\u00020\b2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u001d0'26\u0010(\u001a2\u0012\u0013\u0012\u00110$¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u001d0)2\b\b\u0002\u0010\"\u001a\u00020\bJ\u000e\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u00020\u001dJ\u0006\u00102\u001a\u00020\u001dJ\u0006\u00103\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u00065"}, d2 = {"Lcom/jarvis/app/asr/HybridAsrBridge;", "", "app", "Landroid/content/Context;", "localSessionFactory", "Lkotlin/Function0;", "Lcom/jarvis/asr/AsrSession;", "localReady", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "localBackend", "Lcom/jarvis/core/voice/StreamingAsrBackend;", "cloudBackend", "router", "Lcom/jarvis/core/voice/StreamingAsrRouter;", "controller", "Lcom/jarvis/core/voice/HybridAsrController;", "turnActive", "Ljava/util/concurrent/atomic/AtomicBoolean;", "value", "Lcom/jarvis/core/voice/AsrTelemetry;", "lastTelemetry", "getLastTelemetry", "()Lcom/jarvis/core/voice/AsrTelemetry;", "cloudOwnsMic", "getCloudOwnsMic", "()Z", "refreshCloud", "", "preference", "Lcom/jarvis/core/voice/AsrPreference;", "routeContext", "Lcom/jarvis/core/voice/AsrRouteContext;", "privacyLocalRequired", "decisionLabel", "", "beginTurn", "onPartial", "Lkotlin/Function1;", "onFinal", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", VoiceService.EXTRA_TEXT, "telemetry", "feed", "samples", "", "finalizeTurn", "cancelTurn", "isTurnActive", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HybridAsrBridge {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<String> DEFAULT_HINTS = CollectionsKt.listOf((Object[]) new String[]{"Telegram", "WhatsApp", "YouTube", "браузер", "Джарвис"});
    private static final String TAG = "Jarvis.Asr";
    private final Context app;
    private volatile StreamingAsrBackend cloudBackend;
    private volatile boolean cloudOwnsMic;
    private volatile HybridAsrController controller;
    private volatile AsrTelemetry lastTelemetry;
    private final StreamingAsrBackend localBackend;
    private final Function0<Boolean> localReady;
    private final Function0<AsrSession> localSessionFactory;
    private volatile StreamingAsrRouter router;
    private final AtomicBoolean turnActive;

    /* JADX INFO: compiled from: HybridAsrBridge.kt */
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
                iArr[AsrSessionPhase.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AsrSessionPhase.CANCEL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HybridAsrBridge(Context app, Function0<? extends AsrSession> localSessionFactory, Function0<Boolean> localReady) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(localSessionFactory, "localSessionFactory");
        Intrinsics.checkNotNullParameter(localReady, "localReady");
        this.app = app;
        this.localSessionFactory = localSessionFactory;
        this.localReady = localReady;
        this.localBackend = CloudAsrFactory.INSTANCE.localBackend(this.localSessionFactory, this.localReady);
        this.cloudBackend = CloudAsrFactory.INSTANCE.cloudBackend(this.app);
        this.router = CloudAsrFactory.INSTANCE.router(this.localBackend, this.cloudBackend);
        this.controller = new HybridAsrController(this.router, this.localBackend, this.cloudBackend, new Function0() { // from class: com.jarvis.app.asr.HybridAsrBridge$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(SystemClock.elapsedRealtime());
            }
        });
        this.turnActive = new AtomicBoolean(false);
    }

    public final AsrTelemetry getLastTelemetry() {
        return this.lastTelemetry;
    }

    public final boolean getCloudOwnsMic() {
        return this.cloudOwnsMic;
    }

    public final void refreshCloud() {
        this.cloudBackend = CloudAsrFactory.INSTANCE.cloudBackend(this.app);
        this.router = CloudAsrFactory.INSTANCE.router(this.localBackend, this.cloudBackend);
        this.controller = new HybridAsrController(this.router, this.localBackend, this.cloudBackend, new Function0() { // from class: com.jarvis.app.asr.HybridAsrBridge$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(SystemClock.elapsedRealtime());
            }
        });
    }

    public final AsrPreference preference() {
        String strAsrMode = JarvisSettings.INSTANCE.asrMode(this.app);
        if (Intrinsics.areEqual(strAsrMode, "CLOUD_PREFERRED")) {
            return AsrPreference.CLOUD_PREFERRED;
        }
        return Intrinsics.areEqual(strAsrMode, "LOCAL_ONLY") ? AsrPreference.LOCAL_ONLY : AsrPreference.AUTO;
    }

    public static /* synthetic */ AsrRouteContext routeContext$default(HybridAsrBridge hybridAsrBridge, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return hybridAsrBridge.routeContext(z);
    }

    public final AsrRouteContext routeContext(boolean privacyLocalRequired) {
        return new AsrRouteContext(CloudAsrFactory.INSTANCE.isOnline(this.app), preference(), privacyLocalRequired || preference() == AsrPreference.LOCAL_ONLY, this.cloudBackend.availability() == VoiceProviderAvailability.AVAILABLE, this.localBackend.availability() == VoiceProviderAvailability.AVAILABLE, true, "ru");
    }

    public final String decisionLabel() {
        AsrRouteDecision d = this.router.decide(routeContext$default(this, false, 1, null));
        String lowerCase = d.getKind().name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return "selected=" + lowerCase + " provider=" + d.getProviderId() + " model=" + d.getModel() + " reason=" + d.getReason();
    }

    public static /* synthetic */ boolean beginTurn$default(HybridAsrBridge hybridAsrBridge, Function1 function1, Function2 function2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return hybridAsrBridge.beginTurn(function1, function2, z);
    }

    public final boolean beginTurn(final Function1<? super String, Unit> onPartial, final Function2<? super String, ? super AsrTelemetry, Unit> onFinal, boolean privacyLocalRequired) {
        Intrinsics.checkNotNullParameter(onPartial, "onPartial");
        Intrinsics.checkNotNullParameter(onFinal, "onFinal");
        cancelTurn();
        refreshCloud();
        AsrRouteContext ctx = routeContext(privacyLocalRequired);
        final AsrRouteDecision decision = this.router.decide(ctx);
        Log.i(TAG, decisionLabel());
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String requestId = "asr-" + StringsKt.take(string, 8);
        String string2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        String sessionId = "s-" + StringsKt.take(string2, 8);
        boolean needsMicHandoff = decision.getKind() == VoiceProviderKind.CLOUD && Intrinsics.areEqual(decision.getProviderId(), "asr.cloud.android_speech");
        this.cloudOwnsMic = needsMicHandoff;
        this.turnActive.set(true);
        AsrSessionListener listener = new AsrSessionListener() { // from class: com.jarvis.app.asr.HybridAsrBridge$$ExternalSyntheticLambda0
            @Override // com.jarvis.core.voice.AsrSessionListener
            public final void onEvent(AsrResult asrResult) {
                HybridAsrBridge.beginTurn$lambda$0(onPartial, this, decision, onFinal, asrResult);
            }
        };
        this.lastTelemetry = this.controller.start(new AsrSessionConfig(requestId, sessionId, "ru-RU", 0, 0, DEFAULT_HINTS, privacyLocalRequired, 24, null), ctx, listener);
        return needsMicHandoff;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginTurn$lambda$0(Function1 $onPartial, HybridAsrBridge this$0, AsrRouteDecision $decision, Function2 $onFinal, AsrResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        switch (WhenMappings.$EnumSwitchMapping$0[result.getPhase().ordinal()]) {
            case 1:
                if (!StringsKt.isBlank(result.getText())) {
                    $onPartial.invoke(result.getText());
                }
                break;
            case 2:
                AsrTelemetry tel = this$0.controller.currentTelemetry();
                if (tel == null) {
                    String provider = result.getProvider();
                    String model = result.getModel();
                    String lowerCase = $decision.getKind().name().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    String strName = $decision.getReason().name();
                    boolean fallback = result.getFallback();
                    AsrRouteReason fallbackReason = result.getFallbackReason();
                    tel = new AsrTelemetry(provider, model, lowerCase, strName, null, null, null, null, result.getSetupMs(), result.getFirstPartialMs(), result.getFinalizeMs(), result.getTotalMs(), fallback, fallbackReason != null ? fallbackReason.name() : null, 240, null);
                }
                this$0.lastTelemetry = tel;
                Log.i(TAG, tel.summary());
                this$0.turnActive.set(false);
                this$0.cloudOwnsMic = false;
                if (!StringsKt.isBlank(result.getText())) {
                    $onFinal.invoke(result.getText(), tel);
                }
                break;
            case 3:
            case 4:
                this$0.turnActive.set(false);
                this$0.cloudOwnsMic = false;
                break;
            default:
                break;
        }
    }

    public final void feed(float[] samples) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        if (!this.turnActive.get() || this.cloudOwnsMic) {
            return;
        }
        HybridAsrController.feedFloatPcm$default(this.controller, samples, 0, 2, null);
    }

    public final void finalizeTurn() {
        if (this.turnActive.get()) {
            this.controller.finalizeUtterance();
        }
    }

    public final void cancelTurn() {
        this.turnActive.set(false);
        this.cloudOwnsMic = false;
        this.controller.cancel(AsrCancelReason.NEW_INTERACTION);
    }

    public final boolean isTurnActive() {
        return this.turnActive.get();
    }

    /* JADX INFO: compiled from: HybridAsrBridge.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/jarvis/app/asr/HybridAsrBridge$Companion;", "", "<init>", "()V", "TAG", "", "DEFAULT_HINTS", "", "getDEFAULT_HINTS", "()Ljava/util/List;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getDEFAULT_HINTS() {
            return HybridAsrBridge.DEFAULT_HINTS;
        }
    }
}
