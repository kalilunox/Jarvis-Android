package com.jarvis.app;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrainGraph.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final /* synthetic */ class BrainGraph$build$1$4 extends FunctionReferenceImpl implements Function1<String, String> {
    BrainGraph$build$1$4(Object obj) {
        super(1, obj, AppAgentRunner.class, "start", "start(Ljava/lang/String;)Ljava/lang/String;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final String invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((AppAgentRunner) this.receiver).start(p0);
    }
}
