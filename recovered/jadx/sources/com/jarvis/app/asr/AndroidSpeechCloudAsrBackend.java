package com.jarvis.app.asr;

import android.content.Context;
import android.speech.SpeechRecognizer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.core.voice.AsrSessionConfig;
import com.jarvis.core.voice.AsrSessionListener;
import com.jarvis.core.voice.StreamingAsrBackend;
import com.jarvis.core.voice.StreamingAsrSession;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.core.voice.VoiceProviderKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidSpeechCloudAsrBackend.kt */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001e"}, d2 = {"Lcom/jarvis/app/asr/AndroidSpeechCloudAsrBackend;", "Lcom/jarvis/core/voice/StreamingAsrBackend;", "appContext", "Landroid/content/Context;", "online", "Lkotlin/Function0;", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "id", "", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "model", "getModel", "supportsStreamingPartials", "getSupportsStreamingPartials", "()Z", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "openSession", "Lcom/jarvis/core/voice/StreamingAsrSession;", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AndroidSpeechCloudAsrBackend implements StreamingAsrBackend {
    private final Context appContext;
    private final String id;
    private final VoiceProviderKind kind;
    private final String model;
    private final Function0<Boolean> online;
    private final boolean supportsStreamingPartials;

    public AndroidSpeechCloudAsrBackend(Context appContext, Function0<Boolean> online) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(online, "online");
        this.appContext = appContext;
        this.online = online;
        this.id = "asr.cloud.android_speech";
        this.kind = VoiceProviderKind.CLOUD;
        this.model = "android.speech.SpeechRecognizer";
        this.supportsStreamingPartials = true;
    }

    public /* synthetic */ AndroidSpeechCloudAsrBackend(Context context, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new Function0() { // from class: com.jarvis.app.asr.AndroidSpeechCloudAsrBackend$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(AndroidSpeechCloudAsrBackend._init_$lambda$0());
            }
        } : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return true;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public VoiceProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public String getModel() {
        return this.model;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public boolean getSupportsStreamingPartials() {
        return this.supportsStreamingPartials;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public VoiceProviderAvailability availability() {
        if (!this.online.invoke().booleanValue()) {
            return VoiceProviderAvailability.UNAVAILABLE;
        }
        if (SpeechRecognizer.isRecognitionAvailable(this.appContext)) {
            return VoiceProviderAvailability.AVAILABLE;
        }
        return VoiceProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public StreamingAsrSession openSession(AsrSessionConfig config, AsrSessionListener listener) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return new AndroidSpeechSession(this.appContext, config, listener, getId(), getModel());
    }
}
