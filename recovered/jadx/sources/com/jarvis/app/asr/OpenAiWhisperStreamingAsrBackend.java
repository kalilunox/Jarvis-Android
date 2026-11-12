package com.jarvis.app.asr;

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
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: OpenAiWhisperStreamingAsrBackend.kt */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$Ba\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0012R\u0014\u0010\u0019\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lcom/jarvis/app/asr/OpenAiWhisperStreamingAsrBackend;", "Lcom/jarvis/core/voice/StreamingAsrBackend;", "baseUrlProvider", "Lkotlin/Function0;", "", "apiKeyProvider", "modelProvider", "online", "", "interimIntervalMs", "", "connectTimeoutMs", "", "readTimeoutMs", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JII)V", "id", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "model", "getModel", "supportsStreamingPartials", "getSupportsStreamingPartials", "()Z", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "openSession", "Lcom/jarvis/core/voice/StreamingAsrSession;", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OpenAiWhisperStreamingAsrBackend implements StreamingAsrBackend {
    public static final String DEFAULT_BASE_URL = "https://api.openai.com/v1";
    public static final String DEFAULT_MODEL = "whisper-1";
    private final Function0<String> apiKeyProvider;
    private final Function0<String> baseUrlProvider;
    private final int connectTimeoutMs;
    private final String id;
    private final long interimIntervalMs;
    private final VoiceProviderKind kind;
    private final Function0<String> modelProvider;
    private final Function0<Boolean> online;
    private final int readTimeoutMs;
    private final boolean supportsStreamingPartials;

    public OpenAiWhisperStreamingAsrBackend(Function0<String> baseUrlProvider, Function0<String> apiKeyProvider, Function0<String> modelProvider, Function0<Boolean> online, long interimIntervalMs, int connectTimeoutMs, int readTimeoutMs) {
        Intrinsics.checkNotNullParameter(baseUrlProvider, "baseUrlProvider");
        Intrinsics.checkNotNullParameter(apiKeyProvider, "apiKeyProvider");
        Intrinsics.checkNotNullParameter(modelProvider, "modelProvider");
        Intrinsics.checkNotNullParameter(online, "online");
        this.baseUrlProvider = baseUrlProvider;
        this.apiKeyProvider = apiKeyProvider;
        this.modelProvider = modelProvider;
        this.online = online;
        this.interimIntervalMs = interimIntervalMs;
        this.connectTimeoutMs = connectTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
        this.id = "asr.cloud.openai_whisper";
        this.kind = VoiceProviderKind.CLOUD;
        this.supportsStreamingPartials = true;
    }

    public /* synthetic */ OpenAiWhisperStreamingAsrBackend(Function0 function0, Function0 function1, Function0 function2, Function0 function3, long j, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, function1, (i3 & 4) != 0 ? new Function0() { // from class: com.jarvis.app.asr.OpenAiWhisperStreamingAsrBackend$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OpenAiWhisperStreamingAsrBackend.DEFAULT_MODEL;
            }
        } : function2, (i3 & 8) != 0 ? new Function0() { // from class: com.jarvis.app.asr.OpenAiWhisperStreamingAsrBackend$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(OpenAiWhisperStreamingAsrBackend._init_$lambda$1());
            }
        } : function3, (i3 & 16) != 0 ? 900L : j, (i3 & 32) != 0 ? 8000 : i, (i3 & 64) != 0 ? 20000 : i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$1() {
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
        String strInvoke = this.modelProvider.invoke();
        if (StringsKt.isBlank(strInvoke)) {
            strInvoke = DEFAULT_MODEL;
        }
        return strInvoke;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public boolean getSupportsStreamingPartials() {
        return this.supportsStreamingPartials;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public VoiceProviderAvailability availability() {
        if (this.online.invoke().booleanValue() && !StringsKt.isBlank(this.apiKeyProvider.invoke()) && !StringsKt.isBlank(this.baseUrlProvider.invoke())) {
            return VoiceProviderAvailability.AVAILABLE;
        }
        return VoiceProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public StreamingAsrSession openSession(AsrSessionConfig config, AsrSessionListener listener) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return new WhisperStreamingSession(config, listener, getId(), getModel(), StringsKt.trimEnd(StringsKt.trim((CharSequence) this.baseUrlProvider.invoke()).toString(), IOUtils.DIR_SEPARATOR_UNIX), this.apiKeyProvider.invoke(), this.interimIntervalMs, this.connectTimeoutMs, this.readTimeoutMs);
    }
}
