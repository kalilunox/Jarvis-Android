package com.jarvis.brain.cloud;

import com.jarvis.brain.aiprovider.AICompletionRequest;
import com.jarvis.core.cloud.ChainEntry;
import com.jarvis.core.cloud.ChainResult;
import com.jarvis.core.cloud.ChainRouter;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ChainCloudProvider.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/jarvis/core/cloud/ChainResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.brain.cloud.ChainCloudProvider$complete$result$1", f = "ChainCloudProvider.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ChainCloudProvider$complete$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ChainResult>, Object> {
    final /* synthetic */ List<ChainEntry> $entries;
    final /* synthetic */ AICompletionRequest $request;
    final /* synthetic */ ChainCloudProvider$complete$sink$1 $sink;
    int label;
    final /* synthetic */ ChainCloudProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChainCloudProvider$complete$result$1(ChainCloudProvider chainCloudProvider, List<ChainEntry> list, AICompletionRequest aICompletionRequest, ChainCloudProvider$complete$sink$1 chainCloudProvider$complete$sink$1, Continuation<? super ChainCloudProvider$complete$result$1> continuation) {
        super(2, continuation);
        this.this$0 = chainCloudProvider;
        this.$entries = list;
        this.$request = aICompletionRequest;
        this.$sink = chainCloudProvider$complete$sink$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ChainCloudProvider$complete$result$1(this.this$0, this.$entries, this.$request, this.$sink, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ChainResult> continuation) {
        return ((ChainCloudProvider$complete$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object objRun = this.this$0.router.run(new ChainRouter.Request(null, 1, null), this.$entries, this.this$0.getHealth(), this.this$0.buildCloudCall(this.$request), this.$sink, this);
                if (objRun == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objRun;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
