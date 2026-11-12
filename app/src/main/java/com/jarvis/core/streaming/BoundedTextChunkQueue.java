package com.jarvis.core.streaming;

import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SentenceSegmenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tJ\b\u0010\u000e\u001a\u0004\u0018\u00010\tJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0003J\u0006\u0010\u0012\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/streaming/BoundedTextChunkQueue;", "", "maxChunks", "", "maxChars", "<init>", "(II)V", "q", "Lkotlin/collections/ArrayDeque;", "", "chars", "offer", "", "chunk", "poll", "clear", "", "size", "isEmpty", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BoundedTextChunkQueue {
    private int chars;
    private final int maxChars;
    private final int maxChunks;
    private final ArrayDeque<String> q;

    /* JADX WARN: Illegal instructions before constructor call */
    public BoundedTextChunkQueue() {
        int i = 0;
        this(i, i, 3, null);
    }

    public BoundedTextChunkQueue(int maxChunks, int maxChars) {
        this.maxChunks = maxChunks;
        this.maxChars = maxChars;
        this.q = new ArrayDeque<>();
    }

    public /* synthetic */ BoundedTextChunkQueue(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 8 : i, (i3 & 2) != 0 ? 2400 : i2);
    }

    public final synchronized boolean offer(String chunk) {
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        String t = StringsKt.trim((CharSequence) chunk).toString();
        if (t.length() == 0) {
            return true;
        }
        if (this.q.size() < this.maxChunks && this.chars + t.length() <= this.maxChars) {
            this.q.addLast(t);
            this.chars += t.length();
            return true;
        }
        return false;
    }

    public final synchronized String poll() {
        String t = this.q.removeFirstOrNull();
        if (t == null) {
            return null;
        }
        this.chars = RangesKt.coerceAtLeast(this.chars - t.length(), 0);
        return t;
    }

    public final synchronized void clear() {
        this.q.clear();
        this.chars = 0;
    }

    public final synchronized int size() {
        return this.q.size();
    }

    public final synchronized boolean isEmpty() {
        return this.q.isEmpty();
    }
}
