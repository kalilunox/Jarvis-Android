package com.jarvis.tools;

import android.content.Context;
import android.telecom.TelecomManager;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PhoneControlTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/jarvis/tools/PhoneControlTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "result", "status", "Lcom/jarvis/core/common/model/ToolResultStatus;", "message", "", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PhoneControlTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public PhoneControlTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("phone.control", "Управление звонком", "Ответить на звонок или сбросить его", CollectionsKt.listOf(new ToolParameter("action", "answer или end", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"answer", "end"}))), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        if (this.context.checkSelfPermission("android.permission.ANSWER_PHONE_CALLS") != 0) {
            return result(call, ToolResultStatus.DENIED, "Нет разрешения управлять звонками, сэр.");
        }
        TelecomManager telecom = (TelecomManager) this.context.getSystemService(TelecomManager.class);
        if (telecom == null) {
            return result(call, ToolResultStatus.FAILED, "Телефония недоступна, сэр.");
        }
        try {
            Object obj = call.getArguments().get("action");
            if (Intrinsics.areEqual(obj != null ? obj.toString() : null, "answer")) {
                if (!telecom.isInCall()) {
                    return result(call, ToolResultStatus.SUCCESS, "Входящего звонка нет, сэр.");
                }
                telecom.acceptRingingCall();
                return result(call, ToolResultStatus.SUCCESS, "Соединяю, сэр.");
            }
            if (telecom.endCall()) {
                return result(call, ToolResultStatus.SUCCESS, "Звонок завершён, сэр.");
            }
            return result(call, ToolResultStatus.SUCCESS, "Активного звонка нет, сэр.");
        } catch (SecurityException e) {
            return result(call, ToolResultStatus.DENIED, "Нет разрешения управлять звонками, сэр.");
        }
    }

    private final ToolResult result(ToolCall call, ToolResultStatus status, String message) {
        return new ToolResult(call.getRequestId(), getDefinition().getId(), status, message, null, 0L, 48, null);
    }
}
