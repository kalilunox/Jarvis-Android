package com.jarvis.asr;

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

/* JADX INFO: compiled from: LocalStreamingAsrBackend.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001e"}, d2 = {"Lcom/jarvis/asr/LocalStreamingAsrBackend;", "Lcom/jarvis/core/voice/StreamingAsrBackend;", "sessionFactory", "Lkotlin/Function0;", "Lcom/jarvis/asr/AsrSession;", "id", "", "model", "ready", "", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "getId", "()Ljava/lang/String;", "getModel", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "supportsStreamingPartials", "getSupportsStreamingPartials", "()Z", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "openSession", "Lcom/jarvis/core/voice/StreamingAsrSession;", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LocalStreamingAsrBackend implements StreamingAsrBackend {
    private final String id;
    private final VoiceProviderKind kind;
    private final String model;
    private final Function0<Boolean> ready;
    private final Function0<AsrSession> sessionFactory;
    private final boolean supportsStreamingPartials;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalStreamingAsrBackend(Function0<? extends AsrSession> sessionFactory, String id, String model, Function0<Boolean> ready) {
        Intrinsics.checkNotNullParameter(sessionFactory, "sessionFactory");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(ready, "ready");
        this.sessionFactory = sessionFactory;
        this.id = id;
        this.model = model;
        this.ready = ready;
        this.kind = VoiceProviderKind.LOCAL;
        this.supportsStreamingPartials = true;
    }

    public /* synthetic */ LocalStreamingAsrBackend(Function0 function0, String str, String str2, Function0 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i & 2) != 0 ? "asr.local.sherpa" : str, (i & 4) != 0 ? AsrModelSpec.INSTANCE.getRU_STREAMING().getDirName() : str2, (i & 8) != 0 ? new Function0() { // from class: com.jarvis.asr.LocalStreamingAsrBackend$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(LocalStreamingAsrBackend._init_$lambda$0());
            }
        } : function1);
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public String getModel() {
        return this.model;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return true;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public VoiceProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public boolean getSupportsStreamingPartials() {
        return this.supportsStreamingPartials;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public VoiceProviderAvailability availability() {
        return this.ready.invoke().booleanValue() ? VoiceProviderAvailability.AVAILABLE : VoiceProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.core.voice.StreamingAsrBackend
    public StreamingAsrSession openSession(AsrSessionConfig config, AsrSessionListener listener) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return new LocalStreamingSession(this.sessionFactory.invoke(), config, listener, getId(), getModel());
    }
}
