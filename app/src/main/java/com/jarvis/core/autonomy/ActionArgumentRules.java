package com.jarvis.core.autonomy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.tools.NotificationReplyTool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActionArgumentRules.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\u000bJ,\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/autonomy/ActionArgumentRules;", "", "<init>", "()V", "GLOBAL_FORBIDDEN_KEYS", "", "", "getGLOBAL_FORBIDDEN_KEYS", "()Ljava/util/Set;", "allowedKeysFor", "action", "Lcom/jarvis/core/autonomy/ProposedAction;", "requiredPermissionsFor", "validateArguments", "Lcom/jarvis/core/autonomy/ActionSafetyFailureReason;", "allowedKeys", "forbiddenKeys", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActionArgumentRules {
    public static final ActionArgumentRules INSTANCE = new ActionArgumentRules();
    private static final Set<String> GLOBAL_FORBIDDEN_KEYS = SetsKt.setOf((Object[]) new String[]{"password", "passwd", "secret", "token", "api_key", "apikey", "auth", "authorization", "cookie", PhoneStateReader.FAKE_SESSION, "private_key", "shell", "command", "exec", "script", "javascript", "raw_html", "intent_extra", "bundle", "parcelable", "uri_override", "accessibility_action", "gesture", "inject_event"});

    /* JADX INFO: compiled from: ActionArgumentRules.kt */
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
                iArr[ActionKind.MEDIA_CONTROL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ActionKind.LAUNCH_APP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ActionKind.SYSTEM_SETTINGS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ActionKind.COMMUNICATION.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ActionKind.UI_AUTOMATION.ordinal()] = 8;
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

    private ActionArgumentRules() {
    }

    public final Set<String> getGLOBAL_FORBIDDEN_KEYS() {
        return GLOBAL_FORBIDDEN_KEYS;
    }

    public final Set<String> allowedKeysFor(ProposedAction action) {
        String tool;
        Intrinsics.checkNotNullParameter(action, "action");
        String toolId = action.getToolId();
        if (toolId != null) {
            tool = toolId.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(tool, "toLowerCase(...)");
        } else {
            tool = null;
        }
        if (tool == null) {
            tool = "";
        }
        String id = action.getActionId().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(id, "toLowerCase(...)");
        if (action.getKind() == ActionKind.READ_ONLY || action.getKind() == ActionKind.NONE) {
            return SetsKt.setOf((Object[]) new String[]{SearchIntents.EXTRA_QUERY, "limit"});
        }
        if (Intrinsics.areEqual(tool, "flashlight") || Intrinsics.areEqual(id, "flashlight") || Intrinsics.areEqual(id, "torch")) {
            return SetsKt.setOf("action");
        }
        if (Intrinsics.areEqual(tool, "volume") || StringsKt.startsWith$default(id, "volume", false, 2, (Object) null)) {
            return SetsKt.setOf((Object[]) new String[]{"action", "level", "stream"});
        }
        if (Intrinsics.areEqual(tool, "brightness") || StringsKt.startsWith$default(id, "brightness", false, 2, (Object) null)) {
            return SetsKt.setOf((Object[]) new String[]{"action", "level"});
        }
        if (Intrinsics.areEqual(tool, "music") || action.getKind() == ActionKind.MEDIA_CONTROL) {
            return SetsKt.setOf((Object[]) new String[]{"action", SearchIntents.EXTRA_QUERY, "package"});
        }
        if (Intrinsics.areEqual(tool, "launcher") || action.getKind() == ActionKind.LAUNCH_APP) {
            return SetsKt.setOf((Object[]) new String[]{"package", "name", "action", "app"});
        }
        if (Intrinsics.areEqual(tool, "weather") || StringsKt.contains$default((CharSequence) id, (CharSequence) "weather", false, 2, (Object) null)) {
            return SetsKt.setOf((Object[]) new String[]{SearchIntents.EXTRA_QUERY, "city"});
        }
        if (Intrinsics.areEqual(tool, "knowledge")) {
            return SetsKt.setOf((Object[]) new String[]{SearchIntents.EXTRA_QUERY, "topic", "limit", "exclude_title", "title"});
        }
        if (Intrinsics.areEqual(tool, "timer")) {
            return SetsKt.setOf((Object[]) new String[]{"action", TypedValues.TransitionType.S_DURATION, "seconds", "label"});
        }
        if (Intrinsics.areEqual(tool, "calendar")) {
            return SetsKt.setOf((Object[]) new String[]{"action", SearchIntents.EXTRA_QUERY, "title", "when", TypedValues.TransitionType.S_DURATION});
        }
        if (Intrinsics.areEqual(tool, "clipboard")) {
            return SetsKt.setOf((Object[]) new String[]{"action", VoiceService.EXTRA_TEXT});
        }
        if (Intrinsics.areEqual(tool, "sms") || Intrinsics.areEqual(tool, "telegram.send") || Intrinsics.areEqual(tool, "phone.call")) {
            return SetsKt.setOf((Object[]) new String[]{"action", "contact", "number", VoiceService.EXTRA_TEXT, JarvisActivity.CHAT, "message", "history"});
        }
        if (Intrinsics.areEqual(tool, NotificationReplyTool.ID)) {
            return SetsKt.setOf((Object[]) new String[]{VoiceService.EXTRA_TEXT, SearchIntents.EXTRA_QUERY});
        }
        if (Intrinsics.areEqual(tool, "contacts.search") || Intrinsics.areEqual(tool, "contacts")) {
            return SetsKt.setOf((Object[]) new String[]{SearchIntents.EXTRA_QUERY, "name"});
        }
        if (Intrinsics.areEqual(tool, "files.search") || Intrinsics.areEqual(tool, "files")) {
            return SetsKt.setOf((Object[]) new String[]{"action", SearchIntents.EXTRA_QUERY, "path", "uri"});
        }
        if (Intrinsics.areEqual(tool, "system.setting")) {
            return SetsKt.setOf((Object[]) new String[]{"action", "key", "value", "setting"});
        }
        if (Intrinsics.areEqual(tool, "notifications.list") || Intrinsics.areEqual(tool, "notifications")) {
            return SetsKt.setOf((Object[]) new String[]{"action", "limit"});
        }
        if (Intrinsics.areEqual(tool, "screenshot")) {
            return SetsKt.setOf("action");
        }
        return StringsKt.startsWith$default(tool, "ui.", false, 2, (Object) null) ? SetsKt.setOf((Object[]) new String[]{"action", VoiceService.EXTRA_TEXT, "x", "y", "dx", "dy", "selector", "timeout", "direction", "count", "key", "value"}) : SetsKt.emptySet();
    }

    public final Set<String> requiredPermissionsFor(ProposedAction action) {
        String tool;
        Intrinsics.checkNotNullParameter(action, "action");
        String toolId = action.getToolId();
        if (toolId != null) {
            tool = toolId.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(tool, "toLowerCase(...)");
        } else {
            tool = null;
        }
        if (tool == null) {
            tool = "";
        }
        switch (WhenMappings.$EnumSwitchMapping$0[action.getKind().ordinal()]) {
            case 1:
            case 2:
            case 3:
                return SetsKt.emptySet();
            case 4:
                return SetsKt.setOf("capability:MEDIA_PLAYBACK");
            case 5:
                return SetsKt.setOf("capability:APP_LAUNCH");
            case 6:
                return SetsKt.setOf("android.permission.WRITE_SETTINGS");
            case 7:
                if (Intrinsics.areEqual(tool, "sms") || StringsKt.contains$default((CharSequence) action.getActionId(), (CharSequence) "sms", false, 2, (Object) null)) {
                    return SetsKt.setOf("android.permission.SEND_SMS");
                }
                return SetsKt.setOf("android.permission.CALL_PHONE");
            case 8:
                return SetsKt.setOf("capability:ACCESSIBILITY");
            case 9:
                return SetsKt.setOf("capability:DATA_DELETE");
            case 10:
                return SetsKt.setOf("capability:FINANCIAL");
            case 11:
                return SetsKt.setOf("capability:UNKNOWN_EXPLICIT");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final ActionSafetyFailureReason validateArguments(ProposedAction action, Set<String> allowedKeys, Set<String> forbiddenKeys) {
        String tool;
        boolean z;
        String level;
        boolean z2;
        Set<String> allowedKeys2 = allowedKeys;
        Set<String> forbiddenKeys2 = forbiddenKeys;
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(allowedKeys2, "allowedKeys");
        Intrinsics.checkNotNullParameter(forbiddenKeys2, "forbiddenKeys");
        for (String key : action.getArguments().keySet()) {
            String lk = key.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lk, "toLowerCase(...)");
            if (!forbiddenKeys2.contains(lk)) {
                Iterable $this$any$iv = GLOBAL_FORBIDDEN_KEYS;
                if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z2 = false;
                            break;
                        }
                        Object element$iv = it.next();
                        String it2 = (String) element$iv;
                        if (StringsKt.contains$default((CharSequence) lk, (CharSequence) it2, false, 2, (Object) null)) {
                            z2 = true;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                }
                if (!z2) {
                    if (!allowedKeys2.contains(key)) {
                        Iterable $this$map$iv = allowedKeys2;
                        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            String it3 = (String) item$iv$iv;
                            String lowerCase = it3.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                            destination$iv$iv.add(lowerCase);
                        }
                        if (!CollectionsKt.toSet((List) destination$iv$iv).contains(lk)) {
                            return ActionSafetyFailureReason.UNKNOWN_PARAMETER;
                        }
                    }
                    allowedKeys2 = allowedKeys;
                    forbiddenKeys2 = forbiddenKeys;
                }
            }
            return ActionSafetyFailureReason.FORBIDDEN_PARAMETER;
        }
        String toolId = action.getToolId();
        if (toolId != null) {
            tool = toolId.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(tool, "toLowerCase(...)");
        } else {
            tool = null;
        }
        if (tool == null) {
            tool = "";
        }
        String id = action.getActionId().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(id, "toLowerCase(...)");
        if (Intrinsics.areEqual(tool, "flashlight") || Intrinsics.areEqual(id, "flashlight") || Intrinsics.areEqual(id, "torch")) {
            String a = action.getArguments().get("action");
            if (a == null) {
                return ActionSafetyFailureReason.INVALID_ARGUMENTS;
            }
            if (!SetsKt.setOf((Object[]) new String[]{"on", "off", "toggle"}).contains(a)) {
                return ActionSafetyFailureReason.INVALID_ARGUMENTS;
            }
        }
        if (Intrinsics.areEqual(tool, "volume") || StringsKt.startsWith$default(id, "volume", false, 2, (Object) null)) {
            String a2 = action.getArguments().get("action");
            if (a2 != null) {
                z = true;
                if (!SetsKt.setOf((Object[]) new String[]{"up", "down", "set", "mute", "unmute"}).contains(a2)) {
                    return ActionSafetyFailureReason.INVALID_ARGUMENTS;
                }
            } else {
                z = true;
            }
            String level2 = action.getArguments().get("level");
            if (level2 != null) {
                Integer intOrNull = StringsKt.toIntOrNull(level2);
                if (intOrNull == null) {
                    return ActionSafetyFailureReason.INVALID_ARGUMENTS;
                }
                int n = intOrNull.intValue();
                if (!((n < 0 || n >= 101) ? false : z)) {
                    return ActionSafetyFailureReason.INVALID_ARGUMENTS;
                }
            }
        } else {
            z = true;
        }
        if ((Intrinsics.areEqual(tool, "brightness") || StringsKt.startsWith$default(id, "brightness", false, 2, (Object) null)) && (level = action.getArguments().get("level")) != null) {
            Integer intOrNull2 = StringsKt.toIntOrNull(level);
            if (intOrNull2 == null) {
                return ActionSafetyFailureReason.INVALID_ARGUMENTS;
            }
            int n2 = intOrNull2.intValue();
            if (!((n2 < 0 || n2 >= 256) ? false : z)) {
                return ActionSafetyFailureReason.INVALID_ARGUMENTS;
            }
        }
        return null;
    }
}
