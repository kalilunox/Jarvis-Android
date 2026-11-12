package com.jarvis.core.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: InteractionLatency.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\bk\n\u0002\u0018\u0002\n\u0002\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u008a\u0001\u001a\u00020\u00052\t\b\u0002\u0010\u008b\u0001\u001a\u00020\u0005J\u001a\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0003\u0010\u008e\u0001J%\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0003\u0010\u0092\u0001J\u000e\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010 \u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010¡\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010¢\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010£\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010¤\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010¥\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u000e\u0010¦\u0001\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\u0007\u0010§\u0001\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010$\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u001e\u0010'\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b(\u0010 \"\u0004\b)\u0010\"R\u001e\u0010*\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"R\u001e\u0010-\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b.\u0010 \"\u0004\b/\u0010\"R\u001e\u00100\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b1\u0010 \"\u0004\b2\u0010\"R\u001e\u00103\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b4\u0010 \"\u0004\b5\u0010\"R\u001e\u00106\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b7\u0010 \"\u0004\b8\u0010\"R\u001e\u00109\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b:\u0010 \"\u0004\b;\u0010\"R\u001e\u0010<\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b=\u0010 \"\u0004\b>\u0010\"R\u001e\u0010?\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b@\u0010 \"\u0004\bA\u0010\"R\u001e\u0010B\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bC\u0010 \"\u0004\bD\u0010\"R\u001e\u0010E\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bF\u0010 \"\u0004\bG\u0010\"R\u001e\u0010H\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bI\u0010 \"\u0004\bJ\u0010\"R\u001e\u0010K\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bL\u0010 \"\u0004\bM\u0010\"R\u001e\u0010N\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bO\u0010 \"\u0004\bP\u0010\"R\u001e\u0010Q\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bR\u0010 \"\u0004\bS\u0010\"R\u001e\u0010T\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bU\u0010 \"\u0004\bV\u0010\"R\u001e\u0010W\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bX\u0010 \"\u0004\bY\u0010\"R\u001e\u0010Z\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b[\u0010 \"\u0004\b\\\u0010\"R\u001e\u0010]\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b^\u0010 \"\u0004\b_\u0010\"R\u001e\u0010`\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\ba\u0010 \"\u0004\bb\u0010\"R\u001e\u0010c\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bd\u0010 \"\u0004\be\u0010\"R\u001e\u0010f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bg\u0010 \"\u0004\bh\u0010\"R\u001e\u0010i\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bj\u0010 \"\u0004\bk\u0010\"R\u001e\u0010l\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bm\u0010 \"\u0004\bn\u0010\"R\u001e\u0010o\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bp\u0010 \"\u0004\bq\u0010\"R\u001e\u0010r\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bs\u0010 \"\u0004\bt\u0010\"R\u001e\u0010u\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bv\u0010 \"\u0004\bw\u0010\"R\u001e\u0010x\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\by\u0010 \"\u0004\bz\u0010\"R\u001e\u0010{\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b|\u0010 \"\u0004\b}\u0010\"R\u001f\u0010~\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0011\n\u0002\u0010#\u001a\u0004\b\u007f\u0010 \"\u0005\b\u0080\u0001\u0010\"R \u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0006\b\u0085\u0001\u0010\u0086\u0001R\u001d\u0010\u0087\u0001\u001a\u00020\rX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u000f\"\u0005\b\u0089\u0001\u0010\u0011¨\u0006¨\u0001"}, d2 = {"Lcom/jarvis/core/runtime/InteractionLatency;", "", "requestId", "", "anchorElapsedMs", "", "<init>", "(Ljava/lang/String;J)V", "getRequestId", "()Ljava/lang/String;", "getAnchorElapsedMs", "()J", "cancelled", "", "getCancelled", "()Z", "setCancelled", "(Z)V", "coldStart", "getColdStart", "setColdStart", "warmState", "Lcom/jarvis/core/runtime/WarmState;", "getWarmState", "()Lcom/jarvis/core/runtime/WarmState;", "setWarmState", "(Lcom/jarvis/core/runtime/WarmState;)V", "fallback", "getFallback", "setFallback", "wakeDetectedAt", "getWakeDetectedAt", "()Ljava/lang/Long;", "setWakeDetectedAt", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "asrStartAt", "getAsrStartAt", "setAsrStartAt", "asrPartialAt", "getAsrPartialAt", "setAsrPartialAt", "asrFinalAt", "getAsrFinalAt", "setAsrFinalAt", "normalizeStartAt", "getNormalizeStartAt", "setNormalizeStartAt", "normalizeEndAt", "getNormalizeEndAt", "setNormalizeEndAt", "semanticStartAt", "getSemanticStartAt", "setSemanticStartAt", "semanticEndAt", "getSemanticEndAt", "setSemanticEndAt", "routeStartAt", "getRouteStartAt", "setRouteStartAt", "routeEndAt", "getRouteEndAt", "setRouteEndAt", "contextStartAt", "getContextStartAt", "setContextStartAt", "contextEndAt", "getContextEndAt", "setContextEndAt", "aiRouteStartAt", "getAiRouteStartAt", "setAiRouteStartAt", "aiRouteEndAt", "getAiRouteEndAt", "setAiRouteEndAt", "llmRequestStartAt", "getLlmRequestStartAt", "setLlmRequestStartAt", "llmFirstByteAt", "getLlmFirstByteAt", "setLlmFirstByteAt", "llmFirstTokenAt", "getLlmFirstTokenAt", "setLlmFirstTokenAt", "llmCompleteAt", "getLlmCompleteAt", "setLlmCompleteAt", "structuredParseStartAt", "getStructuredParseStartAt", "setStructuredParseStartAt", "structuredParseEndAt", "getStructuredParseEndAt", "setStructuredParseEndAt", "plannerStartAt", "getPlannerStartAt", "setPlannerStartAt", "plannerEndAt", "getPlannerEndAt", "setPlannerEndAt", "toolStartAt", "getToolStartAt", "setToolStartAt", "toolEndAt", "getToolEndAt", "setToolEndAt", "verificationStartAt", "getVerificationStartAt", "setVerificationStartAt", "verificationEndAt", "getVerificationEndAt", "setVerificationEndAt", "ttsStartAt", "getTtsStartAt", "setTtsStartAt", "ttsFirstAudioAt", "getTtsFirstAudioAt", "setTtsFirstAudioAt", "ttsCompleteAt", "getTtsCompleteAt", "setTtsCompleteAt", "firstAckAt", "getFirstAckAt", "setFirstAckAt", "firstUsefulAt", "getFirstUsefulAt", "setFirstUsefulAt", "interactionCompleteAt", "getInteractionCompleteAt", "setInteractionCompleteAt", "ttftKind", "Lcom/jarvis/core/runtime/TtftKind;", "getTtftKind", "()Lcom/jarvis/core/runtime/TtftKind;", "setTtftKind", "(Lcom/jarvis/core/runtime/TtftKind;)V", "streamingUsed", "getStreamingUsed", "setStreamingUsed", "mark", "now", "sinceAnchor", "at", "(Ljava/lang/Long;)Ljava/lang/Long;", "span", "start", "end", "(Ljava/lang/Long;Ljava/lang/Long;)Ljava/lang/Long;", "timeToFirstAcknowledgementMs", "timeToFirstUsefulResultMs", "timeToFirstAudioMs", "timeToCompletionMs", "endToEndMs", "asrMs", "semanticMs", "contextMs", "routeMs", "aiRouteMs", "aiWaitMs", "aiFirstByteMs", "realTtftMs", "aiCompletionMs", "parseMs", "plannerMs", "toolMs", "verifyMs", "ttsStartupMs", "ttsFirstAudioFromAnchorMs", "summaryLine", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InteractionLatency {
    private Long aiRouteEndAt;
    private Long aiRouteStartAt;
    private final long anchorElapsedMs;
    private Long asrFinalAt;
    private Long asrPartialAt;
    private Long asrStartAt;
    private volatile boolean cancelled;
    private volatile boolean coldStart;
    private Long contextEndAt;
    private Long contextStartAt;
    private volatile boolean fallback;
    private Long firstAckAt;
    private Long firstUsefulAt;
    private Long interactionCompleteAt;
    private Long llmCompleteAt;
    private Long llmFirstByteAt;
    private Long llmFirstTokenAt;
    private Long llmRequestStartAt;
    private Long normalizeEndAt;
    private Long normalizeStartAt;
    private Long plannerEndAt;
    private Long plannerStartAt;
    private final String requestId;
    private Long routeEndAt;
    private Long routeStartAt;
    private Long semanticEndAt;
    private Long semanticStartAt;
    private boolean streamingUsed;
    private Long structuredParseEndAt;
    private Long structuredParseStartAt;
    private Long toolEndAt;
    private Long toolStartAt;
    private TtftKind ttftKind;
    private Long ttsCompleteAt;
    private Long ttsFirstAudioAt;
    private Long ttsStartAt;
    private Long verificationEndAt;
    private Long verificationStartAt;
    private Long wakeDetectedAt;
    private volatile WarmState warmState;

    public InteractionLatency(String requestId, long anchorElapsedMs) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.requestId = requestId;
        this.anchorElapsedMs = anchorElapsedMs;
        this.warmState = WarmState.UNKNOWN;
        this.ttftKind = TtftKind.NOT_AVAILABLE;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final long getAnchorElapsedMs() {
        return this.anchorElapsedMs;
    }

    public final boolean getCancelled() {
        return this.cancelled;
    }

    public final void setCancelled(boolean z) {
        this.cancelled = z;
    }

    public final boolean getColdStart() {
        return this.coldStart;
    }

    public final void setColdStart(boolean z) {
        this.coldStart = z;
    }

    public final WarmState getWarmState() {
        return this.warmState;
    }

    public final void setWarmState(WarmState warmState) {
        Intrinsics.checkNotNullParameter(warmState, "<set-?>");
        this.warmState = warmState;
    }

    public final boolean getFallback() {
        return this.fallback;
    }

    public final void setFallback(boolean z) {
        this.fallback = z;
    }

    public final Long getWakeDetectedAt() {
        return this.wakeDetectedAt;
    }

    public final void setWakeDetectedAt(Long l) {
        this.wakeDetectedAt = l;
    }

    public final Long getAsrStartAt() {
        return this.asrStartAt;
    }

    public final void setAsrStartAt(Long l) {
        this.asrStartAt = l;
    }

    public final Long getAsrPartialAt() {
        return this.asrPartialAt;
    }

    public final void setAsrPartialAt(Long l) {
        this.asrPartialAt = l;
    }

    public final Long getAsrFinalAt() {
        return this.asrFinalAt;
    }

    public final void setAsrFinalAt(Long l) {
        this.asrFinalAt = l;
    }

    public final Long getNormalizeStartAt() {
        return this.normalizeStartAt;
    }

    public final void setNormalizeStartAt(Long l) {
        this.normalizeStartAt = l;
    }

    public final Long getNormalizeEndAt() {
        return this.normalizeEndAt;
    }

    public final void setNormalizeEndAt(Long l) {
        this.normalizeEndAt = l;
    }

    public final Long getSemanticStartAt() {
        return this.semanticStartAt;
    }

    public final void setSemanticStartAt(Long l) {
        this.semanticStartAt = l;
    }

    public final Long getSemanticEndAt() {
        return this.semanticEndAt;
    }

    public final void setSemanticEndAt(Long l) {
        this.semanticEndAt = l;
    }

    public final Long getRouteStartAt() {
        return this.routeStartAt;
    }

    public final void setRouteStartAt(Long l) {
        this.routeStartAt = l;
    }

    public final Long getRouteEndAt() {
        return this.routeEndAt;
    }

    public final void setRouteEndAt(Long l) {
        this.routeEndAt = l;
    }

    public final Long getContextStartAt() {
        return this.contextStartAt;
    }

    public final void setContextStartAt(Long l) {
        this.contextStartAt = l;
    }

    public final Long getContextEndAt() {
        return this.contextEndAt;
    }

    public final void setContextEndAt(Long l) {
        this.contextEndAt = l;
    }

    public final Long getAiRouteStartAt() {
        return this.aiRouteStartAt;
    }

    public final void setAiRouteStartAt(Long l) {
        this.aiRouteStartAt = l;
    }

    public final Long getAiRouteEndAt() {
        return this.aiRouteEndAt;
    }

    public final void setAiRouteEndAt(Long l) {
        this.aiRouteEndAt = l;
    }

    public final Long getLlmRequestStartAt() {
        return this.llmRequestStartAt;
    }

    public final void setLlmRequestStartAt(Long l) {
        this.llmRequestStartAt = l;
    }

    public final Long getLlmFirstByteAt() {
        return this.llmFirstByteAt;
    }

    public final void setLlmFirstByteAt(Long l) {
        this.llmFirstByteAt = l;
    }

    public final Long getLlmFirstTokenAt() {
        return this.llmFirstTokenAt;
    }

    public final void setLlmFirstTokenAt(Long l) {
        this.llmFirstTokenAt = l;
    }

    public final Long getLlmCompleteAt() {
        return this.llmCompleteAt;
    }

    public final void setLlmCompleteAt(Long l) {
        this.llmCompleteAt = l;
    }

    public final Long getStructuredParseStartAt() {
        return this.structuredParseStartAt;
    }

    public final void setStructuredParseStartAt(Long l) {
        this.structuredParseStartAt = l;
    }

    public final Long getStructuredParseEndAt() {
        return this.structuredParseEndAt;
    }

    public final void setStructuredParseEndAt(Long l) {
        this.structuredParseEndAt = l;
    }

    public final Long getPlannerStartAt() {
        return this.plannerStartAt;
    }

    public final void setPlannerStartAt(Long l) {
        this.plannerStartAt = l;
    }

    public final Long getPlannerEndAt() {
        return this.plannerEndAt;
    }

    public final void setPlannerEndAt(Long l) {
        this.plannerEndAt = l;
    }

    public final Long getToolStartAt() {
        return this.toolStartAt;
    }

    public final void setToolStartAt(Long l) {
        this.toolStartAt = l;
    }

    public final Long getToolEndAt() {
        return this.toolEndAt;
    }

    public final void setToolEndAt(Long l) {
        this.toolEndAt = l;
    }

    public final Long getVerificationStartAt() {
        return this.verificationStartAt;
    }

    public final void setVerificationStartAt(Long l) {
        this.verificationStartAt = l;
    }

    public final Long getVerificationEndAt() {
        return this.verificationEndAt;
    }

    public final void setVerificationEndAt(Long l) {
        this.verificationEndAt = l;
    }

    public final Long getTtsStartAt() {
        return this.ttsStartAt;
    }

    public final void setTtsStartAt(Long l) {
        this.ttsStartAt = l;
    }

    public final Long getTtsFirstAudioAt() {
        return this.ttsFirstAudioAt;
    }

    public final void setTtsFirstAudioAt(Long l) {
        this.ttsFirstAudioAt = l;
    }

    public final Long getTtsCompleteAt() {
        return this.ttsCompleteAt;
    }

    public final void setTtsCompleteAt(Long l) {
        this.ttsCompleteAt = l;
    }

    public final Long getFirstAckAt() {
        return this.firstAckAt;
    }

    public final void setFirstAckAt(Long l) {
        this.firstAckAt = l;
    }

    public final Long getFirstUsefulAt() {
        return this.firstUsefulAt;
    }

    public final void setFirstUsefulAt(Long l) {
        this.firstUsefulAt = l;
    }

    public final Long getInteractionCompleteAt() {
        return this.interactionCompleteAt;
    }

    public final void setInteractionCompleteAt(Long l) {
        this.interactionCompleteAt = l;
    }

    public final TtftKind getTtftKind() {
        return this.ttftKind;
    }

    public final void setTtftKind(TtftKind ttftKind) {
        Intrinsics.checkNotNullParameter(ttftKind, "<set-?>");
        this.ttftKind = ttftKind;
    }

    public final boolean getStreamingUsed() {
        return this.streamingUsed;
    }

    public final void setStreamingUsed(boolean z) {
        this.streamingUsed = z;
    }

    public static /* synthetic */ long mark$default(InteractionLatency interactionLatency, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = LatencyClock.INSTANCE.elapsedMs();
        }
        return interactionLatency.mark(j);
    }

    public final long mark(long now) {
        return now;
    }

    public final Long sinceAnchor(Long at) {
        if (at == null) {
            return null;
        }
        long it = at.longValue();
        return Long.valueOf(RangesKt.coerceAtLeast(it - this.anchorElapsedMs, 0L));
    }

    public final Long span(Long start, Long end) {
        if (start == null || end == null) {
            return null;
        }
        return Long.valueOf(RangesKt.coerceAtLeast(end.longValue() - start.longValue(), 0L));
    }

    public final Long timeToFirstAcknowledgementMs() {
        return sinceAnchor(this.firstAckAt);
    }

    public final Long timeToFirstUsefulResultMs() {
        return sinceAnchor(this.firstUsefulAt);
    }

    public final Long timeToFirstAudioMs() {
        return sinceAnchor(this.ttsFirstAudioAt);
    }

    public final Long timeToCompletionMs() {
        return sinceAnchor(this.interactionCompleteAt);
    }

    public final Long endToEndMs() {
        Long lTimeToCompletionMs = timeToCompletionMs();
        return lTimeToCompletionMs == null ? timeToFirstAudioMs() : lTimeToCompletionMs;
    }

    public final Long asrMs() {
        return span(this.asrStartAt, this.asrFinalAt);
    }

    public final Long semanticMs() {
        return span(this.semanticStartAt, this.semanticEndAt);
    }

    public final Long contextMs() {
        return span(this.contextStartAt, this.contextEndAt);
    }

    public final Long routeMs() {
        return span(this.routeStartAt, this.routeEndAt);
    }

    public final Long aiRouteMs() {
        return span(this.aiRouteStartAt, this.aiRouteEndAt);
    }

    public final Long aiWaitMs() {
        return span(this.llmRequestStartAt, this.llmCompleteAt);
    }

    public final Long aiFirstByteMs() {
        return span(this.llmRequestStartAt, this.llmFirstByteAt);
    }

    public final Long realTtftMs() {
        if (this.ttftKind != TtftKind.REAL) {
            return null;
        }
        Long l = this.llmRequestStartAt;
        Long l2 = this.llmFirstTokenAt;
        if (l2 == null) {
            l2 = this.llmFirstByteAt;
        }
        return span(l, l2);
    }

    public final Long aiCompletionMs() {
        return span(this.llmRequestStartAt, this.llmCompleteAt);
    }

    public final Long parseMs() {
        return span(this.structuredParseStartAt, this.structuredParseEndAt);
    }

    public final Long plannerMs() {
        return span(this.plannerStartAt, this.plannerEndAt);
    }

    public final Long toolMs() {
        return span(this.toolStartAt, this.toolEndAt);
    }

    public final Long verifyMs() {
        return span(this.verificationStartAt, this.verificationEndAt);
    }

    public final Long ttsStartupMs() {
        return span(this.ttsStartAt, this.ttsFirstAudioAt);
    }

    public final Long ttsFirstAudioFromAnchorMs() {
        return timeToFirstAudioMs();
    }

    public final String summaryLine() {
        String str = this.requestId;
        Object objEndToEndMs = endToEndMs();
        if (objEndToEndMs == null) {
            objEndToEndMs = "-";
        }
        Object objTimeToFirstAcknowledgementMs = timeToFirstAcknowledgementMs();
        if (objTimeToFirstAcknowledgementMs == null) {
            objTimeToFirstAcknowledgementMs = "-";
        }
        Object objTimeToFirstUsefulResultMs = timeToFirstUsefulResultMs();
        if (objTimeToFirstUsefulResultMs == null) {
            objTimeToFirstUsefulResultMs = "-";
        }
        Object objTimeToFirstAudioMs = timeToFirstAudioMs();
        if (objTimeToFirstAudioMs == null) {
            objTimeToFirstAudioMs = "-";
        }
        Object objTimeToCompletionMs = timeToCompletionMs();
        if (objTimeToCompletionMs == null) {
            objTimeToCompletionMs = "-";
        }
        Object objAsrMs = asrMs();
        if (objAsrMs == null) {
            objAsrMs = "-";
        }
        Object objSemanticMs = semanticMs();
        if (objSemanticMs == null) {
            objSemanticMs = "-";
        }
        Object objContextMs = contextMs();
        if (objContextMs == null) {
            objContextMs = "-";
        }
        Object objRouteMs = routeMs();
        if (objRouteMs == null) {
            objRouteMs = "-";
        }
        Object objAiWaitMs = aiWaitMs();
        if (objAiWaitMs == null) {
            objAiWaitMs = "-";
        }
        Object objAiFirstByteMs = aiFirstByteMs();
        if (objAiFirstByteMs == null) {
            objAiFirstByteMs = "-";
        }
        Object objRealTtftMs = realTtftMs();
        if (objRealTtftMs == null) {
            objRealTtftMs = "-";
        }
        TtftKind ttftKind = this.ttftKind;
        Long lAiCompletionMs = aiCompletionMs();
        Object obj = lAiCompletionMs != null ? lAiCompletionMs : "-";
        Long ms = parseMs();
        Object obj2 = ms == null ? "-" : ms;
        Long lPlannerMs = plannerMs();
        Object obj3 = lPlannerMs == null ? "-" : lPlannerMs;
        Long l = toolMs();
        Object obj4 = l == null ? "-" : l;
        Long lVerifyMs = verifyMs();
        Object obj5 = lVerifyMs == null ? "-" : lVerifyMs;
        Long lTtsStartupMs = ttsStartupMs();
        Object obj6 = lTtsStartupMs == null ? "-" : lTtsStartupMs;
        return "lat req=" + str + " e2e=" + objEndToEndMs + " ack=" + objTimeToFirstAcknowledgementMs + " useful=" + objTimeToFirstUsefulResultMs + " audio=" + objTimeToFirstAudioMs + " done=" + objTimeToCompletionMs + " asr=" + objAsrMs + " sem=" + objSemanticMs + " ctx=" + objContextMs + " route=" + objRouteMs + " aiWait=" + objAiWaitMs + " aiFB=" + objAiFirstByteMs + " realTtft=" + objRealTtftMs + " ttftKind=" + ttftKind + " aiDone=" + obj + " parse=" + obj2 + " plan=" + obj3 + " tool=" + obj4 + " verify=" + obj5 + " ttsStart=" + obj6 + " warm=" + this.warmState + " cold=" + this.coldStart + " cancelled=" + this.cancelled + " fallback=" + this.fallback + " stream=" + this.streamingUsed;
    }
}
