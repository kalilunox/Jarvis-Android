package com.jarvis.core.cloud;

import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ChainRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.core.cloud.ChainRouter$run$2$launchAttempt$1", f = "ChainRouter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ChainRouter$run$2$launchAttempt$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Attempt $a;
    final /* synthetic */ CloudCall $cloudCall;
    final /* synthetic */ ChainEntry $entry;
    final /* synthetic */ int $paramIndex;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChainRouter$run$2$launchAttempt$1(CloudCall cloudCall, ChainEntry chainEntry, int i, Attempt attempt, Continuation<? super ChainRouter$run$2$launchAttempt$1> continuation) {
        super(2, continuation);
        this.$cloudCall = cloudCall;
        this.$entry = chainEntry;
        this.$paramIndex = i;
        this.$a = attempt;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ChainRouter$run$2$launchAttempt$1(this.$cloudCall, this.$entry, this.$paramIndex, this.$a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChainRouter$run$2$launchAttempt$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final Attempt attempt = this.$a;
                CancelHandle handle = this.$cloudCall.start(this.$entry, this.$paramIndex, new CallListener() { // from class: com.jarvis.core.cloud.ChainRouter$run$2$launchAttempt$1$listener$1
                    @Override // com.jarvis.core.cloud.CallListener
                    public void onFirstToken(String text) {
                        Intrinsics.checkNotNullParameter(text, "text");
                        attempt.setReceivedEvent(true);
                        String visible = attempt.getStripper().strip(text);
                        if (visible.length() > 0) {
                            attempt.getChannel().mo1918trySendJP2dKIU(new AttemptEvent.VisibleToken(visible, attempt));
                        }
                    }

                    @Override // com.jarvis.core.cloud.CallListener
                    public void onToken(String text) {
                        Intrinsics.checkNotNullParameter(text, "text");
                        attempt.setReceivedEvent(true);
                        String visible = attempt.getStripper().strip(text);
                        if (visible.length() > 0) {
                            attempt.getChannel().mo1918trySendJP2dKIU(new AttemptEvent.VisibleToken(visible, attempt));
                        }
                    }

                    @Override // com.jarvis.core.cloud.CallListener
                    public void onComplete(Map<String, ? extends Object> usage) {
                        attempt.setReceivedEvent(true);
                        String tail = attempt.getStripper().flush();
                        if (tail.length() > 0) {
                            attempt.getChannel().mo1918trySendJP2dKIU(new AttemptEvent.VisibleToken(tail, attempt));
                        }
                        attempt.getChannel().mo1918trySendJP2dKIU(new AttemptEvent.Completed(usage, attempt));
                    }

                    @Override // com.jarvis.core.cloud.CallListener
                    public void onError(Integer httpStatus, String body) {
                        attempt.setReceivedEvent(true);
                        attempt.getChannel().mo1918trySendJP2dKIU(new AttemptEvent.Errored(httpStatus, body, attempt));
                    }
                });
                this.$a.assignHandle(handle);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
