package com.jarvis.core.browseragent;

import com.jarvis.core.aioutput.CloudToolAction;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: BrowserAgent.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/jarvis/core/browseragent/BrowserStepKind;", "", "<init>", "(Ljava/lang/String;I)V", "SEARCH", "OPEN", "INSPECT", "EXTRACT", CloudToolAction.MARKER, "VERIFY", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum BrowserStepKind {
    SEARCH,
    OPEN,
    INSPECT,
    EXTRACT,
    ACT,
    VERIFY;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<BrowserStepKind> getEntries() {
        return $ENTRIES;
    }
}
