package com.jarvis.core.llm;

import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LlmContextModels.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Lcom/jarvis/core/llm/LlmTokenEstimate;", "", "<init>", "()V", "charsToTokens", "", "chars", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LlmTokenEstimate {
    public static final LlmTokenEstimate INSTANCE = new LlmTokenEstimate();

    private LlmTokenEstimate() {
    }

    public final int charsToTokens(int chars) {
        return RangesKt.coerceAtLeast((int) (((double) chars) / 3.5d), 0);
    }
}
