package com.jarvis.app.asr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AndroidSpeechCloudAsrBackend.kt */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000 82\u00020\u0001:\u00018B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010%\u001a\u00020\u001cH\u0016J\u0010\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u001aH\u0016J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\tH\u0002J\u0018\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\tH\u0002J\u0010\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u000202H\u0002J\u0014\u00103\u001a\u0004\u0018\u00010\t2\b\u00104\u001a\u0004\u0018\u000105H\u0002J\u0010\u00106\u001a\u00020.2\u0006\u00107\u001a\u00020!H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/jarvis/app/asr/AndroidSpeechSession;", "Lcom/jarvis/core/voice/StreamingAsrSession;", "appContext", "Landroid/content/Context;", "config", "Lcom/jarvis/core/voice/AsrSessionConfig;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/voice/AsrSessionListener;", "backendId", "", "modelId", "<init>", "(Landroid/content/Context;Lcom/jarvis/core/voice/AsrSessionConfig;Lcom/jarvis/core/voice/AsrSessionListener;Ljava/lang/String;Ljava/lang/String;)V", "sessionId", "getSessionId", "()Ljava/lang/String;", "providerId", "getProviderId", "active", "Ljava/util/concurrent/atomic/AtomicBoolean;", "main", "Landroid/os/Handler;", "recognizer", "Landroid/speech/SpeechRecognizer;", "lastPartial", "started", "", "startRecognition", "", "feedFloatPcm", "samples", "", "sampleRateHz", "", "feedPcm16le", "bytes", "", "finalizeUtterance", "cancel", "reason", "Lcom/jarvis/core/voice/AsrCancelReason;", "isActive", "emitFinal", VoiceService.EXTRA_TEXT, "emitError", "kind", "Lcom/jarvis/core/voice/AsrErrorKind;", "detail", "emit", "result", "Lcom/jarvis/core/voice/AsrResult;", "firstResult", "bundle", "Landroid/os/Bundle;", "mapError", "error", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class AndroidSpeechSession implements StreamingAsrSession {
    private static final String TAG = "Jarvis.Asr";
    private final AtomicBoolean active;
    private final Context appContext;
    private final String backendId;
    private final AsrSessionConfig config;
    private String lastPartial;
    private final AsrSessionListener listener;
    private final Handler main;
    private final String modelId;
    private final String providerId;
    private SpeechRecognizer recognizer;
    private final String sessionId;
    private boolean started;

    public AndroidSpeechSession(Context appContext, AsrSessionConfig config, AsrSessionListener listener, String backendId, String modelId) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(backendId, "backendId");
        Intrinsics.checkNotNullParameter(modelId, "modelId");
        this.appContext = appContext;
        this.config = config;
        this.listener = listener;
        this.backendId = backendId;
        this.modelId = modelId;
        this.sessionId = this.config.getSessionId();
        this.providerId = this.backendId;
        this.active = new AtomicBoolean(true);
        this.main = new Handler(Looper.getMainLooper());
        this.lastPartial = "";
        this.main.post(new Runnable() { // from class: com.jarvis.app.asr.AndroidSpeechSession$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.startRecognition();
            }
        });
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public String getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRecognition() {
        if (this.active.get()) {
            if (!SpeechRecognizer.isRecognitionAvailable(this.appContext)) {
                emitError(AsrErrorKind.UNAVAILABLE, "SpeechRecognizer unavailable");
                return;
            }
            SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(this.appContext);
            this.recognizer = sr;
            sr.setRecognitionListener(new RecognitionListener() { // from class: com.jarvis.app.asr.AndroidSpeechSession.startRecognition.1
                @Override // android.speech.RecognitionListener
                public void onReadyForSpeech(Bundle params) {
                    Log.i(AndroidSpeechSession.TAG, "ready session=" + AndroidSpeechSession.this.config.getSessionId());
                }

                @Override // android.speech.RecognitionListener
                public void onBeginningOfSpeech() {
                }

                @Override // android.speech.RecognitionListener
                public void onRmsChanged(float rmsdB) {
                }

                @Override // android.speech.RecognitionListener
                public void onBufferReceived(byte[] buffer) {
                }

                @Override // android.speech.RecognitionListener
                public void onEvent(int eventType, Bundle params) {
                }

                @Override // android.speech.RecognitionListener
                public void onPartialResults(Bundle partialResults) {
                    String text = AndroidSpeechSession.this.firstResult(partialResults);
                    if (text != null && !StringsKt.isBlank(text) && !Intrinsics.areEqual(text, AndroidSpeechSession.this.lastPartial)) {
                        AndroidSpeechSession.this.lastPartial = text;
                        AndroidSpeechSession.this.emit(new AsrResult(AndroidSpeechSession.this.config.getRequestId(), text, false, AsrConfidence.Unknown.INSTANCE, AndroidSpeechSession.this.config.getLanguage(), AndroidSpeechSession.this.backendId, AndroidSpeechSession.this.modelId, AndroidSpeechSession.this.config.getSessionId(), AsrSessionPhase.PARTIAL, null, null, null, null, false, null, null, 65024, null));
                    }
                }

                @Override // android.speech.RecognitionListener
                public void onResults(Bundle results) {
                    String text = AndroidSpeechSession.this.firstResult(results);
                    if (text == null) {
                        text = AndroidSpeechSession.this.lastPartial;
                    }
                    AndroidSpeechSession.this.emitFinal(text);
                }

                @Override // android.speech.RecognitionListener
                public void onEndOfSpeech() {
                }

                @Override // android.speech.RecognitionListener
                public void onError(int error) {
                    AsrErrorKind kind = AndroidSpeechSession.this.mapError(error);
                    Log.w(AndroidSpeechSession.TAG, "error=" + error + " kind=" + kind + " session=" + AndroidSpeechSession.this.config.getSessionId());
                    if (StringsKt.isBlank(AndroidSpeechSession.this.lastPartial) || (error != 6 && error != 7)) {
                        AndroidSpeechSession.this.emitError(kind, "speech_error_" + error);
                    } else {
                        AndroidSpeechSession.this.emitFinal(AndroidSpeechSession.this.lastPartial);
                    }
                }
            });
            Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
            intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
            intent.putExtra("android.speech.extra.LANGUAGE", this.config.getLanguage());
            intent.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
            intent.putExtra("android.speech.extra.MAX_RESULTS", 3);
            intent.putExtra("calling_package", this.appContext.getPackageName());
            if (!this.config.getVocabularyHints().isEmpty()) {
                intent.putExtra("android.speech.extra.PREFER_OFFLINE", false);
            }
            this.started = true;
            sr.startListening(intent);
        }
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void feedFloatPcm(float[] samples, int sampleRateHz) {
        Intrinsics.checkNotNullParameter(samples, "samples");
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void feedPcm16le(byte[] bytes, int sampleRateHz) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void finalizeUtterance() {
        this.main.post(new Runnable() { // from class: com.jarvis.app.asr.AndroidSpeechSession$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AndroidSpeechSession.finalizeUtterance$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void finalizeUtterance$lambda$0(AndroidSpeechSession this$0) {
        SpeechRecognizer speechRecognizer;
        if (this$0.active.get() && (speechRecognizer = this$0.recognizer) != null) {
            speechRecognizer.stopListening();
        }
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public void cancel(AsrCancelReason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (this.active.compareAndSet(true, false)) {
            this.main.post(new Runnable() { // from class: com.jarvis.app.asr.AndroidSpeechSession$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidSpeechSession.cancel$lambda$0(this.f$0);
                }
            });
            emit(new AsrResult(this.config.getRequestId(), this.lastPartial, false, null, null, this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.CANCEL, null, null, null, null, false, null, AsrErrorKind.CANCELLED, 32280, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cancel$lambda$0(AndroidSpeechSession this$0) {
        Unit unit;
        try {
            Result.Companion companion = Result.INSTANCE;
            SpeechRecognizer speechRecognizer = this$0.recognizer;
            if (speechRecognizer != null) {
                speechRecognizer.cancel();
            }
            SpeechRecognizer speechRecognizer2 = this$0.recognizer;
            if (speechRecognizer2 != null) {
                speechRecognizer2.destroy();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        this$0.recognizer = null;
    }

    @Override // com.jarvis.core.voice.StreamingAsrSession
    public boolean isActive() {
        return this.active.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitFinal(String text) {
        if (this.active.compareAndSet(true, false)) {
            this.main.post(new Runnable() { // from class: com.jarvis.app.asr.AndroidSpeechSession$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidSpeechSession.emitFinal$lambda$0(this.f$0);
                }
            });
            emit(new AsrResult(this.config.getRequestId(), StringsKt.trim((CharSequence) text).toString(), true, AsrConfidence.Unknown.INSTANCE, this.config.getLanguage(), this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.FINAL, null, null, null, null, false, null, null, 65024, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void emitFinal$lambda$0(AndroidSpeechSession this$0) {
        Unit unit;
        try {
            Result.Companion companion = Result.INSTANCE;
            SpeechRecognizer speechRecognizer = this$0.recognizer;
            if (speechRecognizer != null) {
                speechRecognizer.destroy();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        this$0.recognizer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitError(AsrErrorKind kind, String detail) {
        if (this.active.compareAndSet(true, false)) {
            this.main.post(new Runnable() { // from class: com.jarvis.app.asr.AndroidSpeechSession$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidSpeechSession.emitError$lambda$0(this.f$0);
                }
            });
            Log.w(TAG, "asr error " + detail);
            emit(new AsrResult(this.config.getRequestId(), this.lastPartial, false, null, null, this.backendId, this.modelId, this.config.getSessionId(), AsrSessionPhase.ERROR, null, null, null, null, false, null, kind, 32280, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void emitError$lambda$0(AndroidSpeechSession this$0) {
        Unit unit;
        try {
            Result.Companion companion = Result.INSTANCE;
            SpeechRecognizer speechRecognizer = this$0.recognizer;
            if (speechRecognizer != null) {
                speechRecognizer.destroy();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        this$0.recognizer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emit(AsrResult result) {
        this.listener.onEvent(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String firstResult(Bundle bundle) {
        ArrayList<String> stringArrayList;
        String str;
        if (bundle == null || (stringArrayList = bundle.getStringArrayList("results_recognition")) == null || (str = (String) CollectionsKt.firstOrNull((List) stringArrayList)) == null) {
            return null;
        }
        return StringsKt.trim((CharSequence) str).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AsrErrorKind mapError(int error) {
        switch (error) {
            case 1:
            case 2:
                return AsrErrorKind.NETWORK;
            case 3:
            case 5:
                return AsrErrorKind.INVALID_AUDIO;
            case 4:
            case 8:
                return AsrErrorKind.UNAVAILABLE;
            case 6:
                return AsrErrorKind.TIMEOUT;
            case 7:
                return AsrErrorKind.UNSUPPORTED;
            case 9:
                return AsrErrorKind.AUTH;
            default:
                return AsrErrorKind.INTERNAL;
        }
    }
}
