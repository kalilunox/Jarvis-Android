package com.jarvis.app;

import dev.ffmpegkit.llama.Llama;
import dev.ffmpegkit.llama.LlamaModel;
import dev.ffmpegkit.llama.LlamaResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LlamaChatLlmEngine.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Ldev/ffmpegkit/llama/LlamaResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.LlamaChatLlmEngine$generate$result$1", f = "LlamaChatLlmEngine.kt", i = {}, l = {188}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class LlamaChatLlmEngine$generate$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LlamaResult>, Object> {
    final /* synthetic */ LlamaModel $m;
    final /* synthetic */ LlamaChatLlmEngine.ProfileParams $p;
    final /* synthetic */ String $prompt;
    final /* synthetic */ String $system;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LlamaChatLlmEngine$generate$result$1(LlamaModel llamaModel, String str, String str2, LlamaChatLlmEngine.ProfileParams profileParams, Continuation<? super LlamaChatLlmEngine$generate$result$1> continuation) {
        super(2, continuation);
        this.$m = llamaModel;
        this.$prompt = str;
        this.$system = str2;
        this.$p = profileParams;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LlamaChatLlmEngine$generate$result$1(this.$m, this.$prompt, this.$system, this.$p, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super LlamaResult> continuation) {
        return ((LlamaChatLlmEngine$generate$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object objComplete = Llama.INSTANCE.complete(this.$m, this.$prompt, this.$system, this.$p.getMaxTokens(), this);
                if (objComplete == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objComplete;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
