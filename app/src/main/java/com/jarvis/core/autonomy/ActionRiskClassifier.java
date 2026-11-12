package com.jarvis.core.autonomy;

import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.tools.NotificationReplyTool;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActionRiskClassifier.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u001c\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/autonomy/ActionRiskClassifier;", "", "<init>", "()V", "classify", "Lcom/jarvis/core/autonomy/ActionRiskAssessment;", "action", "Lcom/jarvis/core/autonomy/ProposedAction;", "resolveKind", "Lcom/jarvis/core/autonomy/ActionKind;", "kindFromIds", "actionId", "", "toolId", "assess", "Lcom/jarvis/core/autonomy/ActionRiskClassifier$Assessed;", "kind", "Assessed", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActionRiskClassifier {
    private static final Set<String> NONE_IDS = SetsKt.setOf((Object[]) new String[]{"no_action", PhoneStateReader.FAKE_NONE, "noop", "silence"});
    private static final Set<String> READ_IDS = SetsKt.setOf((Object[]) new String[]{"answer", "tell", "weather_query", "knowledge_query"});
    private static final Set<String> READ_TOOLS = SetsKt.setOf((Object[]) new String[]{"weather", "knowledge", "contacts.search", "files.search"});
    private static final Set<String> FLASHLIGHT_IDS = SetsKt.setOf((Object[]) new String[]{"flashlight", "torch"});
    private static final Set<String> VOLUME_BRIGHTNESS_IDS = SetsKt.setOf((Object[]) new String[]{"volume_up", "volume_down", "volume_set", "brightness_up", "brightness_down", "brightness_set"});
    private static final Set<String> LAUNCH_IDS = SetsKt.setOf((Object[]) new String[]{"launch_app", "launch_music", "open_app", "launcher"});
    private static final Set<String> MEDIA_IDS = SetsKt.setOf((Object[]) new String[]{"music_play", "music_pause", "music_next", "media_play"});
    private static final Set<String> COMM_IDS = SetsKt.setOf((Object[]) new String[]{"sms_send", "send_message", "telegram_send", "call_dial"});
    private static final Set<String> COMM_TOOLS = SetsKt.setOf((Object[]) new String[]{"sms", "telegram.send", NotificationReplyTool.ID, "call", "phone.call"});
    private static final Set<String> SETTINGS_IDS = SetsKt.setOf((Object[]) new String[]{"system_setting", "toggle_setting", "wifi_toggle"});
    private static final Set<String> UI_IDS = SetsKt.setOf((Object[]) new String[]{"ui_click", "ui_type", "ui_swipe", "ui_global"});
    private static final Set<String> DELETE_IDS = SetsKt.setOf((Object[]) new String[]{"delete_data", "clear_memory", "wipe_cache", "delete_file"});
    private static final Set<String> FINANCIAL_IDS = SetsKt.setOf((Object[]) new String[]{"payment", "purchase", "pay", "checkout"});

    /* JADX INFO: compiled from: ActionRiskClassifier.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActionKind.values().length];
            try {
                iArr[ActionKind.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ActionKind.READ_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ActionKind.LOCAL_DEVICE_SAFE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ActionKind.LAUNCH_APP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ActionKind.MEDIA_CONTROL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ActionKind.SYSTEM_SETTINGS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ActionKind.UI_AUTOMATION.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ActionKind.COMMUNICATION.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ActionKind.DATA_DELETION.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ActionKind.FINANCIAL.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[ActionKind.UNKNOWN.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final ActionRiskAssessment classify(ProposedAction action) {
        Intrinsics.checkNotNullParameter(action, "action");
        ActionKind kind = resolveKind(action);
        Assessed assessedAssess = assess(kind, action.getActionId(), action.getToolId());
        ActionRiskClass risk = assessedAssess.getRisk();
        ActionRiskReasonCode reasonCode = assessedAssess.getReasonCode();
        String reason = assessedAssess.getReason();
        return new ActionRiskAssessment(risk, reasonCode, reason, assessedAssess.component4(), action.getActionId(), kind);
    }

    private final ActionKind resolveKind(ProposedAction action) {
        if (action.getKind() != ActionKind.UNKNOWN) {
            return action.getKind();
        }
        ActionKind actionKindKindFromIds = kindFromIds(action.getActionId(), action.getToolId());
        return actionKindKindFromIds == null ? ActionKind.UNKNOWN : actionKindKindFromIds;
    }

    private final ActionKind kindFromIds(String actionId, String toolId) {
        String lowerCase;
        String id = actionId.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(id, "toLowerCase(...)");
        if (toolId != null) {
            lowerCase = toolId.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        } else {
            lowerCase = null;
        }
        if (lowerCase == null) {
            lowerCase = "";
        }
        String tool = lowerCase;
        String token = id + "|" + tool;
        if (!NONE_IDS.contains(id) && !NONE_IDS.contains(tool)) {
            if (StringsKt.startsWith$default(id, "read_", false, 2, (Object) null) || READ_IDS.contains(id) || READ_TOOLS.contains(tool)) {
                return ActionKind.READ_ONLY;
            }
            if (FLASHLIGHT_IDS.contains(id) || Intrinsics.areEqual(tool, "flashlight")) {
                return ActionKind.LOCAL_DEVICE_SAFE;
            }
            if (VOLUME_BRIGHTNESS_IDS.contains(id) || SetsKt.setOf((Object[]) new String[]{"volume", "brightness"}).contains(tool)) {
                return ActionKind.LOCAL_DEVICE_SAFE;
            }
            if (LAUNCH_IDS.contains(id) || Intrinsics.areEqual(tool, "launcher")) {
                return ActionKind.LAUNCH_APP;
            }
            if (MEDIA_IDS.contains(id) || Intrinsics.areEqual(tool, "music")) {
                return ActionKind.MEDIA_CONTROL;
            }
            if (COMM_IDS.contains(id) || COMM_TOOLS.contains(tool)) {
                return ActionKind.COMMUNICATION;
            }
            if (SETTINGS_IDS.contains(id) || SetsKt.setOf((Object[]) new String[]{"settings", "system_setting", "system.setting"}).contains(tool)) {
                return ActionKind.SYSTEM_SETTINGS;
            }
            if (UI_IDS.contains(id) || StringsKt.startsWith$default(tool, "ui.", false, 2, (Object) null)) {
                return ActionKind.UI_AUTOMATION;
            }
            if (DELETE_IDS.contains(id) || StringsKt.contains$default((CharSequence) token, (CharSequence) "delete", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) token, (CharSequence) "clear_memory", false, 2, (Object) null)) {
                return ActionKind.DATA_DELETION;
            }
            if (!FINANCIAL_IDS.contains(id) && !StringsKt.contains$default((CharSequence) token, (CharSequence) "pay", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) token, (CharSequence) "purchase", false, 2, (Object) null)) {
                return null;
            }
            return ActionKind.FINANCIAL;
        }
        return ActionKind.NONE;
    }

    /* JADX INFO: compiled from: ActionRiskClassifier.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\tHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/autonomy/ActionRiskClassifier$Assessed;", "", "risk", "Lcom/jarvis/core/autonomy/ActionRiskClass;", "reasonCode", "Lcom/jarvis/core/autonomy/ActionRiskReasonCode;", "reason", "", "factors", "", "<init>", "(Lcom/jarvis/core/autonomy/ActionRiskClass;Lcom/jarvis/core/autonomy/ActionRiskReasonCode;Ljava/lang/String;Ljava/util/List;)V", "getRisk", "()Lcom/jarvis/core/autonomy/ActionRiskClass;", "getReasonCode", "()Lcom/jarvis/core/autonomy/ActionRiskReasonCode;", "getReason", "()Ljava/lang/String;", "getFactors", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Assessed {
        private final List<String> factors;
        private final String reason;
        private final ActionRiskReasonCode reasonCode;
        private final ActionRiskClass risk;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Assessed copy$default(Assessed assessed, ActionRiskClass actionRiskClass, ActionRiskReasonCode actionRiskReasonCode, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                actionRiskClass = assessed.risk;
            }
            if ((i & 2) != 0) {
                actionRiskReasonCode = assessed.reasonCode;
            }
            if ((i & 4) != 0) {
                str = assessed.reason;
            }
            if ((i & 8) != 0) {
                list = assessed.factors;
            }
            return assessed.copy(actionRiskClass, actionRiskReasonCode, str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ActionRiskClass getRisk() {
            return this.risk;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ActionRiskReasonCode getReasonCode() {
            return this.reasonCode;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final List<String> component4() {
            return this.factors;
        }

        public final Assessed copy(ActionRiskClass risk, ActionRiskReasonCode reasonCode, String reason, List<String> factors) {
            Intrinsics.checkNotNullParameter(risk, "risk");
            Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(factors, "factors");
            return new Assessed(risk, reasonCode, reason, factors);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Assessed)) {
                return false;
            }
            Assessed assessed = (Assessed) other;
            return this.risk == assessed.risk && this.reasonCode == assessed.reasonCode && Intrinsics.areEqual(this.reason, assessed.reason) && Intrinsics.areEqual(this.factors, assessed.factors);
        }

        public int hashCode() {
            return (((((this.risk.hashCode() * 31) + this.reasonCode.hashCode()) * 31) + this.reason.hashCode()) * 31) + this.factors.hashCode();
        }

        public String toString() {
            return "Assessed(risk=" + this.risk + ", reasonCode=" + this.reasonCode + ", reason=" + this.reason + ", factors=" + this.factors + ")";
        }

        public Assessed(ActionRiskClass risk, ActionRiskReasonCode reasonCode, String reason, List<String> factors) {
            Intrinsics.checkNotNullParameter(risk, "risk");
            Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(factors, "factors");
            this.risk = risk;
            this.reasonCode = reasonCode;
            this.reason = reason;
            this.factors = factors;
        }

        public final ActionRiskClass getRisk() {
            return this.risk;
        }

        public final ActionRiskReasonCode getReasonCode() {
            return this.reasonCode;
        }

        public final String getReason() {
            return this.reason;
        }

        public final List<String> getFactors() {
            return this.factors;
        }
    }

    private final Assessed assess(ActionKind kind, String actionId, String toolId) {
        switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
            case 1:
                return new Assessed(ActionRiskClass.NONE, ActionRiskReasonCode.NO_SIDE_EFFECT, "No state change", CollectionsKt.listOf((Object[]) new String[]{"kind=NONE", "id=" + actionId}));
            case 2:
                return new Assessed(ActionRiskClass.NONE, ActionRiskReasonCode.READ_ONLY, "Read-only / no device mutation", CollectionsKt.listOf((Object[]) new String[]{"kind=READ_ONLY", "id=" + actionId}));
            case 3:
                ActionRiskClass actionRiskClass = ActionRiskClass.LOW;
                ActionRiskReasonCode actionRiskReasonCode = ActionRiskReasonCode.LOCAL_REVERSIBLE;
                String[] strArr = new String[3];
                strArr[0] = "kind=LOCAL_DEVICE_SAFE";
                strArr[1] = "id=" + actionId;
                strArr[2] = "tool=" + (toolId != null ? toolId : "-");
                return new Assessed(actionRiskClass, actionRiskReasonCode, "Local reversible device effect", CollectionsKt.listOf((Object[]) strArr));
            case 4:
                return new Assessed(ActionRiskClass.MEDIUM, ActionRiskReasonCode.USER_VISIBLE_EFFECT, "Launches an application (user-visible)", CollectionsKt.listOf((Object[]) new String[]{"kind=LAUNCH_APP", "id=" + actionId}));
            case 5:
                return new Assessed(ActionRiskClass.MEDIUM, ActionRiskReasonCode.USER_VISIBLE_EFFECT, "Media / playback control", CollectionsKt.listOf((Object[]) new String[]{"kind=MEDIA_CONTROL", "id=" + actionId}));
            case 6:
                return new Assessed(ActionRiskClass.MEDIUM, ActionRiskReasonCode.DIRECT_STATE_CHANGE, "Noticeable system state change", CollectionsKt.listOf((Object[]) new String[]{"kind=SYSTEM_SETTINGS", "id=" + actionId}));
            case 7:
                return new Assessed(ActionRiskClass.HIGH, ActionRiskReasonCode.DIRECT_STATE_CHANGE, "UI / accessibility automation can drive arbitrary UI", CollectionsKt.listOf((Object[]) new String[]{"kind=UI_AUTOMATION", "id=" + actionId}));
            case 8:
                return new Assessed(ActionRiskClass.HIGH, ActionRiskReasonCode.EXTERNAL_SIDE_EFFECT, "External communication side effect", CollectionsKt.listOf((Object[]) new String[]{"kind=COMMUNICATION", "id=" + actionId}));
            case 9:
                return new Assessed(ActionRiskClass.HIGH, ActionRiskReasonCode.DATA_DELETION, "Data deletion / destructive local mutation", CollectionsKt.listOf((Object[]) new String[]{"kind=DATA_DELETION", "id=" + actionId}));
            case 10:
                return new Assessed(ActionRiskClass.CRITICAL, ActionRiskReasonCode.FINANCIAL_EFFECT, "Financial or purchase-related action", CollectionsKt.listOf((Object[]) new String[]{"kind=FINANCIAL", "id=" + actionId}));
            case 11:
                ActionRiskClass actionRiskClass2 = ActionRiskClass.CRITICAL;
                ActionRiskReasonCode actionRiskReasonCode2 = ActionRiskReasonCode.UNKNOWN_ACTION;
                String[] strArr2 = new String[3];
                strArr2[0] = "kind=UNKNOWN";
                strArr2[1] = "id=" + actionId;
                strArr2[2] = "tool=" + (toolId != null ? toolId : "-");
                return new Assessed(actionRiskClass2, actionRiskReasonCode2, "Unknown action — classified CRITICAL (conservative; not permission)", CollectionsKt.listOf((Object[]) strArr2));
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
