package com.jarvis.core.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.privacy.SensitivityClassifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FactsRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001'B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u000eJ\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0018\u001a\u00020\nJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000eJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u0016J\u000e\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"J\u0018\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/jarvis/core/storage/FactsRepository;", "", "helper", "Lcom/jarvis/core/storage/JarvisDb;", "<init>", "(Lcom/jarvis/core/storage/JarvisDb;)V", "lock", "add", "Lcom/jarvis/core/storage/FactAddResult;", VoiceService.EXTRA_TEXT, "", "category", "source", "createdAt", "", "list", "", "Lcom/jarvis/core/storage/FactsRepository$StoredFact;", "now", "retrieve", SearchIntents.EXTRA_QUERY, "limit", "", "findById", "id", "delete", "", "deleteRow", "rowId", "clearAll", "", "count", "countBySensitivity", "sensitivity", "Lcom/jarvis/core/companion/Sensitivity;", "existsNormalized", "db", "Landroid/database/sqlite/SQLiteDatabase;", "normalized", "StoredFact", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FactsRepository {
    private final JarvisDb helper;
    private final Object lock;

    public FactsRepository(JarvisDb helper) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        this.helper = helper;
        this.lock = new Object();
    }

    /* JADX INFO: compiled from: FactsRepository.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001eJt\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001e¨\u00062"}, d2 = {"Lcom/jarvis/core/storage/FactsRepository$StoredFact;", "", "id", "", "rowId", "", VoiceService.EXTRA_TEXT, "category", "source", "weight", "", "sensitivity", "createdAt", "updatedAt", "expiresAt", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/String;JJLjava/lang/Long;)V", "getId", "()Ljava/lang/String;", "getRowId", "()J", "getText", "getCategory", "getSource", "getWeight", "()F", "getSensitivity", "getCreatedAt", "getUpdatedAt", "getExpiresAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/String;JJLjava/lang/Long;)Lcom/jarvis/core/storage/FactsRepository$StoredFact;", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StoredFact {
        private final String category;
        private final long createdAt;
        private final Long expiresAt;
        private final String id;
        private final long rowId;
        private final String sensitivity;
        private final String source;
        private final String text;
        private final long updatedAt;
        private final float weight;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Long getExpiresAt() {
            return this.expiresAt;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getRowId() {
            return this.rowId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCategory() {
            return this.category;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final float getWeight() {
            return this.weight;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSensitivity() {
            return this.sensitivity;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final long getUpdatedAt() {
            return this.updatedAt;
        }

        public final StoredFact copy(String id, long rowId, String text, String category, String source, float weight, String sensitivity, long createdAt, long updatedAt, Long expiresAt) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
            return new StoredFact(id, rowId, text, category, source, weight, sensitivity, createdAt, updatedAt, expiresAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StoredFact)) {
                return false;
            }
            StoredFact storedFact = (StoredFact) other;
            return Intrinsics.areEqual(this.id, storedFact.id) && this.rowId == storedFact.rowId && Intrinsics.areEqual(this.text, storedFact.text) && Intrinsics.areEqual(this.category, storedFact.category) && Intrinsics.areEqual(this.source, storedFact.source) && Float.compare(this.weight, storedFact.weight) == 0 && Intrinsics.areEqual(this.sensitivity, storedFact.sensitivity) && this.createdAt == storedFact.createdAt && this.updatedAt == storedFact.updatedAt && Intrinsics.areEqual(this.expiresAt, storedFact.expiresAt);
        }

        public int hashCode() {
            return (((((((((((((((((this.id.hashCode() * 31) + Long.hashCode(this.rowId)) * 31) + this.text.hashCode()) * 31) + this.category.hashCode()) * 31) + this.source.hashCode()) * 31) + Float.hashCode(this.weight)) * 31) + this.sensitivity.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt)) * 31) + (this.expiresAt == null ? 0 : this.expiresAt.hashCode());
        }

        public String toString() {
            return "StoredFact(id=" + this.id + ", rowId=" + this.rowId + ", text=" + this.text + ", category=" + this.category + ", source=" + this.source + ", weight=" + this.weight + ", sensitivity=" + this.sensitivity + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", expiresAt=" + this.expiresAt + ")";
        }

        public StoredFact(String id, long rowId, String text, String category, String source, float weight, String sensitivity, long createdAt, long updatedAt, Long expiresAt) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
            this.id = id;
            this.rowId = rowId;
            this.text = text;
            this.category = category;
            this.source = source;
            this.weight = weight;
            this.sensitivity = sensitivity;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.expiresAt = expiresAt;
        }

        public final String getId() {
            return this.id;
        }

        public final long getRowId() {
            return this.rowId;
        }

        public final String getText() {
            return this.text;
        }

        public final String getCategory() {
            return this.category;
        }

        public final String getSource() {
            return this.source;
        }

        public final float getWeight() {
            return this.weight;
        }

        public final String getSensitivity() {
            return this.sensitivity;
        }

        public final long getCreatedAt() {
            return this.createdAt;
        }

        public final long getUpdatedAt() {
            return this.updatedAt;
        }

        public final Long getExpiresAt() {
            return this.expiresAt;
        }
    }

    public final FactAddResult add(String text, String category, String source, long createdAt) {
        FactAddResult factAddResult;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(source, "source");
        synchronized (this.lock) {
            String trimmed = StringsKt.trim((CharSequence) text).toString();
            if (trimmed.length() < 3) {
                factAddResult = FactAddResult.REJECTED_TOO_SHORT;
            } else if (trimmed.length() > 240) {
                factAddResult = FactAddResult.REJECTED_TOO_LONG;
            } else {
                if (!FactPolicies.INSTANCE.isSecret(trimmed)) {
                    String key = FactPolicies.INSTANCE.normalizeForDedup(trimmed);
                    SQLiteDatabase db = this.helper.getWritableDatabase();
                    Intrinsics.checkNotNull(db);
                    if (existsNormalized(db, key)) {
                        return FactAddResult.DUPLICATE;
                    }
                    String stored = FactPolicies.INSTANCE.diskText(trimmed);
                    long now = System.currentTimeMillis();
                    ContentValues $this$add_u24lambda_u240_u240 = new ContentValues();
                    $this$add_u24lambda_u240_u240.put("id", UUID.randomUUID().toString());
                    $this$add_u24lambda_u240_u240.put(VoiceService.EXTRA_TEXT, stored);
                    $this$add_u24lambda_u240_u240.put("category", category);
                    $this$add_u24lambda_u240_u240.put("source", source);
                    $this$add_u24lambda_u240_u240.put("weight", Double.valueOf(1.0d));
                    $this$add_u24lambda_u240_u240.put("sensitivity", SensitivityClassifier.INSTANCE.classify(stored, null).name());
                    $this$add_u24lambda_u240_u240.put("created_at", Long.valueOf(createdAt));
                    $this$add_u24lambda_u240_u240.put("updated_at", Long.valueOf(now));
                    $this$add_u24lambda_u240_u240.putNull("expires_at");
                    db.insertOrThrow(JarvisSchema.TABLE_FACTS, null, $this$add_u24lambda_u240_u240);
                    return FactAddResult.STORED;
                }
                factAddResult = FactAddResult.REJECTED_SECRET;
            }
            return factAddResult;
        }
    }

    public static /* synthetic */ List list$default(FactsRepository factsRepository, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return factsRepository.list(j);
    }

    public final List<StoredFact> list(long now) {
        ArrayList out;
        synchronized (this.lock) {
            out = new ArrayList();
            int i = 1;
            int i2 = 0;
            Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT rowid, id, text, category, source, weight, sensitivity, created_at, updated_at, expires_at FROM facts WHERE expires_at IS NULL OR expires_at > ? ORDER BY created_at DESC", new String[]{String.valueOf(now)});
            try {
                for (Cursor c = cursorRawQuery; c.moveToNext(); c = c) {
                    ArrayList arrayList = out;
                    String string = c.getString(i);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    long j = c.getLong(i2);
                    String string2 = c.getString(2);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String string3 = c.getString(3);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = c.getString(4);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    float f = c.getFloat(5);
                    String string5 = c.getString(6);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    arrayList.add(new StoredFact(string, j, string2, string3, string4, f, string5, c.getLong(7), c.getLong(8), c.isNull(9) ? null : Long.valueOf(c.getLong(9))));
                    i = 1;
                    i2 = 0;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        }
        return out;
    }

    public static /* synthetic */ List retrieve$default(FactsRepository factsRepository, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 3;
        }
        return factsRepository.retrieve(str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String retrieve$lambda$0(StoredFact it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getText();
    }

    public final List<StoredFact> retrieve(String query, int limit) {
        Intrinsics.checkNotNullParameter(query, "query");
        return FactScoring.INSTANCE.topN(query, list$default(this, 0L, 1, null), limit, new Function1() { // from class: com.jarvis.core.storage.FactsRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FactsRepository.retrieve$lambda$0((FactsRepository.StoredFact) obj);
            }
        });
    }

    public final StoredFact findById(String id) {
        StoredFact storedFact;
        Intrinsics.checkNotNullParameter(id, "id");
        synchronized (this.lock) {
            Object obj = null;
            Iterable $this$firstOrNull$iv = list$default(this, 0L, 1, null);
            for (Object element$iv : $this$firstOrNull$iv) {
                StoredFact it = (StoredFact) element$iv;
                if (Intrinsics.areEqual(it.getId(), id)) {
                    obj = element$iv;
                    break;
                }
            }
            storedFact = (StoredFact) obj;
        }
        return storedFact;
    }

    public final boolean delete(String id) {
        boolean z;
        Intrinsics.checkNotNullParameter(id, "id");
        synchronized (this.lock) {
            z = this.helper.getWritableDatabase().delete(JarvisSchema.TABLE_FACTS, "id = ?", new String[]{id}) > 0;
        }
        return z;
    }

    public final boolean deleteRow(long rowId) {
        boolean z;
        synchronized (this.lock) {
            z = this.helper.getWritableDatabase().delete(JarvisSchema.TABLE_FACTS, "rowid = ?", new String[]{String.valueOf(rowId)}) > 0;
        }
        return z;
    }

    public final void clearAll() {
        synchronized (this.lock) {
            this.helper.getWritableDatabase().delete(JarvisSchema.TABLE_FACTS, null, null);
        }
    }

    public final int count() {
        int i;
        synchronized (this.lock) {
            Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM facts", null);
            try {
                Cursor c = cursorRawQuery;
                i = c.moveToFirst() ? c.getInt(0) : 0;
                CloseableKt.closeFinally(cursorRawQuery, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        }
        return i;
    }

    public final int countBySensitivity(Sensitivity sensitivity) {
        int i;
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        synchronized (this.lock) {
            Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM facts WHERE sensitivity = ?", new String[]{sensitivity.name()});
            try {
                Cursor c = cursorRawQuery;
                i = c.moveToFirst() ? c.getInt(0) : 0;
                CloseableKt.closeFinally(cursorRawQuery, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery, th);
                    throw th2;
                }
            }
        }
        return i;
    }

    private final boolean existsNormalized(SQLiteDatabase db, String normalized) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT text FROM facts", null);
        try {
            Cursor c = cursorRawQuery;
            while (c.moveToNext()) {
                FactPolicies factPolicies = FactPolicies.INSTANCE;
                String string = c.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (Intrinsics.areEqual(factPolicies.normalizeForDedup(string), normalized)) {
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    return true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return false;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }
}
