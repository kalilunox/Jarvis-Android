package com.jarvis.app.assist;

import android.content.Intent;
import android.os.RemoteException;
import android.speech.RecognitionService;
import android.util.Log;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisRecognitionService.kt */
/* JADX INFO: loaded from: classes22.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tR\u00020\u0001H\u0014J\u0014\u0010\n\u001a\u00020\u00052\n\u0010\b\u001a\u00060\tR\u00020\u0001H\u0014J\u0014\u0010\u000b\u001a\u00020\u00052\n\u0010\b\u001a\u00060\tR\u00020\u0001H\u0014¨\u0006\r"}, d2 = {"Lcom/jarvis/app/assist/JarvisRecognitionService;", "Landroid/speech/RecognitionService;", "<init>", "()V", "onStartListening", "", "recognizerIntent", "Landroid/content/Intent;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/speech/RecognitionService$Callback;", "onCancel", "onStopListening", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisRecognitionService extends RecognitionService {
    private static final String TAG = "Jarvis.Recognition";

    @Override // android.speech.RecognitionService
    protected void onStartListening(Intent recognizerIntent, RecognitionService.Callback listener) {
        Intrinsics.checkNotNullParameter(recognizerIntent, "recognizerIntent");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.d(TAG, "onStartListening (stub → forward to VoiceService path)");
        try {
            listener.error(5);
        } catch (RemoteException e) {
        }
    }

    @Override // android.speech.RecognitionService
    protected void onCancel(RecognitionService.Callback listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.d(TAG, "onCancel");
    }

    @Override // android.speech.RecognitionService
    protected void onStopListening(RecognitionService.Callback listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.d(TAG, "onStopListening");
    }
}
