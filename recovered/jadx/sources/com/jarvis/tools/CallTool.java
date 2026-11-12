package com.jarvis.tools;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import java.io.IOException;
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
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: CallTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/jarvis/tools/CallTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "redial", "history", "dial", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public CallTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("phone.call", "Звонок", "Позвонить контакту/номеру или прочитать историю звонков", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("contact", "Имя контакта или номер", ParameterType.STRING, false, null, 16, null), new ToolParameter("action", "call или history", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"call", "history", "redial"})), new ToolParameter("limit", "Сколько записей истории (по умолчанию 5)", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.CONFIRM, CollectionsKt.listOf((Object[]) new String[]{"android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG"}), OnlineRequirement.LOCAL_ONLY, null, 128, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0029  */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String string;
        String action = "call";
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("action");
        if (obj != null && (string = obj.toString()) != null) {
            String lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                action = lowerCase;
            } else if (call.getArguments().get("contact") == null) {
                action = "history";
            }
        } else if (call.getArguments().get("contact") == null && call.getArguments().get("number") == null) {
            action = "history";
        }
        if (Intrinsics.areEqual(action, "history")) {
            return history(call);
        }
        return Intrinsics.areEqual(action, "redial") ? redial(call) : dial(call);
    }

    private final ToolResult redial(ToolCall call) throws IOException {
        if (this.context.checkSelfPermission("android.permission.READ_CALL_LOG") != 0) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения на историю звонков.", null, 0L, 48, null);
        }
        boolean z = true;
        Cursor cursorQuery = this.context.getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[]{"number"}, null, null, "date DESC");
        String str = null;
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor it = cursor;
                String string = it.moveToFirst() ? it.getString(0) : null;
                CloseableKt.closeFinally(cursor, null);
                str = string;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursor, th);
                    throw th2;
                }
            }
        }
        String number = str;
        String str2 = number;
        if (str2 != null && !StringsKt.isBlank(str2)) {
            z = false;
        }
        return z ? new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "В истории нет звонков, сэр.", null, 0L, 48, null) : dial(ToolCall.copy$default(call, null, null, null, MapsKt.mapOf(TuplesKt.to("number", number)), 7, null));
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00e8 A[Catch: all -> 0x0156, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0156, blocks: (B:30:0x00ca, B:32:0x00d0, B:44:0x00f1, B:45:0x00f5, B:52:0x0105, B:40:0x00e8), top: B:84:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00ee  */
    private final ToolResult history(ToolCall call) {
        int limit;
        String string;
        Throwable th;
        Cursor cursor;
        String str;
        if (this.context.checkSelfPermission("android.permission.READ_CALL_LOG") != 0) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения на историю звонков.", null, 0L, 48, null);
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
            int i = 1;
            String[] projection = {"number", "name", "type", "date", TypedValues.TransitionType.S_DURATION};
            ArrayList rows = new ArrayList();
            Cursor cursorQuery = this.context.getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, null, null, "date DESC");
            if (cursorQuery != null) {
                Cursor cursor2 = cursorQuery;
                try {
                    Cursor c = cursor2;
                    int iNum = c.getColumnIndex("number");
                    int iName = c.getColumnIndex("name");
                    int iType = c.getColumnIndex("type");
                    int iDate = c.getColumnIndex("date");
                    int n = 0;
                    try {
                        while (c.moveToNext()) {
                            try {
                                if (n < RangesKt.coerceIn(limit, i, 20)) {
                                    String name = c.getString(iName);
                                    if (name == null) {
                                        name = c.getString(iNum);
                                        if (name == null) {
                                            name = "";
                                        }
                                    } else {
                                        if (StringsKt.isBlank(name)) {
                                            name = null;
                                        }
                                        if (name == null) {
                                            name = c.getString(iNum);
                                            if (name == null) {
                                                name = "";
                                            }
                                        }
                                    }
                                    switch (c.getInt(iType)) {
                                        case 1:
                                            str = "вх";
                                            break;
                                        case 2:
                                            str = "исх";
                                            break;
                                        case 3:
                                            str = "проп";
                                            break;
                                        default:
                                            str = "?";
                                            break;
                                    }
                                    String type = str;
                                    Cursor cursor3 = cursor2;
                                    try {
                                        String whenStr = fmt.format(new Date(c.getLong(iDate)));
                                        int iDate2 = iDate;
                                        rows.add(type + StringUtils.SPACE + name + " (" + whenStr + ")");
                                        n++;
                                        cursor2 = cursor3;
                                        iDate = iDate2;
                                        i = 1;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        cursor2 = cursor3;
                                    }
                                } else {
                                    cursor = cursor2;
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(cursor, null);
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                            th = th;
                            try {
                                throw th;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(cursor2, th);
                                throw th4;
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, null);
                    } catch (Throwable th5) {
                        th = th5;
                        cursor2 = cursor;
                    }
                    cursor = cursor2;
                } catch (Throwable th6) {
                    th = th6;
                }
            }
            return rows.isEmpty() ? new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "История звонков пуста.", null, 0L, 48, null) : new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Последние звонки: " + CollectionsKt.joinToString$default(rows, "; ", null, null, 0, null, null, 62, null) + ".", null, 0L, 48, null);
        } catch (Throwable t) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:112:0x0111 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x00d8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:56:0x00da  */
    /* JADX WARN: Code duplicated, block: B:57:0x00df  */
    /* JADX WARN: Code duplicated, block: B:59:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:68:0x010e  */
    /* JADX WARN: Code duplicated, block: B:92:0x01e6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x01e8 A[Catch: all -> 0x0254, TryCatch #0 {all -> 0x0254, blocks: (B:81:0x0174, B:83:0x01c7, B:85:0x01d1, B:87:0x01d7, B:95:0x01ef, B:98:0x0204, B:100:0x022b, B:99:0x0218, B:93:0x01e8, B:82:0x019e), top: B:105:0x0172 }] */
    /* JADX WARN: Code duplicated, block: B:94:0x01ed  */
    private final ToolResult dial(ToolCall call) throws IOException {
        String $this$filter$iv;
        String $this$filterTo$iv$iv;
        Appendable destination$iv$iv;
        int index$iv$iv;
        int length;
        String $this$filter$iv2;
        char element$iv$iv;
        char it;
        String it2;
        CharSequence $this$filter$iv3;
        String string;
        Object obj = call.getArguments().get("contact");
        String string2 = (obj == null || (string = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
        if (string2 == null) {
            string2 = "";
        }
        String raw = string2;
        if (StringsKt.isBlank(raw) && call.getArguments().get("number") == null) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Кому звонить?", null, 0L, 48, null);
        }
        Object obj2 = call.getArguments().get("number");
        if (obj2 == null || ($this$filter$iv3 = obj2.toString()) == null) {
            $this$filter$iv = null;
        } else {
            CharSequence $this$filterTo$iv$iv2 = $this$filter$iv3;
            Appendable destination$iv$iv2 = new StringBuilder();
            int length2 = $this$filterTo$iv$iv2.length();
            for (int index$iv$iv2 = 0; index$iv$iv2 < length2; index$iv$iv2++) {
                char element$iv$iv2 = $this$filterTo$iv$iv2.charAt(index$iv$iv2);
                char it3 = (Character.isDigit(element$iv$iv2) || element$iv$iv2 == '+') ? (char) 1 : (char) 0;
                if (it3 != 0) {
                    destination$iv$iv2.append(element$iv$iv2);
                }
            }
            $this$filter$iv = ((StringBuilder) destination$iv$iv2).toString();
        }
        String presetNumber = $this$filter$iv;
        String str = presetNumber;
        ContactResolver.Match match = str == null || StringsKt.isBlank(str) ? ContactResolver.INSTANCE.resolvePhone(this.context, raw) : null;
        if (presetNumber != null) {
            $this$filter$iv2 = presetNumber.length() >= 3 ? presetNumber : null;
            if ($this$filter$iv2 == null) {
                if (match != null) {
                    $this$filter$iv2 = match.getNumber();
                } else {
                    $this$filterTo$iv$iv = raw;
                    destination$iv$iv = new StringBuilder();
                    length = $this$filterTo$iv$iv.length();
                    for (index$iv$iv = 0; index$iv$iv < length; index$iv$iv++) {
                        element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
                        if (Character.isDigit(element$iv$iv)) {
                        }
                        if (it != 0) {
                            destination$iv$iv.append(element$iv$iv);
                        }
                    }
                    $this$filter$iv2 = ((StringBuilder) destination$iv$iv).toString();
                }
            }
        } else if (match != null) {
            $this$filter$iv2 = match.getNumber();
        } else {
            $this$filterTo$iv$iv = raw;
            destination$iv$iv = new StringBuilder();
            length = $this$filterTo$iv$iv.length();
            while (index$iv$iv < length) {
                element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
                it = (!Character.isDigit(element$iv$iv) || element$iv$iv == '+') ? (char) 1 : (char) 0;
                if (it != 0) {
                    destination$iv$iv.append(element$iv$iv);
                }
            }
            $this$filter$iv2 = ((StringBuilder) destination$iv$iv).toString();
        }
        String number = $this$filter$iv2;
        if (number.length() < 3) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не нашёл контакт «" + raw + "». Нужно разрешение на контакты или точный номер.", null, 0L, 48, null);
        }
        boolean hasCall = this.context.checkSelfPermission("android.permission.CALL_PHONE") == 0;
        try {
            if (hasCall) {
                Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + number)).addFlags(268435456);
                Intrinsics.checkNotNullExpressionValue(intent, "addFlags(...)");
                this.context.startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + number)).addFlags(268435456);
                Intrinsics.checkNotNullExpressionValue(intent2, "addFlags(...)");
                this.context.startActivity(intent2);
            }
            Object obj3 = call.getArguments().get("contact");
            if (obj3 != null && (it2 = obj3.toString()) != null) {
                if (StringsKt.isBlank(it2)) {
                    it2 = null;
                }
                if (it2 == null) {
                    if (match != null) {
                        it2 = match.getName();
                    } else {
                        it2 = number;
                    }
                }
            } else if (match != null) {
                it2 = match.getName();
            } else {
                it2 = number;
            }
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, (hasCall ? new StringBuilder().append("Звоню «").append(it2).append("».") : new StringBuilder().append("Открыл набор номера «").append(it2).append("».")).toString(), MapsKt.mapOf(TuplesKt.to("number", number), TuplesKt.to("name", it2)), 0L, 32, null);
        } catch (Throwable t) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }
}
