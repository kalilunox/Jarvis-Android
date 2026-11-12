package com.jarvis.app.ui;

import com.jarvis.app.VoiceIdentity;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0014\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "pcm", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$voiceLock$1$2$1", f = "More.kt", i = {0, 1, 1}, l = {257, TarConstants.VERSION_OFFSET}, m = "invokeSuspend", n = {"pcm", "pcm", "embedding"}, s = {"L$0", "L$0", "L$1"}, v = 1)
final class SectionScreen$voiceLock$1$2$1 extends SuspendLambda implements Function2<float[], Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<String> $hint;
    final /* synthetic */ int $needed;
    final /* synthetic */ ArrayList<float[]> $samples;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SectionScreen this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SectionScreen$voiceLock$1$2$1(Ref.ObjectRef<String> objectRef, ArrayList<float[]> arrayList, int i, SectionScreen sectionScreen, Continuation<? super SectionScreen$voiceLock$1$2$1> continuation) {
        super(2, continuation);
        this.$hint = objectRef;
        this.$samples = arrayList;
        this.$needed = i;
        this.this$0 = sectionScreen;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SectionScreen$voiceLock$1$2$1 sectionScreen$voiceLock$1$2$1 = new SectionScreen$voiceLock$1$2$1(this.$hint, this.$samples, this.$needed, this.this$0, continuation);
        sectionScreen$voiceLock$1$2$1.L$0 = obj;
        return sectionScreen$voiceLock$1$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(float[] fArr, Continuation<? super Unit> continuation) {
        return ((SectionScreen$voiceLock$1$2$1) create(fArr, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x004d  */
    /* JADX WARN: Code duplicated, block: B:14:0x0054  */
    /* JADX WARN: Code duplicated, block: B:16:0x0065  */
    /* JADX WARN: Code duplicated, block: B:18:0x008e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x008f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objWithContext;
        float[] embedding;
        float[] embedding2;
        float[] pcm = (float[]) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.L$0 = SpillingKt.nullOutSpilledVariable(pcm);
                this.label = 1;
                objWithContext = BuildersKt.withContext(Dispatchers.getDefault(), new SectionScreen$voiceLock$1$2$1$embedding$1(this.this$0, pcm, null), this);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                embedding = (float[]) objWithContext;
                if (embedding == null) {
                    this.$hint.element = "Не расслышал, повторите громче.";
                } else {
                    this.$samples.add(embedding);
                    if (this.$samples.size() >= this.$needed) {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(pcm);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(embedding);
                        this.label = 2;
                        if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, this.$samples, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        embedding2 = embedding;
                        this.$samples.clear();
                    }
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                embedding = (float[]) objWithContext;
                if (embedding == null) {
                    this.$hint.element = "Не расслышал, повторите громче.";
                } else {
                    this.$samples.add(embedding);
                    if (this.$samples.size() >= this.$needed) {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(pcm);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(embedding);
                        this.label = 2;
                        if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, this.$samples, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        embedding2 = embedding;
                        this.$samples.clear();
                    }
                }
                return Unit.INSTANCE;
            case 2:
                embedding2 = (float[]) this.L$1;
                ResultKt.throwOnFailure($result);
                this.$samples.clear();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.SectionScreen$voiceLock$1$2$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: More.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$voiceLock$1$2$1$1", f = "More.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ArrayList<float[]> $samples;
        int label;
        final /* synthetic */ SectionScreen this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SectionScreen sectionScreen, ArrayList<float[]> arrayList, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = sectionScreen;
            this.$samples = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$samples, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    VoiceIdentity.INSTANCE.save(this.this$0.getHost(), CollectionsKt.toList(this.$samples));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
