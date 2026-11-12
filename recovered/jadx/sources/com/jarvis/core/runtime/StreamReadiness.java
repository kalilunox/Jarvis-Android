package com.jarvis.core.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LatencyUx.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/runtime/StreamReadiness;", "", "asrPartialSupported", "", "llmStreamingSupported", "ttsChunkSupported", "cancellationSupported", "bargeInSupported", "<init>", "(ZZZZZ)V", "getAsrPartialSupported", "()Z", "getLlmStreamingSupported", "getTtsChunkSupported", "getCancellationSupported", "getBargeInSupported", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StreamReadiness {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean asrPartialSupported;
    private final boolean bargeInSupported;
    private final boolean cancellationSupported;
    private final boolean llmStreamingSupported;
    private final boolean ttsChunkSupported;

    public StreamReadiness() {
        this(false, false, false, false, false, 31, null);
    }

    public static /* synthetic */ StreamReadiness copy$default(StreamReadiness streamReadiness, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, Object obj) {
        if ((i & 1) != 0) {
            z = streamReadiness.asrPartialSupported;
        }
        if ((i & 2) != 0) {
            z2 = streamReadiness.llmStreamingSupported;
        }
        boolean z6 = z2;
        if ((i & 4) != 0) {
            z3 = streamReadiness.ttsChunkSupported;
        }
        boolean z7 = z3;
        if ((i & 8) != 0) {
            z4 = streamReadiness.cancellationSupported;
        }
        boolean z8 = z4;
        if ((i & 16) != 0) {
            z5 = streamReadiness.bargeInSupported;
        }
        return streamReadiness.copy(z, z6, z7, z8, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getAsrPartialSupported() {
        return this.asrPartialSupported;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getLlmStreamingSupported() {
        return this.llmStreamingSupported;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getTtsChunkSupported() {
        return this.ttsChunkSupported;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getCancellationSupported() {
        return this.cancellationSupported;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getBargeInSupported() {
        return this.bargeInSupported;
    }

    public final StreamReadiness copy(boolean asrPartialSupported, boolean llmStreamingSupported, boolean ttsChunkSupported, boolean cancellationSupported, boolean bargeInSupported) {
        return new StreamReadiness(asrPartialSupported, llmStreamingSupported, ttsChunkSupported, cancellationSupported, bargeInSupported);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamReadiness)) {
            return false;
        }
        StreamReadiness streamReadiness = (StreamReadiness) other;
        return this.asrPartialSupported == streamReadiness.asrPartialSupported && this.llmStreamingSupported == streamReadiness.llmStreamingSupported && this.ttsChunkSupported == streamReadiness.ttsChunkSupported && this.cancellationSupported == streamReadiness.cancellationSupported && this.bargeInSupported == streamReadiness.bargeInSupported;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.asrPartialSupported) * 31) + Boolean.hashCode(this.llmStreamingSupported)) * 31) + Boolean.hashCode(this.ttsChunkSupported)) * 31) + Boolean.hashCode(this.cancellationSupported)) * 31) + Boolean.hashCode(this.bargeInSupported);
    }

    public String toString() {
        return "StreamReadiness(asrPartialSupported=" + this.asrPartialSupported + ", llmStreamingSupported=" + this.llmStreamingSupported + ", ttsChunkSupported=" + this.ttsChunkSupported + ", cancellationSupported=" + this.cancellationSupported + ", bargeInSupported=" + this.bargeInSupported + ")";
    }

    public StreamReadiness(boolean asrPartialSupported, boolean llmStreamingSupported, boolean ttsChunkSupported, boolean cancellationSupported, boolean bargeInSupported) {
        this.asrPartialSupported = asrPartialSupported;
        this.llmStreamingSupported = llmStreamingSupported;
        this.ttsChunkSupported = ttsChunkSupported;
        this.cancellationSupported = cancellationSupported;
        this.bargeInSupported = bargeInSupported;
    }

    public /* synthetic */ StreamReadiness(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? true : z4, (i & 16) != 0 ? true : z5);
    }

    public final boolean getAsrPartialSupported() {
        return this.asrPartialSupported;
    }

    public final boolean getLlmStreamingSupported() {
        return this.llmStreamingSupported;
    }

    public final boolean getTtsChunkSupported() {
        return this.ttsChunkSupported;
    }

    public final boolean getCancellationSupported() {
        return this.cancellationSupported;
    }

    public final boolean getBargeInSupported() {
        return this.bargeInSupported;
    }

    /* JADX INFO: compiled from: LatencyUx.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/jarvis/core/runtime/StreamReadiness$Companion;", "", "<init>", "()V", "forProviders", "Lcom/jarvis/core/runtime/StreamReadiness;", "llmStreaming", "", "ttsChunk", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ StreamReadiness forProviders$default(Companion companion, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z2 = false;
            }
            return companion.forProviders(z, z2);
        }

        public final StreamReadiness forProviders(boolean llmStreaming, boolean ttsChunk) {
            return new StreamReadiness(false, llmStreaming, ttsChunk, false, false, 25, null);
        }
    }
}
