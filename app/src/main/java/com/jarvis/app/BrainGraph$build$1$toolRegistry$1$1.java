package com.jarvis.app;

import com.jarvis.core.common.Tool;
import com.jarvis.core.common.ToolRegistry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrainGraph.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final /* synthetic */ class BrainGraph$build$1$toolRegistry$1$1 extends FunctionReferenceImpl implements Function1<String, Tool> {
    BrainGraph$build$1$toolRegistry$1$1(Object obj) {
        super(1, obj, ToolRegistry.class, "find", "find(Ljava/lang/String;)Lcom/jarvis/core/common/Tool;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Tool invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((ToolRegistry) this.receiver).find(p0);
    }
}
