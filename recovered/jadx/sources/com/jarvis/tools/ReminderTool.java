package com.jarvis.tools;

import androidx.core.app.NotificationCompat;
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
import com.jarvis.core.reminder.Reminder;
import com.jarvis.core.reminder.ReminderBook;
import com.jarvis.core.reminder.ReminderParser;
import com.jarvis.core.reminder.ReminderRequest;
import com.jarvis.core.reminder.ReminderSpeech;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: ReminderTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0013H\u0002J \u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/jarvis/tools/ReminderTool;", "Lcom/jarvis/core/common/Tool;", "book", "Lcom/jarvis/core/reminder/ReminderBook;", "now", "Lkotlin/Function0;", "Ljava/time/LocalDateTime;", "<init>", "(Lcom/jarvis/core/reminder/ReminderBook;Lkotlin/jvm/functions/Function0;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "add", "confirmation", "", "r", "Lcom/jarvis/core/reminder/Reminder;", "list", "cancel", "ok", "message", "result", "status", "Lcom/jarvis/core/common/model/ToolResultStatus;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReminderTool implements Tool {
    private final ReminderBook book;
    private final ToolDefinition definition;
    private final Function0<LocalDateTime> now;

    public ReminderTool(ReminderBook book, Function0<LocalDateTime> now) {
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(now, "now");
        this.book = book;
        this.now = now;
        this.definition = new ToolDefinition(NotificationCompat.CATEGORY_REMINDER, "Напоминания", "Поставить, показать или отменить напоминание с текстом на указанное время", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("action", "add, list или cancel", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"add", "list", "cancel"})), new ToolParameter("phrase", "что и когда напомнить своими словами", ParameterType.STRING, false, null, 16, null), new ToolParameter(SearchIntents.EXTRA_QUERY, "какое напоминание отменить", ParameterType.STRING, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    public /* synthetic */ ReminderTool(ReminderBook reminderBook, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reminderBook, (i & 2) != 0 ? AnonymousClass1.INSTANCE : anonymousClass1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.jarvis.tools.ReminderTool$1, reason: invalid class name */
    /* JADX INFO: compiled from: ReminderTool.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<LocalDateTime> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(0, LocalDateTime.class, "now", "now()Ljava/time/LocalDateTime;", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final LocalDateTime invoke() {
            return LocalDateTime.now();
        }
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("action");
        String string = obj != null ? obj.toString() : null;
        if (Intrinsics.areEqual(string, "list")) {
            return list(call);
        }
        return Intrinsics.areEqual(string, "cancel") ? cancel(call) : add(call);
    }

    private final ToolResult add(ToolCall call) {
        ReminderParser reminderParser = ReminderParser.INSTANCE;
        Object obj = call.getArguments().get("phrase");
        String string = obj != null ? obj.toString() : null;
        if (string == null) {
            string = "";
        }
        ReminderRequest parsed = reminderParser.parse(string, this.now.invoke());
        LocalDateTime at = parsed.getAt();
        if (at == null) {
            return result(call, ToolResultStatus.AMBIGUOUS, "Когда напомнить, сэр?");
        }
        Reminder saved = this.book.add(at, parsed.getText(), parsed.getDaily(), Intrinsics.areEqual(call.getArguments().get("deferred"), (Object) true));
        return ok(call, confirmation(saved));
    }

    private final String confirmation(Reminder r) {
        String moment;
        String verb;
        StringBuilder sbAppend;
        String str;
        if (r.getDaily()) {
            moment = String.format("каждый день в %d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(r.getAt().getHour()), Integer.valueOf(r.getAt().getMinute())}, 2));
            Intrinsics.checkNotNullExpressionValue(moment, "format(...)");
        } else {
            moment = ReminderSpeech.INSTANCE.whenPhrase(r.getAt(), this.now.invoke());
        }
        if (r.getCommand() && r.getDaily()) {
            verb = "Буду выполнять";
        } else if (r.getCommand()) {
            verb = "Выполню";
        } else {
            verb = r.getDaily() ? "Буду напоминать" : "Напомню";
        }
        if (StringsKt.isBlank(r.getText())) {
            sbAppend = new StringBuilder().append(verb).append(StringUtils.SPACE).append(moment);
            str = ", сэр.";
        } else {
            sbAppend = new StringBuilder().append(verb).append(StringUtils.SPACE).append(moment).append(", сэр: ").append(r.getText());
            str = ".";
        }
        return sbAppend.append(str).toString();
    }

    private final ToolResult list(ToolCall call) {
        Iterable $this$sortedBy$iv = this.book.all();
        List all = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.tools.ReminderTool$list$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Reminder it = (Reminder) t;
                Reminder it2 = (Reminder) t2;
                return ComparisonsKt.compareValues(it.getAt(), it2.getAt());
            }
        });
        if (all.isEmpty()) {
            return ok(call, "Напоминаний нет, сэр.");
        }
        String items = CollectionsKt.joinToString$default(all, "; ", null, null, 0, null, new Function1() { // from class: com.jarvis.tools.ReminderTool$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReminderTool.list$lambda$1(this.f$0, (Reminder) obj);
            }
        }, 30, null);
        return ok(call, "Напоминаний: " + all.size() + ". " + items + ".");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence list$lambda$1(ReminderTool this$0, Reminder r) {
        Intrinsics.checkNotNullParameter(r, "r");
        String moment = ReminderSpeech.INSTANCE.whenPhrase(r.getAt(), this$0.now.invoke());
        if (StringsKt.isBlank(r.getText())) {
            return moment;
        }
        return moment + ": " + r.getText();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x006f  */
    private final ToolResult cancel(ToolCall call) {
        String string;
        List<Reminder> listAll = this.book.all();
        if (listAll.isEmpty()) {
            return ok(call, "Напоминаний нет, сэр.");
        }
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        String string2 = (obj == null || (string = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
        if (string2 == null) {
            string2 = "";
        }
        String query = string2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(query, "toLowerCase(...)");
        Object obj2 = call.getArguments().get("all");
        boolean everything = Intrinsics.areEqual(obj2 != null ? obj2.toString() : null, "true");
        if (!everything) {
            if (!(query.length() == 0)) {
                List<Reminder> $this$filter$iv = listAll;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    Reminder it = (Reminder) element$iv$iv;
                    String lowerCase = it.getText().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    List<Reminder> list = listAll;
                    String query2 = query;
                    if (StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) query, false, 2, (Object) null)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                    listAll = list;
                    query = query2;
                }
                listAll = (List) destination$iv$iv;
            }
        }
        if (listAll.isEmpty()) {
            return result(call, ToolResultStatus.AMBIGUOUS, "Такого напоминания нет, сэр.");
        }
        if (listAll.size() > 1 && !everything) {
            return result(call, ToolResultStatus.AMBIGUOUS, "Их " + listAll.size() + ", сэр. Какое отменить или все?");
        }
        Iterable $this$forEach$iv = listAll;
        for (Object element$iv : $this$forEach$iv) {
            Reminder it2 = (Reminder) element$iv;
            this.book.remove(it2.getId());
        }
        return ok(call, listAll.size() == 1 ? "Напоминание отменено, сэр." : "Все напоминания отменены, сэр.");
    }

    private final ToolResult ok(ToolCall call, String message) {
        return result(call, ToolResultStatus.SUCCESS, message);
    }

    private final ToolResult result(ToolCall call, ToolResultStatus status, String message) {
        return new ToolResult(call.getRequestId(), getDefinition().getId(), status, message, null, 0L, 48, null);
    }
}
