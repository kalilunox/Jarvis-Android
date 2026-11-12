package com.jarvis.core.streaming;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamSession.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/streaming/StreamEvent;", "", "type", "Lcom/jarvis/core/streaming/StreamEventType;", TypedValues.CycleType.S_WAVE_PHASE, "Lcom/jarvis/core/streaming/StreamPhase;", VoiceService.EXTRA_TEXT, "", "atMillis", "", "<init>", "(Lcom/jarvis/core/streaming/StreamEventType;Lcom/jarvis/core/streaming/StreamPhase;Ljava/lang/String;J)V", "getType", "()Lcom/jarvis/core/streaming/StreamEventType;", "getPhase", "()Lcom/jarvis/core/streaming/StreamPhase;", "getText", "()Ljava/lang/String;", "getAtMillis", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StreamEvent {
    private final long atMillis;
    private final StreamPhase phase;
    private final String text;
    private final StreamEventType type;

    public static /* synthetic */ StreamEvent copy$default(StreamEvent streamEvent, StreamEventType streamEventType, StreamPhase streamPhase, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            streamEventType = streamEvent.type;
        }
        if ((i & 2) != 0) {
            streamPhase = streamEvent.phase;
        }
        StreamPhase streamPhase2 = streamPhase;
        if ((i & 4) != 0) {
            str = streamEvent.text;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            j = streamEvent.atMillis;
        }
        return streamEvent.copy(streamEventType, streamPhase2, str2, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final StreamEventType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final StreamPhase getPhase() {
        return this.phase;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getAtMillis() {
        return this.atMillis;
    }

    public final StreamEvent copy(StreamEventType type, StreamPhase phase, String text, long atMillis) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(text, "text");
        return new StreamEvent(type, phase, text, atMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamEvent)) {
            return false;
        }
        StreamEvent streamEvent = (StreamEvent) other;
        return this.type == streamEvent.type && this.phase == streamEvent.phase && Intrinsics.areEqual(this.text, streamEvent.text) && this.atMillis == streamEvent.atMillis;
    }

    public int hashCode() {
        return (((((this.type.hashCode() * 31) + this.phase.hashCode()) * 31) + this.text.hashCode()) * 31) + Long.hashCode(this.atMillis);
    }

    public String toString() {
        return "StreamEvent(type=" + this.type + ", phase=" + this.phase + ", text=" + this.text + ", atMillis=" + this.atMillis + ")";
    }

    public StreamEvent(StreamEventType type, StreamPhase phase, String text, long atMillis) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(text, "text");
        this.type = type;
        this.phase = phase;
        this.text = text;
        this.atMillis = atMillis;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ StreamEvent(StreamEventType streamEventType, StreamPhase streamPhase, String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str2;
        long j2;
        if ((i & 4) == 0) {
            str2 = str;
        } else {
            str2 = "";
        }
        if ((i & 8) == 0) {
            j2 = j;
        } else {
            j2 = 0;
        }
        this(streamEventType, streamPhase, str2, j2);
    }

    public final StreamEventType getType() {
        return this.type;
    }

    public final StreamPhase getPhase() {
        return this.phase;
    }

    public final String getText() {
        return this.text;
    }

    public final long getAtMillis() {
        return this.atMillis;
    }
}
