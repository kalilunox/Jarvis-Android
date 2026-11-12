package com.jarvis.tools;

import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: InfoTools.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\"\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u000e\u001a\u0004\u0018\u00010\u0003H$J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0012\u0010\u0011\u001a\u00020\u0003X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/jarvis/tools/ReadTool;", "Lcom/jarvis/core/common/Tool;", "id", "", "name", "description", "online", "Lcom/jarvis/core/common/model/OnlineRequirement;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/OnlineRequirement;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "read", "call", "Lcom/jarvis/core/common/model/ToolCall;", "failure", "getFailure", "()Ljava/lang/String;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
abstract class ReadTool implements Tool {
    private final ToolDefinition definition;

    protected abstract String getFailure();

    protected abstract String read();

    public ReadTool(String id, String name, String description, OnlineRequirement online) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(online, "online");
        this.definition = new ToolDefinition(id, name, description, CollectionsKt.emptyList(), RiskLevel.SAFE, null, online, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    protected String read(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        return read();
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            Result.Companion companion = Result.INSTANCE;
            ReadTool $this$execute_u24lambda_u240 = this;
            objM392constructorimpl = Result.m392constructorimpl($this$execute_u24lambda_u240.read(call));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        String text = (String) objM392constructorimpl;
        String str = text;
        if (str == null || StringsKt.isBlank(str)) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, getFailure(), null, 0L, 48, null);
        }
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, text, null, 0L, 48, null);
    }
}
