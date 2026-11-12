package com.jarvis.app.ui;

import com.jarvis.app.VoiceIdentity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0014\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$voiceLock$1$2$1$embedding$1", f = "More.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SectionScreen$voiceLock$1$2$1$embedding$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super float[]>, Object> {
    final /* synthetic */ float[] $pcm;
    int label;
    final /* synthetic */ SectionScreen this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SectionScreen$voiceLock$1$2$1$embedding$1(SectionScreen sectionScreen, float[] fArr, Continuation<? super SectionScreen$voiceLock$1$2$1$embedding$1> continuation) {
        super(2, continuation);
        this.this$0 = sectionScreen;
        this.$pcm = fArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SectionScreen$voiceLock$1$2$1$embedding$1(this.this$0, this.$pcm, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super float[]> continuation) {
        return ((SectionScreen$voiceLock$1$2$1$embedding$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return VoiceIdentity.INSTANCE.embedSample(this.this$0.getHost(), this.$pcm);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
