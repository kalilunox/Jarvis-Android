package com.jarvis.brain;

import android.content.Context;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.storage.FactAddResult;
import com.jarvis.core.storage.FactsRepository;
import com.jarvis.core.storage.JarvisDb;
import com.jarvis.core.storage.JarvisSchema;
import com.jarvis.core.storage.LegacyFactsMigration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LongTermMemory.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001(B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0018H\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0013H\u0016J\b\u0010%\u001a\u00020\u001aH\u0016J\f\u0010&\u001a\u00020\u0016*\u00020'H\u0002R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006)"}, d2 = {"Lcom/jarvis/brain/LongTermMemory;", "Lcom/jarvis/brain/LongTermMemoryInterface;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "migrationDone", "Ljava/util/concurrent/atomic/AtomicBoolean;", "repo", "Lcom/jarvis/core/storage/FactsRepository;", "getRepo", "()Lcom/jarvis/core/storage/FactsRepository;", "repo$delegate", "Lkotlin/Lazy;", "ensureReady", "", "retrieve", "", "Lcom/jarvis/brain/LongTermMemory$MemoryFact;", SearchIntents.EXTRA_QUERY, "", "limit", "", "add", "", VoiceService.EXTRA_TEXT, "category", "source", "all", "remove", "id", "", "clear", "count", "toMemoryFact", "Lcom/jarvis/core/storage/FactsRepository$StoredFact;", "MemoryFact", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LongTermMemory implements LongTermMemoryInterface {
    private final Context appContext;
    private final ReentrantLock lock;
    private final AtomicBoolean migrationDone;

    /* JADX INFO: renamed from: repo$delegate, reason: from kotlin metadata */
    private final Lazy repo;

    public LongTermMemory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        this.lock = new ReentrantLock();
        this.migrationDone = new AtomicBoolean(false);
        this.repo = LazyKt.lazy(new Function0() { // from class: com.jarvis.brain.LongTermMemory$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LongTermMemory.repo_delegate$lambda$0(this.f$0);
            }
        });
    }

    private final FactsRepository getRepo() {
        return (FactsRepository) this.repo.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FactsRepository repo_delegate$lambda$0(LongTermMemory this$0) {
        JarvisDb.Companion companion = JarvisDb.INSTANCE;
        Context appContext = this$0.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        return new FactsRepository(companion.get(appContext));
    }

    private final void ensureReady() {
        if (this.migrationDone.compareAndSet(false, true)) {
            try {
                Result.Companion companion = Result.INSTANCE;
                LongTermMemory $this$ensureReady_u24lambda_u240 = this;
                LegacyFactsMigration legacyFactsMigration = new LegacyFactsMigration($this$ensureReady_u24lambda_u240.getRepo());
                Context appContext = $this$ensureReady_u24lambda_u240.appContext;
                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                Result.m392constructorimpl(legacyFactsMigration.migrateIfNeeded(appContext));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    @Override // com.jarvis.brain.LongTermMemoryInterface
    public List<MemoryFact> retrieve(String query, int limit) {
        Intrinsics.checkNotNullParameter(query, "query");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            ensureReady();
            Iterable $this$map$iv = getRepo().retrieve(query, limit);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                FactsRepository.StoredFact it = (FactsRepository.StoredFact) item$iv$iv;
                destination$iv$iv.add(toMemoryFact(it));
            }
            return (List) destination$iv$iv;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.brain.LongTermMemoryInterface
    public boolean add(String text, String category, String source) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(source, "source");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            ensureReady();
            return getRepo().add(text, (8 & 2) != 0 ? "general" : category, (8 & 4) != 0 ? JarvisSchema.ROLE_USER : source, (8 & 8) != 0 ? System.currentTimeMillis() : 0L) == FactAddResult.STORED;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.brain.LongTermMemoryInterface
    public List<MemoryFact> all() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            ensureReady();
            Iterable $this$map$iv = FactsRepository.list$default(getRepo(), 0L, 1, null);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                FactsRepository.StoredFact it = (FactsRepository.StoredFact) item$iv$iv;
                destination$iv$iv.add(toMemoryFact(it));
            }
            return (List) destination$iv$iv;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.brain.LongTermMemoryInterface
    public boolean remove(long id) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            ensureReady();
            return getRepo().deleteRow(id);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.brain.LongTermMemoryInterface
    public void clear() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            ensureReady();
            getRepo().clearAll();
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.brain.LongTermMemoryInterface
    public int count() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            ensureReady();
            return getRepo().count();
        } finally {
            reentrantLock.unlock();
        }
    }

    private final MemoryFact toMemoryFact(FactsRepository.StoredFact $this$toMemoryFact) {
        return new MemoryFact($this$toMemoryFact.getRowId(), $this$toMemoryFact.getText(), $this$toMemoryFact.getCategory(), $this$toMemoryFact.getCreatedAt(), $this$toMemoryFact.getSource());
    }

    /* JADX INFO: compiled from: LongTermMemory.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/jarvis/brain/LongTermMemory$MemoryFact;", "", "id", "", VoiceService.EXTRA_TEXT, "", "category", "createdAt", "source", "<init>", "(JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getId", "()J", "getText", "()Ljava/lang/String;", "getCategory", "getCreatedAt", "getSource", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MemoryFact {
        private final String category;
        private final long createdAt;
        private final long id;
        private final String source;
        private final String text;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCategory() {
            return this.category;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        public final MemoryFact copy(long id, String text, String category, long createdAt, String source) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(source, "source");
            return new MemoryFact(id, text, category, createdAt, source);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MemoryFact)) {
                return false;
            }
            MemoryFact memoryFact = (MemoryFact) other;
            return this.id == memoryFact.id && Intrinsics.areEqual(this.text, memoryFact.text) && Intrinsics.areEqual(this.category, memoryFact.category) && this.createdAt == memoryFact.createdAt && Intrinsics.areEqual(this.source, memoryFact.source);
        }

        public int hashCode() {
            return (((((((Long.hashCode(this.id) * 31) + this.text.hashCode()) * 31) + this.category.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + this.source.hashCode();
        }

        public String toString() {
            return "MemoryFact(id=" + this.id + ", text=" + this.text + ", category=" + this.category + ", createdAt=" + this.createdAt + ", source=" + this.source + ")";
        }

        public MemoryFact(long id, String text, String category, long createdAt, String source) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(source, "source");
            this.id = id;
            this.text = text;
            this.category = category;
            this.createdAt = createdAt;
            this.source = source;
        }

        public final long getId() {
            return this.id;
        }

        public final String getText() {
            return this.text;
        }

        public final String getCategory() {
            return this.category;
        }

        public final long getCreatedAt() {
            return this.createdAt;
        }

        public final String getSource() {
            return this.source;
        }
    }
}
