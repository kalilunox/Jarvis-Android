package com.jarvis.tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ClipboardTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/jarvis/tools/ClipboardTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClipboardTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public ClipboardTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("clipboard", "Буфер обмена", "Читает или записывает текст в буфер обмена", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("action", "get или set", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"get", "set"})), new ToolParameter(VoiceService.EXTRA_TEXT, "Текст для записи (для set)", ParameterType.STRING, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String action;
        ClipData.Item itemAt;
        CharSequence charSequenceCoerceToText;
        String string;
        Intrinsics.checkNotNullParameter(call, "call");
        Object systemService = this.context.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipboardManager cm = (ClipboardManager) systemService;
        Object obj = call.getArguments().get("action");
        String string2 = null;
        if (obj == null || (string = obj.toString()) == null) {
            action = null;
        } else {
            action = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(action, "toLowerCase(...)");
        }
        if (action == null) {
            action = "";
        }
        if (Intrinsics.areEqual(action, "get")) {
            ClipData primaryClip = cm.getPrimaryClip();
            if (primaryClip != null && (itemAt = primaryClip.getItemAt(0)) != null && (charSequenceCoerceToText = itemAt.coerceToText(this.context)) != null) {
                string2 = charSequenceCoerceToText.toString();
            }
            String text = string2 != null ? string2 : "";
            if (StringsKt.isBlank(text)) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Буфер обмена пуст.", null, 0L, 48, null);
            }
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "В буфере: " + StringsKt.take(text, 200), MapsKt.mapOf(TuplesKt.to(VoiceService.EXTRA_TEXT, text)), 0L, 32, null);
        }
        if (Intrinsics.areEqual(action, "set")) {
            Object obj2 = call.getArguments().get(VoiceService.EXTRA_TEXT);
            string2 = obj2 != null ? obj2.toString() : null;
            String text2 = string2 != null ? string2 : "";
            if (StringsKt.isBlank(text2)) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Нет текста", null, 0L, 48, null);
            }
            cm.setPrimaryClip(ClipData.newPlainText("jarvis", text2));
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Скопировал в буфер.", null, 0L, 48, null);
        }
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "action: get или set", null, 0L, 48, null);
    }
}
