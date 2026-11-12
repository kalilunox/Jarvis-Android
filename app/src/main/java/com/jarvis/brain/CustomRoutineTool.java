package com.jarvis.brain;

import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.notes.CustomRoutines;
import com.jarvis.core.routines.EventRoutines;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomRoutineTool.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/jarvis/brain/CustomRoutineTool;", "Lcom/jarvis/core/common/Tool;", JarvisActivity.ROUTINES, "Lcom/jarvis/core/notes/CustomRoutines;", "events", "Lcom/jarvis/core/routines/EventRoutines;", "<init>", "(Lcom/jarvis/core/notes/CustomRoutines;Lcom/jarvis/core/routines/EventRoutines;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomRoutineTool implements Tool {
    private final ToolDefinition definition;
    private final EventRoutines events;
    private final CustomRoutines routines;

    public CustomRoutineTool(CustomRoutines routines, EventRoutines events) {
        Intrinsics.checkNotNullParameter(routines, "routines");
        Intrinsics.checkNotNullParameter(events, "events");
        this.routines = routines;
        this.events = events;
        this.definition = new ToolDefinition("routine.custom", "Сценарии", "Создать, удалить или перечислить свои сценарии", CollectionsKt.listOf(new ToolParameter("phrase", "просьба словами пользователя", ParameterType.STRING, true, null, 16, null)), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String str;
        String reply;
        Intrinsics.checkNotNullParameter(call, "call");
        CustomRoutineParser customRoutineParser = CustomRoutineParser.INSTANCE;
        Object obj = call.getArguments().get("phrase");
        String string = obj != null ? obj.toString() : null;
        if (string == null) {
            string = "";
        }
        CustomRoutineParser.Request request = customRoutineParser.parse(string);
        if (request instanceof CustomRoutineParser.Request.Define) {
            this.routines.put(((CustomRoutineParser.Request.Define) request).getName(), ((CustomRoutineParser.Request.Define) request).getSteps());
            reply = "Запомнил сценарий «" + ((CustomRoutineParser.Request.Define) request).getName() + "», сэр. Шагов: " + ((CustomRoutineParser.Request.Define) request).getSteps().size() + ".";
        } else if (request instanceof CustomRoutineParser.Request.DefineEvent) {
            this.routines.put(((CustomRoutineParser.Request.DefineEvent) request).getRule().getName(), ((CustomRoutineParser.Request.DefineEvent) request).getSteps());
            this.events.put(((CustomRoutineParser.Request.DefineEvent) request).getRule());
            reply = "Хорошо, сэр. Сработает, когда: " + ((CustomRoutineParser.Request.DefineEvent) request).getRule().getName() + ". Шагов: " + ((CustomRoutineParser.Request.DefineEvent) request).getSteps().size() + ".";
        } else if (request instanceof CustomRoutineParser.Request.Delete) {
            reply = this.routines.remove(((CustomRoutineParser.Request.Delete) request).getName()) ? "Сценарий удалён, сэр." : "Такого сценария нет, сэр.";
        } else if (Intrinsics.areEqual(request, CustomRoutineParser.Request.Names.INSTANCE)) {
            List<String> listNames = this.routines.names();
            if (listNames.isEmpty()) {
                str = "Своих сценариев пока нет, сэр.";
            } else {
                str = "Ваши сценарии: " + CollectionsKt.joinToString$default(listNames, ", ", null, null, 0, null, null, 62, null) + ".";
            }
            reply = str;
        } else {
            if (request == null) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Что должен делать сценарий, сэр?", null, 0L, 48, null);
            }
            throw new NoWhenBranchMatchedException();
        }
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, reply, null, 0L, 48, null);
    }
}
