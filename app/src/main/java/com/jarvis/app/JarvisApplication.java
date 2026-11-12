package com.jarvis.app;

import android.app.Application;
import android.util.Log;
import com.jarvis.app.companion.CompanionMemory;
import com.jarvis.app.companion.SystemStateObserver;
import com.jarvis.app.companion.UsageStatsObserver;
import com.jarvis.core.storage.ConversationJournal;
import com.jarvis.core.storage.RetentionPolicy;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: JarvisApplication.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0002¨\u0006\u0007"}, d2 = {"Lcom/jarvis/app/JarvisApplication;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "restoreUiHistory", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        SecureKeyMigration.INSTANCE.migrate(this);
        SystemStateObserver.INSTANCE.ensureStarted(this);
        UsageStatsObserver.INSTANCE.ensureStarted(this);
        new EventRoutineRunner(this).start();
        CompanionMemory.INSTANCE.get(this).expireNow();
        ConversationJournal.INSTANCE.install(this);
        ConversationJournal.expire$default(ConversationJournal.INSTANCE, 0L, 1, null);
        restoreUiHistory();
        BrainGraph.INSTANCE.preloadChatModel(this);
    }

    private final void restoreUiHistory() {
        new Thread(new Runnable() { // from class: com.jarvis.app.JarvisApplication$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                JarvisApplication.restoreUiHistory$lambda$0(this.f$0);
            }
        }, "chat-history-restore").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void restoreUiHistory$lambda$0(JarvisApplication this$0) {
        Object objM392constructorimpl;
        long startedAt = System.currentTimeMillis();
        try {
            Result.Companion companion = Result.INSTANCE;
            List<ConversationJournal.Entry> listRecent = ConversationJournal.INSTANCE.recent(60, RetentionPolicy.CONVERSATION_TTL_MS);
            ConversationStore.INSTANCE.restore(listRecent);
            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.ChatHistory", "ui restore n=" + listRecent.size() + " in " + (System.currentTimeMillis() - startedAt) + "ms")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            Log.w("Jarvis.ChatHistory", "ui restore failed: " + it.getMessage());
        }
    }
}
