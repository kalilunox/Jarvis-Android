package com.jarvis.core.streaming;

import kotlin.Metadata;

/* JADX INFO: compiled from: StreamSession.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/jarvis/core/streaming/CancellationToken;", "", "<init>", "()V", "cancelled", "", "cancel", "", "isCancelled", "throwIfCancelled", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CancellationToken {
    private volatile boolean cancelled;

    public final void cancel() {
        this.cancelled = true;
    }

    /* JADX INFO: renamed from: isCancelled, reason: from getter */
    public final boolean getCancelled() {
        return this.cancelled;
    }

    public final void throwIfCancelled() {
        if (this.cancelled) {
            throw new StreamCancelledException();
        }
    }
}
