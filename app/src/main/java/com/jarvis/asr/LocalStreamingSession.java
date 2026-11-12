package com.jarvis.asr;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.app.VoiceService;
import com.jarvis.core.voice.AsrCancelReason;
import com.jarvis.core.voice.AsrConfidence;
import com.jarvis.core.voice.AsrErrorKind;
import com.jarvis.core.voice.AsrResult;
import com.jarvis.core.voice.AsrSessionConfig;
import com.jarvis.core.voice.AsrSessionListener;
import com.jarvis.core.voice.AsrSessionPhase;
import com.jarvis.core.voice.StreamingAsrSession;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LocalStreamingAsrBackend.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u0016H\u0016J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/jarvis/asr/LocalStreamingSession;", "Lcom/jarvis/core/voice/StreamingAsrSession;", "asr", "Lcom/jarvis/asr/AsrSession;", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "backendId", "", "modelId", "<init>", "(Lcom/jarvis/asr/AsrSession;Lcom/jarvis/core/voice/AsrSessionConfig;Lcom/jarvis/core/voice/AsrSessionListener;Ljava/lang/String;Ljava/lang/String;)V", "sessionId", "getSessionId", "()Ljava/lang/String;", "providerId", "getProviderId", "active", "Ljava/util/concurrent/atomic/AtomicBoolean;", "lastText", "feedFloatPcm", "", "samples", "", "sampleRateHz", "", "feedPcm16le", "bytes", "", "finalizeUtterance", "cancel", "reason", "Lcom/jarvis/core/voice/AsrCancelReason;", "isActive", "", "emitFinal", VoiceService.EXTRA_TEXT, "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class LocalStreamingSession implements StreamingAsrSession {
    private final AtomicBoolean active;
    private final AsrSession asr;
    private final String backendId;
    private final AsrSessionConfig config;
    private String lastText;
    private final AsrSessionListener listener;
    private final String modelId;
    private final String providerId;
    private final String sessionId;

    public LocalStreamingSession(AsrSession asr, AsrSessionConfig config, AsrSessionListener listener, String backendId, String modelId) {
        Intrinsics.checkNotNullParameter(asr, "asr");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(backendId, "backendId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        this.asr = asr;
        this.config = config;
        this.listener = listener;
        this.backendId = backendId;
        this.modelId = modelId;
        this.sessionId = this.config.getSessionId();
        this.providerId = this.backendId;
        this.active = new AtomicBoolean(true);
        this.lastText = "";
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public String getProviderId() {
        return this.providerId;
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void feedFloatPcm(float[] samples, int sampleRateHz) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        if (this.active.get()) {
            AsrChunkResult result = this.asr.processChunk(samples);
            String text = StringsKt.trim((CharSequence) result.getText()).toString();
            if (!StringsKt.isBlank(text) && !Intrinsics.areEqual(text, this.lastText)) {
                this.lastText = text;
                this.listener.onEvent(new AsrResult(this.config.getRequestId(), text, false, AsrConfidence.Unknown.INSTANCE, this.config.getLanguage(), this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.PARTIAL, null, null, null, null, false, null, null, 65024, null));
            }
            if (result.isEndpoint() && !StringsKt.isBlank(text)) {
                emitFinal(text);
            }
        }
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void feedPcm16le(byte[] bytes, int sampleRateHz) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        float[] samples = new float[bytes.length / 2];
        int i = 0;
        int s = 0;
        while (i + 1 < bytes.length) {
            int lo = bytes[i] & UByte.MAX_VALUE;
            int hi = bytes[i + 1];
            short v = (short) ((hi << 8) | lo);
            samples[s] = v / 32768.0f;
            i += 2;
            s++;
        }
        float[] fArrCopyOf = Arrays.copyOf(samples, s);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        feedFloatPcm(fArrCopyOf, sampleRateHz);
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void finalizeUtterance() {
        if (this.active.get()) {
            emitFinal(this.lastText);
        }
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void cancel(AsrCancelReason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (this.active.compareAndSet(true, false)) {
            this.asr.reset();
            this.listener.onEvent(new AsrResult(this.config.getRequestId(), this.lastText, false, null, null, this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.CANCEL, null, null, null, null, false, null, AsrErrorKind.CANCELLED, 32280, null));
        }
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public boolean isActive() {
        return this.active.get();
    }

    private final void emitFinal(String text) {
        if (this.active.compareAndSet(true, false)) {
            this.asr.reset();
            this.listener.onEvent(new AsrResult(this.config.getRequestId(), text, true, AsrConfidence.Unknown.INSTANCE, this.config.getLanguage(), this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.FINAL, null, null, null, null, false, null, null, 65024, null));
        }
    }
}
