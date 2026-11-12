package com.jarvis.tools;

import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.telephony.SmsManager;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SmsTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/jarvis/tools/SmsTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "readInbox", "send", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SmsTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public SmsTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("sms", "SMS", "Отправить SMS или прочитать последние сообщения из входящих", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("action", "send или read", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"send", "read"})), new ToolParameter(VoiceService.EXTRA_TEXT, "Текст сообщения (для send)", ParameterType.STRING, false, null, 16, null), new ToolParameter("recipient", "Имя контакта или номер телефона", ParameterType.STRING, false, null, 16, null), new ToolParameter("limit", "Сколько SMS прочитать (по умолчанию 5)", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.CONFIRM, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0033  */
    /* JADX WARN: Code duplicated, block: B:12:0x0036  */
    /* JADX WARN: Code duplicated, block: B:9:0x0027  */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String action;
        String string;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("action");
        if (obj != null && (string = obj.toString()) != null) {
            action = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(action, "toLowerCase(...)");
            if (action == null) {
                if (call.getArguments().get(VoiceService.EXTRA_TEXT) != null) {
                    action = "send";
                } else {
                    action = "read";
                }
            }
        } else if (call.getArguments().get(VoiceService.EXTRA_TEXT) != null) {
            action = "send";
        } else {
            action = "read";
        }
        return Intrinsics.areEqual(action, "read") ? readInbox(call) : send(call);
    }

    private final ToolResult readInbox(ToolCall call) {
        int limit;
        String string;
        if (this.context.checkSelfPermission("android.permission.READ_SMS") != 0) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения на чтение SMS.", null, 0L, 48, null);
        }
        Object obj = call.getArguments().get("limit");
        Number number = obj instanceof Number ? (Number) obj : null;
        if (number != null) {
            limit = number.intValue();
        } else {
            Object obj2 = call.getArguments().get("limit");
            Integer intOrNull = (obj2 == null || (string = obj2.toString()) == null) ? null : StringsKt.toIntOrNull(string);
            limit = intOrNull != null ? intOrNull.intValue() : 5;
        }
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM HH:mm", Locale.getDefault());
        try {
            ArrayList rows = new ArrayList();
            Cursor cursorQuery = this.context.getContentResolver().query(Telephony.Sms.Inbox.CONTENT_URI, new String[]{"address", "body", "date"}, null, null, "date DESC");
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor c = cursor;
                    int iAddr = c.getColumnIndex("address");
                    int iBody = c.getColumnIndex("body");
                    int iDate = c.getColumnIndex("date");
                    int n = 0;
                    for (int i = 1; c.moveToNext() && n < RangesKt.coerceIn(limit, i, 20); i = 1) {
                        String addr = c.getString(iAddr);
                        String str = "";
                        if (addr == null) {
                            addr = "";
                        }
                        String string2 = c.getString(iBody);
                        if (string2 != null) {
                            str = string2;
                        }
                        String body = StringsKt.take(str, 80);
                        String whenStr = fmt.format(new Date(c.getLong(iDate)));
                        rows.add("от " + addr + " (" + whenStr + "): " + body);
                        n++;
                        iDate = iDate;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursor, th);
                        throw th2;
                    }
                }
            }
            return rows.isEmpty() ? new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Входящих SMS нет.", null, 0L, 48, null) : new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Последние SMS: " + CollectionsKt.joinToString$default(rows, "; ", null, null, 0, null, null, 62, null) + ".", null, 0L, 48, null);
        } catch (Throwable t) {
            String requestId = call.getRequestId();
            String id = getDefinition().getId();
            ToolResultStatus toolResultStatus = ToolResultStatus.FAILED;
            String message = t.getMessage();
            if (message == null) {
                message = t.getClass().getSimpleName();
            }
            return new ToolResult(requestId, id, toolResultStatus, "Не удалось прочитать SMS: " + message, null, 0L, 48, null);
        }
    }

    private final ToolResult send(ToolCall call) {
        String number;
        String label;
        try {
            Object obj = call.getArguments().get(VoiceService.EXTRA_TEXT);
            ContactResolver.Match resolved = null;
            String text = obj instanceof String ? (String) obj : null;
            String str = text;
            if (str == null || StringsKt.isBlank(str)) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указан текст сообщения", null, 0L, 48, null);
            }
            if (this.context.checkSelfPermission("android.permission.SEND_SMS") != 0) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения на отправку SMS. Выдай разрешение в настройках.", null, 0L, 48, null);
            }
            Object obj2 = call.getArguments().get("recipient");
            String recipientRaw = obj2 instanceof String ? (String) obj2 : null;
            if (recipientRaw != null) {
                String it = recipientRaw;
                resolved = ContactResolver.INSTANCE.resolvePhone(this.context, it);
            }
            if (resolved == null || (number = resolved.getNumber()) == null) {
                number = recipientRaw;
            }
            String recipient = number;
            String str2 = recipient;
            if (str2 == null || StringsKt.isBlank(str2)) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Укажи получателя (имя контакта или номер).", null, 0L, 48, null);
            }
            SmsManager smsManager = SmsManager.getDefault();
            try {
                smsManager.sendTextMessage(recipient, null, text, null, null);
                if (resolved == null || (label = resolved.getName()) == null) {
                    label = recipient;
                }
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Сообщение отправлено: " + label + ".", MapsKt.mapOf(TuplesKt.to(VoiceService.EXTRA_TEXT, text), TuplesKt.to("recipient", recipient)), 0L, 32, null);
            } catch (Throwable t) {
                String requestId = call.getRequestId();
                String id = getDefinition().getId();
                ToolResultStatus toolResultStatus = ToolResultStatus.FAILED;
                String message = t.getMessage();
                if (message == null) {
                    message = t.getClass().getSimpleName();
                }
                return new ToolResult(requestId, id, toolResultStatus, "Не удалось отправить SMS: " + message, null, 0L, 48, null);
            }
        } catch (Throwable t2) {
            String requestId2 = call.getRequestId();
            String id2 = getDefinition().getId();
            ToolResultStatus toolResultStatus2 = ToolResultStatus.FAILED;
            String message2 = t2.getMessage();
            if (message2 == null) {
                message2 = t2.getClass().getSimpleName();
            }
            return new ToolResult(requestId2, id2, toolResultStatus2, "Не удалось отправить SMS: " + message2, null, 0L, 48, null);
        }
    }
}
