package com.jarvis.core.memory;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Memory2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/memory/Memory2Policy;", "", "<init>", "()V", "allowDurable", "", "kind", "Lcom/jarvis/core/memory/Memory2Kind;", "clampDurability", "Lcom/jarvis/core/memory/Memory2Durability;", "requested", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Memory2Policy {
    public static final Memory2Policy INSTANCE = new Memory2Policy();

    /* JADX INFO: compiled from: Memory2.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Memory2Kind.values().length];
            try {
                iArr[Memory2Kind.PREFERENCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Memory2Kind.ROUTINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Memory2Kind.FEEDBACK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Memory2Kind.BEHAVIOR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Memory2Kind.CONTEXT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Memory2Kind.EXECUTION_HISTORY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[Memory2Kind.TOOL_PERFORMANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private Memory2Policy() {
    }

    public final boolean allowDurable(Memory2Kind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return true;
            case 5:
            case 6:
            case 7:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final Memory2Durability clampDurability(Memory2Kind kind, Memory2Durability requested) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(requested, "requested");
        if (requested == Memory2Durability.DURABLE && !allowDurable(kind)) {
            return Memory2Durability.SESSION;
        }
        return requested;
    }
}
