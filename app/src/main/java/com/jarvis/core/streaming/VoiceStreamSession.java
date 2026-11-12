package com.jarvis.core.streaming;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SentenceSegmenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000fJ\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u001dJ\u0006\u0010\"\u001a\u00020\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/jarvis/core/streaming/VoiceStreamSession;", "", "requestId", "", "generationId", "", "streamSession", "Lcom/jarvis/core/streaming/StreamSession;", "<init>", "(Ljava/lang/String;JLcom/jarvis/core/streaming/StreamSession;)V", "getRequestId", "()Ljava/lang/String;", "getGenerationId", "()J", "value", "Lcom/jarvis/core/streaming/VoiceStreamState;", "state", "getState", "()Lcom/jarvis/core/streaming/VoiceStreamState;", "cancellation", "Lcom/jarvis/core/streaming/CancellationToken;", "getCancellation", "()Lcom/jarvis/core/streaming/CancellationToken;", "textQueue", "Lcom/jarvis/core/streaming/BoundedTextChunkQueue;", "getTextQueue", "()Lcom/jarvis/core/streaming/BoundedTextChunkQueue;", "lock", "transition", "", TypedValues.TransitionType.S_TO, "cancel", "", "canSpeak", "underlying", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceStreamSession {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long generationId;
    private final Object lock;
    private final String requestId;
    private volatile VoiceStreamState state;
    private final StreamSession streamSession;
    private final BoundedTextChunkQueue textQueue;

    /* JADX INFO: compiled from: SentenceSegmenter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VoiceStreamState.values().length];
            try {
                iArr[VoiceStreamState.CANCELLING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VoiceStreamState.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VoiceStreamState.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[VoiceStreamState.COMPLETED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VoiceStreamSession(String requestId, long generationId, StreamSession streamSession) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(streamSession, "streamSession");
        this.requestId = requestId;
        this.generationId = generationId;
        this.streamSession = streamSession;
        this.state = VoiceStreamState.IDLE;
        this.textQueue = new BoundedTextChunkQueue(0, 0, 3, null);
        this.lock = new Object();
    }

    public /* synthetic */ VoiceStreamSession(String str, long j, StreamSession streamSession, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, (i & 4) != 0 ? new StreamSession(str, null, 0L, 6, null) : streamSession);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final long getGenerationId() {
        return this.generationId;
    }

    public final VoiceStreamState getState() {
        return this.state;
    }

    public final CancellationToken getCancellation() {
        return this.streamSession.getCancellation();
    }

    public final BoundedTextChunkQueue getTextQueue() {
        return this.textQueue;
    }

    public final boolean transition(VoiceStreamState to) {
        Intrinsics.checkNotNullParameter(to, "to");
        synchronized (this.lock) {
            if (!INSTANCE.isAllowed(this.state, to)) {
                return false;
            }
            this.state = to;
            switch (WhenMappings.$EnumSwitchMapping$0[to.ordinal()]) {
                case 1:
                    this.streamSession.emit(new StreamEvent(StreamEventType.CANCELLED, StreamPhase.LLM, null, 0L, 12, null));
                    getCancellation().cancel();
                    this.textQueue.clear();
                    break;
                case 2:
                    if (!getCancellation().getCancelled()) {
                        getCancellation().cancel();
                    }
                    this.textQueue.clear();
                    break;
                case 3:
                    this.streamSession.emit(new StreamEvent(StreamEventType.ERROR, StreamPhase.LLM, null, 0L, 12, null));
                    break;
                case 4:
                    this.streamSession.emit(new StreamEvent(StreamEventType.FINAL_LLM, StreamPhase.LLM, null, 0L, 12, null));
                    break;
            }
            return true;
        }
    }

    public final void cancel() {
        transition(VoiceStreamState.CANCELLING);
        transition(VoiceStreamState.CANCELLED);
    }

    public final boolean canSpeak() {
        return this.state == VoiceStreamState.STREAMING || this.state == VoiceStreamState.DRAINING || this.state == VoiceStreamState.STARTING;
    }

    /* JADX INFO: renamed from: underlying, reason: from getter */
    public final StreamSession getStreamSession() {
        return this.streamSession;
    }

    /* JADX INFO: compiled from: SentenceSegmenter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/jarvis/core/streaming/VoiceStreamSession$Companion;", "", "<init>", "()V", "isAllowed", "", TypedValues.TransitionType.S_FROM, "Lcom/jarvis/core/streaming/VoiceStreamState;", TypedValues.TransitionType.S_TO, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: SentenceSegmenter.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[VoiceStreamState.values().length];
                try {
                    iArr[VoiceStreamState.IDLE.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[VoiceStreamState.STARTING.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[VoiceStreamState.STREAMING.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[VoiceStreamState.DRAINING.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[VoiceStreamState.FALLBACK.ordinal()] = 5;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[VoiceStreamState.CANCELLING.ordinal()] = 6;
                } catch (NoSuchFieldError e6) {
                }
                try {
                    iArr[VoiceStreamState.COMPLETED.ordinal()] = 7;
                } catch (NoSuchFieldError e7) {
                }
                try {
                    iArr[VoiceStreamState.CANCELLED.ordinal()] = 8;
                } catch (NoSuchFieldError e8) {
                }
                try {
                    iArr[VoiceStreamState.FAILED.ordinal()] = 9;
                } catch (NoSuchFieldError e9) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isAllowed(VoiceStreamState from, VoiceStreamState to) {
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            if (from == to) {
                return true;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[from.ordinal()]) {
                case 1:
                    return SetsKt.setOf((Object[]) new VoiceStreamState[]{VoiceStreamState.STARTING, VoiceStreamState.CANCELLED, VoiceStreamState.FAILED}).contains(to);
                case 2:
                    return SetsKt.setOf((Object[]) new VoiceStreamState[]{VoiceStreamState.STREAMING, VoiceStreamState.FALLBACK, VoiceStreamState.CANCELLING, VoiceStreamState.FAILED, VoiceStreamState.CANCELLED}).contains(to);
                case 3:
                    return SetsKt.setOf((Object[]) new VoiceStreamState[]{VoiceStreamState.DRAINING, VoiceStreamState.COMPLETED, VoiceStreamState.CANCELLING, VoiceStreamState.FAILED, VoiceStreamState.CANCELLED, VoiceStreamState.FALLBACK}).contains(to);
                case 4:
                    return SetsKt.setOf((Object[]) new VoiceStreamState[]{VoiceStreamState.COMPLETED, VoiceStreamState.CANCELLING, VoiceStreamState.CANCELLED, VoiceStreamState.FAILED}).contains(to);
                case 5:
                    return SetsKt.setOf((Object[]) new VoiceStreamState[]{VoiceStreamState.COMPLETED, VoiceStreamState.FAILED, VoiceStreamState.CANCELLED}).contains(to);
                case 6:
                    return to == VoiceStreamState.CANCELLED;
                case 7:
                case 8:
                case 9:
                    return false;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }
}
