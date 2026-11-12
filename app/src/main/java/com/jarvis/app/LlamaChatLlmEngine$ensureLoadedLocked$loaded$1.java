package com.jarvis.app;

import dev.ffmpegkit.llama.Llama;
import dev.ffmpegkit.llama.LlamaConfig;
import dev.ffmpegkit.llama.LlamaModel;
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

/* JADX INFO: compiled from: LlamaChatLlmEngine.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Ldev/ffmpegkit/llama/LlamaModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.LlamaChatLlmEngine$ensureLoadedLocked$loaded$1", f = "LlamaChatLlmEngine.kt", i = {}, l = {253}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class LlamaChatLlmEngine$ensureLoadedLocked$loaded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LlamaModel>, Object> {
    final /* synthetic */ LlamaChatLlmEngine.ProfileParams $p;
    int label;
    final /* synthetic */ LlamaChatLlmEngine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LlamaChatLlmEngine$ensureLoadedLocked$loaded$1(LlamaChatLlmEngine llamaChatLlmEngine, LlamaChatLlmEngine.ProfileParams profileParams, Continuation<? super LlamaChatLlmEngine$ensureLoadedLocked$loaded$1> continuation) {
        super(2, continuation);
        this.this$0 = llamaChatLlmEngine;
        this.$p = profileParams;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LlamaChatLlmEngine$ensureLoadedLocked$loaded$1(this.this$0, this.$p, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super LlamaModel> continuation) {
        return ((LlamaChatLlmEngine$ensureLoadedLocked$loaded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Llama llama = Llama.INSTANCE;
                String absolutePath = this.this$0.modelFile.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                this.label = 1;
                Object objLoadModel = llama.loadModel(absolutePath, new LlamaConfig(this.$p.getContextSize(), this.this$0.threads, 0, this.$p.getTemperature(), this.$p.getTopP(), this.$p.getTopK(), 42), this);
                if (objLoadModel == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objLoadModel;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
