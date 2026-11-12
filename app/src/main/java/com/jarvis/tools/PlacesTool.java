package com.jarvis.tools;

import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.notes.PlaceBook;
import com.jarvis.core.notes.Places;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlacesTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/jarvis/tools/PlacesTool;", "Lcom/jarvis/core/common/Tool;", "places", "Lcom/jarvis/core/notes/PlaceBook;", "<init>", "(Lcom/jarvis/core/notes/PlaceBook;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlacesTool implements Tool {
    private final ToolDefinition definition;
    private final PlaceBook places;

    public PlacesTool(PlaceBook places) {
        Intrinsics.checkNotNullParameter(places, "places");
        this.places = places;
        this.definition = new ToolDefinition("places", "Адреса", "Запомнить адрес дома или работы", CollectionsKt.listOf(new ToolParameter("phrase", "просьба словами пользователя", ParameterType.STRING, true, null, 16, null)), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Places places = Places.INSTANCE;
        Object obj = call.getArguments().get("phrase");
        String string = obj != null ? obj.toString() : null;
        if (string == null) {
            string = "";
        }
        Pair<String, String> pairSaveRequest = places.saveRequest(string);
        if (pairSaveRequest != null) {
            String key = pairSaveRequest.component1();
            String address = pairSaveRequest.component2();
            this.places.set(key, address);
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Адрес сохранён, сэр.", null, 0L, 48, null);
        }
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Какой адрес запомнить, сэр?", null, 0L, 48, null);
    }
}
