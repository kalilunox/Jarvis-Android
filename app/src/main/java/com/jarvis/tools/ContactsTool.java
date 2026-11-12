package com.jarvis.tools;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
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
import com.jarvis.core.nlp.Transliterator;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ContactsTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/jarvis/tools/ContactsTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "searchNames", "", "", SearchIntents.EXTRA_QUERY, "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ContactsTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public ContactsTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("contacts.search", "Контакты", "Ищет контакт по имени и возвращает номер телефона", CollectionsKt.listOf(new ToolParameter(SearchIntents.EXTRA_QUERY, "Имя контакта", ParameterType.STRING, true, null, 16, null)), RiskLevel.LOW, CollectionsKt.listOf("android.permission.READ_CONTACTS"), OnlineRequirement.LOCAL_ONLY, null, 128, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String string;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        String query = (obj == null || (string = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
        if (query == null) {
            query = "";
        }
        if (StringsKt.isBlank(query)) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Кого искать?", null, 0L, 48, null);
        }
        if (this.context.checkSelfPermission("android.permission.READ_CONTACTS") != 0) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения на чтение контактов.", null, 0L, 48, null);
        }
        ContactResolver.Match match = ContactResolver.INSTANCE.resolvePhone(this.context, query);
        if (match != null) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "«" + match.getName() + "»: " + match.getNumber(), MapsKt.mapOf(TuplesKt.to("name", match.getName()), TuplesKt.to("number", match.getNumber())), 0L, 32, null);
        }
        List names = CollectionsKt.take(searchNames(query), 5);
        if (names.isEmpty()) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Контакт «" + query + "» не найден.", null, 0L, 48, null);
        }
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Нашёл: " + CollectionsKt.joinToString$default(names, ", ", null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to("names", CollectionsKt.joinToString$default(names, "|", null, null, 0, null, null, 62, null))), 0L, 32, null);
    }

    private final List<String> searchNames(String query) throws IOException {
        String str = "toLowerCase(...)";
        String qNorm = Transliterator.INSTANCE.compareKey(query);
        Cursor cursor = this.context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"display_name"}, null, null, null);
        if (cursor == null) {
            return CollectionsKt.emptyList();
        }
        LinkedHashSet out = new LinkedHashSet();
        Cursor cursor2 = cursor;
        try {
            Cursor it = cursor2;
            while (it.moveToNext()) {
                String name = it.getString(0);
                if (name == null) {
                    name = "";
                }
                if (!StringsKt.isBlank(name)) {
                    String n = name.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(n, str);
                    String nNorm = Transliterator.INSTANCE.compareKey(name);
                    String lowerCase = query.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, str);
                    String str2 = str;
                    if (StringsKt.contains$default((CharSequence) n, (CharSequence) lowerCase, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) nNorm, (CharSequence) qNorm, false, 2, (Object) null)) {
                        out.add(name);
                    }
                    str = str2;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor2, null);
            return CollectionsKt.toList(out);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursor2, th);
                throw th2;
            }
        }
    }
}
