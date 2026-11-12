package com.jarvis.core.filesagent;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: FilesAgent.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/jarvis/core/filesagent/FileOperation;", "", "<init>", "(Ljava/lang/String;I)V", "SEARCH", "INSPECT", "READ", "CREATE", "RENAME", "MOVE", "ORGANIZE", "DELETE", "METADATA", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum FileOperation {
    SEARCH,
    INSPECT,
    READ,
    CREATE,
    RENAME,
    MOVE,
    ORGANIZE,
    DELETE,
    METADATA;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<FileOperation> getEntries() {
        return $ENTRIES;
    }
}
