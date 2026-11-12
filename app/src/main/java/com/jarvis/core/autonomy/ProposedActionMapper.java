package com.jarvis.core.autonomy;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.tools.NotificationReplyTool;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProposedActionMapper.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007J&\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u00072\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0007R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/autonomy/ProposedActionMapper;", "", "<init>", "()V", "fromToolId", "Lcom/jarvis/core/autonomy/ProposedAction;", "toolId", "", "actionId", "args", "", "displaySummary", "fromCapabilityId", "capabilityId", "fromBehaviorPatternKey", ProactiveActionReceiver.EXTRA_PATTERN_KEY, "FIRST_SAFE_TOOL_IDS", "", "getFIRST_SAFE_TOOL_IDS", "()Ljava/util/Set;", "TOOL_BY_CAPABILITY", "KIND_BY_TOOL", "Lcom/jarvis/core/autonomy/ActionKind;", "SUMMARY_BY_TOOL", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProposedActionMapper {
    public static final ProposedActionMapper INSTANCE = new ProposedActionMapper();
    private static final Set<String> FIRST_SAFE_TOOL_IDS = SetsKt.setOf((Object[]) new String[]{"flashlight", "volume", "brightness"});
    private static final Map<String, String> TOOL_BY_CAPABILITY = MapsKt.mapOf(TuplesKt.to("DEVICE_LIGHT", "flashlight"), TuplesKt.to("DEVICE_AUDIO", "volume"), TuplesKt.to("DEVICE_DISPLAY", "brightness"), TuplesKt.to("MEDIA_PLAYBACK", "music"), TuplesKt.to("APP_LAUNCH", "launcher"), TuplesKt.to("SYSTEM_CONFIGURATION", "system.setting"), TuplesKt.to("COMMUNICATION_CALL", "phone.call"), TuplesKt.to("COMMUNICATION_SMS", "sms"), TuplesKt.to("KNOWLEDGE_LOOKUP", "knowledge"));
    private static final Map<String, ActionKind> KIND_BY_TOOL = MapsKt.mapOf(TuplesKt.to("flashlight", ActionKind.LOCAL_DEVICE_SAFE), TuplesKt.to("volume", ActionKind.LOCAL_DEVICE_SAFE), TuplesKt.to("brightness", ActionKind.LOCAL_DEVICE_SAFE), TuplesKt.to("timer", ActionKind.LOCAL_DEVICE_SAFE), TuplesKt.to("music", ActionKind.MEDIA_CONTROL), TuplesKt.to("launcher", ActionKind.LAUNCH_APP), TuplesKt.to("system.setting", ActionKind.SYSTEM_SETTINGS), TuplesKt.to("phone.call", ActionKind.COMMUNICATION), TuplesKt.to("sms", ActionKind.COMMUNICATION), TuplesKt.to("telegram.send", ActionKind.COMMUNICATION), TuplesKt.to(NotificationReplyTool.ID, ActionKind.COMMUNICATION), TuplesKt.to("knowledge", ActionKind.READ_ONLY), TuplesKt.to("weather", ActionKind.READ_ONLY), TuplesKt.to("clipboard", ActionKind.READ_ONLY), TuplesKt.to("notifications.list", ActionKind.READ_ONLY), TuplesKt.to("calendar", ActionKind.READ_ONLY), TuplesKt.to("contacts.search", ActionKind.READ_ONLY), TuplesKt.to("files.search", ActionKind.READ_ONLY), TuplesKt.to("screenshot", ActionKind.UI_AUTOMATION), TuplesKt.to("ui.click", ActionKind.UI_AUTOMATION), TuplesKt.to("ui.type", ActionKind.UI_AUTOMATION), TuplesKt.to("ui.swipe", ActionKind.UI_AUTOMATION), TuplesKt.to("ui.global", ActionKind.UI_AUTOMATION), TuplesKt.to("ui.read", ActionKind.UI_AUTOMATION), TuplesKt.to("ui.wait", ActionKind.UI_AUTOMATION));
    private static final Map<String, String> SUMMARY_BY_TOOL = MapsKt.mapOf(TuplesKt.to("flashlight", "Фонарик"), TuplesKt.to("volume", "Громкость"), TuplesKt.to("brightness", "Яркость"), TuplesKt.to("timer", "Таймер"), TuplesKt.to("music", "Музыка"), TuplesKt.to("launcher", "Приложение"), TuplesKt.to("system.setting", "Настройка"), TuplesKt.to("phone.call", "Звонок"), TuplesKt.to("sms", "SMS"), TuplesKt.to("telegram.send", "Telegram"), TuplesKt.to(NotificationReplyTool.ID, "Ответ на сообщение"), TuplesKt.to("knowledge", "Знания"), TuplesKt.to("weather", "Погода"), TuplesKt.to("clipboard", "Буфер"), TuplesKt.to("notifications.list", "Уведомления"), TuplesKt.to("calendar", "Календарь"), TuplesKt.to("contacts.search", "Контакты"), TuplesKt.to("files.search", "Файлы"), TuplesKt.to("screenshot", "Скриншот"), TuplesKt.to("ui.click", "UI click"), TuplesKt.to("ui.type", "UI type"), TuplesKt.to("ui.swipe", "UI swipe"), TuplesKt.to("ui.global", "UI global"), TuplesKt.to("ui.read", "UI read"), TuplesKt.to("ui.wait", "UI wait"));

    private ProposedActionMapper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ProposedAction fromToolId$default(ProposedActionMapper proposedActionMapper, String str, String str2, Map map, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        return proposedActionMapper.fromToolId(str, str2, map, str3);
    }

    public final ProposedAction fromToolId(String toolId, String actionId, Map<String, String> args, String displaySummary) {
        String summary;
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(actionId, "actionId");
        Intrinsics.checkNotNullParameter(args, "args");
        ActionKind kind = KIND_BY_TOOL.get(toolId);
        if (kind == null) {
            return null;
        }
        if (displaySummary == null) {
            String str = SUMMARY_BY_TOOL.get(toolId);
            if (str != null) {
                summary = str;
            } else {
                summary = toolId;
            }
        } else {
            summary = displaySummary;
        }
        return new ProposedAction(actionId, kind, summary, toolId, args);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ProposedAction fromCapabilityId$default(ProposedActionMapper proposedActionMapper, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        return proposedActionMapper.fromCapabilityId(str, map);
    }

    public final ProposedAction fromCapabilityId(String capabilityId, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(capabilityId, "capabilityId");
        Intrinsics.checkNotNullParameter(args, "args");
        String toolId = TOOL_BY_CAPABILITY.get(capabilityId);
        if (toolId == null) {
            return null;
        }
        String lowerCase = capabilityId.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return fromToolId(toolId, lowerCase, args, SUMMARY_BY_TOOL.get(toolId));
    }

    public final ProposedAction fromBehaviorPatternKey(String patternKey) {
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        String key = patternKey.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(key, "toLowerCase(...)");
        if (StringsKt.contains$default((CharSequence) key, (CharSequence) "flashlight", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) key, (CharSequence) "фонарик", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) key, (CharSequence) "torch", false, 2, (Object) null)) {
            return fromToolId$default(this, "flashlight", null, MapsKt.mapOf(TuplesKt.to("action", "on")), null, 10, null);
        }
        if (StringsKt.contains$default((CharSequence) key, (CharSequence) "volume", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) key, (CharSequence) "громк", false, 2, (Object) null)) {
            return fromToolId$default(this, "volume", "volume_up", MapsKt.mapOf(TuplesKt.to("action", "up")), null, 8, null);
        }
        if (StringsKt.contains$default((CharSequence) key, (CharSequence) "music", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) key, (CharSequence) "media", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) key, (CharSequence) "headphones", false, 2, (Object) null)) {
            return fromToolId$default(this, "music", "music_play", MapsKt.mapOf(TuplesKt.to("action", "play")), null, 8, null);
        }
        if (StringsKt.contains$default((CharSequence) key, (CharSequence) "brightness", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) key, (CharSequence) "ярк", false, 2, (Object) null)) {
            return fromToolId$default(this, "brightness", null, MapsKt.mapOf(TuplesKt.to("action", "up")), null, 10, null);
        }
        return null;
    }

    public final Set<String> getFIRST_SAFE_TOOL_IDS() {
        return FIRST_SAFE_TOOL_IDS;
    }
}
