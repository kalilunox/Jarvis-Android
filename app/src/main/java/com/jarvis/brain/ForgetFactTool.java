package com.jarvis.brain;

import com.google.android.gms.actions.SearchIntents;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ForgetFactTool.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/jarvis/brain/ForgetFactTool;", "Lcom/jarvis/core/common/Tool;", JarvisActivity.MEMORY, "Lkotlin/Function0;", "Lcom/jarvis/brain/LongTermMemoryInterface;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "result", "status", "Lcom/jarvis/core/common/model/ToolResultStatus;", "message", "", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ForgetFactTool implements Tool {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int MIN_WORD = 3;

    @Deprecated
    public static final int STEM = 4;
    private final ToolDefinition definition;
    private final Function0<LongTermMemoryInterface> memory;

    /* JADX WARN: Multi-variable type inference failed */
    public ForgetFactTool(Function0<? extends LongTermMemoryInterface> memory) {
        Intrinsics.checkNotNullParameter(memory, "memory");
        this.memory = memory;
        this.definition = new ToolDefinition("memory.forget", "Забыть факт", "Удалить из памяти факт по словам", CollectionsKt.listOf(new ToolParameter(SearchIntents.EXTRA_QUERY, "о чём забыть", ParameterType.STRING, true, null, 16, null)), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        List stems;
        String str;
        Iterable $this$filter$iv;
        int $i$f$filter;
        int $i$f$filter2;
        boolean z;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        String string = obj != null ? obj.toString() : null;
        if (string == null) {
            string = "";
        }
        String lowerCase = string.toLowerCase(Locale.ROOT);
        String str2 = "toLowerCase(...)";
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable $this$filter$iv2 = StringsKt.split$default((CharSequence) lowerCase, new char[]{' '}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv2) {
            String it = (String) element$iv$iv;
            if (it.length() >= 3) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it2 = (String) item$iv$iv;
            destination$iv$iv2.add(StringsKt.take(it2, 4));
        }
        List stems2 = (List) destination$iv$iv2;
        if (stems2.isEmpty()) {
            return result(call, ToolResultStatus.AMBIGUOUS, "Что именно забыть, сэр?");
        }
        LongTermMemoryInterface store = this.memory.invoke();
        Iterable $this$filter$iv3 = store.all();
        int $i$f$filter3 = 0;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv3) {
            LongTermMemory.MemoryFact fact = (LongTermMemory.MemoryFact) element$iv$iv2;
            Iterable $this$all$iv = stems2;
            if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
                stems = stems2;
                str = str2;
                $this$filter$iv = $this$filter$iv3;
                $i$f$filter = $i$f$filter3;
                z = true;
                $i$f$filter2 = 0;
            } else {
                Iterator it3 = $this$all$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        stems = stems2;
                        str = str2;
                        $this$filter$iv = $this$filter$iv3;
                        $i$f$filter = $i$f$filter3;
                        $i$f$filter2 = 0;
                        z = true;
                        break;
                    }
                    Object element$iv = it3.next();
                    String it4 = (String) element$iv;
                    stems = stems2;
                    Iterable $this$all$iv2 = $this$all$iv;
                    String lowerCase2 = fact.getText().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, str2);
                    str = str2;
                    $this$filter$iv = $this$filter$iv3;
                    $i$f$filter = $i$f$filter3;
                    $i$f$filter2 = 0;
                    if (!StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) it4, false, 2, (Object) null)) {
                        z = false;
                        break;
                    }
                    $i$f$filter3 = $i$f$filter;
                    stems2 = stems;
                    $this$all$iv = $this$all$iv2;
                    str2 = str;
                    $this$filter$iv3 = $this$filter$iv;
                }
            }
            if (z) {
                destination$iv$iv3.add(element$iv$iv2);
            }
            $i$f$filter3 = $i$f$filter;
            stems2 = stems;
            str2 = str;
            $this$filter$iv3 = $this$filter$iv;
        }
        List found = (List) destination$iv$iv3;
        if (found.isEmpty()) {
            return result(call, ToolResultStatus.SUCCESS, "Такого я не помню, сэр.");
        }
        List $this$forEach$iv = found;
        for (Object element$iv2 : $this$forEach$iv) {
            LongTermMemory.MemoryFact it5 = (LongTermMemory.MemoryFact) element$iv2;
            store.remove(it5.getId());
        }
        return result(call, ToolResultStatus.SUCCESS, "Забыл, сэр.");
    }

    private final ToolResult result(ToolCall call, ToolResultStatus status, String message) {
        return new ToolResult(call.getRequestId(), getDefinition().getId(), status, message, null, 0L, 48, null);
    }

    /* JADX INFO: compiled from: ForgetFactTool.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/jarvis/brain/ForgetFactTool$Companion;", "", "<init>", "()V", "MIN_WORD", "", "STEM", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
