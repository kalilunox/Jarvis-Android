package com.jarvis.core.cloud;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ChainRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Lcom/jarvis/core/cloud/AttemptEvent;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.core.cloud.ChainRouter$run$2$event$1$2", f = "ChainRouter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ChainRouter$run$2$event$1$2 extends SuspendLambda implements Function1<Continuation<? super AttemptEvent>, Object> {
    int label;

    ChainRouter$run$2$event$1$2(Continuation<? super ChainRouter$run$2$event$1$2> continuation) {
        super(1, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new ChainRouter$run$2$event$1$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super AttemptEvent> continuation) {
        return ((ChainRouter$run$2$event$1$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return null;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
