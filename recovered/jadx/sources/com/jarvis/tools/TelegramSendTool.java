package com.jarvis.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.VoiceService;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.nlp.ContactPhraseNormalizer;
import com.jarvis.core.nlp.Transliterator;
import com.jarvis.screencontrol.ActionCoordinator;
import com.jarvis.screencontrol.ScreenController;
import com.jarvis.screencontrol.UiNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TelegramSendTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/jarvis/tools/TelegramSendTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "coordinator", "Lcom/jarvis/screencontrol/ActionCoordinator;", "getCoordinator", "()Lcom/jarvis/screencontrol/ActionCoordinator;", "coordinator$delegate", "Lkotlin/Lazy;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "fail", NotificationCompat.CATEGORY_MESSAGE, "", "openTelegram", "", "findTelegramByLabel", "alreadyInChat", JarvisActivity.CHAT, "hasMessageCompose", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TelegramSendTool implements Tool {
    private static final List<String> TELEGRAM_PACKAGES = CollectionsKt.listOf((Object[]) new String[]{"org.telegram.messenger", "org.telegram.messenger.web", "org.thunderdog.challegram"});
    private final Context context;

    /* JADX INFO: renamed from: coordinator$delegate, reason: from kotlin metadata */
    private final Lazy coordinator;
    private final ToolDefinition definition;

    public TelegramSendTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.coordinator = LazyKt.lazy(new Function0() { // from class: com.jarvis.tools.TelegramSendTool$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TelegramSendTool.coordinator_delegate$lambda$0(this.f$0);
            }
        });
        this.definition = new ToolDefinition("telegram.send", "Написать в Telegram", "Открывает Telegram, находит указанный чат/контакт, вводит сообщение и нажимает Отправить", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter(JarvisActivity.CHAT, "Имя чата или контакта", ParameterType.STRING, true, null, 16, null), new ToolParameter(VoiceService.EXTRA_TEXT, "Текст сообщения", ParameterType.STRING, true, null, 16, null)}), RiskLevel.CONFIRM, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ActionCoordinator coordinator_delegate$lambda$0(TelegramSendTool this$0) {
        return new ActionCoordinator(this$0.context);
    }

    private final ActionCoordinator getCoordinator() {
        return (ActionCoordinator) this.coordinator.getValue();
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        boolean z;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(VoiceService.EXTRA_TEXT);
        String str = null;
        String string = obj != null ? obj.toString() : null;
        String lastErr = "";
        if (string == null) {
            string = "";
        }
        String text = StringsKt.trim((CharSequence) string).toString();
        if (StringsKt.isBlank(text)) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указан текст сообщения", null, 0L, 48, null);
        }
        Object obj2 = call.getArguments().get(JarvisActivity.CHAT);
        String string2 = obj2 != null ? obj2.toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        String chatRaw = StringsKt.trim((CharSequence) string2).toString();
        if (StringsKt.isBlank(chatRaw)) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указан чат или контакт", null, 0L, 48, null);
        }
        String strCanonical = ContactPhraseNormalizer.INSTANCE.canonical(chatRaw);
        if (StringsKt.isBlank(strCanonical)) {
            strCanonical = chatRaw;
        }
        String chatLabel = strCanonical;
        List<String> listSearchQueries = ContactPhraseNormalizer.INSTANCE.searchQueries(chatRaw);
        if (listSearchQueries.isEmpty()) {
            listSearchQueries = CollectionsKt.listOf(chatLabel);
        }
        List<String> list = listSearchQueries;
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Служба спец. возможностей Jarvis не включена.", null, 0L, 48, null);
        }
        List steps = new ArrayList();
        try {
            try {
                if (!openTelegram()) {
                    return fail(call, "Не удалось открыть Telegram.");
                }
                steps.add("Открыл Telegram");
                Thread.sleep(600L);
                List<String> $this$any$iv = list;
                if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object element$iv = it.next();
                            String it2 = (String) element$iv;
                            if (alreadyInChat(it2)) {
                                z = true;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
                if (z) {
                    steps.add("Чат «" + chatLabel + "» уже открыт");
                } else {
                    if (hasMessageCompose()) {
                        getCoordinator().findAndClick("назад", (14 & 2) != 0 ? false : false, (14 & 4) != 0 ? 5 : 0, (14 & 8) != 0 ? 4000L : 1500L);
                        Thread.sleep(700L);
                    }
                    ActionCoordinator.ActionResult search = getCoordinator().findAndClick("поиск", (14 & 2) != 0 ? false : false, (14 & 4) != 0 ? 5 : 1, (14 & 8) != 0 ? 4000L : 3000L);
                    if (!search.getSuccess()) {
                        return fail(call, "Не нашёл поиск в Telegram. " + CollectionsKt.joinToString$default(steps, ". ", null, null, 0, null, null, 62, null));
                    }
                    steps.add("Поиск");
                    Thread.sleep(500L);
                    boolean opened = false;
                    for (String q : list) {
                        ActionCoordinator.ActionResult typed = getCoordinator().findAndType(str, q);
                        if (!typed.getSuccess()) {
                            lastErr = typed.getMessage();
                        } else {
                            String chatRaw2 = chatRaw;
                            try {
                                List<String> list2 = list;
                                try {
                                    steps.add("Ищу «" + q + "»");
                                    Thread.sleep(1600L);
                                    ActionCoordinator.ActionResult openChat = getCoordinator().findAndClick(q, (14 & 2) != 0 ? false : false, (14 & 4) != 0 ? 5 : 1, (14 & 8) != 0 ? 4000L : 3500L);
                                    if (openChat.getSuccess()) {
                                        List list3 = steps;
                                        String matchedText = openChat.getMatchedText();
                                        if (matchedText == null) {
                                            matchedText = q;
                                        }
                                        list3.add("Открыл «" + matchedText + "»");
                                        opened = true;
                                        break;
                                    }
                                    lastErr = openChat.getMessage();
                                    chatRaw = chatRaw2;
                                    search = search;
                                    list = list2;
                                    str = null;
                                } catch (Throwable th) {
                                    t = th;
                                }
                            } catch (Throwable th2) {
                                t = th2;
                            }
                        }
                    }
                    if (!opened) {
                        return fail(call, "Не открыл чат «" + chatLabel + "». " + lastErr);
                    }
                    Thread.sleep(1000L);
                }
                ActionCoordinator.ActionResult typedMsg = getCoordinator().findAndTypeInBottomField(text);
                if (!typedMsg.getSuccess()) {
                    return fail(call, "Не ввёл текст в чат. " + typedMsg.getMessage());
                }
                steps.add("Ввёл текст");
                Thread.sleep(400L);
                ActionCoordinator.ActionResult sent = getCoordinator().findAndClick("отправить", (14 & 2) != 0 ? false : false, (14 & 4) != 0 ? 5 : 0, (14 & 8) != 0 ? 4000L : 2500L);
                if (!sent.getSuccess()) {
                    return fail(call, "Текст введён, но «Отправить» не нажался. " + sent.getMessage());
                }
                steps.add("Отправил");
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Написал в «" + chatLabel + "» и отправил.", MapsKt.mapOf(TuplesKt.to(JarvisActivity.CHAT, chatLabel), TuplesKt.to("steps", CollectionsKt.joinToString$default(steps, " → ", null, null, 0, null, null, 62, null))), 0L, 32, null);
            } catch (Throwable th3) {
                t = th3;
            }
        } catch (Throwable th4) {
            t = th4;
        }
        String message = t.getMessage();
        if (message == null) {
            message = "Ошибка отправки в Telegram";
        }
        return fail(call, message);
    }

    private final ToolResult fail(ToolCall call, String msg) {
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, msg, null, 0L, 48, null);
    }

    private final boolean openTelegram() {
        Object element$iv;
        String it;
        PackageManager pm = this.context.getPackageManager();
        Iterable $this$firstOrNull$iv = TELEGRAM_PACKAGES;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (String) element$iv;
        } while (!(pm.getLaunchIntentForPackage(it) != null));
        String strFindTelegramByLabel = (String) element$iv;
        if (strFindTelegramByLabel == null && (strFindTelegramByLabel = findTelegramByLabel()) == null) {
            return false;
        }
        String pkg = strFindTelegramByLabel;
        Intent intent = pm.getLaunchIntentForPackage(pkg);
        if (intent == null) {
            return false;
        }
        intent.addFlags(268435456);
        this.context.startActivity(intent);
        getCoordinator().waitForPackage("telegram", 4000L);
        return true;
    }

    private final String findTelegramByLabel() {
        Object element$iv;
        ActivityInfo activityInfo;
        boolean z;
        PackageManager pm = this.context.getPackageManager();
        Intent main = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
        Intrinsics.checkNotNullExpressionValue(main, "addCategory(...)");
        Iterable iterableQueryIntentActivities = pm.queryIntentActivities(main, 0);
        Intrinsics.checkNotNullExpressionValue(iterableQueryIntentActivities, "queryIntentActivities(...)");
        Iterable $this$firstOrNull$iv = iterableQueryIntentActivities;
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            ResolveInfo ri = (ResolveInfo) element$iv;
            CharSequence charSequenceLoadLabel = ri.loadLabel(pm);
            String label = charSequenceLoadLabel != null ? charSequenceLoadLabel.toString() : null;
            if (label == null) {
                label = "";
            }
            String key = Transliterator.INSTANCE.compareKey(label);
            z = true;
            if (!StringsKt.contains$default((CharSequence) key, (CharSequence) "telegram", false, 2, (Object) null) && !StringsKt.contains((CharSequence) label, (CharSequence) "телеграм", true)) {
                z = false;
            }
        } while (!z);
        ResolveInfo resolveInfo = (ResolveInfo) element$iv;
        if (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null) {
            return null;
        }
        return activityInfo.packageName;
    }

    private final boolean alreadyInChat(String chat) {
        if (!hasMessageCompose()) {
            return false;
        }
        List listDumpActiveTree$default = ScreenController.dumpActiveTree$default(ScreenController.INSTANCE, 0, 1, null);
        if (listDumpActiveTree$default.isEmpty()) {
            listDumpActiveTree$default = ScreenController.dumpTree$default(ScreenController.INSTANCE, 0, 1, null);
        }
        List nodes = listDumpActiveTree$default;
        String q = chat.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        List $this$any$iv = nodes;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            UiNode n = (UiNode) element$iv;
            String t = n.getText().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
            String d = n.getContentDescription().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(d, "toLowerCase(...)");
            List nodes2 = nodes;
            if ((StringsKt.contains$default((CharSequence) t, (CharSequence) q, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) d, (CharSequence) q, false, 2, (Object) null)) && !n.getEditable()) {
                return true;
            }
            nodes = nodes2;
        }
        return false;
    }

    private final boolean hasMessageCompose() {
        List listDumpActiveTree$default = ScreenController.dumpActiveTree$default(ScreenController.INSTANCE, 0, 1, null);
        if (listDumpActiveTree$default.isEmpty()) {
            listDumpActiveTree$default = ScreenController.dumpTree$default(ScreenController.INSTANCE, 0, 1, null);
        }
        List nodes = listDumpActiveTree$default;
        List $this$any$iv = nodes;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            UiNode n = (UiNode) element$iv;
            if (n.getEditable() && (!StringsKt.isBlank(n.getText()) || StringsKt.contains((CharSequence) n.getContentDescription(), (CharSequence) "сообщен", true) || ((float) n.getBounds().centerY()) > ((float) this.context.getResources().getDisplayMetrics().heightPixels) * 0.75f)) {
                return true;
            }
        }
        return false;
    }
}
