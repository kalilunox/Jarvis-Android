package com.jarvis.app.proactive;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import com.jarvis.app.VoiceController;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.proactive.InterruptionKind;
import com.jarvis.core.proactive.PhoneStateSnapshot;
import com.jarvis.core.proactive.ProactiveDeliveryContract;
import com.jarvis.core.proactive.RingerKind;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PhoneStateReader.kt */
/* JADX INFO: loaded from: classes23.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/jarvis/app/proactive/PhoneStateReader;", "", "<init>", "()V", "FAKE_NONE", "", "FAKE_SILENT", "FAKE_DND", "FAKE_CALL", "FAKE_SESSION", "FAKE_NO_NOTIFICATIONS", "FAKE_NO_VOICE", "read", "Lcom/jarvis/core/proactive/PhoneStateSnapshot;", "context", "Landroid/content/Context;", "applyFake", "base", "fakeState", "knownFakeStates", "", "isCallActive", "", "audio", "Landroid/media/AudioManager;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PhoneStateReader {
    public static final String FAKE_CALL = "call";
    public static final String FAKE_DND = "dnd";
    public static final String FAKE_NONE = "none";
    public static final String FAKE_NO_NOTIFICATIONS = "nonotif";
    public static final String FAKE_NO_VOICE = "novoice";
    public static final String FAKE_SESSION = "session";
    public static final String FAKE_SILENT = "silent";
    public static final PhoneStateReader INSTANCE = new PhoneStateReader();

    private PhoneStateReader() {
    }

    public final PhoneStateSnapshot read(Context context) {
        Object objM392constructorimpl;
        RingerKind ringer;
        Object objM392constructorimpl2;
        InterruptionKind filter;
        Object objM392constructorimpl3;
        Intrinsics.checkNotNullParameter(context, "context");
        Context app = context.getApplicationContext();
        AudioManager audio = (AudioManager) app.getSystemService(AudioManager.class);
        NotificationManager notifications = (NotificationManager) app.getSystemService(NotificationManager.class);
        NotificationChannel channel = null;
        try {
            Result.Companion companion = Result.INSTANCE;
            PhoneStateReader phoneStateReader = this;
            objM392constructorimpl = Result.m392constructorimpl(audio != null ? Integer.valueOf(audio.getRingerMode()) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        Integer num = (Integer) objM392constructorimpl;
        boolean z = true;
        if (num != null && num.intValue() == 0) {
            ringer = RingerKind.SILENT;
        } else {
            ringer = (num != null && num.intValue() == 1) ? RingerKind.VIBRATE : RingerKind.NORMAL;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            PhoneStateReader phoneStateReader2 = this;
            objM392constructorimpl2 = Result.m392constructorimpl(notifications != null ? Integer.valueOf(notifications.getCurrentInterruptionFilter()) : null);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl2)) {
            objM392constructorimpl2 = null;
        }
        Integer num2 = (Integer) objM392constructorimpl2;
        if (num2 != null && num2.intValue() == 1) {
            filter = InterruptionKind.ALL;
        } else if (num2 != null && num2.intValue() == 2) {
            filter = InterruptionKind.PRIORITY;
        } else if (num2 != null && num2.intValue() == 4) {
            filter = InterruptionKind.ALARMS;
        } else {
            filter = (num2 != null && num2.intValue() == 3) ? InterruptionKind.NONE : InterruptionKind.UNKNOWN;
        }
        Intrinsics.checkNotNull(app);
        boolean call = isCallActive(app, audio);
        AssistantState assistant = JarvisEvents.INSTANCE.getAssistantState().getValue();
        boolean session = VoiceController.INSTANCE.isLiveSession() || assistant == AssistantState.LISTENING || assistant == AssistantState.THINKING || assistant == AssistantState.SPEAKING;
        try {
            Result.Companion companion5 = Result.INSTANCE;
            PhoneStateReader phoneStateReader3 = this;
            boolean enabled = notifications != null && notifications.areNotificationsEnabled();
            if (notifications != null) {
                channel = notifications.getNotificationChannel(ProactiveDeliveryContract.CHANNEL_ID);
            }
            if (!enabled || (channel != null && channel.getImportance() == 0)) {
                z = false;
            }
            objM392constructorimpl3 = Result.m392constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            objM392constructorimpl3 = Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl3)) {
            objM392constructorimpl3 = false;
        }
        boolean notificationsAllowed = ((Boolean) objM392constructorimpl3).booleanValue();
        return new PhoneStateSnapshot(ringer, filter, call, session, VoiceController.INSTANCE.isSpeaking(), notificationsAllowed, VoiceController.INSTANCE.getHasEngine());
    }

    public final PhoneStateSnapshot applyFake(PhoneStateSnapshot base, String fakeState) {
        String lowerCase;
        String string;
        Intrinsics.checkNotNullParameter(base, "base");
        if (fakeState == null || (string = StringsKt.trim((CharSequence) fakeState).toString()) == null) {
            lowerCase = null;
        } else {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        if (lowerCase == null) {
            return base;
        }
        switch (lowerCase.hashCode()) {
            case -902327211:
                return !lowerCase.equals("silent") ? base : PhoneStateSnapshot.copy$default(base, RingerKind.SILENT, null, false, false, false, false, false, 126, null);
            case 0:
                if (!lowerCase.equals("")) {
                }
                return base;
            case 99610:
                return !lowerCase.equals(FAKE_DND) ? base : PhoneStateSnapshot.copy$default(base, null, InterruptionKind.PRIORITY, false, false, false, false, false, 125, null);
            case 3045982:
                return !lowerCase.equals("call") ? base : PhoneStateSnapshot.copy$default(base, null, null, true, false, false, false, false, 123, null);
            case 3387192:
                if (!lowerCase.equals(FAKE_NONE)) {
                }
                return base;
            case 1984987798:
                return !lowerCase.equals(FAKE_SESSION) ? base : PhoneStateSnapshot.copy$default(base, null, null, false, true, false, false, false, 119, null);
            case 2124001807:
                return !lowerCase.equals(FAKE_NO_NOTIFICATIONS) ? base : PhoneStateSnapshot.copy$default(base, null, null, false, false, false, false, false, 95, null);
            case 2131379217:
                return !lowerCase.equals(FAKE_NO_VOICE) ? base : PhoneStateSnapshot.copy$default(base, null, null, false, false, false, false, false, 63, null);
            default:
                return base;
        }
    }

    public final Set<String> knownFakeStates() {
        return SetsKt.setOf((Object[]) new String[]{FAKE_NONE, "silent", FAKE_DND, "call", FAKE_SESSION, FAKE_NO_NOTIFICATIONS, FAKE_NO_VOICE});
    }

    private final boolean isCallActive(Context context, AudioManager audio) {
        Object objM392constructorimpl;
        Integer phoneState;
        Object objM392constructorimpl2;
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
            try {
                Result.Companion companion = Result.INSTANCE;
                PhoneStateReader phoneStateReader = this;
                objM392constructorimpl = Result.m392constructorimpl(telephony != null ? Integer.valueOf(telephony.getCallState()) : null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = null;
            }
            phoneState = (Integer) objM392constructorimpl;
        } else {
            phoneState = null;
        }
        boolean telephonyCall = (phoneState != null && phoneState.intValue() == 1) || (phoneState != null && phoneState.intValue() == 2);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            PhoneStateReader phoneStateReader2 = this;
            objM392constructorimpl2 = Result.m392constructorimpl(audio != null ? Integer.valueOf(audio.getMode()) : null);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        Integer audioMode = (Integer) (Result.m398isFailureimpl(objM392constructorimpl2) ? null : objM392constructorimpl2);
        boolean audioCall = (audioMode != null && audioMode.intValue() == 2) || (audioMode != null && audioMode.intValue() == 3);
        return telephonyCall || audioCall;
    }
}
