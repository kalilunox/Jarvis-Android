package com.jarvis.core.storage;

import android.content.Context;
import android.util.Log;
import com.jarvis.app.VoiceService;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.privacy.SensitivityClassifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ConversationJournal.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002/0B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001b\u001a\u00020\u0005J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001b\u001a\u00020\u0005J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0012J\u0018\u0010%\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010\u00052\u0006\u0010'\u001a\u00020\u0019J\u0010\u0010(\u001a\u00020#2\b\b\u0002\u0010)\u001a\u00020\u0012J\u0006\u0010*\u001a\u00020\u0015J\b\u0010+\u001a\u0004\u0018\u00010,J\u000e\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/jarvis/core/storage/ConversationJournal;", "", "<init>", "()V", "TAG", "", "repo", "Lcom/jarvis/core/storage/ConversationRepository;", "executor", "Ljava/util/concurrent/Executor;", "value", "sessionId", "getSessionId", "()Ljava/lang/String;", "lastLock", "lastRole", "lastText", "lastAt", "", "lastId", "install", "", "context", "Landroid/content/Context;", "isInstalled", "", "appendUser", VoiceService.EXTRA_TEXT, "appendAssistant", "append", "role", "recent", "", "Lcom/jarvis/core/storage/ConversationJournal$Entry;", "limit", "", "maxAgeMs", "setKeep", "id", "keep", "expire", "now", "clearAll", "stats", "Lcom/jarvis/core/storage/ConversationJournal$Stats;", "containsText", "needle", "Entry", "Stats", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConversationJournal {
    public static final ConversationJournal INSTANCE = new ConversationJournal();
    private static final String TAG = "Jarvis.ChatJournal";
    private static volatile Executor executor;
    private static long lastAt;
    private static String lastId;
    private static final Object lastLock;
    private static String lastRole;
    private static String lastText;
    private static volatile ConversationRepository repo;
    private static volatile String sessionId;

    private ConversationJournal() {
    }

    /* JADX INFO: compiled from: ConversationJournal.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/storage/ConversationJournal$Entry;", "", "id", "", "role", VoiceService.EXTRA_TEXT, "createdAt", "", "keep", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZ)V", "getId", "()Ljava/lang/String;", "getRole", "getText", "getCreatedAt", "()J", "getKeep", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Entry {
        private final long createdAt;
        private final String id;
        private final boolean keep;
        private final String role;
        private final String text;

        public static /* synthetic */ Entry copy$default(Entry entry, String str, String str2, String str3, long j, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = entry.id;
            }
            if ((i & 2) != 0) {
                str2 = entry.role;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = entry.text;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                j = entry.createdAt;
            }
            long j2 = j;
            if ((i & 16) != 0) {
                z = entry.keep;
            }
            return entry.copy(str, str4, str5, j2, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRole() {
            return this.role;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getKeep() {
            return this.keep;
        }

        public final Entry copy(String id, String role, String text, long createdAt, boolean keep) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(text, "text");
            return new Entry(id, role, text, createdAt, keep);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return Intrinsics.areEqual(this.id, entry.id) && Intrinsics.areEqual(this.role, entry.role) && Intrinsics.areEqual(this.text, entry.text) && this.createdAt == entry.createdAt && this.keep == entry.keep;
        }

        public int hashCode() {
            return (((((((this.id.hashCode() * 31) + this.role.hashCode()) * 31) + this.text.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Boolean.hashCode(this.keep);
        }

        public String toString() {
            return "Entry(id=" + this.id + ", role=" + this.role + ", text=" + this.text + ", createdAt=" + this.createdAt + ", keep=" + this.keep + ")";
        }

        public Entry(String id, String role, String text, long createdAt, boolean keep) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(text, "text");
            this.id = id;
            this.role = role;
            this.text = text;
            this.createdAt = createdAt;
            this.keep = keep;
        }

        public final String getId() {
            return this.id;
        }

        public final String getRole() {
            return this.role;
        }

        public final String getText() {
            return this.text;
        }

        public final long getCreatedAt() {
            return this.createdAt;
        }

        public final boolean getKeep() {
            return this.keep;
        }
    }

    /* JADX INFO: compiled from: ConversationJournal.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/storage/ConversationJournal$Stats;", "", "count", "", "keepCount", "oldestAt", "", "newestAt", "<init>", "(IIJJ)V", "getCount", "()I", "getKeepCount", "getOldestAt", "()J", "getNewestAt", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Stats {
        private final int count;
        private final int keepCount;
        private final long newestAt;
        private final long oldestAt;

        public static /* synthetic */ Stats copy$default(Stats stats, int i, int i2, long j, long j2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = stats.count;
            }
            if ((i3 & 2) != 0) {
                i2 = stats.keepCount;
            }
            int i4 = i2;
            if ((i3 & 4) != 0) {
                j = stats.oldestAt;
            }
            long j3 = j;
            if ((i3 & 8) != 0) {
                j2 = stats.newestAt;
            }
            return stats.copy(i, i4, j3, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getKeepCount() {
            return this.keepCount;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getOldestAt() {
            return this.oldestAt;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getNewestAt() {
            return this.newestAt;
        }

        public final Stats copy(int count, int keepCount, long oldestAt, long newestAt) {
            return new Stats(count, keepCount, oldestAt, newestAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Stats)) {
                return false;
            }
            Stats stats = (Stats) other;
            return this.count == stats.count && this.keepCount == stats.keepCount && this.oldestAt == stats.oldestAt && this.newestAt == stats.newestAt;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.count) * 31) + Integer.hashCode(this.keepCount)) * 31) + Long.hashCode(this.oldestAt)) * 31) + Long.hashCode(this.newestAt);
        }

        public String toString() {
            return "Stats(count=" + this.count + ", keepCount=" + this.keepCount + ", oldestAt=" + this.oldestAt + ", newestAt=" + this.newestAt + ")";
        }

        public Stats(int count, int keepCount, long oldestAt, long newestAt) {
            this.count = count;
            this.keepCount = keepCount;
            this.oldestAt = oldestAt;
            this.newestAt = newestAt;
        }

        public final int getCount() {
            return this.count;
        }

        public final int getKeepCount() {
            return this.keepCount;
        }

        public final long getOldestAt() {
            return this.oldestAt;
        }

        public final long getNewestAt() {
            return this.newestAt;
        }
    }

    static {
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        sessionId = string;
        lastLock = new Object();
    }

    public final String getSessionId() {
        return sessionId;
    }

    public final void install(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (this) {
            if (repo != null) {
                return;
            }
            JarvisDb.Companion companion = JarvisDb.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            repo = new ConversationRepository(companion.get(applicationContext));
            executor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.jarvis.core.storage.ConversationJournal$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    return ConversationJournal.install$lambda$0$0(runnable);
                }
            });
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            sessionId = string;
            Log.i(TAG, "installed session=" + StringsKt.take(sessionId, 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread install$lambda$0$0(Runnable runnable) {
        Thread $this$install_u24lambda_u240_u240_u240 = new Thread(runnable, "jarvis-chat-journal");
        $this$install_u24lambda_u240_u240_u240.setDaemon(true);
        return $this$install_u24lambda_u240_u240_u240;
    }

    public final boolean isInstalled() {
        return repo != null;
    }

    public final String appendUser(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return append(JarvisSchema.ROLE_USER, text);
    }

    public final String appendAssistant(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return append(JarvisSchema.ROLE_ASSISTANT, text);
    }

    private final String append(final String role, String text) throws Throwable {
        final ConversationRepository r = repo;
        String str = null;
        if (r == null) {
            return null;
        }
        final String masked = StringsKt.trim((CharSequence) SensitivityClassifier.INSTANCE.mask(text)).toString();
        if ((masked.length() == 0) || SensitivityClassifier.classify$default(SensitivityClassifier.INSTANCE, masked, null, 2, null) == Sensitivity.SECRET) {
            return null;
        }
        final long now = System.currentTimeMillis();
        synchronized (lastLock) {
            try {
                if (ConversationDedupe.isDuplicate$default(ConversationDedupe.INSTANCE, lastRole, lastText, lastAt, role, masked, now, 0L, 64, null)) {
                    try {
                        str = lastId;
                    } catch (Throwable th) {
                        th = th;
                    }
                } else {
                    final String id = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(id, "toString(...)");
                    lastRole = role;
                    lastText = masked;
                    lastAt = now;
                    lastId = id;
                    Executor exec = executor;
                    if (exec != null) {
                        try {
                            exec.execute(new Runnable() { // from class: com.jarvis.core.storage.ConversationJournal$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    ConversationJournal.append$lambda$0$0(r, id, role, masked, now);
                                }
                            });
                            return id;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    throw th;
                }
                return str;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void append$lambda$0$0(ConversationRepository $r, String $id, String $role, String $masked, long $now) {
        ConversationJournal conversationJournal = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            $r.append($id, $role, $masked, sessionId, $now);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final List<Entry> recent(int limit, long maxAgeMs) {
        Object objM392constructorimpl;
        ConversationRepository r = repo;
        if (r == null) {
            return CollectionsKt.emptyList();
        }
        long startedAt = System.currentTimeMillis();
        try {
            Result.Companion companion = Result.INSTANCE;
            ConversationJournal conversationJournal = this;
            objM392constructorimpl = Result.m392constructorimpl(ConversationRepository.loadRecent$default(r, limit, maxAgeMs, 0L, 4, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        List rows = (List) objM392constructorimpl;
        Log.i(TAG, "recent limit=" + limit + " age=" + maxAgeMs + "ms size=" + rows.size() + " in " + (System.currentTimeMillis() - startedAt) + "ms");
        List $this$map$iv = rows;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ConversationRepository.Message it = (ConversationRepository.Message) item$iv$iv;
            destination$iv$iv.add(new Entry(it.getId(), it.getRole(), it.getText(), it.getCreatedAt(), it.getKeep()));
            rows = rows;
        }
        return (List) destination$iv$iv;
    }

    public final void setKeep(final String id, final boolean keep) {
        Executor executor2;
        final ConversationRepository r = repo;
        if (r != null && id != null && (executor2 = executor) != null) {
            executor2.execute(new Runnable() { // from class: com.jarvis.core.storage.ConversationJournal$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ConversationJournal.setKeep$lambda$0(r, id, keep);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setKeep$lambda$0(ConversationRepository $r, String $target, boolean $keep) {
        ConversationJournal conversationJournal = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            Result.m392constructorimpl(Boolean.valueOf($r.setKeep($target, $keep)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static /* synthetic */ int expire$default(ConversationJournal conversationJournal, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return conversationJournal.expire(j);
    }

    public final int expire(long now) {
        Object objM392constructorimpl;
        ConversationRepository r = repo;
        if (r == null) {
            return 0;
        }
        long startedAt = System.currentTimeMillis();
        try {
            Result.Companion companion = Result.INSTANCE;
            ConversationJournal conversationJournal = this;
            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(r.expire(now)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = 0;
        }
        int removed = ((Number) objM392constructorimpl).intValue();
        Log.i(TAG, "expire removed=" + removed + " in " + (System.currentTimeMillis() - startedAt) + "ms");
        return removed;
    }

    public final void clearAll() {
        final ConversationRepository r = repo;
        if (r == null) {
            return;
        }
        synchronized (lastLock) {
            lastRole = null;
            lastText = null;
            lastAt = 0L;
            lastId = null;
            Unit unit = Unit.INSTANCE;
        }
        Executor executor2 = executor;
        if (executor2 != null) {
            executor2.execute(new Runnable() { // from class: com.jarvis.core.storage.ConversationJournal$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    ConversationJournal.clearAll$lambda$1(r);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clearAll$lambda$1(ConversationRepository $r) {
        ConversationJournal conversationJournal = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            $r.clear();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final Stats stats() {
        Object objM392constructorimpl;
        ConversationRepository r = repo;
        if (r == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            ConversationJournal conversationJournal = this;
            Pair<Long, Long> pairBoundaries = r.boundaries();
            objM392constructorimpl = Result.m392constructorimpl(new Stats(r.count(), r.keepCount(), pairBoundaries.getFirst().longValue(), pairBoundaries.getSecond().longValue()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (Stats) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    public final boolean containsText(String needle) {
        Object objM392constructorimpl;
        boolean z;
        Intrinsics.checkNotNullParameter(needle, "needle");
        try {
            Result.Companion companion = Result.INSTANCE;
            ConversationJournal conversationJournal = this;
            ConversationRepository conversationRepository = repo;
            if (conversationRepository != null) {
                z = true;
                if (!conversationRepository.containsText(needle)) {
                    z = false;
                }
            } else {
                z = false;
            }
            objM392constructorimpl = Result.m392constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        return ((Boolean) objM392constructorimpl).booleanValue();
    }
}
