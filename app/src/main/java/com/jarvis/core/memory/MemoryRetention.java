package com.jarvis.core.memory;

import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.storage.RetentionPolicy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryRetention.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/memory/MemoryRetention;", "", "<init>", "()V", "HOUR", "", "DAY", "expiresAt", "nowMillis", "kind", "Lcom/jarvis/core/memory/MemoryKind;", "provenance", "Lcom/jarvis/core/memory/MemoryProvenance;", "sensitivity", "Lcom/jarvis/core/companion/Sensitivity;", "confidence", "", "disposition", "Lcom/jarvis/core/memory/MemoryDisposition;", "(JLcom/jarvis/core/memory/MemoryKind;Lcom/jarvis/core/memory/MemoryProvenance;Lcom/jarvis/core/companion/Sensitivity;FLcom/jarvis/core/memory/MemoryDisposition;)Ljava/lang/Long;", "staleAfterMillis", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryRetention {
    private static final long DAY = 86400000;
    private static final long HOUR = 3600000;
    public static final MemoryRetention INSTANCE = new MemoryRetention();

    /* JADX INFO: compiled from: MemoryRetention.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MemoryKind.values().length];
            try {
                iArr[MemoryKind.WORKING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MemoryKind.EPISODIC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MemoryKind.ROUTINE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[MemoryKind.PREFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[MemoryKind.SEMANTIC.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[MemoryKind.PROCEDURAL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private MemoryRetention() {
    }

    public final Long expiresAt(long nowMillis, MemoryKind kind, MemoryProvenance provenance, Sensitivity sensitivity, float confidence, MemoryDisposition disposition) {
        long ttl;
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(provenance, "provenance");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        Intrinsics.checkNotNullParameter(disposition, "disposition");
        if (sensitivity != Sensitivity.SECRET && disposition != MemoryDisposition.REJECT) {
            if (disposition == MemoryDisposition.TEMPORARY) {
                switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                    case 1:
                        ttl = 7200000;
                        break;
                    case 2:
                        ttl = RetentionPolicy.RECENT_WINDOW_MS;
                        break;
                    case 3:
                        ttl = 604800000;
                        break;
                    case 4:
                        ttl = 259200000;
                        break;
                    case 5:
                    case 6:
                        ttl = 432000000;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return Long.valueOf(nowMillis + ttl);
            }
            if (provenance == MemoryProvenance.USER_STATED && kind == MemoryKind.PREFERENCE) {
                return null;
            }
            if (provenance == MemoryProvenance.USER_STATED && confidence >= 0.95f) {
                return null;
            }
            if (kind == MemoryKind.PREFERENCE) {
                return Long.valueOf(15552000000L + nowMillis);
            }
            if (kind == MemoryKind.ROUTINE) {
                return Long.valueOf(5184000000L + nowMillis);
            }
            if (kind == MemoryKind.EPISODIC) {
                return Long.valueOf(2592000000L + nowMillis);
            }
            if (kind != MemoryKind.SEMANTIC && kind != MemoryKind.PROCEDURAL) {
                return kind == MemoryKind.WORKING ? Long.valueOf(43200000 + nowMillis) : Long.valueOf(1209600000 + nowMillis);
            }
            return Long.valueOf(RetentionPolicy.CONVERSATION_TTL_MS + nowMillis);
        }
        return Long.valueOf(nowMillis);
    }

    public final long staleAfterMillis(MemoryKind kind, MemoryProvenance provenance) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(provenance, "provenance");
        if (provenance == MemoryProvenance.USER_STATED) {
            return 15552000000L;
        }
        if (kind == MemoryKind.ROUTINE) {
            return 1814400000L;
        }
        if (kind == MemoryKind.EPISODIC) {
            return 1209600000L;
        }
        return kind == MemoryKind.WORKING ? 21600000L : 2592000000L;
    }
}
