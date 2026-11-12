package com.jarvis.core.proactive;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.behavior.BehaviorFeedback;
import com.jarvis.core.companion.ContextSnapshot;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProactiveRuntime.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\u000f\u001a\u0004\u0018\u00010\u000bJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJL\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\u000e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 J \u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001b\u001a\u00020%2\b\b\u0002\u0010\u001d\u001a\u00020\u000eJ\u0006\u0010&\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/jarvis/core/proactive/ProactiveRuntime;", "", "engine", "Lcom/jarvis/core/proactive/ProactiveDecisionEngine;", "cooldownPolicy", "Lcom/jarvis/core/proactive/CooldownPolicy;", "<init>", "(Lcom/jarvis/core/proactive/ProactiveDecisionEngine;Lcom/jarvis/core/proactive/CooldownPolicy;)V", "state", "Lcom/jarvis/core/proactive/ProactivityState;", "lastVisible", "Lcom/jarvis/core/proactive/ProactiveDecision;", "lastEvaluated", "lastEvaluateAt", "", "lastVisibleDecision", "lastEvaluatedDecision", "restore", "", "saved", "evaluate", "Lcom/jarvis/core/proactive/DecisionEvaluationResult;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "candidates", "", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "feedback", "Lcom/jarvis/core/proactive/BehaviorFeedbackView;", "nowMillis", "minIntervalMs", "force", "", "bypassCoalesce", "afterFeedback", ProactiveActionReceiver.EXTRA_PATTERN_KEY, "", "Lcom/jarvis/core/behavior/BehaviorFeedback;", "clearVisible", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveRuntime {
    private final CooldownPolicy cooldownPolicy;
    private final ProactiveDecisionEngine engine;
    private volatile long lastEvaluateAt;
    private volatile ProactiveDecision lastEvaluated;
    private volatile ProactiveDecision lastVisible;
    private volatile ProactivityState state;

    /* JADX WARN: Multi-variable type inference failed */
    public ProactiveRuntime() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ProactiveRuntime(ProactiveDecisionEngine engine, CooldownPolicy cooldownPolicy) {
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(cooldownPolicy, "cooldownPolicy");
        this.engine = engine;
        this.cooldownPolicy = cooldownPolicy;
        this.state = ProactivityState.Companion.initial$default(ProactivityState.INSTANCE, null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ ProactiveRuntime(ProactiveDecisionEngine proactiveDecisionEngine, CooldownPolicy cooldownPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            proactiveDecisionEngine = new ProactiveDecisionEngine(null, null, null, null, 15, null);
        }
        this(proactiveDecisionEngine, (i & 2) != 0 ? new CooldownPolicy(null, 1, 0 == true ? 1 : 0) : cooldownPolicy);
    }

    /* JADX INFO: renamed from: state, reason: from getter */
    public final ProactivityState getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: lastVisibleDecision, reason: from getter */
    public final ProactiveDecision getLastVisible() {
        return this.lastVisible;
    }

    /* JADX INFO: renamed from: lastEvaluatedDecision, reason: from getter */
    public final ProactiveDecision getLastEvaluated() {
        return this.lastEvaluated;
    }

    public final void restore(ProactivityState saved) {
        Intrinsics.checkNotNullParameter(saved, "saved");
        this.state = saved;
        this.lastVisible = null;
        this.lastEvaluated = null;
        this.lastEvaluateAt = 0L;
    }

    public static /* synthetic */ DecisionEvaluationResult evaluate$default(ProactiveRuntime proactiveRuntime, ContextSnapshot contextSnapshot, List list, BehaviorFeedbackView behaviorFeedbackView, long j, long j2, boolean z, boolean z2, int i, Object obj) {
        long takenAtMillis = (i & 8) != 0 ? contextSnapshot.getTakenAtMillis() : j;
        long j3 = (i & 16) != 0 ? 2000L : j2;
        boolean z3 = (i & 32) != 0 ? false : z;
        return proactiveRuntime.evaluate(contextSnapshot, list, behaviorFeedbackView, takenAtMillis, j3, z3, (i & 64) != 0 ? z3 : z2);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00ba  */
    public final DecisionEvaluationResult evaluate(ContextSnapshot snapshot, List<BehaviorCandidate> candidates, BehaviorFeedbackView feedback, long nowMillis, long minIntervalMs, boolean force, boolean bypassCoalesce) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        long now = ProactivityState.INSTANCE.sanitizeNow(nowMillis);
        if (!bypassCoalesce && now - this.lastEvaluateAt < minIntervalMs) {
            ProactiveDecision coalesced = this.lastEvaluated;
            if (coalesced == null) {
                coalesced = new ProactiveDecision(ProactiveDecisionType.NO_OP, null, null, null, ProactiveReasonCode.ALREADY_HANDLED, "coalesced", 0.0f, 0.0f, now, null, null, null, 3790, null);
            }
            return new DecisionEvaluationResult(coalesced, this.state);
        }
        this.lastEvaluateAt = now;
        DecisionEvaluationResult result = this.engine.evaluateBest(candidates, snapshot, this.state, feedback, now);
        this.state = result.getNextState();
        ProactiveDecision decision = result.getDecision();
        this.lastEvaluated = decision;
        boolean z = true;
        if (decision.getType() != ProactiveDecisionType.NO_OP) {
            ProactiveDecision it = this.lastVisible;
            if (it != null) {
                z = it.getType() == decision.getType() && Intrinsics.areEqual(it.getBehaviorPatternKey(), decision.getBehaviorPatternKey()) && Intrinsics.areEqual(it.getFingerprint(), decision.getFingerprint());
            }
            boolean sameVisible = z;
            if (!sameVisible) {
                this.lastVisible = decision;
            }
        } else {
            ProactiveDecision proactiveDecision = this.lastVisible;
            String visibleKey = proactiveDecision != null ? proactiveDecision.getBehaviorPatternKey() : null;
            String str = visibleKey;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            if (!z && Intrinsics.areEqual(visibleKey, decision.getBehaviorPatternKey()) && (decision.getReasonCode() == ProactiveReasonCode.CONTEXT_ALREADY_SATISFIED || decision.getReasonCode() == ProactiveReasonCode.DISABLED || decision.getReasonCode() == ProactiveReasonCode.STALE)) {
                this.lastVisible = null;
            }
        }
        return new DecisionEvaluationResult(decision, this.state);
    }

    public static /* synthetic */ ProactivityState afterFeedback$default(ProactiveRuntime proactiveRuntime, String str, BehaviorFeedback behaviorFeedback, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = System.currentTimeMillis();
        }
        return proactiveRuntime.afterFeedback(str, behaviorFeedback, j);
    }

    public final ProactivityState afterFeedback(String patternKey, BehaviorFeedback feedback, long nowMillis) {
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        this.state = this.cooldownPolicy.afterFeedback(this.state, nowMillis, patternKey, feedback);
        ProactiveDecision proactiveDecision = this.lastVisible;
        if (Intrinsics.areEqual(proactiveDecision != null ? proactiveDecision.getBehaviorPatternKey() : null, patternKey)) {
            this.lastVisible = null;
        }
        return this.state;
    }

    public final void clearVisible() {
        this.lastVisible = null;
    }
}
