package com.jarvis.app;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrainGraph.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final /* synthetic */ class BrainGraph$build$1$orchestrator$11 extends FunctionReferenceImpl implements Function1<String, List<? extends String>> {
    BrainGraph$build$1$orchestrator$11(Object obj) {
        super(1, obj, PrefsCustomRoutines.class, "steps", "steps(Ljava/lang/String;)Ljava/util/List;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final List<String> invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((PrefsCustomRoutines) this.receiver).steps(p0);
    }
}
