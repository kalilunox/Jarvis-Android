package com.jarvis.core.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FactsRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0002\r\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/storage/LegacyFactsMigration;", "", "repo", "Lcom/jarvis/core/storage/FactsRepository;", "<init>", "(Lcom/jarvis/core/storage/FactsRepository;)V", "migrateIfNeeded", "Lcom/jarvis/core/storage/LegacyFactsMigration$Report;", "context", "Landroid/content/Context;", "copy", "legacy", "Landroid/database/sqlite/SQLiteDatabase;", "Report", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LegacyFactsMigration {
    public static final String PREFS = "jarvis_prefs";
    private static final String TAG = "Jarvis.MemoryMigration";
    private final FactsRepository repo;

    /* JADX INFO: compiled from: FactsRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FactAddResult.values().length];
            try {
                iArr[FactAddResult.STORED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FactAddResult.DUPLICATE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FactAddResult.REJECTED_SECRET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FactAddResult.REJECTED_TOO_SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[FactAddResult.REJECTED_TOO_LONG.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LegacyFactsMigration(FactsRepository repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        this.repo = repo;
    }

    /* JADX INFO: compiled from: FactsRepository.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0005HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006#"}, d2 = {"Lcom/jarvis/core/storage/LegacyFactsMigration$Report;", "", "ran", "", "scanned", "", "migrated", "skippedSeed", "skippedSecret", "skippedDuplicate", "skippedInvalid", "<init>", "(ZIIIIII)V", "getRan", "()Z", "getScanned", "()I", "getMigrated", "getSkippedSeed", "getSkippedSecret", "getSkippedDuplicate", "getSkippedInvalid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Report {
        private final int migrated;
        private final boolean ran;
        private final int scanned;
        private final int skippedDuplicate;
        private final int skippedInvalid;
        private final int skippedSecret;
        private final int skippedSeed;

        public static /* synthetic */ Report copy$default(Report report, boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                z = report.ran;
            }
            if ((i7 & 2) != 0) {
                i = report.scanned;
            }
            int i8 = i;
            if ((i7 & 4) != 0) {
                i2 = report.migrated;
            }
            int i9 = i2;
            if ((i7 & 8) != 0) {
                i3 = report.skippedSeed;
            }
            int i10 = i3;
            if ((i7 & 16) != 0) {
                i4 = report.skippedSecret;
            }
            int i11 = i4;
            if ((i7 & 32) != 0) {
                i5 = report.skippedDuplicate;
            }
            int i12 = i5;
            if ((i7 & 64) != 0) {
                i6 = report.skippedInvalid;
            }
            return report.copy(z, i8, i9, i10, i11, i12, i6);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getRan() {
            return this.ran;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getScanned() {
            return this.scanned;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getMigrated() {
            return this.migrated;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getSkippedSeed() {
            return this.skippedSeed;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getSkippedSecret() {
            return this.skippedSecret;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getSkippedDuplicate() {
            return this.skippedDuplicate;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getSkippedInvalid() {
            return this.skippedInvalid;
        }

        public final Report copy(boolean ran, int scanned, int migrated, int skippedSeed, int skippedSecret, int skippedDuplicate, int skippedInvalid) {
            return new Report(ran, scanned, migrated, skippedSeed, skippedSecret, skippedDuplicate, skippedInvalid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Report)) {
                return false;
            }
            Report report = (Report) other;
            return this.ran == report.ran && this.scanned == report.scanned && this.migrated == report.migrated && this.skippedSeed == report.skippedSeed && this.skippedSecret == report.skippedSecret && this.skippedDuplicate == report.skippedDuplicate && this.skippedInvalid == report.skippedInvalid;
        }

        public int hashCode() {
            return (((((((((((Boolean.hashCode(this.ran) * 31) + Integer.hashCode(this.scanned)) * 31) + Integer.hashCode(this.migrated)) * 31) + Integer.hashCode(this.skippedSeed)) * 31) + Integer.hashCode(this.skippedSecret)) * 31) + Integer.hashCode(this.skippedDuplicate)) * 31) + Integer.hashCode(this.skippedInvalid);
        }

        public String toString() {
            return "Report(ran=" + this.ran + ", scanned=" + this.scanned + ", migrated=" + this.migrated + ", skippedSeed=" + this.skippedSeed + ", skippedSecret=" + this.skippedSecret + ", skippedDuplicate=" + this.skippedDuplicate + ", skippedInvalid=" + this.skippedInvalid + ")";
        }

        public Report(boolean ran, int scanned, int migrated, int skippedSeed, int skippedSecret, int skippedDuplicate, int skippedInvalid) {
            this.ran = ran;
            this.scanned = scanned;
            this.migrated = migrated;
            this.skippedSeed = skippedSeed;
            this.skippedSecret = skippedSecret;
            this.skippedDuplicate = skippedDuplicate;
            this.skippedInvalid = skippedInvalid;
        }

        public /* synthetic */ Report(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i7 & 2) != 0 ? 0 : i, (i7 & 4) != 0 ? 0 : i2, (i7 & 8) != 0 ? 0 : i3, (i7 & 16) != 0 ? 0 : i4, (i7 & 32) != 0 ? 0 : i5, (i7 & 64) == 0 ? i6 : 0);
        }

        public final boolean getRan() {
            return this.ran;
        }

        public final int getScanned() {
            return this.scanned;
        }

        public final int getMigrated() {
            return this.migrated;
        }

        public final int getSkippedSeed() {
            return this.skippedSeed;
        }

        public final int getSkippedSecret() {
            return this.skippedSecret;
        }

        public final int getSkippedDuplicate() {
            return this.skippedDuplicate;
        }

        public final int getSkippedInvalid() {
            return this.skippedInvalid;
        }
    }

    public final Report migrateIfNeeded(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences("jarvis_prefs", 0);
        if (prefs.getBoolean(FactPolicies.MIGRATED_FLAG, false)) {
            return new Report(false, 0, 0, 0, 0, 0, 0, 126, null);
        }
        File legacyFile = context.getDatabasePath(FactPolicies.LEGACY_DB_NAME);
        if (!legacyFile.exists()) {
            prefs.edit().putBoolean(FactPolicies.MIGRATED_FLAG, true).apply();
            Log.i(TAG, "legacy db absent; nothing to migrate");
            return new Report(false, 0, 0, 0, 0, 0, 0, 126, null);
        }
        SQLiteDatabase element = null;
        Unit unit = null;
        try {
            element = SQLiteDatabase.openDatabase(legacyFile.getPath(), null, 1);
            Intrinsics.checkNotNullExpressionValue(element, "element");
            Report report = copy(element);
            prefs.edit().putBoolean(FactPolicies.MIGRATED_FLAG, true).apply();
            Log.i(TAG, "legacy facts migrated scanned=" + report.getScanned() + " ok=" + report.getMigrated() + " seed=" + report.getSkippedSeed() + " secret=" + report.getSkippedSecret() + " dup=" + report.getSkippedDuplicate() + " invalid=" + report.getSkippedInvalid());
            try {
                Result.Companion companion = Result.INSTANCE;
                LegacyFactsMigration legacyFactsMigration = this;
                if (element != null) {
                    element.close();
                    unit = Unit.INSTANCE;
                }
                Result.m392constructorimpl(unit);
                return report;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
                return report;
            }
        } catch (Throwable t) {
            try {
                Log.w(TAG, "legacy facts migration failed: " + t.getMessage());
                return new Report(true, 0, 0, 0, 0, 0, 0, 126, null);
            } finally {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    SQLiteDatabase sQLiteDatabase = element;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                        unit = Unit.INSTANCE;
                    }
                    Result.m392constructorimpl(unit);
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th2));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0040  */
    /* JADX WARN: Code duplicated, block: B:24:0x005a  */
    private final Report copy(SQLiteDatabase legacy) throws IOException {
        int scanned = 0;
        int migrated = 0;
        int seed = 0;
        int secret = 0;
        int dup = 0;
        int invalid = 0;
        Cursor cursorRawQuery = legacy.rawQuery("SELECT text, category, source, created_at FROM facts ORDER BY created_at ASC", null);
        try {
            Cursor c = cursorRawQuery;
            while (c.moveToNext()) {
                scanned++;
                String text = c.getString(0);
                if (text == null) {
                    text = "";
                }
                String it = c.getString(1);
                if (it == null) {
                    it = "general";
                } else {
                    if (StringsKt.isBlank(it)) {
                        it = null;
                    }
                    if (it == null) {
                        it = "general";
                    }
                }
                String category = it;
                String it2 = c.getString(2);
                if (it2 == null) {
                    it2 = JarvisSchema.ROLE_USER;
                } else {
                    if (StringsKt.isBlank(it2)) {
                        it2 = null;
                    }
                    if (it2 == null) {
                        it2 = JarvisSchema.ROLE_USER;
                    }
                }
                String source = it2;
                long createdAt = c.isNull(3) ? System.currentTimeMillis() : c.getLong(3);
                if (!FactPolicies.INSTANCE.shouldMigrateSource(source)) {
                    seed++;
                } else if (!FactPolicies.INSTANCE.isStorable(text)) {
                    invalid++;
                } else if (FactPolicies.INSTANCE.isSecret(text)) {
                    secret++;
                } else {
                    switch (WhenMappings.$EnumSwitchMapping$0[this.repo.add(text, category, source, createdAt).ordinal()]) {
                        case 1:
                            migrated++;
                            break;
                        case 2:
                            dup++;
                            break;
                        case 3:
                            secret++;
                            break;
                        case 4:
                        case 5:
                            invalid++;
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return new Report(true, scanned, migrated, seed, secret, dup, invalid);
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
