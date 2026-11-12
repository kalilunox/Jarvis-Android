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
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnnounceSettingsTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B?\u00126\u0010\u0002\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R>\u0010\u0002\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Lcom/jarvis/tools/AnnounceSettingsTool;", "Lcom/jarvis/core/common/Tool;", "apply", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "kind", "", "on", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AnnounceSettingsTool implements Tool {
    private static final Companion Companion = new Companion(null);
    private static final Map<String, String> LABELS = MapsKt.mapOf(TuplesKt.to("calls", "о звонках"), TuplesKt.to("messages", "о сообщениях"), TuplesKt.to("hints", "о делах"));
    private final Function2<String, Boolean, Unit> apply;
    private final ToolDefinition definition;

    /* JADX WARN: Multi-variable type inference failed */
    public AnnounceSettingsTool(Function2<? super String, ? super Boolean, Unit> apply) {
        Intrinsics.checkNotNullParameter(apply, "apply");
        this.apply = apply;
        this.definition = new ToolDefinition("announce.settings", "Объявления", "Объявлять звонки, сообщения и подсказки или молчать", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("kind", "calls, messages или hints", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"calls", "messages", "hints"})), new ToolParameter("on", "true или false", ParameterType.STRING, true, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("kind");
        String kind = obj != null ? obj.toString() : null;
        String label = LABELS.get(kind);
        if (label == null) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Что именно, сэр?", null, 0L, 48, null);
        }
        Object obj2 = call.getArguments().get("on");
        boolean on = !Intrinsics.areEqual(obj2 != null ? obj2.toString() : null, "false");
        this.apply.invoke(kind == null ? "" : kind, Boolean.valueOf(on));
        if (on) {
            sb = new StringBuilder();
            str = "Буду сообщать ";
        } else {
            sb = new StringBuilder();
            str = "Больше не сообщаю ";
        }
        String reply = sb.append(str).append(label).append(", сэр.").toString();
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, reply, null, 0L, 48, null);
    }

    /* JADX INFO: compiled from: AnnounceSettingsTool.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/jarvis/tools/AnnounceSettingsTool$Companion;", "", "<init>", "()V", "LABELS", "", "", "getLABELS", "()Ljava/util/Map;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, String> getLABELS() {
            return AnnounceSettingsTool.LABELS;
        }
    }
}
