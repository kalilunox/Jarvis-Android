package com.jarvis.brain.cloud;

import com.jarvis.core.cloud.ChainEntry;
import com.jarvis.core.cloud.ChainResult;
import com.jarvis.core.cloud.ChainRouter;
import com.jarvis.core.cloud.CloudCall;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ChainCloudProvider.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lcom/jarvis/core/cloud/ChainResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1$run$1", f = "ChainCloudProvider.kt", i = {0, 0, 0}, l = {231}, m = "invokeSuspend", n = {"$this$async", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-ChainCloudProvider$runStream$result$1$run$1$1"}, s = {"L$0", "L$1", "I$0"}, v = 1)
final class ChainCloudProvider$runStream$result$1$run$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends ChainResult>>, Object> {
    final /* synthetic */ CloudCall $call;
    final /* synthetic */ List<ChainEntry> $entries;
    final /* synthetic */ ChainCloudProvider$runStream$sink$1 $sink;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ChainCloudProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChainCloudProvider$runStream$result$1$run$1(ChainCloudProvider chainCloudProvider, List<ChainEntry> list, CloudCall cloudCall, ChainCloudProvider$runStream$sink$1 chainCloudProvider$runStream$sink$1, Continuation<? super ChainCloudProvider$runStream$result$1$run$1> continuation) {
        super(2, continuation);
        this.this$0 = chainCloudProvider;
        this.$entries = list;
        this.$call = cloudCall;
        this.$sink = chainCloudProvider$runStream$sink$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChainCloudProvider$runStream$result$1$run$1 chainCloudProvider$runStream$result$1$run$1 = new ChainCloudProvider$runStream$result$1$run$1(this.this$0, this.$entries, this.$call, this.$sink, continuation);
        chainCloudProvider$runStream$result$1$run$1.L$0 = obj;
        return chainCloudProvider$runStream$result$1$run$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends ChainResult>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<ChainResult>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<ChainResult>> continuation) {
        return ((ChainCloudProvider$runStream$result$1$run$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM392constructorimpl;
        Object objRun;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    ChainCloudProvider chainCloudProvider = this.this$0;
                    List<ChainEntry> list = this.$entries;
                    CloudCall cloudCall = this.$call;
                    ChainCloudProvider$runStream$sink$1 chainCloudProvider$runStream$sink$1 = this.$sink;
                    Result.Companion companion = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.I$0 = 0;
                    this.label = 1;
                    objRun = chainCloudProvider.router.run(new ChainRouter.Request(null, 1, 0 == true ? 1 : 0), list, chainCloudProvider.getHealth(), cloudCall, chainCloudProvider$runStream$sink$1, this);
                    if (objRun == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    int i = this.I$0;
                    ResultKt.throwOnFailure(obj);
                    objRun = obj;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objM392constructorimpl = Result.m392constructorimpl((ChainResult) objRun);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return Result.m391boximpl(objM392constructorimpl);
    }
}
