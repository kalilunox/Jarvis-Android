package com.jarvis.core.streaming;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StreamSession.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0012J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u001fJ\u000e\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u0007J\u0016\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lcom/jarvis/core/streaming/StreamSession;", "", "sessionId", "", "config", "Lcom/jarvis/core/streaming/StreamSessionConfig;", "startedAtMillis", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/streaming/StreamSessionConfig;J)V", "getSessionId", "()Ljava/lang/String;", "cancellation", "Lcom/jarvis/core/streaming/CancellationToken;", "getCancellation", "()Lcom/jarvis/core/streaming/CancellationToken;", "events", "", "Lcom/jarvis/core/streaming/StreamEvent;", "value", "Lcom/jarvis/core/streaming/StreamPhase;", TypedValues.CycleType.S_WAVE_PHASE, "getPhase", "()Lcom/jarvis/core/streaming/StreamPhase;", "", "bargedIn", "getBargedIn", "()Z", "emit", "", NotificationCompat.CATEGORY_EVENT, "", "timedOut", "nowMillis", "markTimeout", "markFallback", "reason", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StreamSession {
    private volatile boolean bargedIn;
    private final CancellationToken cancellation;
    private final StreamSessionConfig config;
    private final List<StreamEvent> events;
    private volatile StreamPhase phase;
    private final String sessionId;
    private final long startedAtMillis;

    public StreamSession(String sessionId, StreamSessionConfig config, long startedAtMillis) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(config, "config");
        this.sessionId = sessionId;
        this.config = config;
        this.startedAtMillis = startedAtMillis;
        this.cancellation = new CancellationToken();
        this.events = new ArrayList();
        this.phase = StreamPhase.IDLE;
    }

    public /* synthetic */ StreamSession(String str, StreamSessionConfig streamSessionConfig, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new StreamSessionConfig(0L, false, false, 7, null) : streamSessionConfig, (i & 4) != 0 ? 0L : j);
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final CancellationToken getCancellation() {
        return this.cancellation;
    }

    public final StreamPhase getPhase() {
        return this.phase;
    }

    public final boolean getBargedIn() {
        return this.bargedIn;
    }

    public final void emit(StreamEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.cancellation.throwIfCancelled();
        if (event.getType() == StreamEventType.BARGE_IN) {
            if (!this.config.getAllowBargeIn()) {
                return;
            }
            this.bargedIn = true;
            this.cancellation.cancel();
        }
        if (!this.config.getAllowPartialResults() && SetsKt.setOf((Object[]) new StreamEventType[]{StreamEventType.PARTIAL_ASR, StreamEventType.PARTIAL_LLM}).contains(event.getType())) {
            return;
        }
        this.phase = event.getPhase();
        this.events.add(event);
    }

    public final List<StreamEvent> events() {
        return CollectionsKt.toList(this.events);
    }

    public final boolean timedOut(long nowMillis) {
        return nowMillis - this.startedAtMillis > this.config.getTimeoutMs();
    }

    public final void markTimeout(long nowMillis) {
        this.events.add(new StreamEvent(StreamEventType.TIMEOUT, this.phase, null, nowMillis, 4, null));
        this.cancellation.cancel();
    }

    public final void markFallback(String reason, long nowMillis) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.events.add(new StreamEvent(StreamEventType.FALLBACK, this.phase, StringsKt.take(reason, 120), nowMillis));
    }
}
