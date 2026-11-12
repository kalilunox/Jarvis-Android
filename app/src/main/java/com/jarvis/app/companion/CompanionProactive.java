package com.jarvis.app.companion;

import android.content.Context;
import android.util.Log;
import com.jarvis.app.BuildConfig;
import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.autonomy.ActionArgumentRules;
import com.jarvis.core.autonomy.ActionIntegrity;
import com.jarvis.core.autonomy.ActionRiskAssessment;
import com.jarvis.core.autonomy.ActionRiskClassifier;
import com.jarvis.core.autonomy.ActionSafetyGate;
import com.jarvis.core.autonomy.ActionSafetyGateInput;
import com.jarvis.core.autonomy.ActionSafetyGateResult;
import com.jarvis.core.autonomy.AutonomyActionPipeline;
import com.jarvis.core.autonomy.AutonomyActionPlan;
import com.jarvis.core.autonomy.AutonomyAuditLog;
import com.jarvis.core.autonomy.AutonomyLevel;
import com.jarvis.core.autonomy.AutonomyPolicy;
import com.jarvis.core.autonomy.AutonomyPolicyInput;
import com.jarvis.core.autonomy.AutonomyVerdict;
import com.jarvis.core.autonomy.ContextFreshness;
import com.jarvis.core.autonomy.DecisionValidity;
import com.jarvis.core.autonomy.DefaultActionSafetyGate;
import com.jarvis.core.autonomy.DefaultAutonomyPolicy;
import com.jarvis.core.autonomy.PolicyDecisionBinding;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.autonomy.ProposedActionMapper;
import com.jarvis.core.autonomy.SafeAutonomousExecutor;
import com.jarvis.core.autonomy.SafeAutonomousResult;
import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.behavior.BehaviorFeedback;
import com.jarvis.core.behavior.BehaviorLearningService;
import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.companion.ContextSnapshot;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.memory.CompanionMemoryService;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.proactive.BehaviorFeedbackView;
import com.jarvis.core.proactive.DecisionEvaluationResult;
import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import com.jarvis.core.proactive.ProactiveRuntime;
import com.jarvis.core.proactive.ProactivitySnapshotCodec;
import com.jarvis.core.proactive.ProactivityState;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CompanionProactive.kt */
/* JADX INFO: loaded from: classes18.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007J\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000bJ\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u000fJ\\\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0002\u0010%\u001a\u00020&2\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020#0\"J2\u0010(\u001a\u00020)2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020#0\"J \u0010*\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 J\u0010\u0010,\u001a\u0004\u0018\u00010\u001e2\u0006\u0010-\u001a\u00020#J\u000e\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u000200J4\u00101\u001a\u0002022\u0006\u0010/\u001a\u0002002\u0006\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u00020\u00172\b\b\u0002\u00108\u001a\u00020\u0017J\u0010\u00109\u001a\u0004\u0018\u00010\u001c2\u0006\u0010/\u001a\u000200J\u0010\u0010:\u001a\u0004\u0018\u00010\u001c2\u0006\u0010/\u001a\u000200J6\u0010;\u001a\u00020\u000f2\u0006\u0010/\u001a\u0002002\u0006\u00103\u001a\u0002042\u0006\u0010<\u001a\u00020=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010?2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010#J\u0018\u0010@\u001a\u00020\u000f2\u0006\u0010/\u001a\u0002002\b\b\u0002\u0010A\u001a\u00020BJ\u0006\u0010C\u001a\u00020\u000fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/jarvis/app/companion/CompanionProactive;", "", "<init>", "()V", "runtime", "Lcom/jarvis/core/proactive/ProactiveRuntime;", "autonomyPolicy", "Lcom/jarvis/core/autonomy/AutonomyPolicy;", "riskClassifier", "Lcom/jarvis/core/autonomy/ActionRiskClassifier;", "safetyGate", "Lcom/jarvis/core/autonomy/ActionSafetyGate;", "safeExecutor", "Lcom/jarvis/core/autonomy/SafeAutonomousExecutor;", "setAutonomyPolicyForTests", "", "policy", "setSafetyGateForTests", "gate", "attachSafeExecutor", "toolExecutor", "Lcom/jarvis/core/common/ToolExecutor;", JarvisActivity.LIVE, "", "clearSafeExecutorForTests", "evaluateAutonomy", "Lcom/jarvis/core/autonomy/AutonomyVerdict;", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", "proposed", "Lcom/jarvis/core/autonomy/ProposedAction;", "level", "Lcom/jarvis/core/autonomy/AutonomyLevel;", "allowlist", "", "", "blocklist", "nowMillis", "", "grantedPermissions", "planAutonomy", "Lcom/jarvis/core/autonomy/AutonomyActionPlan;", "trySafeExecute", "Lcom/jarvis/core/autonomy/SafeAutonomousResult;", "proposeFromPattern", ProactiveActionReceiver.EXTRA_PATTERN_KEY, "get", "context", "Landroid/content/Context;", "tick", "Lcom/jarvis/core/proactive/DecisionEvaluationResult;", "behavior", "Lcom/jarvis/core/behavior/BehaviorLearningService;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "force", "bypassCoalesce", "visibleDecision", "lastEvaluatedDecision", "applyFeedback", "feedback", "Lcom/jarvis/core/behavior/BehaviorFeedback;", JarvisActivity.MEMORY, "Lcom/jarvis/core/memory/CompanionMemoryService;", "persist", "state", "Lcom/jarvis/core/proactive/ProactivityState;", "resetForTests", "PREFS", "KEY_STATE", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CompanionProactive {
    private static final String KEY_STATE = "proactivity_state_v1";
    private static final String PREFS = "jarvis_proactivity_v1";
    private static volatile ProactiveRuntime runtime;
    private static volatile SafeAutonomousExecutor safeExecutor;
    public static final CompanionProactive INSTANCE = new CompanionProactive();
    private static volatile AutonomyPolicy autonomyPolicy = new DefaultAutonomyPolicy();
    private static volatile ActionRiskClassifier riskClassifier = new ActionRiskClassifier();
    private static volatile ActionSafetyGate safetyGate = new DefaultActionSafetyGate(0, 1, null);

    private CompanionProactive() {
    }

    public final AutonomyPolicy autonomyPolicy() {
        return autonomyPolicy;
    }

    public final ActionSafetyGate safetyGate() {
        return safetyGate;
    }

    public final void setAutonomyPolicyForTests(AutonomyPolicy policy) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        autonomyPolicy = policy;
    }

    public final void setSafetyGateForTests(ActionSafetyGate gate) {
        Intrinsics.checkNotNullParameter(gate, "gate");
        safetyGate = gate;
    }

    public static /* synthetic */ void attachSafeExecutor$default(CompanionProactive companionProactive, ToolExecutor toolExecutor, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        companionProactive.attachSafeExecutor(toolExecutor, z);
    }

    public final void attachSafeExecutor(ToolExecutor toolExecutor, boolean live) {
        Intrinsics.checkNotNullParameter(toolExecutor, "toolExecutor");
        DefaultActionSafetyGate gate = new DefaultActionSafetyGate(0L, 1, null);
        safetyGate = gate;
        safeExecutor = new SafeAutonomousExecutor(toolExecutor, new AutonomyActionPipeline(autonomyPolicy, gate, riskClassifier));
    }

    public final void clearSafeExecutorForTests() {
        safeExecutor = null;
        safetyGate = new DefaultActionSafetyGate(0L, 1, null);
    }

    public final AutonomyVerdict evaluateAutonomy(ProactiveDecision decision, ProposedAction proposed, AutonomyLevel level, Set<String> allowlist, Set<String> blocklist, long nowMillis, Set<String> grantedPermissions) {
        PolicyDecisionBinding binding;
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(allowlist, "allowlist");
        Intrinsics.checkNotNullParameter(blocklist, "blocklist");
        Intrinsics.checkNotNullParameter(grantedPermissions, "grantedPermissions");
        Set<String> setRequiredPermissionsFor = null;
        ActionRiskAssessment assessment = proposed != null ? riskClassifier.classify(proposed) : null;
        AutonomyVerdict policyVerdict = autonomyPolicy.evaluate(new AutonomyPolicyInput(decision, proposed, level, assessment, null, allowlist, blocklist, nowMillis, 16, null));
        AutonomyAuditLog.INSTANCE.record(policyVerdict, "policy");
        if (proposed != null && assessment != null) {
            binding = ActionIntegrity.INSTANCE.binding(proposed, assessment);
        } else {
            binding = null;
        }
        if (proposed != null) {
            setRequiredPermissionsFor = ActionArgumentRules.INSTANCE.requiredPermissionsFor(proposed);
        }
        ActionSafetyGateResult gateResult = safetyGate.evaluate(new ActionSafetyGateInput(policyVerdict, assessment, nowMillis, setRequiredPermissionsFor == null ? SetsKt.emptySet() : setRequiredPermissionsFor, grantedPermissions, new ContextFreshness(nowMillis, 0L, 2, null), new DecisionValidity(nowMillis, 0L, 2, null), null, binding, proposed, null, null, 3200, null));
        AutonomyAuditLog.INSTANCE.record(gateResult.getVerdict(), "gate");
        return gateResult.getVerdict();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AutonomyActionPlan planAutonomy$default(CompanionProactive companionProactive, ProactiveDecision proactiveDecision, ProposedAction proposedAction, AutonomyLevel autonomyLevel, Set set, int i, Object obj) {
        if ((i & 4) != 0) {
            autonomyLevel = AutonomyLevel.SAFE_ONLY;
        }
        if ((i & 8) != 0) {
            set = SetsKt.emptySet();
        }
        return companionProactive.planAutonomy(proactiveDecision, proposedAction, autonomyLevel, set);
    }

    public final AutonomyActionPlan planAutonomy(ProactiveDecision decision, ProposedAction proposed, AutonomyLevel level, Set<String> grantedPermissions) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(grantedPermissions, "grantedPermissions");
        return AutonomyActionPipeline.plan$default(new AutonomyActionPipeline(autonomyPolicy, safetyGate, riskClassifier), decision, proposed, level, null, null, 0L, null, grantedPermissions, null, 0L, null, null, null, 8056, null);
    }

    public static /* synthetic */ SafeAutonomousResult trySafeExecute$default(CompanionProactive companionProactive, ProactiveDecision proactiveDecision, ProposedAction proposedAction, AutonomyLevel autonomyLevel, int i, Object obj) {
        if ((i & 4) != 0) {
            autonomyLevel = AutonomyLevel.SAFE_ONLY;
        }
        return companionProactive.trySafeExecute(proactiveDecision, proposedAction, autonomyLevel);
    }

    public final SafeAutonomousResult trySafeExecute(ProactiveDecision decision, ProposedAction proposed, AutonomyLevel level) {
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(proposed, "proposed");
        Intrinsics.checkNotNullParameter(level, "level");
        SafeAutonomousExecutor exec = safeExecutor;
        if (exec != null) {
            return exec.tryExecute(decision, proposed, (56 & 4) != 0 ? AutonomyLevel.SAFE_ONLY : level, (56 & 8) != 0 ? SetsKt.emptySet() : null, (56 & 16) != 0 ? SetsKt.emptySet() : null, (56 & 32) != 0 ? System.currentTimeMillis() : 0L);
        }
        return new SafeAutonomousResult(false, null, null, "SafeAutonomousExecutor not attached", null, 16, null);
    }

    public final ProposedAction proposeFromPattern(String patternKey) {
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        return ProposedActionMapper.INSTANCE.fromBehaviorPatternKey(patternKey);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ProactiveRuntime get(Context context) {
        ProactiveRuntime proactiveRuntime;
        Intrinsics.checkNotNullParameter(context, "context");
        ProactiveRuntime proactiveRuntime2 = runtime;
        if (proactiveRuntime2 != null) {
            return proactiveRuntime2;
        }
        synchronized (this) {
            proactiveRuntime = runtime;
            if (proactiveRuntime == null) {
                CompanionProactive companionProactive = INSTANCE;
                Context applicationContext = context.getApplicationContext();
                ProactiveRuntime proactiveRuntime3 = new ProactiveRuntime(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
                String string = applicationContext.getSharedPreferences(PREFS, 0).getString(KEY_STATE, null);
                proactiveRuntime3.restore(ProactivitySnapshotCodec.INSTANCE.decode(string == null ? "" : string));
                runtime = proactiveRuntime3;
                proactiveRuntime = proactiveRuntime3;
            }
        }
        return proactiveRuntime;
    }

    public static /* synthetic */ DecisionEvaluationResult tick$default(CompanionProactive companionProactive, Context context, BehaviorLearningService behaviorLearningService, ContextSnapshot contextSnapshot, boolean z, boolean z2, int i, Object obj) {
        ContextSnapshot contextSnapshotSnapshot = (i & 4) != 0 ? PhoneContextBus.INSTANCE.snapshot() : contextSnapshot;
        boolean z3 = (i & 8) != 0 ? false : z;
        return companionProactive.tick(context, behaviorLearningService, contextSnapshotSnapshot, z3, (i & 16) != 0 ? z3 : z2);
    }

    public final DecisionEvaluationResult tick(Context context, BehaviorLearningService behavior, ContextSnapshot snapshot, boolean force, boolean bypassCoalesce) {
        String fgAppSafe;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        ProactiveRuntime rt = get(context);
        long now = snapshot.getTakenAtMillis();
        List<BehaviorCandidate> listActiveCandidates = behavior.getStore().activeCandidates(now);
        BehaviorFeedbackView feedback = BehaviorFeedbackView.INSTANCE.from(behavior.getStore());
        DecisionEvaluationResult result = ProactiveRuntime.evaluate$default(rt, snapshot, listActiveCandidates, feedback, now, 0L, false, bypassCoalesce, 48, null);
        if (BuildConfig.DEBUG) {
            String fgApp = snapshot.getContext().getCurrentApp();
            String str = fgApp;
            if (str == null || StringsKt.isBlank(str)) {
                fgAppSafe = "—";
            } else {
                fgAppSafe = SensitivityClassifier.INSTANCE.isSensitivePackage(fgApp) ? "[скрыто]" : fgApp;
            }
            Log.i("JarvisProactive", "tick type=" + result.getDecision().getType() + " reason=" + result.getDecision().getReasonCode() + " key=" + result.getDecision().getBehaviorPatternKey() + " hp=" + snapshot.getContext().getHeadphonesConnected() + " app=" + fgAppSafe + " cands=" + listActiveCandidates.size() + " bypass=" + bypassCoalesce);
        }
        persist(context, rt.getState());
        return result;
    }

    public final ProactiveDecision visibleDecision(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProactiveDecision d = get(context).getLastVisible();
        if (d == null || d.getType() == ProactiveDecisionType.NO_OP) {
            return null;
        }
        return d;
    }

    public final ProactiveDecision lastEvaluatedDecision(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return get(context).getLastEvaluated();
    }

    public static /* synthetic */ void applyFeedback$default(CompanionProactive companionProactive, Context context, BehaviorLearningService behaviorLearningService, BehaviorFeedback behaviorFeedback, CompanionMemoryService companionMemoryService, String str, int i, Object obj) {
        CompanionMemoryService companionMemoryService2;
        String str2;
        if ((i & 8) == 0) {
            companionMemoryService2 = companionMemoryService;
        } else {
            companionMemoryService2 = null;
        }
        if ((i & 16) == 0) {
            str2 = str;
        } else {
            str2 = null;
        }
        companionProactive.applyFeedback(context, behaviorLearningService, behaviorFeedback, companionMemoryService2, str2);
    }

    public final void applyFeedback(Context context, BehaviorLearningService behavior, BehaviorFeedback feedback, CompanionMemoryService memory, String patternKey) {
        String behaviorPatternKey;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        ProactiveRuntime rt = get(context);
        if (patternKey != null) {
            behaviorPatternKey = patternKey;
        } else {
            ProactiveDecision lastVisible = rt.getLastVisible();
            behaviorPatternKey = lastVisible != null ? lastVisible.getBehaviorPatternKey() : null;
            if (behaviorPatternKey == null) {
                return;
            }
        }
        String key = behaviorPatternKey;
        behavior.feedback(key, feedback, memory);
        CompanionBehavior.INSTANCE.persistFeedback(context, behavior);
        ProactiveRuntime.afterFeedback$default(rt, key, feedback, 0L, 4, null);
        persist(context, rt.getState());
    }

    public static /* synthetic */ void persist$default(CompanionProactive companionProactive, Context context, ProactivityState proactivityState, int i, Object obj) {
        if ((i & 2) != 0) {
            proactivityState = companionProactive.get(context).getState();
        }
        companionProactive.persist(context, proactivityState);
    }

    public final void persist(Context context, ProactivityState state) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(state, "state");
        context.getApplicationContext().getSharedPreferences(PREFS, 0).edit().putString(KEY_STATE, ProactivitySnapshotCodec.INSTANCE.encode(state)).apply();
    }

    public final void resetForTests() {
        synchronized (this) {
            runtime = null;
            Unit unit = Unit.INSTANCE;
        }
    }
}
