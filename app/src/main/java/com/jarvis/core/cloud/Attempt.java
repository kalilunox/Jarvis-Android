package com.jarvis.core.cloud;

import com.jarvis.app.VoiceService;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;

/* JADX INFO: compiled from: ChainRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u00063"}, d2 = {"Lcom/jarvis/core/cloud/Attempt;", "", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/core/cloud/ChainEntry;", "paramIndex", "", "channel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/jarvis/core/cloud/AttemptEvent;", "stripper", "Lcom/jarvis/core/cloud/ThoughtStripper;", "<init>", "(Lcom/jarvis/core/cloud/ChainEntry;ILkotlinx/coroutines/channels/Channel;Lcom/jarvis/core/cloud/ThoughtStripper;)V", "getEntry", "()Lcom/jarvis/core/cloud/ChainEntry;", "getParamIndex", "()I", "getChannel", "()Lkotlinx/coroutines/channels/Channel;", "getStripper", "()Lcom/jarvis/core/cloud/ThoughtStripper;", "handle", "Lcom/jarvis/core/cloud/CancelHandle;", "getHandle", "()Lcom/jarvis/core/cloud/CancelHandle;", "setHandle", "(Lcom/jarvis/core/cloud/CancelHandle;)V", "cancelled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "receivedEvent", "", "getReceivedEvent", "()Z", "setReceivedEvent", "(Z)V", "startedAtMs", "", "getStartedAtMs", "()J", "setStartedAtMs", "(J)V", "lastError", "Lcom/jarvis/core/cloud/ErrorClass;", "getLastError", "()Lcom/jarvis/core/cloud/ErrorClass;", "setLastError", "(Lcom/jarvis/core/cloud/ErrorClass;)V", "cancelAttempt", "", "assignHandle", "h", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Attempt {
    private final AtomicBoolean cancelled;
    private final Channel<AttemptEvent> channel;
    private final ChainEntry entry;
    private volatile CancelHandle handle;
    private volatile ErrorClass lastError;
    private final int paramIndex;
    private volatile boolean receivedEvent;
    private volatile long startedAtMs;
    private final ThoughtStripper stripper;

    public Attempt(ChainEntry entry, int paramIndex, Channel<AttemptEvent> channel, ThoughtStripper stripper) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(stripper, "stripper");
        this.entry = entry;
        this.paramIndex = paramIndex;
        this.channel = channel;
        this.stripper = stripper;
        this.cancelled = new AtomicBoolean(false);
    }

    public final ChainEntry getEntry() {
        return this.entry;
    }

    public final int getParamIndex() {
        return this.paramIndex;
    }

    public final Channel<AttemptEvent> getChannel() {
        return this.channel;
    }

    public final ThoughtStripper getStripper() {
        return this.stripper;
    }

    public final CancelHandle getHandle() {
        return this.handle;
    }

    public final void setHandle(CancelHandle cancelHandle) {
        this.handle = cancelHandle;
    }

    public final boolean getReceivedEvent() {
        return this.receivedEvent;
    }

    public final void setReceivedEvent(boolean z) {
        this.receivedEvent = z;
    }

    public final long getStartedAtMs() {
        return this.startedAtMs;
    }

    public final void setStartedAtMs(long j) {
        this.startedAtMs = j;
    }

    public final ErrorClass getLastError() {
        return this.lastError;
    }

    public final void setLastError(ErrorClass errorClass) {
        this.lastError = errorClass;
    }

    public final void cancelAttempt() {
        CancelHandle cancelHandle;
        if (this.cancelled.compareAndSet(false, true) && (cancelHandle = this.handle) != null) {
            cancelHandle.cancel();
        }
    }

    public final void assignHandle(CancelHandle h) {
        Intrinsics.checkNotNullParameter(h, "h");
        this.handle = h;
        if (this.cancelled.get()) {
            h.cancel();
        }
    }
}
