package com.jarvis.core.startup;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartupRoute.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/startup/UiStyle;", "", "id", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "MODERN", "CLASSIC", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum UiStyle {
    MODERN("modern"),
    CLASSIC("classic");

    private final String id;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<UiStyle> getEntries() {
        return $ENTRIES;
    }

    UiStyle(String id) {
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }

    /* JADX INFO: compiled from: StartupRoute.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/startup/UiStyle$Companion;", "", "<init>", "()V", "of", "Lcom/jarvis/core/startup/UiStyle;", "id", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UiStyle of(String id) {
            Object element$iv;
            Iterable $this$firstOrNull$iv = UiStyle.getEntries();
            Iterator<UiStyle> it = $this$firstOrNull$iv.iterator();
            while (it.hasNext()) {
                element$iv = it.next();
                UiStyle it2 = (UiStyle) element$iv;
                if (Intrinsics.areEqual(it2.getId(), id)) {
                    return (UiStyle) element$iv;
                }
            }
            element$iv = null;
            return (UiStyle) element$iv;
        }
    }
}
