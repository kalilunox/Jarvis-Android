package com.jarvis.tools;

import com.google.android.gms.actions.SearchIntents;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NotificationTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/jarvis/tools/NotificationTool;", "Lcom/jarvis/core/common/Tool;", "<init>", "()V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationTool implements Tool {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String OWN_PACKAGE = "com.jarvis.assistant";

    @Deprecated
    public static final int SPOKEN_ITEMS = 5;
    private final ToolDefinition definition = new ToolDefinition("notifications.list", "Уведомления", "Читает уведомления, закрывает, жмёт действие или отвечает текстом (RemoteInput)", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("action", "list, clear или dismiss", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"list", "clear", "dismiss"})), new ToolParameter("key", "Ключ уведомления", ParameterType.STRING, false, null, 16, null), new ToolParameter(SearchIntents.EXTRA_QUERY, "Фильтр по заголовку/тексту", ParameterType.STRING, false, null, 16, null)}), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);

    /* JADX INFO: compiled from: NotificationTool.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/jarvis/tools/NotificationTool$Companion;", "", "<init>", "()V", "OWN_PACKAGE", "", "SPOKEN_ITEMS", "", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:101:0x0227  */
    /* JADX WARN: Code duplicated, block: B:12:0x004e  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String action;
        String lowerCase;
        boolean z;
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(call, "call");
        if (!JarvisNotificationListenerService.INSTANCE.isConnected()) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.UNAVAILABLE, "Служба чтения уведомлений не включена. Включи её в онбординге Jarvis.", null, 0L, 48, null);
        }
        Object obj = call.getArguments().get("action");
        if (obj == null || (string2 = obj.toString()) == null) {
            action = "list";
        } else {
            action = string2.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(action, "toLowerCase(...)");
            if (action == null) {
                action = "list";
            }
        }
        switch (action.hashCode()) {
            case 3322014:
                if (action.equals("list")) {
                    Object obj2 = call.getArguments().get(SearchIntents.EXTRA_QUERY);
                    if (obj2 == null || (string = obj2.toString()) == null) {
                        lowerCase = null;
                    } else {
                        lowerCase = string.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    }
                    String q = lowerCase != null ? lowerCase : "";
                    Iterable $this$filter$iv = JarvisNotificationListenerService.INSTANCE.snapshot();
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        NotificationInfo it = (NotificationInfo) element$iv$iv;
                        if ((Intrinsics.areEqual(it.getPackageName(), "com.jarvis.assistant") || StringsKt.isBlank(it.getTitle()) || StringsKt.isBlank(it.getText())) ? false : true) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                    List items = (List) destination$iv$iv;
                    if (!StringsKt.isBlank(q)) {
                        List $this$filter$iv2 = items;
                        Collection destination$iv$iv2 = new ArrayList();
                        for (Object element$iv$iv2 : $this$filter$iv2) {
                            NotificationInfo it2 = (NotificationInfo) element$iv$iv2;
                            String lowerCase2 = it2.getTitle().toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                            String action2 = action;
                            List items2 = items;
                            if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) q, false, 2, (Object) null)) {
                                z = true;
                            } else {
                                String lowerCase3 = it2.getText().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                                if (StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) q, false, 2, (Object) null)) {
                                    z = true;
                                } else {
                                    String lowerCase4 = it2.getPackageName().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                                    if (StringsKt.contains$default((CharSequence) lowerCase4, (CharSequence) q, false, 2, (Object) null)) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                }
                            }
                            if (z) {
                                destination$iv$iv2.add(element$iv$iv2);
                            }
                            items = items2;
                            action = action2;
                        }
                        items = (List) destination$iv$iv2;
                    }
                    if (items.isEmpty()) {
                        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Нет активных уведомлений.", null, 0L, 48, null);
                    }
                    String summary = CollectionsKt.joinToString$default(CollectionsKt.take(items, 5), "; ", null, null, 0, null, new Function1() { // from class: com.jarvis.tools.NotificationTool$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return NotificationTool.execute$lambda$2((NotificationInfo) obj3);
                        }
                    }, 30, null);
                    return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Уведомлений: " + items.size() + ", сэр. " + summary + ".", MapsKt.mapOf(TuplesKt.to("count", Integer.valueOf(items.size())), TuplesKt.to("keys", CollectionsKt.joinToString$default(CollectionsKt.take(items, 8), "|", null, null, 0, null, new Function1() { // from class: com.jarvis.tools.NotificationTool$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return NotificationTool.execute$lambda$3((NotificationInfo) obj3);
                        }
                    }, 30, null))), 0L, 32, null);
                }
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Неизвестное действие", null, 0L, 48, null);
            case 94746189:
                if (action.equals("clear")) {
                    JarvisNotificationListenerService companion = JarvisNotificationListenerService.INSTANCE.getInstance();
                    boolean ok = companion != null && companion.clearAll();
                    return new ToolResult(call.getRequestId(), getDefinition().getId(), ok ? ToolResultStatus.SUCCESS : ToolResultStatus.FAILED, ok ? "Уведомления убраны, сэр." : "Не удалось убрать уведомления.", null, 0L, 48, null);
                }
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Неизвестное действие", null, 0L, 48, null);
            case 1671672458:
                if (action.equals("dismiss")) {
                    Object obj3 = call.getArguments().get("key");
                    String string3 = obj3 != null ? obj3.toString() : null;
                    String key = string3 != null ? string3 : "";
                    if (StringsKt.isBlank(key)) {
                        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Нужен key уведомления", null, 0L, 48, null);
                    }
                    JarvisNotificationListenerService companion2 = JarvisNotificationListenerService.INSTANCE.getInstance();
                    boolean ok2 = companion2 != null && companion2.dismiss(key);
                    return new ToolResult(call.getRequestId(), getDefinition().getId(), ok2 ? ToolResultStatus.SUCCESS : ToolResultStatus.FAILED, ok2 ? "Уведомление закрыто." : "Не удалось закрыть.", null, 0L, 48, null);
                }
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Неизвестное действие", null, 0L, 48, null);
            default:
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Неизвестное действие", null, 0L, 48, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence execute$lambda$2(NotificationInfo it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getTitle() + ": " + StringsKt.take(it.getText(), 60);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence execute$lambda$3(NotificationInfo it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getKey();
    }
}
