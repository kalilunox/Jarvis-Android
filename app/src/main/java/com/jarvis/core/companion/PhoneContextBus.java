package com.jarvis.core.companion;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: PhoneContextBus.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\tJ\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u0010J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0$2\b\b\u0002\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020(R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/jarvis/core/companion/PhoneContextBus;", "", "<init>", "()V", "lock", "aggregator", "Lcom/jarvis/core/companion/ContextAggregator;", "_observations", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/jarvis/core/companion/PhoneObservation;", "observations", "Lkotlinx/coroutines/flow/SharedFlow;", "getObservations", "()Lkotlinx/coroutines/flow/SharedFlow;", "_context", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/jarvis/core/companion/PhoneContext;", "context", "Lkotlinx/coroutines/flow/StateFlow;", "getContext", "()Lkotlinx/coroutines/flow/StateFlow;", "lastAcceptedId", "", "lastAcceptedAtMillis", "", "lastAcceptedSource", "Lcom/jarvis/core/companion/ObservationSource;", "lastAcceptedType", "Lcom/jarvis/core/companion/ObservationType;", "publish", "", "observation", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "current", "recent", "", "limit", "", "resetForTests", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PhoneContextBus {
    private static volatile long lastAcceptedAtMillis;
    private static volatile String lastAcceptedId;
    private static volatile ObservationSource lastAcceptedSource;
    private static volatile ObservationType lastAcceptedType;
    public static final PhoneContextBus INSTANCE = new PhoneContextBus();
    private static final Object lock = new Object();
    private static final ContextAggregator aggregator = new ContextAggregator(null, 1, null);
    private static final MutableSharedFlow<PhoneObservation> _observations = SharedFlowKt.MutableSharedFlow$default(0, 128, null, 5, null);
    private static final SharedFlow<PhoneObservation> observations = FlowKt.asSharedFlow(_observations);
    private static final MutableStateFlow<PhoneContext> _context = StateFlowKt.MutableStateFlow(new PhoneContext(null, null, null, null, 0, false, null, false, false, null, null, null, null, 0, 0, 32767, null));
    private static final StateFlow<PhoneContext> context = FlowKt.asStateFlow(_context);

    private PhoneContextBus() {
    }

    public final SharedFlow<PhoneObservation> getObservations() {
        return observations;
    }

    public final StateFlow<PhoneContext> getContext() {
        return context;
    }

    public final boolean publish(PhoneObservation observation) {
        Intrinsics.checkNotNullParameter(observation, "observation");
        synchronized (lock) {
            AggregateResult result = aggregator.aggregate(_context.getValue(), observation);
            if (!result.getAccepted()) {
                return false;
            }
            _context.setValue(result.getContext());
            lastAcceptedId = observation.getId();
            lastAcceptedAtMillis = observation.getTimestampMillis();
            lastAcceptedSource = observation.getSource();
            lastAcceptedType = observation.getType();
            ObservationDiagnosticsBuffer.INSTANCE.record(observation);
            _observations.tryEmit(observation);
            return true;
        }
    }

    public final ContextSnapshot snapshot() {
        ContextSnapshot contextSnapshot;
        synchronized (lock) {
            contextSnapshot = new ContextSnapshot(_context.getValue(), System.currentTimeMillis(), lastAcceptedId, lastAcceptedAtMillis, lastAcceptedSource, lastAcceptedType);
        }
        return contextSnapshot;
    }

    public final PhoneContext current() {
        return _context.getValue();
    }

    public static /* synthetic */ List recent$default(PhoneContextBus phoneContextBus, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 20;
        }
        return phoneContextBus.recent(i);
    }

    public final List<PhoneObservation> recent(int limit) {
        return ObservationDiagnosticsBuffer.INSTANCE.recent(limit);
    }

    public final void resetForTests() {
        synchronized (lock) {
            aggregator.reset();
            _context.setValue(new PhoneContext(null, null, null, null, 0L, false, null, false, false, null, null, null, null, 0L, 0L, 32767, null));
            lastAcceptedId = null;
            lastAcceptedAtMillis = 0L;
            lastAcceptedSource = null;
            lastAcceptedType = null;
            ObservationDiagnosticsBuffer.resetForTests$default(ObservationDiagnosticsBuffer.INSTANCE, 0, 1, null);
            Unit unit = Unit.INSTANCE;
        }
    }
}
