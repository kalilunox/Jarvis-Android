package com.jarvis.core.companion;

import java.util.ArrayDeque;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ObservationDiagnosticsBuffer.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0005J\u0006\u0010\u0011\u001a\u00020\fJ\u0010\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/companion/ObservationDiagnosticsBuffer;", "", "<init>", "()V", "DEFAULT_LIMIT", "", "lock", "deque", "Ljava/util/ArrayDeque;", "Lcom/jarvis/core/companion/PhoneObservation;", "capacity", "record", "", "observation", "recent", "", "limit", "clear", "resetForTests", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ObservationDiagnosticsBuffer {
    private static final int DEFAULT_LIMIT = 40;
    public static final ObservationDiagnosticsBuffer INSTANCE = new ObservationDiagnosticsBuffer();
    private static final Object lock = new Object();
    private static final ArrayDeque<PhoneObservation> deque = new ArrayDeque<>(40);
    private static int capacity = 40;

    private ObservationDiagnosticsBuffer() {
    }

    public final void record(PhoneObservation observation) {
        Intrinsics.checkNotNullParameter(observation, "observation");
        synchronized (lock) {
            if (deque.size() >= capacity) {
                deque.removeFirst();
            }
            deque.addLast(observation);
            Unit unit = Unit.INSTANCE;
        }
    }

    public static /* synthetic */ List recent$default(ObservationDiagnosticsBuffer observationDiagnosticsBuffer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 20;
        }
        return observationDiagnosticsBuffer.recent(i);
    }

    public final List<PhoneObservation> recent(int limit) {
        List<PhoneObservation> listTakeLast;
        synchronized (lock) {
            listTakeLast = CollectionsKt.takeLast(CollectionsKt.toList(deque), RangesKt.coerceAtLeast(limit, 0));
        }
        return listTakeLast;
    }

    public final void clear() {
        synchronized (lock) {
            deque.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void resetForTests$default(ObservationDiagnosticsBuffer observationDiagnosticsBuffer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 40;
        }
        observationDiagnosticsBuffer.resetForTests(i);
    }

    public final void resetForTests(int limit) {
        synchronized (lock) {
            capacity = RangesKt.coerceAtLeast(limit, 1);
            deque.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
