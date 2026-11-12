package com.jarvis.brain.cloud;

import com.jarvis.core.cloud.ChainEntry;
import com.jarvis.core.cloud.ChainResult;
import com.jarvis.core.cloud.CloudCall;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: ChainCloudProvider.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/jarvis/core/cloud/ChainResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1", f = "ChainCloudProvider.kt", i = {0, 0, 1, 1}, l = {234, 237}, m = "invokeSuspend", n = {"$this$runBlocking", "run", "$this$runBlocking", "run"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
final class ChainCloudProvider$runStream$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ChainResult>, Object> {
    final /* synthetic */ CloudCall $call;
    final /* synthetic */ AtomicBoolean $cancelFlag;
    final /* synthetic */ List<ChainEntry> $entries;
    final /* synthetic */ ChainCloudProvider$runStream$sink$1 $sink;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ChainCloudProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChainCloudProvider$runStream$result$1(AtomicBoolean atomicBoolean, ChainCloudProvider chainCloudProvider, List<ChainEntry> list, CloudCall cloudCall, ChainCloudProvider$runStream$sink$1 chainCloudProvider$runStream$sink$1, Continuation<? super ChainCloudProvider$runStream$result$1> continuation) {
        super(2, continuation);
        this.$cancelFlag = atomicBoolean;
        this.this$0 = chainCloudProvider;
        this.$entries = list;
        this.$call = cloudCall;
        this.$sink = chainCloudProvider$runStream$sink$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChainCloudProvider$runStream$result$1 chainCloudProvider$runStream$result$1 = new ChainCloudProvider$runStream$result$1(this.$cancelFlag, this.this$0, this.$entries, this.$call, this.$sink, continuation);
        chainCloudProvider$runStream$result$1.L$0 = obj;
        return chainCloudProvider$runStream$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ChainResult> continuation) {
        return ((ChainCloudProvider$runStream$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0079 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:13:0x007c  */
    /* JADX WARN: Code duplicated, block: B:15:0x0084  */
    /* JADX WARN: Code duplicated, block: B:16:0x008b  */
    /* JADX WARN: Code duplicated, block: B:18:0x00a3 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0077 -> B:12:0x007a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:18:0x00a3
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = r0.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r9 = 1
            r10 = 0
            switch(r2) {
                case 0: goto L34;
                case 1: goto L27;
                case 2: goto L19;
                default: goto L11;
            }
        L11:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "call to 'resume' before 'invoke' with coroutine"
            r2.<init>(r3)
            throw r2
        L19:
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.Deferred r2 = (kotlinx.coroutines.Deferred) r2
            kotlin.ResultKt.throwOnFailure(r18)
            r4 = r18
            r3 = r2
            r2 = r1
            r1 = r4
            goto La4
        L27:
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.Deferred r2 = (kotlinx.coroutines.Deferred) r2
            kotlin.ResultKt.throwOnFailure(r18)
            r4 = r18
            r3 = r2
            r2 = r1
            r1 = r4
            goto L7a
        L34:
            kotlin.ResultKt.throwOnFailure(r18)
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getDefault()
            r3 = r2
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1$run$1 r2 = new com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1$run$1
            com.jarvis.brain.cloud.ChainCloudProvider r12 = r0.this$0
            java.util.List<com.jarvis.core.cloud.ChainEntry> r13 = r0.$entries
            com.jarvis.core.cloud.CloudCall r14 = r0.$call
            com.jarvis.brain.cloud.ChainCloudProvider$runStream$sink$1 r15 = r0.$sink
            r16 = 0
            r11 = r2
            r11.<init>(r12, r13, r14, r15, r16)
            r5 = r2
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 2
            r7 = 0
            r4 = 0
            r2 = r1
            kotlinx.coroutines.Deferred r2 = kotlinx.coroutines.BuildersKt.async$default(r2, r3, r4, r5, r6, r7)
            r3 = r2
            r2 = r1
            r1 = r18
        L5d:
            com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1$1 r4 = new com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1$1
            r4.<init>(r3, r10)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$0 = r6
            r0.L$1 = r3
            r0.label = r9
            r6 = 50
            java.lang.Object r4 = kotlinx.coroutines.TimeoutKt.withTimeoutOrNull(r6, r4, r5)
            if (r4 != r8) goto L7a
            return r8
        L7a:
            if (r4 != 0) goto L8b
            java.util.concurrent.atomic.AtomicBoolean r4 = r0.$cancelFlag
            boolean r4 = r4.get()
            if (r4 == 0) goto L5d
            r4 = r3
            kotlinx.coroutines.Job r4 = (kotlinx.coroutines.Job) r4
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r4, r10, r9, r10)
            goto L5d
        L8b:
            r4 = r0
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r4 = r3.await(r4)
            if (r4 != r8) goto La4
            return r8
        La4:
            kotlin.Result r4 = (kotlin.Result) r4
            java.lang.Object r4 = r4.getValue()
            kotlin.ResultKt.throwOnFailure(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: ChainCloudProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.brain.cloud.ChainCloudProvider$runStream$result$1$1", f = "ChainCloudProvider.kt", i = {}, l = {234}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Deferred<Result<ChainResult>> $run;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Deferred<Result<ChainResult>> deferred, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$run = deferred;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$run, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (this.$run.join(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }
}
