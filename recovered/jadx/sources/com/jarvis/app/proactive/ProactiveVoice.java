package com.jarvis.app.proactive;

import android.content.Context;
import android.content.Intent;
import com.jarvis.app.VoiceController;
import com.jarvis.app.VoiceService;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProactiveVoice.kt */
/* JADX INFO: loaded from: classes23.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\u000b"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveVoice;", "", "<init>", "()V", "speak", "Lcom/jarvis/app/proactive/ProactiveVoice$Result;", "context", "Landroid/content/Context;", VoiceService.EXTRA_TEXT, "", "Result", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveVoice {
    public static final ProactiveVoice INSTANCE = new ProactiveVoice();

    private ProactiveVoice() {
    }

    /* JADX INFO: compiled from: ProactiveVoice.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveVoice$Result;", "", "requested", "", "note", "", "<init>", "(ZLjava/lang/String;)V", "getRequested", "()Z", "getNote", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Result {
        private final String note;
        private final boolean requested;

        public static /* synthetic */ Result copy$default(Result result, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = result.requested;
            }
            if ((i & 2) != 0) {
                str = result.note;
            }
            return result.copy(z, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getRequested() {
            return this.requested;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getNote() {
            return this.note;
        }

        public final Result copy(boolean requested, String note) {
            Intrinsics.checkNotNullParameter(note, "note");
            return new Result(requested, note);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.requested == result.requested && Intrinsics.areEqual(this.note, result.note);
        }

        public int hashCode() {
            return (Boolean.hashCode(this.requested) * 31) + this.note.hashCode();
        }

        public String toString() {
            return "Result(requested=" + this.requested + ", note=" + this.note + ")";
        }

        public Result(boolean requested, String note) {
            Intrinsics.checkNotNullParameter(note, "note");
            this.requested = requested;
            this.note = note;
        }

        public /* synthetic */ Result(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? "" : str);
        }

        public final boolean getRequested() {
            return this.requested;
        }

        public final String getNote() {
            return this.note;
        }
    }

    public final Result speak(Context context, String text) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        if (StringsKt.isBlank(text)) {
            return new Result(false, "empty_text");
        }
        if (!VoiceController.INSTANCE.getHasEngine()) {
            return new Result(false, "no_engine");
        }
        if (VoiceController.INSTANCE.isSpeaking()) {
            return new Result(false, "already_speaking");
        }
        AssistantState state = JarvisEvents.INSTANCE.getAssistantState().getValue();
        boolean session = VoiceController.INSTANCE.isLiveSession() || state == AssistantState.LISTENING || state == AssistantState.THINKING || state == AssistantState.SPEAKING;
        if (session) {
            return new Result(false, "session_active");
        }
        Intent intent = new Intent(context, (Class<?>) VoiceService.class).setAction(VoiceService.ACTION_SAY).putExtra(VoiceService.EXTRA_TEXT, text);
        Intrinsics.checkNotNullExpressionValue(intent, "putExtra(...)");
        try {
            kotlin.Result.Companion companion = kotlin.Result.INSTANCE;
            ProactiveVoice proactiveVoice = this;
            context.startService(intent);
            objM392constructorimpl = kotlin.Result.m392constructorimpl(new Result(true, "sent"));
        } catch (Throwable th) {
            kotlin.Result.Companion companion2 = kotlin.Result.INSTANCE;
            objM392constructorimpl = kotlin.Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable e = kotlin.Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (e != null) {
            objM392constructorimpl = new Result(false, "start_failed:" + e.getMessage());
        }
        return (Result) objM392constructorimpl;
    }
}
