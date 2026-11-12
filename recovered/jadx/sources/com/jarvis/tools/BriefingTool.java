package com.jarvis.tools;

import androidx.core.app.NotificationCompat;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.nlp.DailyBriefing;
import java.time.LocalDateTime;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BriefingTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B-\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J0\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00042\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0015H\u0002R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/jarvis/tools/BriefingTool;", "Lcom/jarvis/core/common/Tool;", "findTool", "Lkotlin/Function1;", "", "now", "Lkotlin/Function0;", "Ljava/time/LocalDateTime;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", JarvisActivity.SECTION, "toolId", "args", "", "", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BriefingTool implements Tool {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int EVENTS = 3;
    private final ToolDefinition definition;
    private final Function1<String, Tool> findTool;
    private final Function0<LocalDateTime> now;

    /* JADX WARN: Multi-variable type inference failed */
    public BriefingTool(Function1<? super String, ? extends Tool> findTool, Function0<LocalDateTime> now) {
        Intrinsics.checkNotNullParameter(findTool, "findTool");
        Intrinsics.checkNotNullParameter(now, "now");
        this.findTool = findTool;
        this.now = now;
        this.definition = new ToolDefinition("briefing", "Сводка на день", "Погода, ближайшие события, напоминания и состояние телефона одним докладом", CollectionsKt.emptyList(), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    public /* synthetic */ BriefingTool(Function1 function1, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, (i & 2) != 0 ? AnonymousClass1.INSTANCE : anonymousClass1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.jarvis.tools.BriefingTool$1, reason: invalid class name */
    /* JADX INFO: compiled from: BriefingTool.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<LocalDateTime> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(0, LocalDateTime.class, "now", "now()Ljava/time/LocalDateTime;", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final LocalDateTime invoke() {
            return LocalDateTime.now();
        }
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        LocalDateTime t = this.now.invoke();
        String report = DailyBriefing.INSTANCE.compose(t.getHour(), t.getMinute(), section$default(this, call, "weather", null, 4, null), section(call, "calendar", MapsKt.mapOf(TuplesKt.to("action", "list"), TuplesKt.to("limit", 3), TuplesKt.to("day", 0))), section(call, NotificationCompat.CATEGORY_REMINDER, MapsKt.mapOf(TuplesKt.to("action", "list"))), section$default(this, call, "system.status", null, 4, null));
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, report, null, 0L, 48, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ String section$default(BriefingTool briefingTool, ToolCall toolCall, String str, Map map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        return briefingTool.section(toolCall, str, map);
    }

    private final String section(ToolCall call, String toolId, Map<String, ? extends Object> args) {
        Object objM392constructorimpl;
        String it;
        Tool tool = this.findTool.invoke(toolId);
        if (tool == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            BriefingTool briefingTool = this;
            objM392constructorimpl = Result.m392constructorimpl(tool.execute(new ToolCall(null, call.getRequestId(), toolId, args, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        ToolResult result = (ToolResult) objM392constructorimpl;
        if (result == null || (it = result.getMessage()) == null) {
            return null;
        }
        if (result.getStatus() == ToolResultStatus.SUCCESS && !StringsKt.isBlank(it)) {
            return it;
        }
        return null;
    }

    /* JADX INFO: compiled from: BriefingTool.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/tools/BriefingTool$Companion;", "", "<init>", "()V", "EVENTS", "", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
