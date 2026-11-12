package com.jarvis.tts;

import com.jarvis.app.JarvisSpeaker;
import com.jarvis.app.VoiceService;
import com.jarvis.core.voice.StreamingTtsBackend;
import com.jarvis.core.voice.TtsAudioResult;
import com.jarvis.core.voice.TtsErrorKind;
import com.jarvis.core.voice.TtsVoiceConfig;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.core.voice.VoiceProviderKind;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.commons.compress.harmony.pack200.PackingOptions;

/* JADX INFO: compiled from: LocalPiperTtsBackend.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003H\u0016R\u0016\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0007\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\b\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001f"}, d2 = {"Lcom/jarvis/tts/LocalPiperTtsBackend;", "Lcom/jarvis/core/voice/StreamingTtsBackend;", "engine", "Lkotlin/Function0;", "Lcom/jarvis/tts/JarvisTts;", "id", "", "model", "defaultVoice", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getModel", "getDefaultVoice", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "supportsStreamingAudio", "", "getSupportsStreamingAudio", "()Z", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "synthesize", "Lcom/jarvis/core/voice/TtsAudioResult;", VoiceService.EXTRA_TEXT, "voice", "Lcom/jarvis/core/voice/TtsVoiceConfig;", "cancelFlag", "tts-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LocalPiperTtsBackend implements StreamingTtsBackend {
    private final String defaultVoice;
    private final Function0<JarvisTts> engine;
    private final String id;
    private final VoiceProviderKind kind;
    private final String model;
    private final boolean supportsStreamingAudio;

    public LocalPiperTtsBackend(Function0<JarvisTts> engine, String id, String model, String defaultVoice) {
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(defaultVoice, "defaultVoice");
        this.engine = engine;
        this.id = id;
        this.model = model;
        this.defaultVoice = defaultVoice;
        this.kind = VoiceProviderKind.LOCAL;
    }

    public /* synthetic */ LocalPiperTtsBackend(Function0 function0, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i & 2) != 0 ? "tts.local.piper" : str, (i & 4) != 0 ? JarvisSpeaker.TTS_MODEL_DIR : str2, (i & 8) != 0 ? "dmitri" : str3);
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public String getModel() {
        return this.model;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public String getDefaultVoice() {
        return this.defaultVoice;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public VoiceProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public boolean getSupportsStreamingAudio() {
        return this.supportsStreamingAudio;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public VoiceProviderAvailability availability() {
        return this.engine.invoke() != null ? VoiceProviderAvailability.AVAILABLE : VoiceProviderAvailability.UNAVAILABLE;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public TtsAudioResult synthesize(String text, TtsVoiceConfig voice, Function0<Boolean> cancelFlag) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(cancelFlag, "cancelFlag");
        if (cancelFlag.invoke().booleanValue()) {
            return new TtsAudioResult(new float[0], voice.getSampleRateHz(), getId(), getModel(), getDefaultVoice(), 0L, 0L, false, false, null, TtsErrorKind.CANCELLED, 992, null);
        }
        JarvisTts tts = this.engine.invoke();
        if (tts == null) {
            return new TtsAudioResult(new float[0], voice.getSampleRateHz(), getId(), getModel(), getDefaultVoice(), 0L, 0L, false, false, null, TtsErrorKind.PROVIDER_UNAVAILABLE, 992, null);
        }
        long t0 = System.nanoTime();
        try {
            Result.Companion companion = Result.INSTANCE;
            LocalPiperTtsBackend localPiperTtsBackend = this;
            objM392constructorimpl = Result.m392constructorimpl(tts.synthesize(text));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m395exceptionOrNullimpl(objM392constructorimpl) != null) {
            return new TtsAudioResult(new float[0], tts.getSampleRate(), getId(), getModel(), getDefaultVoice(), 0L, 0L, false, false, null, TtsErrorKind.UNKNOWN, 992, null);
        }
        float[] samples = (float[]) objM392constructorimpl;
        if (cancelFlag.invoke().booleanValue()) {
            return new TtsAudioResult(new float[0], tts.getSampleRate(), getId(), getModel(), getDefaultVoice(), 0L, 0L, false, false, null, TtsErrorKind.CANCELLED, 992, null);
        }
        long ms = RangesKt.coerceAtLeast((System.nanoTime() - t0) / PackingOptions.SEGMENT_LIMIT, 0L);
        return new TtsAudioResult(samples, tts.getSampleRate(), getId(), getModel(), getDefaultVoice(), 0L, ms, false, false, null, null, 1792, null);
    }
}
