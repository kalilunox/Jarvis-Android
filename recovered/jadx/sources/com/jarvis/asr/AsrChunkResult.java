package com.jarvis.asr;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsrSession.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/jarvis/asr/AsrChunkResult;", "", VoiceService.EXTRA_TEXT, "", "isEndpoint", "", "<init>", "(Ljava/lang/String;Z)V", "getText", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AsrChunkResult {
    private final boolean isEndpoint;
    private final String text;

    public static /* synthetic */ AsrChunkResult copy$default(AsrChunkResult asrChunkResult, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = asrChunkResult.text;
        }
        if ((i & 2) != 0) {
            z = asrChunkResult.isEndpoint;
        }
        return asrChunkResult.copy(str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsEndpoint() {
        return this.isEndpoint;
    }

    public final AsrChunkResult copy(String text, boolean isEndpoint) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new AsrChunkResult(text, isEndpoint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrChunkResult)) {
            return false;
        }
        AsrChunkResult asrChunkResult = (AsrChunkResult) other;
        return Intrinsics.areEqual(this.text, asrChunkResult.text) && this.isEndpoint == asrChunkResult.isEndpoint;
    }

    public int hashCode() {
        return (this.text.hashCode() * 31) + Boolean.hashCode(this.isEndpoint);
    }

    public String toString() {
        return "AsrChunkResult(text=" + this.text + ", isEndpoint=" + this.isEndpoint + ")";
    }

    public AsrChunkResult(String text, boolean isEndpoint) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.isEndpoint = isEndpoint;
    }

    public final String getText() {
        return this.text;
    }

    public final boolean isEndpoint() {
        return this.isEndpoint;
    }
}
