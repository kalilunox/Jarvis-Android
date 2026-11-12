package com.jarvis.brain.aiprovider;

import kotlin.Metadata;

/* JADX INFO: compiled from: ChatStreamPipeline.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/aiprovider/ChatSentenceSink;", "", "onSentence", "", "sentence", "", "first", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ChatSentenceSink {
    boolean onSentence(String sentence, boolean first);
}
