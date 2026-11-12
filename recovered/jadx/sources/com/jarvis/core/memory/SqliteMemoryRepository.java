package com.jarvis.core.memory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.companion.Sensitivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SqliteMemoryRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\u00020\u0001:\u0002()B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u00192\u0006\u0010\u0018\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0010H\u0002J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020'H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006*"}, d2 = {"Lcom/jarvis/core/memory/SqliteMemoryRepository;", "Lcom/jarvis/core/memory/MemoryRepository;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "kotlin.jvm.PlatformType", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "db$delegate", "Lkotlin/Lazy;", "save", "Lcom/jarvis/core/memory/MemoryEntry;", VoiceService.EXTRA_ENTRY, "get", "id", "", "update", "delete", "", SearchIntents.EXTRA_QUERY, "", "Lcom/jarvis/core/memory/MemoryQuery;", "expire", "", "nowMillis", "", "clearAll", "", "getUnlocked", "toValues", "Landroid/content/ContentValues;", "e", "read", "c", "Landroid/database/Cursor;", "Helper", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SqliteMemoryRepository implements MemoryRepository {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DB_NAME = "jarvis_companion_memory.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE = "companion_memory";

    /* JADX INFO: renamed from: db$delegate, reason: from kotlin metadata */
    private final Lazy db;
    private final ReentrantLock lock;

    public SqliteMemoryRepository(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.lock = new ReentrantLock();
        this.db = LazyKt.lazy(new Function0() { // from class: com.jarvis.core.memory.SqliteMemoryRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SqliteMemoryRepository.db_delegate$lambda$0(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SQLiteDatabase db_delegate$lambda$0(Context $context) {
        Context applicationContext = $context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return new Helper(applicationContext).getWritableDatabase();
    }

    private final SQLiteDatabase getDb() {
        return (SQLiteDatabase) this.db.getValue();
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public MemoryEntry save(MemoryEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            getDb().insertWithOnConflict(TABLE, null, toValues(entry), 5);
            return entry;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public MemoryEntry get(String id) {
        MemoryEntry memoryEntry;
        Intrinsics.checkNotNullParameter(id, "id");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Cursor cursorRawQuery = getDb().rawQuery("SELECT * FROM companion_memory WHERE id = ? LIMIT 1", new String[]{id});
            try {
                Cursor c = cursorRawQuery;
                if (c.moveToFirst()) {
                    Intrinsics.checkNotNull(c);
                    memoryEntry = read(c);
                } else {
                    memoryEntry = null;
                }
                CloseableKt.closeFinally(cursorRawQuery, null);
                reentrantLock.unlock();
                return memoryEntry;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            reentrantLock.unlock();
            throw th3;
        }
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public MemoryEntry update(MemoryEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int n = getDb().update(TABLE, toValues(entry), "id = ?", new String[]{entry.getId()});
            return n > 0 ? entry : null;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public boolean delete(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            MemoryEntry cur = getUnlocked(id);
            boolean z = false;
            if (cur != null) {
                MemoryEntry updated = cur.copy((8559 & 1) != 0 ? cur.id : null, (8559 & 2) != 0 ? cur.type : null, (8559 & 4) != 0 ? cur.content : null, (8559 & 8) != 0 ? cur.provenance : null, (8559 & 16) != 0 ? cur.confidence : 0.0f, (8559 & 32) != 0 ? cur.sensitivity : null, (8559 & 64) != 0 ? cur.createdAtMillis : 0L, (8559 & 128) != 0 ? cur.updatedAtMillis : System.currentTimeMillis(), (8559 & 256) != 0 ? cur.firstObservedAtMillis : 0L, (8559 & 512) != 0 ? cur.lastObservedAtMillis : 0L, (8559 & 1024) != 0 ? cur.expiresAtMillis : null, (8559 & 2048) != 0 ? cur.evidenceCount : 0, (8559 & 4096) != 0 ? cur.evidenceObservationIds : null, (8559 & 8192) != 0 ? cur.packageName : null, (8559 & 16384) != 0 ? cur.entities : null, (8559 & 32768) != 0 ? cur.status : MemoryStatus.DELETED, (8559 & 65536) != 0 ? cur.disposition : null);
                if (getDb().update(TABLE, toValues(updated), "id = ?", new String[]{id}) > 0) {
                    z = true;
                }
            }
            return z;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public List<MemoryEntry> query(final MemoryQuery query) {
        Intrinsics.checkNotNullParameter(query, "query");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            List all = new ArrayList();
            String string = null;
            Cursor cursorRawQuery = getDb().rawQuery("SELECT * FROM companion_memory ORDER BY updated_at DESC", null);
            try {
                Cursor c = cursorRawQuery;
                while (c.moveToNext()) {
                    Intrinsics.checkNotNull(c);
                    all.add(read(c));
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery, null);
                String textContains = query.getTextContains();
                if (textContains != null) {
                    String lowerCase = textContains.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    if (lowerCase != null) {
                        string = StringsKt.trim((CharSequence) lowerCase).toString();
                    }
                }
                if (string == null) {
                    string = "";
                }
                final String needle = string;
                List<MemoryEntry> list = SequencesKt.toList(SequencesKt.take(SequencesKt.filter(CollectionsKt.asSequence(all), new Function1() { // from class: com.jarvis.core.memory.SqliteMemoryRepository$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(SqliteMemoryRepository.query$lambda$0$1(query, needle, (MemoryEntry) obj));
                    }
                }), RangesKt.coerceAtLeast(query.getLimit(), 0)));
                reentrantLock.unlock();
                return list;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            reentrantLock.unlock();
            throw th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean query$lambda$0$1(MemoryQuery $query, String $needle, MemoryEntry e) {
        Intrinsics.checkNotNullParameter(e, "e");
        if (!$query.getIncludeDeleted() && e.getStatus() == MemoryStatus.DELETED) {
            return false;
        }
        if (!$query.getIncludeExpired() && e.getStatus() == MemoryStatus.EXPIRED) {
            return false;
        }
        if ($query.getType() != null && e.getType() != $query.getType()) {
            return false;
        }
        if ($query.getStatus() != null && e.getStatus() != $query.getStatus()) {
            return false;
        }
        if ($query.getProvenance() != null && e.getProvenance() != $query.getProvenance()) {
            return false;
        }
        if ($query.getPackageName() != null && !Intrinsics.areEqual(e.getPackageName(), $query.getPackageName())) {
            return false;
        }
        if ($needle.length() > 0) {
            String lowerCase = e.getContent().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (!StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) $needle, false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public int expire(long nowMillis) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            List<MemoryEntry> all = new ArrayList();
            Cursor cursorRawQuery = getDb().rawQuery("SELECT * FROM companion_memory", null);
            try {
                Cursor c = cursorRawQuery;
                while (c.moveToNext()) {
                    Intrinsics.checkNotNull(c);
                    all.add(read(c));
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery, null);
                int n = 0;
                for (MemoryEntry e : all) {
                    if (e.getStatus() != MemoryStatus.DELETED && e.getStatus() != MemoryStatus.EXPIRED) {
                        Long exp = e.getExpiresAtMillis();
                        if (exp != null && nowMillis >= exp.longValue()) {
                            getDb().update(TABLE, toValues(e.copy((8559 & 1) != 0 ? e.id : null, (8559 & 2) != 0 ? e.type : null, (8559 & 4) != 0 ? e.content : null, (8559 & 8) != 0 ? e.provenance : null, (8559 & 16) != 0 ? e.confidence : 0.0f, (8559 & 32) != 0 ? e.sensitivity : null, (8559 & 64) != 0 ? e.createdAtMillis : 0L, (8559 & 128) != 0 ? e.updatedAtMillis : nowMillis, (8559 & 256) != 0 ? e.firstObservedAtMillis : 0L, (8559 & 512) != 0 ? e.lastObservedAtMillis : 0L, (8559 & 1024) != 0 ? e.expiresAtMillis : null, (8559 & 2048) != 0 ? e.evidenceCount : 0, (8559 & 4096) != 0 ? e.evidenceObservationIds : null, (8559 & 8192) != 0 ? e.packageName : null, (8559 & 16384) != 0 ? e.entities : null, (8559 & 32768) != 0 ? e.status : MemoryStatus.EXPIRED, (8559 & 65536) != 0 ? e.disposition : null)), "id = ?", new String[]{e.getId()});
                            n++;
                        } else {
                            long staleAfter = MemoryRetention.INSTANCE.staleAfterMillis(e.getType(), e.getProvenance());
                            if (e.getStatus() == MemoryStatus.ACTIVE && nowMillis - e.getLastObservedAtMillis() >= staleAfter) {
                                getDb().update(TABLE, toValues(e.copy((8559 & 1) != 0 ? e.id : null, (8559 & 2) != 0 ? e.type : null, (8559 & 4) != 0 ? e.content : null, (8559 & 8) != 0 ? e.provenance : null, (8559 & 16) != 0 ? e.confidence : 0.0f, (8559 & 32) != 0 ? e.sensitivity : null, (8559 & 64) != 0 ? e.createdAtMillis : 0L, (8559 & 128) != 0 ? e.updatedAtMillis : nowMillis, (8559 & 256) != 0 ? e.firstObservedAtMillis : 0L, (8559 & 512) != 0 ? e.lastObservedAtMillis : 0L, (8559 & 1024) != 0 ? e.expiresAtMillis : null, (8559 & 2048) != 0 ? e.evidenceCount : 0, (8559 & 4096) != 0 ? e.evidenceObservationIds : null, (8559 & 8192) != 0 ? e.packageName : null, (8559 & 16384) != 0 ? e.entities : null, (8559 & 32768) != 0 ? e.status : MemoryStatus.STALE, (8559 & 65536) != 0 ? e.disposition : null)), "id = ?", new String[]{e.getId()});
                                n++;
                            }
                        }
                    }
                }
                reentrantLock.unlock();
                return n;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            reentrantLock.unlock();
            throw th3;
        }
    }

    @Override // com.jarvis.core.memory.MemoryRepository
    public void clearAll() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            getDb().delete(TABLE, null, null);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    private final MemoryEntry getUnlocked(String id) throws IOException {
        MemoryEntry memoryEntry;
        Cursor cursorRawQuery = getDb().rawQuery("SELECT * FROM companion_memory WHERE id = ? LIMIT 1", new String[]{id});
        try {
            Cursor c = cursorRawQuery;
            if (c.moveToFirst()) {
                Intrinsics.checkNotNull(c);
                memoryEntry = read(c);
            } else {
                memoryEntry = null;
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return memoryEntry;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final ContentValues toValues(MemoryEntry e) {
        ContentValues $this$toValues_u24lambda_u240 = new ContentValues();
        $this$toValues_u24lambda_u240.put("id", e.getId());
        $this$toValues_u24lambda_u240.put("type", e.getType().name());
        $this$toValues_u24lambda_u240.put("content", e.getContent());
        $this$toValues_u24lambda_u240.put("provenance", e.getProvenance().name());
        $this$toValues_u24lambda_u240.put("confidence", Float.valueOf(e.getConfidence()));
        $this$toValues_u24lambda_u240.put("sensitivity", e.getSensitivity().name());
        $this$toValues_u24lambda_u240.put("created_at", Long.valueOf(e.getCreatedAtMillis()));
        $this$toValues_u24lambda_u240.put("updated_at", Long.valueOf(e.getUpdatedAtMillis()));
        $this$toValues_u24lambda_u240.put("first_observed_at", Long.valueOf(e.getFirstObservedAtMillis()));
        $this$toValues_u24lambda_u240.put("last_observed_at", Long.valueOf(e.getLastObservedAtMillis()));
        if (e.getExpiresAtMillis() == null) {
            $this$toValues_u24lambda_u240.putNull("expires_at");
        } else {
            $this$toValues_u24lambda_u240.put("expires_at", e.getExpiresAtMillis());
        }
        $this$toValues_u24lambda_u240.put("evidence_count", Integer.valueOf(e.getEvidenceCount()));
        $this$toValues_u24lambda_u240.put("evidence_ids", CollectionsKt.joinToString$default(e.getEvidenceObservationIds(), ",", null, null, 0, null, null, 62, null));
        $this$toValues_u24lambda_u240.put("package_name", e.getPackageName());
        $this$toValues_u24lambda_u240.put("entities", INSTANCE.encodeEntities(e.getEntities()));
        $this$toValues_u24lambda_u240.put("status", e.getStatus().name());
        $this$toValues_u24lambda_u240.put("disposition", e.getDisposition().name());
        return $this$toValues_u24lambda_u240;
    }

    private final MemoryEntry read(Cursor c) {
        int expIdx = c.getColumnIndexOrThrow("expires_at");
        Long expires = c.isNull(expIdx) ? null : Long.valueOf(c.getLong(expIdx));
        String evidenceRaw = c.getString(c.getColumnIndexOrThrow("evidence_ids"));
        if (evidenceRaw == null) {
            evidenceRaw = "";
        }
        String string = c.getString(c.getColumnIndexOrThrow("id"));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = c.getString(c.getColumnIndexOrThrow("type"));
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        MemoryKind memoryKindValueOf = MemoryKind.valueOf(string2);
        String string3 = c.getString(c.getColumnIndexOrThrow("content"));
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = c.getString(c.getColumnIndexOrThrow("provenance"));
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        MemoryProvenance memoryProvenanceValueOf = MemoryProvenance.valueOf(string4);
        float f = c.getFloat(c.getColumnIndexOrThrow("confidence"));
        String string5 = c.getString(c.getColumnIndexOrThrow("sensitivity"));
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        Sensitivity sensitivityValueOf = Sensitivity.valueOf(string5);
        long j = c.getLong(c.getColumnIndexOrThrow("created_at"));
        long j2 = c.getLong(c.getColumnIndexOrThrow("updated_at"));
        long j3 = c.getLong(c.getColumnIndexOrThrow("first_observed_at"));
        long j4 = c.getLong(c.getColumnIndexOrThrow("last_observed_at"));
        int i = c.getInt(c.getColumnIndexOrThrow("evidence_count"));
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) evidenceRaw, new char[]{','}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
            $this$map$iv = $this$map$iv;
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Iterable $this$filter$iv2 = $this$filter$iv;
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
            $this$filter$iv = $this$filter$iv2;
        }
        String string6 = c.getString(c.getColumnIndexOrThrow("package_name"));
        Map<String, String> mapDecodeEntities = INSTANCE.decodeEntities(c.getString(c.getColumnIndexOrThrow("entities")));
        String string7 = c.getString(c.getColumnIndexOrThrow("status"));
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        MemoryStatus memoryStatusValueOf = MemoryStatus.valueOf(string7);
        String string8 = c.getString(c.getColumnIndexOrThrow("disposition"));
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        return new MemoryEntry(string, memoryKindValueOf, string3, memoryProvenanceValueOf, f, sensitivityValueOf, j, j2, j3, j4, expires, i, (List) destination$iv$iv2, string6, mapDecodeEntities, memoryStatusValueOf, MemoryDisposition.valueOf(string8));
    }

    /* JADX INFO: compiled from: SqliteMemoryRepository.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/memory/SqliteMemoryRepository$Helper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Helper extends SQLiteOpenHelper {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Helper(Context context) {
            super(context, SqliteMemoryRepository.DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
            Intrinsics.checkNotNullParameter(context, "context");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("CREATE TABLE companion_memory (\n  id TEXT PRIMARY KEY NOT NULL,\n  type TEXT NOT NULL,\n  content TEXT NOT NULL,\n  provenance TEXT NOT NULL,\n  confidence REAL NOT NULL,\n  sensitivity TEXT NOT NULL,\n  created_at INTEGER NOT NULL,\n  updated_at INTEGER NOT NULL,\n  first_observed_at INTEGER NOT NULL,\n  last_observed_at INTEGER NOT NULL,\n  expires_at INTEGER,\n  evidence_count INTEGER NOT NULL,\n  evidence_ids TEXT NOT NULL,\n  package_name TEXT,\n  entities TEXT NOT NULL,\n  status TEXT NOT NULL,\n  disposition TEXT NOT NULL\n)");
            db.execSQL("CREATE INDEX idx_mem_type ON companion_memory(type)");
            db.execSQL("CREATE INDEX idx_mem_status ON companion_memory(status)");
            db.execSQL("CREATE INDEX idx_mem_updated ON companion_memory(updated_at)");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Intrinsics.checkNotNullParameter(db, "db");
        }
    }

    /* JADX INFO: compiled from: SqliteMemoryRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00052\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bJ\u001c\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/memory/SqliteMemoryRepository$Companion;", "", "<init>", "()V", "DB_NAME", "", "DB_VERSION", "", "TABLE", "encodeEntities", "map", "", "decodeEntities", "raw", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String encodeEntities(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            return CollectionsKt.joinToString$default(map.entrySet(), ";", null, null, 0, null, new Function1() { // from class: com.jarvis.core.memory.SqliteMemoryRepository$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SqliteMemoryRepository.Companion.encodeEntities$lambda$0((Map.Entry) obj);
                }
            }, 30, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence encodeEntities$lambda$0(Map.Entry entry) {
            Intrinsics.checkNotNullParameter(entry, "<destruct>");
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            return StringsKt.replace$default(StringsKt.replace$default(k, '=', '_', false, 4, (Object) null), ';', '_', false, 4, (Object) null) + "=" + StringsKt.replace$default(v, ';', '_', false, 4, (Object) null);
        }

        public final Map<String, String> decodeEntities(String raw) {
            Pair pair;
            String str = raw;
            int i = 0;
            if (str == null || StringsKt.isBlank(str)) {
                return MapsKt.emptyMap();
            }
            Iterable $this$mapNotNull$iv = StringsKt.split$default((CharSequence) raw, new char[]{';'}, false, 0, 6, (Object) null);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                String part = (String) element$iv$iv$iv;
                int i2 = StringsKt.indexOf$default((CharSequence) part, '=', 0, false, 6, (Object) null);
                if (i2 <= 0) {
                    pair = null;
                } else {
                    String strSubstring = part.substring(i, i2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    String strSubstring2 = part.substring(i2 + 1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                    pair = TuplesKt.to(strSubstring, strSubstring2);
                }
                if (pair != null) {
                    destination$iv$iv.add(pair);
                }
                $this$mapNotNull$iv = $this$mapNotNull$iv;
                i = 0;
            }
            return MapsKt.toMap((List) destination$iv$iv);
        }
    }
}
