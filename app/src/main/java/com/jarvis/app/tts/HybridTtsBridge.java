package com.jarvis.app.tts;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.app.JarvisSettings;
import com.jarvis.app.VoiceService;
import com.jarvis.core.voice.HybridTtsSynthesizer;
import com.jarvis.core.voice.StreamingTtsBackend;
import com.jarvis.core.voice.StreamingTtsRouter;
import com.jarvis.core.voice.TtsAudioResult;
import com.jarvis.core.voice.TtsPreference;
import com.jarvis.core.voice.TtsRouteContext;
import com.jarvis.core.voice.TtsRouteDecision;
import com.jarvis.core.voice.TtsTelemetry;
import com.jarvis.core.voice.TtsVoiceConfig;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.tts.JarvisTts;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CloudTtsFactory.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0005J\u0006\u0010\u001e\u001a\u00020\u001fJ&\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00052\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00072\b\b\u0002\u0010%\u001a\u00020&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006'"}, d2 = {"Lcom/jarvis/app/tts/HybridTtsBridge;", "", "app", "Landroid/content/Context;", "voiceDir", "", "localEngine", "Lkotlin/Function0;", "Lcom/jarvis/tts/JarvisTts;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", ImagesContract.LOCAL, "Lcom/jarvis/core/voice/StreamingTtsBackend;", "cloud", "router", "Lcom/jarvis/core/voice/StreamingTtsRouter;", "synth", "Lcom/jarvis/core/voice/HybridTtsSynthesizer;", "value", "Lcom/jarvis/core/voice/TtsTelemetry;", "lastTelemetry", "getLastTelemetry", "()Lcom/jarvis/core/voice/TtsTelemetry;", "refresh", "", "preference", "Lcom/jarvis/core/voice/TtsPreference;", "routeContext", "Lcom/jarvis/core/voice/TtsRouteContext;", "decisionLabel", "voiceConfig", "Lcom/jarvis/core/voice/TtsVoiceConfig;", "synthesize", "Lcom/jarvis/core/voice/TtsAudioResult;", VoiceService.EXTRA_TEXT, "cancelFlag", "", "queueDepth", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HybridTtsBridge {
    private final Context app;
    private volatile StreamingTtsBackend cloud;
    private volatile TtsTelemetry lastTelemetry;
    private final StreamingTtsBackend local;
    private final Function0<JarvisTts> localEngine;
    private volatile StreamingTtsRouter router;
    private volatile HybridTtsSynthesizer synth;

    public HybridTtsBridge(Context app, String voiceDir, Function0<JarvisTts> localEngine) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(voiceDir, "voiceDir");
        Intrinsics.checkNotNullParameter(localEngine, "localEngine");
        this.app = app;
        this.localEngine = localEngine;
        this.local = CloudTtsFactory.INSTANCE.localBackend(voiceDir, this.localEngine);
        this.cloud = CloudTtsFactory.INSTANCE.cloudBackend(this.app);
        this.router = new StreamingTtsRouter(this.local, this.cloud);
        this.synth = new HybridTtsSynthesizer(this.router, this.local, this.cloud, null, 8, null);
        this.lastTelemetry = new TtsTelemetry(null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, null, 131071, null);
    }

    public final TtsTelemetry getLastTelemetry() {
        return this.lastTelemetry;
    }

    public final void refresh() {
        this.cloud = CloudTtsFactory.INSTANCE.cloudBackend(this.app);
        this.router = new StreamingTtsRouter(this.local, this.cloud);
        this.synth = new HybridTtsSynthesizer(this.router, this.local, this.cloud, null, 8, null);
    }

    public final TtsPreference preference() {
        String strTtsMode = JarvisSettings.INSTANCE.ttsMode(this.app);
        if (Intrinsics.areEqual(strTtsMode, "CLOUD_PREFERRED")) {
            return TtsPreference.CLOUD_PREFERRED;
        }
        return Intrinsics.areEqual(strTtsMode, "LOCAL_ONLY") ? TtsPreference.LOCAL_ONLY : TtsPreference.AUTO;
    }

    public final TtsRouteContext routeContext() {
        return new TtsRouteContext(CloudTtsFactory.INSTANCE.isOnline(this.app), preference(), preference() == TtsPreference.LOCAL_ONLY, this.cloud.availability() == VoiceProviderAvailability.AVAILABLE, this.local.availability() == VoiceProviderAvailability.AVAILABLE, true, "ru");
    }

    public final String decisionLabel() {
        TtsRouteDecision d = this.router.decide(routeContext());
        String lowerCase = d.getKind().name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return "selected=" + lowerCase + " provider=" + d.getProviderId() + " model=" + d.getModel() + " voice=" + d.getVoice() + " reason=" + d.getReason();
    }

    public final TtsVoiceConfig voiceConfig() {
        String strTtsCloudVoice = JarvisSettings.INSTANCE.ttsCloudVoice(this.app);
        if (StringsKt.isBlank(strTtsCloudVoice)) {
            strTtsCloudVoice = "default";
        }
        return new TtsVoiceConfig("ru-RU", strTtsCloudVoice, JarvisSettings.INSTANCE.ttsCloudModel(this.app), 1.0f, 1.0f, 22050, 0, 64, null);
    }

    public static /* synthetic */ TtsAudioResult synthesize$default(HybridTtsBridge hybridTtsBridge, String str, Function0 function0, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return hybridTtsBridge.synthesize(str, function0, i);
    }

    public final TtsAudioResult synthesize(String text, Function0<Boolean> cancelFlag, int queueDepth) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(cancelFlag, "cancelFlag");
        refresh();
        TtsTelemetry tel = new TtsTelemetry(null, null, null, null, null, false, false, "tts-" + System.nanoTime(), null, null, null, null, null, null, Integer.valueOf(queueDepth), false, null, 114559, null);
        TtsAudioResult result = this.synth.synthesizeChunk(text, routeContext(), voiceConfig(), cancelFlag, tel);
        this.lastTelemetry = tel;
        Log.i("Jarvis.Tts", tel.summary());
        return result;
    }
}
