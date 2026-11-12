package com.jarvis.tools;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.reminder.ReminderParser;
import com.jarvis.core.reminder.ReminderRequest;
import com.jarvis.core.reminder.ReminderSpeech;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CalendarTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/jarvis/tools/CalendarTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "listEvents", "createEvent", "firstCalendarId", "", "()Ljava/lang/Long;", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CalendarTool implements Tool {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long DAY_MS = 86400000;

    @Deprecated
    public static final long HOUR_MS = 3600000;

    @Deprecated
    public static final int LIST_WINDOW_DAYS = 30;

    @Deprecated
    public static final int WEEK_LIMIT = 12;
    private final Context context;
    private final ToolDefinition definition;

    public CalendarTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("calendar", "Календарь", "Создаёт событие или читает ближайшие события календаря", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("action", "create или list", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"create", "list"})), new ToolParameter("title", "Название события (для create)", ParameterType.STRING, false, null, 16, null), new ToolParameter("limit", "Сколько событий показать (list)", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0032  */
    /* JADX WARN: Code duplicated, block: B:11:0x0035  */
    /* JADX WARN: Code duplicated, block: B:8:0x0026  */
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
                if (call.getArguments().get("title") != null) {
                    action = "create";
                } else {
                    action = "list";
                }
            }
        } else if (call.getArguments().get("title") != null) {
            action = "create";
        } else {
            action = "list";
        }
        return Intrinsics.areEqual(action, "list") ? listEvents(call) : createEvent(call);
    }

    /* JADX WARN: Code duplicated, block: B:82:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:85:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:86:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:90:0x0201  */
    /* JADX WARN: Code duplicated, block: B:96:0x0236 A[Catch: all -> 0x029a, TryCatch #3 {all -> 0x029a, blocks: (B:93:0x0209, B:97:0x0262, B:96:0x0236, B:100:0x0292), top: B:142:0x0209 }] */
    private final ToolResult listEvents(ToolCall call) {
        int limit;
        String string;
        Integer day;
        String string2;
        Long dayStart;
        long end;
        Throwable th;
        int iTitle;
        boolean allDay;
        String str;
        String str2 = "allDay";
        if (this.context.checkSelfPermission("android.permission.READ_CALENDAR") != 0) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения на чтение календаря.", null, 0L, 48, null);
        }
        Object obj = call.getArguments().get("limit");
        Number number = obj instanceof Number ? (Number) obj : null;
        if (number != null) {
            limit = number.intValue();
        } else {
            Object obj2 = call.getArguments().get("limit");
            Integer intOrNull = (obj2 == null || (string = obj2.toString()) == null) ? null : StringsKt.toIntOrNull(string);
            if (intOrNull != null) {
                limit = intOrNull.intValue();
            } else {
                limit = call.getArguments().containsKey("days") ? 12 : 5;
            }
        }
        long now = System.currentTimeMillis();
        Object obj3 = call.getArguments().get("day");
        Number number2 = obj3 instanceof Number ? (Number) obj3 : null;
        if (number2 != null) {
            day = Integer.valueOf(number2.intValue());
        } else {
            Object obj4 = call.getArguments().get("day");
            day = (obj4 == null || (string2 = obj4.toString()) == null) ? null : StringsKt.toIntOrNull(string2);
        }
        ZoneId zone = ZoneId.systemDefault();
        if (day != null) {
            int it = day.intValue();
            dayStart = Long.valueOf(LocalDate.now().plusDays(it).atStartOfDay(zone).toInstant().toEpochMilli());
        } else {
            dayStart = null;
        }
        long begin = dayStart != null ? dayStart.longValue() : now;
        Object obj5 = call.getArguments().get("days");
        Number number3 = obj5 instanceof Number ? (Number) obj5 : null;
        Integer days = number3 != null ? Integer.valueOf(number3.intValue()) : null;
        if (dayStart != null) {
            end = dayStart.longValue() + 86400000;
        } else {
            end = (((long) (days != null ? days.intValue() : 30)) * 86400000) + now;
        }
        Uri instances = CalendarContract.Instances.CONTENT_URI.buildUpon().appendPath(String.valueOf(begin)).appendPath(String.valueOf(end)).build();
        SimpleDateFormat fmt = new SimpleDateFormat(day != null ? "HH:mm" : "dd.MM HH:mm", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("d MMMM", new Locale("ru"));
        dayFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            ArrayList rows = new ArrayList();
            int n = 1;
            Cursor cursorQuery = this.context.getContentResolver().query(instances, new String[]{"title", "begin", "allDay"}, null, null, "begin ASC");
            try {
                if (cursorQuery != null) {
                    Cursor cursor = cursorQuery;
                    try {
                        Cursor c = cursor;
                        int iTitle2 = c.getColumnIndex("title");
                        int iStart = c.getColumnIndex("begin");
                        int limit2 = 0;
                        while (c.moveToNext()) {
                            int iCoerceIn = RangesKt.coerceIn(limit, n, 20);
                            int n2 = limit2;
                            try {
                                if (n2 >= iCoerceIn) {
                                    break;
                                }
                                String title = c.getString(iTitle2);
                                if (title != null) {
                                    try {
                                        if (StringsKt.isBlank(title)) {
                                            title = null;
                                        }
                                        if (title == null) {
                                            title = "без названия";
                                        }
                                        int iTitle3 = iTitle2;
                                        iTitle = c.getColumnIndexOrThrow(str2);
                                        String str3 = str2;
                                        if (c.getInt(iTitle) == 1) {
                                            allDay = true;
                                        } else {
                                            allDay = false;
                                        }
                                        ArrayList arrayList = rows;
                                        int limit3 = limit;
                                        ZoneId zone2 = zone;
                                        if (allDay) {
                                            try {
                                                try {
                                                    str = title + " (" + dayFormat.format(new Date(c.getLong(iStart))) + ")";
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                            }
                                        } else {
                                            str = title + " (" + fmt.format(new Date(c.getLong(iStart))) + ")";
                                        }
                                        arrayList.add(str);
                                        int i = n2 + 1;
                                        iTitle2 = iTitle3;
                                        limit = limit3;
                                        zone = zone2;
                                        dayStart = dayStart;
                                        begin = begin;
                                        n = 1;
                                        limit2 = i;
                                        str2 = str3;
                                    } catch (Throwable th4) {
                                        th = th4;
                                    }
                                } else {
                                    title = "без названия";
                                    int iTitle4 = iTitle2;
                                    iTitle = c.getColumnIndexOrThrow(str2);
                                    String str4 = str2;
                                    if (c.getInt(iTitle) == 1) {
                                        allDay = true;
                                    } else {
                                        allDay = false;
                                    }
                                    ArrayList arrayList2 = rows;
                                    int limit4 = limit;
                                    ZoneId zone3 = zone;
                                    if (allDay) {
                                        str = title + " (" + dayFormat.format(new Date(c.getLong(iStart))) + ")";
                                    } else {
                                        str = title + " (" + fmt.format(new Date(c.getLong(iStart))) + ")";
                                    }
                                    arrayList2.add(str);
                                    int i2 = n2 + 1;
                                    iTitle2 = iTitle4;
                                    limit = limit4;
                                    zone = zone3;
                                    dayStart = dayStart;
                                    begin = begin;
                                    n = 1;
                                    limit2 = i2;
                                    str2 = str4;
                                }
                                throw th;
                                throw th;
                            } catch (Throwable th5) {
                                CloseableKt.closeFinally(cursor, th);
                                throw th5;
                            }
                            th = th2;
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, null);
                    } catch (Throwable th6) {
                        th = th6;
                    }
                }
                if (rows.isEmpty()) {
                    return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, day != null ? "В этот день событий нет, сэр." : "Ближайших событий нет, сэр.", null, 0L, 48, null);
                }
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, (day != null ? new StringBuilder().append("События: ").append(CollectionsKt.joinToString$default(rows, "; ", null, null, 0, null, null, 62, null)).append(".") : new StringBuilder().append("Ближайшие события: ").append(CollectionsKt.joinToString$default(rows, "; ", null, null, 0, null, null, 62, null)).append(".")).toString(), null, 0L, 48, null);
            } catch (Throwable th7) {
                t = th7;
                String requestId = call.getRequestId();
                String id = getDefinition().getId();
                ToolResultStatus toolResultStatus = ToolResultStatus.FAILED;
                String message = t.getMessage();
                if (message == null) {
                    message = t.getClass().getSimpleName();
                }
                return new ToolResult(requestId, id, toolResultStatus, "Не удалось прочитать календарь: " + message, null, 0L, 48, null);
            }
        } catch (Throwable th8) {
            t = th8;
        }
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [java.time.ZonedDateTime] */
    private final ToolResult createEvent(ToolCall call) {
        try {
            Object obj = call.getArguments().get("title");
            String title = obj instanceof String ? (String) obj : null;
            String str = title;
            if (str == null || StringsKt.isBlank(str)) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указано название события", null, 0L, 48, null);
            }
            if (this.context.checkSelfPermission("android.permission.WRITE_CALENDAR") != 0) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения на запись в календарь. Выдай разрешение в настройках.", null, 0L, 48, null);
            }
            LocalDateTime moment = LocalDateTime.now();
            ReminderParser reminderParser = ReminderParser.INSTANCE;
            Intrinsics.checkNotNull(moment);
            ReminderRequest parsed = reminderParser.parse(title, moment);
            String text = parsed.getText();
            if (StringsKt.isBlank(text)) {
                text = title;
            }
            String eventTitle = text;
            LocalDateTime startAt = parsed.getAt();
            if (startAt == null) {
                startAt = moment.plusDays(1L);
            }
            long start = startAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            ContentValues values = new ContentValues();
            Long lFirstCalendarId = firstCalendarId();
            values.put("calendar_id", Long.valueOf(lFirstCalendarId != null ? lFirstCalendarId.longValue() : -1L));
            values.put("title", eventTitle);
            values.put("dtstart", Long.valueOf(start));
            values.put("dtend", Long.valueOf(3600000 + start));
            values.put("eventTimezone", TimeZone.getDefault().getID());
            ContentResolver resolver = this.context.getContentResolver();
            Uri uri = resolver.insert(CalendarContract.Events.CONTENT_URI, values);
            if (uri == null) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не удалось создать событие в календаре", null, 0L, 48, null);
            }
            String requestId = call.getRequestId();
            String id = getDefinition().getId();
            ToolResultStatus toolResultStatus = ToolResultStatus.SUCCESS;
            ReminderSpeech reminderSpeech = ReminderSpeech.INSTANCE;
            Intrinsics.checkNotNull(startAt);
            String str2 = "Записал, сэр: " + eventTitle + ", " + reminderSpeech.whenPhrase(startAt, moment) + ".";
            Pair[] pairArr = new Pair[2];
            String lastPathSegment = uri.getLastPathSegment();
            if (lastPathSegment == null) {
                lastPathSegment = "";
            }
            pairArr[0] = TuplesKt.to("eventId", lastPathSegment);
            pairArr[1] = TuplesKt.to("title", eventTitle);
            return new ToolResult(requestId, id, toolResultStatus, str2, MapsKt.mapOf(pairArr), 0L, 32, null);
        } catch (Throwable t) {
            String requestId2 = call.getRequestId();
            String id2 = getDefinition().getId();
            ToolResultStatus toolResultStatus2 = ToolResultStatus.FAILED;
            String message = t.getMessage();
            if (message == null) {
                message = t.getClass().getSimpleName();
            }
            return new ToolResult(requestId2, id2, toolResultStatus2, "Не удалось создать событие: " + message, null, 0L, 48, null);
        }
    }

    private final Long firstCalendarId() {
        Object objM392constructorimpl;
        Long lValueOf;
        try {
            Result.Companion companion = Result.INSTANCE;
            CalendarTool $this$firstCalendarId_u24lambda_u240 = this;
            String[] projection = {"_id"};
            Cursor cursorQuery = $this$firstCalendarId_u24lambda_u240.context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, projection, null, null, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor c = cursor;
                    lValueOf = c.moveToFirst() ? Long.valueOf(c.getLong(0)) : null;
                    CloseableKt.closeFinally(cursor, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursor, th);
                        throw th2;
                    }
                }
            } else {
                lValueOf = null;
            }
            objM392constructorimpl = Result.m392constructorimpl(lValueOf);
        } catch (Throwable th3) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        return (Long) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }

    /* JADX INFO: compiled from: CalendarTool.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/jarvis/tools/CalendarTool$Companion;", "", "<init>", "()V", "HOUR_MS", "", "DAY_MS", "WEEK_LIMIT", "", "LIST_WINDOW_DAYS", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
