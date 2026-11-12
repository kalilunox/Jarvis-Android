package com.jarvis.app.assist;

import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.service.voice.VoiceInteractionSessionService;
import android.util.Log;
import kotlin.Metadata;

/* JADX INFO: compiled from: JarvisVoiceInteractionSessionService.kt */
/* JADX INFO: loaded from: classes22.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\t"}, d2 = {"Lcom/jarvis/app/assist/JarvisVoiceInteractionSessionService;", "Landroid/service/voice/VoiceInteractionSessionService;", "<init>", "()V", "onNewSession", "Landroid/service/voice/VoiceInteractionSession;", "args", "Landroid/os/Bundle;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisVoiceInteractionSessionService extends VoiceInteractionSessionService {
    private static final String TAG = "Jarvis.VIS.SessionSvc";

    @Override // android.service.voice.VoiceInteractionSessionService
    public VoiceInteractionSession onNewSession(Bundle args) {
        Log.i(TAG, "onNewSession");
        return new JarvisVoiceInteractionSession(this);
    }
}
