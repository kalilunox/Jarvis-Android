package com.jarvis.core.cloud;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ChainRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/jarvis/core/cloud/AttemptEvent;", "it"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.core.cloud.ChainRouter$run$2$event$1$1", f = "ChainRouter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ChainRouter$run$2$event$1$1 extends SuspendLambda implements Function2<AttemptEvent, Continuation<? super AttemptEvent>, Object> {
    /* synthetic */ Object L$0;
    int label;

    ChainRouter$run$2$event$1$1(Continuation<? super ChainRouter$run$2$event$1$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChainRouter$run$2$event$1$1 chainRouter$run$2$event$1$1 = new ChainRouter$run$2$event$1$1(continuation);
        chainRouter$run$2$event$1$1.L$0 = obj;
        return chainRouter$run$2$event$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AttemptEvent attemptEvent, Continuation<? super AttemptEvent> continuation) {
        return ((ChainRouter$run$2$event$1$1) create(attemptEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AttemptEvent it = (AttemptEvent) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return it;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
