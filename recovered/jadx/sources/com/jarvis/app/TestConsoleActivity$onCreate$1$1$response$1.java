package com.jarvis.app;

import com.jarvis.core.common.model.AssistantResponse;
import com.jarvis.core.common.model.UserRequest;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TestConsoleActivity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/jarvis/core/common/model/AssistantResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.TestConsoleActivity$onCreate$1$1$response$1", f = "TestConsoleActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TestConsoleActivity$onCreate$1$1$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssistantResponse>, Object> {
    final /* synthetic */ String $text;
    int label;
    final /* synthetic */ TestConsoleActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TestConsoleActivity$onCreate$1$1$response$1(TestConsoleActivity testConsoleActivity, String str, Continuation<? super TestConsoleActivity$onCreate$1$1$response$1> continuation) {
        super(2, continuation);
        this.this$0 = testConsoleActivity;
        this.$text = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TestConsoleActivity$onCreate$1$1$response$1(this.this$0, this.$text, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssistantResponse> continuation) {
        return ((TestConsoleActivity$onCreate$1$1$response$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return this.this$0.getBrain().process(new UserRequest(this.$text, null, null, 0L, null, 30, null));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
