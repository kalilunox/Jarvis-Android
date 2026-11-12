package com.jarvis.core.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisSchema.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\u0006\u0010\u0018\u001a\u00020\u00072\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0014\u0010\u0019\u001a\u00020\u001a2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/jarvis/core/storage/JarvisSchema;", "", "<init>", "()V", "DB_NAME", "", "VERSION", "", "TABLE_FACTS", "TABLE_MESSAGES", "ROLE_USER", "ROLE_ASSISTANT", "MIGRATIONS", "", "Lcom/jarvis/core/storage/JarvisSchema$Migration;", "getMIGRATIONS", "()Ljava/util/List;", "CREATE_STATEMENTS", "getCREATE_STATEMENTS", "planUpgrade", "oldVersion", "newVersion", "migrations", "statementsFor", "targetVersion", "isContiguous", "", "Migration", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisSchema {
    public static final String DB_NAME = "jarvis_core.db";
    public static final String ROLE_ASSISTANT = "assistant";
    public static final String ROLE_USER = "user";
    public static final String TABLE_FACTS = "facts";
    public static final String TABLE_MESSAGES = "conversation_messages";
    public static final int VERSION = 1;
    public static final JarvisSchema INSTANCE = new JarvisSchema();
    private static final List<Migration> MIGRATIONS = CollectionsKt.emptyList();
    private static final List<String> CREATE_STATEMENTS = CollectionsKt.listOf((Object[]) new String[]{"CREATE TABLE facts (\n    id TEXT PRIMARY KEY NOT NULL,\n    text TEXT NOT NULL,\n    category TEXT NOT NULL,\n    source TEXT NOT NULL,\n    weight REAL NOT NULL DEFAULT 1,\n    sensitivity TEXT NOT NULL DEFAULT 'NORMAL',\n    created_at INTEGER NOT NULL,\n    updated_at INTEGER NOT NULL,\n    expires_at INTEGER\n)", "CREATE INDEX idx_facts_category ON facts(category)", "CREATE INDEX idx_facts_updated_at ON facts(updated_at)", "CREATE TABLE conversation_messages (\n    id TEXT PRIMARY KEY NOT NULL,\n    session_id TEXT NOT NULL,\n    role TEXT NOT NULL,\n    text TEXT NOT NULL,\n    created_at INTEGER NOT NULL,\n    keep INTEGER NOT NULL DEFAULT 0\n)", "CREATE INDEX idx_messages_created_at ON conversation_messages(created_at)"});

    private JarvisSchema() {
    }

    /* JADX INFO: compiled from: JarvisSchema.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/storage/JarvisSchema$Migration;", "", "targetVersion", "", "statements", "", "", "<init>", "(ILjava/util/List;)V", "getTargetVersion", "()I", "getStatements", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Migration {
        private final List<String> statements;
        private final int targetVersion;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Migration copy$default(Migration migration, int i, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = migration.targetVersion;
            }
            if ((i2 & 2) != 0) {
                list = migration.statements;
            }
            return migration.copy(i, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getTargetVersion() {
            return this.targetVersion;
        }

        public final List<String> component2() {
            return this.statements;
        }

        public final Migration copy(int targetVersion, List<String> statements) {
            Intrinsics.checkNotNullParameter(statements, "statements");
            return new Migration(targetVersion, statements);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Migration)) {
                return false;
            }
            Migration migration = (Migration) other;
            return this.targetVersion == migration.targetVersion && Intrinsics.areEqual(this.statements, migration.statements);
        }

        public int hashCode() {
            return (Integer.hashCode(this.targetVersion) * 31) + this.statements.hashCode();
        }

        public String toString() {
            return "Migration(targetVersion=" + this.targetVersion + ", statements=" + this.statements + ")";
        }

        public Migration(int targetVersion, List<String> statements) {
            Intrinsics.checkNotNullParameter(statements, "statements");
            this.targetVersion = targetVersion;
            this.statements = statements;
        }

        public final int getTargetVersion() {
            return this.targetVersion;
        }

        public final List<String> getStatements() {
            return this.statements;
        }
    }

    public final List<Migration> getMIGRATIONS() {
        return MIGRATIONS;
    }

    public final List<String> getCREATE_STATEMENTS() {
        return CREATE_STATEMENTS;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List planUpgrade$default(JarvisSchema jarvisSchema, int i, int i2, List list, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            list = MIGRATIONS;
        }
        return jarvisSchema.planUpgrade(i, i2, list);
    }

    public final List<Integer> planUpgrade(int oldVersion, int newVersion, List<Migration> migrations) {
        Intrinsics.checkNotNullParameter(migrations, "migrations");
        List<Migration> $this$map$iv = migrations;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(Integer.valueOf(((Migration) item$iv$iv).getTargetVersion()));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            int it = ((Number) element$iv$iv).intValue();
            if (it > oldVersion && it <= newVersion) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return CollectionsKt.sorted(CollectionsKt.distinct((List) destination$iv$iv2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List statementsFor$default(JarvisSchema jarvisSchema, int i, List list, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            list = MIGRATIONS;
        }
        return jarvisSchema.statementsFor(i, list);
    }

    public final List<String> statementsFor(int targetVersion, List<Migration> migrations) {
        Object element$iv;
        Migration it;
        Intrinsics.checkNotNullParameter(migrations, "migrations");
        List<Migration> $this$firstOrNull$iv = migrations;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (Migration) element$iv;
        } while (!(it.getTargetVersion() == targetVersion));
        Migration step = (Migration) element$iv;
        if (step == null) {
            throw new IllegalArgumentException("no migration to schema v" + targetVersion);
        }
        return step.getStatements();
    }

    public final boolean isContiguous(List<Migration> migrations) {
        Intrinsics.checkNotNullParameter(migrations, "migrations");
        if (migrations.isEmpty()) {
            return true;
        }
        int expected = 2;
        for (Migration m : migrations) {
            if (m.getTargetVersion() != expected || m.getStatements().isEmpty()) {
                return false;
            }
            expected++;
        }
        return 1 == ((Migration) CollectionsKt.last((List) migrations)).getTargetVersion();
    }
}
