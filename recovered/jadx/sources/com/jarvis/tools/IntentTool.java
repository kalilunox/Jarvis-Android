package com.jarvis.tools;

import android.content.Context;
import android.content.Intent;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceIntentTools.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\"\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH$J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000bX¤\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000bX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/jarvis/tools/IntentTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "intentFor", "Landroid/content/Intent;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "done", "", "getDone", "()Ljava/lang/String;", "missing", "getMissing", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
abstract class IntentTool implements Tool {
    private final Context context;

    protected abstract String getDone();

    protected abstract String getMissing();

    protected abstract Intent intentFor(ToolCall call);

    public IntentTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intent intent = intentFor(call);
        if (intent == null) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, getMissing(), null, 0L, 48, null);
        }
        try {
            this.context.startActivity(intent.addFlags(268435456));
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, getDone(), null, 0L, 48, null);
        } catch (Throwable th) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Нет подходящего приложения, сэр.", null, 0L, 48, null);
        }
    }
}
