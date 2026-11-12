package com.jarvis.app.ui;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$voiceLock$record$started$1$1", f = "More.kt", i = {}, l = {228}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SectionScreen$voiceLock$record$started$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<float[], Continuation<? super Unit>, Object> $onDone;
    final /* synthetic */ float[] $pcm;
    final /* synthetic */ Ref.BooleanRef $recording;
    final /* synthetic */ Ref.ObjectRef<Function0<Unit>> $render;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SectionScreen$voiceLock$record$started$1$1(Function2<? super float[], ? super Continuation<? super Unit>, ? extends Object> function2, float[] fArr, Ref.BooleanRef booleanRef, Ref.ObjectRef<Function0<Unit>> objectRef, Continuation<? super SectionScreen$voiceLock$record$started$1$1> continuation) {
        super(2, continuation);
        this.$onDone = function2;
        this.$pcm = fArr;
        this.$recording = booleanRef;
        this.$render = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SectionScreen$voiceLock$record$started$1$1(this.$onDone, this.$pcm, this.$recording, this.$render, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SectionScreen$voiceLock$record$started$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Function0<Unit> function0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Function2<float[], Continuation<? super Unit>, Object> function2 = this.$onDone;
                float[] fArr = this.$pcm;
                this.label = 1;
                if (function2.invoke(fArr, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$recording.element = false;
        if (this.$render.element == null) {
            Intrinsics.throwUninitializedPropertyAccessException("render");
            function0 = null;
        } else {
            function0 = this.$render.element;
        }
        function0.invoke();
        return Unit.INSTANCE;
    }
}
