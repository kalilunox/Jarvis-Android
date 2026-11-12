package com.jarvis.core.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisDb.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/storage/JarvisDb;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "createdAtElapsed", "", "onOpen", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onConfigure", "onCreate", "onUpgrade", "oldVersion", "", "newVersion", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisDb extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "Jarvis.Db";
    private static volatile JarvisDb instance;
    private final long createdAtElapsed;

    public /* synthetic */ JarvisDb(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private JarvisDb(Context context) {
        super(context.getApplicationContext(), JarvisSchema.DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.createdAtElapsed = SystemClock.elapsedRealtime();
        setWriteAheadLoggingEnabled(true);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        super.onOpen(db);
        Log.i(TAG, "opened in " + (SystemClock.elapsedRealtime() - this.createdAtElapsed) + "ms wal=" + db.isWriteAheadLoggingEnabled() + " version=" + db.getVersion());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        long startedAt = System.currentTimeMillis();
        Iterable $this$forEach$iv = JarvisSchema.INSTANCE.getCREATE_STATEMENTS();
        for (Object element$iv : $this$forEach$iv) {
            String it = (String) element$iv;
            db.execSQL(it);
        }
        Log.i(TAG, "schema created v1 in " + (System.currentTimeMillis() - startedAt) + "ms");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
        if (newVersion > 1) {
            throw new IllegalStateException(("db schema v" + newVersion + " is newer than app schema v1").toString());
        }
        List plan = JarvisSchema.planUpgrade$default(JarvisSchema.INSTANCE, oldVersion, newVersion, null, 4, null);
        Log.i(TAG, "upgrade v" + oldVersion + " -> v" + newVersion + " plan=" + plan);
        Iterator it = plan.iterator();
        while (it.hasNext()) {
            int target = ((Number) it.next()).intValue();
            Iterable $this$forEach$iv = JarvisSchema.statementsFor$default(JarvisSchema.INSTANCE, target, null, 2, null);
            for (Object element$iv : $this$forEach$iv) {
                String it2 = (String) element$iv;
                db.execSQL(it2);
            }
        }
    }

    /* JADX INFO: compiled from: JarvisDb.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/jarvis/core/storage/JarvisDb$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lcom/jarvis/core/storage/JarvisDb;", "get", "context", "Landroid/content/Context;", "resetForTests", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JarvisDb get(Context context) {
            JarvisDb it;
            Intrinsics.checkNotNullParameter(context, "context");
            JarvisDb jarvisDb = JarvisDb.instance;
            if (jarvisDb == null) {
                synchronized (this) {
                    it = JarvisDb.instance;
                    if (it == null) {
                        it = new JarvisDb(context, null);
                        Companion companion = JarvisDb.INSTANCE;
                        JarvisDb.instance = it;
                    }
                }
                return it;
            }
            return jarvisDb;
        }

        public final void resetForTests() {
            Unit unit;
            synchronized (this) {
                Companion companion = JarvisDb.INSTANCE;
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    JarvisDb jarvisDb = JarvisDb.instance;
                    if (jarvisDb != null) {
                        jarvisDb.close();
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    Result.m392constructorimpl(unit);
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                Companion companion4 = JarvisDb.INSTANCE;
                JarvisDb.instance = null;
                Unit unit2 = Unit.INSTANCE;
            }
        }
    }
}
