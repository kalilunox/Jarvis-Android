package com.jarvis.core.autonomy;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionIntegrity.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\f"}, d2 = {"Lcom/jarvis/core/autonomy/ActionIntegrity;", "", "<init>", "()V", ProactiveActionReceiver.EXTRA_FINGERPRINT, "", "action", "Lcom/jarvis/core/autonomy/ProposedAction;", "assessment", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "binding", "Lcom/jarvis/core/autonomy/PolicyDecisionBinding;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActionIntegrity {
    public static final ActionIntegrity INSTANCE = new ActionIntegrity();

    private ActionIntegrity() {
    }

    public final String fingerprint(ProposedAction action, ActionRiskAssessment assessment) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(assessment, "assessment");
        Set setEntrySet = MapsKt.toSortedMap(action.getArguments()).entrySet();
        Intrinsics.checkNotNullExpressionValue(setEntrySet, "<get-entries>(...)");
        String args = CollectionsKt.joinToString$default(setEntrySet, ";", null, null, 0, null, new Function1() { // from class: com.jarvis.core.autonomy.ActionIntegrity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ActionIntegrity.fingerprint$lambda$0((Map.Entry) obj);
            }
        }, 30, null);
        String[] strArr = new String[9];
        strArr[0] = "v1";
        strArr[1] = action.getActionId();
        strArr[2] = action.getKind().name();
        String toolId = action.getToolId();
        if (toolId == null) {
            toolId = "";
        }
        strArr[3] = toolId;
        strArr[4] = args;
        strArr[5] = assessment.getActionId();
        strArr[6] = assessment.getRiskClass().name();
        strArr[7] = assessment.getKind().name();
        strArr[8] = assessment.getReasonCode().name();
        return CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) strArr), "|", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence fingerprint$lambda$0(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        String k = (String) entry.getKey();
        String v = (String) entry.getValue();
        return k + "=" + v;
    }

    public final PolicyDecisionBinding binding(ProposedAction action, ActionRiskAssessment assessment) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(assessment, "assessment");
        return new PolicyDecisionBinding(fingerprint(action, assessment), action, assessment);
    }
}
