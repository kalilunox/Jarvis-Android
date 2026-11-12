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
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UiTools.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/jarvis/tools/UiWaitTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "coordinator", "Lcom/jarvis/screencontrol/ActionCoordinator;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UiWaitTool implements Tool {
    private final ActionCoordinator coordinator;
    private final ToolDefinition definition;

    public UiWaitTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.coordinator = new ActionCoordinator(context);
        this.definition = new ToolDefinition("ui.wait", "Ждать узел", "Ждёт появления элемента на экране по тексту, viewId или пакету приложения (timeout + backoff)", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter(SearchIntents.EXTRA_QUERY, "Текст/описание элемента", ParameterType.STRING, false, null, 16, null), new ToolParameter("viewId", "Подстрока viewIdResourceName", ParameterType.STRING, false, null, 16, null), new ToolParameter("package", "Подстрока packageName активного окна", ParameterType.STRING, false, null, 16, null), new ToolParameter("timeoutMs", "Таймаут ожидания в мс (по умолчанию 5000)", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:52:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:57:0x010e  */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        long timeout;
        long jLongValue;
        String string;
        ActionCoordinator.ActionResult r;
        ToolResultStatus toolResultStatus;
        String it;
        Map mapEmptyMap;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        Long longOrNull = null;
        String query = obj != null ? obj.toString() : null;
        if (query == null) {
            query = "";
        }
        Object obj2 = call.getArguments().get("viewId");
        String string2 = obj2 != null ? obj2.toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        String str = string2;
        if (StringsKt.isBlank(str)) {
            str = null;
        }
        String viewId = str;
        Object obj3 = call.getArguments().get("package");
        String string3 = obj3 != null ? obj3.toString() : null;
        String str2 = string3 != null ? string3 : "";
        if (StringsKt.isBlank(str2)) {
            str2 = null;
        }
        String pkg = str2;
        Object obj4 = call.getArguments().get("timeoutMs");
        Number number = obj4 instanceof Number ? (Number) obj4 : null;
        if (number == null) {
            Object obj5 = call.getArguments().get("timeoutMs");
            if (obj5 != null && (string = obj5.toString()) != null) {
                longOrNull = StringsKt.toLongOrNull(string);
            }
            if (longOrNull != null) {
                jLongValue = longOrNull.longValue();
            } else {
                timeout = 5000;
            }
            if (!StringsKt.isBlank(query) && viewId == null && pkg == null) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Укажи query, viewId или package для ожидания.", null, 0L, 48, null);
            }
            r = ActionCoordinator.waitForNode$default(this.coordinator, query, timeout, pkg, viewId, false, 16, null);
            String requestId = call.getRequestId();
            String id = getDefinition().getId();
            if (r.getSuccess()) {
                toolResultStatus = ToolResultStatus.SUCCESS;
            } else {
                toolResultStatus = ToolResultStatus.FAILED;
            }
            ToolResultStatus toolResultStatus2 = toolResultStatus;
            String message = r.getMessage();
            it = r.getMatchedText();
            if (it != null || (mapEmptyMap = MapsKt.mapOf(TuplesKt.to("matched", it))) == null) {
                mapEmptyMap = MapsKt.emptyMap();
            }
            return new ToolResult(requestId, id, toolResultStatus2, message, mapEmptyMap, 0L, 32, null);
        }
        jLongValue = number.longValue();
        timeout = jLongValue;
        if (!StringsKt.isBlank(query)) {
        }
        r = ActionCoordinator.waitForNode$default(this.coordinator, query, timeout, pkg, viewId, false, 16, null);
        String requestId2 = call.getRequestId();
        String id2 = getDefinition().getId();
        if (r.getSuccess()) {
            toolResultStatus = ToolResultStatus.SUCCESS;
        } else {
            toolResultStatus = ToolResultStatus.FAILED;
        }
        ToolResultStatus toolResultStatus3 = toolResultStatus;
        String message2 = r.getMessage();
        it = r.getMatchedText();
        if (it != null) {
            mapEmptyMap = MapsKt.emptyMap();
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        return new ToolResult(requestId2, id2, toolResultStatus3, message2, mapEmptyMap, 0L, 32, null);
    }
}
