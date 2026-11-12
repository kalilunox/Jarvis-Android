package com.jarvis.core.runtime;

import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.autonomy.ProposedActionMapper;
import com.jarvis.core.common.PolicyDecision;
import com.jarvis.core.common.RiskBasedPolicy;
import com.jarvis.core.common.ToolPolicy;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LiveSkillRiskBridge.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/runtime/LiveSkillRiskBridge;", "", "legacyPolicy", "Lcom/jarvis/core/common/ToolPolicy;", "<init>", "(Lcom/jarvis/core/common/ToolPolicy;)V", "bridge", "Lcom/jarvis/core/runtime/LiveSkillRiskBridge$BridgedSkill;", "toolCall", "Lcom/jarvis/core/common/model/ToolCall;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "kindForRiskLevel", "Lcom/jarvis/core/autonomy/ActionKind;", "risk", "Lcom/jarvis/core/common/model/RiskLevel;", "toolId", "", "mappedKind", "BridgedSkill", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LiveSkillRiskBridge {
    private final ToolPolicy legacyPolicy;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> READ_TOOL_IDS = SetsKt.setOf((Object[]) new String[]{"weather", "knowledge", "clipboard", "notifications.list", "contacts.search", "files.search", "calendar"});
    private static final Set<String> READ_TOOLS = READ_TOOL_IDS;

    /* JADX INFO: compiled from: LiveSkillRiskBridge.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RiskLevel.values().length];
            try {
                iArr[RiskLevel.SENSITIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RiskLevel.CONFIRM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RiskLevel.SAFE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[RiskLevel.LOW.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[RiskLevel.BLOCKED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LiveSkillRiskBridge() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public LiveSkillRiskBridge(ToolPolicy legacyPolicy) {
        Intrinsics.checkNotNullParameter(legacyPolicy, "legacyPolicy");
        this.legacyPolicy = legacyPolicy;
    }

    public /* synthetic */ LiveSkillRiskBridge(RiskBasedPolicy riskBasedPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new RiskBasedPolicy() : riskBasedPolicy);
    }

    /* JADX INFO: compiled from: LiveSkillRiskBridge.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/runtime/LiveSkillRiskBridge$BridgedSkill;", "", "legacy", "Lcom/jarvis/core/common/PolicyDecision;", "proposed", "Lcom/jarvis/core/autonomy/ProposedAction;", "allowlist", "", "", "<init>", "(Lcom/jarvis/core/common/PolicyDecision;Lcom/jarvis/core/autonomy/ProposedAction;Ljava/util/Set;)V", "getLegacy", "()Lcom/jarvis/core/common/PolicyDecision;", "getProposed", "()Lcom/jarvis/core/autonomy/ProposedAction;", "getAllowlist", "()Ljava/util/Set;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BridgedSkill {
        private final Set<String> allowlist;
        private final PolicyDecision legacy;
        private final ProposedAction proposed;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BridgedSkill copy$default(BridgedSkill bridgedSkill, PolicyDecision policyDecision, ProposedAction proposedAction, Set set, int i, Object obj) {
            if ((i & 1) != 0) {
                policyDecision = bridgedSkill.legacy;
            }
            if ((i & 2) != 0) {
                proposedAction = bridgedSkill.proposed;
            }
            if ((i & 4) != 0) {
                set = bridgedSkill.allowlist;
            }
            return bridgedSkill.copy(policyDecision, proposedAction, set);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final PolicyDecision getLegacy() {
            return this.legacy;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ProposedAction getProposed() {
            return this.proposed;
        }

        public final Set<String> component3() {
            return this.allowlist;
        }

        public final BridgedSkill copy(PolicyDecision legacy, ProposedAction proposed, Set<String> allowlist) {
            Intrinsics.checkNotNullParameter(legacy, "legacy");
            Intrinsics.checkNotNullParameter(proposed, "proposed");
            Intrinsics.checkNotNullParameter(allowlist, "allowlist");
            return new BridgedSkill(legacy, proposed, allowlist);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BridgedSkill)) {
                return false;
            }
            BridgedSkill bridgedSkill = (BridgedSkill) other;
            return Intrinsics.areEqual(this.legacy, bridgedSkill.legacy) && Intrinsics.areEqual(this.proposed, bridgedSkill.proposed) && Intrinsics.areEqual(this.allowlist, bridgedSkill.allowlist);
        }

        public int hashCode() {
            return (((this.legacy.hashCode() * 31) + this.proposed.hashCode()) * 31) + this.allowlist.hashCode();
        }

        public String toString() {
            return "BridgedSkill(legacy=" + this.legacy + ", proposed=" + this.proposed + ", allowlist=" + this.allowlist + ")";
        }

        public BridgedSkill(PolicyDecision legacy, ProposedAction proposed, Set<String> allowlist) {
            Intrinsics.checkNotNullParameter(legacy, "legacy");
            Intrinsics.checkNotNullParameter(proposed, "proposed");
            Intrinsics.checkNotNullParameter(allowlist, "allowlist");
            this.legacy = legacy;
            this.proposed = proposed;
            this.allowlist = allowlist;
        }

        public /* synthetic */ BridgedSkill(PolicyDecision policyDecision, ProposedAction proposedAction, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(policyDecision, proposedAction, (i & 4) != 0 ? SetsKt.emptySet() : set);
        }

        public final PolicyDecision getLegacy() {
            return this.legacy;
        }

        public final ProposedAction getProposed() {
            return this.proposed;
        }

        public final Set<String> getAllowlist() {
            return this.allowlist;
        }
    }

    public final BridgedSkill bridge(ToolCall toolCall, ToolDefinition definition) {
        Set allowlist;
        Intrinsics.checkNotNullParameter(toolCall, "toolCall");
        Intrinsics.checkNotNullParameter(definition, "definition");
        PolicyDecision legacy = this.legacyPolicy.decide(definition);
        Map<String, String> mapStringifyArgs = INSTANCE.stringifyArgs(toolCall.getArguments());
        ProposedAction mapped = ProposedActionMapper.INSTANCE.fromToolId(toolCall.getToolId(), toolCall.getToolId(), mapStringifyArgs, definition.getName());
        ActionKind kind = kindForRiskLevel(definition.getRiskLevel(), toolCall.getToolId(), mapped != null ? mapped.getKind() : null);
        ProposedAction proposed = new ProposedAction(toolCall.getToolId(), kind, StringsKt.take(definition.getName(), 160), toolCall.getToolId(), mapStringifyArgs);
        switch (WhenMappings.$EnumSwitchMapping$0[definition.getRiskLevel().ordinal()]) {
            case 1:
            case 2:
                allowlist = SetsKt.setOf(proposed.getActionId());
                break;
            default:
                allowlist = SetsKt.emptySet();
                break;
        }
        return new BridgedSkill(legacy, proposed, allowlist);
    }

    public final ActionKind kindForRiskLevel(RiskLevel risk, String toolId, ActionKind mappedKind) {
        Intrinsics.checkNotNullParameter(risk, "risk");
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        switch (WhenMappings.$EnumSwitchMapping$0[risk.ordinal()]) {
            case 1:
                return ActionKind.COMMUNICATION;
            case 2:
                return ActionKind.MEDIA_CONTROL;
            case 3:
            case 4:
                if (READ_TOOLS.contains(toolId) || mappedKind == ActionKind.READ_ONLY) {
                    return ActionKind.READ_ONLY;
                }
                return ActionKind.LOCAL_DEVICE_SAFE;
            case 5:
                return ActionKind.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: compiled from: LiveSkillRiskBridge.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/jarvis/core/runtime/LiveSkillRiskBridge$Companion;", "", "<init>", "()V", "READ_TOOL_IDS", "", "", "getREAD_TOOL_IDS", "()Ljava/util/Set;", "READ_TOOLS", "stringifyArgs", "", "raw", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getREAD_TOOL_IDS() {
            return LiveSkillRiskBridge.READ_TOOL_IDS;
        }

        public final Map<String, String> stringifyArgs(Map<String, ? extends Object> raw) {
            Intrinsics.checkNotNullParameter(raw, "raw");
            LinkedHashMap out = new LinkedHashMap();
            for (Map.Entry<String, ? extends Object> entry : raw.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                if (out.size() >= 12) {
                    break;
                }
                if (k.length() <= 48) {
                    out.put(k, StringsKt.take(v.toString(), 96));
                }
            }
            return out;
        }
    }
}
