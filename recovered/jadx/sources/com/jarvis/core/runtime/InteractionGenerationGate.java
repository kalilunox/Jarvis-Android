package com.jarvis.core.runtime;

import kotlin.Metadata;

/* JADX INFO: compiled from: LatencyUx.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0005J\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/runtime/InteractionGenerationGate;", "", "<init>", "()V", "generation", "", "cancelFlag", "", "begin", "current", "cancelCurrent", "", "isStale", "gen", "isCancelled", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InteractionGenerationGate {
    private volatile boolean cancelFlag;
    private volatile long generation;

    public final long begin() {
        this.cancelFlag = false;
        this.generation++;
        return this.generation;
    }

    /* JADX INFO: renamed from: current, reason: from getter */
    public final long getGeneration() {
        return this.generation;
    }

    public final void cancelCurrent() {
        this.cancelFlag = true;
        this.generation++;
    }

    public final boolean isStale(long gen) {
        return gen != this.generation || this.cancelFlag;
    }

    /* JADX INFO: renamed from: isCancelled, reason: from getter */
    public final boolean getCancelFlag() {
        return this.cancelFlag;
    }
}
