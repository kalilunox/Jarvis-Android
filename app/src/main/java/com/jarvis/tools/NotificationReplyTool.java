package com.jarvis.tools;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.screencontrol.JarvisNotificationListenerService;
import com.jarvis.screencontrol.NotificationInfo;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NotificationTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/jarvis/tools/NotificationReplyTool;", "Lcom/jarvis/core/common/Tool;", "<init>", "()V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "result", "status", "Lcom/jarvis/core/common/model/ToolResultStatus;", "message", "", "resolveKey", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationReplyTool implements Tool {
    public static final String ID = "notifications.reply";
    private final ToolDefinition definition = new ToolDefinition(ID, "Ответ на сообщение", "Ответить на объявленное сообщение из уведомления", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter(VoiceService.EXTRA_TEXT, "Текст ответа", ParameterType.STRING, true, null, 16, null), new ToolParameter(SearchIntents.EXTRA_QUERY, "От кого сообщение", ParameterType.STRING, false, null, 16, null)}), RiskLevel.CONFIRM, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String string;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(VoiceService.EXTRA_TEXT);
        String text = (obj == null || (string = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
        if (text == null) {
            text = "";
        }
        if (StringsKt.isBlank(text)) {
            return result(call, ToolResultStatus.AMBIGUOUS, "Что ответить, сэр?");
        }
        String key = resolveKey(call);
        if (key == null) {
            return result(call, ToolResultStatus.AMBIGUOUS, "Сообщения для ответа нет, сэр.");
        }
        JarvisNotificationListenerService companion = JarvisNotificationListenerService.INSTANCE.getInstance();
        boolean z = false;
        if (companion != null && companion.replyText(key, text)) {
            z = true;
        }
        if (z) {
            return result(call, ToolResultStatus.SUCCESS, "Ответил, сэр.");
        }
        return result(call, ToolResultStatus.FAILED, "На это сообщение нельзя ответить отсюда, сэр.");
    }

    private final ToolResult result(ToolCall call, ToolResultStatus status, String message) {
        return new ToolResult(call.getRequestId(), getDefinition().getId(), status, message, null, 0L, 48, null);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x009a  */
    private final String resolveKey(ToolCall call) {
        String q;
        Object element$iv;
        boolean z;
        String string;
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        if (obj == null || (string = obj.toString()) == null) {
            q = null;
        } else {
            q = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        }
        if (q == null) {
            q = "";
        }
        if (StringsKt.isBlank(q)) {
            return JarvisNotificationListenerService.INSTANCE.getLastAnnouncedKey();
        }
        Iterable $this$firstOrNull$iv = JarvisNotificationListenerService.INSTANCE.snapshot();
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            NotificationInfo it2 = (NotificationInfo) element$iv;
            String lowerCase = it2.getTitle().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            z = false;
            if (StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) q, false, 2, (Object) null)) {
                z = true;
            } else {
                String lowerCase2 = it2.getText().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) q, false, 2, (Object) null)) {
                    z = true;
                } else {
                    String lowerCase3 = it2.getPackageName().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                    if (StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) q, false, 2, (Object) null)) {
                        z = true;
                    }
                }
            }
        } while (!z);
        NotificationInfo notificationInfo = (NotificationInfo) element$iv;
        if (notificationInfo != null) {
            return notificationInfo.getKey();
        }
        return null;
    }
}
