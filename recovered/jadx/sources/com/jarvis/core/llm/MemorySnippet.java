package com.jarvis.core.llm;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LlmContextModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/llm/MemorySnippet;", "", VoiceService.EXTRA_TEXT, "", "source", "sensitivity", "score", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;F)V", "getText", "()Ljava/lang/String;", "getSource", "getSensitivity", "getScore", "()F", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MemorySnippet {
    private final float score;
    private final String sensitivity;
    private final String source;
    private final String text;

    public static /* synthetic */ MemorySnippet copy$default(MemorySnippet memorySnippet, String str, String str2, String str3, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            str = memorySnippet.text;
        }
        if ((i & 2) != 0) {
            str2 = memorySnippet.source;
        }
        if ((i & 4) != 0) {
            str3 = memorySnippet.sensitivity;
        }
        if ((i & 8) != 0) {
            f = memorySnippet.score;
        }
        return memorySnippet.copy(str, str2, str3, f);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSensitivity() {
        return this.sensitivity;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getScore() {
        return this.score;
    }

    public final MemorySnippet copy(String text, String source, String sensitivity, float score) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        return new MemorySnippet(text, source, sensitivity, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemorySnippet)) {
            return false;
        }
        MemorySnippet memorySnippet = (MemorySnippet) other;
        return Intrinsics.areEqual(this.text, memorySnippet.text) && Intrinsics.areEqual(this.source, memorySnippet.source) && Intrinsics.areEqual(this.sensitivity, memorySnippet.sensitivity) && Float.compare(this.score, memorySnippet.score) == 0;
    }

    public int hashCode() {
        return (((((this.text.hashCode() * 31) + this.source.hashCode()) * 31) + this.sensitivity.hashCode()) * 31) + Float.hashCode(this.score);
    }

    public String toString() {
        return "MemorySnippet(text=" + this.text + ", source=" + this.source + ", sensitivity=" + this.sensitivity + ", score=" + this.score + ")";
    }

    public MemorySnippet(String text, String source, String sensitivity, float score) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        this.text = text;
        this.source = source;
        this.sensitivity = sensitivity;
        this.score = score;
    }

    public /* synthetic */ MemorySnippet(String str, String str2, String str3, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? "NORMAL" : str3, (i & 8) != 0 ? 0.0f : f);
    }

    public final String getText() {
        return this.text;
    }

    public final String getSource() {
        return this.source;
    }

    public final String getSensitivity() {
        return this.sensitivity;
    }

    public final float getScore() {
        return this.score;
    }
}
