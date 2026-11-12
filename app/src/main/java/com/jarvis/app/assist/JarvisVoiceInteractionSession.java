package com.jarvis.app.assist;

import android.content.Context;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import com.jarvis.app.VoiceController;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisVoiceInteractionSession.kt */
/* JADX INFO: loaded from: classes22.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u001a\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¨\u0006\u0015"}, d2 = {"Lcom/jarvis/app/assist/JarvisVoiceInteractionSession;", "Landroid/service/voice/VoiceInteractionSession;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onCreate", "", "onShow", "args", "Landroid/os/Bundle;", "showFlags", "", "onHandleAssist", "state", "Landroid/service/voice/VoiceInteractionSession$AssistState;", "onHide", "handoffToVoicePipeline", "reason", "", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisVoiceInteractionSession extends VoiceInteractionSession {
    private static final String TAG = "Jarvis.VIS.Session";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JarvisVoiceInteractionSession(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "session created");
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onShow(Bundle args, int showFlags) {
        super.onShow(args, showFlags);
        Log.i(TAG, "onShow flags=" + showFlags + " → VoicePipeline");
        handoffToVoicePipeline("onShow");
        hide();
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onHandleAssist(VoiceInteractionSession.AssistState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onHandleAssist(state);
        Log.i(TAG, "onHandleAssist → VoicePipeline (payload ignored until Memory)");
        handoffToVoicePipeline("onHandleAssist");
        hide();
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onHide() {
        Log.d(TAG, "onHide");
        super.onHide();
    }

    private final void handoffToVoicePipeline(String reason) {
        Object objM392constructorimpl;
        Context appCtx = getContext().getApplicationContext();
        try {
            Result.Companion companion = Result.INSTANCE;
            JarvisVoiceInteractionSession jarvisVoiceInteractionSession = this;
            VoiceController voiceController = VoiceController.INSTANCE;
            Intrinsics.checkNotNull(appCtx);
            voiceController.startFromSystemAssist(appCtx);
            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Log.i(TAG, "handoff ok reason=" + reason + " entry=system_assist")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            Log.e(TAG, "handoff failed reason=" + reason + ": " + it.getMessage(), it);
        }
    }
}
