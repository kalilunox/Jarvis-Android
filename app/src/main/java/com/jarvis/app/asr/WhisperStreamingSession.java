package com.jarvis.app.asr;

import android.util.Log;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.app.VoiceService;
import com.jarvis.core.voice.AsrAudioFormat;
import com.jarvis.core.voice.AsrCancelReason;
import com.jarvis.core.voice.AsrConfidence;
import com.jarvis.core.voice.AsrErrorKind;
import com.jarvis.core.voice.AsrResult;
import com.jarvis.core.voice.AsrSessionConfig;
import com.jarvis.core.voice.AsrSessionListener;
import com.jarvis.core.voice.AsrSessionPhase;
import com.jarvis.core.voice.BoundedPcmRing;
import com.jarvis.core.voice.StreamingAsrSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: OpenAiWhisperStreamingAsrBackend.kt */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 A2\u00020\u0001:\u0001ABO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u000eH\u0016J\u0018\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020\u000eH\u0016J\b\u0010,\u001a\u00020%H\u0016J\u0010\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020%H\u0002J\u001a\u00103\u001a\u0004\u0018\u00010\u00072\u0006\u0010&\u001a\u00020'2\u0006\u00104\u001a\u000201H\u0002J(\u00105\u001a\u00020%2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020+2\u0006\u00104\u001a\u000201H\u0002J\u0012\u0010:\u001a\u0004\u0018\u00010\u00072\u0006\u0010;\u001a\u00020\u0007H\u0002J\u0010\u0010<\u001a\u00020%2\u0006\u0010=\u001a\u00020\u0007H\u0002J\u0010\u0010>\u001a\u00020%2\u0006\u0010?\u001a\u00020@H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u001d*\u0004\u0018\u00010\u001c0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/jarvis/app/asr/WhisperStreamingSession;", "Lcom/jarvis/core/voice/StreamingAsrSession;", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "backendId", "", "modelId", "baseUrl", "apiKey", "interimIntervalMs", "", "connectTimeoutMs", "", "readTimeoutMs", "<init>", "(Lcom/jarvis/core/voice/AsrSessionConfig;Lcom/jarvis/core/voice/AsrSessionListener;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JII)V", "sessionId", "getSessionId", "()Ljava/lang/String;", "providerId", "getProviderId", "active", "Ljava/util/concurrent/atomic/AtomicBoolean;", "ring", "Lcom/jarvis/core/voice/BoundedPcmRing;", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "lastInterimAt", "Ljava/util/concurrent/atomic/AtomicLong;", "inFlight", "lastPartial", "pending", "Ljava/util/concurrent/Future;", "feedFloatPcm", "", "samples", "", "sampleRateHz", "feedPcm16le", "bytes", "", "finalizeUtterance", "cancel", "reason", "Lcom/jarvis/core/voice/AsrCancelReason;", "isActive", "", "maybeInterim", "transcribe", "isFinal", "writeMultipart", "os", "Ljava/io/OutputStream;", "boundary", "wav", "parseTranscript", "body", "emitFinal", VoiceService.EXTRA_TEXT, "emitError", "kind", "Lcom/jarvis/core/voice/AsrErrorKind;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class WhisperStreamingSession implements StreamingAsrSession {
    private static final String TAG = "Jarvis.Asr";
    private final AtomicBoolean active;
    private final String apiKey;
    private final String backendId;
    private final String baseUrl;
    private final AsrSessionConfig config;
    private final int connectTimeoutMs;
    private final ExecutorService executor;
    private final AtomicBoolean inFlight;
    private final long interimIntervalMs;
    private final AtomicLong lastInterimAt;
    private String lastPartial;
    private final AsrSessionListener listener;
    private final String modelId;
    private Future<?> pending;
    private final String providerId;
    private final int readTimeoutMs;
    private final BoundedPcmRing ring;
    private final String sessionId;

    public WhisperStreamingSession(AsrSessionConfig config, AsrSessionListener listener, String backendId, String modelId, String baseUrl, String apiKey, long interimIntervalMs, int connectTimeoutMs, int readTimeoutMs) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(backendId, "backendId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        this.config = config;
        this.listener = listener;
        this.backendId = backendId;
        this.modelId = modelId;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.interimIntervalMs = interimIntervalMs;
        this.connectTimeoutMs = connectTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
        this.sessionId = this.config.getSessionId();
        this.providerId = this.backendId;
        this.active = new AtomicBoolean(true);
        this.ring = new BoundedPcmRing(this.config.getMaxBufferedMs(), this.config.getSampleRateHz());
        this.executor = Executors.newSingleThreadExecutor();
        this.lastInterimAt = new AtomicLong(0L);
        this.inFlight = new AtomicBoolean(false);
        this.lastPartial = "";
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
            this.ring.appendFloat(samples, sampleRateHz);
            maybeInterim();
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
            this.pending = this.executor.submit(new Runnable() { // from class: com.jarvis.app.asr.WhisperStreamingSession$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WhisperStreamingSession.finalizeUtterance$lambda$0(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void finalizeUtterance$lambda$0(WhisperStreamingSession this$0) {
        float[] snap = this$0.ring.snapshotFloat();
        if (snap.length == 0) {
            this$0.emitFinal(this$0.lastPartial);
            return;
        }
        String text = this$0.transcribe(snap, true);
        if (text != null) {
            this$0.emitFinal(text);
        } else if (!StringsKt.isBlank(this$0.lastPartial)) {
            this$0.emitFinal(this$0.lastPartial);
        } else {
            this$0.emitError(AsrErrorKind.NETWORK);
        }
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void cancel(AsrCancelReason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (this.active.compareAndSet(true, false)) {
            Future<?> future = this.pending;
            if (future != null) {
                future.cancel(true);
            }
            this.executor.shutdownNow();
            this.listener.onEvent(new AsrResult(this.config.getRequestId(), this.lastPartial, false, null, null, this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.CANCEL, null, null, null, null, false, null, AsrErrorKind.CANCELLED, 32280, null));
        }
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public boolean isActive() {
        return this.active.get();
    }

    private final void maybeInterim() {
        long now = System.currentTimeMillis();
        if (now - this.lastInterimAt.get() >= this.interimIntervalMs && this.inFlight.compareAndSet(false, true)) {
            this.lastInterimAt.set(now);
            this.pending = this.executor.submit(new Runnable() { // from class: com.jarvis.app.asr.WhisperStreamingSession$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WhisperStreamingSession.maybeInterim$lambda$0(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void maybeInterim$lambda$0(WhisperStreamingSession this$0) {
        try {
            if (!this$0.active.get()) {
                this$0.inFlight.set(false);
                return;
            }
            float[] snap = this$0.ring.snapshotFloat();
            if (snap.length < ((double) this$0.config.getSampleRateHz()) * 0.6d) {
                this$0.inFlight.set(false);
                return;
            }
            String text = this$0.transcribe(snap, false);
            if (text == null) {
                this$0.inFlight.set(false);
                return;
            }
            if (StringsKt.isBlank(text) || Intrinsics.areEqual(text, this$0.lastPartial)) {
                this$0.inFlight.set(false);
                return;
            }
            this$0.lastPartial = text;
            this$0.listener.onEvent(new AsrResult(this$0.config.getRequestId(), text, false, AsrConfidence.Unknown.INSTANCE, this$0.config.getLanguage(), this$0.backendId, this$0.modelId, this$0.config.getSessionId(), AsrSessionPhase.PARTIAL, null, null, null, null, false, null, null, 65024, null));
            this$0.inFlight.set(false);
        } catch (Throwable th) {
            this$0.inFlight.set(false);
            throw th;
        }
    }

    private final String transcribe(float[] samples, boolean isFinal) {
        String text;
        if (StringsKt.isBlank(this.apiKey)) {
            return null;
        }
        byte[] pcm = AsrAudioFormat.INSTANCE.floatToPcm16le(samples);
        byte[] wav = AsrAudioFormat.INSTANCE.pcm16leToWav(pcm, this.config.getSampleRateHz());
        String url = this.baseUrl + "/audio/transcriptions";
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String boundary = "----JarvisAsr" + StringsKt.replace$default(string, "-", "", false, 4, (Object) null);
        try {
            URLConnection uRLConnectionOpenConnection = new URI(url).toURL().openConnection();
            Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection $this$transcribe_u24lambda_u240 = (HttpURLConnection) uRLConnectionOpenConnection;
            $this$transcribe_u24lambda_u240.setRequestMethod("POST");
            $this$transcribe_u24lambda_u240.setDoInput(true);
            $this$transcribe_u24lambda_u240.setDoOutput(true);
            $this$transcribe_u24lambda_u240.setConnectTimeout(this.connectTimeoutMs);
            $this$transcribe_u24lambda_u240.setReadTimeout(this.readTimeoutMs);
            $this$transcribe_u24lambda_u240.setRequestProperty("Authorization", "Bearer " + this.apiKey);
            $this$transcribe_u24lambda_u240.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            try {
                try {
                    try {
                        OutputStream outputStream = $this$transcribe_u24lambda_u240.getOutputStream();
                        try {
                            OutputStream os = outputStream;
                            Intrinsics.checkNotNull(os);
                            try {
                                writeMultipart(os, boundary, wav, isFinal);
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(outputStream, null);
                                int code = $this$transcribe_u24lambda_u240.getResponseCode();
                                InputStream stream = 200 <= code && code < 300 ? $this$transcribe_u24lambda_u240.getInputStream() : $this$transcribe_u24lambda_u240.getErrorStream();
                                if (stream != null) {
                                    Reader inputStreamReader = new InputStreamReader(stream, Charsets.UTF_8);
                                    BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                                    try {
                                        BufferedReader it = bufferedReader;
                                        text = TextStreamsKt.readText(it);
                                        CloseableKt.closeFinally(bufferedReader, null);
                                    } catch (Throwable th) {
                                        try {
                                            throw th;
                                        } catch (Throwable th2) {
                                            CloseableKt.closeFinally(bufferedReader, th);
                                            throw th2;
                                        }
                                    }
                                } else {
                                    text = null;
                                }
                                if (text == null) {
                                    text = "";
                                }
                                String body = text;
                                if (200 <= code && code < 300) {
                                    String transcript = parseTranscript(body);
                                    $this$transcribe_u24lambda_u240.disconnect();
                                    return transcript;
                                }
                                Log.w(TAG, "whisper HTTP " + code + " (key redacted)");
                                $this$transcribe_u24lambda_u240.disconnect();
                                return null;
                            } catch (Throwable th3) {
                                th = th3;
                                Throwable th4 = th;
                                try {
                                    throw th4;
                                } catch (Throwable th5) {
                                    CloseableKt.closeFinally(outputStream, th4);
                                    throw th5;
                                }
                            }
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    } catch (Throwable th7) {
                        t = th7;
                        Log.w(TAG, "whisper transport failed: " + t.getClass().getSimpleName());
                        return null;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    $this$transcribe_u24lambda_u240.disconnect();
                    throw th;
                }
            } catch (Throwable th9) {
                th = th9;
                $this$transcribe_u24lambda_u240.disconnect();
                throw th;
            }
        } catch (Throwable th10) {
            t = th10;
        }
    }

    private final void writeMultipart(OutputStream os, String boundary, byte[] wav, boolean isFinal) throws IOException {
        OutputStreamWriter w = new OutputStreamWriter(os, Charsets.UTF_8);
        writeMultipart$field(w, boundary, "model", this.modelId);
        String strTake = StringsKt.take(this.config.getLanguage(), 2);
        if (StringsKt.isBlank(strTake)) {
            strTake = "ru";
        }
        writeMultipart$field(w, boundary, "language", strTake);
        writeMultipart$field(w, boundary, "response_format", "json");
        if (!this.config.getVocabularyHints().isEmpty()) {
            writeMultipart$field(w, boundary, "prompt", CollectionsKt.joinToString$default(CollectionsKt.take(this.config.getVocabularyHints(), 12), ", ", null, null, 0, null, null, 62, null));
        }
        w.append((CharSequence) "--").append((CharSequence) boundary).append((CharSequence) "\r\n");
        w.append((CharSequence) "Content-Disposition: form-data; name=\"file\"; filename=\"utt.wav\"\r\n");
        w.append((CharSequence) "Content-Type: audio/wav\r\n\r\n");
        w.flush();
        os.write(wav);
        os.flush();
        w.append((CharSequence) "\r\n--").append((CharSequence) boundary).append((CharSequence) "--\r\n");
        w.flush();
    }

    private static final void writeMultipart$field(OutputStreamWriter w, String $boundary, String name, String value) throws IOException {
        w.append("--").append((CharSequence) $boundary).append("\r\n");
        w.append("Content-Disposition: form-data; name=\"").append((CharSequence) name).append("\"\r\n\r\n");
        w.append((CharSequence) value).append("\r\n");
    }

    private final String parseTranscript(String body) {
        String str = null;
        try {
            String strOptString = new JSONObject(body).optString(VoiceService.EXTRA_TEXT);
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            String string = StringsKt.trim((CharSequence) strOptString).toString();
            if (StringsKt.isBlank(string)) {
                string = null;
            }
            return string;
        } catch (Throwable th) {
            String string2 = StringsKt.trim((CharSequence) body).toString();
            if (!StringsKt.isBlank(string2)) {
                str = string2;
            }
            return str;
        }
    }

    private final void emitFinal(String text) {
        if (this.active.compareAndSet(true, false)) {
            this.executor.shutdownNow();
            this.listener.onEvent(new AsrResult(this.config.getRequestId(), StringsKt.trim((CharSequence) text).toString(), true, AsrConfidence.Unknown.INSTANCE, this.config.getLanguage(), this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.FINAL, null, null, null, null, false, null, null, 65024, null));
        }
    }

    private final void emitError(AsrErrorKind kind) {
        if (this.active.compareAndSet(true, false)) {
            this.executor.shutdownNow();
            this.listener.onEvent(new AsrResult(this.config.getRequestId(), this.lastPartial, false, null, null, this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.ERROR, null, null, null, null, false, null, kind, 32280, null));
        }
    }
}
