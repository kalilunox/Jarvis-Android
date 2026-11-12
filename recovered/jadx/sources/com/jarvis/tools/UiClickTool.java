package com.jarvis.tools;

import android.content.Context;
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
import com.jarvis.screencontrol.ActionCoordinator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UiTools.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/tools/UiClickTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "coordinator", "Lcom/jarvis/screencontrol/ActionCoordinator;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "boolArg", "", "key", "", "default", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UiClickTool implements Tool {
    private final ActionCoordinator coordinator;
    private final ToolDefinition definition;

    public UiClickTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.coordinator = new ActionCoordinator(context);
        this.definition = new ToolDefinition("ui.click", "Клик по экрану", "Находит элемент на текущем экране по тексту/описанию и нажимает; при необходимости прокручивает список", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter(SearchIntents.EXTRA_QUERY, "Текст или описание элемента", ParameterType.STRING, true, null, 16, null), new ToolParameter("longPress", "Долгое нажатие", ParameterType.BOOLEAN, false, null, 16, null), new ToolParameter("scroll", "Искать с прокруткой (true/false)", ParameterType.BOOLEAN, false, null, 16, null)}), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) throws InterruptedException {
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        String string = obj != null ? obj.toString() : null;
        if (string == null) {
            string = "";
        }
        String query = string;
        if (StringsKt.isBlank(query)) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указан элемент", null, 0L, 48, null);
        }
        boolean longPress = boolArg$default(this, call, "longPress", false, 4, null);
        boolean scroll = boolArg(call, "scroll", true);
        ActionCoordinator.ActionResult r = this.coordinator.findAndClick(query, (14 & 2) != 0 ? false : longPress, (14 & 4) != 0 ? 5 : scroll ? 5 : 0, (14 & 8) != 0 ? 4000L : 0L);
        String requestId = call.getRequestId();
        String id = getDefinition().getId();
        ToolResultStatus toolResultStatus = r.getSuccess() ? ToolResultStatus.SUCCESS : ToolResultStatus.FAILED;
        String message = r.getMessage();
        Map $this$execute_u24lambda_u240 = MapsKt.createMapBuilder();
        $this$execute_u24lambda_u240.put("usedFallback", Boolean.valueOf(r.getUsedFallback()));
        String it = r.getMatchedText();
        if (it != null) {
            $this$execute_u24lambda_u240.put("matched", it);
        }
        Unit unit = Unit.INSTANCE;
        return new ToolResult(requestId, id, toolResultStatus, message, MapsKt.build($this$execute_u24lambda_u240), 0L, 32, null);
    }

    static /* synthetic */ boolean boolArg$default(UiClickTool uiClickTool, ToolCall toolCall, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return uiClickTool.boolArg(toolCall, str, z);
    }

    private final boolean boolArg(ToolCall call, String key, boolean z) {
        Object v = call.getArguments().get(key);
        if (v instanceof Boolean) {
            return ((Boolean) v).booleanValue();
        }
        if (v instanceof String) {
            return StringsKt.equals((String) v, "true", true);
        }
        if (v == null) {
        }
        return z;
    }
}
