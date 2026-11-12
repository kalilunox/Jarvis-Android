package com.jarvis.app.assist;

import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.util.Log;
import kotlin.Metadata;

/* JADX INFO: compiled from: JarvisVoiceInteractionService.kt */
/* JADX INFO: loaded from: classes22.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"Lcom/jarvis/app/assist/JarvisVoiceInteractionService;", "Landroid/service/voice/VoiceInteractionService;", "<init>", "()V", "onReady", "", "onShutdown", "onLaunchVoiceAssistFromKeyguard", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisVoiceInteractionService extends VoiceInteractionService {
    private static final String TAG = "Jarvis.VIS";

    @Override // android.service.voice.VoiceInteractionService
    public void onReady() {
        super.onReady();
        Log.i(TAG, "ready (pipeline owner = VoiceService)");
    }

    @Override // android.service.voice.VoiceInteractionService
    public void onShutdown() {
        Log.i(TAG, "shutdown");
        super.onShutdown();
    }

    @Override // android.service.voice.VoiceInteractionService
    public void onLaunchVoiceAssistFromKeyguard() {
        Log.i(TAG, "launch from keyguard → showSession");
        showSession(new Bundle(), 0);
    }
}
